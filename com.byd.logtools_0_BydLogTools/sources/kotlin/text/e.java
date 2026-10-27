package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.TypeCastException;

/* JADX INFO: compiled from: Strings.kt */
/* JADX INFO: loaded from: classes.dex */
final class e implements kotlin.p.d<kotlin.o.c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CharSequence f5170a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f5171b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f5172c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final kotlin.jvm.b.p<CharSequence, Integer, Pair<Integer, Integer>> f5173d;

    /* JADX INFO: compiled from: Strings.kt */
    public static final class a implements Iterator<kotlin.o.c>, kotlin.jvm.internal.n.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f5174b = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f5175c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f5176d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private kotlin.o.c f5177e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f5178f;

        a() {
            int iE = kotlin.o.f.e(e.this.f5171b, 0, e.this.f5170a.length());
            this.f5175c = iE;
            this.f5176d = iE;
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final void a() {
            /*
                r6 = this;
                int r0 = r6.f5176d
                r1 = 0
                if (r0 >= 0) goto Lc
                r6.f5174b = r1
                r0 = 0
                r6.f5177e = r0
                goto L9e
            Lc:
                kotlin.text.e r0 = kotlin.text.e.this
                int r0 = kotlin.text.e.d(r0)
                r2 = -1
                r3 = 1
                if (r0 <= 0) goto L23
                int r0 = r6.f5178f
                int r0 = r0 + r3
                r6.f5178f = r0
                kotlin.text.e r4 = kotlin.text.e.this
                int r4 = kotlin.text.e.d(r4)
                if (r0 >= r4) goto L31
            L23:
                int r0 = r6.f5176d
                kotlin.text.e r4 = kotlin.text.e.this
                java.lang.CharSequence r4 = kotlin.text.e.c(r4)
                int r4 = r4.length()
                if (r0 <= r4) goto L47
            L31:
                int r0 = r6.f5175c
                kotlin.o.c r1 = new kotlin.o.c
                kotlin.text.e r4 = kotlin.text.e.this
                java.lang.CharSequence r4 = kotlin.text.e.c(r4)
                int r4 = kotlin.text.StringsKt__StringsKt.G(r4)
                r1.<init>(r0, r4)
                r6.f5177e = r1
                r6.f5176d = r2
                goto L9c
            L47:
                kotlin.text.e r0 = kotlin.text.e.this
                kotlin.jvm.b.p r0 = kotlin.text.e.b(r0)
                kotlin.text.e r4 = kotlin.text.e.this
                java.lang.CharSequence r4 = kotlin.text.e.c(r4)
                int r5 = r6.f5176d
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                java.lang.Object r0 = r0.invoke(r4, r5)
                kotlin.Pair r0 = (kotlin.Pair) r0
                if (r0 != 0) goto L77
                int r0 = r6.f5175c
                kotlin.o.c r1 = new kotlin.o.c
                kotlin.text.e r4 = kotlin.text.e.this
                java.lang.CharSequence r4 = kotlin.text.e.c(r4)
                int r4 = kotlin.text.StringsKt__StringsKt.G(r4)
                r1.<init>(r0, r4)
                r6.f5177e = r1
                r6.f5176d = r2
                goto L9c
            L77:
                java.lang.Object r2 = r0.component1()
                java.lang.Number r2 = (java.lang.Number) r2
                int r2 = r2.intValue()
                java.lang.Object r0 = r0.component2()
                java.lang.Number r0 = (java.lang.Number) r0
                int r0 = r0.intValue()
                int r4 = r6.f5175c
                kotlin.o.c r4 = kotlin.o.d.h(r4, r2)
                r6.f5177e = r4
                int r2 = r2 + r0
                r6.f5175c = r2
                if (r0 != 0) goto L99
                r1 = r3
            L99:
                int r2 = r2 + r1
                r6.f5176d = r2
            L9c:
                r6.f5174b = r3
            L9e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.text.e.a.a():void");
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public kotlin.o.c next() {
            if (this.f5174b == -1) {
                a();
            }
            if (this.f5174b == 0) {
                throw new NoSuchElementException();
            }
            kotlin.o.c cVar = this.f5177e;
            if (cVar == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.ranges.IntRange");
            }
            this.f5177e = null;
            this.f5174b = -1;
            return cVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f5174b == -1) {
                a();
            }
            return this.f5174b == 1;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public e(CharSequence charSequence, int i, int i2, kotlin.jvm.b.p<? super CharSequence, ? super Integer, Pair<Integer, Integer>> pVar) {
        kotlin.jvm.internal.i.c(charSequence, "input");
        kotlin.jvm.internal.i.c(pVar, "getNextMatch");
        this.f5170a = charSequence;
        this.f5171b = i;
        this.f5172c = i2;
        this.f5173d = pVar;
    }

    @Override // kotlin.p.d
    public Iterator<kotlin.o.c> iterator() {
        return new a();
    }
}
