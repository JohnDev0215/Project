package a.f.b;

import a.b.k.l;
import a.f.b.e;
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
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f651d = {0, 4, 8};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static SparseIntArray f652e = new SparseIntArray();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static SparseIntArray f653f = new SparseIntArray();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<String, a.f.b.a> f654a = new HashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f655b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public HashMap<Integer, a> f656c = new HashMap<>();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f657a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final C0010d f658b = new C0010d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c f659c = new c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b f660d = new b();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final e f661e = new e();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public HashMap<String, a.f.b.a> f662f = new HashMap<>();
        public C0009a g;

        /* JADX INFO: renamed from: a.f.b.d$a$a, reason: collision with other inner class name */
        public static class C0009a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int[] f663a = new int[10];

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int[] f664b = new int[10];

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f665c = 0;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int[] f666d = new int[10];

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public float[] f667e = new float[10];

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public int f668f = 0;
            public int[] g = new int[5];
            public String[] h = new String[5];
            public int i = 0;
            public int[] j = new int[4];
            public boolean[] k = new boolean[4];
            public int l = 0;

            public void a(int i, float f2) {
                int i2 = this.f668f;
                int[] iArr = this.f666d;
                if (i2 >= iArr.length) {
                    this.f666d = Arrays.copyOf(iArr, iArr.length * 2);
                    float[] fArr = this.f667e;
                    this.f667e = Arrays.copyOf(fArr, fArr.length * 2);
                }
                int[] iArr2 = this.f666d;
                int i3 = this.f668f;
                iArr2[i3] = i;
                float[] fArr2 = this.f667e;
                this.f668f = i3 + 1;
                fArr2[i3] = f2;
            }

            public void b(int i, int i2) {
                int i3 = this.f665c;
                int[] iArr = this.f663a;
                if (i3 >= iArr.length) {
                    this.f663a = Arrays.copyOf(iArr, iArr.length * 2);
                    int[] iArr2 = this.f664b;
                    this.f664b = Arrays.copyOf(iArr2, iArr2.length * 2);
                }
                int[] iArr3 = this.f663a;
                int i4 = this.f665c;
                iArr3[i4] = i;
                int[] iArr4 = this.f664b;
                this.f665c = i4 + 1;
                iArr4[i4] = i2;
            }

            public void c(int i, String str) {
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

            public void d(int i, boolean z) {
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
        }

        public void a(ConstraintLayout.a aVar) {
            b bVar = this.f660d;
            aVar.f1496e = bVar.i;
            aVar.f1497f = bVar.j;
            aVar.g = bVar.k;
            aVar.h = bVar.l;
            aVar.i = bVar.m;
            aVar.j = bVar.n;
            aVar.k = bVar.o;
            aVar.l = bVar.p;
            aVar.m = bVar.q;
            aVar.n = bVar.r;
            aVar.o = bVar.s;
            aVar.s = bVar.t;
            aVar.t = bVar.u;
            aVar.u = bVar.v;
            aVar.v = bVar.w;
            ((ViewGroup.MarginLayoutParams) aVar).leftMargin = bVar.G;
            ((ViewGroup.MarginLayoutParams) aVar).rightMargin = bVar.H;
            ((ViewGroup.MarginLayoutParams) aVar).topMargin = bVar.I;
            ((ViewGroup.MarginLayoutParams) aVar).bottomMargin = bVar.J;
            aVar.A = bVar.S;
            aVar.B = bVar.R;
            aVar.x = bVar.O;
            aVar.z = bVar.Q;
            aVar.E = bVar.x;
            aVar.F = bVar.y;
            aVar.p = bVar.A;
            aVar.q = bVar.B;
            aVar.r = bVar.C;
            aVar.G = bVar.z;
            aVar.T = bVar.D;
            aVar.U = bVar.E;
            aVar.I = bVar.U;
            aVar.H = bVar.V;
            aVar.K = bVar.X;
            aVar.J = bVar.W;
            aVar.W = bVar.m0;
            aVar.X = bVar.n0;
            aVar.L = bVar.Y;
            aVar.M = bVar.Z;
            aVar.P = bVar.a0;
            aVar.Q = bVar.b0;
            aVar.N = bVar.c0;
            aVar.O = bVar.d0;
            aVar.R = bVar.e0;
            aVar.S = bVar.f0;
            aVar.V = bVar.F;
            aVar.f1494c = bVar.g;
            aVar.f1492a = bVar.f673e;
            aVar.f1493b = bVar.f674f;
            ((ViewGroup.MarginLayoutParams) aVar).width = bVar.f671c;
            ((ViewGroup.MarginLayoutParams) aVar).height = bVar.f672d;
            String str = bVar.l0;
            if (str != null) {
                aVar.Y = str;
            }
            b bVar2 = this.f660d;
            aVar.Z = bVar2.p0;
            aVar.setMarginStart(bVar2.L);
            aVar.setMarginEnd(this.f660d.K);
            aVar.a();
        }

        public final void b(int i, ConstraintLayout.a aVar) {
            this.f657a = i;
            b bVar = this.f660d;
            bVar.i = aVar.f1496e;
            bVar.j = aVar.f1497f;
            bVar.k = aVar.g;
            bVar.l = aVar.h;
            bVar.m = aVar.i;
            bVar.n = aVar.j;
            bVar.o = aVar.k;
            bVar.p = aVar.l;
            bVar.q = aVar.m;
            bVar.r = aVar.n;
            bVar.s = aVar.o;
            bVar.t = aVar.s;
            bVar.u = aVar.t;
            bVar.v = aVar.u;
            bVar.w = aVar.v;
            bVar.x = aVar.E;
            bVar.y = aVar.F;
            bVar.z = aVar.G;
            bVar.A = aVar.p;
            bVar.B = aVar.q;
            bVar.C = aVar.r;
            bVar.D = aVar.T;
            bVar.E = aVar.U;
            bVar.F = aVar.V;
            bVar.g = aVar.f1494c;
            bVar.f673e = aVar.f1492a;
            bVar.f674f = aVar.f1493b;
            bVar.f671c = ((ViewGroup.MarginLayoutParams) aVar).width;
            bVar.f672d = ((ViewGroup.MarginLayoutParams) aVar).height;
            bVar.G = ((ViewGroup.MarginLayoutParams) aVar).leftMargin;
            bVar.H = ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
            bVar.I = ((ViewGroup.MarginLayoutParams) aVar).topMargin;
            bVar.J = ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
            bVar.M = aVar.D;
            bVar.U = aVar.I;
            bVar.V = aVar.H;
            bVar.X = aVar.K;
            bVar.W = aVar.J;
            bVar.m0 = aVar.W;
            bVar.n0 = aVar.X;
            bVar.Y = aVar.L;
            bVar.Z = aVar.M;
            bVar.a0 = aVar.P;
            bVar.b0 = aVar.Q;
            bVar.c0 = aVar.N;
            bVar.d0 = aVar.O;
            bVar.e0 = aVar.R;
            bVar.f0 = aVar.S;
            bVar.l0 = aVar.Y;
            bVar.O = aVar.x;
            bVar.Q = aVar.z;
            bVar.N = aVar.w;
            bVar.P = aVar.y;
            bVar.S = aVar.A;
            bVar.R = aVar.B;
            bVar.T = aVar.C;
            bVar.p0 = aVar.Z;
            bVar.K = aVar.getMarginEnd();
            this.f660d.L = aVar.getMarginStart();
        }

        public final void c(int i, e.a aVar) {
            b(i, aVar);
            this.f658b.f684d = aVar.s0;
            e eVar = this.f661e;
            eVar.f687b = aVar.v0;
            eVar.f688c = aVar.w0;
            eVar.f689d = aVar.x0;
            eVar.f690e = aVar.y0;
            eVar.f691f = aVar.z0;
            eVar.g = aVar.A0;
            eVar.h = aVar.B0;
            eVar.j = aVar.C0;
            eVar.k = aVar.D0;
            eVar.l = aVar.E0;
            eVar.n = aVar.u0;
            eVar.m = aVar.t0;
        }

        public Object clone() {
            a aVar = new a();
            b bVar = aVar.f660d;
            b bVar2 = this.f660d;
            if (bVar == null) {
                throw null;
            }
            bVar.f669a = bVar2.f669a;
            bVar.f671c = bVar2.f671c;
            bVar.f670b = bVar2.f670b;
            bVar.f672d = bVar2.f672d;
            bVar.f673e = bVar2.f673e;
            bVar.f674f = bVar2.f674f;
            bVar.g = bVar2.g;
            bVar.h = bVar2.h;
            bVar.i = bVar2.i;
            bVar.j = bVar2.j;
            bVar.k = bVar2.k;
            bVar.l = bVar2.l;
            bVar.m = bVar2.m;
            bVar.n = bVar2.n;
            bVar.o = bVar2.o;
            bVar.p = bVar2.p;
            bVar.q = bVar2.q;
            bVar.r = bVar2.r;
            bVar.s = bVar2.s;
            bVar.t = bVar2.t;
            bVar.u = bVar2.u;
            bVar.v = bVar2.v;
            bVar.w = bVar2.w;
            bVar.x = bVar2.x;
            bVar.y = bVar2.y;
            bVar.z = bVar2.z;
            bVar.A = bVar2.A;
            bVar.B = bVar2.B;
            bVar.C = bVar2.C;
            bVar.D = bVar2.D;
            bVar.E = bVar2.E;
            bVar.F = bVar2.F;
            bVar.G = bVar2.G;
            bVar.H = bVar2.H;
            bVar.I = bVar2.I;
            bVar.J = bVar2.J;
            bVar.K = bVar2.K;
            bVar.L = bVar2.L;
            bVar.M = bVar2.M;
            bVar.N = bVar2.N;
            bVar.O = bVar2.O;
            bVar.P = bVar2.P;
            bVar.Q = bVar2.Q;
            bVar.R = bVar2.R;
            bVar.S = bVar2.S;
            bVar.T = bVar2.T;
            bVar.U = bVar2.U;
            bVar.V = bVar2.V;
            bVar.W = bVar2.W;
            bVar.X = bVar2.X;
            bVar.Y = bVar2.Y;
            bVar.Z = bVar2.Z;
            bVar.a0 = bVar2.a0;
            bVar.b0 = bVar2.b0;
            bVar.c0 = bVar2.c0;
            bVar.d0 = bVar2.d0;
            bVar.e0 = bVar2.e0;
            bVar.f0 = bVar2.f0;
            bVar.g0 = bVar2.g0;
            bVar.h0 = bVar2.h0;
            bVar.i0 = bVar2.i0;
            bVar.l0 = bVar2.l0;
            int[] iArr = bVar2.j0;
            if (iArr == null || bVar2.k0 != null) {
                bVar.j0 = null;
            } else {
                bVar.j0 = Arrays.copyOf(iArr, iArr.length);
            }
            bVar.k0 = bVar2.k0;
            bVar.m0 = bVar2.m0;
            bVar.n0 = bVar2.n0;
            bVar.o0 = bVar2.o0;
            bVar.p0 = bVar2.p0;
            c cVar = aVar.f659c;
            c cVar2 = this.f659c;
            if (cVar == null) {
                throw null;
            }
            cVar.f675a = cVar2.f675a;
            cVar.f676b = cVar2.f676b;
            cVar.f678d = cVar2.f678d;
            cVar.f679e = cVar2.f679e;
            cVar.f680f = cVar2.f680f;
            cVar.i = cVar2.i;
            cVar.g = cVar2.g;
            cVar.h = cVar2.h;
            C0010d c0010d = aVar.f658b;
            C0010d c0010d2 = this.f658b;
            if (c0010d == null) {
                throw null;
            }
            c0010d.f681a = c0010d2.f681a;
            c0010d.f682b = c0010d2.f682b;
            c0010d.f684d = c0010d2.f684d;
            c0010d.f685e = c0010d2.f685e;
            c0010d.f683c = c0010d2.f683c;
            e eVar = aVar.f661e;
            e eVar2 = this.f661e;
            if (eVar == null) {
                throw null;
            }
            eVar.f686a = eVar2.f686a;
            eVar.f687b = eVar2.f687b;
            eVar.f688c = eVar2.f688c;
            eVar.f689d = eVar2.f689d;
            eVar.f690e = eVar2.f690e;
            eVar.f691f = eVar2.f691f;
            eVar.g = eVar2.g;
            eVar.h = eVar2.h;
            eVar.i = eVar2.i;
            eVar.j = eVar2.j;
            eVar.k = eVar2.k;
            eVar.l = eVar2.l;
            eVar.m = eVar2.m;
            eVar.n = eVar2.n;
            aVar.f657a = this.f657a;
            aVar.g = this.g;
            return aVar;
        }
    }

    public static class b {
        public static SparseIntArray q0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f671c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f672d;
        public int[] j0;
        public String k0;
        public String l0;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f669a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f670b = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f673e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f674f = -1;
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
        public int J = 0;
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
            sparseIntArray.append(i.Layout_layout_constraintLeft_toLeftOf, 24);
            q0.append(i.Layout_layout_constraintLeft_toRightOf, 25);
            q0.append(i.Layout_layout_constraintRight_toLeftOf, 28);
            q0.append(i.Layout_layout_constraintRight_toRightOf, 29);
            q0.append(i.Layout_layout_constraintTop_toTopOf, 35);
            q0.append(i.Layout_layout_constraintTop_toBottomOf, 34);
            q0.append(i.Layout_layout_constraintBottom_toTopOf, 4);
            q0.append(i.Layout_layout_constraintBottom_toBottomOf, 3);
            q0.append(i.Layout_layout_constraintBaseline_toBaselineOf, 1);
            q0.append(i.Layout_layout_editor_absoluteX, 6);
            q0.append(i.Layout_layout_editor_absoluteY, 7);
            q0.append(i.Layout_layout_constraintGuide_begin, 17);
            q0.append(i.Layout_layout_constraintGuide_end, 18);
            q0.append(i.Layout_layout_constraintGuide_percent, 19);
            q0.append(i.Layout_guidelineUseRtl, 90);
            q0.append(i.Layout_android_orientation, 26);
            q0.append(i.Layout_layout_constraintStart_toEndOf, 31);
            q0.append(i.Layout_layout_constraintStart_toStartOf, 32);
            q0.append(i.Layout_layout_constraintEnd_toStartOf, 10);
            q0.append(i.Layout_layout_constraintEnd_toEndOf, 9);
            q0.append(i.Layout_layout_goneMarginLeft, 13);
            q0.append(i.Layout_layout_goneMarginTop, 16);
            q0.append(i.Layout_layout_goneMarginRight, 14);
            q0.append(i.Layout_layout_goneMarginBottom, 11);
            q0.append(i.Layout_layout_goneMarginStart, 15);
            q0.append(i.Layout_layout_goneMarginEnd, 12);
            q0.append(i.Layout_layout_constraintVertical_weight, 38);
            q0.append(i.Layout_layout_constraintHorizontal_weight, 37);
            q0.append(i.Layout_layout_constraintHorizontal_chainStyle, 39);
            q0.append(i.Layout_layout_constraintVertical_chainStyle, 40);
            q0.append(i.Layout_layout_constraintHorizontal_bias, 20);
            q0.append(i.Layout_layout_constraintVertical_bias, 36);
            q0.append(i.Layout_layout_constraintDimensionRatio, 5);
            q0.append(i.Layout_layout_constraintLeft_creator, 91);
            q0.append(i.Layout_layout_constraintTop_creator, 91);
            q0.append(i.Layout_layout_constraintRight_creator, 91);
            q0.append(i.Layout_layout_constraintBottom_creator, 91);
            q0.append(i.Layout_layout_constraintBaseline_creator, 91);
            q0.append(i.Layout_android_layout_marginLeft, 23);
            q0.append(i.Layout_android_layout_marginRight, 27);
            q0.append(i.Layout_android_layout_marginStart, 30);
            q0.append(i.Layout_android_layout_marginEnd, 8);
            q0.append(i.Layout_android_layout_marginTop, 33);
            q0.append(i.Layout_android_layout_marginBottom, 2);
            q0.append(i.Layout_android_layout_width, 22);
            q0.append(i.Layout_android_layout_height, 21);
            q0.append(i.Layout_layout_constraintWidth, 41);
            q0.append(i.Layout_layout_constraintHeight, 42);
            q0.append(i.Layout_layout_constrainedWidth, 41);
            q0.append(i.Layout_layout_constrainedHeight, 42);
            q0.append(i.Layout_layout_wrapBehaviorInParent, 76);
            q0.append(i.Layout_layout_constraintCircle, 61);
            q0.append(i.Layout_layout_constraintCircleRadius, 62);
            q0.append(i.Layout_layout_constraintCircleAngle, 63);
            q0.append(i.Layout_layout_constraintWidth_percent, 69);
            q0.append(i.Layout_layout_constraintHeight_percent, 70);
            q0.append(i.Layout_chainUseRtl, 71);
            q0.append(i.Layout_barrierDirection, 72);
            q0.append(i.Layout_barrierMargin, 73);
            q0.append(i.Layout_constraint_referenced_ids, 74);
            q0.append(i.Layout_barrierAllowsGoneWidgets, 75);
        }

        public void a(Context context, AttributeSet attributeSet) {
            StringBuilder sb;
            String str;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.Layout);
            this.f670b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                int i2 = q0.get(index);
                switch (i2) {
                    case 1:
                        this.q = d.a(typedArrayObtainStyledAttributes, index, this.q);
                        break;
                    case 2:
                        this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                        break;
                    case 3:
                        this.p = d.a(typedArrayObtainStyledAttributes, index, this.p);
                        break;
                    case 4:
                        this.o = d.a(typedArrayObtainStyledAttributes, index, this.o);
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
                        this.w = d.a(typedArrayObtainStyledAttributes, index, this.w);
                        break;
                    case 10:
                        this.v = d.a(typedArrayObtainStyledAttributes, index, this.v);
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
                        this.f673e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f673e);
                        break;
                    case 18:
                        this.f674f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f674f);
                        break;
                    case 19:
                        this.g = typedArrayObtainStyledAttributes.getFloat(index, this.g);
                        break;
                    case 20:
                        this.x = typedArrayObtainStyledAttributes.getFloat(index, this.x);
                        break;
                    case 21:
                        this.f672d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f672d);
                        break;
                    case 22:
                        this.f671c = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f671c);
                        break;
                    case 23:
                        this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.G);
                        break;
                    case 24:
                        this.i = d.a(typedArrayObtainStyledAttributes, index, this.i);
                        break;
                    case 25:
                        this.j = d.a(typedArrayObtainStyledAttributes, index, this.j);
                        break;
                    case 26:
                        this.F = typedArrayObtainStyledAttributes.getInt(index, this.F);
                        break;
                    case 27:
                        this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.H);
                        break;
                    case 28:
                        this.k = d.a(typedArrayObtainStyledAttributes, index, this.k);
                        break;
                    case 29:
                        this.l = d.a(typedArrayObtainStyledAttributes, index, this.l);
                        break;
                    case 30:
                        this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.L);
                        break;
                    case 31:
                        this.t = d.a(typedArrayObtainStyledAttributes, index, this.t);
                        break;
                    case 32:
                        this.u = d.a(typedArrayObtainStyledAttributes, index, this.u);
                        break;
                    case 33:
                        this.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.I);
                        break;
                    case 34:
                        this.n = d.a(typedArrayObtainStyledAttributes, index, this.n);
                        break;
                    case 35:
                        this.m = d.a(typedArrayObtainStyledAttributes, index, this.m);
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
                        d.g(this, typedArrayObtainStyledAttributes, index, 0);
                        break;
                    case 42:
                        d.g(this, typedArrayObtainStyledAttributes, index, 1);
                        break;
                    default:
                        switch (i2) {
                            case 61:
                                this.A = d.a(typedArrayObtainStyledAttributes, index, this.A);
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
                                        continue;
                                    case 70:
                                        this.f0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                        continue;
                                    case 71:
                                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                        continue;
                                    case 72:
                                        this.g0 = typedArrayObtainStyledAttributes.getInt(index, this.g0);
                                        continue;
                                    case 73:
                                        this.h0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.h0);
                                        continue;
                                    case 74:
                                        this.k0 = typedArrayObtainStyledAttributes.getString(index);
                                        continue;
                                    case 75:
                                        this.o0 = typedArrayObtainStyledAttributes.getBoolean(index, this.o0);
                                        continue;
                                    case 76:
                                        this.p0 = typedArrayObtainStyledAttributes.getInt(index, this.p0);
                                        continue;
                                    case 77:
                                        this.r = d.a(typedArrayObtainStyledAttributes, index, this.r);
                                        continue;
                                    case 78:
                                        this.s = d.a(typedArrayObtainStyledAttributes, index, this.s);
                                        continue;
                                    case 79:
                                        this.T = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.T);
                                        continue;
                                    case 80:
                                        this.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.M);
                                        continue;
                                    case 81:
                                        this.Y = typedArrayObtainStyledAttributes.getInt(index, this.Y);
                                        continue;
                                    case 82:
                                        this.Z = typedArrayObtainStyledAttributes.getInt(index, this.Z);
                                        continue;
                                    case 83:
                                        this.b0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.b0);
                                        continue;
                                    case 84:
                                        this.a0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.a0);
                                        continue;
                                    case 85:
                                        this.d0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.d0);
                                        continue;
                                    case 86:
                                        this.c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.c0);
                                        continue;
                                    case 87:
                                        this.m0 = typedArrayObtainStyledAttributes.getBoolean(index, this.m0);
                                        continue;
                                    case 88:
                                        this.n0 = typedArrayObtainStyledAttributes.getBoolean(index, this.n0);
                                        continue;
                                    case 89:
                                        this.l0 = typedArrayObtainStyledAttributes.getString(index);
                                        continue;
                                    case 90:
                                        this.h = typedArrayObtainStyledAttributes.getBoolean(index, this.h);
                                        continue;
                                    case 91:
                                        sb = new StringBuilder();
                                        str = "unused attribute 0x";
                                        break;
                                    default:
                                        sb = new StringBuilder();
                                        str = "Unknown attribute 0x";
                                        break;
                                }
                                sb.append(str);
                                sb.append(Integer.toHexString(index));
                                sb.append("   ");
                                sb.append(q0.get(index));
                                Log.w("ConstraintSet", sb.toString());
                                break;
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static class c {
        public static SparseIntArray o;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f675a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f676b = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f677c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f678d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f679e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f680f = 0;
        public float g = Float.NaN;
        public int h = -1;
        public float i = Float.NaN;
        public float j = Float.NaN;
        public int k = -1;
        public String l = null;
        public int m = -3;
        public int n = -1;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            o = sparseIntArray;
            sparseIntArray.append(i.Motion_motionPathRotate, 1);
            o.append(i.Motion_pathMotionArc, 2);
            o.append(i.Motion_transitionEasing, 3);
            o.append(i.Motion_drawPath, 4);
            o.append(i.Motion_animateRelativeTo, 5);
            o.append(i.Motion_animateCircleAngleTo, 6);
            o.append(i.Motion_motionStagger, 7);
            o.append(i.Motion_quantizeMotionSteps, 8);
            o.append(i.Motion_quantizeMotionPhase, 9);
            o.append(i.Motion_quantizeMotionInterpolator, 10);
        }

        public void a(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.Motion);
            this.f675a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (o.get(index)) {
                    case 1:
                        this.i = typedArrayObtainStyledAttributes.getFloat(index, this.i);
                        break;
                    case 2:
                        this.f679e = typedArrayObtainStyledAttributes.getInt(index, this.f679e);
                        break;
                    case 3:
                        this.f678d = typedArrayObtainStyledAttributes.peekValue(index).type == 3 ? typedArrayObtainStyledAttributes.getString(index) : a.f.a.h.a.a.f535c[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        break;
                    case 4:
                        this.f680f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.f676b = d.a(typedArrayObtainStyledAttributes, index, this.f676b);
                        break;
                    case 6:
                        this.f677c = typedArrayObtainStyledAttributes.getInteger(index, this.f677c);
                        break;
                    case 7:
                        this.g = typedArrayObtainStyledAttributes.getFloat(index, this.g);
                        break;
                    case 8:
                        this.k = typedArrayObtainStyledAttributes.getInteger(index, this.k);
                        break;
                    case 9:
                        this.j = typedArrayObtainStyledAttributes.getFloat(index, this.j);
                        break;
                    case 10:
                        int i2 = typedArrayObtainStyledAttributes.peekValue(index).type;
                        if (i2 == 1) {
                            int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                            this.n = resourceId;
                            if (resourceId != -1) {
                                this.m = -2;
                            }
                        } else if (i2 == 3) {
                            String string = typedArrayObtainStyledAttributes.getString(index);
                            this.l = string;
                            if (string.indexOf("/") > 0) {
                                this.n = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                                this.m = -2;
                            } else {
                                this.m = -1;
                            }
                        } else {
                            this.m = typedArrayObtainStyledAttributes.getInteger(index, this.n);
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: renamed from: a.f.b.d$d, reason: collision with other inner class name */
    public static class C0010d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f681a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f682b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f683c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f684d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f685e = Float.NaN;

        public void a(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.PropertySet);
            this.f681a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == i.PropertySet_android_alpha) {
                    this.f684d = typedArrayObtainStyledAttributes.getFloat(index, this.f684d);
                } else if (index == i.PropertySet_android_visibility) {
                    int i2 = typedArrayObtainStyledAttributes.getInt(index, this.f682b);
                    this.f682b = i2;
                    this.f682b = d.f651d[i2];
                } else if (index == i.PropertySet_visibilityMode) {
                    this.f683c = typedArrayObtainStyledAttributes.getInt(index, this.f683c);
                } else if (index == i.PropertySet_motionProgress) {
                    this.f685e = typedArrayObtainStyledAttributes.getFloat(index, this.f685e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static class e {
        public static SparseIntArray o;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f686a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f687b = 0.0f;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f688c = 0.0f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f689d = 0.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f690e = 1.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f691f = 1.0f;
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
            sparseIntArray.append(i.Transform_android_rotation, 1);
            o.append(i.Transform_android_rotationX, 2);
            o.append(i.Transform_android_rotationY, 3);
            o.append(i.Transform_android_scaleX, 4);
            o.append(i.Transform_android_scaleY, 5);
            o.append(i.Transform_android_transformPivotX, 6);
            o.append(i.Transform_android_transformPivotY, 7);
            o.append(i.Transform_android_translationX, 8);
            o.append(i.Transform_android_translationY, 9);
            o.append(i.Transform_android_translationZ, 10);
            o.append(i.Transform_android_elevation, 11);
            o.append(i.Transform_transformPivotTarget, 12);
        }

        public void a(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.Transform);
            this.f686a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (o.get(index)) {
                    case 1:
                        this.f687b = typedArrayObtainStyledAttributes.getFloat(index, this.f687b);
                        break;
                    case 2:
                        this.f688c = typedArrayObtainStyledAttributes.getFloat(index, this.f688c);
                        break;
                    case 3:
                        this.f689d = typedArrayObtainStyledAttributes.getFloat(index, this.f689d);
                        break;
                    case 4:
                        this.f690e = typedArrayObtainStyledAttributes.getFloat(index, this.f690e);
                        break;
                    case 5:
                        this.f691f = typedArrayObtainStyledAttributes.getFloat(index, this.f691f);
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
                        this.i = d.a(typedArrayObtainStyledAttributes, index, this.i);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    static {
        f652e.append(i.Constraint_layout_constraintLeft_toLeftOf, 25);
        f652e.append(i.Constraint_layout_constraintLeft_toRightOf, 26);
        f652e.append(i.Constraint_layout_constraintRight_toLeftOf, 29);
        f652e.append(i.Constraint_layout_constraintRight_toRightOf, 30);
        f652e.append(i.Constraint_layout_constraintTop_toTopOf, 36);
        f652e.append(i.Constraint_layout_constraintTop_toBottomOf, 35);
        f652e.append(i.Constraint_layout_constraintBottom_toTopOf, 4);
        f652e.append(i.Constraint_layout_constraintBottom_toBottomOf, 3);
        f652e.append(i.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        f652e.append(i.Constraint_layout_constraintBaseline_toTopOf, 91);
        f652e.append(i.Constraint_layout_constraintBaseline_toBottomOf, 92);
        f652e.append(i.Constraint_layout_editor_absoluteX, 6);
        f652e.append(i.Constraint_layout_editor_absoluteY, 7);
        f652e.append(i.Constraint_layout_constraintGuide_begin, 17);
        f652e.append(i.Constraint_layout_constraintGuide_end, 18);
        f652e.append(i.Constraint_layout_constraintGuide_percent, 19);
        f652e.append(i.Constraint_guidelineUseRtl, 99);
        f652e.append(i.Constraint_android_orientation, 27);
        f652e.append(i.Constraint_layout_constraintStart_toEndOf, 32);
        f652e.append(i.Constraint_layout_constraintStart_toStartOf, 33);
        f652e.append(i.Constraint_layout_constraintEnd_toStartOf, 10);
        f652e.append(i.Constraint_layout_constraintEnd_toEndOf, 9);
        f652e.append(i.Constraint_layout_goneMarginLeft, 13);
        f652e.append(i.Constraint_layout_goneMarginTop, 16);
        f652e.append(i.Constraint_layout_goneMarginRight, 14);
        f652e.append(i.Constraint_layout_goneMarginBottom, 11);
        f652e.append(i.Constraint_layout_goneMarginStart, 15);
        f652e.append(i.Constraint_layout_goneMarginEnd, 12);
        f652e.append(i.Constraint_layout_constraintVertical_weight, 40);
        f652e.append(i.Constraint_layout_constraintHorizontal_weight, 39);
        f652e.append(i.Constraint_layout_constraintHorizontal_chainStyle, 41);
        f652e.append(i.Constraint_layout_constraintVertical_chainStyle, 42);
        f652e.append(i.Constraint_layout_constraintHorizontal_bias, 20);
        f652e.append(i.Constraint_layout_constraintVertical_bias, 37);
        f652e.append(i.Constraint_layout_constraintDimensionRatio, 5);
        f652e.append(i.Constraint_layout_constraintLeft_creator, 87);
        f652e.append(i.Constraint_layout_constraintTop_creator, 87);
        f652e.append(i.Constraint_layout_constraintRight_creator, 87);
        f652e.append(i.Constraint_layout_constraintBottom_creator, 87);
        f652e.append(i.Constraint_layout_constraintBaseline_creator, 87);
        f652e.append(i.Constraint_android_layout_marginLeft, 24);
        f652e.append(i.Constraint_android_layout_marginRight, 28);
        f652e.append(i.Constraint_android_layout_marginStart, 31);
        f652e.append(i.Constraint_android_layout_marginEnd, 8);
        f652e.append(i.Constraint_android_layout_marginTop, 34);
        f652e.append(i.Constraint_android_layout_marginBottom, 2);
        f652e.append(i.Constraint_android_layout_width, 23);
        f652e.append(i.Constraint_android_layout_height, 21);
        f652e.append(i.Constraint_layout_constraintWidth, 95);
        f652e.append(i.Constraint_layout_constraintHeight, 96);
        f652e.append(i.Constraint_android_visibility, 22);
        f652e.append(i.Constraint_android_alpha, 43);
        f652e.append(i.Constraint_android_elevation, 44);
        f652e.append(i.Constraint_android_rotationX, 45);
        f652e.append(i.Constraint_android_rotationY, 46);
        f652e.append(i.Constraint_android_rotation, 60);
        f652e.append(i.Constraint_android_scaleX, 47);
        f652e.append(i.Constraint_android_scaleY, 48);
        f652e.append(i.Constraint_android_transformPivotX, 49);
        f652e.append(i.Constraint_android_transformPivotY, 50);
        f652e.append(i.Constraint_android_translationX, 51);
        f652e.append(i.Constraint_android_translationY, 52);
        f652e.append(i.Constraint_android_translationZ, 53);
        f652e.append(i.Constraint_layout_constraintWidth_default, 54);
        f652e.append(i.Constraint_layout_constraintHeight_default, 55);
        f652e.append(i.Constraint_layout_constraintWidth_max, 56);
        f652e.append(i.Constraint_layout_constraintHeight_max, 57);
        f652e.append(i.Constraint_layout_constraintWidth_min, 58);
        f652e.append(i.Constraint_layout_constraintHeight_min, 59);
        f652e.append(i.Constraint_layout_constraintCircle, 61);
        f652e.append(i.Constraint_layout_constraintCircleRadius, 62);
        f652e.append(i.Constraint_layout_constraintCircleAngle, 63);
        f652e.append(i.Constraint_animateRelativeTo, 64);
        f652e.append(i.Constraint_transitionEasing, 65);
        f652e.append(i.Constraint_drawPath, 66);
        f652e.append(i.Constraint_transitionPathRotate, 67);
        f652e.append(i.Constraint_motionStagger, 79);
        f652e.append(i.Constraint_android_id, 38);
        f652e.append(i.Constraint_motionProgress, 68);
        f652e.append(i.Constraint_layout_constraintWidth_percent, 69);
        f652e.append(i.Constraint_layout_constraintHeight_percent, 70);
        f652e.append(i.Constraint_layout_wrapBehaviorInParent, 97);
        f652e.append(i.Constraint_chainUseRtl, 71);
        f652e.append(i.Constraint_barrierDirection, 72);
        f652e.append(i.Constraint_barrierMargin, 73);
        f652e.append(i.Constraint_constraint_referenced_ids, 74);
        f652e.append(i.Constraint_barrierAllowsGoneWidgets, 75);
        f652e.append(i.Constraint_pathMotionArc, 76);
        f652e.append(i.Constraint_layout_constraintTag, 77);
        f652e.append(i.Constraint_visibilityMode, 78);
        f652e.append(i.Constraint_layout_constrainedWidth, 80);
        f652e.append(i.Constraint_layout_constrainedHeight, 81);
        f652e.append(i.Constraint_polarRelativeTo, 82);
        f652e.append(i.Constraint_transformPivotTarget, 83);
        f652e.append(i.Constraint_quantizeMotionSteps, 84);
        f652e.append(i.Constraint_quantizeMotionPhase, 85);
        f652e.append(i.Constraint_quantizeMotionInterpolator, 86);
        f653f.append(i.ConstraintOverride_layout_editor_absoluteY, 6);
        f653f.append(i.ConstraintOverride_layout_editor_absoluteY, 7);
        f653f.append(i.ConstraintOverride_android_orientation, 27);
        f653f.append(i.ConstraintOverride_layout_goneMarginLeft, 13);
        f653f.append(i.ConstraintOverride_layout_goneMarginTop, 16);
        f653f.append(i.ConstraintOverride_layout_goneMarginRight, 14);
        f653f.append(i.ConstraintOverride_layout_goneMarginBottom, 11);
        f653f.append(i.ConstraintOverride_layout_goneMarginStart, 15);
        f653f.append(i.ConstraintOverride_layout_goneMarginEnd, 12);
        f653f.append(i.ConstraintOverride_layout_constraintVertical_weight, 40);
        f653f.append(i.ConstraintOverride_layout_constraintHorizontal_weight, 39);
        f653f.append(i.ConstraintOverride_layout_constraintHorizontal_chainStyle, 41);
        f653f.append(i.ConstraintOverride_layout_constraintVertical_chainStyle, 42);
        f653f.append(i.ConstraintOverride_layout_constraintHorizontal_bias, 20);
        f653f.append(i.ConstraintOverride_layout_constraintVertical_bias, 37);
        f653f.append(i.ConstraintOverride_layout_constraintDimensionRatio, 5);
        f653f.append(i.ConstraintOverride_layout_constraintLeft_creator, 87);
        f653f.append(i.ConstraintOverride_layout_constraintTop_creator, 87);
        f653f.append(i.ConstraintOverride_layout_constraintRight_creator, 87);
        f653f.append(i.ConstraintOverride_layout_constraintBottom_creator, 87);
        f653f.append(i.ConstraintOverride_layout_constraintBaseline_creator, 87);
        f653f.append(i.ConstraintOverride_android_layout_marginLeft, 24);
        f653f.append(i.ConstraintOverride_android_layout_marginRight, 28);
        f653f.append(i.ConstraintOverride_android_layout_marginStart, 31);
        f653f.append(i.ConstraintOverride_android_layout_marginEnd, 8);
        f653f.append(i.ConstraintOverride_android_layout_marginTop, 34);
        f653f.append(i.ConstraintOverride_android_layout_marginBottom, 2);
        f653f.append(i.ConstraintOverride_android_layout_width, 23);
        f653f.append(i.ConstraintOverride_android_layout_height, 21);
        f653f.append(i.ConstraintOverride_layout_constraintWidth, 95);
        f653f.append(i.ConstraintOverride_layout_constraintHeight, 96);
        f653f.append(i.ConstraintOverride_android_visibility, 22);
        f653f.append(i.ConstraintOverride_android_alpha, 43);
        f653f.append(i.ConstraintOverride_android_elevation, 44);
        f653f.append(i.ConstraintOverride_android_rotationX, 45);
        f653f.append(i.ConstraintOverride_android_rotationY, 46);
        f653f.append(i.ConstraintOverride_android_rotation, 60);
        f653f.append(i.ConstraintOverride_android_scaleX, 47);
        f653f.append(i.ConstraintOverride_android_scaleY, 48);
        f653f.append(i.ConstraintOverride_android_transformPivotX, 49);
        f653f.append(i.ConstraintOverride_android_transformPivotY, 50);
        f653f.append(i.ConstraintOverride_android_translationX, 51);
        f653f.append(i.ConstraintOverride_android_translationY, 52);
        f653f.append(i.ConstraintOverride_android_translationZ, 53);
        f653f.append(i.ConstraintOverride_layout_constraintWidth_default, 54);
        f653f.append(i.ConstraintOverride_layout_constraintHeight_default, 55);
        f653f.append(i.ConstraintOverride_layout_constraintWidth_max, 56);
        f653f.append(i.ConstraintOverride_layout_constraintHeight_max, 57);
        f653f.append(i.ConstraintOverride_layout_constraintWidth_min, 58);
        f653f.append(i.ConstraintOverride_layout_constraintHeight_min, 59);
        f653f.append(i.ConstraintOverride_layout_constraintCircleRadius, 62);
        f653f.append(i.ConstraintOverride_layout_constraintCircleAngle, 63);
        f653f.append(i.ConstraintOverride_animateRelativeTo, 64);
        f653f.append(i.ConstraintOverride_transitionEasing, 65);
        f653f.append(i.ConstraintOverride_drawPath, 66);
        f653f.append(i.ConstraintOverride_transitionPathRotate, 67);
        f653f.append(i.ConstraintOverride_motionStagger, 79);
        f653f.append(i.ConstraintOverride_android_id, 38);
        f653f.append(i.ConstraintOverride_motionTarget, 98);
        f653f.append(i.ConstraintOverride_motionProgress, 68);
        f653f.append(i.ConstraintOverride_layout_constraintWidth_percent, 69);
        f653f.append(i.ConstraintOverride_layout_constraintHeight_percent, 70);
        f653f.append(i.ConstraintOverride_chainUseRtl, 71);
        f653f.append(i.ConstraintOverride_barrierDirection, 72);
        f653f.append(i.ConstraintOverride_barrierMargin, 73);
        f653f.append(i.ConstraintOverride_constraint_referenced_ids, 74);
        f653f.append(i.ConstraintOverride_barrierAllowsGoneWidgets, 75);
        f653f.append(i.ConstraintOverride_pathMotionArc, 76);
        f653f.append(i.ConstraintOverride_layout_constraintTag, 77);
        f653f.append(i.ConstraintOverride_visibilityMode, 78);
        f653f.append(i.ConstraintOverride_layout_constrainedWidth, 80);
        f653f.append(i.ConstraintOverride_layout_constrainedHeight, 81);
        f653f.append(i.ConstraintOverride_polarRelativeTo, 82);
        f653f.append(i.ConstraintOverride_transformPivotTarget, 83);
        f653f.append(i.ConstraintOverride_quantizeMotionSteps, 84);
        f653f.append(i.ConstraintOverride_quantizeMotionPhase, 85);
        f653f.append(i.ConstraintOverride_quantizeMotionInterpolator, 86);
        f653f.append(i.ConstraintOverride_layout_wrapBehaviorInParent, 97);
    }

    public static int a(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void g(java.lang.Object r8, android.content.res.TypedArray r9, int r10, int r11) {
        /*
            Method dump skipped, instruction units count: 365
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.b.d.g(java.lang.Object, android.content.res.TypedArray, int, int):void");
    }

    public static void h(ConstraintLayout.a aVar, String str) {
        if (str != null) {
            int length = str.length();
            int iIndexOf = str.indexOf(44);
            int i = -1;
            if (iIndexOf > 0 && iIndexOf < length - 1) {
                String strSubstring = str.substring(0, iIndexOf);
                i = strSubstring.equalsIgnoreCase("W") ? 0 : strSubstring.equalsIgnoreCase("H") ? 1 : -1;
                i = iIndexOf + 1;
            }
            int iIndexOf2 = str.indexOf(58);
            try {
                if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                    String strSubstring2 = str.substring(i);
                    if (strSubstring2.length() > 0) {
                        Float.parseFloat(strSubstring2);
                    }
                } else {
                    String strSubstring3 = str.substring(i, iIndexOf2);
                    String strSubstring4 = str.substring(iIndexOf2 + 1);
                    if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                        float f2 = Float.parseFloat(strSubstring3);
                        float f3 = Float.parseFloat(strSubstring4);
                        if (f2 > 0.0f && f3 > 0.0f) {
                            if (i == 1) {
                                Math.abs(f3 / f2);
                            } else {
                                Math.abs(f2 / f3);
                            }
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        aVar.G = str;
    }

    public void b(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.f656c.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.f656c.containsKey(Integer.valueOf(id))) {
                StringBuilder sbD = b.a.a.a.a.d("id unknown ");
                sbD.append(l.i.B(childAt));
                Log.w("ConstraintSet", sbD.toString());
            } else {
                if (this.f655b && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.f656c.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = this.f656c.get(Integer.valueOf(id));
                        if (aVar != null) {
                            if (childAt instanceof Barrier) {
                                aVar.f660d.i0 = 1;
                                Barrier barrier = (Barrier) childAt;
                                barrier.setId(id);
                                barrier.setType(aVar.f660d.g0);
                                barrier.setMargin(aVar.f660d.h0);
                                barrier.setAllowsGoneWidget(aVar.f660d.o0);
                                b bVar = aVar.f660d;
                                int[] iArr = bVar.j0;
                                if (iArr != null) {
                                    barrier.setReferencedIds(iArr);
                                } else {
                                    String str = bVar.k0;
                                    if (str != null) {
                                        bVar.j0 = d(barrier, str);
                                        barrier.setReferencedIds(aVar.f660d.j0);
                                    }
                                }
                            }
                            ConstraintLayout.a aVar2 = (ConstraintLayout.a) childAt.getLayoutParams();
                            aVar2.a();
                            aVar.a(aVar2);
                            if (z) {
                                a.f.b.a.b(childAt, aVar.f662f);
                            }
                            childAt.setLayoutParams(aVar2);
                            C0010d c0010d = aVar.f658b;
                            if (c0010d.f683c == 0) {
                                childAt.setVisibility(c0010d.f682b);
                            }
                            childAt.setAlpha(aVar.f658b.f684d);
                            childAt.setRotation(aVar.f661e.f687b);
                            childAt.setRotationX(aVar.f661e.f688c);
                            childAt.setRotationY(aVar.f661e.f689d);
                            childAt.setScaleX(aVar.f661e.f690e);
                            childAt.setScaleY(aVar.f661e.f691f);
                            e eVar = aVar.f661e;
                            if (eVar.i != -1) {
                                if (((View) childAt.getParent()).findViewById(aVar.f661e.i) != null) {
                                    float bottom = (r4.getBottom() + r4.getTop()) / 2.0f;
                                    float right = (r4.getRight() + r4.getLeft()) / 2.0f;
                                    if (childAt.getRight() - childAt.getLeft() > 0 && childAt.getBottom() - childAt.getTop() > 0) {
                                        childAt.setPivotX(right - childAt.getLeft());
                                        childAt.setPivotY(bottom - childAt.getTop());
                                    }
                                }
                            } else {
                                if (!Float.isNaN(eVar.g)) {
                                    childAt.setPivotX(aVar.f661e.g);
                                }
                                if (!Float.isNaN(aVar.f661e.h)) {
                                    childAt.setPivotY(aVar.f661e.h);
                                }
                            }
                            childAt.setTranslationX(aVar.f661e.j);
                            childAt.setTranslationY(aVar.f661e.k);
                            childAt.setTranslationZ(aVar.f661e.l);
                            e eVar2 = aVar.f661e;
                            if (eVar2.m) {
                                childAt.setElevation(eVar2.n);
                            }
                        }
                    } else {
                        Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + id);
                    }
                }
            }
        }
        for (Integer num : hashSet) {
            a aVar3 = this.f656c.get(num);
            if (aVar3 != null) {
                if (aVar3.f660d.i0 == 1) {
                    Barrier barrier2 = new Barrier(constraintLayout.getContext());
                    barrier2.setId(num.intValue());
                    b bVar2 = aVar3.f660d;
                    int[] iArr2 = bVar2.j0;
                    if (iArr2 != null) {
                        barrier2.setReferencedIds(iArr2);
                    } else {
                        String str2 = bVar2.k0;
                        if (str2 != null) {
                            bVar2.j0 = d(barrier2, str2);
                            barrier2.setReferencedIds(aVar3.f660d.j0);
                        }
                    }
                    barrier2.setType(aVar3.f660d.g0);
                    barrier2.setMargin(aVar3.f660d.h0);
                    ConstraintLayout.a aVarB = constraintLayout.generateDefaultLayoutParams();
                    barrier2.l();
                    aVar3.a(aVarB);
                    constraintLayout.addView(barrier2, aVarB);
                }
                if (aVar3.f660d.f669a) {
                    View guideline = new Guideline(constraintLayout.getContext());
                    guideline.setId(num.intValue());
                    ConstraintLayout.a aVarB2 = constraintLayout.generateDefaultLayoutParams();
                    aVar3.a(aVarB2);
                    constraintLayout.addView(guideline, aVarB2);
                }
            }
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt2 = constraintLayout.getChildAt(i2);
            if ((childAt2 instanceof a.f.b.b) && ((a.f.b.b) childAt2) == null) {
                throw null;
            }
        }
    }

    public void c(ConstraintLayout constraintLayout) {
        a.f.b.a aVar;
        d dVar = this;
        int childCount = constraintLayout.getChildCount();
        dVar.f656c.clear();
        int i = 0;
        while (i < childCount) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.a aVar2 = (ConstraintLayout.a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (dVar.f655b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!dVar.f656c.containsKey(Integer.valueOf(id))) {
                dVar.f656c.put(Integer.valueOf(id), new a());
            }
            a aVar3 = dVar.f656c.get(Integer.valueOf(id));
            if (aVar3 != null) {
                HashMap<String, a.f.b.a> map = dVar.f654a;
                HashMap<String, a.f.b.a> map2 = new HashMap<>();
                Class<?> cls = childAt.getClass();
                for (String str : map.keySet()) {
                    a.f.b.a aVar4 = map.get(str);
                    try {
                        if (str.equals("BackgroundColor")) {
                            aVar = new a.f.b.a(aVar4, Integer.valueOf(((ColorDrawable) childAt.getBackground()).getColor()));
                        } else {
                            try {
                                aVar = new a.f.b.a(aVar4, cls.getMethod("getMap" + str, new Class[0]).invoke(childAt, new Object[0]));
                            } catch (IllegalAccessException e2) {
                                e = e2;
                                e.printStackTrace();
                            } catch (NoSuchMethodException e3) {
                                e = e3;
                                e.printStackTrace();
                            } catch (InvocationTargetException e4) {
                                e = e4;
                                e.printStackTrace();
                            }
                        }
                        map2.put(str, aVar);
                    } catch (IllegalAccessException e5) {
                        e = e5;
                    } catch (NoSuchMethodException e6) {
                        e = e6;
                    } catch (InvocationTargetException e7) {
                        e = e7;
                    }
                }
                aVar3.f662f = map2;
                aVar3.b(id, aVar2);
                aVar3.f658b.f682b = childAt.getVisibility();
                aVar3.f658b.f684d = childAt.getAlpha();
                aVar3.f661e.f687b = childAt.getRotation();
                aVar3.f661e.f688c = childAt.getRotationX();
                aVar3.f661e.f689d = childAt.getRotationY();
                aVar3.f661e.f690e = childAt.getScaleX();
                aVar3.f661e.f691f = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    e eVar = aVar3.f661e;
                    eVar.g = pivotX;
                    eVar.h = pivotY;
                }
                aVar3.f661e.j = childAt.getTranslationX();
                aVar3.f661e.k = childAt.getTranslationY();
                aVar3.f661e.l = childAt.getTranslationZ();
                e eVar2 = aVar3.f661e;
                if (eVar2.m) {
                    eVar2.n = childAt.getElevation();
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    aVar3.f660d.o0 = barrier.getAllowsGoneWidget();
                    aVar3.f660d.j0 = barrier.getReferencedIds();
                    aVar3.f660d.g0 = barrier.getType();
                    aVar3.f660d.h0 = barrier.getMargin();
                }
            }
            i++;
            dVar = this;
        }
    }

    public final int[] d(View view, String str) {
        int iIntValue;
        Object objC;
        String[] strArrSplit = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i = 0;
        int i2 = 0;
        while (i < strArrSplit.length) {
            String strTrim = strArrSplit[i].trim();
            try {
                iIntValue = h.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
                iIntValue = 0;
            }
            if (iIntValue == 0) {
                iIntValue = context.getResources().getIdentifier(strTrim, "id", context.getPackageName());
            }
            if (iIntValue == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (objC = ((ConstraintLayout) view.getParent()).c(0, strTrim)) != null && (objC instanceof Integer)) {
                iIntValue = ((Integer) objC).intValue();
            }
            iArr[i2] = iIntValue;
            i++;
            i2++;
        }
        return i2 != strArrSplit.length ? Arrays.copyOf(iArr, i2) : iArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x01e0 A[PHI: r10 r15
  0x01e0: PHI (r10v221 int) = (r10v189 int), (r10v222 int) binds: [B:69:0x01de, B:40:0x012c] A[DONT_GENERATE, DONT_INLINE]
  0x01e0: PHI (r15v9 int) = (r15v6 int), (r15v10 int) binds: [B:69:0x01de, B:40:0x012c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final a.f.b.d.a e(android.content.Context r18, android.util.AttributeSet r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 2754
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.b.d.e(android.content.Context, android.util.AttributeSet, boolean):a.f.b.d$a");
    }

    public void f(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    a aVarE = e(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        aVarE.f660d.f669a = true;
                    }
                    this.f656c.put(Integer.valueOf(aVarE.f657a), aVarE);
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }
}
