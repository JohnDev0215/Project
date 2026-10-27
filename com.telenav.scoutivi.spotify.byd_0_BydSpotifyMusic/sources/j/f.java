package j;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: SubMenuWrapperICS.java */
/* JADX INFO: loaded from: classes.dex */
class f extends d implements SubMenu {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final b0.c f7106e;

    f(Context context, b0.c cVar) {
        super(context, cVar);
        this.f7106e = cVar;
    }

    @Override // android.view.SubMenu
    public void clearHeader() {
        this.f7106e.clearHeader();
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return c(this.f7106e.getItem());
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i7) {
        this.f7106e.setHeaderIcon(i7);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i7) {
        this.f7106e.setHeaderTitle(i7);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        this.f7106e.setHeaderView(view);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i7) {
        this.f7106e.setIcon(i7);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        this.f7106e.setHeaderIcon(drawable);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        this.f7106e.setHeaderTitle(charSequence);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.f7106e.setIcon(drawable);
        return this;
    }
}
