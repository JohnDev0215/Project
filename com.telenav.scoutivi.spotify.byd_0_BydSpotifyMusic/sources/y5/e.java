package y5;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/* JADX INFO: compiled from: Strings.kt */
/* JADX INFO: loaded from: classes.dex */
final class e implements x5.d<v5.c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CharSequence f9998a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f9999b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f10000c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final r5.p<CharSequence, Integer, g5.k<Integer, Integer>> f10001d;

    /* JADX INFO: compiled from: Strings.kt */
    public static final class a implements Iterator<v5.c>, s5.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f10002e = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f10003g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f10004h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private v5.c f10005i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private int f10006j;

        a() {
            int iF = v5.h.f(e.this.f9999b, 0, e.this.f9998a.length());
            this.f10003g = iF;
            this.f10004h = iF;
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final void a() {
            /*
                r6 = this;
                int r0 = r6.f10004h
                r1 = 0
                if (r0 >= 0) goto Lc
                r6.f10002e = r1
                r0 = 0
                r6.f10005i = r0
                goto L9e
            Lc:
                y5.e r0 = y5.e.this
                int r0 = y5.e.d(r0)
                r2 = -1
                r3 = 1
                if (r0 <= 0) goto L23
                int r0 = r6.f10006j
                int r0 = r0 + r3
                r6.f10006j = r0
                y5.e r4 = y5.e.this
                int r4 = y5.e.d(r4)
                if (r0 >= r4) goto L31
            L23:
                int r0 = r6.f10004h
                y5.e r4 = y5.e.this
                java.lang.CharSequence r4 = y5.e.c(r4)
                int r4 = r4.length()
                if (r0 <= r4) goto L47
            L31:
                v5.c r0 = new v5.c
                int r1 = r6.f10003g
                y5.e r4 = y5.e.this
                java.lang.CharSequence r4 = y5.e.c(r4)
                int r4 = y5.q.K(r4)
                r0.<init>(r1, r4)
                r6.f10005i = r0
                r6.f10004h = r2
                goto L9c
            L47:
                y5.e r0 = y5.e.this
                r5.p r0 = y5.e.b(r0)
                y5.e r4 = y5.e.this
                java.lang.CharSequence r4 = y5.e.c(r4)
                int r5 = r6.f10004h
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                java.lang.Object r0 = r0.invoke(r4, r5)
                g5.k r0 = (g5.k) r0
                if (r0 != 0) goto L77
                v5.c r0 = new v5.c
                int r1 = r6.f10003g
                y5.e r4 = y5.e.this
                java.lang.CharSequence r4 = y5.e.c(r4)
                int r4 = y5.q.K(r4)
                r0.<init>(r1, r4)
                r6.f10005i = r0
                r6.f10004h = r2
                goto L9c
            L77:
                java.lang.Object r2 = r0.a()
                java.lang.Number r2 = (java.lang.Number) r2
                int r2 = r2.intValue()
                java.lang.Object r0 = r0.b()
                java.lang.Number r0 = (java.lang.Number) r0
                int r0 = r0.intValue()
                int r4 = r6.f10003g
                v5.c r4 = v5.f.i(r4, r2)
                r6.f10005i = r4
                int r2 = r2 + r0
                r6.f10003g = r2
                if (r0 != 0) goto L99
                r1 = r3
            L99:
                int r2 = r2 + r1
                r6.f10004h = r2
            L9c:
                r6.f10002e = r3
            L9e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: y5.e.a.a():void");
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public v5.c next() {
            if (this.f10002e == -1) {
                a();
            }
            if (this.f10002e == 0) {
                throw new NoSuchElementException();
            }
            v5.c cVar = this.f10005i;
            Objects.requireNonNull(cVar, "null cannot be cast to non-null type kotlin.ranges.IntRange");
            this.f10005i = null;
            this.f10002e = -1;
            return cVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f10002e == -1) {
                a();
            }
            return this.f10002e == 1;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public e(CharSequence input, int i7, int i8, r5.p<? super CharSequence, ? super Integer, g5.k<Integer, Integer>> getNextMatch) {
        kotlin.jvm.internal.l.e(input, "input");
        kotlin.jvm.internal.l.e(getNextMatch, "getNextMatch");
        this.f9998a = input;
        this.f9999b = i7;
        this.f10000c = i8;
        this.f10001d = getNextMatch;
    }

    @Override // x5.d
    public Iterator<v5.c> iterator() {
        return new a();
    }
}
