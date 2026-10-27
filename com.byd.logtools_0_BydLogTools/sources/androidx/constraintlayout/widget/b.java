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
    private static final int[] f1133a = {0, 4, 8};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static SparseIntArray f1134b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1135c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HashMap<String, ConstraintAttribute> f1136d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1137e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private HashMap<Integer, a> f1138f = new HashMap<>();

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1139a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f1140b = new d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c f1141c = new c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final C0012b f1142d = new C0012b();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final e f1143e = new e();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public HashMap<String, ConstraintAttribute> f1144f = new HashMap<>();

        /* JADX INFO: Access modifiers changed from: private */
        public void f(int i, ConstraintLayout.b bVar) {
            this.f1139a = i;
            C0012b c0012b = this.f1142d;
            c0012b.i = bVar.f1098d;
            c0012b.j = bVar.f1099e;
            c0012b.k = bVar.f1100f;
            c0012b.l = bVar.f1101g;
            c0012b.m = bVar.f1102h;
            c0012b.n = bVar.i;
            c0012b.o = bVar.j;
            c0012b.p = bVar.k;
            c0012b.q = bVar.l;
            c0012b.r = bVar.p;
            c0012b.s = bVar.q;
            c0012b.t = bVar.r;
            c0012b.u = bVar.s;
            c0012b.v = bVar.z;
            c0012b.w = bVar.A;
            c0012b.x = bVar.B;
            c0012b.y = bVar.m;
            c0012b.z = bVar.n;
            c0012b.A = bVar.o;
            c0012b.B = bVar.Q;
            c0012b.C = bVar.R;
            c0012b.D = bVar.S;
            c0012b.f1152h = bVar.f1097c;
            c0012b.f1150f = bVar.f1095a;
            c0012b.f1151g = bVar.f1096b;
            C0012b c0012b2 = this.f1142d;
            c0012b2.f1148d = ((ViewGroup.MarginLayoutParams) bVar).width;
            c0012b2.f1149e = ((ViewGroup.MarginLayoutParams) bVar).height;
            c0012b2.E = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            c0012b2.F = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
            c0012b2.G = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            c0012b2.H = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
            c0012b2.Q = bVar.F;
            c0012b2.R = bVar.E;
            c0012b2.T = bVar.H;
            c0012b2.S = bVar.G;
            c0012b2.i0 = bVar.T;
            c0012b2.j0 = bVar.U;
            c0012b2.U = bVar.I;
            c0012b2.V = bVar.J;
            c0012b2.W = bVar.M;
            c0012b2.X = bVar.N;
            c0012b2.Y = bVar.K;
            c0012b2.Z = bVar.L;
            c0012b2.a0 = bVar.O;
            c0012b2.b0 = bVar.P;
            c0012b2.h0 = bVar.V;
            c0012b2.L = bVar.u;
            c0012b2.N = bVar.w;
            c0012b2.K = bVar.t;
            c0012b2.M = bVar.v;
            C0012b c0012b3 = this.f1142d;
            c0012b3.P = bVar.x;
            c0012b3.O = bVar.y;
            if (Build.VERSION.SDK_INT >= 17) {
                c0012b3.I = bVar.getMarginEnd();
                this.f1142d.J = bVar.getMarginStart();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g(int i, Constraints.a aVar) {
            f(i, aVar);
            this.f1140b.f1164d = aVar.p0;
            e eVar = this.f1143e;
            eVar.f1168c = aVar.s0;
            eVar.f1169d = aVar.t0;
            eVar.f1170e = aVar.u0;
            eVar.f1171f = aVar.v0;
            eVar.f1172g = aVar.w0;
            eVar.f1173h = aVar.x0;
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
                C0012b c0012b = this.f1142d;
                c0012b.e0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                c0012b.c0 = barrier.getType();
                this.f1142d.f0 = barrier.getReferencedIds();
                this.f1142d.d0 = barrier.getMargin();
            }
        }

        public void d(ConstraintLayout.b bVar) {
            C0012b c0012b = this.f1142d;
            bVar.f1098d = c0012b.i;
            bVar.f1099e = c0012b.j;
            bVar.f1100f = c0012b.k;
            bVar.f1101g = c0012b.l;
            bVar.f1102h = c0012b.m;
            bVar.i = c0012b.n;
            bVar.j = c0012b.o;
            bVar.k = c0012b.p;
            bVar.l = c0012b.q;
            bVar.p = c0012b.r;
            bVar.q = c0012b.s;
            bVar.r = c0012b.t;
            bVar.s = c0012b.u;
            ((ViewGroup.MarginLayoutParams) bVar).leftMargin = c0012b.E;
            ((ViewGroup.MarginLayoutParams) bVar).rightMargin = c0012b.F;
            ((ViewGroup.MarginLayoutParams) bVar).topMargin = c0012b.G;
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = c0012b.H;
            bVar.x = c0012b.P;
            bVar.y = c0012b.O;
            bVar.u = c0012b.L;
            bVar.w = c0012b.N;
            bVar.z = c0012b.v;
            bVar.A = c0012b.w;
            bVar.m = c0012b.y;
            bVar.n = c0012b.z;
            C0012b c0012b2 = this.f1142d;
            bVar.o = c0012b2.A;
            bVar.B = c0012b2.x;
            bVar.Q = c0012b2.B;
            bVar.R = c0012b2.C;
            bVar.F = c0012b2.Q;
            bVar.E = c0012b2.R;
            bVar.H = c0012b2.T;
            bVar.G = c0012b2.S;
            bVar.T = c0012b2.i0;
            bVar.U = c0012b2.j0;
            bVar.I = c0012b2.U;
            bVar.J = c0012b2.V;
            bVar.M = c0012b2.W;
            bVar.N = c0012b2.X;
            bVar.K = c0012b2.Y;
            bVar.L = c0012b2.Z;
            bVar.O = c0012b2.a0;
            bVar.P = c0012b2.b0;
            bVar.S = c0012b2.D;
            bVar.f1097c = c0012b2.f1152h;
            bVar.f1095a = c0012b2.f1150f;
            bVar.f1096b = c0012b2.f1151g;
            ((ViewGroup.MarginLayoutParams) bVar).width = c0012b2.f1148d;
            ((ViewGroup.MarginLayoutParams) bVar).height = c0012b2.f1149e;
            String str = c0012b2.h0;
            if (str != null) {
                bVar.V = str;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                bVar.setMarginStart(this.f1142d.J);
                bVar.setMarginEnd(this.f1142d.I);
            }
            bVar.c();
        }

        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.f1142d.a(this.f1142d);
            aVar.f1141c.a(this.f1141c);
            aVar.f1140b.a(this.f1140b);
            aVar.f1143e.a(this.f1143e);
            aVar.f1139a = this.f1139a;
            return aVar;
        }
    }

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class C0012b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1145a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1148d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1149e;
        public int[] f0;
        public String g0;
        public String h0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1146b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1147c = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1150f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1151g = -1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1152h = -1.0f;
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
            f1145a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.O5, 24);
            f1145a.append(androidx.constraintlayout.widget.e.P5, 25);
            f1145a.append(androidx.constraintlayout.widget.e.R5, 28);
            f1145a.append(androidx.constraintlayout.widget.e.S5, 29);
            f1145a.append(androidx.constraintlayout.widget.e.X5, 35);
            f1145a.append(androidx.constraintlayout.widget.e.W5, 34);
            f1145a.append(androidx.constraintlayout.widget.e.z5, 4);
            f1145a.append(androidx.constraintlayout.widget.e.y5, 3);
            f1145a.append(androidx.constraintlayout.widget.e.w5, 1);
            f1145a.append(androidx.constraintlayout.widget.e.c6, 6);
            f1145a.append(androidx.constraintlayout.widget.e.d6, 7);
            f1145a.append(androidx.constraintlayout.widget.e.G5, 17);
            f1145a.append(androidx.constraintlayout.widget.e.H5, 18);
            f1145a.append(androidx.constraintlayout.widget.e.I5, 19);
            f1145a.append(androidx.constraintlayout.widget.e.h5, 26);
            f1145a.append(androidx.constraintlayout.widget.e.T5, 31);
            f1145a.append(androidx.constraintlayout.widget.e.U5, 32);
            f1145a.append(androidx.constraintlayout.widget.e.F5, 10);
            f1145a.append(androidx.constraintlayout.widget.e.E5, 9);
            f1145a.append(androidx.constraintlayout.widget.e.g6, 13);
            f1145a.append(androidx.constraintlayout.widget.e.j6, 16);
            f1145a.append(androidx.constraintlayout.widget.e.h6, 14);
            f1145a.append(androidx.constraintlayout.widget.e.e6, 11);
            f1145a.append(androidx.constraintlayout.widget.e.i6, 15);
            f1145a.append(androidx.constraintlayout.widget.e.f6, 12);
            f1145a.append(androidx.constraintlayout.widget.e.a6, 38);
            f1145a.append(androidx.constraintlayout.widget.e.M5, 37);
            f1145a.append(androidx.constraintlayout.widget.e.L5, 39);
            f1145a.append(androidx.constraintlayout.widget.e.Z5, 40);
            f1145a.append(androidx.constraintlayout.widget.e.K5, 20);
            f1145a.append(androidx.constraintlayout.widget.e.Y5, 36);
            f1145a.append(androidx.constraintlayout.widget.e.D5, 5);
            f1145a.append(androidx.constraintlayout.widget.e.N5, 76);
            f1145a.append(androidx.constraintlayout.widget.e.V5, 76);
            f1145a.append(androidx.constraintlayout.widget.e.Q5, 76);
            f1145a.append(androidx.constraintlayout.widget.e.x5, 76);
            f1145a.append(androidx.constraintlayout.widget.e.v5, 76);
            f1145a.append(androidx.constraintlayout.widget.e.k5, 23);
            f1145a.append(androidx.constraintlayout.widget.e.m5, 27);
            f1145a.append(androidx.constraintlayout.widget.e.o5, 30);
            f1145a.append(androidx.constraintlayout.widget.e.p5, 8);
            f1145a.append(androidx.constraintlayout.widget.e.l5, 33);
            f1145a.append(androidx.constraintlayout.widget.e.n5, 2);
            f1145a.append(androidx.constraintlayout.widget.e.i5, 22);
            f1145a.append(androidx.constraintlayout.widget.e.j5, 21);
            f1145a.append(androidx.constraintlayout.widget.e.A5, 61);
            f1145a.append(androidx.constraintlayout.widget.e.C5, 62);
            f1145a.append(androidx.constraintlayout.widget.e.B5, 63);
            f1145a.append(androidx.constraintlayout.widget.e.b6, 69);
            f1145a.append(androidx.constraintlayout.widget.e.J5, 70);
            f1145a.append(androidx.constraintlayout.widget.e.t5, 71);
            f1145a.append(androidx.constraintlayout.widget.e.r5, 72);
            f1145a.append(androidx.constraintlayout.widget.e.s5, 73);
            f1145a.append(androidx.constraintlayout.widget.e.u5, 74);
            f1145a.append(androidx.constraintlayout.widget.e.q5, 75);
        }

        public void a(C0012b c0012b) {
            this.f1146b = c0012b.f1146b;
            this.f1148d = c0012b.f1148d;
            this.f1147c = c0012b.f1147c;
            this.f1149e = c0012b.f1149e;
            this.f1150f = c0012b.f1150f;
            this.f1151g = c0012b.f1151g;
            this.f1152h = c0012b.f1152h;
            this.i = c0012b.i;
            this.j = c0012b.j;
            this.k = c0012b.k;
            this.l = c0012b.l;
            this.m = c0012b.m;
            this.n = c0012b.n;
            this.o = c0012b.o;
            this.p = c0012b.p;
            this.q = c0012b.q;
            this.r = c0012b.r;
            this.s = c0012b.s;
            this.t = c0012b.t;
            this.u = c0012b.u;
            this.v = c0012b.v;
            this.w = c0012b.w;
            this.x = c0012b.x;
            this.y = c0012b.y;
            this.z = c0012b.z;
            this.A = c0012b.A;
            this.B = c0012b.B;
            this.C = c0012b.C;
            this.D = c0012b.D;
            this.E = c0012b.E;
            this.F = c0012b.F;
            this.G = c0012b.G;
            this.H = c0012b.H;
            this.I = c0012b.I;
            this.J = c0012b.J;
            this.K = c0012b.K;
            this.L = c0012b.L;
            this.M = c0012b.M;
            this.N = c0012b.N;
            this.O = c0012b.O;
            this.P = c0012b.P;
            this.Q = c0012b.Q;
            this.R = c0012b.R;
            this.S = c0012b.S;
            this.T = c0012b.T;
            this.U = c0012b.U;
            this.V = c0012b.V;
            this.W = c0012b.W;
            this.X = c0012b.X;
            this.Y = c0012b.Y;
            this.Z = c0012b.Z;
            this.a0 = c0012b.a0;
            this.b0 = c0012b.b0;
            this.c0 = c0012b.c0;
            this.d0 = c0012b.d0;
            this.e0 = c0012b.e0;
            this.h0 = c0012b.h0;
            int[] iArr = c0012b.f0;
            if (iArr != null) {
                this.f0 = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.f0 = null;
            }
            this.g0 = c0012b.g0;
            this.i0 = c0012b.i0;
            this.j0 = c0012b.j0;
            this.k0 = c0012b.k0;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.g5);
            this.f1147c = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                int i2 = f1145a.get(index);
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
                            this.f1150f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1150f);
                            break;
                        case 18:
                            this.f1151g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1151g);
                            break;
                        case 19:
                            this.f1152h = typedArrayObtainStyledAttributes.getFloat(index, this.f1152h);
                            break;
                        case 20:
                            this.v = typedArrayObtainStyledAttributes.getFloat(index, this.v);
                            break;
                        case 21:
                            this.f1149e = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f1149e);
                            break;
                        case 22:
                            this.f1148d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f1148d);
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
                                                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1145a.get(index));
                                                    break;
                                                case 77:
                                                    this.h0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                default:
                                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1145a.get(index));
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
        private static SparseIntArray f1153a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1154b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1155c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f1156d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1157e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1158f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f1159g = Float.NaN;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1160h = Float.NaN;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1153a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.A6, 1);
            f1153a.append(androidx.constraintlayout.widget.e.C6, 2);
            f1153a.append(androidx.constraintlayout.widget.e.D6, 3);
            f1153a.append(androidx.constraintlayout.widget.e.z6, 4);
            f1153a.append(androidx.constraintlayout.widget.e.y6, 5);
            f1153a.append(androidx.constraintlayout.widget.e.B6, 6);
        }

        public void a(c cVar) {
            this.f1154b = cVar.f1154b;
            this.f1155c = cVar.f1155c;
            this.f1156d = cVar.f1156d;
            this.f1157e = cVar.f1157e;
            this.f1158f = cVar.f1158f;
            this.f1160h = cVar.f1160h;
            this.f1159g = cVar.f1159g;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.x6);
            this.f1154b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (f1153a.get(index)) {
                    case 1:
                        this.f1160h = typedArrayObtainStyledAttributes.getFloat(index, this.f1160h);
                        break;
                    case 2:
                        this.f1157e = typedArrayObtainStyledAttributes.getInt(index, this.f1157e);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.f1156d = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.f1156d = b.e.a.a.c.f2318b[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.f1158f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.f1155c = b.w(typedArrayObtainStyledAttributes, index, this.f1155c);
                        break;
                    case 6:
                        this.f1159g = typedArrayObtainStyledAttributes.getFloat(index, this.f1159g);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1161a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1162b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1163c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1164d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1165e = Float.NaN;

        public void a(d dVar) {
            this.f1161a = dVar.f1161a;
            this.f1162b = dVar.f1162b;
            this.f1164d = dVar.f1164d;
            this.f1165e = dVar.f1165e;
            this.f1163c = dVar.f1163c;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.n7);
            this.f1161a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == androidx.constraintlayout.widget.e.p7) {
                    this.f1164d = typedArrayObtainStyledAttributes.getFloat(index, this.f1164d);
                } else if (index == androidx.constraintlayout.widget.e.o7) {
                    this.f1162b = typedArrayObtainStyledAttributes.getInt(index, this.f1162b);
                    this.f1162b = b.f1133a[this.f1162b];
                } else if (index == androidx.constraintlayout.widget.e.r7) {
                    this.f1163c = typedArrayObtainStyledAttributes.getInt(index, this.f1163c);
                } else if (index == androidx.constraintlayout.widget.e.q7) {
                    this.f1165e = typedArrayObtainStyledAttributes.getFloat(index, this.f1165e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1166a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1167b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1168c = 0.0f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1169d = 0.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1170e = 0.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f1171f = 1.0f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f1172g = 1.0f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1173h = Float.NaN;
        public float i = Float.NaN;
        public float j = 0.0f;
        public float k = 0.0f;
        public float l = 0.0f;
        public boolean m = false;
        public float n = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1166a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.M7, 1);
            f1166a.append(androidx.constraintlayout.widget.e.N7, 2);
            f1166a.append(androidx.constraintlayout.widget.e.O7, 3);
            f1166a.append(androidx.constraintlayout.widget.e.K7, 4);
            f1166a.append(androidx.constraintlayout.widget.e.L7, 5);
            f1166a.append(androidx.constraintlayout.widget.e.G7, 6);
            f1166a.append(androidx.constraintlayout.widget.e.H7, 7);
            f1166a.append(androidx.constraintlayout.widget.e.I7, 8);
            f1166a.append(androidx.constraintlayout.widget.e.J7, 9);
            f1166a.append(androidx.constraintlayout.widget.e.P7, 10);
            f1166a.append(androidx.constraintlayout.widget.e.Q7, 11);
        }

        public void a(e eVar) {
            this.f1167b = eVar.f1167b;
            this.f1168c = eVar.f1168c;
            this.f1169d = eVar.f1169d;
            this.f1170e = eVar.f1170e;
            this.f1171f = eVar.f1171f;
            this.f1172g = eVar.f1172g;
            this.f1173h = eVar.f1173h;
            this.i = eVar.i;
            this.j = eVar.j;
            this.k = eVar.k;
            this.l = eVar.l;
            this.m = eVar.m;
            this.n = eVar.n;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.F7);
            this.f1167b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (f1166a.get(index)) {
                    case 1:
                        this.f1168c = typedArrayObtainStyledAttributes.getFloat(index, this.f1168c);
                        break;
                    case 2:
                        this.f1169d = typedArrayObtainStyledAttributes.getFloat(index, this.f1169d);
                        break;
                    case 3:
                        this.f1170e = typedArrayObtainStyledAttributes.getFloat(index, this.f1170e);
                        break;
                    case 4:
                        this.f1171f = typedArrayObtainStyledAttributes.getFloat(index, this.f1171f);
                        break;
                    case 5:
                        this.f1172g = typedArrayObtainStyledAttributes.getFloat(index, this.f1172g);
                        break;
                    case 6:
                        this.f1173h = typedArrayObtainStyledAttributes.getDimension(index, this.f1173h);
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
        f1134b = sparseIntArray;
        sparseIntArray.append(androidx.constraintlayout.widget.e.u0, 25);
        f1134b.append(androidx.constraintlayout.widget.e.v0, 26);
        f1134b.append(androidx.constraintlayout.widget.e.x0, 29);
        f1134b.append(androidx.constraintlayout.widget.e.y0, 30);
        f1134b.append(androidx.constraintlayout.widget.e.E0, 36);
        f1134b.append(androidx.constraintlayout.widget.e.D0, 35);
        f1134b.append(androidx.constraintlayout.widget.e.c0, 4);
        f1134b.append(androidx.constraintlayout.widget.e.b0, 3);
        f1134b.append(androidx.constraintlayout.widget.e.Z, 1);
        f1134b.append(androidx.constraintlayout.widget.e.M0, 6);
        f1134b.append(androidx.constraintlayout.widget.e.N0, 7);
        f1134b.append(androidx.constraintlayout.widget.e.j0, 17);
        f1134b.append(androidx.constraintlayout.widget.e.k0, 18);
        f1134b.append(androidx.constraintlayout.widget.e.l0, 19);
        f1134b.append(androidx.constraintlayout.widget.e.s, 27);
        f1134b.append(androidx.constraintlayout.widget.e.z0, 32);
        f1134b.append(androidx.constraintlayout.widget.e.A0, 33);
        f1134b.append(androidx.constraintlayout.widget.e.i0, 10);
        f1134b.append(androidx.constraintlayout.widget.e.h0, 9);
        f1134b.append(androidx.constraintlayout.widget.e.Q0, 13);
        f1134b.append(androidx.constraintlayout.widget.e.T0, 16);
        f1134b.append(androidx.constraintlayout.widget.e.R0, 14);
        f1134b.append(androidx.constraintlayout.widget.e.O0, 11);
        f1134b.append(androidx.constraintlayout.widget.e.S0, 15);
        f1134b.append(androidx.constraintlayout.widget.e.P0, 12);
        f1134b.append(androidx.constraintlayout.widget.e.H0, 40);
        f1134b.append(androidx.constraintlayout.widget.e.s0, 39);
        f1134b.append(androidx.constraintlayout.widget.e.r0, 41);
        f1134b.append(androidx.constraintlayout.widget.e.G0, 42);
        f1134b.append(androidx.constraintlayout.widget.e.q0, 20);
        f1134b.append(androidx.constraintlayout.widget.e.F0, 37);
        f1134b.append(androidx.constraintlayout.widget.e.g0, 5);
        f1134b.append(androidx.constraintlayout.widget.e.t0, 82);
        f1134b.append(androidx.constraintlayout.widget.e.C0, 82);
        f1134b.append(androidx.constraintlayout.widget.e.w0, 82);
        f1134b.append(androidx.constraintlayout.widget.e.a0, 82);
        f1134b.append(androidx.constraintlayout.widget.e.Y, 82);
        f1134b.append(androidx.constraintlayout.widget.e.x, 24);
        f1134b.append(androidx.constraintlayout.widget.e.z, 28);
        f1134b.append(androidx.constraintlayout.widget.e.L, 31);
        f1134b.append(androidx.constraintlayout.widget.e.M, 8);
        f1134b.append(androidx.constraintlayout.widget.e.y, 34);
        f1134b.append(androidx.constraintlayout.widget.e.A, 2);
        f1134b.append(androidx.constraintlayout.widget.e.v, 23);
        f1134b.append(androidx.constraintlayout.widget.e.w, 21);
        f1134b.append(androidx.constraintlayout.widget.e.u, 22);
        f1134b.append(androidx.constraintlayout.widget.e.B, 43);
        f1134b.append(androidx.constraintlayout.widget.e.O, 44);
        f1134b.append(androidx.constraintlayout.widget.e.J, 45);
        f1134b.append(androidx.constraintlayout.widget.e.K, 46);
        f1134b.append(androidx.constraintlayout.widget.e.I, 60);
        f1134b.append(androidx.constraintlayout.widget.e.G, 47);
        f1134b.append(androidx.constraintlayout.widget.e.H, 48);
        f1134b.append(androidx.constraintlayout.widget.e.C, 49);
        f1134b.append(androidx.constraintlayout.widget.e.D, 50);
        f1134b.append(androidx.constraintlayout.widget.e.E, 51);
        f1134b.append(androidx.constraintlayout.widget.e.F, 52);
        f1134b.append(androidx.constraintlayout.widget.e.N, 53);
        f1134b.append(androidx.constraintlayout.widget.e.I0, 54);
        f1134b.append(androidx.constraintlayout.widget.e.m0, 55);
        f1134b.append(androidx.constraintlayout.widget.e.J0, 56);
        f1134b.append(androidx.constraintlayout.widget.e.n0, 57);
        f1134b.append(androidx.constraintlayout.widget.e.K0, 58);
        f1134b.append(androidx.constraintlayout.widget.e.o0, 59);
        f1134b.append(androidx.constraintlayout.widget.e.d0, 61);
        f1134b.append(androidx.constraintlayout.widget.e.f0, 62);
        f1134b.append(androidx.constraintlayout.widget.e.e0, 63);
        f1134b.append(androidx.constraintlayout.widget.e.P, 64);
        f1134b.append(androidx.constraintlayout.widget.e.X0, 65);
        f1134b.append(androidx.constraintlayout.widget.e.V, 66);
        f1134b.append(androidx.constraintlayout.widget.e.Y0, 67);
        f1134b.append(androidx.constraintlayout.widget.e.V0, 79);
        f1134b.append(androidx.constraintlayout.widget.e.t, 38);
        f1134b.append(androidx.constraintlayout.widget.e.U0, 68);
        f1134b.append(androidx.constraintlayout.widget.e.L0, 69);
        f1134b.append(androidx.constraintlayout.widget.e.p0, 70);
        f1134b.append(androidx.constraintlayout.widget.e.T, 71);
        f1134b.append(androidx.constraintlayout.widget.e.R, 72);
        f1134b.append(androidx.constraintlayout.widget.e.S, 73);
        f1134b.append(androidx.constraintlayout.widget.e.U, 74);
        f1134b.append(androidx.constraintlayout.widget.e.Q, 75);
        f1134b.append(androidx.constraintlayout.widget.e.W0, 76);
        f1134b.append(androidx.constraintlayout.widget.e.B0, 77);
        f1134b.append(androidx.constraintlayout.widget.e.Z0, 78);
        f1134b.append(androidx.constraintlayout.widget.e.X, 80);
        f1134b.append(androidx.constraintlayout.widget.e.W, 81);
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
        if (!this.f1138f.containsKey(Integer.valueOf(i))) {
            this.f1138f.put(Integer.valueOf(i), new a());
        }
        return this.f1138f.get(Integer.valueOf(i));
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
                aVar.f1141c.f1154b = true;
                aVar.f1142d.f1147c = true;
                aVar.f1140b.f1161a = true;
                aVar.f1143e.f1167b = true;
            }
            switch (f1134b.get(index)) {
                case 1:
                    C0012b c0012b = aVar.f1142d;
                    c0012b.q = w(typedArray, index, c0012b.q);
                    break;
                case 2:
                    C0012b c0012b2 = aVar.f1142d;
                    c0012b2.H = typedArray.getDimensionPixelSize(index, c0012b2.H);
                    break;
                case 3:
                    C0012b c0012b3 = aVar.f1142d;
                    c0012b3.p = w(typedArray, index, c0012b3.p);
                    break;
                case 4:
                    C0012b c0012b4 = aVar.f1142d;
                    c0012b4.o = w(typedArray, index, c0012b4.o);
                    break;
                case 5:
                    aVar.f1142d.x = typedArray.getString(index);
                    break;
                case 6:
                    C0012b c0012b5 = aVar.f1142d;
                    c0012b5.B = typedArray.getDimensionPixelOffset(index, c0012b5.B);
                    break;
                case 7:
                    C0012b c0012b6 = aVar.f1142d;
                    c0012b6.C = typedArray.getDimensionPixelOffset(index, c0012b6.C);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0012b c0012b7 = aVar.f1142d;
                        c0012b7.I = typedArray.getDimensionPixelSize(index, c0012b7.I);
                    }
                    break;
                case 9:
                    C0012b c0012b8 = aVar.f1142d;
                    c0012b8.u = w(typedArray, index, c0012b8.u);
                    break;
                case 10:
                    C0012b c0012b9 = aVar.f1142d;
                    c0012b9.t = w(typedArray, index, c0012b9.t);
                    break;
                case 11:
                    C0012b c0012b10 = aVar.f1142d;
                    c0012b10.N = typedArray.getDimensionPixelSize(index, c0012b10.N);
                    break;
                case 12:
                    C0012b c0012b11 = aVar.f1142d;
                    c0012b11.O = typedArray.getDimensionPixelSize(index, c0012b11.O);
                    break;
                case 13:
                    C0012b c0012b12 = aVar.f1142d;
                    c0012b12.K = typedArray.getDimensionPixelSize(index, c0012b12.K);
                    break;
                case 14:
                    C0012b c0012b13 = aVar.f1142d;
                    c0012b13.M = typedArray.getDimensionPixelSize(index, c0012b13.M);
                    break;
                case 15:
                    C0012b c0012b14 = aVar.f1142d;
                    c0012b14.P = typedArray.getDimensionPixelSize(index, c0012b14.P);
                    break;
                case 16:
                    C0012b c0012b15 = aVar.f1142d;
                    c0012b15.L = typedArray.getDimensionPixelSize(index, c0012b15.L);
                    break;
                case 17:
                    C0012b c0012b16 = aVar.f1142d;
                    c0012b16.f1150f = typedArray.getDimensionPixelOffset(index, c0012b16.f1150f);
                    break;
                case 18:
                    C0012b c0012b17 = aVar.f1142d;
                    c0012b17.f1151g = typedArray.getDimensionPixelOffset(index, c0012b17.f1151g);
                    break;
                case 19:
                    C0012b c0012b18 = aVar.f1142d;
                    c0012b18.f1152h = typedArray.getFloat(index, c0012b18.f1152h);
                    break;
                case 20:
                    C0012b c0012b19 = aVar.f1142d;
                    c0012b19.v = typedArray.getFloat(index, c0012b19.v);
                    break;
                case 21:
                    C0012b c0012b20 = aVar.f1142d;
                    c0012b20.f1149e = typedArray.getLayoutDimension(index, c0012b20.f1149e);
                    break;
                case 22:
                    d dVar = aVar.f1140b;
                    dVar.f1162b = typedArray.getInt(index, dVar.f1162b);
                    d dVar2 = aVar.f1140b;
                    dVar2.f1162b = f1133a[dVar2.f1162b];
                    break;
                case 23:
                    C0012b c0012b21 = aVar.f1142d;
                    c0012b21.f1148d = typedArray.getLayoutDimension(index, c0012b21.f1148d);
                    break;
                case 24:
                    C0012b c0012b22 = aVar.f1142d;
                    c0012b22.E = typedArray.getDimensionPixelSize(index, c0012b22.E);
                    break;
                case 25:
                    C0012b c0012b23 = aVar.f1142d;
                    c0012b23.i = w(typedArray, index, c0012b23.i);
                    break;
                case 26:
                    C0012b c0012b24 = aVar.f1142d;
                    c0012b24.j = w(typedArray, index, c0012b24.j);
                    break;
                case 27:
                    C0012b c0012b25 = aVar.f1142d;
                    c0012b25.D = typedArray.getInt(index, c0012b25.D);
                    break;
                case 28:
                    C0012b c0012b26 = aVar.f1142d;
                    c0012b26.F = typedArray.getDimensionPixelSize(index, c0012b26.F);
                    break;
                case 29:
                    C0012b c0012b27 = aVar.f1142d;
                    c0012b27.k = w(typedArray, index, c0012b27.k);
                    break;
                case 30:
                    C0012b c0012b28 = aVar.f1142d;
                    c0012b28.l = w(typedArray, index, c0012b28.l);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0012b c0012b29 = aVar.f1142d;
                        c0012b29.J = typedArray.getDimensionPixelSize(index, c0012b29.J);
                    }
                    break;
                case 32:
                    C0012b c0012b30 = aVar.f1142d;
                    c0012b30.r = w(typedArray, index, c0012b30.r);
                    break;
                case 33:
                    C0012b c0012b31 = aVar.f1142d;
                    c0012b31.s = w(typedArray, index, c0012b31.s);
                    break;
                case 34:
                    C0012b c0012b32 = aVar.f1142d;
                    c0012b32.G = typedArray.getDimensionPixelSize(index, c0012b32.G);
                    break;
                case 35:
                    C0012b c0012b33 = aVar.f1142d;
                    c0012b33.n = w(typedArray, index, c0012b33.n);
                    break;
                case 36:
                    C0012b c0012b34 = aVar.f1142d;
                    c0012b34.m = w(typedArray, index, c0012b34.m);
                    break;
                case 37:
                    C0012b c0012b35 = aVar.f1142d;
                    c0012b35.w = typedArray.getFloat(index, c0012b35.w);
                    break;
                case 38:
                    aVar.f1139a = typedArray.getResourceId(index, aVar.f1139a);
                    break;
                case 39:
                    C0012b c0012b36 = aVar.f1142d;
                    c0012b36.R = typedArray.getFloat(index, c0012b36.R);
                    break;
                case 40:
                    C0012b c0012b37 = aVar.f1142d;
                    c0012b37.Q = typedArray.getFloat(index, c0012b37.Q);
                    break;
                case 41:
                    C0012b c0012b38 = aVar.f1142d;
                    c0012b38.S = typedArray.getInt(index, c0012b38.S);
                    break;
                case 42:
                    C0012b c0012b39 = aVar.f1142d;
                    c0012b39.T = typedArray.getInt(index, c0012b39.T);
                    break;
                case 43:
                    d dVar3 = aVar.f1140b;
                    dVar3.f1164d = typedArray.getFloat(index, dVar3.f1164d);
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar = aVar.f1143e;
                        eVar.m = true;
                        eVar.n = typedArray.getDimension(index, eVar.n);
                    }
                    break;
                case 45:
                    e eVar2 = aVar.f1143e;
                    eVar2.f1169d = typedArray.getFloat(index, eVar2.f1169d);
                    break;
                case 46:
                    e eVar3 = aVar.f1143e;
                    eVar3.f1170e = typedArray.getFloat(index, eVar3.f1170e);
                    break;
                case 47:
                    e eVar4 = aVar.f1143e;
                    eVar4.f1171f = typedArray.getFloat(index, eVar4.f1171f);
                    break;
                case 48:
                    e eVar5 = aVar.f1143e;
                    eVar5.f1172g = typedArray.getFloat(index, eVar5.f1172g);
                    break;
                case 49:
                    e eVar6 = aVar.f1143e;
                    eVar6.f1173h = typedArray.getDimension(index, eVar6.f1173h);
                    break;
                case 50:
                    e eVar7 = aVar.f1143e;
                    eVar7.i = typedArray.getDimension(index, eVar7.i);
                    break;
                case 51:
                    e eVar8 = aVar.f1143e;
                    eVar8.j = typedArray.getDimension(index, eVar8.j);
                    break;
                case 52:
                    e eVar9 = aVar.f1143e;
                    eVar9.k = typedArray.getDimension(index, eVar9.k);
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar10 = aVar.f1143e;
                        eVar10.l = typedArray.getDimension(index, eVar10.l);
                    }
                    break;
                case 54:
                    C0012b c0012b40 = aVar.f1142d;
                    c0012b40.U = typedArray.getInt(index, c0012b40.U);
                    break;
                case 55:
                    C0012b c0012b41 = aVar.f1142d;
                    c0012b41.V = typedArray.getInt(index, c0012b41.V);
                    break;
                case 56:
                    C0012b c0012b42 = aVar.f1142d;
                    c0012b42.W = typedArray.getDimensionPixelSize(index, c0012b42.W);
                    break;
                case 57:
                    C0012b c0012b43 = aVar.f1142d;
                    c0012b43.X = typedArray.getDimensionPixelSize(index, c0012b43.X);
                    break;
                case 58:
                    C0012b c0012b44 = aVar.f1142d;
                    c0012b44.Y = typedArray.getDimensionPixelSize(index, c0012b44.Y);
                    break;
                case 59:
                    C0012b c0012b45 = aVar.f1142d;
                    c0012b45.Z = typedArray.getDimensionPixelSize(index, c0012b45.Z);
                    break;
                case 60:
                    e eVar11 = aVar.f1143e;
                    eVar11.f1168c = typedArray.getFloat(index, eVar11.f1168c);
                    break;
                case 61:
                    C0012b c0012b46 = aVar.f1142d;
                    c0012b46.y = w(typedArray, index, c0012b46.y);
                    break;
                case 62:
                    C0012b c0012b47 = aVar.f1142d;
                    c0012b47.z = typedArray.getDimensionPixelSize(index, c0012b47.z);
                    break;
                case 63:
                    C0012b c0012b48 = aVar.f1142d;
                    c0012b48.A = typedArray.getFloat(index, c0012b48.A);
                    break;
                case 64:
                    c cVar = aVar.f1141c;
                    cVar.f1155c = w(typedArray, index, cVar.f1155c);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.f1141c.f1156d = typedArray.getString(index);
                    } else {
                        aVar.f1141c.f1156d = b.e.a.a.c.f2318b[typedArray.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    aVar.f1141c.f1158f = typedArray.getInt(index, 0);
                    break;
                case 67:
                    c cVar2 = aVar.f1141c;
                    cVar2.f1160h = typedArray.getFloat(index, cVar2.f1160h);
                    break;
                case 68:
                    d dVar4 = aVar.f1140b;
                    dVar4.f1165e = typedArray.getFloat(index, dVar4.f1165e);
                    break;
                case 69:
                    aVar.f1142d.a0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    aVar.f1142d.b0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    C0012b c0012b49 = aVar.f1142d;
                    c0012b49.c0 = typedArray.getInt(index, c0012b49.c0);
                    break;
                case 73:
                    C0012b c0012b50 = aVar.f1142d;
                    c0012b50.d0 = typedArray.getDimensionPixelSize(index, c0012b50.d0);
                    break;
                case 74:
                    aVar.f1142d.g0 = typedArray.getString(index);
                    break;
                case 75:
                    C0012b c0012b51 = aVar.f1142d;
                    c0012b51.k0 = typedArray.getBoolean(index, c0012b51.k0);
                    break;
                case 76:
                    c cVar3 = aVar.f1141c;
                    cVar3.f1157e = typedArray.getInt(index, cVar3.f1157e);
                    break;
                case 77:
                    aVar.f1142d.h0 = typedArray.getString(index);
                    break;
                case 78:
                    d dVar5 = aVar.f1140b;
                    dVar5.f1163c = typedArray.getInt(index, dVar5.f1163c);
                    break;
                case 79:
                    c cVar4 = aVar.f1141c;
                    cVar4.f1159g = typedArray.getFloat(index, cVar4.f1159g);
                    break;
                case 80:
                    C0012b c0012b52 = aVar.f1142d;
                    c0012b52.i0 = typedArray.getBoolean(index, c0012b52.i0);
                    break;
                case 81:
                    C0012b c0012b53 = aVar.f1142d;
                    c0012b53.j0 = typedArray.getBoolean(index, c0012b53.j0);
                    break;
                case 82:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1134b.get(index));
                    break;
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1134b.get(index));
                    break;
            }
        }
    }

    public void A(boolean z) {
        this.f1137e = z;
    }

    public void B(boolean z) {
        this.f1135c = z;
    }

    public void c(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.f1138f.containsKey(Integer.valueOf(id))) {
                Log.v("ConstraintSet", "id unknown " + androidx.constraintlayout.motion.widget.a.c(childAt));
            } else {
                if (this.f1137e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (this.f1138f.containsKey(Integer.valueOf(id))) {
                    ConstraintAttribute.h(childAt, this.f1138f.get(Integer.valueOf(id)).f1144f);
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
        if (this.f1138f.containsKey(Integer.valueOf(id))) {
            a aVar = this.f1138f.get(Integer.valueOf(id));
            if (constraintWidget instanceof h) {
                constraintHelper.n(aVar, (h) constraintWidget, bVar, sparseArray);
            }
        }
    }

    void f(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.f1138f.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.f1138f.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + androidx.constraintlayout.motion.widget.a.c(childAt));
            } else {
                if (this.f1137e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.f1138f.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = this.f1138f.get(Integer.valueOf(id));
                        if (childAt instanceof Barrier) {
                            aVar.f1142d.e0 = 1;
                        }
                        int i2 = aVar.f1142d.e0;
                        if (i2 != -1 && i2 == 1) {
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(aVar.f1142d.c0);
                            barrier.setMargin(aVar.f1142d.d0);
                            barrier.setAllowsGoneWidget(aVar.f1142d.k0);
                            C0012b c0012b = aVar.f1142d;
                            int[] iArr = c0012b.f0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = c0012b.g0;
                                if (str != null) {
                                    c0012b.f0 = k(barrier, str);
                                    barrier.setReferencedIds(aVar.f1142d.f0);
                                }
                            }
                        }
                        ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
                        bVar.c();
                        aVar.d(bVar);
                        if (z) {
                            ConstraintAttribute.h(childAt, aVar.f1144f);
                        }
                        childAt.setLayoutParams(bVar);
                        d dVar = aVar.f1140b;
                        if (dVar.f1163c == 0) {
                            childAt.setVisibility(dVar.f1162b);
                        }
                        int i3 = Build.VERSION.SDK_INT;
                        if (i3 >= 17) {
                            childAt.setAlpha(aVar.f1140b.f1164d);
                            childAt.setRotation(aVar.f1143e.f1168c);
                            childAt.setRotationX(aVar.f1143e.f1169d);
                            childAt.setRotationY(aVar.f1143e.f1170e);
                            childAt.setScaleX(aVar.f1143e.f1171f);
                            childAt.setScaleY(aVar.f1143e.f1172g);
                            if (!Float.isNaN(aVar.f1143e.f1173h)) {
                                childAt.setPivotX(aVar.f1143e.f1173h);
                            }
                            if (!Float.isNaN(aVar.f1143e.i)) {
                                childAt.setPivotY(aVar.f1143e.i);
                            }
                            childAt.setTranslationX(aVar.f1143e.j);
                            childAt.setTranslationY(aVar.f1143e.k);
                            if (i3 >= 21) {
                                childAt.setTranslationZ(aVar.f1143e.l);
                                e eVar = aVar.f1143e;
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
            a aVar2 = this.f1138f.get(num);
            int i4 = aVar2.f1142d.e0;
            if (i4 != -1 && i4 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                C0012b c0012b2 = aVar2.f1142d;
                int[] iArr2 = c0012b2.f0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = c0012b2.g0;
                    if (str2 != null) {
                        c0012b2.f0 = k(barrier2, str2);
                        barrier2.setReferencedIds(aVar2.f1142d.f0);
                    }
                }
                barrier2.setType(aVar2.f1142d.c0);
                barrier2.setMargin(aVar2.f1142d.d0);
                ConstraintLayout.b bVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.u();
                aVar2.d(bVarGenerateDefaultLayoutParams);
                constraintLayout.addView(barrier2, bVarGenerateDefaultLayoutParams);
            }
            if (aVar2.f1142d.f1146b) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.b bVarGenerateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                aVar2.d(bVarGenerateDefaultLayoutParams2);
                constraintLayout.addView(guideline, bVarGenerateDefaultLayoutParams2);
            }
        }
    }

    public void g(int i, ConstraintLayout.b bVar) {
        if (this.f1138f.containsKey(Integer.valueOf(i))) {
            this.f1138f.get(Integer.valueOf(i)).d(bVar);
        }
    }

    public void h(Context context, int i) {
        i((ConstraintLayout) LayoutInflater.from(context).inflate(i, (ViewGroup) null));
    }

    public void i(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.f1138f.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1137e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1138f.containsKey(Integer.valueOf(id))) {
                this.f1138f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f1138f.get(Integer.valueOf(id));
            aVar.f1144f = ConstraintAttribute.b(this.f1136d, childAt);
            aVar.f(id, bVar);
            aVar.f1140b.f1162b = childAt.getVisibility();
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 17) {
                aVar.f1140b.f1164d = childAt.getAlpha();
                aVar.f1143e.f1168c = childAt.getRotation();
                aVar.f1143e.f1169d = childAt.getRotationX();
                aVar.f1143e.f1170e = childAt.getRotationY();
                aVar.f1143e.f1171f = childAt.getScaleX();
                aVar.f1143e.f1172g = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    e eVar = aVar.f1143e;
                    eVar.f1173h = pivotX;
                    eVar.i = pivotY;
                }
                aVar.f1143e.j = childAt.getTranslationX();
                aVar.f1143e.k = childAt.getTranslationY();
                if (i2 >= 21) {
                    aVar.f1143e.l = childAt.getTranslationZ();
                    e eVar2 = aVar.f1143e;
                    if (eVar2.m) {
                        eVar2.n = childAt.getElevation();
                    }
                }
            }
            if (childAt instanceof Barrier) {
                Barrier barrier = (Barrier) childAt;
                aVar.f1142d.k0 = barrier.v();
                aVar.f1142d.f0 = barrier.getReferencedIds();
                aVar.f1142d.c0 = barrier.getType();
                aVar.f1142d.d0 = barrier.getMargin();
            }
        }
    }

    public void j(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.f1138f.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraints.getChildAt(i);
            Constraints.a aVar = (Constraints.a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1137e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1138f.containsKey(Integer.valueOf(id))) {
                this.f1138f.put(Integer.valueOf(id), new a());
            }
            a aVar2 = this.f1138f.get(Integer.valueOf(id));
            if (childAt instanceof ConstraintHelper) {
                aVar2.h((ConstraintHelper) childAt, id, aVar);
            }
            aVar2.g(id, aVar);
        }
    }

    public a n(int i) {
        if (this.f1138f.containsKey(Integer.valueOf(i))) {
            return this.f1138f.get(Integer.valueOf(i));
        }
        return null;
    }

    public int o(int i) {
        return m(i).f1142d.f1149e;
    }

    public int[] p() {
        Integer[] numArr = (Integer[]) this.f1138f.keySet().toArray(new Integer[0]);
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
        return m(i).f1140b.f1162b;
    }

    public int s(int i) {
        return m(i).f1140b.f1163c;
    }

    public int t(int i) {
        return m(i).f1142d.f1148d;
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
                        aVarL.f1142d.f1146b = true;
                    }
                    this.f1138f.put(Integer.valueOf(aVarL.f1139a), aVarL);
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
            if (this.f1137e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1138f.containsKey(Integer.valueOf(id))) {
                this.f1138f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f1138f.get(Integer.valueOf(id));
            if (!aVar.f1142d.f1147c) {
                aVar.f(id, bVar);
                if (childAt instanceof ConstraintHelper) {
                    aVar.f1142d.f0 = ((ConstraintHelper) childAt).getReferencedIds();
                    if (childAt instanceof Barrier) {
                        Barrier barrier = (Barrier) childAt;
                        aVar.f1142d.k0 = barrier.v();
                        aVar.f1142d.c0 = barrier.getType();
                        aVar.f1142d.d0 = barrier.getMargin();
                    }
                }
                aVar.f1142d.f1147c = true;
            }
            d dVar = aVar.f1140b;
            if (!dVar.f1161a) {
                dVar.f1162b = childAt.getVisibility();
                aVar.f1140b.f1164d = childAt.getAlpha();
                aVar.f1140b.f1161a = true;
            }
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 17) {
                e eVar = aVar.f1143e;
                if (!eVar.f1167b) {
                    eVar.f1167b = true;
                    eVar.f1168c = childAt.getRotation();
                    aVar.f1143e.f1169d = childAt.getRotationX();
                    aVar.f1143e.f1170e = childAt.getRotationY();
                    aVar.f1143e.f1171f = childAt.getScaleX();
                    aVar.f1143e.f1172g = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        e eVar2 = aVar.f1143e;
                        eVar2.f1173h = pivotX;
                        eVar2.i = pivotY;
                    }
                    aVar.f1143e.j = childAt.getTranslationX();
                    aVar.f1143e.k = childAt.getTranslationY();
                    if (i2 >= 21) {
                        aVar.f1143e.l = childAt.getTranslationZ();
                        e eVar3 = aVar.f1143e;
                        if (eVar3.m) {
                            eVar3.n = childAt.getElevation();
                        }
                    }
                }
            }
        }
    }

    public void z(b bVar) {
        for (Integer num : bVar.f1138f.keySet()) {
            int iIntValue = num.intValue();
            a aVar = bVar.f1138f.get(num);
            if (!this.f1138f.containsKey(Integer.valueOf(iIntValue))) {
                this.f1138f.put(Integer.valueOf(iIntValue), new a());
            }
            a aVar2 = this.f1138f.get(Integer.valueOf(iIntValue));
            C0012b c0012b = aVar2.f1142d;
            if (!c0012b.f1147c) {
                c0012b.a(aVar.f1142d);
            }
            d dVar = aVar2.f1140b;
            if (!dVar.f1161a) {
                dVar.a(aVar.f1140b);
            }
            e eVar = aVar2.f1143e;
            if (!eVar.f1167b) {
                eVar.a(aVar.f1143e);
            }
            c cVar = aVar2.f1141c;
            if (!cVar.f1154b) {
                cVar.a(aVar.f1141c);
            }
            for (String str : aVar.f1144f.keySet()) {
                if (!aVar2.f1144f.containsKey(str)) {
                    aVar2.f1144f.put(str, aVar.f1144f.get(str));
                }
            }
        }
    }
}
