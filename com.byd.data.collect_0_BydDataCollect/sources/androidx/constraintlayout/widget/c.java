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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import b.e.b.k.j;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintSet.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f1064a = {0, 4, 8};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static SparseIntArray f1065b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1066c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HashMap<String, androidx.constraintlayout.widget.a> f1067d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1068e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private HashMap<Integer, a> f1069f = new HashMap<>();

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1070a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f1071b = new d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final C0012c f1072c = new C0012c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b f1073d = new b();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final e f1074e = new e();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public HashMap<String, androidx.constraintlayout.widget.a> f1075f = new HashMap<>();

        /* JADX INFO: Access modifiers changed from: private */
        public void f(int i2, ConstraintLayout.b bVar) {
            this.f1070a = i2;
            b bVar2 = this.f1073d;
            bVar2.f1084i = bVar.f1012d;
            bVar2.j = bVar.f1013e;
            bVar2.k = bVar.f1014f;
            bVar2.l = bVar.f1015g;
            bVar2.m = bVar.f1016h;
            bVar2.n = bVar.f1017i;
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
            bVar2.f1083h = bVar.f1011c;
            bVar2.f1081f = bVar.f1009a;
            bVar2.f1082g = bVar.f1010b;
            b bVar3 = this.f1073d;
            bVar3.f1079d = ((ViewGroup.MarginLayoutParams) bVar).width;
            bVar3.f1080e = ((ViewGroup.MarginLayoutParams) bVar).height;
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
            b bVar4 = this.f1073d;
            bVar4.P = bVar.x;
            bVar4.O = bVar.y;
            if (Build.VERSION.SDK_INT >= 17) {
                bVar4.I = bVar.getMarginEnd();
                this.f1073d.J = bVar.getMarginStart();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g(int i2, Constraints.a aVar) {
            f(i2, aVar);
            this.f1071b.f1096d = aVar.p0;
            e eVar = this.f1074e;
            eVar.f1100c = aVar.s0;
            eVar.f1101d = aVar.t0;
            eVar.f1102e = aVar.u0;
            eVar.f1103f = aVar.v0;
            eVar.f1104g = aVar.w0;
            eVar.f1105h = aVar.x0;
            eVar.f1106i = aVar.y0;
            eVar.j = aVar.z0;
            eVar.k = aVar.A0;
            eVar.l = aVar.B0;
            eVar.n = aVar.r0;
            eVar.m = aVar.q0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void h(ConstraintHelper constraintHelper, int i2, Constraints.a aVar) {
            g(i2, aVar);
            if (constraintHelper instanceof Barrier) {
                b bVar = this.f1073d;
                bVar.e0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                bVar.c0 = barrier.getType();
                this.f1073d.f0 = barrier.getReferencedIds();
                this.f1073d.d0 = barrier.getMargin();
            }
        }

        public void d(ConstraintLayout.b bVar) {
            b bVar2 = this.f1073d;
            bVar.f1012d = bVar2.f1084i;
            bVar.f1013e = bVar2.j;
            bVar.f1014f = bVar2.k;
            bVar.f1015g = bVar2.l;
            bVar.f1016h = bVar2.m;
            bVar.f1017i = bVar2.n;
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
            b bVar3 = this.f1073d;
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
            bVar.f1011c = bVar3.f1083h;
            bVar.f1009a = bVar3.f1081f;
            bVar.f1010b = bVar3.f1082g;
            ((ViewGroup.MarginLayoutParams) bVar).width = bVar3.f1079d;
            ((ViewGroup.MarginLayoutParams) bVar).height = bVar3.f1080e;
            String str = bVar3.h0;
            if (str != null) {
                bVar.V = str;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                bVar.setMarginStart(this.f1073d.J);
                bVar.setMarginEnd(this.f1073d.I);
            }
            bVar.c();
        }

        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.f1073d.a(this.f1073d);
            aVar.f1072c.a(this.f1072c);
            aVar.f1071b.a(this.f1071b);
            aVar.f1074e.a(this.f1074e);
            aVar.f1070a = this.f1070a;
            return aVar;
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1076a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1079d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1080e;
        public int[] f0;
        public String g0;
        public String h0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1077b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1078c = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1081f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1082g = -1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1083h = -1.0f;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1084i = -1;
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
            f1076a = sparseIntArray;
            sparseIntArray.append(f.Layout_layout_constraintLeft_toLeftOf, 24);
            f1076a.append(f.Layout_layout_constraintLeft_toRightOf, 25);
            f1076a.append(f.Layout_layout_constraintRight_toLeftOf, 28);
            f1076a.append(f.Layout_layout_constraintRight_toRightOf, 29);
            f1076a.append(f.Layout_layout_constraintTop_toTopOf, 35);
            f1076a.append(f.Layout_layout_constraintTop_toBottomOf, 34);
            f1076a.append(f.Layout_layout_constraintBottom_toTopOf, 4);
            f1076a.append(f.Layout_layout_constraintBottom_toBottomOf, 3);
            f1076a.append(f.Layout_layout_constraintBaseline_toBaselineOf, 1);
            f1076a.append(f.Layout_layout_editor_absoluteX, 6);
            f1076a.append(f.Layout_layout_editor_absoluteY, 7);
            f1076a.append(f.Layout_layout_constraintGuide_begin, 17);
            f1076a.append(f.Layout_layout_constraintGuide_end, 18);
            f1076a.append(f.Layout_layout_constraintGuide_percent, 19);
            f1076a.append(f.Layout_android_orientation, 26);
            f1076a.append(f.Layout_layout_constraintStart_toEndOf, 31);
            f1076a.append(f.Layout_layout_constraintStart_toStartOf, 32);
            f1076a.append(f.Layout_layout_constraintEnd_toStartOf, 10);
            f1076a.append(f.Layout_layout_constraintEnd_toEndOf, 9);
            f1076a.append(f.Layout_layout_goneMarginLeft, 13);
            f1076a.append(f.Layout_layout_goneMarginTop, 16);
            f1076a.append(f.Layout_layout_goneMarginRight, 14);
            f1076a.append(f.Layout_layout_goneMarginBottom, 11);
            f1076a.append(f.Layout_layout_goneMarginStart, 15);
            f1076a.append(f.Layout_layout_goneMarginEnd, 12);
            f1076a.append(f.Layout_layout_constraintVertical_weight, 38);
            f1076a.append(f.Layout_layout_constraintHorizontal_weight, 37);
            f1076a.append(f.Layout_layout_constraintHorizontal_chainStyle, 39);
            f1076a.append(f.Layout_layout_constraintVertical_chainStyle, 40);
            f1076a.append(f.Layout_layout_constraintHorizontal_bias, 20);
            f1076a.append(f.Layout_layout_constraintVertical_bias, 36);
            f1076a.append(f.Layout_layout_constraintDimensionRatio, 5);
            f1076a.append(f.Layout_layout_constraintLeft_creator, 76);
            f1076a.append(f.Layout_layout_constraintTop_creator, 76);
            f1076a.append(f.Layout_layout_constraintRight_creator, 76);
            f1076a.append(f.Layout_layout_constraintBottom_creator, 76);
            f1076a.append(f.Layout_layout_constraintBaseline_creator, 76);
            f1076a.append(f.Layout_android_layout_marginLeft, 23);
            f1076a.append(f.Layout_android_layout_marginRight, 27);
            f1076a.append(f.Layout_android_layout_marginStart, 30);
            f1076a.append(f.Layout_android_layout_marginEnd, 8);
            f1076a.append(f.Layout_android_layout_marginTop, 33);
            f1076a.append(f.Layout_android_layout_marginBottom, 2);
            f1076a.append(f.Layout_android_layout_width, 22);
            f1076a.append(f.Layout_android_layout_height, 21);
            f1076a.append(f.Layout_layout_constraintCircle, 61);
            f1076a.append(f.Layout_layout_constraintCircleRadius, 62);
            f1076a.append(f.Layout_layout_constraintCircleAngle, 63);
            f1076a.append(f.Layout_layout_constraintWidth_percent, 69);
            f1076a.append(f.Layout_layout_constraintHeight_percent, 70);
            f1076a.append(f.Layout_chainUseRtl, 71);
            f1076a.append(f.Layout_barrierDirection, 72);
            f1076a.append(f.Layout_barrierMargin, 73);
            f1076a.append(f.Layout_constraint_referenced_ids, 74);
            f1076a.append(f.Layout_barrierAllowsGoneWidgets, 75);
        }

        public void a(b bVar) {
            this.f1077b = bVar.f1077b;
            this.f1079d = bVar.f1079d;
            this.f1078c = bVar.f1078c;
            this.f1080e = bVar.f1080e;
            this.f1081f = bVar.f1081f;
            this.f1082g = bVar.f1082g;
            this.f1083h = bVar.f1083h;
            this.f1084i = bVar.f1084i;
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
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.Layout);
            this.f1078c = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                int i3 = f1076a.get(index);
                if (i3 == 80) {
                    this.i0 = typedArrayObtainStyledAttributes.getBoolean(index, this.i0);
                } else if (i3 != 81) {
                    switch (i3) {
                        case 1:
                            this.q = c.w(typedArrayObtainStyledAttributes, index, this.q);
                            break;
                        case 2:
                            this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.H);
                            break;
                        case 3:
                            this.p = c.w(typedArrayObtainStyledAttributes, index, this.p);
                            break;
                        case 4:
                            this.o = c.w(typedArrayObtainStyledAttributes, index, this.o);
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
                            this.u = c.w(typedArrayObtainStyledAttributes, index, this.u);
                            break;
                        case 10:
                            this.t = c.w(typedArrayObtainStyledAttributes, index, this.t);
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
                            this.f1081f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1081f);
                            break;
                        case 18:
                            this.f1082g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1082g);
                            break;
                        case 19:
                            this.f1083h = typedArrayObtainStyledAttributes.getFloat(index, this.f1083h);
                            break;
                        case 20:
                            this.v = typedArrayObtainStyledAttributes.getFloat(index, this.v);
                            break;
                        case 21:
                            this.f1080e = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f1080e);
                            break;
                        case 22:
                            this.f1079d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f1079d);
                            break;
                        case 23:
                            this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.E);
                            break;
                        case 24:
                            this.f1084i = c.w(typedArrayObtainStyledAttributes, index, this.f1084i);
                            break;
                        case 25:
                            this.j = c.w(typedArrayObtainStyledAttributes, index, this.j);
                            break;
                        case 26:
                            this.D = typedArrayObtainStyledAttributes.getInt(index, this.D);
                            break;
                        case 27:
                            this.F = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.F);
                            break;
                        case 28:
                            this.k = c.w(typedArrayObtainStyledAttributes, index, this.k);
                            break;
                        case 29:
                            this.l = c.w(typedArrayObtainStyledAttributes, index, this.l);
                            break;
                        case 30:
                            if (Build.VERSION.SDK_INT >= 17) {
                                this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                            }
                            break;
                        case 31:
                            this.r = c.w(typedArrayObtainStyledAttributes, index, this.r);
                            break;
                        case 32:
                            this.s = c.w(typedArrayObtainStyledAttributes, index, this.s);
                            break;
                        case 33:
                            this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.G);
                            break;
                        case 34:
                            this.n = c.w(typedArrayObtainStyledAttributes, index, this.n);
                            break;
                        case 35:
                            this.m = c.w(typedArrayObtainStyledAttributes, index, this.m);
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
                                            this.y = c.w(typedArrayObtainStyledAttributes, index, this.y);
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
                                                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1076a.get(index));
                                                    break;
                                                case 77:
                                                    this.h0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                default:
                                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1076a.get(index));
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

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class C0012c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1085a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1086b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1087c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f1088d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1089e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1090f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f1091g = Float.NaN;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1092h = Float.NaN;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1085a = sparseIntArray;
            sparseIntArray.append(f.Motion_motionPathRotate, 1);
            f1085a.append(f.Motion_pathMotionArc, 2);
            f1085a.append(f.Motion_transitionEasing, 3);
            f1085a.append(f.Motion_drawPath, 4);
            f1085a.append(f.Motion_animate_relativeTo, 5);
            f1085a.append(f.Motion_motionStagger, 6);
        }

        public void a(C0012c c0012c) {
            this.f1086b = c0012c.f1086b;
            this.f1087c = c0012c.f1087c;
            this.f1088d = c0012c.f1088d;
            this.f1089e = c0012c.f1089e;
            this.f1090f = c0012c.f1090f;
            this.f1092h = c0012c.f1092h;
            this.f1091g = c0012c.f1091g;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.Motion);
            this.f1086b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                switch (f1085a.get(index)) {
                    case 1:
                        this.f1092h = typedArrayObtainStyledAttributes.getFloat(index, this.f1092h);
                        break;
                    case 2:
                        this.f1089e = typedArrayObtainStyledAttributes.getInt(index, this.f1089e);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.f1088d = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.f1088d = b.e.a.a.c.f2316b[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.f1090f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.f1087c = c.w(typedArrayObtainStyledAttributes, index, this.f1087c);
                        break;
                    case 6:
                        this.f1091g = typedArrayObtainStyledAttributes.getFloat(index, this.f1091g);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1093a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1094b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1095c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1096d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1097e = Float.NaN;

        public void a(d dVar) {
            this.f1093a = dVar.f1093a;
            this.f1094b = dVar.f1094b;
            this.f1096d = dVar.f1096d;
            this.f1097e = dVar.f1097e;
            this.f1095c = dVar.f1095c;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.PropertySet);
            this.f1093a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == f.PropertySet_android_alpha) {
                    this.f1096d = typedArrayObtainStyledAttributes.getFloat(index, this.f1096d);
                } else if (index == f.PropertySet_android_visibility) {
                    this.f1094b = typedArrayObtainStyledAttributes.getInt(index, this.f1094b);
                    this.f1094b = c.f1064a[this.f1094b];
                } else if (index == f.PropertySet_visibilityMode) {
                    this.f1095c = typedArrayObtainStyledAttributes.getInt(index, this.f1095c);
                } else if (index == f.PropertySet_motionProgress) {
                    this.f1097e = typedArrayObtainStyledAttributes.getFloat(index, this.f1097e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static SparseIntArray f1098a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1099b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1100c = 0.0f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1101d = 0.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1102e = 0.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f1103f = 1.0f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f1104g = 1.0f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1105h = Float.NaN;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f1106i = Float.NaN;
        public float j = 0.0f;
        public float k = 0.0f;
        public float l = 0.0f;
        public boolean m = false;
        public float n = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1098a = sparseIntArray;
            sparseIntArray.append(f.Transform_android_rotation, 1);
            f1098a.append(f.Transform_android_rotationX, 2);
            f1098a.append(f.Transform_android_rotationY, 3);
            f1098a.append(f.Transform_android_scaleX, 4);
            f1098a.append(f.Transform_android_scaleY, 5);
            f1098a.append(f.Transform_android_transformPivotX, 6);
            f1098a.append(f.Transform_android_transformPivotY, 7);
            f1098a.append(f.Transform_android_translationX, 8);
            f1098a.append(f.Transform_android_translationY, 9);
            f1098a.append(f.Transform_android_translationZ, 10);
            f1098a.append(f.Transform_android_elevation, 11);
        }

        public void a(e eVar) {
            this.f1099b = eVar.f1099b;
            this.f1100c = eVar.f1100c;
            this.f1101d = eVar.f1101d;
            this.f1102e = eVar.f1102e;
            this.f1103f = eVar.f1103f;
            this.f1104g = eVar.f1104g;
            this.f1105h = eVar.f1105h;
            this.f1106i = eVar.f1106i;
            this.j = eVar.j;
            this.k = eVar.k;
            this.l = eVar.l;
            this.m = eVar.m;
            this.n = eVar.n;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.Transform);
            this.f1099b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                switch (f1098a.get(index)) {
                    case 1:
                        this.f1100c = typedArrayObtainStyledAttributes.getFloat(index, this.f1100c);
                        break;
                    case 2:
                        this.f1101d = typedArrayObtainStyledAttributes.getFloat(index, this.f1101d);
                        break;
                    case 3:
                        this.f1102e = typedArrayObtainStyledAttributes.getFloat(index, this.f1102e);
                        break;
                    case 4:
                        this.f1103f = typedArrayObtainStyledAttributes.getFloat(index, this.f1103f);
                        break;
                    case 5:
                        this.f1104g = typedArrayObtainStyledAttributes.getFloat(index, this.f1104g);
                        break;
                    case 6:
                        this.f1105h = typedArrayObtainStyledAttributes.getDimension(index, this.f1105h);
                        break;
                    case 7:
                        this.f1106i = typedArrayObtainStyledAttributes.getDimension(index, this.f1106i);
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
        f1065b = sparseIntArray;
        sparseIntArray.append(f.Constraint_layout_constraintLeft_toLeftOf, 25);
        f1065b.append(f.Constraint_layout_constraintLeft_toRightOf, 26);
        f1065b.append(f.Constraint_layout_constraintRight_toLeftOf, 29);
        f1065b.append(f.Constraint_layout_constraintRight_toRightOf, 30);
        f1065b.append(f.Constraint_layout_constraintTop_toTopOf, 36);
        f1065b.append(f.Constraint_layout_constraintTop_toBottomOf, 35);
        f1065b.append(f.Constraint_layout_constraintBottom_toTopOf, 4);
        f1065b.append(f.Constraint_layout_constraintBottom_toBottomOf, 3);
        f1065b.append(f.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        f1065b.append(f.Constraint_layout_editor_absoluteX, 6);
        f1065b.append(f.Constraint_layout_editor_absoluteY, 7);
        f1065b.append(f.Constraint_layout_constraintGuide_begin, 17);
        f1065b.append(f.Constraint_layout_constraintGuide_end, 18);
        f1065b.append(f.Constraint_layout_constraintGuide_percent, 19);
        f1065b.append(f.Constraint_android_orientation, 27);
        f1065b.append(f.Constraint_layout_constraintStart_toEndOf, 32);
        f1065b.append(f.Constraint_layout_constraintStart_toStartOf, 33);
        f1065b.append(f.Constraint_layout_constraintEnd_toStartOf, 10);
        f1065b.append(f.Constraint_layout_constraintEnd_toEndOf, 9);
        f1065b.append(f.Constraint_layout_goneMarginLeft, 13);
        f1065b.append(f.Constraint_layout_goneMarginTop, 16);
        f1065b.append(f.Constraint_layout_goneMarginRight, 14);
        f1065b.append(f.Constraint_layout_goneMarginBottom, 11);
        f1065b.append(f.Constraint_layout_goneMarginStart, 15);
        f1065b.append(f.Constraint_layout_goneMarginEnd, 12);
        f1065b.append(f.Constraint_layout_constraintVertical_weight, 40);
        f1065b.append(f.Constraint_layout_constraintHorizontal_weight, 39);
        f1065b.append(f.Constraint_layout_constraintHorizontal_chainStyle, 41);
        f1065b.append(f.Constraint_layout_constraintVertical_chainStyle, 42);
        f1065b.append(f.Constraint_layout_constraintHorizontal_bias, 20);
        f1065b.append(f.Constraint_layout_constraintVertical_bias, 37);
        f1065b.append(f.Constraint_layout_constraintDimensionRatio, 5);
        f1065b.append(f.Constraint_layout_constraintLeft_creator, 82);
        f1065b.append(f.Constraint_layout_constraintTop_creator, 82);
        f1065b.append(f.Constraint_layout_constraintRight_creator, 82);
        f1065b.append(f.Constraint_layout_constraintBottom_creator, 82);
        f1065b.append(f.Constraint_layout_constraintBaseline_creator, 82);
        f1065b.append(f.Constraint_android_layout_marginLeft, 24);
        f1065b.append(f.Constraint_android_layout_marginRight, 28);
        f1065b.append(f.Constraint_android_layout_marginStart, 31);
        f1065b.append(f.Constraint_android_layout_marginEnd, 8);
        f1065b.append(f.Constraint_android_layout_marginTop, 34);
        f1065b.append(f.Constraint_android_layout_marginBottom, 2);
        f1065b.append(f.Constraint_android_layout_width, 23);
        f1065b.append(f.Constraint_android_layout_height, 21);
        f1065b.append(f.Constraint_android_visibility, 22);
        f1065b.append(f.Constraint_android_alpha, 43);
        f1065b.append(f.Constraint_android_elevation, 44);
        f1065b.append(f.Constraint_android_rotationX, 45);
        f1065b.append(f.Constraint_android_rotationY, 46);
        f1065b.append(f.Constraint_android_rotation, 60);
        f1065b.append(f.Constraint_android_scaleX, 47);
        f1065b.append(f.Constraint_android_scaleY, 48);
        f1065b.append(f.Constraint_android_transformPivotX, 49);
        f1065b.append(f.Constraint_android_transformPivotY, 50);
        f1065b.append(f.Constraint_android_translationX, 51);
        f1065b.append(f.Constraint_android_translationY, 52);
        f1065b.append(f.Constraint_android_translationZ, 53);
        f1065b.append(f.Constraint_layout_constraintWidth_default, 54);
        f1065b.append(f.Constraint_layout_constraintHeight_default, 55);
        f1065b.append(f.Constraint_layout_constraintWidth_max, 56);
        f1065b.append(f.Constraint_layout_constraintHeight_max, 57);
        f1065b.append(f.Constraint_layout_constraintWidth_min, 58);
        f1065b.append(f.Constraint_layout_constraintHeight_min, 59);
        f1065b.append(f.Constraint_layout_constraintCircle, 61);
        f1065b.append(f.Constraint_layout_constraintCircleRadius, 62);
        f1065b.append(f.Constraint_layout_constraintCircleAngle, 63);
        f1065b.append(f.Constraint_animate_relativeTo, 64);
        f1065b.append(f.Constraint_transitionEasing, 65);
        f1065b.append(f.Constraint_drawPath, 66);
        f1065b.append(f.Constraint_transitionPathRotate, 67);
        f1065b.append(f.Constraint_motionStagger, 79);
        f1065b.append(f.Constraint_android_id, 38);
        f1065b.append(f.Constraint_motionProgress, 68);
        f1065b.append(f.Constraint_layout_constraintWidth_percent, 69);
        f1065b.append(f.Constraint_layout_constraintHeight_percent, 70);
        f1065b.append(f.Constraint_chainUseRtl, 71);
        f1065b.append(f.Constraint_barrierDirection, 72);
        f1065b.append(f.Constraint_barrierMargin, 73);
        f1065b.append(f.Constraint_constraint_referenced_ids, 74);
        f1065b.append(f.Constraint_barrierAllowsGoneWidgets, 75);
        f1065b.append(f.Constraint_pathMotionArc, 76);
        f1065b.append(f.Constraint_layout_constraintTag, 77);
        f1065b.append(f.Constraint_visibilityMode, 78);
        f1065b.append(f.Constraint_layout_constrainedWidth, 80);
        f1065b.append(f.Constraint_layout_constrainedHeight, 81);
    }

    private int[] k(View view, String str) {
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
                iIntValue = androidx.constraintlayout.widget.e.class.getField(strTrim).getInt(null);
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

    private a l(Context context, AttributeSet attributeSet) {
        a aVar = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.Constraint);
        x(context, aVar, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    private a m(int i2) {
        if (!this.f1069f.containsKey(Integer.valueOf(i2))) {
            this.f1069f.put(Integer.valueOf(i2), new a());
        }
        return this.f1069f.get(Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int w(TypedArray typedArray, int i2, int i3) {
        int resourceId = typedArray.getResourceId(i2, i3);
        return resourceId == -1 ? typedArray.getInt(i2, -1) : resourceId;
    }

    private void x(Context context, a aVar, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArray.getIndex(i2);
            if (index != f.Constraint_android_id && f.Constraint_android_layout_marginStart != index && f.Constraint_android_layout_marginEnd != index) {
                aVar.f1072c.f1086b = true;
                aVar.f1073d.f1078c = true;
                aVar.f1071b.f1093a = true;
                aVar.f1074e.f1099b = true;
            }
            switch (f1065b.get(index)) {
                case 1:
                    b bVar = aVar.f1073d;
                    bVar.q = w(typedArray, index, bVar.q);
                    break;
                case 2:
                    b bVar2 = aVar.f1073d;
                    bVar2.H = typedArray.getDimensionPixelSize(index, bVar2.H);
                    break;
                case 3:
                    b bVar3 = aVar.f1073d;
                    bVar3.p = w(typedArray, index, bVar3.p);
                    break;
                case 4:
                    b bVar4 = aVar.f1073d;
                    bVar4.o = w(typedArray, index, bVar4.o);
                    break;
                case 5:
                    aVar.f1073d.x = typedArray.getString(index);
                    break;
                case 6:
                    b bVar5 = aVar.f1073d;
                    bVar5.B = typedArray.getDimensionPixelOffset(index, bVar5.B);
                    break;
                case 7:
                    b bVar6 = aVar.f1073d;
                    bVar6.C = typedArray.getDimensionPixelOffset(index, bVar6.C);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        b bVar7 = aVar.f1073d;
                        bVar7.I = typedArray.getDimensionPixelSize(index, bVar7.I);
                    }
                    break;
                case 9:
                    b bVar8 = aVar.f1073d;
                    bVar8.u = w(typedArray, index, bVar8.u);
                    break;
                case 10:
                    b bVar9 = aVar.f1073d;
                    bVar9.t = w(typedArray, index, bVar9.t);
                    break;
                case 11:
                    b bVar10 = aVar.f1073d;
                    bVar10.N = typedArray.getDimensionPixelSize(index, bVar10.N);
                    break;
                case 12:
                    b bVar11 = aVar.f1073d;
                    bVar11.O = typedArray.getDimensionPixelSize(index, bVar11.O);
                    break;
                case 13:
                    b bVar12 = aVar.f1073d;
                    bVar12.K = typedArray.getDimensionPixelSize(index, bVar12.K);
                    break;
                case 14:
                    b bVar13 = aVar.f1073d;
                    bVar13.M = typedArray.getDimensionPixelSize(index, bVar13.M);
                    break;
                case 15:
                    b bVar14 = aVar.f1073d;
                    bVar14.P = typedArray.getDimensionPixelSize(index, bVar14.P);
                    break;
                case 16:
                    b bVar15 = aVar.f1073d;
                    bVar15.L = typedArray.getDimensionPixelSize(index, bVar15.L);
                    break;
                case 17:
                    b bVar16 = aVar.f1073d;
                    bVar16.f1081f = typedArray.getDimensionPixelOffset(index, bVar16.f1081f);
                    break;
                case 18:
                    b bVar17 = aVar.f1073d;
                    bVar17.f1082g = typedArray.getDimensionPixelOffset(index, bVar17.f1082g);
                    break;
                case 19:
                    b bVar18 = aVar.f1073d;
                    bVar18.f1083h = typedArray.getFloat(index, bVar18.f1083h);
                    break;
                case 20:
                    b bVar19 = aVar.f1073d;
                    bVar19.v = typedArray.getFloat(index, bVar19.v);
                    break;
                case 21:
                    b bVar20 = aVar.f1073d;
                    bVar20.f1080e = typedArray.getLayoutDimension(index, bVar20.f1080e);
                    break;
                case 22:
                    d dVar = aVar.f1071b;
                    dVar.f1094b = typedArray.getInt(index, dVar.f1094b);
                    d dVar2 = aVar.f1071b;
                    dVar2.f1094b = f1064a[dVar2.f1094b];
                    break;
                case 23:
                    b bVar21 = aVar.f1073d;
                    bVar21.f1079d = typedArray.getLayoutDimension(index, bVar21.f1079d);
                    break;
                case 24:
                    b bVar22 = aVar.f1073d;
                    bVar22.E = typedArray.getDimensionPixelSize(index, bVar22.E);
                    break;
                case 25:
                    b bVar23 = aVar.f1073d;
                    bVar23.f1084i = w(typedArray, index, bVar23.f1084i);
                    break;
                case 26:
                    b bVar24 = aVar.f1073d;
                    bVar24.j = w(typedArray, index, bVar24.j);
                    break;
                case 27:
                    b bVar25 = aVar.f1073d;
                    bVar25.D = typedArray.getInt(index, bVar25.D);
                    break;
                case 28:
                    b bVar26 = aVar.f1073d;
                    bVar26.F = typedArray.getDimensionPixelSize(index, bVar26.F);
                    break;
                case 29:
                    b bVar27 = aVar.f1073d;
                    bVar27.k = w(typedArray, index, bVar27.k);
                    break;
                case 30:
                    b bVar28 = aVar.f1073d;
                    bVar28.l = w(typedArray, index, bVar28.l);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        b bVar29 = aVar.f1073d;
                        bVar29.J = typedArray.getDimensionPixelSize(index, bVar29.J);
                    }
                    break;
                case 32:
                    b bVar30 = aVar.f1073d;
                    bVar30.r = w(typedArray, index, bVar30.r);
                    break;
                case 33:
                    b bVar31 = aVar.f1073d;
                    bVar31.s = w(typedArray, index, bVar31.s);
                    break;
                case 34:
                    b bVar32 = aVar.f1073d;
                    bVar32.G = typedArray.getDimensionPixelSize(index, bVar32.G);
                    break;
                case 35:
                    b bVar33 = aVar.f1073d;
                    bVar33.n = w(typedArray, index, bVar33.n);
                    break;
                case 36:
                    b bVar34 = aVar.f1073d;
                    bVar34.m = w(typedArray, index, bVar34.m);
                    break;
                case 37:
                    b bVar35 = aVar.f1073d;
                    bVar35.w = typedArray.getFloat(index, bVar35.w);
                    break;
                case 38:
                    aVar.f1070a = typedArray.getResourceId(index, aVar.f1070a);
                    break;
                case 39:
                    b bVar36 = aVar.f1073d;
                    bVar36.R = typedArray.getFloat(index, bVar36.R);
                    break;
                case 40:
                    b bVar37 = aVar.f1073d;
                    bVar37.Q = typedArray.getFloat(index, bVar37.Q);
                    break;
                case 41:
                    b bVar38 = aVar.f1073d;
                    bVar38.S = typedArray.getInt(index, bVar38.S);
                    break;
                case 42:
                    b bVar39 = aVar.f1073d;
                    bVar39.T = typedArray.getInt(index, bVar39.T);
                    break;
                case 43:
                    d dVar3 = aVar.f1071b;
                    dVar3.f1096d = typedArray.getFloat(index, dVar3.f1096d);
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar = aVar.f1074e;
                        eVar.m = true;
                        eVar.n = typedArray.getDimension(index, eVar.n);
                    }
                    break;
                case 45:
                    e eVar2 = aVar.f1074e;
                    eVar2.f1101d = typedArray.getFloat(index, eVar2.f1101d);
                    break;
                case 46:
                    e eVar3 = aVar.f1074e;
                    eVar3.f1102e = typedArray.getFloat(index, eVar3.f1102e);
                    break;
                case 47:
                    e eVar4 = aVar.f1074e;
                    eVar4.f1103f = typedArray.getFloat(index, eVar4.f1103f);
                    break;
                case 48:
                    e eVar5 = aVar.f1074e;
                    eVar5.f1104g = typedArray.getFloat(index, eVar5.f1104g);
                    break;
                case 49:
                    e eVar6 = aVar.f1074e;
                    eVar6.f1105h = typedArray.getDimension(index, eVar6.f1105h);
                    break;
                case 50:
                    e eVar7 = aVar.f1074e;
                    eVar7.f1106i = typedArray.getDimension(index, eVar7.f1106i);
                    break;
                case 51:
                    e eVar8 = aVar.f1074e;
                    eVar8.j = typedArray.getDimension(index, eVar8.j);
                    break;
                case 52:
                    e eVar9 = aVar.f1074e;
                    eVar9.k = typedArray.getDimension(index, eVar9.k);
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar10 = aVar.f1074e;
                        eVar10.l = typedArray.getDimension(index, eVar10.l);
                    }
                    break;
                case 54:
                    b bVar40 = aVar.f1073d;
                    bVar40.U = typedArray.getInt(index, bVar40.U);
                    break;
                case 55:
                    b bVar41 = aVar.f1073d;
                    bVar41.V = typedArray.getInt(index, bVar41.V);
                    break;
                case 56:
                    b bVar42 = aVar.f1073d;
                    bVar42.W = typedArray.getDimensionPixelSize(index, bVar42.W);
                    break;
                case 57:
                    b bVar43 = aVar.f1073d;
                    bVar43.X = typedArray.getDimensionPixelSize(index, bVar43.X);
                    break;
                case 58:
                    b bVar44 = aVar.f1073d;
                    bVar44.Y = typedArray.getDimensionPixelSize(index, bVar44.Y);
                    break;
                case 59:
                    b bVar45 = aVar.f1073d;
                    bVar45.Z = typedArray.getDimensionPixelSize(index, bVar45.Z);
                    break;
                case 60:
                    e eVar11 = aVar.f1074e;
                    eVar11.f1100c = typedArray.getFloat(index, eVar11.f1100c);
                    break;
                case 61:
                    b bVar46 = aVar.f1073d;
                    bVar46.y = w(typedArray, index, bVar46.y);
                    break;
                case 62:
                    b bVar47 = aVar.f1073d;
                    bVar47.z = typedArray.getDimensionPixelSize(index, bVar47.z);
                    break;
                case 63:
                    b bVar48 = aVar.f1073d;
                    bVar48.A = typedArray.getFloat(index, bVar48.A);
                    break;
                case 64:
                    C0012c c0012c = aVar.f1072c;
                    c0012c.f1087c = w(typedArray, index, c0012c.f1087c);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.f1072c.f1088d = typedArray.getString(index);
                    } else {
                        aVar.f1072c.f1088d = b.e.a.a.c.f2316b[typedArray.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    aVar.f1072c.f1090f = typedArray.getInt(index, 0);
                    break;
                case 67:
                    C0012c c0012c2 = aVar.f1072c;
                    c0012c2.f1092h = typedArray.getFloat(index, c0012c2.f1092h);
                    break;
                case 68:
                    d dVar4 = aVar.f1071b;
                    dVar4.f1097e = typedArray.getFloat(index, dVar4.f1097e);
                    break;
                case 69:
                    aVar.f1073d.a0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    aVar.f1073d.b0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    b bVar49 = aVar.f1073d;
                    bVar49.c0 = typedArray.getInt(index, bVar49.c0);
                    break;
                case 73:
                    b bVar50 = aVar.f1073d;
                    bVar50.d0 = typedArray.getDimensionPixelSize(index, bVar50.d0);
                    break;
                case 74:
                    aVar.f1073d.g0 = typedArray.getString(index);
                    break;
                case 75:
                    b bVar51 = aVar.f1073d;
                    bVar51.k0 = typedArray.getBoolean(index, bVar51.k0);
                    break;
                case 76:
                    C0012c c0012c3 = aVar.f1072c;
                    c0012c3.f1089e = typedArray.getInt(index, c0012c3.f1089e);
                    break;
                case 77:
                    aVar.f1073d.h0 = typedArray.getString(index);
                    break;
                case 78:
                    d dVar5 = aVar.f1071b;
                    dVar5.f1095c = typedArray.getInt(index, dVar5.f1095c);
                    break;
                case 79:
                    C0012c c0012c4 = aVar.f1072c;
                    c0012c4.f1091g = typedArray.getFloat(index, c0012c4.f1091g);
                    break;
                case 80:
                    b bVar52 = aVar.f1073d;
                    bVar52.i0 = typedArray.getBoolean(index, bVar52.i0);
                    break;
                case 81:
                    b bVar53 = aVar.f1073d;
                    bVar53.j0 = typedArray.getBoolean(index, bVar53.j0);
                    break;
                case 82:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1065b.get(index));
                    break;
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1065b.get(index));
                    break;
            }
        }
    }

    public void A(boolean z) {
        this.f1068e = z;
    }

    public void B(boolean z) {
        this.f1066c = z;
    }

    public void c(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!this.f1069f.containsKey(Integer.valueOf(id))) {
                Log.v("ConstraintSet", "id unknown " + androidx.constraintlayout.motion.widget.a.c(childAt));
            } else {
                if (this.f1068e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (this.f1069f.containsKey(Integer.valueOf(id))) {
                    androidx.constraintlayout.widget.a.h(childAt, this.f1069f.get(Integer.valueOf(id)).f1075f);
                }
            }
        }
    }

    public void d(ConstraintLayout constraintLayout) {
        f(constraintLayout, true);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public void e(ConstraintHelper constraintHelper, b.e.b.k.e eVar, ConstraintLayout.b bVar, SparseArray<b.e.b.k.e> sparseArray) {
        int id = constraintHelper.getId();
        if (this.f1069f.containsKey(Integer.valueOf(id))) {
            a aVar = this.f1069f.get(Integer.valueOf(id));
            if (eVar instanceof j) {
                constraintHelper.n(aVar, (j) eVar, bVar, sparseArray);
            }
        }
    }

    void f(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.f1069f.keySet());
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!this.f1069f.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + androidx.constraintlayout.motion.widget.a.c(childAt));
            } else {
                if (this.f1068e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.f1069f.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = this.f1069f.get(Integer.valueOf(id));
                        if (childAt instanceof Barrier) {
                            aVar.f1073d.e0 = 1;
                        }
                        int i3 = aVar.f1073d.e0;
                        if (i3 != -1 && i3 == 1) {
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(aVar.f1073d.c0);
                            barrier.setMargin(aVar.f1073d.d0);
                            barrier.setAllowsGoneWidget(aVar.f1073d.k0);
                            b bVar = aVar.f1073d;
                            int[] iArr = bVar.f0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = bVar.g0;
                                if (str != null) {
                                    bVar.f0 = k(barrier, str);
                                    barrier.setReferencedIds(aVar.f1073d.f0);
                                }
                            }
                        }
                        ConstraintLayout.b bVar2 = (ConstraintLayout.b) childAt.getLayoutParams();
                        bVar2.c();
                        aVar.d(bVar2);
                        if (z) {
                            androidx.constraintlayout.widget.a.h(childAt, aVar.f1075f);
                        }
                        childAt.setLayoutParams(bVar2);
                        d dVar = aVar.f1071b;
                        if (dVar.f1095c == 0) {
                            childAt.setVisibility(dVar.f1094b);
                        }
                        int i4 = Build.VERSION.SDK_INT;
                        if (i4 >= 17) {
                            childAt.setAlpha(aVar.f1071b.f1096d);
                            childAt.setRotation(aVar.f1074e.f1100c);
                            childAt.setRotationX(aVar.f1074e.f1101d);
                            childAt.setRotationY(aVar.f1074e.f1102e);
                            childAt.setScaleX(aVar.f1074e.f1103f);
                            childAt.setScaleY(aVar.f1074e.f1104g);
                            if (!Float.isNaN(aVar.f1074e.f1105h)) {
                                childAt.setPivotX(aVar.f1074e.f1105h);
                            }
                            if (!Float.isNaN(aVar.f1074e.f1106i)) {
                                childAt.setPivotY(aVar.f1074e.f1106i);
                            }
                            childAt.setTranslationX(aVar.f1074e.j);
                            childAt.setTranslationY(aVar.f1074e.k);
                            if (i4 >= 21) {
                                childAt.setTranslationZ(aVar.f1074e.l);
                                e eVar = aVar.f1074e;
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
            a aVar2 = this.f1069f.get(num);
            int i5 = aVar2.f1073d.e0;
            if (i5 != -1 && i5 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                b bVar3 = aVar2.f1073d;
                int[] iArr2 = bVar3.f0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = bVar3.g0;
                    if (str2 != null) {
                        bVar3.f0 = k(barrier2, str2);
                        barrier2.setReferencedIds(aVar2.f1073d.f0);
                    }
                }
                barrier2.setType(aVar2.f1073d.c0);
                barrier2.setMargin(aVar2.f1073d.d0);
                ConstraintLayout.b bVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.u();
                aVar2.d(bVarGenerateDefaultLayoutParams);
                constraintLayout.addView(barrier2, bVarGenerateDefaultLayoutParams);
            }
            if (aVar2.f1073d.f1077b) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.b bVarGenerateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                aVar2.d(bVarGenerateDefaultLayoutParams2);
                constraintLayout.addView(guideline, bVarGenerateDefaultLayoutParams2);
            }
        }
    }

    public void g(int i2, ConstraintLayout.b bVar) {
        if (this.f1069f.containsKey(Integer.valueOf(i2))) {
            this.f1069f.get(Integer.valueOf(i2)).d(bVar);
        }
    }

    public void h(Context context, int i2) {
        i((ConstraintLayout) LayoutInflater.from(context).inflate(i2, (ViewGroup) null));
    }

    public void i(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.f1069f.clear();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1068e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1069f.containsKey(Integer.valueOf(id))) {
                this.f1069f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f1069f.get(Integer.valueOf(id));
            aVar.f1075f = androidx.constraintlayout.widget.a.b(this.f1067d, childAt);
            aVar.f(id, bVar);
            aVar.f1071b.f1094b = childAt.getVisibility();
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 17) {
                aVar.f1071b.f1096d = childAt.getAlpha();
                aVar.f1074e.f1100c = childAt.getRotation();
                aVar.f1074e.f1101d = childAt.getRotationX();
                aVar.f1074e.f1102e = childAt.getRotationY();
                aVar.f1074e.f1103f = childAt.getScaleX();
                aVar.f1074e.f1104g = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    e eVar = aVar.f1074e;
                    eVar.f1105h = pivotX;
                    eVar.f1106i = pivotY;
                }
                aVar.f1074e.j = childAt.getTranslationX();
                aVar.f1074e.k = childAt.getTranslationY();
                if (i3 >= 21) {
                    aVar.f1074e.l = childAt.getTranslationZ();
                    e eVar2 = aVar.f1074e;
                    if (eVar2.m) {
                        eVar2.n = childAt.getElevation();
                    }
                }
            }
            if (childAt instanceof Barrier) {
                Barrier barrier = (Barrier) childAt;
                aVar.f1073d.k0 = barrier.v();
                aVar.f1073d.f0 = barrier.getReferencedIds();
                aVar.f1073d.c0 = barrier.getType();
                aVar.f1073d.d0 = barrier.getMargin();
            }
        }
    }

    public void j(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.f1069f.clear();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraints.getChildAt(i2);
            Constraints.a aVar = (Constraints.a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1068e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1069f.containsKey(Integer.valueOf(id))) {
                this.f1069f.put(Integer.valueOf(id), new a());
            }
            a aVar2 = this.f1069f.get(Integer.valueOf(id));
            if (childAt instanceof ConstraintHelper) {
                aVar2.h((ConstraintHelper) childAt, id, aVar);
            }
            aVar2.g(id, aVar);
        }
    }

    public a n(int i2) {
        if (this.f1069f.containsKey(Integer.valueOf(i2))) {
            return this.f1069f.get(Integer.valueOf(i2));
        }
        return null;
    }

    public int o(int i2) {
        return m(i2).f1073d.f1080e;
    }

    public int[] p() {
        Integer[] numArr = (Integer[]) this.f1069f.keySet().toArray(new Integer[0]);
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
        return m(i2).f1071b.f1094b;
    }

    public int s(int i2) {
        return m(i2).f1071b.f1095c;
    }

    public int t(int i2) {
        return m(i2).f1073d.f1079d;
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
                        aVarL.f1073d.f1077b = true;
                    }
                    this.f1069f.put(Integer.valueOf(aVarL.f1070a), aVarL);
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.c.v(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public void y(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1068e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1069f.containsKey(Integer.valueOf(id))) {
                this.f1069f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f1069f.get(Integer.valueOf(id));
            if (!aVar.f1073d.f1078c) {
                aVar.f(id, bVar);
                if (childAt instanceof ConstraintHelper) {
                    aVar.f1073d.f0 = ((ConstraintHelper) childAt).getReferencedIds();
                    if (childAt instanceof Barrier) {
                        Barrier barrier = (Barrier) childAt;
                        aVar.f1073d.k0 = barrier.v();
                        aVar.f1073d.c0 = barrier.getType();
                        aVar.f1073d.d0 = barrier.getMargin();
                    }
                }
                aVar.f1073d.f1078c = true;
            }
            d dVar = aVar.f1071b;
            if (!dVar.f1093a) {
                dVar.f1094b = childAt.getVisibility();
                aVar.f1071b.f1096d = childAt.getAlpha();
                aVar.f1071b.f1093a = true;
            }
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 17) {
                e eVar = aVar.f1074e;
                if (!eVar.f1099b) {
                    eVar.f1099b = true;
                    eVar.f1100c = childAt.getRotation();
                    aVar.f1074e.f1101d = childAt.getRotationX();
                    aVar.f1074e.f1102e = childAt.getRotationY();
                    aVar.f1074e.f1103f = childAt.getScaleX();
                    aVar.f1074e.f1104g = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        e eVar2 = aVar.f1074e;
                        eVar2.f1105h = pivotX;
                        eVar2.f1106i = pivotY;
                    }
                    aVar.f1074e.j = childAt.getTranslationX();
                    aVar.f1074e.k = childAt.getTranslationY();
                    if (i3 >= 21) {
                        aVar.f1074e.l = childAt.getTranslationZ();
                        e eVar3 = aVar.f1074e;
                        if (eVar3.m) {
                            eVar3.n = childAt.getElevation();
                        }
                    }
                }
            }
        }
    }

    public void z(c cVar) {
        for (Integer num : cVar.f1069f.keySet()) {
            int iIntValue = num.intValue();
            a aVar = cVar.f1069f.get(num);
            if (!this.f1069f.containsKey(Integer.valueOf(iIntValue))) {
                this.f1069f.put(Integer.valueOf(iIntValue), new a());
            }
            a aVar2 = this.f1069f.get(Integer.valueOf(iIntValue));
            b bVar = aVar2.f1073d;
            if (!bVar.f1078c) {
                bVar.a(aVar.f1073d);
            }
            d dVar = aVar2.f1071b;
            if (!dVar.f1093a) {
                dVar.a(aVar.f1071b);
            }
            e eVar = aVar2.f1074e;
            if (!eVar.f1099b) {
                eVar.a(aVar.f1074e);
            }
            C0012c c0012c = aVar2.f1072c;
            if (!c0012c.f1086b) {
                c0012c.a(aVar.f1072c);
            }
            for (String str : aVar.f1075f.keySet()) {
                if (!aVar2.f1075f.containsKey(str)) {
                    aVar2.f1075f.put(str, aVar.f1075f.get(str));
                }
            }
        }
    }
}
