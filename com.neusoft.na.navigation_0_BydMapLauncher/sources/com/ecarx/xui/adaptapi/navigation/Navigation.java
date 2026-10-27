package com.ecarx.xui.adaptapi.navigation;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class Navigation {
    public static INavigation create(Context context) {
        return new NavigationImpl(context);
    }
}
