package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.util.Currency;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VastPricing {
    private final Currency mCurrency;
    private final VastPricingModel mPricingModel;

    public enum VastPricingModel {
        CPM,
        CPC,
        CPE,
        CPV
    }

    public VastPricing(@Nonnull VastPricingModel vastPricingModel, @Nonnull Currency currency) {
        this.mPricingModel = (VastPricingModel) Preconditions.checkNotNull(vastPricingModel, "pricingModel");
        this.mCurrency = (Currency) Preconditions.checkNotNull(currency, "currency");
    }
}
