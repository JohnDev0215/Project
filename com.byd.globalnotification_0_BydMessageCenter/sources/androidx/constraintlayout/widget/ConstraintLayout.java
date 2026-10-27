package androidx.constraintlayout.widget;

import a.f.a.i.c;
import a.f.a.i.e;
import a.f.a.i.f;
import a.f.a.i.l.b;
import a.f.b.c;
import a.f.b.d;
import a.f.b.g;
import a.f.b.i;
import a.f.b.j;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    public static j t;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SparseArray<View> f1486a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<a.f.b.b> f1487b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e f1488c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1489d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1490e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1491f;
    public int g;
    public boolean h;
    public int i;
    public d j;
    public c k;
    public int l;
    public HashMap<String, Integer> m;
    public int n;
    public int o;
    public SparseArray<a.f.a.i.d> p;
    public b q;
    public int r;
    public int s;

    public static class a extends ViewGroup.MarginLayoutParams {
        public int A;
        public int B;
        public int C;
        public int D;
        public float E;
        public float F;
        public String G;
        public float H;
        public float I;
        public int J;
        public int K;
        public int L;
        public int M;
        public int N;
        public int O;
        public int P;
        public int Q;
        public float R;
        public float S;
        public int T;
        public int U;
        public int V;
        public boolean W;
        public boolean X;
        public String Y;
        public int Z;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1492a;
        public boolean a0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1493b;
        public boolean b0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1494c;
        public boolean c0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1495d;
        public boolean d0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1496e;
        public boolean e0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1497f;
        public boolean f0;
        public int g;
        public boolean g0;
        public int h;
        public int h0;
        public int i;
        public int i0;
        public int j;
        public int j0;
        public int k;
        public int k0;
        public int l;
        public int l0;
        public int m;
        public int m0;
        public int n;
        public float n0;
        public int o;
        public int o0;
        public int p;
        public int p0;
        public int q;
        public float q0;
        public float r;
        public a.f.a.i.d r0;
        public int s;
        public int t;
        public int u;
        public int v;
        public int w;
        public int x;
        public int y;
        public int z;

        /* JADX INFO: renamed from: androidx.constraintlayout.widget.ConstraintLayout$a$a, reason: collision with other inner class name */
        public static class C0030a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final SparseIntArray f1498a;

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                f1498a = sparseIntArray;
                sparseIntArray.append(i.ConstraintLayout_Layout_layout_constraintWidth, 64);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintHeight, 65);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintBaseline_toTopOf, 52);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintBaseline_toBottomOf, 53);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintCircle, 2);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                f1498a.append(i.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                f1498a.append(i.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                f1498a.append(i.ConstraintLayout_Layout_guidelineUseRtl, 67);
                f1498a.append(i.ConstraintLayout_Layout_android_orientation, 1);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                f1498a.append(i.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                f1498a.append(i.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                f1498a.append(i.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                f1498a.append(i.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                f1498a.append(i.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                f1498a.append(i.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                f1498a.append(i.ConstraintLayout_Layout_layout_goneMarginBaseline, 55);
                f1498a.append(i.ConstraintLayout_Layout_layout_marginBaseline, 54);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                f1498a.append(i.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                f1498a.append(i.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
                f1498a.append(i.ConstraintLayout_Layout_layout_constraintTag, 51);
                f1498a.append(i.ConstraintLayout_Layout_layout_wrapBehaviorInParent, 66);
            }
        }

        public a(int i, int i2) {
            super(i, i2);
            this.f1492a = -1;
            this.f1493b = -1;
            this.f1494c = -1.0f;
            this.f1495d = true;
            this.f1496e = -1;
            this.f1497f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = -1;
            this.o = -1;
            this.p = -1;
            this.q = 0;
            this.r = 0.0f;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
            this.z = Integer.MIN_VALUE;
            this.A = Integer.MIN_VALUE;
            this.B = Integer.MIN_VALUE;
            this.C = Integer.MIN_VALUE;
            this.D = 0;
            this.E = 0.5f;
            this.F = 0.5f;
            this.G = null;
            this.H = -1.0f;
            this.I = -1.0f;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 0;
            this.R = 1.0f;
            this.S = 1.0f;
            this.T = -1;
            this.U = -1;
            this.V = -1;
            this.W = false;
            this.X = false;
            this.Y = null;
            this.Z = 0;
            this.a0 = true;
            this.b0 = true;
            this.c0 = false;
            this.d0 = false;
            this.e0 = false;
            this.f0 = false;
            this.g0 = false;
            this.h0 = -1;
            this.i0 = -1;
            this.j0 = -1;
            this.k0 = -1;
            this.l0 = Integer.MIN_VALUE;
            this.m0 = Integer.MIN_VALUE;
            this.n0 = 0.5f;
            this.r0 = new a.f.a.i.d();
        }

        public a(Context context, AttributeSet attributeSet) {
            String str;
            super(context, attributeSet);
            this.f1492a = -1;
            this.f1493b = -1;
            this.f1494c = -1.0f;
            this.f1495d = true;
            this.f1496e = -1;
            this.f1497f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = -1;
            this.o = -1;
            this.p = -1;
            this.q = 0;
            this.r = 0.0f;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
            this.z = Integer.MIN_VALUE;
            this.A = Integer.MIN_VALUE;
            this.B = Integer.MIN_VALUE;
            this.C = Integer.MIN_VALUE;
            this.D = 0;
            this.E = 0.5f;
            this.F = 0.5f;
            this.G = null;
            this.H = -1.0f;
            this.I = -1.0f;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 0;
            this.R = 1.0f;
            this.S = 1.0f;
            this.T = -1;
            this.U = -1;
            this.V = -1;
            this.W = false;
            this.X = false;
            this.Y = null;
            this.Z = 0;
            this.a0 = true;
            this.b0 = true;
            this.c0 = false;
            this.d0 = false;
            this.e0 = false;
            this.f0 = false;
            this.g0 = false;
            this.h0 = -1;
            this.i0 = -1;
            this.j0 = -1;
            this.k0 = -1;
            this.l0 = Integer.MIN_VALUE;
            this.m0 = Integer.MIN_VALUE;
            this.n0 = 0.5f;
            this.r0 = new a.f.a.i.d();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                int i2 = C0030a.f1498a.get(index);
                switch (i2) {
                    case 1:
                        this.V = typedArrayObtainStyledAttributes.getInt(index, this.V);
                        continue;
                        break;
                    case 2:
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.p);
                        this.p = resourceId;
                        if (resourceId == -1) {
                            this.p = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 3:
                        this.q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.q);
                        continue;
                        break;
                    case 4:
                        float f2 = typedArrayObtainStyledAttributes.getFloat(index, this.r) % 360.0f;
                        this.r = f2;
                        if (f2 < 0.0f) {
                            this.r = (360.0f - f2) % 360.0f;
                        } else {
                            continue;
                        }
                        break;
                    case 5:
                        this.f1492a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1492a);
                        continue;
                        break;
                    case 6:
                        this.f1493b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1493b);
                        continue;
                        break;
                    case 7:
                        this.f1494c = typedArrayObtainStyledAttributes.getFloat(index, this.f1494c);
                        continue;
                        break;
                    case 8:
                        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1496e);
                        this.f1496e = resourceId2;
                        if (resourceId2 == -1) {
                            this.f1496e = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 9:
                        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1497f);
                        this.f1497f = resourceId3;
                        if (resourceId3 == -1) {
                            this.f1497f = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 10:
                        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(index, this.g);
                        this.g = resourceId4;
                        if (resourceId4 == -1) {
                            this.g = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 11:
                        int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(index, this.h);
                        this.h = resourceId5;
                        if (resourceId5 == -1) {
                            this.h = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 12:
                        int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(index, this.i);
                        this.i = resourceId6;
                        if (resourceId6 == -1) {
                            this.i = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 13:
                        int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(index, this.j);
                        this.j = resourceId7;
                        if (resourceId7 == -1) {
                            this.j = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 14:
                        int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(index, this.k);
                        this.k = resourceId8;
                        if (resourceId8 == -1) {
                            this.k = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 15:
                        int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(index, this.l);
                        this.l = resourceId9;
                        if (resourceId9 == -1) {
                            this.l = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 16:
                        int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(index, this.m);
                        this.m = resourceId10;
                        if (resourceId10 == -1) {
                            this.m = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 17:
                        int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(index, this.s);
                        this.s = resourceId11;
                        if (resourceId11 == -1) {
                            this.s = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 18:
                        int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(index, this.t);
                        this.t = resourceId12;
                        if (resourceId12 == -1) {
                            this.t = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 19:
                        int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(index, this.u);
                        this.u = resourceId13;
                        if (resourceId13 == -1) {
                            this.u = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 20:
                        int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(index, this.v);
                        this.v = resourceId14;
                        if (resourceId14 == -1) {
                            this.v = typedArrayObtainStyledAttributes.getInt(index, -1);
                        } else {
                            continue;
                        }
                        break;
                    case 21:
                        this.w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.w);
                        continue;
                        break;
                    case 22:
                        this.x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.x);
                        continue;
                        break;
                    case 23:
                        this.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.y);
                        continue;
                        break;
                    case 24:
                        this.z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.z);
                        continue;
                        break;
                    case 25:
                        this.A = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.A);
                        continue;
                        break;
                    case 26:
                        this.B = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.B);
                        continue;
                        break;
                    case 27:
                        this.W = typedArrayObtainStyledAttributes.getBoolean(index, this.W);
                        continue;
                        break;
                    case 28:
                        this.X = typedArrayObtainStyledAttributes.getBoolean(index, this.X);
                        continue;
                        break;
                    case 29:
                        this.E = typedArrayObtainStyledAttributes.getFloat(index, this.E);
                        continue;
                        break;
                    case 30:
                        this.F = typedArrayObtainStyledAttributes.getFloat(index, this.F);
                        continue;
                        break;
                    case 31:
                        int i3 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.L = i3;
                        if (i3 == 1) {
                            str = "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.";
                        }
                        break;
                    case 32:
                        int i4 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.M = i4;
                        if (i4 == 1) {
                            str = "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.";
                        }
                        break;
                    case 33:
                        try {
                            this.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.N);
                            continue;
                        } catch (Exception unused) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.N) == -2) {
                                this.N = -2;
                            }
                        }
                        break;
                    case 34:
                        try {
                            this.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.P);
                            continue;
                        } catch (Exception unused2) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.P) == -2) {
                                this.P = -2;
                            }
                        }
                        break;
                    case 35:
                        this.R = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.R));
                        this.L = 2;
                        continue;
                        break;
                    case 36:
                        try {
                            this.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.O);
                            continue;
                        } catch (Exception unused3) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.O) == -2) {
                                this.O = -2;
                            }
                        }
                        break;
                    case 37:
                        try {
                            this.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.Q);
                            continue;
                        } catch (Exception unused4) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.Q) == -2) {
                                this.Q = -2;
                            }
                        }
                        break;
                    case 38:
                        this.S = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.S));
                        this.M = 2;
                        continue;
                        break;
                    default:
                        switch (i2) {
                            case 44:
                                d.h(this, typedArrayObtainStyledAttributes.getString(index));
                                break;
                            case 45:
                                this.H = typedArrayObtainStyledAttributes.getFloat(index, this.H);
                                break;
                            case 46:
                                this.I = typedArrayObtainStyledAttributes.getFloat(index, this.I);
                                break;
                            case 47:
                                this.J = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case 48:
                                this.K = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case 49:
                                this.T = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.T);
                                break;
                            case 50:
                                this.U = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.U);
                                break;
                            case 51:
                                this.Y = typedArrayObtainStyledAttributes.getString(index);
                                break;
                            case 52:
                                int resourceId15 = typedArrayObtainStyledAttributes.getResourceId(index, this.n);
                                this.n = resourceId15;
                                if (resourceId15 == -1) {
                                    this.n = typedArrayObtainStyledAttributes.getInt(index, -1);
                                }
                                break;
                            case 53:
                                int resourceId16 = typedArrayObtainStyledAttributes.getResourceId(index, this.o);
                                this.o = resourceId16;
                                if (resourceId16 == -1) {
                                    this.o = typedArrayObtainStyledAttributes.getInt(index, -1);
                                }
                                break;
                            case 54:
                                this.D = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.D);
                                break;
                            case 55:
                                this.C = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.C);
                                break;
                            default:
                                switch (i2) {
                                    case 64:
                                        d.g(this, typedArrayObtainStyledAttributes, index, 0);
                                        break;
                                    case 65:
                                        d.g(this, typedArrayObtainStyledAttributes, index, 1);
                                        break;
                                    case 66:
                                        this.Z = typedArrayObtainStyledAttributes.getInt(index, this.Z);
                                        break;
                                    case 67:
                                        this.f1495d = typedArrayObtainStyledAttributes.getBoolean(index, this.f1495d);
                                        continue;
                                }
                                break;
                        }
                        break;
                }
                Log.e("ConstraintLayout", str);
            }
            typedArrayObtainStyledAttributes.recycle();
            a();
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1492a = -1;
            this.f1493b = -1;
            this.f1494c = -1.0f;
            this.f1495d = true;
            this.f1496e = -1;
            this.f1497f = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = -1;
            this.o = -1;
            this.p = -1;
            this.q = 0;
            this.r = 0.0f;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
            this.z = Integer.MIN_VALUE;
            this.A = Integer.MIN_VALUE;
            this.B = Integer.MIN_VALUE;
            this.C = Integer.MIN_VALUE;
            this.D = 0;
            this.E = 0.5f;
            this.F = 0.5f;
            this.G = null;
            this.H = -1.0f;
            this.I = -1.0f;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 0;
            this.O = 0;
            this.P = 0;
            this.Q = 0;
            this.R = 1.0f;
            this.S = 1.0f;
            this.T = -1;
            this.U = -1;
            this.V = -1;
            this.W = false;
            this.X = false;
            this.Y = null;
            this.Z = 0;
            this.a0 = true;
            this.b0 = true;
            this.c0 = false;
            this.d0 = false;
            this.e0 = false;
            this.f0 = false;
            this.g0 = false;
            this.h0 = -1;
            this.i0 = -1;
            this.j0 = -1;
            this.k0 = -1;
            this.l0 = Integer.MIN_VALUE;
            this.m0 = Integer.MIN_VALUE;
            this.n0 = 0.5f;
            this.r0 = new a.f.a.i.d();
        }

        public void a() {
            this.d0 = false;
            this.a0 = true;
            this.b0 = true;
            if (((ViewGroup.MarginLayoutParams) this).width == -2 && this.W) {
                this.a0 = false;
                if (this.L == 0) {
                    this.L = 1;
                }
            }
            if (((ViewGroup.MarginLayoutParams) this).height == -2 && this.X) {
                this.b0 = false;
                if (this.M == 0) {
                    this.M = 1;
                }
            }
            int i = ((ViewGroup.MarginLayoutParams) this).width;
            if (i == 0 || i == -1) {
                this.a0 = false;
                if (((ViewGroup.MarginLayoutParams) this).width == 0 && this.L == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.W = true;
                }
            }
            int i2 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i2 == 0 || i2 == -1) {
                this.b0 = false;
                if (((ViewGroup.MarginLayoutParams) this).height == 0 && this.M == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.X = true;
                }
            }
            if (this.f1494c == -1.0f && this.f1492a == -1 && this.f1493b == -1) {
                return;
            }
            this.d0 = true;
            this.a0 = true;
            this.b0 = true;
            if (!(this.r0 instanceof f)) {
                this.r0 = new f();
            }
            ((f) this.r0).W(this.V);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x004e  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0055  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0082  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x008a  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x00de  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        @android.annotation.TargetApi(17)
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void resolveLayoutDirection(int r7) {
            /*
                Method dump skipped, instruction units count: 267
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.a.resolveLayoutDirection(int):void");
        }
    }

    public class b implements b.InterfaceC0007b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ConstraintLayout f1499a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1500b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1501c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1502d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1503e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1504f;
        public int g;

        public b(ConstraintLayout constraintLayout) {
            this.f1499a = constraintLayout;
        }

        public final boolean a(int i, int i2, int i3) {
            if (i == i2) {
                return true;
            }
            int mode = View.MeasureSpec.getMode(i);
            View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (mode2 == 1073741824) {
                return (mode == Integer.MIN_VALUE || mode == 0) && i3 == size;
            }
            return false;
        }

        /* JADX WARN: Removed duplicated region for block: B:37:0x007e  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x0108  */
        @android.annotation.SuppressLint({"WrongCall"})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void b(a.f.a.i.d r19, a.f.a.i.l.b.a r20) {
            /*
                Method dump skipped, instruction units count: 757
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.b.b(a.f.a.i.d, a.f.a.i.l.b$a):void");
        }
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1486a = new SparseArray<>();
        this.f1487b = new ArrayList<>(4);
        this.f1488c = new e();
        this.f1489d = 0;
        this.f1490e = 0;
        this.f1491f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
        this.h = true;
        this.i = 257;
        this.j = null;
        this.k = null;
        this.l = -1;
        this.m = new HashMap<>();
        this.n = -1;
        this.o = -1;
        this.p = new SparseArray<>();
        this.q = new b(this);
        this.r = 0;
        this.s = 0;
        f(attributeSet, 0, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1486a = new SparseArray<>();
        this.f1487b = new ArrayList<>(4);
        this.f1488c = new e();
        this.f1489d = 0;
        this.f1490e = 0;
        this.f1491f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
        this.h = true;
        this.i = 257;
        this.j = null;
        this.k = null;
        this.l = -1;
        this.m = new HashMap<>();
        this.n = -1;
        this.o = -1;
        this.p = new SparseArray<>();
        this.q = new b(this);
        this.r = 0;
        this.s = 0;
        f(attributeSet, i, 0);
    }

    private int getPaddingWidth() {
        int iMax = Math.max(0, getPaddingRight()) + Math.max(0, getPaddingLeft());
        int iMax2 = Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart());
        return iMax2 > 0 ? iMax2 : iMax;
    }

    public static j getSharedValues() {
        if (t == null) {
            t = new j();
        }
        return t;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public a generateDefaultLayoutParams() {
        return new a(-2, -2);
    }

    public Object c(int i, Object obj) {
        if (i != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        HashMap<String, Integer> map = this.m;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return this.m.get(str);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    public View d(int i) {
        return this.f1486a.get(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList<a.f.b.b> arrayList = this.f1487b;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i = 0; i < size; i++) {
                this.f1487b.get(i).k();
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(",");
                    if (strArrSplit.length == 4) {
                        int i3 = Integer.parseInt(strArrSplit[0]);
                        int i4 = Integer.parseInt(strArrSplit[1]);
                        int i5 = Integer.parseInt(strArrSplit[2]);
                        int i6 = (int) ((i3 / 1080.0f) * width);
                        int i7 = (int) ((i4 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f2 = i6;
                        float f3 = i7;
                        float f4 = i6 + ((int) ((i5 / 1080.0f) * width));
                        canvas.drawLine(f2, f3, f4, f3, paint);
                        float f5 = i7 + ((int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height));
                        canvas.drawLine(f4, f3, f4, f5, paint);
                        canvas.drawLine(f4, f5, f2, f5, paint);
                        canvas.drawLine(f2, f5, f2, f3, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f2, f3, f4, f5, paint);
                        canvas.drawLine(f2, f5, f4, f3, paint);
                    }
                }
            }
        }
    }

    public final a.f.a.i.d e(View view) {
        if (view == this) {
            return this.f1488c;
        }
        if (view == null) {
            return null;
        }
        if (!(view.getLayoutParams() instanceof a)) {
            view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
            if (!(view.getLayoutParams() instanceof a)) {
                return null;
            }
        }
        return ((a) view.getLayoutParams()).r0;
    }

    public final void f(AttributeSet attributeSet, int i, int i2) {
        e eVar = this.f1488c;
        eVar.j0 = this;
        b bVar = this.q;
        eVar.z0 = bVar;
        eVar.x0.f581f = bVar;
        this.f1486a.put(getId(), this);
        this.j = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, i.ConstraintLayout_Layout, i, i2);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == i.ConstraintLayout_Layout_android_minWidth) {
                    this.f1489d = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1489d);
                } else if (index == i.ConstraintLayout_Layout_android_minHeight) {
                    this.f1490e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1490e);
                } else if (index == i.ConstraintLayout_Layout_android_maxWidth) {
                    this.f1491f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1491f);
                } else if (index == i.ConstraintLayout_Layout_android_maxHeight) {
                    this.g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.g);
                } else if (index == i.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.i = typedArrayObtainStyledAttributes.getInt(index, this.i);
                } else if (index == i.ConstraintLayout_Layout_layoutDescription) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            h(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.k = null;
                        }
                    }
                } else if (index == i.ConstraintLayout_Layout_constraintSet) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        d dVar = new d();
                        this.j = dVar;
                        dVar.f(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.j = null;
                    }
                    this.l = resourceId2;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.f1488c.g0(this.i);
    }

    @Override // android.view.View
    public void forceLayout() {
        this.h = true;
        this.n = -1;
        this.o = -1;
        super.forceLayout();
    }

    public boolean g() {
        return ((getContext().getApplicationInfo().flags & 4194304) != 0) && 1 == getLayoutDirection();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    public int getMaxHeight() {
        return this.g;
    }

    public int getMaxWidth() {
        return this.f1491f;
    }

    public int getMinHeight() {
        return this.f1490e;
    }

    public int getMinWidth() {
        return this.f1489d;
    }

    public int getOptimizationLevel() {
        return this.f1488c.I0;
    }

    public String getSceneString() {
        int id;
        StringBuilder sb = new StringBuilder();
        if (this.f1488c.l == null) {
            int id2 = getId();
            if (id2 != -1) {
                this.f1488c.l = getContext().getResources().getResourceEntryName(id2);
            } else {
                this.f1488c.l = "parent";
            }
        }
        e eVar = this.f1488c;
        if (eVar.m0 == null) {
            eVar.m0 = eVar.l;
            StringBuilder sbD = b.a.a.a.a.d(" setDebugName ");
            sbD.append(this.f1488c.m0);
            Log.v("ConstraintLayout", sbD.toString());
        }
        for (a.f.a.i.d dVar : this.f1488c.v0) {
            View view = (View) dVar.j0;
            if (view != null) {
                if (dVar.l == null && (id = view.getId()) != -1) {
                    dVar.l = getContext().getResources().getResourceEntryName(id);
                }
                if (dVar.m0 == null) {
                    dVar.m0 = dVar.l;
                    StringBuilder sbD2 = b.a.a.a.a.d(" setDebugName ");
                    sbD2.append(dVar.m0);
                    Log.v("ConstraintLayout", sbD2.toString());
                }
            }
        }
        this.f1488c.q(sb);
        return sb.toString();
    }

    public void h(int i) {
        this.k = new c(getContext(), this, i);
    }

    public void i(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        b bVar = this.q;
        int i5 = bVar.f1503e;
        int iResolveSizeAndState = ViewGroup.resolveSizeAndState(i3 + bVar.f1502d, i, 0);
        int iResolveSizeAndState2 = ViewGroup.resolveSizeAndState(i4 + i5, i2, 0) & 16777215;
        int iMin = Math.min(this.f1491f, iResolveSizeAndState & 16777215);
        int iMin2 = Math.min(this.g, iResolveSizeAndState2);
        if (z) {
            iMin |= 16777216;
        }
        if (z2) {
            iMin2 |= 16777216;
        }
        setMeasuredDimension(iMin, iMin2);
        this.n = iMin;
        this.o = iMin2;
    }

    public void j(int i, Object obj, Object obj2) {
        if (i == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.m == null) {
                this.m = new HashMap<>();
            }
            String strSubstring = (String) obj;
            int iIndexOf = strSubstring.indexOf("/");
            if (iIndexOf != -1) {
                strSubstring = strSubstring.substring(iIndexOf + 1);
            }
            this.m.put(strSubstring, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    public final void k(a.f.a.i.d dVar, a aVar, SparseArray<a.f.a.i.d> sparseArray, int i, c.a aVar2) {
        c.a aVar3 = c.a.BASELINE;
        View view = this.f1486a.get(i);
        a.f.a.i.d dVar2 = sparseArray.get(i);
        if (dVar2 == null || view == null || !(view.getLayoutParams() instanceof a)) {
            return;
        }
        aVar.c0 = true;
        if (aVar2 == aVar3) {
            a aVar4 = (a) view.getLayoutParams();
            aVar4.c0 = true;
            aVar4.r0.G = true;
        }
        dVar.i(aVar3).a(dVar2.i(aVar2), aVar.D, aVar.C, true);
        dVar.G = true;
        dVar.i(c.a.TOP).h();
        dVar.i(c.a.BOTTOM).h();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x04ea  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x04f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean l() {
        /*
            Method dump skipped, instruction units count: 1395
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.l():boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View content;
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            a aVar = (a) childAt.getLayoutParams();
            a.f.a.i.d dVar = aVar.r0;
            if ((childAt.getVisibility() != 8 || aVar.d0 || aVar.e0 || aVar.g0 || zIsInEditMode) && !aVar.f0) {
                int iV = dVar.v();
                int iW = dVar.w();
                int iU = dVar.u() + iV;
                int iL = dVar.l() + iW;
                childAt.layout(iV, iW, iU, iL);
                if ((childAt instanceof g) && (content = ((g) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(iV, iW, iU, iL);
                }
            }
        }
        int size = this.f1487b.size();
        if (size > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                this.f1487b.get(i6).i();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:287:0x0585 A[PHI: r12
  0x0585: PHI (r12v23 boolean) = (r12v22 boolean), (r12v22 boolean), (r12v22 boolean), (r12v26 boolean) binds: [B:263:0x054e, B:265:0x0554, B:267:0x0558, B:283:0x057a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0589  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x07b7  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x058d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x016e  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 1977
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        a.f.a.i.d dVarE = e(view);
        if ((view instanceof Guideline) && !(dVarE instanceof f)) {
            a aVar = (a) view.getLayoutParams();
            f fVar = new f();
            aVar.r0 = fVar;
            aVar.d0 = true;
            fVar.W(aVar.V);
        }
        if (view instanceof a.f.b.b) {
            a.f.b.b bVar = (a.f.b.b) view;
            bVar.l();
            ((a) view.getLayoutParams()).e0 = true;
            if (!this.f1487b.contains(bVar)) {
                this.f1487b.add(bVar);
            }
        }
        this.f1486a.put(view.getId(), view);
        this.h = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.f1486a.remove(view.getId());
        a.f.a.i.d dVarE = e(view);
        this.f1488c.v0.remove(dVarE);
        dVarE.F();
        this.f1487b.remove(view);
        this.h = true;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.h = true;
        this.n = -1;
        this.o = -1;
        super.requestLayout();
    }

    public void setConstraintSet(d dVar) {
        this.j = dVar;
    }

    @Override // android.view.View
    public void setId(int i) {
        this.f1486a.remove(getId());
        super.setId(i);
        this.f1486a.put(getId(), this);
    }

    public void setMaxHeight(int i) {
        if (i == this.g) {
            return;
        }
        this.g = i;
        requestLayout();
    }

    public void setMaxWidth(int i) {
        if (i == this.f1491f) {
            return;
        }
        this.f1491f = i;
        requestLayout();
    }

    public void setMinHeight(int i) {
        if (i == this.f1490e) {
            return;
        }
        this.f1490e = i;
        requestLayout();
    }

    public void setMinWidth(int i) {
        if (i == this.f1489d) {
            return;
        }
        this.f1489d = i;
        requestLayout();
    }

    public void setOnConstraintsChanged(a.f.b.f fVar) {
        a.f.b.c cVar = this.k;
    }

    public void setOptimizationLevel(int i) {
        this.i = i;
        e eVar = this.f1488c;
        eVar.I0 = i;
        a.f.a.d.r = eVar.f0(512);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
