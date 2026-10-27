package com.bydux.api.common.views.skin.loader;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

/* JADX INFO: loaded from: classes.dex */
public class SkinContextWrapper extends ContextWrapper {
    Context mSkinbase;

    public SkinContextWrapper(Context context) {
        super(context);
        this.mSkinbase = context;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return SkinManager.getInstance().getSkinEnable() ? SkinManager.getInstance().getResources() : super.getResources();
    }
}
