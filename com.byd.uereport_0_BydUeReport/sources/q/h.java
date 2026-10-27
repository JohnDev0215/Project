package q;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.R$layout;
import l.c;
import q.m;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: compiled from: MenuDialogHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, m.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f7156d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public l.c f7157e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public e f7158f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public m.a f7159g;

    public h(g gVar) {
        this.f7156d = gVar;
    }

    @Override // q.m.a
    public void a(g gVar, boolean z7) {
        if (z7 || gVar == this.f7156d) {
            c();
        }
        m.a aVar = this.f7159g;
        if (aVar != null) {
            aVar.a(gVar, z7);
        }
    }

    @Override // q.m.a
    public boolean b(g gVar) {
        m.a aVar = this.f7159g;
        if (aVar != null) {
            return aVar.b(gVar);
        }
        return false;
    }

    public void c() {
        l.c cVar = this.f7157e;
        if (cVar != null) {
            cVar.dismiss();
        }
    }

    public void d(IBinder iBinder) {
        g gVar = this.f7156d;
        c.a aVar = new c.a(gVar.w());
        e eVar = new e(aVar.b(), R$layout.abc_list_menu_item_layout);
        this.f7158f = eVar;
        eVar.n(this);
        this.f7156d.b(this.f7158f);
        aVar.c(this.f7158f.c(), this);
        View viewA = gVar.A();
        if (viewA != null) {
            aVar.d(viewA);
        } else {
            aVar.e(gVar.y()).h(gVar.z());
        }
        aVar.f(this);
        l.c cVarA = aVar.a();
        this.f7157e = cVarA;
        cVarA.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f7157e.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= CarPlayConfig.IapFeature.IAP_FEATURE_APP_LAUNCH;
        this.f7157e.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i8) {
        this.f7156d.N((i) this.f7158f.c().getItem(i8), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f7158f.a(this.f7156d, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i8, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i8 == 82 || i8 == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f7157e.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f7157e.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f7156d.e(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f7156d.performShortcut(i8, keyEvent, 0);
    }
}
