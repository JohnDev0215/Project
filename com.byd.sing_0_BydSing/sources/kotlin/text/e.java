package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import kotlin.Pair;

/* JADX INFO: compiled from: Strings.kt */
/* JADX INFO: loaded from: classes.dex */
final class e implements kotlin.r.a<kotlin.q.h> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CharSequence f4115a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4116b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4117c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final kotlin.jvm.b.p<CharSequence, Integer, Pair<Integer, Integer>> f4118d;

    /* JADX INFO: compiled from: Strings.kt */
    public static final class a implements Iterator<kotlin.q.h> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f4119b = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f4120c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f4121d;
        private kotlin.q.h e;
        private int f;

        a() {
            int iC = kotlin.q.n.c(e.this.f4116b, 0, e.this.f4115a.length());
            this.f4120c = iC;
            this.f4121d = iC;
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final void a() {
            /*
                r6 = this;
                int r0 = r6.f4121d
                r1 = 0
                if (r0 >= 0) goto Lc
                r6.f4119b = r1
                r0 = 0
                r6.e = r0
                goto L9e
            Lc:
                kotlin.text.e r0 = kotlin.text.e.this
                int r0 = kotlin.text.e.c(r0)
                r2 = -1
                r3 = 1
                if (r0 <= 0) goto L23
                int r0 = r6.f
                int r0 = r0 + r3
                r6.f = r0
                kotlin.text.e r4 = kotlin.text.e.this
                int r4 = kotlin.text.e.c(r4)
                if (r0 >= r4) goto L31
            L23:
                int r0 = r6.f4121d
                kotlin.text.e r4 = kotlin.text.e.this
                java.lang.CharSequence r4 = kotlin.text.e.b(r4)
                int r4 = r4.length()
                if (r0 <= r4) goto L47
            L31:
                int r0 = r6.f4120c
                kotlin.q.h r1 = new kotlin.q.h
                kotlin.text.e r4 = kotlin.text.e.this
                java.lang.CharSequence r4 = kotlin.text.e.b(r4)
                int r4 = kotlin.text.StringsKt__StringsKt.s(r4)
                r1.<init>(r0, r4)
                r6.e = r1
                r6.f4121d = r2
                goto L9c
            L47:
                kotlin.text.e r0 = kotlin.text.e.this
                kotlin.jvm.b.p r0 = kotlin.text.e.a(r0)
                kotlin.text.e r4 = kotlin.text.e.this
                java.lang.CharSequence r4 = kotlin.text.e.b(r4)
                int r5 = r6.f4121d
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                java.lang.Object r0 = r0.invoke(r4, r5)
                kotlin.Pair r0 = (kotlin.Pair) r0
                if (r0 != 0) goto L77
                int r0 = r6.f4120c
                kotlin.q.h r1 = new kotlin.q.h
                kotlin.text.e r4 = kotlin.text.e.this
                java.lang.CharSequence r4 = kotlin.text.e.b(r4)
                int r4 = kotlin.text.StringsKt__StringsKt.s(r4)
                r1.<init>(r0, r4)
                r6.e = r1
                r6.f4121d = r2
                goto L9c
            L77:
                java.lang.Object r2 = r0.component1()
                java.lang.Number r2 = (java.lang.Number) r2
                int r2 = r2.intValue()
                java.lang.Object r0 = r0.component2()
                java.lang.Number r0 = (java.lang.Number) r0
                int r0 = r0.intValue()
                int r4 = r6.f4120c
                kotlin.q.h r4 = kotlin.q.l.e(r4, r2)
                r6.e = r4
                int r2 = r2 + r0
                r6.f4120c = r2
                if (r0 != 0) goto L99
                r1 = r3
            L99:
                int r2 = r2 + r1
                r6.f4121d = r2
            L9c:
                r6.f4119b = r3
            L9e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.text.e.a.a():void");
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public kotlin.q.h next() {
            if (this.f4119b == -1) {
                a();
            }
            if (this.f4119b == 0) {
                throw new NoSuchElementException();
            }
            kotlin.q.h hVar = this.e;
            Objects.requireNonNull(hVar, "null cannot be cast to non-null type kotlin.ranges.IntRange");
            this.e = null;
            this.f4119b = -1;
            return hVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f4119b == -1) {
                a();
            }
            return this.f4119b == 1;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public e(CharSequence charSequence, int i, int i2, kotlin.jvm.b.p<? super CharSequence, ? super Integer, Pair<Integer, Integer>> pVar) {
        kotlin.jvm.internal.h.d(charSequence, "input");
        kotlin.jvm.internal.h.d(pVar, "getNextMatch");
        this.f4115a = charSequence;
        this.f4116b = i;
        this.f4117c = i2;
        this.f4118d = pVar;
    }

    @Override // kotlin.r.a
    public Iterator<kotlin.q.h> iterator() {
        return new a();
    }
}
