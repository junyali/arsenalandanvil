package io.github.junyali.arsenalandanvil.item.custom;

import io.github.junyali.arsenalandanvil.util.ItemAttributeBuilder;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class KatanaItem extends SwordItem {
    public KatanaItem(Tier tier, Properties properties) {
        super(tier, properties.attributes(createAttributes(tier)));
    }

    private static ItemAttributeModifiers createAttributes(Tier tier) {
        return ItemAttributeBuilder.weapon()
                .attackDamage(tier.getAttackDamageBonus() + 1.0f)
                .attackSpeed(-2.6f)
                .reach(0.2f)
                .critChance(0.15f)
                .critDamage(0.45f)
                .armourPierce(0.1f)
                .build();
    }
}
