package com.amazon.music.featuregating.configuration;

import kotlin.Metadata;

/* JADX INFO: compiled from: ArcusManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/featuregating/configuration/NoSavedConfigurationFound;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NoSavedConfigurationFound extends Exception {
    public NoSavedConfigurationFound() {
        super("No Saved Configuration Found");
    }
}
