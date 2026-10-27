package o1;

import android.content.Context;
import android.graphics.Rect;
import com.airbnb.lottie.model.layer.Layer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: LottieComposition.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Map<String, List<Layer>> f6741c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Map<String, h> f6742d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Map<String, t1.b> f6743e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List<t1.g> f6744f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public v.h<t1.c> f6745g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public v.d<Layer> f6746h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public List<Layer> f6747i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Rect f6748j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f6749k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f6750l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f6751m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f6752n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f6739a = new p();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashSet<String> f6740b = new HashSet<>();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f6753o = 0;

    /* JADX INFO: compiled from: LottieComposition.java */
    @Deprecated
    public static class b {

        /* JADX INFO: compiled from: LottieComposition.java */
        public static final class a implements i<e>, o1.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final o f6754a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public boolean f6755b;

            @Override // o1.i
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(e eVar) {
                if (this.f6755b) {
                    return;
                }
                this.f6754a.onCompositionLoaded(eVar);
            }

            public a(o oVar) {
                this.f6755b = false;
                this.f6754a = oVar;
            }
        }

        @Deprecated
        public static o1.a a(Context context, String str, o oVar) {
            a aVar = new a(oVar);
            f.d(context, str).f(aVar);
            return aVar;
        }
    }

    public void a(String str) {
        a2.d.c(str);
        this.f6740b.add(str);
    }

    public Rect b() {
        return this.f6748j;
    }

    public v.h<t1.c> c() {
        return this.f6745g;
    }

    public float d() {
        return (long) ((e() / this.f6751m) * 1000.0f);
    }

    public float e() {
        return this.f6750l - this.f6749k;
    }

    public float f() {
        return this.f6750l;
    }

    public Map<String, t1.b> g() {
        return this.f6743e;
    }

    public float h() {
        return this.f6751m;
    }

    public Map<String, h> i() {
        return this.f6742d;
    }

    public List<Layer> j() {
        return this.f6747i;
    }

    public t1.g k(String str) {
        this.f6744f.size();
        for (int i8 = 0; i8 < this.f6744f.size(); i8++) {
            t1.g gVar = this.f6744f.get(i8);
            if (gVar.a(str)) {
                return gVar;
            }
        }
        return null;
    }

    public int l() {
        return this.f6753o;
    }

    public p m() {
        return this.f6739a;
    }

    public List<Layer> n(String str) {
        return this.f6741c.get(str);
    }

    public float o() {
        return this.f6749k;
    }

    public boolean p() {
        return this.f6752n;
    }

    public void q(int i8) {
        this.f6753o += i8;
    }

    public void r(Rect rect, float f8, float f9, float f10, List<Layer> list, v.d<Layer> dVar, Map<String, List<Layer>> map, Map<String, h> map2, v.h<t1.c> hVar, Map<String, t1.b> map3, List<t1.g> list2) {
        this.f6748j = rect;
        this.f6749k = f8;
        this.f6750l = f9;
        this.f6751m = f10;
        this.f6747i = list;
        this.f6746h = dVar;
        this.f6741c = map;
        this.f6742d = map2;
        this.f6745g = hVar;
        this.f6743e = map3;
        this.f6744f = list2;
    }

    public Layer s(long j8) {
        return this.f6746h.f(j8);
    }

    public void t(boolean z7) {
        this.f6752n = z7;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LottieComposition:\n");
        Iterator<Layer> it = this.f6747i.iterator();
        while (it.hasNext()) {
            sb.append(it.next().w("\t"));
        }
        return sb.toString();
    }

    public void u(boolean z7) {
        this.f6739a.b(z7);
    }
}
