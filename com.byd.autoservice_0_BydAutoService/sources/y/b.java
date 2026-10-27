package y;

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
import w.h;

/* JADX INFO: compiled from: ConstraintSet.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f5760a = {0, 4, 8};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static SparseIntArray f5761b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5762c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public HashMap<String, ConstraintAttribute> f5763d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5764e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public HashMap<Integer, a> f5765f = new HashMap<>();

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5766a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f5767b = new d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c f5768c = new c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final C0074b f5769d = new C0074b();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final e f5770e = new e();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public HashMap<String, ConstraintAttribute> f5771f = new HashMap<>();

        public void d(ConstraintLayout.b bVar) {
            C0074b c0074b = this.f5769d;
            bVar.f1008d = c0074b.f5788i;
            bVar.f1010e = c0074b.f5790j;
            bVar.f1012f = c0074b.f5792k;
            bVar.f1014g = c0074b.f5794l;
            bVar.f1016h = c0074b.f5795m;
            bVar.f1018i = c0074b.f5796n;
            bVar.f1020j = c0074b.f5797o;
            bVar.f1022k = c0074b.f5798p;
            bVar.f1024l = c0074b.f5799q;
            bVar.f1032p = c0074b.f5800r;
            bVar.f1033q = c0074b.f5801s;
            bVar.f1034r = c0074b.f5802t;
            bVar.f1035s = c0074b.f5803u;
            ((ViewGroup.MarginLayoutParams) bVar).leftMargin = c0074b.E;
            ((ViewGroup.MarginLayoutParams) bVar).rightMargin = c0074b.F;
            ((ViewGroup.MarginLayoutParams) bVar).topMargin = c0074b.G;
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = c0074b.H;
            bVar.f1040x = c0074b.P;
            bVar.f1041y = c0074b.O;
            bVar.f1037u = c0074b.L;
            bVar.f1039w = c0074b.N;
            bVar.f1042z = c0074b.f5804v;
            bVar.A = c0074b.f5805w;
            bVar.f1026m = c0074b.f5807y;
            bVar.f1028n = c0074b.f5808z;
            C0074b c0074b2 = this.f5769d;
            bVar.f1030o = c0074b2.A;
            bVar.B = c0074b2.f5806x;
            bVar.Q = c0074b2.B;
            bVar.R = c0074b2.C;
            bVar.F = c0074b2.Q;
            bVar.E = c0074b2.R;
            bVar.H = c0074b2.T;
            bVar.G = c0074b2.S;
            bVar.T = c0074b2.f5789i0;
            bVar.U = c0074b2.f5791j0;
            bVar.I = c0074b2.U;
            bVar.J = c0074b2.V;
            bVar.M = c0074b2.W;
            bVar.N = c0074b2.X;
            bVar.K = c0074b2.Y;
            bVar.L = c0074b2.Z;
            bVar.O = c0074b2.f5773a0;
            bVar.P = c0074b2.f5775b0;
            bVar.S = c0074b2.D;
            bVar.f1006c = c0074b2.f5786h;
            bVar.f1002a = c0074b2.f5782f;
            bVar.f1004b = c0074b2.f5784g;
            ((ViewGroup.MarginLayoutParams) bVar).width = c0074b2.f5778d;
            ((ViewGroup.MarginLayoutParams) bVar).height = c0074b2.f5780e;
            String str = c0074b2.f5787h0;
            if (str != null) {
                bVar.V = str;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                bVar.setMarginStart(this.f5769d.J);
                bVar.setMarginEnd(this.f5769d.I);
            }
            bVar.c();
        }

        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.f5769d.a(this.f5769d);
            aVar.f5768c.a(this.f5768c);
            aVar.f5767b.a(this.f5767b);
            aVar.f5770e.a(this.f5770e);
            aVar.f5766a = this.f5766a;
            return aVar;
        }

        public final void f(int i3, ConstraintLayout.b bVar) {
            this.f5766a = i3;
            C0074b c0074b = this.f5769d;
            c0074b.f5788i = bVar.f1008d;
            c0074b.f5790j = bVar.f1010e;
            c0074b.f5792k = bVar.f1012f;
            c0074b.f5794l = bVar.f1014g;
            c0074b.f5795m = bVar.f1016h;
            c0074b.f5796n = bVar.f1018i;
            c0074b.f5797o = bVar.f1020j;
            c0074b.f5798p = bVar.f1022k;
            c0074b.f5799q = bVar.f1024l;
            c0074b.f5800r = bVar.f1032p;
            c0074b.f5801s = bVar.f1033q;
            c0074b.f5802t = bVar.f1034r;
            c0074b.f5803u = bVar.f1035s;
            c0074b.f5804v = bVar.f1042z;
            c0074b.f5805w = bVar.A;
            c0074b.f5806x = bVar.B;
            c0074b.f5807y = bVar.f1026m;
            c0074b.f5808z = bVar.f1028n;
            c0074b.A = bVar.f1030o;
            c0074b.B = bVar.Q;
            c0074b.C = bVar.R;
            c0074b.D = bVar.S;
            c0074b.f5786h = bVar.f1006c;
            c0074b.f5782f = bVar.f1002a;
            c0074b.f5784g = bVar.f1004b;
            C0074b c0074b2 = this.f5769d;
            c0074b2.f5778d = ((ViewGroup.MarginLayoutParams) bVar).width;
            c0074b2.f5780e = ((ViewGroup.MarginLayoutParams) bVar).height;
            c0074b2.E = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            c0074b2.F = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
            c0074b2.G = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            c0074b2.H = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
            c0074b2.Q = bVar.F;
            c0074b2.R = bVar.E;
            c0074b2.T = bVar.H;
            c0074b2.S = bVar.G;
            c0074b2.f5789i0 = bVar.T;
            c0074b2.f5791j0 = bVar.U;
            c0074b2.U = bVar.I;
            c0074b2.V = bVar.J;
            c0074b2.W = bVar.M;
            c0074b2.X = bVar.N;
            c0074b2.Y = bVar.K;
            c0074b2.Z = bVar.L;
            c0074b2.f5773a0 = bVar.O;
            c0074b2.f5775b0 = bVar.P;
            c0074b2.f5787h0 = bVar.V;
            c0074b2.L = bVar.f1037u;
            c0074b2.N = bVar.f1039w;
            c0074b2.K = bVar.f1036t;
            c0074b2.M = bVar.f1038v;
            C0074b c0074b3 = this.f5769d;
            c0074b3.P = bVar.f1040x;
            c0074b3.O = bVar.f1041y;
            if (Build.VERSION.SDK_INT >= 17) {
                c0074b3.I = bVar.getMarginEnd();
                this.f5769d.J = bVar.getMarginStart();
            }
        }

        public final void g(int i3, Constraints.a aVar) {
            f(i3, aVar);
            this.f5767b.f5820d = aVar.f1053p0;
            e eVar = this.f5770e;
            eVar.f5824c = aVar.f1056s0;
            eVar.f5825d = aVar.f1057t0;
            eVar.f5826e = aVar.f1058u0;
            eVar.f5827f = aVar.f1059v0;
            eVar.f5828g = aVar.f1060w0;
            eVar.f5829h = aVar.f1061x0;
            eVar.f5830i = aVar.f1062y0;
            eVar.f5831j = aVar.f1063z0;
            eVar.f5832k = aVar.A0;
            eVar.f5833l = aVar.B0;
            eVar.f5835n = aVar.f1055r0;
            eVar.f5834m = aVar.f1054q0;
        }

        public final void h(ConstraintHelper constraintHelper, int i3, Constraints.a aVar) {
            g(i3, aVar);
            if (constraintHelper instanceof Barrier) {
                C0074b c0074b = this.f5769d;
                c0074b.f5781e0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                c0074b.f5777c0 = barrier.getType();
                this.f5769d.f5783f0 = barrier.getReferencedIds();
                this.f5769d.f5779d0 = barrier.getMargin();
            }
        }
    }

    /* JADX INFO: renamed from: y.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class C0074b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f5772a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5778d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5780e;

        /* JADX INFO: renamed from: f0, reason: collision with root package name */
        public int[] f5783f0;

        /* JADX INFO: renamed from: g0, reason: collision with root package name */
        public String f5785g0;

        /* JADX INFO: renamed from: h0, reason: collision with root package name */
        public String f5787h0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f5774b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f5776c = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5782f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f5784g = -1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f5786h = -1.0f;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f5788i = -1;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f5790j = -1;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f5792k = -1;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f5794l = -1;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f5795m = -1;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f5796n = -1;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f5797o = -1;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f5798p = -1;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f5799q = -1;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f5800r = -1;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f5801s = -1;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f5802t = -1;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public int f5803u = -1;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public float f5804v = 0.5f;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public float f5805w = 0.5f;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public String f5806x = null;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public int f5807y = -1;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public int f5808z = 0;
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
        public float f5773a0 = 1.0f;

        /* JADX INFO: renamed from: b0, reason: collision with root package name */
        public float f5775b0 = 1.0f;

        /* JADX INFO: renamed from: c0, reason: collision with root package name */
        public int f5777c0 = -1;

        /* JADX INFO: renamed from: d0, reason: collision with root package name */
        public int f5779d0 = 0;

        /* JADX INFO: renamed from: e0, reason: collision with root package name */
        public int f5781e0 = -1;

        /* JADX INFO: renamed from: i0, reason: collision with root package name */
        public boolean f5789i0 = false;

        /* JADX INFO: renamed from: j0, reason: collision with root package name */
        public boolean f5791j0 = false;

        /* JADX INFO: renamed from: k0, reason: collision with root package name */
        public boolean f5793k0 = true;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f5772a = sparseIntArray;
            sparseIntArray.append(R$styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            f5772a.append(R$styleable.Layout_layout_constraintLeft_toRightOf, 25);
            f5772a.append(R$styleable.Layout_layout_constraintRight_toLeftOf, 28);
            f5772a.append(R$styleable.Layout_layout_constraintRight_toRightOf, 29);
            f5772a.append(R$styleable.Layout_layout_constraintTop_toTopOf, 35);
            f5772a.append(R$styleable.Layout_layout_constraintTop_toBottomOf, 34);
            f5772a.append(R$styleable.Layout_layout_constraintBottom_toTopOf, 4);
            f5772a.append(R$styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            f5772a.append(R$styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            f5772a.append(R$styleable.Layout_layout_editor_absoluteX, 6);
            f5772a.append(R$styleable.Layout_layout_editor_absoluteY, 7);
            f5772a.append(R$styleable.Layout_layout_constraintGuide_begin, 17);
            f5772a.append(R$styleable.Layout_layout_constraintGuide_end, 18);
            f5772a.append(R$styleable.Layout_layout_constraintGuide_percent, 19);
            f5772a.append(R$styleable.Layout_android_orientation, 26);
            f5772a.append(R$styleable.Layout_layout_constraintStart_toEndOf, 31);
            f5772a.append(R$styleable.Layout_layout_constraintStart_toStartOf, 32);
            f5772a.append(R$styleable.Layout_layout_constraintEnd_toStartOf, 10);
            f5772a.append(R$styleable.Layout_layout_constraintEnd_toEndOf, 9);
            f5772a.append(R$styleable.Layout_layout_goneMarginLeft, 13);
            f5772a.append(R$styleable.Layout_layout_goneMarginTop, 16);
            f5772a.append(R$styleable.Layout_layout_goneMarginRight, 14);
            f5772a.append(R$styleable.Layout_layout_goneMarginBottom, 11);
            f5772a.append(R$styleable.Layout_layout_goneMarginStart, 15);
            f5772a.append(R$styleable.Layout_layout_goneMarginEnd, 12);
            f5772a.append(R$styleable.Layout_layout_constraintVertical_weight, 38);
            f5772a.append(R$styleable.Layout_layout_constraintHorizontal_weight, 37);
            f5772a.append(R$styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            f5772a.append(R$styleable.Layout_layout_constraintVertical_chainStyle, 40);
            f5772a.append(R$styleable.Layout_layout_constraintHorizontal_bias, 20);
            f5772a.append(R$styleable.Layout_layout_constraintVertical_bias, 36);
            f5772a.append(R$styleable.Layout_layout_constraintDimensionRatio, 5);
            f5772a.append(R$styleable.Layout_layout_constraintLeft_creator, 76);
            f5772a.append(R$styleable.Layout_layout_constraintTop_creator, 76);
            f5772a.append(R$styleable.Layout_layout_constraintRight_creator, 76);
            f5772a.append(R$styleable.Layout_layout_constraintBottom_creator, 76);
            f5772a.append(R$styleable.Layout_layout_constraintBaseline_creator, 76);
            f5772a.append(R$styleable.Layout_android_layout_marginLeft, 23);
            f5772a.append(R$styleable.Layout_android_layout_marginRight, 27);
            f5772a.append(R$styleable.Layout_android_layout_marginStart, 30);
            f5772a.append(R$styleable.Layout_android_layout_marginEnd, 8);
            f5772a.append(R$styleable.Layout_android_layout_marginTop, 33);
            f5772a.append(R$styleable.Layout_android_layout_marginBottom, 2);
            f5772a.append(R$styleable.Layout_android_layout_width, 22);
            f5772a.append(R$styleable.Layout_android_layout_height, 21);
            f5772a.append(R$styleable.Layout_layout_constraintCircle, 61);
            f5772a.append(R$styleable.Layout_layout_constraintCircleRadius, 62);
            f5772a.append(R$styleable.Layout_layout_constraintCircleAngle, 63);
            f5772a.append(R$styleable.Layout_layout_constraintWidth_percent, 69);
            f5772a.append(R$styleable.Layout_layout_constraintHeight_percent, 70);
            f5772a.append(R$styleable.Layout_chainUseRtl, 71);
            f5772a.append(R$styleable.Layout_barrierDirection, 72);
            f5772a.append(R$styleable.Layout_barrierMargin, 73);
            f5772a.append(R$styleable.Layout_constraint_referenced_ids, 74);
            f5772a.append(R$styleable.Layout_barrierAllowsGoneWidgets, 75);
        }

        public void a(C0074b c0074b) {
            this.f5774b = c0074b.f5774b;
            this.f5778d = c0074b.f5778d;
            this.f5776c = c0074b.f5776c;
            this.f5780e = c0074b.f5780e;
            this.f5782f = c0074b.f5782f;
            this.f5784g = c0074b.f5784g;
            this.f5786h = c0074b.f5786h;
            this.f5788i = c0074b.f5788i;
            this.f5790j = c0074b.f5790j;
            this.f5792k = c0074b.f5792k;
            this.f5794l = c0074b.f5794l;
            this.f5795m = c0074b.f5795m;
            this.f5796n = c0074b.f5796n;
            this.f5797o = c0074b.f5797o;
            this.f5798p = c0074b.f5798p;
            this.f5799q = c0074b.f5799q;
            this.f5800r = c0074b.f5800r;
            this.f5801s = c0074b.f5801s;
            this.f5802t = c0074b.f5802t;
            this.f5803u = c0074b.f5803u;
            this.f5804v = c0074b.f5804v;
            this.f5805w = c0074b.f5805w;
            this.f5806x = c0074b.f5806x;
            this.f5807y = c0074b.f5807y;
            this.f5808z = c0074b.f5808z;
            this.A = c0074b.A;
            this.B = c0074b.B;
            this.C = c0074b.C;
            this.D = c0074b.D;
            this.E = c0074b.E;
            this.F = c0074b.F;
            this.G = c0074b.G;
            this.H = c0074b.H;
            this.I = c0074b.I;
            this.J = c0074b.J;
            this.K = c0074b.K;
            this.L = c0074b.L;
            this.M = c0074b.M;
            this.N = c0074b.N;
            this.O = c0074b.O;
            this.P = c0074b.P;
            this.Q = c0074b.Q;
            this.R = c0074b.R;
            this.S = c0074b.S;
            this.T = c0074b.T;
            this.U = c0074b.U;
            this.V = c0074b.V;
            this.W = c0074b.W;
            this.X = c0074b.X;
            this.Y = c0074b.Y;
            this.Z = c0074b.Z;
            this.f5773a0 = c0074b.f5773a0;
            this.f5775b0 = c0074b.f5775b0;
            this.f5777c0 = c0074b.f5777c0;
            this.f5779d0 = c0074b.f5779d0;
            this.f5781e0 = c0074b.f5781e0;
            this.f5787h0 = c0074b.f5787h0;
            int[] iArr = c0074b.f5783f0;
            if (iArr != null) {
                this.f5783f0 = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.f5783f0 = null;
            }
            this.f5785g0 = c0074b.f5785g0;
            this.f5789i0 = c0074b.f5789i0;
            this.f5791j0 = c0074b.f5791j0;
            this.f5793k0 = c0074b.f5793k0;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Layout);
            this.f5776c = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                int i4 = f5772a.get(index);
                if (i4 == 80) {
                    this.f5789i0 = typedArrayObtainStyledAttributes.getBoolean(index, this.f5789i0);
                } else if (i4 != 81) {
                    switch (i4) {
                        case 1:
                            this.f5799q = b.w(typedArrayObtainStyledAttributes, index, this.f5799q);
                            break;
                        case 2:
                            this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.H);
                            break;
                        case 3:
                            this.f5798p = b.w(typedArrayObtainStyledAttributes, index, this.f5798p);
                            break;
                        case 4:
                            this.f5797o = b.w(typedArrayObtainStyledAttributes, index, this.f5797o);
                            break;
                        case 5:
                            this.f5806x = typedArrayObtainStyledAttributes.getString(index);
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
                            this.f5803u = b.w(typedArrayObtainStyledAttributes, index, this.f5803u);
                            break;
                        case 10:
                            this.f5802t = b.w(typedArrayObtainStyledAttributes, index, this.f5802t);
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
                            this.f5782f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f5782f);
                            break;
                        case 18:
                            this.f5784g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f5784g);
                            break;
                        case 19:
                            this.f5786h = typedArrayObtainStyledAttributes.getFloat(index, this.f5786h);
                            break;
                        case 20:
                            this.f5804v = typedArrayObtainStyledAttributes.getFloat(index, this.f5804v);
                            break;
                        case 21:
                            this.f5780e = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f5780e);
                            break;
                        case 22:
                            this.f5778d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f5778d);
                            break;
                        case 23:
                            this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.E);
                            break;
                        case 24:
                            this.f5788i = b.w(typedArrayObtainStyledAttributes, index, this.f5788i);
                            break;
                        case 25:
                            this.f5790j = b.w(typedArrayObtainStyledAttributes, index, this.f5790j);
                            break;
                        case 26:
                            this.D = typedArrayObtainStyledAttributes.getInt(index, this.D);
                            break;
                        case 27:
                            this.F = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.F);
                            break;
                        case 28:
                            this.f5792k = b.w(typedArrayObtainStyledAttributes, index, this.f5792k);
                            break;
                        case 29:
                            this.f5794l = b.w(typedArrayObtainStyledAttributes, index, this.f5794l);
                            break;
                        case 30:
                            if (Build.VERSION.SDK_INT >= 17) {
                                this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                            }
                            break;
                        case 31:
                            this.f5800r = b.w(typedArrayObtainStyledAttributes, index, this.f5800r);
                            break;
                        case 32:
                            this.f5801s = b.w(typedArrayObtainStyledAttributes, index, this.f5801s);
                            break;
                        case 33:
                            this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.G);
                            break;
                        case 34:
                            this.f5796n = b.w(typedArrayObtainStyledAttributes, index, this.f5796n);
                            break;
                        case 35:
                            this.f5795m = b.w(typedArrayObtainStyledAttributes, index, this.f5795m);
                            break;
                        case 36:
                            this.f5805w = typedArrayObtainStyledAttributes.getFloat(index, this.f5805w);
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
                            switch (i4) {
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
                                    switch (i4) {
                                        case 61:
                                            this.f5807y = b.w(typedArrayObtainStyledAttributes, index, this.f5807y);
                                            break;
                                        case 62:
                                            this.f5808z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f5808z);
                                            break;
                                        case 63:
                                            this.A = typedArrayObtainStyledAttributes.getFloat(index, this.A);
                                            break;
                                        default:
                                            switch (i4) {
                                                case 69:
                                                    this.f5773a0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 70:
                                                    this.f5775b0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                                    break;
                                                case 71:
                                                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                                    break;
                                                case 72:
                                                    this.f5777c0 = typedArrayObtainStyledAttributes.getInt(index, this.f5777c0);
                                                    break;
                                                case 73:
                                                    this.f5779d0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f5779d0);
                                                    break;
                                                case 74:
                                                    this.f5785g0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                case 75:
                                                    this.f5793k0 = typedArrayObtainStyledAttributes.getBoolean(index, this.f5793k0);
                                                    break;
                                                case 76:
                                                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f5772a.get(index));
                                                    break;
                                                case 77:
                                                    this.f5787h0 = typedArrayObtainStyledAttributes.getString(index);
                                                    break;
                                                default:
                                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f5772a.get(index));
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } else {
                    this.f5791j0 = typedArrayObtainStyledAttributes.getBoolean(index, this.f5791j0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f5809a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f5810b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5811c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f5812d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5813e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5814f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f5815g = Float.NaN;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f5816h = Float.NaN;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f5809a = sparseIntArray;
            sparseIntArray.append(R$styleable.Motion_motionPathRotate, 1);
            f5809a.append(R$styleable.Motion_pathMotionArc, 2);
            f5809a.append(R$styleable.Motion_transitionEasing, 3);
            f5809a.append(R$styleable.Motion_drawPath, 4);
            f5809a.append(R$styleable.Motion_animate_relativeTo, 5);
            f5809a.append(R$styleable.Motion_motionStagger, 6);
        }

        public void a(c cVar) {
            this.f5810b = cVar.f5810b;
            this.f5811c = cVar.f5811c;
            this.f5812d = cVar.f5812d;
            this.f5813e = cVar.f5813e;
            this.f5814f = cVar.f5814f;
            this.f5816h = cVar.f5816h;
            this.f5815g = cVar.f5815g;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Motion);
            this.f5810b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                switch (f5809a.get(index)) {
                    case 1:
                        this.f5816h = typedArrayObtainStyledAttributes.getFloat(index, this.f5816h);
                        break;
                    case 2:
                        this.f5813e = typedArrayObtainStyledAttributes.getInt(index, this.f5813e);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.f5812d = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.f5812d = t.c.f4950b[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.f5814f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.f5811c = b.w(typedArrayObtainStyledAttributes, index, this.f5811c);
                        break;
                    case 6:
                        this.f5815g = typedArrayObtainStyledAttributes.getFloat(index, this.f5815g);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f5817a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5818b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5819c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f5820d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f5821e = Float.NaN;

        public void a(d dVar) {
            this.f5817a = dVar.f5817a;
            this.f5818b = dVar.f5818b;
            this.f5820d = dVar.f5820d;
            this.f5821e = dVar.f5821e;
            this.f5819c = dVar.f5819c;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PropertySet);
            this.f5817a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.PropertySet_android_alpha) {
                    this.f5820d = typedArrayObtainStyledAttributes.getFloat(index, this.f5820d);
                } else if (index == R$styleable.PropertySet_android_visibility) {
                    this.f5818b = typedArrayObtainStyledAttributes.getInt(index, this.f5818b);
                    this.f5818b = b.f5760a[this.f5818b];
                } else if (index == R$styleable.PropertySet_visibilityMode) {
                    this.f5819c = typedArrayObtainStyledAttributes.getInt(index, this.f5819c);
                } else if (index == R$styleable.PropertySet_motionProgress) {
                    this.f5821e = typedArrayObtainStyledAttributes.getFloat(index, this.f5821e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static SparseIntArray f5822a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f5823b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f5824c = 0.0f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f5825d = 0.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f5826e = 0.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f5827f = 1.0f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f5828g = 1.0f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f5829h = Float.NaN;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f5830i = Float.NaN;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f5831j = 0.0f;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f5832k = 0.0f;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f5833l = 0.0f;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f5834m = false;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public float f5835n = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f5822a = sparseIntArray;
            sparseIntArray.append(R$styleable.Transform_android_rotation, 1);
            f5822a.append(R$styleable.Transform_android_rotationX, 2);
            f5822a.append(R$styleable.Transform_android_rotationY, 3);
            f5822a.append(R$styleable.Transform_android_scaleX, 4);
            f5822a.append(R$styleable.Transform_android_scaleY, 5);
            f5822a.append(R$styleable.Transform_android_transformPivotX, 6);
            f5822a.append(R$styleable.Transform_android_transformPivotY, 7);
            f5822a.append(R$styleable.Transform_android_translationX, 8);
            f5822a.append(R$styleable.Transform_android_translationY, 9);
            f5822a.append(R$styleable.Transform_android_translationZ, 10);
            f5822a.append(R$styleable.Transform_android_elevation, 11);
        }

        public void a(e eVar) {
            this.f5823b = eVar.f5823b;
            this.f5824c = eVar.f5824c;
            this.f5825d = eVar.f5825d;
            this.f5826e = eVar.f5826e;
            this.f5827f = eVar.f5827f;
            this.f5828g = eVar.f5828g;
            this.f5829h = eVar.f5829h;
            this.f5830i = eVar.f5830i;
            this.f5831j = eVar.f5831j;
            this.f5832k = eVar.f5832k;
            this.f5833l = eVar.f5833l;
            this.f5834m = eVar.f5834m;
            this.f5835n = eVar.f5835n;
        }

        public void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Transform);
            this.f5823b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                switch (f5822a.get(index)) {
                    case 1:
                        this.f5824c = typedArrayObtainStyledAttributes.getFloat(index, this.f5824c);
                        break;
                    case 2:
                        this.f5825d = typedArrayObtainStyledAttributes.getFloat(index, this.f5825d);
                        break;
                    case 3:
                        this.f5826e = typedArrayObtainStyledAttributes.getFloat(index, this.f5826e);
                        break;
                    case 4:
                        this.f5827f = typedArrayObtainStyledAttributes.getFloat(index, this.f5827f);
                        break;
                    case 5:
                        this.f5828g = typedArrayObtainStyledAttributes.getFloat(index, this.f5828g);
                        break;
                    case 6:
                        this.f5829h = typedArrayObtainStyledAttributes.getDimension(index, this.f5829h);
                        break;
                    case 7:
                        this.f5830i = typedArrayObtainStyledAttributes.getDimension(index, this.f5830i);
                        break;
                    case 8:
                        this.f5831j = typedArrayObtainStyledAttributes.getDimension(index, this.f5831j);
                        break;
                    case 9:
                        this.f5832k = typedArrayObtainStyledAttributes.getDimension(index, this.f5832k);
                        break;
                    case 10:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.f5833l = typedArrayObtainStyledAttributes.getDimension(index, this.f5833l);
                        }
                        break;
                    case 11:
                        if (Build.VERSION.SDK_INT >= 21) {
                            this.f5834m = true;
                            this.f5835n = typedArrayObtainStyledAttributes.getDimension(index, this.f5835n);
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f5761b = sparseIntArray;
        sparseIntArray.append(R$styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        f5761b.append(R$styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        f5761b.append(R$styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        f5761b.append(R$styleable.Constraint_layout_constraintRight_toRightOf, 30);
        f5761b.append(R$styleable.Constraint_layout_constraintTop_toTopOf, 36);
        f5761b.append(R$styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        f5761b.append(R$styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        f5761b.append(R$styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        f5761b.append(R$styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        f5761b.append(R$styleable.Constraint_layout_editor_absoluteX, 6);
        f5761b.append(R$styleable.Constraint_layout_editor_absoluteY, 7);
        f5761b.append(R$styleable.Constraint_layout_constraintGuide_begin, 17);
        f5761b.append(R$styleable.Constraint_layout_constraintGuide_end, 18);
        f5761b.append(R$styleable.Constraint_layout_constraintGuide_percent, 19);
        f5761b.append(R$styleable.Constraint_android_orientation, 27);
        f5761b.append(R$styleable.Constraint_layout_constraintStart_toEndOf, 32);
        f5761b.append(R$styleable.Constraint_layout_constraintStart_toStartOf, 33);
        f5761b.append(R$styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        f5761b.append(R$styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        f5761b.append(R$styleable.Constraint_layout_goneMarginLeft, 13);
        f5761b.append(R$styleable.Constraint_layout_goneMarginTop, 16);
        f5761b.append(R$styleable.Constraint_layout_goneMarginRight, 14);
        f5761b.append(R$styleable.Constraint_layout_goneMarginBottom, 11);
        f5761b.append(R$styleable.Constraint_layout_goneMarginStart, 15);
        f5761b.append(R$styleable.Constraint_layout_goneMarginEnd, 12);
        f5761b.append(R$styleable.Constraint_layout_constraintVertical_weight, 40);
        f5761b.append(R$styleable.Constraint_layout_constraintHorizontal_weight, 39);
        f5761b.append(R$styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        f5761b.append(R$styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        f5761b.append(R$styleable.Constraint_layout_constraintHorizontal_bias, 20);
        f5761b.append(R$styleable.Constraint_layout_constraintVertical_bias, 37);
        f5761b.append(R$styleable.Constraint_layout_constraintDimensionRatio, 5);
        f5761b.append(R$styleable.Constraint_layout_constraintLeft_creator, 82);
        f5761b.append(R$styleable.Constraint_layout_constraintTop_creator, 82);
        f5761b.append(R$styleable.Constraint_layout_constraintRight_creator, 82);
        f5761b.append(R$styleable.Constraint_layout_constraintBottom_creator, 82);
        f5761b.append(R$styleable.Constraint_layout_constraintBaseline_creator, 82);
        f5761b.append(R$styleable.Constraint_android_layout_marginLeft, 24);
        f5761b.append(R$styleable.Constraint_android_layout_marginRight, 28);
        f5761b.append(R$styleable.Constraint_android_layout_marginStart, 31);
        f5761b.append(R$styleable.Constraint_android_layout_marginEnd, 8);
        f5761b.append(R$styleable.Constraint_android_layout_marginTop, 34);
        f5761b.append(R$styleable.Constraint_android_layout_marginBottom, 2);
        f5761b.append(R$styleable.Constraint_android_layout_width, 23);
        f5761b.append(R$styleable.Constraint_android_layout_height, 21);
        f5761b.append(R$styleable.Constraint_android_visibility, 22);
        f5761b.append(R$styleable.Constraint_android_alpha, 43);
        f5761b.append(R$styleable.Constraint_android_elevation, 44);
        f5761b.append(R$styleable.Constraint_android_rotationX, 45);
        f5761b.append(R$styleable.Constraint_android_rotationY, 46);
        f5761b.append(R$styleable.Constraint_android_rotation, 60);
        f5761b.append(R$styleable.Constraint_android_scaleX, 47);
        f5761b.append(R$styleable.Constraint_android_scaleY, 48);
        f5761b.append(R$styleable.Constraint_android_transformPivotX, 49);
        f5761b.append(R$styleable.Constraint_android_transformPivotY, 50);
        f5761b.append(R$styleable.Constraint_android_translationX, 51);
        f5761b.append(R$styleable.Constraint_android_translationY, 52);
        f5761b.append(R$styleable.Constraint_android_translationZ, 53);
        f5761b.append(R$styleable.Constraint_layout_constraintWidth_default, 54);
        f5761b.append(R$styleable.Constraint_layout_constraintHeight_default, 55);
        f5761b.append(R$styleable.Constraint_layout_constraintWidth_max, 56);
        f5761b.append(R$styleable.Constraint_layout_constraintHeight_max, 57);
        f5761b.append(R$styleable.Constraint_layout_constraintWidth_min, 58);
        f5761b.append(R$styleable.Constraint_layout_constraintHeight_min, 59);
        f5761b.append(R$styleable.Constraint_layout_constraintCircle, 61);
        f5761b.append(R$styleable.Constraint_layout_constraintCircleRadius, 62);
        f5761b.append(R$styleable.Constraint_layout_constraintCircleAngle, 63);
        f5761b.append(R$styleable.Constraint_animate_relativeTo, 64);
        f5761b.append(R$styleable.Constraint_transitionEasing, 65);
        f5761b.append(R$styleable.Constraint_drawPath, 66);
        f5761b.append(R$styleable.Constraint_transitionPathRotate, 67);
        f5761b.append(R$styleable.Constraint_motionStagger, 79);
        f5761b.append(R$styleable.Constraint_android_id, 38);
        f5761b.append(R$styleable.Constraint_motionProgress, 68);
        f5761b.append(R$styleable.Constraint_layout_constraintWidth_percent, 69);
        f5761b.append(R$styleable.Constraint_layout_constraintHeight_percent, 70);
        f5761b.append(R$styleable.Constraint_chainUseRtl, 71);
        f5761b.append(R$styleable.Constraint_barrierDirection, 72);
        f5761b.append(R$styleable.Constraint_barrierMargin, 73);
        f5761b.append(R$styleable.Constraint_constraint_referenced_ids, 74);
        f5761b.append(R$styleable.Constraint_barrierAllowsGoneWidgets, 75);
        f5761b.append(R$styleable.Constraint_pathMotionArc, 76);
        f5761b.append(R$styleable.Constraint_layout_constraintTag, 77);
        f5761b.append(R$styleable.Constraint_visibilityMode, 78);
        f5761b.append(R$styleable.Constraint_layout_constrainedWidth, 80);
        f5761b.append(R$styleable.Constraint_layout_constrainedHeight, 81);
    }

    public static int w(TypedArray typedArray, int i3, int i4) {
        int resourceId = typedArray.getResourceId(i3, i4);
        return resourceId == -1 ? typedArray.getInt(i3, -1) : resourceId;
    }

    public void A(boolean z3) {
        this.f5764e = z3;
    }

    public void B(boolean z3) {
        this.f5762c = z3;
    }

    public void c(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = constraintLayout.getChildAt(i3);
            int id = childAt.getId();
            if (!this.f5765f.containsKey(Integer.valueOf(id))) {
                Log.v("ConstraintSet", "id unknown " + u.a.c(childAt));
            } else {
                if (this.f5764e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (this.f5765f.containsKey(Integer.valueOf(id))) {
                    ConstraintAttribute.h(childAt, this.f5765f.get(Integer.valueOf(id)).f5771f);
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
        if (this.f5765f.containsKey(Integer.valueOf(id))) {
            a aVar = this.f5765f.get(Integer.valueOf(id));
            if (constraintWidget instanceof h) {
                constraintHelper.n(aVar, (h) constraintWidget, bVar, sparseArray);
            }
        }
    }

    public void f(ConstraintLayout constraintLayout, boolean z3) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.f5765f.keySet());
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = constraintLayout.getChildAt(i3);
            int id = childAt.getId();
            if (!this.f5765f.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + u.a.c(childAt));
            } else {
                if (this.f5764e && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.f5765f.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = this.f5765f.get(Integer.valueOf(id));
                        if (childAt instanceof Barrier) {
                            aVar.f5769d.f5781e0 = 1;
                        }
                        int i4 = aVar.f5769d.f5781e0;
                        if (i4 != -1 && i4 == 1) {
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(aVar.f5769d.f5777c0);
                            barrier.setMargin(aVar.f5769d.f5779d0);
                            barrier.setAllowsGoneWidget(aVar.f5769d.f5793k0);
                            C0074b c0074b = aVar.f5769d;
                            int[] iArr = c0074b.f5783f0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = c0074b.f5785g0;
                                if (str != null) {
                                    c0074b.f5783f0 = k(barrier, str);
                                    barrier.setReferencedIds(aVar.f5769d.f5783f0);
                                }
                            }
                        }
                        ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
                        bVar.c();
                        aVar.d(bVar);
                        if (z3) {
                            ConstraintAttribute.h(childAt, aVar.f5771f);
                        }
                        childAt.setLayoutParams(bVar);
                        d dVar = aVar.f5767b;
                        if (dVar.f5819c == 0) {
                            childAt.setVisibility(dVar.f5818b);
                        }
                        int i5 = Build.VERSION.SDK_INT;
                        if (i5 >= 17) {
                            childAt.setAlpha(aVar.f5767b.f5820d);
                            childAt.setRotation(aVar.f5770e.f5824c);
                            childAt.setRotationX(aVar.f5770e.f5825d);
                            childAt.setRotationY(aVar.f5770e.f5826e);
                            childAt.setScaleX(aVar.f5770e.f5827f);
                            childAt.setScaleY(aVar.f5770e.f5828g);
                            if (!Float.isNaN(aVar.f5770e.f5829h)) {
                                childAt.setPivotX(aVar.f5770e.f5829h);
                            }
                            if (!Float.isNaN(aVar.f5770e.f5830i)) {
                                childAt.setPivotY(aVar.f5770e.f5830i);
                            }
                            childAt.setTranslationX(aVar.f5770e.f5831j);
                            childAt.setTranslationY(aVar.f5770e.f5832k);
                            if (i5 >= 21) {
                                childAt.setTranslationZ(aVar.f5770e.f5833l);
                                e eVar = aVar.f5770e;
                                if (eVar.f5834m) {
                                    childAt.setElevation(eVar.f5835n);
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
            a aVar2 = this.f5765f.get(num);
            int i6 = aVar2.f5769d.f5781e0;
            if (i6 != -1 && i6 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                C0074b c0074b2 = aVar2.f5769d;
                int[] iArr2 = c0074b2.f5783f0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = c0074b2.f5785g0;
                    if (str2 != null) {
                        c0074b2.f5783f0 = k(barrier2, str2);
                        barrier2.setReferencedIds(aVar2.f5769d.f5783f0);
                    }
                }
                barrier2.setType(aVar2.f5769d.f5777c0);
                barrier2.setMargin(aVar2.f5769d.f5779d0);
                ConstraintLayout.b bVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.u();
                aVar2.d(bVarGenerateDefaultLayoutParams);
                constraintLayout.addView(barrier2, bVarGenerateDefaultLayoutParams);
            }
            if (aVar2.f5769d.f5774b) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.b bVarGenerateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                aVar2.d(bVarGenerateDefaultLayoutParams2);
                constraintLayout.addView(guideline, bVarGenerateDefaultLayoutParams2);
            }
        }
    }

    public void g(int i3, ConstraintLayout.b bVar) {
        if (this.f5765f.containsKey(Integer.valueOf(i3))) {
            this.f5765f.get(Integer.valueOf(i3)).d(bVar);
        }
    }

    public void h(Context context, int i3) {
        i((ConstraintLayout) LayoutInflater.from(context).inflate(i3, (ViewGroup) null));
    }

    public void i(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.f5765f.clear();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = constraintLayout.getChildAt(i3);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f5764e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f5765f.containsKey(Integer.valueOf(id))) {
                this.f5765f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f5765f.get(Integer.valueOf(id));
            aVar.f5771f = ConstraintAttribute.b(this.f5763d, childAt);
            aVar.f(id, bVar);
            aVar.f5767b.f5818b = childAt.getVisibility();
            int i4 = Build.VERSION.SDK_INT;
            if (i4 >= 17) {
                aVar.f5767b.f5820d = childAt.getAlpha();
                aVar.f5770e.f5824c = childAt.getRotation();
                aVar.f5770e.f5825d = childAt.getRotationX();
                aVar.f5770e.f5826e = childAt.getRotationY();
                aVar.f5770e.f5827f = childAt.getScaleX();
                aVar.f5770e.f5828g = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    e eVar = aVar.f5770e;
                    eVar.f5829h = pivotX;
                    eVar.f5830i = pivotY;
                }
                aVar.f5770e.f5831j = childAt.getTranslationX();
                aVar.f5770e.f5832k = childAt.getTranslationY();
                if (i4 >= 21) {
                    aVar.f5770e.f5833l = childAt.getTranslationZ();
                    e eVar2 = aVar.f5770e;
                    if (eVar2.f5834m) {
                        eVar2.f5835n = childAt.getElevation();
                    }
                }
            }
            if (childAt instanceof Barrier) {
                Barrier barrier = (Barrier) childAt;
                aVar.f5769d.f5793k0 = barrier.v();
                aVar.f5769d.f5783f0 = barrier.getReferencedIds();
                aVar.f5769d.f5777c0 = barrier.getType();
                aVar.f5769d.f5779d0 = barrier.getMargin();
            }
        }
    }

    public void j(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.f5765f.clear();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = constraints.getChildAt(i3);
            Constraints.a aVar = (Constraints.a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f5764e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f5765f.containsKey(Integer.valueOf(id))) {
                this.f5765f.put(Integer.valueOf(id), new a());
            }
            a aVar2 = this.f5765f.get(Integer.valueOf(id));
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
        int i3 = 0;
        int i4 = 0;
        while (i3 < strArrSplit.length) {
            String strTrim = strArrSplit[i3].trim();
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
            iArr[i4] = iIntValue;
            i3++;
            i4++;
        }
        return i4 != strArrSplit.length ? Arrays.copyOf(iArr, i4) : iArr;
    }

    public final a l(Context context, AttributeSet attributeSet) {
        a aVar = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Constraint);
        x(context, aVar, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    public final a m(int i3) {
        if (!this.f5765f.containsKey(Integer.valueOf(i3))) {
            this.f5765f.put(Integer.valueOf(i3), new a());
        }
        return this.f5765f.get(Integer.valueOf(i3));
    }

    public a n(int i3) {
        if (this.f5765f.containsKey(Integer.valueOf(i3))) {
            return this.f5765f.get(Integer.valueOf(i3));
        }
        return null;
    }

    public int o(int i3) {
        return m(i3).f5769d.f5780e;
    }

    public int[] p() {
        Integer[] numArr = (Integer[]) this.f5765f.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = numArr[i3].intValue();
        }
        return iArr;
    }

    public a q(int i3) {
        return m(i3);
    }

    public int r(int i3) {
        return m(i3).f5767b.f5818b;
    }

    public int s(int i3) {
        return m(i3).f5767b.f5819c;
    }

    public int t(int i3) {
        return m(i3).f5769d.f5778d;
    }

    public void u(Context context, int i3) {
        XmlResourceParser xml = context.getResources().getXml(i3);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    a aVarL = l(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        aVarL.f5769d.f5774b = true;
                    }
                    this.f5765f.put(Integer.valueOf(aVarL.f5766a), aVarL);
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (XmlPullParserException e4) {
            e4.printStackTrace();
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
        throw new UnsupportedOperationException("Method not decompiled: y.b.v(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public final void x(Context context, a aVar, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = typedArray.getIndex(i3);
            if (index != R$styleable.Constraint_android_id && R$styleable.Constraint_android_layout_marginStart != index && R$styleable.Constraint_android_layout_marginEnd != index) {
                aVar.f5768c.f5810b = true;
                aVar.f5769d.f5776c = true;
                aVar.f5767b.f5817a = true;
                aVar.f5770e.f5823b = true;
            }
            switch (f5761b.get(index)) {
                case 1:
                    C0074b c0074b = aVar.f5769d;
                    c0074b.f5799q = w(typedArray, index, c0074b.f5799q);
                    break;
                case 2:
                    C0074b c0074b2 = aVar.f5769d;
                    c0074b2.H = typedArray.getDimensionPixelSize(index, c0074b2.H);
                    break;
                case 3:
                    C0074b c0074b3 = aVar.f5769d;
                    c0074b3.f5798p = w(typedArray, index, c0074b3.f5798p);
                    break;
                case 4:
                    C0074b c0074b4 = aVar.f5769d;
                    c0074b4.f5797o = w(typedArray, index, c0074b4.f5797o);
                    break;
                case 5:
                    aVar.f5769d.f5806x = typedArray.getString(index);
                    break;
                case 6:
                    C0074b c0074b5 = aVar.f5769d;
                    c0074b5.B = typedArray.getDimensionPixelOffset(index, c0074b5.B);
                    break;
                case 7:
                    C0074b c0074b6 = aVar.f5769d;
                    c0074b6.C = typedArray.getDimensionPixelOffset(index, c0074b6.C);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0074b c0074b7 = aVar.f5769d;
                        c0074b7.I = typedArray.getDimensionPixelSize(index, c0074b7.I);
                    }
                    break;
                case 9:
                    C0074b c0074b8 = aVar.f5769d;
                    c0074b8.f5803u = w(typedArray, index, c0074b8.f5803u);
                    break;
                case 10:
                    C0074b c0074b9 = aVar.f5769d;
                    c0074b9.f5802t = w(typedArray, index, c0074b9.f5802t);
                    break;
                case 11:
                    C0074b c0074b10 = aVar.f5769d;
                    c0074b10.N = typedArray.getDimensionPixelSize(index, c0074b10.N);
                    break;
                case 12:
                    C0074b c0074b11 = aVar.f5769d;
                    c0074b11.O = typedArray.getDimensionPixelSize(index, c0074b11.O);
                    break;
                case 13:
                    C0074b c0074b12 = aVar.f5769d;
                    c0074b12.K = typedArray.getDimensionPixelSize(index, c0074b12.K);
                    break;
                case 14:
                    C0074b c0074b13 = aVar.f5769d;
                    c0074b13.M = typedArray.getDimensionPixelSize(index, c0074b13.M);
                    break;
                case 15:
                    C0074b c0074b14 = aVar.f5769d;
                    c0074b14.P = typedArray.getDimensionPixelSize(index, c0074b14.P);
                    break;
                case 16:
                    C0074b c0074b15 = aVar.f5769d;
                    c0074b15.L = typedArray.getDimensionPixelSize(index, c0074b15.L);
                    break;
                case 17:
                    C0074b c0074b16 = aVar.f5769d;
                    c0074b16.f5782f = typedArray.getDimensionPixelOffset(index, c0074b16.f5782f);
                    break;
                case 18:
                    C0074b c0074b17 = aVar.f5769d;
                    c0074b17.f5784g = typedArray.getDimensionPixelOffset(index, c0074b17.f5784g);
                    break;
                case 19:
                    C0074b c0074b18 = aVar.f5769d;
                    c0074b18.f5786h = typedArray.getFloat(index, c0074b18.f5786h);
                    break;
                case 20:
                    C0074b c0074b19 = aVar.f5769d;
                    c0074b19.f5804v = typedArray.getFloat(index, c0074b19.f5804v);
                    break;
                case 21:
                    C0074b c0074b20 = aVar.f5769d;
                    c0074b20.f5780e = typedArray.getLayoutDimension(index, c0074b20.f5780e);
                    break;
                case 22:
                    d dVar = aVar.f5767b;
                    dVar.f5818b = typedArray.getInt(index, dVar.f5818b);
                    d dVar2 = aVar.f5767b;
                    dVar2.f5818b = f5760a[dVar2.f5818b];
                    break;
                case 23:
                    C0074b c0074b21 = aVar.f5769d;
                    c0074b21.f5778d = typedArray.getLayoutDimension(index, c0074b21.f5778d);
                    break;
                case 24:
                    C0074b c0074b22 = aVar.f5769d;
                    c0074b22.E = typedArray.getDimensionPixelSize(index, c0074b22.E);
                    break;
                case 25:
                    C0074b c0074b23 = aVar.f5769d;
                    c0074b23.f5788i = w(typedArray, index, c0074b23.f5788i);
                    break;
                case 26:
                    C0074b c0074b24 = aVar.f5769d;
                    c0074b24.f5790j = w(typedArray, index, c0074b24.f5790j);
                    break;
                case 27:
                    C0074b c0074b25 = aVar.f5769d;
                    c0074b25.D = typedArray.getInt(index, c0074b25.D);
                    break;
                case 28:
                    C0074b c0074b26 = aVar.f5769d;
                    c0074b26.F = typedArray.getDimensionPixelSize(index, c0074b26.F);
                    break;
                case 29:
                    C0074b c0074b27 = aVar.f5769d;
                    c0074b27.f5792k = w(typedArray, index, c0074b27.f5792k);
                    break;
                case 30:
                    C0074b c0074b28 = aVar.f5769d;
                    c0074b28.f5794l = w(typedArray, index, c0074b28.f5794l);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT >= 17) {
                        C0074b c0074b29 = aVar.f5769d;
                        c0074b29.J = typedArray.getDimensionPixelSize(index, c0074b29.J);
                    }
                    break;
                case 32:
                    C0074b c0074b30 = aVar.f5769d;
                    c0074b30.f5800r = w(typedArray, index, c0074b30.f5800r);
                    break;
                case 33:
                    C0074b c0074b31 = aVar.f5769d;
                    c0074b31.f5801s = w(typedArray, index, c0074b31.f5801s);
                    break;
                case 34:
                    C0074b c0074b32 = aVar.f5769d;
                    c0074b32.G = typedArray.getDimensionPixelSize(index, c0074b32.G);
                    break;
                case 35:
                    C0074b c0074b33 = aVar.f5769d;
                    c0074b33.f5796n = w(typedArray, index, c0074b33.f5796n);
                    break;
                case 36:
                    C0074b c0074b34 = aVar.f5769d;
                    c0074b34.f5795m = w(typedArray, index, c0074b34.f5795m);
                    break;
                case 37:
                    C0074b c0074b35 = aVar.f5769d;
                    c0074b35.f5805w = typedArray.getFloat(index, c0074b35.f5805w);
                    break;
                case 38:
                    aVar.f5766a = typedArray.getResourceId(index, aVar.f5766a);
                    break;
                case 39:
                    C0074b c0074b36 = aVar.f5769d;
                    c0074b36.R = typedArray.getFloat(index, c0074b36.R);
                    break;
                case 40:
                    C0074b c0074b37 = aVar.f5769d;
                    c0074b37.Q = typedArray.getFloat(index, c0074b37.Q);
                    break;
                case 41:
                    C0074b c0074b38 = aVar.f5769d;
                    c0074b38.S = typedArray.getInt(index, c0074b38.S);
                    break;
                case 42:
                    C0074b c0074b39 = aVar.f5769d;
                    c0074b39.T = typedArray.getInt(index, c0074b39.T);
                    break;
                case 43:
                    d dVar3 = aVar.f5767b;
                    dVar3.f5820d = typedArray.getFloat(index, dVar3.f5820d);
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar = aVar.f5770e;
                        eVar.f5834m = true;
                        eVar.f5835n = typedArray.getDimension(index, eVar.f5835n);
                    }
                    break;
                case 45:
                    e eVar2 = aVar.f5770e;
                    eVar2.f5825d = typedArray.getFloat(index, eVar2.f5825d);
                    break;
                case 46:
                    e eVar3 = aVar.f5770e;
                    eVar3.f5826e = typedArray.getFloat(index, eVar3.f5826e);
                    break;
                case 47:
                    e eVar4 = aVar.f5770e;
                    eVar4.f5827f = typedArray.getFloat(index, eVar4.f5827f);
                    break;
                case 48:
                    e eVar5 = aVar.f5770e;
                    eVar5.f5828g = typedArray.getFloat(index, eVar5.f5828g);
                    break;
                case 49:
                    e eVar6 = aVar.f5770e;
                    eVar6.f5829h = typedArray.getDimension(index, eVar6.f5829h);
                    break;
                case 50:
                    e eVar7 = aVar.f5770e;
                    eVar7.f5830i = typedArray.getDimension(index, eVar7.f5830i);
                    break;
                case 51:
                    e eVar8 = aVar.f5770e;
                    eVar8.f5831j = typedArray.getDimension(index, eVar8.f5831j);
                    break;
                case 52:
                    e eVar9 = aVar.f5770e;
                    eVar9.f5832k = typedArray.getDimension(index, eVar9.f5832k);
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT >= 21) {
                        e eVar10 = aVar.f5770e;
                        eVar10.f5833l = typedArray.getDimension(index, eVar10.f5833l);
                    }
                    break;
                case 54:
                    C0074b c0074b40 = aVar.f5769d;
                    c0074b40.U = typedArray.getInt(index, c0074b40.U);
                    break;
                case 55:
                    C0074b c0074b41 = aVar.f5769d;
                    c0074b41.V = typedArray.getInt(index, c0074b41.V);
                    break;
                case 56:
                    C0074b c0074b42 = aVar.f5769d;
                    c0074b42.W = typedArray.getDimensionPixelSize(index, c0074b42.W);
                    break;
                case 57:
                    C0074b c0074b43 = aVar.f5769d;
                    c0074b43.X = typedArray.getDimensionPixelSize(index, c0074b43.X);
                    break;
                case 58:
                    C0074b c0074b44 = aVar.f5769d;
                    c0074b44.Y = typedArray.getDimensionPixelSize(index, c0074b44.Y);
                    break;
                case 59:
                    C0074b c0074b45 = aVar.f5769d;
                    c0074b45.Z = typedArray.getDimensionPixelSize(index, c0074b45.Z);
                    break;
                case 60:
                    e eVar11 = aVar.f5770e;
                    eVar11.f5824c = typedArray.getFloat(index, eVar11.f5824c);
                    break;
                case 61:
                    C0074b c0074b46 = aVar.f5769d;
                    c0074b46.f5807y = w(typedArray, index, c0074b46.f5807y);
                    break;
                case 62:
                    C0074b c0074b47 = aVar.f5769d;
                    c0074b47.f5808z = typedArray.getDimensionPixelSize(index, c0074b47.f5808z);
                    break;
                case 63:
                    C0074b c0074b48 = aVar.f5769d;
                    c0074b48.A = typedArray.getFloat(index, c0074b48.A);
                    break;
                case 64:
                    c cVar = aVar.f5768c;
                    cVar.f5811c = w(typedArray, index, cVar.f5811c);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.f5768c.f5812d = typedArray.getString(index);
                    } else {
                        aVar.f5768c.f5812d = t.c.f4950b[typedArray.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    aVar.f5768c.f5814f = typedArray.getInt(index, 0);
                    break;
                case 67:
                    c cVar2 = aVar.f5768c;
                    cVar2.f5816h = typedArray.getFloat(index, cVar2.f5816h);
                    break;
                case 68:
                    d dVar4 = aVar.f5767b;
                    dVar4.f5821e = typedArray.getFloat(index, dVar4.f5821e);
                    break;
                case 69:
                    aVar.f5769d.f5773a0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    aVar.f5769d.f5775b0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    C0074b c0074b49 = aVar.f5769d;
                    c0074b49.f5777c0 = typedArray.getInt(index, c0074b49.f5777c0);
                    break;
                case 73:
                    C0074b c0074b50 = aVar.f5769d;
                    c0074b50.f5779d0 = typedArray.getDimensionPixelSize(index, c0074b50.f5779d0);
                    break;
                case 74:
                    aVar.f5769d.f5785g0 = typedArray.getString(index);
                    break;
                case 75:
                    C0074b c0074b51 = aVar.f5769d;
                    c0074b51.f5793k0 = typedArray.getBoolean(index, c0074b51.f5793k0);
                    break;
                case 76:
                    c cVar3 = aVar.f5768c;
                    cVar3.f5813e = typedArray.getInt(index, cVar3.f5813e);
                    break;
                case 77:
                    aVar.f5769d.f5787h0 = typedArray.getString(index);
                    break;
                case 78:
                    d dVar5 = aVar.f5767b;
                    dVar5.f5819c = typedArray.getInt(index, dVar5.f5819c);
                    break;
                case 79:
                    c cVar4 = aVar.f5768c;
                    cVar4.f5815g = typedArray.getFloat(index, cVar4.f5815g);
                    break;
                case 80:
                    C0074b c0074b52 = aVar.f5769d;
                    c0074b52.f5789i0 = typedArray.getBoolean(index, c0074b52.f5789i0);
                    break;
                case 81:
                    C0074b c0074b53 = aVar.f5769d;
                    c0074b53.f5791j0 = typedArray.getBoolean(index, c0074b53.f5791j0);
                    break;
                case 82:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f5761b.get(index));
                    break;
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f5761b.get(index));
                    break;
            }
        }
    }

    public void y(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = constraintLayout.getChildAt(i3);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f5764e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f5765f.containsKey(Integer.valueOf(id))) {
                this.f5765f.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f5765f.get(Integer.valueOf(id));
            if (!aVar.f5769d.f5776c) {
                aVar.f(id, bVar);
                if (childAt instanceof ConstraintHelper) {
                    aVar.f5769d.f5783f0 = ((ConstraintHelper) childAt).getReferencedIds();
                    if (childAt instanceof Barrier) {
                        Barrier barrier = (Barrier) childAt;
                        aVar.f5769d.f5793k0 = barrier.v();
                        aVar.f5769d.f5777c0 = barrier.getType();
                        aVar.f5769d.f5779d0 = barrier.getMargin();
                    }
                }
                aVar.f5769d.f5776c = true;
            }
            d dVar = aVar.f5767b;
            if (!dVar.f5817a) {
                dVar.f5818b = childAt.getVisibility();
                aVar.f5767b.f5820d = childAt.getAlpha();
                aVar.f5767b.f5817a = true;
            }
            int i4 = Build.VERSION.SDK_INT;
            if (i4 >= 17) {
                e eVar = aVar.f5770e;
                if (!eVar.f5823b) {
                    eVar.f5823b = true;
                    eVar.f5824c = childAt.getRotation();
                    aVar.f5770e.f5825d = childAt.getRotationX();
                    aVar.f5770e.f5826e = childAt.getRotationY();
                    aVar.f5770e.f5827f = childAt.getScaleX();
                    aVar.f5770e.f5828g = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        e eVar2 = aVar.f5770e;
                        eVar2.f5829h = pivotX;
                        eVar2.f5830i = pivotY;
                    }
                    aVar.f5770e.f5831j = childAt.getTranslationX();
                    aVar.f5770e.f5832k = childAt.getTranslationY();
                    if (i4 >= 21) {
                        aVar.f5770e.f5833l = childAt.getTranslationZ();
                        e eVar3 = aVar.f5770e;
                        if (eVar3.f5834m) {
                            eVar3.f5835n = childAt.getElevation();
                        }
                    }
                }
            }
        }
    }

    public void z(b bVar) {
        for (Integer num : bVar.f5765f.keySet()) {
            int iIntValue = num.intValue();
            a aVar = bVar.f5765f.get(num);
            if (!this.f5765f.containsKey(Integer.valueOf(iIntValue))) {
                this.f5765f.put(Integer.valueOf(iIntValue), new a());
            }
            a aVar2 = this.f5765f.get(Integer.valueOf(iIntValue));
            C0074b c0074b = aVar2.f5769d;
            if (!c0074b.f5776c) {
                c0074b.a(aVar.f5769d);
            }
            d dVar = aVar2.f5767b;
            if (!dVar.f5817a) {
                dVar.a(aVar.f5767b);
            }
            e eVar = aVar2.f5770e;
            if (!eVar.f5823b) {
                eVar.a(aVar.f5770e);
            }
            c cVar = aVar2.f5768c;
            if (!cVar.f5810b) {
                cVar.a(aVar.f5768c);
            }
            for (String str : aVar.f5771f.keySet()) {
                if (!aVar2.f5771f.containsKey(str)) {
                    aVar2.f5771f.put(str, aVar.f5771f.get(str));
                }
            }
        }
    }
}
