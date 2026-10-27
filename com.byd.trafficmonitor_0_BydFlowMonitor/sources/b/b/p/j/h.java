package b.b.p.j;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import b.b.k.c;
import b.b.p.j.m;

/* JADX INFO: compiled from: MenuDialogHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, m.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g f1641b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.k.c f1642c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e f1643d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public m.a f1644e;

    public h(g gVar) {
        this.f1641b = gVar;
    }

    @Override // b.b.p.j.m.a
    public void a(g gVar, boolean z) {
        if (z || gVar == this.f1641b) {
            c();
        }
        m.a aVar = this.f1644e;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // b.b.p.j.m.a
    public boolean b(g gVar) {
        m.a aVar = this.f1644e;
        if (aVar != null) {
            return aVar.b(gVar);
        }
        return false;
    }

    public void c() {
        b.b.k.c cVar = this.f1642c;
        if (cVar != null) {
            cVar.dismiss();
        }
    }

    public void d(IBinder iBinder) {
        g gVar = this.f1641b;
        c.a aVar = new c.a(gVar.w());
        e eVar = new e(aVar.b(), b.b.g.abc_list_menu_item_layout);
        this.f1643d = eVar;
        eVar.k(this);
        this.f1641b.b(this.f1643d);
        aVar.c(this.f1643d.b(), this);
        View viewA = gVar.A();
        if (viewA != null) {
            aVar.d(viewA);
        } else {
            aVar.e(gVar.y()).h(gVar.z());
        }
        aVar.f(this);
        b.b.k.c cVarA = aVar.a();
        this.f1642c = cVarA;
        cVarA.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f1642c.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.f1642c.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        this.f1641b.N((i) this.f1643d.b().getItem(i2), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f1643d.a(this.f1641b, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i2 == 82 || i2 == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f1642c.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f1642c.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f1641b.e(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f1641b.performShortcut(i2, keyEvent, 0);
    }
}
