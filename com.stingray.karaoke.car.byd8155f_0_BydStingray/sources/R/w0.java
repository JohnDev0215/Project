package r;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import q.InterfaceC2157n;
import q.MenuC2151h;
import q.MenuItemC2152i;
import q.SubMenuC2161r;

/* JADX INFO: loaded from: classes.dex */
public final class w0 implements InterfaceC2157n {
    public MenuC2151h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public MenuItemC2152i f21566m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Toolbar f21567n;

    public w0(Toolbar toolbar) {
        this.f21567n = toolbar;
    }

    @Override // q.InterfaceC2157n
    public final void a(MenuC2151h menuC2151h, boolean z6) {
    }

    @Override // q.InterfaceC2157n
    public final boolean b(SubMenuC2161r subMenuC2161r) {
        return false;
    }

    @Override // q.InterfaceC2157n
    public final void d() {
        if (this.f21566m != null) {
            MenuC2151h menuC2151h = this.l;
            if (menuC2151h != null) {
                int size = menuC2151h.f21052f.size();
                for (int i7 = 0; i7 < size; i7++) {
                    if (this.l.getItem(i7) == this.f21566m) {
                        return;
                    }
                }
            }
            k(this.f21566m);
        }
    }

    @Override // q.InterfaceC2157n
    public final boolean f(MenuItemC2152i menuItemC2152i) {
        Toolbar toolbar = this.f21567n;
        toolbar.c();
        ViewParent parent = toolbar.f11382s.getParent();
        if (parent != toolbar) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(toolbar.f11382s);
            }
            toolbar.addView(toolbar.f11382s);
        }
        View view = menuItemC2152i.f21088z;
        if (view == null) {
            view = null;
        }
        toolbar.f11383t = view;
        this.f21566m = menuItemC2152i;
        ViewParent parent2 = view.getParent();
        if (parent2 != toolbar) {
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(toolbar.f11383t);
            }
            x0 x0VarG = Toolbar.g();
            x0VarG.f21568a = (toolbar.f11388y & 112) | 8388611;
            x0VarG.f21569b = 2;
            toolbar.f11383t.setLayoutParams(x0VarG);
            toolbar.addView(toolbar.f11383t);
        }
        for (int childCount = toolbar.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = toolbar.getChildAt(childCount);
            if (((x0) childAt.getLayoutParams()).f21569b != 2 && childAt != toolbar.l) {
                toolbar.removeViewAt(childCount);
                toolbar.f11365P.add(childAt);
            }
        }
        toolbar.requestLayout();
        menuItemC2152i.B = true;
        menuItemC2152i.f21077n.o(false);
        toolbar.s();
        return true;
    }

    @Override // q.InterfaceC2157n
    public final boolean g() {
        return false;
    }

    @Override // q.InterfaceC2157n
    public final void h(Context context, MenuC2151h menuC2151h) {
        MenuItemC2152i menuItemC2152i;
        MenuC2151h menuC2151h2 = this.l;
        if (menuC2151h2 != null && (menuItemC2152i = this.f21566m) != null) {
            menuC2151h2.d(menuItemC2152i);
        }
        this.l = menuC2151h;
    }

    @Override // q.InterfaceC2157n
    public final boolean k(MenuItemC2152i menuItemC2152i) {
        Toolbar toolbar = this.f21567n;
        toolbar.removeView(toolbar.f11383t);
        toolbar.removeView(toolbar.f11382s);
        toolbar.f11383t = null;
        ArrayList arrayList = toolbar.f11365P;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            toolbar.addView((View) arrayList.get(size));
        }
        arrayList.clear();
        this.f21566m = null;
        toolbar.requestLayout();
        menuItemC2152i.B = false;
        menuItemC2152i.f21077n.o(false);
        toolbar.s();
        return true;
    }
}
