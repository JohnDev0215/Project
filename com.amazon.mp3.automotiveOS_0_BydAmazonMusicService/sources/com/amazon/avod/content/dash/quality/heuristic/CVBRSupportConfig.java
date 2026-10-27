package com.amazon.avod.content.dash.quality.heuristic;

/* JADX INFO: loaded from: classes2.dex */
public interface CVBRSupportConfig {
    double getBitrateVariationCost();

    double getMaxBitrateVariationPenaltyFactor();

    double getMinBitrateVariationPenaltyFactor();

    int getNumForesightFragments();
}
