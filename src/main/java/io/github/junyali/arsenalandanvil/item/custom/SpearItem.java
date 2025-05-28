package io.github.junyali.arsenalandanvil.item.custom;

import io.github.junyali.arsenalandanvil.util.ItemAttributeBuilder;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class SpearItem extends SwordItem {
    public SpearItem(Tier tier, Properties properties) {
        super(tier, properties.attributes(createAttributes(tier)));
    }

    private static ItemAttributeModifiers createAttributes(Tier tier) {
        return ItemAttributeBuilder.weapon()
                .attackDamage(tier.getAttackDamageBonus() + 1.0f)
                .attackSpeed(2.8f)
                .reach(1.5f)
                .armourPierce(0.1f)
                .build();
    }
}
