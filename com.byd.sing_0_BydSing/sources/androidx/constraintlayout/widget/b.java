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
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintSet.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f1271a = {0, 4, 8};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static SparseIntArray f1272b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1273c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HashMap<String, ConstraintAttribute> f1274d = new HashMap<>();
    private boolean e = true;
    private HashMap<Integer, a> f = new HashMap<>();

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1275a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f1276b = new d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c f1277c = new c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final C0039b f1278d = new C0039b();
        public final e e = new e();
        public HashMap<String, ConstraintAttribute> f = new HashMap<>();

        /* JADX INFO: Access modifiers changed from: private */
        public void f(int i, ConstraintLayout.b bVar) {
            this.f1275a = i;
            C0039b c0039b = this.f1278d;
            c0039b.i = bVar.f1249d;
            c0039b.j = bVar.e;
            c0039b.k = bVar.f;
            c0039b.l = bVar.g;
            c0039b.m = bVar.h;
            c0039b.n = bVar.i;
            c0039b.o = bVar.j;
            c0039b.p = bVar.k;
            c0039b.q = bVar.l;
            c0039b.r = bVar.p;
            c0039b.s = bVar.q;
            c0039b.t = bVar.r;
            c0039b.u = bVar.s;
            c0039b.v = bVar.z;
            c0039b.w = bVar.A;
            c0039b.x = bVar.B;
            c0039b.y = bVar.m;
            c0039b.z = bVar.n;
            c0039b.A = bVar.o;
            c0039b.B = bVar.Q;
            c0039b.C = bVar.R;
            c0039b.D = bVar.S;
            c0039b.h = bVar.f1248c;
            c0039b.f = bVar.f1246a;
            c0039b.g = bVar.f1247b;
            C0039b c0039b2 = this.f1278d;
            c0039b2.f1282d = ((ViewGroup.MarginLayoutParams) bVar).width;
            c0039b2.e = ((ViewGroup.MarginLayoutParams) bVar).height;
            c0039b2.E = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            c0039b2.F = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
            c0039b2.G = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            c0039b2.H = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
            c0039b2.Q = bVar.F;
            c0039b2.R = bVar.E;
            c0039b2.T = bVar.H;
            c0039b2.S = bVar.G;
            c0039b2.i0 = bVar.T;
            c0039b2.j0 = bVar.U;
            c0039b2.U = bVar.I;
            c0039b2.V = bVar.J;
            c0039b2.W = bVar.M;
            c0039b2.X = bVar.N;
            c0039b2.Y = bVar.K;
            c0039b2.Z = bVar.L;
            c0039b2.a0 = bVar.O;
            c0039b2.b0 = bVar.P;
            c0039b2.h0 = bVar.V;
            c0039b2.L = bVar.u;
            c0039b2.N = bVar.w;
            c0039b2.K = bVar.t;
            c0039b2.M = bVar.v;
            C0039b c0039b3 = this.f1278d;
            c0039b3.P = bVar.x;
            c0039b3.O = bVar.y;
            if (Build.VERSION.SDK_INT >= 17) {
                c0039b3.I = bVar.getMarginEnd();
                this.f1278d.J = bVar.getMarginStart();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g(int i, Constraints.a aVar) {
            f(i, aVar);
            this.f1276b.f1290d = aVar.p0;
            e eVar = this.e;
            eVar.f1293c = aVar.s0;
            eVar.f1294d = aVar.t0;
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
                C0039b c0039b = this.f1278d;
                c0039b.e0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                c0039b.c0 = barrier.getType();
                this.f1278d.f0 = barrier.getReferencedIds();
                this.f1278d.d0 = barrier.getMargin();
            }
        }

        public void d(ConstraintLayout.b bVar) {
            C0039b c0039b = this.f1278d;
            bVar.f1249d = c0039b.i;
            bVar.e = c0039b.j;
            bVar.f = c0039b.k;
            bVar.g = c0039b.l;
            bVar.h = c0039b.m;
            bVar.i = c0039b.n;
            bVar.j = c0039b.o;
            bVar.k = c0039b.p;
            bVar.l = c0039b.q;
            bVar.p = c0039b.r;
            bVar.q = c0039b.s;
            bVar.r = c0039b.t;
            bVar.s = c0039b.u;
            ((ViewGroup.MarginLayoutParams) bVar).leftMargin = c0039b.E;
            ((ViewGroup.MarginLayoutParams) bVar).rightMargin = c0039b.F;
            ((ViewGroup.MarginLayoutParams) bVar).topMargin = c0039b.G;
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = c0039b.H;
            bVar.x = c0039b.P;
            bVar.y = c0039b.O;
            bVar.u = c0039b.L;
            bVar.w = c0039b.N;
            bVar.z = c0039b.v;
            bVar.A = c0039b.w;
            bVar.m = c0039b.y;
            bVar.n = c0039b.z;
            C0039b c0039b2 = this.f1278d;
            bVar.o = c0039b2.A;
            bVar.B = c0039b2.x;
            bVar.Q = c0039b2.B;
            bVar.R = c0039b2.C;
            bVar.F = c0039b2.Q;
            bVar.E = c0039b2.R;
            bVar.H = c0039b2.T;
            bVar.G = c0039b2.S;
            bVar.T = c0039b2.i0;
            bVar.U = c0039b2.j0;
            bVar.I = c0039b2.U;
            bVar.J = c0039b2.V;
            bVar.M = c0039b2.W;
            bVar.N = c0039b2.X;
            bVar.K = c0039b2.Y;
            bVar.L = c0039b2.Z;
            bVar.O = c0039b2.a0;
            bVar.P = c0039b2.b0;
            bVar.S = c0039b2.D;
            bVar.f1248c = c0039b2.h;
            bVar.f1246a = c0039b2.f;
            bVar.f1247b = c0039b2.g;
            ((ViewGroup.MarginLayoutParams) bVar).width = c0039b2.f1282d;
            ((ViewGroup.MarginLayoutParams) bVar).height = c0039b2.e;
            String str = c0039b2.h0;
            if (str != null) {
                bVar.V = str;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                bVar.setMarginStart(this.f1278d.J);
                bVar.setMarginEnd(this.f1278d.I);
            }
            bVar.c();
        }

        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.f1278d.a(this.f1278d);
            aVar.f1277c.a(this.f1277c);
            aVar.f1276b.a(this.f1276b);
            aVar.e.a(this.e);
            aVar.f1275a = this.f1275a;
            return aVar;
        }
    }

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class C0039b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1279a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1282d;
        public int e;
        public int[] f0;
        public String g0;
        public String h0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1280b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1281c = false;
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
            f1279a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.O5, 24);
            f1279a.append(androidx.constraintlayout.widget.e.P5, 25);
            f1279a.append(androidx.constraintlayout.widget.e.R5, 28);
            f1279a.append(androidx.constraintlayout.widget.e.S5, 29);
            f1279a.append(androidx.constraintlayout.widget.e.X5, 35);
            f1279a.append(androidx.constraintlayout.widget.e.W5, 34);
            f1279a.append(androidx.constraintlayout.widget.e.z5, 4);
            f1279a.append(androidx.constraintlayout.widget.e.y5, 3);
            f1279a.append(androidx.constraintlayout.widget.e.w5, 1);
            f1279a.append(androidx.constraintlayout.widget.e.c6, 6);
            f1279a.append(androidx.constraintlayout.widget.e.d6, 7);
            f1279a.append(androidx.constraintlayout.widget.e.G5, 17);
            f1279a.append(androidx.constraintlayout.widget.e.H5, 18);
            f1279a.append(androidx.constraintlayout.widget.e.I5, 19);
            f1279a.append(androidx.constraintlayout.widget.e.h5, 26);
            f1279a.append(androidx.constraintlayout.widget.e.T5, 31);
            f1279a.append(androidx.constraintlayout.widget.e.U5, 32);
            f1279a.append(androidx.constraintlayout.widget.e.F5, 10);
            f1279a.append(androidx.constraintlayout.widget.e.E5, 9);
            f1279a.append(androidx.constraintlayout.widget.e.g6, 13);
            f1279a.append(androidx.constraintlayout.widget.e.j6, 16);
            f1279a.append(androidx.constraintlayout.widget.e.h6, 14);
            f1279a.append(androidx.constraintlayout.widget.e.e6, 11);
            f1279a.append(androidx.constraintlayout.widget.e.i6, 15);
            f1279a.append(androidx.constraintlayout.widget.e.f6, 12);
            f1279a.append(androidx.constraintlayout.widget.e.a6, 38);
            f1279a.append(androidx.constraintlayout.widget.e.M5, 37);
            f1279a.append(androidx.constraintlayout.widget.e.L5, 39);
            f1279a.append(androidx.constraintlayout.widget.e.Z5, 40);
            f1279a.append(androidx.constraintlayout.widget.e.K5, 20);
            f1279a.append(androidx.constraintlayout.widget.e.Y5, 36);
            f1279a.append(androidx.constraintlayout.widget.e.D5, 5);
            f1279a.append(androidx.constraintlayout.widget.e.N5, 76);
            f1279a.append(androidx.constraintlayout.widget.e.V5, 76);
            f1279a.append(androidx.constraintlayout.widget.e.Q5, 76);
            f1279a.append(androidx.constraintlayout.widget.e.x5, 76);
            f1279a.append(androidx.constraintlayout.widget.e.v5, 76);
            f1279a.append(androidx.constraintlayout.widget.e.k5, 23);
            f1279a.append(androidx.constraintlayout.widget.e.m5, 27);
            f1279a.append(androidx.constraintlayout.widget.e.o5, 30);
            f1279a.append(androidx.constraintlayout.widget.e.p5, 8);
            f1279a.append(androidx.constraintlayout.widget.e.l5, 33);
            f1279a.append(androidx.constraintlayout.widget.e.n5, 2);
            f1279a.append(androidx.constraintlayout.widget.e.i5, 22);
            f1279a.append(androidx.constraintlayout.widget.e.j5, 21);
            f1279a.append(androidx.constraintlayout.widget.e.A5, 61);
            f1279a.append(androidx.constraintlayout.widget.e.C5, 62);
            f1279a.append(androidx.constraintlayout.widget.e.B5, 63);
            f1279a.append(androidx.constraintlayout.widget.e.b6, 69);
            f1279a.append(androidx.constraintlayout.widget.e.J5, 70);
            f1279a.append(androidx.constraintlayout.widget.e.t5, 71);
            f1279a.append(androidx.constraintlayout.widget.e.r5, 72);
            f1279a.append(androidx.constraintlayout.widget.e.s5, 73);
            f1279a.append(androidx.constraintlayout.widget.e.u5, 74);
            f1279a.append(androidx.constraintlayout.widget.e.q5, 75);
        }

        public void a(C0039b c0039b) {
            this.f1280b = c0039b.f1280b;
            this.f1282d = c0039b.f1282d;
            this.f1281c = c0039b.f1281c;
            this.e = c0039b.e;
            this.f = c0039b.f;
            this.g = c0039b.g;
            this.h = c0039b.h;
            this.i = c0039b.i;
            this.j = c0039b.j;
            this.k = c0039b.k;
            this.l = c0039b.l;
            this.m = c0039b.m;
            this.n = c0039b.n;
            this.o = c0039b.o;
            this.p = c0039b.p;
            this.q = c0039b.q;
            this.r = c0039b.r;
            this.s = c0039b.s;
            this.t = c0039b.t;
            this.u = c0039b.u;
            this.v = c0039b.v;
            this.w = c0039b.w;
            this.x = c0039b.x;
            this.y = c0039b.y;
            this.z = c0039b.z;
            this.A = c0039b.A;
            this.B = c0039b.B;
            this.C = c0039b.C;
            this.D = c0039b.D;
            this.E = c0039b.E;
            this.F = c0039b.F;
            this.G = c0039b.G;
            this.H = c0039b.H;
            this.I = c0039b.I;
            this.J = c0039b.J;
            this.K = c0039b.K;
            this.L = c0039b.L;
            this.M = c0039b.M;
            this.N = c0039b.N;
            this.O = c0039b.O;
            this.P = c0039b.P;
            this.Q = c0039b.Q;
            this.R = c0039b.R;
            this.S = c0039b.S;
            this.T = c0039b.T;
            this.U = c0039b.U;
            this.V = c0039b.V;
            this.W = c0039b.W;
            this.X = c0039b.X;
            this.Y = c0039b.Y;
            this.Z = c0039b.Z;
            this.a0 = c0039b.a0;
            this.b0 = c0039b.b0;
            this.c0 = c0039b.c0;
            this.d0 = c0039b.d0;
            this.e0 = c0039b.e0;
            this.h0 = c0039b.h0;
            int[] iArr = c0039b.f0;
            if (iArr != null) {
                this.f0 = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.f0 = null;
            }
            this.g0 = c0039b.g0;
            this.i0 = c0039b.i0;
            this.j0 = c0039b.j0;
            this.k0 = c0039b.k0;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.g5);
            this.f1281c = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                int i2 = f1279a.get(index);
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
                            this.f1282d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f1282d);
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
                                                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1279a.get(index));
                                                    break;
                                                case 77:
                                                    this.h0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                default:
                                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1279a.get(index));
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
        private static SparseIntArray f1283a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1284b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1285c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f1286d = null;
        public int e = -1;
        public int f = 0;
        public float g = Float.NaN;
        public float h = Float.NaN;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1283a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.A6, 1);
            f1283a.append(androidx.constraintlayout.widget.e.C6, 2);
            f1283a.append(androidx.constraintlayout.widget.e.D6, 3);
            f1283a.append(androidx.constraintlayout.widget.e.z6, 4);
            f1283a.append(androidx.constraintlayout.widget.e.y6, 5);
            f1283a.append(androidx.constraintlayout.widget.e.B6, 6);
        }

        public void a(c cVar) {
            this.f1284b = cVar.f1284b;
            this.f1285c = cVar.f1285c;
            this.f1286d = cVar.f1286d;
            this.e = cVar.e;
            this.f = cVar.f;
            this.h = cVar.h;
            this.g = cVar.g;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.x6);
            this.f1284b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (f1283a.get(index)) {
                    case 1:
                        this.h = typedArrayObtainStyledAttributes.getFloat(index, this.h);
                        break;
                    case 2:
                        this.e = typedArrayObtainStyledAttributes.getInt(index, this.e);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.f1286d = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.f1286d = c.e.a.a.c.f2136b[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.f1285c = b.w(typedArrayObtainStyledAttributes, index, this.f1285c);
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
        public boolean f1287a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1288b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1289c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1290d = 1.0f;
        public float e = Float.NaN;

        public void a(d dVar) {
            this.f1287a = dVar.f1287a;
            this.f1288b = dVar.f1288b;
            this.f1290d = dVar.f1290d;
            this.e = dVar.e;
            this.f1289c = dVar.f1289c;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.n7);
            this.f1287a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == androidx.constraintlayout.widget.e.p7) {
                    this.f1290d = typedArrayObtainStyledAttributes.getFloat(index, this.f1290d);
                } else if (index == androidx.constraintlayout.widget.e.o7) {
                    this.f1288b = typedArrayObtainStyledAttributes.getInt(index, this.f1288b);
                    this.f1288b = b.f1271a[this.f1288b];
                } else if (index == androidx.constraintlayout.widget.e.r7) {
                    this.f1289c = typedArrayObtainStyledAttributes.getInt(index, this.f1289c);
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
        private static SparseIntArray f1291a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1292b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1293c = 0.0f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1294d = 0.0f;
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
            f1291a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.M7, 1);
            f1291a.append(androidx.constraintlayout.widget.e.N7, 2);
            f1291a.append(androidx.constraintlayout.widget.e.O7, 3);
            f1291a.append(androidx.constraintlayout.widget.e.K7, 4);
            f1291a.append(androidx.constraintlayout.widget.e.L7, 5);
            f1291a.append(androidx.constraintlayout.widget.e.G7, 6);
            f1291a.append(androidx.constraintlayout.widget.e.H7, 7);
            f1291a.append(androidx.constraintlayout.widget.e.I7, 8);
            f1291a.append(androidx.constraintlayout.widget.e.J7, 9);
            f1291a.append(androidx.constraintlayout.widget.e.P7, 10);
            f1291a.append(androidx.constraintlayout.widget.e.Q7, 11);
        }

        public void a(e eVar) {
            this.f1292b = eVar.f1292b;
            this.f1293c = eVar.f1293c;
            this.f1294d = eVar.f1294d;
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
            this.f1292b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (f1291a.get(index)) {
                    case 1:
                        this.f1293c = typedArrayObtainStyledAttributes.getFloat(index, this.f1293c);
                        break;
                    case 2:
                        this.f1294d = typedArrayObtainStyledAttributes.getFloat(index, this.f1294d);
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
        f1272b = sparseIntArray;
        sparseIntArray.append(androidx.constraintlayout.widget.e.u0, 25);
        f1272b.append(androidx.constraintlayout.widget.e.v0, 26);
        f1272b.append(androidx.constraintlayout.widget.e.x0, 29);
        f1272b.append(androidx.constraintlayout.widget.e.y0, 30);
        f1272b.append(androidx.constraintlayout.widget.e.E0, 36);
        f1272b.append(androidx.constraintlayout.widget.e.D0, 35);
        f1272b.append(androidx.constraintlayout.widget.e.c0, 4);
        f1272b.append(androidx.constraintlayout.widget.e.b0, 3);
        f1272b.append(androidx.constraintlayout.widget.e.Z, 1);
        f1272b.append(androidx.constraintlayout.widget.e.M0, 6);
        f1272b.append(androidx.constraintlayout.widget.e.N0, 7);
        f1272b.append(androidx.constraintlayout.widget.e.j0, 17);
        f1272b.append(androidx.constraintlayout.widget.e.k0, 18);
        f1272b.append(androidx.constraintlayout.widget.e.l0, 19);
        f1272b.append(androidx.constraintlayout.widget.e.s, 27);
        f1272b.append(androidx.constraintlayout.widget.e.z0, 32);
        f1272b.append(androidx.constraintlayout.widget.e.A0, 33);
        f1272b.append(androidx.constraintlayout.widget.e.i0, 10);
        f1272b.append(androidx.constraintlayout.widget.e.h0, 9);
        f1272b.append(androidx.constraintlayout.widget.e.Q0, 13);
        f1272b.append(androidx.constraintlayout.widget.e.T0, 16);
        f1272b.append(androidx.constraintlayout.widget.e.R0, 14);
        f1272b.append(androidx.constraintlayout.widget.e.O0, 11);
        f1272b.append(androidx.constraintlayout.widget.e.S0, 15);
        f1272b.append(androidx.constraintlayout.widget.e.P0, 12);
        f1272b.append(androidx.constraintlayout.widget.e.H0, 40);
        f1272b.append(androidx.constraintlayout.widget.e.s0, 39);
        f1272b.append(androidx.constraintlayout.widget.e.r0, 41);
        f1272b.append(androidx.constraintlayout.widget.e.G0, 42);
        f1272b.append(androidx.constraintlayout.widget.e.q0, 20);
        f1272b.append(androidx.constraintlayout.widget.e.F0, 37);
        f1272b.append(androidx.constraintlayout.widget.e.g0, 5);
        f1272b.append(androidx.constraintlayout.widget.e.t0, 82);
        f1272b.append(androidx.constraintlayout.widget.e.C0, 82);
        f1272b.append(androidx.constraintlayout.widget.e.w0, 82);
        f1272b.append(androidx.constraintlayout.widget.e.a0, 82);
        f1272b.append(androidx.constraintlayout.widget.e.Y, 82);
        f1272b.append(androidx.constraintlayout.widget.e.x, 24);
        f1272b.append(androidx.constraintlayout.widget.e.z, 28);
        f1272b.append(androidx.constraintlayout.widget.e.L, 31);
        f1272b.append(androidx.constraintlayout.widget.e.M, 8);
        f1272b.append(androidx.constraintlayout.widget.e.y, 34);
        f1272b.append(androidx.constraintlayout.widget.e.A, 2);
        f1272b.append(androidx.constraintlayout.widget.e.v, 23);
        f1272b.append(androidx.constraintlayout.widget.e.w, 21);
        f1272b.append(androidx.constraintlayout.widget.e.u, 22);
        f1272b.append(androidx.constraintlayout.widget.e.B, 43);
        f1272b.append(androidx.constraintlayout.widget.e.O, 44);
        f1272b.append(androidx.constraintlayout.widget.e.J, 45);
        f1272b.append(androidx.constraintlayout.widget.e.K, 46);
        f1272b.append(androidx.constraintlayout.widget.e.I, 60);
        f1272b.append(androidx.constraintlayout.widget.e.G, 47);
        f1272b.append(androidx.constraintlayout.widget.e.H, 48);
        f1272b.append(androidx.constraintlayout.widget.e.C, 49);
        f1272b.append(androidx.constraintlayout.widget.e.D, 50);
        f1272b.append(androidx.constraintlayout.widget.e.E, 51);
        f1272b.append(androidx.constraintlayout.widget.e.F, 52);
        f1272b.append(androidx.constraintlayout.widget.e.N, 53);
        f1272b.append(androidx.constraintlayout.widget.e.I0, 54);
        f1272b.append(androidx.constraintlayout.widget.e.m0, 55);
        f1272b.append(androidx.constraintlayout.widget.e.J0, 56);
        f1272b.append(androidx.constraintlayout.widget.e.n0, 57);
        f1272b.append(androidx.constraintlayout.widget.e.K0, 58);
        f1272b.append(androidx.constraintlayout.widget.e.o0, 59);
        f1272b.append(androidx.constraintlayout.widget.e.d0, 61);
        f1272b.append(androidx.constraintlayout.widget.e.f0, 62);
        f1272b.append(androidx.constraintlayout.widget.e.e0, 63);
        f1272b.append(androidx.constraintlayout.widget.e.P, 64);
        f1272b.append(androidx.constraintlayout.widget.e.X0, 65);
        f1272b.append(androidx.constraintlayout.widget.e.V, 66);
        f1272b.append(androidx.constraintlayout.widget.e.Y0, 67);
        f1272b.append(androidx.constraintlayout.widget.e.V0, 79);
        f1272b.append(androidx.constraintlayout.widget.e.t, 38);
        f1272b.append(androidx.constraintlayout.widget.e.U0, 68);
        f1272b.append(androidx.constraintlayout.widget.e.L0, 69);
        f1272b.append(androidx.constraintlayout.widget.e.p0, 70);
        f1272b.append(androidx.constraintlayout.widget.e.T, 71);
        f1272b.append(androidx.constraintlayout.widget.e.R, 72);
        f1272b.append(androidx.constraintlayout.widget.e.S, 73);
        f1272b.append(androidx.constraintlayout.widget.e.U, 74);
        f1272b.append(androidx.constraintlayout.widget.e.Q, 75);
        f1272b.append(androidx.constraintlayout.widget.e.W0, 76);
        f1272b.append(androidx.constraintlayout.widget.e.B0, 77);
        f1272b.append(androidx.constraintlayout.widget.e.Z0, 78);
        f1272b.append(androidx.constraintlayout.widget.e.X, 80);
        f1272b.append(androidx.constraintlayout.widget.e.W, 81);
    }

    private int[] k(View view, String str) {
        int iIntValue;
        Object objE;
        String[] strArrSplit = str.split(",");
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
                aVar.f1277c.f1284b = true;
                aVar.f1278d.f1281c = true;
                aVar.f1276b.f1287a = true;
                aVar.e.f1292b = true;
            }
            switch (f1272b.get(index)) {
                case 1:
                    C0039b c0039b = aVar.f1278d;
                    c0039b.q = w(typedArray, index, c0039b.q);
                    break;
                case 2:
                    C0039b c0039b2 = aVar.f1278d;
                    c0039b2.H = typedArray.getDimensionPixelSize(index, c0039b2.H);
                    break;
                case 3:
                    C0039b c0039b3 = aVar.f1278d;
                    c0039b3.p = w(typedArray, index, c0039b3.p);
                    break;
                case 4:
                    C0039b c0039b4 = aVar.f1278d;
                    c0039b4.o = w(typedArray, index, c0039b4.o);
                    break;
                case 5:
                    aVar.f1278d.x = typedArray.getString(index);
                    break;
                case 6:
                    C0039b c0039b5 = aVar.f1278d;
                    c0039b5.B = typedArray.getDimensionPixelOffset(index, c0039b5.B);
                    break;
                case 7:
                    C0039b c0039b6 = aVar.f1278d;
                    c0039b6.C = typedArray.getDimensionPixelOffset(index, c0039b6.C);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0039b c0039b7 = aVar.f1278d;
                        c0039b7.I = typedArray.getDimensionPixelSize(index, c0039b7.I);
                    }
                    break;
                case 9:
                    C0039b c0039b8 = aVar.f1278d;
                    c0039b8.u = w(typedArray, index, c0039b8.u);
                    break;
                case 10:
                    C0039b c0039b9 = aVar.f1278d;
                    c0039b9.t = w(typedArray, index, c0039b9.t);
                    break;
                case 11:
                    C0039b c0039b10 = aVar.f1278d;
                    c0039b10.N = typedArray.getDimensionPixelSize(index, c0039b10.N);
                    break;
                case 12:
                    C0039b c0039b11 = aVar.f1278d;
                    c0039b11.O = typedArray.getDimensionPixelSize(index, c0039b11.O);
                    break;
                case 13:
                    C0039b c0039b12 = aVar.f1278d;
                    c0039b12.K = typedArray.getDimensionPixelSize(index, c0039b12.K);
                    break;
                case 14:
                    C0039b c0039b13 = aVar.f1278d;
                    c0039b13.M = typedArray.getDimensionPixelSize(index, c0039b13.M);
                    break;
                case 15:
                    C0039b c0039b14 = aVar.f1278d;
                    c0039b14.P = typedArray.getDimensionPixelSize(index, c0039b14.P);
                    break;
                case 16:
                    C0039b c0039b15 = aVar.f1278d;
                    c0039b15.L = typedArray.getDimensionPixelSize(index, c0039b15.L);
                    break;
                case 17:
                    C0039b c0039b16 = aVar.f1278d;
                    c0039b16.f = typedArray.getDimensionPixelOffset(index, c0039b16.f);
                    break;
                case 18:
                    C0039b c0039b17 = aVar.f1278d;
                    c0039b17.g = typedArray.getDimensionPixelOffset(index, c0039b17.g);
                    break;
                case 19:
                    C0039b c0039b18 = aVar.f1278d;
                    c0039b18.h = typedArray.getFloat(index, c0039b18.h);
                    break;
                case 20:
                    C0039b c0039b19 = aVar.f1278d;
                    c0039b19.v = typedArray.getFloat(index, c0039b19.v);
                    break;
                case 21:
                    C0039b c0039b20 = aVar.f1278d;
                    c0039b20.e = typedArray.getLayoutDimension(index, c0039b20.e);
                    break;
                case 22:
                    d dVar = aVar.f1276b;
                    dVar.f1288b = typedArray.getInt(index, dVar.f1288b);
                    d dVar2 = aVar.f1276b;
                    dVar2.f1288b = f1271a[dVar2.f1288b];
                    break;
                case 23:
                    C0039b c0039b21 = aVar.f1278d;
                    c0039b21.f1282d = typedArray.getLayoutDimension(index, c0039b21.f1282d);
                    break;
                case 24:
                    C0039b c0039b22 = aVar.f1278d;
                    c0039b22.E = typedArray.getDimensionPixelSize(index, c0039b22.E);
                    break;
                case 25:
                    C0039b c0039b23 = aVar.f1278d;
                    c0039b23.i = w(typedArray, index, c0039b23.i);
                    break;
                case 26:
                    C0039b c0039b24 = aVar.f1278d;
                    c0039b24.j = w(typedArray, index, c0039b24.j);
                    break;
                case 27:
                    C0039b c0039b25 = aVar.f1278d;
                    c0039b25.D = typedArray.getInt(index, c0039b25.D);
                    break;
                case 28:
                    C0039b c0039b26 = aVar.f1278d;
                    c0039b26.F = typedArray.getDimensionPixelSize(index, c0039b26.F);
                    break;
                case 29:
                    C0039b c0039b27 = aVar.f1278d;
                    c0039b27.k = w(typedArray, index, c0039b27.k);
                    break;
                case 30:
                    C0039b c0039b28 = aVar.f1278d;
                    c0039b28.l = w(typedArray, index, c0039b28.l);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0039b c0039b29 = aVar.f1278d;
                        c0039b29.J = typedArray.getDimensionPixelSize(index, c0039b29.J);
                    }
                    break;
                case 32:
                    C0039b c0039b30 = aVar.f1278d;
                    c0039b30.r = w(typedArray, index, c0039b30.r);
                    break;
                case 33:
                    C0039b c0039b31 = aVar.f1278d;
                    c0039b31.s = w(typedArray, index, c0039b31.s);
                    break;
                case 34:
                    C0039b c0039b32 = aVar.f1278d;
                    c0039b32.G = typedArray.getDimensionPixelSize(index, c0039b32.G);
                    break;
                case 35:
                    C0039b c0039b33 = aVar.f1278d;
                    c0039b33.n = w(typedArray, index, c0039b33.n);
                    break;
                case 36:
                    C0039b c0039b34 = aVar.f1278d;
                    c0039b34.m = w(typedArray, index, c0039b34.m);
                    break;
                case 37:
                    C0039b c0039b35 = aVar.f1278d;
                    c0039b35.w = typedArray.getFloat(index, c0039b35.w);
                    break;
                case 38:
                    aVar.f1275a = typedArray.getResourceId(index, aVar.f1275a);
                    break;
                case 39:
                    C0039b c0039b36 = aVar.f1278d;
                    c0039b36.R = typedArray.getFloat(index, c0039b36.R);
                    break;
                case 40:
                    C0039b c0039b37 = aVar.f1278d;
                    c0039b37.Q = typedArray.getFloat(index, c0039b37.Q);
                    break;
                case 41:
                    C0039b c0039b38 = aVar.f1278d;
                    c0039b38.S = typedArray.getInt(index, c0039b38.S);
                    break;
                case 42:
                    C0039b c0039b39 = aVar.f1278d;
                    c0039b39.T = typedArray.getInt(index, c0039b39.T);
                    break;
                case 43:
                    d dVar3 = aVar.f1276b;
                    dVar3.f1290d = typedArray.getFloat(index, dVar3.f1290d);
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
                    eVar2.f1294d = typedArray.getFloat(index, eVar2.f1294d);
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
                    C0039b c0039b40 = aVar.f1278d;
                    c0039b40.U = typedArray.getInt(index, c0039b40.U);
                    break;
                case 55:
                    C0039b c0039b41 = aVar.f1278d;
                    c0039b41.V = typedArray.getInt(index, c0039b41.V);
                    break;
                case 56:
                    C0039b c0039b42 = aVar.f1278d;
                    c0039b42.W = typedArray.getDimensionPixelSize(index, c0039b42.W);
                    break;
                case 57:
                    C0039b c0039b43 = aVar.f1278d;
                    c0039b43.X = typedArray.getDimensionPixelSize(index, c0039b43.X);
                    break;
                case 58:
                    C0039b c0039b44 = aVar.f1278d;
                    c0039b44.Y = typedArray.getDimensionPixelSize(index, c0039b44.Y);
                    break;
                case 59:
                    C0039b c0039b45 = aVar.f1278d;
                    c0039b45.Z = typedArray.getDimensionPixelSize(index, c0039b45.Z);
                    break;
                case 60:
                    e eVar11 = aVar.e;
                    eVar11.f1293c = typedArray.getFloat(index, eVar11.f1293c);
                    break;
                case 61:
                    C0039b c0039b46 = aVar.f1278d;
                    c0039b46.y = w(typedArray, index, c0039b46.y);
                    break;
                case 62:
                    C0039b c0039b47 = aVar.f1278d;
                    c0039b47.z = typedArray.getDimensionPixelSize(index, c0039b47.z);
                    break;
                case 63:
                    C0039b c0039b48 = aVar.f1278d;
                    c0039b48.A = typedArray.getFloat(index, c0039b48.A);
                    break;
                case 64:
                    c cVar = aVar.f1277c;
                    cVar.f1285c = w(typedArray, index, cVar.f1285c);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.f1277c.f1286d = typedArray.getString(index);
                    } else {
                        aVar.f1277c.f1286d = c.e.a.a.c.f2136b[typedArray.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    aVar.f1277c.f = typedArray.getInt(index, 0);
                    break;
                case 67:
                    c cVar2 = aVar.f1277c;
                    cVar2.h = typedArray.getFloat(index, cVar2.h);
                    break;
                case 68:
                    d dVar4 = aVar.f1276b;
                    dVar4.e = typedArray.getFloat(index, dVar4.e);
                    break;
                case 69:
                    aVar.f1278d.a0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    aVar.f1278d.b0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    C0039b c0039b49 = aVar.f1278d;
                    c0039b49.c0 = typedArray.getInt(index, c0039b49.c0);
                    break;
                case 73:
                    C0039b c0039b50 = aVar.f1278d;
                    c0039b50.d0 = typedArray.getDimensionPixelSize(index, c0039b50.d0);
                    break;
                case 74:
                    aVar.f1278d.g0 = typedArray.getString(index);
                    break;
                case 75:
                    C0039b c0039b51 = aVar.f1278d;
                    c0039b51.k0 = typedArray.getBoolean(index, c0039b51.k0);
                    break;
                case 76:
                    c cVar3 = aVar.f1277c;
                    cVar3.e = typedArray.getInt(index, cVar3.e);
                    break;
                case 77:
                    aVar.f1278d.h0 = typedArray.getString(index);
                    break;
                case 78:
                    d dVar5 = aVar.f1276b;
                    dVar5.f1289c = typedArray.getInt(index, dVar5.f1289c);
                    break;
                case 79:
                    c cVar4 = aVar.f1277c;
                    cVar4.g = typedArray.getFloat(index, cVar4.g);
                    break;
                case 80:
                    C0039b c0039b52 = aVar.f1278d;
                    c0039b52.i0 = typedArray.getBoolean(index, c0039b52.i0);
                    break;
                case 81:
                    C0039b c0039b53 = aVar.f1278d;
                    c0039b53.j0 = typedArray.getBoolean(index, c0039b53.j0);
                    break;
                case 82:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1272b.get(index));
                    break;
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1272b.get(index));
                    break;
            }
        }
    }

    public void A(boolean z) {
        this.e = z;
    }

    public void B(boolean z) {
        this.f1273c = z;
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
                            aVar.f1278d.e0 = 1;
                        }
                        int i2 = aVar.f1278d.e0;
                        if (i2 != -1 && i2 == 1) {
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(aVar.f1278d.c0);
                            barrier.setMargin(aVar.f1278d.d0);
                            barrier.setAllowsGoneWidget(aVar.f1278d.k0);
                            C0039b c0039b = aVar.f1278d;
                            int[] iArr = c0039b.f0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = c0039b.g0;
                                if (str != null) {
                                    c0039b.f0 = k(barrier, str);
                                    barrier.setReferencedIds(aVar.f1278d.f0);
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
                        d dVar = aVar.f1276b;
                        if (dVar.f1289c == 0) {
                            childAt.setVisibility(dVar.f1288b);
                        }
                        int i3 = Build.VERSION.SDK_INT;
                        if (i3 >= 17) {
                            childAt.setAlpha(aVar.f1276b.f1290d);
                            childAt.setRotation(aVar.e.f1293c);
                            childAt.setRotationX(aVar.e.f1294d);
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
            int i4 = aVar2.f1278d.e0;
            if (i4 != -1 && i4 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                C0039b c0039b2 = aVar2.f1278d;
                int[] iArr2 = c0039b2.f0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = c0039b2.g0;
                    if (str2 != null) {
                        c0039b2.f0 = k(barrier2, str2);
                        barrier2.setReferencedIds(aVar2.f1278d.f0);
                    }
                }
                barrier2.setType(aVar2.f1278d.c0);
                barrier2.setMargin(aVar2.f1278d.d0);
                ConstraintLayout.b bVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.u();
                aVar2.d(bVarGenerateDefaultLayoutParams);
                constraintLayout.addView(barrier2, bVarGenerateDefaultLayoutParams);
            }
            if (aVar2.f1278d.f1280b) {
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
            aVar.f = ConstraintAttribute.b(this.f1274d, childAt);
            aVar.f(id, bVar);
            aVar.f1276b.f1288b = childAt.getVisibility();
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 17) {
                aVar.f1276b.f1290d = childAt.getAlpha();
                aVar.e.f1293c = childAt.getRotation();
                aVar.e.f1294d = childAt.getRotationX();
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
                aVar.f1278d.k0 = barrier.v();
                aVar.f1278d.f0 = barrier.getReferencedIds();
                aVar.f1278d.c0 = barrier.getType();
                aVar.f1278d.d0 = barrier.getMargin();
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
        return m(i).f1278d.e;
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
        return m(i).f1276b.f1288b;
    }

    public int s(int i) {
        return m(i).f1276b.f1289c;
    }

    public int t(int i) {
        return m(i).f1278d.f1282d;
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
                        aVarL.f1278d.f1280b = true;
                    }
                    this.f.put(Integer.valueOf(aVarL.f1275a), aVarL);
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
            if (!aVar.f1278d.f1281c) {
                aVar.f(id, bVar);
                if (childAt instanceof ConstraintHelper) {
                    aVar.f1278d.f0 = ((ConstraintHelper) childAt).getReferencedIds();
                    if (childAt instanceof Barrier) {
                        Barrier barrier = (Barrier) childAt;
                        aVar.f1278d.k0 = barrier.v();
                        aVar.f1278d.c0 = barrier.getType();
                        aVar.f1278d.d0 = barrier.getMargin();
                    }
                }
                aVar.f1278d.f1281c = true;
            }
            d dVar = aVar.f1276b;
            if (!dVar.f1287a) {
                dVar.f1288b = childAt.getVisibility();
                aVar.f1276b.f1290d = childAt.getAlpha();
                aVar.f1276b.f1287a = true;
            }
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 17) {
                e eVar = aVar.e;
                if (!eVar.f1292b) {
                    eVar.f1292b = true;
                    eVar.f1293c = childAt.getRotation();
                    aVar.e.f1294d = childAt.getRotationX();
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
            C0039b c0039b = aVar2.f1278d;
            if (!c0039b.f1281c) {
                c0039b.a(aVar.f1278d);
            }
            d dVar = aVar2.f1276b;
            if (!dVar.f1287a) {
                dVar.a(aVar.f1276b);
            }
            e eVar = aVar2.e;
            if (!eVar.f1292b) {
                eVar.a(aVar.e);
            }
            c cVar = aVar2.f1277c;
            if (!cVar.f1284b) {
                cVar.a(aVar.f1277c);
            }
            for (String str : aVar.f.keySet()) {
                if (!aVar2.f.containsKey(str)) {
                    aVar2.f.put(str, aVar.f.get(str));
                }
            }
        }
    }
}
