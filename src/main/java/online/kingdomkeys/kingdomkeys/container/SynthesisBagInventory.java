package online.kingdomkeys.kingdomkeys.container;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import online.kingdomkeys.kingdomkeys.item.SynthesisItem;

public class SynthesisBagInventory implements ICapabilityProvider, INBTSerializable<CompoundTag> {

	private final IItemHandler inv = new ItemStackHandler(54) {
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
			return !stack.isEmpty() && stack.getItem() instanceof SynthesisItem;
		}
	};
	
	private final LazyOptional<IItemHandler> opt = LazyOptional.of(() -> inv);

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
		return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(capability, opt);
	}

	@Override
	public CompoundTag serializeNBT() {
		return ((ItemStackHandler)inv).serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		((ItemStackHandler)inv).deserializeNBT(nbt);
	}
	
}
