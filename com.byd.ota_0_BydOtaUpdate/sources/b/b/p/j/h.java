package b.b.p.j;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import b.b.k.b;
import b.b.p.j.m;

/* JADX INFO: compiled from: MenuDialogHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, m.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f946c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.b.k.b f947d;
    public e s;
    public m.a x;

    public h(g gVar) {
        this.f946c = gVar;
    }

    @Override // b.b.p.j.m.a
    public void a(g gVar, boolean z) {
        if (z || gVar == this.f946c) {
            c();
        }
        m.a aVar = this.x;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // b.b.p.j.m.a
    public boolean b(g gVar) {
        m.a aVar = this.x;
        if (aVar != null) {
            return aVar.b(gVar);
        }
        return false;
    }

    public void c() {
        b.b.k.b bVar = this.f947d;
        if (bVar != null) {
            bVar.dismiss();
        }
    }

    public void d(IBinder iBinder) {
        g gVar = this.f946c;
        b.a aVar = new b.a(gVar.u());
        e eVar = new e(aVar.b(), b.b.g.l);
        this.s = eVar;
        eVar.h(this);
        this.f946c.b(this.s);
        aVar.c(this.s.b(), this);
        View viewY = gVar.y();
        if (viewY != null) {
            aVar.d(viewY);
        } else {
            aVar.e(gVar.w()).h(gVar.x());
        }
        aVar.f(this);
        b.b.k.b bVarA = aVar.a();
        this.f947d = bVarA;
        bVarA.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f947d.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.f947d.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        this.f946c.L((i) this.s.b().getItem(i2), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.s.a(this.f946c, true);
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
                Window window2 = this.f947d.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f947d.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f946c.e(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f946c.performShortcut(i2, keyEvent, 0);
    }
}
