package net.fabricmc.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.crimsondawn45.fabricshieldlib.lib.object.FabricShield;
import me.crimsondawn45.fabricshieldlib.lib.object.ShieldEnchantment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.object.ExampleEvent;
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
		/**
		 * Add example shield item to game
		 */
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "example_shield"), new FabricShield(new Item.Settings().group(ItemGroup.COMBAT), 100, 337, ItemTags.PLANKS));
		
		/**
		 * Add example shield enchantment to game using a ShieldEvent to add effects
		 */
		Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "example_enchantment"), new ShieldEnchantment(Rarity.COMMON, new ExampleEvent(true, true, true)));
		
		LOGGER.info("\"" + MOD_ID + "\" Initialized...");
	}
}
