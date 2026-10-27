package com.google.zxing.qrcode.encoder;

import com.bydux.api.common.tools.util.ListUtils;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class MinimalEncoder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4217a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f4218b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final r6.c f4219c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ErrorCorrectionLevel f4220d;

    public enum VersionSize {
        SMALL("version 1-9"),
        MEDIUM("version 10-26"),
        LARGE("version 27-40");

        private final String description;

        VersionSize(String str) {
            this.description = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.description;
        }
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4221a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f4222b;

        static {
            int[] iArr = new int[Mode.values().length];
            f4222b = iArr;
            try {
                iArr[Mode.KANJI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4222b[Mode.ALPHANUMERIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4222b[Mode.NUMERIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4222b[Mode.BYTE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4222b[Mode.ECI.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[VersionSize.values().length];
            f4221a = iArr2;
            try {
                iArr2[VersionSize.SMALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f4221a[VersionSize.MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f4221a[VersionSize.LARGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Mode f4223a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f4224b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f4225c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f4226d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final b f4227e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final int f4228f;

        public /* synthetic */ b(MinimalEncoder minimalEncoder, Mode mode, int i8, int i9, int i10, b bVar, u6.a aVar, a aVar2) {
            this(mode, i8, i9, i10, bVar, aVar);
        }

        public b(Mode mode, int i8, int i9, int i10, b bVar, u6.a aVar) {
            this.f4223a = mode;
            this.f4224b = i8;
            Mode mode2 = Mode.BYTE;
            int i11 = (mode == mode2 || bVar == null) ? i9 : bVar.f4225c;
            this.f4225c = i11;
            this.f4226d = i10;
            this.f4227e = bVar;
            boolean z7 = false;
            int characterCountBits = bVar != null ? bVar.f4228f : 0;
            if ((mode == mode2 && bVar == null && i11 != 0) || (bVar != null && i11 != bVar.f4225c)) {
                z7 = true;
            }
            characterCountBits = (bVar == null || mode != bVar.f4223a || z7) ? characterCountBits + mode.getCharacterCountBits(aVar) + 4 : characterCountBits;
            int i12 = a.f4222b[mode.ordinal()];
            if (i12 == 1) {
                characterCountBits += 13;
            } else if (i12 == 2) {
                characterCountBits += i10 == 1 ? 6 : 11;
            } else if (i12 == 3) {
                characterCountBits += i10 != 1 ? i10 == 2 ? 7 : 10 : 4;
            } else if (i12 == 4) {
                characterCountBits += MinimalEncoder.this.f4219c.b(MinimalEncoder.this.f4217a.substring(i8, i10 + i8), i9).length * 8;
                if (z7) {
                    characterCountBits += 12;
                }
            }
            this.f4228f = characterCountBits;
        }
    }

    public final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<a> f4230a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final u6.a f4231b;

        public final class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final Mode f4233a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final int f4234b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final int f4235c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final int f4236d;

            public a(Mode mode, int i8, int i9, int i10) {
                this.f4233a = mode;
                this.f4234b = i8;
                this.f4235c = i9;
                this.f4236d = i10;
            }

            public final void d(r6.a aVar) throws WriterException {
                aVar.c(this.f4233a.getBits(), 4);
                if (this.f4236d > 0) {
                    aVar.c(e(), this.f4233a.getCharacterCountBits(c.this.f4231b));
                }
                if (this.f4233a == Mode.ECI) {
                    aVar.c(MinimalEncoder.this.f4219c.d(this.f4235c), 8);
                } else if (this.f4236d > 0) {
                    String str = MinimalEncoder.this.f4217a;
                    int i8 = this.f4234b;
                    v6.c.c(str.substring(i8, this.f4236d + i8), this.f4233a, aVar, MinimalEncoder.this.f4219c.c(this.f4235c));
                }
            }

            public final int e() {
                if (this.f4233a != Mode.BYTE) {
                    return this.f4236d;
                }
                r6.c cVar = MinimalEncoder.this.f4219c;
                String str = MinimalEncoder.this.f4217a;
                int i8 = this.f4234b;
                return cVar.b(str.substring(i8, this.f4236d + i8), this.f4235c).length;
            }

            public final int f(u6.a aVar) {
                int iE = 4;
                int characterCountBits = this.f4233a.getCharacterCountBits(aVar) + 4;
                int i8 = a.f4222b[this.f4233a.ordinal()];
                if (i8 != 1) {
                    if (i8 == 2) {
                        int i9 = this.f4236d;
                        return characterCountBits + ((i9 / 2) * 11) + (i9 % 2 == 1 ? 6 : 0);
                    }
                    if (i8 == 3) {
                        int i10 = this.f4236d;
                        characterCountBits += (i10 / 3) * 10;
                        int i11 = i10 % 3;
                        if (i11 != 1) {
                            iE = i11 == 2 ? 7 : 0;
                        }
                    } else {
                        if (i8 != 4) {
                            return i8 != 5 ? characterCountBits : characterCountBits + 8;
                        }
                        iE = e() * 8;
                    }
                } else {
                    iE = this.f4236d * 13;
                }
                return characterCountBits + iE;
            }

            public final String g(String str) {
                StringBuilder sb = new StringBuilder();
                for (int i8 = 0; i8 < str.length(); i8++) {
                    if (str.charAt(i8) < ' ' || str.charAt(i8) > '~') {
                        sb.append('.');
                    } else {
                        sb.append(str.charAt(i8));
                    }
                }
                return sb.toString();
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(this.f4233a);
                sb.append('(');
                if (this.f4233a == Mode.ECI) {
                    sb.append(MinimalEncoder.this.f4219c.c(this.f4235c).displayName());
                } else {
                    String str = MinimalEncoder.this.f4217a;
                    int i8 = this.f4234b;
                    sb.append(g(str.substring(i8, this.f4236d + i8)));
                }
                sb.append(')');
                return sb.toString();
            }
        }

        public c(u6.a aVar, b bVar) {
            int i8;
            int i9;
            int i10 = 0;
            boolean z7 = false;
            while (true) {
                i8 = 1;
                if (bVar == null) {
                    break;
                }
                int i11 = i10 + bVar.f4226d;
                b bVar2 = bVar.f4227e;
                boolean z8 = (bVar.f4223a == Mode.BYTE && bVar2 == null && bVar.f4225c != 0) || !(bVar2 == null || bVar.f4225c == bVar2.f4225c);
                z7 = z8 ? true : z7;
                if (bVar2 == null || bVar2.f4223a != bVar.f4223a || z8) {
                    this.f4230a.add(0, new a(bVar.f4223a, bVar.f4224b, bVar.f4225c, i11));
                    i11 = 0;
                }
                if (z8) {
                    this.f4230a.add(0, new a(Mode.ECI, bVar.f4224b, bVar.f4225c, 0));
                }
                bVar = bVar2;
                i10 = i11;
            }
            if (MinimalEncoder.this.f4218b) {
                a aVar2 = this.f4230a.get(0);
                if (aVar2 != null) {
                    Mode mode = aVar2.f4233a;
                    Mode mode2 = Mode.ECI;
                    if (mode != mode2 && z7) {
                        this.f4230a.add(0, new a(mode2, 0, 0, 0));
                    }
                }
                this.f4230a.add(this.f4230a.get(0).f4233a == Mode.ECI ? 1 : 0, new a(Mode.FNC1_FIRST_POSITION, 0, 0, 0));
            }
            int iF = aVar.f();
            int i12 = a.f4221a[MinimalEncoder.m(aVar).ordinal()];
            if (i12 == 1) {
                i9 = 9;
            } else if (i12 != 2) {
                i8 = 27;
                i9 = 40;
            } else {
                i8 = 10;
                i9 = 26;
            }
            int iD = d(aVar);
            while (iF < i9 && !v6.c.v(iD, u6.a.e(iF), MinimalEncoder.this.f4220d)) {
                iF++;
            }
            while (iF > i8 && v6.c.v(iD, u6.a.e(iF - 1), MinimalEncoder.this.f4220d)) {
                iF--;
            }
            this.f4231b = u6.a.e(iF);
        }

        public void b(r6.a aVar) throws WriterException {
            Iterator<a> it = this.f4230a.iterator();
            while (it.hasNext()) {
                it.next().d(aVar);
            }
        }

        public int c() {
            return d(this.f4231b);
        }

        public final int d(u6.a aVar) {
            Iterator<a> it = this.f4230a.iterator();
            int iF = 0;
            while (it.hasNext()) {
                iF += it.next().f(aVar);
            }
            return iF;
        }

        public u6.a e() {
            return this.f4231b;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            a aVar = null;
            for (a aVar2 : this.f4230a) {
                if (aVar != null) {
                    sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
                }
                sb.append(aVar2.toString());
                aVar = aVar2;
            }
            return sb.toString();
        }
    }

    public MinimalEncoder(String str, Charset charset, boolean z7, ErrorCorrectionLevel errorCorrectionLevel) {
        this.f4217a = str;
        this.f4218b = z7;
        this.f4219c = new r6.c(str, charset, -1);
        this.f4220d = errorCorrectionLevel;
    }

    public static c i(String str, u6.a aVar, Charset charset, boolean z7, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        return new MinimalEncoder(str, charset, z7, errorCorrectionLevel).h(aVar);
    }

    public static int k(Mode mode) {
        int i8;
        if (mode == null || (i8 = a.f4222b[mode.ordinal()]) == 1) {
            return 0;
        }
        if (i8 == 2) {
            return 1;
        }
        if (i8 == 3) {
            return 2;
        }
        if (i8 == 4) {
            return 3;
        }
        throw new IllegalStateException("Illegal mode " + mode);
    }

    public static u6.a l(VersionSize versionSize) {
        int i8 = a.f4221a[versionSize.ordinal()];
        return i8 != 1 ? i8 != 2 ? u6.a.e(40) : u6.a.e(26) : u6.a.e(9);
    }

    public static VersionSize m(u6.a aVar) {
        return aVar.f() <= 9 ? VersionSize.SMALL : aVar.f() <= 26 ? VersionSize.MEDIUM : VersionSize.LARGE;
    }

    public static boolean n(char c8) {
        return v6.c.p(c8) != -1;
    }

    public static boolean o(char c8) {
        return v6.c.s(String.valueOf(c8));
    }

    public static boolean p(char c8) {
        return c8 >= '0' && c8 <= '9';
    }

    public void e(b[][][] bVarArr, int i8, b bVar) {
        b[] bVarArr2 = bVarArr[i8 + bVar.f4226d][bVar.f4225c];
        int iK = k(bVar.f4223a);
        if (bVarArr2[iK] == null || bVarArr2[iK].f4228f > bVar.f4228f) {
            bVarArr2[iK] = bVar;
        }
    }

    public void f(u6.a aVar, b[][][] bVarArr, int i8, b bVar) {
        int i9;
        int iF = this.f4219c.f();
        int iE = this.f4219c.e();
        if (iE < 0 || !this.f4219c.a(this.f4217a.charAt(i8), iE)) {
            iE = 0;
        } else {
            iF = iE + 1;
        }
        int i10 = iF;
        for (int i11 = iE; i11 < i10; i11++) {
            if (this.f4219c.a(this.f4217a.charAt(i8), i11)) {
                e(bVarArr, i8, new b(this, Mode.BYTE, i8, i11, 1, bVar, aVar, null));
            }
        }
        Mode mode = Mode.KANJI;
        if (g(mode, this.f4217a.charAt(i8))) {
            e(bVarArr, i8, new b(this, mode, i8, 0, 1, bVar, aVar, null));
        }
        int length = this.f4217a.length();
        Mode mode2 = Mode.ALPHANUMERIC;
        if (g(mode2, this.f4217a.charAt(i8))) {
            int i12 = i8 + 1;
            e(bVarArr, i8, new b(this, mode2, i8, 0, (i12 >= length || !g(mode2, this.f4217a.charAt(i12))) ? 1 : 2, bVar, aVar, null));
        }
        Mode mode3 = Mode.NUMERIC;
        if (g(mode3, this.f4217a.charAt(i8))) {
            int i13 = 0;
            int i14 = i8 + 1;
            if (i14 >= length || !g(mode3, this.f4217a.charAt(i14))) {
                i9 = 1;
            } else {
                int i15 = i8 + 2;
                i9 = (i15 >= length || !g(mode3, this.f4217a.charAt(i15))) ? 2 : 3;
            }
            e(bVarArr, i8, new b(this, mode3, i8, i13, i9, bVar, aVar, null));
        }
    }

    public boolean g(Mode mode, char c8) {
        int i8 = a.f4222b[mode.ordinal()];
        return i8 != 1 ? i8 != 2 ? i8 != 3 ? i8 == 4 : p(c8) : n(c8) : o(c8);
    }

    public c h(u6.a aVar) throws WriterException {
        if (aVar != null) {
            c cVarJ = j(aVar);
            if (v6.c.v(cVarJ.c(), l(m(cVarJ.e())), this.f4220d)) {
                return cVarJ;
            }
            throw new WriterException("Data too big for version" + aVar);
        }
        u6.a[] aVarArr = {l(VersionSize.SMALL), l(VersionSize.MEDIUM), l(VersionSize.LARGE)};
        c[] cVarArr = {j(aVarArr[0]), j(aVarArr[1]), j(aVarArr[2])};
        int i8 = Integer.MAX_VALUE;
        int i9 = -1;
        for (int i10 = 0; i10 < 3; i10++) {
            int iC = cVarArr[i10].c();
            if (v6.c.v(iC, aVarArr[i10], this.f4220d) && iC < i8) {
                i9 = i10;
                i8 = iC;
            }
        }
        if (i9 >= 0) {
            return cVarArr[i9];
        }
        throw new WriterException("Data too big for any version");
    }

    public c j(u6.a aVar) throws WriterException {
        int length = this.f4217a.length();
        b[][][] bVarArr = (b[][][]) Array.newInstance((Class<?>) b.class, length + 1, this.f4219c.f(), 4);
        f(aVar, bVarArr, 0, null);
        for (int i8 = 1; i8 <= length; i8++) {
            for (int i9 = 0; i9 < this.f4219c.f(); i9++) {
                for (int i10 = 0; i10 < 4; i10++) {
                    if (bVarArr[i8][i9][i10] != null && i8 < length) {
                        f(aVar, bVarArr, i8, bVarArr[i8][i9][i10]);
                    }
                }
            }
        }
        int i11 = Integer.MAX_VALUE;
        int i12 = -1;
        int i13 = -1;
        for (int i14 = 0; i14 < this.f4219c.f(); i14++) {
            for (int i15 = 0; i15 < 4; i15++) {
                if (bVarArr[length][i14][i15] != null) {
                    b bVar = bVarArr[length][i14][i15];
                    if (bVar.f4228f < i11) {
                        i11 = bVar.f4228f;
                        i12 = i14;
                        i13 = i15;
                    }
                }
            }
        }
        if (i12 >= 0) {
            return new c(aVar, bVarArr[length][i12][i13]);
        }
        throw new WriterException("Internal error: failed to encode \"" + this.f4217a + "\"");
    }
}
