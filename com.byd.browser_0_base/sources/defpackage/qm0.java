package defpackage;

import J.N;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import androidx.core.app.NotificationCompat$Builder;
import com.google.protobuf.d0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.feed.FeedStream;
import org.chromium.chrome.browser.feed.webfeed.e;
import org.chromium.chrome.browser.firstrun.b;
import org.chromium.chrome.browser.notifications.NotificationServiceImpl;
import org.chromium.chrome.browser.omnibox.UrlBar;
import org.chromium.chrome.browser.omnibox.g;
import org.chromium.chrome.browser.omnibox.suggestions.c;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.chrome.browser.password_check.f;
import org.chromium.chrome.browser.password_manager.PasswordStoreAndroidBackendDispatcherBridgeImpl;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.searchwidget.SearchActivity;
import org.chromium.chrome.browser.searchwidget.SearchActivityLocationBarLayout;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.components.browser_ui.styles.R$attr;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.omnibox.AutocompleteResult;
import org.chromium.components.omnibox.a;
import org.chromium.components.policy.PolicyService;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class qm0 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ qm0(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.util.List] */
    @Override // java.lang.Runnable
    public final void run() {
        a aVarS;
        int i;
        ArrayList arrayList;
        int i2 = this.e;
        int i3 = 17;
        p52 p52Var = null;
        int i4 = 1;
        Object obj = this.f;
        switch (i2) {
            case 0:
                ((org.chromium.chrome.browser.download.home.filter.a) obj).a();
                break;
            case 1:
                ((s60) obj).o();
                break;
            case 2:
                FeedStream feedStream = (FeedStream) obj;
                feedStream.getClass();
                N.MOl5J0Or(feedStream.c, feedStream, new wb(24, feedStream));
                break;
            case 3:
                e eVar = (e) obj;
                if (!eVar.d.a("webfeed_follow_intro_debug.enable")) {
                    eVar.f.dismissed("IPH_WebFeedFollow");
                }
                break;
            case 4:
            default:
                SearchActivityLocationBarLayout searchActivityLocationBarLayout = (SearchActivityLocationBarLayout) obj;
                int i5 = SearchActivityLocationBarLayout.t;
                if (searchActivityLocationBarLayout.k != null && searchActivityLocationBarLayout.l != null) {
                    searchActivityLocationBarLayout.h.requestFocus();
                    searchActivityLocationBarLayout.k.e(true, false);
                    d dVar = searchActivityLocationBarLayout.l.h;
                    if (!dVar.v) {
                        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                        int iD = sharedPreferencesManager.d(-1, "zero_suggest_list_size");
                        if (iD <= 1) {
                            iD = 0;
                        }
                        ?? arrayList2 = new ArrayList(iD);
                        ArrayList arrayList3 = new ArrayList();
                        arrayList3.add(new AutocompleteMatch.a(0, 0));
                        int i6 = 0;
                        while (i6 < iD) {
                            if (TextUtils.isEmpty(sharedPreferencesManager.g(ar.x.a(i6), null))) {
                                GURL gurlA = GURL.a(sharedPreferencesManager.g(ar.r.a(i6), null));
                                String strG = sharedPreferencesManager.g(ar.s.a(i6), null);
                                String strG2 = sharedPreferencesManager.g(ar.t.a(i6), null);
                                int iD2 = sharedPreferencesManager.d(-1, ar.u.a(i6));
                                boolean z = sharedPreferencesManager.readBoolean(ar.w.a(i6), false);
                                boolean z2 = sharedPreferencesManager.readBoolean(ar.z.a(i6), false);
                                String strG3 = sharedPreferencesManager.g(ar.A.a(i6), null);
                                String strG4 = sharedPreferencesManager.g(ar.B.a(i6), null);
                                try {
                                    i = i6;
                                    arrayList = arrayList3;
                                    arrayList2.add(new AutocompleteMatch(iD2, el.a(sharedPreferencesManager.i(ar.v.a(i6), null), new Function() { // from class: dl
                                        @Override // java.util.function.Function
                                        public final Object apply(Object obj2) {
                                            return Integer.valueOf(Integer.parseInt((String) obj2));
                                        }
                                    }), z, 0, 0, strG, arrayList3, strG2, arrayList, null, null, gurlA, GURL.emptyGURL(), null, z2, strG3, strG4 == null ? null : Base64.decode(strG4, 0), sharedPreferencesManager.d(-1, ar.y.a(i6)), null, null, false, null, null));
                                } catch (NumberFormatException unused) {
                                    arrayList2 = Collections.emptyList();
                                }
                            } else {
                                i = i6;
                                arrayList = arrayList3;
                            }
                            i6 = i + 1;
                            arrayList3 = arrayList;
                        }
                        String strG5 = sharedPreferencesManager.g("Chrome.Omnibox.CachedZeroSuggestGroupsInfo", null);
                        if (strG5 != null) {
                            try {
                                aVarS = a.S(Base64.decode(strG5, 0));
                            } catch (d0 | IllegalArgumentException unused2) {
                                sharedPreferencesManager.removeKey("Chrome.Omnibox.CachedZeroSuggestGroupsInfo");
                                aVarS = a.Q().build();
                            }
                        } else {
                            aVarS = a.Q().build();
                        }
                        Map<Integer, pt0> mapP = aVarS.P();
                        for (int size = arrayList2.size() - 1; size >= 0; size--) {
                            AutocompleteMatch autocompleteMatch = (AutocompleteMatch) arrayList2.get(size);
                            int i7 = autocompleteMatch.r;
                            GURL gurl = autocompleteMatch.j;
                            if (!gurl.b || gurl.k() || (i7 != -1 && !mapP.containsKey(Integer.valueOf(i7)))) {
                                arrayList2.remove(size);
                            }
                        }
                        dVar.a(new AutocompleteResult(0L, arrayList2, aVarS), "", true);
                        break;
                    }
                }
                break;
            case 5:
                b bVar = (b) obj;
                fn0 fn0Var = bVar.b;
                en0 en0Var = fn0Var.f;
                if (en0Var != null) {
                    en0Var.a(true);
                }
                fn0Var.d.clear();
                fn0Var.e.clear();
                org.chromium.chrome.browser.firstrun.a aVar = bVar.a;
                aVar.e.a();
                o22 o22Var = aVar.i;
                if (o22Var != null) {
                    o22Var.e.a();
                    if (o22Var.h != null) {
                        PolicyService policyService = o22Var.g.get();
                        PolicyService.a aVar2 = o22Var.h;
                        org.chromium.base.b<PolicyService.a> bVar2 = policyService.b;
                        bVar2.c(aVar2);
                        if (bVar2.isEmpty()) {
                            N.MU0pXsSP(policyService.a, policyService);
                        }
                        o22Var.h = null;
                    }
                    aVar.i = null;
                }
                break;
            case 6:
                ((fj) obj).y();
                break;
            case 7:
                ly0 ly0Var = (ly0) obj;
                ly0Var.b.d(false);
                ly0Var.i.t(2, false);
                break;
            case 8:
                y8 y8Var = (y8) obj;
                y8Var.getClass();
                try {
                    org.chromium.base.library_loader.a aVar3 = org.chromium.base.library_loader.a.l;
                    aVar3.g.b();
                    aVar3.a();
                    x61.a();
                } catch (p52 e) {
                    p52Var = e;
                }
                ThreadUtils.e(new l91(y8Var, i3, p52Var));
                break;
            case 9:
                nj njVar = (nj) obj;
                if (!njVar.d()) {
                    njVar.g0();
                    break;
                }
                break;
            case 10:
                ((gb1) obj).a(false);
                break;
            case 11:
                NotificationServiceImpl.c((Intent) obj);
                break;
            case 12:
                mq1 mq1Var = (mq1) obj;
                mq1Var.getClass();
                n3.X0("OfflineIndicator.Hidden");
                mq1Var.q.a();
                mq1Var.m = SystemClock.elapsedRealtime();
                Context context = mq1Var.a;
                int iB = ii2.b(context);
                int iG = ii2.g(context, R$attr.colorOnPrimary);
                Drawable drawable = context.getDrawable(R$drawable.ic_globe_24dp);
                int iD3 = ii2.d(context);
                String string = context.getString(R$string.offline_indicator_v2_back_online_text);
                ft2 ft2Var = mq1Var.b;
                if (ft2Var.c) {
                    qt2 qt2Var = ft2Var.a;
                    if (string.equals(qt2Var.e.j(rt2.a)) && drawable == qt2Var.e.j(rt2.b) && iB == qt2Var.e.h(rt2.e) && iG == qt2Var.e.h(rt2.g)) {
                        qt2Var.e.h(rt2.h);
                    }
                    ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
                    jk0 jk0Var = h21.e;
                    valueAnimatorOfFloat.setInterpolator(jk0Var);
                    valueAnimatorOfFloat.setDuration(150L);
                    valueAnimatorOfFloat.addUpdateListener(new jt2(qt2Var, 0));
                    valueAnimatorOfFloat.addListener(new mt2(qt2Var, string, drawable, iG, iD3));
                    ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(qt2Var.e.h(rt2.e), iB);
                    valueAnimatorOfInt.setEvaluator(new ArgbEvaluator());
                    valueAnimatorOfInt.setInterpolator(jk0Var);
                    valueAnimatorOfInt.setDuration(400L);
                    valueAnimatorOfInt.addUpdateListener(new ht2(i4, qt2Var));
                    ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
                    valueAnimatorOfFloat2.setInterpolator(jk0Var);
                    valueAnimatorOfFloat2.setDuration(150L);
                    valueAnimatorOfFloat2.addUpdateListener(new jw1(i4, qt2Var));
                    AnimatorSet animatorSet = new AnimatorSet();
                    qt2Var.r = animatorSet;
                    animatorSet.play(valueAnimatorOfFloat).with(valueAnimatorOfInt);
                    qt2Var.r.play(valueAnimatorOfFloat2).after(valueAnimatorOfInt);
                    qt2Var.r.addListener(new nt2(qt2Var, mq1Var.j));
                    qt2Var.r.start();
                    break;
                }
                break;
            case 13:
                ((nq1) obj).getClass();
                break;
            case 14:
                ((g) obj).S();
                break;
            case 15:
                UrlBar urlBar = (UrlBar) obj;
                urlBar.setFocusable(urlBar.E);
                urlBar.setFocusableInTouchMode(urlBar.E);
                break;
            case 16:
                Iterator<vr1> it = ((c) obj).k.iterator();
                while (true) {
                    b.a aVar4 = (b.a) it;
                    if (aVar4.hasNext()) {
                        ((vr1) aVar4.next()).D0();
                    }
                    break;
                }
                break;
            case 17:
                ((org.chromium.chrome.browser.omnibox.c) ((d) obj).g).k.s(null, 12, false);
                break;
            case 18:
                ((i9) obj).a(true);
                break;
            case 19:
                f fVar = (f) obj;
                fVar.getClass();
                z7.b0(1);
                N.MqdzTSiP(((org.chromium.chrome.browser.password_check.e) fVar.j()).a.a);
                break;
            case 20:
                ((PasswordStoreAndroidBackendDispatcherBridgeImpl) obj).getClass();
                Context context2 = xz.a;
                if (context2 != null) {
                    String string2 = context2.getString(R$string.upm_error_notification_title);
                    String string3 = context2.getString(R$string.upm_error_notification_contents);
                    NotificationManagerProxyImpl notificationManagerProxyImpl = new NotificationManagerProxyImpl(context2);
                    qq qqVarD = n3.D("browser", new o8(37, 17, (String) null));
                    qqVarD.h(false);
                    qqVarD.k(string2);
                    qqVarD.j(string3);
                    qqVarD.p(R$drawable.ic_vpn_key_blue);
                    NotificationCompat$Builder notificationCompat$Builder = qqVarD.a;
                    notificationCompat$Builder.A.tickerText = NotificationCompat$Builder.b(string3);
                    notificationCompat$Builder.r = true;
                    notificationManagerProxyImpl.c(qqVarD.g(string3));
                    break;
                }
                break;
            case 21:
                s90 s90Var = (s90) obj;
                r90 r90Var = s90Var.a;
                if (r90Var != null) {
                    r90Var.I0(false, false);
                }
                Runnable runnable = s90Var.d;
                if (runnable != null) {
                    PostTask.c(rh3.a, runnable);
                }
                s90Var.a = null;
                s90Var.d = null;
                s90Var.c = null;
                break;
            case 22:
                fz1 fz1Var = (fz1) obj;
                fz1Var.getClass();
                fz1Var.f.l(gz1.d, false);
                fz1Var.g = null;
                break;
            case 23:
                ((c52) obj).l.pageScroll(130);
                break;
            case 24:
                ((d52) obj).h.pageScroll(130);
                break;
            case 25:
                g52 g52Var = (g52) obj;
                g52Var.l.scrollTo(0, g52Var.i.getTop());
                break;
            case 26:
                ((i7) ((b92) obj).b).c(Integer.valueOf(R$id.bookmark_this_page_id));
                break;
            case 27:
                ((v70) obj).dismiss();
                break;
            case 28:
                SearchActivity searchActivity = (SearchActivity) obj;
                searchActivity.X = false;
                searchActivity.f68J.b(searchActivity.d2());
                if (searchActivity.Y) {
                    searchActivity.V1();
                }
                break;
        }
    }
}
