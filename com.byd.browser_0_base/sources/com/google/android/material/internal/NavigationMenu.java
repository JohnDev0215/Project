package com.google.android.material.internal;

import android.content.Context;
import android.view.SubMenu;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import defpackage.jl1;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenu extends MenuBuilder {
    public NavigationMenu(Context context) {
        super(context);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder, android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        MenuItemImpl menuItemImplA = a(i, i2, i3, charSequence);
        jl1 jl1Var = new jl1(this.a, this, menuItemImplA);
        menuItemImplA.o = jl1Var;
        jl1Var.setHeaderTitle(menuItemImplA.e);
        return jl1Var;
    }
}
