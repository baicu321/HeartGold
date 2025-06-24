package com.heartgold.data.singularity;

import com.google.gson.Gson;
import com.heartgold.HeartGold;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class SingularityAssetsManager implements SimpleSynchronousResourceReloadListener {
    private static final Gson GSON = new Gson();

    @Override
    public Identifier getFabricId() {
        return new Identifier(HeartGold.MOD_ID, "singularity");
    }

    @Override
    public void reload(ResourceManager manager) {
        SingularityColor.COLOR_MAP.clear();
        for (Map.Entry<Identifier, Resource> entry : manager.findResources(HeartGold.MOD_ID + "/singularity", p -> p.getPath().endsWith(".json")).entrySet()) {
            try (InputStream stream = entry.getValue().getInputStream()) {
                SingularityColor color = GSON.fromJson(new InputStreamReader(stream), SingularityColor.class);
                SingularityColor.COLOR_MAP.put(color.id(), color);
            } catch (Exception e) {
                HeartGold.LOGGER.error("Error occurred while loading resource json " + entry.getKey().toString(), e);
            }
        }
        HeartGold.LOGGER.info(SingularityColor.COLOR_MAP.size() + " singularities assets loaded.");
    }
}