package com.bydux.api.common.views.skin.loader;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

/* JADX INFO: loaded from: classes.dex */
public class SkinContextWrapper extends ContextWrapper {
    Context mSkinbase;

    public SkinContextWrapper(Context base) {
        super(base);
        this.mSkinbase = base;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (SkinManager.getInstance().getSkinEnable()) {
            return SkinManager.getInstance().getResources();
        }
        return super.getResources();
    }
}
