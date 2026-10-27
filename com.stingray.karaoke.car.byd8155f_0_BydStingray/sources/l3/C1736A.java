package l3;

import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.os.RemoteException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import k3.C1646h0;
import k3.ResultReceiverC1643g0;

/* JADX INFO: renamed from: l3.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1736A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MediaController f18506a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f18507b = new Object();
    public final ArrayList c = new ArrayList();
    public final HashMap d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final O f18508e;

    public C1736A(Context context, O o) {
        this.f18508e = o;
        Object obj = o.f18534m;
        obj.getClass();
        MediaController mediaController = new MediaController(context, (MediaSession.Token) obj);
        this.f18506a = mediaController;
        if (o.a() == null) {
            ResultReceiverC1643g0 resultReceiverC1643g0 = new ResultReceiverC1643g0(null);
            resultReceiverC1643g0.f17962m = new WeakReference(this);
            mediaController.sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, resultReceiverC1643g0);
        }
    }

    public final void a() {
        InterfaceC1750f interfaceC1750fA = this.f18508e.a();
        if (interfaceC1750fA == null) {
            return;
        }
        ArrayList<C1646h0> arrayList = this.c;
        for (C1646h0 c1646h0 : arrayList) {
            BinderC1769z binderC1769z = new BinderC1769z(c1646h0);
            this.d.put(c1646h0, binderC1769z);
            c1646h0.c = binderC1769z;
            try {
                interfaceC1750fA.h1(binderC1769z);
                c1646h0.i(13, null, null);
            } catch (RemoteException e10) {
                h6.n.i("MediaControllerCompat", "Dead object in registerCallback.", e10);
            }
        }
        arrayList.clear();
    }

    public final void b(C1646h0 c1646h0) {
        MediaController mediaController = this.f18506a;
        C1767x c1767x = c1646h0.f17963a;
        c1767x.getClass();
        mediaController.unregisterCallback(c1767x);
        synchronized (this.f18507b) {
            InterfaceC1750f interfaceC1750fA = this.f18508e.a();
            if (interfaceC1750fA != null) {
                try {
                    BinderC1769z binderC1769z = (BinderC1769z) this.d.remove(c1646h0);
                    if (binderC1769z != null) {
                        c1646h0.c = null;
                        interfaceC1750fA.c0(binderC1769z);
                    }
                } catch (RemoteException e10) {
                    h6.n.i("MediaControllerCompat", "Dead object in unregisterCallback.", e10);
                }
            }
            this.c.remove(c1646h0);
        }
    }
}
