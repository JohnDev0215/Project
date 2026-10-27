package com.vivid.spotify.common.base;

import android.content.res.Resources;

/* JADX INFO: compiled from: BaseActivity.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends j {
    a4.a A;

    @Override // androidx.appcompat.app.c, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = super.getResources();
        a4.a aVar = this.A;
        if (aVar != null && aVar.h()) {
            resources.getDisplayMetrics().density = this.A.a();
            resources.getDisplayMetrics().densityDpi = this.A.b();
            resources.getDisplayMetrics().scaledDensity = this.A.c();
        }
        return resources;
    }
}
