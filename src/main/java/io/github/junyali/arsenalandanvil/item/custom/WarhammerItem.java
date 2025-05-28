package io.github.junyali.arsenalandanvil.item.custom;

import io.github.junyali.arsenalandanvil.util.ItemAttributeBuilder;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class WarhammerItem extends SwordItem {
    public WarhammerItem(Tier tier, Properties properties) {
        super(tier, properties.attributes(createAttributes(tier)));
    }

    private static ItemAttributeModifiers createAttributes(Tier tier) {
        return ItemAttributeBuilder.weapon()
                .attackDamage(tier.getAttackDamageBonus() + 1.0f + 2.5f)
                .attackSpeed(3.2f)
                .reach(-0.3f)
                .attackKnockback(1.0f)
                .armourPierce(0.1f)
                .build();
    }
}
