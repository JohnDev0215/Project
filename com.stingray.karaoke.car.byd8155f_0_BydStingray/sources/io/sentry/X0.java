package io.sentry;

import io.sentry.protocol.C1498c;
import io.sentry.protocol.C1499d;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class X0 {
    public io.sentry.protocol.t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C1498c f15978m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public io.sentry.protocol.r f15979n;
    public io.sentry.protocol.n o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public AbstractMap f15980p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f15981q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f15982r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f15983s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public io.sentry.protocol.E f15984t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public transient Exception f15985u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f15986v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public String f15987w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public List f15988x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public C1499d f15989y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public AbstractMap f15990z;

    public X0(io.sentry.protocol.t tVar) {
        this.f15978m = new C1498c();
        this.l = tVar;
    }

    public final void a(String str, String str2) {
        if (this.f15980p == null) {
            this.f15980p = new HashMap();
        }
        this.f15980p.put(str, str2);
    }

    public X0() {
        this(new io.sentry.protocol.t((UUID) null));
    }
}
