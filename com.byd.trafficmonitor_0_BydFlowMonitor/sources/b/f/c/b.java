package b.f.c;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.Guideline;
import b.f.b.j.h;
import com.bydux.api.common.tools.util.ListUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintSet.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f2282a = {0, 4, 8};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static SparseIntArray f2283b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2284c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public HashMap<String, ConstraintAttribute> f2285d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2286e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public HashMap<Integer, a> f2287f = new HashMap<>();

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2288a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f2289b = new d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c f2290c = new c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final C0029b f2291d = new C0029b();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final e f2292e = new e();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public HashMap<String, ConstraintAttribute> f2293f = new HashMap<>();

        public void d(ConstraintLayout.b bVar) {
            C0029b c0029b = this.f2291d;
            bVar.f720d = c0029b.f2302i;
            bVar.f721e = c0029b.j;
            bVar.f722f = c0029b.k;
            bVar.f723g = c0029b.l;
            bVar.f724h = c0029b.m;
            bVar.f725i = c0029b.n;
            bVar.j = c0029b.o;
            bVar.k = c0029b.p;
            bVar.l = c0029b.q;
            bVar.p = c0029b.r;
            bVar.q = c0029b.s;
            bVar.r = c0029b.t;
            bVar.s = c0029b.u;
            ((ViewGroup.MarginLayoutParams) bVar).leftMargin = c0029b.E;
            ((ViewGroup.MarginLayoutParams) bVar).rightMargin = c0029b.F;
            ((ViewGroup.MarginLayoutParams) bVar).topMargin = c0029b.G;
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = c0029b.H;
            bVar.x = c0029b.P;
            bVar.y = c0029b.O;
            bVar.u = c0029b.L;
            bVar.w = c0029b.N;
            bVar.z = c0029b.v;
            bVar.A = c0029b.w;
            bVar.m = c0029b.y;
            bVar.n = c0029b.z;
            C0029b c0029b2 = this.f2291d;
            bVar.o = c0029b2.A;
            bVar.B = c0029b2.x;
            bVar.Q = c0029b2.B;
            bVar.R = c0029b2.C;
            bVar.F = c0029b2.Q;
            bVar.E = c0029b2.R;
            bVar.H = c0029b2.T;
            bVar.G = c0029b2.S;
            bVar.T = c0029b2.i0;
            bVar.U = c0029b2.j0;
            bVar.I = c0029b2.U;
            bVar.J = c0029b2.V;
            bVar.M = c0029b2.W;
            bVar.N = c0029b2.X;
            bVar.K = c0029b2.Y;
            bVar.L = c0029b2.Z;
            bVar.O = c0029b2.a0;
            bVar.P = c0029b2.b0;
            bVar.S = c0029b2.D;
            bVar.f719c = c0029b2.f2301h;
            bVar.f717a = c0029b2.f2299f;
            bVar.f718b = c0029b2.f2300g;
            ((ViewGroup.MarginLayoutParams) bVar).width = c0029b2.f2297d;
            ((ViewGroup.MarginLayoutParams) bVar).height = c0029b2.f2298e;
            String str = c0029b2.h0;
            if (str != null) {
                bVar.V = str;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                bVar.setMarginStart(this.f2291d.J);
                bVar.setMarginEnd(this.f2291d.I);
            }
            bVar.c();
        }

        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.f2291d.a(this.f2291d);
            aVar.f2290c.a(this.f2290c);
            aVar.f2289b.a(this.f2289b);
            aVar.f2292e.a(this.f2292e);
            aVar.f2288a = this.f2288a;
            return aVar;
        }

        public final void f(int i2, ConstraintLayout.b bVar) {
            this.f2288a = i2;
            C0029b c0029b = this.f2291d;
            c0029b.f2302i = bVar.f720d;
            c0029b.j = bVar.f721e;
            c0029b.k = bVar.f722f;
            c0029b.l = bVar.f723g;
            c0029b.m = bVar.f724h;
            c0029b.n = bVar.f725i;
            c0029b.o = bVar.j;
            c0029b.p = bVar.k;
            c0029b.q = bVar.l;
            c0029b.r = bVar.p;
            c0029b.s = bVar.q;
            c0029b.t = bVar.r;
            c0029b.u = bVar.s;
            c0029b.v = bVar.z;
            c0029b.w = bVar.A;
            c0029b.x = bVar.B;
            c0029b.y = bVar.m;
            c0029b.z = bVar.n;
            c0029b.A = bVar.o;
            c0029b.B = bVar.Q;
            c0029b.C = bVar.R;
            c0029b.D = bVar.S;
            c0029b.f2301h = bVar.f719c;
            c0029b.f2299f = bVar.f717a;
            c0029b.f2300g = bVar.f718b;
            C0029b c0029b2 = this.f2291d;
            c0029b2.f2297d = ((ViewGroup.MarginLayoutParams) bVar).width;
            c0029b2.f2298e = ((ViewGroup.MarginLayoutParams) bVar).height;
            c0029b2.E = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            c0029b2.F = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
            c0029b2.G = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            c0029b2.H = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
            c0029b2.Q = bVar.F;
            c0029b2.R = bVar.E;
            c0029b2.T = bVar.H;
            c0029b2.S = bVar.G;
            c0029b2.i0 = bVar.T;
            c0029b2.j0 = bVar.U;
            c0029b2.U = bVar.I;
            c0029b2.V = bVar.J;
            c0029b2.W = bVar.M;
            c0029b2.X = bVar.N;
            c0029b2.Y = bVar.K;
            c0029b2.Z = bVar.L;
            c0029b2.a0 = bVar.O;
            c0029b2.b0 = bVar.P;
            c0029b2.h0 = bVar.V;
            c0029b2.L = bVar.u;
            c0029b2.N = bVar.w;
            c0029b2.K = bVar.t;
            c0029b2.M = bVar.v;
            C0029b c0029b3 = this.f2291d;
            c0029b3.P = bVar.x;
            c0029b3.O = bVar.y;
            if (Build.VERSION.SDK_INT >= 17) {
                c0029b3.I = bVar.getMarginEnd();
                this.f2291d.J = bVar.getMarginStart();
            }
        }

        public final void g(int i2, Constraints.a aVar) {
            f(i2, aVar);
            this.f2289b.f2314d = aVar.p0;
            e eVar = this.f2292e;
            eVar.f2318c = aVar.s0;
            eVar.f2319d = aVar.t0;
            eVar.f2320e = aVar.u0;
            eVar.f2321f = aVar.v0;
            eVar.f2322g = aVar.w0;
            eVar.f2323h = aVar.x0;
            eVar.f2324i = aVar.y0;
            eVar.j = aVar.z0;
            eVar.k = aVar.A0;
            eVar.l = aVar.B0;
            eVar.n = aVar.r0;
            eVar.m = aVar.q0;
        }

        public final void h(ConstraintHelper constraintHelper, int i2, Constraints.a aVar) {
            g(i2, aVar);
            if (constraintHelper instanceof Barrier) {
                C0029b c0029b = this.f2291d;
                c0029b.e0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                c0029b.c0 = barrier.getType();
                this.f2291d.f0 = barrier.getReferencedIds();
                this.f2291d.d0 = barrier.getMargin();
            }
        }
    }

    /* JADX INFO: renamed from: b.f.c.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class C0029b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f2294a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2297d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2298e;
        public int[] f0;
        public String g0;
        public String h0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2295b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2296c = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f2299f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f2300g = -1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f2301h = -1.0f;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f2302i = -1;
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
        public float v = 0.5f;
        public float w = 0.5f;
        public String x = null;
        public int y = -1;
        public int z = 0;
        public float A = 0.0f;
        public int B = -1;
        public int C = -1;
        public int D = -1;
        public int E = -1;
        public int F = -1;
        public int G = -1;
        public int H = -1;
        public int I = -1;
        public int J = -1;
        public int K = -1;
        public int L = -1;
        public int M = -1;
        public int N = -1;
        public int O = -1;
        public int P = -1;
        public float Q = -1.0f;
        public float R = -1.0f;
        public int S = 0;
        public int T = 0;
        public int U = 0;
        public int V = 0;
        public int W = -1;
        public int X = -1;
        public int Y = -1;
        public int Z = -1;
        public float a0 = 1.0f;
        public float b0 = 1.0f;
        public int c0 = -1;
        public int d0 = 0;
        public int e0 = -1;
        public boolean i0 = false;
        public boolean j0 = false;
        public boolean k0 = true;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f2294a = sparseIntArray;
            sparseIntArray.append(b.f.c.e.Layout_layout_constraintLeft_toLeftOf, 24);
            f2294a.append(b.f.c.e.Layout_layout_constraintLeft_toRightOf, 25);
            f2294a.append(b.f.c.e.Layout_layout_constraintRight_toLeftOf, 28);
            f2294a.append(b.f.c.e.Layout_layout_constraintRight_toRightOf, 29);
            f2294a.append(b.f.c.e.Layout_layout_constraintTop_toTopOf, 35);
            f2294a.append(b.f.c.e.Layout_layout_constraintTop_toBottomOf, 34);
            f2294a.append(b.f.c.e.Layout_layout_constraintBottom_toTopOf, 4);
            f2294a.append(b.f.c.e.Layout_layout_constraintBottom_toBottomOf, 3);
            f2294a.append(b.f.c.e.Layout_layout_constraintBaseline_toBaselineOf, 1);
            f2294a.append(b.f.c.e.Layout_layout_editor_absoluteX, 6);
            f2294a.append(b.f.c.e.Layout_layout_editor_absoluteY, 7);
            f2294a.append(b.f.c.e.Layout_layout_constraintGuide_begin, 17);
            f2294a.append(b.f.c.e.Layout_layout_constraintGuide_end, 18);
            f2294a.append(b.f.c.e.Layout_layout_constraintGuide_percent, 19);
            f2294a.append(b.f.c.e.Layout_android_orientation, 26);
            f2294a.append(b.f.c.e.Layout_layout_constraintStart_toEndOf, 31);
            f2294a.append(b.f.c.e.Layout_layout_constraintStart_toStartOf, 32);
            f2294a.append(b.f.c.e.Layout_layout_constraintEnd_toStartOf, 10);
            f2294a.append(b.f.c.e.Layout_layout_constraintEnd_toEndOf, 9);
            f2294a.append(b.f.c.e.Layout_layout_goneMarginLeft, 13);
            f2294a.append(b.f.c.e.Layout_layout_goneMarginTop, 16);
            f2294a.append(b.f.c.e.Layout_layout_goneMarginRight, 14);
            f2294a.append(b.f.c.e.Layout_layout_goneMarginBottom, 11);
            f2294a.append(b.f.c.e.Layout_layout_goneMarginStart, 15);
            f2294a.append(b.f.c.e.Layout_layout_goneMarginEnd, 12);
            f2294a.append(b.f.c.e.Layout_layout_constraintVertical_weight, 38);
            f2294a.append(b.f.c.e.Layout_layout_constraintHorizontal_weight, 37);
            f2294a.append(b.f.c.e.Layout_layout_constraintHorizontal_chainStyle, 39);
            f2294a.append(b.f.c.e.Layout_layout_constraintVertical_chainStyle, 40);
            f2294a.append(b.f.c.e.Layout_layout_constraintHorizontal_bias, 20);
            f2294a.append(b.f.c.e.Layout_layout_constraintVertical_bias, 36);
            f2294a.append(b.f.c.e.Layout_layout_constraintDimensionRatio, 5);
            f2294a.append(b.f.c.e.Layout_layout_constraintLeft_creator, 76);
            f2294a.append(b.f.c.e.Layout_layout_constraintTop_creator, 76);
            f2294a.append(b.f.c.e.Layout_layout_constraintRight_creator, 76);
            f2294a.append(b.f.c.e.Layout_layout_constraintBottom_creator, 76);
            f2294a.append(b.f.c.e.Layout_layout_constraintBaseline_creator, 76);
            f2294a.append(b.f.c.e.Layout_android_layout_marginLeft, 23);
            f2294a.append(b.f.c.e.Layout_android_layout_marginRight, 27);
            f2294a.append(b.f.c.e.Layout_android_layout_marginStart, 30);
            f2294a.append(b.f.c.e.Layout_android_layout_marginEnd, 8);
            f2294a.append(b.f.c.e.Layout_android_layout_marginTop, 33);
            f2294a.append(b.f.c.e.Layout_android_layout_marginBottom, 2);
            f2294a.append(b.f.c.e.Layout_android_layout_width, 22);
            f2294a.append(b.f.c.e.Layout_android_layout_height, 21);
            f2294a.append(b.f.c.e.Layout_layout_constraintCircle, 61);
            f2294a.append(b.f.c.e.Layout_layout_constraintCircleRadius, 62);
            f2294a.append(b.f.c.e.Layout_layout_constraintCircleAngle, 63);
            f2294a.append(b.f.c.e.Layout_layout_constraintWidth_percent, 69);
            f2294a.append(b.f.c.e.Layout_layout_constraintHeight_percent, 70);
            f2294a.append(b.f.c.e.Layout_chainUseRtl, 71);
            f2294a.append(b.f.c.e.Layout_barrierDirection, 72);
            f2294a.append(b.f.c.e.Layout_barrierMargin, 73);
            f2294a.append(b.f.c.e.Layout_constraint_referenced_ids, 74);
            f2294a.append(b.f.c.e.Layout_barrierAllowsGoneWidgets, 75);
        }

        public void a(C0029b c0029b) {
            this.f2295b = c0029b.f2295b;
            this.f2297d = c0029b.f2297d;
            this.f2296c = c0029b.f2296c;
            this.f2298e = c0029b.f2298e;
            this.f2299f = c0029b.f2299f;
            this.f2300g = c0029b.f2300g;
            this.f2301h = c0029b.f2301h;
            this.f2302i = c0029b.f2302i;
            this.j = c0029b.j;
            this.k = c0029b.k;
            this.l = c0029b.l;
            this.m = c0029b.m;
            this.n = c0029b.n;
            this.o = c0029b.o;
            this.p = c0029b.p;
            this.q = c0029b.q;
            this.r = c0029b.r;
            this.s = c0029b.s;
            this.t = c0029b.t;
            this.u = c0029b.u;
            this.v = c0029b.v;
            this.w = c0029b.w;
            this.x = c0029b.x;
            this.y = c0029b.y;
            this.z = c0029b.z;
            this.A = c0029b.A;
            this.B = c0029b.B;
            this.C = c0029b.C;
            this.D = c0029b.D;
            this.E = c0029b.E;
            this.F = c0029b.F;
            this.G = c0029b.G;
            this.H = c0029b.H;
            this.I = c0029b.I;
            this.J = c0029b.J;
            this.K = c0029b.K;
            this.L = c0029b.L;
            this.M = c0029b.M;
            this.N = c0029b.N;
            this.O = c0029b.O;
            this.P = c0029b.P;
            this.Q = c0029b.Q;
            this.R = c0029b.R;
            this.S = c0029b.S;
            this.T = c0029b.T;
            this.U = c0029b.U;
            this.V = c0029b.V;
            this.W = c0029b.W;
            this.X = c0029b.X;
            this.Y = c0029b.Y;
            this.Z = c0029b.Z;
            this.a0 = c0029b.a0;
            this.b0 = c0029b.b0;
            this.c0 = c0029b.c0;
            this.d0 = c0029b.d0;
            this.e0 = c0029b.e0;
            this.h0 = c0029b.h0;
            int[] iArr = c0029b.f0;
            if (iArr != null) {
                this.f0 = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.f0 = null;
            }
            this.g0 = c0029b.g0;
            this.i0 = c0029b.i0;
            this.j0 = c0029b.j0;
            this.k0 = c0029b.k0;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.f.c.e.Layout);
            this.f2296c = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                int i3 = f2294a.get(index);
                if (i3 == 80) {
                    this.i0 = typedArrayObtainStyledAttributes.getBoolean(index, this.i0);
                } else if (i3 != 81) {
                    switch (i3) {
                        case 1:
                            this.q = b.w(typedArrayObtainStyledAttributes, index, this.q);
                            break;
                        case 2:
                            this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.H);
                            break;
                        case 3:
                            this.p = b.w(typedArrayObtainStyledAttributes, index, this.p);
                            break;
                        case 4:
                            this.o = b.w(typedArrayObtainStyledAttributes, index, this.o);
                            break;
                        case 5:
                            this.x = typedArrayObtainStyledAttributes.getString(index);
                            break;
                        case 6:
                            this.B = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.B);
                            break;
                        case 7:
                            this.C = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.C);
                            break;
                        case 8:
                            if (Build.VERSION.SDK_INT >= 17) {
                                this.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.I);
                            }
                            break;
                        case 9:
                            this.u = b.w(typedArrayObtainStyledAttributes, index, this.u);
                            break;
                        case 10:
                            this.t = b.w(typedArrayObtainStyledAttributes, index, this.t);
                            break;
                        case 11:
                            this.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.N);
                            break;
                        case 12:
                            this.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.O);
                            break;
                        case 13:
                            this.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.K);
                            break;
                        case 14:
                            this.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.M);
                            break;
                        case 15:
                            this.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.P);
                            break;
                        case 16:
                            this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.L);
                            break;
                        case 17:
                            this.f2299f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f2299f);
                            break;
                        case 18:
                            this.f2300g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f2300g);
                            break;
                        case 19:
                            this.f2301h = typedArrayObtainStyledAttributes.getFloat(index, this.f2301h);
                            break;
                        case 20:
                            this.v = typedArrayObtainStyledAttributes.getFloat(index, this.v);
                            break;
                        case 21:
                            this.f2298e = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f2298e);
                            break;
                        case 22:
                            this.f2297d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f2297d);
                            break;
                        case 23:
                            this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.E);
                            break;
                        case 24:
                            this.f2302i = b.w(typedArrayObtainStyledAttributes, index, this.f2302i);
                            break;
                        case 25:
                            this.j = b.w(typedArrayObtainStyledAttributes, index, this.j);
                            break;
                        case 26:
                            this.D = typedArrayObtainStyledAttributes.getInt(index, this.D);
                            break;
                        case 27:
                            this.F = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.F);
                            break;
                        case 28:
                            this.k = b.w(typedArrayObtainStyledAttributes, index, this.k);
                            break;
                        case 29:
                            this.l = b.w(typedArrayObtainStyledAttributes, index, this.l);
                            break;
                        case 30:
                            if (Build.VERSION.SDK_INT >= 17) {
                                this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                            }
                            break;
                        case 31:
                            this.r = b.w(typedArrayObtainStyledAttributes, index, this.r);
                            break;
                        case 32:
                            this.s = b.w(typedArrayObtainStyledAttributes, index, this.s);
                            break;
                        case 33:
                            this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.G);
                            break;
                        case 34:
                            this.n = b.w(typedArrayObtainStyledAttributes, index, this.n);
                            break;
                        case 35:
                            this.m = b.w(typedArrayObtainStyledAttributes, index, this.m);
                            break;
                        case 36:
                            this.w = typedArrayObtainStyledAttributes.getFloat(index, this.w);
                            break;
                        case 37:
                            this.R = typedArrayObtainStyledAttributes.getFloat(index, this.R);
                            break;
                        case 38:
                            this.Q = typedArrayObtainStyledAttributes.getFloat(index, this.Q);
                            break;
                        case 39:
                            this.S = typedArrayObtainStyledAttributes.getInt(index, this.S);
                            break;
                        case 40:
                            this.T = typedArrayObtainStyledAttributes.getInt(index, this.T);
                            break;
                        default:
                            switch (i3) {
                                case 54:
                                    this.U = typedArrayObtainStyledAttributes.getInt(index, this.U);
                                    break;
                                case 55:
                                    this.V = typedArrayObtainStyledAttributes.getInt(index, this.V);
                                    break;
                                case 56:
                                    this.W = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.W);
                                    break;
                                case 57:
                                    this.X = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.X);
                                    break;
                                case 58:
                                    this.Y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.Y);
                                    break;
                                case 59:
                                    this.Z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.Z);
                                    break;
                                default:
                                    switch (i3) {
                                        case 61:
                                            this.y = b.w(typedArrayObtainStyledAttributes, index, this.y);
                                            break;
                                        case 62:
                                            this.z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.z);
                                            break;
                                        case 63:
                                            this.A = typedArrayObtainStyledAttributes.getFloat(index, this.A);
                                            break;
                                        default:
                                            switch (i3) {
                                                case 69:
                                                    this.a0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 70:
                                                    this.b0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 71:
                                                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                                    break;
                                                case 72:
                                                    this.c0 = typedArrayObtainStyledAttributes.getInt(index, this.c0);
                                                    break;
                                                case 73:
                                                    this.d0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.d0);
                                                    break;
                                                case 74:
                                                    this.g0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                case 75:
                                                    this.k0 = typedArrayObtainStyledAttributes.getBoolean(index, this.k0);
                                                    break;
                                                case 76:
                                                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f2294a.get(index));
                                                    break;
                                                case 77:
                                                    this.h0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                default:
                                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f2294a.get(index));
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } else {
                    this.j0 = typedArrayObtainStyledAttributes.getBoolean(index, this.j0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f2303a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2304b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2305c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f2306d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2307e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f2308f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f2309g = Float.NaN;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f2310h = Float.NaN;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f2303a = sparseIntArray;
            sparseIntArray.append(b.f.c.e.Motion_motionPathRotate, 1);
            f2303a.append(b.f.c.e.Motion_pathMotionArc, 2);
            f2303a.append(b.f.c.e.Motion_transitionEasing, 3);
            f2303a.append(b.f.c.e.Motion_drawPath, 4);
            f2303a.append(b.f.c.e.Motion_animate_relativeTo, 5);
            f2303a.append(b.f.c.e.Motion_motionStagger, 6);
        }

        public void a(c cVar) {
            this.f2304b = cVar.f2304b;
            this.f2305c = cVar.f2305c;
            this.f2306d = cVar.f2306d;
            this.f2307e = cVar.f2307e;
            this.f2308f = cVar.f2308f;
            this.f2310h = cVar.f2310h;
            this.f2309g = cVar.f2309g;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.f.c.e.Motion);
            this.f2304b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                switch (f2303a.get(index)) {
                    case 1:
                        this.f2310h = typedArrayObtainStyledAttributes.getFloat(index, this.f2310h);
                        break;
                    case 2:
                        this.f2307e = typedArrayObtainStyledAttributes.getInt(index, this.f2307e);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.f2306d = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.f2306d = b.f.a.a.c.f1996b[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.f2308f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.f2305c = b.w(typedArrayObtainStyledAttributes, index, this.f2305c);
                        break;
                    case 6:
                        this.f2309g = typedArrayObtainStyledAttributes.getFloat(index, this.f2309g);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2311a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2312b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2313c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f2314d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f2315e = Float.NaN;

        public void a(d dVar) {
            this.f2311a = dVar.f2311a;
            this.f2312b = dVar.f2312b;
            this.f2314d = dVar.f2314d;
            this.f2315e = dVar.f2315e;
            this.f2313c = dVar.f2313c;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.f.c.e.PropertySet);
            this.f2311a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == b.f.c.e.PropertySet_android_alpha) {
                    this.f2314d = typedArrayObtainStyledAttributes.getFloat(index, this.f2314d);
                } else if (index == b.f.c.e.PropertySet_android_visibility) {
                    this.f2312b = typedArrayObtainStyledAttributes.getInt(index, this.f2312b);
                    this.f2312b = b.f2282a[this.f2312b];
                } else if (index == b.f.c.e.PropertySet_visibilityMode) {
                    this.f2313c = typedArrayObtainStyledAttributes.getInt(index, this.f2313c);
                } else if (index == b.f.c.e.PropertySet_motionProgress) {
                    this.f2315e = typedArrayObtainStyledAttributes.getFloat(index, this.f2315e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f2316a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2317b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f2318c = 0.0f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f2319d = 0.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f2320e = 0.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f2321f = 1.0f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f2322g = 1.0f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f2323h = Float.NaN;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f2324i = Float.NaN;
        public float j = 0.0f;
        public float k = 0.0f;
        public float l = 0.0f;
        public boolean m = false;
        public float n = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f2316a = sparseIntArray;
            sparseIntArray.append(b.f.c.e.Transform_android_rotation, 1);
            f2316a.append(b.f.c.e.Transform_android_rotationX, 2);
            f2316a.append(b.f.c.e.Transform_android_rotationY, 3);
            f2316a.append(b.f.c.e.Transform_android_scaleX, 4);
            f2316a.append(b.f.c.e.Transform_android_scaleY, 5);
            f2316a.append(b.f.c.e.Transform_android_transformPivotX, 6);
            f2316a.append(b.f.c.e.Transform_android_transformPivotY, 7);
            f2316a.append(b.f.c.e.Transform_android_translationX, 8);
            f2316a.append(b.f.c.e.Transform_android_translationY, 9);
            f2316a.append(b.f.c.e.Transform_android_translationZ, 10);
            f2316a.append(b.f.c.e.Transform_android_elevation, 11);
        }

        public void a(e eVar) {
            this.f2317b = eVar.f2317b;
            this.f2318c = eVar.f2318c;
            this.f2319d = eVar.f2319d;
            this.f2320e = eVar.f2320e;
            this.f2321f = eVar.f2321f;
            this.f2322g = eVar.f2322g;
            this.f2323h = eVar.f2323h;
            this.f2324i = eVar.f2324i;
            this.j = eVar.j;
            this.k = eVar.k;
            this.l = eVar.l;
            this.m = eVar.m;
            this.n = eVar.n;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.f.c.e.Transform);
            this.f2317b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                switch (f2316a.get(index)) {
                    case 1:
                        this.f2318c = typedArrayObtainStyledAttributes.getFloat(index, this.f2318c);
                        break;
                    case 2:
                        this.f2319d = typedArrayObtainStyledAttributes.getFloat(index, this.f2319d);
                        break;
                    case 3:
                        this.f2320e = typedArrayObtainStyledAttributes.getFloat(index, this.f2320e);
                        break;
                    case 4:
                        this.f2321f = typedArrayObtainStyledAttributes.getFloat(index, this.f2321f);
                        break;
                    case 5:
                        this.f2322g = typedArrayObtainStyledAttributes.getFloat(index, this.f2322g);
                        break;
                    case 6:
                        this.f2323h = typedArrayObtainStyledAttributes.getDimension(index, this.f2323h);
                        break;
                    case 7:
                        this.f2324i = typedArrayObtainStyledAttributes.getDimension(index, this.f2324i);
                        break;
                    case 8:
                        this.j = typedArrayObtainStyledAttributes.getDimension(index, this.j);
                        break;
                    case 9:
                        this.k = typedArrayObtainStyledAttributes.getDimension(index, this.k);
                        break;
                    case 10:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.l = typedArrayObtainStyledAttributes.getDimension(index, this.l);
                        }
                        break;
                    case 11:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.m = true;
                            this.n = typedArrayObtainStyledAttributes.getDimension(index, this.n);
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f2283b = sparseIntArray;
        sparseIntArray.append(b.f.c.e.Constraint_layout_constraintLeft_toLeftOf, 25);
        f2283b.append(b.f.c.e.Constraint_layout_constraintLeft_toRightOf, 26);
        f2283b.append(b.f.c.e.Constraint_layout_constraintRight_toLeftOf, 29);
        f2283b.append(b.f.c.e.Constraint_layout_constraintRight_toRightOf, 30);
        f2283b.append(b.f.c.e.Constraint_layout_constraintTop_toTopOf, 36);
        f2283b.append(b.f.c.e.Constraint_layout_constraintTop_toBottomOf, 35);
        f2283b.append(b.f.c.e.Constraint_layout_constraintBottom_toTopOf, 4);
        f2283b.append(b.f.c.e.Constraint_layout_constraintBottom_toBottomOf, 3);
        f2283b.append(b.f.c.e.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        f2283b.append(b.f.c.e.Constraint_layout_editor_absoluteX, 6);
        f2283b.append(b.f.c.e.Constraint_layout_editor_absoluteY, 7);
        f2283b.append(b.f.c.e.Constraint_layout_constraintGuide_begin, 17);
        f2283b.append(b.f.c.e.Constraint_layout_constraintGuide_end, 18);
        f2283b.append(b.f.c.e.Constraint_layout_constraintGuide_percent, 19);
        f2283b.append(b.f.c.e.Constraint_android_orientation, 27);
        f2283b.append(b.f.c.e.Constraint_layout_constraintStart_toEndOf, 32);
        f2283b.append(b.f.c.e.Constraint_layout_constraintStart_toStartOf, 33);
        f2283b.append(b.f.c.e.Constraint_layout_constraintEnd_toStartOf, 10);
        f2283b.append(b.f.c.e.Constraint_layout_constraintEnd_toEndOf, 9);
        f2283b.append(b.f.c.e.Constraint_layout_goneMarginLeft, 13);
        f2283b.append(b.f.c.e.Constraint_layout_goneMarginTop, 16);
        f2283b.append(b.f.c.e.Constraint_layout_goneMarginRight, 14);
        f2283b.append(b.f.c.e.Constraint_layout_goneMarginBottom, 11);
        f2283b.append(b.f.c.e.Constraint_layout_goneMarginStart, 15);
        f2283b.append(b.f.c.e.Constraint_layout_goneMarginEnd, 12);
        f2283b.append(b.f.c.e.Constraint_layout_constraintVertical_weight, 40);
        f2283b.append(b.f.c.e.Constraint_layout_constraintHorizontal_weight, 39);
        f2283b.append(b.f.c.e.Constraint_layout_constraintHorizontal_chainStyle, 41);
        f2283b.append(b.f.c.e.Constraint_layout_constraintVertical_chainStyle, 42);
        f2283b.append(b.f.c.e.Constraint_layout_constraintHorizontal_bias, 20);
        f2283b.append(b.f.c.e.Constraint_layout_constraintVertical_bias, 37);
        f2283b.append(b.f.c.e.Constraint_layout_constraintDimensionRatio, 5);
        f2283b.append(b.f.c.e.Constraint_layout_constraintLeft_creator, 82);
        f2283b.append(b.f.c.e.Constraint_layout_constraintTop_creator, 82);
        f2283b.append(b.f.c.e.Constraint_layout_constraintRight_creator, 82);
        f2283b.append(b.f.c.e.Constraint_layout_constraintBottom_creator, 82);
        f2283b.append(b.f.c.e.Constraint_layout_constraintBaseline_creator, 82);
        f2283b.append(b.f.c.e.Constraint_android_layout_marginLeft, 24);
        f2283b.append(b.f.c.e.Constraint_android_layout_marginRight, 28);
        f2283b.append(b.f.c.e.Constraint_android_layout_marginStart, 31);
        f2283b.append(b.f.c.e.Constraint_android_layout_marginEnd, 8);
        f2283b.append(b.f.c.e.Constraint_android_layout_marginTop, 34);
        f2283b.append(b.f.c.e.Constraint_android_layout_marginBottom, 2);
        f2283b.append(b.f.c.e.Constraint_android_layout_width, 23);
        f2283b.append(b.f.c.e.Constraint_android_layout_height, 21);
        f2283b.append(b.f.c.e.Constraint_android_visibility, 22);
        f2283b.append(b.f.c.e.Constraint_android_alpha, 43);
        f2283b.append(b.f.c.e.Constraint_android_elevation, 44);
        f2283b.append(b.f.c.e.Constraint_android_rotationX, 45);
        f2283b.append(b.f.c.e.Constraint_android_rotationY, 46);
        f2283b.append(b.f.c.e.Constraint_android_rotation, 60);
        f2283b.append(b.f.c.e.Constraint_android_scaleX, 47);
        f2283b.append(b.f.c.e.Constraint_android_scaleY, 48);
        f2283b.append(b.f.c.e.Constraint_android_transformPivotX, 49);
        f2283b.append(b.f.c.e.Constraint_android_transformPivotY, 50);
        f2283b.append(b.f.c.e.Constraint_android_translationX, 51);
        f2283b.append(b.f.c.e.Constraint_android_translationY, 52);
        f2283b.append(b.f.c.e.Constraint_android_translationZ, 53);
        f2283b.append(b.f.c.e.Constraint_layout_constraintWidth_default, 54);
        f2283b.append(b.f.c.e.Constraint_layout_constraintHeight_default, 55);
        f2283b.append(b.f.c.e.Constraint_layout_constraintWidth_max, 56);
        f2283b.append(b.f.c.e.Constraint_layout_constraintHeight_max, 57);
        f2283b.append(b.f.c.e.Constraint_layout_constraintWidth_min, 58);
        f2283b.append(b.f.c.e.Constraint_layout_constraintHeight_min, 59);
        f2283b.append(b.f.c.e.Constraint_layout_constraintCircle, 61);
        f2283b.append(b.f.c.e.Constraint_layout_constraintCircleRadius, 62);
        f2283b.append(b.f.c.e.Constraint_layout_constraintCircleAngle, 63);
        f2283b.append(b.f.c.e.Constraint_animate_relativeTo, 64);
        f2283b.append(b.f.c.e.Constraint_transitionEasing, 65);
        f2283b.append(b.f.c.e.Constraint_drawPath, 66);
        f2283b.append(b.f.c.e.Constraint_transitionPathRotate, 67);
        f2283b.append(b.f.c.e.Constraint_motionStagger, 79);
        f2283b.append(b.f.c.e.Constraint_android_id, 38);
        f2283b.append(b.f.c.e.Constraint_motionProgress, 68);
        f2283b.append(b.f.c.e.Constraint_layout_constraintWidth_percent, 69);
        f2283b.append(b.f.c.e.Constraint_layout_constraintHeight_percent, 70);
        f2283b.append(b.f.c.e.Constraint_chainUseRtl, 71);
        f2283b.append(b.f.c.e.Constraint_barrierDirection, 72);
        f2283b.append(b.f.c.e.Constraint_barrierMargin, 73);
        f2283b.append(b.f.c.e.Constraint_constraint_referenced_ids, 74);
        f2283b.append(b.f.c.e.Constraint_barrierAllowsGoneWidgets, 75);
        f2283b.append(b.f.c.e.Constraint_pathMotionArc, 76);
        f2283b.append(b.f.c.e.Constraint_layout_constraintTag, 77);
        f2283b.append(b.f.c.e.Constraint_visibilityMode, 78);
        f2283b.append(b.f.c.e.Constraint_layout_constrainedWidth, 80);
        f2283b.append(b.f.c.e.Constraint_layout_constrainedHeight, 81);
    }

    public static int w(TypedArray typedArray, int i2, int i3) {
        int resourceId = typedArray.getResourceId(i2, i3);
        return resourceId == -1 ? typedArray.getInt(i2, -1) : resourceId;
    }

    public void A(boolean z) {
        this.f2286e = z;
    }

    public void B(boolean z) {
        this.f2284c = z;
    }

    public void c(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!this.f2287f.containsKey(Integer.valueOf(id))) {
                Log.v("ConstraintSet", "id unknown " + b.f.a.b.a.c(childAt));
            } else {
                if (this.f2286e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (this.f2287f.containsKey(Integer.valueOf(id))) {
                    ConstraintAttribute.h(childAt, this.f2287f.get(Integer.valueOf(id)).f2293f);
                }
            }
        }
    }

    public void d(ConstraintLayout constraintLayout) {
        f(constraintLayout, true);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public void e(ConstraintHelper constraintHelper, ConstraintWidget constraintWidget, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        int id = constraintHelper.getId();
        if (this.f2287f.containsKey(Integer.valueOf(id))) {
            a aVar = this.f2287f.get(Integer.valueOf(id));
            if (constraintWidget instanceof h) {
                constraintHelper.n(aVar, (h) constraintWidget, bVar, sparseArray);
            }
        }
    }

    public void f(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.f2287f.keySet());
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!this.f2287f.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + b.f.a.b.a.c(childAt));
            } else {
                if (this.f2286e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.f2287f.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = this.f2287f.get(Integer.valueOf(id));
                        if (childAt instanceof Barrier) {
                            aVar.f2291d.e0 = 1;
                        }
                        int i3 = aVar.f2291d.e0;
                        if (i3 != -1 && i3 == 1) {
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(aVar.f2291d.c0);
                            barrier.setMargin(aVar.f2291d.d0);
                            barrier.setAllowsGoneWidget(aVar.f2291d.k0);
                            C0029b c0029b = aVar.f2291d;
                            int[] iArr = c0029b.f0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = c0029b.g0;
                                if (str != null) {
                                    c0029b.f0 = k(barrier, str);
                                    barrier.setReferencedIds(aVar.f2291d.f0);
                                }
                            }
                        }
                        ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
                        bVar.c();
                        aVar.d(bVar);
                        if (z) {
                            ConstraintAttribute.h(childAt, aVar.f2293f);
                        }
                        childAt.setLayoutParams(bVar);
                        d dVar = aVar.f2289b;
                        if (dVar.f2313c == 0) {
                            childAt.setVisibility(dVar.f2312b);
                        }
                        int i4 = Build.VERSION.SDK_INT;
                        if (i4 >= 17) {
                            childAt.setAlpha(aVar.f2289b.f2314d);
                            childAt.setRotation(aVar.f2292e.f2318c);
                            childAt.setRotationX(aVar.f2292e.f2319d);
                            childAt.setRotationY(aVar.f2292e.f2320e);
                            childAt.setScaleX(aVar.f2292e.f2321f);
                            childAt.setScaleY(aVar.f2292e.f2322g);
                            if (!Float.isNaN(aVar.f2292e.f2323h)) {
                                childAt.setPivotX(aVar.f2292e.f2323h);
                            }
                            if (!Float.isNaN(aVar.f2292e.f2324i)) {
                                childAt.setPivotY(aVar.f2292e.f2324i);
                            }
                            childAt.setTranslationX(aVar.f2292e.j);
                            childAt.setTranslationY(aVar.f2292e.k);
                            if (i4 >= 21) {
                                childAt.setTranslationZ(aVar.f2292e.l);
                                e eVar = aVar.f2292e;
                                if (eVar.m) {
                                    childAt.setElevation(eVar.n);
                                }
                            }
                        }
                    } else {
                        Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + id);
                    }
                }
            }
        }
        for (Integer num : hashSet) {
            a aVar2 = this.f2287f.get(num);
            int i5 = aVar2.f2291d.e0;
            if (i5 != -1 && i5 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                C0029b c0029b2 = aVar2.f2291d;
                int[] iArr2 = c0029b2.f0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = c0029b2.g0;
                    if (str2 != null) {
                        c0029b2.f0 = k(barrier2, str2);
                        barrier2.setReferencedIds(aVar2.f2291d.f0);
                    }
                }
                barrier2.setType(aVar2.f2291d.c0);
                barrier2.setMargin(aVar2.f2291d.d0);
                ConstraintLayout.b bVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.u();
                aVar2.d(bVarGenerateDefaultLayoutParams);
                constraintLayout.addView(barrier2, bVarGenerateDefaultLayoutParams);
            }
            if (aVar2.f2291d.f2295b) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.b bVarGenerateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                aVar2.d(bVarGenerateDefaultLayoutParams2);
                constraintLayout.addView(guideline, bVarGenerateDefaultLayoutParams2);
            }
        }
    }

    public void g(int i2, ConstraintLayout.b bVar) {
        if (this.f2287f.containsKey(Integer.valueOf(i2))) {
            this.f2287f.get(Integer.valueOf(i2)).d(bVar);
        }
    }

    public void h(Context context, int i2) {
        i((ConstraintLayout) LayoutInflater.from(context).inflate(i2, (ViewGroup) null));
    }

    public void i(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.f2287f.clear();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f2286e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f2287f.containsKey(Integer.valueOf(id))) {
                this.f2287f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f2287f.get(Integer.valueOf(id));
            aVar.f2293f = ConstraintAttribute.b(this.f2285d, childAt);
            aVar.f(id, bVar);
            aVar.f2289b.f2312b = childAt.getVisibility();
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 17) {
                aVar.f2289b.f2314d = childAt.getAlpha();
                aVar.f2292e.f2318c = childAt.getRotation();
                aVar.f2292e.f2319d = childAt.getRotationX();
                aVar.f2292e.f2320e = childAt.getRotationY();
                aVar.f2292e.f2321f = childAt.getScaleX();
                aVar.f2292e.f2322g = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    e eVar = aVar.f2292e;
                    eVar.f2323h = pivotX;
                    eVar.f2324i = pivotY;
                }
                aVar.f2292e.j = childAt.getTranslationX();
                aVar.f2292e.k = childAt.getTranslationY();
                if (i3 >= 21) {
                    aVar.f2292e.l = childAt.getTranslationZ();
                    e eVar2 = aVar.f2292e;
                    if (eVar2.m) {
                        eVar2.n = childAt.getElevation();
                    }
                }
            }
            if (childAt instanceof Barrier) {
                Barrier barrier = (Barrier) childAt;
                aVar.f2291d.k0 = barrier.v();
                aVar.f2291d.f0 = barrier.getReferencedIds();
                aVar.f2291d.c0 = barrier.getType();
                aVar.f2291d.d0 = barrier.getMargin();
            }
        }
    }

    public void j(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.f2287f.clear();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraints.getChildAt(i2);
            Constraints.a aVar = (Constraints.a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f2286e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f2287f.containsKey(Integer.valueOf(id))) {
                this.f2287f.put(Integer.valueOf(id), new a());
            }
            a aVar2 = this.f2287f.get(Integer.valueOf(id));
            if (childAt instanceof ConstraintHelper) {
                aVar2.h((ConstraintHelper) childAt, id, aVar);
            }
            aVar2.g(id, aVar);
        }
    }

    public final int[] k(View view, String str) {
        int iIntValue;
        Object objE;
        String[] strArrSplit = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
        Context context = view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < strArrSplit.length) {
            String strTrim = strArrSplit[i2].trim();
            try {
                iIntValue = b.f.c.d.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
                iIntValue = 0;
            }
            if (iIntValue == 0) {
                iIntValue = context.getResources().getIdentifier(strTrim, "id", context.getPackageName());
            }
            if (iIntValue == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (objE = ((ConstraintLayout) view.getParent()).e(0, strTrim)) != null && (objE instanceof Integer)) {
                iIntValue = ((Integer) objE).intValue();
            }
            iArr[i3] = iIntValue;
            i2++;
            i3++;
        }
        return i3 != strArrSplit.length ? Arrays.copyOf(iArr, i3) : iArr;
    }

    public final a l(Context context, AttributeSet attributeSet) {
        a aVar = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.f.c.e.Constraint);
        x(context, aVar, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    public final a m(int i2) {
        if (!this.f2287f.containsKey(Integer.valueOf(i2))) {
            this.f2287f.put(Integer.valueOf(i2), new a());
        }
        return this.f2287f.get(Integer.valueOf(i2));
    }

    public a n(int i2) {
        if (this.f2287f.containsKey(Integer.valueOf(i2))) {
            return this.f2287f.get(Integer.valueOf(i2));
        }
        return null;
    }

    public int o(int i2) {
        return m(i2).f2291d.f2298e;
    }

    public int[] p() {
        Integer[] numArr = (Integer[]) this.f2287f.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = numArr[i2].intValue();
        }
        return iArr;
    }

    public a q(int i2) {
        return m(i2);
    }

    public int r(int i2) {
        return m(i2).f2289b.f2312b;
    }

    public int s(int i2) {
        return m(i2).f2289b.f2313c;
    }

    public int t(int i2) {
        return m(i2).f2291d.f2297d;
    }

    public void u(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    a aVarL = l(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        aVarL.f2291d.f2295b = true;
                    }
                    this.f2287f.put(Integer.valueOf(aVarL.f2288a), aVarL);
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:98:0x0179, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void v(android.content.Context r10, org.xmlpull.v1.XmlPullParser r11) {
        /*
            Method dump skipped, instruction units count: 448
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.f.c.b.v(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public final void x(Context context, a aVar, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArray.getIndex(i2);
            if (index != b.f.c.e.Constraint_android_id && b.f.c.e.Constraint_android_layout_marginStart != index && b.f.c.e.Constraint_android_layout_marginEnd != index) {
                aVar.f2290c.f2304b = true;
                aVar.f2291d.f2296c = true;
                aVar.f2289b.f2311a = true;
                aVar.f2292e.f2317b = true;
            }
            switch (f2283b.get(index)) {
                case 1:
                    C0029b c0029b = aVar.f2291d;
                    c0029b.q = w(typedArray, index, c0029b.q);
                    break;
                case 2:
                    C0029b c0029b2 = aVar.f2291d;
                    c0029b2.H = typedArray.getDimensionPixelSize(index, c0029b2.H);
                    break;
                case 3:
                    C0029b c0029b3 = aVar.f2291d;
                    c0029b3.p = w(typedArray, index, c0029b3.p);
                    break;
                case 4:
                    C0029b c0029b4 = aVar.f2291d;
                    c0029b4.o = w(typedArray, index, c0029b4.o);
                    break;
                case 5:
                    aVar.f2291d.x = typedArray.getString(index);
                    break;
                case 6:
                    C0029b c0029b5 = aVar.f2291d;
                    c0029b5.B = typedArray.getDimensionPixelOffset(index, c0029b5.B);
                    break;
                case 7:
                    C0029b c0029b6 = aVar.f2291d;
                    c0029b6.C = typedArray.getDimensionPixelOffset(index, c0029b6.C);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0029b c0029b7 = aVar.f2291d;
                        c0029b7.I = typedArray.getDimensionPixelSize(index, c0029b7.I);
                    }
                    break;
                case 9:
                    C0029b c0029b8 = aVar.f2291d;
                    c0029b8.u = w(typedArray, index, c0029b8.u);
                    break;
                case 10:
                    C0029b c0029b9 = aVar.f2291d;
                    c0029b9.t = w(typedArray, index, c0029b9.t);
                    break;
                case 11:
                    C0029b c0029b10 = aVar.f2291d;
                    c0029b10.N = typedArray.getDimensionPixelSize(index, c0029b10.N);
                    break;
                case 12:
                    C0029b c0029b11 = aVar.f2291d;
                    c0029b11.O = typedArray.getDimensionPixelSize(index, c0029b11.O);
                    break;
                case 13:
                    C0029b c0029b12 = aVar.f2291d;
                    c0029b12.K = typedArray.getDimensionPixelSize(index, c0029b12.K);
                    break;
                case 14:
                    C0029b c0029b13 = aVar.f2291d;
                    c0029b13.M = typedArray.getDimensionPixelSize(index, c0029b13.M);
                    break;
                case 15:
                    C0029b c0029b14 = aVar.f2291d;
                    c0029b14.P = typedArray.getDimensionPixelSize(index, c0029b14.P);
                    break;
                case 16:
                    C0029b c0029b15 = aVar.f2291d;
                    c0029b15.L = typedArray.getDimensionPixelSize(index, c0029b15.L);
                    break;
                case 17:
                    C0029b c0029b16 = aVar.f2291d;
                    c0029b16.f2299f = typedArray.getDimensionPixelOffset(index, c0029b16.f2299f);
                    break;
                case 18:
                    C0029b c0029b17 = aVar.f2291d;
                    c0029b17.f2300g = typedArray.getDimensionPixelOffset(index, c0029b17.f2300g);
                    break;
                case 19:
                    C0029b c0029b18 = aVar.f2291d;
                    c0029b18.f2301h = typedArray.getFloat(index, c0029b18.f2301h);
                    break;
                case 20:
                    C0029b c0029b19 = aVar.f2291d;
                    c0029b19.v = typedArray.getFloat(index, c0029b19.v);
                    break;
                case 21:
                    C0029b c0029b20 = aVar.f2291d;
                    c0029b20.f2298e = typedArray.getLayoutDimension(index, c0029b20.f2298e);
                    break;
                case 22:
                    d dVar = aVar.f2289b;
                    dVar.f2312b = typedArray.getInt(index, dVar.f2312b);
                    d dVar2 = aVar.f2289b;
                    dVar2.f2312b = f2282a[dVar2.f2312b];
                    break;
                case 23:
                    C0029b c0029b21 = aVar.f2291d;
                    c0029b21.f2297d = typedArray.getLayoutDimension(index, c0029b21.f2297d);
                    break;
                case 24:
                    C0029b c0029b22 = aVar.f2291d;
                    c0029b22.E = typedArray.getDimensionPixelSize(index, c0029b22.E);
                    break;
                case 25:
                    C0029b c0029b23 = aVar.f2291d;
                    c0029b23.f2302i = w(typedArray, index, c0029b23.f2302i);
                    break;
                case 26:
                    C0029b c0029b24 = aVar.f2291d;
                    c0029b24.j = w(typedArray, index, c0029b24.j);
                    break;
                case 27:
                    C0029b c0029b25 = aVar.f2291d;
                    c0029b25.D = typedArray.getInt(index, c0029b25.D);
                    break;
                case 28:
                    C0029b c0029b26 = aVar.f2291d;
                    c0029b26.F = typedArray.getDimensionPixelSize(index, c0029b26.F);
                    break;
                case 29:
                    C0029b c0029b27 = aVar.f2291d;
                    c0029b27.k = w(typedArray, index, c0029b27.k);
                    break;
                case 30:
                    C0029b c0029b28 = aVar.f2291d;
                    c0029b28.l = w(typedArray, index, c0029b28.l);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0029b c0029b29 = aVar.f2291d;
                        c0029b29.J = typedArray.getDimensionPixelSize(index, c0029b29.J);
                    }
                    break;
                case 32:
                    C0029b c0029b30 = aVar.f2291d;
                    c0029b30.r = w(typedArray, index, c0029b30.r);
                    break;
                case 33:
                    C0029b c0029b31 = aVar.f2291d;
                    c0029b31.s = w(typedArray, index, c0029b31.s);
                    break;
                case 34:
                    C0029b c0029b32 = aVar.f2291d;
                    c0029b32.G = typedArray.getDimensionPixelSize(index, c0029b32.G);
                    break;
                case 35:
                    C0029b c0029b33 = aVar.f2291d;
                    c0029b33.n = w(typedArray, index, c0029b33.n);
                    break;
                case 36:
                    C0029b c0029b34 = aVar.f2291d;
                    c0029b34.m = w(typedArray, index, c0029b34.m);
                    break;
                case 37:
                    C0029b c0029b35 = aVar.f2291d;
                    c0029b35.w = typedArray.getFloat(index, c0029b35.w);
                    break;
                case 38:
                    aVar.f2288a = typedArray.getResourceId(index, aVar.f2288a);
                    break;
                case 39:
                    C0029b c0029b36 = aVar.f2291d;
                    c0029b36.R = typedArray.getFloat(index, c0029b36.R);
                    break;
                case 40:
                    C0029b c0029b37 = aVar.f2291d;
                    c0029b37.Q = typedArray.getFloat(index, c0029b37.Q);
                    break;
                case 41:
                    C0029b c0029b38 = aVar.f2291d;
                    c0029b38.S = typedArray.getInt(index, c0029b38.S);
                    break;
                case 42:
                    C0029b c0029b39 = aVar.f2291d;
                    c0029b39.T = typedArray.getInt(index, c0029b39.T);
                    break;
                case 43:
                    d dVar3 = aVar.f2289b;
                    dVar3.f2314d = typedArray.getFloat(index, dVar3.f2314d);
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar = aVar.f2292e;
                        eVar.m = true;
                        eVar.n = typedArray.getDimension(index, eVar.n);
                    }
                    break;
                case 45:
                    e eVar2 = aVar.f2292e;
                    eVar2.f2319d = typedArray.getFloat(index, eVar2.f2319d);
                    break;
                case 46:
                    e eVar3 = aVar.f2292e;
                    eVar3.f2320e = typedArray.getFloat(index, eVar3.f2320e);
                    break;
                case 47:
                    e eVar4 = aVar.f2292e;
                    eVar4.f2321f = typedArray.getFloat(index, eVar4.f2321f);
                    break;
                case 48:
                    e eVar5 = aVar.f2292e;
                    eVar5.f2322g = typedArray.getFloat(index, eVar5.f2322g);
                    break;
                case 49:
                    e eVar6 = aVar.f2292e;
                    eVar6.f2323h = typedArray.getDimension(index, eVar6.f2323h);
                    break;
                case 50:
                    e eVar7 = aVar.f2292e;
                    eVar7.f2324i = typedArray.getDimension(index, eVar7.f2324i);
                    break;
                case 51:
                    e eVar8 = aVar.f2292e;
                    eVar8.j = typedArray.getDimension(index, eVar8.j);
                    break;
                case 52:
                    e eVar9 = aVar.f2292e;
                    eVar9.k = typedArray.getDimension(index, eVar9.k);
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar10 = aVar.f2292e;
                        eVar10.l = typedArray.getDimension(index, eVar10.l);
                    }
                    break;
                case 54:
                    C0029b c0029b40 = aVar.f2291d;
                    c0029b40.U = typedArray.getInt(index, c0029b40.U);
                    break;
                case 55:
                    C0029b c0029b41 = aVar.f2291d;
                    c0029b41.V = typedArray.getInt(index, c0029b41.V);
                    break;
                case 56:
                    C0029b c0029b42 = aVar.f2291d;
                    c0029b42.W = typedArray.getDimensionPixelSize(index, c0029b42.W);
                    break;
                case 57:
                    C0029b c0029b43 = aVar.f2291d;
                    c0029b43.X = typedArray.getDimensionPixelSize(index, c0029b43.X);
                    break;
                case 58:
                    C0029b c0029b44 = aVar.f2291d;
                    c0029b44.Y = typedArray.getDimensionPixelSize(index, c0029b44.Y);
                    break;
                case 59:
                    C0029b c0029b45 = aVar.f2291d;
                    c0029b45.Z = typedArray.getDimensionPixelSize(index, c0029b45.Z);
                    break;
                case 60:
                    e eVar11 = aVar.f2292e;
                    eVar11.f2318c = typedArray.getFloat(index, eVar11.f2318c);
                    break;
                case 61:
                    C0029b c0029b46 = aVar.f2291d;
                    c0029b46.y = w(typedArray, index, c0029b46.y);
                    break;
                case 62:
                    C0029b c0029b47 = aVar.f2291d;
                    c0029b47.z = typedArray.getDimensionPixelSize(index, c0029b47.z);
                    break;
                case 63:
                    C0029b c0029b48 = aVar.f2291d;
                    c0029b48.A = typedArray.getFloat(index, c0029b48.A);
                    break;
                case 64:
                    c cVar = aVar.f2290c;
                    cVar.f2305c = w(typedArray, index, cVar.f2305c);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.f2290c.f2306d = typedArray.getString(index);
                    } else {
                        aVar.f2290c.f2306d = b.f.a.a.c.f1996b[typedArray.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    aVar.f2290c.f2308f = typedArray.getInt(index, 0);
                    break;
                case 67:
                    c cVar2 = aVar.f2290c;
                    cVar2.f2310h = typedArray.getFloat(index, cVar2.f2310h);
                    break;
                case 68:
                    d dVar4 = aVar.f2289b;
                    dVar4.f2315e = typedArray.getFloat(index, dVar4.f2315e);
                    break;
                case 69:
                    aVar.f2291d.a0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    aVar.f2291d.b0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    C0029b c0029b49 = aVar.f2291d;
                    c0029b49.c0 = typedArray.getInt(index, c0029b49.c0);
                    break;
                case 73:
                    C0029b c0029b50 = aVar.f2291d;
                    c0029b50.d0 = typedArray.getDimensionPixelSize(index, c0029b50.d0);
                    break;
                case 74:
                    aVar.f2291d.g0 = typedArray.getString(index);
                    break;
                case 75:
                    C0029b c0029b51 = aVar.f2291d;
                    c0029b51.k0 = typedArray.getBoolean(index, c0029b51.k0);
                    break;
                case 76:
                    c cVar3 = aVar.f2290c;
                    cVar3.f2307e = typedArray.getInt(index, cVar3.f2307e);
                    break;
                case 77:
                    aVar.f2291d.h0 = typedArray.getString(index);
                    break;
                case 78:
                    d dVar5 = aVar.f2289b;
                    dVar5.f2313c = typedArray.getInt(index, dVar5.f2313c);
                    break;
                case 79:
                    c cVar4 = aVar.f2290c;
                    cVar4.f2309g = typedArray.getFloat(index, cVar4.f2309g);
                    break;
                case 80:
                    C0029b c0029b52 = aVar.f2291d;
                    c0029b52.i0 = typedArray.getBoolean(index, c0029b52.i0);
                    break;
                case 81:
                    C0029b c0029b53 = aVar.f2291d;
                    c0029b53.j0 = typedArray.getBoolean(index, c0029b53.j0);
                    break;
                case 82:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f2283b.get(index));
                    break;
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f2283b.get(index));
                    break;
            }
        }
    }

    public void y(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f2286e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f2287f.containsKey(Integer.valueOf(id))) {
                this.f2287f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f2287f.get(Integer.valueOf(id));
            if (!aVar.f2291d.f2296c) {
                aVar.f(id, bVar);
                if (childAt instanceof ConstraintHelper) {
                    aVar.f2291d.f0 = ((ConstraintHelper) childAt).getReferencedIds();
                    if (childAt instanceof Barrier) {
                        Barrier barrier = (Barrier) childAt;
                        aVar.f2291d.k0 = barrier.v();
                        aVar.f2291d.c0 = barrier.getType();
                        aVar.f2291d.d0 = barrier.getMargin();
                    }
                }
                aVar.f2291d.f2296c = true;
            }
            d dVar = aVar.f2289b;
            if (!dVar.f2311a) {
                dVar.f2312b = childAt.getVisibility();
                aVar.f2289b.f2314d = childAt.getAlpha();
                aVar.f2289b.f2311a = true;
            }
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 17) {
                e eVar = aVar.f2292e;
                if (!eVar.f2317b) {
                    eVar.f2317b = true;
                    eVar.f2318c = childAt.getRotation();
                    aVar.f2292e.f2319d = childAt.getRotationX();
                    aVar.f2292e.f2320e = childAt.getRotationY();
                    aVar.f2292e.f2321f = childAt.getScaleX();
                    aVar.f2292e.f2322g = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        e eVar2 = aVar.f2292e;
                        eVar2.f2323h = pivotX;
                        eVar2.f2324i = pivotY;
                    }
                    aVar.f2292e.j = childAt.getTranslationX();
                    aVar.f2292e.k = childAt.getTranslationY();
                    if (i3 >= 21) {
                        aVar.f2292e.l = childAt.getTranslationZ();
                        e eVar3 = aVar.f2292e;
                        if (eVar3.m) {
                            eVar3.n = childAt.getElevation();
                        }
                    }
                }
            }
        }
    }

    public void z(b bVar) {
        for (Integer num : bVar.f2287f.keySet()) {
            int iIntValue = num.intValue();
            a aVar = bVar.f2287f.get(num);
            if (!this.f2287f.containsKey(Integer.valueOf(iIntValue))) {
                this.f2287f.put(Integer.valueOf(iIntValue), new a());
            }
            a aVar2 = this.f2287f.get(Integer.valueOf(iIntValue));
            C0029b c0029b = aVar2.f2291d;
            if (!c0029b.f2296c) {
                c0029b.a(aVar.f2291d);
            }
            d dVar = aVar2.f2289b;
            if (!dVar.f2311a) {
                dVar.a(aVar.f2289b);
            }
            e eVar = aVar2.f2292e;
            if (!eVar.f2317b) {
                eVar.a(aVar.f2292e);
            }
            c cVar = aVar2.f2290c;
            if (!cVar.f2304b) {
                cVar.a(aVar.f2290c);
            }
            for (String str : aVar.f2293f.keySet()) {
                if (!aVar2.f2293f.containsKey(str)) {
                    aVar2.f2293f.put(str, aVar.f2293f.get(str));
                }
            }
        }
    }
}
