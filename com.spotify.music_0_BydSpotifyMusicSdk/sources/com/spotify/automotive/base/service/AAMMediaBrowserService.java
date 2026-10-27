package com.spotify.automotive.base.service;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.partnervoice.PartnerVoiceSearchIntent;
import com.spotify.automotive.partnervoice.PartnerVoiceSearchSlotValueType;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.base.java.logging.Logger;
import com.spotify.music.R;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.operators.completable.e;
import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.NoWhenBranchMatchedException;
import p.a0;
import p.a82;
import p.ak;
import p.as2;
import p.az1;
import p.b82;
import p.bh1;
import p.bv2;
import p.c82;
import p.d82;
import p.e82;
import p.eg1;
import p.ek;
import p.f82;
import p.fk;
import p.gg1;
import p.he1;
import p.hg1;
import p.hs1;
import p.if2;
import p.is1;
import p.js1;
import p.ki1;
import p.kk0;
import p.lq2;
import p.lu;
import p.m;
import p.ou;
import p.p01;
import p.py0;
import p.rd1;
import p.sd1;
import p.sn;
import p.st1;
import p.t72;
import p.u72;
import p.up2;
import p.v03;
import p.v4;
import p.v72;
import p.vg1;
import p.vr2;
import p.w40;
import p.w72;
import p.wi0;
import p.wj2;
import p.wn;
import p.x72;
import p.xi;
import p.xj2;
import p.xn;
import p.y51;
import p.y72;
import p.yj;
import p.yn;
import p.z51;
import p.z72;
import p.zn;

/* JADX INFO: loaded from: classes.dex */
public final class AAMMediaBrowserService extends bh1 implements eg1 {
    public a q;

    @Override // p.bh1
    public final hg1 a(String str, int i) {
        a aVar = this.q;
        aVar.getClass();
        v03.j(str, "clientPackageName");
        if (!aVar.e.a(i, str)) {
            Logger.b("Failed to validate package: \"%s\", uid: %d", str, Integer.valueOf(i));
            return null;
        }
        p01 p01Var = new p01(25);
        ((Bundle) p01Var.e).putBoolean("android.media.browse.SEARCH_SUPPORTED", true);
        p01Var.E(2);
        p01Var.D(1);
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        ki1 ki1Var = aVar.g;
        String string = ki1Var.a(R.drawable.ic_play).toString();
        v03.i(string, "mediaUriUtil.toLocalCont…wable.ic_play).toString()");
        Context context = aVar.a;
        String string2 = context.getString(R.string.play_action_name);
        v03.i(string2, "mContext.getString(R.string.play_action_name)");
        Bundle bundleA = a.a("play", string2, string);
        String string3 = ki1Var.a(R.drawable.ic_pause).toString();
        v03.i(string3, "mediaUriUtil.toLocalCont…able.ic_pause).toString()");
        String string4 = context.getString(R.string.pause_action_name);
        v03.i(string4, "mContext.getString(R.string.pause_action_name)");
        Bundle bundleA2 = a.a("pause", string4, string3);
        String string5 = ki1Var.a(R.drawable.ic_shuffle).toString();
        v03.i(string5, "mediaUriUtil.toLocalCont…le.ic_shuffle).toString()");
        String string6 = context.getString(R.string.enable_shuffle_action_name);
        v03.i(string6, "mContext.getString(R.str…able_shuffle_action_name)");
        Bundle bundleA3 = a.a("shuffle", string6, string5);
        String string7 = ki1Var.a(R.drawable.ic_shuffle_active_green).toString();
        v03.i(string7, "mediaUriUtil.toLocalCont…_active_green).toString()");
        String string8 = context.getString(R.string.disable_shuffle_action_name);
        v03.i(string8, "mContext.getString(R.str…able_shuffle_action_name)");
        Bundle bundleA4 = a.a("disable-shuffle", string8, string7);
        arrayList.add(bundleA);
        arrayList.add(bundleA2);
        arrayList.add(bundleA3);
        arrayList.add(bundleA4);
        ((Bundle) p01Var.e).putParcelableArrayList("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ROOT_LIST", arrayList);
        Bundle bundle = (Bundle) p01Var.e;
        String str2 = bv2.a.c;
        hg1 hg1Var = new hg1(bundle, str2);
        kk0 kk0Var = aVar.f;
        kk0Var.getClass();
        kk0Var.l = str2;
        return hg1Var;
    }

    @Override // p.bh1
    public final void b(String str, vg1 vg1Var) {
        a aVar = this.q;
        aVar.getClass();
        v03.j(str, "itemId");
        if2 if2VarG = vr2.g(str);
        Referrer referrer = null;
        if (if2VarG == null) {
            vg1Var.g(null);
            return;
        }
        String strF = vr2.f(str);
        Integer numE = vr2.e(str);
        Integer numE2 = vr2.e(str);
        Referrer[] referrerArrValues = Referrer.values();
        int length = referrerArrValues.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Referrer referrer2 = referrerArrValues[i];
            if (v03.b(referrer2.a(), strF)) {
                referrer = referrer2;
                break;
            }
            i++;
        }
        aVar.c(if2VarG, numE2, referrer);
        vg1Var.a();
        sn snVarB = aVar.b();
        if (snVarB == null) {
            Logger.b("Called loadItem before binder is ready.", new Object[0]);
            vg1Var.f();
            return;
        }
        Locale localeS = he1.s(aVar.a);
        v03.i(localeS, "locale");
        AAMMediaBrowserServiceDelegate$resolveItem$1 aAMMediaBrowserServiceDelegate$resolveItem$1 = new AAMMediaBrowserServiceDelegate$resolveItem$1(1, vg1Var, vg1.class, "sendResult", "sendResult(Ljava/lang/Object;)V", 0);
        zn znVar = (zn) snVarB;
        znVar.c().flatMap(new xn(if2VarG, strF, numE, localeS, znVar, 0)).observeOn(znVar.d).map(yn.e).subscribe(new wn(2, aAMMediaBrowserServiceDelegate$resolveItem$1), new wn(3, aAMMediaBrowserServiceDelegate$resolveItem$1), znVar.g);
    }

    @Override // p.bh1
    public final void c(Bundle bundle, final gg1 gg1Var, String str) {
        py0 py0Var;
        f82 wj2Var;
        t72 z51Var;
        lq2 lq2Var;
        z72 w72Var;
        PartnerVoiceSearchIntent partnerVoiceSearchIntent;
        z72 w72Var2;
        he1 c82Var;
        a aVar = this.q;
        Bundle bundle2 = bundle == null ? Bundle.EMPTY : bundle;
        aVar.getClass();
        v03.j(str, "query");
        v03.j(bundle2, "extras");
        yj yjVar = aVar.d;
        sn snVarB = aVar.b();
        Context context = aVar.a;
        Resources resources = context.getResources();
        if (aVar.i == null && yjVar != null && snVarB != null && resources != null) {
            fk fkVar = ((ek) yjVar).b;
            xi xiVar = fkVar.q;
            st1 st1Var = xiVar.e;
            v03.i(st1Var, "playbackController");
            final as2 as2Var = xiVar.c;
            py0 py0Var2 = new py0() { // from class: com.spotify.automotive.base.service.AAMMediaBrowserServiceDelegate$getSearchActionResolver$searchActionResolverFactory$1
                {
                    super(0);
                }

                @Override // p.py0
                public final Object invoke() {
                    return as2Var.c;
                }
            };
            wi0 wi0Var = fkVar.x;
            v03.i(wi0Var, "eventSender");
            up2 up2Var = fkVar.t;
            v03.i(up2Var, "ubiLogger");
            u72 u72Var = new u72(snVarB, st1Var, py0Var2, resources, wi0Var, up2Var);
            final rd1 rd1Var = fkVar.g;
            py0 py0Var3 = new py0() { // from class: com.spotify.automotive.base.service.AAMMediaBrowserServiceDelegate$getSearchActionResolver$isLoggedInProvider$1
                {
                    super(0);
                }

                @Override // p.py0
                public final Object invoke() {
                    return Boolean.valueOf(((sd1) rd1Var).c == LoginState.e);
                }
            };
            m mVar = fkVar.m;
            v03.i(mVar, "automotiveProperties");
            aVar.i = new e82(u72Var, py0Var3, mVar);
        }
        e82 e82Var = aVar.i;
        if (e82Var == null) {
            Logger.b("Called onSearch before binder is ready.", new Object[0]);
            gg1Var.g(null);
            return;
        }
        gg1Var.a();
        Locale localeS = he1.s(context);
        v03.i(localeS, "locale");
        AAMMediaBrowserServiceDelegate$onSearch$1 aAMMediaBrowserServiceDelegate$onSearch$1 = new AAMMediaBrowserServiceDelegate$onSearch$1(1, gg1Var, vg1.class, "sendResult", "sendResult(Ljava/lang/Object;)V", 0);
        py0 py0Var4 = new py0() { // from class: com.spotify.automotive.base.service.AAMMediaBrowserServiceDelegate$onSearch$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                gg1Var.g(null);
                return lq2.a;
            }
        };
        if (!((Boolean) e82Var.b.invoke()).booleanValue()) {
            py0Var4.invoke();
            return;
        }
        if (((com.spotify.remoteconfig.a) e82Var.c.a.get()).f()) {
            String string = bundle2.getString("com.spotify.music.partnervoice.UTTERANCE");
            String str2 = e82.d;
            z72 v72Var = string == null ? new v72("com.spotify.music.partnervoice.UTTERANCE".concat(" is absent")) : v03.b(string, str2) ? new w72("com.spotify.music.partnervoice.UTTERANCE".concat(" is empty")) : new y72(string);
            String string2 = bundle2.getString("com.spotify.music.partnervoice.PROVIDER");
            z72 v72Var2 = string2 == null ? new v72("com.spotify.music.partnervoice.PROVIDER".concat(" is absent")) : v03.b(string2, str2) ? new w72("com.spotify.music.partnervoice.PROVIDER".concat(" is empty")) : new y72(string2);
            String string3 = bundle2.getString("com.spotify.music.partnervoice.LANGUAGE");
            if (string3 == null) {
                w72Var = new v72("com.spotify.music.partnervoice.LANGUAGE".concat(" is absent"));
            } else if (v03.b(string3, str2)) {
                w72Var = new w72("com.spotify.music.partnervoice.LANGUAGE".concat(" is empty"));
            } else {
                try {
                    new Locale.Builder().setLanguageTag(string3).build();
                    w72Var = new y72(string3);
                } catch (IllformedLocaleException unused) {
                    w72Var = new w72("com.spotify.music.partnervoice.LANGUAGE".concat(" has an incorrect value"));
                }
            }
            String string4 = bundle2.getString("com.spotify.music.partnervoice.INTENT");
            if (string4 == null) {
                w72Var2 = new v72("com.spotify.music.partnervoice.INTENT".concat(" is absent"));
            } else if (v03.b(string4, str2)) {
                w72Var2 = new w72("com.spotify.music.partnervoice.INTENT".concat(" is empty"));
            } else {
                PartnerVoiceSearchIntent[] partnerVoiceSearchIntentArrValues = PartnerVoiceSearchIntent.values();
                int length = partnerVoiceSearchIntentArrValues.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        partnerVoiceSearchIntent = null;
                        break;
                    }
                    PartnerVoiceSearchIntent partnerVoiceSearchIntent2 = partnerVoiceSearchIntentArrValues[i];
                    PartnerVoiceSearchIntent[] partnerVoiceSearchIntentArr = partnerVoiceSearchIntentArrValues;
                    if (v03.b(partnerVoiceSearchIntent2.name(), string4)) {
                        partnerVoiceSearchIntent = partnerVoiceSearchIntent2;
                        break;
                    } else {
                        i++;
                        partnerVoiceSearchIntentArrValues = partnerVoiceSearchIntentArr;
                    }
                }
                w72Var2 = (partnerVoiceSearchIntent == null || d82.a[partnerVoiceSearchIntent.ordinal()] == -1) ? new w72("com.spotify.music.partnervoice.INTENT".concat(" has an incorrect value")) : new y72(partnerVoiceSearchIntent);
            }
            if ((v72Var instanceof y72) && (v72Var2 instanceof y72) && (w72Var instanceof y72) && (w72Var2 instanceof y72)) {
                c82Var = new a82((String) ((y72) v72Var).a, (String) ((y72) v72Var2).a, (String) ((y72) w72Var).a, (PartnerVoiceSearchIntent) ((y72) w72Var2).a);
            } else if ((v72Var instanceof v72) && (v72Var2 instanceof v72) && (w72Var instanceof v72) && (w72Var2 instanceof v72)) {
                c82Var = b82.k;
            } else {
                List<z72> listC = v03.C(v72Var, v72Var2, w72Var, w72Var2);
                ArrayList arrayList = new ArrayList();
                for (z72 z72Var : listC) {
                    x72 x72Var = z72Var instanceof x72 ? (x72) z72Var : null;
                    if (x72Var != null) {
                        arrayList.add(x72Var);
                    }
                }
                ArrayList arrayList2 = new ArrayList(lu.W(arrayList));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((x72) it.next()).a());
                }
                c82Var = new c82(arrayList2);
            }
            if (c82Var instanceof a82) {
                a82 a82Var = (a82) c82Var;
                String str3 = a82Var.k;
                String str4 = a82Var.l;
                String str5 = a82Var.m;
                PartnerVoiceSearchIntent partnerVoiceSearchIntent3 = a82Var.n;
                ArrayList arrayList3 = new ArrayList();
                is1 is1VarA = e82.a(bundle2, "com.spotify.music.partnervoice.SLOT_ALBUM", PartnerVoiceSearchSlotValueType.SLOT_ALBUM);
                if (is1VarA != null) {
                    arrayList3.add(is1VarA);
                }
                is1 is1VarA2 = e82.a(bundle2, "com.spotify.music.partnervoice.SLOT_ARTIST", PartnerVoiceSearchSlotValueType.SLOT_ARTIST);
                if (is1VarA2 != null) {
                    arrayList3.add(is1VarA2);
                }
                is1 is1VarA3 = e82.a(bundle2, "com.spotify.music.partnervoice.SLOT_EPISODE", PartnerVoiceSearchSlotValueType.SLOT_EPISODE);
                if (is1VarA3 != null) {
                    arrayList3.add(is1VarA3);
                }
                is1 is1VarA4 = e82.a(bundle2, "com.spotify.music.partnervoice.SLOT_GENRE", PartnerVoiceSearchSlotValueType.SLOT_GENRE);
                if (is1VarA4 != null) {
                    arrayList3.add(is1VarA4);
                }
                is1 is1VarA5 = e82.a(bundle2, "com.spotify.music.partnervoice.SLOT_PLAYLIST", PartnerVoiceSearchSlotValueType.SLOT_PLAYLIST);
                if (is1VarA5 != null) {
                    arrayList3.add(is1VarA5);
                }
                is1 is1VarA6 = e82.a(bundle2, "com.spotify.music.partnervoice.SLOT_SHOW", PartnerVoiceSearchSlotValueType.SLOT_SHOW);
                if (is1VarA6 != null) {
                    arrayList3.add(is1VarA6);
                }
                is1 is1VarA7 = e82.a(bundle2, "com.spotify.music.partnervoice.SLOT_TRACK", PartnerVoiceSearchSlotValueType.SLOT_TRACK);
                if (is1VarA7 != null) {
                    arrayList3.add(is1VarA7);
                }
                wj2Var = new js1(str, bundle2, str3, str4, str5, partnerVoiceSearchIntent3, arrayList3);
                py0Var = py0Var4;
            } else {
                py0Var = py0Var4;
                if (c82Var instanceof c82) {
                    wj2Var = new y51(ou.h0(((c82) c82Var).k, null, null, null, null, 63));
                } else {
                    if (!(c82Var instanceof b82)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    wj2Var = new wj2(bundle2, str);
                }
            }
        } else {
            py0Var = py0Var4;
            wj2Var = new wj2(bundle2, str);
        }
        u72 u72Var2 = e82Var.a;
        u72Var2.getClass();
        if (wj2Var instanceof wj2) {
            z51Var = new xj2(u72Var2.a);
        } else if (wj2Var instanceof js1) {
            z51Var = new com.spotify.automotive.partnervoice.a(u72Var2.a, u72Var2.b, u72Var2.c, u72Var2.d, new hs1(u72Var2.e, u72Var2.f));
        } else if (wj2Var instanceof y51) {
            z51Var = new z51();
        } else {
            Logger.b("Unsupported SearchActionType!", new Object[0]);
            z51Var = null;
        }
        if (!(z51Var instanceof t72)) {
            z51Var = null;
        }
        if (z51Var != null) {
            z51Var.a(wj2Var, localeS, aAMMediaBrowserServiceDelegate$onSearch$1, py0Var);
            lq2Var = lq2.a;
        } else {
            lq2Var = null;
        }
        if (lq2Var == null) {
            Logger.b("The search could not be performed!", new Object[0]);
        }
    }

    @Override // p.bh1, android.app.Service
    public final void onCreate() {
        super.onCreate();
        final a aVar = new a(this, this, he1.n(this), new kk0(new p01((eg1) this)), new ki1(this, getPackageName()));
        this.q = aVar;
        aVar.k = new e(3, new az1() { // from class: p.hj
            @Override // p.az1
            public final void subscribe(rg2 rg2Var) {
                ij ijVar = aVar;
                ijVar.getClass();
                ak akVar = new ak(ijVar.a, new ww1(ijVar, rg2Var, 13));
                ijVar.c = akVar;
                akVar.a();
            }
        }).subscribe(new a0(0, aVar));
    }

    @Override // p.bh1, android.app.Service
    public final void onDestroy() {
        a aVar = this.q;
        if (!aVar.k.isDisposed()) {
            aVar.k.dispose();
        }
        zn znVar = aVar.h;
        if (znVar != null) {
            znVar.f.dispose();
            znVar.g.dispose();
        }
        v4 v4Var = aVar.j;
        if (v4Var != null) {
            ((io.reactivex.rxjava3.disposables.a) v4Var.l).d();
            w40 w40Var = (w40) v4Var.k;
            DisposableHelper.d(w40Var.c.a.c, null);
            vg1 vg1Var = w40Var.g;
            if (vg1Var != null) {
                vg1Var.g(new Bundle());
            }
        }
        ak akVar = aVar.c;
        if (akVar != null) {
            akVar.b();
        }
        super.onDestroy();
    }
}
