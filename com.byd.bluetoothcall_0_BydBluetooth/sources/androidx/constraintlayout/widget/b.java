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
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.h;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import com.bydux.api.common.tools.util.ListUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintSet.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f1070a = {0, 4, 8};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static SparseIntArray f1071b = new SparseIntArray();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static SparseIntArray f1072c = new SparseIntArray();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f1073d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1074e;
    public String f = "";
    public int g = 0;
    private HashMap<String, ConstraintAttribute> h = new HashMap<>();
    private boolean i = true;
    private HashMap<Integer, a> j = new HashMap<>();

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1075a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f1076b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final d f1077c = new d();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final c f1078d = new c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final C0020b f1079e = new C0020b();
        public final e f = new e();
        public HashMap<String, ConstraintAttribute> g = new HashMap<>();
        C0019a h;

        /* JADX INFO: renamed from: androidx.constraintlayout.widget.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ConstraintSet.java */
        static class C0019a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int[] f1080a = new int[10];

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            int[] f1081b = new int[10];

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f1082c = 0;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            int[] f1083d = new int[10];

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            float[] f1084e = new float[10];
            int f = 0;
            int[] g = new int[5];
            String[] h = new String[5];
            int i = 0;
            int[] j = new int[4];
            boolean[] k = new boolean[4];
            int l = 0;

            C0019a() {
            }

            void a(int i, float f) {
                int i2 = this.f;
                int[] iArr = this.f1083d;
                if (i2 >= iArr.length) {
                    this.f1083d = Arrays.copyOf(iArr, iArr.length * 2);
                    float[] fArr = this.f1084e;
                    this.f1084e = Arrays.copyOf(fArr, fArr.length * 2);
                }
                int[] iArr2 = this.f1083d;
                int i3 = this.f;
                iArr2[i3] = i;
                float[] fArr2 = this.f1084e;
                this.f = i3 + 1;
                fArr2[i3] = f;
            }

            void b(int i, int i2) {
                int i3 = this.f1082c;
                int[] iArr = this.f1080a;
                if (i3 >= iArr.length) {
                    this.f1080a = Arrays.copyOf(iArr, iArr.length * 2);
                    int[] iArr2 = this.f1081b;
                    this.f1081b = Arrays.copyOf(iArr2, iArr2.length * 2);
                }
                int[] iArr3 = this.f1080a;
                int i4 = this.f1082c;
                iArr3[i4] = i;
                int[] iArr4 = this.f1081b;
                this.f1082c = i4 + 1;
                iArr4[i4] = i2;
            }

            void c(int i, String str) {
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

            void d(int i, boolean z) {
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

            void e(a aVar) {
                for (int i = 0; i < this.f1082c; i++) {
                    b.M(aVar, this.f1080a[i], this.f1081b[i]);
                }
                for (int i2 = 0; i2 < this.f; i2++) {
                    b.L(aVar, this.f1083d[i2], this.f1084e[i2]);
                }
                for (int i3 = 0; i3 < this.i; i3++) {
                    b.N(aVar, this.g[i3], this.h[i3]);
                }
                for (int i4 = 0; i4 < this.l; i4++) {
                    b.O(aVar, this.j[i4], this.k[i4]);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g(int i, ConstraintLayout.b bVar) {
            this.f1075a = i;
            C0020b c0020b = this.f1079e;
            c0020b.k = bVar.f1041e;
            c0020b.l = bVar.f;
            c0020b.m = bVar.g;
            c0020b.n = bVar.h;
            c0020b.o = bVar.i;
            c0020b.p = bVar.j;
            c0020b.q = bVar.k;
            c0020b.r = bVar.l;
            c0020b.s = bVar.m;
            c0020b.t = bVar.n;
            c0020b.u = bVar.o;
            c0020b.v = bVar.s;
            c0020b.w = bVar.t;
            c0020b.x = bVar.u;
            c0020b.y = bVar.v;
            c0020b.z = bVar.G;
            c0020b.A = bVar.H;
            c0020b.B = bVar.I;
            c0020b.C = bVar.p;
            c0020b.D = bVar.q;
            c0020b.E = bVar.r;
            c0020b.F = bVar.X;
            c0020b.G = bVar.Y;
            c0020b.H = bVar.Z;
            c0020b.i = bVar.f1039c;
            C0020b c0020b2 = this.f1079e;
            c0020b2.g = bVar.f1037a;
            c0020b2.h = bVar.f1038b;
            c0020b2.f1089e = ((ViewGroup.MarginLayoutParams) bVar).width;
            c0020b2.f = ((ViewGroup.MarginLayoutParams) bVar).height;
            c0020b2.I = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            c0020b2.J = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
            c0020b2.K = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            c0020b2.L = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
            c0020b2.O = bVar.D;
            c0020b2.W = bVar.M;
            c0020b2.X = bVar.L;
            c0020b2.Z = bVar.O;
            c0020b2.Y = bVar.N;
            c0020b2.o0 = bVar.a0;
            c0020b2.p0 = bVar.b0;
            c0020b2.a0 = bVar.P;
            c0020b2.b0 = bVar.Q;
            c0020b2.c0 = bVar.T;
            c0020b2.d0 = bVar.U;
            c0020b2.e0 = bVar.R;
            c0020b2.f0 = bVar.S;
            c0020b2.g0 = bVar.V;
            c0020b2.h0 = bVar.W;
            c0020b2.n0 = bVar.c0;
            c0020b2.Q = bVar.x;
            C0020b c0020b3 = this.f1079e;
            c0020b3.S = bVar.z;
            c0020b3.P = bVar.w;
            c0020b3.R = bVar.y;
            c0020b3.U = bVar.A;
            c0020b3.T = bVar.B;
            c0020b3.V = bVar.C;
            c0020b3.r0 = bVar.d0;
            if (Build.VERSION.SDK_INT >= 17) {
                c0020b3.M = bVar.getMarginEnd();
                this.f1079e.N = bVar.getMarginStart();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void h(int i, Constraints.a aVar) {
            g(i, aVar);
            this.f1077c.f1098d = aVar.x0;
            e eVar = this.f;
            eVar.f1102c = aVar.A0;
            eVar.f1103d = aVar.B0;
            eVar.f1104e = aVar.C0;
            eVar.f = aVar.D0;
            eVar.g = aVar.E0;
            eVar.h = aVar.F0;
            eVar.i = aVar.G0;
            eVar.k = aVar.H0;
            eVar.l = aVar.I0;
            eVar.m = aVar.J0;
            eVar.o = aVar.z0;
            eVar.n = aVar.y0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void i(ConstraintHelper constraintHelper, int i, Constraints.a aVar) {
            h(i, aVar);
            if (constraintHelper instanceof Barrier) {
                C0020b c0020b = this.f1079e;
                c0020b.k0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                c0020b.i0 = barrier.getType();
                this.f1079e.l0 = barrier.getReferencedIds();
                this.f1079e.j0 = barrier.getMargin();
            }
        }

        public void d(a aVar) {
            C0019a c0019a = this.h;
            if (c0019a != null) {
                c0019a.e(aVar);
            }
        }

        public void e(ConstraintLayout.b bVar) {
            C0020b c0020b = this.f1079e;
            bVar.f1041e = c0020b.k;
            bVar.f = c0020b.l;
            bVar.g = c0020b.m;
            bVar.h = c0020b.n;
            bVar.i = c0020b.o;
            bVar.j = c0020b.p;
            bVar.k = c0020b.q;
            bVar.l = c0020b.r;
            bVar.m = c0020b.s;
            bVar.n = c0020b.t;
            bVar.o = c0020b.u;
            bVar.s = c0020b.v;
            bVar.t = c0020b.w;
            bVar.u = c0020b.x;
            bVar.v = c0020b.y;
            ((ViewGroup.MarginLayoutParams) bVar).leftMargin = c0020b.I;
            ((ViewGroup.MarginLayoutParams) bVar).rightMargin = c0020b.J;
            ((ViewGroup.MarginLayoutParams) bVar).topMargin = c0020b.K;
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = c0020b.L;
            bVar.A = c0020b.U;
            bVar.B = c0020b.T;
            bVar.x = c0020b.Q;
            bVar.z = c0020b.S;
            bVar.G = c0020b.z;
            bVar.H = c0020b.A;
            C0020b c0020b2 = this.f1079e;
            bVar.p = c0020b2.C;
            bVar.q = c0020b2.D;
            bVar.r = c0020b2.E;
            bVar.I = c0020b2.B;
            bVar.X = c0020b2.F;
            bVar.Y = c0020b2.G;
            bVar.M = c0020b2.W;
            bVar.L = c0020b2.X;
            bVar.O = c0020b2.Z;
            bVar.N = c0020b2.Y;
            bVar.a0 = c0020b2.o0;
            bVar.b0 = c0020b2.p0;
            bVar.P = c0020b2.a0;
            bVar.Q = c0020b2.b0;
            bVar.T = c0020b2.c0;
            bVar.U = c0020b2.d0;
            bVar.R = c0020b2.e0;
            bVar.S = c0020b2.f0;
            bVar.V = c0020b2.g0;
            bVar.W = c0020b2.h0;
            bVar.Z = c0020b2.H;
            bVar.f1039c = c0020b2.i;
            bVar.f1037a = c0020b2.g;
            bVar.f1038b = c0020b2.h;
            ((ViewGroup.MarginLayoutParams) bVar).width = c0020b2.f1089e;
            C0020b c0020b3 = this.f1079e;
            ((ViewGroup.MarginLayoutParams) bVar).height = c0020b3.f;
            String str = c0020b3.n0;
            if (str != null) {
                bVar.c0 = str;
            }
            bVar.d0 = c0020b3.r0;
            if (Build.VERSION.SDK_INT >= 17) {
                bVar.setMarginStart(c0020b3.N);
                bVar.setMarginEnd(this.f1079e.M);
            }
            bVar.c();
        }

        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.f1079e.a(this.f1079e);
            aVar.f1078d.a(this.f1078d);
            aVar.f1077c.a(this.f1077c);
            aVar.f.a(this.f);
            aVar.f1075a = this.f1075a;
            aVar.h = this.h;
            return aVar;
        }
    }

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class C0020b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1085a;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1089e;
        public int f;
        public int[] l0;
        public String m0;
        public String n0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1086b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1087c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1088d = false;
        public int g = -1;
        public int h = -1;
        public float i = -1.0f;
        public boolean j = true;
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
        public int x = -1;
        public int y = -1;
        public float z = 0.5f;
        public float A = 0.5f;
        public String B = null;
        public int C = -1;
        public int D = 0;
        public float E = 0.0f;
        public int F = -1;
        public int G = -1;
        public int H = -1;
        public int I = 0;
        public int J = 0;
        public int K = 0;
        public int L = 0;
        public int M = 0;
        public int N = 0;
        public int O = 0;
        public int P = Integer.MIN_VALUE;
        public int Q = Integer.MIN_VALUE;
        public int R = Integer.MIN_VALUE;
        public int S = Integer.MIN_VALUE;
        public int T = Integer.MIN_VALUE;
        public int U = Integer.MIN_VALUE;
        public int V = Integer.MIN_VALUE;
        public float W = -1.0f;
        public float X = -1.0f;
        public int Y = 0;
        public int Z = 0;
        public int a0 = 0;
        public int b0 = 0;
        public int c0 = 0;
        public int d0 = 0;
        public int e0 = 0;
        public int f0 = 0;
        public float g0 = 1.0f;
        public float h0 = 1.0f;
        public int i0 = -1;
        public int j0 = 0;
        public int k0 = -1;
        public boolean o0 = false;
        public boolean p0 = false;
        public boolean q0 = true;
        public int r0 = 0;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1085a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.w8, 24);
            f1085a.append(androidx.constraintlayout.widget.e.x8, 25);
            f1085a.append(androidx.constraintlayout.widget.e.z8, 28);
            f1085a.append(androidx.constraintlayout.widget.e.A8, 29);
            f1085a.append(androidx.constraintlayout.widget.e.F8, 35);
            f1085a.append(androidx.constraintlayout.widget.e.E8, 34);
            f1085a.append(androidx.constraintlayout.widget.e.g8, 4);
            f1085a.append(androidx.constraintlayout.widget.e.f8, 3);
            f1085a.append(androidx.constraintlayout.widget.e.d8, 1);
            f1085a.append(androidx.constraintlayout.widget.e.L8, 6);
            f1085a.append(androidx.constraintlayout.widget.e.M8, 7);
            f1085a.append(androidx.constraintlayout.widget.e.n8, 17);
            f1085a.append(androidx.constraintlayout.widget.e.o8, 18);
            f1085a.append(androidx.constraintlayout.widget.e.p8, 19);
            f1085a.append(androidx.constraintlayout.widget.e.Z7, 90);
            f1085a.append(androidx.constraintlayout.widget.e.L7, 26);
            f1085a.append(androidx.constraintlayout.widget.e.B8, 31);
            f1085a.append(androidx.constraintlayout.widget.e.C8, 32);
            f1085a.append(androidx.constraintlayout.widget.e.m8, 10);
            f1085a.append(androidx.constraintlayout.widget.e.l8, 9);
            f1085a.append(androidx.constraintlayout.widget.e.P8, 13);
            f1085a.append(androidx.constraintlayout.widget.e.S8, 16);
            f1085a.append(androidx.constraintlayout.widget.e.Q8, 14);
            f1085a.append(androidx.constraintlayout.widget.e.N8, 11);
            f1085a.append(androidx.constraintlayout.widget.e.R8, 15);
            f1085a.append(androidx.constraintlayout.widget.e.O8, 12);
            f1085a.append(androidx.constraintlayout.widget.e.I8, 38);
            f1085a.append(androidx.constraintlayout.widget.e.u8, 37);
            f1085a.append(androidx.constraintlayout.widget.e.t8, 39);
            f1085a.append(androidx.constraintlayout.widget.e.H8, 40);
            f1085a.append(androidx.constraintlayout.widget.e.s8, 20);
            f1085a.append(androidx.constraintlayout.widget.e.G8, 36);
            f1085a.append(androidx.constraintlayout.widget.e.k8, 5);
            f1085a.append(androidx.constraintlayout.widget.e.v8, 91);
            f1085a.append(androidx.constraintlayout.widget.e.D8, 91);
            f1085a.append(androidx.constraintlayout.widget.e.y8, 91);
            f1085a.append(androidx.constraintlayout.widget.e.e8, 91);
            f1085a.append(androidx.constraintlayout.widget.e.c8, 91);
            f1085a.append(androidx.constraintlayout.widget.e.O7, 23);
            f1085a.append(androidx.constraintlayout.widget.e.Q7, 27);
            f1085a.append(androidx.constraintlayout.widget.e.S7, 30);
            f1085a.append(androidx.constraintlayout.widget.e.T7, 8);
            f1085a.append(androidx.constraintlayout.widget.e.P7, 33);
            f1085a.append(androidx.constraintlayout.widget.e.R7, 2);
            f1085a.append(androidx.constraintlayout.widget.e.M7, 22);
            f1085a.append(androidx.constraintlayout.widget.e.N7, 21);
            f1085a.append(androidx.constraintlayout.widget.e.J8, 41);
            f1085a.append(androidx.constraintlayout.widget.e.q8, 42);
            f1085a.append(androidx.constraintlayout.widget.e.b8, 41);
            f1085a.append(androidx.constraintlayout.widget.e.a8, 42);
            f1085a.append(androidx.constraintlayout.widget.e.T8, 76);
            f1085a.append(androidx.constraintlayout.widget.e.h8, 61);
            f1085a.append(androidx.constraintlayout.widget.e.j8, 62);
            f1085a.append(androidx.constraintlayout.widget.e.i8, 63);
            f1085a.append(androidx.constraintlayout.widget.e.K8, 69);
            f1085a.append(androidx.constraintlayout.widget.e.r8, 70);
            f1085a.append(androidx.constraintlayout.widget.e.X7, 71);
            f1085a.append(androidx.constraintlayout.widget.e.V7, 72);
            f1085a.append(androidx.constraintlayout.widget.e.W7, 73);
            f1085a.append(androidx.constraintlayout.widget.e.Y7, 74);
            f1085a.append(androidx.constraintlayout.widget.e.U7, 75);
        }

        public void a(C0020b c0020b) {
            this.f1086b = c0020b.f1086b;
            this.f1089e = c0020b.f1089e;
            this.f1087c = c0020b.f1087c;
            this.f = c0020b.f;
            this.g = c0020b.g;
            this.h = c0020b.h;
            this.i = c0020b.i;
            this.j = c0020b.j;
            this.k = c0020b.k;
            this.l = c0020b.l;
            this.m = c0020b.m;
            this.n = c0020b.n;
            this.o = c0020b.o;
            this.p = c0020b.p;
            this.q = c0020b.q;
            this.r = c0020b.r;
            this.s = c0020b.s;
            this.t = c0020b.t;
            this.u = c0020b.u;
            this.v = c0020b.v;
            this.w = c0020b.w;
            this.x = c0020b.x;
            this.y = c0020b.y;
            this.z = c0020b.z;
            this.A = c0020b.A;
            this.B = c0020b.B;
            this.C = c0020b.C;
            this.D = c0020b.D;
            this.E = c0020b.E;
            this.F = c0020b.F;
            this.G = c0020b.G;
            this.H = c0020b.H;
            this.I = c0020b.I;
            this.J = c0020b.J;
            this.K = c0020b.K;
            this.L = c0020b.L;
            this.M = c0020b.M;
            this.N = c0020b.N;
            this.O = c0020b.O;
            this.P = c0020b.P;
            this.Q = c0020b.Q;
            this.R = c0020b.R;
            this.S = c0020b.S;
            this.T = c0020b.T;
            this.U = c0020b.U;
            this.V = c0020b.V;
            this.W = c0020b.W;
            this.X = c0020b.X;
            this.Y = c0020b.Y;
            this.Z = c0020b.Z;
            this.a0 = c0020b.a0;
            this.b0 = c0020b.b0;
            this.c0 = c0020b.c0;
            this.d0 = c0020b.d0;
            this.e0 = c0020b.e0;
            this.f0 = c0020b.f0;
            this.g0 = c0020b.g0;
            this.h0 = c0020b.h0;
            this.i0 = c0020b.i0;
            this.j0 = c0020b.j0;
            this.k0 = c0020b.k0;
            this.n0 = c0020b.n0;
            int[] iArr = c0020b.l0;
            if (iArr == null || c0020b.m0 != null) {
                this.l0 = null;
            } else {
                this.l0 = Arrays.copyOf(iArr, iArr.length);
            }
            this.m0 = c0020b.m0;
            this.o0 = c0020b.o0;
            this.p0 = c0020b.p0;
            this.q0 = c0020b.q0;
            this.r0 = c0020b.r0;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.K7);
            this.f1087c = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                int i2 = f1085a.get(index);
                switch (i2) {
                    case 1:
                        this.s = b.D(typedArrayObtainStyledAttributes, index, this.s);
                        break;
                    case 2:
                        this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.L);
                        break;
                    case 3:
                        this.r = b.D(typedArrayObtainStyledAttributes, index, this.r);
                        break;
                    case 4:
                        this.q = b.D(typedArrayObtainStyledAttributes, index, this.q);
                        break;
                    case 5:
                        this.B = typedArrayObtainStyledAttributes.getString(index);
                        break;
                    case 6:
                        this.F = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.F);
                        break;
                    case 7:
                        this.G = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.G);
                        break;
                    case 8:
                        if (Build.VERSION.SDK_INT >= 17) {
                            this.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.M);
                        }
                        break;
                    case 9:
                        this.y = b.D(typedArrayObtainStyledAttributes, index, this.y);
                        break;
                    case 10:
                        this.x = b.D(typedArrayObtainStyledAttributes, index, this.x);
                        break;
                    case 11:
                        this.S = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.S);
                        break;
                    case 12:
                        this.T = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.T);
                        break;
                    case 13:
                        this.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.P);
                        break;
                    case 14:
                        this.R = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.R);
                        break;
                    case 15:
                        this.U = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.U);
                        break;
                    case 16:
                        this.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.Q);
                        break;
                    case 17:
                        this.g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.g);
                        break;
                    case 18:
                        this.h = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.h);
                        break;
                    case 19:
                        this.i = typedArrayObtainStyledAttributes.getFloat(index, this.i);
                        break;
                    case 20:
                        this.z = typedArrayObtainStyledAttributes.getFloat(index, this.z);
                        break;
                    case 21:
                        this.f = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f);
                        break;
                    case 22:
                        this.f1089e = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f1089e);
                        break;
                    case 23:
                        this.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.I);
                        break;
                    case 24:
                        this.k = b.D(typedArrayObtainStyledAttributes, index, this.k);
                        break;
                    case 25:
                        this.l = b.D(typedArrayObtainStyledAttributes, index, this.l);
                        break;
                    case 26:
                        this.H = typedArrayObtainStyledAttributes.getInt(index, this.H);
                        break;
                    case 27:
                        this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                        break;
                    case 28:
                        this.m = b.D(typedArrayObtainStyledAttributes, index, this.m);
                        break;
                    case 29:
                        this.n = b.D(typedArrayObtainStyledAttributes, index, this.n);
                        break;
                    case 30:
                        if (Build.VERSION.SDK_INT >= 17) {
                            this.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.N);
                        }
                        break;
                    case 31:
                        this.v = b.D(typedArrayObtainStyledAttributes, index, this.v);
                        break;
                    case 32:
                        this.w = b.D(typedArrayObtainStyledAttributes, index, this.w);
                        break;
                    case 33:
                        this.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.K);
                        break;
                    case 34:
                        this.p = b.D(typedArrayObtainStyledAttributes, index, this.p);
                        break;
                    case 35:
                        this.o = b.D(typedArrayObtainStyledAttributes, index, this.o);
                        break;
                    case 36:
                        this.A = typedArrayObtainStyledAttributes.getFloat(index, this.A);
                        break;
                    case 37:
                        this.X = typedArrayObtainStyledAttributes.getFloat(index, this.X);
                        break;
                    case 38:
                        this.W = typedArrayObtainStyledAttributes.getFloat(index, this.W);
                        break;
                    case 39:
                        this.Y = typedArrayObtainStyledAttributes.getInt(index, this.Y);
                        break;
                    case 40:
                        this.Z = typedArrayObtainStyledAttributes.getInt(index, this.Z);
                        break;
                    case 41:
                        b.E(this, typedArrayObtainStyledAttributes, index, 0);
                        break;
                    case 42:
                        b.E(this, typedArrayObtainStyledAttributes, index, 1);
                        break;
                    default:
                        switch (i2) {
                            case 61:
                                this.C = b.D(typedArrayObtainStyledAttributes, index, this.C);
                                break;
                            case 62:
                                this.D = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.D);
                                break;
                            case 63:
                                this.E = typedArrayObtainStyledAttributes.getFloat(index, this.E);
                                break;
                            default:
                                switch (i2) {
                                    case 69:
                                        this.g0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 70:
                                        this.h0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 71:
                                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                        break;
                                    case 72:
                                        this.i0 = typedArrayObtainStyledAttributes.getInt(index, this.i0);
                                        break;
                                    case 73:
                                        this.j0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.j0);
                                        break;
                                    case 74:
                                        this.m0 = typedArrayObtainStyledAttributes.getString(index);
                                        break;
                                    case 75:
                                        this.q0 = typedArrayObtainStyledAttributes.getBoolean(index, this.q0);
                                        break;
                                    case 76:
                                        this.r0 = typedArrayObtainStyledAttributes.getInt(index, this.r0);
                                        break;
                                    case 77:
                                        this.t = b.D(typedArrayObtainStyledAttributes, index, this.t);
                                        break;
                                    case 78:
                                        this.u = b.D(typedArrayObtainStyledAttributes, index, this.u);
                                        break;
                                    case 79:
                                        this.V = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.V);
                                        break;
                                    case 80:
                                        this.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.O);
                                        break;
                                    case 81:
                                        this.a0 = typedArrayObtainStyledAttributes.getInt(index, this.a0);
                                        break;
                                    case 82:
                                        this.b0 = typedArrayObtainStyledAttributes.getInt(index, this.b0);
                                        break;
                                    case 83:
                                        this.d0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.d0);
                                        break;
                                    case 84:
                                        this.c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.c0);
                                        break;
                                    case 85:
                                        this.f0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f0);
                                        break;
                                    case 86:
                                        this.e0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.e0);
                                        break;
                                    case 87:
                                        this.o0 = typedArrayObtainStyledAttributes.getBoolean(index, this.o0);
                                        break;
                                    case 88:
                                        this.p0 = typedArrayObtainStyledAttributes.getBoolean(index, this.p0);
                                        break;
                                    case 89:
                                        this.n0 = typedArrayObtainStyledAttributes.getString(index);
                                        break;
                                    case 90:
                                        this.j = typedArrayObtainStyledAttributes.getBoolean(index, this.j);
                                        break;
                                    case 91:
                                        Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1085a.get(index));
                                        break;
                                    default:
                                        Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1085a.get(index));
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

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1090a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1091b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1092c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1093d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f1094e = null;
        public int f = -1;
        public int g = 0;
        public float h = Float.NaN;
        public int i = -1;
        public float j = Float.NaN;
        public float k = Float.NaN;
        public int l = -1;
        public String m = null;
        public int n = -3;
        public int o = -1;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1090a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.l9, 1);
            f1090a.append(androidx.constraintlayout.widget.e.n9, 2);
            f1090a.append(androidx.constraintlayout.widget.e.r9, 3);
            f1090a.append(androidx.constraintlayout.widget.e.k9, 4);
            f1090a.append(androidx.constraintlayout.widget.e.j9, 5);
            f1090a.append(androidx.constraintlayout.widget.e.i9, 6);
            f1090a.append(androidx.constraintlayout.widget.e.m9, 7);
            f1090a.append(androidx.constraintlayout.widget.e.q9, 8);
            f1090a.append(androidx.constraintlayout.widget.e.p9, 9);
            f1090a.append(androidx.constraintlayout.widget.e.o9, 10);
        }

        public void a(c cVar) {
            this.f1091b = cVar.f1091b;
            this.f1092c = cVar.f1092c;
            this.f1094e = cVar.f1094e;
            this.f = cVar.f;
            this.g = cVar.g;
            this.j = cVar.j;
            this.h = cVar.h;
            this.i = cVar.i;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.h9);
            this.f1091b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (f1090a.get(index)) {
                    case 1:
                        this.j = typedArrayObtainStyledAttributes.getFloat(index, this.j);
                        break;
                    case 2:
                        this.f = typedArrayObtainStyledAttributes.getInt(index, this.f);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.f1094e = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.f1094e = androidx.constraintlayout.core.j.a.c.f746b[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.g = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.f1092c = b.D(typedArrayObtainStyledAttributes, index, this.f1092c);
                        break;
                    case 6:
                        this.f1093d = typedArrayObtainStyledAttributes.getInteger(index, this.f1093d);
                        break;
                    case 7:
                        this.h = typedArrayObtainStyledAttributes.getFloat(index, this.h);
                        break;
                    case 8:
                        this.l = typedArrayObtainStyledAttributes.getInteger(index, this.l);
                        break;
                    case 9:
                        this.k = typedArrayObtainStyledAttributes.getFloat(index, this.k);
                        break;
                    case 10:
                        int i2 = typedArrayObtainStyledAttributes.peekValue(index).type;
                        if (i2 == 1) {
                            int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                            this.o = resourceId;
                            if (resourceId != -1) {
                                this.n = -2;
                            }
                        } else if (i2 == 3) {
                            String string = typedArrayObtainStyledAttributes.getString(index);
                            this.m = string;
                            if (string.indexOf("/") > 0) {
                                this.o = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                                this.n = -2;
                            } else {
                                this.n = -1;
                            }
                        } else {
                            this.n = typedArrayObtainStyledAttributes.getInteger(index, this.o);
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1095a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1096b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1097c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1098d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1099e = Float.NaN;

        public void a(d dVar) {
            this.f1095a = dVar.f1095a;
            this.f1096b = dVar.f1096b;
            this.f1098d = dVar.f1098d;
            this.f1099e = dVar.f1099e;
            this.f1097c = dVar.f1097c;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.Pa);
            this.f1095a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == androidx.constraintlayout.widget.e.Ra) {
                    this.f1098d = typedArrayObtainStyledAttributes.getFloat(index, this.f1098d);
                } else if (index == androidx.constraintlayout.widget.e.Qa) {
                    this.f1096b = typedArrayObtainStyledAttributes.getInt(index, this.f1096b);
                    this.f1096b = b.f1070a[this.f1096b];
                } else if (index == androidx.constraintlayout.widget.e.Ta) {
                    this.f1097c = typedArrayObtainStyledAttributes.getInt(index, this.f1097c);
                } else if (index == androidx.constraintlayout.widget.e.Sa) {
                    this.f1099e = typedArrayObtainStyledAttributes.getFloat(index, this.f1099e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1100a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1101b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1102c = 0.0f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1103d = 0.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1104e = 0.0f;
        public float f = 1.0f;
        public float g = 1.0f;
        public float h = Float.NaN;
        public float i = Float.NaN;
        public int j = -1;
        public float k = 0.0f;
        public float l = 0.0f;
        public float m = 0.0f;
        public boolean n = false;
        public float o = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1100a = sparseIntArray;
            sparseIntArray.append(androidx.constraintlayout.widget.e.pb, 1);
            f1100a.append(androidx.constraintlayout.widget.e.qb, 2);
            f1100a.append(androidx.constraintlayout.widget.e.rb, 3);
            f1100a.append(androidx.constraintlayout.widget.e.nb, 4);
            f1100a.append(androidx.constraintlayout.widget.e.ob, 5);
            f1100a.append(androidx.constraintlayout.widget.e.jb, 6);
            f1100a.append(androidx.constraintlayout.widget.e.kb, 7);
            f1100a.append(androidx.constraintlayout.widget.e.lb, 8);
            f1100a.append(androidx.constraintlayout.widget.e.mb, 9);
            f1100a.append(androidx.constraintlayout.widget.e.sb, 10);
            f1100a.append(androidx.constraintlayout.widget.e.tb, 11);
            f1100a.append(androidx.constraintlayout.widget.e.ub, 12);
        }

        public void a(e eVar) {
            this.f1101b = eVar.f1101b;
            this.f1102c = eVar.f1102c;
            this.f1103d = eVar.f1103d;
            this.f1104e = eVar.f1104e;
            this.f = eVar.f;
            this.g = eVar.g;
            this.h = eVar.h;
            this.i = eVar.i;
            this.j = eVar.j;
            this.k = eVar.k;
            this.l = eVar.l;
            this.m = eVar.m;
            this.n = eVar.n;
            this.o = eVar.o;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.ib);
            this.f1101b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (f1100a.get(index)) {
                    case 1:
                        this.f1102c = typedArrayObtainStyledAttributes.getFloat(index, this.f1102c);
                        break;
                    case 2:
                        this.f1103d = typedArrayObtainStyledAttributes.getFloat(index, this.f1103d);
                        break;
                    case 3:
                        this.f1104e = typedArrayObtainStyledAttributes.getFloat(index, this.f1104e);
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
                        this.k = typedArrayObtainStyledAttributes.getDimension(index, this.k);
                        break;
                    case 9:
                        this.l = typedArrayObtainStyledAttributes.getDimension(index, this.l);
                        break;
                    case 10:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.m = typedArrayObtainStyledAttributes.getDimension(index, this.m);
                        }
                        break;
                    case 11:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.n = true;
                            this.o = typedArrayObtainStyledAttributes.getDimension(index, this.o);
                        }
                        break;
                    case 12:
                        this.j = b.D(typedArrayObtainStyledAttributes, index, this.j);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    static {
        f1071b.append(androidx.constraintlayout.widget.e.K0, 25);
        f1071b.append(androidx.constraintlayout.widget.e.L0, 26);
        f1071b.append(androidx.constraintlayout.widget.e.N0, 29);
        f1071b.append(androidx.constraintlayout.widget.e.O0, 30);
        f1071b.append(androidx.constraintlayout.widget.e.U0, 36);
        f1071b.append(androidx.constraintlayout.widget.e.T0, 35);
        f1071b.append(androidx.constraintlayout.widget.e.r0, 4);
        f1071b.append(androidx.constraintlayout.widget.e.q0, 3);
        f1071b.append(androidx.constraintlayout.widget.e.m0, 1);
        f1071b.append(androidx.constraintlayout.widget.e.o0, 91);
        f1071b.append(androidx.constraintlayout.widget.e.n0, 92);
        f1071b.append(androidx.constraintlayout.widget.e.d1, 6);
        f1071b.append(androidx.constraintlayout.widget.e.e1, 7);
        f1071b.append(androidx.constraintlayout.widget.e.y0, 17);
        f1071b.append(androidx.constraintlayout.widget.e.z0, 18);
        f1071b.append(androidx.constraintlayout.widget.e.A0, 19);
        f1071b.append(androidx.constraintlayout.widget.e.i0, 99);
        f1071b.append(androidx.constraintlayout.widget.e.E, 27);
        f1071b.append(androidx.constraintlayout.widget.e.P0, 32);
        f1071b.append(androidx.constraintlayout.widget.e.Q0, 33);
        f1071b.append(androidx.constraintlayout.widget.e.x0, 10);
        f1071b.append(androidx.constraintlayout.widget.e.w0, 9);
        f1071b.append(androidx.constraintlayout.widget.e.h1, 13);
        f1071b.append(androidx.constraintlayout.widget.e.k1, 16);
        f1071b.append(androidx.constraintlayout.widget.e.i1, 14);
        f1071b.append(androidx.constraintlayout.widget.e.f1, 11);
        f1071b.append(androidx.constraintlayout.widget.e.j1, 15);
        f1071b.append(androidx.constraintlayout.widget.e.g1, 12);
        f1071b.append(androidx.constraintlayout.widget.e.X0, 40);
        f1071b.append(androidx.constraintlayout.widget.e.I0, 39);
        f1071b.append(androidx.constraintlayout.widget.e.H0, 41);
        f1071b.append(androidx.constraintlayout.widget.e.W0, 42);
        f1071b.append(androidx.constraintlayout.widget.e.G0, 20);
        f1071b.append(androidx.constraintlayout.widget.e.V0, 37);
        f1071b.append(androidx.constraintlayout.widget.e.v0, 5);
        f1071b.append(androidx.constraintlayout.widget.e.J0, 87);
        f1071b.append(androidx.constraintlayout.widget.e.S0, 87);
        f1071b.append(androidx.constraintlayout.widget.e.M0, 87);
        f1071b.append(androidx.constraintlayout.widget.e.p0, 87);
        f1071b.append(androidx.constraintlayout.widget.e.l0, 87);
        f1071b.append(androidx.constraintlayout.widget.e.J, 24);
        f1071b.append(androidx.constraintlayout.widget.e.L, 28);
        f1071b.append(androidx.constraintlayout.widget.e.X, 31);
        f1071b.append(androidx.constraintlayout.widget.e.Y, 8);
        f1071b.append(androidx.constraintlayout.widget.e.K, 34);
        f1071b.append(androidx.constraintlayout.widget.e.M, 2);
        f1071b.append(androidx.constraintlayout.widget.e.H, 23);
        f1071b.append(androidx.constraintlayout.widget.e.I, 21);
        f1071b.append(androidx.constraintlayout.widget.e.Y0, 95);
        f1071b.append(androidx.constraintlayout.widget.e.B0, 96);
        f1071b.append(androidx.constraintlayout.widget.e.G, 22);
        f1071b.append(androidx.constraintlayout.widget.e.N, 43);
        f1071b.append(androidx.constraintlayout.widget.e.a0, 44);
        f1071b.append(androidx.constraintlayout.widget.e.V, 45);
        f1071b.append(androidx.constraintlayout.widget.e.W, 46);
        f1071b.append(androidx.constraintlayout.widget.e.U, 60);
        f1071b.append(androidx.constraintlayout.widget.e.S, 47);
        f1071b.append(androidx.constraintlayout.widget.e.T, 48);
        f1071b.append(androidx.constraintlayout.widget.e.O, 49);
        f1071b.append(androidx.constraintlayout.widget.e.P, 50);
        f1071b.append(androidx.constraintlayout.widget.e.Q, 51);
        f1071b.append(androidx.constraintlayout.widget.e.R, 52);
        f1071b.append(androidx.constraintlayout.widget.e.Z, 53);
        f1071b.append(androidx.constraintlayout.widget.e.Z0, 54);
        f1071b.append(androidx.constraintlayout.widget.e.C0, 55);
        f1071b.append(androidx.constraintlayout.widget.e.a1, 56);
        f1071b.append(androidx.constraintlayout.widget.e.D0, 57);
        f1071b.append(androidx.constraintlayout.widget.e.b1, 58);
        f1071b.append(androidx.constraintlayout.widget.e.E0, 59);
        f1071b.append(androidx.constraintlayout.widget.e.s0, 61);
        f1071b.append(androidx.constraintlayout.widget.e.u0, 62);
        f1071b.append(androidx.constraintlayout.widget.e.t0, 63);
        f1071b.append(androidx.constraintlayout.widget.e.b0, 64);
        f1071b.append(androidx.constraintlayout.widget.e.u1, 65);
        f1071b.append(androidx.constraintlayout.widget.e.h0, 66);
        f1071b.append(androidx.constraintlayout.widget.e.v1, 67);
        f1071b.append(androidx.constraintlayout.widget.e.n1, 79);
        f1071b.append(androidx.constraintlayout.widget.e.F, 38);
        f1071b.append(androidx.constraintlayout.widget.e.m1, 68);
        f1071b.append(androidx.constraintlayout.widget.e.c1, 69);
        f1071b.append(androidx.constraintlayout.widget.e.F0, 70);
        f1071b.append(androidx.constraintlayout.widget.e.l1, 97);
        f1071b.append(androidx.constraintlayout.widget.e.f0, 71);
        f1071b.append(androidx.constraintlayout.widget.e.d0, 72);
        f1071b.append(androidx.constraintlayout.widget.e.e0, 73);
        f1071b.append(androidx.constraintlayout.widget.e.g0, 74);
        f1071b.append(androidx.constraintlayout.widget.e.c0, 75);
        f1071b.append(androidx.constraintlayout.widget.e.o1, 76);
        f1071b.append(androidx.constraintlayout.widget.e.R0, 77);
        f1071b.append(androidx.constraintlayout.widget.e.w1, 78);
        f1071b.append(androidx.constraintlayout.widget.e.k0, 80);
        f1071b.append(androidx.constraintlayout.widget.e.j0, 81);
        f1071b.append(androidx.constraintlayout.widget.e.p1, 82);
        f1071b.append(androidx.constraintlayout.widget.e.t1, 83);
        f1071b.append(androidx.constraintlayout.widget.e.s1, 84);
        f1071b.append(androidx.constraintlayout.widget.e.r1, 85);
        f1071b.append(androidx.constraintlayout.widget.e.q1, 86);
        SparseIntArray sparseIntArray = f1072c;
        int i = androidx.constraintlayout.widget.e.L4;
        sparseIntArray.append(i, 6);
        f1072c.append(i, 7);
        f1072c.append(androidx.constraintlayout.widget.e.G3, 27);
        f1072c.append(androidx.constraintlayout.widget.e.O4, 13);
        f1072c.append(androidx.constraintlayout.widget.e.R4, 16);
        f1072c.append(androidx.constraintlayout.widget.e.P4, 14);
        f1072c.append(androidx.constraintlayout.widget.e.M4, 11);
        f1072c.append(androidx.constraintlayout.widget.e.Q4, 15);
        f1072c.append(androidx.constraintlayout.widget.e.N4, 12);
        f1072c.append(androidx.constraintlayout.widget.e.F4, 40);
        f1072c.append(androidx.constraintlayout.widget.e.y4, 39);
        f1072c.append(androidx.constraintlayout.widget.e.x4, 41);
        f1072c.append(androidx.constraintlayout.widget.e.E4, 42);
        f1072c.append(androidx.constraintlayout.widget.e.w4, 20);
        f1072c.append(androidx.constraintlayout.widget.e.D4, 37);
        f1072c.append(androidx.constraintlayout.widget.e.q4, 5);
        f1072c.append(androidx.constraintlayout.widget.e.z4, 87);
        f1072c.append(androidx.constraintlayout.widget.e.C4, 87);
        f1072c.append(androidx.constraintlayout.widget.e.A4, 87);
        f1072c.append(androidx.constraintlayout.widget.e.n4, 87);
        f1072c.append(androidx.constraintlayout.widget.e.m4, 87);
        f1072c.append(androidx.constraintlayout.widget.e.L3, 24);
        f1072c.append(androidx.constraintlayout.widget.e.N3, 28);
        f1072c.append(androidx.constraintlayout.widget.e.Z3, 31);
        f1072c.append(androidx.constraintlayout.widget.e.a4, 8);
        f1072c.append(androidx.constraintlayout.widget.e.M3, 34);
        f1072c.append(androidx.constraintlayout.widget.e.O3, 2);
        f1072c.append(androidx.constraintlayout.widget.e.J3, 23);
        f1072c.append(androidx.constraintlayout.widget.e.K3, 21);
        f1072c.append(androidx.constraintlayout.widget.e.G4, 95);
        f1072c.append(androidx.constraintlayout.widget.e.r4, 96);
        f1072c.append(androidx.constraintlayout.widget.e.I3, 22);
        f1072c.append(androidx.constraintlayout.widget.e.P3, 43);
        f1072c.append(androidx.constraintlayout.widget.e.c4, 44);
        f1072c.append(androidx.constraintlayout.widget.e.X3, 45);
        f1072c.append(androidx.constraintlayout.widget.e.Y3, 46);
        f1072c.append(androidx.constraintlayout.widget.e.W3, 60);
        f1072c.append(androidx.constraintlayout.widget.e.U3, 47);
        f1072c.append(androidx.constraintlayout.widget.e.V3, 48);
        f1072c.append(androidx.constraintlayout.widget.e.Q3, 49);
        f1072c.append(androidx.constraintlayout.widget.e.R3, 50);
        f1072c.append(androidx.constraintlayout.widget.e.S3, 51);
        f1072c.append(androidx.constraintlayout.widget.e.T3, 52);
        f1072c.append(androidx.constraintlayout.widget.e.b4, 53);
        f1072c.append(androidx.constraintlayout.widget.e.H4, 54);
        f1072c.append(androidx.constraintlayout.widget.e.s4, 55);
        f1072c.append(androidx.constraintlayout.widget.e.I4, 56);
        f1072c.append(androidx.constraintlayout.widget.e.t4, 57);
        f1072c.append(androidx.constraintlayout.widget.e.J4, 58);
        f1072c.append(androidx.constraintlayout.widget.e.u4, 59);
        f1072c.append(androidx.constraintlayout.widget.e.p4, 62);
        f1072c.append(androidx.constraintlayout.widget.e.o4, 63);
        f1072c.append(androidx.constraintlayout.widget.e.d4, 64);
        f1072c.append(androidx.constraintlayout.widget.e.c5, 65);
        f1072c.append(androidx.constraintlayout.widget.e.j4, 66);
        f1072c.append(androidx.constraintlayout.widget.e.d5, 67);
        f1072c.append(androidx.constraintlayout.widget.e.U4, 79);
        f1072c.append(androidx.constraintlayout.widget.e.H3, 38);
        f1072c.append(androidx.constraintlayout.widget.e.V4, 98);
        f1072c.append(androidx.constraintlayout.widget.e.T4, 68);
        f1072c.append(androidx.constraintlayout.widget.e.K4, 69);
        f1072c.append(androidx.constraintlayout.widget.e.v4, 70);
        f1072c.append(androidx.constraintlayout.widget.e.h4, 71);
        f1072c.append(androidx.constraintlayout.widget.e.f4, 72);
        f1072c.append(androidx.constraintlayout.widget.e.g4, 73);
        f1072c.append(androidx.constraintlayout.widget.e.i4, 74);
        f1072c.append(androidx.constraintlayout.widget.e.e4, 75);
        f1072c.append(androidx.constraintlayout.widget.e.W4, 76);
        f1072c.append(androidx.constraintlayout.widget.e.B4, 77);
        f1072c.append(androidx.constraintlayout.widget.e.e5, 78);
        f1072c.append(androidx.constraintlayout.widget.e.l4, 80);
        f1072c.append(androidx.constraintlayout.widget.e.k4, 81);
        f1072c.append(androidx.constraintlayout.widget.e.X4, 82);
        f1072c.append(androidx.constraintlayout.widget.e.b5, 83);
        f1072c.append(androidx.constraintlayout.widget.e.a5, 84);
        f1072c.append(androidx.constraintlayout.widget.e.Z4, 85);
        f1072c.append(androidx.constraintlayout.widget.e.Y4, 86);
        f1072c.append(androidx.constraintlayout.widget.e.S4, 97);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int D(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    static void E(Object obj, TypedArray typedArray, int i, int i2) {
        if (obj == null) {
            return;
        }
        int i3 = typedArray.peekValue(i).type;
        if (i3 == 3) {
            F(obj, typedArray.getString(i), i2);
            return;
        }
        int dimensionPixelSize = -2;
        boolean z = false;
        if (i3 != 5) {
            int i4 = typedArray.getInt(i, 0);
            if (i4 != -4) {
                dimensionPixelSize = (i4 == -3 || !(i4 == -2 || i4 == -1)) ? 0 : i4;
            } else {
                z = true;
            }
        } else {
            dimensionPixelSize = typedArray.getDimensionPixelSize(i, 0);
        }
        if (obj instanceof ConstraintLayout.b) {
            ConstraintLayout.b bVar = (ConstraintLayout.b) obj;
            if (i2 == 0) {
                ((ViewGroup.MarginLayoutParams) bVar).width = dimensionPixelSize;
                bVar.a0 = z;
                return;
            } else {
                ((ViewGroup.MarginLayoutParams) bVar).height = dimensionPixelSize;
                bVar.b0 = z;
                return;
            }
        }
        if (obj instanceof C0020b) {
            C0020b c0020b = (C0020b) obj;
            if (i2 == 0) {
                c0020b.f1089e = dimensionPixelSize;
                c0020b.o0 = z;
                return;
            } else {
                c0020b.f = dimensionPixelSize;
                c0020b.p0 = z;
                return;
            }
        }
        if (obj instanceof a.C0019a) {
            a.C0019a c0019a = (a.C0019a) obj;
            if (i2 == 0) {
                c0019a.b(23, dimensionPixelSize);
                c0019a.d(80, z);
            } else {
                c0019a.b(21, dimensionPixelSize);
                c0019a.d(81, z);
            }
        }
    }

    static void F(Object obj, String str, int i) {
        if (str == null) {
            return;
        }
        int iIndexOf = str.indexOf(61);
        int length = str.length();
        if (iIndexOf <= 0 || iIndexOf >= length - 1) {
            return;
        }
        String strSubstring = str.substring(0, iIndexOf);
        String strSubstring2 = str.substring(iIndexOf + 1);
        if (strSubstring2.length() > 0) {
            String strTrim = strSubstring.trim();
            String strTrim2 = strSubstring2.trim();
            if ("ratio".equalsIgnoreCase(strTrim)) {
                if (obj instanceof ConstraintLayout.b) {
                    ConstraintLayout.b bVar = (ConstraintLayout.b) obj;
                    if (i == 0) {
                        ((ViewGroup.MarginLayoutParams) bVar).width = 0;
                    } else {
                        ((ViewGroup.MarginLayoutParams) bVar).height = 0;
                    }
                    G(bVar, strTrim2);
                    return;
                }
                if (obj instanceof C0020b) {
                    ((C0020b) obj).B = strTrim2;
                    return;
                } else {
                    if (obj instanceof a.C0019a) {
                        ((a.C0019a) obj).c(5, strTrim2);
                        return;
                    }
                    return;
                }
            }
            try {
                if ("weight".equalsIgnoreCase(strTrim)) {
                    float f = Float.parseFloat(strTrim2);
                    if (obj instanceof ConstraintLayout.b) {
                        ConstraintLayout.b bVar2 = (ConstraintLayout.b) obj;
                        if (i == 0) {
                            ((ViewGroup.MarginLayoutParams) bVar2).width = 0;
                            bVar2.L = f;
                        } else {
                            ((ViewGroup.MarginLayoutParams) bVar2).height = 0;
                            bVar2.M = f;
                        }
                    } else if (obj instanceof C0020b) {
                        C0020b c0020b = (C0020b) obj;
                        if (i == 0) {
                            c0020b.f1089e = 0;
                            c0020b.X = f;
                        } else {
                            c0020b.f = 0;
                            c0020b.W = f;
                        }
                    } else if (obj instanceof a.C0019a) {
                        a.C0019a c0019a = (a.C0019a) obj;
                        if (i == 0) {
                            c0019a.b(23, 0);
                            c0019a.a(39, f);
                        } else {
                            c0019a.b(21, 0);
                            c0019a.a(40, f);
                        }
                    }
                } else {
                    if (!"parent".equalsIgnoreCase(strTrim)) {
                        return;
                    }
                    float fMax = Math.max(0.0f, Math.min(1.0f, Float.parseFloat(strTrim2)));
                    if (obj instanceof ConstraintLayout.b) {
                        ConstraintLayout.b bVar3 = (ConstraintLayout.b) obj;
                        if (i == 0) {
                            ((ViewGroup.MarginLayoutParams) bVar3).width = 0;
                            bVar3.V = fMax;
                            bVar3.P = 2;
                        } else {
                            ((ViewGroup.MarginLayoutParams) bVar3).height = 0;
                            bVar3.W = fMax;
                            bVar3.Q = 2;
                        }
                    } else if (obj instanceof C0020b) {
                        C0020b c0020b2 = (C0020b) obj;
                        if (i == 0) {
                            c0020b2.f1089e = 0;
                            c0020b2.g0 = fMax;
                            c0020b2.a0 = 2;
                        } else {
                            c0020b2.f = 0;
                            c0020b2.h0 = fMax;
                            c0020b2.b0 = 2;
                        }
                    } else if (obj instanceof a.C0019a) {
                        a.C0019a c0019a2 = (a.C0019a) obj;
                        if (i == 0) {
                            c0019a2.b(23, 0);
                            c0019a2.b(54, 2);
                        } else {
                            c0019a2.b(21, 0);
                            c0019a2.b(55, 2);
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
    }

    static void G(ConstraintLayout.b bVar, String str) {
        float fAbs = Float.NaN;
        int i = -1;
        if (str != null) {
            int length = str.length();
            int iIndexOf = str.indexOf(44);
            int i2 = 0;
            if (iIndexOf > 0 && iIndexOf < length - 1) {
                String strSubstring = str.substring(0, iIndexOf);
                if (strSubstring.equalsIgnoreCase("W")) {
                    i = 0;
                } else if (strSubstring.equalsIgnoreCase("H")) {
                    i = 1;
                }
                i2 = iIndexOf + 1;
            }
            int iIndexOf2 = str.indexOf(58);
            try {
                if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                    String strSubstring2 = str.substring(i2);
                    if (strSubstring2.length() > 0) {
                        fAbs = Float.parseFloat(strSubstring2);
                    }
                } else {
                    String strSubstring3 = str.substring(i2, iIndexOf2);
                    String strSubstring4 = str.substring(iIndexOf2 + 1);
                    if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                        float f = Float.parseFloat(strSubstring3);
                        float f2 = Float.parseFloat(strSubstring4);
                        if (f > 0.0f && f2 > 0.0f) {
                            fAbs = i == 1 ? Math.abs(f2 / f) : Math.abs(f / f2);
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        bVar.I = str;
        bVar.J = fAbs;
        bVar.K = i;
    }

    private void H(Context context, a aVar, TypedArray typedArray, boolean z) {
        if (z) {
            I(context, aVar, typedArray);
            return;
        }
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index != androidx.constraintlayout.widget.e.F && androidx.constraintlayout.widget.e.X != index && androidx.constraintlayout.widget.e.Y != index) {
                aVar.f1078d.f1091b = true;
                aVar.f1079e.f1087c = true;
                aVar.f1077c.f1095a = true;
                aVar.f.f1101b = true;
            }
            switch (f1071b.get(index)) {
                case 1:
                    C0020b c0020b = aVar.f1079e;
                    c0020b.s = D(typedArray, index, c0020b.s);
                    break;
                case 2:
                    C0020b c0020b2 = aVar.f1079e;
                    c0020b2.L = typedArray.getDimensionPixelSize(index, c0020b2.L);
                    break;
                case 3:
                    C0020b c0020b3 = aVar.f1079e;
                    c0020b3.r = D(typedArray, index, c0020b3.r);
                    break;
                case 4:
                    C0020b c0020b4 = aVar.f1079e;
                    c0020b4.q = D(typedArray, index, c0020b4.q);
                    break;
                case 5:
                    aVar.f1079e.B = typedArray.getString(index);
                    break;
                case 6:
                    C0020b c0020b5 = aVar.f1079e;
                    c0020b5.F = typedArray.getDimensionPixelOffset(index, c0020b5.F);
                    break;
                case 7:
                    C0020b c0020b6 = aVar.f1079e;
                    c0020b6.G = typedArray.getDimensionPixelOffset(index, c0020b6.G);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0020b c0020b7 = aVar.f1079e;
                        c0020b7.M = typedArray.getDimensionPixelSize(index, c0020b7.M);
                    }
                    break;
                case 9:
                    C0020b c0020b8 = aVar.f1079e;
                    c0020b8.y = D(typedArray, index, c0020b8.y);
                    break;
                case 10:
                    C0020b c0020b9 = aVar.f1079e;
                    c0020b9.x = D(typedArray, index, c0020b9.x);
                    break;
                case 11:
                    C0020b c0020b10 = aVar.f1079e;
                    c0020b10.S = typedArray.getDimensionPixelSize(index, c0020b10.S);
                    break;
                case 12:
                    C0020b c0020b11 = aVar.f1079e;
                    c0020b11.T = typedArray.getDimensionPixelSize(index, c0020b11.T);
                    break;
                case 13:
                    C0020b c0020b12 = aVar.f1079e;
                    c0020b12.P = typedArray.getDimensionPixelSize(index, c0020b12.P);
                    break;
                case 14:
                    C0020b c0020b13 = aVar.f1079e;
                    c0020b13.R = typedArray.getDimensionPixelSize(index, c0020b13.R);
                    break;
                case 15:
                    C0020b c0020b14 = aVar.f1079e;
                    c0020b14.U = typedArray.getDimensionPixelSize(index, c0020b14.U);
                    break;
                case 16:
                    C0020b c0020b15 = aVar.f1079e;
                    c0020b15.Q = typedArray.getDimensionPixelSize(index, c0020b15.Q);
                    break;
                case 17:
                    C0020b c0020b16 = aVar.f1079e;
                    c0020b16.g = typedArray.getDimensionPixelOffset(index, c0020b16.g);
                    break;
                case 18:
                    C0020b c0020b17 = aVar.f1079e;
                    c0020b17.h = typedArray.getDimensionPixelOffset(index, c0020b17.h);
                    break;
                case 19:
                    C0020b c0020b18 = aVar.f1079e;
                    c0020b18.i = typedArray.getFloat(index, c0020b18.i);
                    break;
                case 20:
                    C0020b c0020b19 = aVar.f1079e;
                    c0020b19.z = typedArray.getFloat(index, c0020b19.z);
                    break;
                case 21:
                    C0020b c0020b20 = aVar.f1079e;
                    c0020b20.f = typedArray.getLayoutDimension(index, c0020b20.f);
                    break;
                case 22:
                    d dVar = aVar.f1077c;
                    dVar.f1096b = typedArray.getInt(index, dVar.f1096b);
                    d dVar2 = aVar.f1077c;
                    dVar2.f1096b = f1070a[dVar2.f1096b];
                    break;
                case 23:
                    C0020b c0020b21 = aVar.f1079e;
                    c0020b21.f1089e = typedArray.getLayoutDimension(index, c0020b21.f1089e);
                    break;
                case 24:
                    C0020b c0020b22 = aVar.f1079e;
                    c0020b22.I = typedArray.getDimensionPixelSize(index, c0020b22.I);
                    break;
                case 25:
                    C0020b c0020b23 = aVar.f1079e;
                    c0020b23.k = D(typedArray, index, c0020b23.k);
                    break;
                case 26:
                    C0020b c0020b24 = aVar.f1079e;
                    c0020b24.l = D(typedArray, index, c0020b24.l);
                    break;
                case 27:
                    C0020b c0020b25 = aVar.f1079e;
                    c0020b25.H = typedArray.getInt(index, c0020b25.H);
                    break;
                case 28:
                    C0020b c0020b26 = aVar.f1079e;
                    c0020b26.J = typedArray.getDimensionPixelSize(index, c0020b26.J);
                    break;
                case 29:
                    C0020b c0020b27 = aVar.f1079e;
                    c0020b27.m = D(typedArray, index, c0020b27.m);
                    break;
                case 30:
                    C0020b c0020b28 = aVar.f1079e;
                    c0020b28.n = D(typedArray, index, c0020b28.n);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0020b c0020b29 = aVar.f1079e;
                        c0020b29.N = typedArray.getDimensionPixelSize(index, c0020b29.N);
                    }
                    break;
                case 32:
                    C0020b c0020b30 = aVar.f1079e;
                    c0020b30.v = D(typedArray, index, c0020b30.v);
                    break;
                case 33:
                    C0020b c0020b31 = aVar.f1079e;
                    c0020b31.w = D(typedArray, index, c0020b31.w);
                    break;
                case 34:
                    C0020b c0020b32 = aVar.f1079e;
                    c0020b32.K = typedArray.getDimensionPixelSize(index, c0020b32.K);
                    break;
                case 35:
                    C0020b c0020b33 = aVar.f1079e;
                    c0020b33.p = D(typedArray, index, c0020b33.p);
                    break;
                case 36:
                    C0020b c0020b34 = aVar.f1079e;
                    c0020b34.o = D(typedArray, index, c0020b34.o);
                    break;
                case 37:
                    C0020b c0020b35 = aVar.f1079e;
                    c0020b35.A = typedArray.getFloat(index, c0020b35.A);
                    break;
                case 38:
                    aVar.f1075a = typedArray.getResourceId(index, aVar.f1075a);
                    break;
                case 39:
                    C0020b c0020b36 = aVar.f1079e;
                    c0020b36.X = typedArray.getFloat(index, c0020b36.X);
                    break;
                case 40:
                    C0020b c0020b37 = aVar.f1079e;
                    c0020b37.W = typedArray.getFloat(index, c0020b37.W);
                    break;
                case 41:
                    C0020b c0020b38 = aVar.f1079e;
                    c0020b38.Y = typedArray.getInt(index, c0020b38.Y);
                    break;
                case 42:
                    C0020b c0020b39 = aVar.f1079e;
                    c0020b39.Z = typedArray.getInt(index, c0020b39.Z);
                    break;
                case 43:
                    d dVar3 = aVar.f1077c;
                    dVar3.f1098d = typedArray.getFloat(index, dVar3.f1098d);
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar = aVar.f;
                        eVar.n = true;
                        eVar.o = typedArray.getDimension(index, eVar.o);
                    }
                    break;
                case 45:
                    e eVar2 = aVar.f;
                    eVar2.f1103d = typedArray.getFloat(index, eVar2.f1103d);
                    break;
                case 46:
                    e eVar3 = aVar.f;
                    eVar3.f1104e = typedArray.getFloat(index, eVar3.f1104e);
                    break;
                case 47:
                    e eVar4 = aVar.f;
                    eVar4.f = typedArray.getFloat(index, eVar4.f);
                    break;
                case 48:
                    e eVar5 = aVar.f;
                    eVar5.g = typedArray.getFloat(index, eVar5.g);
                    break;
                case 49:
                    e eVar6 = aVar.f;
                    eVar6.h = typedArray.getDimension(index, eVar6.h);
                    break;
                case 50:
                    e eVar7 = aVar.f;
                    eVar7.i = typedArray.getDimension(index, eVar7.i);
                    break;
                case 51:
                    e eVar8 = aVar.f;
                    eVar8.k = typedArray.getDimension(index, eVar8.k);
                    break;
                case 52:
                    e eVar9 = aVar.f;
                    eVar9.l = typedArray.getDimension(index, eVar9.l);
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar10 = aVar.f;
                        eVar10.m = typedArray.getDimension(index, eVar10.m);
                    }
                    break;
                case 54:
                    C0020b c0020b40 = aVar.f1079e;
                    c0020b40.a0 = typedArray.getInt(index, c0020b40.a0);
                    break;
                case 55:
                    C0020b c0020b41 = aVar.f1079e;
                    c0020b41.b0 = typedArray.getInt(index, c0020b41.b0);
                    break;
                case 56:
                    C0020b c0020b42 = aVar.f1079e;
                    c0020b42.c0 = typedArray.getDimensionPixelSize(index, c0020b42.c0);
                    break;
                case 57:
                    C0020b c0020b43 = aVar.f1079e;
                    c0020b43.d0 = typedArray.getDimensionPixelSize(index, c0020b43.d0);
                    break;
                case 58:
                    C0020b c0020b44 = aVar.f1079e;
                    c0020b44.e0 = typedArray.getDimensionPixelSize(index, c0020b44.e0);
                    break;
                case 59:
                    C0020b c0020b45 = aVar.f1079e;
                    c0020b45.f0 = typedArray.getDimensionPixelSize(index, c0020b45.f0);
                    break;
                case 60:
                    e eVar11 = aVar.f;
                    eVar11.f1102c = typedArray.getFloat(index, eVar11.f1102c);
                    break;
                case 61:
                    C0020b c0020b46 = aVar.f1079e;
                    c0020b46.C = D(typedArray, index, c0020b46.C);
                    break;
                case 62:
                    C0020b c0020b47 = aVar.f1079e;
                    c0020b47.D = typedArray.getDimensionPixelSize(index, c0020b47.D);
                    break;
                case 63:
                    C0020b c0020b48 = aVar.f1079e;
                    c0020b48.E = typedArray.getFloat(index, c0020b48.E);
                    break;
                case 64:
                    c cVar = aVar.f1078d;
                    cVar.f1092c = D(typedArray, index, cVar.f1092c);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.f1078d.f1094e = typedArray.getString(index);
                    } else {
                        aVar.f1078d.f1094e = androidx.constraintlayout.core.j.a.c.f746b[typedArray.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    aVar.f1078d.g = typedArray.getInt(index, 0);
                    break;
                case 67:
                    c cVar2 = aVar.f1078d;
                    cVar2.j = typedArray.getFloat(index, cVar2.j);
                    break;
                case 68:
                    d dVar4 = aVar.f1077c;
                    dVar4.f1099e = typedArray.getFloat(index, dVar4.f1099e);
                    break;
                case 69:
                    aVar.f1079e.g0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    aVar.f1079e.h0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    C0020b c0020b49 = aVar.f1079e;
                    c0020b49.i0 = typedArray.getInt(index, c0020b49.i0);
                    break;
                case 73:
                    C0020b c0020b50 = aVar.f1079e;
                    c0020b50.j0 = typedArray.getDimensionPixelSize(index, c0020b50.j0);
                    break;
                case 74:
                    aVar.f1079e.m0 = typedArray.getString(index);
                    break;
                case 75:
                    C0020b c0020b51 = aVar.f1079e;
                    c0020b51.q0 = typedArray.getBoolean(index, c0020b51.q0);
                    break;
                case 76:
                    c cVar3 = aVar.f1078d;
                    cVar3.f = typedArray.getInt(index, cVar3.f);
                    break;
                case 77:
                    aVar.f1079e.n0 = typedArray.getString(index);
                    break;
                case 78:
                    d dVar5 = aVar.f1077c;
                    dVar5.f1097c = typedArray.getInt(index, dVar5.f1097c);
                    break;
                case 79:
                    c cVar4 = aVar.f1078d;
                    cVar4.h = typedArray.getFloat(index, cVar4.h);
                    break;
                case 80:
                    C0020b c0020b52 = aVar.f1079e;
                    c0020b52.o0 = typedArray.getBoolean(index, c0020b52.o0);
                    break;
                case 81:
                    C0020b c0020b53 = aVar.f1079e;
                    c0020b53.p0 = typedArray.getBoolean(index, c0020b53.p0);
                    break;
                case 82:
                    c cVar5 = aVar.f1078d;
                    cVar5.f1093d = typedArray.getInteger(index, cVar5.f1093d);
                    break;
                case 83:
                    e eVar12 = aVar.f;
                    eVar12.j = D(typedArray, index, eVar12.j);
                    break;
                case 84:
                    c cVar6 = aVar.f1078d;
                    cVar6.l = typedArray.getInteger(index, cVar6.l);
                    break;
                case 85:
                    c cVar7 = aVar.f1078d;
                    cVar7.k = typedArray.getFloat(index, cVar7.k);
                    break;
                case 86:
                    int i2 = typedArray.peekValue(index).type;
                    if (i2 == 1) {
                        aVar.f1078d.o = typedArray.getResourceId(index, -1);
                        c cVar8 = aVar.f1078d;
                        if (cVar8.o != -1) {
                            cVar8.n = -2;
                        }
                    } else if (i2 == 3) {
                        aVar.f1078d.m = typedArray.getString(index);
                        if (aVar.f1078d.m.indexOf("/") > 0) {
                            aVar.f1078d.o = typedArray.getResourceId(index, -1);
                            aVar.f1078d.n = -2;
                        } else {
                            aVar.f1078d.n = -1;
                        }
                    } else {
                        c cVar9 = aVar.f1078d;
                        cVar9.n = typedArray.getInteger(index, cVar9.o);
                    }
                    break;
                case 87:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1071b.get(index));
                    break;
                case 88:
                case 89:
                case 90:
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1071b.get(index));
                    break;
                case 91:
                    C0020b c0020b54 = aVar.f1079e;
                    c0020b54.t = D(typedArray, index, c0020b54.t);
                    break;
                case 92:
                    C0020b c0020b55 = aVar.f1079e;
                    c0020b55.u = D(typedArray, index, c0020b55.u);
                    break;
                case 93:
                    C0020b c0020b56 = aVar.f1079e;
                    c0020b56.O = typedArray.getDimensionPixelSize(index, c0020b56.O);
                    break;
                case 94:
                    C0020b c0020b57 = aVar.f1079e;
                    c0020b57.V = typedArray.getDimensionPixelSize(index, c0020b57.V);
                    break;
                case 95:
                    E(aVar.f1079e, typedArray, index, 0);
                    break;
                case 96:
                    E(aVar.f1079e, typedArray, index, 1);
                    break;
                case 97:
                    C0020b c0020b58 = aVar.f1079e;
                    c0020b58.r0 = typedArray.getInt(index, c0020b58.r0);
                    break;
            }
        }
        C0020b c0020b59 = aVar.f1079e;
        if (c0020b59.m0 != null) {
            c0020b59.l0 = null;
        }
    }

    private static void I(Context context, a aVar, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        a.C0019a c0019a = new a.C0019a();
        aVar.h = c0019a;
        aVar.f1078d.f1091b = false;
        aVar.f1079e.f1087c = false;
        aVar.f1077c.f1095a = false;
        aVar.f.f1101b = false;
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (f1072c.get(index)) {
                case 2:
                    c0019a.b(2, typedArray.getDimensionPixelSize(index, aVar.f1079e.L));
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
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1071b.get(index));
                    break;
                case 5:
                    c0019a.c(5, typedArray.getString(index));
                    break;
                case 6:
                    c0019a.b(6, typedArray.getDimensionPixelOffset(index, aVar.f1079e.F));
                    break;
                case 7:
                    c0019a.b(7, typedArray.getDimensionPixelOffset(index, aVar.f1079e.G));
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        c0019a.b(8, typedArray.getDimensionPixelSize(index, aVar.f1079e.M));
                    }
                    break;
                case 11:
                    c0019a.b(11, typedArray.getDimensionPixelSize(index, aVar.f1079e.S));
                    break;
                case 12:
                    c0019a.b(12, typedArray.getDimensionPixelSize(index, aVar.f1079e.T));
                    break;
                case 13:
                    c0019a.b(13, typedArray.getDimensionPixelSize(index, aVar.f1079e.P));
                    break;
                case 14:
                    c0019a.b(14, typedArray.getDimensionPixelSize(index, aVar.f1079e.R));
                    break;
                case 15:
                    c0019a.b(15, typedArray.getDimensionPixelSize(index, aVar.f1079e.U));
                    break;
                case 16:
                    c0019a.b(16, typedArray.getDimensionPixelSize(index, aVar.f1079e.Q));
                    break;
                case 17:
                    c0019a.b(17, typedArray.getDimensionPixelOffset(index, aVar.f1079e.g));
                    break;
                case 18:
                    c0019a.b(18, typedArray.getDimensionPixelOffset(index, aVar.f1079e.h));
                    break;
                case 19:
                    c0019a.a(19, typedArray.getFloat(index, aVar.f1079e.i));
                    break;
                case 20:
                    c0019a.a(20, typedArray.getFloat(index, aVar.f1079e.z));
                    break;
                case 21:
                    c0019a.b(21, typedArray.getLayoutDimension(index, aVar.f1079e.f));
                    break;
                case 22:
                    c0019a.b(22, f1070a[typedArray.getInt(index, aVar.f1077c.f1096b)]);
                    break;
                case 23:
                    c0019a.b(23, typedArray.getLayoutDimension(index, aVar.f1079e.f1089e));
                    break;
                case 24:
                    c0019a.b(24, typedArray.getDimensionPixelSize(index, aVar.f1079e.I));
                    break;
                case 27:
                    c0019a.b(27, typedArray.getInt(index, aVar.f1079e.H));
                    break;
                case 28:
                    c0019a.b(28, typedArray.getDimensionPixelSize(index, aVar.f1079e.J));
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        c0019a.b(31, typedArray.getDimensionPixelSize(index, aVar.f1079e.N));
                    }
                    break;
                case 34:
                    c0019a.b(34, typedArray.getDimensionPixelSize(index, aVar.f1079e.K));
                    break;
                case 37:
                    c0019a.a(37, typedArray.getFloat(index, aVar.f1079e.A));
                    break;
                case 38:
                    int resourceId = typedArray.getResourceId(index, aVar.f1075a);
                    aVar.f1075a = resourceId;
                    c0019a.b(38, resourceId);
                    break;
                case 39:
                    c0019a.a(39, typedArray.getFloat(index, aVar.f1079e.X));
                    break;
                case 40:
                    c0019a.a(40, typedArray.getFloat(index, aVar.f1079e.W));
                    break;
                case 41:
                    c0019a.b(41, typedArray.getInt(index, aVar.f1079e.Y));
                    break;
                case 42:
                    c0019a.b(42, typedArray.getInt(index, aVar.f1079e.Z));
                    break;
                case 43:
                    c0019a.a(43, typedArray.getFloat(index, aVar.f1077c.f1098d));
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        c0019a.d(44, true);
                        c0019a.a(44, typedArray.getDimension(index, aVar.f.o));
                    }
                    break;
                case 45:
                    c0019a.a(45, typedArray.getFloat(index, aVar.f.f1103d));
                    break;
                case 46:
                    c0019a.a(46, typedArray.getFloat(index, aVar.f.f1104e));
                    break;
                case 47:
                    c0019a.a(47, typedArray.getFloat(index, aVar.f.f));
                    break;
                case 48:
                    c0019a.a(48, typedArray.getFloat(index, aVar.f.g));
                    break;
                case 49:
                    c0019a.a(49, typedArray.getDimension(index, aVar.f.h));
                    break;
                case 50:
                    c0019a.a(50, typedArray.getDimension(index, aVar.f.i));
                    break;
                case 51:
                    c0019a.a(51, typedArray.getDimension(index, aVar.f.k));
                    break;
                case 52:
                    c0019a.a(52, typedArray.getDimension(index, aVar.f.l));
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        c0019a.a(53, typedArray.getDimension(index, aVar.f.m));
                    }
                    break;
                case 54:
                    c0019a.b(54, typedArray.getInt(index, aVar.f1079e.a0));
                    break;
                case 55:
                    c0019a.b(55, typedArray.getInt(index, aVar.f1079e.b0));
                    break;
                case 56:
                    c0019a.b(56, typedArray.getDimensionPixelSize(index, aVar.f1079e.c0));
                    break;
                case 57:
                    c0019a.b(57, typedArray.getDimensionPixelSize(index, aVar.f1079e.d0));
                    break;
                case 58:
                    c0019a.b(58, typedArray.getDimensionPixelSize(index, aVar.f1079e.e0));
                    break;
                case 59:
                    c0019a.b(59, typedArray.getDimensionPixelSize(index, aVar.f1079e.f0));
                    break;
                case 60:
                    c0019a.a(60, typedArray.getFloat(index, aVar.f.f1102c));
                    break;
                case 62:
                    c0019a.b(62, typedArray.getDimensionPixelSize(index, aVar.f1079e.D));
                    break;
                case 63:
                    c0019a.a(63, typedArray.getFloat(index, aVar.f1079e.E));
                    break;
                case 64:
                    c0019a.b(64, D(typedArray, index, aVar.f1078d.f1092c));
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        c0019a.c(65, typedArray.getString(index));
                    } else {
                        c0019a.c(65, androidx.constraintlayout.core.j.a.c.f746b[typedArray.getInteger(index, 0)]);
                    }
                    break;
                case 66:
                    c0019a.b(66, typedArray.getInt(index, 0));
                    break;
                case 67:
                    c0019a.a(67, typedArray.getFloat(index, aVar.f1078d.j));
                    break;
                case 68:
                    c0019a.a(68, typedArray.getFloat(index, aVar.f1077c.f1099e));
                    break;
                case 69:
                    c0019a.a(69, typedArray.getFloat(index, 1.0f));
                    break;
                case 70:
                    c0019a.a(70, typedArray.getFloat(index, 1.0f));
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    c0019a.b(72, typedArray.getInt(index, aVar.f1079e.i0));
                    break;
                case 73:
                    c0019a.b(73, typedArray.getDimensionPixelSize(index, aVar.f1079e.j0));
                    break;
                case 74:
                    c0019a.c(74, typedArray.getString(index));
                    break;
                case 75:
                    c0019a.d(75, typedArray.getBoolean(index, aVar.f1079e.q0));
                    break;
                case 76:
                    c0019a.b(76, typedArray.getInt(index, aVar.f1078d.f));
                    break;
                case 77:
                    c0019a.c(77, typedArray.getString(index));
                    break;
                case 78:
                    c0019a.b(78, typedArray.getInt(index, aVar.f1077c.f1097c));
                    break;
                case 79:
                    c0019a.a(79, typedArray.getFloat(index, aVar.f1078d.h));
                    break;
                case 80:
                    c0019a.d(80, typedArray.getBoolean(index, aVar.f1079e.o0));
                    break;
                case 81:
                    c0019a.d(81, typedArray.getBoolean(index, aVar.f1079e.p0));
                    break;
                case 82:
                    c0019a.b(82, typedArray.getInteger(index, aVar.f1078d.f1093d));
                    break;
                case 83:
                    c0019a.b(83, D(typedArray, index, aVar.f.j));
                    break;
                case 84:
                    c0019a.b(84, typedArray.getInteger(index, aVar.f1078d.l));
                    break;
                case 85:
                    c0019a.a(85, typedArray.getFloat(index, aVar.f1078d.k));
                    break;
                case 86:
                    int i2 = typedArray.peekValue(index).type;
                    if (i2 == 1) {
                        aVar.f1078d.o = typedArray.getResourceId(index, -1);
                        c0019a.b(89, aVar.f1078d.o);
                        c cVar = aVar.f1078d;
                        if (cVar.o != -1) {
                            cVar.n = -2;
                            c0019a.b(88, -2);
                        }
                    } else if (i2 == 3) {
                        aVar.f1078d.m = typedArray.getString(index);
                        c0019a.c(90, aVar.f1078d.m);
                        if (aVar.f1078d.m.indexOf("/") > 0) {
                            aVar.f1078d.o = typedArray.getResourceId(index, -1);
                            c0019a.b(89, aVar.f1078d.o);
                            aVar.f1078d.n = -2;
                            c0019a.b(88, -2);
                        } else {
                            aVar.f1078d.n = -1;
                            c0019a.b(88, -1);
                        }
                    } else {
                        c cVar2 = aVar.f1078d;
                        cVar2.n = typedArray.getInteger(index, cVar2.o);
                        c0019a.b(88, aVar.f1078d.n);
                    }
                    break;
                case 87:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1071b.get(index));
                    break;
                case 93:
                    c0019a.b(93, typedArray.getDimensionPixelSize(index, aVar.f1079e.O));
                    break;
                case 94:
                    c0019a.b(94, typedArray.getDimensionPixelSize(index, aVar.f1079e.V));
                    break;
                case 95:
                    E(c0019a, typedArray, index, 0);
                    break;
                case 96:
                    E(c0019a, typedArray, index, 1);
                    break;
                case 97:
                    c0019a.b(97, typedArray.getInt(index, aVar.f1079e.r0));
                    break;
                case 98:
                    if (MotionLayout.z) {
                        int resourceId2 = typedArray.getResourceId(index, aVar.f1075a);
                        aVar.f1075a = resourceId2;
                        if (resourceId2 == -1) {
                            aVar.f1076b = typedArray.getString(index);
                        }
                    } else if (typedArray.peekValue(index).type == 3) {
                        aVar.f1076b = typedArray.getString(index);
                    } else {
                        aVar.f1075a = typedArray.getResourceId(index, aVar.f1075a);
                    }
                    break;
                case 99:
                    c0019a.d(99, typedArray.getBoolean(index, aVar.f1079e.j));
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void L(a aVar, int i, float f) {
        if (i == 19) {
            aVar.f1079e.i = f;
        }
        if (i == 20) {
            aVar.f1079e.z = f;
            return;
        }
        if (i == 37) {
            aVar.f1079e.A = f;
            return;
        }
        if (i == 60) {
            aVar.f.f1102c = f;
            return;
        }
        if (i == 63) {
            aVar.f1079e.E = f;
            return;
        }
        if (i == 79) {
            aVar.f1078d.h = f;
            return;
        }
        if (i == 85) {
            aVar.f1078d.k = f;
            return;
        }
        if (i != 87) {
            if (i == 39) {
                aVar.f1079e.X = f;
                return;
            }
            if (i == 40) {
                aVar.f1079e.W = f;
                return;
            }
            switch (i) {
                case 43:
                    aVar.f1077c.f1098d = f;
                    break;
                case 44:
                    e eVar = aVar.f;
                    eVar.o = f;
                    eVar.n = true;
                    break;
                case 45:
                    aVar.f.f1103d = f;
                    break;
                case 46:
                    aVar.f.f1104e = f;
                    break;
                case 47:
                    aVar.f.f = f;
                    break;
                case 48:
                    aVar.f.g = f;
                    break;
                case 49:
                    aVar.f.h = f;
                    break;
                case 50:
                    aVar.f.i = f;
                    break;
                case 51:
                    aVar.f.k = f;
                    break;
                case 52:
                    aVar.f.l = f;
                    break;
                case 53:
                    aVar.f.m = f;
                    break;
                default:
                    switch (i) {
                        case 67:
                            aVar.f1078d.j = f;
                            break;
                        case 68:
                            aVar.f1077c.f1099e = f;
                            break;
                        case 69:
                            aVar.f1079e.g0 = f;
                            break;
                        case 70:
                            aVar.f1079e.h0 = f;
                            break;
                        default:
                            Log.w("ConstraintSet", "Unknown attribute 0x");
                            break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void M(a aVar, int i, int i2) {
        if (i == 6) {
            aVar.f1079e.F = i2;
        }
        if (i == 7) {
            aVar.f1079e.G = i2;
            return;
        }
        if (i == 8) {
            aVar.f1079e.M = i2;
            return;
        }
        if (i == 27) {
            aVar.f1079e.H = i2;
            return;
        }
        if (i == 28) {
            aVar.f1079e.J = i2;
            return;
        }
        if (i == 41) {
            aVar.f1079e.Y = i2;
            return;
        }
        if (i == 42) {
            aVar.f1079e.Z = i2;
            return;
        }
        if (i == 61) {
            aVar.f1079e.C = i2;
            return;
        }
        if (i == 62) {
            aVar.f1079e.D = i2;
            return;
        }
        if (i == 72) {
            aVar.f1079e.i0 = i2;
            return;
        }
        if (i == 73) {
            aVar.f1079e.j0 = i2;
            return;
        }
        switch (i) {
            case 2:
                aVar.f1079e.L = i2;
                break;
            case 11:
                aVar.f1079e.S = i2;
                break;
            case 12:
                aVar.f1079e.T = i2;
                break;
            case 13:
                aVar.f1079e.P = i2;
                break;
            case 14:
                aVar.f1079e.R = i2;
                break;
            case 15:
                aVar.f1079e.U = i2;
                break;
            case 16:
                aVar.f1079e.Q = i2;
                break;
            case 17:
                aVar.f1079e.g = i2;
                break;
            case 18:
                aVar.f1079e.h = i2;
                break;
            case 31:
                aVar.f1079e.N = i2;
                break;
            case 34:
                aVar.f1079e.K = i2;
                break;
            case 38:
                aVar.f1075a = i2;
                break;
            case 64:
                aVar.f1078d.f1092c = i2;
                break;
            case 66:
                aVar.f1078d.g = i2;
                break;
            case 76:
                aVar.f1078d.f = i2;
                break;
            case 78:
                aVar.f1077c.f1097c = i2;
                break;
            case 93:
                aVar.f1079e.O = i2;
                break;
            case 94:
                aVar.f1079e.V = i2;
                break;
            case 97:
                aVar.f1079e.r0 = i2;
                break;
            default:
                switch (i) {
                    case 21:
                        aVar.f1079e.f = i2;
                        break;
                    case 22:
                        aVar.f1077c.f1096b = i2;
                        break;
                    case 23:
                        aVar.f1079e.f1089e = i2;
                        break;
                    case 24:
                        aVar.f1079e.I = i2;
                        break;
                    default:
                        switch (i) {
                            case 54:
                                aVar.f1079e.a0 = i2;
                                break;
                            case 55:
                                aVar.f1079e.b0 = i2;
                                break;
                            case 56:
                                aVar.f1079e.c0 = i2;
                                break;
                            case 57:
                                aVar.f1079e.d0 = i2;
                                break;
                            case 58:
                                aVar.f1079e.e0 = i2;
                                break;
                            case 59:
                                aVar.f1079e.f0 = i2;
                                break;
                            default:
                                switch (i) {
                                    case 82:
                                        aVar.f1078d.f1093d = i2;
                                        break;
                                    case 83:
                                        aVar.f.j = i2;
                                        break;
                                    case 84:
                                        aVar.f1078d.l = i2;
                                        break;
                                    default:
                                        switch (i) {
                                            case 87:
                                                break;
                                            case 88:
                                                aVar.f1078d.n = i2;
                                                break;
                                            case 89:
                                                aVar.f1078d.o = i2;
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void N(a aVar, int i, String str) {
        if (i == 5) {
            aVar.f1079e.B = str;
            return;
        }
        if (i == 65) {
            aVar.f1078d.f1094e = str;
            return;
        }
        if (i == 74) {
            C0020b c0020b = aVar.f1079e;
            c0020b.m0 = str;
            c0020b.l0 = null;
        } else if (i == 77) {
            aVar.f1079e.n0 = str;
        } else if (i != 87) {
            if (i != 90) {
                Log.w("ConstraintSet", "Unknown attribute 0x");
            } else {
                aVar.f1078d.m = str;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void O(a aVar, int i, boolean z) {
        if (i == 44) {
            aVar.f.n = z;
            return;
        }
        if (i == 75) {
            aVar.f1079e.q0 = z;
            return;
        }
        if (i != 87) {
            if (i == 80) {
                aVar.f1079e.o0 = z;
            } else if (i != 81) {
                Log.w("ConstraintSet", "Unknown attribute 0x");
            } else {
                aVar.f1079e.p0 = z;
            }
        }
    }

    public static a m(Context context, XmlPullParser xmlPullParser) {
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xmlPullParser);
        a aVar = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSetAsAttributeSet, androidx.constraintlayout.widget.e.F3);
        I(context, aVar, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    private int[] r(View view, String str) {
        int iIntValue;
        Object objF;
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
            if (iIntValue == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (objF = ((ConstraintLayout) view.getParent()).f(0, strTrim)) != null && (objF instanceof Integer)) {
                iIntValue = ((Integer) objF).intValue();
            }
            iArr[i2] = iIntValue;
            i++;
            i2++;
        }
        return i2 != strArrSplit.length ? Arrays.copyOf(iArr, i2) : iArr;
    }

    private a s(Context context, AttributeSet attributeSet, boolean z) {
        a aVar = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z ? androidx.constraintlayout.widget.e.F3 : androidx.constraintlayout.widget.e.D);
        H(context, aVar, typedArrayObtainStyledAttributes, z);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    private a t(int i) {
        if (!this.j.containsKey(Integer.valueOf(i))) {
            this.j.put(Integer.valueOf(i), new a());
        }
        return this.j.get(Integer.valueOf(i));
    }

    public int A(int i) {
        return t(i).f1079e.f1089e;
    }

    public void B(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    a aVarS = s(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        aVarS.f1079e.f1086b = true;
                    }
                    this.j.put(Integer.valueOf(aVarS.f1075a), aVarS);
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x01cb, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void C(android.content.Context r10, org.xmlpull.v1.XmlPullParser r11) {
        /*
            Method dump skipped, instruction units count: 560
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.b.C(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public void J(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.i && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.j.containsKey(Integer.valueOf(id))) {
                this.j.put(Integer.valueOf(id), new a());
            }
            a aVar = this.j.get(Integer.valueOf(id));
            if (aVar != null) {
                if (!aVar.f1079e.f1087c) {
                    aVar.g(id, bVar);
                    if (childAt instanceof ConstraintHelper) {
                        aVar.f1079e.l0 = ((ConstraintHelper) childAt).getReferencedIds();
                        if (childAt instanceof Barrier) {
                            Barrier barrier = (Barrier) childAt;
                            aVar.f1079e.q0 = barrier.getAllowsGoneWidget();
                            aVar.f1079e.i0 = barrier.getType();
                            aVar.f1079e.j0 = barrier.getMargin();
                        }
                    }
                    aVar.f1079e.f1087c = true;
                }
                d dVar = aVar.f1077c;
                if (!dVar.f1095a) {
                    dVar.f1096b = childAt.getVisibility();
                    aVar.f1077c.f1098d = childAt.getAlpha();
                    aVar.f1077c.f1095a = true;
                }
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 17) {
                    e eVar = aVar.f;
                    if (!eVar.f1101b) {
                        eVar.f1101b = true;
                        eVar.f1102c = childAt.getRotation();
                        aVar.f.f1103d = childAt.getRotationX();
                        aVar.f.f1104e = childAt.getRotationY();
                        aVar.f.f = childAt.getScaleX();
                        aVar.f.g = childAt.getScaleY();
                        float pivotX = childAt.getPivotX();
                        float pivotY = childAt.getPivotY();
                        if (pivotX != 0.0d || pivotY != 0.0d) {
                            e eVar2 = aVar.f;
                            eVar2.h = pivotX;
                            eVar2.i = pivotY;
                        }
                        aVar.f.k = childAt.getTranslationX();
                        aVar.f.l = childAt.getTranslationY();
                        if (i2 >= 21) {
                            aVar.f.m = childAt.getTranslationZ();
                            e eVar3 = aVar.f;
                            if (eVar3.n) {
                                eVar3.o = childAt.getElevation();
                            }
                        }
                    }
                }
            }
        }
    }

    public void K(b bVar) {
        for (Integer num : bVar.j.keySet()) {
            int iIntValue = num.intValue();
            a aVar = bVar.j.get(num);
            if (!this.j.containsKey(Integer.valueOf(iIntValue))) {
                this.j.put(Integer.valueOf(iIntValue), new a());
            }
            a aVar2 = this.j.get(Integer.valueOf(iIntValue));
            if (aVar2 != null) {
                C0020b c0020b = aVar2.f1079e;
                if (!c0020b.f1087c) {
                    c0020b.a(aVar.f1079e);
                }
                d dVar = aVar2.f1077c;
                if (!dVar.f1095a) {
                    dVar.a(aVar.f1077c);
                }
                e eVar = aVar2.f;
                if (!eVar.f1101b) {
                    eVar.a(aVar.f);
                }
                c cVar = aVar2.f1078d;
                if (!cVar.f1091b) {
                    cVar.a(aVar.f1078d);
                }
                for (String str : aVar.g.keySet()) {
                    if (!aVar2.g.containsKey(str)) {
                        aVar2.g.put(str, aVar.g.get(str));
                    }
                }
            }
        }
    }

    public void P(boolean z) {
        this.i = z;
    }

    public void Q(boolean z) {
        this.f1073d = z;
    }

    public void g(ConstraintLayout constraintLayout) {
        a aVar;
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.j.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + androidx.constraintlayout.motion.widget.b.d(childAt));
            } else {
                if (this.i && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (this.j.containsKey(Integer.valueOf(id)) && (aVar = this.j.get(Integer.valueOf(id))) != null) {
                    ConstraintAttribute.j(childAt, aVar.g);
                }
            }
        }
    }

    public void h(b bVar) {
        for (a aVar : bVar.j.values()) {
            if (aVar.h != null) {
                if (aVar.f1076b != null) {
                    Iterator<Integer> it = this.j.keySet().iterator();
                    while (it.hasNext()) {
                        a aVarU = u(it.next().intValue());
                        String str = aVarU.f1079e.n0;
                        if (str != null && aVar.f1076b.matches(str)) {
                            aVar.h.e(aVarU);
                            aVarU.g.putAll((HashMap) aVar.g.clone());
                        }
                    }
                } else {
                    aVar.h.e(u(aVar.f1075a));
                }
            }
        }
    }

    public void i(ConstraintLayout constraintLayout) {
        k(constraintLayout, true);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public void j(ConstraintHelper constraintHelper, ConstraintWidget constraintWidget, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        a aVar;
        int id = constraintHelper.getId();
        if (this.j.containsKey(Integer.valueOf(id)) && (aVar = this.j.get(Integer.valueOf(id))) != null && (constraintWidget instanceof h)) {
            constraintHelper.p(aVar, (h) constraintWidget, bVar, sparseArray);
        }
    }

    void k(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.j.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.j.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + androidx.constraintlayout.motion.widget.b.d(childAt));
            } else {
                if (this.i && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.j.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = this.j.get(Integer.valueOf(id));
                        if (aVar != null) {
                            if (childAt instanceof Barrier) {
                                aVar.f1079e.k0 = 1;
                                Barrier barrier = (Barrier) childAt;
                                barrier.setId(id);
                                barrier.setType(aVar.f1079e.i0);
                                barrier.setMargin(aVar.f1079e.j0);
                                barrier.setAllowsGoneWidget(aVar.f1079e.q0);
                                C0020b c0020b = aVar.f1079e;
                                int[] iArr = c0020b.l0;
                                if (iArr != null) {
                                    barrier.setReferencedIds(iArr);
                                } else {
                                    String str = c0020b.m0;
                                    if (str != null) {
                                        c0020b.l0 = r(barrier, str);
                                        barrier.setReferencedIds(aVar.f1079e.l0);
                                    }
                                }
                            }
                            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
                            bVar.c();
                            aVar.e(bVar);
                            if (z) {
                                ConstraintAttribute.j(childAt, aVar.g);
                            }
                            childAt.setLayoutParams(bVar);
                            d dVar = aVar.f1077c;
                            if (dVar.f1097c == 0) {
                                childAt.setVisibility(dVar.f1096b);
                            }
                            int i2 = Build.VERSION.SDK_INT;
                            if (i2 >= 17) {
                                childAt.setAlpha(aVar.f1077c.f1098d);
                                childAt.setRotation(aVar.f.f1102c);
                                childAt.setRotationX(aVar.f.f1103d);
                                childAt.setRotationY(aVar.f.f1104e);
                                childAt.setScaleX(aVar.f.f);
                                childAt.setScaleY(aVar.f.g);
                                e eVar = aVar.f;
                                if (eVar.j != -1) {
                                    if (((View) childAt.getParent()).findViewById(aVar.f.j) != null) {
                                        float top = (r6.getTop() + r6.getBottom()) / 2.0f;
                                        float left = (r6.getLeft() + r6.getRight()) / 2.0f;
                                        if (childAt.getRight() - childAt.getLeft() > 0 && childAt.getBottom() - childAt.getTop() > 0) {
                                            childAt.setPivotX(left - childAt.getLeft());
                                            childAt.setPivotY(top - childAt.getTop());
                                        }
                                    }
                                } else {
                                    if (!Float.isNaN(eVar.h)) {
                                        childAt.setPivotX(aVar.f.h);
                                    }
                                    if (!Float.isNaN(aVar.f.i)) {
                                        childAt.setPivotY(aVar.f.i);
                                    }
                                }
                                childAt.setTranslationX(aVar.f.k);
                                childAt.setTranslationY(aVar.f.l);
                                if (i2 >= 21) {
                                    childAt.setTranslationZ(aVar.f.m);
                                    e eVar2 = aVar.f;
                                    if (eVar2.n) {
                                        childAt.setElevation(eVar2.o);
                                    }
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
            a aVar2 = this.j.get(num);
            if (aVar2 != null) {
                if (aVar2.f1079e.k0 == 1) {
                    Barrier barrier2 = new Barrier(constraintLayout.getContext());
                    barrier2.setId(num.intValue());
                    C0020b c0020b2 = aVar2.f1079e;
                    int[] iArr2 = c0020b2.l0;
                    if (iArr2 != null) {
                        barrier2.setReferencedIds(iArr2);
                    } else {
                        String str2 = c0020b2.m0;
                        if (str2 != null) {
                            c0020b2.l0 = r(barrier2, str2);
                            barrier2.setReferencedIds(aVar2.f1079e.l0);
                        }
                    }
                    barrier2.setType(aVar2.f1079e.i0);
                    barrier2.setMargin(aVar2.f1079e.j0);
                    ConstraintLayout.b bVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                    barrier2.w();
                    aVar2.e(bVarGenerateDefaultLayoutParams);
                    constraintLayout.addView(barrier2, bVarGenerateDefaultLayoutParams);
                }
                if (aVar2.f1079e.f1086b) {
                    View guideline = new Guideline(constraintLayout.getContext());
                    guideline.setId(num.intValue());
                    ConstraintLayout.b bVarGenerateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                    aVar2.e(bVarGenerateDefaultLayoutParams2);
                    constraintLayout.addView(guideline, bVarGenerateDefaultLayoutParams2);
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

    public void l(int i, ConstraintLayout.b bVar) {
        a aVar;
        if (!this.j.containsKey(Integer.valueOf(i)) || (aVar = this.j.get(Integer.valueOf(i))) == null) {
            return;
        }
        aVar.e(bVar);
    }

    public void n(Context context, int i) {
        o((ConstraintLayout) LayoutInflater.from(context).inflate(i, (ViewGroup) null));
    }

    public void o(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.j.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.i && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.j.containsKey(Integer.valueOf(id))) {
                this.j.put(Integer.valueOf(id), new a());
            }
            a aVar = this.j.get(Integer.valueOf(id));
            if (aVar != null) {
                aVar.g = ConstraintAttribute.b(this.h, childAt);
                aVar.g(id, bVar);
                aVar.f1077c.f1096b = childAt.getVisibility();
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 17) {
                    aVar.f1077c.f1098d = childAt.getAlpha();
                    aVar.f.f1102c = childAt.getRotation();
                    aVar.f.f1103d = childAt.getRotationX();
                    aVar.f.f1104e = childAt.getRotationY();
                    aVar.f.f = childAt.getScaleX();
                    aVar.f.g = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        e eVar = aVar.f;
                        eVar.h = pivotX;
                        eVar.i = pivotY;
                    }
                    aVar.f.k = childAt.getTranslationX();
                    aVar.f.l = childAt.getTranslationY();
                    if (i2 >= 21) {
                        aVar.f.m = childAt.getTranslationZ();
                        e eVar2 = aVar.f;
                        if (eVar2.n) {
                            eVar2.o = childAt.getElevation();
                        }
                    }
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    aVar.f1079e.q0 = barrier.getAllowsGoneWidget();
                    aVar.f1079e.l0 = barrier.getReferencedIds();
                    aVar.f1079e.i0 = barrier.getType();
                    aVar.f1079e.j0 = barrier.getMargin();
                }
            }
        }
    }

    public void p(b bVar) {
        this.j.clear();
        for (Integer num : bVar.j.keySet()) {
            a aVar = bVar.j.get(num);
            if (aVar != null) {
                this.j.put(num, aVar.clone());
            }
        }
    }

    public void q(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.j.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraints.getChildAt(i);
            Constraints.a aVar = (Constraints.a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.i && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.j.containsKey(Integer.valueOf(id))) {
                this.j.put(Integer.valueOf(id), new a());
            }
            a aVar2 = this.j.get(Integer.valueOf(id));
            if (aVar2 != null) {
                if (childAt instanceof ConstraintHelper) {
                    aVar2.i((ConstraintHelper) childAt, id, aVar);
                }
                aVar2.h(id, aVar);
            }
        }
    }

    public a u(int i) {
        if (this.j.containsKey(Integer.valueOf(i))) {
            return this.j.get(Integer.valueOf(i));
        }
        return null;
    }

    public int v(int i) {
        return t(i).f1079e.f;
    }

    public int[] w() {
        Integer[] numArr = (Integer[]) this.j.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = numArr[i].intValue();
        }
        return iArr;
    }

    public a x(int i) {
        return t(i);
    }

    public int y(int i) {
        return t(i).f1077c.f1096b;
    }

    public int z(int i) {
        return t(i).f1077c.f1097c;
    }
}
