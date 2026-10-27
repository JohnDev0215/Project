package n;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: SubMenuWrapperICS.java */
/* JADX INFO: loaded from: classes.dex */
public class s extends o implements SubMenu {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f0.c f4039e;

    public s(Context context, f0.c cVar) {
        super(context, cVar);
        this.f4039e = cVar;
    }

    @Override // android.view.SubMenu
    public void clearHeader() {
        this.f4039e.clearHeader();
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return c(this.f4039e.getItem());
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i3) {
        this.f4039e.setHeaderIcon(i3);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i3) {
        this.f4039e.setHeaderTitle(i3);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        this.f4039e.setHeaderView(view);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i3) {
        this.f4039e.setIcon(i3);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        this.f4039e.setHeaderIcon(drawable);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        this.f4039e.setHeaderTitle(charSequence);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.f4039e.setIcon(drawable);
        return this;
    }
}
