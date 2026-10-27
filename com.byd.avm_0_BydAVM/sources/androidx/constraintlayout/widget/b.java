package androidx.constraintlayout.widget;

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
import androidx.constraintlayout.solver.widgets.h;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
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
    private static final int[] f752a = {0, 4, 8};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static SparseIntArray f753b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f754c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HashMap<String, ConstraintAttribute> f755d = new HashMap<>();
    private boolean e = true;
    private HashMap<Integer, a> f = new HashMap<>();

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f756a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f757b = new d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c f758c = new c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final C0011b f759d = new C0011b();
        public final e e = new e();
        public HashMap<String, ConstraintAttribute> f = new HashMap<>();

        /* JADX INFO: Access modifiers changed from: private */
        public void f(int i, ConstraintLayout.b bVar) {
            this.f756a = i;
            C0011b c0011b = this.f759d;
            c0011b.i = bVar.f730d;
            c0011b.j = bVar.e;
            c0011b.k = bVar.f;
            c0011b.l = bVar.g;
            c0011b.m = bVar.h;
            c0011b.n = bVar.i;
            c0011b.o = bVar.j;
            c0011b.p = bVar.k;
            c0011b.q = bVar.l;
            c0011b.r = bVar.p;
            c0011b.s = bVar.q;
            c0011b.t = bVar.r;
            c0011b.u = bVar.s;
            c0011b.v = bVar.z;
            c0011b.w = bVar.A;
            c0011b.x = bVar.B;
            c0011b.y = bVar.m;
            c0011b.z = bVar.n;
            c0011b.A = bVar.o;
            c0011b.B = bVar.Q;
            c0011b.C = bVar.R;
            c0011b.D = bVar.S;
            c0011b.h = bVar.f729c;
            c0011b.f = bVar.f727a;
            c0011b.g = bVar.f728b;
            C0011b c0011b2 = this.f759d;
            c0011b2.f763d = ((ViewGroup.MarginLayoutParams) bVar).width;
            c0011b2.e = ((ViewGroup.MarginLayoutParams) bVar).height;
            c0011b2.E = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            c0011b2.F = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
            c0011b2.G = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            c0011b2.H = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
            c0011b2.Q = bVar.F;
            c0011b2.R = bVar.E;
            c0011b2.T = bVar.H;
            c0011b2.S = bVar.G;
            c0011b2.i0 = bVar.T;
            c0011b2.j0 = bVar.U;
            c0011b2.U = bVar.I;
            c0011b2.V = bVar.J;
            c0011b2.W = bVar.M;
            c0011b2.X = bVar.N;
            c0011b2.Y = bVar.K;
            c0011b2.Z = bVar.L;
            c0011b2.a0 = bVar.O;
            c0011b2.b0 = bVar.P;
            c0011b2.h0 = bVar.V;
            c0011b2.L = bVar.u;
            c0011b2.N = bVar.w;
            c0011b2.K = bVar.t;
            c0011b2.M = bVar.v;
            C0011b c0011b3 = this.f759d;
            c0011b3.P = bVar.x;
            c0011b3.O = bVar.y;
            if (Build.VERSION.SDK_INT >= 17) {
                c0011b3.I = bVar.getMarginEnd();
                this.f759d.J = bVar.getMarginStart();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g(int i, Constraints.a aVar) {
            f(i, aVar);
            this.f757b.f771d = aVar.p0;
            e eVar = this.e;
            eVar.f774c = aVar.s0;
            eVar.f775d = aVar.t0;
            eVar.e = aVar.u0;
            eVar.f = aVar.v0;
            eVar.g = aVar.w0;
            eVar.h = aVar.x0;
            eVar.i = aVar.y0;
            eVar.j = aVar.z0;
            eVar.k = aVar.A0;
            eVar.l = aVar.B0;
            eVar.n = aVar.r0;
            eVar.m = aVar.q0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void h(ConstraintHelper constraintHelper, int i, Constraints.a aVar) {
            g(i, aVar);
            if (constraintHelper instanceof Barrier) {
                C0011b c0011b = this.f759d;
                c0011b.e0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                c0011b.c0 = barrier.getType();
                this.f759d.f0 = barrier.getReferencedIds();
                this.f759d.d0 = barrier.getMargin();
            }
        }

        public void d(ConstraintLayout.b bVar) {
            C0011b c0011b = this.f759d;
            bVar.f730d = c0011b.i;
            bVar.e = c0011b.j;
            bVar.f = c0011b.k;
            bVar.g = c0011b.l;
            bVar.h = c0011b.m;
            bVar.i = c0011b.n;
            bVar.j = c0011b.o;
            bVar.k = c0011b.p;
            bVar.l = c0011b.q;
            bVar.p = c0011b.r;
            bVar.q = c0011b.s;
            bVar.r = c0011b.t;
            bVar.s = c0011b.u;
            ((ViewGroup.MarginLayoutParams) bVar).leftMargin = c0011b.E;
            ((ViewGroup.MarginLayoutParams) bVar).rightMargin = c0011b.F;
            ((ViewGroup.MarginLayoutParams) bVar).topMargin = c0011b.G;
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = c0011b.H;
            bVar.x = c0011b.P;
            bVar.y = c0011b.O;
            bVar.u = c0011b.L;
            bVar.w = c0011b.N;
            bVar.z = c0011b.v;
            bVar.A = c0011b.w;
            bVar.m = c0011b.y;
            bVar.n = c0011b.z;
            C0011b c0011b2 = this.f759d;
            bVar.o = c0011b2.A;
            bVar.B = c0011b2.x;
            bVar.Q = c0011b2.B;
            bVar.R = c0011b2.C;
            bVar.F = c0011b2.Q;
            bVar.E = c0011b2.R;
            bVar.H = c0011b2.T;
            bVar.G = c0011b2.S;
            bVar.T = c0011b2.i0;
            bVar.U = c0011b2.j0;
            bVar.I = c0011b2.U;
            bVar.J = c0011b2.V;
            bVar.M = c0011b2.W;
            bVar.N = c0011b2.X;
            bVar.K = c0011b2.Y;
            bVar.L = c0011b2.Z;
            bVar.O = c0011b2.a0;
            bVar.P = c0011b2.b0;
            bVar.S = c0011b2.D;
            bVar.f729c = c0011b2.h;
            bVar.f727a = c0011b2.f;
            bVar.f728b = c0011b2.g;
            ((ViewGroup.MarginLayoutParams) bVar).width = c0011b2.f763d;
            ((ViewGroup.MarginLayoutParams) bVar).height = c0011b2.e;
            String str = c0011b2.h0;
            if (str != null) {
                bVar.V = str;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                bVar.setMarginStart(this.f759d.J);
                bVar.setMarginEnd(this.f759d.I);
            }
            bVar.c();
        }

        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.f759d.a(this.f759d);
            aVar.f758c.a(this.f758c);
            aVar.f757b.a(this.f757b);
            aVar.e.a(this.e);
            aVar.f756a = this.f756a;
            return aVar;
        }
    }

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class C0011b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f760a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f763d;
        public int e;
        public int[] f0;
        public String g0;
        public String h0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f761b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f762c = false;
        public int f = -1;
        public int g = -1;
        public float h = -1.0f;
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
            f760a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.O5, 24);
            f760a.append(androidx.constraintlayout.widget.e.P5, 25);
            f760a.append(androidx.constraintlayout.widget.e.R5, 28);
            f760a.append(androidx.constraintlayout.widget.e.S5, 29);
            f760a.append(androidx.constraintlayout.widget.e.X5, 35);
            f760a.append(androidx.constraintlayout.widget.e.W5, 34);
            f760a.append(androidx.constraintlayout.widget.e.z5, 4);
            f760a.append(androidx.constraintlayout.widget.e.y5, 3);
            f760a.append(androidx.constraintlayout.widget.e.w5, 1);
            f760a.append(androidx.constraintlayout.widget.e.c6, 6);
            f760a.append(androidx.constraintlayout.widget.e.d6, 7);
            f760a.append(androidx.constraintlayout.widget.e.G5, 17);
            f760a.append(androidx.constraintlayout.widget.e.H5, 18);
            f760a.append(androidx.constraintlayout.widget.e.I5, 19);
            f760a.append(androidx.constraintlayout.widget.e.h5, 26);
            f760a.append(androidx.constraintlayout.widget.e.T5, 31);
            f760a.append(androidx.constraintlayout.widget.e.U5, 32);
            f760a.append(androidx.constraintlayout.widget.e.F5, 10);
            f760a.append(androidx.constraintlayout.widget.e.E5, 9);
            f760a.append(androidx.constraintlayout.widget.e.g6, 13);
            f760a.append(androidx.constraintlayout.widget.e.j6, 16);
            f760a.append(androidx.constraintlayout.widget.e.h6, 14);
            f760a.append(androidx.constraintlayout.widget.e.e6, 11);
            f760a.append(androidx.constraintlayout.widget.e.i6, 15);
            f760a.append(androidx.constraintlayout.widget.e.f6, 12);
            f760a.append(androidx.constraintlayout.widget.e.a6, 38);
            f760a.append(androidx.constraintlayout.widget.e.M5, 37);
            f760a.append(androidx.constraintlayout.widget.e.L5, 39);
            f760a.append(androidx.constraintlayout.widget.e.Z5, 40);
            f760a.append(androidx.constraintlayout.widget.e.K5, 20);
            f760a.append(androidx.constraintlayout.widget.e.Y5, 36);
            f760a.append(androidx.constraintlayout.widget.e.D5, 5);
            f760a.append(androidx.constraintlayout.widget.e.N5, 76);
            f760a.append(androidx.constraintlayout.widget.e.V5, 76);
            f760a.append(androidx.constraintlayout.widget.e.Q5, 76);
            f760a.append(androidx.constraintlayout.widget.e.x5, 76);
            f760a.append(androidx.constraintlayout.widget.e.v5, 76);
            f760a.append(androidx.constraintlayout.widget.e.k5, 23);
            f760a.append(androidx.constraintlayout.widget.e.m5, 27);
            f760a.append(androidx.constraintlayout.widget.e.o5, 30);
            f760a.append(androidx.constraintlayout.widget.e.p5, 8);
            f760a.append(androidx.constraintlayout.widget.e.l5, 33);
            f760a.append(androidx.constraintlayout.widget.e.n5, 2);
            f760a.append(androidx.constraintlayout.widget.e.i5, 22);
            f760a.append(androidx.constraintlayout.widget.e.j5, 21);
            f760a.append(androidx.constraintlayout.widget.e.A5, 61);
            f760a.append(androidx.constraintlayout.widget.e.C5, 62);
            f760a.append(androidx.constraintlayout.widget.e.B5, 63);
            f760a.append(androidx.constraintlayout.widget.e.b6, 69);
            f760a.append(androidx.constraintlayout.widget.e.J5, 70);
            f760a.append(androidx.constraintlayout.widget.e.t5, 71);
            f760a.append(androidx.constraintlayout.widget.e.r5, 72);
            f760a.append(androidx.constraintlayout.widget.e.s5, 73);
            f760a.append(androidx.constraintlayout.widget.e.u5, 74);
            f760a.append(androidx.constraintlayout.widget.e.q5, 75);
        }

        public void a(C0011b c0011b) {
            this.f761b = c0011b.f761b;
            this.f763d = c0011b.f763d;
            this.f762c = c0011b.f762c;
            this.e = c0011b.e;
            this.f = c0011b.f;
            this.g = c0011b.g;
            this.h = c0011b.h;
            this.i = c0011b.i;
            this.j = c0011b.j;
            this.k = c0011b.k;
            this.l = c0011b.l;
            this.m = c0011b.m;
            this.n = c0011b.n;
            this.o = c0011b.o;
            this.p = c0011b.p;
            this.q = c0011b.q;
            this.r = c0011b.r;
            this.s = c0011b.s;
            this.t = c0011b.t;
            this.u = c0011b.u;
            this.v = c0011b.v;
            this.w = c0011b.w;
            this.x = c0011b.x;
            this.y = c0011b.y;
            this.z = c0011b.z;
            this.A = c0011b.A;
            this.B = c0011b.B;
            this.C = c0011b.C;
            this.D = c0011b.D;
            this.E = c0011b.E;
            this.F = c0011b.F;
            this.G = c0011b.G;
            this.H = c0011b.H;
            this.I = c0011b.I;
            this.J = c0011b.J;
            this.K = c0011b.K;
            this.L = c0011b.L;
            this.M = c0011b.M;
            this.N = c0011b.N;
            this.O = c0011b.O;
            this.P = c0011b.P;
            this.Q = c0011b.Q;
            this.R = c0011b.R;
            this.S = c0011b.S;
            this.T = c0011b.T;
            this.U = c0011b.U;
            this.V = c0011b.V;
            this.W = c0011b.W;
            this.X = c0011b.X;
            this.Y = c0011b.Y;
            this.Z = c0011b.Z;
            this.a0 = c0011b.a0;
            this.b0 = c0011b.b0;
            this.c0 = c0011b.c0;
            this.d0 = c0011b.d0;
            this.e0 = c0011b.e0;
            this.h0 = c0011b.h0;
            int[] iArr = c0011b.f0;
            if (iArr != null) {
                this.f0 = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.f0 = null;
            }
            this.g0 = c0011b.g0;
            this.i0 = c0011b.i0;
            this.j0 = c0011b.j0;
            this.k0 = c0011b.k0;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.g5);
            this.f762c = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                int i2 = f760a.get(index);
                if (i2 == 80) {
                    this.i0 = typedArrayObtainStyledAttributes.getBoolean(index, this.i0);
                } else if (i2 != 81) {
                    switch (i2) {
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
                            this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f);
                            break;
                        case 18:
                            this.g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.g);
                            break;
                        case 19:
                            this.h = typedArrayObtainStyledAttributes.getFloat(index, this.h);
                            break;
                        case 20:
                            this.v = typedArrayObtainStyledAttributes.getFloat(index, this.v);
                            break;
                        case 21:
                            this.e = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.e);
                            break;
                        case 22:
                            this.f763d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f763d);
                            break;
                        case 23:
                            this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.E);
                            break;
                        case 24:
                            this.i = b.w(typedArrayObtainStyledAttributes, index, this.i);
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
                            switch (i2) {
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
                                    switch (i2) {
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
                                            switch (i2) {
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
                                                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f760a.get(index));
                                                    break;
                                                case 77:
                                                    this.h0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                default:
                                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f760a.get(index));
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
        private static SparseIntArray f764a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f765b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f766c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f767d = null;
        public int e = -1;
        public int f = 0;
        public float g = Float.NaN;
        public float h = Float.NaN;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f764a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.A6, 1);
            f764a.append(androidx.constraintlayout.widget.e.C6, 2);
            f764a.append(androidx.constraintlayout.widget.e.D6, 3);
            f764a.append(androidx.constraintlayout.widget.e.z6, 4);
            f764a.append(androidx.constraintlayout.widget.e.y6, 5);
            f764a.append(androidx.constraintlayout.widget.e.B6, 6);
        }

        public void a(c cVar) {
            this.f765b = cVar.f765b;
            this.f766c = cVar.f766c;
            this.f767d = cVar.f767d;
            this.e = cVar.e;
            this.f = cVar.f;
            this.h = cVar.h;
            this.g = cVar.g;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.x6);
            this.f765b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (f764a.get(index)) {
                    case 1:
                        this.h = typedArrayObtainStyledAttributes.getFloat(index, this.h);
                        break;
                    case 2:
                        this.e = typedArrayObtainStyledAttributes.getInt(index, this.e);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.f767d = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.f767d = b.e.a.a.c.f1569b[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.f766c = b.w(typedArrayObtainStyledAttributes, index, this.f766c);
                        break;
                    case 6:
                        this.g = typedArrayObtainStyledAttributes.getFloat(index, this.g);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f768a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f769b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f770c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f771d = 1.0f;
        public float e = Float.NaN;

        public void a(d dVar) {
            this.f768a = dVar.f768a;
            this.f769b = dVar.f769b;
            this.f771d = dVar.f771d;
            this.e = dVar.e;
            this.f770c = dVar.f770c;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.n7);
            this.f768a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == androidx.constraintlayout.widget.e.p7) {
                    this.f771d = typedArrayObtainStyledAttributes.getFloat(index, this.f771d);
                } else if (index == androidx.constraintlayout.widget.e.o7) {
                    this.f769b = typedArrayObtainStyledAttributes.getInt(index, this.f769b);
                    this.f769b = b.f752a[this.f769b];
                } else if (index == androidx.constraintlayout.widget.e.r7) {
                    this.f770c = typedArrayObtainStyledAttributes.getInt(index, this.f770c);
                } else if (index == androidx.constraintlayout.widget.e.q7) {
                    this.e = typedArrayObtainStyledAttributes.getFloat(index, this.e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f772a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f773b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f774c = 0.0f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f775d = 0.0f;
        public float e = 0.0f;
        public float f = 1.0f;
        public float g = 1.0f;
        public float h = Float.NaN;
        public float i = Float.NaN;
        public float j = 0.0f;
        public float k = 0.0f;
        public float l = 0.0f;
        public boolean m = false;
        public float n = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f772a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.M7, 1);
            f772a.append(androidx.constraintlayout.widget.e.N7, 2);
            f772a.append(androidx.constraintlayout.widget.e.O7, 3);
            f772a.append(androidx.constraintlayout.widget.e.K7, 4);
            f772a.append(androidx.constraintlayout.widget.e.L7, 5);
            f772a.append(androidx.constraintlayout.widget.e.G7, 6);
            f772a.append(androidx.constraintlayout.widget.e.H7, 7);
            f772a.append(androidx.constraintlayout.widget.e.I7, 8);
            f772a.append(androidx.constraintlayout.widget.e.J7, 9);
            f772a.append(androidx.constraintlayout.widget.e.P7, 10);
            f772a.append(androidx.constraintlayout.widget.e.Q7, 11);
        }

        public void a(e eVar) {
            this.f773b = eVar.f773b;
            this.f774c = eVar.f774c;
            this.f775d = eVar.f775d;
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

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.F7);
            this.f773b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (f772a.get(index)) {
                    case 1:
                        this.f774c = typedArrayObtainStyledAttributes.getFloat(index, this.f774c);
                        break;
                    case 2:
                        this.f775d = typedArrayObtainStyledAttributes.getFloat(index, this.f775d);
                        break;
                    case 3:
                        this.e = typedArrayObtainStyledAttributes.getFloat(index, this.e);
                        break;
                    case 4:
                        this.f = typedArrayObtainStyledAttributes.getFloat(index, this.f);
                        break;
                    case 5:
                        this.g = typedArrayObtainStyledAttributes.getFloat(index, this.g);
                        break;
                    case 6:
                        this.h = typedArrayObtainStyledAttributes.getDimension(index, this.h);
                        break;
                    case 7:
                        this.i = typedArrayObtainStyledAttributes.getDimension(index, this.i);
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
        f753b = sparseIntArray;
        sparseIntArray.append(androidx.constraintlayout.widget.e.u0, 25);
        f753b.append(androidx.constraintlayout.widget.e.v0, 26);
        f753b.append(androidx.constraintlayout.widget.e.x0, 29);
        f753b.append(androidx.constraintlayout.widget.e.y0, 30);
        f753b.append(androidx.constraintlayout.widget.e.E0, 36);
        f753b.append(androidx.constraintlayout.widget.e.D0, 35);
        f753b.append(androidx.constraintlayout.widget.e.c0, 4);
        f753b.append(androidx.constraintlayout.widget.e.b0, 3);
        f753b.append(androidx.constraintlayout.widget.e.Z, 1);
        f753b.append(androidx.constraintlayout.widget.e.M0, 6);
        f753b.append(androidx.constraintlayout.widget.e.N0, 7);
        f753b.append(androidx.constraintlayout.widget.e.j0, 17);
        f753b.append(androidx.constraintlayout.widget.e.k0, 18);
        f753b.append(androidx.constraintlayout.widget.e.l0, 19);
        f753b.append(androidx.constraintlayout.widget.e.s, 27);
        f753b.append(androidx.constraintlayout.widget.e.z0, 32);
        f753b.append(androidx.constraintlayout.widget.e.A0, 33);
        f753b.append(androidx.constraintlayout.widget.e.i0, 10);
        f753b.append(androidx.constraintlayout.widget.e.h0, 9);
        f753b.append(androidx.constraintlayout.widget.e.Q0, 13);
        f753b.append(androidx.constraintlayout.widget.e.T0, 16);
        f753b.append(androidx.constraintlayout.widget.e.R0, 14);
        f753b.append(androidx.constraintlayout.widget.e.O0, 11);
        f753b.append(androidx.constraintlayout.widget.e.S0, 15);
        f753b.append(androidx.constraintlayout.widget.e.P0, 12);
        f753b.append(androidx.constraintlayout.widget.e.H0, 40);
        f753b.append(androidx.constraintlayout.widget.e.s0, 39);
        f753b.append(androidx.constraintlayout.widget.e.r0, 41);
        f753b.append(androidx.constraintlayout.widget.e.G0, 42);
        f753b.append(androidx.constraintlayout.widget.e.q0, 20);
        f753b.append(androidx.constraintlayout.widget.e.F0, 37);
        f753b.append(androidx.constraintlayout.widget.e.g0, 5);
        f753b.append(androidx.constraintlayout.widget.e.t0, 82);
        f753b.append(androidx.constraintlayout.widget.e.C0, 82);
        f753b.append(androidx.constraintlayout.widget.e.w0, 82);
        f753b.append(androidx.constraintlayout.widget.e.a0, 82);
        f753b.append(androidx.constraintlayout.widget.e.Y, 82);
        f753b.append(androidx.constraintlayout.widget.e.x, 24);
        f753b.append(androidx.constraintlayout.widget.e.z, 28);
        f753b.append(androidx.constraintlayout.widget.e.L, 31);
        f753b.append(androidx.constraintlayout.widget.e.M, 8);
        f753b.append(androidx.constraintlayout.widget.e.y, 34);
        f753b.append(androidx.constraintlayout.widget.e.A, 2);
        f753b.append(androidx.constraintlayout.widget.e.v, 23);
        f753b.append(androidx.constraintlayout.widget.e.w, 21);
        f753b.append(androidx.constraintlayout.widget.e.u, 22);
        f753b.append(androidx.constraintlayout.widget.e.B, 43);
        f753b.append(androidx.constraintlayout.widget.e.O, 44);
        f753b.append(androidx.constraintlayout.widget.e.J, 45);
        f753b.append(androidx.constraintlayout.widget.e.K, 46);
        f753b.append(androidx.constraintlayout.widget.e.I, 60);
        f753b.append(androidx.constraintlayout.widget.e.G, 47);
        f753b.append(androidx.constraintlayout.widget.e.H, 48);
        f753b.append(androidx.constraintlayout.widget.e.C, 49);
        f753b.append(androidx.constraintlayout.widget.e.D, 50);
        f753b.append(androidx.constraintlayout.widget.e.E, 51);
        f753b.append(androidx.constraintlayout.widget.e.F, 52);
        f753b.append(androidx.constraintlayout.widget.e.N, 53);
        f753b.append(androidx.constraintlayout.widget.e.I0, 54);
        f753b.append(androidx.constraintlayout.widget.e.m0, 55);
        f753b.append(androidx.constraintlayout.widget.e.J0, 56);
        f753b.append(androidx.constraintlayout.widget.e.n0, 57);
        f753b.append(androidx.constraintlayout.widget.e.K0, 58);
        f753b.append(androidx.constraintlayout.widget.e.o0, 59);
        f753b.append(androidx.constraintlayout.widget.e.d0, 61);
        f753b.append(androidx.constraintlayout.widget.e.f0, 62);
        f753b.append(androidx.constraintlayout.widget.e.e0, 63);
        f753b.append(androidx.constraintlayout.widget.e.P, 64);
        f753b.append(androidx.constraintlayout.widget.e.X0, 65);
        f753b.append(androidx.constraintlayout.widget.e.V, 66);
        f753b.append(androidx.constraintlayout.widget.e.Y0, 67);
        f753b.append(androidx.constraintlayout.widget.e.V0, 79);
        f753b.append(androidx.constraintlayout.widget.e.t, 38);
        f753b.append(androidx.constraintlayout.widget.e.U0, 68);
        f753b.append(androidx.constraintlayout.widget.e.L0, 69);
        f753b.append(androidx.constraintlayout.widget.e.p0, 70);
        f753b.append(androidx.constraintlayout.widget.e.T, 71);
        f753b.append(androidx.constraintlayout.widget.e.R, 72);
        f753b.append(androidx.constraintlayout.widget.e.S, 73);
        f753b.append(androidx.constraintlayout.widget.e.U, 74);
        f753b.append(androidx.constraintlayout.widget.e.Q, 75);
        f753b.append(androidx.constraintlayout.widget.e.W0, 76);
        f753b.append(androidx.constraintlayout.widget.e.B0, 77);
        f753b.append(androidx.constraintlayout.widget.e.Z0, 78);
        f753b.append(androidx.constraintlayout.widget.e.X, 80);
        f753b.append(androidx.constraintlayout.widget.e.W, 81);
    }

    private int[] k(View view, String str) {
        int iIntValue;
        Object objE;
        String[] strArrSplit = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
        Context context = view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i = 0;
        int i2 = 0;
        while (i < strArrSplit.length) {
            String strTrim = strArrSplit[i].trim();
            try {
                iIntValue = androidx.constraintlayout.widget.d.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
                iIntValue = 0;
            }
            if (iIntValue == 0) {
                iIntValue = context.getResources().getIdentifier(strTrim, "id", context.getPackageName());
            }
            if (iIntValue == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (objE = ((ConstraintLayout) view.getParent()).e(0, strTrim)) != null && (objE instanceof Integer)) {
                iIntValue = ((Integer) objE).intValue();
            }
            iArr[i2] = iIntValue;
            i++;
            i2++;
        }
        return i2 != strArrSplit.length ? Arrays.copyOf(iArr, i2) : iArr;
    }

    private a l(Context context, AttributeSet attributeSet) {
        a aVar = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.r);
        x(context, aVar, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    private a m(int i) {
        if (!this.f.containsKey(Integer.valueOf(i))) {
            this.f.put(Integer.valueOf(i), new a());
        }
        return this.f.get(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int w(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    private void x(Context context, a aVar, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index != androidx.constraintlayout.widget.e.t && androidx.constraintlayout.widget.e.L != index && androidx.constraintlayout.widget.e.M != index) {
                aVar.f758c.f765b = true;
                aVar.f759d.f762c = true;
                aVar.f757b.f768a = true;
                aVar.e.f773b = true;
            }
            switch (f753b.get(index)) {
                case 1:
                    C0011b c0011b = aVar.f759d;
                    c0011b.q = w(typedArray, index, c0011b.q);
                    break;
                case 2:
                    C0011b c0011b2 = aVar.f759d;
                    c0011b2.H = typedArray.getDimensionPixelSize(index, c0011b2.H);
                    break;
                case 3:
                    C0011b c0011b3 = aVar.f759d;
                    c0011b3.p = w(typedArray, index, c0011b3.p);
                    break;
                case 4:
                    C0011b c0011b4 = aVar.f759d;
                    c0011b4.o = w(typedArray, index, c0011b4.o);
                    break;
                case 5:
                    aVar.f759d.x = typedArray.getString(index);
                    break;
                case 6:
                    C0011b c0011b5 = aVar.f759d;
                    c0011b5.B = typedArray.getDimensionPixelOffset(index, c0011b5.B);
                    break;
                case 7:
                    C0011b c0011b6 = aVar.f759d;
                    c0011b6.C = typedArray.getDimensionPixelOffset(index, c0011b6.C);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0011b c0011b7 = aVar.f759d;
                        c0011b7.I = typedArray.getDimensionPixelSize(index, c0011b7.I);
                    }
                    break;
                case 9:
                    C0011b c0011b8 = aVar.f759d;
                    c0011b8.u = w(typedArray, index, c0011b8.u);
                    break;
                case 10:
                    C0011b c0011b9 = aVar.f759d;
                    c0011b9.t = w(typedArray, index, c0011b9.t);
                    break;
                case 11:
                    C0011b c0011b10 = aVar.f759d;
                    c0011b10.N = typedArray.getDimensionPixelSize(index, c0011b10.N);
                    break;
                case 12:
                    C0011b c0011b11 = aVar.f759d;
                    c0011b11.O = typedArray.getDimensionPixelSize(index, c0011b11.O);
                    break;
                case 13:
                    C0011b c0011b12 = aVar.f759d;
                    c0011b12.K = typedArray.getDimensionPixelSize(index, c0011b12.K);
                    break;
                case 14:
                    C0011b c0011b13 = aVar.f759d;
                    c0011b13.M = typedArray.getDimensionPixelSize(index, c0011b13.M);
                    break;
                case 15:
                    C0011b c0011b14 = aVar.f759d;
                    c0011b14.P = typedArray.getDimensionPixelSize(index, c0011b14.P);
                    break;
                case 16:
                    C0011b c0011b15 = aVar.f759d;
                    c0011b15.L = typedArray.getDimensionPixelSize(index, c0011b15.L);
                    break;
                case 17:
                    C0011b c0011b16 = aVar.f759d;
                    c0011b16.f = typedArray.getDimensionPixelOffset(index, c0011b16.f);
                    break;
                case 18:
                    C0011b c0011b17 = aVar.f759d;
                    c0011b17.g = typedArray.getDimensionPixelOffset(index, c0011b17.g);
                    break;
                case 19:
                    C0011b c0011b18 = aVar.f759d;
                    c0011b18.h = typedArray.getFloat(index, c0011b18.h);
                    break;
                case 20:
                    C0011b c0011b19 = aVar.f759d;
                    c0011b19.v = typedArray.getFloat(index, c0011b19.v);
                    break;
                case 21:
                    C0011b c0011b20 = aVar.f759d;
                    c0011b20.e = typedArray.getLayoutDimension(index, c0011b20.e);
                    break;
                case 22:
                    d dVar = aVar.f757b;
                    dVar.f769b = typedArray.getInt(index, dVar.f769b);
                    d dVar2 = aVar.f757b;
                    dVar2.f769b = f752a[dVar2.f769b];
                    break;
                case 23:
                    C0011b c0011b21 = aVar.f759d;
                    c0011b21.f763d = typedArray.getLayoutDimension(index, c0011b21.f763d);
                    break;
                case 24:
                    C0011b c0011b22 = aVar.f759d;
                    c0011b22.E = typedArray.getDimensionPixelSize(index, c0011b22.E);
                    break;
                case 25:
                    C0011b c0011b23 = aVar.f759d;
                    c0011b23.i = w(typedArray, index, c0011b23.i);
                    break;
                case 26:
                    C0011b c0011b24 = aVar.f759d;
                    c0011b24.j = w(typedArray, index, c0011b24.j);
                    break;
                case 27:
                    C0011b c0011b25 = aVar.f759d;
                    c0011b25.D = typedArray.getInt(index, c0011b25.D);
                    break;
                case 28:
                    C0011b c0011b26 = aVar.f759d;
                    c0011b26.F = typedArray.getDimensionPixelSize(index, c0011b26.F);
                    break;
                case 29:
                    C0011b c0011b27 = aVar.f759d;
                    c0011b27.k = w(typedArray, index, c0011b27.k);
                    break;
                case 30:
                    C0011b c0011b28 = aVar.f759d;
                    c0011b28.l = w(typedArray, index, c0011b28.l);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0011b c0011b29 = aVar.f759d;
                        c0011b29.J = typedArray.getDimensionPixelSize(index, c0011b29.J);
                    }
                    break;
                case 32:
                    C0011b c0011b30 = aVar.f759d;
                    c0011b30.r = w(typedArray, index, c0011b30.r);
                    break;
                case 33:
                    C0011b c0011b31 = aVar.f759d;
                    c0011b31.s = w(typedArray, index, c0011b31.s);
                    break;
                case 34:
                    C0011b c0011b32 = aVar.f759d;
                    c0011b32.G = typedArray.getDimensionPixelSize(index, c0011b32.G);
                    break;
                case 35:
                    C0011b c0011b33 = aVar.f759d;
                    c0011b33.n = w(typedArray, index, c0011b33.n);
                    break;
                case 36:
                    C0011b c0011b34 = aVar.f759d;
                    c0011b34.m = w(typedArray, index, c0011b34.m);
                    break;
                case 37:
                    C0011b c0011b35 = aVar.f759d;
                    c0011b35.w = typedArray.getFloat(index, c0011b35.w);
                    break;
                case 38:
                    aVar.f756a = typedArray.getResourceId(index, aVar.f756a);
                    break;
                case 39:
                    C0011b c0011b36 = aVar.f759d;
                    c0011b36.R = typedArray.getFloat(index, c0011b36.R);
                    break;
                case 40:
                    C0011b c0011b37 = aVar.f759d;
                    c0011b37.Q = typedArray.getFloat(index, c0011b37.Q);
                    break;
                case 41:
                    C0011b c0011b38 = aVar.f759d;
                    c0011b38.S = typedArray.getInt(index, c0011b38.S);
                    break;
                case 42:
                    C0011b c0011b39 = aVar.f759d;
                    c0011b39.T = typedArray.getInt(index, c0011b39.T);
                    break;
                case 43:
                    d dVar3 = aVar.f757b;
                    dVar3.f771d = typedArray.getFloat(index, dVar3.f771d);
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar = aVar.e;
                        eVar.m = true;
                        eVar.n = typedArray.getDimension(index, eVar.n);
                    }
                    break;
                case 45:
                    e eVar2 = aVar.e;
                    eVar2.f775d = typedArray.getFloat(index, eVar2.f775d);
                    break;
                case 46:
                    e eVar3 = aVar.e;
                    eVar3.e = typedArray.getFloat(index, eVar3.e);
                    break;
                case 47:
                    e eVar4 = aVar.e;
                    eVar4.f = typedArray.getFloat(index, eVar4.f);
                    break;
                case 48:
                    e eVar5 = aVar.e;
                    eVar5.g = typedArray.getFloat(index, eVar5.g);
                    break;
                case 49:
                    e eVar6 = aVar.e;
                    eVar6.h = typedArray.getDimension(index, eVar6.h);
                    break;
                case 50:
                    e eVar7 = aVar.e;
                    eVar7.i = typedArray.getDimension(index, eVar7.i);
                    break;
                case 51:
                    e eVar8 = aVar.e;
                    eVar8.j = typedArray.getDimension(index, eVar8.j);
                    break;
                case 52:
                    e eVar9 = aVar.e;
                    eVar9.k = typedArray.getDimension(index, eVar9.k);
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar10 = aVar.e;
                        eVar10.l = typedArray.getDimension(index, eVar10.l);
                    }
                    break;
                case 54:
                    C0011b c0011b40 = aVar.f759d;
                    c0011b40.U = typedArray.getInt(index, c0011b40.U);
                    break;
                case 55:
                    C0011b c0011b41 = aVar.f759d;
                    c0011b41.V = typedArray.getInt(index, c0011b41.V);
                    break;
                case 56:
                    C0011b c0011b42 = aVar.f759d;
                    c0011b42.W = typedArray.getDimensionPixelSize(index, c0011b42.W);
                    break;
                case 57:
                    C0011b c0011b43 = aVar.f759d;
                    c0011b43.X = typedArray.getDimensionPixelSize(index, c0011b43.X);
                    break;
                case 58:
                    C0011b c0011b44 = aVar.f759d;
                    c0011b44.Y = typedArray.getDimensionPixelSize(index, c0011b44.Y);
                    break;
                case 59:
                    C0011b c0011b45 = aVar.f759d;
                    c0011b45.Z = typedArray.getDimensionPixelSize(index, c0011b45.Z);
                    break;
                case 60:
                    e eVar11 = aVar.e;
                    eVar11.f774c = typedArray.getFloat(index, eVar11.f774c);
                    break;
                case 61:
                    C0011b c0011b46 = aVar.f759d;
                    c0011b46.y = w(typedArray, index, c0011b46.y);
                    break;
                case 62:
                    C0011b c0011b47 = aVar.f759d;
                    c0011b47.z = typedArray.getDimensionPixelSize(index, c0011b47.z);
                    break;
                case 63:
                    C0011b c0011b48 = aVar.f759d;
                    c0011b48.A = typedArray.getFloat(index, c0011b48.A);
                    break;
                case 64:
                    c cVar = aVar.f758c;
                    cVar.f766c = w(typedArray, index, cVar.f766c);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.f758c.f767d = typedArray.getString(index);
                    } else {
                        aVar.f758c.f767d = b.e.a.a.c.f1569b[typedArray.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    aVar.f758c.f = typedArray.getInt(index, 0);
                    break;
                case 67:
                    c cVar2 = aVar.f758c;
                    cVar2.h = typedArray.getFloat(index, cVar2.h);
                    break;
                case 68:
                    d dVar4 = aVar.f757b;
                    dVar4.e = typedArray.getFloat(index, dVar4.e);
                    break;
                case 69:
                    aVar.f759d.a0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    aVar.f759d.b0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    C0011b c0011b49 = aVar.f759d;
                    c0011b49.c0 = typedArray.getInt(index, c0011b49.c0);
                    break;
                case 73:
                    C0011b c0011b50 = aVar.f759d;
                    c0011b50.d0 = typedArray.getDimensionPixelSize(index, c0011b50.d0);
                    break;
                case 74:
                    aVar.f759d.g0 = typedArray.getString(index);
                    break;
                case 75:
                    C0011b c0011b51 = aVar.f759d;
                    c0011b51.k0 = typedArray.getBoolean(index, c0011b51.k0);
                    break;
                case 76:
                    c cVar3 = aVar.f758c;
                    cVar3.e = typedArray.getInt(index, cVar3.e);
                    break;
                case 77:
                    aVar.f759d.h0 = typedArray.getString(index);
                    break;
                case 78:
                    d dVar5 = aVar.f757b;
                    dVar5.f770c = typedArray.getInt(index, dVar5.f770c);
                    break;
                case 79:
                    c cVar4 = aVar.f758c;
                    cVar4.g = typedArray.getFloat(index, cVar4.g);
                    break;
                case 80:
                    C0011b c0011b52 = aVar.f759d;
                    c0011b52.i0 = typedArray.getBoolean(index, c0011b52.i0);
                    break;
                case 81:
                    C0011b c0011b53 = aVar.f759d;
                    c0011b53.j0 = typedArray.getBoolean(index, c0011b53.j0);
                    break;
                case 82:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f753b.get(index));
                    break;
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f753b.get(index));
                    break;
            }
        }
    }

    public void A(boolean z) {
        this.e = z;
    }

    public void B(boolean z) {
        this.f754c = z;
    }

    public void c(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.f.containsKey(Integer.valueOf(id))) {
                Log.v("ConstraintSet", "id unknown " + androidx.constraintlayout.motion.widget.a.c(childAt));
            } else {
                if (this.e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (this.f.containsKey(Integer.valueOf(id))) {
                    ConstraintAttribute.h(childAt, this.f.get(Integer.valueOf(id)).f);
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
        if (this.f.containsKey(Integer.valueOf(id))) {
            a aVar = this.f.get(Integer.valueOf(id));
            if (constraintWidget instanceof h) {
                constraintHelper.n(aVar, (h) constraintWidget, bVar, sparseArray);
            }
        }
    }

    void f(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.f.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.f.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + androidx.constraintlayout.motion.widget.a.c(childAt));
            } else {
                if (this.e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.f.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = this.f.get(Integer.valueOf(id));
                        if (childAt instanceof Barrier) {
                            aVar.f759d.e0 = 1;
                        }
                        int i2 = aVar.f759d.e0;
                        if (i2 != -1 && i2 == 1) {
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(aVar.f759d.c0);
                            barrier.setMargin(aVar.f759d.d0);
                            barrier.setAllowsGoneWidget(aVar.f759d.k0);
                            C0011b c0011b = aVar.f759d;
                            int[] iArr = c0011b.f0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = c0011b.g0;
                                if (str != null) {
                                    c0011b.f0 = k(barrier, str);
                                    barrier.setReferencedIds(aVar.f759d.f0);
                                }
                            }
                        }
                        ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
                        bVar.c();
                        aVar.d(bVar);
                        if (z) {
                            ConstraintAttribute.h(childAt, aVar.f);
                        }
                        childAt.setLayoutParams(bVar);
                        d dVar = aVar.f757b;
                        if (dVar.f770c == 0) {
                            childAt.setVisibility(dVar.f769b);
                        }
                        int i3 = Build.VERSION.SDK_INT;
                        if (i3 >= 17) {
                            childAt.setAlpha(aVar.f757b.f771d);
                            childAt.setRotation(aVar.e.f774c);
                            childAt.setRotationX(aVar.e.f775d);
                            childAt.setRotationY(aVar.e.e);
                            childAt.setScaleX(aVar.e.f);
                            childAt.setScaleY(aVar.e.g);
                            if (!Float.isNaN(aVar.e.h)) {
                                childAt.setPivotX(aVar.e.h);
                            }
                            if (!Float.isNaN(aVar.e.i)) {
                                childAt.setPivotY(aVar.e.i);
                            }
                            childAt.setTranslationX(aVar.e.j);
                            childAt.setTranslationY(aVar.e.k);
                            if (i3 >= 21) {
                                childAt.setTranslationZ(aVar.e.l);
                                e eVar = aVar.e;
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
            a aVar2 = this.f.get(num);
            int i4 = aVar2.f759d.e0;
            if (i4 != -1 && i4 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                C0011b c0011b2 = aVar2.f759d;
                int[] iArr2 = c0011b2.f0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = c0011b2.g0;
                    if (str2 != null) {
                        c0011b2.f0 = k(barrier2, str2);
                        barrier2.setReferencedIds(aVar2.f759d.f0);
                    }
                }
                barrier2.setType(aVar2.f759d.c0);
                barrier2.setMargin(aVar2.f759d.d0);
                ConstraintLayout.b bVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.u();
                aVar2.d(bVarGenerateDefaultLayoutParams);
                constraintLayout.addView(barrier2, bVarGenerateDefaultLayoutParams);
            }
            if (aVar2.f759d.f761b) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.b bVarGenerateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                aVar2.d(bVarGenerateDefaultLayoutParams2);
                constraintLayout.addView(guideline, bVarGenerateDefaultLayoutParams2);
            }
        }
    }

    public void g(int i, ConstraintLayout.b bVar) {
        if (this.f.containsKey(Integer.valueOf(i))) {
            this.f.get(Integer.valueOf(i)).d(bVar);
        }
    }

    public void h(Context context, int i) {
        i((ConstraintLayout) LayoutInflater.from(context).inflate(i, (ViewGroup) null));
    }

    public void i(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.f.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f.containsKey(Integer.valueOf(id))) {
                this.f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f.get(Integer.valueOf(id));
            aVar.f = ConstraintAttribute.b(this.f755d, childAt);
            aVar.f(id, bVar);
            aVar.f757b.f769b = childAt.getVisibility();
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 17) {
                aVar.f757b.f771d = childAt.getAlpha();
                aVar.e.f774c = childAt.getRotation();
                aVar.e.f775d = childAt.getRotationX();
                aVar.e.e = childAt.getRotationY();
                aVar.e.f = childAt.getScaleX();
                aVar.e.g = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    e eVar = aVar.e;
                    eVar.h = pivotX;
                    eVar.i = pivotY;
                }
                aVar.e.j = childAt.getTranslationX();
                aVar.e.k = childAt.getTranslationY();
                if (i2 >= 21) {
                    aVar.e.l = childAt.getTranslationZ();
                    e eVar2 = aVar.e;
                    if (eVar2.m) {
                        eVar2.n = childAt.getElevation();
                    }
                }
            }
            if (childAt instanceof Barrier) {
                Barrier barrier = (Barrier) childAt;
                aVar.f759d.k0 = barrier.v();
                aVar.f759d.f0 = barrier.getReferencedIds();
                aVar.f759d.c0 = barrier.getType();
                aVar.f759d.d0 = barrier.getMargin();
            }
        }
    }

    public void j(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.f.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraints.getChildAt(i);
            Constraints.a aVar = (Constraints.a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f.containsKey(Integer.valueOf(id))) {
                this.f.put(Integer.valueOf(id), new a());
            }
            a aVar2 = this.f.get(Integer.valueOf(id));
            if (childAt instanceof ConstraintHelper) {
                aVar2.h((ConstraintHelper) childAt, id, aVar);
            }
            aVar2.g(id, aVar);
        }
    }

    public a n(int i) {
        if (this.f.containsKey(Integer.valueOf(i))) {
            return this.f.get(Integer.valueOf(i));
        }
        return null;
    }

    public int o(int i) {
        return m(i).f759d.e;
    }

    public int[] p() {
        Integer[] numArr = (Integer[]) this.f.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = numArr[i].intValue();
        }
        return iArr;
    }

    public a q(int i) {
        return m(i);
    }

    public int r(int i) {
        return m(i).f757b.f769b;
    }

    public int s(int i) {
        return m(i).f757b.f770c;
    }

    public int t(int i) {
        return m(i).f759d.f763d;
    }

    public void u(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    a aVarL = l(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        aVarL.f759d.f761b = true;
                    }
                    this.f.put(Integer.valueOf(aVarL.f756a), aVarL);
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.b.v(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public void y(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f.containsKey(Integer.valueOf(id))) {
                this.f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f.get(Integer.valueOf(id));
            if (!aVar.f759d.f762c) {
                aVar.f(id, bVar);
                if (childAt instanceof ConstraintHelper) {
                    aVar.f759d.f0 = ((ConstraintHelper) childAt).getReferencedIds();
                    if (childAt instanceof Barrier) {
                        Barrier barrier = (Barrier) childAt;
                        aVar.f759d.k0 = barrier.v();
                        aVar.f759d.c0 = barrier.getType();
                        aVar.f759d.d0 = barrier.getMargin();
                    }
                }
                aVar.f759d.f762c = true;
            }
            d dVar = aVar.f757b;
            if (!dVar.f768a) {
                dVar.f769b = childAt.getVisibility();
                aVar.f757b.f771d = childAt.getAlpha();
                aVar.f757b.f768a = true;
            }
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 17) {
                e eVar = aVar.e;
                if (!eVar.f773b) {
                    eVar.f773b = true;
                    eVar.f774c = childAt.getRotation();
                    aVar.e.f775d = childAt.getRotationX();
                    aVar.e.e = childAt.getRotationY();
                    aVar.e.f = childAt.getScaleX();
                    aVar.e.g = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        e eVar2 = aVar.e;
                        eVar2.h = pivotX;
                        eVar2.i = pivotY;
                    }
                    aVar.e.j = childAt.getTranslationX();
                    aVar.e.k = childAt.getTranslationY();
                    if (i2 >= 21) {
                        aVar.e.l = childAt.getTranslationZ();
                        e eVar3 = aVar.e;
                        if (eVar3.m) {
                            eVar3.n = childAt.getElevation();
                        }
                    }
                }
            }
        }
    }

    public void z(b bVar) {
        for (Integer num : bVar.f.keySet()) {
            int iIntValue = num.intValue();
            a aVar = bVar.f.get(num);
            if (!this.f.containsKey(Integer.valueOf(iIntValue))) {
                this.f.put(Integer.valueOf(iIntValue), new a());
            }
            a aVar2 = this.f.get(Integer.valueOf(iIntValue));
            C0011b c0011b = aVar2.f759d;
            if (!c0011b.f762c) {
                c0011b.a(aVar.f759d);
            }
            d dVar = aVar2.f757b;
            if (!dVar.f768a) {
                dVar.a(aVar.f757b);
            }
            e eVar = aVar2.e;
            if (!eVar.f773b) {
                eVar.a(aVar.e);
            }
            c cVar = aVar2.f758c;
            if (!cVar.f765b) {
                cVar.a(aVar.f758c);
            }
            for (String str : aVar.f.keySet()) {
                if (!aVar2.f.containsKey(str)) {
                    aVar2.f.put(str, aVar.f.get(str));
                }
            }
        }
    }
}
