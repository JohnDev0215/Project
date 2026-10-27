package com.google.android.material.internal;

import android.content.Context;
import android.view.SubMenu;

/* JADX INFO: compiled from: NavigationMenu.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends androidx.appcompat.view.menu.g {
    public e(Context context) {
        super(context);
    }

    @Override // androidx.appcompat.view.menu.g, android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        androidx.appcompat.view.menu.i iVar = (androidx.appcompat.view.menu.i) a(i2, i3, i4, charSequence);
        g gVar = new g(w(), this, iVar);
        iVar.w(gVar);
        return gVar;
    }
}
