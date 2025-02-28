package online.kingdomkeys.kingdomkeys.client.gui.synthesis;

import java.awt.Color;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import online.kingdomkeys.kingdomkeys.KingdomKeys;
import online.kingdomkeys.kingdomkeys.client.gui.elements.MenuBackground;
import online.kingdomkeys.kingdomkeys.client.gui.elements.buttons.MenuButton;
import online.kingdomkeys.kingdomkeys.client.gui.elements.buttons.MenuButton.ButtonType;
import online.kingdomkeys.kingdomkeys.client.sound.ModSounds;
import online.kingdomkeys.kingdomkeys.config.ModConfigs;
import online.kingdomkeys.kingdomkeys.lib.Strings;
import online.kingdomkeys.kingdomkeys.synthesis.shop.ShopListRegistry;
import online.kingdomkeys.kingdomkeys.util.Utils;

public class SynthesisScreen extends MenuBackground {
		
	MenuButton synthesise, forge, materials, shop;
	
	String invFile = ModConfigs.projectorHasShop ? "kingdomkeys:default" : "";
	
	public SynthesisScreen() {
		super(Strings.Gui_Synthesis,new Color(0,255,0));
		drawPlayerInfo = true;
	}
	
	public SynthesisScreen(String inv) {
		this();
		if(ShopListRegistry.getInstance().containsKey(new ResourceLocation(inv)))
			this.invFile = inv;
		else {
			KingdomKeys.LOGGER.error("The Shop '"+inv+"' does not exist or didn't get registered");
			this.invFile = "";
		}
	}

	protected void action(String string) {
		switch(string) {
		case "synthesise":
			minecraft.level.playSound(minecraft.player, minecraft.player.blockPosition(), ModSounds.menu_in.get(), SoundSource.MASTER, 1.0f, 1.0f);
			minecraft.setScreen(new SynthesisCreateScreen(this));
			break;
		case "forge":
			minecraft.level.playSound(minecraft.player, minecraft.player.blockPosition(), ModSounds.menu_in.get(), SoundSource.MASTER, 1.0f, 1.0f);
			minecraft.setScreen(new SynthesisForgeScreen(this));
			break;
		case "materials":
			minecraft.level.playSound(minecraft.player, minecraft.player.blockPosition(), ModSounds.menu_in.get(), SoundSource.MASTER, 1.0f, 1.0f);
			minecraft.setScreen(new SynthesisMaterialScreen(this));
			break;
		case "shop":
			minecraft.level.playSound(minecraft.player, minecraft.player.blockPosition(), ModSounds.menu_in.get(), SoundSource.MASTER, 1.0f, 1.0f);
			minecraft.setScreen(new ShopScreen(this));
			break;
		}
	}

	@Override
	public void init() {
		//TODO request packet to sync other players data
		super.width = width;
		super.height = height;
		super.init();
		this.renderables.clear();
				
		float topBarHeight = (float) height * 0.17F;
		int button_statsY = (int) topBarHeight + 5;
		float buttonPosX = (float) width * 0.03F;
		float buttonWidth = ((float) width * 0.1744F) - 20;

		int pos = 0;
		if(invFile != null && !invFile.equals(""))
			addRenderableWidget(shop = new MenuButton((int) buttonPosX, button_statsY + (pos++ * 18), (int) buttonWidth, Utils.translateToLocal(Strings.Gui_Shop), ButtonType.BUTTON, (e) -> { action("shop"); }));

		addRenderableWidget(synthesise = new MenuButton((int) buttonPosX, button_statsY + (pos++ * 18), (int) buttonWidth, Utils.translateToLocal(Strings.Gui_Synthesis_Synthesise), ButtonType.BUTTON, (e) -> { action("synthesise"); }));
		addRenderableWidget(forge = new MenuButton((int) buttonPosX, button_statsY + (pos++ * 18), (int) buttonWidth, Utils.translateToLocal(Strings.Gui_Synthesis_Forge), ButtonType.BUTTON, (e) -> { action("forge"); }));
		addRenderableWidget(materials = new MenuButton((int) buttonPosX, button_statsY + (pos++ * 18), (int) buttonWidth, Utils.translateToLocal(Strings.Gui_Synthesis_Materials), ButtonType.BUTTON, (e) -> { action("materials"); }));
	}

	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		
		//fill(125, ((-140 / 16) + 75) + 10, 200, ((-140 / 16) + 75) + 20, 0xFFFFFF);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
}
