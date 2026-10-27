package com.google.android.gms.common.api.internal;

import android.util.Pair;
import com.google.android.gms.common.api.Status;
import defpackage.AbstractC0146Ic;
import defpackage.AbstractC0182Kc;
import defpackage.C1334pd;
import defpackage.C1829ye;
import defpackage.Cif;
import defpackage.HandlerC0379Vc;
import defpackage.InterfaceC0164Jc;
import defpackage.InterfaceC0217Mc;
import defpackage.InterfaceC0235Nc;
import defpackage.InterfaceC0558bf;
import defpackage.InterfaceC1499se;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class BasePendingResult extends AbstractC0182Kc {
    public static final ThreadLocal o = new C1829ye();
    public final HandlerC0379Vc b;
    public final WeakReference c;
    public InterfaceC0235Nc f;
    public InterfaceC0217Mc h;
    public Status i;
    public volatile boolean j;
    public boolean k;
    public boolean l;
    public InterfaceC0558bf m;
    public final Object a = new Object();
    public final CountDownLatch d = new CountDownLatch(1);
    public final ArrayList e = new ArrayList();
    public final AtomicReference g = new AtomicReference();
    public boolean n = false;

    public BasePendingResult(AbstractC0146Ic abstractC0146Ic) {
        this.b = new HandlerC0379Vc(abstractC0146Ic.h());
        this.c = new WeakReference(abstractC0146Ic);
    }

    public void a() {
        synchronized (this.a) {
            if (!this.k && !this.j) {
                this.k = true;
                e(Status.z);
            }
        }
    }

    public final InterfaceC0217Mc b() {
        InterfaceC0217Mc interfaceC0217Mc;
        synchronized (this.a) {
            Cif.i(!this.j, "Result has already been consumed.");
            Cif.i(c(), "Result is not ready.");
            interfaceC0217Mc = this.h;
            this.h = null;
            this.f = null;
            this.j = true;
        }
        InterfaceC1499se interfaceC1499se = (InterfaceC1499se) this.g.getAndSet(null);
        if (interfaceC1499se != null) {
            interfaceC1499se.a(this);
        }
        return interfaceC0217Mc;
    }

    public final boolean c() {
        return this.d.getCount() == 0;
    }

    public final void d(InterfaceC0217Mc interfaceC0217Mc) {
        synchronized (this.a) {
            if (this.l || this.k) {
                return;
            }
            c();
            boolean z = true;
            Cif.i(!c(), "Results have already been set");
            if (this.j) {
                z = false;
            }
            Cif.i(z, "Result has already been consumed");
            e(interfaceC0217Mc);
        }
    }

    public final void e(InterfaceC0217Mc interfaceC0217Mc) {
        this.h = interfaceC0217Mc;
        this.m = null;
        this.d.countDown();
        this.i = this.h.a();
        if (this.k) {
            this.f = null;
        } else if (this.f != null) {
            this.b.removeMessages(2);
            HandlerC0379Vc handlerC0379Vc = this.b;
            InterfaceC0235Nc interfaceC0235Nc = this.f;
            InterfaceC0217Mc interfaceC0217McB = b();
            Objects.requireNonNull(handlerC0379Vc);
            handlerC0379Vc.sendMessage(handlerC0379Vc.obtainMessage(1, new Pair(interfaceC0235Nc, interfaceC0217McB)));
        }
        ArrayList arrayList = this.e;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            C1334pd c1334pd = (C1334pd) ((InterfaceC0164Jc) obj);
            c1334pd.b.a.remove(c1334pd.a);
        }
        this.e.clear();
    }

    public final boolean f() {
        boolean z;
        synchronized (this.a) {
            if (((AbstractC0146Ic) this.c.get()) == null || !this.n) {
                a();
            }
            synchronized (this.a) {
                z = this.k;
            }
        }
        return z;
    }

    public final void g() {
        this.n = this.n || ((Boolean) o.get()).booleanValue();
    }

    public final void h(Status status) {
        synchronized (this.a) {
            if (!c()) {
                d(status);
                this.l = true;
            }
        }
    }
}
