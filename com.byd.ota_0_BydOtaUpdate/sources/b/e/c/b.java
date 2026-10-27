package b.e.c;

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
import b.e.b.j.h;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintSet.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f1470a = {0, 4, 8};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static SparseIntArray f1471b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1472c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public HashMap<String, ConstraintAttribute> f1473d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1474e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public HashMap<Integer, a> f1475f = new HashMap<>();

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1476a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f1477b = new d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c f1478c = new c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final C0018b f1479d = new C0018b();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final e f1480e = new e();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public HashMap<String, ConstraintAttribute> f1481f = new HashMap<>();

        public void d(ConstraintLayout.b bVar) {
            C0018b c0018b = this.f1479d;
            bVar.f401d = c0018b.f1490i;
            bVar.f402e = c0018b.j;
            bVar.f403f = c0018b.k;
            bVar.f404g = c0018b.l;
            bVar.f405h = c0018b.m;
            bVar.f406i = c0018b.n;
            bVar.j = c0018b.o;
            bVar.k = c0018b.p;
            bVar.l = c0018b.q;
            bVar.p = c0018b.r;
            bVar.q = c0018b.s;
            bVar.r = c0018b.t;
            bVar.s = c0018b.u;
            ((ViewGroup.MarginLayoutParams) bVar).leftMargin = c0018b.E;
            ((ViewGroup.MarginLayoutParams) bVar).rightMargin = c0018b.F;
            ((ViewGroup.MarginLayoutParams) bVar).topMargin = c0018b.G;
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = c0018b.H;
            bVar.x = c0018b.P;
            bVar.y = c0018b.O;
            bVar.u = c0018b.L;
            bVar.w = c0018b.N;
            bVar.z = c0018b.v;
            bVar.A = c0018b.w;
            bVar.m = c0018b.y;
            bVar.n = c0018b.z;
            C0018b c0018b2 = this.f1479d;
            bVar.o = c0018b2.A;
            bVar.B = c0018b2.x;
            bVar.Q = c0018b2.B;
            bVar.R = c0018b2.C;
            bVar.F = c0018b2.Q;
            bVar.E = c0018b2.R;
            bVar.H = c0018b2.T;
            bVar.G = c0018b2.S;
            bVar.T = c0018b2.i0;
            bVar.U = c0018b2.j0;
            bVar.I = c0018b2.U;
            bVar.J = c0018b2.V;
            bVar.M = c0018b2.W;
            bVar.N = c0018b2.X;
            bVar.K = c0018b2.Y;
            bVar.L = c0018b2.Z;
            bVar.O = c0018b2.a0;
            bVar.P = c0018b2.b0;
            bVar.S = c0018b2.D;
            bVar.f400c = c0018b2.f1489h;
            bVar.f398a = c0018b2.f1487f;
            bVar.f399b = c0018b2.f1488g;
            ((ViewGroup.MarginLayoutParams) bVar).width = c0018b2.f1485d;
            ((ViewGroup.MarginLayoutParams) bVar).height = c0018b2.f1486e;
            String str = c0018b2.h0;
            if (str != null) {
                bVar.V = str;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                bVar.setMarginStart(this.f1479d.J);
                bVar.setMarginEnd(this.f1479d.I);
            }
            bVar.c();
        }

        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.f1479d.a(this.f1479d);
            aVar.f1478c.a(this.f1478c);
            aVar.f1477b.a(this.f1477b);
            aVar.f1480e.a(this.f1480e);
            aVar.f1476a = this.f1476a;
            return aVar;
        }

        public final void f(int i2, ConstraintLayout.b bVar) {
            this.f1476a = i2;
            C0018b c0018b = this.f1479d;
            c0018b.f1490i = bVar.f401d;
            c0018b.j = bVar.f402e;
            c0018b.k = bVar.f403f;
            c0018b.l = bVar.f404g;
            c0018b.m = bVar.f405h;
            c0018b.n = bVar.f406i;
            c0018b.o = bVar.j;
            c0018b.p = bVar.k;
            c0018b.q = bVar.l;
            c0018b.r = bVar.p;
            c0018b.s = bVar.q;
            c0018b.t = bVar.r;
            c0018b.u = bVar.s;
            c0018b.v = bVar.z;
            c0018b.w = bVar.A;
            c0018b.x = bVar.B;
            c0018b.y = bVar.m;
            c0018b.z = bVar.n;
            c0018b.A = bVar.o;
            c0018b.B = bVar.Q;
            c0018b.C = bVar.R;
            c0018b.D = bVar.S;
            c0018b.f1489h = bVar.f400c;
            c0018b.f1487f = bVar.f398a;
            c0018b.f1488g = bVar.f399b;
            C0018b c0018b2 = this.f1479d;
            c0018b2.f1485d = ((ViewGroup.MarginLayoutParams) bVar).width;
            c0018b2.f1486e = ((ViewGroup.MarginLayoutParams) bVar).height;
            c0018b2.E = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            c0018b2.F = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
            c0018b2.G = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            c0018b2.H = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
            c0018b2.Q = bVar.F;
            c0018b2.R = bVar.E;
            c0018b2.T = bVar.H;
            c0018b2.S = bVar.G;
            c0018b2.i0 = bVar.T;
            c0018b2.j0 = bVar.U;
            c0018b2.U = bVar.I;
            c0018b2.V = bVar.J;
            c0018b2.W = bVar.M;
            c0018b2.X = bVar.N;
            c0018b2.Y = bVar.K;
            c0018b2.Z = bVar.L;
            c0018b2.a0 = bVar.O;
            c0018b2.b0 = bVar.P;
            c0018b2.h0 = bVar.V;
            c0018b2.L = bVar.u;
            c0018b2.N = bVar.w;
            c0018b2.K = bVar.t;
            c0018b2.M = bVar.v;
            C0018b c0018b3 = this.f1479d;
            c0018b3.P = bVar.x;
            c0018b3.O = bVar.y;
            if (Build.VERSION.SDK_INT >= 17) {
                c0018b3.I = bVar.getMarginEnd();
                this.f1479d.J = bVar.getMarginStart();
            }
        }

        public final void g(int i2, Constraints.a aVar) {
            f(i2, aVar);
            this.f1477b.f1502d = aVar.p0;
            e eVar = this.f1480e;
            eVar.f1506c = aVar.s0;
            eVar.f1507d = aVar.t0;
            eVar.f1508e = aVar.u0;
            eVar.f1509f = aVar.v0;
            eVar.f1510g = aVar.w0;
            eVar.f1511h = aVar.x0;
            eVar.f1512i = aVar.y0;
            eVar.j = aVar.z0;
            eVar.k = aVar.A0;
            eVar.l = aVar.B0;
            eVar.n = aVar.r0;
            eVar.m = aVar.q0;
        }

        public final void h(ConstraintHelper constraintHelper, int i2, Constraints.a aVar) {
            g(i2, aVar);
            if (constraintHelper instanceof Barrier) {
                C0018b c0018b = this.f1479d;
                c0018b.e0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                c0018b.c0 = barrier.getType();
                this.f1479d.f0 = barrier.getReferencedIds();
                this.f1479d.d0 = barrier.getMargin();
            }
        }
    }

    /* JADX INFO: renamed from: b.e.c.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class C0018b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f1482a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1485d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1486e;
        public int[] f0;
        public String g0;
        public String h0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1483b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1484c = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1487f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1488g = -1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1489h = -1.0f;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1490i = -1;
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
            f1482a = sparseIntArray;
            sparseIntArray.append(b.e.c.e.O5, 24);
            f1482a.append(b.e.c.e.P5, 25);
            f1482a.append(b.e.c.e.R5, 28);
            f1482a.append(b.e.c.e.S5, 29);
            f1482a.append(b.e.c.e.X5, 35);
            f1482a.append(b.e.c.e.W5, 34);
            f1482a.append(b.e.c.e.z5, 4);
            f1482a.append(b.e.c.e.y5, 3);
            f1482a.append(b.e.c.e.w5, 1);
            f1482a.append(b.e.c.e.c6, 6);
            f1482a.append(b.e.c.e.d6, 7);
            f1482a.append(b.e.c.e.G5, 17);
            f1482a.append(b.e.c.e.H5, 18);
            f1482a.append(b.e.c.e.I5, 19);
            f1482a.append(b.e.c.e.h5, 26);
            f1482a.append(b.e.c.e.T5, 31);
            f1482a.append(b.e.c.e.U5, 32);
            f1482a.append(b.e.c.e.F5, 10);
            f1482a.append(b.e.c.e.E5, 9);
            f1482a.append(b.e.c.e.g6, 13);
            f1482a.append(b.e.c.e.j6, 16);
            f1482a.append(b.e.c.e.h6, 14);
            f1482a.append(b.e.c.e.e6, 11);
            f1482a.append(b.e.c.e.i6, 15);
            f1482a.append(b.e.c.e.f6, 12);
            f1482a.append(b.e.c.e.a6, 38);
            f1482a.append(b.e.c.e.M5, 37);
            f1482a.append(b.e.c.e.L5, 39);
            f1482a.append(b.e.c.e.Z5, 40);
            f1482a.append(b.e.c.e.K5, 20);
            f1482a.append(b.e.c.e.Y5, 36);
            f1482a.append(b.e.c.e.D5, 5);
            f1482a.append(b.e.c.e.N5, 76);
            f1482a.append(b.e.c.e.V5, 76);
            f1482a.append(b.e.c.e.Q5, 76);
            f1482a.append(b.e.c.e.x5, 76);
            f1482a.append(b.e.c.e.v5, 76);
            f1482a.append(b.e.c.e.k5, 23);
            f1482a.append(b.e.c.e.m5, 27);
            f1482a.append(b.e.c.e.o5, 30);
            f1482a.append(b.e.c.e.p5, 8);
            f1482a.append(b.e.c.e.l5, 33);
            f1482a.append(b.e.c.e.n5, 2);
            f1482a.append(b.e.c.e.i5, 22);
            f1482a.append(b.e.c.e.j5, 21);
            f1482a.append(b.e.c.e.A5, 61);
            f1482a.append(b.e.c.e.C5, 62);
            f1482a.append(b.e.c.e.B5, 63);
            f1482a.append(b.e.c.e.b6, 69);
            f1482a.append(b.e.c.e.J5, 70);
            f1482a.append(b.e.c.e.t5, 71);
            f1482a.append(b.e.c.e.r5, 72);
            f1482a.append(b.e.c.e.s5, 73);
            f1482a.append(b.e.c.e.u5, 74);
            f1482a.append(b.e.c.e.q5, 75);
        }

        public void a(C0018b c0018b) {
            this.f1483b = c0018b.f1483b;
            this.f1485d = c0018b.f1485d;
            this.f1484c = c0018b.f1484c;
            this.f1486e = c0018b.f1486e;
            this.f1487f = c0018b.f1487f;
            this.f1488g = c0018b.f1488g;
            this.f1489h = c0018b.f1489h;
            this.f1490i = c0018b.f1490i;
            this.j = c0018b.j;
            this.k = c0018b.k;
            this.l = c0018b.l;
            this.m = c0018b.m;
            this.n = c0018b.n;
            this.o = c0018b.o;
            this.p = c0018b.p;
            this.q = c0018b.q;
            this.r = c0018b.r;
            this.s = c0018b.s;
            this.t = c0018b.t;
            this.u = c0018b.u;
            this.v = c0018b.v;
            this.w = c0018b.w;
            this.x = c0018b.x;
            this.y = c0018b.y;
            this.z = c0018b.z;
            this.A = c0018b.A;
            this.B = c0018b.B;
            this.C = c0018b.C;
            this.D = c0018b.D;
            this.E = c0018b.E;
            this.F = c0018b.F;
            this.G = c0018b.G;
            this.H = c0018b.H;
            this.I = c0018b.I;
            this.J = c0018b.J;
            this.K = c0018b.K;
            this.L = c0018b.L;
            this.M = c0018b.M;
            this.N = c0018b.N;
            this.O = c0018b.O;
            this.P = c0018b.P;
            this.Q = c0018b.Q;
            this.R = c0018b.R;
            this.S = c0018b.S;
            this.T = c0018b.T;
            this.U = c0018b.U;
            this.V = c0018b.V;
            this.W = c0018b.W;
            this.X = c0018b.X;
            this.Y = c0018b.Y;
            this.Z = c0018b.Z;
            this.a0 = c0018b.a0;
            this.b0 = c0018b.b0;
            this.c0 = c0018b.c0;
            this.d0 = c0018b.d0;
            this.e0 = c0018b.e0;
            this.h0 = c0018b.h0;
            int[] iArr = c0018b.f0;
            if (iArr != null) {
                this.f0 = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.f0 = null;
            }
            this.g0 = c0018b.g0;
            this.i0 = c0018b.i0;
            this.j0 = c0018b.j0;
            this.k0 = c0018b.k0;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.e.c.e.g5);
            this.f1484c = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                int i3 = f1482a.get(index);
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
                            this.f1487f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1487f);
                            break;
                        case 18:
                            this.f1488g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1488g);
                            break;
                        case 19:
                            this.f1489h = typedArrayObtainStyledAttributes.getFloat(index, this.f1489h);
                            break;
                        case 20:
                            this.v = typedArrayObtainStyledAttributes.getFloat(index, this.v);
                            break;
                        case 21:
                            this.f1486e = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f1486e);
                            break;
                        case 22:
                            this.f1485d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f1485d);
                            break;
                        case 23:
                            this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.E);
                            break;
                        case 24:
                            this.f1490i = b.w(typedArrayObtainStyledAttributes, index, this.f1490i);
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
                                                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1482a.get(index));
                                                    break;
                                                case 77:
                                                    this.h0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                default:
                                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1482a.get(index));
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
        public static SparseIntArray f1491a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1492b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1493c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f1494d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1495e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1496f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f1497g = Float.NaN;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1498h = Float.NaN;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1491a = sparseIntArray;
            sparseIntArray.append(b.e.c.e.A6, 1);
            f1491a.append(b.e.c.e.C6, 2);
            f1491a.append(b.e.c.e.D6, 3);
            f1491a.append(b.e.c.e.z6, 4);
            f1491a.append(b.e.c.e.y6, 5);
            f1491a.append(b.e.c.e.B6, 6);
        }

        public void a(c cVar) {
            this.f1492b = cVar.f1492b;
            this.f1493c = cVar.f1493c;
            this.f1494d = cVar.f1494d;
            this.f1495e = cVar.f1495e;
            this.f1496f = cVar.f1496f;
            this.f1498h = cVar.f1498h;
            this.f1497g = cVar.f1497g;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.e.c.e.x6);
            this.f1492b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                switch (f1491a.get(index)) {
                    case 1:
                        this.f1498h = typedArrayObtainStyledAttributes.getFloat(index, this.f1498h);
                        break;
                    case 2:
                        this.f1495e = typedArrayObtainStyledAttributes.getInt(index, this.f1495e);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.f1494d = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.f1494d = b.e.a.a.c.f1198b[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.f1496f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.f1493c = b.w(typedArrayObtainStyledAttributes, index, this.f1493c);
                        break;
                    case 6:
                        this.f1497g = typedArrayObtainStyledAttributes.getFloat(index, this.f1497g);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1499a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1500b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1501c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1502d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1503e = Float.NaN;

        public void a(d dVar) {
            this.f1499a = dVar.f1499a;
            this.f1500b = dVar.f1500b;
            this.f1502d = dVar.f1502d;
            this.f1503e = dVar.f1503e;
            this.f1501c = dVar.f1501c;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.e.c.e.n7);
            this.f1499a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == b.e.c.e.p7) {
                    this.f1502d = typedArrayObtainStyledAttributes.getFloat(index, this.f1502d);
                } else if (index == b.e.c.e.o7) {
                    this.f1500b = typedArrayObtainStyledAttributes.getInt(index, this.f1500b);
                    this.f1500b = b.f1470a[this.f1500b];
                } else if (index == b.e.c.e.r7) {
                    this.f1501c = typedArrayObtainStyledAttributes.getInt(index, this.f1501c);
                } else if (index == b.e.c.e.q7) {
                    this.f1503e = typedArrayObtainStyledAttributes.getFloat(index, this.f1503e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f1504a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1505b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1506c = 0.0f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1507d = 0.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1508e = 0.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f1509f = 1.0f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f1510g = 1.0f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1511h = Float.NaN;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f1512i = Float.NaN;
        public float j = 0.0f;
        public float k = 0.0f;
        public float l = 0.0f;
        public boolean m = false;
        public float n = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1504a = sparseIntArray;
            sparseIntArray.append(b.e.c.e.M7, 1);
            f1504a.append(b.e.c.e.N7, 2);
            f1504a.append(b.e.c.e.O7, 3);
            f1504a.append(b.e.c.e.K7, 4);
            f1504a.append(b.e.c.e.L7, 5);
            f1504a.append(b.e.c.e.G7, 6);
            f1504a.append(b.e.c.e.H7, 7);
            f1504a.append(b.e.c.e.I7, 8);
            f1504a.append(b.e.c.e.J7, 9);
            f1504a.append(b.e.c.e.P7, 10);
            f1504a.append(b.e.c.e.Q7, 11);
        }

        public void a(e eVar) {
            this.f1505b = eVar.f1505b;
            this.f1506c = eVar.f1506c;
            this.f1507d = eVar.f1507d;
            this.f1508e = eVar.f1508e;
            this.f1509f = eVar.f1509f;
            this.f1510g = eVar.f1510g;
            this.f1511h = eVar.f1511h;
            this.f1512i = eVar.f1512i;
            this.j = eVar.j;
            this.k = eVar.k;
            this.l = eVar.l;
            this.m = eVar.m;
            this.n = eVar.n;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.e.c.e.F7);
            this.f1505b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                switch (f1504a.get(index)) {
                    case 1:
                        this.f1506c = typedArrayObtainStyledAttributes.getFloat(index, this.f1506c);
                        break;
                    case 2:
                        this.f1507d = typedArrayObtainStyledAttributes.getFloat(index, this.f1507d);
                        break;
                    case 3:
                        this.f1508e = typedArrayObtainStyledAttributes.getFloat(index, this.f1508e);
                        break;
                    case 4:
                        this.f1509f = typedArrayObtainStyledAttributes.getFloat(index, this.f1509f);
                        break;
                    case 5:
                        this.f1510g = typedArrayObtainStyledAttributes.getFloat(index, this.f1510g);
                        break;
                    case 6:
                        this.f1511h = typedArrayObtainStyledAttributes.getDimension(index, this.f1511h);
                        break;
                    case 7:
                        this.f1512i = typedArrayObtainStyledAttributes.getDimension(index, this.f1512i);
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
        f1471b = sparseIntArray;
        sparseIntArray.append(b.e.c.e.u0, 25);
        f1471b.append(b.e.c.e.v0, 26);
        f1471b.append(b.e.c.e.x0, 29);
        f1471b.append(b.e.c.e.y0, 30);
        f1471b.append(b.e.c.e.E0, 36);
        f1471b.append(b.e.c.e.D0, 35);
        f1471b.append(b.e.c.e.c0, 4);
        f1471b.append(b.e.c.e.b0, 3);
        f1471b.append(b.e.c.e.Z, 1);
        f1471b.append(b.e.c.e.M0, 6);
        f1471b.append(b.e.c.e.N0, 7);
        f1471b.append(b.e.c.e.j0, 17);
        f1471b.append(b.e.c.e.k0, 18);
        f1471b.append(b.e.c.e.l0, 19);
        f1471b.append(b.e.c.e.s, 27);
        f1471b.append(b.e.c.e.z0, 32);
        f1471b.append(b.e.c.e.A0, 33);
        f1471b.append(b.e.c.e.i0, 10);
        f1471b.append(b.e.c.e.h0, 9);
        f1471b.append(b.e.c.e.Q0, 13);
        f1471b.append(b.e.c.e.T0, 16);
        f1471b.append(b.e.c.e.R0, 14);
        f1471b.append(b.e.c.e.O0, 11);
        f1471b.append(b.e.c.e.S0, 15);
        f1471b.append(b.e.c.e.P0, 12);
        f1471b.append(b.e.c.e.H0, 40);
        f1471b.append(b.e.c.e.s0, 39);
        f1471b.append(b.e.c.e.r0, 41);
        f1471b.append(b.e.c.e.G0, 42);
        f1471b.append(b.e.c.e.q0, 20);
        f1471b.append(b.e.c.e.F0, 37);
        f1471b.append(b.e.c.e.g0, 5);
        f1471b.append(b.e.c.e.t0, 82);
        f1471b.append(b.e.c.e.C0, 82);
        f1471b.append(b.e.c.e.w0, 82);
        f1471b.append(b.e.c.e.a0, 82);
        f1471b.append(b.e.c.e.Y, 82);
        f1471b.append(b.e.c.e.x, 24);
        f1471b.append(b.e.c.e.z, 28);
        f1471b.append(b.e.c.e.L, 31);
        f1471b.append(b.e.c.e.M, 8);
        f1471b.append(b.e.c.e.y, 34);
        f1471b.append(b.e.c.e.A, 2);
        f1471b.append(b.e.c.e.v, 23);
        f1471b.append(b.e.c.e.w, 21);
        f1471b.append(b.e.c.e.u, 22);
        f1471b.append(b.e.c.e.B, 43);
        f1471b.append(b.e.c.e.O, 44);
        f1471b.append(b.e.c.e.J, 45);
        f1471b.append(b.e.c.e.K, 46);
        f1471b.append(b.e.c.e.I, 60);
        f1471b.append(b.e.c.e.G, 47);
        f1471b.append(b.e.c.e.H, 48);
        f1471b.append(b.e.c.e.C, 49);
        f1471b.append(b.e.c.e.D, 50);
        f1471b.append(b.e.c.e.E, 51);
        f1471b.append(b.e.c.e.F, 52);
        f1471b.append(b.e.c.e.N, 53);
        f1471b.append(b.e.c.e.I0, 54);
        f1471b.append(b.e.c.e.m0, 55);
        f1471b.append(b.e.c.e.J0, 56);
        f1471b.append(b.e.c.e.n0, 57);
        f1471b.append(b.e.c.e.K0, 58);
        f1471b.append(b.e.c.e.o0, 59);
        f1471b.append(b.e.c.e.d0, 61);
        f1471b.append(b.e.c.e.f0, 62);
        f1471b.append(b.e.c.e.e0, 63);
        f1471b.append(b.e.c.e.P, 64);
        f1471b.append(b.e.c.e.X0, 65);
        f1471b.append(b.e.c.e.V, 66);
        f1471b.append(b.e.c.e.Y0, 67);
        f1471b.append(b.e.c.e.V0, 79);
        f1471b.append(b.e.c.e.t, 38);
        f1471b.append(b.e.c.e.U0, 68);
        f1471b.append(b.e.c.e.L0, 69);
        f1471b.append(b.e.c.e.p0, 70);
        f1471b.append(b.e.c.e.T, 71);
        f1471b.append(b.e.c.e.R, 72);
        f1471b.append(b.e.c.e.S, 73);
        f1471b.append(b.e.c.e.U, 74);
        f1471b.append(b.e.c.e.Q, 75);
        f1471b.append(b.e.c.e.W0, 76);
        f1471b.append(b.e.c.e.B0, 77);
        f1471b.append(b.e.c.e.Z0, 78);
        f1471b.append(b.e.c.e.X, 80);
        f1471b.append(b.e.c.e.W, 81);
    }

    public static int w(TypedArray typedArray, int i2, int i3) {
        int resourceId = typedArray.getResourceId(i2, i3);
        return resourceId == -1 ? typedArray.getInt(i2, -1) : resourceId;
    }

    public void A(boolean z) {
        this.f1474e = z;
    }

    public void B(boolean z) {
        this.f1472c = z;
    }

    public void c(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!this.f1475f.containsKey(Integer.valueOf(id))) {
                Log.v("ConstraintSet", "id unknown " + b.e.a.b.a.c(childAt));
            } else {
                if (this.f1474e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (this.f1475f.containsKey(Integer.valueOf(id))) {
                    ConstraintAttribute.h(childAt, this.f1475f.get(Integer.valueOf(id)).f1481f);
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
        if (this.f1475f.containsKey(Integer.valueOf(id))) {
            a aVar = this.f1475f.get(Integer.valueOf(id));
            if (constraintWidget instanceof h) {
                constraintHelper.n(aVar, (h) constraintWidget, bVar, sparseArray);
            }
        }
    }

    public void f(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.f1475f.keySet());
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!this.f1475f.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + b.e.a.b.a.c(childAt));
            } else {
                if (this.f1474e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.f1475f.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = this.f1475f.get(Integer.valueOf(id));
                        if (childAt instanceof Barrier) {
                            aVar.f1479d.e0 = 1;
                        }
                        int i3 = aVar.f1479d.e0;
                        if (i3 != -1 && i3 == 1) {
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(aVar.f1479d.c0);
                            barrier.setMargin(aVar.f1479d.d0);
                            barrier.setAllowsGoneWidget(aVar.f1479d.k0);
                            C0018b c0018b = aVar.f1479d;
                            int[] iArr = c0018b.f0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = c0018b.g0;
                                if (str != null) {
                                    c0018b.f0 = k(barrier, str);
                                    barrier.setReferencedIds(aVar.f1479d.f0);
                                }
                            }
                        }
                        ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
                        bVar.c();
                        aVar.d(bVar);
                        if (z) {
                            ConstraintAttribute.h(childAt, aVar.f1481f);
                        }
                        childAt.setLayoutParams(bVar);
                        d dVar = aVar.f1477b;
                        if (dVar.f1501c == 0) {
                            childAt.setVisibility(dVar.f1500b);
                        }
                        int i4 = Build.VERSION.SDK_INT;
                        if (i4 >= 17) {
                            childAt.setAlpha(aVar.f1477b.f1502d);
                            childAt.setRotation(aVar.f1480e.f1506c);
                            childAt.setRotationX(aVar.f1480e.f1507d);
                            childAt.setRotationY(aVar.f1480e.f1508e);
                            childAt.setScaleX(aVar.f1480e.f1509f);
                            childAt.setScaleY(aVar.f1480e.f1510g);
                            if (!Float.isNaN(aVar.f1480e.f1511h)) {
                                childAt.setPivotX(aVar.f1480e.f1511h);
                            }
                            if (!Float.isNaN(aVar.f1480e.f1512i)) {
                                childAt.setPivotY(aVar.f1480e.f1512i);
                            }
                            childAt.setTranslationX(aVar.f1480e.j);
                            childAt.setTranslationY(aVar.f1480e.k);
                            if (i4 >= 21) {
                                childAt.setTranslationZ(aVar.f1480e.l);
                                e eVar = aVar.f1480e;
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
            a aVar2 = this.f1475f.get(num);
            int i5 = aVar2.f1479d.e0;
            if (i5 != -1 && i5 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                C0018b c0018b2 = aVar2.f1479d;
                int[] iArr2 = c0018b2.f0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = c0018b2.g0;
                    if (str2 != null) {
                        c0018b2.f0 = k(barrier2, str2);
                        barrier2.setReferencedIds(aVar2.f1479d.f0);
                    }
                }
                barrier2.setType(aVar2.f1479d.c0);
                barrier2.setMargin(aVar2.f1479d.d0);
                ConstraintLayout.b bVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.u();
                aVar2.d(bVarGenerateDefaultLayoutParams);
                constraintLayout.addView(barrier2, bVarGenerateDefaultLayoutParams);
            }
            if (aVar2.f1479d.f1483b) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.b bVarGenerateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                aVar2.d(bVarGenerateDefaultLayoutParams2);
                constraintLayout.addView(guideline, bVarGenerateDefaultLayoutParams2);
            }
        }
    }

    public void g(int i2, ConstraintLayout.b bVar) {
        if (this.f1475f.containsKey(Integer.valueOf(i2))) {
            this.f1475f.get(Integer.valueOf(i2)).d(bVar);
        }
    }

    public void h(Context context, int i2) {
        i((ConstraintLayout) LayoutInflater.from(context).inflate(i2, (ViewGroup) null));
    }

    public void i(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.f1475f.clear();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1474e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1475f.containsKey(Integer.valueOf(id))) {
                this.f1475f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f1475f.get(Integer.valueOf(id));
            aVar.f1481f = ConstraintAttribute.b(this.f1473d, childAt);
            aVar.f(id, bVar);
            aVar.f1477b.f1500b = childAt.getVisibility();
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 17) {
                aVar.f1477b.f1502d = childAt.getAlpha();
                aVar.f1480e.f1506c = childAt.getRotation();
                aVar.f1480e.f1507d = childAt.getRotationX();
                aVar.f1480e.f1508e = childAt.getRotationY();
                aVar.f1480e.f1509f = childAt.getScaleX();
                aVar.f1480e.f1510g = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    e eVar = aVar.f1480e;
                    eVar.f1511h = pivotX;
                    eVar.f1512i = pivotY;
                }
                aVar.f1480e.j = childAt.getTranslationX();
                aVar.f1480e.k = childAt.getTranslationY();
                if (i3 >= 21) {
                    aVar.f1480e.l = childAt.getTranslationZ();
                    e eVar2 = aVar.f1480e;
                    if (eVar2.m) {
                        eVar2.n = childAt.getElevation();
                    }
                }
            }
            if (childAt instanceof Barrier) {
                Barrier barrier = (Barrier) childAt;
                aVar.f1479d.k0 = barrier.v();
                aVar.f1479d.f0 = barrier.getReferencedIds();
                aVar.f1479d.c0 = barrier.getType();
                aVar.f1479d.d0 = barrier.getMargin();
            }
        }
    }

    public void j(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.f1475f.clear();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraints.getChildAt(i2);
            Constraints.a aVar = (Constraints.a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1474e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1475f.containsKey(Integer.valueOf(id))) {
                this.f1475f.put(Integer.valueOf(id), new a());
            }
            a aVar2 = this.f1475f.get(Integer.valueOf(id));
            if (childAt instanceof ConstraintHelper) {
                aVar2.h((ConstraintHelper) childAt, id, aVar);
            }
            aVar2.g(id, aVar);
        }
    }

    public final int[] k(View view, String str) {
        int iIntValue;
        Object objE;
        String[] strArrSplit = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < strArrSplit.length) {
            String strTrim = strArrSplit[i2].trim();
            try {
                iIntValue = b.e.c.d.class.getField(strTrim).getInt(null);
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
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.e.c.e.r);
        x(context, aVar, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    public final a m(int i2) {
        if (!this.f1475f.containsKey(Integer.valueOf(i2))) {
            this.f1475f.put(Integer.valueOf(i2), new a());
        }
        return this.f1475f.get(Integer.valueOf(i2));
    }

    public a n(int i2) {
        if (this.f1475f.containsKey(Integer.valueOf(i2))) {
            return this.f1475f.get(Integer.valueOf(i2));
        }
        return null;
    }

    public int o(int i2) {
        return m(i2).f1479d.f1486e;
    }

    public int[] p() {
        Integer[] numArr = (Integer[]) this.f1475f.keySet().toArray(new Integer[0]);
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
        return m(i2).f1477b.f1500b;
    }

    public int s(int i2) {
        return m(i2).f1477b.f1501c;
    }

    public int t(int i2) {
        return m(i2).f1479d.f1485d;
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
                        aVarL.f1479d.f1483b = true;
                    }
                    this.f1475f.put(Integer.valueOf(aVarL.f1476a), aVarL);
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
        throw new UnsupportedOperationException("Method not decompiled: b.e.c.b.v(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public final void x(Context context, a aVar, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArray.getIndex(i2);
            if (index != b.e.c.e.t && b.e.c.e.L != index && b.e.c.e.M != index) {
                aVar.f1478c.f1492b = true;
                aVar.f1479d.f1484c = true;
                aVar.f1477b.f1499a = true;
                aVar.f1480e.f1505b = true;
            }
            switch (f1471b.get(index)) {
                case 1:
                    C0018b c0018b = aVar.f1479d;
                    c0018b.q = w(typedArray, index, c0018b.q);
                    break;
                case 2:
                    C0018b c0018b2 = aVar.f1479d;
                    c0018b2.H = typedArray.getDimensionPixelSize(index, c0018b2.H);
                    break;
                case 3:
                    C0018b c0018b3 = aVar.f1479d;
                    c0018b3.p = w(typedArray, index, c0018b3.p);
                    break;
                case 4:
                    C0018b c0018b4 = aVar.f1479d;
                    c0018b4.o = w(typedArray, index, c0018b4.o);
                    break;
                case 5:
                    aVar.f1479d.x = typedArray.getString(index);
                    break;
                case 6:
                    C0018b c0018b5 = aVar.f1479d;
                    c0018b5.B = typedArray.getDimensionPixelOffset(index, c0018b5.B);
                    break;
                case 7:
                    C0018b c0018b6 = aVar.f1479d;
                    c0018b6.C = typedArray.getDimensionPixelOffset(index, c0018b6.C);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0018b c0018b7 = aVar.f1479d;
                        c0018b7.I = typedArray.getDimensionPixelSize(index, c0018b7.I);
                    }
                    break;
                case 9:
                    C0018b c0018b8 = aVar.f1479d;
                    c0018b8.u = w(typedArray, index, c0018b8.u);
                    break;
                case 10:
                    C0018b c0018b9 = aVar.f1479d;
                    c0018b9.t = w(typedArray, index, c0018b9.t);
                    break;
                case 11:
                    C0018b c0018b10 = aVar.f1479d;
                    c0018b10.N = typedArray.getDimensionPixelSize(index, c0018b10.N);
                    break;
                case 12:
                    C0018b c0018b11 = aVar.f1479d;
                    c0018b11.O = typedArray.getDimensionPixelSize(index, c0018b11.O);
                    break;
                case 13:
                    C0018b c0018b12 = aVar.f1479d;
                    c0018b12.K = typedArray.getDimensionPixelSize(index, c0018b12.K);
                    break;
                case 14:
                    C0018b c0018b13 = aVar.f1479d;
                    c0018b13.M = typedArray.getDimensionPixelSize(index, c0018b13.M);
                    break;
                case 15:
                    C0018b c0018b14 = aVar.f1479d;
                    c0018b14.P = typedArray.getDimensionPixelSize(index, c0018b14.P);
                    break;
                case 16:
                    C0018b c0018b15 = aVar.f1479d;
                    c0018b15.L = typedArray.getDimensionPixelSize(index, c0018b15.L);
                    break;
                case 17:
                    C0018b c0018b16 = aVar.f1479d;
                    c0018b16.f1487f = typedArray.getDimensionPixelOffset(index, c0018b16.f1487f);
                    break;
                case 18:
                    C0018b c0018b17 = aVar.f1479d;
                    c0018b17.f1488g = typedArray.getDimensionPixelOffset(index, c0018b17.f1488g);
                    break;
                case 19:
                    C0018b c0018b18 = aVar.f1479d;
                    c0018b18.f1489h = typedArray.getFloat(index, c0018b18.f1489h);
                    break;
                case 20:
                    C0018b c0018b19 = aVar.f1479d;
                    c0018b19.v = typedArray.getFloat(index, c0018b19.v);
                    break;
                case 21:
                    C0018b c0018b20 = aVar.f1479d;
                    c0018b20.f1486e = typedArray.getLayoutDimension(index, c0018b20.f1486e);
                    break;
                case 22:
                    d dVar = aVar.f1477b;
                    dVar.f1500b = typedArray.getInt(index, dVar.f1500b);
                    d dVar2 = aVar.f1477b;
                    dVar2.f1500b = f1470a[dVar2.f1500b];
                    break;
                case 23:
                    C0018b c0018b21 = aVar.f1479d;
                    c0018b21.f1485d = typedArray.getLayoutDimension(index, c0018b21.f1485d);
                    break;
                case 24:
                    C0018b c0018b22 = aVar.f1479d;
                    c0018b22.E = typedArray.getDimensionPixelSize(index, c0018b22.E);
                    break;
                case 25:
                    C0018b c0018b23 = aVar.f1479d;
                    c0018b23.f1490i = w(typedArray, index, c0018b23.f1490i);
                    break;
                case 26:
                    C0018b c0018b24 = aVar.f1479d;
                    c0018b24.j = w(typedArray, index, c0018b24.j);
                    break;
                case 27:
                    C0018b c0018b25 = aVar.f1479d;
                    c0018b25.D = typedArray.getInt(index, c0018b25.D);
                    break;
                case 28:
                    C0018b c0018b26 = aVar.f1479d;
                    c0018b26.F = typedArray.getDimensionPixelSize(index, c0018b26.F);
                    break;
                case 29:
                    C0018b c0018b27 = aVar.f1479d;
                    c0018b27.k = w(typedArray, index, c0018b27.k);
                    break;
                case 30:
                    C0018b c0018b28 = aVar.f1479d;
                    c0018b28.l = w(typedArray, index, c0018b28.l);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0018b c0018b29 = aVar.f1479d;
                        c0018b29.J = typedArray.getDimensionPixelSize(index, c0018b29.J);
                    }
                    break;
                case 32:
                    C0018b c0018b30 = aVar.f1479d;
                    c0018b30.r = w(typedArray, index, c0018b30.r);
                    break;
                case 33:
                    C0018b c0018b31 = aVar.f1479d;
                    c0018b31.s = w(typedArray, index, c0018b31.s);
                    break;
                case 34:
                    C0018b c0018b32 = aVar.f1479d;
                    c0018b32.G = typedArray.getDimensionPixelSize(index, c0018b32.G);
                    break;
                case 35:
                    C0018b c0018b33 = aVar.f1479d;
                    c0018b33.n = w(typedArray, index, c0018b33.n);
                    break;
                case 36:
                    C0018b c0018b34 = aVar.f1479d;
                    c0018b34.m = w(typedArray, index, c0018b34.m);
                    break;
                case 37:
                    C0018b c0018b35 = aVar.f1479d;
                    c0018b35.w = typedArray.getFloat(index, c0018b35.w);
                    break;
                case 38:
                    aVar.f1476a = typedArray.getResourceId(index, aVar.f1476a);
                    break;
                case 39:
                    C0018b c0018b36 = aVar.f1479d;
                    c0018b36.R = typedArray.getFloat(index, c0018b36.R);
                    break;
                case 40:
                    C0018b c0018b37 = aVar.f1479d;
                    c0018b37.Q = typedArray.getFloat(index, c0018b37.Q);
                    break;
                case 41:
                    C0018b c0018b38 = aVar.f1479d;
                    c0018b38.S = typedArray.getInt(index, c0018b38.S);
                    break;
                case 42:
                    C0018b c0018b39 = aVar.f1479d;
                    c0018b39.T = typedArray.getInt(index, c0018b39.T);
                    break;
                case 43:
                    d dVar3 = aVar.f1477b;
                    dVar3.f1502d = typedArray.getFloat(index, dVar3.f1502d);
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar = aVar.f1480e;
                        eVar.m = true;
                        eVar.n = typedArray.getDimension(index, eVar.n);
                    }
                    break;
                case 45:
                    e eVar2 = aVar.f1480e;
                    eVar2.f1507d = typedArray.getFloat(index, eVar2.f1507d);
                    break;
                case 46:
                    e eVar3 = aVar.f1480e;
                    eVar3.f1508e = typedArray.getFloat(index, eVar3.f1508e);
                    break;
                case 47:
                    e eVar4 = aVar.f1480e;
                    eVar4.f1509f = typedArray.getFloat(index, eVar4.f1509f);
                    break;
                case 48:
                    e eVar5 = aVar.f1480e;
                    eVar5.f1510g = typedArray.getFloat(index, eVar5.f1510g);
                    break;
                case 49:
                    e eVar6 = aVar.f1480e;
                    eVar6.f1511h = typedArray.getDimension(index, eVar6.f1511h);
                    break;
                case 50:
                    e eVar7 = aVar.f1480e;
                    eVar7.f1512i = typedArray.getDimension(index, eVar7.f1512i);
                    break;
                case 51:
                    e eVar8 = aVar.f1480e;
                    eVar8.j = typedArray.getDimension(index, eVar8.j);
                    break;
                case 52:
                    e eVar9 = aVar.f1480e;
                    eVar9.k = typedArray.getDimension(index, eVar9.k);
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar10 = aVar.f1480e;
                        eVar10.l = typedArray.getDimension(index, eVar10.l);
                    }
                    break;
                case 54:
                    C0018b c0018b40 = aVar.f1479d;
                    c0018b40.U = typedArray.getInt(index, c0018b40.U);
                    break;
                case 55:
                    C0018b c0018b41 = aVar.f1479d;
                    c0018b41.V = typedArray.getInt(index, c0018b41.V);
                    break;
                case 56:
                    C0018b c0018b42 = aVar.f1479d;
                    c0018b42.W = typedArray.getDimensionPixelSize(index, c0018b42.W);
                    break;
                case 57:
                    C0018b c0018b43 = aVar.f1479d;
                    c0018b43.X = typedArray.getDimensionPixelSize(index, c0018b43.X);
                    break;
                case 58:
                    C0018b c0018b44 = aVar.f1479d;
                    c0018b44.Y = typedArray.getDimensionPixelSize(index, c0018b44.Y);
                    break;
                case 59:
                    C0018b c0018b45 = aVar.f1479d;
                    c0018b45.Z = typedArray.getDimensionPixelSize(index, c0018b45.Z);
                    break;
                case 60:
                    e eVar11 = aVar.f1480e;
                    eVar11.f1506c = typedArray.getFloat(index, eVar11.f1506c);
                    break;
                case 61:
                    C0018b c0018b46 = aVar.f1479d;
                    c0018b46.y = w(typedArray, index, c0018b46.y);
                    break;
                case 62:
                    C0018b c0018b47 = aVar.f1479d;
                    c0018b47.z = typedArray.getDimensionPixelSize(index, c0018b47.z);
                    break;
                case 63:
                    C0018b c0018b48 = aVar.f1479d;
                    c0018b48.A = typedArray.getFloat(index, c0018b48.A);
                    break;
                case 64:
                    c cVar = aVar.f1478c;
                    cVar.f1493c = w(typedArray, index, cVar.f1493c);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.f1478c.f1494d = typedArray.getString(index);
                    } else {
                        aVar.f1478c.f1494d = b.e.a.a.c.f1198b[typedArray.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    aVar.f1478c.f1496f = typedArray.getInt(index, 0);
                    break;
                case 67:
                    c cVar2 = aVar.f1478c;
                    cVar2.f1498h = typedArray.getFloat(index, cVar2.f1498h);
                    break;
                case 68:
                    d dVar4 = aVar.f1477b;
                    dVar4.f1503e = typedArray.getFloat(index, dVar4.f1503e);
                    break;
                case 69:
                    aVar.f1479d.a0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    aVar.f1479d.b0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    C0018b c0018b49 = aVar.f1479d;
                    c0018b49.c0 = typedArray.getInt(index, c0018b49.c0);
                    break;
                case 73:
                    C0018b c0018b50 = aVar.f1479d;
                    c0018b50.d0 = typedArray.getDimensionPixelSize(index, c0018b50.d0);
                    break;
                case 74:
                    aVar.f1479d.g0 = typedArray.getString(index);
                    break;
                case 75:
                    C0018b c0018b51 = aVar.f1479d;
                    c0018b51.k0 = typedArray.getBoolean(index, c0018b51.k0);
                    break;
                case 76:
                    c cVar3 = aVar.f1478c;
                    cVar3.f1495e = typedArray.getInt(index, cVar3.f1495e);
                    break;
                case 77:
                    aVar.f1479d.h0 = typedArray.getString(index);
                    break;
                case 78:
                    d dVar5 = aVar.f1477b;
                    dVar5.f1501c = typedArray.getInt(index, dVar5.f1501c);
                    break;
                case 79:
                    c cVar4 = aVar.f1478c;
                    cVar4.f1497g = typedArray.getFloat(index, cVar4.f1497g);
                    break;
                case 80:
                    C0018b c0018b52 = aVar.f1479d;
                    c0018b52.i0 = typedArray.getBoolean(index, c0018b52.i0);
                    break;
                case 81:
                    C0018b c0018b53 = aVar.f1479d;
                    c0018b53.j0 = typedArray.getBoolean(index, c0018b53.j0);
                    break;
                case 82:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1471b.get(index));
                    break;
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1471b.get(index));
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
            if (this.f1474e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1475f.containsKey(Integer.valueOf(id))) {
                this.f1475f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f1475f.get(Integer.valueOf(id));
            if (!aVar.f1479d.f1484c) {
                aVar.f(id, bVar);
                if (childAt instanceof ConstraintHelper) {
                    aVar.f1479d.f0 = ((ConstraintHelper) childAt).getReferencedIds();
                    if (childAt instanceof Barrier) {
                        Barrier barrier = (Barrier) childAt;
                        aVar.f1479d.k0 = barrier.v();
                        aVar.f1479d.c0 = barrier.getType();
                        aVar.f1479d.d0 = barrier.getMargin();
                    }
                }
                aVar.f1479d.f1484c = true;
            }
            d dVar = aVar.f1477b;
            if (!dVar.f1499a) {
                dVar.f1500b = childAt.getVisibility();
                aVar.f1477b.f1502d = childAt.getAlpha();
                aVar.f1477b.f1499a = true;
            }
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 17) {
                e eVar = aVar.f1480e;
                if (!eVar.f1505b) {
                    eVar.f1505b = true;
                    eVar.f1506c = childAt.getRotation();
                    aVar.f1480e.f1507d = childAt.getRotationX();
                    aVar.f1480e.f1508e = childAt.getRotationY();
                    aVar.f1480e.f1509f = childAt.getScaleX();
                    aVar.f1480e.f1510g = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        e eVar2 = aVar.f1480e;
                        eVar2.f1511h = pivotX;
                        eVar2.f1512i = pivotY;
                    }
                    aVar.f1480e.j = childAt.getTranslationX();
                    aVar.f1480e.k = childAt.getTranslationY();
                    if (i3 >= 21) {
                        aVar.f1480e.l = childAt.getTranslationZ();
                        e eVar3 = aVar.f1480e;
                        if (eVar3.m) {
                            eVar3.n = childAt.getElevation();
                        }
                    }
                }
            }
        }
    }

    public void z(b bVar) {
        for (Integer num : bVar.f1475f.keySet()) {
            int iIntValue = num.intValue();
            a aVar = bVar.f1475f.get(num);
            if (!this.f1475f.containsKey(Integer.valueOf(iIntValue))) {
                this.f1475f.put(Integer.valueOf(iIntValue), new a());
            }
            a aVar2 = this.f1475f.get(Integer.valueOf(iIntValue));
            C0018b c0018b = aVar2.f1479d;
            if (!c0018b.f1484c) {
                c0018b.a(aVar.f1479d);
            }
            d dVar = aVar2.f1477b;
            if (!dVar.f1499a) {
                dVar.a(aVar.f1477b);
            }
            e eVar = aVar2.f1480e;
            if (!eVar.f1505b) {
                eVar.a(aVar.f1480e);
            }
            c cVar = aVar2.f1478c;
            if (!cVar.f1492b) {
                cVar.a(aVar.f1478c);
            }
            for (String str : aVar.f1481f.keySet()) {
                if (!aVar2.f1481f.containsKey(str)) {
                    aVar2.f1481f.put(str, aVar.f1481f.get(str));
                }
            }
        }
    }
}
