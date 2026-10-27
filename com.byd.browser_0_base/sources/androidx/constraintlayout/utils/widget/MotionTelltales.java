package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewParent;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$styleable;
import defpackage.bj1;
import defpackage.d8;
import defpackage.dj1;
import defpackage.dn3;
import defpackage.ej1;
import defpackage.fl3;
import defpackage.rn3;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MotionTelltales extends MockView {
    public final Paint p;
    public MotionLayout q;
    public final float[] r;
    public final Matrix s;
    public int t;
    public int u;
    public float v;

    public MotionTelltales(Context context) {
        super(context);
        this.p = new Paint();
        this.r = new float[2];
        this.s = new Matrix();
        this.t = 0;
        this.u = -65281;
        this.v = 0.25f;
        a(context, null);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MotionTelltales);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.MotionTelltales_telltales_tailColor) {
                    this.u = typedArrayObtainStyledAttributes.getColor(index, this.u);
                } else if (index == R$styleable.MotionTelltales_telltales_velocityMode) {
                    this.t = typedArrayObtainStyledAttributes.getInt(index, this.t);
                } else if (index == R$styleable.MotionTelltales_telltales_tailScale) {
                    this.v = typedArrayObtainStyledAttributes.getFloat(index, this.v);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        int i2 = this.u;
        Paint paint = this.p;
        paint.setColor(i2);
        paint.setStrokeWidth(5.0f);
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // androidx.constraintlayout.utils.widget.MockView, android.view.View
    public final void onDraw(Canvas canvas) {
        float[] fArr;
        int i;
        Matrix matrix;
        int i2;
        float f;
        int i3;
        int i4;
        float[] fArr2;
        float f2;
        int i5;
        rn3 rn3Var;
        int i6;
        rn3 rn3Var2;
        rn3 rn3Var3;
        rn3 rn3Var4;
        rn3 rn3Var5;
        int i7;
        bj1 bj1Var;
        dn3 dn3Var;
        double[] dArr;
        float[] fArr3;
        int i8;
        fl3 fl3Var;
        super.onDraw(canvas);
        Matrix matrix2 = getMatrix();
        Matrix matrix3 = this.s;
        matrix2.invert(matrix3);
        if (this.q == null) {
            ViewParent parent = getParent();
            if (parent instanceof MotionLayout) {
                this.q = (MotionLayout) parent;
                return;
            }
            return;
        }
        int width = getWidth();
        int height = getHeight();
        int i9 = 5;
        float[] fArr4 = {0.1f, 0.25f, 0.5f, 0.75f, 0.9f};
        MotionTelltales motionTelltales = this;
        int i10 = 0;
        while (i10 < i9) {
            float f3 = fArr4[i10];
            int i11 = 0;
            while (i11 < i9) {
                float f4 = fArr4[i11];
                MotionLayout motionLayout = motionTelltales.q;
                float[] fArr5 = motionTelltales.r;
                int i12 = motionTelltales.t;
                float fA = motionLayout.x;
                float f5 = motionLayout.I;
                if (motionLayout.v != null) {
                    float fSignum = Math.signum(motionLayout.K - f5);
                    float interpolation = motionLayout.v.getInterpolation(motionLayout.I + 1.0E-5f);
                    fArr = fArr4;
                    float interpolation2 = motionLayout.v.getInterpolation(motionLayout.I);
                    fA = (((interpolation - interpolation2) / 1.0E-5f) * fSignum) / motionLayout.G;
                    f5 = interpolation2;
                } else {
                    fArr = fArr4;
                }
                dj1 dj1Var = motionLayout.v;
                if (dj1Var instanceof dj1) {
                    fA = dj1Var.a();
                }
                float f6 = fA;
                bj1 bj1Var2 = motionLayout.E.get(motionTelltales);
                if ((i12 & 1) == 0) {
                    int width2 = getWidth();
                    int height2 = getHeight();
                    float[] fArr6 = bj1Var2.v;
                    float fB = bj1Var2.b(f5, fArr6);
                    HashMap<String, rn3> map = bj1Var2.y;
                    if (map == null) {
                        i6 = i11;
                        rn3Var = null;
                    } else {
                        rn3Var = map.get("translationX");
                        i6 = i11;
                    }
                    HashMap<String, rn3> map2 = bj1Var2.y;
                    i3 = i10;
                    if (map2 == null) {
                        i2 = height;
                        rn3Var2 = null;
                    } else {
                        rn3Var2 = map2.get("translationY");
                        i2 = height;
                    }
                    HashMap<String, rn3> map3 = bj1Var2.y;
                    i = width;
                    if (map3 == null) {
                        matrix = matrix3;
                        rn3Var3 = null;
                    } else {
                        rn3Var3 = map3.get("rotation");
                        matrix = matrix3;
                    }
                    HashMap<String, rn3> map4 = bj1Var2.y;
                    if (map4 == null) {
                        f = f6;
                        rn3Var4 = null;
                    } else {
                        rn3Var4 = map4.get("scaleX");
                        f = f6;
                    }
                    HashMap<String, rn3> map5 = bj1Var2.y;
                    if (map5 == null) {
                        i7 = width2;
                        rn3Var5 = null;
                    } else {
                        rn3Var5 = map5.get("scaleY");
                        i7 = width2;
                    }
                    HashMap<String, dn3> map6 = bj1Var2.z;
                    dn3 dn3Var2 = map6 == null ? null : map6.get("translationX");
                    HashMap<String, dn3> map7 = bj1Var2.z;
                    dn3 dn3Var3 = map7 == null ? null : map7.get("translationY");
                    HashMap<String, dn3> map8 = bj1Var2.z;
                    dn3 dn3Var4 = map8 == null ? null : map8.get("rotation");
                    HashMap<String, dn3> map9 = bj1Var2.z;
                    dn3 dn3Var5 = map9 == null ? null : map9.get("scaleX");
                    HashMap<String, dn3> map10 = bj1Var2.z;
                    dn3 dn3Var6 = map10 != null ? map10.get("scaleY") : null;
                    fl3 fl3Var2 = new fl3();
                    fl3Var2.e = 0.0f;
                    fl3Var2.d = 0.0f;
                    fl3Var2.c = 0.0f;
                    fl3Var2.b = 0.0f;
                    fl3Var2.a = 0.0f;
                    if (rn3Var3 != null) {
                        bj1Var = bj1Var2;
                        dn3Var = dn3Var3;
                        fl3Var2.e = (float) rn3Var3.a.e(fB);
                        fl3Var2.f = rn3Var3.a(fB);
                    } else {
                        bj1Var = bj1Var2;
                        dn3Var = dn3Var3;
                    }
                    if (rn3Var != null) {
                        f2 = f4;
                        fl3Var2.c = (float) rn3Var.a.e(fB);
                    } else {
                        f2 = f4;
                    }
                    if (rn3Var2 != null) {
                        fl3Var2.d = (float) rn3Var2.a.e(fB);
                    }
                    if (rn3Var4 != null) {
                        fl3Var2.a = (float) rn3Var4.a.e(fB);
                    }
                    if (rn3Var5 != null) {
                        fl3Var2.b = (float) rn3Var5.a.e(fB);
                    }
                    if (dn3Var4 != null) {
                        fl3Var2.e = dn3Var4.b(fB);
                    }
                    if (dn3Var2 != null) {
                        fl3Var2.c = dn3Var2.b(fB);
                    }
                    dn3 dn3Var7 = dn3Var;
                    if (dn3Var != null) {
                        fl3Var2.d = dn3Var7.b(fB);
                    }
                    if (dn3Var5 != null) {
                        fl3Var2.a = dn3Var5.b(fB);
                    }
                    if (dn3Var6 != null) {
                        fl3Var2.b = dn3Var6.b(fB);
                    }
                    bj1 bj1Var3 = bj1Var;
                    d8 d8Var = bj1Var3.k;
                    ej1 ej1Var = bj1Var3.f;
                    if (d8Var != null) {
                        double[] dArr2 = bj1Var3.p;
                        if (dArr2.length > 0) {
                            double d = fB;
                            d8Var.c(d, dArr2);
                            bj1Var3.k.f(d, bj1Var3.q);
                            int[] iArr = bj1Var3.o;
                            double[] dArr3 = bj1Var3.q;
                            double[] dArr4 = bj1Var3.p;
                            ej1Var.getClass();
                            i8 = i12;
                            fl3Var = fl3Var2;
                            fArr3 = fArr5;
                            i5 = i6;
                            ej1.h(f2, f3, fArr5, iArr, dArr3, dArr4);
                        } else {
                            fArr3 = fArr5;
                            i8 = i12;
                            i5 = i6;
                            fl3Var = fl3Var2;
                        }
                        fl3Var.a(f2, f3, i7, height2, fArr3);
                        i4 = i8;
                        fArr2 = fArr3;
                    } else {
                        i4 = i12;
                        i5 = i6;
                        if (bj1Var3.j != null) {
                            double dB = bj1Var3.b(fB, fArr6);
                            bj1Var3.j[0].f(dB, bj1Var3.q);
                            bj1Var3.j[0].c(dB, bj1Var3.p);
                            float f7 = fArr6[0];
                            int i13 = 0;
                            while (true) {
                                dArr = bj1Var3.q;
                                if (i13 >= dArr.length) {
                                    break;
                                }
                                dArr[i13] = dArr[i13] * ((double) f7);
                                i13++;
                            }
                            int[] iArr2 = bj1Var3.o;
                            double[] dArr5 = bj1Var3.p;
                            ej1Var.getClass();
                            fArr2 = fArr5;
                            ej1.h(f2, f3, fArr5, iArr2, dArr, dArr5);
                            fl3Var2.a(f2, f3, i7, height2, fArr2);
                        } else {
                            ej1 ej1Var2 = bj1Var3.g;
                            dn3 dn3Var8 = dn3Var5;
                            float f8 = ej1Var2.i - ej1Var.i;
                            float f9 = ej1Var2.j - ej1Var.j;
                            dn3 dn3Var9 = dn3Var2;
                            float f10 = ej1Var2.k - ej1Var.k;
                            float f11 = (ej1Var2.l - ej1Var.l) + f9;
                            fArr5[0] = ((f10 + f8) * f2) + ((1.0f - f2) * f8);
                            fArr5[1] = (f11 * f3) + ((1.0f - f3) * f9);
                            fl3Var2.e = 0.0f;
                            fl3Var2.d = 0.0f;
                            fl3Var2.c = 0.0f;
                            fl3Var2.b = 0.0f;
                            fl3Var2.a = 0.0f;
                            if (rn3Var3 != null) {
                                fl3Var2.e = (float) rn3Var3.a.e(fB);
                                fl3Var2.f = rn3Var3.a(fB);
                            }
                            if (rn3Var != null) {
                                fl3Var2.c = (float) rn3Var.a.e(fB);
                            }
                            if (rn3Var2 != null) {
                                fl3Var2.d = (float) rn3Var2.a.e(fB);
                            }
                            if (rn3Var4 != null) {
                                fl3Var2.a = (float) rn3Var4.a.e(fB);
                            }
                            if (rn3Var5 != null) {
                                fl3Var2.b = (float) rn3Var5.a.e(fB);
                            }
                            if (dn3Var4 != null) {
                                fl3Var2.e = dn3Var4.b(fB);
                            }
                            if (dn3Var9 != null) {
                                fl3Var2.c = dn3Var9.b(fB);
                            }
                            if (dn3Var7 != null) {
                                fl3Var2.d = dn3Var7.b(fB);
                            }
                            if (dn3Var8 != null) {
                                fl3Var2.a = dn3Var8.b(fB);
                            }
                            if (dn3Var6 != null) {
                                fl3Var2.b = dn3Var6.b(fB);
                            }
                            fArr2 = fArr5;
                            fl3Var2.a(f2, f3, i7, height2, fArr2);
                        }
                    }
                } else {
                    i = width;
                    matrix = matrix3;
                    i2 = height;
                    f = f6;
                    i3 = i10;
                    i4 = i12;
                    fArr2 = fArr5;
                    f2 = f4;
                    i5 = i11;
                    bj1Var2.d(f5, f2, f3, fArr2);
                }
                if (i4 < 2) {
                    fArr2[0] = fArr2[0] * f;
                    fArr2[1] = fArr2[1] * f;
                }
                float[] fArr7 = this.r;
                matrix3 = matrix;
                matrix3.mapVectors(fArr7);
                int i14 = i;
                float f12 = i14 * f2;
                int i15 = i2;
                float f13 = i15 * f3;
                float f14 = fArr7[0];
                float f15 = this.v;
                float f16 = f13 - (fArr7[1] * f15);
                matrix3.mapVectors(fArr7);
                canvas.drawLine(f12, f13, f12 - (f14 * f15), f16, this.p);
                i11 = i5 + 1;
                motionTelltales = this;
                width = i14;
                height = i15;
                fArr4 = fArr;
                i10 = i3;
                i9 = 5;
            }
            i10++;
            height = height;
            fArr4 = fArr4;
            i9 = 5;
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        postInvalidate();
    }

    public void setText(CharSequence charSequence) {
        this.j = charSequence.toString();
        requestLayout();
    }

    public MotionTelltales(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.p = new Paint();
        this.r = new float[2];
        this.s = new Matrix();
        this.t = 0;
        this.u = -65281;
        this.v = 0.25f;
        a(context, attributeSet);
    }

    public MotionTelltales(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.p = new Paint();
        this.r = new float[2];
        this.s = new Matrix();
        this.t = 0;
        this.u = -65281;
        this.v = 0.25f;
        a(context, attributeSet);
    }
}
