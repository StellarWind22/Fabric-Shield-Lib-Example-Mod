package net.fabricmc.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.object.ExampleShield;
import net.fabricmc.example.object.ExampleShieldEnchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer
{
	public static final String MOD_ID = "modid";
	
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	
	@Override
	public void onInitialize()
	{
		//Register the new shield item
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "example_shield"), new ExampleShield(new Item.Settings().group(ItemGroup.COMBAT), 100, 337, ItemTags.PLANKS));
		
		//Register the new shield enchantment
		Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "example_enchantment"), new ExampleShieldEnchantment(Rarity.COMMON));
		
		LOGGER.info("\"" + MOD_ID + "\" Initialized...");
	}
}
