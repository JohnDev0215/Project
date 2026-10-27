package io.sentry.android.replay.util;

import A7.m;
import T1.k;
import e0.C1183w;
import io.sentry.F1;
import io.sentry.L0;
import io.sentry.android.replay.capture.c;
import io.sentry.android.replay.i;
import java.io.BufferedWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final class a extends LinkedList {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final F1 f16439m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ScheduledExecutorService f16440n;
    public final C1183w o;

    public a(F1 f12, ScheduledExecutorService scheduledExecutorService, C1183w c1183w) {
        m.f(f12, "options");
        this.l = "replay.recording";
        this.f16439m = f12;
        this.f16440n = scheduledExecutorService;
        this.o = c1183w;
    }

    public final void a() {
        i iVar = ((c) this.o.f13745n).f16353h;
        if (iVar == null) {
            return;
        }
        L0 l02 = new L0();
        l02.f15890m = new ArrayList(this);
        F1 f12 = this.f16439m;
        if (f12.getMainThreadChecker().a()) {
            this.f16440n.submit(new k(this, l02, iVar, 7));
        } else {
            StringWriter stringWriter = new StringWriter();
            f12.getSerializer().f(l02, new BufferedWriter(stringWriter));
            iVar.i(this.l, stringWriter.toString());
        }
    }

    @Override // java.util.LinkedList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List, java.util.Deque, java.util.Queue
    public final boolean add(Object obj) {
        io.sentry.rrweb.b bVar = (io.sentry.rrweb.b) obj;
        m.f(bVar, "element");
        boolean zAdd = super.add(bVar);
        a();
        return zAdd;
    }

    @Override // java.util.LinkedList, java.util.AbstractCollection, java.util.Collection, java.util.List, java.util.Deque
    public final boolean addAll(Collection collection) {
        m.f(collection, "elements");
        boolean zAddAll = super.addAll(collection);
        a();
        return zAddAll;
    }

    @Override // java.util.LinkedList, java.util.AbstractCollection, java.util.Collection, java.util.List, java.util.Deque
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof io.sentry.rrweb.b) {
            return super.contains((io.sentry.rrweb.b) obj);
        }
        return false;
    }

    @Override // java.util.LinkedList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof io.sentry.rrweb.b) {
            return super.indexOf((io.sentry.rrweb.b) obj);
        }
        return -1;
    }

    @Override // java.util.LinkedList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof io.sentry.rrweb.b) {
            return super.lastIndexOf((io.sentry.rrweb.b) obj);
        }
        return -1;
    }

    @Override // java.util.LinkedList, java.util.AbstractCollection, java.util.Collection, java.util.List, java.util.Deque
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof io.sentry.rrweb.b) {
            return super.remove((io.sentry.rrweb.b) obj);
        }
        return false;
    }

    @Override // java.util.LinkedList, java.util.Deque, java.util.Queue
    public final Object remove() {
        io.sentry.rrweb.b bVar = (io.sentry.rrweb.b) super.remove();
        a();
        m.e(bVar, "result");
        return bVar;
    }
}
