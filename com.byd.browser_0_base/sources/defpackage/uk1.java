package defpackage;

import android.content.Context;
import android.view.SubMenu;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;

/* JADX INFO: compiled from: NavigationBarMenu.java */
/* JADX INFO: loaded from: classes.dex */
public final class uk1 extends MenuBuilder {
    public final int A;
    public final Class<?> z;

    public uk1(Context context, Class<?> cls, int i) {
        super(context);
        this.z = cls;
        this.A = i;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public final MenuItemImpl a(int i, int i2, int i3, CharSequence charSequence) {
        int size = size() + 1;
        int i4 = this.A;
        if (size <= i4) {
            y();
            MenuItemImpl menuItemImplA = super.a(i, i2, i3, charSequence);
            menuItemImplA.f(true);
            x();
            return menuItemImplA;
        }
        String simpleName = this.z.getSimpleName();
        StringBuilder sb = new StringBuilder("Maximum number of items supported by ");
        sb.append(simpleName);
        sb.append(" is ");
        sb.append(i4);
        sb.append(". Limit can be checked with ");
        throw new IllegalArgumentException(gq2.i(sb, simpleName, "#getMaxItemCount()"));
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder, android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        throw new UnsupportedOperationException(this.z.getSimpleName().concat(" does not support submenus"));
    }
}
