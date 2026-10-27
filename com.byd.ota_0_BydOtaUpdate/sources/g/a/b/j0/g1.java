package g.a.b.j0;

import java.util.Enumeration;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class g1 implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Hashtable f5026c;

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Hashtable f5027a = new Hashtable();

        public b() {
        }

        public b(Hashtable hashtable) {
            Enumeration enumerationKeys = hashtable.keys();
            while (enumerationKeys.hasMoreElements()) {
                Integer num = (Integer) enumerationKeys.nextElement();
                this.f5027a.put(num, hashtable.get(num));
            }
        }

        public g1 a() {
            return new g1(this.f5027a);
        }

        public b b(int i2, byte[] bArr) {
            if (bArr == null) {
                throw new IllegalArgumentException("Parameter value must not be null.");
            }
            if (i2 != 0 && (i2 <= 4 || i2 >= 63 || i2 == 48)) {
                throw new IllegalArgumentException("Parameter types must be in the range 0,5..47,49..62.");
            }
            if (i2 == 4) {
                throw new IllegalArgumentException("Parameter type 4 is reserved for internal use.");
            }
            this.f5027a.put(g.a.f.d.b(i2), bArr);
            return this;
        }

        public b c(byte[] bArr) {
            return b(0, bArr);
        }
    }

    public g1() {
        this(new Hashtable());
    }

    public g1(Hashtable hashtable) {
        this.f5026c = hashtable;
    }

    public byte[] a() {
        return (byte[]) this.f5026c.get(g.a.f.d.b(0));
    }

    public Hashtable b() {
        return this.f5026c;
    }
}
