package b0;

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
import z.h;

/* JADX INFO: compiled from: ConstraintSet.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f1936a = {0, 4, 8};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static SparseIntArray f1937b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1938c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public HashMap<String, ConstraintAttribute> f1939d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1940e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public HashMap<Integer, a> f1941f = new HashMap<>();

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1942a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f1943b = new d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c f1944c = new c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final C0006b f1945d = new C0006b();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final e f1946e = new e();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public HashMap<String, ConstraintAttribute> f1947f = new HashMap<>();

        public void d(ConstraintLayout.b bVar) {
            C0006b c0006b = this.f1945d;
            bVar.f1099d = c0006b.f1964i;
            bVar.f1101e = c0006b.f1966j;
            bVar.f1103f = c0006b.f1968k;
            bVar.f1105g = c0006b.f1970l;
            bVar.f1107h = c0006b.f1971m;
            bVar.f1109i = c0006b.f1972n;
            bVar.f1111j = c0006b.f1973o;
            bVar.f1113k = c0006b.f1974p;
            bVar.f1115l = c0006b.f1975q;
            bVar.f1123p = c0006b.f1976r;
            bVar.f1124q = c0006b.f1977s;
            bVar.f1125r = c0006b.f1978t;
            bVar.f1126s = c0006b.f1979u;
            ((ViewGroup.MarginLayoutParams) bVar).leftMargin = c0006b.E;
            ((ViewGroup.MarginLayoutParams) bVar).rightMargin = c0006b.F;
            ((ViewGroup.MarginLayoutParams) bVar).topMargin = c0006b.G;
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = c0006b.H;
            bVar.f1131x = c0006b.P;
            bVar.f1132y = c0006b.O;
            bVar.f1128u = c0006b.L;
            bVar.f1130w = c0006b.N;
            bVar.f1133z = c0006b.f1980v;
            bVar.A = c0006b.f1981w;
            bVar.f1117m = c0006b.f1983y;
            bVar.f1119n = c0006b.f1984z;
            C0006b c0006b2 = this.f1945d;
            bVar.f1121o = c0006b2.A;
            bVar.B = c0006b2.f1982x;
            bVar.Q = c0006b2.B;
            bVar.R = c0006b2.C;
            bVar.F = c0006b2.Q;
            bVar.E = c0006b2.R;
            bVar.H = c0006b2.T;
            bVar.G = c0006b2.S;
            bVar.T = c0006b2.f1965i0;
            bVar.U = c0006b2.f1967j0;
            bVar.I = c0006b2.U;
            bVar.J = c0006b2.V;
            bVar.M = c0006b2.W;
            bVar.N = c0006b2.X;
            bVar.K = c0006b2.Y;
            bVar.L = c0006b2.Z;
            bVar.O = c0006b2.f1949a0;
            bVar.P = c0006b2.f1951b0;
            bVar.S = c0006b2.D;
            bVar.f1097c = c0006b2.f1962h;
            bVar.f1093a = c0006b2.f1958f;
            bVar.f1095b = c0006b2.f1960g;
            ((ViewGroup.MarginLayoutParams) bVar).width = c0006b2.f1954d;
            ((ViewGroup.MarginLayoutParams) bVar).height = c0006b2.f1956e;
            String str = c0006b2.f1963h0;
            if (str != null) {
                bVar.V = str;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                bVar.setMarginStart(this.f1945d.J);
                bVar.setMarginEnd(this.f1945d.I);
            }
            bVar.c();
        }

        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.f1945d.a(this.f1945d);
            aVar.f1944c.a(this.f1944c);
            aVar.f1943b.a(this.f1943b);
            aVar.f1946e.a(this.f1946e);
            aVar.f1942a = this.f1942a;
            return aVar;
        }

        public final void f(int i8, ConstraintLayout.b bVar) {
            this.f1942a = i8;
            C0006b c0006b = this.f1945d;
            c0006b.f1964i = bVar.f1099d;
            c0006b.f1966j = bVar.f1101e;
            c0006b.f1968k = bVar.f1103f;
            c0006b.f1970l = bVar.f1105g;
            c0006b.f1971m = bVar.f1107h;
            c0006b.f1972n = bVar.f1109i;
            c0006b.f1973o = bVar.f1111j;
            c0006b.f1974p = bVar.f1113k;
            c0006b.f1975q = bVar.f1115l;
            c0006b.f1976r = bVar.f1123p;
            c0006b.f1977s = bVar.f1124q;
            c0006b.f1978t = bVar.f1125r;
            c0006b.f1979u = bVar.f1126s;
            c0006b.f1980v = bVar.f1133z;
            c0006b.f1981w = bVar.A;
            c0006b.f1982x = bVar.B;
            c0006b.f1983y = bVar.f1117m;
            c0006b.f1984z = bVar.f1119n;
            c0006b.A = bVar.f1121o;
            c0006b.B = bVar.Q;
            c0006b.C = bVar.R;
            c0006b.D = bVar.S;
            c0006b.f1962h = bVar.f1097c;
            c0006b.f1958f = bVar.f1093a;
            c0006b.f1960g = bVar.f1095b;
            C0006b c0006b2 = this.f1945d;
            c0006b2.f1954d = ((ViewGroup.MarginLayoutParams) bVar).width;
            c0006b2.f1956e = ((ViewGroup.MarginLayoutParams) bVar).height;
            c0006b2.E = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            c0006b2.F = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
            c0006b2.G = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            c0006b2.H = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
            c0006b2.Q = bVar.F;
            c0006b2.R = bVar.E;
            c0006b2.T = bVar.H;
            c0006b2.S = bVar.G;
            c0006b2.f1965i0 = bVar.T;
            c0006b2.f1967j0 = bVar.U;
            c0006b2.U = bVar.I;
            c0006b2.V = bVar.J;
            c0006b2.W = bVar.M;
            c0006b2.X = bVar.N;
            c0006b2.Y = bVar.K;
            c0006b2.Z = bVar.L;
            c0006b2.f1949a0 = bVar.O;
            c0006b2.f1951b0 = bVar.P;
            c0006b2.f1963h0 = bVar.V;
            c0006b2.L = bVar.f1128u;
            c0006b2.N = bVar.f1130w;
            c0006b2.K = bVar.f1127t;
            c0006b2.M = bVar.f1129v;
            C0006b c0006b3 = this.f1945d;
            c0006b3.P = bVar.f1131x;
            c0006b3.O = bVar.f1132y;
            if (Build.VERSION.SDK_INT >= 17) {
                c0006b3.I = bVar.getMarginEnd();
                this.f1945d.J = bVar.getMarginStart();
            }
        }

        public final void g(int i8, Constraints.a aVar) {
            f(i8, aVar);
            this.f1943b.f1996d = aVar.f1144p0;
            e eVar = this.f1946e;
            eVar.f2000c = aVar.f1147s0;
            eVar.f2001d = aVar.f1148t0;
            eVar.f2002e = aVar.f1149u0;
            eVar.f2003f = aVar.f1150v0;
            eVar.f2004g = aVar.f1151w0;
            eVar.f2005h = aVar.f1152x0;
            eVar.f2006i = aVar.f1153y0;
            eVar.f2007j = aVar.f1154z0;
            eVar.f2008k = aVar.A0;
            eVar.f2009l = aVar.B0;
            eVar.f2011n = aVar.f1146r0;
            eVar.f2010m = aVar.f1145q0;
        }

        public final void h(ConstraintHelper constraintHelper, int i8, Constraints.a aVar) {
            g(i8, aVar);
            if (constraintHelper instanceof Barrier) {
                C0006b c0006b = this.f1945d;
                c0006b.f1957e0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                c0006b.f1953c0 = barrier.getType();
                this.f1945d.f1959f0 = barrier.getReferencedIds();
                this.f1945d.f1955d0 = barrier.getMargin();
            }
        }
    }

    /* JADX INFO: renamed from: b0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class C0006b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f1948a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1954d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1956e;

        /* JADX INFO: renamed from: f0, reason: collision with root package name */
        public int[] f1959f0;

        /* JADX INFO: renamed from: g0, reason: collision with root package name */
        public String f1961g0;

        /* JADX INFO: renamed from: h0, reason: collision with root package name */
        public String f1963h0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1950b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1952c = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1958f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1960g = -1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1962h = -1.0f;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1964i = -1;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f1966j = -1;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f1968k = -1;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f1970l = -1;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f1971m = -1;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f1972n = -1;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f1973o = -1;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f1974p = -1;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f1975q = -1;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f1976r = -1;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f1977s = -1;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f1978t = -1;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public int f1979u = -1;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public float f1980v = 0.5f;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public float f1981w = 0.5f;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public String f1982x = null;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public int f1983y = -1;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public int f1984z = 0;
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

        /* JADX INFO: renamed from: a0, reason: collision with root package name */
        public float f1949a0 = 1.0f;

        /* JADX INFO: renamed from: b0, reason: collision with root package name */
        public float f1951b0 = 1.0f;

        /* JADX INFO: renamed from: c0, reason: collision with root package name */
        public int f1953c0 = -1;

        /* JADX INFO: renamed from: d0, reason: collision with root package name */
        public int f1955d0 = 0;

        /* JADX INFO: renamed from: e0, reason: collision with root package name */
        public int f1957e0 = -1;

        /* JADX INFO: renamed from: i0, reason: collision with root package name */
        public boolean f1965i0 = false;

        /* JADX INFO: renamed from: j0, reason: collision with root package name */
        public boolean f1967j0 = false;

        /* JADX INFO: renamed from: k0, reason: collision with root package name */
        public boolean f1969k0 = true;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1948a = sparseIntArray;
            sparseIntArray.append(R$styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            f1948a.append(R$styleable.Layout_layout_constraintLeft_toRightOf, 25);
            f1948a.append(R$styleable.Layout_layout_constraintRight_toLeftOf, 28);
            f1948a.append(R$styleable.Layout_layout_constraintRight_toRightOf, 29);
            f1948a.append(R$styleable.Layout_layout_constraintTop_toTopOf, 35);
            f1948a.append(R$styleable.Layout_layout_constraintTop_toBottomOf, 34);
            f1948a.append(R$styleable.Layout_layout_constraintBottom_toTopOf, 4);
            f1948a.append(R$styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            f1948a.append(R$styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            f1948a.append(R$styleable.Layout_layout_editor_absoluteX, 6);
            f1948a.append(R$styleable.Layout_layout_editor_absoluteY, 7);
            f1948a.append(R$styleable.Layout_layout_constraintGuide_begin, 17);
            f1948a.append(R$styleable.Layout_layout_constraintGuide_end, 18);
            f1948a.append(R$styleable.Layout_layout_constraintGuide_percent, 19);
            f1948a.append(R$styleable.Layout_android_orientation, 26);
            f1948a.append(R$styleable.Layout_layout_constraintStart_toEndOf, 31);
            f1948a.append(R$styleable.Layout_layout_constraintStart_toStartOf, 32);
            f1948a.append(R$styleable.Layout_layout_constraintEnd_toStartOf, 10);
            f1948a.append(R$styleable.Layout_layout_constraintEnd_toEndOf, 9);
            f1948a.append(R$styleable.Layout_layout_goneMarginLeft, 13);
            f1948a.append(R$styleable.Layout_layout_goneMarginTop, 16);
            f1948a.append(R$styleable.Layout_layout_goneMarginRight, 14);
            f1948a.append(R$styleable.Layout_layout_goneMarginBottom, 11);
            f1948a.append(R$styleable.Layout_layout_goneMarginStart, 15);
            f1948a.append(R$styleable.Layout_layout_goneMarginEnd, 12);
            f1948a.append(R$styleable.Layout_layout_constraintVertical_weight, 38);
            f1948a.append(R$styleable.Layout_layout_constraintHorizontal_weight, 37);
            f1948a.append(R$styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            f1948a.append(R$styleable.Layout_layout_constraintVertical_chainStyle, 40);
            f1948a.append(R$styleable.Layout_layout_constraintHorizontal_bias, 20);
            f1948a.append(R$styleable.Layout_layout_constraintVertical_bias, 36);
            f1948a.append(R$styleable.Layout_layout_constraintDimensionRatio, 5);
            f1948a.append(R$styleable.Layout_layout_constraintLeft_creator, 76);
            f1948a.append(R$styleable.Layout_layout_constraintTop_creator, 76);
            f1948a.append(R$styleable.Layout_layout_constraintRight_creator, 76);
            f1948a.append(R$styleable.Layout_layout_constraintBottom_creator, 76);
            f1948a.append(R$styleable.Layout_layout_constraintBaseline_creator, 76);
            f1948a.append(R$styleable.Layout_android_layout_marginLeft, 23);
            f1948a.append(R$styleable.Layout_android_layout_marginRight, 27);
            f1948a.append(R$styleable.Layout_android_layout_marginStart, 30);
            f1948a.append(R$styleable.Layout_android_layout_marginEnd, 8);
            f1948a.append(R$styleable.Layout_android_layout_marginTop, 33);
            f1948a.append(R$styleable.Layout_android_layout_marginBottom, 2);
            f1948a.append(R$styleable.Layout_android_layout_width, 22);
            f1948a.append(R$styleable.Layout_android_layout_height, 21);
            f1948a.append(R$styleable.Layout_layout_constraintCircle, 61);
            f1948a.append(R$styleable.Layout_layout_constraintCircleRadius, 62);
            f1948a.append(R$styleable.Layout_layout_constraintCircleAngle, 63);
            f1948a.append(R$styleable.Layout_layout_constraintWidth_percent, 69);
            f1948a.append(R$styleable.Layout_layout_constraintHeight_percent, 70);
            f1948a.append(R$styleable.Layout_chainUseRtl, 71);
            f1948a.append(R$styleable.Layout_barrierDirection, 72);
            f1948a.append(R$styleable.Layout_barrierMargin, 73);
            f1948a.append(R$styleable.Layout_constraint_referenced_ids, 74);
            f1948a.append(R$styleable.Layout_barrierAllowsGoneWidgets, 75);
        }

        public void a(C0006b c0006b) {
            this.f1950b = c0006b.f1950b;
            this.f1954d = c0006b.f1954d;
            this.f1952c = c0006b.f1952c;
            this.f1956e = c0006b.f1956e;
            this.f1958f = c0006b.f1958f;
            this.f1960g = c0006b.f1960g;
            this.f1962h = c0006b.f1962h;
            this.f1964i = c0006b.f1964i;
            this.f1966j = c0006b.f1966j;
            this.f1968k = c0006b.f1968k;
            this.f1970l = c0006b.f1970l;
            this.f1971m = c0006b.f1971m;
            this.f1972n = c0006b.f1972n;
            this.f1973o = c0006b.f1973o;
            this.f1974p = c0006b.f1974p;
            this.f1975q = c0006b.f1975q;
            this.f1976r = c0006b.f1976r;
            this.f1977s = c0006b.f1977s;
            this.f1978t = c0006b.f1978t;
            this.f1979u = c0006b.f1979u;
            this.f1980v = c0006b.f1980v;
            this.f1981w = c0006b.f1981w;
            this.f1982x = c0006b.f1982x;
            this.f1983y = c0006b.f1983y;
            this.f1984z = c0006b.f1984z;
            this.A = c0006b.A;
            this.B = c0006b.B;
            this.C = c0006b.C;
            this.D = c0006b.D;
            this.E = c0006b.E;
            this.F = c0006b.F;
            this.G = c0006b.G;
            this.H = c0006b.H;
            this.I = c0006b.I;
            this.J = c0006b.J;
            this.K = c0006b.K;
            this.L = c0006b.L;
            this.M = c0006b.M;
            this.N = c0006b.N;
            this.O = c0006b.O;
            this.P = c0006b.P;
            this.Q = c0006b.Q;
            this.R = c0006b.R;
            this.S = c0006b.S;
            this.T = c0006b.T;
            this.U = c0006b.U;
            this.V = c0006b.V;
            this.W = c0006b.W;
            this.X = c0006b.X;
            this.Y = c0006b.Y;
            this.Z = c0006b.Z;
            this.f1949a0 = c0006b.f1949a0;
            this.f1951b0 = c0006b.f1951b0;
            this.f1953c0 = c0006b.f1953c0;
            this.f1955d0 = c0006b.f1955d0;
            this.f1957e0 = c0006b.f1957e0;
            this.f1963h0 = c0006b.f1963h0;
            int[] iArr = c0006b.f1959f0;
            if (iArr != null) {
                this.f1959f0 = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.f1959f0 = null;
            }
            this.f1961g0 = c0006b.f1961g0;
            this.f1965i0 = c0006b.f1965i0;
            this.f1967j0 = c0006b.f1967j0;
            this.f1969k0 = c0006b.f1969k0;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Layout);
            this.f1952c = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                int i9 = f1948a.get(index);
                if (i9 == 80) {
                    this.f1965i0 = typedArrayObtainStyledAttributes.getBoolean(index, this.f1965i0);
                } else if (i9 != 81) {
                    switch (i9) {
                        case 1:
                            this.f1975q = b.w(typedArrayObtainStyledAttributes, index, this.f1975q);
                            break;
                        case 2:
                            this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.H);
                            break;
                        case 3:
                            this.f1974p = b.w(typedArrayObtainStyledAttributes, index, this.f1974p);
                            break;
                        case 4:
                            this.f1973o = b.w(typedArrayObtainStyledAttributes, index, this.f1973o);
                            break;
                        case 5:
                            this.f1982x = typedArrayObtainStyledAttributes.getString(index);
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
                            this.f1979u = b.w(typedArrayObtainStyledAttributes, index, this.f1979u);
                            break;
                        case 10:
                            this.f1978t = b.w(typedArrayObtainStyledAttributes, index, this.f1978t);
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
                            this.f1958f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1958f);
                            break;
                        case 18:
                            this.f1960g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1960g);
                            break;
                        case 19:
                            this.f1962h = typedArrayObtainStyledAttributes.getFloat(index, this.f1962h);
                            break;
                        case 20:
                            this.f1980v = typedArrayObtainStyledAttributes.getFloat(index, this.f1980v);
                            break;
                        case 21:
                            this.f1956e = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f1956e);
                            break;
                        case 22:
                            this.f1954d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f1954d);
                            break;
                        case 23:
                            this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.E);
                            break;
                        case 24:
                            this.f1964i = b.w(typedArrayObtainStyledAttributes, index, this.f1964i);
                            break;
                        case 25:
                            this.f1966j = b.w(typedArrayObtainStyledAttributes, index, this.f1966j);
                            break;
                        case 26:
                            this.D = typedArrayObtainStyledAttributes.getInt(index, this.D);
                            break;
                        case 27:
                            this.F = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.F);
                            break;
                        case 28:
                            this.f1968k = b.w(typedArrayObtainStyledAttributes, index, this.f1968k);
                            break;
                        case 29:
                            this.f1970l = b.w(typedArrayObtainStyledAttributes, index, this.f1970l);
                            break;
                        case 30:
                            if (Build.VERSION.SDK_INT >= 17) {
                                this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                            }
                            break;
                        case 31:
                            this.f1976r = b.w(typedArrayObtainStyledAttributes, index, this.f1976r);
                            break;
                        case 32:
                            this.f1977s = b.w(typedArrayObtainStyledAttributes, index, this.f1977s);
                            break;
                        case 33:
                            this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.G);
                            break;
                        case 34:
                            this.f1972n = b.w(typedArrayObtainStyledAttributes, index, this.f1972n);
                            break;
                        case 35:
                            this.f1971m = b.w(typedArrayObtainStyledAttributes, index, this.f1971m);
                            break;
                        case 36:
                            this.f1981w = typedArrayObtainStyledAttributes.getFloat(index, this.f1981w);
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
                            switch (i9) {
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
                                    switch (i9) {
                                        case 61:
                                            this.f1983y = b.w(typedArrayObtainStyledAttributes, index, this.f1983y);
                                            break;
                                        case 62:
                                            this.f1984z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1984z);
                                            break;
                                        case 63:
                                            this.A = typedArrayObtainStyledAttributes.getFloat(index, this.A);
                                            break;
                                        default:
                                            switch (i9) {
                                                case 69:
                                                    this.f1949a0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 70:
                                                    this.f1951b0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 71:
                                                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                                    break;
                                                case 72:
                                                    this.f1953c0 = typedArrayObtainStyledAttributes.getInt(index, this.f1953c0);
                                                    break;
                                                case 73:
                                                    this.f1955d0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1955d0);
                                                    break;
                                                case 74:
                                                    this.f1961g0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                case 75:
                                                    this.f1969k0 = typedArrayObtainStyledAttributes.getBoolean(index, this.f1969k0);
                                                    break;
                                                case 76:
                                                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1948a.get(index));
                                                    break;
                                                case 77:
                                                    this.f1963h0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                default:
                                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1948a.get(index));
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } else {
                    this.f1967j0 = typedArrayObtainStyledAttributes.getBoolean(index, this.f1967j0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f1985a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1986b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1987c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f1988d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1989e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1990f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f1991g = Float.NaN;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1992h = Float.NaN;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1985a = sparseIntArray;
            sparseIntArray.append(R$styleable.Motion_motionPathRotate, 1);
            f1985a.append(R$styleable.Motion_pathMotionArc, 2);
            f1985a.append(R$styleable.Motion_transitionEasing, 3);
            f1985a.append(R$styleable.Motion_drawPath, 4);
            f1985a.append(R$styleable.Motion_animate_relativeTo, 5);
            f1985a.append(R$styleable.Motion_motionStagger, 6);
        }

        public void a(c cVar) {
            this.f1986b = cVar.f1986b;
            this.f1987c = cVar.f1987c;
            this.f1988d = cVar.f1988d;
            this.f1989e = cVar.f1989e;
            this.f1990f = cVar.f1990f;
            this.f1992h = cVar.f1992h;
            this.f1991g = cVar.f1991g;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Motion);
            this.f1986b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                switch (f1985a.get(index)) {
                    case 1:
                        this.f1992h = typedArrayObtainStyledAttributes.getFloat(index, this.f1992h);
                        break;
                    case 2:
                        this.f1989e = typedArrayObtainStyledAttributes.getInt(index, this.f1989e);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.f1988d = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.f1988d = w.c.f8625b[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.f1990f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.f1987c = b.w(typedArrayObtainStyledAttributes, index, this.f1987c);
                        break;
                    case 6:
                        this.f1991g = typedArrayObtainStyledAttributes.getFloat(index, this.f1991g);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1993a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1994b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1995c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1996d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1997e = Float.NaN;

        public void a(d dVar) {
            this.f1993a = dVar.f1993a;
            this.f1994b = dVar.f1994b;
            this.f1996d = dVar.f1996d;
            this.f1997e = dVar.f1997e;
            this.f1995c = dVar.f1995c;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PropertySet);
            this.f1993a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.PropertySet_android_alpha) {
                    this.f1996d = typedArrayObtainStyledAttributes.getFloat(index, this.f1996d);
                } else if (index == R$styleable.PropertySet_android_visibility) {
                    this.f1994b = typedArrayObtainStyledAttributes.getInt(index, this.f1994b);
                    this.f1994b = b.f1936a[this.f1994b];
                } else if (index == R$styleable.PropertySet_visibilityMode) {
                    this.f1995c = typedArrayObtainStyledAttributes.getInt(index, this.f1995c);
                } else if (index == R$styleable.PropertySet_motionProgress) {
                    this.f1997e = typedArrayObtainStyledAttributes.getFloat(index, this.f1997e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f1998a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1999b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f2000c = 0.0f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f2001d = 0.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f2002e = 0.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f2003f = 1.0f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f2004g = 1.0f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f2005h = Float.NaN;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f2006i = Float.NaN;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f2007j = 0.0f;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f2008k = 0.0f;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f2009l = 0.0f;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f2010m = false;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public float f2011n = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1998a = sparseIntArray;
            sparseIntArray.append(R$styleable.Transform_android_rotation, 1);
            f1998a.append(R$styleable.Transform_android_rotationX, 2);
            f1998a.append(R$styleable.Transform_android_rotationY, 3);
            f1998a.append(R$styleable.Transform_android_scaleX, 4);
            f1998a.append(R$styleable.Transform_android_scaleY, 5);
            f1998a.append(R$styleable.Transform_android_transformPivotX, 6);
            f1998a.append(R$styleable.Transform_android_transformPivotY, 7);
            f1998a.append(R$styleable.Transform_android_translationX, 8);
            f1998a.append(R$styleable.Transform_android_translationY, 9);
            f1998a.append(R$styleable.Transform_android_translationZ, 10);
            f1998a.append(R$styleable.Transform_android_elevation, 11);
        }

        public void a(e eVar) {
            this.f1999b = eVar.f1999b;
            this.f2000c = eVar.f2000c;
            this.f2001d = eVar.f2001d;
            this.f2002e = eVar.f2002e;
            this.f2003f = eVar.f2003f;
            this.f2004g = eVar.f2004g;
            this.f2005h = eVar.f2005h;
            this.f2006i = eVar.f2006i;
            this.f2007j = eVar.f2007j;
            this.f2008k = eVar.f2008k;
            this.f2009l = eVar.f2009l;
            this.f2010m = eVar.f2010m;
            this.f2011n = eVar.f2011n;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Transform);
            this.f1999b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                switch (f1998a.get(index)) {
                    case 1:
                        this.f2000c = typedArrayObtainStyledAttributes.getFloat(index, this.f2000c);
                        break;
                    case 2:
                        this.f2001d = typedArrayObtainStyledAttributes.getFloat(index, this.f2001d);
                        break;
                    case 3:
                        this.f2002e = typedArrayObtainStyledAttributes.getFloat(index, this.f2002e);
                        break;
                    case 4:
                        this.f2003f = typedArrayObtainStyledAttributes.getFloat(index, this.f2003f);
                        break;
                    case 5:
                        this.f2004g = typedArrayObtainStyledAttributes.getFloat(index, this.f2004g);
                        break;
                    case 6:
                        this.f2005h = typedArrayObtainStyledAttributes.getDimension(index, this.f2005h);
                        break;
                    case 7:
                        this.f2006i = typedArrayObtainStyledAttributes.getDimension(index, this.f2006i);
                        break;
                    case 8:
                        this.f2007j = typedArrayObtainStyledAttributes.getDimension(index, this.f2007j);
                        break;
                    case 9:
                        this.f2008k = typedArrayObtainStyledAttributes.getDimension(index, this.f2008k);
                        break;
                    case 10:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.f2009l = typedArrayObtainStyledAttributes.getDimension(index, this.f2009l);
                        }
                        break;
                    case 11:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.f2010m = true;
                            this.f2011n = typedArrayObtainStyledAttributes.getDimension(index, this.f2011n);
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f1937b = sparseIntArray;
        sparseIntArray.append(R$styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        f1937b.append(R$styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        f1937b.append(R$styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        f1937b.append(R$styleable.Constraint_layout_constraintRight_toRightOf, 30);
        f1937b.append(R$styleable.Constraint_layout_constraintTop_toTopOf, 36);
        f1937b.append(R$styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        f1937b.append(R$styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        f1937b.append(R$styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        f1937b.append(R$styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        f1937b.append(R$styleable.Constraint_layout_editor_absoluteX, 6);
        f1937b.append(R$styleable.Constraint_layout_editor_absoluteY, 7);
        f1937b.append(R$styleable.Constraint_layout_constraintGuide_begin, 17);
        f1937b.append(R$styleable.Constraint_layout_constraintGuide_end, 18);
        f1937b.append(R$styleable.Constraint_layout_constraintGuide_percent, 19);
        f1937b.append(R$styleable.Constraint_android_orientation, 27);
        f1937b.append(R$styleable.Constraint_layout_constraintStart_toEndOf, 32);
        f1937b.append(R$styleable.Constraint_layout_constraintStart_toStartOf, 33);
        f1937b.append(R$styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        f1937b.append(R$styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        f1937b.append(R$styleable.Constraint_layout_goneMarginLeft, 13);
        f1937b.append(R$styleable.Constraint_layout_goneMarginTop, 16);
        f1937b.append(R$styleable.Constraint_layout_goneMarginRight, 14);
        f1937b.append(R$styleable.Constraint_layout_goneMarginBottom, 11);
        f1937b.append(R$styleable.Constraint_layout_goneMarginStart, 15);
        f1937b.append(R$styleable.Constraint_layout_goneMarginEnd, 12);
        f1937b.append(R$styleable.Constraint_layout_constraintVertical_weight, 40);
        f1937b.append(R$styleable.Constraint_layout_constraintHorizontal_weight, 39);
        f1937b.append(R$styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        f1937b.append(R$styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        f1937b.append(R$styleable.Constraint_layout_constraintHorizontal_bias, 20);
        f1937b.append(R$styleable.Constraint_layout_constraintVertical_bias, 37);
        f1937b.append(R$styleable.Constraint_layout_constraintDimensionRatio, 5);
        f1937b.append(R$styleable.Constraint_layout_constraintLeft_creator, 82);
        f1937b.append(R$styleable.Constraint_layout_constraintTop_creator, 82);
        f1937b.append(R$styleable.Constraint_layout_constraintRight_creator, 82);
        f1937b.append(R$styleable.Constraint_layout_constraintBottom_creator, 82);
        f1937b.append(R$styleable.Constraint_layout_constraintBaseline_creator, 82);
        f1937b.append(R$styleable.Constraint_android_layout_marginLeft, 24);
        f1937b.append(R$styleable.Constraint_android_layout_marginRight, 28);
        f1937b.append(R$styleable.Constraint_android_layout_marginStart, 31);
        f1937b.append(R$styleable.Constraint_android_layout_marginEnd, 8);
        f1937b.append(R$styleable.Constraint_android_layout_marginTop, 34);
        f1937b.append(R$styleable.Constraint_android_layout_marginBottom, 2);
        f1937b.append(R$styleable.Constraint_android_layout_width, 23);
        f1937b.append(R$styleable.Constraint_android_layout_height, 21);
        f1937b.append(R$styleable.Constraint_android_visibility, 22);
        f1937b.append(R$styleable.Constraint_android_alpha, 43);
        f1937b.append(R$styleable.Constraint_android_elevation, 44);
        f1937b.append(R$styleable.Constraint_android_rotationX, 45);
        f1937b.append(R$styleable.Constraint_android_rotationY, 46);
        f1937b.append(R$styleable.Constraint_android_rotation, 60);
        f1937b.append(R$styleable.Constraint_android_scaleX, 47);
        f1937b.append(R$styleable.Constraint_android_scaleY, 48);
        f1937b.append(R$styleable.Constraint_android_transformPivotX, 49);
        f1937b.append(R$styleable.Constraint_android_transformPivotY, 50);
        f1937b.append(R$styleable.Constraint_android_translationX, 51);
        f1937b.append(R$styleable.Constraint_android_translationY, 52);
        f1937b.append(R$styleable.Constraint_android_translationZ, 53);
        f1937b.append(R$styleable.Constraint_layout_constraintWidth_default, 54);
        f1937b.append(R$styleable.Constraint_layout_constraintHeight_default, 55);
        f1937b.append(R$styleable.Constraint_layout_constraintWidth_max, 56);
        f1937b.append(R$styleable.Constraint_layout_constraintHeight_max, 57);
        f1937b.append(R$styleable.Constraint_layout_constraintWidth_min, 58);
        f1937b.append(R$styleable.Constraint_layout_constraintHeight_min, 59);
        f1937b.append(R$styleable.Constraint_layout_constraintCircle, 61);
        f1937b.append(R$styleable.Constraint_layout_constraintCircleRadius, 62);
        f1937b.append(R$styleable.Constraint_layout_constraintCircleAngle, 63);
        f1937b.append(R$styleable.Constraint_animate_relativeTo, 64);
        f1937b.append(R$styleable.Constraint_transitionEasing, 65);
        f1937b.append(R$styleable.Constraint_drawPath, 66);
        f1937b.append(R$styleable.Constraint_transitionPathRotate, 67);
        f1937b.append(R$styleable.Constraint_motionStagger, 79);
        f1937b.append(R$styleable.Constraint_android_id, 38);
        f1937b.append(R$styleable.Constraint_motionProgress, 68);
        f1937b.append(R$styleable.Constraint_layout_constraintWidth_percent, 69);
        f1937b.append(R$styleable.Constraint_layout_constraintHeight_percent, 70);
        f1937b.append(R$styleable.Constraint_chainUseRtl, 71);
        f1937b.append(R$styleable.Constraint_barrierDirection, 72);
        f1937b.append(R$styleable.Constraint_barrierMargin, 73);
        f1937b.append(R$styleable.Constraint_constraint_referenced_ids, 74);
        f1937b.append(R$styleable.Constraint_barrierAllowsGoneWidgets, 75);
        f1937b.append(R$styleable.Constraint_pathMotionArc, 76);
        f1937b.append(R$styleable.Constraint_layout_constraintTag, 77);
        f1937b.append(R$styleable.Constraint_visibilityMode, 78);
        f1937b.append(R$styleable.Constraint_layout_constrainedWidth, 80);
        f1937b.append(R$styleable.Constraint_layout_constrainedHeight, 81);
    }

    public static int w(TypedArray typedArray, int i8, int i9) {
        int resourceId = typedArray.getResourceId(i8, i9);
        return resourceId == -1 ? typedArray.getInt(i8, -1) : resourceId;
    }

    public void A(boolean z7) {
        this.f1940e = z7;
    }

    public void B(boolean z7) {
        this.f1938c = z7;
    }

    public void c(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = constraintLayout.getChildAt(i8);
            int id = childAt.getId();
            if (!this.f1941f.containsKey(Integer.valueOf(id))) {
                Log.v("ConstraintSet", "id unknown " + x.a.c(childAt));
            } else {
                if (this.f1940e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (this.f1941f.containsKey(Integer.valueOf(id))) {
                    ConstraintAttribute.h(childAt, this.f1941f.get(Integer.valueOf(id)).f1947f);
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
        if (this.f1941f.containsKey(Integer.valueOf(id))) {
            a aVar = this.f1941f.get(Integer.valueOf(id));
            if (constraintWidget instanceof h) {
                constraintHelper.n(aVar, (h) constraintWidget, bVar, sparseArray);
            }
        }
    }

    public void f(ConstraintLayout constraintLayout, boolean z7) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.f1941f.keySet());
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = constraintLayout.getChildAt(i8);
            int id = childAt.getId();
            if (!this.f1941f.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + x.a.c(childAt));
            } else {
                if (this.f1940e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.f1941f.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = this.f1941f.get(Integer.valueOf(id));
                        if (childAt instanceof Barrier) {
                            aVar.f1945d.f1957e0 = 1;
                        }
                        int i9 = aVar.f1945d.f1957e0;
                        if (i9 != -1 && i9 == 1) {
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(aVar.f1945d.f1953c0);
                            barrier.setMargin(aVar.f1945d.f1955d0);
                            barrier.setAllowsGoneWidget(aVar.f1945d.f1969k0);
                            C0006b c0006b = aVar.f1945d;
                            int[] iArr = c0006b.f1959f0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = c0006b.f1961g0;
                                if (str != null) {
                                    c0006b.f1959f0 = k(barrier, str);
                                    barrier.setReferencedIds(aVar.f1945d.f1959f0);
                                }
                            }
                        }
                        ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
                        bVar.c();
                        aVar.d(bVar);
                        if (z7) {
                            ConstraintAttribute.h(childAt, aVar.f1947f);
                        }
                        childAt.setLayoutParams(bVar);
                        d dVar = aVar.f1943b;
                        if (dVar.f1995c == 0) {
                            childAt.setVisibility(dVar.f1994b);
                        }
                        int i10 = Build.VERSION.SDK_INT;
                        if (i10 >= 17) {
                            childAt.setAlpha(aVar.f1943b.f1996d);
                            childAt.setRotation(aVar.f1946e.f2000c);
                            childAt.setRotationX(aVar.f1946e.f2001d);
                            childAt.setRotationY(aVar.f1946e.f2002e);
                            childAt.setScaleX(aVar.f1946e.f2003f);
                            childAt.setScaleY(aVar.f1946e.f2004g);
                            if (!Float.isNaN(aVar.f1946e.f2005h)) {
                                childAt.setPivotX(aVar.f1946e.f2005h);
                            }
                            if (!Float.isNaN(aVar.f1946e.f2006i)) {
                                childAt.setPivotY(aVar.f1946e.f2006i);
                            }
                            childAt.setTranslationX(aVar.f1946e.f2007j);
                            childAt.setTranslationY(aVar.f1946e.f2008k);
                            if (i10 >= 21) {
                                childAt.setTranslationZ(aVar.f1946e.f2009l);
                                e eVar = aVar.f1946e;
                                if (eVar.f2010m) {
                                    childAt.setElevation(eVar.f2011n);
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
            a aVar2 = this.f1941f.get(num);
            int i11 = aVar2.f1945d.f1957e0;
            if (i11 != -1 && i11 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                C0006b c0006b2 = aVar2.f1945d;
                int[] iArr2 = c0006b2.f1959f0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = c0006b2.f1961g0;
                    if (str2 != null) {
                        c0006b2.f1959f0 = k(barrier2, str2);
                        barrier2.setReferencedIds(aVar2.f1945d.f1959f0);
                    }
                }
                barrier2.setType(aVar2.f1945d.f1953c0);
                barrier2.setMargin(aVar2.f1945d.f1955d0);
                ConstraintLayout.b bVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.u();
                aVar2.d(bVarGenerateDefaultLayoutParams);
                constraintLayout.addView(barrier2, bVarGenerateDefaultLayoutParams);
            }
            if (aVar2.f1945d.f1950b) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.b bVarGenerateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                aVar2.d(bVarGenerateDefaultLayoutParams2);
                constraintLayout.addView(guideline, bVarGenerateDefaultLayoutParams2);
            }
        }
    }

    public void g(int i8, ConstraintLayout.b bVar) {
        if (this.f1941f.containsKey(Integer.valueOf(i8))) {
            this.f1941f.get(Integer.valueOf(i8)).d(bVar);
        }
    }

    public void h(Context context, int i8) {
        i((ConstraintLayout) LayoutInflater.from(context).inflate(i8, (ViewGroup) null));
    }

    public void i(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.f1941f.clear();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = constraintLayout.getChildAt(i8);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1940e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1941f.containsKey(Integer.valueOf(id))) {
                this.f1941f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f1941f.get(Integer.valueOf(id));
            aVar.f1947f = ConstraintAttribute.b(this.f1939d, childAt);
            aVar.f(id, bVar);
            aVar.f1943b.f1994b = childAt.getVisibility();
            int i9 = Build.VERSION.SDK_INT;
            if (i9 >= 17) {
                aVar.f1943b.f1996d = childAt.getAlpha();
                aVar.f1946e.f2000c = childAt.getRotation();
                aVar.f1946e.f2001d = childAt.getRotationX();
                aVar.f1946e.f2002e = childAt.getRotationY();
                aVar.f1946e.f2003f = childAt.getScaleX();
                aVar.f1946e.f2004g = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    e eVar = aVar.f1946e;
                    eVar.f2005h = pivotX;
                    eVar.f2006i = pivotY;
                }
                aVar.f1946e.f2007j = childAt.getTranslationX();
                aVar.f1946e.f2008k = childAt.getTranslationY();
                if (i9 >= 21) {
                    aVar.f1946e.f2009l = childAt.getTranslationZ();
                    e eVar2 = aVar.f1946e;
                    if (eVar2.f2010m) {
                        eVar2.f2011n = childAt.getElevation();
                    }
                }
            }
            if (childAt instanceof Barrier) {
                Barrier barrier = (Barrier) childAt;
                aVar.f1945d.f1969k0 = barrier.v();
                aVar.f1945d.f1959f0 = barrier.getReferencedIds();
                aVar.f1945d.f1953c0 = barrier.getType();
                aVar.f1945d.f1955d0 = barrier.getMargin();
            }
        }
    }

    public void j(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.f1941f.clear();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = constraints.getChildAt(i8);
            Constraints.a aVar = (Constraints.a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1940e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1941f.containsKey(Integer.valueOf(id))) {
                this.f1941f.put(Integer.valueOf(id), new a());
            }
            a aVar2 = this.f1941f.get(Integer.valueOf(id));
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
        int i8 = 0;
        int i9 = 0;
        while (i8 < strArrSplit.length) {
            String strTrim = strArrSplit[i8].trim();
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
            iArr[i9] = iIntValue;
            i8++;
            i9++;
        }
        return i9 != strArrSplit.length ? Arrays.copyOf(iArr, i9) : iArr;
    }

    public final a l(Context context, AttributeSet attributeSet) {
        a aVar = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Constraint);
        x(context, aVar, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    public final a m(int i8) {
        if (!this.f1941f.containsKey(Integer.valueOf(i8))) {
            this.f1941f.put(Integer.valueOf(i8), new a());
        }
        return this.f1941f.get(Integer.valueOf(i8));
    }

    public a n(int i8) {
        if (this.f1941f.containsKey(Integer.valueOf(i8))) {
            return this.f1941f.get(Integer.valueOf(i8));
        }
        return null;
    }

    public int o(int i8) {
        return m(i8).f1945d.f1956e;
    }

    public int[] p() {
        Integer[] numArr = (Integer[]) this.f1941f.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i8 = 0; i8 < length; i8++) {
            iArr[i8] = numArr[i8].intValue();
        }
        return iArr;
    }

    public a q(int i8) {
        return m(i8);
    }

    public int r(int i8) {
        return m(i8).f1943b.f1994b;
    }

    public int s(int i8) {
        return m(i8).f1943b.f1995c;
    }

    public int t(int i8) {
        return m(i8).f1945d.f1954d;
    }

    public void u(Context context, int i8) {
        XmlResourceParser xml = context.getResources().getXml(i8);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    a aVarL = l(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        aVarL.f1945d.f1950b = true;
                    }
                    this.f1941f.put(Integer.valueOf(aVarL.f1942a), aVarL);
                }
            }
        } catch (IOException e8) {
            e8.printStackTrace();
        } catch (XmlPullParserException e9) {
            e9.printStackTrace();
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
        throw new UnsupportedOperationException("Method not decompiled: b0.b.v(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public final void x(Context context, a aVar, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i8 = 0; i8 < indexCount; i8++) {
            int index = typedArray.getIndex(i8);
            if (index != R$styleable.Constraint_android_id && R$styleable.Constraint_android_layout_marginStart != index && R$styleable.Constraint_android_layout_marginEnd != index) {
                aVar.f1944c.f1986b = true;
                aVar.f1945d.f1952c = true;
                aVar.f1943b.f1993a = true;
                aVar.f1946e.f1999b = true;
            }
            switch (f1937b.get(index)) {
                case 1:
                    C0006b c0006b = aVar.f1945d;
                    c0006b.f1975q = w(typedArray, index, c0006b.f1975q);
                    break;
                case 2:
                    C0006b c0006b2 = aVar.f1945d;
                    c0006b2.H = typedArray.getDimensionPixelSize(index, c0006b2.H);
                    break;
                case 3:
                    C0006b c0006b3 = aVar.f1945d;
                    c0006b3.f1974p = w(typedArray, index, c0006b3.f1974p);
                    break;
                case 4:
                    C0006b c0006b4 = aVar.f1945d;
                    c0006b4.f1973o = w(typedArray, index, c0006b4.f1973o);
                    break;
                case 5:
                    aVar.f1945d.f1982x = typedArray.getString(index);
                    break;
                case 6:
                    C0006b c0006b5 = aVar.f1945d;
                    c0006b5.B = typedArray.getDimensionPixelOffset(index, c0006b5.B);
                    break;
                case 7:
                    C0006b c0006b6 = aVar.f1945d;
                    c0006b6.C = typedArray.getDimensionPixelOffset(index, c0006b6.C);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0006b c0006b7 = aVar.f1945d;
                        c0006b7.I = typedArray.getDimensionPixelSize(index, c0006b7.I);
                    }
                    break;
                case 9:
                    C0006b c0006b8 = aVar.f1945d;
                    c0006b8.f1979u = w(typedArray, index, c0006b8.f1979u);
                    break;
                case 10:
                    C0006b c0006b9 = aVar.f1945d;
                    c0006b9.f1978t = w(typedArray, index, c0006b9.f1978t);
                    break;
                case 11:
                    C0006b c0006b10 = aVar.f1945d;
                    c0006b10.N = typedArray.getDimensionPixelSize(index, c0006b10.N);
                    break;
                case 12:
                    C0006b c0006b11 = aVar.f1945d;
                    c0006b11.O = typedArray.getDimensionPixelSize(index, c0006b11.O);
                    break;
                case 13:
                    C0006b c0006b12 = aVar.f1945d;
                    c0006b12.K = typedArray.getDimensionPixelSize(index, c0006b12.K);
                    break;
                case 14:
                    C0006b c0006b13 = aVar.f1945d;
                    c0006b13.M = typedArray.getDimensionPixelSize(index, c0006b13.M);
                    break;
                case 15:
                    C0006b c0006b14 = aVar.f1945d;
                    c0006b14.P = typedArray.getDimensionPixelSize(index, c0006b14.P);
                    break;
                case 16:
                    C0006b c0006b15 = aVar.f1945d;
                    c0006b15.L = typedArray.getDimensionPixelSize(index, c0006b15.L);
                    break;
                case 17:
                    C0006b c0006b16 = aVar.f1945d;
                    c0006b16.f1958f = typedArray.getDimensionPixelOffset(index, c0006b16.f1958f);
                    break;
                case 18:
                    C0006b c0006b17 = aVar.f1945d;
                    c0006b17.f1960g = typedArray.getDimensionPixelOffset(index, c0006b17.f1960g);
                    break;
                case 19:
                    C0006b c0006b18 = aVar.f1945d;
                    c0006b18.f1962h = typedArray.getFloat(index, c0006b18.f1962h);
                    break;
                case 20:
                    C0006b c0006b19 = aVar.f1945d;
                    c0006b19.f1980v = typedArray.getFloat(index, c0006b19.f1980v);
                    break;
                case 21:
                    C0006b c0006b20 = aVar.f1945d;
                    c0006b20.f1956e = typedArray.getLayoutDimension(index, c0006b20.f1956e);
                    break;
                case 22:
                    d dVar = aVar.f1943b;
                    dVar.f1994b = typedArray.getInt(index, dVar.f1994b);
                    d dVar2 = aVar.f1943b;
                    dVar2.f1994b = f1936a[dVar2.f1994b];
                    break;
                case 23:
                    C0006b c0006b21 = aVar.f1945d;
                    c0006b21.f1954d = typedArray.getLayoutDimension(index, c0006b21.f1954d);
                    break;
                case 24:
                    C0006b c0006b22 = aVar.f1945d;
                    c0006b22.E = typedArray.getDimensionPixelSize(index, c0006b22.E);
                    break;
                case 25:
                    C0006b c0006b23 = aVar.f1945d;
                    c0006b23.f1964i = w(typedArray, index, c0006b23.f1964i);
                    break;
                case 26:
                    C0006b c0006b24 = aVar.f1945d;
                    c0006b24.f1966j = w(typedArray, index, c0006b24.f1966j);
                    break;
                case 27:
                    C0006b c0006b25 = aVar.f1945d;
                    c0006b25.D = typedArray.getInt(index, c0006b25.D);
                    break;
                case 28:
                    C0006b c0006b26 = aVar.f1945d;
                    c0006b26.F = typedArray.getDimensionPixelSize(index, c0006b26.F);
                    break;
                case 29:
                    C0006b c0006b27 = aVar.f1945d;
                    c0006b27.f1968k = w(typedArray, index, c0006b27.f1968k);
                    break;
                case 30:
                    C0006b c0006b28 = aVar.f1945d;
                    c0006b28.f1970l = w(typedArray, index, c0006b28.f1970l);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0006b c0006b29 = aVar.f1945d;
                        c0006b29.J = typedArray.getDimensionPixelSize(index, c0006b29.J);
                    }
                    break;
                case 32:
                    C0006b c0006b30 = aVar.f1945d;
                    c0006b30.f1976r = w(typedArray, index, c0006b30.f1976r);
                    break;
                case 33:
                    C0006b c0006b31 = aVar.f1945d;
                    c0006b31.f1977s = w(typedArray, index, c0006b31.f1977s);
                    break;
                case 34:
                    C0006b c0006b32 = aVar.f1945d;
                    c0006b32.G = typedArray.getDimensionPixelSize(index, c0006b32.G);
                    break;
                case 35:
                    C0006b c0006b33 = aVar.f1945d;
                    c0006b33.f1972n = w(typedArray, index, c0006b33.f1972n);
                    break;
                case 36:
                    C0006b c0006b34 = aVar.f1945d;
                    c0006b34.f1971m = w(typedArray, index, c0006b34.f1971m);
                    break;
                case 37:
                    C0006b c0006b35 = aVar.f1945d;
                    c0006b35.f1981w = typedArray.getFloat(index, c0006b35.f1981w);
                    break;
                case 38:
                    aVar.f1942a = typedArray.getResourceId(index, aVar.f1942a);
                    break;
                case 39:
                    C0006b c0006b36 = aVar.f1945d;
                    c0006b36.R = typedArray.getFloat(index, c0006b36.R);
                    break;
                case 40:
                    C0006b c0006b37 = aVar.f1945d;
                    c0006b37.Q = typedArray.getFloat(index, c0006b37.Q);
                    break;
                case 41:
                    C0006b c0006b38 = aVar.f1945d;
                    c0006b38.S = typedArray.getInt(index, c0006b38.S);
                    break;
                case 42:
                    C0006b c0006b39 = aVar.f1945d;
                    c0006b39.T = typedArray.getInt(index, c0006b39.T);
                    break;
                case 43:
                    d dVar3 = aVar.f1943b;
                    dVar3.f1996d = typedArray.getFloat(index, dVar3.f1996d);
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar = aVar.f1946e;
                        eVar.f2010m = true;
                        eVar.f2011n = typedArray.getDimension(index, eVar.f2011n);
                    }
                    break;
                case 45:
                    e eVar2 = aVar.f1946e;
                    eVar2.f2001d = typedArray.getFloat(index, eVar2.f2001d);
                    break;
                case 46:
                    e eVar3 = aVar.f1946e;
                    eVar3.f2002e = typedArray.getFloat(index, eVar3.f2002e);
                    break;
                case 47:
                    e eVar4 = aVar.f1946e;
                    eVar4.f2003f = typedArray.getFloat(index, eVar4.f2003f);
                    break;
                case 48:
                    e eVar5 = aVar.f1946e;
                    eVar5.f2004g = typedArray.getFloat(index, eVar5.f2004g);
                    break;
                case 49:
                    e eVar6 = aVar.f1946e;
                    eVar6.f2005h = typedArray.getDimension(index, eVar6.f2005h);
                    break;
                case 50:
                    e eVar7 = aVar.f1946e;
                    eVar7.f2006i = typedArray.getDimension(index, eVar7.f2006i);
                    break;
                case 51:
                    e eVar8 = aVar.f1946e;
                    eVar8.f2007j = typedArray.getDimension(index, eVar8.f2007j);
                    break;
                case 52:
                    e eVar9 = aVar.f1946e;
                    eVar9.f2008k = typedArray.getDimension(index, eVar9.f2008k);
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar10 = aVar.f1946e;
                        eVar10.f2009l = typedArray.getDimension(index, eVar10.f2009l);
                    }
                    break;
                case 54:
                    C0006b c0006b40 = aVar.f1945d;
                    c0006b40.U = typedArray.getInt(index, c0006b40.U);
                    break;
                case 55:
                    C0006b c0006b41 = aVar.f1945d;
                    c0006b41.V = typedArray.getInt(index, c0006b41.V);
                    break;
                case 56:
                    C0006b c0006b42 = aVar.f1945d;
                    c0006b42.W = typedArray.getDimensionPixelSize(index, c0006b42.W);
                    break;
                case 57:
                    C0006b c0006b43 = aVar.f1945d;
                    c0006b43.X = typedArray.getDimensionPixelSize(index, c0006b43.X);
                    break;
                case 58:
                    C0006b c0006b44 = aVar.f1945d;
                    c0006b44.Y = typedArray.getDimensionPixelSize(index, c0006b44.Y);
                    break;
                case 59:
                    C0006b c0006b45 = aVar.f1945d;
                    c0006b45.Z = typedArray.getDimensionPixelSize(index, c0006b45.Z);
                    break;
                case 60:
                    e eVar11 = aVar.f1946e;
                    eVar11.f2000c = typedArray.getFloat(index, eVar11.f2000c);
                    break;
                case 61:
                    C0006b c0006b46 = aVar.f1945d;
                    c0006b46.f1983y = w(typedArray, index, c0006b46.f1983y);
                    break;
                case 62:
                    C0006b c0006b47 = aVar.f1945d;
                    c0006b47.f1984z = typedArray.getDimensionPixelSize(index, c0006b47.f1984z);
                    break;
                case 63:
                    C0006b c0006b48 = aVar.f1945d;
                    c0006b48.A = typedArray.getFloat(index, c0006b48.A);
                    break;
                case 64:
                    c cVar = aVar.f1944c;
                    cVar.f1987c = w(typedArray, index, cVar.f1987c);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.f1944c.f1988d = typedArray.getString(index);
                    } else {
                        aVar.f1944c.f1988d = w.c.f8625b[typedArray.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    aVar.f1944c.f1990f = typedArray.getInt(index, 0);
                    break;
                case 67:
                    c cVar2 = aVar.f1944c;
                    cVar2.f1992h = typedArray.getFloat(index, cVar2.f1992h);
                    break;
                case 68:
                    d dVar4 = aVar.f1943b;
                    dVar4.f1997e = typedArray.getFloat(index, dVar4.f1997e);
                    break;
                case 69:
                    aVar.f1945d.f1949a0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    aVar.f1945d.f1951b0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    C0006b c0006b49 = aVar.f1945d;
                    c0006b49.f1953c0 = typedArray.getInt(index, c0006b49.f1953c0);
                    break;
                case 73:
                    C0006b c0006b50 = aVar.f1945d;
                    c0006b50.f1955d0 = typedArray.getDimensionPixelSize(index, c0006b50.f1955d0);
                    break;
                case 74:
                    aVar.f1945d.f1961g0 = typedArray.getString(index);
                    break;
                case 75:
                    C0006b c0006b51 = aVar.f1945d;
                    c0006b51.f1969k0 = typedArray.getBoolean(index, c0006b51.f1969k0);
                    break;
                case 76:
                    c cVar3 = aVar.f1944c;
                    cVar3.f1989e = typedArray.getInt(index, cVar3.f1989e);
                    break;
                case 77:
                    aVar.f1945d.f1963h0 = typedArray.getString(index);
                    break;
                case 78:
                    d dVar5 = aVar.f1943b;
                    dVar5.f1995c = typedArray.getInt(index, dVar5.f1995c);
                    break;
                case 79:
                    c cVar4 = aVar.f1944c;
                    cVar4.f1991g = typedArray.getFloat(index, cVar4.f1991g);
                    break;
                case 80:
                    C0006b c0006b52 = aVar.f1945d;
                    c0006b52.f1965i0 = typedArray.getBoolean(index, c0006b52.f1965i0);
                    break;
                case 81:
                    C0006b c0006b53 = aVar.f1945d;
                    c0006b53.f1967j0 = typedArray.getBoolean(index, c0006b53.f1967j0);
                    break;
                case 82:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1937b.get(index));
                    break;
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1937b.get(index));
                    break;
            }
        }
    }

    public void y(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = constraintLayout.getChildAt(i8);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1940e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1941f.containsKey(Integer.valueOf(id))) {
                this.f1941f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f1941f.get(Integer.valueOf(id));
            if (!aVar.f1945d.f1952c) {
                aVar.f(id, bVar);
                if (childAt instanceof ConstraintHelper) {
                    aVar.f1945d.f1959f0 = ((ConstraintHelper) childAt).getReferencedIds();
                    if (childAt instanceof Barrier) {
                        Barrier barrier = (Barrier) childAt;
                        aVar.f1945d.f1969k0 = barrier.v();
                        aVar.f1945d.f1953c0 = barrier.getType();
                        aVar.f1945d.f1955d0 = barrier.getMargin();
                    }
                }
                aVar.f1945d.f1952c = true;
            }
            d dVar = aVar.f1943b;
            if (!dVar.f1993a) {
                dVar.f1994b = childAt.getVisibility();
                aVar.f1943b.f1996d = childAt.getAlpha();
                aVar.f1943b.f1993a = true;
            }
            int i9 = Build.VERSION.SDK_INT;
            if (i9 >= 17) {
                e eVar = aVar.f1946e;
                if (!eVar.f1999b) {
                    eVar.f1999b = true;
                    eVar.f2000c = childAt.getRotation();
                    aVar.f1946e.f2001d = childAt.getRotationX();
                    aVar.f1946e.f2002e = childAt.getRotationY();
                    aVar.f1946e.f2003f = childAt.getScaleX();
                    aVar.f1946e.f2004g = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        e eVar2 = aVar.f1946e;
                        eVar2.f2005h = pivotX;
                        eVar2.f2006i = pivotY;
                    }
                    aVar.f1946e.f2007j = childAt.getTranslationX();
                    aVar.f1946e.f2008k = childAt.getTranslationY();
                    if (i9 >= 21) {
                        aVar.f1946e.f2009l = childAt.getTranslationZ();
                        e eVar3 = aVar.f1946e;
                        if (eVar3.f2010m) {
                            eVar3.f2011n = childAt.getElevation();
                        }
                    }
                }
            }
        }
    }

    public void z(b bVar) {
        for (Integer num : bVar.f1941f.keySet()) {
            int iIntValue = num.intValue();
            a aVar = bVar.f1941f.get(num);
            if (!this.f1941f.containsKey(Integer.valueOf(iIntValue))) {
                this.f1941f.put(Integer.valueOf(iIntValue), new a());
            }
            a aVar2 = this.f1941f.get(Integer.valueOf(iIntValue));
            C0006b c0006b = aVar2.f1945d;
            if (!c0006b.f1952c) {
                c0006b.a(aVar.f1945d);
            }
            d dVar = aVar2.f1943b;
            if (!dVar.f1993a) {
                dVar.a(aVar.f1943b);
            }
            e eVar = aVar2.f1946e;
            if (!eVar.f1999b) {
                eVar.a(aVar.f1946e);
            }
            c cVar = aVar2.f1944c;
            if (!cVar.f1986b) {
                cVar.a(aVar.f1944c);
            }
            for (String str : aVar.f1947f.keySet()) {
                if (!aVar2.f1947f.containsKey(str)) {
                    aVar2.f1947f.put(str, aVar.f1947f.get(str));
                }
            }
        }
    }
}
