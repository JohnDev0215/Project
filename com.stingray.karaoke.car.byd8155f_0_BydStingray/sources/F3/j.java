package F3;

import K3.k;
import android.content.Context;
import android.os.Bundle;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import h6.n;
import java.io.Serializable;
import java.util.ArrayList;
import l3.AbstractServiceC1765v;
import l3.C1756l;
import l3.C1764u;
import u.C2465e;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2404a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2405b;
    public final Object c;
    public final Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Serializable f2406e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f2407f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f2408g;

    public j(K3.i iVar, ArrayList arrayList, int i7, K3.i iVar2, L3.g gVar, z3.c cVar, boolean z6) {
        this.c = iVar;
        this.f2406e = arrayList;
        this.f2405b = i7;
        this.d = iVar2;
        this.f2407f = gVar;
        this.f2408g = cVar;
        this.f2404a = z6;
    }

    public void a(K3.i iVar, h hVar) {
        Context context = iVar.f3542a;
        K3.i iVar2 = (K3.i) this.c;
        if (context != iVar2.f3542a) {
            throw new IllegalStateException(("Interceptor '" + hVar + "' cannot modify the request's context.").toString());
        }
        if (iVar.f3543b == k.f3564a) {
            throw new IllegalStateException(("Interceptor '" + hVar + "' cannot set the request's data to null.").toString());
        }
        if (iVar.c != iVar2.c) {
            throw new IllegalStateException(("Interceptor '" + hVar + "' cannot modify the request's target.").toString());
        }
        if (iVar.f3558u != iVar2.f3558u) {
            throw new IllegalStateException(("Interceptor '" + hVar + "' cannot modify the request's lifecycle.").toString());
        }
        if (iVar.f3559v == iVar2.f3559v) {
            return;
        }
        throw new IllegalStateException(("Interceptor '" + hVar + "' cannot modify the request's size resolver. Use `Interceptor.Chain.withSize` instead.").toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object b(K3.i r13, s7.AbstractC2410c r14) {
        /*
            r12 = this;
            boolean r0 = r14 instanceof F3.i
            if (r0 == 0) goto L13
            r0 = r14
            F3.i r0 = (F3.i) r0
            int r1 = r0.f2403p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f2403p = r1
            goto L18
        L13:
            F3.i r0 = new F3.i
            r0.<init>(r12, r14)
        L18:
            java.lang.Object r14 = r0.f2402n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f2403p
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            F3.h r12 = r0.f2401m
            F3.j r13 = r0.l
            h8.AbstractC1386c.u(r14)
            r2 = r12
            r12 = r13
            goto L79
        L2d:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L35:
            h8.AbstractC1386c.u(r14)
            java.io.Serializable r14 = r12.f2406e
            r6 = r14
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            int r14 = r12.f2405b
            if (r14 <= 0) goto L4c
            int r2 = r14 + (-1)
            java.lang.Object r2 = r6.get(r2)
            F3.h r2 = (F3.h) r2
            r12.a(r13, r2)
        L4c:
            java.lang.Object r2 = r6.get(r14)
            F3.h r2 = (F3.h) r2
            int r7 = r14 + 1
            F3.j r14 = new F3.j
            java.lang.Object r4 = r12.c
            r5 = r4
            K3.i r5 = (K3.i) r5
            java.lang.Object r4 = r12.f2408g
            r10 = r4
            z3.c r10 = (z3.c) r10
            java.lang.Object r4 = r12.f2407f
            r9 = r4
            L3.g r9 = (L3.g) r9
            boolean r11 = r12.f2404a
            r4 = r14
            r8 = r13
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)
            r0.l = r12
            r0.f2401m = r2
            r0.f2403p = r3
            java.lang.Object r14 = r2.d(r14, r0)
            if (r14 != r1) goto L79
            return r1
        L79:
            K3.j r14 = (K3.j) r14
            K3.i r13 = r14.b()
            r12.a(r13, r2)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: F3.j.b(K3.i, s7.c):java.lang.Object");
    }

    public void c() {
        if (this.f2404a) {
            throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.c);
        }
        this.f2404a = true;
        C2465e c2465e = ((AbstractServiceC1765v) this.f2408g).f18612p;
        C1756l c1756l = (C1756l) this.d;
        C1764u c1764u = c1756l.f18582e;
        c1764u.getClass();
        Object obj = c2465e.get(((Messenger) c1764u.f18608m).getBinder());
        String str = c1756l.f18580a;
        String str2 = (String) this.f2406e;
        if (obj != c1756l) {
            if (AbstractServiceC1765v.f18609t) {
                Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + str + " id=" + str2);
                return;
            }
            return;
        }
        int i7 = 1 & this.f2405b;
        Bundle bundle = (Bundle) this.f2407f;
        if (i7 != 0) {
            boolean z6 = AbstractServiceC1765v.f18609t;
        }
        try {
            c1764u.getClass();
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str2);
            bundle2.putBundle("data_options", bundle);
            bundle2.putBundle("data_notify_children_changed_options", null);
            c1764u.V(3, bundle2);
        } catch (RemoteException unused) {
            n.t("MBServiceCompat", "Calling onLoadChildren() failed for id=" + str2 + " package=" + str);
        }
    }

    public j(AbstractServiceC1765v abstractServiceC1765v, Object obj, C1756l c1756l, String str, Bundle bundle) {
        this.f2408g = abstractServiceC1765v;
        this.d = c1756l;
        this.f2406e = str;
        this.f2407f = bundle;
        this.c = obj;
    }
}
