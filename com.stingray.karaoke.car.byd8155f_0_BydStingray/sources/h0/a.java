package H0;

import A7.m;
import android.graphics.Rect;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import m0.d;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class a extends ActionMode.Callback2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f2764a;

    public a(b bVar) {
        this.f2764a = bVar;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        b bVar = this.f2764a;
        bVar.getClass();
        m.c(menuItem);
        int itemId = menuItem.getItemId();
        if (itemId == 0) {
            InterfaceC2772a interfaceC2772a = (InterfaceC2772a) bVar.f2766n;
            if (interfaceC2772a != null) {
                interfaceC2772a.a();
            }
        } else if (itemId == 1) {
            InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) bVar.o;
            if (interfaceC2772a2 != null) {
                interfaceC2772a2.a();
            }
        } else if (itemId == 2) {
            InterfaceC2772a interfaceC2772a3 = (InterfaceC2772a) bVar.f2767p;
            if (interfaceC2772a3 != null) {
                interfaceC2772a3.a();
            }
        } else {
            if (itemId != 3) {
                return false;
            }
            InterfaceC2772a interfaceC2772a4 = (InterfaceC2772a) bVar.f2768q;
            if (interfaceC2772a4 != null) {
                interfaceC2772a4.a();
            }
        }
        if (actionMode == null) {
            return true;
        }
        actionMode.finish();
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        b bVar = this.f2764a;
        bVar.getClass();
        if (menu == null) {
            throw new IllegalArgumentException("onCreateActionMode requires a non-null menu");
        }
        if (actionMode == null) {
            throw new IllegalArgumentException("onCreateActionMode requires a non-null mode");
        }
        if (((InterfaceC2772a) bVar.f2766n) != null) {
            b.h(1, menu);
        }
        if (((InterfaceC2772a) bVar.o) != null) {
            b.h(2, menu);
        }
        if (((InterfaceC2772a) bVar.f2767p) != null) {
            b.h(3, menu);
        }
        if (((InterfaceC2772a) bVar.f2768q) != null) {
            b.h(4, menu);
        }
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public final void onDestroyActionMode(ActionMode actionMode) {
        InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.f2764a.f2765m;
        if (interfaceC2772a != null) {
            interfaceC2772a.a();
        }
    }

    @Override // android.view.ActionMode.Callback2
    public final void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
        d dVar = (d) this.f2764a.f2769r;
        if (rect != null) {
            rect.set((int) dVar.f18882a, (int) dVar.f18883b, (int) dVar.c, (int) dVar.d);
        }
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        b bVar = this.f2764a;
        bVar.getClass();
        if (actionMode == null || menu == null) {
            return false;
        }
        b.j(menu, 1, (InterfaceC2772a) bVar.f2766n);
        b.j(menu, 2, (InterfaceC2772a) bVar.o);
        b.j(menu, 3, (InterfaceC2772a) bVar.f2767p);
        b.j(menu, 4, (InterfaceC2772a) bVar.f2768q);
        return true;
    }
}
