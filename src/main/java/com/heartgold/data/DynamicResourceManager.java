package com.heartgold.data;

import com.google.gson.Gson;
import com.heartgold.HeartGold;
import com.heartgold.data.singularity.SingularityResourceManager;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class DynamicResourceManager implements SimpleSynchronousResourceReloadListener {
    private static final Gson GSON = new Gson();

    @Override
    public Identifier getFabricId() {
        return new Identifier(HeartGold.MOD_ID, "dynamic");
    }

    @Override
    public void reload(ResourceManager manager) {
        SingularityResourceManager.reload(manager);
        for (Map.Entry<Identifier, Resource> entry : manager.findResources(HeartGold.MOD_ID + "/dynamic", p -> p.getPath().endsWith(".json")).entrySet()) {
            try (InputStream stream = entry.getValue().getInputStream()) {
                DynamicManager.DynamicData data = GSON.fromJson(new InputStreamReader(stream), DynamicManager.DynamicData.class);
                DynamicManager.process(data);
            } catch (Exception e) {
                HeartGold.LOGGER.error("Error occurred while loading resource json " + entry.getKey().toString(), e);
            }
        }
    }
}