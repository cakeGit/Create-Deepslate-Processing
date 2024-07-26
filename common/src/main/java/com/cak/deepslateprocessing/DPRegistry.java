package com.cak.deepslateprocessing;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import io.github.fabricators_of_create.porting_lib.tags.Tags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class DPRegistry {
    
    public static CreateRegistrate REGISTRATE = CreateRegistrate.create(Constants.MOD_ID)
        .setCreativeTab(DPTabs.DEEPSLATE_PROCESSING.key());
    
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
