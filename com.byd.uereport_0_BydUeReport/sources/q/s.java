package q;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: SubMenuWrapperICS.java */
/* JADX INFO: loaded from: classes.dex */
public class s extends o implements SubMenu {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final i0.c f7236e;

    public s(Context context, i0.c cVar) {
        super(context, cVar);
        this.f7236e = cVar;
    }

    @Override // android.view.SubMenu
    public void clearHeader() {
        this.f7236e.clearHeader();
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return c(this.f7236e.getItem());
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i8) {
        this.f7236e.setHeaderIcon(i8);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i8) {
        this.f7236e.setHeaderTitle(i8);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        this.f7236e.setHeaderView(view);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i8) {
        this.f7236e.setIcon(i8);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        this.f7236e.setHeaderIcon(drawable);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        this.f7236e.setHeaderTitle(charSequence);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.f7236e.setIcon(drawable);
        return this;
    }
}
