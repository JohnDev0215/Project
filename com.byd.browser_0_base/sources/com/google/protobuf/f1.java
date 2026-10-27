package com.google.protobuf;

import defpackage.zg1;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: compiled from: StructuralMessageInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class f1 implements zg1 {
    public final int a;
    public final boolean b;
    public final int[] c;
    public final w[] d;
    public final r0 e;

    /* JADX INFO: compiled from: StructuralMessageInfo.java */
    public static final class a {
        public final ArrayList a;
        public int b;
        public boolean c;
        public boolean d;
        public int[] e = null;
        public Object f;

        public a(int i) {
            this.a = new ArrayList(i);
        }

        public final f1 a() {
            if (this.c) {
                throw new IllegalStateException("Builder can only build once");
            }
            if (this.b == 0) {
                throw new IllegalStateException("Must specify a proto syntax");
            }
            this.c = true;
            ArrayList arrayList = this.a;
            Collections.sort(arrayList);
            return new f1(this.b, this.d, this.e, (w[]) arrayList.toArray(new w[0]), this.f);
        }

        public final void b(w wVar) {
            if (this.c) {
                throw new IllegalStateException("Builder can only build once");
            }
            this.a.add(wVar);
        }
    }

    public f1(int i, boolean z, int[] iArr, w[] wVarArr, Object obj) {
        this.a = i;
        this.b = z;
        this.c = iArr;
        this.d = wVarArr;
        Charset charset = c0.a;
        if (obj == null) {
            throw new NullPointerException("defaultInstance");
        }
        this.e = (r0) obj;
    }

    @Override // defpackage.zg1
    public final boolean a() {
        return this.b;
    }

    @Override // defpackage.zg1
    public final r0 b() {
        return this.e;
    }

    @Override // defpackage.zg1
    public final int c() {
        return this.a;
    }
}
