package com.spotify.automotive.partnervoice;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat$MediaItem;
import android.support.v4.media.MediaDescriptionCompat;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.base.java.logging.Logger;
import com.spotify.common.uri.SpotifyUri$Kind;
import com.spotify.music.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.collections.EmptyList;
import p.bq2;
import p.cq2;
import p.f82;
import p.g81;
import p.h81;
import p.he1;
import p.hs1;
import p.if2;
import p.is1;
import p.js1;
import p.kh1;
import p.ks1;
import p.lq2;
import p.ls1;
import p.ou;
import p.py0;
import p.ry0;
import p.sn;
import p.st1;
import p.t72;
import p.v03;
import p.vr2;
import p.yn;
import p.z1;
import p.zn;

/* JADX INFO: loaded from: classes.dex */
public final class a implements t72 {
    public static final List f = v03.C(Integer.valueOf(R.string.content_albums_title), Integer.valueOf(R.string.content_artists_title), Integer.valueOf(R.string.content_playlists_title), Integer.valueOf(R.string.content_episodes_title));
    public final sn a;
    public final st1 b;
    public final py0 c;
    public final Resources d;
    public final hs1 e;

    public a(sn snVar, st1 st1Var, py0 py0Var, Resources resources, hs1 hs1Var) {
        v03.j(snVar, "browseActionResolverProxy");
        v03.j(st1Var, "playbackController");
        v03.j(py0Var, "usernameProvider");
        v03.j(resources, "resources");
        this.a = snVar;
        this.b = st1Var;
        this.c = py0Var;
        this.d = resources;
        this.e = hs1Var;
    }

    public static final ArrayList b(a aVar, List list, List list2) {
        aVar.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem = (MediaBrowserCompat$MediaItem) obj;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator it = list2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        String string = aVar.d.getString(((Number) it.next()).intValue());
                        Bundle bundle = mediaBrowserCompat$MediaItem.e.o;
                        if (v03.b(string, bundle != null ? bundle.getString("android.media.browse.CONTENT_STYLE_GROUP_TITLE_HINT") : null)) {
                            arrayList.add(obj);
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static final boolean c(a aVar, MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem, List list) {
        String string;
        SpotifyUri$Kind spotifyUri$Kind;
        aVar.getClass();
        Uri uri = mediaBrowserCompat$MediaItem.e.f1p;
        if (uri == null || (string = uri.toString()) == null || (spotifyUri$Kind = new if2(string).e) == null) {
            return false;
        }
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((is1) it.next()).a.a().contains(spotifyUri$Kind)) {
                return true;
            }
        }
        return false;
    }

    @Override // p.t72
    public final void a(f82 f82Var, Locale locale, final ry0 ry0Var, py0 py0Var) {
        final js1 js1Var = (js1) f82Var;
        int i = ks1.a[js1Var.f.ordinal()];
        lq2 lq2Var = lq2.a;
        Referrer referrer = Referrer.PARTNER_VOICE;
        py0 py0Var2 = this.c;
        sn snVar = this.a;
        hs1 hs1Var = this.e;
        Bundle bundle = js1Var.b;
        String str = js1Var.a;
        switch (i) {
            case 1:
                if2 if2VarH = vr2.h(str);
                if (if2VarH != null) {
                    String strA = referrer.a();
                    Bundle bundle2 = new Bundle(bundle);
                    bundle2.putInt("com.spotify.music.extra.LIST_LIMIT", 6);
                    ((zn) snVar).e(if2VarH, strA, locale, bundle2, new ry0() { // from class: com.spotify.automotive.partnervoice.PartnerVoiceSearchActionResolver$handlePlayIntent$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // p.ry0
                        public final Object invoke(Object obj) {
                            Object next;
                            MediaDescriptionCompat mediaDescriptionCompat;
                            Uri uri;
                            List list = (List) obj;
                            v03.j(list, "mediaItems");
                            List listL0 = ou.l0(a.b(this.this$0, list, v03.B(Integer.valueOf(R.string.content_top_results_title))), 6);
                            a aVar = this.this$0;
                            js1 js1Var2 = js1Var;
                            Iterator it = listL0.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    next = null;
                                    break;
                                }
                                next = it.next();
                                if (a.c(aVar, (MediaBrowserCompat$MediaItem) next, js1Var2.g)) {
                                    break;
                                }
                            }
                            MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem = (MediaBrowserCompat$MediaItem) next;
                            if (mediaBrowserCompat$MediaItem == null) {
                                mediaBrowserCompat$MediaItem = (MediaBrowserCompat$MediaItem) ou.d0(listL0);
                            }
                            ArrayList arrayListO0 = ou.o0(listL0);
                            if ((arrayListO0 instanceof g81) && !(arrayListO0 instanceof h81)) {
                                he1.Q(arrayListO0, "kotlin.collections.MutableCollection");
                                throw null;
                            }
                            arrayListO0.remove(mediaBrowserCompat$MediaItem);
                            String string = (mediaBrowserCompat$MediaItem == null || (mediaDescriptionCompat = mediaBrowserCompat$MediaItem.e) == null || (uri = mediaDescriptionCompat.f1p) == null) ? null : uri.toString();
                            hs1 hs1Var2 = this.this$0.e;
                            js1 js1Var3 = js1Var;
                            hs1Var2.getClass();
                            v03.j(js1Var3, "params");
                            hs1Var2.b(string != null ? hs1Var2.b.b(new bq2(string)) : null, js1Var3);
                            if (string != null) {
                                ((z1) this.this$0.b).s(null, string, null, Referrer.PARTNER_VOICE.a());
                            }
                            ry0Var.invoke(arrayListO0);
                            return lq2.a;
                        }
                    });
                } else {
                    lq2Var = null;
                }
                if (lq2Var == null) {
                    hs1Var.getClass();
                    hs1Var.b(null, js1Var);
                    ry0Var.invoke(EmptyList.c);
                }
                break;
            case 2:
                String str2 = (String) py0Var2.invoke();
                if (str2 == null) {
                    Logger.b("Could not retrieve username for Partner Voice search action!", new Object[0]);
                    py0Var.invoke();
                } else {
                    ((z1) this.b).s(null, new if2(SpotifyUri$Kind.COLLECTION, null, str2).toString(), null, referrer.a());
                    ry0Var.invoke(EmptyList.c);
                }
                break;
            case 3:
                hs1Var.getClass();
                hs1Var.b(hs1Var.b.b(cq2.h), js1Var);
                if2 if2VarH2 = vr2.h(str);
                if (if2VarH2 != null) {
                    ((zn) snVar).e(if2VarH2, referrer.a(), locale, js1Var.b, ry0Var);
                } else {
                    lq2Var = null;
                }
                if (lq2Var == null) {
                    ry0Var.invoke(EmptyList.c);
                }
                break;
            case 4:
                if2 if2VarH3 = vr2.h(str);
                if (if2VarH3 != null) {
                    String strA2 = referrer.a();
                    Bundle bundle3 = new Bundle(bundle);
                    bundle3.putInt("com.spotify.music.extra.LIST_LIMIT", 1);
                    ((zn) snVar).e(if2VarH3, strA2, locale, bundle3, new ry0() { // from class: com.spotify.automotive.partnervoice.PartnerVoiceSearchActionResolver$handleShowIntent$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // p.ry0
                        public final Object invoke(Object obj) {
                            String string;
                            Object next;
                            MediaDescriptionCompat mediaDescriptionCompat;
                            Uri uri;
                            List list = (List) obj;
                            v03.j(list, "mediaItems");
                            a aVar = this.this$0;
                            js1 js1Var2 = js1Var;
                            Iterator it = list.iterator();
                            while (true) {
                                string = null;
                                if (!it.hasNext()) {
                                    next = null;
                                    break;
                                }
                                next = it.next();
                                if (a.c(aVar, (MediaBrowserCompat$MediaItem) next, js1Var2.g)) {
                                    break;
                                }
                            }
                            MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem = (MediaBrowserCompat$MediaItem) next;
                            if (mediaBrowserCompat$MediaItem == null) {
                                mediaBrowserCompat$MediaItem = (MediaBrowserCompat$MediaItem) ou.d0(a.b(this.this$0, list, a.f));
                            }
                            if (mediaBrowserCompat$MediaItem != null && (mediaDescriptionCompat = mediaBrowserCompat$MediaItem.e) != null && (uri = mediaDescriptionCompat.f1p) != null) {
                                string = uri.toString();
                            }
                            this.this$0.e.a(string, js1Var);
                            ry0Var.invoke(mediaBrowserCompat$MediaItem != null ? v03.B(mediaBrowserCompat$MediaItem) : EmptyList.c);
                            return lq2.a;
                        }
                    });
                } else {
                    lq2Var = null;
                }
                if (lq2Var == null) {
                    hs1Var.a(null, js1Var);
                    ry0Var.invoke(EmptyList.c);
                }
                break;
            case 5:
                String str3 = (String) py0Var2.invoke();
                if (str3 == null) {
                    hs1Var.a(null, js1Var);
                    Logger.b("Could not retrieve username for show liked songs action!", new Object[0]);
                    py0Var.invoke();
                } else {
                    String string = new if2(SpotifyUri$Kind.COLLECTION, null, str3).toString();
                    v03.i(string, "collection(username).toString()");
                    d(string, js1Var, ry0Var);
                }
                break;
            case 6:
                d(ls1.a, js1Var, ry0Var);
                break;
            case 7:
                d(ls1.b, js1Var, ry0Var);
                break;
        }
    }

    public final void d(String str, js1 js1Var, ry0 ry0Var) {
        Uri uri = Uri.parse(str);
        this.e.a(uri.toString(), js1Var);
        kh1 kh1VarS = yn.s(str);
        kh1VarS.b(Referrer.PARTNER_VOICE);
        ry0Var.invoke(v03.B(new MediaBrowserCompat$MediaItem(new MediaDescriptionCompat(kh1VarS.a(), null, null, null, null, null, null, uri), 1)));
    }
}
