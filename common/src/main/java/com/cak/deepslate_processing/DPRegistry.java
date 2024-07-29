package com.cak.deepslate_processing;

import com.mojang.datafixers.optics.profunctors.Mapping;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import static com.cak.deepslate_processing.DeepslateProcessingCommon.REGISTRATE;

public class DPRegistry {
    
    public static BlockEntry<GravelBlock> DEEP_GRAVEL = REGISTRATE.block("deep_gravel", GravelBlock::new)
        .defaultBlockstate()
        .properties(p -> p.mapColor(MapColor.STONE).instrument(NoteBlockInstrument.SNARE).strength(0.6f).sound(SoundType.GRAVEL))
        .tag(BlockTags.MINEABLE_WITH_SHOVEL)
        .simpleItem()
        .register();
    
    public static ItemEntry<Item> DEEPSLATE_SHARD = REGISTRATE.item("deepslate_shard", Item::new)
        .register();
    
    public static void register() {
    }
    
}
