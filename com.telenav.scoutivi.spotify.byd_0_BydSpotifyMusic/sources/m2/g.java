package m2;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import m2.b;

/* JADX INFO: compiled from: SVG.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static boolean f8134g = true;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private f0 f8135a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f8136b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f8137c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f8138d = 96.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b.r f8139e = new b.r();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Map<String, l0> f8140f = new HashMap();

    /* JADX INFO: compiled from: SVG.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f8141a;

        static {
            int[] iArr = new int[d1.values().length];
            f8141a = iArr;
            try {
                iArr[d1.px.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8141a[d1.em.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8141a[d1.ex.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8141a[d1.in.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8141a[d1.cm.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8141a[d1.mm.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8141a[d1.pt.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8141a[d1.pc.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f8141a[d1.percent.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class a0 extends z {
        a0() {
        }

        @Override // m2.g.z, m2.g.l0
        String o() {
            return "polygon";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static abstract class a1 extends y0 {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        List<p> f8142o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        List<p> f8143p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        List<p> f8144q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        List<p> f8145r;

        a1() {
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class b0 extends l {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        p f8150o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        p f8151p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        p f8152q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        p f8153r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        p f8154s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        p f8155t;

        b0() {
        }

        @Override // m2.g.l0
        String o() {
            return "rect";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    interface b1 {
    }

    /* JADX INFO: compiled from: SVG.java */
    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        p f8156a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        p f8157b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        p f8158c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        p f8159d;

        c(p pVar, p pVar2, p pVar3, p pVar4) {
            this.f8156a = pVar;
            this.f8157b = pVar2;
            this.f8158c = pVar3;
            this.f8159d = pVar4;
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class c0 extends l0 implements j0 {
        c0() {
        }

        @Override // m2.g.j0
        public void i(n0 n0Var) {
        }

        @Override // m2.g.j0
        public List<n0> j() {
            return Collections.emptyList();
        }

        @Override // m2.g.l0
        String o() {
            return "solidColor";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class c1 extends n0 implements x0 {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f8160c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private b1 f8161d;

        c1(String str) {
            this.f8160c = str;
        }

        @Override // m2.g.x0
        public b1 h() {
            return this.f8161d;
        }

        @Override // m2.g.n0
        public String toString() {
            return getClass().getSimpleName() + " '" + this.f8160c + "'";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class d extends l {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        p f8162o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        p f8163p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        p f8164q;

        d() {
        }

        @Override // m2.g.l0
        String o() {
            return "circle";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class d0 extends l0 implements j0 {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        Float f8165h;

        d0() {
        }

        @Override // m2.g.j0
        public void i(n0 n0Var) {
        }

        @Override // m2.g.j0
        public List<n0> j() {
            return Collections.emptyList();
        }

        @Override // m2.g.l0
        String o() {
            return "stop";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    enum d1 {
        px,
        em,
        ex,
        in,
        cm,
        mm,
        pt,
        pc,
        percent
    }

    /* JADX INFO: compiled from: SVG.java */
    static class e extends m implements t {

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        Boolean f8167p;

        e() {
        }

        @Override // m2.g.m, m2.g.l0
        String o() {
            return "clipPath";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class e0 implements Cloneable {
        Boolean A;
        c B;
        String C;
        String D;
        String E;
        Boolean F;
        Boolean G;
        o0 H;
        Float I;
        String J;
        a K;
        String L;
        o0 M;
        Float N;
        o0 O;
        Float P;
        i Q;
        e R;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        long f8168e = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        o0 f8169g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        a f8170h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        Float f8171i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        o0 f8172j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        Float f8173k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        p f8174l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        c f8175m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        d f8176n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        Float f8177o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        p[] f8178p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        p f8179q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        Float f8180r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        f f8181s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        List<String> f8182t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        p f8183u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        Integer f8184v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        b f8185w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        EnumC0124g f8186x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        h f8187y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        f f8188z;

        /* JADX INFO: compiled from: SVG.java */
        public enum a {
            NonZero,
            EvenOdd
        }

        /* JADX INFO: compiled from: SVG.java */
        public enum b {
            Normal,
            Italic,
            Oblique
        }

        /* JADX INFO: compiled from: SVG.java */
        public enum c {
            Butt,
            Round,
            Square
        }

        /* JADX INFO: compiled from: SVG.java */
        public enum d {
            Miter,
            Round,
            Bevel
        }

        /* JADX INFO: compiled from: SVG.java */
        public enum e {
            auto,
            optimizeQuality,
            optimizeSpeed
        }

        /* JADX INFO: compiled from: SVG.java */
        public enum f {
            Start,
            Middle,
            End
        }

        /* JADX INFO: renamed from: m2.g$e0$g, reason: collision with other inner class name */
        /* JADX INFO: compiled from: SVG.java */
        public enum EnumC0124g {
            None,
            Underline,
            Overline,
            LineThrough,
            Blink
        }

        /* JADX INFO: compiled from: SVG.java */
        public enum h {
            LTR,
            RTL
        }

        /* JADX INFO: compiled from: SVG.java */
        public enum i {
            None,
            NonScalingStroke
        }

        e0() {
        }

        static e0 a() {
            e0 e0Var = new e0();
            e0Var.f8168e = -1L;
            f fVar = f.f8203g;
            e0Var.f8169g = fVar;
            a aVar = a.NonZero;
            e0Var.f8170h = aVar;
            Float fValueOf = Float.valueOf(1.0f);
            e0Var.f8171i = fValueOf;
            e0Var.f8172j = null;
            e0Var.f8173k = fValueOf;
            e0Var.f8174l = new p(1.0f);
            e0Var.f8175m = c.Butt;
            e0Var.f8176n = d.Miter;
            e0Var.f8177o = Float.valueOf(4.0f);
            e0Var.f8178p = null;
            e0Var.f8179q = new p(0.0f);
            e0Var.f8180r = fValueOf;
            e0Var.f8181s = fVar;
            e0Var.f8182t = null;
            e0Var.f8183u = new p(12.0f, d1.pt);
            e0Var.f8184v = 400;
            e0Var.f8185w = b.Normal;
            e0Var.f8186x = EnumC0124g.None;
            e0Var.f8187y = h.LTR;
            e0Var.f8188z = f.Start;
            Boolean bool = Boolean.TRUE;
            e0Var.A = bool;
            e0Var.B = null;
            e0Var.C = null;
            e0Var.D = null;
            e0Var.E = null;
            e0Var.F = bool;
            e0Var.G = bool;
            e0Var.H = fVar;
            e0Var.I = fValueOf;
            e0Var.J = null;
            e0Var.K = aVar;
            e0Var.L = null;
            e0Var.M = null;
            e0Var.N = fValueOf;
            e0Var.O = null;
            e0Var.P = fValueOf;
            e0Var.Q = i.None;
            e0Var.R = e.auto;
            return e0Var;
        }

        void b(boolean z6) {
            Boolean bool = Boolean.TRUE;
            this.F = bool;
            if (!z6) {
                bool = Boolean.FALSE;
            }
            this.A = bool;
            this.B = null;
            this.J = null;
            this.f8180r = Float.valueOf(1.0f);
            this.H = f.f8203g;
            this.I = Float.valueOf(1.0f);
            this.L = null;
            this.M = null;
            this.N = Float.valueOf(1.0f);
            this.O = null;
            this.P = Float.valueOf(1.0f);
            this.Q = i.None;
        }

        protected Object clone() {
            e0 e0Var = (e0) super.clone();
            p[] pVarArr = this.f8178p;
            if (pVarArr != null) {
                e0Var.f8178p = (p[]) pVarArr.clone();
            }
            return e0Var;
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class e1 extends m {

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        String f8198p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        p f8199q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        p f8200r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        p f8201s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        p f8202t;

        e1() {
        }

        @Override // m2.g.m, m2.g.l0
        String o() {
            return "use";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class f extends o0 {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        static final f f8203g = new f(-16777216);

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        static final f f8204h = new f(0);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f8205e;

        f(int i7) {
            this.f8205e = i7;
        }

        public String toString() {
            return String.format("#%08x", Integer.valueOf(this.f8205e));
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class f0 extends r0 {

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        p f8206q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        p f8207r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        p f8208s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        p f8209t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public String f8210u;

        f0() {
        }

        @Override // m2.g.l0
        String o() {
            return "svg";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class f1 extends r0 implements t {
        f1() {
        }

        @Override // m2.g.l0
        String o() {
            return "view";
        }
    }

    /* JADX INFO: renamed from: m2.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SVG.java */
    static class C0125g extends o0 {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static C0125g f8211e = new C0125g();

        private C0125g() {
        }

        static C0125g a() {
            return f8211e;
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    interface g0 {
        Set<String> a();

        void b(Set<String> set);

        String c();

        void d(Set<String> set);

        void e(Set<String> set);

        Set<String> f();

        void g(String str);

        void k(Set<String> set);

        Set<String> m();

        Set<String> n();
    }

    /* JADX INFO: compiled from: SVG.java */
    static class h extends m implements t {
        h() {
        }

        @Override // m2.g.m, m2.g.l0
        String o() {
            return "defs";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static abstract class h0 extends k0 implements j0, g0 {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        List<n0> f8212i = new ArrayList();

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        Set<String> f8213j = null;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        String f8214k = null;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        Set<String> f8215l = null;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        Set<String> f8216m = null;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        Set<String> f8217n = null;

        h0() {
        }

        @Override // m2.g.g0
        public Set<String> a() {
            return null;
        }

        @Override // m2.g.g0
        public void b(Set<String> set) {
            this.f8216m = set;
        }

        @Override // m2.g.g0
        public String c() {
            return this.f8214k;
        }

        @Override // m2.g.g0
        public void d(Set<String> set) {
            this.f8217n = set;
        }

        @Override // m2.g.g0
        public void e(Set<String> set) {
            this.f8215l = set;
        }

        @Override // m2.g.g0
        public Set<String> f() {
            return this.f8213j;
        }

        @Override // m2.g.g0
        public void g(String str) {
            this.f8214k = str;
        }

        @Override // m2.g.j0
        public void i(n0 n0Var) {
            this.f8212i.add(n0Var);
        }

        @Override // m2.g.j0
        public List<n0> j() {
            return this.f8212i;
        }

        @Override // m2.g.g0
        public void k(Set<String> set) {
            this.f8213j = set;
        }

        @Override // m2.g.g0
        public Set<String> m() {
            return this.f8216m;
        }

        @Override // m2.g.g0
        public Set<String> n() {
            return this.f8217n;
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class i extends l {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        p f8218o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        p f8219p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        p f8220q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        p f8221r;

        i() {
        }

        @Override // m2.g.l0
        String o() {
            return "ellipse";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static abstract class i0 extends k0 implements g0 {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        Set<String> f8222i = null;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        String f8223j = null;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        Set<String> f8224k = null;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        Set<String> f8225l = null;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        Set<String> f8226m = null;

        i0() {
        }

        @Override // m2.g.g0
        public Set<String> a() {
            return this.f8224k;
        }

        @Override // m2.g.g0
        public void b(Set<String> set) {
            this.f8225l = set;
        }

        @Override // m2.g.g0
        public String c() {
            return this.f8223j;
        }

        @Override // m2.g.g0
        public void d(Set<String> set) {
            this.f8226m = set;
        }

        @Override // m2.g.g0
        public void e(Set<String> set) {
            this.f8224k = set;
        }

        @Override // m2.g.g0
        public Set<String> f() {
            return this.f8222i;
        }

        @Override // m2.g.g0
        public void g(String str) {
            this.f8223j = str;
        }

        @Override // m2.g.g0
        public void k(Set<String> set) {
            this.f8222i = set;
        }

        @Override // m2.g.g0
        public Set<String> m() {
            return this.f8225l;
        }

        @Override // m2.g.g0
        public Set<String> n() {
            return this.f8226m;
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static abstract class j extends l0 implements j0 {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        List<n0> f8227h = new ArrayList();

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        Boolean f8228i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        Matrix f8229j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        k f8230k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        String f8231l;

        j() {
        }

        @Override // m2.g.j0
        public void i(n0 n0Var) throws m2.j {
            if (n0Var instanceof d0) {
                this.f8227h.add(n0Var);
                return;
            }
            throw new m2.j("Gradient elements cannot contain " + n0Var + " elements.");
        }

        @Override // m2.g.j0
        public List<n0> j() {
            return this.f8227h;
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    interface j0 {
        void i(n0 n0Var);

        List<n0> j();
    }

    /* JADX INFO: compiled from: SVG.java */
    enum k {
        pad,
        reflect,
        repeat
    }

    /* JADX INFO: compiled from: SVG.java */
    static abstract class k0 extends l0 {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        b f8233h = null;

        k0() {
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static abstract class l extends i0 implements n {

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        Matrix f8234n;

        l() {
        }

        @Override // m2.g.n
        public void l(Matrix matrix) {
            this.f8234n = matrix;
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static abstract class l0 extends n0 {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f8235c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Boolean f8236d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        e0 f8237e = null;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        e0 f8238f = null;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        List<String> f8239g = null;

        l0() {
        }

        abstract String o();
    }

    /* JADX INFO: compiled from: SVG.java */
    static class m extends h0 implements n {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        Matrix f8240o;

        m() {
        }

        @Override // m2.g.n
        public void l(Matrix matrix) {
            this.f8240o = matrix;
        }

        @Override // m2.g.l0
        String o() {
            return "group";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class m0 extends j {

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        p f8241m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        p f8242n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        p f8243o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        p f8244p;

        m0() {
        }

        @Override // m2.g.l0
        String o() {
            return "linearGradient";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    interface n {
        void l(Matrix matrix);
    }

    /* JADX INFO: compiled from: SVG.java */
    static class n0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        g f8245a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        j0 f8246b;

        n0() {
        }

        public String toString() {
            return getClass().getSimpleName();
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class o extends p0 implements n {

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        String f8247p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        p f8248q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        p f8249r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        p f8250s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        p f8251t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        Matrix f8252u;

        o() {
        }

        @Override // m2.g.n
        public void l(Matrix matrix) {
            this.f8252u = matrix;
        }

        @Override // m2.g.l0
        String o() {
            return "image";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static abstract class o0 implements Cloneable {
        o0() {
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static abstract class p0 extends h0 {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        m2.e f8255o = null;

        p0() {
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class q extends l {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        p f8256o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        p f8257p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        p f8258q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        p f8259r;

        q() {
        }

        @Override // m2.g.l0
        String o() {
            return "line";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class q0 extends j {

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        p f8260m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        p f8261n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        p f8262o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        p f8263p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        p f8264q;

        q0() {
        }

        @Override // m2.g.l0
        String o() {
            return "radialGradient";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class r extends r0 implements t {

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        boolean f8265q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        p f8266r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        p f8267s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        p f8268t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        p f8269u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        Float f8270v;

        r() {
        }

        @Override // m2.g.l0
        String o() {
            return "marker";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static abstract class r0 extends p0 {

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        b f8271p;

        r0() {
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class s extends h0 implements t {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        Boolean f8272o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        Boolean f8273p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        p f8274q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        p f8275r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        p f8276s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        p f8277t;

        s() {
        }

        @Override // m2.g.l0
        String o() {
            return "mask";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class s0 extends m {
        s0() {
        }

        @Override // m2.g.m, m2.g.l0
        String o() {
            return "switch";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    interface t {
    }

    /* JADX INFO: compiled from: SVG.java */
    static class t0 extends r0 implements t {
        t0() {
        }

        @Override // m2.g.l0
        String o() {
            return "symbol";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class u extends o0 {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        String f8278e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        o0 f8279g;

        u(String str, o0 o0Var) {
            this.f8278e = str;
            this.f8279g = o0Var;
        }

        public String toString() {
            return this.f8278e + " " + this.f8279g;
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class u0 extends y0 implements x0 {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        String f8280o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private b1 f8281p;

        u0() {
        }

        @Override // m2.g.x0
        public b1 h() {
            return this.f8281p;
        }

        @Override // m2.g.l0
        String o() {
            return "tref";
        }

        public void p(b1 b1Var) {
            this.f8281p = b1Var;
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class v extends l {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        w f8282o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        Float f8283p;

        v() {
        }

        @Override // m2.g.l0
        String o() {
            return "path";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class v0 extends a1 implements x0 {

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        private b1 f8284s;

        v0() {
        }

        @Override // m2.g.x0
        public b1 h() {
            return this.f8284s;
        }

        @Override // m2.g.l0
        String o() {
            return "tspan";
        }

        public void p(b1 b1Var) {
            this.f8284s = b1Var;
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class w implements x {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f8286b = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f8288d = 0;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private byte[] f8285a = new byte[8];

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float[] f8287c = new float[16];

        w() {
        }

        private void f(byte b7) {
            int i7 = this.f8286b;
            byte[] bArr = this.f8285a;
            if (i7 == bArr.length) {
                byte[] bArr2 = new byte[bArr.length * 2];
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                this.f8285a = bArr2;
            }
            byte[] bArr3 = this.f8285a;
            int i8 = this.f8286b;
            this.f8286b = i8 + 1;
            bArr3[i8] = b7;
        }

        private void g(int i7) {
            float[] fArr = this.f8287c;
            if (fArr.length < this.f8288d + i7) {
                float[] fArr2 = new float[fArr.length * 2];
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                this.f8287c = fArr2;
            }
        }

        @Override // m2.g.x
        public void a(float f7, float f8) {
            f((byte) 0);
            g(2);
            float[] fArr = this.f8287c;
            int i7 = this.f8288d;
            int i8 = i7 + 1;
            this.f8288d = i8;
            fArr[i7] = f7;
            this.f8288d = i8 + 1;
            fArr[i8] = f8;
        }

        @Override // m2.g.x
        public void b(float f7, float f8, float f9, float f10) {
            f((byte) 3);
            g(4);
            float[] fArr = this.f8287c;
            int i7 = this.f8288d;
            int i8 = i7 + 1;
            this.f8288d = i8;
            fArr[i7] = f7;
            int i9 = i8 + 1;
            this.f8288d = i9;
            fArr[i8] = f8;
            int i10 = i9 + 1;
            this.f8288d = i10;
            fArr[i9] = f9;
            this.f8288d = i10 + 1;
            fArr[i10] = f10;
        }

        @Override // m2.g.x
        public void c(float f7, float f8, float f9, float f10, float f11, float f12) {
            f((byte) 2);
            g(6);
            float[] fArr = this.f8287c;
            int i7 = this.f8288d;
            int i8 = i7 + 1;
            this.f8288d = i8;
            fArr[i7] = f7;
            int i9 = i8 + 1;
            this.f8288d = i9;
            fArr[i8] = f8;
            int i10 = i9 + 1;
            this.f8288d = i10;
            fArr[i9] = f9;
            int i11 = i10 + 1;
            this.f8288d = i11;
            fArr[i10] = f10;
            int i12 = i11 + 1;
            this.f8288d = i12;
            fArr[i11] = f11;
            this.f8288d = i12 + 1;
            fArr[i12] = f12;
        }

        @Override // m2.g.x
        public void close() {
            f((byte) 8);
        }

        @Override // m2.g.x
        public void d(float f7, float f8) {
            f((byte) 1);
            g(2);
            float[] fArr = this.f8287c;
            int i7 = this.f8288d;
            int i8 = i7 + 1;
            this.f8288d = i8;
            fArr[i7] = f7;
            this.f8288d = i8 + 1;
            fArr[i8] = f8;
        }

        @Override // m2.g.x
        public void e(float f7, float f8, float f9, boolean z6, boolean z7, float f10, float f11) {
            f((byte) ((z6 ? 2 : 0) | 4 | (z7 ? 1 : 0)));
            g(5);
            float[] fArr = this.f8287c;
            int i7 = this.f8288d;
            int i8 = i7 + 1;
            this.f8288d = i8;
            fArr[i7] = f7;
            int i9 = i8 + 1;
            this.f8288d = i9;
            fArr[i8] = f8;
            int i10 = i9 + 1;
            this.f8288d = i10;
            fArr[i9] = f9;
            int i11 = i10 + 1;
            this.f8288d = i11;
            fArr[i10] = f10;
            this.f8288d = i11 + 1;
            fArr[i11] = f11;
        }

        void h(x xVar) {
            int i7;
            int i8 = 0;
            for (int i9 = 0; i9 < this.f8286b; i9++) {
                byte b7 = this.f8285a[i9];
                if (b7 == 0) {
                    float[] fArr = this.f8287c;
                    int i10 = i8 + 1;
                    i7 = i10 + 1;
                    xVar.a(fArr[i8], fArr[i10]);
                } else if (b7 != 1) {
                    if (b7 == 2) {
                        float[] fArr2 = this.f8287c;
                        int i11 = i8 + 1;
                        float f7 = fArr2[i8];
                        int i12 = i11 + 1;
                        float f8 = fArr2[i11];
                        int i13 = i12 + 1;
                        float f9 = fArr2[i12];
                        int i14 = i13 + 1;
                        float f10 = fArr2[i13];
                        int i15 = i14 + 1;
                        float f11 = fArr2[i14];
                        i8 = i15 + 1;
                        xVar.c(f7, f8, f9, f10, f11, fArr2[i15]);
                    } else if (b7 == 3) {
                        float[] fArr3 = this.f8287c;
                        int i16 = i8 + 1;
                        int i17 = i16 + 1;
                        int i18 = i17 + 1;
                        xVar.b(fArr3[i8], fArr3[i16], fArr3[i17], fArr3[i18]);
                        i8 = i18 + 1;
                    } else if (b7 != 8) {
                        boolean z6 = (b7 & 2) != 0;
                        boolean z7 = (b7 & 1) != 0;
                        float[] fArr4 = this.f8287c;
                        int i19 = i8 + 1;
                        float f12 = fArr4[i8];
                        int i20 = i19 + 1;
                        float f13 = fArr4[i19];
                        int i21 = i20 + 1;
                        float f14 = fArr4[i20];
                        int i22 = i21 + 1;
                        xVar.e(f12, f13, f14, z6, z7, fArr4[i21], fArr4[i22]);
                        i8 = i22 + 1;
                    } else {
                        xVar.close();
                    }
                } else {
                    float[] fArr5 = this.f8287c;
                    int i23 = i8 + 1;
                    i7 = i23 + 1;
                    xVar.d(fArr5[i8], fArr5[i23]);
                }
                i8 = i7;
            }
        }

        boolean i() {
            return this.f8286b == 0;
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class w0 extends a1 implements b1, n {

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        Matrix f8289s;

        w0() {
        }

        @Override // m2.g.n
        public void l(Matrix matrix) {
            this.f8289s = matrix;
        }

        @Override // m2.g.l0
        String o() {
            return "text";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    interface x {
        void a(float f7, float f8);

        void b(float f7, float f8, float f9, float f10);

        void c(float f7, float f8, float f9, float f10, float f11, float f12);

        void close();

        void d(float f7, float f8);

        void e(float f7, float f8, float f9, boolean z6, boolean z7, float f10, float f11);
    }

    /* JADX INFO: compiled from: SVG.java */
    interface x0 {
        b1 h();
    }

    /* JADX INFO: compiled from: SVG.java */
    static class y extends r0 implements t {

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        Boolean f8290q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        Boolean f8291r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        Matrix f8292s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        p f8293t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        p f8294u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        p f8295v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        p f8296w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        String f8297x;

        y() {
        }

        @Override // m2.g.l0
        String o() {
            return BYDAutoMultimediaDevice.PARAM_PATTERN;
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static abstract class y0 extends h0 {
        y0() {
        }

        @Override // m2.g.h0, m2.g.j0
        public void i(n0 n0Var) throws m2.j {
            if (n0Var instanceof x0) {
                this.f8212i.add(n0Var);
                return;
            }
            throw new m2.j("Text content elements cannot contain " + n0Var + " elements.");
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class z extends l {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        float[] f8298o;

        z() {
        }

        @Override // m2.g.l0
        String o() {
            return "polyline";
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class z0 extends y0 implements x0 {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        String f8299o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        p f8300p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        private b1 f8301q;

        z0() {
        }

        @Override // m2.g.x0
        public b1 h() {
            return this.f8301q;
        }

        @Override // m2.g.l0
        String o() {
            return "textPath";
        }

        public void p(b1 b1Var) {
            this.f8301q = b1Var;
        }
    }

    g() {
    }

    private String c(String str) {
        if (str.startsWith("\"") && str.endsWith("\"")) {
            str = str.substring(1, str.length() - 1).replace("\\\"", "\"");
        } else if (str.startsWith("'") && str.endsWith("'")) {
            str = str.substring(1, str.length() - 1).replace("\\'", "'");
        }
        return str.replace("\\\n", "").replace("\\A", "\n");
    }

    private b e(float f7) {
        d1 d1Var;
        d1 d1Var2;
        d1 d1Var3;
        d1 d1Var4;
        float fB;
        d1 d1Var5;
        f0 f0Var = this.f8135a;
        p pVar = f0Var.f8208s;
        p pVar2 = f0Var.f8209t;
        if (pVar == null || pVar.h() || (d1Var = pVar.f8254g) == (d1Var2 = d1.percent) || d1Var == (d1Var3 = d1.em) || d1Var == (d1Var4 = d1.ex)) {
            return new b(-1.0f, -1.0f, -1.0f, -1.0f);
        }
        float fB2 = pVar.b(f7);
        if (pVar2 == null) {
            b bVar = this.f8135a.f8271p;
            fB = bVar != null ? (bVar.f8149d * fB2) / bVar.f8148c : fB2;
        } else {
            if (pVar2.h() || (d1Var5 = pVar2.f8254g) == d1Var2 || d1Var5 == d1Var3 || d1Var5 == d1Var4) {
                return new b(-1.0f, -1.0f, -1.0f, -1.0f);
            }
            fB = pVar2.b(f7);
        }
        return new b(0.0f, 0.0f, fB2, fB);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private l0 j(j0 j0Var, String str) {
        l0 l0VarJ;
        l0 l0Var = (l0) j0Var;
        if (str.equals(l0Var.f8235c)) {
            return l0Var;
        }
        for (Object obj : j0Var.j()) {
            if (obj instanceof l0) {
                l0 l0Var2 = (l0) obj;
                if (str.equals(l0Var2.f8235c)) {
                    return l0Var2;
                }
                if ((obj instanceof j0) && (l0VarJ = j((j0) obj, str)) != null) {
                    return l0VarJ;
                }
            }
        }
        return null;
    }

    static m2.i k() {
        return null;
    }

    public static g l(InputStream inputStream) {
        return new m2.k().z(inputStream, f8134g);
    }

    void a(b.r rVar) {
        this.f8139e.b(rVar);
    }

    void b() {
        this.f8139e.e(b.u.RenderOptions);
    }

    List<b.p> d() {
        return this.f8139e.c();
    }

    public float f() {
        if (this.f8135a != null) {
            return e(this.f8138d).f8149d;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public RectF g() {
        f0 f0Var = this.f8135a;
        if (f0Var == null) {
            throw new IllegalArgumentException("SVG document is empty");
        }
        b bVar = f0Var.f8271p;
        if (bVar == null) {
            return null;
        }
        return bVar.d();
    }

    public float h() {
        if (this.f8135a != null) {
            return e(this.f8138d).f8148c;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    l0 i(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (str.equals(this.f8135a.f8235c)) {
            return this.f8135a;
        }
        if (this.f8140f.containsKey(str)) {
            return this.f8140f.get(str);
        }
        l0 l0VarJ = j(this.f8135a, str);
        this.f8140f.put(str, l0VarJ);
        return l0VarJ;
    }

    f0 m() {
        return this.f8135a;
    }

    boolean n() {
        return !this.f8139e.d();
    }

    public Picture o() {
        return q(null);
    }

    public Picture p(int i7, int i8, m2.f fVar) {
        Picture picture = new Picture();
        Canvas canvasBeginRecording = picture.beginRecording(i7, i8);
        if (fVar == null || fVar.f8133f == null) {
            fVar = fVar == null ? new m2.f() : new m2.f(fVar);
            fVar.g(0.0f, 0.0f, i7, i8);
        }
        new m2.h(canvasBeginRecording, this.f8138d).E0(this, fVar);
        picture.endRecording();
        return picture;
    }

    public Picture q(m2.f fVar) {
        p pVar;
        b bVar = (fVar == null || !fVar.e()) ? this.f8135a.f8271p : fVar.f8131d;
        if (fVar != null && fVar.f()) {
            return p((int) Math.ceil(fVar.f8133f.b()), (int) Math.ceil(fVar.f8133f.c()), fVar);
        }
        f0 f0Var = this.f8135a;
        p pVar2 = f0Var.f8208s;
        if (pVar2 != null) {
            d1 d1Var = pVar2.f8254g;
            d1 d1Var2 = d1.percent;
            if (d1Var != d1Var2 && (pVar = f0Var.f8209t) != null && pVar.f8254g != d1Var2) {
                return p((int) Math.ceil(pVar2.b(this.f8138d)), (int) Math.ceil(this.f8135a.f8209t.b(this.f8138d)), fVar);
            }
        }
        if (pVar2 != null && bVar != null) {
            return p((int) Math.ceil(pVar2.b(this.f8138d)), (int) Math.ceil((bVar.f8149d * r1) / bVar.f8148c), fVar);
        }
        p pVar3 = f0Var.f8209t;
        if (pVar3 == null || bVar == null) {
            return p(512, 512, fVar);
        }
        return p((int) Math.ceil((bVar.f8148c * r1) / bVar.f8149d), (int) Math.ceil(pVar3.b(this.f8138d)), fVar);
    }

    n0 r(String str) {
        if (str == null) {
            return null;
        }
        String strC = c(str);
        if (strC.length() <= 1 || !strC.startsWith("#")) {
            return null;
        }
        return i(strC.substring(1));
    }

    void s(String str) {
        this.f8137c = str;
    }

    public void t(float f7) {
        f0 f0Var = this.f8135a;
        if (f0Var == null) {
            throw new IllegalArgumentException("SVG document is empty");
        }
        f0Var.f8209t = new p(f7);
    }

    public void u(m2.e eVar) {
        f0 f0Var = this.f8135a;
        if (f0Var == null) {
            throw new IllegalArgumentException("SVG document is empty");
        }
        f0Var.f8255o = eVar;
    }

    public void v(float f7) {
        f0 f0Var = this.f8135a;
        if (f0Var == null) {
            throw new IllegalArgumentException("SVG document is empty");
        }
        f0Var.f8208s = new p(f7);
    }

    void w(f0 f0Var) {
        this.f8135a = f0Var;
    }

    void x(String str) {
        this.f8136b = str;
    }

    /* JADX INFO: compiled from: SVG.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f8146a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f8147b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f8148c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f8149d;

        b(float f7, float f8, float f9, float f10) {
            this.f8146a = f7;
            this.f8147b = f8;
            this.f8148c = f9;
            this.f8149d = f10;
        }

        static b a(float f7, float f8, float f9, float f10) {
            return new b(f7, f8, f9 - f7, f10 - f8);
        }

        float b() {
            return this.f8146a + this.f8148c;
        }

        float c() {
            return this.f8147b + this.f8149d;
        }

        RectF d() {
            return new RectF(this.f8146a, this.f8147b, b(), c());
        }

        void e(b bVar) {
            float f7 = bVar.f8146a;
            if (f7 < this.f8146a) {
                this.f8146a = f7;
            }
            float f8 = bVar.f8147b;
            if (f8 < this.f8147b) {
                this.f8147b = f8;
            }
            if (bVar.b() > b()) {
                this.f8148c = bVar.b() - this.f8146a;
            }
            if (bVar.c() > c()) {
                this.f8149d = bVar.c() - this.f8147b;
            }
        }

        public String toString() {
            return "[" + this.f8146a + " " + this.f8147b + " " + this.f8148c + " " + this.f8149d + "]";
        }

        b(b bVar) {
            this.f8146a = bVar.f8146a;
            this.f8147b = bVar.f8147b;
            this.f8148c = bVar.f8148c;
            this.f8149d = bVar.f8149d;
        }
    }

    /* JADX INFO: compiled from: SVG.java */
    static class p implements Cloneable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        float f8253e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        d1 f8254g;

        p(float f7, d1 d1Var) {
            this.f8253e = 0.0f;
            d1 d1Var2 = d1.px;
            this.f8253e = f7;
            this.f8254g = d1Var;
        }

        float a() {
            return this.f8253e;
        }

        float b(float f7) {
            int i7 = a.f8141a[this.f8254g.ordinal()];
            if (i7 == 1) {
                return this.f8253e;
            }
            switch (i7) {
                case 4:
                    return this.f8253e * f7;
                case 5:
                    return (this.f8253e * f7) / 2.54f;
                case 6:
                    return (this.f8253e * f7) / 25.4f;
                case 7:
                    return (this.f8253e * f7) / 72.0f;
                case 8:
                    return (this.f8253e * f7) / 6.0f;
                default:
                    return this.f8253e;
            }
        }

        float c(m2.h hVar) {
            if (this.f8254g != d1.percent) {
                return e(hVar);
            }
            b bVarR = hVar.R();
            if (bVarR == null) {
                return this.f8253e;
            }
            float f7 = bVarR.f8148c;
            if (f7 == bVarR.f8149d) {
                return (this.f8253e * f7) / 100.0f;
            }
            return (this.f8253e * ((float) (Math.sqrt((f7 * f7) + (r6 * r6)) / 1.414213562373095d))) / 100.0f;
        }

        float d(m2.h hVar, float f7) {
            return this.f8254g == d1.percent ? (this.f8253e * f7) / 100.0f : e(hVar);
        }

        float e(m2.h hVar) {
            switch (a.f8141a[this.f8254g.ordinal()]) {
                case 9:
                    b bVarR = hVar.R();
                    if (bVarR != null) {
                    }
                    break;
            }
            return this.f8253e;
        }

        float f(m2.h hVar) {
            if (this.f8254g != d1.percent) {
                return e(hVar);
            }
            b bVarR = hVar.R();
            return bVarR == null ? this.f8253e : (this.f8253e * bVarR.f8149d) / 100.0f;
        }

        boolean g() {
            return this.f8253e < 0.0f;
        }

        boolean h() {
            return this.f8253e == 0.0f;
        }

        public String toString() {
            return String.valueOf(this.f8253e) + this.f8254g;
        }

        p(float f7) {
            this.f8253e = 0.0f;
            d1 d1Var = d1.px;
            this.f8253e = f7;
            this.f8254g = d1Var;
        }
    }
}
