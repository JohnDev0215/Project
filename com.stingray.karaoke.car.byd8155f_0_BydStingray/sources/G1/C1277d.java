package g1;

import A7.A;
import A7.B;
import H7.w;
import M.t;

/* JADX INFO: renamed from: g1.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1277d {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ w[] f14708j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f14709a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k1.g f14710b;
    public final t d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f14711e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final t f14712f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f14713g;
    public final C1278e c = new C1278e("parent");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C1276c f14714h = new C1276c(this, new C1286m("wrap"));

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C1276c f14715i = new C1276c(this, new C1286m("wrap"));

    static {
        A7.p pVar = new A7.p(C1277d.class, "width", "getWidth()Landroidx/constraintlayout/compose/Dimension;", 0);
        B b10 = A.f443a;
        f14708j = new w[]{b10.f(pVar), A7.k.a(C1277d.class, "height", "getHeight()Landroidx/constraintlayout/compose/Dimension;", 0, b10), A7.k.a(C1277d.class, "visibility", "getVisibility()Landroidx/constraintlayout/compose/Visibility;", 0, b10), A7.k.a(C1277d.class, "scaleX", "getScaleX()F", 0, b10), A7.k.a(C1277d.class, "scaleY", "getScaleY()F", 0, b10), A7.k.a(C1277d.class, "rotationX", "getRotationX()F", 0, b10), A7.k.a(C1277d.class, "rotationY", "getRotationY()F", 0, b10), A7.k.a(C1277d.class, "rotationZ", "getRotationZ()F", 0, b10), A7.k.a(C1277d.class, "translationX", "getTranslationX-D9Ej5fM()F", 0, b10), A7.k.a(C1277d.class, "translationY", "getTranslationY-D9Ej5fM()F", 0, b10), A7.k.a(C1277d.class, "translationZ", "getTranslationZ-D9Ej5fM()F", 0, b10), A7.k.a(C1277d.class, "pivotX", "getPivotX()F", 0, b10), A7.k.a(C1277d.class, "pivotY", "getPivotY()F", 0, b10), A7.k.a(C1277d.class, "horizontalChainWeight", "getHorizontalChainWeight()F", 0, b10), A7.k.a(C1277d.class, "verticalChainWeight", "getVerticalChainWeight()F", 0, b10)};
    }

    public C1277d(Object obj, k1.g gVar) {
        this.f14709a = obj;
        this.f14710b = gVar;
        this.d = new t(-2, gVar);
        this.f14711e = new io.sentry.internal.debugmeta.c(0, gVar);
        this.f14712f = new t(-1, gVar);
        this.f14713g = new io.sentry.internal.debugmeta.c(1, gVar);
    }

    public static void a(C1277d c1277d, C1278e c1278e) {
        c1277d.getClass();
        float f10 = 0;
        c1277d.d.I(c1278e.d, f10, f10);
        c1277d.f14712f.I(c1278e.f14717f, f10, f10);
        k1.g gVar = c1277d.f14710b;
        gVar.getClass();
        gVar.G("hRtlBias", new k1.e(0.5f));
    }

    public static void b(C1277d c1277d, C1279f c1279f, C1279f c1279f2, float f10, int i7) {
        float f11 = 0;
        float f12 = 0;
        float f13 = 0;
        float f14 = 0;
        if ((i7 & 64) != 0) {
            f10 = 0.5f;
        }
        c1277d.f14711e.N(c1279f, f11, f13);
        c1277d.f14713g.N(c1279f2, f12, f14);
        k1.g gVar = c1277d.f14710b;
        gVar.getClass();
        gVar.G("vBias", new k1.e(f10));
    }
}
