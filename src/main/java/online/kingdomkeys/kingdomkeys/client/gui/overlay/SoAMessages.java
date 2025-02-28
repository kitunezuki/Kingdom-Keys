package online.kingdomkeys.kingdomkeys.client.gui.overlay;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.util.Mth;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import online.kingdomkeys.kingdomkeys.util.Utils;

//Text rendering from IngameGui with an added message queue system
public class SoAMessages extends OverlayBase {

    public static final SoAMessages INSTANCE = new SoAMessages();

    public static class Title {
        public String title, subtitle;
        public int fadeIn = 10, fadeOut = 20, displayTime = 70;

        public Title(String title, String subtitle, int fadeIn, int displayTime, int fadeOut) {
            this.title = title;
            this.subtitle = subtitle;
            this.fadeIn = fadeIn;
            this.fadeOut = fadeOut;
            this.displayTime = displayTime;
        }

        public Title(String title, String subtitle) {
            this.title = title;
            this.subtitle = subtitle;
        }
    }

    private final Queue<Title> messages = new LinkedList<>();

    public void queueMessages(Title... messages) {
        this.messages.addAll(Arrays.asList(messages));
    }

    public void queueMessage(Title message) {
        messages.add(message);
    }

    public void clearMessage() {
        messages.clear();
        displayedTitle = "";
        displayedSubTitle = "";
        titlesTimer = 0;
    }

    @Override
    public void render(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        super.render(gui, poseStack, partialTick, width, height);
        if (!messages.isEmpty() || titlesTimer != 0) {
            draw(poseStack, partialTick);
        }
    }

    @SubscribeEvent
    public void clientTick(TickEvent.ClientTickEvent event) {
        if (!Minecraft.getInstance().isPaused() && (!messages.isEmpty() || titlesTimer != 0)) {
            tick();
        }
    }

    String displayedTitle, displayedSubTitle;
    int titleFadeIn, titleDisplayTime, titleFadeOut, titlesTimer;
    float scaledWidth, scaledHeight;


    public void displayTitle(String title, String subTitle, int timeFadeIn, int displayTime, int timeFadeOut) {
        if (title == null && subTitle == null && timeFadeIn < 0 && displayTime < 0 && timeFadeOut < 0) {
            this.displayedTitle = "";
            this.titlesTimer = 0;
        } else if (title != null) {
            this.displayedSubTitle = subTitle;
            this.displayedTitle = title;
            this.titleFadeIn = timeFadeIn;
            this.titleDisplayTime = displayTime;
            this.titleFadeOut = timeFadeOut;
            this.titlesTimer = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut;
        } else if (subTitle != null) {
            this.displayedTitle = "";
            this.displayedSubTitle = subTitle;
            this.titleFadeIn = timeFadeIn;
            this.titleDisplayTime = displayTime;
            this.titleFadeOut = timeFadeOut;
            this.titlesTimer = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut;
        } else {
            if (timeFadeIn >= 0) {
                this.titleFadeIn = timeFadeIn;
            }

            if (displayTime >= 0) {
                this.titleDisplayTime = displayTime;
            }

            if (timeFadeOut >= 0) {
                this.titleFadeOut = timeFadeOut;
            }

            if (this.titlesTimer > 0) {
                this.titlesTimer = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut;
            }

        }
    }

    public void draw(PoseStack matrixStack, float partialTicks) {
        this.scaledWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.scaledHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        if (this.titlesTimer > 0) {
            float f4 = (float)this.titlesTimer - partialTicks;
            int j1 = 255;
            if (this.titlesTimer > this.titleFadeOut + this.titleDisplayTime) {
                float f5 = (float)(this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut) - f4;
                j1 = (int)(f5 * 255.0F / (float)this.titleFadeIn);
            }

            if (this.titlesTimer <= this.titleFadeOut) {
                j1 = (int)(f4 * 255.0F / (float)this.titleFadeOut);
            }

            j1 = Mth.clamp(j1, 0, 255);
            if (j1 > 8) {
                matrixStack.pushPose();
                matrixStack.translate(this.scaledWidth / 2, this.scaledHeight / 2, 0.0F);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                matrixStack.pushPose();
                matrixStack.scale(4.0F, 4.0F, 4.0F);
                int l1 = j1 << 24 & -16777216;
                int i2 = font.width(Utils.translateToLocal(this.displayedTitle));
                this.renderTextBackground(matrixStack, font, -10, i2);
                font.drawShadow(matrixStack, Utils.translateToLocal(this.displayedTitle), (float)(-i2 / 2), -10.0F, 16777215 | l1);
                matrixStack.popPose();
                if (!this.displayedSubTitle.isEmpty()) {
                    matrixStack.pushPose();
                    matrixStack.scale(2.0F, 2.0F, 2.0F);
                    int k = font.width(Utils.translateToLocal(this.displayedSubTitle));
                    this.renderTextBackground(matrixStack, font, 5, k);
                    font.drawShadow(matrixStack, Utils.translateToLocal(this.displayedSubTitle), (float)(-k / 2), 5.0F, 16777215 | l1);
                    matrixStack.popPose();
                }
                RenderSystem.disableBlend();
                matrixStack.popPose();
            }
        }
    }
    protected void renderTextBackground(PoseStack matrixStack, Font fontRendererIn, int yIn, int stringWidthIn) {
        int i = Minecraft.getInstance().options.getBackgroundColor(0.0F);
        if (i != 0) {
            int j = -stringWidthIn / 2;
            GuiComponent.fill(matrixStack, j - 2, yIn - 2, j + stringWidthIn + 2, yIn + 9 + 2, i);
        }

    }

    public void tick() {
        if (this.titlesTimer > 0) {
            --this.titlesTimer;
            if (this.titlesTimer <= 0) {
                this.displayedTitle = "";
                this.displayedSubTitle = "";
            }
        }
        if (!messages.isEmpty() && titlesTimer == 0) {
            Title t = messages.poll();
            displayTitle(t.title, t.subtitle, t.fadeIn, t.displayTime, t.fadeOut);
        }
    }
}
