package io.github.junyali.arsenalandanvil.item.custom;

import io.github.junyali.arsenalandanvil.util.ItemAttributeBuilder;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class RapierItem extends SwordItem {
    public RapierItem(Tier tier, Properties properties) {
        super(tier, properties.attributes(createAttributes(tier)));
    }

    private static ItemAttributeModifiers createAttributes(Tier tier) {
        return ItemAttributeBuilder.weapon()
                .attackDamage(tier.getAttackDamageBonus() + 1.0f - 1.0f)
                .attackSpeed(2.8f)
                .reach(0.8f)
                .critChance(0.15f)
                .critDamage(0.4f)
                .armourPierce(0.15f)
                .build();
    }
}
