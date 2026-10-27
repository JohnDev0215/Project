package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.e;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintSet.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int[] f1765h = {0, 4, 8};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static SparseIntArray f1766i = new SparseIntArray();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static SparseIntArray f1767j = new SparseIntArray();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1768a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f1769b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f1770c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1771d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private HashMap<String, androidx.constraintlayout.widget.a> f1772e = new HashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f1773f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private HashMap<Integer, a> f1774g = new HashMap<>();

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1775a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f1776b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final C0018d f1777c = new C0018d();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final c f1778d = new c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final b f1779e = new b();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final e f1780f = new e();

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public HashMap<String, androidx.constraintlayout.widget.a> f1781g = new HashMap<>();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        C0017a f1782h;

        /* JADX INFO: renamed from: androidx.constraintlayout.widget.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ConstraintSet.java */
        static class C0017a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            int[] f1783a = new int[10];

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            int[] f1784b = new int[10];

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            int f1785c = 0;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            int[] f1786d = new int[10];

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            float[] f1787e = new float[10];

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            int f1788f = 0;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            int[] f1789g = new int[5];

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            String[] f1790h = new String[5];

            /* JADX INFO: renamed from: i, reason: collision with root package name */
            int f1791i = 0;

            /* JADX INFO: renamed from: j, reason: collision with root package name */
            int[] f1792j = new int[4];

            /* JADX INFO: renamed from: k, reason: collision with root package name */
            boolean[] f1793k = new boolean[4];

            /* JADX INFO: renamed from: l, reason: collision with root package name */
            int f1794l = 0;

            C0017a() {
            }

            void a(int i7, float f7) {
                int i8 = this.f1788f;
                int[] iArr = this.f1786d;
                if (i8 >= iArr.length) {
                    this.f1786d = Arrays.copyOf(iArr, iArr.length * 2);
                    float[] fArr = this.f1787e;
                    this.f1787e = Arrays.copyOf(fArr, fArr.length * 2);
                }
                int[] iArr2 = this.f1786d;
                int i9 = this.f1788f;
                iArr2[i9] = i7;
                float[] fArr2 = this.f1787e;
                this.f1788f = i9 + 1;
                fArr2[i9] = f7;
            }

            void b(int i7, int i8) {
                int i9 = this.f1785c;
                int[] iArr = this.f1783a;
                if (i9 >= iArr.length) {
                    this.f1783a = Arrays.copyOf(iArr, iArr.length * 2);
                    int[] iArr2 = this.f1784b;
                    this.f1784b = Arrays.copyOf(iArr2, iArr2.length * 2);
                }
                int[] iArr3 = this.f1783a;
                int i10 = this.f1785c;
                iArr3[i10] = i7;
                int[] iArr4 = this.f1784b;
                this.f1785c = i10 + 1;
                iArr4[i10] = i8;
            }

            void c(int i7, String str) {
                int i8 = this.f1791i;
                int[] iArr = this.f1789g;
                if (i8 >= iArr.length) {
                    this.f1789g = Arrays.copyOf(iArr, iArr.length * 2);
                    String[] strArr = this.f1790h;
                    this.f1790h = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                }
                int[] iArr2 = this.f1789g;
                int i9 = this.f1791i;
                iArr2[i9] = i7;
                String[] strArr2 = this.f1790h;
                this.f1791i = i9 + 1;
                strArr2[i9] = str;
            }

            void d(int i7, boolean z6) {
                int i8 = this.f1794l;
                int[] iArr = this.f1792j;
                if (i8 >= iArr.length) {
                    this.f1792j = Arrays.copyOf(iArr, iArr.length * 2);
                    boolean[] zArr = this.f1793k;
                    this.f1793k = Arrays.copyOf(zArr, zArr.length * 2);
                }
                int[] iArr2 = this.f1792j;
                int i9 = this.f1794l;
                iArr2[i9] = i7;
                boolean[] zArr2 = this.f1793k;
                this.f1794l = i9 + 1;
                zArr2[i9] = z6;
            }

            void e(a aVar) {
                for (int i7 = 0; i7 < this.f1785c; i7++) {
                    d.O(aVar, this.f1783a[i7], this.f1784b[i7]);
                }
                for (int i8 = 0; i8 < this.f1788f; i8++) {
                    d.N(aVar, this.f1786d[i8], this.f1787e[i8]);
                }
                for (int i9 = 0; i9 < this.f1791i; i9++) {
                    d.P(aVar, this.f1789g[i9], this.f1790h[i9]);
                }
                for (int i10 = 0; i10 < this.f1794l; i10++) {
                    d.Q(aVar, this.f1792j[i10], this.f1793k[i10]);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g(int i7, ConstraintLayout.b bVar) {
            this.f1775a = i7;
            b bVar2 = this.f1779e;
            bVar2.f1814j = bVar.f1680e;
            bVar2.f1816k = bVar.f1682f;
            bVar2.f1818l = bVar.f1684g;
            bVar2.f1820m = bVar.f1686h;
            bVar2.f1822n = bVar.f1688i;
            bVar2.f1824o = bVar.f1690j;
            bVar2.f1826p = bVar.f1692k;
            bVar2.f1828q = bVar.f1694l;
            bVar2.f1830r = bVar.f1696m;
            bVar2.f1831s = bVar.f1698n;
            bVar2.f1832t = bVar.f1700o;
            bVar2.f1833u = bVar.f1708s;
            bVar2.f1834v = bVar.f1710t;
            bVar2.f1835w = bVar.f1712u;
            bVar2.f1836x = bVar.f1714v;
            bVar2.f1837y = bVar.G;
            bVar2.f1838z = bVar.H;
            bVar2.A = bVar.I;
            bVar2.B = bVar.f1702p;
            bVar2.C = bVar.f1704q;
            bVar2.D = bVar.f1706r;
            bVar2.E = bVar.X;
            bVar2.F = bVar.Y;
            bVar2.G = bVar.Z;
            bVar2.f1810h = bVar.f1676c;
            bVar2.f1806f = bVar.f1672a;
            bVar2.f1808g = bVar.f1674b;
            bVar2.f1802d = ((ViewGroup.MarginLayoutParams) bVar).width;
            bVar2.f1804e = ((ViewGroup.MarginLayoutParams) bVar).height;
            bVar2.H = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            bVar2.I = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
            bVar2.J = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            bVar2.K = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
            bVar2.N = bVar.D;
            bVar2.V = bVar.M;
            bVar2.W = bVar.L;
            bVar2.Y = bVar.O;
            bVar2.X = bVar.N;
            bVar2.f1823n0 = bVar.f1673a0;
            bVar2.f1825o0 = bVar.f1675b0;
            bVar2.Z = bVar.P;
            bVar2.f1797a0 = bVar.Q;
            bVar2.f1799b0 = bVar.T;
            bVar2.f1801c0 = bVar.U;
            bVar2.f1803d0 = bVar.R;
            bVar2.f1805e0 = bVar.S;
            bVar2.f1807f0 = bVar.V;
            bVar2.f1809g0 = bVar.W;
            bVar2.f1821m0 = bVar.f1677c0;
            bVar2.P = bVar.f1718x;
            bVar2.R = bVar.f1720z;
            bVar2.O = bVar.f1716w;
            bVar2.Q = bVar.f1719y;
            bVar2.T = bVar.A;
            bVar2.S = bVar.B;
            bVar2.U = bVar.C;
            bVar2.f1829q0 = bVar.f1679d0;
            bVar2.L = bVar.getMarginEnd();
            this.f1779e.M = bVar.getMarginStart();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void h(int i7, e.a aVar) {
            g(i7, aVar);
            this.f1777c.f1857d = aVar.f1875x0;
            e eVar = this.f1780f;
            eVar.f1861b = aVar.A0;
            eVar.f1862c = aVar.B0;
            eVar.f1863d = aVar.C0;
            eVar.f1864e = aVar.D0;
            eVar.f1865f = aVar.E0;
            eVar.f1866g = aVar.F0;
            eVar.f1867h = aVar.G0;
            eVar.f1869j = aVar.H0;
            eVar.f1870k = aVar.I0;
            eVar.f1871l = aVar.J0;
            eVar.f1873n = aVar.f1877z0;
            eVar.f1872m = aVar.f1876y0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void i(androidx.constraintlayout.widget.b bVar, int i7, e.a aVar) {
            h(i7, aVar);
            if (bVar instanceof Barrier) {
                b bVar2 = this.f1779e;
                bVar2.f1815j0 = 1;
                Barrier barrier = (Barrier) bVar;
                bVar2.f1811h0 = barrier.getType();
                this.f1779e.f1817k0 = barrier.getReferencedIds();
                this.f1779e.f1813i0 = barrier.getMargin();
            }
        }

        public void d(a aVar) {
            C0017a c0017a = this.f1782h;
            if (c0017a != null) {
                c0017a.e(aVar);
            }
        }

        public void e(ConstraintLayout.b bVar) {
            b bVar2 = this.f1779e;
            bVar.f1680e = bVar2.f1814j;
            bVar.f1682f = bVar2.f1816k;
            bVar.f1684g = bVar2.f1818l;
            bVar.f1686h = bVar2.f1820m;
            bVar.f1688i = bVar2.f1822n;
            bVar.f1690j = bVar2.f1824o;
            bVar.f1692k = bVar2.f1826p;
            bVar.f1694l = bVar2.f1828q;
            bVar.f1696m = bVar2.f1830r;
            bVar.f1698n = bVar2.f1831s;
            bVar.f1700o = bVar2.f1832t;
            bVar.f1708s = bVar2.f1833u;
            bVar.f1710t = bVar2.f1834v;
            bVar.f1712u = bVar2.f1835w;
            bVar.f1714v = bVar2.f1836x;
            ((ViewGroup.MarginLayoutParams) bVar).leftMargin = bVar2.H;
            ((ViewGroup.MarginLayoutParams) bVar).rightMargin = bVar2.I;
            ((ViewGroup.MarginLayoutParams) bVar).topMargin = bVar2.J;
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = bVar2.K;
            bVar.A = bVar2.T;
            bVar.B = bVar2.S;
            bVar.f1718x = bVar2.P;
            bVar.f1720z = bVar2.R;
            bVar.G = bVar2.f1837y;
            bVar.H = bVar2.f1838z;
            bVar.f1702p = bVar2.B;
            bVar.f1704q = bVar2.C;
            bVar.f1706r = bVar2.D;
            bVar.I = bVar2.A;
            bVar.X = bVar2.E;
            bVar.Y = bVar2.F;
            bVar.M = bVar2.V;
            bVar.L = bVar2.W;
            bVar.O = bVar2.Y;
            bVar.N = bVar2.X;
            bVar.f1673a0 = bVar2.f1823n0;
            bVar.f1675b0 = bVar2.f1825o0;
            bVar.P = bVar2.Z;
            bVar.Q = bVar2.f1797a0;
            bVar.T = bVar2.f1799b0;
            bVar.U = bVar2.f1801c0;
            bVar.R = bVar2.f1803d0;
            bVar.S = bVar2.f1805e0;
            bVar.V = bVar2.f1807f0;
            bVar.W = bVar2.f1809g0;
            bVar.Z = bVar2.G;
            bVar.f1676c = bVar2.f1810h;
            bVar.f1672a = bVar2.f1806f;
            bVar.f1674b = bVar2.f1808g;
            ((ViewGroup.MarginLayoutParams) bVar).width = bVar2.f1802d;
            ((ViewGroup.MarginLayoutParams) bVar).height = bVar2.f1804e;
            String str = bVar2.f1821m0;
            if (str != null) {
                bVar.f1677c0 = str;
            }
            bVar.f1679d0 = bVar2.f1829q0;
            bVar.setMarginStart(bVar2.M);
            bVar.setMarginEnd(this.f1779e.L);
            bVar.b();
        }

        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.f1779e.a(this.f1779e);
            aVar.f1778d.a(this.f1778d);
            aVar.f1777c.a(this.f1777c);
            aVar.f1780f.a(this.f1780f);
            aVar.f1775a = this.f1775a;
            aVar.f1782h = this.f1782h;
            return aVar;
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class b {

        /* JADX INFO: renamed from: r0, reason: collision with root package name */
        private static SparseIntArray f1795r0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1802d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1804e;

        /* JADX INFO: renamed from: k0, reason: collision with root package name */
        public int[] f1817k0;

        /* JADX INFO: renamed from: l0, reason: collision with root package name */
        public String f1819l0;

        /* JADX INFO: renamed from: m0, reason: collision with root package name */
        public String f1821m0;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1796a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1798b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1800c = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1806f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1808g = -1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1810h = -1.0f;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f1812i = true;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f1814j = -1;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f1816k = -1;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f1818l = -1;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f1820m = -1;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f1822n = -1;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f1824o = -1;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f1826p = -1;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f1828q = -1;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f1830r = -1;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f1831s = -1;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f1832t = -1;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public int f1833u = -1;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public int f1834v = -1;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public int f1835w = -1;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public int f1836x = -1;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public float f1837y = 0.5f;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public float f1838z = 0.5f;
        public String A = null;
        public int B = -1;
        public int C = 0;
        public float D = 0.0f;
        public int E = -1;
        public int F = -1;
        public int G = -1;
        public int H = 0;
        public int I = 0;
        public int J = 0;
        public int K = 0;
        public int L = 0;
        public int M = 0;
        public int N = 0;
        public int O = Integer.MIN_VALUE;
        public int P = Integer.MIN_VALUE;
        public int Q = Integer.MIN_VALUE;
        public int R = Integer.MIN_VALUE;
        public int S = Integer.MIN_VALUE;
        public int T = Integer.MIN_VALUE;
        public int U = Integer.MIN_VALUE;
        public float V = -1.0f;
        public float W = -1.0f;
        public int X = 0;
        public int Y = 0;
        public int Z = 0;

        /* JADX INFO: renamed from: a0, reason: collision with root package name */
        public int f1797a0 = 0;

        /* JADX INFO: renamed from: b0, reason: collision with root package name */
        public int f1799b0 = 0;

        /* JADX INFO: renamed from: c0, reason: collision with root package name */
        public int f1801c0 = 0;

        /* JADX INFO: renamed from: d0, reason: collision with root package name */
        public int f1803d0 = 0;

        /* JADX INFO: renamed from: e0, reason: collision with root package name */
        public int f1805e0 = 0;

        /* JADX INFO: renamed from: f0, reason: collision with root package name */
        public float f1807f0 = 1.0f;

        /* JADX INFO: renamed from: g0, reason: collision with root package name */
        public float f1809g0 = 1.0f;

        /* JADX INFO: renamed from: h0, reason: collision with root package name */
        public int f1811h0 = -1;

        /* JADX INFO: renamed from: i0, reason: collision with root package name */
        public int f1813i0 = 0;

        /* JADX INFO: renamed from: j0, reason: collision with root package name */
        public int f1815j0 = -1;

        /* JADX INFO: renamed from: n0, reason: collision with root package name */
        public boolean f1823n0 = false;

        /* JADX INFO: renamed from: o0, reason: collision with root package name */
        public boolean f1825o0 = false;

        /* JADX INFO: renamed from: p0, reason: collision with root package name */
        public boolean f1827p0 = true;

        /* JADX INFO: renamed from: q0, reason: collision with root package name */
        public int f1829q0 = 0;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1795r0 = sparseIntArray;
            sparseIntArray.append(i.Layout_layout_constraintLeft_toLeftOf, 24);
            f1795r0.append(i.Layout_layout_constraintLeft_toRightOf, 25);
            f1795r0.append(i.Layout_layout_constraintRight_toLeftOf, 28);
            f1795r0.append(i.Layout_layout_constraintRight_toRightOf, 29);
            f1795r0.append(i.Layout_layout_constraintTop_toTopOf, 35);
            f1795r0.append(i.Layout_layout_constraintTop_toBottomOf, 34);
            f1795r0.append(i.Layout_layout_constraintBottom_toTopOf, 4);
            f1795r0.append(i.Layout_layout_constraintBottom_toBottomOf, 3);
            f1795r0.append(i.Layout_layout_constraintBaseline_toBaselineOf, 1);
            f1795r0.append(i.Layout_layout_editor_absoluteX, 6);
            f1795r0.append(i.Layout_layout_editor_absoluteY, 7);
            f1795r0.append(i.Layout_layout_constraintGuide_begin, 17);
            f1795r0.append(i.Layout_layout_constraintGuide_end, 18);
            f1795r0.append(i.Layout_layout_constraintGuide_percent, 19);
            f1795r0.append(i.Layout_guidelineUseRtl, 90);
            f1795r0.append(i.Layout_android_orientation, 26);
            f1795r0.append(i.Layout_layout_constraintStart_toEndOf, 31);
            f1795r0.append(i.Layout_layout_constraintStart_toStartOf, 32);
            f1795r0.append(i.Layout_layout_constraintEnd_toStartOf, 10);
            f1795r0.append(i.Layout_layout_constraintEnd_toEndOf, 9);
            f1795r0.append(i.Layout_layout_goneMarginLeft, 13);
            f1795r0.append(i.Layout_layout_goneMarginTop, 16);
            f1795r0.append(i.Layout_layout_goneMarginRight, 14);
            f1795r0.append(i.Layout_layout_goneMarginBottom, 11);
            f1795r0.append(i.Layout_layout_goneMarginStart, 15);
            f1795r0.append(i.Layout_layout_goneMarginEnd, 12);
            f1795r0.append(i.Layout_layout_constraintVertical_weight, 38);
            f1795r0.append(i.Layout_layout_constraintHorizontal_weight, 37);
            f1795r0.append(i.Layout_layout_constraintHorizontal_chainStyle, 39);
            f1795r0.append(i.Layout_layout_constraintVertical_chainStyle, 40);
            f1795r0.append(i.Layout_layout_constraintHorizontal_bias, 20);
            f1795r0.append(i.Layout_layout_constraintVertical_bias, 36);
            f1795r0.append(i.Layout_layout_constraintDimensionRatio, 5);
            f1795r0.append(i.Layout_layout_constraintLeft_creator, 91);
            f1795r0.append(i.Layout_layout_constraintTop_creator, 91);
            f1795r0.append(i.Layout_layout_constraintRight_creator, 91);
            f1795r0.append(i.Layout_layout_constraintBottom_creator, 91);
            f1795r0.append(i.Layout_layout_constraintBaseline_creator, 91);
            f1795r0.append(i.Layout_android_layout_marginLeft, 23);
            f1795r0.append(i.Layout_android_layout_marginRight, 27);
            f1795r0.append(i.Layout_android_layout_marginStart, 30);
            f1795r0.append(i.Layout_android_layout_marginEnd, 8);
            f1795r0.append(i.Layout_android_layout_marginTop, 33);
            f1795r0.append(i.Layout_android_layout_marginBottom, 2);
            f1795r0.append(i.Layout_android_layout_width, 22);
            f1795r0.append(i.Layout_android_layout_height, 21);
            f1795r0.append(i.Layout_layout_constraintWidth, 41);
            f1795r0.append(i.Layout_layout_constraintHeight, 42);
            f1795r0.append(i.Layout_layout_constrainedWidth, 41);
            f1795r0.append(i.Layout_layout_constrainedHeight, 42);
            f1795r0.append(i.Layout_layout_wrapBehaviorInParent, 76);
            f1795r0.append(i.Layout_layout_constraintCircle, 61);
            f1795r0.append(i.Layout_layout_constraintCircleRadius, 62);
            f1795r0.append(i.Layout_layout_constraintCircleAngle, 63);
            f1795r0.append(i.Layout_layout_constraintWidth_percent, 69);
            f1795r0.append(i.Layout_layout_constraintHeight_percent, 70);
            f1795r0.append(i.Layout_chainUseRtl, 71);
            f1795r0.append(i.Layout_barrierDirection, 72);
            f1795r0.append(i.Layout_barrierMargin, 73);
            f1795r0.append(i.Layout_constraint_referenced_ids, 74);
            f1795r0.append(i.Layout_barrierAllowsGoneWidgets, 75);
        }

        public void a(b bVar) {
            this.f1796a = bVar.f1796a;
            this.f1802d = bVar.f1802d;
            this.f1798b = bVar.f1798b;
            this.f1804e = bVar.f1804e;
            this.f1806f = bVar.f1806f;
            this.f1808g = bVar.f1808g;
            this.f1810h = bVar.f1810h;
            this.f1812i = bVar.f1812i;
            this.f1814j = bVar.f1814j;
            this.f1816k = bVar.f1816k;
            this.f1818l = bVar.f1818l;
            this.f1820m = bVar.f1820m;
            this.f1822n = bVar.f1822n;
            this.f1824o = bVar.f1824o;
            this.f1826p = bVar.f1826p;
            this.f1828q = bVar.f1828q;
            this.f1830r = bVar.f1830r;
            this.f1831s = bVar.f1831s;
            this.f1832t = bVar.f1832t;
            this.f1833u = bVar.f1833u;
            this.f1834v = bVar.f1834v;
            this.f1835w = bVar.f1835w;
            this.f1836x = bVar.f1836x;
            this.f1837y = bVar.f1837y;
            this.f1838z = bVar.f1838z;
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
            this.f1797a0 = bVar.f1797a0;
            this.f1799b0 = bVar.f1799b0;
            this.f1801c0 = bVar.f1801c0;
            this.f1803d0 = bVar.f1803d0;
            this.f1805e0 = bVar.f1805e0;
            this.f1807f0 = bVar.f1807f0;
            this.f1809g0 = bVar.f1809g0;
            this.f1811h0 = bVar.f1811h0;
            this.f1813i0 = bVar.f1813i0;
            this.f1815j0 = bVar.f1815j0;
            this.f1821m0 = bVar.f1821m0;
            int[] iArr = bVar.f1817k0;
            if (iArr == null || bVar.f1819l0 != null) {
                this.f1817k0 = null;
            } else {
                this.f1817k0 = Arrays.copyOf(iArr, iArr.length);
            }
            this.f1819l0 = bVar.f1819l0;
            this.f1823n0 = bVar.f1823n0;
            this.f1825o0 = bVar.f1825o0;
            this.f1827p0 = bVar.f1827p0;
            this.f1829q0 = bVar.f1829q0;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.Layout);
            this.f1798b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                int i8 = f1795r0.get(index);
                switch (i8) {
                    case 1:
                        this.f1830r = d.F(typedArrayObtainStyledAttributes, index, this.f1830r);
                        break;
                    case 2:
                        this.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.K);
                        break;
                    case 3:
                        this.f1828q = d.F(typedArrayObtainStyledAttributes, index, this.f1828q);
                        break;
                    case 4:
                        this.f1826p = d.F(typedArrayObtainStyledAttributes, index, this.f1826p);
                        break;
                    case 5:
                        this.A = typedArrayObtainStyledAttributes.getString(index);
                        break;
                    case 6:
                        this.E = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.E);
                        break;
                    case 7:
                        this.F = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.F);
                        break;
                    case 8:
                        this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.L);
                        break;
                    case 9:
                        this.f1836x = d.F(typedArrayObtainStyledAttributes, index, this.f1836x);
                        break;
                    case 10:
                        this.f1835w = d.F(typedArrayObtainStyledAttributes, index, this.f1835w);
                        break;
                    case 11:
                        this.R = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.R);
                        break;
                    case 12:
                        this.S = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.S);
                        break;
                    case 13:
                        this.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.O);
                        break;
                    case 14:
                        this.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.Q);
                        break;
                    case 15:
                        this.T = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.T);
                        break;
                    case 16:
                        this.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.P);
                        break;
                    case 17:
                        this.f1806f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1806f);
                        break;
                    case 18:
                        this.f1808g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1808g);
                        break;
                    case 19:
                        this.f1810h = typedArrayObtainStyledAttributes.getFloat(index, this.f1810h);
                        break;
                    case 20:
                        this.f1837y = typedArrayObtainStyledAttributes.getFloat(index, this.f1837y);
                        break;
                    case 21:
                        this.f1804e = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f1804e);
                        break;
                    case 22:
                        this.f1802d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.f1802d);
                        break;
                    case 23:
                        this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.H);
                        break;
                    case 24:
                        this.f1814j = d.F(typedArrayObtainStyledAttributes, index, this.f1814j);
                        break;
                    case 25:
                        this.f1816k = d.F(typedArrayObtainStyledAttributes, index, this.f1816k);
                        break;
                    case 26:
                        this.G = typedArrayObtainStyledAttributes.getInt(index, this.G);
                        break;
                    case 27:
                        this.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.I);
                        break;
                    case 28:
                        this.f1818l = d.F(typedArrayObtainStyledAttributes, index, this.f1818l);
                        break;
                    case 29:
                        this.f1820m = d.F(typedArrayObtainStyledAttributes, index, this.f1820m);
                        break;
                    case 30:
                        this.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.M);
                        break;
                    case 31:
                        this.f1833u = d.F(typedArrayObtainStyledAttributes, index, this.f1833u);
                        break;
                    case 32:
                        this.f1834v = d.F(typedArrayObtainStyledAttributes, index, this.f1834v);
                        break;
                    case 33:
                        this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                        break;
                    case 34:
                        this.f1824o = d.F(typedArrayObtainStyledAttributes, index, this.f1824o);
                        break;
                    case 35:
                        this.f1822n = d.F(typedArrayObtainStyledAttributes, index, this.f1822n);
                        break;
                    case 36:
                        this.f1838z = typedArrayObtainStyledAttributes.getFloat(index, this.f1838z);
                        break;
                    case 37:
                        this.W = typedArrayObtainStyledAttributes.getFloat(index, this.W);
                        break;
                    case 38:
                        this.V = typedArrayObtainStyledAttributes.getFloat(index, this.V);
                        break;
                    case 39:
                        this.X = typedArrayObtainStyledAttributes.getInt(index, this.X);
                        break;
                    case 40:
                        this.Y = typedArrayObtainStyledAttributes.getInt(index, this.Y);
                        break;
                    case 41:
                        d.G(this, typedArrayObtainStyledAttributes, index, 0);
                        break;
                    case 42:
                        d.G(this, typedArrayObtainStyledAttributes, index, 1);
                        break;
                    default:
                        switch (i8) {
                            case 61:
                                this.B = d.F(typedArrayObtainStyledAttributes, index, this.B);
                                break;
                            case 62:
                                this.C = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.C);
                                break;
                            case 63:
                                this.D = typedArrayObtainStyledAttributes.getFloat(index, this.D);
                                break;
                            default:
                                switch (i8) {
                                    case 69:
                                        this.f1807f0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 70:
                                        this.f1809g0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 71:
                                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                        break;
                                    case 72:
                                        this.f1811h0 = typedArrayObtainStyledAttributes.getInt(index, this.f1811h0);
                                        break;
                                    case 73:
                                        this.f1813i0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1813i0);
                                        break;
                                    case 74:
                                        this.f1819l0 = typedArrayObtainStyledAttributes.getString(index);
                                        break;
                                    case 75:
                                        this.f1827p0 = typedArrayObtainStyledAttributes.getBoolean(index, this.f1827p0);
                                        break;
                                    case 76:
                                        this.f1829q0 = typedArrayObtainStyledAttributes.getInt(index, this.f1829q0);
                                        break;
                                    case 77:
                                        this.f1831s = d.F(typedArrayObtainStyledAttributes, index, this.f1831s);
                                        break;
                                    case 78:
                                        this.f1832t = d.F(typedArrayObtainStyledAttributes, index, this.f1832t);
                                        break;
                                    case 79:
                                        this.U = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.U);
                                        break;
                                    case 80:
                                        this.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.N);
                                        break;
                                    case 81:
                                        this.Z = typedArrayObtainStyledAttributes.getInt(index, this.Z);
                                        break;
                                    case 82:
                                        this.f1797a0 = typedArrayObtainStyledAttributes.getInt(index, this.f1797a0);
                                        break;
                                    case 83:
                                        this.f1801c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1801c0);
                                        break;
                                    case 84:
                                        this.f1799b0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1799b0);
                                        break;
                                    case 85:
                                        this.f1805e0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1805e0);
                                        break;
                                    case 86:
                                        this.f1803d0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1803d0);
                                        break;
                                    case 87:
                                        this.f1823n0 = typedArrayObtainStyledAttributes.getBoolean(index, this.f1823n0);
                                        break;
                                    case 88:
                                        this.f1825o0 = typedArrayObtainStyledAttributes.getBoolean(index, this.f1825o0);
                                        break;
                                    case 89:
                                        this.f1821m0 = typedArrayObtainStyledAttributes.getString(index);
                                        break;
                                    case 90:
                                        this.f1812i = typedArrayObtainStyledAttributes.getBoolean(index, this.f1812i);
                                        break;
                                    case 91:
                                        Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1795r0.get(index));
                                        break;
                                    default:
                                        Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1795r0.get(index));
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

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private static SparseIntArray f1839o;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1840a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1841b = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1842c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f1843d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1844e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1845f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f1846g = Float.NaN;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1847h = -1;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f1848i = Float.NaN;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f1849j = Float.NaN;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f1850k = -1;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public String f1851l = null;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f1852m = -3;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f1853n = -1;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1839o = sparseIntArray;
            sparseIntArray.append(i.Motion_motionPathRotate, 1);
            f1839o.append(i.Motion_pathMotionArc, 2);
            f1839o.append(i.Motion_transitionEasing, 3);
            f1839o.append(i.Motion_drawPath, 4);
            f1839o.append(i.Motion_animateRelativeTo, 5);
            f1839o.append(i.Motion_animateCircleAngleTo, 6);
            f1839o.append(i.Motion_motionStagger, 7);
            f1839o.append(i.Motion_quantizeMotionSteps, 8);
            f1839o.append(i.Motion_quantizeMotionPhase, 9);
            f1839o.append(i.Motion_quantizeMotionInterpolator, 10);
        }

        public void a(c cVar) {
            this.f1840a = cVar.f1840a;
            this.f1841b = cVar.f1841b;
            this.f1843d = cVar.f1843d;
            this.f1844e = cVar.f1844e;
            this.f1845f = cVar.f1845f;
            this.f1848i = cVar.f1848i;
            this.f1846g = cVar.f1846g;
            this.f1847h = cVar.f1847h;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.Motion);
            this.f1840a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                switch (f1839o.get(index)) {
                    case 1:
                        this.f1848i = typedArrayObtainStyledAttributes.getFloat(index, this.f1848i);
                        break;
                    case 2:
                        this.f1844e = typedArrayObtainStyledAttributes.getInt(index, this.f1844e);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.f1843d = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.f1843d = p.c.f8706c[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.f1845f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.f1841b = d.F(typedArrayObtainStyledAttributes, index, this.f1841b);
                        break;
                    case 6:
                        this.f1842c = typedArrayObtainStyledAttributes.getInteger(index, this.f1842c);
                        break;
                    case 7:
                        this.f1846g = typedArrayObtainStyledAttributes.getFloat(index, this.f1846g);
                        break;
                    case 8:
                        this.f1850k = typedArrayObtainStyledAttributes.getInteger(index, this.f1850k);
                        break;
                    case 9:
                        this.f1849j = typedArrayObtainStyledAttributes.getFloat(index, this.f1849j);
                        break;
                    case 10:
                        int i8 = typedArrayObtainStyledAttributes.peekValue(index).type;
                        if (i8 == 1) {
                            int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                            this.f1853n = resourceId;
                            if (resourceId != -1) {
                                this.f1852m = -2;
                            }
                        } else if (i8 == 3) {
                            String string = typedArrayObtainStyledAttributes.getString(index);
                            this.f1851l = string;
                            if (string.indexOf("/") > 0) {
                                this.f1853n = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                                this.f1852m = -2;
                            } else {
                                this.f1852m = -1;
                            }
                        } else {
                            this.f1852m = typedArrayObtainStyledAttributes.getInteger(index, this.f1853n);
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class C0018d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1854a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1855b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1856c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1857d = 1.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1858e = Float.NaN;

        public void a(C0018d c0018d) {
            this.f1854a = c0018d.f1854a;
            this.f1855b = c0018d.f1855b;
            this.f1857d = c0018d.f1857d;
            this.f1858e = c0018d.f1858e;
            this.f1856c = c0018d.f1856c;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.PropertySet);
            this.f1854a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == i.PropertySet_android_alpha) {
                    this.f1857d = typedArrayObtainStyledAttributes.getFloat(index, this.f1857d);
                } else if (index == i.PropertySet_android_visibility) {
                    this.f1855b = typedArrayObtainStyledAttributes.getInt(index, this.f1855b);
                    this.f1855b = d.f1765h[this.f1855b];
                } else if (index == i.PropertySet_visibilityMode) {
                    this.f1856c = typedArrayObtainStyledAttributes.getInt(index, this.f1856c);
                } else if (index == i.PropertySet_motionProgress) {
                    this.f1858e = typedArrayObtainStyledAttributes.getFloat(index, this.f1858e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: compiled from: ConstraintSet.java */
    public static class e {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private static SparseIntArray f1859o;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1860a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1861b = 0.0f;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1862c = 0.0f;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1863d = 0.0f;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1864e = 1.0f;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f1865f = 1.0f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f1866g = Float.NaN;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f1867h = Float.NaN;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1868i = -1;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f1869j = 0.0f;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f1870k = 0.0f;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f1871l = 0.0f;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f1872m = false;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public float f1873n = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f1859o = sparseIntArray;
            sparseIntArray.append(i.Transform_android_rotation, 1);
            f1859o.append(i.Transform_android_rotationX, 2);
            f1859o.append(i.Transform_android_rotationY, 3);
            f1859o.append(i.Transform_android_scaleX, 4);
            f1859o.append(i.Transform_android_scaleY, 5);
            f1859o.append(i.Transform_android_transformPivotX, 6);
            f1859o.append(i.Transform_android_transformPivotY, 7);
            f1859o.append(i.Transform_android_translationX, 8);
            f1859o.append(i.Transform_android_translationY, 9);
            f1859o.append(i.Transform_android_translationZ, 10);
            f1859o.append(i.Transform_android_elevation, 11);
            f1859o.append(i.Transform_transformPivotTarget, 12);
        }

        public void a(e eVar) {
            this.f1860a = eVar.f1860a;
            this.f1861b = eVar.f1861b;
            this.f1862c = eVar.f1862c;
            this.f1863d = eVar.f1863d;
            this.f1864e = eVar.f1864e;
            this.f1865f = eVar.f1865f;
            this.f1866g = eVar.f1866g;
            this.f1867h = eVar.f1867h;
            this.f1868i = eVar.f1868i;
            this.f1869j = eVar.f1869j;
            this.f1870k = eVar.f1870k;
            this.f1871l = eVar.f1871l;
            this.f1872m = eVar.f1872m;
            this.f1873n = eVar.f1873n;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.Transform);
            this.f1860a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                switch (f1859o.get(index)) {
                    case 1:
                        this.f1861b = typedArrayObtainStyledAttributes.getFloat(index, this.f1861b);
                        break;
                    case 2:
                        this.f1862c = typedArrayObtainStyledAttributes.getFloat(index, this.f1862c);
                        break;
                    case 3:
                        this.f1863d = typedArrayObtainStyledAttributes.getFloat(index, this.f1863d);
                        break;
                    case 4:
                        this.f1864e = typedArrayObtainStyledAttributes.getFloat(index, this.f1864e);
                        break;
                    case 5:
                        this.f1865f = typedArrayObtainStyledAttributes.getFloat(index, this.f1865f);
                        break;
                    case 6:
                        this.f1866g = typedArrayObtainStyledAttributes.getDimension(index, this.f1866g);
                        break;
                    case 7:
                        this.f1867h = typedArrayObtainStyledAttributes.getDimension(index, this.f1867h);
                        break;
                    case 8:
                        this.f1869j = typedArrayObtainStyledAttributes.getDimension(index, this.f1869j);
                        break;
                    case 9:
                        this.f1870k = typedArrayObtainStyledAttributes.getDimension(index, this.f1870k);
                        break;
                    case 10:
                        this.f1871l = typedArrayObtainStyledAttributes.getDimension(index, this.f1871l);
                        break;
                    case 11:
                        this.f1872m = true;
                        this.f1873n = typedArrayObtainStyledAttributes.getDimension(index, this.f1873n);
                        break;
                    case 12:
                        this.f1868i = d.F(typedArrayObtainStyledAttributes, index, this.f1868i);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    static {
        f1766i.append(i.Constraint_layout_constraintLeft_toLeftOf, 25);
        f1766i.append(i.Constraint_layout_constraintLeft_toRightOf, 26);
        f1766i.append(i.Constraint_layout_constraintRight_toLeftOf, 29);
        f1766i.append(i.Constraint_layout_constraintRight_toRightOf, 30);
        f1766i.append(i.Constraint_layout_constraintTop_toTopOf, 36);
        f1766i.append(i.Constraint_layout_constraintTop_toBottomOf, 35);
        f1766i.append(i.Constraint_layout_constraintBottom_toTopOf, 4);
        f1766i.append(i.Constraint_layout_constraintBottom_toBottomOf, 3);
        f1766i.append(i.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        f1766i.append(i.Constraint_layout_constraintBaseline_toTopOf, 91);
        f1766i.append(i.Constraint_layout_constraintBaseline_toBottomOf, 92);
        f1766i.append(i.Constraint_layout_editor_absoluteX, 6);
        f1766i.append(i.Constraint_layout_editor_absoluteY, 7);
        f1766i.append(i.Constraint_layout_constraintGuide_begin, 17);
        f1766i.append(i.Constraint_layout_constraintGuide_end, 18);
        f1766i.append(i.Constraint_layout_constraintGuide_percent, 19);
        f1766i.append(i.Constraint_guidelineUseRtl, 99);
        f1766i.append(i.Constraint_android_orientation, 27);
        f1766i.append(i.Constraint_layout_constraintStart_toEndOf, 32);
        f1766i.append(i.Constraint_layout_constraintStart_toStartOf, 33);
        f1766i.append(i.Constraint_layout_constraintEnd_toStartOf, 10);
        f1766i.append(i.Constraint_layout_constraintEnd_toEndOf, 9);
        f1766i.append(i.Constraint_layout_goneMarginLeft, 13);
        f1766i.append(i.Constraint_layout_goneMarginTop, 16);
        f1766i.append(i.Constraint_layout_goneMarginRight, 14);
        f1766i.append(i.Constraint_layout_goneMarginBottom, 11);
        f1766i.append(i.Constraint_layout_goneMarginStart, 15);
        f1766i.append(i.Constraint_layout_goneMarginEnd, 12);
        f1766i.append(i.Constraint_layout_constraintVertical_weight, 40);
        f1766i.append(i.Constraint_layout_constraintHorizontal_weight, 39);
        f1766i.append(i.Constraint_layout_constraintHorizontal_chainStyle, 41);
        f1766i.append(i.Constraint_layout_constraintVertical_chainStyle, 42);
        f1766i.append(i.Constraint_layout_constraintHorizontal_bias, 20);
        f1766i.append(i.Constraint_layout_constraintVertical_bias, 37);
        f1766i.append(i.Constraint_layout_constraintDimensionRatio, 5);
        f1766i.append(i.Constraint_layout_constraintLeft_creator, 87);
        f1766i.append(i.Constraint_layout_constraintTop_creator, 87);
        f1766i.append(i.Constraint_layout_constraintRight_creator, 87);
        f1766i.append(i.Constraint_layout_constraintBottom_creator, 87);
        f1766i.append(i.Constraint_layout_constraintBaseline_creator, 87);
        f1766i.append(i.Constraint_android_layout_marginLeft, 24);
        f1766i.append(i.Constraint_android_layout_marginRight, 28);
        f1766i.append(i.Constraint_android_layout_marginStart, 31);
        f1766i.append(i.Constraint_android_layout_marginEnd, 8);
        f1766i.append(i.Constraint_android_layout_marginTop, 34);
        f1766i.append(i.Constraint_android_layout_marginBottom, 2);
        f1766i.append(i.Constraint_android_layout_width, 23);
        f1766i.append(i.Constraint_android_layout_height, 21);
        f1766i.append(i.Constraint_layout_constraintWidth, 95);
        f1766i.append(i.Constraint_layout_constraintHeight, 96);
        f1766i.append(i.Constraint_android_visibility, 22);
        f1766i.append(i.Constraint_android_alpha, 43);
        f1766i.append(i.Constraint_android_elevation, 44);
        f1766i.append(i.Constraint_android_rotationX, 45);
        f1766i.append(i.Constraint_android_rotationY, 46);
        f1766i.append(i.Constraint_android_rotation, 60);
        f1766i.append(i.Constraint_android_scaleX, 47);
        f1766i.append(i.Constraint_android_scaleY, 48);
        f1766i.append(i.Constraint_android_transformPivotX, 49);
        f1766i.append(i.Constraint_android_transformPivotY, 50);
        f1766i.append(i.Constraint_android_translationX, 51);
        f1766i.append(i.Constraint_android_translationY, 52);
        f1766i.append(i.Constraint_android_translationZ, 53);
        f1766i.append(i.Constraint_layout_constraintWidth_default, 54);
        f1766i.append(i.Constraint_layout_constraintHeight_default, 55);
        f1766i.append(i.Constraint_layout_constraintWidth_max, 56);
        f1766i.append(i.Constraint_layout_constraintHeight_max, 57);
        f1766i.append(i.Constraint_layout_constraintWidth_min, 58);
        f1766i.append(i.Constraint_layout_constraintHeight_min, 59);
        f1766i.append(i.Constraint_layout_constraintCircle, 61);
        f1766i.append(i.Constraint_layout_constraintCircleRadius, 62);
        f1766i.append(i.Constraint_layout_constraintCircleAngle, 63);
        f1766i.append(i.Constraint_animateRelativeTo, 64);
        f1766i.append(i.Constraint_transitionEasing, 65);
        f1766i.append(i.Constraint_drawPath, 66);
        f1766i.append(i.Constraint_transitionPathRotate, 67);
        f1766i.append(i.Constraint_motionStagger, 79);
        f1766i.append(i.Constraint_android_id, 38);
        f1766i.append(i.Constraint_motionProgress, 68);
        f1766i.append(i.Constraint_layout_constraintWidth_percent, 69);
        f1766i.append(i.Constraint_layout_constraintHeight_percent, 70);
        f1766i.append(i.Constraint_layout_wrapBehaviorInParent, 97);
        f1766i.append(i.Constraint_chainUseRtl, 71);
        f1766i.append(i.Constraint_barrierDirection, 72);
        f1766i.append(i.Constraint_barrierMargin, 73);
        f1766i.append(i.Constraint_constraint_referenced_ids, 74);
        f1766i.append(i.Constraint_barrierAllowsGoneWidgets, 75);
        f1766i.append(i.Constraint_pathMotionArc, 76);
        f1766i.append(i.Constraint_layout_constraintTag, 77);
        f1766i.append(i.Constraint_visibilityMode, 78);
        f1766i.append(i.Constraint_layout_constrainedWidth, 80);
        f1766i.append(i.Constraint_layout_constrainedHeight, 81);
        f1766i.append(i.Constraint_polarRelativeTo, 82);
        f1766i.append(i.Constraint_transformPivotTarget, 83);
        f1766i.append(i.Constraint_quantizeMotionSteps, 84);
        f1766i.append(i.Constraint_quantizeMotionPhase, 85);
        f1766i.append(i.Constraint_quantizeMotionInterpolator, 86);
        SparseIntArray sparseIntArray = f1767j;
        int i7 = i.ConstraintOverride_layout_editor_absoluteY;
        sparseIntArray.append(i7, 6);
        f1767j.append(i7, 7);
        f1767j.append(i.ConstraintOverride_android_orientation, 27);
        f1767j.append(i.ConstraintOverride_layout_goneMarginLeft, 13);
        f1767j.append(i.ConstraintOverride_layout_goneMarginTop, 16);
        f1767j.append(i.ConstraintOverride_layout_goneMarginRight, 14);
        f1767j.append(i.ConstraintOverride_layout_goneMarginBottom, 11);
        f1767j.append(i.ConstraintOverride_layout_goneMarginStart, 15);
        f1767j.append(i.ConstraintOverride_layout_goneMarginEnd, 12);
        f1767j.append(i.ConstraintOverride_layout_constraintVertical_weight, 40);
        f1767j.append(i.ConstraintOverride_layout_constraintHorizontal_weight, 39);
        f1767j.append(i.ConstraintOverride_layout_constraintHorizontal_chainStyle, 41);
        f1767j.append(i.ConstraintOverride_layout_constraintVertical_chainStyle, 42);
        f1767j.append(i.ConstraintOverride_layout_constraintHorizontal_bias, 20);
        f1767j.append(i.ConstraintOverride_layout_constraintVertical_bias, 37);
        f1767j.append(i.ConstraintOverride_layout_constraintDimensionRatio, 5);
        f1767j.append(i.ConstraintOverride_layout_constraintLeft_creator, 87);
        f1767j.append(i.ConstraintOverride_layout_constraintTop_creator, 87);
        f1767j.append(i.ConstraintOverride_layout_constraintRight_creator, 87);
        f1767j.append(i.ConstraintOverride_layout_constraintBottom_creator, 87);
        f1767j.append(i.ConstraintOverride_layout_constraintBaseline_creator, 87);
        f1767j.append(i.ConstraintOverride_android_layout_marginLeft, 24);
        f1767j.append(i.ConstraintOverride_android_layout_marginRight, 28);
        f1767j.append(i.ConstraintOverride_android_layout_marginStart, 31);
        f1767j.append(i.ConstraintOverride_android_layout_marginEnd, 8);
        f1767j.append(i.ConstraintOverride_android_layout_marginTop, 34);
        f1767j.append(i.ConstraintOverride_android_layout_marginBottom, 2);
        f1767j.append(i.ConstraintOverride_android_layout_width, 23);
        f1767j.append(i.ConstraintOverride_android_layout_height, 21);
        f1767j.append(i.ConstraintOverride_layout_constraintWidth, 95);
        f1767j.append(i.ConstraintOverride_layout_constraintHeight, 96);
        f1767j.append(i.ConstraintOverride_android_visibility, 22);
        f1767j.append(i.ConstraintOverride_android_alpha, 43);
        f1767j.append(i.ConstraintOverride_android_elevation, 44);
        f1767j.append(i.ConstraintOverride_android_rotationX, 45);
        f1767j.append(i.ConstraintOverride_android_rotationY, 46);
        f1767j.append(i.ConstraintOverride_android_rotation, 60);
        f1767j.append(i.ConstraintOverride_android_scaleX, 47);
        f1767j.append(i.ConstraintOverride_android_scaleY, 48);
        f1767j.append(i.ConstraintOverride_android_transformPivotX, 49);
        f1767j.append(i.ConstraintOverride_android_transformPivotY, 50);
        f1767j.append(i.ConstraintOverride_android_translationX, 51);
        f1767j.append(i.ConstraintOverride_android_translationY, 52);
        f1767j.append(i.ConstraintOverride_android_translationZ, 53);
        f1767j.append(i.ConstraintOverride_layout_constraintWidth_default, 54);
        f1767j.append(i.ConstraintOverride_layout_constraintHeight_default, 55);
        f1767j.append(i.ConstraintOverride_layout_constraintWidth_max, 56);
        f1767j.append(i.ConstraintOverride_layout_constraintHeight_max, 57);
        f1767j.append(i.ConstraintOverride_layout_constraintWidth_min, 58);
        f1767j.append(i.ConstraintOverride_layout_constraintHeight_min, 59);
        f1767j.append(i.ConstraintOverride_layout_constraintCircleRadius, 62);
        f1767j.append(i.ConstraintOverride_layout_constraintCircleAngle, 63);
        f1767j.append(i.ConstraintOverride_animateRelativeTo, 64);
        f1767j.append(i.ConstraintOverride_transitionEasing, 65);
        f1767j.append(i.ConstraintOverride_drawPath, 66);
        f1767j.append(i.ConstraintOverride_transitionPathRotate, 67);
        f1767j.append(i.ConstraintOverride_motionStagger, 79);
        f1767j.append(i.ConstraintOverride_android_id, 38);
        f1767j.append(i.ConstraintOverride_motionTarget, 98);
        f1767j.append(i.ConstraintOverride_motionProgress, 68);
        f1767j.append(i.ConstraintOverride_layout_constraintWidth_percent, 69);
        f1767j.append(i.ConstraintOverride_layout_constraintHeight_percent, 70);
        f1767j.append(i.ConstraintOverride_chainUseRtl, 71);
        f1767j.append(i.ConstraintOverride_barrierDirection, 72);
        f1767j.append(i.ConstraintOverride_barrierMargin, 73);
        f1767j.append(i.ConstraintOverride_constraint_referenced_ids, 74);
        f1767j.append(i.ConstraintOverride_barrierAllowsGoneWidgets, 75);
        f1767j.append(i.ConstraintOverride_pathMotionArc, 76);
        f1767j.append(i.ConstraintOverride_layout_constraintTag, 77);
        f1767j.append(i.ConstraintOverride_visibilityMode, 78);
        f1767j.append(i.ConstraintOverride_layout_constrainedWidth, 80);
        f1767j.append(i.ConstraintOverride_layout_constrainedHeight, 81);
        f1767j.append(i.ConstraintOverride_polarRelativeTo, 82);
        f1767j.append(i.ConstraintOverride_transformPivotTarget, 83);
        f1767j.append(i.ConstraintOverride_quantizeMotionSteps, 84);
        f1767j.append(i.ConstraintOverride_quantizeMotionPhase, 85);
        f1767j.append(i.ConstraintOverride_quantizeMotionInterpolator, 86);
        f1767j.append(i.ConstraintOverride_layout_wrapBehaviorInParent, 97);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int F(TypedArray typedArray, int i7, int i8) {
        int resourceId = typedArray.getResourceId(i7, i8);
        return resourceId == -1 ? typedArray.getInt(i7, -1) : resourceId;
    }

    static void G(Object obj, TypedArray typedArray, int i7, int i8) {
        if (obj == null) {
            return;
        }
        int i9 = typedArray.peekValue(i7).type;
        if (i9 == 3) {
            H(obj, typedArray.getString(i7), i8);
            return;
        }
        int dimensionPixelSize = -2;
        boolean z6 = false;
        if (i9 != 5) {
            int i10 = typedArray.getInt(i7, 0);
            if (i10 != -4) {
                dimensionPixelSize = (i10 == -3 || !(i10 == -2 || i10 == -1)) ? 0 : i10;
            } else {
                z6 = true;
            }
        } else {
            dimensionPixelSize = typedArray.getDimensionPixelSize(i7, 0);
        }
        if (obj instanceof ConstraintLayout.b) {
            ConstraintLayout.b bVar = (ConstraintLayout.b) obj;
            if (i8 == 0) {
                ((ViewGroup.MarginLayoutParams) bVar).width = dimensionPixelSize;
                bVar.f1673a0 = z6;
                return;
            } else {
                ((ViewGroup.MarginLayoutParams) bVar).height = dimensionPixelSize;
                bVar.f1675b0 = z6;
                return;
            }
        }
        if (obj instanceof b) {
            b bVar2 = (b) obj;
            if (i8 == 0) {
                bVar2.f1802d = dimensionPixelSize;
                bVar2.f1823n0 = z6;
                return;
            } else {
                bVar2.f1804e = dimensionPixelSize;
                bVar2.f1825o0 = z6;
                return;
            }
        }
        if (obj instanceof a.C0017a) {
            a.C0017a c0017a = (a.C0017a) obj;
            if (i8 == 0) {
                c0017a.b(23, dimensionPixelSize);
                c0017a.d(80, z6);
            } else {
                c0017a.b(21, dimensionPixelSize);
                c0017a.d(81, z6);
            }
        }
    }

    static void H(Object obj, String str, int i7) {
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
                    if (i7 == 0) {
                        ((ViewGroup.MarginLayoutParams) bVar).width = 0;
                    } else {
                        ((ViewGroup.MarginLayoutParams) bVar).height = 0;
                    }
                    I(bVar, strTrim2);
                    return;
                }
                if (obj instanceof b) {
                    ((b) obj).A = strTrim2;
                    return;
                } else {
                    if (obj instanceof a.C0017a) {
                        ((a.C0017a) obj).c(5, strTrim2);
                        return;
                    }
                    return;
                }
            }
            try {
                if ("weight".equalsIgnoreCase(strTrim)) {
                    float f7 = Float.parseFloat(strTrim2);
                    if (obj instanceof ConstraintLayout.b) {
                        ConstraintLayout.b bVar2 = (ConstraintLayout.b) obj;
                        if (i7 == 0) {
                            ((ViewGroup.MarginLayoutParams) bVar2).width = 0;
                            bVar2.L = f7;
                        } else {
                            ((ViewGroup.MarginLayoutParams) bVar2).height = 0;
                            bVar2.M = f7;
                        }
                    } else if (obj instanceof b) {
                        b bVar3 = (b) obj;
                        if (i7 == 0) {
                            bVar3.f1802d = 0;
                            bVar3.W = f7;
                        } else {
                            bVar3.f1804e = 0;
                            bVar3.V = f7;
                        }
                    } else if (obj instanceof a.C0017a) {
                        a.C0017a c0017a = (a.C0017a) obj;
                        if (i7 == 0) {
                            c0017a.b(23, 0);
                            c0017a.a(39, f7);
                        } else {
                            c0017a.b(21, 0);
                            c0017a.a(40, f7);
                        }
                    }
                } else {
                    if (!"parent".equalsIgnoreCase(strTrim)) {
                        return;
                    }
                    float fMax = Math.max(0.0f, Math.min(1.0f, Float.parseFloat(strTrim2)));
                    if (obj instanceof ConstraintLayout.b) {
                        ConstraintLayout.b bVar4 = (ConstraintLayout.b) obj;
                        if (i7 == 0) {
                            ((ViewGroup.MarginLayoutParams) bVar4).width = 0;
                            bVar4.V = fMax;
                            bVar4.P = 2;
                        } else {
                            ((ViewGroup.MarginLayoutParams) bVar4).height = 0;
                            bVar4.W = fMax;
                            bVar4.Q = 2;
                        }
                    } else if (obj instanceof b) {
                        b bVar5 = (b) obj;
                        if (i7 == 0) {
                            bVar5.f1802d = 0;
                            bVar5.f1807f0 = fMax;
                            bVar5.Z = 2;
                        } else {
                            bVar5.f1804e = 0;
                            bVar5.f1809g0 = fMax;
                            bVar5.f1797a0 = 2;
                        }
                    } else if (obj instanceof a.C0017a) {
                        a.C0017a c0017a2 = (a.C0017a) obj;
                        if (i7 == 0) {
                            c0017a2.b(23, 0);
                            c0017a2.b(54, 2);
                        } else {
                            c0017a2.b(21, 0);
                            c0017a2.b(55, 2);
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
    }

    static void I(ConstraintLayout.b bVar, String str) {
        float fAbs = Float.NaN;
        int i7 = -1;
        if (str != null) {
            int length = str.length();
            int iIndexOf = str.indexOf(44);
            int i8 = 0;
            if (iIndexOf > 0 && iIndexOf < length - 1) {
                String strSubstring = str.substring(0, iIndexOf);
                if (strSubstring.equalsIgnoreCase("W")) {
                    i7 = 0;
                } else if (strSubstring.equalsIgnoreCase("H")) {
                    i7 = 1;
                }
                i8 = iIndexOf + 1;
            }
            int iIndexOf2 = str.indexOf(58);
            try {
                if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                    String strSubstring2 = str.substring(i8);
                    if (strSubstring2.length() > 0) {
                        fAbs = Float.parseFloat(strSubstring2);
                    }
                } else {
                    String strSubstring3 = str.substring(i8, iIndexOf2);
                    String strSubstring4 = str.substring(iIndexOf2 + 1);
                    if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                        float f7 = Float.parseFloat(strSubstring3);
                        float f8 = Float.parseFloat(strSubstring4);
                        if (f7 > 0.0f && f8 > 0.0f) {
                            fAbs = i7 == 1 ? Math.abs(f8 / f7) : Math.abs(f7 / f8);
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        bVar.I = str;
        bVar.J = fAbs;
        bVar.K = i7;
    }

    private void J(Context context, a aVar, TypedArray typedArray, boolean z6) {
        if (z6) {
            K(context, aVar, typedArray);
            return;
        }
        int indexCount = typedArray.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArray.getIndex(i7);
            if (index != i.Constraint_android_id && i.Constraint_android_layout_marginStart != index && i.Constraint_android_layout_marginEnd != index) {
                aVar.f1778d.f1840a = true;
                aVar.f1779e.f1798b = true;
                aVar.f1777c.f1854a = true;
                aVar.f1780f.f1860a = true;
            }
            switch (f1766i.get(index)) {
                case 1:
                    b bVar = aVar.f1779e;
                    bVar.f1830r = F(typedArray, index, bVar.f1830r);
                    break;
                case 2:
                    b bVar2 = aVar.f1779e;
                    bVar2.K = typedArray.getDimensionPixelSize(index, bVar2.K);
                    break;
                case 3:
                    b bVar3 = aVar.f1779e;
                    bVar3.f1828q = F(typedArray, index, bVar3.f1828q);
                    break;
                case 4:
                    b bVar4 = aVar.f1779e;
                    bVar4.f1826p = F(typedArray, index, bVar4.f1826p);
                    break;
                case 5:
                    aVar.f1779e.A = typedArray.getString(index);
                    break;
                case 6:
                    b bVar5 = aVar.f1779e;
                    bVar5.E = typedArray.getDimensionPixelOffset(index, bVar5.E);
                    break;
                case 7:
                    b bVar6 = aVar.f1779e;
                    bVar6.F = typedArray.getDimensionPixelOffset(index, bVar6.F);
                    break;
                case 8:
                    b bVar7 = aVar.f1779e;
                    bVar7.L = typedArray.getDimensionPixelSize(index, bVar7.L);
                    break;
                case 9:
                    b bVar8 = aVar.f1779e;
                    bVar8.f1836x = F(typedArray, index, bVar8.f1836x);
                    break;
                case 10:
                    b bVar9 = aVar.f1779e;
                    bVar9.f1835w = F(typedArray, index, bVar9.f1835w);
                    break;
                case 11:
                    b bVar10 = aVar.f1779e;
                    bVar10.R = typedArray.getDimensionPixelSize(index, bVar10.R);
                    break;
                case 12:
                    b bVar11 = aVar.f1779e;
                    bVar11.S = typedArray.getDimensionPixelSize(index, bVar11.S);
                    break;
                case 13:
                    b bVar12 = aVar.f1779e;
                    bVar12.O = typedArray.getDimensionPixelSize(index, bVar12.O);
                    break;
                case 14:
                    b bVar13 = aVar.f1779e;
                    bVar13.Q = typedArray.getDimensionPixelSize(index, bVar13.Q);
                    break;
                case 15:
                    b bVar14 = aVar.f1779e;
                    bVar14.T = typedArray.getDimensionPixelSize(index, bVar14.T);
                    break;
                case 16:
                    b bVar15 = aVar.f1779e;
                    bVar15.P = typedArray.getDimensionPixelSize(index, bVar15.P);
                    break;
                case 17:
                    b bVar16 = aVar.f1779e;
                    bVar16.f1806f = typedArray.getDimensionPixelOffset(index, bVar16.f1806f);
                    break;
                case 18:
                    b bVar17 = aVar.f1779e;
                    bVar17.f1808g = typedArray.getDimensionPixelOffset(index, bVar17.f1808g);
                    break;
                case 19:
                    b bVar18 = aVar.f1779e;
                    bVar18.f1810h = typedArray.getFloat(index, bVar18.f1810h);
                    break;
                case 20:
                    b bVar19 = aVar.f1779e;
                    bVar19.f1837y = typedArray.getFloat(index, bVar19.f1837y);
                    break;
                case 21:
                    b bVar20 = aVar.f1779e;
                    bVar20.f1804e = typedArray.getLayoutDimension(index, bVar20.f1804e);
                    break;
                case 22:
                    C0018d c0018d = aVar.f1777c;
                    c0018d.f1855b = typedArray.getInt(index, c0018d.f1855b);
                    C0018d c0018d2 = aVar.f1777c;
                    c0018d2.f1855b = f1765h[c0018d2.f1855b];
                    break;
                case 23:
                    b bVar21 = aVar.f1779e;
                    bVar21.f1802d = typedArray.getLayoutDimension(index, bVar21.f1802d);
                    break;
                case 24:
                    b bVar22 = aVar.f1779e;
                    bVar22.H = typedArray.getDimensionPixelSize(index, bVar22.H);
                    break;
                case 25:
                    b bVar23 = aVar.f1779e;
                    bVar23.f1814j = F(typedArray, index, bVar23.f1814j);
                    break;
                case 26:
                    b bVar24 = aVar.f1779e;
                    bVar24.f1816k = F(typedArray, index, bVar24.f1816k);
                    break;
                case 27:
                    b bVar25 = aVar.f1779e;
                    bVar25.G = typedArray.getInt(index, bVar25.G);
                    break;
                case 28:
                    b bVar26 = aVar.f1779e;
                    bVar26.I = typedArray.getDimensionPixelSize(index, bVar26.I);
                    break;
                case 29:
                    b bVar27 = aVar.f1779e;
                    bVar27.f1818l = F(typedArray, index, bVar27.f1818l);
                    break;
                case 30:
                    b bVar28 = aVar.f1779e;
                    bVar28.f1820m = F(typedArray, index, bVar28.f1820m);
                    break;
                case 31:
                    b bVar29 = aVar.f1779e;
                    bVar29.M = typedArray.getDimensionPixelSize(index, bVar29.M);
                    break;
                case 32:
                    b bVar30 = aVar.f1779e;
                    bVar30.f1833u = F(typedArray, index, bVar30.f1833u);
                    break;
                case 33:
                    b bVar31 = aVar.f1779e;
                    bVar31.f1834v = F(typedArray, index, bVar31.f1834v);
                    break;
                case 34:
                    b bVar32 = aVar.f1779e;
                    bVar32.J = typedArray.getDimensionPixelSize(index, bVar32.J);
                    break;
                case 35:
                    b bVar33 = aVar.f1779e;
                    bVar33.f1824o = F(typedArray, index, bVar33.f1824o);
                    break;
                case 36:
                    b bVar34 = aVar.f1779e;
                    bVar34.f1822n = F(typedArray, index, bVar34.f1822n);
                    break;
                case 37:
                    b bVar35 = aVar.f1779e;
                    bVar35.f1838z = typedArray.getFloat(index, bVar35.f1838z);
                    break;
                case 38:
                    aVar.f1775a = typedArray.getResourceId(index, aVar.f1775a);
                    break;
                case 39:
                    b bVar36 = aVar.f1779e;
                    bVar36.W = typedArray.getFloat(index, bVar36.W);
                    break;
                case 40:
                    b bVar37 = aVar.f1779e;
                    bVar37.V = typedArray.getFloat(index, bVar37.V);
                    break;
                case 41:
                    b bVar38 = aVar.f1779e;
                    bVar38.X = typedArray.getInt(index, bVar38.X);
                    break;
                case 42:
                    b bVar39 = aVar.f1779e;
                    bVar39.Y = typedArray.getInt(index, bVar39.Y);
                    break;
                case 43:
                    C0018d c0018d3 = aVar.f1777c;
                    c0018d3.f1857d = typedArray.getFloat(index, c0018d3.f1857d);
                    break;
                case 44:
                    e eVar = aVar.f1780f;
                    eVar.f1872m = true;
                    eVar.f1873n = typedArray.getDimension(index, eVar.f1873n);
                    break;
                case 45:
                    e eVar2 = aVar.f1780f;
                    eVar2.f1862c = typedArray.getFloat(index, eVar2.f1862c);
                    break;
                case 46:
                    e eVar3 = aVar.f1780f;
                    eVar3.f1863d = typedArray.getFloat(index, eVar3.f1863d);
                    break;
                case 47:
                    e eVar4 = aVar.f1780f;
                    eVar4.f1864e = typedArray.getFloat(index, eVar4.f1864e);
                    break;
                case 48:
                    e eVar5 = aVar.f1780f;
                    eVar5.f1865f = typedArray.getFloat(index, eVar5.f1865f);
                    break;
                case 49:
                    e eVar6 = aVar.f1780f;
                    eVar6.f1866g = typedArray.getDimension(index, eVar6.f1866g);
                    break;
                case 50:
                    e eVar7 = aVar.f1780f;
                    eVar7.f1867h = typedArray.getDimension(index, eVar7.f1867h);
                    break;
                case 51:
                    e eVar8 = aVar.f1780f;
                    eVar8.f1869j = typedArray.getDimension(index, eVar8.f1869j);
                    break;
                case 52:
                    e eVar9 = aVar.f1780f;
                    eVar9.f1870k = typedArray.getDimension(index, eVar9.f1870k);
                    break;
                case 53:
                    e eVar10 = aVar.f1780f;
                    eVar10.f1871l = typedArray.getDimension(index, eVar10.f1871l);
                    break;
                case 54:
                    b bVar40 = aVar.f1779e;
                    bVar40.Z = typedArray.getInt(index, bVar40.Z);
                    break;
                case 55:
                    b bVar41 = aVar.f1779e;
                    bVar41.f1797a0 = typedArray.getInt(index, bVar41.f1797a0);
                    break;
                case 56:
                    b bVar42 = aVar.f1779e;
                    bVar42.f1799b0 = typedArray.getDimensionPixelSize(index, bVar42.f1799b0);
                    break;
                case 57:
                    b bVar43 = aVar.f1779e;
                    bVar43.f1801c0 = typedArray.getDimensionPixelSize(index, bVar43.f1801c0);
                    break;
                case 58:
                    b bVar44 = aVar.f1779e;
                    bVar44.f1803d0 = typedArray.getDimensionPixelSize(index, bVar44.f1803d0);
                    break;
                case 59:
                    b bVar45 = aVar.f1779e;
                    bVar45.f1805e0 = typedArray.getDimensionPixelSize(index, bVar45.f1805e0);
                    break;
                case 60:
                    e eVar11 = aVar.f1780f;
                    eVar11.f1861b = typedArray.getFloat(index, eVar11.f1861b);
                    break;
                case 61:
                    b bVar46 = aVar.f1779e;
                    bVar46.B = F(typedArray, index, bVar46.B);
                    break;
                case 62:
                    b bVar47 = aVar.f1779e;
                    bVar47.C = typedArray.getDimensionPixelSize(index, bVar47.C);
                    break;
                case 63:
                    b bVar48 = aVar.f1779e;
                    bVar48.D = typedArray.getFloat(index, bVar48.D);
                    break;
                case 64:
                    c cVar = aVar.f1778d;
                    cVar.f1841b = F(typedArray, index, cVar.f1841b);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.f1778d.f1843d = typedArray.getString(index);
                    } else {
                        aVar.f1778d.f1843d = p.c.f8706c[typedArray.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    aVar.f1778d.f1845f = typedArray.getInt(index, 0);
                    break;
                case 67:
                    c cVar2 = aVar.f1778d;
                    cVar2.f1848i = typedArray.getFloat(index, cVar2.f1848i);
                    break;
                case 68:
                    C0018d c0018d4 = aVar.f1777c;
                    c0018d4.f1858e = typedArray.getFloat(index, c0018d4.f1858e);
                    break;
                case 69:
                    aVar.f1779e.f1807f0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    aVar.f1779e.f1809g0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    b bVar49 = aVar.f1779e;
                    bVar49.f1811h0 = typedArray.getInt(index, bVar49.f1811h0);
                    break;
                case 73:
                    b bVar50 = aVar.f1779e;
                    bVar50.f1813i0 = typedArray.getDimensionPixelSize(index, bVar50.f1813i0);
                    break;
                case 74:
                    aVar.f1779e.f1819l0 = typedArray.getString(index);
                    break;
                case 75:
                    b bVar51 = aVar.f1779e;
                    bVar51.f1827p0 = typedArray.getBoolean(index, bVar51.f1827p0);
                    break;
                case 76:
                    c cVar3 = aVar.f1778d;
                    cVar3.f1844e = typedArray.getInt(index, cVar3.f1844e);
                    break;
                case 77:
                    aVar.f1779e.f1821m0 = typedArray.getString(index);
                    break;
                case 78:
                    C0018d c0018d5 = aVar.f1777c;
                    c0018d5.f1856c = typedArray.getInt(index, c0018d5.f1856c);
                    break;
                case 79:
                    c cVar4 = aVar.f1778d;
                    cVar4.f1846g = typedArray.getFloat(index, cVar4.f1846g);
                    break;
                case 80:
                    b bVar52 = aVar.f1779e;
                    bVar52.f1823n0 = typedArray.getBoolean(index, bVar52.f1823n0);
                    break;
                case 81:
                    b bVar53 = aVar.f1779e;
                    bVar53.f1825o0 = typedArray.getBoolean(index, bVar53.f1825o0);
                    break;
                case 82:
                    c cVar5 = aVar.f1778d;
                    cVar5.f1842c = typedArray.getInteger(index, cVar5.f1842c);
                    break;
                case 83:
                    e eVar12 = aVar.f1780f;
                    eVar12.f1868i = F(typedArray, index, eVar12.f1868i);
                    break;
                case 84:
                    c cVar6 = aVar.f1778d;
                    cVar6.f1850k = typedArray.getInteger(index, cVar6.f1850k);
                    break;
                case 85:
                    c cVar7 = aVar.f1778d;
                    cVar7.f1849j = typedArray.getFloat(index, cVar7.f1849j);
                    break;
                case 86:
                    int i8 = typedArray.peekValue(index).type;
                    if (i8 == 1) {
                        aVar.f1778d.f1853n = typedArray.getResourceId(index, -1);
                        c cVar8 = aVar.f1778d;
                        if (cVar8.f1853n != -1) {
                            cVar8.f1852m = -2;
                        }
                    } else if (i8 == 3) {
                        aVar.f1778d.f1851l = typedArray.getString(index);
                        if (aVar.f1778d.f1851l.indexOf("/") > 0) {
                            aVar.f1778d.f1853n = typedArray.getResourceId(index, -1);
                            aVar.f1778d.f1852m = -2;
                        } else {
                            aVar.f1778d.f1852m = -1;
                        }
                    } else {
                        c cVar9 = aVar.f1778d;
                        cVar9.f1852m = typedArray.getInteger(index, cVar9.f1853n);
                    }
                    break;
                case 87:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1766i.get(index));
                    break;
                case 88:
                case 89:
                case 90:
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1766i.get(index));
                    break;
                case 91:
                    b bVar54 = aVar.f1779e;
                    bVar54.f1831s = F(typedArray, index, bVar54.f1831s);
                    break;
                case 92:
                    b bVar55 = aVar.f1779e;
                    bVar55.f1832t = F(typedArray, index, bVar55.f1832t);
                    break;
                case 93:
                    b bVar56 = aVar.f1779e;
                    bVar56.N = typedArray.getDimensionPixelSize(index, bVar56.N);
                    break;
                case 94:
                    b bVar57 = aVar.f1779e;
                    bVar57.U = typedArray.getDimensionPixelSize(index, bVar57.U);
                    break;
                case 95:
                    G(aVar.f1779e, typedArray, index, 0);
                    break;
                case 96:
                    G(aVar.f1779e, typedArray, index, 1);
                    break;
                case 97:
                    b bVar58 = aVar.f1779e;
                    bVar58.f1829q0 = typedArray.getInt(index, bVar58.f1829q0);
                    break;
            }
        }
        b bVar59 = aVar.f1779e;
        if (bVar59.f1819l0 != null) {
            bVar59.f1817k0 = null;
        }
    }

    private static void K(Context context, a aVar, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        a.C0017a c0017a = new a.C0017a();
        aVar.f1782h = c0017a;
        aVar.f1778d.f1840a = false;
        aVar.f1779e.f1798b = false;
        aVar.f1777c.f1854a = false;
        aVar.f1780f.f1860a = false;
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArray.getIndex(i7);
            switch (f1767j.get(index)) {
                case 2:
                    c0017a.b(2, typedArray.getDimensionPixelSize(index, aVar.f1779e.K));
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
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f1766i.get(index));
                    break;
                case 5:
                    c0017a.c(5, typedArray.getString(index));
                    break;
                case 6:
                    c0017a.b(6, typedArray.getDimensionPixelOffset(index, aVar.f1779e.E));
                    break;
                case 7:
                    c0017a.b(7, typedArray.getDimensionPixelOffset(index, aVar.f1779e.F));
                    break;
                case 8:
                    c0017a.b(8, typedArray.getDimensionPixelSize(index, aVar.f1779e.L));
                    break;
                case 11:
                    c0017a.b(11, typedArray.getDimensionPixelSize(index, aVar.f1779e.R));
                    break;
                case 12:
                    c0017a.b(12, typedArray.getDimensionPixelSize(index, aVar.f1779e.S));
                    break;
                case 13:
                    c0017a.b(13, typedArray.getDimensionPixelSize(index, aVar.f1779e.O));
                    break;
                case 14:
                    c0017a.b(14, typedArray.getDimensionPixelSize(index, aVar.f1779e.Q));
                    break;
                case 15:
                    c0017a.b(15, typedArray.getDimensionPixelSize(index, aVar.f1779e.T));
                    break;
                case 16:
                    c0017a.b(16, typedArray.getDimensionPixelSize(index, aVar.f1779e.P));
                    break;
                case 17:
                    c0017a.b(17, typedArray.getDimensionPixelOffset(index, aVar.f1779e.f1806f));
                    break;
                case 18:
                    c0017a.b(18, typedArray.getDimensionPixelOffset(index, aVar.f1779e.f1808g));
                    break;
                case 19:
                    c0017a.a(19, typedArray.getFloat(index, aVar.f1779e.f1810h));
                    break;
                case 20:
                    c0017a.a(20, typedArray.getFloat(index, aVar.f1779e.f1837y));
                    break;
                case 21:
                    c0017a.b(21, typedArray.getLayoutDimension(index, aVar.f1779e.f1804e));
                    break;
                case 22:
                    c0017a.b(22, f1765h[typedArray.getInt(index, aVar.f1777c.f1855b)]);
                    break;
                case 23:
                    c0017a.b(23, typedArray.getLayoutDimension(index, aVar.f1779e.f1802d));
                    break;
                case 24:
                    c0017a.b(24, typedArray.getDimensionPixelSize(index, aVar.f1779e.H));
                    break;
                case 27:
                    c0017a.b(27, typedArray.getInt(index, aVar.f1779e.G));
                    break;
                case 28:
                    c0017a.b(28, typedArray.getDimensionPixelSize(index, aVar.f1779e.I));
                    break;
                case 31:
                    c0017a.b(31, typedArray.getDimensionPixelSize(index, aVar.f1779e.M));
                    break;
                case 34:
                    c0017a.b(34, typedArray.getDimensionPixelSize(index, aVar.f1779e.J));
                    break;
                case 37:
                    c0017a.a(37, typedArray.getFloat(index, aVar.f1779e.f1838z));
                    break;
                case 38:
                    int resourceId = typedArray.getResourceId(index, aVar.f1775a);
                    aVar.f1775a = resourceId;
                    c0017a.b(38, resourceId);
                    break;
                case 39:
                    c0017a.a(39, typedArray.getFloat(index, aVar.f1779e.W));
                    break;
                case 40:
                    c0017a.a(40, typedArray.getFloat(index, aVar.f1779e.V));
                    break;
                case 41:
                    c0017a.b(41, typedArray.getInt(index, aVar.f1779e.X));
                    break;
                case 42:
                    c0017a.b(42, typedArray.getInt(index, aVar.f1779e.Y));
                    break;
                case 43:
                    c0017a.a(43, typedArray.getFloat(index, aVar.f1777c.f1857d));
                    break;
                case 44:
                    c0017a.d(44, true);
                    c0017a.a(44, typedArray.getDimension(index, aVar.f1780f.f1873n));
                    break;
                case 45:
                    c0017a.a(45, typedArray.getFloat(index, aVar.f1780f.f1862c));
                    break;
                case 46:
                    c0017a.a(46, typedArray.getFloat(index, aVar.f1780f.f1863d));
                    break;
                case 47:
                    c0017a.a(47, typedArray.getFloat(index, aVar.f1780f.f1864e));
                    break;
                case 48:
                    c0017a.a(48, typedArray.getFloat(index, aVar.f1780f.f1865f));
                    break;
                case 49:
                    c0017a.a(49, typedArray.getDimension(index, aVar.f1780f.f1866g));
                    break;
                case 50:
                    c0017a.a(50, typedArray.getDimension(index, aVar.f1780f.f1867h));
                    break;
                case 51:
                    c0017a.a(51, typedArray.getDimension(index, aVar.f1780f.f1869j));
                    break;
                case 52:
                    c0017a.a(52, typedArray.getDimension(index, aVar.f1780f.f1870k));
                    break;
                case 53:
                    c0017a.a(53, typedArray.getDimension(index, aVar.f1780f.f1871l));
                    break;
                case 54:
                    c0017a.b(54, typedArray.getInt(index, aVar.f1779e.Z));
                    break;
                case 55:
                    c0017a.b(55, typedArray.getInt(index, aVar.f1779e.f1797a0));
                    break;
                case 56:
                    c0017a.b(56, typedArray.getDimensionPixelSize(index, aVar.f1779e.f1799b0));
                    break;
                case 57:
                    c0017a.b(57, typedArray.getDimensionPixelSize(index, aVar.f1779e.f1801c0));
                    break;
                case 58:
                    c0017a.b(58, typedArray.getDimensionPixelSize(index, aVar.f1779e.f1803d0));
                    break;
                case 59:
                    c0017a.b(59, typedArray.getDimensionPixelSize(index, aVar.f1779e.f1805e0));
                    break;
                case 60:
                    c0017a.a(60, typedArray.getFloat(index, aVar.f1780f.f1861b));
                    break;
                case 62:
                    c0017a.b(62, typedArray.getDimensionPixelSize(index, aVar.f1779e.C));
                    break;
                case 63:
                    c0017a.a(63, typedArray.getFloat(index, aVar.f1779e.D));
                    break;
                case 64:
                    c0017a.b(64, F(typedArray, index, aVar.f1778d.f1841b));
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        c0017a.c(65, typedArray.getString(index));
                    } else {
                        c0017a.c(65, p.c.f8706c[typedArray.getInteger(index, 0)]);
                    }
                    break;
                case 66:
                    c0017a.b(66, typedArray.getInt(index, 0));
                    break;
                case 67:
                    c0017a.a(67, typedArray.getFloat(index, aVar.f1778d.f1848i));
                    break;
                case 68:
                    c0017a.a(68, typedArray.getFloat(index, aVar.f1777c.f1858e));
                    break;
                case 69:
                    c0017a.a(69, typedArray.getFloat(index, 1.0f));
                    break;
                case 70:
                    c0017a.a(70, typedArray.getFloat(index, 1.0f));
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    c0017a.b(72, typedArray.getInt(index, aVar.f1779e.f1811h0));
                    break;
                case 73:
                    c0017a.b(73, typedArray.getDimensionPixelSize(index, aVar.f1779e.f1813i0));
                    break;
                case 74:
                    c0017a.c(74, typedArray.getString(index));
                    break;
                case 75:
                    c0017a.d(75, typedArray.getBoolean(index, aVar.f1779e.f1827p0));
                    break;
                case 76:
                    c0017a.b(76, typedArray.getInt(index, aVar.f1778d.f1844e));
                    break;
                case 77:
                    c0017a.c(77, typedArray.getString(index));
                    break;
                case 78:
                    c0017a.b(78, typedArray.getInt(index, aVar.f1777c.f1856c));
                    break;
                case 79:
                    c0017a.a(79, typedArray.getFloat(index, aVar.f1778d.f1846g));
                    break;
                case 80:
                    c0017a.d(80, typedArray.getBoolean(index, aVar.f1779e.f1823n0));
                    break;
                case 81:
                    c0017a.d(81, typedArray.getBoolean(index, aVar.f1779e.f1825o0));
                    break;
                case 82:
                    c0017a.b(82, typedArray.getInteger(index, aVar.f1778d.f1842c));
                    break;
                case 83:
                    c0017a.b(83, F(typedArray, index, aVar.f1780f.f1868i));
                    break;
                case 84:
                    c0017a.b(84, typedArray.getInteger(index, aVar.f1778d.f1850k));
                    break;
                case 85:
                    c0017a.a(85, typedArray.getFloat(index, aVar.f1778d.f1849j));
                    break;
                case 86:
                    int i8 = typedArray.peekValue(index).type;
                    if (i8 == 1) {
                        aVar.f1778d.f1853n = typedArray.getResourceId(index, -1);
                        c0017a.b(89, aVar.f1778d.f1853n);
                        c cVar = aVar.f1778d;
                        if (cVar.f1853n != -1) {
                            cVar.f1852m = -2;
                            c0017a.b(88, -2);
                        }
                    } else if (i8 == 3) {
                        aVar.f1778d.f1851l = typedArray.getString(index);
                        c0017a.c(90, aVar.f1778d.f1851l);
                        if (aVar.f1778d.f1851l.indexOf("/") > 0) {
                            aVar.f1778d.f1853n = typedArray.getResourceId(index, -1);
                            c0017a.b(89, aVar.f1778d.f1853n);
                            aVar.f1778d.f1852m = -2;
                            c0017a.b(88, -2);
                        } else {
                            aVar.f1778d.f1852m = -1;
                            c0017a.b(88, -1);
                        }
                    } else {
                        c cVar2 = aVar.f1778d;
                        cVar2.f1852m = typedArray.getInteger(index, cVar2.f1853n);
                        c0017a.b(88, aVar.f1778d.f1852m);
                    }
                    break;
                case 87:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f1766i.get(index));
                    break;
                case 93:
                    c0017a.b(93, typedArray.getDimensionPixelSize(index, aVar.f1779e.N));
                    break;
                case 94:
                    c0017a.b(94, typedArray.getDimensionPixelSize(index, aVar.f1779e.U));
                    break;
                case 95:
                    G(c0017a, typedArray, index, 0);
                    break;
                case 96:
                    G(c0017a, typedArray, index, 1);
                    break;
                case 97:
                    c0017a.b(97, typedArray.getInt(index, aVar.f1779e.f1829q0));
                    break;
                case 98:
                    if (MotionLayout.f1260h1) {
                        int resourceId2 = typedArray.getResourceId(index, aVar.f1775a);
                        aVar.f1775a = resourceId2;
                        if (resourceId2 == -1) {
                            aVar.f1776b = typedArray.getString(index);
                        }
                    } else if (typedArray.peekValue(index).type == 3) {
                        aVar.f1776b = typedArray.getString(index);
                    } else {
                        aVar.f1775a = typedArray.getResourceId(index, aVar.f1775a);
                    }
                    break;
                case 99:
                    c0017a.d(99, typedArray.getBoolean(index, aVar.f1779e.f1812i));
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void N(a aVar, int i7, float f7) {
        if (i7 == 19) {
            aVar.f1779e.f1810h = f7;
        }
        if (i7 == 20) {
            aVar.f1779e.f1837y = f7;
            return;
        }
        if (i7 == 37) {
            aVar.f1779e.f1838z = f7;
            return;
        }
        if (i7 == 60) {
            aVar.f1780f.f1861b = f7;
            return;
        }
        if (i7 == 63) {
            aVar.f1779e.D = f7;
            return;
        }
        if (i7 == 79) {
            aVar.f1778d.f1846g = f7;
            return;
        }
        if (i7 == 85) {
            aVar.f1778d.f1849j = f7;
            return;
        }
        if (i7 != 87) {
            if (i7 == 39) {
                aVar.f1779e.W = f7;
                return;
            }
            if (i7 == 40) {
                aVar.f1779e.V = f7;
                return;
            }
            switch (i7) {
                case 43:
                    aVar.f1777c.f1857d = f7;
                    break;
                case 44:
                    e eVar = aVar.f1780f;
                    eVar.f1873n = f7;
                    eVar.f1872m = true;
                    break;
                case 45:
                    aVar.f1780f.f1862c = f7;
                    break;
                case 46:
                    aVar.f1780f.f1863d = f7;
                    break;
                case 47:
                    aVar.f1780f.f1864e = f7;
                    break;
                case 48:
                    aVar.f1780f.f1865f = f7;
                    break;
                case 49:
                    aVar.f1780f.f1866g = f7;
                    break;
                case 50:
                    aVar.f1780f.f1867h = f7;
                    break;
                case 51:
                    aVar.f1780f.f1869j = f7;
                    break;
                case 52:
                    aVar.f1780f.f1870k = f7;
                    break;
                case 53:
                    aVar.f1780f.f1871l = f7;
                    break;
                default:
                    switch (i7) {
                        case 67:
                            aVar.f1778d.f1848i = f7;
                            break;
                        case 68:
                            aVar.f1777c.f1858e = f7;
                            break;
                        case 69:
                            aVar.f1779e.f1807f0 = f7;
                            break;
                        case 70:
                            aVar.f1779e.f1809g0 = f7;
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
    public static void O(a aVar, int i7, int i8) {
        if (i7 == 6) {
            aVar.f1779e.E = i8;
        }
        if (i7 == 7) {
            aVar.f1779e.F = i8;
            return;
        }
        if (i7 == 8) {
            aVar.f1779e.L = i8;
            return;
        }
        if (i7 == 27) {
            aVar.f1779e.G = i8;
            return;
        }
        if (i7 == 28) {
            aVar.f1779e.I = i8;
            return;
        }
        if (i7 == 41) {
            aVar.f1779e.X = i8;
            return;
        }
        if (i7 == 42) {
            aVar.f1779e.Y = i8;
            return;
        }
        if (i7 == 61) {
            aVar.f1779e.B = i8;
            return;
        }
        if (i7 == 62) {
            aVar.f1779e.C = i8;
            return;
        }
        if (i7 == 72) {
            aVar.f1779e.f1811h0 = i8;
            return;
        }
        if (i7 == 73) {
            aVar.f1779e.f1813i0 = i8;
            return;
        }
        switch (i7) {
            case 2:
                aVar.f1779e.K = i8;
                break;
            case 11:
                aVar.f1779e.R = i8;
                break;
            case 12:
                aVar.f1779e.S = i8;
                break;
            case 13:
                aVar.f1779e.O = i8;
                break;
            case 14:
                aVar.f1779e.Q = i8;
                break;
            case 15:
                aVar.f1779e.T = i8;
                break;
            case 16:
                aVar.f1779e.P = i8;
                break;
            case 17:
                aVar.f1779e.f1806f = i8;
                break;
            case 18:
                aVar.f1779e.f1808g = i8;
                break;
            case 31:
                aVar.f1779e.M = i8;
                break;
            case 34:
                aVar.f1779e.J = i8;
                break;
            case 38:
                aVar.f1775a = i8;
                break;
            case 64:
                aVar.f1778d.f1841b = i8;
                break;
            case 66:
                aVar.f1778d.f1845f = i8;
                break;
            case 76:
                aVar.f1778d.f1844e = i8;
                break;
            case 78:
                aVar.f1777c.f1856c = i8;
                break;
            case 93:
                aVar.f1779e.N = i8;
                break;
            case 94:
                aVar.f1779e.U = i8;
                break;
            case 97:
                aVar.f1779e.f1829q0 = i8;
                break;
            default:
                switch (i7) {
                    case 21:
                        aVar.f1779e.f1804e = i8;
                        break;
                    case 22:
                        aVar.f1777c.f1855b = i8;
                        break;
                    case 23:
                        aVar.f1779e.f1802d = i8;
                        break;
                    case 24:
                        aVar.f1779e.H = i8;
                        break;
                    default:
                        switch (i7) {
                            case 54:
                                aVar.f1779e.Z = i8;
                                break;
                            case 55:
                                aVar.f1779e.f1797a0 = i8;
                                break;
                            case 56:
                                aVar.f1779e.f1799b0 = i8;
                                break;
                            case 57:
                                aVar.f1779e.f1801c0 = i8;
                                break;
                            case 58:
                                aVar.f1779e.f1803d0 = i8;
                                break;
                            case 59:
                                aVar.f1779e.f1805e0 = i8;
                                break;
                            default:
                                switch (i7) {
                                    case 82:
                                        aVar.f1778d.f1842c = i8;
                                        break;
                                    case 83:
                                        aVar.f1780f.f1868i = i8;
                                        break;
                                    case 84:
                                        aVar.f1778d.f1850k = i8;
                                        break;
                                    default:
                                        switch (i7) {
                                            case 87:
                                                break;
                                            case 88:
                                                aVar.f1778d.f1852m = i8;
                                                break;
                                            case 89:
                                                aVar.f1778d.f1853n = i8;
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
    public static void P(a aVar, int i7, String str) {
        if (i7 == 5) {
            aVar.f1779e.A = str;
            return;
        }
        if (i7 == 65) {
            aVar.f1778d.f1843d = str;
            return;
        }
        if (i7 == 74) {
            b bVar = aVar.f1779e;
            bVar.f1819l0 = str;
            bVar.f1817k0 = null;
        } else if (i7 == 77) {
            aVar.f1779e.f1821m0 = str;
        } else if (i7 != 87) {
            if (i7 != 90) {
                Log.w("ConstraintSet", "Unknown attribute 0x");
            } else {
                aVar.f1778d.f1851l = str;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void Q(a aVar, int i7, boolean z6) {
        if (i7 == 44) {
            aVar.f1780f.f1872m = z6;
            return;
        }
        if (i7 == 75) {
            aVar.f1779e.f1827p0 = z6;
            return;
        }
        if (i7 != 87) {
            if (i7 == 80) {
                aVar.f1779e.f1823n0 = z6;
            } else if (i7 != 81) {
                Log.w("ConstraintSet", "Unknown attribute 0x");
            } else {
                aVar.f1779e.f1825o0 = z6;
            }
        }
    }

    public static a m(Context context, XmlPullParser xmlPullParser) {
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xmlPullParser);
        a aVar = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSetAsAttributeSet, i.ConstraintOverride);
        K(context, aVar, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    private int[] t(View view, String str) {
        int iIntValue;
        Object objG;
        String[] strArrSplit = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i7 = 0;
        int i8 = 0;
        while (i7 < strArrSplit.length) {
            String strTrim = strArrSplit[i7].trim();
            try {
                iIntValue = h.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
                iIntValue = 0;
            }
            if (iIntValue == 0) {
                iIntValue = context.getResources().getIdentifier(strTrim, "id", context.getPackageName());
            }
            if (iIntValue == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (objG = ((ConstraintLayout) view.getParent()).g(0, strTrim)) != null && (objG instanceof Integer)) {
                iIntValue = ((Integer) objG).intValue();
            }
            iArr[i8] = iIntValue;
            i7++;
            i8++;
        }
        return i8 != strArrSplit.length ? Arrays.copyOf(iArr, i8) : iArr;
    }

    private a u(Context context, AttributeSet attributeSet, boolean z6) {
        a aVar = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z6 ? i.ConstraintOverride : i.Constraint);
        J(context, aVar, typedArrayObtainStyledAttributes, z6);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    private a v(int i7) {
        if (!this.f1774g.containsKey(Integer.valueOf(i7))) {
            this.f1774g.put(Integer.valueOf(i7), new a());
        }
        return this.f1774g.get(Integer.valueOf(i7));
    }

    public int A(int i7) {
        return v(i7).f1777c.f1855b;
    }

    public int B(int i7) {
        return v(i7).f1777c.f1856c;
    }

    public int C(int i7) {
        return v(i7).f1779e.f1802d;
    }

    public void D(Context context, int i7) {
        XmlResourceParser xml = context.getResources().getXml(i7);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    a aVarU = u(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        aVarU.f1779e.f1796a = true;
                    }
                    this.f1774g.put(Integer.valueOf(aVarU.f1775a), aVarU);
                }
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        } catch (XmlPullParserException e8) {
            e8.printStackTrace();
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
    public void E(android.content.Context r10, org.xmlpull.v1.XmlPullParser r11) {
        /*
            Method dump skipped, instruction units count: 560
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.d.E(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public void L(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1773f && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1774g.containsKey(Integer.valueOf(id))) {
                this.f1774g.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f1774g.get(Integer.valueOf(id));
            if (aVar != null) {
                if (!aVar.f1779e.f1798b) {
                    aVar.g(id, bVar);
                    if (childAt instanceof androidx.constraintlayout.widget.b) {
                        aVar.f1779e.f1817k0 = ((androidx.constraintlayout.widget.b) childAt).getReferencedIds();
                        if (childAt instanceof Barrier) {
                            Barrier barrier = (Barrier) childAt;
                            aVar.f1779e.f1827p0 = barrier.getAllowsGoneWidget();
                            aVar.f1779e.f1811h0 = barrier.getType();
                            aVar.f1779e.f1813i0 = barrier.getMargin();
                        }
                    }
                    aVar.f1779e.f1798b = true;
                }
                C0018d c0018d = aVar.f1777c;
                if (!c0018d.f1854a) {
                    c0018d.f1855b = childAt.getVisibility();
                    aVar.f1777c.f1857d = childAt.getAlpha();
                    aVar.f1777c.f1854a = true;
                }
                e eVar = aVar.f1780f;
                if (!eVar.f1860a) {
                    eVar.f1860a = true;
                    eVar.f1861b = childAt.getRotation();
                    aVar.f1780f.f1862c = childAt.getRotationX();
                    aVar.f1780f.f1863d = childAt.getRotationY();
                    aVar.f1780f.f1864e = childAt.getScaleX();
                    aVar.f1780f.f1865f = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        e eVar2 = aVar.f1780f;
                        eVar2.f1866g = pivotX;
                        eVar2.f1867h = pivotY;
                    }
                    aVar.f1780f.f1869j = childAt.getTranslationX();
                    aVar.f1780f.f1870k = childAt.getTranslationY();
                    aVar.f1780f.f1871l = childAt.getTranslationZ();
                    e eVar3 = aVar.f1780f;
                    if (eVar3.f1872m) {
                        eVar3.f1873n = childAt.getElevation();
                    }
                }
            }
        }
    }

    public void M(d dVar) {
        for (Integer num : dVar.f1774g.keySet()) {
            int iIntValue = num.intValue();
            a aVar = dVar.f1774g.get(num);
            if (!this.f1774g.containsKey(Integer.valueOf(iIntValue))) {
                this.f1774g.put(Integer.valueOf(iIntValue), new a());
            }
            a aVar2 = this.f1774g.get(Integer.valueOf(iIntValue));
            if (aVar2 != null) {
                b bVar = aVar2.f1779e;
                if (!bVar.f1798b) {
                    bVar.a(aVar.f1779e);
                }
                C0018d c0018d = aVar2.f1777c;
                if (!c0018d.f1854a) {
                    c0018d.a(aVar.f1777c);
                }
                e eVar = aVar2.f1780f;
                if (!eVar.f1860a) {
                    eVar.a(aVar.f1780f);
                }
                c cVar = aVar2.f1778d;
                if (!cVar.f1840a) {
                    cVar.a(aVar.f1778d);
                }
                for (String str : aVar.f1781g.keySet()) {
                    if (!aVar2.f1781g.containsKey(str)) {
                        aVar2.f1781g.put(str, aVar.f1781g.get(str));
                    }
                }
            }
        }
    }

    public void R(boolean z6) {
        this.f1773f = z6;
    }

    public void S(boolean z6) {
        this.f1768a = z6;
    }

    public void g(ConstraintLayout constraintLayout) {
        a aVar;
        int childCount = constraintLayout.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
            int id = childAt.getId();
            if (!this.f1774g.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + androidx.constraintlayout.motion.widget.a.d(childAt));
            } else {
                if (this.f1773f && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (this.f1774g.containsKey(Integer.valueOf(id)) && (aVar = this.f1774g.get(Integer.valueOf(id))) != null) {
                    androidx.constraintlayout.widget.a.j(childAt, aVar.f1781g);
                }
            }
        }
    }

    public void h(d dVar) {
        for (a aVar : dVar.f1774g.values()) {
            if (aVar.f1782h != null) {
                if (aVar.f1776b != null) {
                    Iterator<Integer> it = this.f1774g.keySet().iterator();
                    while (it.hasNext()) {
                        a aVarW = w(it.next().intValue());
                        String str = aVarW.f1779e.f1821m0;
                        if (str != null && aVar.f1776b.matches(str)) {
                            aVar.f1782h.e(aVarW);
                            aVarW.f1781g.putAll((HashMap) aVar.f1781g.clone());
                        }
                    }
                } else {
                    aVar.f1782h.e(w(aVar.f1775a));
                }
            }
        }
    }

    public void i(ConstraintLayout constraintLayout) {
        k(constraintLayout, true);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public void j(androidx.constraintlayout.widget.b bVar, r.e eVar, ConstraintLayout.b bVar2, SparseArray<r.e> sparseArray) {
        a aVar;
        int id = bVar.getId();
        if (this.f1774g.containsKey(Integer.valueOf(id)) && (aVar = this.f1774g.get(Integer.valueOf(id))) != null && (eVar instanceof r.j)) {
            bVar.p(aVar, (r.j) eVar, bVar2, sparseArray);
        }
    }

    void k(ConstraintLayout constraintLayout, boolean z6) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.f1774g.keySet());
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
            int id = childAt.getId();
            if (!this.f1774g.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + androidx.constraintlayout.motion.widget.a.d(childAt));
            } else {
                if (this.f1773f && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.f1774g.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = this.f1774g.get(Integer.valueOf(id));
                        if (aVar != null) {
                            if (childAt instanceof Barrier) {
                                aVar.f1779e.f1815j0 = 1;
                                Barrier barrier = (Barrier) childAt;
                                barrier.setId(id);
                                barrier.setType(aVar.f1779e.f1811h0);
                                barrier.setMargin(aVar.f1779e.f1813i0);
                                barrier.setAllowsGoneWidget(aVar.f1779e.f1827p0);
                                b bVar = aVar.f1779e;
                                int[] iArr = bVar.f1817k0;
                                if (iArr != null) {
                                    barrier.setReferencedIds(iArr);
                                } else {
                                    String str = bVar.f1819l0;
                                    if (str != null) {
                                        bVar.f1817k0 = t(barrier, str);
                                        barrier.setReferencedIds(aVar.f1779e.f1817k0);
                                    }
                                }
                            }
                            ConstraintLayout.b bVar2 = (ConstraintLayout.b) childAt.getLayoutParams();
                            bVar2.b();
                            aVar.e(bVar2);
                            if (z6) {
                                androidx.constraintlayout.widget.a.j(childAt, aVar.f1781g);
                            }
                            childAt.setLayoutParams(bVar2);
                            C0018d c0018d = aVar.f1777c;
                            if (c0018d.f1856c == 0) {
                                childAt.setVisibility(c0018d.f1855b);
                            }
                            childAt.setAlpha(aVar.f1777c.f1857d);
                            childAt.setRotation(aVar.f1780f.f1861b);
                            childAt.setRotationX(aVar.f1780f.f1862c);
                            childAt.setRotationY(aVar.f1780f.f1863d);
                            childAt.setScaleX(aVar.f1780f.f1864e);
                            childAt.setScaleY(aVar.f1780f.f1865f);
                            e eVar = aVar.f1780f;
                            if (eVar.f1868i != -1) {
                                if (((View) childAt.getParent()).findViewById(aVar.f1780f.f1868i) != null) {
                                    float top = (r4.getTop() + r4.getBottom()) / 2.0f;
                                    float left = (r4.getLeft() + r4.getRight()) / 2.0f;
                                    if (childAt.getRight() - childAt.getLeft() > 0 && childAt.getBottom() - childAt.getTop() > 0) {
                                        childAt.setPivotX(left - childAt.getLeft());
                                        childAt.setPivotY(top - childAt.getTop());
                                    }
                                }
                            } else {
                                if (!Float.isNaN(eVar.f1866g)) {
                                    childAt.setPivotX(aVar.f1780f.f1866g);
                                }
                                if (!Float.isNaN(aVar.f1780f.f1867h)) {
                                    childAt.setPivotY(aVar.f1780f.f1867h);
                                }
                            }
                            childAt.setTranslationX(aVar.f1780f.f1869j);
                            childAt.setTranslationY(aVar.f1780f.f1870k);
                            childAt.setTranslationZ(aVar.f1780f.f1871l);
                            e eVar2 = aVar.f1780f;
                            if (eVar2.f1872m) {
                                childAt.setElevation(eVar2.f1873n);
                            }
                        }
                    } else {
                        Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + id);
                    }
                }
            }
        }
        for (Integer num : hashSet) {
            a aVar2 = this.f1774g.get(num);
            if (aVar2 != null) {
                if (aVar2.f1779e.f1815j0 == 1) {
                    Barrier barrier2 = new Barrier(constraintLayout.getContext());
                    barrier2.setId(num.intValue());
                    b bVar3 = aVar2.f1779e;
                    int[] iArr2 = bVar3.f1817k0;
                    if (iArr2 != null) {
                        barrier2.setReferencedIds(iArr2);
                    } else {
                        String str2 = bVar3.f1819l0;
                        if (str2 != null) {
                            bVar3.f1817k0 = t(barrier2, str2);
                            barrier2.setReferencedIds(aVar2.f1779e.f1817k0);
                        }
                    }
                    barrier2.setType(aVar2.f1779e.f1811h0);
                    barrier2.setMargin(aVar2.f1779e.f1813i0);
                    ConstraintLayout.b bVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                    barrier2.w();
                    aVar2.e(bVarGenerateDefaultLayoutParams);
                    constraintLayout.addView(barrier2, bVarGenerateDefaultLayoutParams);
                }
                if (aVar2.f1779e.f1796a) {
                    View guideline = new Guideline(constraintLayout.getContext());
                    guideline.setId(num.intValue());
                    ConstraintLayout.b bVarGenerateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                    aVar2.e(bVarGenerateDefaultLayoutParams2);
                    constraintLayout.addView(guideline, bVarGenerateDefaultLayoutParams2);
                }
            }
        }
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt2 = constraintLayout.getChildAt(i8);
            if (childAt2 instanceof androidx.constraintlayout.widget.b) {
                ((androidx.constraintlayout.widget.b) childAt2).j(constraintLayout);
            }
        }
    }

    public void l(int i7, ConstraintLayout.b bVar) {
        a aVar;
        if (!this.f1774g.containsKey(Integer.valueOf(i7)) || (aVar = this.f1774g.get(Integer.valueOf(i7))) == null) {
            return;
        }
        aVar.e(bVar);
    }

    public void n(int i7, int i8) {
        a aVar;
        if (!this.f1774g.containsKey(Integer.valueOf(i7)) || (aVar = this.f1774g.get(Integer.valueOf(i7))) == null) {
            return;
        }
        switch (i8) {
            case 1:
                b bVar = aVar.f1779e;
                bVar.f1816k = -1;
                bVar.f1814j = -1;
                bVar.H = -1;
                bVar.O = Integer.MIN_VALUE;
                return;
            case 2:
                b bVar2 = aVar.f1779e;
                bVar2.f1820m = -1;
                bVar2.f1818l = -1;
                bVar2.I = -1;
                bVar2.Q = Integer.MIN_VALUE;
                return;
            case 3:
                b bVar3 = aVar.f1779e;
                bVar3.f1824o = -1;
                bVar3.f1822n = -1;
                bVar3.J = 0;
                bVar3.P = Integer.MIN_VALUE;
                return;
            case 4:
                b bVar4 = aVar.f1779e;
                bVar4.f1826p = -1;
                bVar4.f1828q = -1;
                bVar4.K = 0;
                bVar4.R = Integer.MIN_VALUE;
                return;
            case 5:
                b bVar5 = aVar.f1779e;
                bVar5.f1830r = -1;
                bVar5.f1831s = -1;
                bVar5.f1832t = -1;
                bVar5.N = 0;
                bVar5.U = Integer.MIN_VALUE;
                return;
            case 6:
                b bVar6 = aVar.f1779e;
                bVar6.f1833u = -1;
                bVar6.f1834v = -1;
                bVar6.M = 0;
                bVar6.T = Integer.MIN_VALUE;
                return;
            case 7:
                b bVar7 = aVar.f1779e;
                bVar7.f1835w = -1;
                bVar7.f1836x = -1;
                bVar7.L = 0;
                bVar7.S = Integer.MIN_VALUE;
                return;
            case 8:
                b bVar8 = aVar.f1779e;
                bVar8.D = -1.0f;
                bVar8.C = -1;
                bVar8.B = -1;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void o(Context context, int i7) {
        p((ConstraintLayout) LayoutInflater.from(context).inflate(i7, (ViewGroup) null));
    }

    public void p(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.f1774g.clear();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = constraintLayout.getChildAt(i7);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1773f && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1774g.containsKey(Integer.valueOf(id))) {
                this.f1774g.put(Integer.valueOf(id), new a());
            }
            a aVar = this.f1774g.get(Integer.valueOf(id));
            if (aVar != null) {
                aVar.f1781g = androidx.constraintlayout.widget.a.b(this.f1772e, childAt);
                aVar.g(id, bVar);
                aVar.f1777c.f1855b = childAt.getVisibility();
                aVar.f1777c.f1857d = childAt.getAlpha();
                aVar.f1780f.f1861b = childAt.getRotation();
                aVar.f1780f.f1862c = childAt.getRotationX();
                aVar.f1780f.f1863d = childAt.getRotationY();
                aVar.f1780f.f1864e = childAt.getScaleX();
                aVar.f1780f.f1865f = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    e eVar = aVar.f1780f;
                    eVar.f1866g = pivotX;
                    eVar.f1867h = pivotY;
                }
                aVar.f1780f.f1869j = childAt.getTranslationX();
                aVar.f1780f.f1870k = childAt.getTranslationY();
                aVar.f1780f.f1871l = childAt.getTranslationZ();
                e eVar2 = aVar.f1780f;
                if (eVar2.f1872m) {
                    eVar2.f1873n = childAt.getElevation();
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    aVar.f1779e.f1827p0 = barrier.getAllowsGoneWidget();
                    aVar.f1779e.f1817k0 = barrier.getReferencedIds();
                    aVar.f1779e.f1811h0 = barrier.getType();
                    aVar.f1779e.f1813i0 = barrier.getMargin();
                }
            }
        }
    }

    public void q(d dVar) {
        this.f1774g.clear();
        for (Integer num : dVar.f1774g.keySet()) {
            a aVar = dVar.f1774g.get(num);
            if (aVar != null) {
                this.f1774g.put(num, aVar.clone());
            }
        }
    }

    public void r(androidx.constraintlayout.widget.e eVar) {
        int childCount = eVar.getChildCount();
        this.f1774g.clear();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = eVar.getChildAt(i7);
            e.a aVar = (e.a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.f1773f && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f1774g.containsKey(Integer.valueOf(id))) {
                this.f1774g.put(Integer.valueOf(id), new a());
            }
            a aVar2 = this.f1774g.get(Integer.valueOf(id));
            if (aVar2 != null) {
                if (childAt instanceof androidx.constraintlayout.widget.b) {
                    aVar2.i((androidx.constraintlayout.widget.b) childAt, id, aVar);
                }
                aVar2.h(id, aVar);
            }
        }
    }

    public void s(int i7, int i8, int i9, float f7) {
        b bVar = v(i7).f1779e;
        bVar.B = i8;
        bVar.C = i9;
        bVar.D = f7;
    }

    public a w(int i7) {
        if (this.f1774g.containsKey(Integer.valueOf(i7))) {
            return this.f1774g.get(Integer.valueOf(i7));
        }
        return null;
    }

    public int x(int i7) {
        return v(i7).f1779e.f1804e;
    }

    public int[] y() {
        Integer[] numArr = (Integer[]) this.f1774g.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i7 = 0; i7 < length; i7++) {
            iArr[i7] = numArr[i7].intValue();
        }
        return iArr;
    }

    public a z(int i7) {
        return v(i7);
    }
}
