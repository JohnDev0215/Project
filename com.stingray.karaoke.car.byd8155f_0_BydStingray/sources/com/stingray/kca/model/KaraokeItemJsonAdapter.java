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
import java.util.Map;
import kotlin.Metadata;
import n7.w;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/KaraokeItemJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/KaraokeItem;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class KaraokeItemJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13123a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13124b;
    public final q c;
    public final q d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q f13125e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final q f13126f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final q f13127g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final q f13128h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final q f13129i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final q f13130j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final q f13131k;
    public volatile Constructor l;

    public KaraokeItemJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13123a = C1489n1.z("id", "name", "image", "subtitle", "type", "childType", "childItems", "showChildren", "showPlayAll", "sortingTypes", "visibleChildItemsCount", "alwaysLoadChildren", "isExplicit", "isAvailable", "hasPitchInfo", "alwaysHasMore", "fillWithEmpty", "logDatabag", "images", "scoringDetail");
        w wVar = w.l;
        this.f13124b = h10.c(String.class, wVar, "id");
        this.c = h10.c(KaraokeItemType.class, wVar, "type");
        this.d = h10.c(KaraokeItemChildType.class, wVar, "childType");
        this.f13125e = h10.c(M.g(List.class, KaraokeItem.class), wVar, "childItems");
        this.f13126f = h10.c(Boolean.class, wVar, "showChildren");
        this.f13127g = h10.c(M.g(List.class, KaraokeItemSortTypeInfo.class), wVar, "sortingTypes");
        this.f13128h = h10.c(Integer.class, wVar, "visibleChildItemsCount");
        this.f13129i = h10.c(M.g(Map.class, String.class, String.class), wVar, "logDatabag");
        this.f13130j = h10.c(Images.class, wVar, "images");
        this.f13131k = h10.c(ScoringDetail.class, wVar, "scoringDetail");
    }

    @Override // E5.q
    public final Object a(u uVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        int i7;
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        int i10 = -1;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        KaraokeItemType karaokeItemType = null;
        KaraokeItemChildType karaokeItemChildType = null;
        List list = null;
        Boolean bool = null;
        Boolean bool2 = null;
        List list2 = null;
        Integer num = null;
        Boolean bool3 = null;
        Boolean bool4 = null;
        Boolean bool5 = null;
        Boolean bool6 = null;
        Boolean bool7 = null;
        Boolean bool8 = null;
        Map map = null;
        Images images = null;
        ScoringDetail scoringDetail = null;
        while (uVar.m()) {
            switch (uVar.F(this.f13123a)) {
                case -1:
                    uVar.I();
                    uVar.K();
                    continue;
                case 0:
                    str = (String) this.f13124b.a(uVar);
                    i10 &= -2;
                    continue;
                case 1:
                    str2 = (String) this.f13124b.a(uVar);
                    i10 &= -3;
                    continue;
                case 2:
                    str3 = (String) this.f13124b.a(uVar);
                    i10 &= -5;
                    continue;
                case 3:
                    str4 = (String) this.f13124b.a(uVar);
                    i10 &= -9;
                    continue;
                case 4:
                    karaokeItemType = (KaraokeItemType) this.c.a(uVar);
                    i10 &= -17;
                    continue;
                case 5:
                    karaokeItemChildType = (KaraokeItemChildType) this.d.a(uVar);
                    i10 &= -33;
                    continue;
                case 6:
                    list = (List) this.f13125e.a(uVar);
                    i10 &= -65;
                    continue;
                case 7:
                    bool = (Boolean) this.f13126f.a(uVar);
                    i10 &= -129;
                    continue;
                case 8:
                    bool2 = (Boolean) this.f13126f.a(uVar);
                    i10 &= -257;
                    continue;
                case 9:
                    list2 = (List) this.f13127g.a(uVar);
                    i10 &= -513;
                    continue;
                case 10:
                    num = (Integer) this.f13128h.a(uVar);
                    i10 &= -1025;
                    continue;
                case 11:
                    bool3 = (Boolean) this.f13126f.a(uVar);
                    i10 &= -2049;
                    continue;
                case 12:
                    bool4 = (Boolean) this.f13126f.a(uVar);
                    i10 &= -4097;
                    continue;
                case 13:
                    bool5 = (Boolean) this.f13126f.a(uVar);
                    i10 &= -8193;
                    continue;
                case 14:
                    bool6 = (Boolean) this.f13126f.a(uVar);
                    i10 &= -16385;
                    continue;
                case 15:
                    bool7 = (Boolean) this.f13126f.a(uVar);
                    i7 = -32769;
                    break;
                case 16:
                    bool8 = (Boolean) this.f13126f.a(uVar);
                    i7 = -65537;
                    break;
                case 17:
                    map = (Map) this.f13129i.a(uVar);
                    i7 = -131073;
                    break;
                case 18:
                    images = (Images) this.f13130j.a(uVar);
                    i7 = -262145;
                    break;
                case 19:
                    scoringDetail = (ScoringDetail) this.f13131k.a(uVar);
                    i7 = -524289;
                    break;
            }
            i10 &= i7;
        }
        uVar.j();
        if (i10 == -1048576) {
            return new KaraokeItem(str, str2, str3, str4, karaokeItemType, karaokeItemChildType, list, bool, bool2, list2, num, bool3, bool4, bool5, bool6, bool7, bool8, map, images, scoringDetail);
        }
        Constructor declaredConstructor = this.l;
        if (declaredConstructor == null) {
            declaredConstructor = KaraokeItem.class.getDeclaredConstructor(String.class, String.class, String.class, String.class, KaraokeItemType.class, KaraokeItemChildType.class, List.class, Boolean.class, Boolean.class, List.class, Integer.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Map.class, Images.class, ScoringDetail.class, Integer.TYPE, f.c);
            this.l = declaredConstructor;
            m.e(declaredConstructor, "also(...)");
        }
        Object objNewInstance = declaredConstructor.newInstance(str, str2, str3, str4, karaokeItemType, karaokeItemChildType, list, bool, bool2, list2, num, bool3, bool4, bool5, bool6, bool7, bool8, map, images, scoringDetail, Integer.valueOf(i10), null);
        m.e(objNewInstance, "newInstance(...)");
        return (KaraokeItem) objNewInstance;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        KaraokeItem karaokeItem = (KaraokeItem) obj;
        m.f(xVar, "writer");
        if (karaokeItem == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("id");
        String str = karaokeItem.f13107a;
        q qVar = this.f13124b;
        qVar.d(xVar, str);
        xVar.k("name");
        qVar.d(xVar, karaokeItem.f13108b);
        xVar.k("image");
        qVar.d(xVar, karaokeItem.c);
        xVar.k("subtitle");
        qVar.d(xVar, karaokeItem.d);
        xVar.k("type");
        this.c.d(xVar, karaokeItem.f13109e);
        xVar.k("childType");
        this.d.d(xVar, karaokeItem.f13110f);
        xVar.k("childItems");
        this.f13125e.d(xVar, karaokeItem.f13111g);
        xVar.k("showChildren");
        Boolean bool = karaokeItem.f13112h;
        q qVar2 = this.f13126f;
        qVar2.d(xVar, bool);
        xVar.k("showPlayAll");
        qVar2.d(xVar, karaokeItem.f13113i);
        xVar.k("sortingTypes");
        this.f13127g.d(xVar, karaokeItem.f13114j);
        xVar.k("visibleChildItemsCount");
        this.f13128h.d(xVar, karaokeItem.f13115k);
        xVar.k("alwaysLoadChildren");
        qVar2.d(xVar, karaokeItem.l);
        xVar.k("isExplicit");
        qVar2.d(xVar, karaokeItem.f13116m);
        xVar.k("isAvailable");
        qVar2.d(xVar, karaokeItem.f13117n);
        xVar.k("hasPitchInfo");
        qVar2.d(xVar, karaokeItem.o);
        xVar.k("alwaysHasMore");
        qVar2.d(xVar, karaokeItem.f13118p);
        xVar.k("fillWithEmpty");
        qVar2.d(xVar, karaokeItem.f13119q);
        xVar.k("logDatabag");
        this.f13129i.d(xVar, karaokeItem.f13120r);
        xVar.k("images");
        this.f13130j.d(xVar, karaokeItem.f13121s);
        xVar.k("scoringDetail");
        this.f13131k.d(xVar, karaokeItem.f13122t);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(33, "GeneratedJsonAdapter(KaraokeItem)");
    }
}
