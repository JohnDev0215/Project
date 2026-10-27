package g.a.e.b.e;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class t {

    public static class a extends ObjectInputStream {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Set f5694c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Class f5695d;
        public boolean s;

        static {
            HashSet hashSet = new HashSet();
            f5694c = hashSet;
            hashSet.add("java.util.TreeMap");
            hashSet.add("java.lang.Integer");
            hashSet.add("java.lang.Number");
            hashSet.add("org.bouncycastle.pqc.crypto.xmss.BDS");
            hashSet.add("java.util.ArrayList");
            hashSet.add("org.bouncycastle.pqc.crypto.xmss.XMSSNode");
            hashSet.add("[B");
            hashSet.add("java.util.LinkedList");
            hashSet.add("java.util.Stack");
            hashSet.add("java.util.Vector");
            hashSet.add("[Ljava.lang.Object;");
            hashSet.add("org.bouncycastle.pqc.crypto.xmss.BDSTreeHash");
        }

        public a(Class cls, InputStream inputStream) throws IOException {
            super(inputStream);
            this.s = false;
            this.f5695d = cls;
        }

        @Override // java.io.ObjectInputStream
        public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
            if (this.s) {
                if (!f5694c.contains(objectStreamClass.getName())) {
                    throw new InvalidClassException("unexpected class: ", objectStreamClass.getName());
                }
            } else {
                if (!objectStreamClass.getName().equals(this.f5695d.getName())) {
                    throw new InvalidClassException("unexpected class: ", objectStreamClass.getName());
                }
                this.s = true;
            }
            return super.resolveClass(objectStreamClass);
        }
    }

    public static long a(byte[] bArr, int i2, int i3) {
        Objects.requireNonNull(bArr, "in == null");
        long j = 0;
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            j = (j << 8) | ((long) (bArr[i4] & 255));
        }
        return j;
    }

    public static int b(int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (((i2 >> i4) & 1) == 0) {
                return i4;
            }
        }
        return 0;
    }

    public static byte[] c(byte[] bArr) {
        Objects.requireNonNull(bArr, "in == null");
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static byte[][] d(byte[][] bArr) {
        if (k(bArr)) {
            throw new NullPointerException("in has null pointers");
        }
        byte[][] bArr2 = new byte[bArr.length][];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr2[i2] = new byte[bArr[i2].length];
            System.arraycopy(bArr[i2], 0, bArr2[i2], 0, bArr[i2].length);
        }
        return bArr2;
    }

    public static void e(byte[] bArr, byte[] bArr2, int i2) {
        Objects.requireNonNull(bArr, "dst == null");
        Objects.requireNonNull(bArr2, "src == null");
        if (i2 < 0) {
            throw new IllegalArgumentException("offset hast to be >= 0");
        }
        if (bArr2.length + i2 > bArr.length) {
            throw new IllegalArgumentException("src length + offset must not be greater than size of destination");
        }
        for (int i3 = 0; i3 < bArr2.length; i3++) {
            bArr[i2 + i3] = bArr2[i3];
        }
    }

    public static Object f(byte[] bArr, Class cls) throws ClassNotFoundException, IOException {
        a aVar = new a(cls, new ByteArrayInputStream(bArr));
        Object object = aVar.readObject();
        if (aVar.available() != 0) {
            throw new IOException("unexpected data found at end of ObjectInputStream");
        }
        if (cls.isInstance(object)) {
            return object;
        }
        throw new IOException("unexpected class found in ObjectInputStream");
    }

    public static byte[] g(byte[] bArr, int i2, int i3) {
        Objects.requireNonNull(bArr, "src == null");
        if (i2 < 0) {
            throw new IllegalArgumentException("offset hast to be >= 0");
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("length hast to be >= 0");
        }
        if (i2 + i3 > bArr.length) {
            throw new IllegalArgumentException("offset + length must not be greater then size of source array");
        }
        byte[] bArr2 = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            bArr2[i4] = bArr[i2 + i4];
        }
        return bArr2;
    }

    public static int h(g.a.b.n nVar) {
        Objects.requireNonNull(nVar, "digest == null");
        String algorithmName = nVar.getAlgorithmName();
        if (algorithmName.equals("SHAKE128")) {
            return 32;
        }
        if (algorithmName.equals("SHAKE256")) {
            return 64;
        }
        return nVar.getDigestSize();
    }

    public static int i(long j, int i2) {
        return (int) (j & ((1 << i2) - 1));
    }

    public static long j(long j, int i2) {
        return j >> i2;
    }

    public static boolean k(byte[][] bArr) {
        if (bArr == null) {
            return true;
        }
        for (byte[] bArr2 : bArr) {
            if (bArr2 == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean l(int i2, long j) {
        if (j >= 0) {
            return j < (1 << i2);
        }
        throw new IllegalStateException("index must not be negative");
    }

    public static boolean m(long j, int i2, int i3) {
        return j != 0 && (j + 1) % ((long) Math.pow((double) (1 << i2), (double) i3)) == 0;
    }

    public static int n(int i2) {
        int i3 = 0;
        while (true) {
            i2 >>= 1;
            if (i2 == 0) {
                return i3;
            }
            i3++;
        }
    }

    public static byte[] o(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] p(long j, int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            bArr[i3] = (byte) j;
            j >>>= 8;
        }
        return bArr;
    }
}
