package com.bydux.api.common.views.skin.loader;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

/* JADX INFO: loaded from: classes2.dex */
public class SkinContextWrapper extends ContextWrapper {
    private static SkinContextWrapper mSkinContextWrapper;
    Context mSkinbase;

    public SkinContextWrapper(Context base) {
        super(base);
        this.mSkinbase = base;
    }

    public static Context getSkinContextWrapper(Context context) {
        if (!SkinManager.getInstance().getSkinEnable()) {
            return context;
        }
        if (mSkinContextWrapper == null) {
            mSkinContextWrapper = new SkinContextWrapper(context);
        }
        return mSkinContextWrapper;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (SkinManager.getInstance().getSkinEnable()) {
            return SkinManager.getInstance().getResources();
        }
        return super.getResources();
    }
}
