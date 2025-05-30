package io.github.junyali.arsenalandanvil.util;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class ItemAttributeBuilder {
    private final ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

    public static ItemAttributeBuilder weapon() {
        return new ItemAttributeBuilder();
    }

    // Generic Attributes - stuff in vanilla mc
    public ItemAttributeBuilder attackDamage(float attackDamage) {
        builder.add(Attributes.ATTACK_DAMAGE,
                new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, (double)attackDamage - 1.0, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
        );
        return this;
    }

    public ItemAttributeBuilder attackSpeed(float attackSpeed) {
        builder.add(Attributes.ATTACK_SPEED,
                new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, (double)attackSpeed, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
        );
        return this;
    }

    public ItemAttributeBuilder attackKnockback(float attackKnockback) {
        builder.add(Attributes.ATTACK_KNOCKBACK,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("knockback"), (double)attackKnockback, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
        );
        return this;
    }

    public ItemAttributeBuilder reach(float reach) {
        builder.add(Attributes.ENTITY_INTERACTION_RANGE,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("reach"), (double)reach, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder movementSpeed(float movementSpeed) {
        builder.add(Attributes.MOVEMENT_SPEED,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("movement_speed"), (double)movementSpeed, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    // Apothic Attributes - stuff for apotheosis :D
    public ItemAttributeBuilder armourPierce(float armourPierce) {
        builder.add(ALObjects.Attributes.ARMOR_PIERCE,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("armor_pierce"), (double)armourPierce, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder armourShred(float armourShred) {
        builder.add(ALObjects.Attributes.ARMOR_SHRED,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("armor_shred"), (double)armourShred, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder arrowDamage(float damage) {
        builder.add(ALObjects.Attributes.ARROW_DAMAGE,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("arrow_damage"), (double)damage, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder arrowVelocity(float velocity) {
        builder.add(ALObjects.Attributes.ARROW_VELOCITY,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("arrow_velocity"), (double)velocity, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder coldDamage(float damage) {
        builder.add(ALObjects.Attributes.COLD_DAMAGE,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("cold_damage"), (double)damage, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder critChance(float critChance) {
        builder.add(ALObjects.Attributes.CRIT_CHANCE,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("crit_chance"), (double)critChance, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder critDamage(float critDamage) {
        builder.add(ALObjects.Attributes.CRIT_DAMAGE,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("crit_damage"), (double)critDamage, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder currentHpDamage(float damage) {
        builder.add(ALObjects.Attributes.CURRENT_HP_DAMAGE,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("current_hp_damage"), (double)damage, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder dodgeChance(float chance) {
        builder.add(ALObjects.Attributes.DODGE_CHANCE,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("dodge_chance"), (double)chance, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder drawSpeed(float speed) {
        builder.add(ALObjects.Attributes.DRAW_SPEED,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("draw_speed"), (double)speed, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder experienceGained(float experience) {
        builder.add(ALObjects.Attributes.EXPERIENCE_GAINED,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("experience_gained"), (double)experience, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder fireDamage(float damage) {
        builder.add(ALObjects.Attributes.FIRE_DAMAGE,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("fire_damage"), (double)damage, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder ghostHealth(float health) {
        builder.add(ALObjects.Attributes.GHOST_HEALTH,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("ghost_health"), (double)health, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder healingReceived(float healing) {
        builder.add(ALObjects.Attributes.HEALING_RECEIVED,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("healing_received"), (double)healing, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder lifeSteal(float steal) {
        builder.add(ALObjects.Attributes.LIFE_STEAL,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("life_steal"), (double)steal, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder miningSpeed(float speed) {
        builder.add(ALObjects.Attributes.MINING_SPEED,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("mining_speed"), (double)speed, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder overheal(float overheal) {
        builder.add(ALObjects.Attributes.OVERHEAL,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("overheal"), (double)overheal, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder protPierce(float pierce) {
        builder.add(ALObjects.Attributes.PROT_PIERCE,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("prot_pierce"), (double)pierce, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeBuilder protShred(float shred) {
        builder.add(ALObjects.Attributes.PROT_SHRED,
                new AttributeModifier(ResourceLocation.withDefaultNamespace("prot_shred"), (double)shred, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND);
        return this;
    }

    public ItemAttributeModifiers build() {
        return builder.build();
    }
}
