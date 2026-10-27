package p;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.common.base.Optional;
import com.spotify.automotive.pathfinder.type.ContentRatingLabel;
import com.spotify.automotive.pathfinder.type.EpisodePlaybackState;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.mediasession.imageprovider.MediaUriUtil$Transformation;
import com.spotify.music.R;
import com.spotify.webapi.service.models.Pager;
import com.spotify.webapi.service.models.Playlist;
import com.spotify.webapi.service.models.PlaylistTrack;
import com.spotify.webapi.service.models.Search;
import com.spotify.webapi.service.models.UserPublic;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class bc implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Object l;
    public final /* synthetic */ Object m;

    public bc(Referrer referrer, Integer num, u5 u5Var, tx1 tx1Var) {
        this.c = 1;
        this.e = referrer;
        this.m = num;
        this.k = u5Var;
        this.l = tx1Var;
    }

    public final Optional a(Optional optional) {
        aw1 aw1Var;
        fe feVar;
        int i = this.c;
        Object obj = this.m;
        Object obj2 = this.k;
        Object obj3 = this.e;
        Object obj4 = this.l;
        switch (i) {
            case 1:
                wb wbVar = (wb) optional.h();
                if (wbVar == null) {
                    return Optional.a();
                }
                u5 u5Var = (u5) obj2;
                return Optional.e(v03.S(wbVar, (Referrer) obj3, (Integer) obj, u5Var.d, u5Var.e.a((tx1) obj4), u5Var.f, null));
            default:
                gt0 gt0Var = (gt0) optional.h();
                if (gt0Var != null && (feVar = gt0Var.b) != null) {
                    id2 id2Var = (id2) obj3;
                    return Optional.e(he1.T(feVar, (String) obj, null, (Integer) obj2, id2Var.b, id2Var.c.a((tx1) obj4), id2Var.e));
                }
                gt0 gt0Var2 = (gt0) optional.h();
                if (gt0Var2 == null || (aw1Var = gt0Var2.c) == null) {
                    return Optional.a();
                }
                id2 id2Var2 = (id2) obj3;
                return Optional.e(v03.T(aw1Var, (String) obj, null, (Integer) obj2, id2Var2.b, id2Var2.c.a((tx1) obj4)));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        String str;
        int i = this.c;
        Object objY = null;
        Object obj2 = this.l;
        Object obj3 = this.e;
        Object obj4 = this.k;
        Object obj5 = this.m;
        switch (i) {
            case 0:
                return b((Optional) obj);
            case 1:
                return a((Optional) obj);
            case 2:
                Playlist playlist = (Playlist) obj;
                v03.j(playlist, Search.Type.PLAYLIST);
                Integer numValueOf = Integer.valueOf(((ub1) obj4).b);
                if (numValueOf.intValue() <= 0) {
                    numValueOf = null;
                }
                int iIntValue = numValueOf != null ? numValueOf.intValue() : 400;
                Pager<PlaylistTrack> pager = playlist.tracks;
                int iMin = Math.min(pager != null ? pager.total : 0, iIntValue);
                int i2 = 100;
                if (iMin <= 100) {
                    return Single.just(playlist);
                }
                pu1 pu1Var = (pu1) obj5;
                if2 if2Var = (if2) obj3;
                Locale locale = (Locale) obj2;
                pu1Var.getClass();
                Playlist playlist2 = new Playlist();
                if (playlist.tracks == null) {
                    Single singleJust = Single.just(playlist2);
                    v03.i(singleJust, "just(empty)");
                    return singleJust;
                }
                ArrayList arrayListD = v03.D(Single.just(playlist));
                while (i2 < iMin) {
                    Playlist playlist3 = playlist2;
                    wv2 wv2Var = new wv2(locale, "from_token", null, null, Search.Type.EPISODE, 100, i2, 0, null, null, 1836);
                    yv2 yv2Var = pu1Var.b.b;
                    String str2 = if2Var.l;
                    v03.h(str2);
                    arrayListD.add(yv2Var.m(str2, wv2Var.a()).map(new ux1(5, pu1Var)).onErrorReturnItem(playlist3));
                    i2 += 100;
                    playlist2 = playlist3;
                }
                Flowable flowableConcat = Single.concat(arrayListD);
                du duVar = new du(0, pu1.g);
                flowableConcat.getClass();
                return new io.reactivex.rxjava3.internal.operators.observable.v2(new io.reactivex.rxjava3.internal.operators.flowable.g0(new io.reactivex.rxjava3.internal.operators.flowable.o(flowableConcat, duVar, 0)), 2, null);
            case 3:
                Playlist playlist4 = (Playlist) obj;
                v03.i(playlist4, Search.Type.PLAYLIST);
                String str3 = (String) obj5;
                Integer num = (Integer) obj4;
                pu1 pu1Var2 = (pu1) obj3;
                ki1 ki1Var = pu1Var2.c;
                lh1 lh1VarA = pu1Var2.e.a((tx1) obj2);
                v03.j(ki1Var, "mediaUriUtil");
                Resources resources = pu1Var2.a;
                v03.j(resources, "resources");
                w40 w40Var = pu1Var2.f;
                v03.j(w40Var, "customBrowseActionHandler");
                String str4 = playlist4.uri;
                if (str4 != null) {
                    Uri uriA = wb1.a(playlist4.images, ki1Var, MediaUriUtil$Transformation.k, ki1Var.a(R.drawable.playlist_placeholder), null);
                    UserPublic userPublic = playlist4.owner;
                    if (userPublic != null && (str = userPublic.display_name) != null) {
                        objY = resources.getString(R.string.content_playlist_subtitle, str);
                    }
                    objY = ci1.y(str3, num, str4, uriA, playlist4.name, objY, null, lh1VarA, w40Var);
                }
                return Optional.b(objY);
            case 4:
                return b((Optional) obj);
            default:
                return a((Optional) obj);
        }
    }

    public final List b(Optional optional) {
        mv1 mv1Var;
        ov1 ov1Var;
        List list;
        Iterable iterable;
        lh1 lh1Var;
        boolean zEquals;
        kr krVar;
        ArrayList arrayList;
        kr krVar2;
        double d;
        String strA;
        ContentRatingLabel contentRatingLabel;
        ContentRatingLabel contentRatingLabel2;
        sd sdVar;
        pr prVar;
        ir irVar;
        int i = this.c;
        Object obj = this.l;
        Object obj2 = this.k;
        Object obj3 = this.e;
        Object obj4 = this.m;
        switch (i) {
            case 0:
                pn0 pn0Var = (pn0) optional.h();
                if (pn0Var == null) {
                    return EmptyList.c;
                }
                u5 u5Var = (u5) obj2;
                ln0 ln0Var = pn0Var.a;
                sn0 sn0Var = ln0Var.a;
                String str = (String) obj4;
                Referrer referrer = (Referrer) obj3;
                u5Var.getClass();
                List list2 = sn0Var.b.a;
                ArrayList arrayList2 = new ArrayList(lu.W(list2));
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((nn0) it.next()).a.b);
                }
                ArrayList arrayList3 = new ArrayList();
                for (Object obj5 : arrayList2) {
                    if (((um2) obj5).h.a) {
                        arrayList3.add(obj5);
                    }
                }
                ArrayList arrayList4 = new ArrayList(lu.W(arrayList3));
                Iterator it2 = arrayList3.iterator();
                int i2 = 0;
                while (true) {
                    boolean zHasNext = it2.hasNext();
                    Resources resources = u5Var.b;
                    if (!zHasNext) {
                        int size = arrayList4.size();
                        tx1 tx1Var = (tx1) obj;
                        List list3 = ln0Var.b.b.a;
                        ArrayList arrayList5 = new ArrayList(lu.W(list3));
                        int i3 = 0;
                        for (Object obj6 : list3) {
                            int i4 = i3 + 1;
                            if (i3 < 0) {
                                v03.P();
                                throw null;
                            }
                            arrayList5.add(com.spotify.automotive.content.loaders.extensions.a.a(((mn0) obj6).b, referrer, resources.getString(R.string.content_albums_title), size + i3, u5Var.d, u5Var.e.a(tx1Var), u5Var.b, u5Var.f));
                            i3 = i4;
                        }
                        return ou.k0(arrayList5, arrayList4);
                    }
                    Object next = it2.next();
                    int i5 = i2 + 1;
                    if (i2 < 0) {
                        v03.P();
                        throw null;
                    }
                    arrayList4.add(com.spotify.automotive.content.loaders.extensions.a.b((um2) next, str, referrer, i2, u5Var.d, resources.getString(R.string.content_top_tracks_title), false, false, 96));
                    i2 = i5;
                }
                break;
            default:
                if (!optional.d()) {
                    return EmptyList.c;
                }
                ud udVar = ((lt0) optional.c()).b;
                if (udVar == null) {
                    qv1 qv1Var = ((lt0) optional.c()).c;
                    if (qv1Var == null || (mv1Var = qv1Var.a) == null || (ov1Var = mv1Var.b) == null || (list = ov1Var.a) == null) {
                        return EmptyList.c;
                    }
                    id2 id2Var = (id2) obj2;
                    if2 if2Var = (if2) obj3;
                    String str2 = (String) obj4;
                    id2Var.getClass();
                    ArrayList arrayList6 = new ArrayList();
                    Iterator it3 = list.iterator();
                    while (it3.hasNext()) {
                        bf0 bf0Var = ((nv1) it3.next()).b.a.b.a.c;
                        pe0 pe0Var = bf0Var != null ? bf0Var.b : null;
                        if (pe0Var != null) {
                            arrayList6.add(pe0Var);
                        }
                    }
                    ArrayList arrayList7 = new ArrayList();
                    for (Object obj7 : arrayList6) {
                        if (((pe0) obj7).g.a) {
                            arrayList7.add(obj7);
                        }
                    }
                    ArrayList arrayList8 = new ArrayList(lu.W(arrayList7));
                    int i6 = 0;
                    for (Object obj8 : arrayList7) {
                        int i7 = i6 + 1;
                        if (i6 < 0) {
                            v03.P();
                            throw null;
                        }
                        pe0 pe0Var2 = (pe0) obj8;
                        ki1 ki1Var = id2Var.b;
                        vj vjVar = id2Var.d;
                        ke0 ke0Var = pe0Var2.i.a.b;
                        arrayList8.add(f60.Q(pe0Var2, if2Var, str2, i6, ki1Var, vjVar, null, ke0Var != null ? ke0Var.a : null));
                        i6 = i7;
                    }
                    return arrayList8;
                }
                id2 id2Var2 = (id2) obj2;
                if2 if2Var2 = (if2) obj3;
                String str3 = (String) obj4;
                tx1 tx1Var2 = (tx1) obj;
                id2Var2.getClass();
                od odVar = udVar.b;
                boolean zB = odVar != null ? v03.b(odVar.a, Boolean.FALSE) : false;
                td tdVar = udVar.d;
                kr krVar3 = (tdVar == null || (prVar = tdVar.b) == null || (irVar = prVar.a) == null) ? null : irVar.c;
                pd pdVar = udVar.c;
                if (pdVar == null || (sdVar = pdVar.b) == null || (iterable = sdVar.a) == null) {
                    iterable = EmptyList.c;
                }
                ArrayList arrayList9 = new ArrayList();
                Iterator it4 = iterable.iterator();
                while (it4.hasNext()) {
                    kr krVar4 = ((rd) it4.next()).a.b.a.c;
                    if (krVar4 != null) {
                        arrayList9.add(krVar4);
                    }
                }
                ArrayList arrayList10 = new ArrayList();
                for (Object obj9 : arrayList9) {
                    kr krVar5 = (kr) obj9;
                    if (krVar5.g.a) {
                        if (!v03.b(krVar5.b, krVar3 != null ? krVar3.b : null)) {
                            arrayList10.add(obj9);
                        }
                    }
                }
                ArrayList arrayListO0 = ou.o0(arrayList10);
                if (krVar3 != null) {
                    arrayListO0.add(0, krVar3);
                }
                ArrayList arrayList11 = new ArrayList(lu.W(arrayListO0));
                int i8 = 0;
                for (Object obj10 : arrayListO0) {
                    int i9 = i8 + 1;
                    if (i8 < 0) {
                        v03.P();
                        throw null;
                    }
                    kr krVar6 = (kr) obj10;
                    boolean zB2 = v03.b(krVar6.b, krVar3 != null ? krVar3.b : null);
                    lh1 lh1VarA = id2Var2.c.a(tx1Var2);
                    v03.j(if2Var2, "contextUri");
                    ki1 ki1Var2 = id2Var2.b;
                    v03.j(ki1Var2, "mediaUriUtil");
                    vj vjVar2 = id2Var2.d;
                    v03.j(vjVar2, "podcastEpisodeSubtitle");
                    hr hrVar = krVar6.c;
                    Uri uriB = ki1Var2.b(he1.r(hrVar != null ? hrVar.b : null), MediaUriUtil$Transformation.e);
                    or orVar = krVar6.d;
                    Date dateF = orVar != null ? wb1.f(orVar.a) : null;
                    jr jrVar = krVar6.e;
                    int i10 = jrVar.a;
                    nr nrVar = krVar6.h;
                    tx1 tx1Var3 = tx1Var2;
                    int i11 = i10 - nrVar.a;
                    id2 id2Var3 = id2Var2;
                    p01 p01Var = new p01(25);
                    gr grVar = krVar6.f;
                    kz kzVar = grVar != null ? grVar.b : null;
                    if (kzVar == null || (contentRatingLabel2 = kzVar.a) == null) {
                        lh1Var = lh1VarA;
                        zEquals = false;
                    } else {
                        lh1Var = lh1VarA;
                        zEquals = contentRatingLabel2.equals(ContentRatingLabel.NINETEEN_PLUS);
                    }
                    p01Var.I(zEquals);
                    kz kzVar2 = grVar != null ? grVar.b : null;
                    p01Var.J((kzVar2 == null || (contentRatingLabel = kzVar2.a) == null) ? false : contentRatingLabel.equals(ContentRatingLabel.EXPLICIT));
                    int i12 = jrVar.a;
                    long j = i12;
                    p01Var.G(j);
                    if (i12 > 0) {
                        krVar = krVar6;
                        arrayList = arrayList11;
                        krVar2 = krVar3;
                        d = ((double) nrVar.a) / ((double) i12);
                    } else {
                        krVar = krVar6;
                        arrayList = arrayList11;
                        krVar2 = krVar3;
                        d = 0.0d;
                    }
                    p01Var.C(d);
                    EpisodePlaybackState episodePlaybackState = nrVar.b;
                    int iO = f60.O(episodePlaybackState);
                    if (iO == -1) {
                        ((Bundle) p01Var.e).remove("android.media.extra.PLAYBACK_STATUS");
                    } else {
                        ((Bundle) p01Var.e).putInt("android.media.extra.PLAYBACK_STATUS", iO);
                    }
                    String str4 = udVar.a;
                    if (TextUtils.isEmpty(str4)) {
                        ((Bundle) p01Var.e).remove("com.spotify.music.extra.CONTEXT_TITLE");
                    } else {
                        ((Bundle) p01Var.e).putString("com.spotify.music.extra.CONTEXT_TITLE", str4);
                    }
                    long j2 = i11;
                    if (j2 <= 0) {
                        ((Bundle) p01Var.e).remove("com.spotify.music.extra.TIME_LEFT_MS");
                    } else {
                        ((Bundle) p01Var.e).putLong("com.spotify.music.extra.TIME_LEFT_MS", j2);
                    }
                    Bundle bundle = (Bundle) p01Var.e;
                    TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                    lh1 lh1Var2 = lh1Var;
                    kr krVar7 = krVar;
                    ArrayList arrayList12 = arrayList;
                    kr krVar8 = krVar2;
                    String strC = vjVar2.c(new rv1((int) timeUnit.toSeconds(j), (int) timeUnit.toSeconds(j2), episodePlaybackState == EpisodePlaybackState.COMPLETED, dateF == null ? new Date(0L) : dateF, true, zB2, zB, 256));
                    String str5 = krVar7.b;
                    if (zB2 && zB) {
                        kh1 kh1VarS = yn.s(str5);
                        kh1VarS.c = str3;
                        kh1VarS.d = Integer.valueOf(i8);
                        strA = kh1VarS.a();
                    } else {
                        kh1 kh1VarS2 = yn.s(str5);
                        kh1VarS2.b = if2Var2.toString();
                        kh1VarS2.c = str3;
                        kh1VarS2.d = Integer.valueOf(i8);
                        strA = kh1VarS2.a();
                    }
                    jh1 jh1Var = new jh1();
                    jh1Var.a = strA;
                    jh1Var.g = Uri.parse(str5);
                    jh1Var.f = uriB;
                    jh1Var.b = krVar7.a;
                    jh1Var.c = strC;
                    jh1Var.h = bundle;
                    arrayList12.add(new dg1(jh1Var, lh1Var2.a(str5)));
                    tx1Var2 = tx1Var3;
                    arrayList11 = arrayList12;
                    i8 = i9;
                    id2Var2 = id2Var3;
                    krVar3 = krVar8;
                }
                return arrayList11;
        }
    }

    public /* synthetic */ bc(Object obj, Object obj2, Comparable comparable, Object obj3, int i) {
        this.c = i;
        this.k = obj;
        this.m = obj2;
        this.e = comparable;
        this.l = obj3;
    }

    public /* synthetic */ bc(String str, Integer num, tb1 tb1Var, tx1 tx1Var, int i) {
        this.c = i;
        this.m = str;
        this.k = num;
        this.e = tb1Var;
        this.l = tx1Var;
    }

    public bc(id2 id2Var, if2 if2Var, String str, tx1 tx1Var) {
        this.c = 4;
        this.k = id2Var;
        this.e = if2Var;
        this.m = str;
        this.l = tx1Var;
    }
}
