package net.minecraft.item;

import com.google.common.collect.Multimap;
import com.google.common.collect.HashMultimap;
import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemKenshiDagger extends ItemSword {

    public ItemKenshiDagger() {
        super(Item.ToolMaterial.WOOD);
        this.setMaxDamage(0);
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
        Multimap<String, AttributeModifier> original = super.getItemAttributeModifiers();
        Multimap<String, AttributeModifier> result = HashMultimap.create(original);

        String key = SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName();

        Collection<AttributeModifier> oldModifiers =
            new ArrayList<AttributeModifier>(result.get(key));

        for (AttributeModifier modifier : oldModifiers) {
            result.remove(key, modifier);
        }

        result.put(
            key,
            new AttributeModifier(itemModifierUUID, "Weapon modifier", 2.0D, 0)
        );

        return result;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, BlockPos pos,
            EntityLivingBase entity) {
        return true;
    }
}
