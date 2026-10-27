package com.amazon.avod.dash.config.g2s2;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.dash.quality.heuristic.CVBRSupportConfig;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class G2S2CVBRSupportConfig extends MediaConfigBase implements CVBRSupportConfig {
    private static final String BITRATE_VARIATION_COST_KEY = "cvbrSupport_bitrateVariationCost";
    private static final String MAX_BITRATE_VARIATION_PENALTY_FACTOR_KEY = "cvbrSupport_maxBitrateVariationPenaltyFactor";
    private static final String MIN_BITRATE_VARIATION_PENALTY_FACTOR_KEY = "cvbrSupport_minBitrateVariationPenaltyFactor";
    private static final String NUM_FORSIGHT_FRAGMENTS_KEY = "cvbrSupport_numForesightFragments";
    private final Float DEFAULT_MAX_BITRATE_VARIATION_PENALTY_FACTOR;
    private final ConfigurationValue<Float> mCvbrSupport_bitrateVariationCost;
    private final ConfigurationValue<Float> mCvbrSupport_maxBitrateVariationPenaltyFactor;
    private final ConfigurationValue<Float> mCvbrSupport_minBitrateVariationPenaltyFactor;
    private final ConfigurationValue<Integer> mCvbrSupport_numForesightFragments;
    private static final Integer DEFAULT_NUM_FORSIGHT_FRAGMENTS = 6;
    private static final Float DEFAULT_BITRATE_VARIATION_COST = Float.valueOf(1.0f);
    private static final Float DEFAULT_MIN_BITRATE_VARIATION_PENALTY_FACTOR = Float.valueOf(-0.2f);

    private static final class SingletonHolder {
        private static final G2S2CVBRSupportConfig INSTANCE = new G2S2CVBRSupportConfig();

        private SingletonHolder() {
        }
    }

    private G2S2CVBRSupportConfig() {
        Float fValueOf = Float.valueOf(0.0f);
        this.DEFAULT_MAX_BITRATE_VARIATION_PENALTY_FACTOR = fValueOf;
        this.mCvbrSupport_numForesightFragments = newIntConfigValue(NUM_FORSIGHT_FRAGMENTS_KEY, DEFAULT_NUM_FORSIGHT_FRAGMENTS.intValue());
        this.mCvbrSupport_bitrateVariationCost = newFloatConfigValue(BITRATE_VARIATION_COST_KEY, DEFAULT_BITRATE_VARIATION_COST.floatValue());
        this.mCvbrSupport_minBitrateVariationPenaltyFactor = newFloatConfigValue(MIN_BITRATE_VARIATION_PENALTY_FACTOR_KEY, DEFAULT_MIN_BITRATE_VARIATION_PENALTY_FACTOR.floatValue());
        this.mCvbrSupport_maxBitrateVariationPenaltyFactor = newFloatConfigValue(MAX_BITRATE_VARIATION_PENALTY_FACTOR_KEY, fValueOf.floatValue());
    }

    public static G2S2CVBRSupportConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.CVBRSupportConfig
    public int getNumForesightFragments() {
        return this.mCvbrSupport_numForesightFragments.getValue().intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.CVBRSupportConfig
    public double getBitrateVariationCost() {
        return this.mCvbrSupport_bitrateVariationCost.getValue().floatValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.CVBRSupportConfig
    public double getMinBitrateVariationPenaltyFactor() {
        return this.mCvbrSupport_minBitrateVariationPenaltyFactor.getValue().floatValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.CVBRSupportConfig
    public double getMaxBitrateVariationPenaltyFactor() {
        return this.mCvbrSupport_maxBitrateVariationPenaltyFactor.getValue().floatValue();
    }
}
