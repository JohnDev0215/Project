package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.f;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class q extends f implements SubMenu {
    private f B;
    private h C;

    public q(Context context, f fVar, h hVar) {
        super(context);
        this.B = fVar;
        this.C = hVar;
    }

    @Override // androidx.appcompat.view.menu.f
    public f D() {
        return this.B.D();
    }

    @Override // androidx.appcompat.view.menu.f
    public boolean F() {
        return this.B.F();
    }

    @Override // androidx.appcompat.view.menu.f
    public boolean G() {
        return this.B.G();
    }

    @Override // androidx.appcompat.view.menu.f
    public boolean H() {
        return this.B.H();
    }

    @Override // androidx.appcompat.view.menu.f
    public void R(f.a aVar) {
        this.B.R(aVar);
    }

    public Menu e0() {
        return this.B;
    }

    @Override // androidx.appcompat.view.menu.f
    public boolean f(h hVar) {
        return this.B.f(hVar);
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.C;
    }

    @Override // androidx.appcompat.view.menu.f
    boolean h(@NonNull f fVar, @NonNull MenuItem menuItem) {
        return super.h(fVar, menuItem) || this.B.h(fVar, menuItem);
    }

    @Override // androidx.appcompat.view.menu.f
    public boolean k(h hVar) {
        return this.B.k(hVar);
    }

    @Override // androidx.appcompat.view.menu.f, android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.B.setGroupDividerEnabled(z);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        super.V(drawable);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        super.Y(charSequence);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        super.Z(view);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.C.setIcon(drawable);
        return this;
    }

    @Override // androidx.appcompat.view.menu.f, android.view.Menu
    public void setQwertyMode(boolean z) {
        this.B.setQwertyMode(z);
    }

    @Override // androidx.appcompat.view.menu.f
    public String t() {
        h hVar = this.C;
        int itemId = hVar != null ? hVar.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.t() + ":" + itemId;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i) {
        super.U(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i) {
        super.X(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i) {
        this.C.setIcon(i);
        return this;
    }
}
