package spi;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import spi.jf;

/* JADX INFO: loaded from: classes2.dex */
public final class oe {

    @NotNull
    public static final ie[] a;

    @NotNull
    public static final Map<ByteString, Integer> b;
    public static final oe c = new oe();

    public static final class a {
        public final List<ie> a;
        public final ul b;

        @JvmField
        @NotNull
        public ie[] c;
        public int d;

        @JvmField
        public int e;

        @JvmField
        public int f;
        public final int g;
        public int h;

        @JvmOverloads
        public a(@NotNull vr vrVar, int i) {
            this(vrVar, i, 0, 4, null);
        }

        @JvmOverloads
        public a(@NotNull vr source, int i, int i2) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.g = i;
            this.h = i2;
            this.a = new ArrayList();
            this.b = (ul) spi.b.c(source);
            this.c = new ie[8];
            this.d = 7;
        }

        public /* synthetic */ a(vr vrVar, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(vrVar, i, (i3 & 4) != 0 ? i : i2);
        }

        public final void a() {
            ArraysKt___ArraysJvmKt.fill$default(this.c, (Object) null, 0, 0, 6, (Object) null);
            this.d = this.c.length - 1;
            this.e = 0;
            this.f = 0;
        }

        public final int b(int i) {
            int i2;
            int i3 = 0;
            if (i > 0) {
                int length = this.c.length;
                while (true) {
                    length--;
                    i2 = this.d;
                    if (length < i2 || i <= 0) {
                        break;
                    }
                    ie ieVar = this.c[length];
                    Intrinsics.checkNotNull(ieVar);
                    int i4 = ieVar.a;
                    i -= i4;
                    this.f -= i4;
                    this.e--;
                    i3++;
                }
                ie[] ieVarArr = this.c;
                System.arraycopy(ieVarArr, i2 + 1, ieVarArr, i2 + 1 + i3, this.e);
                this.d += i3;
            }
            return i3;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0010  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final okio.ByteString c(int r5) throws java.io.IOException {
            /*
                r4 = this;
                r0 = 1
                if (r5 < 0) goto L10
                spi.oe r1 = spi.oe.c
                java.util.Objects.requireNonNull(r1)
                spi.ie[] r1 = spi.oe.a
                int r1 = r1.length
                int r1 = r1 - r0
                if (r5 > r1) goto L10
                r1 = r0
                goto L11
            L10:
                r1 = 0
            L11:
                if (r1 == 0) goto L1d
                spi.oe r0 = spi.oe.c
                java.util.Objects.requireNonNull(r0)
                spi.ie[] r0 = spi.oe.a
                r5 = r0[r5]
                goto L37
            L1d:
                spi.oe r1 = spi.oe.c
                java.util.Objects.requireNonNull(r1)
                spi.ie[] r1 = spi.oe.a
                int r1 = r1.length
                int r1 = r5 - r1
                int r2 = r4.d
                int r2 = r2 + r0
                int r2 = r2 + r1
                if (r2 < 0) goto L3a
                spi.ie[] r1 = r4.c
                int r3 = r1.length
                if (r2 >= r3) goto L3a
                r5 = r1[r2]
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            L37:
                okio.ByteString r5 = r5.b
                return r5
            L3a:
                java.io.IOException r1 = new java.io.IOException
                java.lang.String r2 = "Header index too large "
                java.lang.StringBuilder r2 = spi.e.k(r2)
                int r5 = r5 + r0
                r2.append(r5)
                java.lang.String r5 = r2.toString()
                r1.<init>(r5)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.oe.a.c(int):okio.ByteString");
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<spi.ie>] */
        public final void d(ie ieVar) {
            this.a.add(ieVar);
            int i = ieVar.a;
            int i2 = this.h;
            if (i > i2) {
                a();
                return;
            }
            b((this.f + i) - i2);
            int i3 = this.e + 1;
            ie[] ieVarArr = this.c;
            if (i3 > ieVarArr.length) {
                ie[] ieVarArr2 = new ie[ieVarArr.length * 2];
                System.arraycopy(ieVarArr, 0, ieVarArr2, ieVarArr.length, ieVarArr.length);
                this.d = this.c.length - 1;
                this.c = ieVarArr2;
            }
            int i4 = this.d;
            this.d = i4 - 1;
            this.c[i4] = ieVar;
            this.e++;
            this.f += i;
        }

        @NotNull
        public final ByteString e() throws IOException {
            byte b = this.b.readByte();
            byte[] bArr = uu.a;
            int i = b & 255;
            int i2 = 0;
            boolean z = (i & 128) == 128;
            long jF = f(i, 127);
            if (!z) {
                return this.b.b(jF);
            }
            g2 sink = new g2();
            jf jfVar = jf.d;
            ul source = this.b;
            Objects.requireNonNull(jfVar);
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(sink, "sink");
            jf.a aVar = jf.c;
            int i3 = 0;
            for (long j = 0; j < jF; j++) {
                byte b2 = source.readByte();
                byte[] bArr2 = uu.a;
                i2 = (i2 << 8) | (b2 & 255);
                i3 += 8;
                while (i3 >= 8) {
                    int i4 = i3 - 8;
                    jf.a[] aVarArr = aVar.a;
                    Intrinsics.checkNotNull(aVarArr);
                    aVar = aVarArr[(i2 >>> i4) & 255];
                    Intrinsics.checkNotNull(aVar);
                    if (aVar.a == null) {
                        sink.S(aVar.b);
                        i3 -= aVar.c;
                        aVar = jf.c;
                    } else {
                        i3 = i4;
                    }
                }
            }
            while (i3 > 0) {
                jf.a[] aVarArr2 = aVar.a;
                Intrinsics.checkNotNull(aVarArr2);
                jf.a aVar2 = aVarArr2[(i2 << (8 - i3)) & 255];
                Intrinsics.checkNotNull(aVar2);
                if (aVar2.a != null || aVar2.c > i3) {
                    break;
                }
                sink.S(aVar2.b);
                i3 -= aVar2.c;
                aVar = jf.c;
            }
            return sink.q();
        }

        public final int f(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                byte b = this.b.readByte();
                byte[] bArr = uu.a;
                int i5 = b & 255;
                if ((i5 & 128) == 0) {
                    return i2 + (i5 << i4);
                }
                i2 += (i5 & 127) << i4;
                i4 += 7;
            }
        }
    }

    public static final class b {
        public int a;
        public boolean b;

        @JvmField
        public int c;

        @JvmField
        @NotNull
        public ie[] d;
        public int e;

        @JvmField
        public int f;

        @JvmField
        public int g;
        public final boolean h;
        public final g2 i;

        @JvmOverloads
        public b(int i, @NotNull g2 g2Var) {
            this(i, false, g2Var, 2, null);
        }

        @JvmOverloads
        public b(int i, boolean z, @NotNull g2 out) {
            Intrinsics.checkNotNullParameter(out, "out");
            this.h = z;
            this.i = out;
            this.a = Integer.MAX_VALUE;
            this.c = i;
            this.d = new ie[8];
            this.e = 7;
        }

        public /* synthetic */ b(int i, boolean z, g2 g2Var, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 4096 : i, (i2 & 2) != 0 ? true : z, g2Var);
        }

        @JvmOverloads
        public b(@NotNull g2 g2Var) {
            this(0, false, g2Var, 3, null);
        }

        public final void a() {
            ArraysKt___ArraysJvmKt.fill$default(this.d, (Object) null, 0, 0, 6, (Object) null);
            this.e = this.d.length - 1;
            this.f = 0;
            this.g = 0;
        }

        public final int b(int i) {
            int i2;
            int i3 = 0;
            if (i > 0) {
                int length = this.d.length;
                while (true) {
                    length--;
                    i2 = this.e;
                    if (length < i2 || i <= 0) {
                        break;
                    }
                    ie ieVar = this.d[length];
                    Intrinsics.checkNotNull(ieVar);
                    i -= ieVar.a;
                    int i4 = this.g;
                    ie ieVar2 = this.d[length];
                    Intrinsics.checkNotNull(ieVar2);
                    this.g = i4 - ieVar2.a;
                    this.f--;
                    i3++;
                }
                ie[] ieVarArr = this.d;
                System.arraycopy(ieVarArr, i2 + 1, ieVarArr, i2 + 1 + i3, this.f);
                ie[] ieVarArr2 = this.d;
                int i5 = this.e;
                Arrays.fill(ieVarArr2, i5 + 1, i5 + 1 + i3, (Object) null);
                this.e += i3;
            }
            return i3;
        }

        public final void c(ie ieVar) {
            int i = ieVar.a;
            int i2 = this.c;
            if (i > i2) {
                a();
                return;
            }
            b((this.g + i) - i2);
            int i3 = this.f + 1;
            ie[] ieVarArr = this.d;
            if (i3 > ieVarArr.length) {
                ie[] ieVarArr2 = new ie[ieVarArr.length * 2];
                System.arraycopy(ieVarArr, 0, ieVarArr2, ieVarArr.length, ieVarArr.length);
                this.e = this.d.length - 1;
                this.d = ieVarArr2;
            }
            int i4 = this.e;
            this.e = i4 - 1;
            this.d[i4] = ieVar;
            this.f++;
            this.g += i;
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0098  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void d(@org.jetbrains.annotations.NotNull okio.ByteString r11) throws java.io.IOException {
            /*
                r10 = this;
                java.lang.String r0 = "data"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                boolean r0 = r10.h
                r1 = 127(0x7f, float:1.78E-43)
                r2 = 0
                if (r0 == 0) goto L98
                spi.jf r0 = spi.jf.d
                java.util.Objects.requireNonNull(r0)
                java.lang.String r0 = "bytes"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                int r0 = r11.size()
                r3 = 0
                r5 = r2
                r6 = r3
            L1e:
                if (r5 >= r0) goto L31
                byte r8 = r11.getByte(r5)
                byte[] r9 = spi.uu.a
                r8 = r8 & 255(0xff, float:3.57E-43)
                byte[] r9 = spi.jf.b
                r8 = r9[r8]
                long r8 = (long) r8
                long r6 = r6 + r8
                int r5 = r5 + 1
                goto L1e
            L31:
                r0 = 7
                long r8 = (long) r0
                long r6 = r6 + r8
                r0 = 3
                long r5 = r6 >> r0
                int r0 = (int) r5
                int r5 = r11.size()
                if (r0 >= r5) goto L98
                spi.g2 r0 = new spi.g2
                r0.<init>()
                spi.jf r5 = spi.jf.d
                java.util.Objects.requireNonNull(r5)
                java.lang.String r5 = "source"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r5)
                java.lang.String r5 = "sink"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r5)
                int r5 = r11.size()
                r6 = r3
                r3 = r2
            L58:
                if (r2 >= r5) goto L7e
                byte r4 = r11.getByte(r2)
                byte[] r8 = spi.uu.a
                r4 = r4 & 255(0xff, float:3.57E-43)
                int[] r8 = spi.jf.a
                r8 = r8[r4]
                byte[] r9 = spi.jf.b
                r4 = r9[r4]
                long r6 = r6 << r4
                long r8 = (long) r8
                long r6 = r6 | r8
                int r3 = r3 + r4
            L6e:
                r4 = 8
                if (r3 < r4) goto L7b
                int r3 = r3 + (-8)
                long r8 = r6 >> r3
                int r4 = (int) r8
                r0.i(r4)
                goto L6e
            L7b:
                int r2 = r2 + 1
                goto L58
            L7e:
                if (r3 <= 0) goto L8d
                int r11 = 8 - r3
                long r4 = r6 << r11
                r6 = 255(0xff, double:1.26E-321)
                long r2 = r6 >>> r3
                long r2 = r2 | r4
                int r11 = (int) r2
                r0.i(r11)
            L8d:
                okio.ByteString r11 = r0.q()
                int r0 = r11.size()
                r2 = 128(0x80, float:1.8E-43)
                goto L9c
            L98:
                int r0 = r11.size()
            L9c:
                r10.f(r0, r1, r2)
                spi.g2 r0 = r10.i
                r0.P(r11)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.oe.b.d(okio.ByteString):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x007b  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00cb  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void e(@org.jetbrains.annotations.NotNull java.util.List<spi.ie> r14) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 259
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.oe.b.e(java.util.List):void");
        }

        public final void f(int i, int i2, int i3) {
            int i4;
            g2 g2Var;
            if (i < i2) {
                g2Var = this.i;
                i4 = i | i3;
            } else {
                this.i.S(i3 | i2);
                i4 = i - i2;
                while (i4 >= 128) {
                    this.i.S(128 | (i4 & 127));
                    i4 >>>= 7;
                }
                g2Var = this.i;
            }
            g2Var.S(i4);
        }
    }

    static {
        ie ieVar = new ie(ie.i, "");
        ByteString byteString = ie.f;
        ByteString byteString2 = ie.g;
        ByteString byteString3 = ie.h;
        ByteString byteString4 = ie.e;
        ie[] ieVarArr = {ieVar, new ie(byteString, "GET"), new ie(byteString, "POST"), new ie(byteString2, "/"), new ie(byteString2, "/index.html"), new ie(byteString3, "http"), new ie(byteString3, "https"), new ie(byteString4, "200"), new ie(byteString4, "204"), new ie(byteString4, "206"), new ie(byteString4, "304"), new ie(byteString4, "400"), new ie(byteString4, "404"), new ie(byteString4, "500"), new ie("accept-charset", ""), new ie("accept-encoding", "gzip, deflate"), new ie("accept-language", ""), new ie("accept-ranges", ""), new ie("accept", ""), new ie("access-control-allow-origin", ""), new ie("age", ""), new ie("allow", ""), new ie("authorization", ""), new ie("cache-control", ""), new ie("content-disposition", ""), new ie("content-encoding", ""), new ie("content-language", ""), new ie("content-length", ""), new ie("content-location", ""), new ie("content-range", ""), new ie("content-type", ""), new ie("cookie", ""), new ie("date", ""), new ie("etag", ""), new ie("expect", ""), new ie("expires", ""), new ie(TypedValues.TransitionType.S_FROM, ""), new ie("host", ""), new ie("if-match", ""), new ie("if-modified-since", ""), new ie("if-none-match", ""), new ie("if-range", ""), new ie("if-unmodified-since", ""), new ie("last-modified", ""), new ie("link", ""), new ie("location", ""), new ie("max-forwards", ""), new ie("proxy-authenticate", ""), new ie("proxy-authorization", ""), new ie("range", ""), new ie("referer", ""), new ie("refresh", ""), new ie("retry-after", ""), new ie("server", ""), new ie("set-cookie", ""), new ie("strict-transport-security", ""), new ie("transfer-encoding", ""), new ie("user-agent", ""), new ie("vary", ""), new ie("via", ""), new ie("www-authenticate", "")};
        a = ieVarArr;
        LinkedHashMap linkedHashMap = new LinkedHashMap(ieVarArr.length);
        int length = ieVarArr.length;
        for (int i = 0; i < length; i++) {
            ie[] ieVarArr2 = a;
            if (!linkedHashMap.containsKey(ieVarArr2[i].b)) {
                linkedHashMap.put(ieVarArr2[i].b, Integer.valueOf(i));
            }
        }
        Map<ByteString, Integer> mapUnmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        Intrinsics.checkNotNullExpressionValue(mapUnmodifiableMap, "Collections.unmodifiableMap(result)");
        b = mapUnmodifiableMap;
    }

    private oe() {
    }

    @NotNull
    public final ByteString a(@NotNull ByteString name) throws IOException {
        Intrinsics.checkNotNullParameter(name, "name");
        int size = name.size();
        for (int i = 0; i < size; i++) {
            byte b2 = (byte) 65;
            byte b3 = (byte) 90;
            byte b4 = name.getByte(i);
            if (b2 <= b4 && b3 >= b4) {
                StringBuilder sbK = e.k("PROTOCOL_ERROR response malformed: mixed case name: ");
                sbK.append(name.utf8());
                throw new IOException(sbK.toString());
            }
        }
        return name;
    }
}
