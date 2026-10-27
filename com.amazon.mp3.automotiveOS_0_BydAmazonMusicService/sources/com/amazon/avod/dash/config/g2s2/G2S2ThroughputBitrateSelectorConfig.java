package com.amazon.avod.dash.config.g2s2;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.dash.quality.heuristic.ThroughputBitrateSelectorConfig;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class G2S2ThroughputBitrateSelectorConfig extends MediaConfigBase implements ThroughputBitrateSelectorConfig {
    private static final String CONFIDENCE_LEVEL_COUNT_KEY = "throughputBitrateSelector_confidenceLevelCount";
    private static final Integer DEFAULT_CONFIDENCE_LEVEL_COUNT = 8;
    private static final Integer DEFAULT_MIN_CONFIDENCE_SCORE = 9;
    private static final String MIN_CONFIDENCE_SCORE_KEY = "throughputBitrateSelector_minConfidenceScore";
    private final ConfigurationValue<Integer> mThroughputBitrateSelector_confidenceLevelCount;
    private final ConfigurationValue<Integer> mThroughputBitrateSelector_minConfidenceScore;

    public static G2S2ThroughputBitrateSelectorConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static G2S2ThroughputBitrateSelectorConfig INSTANCE = new G2S2ThroughputBitrateSelectorConfig();

        private SingletonHolder() {
        }
    }

    private G2S2ThroughputBitrateSelectorConfig() {
        this.mThroughputBitrateSelector_confidenceLevelCount = newIntConfigValue(CONFIDENCE_LEVEL_COUNT_KEY, DEFAULT_CONFIDENCE_LEVEL_COUNT.intValue());
        this.mThroughputBitrateSelector_minConfidenceScore = newIntConfigValue(MIN_CONFIDENCE_SCORE_KEY, DEFAULT_MIN_CONFIDENCE_SCORE.intValue());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.ThroughputBitrateSelectorConfig
    public int getConfidenceLevelCount() {
        return this.mThroughputBitrateSelector_confidenceLevelCount.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.ThroughputBitrateSelectorConfig
    public int getMinConfidenceScore() {
        return this.mThroughputBitrateSelector_minConfidenceScore.getValue().intValue();
    }
}
