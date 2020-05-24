package net.fabricmc.example.object;

import me.crimsondawn45.fabricshieldlib.object.FabricShieldEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class ExampleShieldEnchantment extends FabricShieldEnchantment
{
	public ExampleShieldEnchantment(Rarity weight)
	{
		super(weight);
	}
	
	@Override
	public void onBlockDamage(LivingEntity defender, DamageSource source, float amount, int level, Hand hand, ItemStack shield)
	{
		//Reflects 25% per level of the damage blocked back to the entity that dealt the damage
		Entity attacker = source.getAttacker();
		
		if(defender instanceof PlayerEntity)
		{
			attacker.damage(DamageSource.player((PlayerEntity) defender), (int)Math.round(amount * (0.25F * level)));
		}
		else
		{
			attacker.damage(DamageSource.mob(defender), (int)Math.round(amount * (0.25F * level)));
		}
	}
	
	@Override
	public void whileBlocking(LivingEntity defender, int enchantmentLevel, Hand hand, ItemStack shield)
	{
		//While blocking give the holder of the shield fire resistance
		defender.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 2, 0, true, true));
	}
	
	@Override
	public void whileHolding(LivingEntity defender, boolean isBlocking, int level, Hand hand, ItemStack shield)
	{
		//While the holder is holding and not blocking make them glow.
		defender.setGlowing(!isBlocking);
	}
	
	@Override
	public int getMaximumLevel()
	{
		return 3;
	}
}
