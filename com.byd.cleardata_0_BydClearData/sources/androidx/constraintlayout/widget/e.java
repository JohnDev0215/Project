package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.f;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f1117a = {0, 4, 8};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static SparseIntArray f1118b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<String, androidx.constraintlayout.widget.b> f1119c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f1120d = true;
    private HashMap<Integer, a> e = new HashMap<>();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1121a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f1122b = new d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c f1123c = new c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b f1124d = new b();
        public final C0035e e = new C0035e();
        public HashMap<String, androidx.constraintlayout.widget.b> f = new HashMap<>();

        /* JADX INFO: Access modifiers changed from: private */
        public void f(int i, ConstraintLayout.b bVar) {
            this.f1121a = i;
            b bVar2 = this.f1124d;
            bVar2.i = bVar.f1088d;
            bVar2.j = bVar.e;
            bVar2.k = bVar.f;
            bVar2.l = bVar.g;
            bVar2.m = bVar.h;
            bVar2.n = bVar.i;
            bVar2.o = bVar.j;
            bVar2.p = bVar.k;
            bVar2.q = bVar.l;
            bVar2.r = bVar.p;
            bVar2.s = bVar.q;
            bVar2.t = bVar.r;
            bVar2.u = bVar.s;
            bVar2.v = bVar.z;
            bVar2.w = bVar.A;
            bVar2.x = bVar.B;
            bVar2.y = bVar.m;
            bVar2.z = bVar.n;
            bVar2.A = bVar.o;
            bVar2.B = bVar.Q;
            bVar2.C = bVar.R;
            bVar2.D = bVar.S;
            bVar2.h = bVar.f1087c;
            bVar2.f = bVar.f1085a;
            bVar2.g = bVar.f1086b;
            b bVar3 = this.f1124d;
            bVar3.f1128d = ((ViewGroup.MarginLayoutParams) bVar).width;
            bVar3.e = ((ViewGroup.MarginLayoutParams) bVar).height;
            bVar3.E = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            bVar3.F = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
            bVar3.G = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            bVar3.H = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
            bVar3.Q = bVar.F;
            bVar3.R = bVar.E;
            bVar3.T = bVar.H;
            bVar3.S = bVar.G;
            bVar3.i0 = bVar.T;
            bVar3.j0 = bVar.U;
            bVar3.U = bVar.I;
            bVar3.V = bVar.J;
            bVar3.W = bVar.M;
            bVar3.X = bVar.N;
            bVar3.Y = bVar.K;
            bVar3.Z = bVar.L;
            bVar3.a0 = bVar.O;
            bVar3.b0 = bVar.P;
            bVar3.h0 = bVar.V;
            bVar3.L = bVar.u;
            bVar3.N = bVar.w;
            bVar3.K = bVar.t;
            bVar3.M = bVar.v;
            b bVar4 = this.f1124d;
            bVar4.P = bVar.x;
            bVar4.O = bVar.y;
            if (Build.VERSION.SDK_INT >= 17) {
                bVar4.I = bVar.getMarginEnd();
                this.f1124d.J = bVar.getMarginStart();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g(int i, f.a aVar) {
            f(i, aVar);
            this.f1122b.f1136d = aVar.p0;
            C0035e c0035e = this.e;
            c0035e.f1139c = aVar.s0;
            c0035e.f1140d = aVar.t0;
            c0035e.e = aVar.u0;
            c0035e.f = aVar.v0;
            c0035e.g = aVar.w0;
            c0035e.h = aVar.x0;
            c0035e.i = aVar.y0;
            c0035e.j = aVar.z0;
            c0035e.k = aVar.A0;
            c0035e.l = aVar.B0;
            c0035e.n = aVar.r0;
            c0035e.m = aVar.q0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void h(androidx.constraintlayout.widget.c cVar, int i, f.a aVar) {
            g(i, aVar);
            if (cVar instanceof androidx.constraintlayout.widget.a) {
                b bVar = this.f1124d;
                bVar.e0 = 1;
                androidx.constraintlayout.widget.a aVar2 = (androidx.constraintlayout.widget.a) cVar;
                bVar.c0 = aVar2.getType();
                this.f1124d.f0 = aVar2.getReferencedIds();
                this.f1124d.d0 = aVar2.getMargin();
            }
        }

        public void d(ConstraintLayout.b bVar) {
            b bVar2 = this.f1124d;
            bVar.f1088d = bVar2.i;
            bVar.e = bVar2.j;
            bVar.f = bVar2.k;
            bVar.g = bVar2.l;
            bVar.h = bVar2.m;
            bVar.i = bVar2.n;
            bVar.j = bVar2.o;
            bVar.k = bVar2.p;
            bVar.l = bVar2.q;
            bVar.p = bVar2.r;
            bVar.q = bVar2.s;
            bVar.r = bVar2.t;
            bVar.s = bVar2.u;
            ((ViewGroup.MarginLayoutParams) bVar).leftMargin = bVar2.E;
            ((ViewGroup.MarginLayoutParams) bVar).rightMargin = bVar2.F;
            ((ViewGroup.MarginLayoutParams) bVar).topMargin = bVar2.G;
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = bVar2.H;
            bVar.x = bVar2.P;
            bVar.y = bVar2.O;
            bVar.u = bVar2.L;
            bVar.w = bVar2.N;
            bVar.z = bVar2.v;
            bVar.A = bVar2.w;
            bVar.m = bVar2.y;
            bVar.n = bVar2.z;
            b bVar3 = this.f1124d;
            bVar.o = bVar3.A;
            bVar.B = bVar3.x;
            bVar.Q = bVar3.B;
            bVar.R = bVar3.C;
            bVar.F = bVar3.Q;
            bVar.E = bVar3.R;
            bVar.H = bVar3.T;
            bVar.G = bVar3.S;
            bVar.T = bVar3.i0;
            bVar.U = bVar3.j0;
            bVar.I = bVar3.U;
            bVar.J = bVar3.V;
            bVar.M = bVar3.W;
            bVar.N = bVar3.X;
            bVar.K = bVar3.Y;
            bVar.L = bVar3.Z;
            bVar.O = bVar3.a0;
            bVar.P = bVar3.b0;
            bVar.S = bVar3.D;
            bVar.f1087c = bVar3.h;
            bVar.f1085a = bVar3.f;
            bVar.f1086b = bVar3.g;
            ((ViewGroup.MarginLayoutParams) bVar).width = bVar3.f1128d;
            ((ViewGroup.MarginLayoutParams) bVar).height = bVar3.e;
            String str = bVar3.h0;
            if (str != null) {
                bVar.V = str;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                bVar.setMarginStart(this.f1124d.J);
                bVar.setMarginEnd(this.f1124d.I);
            }
            bVar.a();
        }

        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.f1124d.a(this.f1124d);
            aVar.f1123c.a(this.f1123c);
            aVar.f1122b.a(this.f1122b);
            aVar.e.a(this.e);
            aVar.f1121a = this.f1121a;
            return aVar;
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1125a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1128d;
        public int e;
        public int[] f0;
        public String g0;
        public String h0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1126b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1127c = false;
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
            f1125a = sparseIntArray;
            sparseIntArray.append(j.E3, 24);
            f1125a.append(j.F3, 25);
            f1125a.append(j.H3, 28);
            f1125a.append(j.I3, 29);
            f1125a.append(j.N3, 35);
            f1125a.append(j.M3, 34);
            f1125a.append(j.p3, 4);
            f1125a.append(j.o3, 3);
            f1125a.append(j.m3, 1);
            f1125a.append(j.S3, 6);
            f1125a.append(j.T3, 7);
            f1125a.append(j.w3, 17);
            f1125a.append(j.x3, 18);
            f1125a.append(j.y3, 19);
            f1125a.append(j.X2, 26);
            f1125a.append(j.J3, 31);
            f1125a.append(j.K3, 32);
            f1125a.append(j.v3, 10);
            f1125a.append(j.u3, 9);
            f1125a.append(j.W3, 13);
            f1125a.append(j.Z3, 16);
            f1125a.append(j.X3, 14);
            f1125a.append(j.U3, 11);
            f1125a.append(j.Y3, 15);
            f1125a.append(j.V3, 12);
            f1125a.append(j.Q3, 38);
            f1125a.append(j.C3, 37);
            f1125a.append(j.B3, 39);
            f1125a.append(j.P3, 40);
            f1125a.append(j.A3, 20);
            f1125a.append(j.O3, 36);
            f1125a.append(j.t3, 5);
            f1125a.append(j.D3, 76);
            f1125a.append(j.L3, 76);
            f1125a.append(j.G3, 76);
            f1125a.append(j.n3, 76);
            f1125a.append(j.l3, 76);
            f1125a.append(j.a3, 23);
            f1125a.append(j.c3, 27);
            f1125a.append(j.e3, 30);
            f1125a.append(j.f3, 8);
            f1125a.append(j.b3, 33);
            f1125a.append(j.d3, 2);
            f1125a.append(j.Y2, 22);
            f1125a.append(j.Z2, 21);
            f1125a.append(j.q3, 61);
            f1125a.append(j.s3, 62);
            f1125a.append(j.r3, 63);
            f1125a.append(j.R3, 69);
            f1125a.append(j.z3, 70);
            f1125a.append(j.j3, 71);
            f1125a.append(j.h3, 72);
            f1125a.append(j.i3, 73);
            f1125a.append(j.k3, 74);
            f1125a.append(j.g3, 75);
        }

        public void a(b bVar) {
            this.f1126b = bVar.f1126b;
            this.f1128d = bVar.f1128d;
            this.f1127c = bVar.f1127c;
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
            this.J = bVar.J;
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
            this.h0 = bVar.h0;
            int[] iArr = bVar.f0;
            if (iArr != null) {
                this.f0 = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.f0 = null;
            }
            this.g0 = bVar.g0;
            this.i0 = bVar.i0;
            this.j0 = bVar.j0;
            this.k0 = bVar.k0;
        }

        void b(Context context, AttributeSet attributeSet) {
            StringBuilder sb;
            String str;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.W2);
            this.f1127c = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                int i2 = f1125a.get(index);
                if (i2 == 80) {
                    this.i0 = typedArrayObtainStyledAttributes.getBoolean(index, this.i0);
                } else if (i2 != 81) {
                    switch (i2) {
                        case 1:
                            this.q = e.l(typedArrayObtainStyledAttributes, index, this.q);
                            break;
                        case 2:
                            this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.H);
                            break;
                        case 3:
                            this.p = e.l(typedArrayObtainStyledAttributes, index, this.p);
                            break;
                        case 4:
                            this.o = e.l(typedArrayObtainStyledAttributes, index, this.o);
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
                            this.u = e.l(typedArrayObtainStyledAttributes, index, this.u);
                            break;
                        case 10:
                            this.t = e.l(typedArrayObtainStyledAttributes, index, this.t);
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
                            this.f1128d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f1128d);
                            break;
                        case 23:
                            this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.E);
                            break;
                        case 24:
                            this.i = e.l(typedArrayObtainStyledAttributes, index, this.i);
                            break;
                        case 25:
                            this.j = e.l(typedArrayObtainStyledAttributes, index, this.j);
                            break;
                        case 26:
                            this.D = typedArrayObtainStyledAttributes.getInt(index, this.D);
                            break;
                        case 27:
                            this.F = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.F);
                            break;
                        case 28:
                            this.k = e.l(typedArrayObtainStyledAttributes, index, this.k);
                            break;
                        case 29:
                            this.l = e.l(typedArrayObtainStyledAttributes, index, this.l);
                            break;
                        case 30:
                            if (Build.VERSION.SDK_INT >= 17) {
                                this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                            }
                            break;
                        case 31:
                            this.r = e.l(typedArrayObtainStyledAttributes, index, this.r);
                            break;
                        case 32:
                            this.s = e.l(typedArrayObtainStyledAttributes, index, this.s);
                            break;
                        case 33:
                            this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.G);
                            break;
                        case 34:
                            this.n = e.l(typedArrayObtainStyledAttributes, index, this.n);
                            break;
                        case 35:
                            this.m = e.l(typedArrayObtainStyledAttributes, index, this.m);
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
                                            this.y = e.l(typedArrayObtainStyledAttributes, index, this.y);
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
                                                    continue;
                                                case 70:
                                                    this.b0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                                    continue;
                                                case 71:
                                                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                                    continue;
                                                case 72:
                                                    this.c0 = typedArrayObtainStyledAttributes.getInt(index, this.c0);
                                                    continue;
                                                case 73:
                                                    this.d0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.d0);
                                                    continue;
                                                case 74:
                                                    this.g0 = typedArrayObtainStyledAttributes.getString(index);
                                                    continue;
                                                case 75:
                                                    this.k0 = typedArrayObtainStyledAttributes.getBoolean(index, this.k0);
                                                    continue;
                                                case 76:
                                                    sb = new StringBuilder();
                                                    str = "unused attribute 0x";
                                                    break;
                                                case 77:
                                                    this.h0 = typedArrayObtainStyledAttributes.getString(index);
                                                    continue;
                                                default:
                                                    sb = new StringBuilder();
                                                    str = "Unknown attribute 0x";
                                                    break;
                                            }
                                            sb.append(str);
                                            sb.append(Integer.toHexString(index));
                                            sb.append("   ");
                                            sb.append(f1125a.get(index));
                                            Log.w("ConstraintSet", sb.toString());
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

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1129a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1130b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1131c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f1132d = null;
        public int e = -1;
        public int f = 0;
        public float g = Float.NaN;
        public float h = Float.NaN;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1129a = sparseIntArray;
            sparseIntArray.append(j.k4, 1);
            f1129a.append(j.m4, 2);
            f1129a.append(j.n4, 3);
            f1129a.append(j.j4, 4);
            f1129a.append(j.i4, 5);
            f1129a.append(j.l4, 6);
        }

        public void a(c cVar) {
            this.f1130b = cVar.f1130b;
            this.f1131c = cVar.f1131c;
            this.f1132d = cVar.f1132d;
            this.e = cVar.e;
            this.f = cVar.f;
            this.h = cVar.h;
            this.g = cVar.g;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.h4);
            this.f1130b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (f1129a.get(index)) {
                    case 1:
                        this.h = typedArrayObtainStyledAttributes.getFloat(index, this.h);
                        break;
                    case 2:
                        this.e = typedArrayObtainStyledAttributes.getInt(index, this.e);
                        break;
                    case 3:
                        this.f1132d = typedArrayObtainStyledAttributes.peekValue(index).type == 3 ? typedArrayObtainStyledAttributes.getString(index) : a.e.a.a.a.f165b[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        break;
                    case 4:
                        this.f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.f1131c = e.l(typedArrayObtainStyledAttributes, index, this.f1131c);
                        break;
                    case 6:
                        this.g = typedArrayObtainStyledAttributes.getFloat(index, this.g);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1133a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1134b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1135c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1136d = 1.0f;
        public float e = Float.NaN;

        public void a(d dVar) {
            this.f1133a = dVar.f1133a;
            this.f1134b = dVar.f1134b;
            this.f1136d = dVar.f1136d;
            this.e = dVar.e;
            this.f1135c = dVar.f1135c;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.w4);
            this.f1133a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == j.y4) {
                    this.f1136d = typedArrayObtainStyledAttributes.getFloat(index, this.f1136d);
                } else if (index == j.x4) {
                    this.f1134b = typedArrayObtainStyledAttributes.getInt(index, this.f1134b);
                    this.f1134b = e.f1117a[this.f1134b];
                } else if (index == j.A4) {
                    this.f1135c = typedArrayObtainStyledAttributes.getInt(index, this.f1135c);
                } else if (index == j.z4) {
                    this.e = typedArrayObtainStyledAttributes.getFloat(index, this.e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.e$e, reason: collision with other inner class name */
    public static class C0035e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1137a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1138b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1139c = 0.0f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1140d = 0.0f;
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
            f1137a = sparseIntArray;
            sparseIntArray.append(j.U4, 1);
            f1137a.append(j.V4, 2);
            f1137a.append(j.W4, 3);
            f1137a.append(j.S4, 4);
            f1137a.append(j.T4, 5);
            f1137a.append(j.O4, 6);
            f1137a.append(j.P4, 7);
            f1137a.append(j.Q4, 8);
            f1137a.append(j.R4, 9);
            f1137a.append(j.X4, 10);
            f1137a.append(j.Y4, 11);
        }

        public void a(C0035e c0035e) {
            this.f1138b = c0035e.f1138b;
            this.f1139c = c0035e.f1139c;
            this.f1140d = c0035e.f1140d;
            this.e = c0035e.e;
            this.f = c0035e.f;
            this.g = c0035e.g;
            this.h = c0035e.h;
            this.i = c0035e.i;
            this.j = c0035e.j;
            this.k = c0035e.k;
            this.l = c0035e.l;
            this.m = c0035e.m;
            this.n = c0035e.n;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.N4);
            this.f1138b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (f1137a.get(index)) {
                    case 1:
                        this.f1139c = typedArrayObtainStyledAttributes.getFloat(index, this.f1139c);
                        break;
                    case 2:
                        this.f1140d = typedArrayObtainStyledAttributes.getFloat(index, this.f1140d);
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
        f1118b = sparseIntArray;
        sparseIntArray.append(j.u0, 25);
        f1118b.append(j.v0, 26);
        f1118b.append(j.x0, 29);
        f1118b.append(j.y0, 30);
        f1118b.append(j.E0, 36);
        f1118b.append(j.D0, 35);
        f1118b.append(j.c0, 4);
        f1118b.append(j.b0, 3);
        f1118b.append(j.Z, 1);
        f1118b.append(j.M0, 6);
        f1118b.append(j.N0, 7);
        f1118b.append(j.j0, 17);
        f1118b.append(j.k0, 18);
        f1118b.append(j.l0, 19);
        f1118b.append(j.s, 27);
        f1118b.append(j.z0, 32);
        f1118b.append(j.A0, 33);
        f1118b.append(j.i0, 10);
        f1118b.append(j.h0, 9);
        f1118b.append(j.Q0, 13);
        f1118b.append(j.T0, 16);
        f1118b.append(j.R0, 14);
        f1118b.append(j.O0, 11);
        f1118b.append(j.S0, 15);
        f1118b.append(j.P0, 12);
        f1118b.append(j.H0, 40);
        f1118b.append(j.s0, 39);
        f1118b.append(j.r0, 41);
        f1118b.append(j.G0, 42);
        f1118b.append(j.q0, 20);
        f1118b.append(j.F0, 37);
        f1118b.append(j.g0, 5);
        f1118b.append(j.t0, 82);
        f1118b.append(j.C0, 82);
        f1118b.append(j.w0, 82);
        f1118b.append(j.a0, 82);
        f1118b.append(j.Y, 82);
        f1118b.append(j.x, 24);
        f1118b.append(j.z, 28);
        f1118b.append(j.L, 31);
        f1118b.append(j.M, 8);
        f1118b.append(j.y, 34);
        f1118b.append(j.A, 2);
        f1118b.append(j.v, 23);
        f1118b.append(j.w, 21);
        f1118b.append(j.u, 22);
        f1118b.append(j.B, 43);
        f1118b.append(j.O, 44);
        f1118b.append(j.J, 45);
        f1118b.append(j.K, 46);
        f1118b.append(j.I, 60);
        f1118b.append(j.G, 47);
        f1118b.append(j.H, 48);
        f1118b.append(j.C, 49);
        f1118b.append(j.D, 50);
        f1118b.append(j.E, 51);
        f1118b.append(j.F, 52);
        f1118b.append(j.N, 53);
        f1118b.append(j.I0, 54);
        f1118b.append(j.m0, 55);
        f1118b.append(j.J0, 56);
        f1118b.append(j.n0, 57);
        f1118b.append(j.K0, 58);
        f1118b.append(j.o0, 59);
        f1118b.append(j.d0, 61);
        f1118b.append(j.f0, 62);
        f1118b.append(j.e0, 63);
        f1118b.append(j.P, 64);
        f1118b.append(j.X0, 65);
        f1118b.append(j.V, 66);
        f1118b.append(j.Y0, 67);
        f1118b.append(j.V0, 79);
        f1118b.append(j.t, 38);
        f1118b.append(j.U0, 68);
        f1118b.append(j.L0, 69);
        f1118b.append(j.p0, 70);
        f1118b.append(j.T, 71);
        f1118b.append(j.R, 72);
        f1118b.append(j.S, 73);
        f1118b.append(j.U, 74);
        f1118b.append(j.Q, 75);
        f1118b.append(j.W0, 76);
        f1118b.append(j.B0, 77);
        f1118b.append(j.Z0, 78);
        f1118b.append(j.X, 80);
        f1118b.append(j.W, 81);
    }

    private int[] h(View view, String str) {
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
                iIntValue = i.class.getField(strTrim).getInt(null);
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

    private a i(Context context, AttributeSet attributeSet) {
        a aVar = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.r);
        m(context, aVar, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int l(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    private void m(Context context, a aVar, TypedArray typedArray) {
        c cVar;
        String string;
        StringBuilder sb;
        String str;
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index != j.t && j.L != index && j.M != index) {
                aVar.f1123c.f1130b = true;
                aVar.f1124d.f1127c = true;
                aVar.f1122b.f1133a = true;
                aVar.e.f1138b = true;
            }
            switch (f1118b.get(index)) {
                case 1:
                    b bVar = aVar.f1124d;
                    bVar.q = l(typedArray, index, bVar.q);
                    continue;
                    break;
                case 2:
                    b bVar2 = aVar.f1124d;
                    bVar2.H = typedArray.getDimensionPixelSize(index, bVar2.H);
                    continue;
                    break;
                case 3:
                    b bVar3 = aVar.f1124d;
                    bVar3.p = l(typedArray, index, bVar3.p);
                    continue;
                    break;
                case 4:
                    b bVar4 = aVar.f1124d;
                    bVar4.o = l(typedArray, index, bVar4.o);
                    continue;
                    break;
                case 5:
                    aVar.f1124d.x = typedArray.getString(index);
                    continue;
                    break;
                case 6:
                    b bVar5 = aVar.f1124d;
                    bVar5.B = typedArray.getDimensionPixelOffset(index, bVar5.B);
                    continue;
                    break;
                case 7:
                    b bVar6 = aVar.f1124d;
                    bVar6.C = typedArray.getDimensionPixelOffset(index, bVar6.C);
                    continue;
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        b bVar7 = aVar.f1124d;
                        bVar7.I = typedArray.getDimensionPixelSize(index, bVar7.I);
                    } else {
                        continue;
                    }
                    break;
                case 9:
                    b bVar8 = aVar.f1124d;
                    bVar8.u = l(typedArray, index, bVar8.u);
                    continue;
                    break;
                case 10:
                    b bVar9 = aVar.f1124d;
                    bVar9.t = l(typedArray, index, bVar9.t);
                    continue;
                    break;
                case 11:
                    b bVar10 = aVar.f1124d;
                    bVar10.N = typedArray.getDimensionPixelSize(index, bVar10.N);
                    continue;
                    break;
                case 12:
                    b bVar11 = aVar.f1124d;
                    bVar11.O = typedArray.getDimensionPixelSize(index, bVar11.O);
                    continue;
                    break;
                case 13:
                    b bVar12 = aVar.f1124d;
                    bVar12.K = typedArray.getDimensionPixelSize(index, bVar12.K);
                    continue;
                    break;
                case 14:
                    b bVar13 = aVar.f1124d;
                    bVar13.M = typedArray.getDimensionPixelSize(index, bVar13.M);
                    continue;
                    break;
                case 15:
                    b bVar14 = aVar.f1124d;
                    bVar14.P = typedArray.getDimensionPixelSize(index, bVar14.P);
                    continue;
                    break;
                case 16:
                    b bVar15 = aVar.f1124d;
                    bVar15.L = typedArray.getDimensionPixelSize(index, bVar15.L);
                    continue;
                    break;
                case 17:
                    b bVar16 = aVar.f1124d;
                    bVar16.f = typedArray.getDimensionPixelOffset(index, bVar16.f);
                    continue;
                    break;
                case 18:
                    b bVar17 = aVar.f1124d;
                    bVar17.g = typedArray.getDimensionPixelOffset(index, bVar17.g);
                    continue;
                    break;
                case 19:
                    b bVar18 = aVar.f1124d;
                    bVar18.h = typedArray.getFloat(index, bVar18.h);
                    continue;
                    break;
                case 20:
                    b bVar19 = aVar.f1124d;
                    bVar19.v = typedArray.getFloat(index, bVar19.v);
                    continue;
                    break;
                case 21:
                    b bVar20 = aVar.f1124d;
                    bVar20.e = typedArray.getLayoutDimension(index, bVar20.e);
                    continue;
                    break;
                case 22:
                    d dVar = aVar.f1122b;
                    dVar.f1134b = typedArray.getInt(index, dVar.f1134b);
                    d dVar2 = aVar.f1122b;
                    dVar2.f1134b = f1117a[dVar2.f1134b];
                    continue;
                    break;
                case 23:
                    b bVar21 = aVar.f1124d;
                    bVar21.f1128d = typedArray.getLayoutDimension(index, bVar21.f1128d);
                    continue;
                    break;
                case 24:
                    b bVar22 = aVar.f1124d;
                    bVar22.E = typedArray.getDimensionPixelSize(index, bVar22.E);
                    continue;
                    break;
                case 25:
                    b bVar23 = aVar.f1124d;
                    bVar23.i = l(typedArray, index, bVar23.i);
                    continue;
                    break;
                case 26:
                    b bVar24 = aVar.f1124d;
                    bVar24.j = l(typedArray, index, bVar24.j);
                    continue;
                    break;
                case 27:
                    b bVar25 = aVar.f1124d;
                    bVar25.D = typedArray.getInt(index, bVar25.D);
                    continue;
                    break;
                case 28:
                    b bVar26 = aVar.f1124d;
                    bVar26.F = typedArray.getDimensionPixelSize(index, bVar26.F);
                    continue;
                    break;
                case 29:
                    b bVar27 = aVar.f1124d;
                    bVar27.k = l(typedArray, index, bVar27.k);
                    continue;
                    break;
                case 30:
                    b bVar28 = aVar.f1124d;
                    bVar28.l = l(typedArray, index, bVar28.l);
                    continue;
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        b bVar29 = aVar.f1124d;
                        bVar29.J = typedArray.getDimensionPixelSize(index, bVar29.J);
                    } else {
                        continue;
                    }
                    break;
                case 32:
                    b bVar30 = aVar.f1124d;
                    bVar30.r = l(typedArray, index, bVar30.r);
                    continue;
                    break;
                case 33:
                    b bVar31 = aVar.f1124d;
                    bVar31.s = l(typedArray, index, bVar31.s);
                    continue;
                    break;
                case 34:
                    b bVar32 = aVar.f1124d;
                    bVar32.G = typedArray.getDimensionPixelSize(index, bVar32.G);
                    continue;
                    break;
                case 35:
                    b bVar33 = aVar.f1124d;
                    bVar33.n = l(typedArray, index, bVar33.n);
                    continue;
                    break;
                case 36:
                    b bVar34 = aVar.f1124d;
                    bVar34.m = l(typedArray, index, bVar34.m);
                    continue;
                    break;
                case 37:
                    b bVar35 = aVar.f1124d;
                    bVar35.w = typedArray.getFloat(index, bVar35.w);
                    continue;
                    break;
                case 38:
                    aVar.f1121a = typedArray.getResourceId(index, aVar.f1121a);
                    continue;
                    break;
                case 39:
                    b bVar36 = aVar.f1124d;
                    bVar36.R = typedArray.getFloat(index, bVar36.R);
                    continue;
                    break;
                case 40:
                    b bVar37 = aVar.f1124d;
                    bVar37.Q = typedArray.getFloat(index, bVar37.Q);
                    continue;
                    break;
                case 41:
                    b bVar38 = aVar.f1124d;
                    bVar38.S = typedArray.getInt(index, bVar38.S);
                    continue;
                    break;
                case 42:
                    b bVar39 = aVar.f1124d;
                    bVar39.T = typedArray.getInt(index, bVar39.T);
                    continue;
                    break;
                case 43:
                    d dVar3 = aVar.f1122b;
                    dVar3.f1136d = typedArray.getFloat(index, dVar3.f1136d);
                    continue;
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        C0035e c0035e = aVar.e;
                        c0035e.m = true;
                        c0035e.n = typedArray.getDimension(index, c0035e.n);
                    } else {
                        continue;
                    }
                    break;
                case 45:
                    C0035e c0035e2 = aVar.e;
                    c0035e2.f1140d = typedArray.getFloat(index, c0035e2.f1140d);
                    continue;
                    break;
                case 46:
                    C0035e c0035e3 = aVar.e;
                    c0035e3.e = typedArray.getFloat(index, c0035e3.e);
                    continue;
                    break;
                case 47:
                    C0035e c0035e4 = aVar.e;
                    c0035e4.f = typedArray.getFloat(index, c0035e4.f);
                    continue;
                    break;
                case 48:
                    C0035e c0035e5 = aVar.e;
                    c0035e5.g = typedArray.getFloat(index, c0035e5.g);
                    continue;
                    break;
                case 49:
                    C0035e c0035e6 = aVar.e;
                    c0035e6.h = typedArray.getDimension(index, c0035e6.h);
                    continue;
                    break;
                case 50:
                    C0035e c0035e7 = aVar.e;
                    c0035e7.i = typedArray.getDimension(index, c0035e7.i);
                    continue;
                    break;
                case 51:
                    C0035e c0035e8 = aVar.e;
                    c0035e8.j = typedArray.getDimension(index, c0035e8.j);
                    continue;
                    break;
                case 52:
                    C0035e c0035e9 = aVar.e;
                    c0035e9.k = typedArray.getDimension(index, c0035e9.k);
                    continue;
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        C0035e c0035e10 = aVar.e;
                        c0035e10.l = typedArray.getDimension(index, c0035e10.l);
                    } else {
                        continue;
                    }
                    break;
                case 54:
                    b bVar40 = aVar.f1124d;
                    bVar40.U = typedArray.getInt(index, bVar40.U);
                    continue;
                    break;
                case 55:
                    b bVar41 = aVar.f1124d;
                    bVar41.V = typedArray.getInt(index, bVar41.V);
                    continue;
                    break;
                case 56:
                    b bVar42 = aVar.f1124d;
                    bVar42.W = typedArray.getDimensionPixelSize(index, bVar42.W);
                    continue;
                    break;
                case 57:
                    b bVar43 = aVar.f1124d;
                    bVar43.X = typedArray.getDimensionPixelSize(index, bVar43.X);
                    continue;
                    break;
                case 58:
                    b bVar44 = aVar.f1124d;
                    bVar44.Y = typedArray.getDimensionPixelSize(index, bVar44.Y);
                    continue;
                    break;
                case 59:
                    b bVar45 = aVar.f1124d;
                    bVar45.Z = typedArray.getDimensionPixelSize(index, bVar45.Z);
                    continue;
                    break;
                case 60:
                    C0035e c0035e11 = aVar.e;
                    c0035e11.f1139c = typedArray.getFloat(index, c0035e11.f1139c);
                    continue;
                    break;
                case 61:
                    b bVar46 = aVar.f1124d;
                    bVar46.y = l(typedArray, index, bVar46.y);
                    continue;
                    break;
                case 62:
                    b bVar47 = aVar.f1124d;
                    bVar47.z = typedArray.getDimensionPixelSize(index, bVar47.z);
                    continue;
                    break;
                case 63:
                    b bVar48 = aVar.f1124d;
                    bVar48.A = typedArray.getFloat(index, bVar48.A);
                    continue;
                    break;
                case 64:
                    c cVar2 = aVar.f1123c;
                    cVar2.f1131c = l(typedArray, index, cVar2.f1131c);
                    continue;
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        cVar = aVar.f1123c;
                        string = typedArray.getString(index);
                    } else {
                        cVar = aVar.f1123c;
                        string = a.e.a.a.a.f165b[typedArray.getInteger(index, 0)];
                    }
                    cVar.f1132d = string;
                    continue;
                    break;
                case 66:
                    aVar.f1123c.f = typedArray.getInt(index, 0);
                    continue;
                    break;
                case 67:
                    c cVar3 = aVar.f1123c;
                    cVar3.h = typedArray.getFloat(index, cVar3.h);
                    continue;
                    break;
                case 68:
                    d dVar4 = aVar.f1122b;
                    dVar4.e = typedArray.getFloat(index, dVar4.e);
                    continue;
                    break;
                case 69:
                    aVar.f1124d.a0 = typedArray.getFloat(index, 1.0f);
                    continue;
                    break;
                case 70:
                    aVar.f1124d.b0 = typedArray.getFloat(index, 1.0f);
                    continue;
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    continue;
                    break;
                case 72:
                    b bVar49 = aVar.f1124d;
                    bVar49.c0 = typedArray.getInt(index, bVar49.c0);
                    continue;
                    break;
                case 73:
                    b bVar50 = aVar.f1124d;
                    bVar50.d0 = typedArray.getDimensionPixelSize(index, bVar50.d0);
                    continue;
                    break;
                case 74:
                    aVar.f1124d.g0 = typedArray.getString(index);
                    continue;
                    break;
                case 75:
                    b bVar51 = aVar.f1124d;
                    bVar51.k0 = typedArray.getBoolean(index, bVar51.k0);
                    continue;
                    break;
                case 76:
                    c cVar4 = aVar.f1123c;
                    cVar4.e = typedArray.getInt(index, cVar4.e);
                    continue;
                    break;
                case 77:
                    aVar.f1124d.h0 = typedArray.getString(index);
                    continue;
                    break;
                case 78:
                    d dVar5 = aVar.f1122b;
                    dVar5.f1135c = typedArray.getInt(index, dVar5.f1135c);
                    continue;
                    break;
                case 79:
                    c cVar5 = aVar.f1123c;
                    cVar5.g = typedArray.getFloat(index, cVar5.g);
                    continue;
                    break;
                case 80:
                    b bVar52 = aVar.f1124d;
                    bVar52.i0 = typedArray.getBoolean(index, bVar52.i0);
                    continue;
                    break;
                case 81:
                    b bVar53 = aVar.f1124d;
                    bVar53.j0 = typedArray.getBoolean(index, bVar53.j0);
                    continue;
                    break;
                case 82:
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
            sb.append(f1118b.get(index));
            Log.w("ConstraintSet", sb.toString());
        }
    }

    public void c(ConstraintLayout constraintLayout) {
        d(constraintLayout, true);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    void d(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.e.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.e.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + a.e.a.b.a.a(childAt));
            } else {
                if (this.f1120d && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.e.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = this.e.get(Integer.valueOf(id));
                        if (childAt instanceof androidx.constraintlayout.widget.a) {
                            aVar.f1124d.e0 = 1;
                        }
                        int i2 = aVar.f1124d.e0;
                        if (i2 != -1 && i2 == 1) {
                            androidx.constraintlayout.widget.a aVar2 = (androidx.constraintlayout.widget.a) childAt;
                            aVar2.setId(id);
                            aVar2.setType(aVar.f1124d.c0);
                            aVar2.setMargin(aVar.f1124d.d0);
                            aVar2.setAllowsGoneWidget(aVar.f1124d.k0);
                            b bVar = aVar.f1124d;
                            int[] iArr = bVar.f0;
                            if (iArr != null) {
                                aVar2.setReferencedIds(iArr);
                            } else {
                                String str = bVar.g0;
                                if (str != null) {
                                    bVar.f0 = h(aVar2, str);
                                    aVar2.setReferencedIds(aVar.f1124d.f0);
                                }
                            }
                        }
                        ConstraintLayout.b bVar2 = (ConstraintLayout.b) childAt.getLayoutParams();
                        bVar2.a();
                        aVar.d(bVar2);
                        if (z) {
                            androidx.constraintlayout.widget.b.c(childAt, aVar.f);
                        }
                        childAt.setLayoutParams(bVar2);
                        d dVar = aVar.f1122b;
                        if (dVar.f1135c == 0) {
                            childAt.setVisibility(dVar.f1134b);
                        }
                        int i3 = Build.VERSION.SDK_INT;
                        if (i3 >= 17) {
                            childAt.setAlpha(aVar.f1122b.f1136d);
                            childAt.setRotation(aVar.e.f1139c);
                            childAt.setRotationX(aVar.e.f1140d);
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
                                C0035e c0035e = aVar.e;
                                if (c0035e.m) {
                                    childAt.setElevation(c0035e.n);
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
            a aVar3 = this.e.get(num);
            int i4 = aVar3.f1124d.e0;
            if (i4 != -1 && i4 == 1) {
                androidx.constraintlayout.widget.a aVar4 = new androidx.constraintlayout.widget.a(constraintLayout.getContext());
                aVar4.setId(num.intValue());
                b bVar3 = aVar3.f1124d;
                int[] iArr2 = bVar3.f0;
                if (iArr2 != null) {
                    aVar4.setReferencedIds(iArr2);
                } else {
                    String str2 = bVar3.g0;
                    if (str2 != null) {
                        bVar3.f0 = h(aVar4, str2);
                        aVar4.setReferencedIds(aVar3.f1124d.f0);
                    }
                }
                aVar4.setType(aVar3.f1124d.c0);
                aVar4.setMargin(aVar3.f1124d.d0);
                ConstraintLayout.b bVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                aVar4.m();
                aVar3.d(bVarGenerateDefaultLayoutParams);
                constraintLayout.addView(aVar4, bVarGenerateDefaultLayoutParams);
            }
            if (aVar3.f1124d.f1126b) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.b bVarGenerateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                aVar3.d(bVarGenerateDefaultLayoutParams2);
                constraintLayout.addView(guideline, bVarGenerateDefaultLayoutParams2);
            }
        }
    }

    public void e(Context context, int i) {
        f((ConstraintLayout) LayoutInflater.from(context).inflate(i, (ViewGroup) null));
    }

    public void f(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.e.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1120d && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.e.containsKey(Integer.valueOf(id))) {
                this.e.put(Integer.valueOf(id), new a());
            }
            a aVar = this.e.get(Integer.valueOf(id));
            aVar.f = androidx.constraintlayout.widget.b.a(this.f1119c, childAt);
            aVar.f(id, bVar);
            aVar.f1122b.f1134b = childAt.getVisibility();
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 17) {
                aVar.f1122b.f1136d = childAt.getAlpha();
                aVar.e.f1139c = childAt.getRotation();
                aVar.e.f1140d = childAt.getRotationX();
                aVar.e.e = childAt.getRotationY();
                aVar.e.f = childAt.getScaleX();
                aVar.e.g = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    C0035e c0035e = aVar.e;
                    c0035e.h = pivotX;
                    c0035e.i = pivotY;
                }
                aVar.e.j = childAt.getTranslationX();
                aVar.e.k = childAt.getTranslationY();
                if (i2 >= 21) {
                    aVar.e.l = childAt.getTranslationZ();
                    C0035e c0035e2 = aVar.e;
                    if (c0035e2.m) {
                        c0035e2.n = childAt.getElevation();
                    }
                }
            }
            if (childAt instanceof androidx.constraintlayout.widget.a) {
                androidx.constraintlayout.widget.a aVar2 = (androidx.constraintlayout.widget.a) childAt;
                aVar.f1124d.k0 = aVar2.n();
                aVar.f1124d.f0 = aVar2.getReferencedIds();
                aVar.f1124d.c0 = aVar2.getType();
                aVar.f1124d.d0 = aVar2.getMargin();
            }
        }
    }

    public void g(f fVar) {
        int childCount = fVar.getChildCount();
        this.e.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = fVar.getChildAt(i);
            f.a aVar = (f.a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1120d && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.e.containsKey(Integer.valueOf(id))) {
                this.e.put(Integer.valueOf(id), new a());
            }
            a aVar2 = this.e.get(Integer.valueOf(id));
            if (childAt instanceof androidx.constraintlayout.widget.c) {
                aVar2.h((androidx.constraintlayout.widget.c) childAt, id, aVar);
            }
            aVar2.g(id, aVar);
        }
    }

    public void j(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    a aVarI = i(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        aVarI.f1124d.f1126b = true;
                    }
                    this.e.put(Integer.valueOf(aVarI.f1121a), aVarI);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
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
    public void k(android.content.Context r10, org.xmlpull.v1.XmlPullParser r11) {
        /*
            Method dump skipped, instruction units count: 448
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.e.k(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }
}
