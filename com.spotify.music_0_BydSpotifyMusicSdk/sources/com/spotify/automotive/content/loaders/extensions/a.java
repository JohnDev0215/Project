package com.spotify.automotive.content.loaders.extensions;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import com.spotify.automotive.pathfinder.type.ContentRatingLabel;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.mediasession.imageprovider.MediaUriUtil$Transformation;
import com.spotify.mediasession.mediaitem.MediaBrowserItemsExtrasBuilder$IconHint;
import com.spotify.music.R;
import java.util.ArrayList;
import p.dg1;
import p.he1;
import p.i5;
import p.j5;
import p.jh1;
import p.k5;
import p.kh1;
import p.ki1;
import p.kz;
import p.lh1;
import p.m5;
import p.mm2;
import p.om2;
import p.ou;
import p.p01;
import p.pm2;
import p.rm2;
import p.ry0;
import p.um2;
import p.v03;
import p.w40;
import p.wb1;
import p.yn;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final dg1 a(m5 m5Var, Referrer referrer, String str, int i, ki1 ki1Var, lh1 lh1Var, Resources resources, w40 w40Var) {
        String string;
        v03.j(m5Var, "<this>");
        v03.j(referrer, "referrer");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(resources, "resources");
        v03.j(w40Var, "customBrowseActionHandler");
        i5 i5Var = m5Var.c;
        Uri uriB = wb1.b(he1.r(i5Var != null ? i5Var.b : null), ki1Var, MediaUriUtil$Transformation.k, ki1Var.a(R.drawable.album_placeholder));
        String strH0 = ou.h0(m5Var.d.a, ", ", null, null, new ry0() { // from class: com.spotify.automotive.content.loaders.extensions.AlbumExtKt$toMediaBrowserItem$artists$1
            @Override // p.ry0
            public final Object invoke(Object obj) {
                k5 k5Var = (k5) obj;
                v03.j(k5Var, "it");
                return k5Var.a.a;
            }
        }, 30);
        j5 j5Var = m5Var.e;
        if (j5Var != null && (string = resources.getString(R.string.content_album_subtitle, strH0, Integer.valueOf(j5Var.a))) != null) {
            strH0 = string;
        }
        String str2 = m5Var.a;
        kh1 kh1VarS = yn.s(str2);
        kh1VarS.b(referrer);
        kh1VarS.d = Integer.valueOf(i);
        String strA = kh1VarS.a();
        ArrayList arrayListA = w40Var.a(strA);
        p01 p01Var = new p01(25);
        p01Var.E(1);
        p01Var.H(str);
        p01Var.A();
        p01Var.B(MediaBrowserItemsExtrasBuilder$IconHint.HEART);
        p01Var.F(arrayListA);
        Bundle bundle = (Bundle) p01Var.e;
        jh1 jh1Var = new jh1();
        jh1Var.a = strA;
        jh1Var.g = Uri.parse(str2);
        jh1Var.f = uriB;
        jh1Var.b = m5Var.b;
        jh1Var.c = strH0;
        jh1Var.h = bundle;
        return new dg1(jh1Var, lh1Var.a(str2));
    }

    public static dg1 b(um2 um2Var, String str, Referrer referrer, int i, ki1 ki1Var, String str2, boolean z, boolean z2, int i2) {
        ContentRatingLabel contentRatingLabel;
        ContentRatingLabel contentRatingLabel2;
        pm2 pm2Var;
        if ((i2 & 16) != 0) {
            str2 = null;
        }
        boolean zEquals = false;
        if ((i2 & 32) != 0) {
            z = false;
        }
        if ((i2 & 64) != 0) {
            z2 = false;
        }
        v03.j(um2Var, "<this>");
        v03.j(referrer, "referrer");
        v03.j(ki1Var, "mediaUriUtil");
        mm2 mm2Var = um2Var.b;
        Uri uriB = wb1.b(he1.r((mm2Var == null || (pm2Var = mm2Var.a) == null) ? null : pm2Var.b), ki1Var, MediaUriUtil$Transformation.k, ki1Var.a(R.drawable.song_placeholder));
        p01 p01Var = new p01(25);
        p01Var.H(str2);
        om2 om2Var = um2Var.c;
        kz kzVar = om2Var != null ? om2Var.b : null;
        p01Var.I((kzVar == null || (contentRatingLabel2 = kzVar.a) == null) ? false : contentRatingLabel2.equals(ContentRatingLabel.NINETEEN_PLUS));
        kz kzVar2 = om2Var != null ? om2Var.b : null;
        if (kzVar2 != null && (contentRatingLabel = kzVar2.a) != null) {
            zEquals = contentRatingLabel.equals(ContentRatingLabel.EXPLICIT);
        }
        p01Var.J(zEquals);
        p01Var.A();
        p01Var.G(um2Var.i.a);
        p01Var.B(MediaBrowserItemsExtrasBuilder$IconHint.HEART);
        if (z) {
            int i3 = um2Var.g;
            if (i3 <= 0) {
                ((Bundle) p01Var.e).remove("android.media.metadata.DISC_NUMBER");
            } else {
                ((Bundle) p01Var.e).putInt("android.media.metadata.DISC_NUMBER", i3);
            }
        }
        if (z2) {
            int i4 = um2Var.f;
            if (i4 <= 0) {
                ((Bundle) p01Var.e).remove("android.media.metadata.TRACK_NUMBER");
            } else {
                ((Bundle) p01Var.e).putInt("android.media.metadata.TRACK_NUMBER", i4);
            }
        }
        Bundle bundle = (Bundle) p01Var.e;
        v03.i(bundle, "mediaBrowserItemsExtrasBuilder.build()");
        String str3 = um2Var.e;
        kh1 kh1VarS = yn.s(str3);
        kh1VarS.b = str;
        kh1VarS.b(referrer);
        kh1VarS.d = Integer.valueOf(i);
        String strA = kh1VarS.a();
        jh1 jh1Var = new jh1();
        jh1Var.a = strA;
        jh1Var.g = Uri.parse(str3);
        jh1Var.f = uriB;
        jh1Var.b = um2Var.d;
        jh1Var.h = bundle;
        jh1Var.c = ou.h0(um2Var.a.a, ", ", null, null, new ry0() { // from class: com.spotify.automotive.content.loaders.extensions.TrackExtKt$toMediaBrowserItem$1
            @Override // p.ry0
            public final Object invoke(Object obj) {
                rm2 rm2Var = (rm2) obj;
                v03.j(rm2Var, "it");
                return rm2Var.a.a;
            }
        }, 30);
        return new dg1(jh1Var, um2Var.h.a ? 2 : 1);
    }
}
