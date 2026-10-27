package spi;

import java.util.Date;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class b3 {
    public static final a c = new a(null);

    @Nullable
    public final im a;

    @Nullable
    public final ln b;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0039  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean a(@org.jetbrains.annotations.NotNull spi.ln r4, @org.jetbrains.annotations.NotNull spi.im r5) {
            /*
                r3 = this;
                java.lang.String r0 = "response"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                java.lang.String r0 = "request"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                int r0 = r4.e
                r1 = 200(0xc8, float:2.8E-43)
                r2 = 0
                if (r0 == r1) goto L5b
                r1 = 410(0x19a, float:5.75E-43)
                if (r0 == r1) goto L5b
                r1 = 414(0x19e, float:5.8E-43)
                if (r0 == r1) goto L5b
                r1 = 501(0x1f5, float:7.02E-43)
                if (r0 == r1) goto L5b
                r1 = 203(0xcb, float:2.84E-43)
                if (r0 == r1) goto L5b
                r1 = 204(0xcc, float:2.86E-43)
                if (r0 == r1) goto L5b
                r1 = 307(0x133, float:4.3E-43)
                if (r0 == r1) goto L39
                r1 = 308(0x134, float:4.32E-43)
                if (r0 == r1) goto L5b
                r1 = 404(0x194, float:5.66E-43)
                if (r0 == r1) goto L5b
                r1 = 405(0x195, float:5.68E-43)
                if (r0 == r1) goto L5b
                switch(r0) {
                    case 300: goto L5b;
                    case 301: goto L5b;
                    case 302: goto L39;
                    default: goto L38;
                }
            L38:
                return r2
            L39:
                java.lang.String r0 = "Expires"
                java.lang.String r0 = spi.ln.B(r4, r0)
                if (r0 != 0) goto L5b
                spi.x2 r0 = r4.e()
                int r0 = r0.c
                r1 = -1
                if (r0 != r1) goto L5b
                spi.x2 r0 = r4.e()
                boolean r0 = r0.f
                if (r0 != 0) goto L5b
                spi.x2 r0 = r4.e()
                boolean r0 = r0.e
                if (r0 != 0) goto L5b
                return r2
            L5b:
                spi.x2 r4 = r4.e()
                boolean r4 = r4.b
                if (r4 != 0) goto L6c
                spi.x2 r4 = r5.a()
                boolean r4 = r4.b
                if (r4 != 0) goto L6c
                r2 = 1
            L6c:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.b3.a.a(spi.ln, spi.im):boolean");
        }
    }

    public static final class b {
        public Date a;
        public String b;
        public Date c;
        public String d;
        public Date e;
        public long f;
        public long g;
        public String h;
        public int i;
        public final long j;

        @NotNull
        public final im k;
        public final ln l;

        public b(long j, @NotNull im request, @Nullable ln lnVar) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.j = j;
            this.k = request;
            this.l = lnVar;
            this.i = -1;
            if (lnVar != null) {
                this.f = lnVar.l;
                this.g = lnVar.m;
                ke keVar = lnVar.g;
                int length = keVar.a.length / 2;
                for (int i = 0; i < length; i++) {
                    String strB = keVar.b(i);
                    String strD = keVar.d(i);
                    if (StringsKt.h(strB, "Date")) {
                        this.a = q5.a(strD);
                        this.b = strD;
                    } else if (StringsKt.h(strB, "Expires")) {
                        this.e = q5.a(strD);
                    } else if (StringsKt.h(strB, "Last-Modified")) {
                        this.c = q5.a(strD);
                        this.d = strD;
                    } else if (StringsKt.h(strB, "ETag")) {
                        this.h = strD;
                    } else if (StringsKt.h(strB, "Age")) {
                        this.i = uu.y(strD, -1);
                    }
                }
            }
        }
    }

    public b3(@Nullable im imVar, @Nullable ln lnVar) {
        this.a = imVar;
        this.b = lnVar;
    }
}
