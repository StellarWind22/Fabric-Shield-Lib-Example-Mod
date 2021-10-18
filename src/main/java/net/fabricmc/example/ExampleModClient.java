package net.fabricmc.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;

public class ExampleModClient implements ClientModInitializer {

    public static final EntityModelLayer EXAMPLE_SHIELD_MODEL_LAYER = new EntityModelLayer(new Identifier("examplemod", "example_shield"),"main");
    
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(EXAMPLE_SHIELD_MODEL_LAYER, ShieldEntityModel::getTexturedModelData);
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(new Identifier("examplemod", "entity/example_shield_base"));
            registry.register(new Identifier("examplemod", "entity/example_shield_base_nopattern"));
        });
    }
}