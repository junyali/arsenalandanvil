package io.github.junyali.arsenalandanvil.item;

import io.github.junyali.arsenalandanvil.ArsenalandAnvil;
import io.github.junyali.arsenalandanvil.item.custom.DaggerItem;
import io.github.junyali.arsenalandanvil.item.custom.KatanaItem;
import io.github.junyali.arsenalandanvil.item.custom.RapierItem;
import io.github.junyali.arsenalandanvil.item.custom.ScimitarItem;
import io.github.junyali.arsenalandanvil.item.custom.LongswordItem;
import io.github.junyali.arsenalandanvil.item.custom.ClaymoreItem;
import io.github.junyali.arsenalandanvil.item.custom.WarhammerItem;
import io.github.junyali.arsenalandanvil.item.custom.BattleaxeItem;
import io.github.junyali.arsenalandanvil.item.custom.SpearItem;
import io.github.junyali.arsenalandanvil.item.custom.PikeItem;
import io.github.junyali.arsenalandanvil.item.custom.HalberdItem;
import io.github.junyali.arsenalandanvil.item.custom.GlaiveItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ArsenalandAnvilItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ArsenalandAnvil.MODID);

    public static final Tier[] TIERS = {
            Tiers.WOOD, Tiers.STONE, Tiers.IRON, Tiers.GOLD, Tiers.DIAMOND, Tiers.NETHERITE
    };

    public static final String[] TIER_NAMES = {
            "wooden", "stone", "iron", "golden", "diamond", "netherite"
    };

    static {
        // registerWeaponSet("weapon_name", WeaponClass::new);
        registerWeaponSet("dagger", DaggerItem::new);
        registerWeaponSet("rapier", RapierItem::new);
        registerWeaponSet("katana", KatanaItem::new);
        registerWeaponSet("scimitar", ScimitarItem::new);
        registerWeaponSet("longsword", LongswordItem::new);
        registerWeaponSet("claymore", ClaymoreItem::new);
        registerWeaponSet("war_hammer", WarhammerItem::new);
        registerWeaponSet("battle_axe", BattleaxeItem::new);
        registerWeaponSet("spear", SpearItem::new);
        registerWeaponSet("pike", PikeItem::new);
        registerWeaponSet("halberd", HalberdItem::new);
        registerWeaponSet("glaive", GlaiveItem::new);

    }

    // really messy way of making a weapon set for all tiers wooden -> netherite
    private static void registerWeaponSet(String weaponType, WeaponFactory factory) {
        for (int i = 0; i < TIERS.length; i++) {
            Tier tier = TIERS[i];
            String tierName = TIER_NAMES[i];
            String itemName = tierName + "_" + weaponType;

            ITEMS.register(itemName, () -> factory.create(tier, new Item.Properties()));
        }
    }

    @FunctionalInterface
    private interface WeaponFactory {
        Item create(Tier tier, Item.Properties properties);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
