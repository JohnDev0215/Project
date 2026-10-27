package com.amazon.mp3.upsellweb;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public class SettingsPageWebTargetBuilderFactory {
    private final Context context;

    public SettingsPageWebTargetBuilderFactory(final Context context) {
        this.context = context;
    }

    public SettingsPageWebTargetBuilder newBuilder() {
        return SettingsPageWebTargetBuilder.withDefaultOptions(this.context);
    }

    public SettingsPageWebTargetBuilder newPlanComparisonViewBuilder() {
        return SettingsPageWebTargetBuilder.forPlanComparisonPage(this.context);
    }
}
