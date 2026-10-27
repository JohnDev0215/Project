package com.byd.music.style;

import android.content.Context;
import android.content.res.Resources;
import android.view.ContextThemeWrapper;

/* JADX INFO: loaded from: classes5.dex */
public class ContextStyleWrapper extends ContextThemeWrapper {
    public ContextStyleWrapper(Context context, int i) {
        super(context, i);
    }

    public ContextStyleWrapper(Context context, Resources.Theme theme) {
        super(context, theme);
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return super.getResources();
    }
}
