package a.b.o.i;

import a.b.o.i.e;
import a.b.o.i.m;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public class h implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, m.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g f182a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a.b.k.h f183b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e f184c;

    public h(g gVar) {
        this.f182a = gVar;
    }

    @Override // a.b.o.i.m.a
    public void b(g gVar, boolean z) {
        a.b.k.h hVar;
        if ((z || gVar == this.f182a) && (hVar = this.f183b) != null) {
            hVar.dismiss();
        }
    }

    @Override // a.b.o.i.m.a
    public boolean c(g gVar) {
        return false;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.f182a.r(((e.a) this.f184c.a()).getItem(i), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        e eVar = this.f184c;
        g gVar = this.f182a;
        m.a aVar = eVar.h;
        if (aVar != null) {
            aVar.b(gVar, true);
        }
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
                Window window2 = this.f183b.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f183b.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f182a.c(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f182a.performShortcut(i, keyEvent, 0);
    }
}
