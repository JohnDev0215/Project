package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* JADX INFO: renamed from: Pd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0272Pd implements InterfaceC0724ee, InterfaceC1884ze {
    public final Lock a;
    public final Condition b;
    public final Context c;
    public final AbstractC1278oc d;
    public final HandlerC0308Rd e;
    public final Map f;
    public final Map g = new HashMap();
    public final C0381Ve h;
    public final Map i;
    public final AbstractC1552tc j;
    public volatile InterfaceC0290Qd k;
    public int l;
    public final C0165Jd m;
    public final InterfaceC0780fe n;

    public C0272Pd(Context context, C0165Jd c0165Jd, Lock lock, Looper looper, AbstractC1278oc abstractC1278oc, Map map, C0381Ve c0381Ve, Map map2, AbstractC1552tc abstractC1552tc, ArrayList arrayList, InterfaceC0780fe interfaceC0780fe) {
        this.c = context;
        this.a = lock;
        this.d = abstractC1278oc;
        this.f = map;
        this.h = c0381Ve;
        this.i = map2;
        this.j = abstractC1552tc;
        this.m = c0165Jd;
        this.n = interfaceC0780fe;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((C0004Ae) obj).c = this;
        }
        this.e = new HandlerC0308Rd(this, looper);
        this.b = lock.newCondition();
        this.k = new C0183Kd(this);
    }

    @Override // defpackage.InterfaceC0724ee
    public final boolean a() {
        return this.k instanceof C1718wd;
    }

    @Override // defpackage.InterfaceC0724ee
    public final AbstractC0343Tc b(AbstractC0343Tc abstractC0343Tc) {
        abstractC0343Tc.g();
        return this.k.b(abstractC0343Tc);
    }

    @Override // defpackage.InterfaceC1884ze
    public final void c(ConnectionResult connectionResult, C0020Bc c0020Bc, boolean z) {
        this.a.lock();
        try {
            this.k.c(connectionResult, c0020Bc, z);
        } finally {
            this.a.unlock();
        }
    }

    @Override // defpackage.InterfaceC0397Wc
    public final void d(int i) {
        this.a.lock();
        try {
            this.k.d(i);
        } finally {
            this.a.unlock();
        }
    }

    @Override // defpackage.InterfaceC0724ee
    public final void disconnect() {
        if (this.k.disconnect()) {
            this.g.clear();
        }
    }

    @Override // defpackage.InterfaceC0724ee
    public final void e() {
        this.k.e();
    }

    @Override // defpackage.InterfaceC0397Wc
    public final void f(Bundle bundle) {
        this.a.lock();
        try {
            this.k.f(bundle);
        } finally {
            this.a.unlock();
        }
    }

    @Override // defpackage.InterfaceC0724ee
    public final void g(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String strConcat = String.valueOf(str).concat("  ");
        printWriter.append((CharSequence) str).append("mState=").println(this.k);
        for (C0020Bc c0020Bc : this.i.keySet()) {
            printWriter.append((CharSequence) str).append((CharSequence) c0020Bc.c).println(":");
            ((BaseGmsClient) ((InterfaceC1882zc) this.f.get(c0020Bc.a()))).dump(strConcat, fileDescriptor, printWriter, strArr);
        }
    }

    public final void h(ConnectionResult connectionResult) {
        this.a.lock();
        try {
            this.k = new C0183Kd(this);
            this.k.g();
            this.b.signalAll();
        } finally {
            this.a.unlock();
        }
    }
}
