package me.fallenbreath.soundlagfix.mixins;

import net.minecraft.client.sound.Channel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Channel.SourceManager.class)
public interface SourceManagerAccessor
{
	@Accessor("stopped")
	void setStopped(boolean value);
}
