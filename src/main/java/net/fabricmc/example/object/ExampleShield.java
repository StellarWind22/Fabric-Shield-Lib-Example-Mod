package net.fabricmc.example.object;

import me.crimsondawn45.fabricshieldlib.object.FabricShield;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag.Identified;
import net.minecraft.util.Hand;

public class ExampleShield extends FabricShield
{
	public ExampleShield(Settings settings, int cooldownTicks, int durability, Identified<Item> planks)
	{
		super(settings, cooldownTicks, durability, planks);
	}
	
	@Override
	public void onBlockDamage(LivingEntity defender, DamageSource source, float amount, Hand hand, ItemStack shield)
	{
		//Set undead enemies that attack you on fire
		if(source.getAttacker() instanceof LivingEntity)
		{
			LivingEntity attacker = (LivingEntity) source.getAttacker();
			
			if(attacker.isUndead())
			{
				attacker.setOnFireFor(4);
			}
		}
	}
	
	@Override
	public void whileBlocking(LivingEntity defender, Hand hand, ItemStack shield)
	{
		//Give the holder resistance while blocking
		defender.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2, 0, true, true));
	}
	
	@Override
	public void whileHolding(LivingEntity defender, boolean blocking, Hand hand, ItemStack shield)
	{
		//If the holder is not blocking give them speed
		if(!blocking)
		{
			defender.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2, 1, true, true));
		}
	}
}
