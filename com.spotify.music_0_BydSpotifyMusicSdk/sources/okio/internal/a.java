package okio.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Pair;
import p.b81;
import p.c81;
import p.de2;
import p.f;
import p.hu0;
import p.lu;
import p.nu;
import p.os1;
import p.ou;
import p.pd2;
import p.py0;
import p.u11;
import p.v03;
import p.v81;
import p.x50;

/* JADX INFO: loaded from: classes.dex */
public final class a extends hu0 {
    public static final os1 e;
    public final ClassLoader b;
    public final hu0 c;
    public final v81 d;

    static {
        String str = os1.e;
        e = u11.z("/", false);
    }

    public a(ClassLoader classLoader) {
        c81 c81Var = hu0.a;
        v03.j(c81Var, "systemFileSystem");
        this.b = classLoader;
        this.c = c81Var;
        this.d = kotlin.a.c(new py0() { // from class: okio.internal.ResourceFileSystem$roots$2
            {
                super(0);
            }

            /* JADX WARN: Code restructure failed: missing block: B:100:0x027c, code lost:
            
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:102:0x027e, code lost:
            
                p.ux2.e(r4, r0);
             */
            /* JADX WARN: Code restructure failed: missing block: B:103:0x0281, code lost:
            
                throw r0;
             */
            /* JADX WARN: Code restructure failed: missing block: B:107:0x0289, code lost:
            
                throw new java.io.IOException("unsupported zip: spanned");
             */
            /* JADX WARN: Code restructure failed: missing block: B:27:0x00fa, code lost:
            
                r9 = r4.i() & 65535;
                r13 = r4.i() & 65535;
                r14 = r4.i() & 65535;
             */
            /* JADX WARN: Code restructure failed: missing block: B:28:0x0111, code lost:
            
                r24 = r0;
             */
            /* JADX WARN: Code restructure failed: missing block: B:29:0x011c, code lost:
            
                if (r14 != (r4.i() & 65535)) goto L146;
             */
            /* JADX WARN: Code restructure failed: missing block: B:30:0x011e, code lost:
            
                if (r9 != 0) goto L147;
             */
            /* JADX WARN: Code restructure failed: missing block: B:31:0x0120, code lost:
            
                if (r13 != 0) goto L148;
             */
            /* JADX WARN: Code restructure failed: missing block: B:33:0x0124, code lost:
            
                r4.y(4);
                r21 = ((long) r4.O()) & 4294967295L;
                r0 = r4.i() & 65535;
                r1 = new p.jo2(r14, r21, r0);
                r4.q(r0);
             */
            /* JADX WARN: Code restructure failed: missing block: B:34:0x014a, code lost:
            
                r4.close();
                r10 = r10 - ((long) 20);
             */
            /* JADX WARN: Code restructure failed: missing block: B:35:0x0155, code lost:
            
                if (r10 <= 0) goto L79;
             */
            /* JADX WARN: Code restructure failed: missing block: B:36:0x0157, code lost:
            
                r4 = p.v03.d(r7.i(r10));
             */
            /* JADX WARN: Code restructure failed: missing block: B:38:0x0166, code lost:
            
                if (r4.O() != 117853008) goto L53;
             */
            /* JADX WARN: Code restructure failed: missing block: B:39:0x0168, code lost:
            
                r0 = r4.O();
                r9 = r4.f0();
             */
            /* JADX WARN: Code restructure failed: missing block: B:40:0x0175, code lost:
            
                if (r4.O() != 1) goto L149;
             */
            /* JADX WARN: Code restructure failed: missing block: B:41:0x0177, code lost:
            
                if (r0 != 0) goto L150;
             */
            /* JADX WARN: Code restructure failed: missing block: B:42:0x0179, code lost:
            
                r9 = p.v03.d(r7.i(r9));
             */
            /* JADX WARN: Code restructure failed: missing block: B:43:0x0181, code lost:
            
                r0 = r9.O();
             */
            /* JADX WARN: Code restructure failed: missing block: B:44:0x0188, code lost:
            
                if (r0 != 101075792) goto L151;
             */
            /* JADX WARN: Code restructure failed: missing block: B:45:0x018a, code lost:
            
                r9.y(12);
                r0 = r9.O();
                r10 = r9.O();
                r19 = r9.f0();
             */
            /* JADX WARN: Code restructure failed: missing block: B:46:0x01a1, code lost:
            
                if (r19 != r9.f0()) goto L152;
             */
            /* JADX WARN: Code restructure failed: missing block: B:47:0x01a3, code lost:
            
                if (r0 != 0) goto L153;
             */
            /* JADX WARN: Code restructure failed: missing block: B:48:0x01a5, code lost:
            
                if (r10 != 0) goto L154;
             */
            /* JADX WARN: Code restructure failed: missing block: B:49:0x01a7, code lost:
            
                r9.y(8);
                r0 = new p.jo2(r19, r9.f0(), r1.c);
             */
            /* JADX WARN: Code restructure failed: missing block: B:51:0x01bc, code lost:
            
                p.ux2.e(r9, null);
             */
            /* JADX WARN: Code restructure failed: missing block: B:52:0x01bf, code lost:
            
                r1 = r0;
             */
            /* JADX WARN: Code restructure failed: missing block: B:54:0x01c2, code lost:
            
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:57:0x01ca, code lost:
            
                throw new java.io.IOException("unsupported zip: spanned");
             */
            /* JADX WARN: Code restructure failed: missing block: B:60:0x01f3, code lost:
            
                throw new java.io.IOException("bad zip: expected " + okio.internal.b.b(101075792) + " but was " + okio.internal.b.b(r0));
             */
            /* JADX WARN: Code restructure failed: missing block: B:61:0x01f4, code lost:
            
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:69:0x0202, code lost:
            
                throw new java.io.IOException("unsupported zip: spanned");
             */
            /* JADX WARN: Code restructure failed: missing block: B:70:0x0203, code lost:
            
                p.ux2.e(r4, null);
             */
            /* JADX WARN: Code restructure failed: missing block: B:79:0x0212, code lost:
            
                r0 = new java.util.ArrayList();
                r4 = p.v03.d(r7.i(r1.b));
             */
            /* JADX WARN: Code restructure failed: missing block: B:80:0x0221, code lost:
            
                r9 = r1.a;
                r12 = 0;
             */
            /* JADX WARN: Code restructure failed: missing block: B:82:0x0227, code lost:
            
                if (r12 >= r9) goto L160;
             */
            /* JADX WARN: Code restructure failed: missing block: B:83:0x0229, code lost:
            
                r11 = okio.internal.b.c(r4);
                r18 = r9;
             */
            /* JADX WARN: Code restructure failed: missing block: B:84:0x0235, code lost:
            
                if (r11.g >= r1.b) goto L155;
             */
            /* JADX WARN: Code restructure failed: missing block: B:86:0x0241, code lost:
            
                if (((java.lang.Boolean) r5.invoke(r11)).booleanValue() == false) goto L161;
             */
            /* JADX WARN: Code restructure failed: missing block: B:87:0x0243, code lost:
            
                r0.add(r11);
             */
            /* JADX WARN: Code restructure failed: missing block: B:88:0x0247, code lost:
            
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:90:0x024a, code lost:
            
                r12 = r12 + 1;
                r9 = r18;
             */
            /* JADX WARN: Code restructure failed: missing block: B:92:0x0257, code lost:
            
                throw new java.io.IOException("bad zip: local file header offset >= central directory offset");
             */
            /* JADX WARN: Code restructure failed: missing block: B:94:0x0259, code lost:
            
                p.ux2.e(r4, null);
                r4 = new p.nz2(r3, r8, okio.internal.b.a(r0));
             */
            /* JADX WARN: Code restructure failed: missing block: B:95:0x0265, code lost:
            
                p.ux2.e(r7, null);
                r9 = new kotlin.Pair(r4, okio.internal.a.e);
             */
            /* JADX WARN: Code restructure failed: missing block: B:99:0x027b, code lost:
            
                throw r0;
             */
            /* JADX WARN: Finally extract failed */
            @Override // p.py0
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invoke() throws java.io.IOException {
                /*
                    Method dump skipped, instruction units count: 719
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: okio.internal.ResourceFileSystem$roots$2.invoke():java.lang.Object");
            }
        });
    }

    public static String m(os1 os1Var) {
        os1 os1Var2 = e;
        os1Var2.getClass();
        v03.j(os1Var, "child");
        return f.b(os1Var2, os1Var, true).c(os1Var2).c.s();
    }

    @Override // p.hu0
    public final pd2 a(os1 os1Var) throws IOException {
        throw new IOException(this + " is read-only");
    }

    @Override // p.hu0
    public final void b(os1 os1Var, os1 os1Var2) throws IOException {
        v03.j(os1Var, "source");
        v03.j(os1Var2, "target");
        throw new IOException(this + " is read-only");
    }

    @Override // p.hu0
    public final void c(os1 os1Var) throws IOException {
        throw new IOException(this + " is read-only");
    }

    @Override // p.hu0
    public final void d(os1 os1Var) throws IOException {
        v03.j(os1Var, "path");
        throw new IOException(this + " is read-only");
    }

    @Override // p.hu0
    public final List g(os1 os1Var) throws FileNotFoundException {
        v03.j(os1Var, "dir");
        String strM = m(os1Var);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        boolean z = false;
        for (Pair pair : (List) this.d.getValue()) {
            hu0 hu0Var = (hu0) pair.a();
            os1 os1Var2 = (os1) pair.b();
            try {
                List listG = hu0Var.g(os1Var2.d(strM));
                ArrayList<os1> arrayList = new ArrayList();
                for (Object obj : listG) {
                    if (u11.m((os1) obj)) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList(lu.W(arrayList));
                for (os1 os1Var3 : arrayList) {
                    v03.j(os1Var3, "<this>");
                    String strS = os1Var2.c.s();
                    os1 os1Var4 = e;
                    String strReplace = kotlin.text.b.v0(strS, os1Var3.c.s()).replace('\\', '/');
                    v03.i(strReplace, "this as java.lang.String…replace(oldChar, newChar)");
                    arrayList2.add(os1Var4.d(strReplace));
                }
                nu.X(arrayList2, linkedHashSet);
                z = true;
            } catch (IOException unused) {
            }
        }
        if (z) {
            return ou.n0(linkedHashSet);
        }
        throw new FileNotFoundException("file not found: " + os1Var);
    }

    @Override // p.hu0
    public final x50 i(os1 os1Var) {
        v03.j(os1Var, "path");
        if (!u11.m(os1Var)) {
            return null;
        }
        String strM = m(os1Var);
        for (Pair pair : (List) this.d.getValue()) {
            x50 x50VarI = ((hu0) pair.a()).i(((os1) pair.b()).d(strM));
            if (x50VarI != null) {
                return x50VarI;
            }
        }
        return null;
    }

    @Override // p.hu0
    public final b81 j(os1 os1Var) throws FileNotFoundException {
        v03.j(os1Var, "file");
        if (!u11.m(os1Var)) {
            throw new FileNotFoundException("file not found: " + os1Var);
        }
        String strM = m(os1Var);
        for (Pair pair : (List) this.d.getValue()) {
            try {
                return ((hu0) pair.a()).j(((os1) pair.b()).d(strM));
            } catch (FileNotFoundException unused) {
            }
        }
        throw new FileNotFoundException("file not found: " + os1Var);
    }

    @Override // p.hu0
    public final pd2 k(os1 os1Var) throws IOException {
        v03.j(os1Var, "file");
        throw new IOException(this + " is read-only");
    }

    @Override // p.hu0
    public final de2 l(os1 os1Var) throws FileNotFoundException {
        v03.j(os1Var, "file");
        if (!u11.m(os1Var)) {
            throw new FileNotFoundException("file not found: " + os1Var);
        }
        os1 os1Var2 = e;
        os1Var2.getClass();
        InputStream resourceAsStream = this.b.getResourceAsStream(f.b(os1Var2, os1Var, false).c(os1Var2).c.s());
        if (resourceAsStream != null) {
            return v03.L(resourceAsStream);
        }
        throw new FileNotFoundException("file not found: " + os1Var);
    }
}
