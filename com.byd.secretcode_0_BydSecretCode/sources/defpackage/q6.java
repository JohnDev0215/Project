package defpackage;

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
import androidx.constraintlayout.widget.R$id;
import androidx.constraintlayout.widget.R$styleable;
import com.bydux.api.common.tools.util.ListUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintSet.java */
/* JADX INFO: loaded from: classes.dex */
public class q6 {
    public static final int[] a = {0, 4, 8};
    public static SparseIntArray b;
    public boolean c;
    public HashMap<String, ConstraintAttribute> d = new HashMap<>();
    public boolean e = true;
    public HashMap<Integer, a> f = new HashMap<>();

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class a {
        public int a;
        public final d b = new d();
        public final c c = new c();
        public final b d = new b();
        public final e e = new e();
        public HashMap<String, ConstraintAttribute> f = new HashMap<>();

        public void d(ConstraintLayout.b bVar) {
            b bVar2 = this.d;
            bVar.d = bVar2.i;
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
            b bVar3 = this.d;
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
            bVar.c = bVar3.h;
            bVar.a = bVar3.f;
            bVar.b = bVar3.g;
            ((ViewGroup.MarginLayoutParams) bVar).width = bVar3.d;
            ((ViewGroup.MarginLayoutParams) bVar).height = bVar3.e;
            String str = bVar3.h0;
            if (str != null) {
                bVar.V = str;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                bVar.setMarginStart(this.d.J);
                bVar.setMarginEnd(this.d.I);
            }
            bVar.c();
        }

        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.d.a(this.d);
            aVar.c.a(this.c);
            aVar.b.a(this.b);
            aVar.e.a(this.e);
            aVar.a = this.a;
            return aVar;
        }

        public final void f(int i, ConstraintLayout.b bVar) {
            this.a = i;
            b bVar2 = this.d;
            bVar2.i = bVar.d;
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
            bVar2.h = bVar.c;
            bVar2.f = bVar.a;
            bVar2.g = bVar.b;
            b bVar3 = this.d;
            bVar3.d = ((ViewGroup.MarginLayoutParams) bVar).width;
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
            b bVar4 = this.d;
            bVar4.P = bVar.x;
            bVar4.O = bVar.y;
            if (Build.VERSION.SDK_INT >= 17) {
                bVar4.I = bVar.getMarginEnd();
                this.d.J = bVar.getMarginStart();
            }
        }

        public final void g(int i, Constraints.a aVar) {
            f(i, aVar);
            this.b.d = aVar.p0;
            e eVar = this.e;
            eVar.c = aVar.s0;
            eVar.d = aVar.t0;
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

        public final void h(ConstraintHelper constraintHelper, int i, Constraints.a aVar) {
            g(i, aVar);
            if (constraintHelper instanceof Barrier) {
                b bVar = this.d;
                bVar.e0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                bVar.c0 = barrier.getType();
                this.d.f0 = barrier.getReferencedIds();
                this.d.d0 = barrier.getMargin();
            }
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class b {
        public static SparseIntArray a;
        public int d;
        public int e;
        public int[] f0;
        public String g0;
        public String h0;
        public boolean b = false;
        public boolean c = false;
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
            a = sparseIntArray;
            sparseIntArray.append(R$styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            a.append(R$styleable.Layout_layout_constraintLeft_toRightOf, 25);
            a.append(R$styleable.Layout_layout_constraintRight_toLeftOf, 28);
            a.append(R$styleable.Layout_layout_constraintRight_toRightOf, 29);
            a.append(R$styleable.Layout_layout_constraintTop_toTopOf, 35);
            a.append(R$styleable.Layout_layout_constraintTop_toBottomOf, 34);
            a.append(R$styleable.Layout_layout_constraintBottom_toTopOf, 4);
            a.append(R$styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            a.append(R$styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            a.append(R$styleable.Layout_layout_editor_absoluteX, 6);
            a.append(R$styleable.Layout_layout_editor_absoluteY, 7);
            a.append(R$styleable.Layout_layout_constraintGuide_begin, 17);
            a.append(R$styleable.Layout_layout_constraintGuide_end, 18);
            a.append(R$styleable.Layout_layout_constraintGuide_percent, 19);
            a.append(R$styleable.Layout_android_orientation, 26);
            a.append(R$styleable.Layout_layout_constraintStart_toEndOf, 31);
            a.append(R$styleable.Layout_layout_constraintStart_toStartOf, 32);
            a.append(R$styleable.Layout_layout_constraintEnd_toStartOf, 10);
            a.append(R$styleable.Layout_layout_constraintEnd_toEndOf, 9);
            a.append(R$styleable.Layout_layout_goneMarginLeft, 13);
            a.append(R$styleable.Layout_layout_goneMarginTop, 16);
            a.append(R$styleable.Layout_layout_goneMarginRight, 14);
            a.append(R$styleable.Layout_layout_goneMarginBottom, 11);
            a.append(R$styleable.Layout_layout_goneMarginStart, 15);
            a.append(R$styleable.Layout_layout_goneMarginEnd, 12);
            a.append(R$styleable.Layout_layout_constraintVertical_weight, 38);
            a.append(R$styleable.Layout_layout_constraintHorizontal_weight, 37);
            a.append(R$styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            a.append(R$styleable.Layout_layout_constraintVertical_chainStyle, 40);
            a.append(R$styleable.Layout_layout_constraintHorizontal_bias, 20);
            a.append(R$styleable.Layout_layout_constraintVertical_bias, 36);
            a.append(R$styleable.Layout_layout_constraintDimensionRatio, 5);
            a.append(R$styleable.Layout_layout_constraintLeft_creator, 76);
            a.append(R$styleable.Layout_layout_constraintTop_creator, 76);
            a.append(R$styleable.Layout_layout_constraintRight_creator, 76);
            a.append(R$styleable.Layout_layout_constraintBottom_creator, 76);
            a.append(R$styleable.Layout_layout_constraintBaseline_creator, 76);
            a.append(R$styleable.Layout_android_layout_marginLeft, 23);
            a.append(R$styleable.Layout_android_layout_marginRight, 27);
            a.append(R$styleable.Layout_android_layout_marginStart, 30);
            a.append(R$styleable.Layout_android_layout_marginEnd, 8);
            a.append(R$styleable.Layout_android_layout_marginTop, 33);
            a.append(R$styleable.Layout_android_layout_marginBottom, 2);
            a.append(R$styleable.Layout_android_layout_width, 22);
            a.append(R$styleable.Layout_android_layout_height, 21);
            a.append(R$styleable.Layout_layout_constraintCircle, 61);
            a.append(R$styleable.Layout_layout_constraintCircleRadius, 62);
            a.append(R$styleable.Layout_layout_constraintCircleAngle, 63);
            a.append(R$styleable.Layout_layout_constraintWidth_percent, 69);
            a.append(R$styleable.Layout_layout_constraintHeight_percent, 70);
            a.append(R$styleable.Layout_chainUseRtl, 71);
            a.append(R$styleable.Layout_barrierDirection, 72);
            a.append(R$styleable.Layout_barrierMargin, 73);
            a.append(R$styleable.Layout_constraint_referenced_ids, 74);
            a.append(R$styleable.Layout_barrierAllowsGoneWidgets, 75);
        }

        public void a(b bVar) {
            this.b = bVar.b;
            this.d = bVar.d;
            this.c = bVar.c;
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

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Layout);
            this.c = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                int i2 = a.get(index);
                if (i2 == 80) {
                    this.i0 = typedArrayObtainStyledAttributes.getBoolean(index, this.i0);
                } else if (i2 != 81) {
                    switch (i2) {
                        case 1:
                            this.q = q6.w(typedArrayObtainStyledAttributes, index, this.q);
                            break;
                        case 2:
                            this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.H);
                            break;
                        case 3:
                            this.p = q6.w(typedArrayObtainStyledAttributes, index, this.p);
                            break;
                        case 4:
                            this.o = q6.w(typedArrayObtainStyledAttributes, index, this.o);
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
                            this.u = q6.w(typedArrayObtainStyledAttributes, index, this.u);
                            break;
                        case 10:
                            this.t = q6.w(typedArrayObtainStyledAttributes, index, this.t);
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
                            this.d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.d);
                            break;
                        case 23:
                            this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.E);
                            break;
                        case 24:
                            this.i = q6.w(typedArrayObtainStyledAttributes, index, this.i);
                            break;
                        case 25:
                            this.j = q6.w(typedArrayObtainStyledAttributes, index, this.j);
                            break;
                        case 26:
                            this.D = typedArrayObtainStyledAttributes.getInt(index, this.D);
                            break;
                        case 27:
                            this.F = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.F);
                            break;
                        case 28:
                            this.k = q6.w(typedArrayObtainStyledAttributes, index, this.k);
                            break;
                        case 29:
                            this.l = q6.w(typedArrayObtainStyledAttributes, index, this.l);
                            break;
                        case 30:
                            if (Build.VERSION.SDK_INT >= 17) {
                                this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                            }
                            break;
                        case 31:
                            this.r = q6.w(typedArrayObtainStyledAttributes, index, this.r);
                            break;
                        case 32:
                            this.s = q6.w(typedArrayObtainStyledAttributes, index, this.s);
                            break;
                        case 33:
                            this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.G);
                            break;
                        case 34:
                            this.n = q6.w(typedArrayObtainStyledAttributes, index, this.n);
                            break;
                        case 35:
                            this.m = q6.w(typedArrayObtainStyledAttributes, index, this.m);
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
                                            this.y = q6.w(typedArrayObtainStyledAttributes, index, this.y);
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
                                                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + a.get(index));
                                                    break;
                                                case 77:
                                                    this.h0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                default:
                                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + a.get(index));
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
        public static SparseIntArray a;
        public boolean b = false;
        public int c = -1;
        public String d = null;
        public int e = -1;
        public int f = 0;
        public float g = Float.NaN;
        public float h = Float.NaN;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R$styleable.Motion_motionPathRotate, 1);
            a.append(R$styleable.Motion_pathMotionArc, 2);
            a.append(R$styleable.Motion_transitionEasing, 3);
            a.append(R$styleable.Motion_drawPath, 4);
            a.append(R$styleable.Motion_animate_relativeTo, 5);
            a.append(R$styleable.Motion_motionStagger, 6);
        }

        public void a(c cVar) {
            this.b = cVar.b;
            this.c = cVar.c;
            this.d = cVar.d;
            this.e = cVar.e;
            this.f = cVar.f;
            this.h = cVar.h;
            this.g = cVar.g;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Motion);
            this.b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (a.get(index)) {
                    case 1:
                        this.h = typedArrayObtainStyledAttributes.getFloat(index, this.h);
                        break;
                    case 2:
                        this.e = typedArrayObtainStyledAttributes.getInt(index, this.e);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.d = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.d = k4.b[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.c = q6.w(typedArrayObtainStyledAttributes, index, this.c);
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
        public boolean a = false;
        public int b = 0;
        public int c = 0;
        public float d = 1.0f;
        public float e = Float.NaN;

        public void a(d dVar) {
            this.a = dVar.a;
            this.b = dVar.b;
            this.d = dVar.d;
            this.e = dVar.e;
            this.c = dVar.c;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PropertySet);
            this.a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.PropertySet_android_alpha) {
                    this.d = typedArrayObtainStyledAttributes.getFloat(index, this.d);
                } else if (index == R$styleable.PropertySet_android_visibility) {
                    this.b = typedArrayObtainStyledAttributes.getInt(index, this.b);
                    this.b = q6.a[this.b];
                } else if (index == R$styleable.PropertySet_visibilityMode) {
                    this.c = typedArrayObtainStyledAttributes.getInt(index, this.c);
                } else if (index == R$styleable.PropertySet_motionProgress) {
                    this.e = typedArrayObtainStyledAttributes.getFloat(index, this.e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class e {
        public static SparseIntArray a;
        public boolean b = false;
        public float c = 0.0f;
        public float d = 0.0f;
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
            a = sparseIntArray;
            sparseIntArray.append(R$styleable.Transform_android_rotation, 1);
            a.append(R$styleable.Transform_android_rotationX, 2);
            a.append(R$styleable.Transform_android_rotationY, 3);
            a.append(R$styleable.Transform_android_scaleX, 4);
            a.append(R$styleable.Transform_android_scaleY, 5);
            a.append(R$styleable.Transform_android_transformPivotX, 6);
            a.append(R$styleable.Transform_android_transformPivotY, 7);
            a.append(R$styleable.Transform_android_translationX, 8);
            a.append(R$styleable.Transform_android_translationY, 9);
            a.append(R$styleable.Transform_android_translationZ, 10);
            a.append(R$styleable.Transform_android_elevation, 11);
        }

        public void a(e eVar) {
            this.b = eVar.b;
            this.c = eVar.c;
            this.d = eVar.d;
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

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Transform);
            this.b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (a.get(index)) {
                    case 1:
                        this.c = typedArrayObtainStyledAttributes.getFloat(index, this.c);
                        break;
                    case 2:
                        this.d = typedArrayObtainStyledAttributes.getFloat(index, this.d);
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
        b = sparseIntArray;
        sparseIntArray.append(R$styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        b.append(R$styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        b.append(R$styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        b.append(R$styleable.Constraint_layout_constraintRight_toRightOf, 30);
        b.append(R$styleable.Constraint_layout_constraintTop_toTopOf, 36);
        b.append(R$styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        b.append(R$styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        b.append(R$styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        b.append(R$styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        b.append(R$styleable.Constraint_layout_editor_absoluteX, 6);
        b.append(R$styleable.Constraint_layout_editor_absoluteY, 7);
        b.append(R$styleable.Constraint_layout_constraintGuide_begin, 17);
        b.append(R$styleable.Constraint_layout_constraintGuide_end, 18);
        b.append(R$styleable.Constraint_layout_constraintGuide_percent, 19);
        b.append(R$styleable.Constraint_android_orientation, 27);
        b.append(R$styleable.Constraint_layout_constraintStart_toEndOf, 32);
        b.append(R$styleable.Constraint_layout_constraintStart_toStartOf, 33);
        b.append(R$styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        b.append(R$styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        b.append(R$styleable.Constraint_layout_goneMarginLeft, 13);
        b.append(R$styleable.Constraint_layout_goneMarginTop, 16);
        b.append(R$styleable.Constraint_layout_goneMarginRight, 14);
        b.append(R$styleable.Constraint_layout_goneMarginBottom, 11);
        b.append(R$styleable.Constraint_layout_goneMarginStart, 15);
        b.append(R$styleable.Constraint_layout_goneMarginEnd, 12);
        b.append(R$styleable.Constraint_layout_constraintVertical_weight, 40);
        b.append(R$styleable.Constraint_layout_constraintHorizontal_weight, 39);
        b.append(R$styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        b.append(R$styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        b.append(R$styleable.Constraint_layout_constraintHorizontal_bias, 20);
        b.append(R$styleable.Constraint_layout_constraintVertical_bias, 37);
        b.append(R$styleable.Constraint_layout_constraintDimensionRatio, 5);
        b.append(R$styleable.Constraint_layout_constraintLeft_creator, 82);
        b.append(R$styleable.Constraint_layout_constraintTop_creator, 82);
        b.append(R$styleable.Constraint_layout_constraintRight_creator, 82);
        b.append(R$styleable.Constraint_layout_constraintBottom_creator, 82);
        b.append(R$styleable.Constraint_layout_constraintBaseline_creator, 82);
        b.append(R$styleable.Constraint_android_layout_marginLeft, 24);
        b.append(R$styleable.Constraint_android_layout_marginRight, 28);
        b.append(R$styleable.Constraint_android_layout_marginStart, 31);
        b.append(R$styleable.Constraint_android_layout_marginEnd, 8);
        b.append(R$styleable.Constraint_android_layout_marginTop, 34);
        b.append(R$styleable.Constraint_android_layout_marginBottom, 2);
        b.append(R$styleable.Constraint_android_layout_width, 23);
        b.append(R$styleable.Constraint_android_layout_height, 21);
        b.append(R$styleable.Constraint_android_visibility, 22);
        b.append(R$styleable.Constraint_android_alpha, 43);
        b.append(R$styleable.Constraint_android_elevation, 44);
        b.append(R$styleable.Constraint_android_rotationX, 45);
        b.append(R$styleable.Constraint_android_rotationY, 46);
        b.append(R$styleable.Constraint_android_rotation, 60);
        b.append(R$styleable.Constraint_android_scaleX, 47);
        b.append(R$styleable.Constraint_android_scaleY, 48);
        b.append(R$styleable.Constraint_android_transformPivotX, 49);
        b.append(R$styleable.Constraint_android_transformPivotY, 50);
        b.append(R$styleable.Constraint_android_translationX, 51);
        b.append(R$styleable.Constraint_android_translationY, 52);
        b.append(R$styleable.Constraint_android_translationZ, 53);
        b.append(R$styleable.Constraint_layout_constraintWidth_default, 54);
        b.append(R$styleable.Constraint_layout_constraintHeight_default, 55);
        b.append(R$styleable.Constraint_layout_constraintWidth_max, 56);
        b.append(R$styleable.Constraint_layout_constraintHeight_max, 57);
        b.append(R$styleable.Constraint_layout_constraintWidth_min, 58);
        b.append(R$styleable.Constraint_layout_constraintHeight_min, 59);
        b.append(R$styleable.Constraint_layout_constraintCircle, 61);
        b.append(R$styleable.Constraint_layout_constraintCircleRadius, 62);
        b.append(R$styleable.Constraint_layout_constraintCircleAngle, 63);
        b.append(R$styleable.Constraint_animate_relativeTo, 64);
        b.append(R$styleable.Constraint_transitionEasing, 65);
        b.append(R$styleable.Constraint_drawPath, 66);
        b.append(R$styleable.Constraint_transitionPathRotate, 67);
        b.append(R$styleable.Constraint_motionStagger, 79);
        b.append(R$styleable.Constraint_android_id, 38);
        b.append(R$styleable.Constraint_motionProgress, 68);
        b.append(R$styleable.Constraint_layout_constraintWidth_percent, 69);
        b.append(R$styleable.Constraint_layout_constraintHeight_percent, 70);
        b.append(R$styleable.Constraint_chainUseRtl, 71);
        b.append(R$styleable.Constraint_barrierDirection, 72);
        b.append(R$styleable.Constraint_barrierMargin, 73);
        b.append(R$styleable.Constraint_constraint_referenced_ids, 74);
        b.append(R$styleable.Constraint_barrierAllowsGoneWidgets, 75);
        b.append(R$styleable.Constraint_pathMotionArc, 76);
        b.append(R$styleable.Constraint_layout_constraintTag, 77);
        b.append(R$styleable.Constraint_visibilityMode, 78);
        b.append(R$styleable.Constraint_layout_constrainedWidth, 80);
        b.append(R$styleable.Constraint_layout_constrainedHeight, 81);
    }

    public static int w(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    public void A(boolean z) {
        this.e = z;
    }

    public void B(boolean z) {
        this.c = z;
    }

    public void c(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.f.containsKey(Integer.valueOf(id))) {
                Log.v("ConstraintSet", "id unknown " + q4.c(childAt));
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
            if (constraintWidget instanceof a6) {
                constraintHelper.n(aVar, (a6) constraintWidget, bVar, sparseArray);
            }
        }
    }

    public void f(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.f.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.f.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + q4.c(childAt));
            } else {
                if (this.e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.f.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = this.f.get(Integer.valueOf(id));
                        if (childAt instanceof Barrier) {
                            aVar.d.e0 = 1;
                        }
                        int i2 = aVar.d.e0;
                        if (i2 != -1 && i2 == 1) {
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(aVar.d.c0);
                            barrier.setMargin(aVar.d.d0);
                            barrier.setAllowsGoneWidget(aVar.d.k0);
                            b bVar = aVar.d;
                            int[] iArr = bVar.f0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = bVar.g0;
                                if (str != null) {
                                    bVar.f0 = k(barrier, str);
                                    barrier.setReferencedIds(aVar.d.f0);
                                }
                            }
                        }
                        ConstraintLayout.b bVar2 = (ConstraintLayout.b) childAt.getLayoutParams();
                        bVar2.c();
                        aVar.d(bVar2);
                        if (z) {
                            ConstraintAttribute.h(childAt, aVar.f);
                        }
                        childAt.setLayoutParams(bVar2);
                        d dVar = aVar.b;
                        if (dVar.c == 0) {
                            childAt.setVisibility(dVar.b);
                        }
                        int i3 = Build.VERSION.SDK_INT;
                        if (i3 >= 17) {
                            childAt.setAlpha(aVar.b.d);
                            childAt.setRotation(aVar.e.c);
                            childAt.setRotationX(aVar.e.d);
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
            int i4 = aVar2.d.e0;
            if (i4 != -1 && i4 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                b bVar3 = aVar2.d;
                int[] iArr2 = bVar3.f0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = bVar3.g0;
                    if (str2 != null) {
                        bVar3.f0 = k(barrier2, str2);
                        barrier2.setReferencedIds(aVar2.d.f0);
                    }
                }
                barrier2.setType(aVar2.d.c0);
                barrier2.setMargin(aVar2.d.d0);
                ConstraintLayout.b bVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.u();
                aVar2.d(bVarGenerateDefaultLayoutParams);
                constraintLayout.addView(barrier2, bVarGenerateDefaultLayoutParams);
            }
            if (aVar2.d.b) {
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
            aVar.f = ConstraintAttribute.b(this.d, childAt);
            aVar.f(id, bVar);
            aVar.b.b = childAt.getVisibility();
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 17) {
                aVar.b.d = childAt.getAlpha();
                aVar.e.c = childAt.getRotation();
                aVar.e.d = childAt.getRotationX();
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
                aVar.d.k0 = barrier.v();
                aVar.d.f0 = barrier.getReferencedIds();
                aVar.d.c0 = barrier.getType();
                aVar.d.d0 = barrier.getMargin();
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

    public final int[] k(View view, String str) {
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
                iIntValue = R$id.class.getField(strTrim).getInt(null);
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

    public final a l(Context context, AttributeSet attributeSet) {
        a aVar = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Constraint);
        x(context, aVar, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    public final a m(int i) {
        if (!this.f.containsKey(Integer.valueOf(i))) {
            this.f.put(Integer.valueOf(i), new a());
        }
        return this.f.get(Integer.valueOf(i));
    }

    public a n(int i) {
        if (this.f.containsKey(Integer.valueOf(i))) {
            return this.f.get(Integer.valueOf(i));
        }
        return null;
    }

    public int o(int i) {
        return m(i).d.e;
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
        return m(i).b.b;
    }

    public int s(int i) {
        return m(i).b.c;
    }

    public int t(int i) {
        return m(i).d.d;
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
                        aVarL.d.b = true;
                    }
                    this.f.put(Integer.valueOf(aVarL.a), aVarL);
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.q6.v(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public final void x(Context context, a aVar, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index != R$styleable.Constraint_android_id && R$styleable.Constraint_android_layout_marginStart != index && R$styleable.Constraint_android_layout_marginEnd != index) {
                aVar.c.b = true;
                aVar.d.c = true;
                aVar.b.a = true;
                aVar.e.b = true;
            }
            switch (b.get(index)) {
                case 1:
                    b bVar = aVar.d;
                    bVar.q = w(typedArray, index, bVar.q);
                    break;
                case 2:
                    b bVar2 = aVar.d;
                    bVar2.H = typedArray.getDimensionPixelSize(index, bVar2.H);
                    break;
                case 3:
                    b bVar3 = aVar.d;
                    bVar3.p = w(typedArray, index, bVar3.p);
                    break;
                case 4:
                    b bVar4 = aVar.d;
                    bVar4.o = w(typedArray, index, bVar4.o);
                    break;
                case 5:
                    aVar.d.x = typedArray.getString(index);
                    break;
                case 6:
                    b bVar5 = aVar.d;
                    bVar5.B = typedArray.getDimensionPixelOffset(index, bVar5.B);
                    break;
                case 7:
                    b bVar6 = aVar.d;
                    bVar6.C = typedArray.getDimensionPixelOffset(index, bVar6.C);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        b bVar7 = aVar.d;
                        bVar7.I = typedArray.getDimensionPixelSize(index, bVar7.I);
                    }
                    break;
                case 9:
                    b bVar8 = aVar.d;
                    bVar8.u = w(typedArray, index, bVar8.u);
                    break;
                case 10:
                    b bVar9 = aVar.d;
                    bVar9.t = w(typedArray, index, bVar9.t);
                    break;
                case 11:
                    b bVar10 = aVar.d;
                    bVar10.N = typedArray.getDimensionPixelSize(index, bVar10.N);
                    break;
                case 12:
                    b bVar11 = aVar.d;
                    bVar11.O = typedArray.getDimensionPixelSize(index, bVar11.O);
                    break;
                case 13:
                    b bVar12 = aVar.d;
                    bVar12.K = typedArray.getDimensionPixelSize(index, bVar12.K);
                    break;
                case 14:
                    b bVar13 = aVar.d;
                    bVar13.M = typedArray.getDimensionPixelSize(index, bVar13.M);
                    break;
                case 15:
                    b bVar14 = aVar.d;
                    bVar14.P = typedArray.getDimensionPixelSize(index, bVar14.P);
                    break;
                case 16:
                    b bVar15 = aVar.d;
                    bVar15.L = typedArray.getDimensionPixelSize(index, bVar15.L);
                    break;
                case 17:
                    b bVar16 = aVar.d;
                    bVar16.f = typedArray.getDimensionPixelOffset(index, bVar16.f);
                    break;
                case 18:
                    b bVar17 = aVar.d;
                    bVar17.g = typedArray.getDimensionPixelOffset(index, bVar17.g);
                    break;
                case 19:
                    b bVar18 = aVar.d;
                    bVar18.h = typedArray.getFloat(index, bVar18.h);
                    break;
                case 20:
                    b bVar19 = aVar.d;
                    bVar19.v = typedArray.getFloat(index, bVar19.v);
                    break;
                case 21:
                    b bVar20 = aVar.d;
                    bVar20.e = typedArray.getLayoutDimension(index, bVar20.e);
                    break;
                case 22:
                    d dVar = aVar.b;
                    dVar.b = typedArray.getInt(index, dVar.b);
                    d dVar2 = aVar.b;
                    dVar2.b = a[dVar2.b];
                    break;
                case 23:
                    b bVar21 = aVar.d;
                    bVar21.d = typedArray.getLayoutDimension(index, bVar21.d);
                    break;
                case 24:
                    b bVar22 = aVar.d;
                    bVar22.E = typedArray.getDimensionPixelSize(index, bVar22.E);
                    break;
                case 25:
                    b bVar23 = aVar.d;
                    bVar23.i = w(typedArray, index, bVar23.i);
                    break;
                case 26:
                    b bVar24 = aVar.d;
                    bVar24.j = w(typedArray, index, bVar24.j);
                    break;
                case 27:
                    b bVar25 = aVar.d;
                    bVar25.D = typedArray.getInt(index, bVar25.D);
                    break;
                case 28:
                    b bVar26 = aVar.d;
                    bVar26.F = typedArray.getDimensionPixelSize(index, bVar26.F);
                    break;
                case 29:
                    b bVar27 = aVar.d;
                    bVar27.k = w(typedArray, index, bVar27.k);
                    break;
                case 30:
                    b bVar28 = aVar.d;
                    bVar28.l = w(typedArray, index, bVar28.l);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        b bVar29 = aVar.d;
                        bVar29.J = typedArray.getDimensionPixelSize(index, bVar29.J);
                    }
                    break;
                case 32:
                    b bVar30 = aVar.d;
                    bVar30.r = w(typedArray, index, bVar30.r);
                    break;
                case 33:
                    b bVar31 = aVar.d;
                    bVar31.s = w(typedArray, index, bVar31.s);
                    break;
                case 34:
                    b bVar32 = aVar.d;
                    bVar32.G = typedArray.getDimensionPixelSize(index, bVar32.G);
                    break;
                case 35:
                    b bVar33 = aVar.d;
                    bVar33.n = w(typedArray, index, bVar33.n);
                    break;
                case 36:
                    b bVar34 = aVar.d;
                    bVar34.m = w(typedArray, index, bVar34.m);
                    break;
                case 37:
                    b bVar35 = aVar.d;
                    bVar35.w = typedArray.getFloat(index, bVar35.w);
                    break;
                case 38:
                    aVar.a = typedArray.getResourceId(index, aVar.a);
                    break;
                case 39:
                    b bVar36 = aVar.d;
                    bVar36.R = typedArray.getFloat(index, bVar36.R);
                    break;
                case 40:
                    b bVar37 = aVar.d;
                    bVar37.Q = typedArray.getFloat(index, bVar37.Q);
                    break;
                case 41:
                    b bVar38 = aVar.d;
                    bVar38.S = typedArray.getInt(index, bVar38.S);
                    break;
                case 42:
                    b bVar39 = aVar.d;
                    bVar39.T = typedArray.getInt(index, bVar39.T);
                    break;
                case 43:
                    d dVar3 = aVar.b;
                    dVar3.d = typedArray.getFloat(index, dVar3.d);
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
                    eVar2.d = typedArray.getFloat(index, eVar2.d);
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
                    b bVar40 = aVar.d;
                    bVar40.U = typedArray.getInt(index, bVar40.U);
                    break;
                case 55:
                    b bVar41 = aVar.d;
                    bVar41.V = typedArray.getInt(index, bVar41.V);
                    break;
                case 56:
                    b bVar42 = aVar.d;
                    bVar42.W = typedArray.getDimensionPixelSize(index, bVar42.W);
                    break;
                case 57:
                    b bVar43 = aVar.d;
                    bVar43.X = typedArray.getDimensionPixelSize(index, bVar43.X);
                    break;
                case 58:
                    b bVar44 = aVar.d;
                    bVar44.Y = typedArray.getDimensionPixelSize(index, bVar44.Y);
                    break;
                case 59:
                    b bVar45 = aVar.d;
                    bVar45.Z = typedArray.getDimensionPixelSize(index, bVar45.Z);
                    break;
                case 60:
                    e eVar11 = aVar.e;
                    eVar11.c = typedArray.getFloat(index, eVar11.c);
                    break;
                case 61:
                    b bVar46 = aVar.d;
                    bVar46.y = w(typedArray, index, bVar46.y);
                    break;
                case 62:
                    b bVar47 = aVar.d;
                    bVar47.z = typedArray.getDimensionPixelSize(index, bVar47.z);
                    break;
                case 63:
                    b bVar48 = aVar.d;
                    bVar48.A = typedArray.getFloat(index, bVar48.A);
                    break;
                case 64:
                    c cVar = aVar.c;
                    cVar.c = w(typedArray, index, cVar.c);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.c.d = typedArray.getString(index);
                    } else {
                        aVar.c.d = k4.b[typedArray.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    aVar.c.f = typedArray.getInt(index, 0);
                    break;
                case 67:
                    c cVar2 = aVar.c;
                    cVar2.h = typedArray.getFloat(index, cVar2.h);
                    break;
                case 68:
                    d dVar4 = aVar.b;
                    dVar4.e = typedArray.getFloat(index, dVar4.e);
                    break;
                case 69:
                    aVar.d.a0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    aVar.d.b0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    b bVar49 = aVar.d;
                    bVar49.c0 = typedArray.getInt(index, bVar49.c0);
                    break;
                case 73:
                    b bVar50 = aVar.d;
                    bVar50.d0 = typedArray.getDimensionPixelSize(index, bVar50.d0);
                    break;
                case 74:
                    aVar.d.g0 = typedArray.getString(index);
                    break;
                case 75:
                    b bVar51 = aVar.d;
                    bVar51.k0 = typedArray.getBoolean(index, bVar51.k0);
                    break;
                case 76:
                    c cVar3 = aVar.c;
                    cVar3.e = typedArray.getInt(index, cVar3.e);
                    break;
                case 77:
                    aVar.d.h0 = typedArray.getString(index);
                    break;
                case 78:
                    d dVar5 = aVar.b;
                    dVar5.c = typedArray.getInt(index, dVar5.c);
                    break;
                case 79:
                    c cVar4 = aVar.c;
                    cVar4.g = typedArray.getFloat(index, cVar4.g);
                    break;
                case 80:
                    b bVar52 = aVar.d;
                    bVar52.i0 = typedArray.getBoolean(index, bVar52.i0);
                    break;
                case 81:
                    b bVar53 = aVar.d;
                    bVar53.j0 = typedArray.getBoolean(index, bVar53.j0);
                    break;
                case 82:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + b.get(index));
                    break;
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + b.get(index));
                    break;
            }
        }
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
            if (!aVar.d.c) {
                aVar.f(id, bVar);
                if (childAt instanceof ConstraintHelper) {
                    aVar.d.f0 = ((ConstraintHelper) childAt).getReferencedIds();
                    if (childAt instanceof Barrier) {
                        Barrier barrier = (Barrier) childAt;
                        aVar.d.k0 = barrier.v();
                        aVar.d.c0 = barrier.getType();
                        aVar.d.d0 = barrier.getMargin();
                    }
                }
                aVar.d.c = true;
            }
            d dVar = aVar.b;
            if (!dVar.a) {
                dVar.b = childAt.getVisibility();
                aVar.b.d = childAt.getAlpha();
                aVar.b.a = true;
            }
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 17) {
                e eVar = aVar.e;
                if (!eVar.b) {
                    eVar.b = true;
                    eVar.c = childAt.getRotation();
                    aVar.e.d = childAt.getRotationX();
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

    public void z(q6 q6Var) {
        for (Integer num : q6Var.f.keySet()) {
            int iIntValue = num.intValue();
            a aVar = q6Var.f.get(num);
            if (!this.f.containsKey(Integer.valueOf(iIntValue))) {
                this.f.put(Integer.valueOf(iIntValue), new a());
            }
            a aVar2 = this.f.get(Integer.valueOf(iIntValue));
            b bVar = aVar2.d;
            if (!bVar.c) {
                bVar.a(aVar.d);
            }
            d dVar = aVar2.b;
            if (!dVar.a) {
                dVar.a(aVar.b);
            }
            e eVar = aVar2.e;
            if (!eVar.b) {
                eVar.a(aVar.e);
            }
            c cVar = aVar2.c;
            if (!cVar.b) {
                cVar.a(aVar.c);
            }
            for (String str : aVar.f.keySet()) {
                if (!aVar2.f.containsKey(str)) {
                    aVar2.f.put(str, aVar.f.get(str));
                }
            }
        }
    }
}
