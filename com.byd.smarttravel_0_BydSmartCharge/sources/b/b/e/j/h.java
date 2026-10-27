package b.b.e.j;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.R$layout;
import b.b.a.c;
import b.b.e.j.m;

/* JADX INFO: compiled from: MenuDialogHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, m.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g f711b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.a.c f712c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e f713d;
    public m.a e;

    public h(g gVar) {
        this.f711b = gVar;
    }

    @Override // b.b.e.j.m.a
    public void a(g gVar, boolean z) {
        if (z || gVar == this.f711b) {
            c();
        }
        m.a aVar = this.e;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // b.b.e.j.m.a
    public boolean b(g gVar) {
        m.a aVar = this.e;
        if (aVar != null) {
            return aVar.b(gVar);
        }
        return false;
    }

    public void c() {
        b.b.a.c cVar = this.f712c;
        if (cVar != null) {
            cVar.dismiss();
        }
    }

    public void d(IBinder iBinder) {
        g gVar = this.f711b;
        c.a aVar = new c.a(gVar.w());
        e eVar = new e(aVar.b(), R$layout.abc_list_menu_item_layout);
        this.f713d = eVar;
        eVar.k(this);
        this.f711b.b(this.f713d);
        aVar.c(this.f713d.b(), this);
        View viewA = gVar.A();
        if (viewA != null) {
            aVar.d(viewA);
        } else {
            aVar.e(gVar.y()).h(gVar.z());
        }
        aVar.f(this);
        b.b.a.c cVarA = aVar.a();
        this.f712c = cVarA;
        cVarA.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f712c.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.f712c.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.f711b.N((i) this.f713d.b().getItem(i), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f713d.a(this.f711b, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f712c.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f712c.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f711b.e(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f711b.performShortcut(i, keyEvent, 0);
    }
}
