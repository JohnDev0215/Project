package com.android.settingslib.widget;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes6.dex */
public abstract class CandidateInfo {
    public final boolean enabled;

    public abstract String getKey();

    public abstract Drawable loadIcon();

    public abstract CharSequence loadLabel();

    public CandidateInfo(boolean enabled) {
        this.enabled = enabled;
    }
}
