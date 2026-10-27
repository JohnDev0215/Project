package com.spotify.automotive.browse;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat$MediaItem;
import android.support.v4.media.MediaDescriptionCompat;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import p.dg1;
import p.if2;
import p.jh1;
import p.lu;
import p.rn;
import p.ry0;
import p.tb1;
import p.tc1;
import p.tx1;
import p.ub1;
import p.ux1;
import p.v03;
import p.vx1;
import p.xb1;
import p.yn;

/* JADX INFO: loaded from: classes.dex */
public final class a implements rn {
    public final vx1 a;
    public final xb1 b;
    public final Scheduler c;

    public a(vx1 vx1Var, xb1 xb1Var, Scheduler scheduler) {
        v03.j(vx1Var, "productStateController");
        v03.j(xb1Var, "loadersResolver");
        v03.j(scheduler, "ioScheduler");
        this.a = vx1Var;
        this.b = xb1Var;
        this.c = scheduler;
    }

    public static final ArrayList d(a aVar, List list) {
        aVar.getClass();
        ArrayList arrayList = new ArrayList(lu.W(list));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            dg1 dg1Var = (dg1) it.next();
            jh1 jh1Var = dg1Var.a;
            arrayList.add(new MediaBrowserCompat$MediaItem(new MediaDescriptionCompat((String) jh1Var.a, (CharSequence) jh1Var.b, (CharSequence) jh1Var.c, (CharSequence) jh1Var.d, (Bitmap) jh1Var.e, (Uri) jh1Var.f, (Bundle) jh1Var.h, (Uri) jh1Var.g), dg1Var.b));
        }
        return arrayList;
    }

    @Override // p.rn
    public final Single a(final com.spotify.navigation.identifier.a aVar, final Locale locale, final Bundle bundle) {
        v03.j(aVar, "viewUri");
        v03.j(locale, "locale");
        v03.j(bundle, "extras");
        Single singleE = e(new ry0() { // from class: com.spotify.automotive.browse.LoggedInBrowseActionResolver$resolveChildren$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                tx1 tx1Var = (tx1) obj;
                v03.j(tx1Var, "productState");
                tb1 tb1VarB = this.this$0.b.b(aVar);
                Locale locale2 = locale;
                Bundle bundle2 = bundle;
                v03.j(bundle2, "bundle");
                Single map = tb1VarB.c(locale2, new ub1(bundle2.getInt("com.spotify.music.extra.GROUP_LIMIT", 0), bundle2.getInt("com.spotify.music.extra.LIST_LIMIT", 0)), tx1Var).subscribeOn(this.this$0.c).retry(1L).map(new tc1(this.this$0, 0));
                v03.i(map, "override fun resolveChil…rItems) }\n        }\n    }");
                return map;
            }
        });
        v03.i(singleE, "override fun resolveChil…rItems) }\n        }\n    }");
        return singleE;
    }

    @Override // p.rn
    public final Single b(final if2 if2Var, final String str, final Locale locale, final Bundle bundle) {
        v03.j(if2Var, "spotifyUri");
        v03.j(locale, "locale");
        v03.j(bundle, "extras");
        Single singleE = e(new ry0() { // from class: com.spotify.automotive.browse.LoggedInBrowseActionResolver$resolveChildren$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                tx1 tx1Var = (tx1) obj;
                v03.j(tx1Var, "productState");
                tb1 tb1VarC = this.this$0.b.c(if2Var);
                if2 if2Var2 = if2Var;
                String str2 = str;
                Locale locale2 = locale;
                Bundle bundle2 = bundle;
                v03.j(bundle2, "bundle");
                Single map = tb1VarC.a(if2Var2, str2, locale2, new ub1(bundle2.getInt("com.spotify.music.extra.GROUP_LIMIT", 0), bundle2.getInt("com.spotify.music.extra.LIST_LIMIT", 0)), tx1Var).subscribeOn(this.this$0.c).retry(1L).map(new tc1(this.this$0, 1));
                v03.i(map, "override fun resolveChil…rItems) }\n        }\n    }");
                return map;
            }
        });
        v03.i(singleE, "override fun resolveChil…rItems) }\n        }\n    }");
        return singleE;
    }

    @Override // p.rn
    public final Single c(final if2 if2Var, final String str, final Integer num, final Locale locale) {
        v03.j(if2Var, "spotifyUri");
        v03.j(locale, "locale");
        Single singleE = e(new ry0() { // from class: com.spotify.automotive.browse.LoggedInBrowseActionResolver$resolveItem$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                tx1 tx1Var = (tx1) obj;
                v03.j(tx1Var, "productState");
                Single map = this.this$0.b.c(if2Var).b(if2Var, str, num, locale, tx1Var).subscribeOn(this.this$0.c).retry(1L).map(new tc1(this.this$0, 2));
                v03.i(map, "override fun resolveItem…        }\n        }\n    }");
                return map;
            }
        });
        v03.i(singleE, "override fun resolveItem…        }\n        }\n    }");
        return singleE;
    }

    public final Single e(ry0 ry0Var) {
        b bVar = this.a.d;
        v03.i(bVar, "productStateSubject");
        return bVar.take(1L).single(new tx1()).onErrorReturn(yn.k).flatMap(new ux1(3, ry0Var));
    }
}
