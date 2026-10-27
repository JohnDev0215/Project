package defpackage;

import java.nio.ByteBuffer;
import java.util.List;
import org.chromium.base.BundleUtils;

/* JADX INFO: compiled from: FrameMetrics.java */
/* JADX INFO: loaded from: classes.dex */
public class gq0 implements o01 {
    public Object a;
    public final Object b;
    public Object c;

    public /* synthetic */ gq0(Object obj, Object obj2, Object obj3) {
        this.a = obj;
        this.b = obj2;
        this.c = obj3;
    }

    public wi2 a() {
        if (((wi2) this.c) == null) {
            this.c = new wi2(this, new yg1(this));
        }
        return (wi2) this.c;
    }

    @Override // defpackage.o01
    public final void b(String str, p01 p01Var) {
        e().b(str, p01Var);
    }

    @Override // defpackage.o01
    public final void c(String str) {
        e().c(str);
    }

    @Override // defpackage.o01
    public final boolean d(String str) {
        if (BundleUtils.d(str)) {
            return true;
        }
        try {
            ku2 ku2VarT = ku2.t();
            try {
                xz.a.getClassLoader().loadClass((String) this.c);
                ku2VarT.close();
                return true;
            } catch (Throwable th) {
                try {
                    ku2VarT.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (ClassNotFoundException unused2) {
            return false;
        }
    }

    public final o01 e() {
        if (((o01) this.a) == null) {
            ((n3) this.b).getClass();
            Object obj = BundleUtils.a;
            this.a = new a8();
        }
        return (o01) this.a;
    }

    public /* synthetic */ gq0() {
        this.a = new Long[0];
        this.b = new Long[0];
        this.c = new Integer[0];
    }

    public /* synthetic */ gq0(String str) {
        n3 n3Var = new n3(0);
        this.c = str;
        this.b = n3Var;
    }

    public /* synthetic */ gq0(ByteBuffer byteBuffer, List list) {
        this.a = byteBuffer;
        this.b = list;
    }
}
