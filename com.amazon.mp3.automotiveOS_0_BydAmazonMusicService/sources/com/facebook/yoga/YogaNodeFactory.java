package com.facebook.yoga;

/* JADX INFO: loaded from: classes6.dex */
public abstract class YogaNodeFactory {
    public static YogaNode create(YogaConfig yogaConfig) {
        return new YogaNodeJNIFinalizer(yogaConfig);
    }
}
