package net.fabricmc.example.object;

import me.crimsondawn45.fabricshieldlib.lib.event.ShieldEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class ExampleEvent extends ShieldEvent {

	public ExampleEvent(boolean usesOnBlockDamage, boolean usesOnDisable, boolean usesWhileHolding) {
		super(usesOnBlockDamage, usesOnDisable, usesWhileHolding);
	}
	
	@Override
	public void onBlockDamage(LivingEntity defender, DamageSource source, float amount, int level, Hand hand, ItemStack shield){
		
		//When shield blocks damage reflect 50% of the damage back to the attacker
		Entity attacker = source.getAttacker();
		
		if(attacker instanceof LivingEntity) {
			LivingEntity attackerLiving = (LivingEntity) attacker;
			attackerLiving.damage(DamageSource.mob(defender), amount / 2);
		}
	}
	
	@Override
	public void onDisable(PlayerEntity defender, int level, Hand hand, ItemStack shield){
		
		//When shield is disabled set user on fire for 3 seconds
		defender.setOnFireFor(3);
	}
	
	@Override
	public void whileHolding(LivingEntity defender, int level, Hand hand, ItemStack shield){
		
		//Every tick user is holding shield give them speed effect
		defender.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 0, false, false));
	}
}
