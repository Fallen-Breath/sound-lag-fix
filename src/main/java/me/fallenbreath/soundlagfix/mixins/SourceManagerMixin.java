package me.fallenbreath.soundlagfix.mixins;

import net.minecraft.client.sound.Channel;
import net.minecraft.client.sound.Source;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Channel.SourceManager.class)
public class SourceManagerMixin
{
	@Shadow private boolean stopped;

	@Shadow private Source source;

	/**
	 * @author Fallen_Breath
	 * @reason this.source might be null all the time if Channel#createSource fails
	 * In that case just mark the source manager as stopped to let the sound system to remove it
	 */
	@Inject(method = "isStopped", at = @At("HEAD"))
	private void nullSourceIsStoppedStateToo(CallbackInfoReturnable<Boolean> cir)
	{
		if (this.source == null)
		{
			this.stopped = true;
		}
	}
}
