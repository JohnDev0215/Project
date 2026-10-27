package androidx.datastore.preferences.protobuf;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0953p {
    public static final /* synthetic */ int c = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0938a0 f11875a = new C0938a0(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f11876b;

    static {
        new C0953p(0);
    }

    public C0953p() {
    }

    public static void b(C0948k c0948k, z0 z0Var, int i7, Object obj) throws IOException {
        if (z0Var == z0.o) {
            c0948k.X(i7, 3);
            ((AbstractC0937a) obj).b(c0948k);
            c0948k.X(i7, 4);
            return;
        }
        c0948k.X(i7, z0Var.f11904m);
        switch (z0Var.ordinal()) {
            case 0:
                c0948k.S(Double.doubleToRawLongBits(((Double) obj).doubleValue()));
                break;
            case 1:
                c0948k.Q(Float.floatToRawIntBits(((Float) obj).floatValue()));
                break;
            case 2:
                c0948k.b0(((Long) obj).longValue());
                break;
            case 3:
                c0948k.b0(((Long) obj).longValue());
                break;
            case 4:
                c0948k.U(((Integer) obj).intValue());
                break;
            case 5:
                c0948k.S(((Long) obj).longValue());
                break;
            case 6:
                c0948k.Q(((Integer) obj).intValue());
                break;
            case 7:
                c0948k.K(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
                break;
            case 8:
                if (!(obj instanceof C0944g)) {
                    c0948k.W((String) obj);
                } else {
                    c0948k.O((C0944g) obj);
                }
                break;
            case 9:
                ((AbstractC0937a) obj).b(c0948k);
                break;
            case 10:
                AbstractC0937a abstractC0937a = (AbstractC0937a) obj;
                c0948k.getClass();
                c0948k.Z(((AbstractC0957u) abstractC0937a).a(null));
                abstractC0937a.b(c0948k);
                break;
            case 11:
                if (!(obj instanceof C0944g)) {
                    byte[] bArr = (byte[]) obj;
                    int length = bArr.length;
                    c0948k.Z(length);
                    c0948k.L(bArr, 0, length);
                } else {
                    c0948k.O((C0944g) obj);
                }
                break;
            case 12:
                c0948k.Z(((Integer) obj).intValue());
                break;
            case 13:
                c0948k.U(((Integer) obj).intValue());
                break;
            case 14:
                c0948k.Q(((Integer) obj).intValue());
                break;
            case 15:
                c0948k.S(((Long) obj).longValue());
                break;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                c0948k.Z((iIntValue >> 31) ^ (iIntValue << 1));
                break;
            case 17:
                long jLongValue = ((Long) obj).longValue();
                c0948k.b0((jLongValue >> 63) ^ (jLongValue << 1));
                break;
        }
    }

    public final void a() {
        C0938a0 c0938a0;
        if (this.f11876b) {
            return;
        }
        int i7 = 0;
        while (true) {
            c0938a0 = this.f11875a;
            if (i7 >= c0938a0.f11826m.size()) {
                break;
            }
            Map.Entry entryD = c0938a0.d(i7);
            if (entryD.getValue() instanceof AbstractC0957u) {
                AbstractC0957u abstractC0957u = (AbstractC0957u) entryD.getValue();
                abstractC0957u.getClass();
                V v10 = V.c;
                v10.getClass();
                v10.a(abstractC0957u.getClass()).d(abstractC0957u);
                abstractC0957u.j();
            }
            i7++;
        }
        if (!c0938a0.o) {
            if (c0938a0.f11826m.size() > 0) {
                c0938a0.d(0).getKey().getClass();
                throw new ClassCastException();
            }
            Iterator it = c0938a0.e().iterator();
            if (it.hasNext()) {
                ((Map.Entry) it.next()).getKey().getClass();
                throw new ClassCastException();
            }
        }
        if (!c0938a0.o) {
            c0938a0.f11827n = c0938a0.f11827n.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(c0938a0.f11827n);
            c0938a0.f11829q = c0938a0.f11829q.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(c0938a0.f11829q);
            c0938a0.o = true;
        }
        this.f11876b = true;
    }

    public final Object clone() {
        C0953p c0953p = new C0953p();
        C0938a0 c0938a0 = this.f11875a;
        if (c0938a0.f11826m.size() > 0) {
            Map.Entry entryD = c0938a0.d(0);
            if (entryD.getKey() != null) {
                throw new ClassCastException();
            }
            entryD.getValue();
            throw null;
        }
        Iterator it = c0938a0.e().iterator();
        if (!it.hasNext()) {
            return c0953p;
        }
        Map.Entry entry = (Map.Entry) it.next();
        if (entry.getKey() != null) {
            throw new ClassCastException();
        }
        entry.getValue();
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0953p) {
            return this.f11875a.equals(((C0953p) obj).f11875a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f11875a.hashCode();
    }

    public C0953p(int i7) {
        a();
        a();
    }
}
