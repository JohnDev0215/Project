package p;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class qg2 extends si1 implements SubMenu {
    public final xi1 A;
    public final si1 z;

    public qg2(Context context, si1 si1Var, xi1 xi1Var) {
        super(context);
        this.z = si1Var;
        this.A = xi1Var;
    }

    @Override // p.si1
    public final boolean d(xi1 xi1Var) {
        return this.z.d(xi1Var);
    }

    @Override // p.si1
    public final boolean e(si1 si1Var, MenuItem menuItem) {
        return super.e(si1Var, menuItem) || this.z.e(si1Var, menuItem);
    }

    @Override // p.si1
    public final boolean f(xi1 xi1Var) {
        return this.z.f(xi1Var);
    }

    @Override // android.view.SubMenu
    public final MenuItem getItem() {
        return this.A;
    }

    @Override // p.si1
    public final String j() {
        xi1 xi1Var = this.A;
        int i = xi1Var != null ? xi1Var.a : 0;
        if (i == 0) {
            return null;
        }
        return u4.e("android:menu:actionviewstates:", i);
    }

    @Override // p.si1
    public final si1 k() {
        return this.z.k();
    }

    @Override // p.si1
    public final boolean m() {
        return this.z.m();
    }

    @Override // p.si1
    public final boolean n() {
        return this.z.n();
    }

    @Override // p.si1
    public final boolean o() {
        return this.z.o();
    }

    @Override // p.si1, android.view.Menu
    public final void setGroupDividerEnabled(boolean z) {
        this.z.setGroupDividerEnabled(z);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        u(0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        u(0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderView(View view) {
        u(0, null, 0, null, view);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        this.A.setIcon(drawable);
        return this;
    }

    @Override // p.si1, android.view.Menu
    public final void setQwertyMode(boolean z) {
        this.z.setQwertyMode(z);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i) {
        u(0, null, i, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i) {
        u(i, null, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i) {
        this.A.setIcon(i);
        return this;
    }
}
