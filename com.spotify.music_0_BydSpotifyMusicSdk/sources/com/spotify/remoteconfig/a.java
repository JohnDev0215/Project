package com.spotify.remoteconfig;

import p.bj;
import p.jx;
import p.my1;
import p.py0;
import p.v81;

/* JADX INFO: loaded from: classes.dex */
public final class a implements my1 {
    public final boolean a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final jx j;
    public final v81 k = kotlin.a.c(new py0() { // from class: com.spotify.remoteconfig.AndroidAutomotiveAppProperties$dataProps$2
        {
            super(0);
        }

        @Override // p.py0
        public final Object invoke() {
            a aVar = this.this$0;
            jx jxVar = aVar.j;
            if (jxVar != null) {
                return (a) jxVar.b(new bj(aVar, 2));
            }
            return null;
        }
    });

    public a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, jx jxVar) {
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.d = z4;
        this.e = z5;
        this.f = z6;
        this.g = z7;
        this.h = z8;
        this.i = z9;
        this.j = jxVar;
    }

    public final a a() {
        return (a) this.k.getValue();
    }

    public final boolean b() {
        a aVarA = a();
        return aVarA != null ? aVarA.b() : this.a;
    }

    public final boolean c() {
        a aVarA = a();
        return aVarA != null ? aVarA.c() : this.b;
    }

    public final boolean d() {
        a aVarA = a();
        return aVarA != null ? aVarA.d() : this.c;
    }

    public final boolean e() {
        a aVarA = a();
        return aVarA != null ? aVarA.e() : this.d;
    }

    public final boolean f() {
        a aVarA = a();
        return aVarA != null ? aVarA.f() : this.e;
    }

    public final boolean g() {
        a aVarA = a();
        return aVarA != null ? aVarA.g() : this.f;
    }

    public final boolean h() {
        a aVarA = a();
        return aVarA != null ? aVarA.h() : this.g;
    }

    public final boolean i() {
        a aVarA = a();
        return aVarA != null ? aVarA.i() : this.h;
    }

    public final boolean j() {
        a aVarA = a();
        return aVarA != null ? aVarA.j() : this.i;
    }
}
