package com.infinityraider.agricraft.impl.v1.requirement;

import com.infinityraider.agricraft.api.v1.requirement.RequirementType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.function.IntPredicate;
import java.util.function.ToIntBiFunction;

public class GrowConditionSingleInt extends GrowConditionAbstract {
    private final BlockPos offset;
    private final ToIntBiFunction<World, BlockPos> fetcher;
    private final IntPredicate predicate;

    public GrowConditionSingleInt(int strength, RequirementType type, BlockPos offset, ToIntBiFunction<World, BlockPos> fetcher, IntPredicate predicate) {
        super(strength, type, offset);
        this.offset = offset;
        this.fetcher = fetcher;
        this.predicate = predicate;
    }

    @Override
    public boolean isMet(@Nonnull World world, @Nonnull BlockPos pos) {
        return this.predicate.test(this.fetcher.applyAsInt(world, pos.add(this.offset)));
    }
}
