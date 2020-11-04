package me.fallenbreath.soundlagfix.mixins;

import net.minecraft.client.sound.Channel;
import net.minecraft.client.sound.SoundEngine;
import net.minecraft.client.sound.Source;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Channel.class)
public class ChannelMixin
{
	/**
	 * @author Fallen_Breath
	 * @reason If Channel#createSource fails, mark the source manager as stopped to let the sound system to remove it
	 */
	@SuppressWarnings("UnresolvedMixinReference")
	@Inject(
			// lambda method in Channel.createSource(RunMode mode)
			method = "method_19724",
			at = @At("TAIL"),
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	void stopSourceManagerIfSourceIsNull(SoundEngine.RunMode runMode, Channel.SourceManager sourceManager, CallbackInfo ci, Source source)
	{
		if (source == null)
		{
			((SourceManagerAccessor)sourceManager).setStopped(true);
		}
	}
}
