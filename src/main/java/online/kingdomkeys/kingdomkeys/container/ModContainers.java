package online.kingdomkeys.kingdomkeys.container;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import online.kingdomkeys.kingdomkeys.KingdomKeys;
import online.kingdomkeys.kingdomkeys.client.gui.container.GummiEditorScreen;
import online.kingdomkeys.kingdomkeys.client.gui.container.MagicalChestScreen;
import online.kingdomkeys.kingdomkeys.client.gui.container.PedestalScreen;
import online.kingdomkeys.kingdomkeys.client.gui.container.SynthesisBagScreen;

//NOTE: they call containers menus in mojang mappings

public class ModContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, KingdomKeys.MODID);

    public static final RegistryObject<MenuType<?>>
        SYNTHESIS_BAG = createContainer("synthesis_bag", SynthesisBagContainer::fromNetwork),
        PEDESTAL = createContainer("pedestal_container", PedestalContainer::new),
        MAGICAL_CHEST = createContainer("magical_chest", MagicalChestContainer::new),
        GUMMI_EDITOR = createContainer("gummi_editor_container", GummiEditorContainer::new)
    ;

    public static <M extends AbstractContainerMenu> RegistryObject<MenuType<?>> createContainer(String name, IContainerFactory<M> container) {
        MenuType<M> newContainer = IForgeMenuType.create(container);
        RegistryObject<MenuType<?>> result = CONTAINERS.register(name, newContainer.delegate);
        return result;
    }

    @OnlyIn(Dist.CLIENT)
    public static <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void registerGUIFactory(MenuType<M> container, MenuScreens.ScreenConstructor<M, U> guiFactory) {
        MenuScreens.register(container, guiFactory);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerGUIFactories() {
        registerGUIFactory((MenuType<SynthesisBagContainer>) (ModContainers.SYNTHESIS_BAG.get()), SynthesisBagScreen::new);
        registerGUIFactory((MenuType<PedestalContainer>) (ModContainers.PEDESTAL.get()), PedestalScreen::new);
        registerGUIFactory((MenuType<MagicalChestContainer>) (ModContainers.MAGICAL_CHEST.get()), MagicalChestScreen::new);
        registerGUIFactory((MenuType<GummiEditorContainer>) (ModContainers.GUMMI_EDITOR.get()), GummiEditorScreen::new);
    }

}
