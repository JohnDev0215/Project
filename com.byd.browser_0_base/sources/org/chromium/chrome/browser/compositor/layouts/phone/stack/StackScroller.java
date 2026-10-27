package org.chromium.chrome.browser.compositor.layouts.phone.stack;

import android.content.Context;
import android.util.Log;
import android.view.ViewConfiguration;
import defpackage.C0173a;

/* JADX INFO: loaded from: classes.dex */
public class StackScroller {
    public static float e;
    public static float f;
    public int a;
    public final a b;
    public final a c;
    public final boolean d = true;

    public static class a {
        public static final float q = (float) (Math.log(0.78d) / Math.log(0.9d));
        public static final float[] r = {2.3E-5f, 0.028561f, 0.057052f, 0.085389f, 0.113496f, 0.141299f, 0.168772f, 0.195811f, 0.222396f, 0.248438f, 0.274002f, 0.298968f, 0.323332f, 0.347096f, 0.370225f, 0.392725f, 0.41457f, 0.435829f, 0.456419f, 0.47641f, 0.495756f, 0.514549f, 0.532721f, 0.550285f, 0.567327f, 0.583811f, 0.599748f, 0.615194f, 0.630117f, 0.644548f, 0.65852f, 0.67204f, 0.6851f, 0.697728f, 0.709951f, 0.721775f, 0.733178f, 0.744231f, 0.754909f, 0.765247f, 0.775225f, 0.784877f, 0.794206f, 0.80323f, 0.811943f, 0.820371f, 0.828519f, 0.836379f, 0.843977f, 0.851323f, 0.858411f, 0.865253f, 0.871853f, 0.878233f, 0.884389f, 0.890316f, 0.896047f, 0.901557f, 0.906874f, 0.911995f, 0.916932f, 0.921675f, 0.926242f, 0.930633f, 0.934848f, 0.938901f, 0.94279f, 0.946522f, 0.950094f, 0.953518f, 0.95679f, 0.959924f, 0.962913f, 0.965762f, 0.968482f, 0.971068f, 0.973523f, 0.975851f, 0.97806f, 0.980149f, 0.982115f, 0.983968f, 0.985709f, 0.987335f, 0.988855f, 0.990269f, 0.991577f, 0.992784f, 0.993891f, 0.994899f, 0.995811f, 0.996627f, 0.997352f, 0.997985f, 0.998529f, 0.998984f, 0.999354f, 0.999639f, 0.99984f, 0.99996f, 1.0f};
        public static final float[] s = {2.0E-6f, 0.003501f, 0.007003f, 0.010507f, 0.014014f, 0.017523f, 0.021044f, 0.024569f, 0.028098f, 0.03164f, 0.035195f, 0.038755f, 0.042337f, 0.045926f, 0.04953f, 0.053156f, 0.056798f, 0.060456f, 0.064138f, 0.067844f, 0.071568f, 0.075316f, 0.079097f, 0.082904f, 0.086737f, 0.090596f, 0.094489f, 0.098416f, 0.102385f, 0.106382f, 0.110422f, 0.114497f, 0.118615f, 0.122783f, 0.126987f, 0.131243f, 0.135549f, 0.1399f, 0.144309f, 0.148776f, 0.153296f, 0.157881f, 0.162519f, 0.16723f, 0.172007f, 0.176851f, 0.181767f, 0.186757f, 0.191835f, 0.196993f, 0.20223f, 0.207555f, 0.212973f, 0.218491f, 0.224109f, 0.229833f, 0.235656f, 0.241598f, 0.247659f, 0.253837f, 0.260147f, 0.266598f, 0.273178f, 0.279912f, 0.286812f, 0.293848f, 0.301075f, 0.308475f, 0.31606f, 0.32384f, 0.331824f, 0.340037f, 0.348487f, 0.357182f, 0.366129f, 0.375349f, 0.384886f, 0.394732f, 0.404901f, 0.415447f, 0.426381f, 0.437738f, 0.449557f, 0.46186f, 0.474729f, 0.488177f, 0.502311f, 0.51715f, 0.532822f, 0.549455f, 0.56713f, 0.586069f, 0.606443f, 0.628536f, 0.652774f, 0.679739f, 0.710244f, 0.745801f, 0.789246f, 0.848082f, 1.0f};
        public int a;
        public int b;
        public int c;
        public int d;
        public float e;
        public float f;
        public long g;
        public int h;
        public int i;
        public int j;
        public int l;
        public final float p;
        public final float m = ViewConfiguration.getScrollFriction();
        public final float n = 1.0f;
        public int o = 0;
        public boolean k = true;

        public a(Context context) {
            this.p = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        }

        public final void a(int i, int i2, int i3) {
            float fAbs = Math.abs((i3 - i) / (i2 - i));
            int i4 = (int) (fAbs * 100.0f);
            if (i4 < 100) {
                float f = i4 / 100.0f;
                int i5 = i4 + 1;
                float[] fArr = s;
                float f2 = fArr[i4];
                this.h = (int) (this.h * C0173a.f(fArr[i5], f2, (fAbs - f) / ((i5 / 100.0f) - f), f2));
            }
        }

        public final boolean b(long j) {
            int i = this.o;
            if (i == 0) {
                int i2 = this.h;
                if (i2 >= this.i) {
                    return false;
                }
                this.a = this.c;
                int i3 = (int) this.e;
                this.d = i3;
                this.f = i3 > 0 ? -2000.0f : 2000.0f;
                this.g += (long) i2;
                d();
            } else {
                if (i == 1) {
                    return false;
                }
                if (i == 2) {
                    this.g += (long) this.h;
                    e(this.c, this.a);
                }
            }
            f(j);
            return true;
        }

        public final void c(int i, int i2, int i3, int i4, int i5, long j) {
            double dExp;
            int i6 = i4;
            this.l = i5;
            this.k = false;
            this.d = i2;
            float f = i2;
            this.e = f;
            this.i = 0;
            this.h = 0;
            this.g = j;
            this.a = i;
            this.b = i;
            float f2 = q;
            float f3 = this.p;
            float f4 = this.n;
            float f5 = this.m;
            if (i <= i6 && i >= i3) {
                this.o = 0;
                if (i2 != 0) {
                    int iExp = (int) (Math.exp(Math.log((Math.abs(i2) * 0.35f) / ((f5 * f4) * f3)) / (((double) f2) - 1.0d)) * 1000.0d);
                    this.i = iExp;
                    this.h = iExp;
                    double d = f2;
                    dExp = Math.exp((d / (d - 1.0d)) * Math.log((Math.abs(i2) * 0.35f) / r14)) * ((double) (f5 * f4 * f3));
                } else {
                    dExp = 0.0d;
                }
                int iSignum = (int) (dExp * ((double) Math.signum(f)));
                this.j = iSignum;
                int i7 = i + iSignum;
                this.c = i7;
                if (i7 < i3) {
                    a(this.a, i7, i3);
                    this.c = i3;
                }
                int i8 = this.c;
                if (i8 > i4) {
                    a(this.a, i8, i4);
                    this.c = i4;
                    return;
                }
                return;
            }
            if (i > i3 && i < i6) {
                Log.e("StackScroller", "startAfterEdge called from a valid position");
                this.k = true;
                return;
            }
            boolean z = i > i6;
            int i9 = z ? i6 : i3;
            int i10 = i - i9;
            if (i10 * i2 >= 0) {
                if (i2 != 0) {
                    i10 = i2;
                }
                float f6 = i10 > 0 ? -2000.0f : 2000.0f;
                this.f = f6;
                float fSqrt = (float) Math.sqrt((((double) ((((i2 * i2) / 2.0f) / Math.abs(f6)) + Math.abs(i9 - i))) * 2.0d) / ((double) Math.abs(this.f)));
                this.g -= (long) ((int) ((fSqrt - ((-i2) / f6)) * 1000.0f));
                this.a = i9;
                this.d = (int) ((-this.f) * fSqrt);
                d();
                return;
            }
            double d2 = f2;
            if (Math.exp((d2 / (d2 - 1.0d)) * Math.log((Math.abs(i2) * 0.35f) / r14)) * ((double) (f5 * f4 * f3)) <= Math.abs(i10)) {
                e(i, i9);
                return;
            }
            int i11 = z ? i3 : i;
            if (z) {
                i6 = i;
            }
            c(i, i2, i11, i6, this.l, j);
        }

        public final void d() {
            int i = this.d;
            float fAbs = (i * i) / (Math.abs(this.f) * 2.0f);
            float fSignum = Math.signum(this.d);
            int i2 = this.l;
            if (fAbs > i2) {
                float f = -fSignum;
                int i3 = this.d;
                this.f = ((f * i3) * i3) / (i2 * 2.0f);
                fAbs = i2;
            }
            this.l = (int) fAbs;
            this.o = 2;
            int i4 = this.a;
            int i5 = this.d;
            if (i5 <= 0) {
                fAbs = -fAbs;
            }
            this.c = i4 + ((int) fAbs);
            this.h = -((int) ((i5 * 1000.0f) / this.f));
        }

        public final void e(int i, int i2) {
            this.k = false;
            this.o = 1;
            this.a = i;
            this.c = i2;
            int i3 = i - i2;
            this.f = i3 > 0 ? -2000.0f : 2000.0f;
            this.d = -i3;
            this.l = Math.abs(i3);
            this.h = (int) (Math.sqrt((((double) i3) * (-2.0d)) / ((double) this.f)) * 1000.0d);
        }

        public final boolean f(long j) {
            float f;
            float f2;
            double d;
            double d2;
            long j2 = j - this.g;
            int i = this.h;
            if (j2 > i) {
                return false;
            }
            int i2 = this.o;
            if (i2 == 0) {
                int i3 = this.i;
                float f3 = j2 / i3;
                int i4 = (int) (f3 * 100.0f);
                if (i4 < 100) {
                    float f4 = i4 / 100.0f;
                    int i5 = i4 + 1;
                    float[] fArr = r;
                    float f5 = fArr[i4];
                    f2 = (fArr[i5] - f5) / ((i5 / 100.0f) - f4);
                    f = C0173a.f(f3, f4, f2, f5);
                } else {
                    f = 1.0f;
                    f2 = 0.0f;
                }
                int i6 = this.j;
                d = f * i6;
                this.e = ((f2 * i6) / i3) * 1000.0f;
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        d2 = 0.0d;
                    } else {
                        float f6 = j2 / 1000.0f;
                        int i7 = this.d;
                        float f7 = this.f;
                        this.e = (f7 * f6) + i7;
                        d2 = (((f7 * f6) * f6) / 2.0f) + (i7 * f6);
                    }
                    this.b = this.a + ((int) Math.round(d2));
                    return true;
                }
                float f8 = j2 / i;
                float f9 = f8 * f8;
                float fSignum = Math.signum(this.d);
                int i8 = this.l;
                d = ((3.0f * f9) - ((2.0f * f8) * f9)) * i8 * fSignum;
                this.e = ((-f8) + f9) * fSignum * i8 * 6.0f;
            }
            d2 = d;
            this.b = this.a + ((int) Math.round(d2));
            return true;
        }
    }

    public StackScroller(Context context) {
        this.b = new a(context);
        this.c = new a(context);
        e = 8.0f;
        f = 1.0f;
        f = 1.0f / c(1.0f);
    }

    public static float c(float f2) {
        float f3 = f2 * e;
        return (f3 < 1.0f ? f3 - (1.0f - ((float) Math.exp(-f3))) : C0173a.f(1.0f, (float) Math.exp(1.0f - f3), 0.63212055f, 0.36787945f)) * f;
    }

    public final boolean a() {
        return this.b.k && this.c.k;
    }

    public final void b(int i, int i2, int i3, long j) {
        this.a = 0;
        a aVar = this.b;
        aVar.k = false;
        aVar.a = i;
        aVar.c = i + i2;
        aVar.g = j;
        aVar.h = i3;
        aVar.f = 0.0f;
        aVar.d = 0;
        a aVar2 = this.c;
        aVar2.k = false;
        aVar2.a = 0;
        aVar2.c = 0;
        aVar2.g = j;
        aVar2.h = i3;
        aVar2.f = 0.0f;
        aVar2.d = 0;
    }
}
