package com.stingray.kca.model;

import A7.m;
import E5.H;
import E5.M;
import E5.q;
import E5.u;
import E5.x;
import G5.f;
import h2.AbstractC1347d;
import io.sentry.C1489n1;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import kotlin.Metadata;
import n7.w;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/KaraokeSongResponseV2JsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/KaraokeSongResponseV2;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class KaraokeSongResponseV2JsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13148a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13149b;
    public final q c;
    public final q d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q f13150e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final q f13151f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final q f13152g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final q f13153h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile Constructor f13154i;

    public KaraokeSongResponseV2JsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13148a = C1489n1.z("id", "title", "artistName", "artistId", "genre", "era", "singerCount", "durationSec", "releaseYear", "explicit", "languages", "cover", "available", "hasPitchInfo", "musicalKey", "tempo");
        w wVar = w.l;
        this.f13149b = h10.c(String.class, wVar, "id");
        this.c = h10.c(Filter.class, wVar, "genre");
        this.d = h10.c(Integer.class, wVar, "singerCount");
        this.f13150e = h10.c(Boolean.class, wVar, "explicit");
        this.f13151f = h10.c(M.g(List.class, Filter.class), wVar, "languages");
        this.f13152g = h10.c(MusicalKey.class, wVar, "musicalKey");
        this.f13153h = h10.c(Float.class, wVar, "tempo");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        int i7 = -1;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Filter filter = null;
        Filter filter2 = null;
        Integer num = null;
        Integer num2 = null;
        Integer num3 = null;
        Boolean bool = null;
        List list = null;
        String str5 = null;
        Boolean bool2 = null;
        Boolean bool3 = null;
        MusicalKey musicalKey = null;
        Float f10 = null;
        while (uVar.m()) {
            switch (uVar.F(this.f13148a)) {
                case -1:
                    uVar.I();
                    uVar.K();
                    break;
                case 0:
                    str = (String) this.f13149b.a(uVar);
                    i7 &= -2;
                    break;
                case 1:
                    str2 = (String) this.f13149b.a(uVar);
                    i7 &= -3;
                    break;
                case 2:
                    str3 = (String) this.f13149b.a(uVar);
                    i7 &= -5;
                    break;
                case 3:
                    str4 = (String) this.f13149b.a(uVar);
                    i7 &= -9;
                    break;
                case 4:
                    filter = (Filter) this.c.a(uVar);
                    i7 &= -17;
                    break;
                case 5:
                    filter2 = (Filter) this.c.a(uVar);
                    i7 &= -33;
                    break;
                case 6:
                    num = (Integer) this.d.a(uVar);
                    i7 &= -65;
                    break;
                case 7:
                    num2 = (Integer) this.d.a(uVar);
                    i7 &= -129;
                    break;
                case 8:
                    num3 = (Integer) this.d.a(uVar);
                    i7 &= -257;
                    break;
                case 9:
                    bool = (Boolean) this.f13150e.a(uVar);
                    i7 &= -513;
                    break;
                case 10:
                    list = (List) this.f13151f.a(uVar);
                    i7 &= -1025;
                    break;
                case 11:
                    str5 = (String) this.f13149b.a(uVar);
                    i7 &= -2049;
                    break;
                case 12:
                    bool2 = (Boolean) this.f13150e.a(uVar);
                    i7 &= -4097;
                    break;
                case 13:
                    bool3 = (Boolean) this.f13150e.a(uVar);
                    i7 &= -8193;
                    break;
                case 14:
                    musicalKey = (MusicalKey) this.f13152g.a(uVar);
                    i7 &= -16385;
                    break;
                case 15:
                    f10 = (Float) this.f13153h.a(uVar);
                    i7 &= -32769;
                    break;
            }
        }
        uVar.j();
        if (i7 == -65536) {
            return new KaraokeSongResponseV2(str, str2, str3, str4, filter, filter2, num, num2, num3, bool, list, str5, bool2, bool3, musicalKey, f10);
        }
        Constructor declaredConstructor = this.f13154i;
        if (declaredConstructor == null) {
            declaredConstructor = KaraokeSongResponseV2.class.getDeclaredConstructor(String.class, String.class, String.class, String.class, Filter.class, Filter.class, Integer.class, Integer.class, Integer.class, Boolean.class, List.class, String.class, Boolean.class, Boolean.class, MusicalKey.class, Float.class, Integer.TYPE, f.c);
            this.f13154i = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, str2, str3, str4, filter, filter2, num, num2, num3, bool, list, str5, bool2, bool3, musicalKey, f10, Integer.valueOf(i7), null);
        m.e(objNewInstance, "newInstance(...)");
        return (KaraokeSongResponseV2) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        KaraokeSongResponseV2 karaokeSongResponseV2 = (KaraokeSongResponseV2) obj;
        m.f(xVar, "writer");
        if (karaokeSongResponseV2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("id");
        String str = karaokeSongResponseV2.f13136a;
        q qVar = this.f13149b;
        qVar.d(xVar, str);
        xVar.k("title");
        qVar.d(xVar, karaokeSongResponseV2.f13137b);
        xVar.k("artistName");
        qVar.d(xVar, karaokeSongResponseV2.c);
        xVar.k("artistId");
        qVar.d(xVar, karaokeSongResponseV2.d);
        xVar.k("genre");
        Filter filter = karaokeSongResponseV2.f13138e;
        q qVar2 = this.c;
        qVar2.d(xVar, filter);
        xVar.k("era");
        qVar2.d(xVar, karaokeSongResponseV2.f13139f);
        xVar.k("singerCount");
        Integer num = karaokeSongResponseV2.f13140g;
        q qVar3 = this.d;
        qVar3.d(xVar, num);
        xVar.k("durationSec");
        qVar3.d(xVar, karaokeSongResponseV2.f13141h);
        xVar.k("releaseYear");
        qVar3.d(xVar, karaokeSongResponseV2.f13142i);
        xVar.k("explicit");
        Boolean bool = karaokeSongResponseV2.f13143j;
        q qVar4 = this.f13150e;
        qVar4.d(xVar, bool);
        xVar.k("languages");
        this.f13151f.d(xVar, karaokeSongResponseV2.f13144k);
        xVar.k("cover");
        qVar.d(xVar, karaokeSongResponseV2.l);
        xVar.k("available");
        qVar4.d(xVar, karaokeSongResponseV2.f13145m);
        xVar.k("hasPitchInfo");
        qVar4.d(xVar, karaokeSongResponseV2.f13146n);
        xVar.k("musicalKey");
        this.f13152g.d(xVar, karaokeSongResponseV2.o);
        xVar.k("tempo");
        this.f13153h.d(xVar, karaokeSongResponseV2.f13147p);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(43, "GeneratedJsonAdapter(KaraokeSongResponseV2)");
    }
}
