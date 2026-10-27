package q;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: renamed from: q.r, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class SubMenuC2161r extends MenuC2151h implements SubMenu {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final MenuC2151h f21115w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final MenuItemC2152i f21116x;

    public SubMenuC2161r(Context context, MenuC2151h menuC2151h, MenuItemC2152i menuItemC2152i) {
        super(context);
        this.f21115w = menuC2151h;
        this.f21116x = menuItemC2152i;
    }

    @Override // q.MenuC2151h
    public final boolean d(MenuItemC2152i menuItemC2152i) {
        return this.f21115w.d(menuItemC2152i);
    }

    @Override // q.MenuC2151h
    public final boolean e(MenuC2151h menuC2151h, MenuItem menuItem) {
        return super.e(menuC2151h, menuItem) || this.f21115w.e(menuC2151h, menuItem);
    }

    @Override // q.MenuC2151h
    public final boolean f(MenuItemC2152i menuItemC2152i) {
        return this.f21115w.f(menuItemC2152i);
    }

    @Override // android.view.SubMenu
    public final MenuItem getItem() {
        return this.f21116x;
    }

    @Override // q.MenuC2151h
    public final MenuC2151h j() {
        return this.f21115w.j();
    }

    @Override // q.MenuC2151h
    public final boolean l() {
        return this.f21115w.l();
    }

    @Override // q.MenuC2151h
    public final boolean m() {
        return this.f21115w.m();
    }

    @Override // q.MenuC2151h
    public final boolean n() {
        return this.f21115w.n();
    }

    @Override // q.MenuC2151h, android.view.Menu
    public final void setGroupDividerEnabled(boolean z6) {
        this.f21115w.setGroupDividerEnabled(z6);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        q(0, null, 0, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        q(0, charSequence, 0, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderView(View view) {
        q(0, null, 0, view);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        this.f21116x.setIcon(drawable);
        return this;
    }

    @Override // q.MenuC2151h, android.view.Menu
    public final void setQwertyMode(boolean z6) {
        this.f21115w.setQwertyMode(z6);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i7) {
        q(0, null, i7, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i7) {
        q(i7, null, 0, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i7) {
        this.f21116x.setIcon(i7);
        return this;
    }
}
