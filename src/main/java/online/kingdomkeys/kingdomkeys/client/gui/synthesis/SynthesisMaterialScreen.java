package online.kingdomkeys.kingdomkeys.client.gui.synthesis;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.registries.ForgeRegistries;
import online.kingdomkeys.kingdomkeys.KingdomKeys;
import online.kingdomkeys.kingdomkeys.capability.IPlayerCapabilities;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.client.gui.elements.MenuBox;
import online.kingdomkeys.kingdomkeys.client.gui.elements.MenuFilterable;
import online.kingdomkeys.kingdomkeys.client.gui.elements.buttons.MenuButton;
import online.kingdomkeys.kingdomkeys.client.gui.elements.buttons.MenuButton.ButtonType;
import online.kingdomkeys.kingdomkeys.client.gui.elements.buttons.MenuStockItem;
import online.kingdomkeys.kingdomkeys.client.sound.ModSounds;
import online.kingdomkeys.kingdomkeys.lib.Strings;
import online.kingdomkeys.kingdomkeys.network.PacketHandler;
import online.kingdomkeys.kingdomkeys.network.cts.CSDepositMaterials;
import online.kingdomkeys.kingdomkeys.network.cts.CSSyncAllClientDataPacket;
import online.kingdomkeys.kingdomkeys.network.cts.CSTakeMaterials;
import online.kingdomkeys.kingdomkeys.synthesis.material.Material;
import online.kingdomkeys.kingdomkeys.synthesis.material.ModMaterials;
import online.kingdomkeys.kingdomkeys.util.Utils;

public class SynthesisMaterialScreen extends MenuFilterable {
		
	MenuButton deposit,back;
	Button prev,next, take;
	MenuBox boxL, boxR;
	TextFieldWidget amountBox;
	
	int page = 0;
	int itemsPerPage = 14;
		
	public SynthesisMaterialScreen() {
		super(Strings.Gui_Synthesis_Materials, new Color(0,255,0));
		drawPlayerInfo = true;
	}
	
	@Override
    public void action(ItemStack stack) {
    	super.action(stack);
		amountBox.setText(""+Math.min(64, stack.getCount()));
	}

	protected void action(String string) {
		switch(string) {
		case "prev":
			page--;
			minecraft.world.playSound(minecraft.player, minecraft.player.getPosition(), ModSounds.menu_in.get(), SoundCategory.MASTER, 1.0f, 1.0f);
			break;
		case "next":
			page++;
			minecraft.world.playSound(minecraft.player, minecraft.player.getPosition(), ModSounds.menu_in.get(), SoundCategory.MASTER, 1.0f, 1.0f);
			break;
		case "deposit":
			minecraft.world.playSound(minecraft.player, minecraft.player.getPosition(), ModSounds.menu_in.get(), SoundCategory.MASTER, 1.0f, 1.0f);
			
			ClientPlayerEntity player = minecraft.player;
			IPlayerCapabilities playerData = ModCapabilities.getPlayer(player);
			for(int i = 0; i < player.inventory.getSizeInventory();i++) {
				ItemStack stack = player.inventory.getStackInSlot(i);
				
				if(!ItemStack.areItemStacksEqual(stack, ItemStack.EMPTY)) {
					//System.out.println(stack.getItem().getRegistryName().getPath());
					if(ModMaterials.registry.getValue(new ResourceLocation(KingdomKeys.MODID,"mat_"+stack.getItem().getRegistryName().getPath())) != null) {
						Material mat = ModMaterials.registry.getValue(new ResourceLocation(KingdomKeys.MODID,"mat_"+stack.getItem().getRegistryName().getPath()));
						playerData.addMaterial(mat, stack.getCount());
						player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
					}
				}
			}
			PacketHandler.sendToServer(new CSDepositMaterials());
			PacketHandler.sendToServer(new CSSyncAllClientDataPacket());
			break;
		case "back":
			minecraft.displayGuiScreen(new SynthesisScreen());
			break;
		case "take":
			if(!ItemStack.areItemsEqual(selected, ItemStack.EMPTY) && minecraft.player.inventory.getFirstEmptyStack() > -1) {
				try { 
					Integer.parseInt(amountBox.getText());
					PacketHandler.sendToServer(new CSTakeMaterials(selected.getItem(), Integer.parseInt(amountBox.getText())));
				} catch (Exception e) {
					System.out.println("NaN "+amountBox.getText());
				}
			}
			break;
		}
		
		updateButtons();
	}

	private void updateButtons() {

	}

	
	@Override
	public void init() {
		float boxPosX = (float) width * 0.2F;
		float topBarHeight = (float) height * 0.17F;
		float boxWidth = (float) width * 0.33F;
		float middleHeight = (float) height * 0.6F;
		boxL = new MenuBox((int) boxPosX, (int) topBarHeight, (int) boxWidth, (int) middleHeight, new Color(4, 4, 68));
		boxR = new MenuBox((int) boxL.x + boxL.getWidth(), (int) topBarHeight, (int) (boxWidth), (int) middleHeight, new Color(4, 4, 68));
		
		// addButton(scrollBar = new MenuScrollBar());
		super.init();
		initItems();

		itemsPerPage = (int) (middleHeight / 14);
	}
	

	@Override
	public void initItems() {
		float buttonPosX = (float) width * 0.008F;
		int button_statsY = (int) topBarHeight + 10;
		float buttonWidth = ((float) width * 0.1744F) - 20;

		float invPosX = boxL.x;
		float invPosY = (float) height * 0.1851F;
		
		inventory.clear();
		children.clear();
		buttons.clear();
		//filterBar.buttons.forEach(this::addButton);

		List<ItemStack> items = new ArrayList<>();
		IPlayerCapabilities playerData = ModCapabilities.getPlayer(minecraft.player);

		Iterator<Entry<String, Integer>> itMaterials = playerData.getMaterialMap().entrySet().iterator();
		while(itMaterials.hasNext()) {
			Entry<String, Integer> mat = itMaterials.next();
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(mat.getKey()));
			items.add(new ItemStack(item,mat.getValue()));
		}
		items.sort(Comparator.comparing(Utils::getCategoryForStack).thenComparing(stack -> stack.getTranslationKey()));

		for (int i = 0; i < items.size(); i++) {
			inventory.add(new MenuStockItem(this, items.get(i), (int) invPosX, (int) invPosY + (i * 14), true));
		}
		
		inventory.forEach(this::addButton);

		addButton(prev = new Button((int) buttonPosX + 10, (int) (height * 0.1F), 30, 20, Utils.translateToLocal("<--"), (e) -> {
			action("prev");
		}));
		addButton(next = new Button((int) buttonPosX + 10 + 76, (int) (height * 0.1F), 30, 20, Utils.translateToLocal("-->"), (e) -> {
			action("next");
		}));
		
		prev.visible = false;
		next.visible = false;
		addButton(deposit = new MenuButton((int) buttonPosX, button_statsY + (0 * 18), (int) buttonWidth, Utils.translateToLocal(Strings.Gui_Synthesis_Materials_Deposit), ButtonType.BUTTON, (e) -> { action("deposit"); }));
		addButton(back = new MenuButton((int) buttonPosX, button_statsY + (1 * 18), (int) buttonWidth, Utils.translateToLocal(Strings.Gui_Menu_Back), ButtonType.BUTTON, (e) -> { action("back"); }));
		addButton(amountBox = new TextFieldWidget(minecraft.fontRenderer, boxR.x+50, (int) (topBarHeight + middleHeight - 20), minecraft.fontRenderer.getStringWidth("#####"), 16, "test") {
			@Override
			public boolean charTyped(char c, int i) {
				if (isNumber(c)) {
					String text = new StringBuilder(this.getText()).insert(this.getCursorPosition(), c).toString();
					if (Integer.parseInt(text) > 64) {
						return false;
					}
				} else {
					return false;
				}
				return super.charTyped(c, i);
			}
		});
		addButton(take = new Button((int) amountBox.x + amountBox.getWidth()+1, (int) (topBarHeight + middleHeight - 22), 50, 20, Utils.translateToLocal(Strings.Gui_Synthesis_Materials_Take), (e) -> { action("take"); }));
		take.visible = false;
		updateButtons();
	}

	public boolean isNumber(char c) {
		try {
			Integer.parseInt(String.valueOf(c));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		drawMenuBackground(mouseX, mouseY, partialTicks);
		boxL.draw();
		boxR.draw();
		super.render(mouseX, mouseY, partialTicks);

		prev.visible = page > 0;
		next.visible = page < inventory.size() / itemsPerPage;
		
		if(minecraft.player.inventory.getFirstEmptyStack() == -1) { //TODO somehow make this detect in singleplayer the inventory changes
			take.active = false;
			take.setMessage("No empty slot");
		}
		
		RenderSystem.pushMatrix();
		{
			RenderSystem.translated(width * 0.008F + 45, (height * 0.15) - 18, 1);
			drawString(minecraft.fontRenderer, Utils.translateToLocal("Page: " + (page + 1)), 0, 10, 0xFF9900);
		}
		RenderSystem.popMatrix();
		
		for (int i = 0; i < inventory.size(); i++) {
			inventory.get(i).active = false;
		}

		for (int i = page * itemsPerPage; i < page * itemsPerPage + itemsPerPage; i++) {
			if (i < inventory.size()) {
				if (inventory.get(i) != null) {
					inventory.get(i).visible = true;
					inventory.get(i).y = (int) (topBarHeight) + (i % itemsPerPage) * 14 + 5; // 6 = offset
					inventory.get(i).render(mouseX, mouseY, partialTicks);
					inventory.get(i).active = true;
				}
			}
		}
		
		prev.render(mouseX,  mouseY,  partialTicks);
		next.render(mouseX,  mouseY,  partialTicks);
		deposit.render(mouseX,  mouseY,  partialTicks);
		back.render(mouseX,  mouseY,  partialTicks);
	}

	@Override
	protected void renderSelectedData(int mouseX, int mouseY, float partialTicks) {
		amountBox.render(mouseX,  mouseY,  partialTicks);
		take.render(mouseX, mouseY, partialTicks);
		
		//amountBox.setWidth(minecraft.fontRenderer.getStringWidth(amountBox.getText()));
		
		take.visible = true;

		float iconPosX = boxR.x;
		float iconPosY = boxR.y + 15;
				
		RenderSystem.pushMatrix();
		{
			String name = selected.getDisplayName().getFormattedText();
			RenderSystem.translated(boxR.x + (boxR.getWidth() / 2) - minecraft.fontRenderer.getStringWidth(name)/2, boxR.y+3, 1);
			drawString(minecraft.fontRenderer, Utils.translateToLocal(name), 0, 0, 0xFF9900);
		}
		RenderSystem.popMatrix();

		RenderSystem.pushMatrix();
		{
			double offset = (boxR.getWidth()*0.2F);
			RenderSystem.translated(iconPosX + offset/2, iconPosY, 1);
			RenderSystem.scaled(boxR.getWidth() / 16 - offset / 16, boxR.getWidth()/16 - offset / 16, 1);
			itemRenderer.renderItemIntoGUI(selected, 0, 0);
		}
		RenderSystem.popMatrix();

	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}

}
