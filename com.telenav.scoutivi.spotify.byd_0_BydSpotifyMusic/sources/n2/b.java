package n2;

import android.content.res.TypedArray;
import android.graphics.RectF;

/* JADX INFO: compiled from: Shimmer.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final float[] f8513a = new float[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int[] f8514b = new int[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final RectF f8515c = new RectF();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f8516d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f8517e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f8518f = 1291845631;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f8519g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f8520h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int f8521i = 0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    float f8522j = 1.0f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    float f8523k = 1.0f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    float f8524l = 0.0f;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    float f8525m = 0.5f;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    float f8526n = 20.0f;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    boolean f8527o = true;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    boolean f8528p = true;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    boolean f8529q = true;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    int f8530r = -1;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    int f8531s = 1;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    long f8532t = 1000;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    long f8533u;

    /* JADX INFO: compiled from: Shimmer.java */
    public static class a extends AbstractC0133b<a> {
        public a() {
            this.f8534a.f8529q = true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // n2.b.AbstractC0133b
        /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
        public a d() {
            return this;
        }
    }

    /* JADX INFO: renamed from: n2.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Shimmer.java */
    public static abstract class AbstractC0133b<T extends AbstractC0133b<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final b f8534a = new b();

        private static float b(float f7, float f8, float f9) {
            return Math.min(f8, Math.max(f7, f9));
        }

        public b a() {
            this.f8534a.b();
            this.f8534a.c();
            return this.f8534a;
        }

        T c(TypedArray typedArray) {
            int i7 = n2.a.ShimmerFrameLayout_shimmer_clip_to_children;
            if (typedArray.hasValue(i7)) {
                g(typedArray.getBoolean(i7, this.f8534a.f8527o));
            }
            int i8 = n2.a.ShimmerFrameLayout_shimmer_auto_start;
            if (typedArray.hasValue(i8)) {
                e(typedArray.getBoolean(i8, this.f8534a.f8528p));
            }
            int i9 = n2.a.ShimmerFrameLayout_shimmer_base_alpha;
            if (typedArray.hasValue(i9)) {
                f(typedArray.getFloat(i9, 0.3f));
            }
            int i10 = n2.a.ShimmerFrameLayout_shimmer_highlight_alpha;
            if (typedArray.hasValue(i10)) {
                n(typedArray.getFloat(i10, 1.0f));
            }
            if (typedArray.hasValue(n2.a.ShimmerFrameLayout_shimmer_duration)) {
                j(typedArray.getInt(r0, (int) this.f8534a.f8532t));
            }
            int i11 = n2.a.ShimmerFrameLayout_shimmer_repeat_count;
            if (typedArray.hasValue(i11)) {
                p(typedArray.getInt(i11, this.f8534a.f8530r));
            }
            if (typedArray.hasValue(n2.a.ShimmerFrameLayout_shimmer_repeat_delay)) {
                q(typedArray.getInt(r0, (int) this.f8534a.f8533u));
            }
            int i12 = n2.a.ShimmerFrameLayout_shimmer_repeat_mode;
            if (typedArray.hasValue(i12)) {
                r(typedArray.getInt(i12, this.f8534a.f8531s));
            }
            int i13 = n2.a.ShimmerFrameLayout_shimmer_direction;
            if (typedArray.hasValue(i13)) {
                int i14 = typedArray.getInt(i13, this.f8534a.f8516d);
                if (i14 == 1) {
                    h(1);
                } else if (i14 == 2) {
                    h(2);
                } else if (i14 != 3) {
                    h(0);
                } else {
                    h(3);
                }
            }
            int i15 = n2.a.ShimmerFrameLayout_shimmer_shape;
            if (typedArray.hasValue(i15)) {
                if (typedArray.getInt(i15, this.f8534a.f8519g) != 1) {
                    s(0);
                } else {
                    s(1);
                }
            }
            int i16 = n2.a.ShimmerFrameLayout_shimmer_dropoff;
            if (typedArray.hasValue(i16)) {
                i(typedArray.getFloat(i16, this.f8534a.f8525m));
            }
            int i17 = n2.a.ShimmerFrameLayout_shimmer_fixed_width;
            if (typedArray.hasValue(i17)) {
                l(typedArray.getDimensionPixelSize(i17, this.f8534a.f8520h));
            }
            int i18 = n2.a.ShimmerFrameLayout_shimmer_fixed_height;
            if (typedArray.hasValue(i18)) {
                k(typedArray.getDimensionPixelSize(i18, this.f8534a.f8521i));
            }
            int i19 = n2.a.ShimmerFrameLayout_shimmer_intensity;
            if (typedArray.hasValue(i19)) {
                o(typedArray.getFloat(i19, this.f8534a.f8524l));
            }
            int i20 = n2.a.ShimmerFrameLayout_shimmer_width_ratio;
            if (typedArray.hasValue(i20)) {
                u(typedArray.getFloat(i20, this.f8534a.f8522j));
            }
            int i21 = n2.a.ShimmerFrameLayout_shimmer_height_ratio;
            if (typedArray.hasValue(i21)) {
                m(typedArray.getFloat(i21, this.f8534a.f8523k));
            }
            int i22 = n2.a.ShimmerFrameLayout_shimmer_tilt;
            if (typedArray.hasValue(i22)) {
                t(typedArray.getFloat(i22, this.f8534a.f8526n));
            }
            return (T) d();
        }

        protected abstract T d();

        public T e(boolean z6) {
            this.f8534a.f8528p = z6;
            return (T) d();
        }

        public T f(float f7) {
            int iB = (int) (b(0.0f, 1.0f, f7) * 255.0f);
            b bVar = this.f8534a;
            bVar.f8518f = (iB << 24) | (bVar.f8518f & 16777215);
            return (T) d();
        }

        public T g(boolean z6) {
            this.f8534a.f8527o = z6;
            return (T) d();
        }

        public T h(int i7) {
            this.f8534a.f8516d = i7;
            return (T) d();
        }

        public T i(float f7) {
            if (f7 >= 0.0f) {
                this.f8534a.f8525m = f7;
                return (T) d();
            }
            throw new IllegalArgumentException("Given invalid dropoff value: " + f7);
        }

        public T j(long j7) {
            if (j7 >= 0) {
                this.f8534a.f8532t = j7;
                return (T) d();
            }
            throw new IllegalArgumentException("Given a negative duration: " + j7);
        }

        public T k(int i7) {
            if (i7 >= 0) {
                this.f8534a.f8521i = i7;
                return (T) d();
            }
            throw new IllegalArgumentException("Given invalid height: " + i7);
        }

        public T l(int i7) {
            if (i7 >= 0) {
                this.f8534a.f8520h = i7;
                return (T) d();
            }
            throw new IllegalArgumentException("Given invalid width: " + i7);
        }

        public T m(float f7) {
            if (f7 >= 0.0f) {
                this.f8534a.f8523k = f7;
                return (T) d();
            }
            throw new IllegalArgumentException("Given invalid height ratio: " + f7);
        }

        public T n(float f7) {
            int iB = (int) (b(0.0f, 1.0f, f7) * 255.0f);
            b bVar = this.f8534a;
            bVar.f8517e = (iB << 24) | (bVar.f8517e & 16777215);
            return (T) d();
        }

        public T o(float f7) {
            if (f7 >= 0.0f) {
                this.f8534a.f8524l = f7;
                return (T) d();
            }
            throw new IllegalArgumentException("Given invalid intensity value: " + f7);
        }

        public T p(int i7) {
            this.f8534a.f8530r = i7;
            return (T) d();
        }

        public T q(long j7) {
            if (j7 >= 0) {
                this.f8534a.f8533u = j7;
                return (T) d();
            }
            throw new IllegalArgumentException("Given a negative repeat delay: " + j7);
        }

        public T r(int i7) {
            this.f8534a.f8531s = i7;
            return (T) d();
        }

        public T s(int i7) {
            this.f8534a.f8519g = i7;
            return (T) d();
        }

        public T t(float f7) {
            this.f8534a.f8526n = f7;
            return (T) d();
        }

        public T u(float f7) {
            if (f7 >= 0.0f) {
                this.f8534a.f8522j = f7;
                return (T) d();
            }
            throw new IllegalArgumentException("Given invalid width ratio: " + f7);
        }
    }

    /* JADX INFO: compiled from: Shimmer.java */
    public static class c extends AbstractC0133b<c> {
        public c() {
            this.f8534a.f8529q = false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // n2.b.AbstractC0133b
        /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
        public c c(TypedArray typedArray) {
            super.c(typedArray);
            int i7 = n2.a.ShimmerFrameLayout_shimmer_base_color;
            if (typedArray.hasValue(i7)) {
                x(typedArray.getColor(i7, this.f8534a.f8518f));
            }
            int i8 = n2.a.ShimmerFrameLayout_shimmer_highlight_color;
            if (typedArray.hasValue(i8)) {
                y(typedArray.getColor(i8, this.f8534a.f8517e));
            }
            return d();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // n2.b.AbstractC0133b
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public c d() {
            return this;
        }

        public c x(int i7) {
            b bVar = this.f8534a;
            bVar.f8518f = (i7 & 16777215) | (bVar.f8518f & (-16777216));
            return d();
        }

        public c y(int i7) {
            this.f8534a.f8517e = i7;
            return d();
        }
    }

    b() {
    }

    int a(int i7) {
        int i8 = this.f8521i;
        return i8 > 0 ? i8 : Math.round(this.f8523k * i7);
    }

    void b() {
        if (this.f8519g != 1) {
            int[] iArr = this.f8514b;
            int i7 = this.f8518f;
            iArr[0] = i7;
            int i8 = this.f8517e;
            iArr[1] = i8;
            iArr[2] = i8;
            iArr[3] = i7;
            return;
        }
        int[] iArr2 = this.f8514b;
        int i9 = this.f8517e;
        iArr2[0] = i9;
        iArr2[1] = i9;
        int i10 = this.f8518f;
        iArr2[2] = i10;
        iArr2[3] = i10;
    }

    void c() {
        if (this.f8519g != 1) {
            this.f8513a[0] = Math.max(((1.0f - this.f8524l) - this.f8525m) / 2.0f, 0.0f);
            this.f8513a[1] = Math.max(((1.0f - this.f8524l) - 0.001f) / 2.0f, 0.0f);
            this.f8513a[2] = Math.min(((this.f8524l + 1.0f) + 0.001f) / 2.0f, 1.0f);
            this.f8513a[3] = Math.min(((this.f8524l + 1.0f) + this.f8525m) / 2.0f, 1.0f);
            return;
        }
        float[] fArr = this.f8513a;
        fArr[0] = 0.0f;
        fArr[1] = Math.min(this.f8524l, 1.0f);
        this.f8513a[2] = Math.min(this.f8524l + this.f8525m, 1.0f);
        this.f8513a[3] = 1.0f;
    }

    int d(int i7) {
        int i8 = this.f8520h;
        return i8 > 0 ? i8 : Math.round(this.f8522j * i7);
    }
}
