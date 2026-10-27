package n;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.R$layout;
import i.c;
import n.m;

/* JADX INFO: compiled from: MenuDialogHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, m.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g f3959b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public i.c f3960c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e f3961d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public m.a f3962e;

    public h(g gVar) {
        this.f3959b = gVar;
    }

    @Override // n.m.a
    public void a(g gVar, boolean z3) {
        if (z3 || gVar == this.f3959b) {
            c();
        }
        m.a aVar = this.f3962e;
        if (aVar != null) {
            aVar.a(gVar, z3);
        }
    }

    @Override // n.m.a
    public boolean b(g gVar) {
        m.a aVar = this.f3962e;
        if (aVar != null) {
            return aVar.b(gVar);
        }
        return false;
    }

    public void c() {
        i.c cVar = this.f3960c;
        if (cVar != null) {
            cVar.dismiss();
        }
    }

    public void d(IBinder iBinder) {
        g gVar = this.f3959b;
        c.a aVar = new c.a(gVar.w());
        e eVar = new e(aVar.b(), R$layout.abc_list_menu_item_layout);
        this.f3961d = eVar;
        eVar.k(this);
        this.f3959b.b(this.f3961d);
        aVar.c(this.f3961d.b(), this);
        View viewA = gVar.A();
        if (viewA != null) {
            aVar.d(viewA);
        } else {
            aVar.e(gVar.y()).h(gVar.z());
        }
        aVar.f(this);
        i.c cVarA = aVar.a();
        this.f3960c = cVarA;
        cVarA.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f3960c.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.f3960c.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i3) {
        this.f3959b.N((i) this.f3961d.b().getItem(i3), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f3961d.a(this.f3959b, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i3, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i3 == 82 || i3 == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f3960c.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f3960c.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f3959b.e(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f3959b.performShortcut(i3, keyEvent, 0);
    }
}
