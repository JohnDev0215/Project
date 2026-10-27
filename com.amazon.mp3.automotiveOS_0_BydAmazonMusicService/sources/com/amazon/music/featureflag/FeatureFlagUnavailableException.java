package com.amazon.music.featureflag;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class FeatureFlagUnavailableException extends Exception {
    public FeatureFlagUnavailableException() {
        super("Something went wrong. Can not return value from FeatureFlagProvider.");
    }
}
