package com.google.protobuf;

import defpackage.lk;

/* JADX INFO: compiled from: LazyFieldLite.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 {
    public lk a;
    public final s b;
    public volatile r0 c;
    public volatile lk d;

    static {
        s.a();
    }

    public f0() {
    }

    public f0(lk lkVar, s sVar) {
        if (sVar == null) {
            throw new NullPointerException("found null ExtensionRegistry");
        }
        if (lkVar == null) {
            throw new NullPointerException("found null ByteString");
        }
        this.b = sVar;
        this.a = lkVar;
    }

    public final r0 a(r0 r0Var) {
        if (this.c == null) {
            synchronized (this) {
                if (this.c == null) {
                    try {
                        if (this.a != null) {
                            this.c = r0Var.u().a(this.a, this.b);
                            this.d = this.a;
                        } else {
                            this.c = r0Var;
                            this.d = lk.f;
                        }
                    } catch (d0 unused) {
                        this.c = r0Var;
                        this.d = lk.f;
                    }
                }
            }
        }
        return this.c;
    }

    public final lk b() {
        if (this.d != null) {
            return this.d;
        }
        lk lkVar = this.a;
        if (lkVar != null) {
            return lkVar;
        }
        synchronized (this) {
            if (this.d != null) {
                return this.d;
            }
            if (this.c == null) {
                this.d = lk.f;
            } else {
                this.d = this.c.l();
            }
            return this.d;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f0)) {
            return false;
        }
        f0 f0Var = (f0) obj;
        r0 r0Var = this.c;
        r0 r0Var2 = f0Var.c;
        return (r0Var == null && r0Var2 == null) ? b().equals(f0Var.b()) : (r0Var == null || r0Var2 == null) ? r0Var != null ? r0Var.equals(f0Var.a(r0Var.b())) : a(r0Var2.b()).equals(r0Var2) : r0Var.equals(r0Var2);
    }

    public int hashCode() {
        return 1;
    }
}
