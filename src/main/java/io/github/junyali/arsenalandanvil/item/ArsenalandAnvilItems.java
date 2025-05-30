package io.github.junyali.arsenalandanvil.item;

import io.github.junyali.arsenalandanvil.ArsenalandAnvil;
import io.github.junyali.arsenalandanvil.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ArsenalandAnvilItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ArsenalandAnvil.MODID);

    public static final Tier[] TIERS = {
            Tiers.WOOD, Tiers.STONE, Tiers.IRON, Tiers.GOLD, Tiers.DIAMOND, Tiers.NETHERITE
    };

    public static final String[] TIER_NAMES = {
            "wooden", "stone", "iron", "golden", "diamond", "netherite"
    };

    public static final DeferredItem<Item> HARDENED_HANDLE = ITEMS.register("hardened_handle",
            () -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));

    static {
        // Weapons
        registerToolSet("dagger", DaggerItem::new);
        registerToolSet("rapier", RapierItem::new);
        registerToolSet("katana", KatanaItem::new);
        registerToolSet("scimitar", ScimitarItem::new);
        registerToolSet("longsword", LongswordItem::new);
        registerToolSet("claymore", ClaymoreItem::new);
        registerToolSet("kanabo", KanaboItem::new);
        registerToolSet("battleaxe", BattleaxeItem::new);
        registerToolSet("spear", SpearItem::new);
        registerToolSet("pike", PikeItem::new);
        registerToolSet("halberd", HalberdItem::new);
        registerToolSet("glaive", GlaiveItem::new);

        // Tools
        registerToolSet("hammer", HammerItem::new);
        registerToolSet("sickle", SickleItem::new);
        registerToolSet("paxel", PaxelItem::new);
    }

    // really messy way of making a tool set for all tiers wooden -> netherite
    private static void registerToolSet(String toolType, ToolFactory factory) {
        for (int i = 0; i < TIERS.length; i++) {
            Tier tier = TIERS[i];
            String tierName = TIER_NAMES[i];
            String itemName = tierName + "_" + toolType;

            final Item.Properties properties = tier == Tiers.NETHERITE
                    ? new Item.Properties().fireResistant()
                    : new Item.Properties();

            ITEMS.register(itemName, () -> factory.create(tier, properties));
        }
    }

    @FunctionalInterface
    private interface ToolFactory {
        Item create(Tier tier, Item.Properties properties);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
