package com.heartgold.registry;

import com.heartgold.data.DynamicResourceManager;
import com.heartgold.data.singularity.SingularityAssetsManager;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class HeartResourceManagers {

        public static void register(){
            ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new DynamicResourceManager());
        }
        public static void registerClient(){
            ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SingularityAssetsManager());
        }
}
