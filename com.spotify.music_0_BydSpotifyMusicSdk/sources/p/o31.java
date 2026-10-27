package p;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.widget.ImageView;
import coil.request.CachePolicy;
import coil.size.Precision;
import coil.size.Scale;
import coil.target.GenericViewTarget;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.EmptyList;
import okhttp3.Headers;

/* JADX INFO: loaded from: classes.dex */
public final class o31 {
    public final kotlinx.coroutines.b A;
    public final v50 B;
    public final mi1 C;
    public final Integer D;
    public final Drawable E;
    public final Integer F;
    public final Drawable G;
    public final Integer H;
    public final Drawable I;
    public final l91 J;
    public rd2 K;
    public Scale L;
    public l91 M;
    public rd2 N;
    public Scale O;
    public final Context a;
    public p70 b;
    public Object c;
    public yi2 d;
    public p31 e;
    public final mi1 f;
    public final String g;
    public final Bitmap.Config h;
    public final ColorSpace i;
    public Precision j;
    public final Pair k;
    public final am l;
    public List m;
    public final un2 n;
    public final Headers.Builder o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final LinkedHashMap f162p;
    public final boolean q;
    public Boolean r;
    public final Boolean s;
    public final boolean t;
    public final CachePolicy u;
    public final CachePolicy v;
    public final CachePolicy w;
    public final kotlinx.coroutines.b x;
    public final kotlinx.coroutines.b y;
    public final kotlinx.coroutines.b z;

    public o31(Context context) {
        this.a = context;
        this.b = g.a;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = EmptyList.c;
        this.n = null;
        this.o = null;
        this.f162p = null;
        this.q = true;
        this.r = null;
        this.s = null;
        this.t = true;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
    }

    public final q31 a() {
        un2 un2Var;
        rd2 rd2Var;
        KeyEvent.Callback callbackI;
        rd2 la0Var;
        ImageView.ScaleType scaleType;
        Context context = this.a;
        Object obj = this.c;
        if (obj == null) {
            obj = oo1.a;
        }
        Object obj2 = obj;
        yi2 yi2Var = this.d;
        p31 p31Var = this.e;
        mi1 mi1Var = this.f;
        String str = this.g;
        Bitmap.Config config = this.h;
        if (config == null) {
            config = this.b.g;
        }
        Bitmap.Config config2 = config;
        ColorSpace colorSpace = this.i;
        Precision precision = this.j;
        if (precision == null) {
            precision = this.b.f;
        }
        Precision precision2 = precision;
        Pair pair = this.k;
        am amVar = this.l;
        List list = this.m;
        un2 un2Var2 = this.n;
        if (un2Var2 == null) {
            un2Var2 = this.b.e;
        }
        un2 un2Var3 = un2Var2;
        Headers.Builder builder = this.o;
        Headers headersE = builder != null ? builder.e() : null;
        if (headersE == null) {
            headersE = i.c;
        } else {
            Bitmap.Config[] configArr = i.a;
        }
        Headers headers = headersE;
        LinkedHashMap linkedHashMap = this.f162p;
        xi2 xi2Var = linkedHashMap != null ? new xi2(l13.r(linkedHashMap)) : null;
        xi2 xi2Var2 = xi2Var == null ? xi2.b : xi2Var;
        boolean z = this.q;
        Boolean bool = this.r;
        boolean zBooleanValue = bool != null ? bool.booleanValue() : this.b.h;
        Boolean bool2 = this.s;
        boolean zBooleanValue2 = bool2 != null ? bool2.booleanValue() : this.b.i;
        boolean z2 = this.t;
        CachePolicy cachePolicy = this.u;
        if (cachePolicy == null) {
            cachePolicy = this.b.m;
        }
        CachePolicy cachePolicy2 = cachePolicy;
        CachePolicy cachePolicy3 = this.v;
        if (cachePolicy3 == null) {
            cachePolicy3 = this.b.n;
        }
        CachePolicy cachePolicy4 = cachePolicy3;
        CachePolicy cachePolicy5 = this.w;
        if (cachePolicy5 == null) {
            cachePolicy5 = this.b.o;
        }
        CachePolicy cachePolicy6 = cachePolicy5;
        kotlinx.coroutines.b bVar = this.x;
        if (bVar == null) {
            bVar = this.b.a;
        }
        kotlinx.coroutines.b bVar2 = bVar;
        kotlinx.coroutines.b bVar3 = this.y;
        if (bVar3 == null) {
            bVar3 = this.b.b;
        }
        kotlinx.coroutines.b bVar4 = bVar3;
        kotlinx.coroutines.b bVar5 = this.z;
        if (bVar5 == null) {
            bVar5 = this.b.c;
        }
        kotlinx.coroutines.b bVar6 = bVar5;
        kotlinx.coroutines.b bVar7 = this.A;
        if (bVar7 == null) {
            bVar7 = this.b.d;
        }
        kotlinx.coroutines.b bVar8 = bVar7;
        l91 l91VarL = this.J;
        Context context2 = this.a;
        if (l91VarL == null && (l91VarL = this.M) == null) {
            yi2 yi2Var2 = this.d;
            un2Var = un2Var3;
            Object context3 = yi2Var2 instanceof GenericViewTarget ? ((GenericViewTarget) yi2Var2).i().getContext() : context2;
            while (true) {
                if (context3 instanceof s91) {
                    l91VarL = ((s91) context3).l();
                    break;
                }
                if (!(context3 instanceof ContextWrapper)) {
                    l91VarL = null;
                    break;
                }
                context3 = ((ContextWrapper) context3).getBaseContext();
            }
            if (l91VarL == null) {
                l91VarL = r01.a;
            }
        } else {
            un2Var = un2Var3;
        }
        l91 l91Var = l91VarL;
        rd2 rd2Var2 = this.K;
        if (rd2Var2 == null && (rd2Var2 = this.N) == null) {
            yi2 yi2Var3 = this.d;
            if (yi2Var3 instanceof GenericViewTarget) {
                ImageView imageViewI = ((GenericViewTarget) yi2Var3).i();
                la0Var = ((imageViewI instanceof ImageView) && ((scaleType = imageViewI.getScaleType()) == ImageView.ScaleType.CENTER || scaleType == ImageView.ScaleType.MATRIX)) ? new d12(qd2.c) : new h12(imageViewI, true);
            } else {
                la0Var = new la0(context2);
            }
            rd2Var = la0Var;
        } else {
            rd2Var = rd2Var2;
        }
        Scale scale = this.L;
        if (scale == null && (scale = this.O) == null) {
            rd2 rd2Var3 = this.K;
            coil.size.a aVar = rd2Var3 instanceof coil.size.a ? (coil.size.a) rd2Var3 : null;
            if (aVar == null || (callbackI = ((h12) aVar).a) == null) {
                yi2 yi2Var4 = this.d;
                GenericViewTarget genericViewTarget = yi2Var4 instanceof GenericViewTarget ? (GenericViewTarget) yi2Var4 : null;
                callbackI = genericViewTarget != null ? genericViewTarget.i() : null;
            }
            if (callbackI instanceof ImageView) {
                Bitmap.Config[] configArr2 = i.a;
                ImageView.ScaleType scaleType2 = ((ImageView) callbackI).getScaleType();
                int i = scaleType2 == null ? -1 : h.a[scaleType2.ordinal()];
                scale = (i == 1 || i == 2 || i == 3 || i == 4) ? Scale.e : Scale.c;
            } else {
                scale = Scale.e;
            }
        }
        Scale scale2 = scale;
        v50 v50Var = this.B;
        bs1 bs1Var = v50Var != null ? new bs1(l13.r(v50Var.a)) : null;
        return new q31(context, obj2, yi2Var, p31Var, mi1Var, str, config2, colorSpace, precision2, pair, amVar, list, un2Var, headers, xi2Var2, z, zBooleanValue, zBooleanValue2, z2, cachePolicy2, cachePolicy4, cachePolicy6, bVar2, bVar4, bVar6, bVar8, l91Var, rd2Var, scale2, bs1Var == null ? bs1.e : bs1Var, this.C, this.D, this.E, this.F, this.G, this.H, this.I, new m80(this.J, this.K, this.L, this.x, this.y, this.z, this.A, this.n, this.j, this.h, this.r, this.s, this.u, this.v, this.w), this.b);
    }

    public o31(q31 q31Var, Context context) {
        this.a = context;
        this.b = q31Var.M;
        this.c = q31Var.b;
        this.d = q31Var.c;
        this.e = q31Var.d;
        this.f = q31Var.e;
        this.g = q31Var.f;
        m80 m80Var = q31Var.L;
        this.h = m80Var.j;
        this.i = q31Var.h;
        this.j = m80Var.i;
        this.k = q31Var.j;
        this.l = q31Var.k;
        this.m = q31Var.l;
        this.n = m80Var.h;
        this.o = q31Var.n.c();
        this.f162p = kotlin.collections.c.n0(q31Var.o.a);
        this.q = q31Var.f170p;
        this.r = m80Var.k;
        this.s = m80Var.l;
        this.t = q31Var.s;
        this.u = m80Var.m;
        this.v = m80Var.n;
        this.w = m80Var.o;
        this.x = m80Var.d;
        this.y = m80Var.e;
        this.z = m80Var.f;
        this.A = m80Var.g;
        v50 v50Var = new v50();
        v50Var.a = kotlin.collections.c.n0(q31Var.D.c);
        this.B = v50Var;
        this.C = q31Var.E;
        this.D = q31Var.F;
        this.E = q31Var.G;
        this.F = q31Var.H;
        this.G = q31Var.I;
        this.H = q31Var.J;
        this.I = q31Var.K;
        this.J = m80Var.a;
        this.K = m80Var.b;
        this.L = m80Var.c;
        if (q31Var.a == context) {
            this.M = q31Var.A;
            this.N = q31Var.B;
            this.O = q31Var.C;
        } else {
            this.M = null;
            this.N = null;
            this.O = null;
        }
    }
}
