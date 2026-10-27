package l3;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Iterator;

/* JADX INFO: renamed from: l3.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1763t implements Runnable {
    public final /* synthetic */ C1764u l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f18605m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f18606n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C1764u f18607p;

    public RunnableC1763t(int i7, int i10, Bundle bundle, String str, C1764u c1764u, C1764u c1764u2) {
        this.f18607p = c1764u;
        this.l = c1764u2;
        this.f18605m = i7;
        this.f18606n = str;
        this.o = i10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C1756l c1756l;
        C1764u c1764u = this.l;
        IBinder binder = ((Messenger) c1764u.f18608m).getBinder();
        C1764u c1764u2 = this.f18607p;
        ((AbstractServiceC1765v) c1764u2.f18608m).f18612p.remove(binder);
        AbstractServiceC1765v abstractServiceC1765v = (AbstractServiceC1765v) c1764u2.f18608m;
        Iterator it = abstractServiceC1765v.o.iterator();
        while (true) {
            c1756l = null;
            if (!it.hasNext()) {
                break;
            }
            C1756l c1756l2 = (C1756l) it.next();
            if (c1756l2.c == this.f18605m) {
                if (TextUtils.isEmpty(this.f18606n) || this.o <= 0) {
                    c1756l = new C1756l((AbstractServiceC1765v) c1764u2.f18608m, c1756l2.f18580a, c1756l2.f18581b, c1756l2.c, c1764u);
                }
                it.remove();
            }
        }
        if (c1756l == null) {
            c1756l = new C1756l((AbstractServiceC1765v) c1764u2.f18608m, this.f18606n, this.o, this.f18605m, c1764u);
        }
        abstractServiceC1765v.f18612p.put(binder, c1756l);
        try {
            binder.linkToDeath(c1756l, 0);
        } catch (RemoteException unused) {
            h6.n.t("MBServiceCompat", "IBinder is already dead.");
        }
    }
}
