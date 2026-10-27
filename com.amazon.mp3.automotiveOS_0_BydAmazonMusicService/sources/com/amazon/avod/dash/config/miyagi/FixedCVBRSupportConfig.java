package com.amazon.avod.dash.config.miyagi;

import com.amazon.avod.content.dash.quality.heuristic.CVBRSupportConfig;
import java.util.Map;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
class FixedCVBRSupportConfig extends HeuristicsConfigBase implements CVBRSupportConfig {
    private final double mBitrateVariationCost;
    private final double mMaxBitrateVariationPenaltyFactor;
    private final double mMinBitrateVariationPenaltyFactor;
    private final int mNumForesightFragments;

    FixedCVBRSupportConfig(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, CVBRSupportConfig cVBRSupportConfig) {
        super(map, map2, map3);
        this.mNumForesightFragments = getIntegerConfigValue("timeToDownloadPredictor_bitrateVariationWindow", cVBRSupportConfig.getNumForesightFragments());
        this.mBitrateVariationCost = getDoubleConfigValue("timeToDownloadPredictor_bitrateVariationFactor", cVBRSupportConfig.getBitrateVariationCost());
        this.mMinBitrateVariationPenaltyFactor = getDoubleConfigValue("timeToDownloadPredictor_minBitrateVariationPenaltyFactor", cVBRSupportConfig.getMinBitrateVariationPenaltyFactor());
        this.mMaxBitrateVariationPenaltyFactor = getDoubleConfigValue("timeToDownloadPredictor_maxBitrateVariationPenaltyFactor", cVBRSupportConfig.getMaxBitrateVariationPenaltyFactor());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.CVBRSupportConfig
    public int getNumForesightFragments() {
        return this.mNumForesightFragments;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.CVBRSupportConfig
    public double getBitrateVariationCost() {
        return this.mBitrateVariationCost;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.CVBRSupportConfig
    public double getMinBitrateVariationPenaltyFactor() {
        return this.mMinBitrateVariationPenaltyFactor;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.CVBRSupportConfig
    public double getMaxBitrateVariationPenaltyFactor() {
        return this.mMaxBitrateVariationPenaltyFactor;
    }
}
