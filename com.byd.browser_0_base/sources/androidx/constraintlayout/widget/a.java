package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import defpackage.C0175b;
import defpackage.b70;
import defpackage.hf0;
import defpackage.ox;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintSet.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final int[] h = {0, 4, 8};
    public static final SparseIntArray i;
    public static final SparseIntArray j;
    public String a;
    public String b = "";
    public String[] c = new String[0];
    public int d = 0;
    public final HashMap<String, ox> e = new HashMap<>();
    public boolean f = true;
    public final HashMap<Integer, C0004a> g = new HashMap<>();

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class C0004a {
        public int a;
        public String b;
        public final d c = new d();
        public final c d = new c();
        public final b e = new b();
        public final e f = new e();
        public HashMap<String, ox> g = new HashMap<>();
        public C0005a h;

        /* JADX INFO: renamed from: androidx.constraintlayout.widget.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ConstraintSet.java */
        public static class C0005a {
            public int[] a = new int[10];
            public int[] b = new int[10];
            public int c = 0;
            public int[] d = new int[10];
            public float[] e = new float[10];
            public int f = 0;
            public int[] g = new int[5];
            public String[] h = new String[5];
            public int i = 0;
            public int[] j = new int[4];
            public boolean[] k = new boolean[4];
            public int l = 0;

            public final void a(float f, int i) {
                int i2 = this.f;
                int[] iArr = this.d;
                if (i2 >= iArr.length) {
                    this.d = Arrays.copyOf(iArr, iArr.length * 2);
                    float[] fArr = this.e;
                    this.e = Arrays.copyOf(fArr, fArr.length * 2);
                }
                int[] iArr2 = this.d;
                int i3 = this.f;
                iArr2[i3] = i;
                float[] fArr2 = this.e;
                this.f = i3 + 1;
                fArr2[i3] = f;
            }

            public final void b(int i, int i2) {
                int i3 = this.c;
                int[] iArr = this.a;
                if (i3 >= iArr.length) {
                    this.a = Arrays.copyOf(iArr, iArr.length * 2);
                    int[] iArr2 = this.b;
                    this.b = Arrays.copyOf(iArr2, iArr2.length * 2);
                }
                int[] iArr3 = this.a;
                int i4 = this.c;
                iArr3[i4] = i;
                int[] iArr4 = this.b;
                this.c = i4 + 1;
                iArr4[i4] = i2;
            }

            public final void c(int i, String str) {
                int i2 = this.i;
                int[] iArr = this.g;
                if (i2 >= iArr.length) {
                    this.g = Arrays.copyOf(iArr, iArr.length * 2);
                    String[] strArr = this.h;
                    this.h = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                }
                int[] iArr2 = this.g;
                int i3 = this.i;
                iArr2[i3] = i;
                String[] strArr2 = this.h;
                this.i = i3 + 1;
                strArr2[i3] = str;
            }

            public final void d(int i, boolean z) {
                int i2 = this.l;
                int[] iArr = this.j;
                if (i2 >= iArr.length) {
                    this.j = Arrays.copyOf(iArr, iArr.length * 2);
                    boolean[] zArr = this.k;
                    this.k = Arrays.copyOf(zArr, zArr.length * 2);
                }
                int[] iArr2 = this.j;
                int i3 = this.l;
                iArr2[i3] = i;
                boolean[] zArr2 = this.k;
                this.l = i3 + 1;
                zArr2[i3] = z;
            }

            public final void e(C0004a c0004a) {
                for (int i = 0; i < this.c; i++) {
                    int i2 = this.a[i];
                    int i3 = this.b[i];
                    int[] iArr = a.h;
                    if (i2 == 6) {
                        c0004a.e.D = i3;
                    } else if (i2 == 7) {
                        c0004a.e.E = i3;
                    } else if (i2 == 8) {
                        c0004a.e.K = i3;
                    } else if (i2 == 27) {
                        c0004a.e.F = i3;
                    } else if (i2 == 28) {
                        c0004a.e.H = i3;
                    } else if (i2 == 41) {
                        c0004a.e.W = i3;
                    } else if (i2 == 42) {
                        c0004a.e.X = i3;
                    } else if (i2 == 61) {
                        c0004a.e.A = i3;
                    } else if (i2 == 62) {
                        c0004a.e.B = i3;
                    } else if (i2 == 72) {
                        c0004a.e.g0 = i3;
                    } else if (i2 == 73) {
                        c0004a.e.h0 = i3;
                    } else if (i2 == 2) {
                        c0004a.e.f9J = i3;
                    } else if (i2 == 31) {
                        c0004a.e.L = i3;
                    } else if (i2 == 34) {
                        c0004a.e.I = i3;
                    } else if (i2 == 38) {
                        c0004a.a = i3;
                    } else if (i2 == 64) {
                        c0004a.d.b = i3;
                    } else if (i2 == 66) {
                        c0004a.d.f = i3;
                    } else if (i2 == 76) {
                        c0004a.d.e = i3;
                    } else if (i2 == 78) {
                        c0004a.c.c = i3;
                    } else if (i2 == 97) {
                        c0004a.e.p0 = i3;
                    } else if (i2 == 93) {
                        c0004a.e.M = i3;
                    } else if (i2 != 94) {
                        switch (i2) {
                            case 11:
                                c0004a.e.Q = i3;
                                break;
                            case 12:
                                c0004a.e.R = i3;
                                break;
                            case 13:
                                c0004a.e.N = i3;
                                break;
                            case 14:
                                c0004a.e.P = i3;
                                break;
                            case 15:
                                c0004a.e.S = i3;
                                break;
                            case 16:
                                c0004a.e.O = i3;
                                break;
                            case 17:
                                c0004a.e.e = i3;
                                break;
                            case 18:
                                c0004a.e.f = i3;
                                break;
                            default:
                                switch (i2) {
                                    case 21:
                                        c0004a.e.d = i3;
                                        break;
                                    case 22:
                                        c0004a.c.b = i3;
                                        break;
                                    case 23:
                                        c0004a.e.c = i3;
                                        break;
                                    case 24:
                                        c0004a.e.G = i3;
                                        break;
                                    default:
                                        switch (i2) {
                                            case 54:
                                                c0004a.e.Y = i3;
                                                break;
                                            case 55:
                                                c0004a.e.Z = i3;
                                                break;
                                            case 56:
                                                c0004a.e.a0 = i3;
                                                break;
                                            case 57:
                                                c0004a.e.b0 = i3;
                                                break;
                                            case 58:
                                                c0004a.e.c0 = i3;
                                                break;
                                            case 59:
                                                c0004a.e.d0 = i3;
                                                break;
                                            default:
                                                switch (i2) {
                                                    case 82:
                                                        c0004a.d.c = i3;
                                                        break;
                                                    case 83:
                                                        c0004a.f.i = i3;
                                                        break;
                                                    case 84:
                                                        c0004a.d.j = i3;
                                                        break;
                                                    default:
                                                        switch (i2) {
                                                            case 87:
                                                                break;
                                                            case 88:
                                                                c0004a.d.l = i3;
                                                                break;
                                                            case 89:
                                                                c0004a.d.m = i3;
                                                                break;
                                                            default:
                                                                Log.w("ConstraintSet", "Unknown attribute 0x");
                                                                break;
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                    } else {
                        c0004a.e.T = i3;
                    }
                }
                for (int i4 = 0; i4 < this.f; i4++) {
                    int i5 = this.d[i4];
                    float f = this.e[i4];
                    int[] iArr2 = a.h;
                    if (i5 == 19) {
                        c0004a.e.g = f;
                    } else if (i5 == 20) {
                        c0004a.e.x = f;
                    } else if (i5 == 37) {
                        c0004a.e.y = f;
                    } else if (i5 == 60) {
                        c0004a.f.b = f;
                    } else if (i5 == 63) {
                        c0004a.e.C = f;
                    } else if (i5 == 79) {
                        c0004a.d.g = f;
                    } else if (i5 == 85) {
                        c0004a.d.i = f;
                    } else if (i5 != 87) {
                        if (i5 == 39) {
                            c0004a.e.V = f;
                        } else if (i5 != 40) {
                            switch (i5) {
                                case 43:
                                    c0004a.c.d = f;
                                    break;
                                case 44:
                                    e eVar = c0004a.f;
                                    eVar.n = f;
                                    eVar.m = true;
                                    break;
                                case 45:
                                    c0004a.f.c = f;
                                    break;
                                case 46:
                                    c0004a.f.d = f;
                                    break;
                                case 47:
                                    c0004a.f.e = f;
                                    break;
                                case 48:
                                    c0004a.f.f = f;
                                    break;
                                case 49:
                                    c0004a.f.g = f;
                                    break;
                                case 50:
                                    c0004a.f.h = f;
                                    break;
                                case 51:
                                    c0004a.f.j = f;
                                    break;
                                case 52:
                                    c0004a.f.k = f;
                                    break;
                                case 53:
                                    c0004a.f.l = f;
                                    break;
                                default:
                                    switch (i5) {
                                        case 67:
                                            c0004a.d.h = f;
                                            break;
                                        case 68:
                                            c0004a.c.e = f;
                                            break;
                                        case 69:
                                            c0004a.e.e0 = f;
                                            break;
                                        case 70:
                                            c0004a.e.f0 = f;
                                            break;
                                        default:
                                            Log.w("ConstraintSet", "Unknown attribute 0x");
                                            break;
                                    }
                                    break;
                            }
                        } else {
                            c0004a.e.U = f;
                        }
                    }
                }
                for (int i6 = 0; i6 < this.i; i6++) {
                    int i7 = this.g[i6];
                    String str = this.h[i6];
                    int[] iArr3 = a.h;
                    if (i7 == 5) {
                        c0004a.e.z = str;
                    } else if (i7 == 65) {
                        c0004a.d.d = str;
                    } else if (i7 == 74) {
                        b bVar = c0004a.e;
                        bVar.k0 = str;
                        bVar.j0 = null;
                    } else if (i7 == 77) {
                        c0004a.e.l0 = str;
                    } else if (i7 != 87) {
                        if (i7 != 90) {
                            Log.w("ConstraintSet", "Unknown attribute 0x");
                        } else {
                            c0004a.d.k = str;
                        }
                    }
                }
                for (int i8 = 0; i8 < this.l; i8++) {
                    int i9 = this.j[i8];
                    boolean z = this.k[i8];
                    int[] iArr4 = a.h;
                    if (i9 == 44) {
                        c0004a.f.m = z;
                    } else if (i9 == 75) {
                        c0004a.e.o0 = z;
                    } else if (i9 != 87) {
                        if (i9 == 80) {
                            c0004a.e.m0 = z;
                        } else if (i9 != 81) {
                            Log.w("ConstraintSet", "Unknown attribute 0x");
                        } else {
                            c0004a.e.n0 = z;
                        }
                    }
                }
            }
        }

        public final void a(ConstraintLayout.LayoutParams layoutParams) {
            b bVar = this.e;
            layoutParams.e = bVar.i;
            layoutParams.f = bVar.j;
            layoutParams.g = bVar.k;
            layoutParams.h = bVar.l;
            layoutParams.i = bVar.m;
            layoutParams.j = bVar.n;
            layoutParams.k = bVar.o;
            layoutParams.l = bVar.p;
            layoutParams.m = bVar.q;
            layoutParams.n = bVar.r;
            layoutParams.o = bVar.s;
            layoutParams.s = bVar.t;
            layoutParams.t = bVar.u;
            layoutParams.u = bVar.v;
            layoutParams.v = bVar.w;
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = bVar.G;
            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = bVar.H;
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = bVar.I;
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = bVar.f9J;
            layoutParams.A = bVar.S;
            layoutParams.B = bVar.R;
            layoutParams.x = bVar.O;
            layoutParams.z = bVar.Q;
            layoutParams.E = bVar.x;
            layoutParams.F = bVar.y;
            layoutParams.p = bVar.A;
            layoutParams.q = bVar.B;
            layoutParams.r = bVar.C;
            layoutParams.G = bVar.z;
            layoutParams.T = bVar.D;
            layoutParams.U = bVar.E;
            layoutParams.I = bVar.U;
            layoutParams.H = bVar.V;
            layoutParams.K = bVar.X;
            layoutParams.f8J = bVar.W;
            layoutParams.W = bVar.m0;
            layoutParams.X = bVar.n0;
            layoutParams.L = bVar.Y;
            layoutParams.M = bVar.Z;
            layoutParams.P = bVar.a0;
            layoutParams.Q = bVar.b0;
            layoutParams.N = bVar.c0;
            layoutParams.O = bVar.d0;
            layoutParams.R = bVar.e0;
            layoutParams.S = bVar.f0;
            layoutParams.V = bVar.F;
            layoutParams.c = bVar.g;
            layoutParams.a = bVar.e;
            layoutParams.b = bVar.f;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = bVar.c;
            ((ViewGroup.MarginLayoutParams) layoutParams).height = bVar.d;
            String str = bVar.l0;
            if (str != null) {
                layoutParams.Y = str;
            }
            layoutParams.Z = bVar.p0;
            layoutParams.setMarginStart(bVar.L);
            layoutParams.setMarginEnd(bVar.K);
            layoutParams.a();
        }

        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final C0004a clone() {
            C0004a c0004a = new C0004a();
            c0004a.e.a(this.e);
            c0004a.d.a(this.d);
            d dVar = c0004a.c;
            dVar.getClass();
            d dVar2 = this.c;
            dVar.a = dVar2.a;
            dVar.b = dVar2.b;
            dVar.d = dVar2.d;
            dVar.e = dVar2.e;
            dVar.c = dVar2.c;
            c0004a.f.a(this.f);
            c0004a.a = this.a;
            c0004a.h = this.h;
            return c0004a;
        }

        public final void c(int i, ConstraintLayout.LayoutParams layoutParams) {
            this.a = i;
            int i2 = layoutParams.e;
            b bVar = this.e;
            bVar.i = i2;
            bVar.j = layoutParams.f;
            bVar.k = layoutParams.g;
            bVar.l = layoutParams.h;
            bVar.m = layoutParams.i;
            bVar.n = layoutParams.j;
            bVar.o = layoutParams.k;
            bVar.p = layoutParams.l;
            bVar.q = layoutParams.m;
            bVar.r = layoutParams.n;
            bVar.s = layoutParams.o;
            bVar.t = layoutParams.s;
            bVar.u = layoutParams.t;
            bVar.v = layoutParams.u;
            bVar.w = layoutParams.v;
            bVar.x = layoutParams.E;
            bVar.y = layoutParams.F;
            bVar.z = layoutParams.G;
            bVar.A = layoutParams.p;
            bVar.B = layoutParams.q;
            bVar.C = layoutParams.r;
            bVar.D = layoutParams.T;
            bVar.E = layoutParams.U;
            bVar.F = layoutParams.V;
            bVar.g = layoutParams.c;
            bVar.e = layoutParams.a;
            bVar.f = layoutParams.b;
            bVar.c = ((ViewGroup.MarginLayoutParams) layoutParams).width;
            bVar.d = ((ViewGroup.MarginLayoutParams) layoutParams).height;
            bVar.G = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            bVar.H = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            bVar.I = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            bVar.f9J = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            bVar.M = layoutParams.D;
            bVar.U = layoutParams.I;
            bVar.V = layoutParams.H;
            bVar.X = layoutParams.K;
            bVar.W = layoutParams.f8J;
            bVar.m0 = layoutParams.W;
            bVar.n0 = layoutParams.X;
            bVar.Y = layoutParams.L;
            bVar.Z = layoutParams.M;
            bVar.a0 = layoutParams.P;
            bVar.b0 = layoutParams.Q;
            bVar.c0 = layoutParams.N;
            bVar.d0 = layoutParams.O;
            bVar.e0 = layoutParams.R;
            bVar.f0 = layoutParams.S;
            bVar.l0 = layoutParams.Y;
            bVar.O = layoutParams.x;
            bVar.Q = layoutParams.z;
            bVar.N = layoutParams.w;
            bVar.P = layoutParams.y;
            bVar.S = layoutParams.A;
            bVar.R = layoutParams.B;
            bVar.T = layoutParams.C;
            bVar.p0 = layoutParams.Z;
            bVar.K = layoutParams.getMarginEnd();
            bVar.L = layoutParams.getMarginStart();
        }

        public final void d(int i, Constraints.LayoutParams layoutParams) {
            c(i, layoutParams);
            this.c.d = layoutParams.r0;
            float f = layoutParams.u0;
            e eVar = this.f;
            eVar.b = f;
            eVar.c = layoutParams.v0;
            eVar.d = layoutParams.w0;
            eVar.e = layoutParams.x0;
            eVar.f = layoutParams.y0;
            eVar.g = layoutParams.z0;
            eVar.h = layoutParams.A0;
            eVar.j = layoutParams.B0;
            eVar.k = layoutParams.C0;
            eVar.l = layoutParams.D0;
            eVar.n = layoutParams.t0;
            eVar.m = layoutParams.s0;
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class b {
        public static final SparseIntArray q0;
        public int c;
        public int d;
        public int[] j0;
        public String k0;
        public String l0;
        public boolean a = false;
        public boolean b = false;
        public int e = -1;
        public int f = -1;
        public float g = -1.0f;
        public boolean h = true;
        public int i = -1;
        public int j = -1;
        public int k = -1;
        public int l = -1;
        public int m = -1;
        public int n = -1;
        public int o = -1;
        public int p = -1;
        public int q = -1;
        public int r = -1;
        public int s = -1;
        public int t = -1;
        public int u = -1;
        public int v = -1;
        public int w = -1;
        public float x = 0.5f;
        public float y = 0.5f;
        public String z = null;
        public int A = -1;
        public int B = 0;
        public float C = 0.0f;
        public int D = -1;
        public int E = -1;
        public int F = -1;
        public int G = 0;
        public int H = 0;
        public int I = 0;

        /* JADX INFO: renamed from: J, reason: collision with root package name */
        public int f9J = 0;
        public int K = 0;
        public int L = 0;
        public int M = 0;
        public int N = Integer.MIN_VALUE;
        public int O = Integer.MIN_VALUE;
        public int P = Integer.MIN_VALUE;
        public int Q = Integer.MIN_VALUE;
        public int R = Integer.MIN_VALUE;
        public int S = Integer.MIN_VALUE;
        public int T = Integer.MIN_VALUE;
        public float U = -1.0f;
        public float V = -1.0f;
        public int W = 0;
        public int X = 0;
        public int Y = 0;
        public int Z = 0;
        public int a0 = 0;
        public int b0 = 0;
        public int c0 = 0;
        public int d0 = 0;
        public float e0 = 1.0f;
        public float f0 = 1.0f;
        public int g0 = -1;
        public int h0 = 0;
        public int i0 = -1;
        public boolean m0 = false;
        public boolean n0 = false;
        public boolean o0 = true;
        public int p0 = 0;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            q0 = sparseIntArray;
            sparseIntArray.append(R$styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            sparseIntArray.append(R$styleable.Layout_layout_constraintLeft_toRightOf, 25);
            sparseIntArray.append(R$styleable.Layout_layout_constraintRight_toLeftOf, 28);
            sparseIntArray.append(R$styleable.Layout_layout_constraintRight_toRightOf, 29);
            sparseIntArray.append(R$styleable.Layout_layout_constraintTop_toTopOf, 35);
            sparseIntArray.append(R$styleable.Layout_layout_constraintTop_toBottomOf, 34);
            sparseIntArray.append(R$styleable.Layout_layout_constraintBottom_toTopOf, 4);
            sparseIntArray.append(R$styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            sparseIntArray.append(R$styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            sparseIntArray.append(R$styleable.Layout_layout_editor_absoluteX, 6);
            sparseIntArray.append(R$styleable.Layout_layout_editor_absoluteY, 7);
            sparseIntArray.append(R$styleable.Layout_layout_constraintGuide_begin, 17);
            sparseIntArray.append(R$styleable.Layout_layout_constraintGuide_end, 18);
            sparseIntArray.append(R$styleable.Layout_layout_constraintGuide_percent, 19);
            int i = R$styleable.Layout_guidelineUseRtl;
            sparseIntArray.append(i, 90);
            sparseIntArray.append(R$styleable.Layout_android_orientation, 26);
            sparseIntArray.append(R$styleable.Layout_layout_constraintStart_toEndOf, 31);
            sparseIntArray.append(R$styleable.Layout_layout_constraintStart_toStartOf, 32);
            sparseIntArray.append(R$styleable.Layout_layout_constraintEnd_toStartOf, 10);
            sparseIntArray.append(R$styleable.Layout_layout_constraintEnd_toEndOf, 9);
            sparseIntArray.append(R$styleable.Layout_layout_goneMarginLeft, 13);
            sparseIntArray.append(R$styleable.Layout_layout_goneMarginTop, 16);
            sparseIntArray.append(R$styleable.Layout_layout_goneMarginRight, 14);
            sparseIntArray.append(R$styleable.Layout_layout_goneMarginBottom, 11);
            sparseIntArray.append(R$styleable.Layout_layout_goneMarginStart, 15);
            sparseIntArray.append(R$styleable.Layout_layout_goneMarginEnd, 12);
            sparseIntArray.append(R$styleable.Layout_layout_constraintVertical_weight, 38);
            sparseIntArray.append(R$styleable.Layout_layout_constraintHorizontal_weight, 37);
            sparseIntArray.append(R$styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            sparseIntArray.append(R$styleable.Layout_layout_constraintVertical_chainStyle, 40);
            sparseIntArray.append(R$styleable.Layout_layout_constraintHorizontal_bias, 20);
            sparseIntArray.append(R$styleable.Layout_layout_constraintVertical_bias, 36);
            sparseIntArray.append(R$styleable.Layout_layout_constraintDimensionRatio, 5);
            sparseIntArray.append(R$styleable.Layout_layout_constraintLeft_creator, 91);
            sparseIntArray.append(R$styleable.Layout_layout_constraintTop_creator, 91);
            sparseIntArray.append(R$styleable.Layout_layout_constraintRight_creator, 91);
            sparseIntArray.append(R$styleable.Layout_layout_constraintBottom_creator, 91);
            sparseIntArray.append(R$styleable.Layout_layout_constraintBaseline_creator, 91);
            sparseIntArray.append(R$styleable.Layout_android_layout_marginLeft, 23);
            sparseIntArray.append(R$styleable.Layout_android_layout_marginRight, 27);
            sparseIntArray.append(R$styleable.Layout_android_layout_marginStart, 30);
            sparseIntArray.append(R$styleable.Layout_android_layout_marginEnd, 8);
            sparseIntArray.append(R$styleable.Layout_android_layout_marginTop, 33);
            sparseIntArray.append(R$styleable.Layout_android_layout_marginBottom, 2);
            sparseIntArray.append(R$styleable.Layout_android_layout_width, 22);
            sparseIntArray.append(R$styleable.Layout_android_layout_height, 21);
            int i2 = R$styleable.Layout_layout_constraintWidth;
            sparseIntArray.append(i2, 41);
            int i3 = R$styleable.Layout_layout_constraintHeight;
            sparseIntArray.append(i3, 42);
            sparseIntArray.append(R$styleable.Layout_layout_constrainedWidth, 41);
            sparseIntArray.append(R$styleable.Layout_layout_constrainedHeight, 42);
            sparseIntArray.append(R$styleable.Layout_layout_wrapBehaviorInParent, 76);
            sparseIntArray.append(R$styleable.Layout_layout_constraintCircle, 61);
            sparseIntArray.append(R$styleable.Layout_layout_constraintCircleRadius, 62);
            sparseIntArray.append(R$styleable.Layout_layout_constraintCircleAngle, 63);
            sparseIntArray.append(R$styleable.Layout_layout_constraintWidth_percent, 69);
            sparseIntArray.append(R$styleable.Layout_layout_constraintHeight_percent, 70);
            sparseIntArray.append(R$styleable.Layout_chainUseRtl, 71);
            sparseIntArray.append(R$styleable.Layout_barrierDirection, 72);
            sparseIntArray.append(R$styleable.Layout_barrierMargin, 73);
            sparseIntArray.append(R$styleable.Layout_constraint_referenced_ids, 74);
            sparseIntArray.append(R$styleable.Layout_barrierAllowsGoneWidgets, 75);
            int i4 = R$styleable.Layout_layout_constraintWidth_max;
            sparseIntArray.append(i4, 84);
            sparseIntArray.append(R$styleable.Layout_layout_constraintWidth_min, 86);
            sparseIntArray.append(i4, 83);
            sparseIntArray.append(R$styleable.Layout_layout_constraintHeight_min, 85);
            sparseIntArray.append(i2, 87);
            sparseIntArray.append(i3, 88);
            sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTag, 89);
            sparseIntArray.append(i, 90);
        }

        public final void a(b bVar) {
            this.a = bVar.a;
            this.c = bVar.c;
            this.b = bVar.b;
            this.d = bVar.d;
            this.e = bVar.e;
            this.f = bVar.f;
            this.g = bVar.g;
            this.h = bVar.h;
            this.i = bVar.i;
            this.j = bVar.j;
            this.k = bVar.k;
            this.l = bVar.l;
            this.m = bVar.m;
            this.n = bVar.n;
            this.o = bVar.o;
            this.p = bVar.p;
            this.q = bVar.q;
            this.r = bVar.r;
            this.s = bVar.s;
            this.t = bVar.t;
            this.u = bVar.u;
            this.v = bVar.v;
            this.w = bVar.w;
            this.x = bVar.x;
            this.y = bVar.y;
            this.z = bVar.z;
            this.A = bVar.A;
            this.B = bVar.B;
            this.C = bVar.C;
            this.D = bVar.D;
            this.E = bVar.E;
            this.F = bVar.F;
            this.G = bVar.G;
            this.H = bVar.H;
            this.I = bVar.I;
            this.f9J = bVar.f9J;
            this.K = bVar.K;
            this.L = bVar.L;
            this.M = bVar.M;
            this.N = bVar.N;
            this.O = bVar.O;
            this.P = bVar.P;
            this.Q = bVar.Q;
            this.R = bVar.R;
            this.S = bVar.S;
            this.T = bVar.T;
            this.U = bVar.U;
            this.V = bVar.V;
            this.W = bVar.W;
            this.X = bVar.X;
            this.Y = bVar.Y;
            this.Z = bVar.Z;
            this.a0 = bVar.a0;
            this.b0 = bVar.b0;
            this.c0 = bVar.c0;
            this.d0 = bVar.d0;
            this.e0 = bVar.e0;
            this.f0 = bVar.f0;
            this.g0 = bVar.g0;
            this.h0 = bVar.h0;
            this.i0 = bVar.i0;
            this.l0 = bVar.l0;
            int[] iArr = bVar.j0;
            if (iArr == null || bVar.k0 != null) {
                this.j0 = null;
            } else {
                this.j0 = Arrays.copyOf(iArr, iArr.length);
            }
            this.k0 = bVar.k0;
            this.m0 = bVar.m0;
            this.n0 = bVar.n0;
            this.o0 = bVar.o0;
            this.p0 = bVar.p0;
        }

        public final void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Layout);
            this.b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                SparseIntArray sparseIntArray = q0;
                int i2 = sparseIntArray.get(index);
                switch (i2) {
                    case 1:
                        this.q = a.m(typedArrayObtainStyledAttributes, index, this.q);
                        break;
                    case 2:
                        this.f9J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f9J);
                        break;
                    case 3:
                        this.p = a.m(typedArrayObtainStyledAttributes, index, this.p);
                        break;
                    case 4:
                        this.o = a.m(typedArrayObtainStyledAttributes, index, this.o);
                        break;
                    case 5:
                        this.z = typedArrayObtainStyledAttributes.getString(index);
                        break;
                    case 6:
                        this.D = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.D);
                        break;
                    case 7:
                        this.E = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.E);
                        break;
                    case 8:
                        this.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.K);
                        break;
                    case 9:
                        this.w = a.m(typedArrayObtainStyledAttributes, index, this.w);
                        break;
                    case 10:
                        this.v = a.m(typedArrayObtainStyledAttributes, index, this.v);
                        break;
                    case 11:
                        this.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.Q);
                        break;
                    case 12:
                        this.R = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.R);
                        break;
                    case 13:
                        this.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.N);
                        break;
                    case 14:
                        this.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.P);
                        break;
                    case 15:
                        this.S = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.S);
                        break;
                    case 16:
                        this.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.O);
                        break;
                    case 17:
                        this.e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.e);
                        break;
                    case 18:
                        this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f);
                        break;
                    case 19:
                        this.g = typedArrayObtainStyledAttributes.getFloat(index, this.g);
                        break;
                    case 20:
                        this.x = typedArrayObtainStyledAttributes.getFloat(index, this.x);
                        break;
                    case 21:
                        this.d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.d);
                        break;
                    case 22:
                        this.c = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.c);
                        break;
                    case 23:
                        this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.G);
                        break;
                    case 24:
                        this.i = a.m(typedArrayObtainStyledAttributes, index, this.i);
                        break;
                    case 25:
                        this.j = a.m(typedArrayObtainStyledAttributes, index, this.j);
                        break;
                    case 26:
                        this.F = typedArrayObtainStyledAttributes.getInt(index, this.F);
                        break;
                    case 27:
                        this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.H);
                        break;
                    case 28:
                        this.k = a.m(typedArrayObtainStyledAttributes, index, this.k);
                        break;
                    case 29:
                        this.l = a.m(typedArrayObtainStyledAttributes, index, this.l);
                        break;
                    case 30:
                        this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.L);
                        break;
                    case 31:
                        this.t = a.m(typedArrayObtainStyledAttributes, index, this.t);
                        break;
                    case 32:
                        this.u = a.m(typedArrayObtainStyledAttributes, index, this.u);
                        break;
                    case 33:
                        this.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.I);
                        break;
                    case 34:
                        this.n = a.m(typedArrayObtainStyledAttributes, index, this.n);
                        break;
                    case 35:
                        this.m = a.m(typedArrayObtainStyledAttributes, index, this.m);
                        break;
                    case 36:
                        this.y = typedArrayObtainStyledAttributes.getFloat(index, this.y);
                        break;
                    case 37:
                        this.V = typedArrayObtainStyledAttributes.getFloat(index, this.V);
                        break;
                    case 38:
                        this.U = typedArrayObtainStyledAttributes.getFloat(index, this.U);
                        break;
                    case 39:
                        this.W = typedArrayObtainStyledAttributes.getInt(index, this.W);
                        break;
                    case 40:
                        this.X = typedArrayObtainStyledAttributes.getInt(index, this.X);
                        break;
                    case 41:
                        a.n(this, typedArrayObtainStyledAttributes, index, 0);
                        break;
                    case 42:
                        a.n(this, typedArrayObtainStyledAttributes, index, 1);
                        break;
                    default:
                        switch (i2) {
                            case 61:
                                this.A = a.m(typedArrayObtainStyledAttributes, index, this.A);
                                break;
                            case 62:
                                this.B = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.B);
                                break;
                            case 63:
                                this.C = typedArrayObtainStyledAttributes.getFloat(index, this.C);
                                break;
                            default:
                                switch (i2) {
                                    case 69:
                                        this.e0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 70:
                                        this.f0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 71:
                                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                        break;
                                    case 72:
                                        this.g0 = typedArrayObtainStyledAttributes.getInt(index, this.g0);
                                        break;
                                    case 73:
                                        this.h0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.h0);
                                        break;
                                    case 74:
                                        this.k0 = typedArrayObtainStyledAttributes.getString(index);
                                        break;
                                    case 75:
                                        this.o0 = typedArrayObtainStyledAttributes.getBoolean(index, this.o0);
                                        break;
                                    case 76:
                                        this.p0 = typedArrayObtainStyledAttributes.getInt(index, this.p0);
                                        break;
                                    case 77:
                                        this.r = a.m(typedArrayObtainStyledAttributes, index, this.r);
                                        break;
                                    case 78:
                                        this.s = a.m(typedArrayObtainStyledAttributes, index, this.s);
                                        break;
                                    case 79:
                                        this.T = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.T);
                                        break;
                                    case 80:
                                        this.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.M);
                                        break;
                                    case 81:
                                        this.Y = typedArrayObtainStyledAttributes.getInt(index, this.Y);
                                        break;
                                    case 82:
                                        this.Z = typedArrayObtainStyledAttributes.getInt(index, this.Z);
                                        break;
                                    case 83:
                                        this.b0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.b0);
                                        break;
                                    case 84:
                                        this.a0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.a0);
                                        break;
                                    case 85:
                                        this.d0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.d0);
                                        break;
                                    case 86:
                                        this.c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.c0);
                                        break;
                                    case 87:
                                        this.m0 = typedArrayObtainStyledAttributes.getBoolean(index, this.m0);
                                        break;
                                    case 88:
                                        this.n0 = typedArrayObtainStyledAttributes.getBoolean(index, this.n0);
                                        break;
                                    case 89:
                                        this.l0 = typedArrayObtainStyledAttributes.getString(index);
                                        break;
                                    case 90:
                                        this.h = typedArrayObtainStyledAttributes.getBoolean(index, this.h);
                                        break;
                                    case 91:
                                        Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                                        break;
                                    default:
                                        Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                                        break;
                                }
                                break;
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class c {
        public static final SparseIntArray n;
        public boolean a = false;
        public int b = -1;
        public int c = 0;
        public String d = null;
        public int e = -1;
        public int f = 0;
        public float g = Float.NaN;
        public float h = Float.NaN;
        public float i = Float.NaN;
        public int j = -1;
        public String k = null;
        public int l = -3;
        public int m = -1;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            n = sparseIntArray;
            sparseIntArray.append(R$styleable.Motion_motionPathRotate, 1);
            sparseIntArray.append(R$styleable.Motion_pathMotionArc, 2);
            sparseIntArray.append(R$styleable.Motion_transitionEasing, 3);
            sparseIntArray.append(R$styleable.Motion_drawPath, 4);
            sparseIntArray.append(R$styleable.Motion_animateRelativeTo, 5);
            sparseIntArray.append(R$styleable.Motion_animateCircleAngleTo, 6);
            sparseIntArray.append(R$styleable.Motion_motionStagger, 7);
            sparseIntArray.append(R$styleable.Motion_quantizeMotionSteps, 8);
            sparseIntArray.append(R$styleable.Motion_quantizeMotionPhase, 9);
            sparseIntArray.append(R$styleable.Motion_quantizeMotionInterpolator, 10);
        }

        public final void a(c cVar) {
            this.a = cVar.a;
            this.b = cVar.b;
            this.d = cVar.d;
            this.e = cVar.e;
            this.f = cVar.f;
            this.h = cVar.h;
            this.g = cVar.g;
        }

        public final void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Motion);
            this.a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (n.get(index)) {
                    case 1:
                        this.h = typedArrayObtainStyledAttributes.getFloat(index, this.h);
                        break;
                    case 2:
                        this.e = typedArrayObtainStyledAttributes.getInt(index, this.e);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.d = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.d = hf0.c[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.b = a.m(typedArrayObtainStyledAttributes, index, this.b);
                        break;
                    case 6:
                        this.c = typedArrayObtainStyledAttributes.getInteger(index, this.c);
                        break;
                    case 7:
                        this.g = typedArrayObtainStyledAttributes.getFloat(index, this.g);
                        break;
                    case 8:
                        this.j = typedArrayObtainStyledAttributes.getInteger(index, this.j);
                        break;
                    case 9:
                        this.i = typedArrayObtainStyledAttributes.getFloat(index, this.i);
                        break;
                    case 10:
                        int i2 = typedArrayObtainStyledAttributes.peekValue(index).type;
                        if (i2 == 1) {
                            int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                            this.m = resourceId;
                            if (resourceId != -1) {
                                this.l = -2;
                            }
                        } else if (i2 == 3) {
                            String string = typedArrayObtainStyledAttributes.getString(index);
                            this.k = string;
                            if (string.indexOf("/") > 0) {
                                this.m = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                                this.l = -2;
                            } else {
                                this.l = -1;
                            }
                        } else {
                            this.l = typedArrayObtainStyledAttributes.getInteger(index, this.m);
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class d {
        public boolean a = false;
        public int b = 0;
        public int c = 0;
        public float d = 1.0f;
        public float e = Float.NaN;

        public final void a(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PropertySet);
            this.a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.PropertySet_android_alpha) {
                    this.d = typedArrayObtainStyledAttributes.getFloat(index, this.d);
                } else if (index == R$styleable.PropertySet_android_visibility) {
                    int i2 = typedArrayObtainStyledAttributes.getInt(index, this.b);
                    this.b = i2;
                    this.b = a.h[i2];
                } else if (index == R$styleable.PropertySet_visibilityMode) {
                    this.c = typedArrayObtainStyledAttributes.getInt(index, this.c);
                } else if (index == R$styleable.PropertySet_motionProgress) {
                    this.e = typedArrayObtainStyledAttributes.getFloat(index, this.e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class e {
        public static final SparseIntArray o;
        public boolean a = false;
        public float b = 0.0f;
        public float c = 0.0f;
        public float d = 0.0f;
        public float e = 1.0f;
        public float f = 1.0f;
        public float g = Float.NaN;
        public float h = Float.NaN;
        public int i = -1;
        public float j = 0.0f;
        public float k = 0.0f;
        public float l = 0.0f;
        public boolean m = false;
        public float n = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            o = sparseIntArray;
            sparseIntArray.append(R$styleable.Transform_android_rotation, 1);
            sparseIntArray.append(R$styleable.Transform_android_rotationX, 2);
            sparseIntArray.append(R$styleable.Transform_android_rotationY, 3);
            sparseIntArray.append(R$styleable.Transform_android_scaleX, 4);
            sparseIntArray.append(R$styleable.Transform_android_scaleY, 5);
            sparseIntArray.append(R$styleable.Transform_android_transformPivotX, 6);
            sparseIntArray.append(R$styleable.Transform_android_transformPivotY, 7);
            sparseIntArray.append(R$styleable.Transform_android_translationX, 8);
            sparseIntArray.append(R$styleable.Transform_android_translationY, 9);
            sparseIntArray.append(R$styleable.Transform_android_translationZ, 10);
            sparseIntArray.append(R$styleable.Transform_android_elevation, 11);
            sparseIntArray.append(R$styleable.Transform_transformPivotTarget, 12);
        }

        public final void a(e eVar) {
            this.a = eVar.a;
            this.b = eVar.b;
            this.c = eVar.c;
            this.d = eVar.d;
            this.e = eVar.e;
            this.f = eVar.f;
            this.g = eVar.g;
            this.h = eVar.h;
            this.i = eVar.i;
            this.j = eVar.j;
            this.k = eVar.k;
            this.l = eVar.l;
            this.m = eVar.m;
            this.n = eVar.n;
        }

        public final void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Transform);
            this.a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (o.get(index)) {
                    case 1:
                        this.b = typedArrayObtainStyledAttributes.getFloat(index, this.b);
                        break;
                    case 2:
                        this.c = typedArrayObtainStyledAttributes.getFloat(index, this.c);
                        break;
                    case 3:
                        this.d = typedArrayObtainStyledAttributes.getFloat(index, this.d);
                        break;
                    case 4:
                        this.e = typedArrayObtainStyledAttributes.getFloat(index, this.e);
                        break;
                    case 5:
                        this.f = typedArrayObtainStyledAttributes.getFloat(index, this.f);
                        break;
                    case 6:
                        this.g = typedArrayObtainStyledAttributes.getDimension(index, this.g);
                        break;
                    case 7:
                        this.h = typedArrayObtainStyledAttributes.getDimension(index, this.h);
                        break;
                    case 8:
                        this.j = typedArrayObtainStyledAttributes.getDimension(index, this.j);
                        break;
                    case 9:
                        this.k = typedArrayObtainStyledAttributes.getDimension(index, this.k);
                        break;
                    case 10:
                        this.l = typedArrayObtainStyledAttributes.getDimension(index, this.l);
                        break;
                    case 11:
                        this.m = true;
                        this.n = typedArrayObtainStyledAttributes.getDimension(index, this.n);
                        break;
                    case 12:
                        this.i = a.m(typedArrayObtainStyledAttributes, index, this.i);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        i = sparseIntArray;
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        j = sparseIntArray2;
        sparseIntArray.append(R$styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintRight_toRightOf, 30);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintTop_toTopOf, 36);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintBaseline_toTopOf, 91);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintBaseline_toBottomOf, 92);
        sparseIntArray.append(R$styleable.Constraint_layout_editor_absoluteX, 6);
        sparseIntArray.append(R$styleable.Constraint_layout_editor_absoluteY, 7);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintGuide_begin, 17);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintGuide_end, 18);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintGuide_percent, 19);
        sparseIntArray.append(R$styleable.Constraint_guidelineUseRtl, 99);
        sparseIntArray.append(R$styleable.Constraint_android_orientation, 27);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintStart_toEndOf, 32);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintStart_toStartOf, 33);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        sparseIntArray.append(R$styleable.Constraint_layout_goneMarginLeft, 13);
        sparseIntArray.append(R$styleable.Constraint_layout_goneMarginTop, 16);
        sparseIntArray.append(R$styleable.Constraint_layout_goneMarginRight, 14);
        sparseIntArray.append(R$styleable.Constraint_layout_goneMarginBottom, 11);
        sparseIntArray.append(R$styleable.Constraint_layout_goneMarginStart, 15);
        sparseIntArray.append(R$styleable.Constraint_layout_goneMarginEnd, 12);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintVertical_weight, 40);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHorizontal_weight, 39);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHorizontal_bias, 20);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintVertical_bias, 37);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintDimensionRatio, 5);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintLeft_creator, 87);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintTop_creator, 87);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintRight_creator, 87);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintBottom_creator, 87);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintBaseline_creator, 87);
        sparseIntArray.append(R$styleable.Constraint_android_layout_marginLeft, 24);
        sparseIntArray.append(R$styleable.Constraint_android_layout_marginRight, 28);
        sparseIntArray.append(R$styleable.Constraint_android_layout_marginStart, 31);
        sparseIntArray.append(R$styleable.Constraint_android_layout_marginEnd, 8);
        sparseIntArray.append(R$styleable.Constraint_android_layout_marginTop, 34);
        sparseIntArray.append(R$styleable.Constraint_android_layout_marginBottom, 2);
        sparseIntArray.append(R$styleable.Constraint_android_layout_width, 23);
        sparseIntArray.append(R$styleable.Constraint_android_layout_height, 21);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintWidth, 95);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHeight, 96);
        sparseIntArray.append(R$styleable.Constraint_android_visibility, 22);
        sparseIntArray.append(R$styleable.Constraint_android_alpha, 43);
        sparseIntArray.append(R$styleable.Constraint_android_elevation, 44);
        sparseIntArray.append(R$styleable.Constraint_android_rotationX, 45);
        sparseIntArray.append(R$styleable.Constraint_android_rotationY, 46);
        sparseIntArray.append(R$styleable.Constraint_android_rotation, 60);
        sparseIntArray.append(R$styleable.Constraint_android_scaleX, 47);
        sparseIntArray.append(R$styleable.Constraint_android_scaleY, 48);
        sparseIntArray.append(R$styleable.Constraint_android_transformPivotX, 49);
        sparseIntArray.append(R$styleable.Constraint_android_transformPivotY, 50);
        sparseIntArray.append(R$styleable.Constraint_android_translationX, 51);
        sparseIntArray.append(R$styleable.Constraint_android_translationY, 52);
        sparseIntArray.append(R$styleable.Constraint_android_translationZ, 53);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintWidth_default, 54);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHeight_default, 55);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintWidth_max, 56);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHeight_max, 57);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintWidth_min, 58);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHeight_min, 59);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintCircle, 61);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintCircleRadius, 62);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintCircleAngle, 63);
        sparseIntArray.append(R$styleable.Constraint_animateRelativeTo, 64);
        sparseIntArray.append(R$styleable.Constraint_transitionEasing, 65);
        sparseIntArray.append(R$styleable.Constraint_drawPath, 66);
        sparseIntArray.append(R$styleable.Constraint_transitionPathRotate, 67);
        sparseIntArray.append(R$styleable.Constraint_motionStagger, 79);
        sparseIntArray.append(R$styleable.Constraint_android_id, 38);
        sparseIntArray.append(R$styleable.Constraint_motionProgress, 68);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintWidth_percent, 69);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHeight_percent, 70);
        sparseIntArray.append(R$styleable.Constraint_layout_wrapBehaviorInParent, 97);
        sparseIntArray.append(R$styleable.Constraint_chainUseRtl, 71);
        sparseIntArray.append(R$styleable.Constraint_barrierDirection, 72);
        sparseIntArray.append(R$styleable.Constraint_barrierMargin, 73);
        sparseIntArray.append(R$styleable.Constraint_constraint_referenced_ids, 74);
        sparseIntArray.append(R$styleable.Constraint_barrierAllowsGoneWidgets, 75);
        sparseIntArray.append(R$styleable.Constraint_pathMotionArc, 76);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintTag, 77);
        sparseIntArray.append(R$styleable.Constraint_visibilityMode, 78);
        sparseIntArray.append(R$styleable.Constraint_layout_constrainedWidth, 80);
        sparseIntArray.append(R$styleable.Constraint_layout_constrainedHeight, 81);
        sparseIntArray.append(R$styleable.Constraint_polarRelativeTo, 82);
        sparseIntArray.append(R$styleable.Constraint_transformPivotTarget, 83);
        sparseIntArray.append(R$styleable.Constraint_quantizeMotionSteps, 84);
        sparseIntArray.append(R$styleable.Constraint_quantizeMotionPhase, 85);
        sparseIntArray.append(R$styleable.Constraint_quantizeMotionInterpolator, 86);
        int i2 = R$styleable.ConstraintOverride_layout_editor_absoluteY;
        sparseIntArray2.append(i2, 6);
        sparseIntArray2.append(i2, 7);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_orientation, 27);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_goneMarginLeft, 13);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_goneMarginTop, 16);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_goneMarginRight, 14);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_goneMarginBottom, 11);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_goneMarginStart, 15);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_goneMarginEnd, 12);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintVertical_weight, 40);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintHorizontal_weight, 39);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintHorizontal_chainStyle, 41);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintVertical_chainStyle, 42);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintHorizontal_bias, 20);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintVertical_bias, 37);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintDimensionRatio, 5);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintLeft_creator, 87);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintTop_creator, 87);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintRight_creator, 87);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintBottom_creator, 87);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintBaseline_creator, 87);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_layout_marginLeft, 24);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_layout_marginRight, 28);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_layout_marginStart, 31);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_layout_marginEnd, 8);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_layout_marginTop, 34);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_layout_marginBottom, 2);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_layout_width, 23);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_layout_height, 21);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintWidth, 95);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintHeight, 96);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_visibility, 22);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_alpha, 43);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_elevation, 44);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_rotationX, 45);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_rotationY, 46);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_rotation, 60);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_scaleX, 47);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_scaleY, 48);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_transformPivotX, 49);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_transformPivotY, 50);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_translationX, 51);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_translationY, 52);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_translationZ, 53);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintWidth_default, 54);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintHeight_default, 55);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintWidth_max, 56);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintHeight_max, 57);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintWidth_min, 58);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintHeight_min, 59);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintCircleRadius, 62);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintCircleAngle, 63);
        sparseIntArray2.append(R$styleable.ConstraintOverride_animateRelativeTo, 64);
        sparseIntArray2.append(R$styleable.ConstraintOverride_transitionEasing, 65);
        sparseIntArray2.append(R$styleable.ConstraintOverride_drawPath, 66);
        sparseIntArray2.append(R$styleable.ConstraintOverride_transitionPathRotate, 67);
        sparseIntArray2.append(R$styleable.ConstraintOverride_motionStagger, 79);
        sparseIntArray2.append(R$styleable.ConstraintOverride_android_id, 38);
        sparseIntArray2.append(R$styleable.ConstraintOverride_motionTarget, 98);
        sparseIntArray2.append(R$styleable.ConstraintOverride_motionProgress, 68);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintWidth_percent, 69);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintHeight_percent, 70);
        sparseIntArray2.append(R$styleable.ConstraintOverride_chainUseRtl, 71);
        sparseIntArray2.append(R$styleable.ConstraintOverride_barrierDirection, 72);
        sparseIntArray2.append(R$styleable.ConstraintOverride_barrierMargin, 73);
        sparseIntArray2.append(R$styleable.ConstraintOverride_constraint_referenced_ids, 74);
        sparseIntArray2.append(R$styleable.ConstraintOverride_barrierAllowsGoneWidgets, 75);
        sparseIntArray2.append(R$styleable.ConstraintOverride_pathMotionArc, 76);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constraintTag, 77);
        sparseIntArray2.append(R$styleable.ConstraintOverride_visibilityMode, 78);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constrainedWidth, 80);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_constrainedHeight, 81);
        sparseIntArray2.append(R$styleable.ConstraintOverride_polarRelativeTo, 82);
        sparseIntArray2.append(R$styleable.ConstraintOverride_transformPivotTarget, 83);
        sparseIntArray2.append(R$styleable.ConstraintOverride_quantizeMotionSteps, 84);
        sparseIntArray2.append(R$styleable.ConstraintOverride_quantizeMotionPhase, 85);
        sparseIntArray2.append(R$styleable.ConstraintOverride_quantizeMotionInterpolator, 86);
        sparseIntArray2.append(R$styleable.ConstraintOverride_layout_wrapBehaviorInParent, 97);
    }

    public static C0004a d(Context context, XmlResourceParser xmlResourceParser) {
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xmlResourceParser);
        C0004a c0004a = new C0004a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSetAsAttributeSet, R$styleable.ConstraintOverride);
        p(c0004a, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return c0004a;
    }

    public static int[] f(Barrier barrier, String str) {
        int iIntValue;
        HashMap<String, Integer> map;
        String[] strArrSplit = str.split(",");
        Context context = barrier.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < strArrSplit.length) {
            String strTrim = strArrSplit[i2].trim();
            Integer num = null;
            try {
                iIntValue = R$id.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
                iIntValue = 0;
            }
            if (iIntValue == 0) {
                iIntValue = context.getResources().getIdentifier(strTrim, "id", context.getPackageName());
            }
            if (iIntValue == 0 && barrier.isInEditMode() && (barrier.getParent() instanceof ConstraintLayout)) {
                ConstraintLayout constraintLayout = (ConstraintLayout) barrier.getParent();
                constraintLayout.getClass();
                if ((strTrim instanceof String) && (map = constraintLayout.q) != null && map.containsKey(strTrim)) {
                    num = constraintLayout.q.get(strTrim);
                }
                if (num != null && (num instanceof Integer)) {
                    iIntValue = num.intValue();
                }
            }
            iArr[i3] = iIntValue;
            i2++;
            i3++;
        }
        return i3 != strArrSplit.length ? Arrays.copyOf(iArr, i3) : iArr;
    }

    public static C0004a g(Context context, AttributeSet attributeSet, boolean z) {
        C0004a c0004a = new C0004a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z ? R$styleable.ConstraintOverride : R$styleable.Constraint);
        if (z) {
            p(c0004a, typedArrayObtainStyledAttributes);
        } else {
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            int i2 = 0;
            while (true) {
                b bVar = c0004a.e;
                if (i2 < indexCount) {
                    int index = typedArrayObtainStyledAttributes.getIndex(i2);
                    int i3 = R$styleable.Constraint_android_id;
                    d dVar = c0004a.c;
                    e eVar = c0004a.f;
                    c cVar = c0004a.d;
                    if (index != i3 && R$styleable.Constraint_android_layout_marginStart != index && R$styleable.Constraint_android_layout_marginEnd != index) {
                        cVar.a = true;
                        bVar.b = true;
                        dVar.a = true;
                        eVar.a = true;
                    }
                    SparseIntArray sparseIntArray = i;
                    switch (sparseIntArray.get(index)) {
                        case 1:
                            bVar.q = m(typedArrayObtainStyledAttributes, index, bVar.q);
                            break;
                        case 2:
                            bVar.f9J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.f9J);
                            break;
                        case 3:
                            bVar.p = m(typedArrayObtainStyledAttributes, index, bVar.p);
                            break;
                        case 4:
                            bVar.o = m(typedArrayObtainStyledAttributes, index, bVar.o);
                            break;
                        case 5:
                            bVar.z = typedArrayObtainStyledAttributes.getString(index);
                            break;
                        case 6:
                            bVar.D = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, bVar.D);
                            break;
                        case 7:
                            bVar.E = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, bVar.E);
                            break;
                        case 8:
                            bVar.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.K);
                            break;
                        case 9:
                            bVar.w = m(typedArrayObtainStyledAttributes, index, bVar.w);
                            break;
                        case 10:
                            bVar.v = m(typedArrayObtainStyledAttributes, index, bVar.v);
                            break;
                        case 11:
                            bVar.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.Q);
                            break;
                        case 12:
                            bVar.R = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.R);
                            break;
                        case 13:
                            bVar.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.N);
                            break;
                        case 14:
                            bVar.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.P);
                            break;
                        case 15:
                            bVar.S = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.S);
                            break;
                        case 16:
                            bVar.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.O);
                            break;
                        case 17:
                            bVar.e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, bVar.e);
                            break;
                        case 18:
                            bVar.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, bVar.f);
                            break;
                        case 19:
                            bVar.g = typedArrayObtainStyledAttributes.getFloat(index, bVar.g);
                            break;
                        case 20:
                            bVar.x = typedArrayObtainStyledAttributes.getFloat(index, bVar.x);
                            break;
                        case 21:
                            bVar.d = typedArrayObtainStyledAttributes.getLayoutDimension(index, bVar.d);
                            break;
                        case 22:
                            dVar.b = h[typedArrayObtainStyledAttributes.getInt(index, dVar.b)];
                            break;
                        case 23:
                            bVar.c = typedArrayObtainStyledAttributes.getLayoutDimension(index, bVar.c);
                            break;
                        case 24:
                            bVar.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.G);
                            break;
                        case 25:
                            bVar.i = m(typedArrayObtainStyledAttributes, index, bVar.i);
                            break;
                        case 26:
                            bVar.j = m(typedArrayObtainStyledAttributes, index, bVar.j);
                            break;
                        case 27:
                            bVar.F = typedArrayObtainStyledAttributes.getInt(index, bVar.F);
                            break;
                        case 28:
                            bVar.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.H);
                            break;
                        case 29:
                            bVar.k = m(typedArrayObtainStyledAttributes, index, bVar.k);
                            break;
                        case 30:
                            bVar.l = m(typedArrayObtainStyledAttributes, index, bVar.l);
                            break;
                        case 31:
                            bVar.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.L);
                            break;
                        case 32:
                            bVar.t = m(typedArrayObtainStyledAttributes, index, bVar.t);
                            break;
                        case 33:
                            bVar.u = m(typedArrayObtainStyledAttributes, index, bVar.u);
                            break;
                        case 34:
                            bVar.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.I);
                            break;
                        case 35:
                            bVar.n = m(typedArrayObtainStyledAttributes, index, bVar.n);
                            break;
                        case 36:
                            bVar.m = m(typedArrayObtainStyledAttributes, index, bVar.m);
                            break;
                        case 37:
                            bVar.y = typedArrayObtainStyledAttributes.getFloat(index, bVar.y);
                            break;
                        case 38:
                            c0004a.a = typedArrayObtainStyledAttributes.getResourceId(index, c0004a.a);
                            break;
                        case 39:
                            bVar.V = typedArrayObtainStyledAttributes.getFloat(index, bVar.V);
                            break;
                        case 40:
                            bVar.U = typedArrayObtainStyledAttributes.getFloat(index, bVar.U);
                            break;
                        case 41:
                            bVar.W = typedArrayObtainStyledAttributes.getInt(index, bVar.W);
                            break;
                        case 42:
                            bVar.X = typedArrayObtainStyledAttributes.getInt(index, bVar.X);
                            break;
                        case 43:
                            dVar.d = typedArrayObtainStyledAttributes.getFloat(index, dVar.d);
                            break;
                        case 44:
                            eVar.m = true;
                            eVar.n = typedArrayObtainStyledAttributes.getDimension(index, eVar.n);
                            break;
                        case 45:
                            eVar.c = typedArrayObtainStyledAttributes.getFloat(index, eVar.c);
                            break;
                        case 46:
                            eVar.d = typedArrayObtainStyledAttributes.getFloat(index, eVar.d);
                            break;
                        case 47:
                            eVar.e = typedArrayObtainStyledAttributes.getFloat(index, eVar.e);
                            break;
                        case 48:
                            eVar.f = typedArrayObtainStyledAttributes.getFloat(index, eVar.f);
                            break;
                        case 49:
                            eVar.g = typedArrayObtainStyledAttributes.getDimension(index, eVar.g);
                            break;
                        case 50:
                            eVar.h = typedArrayObtainStyledAttributes.getDimension(index, eVar.h);
                            break;
                        case 51:
                            eVar.j = typedArrayObtainStyledAttributes.getDimension(index, eVar.j);
                            break;
                        case 52:
                            eVar.k = typedArrayObtainStyledAttributes.getDimension(index, eVar.k);
                            break;
                        case 53:
                            eVar.l = typedArrayObtainStyledAttributes.getDimension(index, eVar.l);
                            break;
                        case 54:
                            bVar.Y = typedArrayObtainStyledAttributes.getInt(index, bVar.Y);
                            break;
                        case 55:
                            bVar.Z = typedArrayObtainStyledAttributes.getInt(index, bVar.Z);
                            break;
                        case 56:
                            bVar.a0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.a0);
                            break;
                        case 57:
                            bVar.b0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.b0);
                            break;
                        case 58:
                            bVar.c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.c0);
                            break;
                        case 59:
                            bVar.d0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.d0);
                            break;
                        case 60:
                            eVar.b = typedArrayObtainStyledAttributes.getFloat(index, eVar.b);
                            break;
                        case 61:
                            bVar.A = m(typedArrayObtainStyledAttributes, index, bVar.A);
                            break;
                        case 62:
                            bVar.B = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.B);
                            break;
                        case 63:
                            bVar.C = typedArrayObtainStyledAttributes.getFloat(index, bVar.C);
                            break;
                        case 64:
                            cVar.b = m(typedArrayObtainStyledAttributes, index, cVar.b);
                            break;
                        case 65:
                            if (typedArrayObtainStyledAttributes.peekValue(index).type != 3) {
                                cVar.d = hf0.c[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                            } else {
                                cVar.d = typedArrayObtainStyledAttributes.getString(index);
                            }
                            break;
                        case 66:
                            cVar.f = typedArrayObtainStyledAttributes.getInt(index, 0);
                            break;
                        case 67:
                            cVar.h = typedArrayObtainStyledAttributes.getFloat(index, cVar.h);
                            break;
                        case 68:
                            dVar.e = typedArrayObtainStyledAttributes.getFloat(index, dVar.e);
                            break;
                        case 69:
                            bVar.e0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                            break;
                        case 70:
                            bVar.f0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                            break;
                        case 71:
                            Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                            break;
                        case 72:
                            bVar.g0 = typedArrayObtainStyledAttributes.getInt(index, bVar.g0);
                            break;
                        case 73:
                            bVar.h0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.h0);
                            break;
                        case 74:
                            bVar.k0 = typedArrayObtainStyledAttributes.getString(index);
                            break;
                        case 75:
                            bVar.o0 = typedArrayObtainStyledAttributes.getBoolean(index, bVar.o0);
                            break;
                        case 76:
                            cVar.e = typedArrayObtainStyledAttributes.getInt(index, cVar.e);
                            break;
                        case 77:
                            bVar.l0 = typedArrayObtainStyledAttributes.getString(index);
                            break;
                        case 78:
                            dVar.c = typedArrayObtainStyledAttributes.getInt(index, dVar.c);
                            break;
                        case 79:
                            cVar.g = typedArrayObtainStyledAttributes.getFloat(index, cVar.g);
                            break;
                        case 80:
                            bVar.m0 = typedArrayObtainStyledAttributes.getBoolean(index, bVar.m0);
                            break;
                        case 81:
                            bVar.n0 = typedArrayObtainStyledAttributes.getBoolean(index, bVar.n0);
                            break;
                        case 82:
                            cVar.c = typedArrayObtainStyledAttributes.getInteger(index, cVar.c);
                            break;
                        case 83:
                            eVar.i = m(typedArrayObtainStyledAttributes, index, eVar.i);
                            break;
                        case 84:
                            cVar.j = typedArrayObtainStyledAttributes.getInteger(index, cVar.j);
                            break;
                        case 85:
                            cVar.i = typedArrayObtainStyledAttributes.getFloat(index, cVar.i);
                            break;
                        case 86:
                            int i4 = typedArrayObtainStyledAttributes.peekValue(index).type;
                            if (i4 == 1) {
                                int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                                cVar.m = resourceId;
                                if (resourceId != -1) {
                                    cVar.l = -2;
                                }
                            } else if (i4 != 3) {
                                cVar.l = typedArrayObtainStyledAttributes.getInteger(index, cVar.m);
                            } else {
                                String string = typedArrayObtainStyledAttributes.getString(index);
                                cVar.k = string;
                                if (string.indexOf("/") <= 0) {
                                    cVar.l = -1;
                                } else {
                                    cVar.m = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                                    cVar.l = -2;
                                }
                            }
                            break;
                        case 87:
                            Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                            break;
                        case 88:
                        case 89:
                        case 90:
                        default:
                            Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                            break;
                        case 91:
                            bVar.r = m(typedArrayObtainStyledAttributes, index, bVar.r);
                            break;
                        case 92:
                            bVar.s = m(typedArrayObtainStyledAttributes, index, bVar.s);
                            break;
                        case 93:
                            bVar.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.M);
                            break;
                        case 94:
                            bVar.T = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, bVar.T);
                            break;
                        case 95:
                            n(bVar, typedArrayObtainStyledAttributes, index, 0);
                            break;
                        case 96:
                            n(bVar, typedArrayObtainStyledAttributes, index, 1);
                            break;
                        case 97:
                            bVar.p0 = typedArrayObtainStyledAttributes.getInt(index, bVar.p0);
                            break;
                    }
                    i2++;
                } else if (bVar.k0 != null) {
                    bVar.j0 = null;
                }
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        return c0004a;
    }

    public static int m(TypedArray typedArray, int i2, int i3) {
        int resourceId = typedArray.getResourceId(i2, i3);
        return resourceId == -1 ? typedArray.getInt(i2, -1) : resourceId;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void n(java.lang.Object r8, android.content.res.TypedArray r9, int r10, int r11) {
        /*
            Method dump skipped, instruction units count: 375
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.a.n(java.lang.Object, android.content.res.TypedArray, int, int):void");
    }

    public static void o(ConstraintLayout.LayoutParams layoutParams, String str) {
        if (str != null) {
            int length = str.length();
            int iIndexOf = str.indexOf(44);
            byte b2 = -1;
            int i2 = 0;
            if (iIndexOf > 0 && iIndexOf < length - 1) {
                String strSubstring = str.substring(0, iIndexOf);
                if (strSubstring.equalsIgnoreCase("W")) {
                    b2 = 0;
                } else if (strSubstring.equalsIgnoreCase("H")) {
                    b2 = 1;
                }
                i2 = iIndexOf + 1;
            }
            int iIndexOf2 = str.indexOf(58);
            try {
                if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                    String strSubstring2 = str.substring(i2);
                    if (strSubstring2.length() > 0) {
                        Float.parseFloat(strSubstring2);
                    }
                } else {
                    String strSubstring3 = str.substring(i2, iIndexOf2);
                    String strSubstring4 = str.substring(iIndexOf2 + 1);
                    if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                        float f = Float.parseFloat(strSubstring3);
                        float f2 = Float.parseFloat(strSubstring4);
                        if (f > 0.0f && f2 > 0.0f) {
                            if (b2 == 1) {
                                Math.abs(f2 / f);
                            } else {
                                Math.abs(f / f2);
                            }
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        layoutParams.G = str;
    }

    public static void p(C0004a c0004a, TypedArray typedArray) {
        boolean z;
        int indexCount = typedArray.getIndexCount();
        C0004a.C0005a c0005a = new C0004a.C0005a();
        c0004a.h = c0005a;
        c cVar = c0004a.d;
        cVar.a = false;
        b bVar = c0004a.e;
        bVar.b = false;
        d dVar = c0004a.c;
        dVar.a = false;
        e eVar = c0004a.f;
        eVar.a = false;
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArray.getIndex(i2);
            int i3 = j.get(index);
            SparseIntArray sparseIntArray = i;
            switch (i3) {
                case 2:
                    z = false;
                    c0005a.b(2, typedArray.getDimensionPixelSize(index, bVar.f9J));
                    continue;
                    break;
                case 3:
                case 4:
                case 9:
                case 10:
                case 25:
                case 26:
                case 29:
                case 30:
                case 32:
                case 33:
                case 35:
                case 36:
                case 61:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                default:
                    z = false;
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                    continue;
                    break;
                case 5:
                    z = false;
                    c0005a.c(5, typedArray.getString(index));
                    continue;
                    break;
                case 6:
                    z = false;
                    c0005a.b(6, typedArray.getDimensionPixelOffset(index, bVar.D));
                    continue;
                    break;
                case 7:
                    z = false;
                    c0005a.b(7, typedArray.getDimensionPixelOffset(index, bVar.E));
                    continue;
                    break;
                case 8:
                    z = false;
                    c0005a.b(8, typedArray.getDimensionPixelSize(index, bVar.K));
                    continue;
                    break;
                case 11:
                    z = false;
                    c0005a.b(11, typedArray.getDimensionPixelSize(index, bVar.Q));
                    continue;
                    break;
                case 12:
                    z = false;
                    c0005a.b(12, typedArray.getDimensionPixelSize(index, bVar.R));
                    continue;
                    break;
                case 13:
                    z = false;
                    c0005a.b(13, typedArray.getDimensionPixelSize(index, bVar.N));
                    continue;
                    break;
                case 14:
                    z = false;
                    c0005a.b(14, typedArray.getDimensionPixelSize(index, bVar.P));
                    continue;
                    break;
                case 15:
                    z = false;
                    c0005a.b(15, typedArray.getDimensionPixelSize(index, bVar.S));
                    continue;
                    break;
                case 16:
                    z = false;
                    c0005a.b(16, typedArray.getDimensionPixelSize(index, bVar.O));
                    continue;
                    break;
                case 17:
                    z = false;
                    c0005a.b(17, typedArray.getDimensionPixelOffset(index, bVar.e));
                    continue;
                    break;
                case 18:
                    z = false;
                    c0005a.b(18, typedArray.getDimensionPixelOffset(index, bVar.f));
                    continue;
                    break;
                case 19:
                    z = false;
                    c0005a.a(typedArray.getFloat(index, bVar.g), 19);
                    continue;
                    break;
                case 20:
                    z = false;
                    c0005a.a(typedArray.getFloat(index, bVar.x), 20);
                    continue;
                    break;
                case 21:
                    z = false;
                    c0005a.b(21, typedArray.getLayoutDimension(index, bVar.d));
                    continue;
                    break;
                case 22:
                    z = false;
                    c0005a.b(22, h[typedArray.getInt(index, dVar.b)]);
                    continue;
                    break;
                case 23:
                    z = false;
                    c0005a.b(23, typedArray.getLayoutDimension(index, bVar.c));
                    continue;
                    break;
                case 24:
                    z = false;
                    c0005a.b(24, typedArray.getDimensionPixelSize(index, bVar.G));
                    continue;
                    break;
                case 27:
                    z = false;
                    c0005a.b(27, typedArray.getInt(index, bVar.F));
                    continue;
                    break;
                case 28:
                    z = false;
                    c0005a.b(28, typedArray.getDimensionPixelSize(index, bVar.H));
                    continue;
                    break;
                case 31:
                    z = false;
                    c0005a.b(31, typedArray.getDimensionPixelSize(index, bVar.L));
                    continue;
                    break;
                case 34:
                    z = false;
                    c0005a.b(34, typedArray.getDimensionPixelSize(index, bVar.I));
                    continue;
                    break;
                case 37:
                    z = false;
                    c0005a.a(typedArray.getFloat(index, bVar.y), 37);
                    continue;
                    break;
                case 38:
                    z = false;
                    int resourceId = typedArray.getResourceId(index, c0004a.a);
                    c0004a.a = resourceId;
                    c0005a.b(38, resourceId);
                    continue;
                    break;
                case 39:
                    z = false;
                    c0005a.a(typedArray.getFloat(index, bVar.V), 39);
                    continue;
                    break;
                case 40:
                    z = false;
                    c0005a.a(typedArray.getFloat(index, bVar.U), 40);
                    continue;
                    break;
                case 41:
                    z = false;
                    c0005a.b(41, typedArray.getInt(index, bVar.W));
                    continue;
                    break;
                case 42:
                    z = false;
                    c0005a.b(42, typedArray.getInt(index, bVar.X));
                    continue;
                    break;
                case 43:
                    z = false;
                    c0005a.a(typedArray.getFloat(index, dVar.d), 43);
                    continue;
                    break;
                case 44:
                    z = false;
                    c0005a.d(44, true);
                    c0005a.a(typedArray.getDimension(index, eVar.n), 44);
                    continue;
                    break;
                case 45:
                    z = false;
                    c0005a.a(typedArray.getFloat(index, eVar.c), 45);
                    continue;
                    break;
                case 46:
                    z = false;
                    c0005a.a(typedArray.getFloat(index, eVar.d), 46);
                    continue;
                    break;
                case 47:
                    z = false;
                    c0005a.a(typedArray.getFloat(index, eVar.e), 47);
                    continue;
                    break;
                case 48:
                    z = false;
                    c0005a.a(typedArray.getFloat(index, eVar.f), 48);
                    continue;
                    break;
                case 49:
                    z = false;
                    c0005a.a(typedArray.getDimension(index, eVar.g), 49);
                    continue;
                    break;
                case 50:
                    z = false;
                    c0005a.a(typedArray.getDimension(index, eVar.h), 50);
                    continue;
                    break;
                case 51:
                    z = false;
                    c0005a.a(typedArray.getDimension(index, eVar.j), 51);
                    continue;
                    break;
                case 52:
                    z = false;
                    c0005a.a(typedArray.getDimension(index, eVar.k), 52);
                    continue;
                    break;
                case 53:
                    z = false;
                    c0005a.a(typedArray.getDimension(index, eVar.l), 53);
                    continue;
                    break;
                case 54:
                    z = false;
                    c0005a.b(54, typedArray.getInt(index, bVar.Y));
                    continue;
                    break;
                case 55:
                    z = false;
                    c0005a.b(55, typedArray.getInt(index, bVar.Z));
                    continue;
                    break;
                case 56:
                    z = false;
                    c0005a.b(56, typedArray.getDimensionPixelSize(index, bVar.a0));
                    continue;
                    break;
                case 57:
                    z = false;
                    c0005a.b(57, typedArray.getDimensionPixelSize(index, bVar.b0));
                    continue;
                    break;
                case 58:
                    z = false;
                    c0005a.b(58, typedArray.getDimensionPixelSize(index, bVar.c0));
                    continue;
                    break;
                case 59:
                    z = false;
                    c0005a.b(59, typedArray.getDimensionPixelSize(index, bVar.d0));
                    continue;
                    break;
                case 60:
                    z = false;
                    c0005a.a(typedArray.getFloat(index, eVar.b), 60);
                    continue;
                    break;
                case 62:
                    z = false;
                    c0005a.b(62, typedArray.getDimensionPixelSize(index, bVar.B));
                    continue;
                    break;
                case 63:
                    z = false;
                    c0005a.a(typedArray.getFloat(index, bVar.C), 63);
                    continue;
                    break;
                case 64:
                    z = false;
                    c0005a.b(64, m(typedArray, index, cVar.b));
                    continue;
                    break;
                case 65:
                    z = false;
                    if (typedArray.peekValue(index).type == 3) {
                        c0005a.c(65, typedArray.getString(index));
                        continue;
                    } else {
                        c0005a.c(65, hf0.c[typedArray.getInteger(index, 0)]);
                    }
                    break;
                case 66:
                    z = false;
                    c0005a.b(66, typedArray.getInt(index, 0));
                    continue;
                    break;
                case 67:
                    c0005a.a(typedArray.getFloat(index, cVar.h), 67);
                    break;
                case 68:
                    c0005a.a(typedArray.getFloat(index, dVar.e), 68);
                    break;
                case 69:
                    c0005a.a(typedArray.getFloat(index, 1.0f), 69);
                    break;
                case 70:
                    c0005a.a(typedArray.getFloat(index, 1.0f), 70);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    c0005a.b(72, typedArray.getInt(index, bVar.g0));
                    break;
                case 73:
                    c0005a.b(73, typedArray.getDimensionPixelSize(index, bVar.h0));
                    break;
                case 74:
                    c0005a.c(74, typedArray.getString(index));
                    break;
                case 75:
                    c0005a.d(75, typedArray.getBoolean(index, bVar.o0));
                    break;
                case 76:
                    c0005a.b(76, typedArray.getInt(index, cVar.e));
                    break;
                case 77:
                    c0005a.c(77, typedArray.getString(index));
                    break;
                case 78:
                    c0005a.b(78, typedArray.getInt(index, dVar.c));
                    break;
                case 79:
                    c0005a.a(typedArray.getFloat(index, cVar.g), 79);
                    break;
                case 80:
                    c0005a.d(80, typedArray.getBoolean(index, bVar.m0));
                    break;
                case 81:
                    c0005a.d(81, typedArray.getBoolean(index, bVar.n0));
                    break;
                case 82:
                    c0005a.b(82, typedArray.getInteger(index, cVar.c));
                    break;
                case 83:
                    c0005a.b(83, m(typedArray, index, eVar.i));
                    break;
                case 84:
                    c0005a.b(84, typedArray.getInteger(index, cVar.j));
                    break;
                case 85:
                    c0005a.a(typedArray.getFloat(index, cVar.i), 85);
                    break;
                case 86:
                    int i4 = typedArray.peekValue(index).type;
                    if (i4 == 1) {
                        int resourceId2 = typedArray.getResourceId(index, -1);
                        cVar.m = resourceId2;
                        c0005a.b(89, resourceId2);
                        if (cVar.m != -1) {
                            cVar.l = -2;
                            c0005a.b(88, -2);
                        }
                    } else if (i4 == 3) {
                        String string = typedArray.getString(index);
                        cVar.k = string;
                        c0005a.c(90, string);
                        if (cVar.k.indexOf("/") > 0) {
                            int resourceId3 = typedArray.getResourceId(index, -1);
                            cVar.m = resourceId3;
                            c0005a.b(89, resourceId3);
                            cVar.l = -2;
                            c0005a.b(88, -2);
                        } else {
                            cVar.l = -1;
                            c0005a.b(88, -1);
                        }
                    } else {
                        int integer = typedArray.getInteger(index, cVar.m);
                        cVar.l = integer;
                        c0005a.b(88, integer);
                    }
                    break;
                case 87:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                    break;
                case 93:
                    c0005a.b(93, typedArray.getDimensionPixelSize(index, bVar.M));
                    break;
                case 94:
                    c0005a.b(94, typedArray.getDimensionPixelSize(index, bVar.T));
                    break;
                case 95:
                    n(c0005a, typedArray, index, 0);
                    z = false;
                    continue;
                    break;
                case 96:
                    n(c0005a, typedArray, index, 1);
                    break;
                case 97:
                    c0005a.b(97, typedArray.getInt(index, bVar.p0));
                    break;
                case 98:
                    if (MotionLayout.J0) {
                        int resourceId4 = typedArray.getResourceId(index, c0004a.a);
                        c0004a.a = resourceId4;
                        if (resourceId4 == -1) {
                            c0004a.b = typedArray.getString(index);
                        }
                    } else if (typedArray.peekValue(index).type == 3) {
                        c0004a.b = typedArray.getString(index);
                    } else {
                        c0004a.a = typedArray.getResourceId(index, c0004a.a);
                    }
                    break;
                case 99:
                    c0005a.d(99, typedArray.getBoolean(index, bVar.h));
                    break;
            }
            z = false;
        }
    }

    public final void a(ConstraintLayout constraintLayout) {
        C0004a c0004a;
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            HashMap<Integer, C0004a> map = this.g;
            if (!map.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + b70.d(childAt));
            } else {
                if (this.f && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (map.containsKey(Integer.valueOf(id)) && (c0004a = map.get(Integer.valueOf(id))) != null) {
                    ox.e(childAt, c0004a.g);
                }
            }
        }
    }

    public final void b(ConstraintLayout constraintLayout) {
        c(constraintLayout);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public final void c(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        HashMap<Integer, C0004a> map = this.g;
        HashSet<Integer> hashSet = new HashSet(map.keySet());
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!map.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + b70.d(childAt));
            } else {
                if (this.f && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1 && map.containsKey(Integer.valueOf(id))) {
                    hashSet.remove(Integer.valueOf(id));
                    C0004a c0004a = map.get(Integer.valueOf(id));
                    if (c0004a != null) {
                        if (childAt instanceof Barrier) {
                            b bVar = c0004a.e;
                            bVar.i0 = 1;
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(bVar.g0);
                            barrier.setMargin(bVar.h0);
                            barrier.setAllowsGoneWidget(bVar.o0);
                            int[] iArr = bVar.j0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = bVar.k0;
                                if (str != null) {
                                    int[] iArrF = f(barrier, str);
                                    bVar.j0 = iArrF;
                                    barrier.setReferencedIds(iArrF);
                                }
                            }
                        }
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                        layoutParams.a();
                        c0004a.a(layoutParams);
                        ox.e(childAt, c0004a.g);
                        childAt.setLayoutParams(layoutParams);
                        d dVar = c0004a.c;
                        if (dVar.c == 0) {
                            childAt.setVisibility(dVar.b);
                        }
                        childAt.setAlpha(dVar.d);
                        e eVar = c0004a.f;
                        childAt.setRotation(eVar.b);
                        childAt.setRotationX(eVar.c);
                        childAt.setRotationY(eVar.d);
                        childAt.setScaleX(eVar.e);
                        childAt.setScaleY(eVar.f);
                        if (eVar.i != -1) {
                            if (((View) childAt.getParent()).findViewById(eVar.i) != null) {
                                float bottom = (r7.getBottom() + r7.getTop()) / 2.0f;
                                float right = (r7.getRight() + r7.getLeft()) / 2.0f;
                                if (childAt.getRight() - childAt.getLeft() > 0 && childAt.getBottom() - childAt.getTop() > 0) {
                                    childAt.setPivotX(right - childAt.getLeft());
                                    childAt.setPivotY(bottom - childAt.getTop());
                                }
                            }
                        } else {
                            if (!Float.isNaN(eVar.g)) {
                                childAt.setPivotX(eVar.g);
                            }
                            if (!Float.isNaN(eVar.h)) {
                                childAt.setPivotY(eVar.h);
                            }
                        }
                        childAt.setTranslationX(eVar.j);
                        childAt.setTranslationY(eVar.k);
                        childAt.setTranslationZ(eVar.l);
                        if (eVar.m) {
                            childAt.setElevation(eVar.n);
                        }
                    }
                }
            }
        }
        for (Integer num : hashSet) {
            C0004a c0004a2 = map.get(num);
            if (c0004a2 != null) {
                b bVar2 = c0004a2.e;
                if (bVar2.i0 == 1) {
                    Barrier barrier2 = new Barrier(constraintLayout.getContext());
                    barrier2.setId(num.intValue());
                    int[] iArr2 = bVar2.j0;
                    if (iArr2 != null) {
                        barrier2.setReferencedIds(iArr2);
                    } else {
                        String str2 = bVar2.k0;
                        if (str2 != null) {
                            int[] iArrF2 = f(barrier2, str2);
                            bVar2.j0 = iArrF2;
                            barrier2.setReferencedIds(iArrF2);
                        }
                    }
                    barrier2.setType(bVar2.g0);
                    barrier2.setMargin(bVar2.h0);
                    androidx.constraintlayout.widget.b bVar3 = ConstraintLayout.t;
                    ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(-2, -2);
                    barrier2.t();
                    c0004a2.a(layoutParams2);
                    constraintLayout.addView(barrier2, layoutParams2);
                }
                if (bVar2.a) {
                    View guideline = new Guideline(constraintLayout.getContext());
                    guideline.setId(num.intValue());
                    androidx.constraintlayout.widget.b bVar4 = ConstraintLayout.t;
                    ConstraintLayout.LayoutParams layoutParams3 = new ConstraintLayout.LayoutParams(-2, -2);
                    c0004a2.a(layoutParams3);
                    constraintLayout.addView(guideline, layoutParams3);
                }
            }
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt2 = constraintLayout.getChildAt(i3);
            if (childAt2 instanceof ConstraintHelper) {
                ((ConstraintHelper) childAt2).j(constraintLayout);
            }
        }
    }

    public final void e(ConstraintLayout constraintLayout) {
        int i2;
        HashMap<Integer, C0004a> map;
        HashMap<Integer, C0004a> map2;
        a aVar = this;
        int childCount = constraintLayout.getChildCount();
        HashMap<Integer, C0004a> map3 = aVar.g;
        map3.clear();
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = constraintLayout.getChildAt(i3);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (aVar.f && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!map3.containsKey(Integer.valueOf(id))) {
                map3.put(Integer.valueOf(id), new C0004a());
            }
            C0004a c0004a = map3.get(Integer.valueOf(id));
            if (c0004a == null) {
                i2 = childCount;
                map = map3;
            } else {
                HashMap<String, ox> map4 = aVar.e;
                HashMap<String, ox> map5 = new HashMap<>();
                Class<?> cls = childAt.getClass();
                for (String str : map4.keySet()) {
                    ox oxVar = map4.get(str);
                    int i4 = childCount;
                    try {
                        if (str.equals("BackgroundColor")) {
                            map2 = map3;
                            try {
                                map5.put(str, new ox(oxVar, Integer.valueOf(((ColorDrawable) childAt.getBackground()).getColor())));
                            } catch (IllegalAccessException e2) {
                                e = e2;
                                StringBuilder sbN = C0175b.n(" Custom Attribute \"", str, "\" not found on ");
                                sbN.append(cls.getName());
                                Log.e("TransitionLayout", sbN.toString(), e);
                            } catch (NoSuchMethodException e3) {
                                e = e3;
                                Log.e("TransitionLayout", cls.getName() + " must have a method " + str, e);
                            } catch (InvocationTargetException e4) {
                                e = e4;
                                StringBuilder sbN2 = C0175b.n(" Custom Attribute \"", str, "\" not found on ");
                                sbN2.append(cls.getName());
                                Log.e("TransitionLayout", sbN2.toString(), e);
                            }
                        } else {
                            map2 = map3;
                            map5.put(str, new ox(oxVar, cls.getMethod("getMap" + str, new Class[0]).invoke(childAt, new Object[0])));
                        }
                    } catch (IllegalAccessException e5) {
                        e = e5;
                        map2 = map3;
                    } catch (NoSuchMethodException e6) {
                        e = e6;
                        map2 = map3;
                    } catch (InvocationTargetException e7) {
                        e = e7;
                        map2 = map3;
                    }
                    childCount = i4;
                    map3 = map2;
                }
                i2 = childCount;
                map = map3;
                c0004a.g = map5;
                c0004a.c(id, layoutParams);
                int visibility = childAt.getVisibility();
                d dVar = c0004a.c;
                dVar.b = visibility;
                dVar.d = childAt.getAlpha();
                float rotation = childAt.getRotation();
                e eVar = c0004a.f;
                eVar.b = rotation;
                eVar.c = childAt.getRotationX();
                eVar.d = childAt.getRotationY();
                eVar.e = childAt.getScaleX();
                eVar.f = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    eVar.g = pivotX;
                    eVar.h = pivotY;
                }
                eVar.j = childAt.getTranslationX();
                eVar.k = childAt.getTranslationY();
                eVar.l = childAt.getTranslationZ();
                if (eVar.m) {
                    eVar.n = childAt.getElevation();
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    boolean allowsGoneWidget = barrier.getAllowsGoneWidget();
                    b bVar = c0004a.e;
                    bVar.o0 = allowsGoneWidget;
                    bVar.j0 = barrier.getReferencedIds();
                    bVar.g0 = barrier.getType();
                    bVar.h0 = barrier.getMargin();
                }
            }
            i3++;
            aVar = this;
            childCount = i2;
            map3 = map;
        }
    }

    public final C0004a h(int i2) {
        HashMap<Integer, C0004a> map = this.g;
        if (!map.containsKey(Integer.valueOf(i2))) {
            map.put(Integer.valueOf(i2), new C0004a());
        }
        return map.get(Integer.valueOf(i2));
    }

    public final C0004a i(int i2) {
        HashMap<Integer, C0004a> map = this.g;
        if (map.containsKey(Integer.valueOf(i2))) {
            return map.get(Integer.valueOf(i2));
        }
        return null;
    }

    public final int[] j() {
        Integer[] numArr = (Integer[]) this.g.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = numArr[i2].intValue();
        }
        return iArr;
    }

    public final void k(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 2) {
                    String name = xml.getName();
                    C0004a c0004aG = g(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        c0004aG.e.a = true;
                    }
                    this.g.put(Integer.valueOf(c0004aG.a), c0004aG);
                }
            }
        } catch (IOException e2) {
            Log.e("ConstraintSet", "Error parsing resource: " + i2, e2);
        } catch (XmlPullParserException e3) {
            Log.e("ConstraintSet", "Error parsing resource: " + i2, e3);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:120:0x01cf, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(android.content.Context r12, android.content.res.XmlResourceParser r13) {
        /*
            Method dump skipped, instruction units count: 564
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.a.l(android.content.Context, android.content.res.XmlResourceParser):void");
    }
}
