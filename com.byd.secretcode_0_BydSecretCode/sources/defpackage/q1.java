package defpackage;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.R$layout;
import defpackage.k0;
import defpackage.v1;

/* JADX INFO: compiled from: MenuDialogHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class q1 implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, v1.a {
    public p1 b;
    public k0 c;
    public n1 d;
    public v1.a e;

    public q1(p1 p1Var) {
        this.b = p1Var;
    }

    @Override // v1.a
    public void a(p1 p1Var, boolean z) {
        if (z || p1Var == this.b) {
            c();
        }
        v1.a aVar = this.e;
        if (aVar != null) {
            aVar.a(p1Var, z);
        }
    }

    @Override // v1.a
    public boolean b(p1 p1Var) {
        v1.a aVar = this.e;
        if (aVar != null) {
            return aVar.b(p1Var);
        }
        return false;
    }

    public void c() {
        k0 k0Var = this.c;
        if (k0Var != null) {
            k0Var.dismiss();
        }
    }

    public void d(IBinder iBinder) {
        p1 p1Var = this.b;
        k0.a aVar = new k0.a(p1Var.w());
        n1 n1Var = new n1(aVar.b(), R$layout.abc_list_menu_item_layout);
        this.d = n1Var;
        n1Var.k(this);
        this.b.b(this.d);
        aVar.c(this.d.b(), this);
        View viewA = p1Var.A();
        if (viewA != null) {
            aVar.d(viewA);
        } else {
            aVar.e(p1Var.y()).i(p1Var.z());
        }
        aVar.f(this);
        k0 k0VarA = aVar.a();
        this.c = k0VarA;
        k0VarA.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.c.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.c.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.N((r1) this.d.b().getItem(i), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.d.a(this.b, true);
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
                Window window2 = this.c.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.c.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.b.e(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.b.performShortcut(i, keyEvent, 0);
    }
}
