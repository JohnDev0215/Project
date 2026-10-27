package com.amazon.avod.dash.config.miyagi;

import com.amazon.avod.content.dash.quality.heuristic.ThroughputBitrateSelectorConfig;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
class FixedThroughputBitrateSelectorConfig extends HeuristicsConfigBase implements ThroughputBitrateSelectorConfig {
    private final int mConfidenceLevelCount;
    private final int mMinConfidenceScore;

    FixedThroughputBitrateSelectorConfig(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, ThroughputBitrateSelectorConfig throughputBitrateSelectorConfig) {
        super(map, map2, map3);
        this.mConfidenceLevelCount = getIntegerConfigValue("scheduler_confidenceLevelsToCompute", throughputBitrateSelectorConfig.getConfidenceLevelCount());
        this.mMinConfidenceScore = getIntegerConfigValue("scheduler_minConfidenceLevelSum", throughputBitrateSelectorConfig.getMinConfidenceScore());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.ThroughputBitrateSelectorConfig
    public int getConfidenceLevelCount() {
        return this.mConfidenceLevelCount;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.ThroughputBitrateSelectorConfig
    public int getMinConfidenceScore() {
        return this.mMinConfidenceScore;
    }
}
