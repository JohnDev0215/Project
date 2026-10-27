package androidx.appcompat.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.appcompat.R$layout;
import androidx.appcompat.app.a;
import androidx.appcompat.view.menu.l;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class g implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, l.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private f f225c;
    private androidx.appcompat.app.a d;
    d e;
    private l.a f;

    public g(f fVar) {
        this.f225c = fVar;
    }

    @Override // androidx.appcompat.view.menu.l.a
    public void a(@NonNull f fVar, boolean z) {
        if (z || fVar == this.f225c) {
            c();
        }
        l.a aVar = this.f;
        if (aVar != null) {
            aVar.a(fVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.l.a
    public boolean b(@NonNull f fVar) {
        l.a aVar = this.f;
        if (aVar != null) {
            return aVar.b(fVar);
        }
        return false;
    }

    public void c() {
        androidx.appcompat.app.a aVar = this.d;
        if (aVar != null) {
            aVar.dismiss();
        }
    }

    public void d(IBinder iBinder) {
        f fVar = this.f225c;
        a.C0003a c0003a = new a.C0003a(fVar.u());
        d dVar = new d(c0003a.b(), R$layout.abc_list_menu_item_layout);
        this.e = dVar;
        dVar.h(this);
        this.f225c.b(this.e);
        c0003a.c(this.e.b(), this);
        View viewY = fVar.y();
        if (viewY != null) {
            c0003a.d(viewY);
        } else {
            c0003a.e(fVar.w());
            c0003a.h(fVar.x());
        }
        c0003a.f(this);
        androidx.appcompat.app.a aVarA = c0003a.a();
        this.d = aVarA;
        aVarA.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.d.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.d.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.f225c.L((h) this.e.b().getItem(i), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.e.a(this.f225c, true);
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
                Window window2 = this.d.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.d.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f225c.e(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f225c.performShortcut(i, keyEvent, 0);
    }
}
