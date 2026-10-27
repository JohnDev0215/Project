package io.sentry;

import android.hardware.bydauto.properties.CarSettings;
import h2.AbstractC1347d;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a2 implements InterfaceC1485m0 {
    public final io.sentry.protocol.t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f16004m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f16005n;
    public final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f16006p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final String f16007q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f16008r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final String f16009s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final String f16010t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final io.sentry.protocol.t f16011u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ConcurrentHashMap f16012v;

    public a2(io.sentry.protocol.t tVar, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, io.sentry.protocol.t tVar2) {
        this.l = tVar;
        this.f16004m = str;
        this.f16005n = str2;
        this.o = str3;
        this.f16006p = str4;
        this.f16007q = str5;
        this.f16008r = str6;
        this.f16009s = str7;
        this.f16010t = str8;
        this.f16011u = tVar2;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("trace_id");
        c1489n1.I(k10, this.l);
        c1489n1.w("public_key");
        c1489n1.L(this.f16004m);
        String str = this.f16005n;
        if (str != null) {
            c1489n1.w("release");
            c1489n1.L(str);
        }
        String str2 = this.o;
        if (str2 != null) {
            c1489n1.w("environment");
            c1489n1.L(str2);
        }
        String str3 = this.f16006p;
        if (str3 != null) {
            c1489n1.w(CarSettings.UserTableData.USER_ID);
            c1489n1.L(str3);
        }
        String str4 = this.f16007q;
        if (str4 != null) {
            c1489n1.w("user_segment");
            c1489n1.L(str4);
        }
        String str5 = this.f16008r;
        if (str5 != null) {
            c1489n1.w("transaction");
            c1489n1.L(str5);
        }
        String str6 = this.f16009s;
        if (str6 != null) {
            c1489n1.w("sample_rate");
            c1489n1.L(str6);
        }
        String str7 = this.f16010t;
        if (str7 != null) {
            c1489n1.w("sampled");
            c1489n1.L(str7);
        }
        io.sentry.protocol.t tVar = this.f16011u;
        if (tVar != null) {
            c1489n1.w("replay_id");
            c1489n1.I(k10, tVar);
        }
        ConcurrentHashMap concurrentHashMap = this.f16012v;
        if (concurrentHashMap != null) {
            for (String str8 : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16012v, str8, c1489n1, str8, k10);
            }
        }
        c1489n1.n();
    }
}
