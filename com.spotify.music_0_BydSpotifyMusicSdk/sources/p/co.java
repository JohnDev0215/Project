package p;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.common.uri.SpotifyUri$Kind;
import com.spotify.mediasession.mediaitem.MediaBrowserItemsExtrasBuilder$IconHint;
import com.spotify.music.R;
import com.spotify.ucs.proto.v0.UcsRequest;
import com.spotify.voiceassistants.playermodels.SearchEndpointRequest;
import com.spotify.webapi.service.models.Entity;
import com.spotify.webapi.service.models.Episode;
import com.spotify.webapi.service.models.LinkedTrack;
import com.spotify.webapi.service.models.Pager;
import com.spotify.webapi.service.models.Playlist;
import com.spotify.webapi.service.models.PlaylistTrack;
import com.spotify.webapi.service.models.ResumePoint;
import com.spotify.webapi.service.models.Search;
import com.spotify.webapi.service.models.Track;
import com.spotify.webapi.service.models.views.View;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Pair;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class co implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final Object e;
    public final Object k;

    public /* synthetic */ co(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    public final ArrayList a(Pair pair) {
        List listB;
        ArrayList arrayList;
        dg1 dg1VarG;
        Referrer referrer = Referrer.LIBRARY;
        int i = 0;
        int i2 = this.c;
        Object obj = this.e;
        Object obj2 = this.k;
        switch (i2) {
            case 5:
                List list = (List) pair.a();
                int iIntValue = ((Number) pair.b()).intValue();
                kz2 kz2Var = (kz2) obj;
                tx1 tx1Var = (tx1) obj2;
                String str = kz2Var.d.c;
                mh1 mh1Var = kz2Var.e;
                if (str == null) {
                    lc.n("Couldn't get username for collection.");
                    listB = EmptyList.c;
                } else if (iIntValue > 0) {
                    Object[] objArr = {Integer.valueOf(iIntValue)};
                    Resources resources = kz2Var.a;
                    String quantityString = resources.getQuantityString(R.plurals.content_liked_songs_subtitle, iIntValue, objArr);
                    v03.i(quantityString, "resources.getQuantityStr… numSavedTracks\n        )");
                    String string = new if2(SpotifyUri$Kind.COLLECTION, null, str).toString();
                    v03.i(string, "collection(username).toString()");
                    p01 p01Var = new p01(25);
                    p01Var.E(1);
                    Bundle bundle = (Bundle) p01Var.e;
                    jh1 jh1Var = new jh1();
                    kh1 kh1VarS = yn.s(string);
                    kh1VarS.b(referrer);
                    kh1VarS.d = 0;
                    jh1Var.a = kh1VarS.a();
                    jh1Var.b = resources.getString(R.string.content_liked_songs_title);
                    jh1Var.c = quantityString;
                    jh1Var.g = Uri.parse(string);
                    jh1Var.h = bundle;
                    jh1Var.f = kz2Var.c.a(R.drawable.liked_songs_cover);
                    listB = v03.B(new dg1(jh1Var, mh1Var.a(tx1Var).a(string)));
                } else {
                    listB = EmptyList.c;
                }
                List list2 = listB;
                ArrayList arrayList2 = new ArrayList();
                for (Object obj3 : list) {
                    int i3 = i + 1;
                    if (i < 0) {
                        v03.P();
                        throw null;
                    }
                    xu1 xu1Var = ((av1) obj3).a.c;
                    if (xu1Var != null) {
                        arrayList = arrayList2;
                        dg1VarG = ci1.G(xu1Var, referrer, null, list2.size() + i, true, kz2Var.c, mh1Var.a(tx1Var), kz2Var.a, kz2Var.f);
                    } else {
                        arrayList = arrayList2;
                        dg1VarG = null;
                    }
                    if (dg1VarG != null) {
                        arrayList.add(dg1VarG);
                    }
                    arrayList2 = arrayList;
                    i = i3;
                }
                return ou.k0(arrayList2, list2);
            default:
                List list3 = (List) pair.a();
                int iIntValue2 = ((Number) pair.b()).intValue();
                if (iIntValue2 <= 0) {
                    return lz2.e((lz2) obj, list3, 0, (tx1) obj2);
                }
                lz2 lz2Var = (lz2) obj;
                String str2 = lz2Var.e.c;
                tx1 tx1Var2 = (tx1) obj2;
                Object[] objArr2 = {Integer.valueOf(iIntValue2)};
                Resources resources2 = lz2Var.a;
                String quantityString2 = resources2.getQuantityString(R.plurals.content_your_episodes_subtitle, iIntValue2, objArr2);
                v03.i(quantityString2, "resources.getQuantityStr…umSavedEpisodes\n        )");
                if (str2 == null) {
                    str2 = "";
                }
                String string2 = new if2(SpotifyUri$Kind.COLLECTION_YOUR_EPISODES, null, str2).toString();
                v03.i(string2, "collectionYourEpisodes(username ?: \"\").toString()");
                p01 p01Var2 = new p01(25);
                p01Var2.E(1);
                Bundle bundle2 = (Bundle) p01Var2.e;
                jh1 jh1Var2 = new jh1();
                kh1 kh1VarS2 = yn.s(string2);
                kh1VarS2.c = referrer.a();
                kh1VarS2.d = 0;
                jh1Var2.a = kh1VarS2.a();
                jh1Var2.b = resources2.getString(R.string.content_your_episodes_title);
                jh1Var2.c = quantityString2;
                jh1Var2.g = Uri.parse(string2);
                jh1Var2.h = bundle2;
                jh1Var2.f = lz2Var.c.a(R.drawable.your_episodes_cover);
                return ou.k0(lz2.e(lz2Var, list3, 1, tx1Var2), v03.B(new dg1(jh1Var2, lz2Var.d.a(tx1Var2).a(string2))));
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        List<Entity> list;
        List<PlaylistTrack> list2;
        long j;
        Iterator it;
        long j2 = 0;
        int i = 1;
        int i2 = this.c;
        Object obj2 = this.e;
        Object obj3 = this.k;
        switch (i2) {
            case 0:
                View view = (View) obj;
                v03.j(view, Search.Type.VIEW);
                Pager<Entity> pager = view.content;
                if (pager == null || (list = pager.items) == null) {
                    return EmptyList.c;
                }
                ArrayList arrayList = new ArrayList();
                Cdo cdo = (Cdo) obj2;
                cdo.f(list, arrayList, cdo.c, cdo.d.a((tx1) obj3), cdo.e);
                return arrayList;
            case 1:
                Playlist playlist = (Playlist) obj;
                v03.j(playlist, Search.Type.PLAYLIST);
                Pager<PlaylistTrack> pager2 = playlist.tracks;
                if (pager2 == null || (list2 = pager2.items) == null) {
                    return EmptyList.c;
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object obj4 : list2) {
                    PlaylistTrack playlistTrack = (PlaylistTrack) obj4;
                    if (playlistTrack != null && v03.b(playlistTrack.is_local, Boolean.FALSE) && Entity.Companion.isPresentValidAndPlayable(playlistTrack.entity)) {
                        arrayList2.add(obj4);
                    }
                }
                pu1 pu1Var = (pu1) obj2;
                String str = (String) obj3;
                ArrayList arrayList3 = new ArrayList();
                Iterator it2 = arrayList2.iterator();
                int i3 = 0;
                while (it2.hasNext()) {
                    Object next = it2.next();
                    int i4 = i3 + 1;
                    dg1 dg1Var = null;
                    if (i3 < 0) {
                        v03.P();
                        throw null;
                    }
                    Entity entity = ((PlaylistTrack) next).entity;
                    if (entity != null) {
                        jh1 jh1VarD = wb1.d(entity, pu1Var.c, str, null);
                        p01 p01VarE = wb1.e(entity);
                        if (entity instanceof Track) {
                            Track track = (Track) entity;
                            LinkedTrack linkedTrack = track.linked_from;
                            String str2 = linkedTrack != null ? linkedTrack.uri : track.uri;
                            jh1VarD.g = Uri.parse(str2);
                            v03.i(str2, "trackUri");
                            kh1 kh1VarS = yn.s(str2);
                            kh1VarS.b = playlist.uri;
                            kh1VarS.c = str;
                            kh1VarS.d = Integer.valueOf(i3);
                            jh1VarD.a = kh1VarS.a();
                            p01VarE.B(MediaBrowserItemsExtrasBuilder$IconHint.HEART);
                        } else {
                            if (entity instanceof Episode) {
                                Episode episode = (Episode) entity;
                                String str3 = episode.uri;
                                if (str3 == null) {
                                    j = j2;
                                    it = it2;
                                } else {
                                    TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                                    ResumePoint resumePoint = episode.resumePoint;
                                    long j3 = episode.durationMs;
                                    if (resumePoint == null) {
                                        it = it2;
                                    } else {
                                        Boolean bool = resumePoint.fullyPlayed;
                                        if (bool == null || !bool.booleanValue()) {
                                            it = it2;
                                            j3 -= (long) resumePoint.resumePositionMs;
                                        } else {
                                            it = it2;
                                            j3 = 0;
                                        }
                                    }
                                    long seconds = timeUnit.toSeconds(j3);
                                    String str4 = episode.releaseDate;
                                    str4.getClass();
                                    Date dateF = wb1.f(str4);
                                    int i5 = episode.durationMs / 1000;
                                    Integer numValueOf = Integer.valueOf((int) seconds);
                                    j = 0;
                                    boolean z = seconds == 0;
                                    dateF.getClass();
                                    jh1VarD.c = pu1Var.d.c(new rv1(i5, numValueOf != null ? numValueOf.intValue() : -1, z, dateF, false, false, false, 480));
                                    kh1 kh1VarS2 = yn.s(str3);
                                    kh1VarS2.b = playlist.uri;
                                    kh1VarS2.c = str;
                                    kh1VarS2.d = Integer.valueOf(i3);
                                    jh1VarD.a = kh1VarS2.a();
                                }
                                p01VarE.B(MediaBrowserItemsExtrasBuilder$IconHint.PLUS);
                            }
                            p01VarE.A();
                            jh1VarD.h = (Bundle) p01VarE.e;
                            dg1Var = new dg1(jh1VarD, 2);
                        }
                        j = j2;
                        it = it2;
                        p01VarE.A();
                        jh1VarD.h = (Bundle) p01VarE.e;
                        dg1Var = new dg1(jh1VarD, 2);
                    } else {
                        j = j2;
                        it = it2;
                    }
                    if (dg1Var != null) {
                        arrayList3.add(dg1Var);
                    }
                    i3 = i4;
                    j2 = j;
                    it2 = it;
                }
                return arrayList3;
            case 2:
                return b((List) obj);
            case 3:
                return b((List) obj);
            case 4:
                return b((List) obj);
            case 5:
                return a((Pair) obj);
            case 6:
                return a((Pair) obj);
            case 7:
                String str5 = (String) obj;
                v03.i(str5, "etag");
                return str5.length() > 0 ? ((com.spotify.remoteconfig.fetcherimpl.a) obj2).b.b((UcsRequest) obj3, str5) : ((com.spotify.remoteconfig.fetcherimpl.a) obj2).b.c((UcsRequest) obj3);
            case 8:
                Throwable th = (Throwable) obj;
                int i6 = ((AtomicInteger) obj2).get();
                int i7 = i6 - 1;
                ((u11) obj3).getClass();
                if (i7 == 3) {
                    int i8 = Flowable.c;
                    Objects.requireNonNull(th, "throwable is null");
                    return new io.reactivex.rxjava3.internal.operators.flowable.s(i, new io.reactivex.rxjava3.internal.functions.e(th));
                }
                long j4 = 500 * i6;
                hk0 hk0VarA = qk2.a();
                Object[] objArr = {Integer.valueOf(i6), th.getMessage(), Long.valueOf(j4)};
                hk0VarA.getClass();
                hk0.t(objArr);
                TimeUnit timeUnit2 = TimeUnit.MILLISECONDS;
                int i9 = Flowable.c;
                Scheduler scheduler = io.reactivex.rxjava3.schedulers.e.b;
                Objects.requireNonNull(timeUnit2, "unit is null");
                Objects.requireNonNull(scheduler, "scheduler is null");
                return new io.reactivex.rxjava3.internal.operators.flowable.x0(Math.max(0L, j4), timeUnit2, scheduler);
            case 9:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                xe2 xe2Var = (xe2) obj2;
                re2 re2Var = xe2Var.a;
                SearchEndpointRequest searchEndpointRequest = (SearchEndpointRequest) obj3;
                re2Var.getClass();
                return re2Var.a.a(searchEndpointRequest, zBooleanValue ? "alternative-results" : "").compose(xe2Var.d);
            default:
                TimeUnit timeUnit3 = (TimeUnit) obj2;
                ((Scheduler) obj3).getClass();
                return new io.reactivex.rxjava3.schedulers.f(obj, Scheduler.a(timeUnit3), timeUnit3);
        }
    }

    public final List b(List list) {
        Referrer referrer = Referrer.LIBRARY;
        int i = this.c;
        int i2 = 0;
        Object obj = this.k;
        Object obj2 = this.e;
        switch (i) {
            case 2:
                v03.i(list, "albums");
                gz2 gz2Var = (gz2) obj2;
                tx1 tx1Var = (tx1) obj;
                ArrayList arrayList = new ArrayList(lu.W(list));
                int i3 = 0;
                for (Object obj3 : list) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        v03.P();
                        throw null;
                    }
                    arrayList.add(com.spotify.automotive.content.loaders.extensions.a.a(((x5) obj3).b, referrer, null, i3, gz2Var.c, gz2Var.d.a(tx1Var), gz2Var.a, gz2Var.e));
                    i3 = i4;
                }
                return arrayList;
            case 3:
                v03.i(list, "artists");
                hz2 hz2Var = (hz2) obj2;
                tx1 tx1Var2 = (tx1) obj;
                ArrayList arrayList2 = new ArrayList(lu.W(list));
                for (Object obj4 : list) {
                    int i5 = i2 + 1;
                    if (i2 < 0) {
                        v03.P();
                        throw null;
                    }
                    arrayList2.add(v03.S(((dc) obj4).b, referrer, Integer.valueOf(i2), hz2Var.c, hz2Var.d.a(tx1Var2), hz2Var.e, null));
                    i2 = i5;
                }
                return arrayList2;
            default:
                iz2 iz2Var = (iz2) obj2;
                v03.i(list, "it");
                tx1 tx1Var3 = (tx1) obj;
                iz2Var.getClass();
                ArrayList arrayList3 = new ArrayList();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    fe feVar = ((me) it.next()).a.c;
                    if (feVar != null) {
                        arrayList3.add(feVar);
                    }
                }
                ArrayList arrayList4 = new ArrayList(lu.W(arrayList3));
                int i6 = 0;
                for (Object obj5 : arrayList3) {
                    int i7 = i6 + 1;
                    if (i6 < 0) {
                        v03.P();
                        throw null;
                    }
                    arrayList4.add(he1.S((fe) obj5, referrer, null, i6, iz2Var.c, iz2Var.d.a(tx1Var3), iz2Var.a));
                    i6 = i7;
                }
                return arrayList4;
        }
    }
}
