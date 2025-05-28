package io.github.junyali.arsenalandanvil;

import io.github.junyali.arsenalandanvil.item.ArsenalandAnvilCreativeModTabs;
import io.github.junyali.arsenalandanvil.item.ArsenalandAnvilItems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(ArsenalandAnvil.MODID)
public class ArsenalandAnvil
{
    public static final String MODID = "arsenalandanvil";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ArsenalandAnvil(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        ArsenalandAnvilCreativeModTabs.register(modEventBus);
        ArsenalandAnvilItems.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // common setup
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // server start
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // client setup
        }
    }
}
