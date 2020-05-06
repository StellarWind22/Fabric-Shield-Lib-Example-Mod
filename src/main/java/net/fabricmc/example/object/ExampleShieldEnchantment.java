package net.fabricmc.example.object;

import java.util.List;

import me.crimsondawn45.fabricshieldlib.object.FabricShieldEnchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.dimension.DimensionType;

public class ExampleShieldEnchantment extends FabricShieldEnchantment
{
	public ExampleShieldEnchantment(Rarity weight, EnchantmentTarget type, List<Item> acceptedItems)
	{
		super(weight, type, acceptedItems);
	}
	
	@Override
	public void onBlockDamage(LivingEntity defender, DamageSource source, Hand hand, ItemStack shield, float amount, int level)
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
	public void whileBlockingTick(LivingEntity defender, Hand hand, ItemStack shield, int enchantmentLevel)
	{
		//While blocking give the holder of the shield fire resistance
		defender.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 2, 0, true, true));
	}
	
	@Override
	public void whileHoldingTick(LivingEntity defender, boolean isBlocking, Hand hand, ItemStack shield, int level)
	{
		//Anything holding a shield with tis enchantment in the nether will catch on fire
		if(defender.world.getDimension().getType() == DimensionType.THE_NETHER)
		{
			defender.setFireTicks(20);
		}
	}
	
	@Override
	public int getMaximumLevel()
	{
		return 3;
	}
}
