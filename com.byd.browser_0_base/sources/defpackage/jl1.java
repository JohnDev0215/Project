package defpackage;

import android.content.Context;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.SubMenuBuilder;
import com.google.android.material.internal.NavigationMenu;

/* JADX INFO: compiled from: NavigationSubMenu.java */
/* JADX INFO: loaded from: classes.dex */
public final class jl1 extends SubMenuBuilder {
    public jl1(Context context, NavigationMenu navigationMenu, MenuItemImpl menuItemImpl) {
        super(context, navigationMenu, menuItemImpl);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public final void p(boolean z) {
        super.p(z);
        this.z.p(z);
    }
}
