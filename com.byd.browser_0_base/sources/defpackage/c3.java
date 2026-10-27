package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import defpackage.z2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.app.video_tutorials.VideoPlayerActivity;
import org.chromium.chrome.browser.app.video_tutorials.VideoTutorialListActivity;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.SaveUpdateAddressProfilePrompt;
import org.chromium.chrome.browser.autofill.SaveUpdateAddressProfilePromptController;
import org.chromium.chrome.browser.autofill.VirtualCardEnrollmentDelegate;
import org.chromium.chrome.browser.autofill.VirtualCardEnrollmentDialogViewBridge;
import org.chromium.chrome.browser.autofill.settings.b;
import org.chromium.chrome.browser.bookmarks.PowerBookmarkShoppingItemRow;
import org.chromium.chrome.browser.creator.CreatorCoordinator;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.chrome.browser.download.settings.a;
import org.chromium.chrome.browser.feed.FeedSwipeRefreshLayout;
import org.chromium.chrome.browser.feed.c;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$FollowResults;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$QueryResult;
import org.chromium.chrome.browser.feed.webfeed.e;
import org.chromium.chrome.browser.feed.webfeed.i;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.video_tutorials.Tutorial;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.signin.identitymanager.IdentityManager;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c3 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ c3(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:28:0x00b0. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        Object[] objArr;
        int i = 4;
        int i2 = this.e;
        boolean z = false;
        int i3 = 0;
        z = false;
        Object obj2 = this.f;
        switch (i2) {
            case 0:
                z2.b bVar = (z2.b) obj2;
                Tab tab = (Tab) obj;
                Tab tab2 = bVar.g;
                if (tab2 != null) {
                    tab2.t(bVar);
                }
                bVar.g = tab;
                if (tab != null) {
                    tab.x(bVar);
                }
                bVar.y1(tab);
                break;
            case 1:
                ChromeActivity chromeActivity = (ChromeActivity) obj2;
                k11 k11Var = ChromeActivity.Z0;
                chromeActivity.getClass();
                ei2 ei2Var = new ei2((y13) obj);
                chromeActivity.U0 = ei2Var;
                chromeActivity.S0.a(ei2Var, 5);
                chromeActivity.f0.d(chromeActivity.V0);
                break;
            case 2:
                ((pq) obj2).e = (l61) obj;
                break;
            case 3:
                VideoPlayerActivity videoPlayerActivity = (VideoPlayerActivity) obj2;
                int i4 = VideoPlayerActivity.K;
                videoPlayerActivity.getClass();
                n3.F.a = ((Tutorial) obj).a;
                Intent intent = new Intent();
                intent.setData(Uri.parse("chrome-native://newtab/"));
                intent.setClass(videoPlayerActivity, ChromeTabbedActivity.class);
                videoPlayerActivity.startActivity(intent);
                break;
            case 4:
                VideoTutorialListActivity videoTutorialListActivity = (VideoTutorialListActivity) obj2;
                int i5 = VideoTutorialListActivity.I;
                videoTutorialListActivity.getClass();
                VideoPlayerActivity.K1(videoTutorialListActivity, ((Tutorial) obj).a);
                break;
            case 5:
                SaveUpdateAddressProfilePrompt saveUpdateAddressProfilePrompt = (SaveUpdateAddressProfilePrompt) obj2;
                saveUpdateAddressProfilePrompt.g = true;
                PersonalDataManager.AutofillProfile autofillProfile = ((qa) obj).l;
                SaveUpdateAddressProfilePromptController saveUpdateAddressProfilePromptController = saveUpdateAddressProfilePrompt.a;
                long j = saveUpdateAddressProfilePromptController.a;
                if (j != 0) {
                    N.MvmusX4r(j, saveUpdateAddressProfilePromptController, autofillProfile);
                }
                saveUpdateAddressProfilePrompt.b.b(3, saveUpdateAddressProfilePrompt.c);
                break;
            case 6:
                VirtualCardEnrollmentDialogViewBridge virtualCardEnrollmentDialogViewBridge = (VirtualCardEnrollmentDialogViewBridge) obj2;
                Integer num = (Integer) obj;
                virtualCardEnrollmentDialogViewBridge.getClass();
                int iIntValue = num.intValue();
                VirtualCardEnrollmentDelegate virtualCardEnrollmentDelegate = virtualCardEnrollmentDialogViewBridge.c;
                if (iIntValue == 1) {
                    long j2 = virtualCardEnrollmentDelegate.a;
                    if (j2 != 0) {
                        N.MPydP90q(j2);
                    }
                } else if (num.intValue() != 2) {
                    long j3 = virtualCardEnrollmentDelegate.a;
                    if (j3 != 0) {
                        N.MbA3iL6k(j3);
                    }
                } else {
                    long j4 = virtualCardEnrollmentDelegate.a;
                    if (j4 != 0) {
                        N.MBzRm4sq(j4);
                    }
                }
                break;
            case 7:
                sq3 sq3Var = (sq3) obj2;
                sq3Var.g((Integer) sq3.c(sq3Var.j), (fj) obj);
                break;
            case 8:
                ((b) obj2).f.j(2, (String) obj);
                break;
            case 9:
                nb nbVar = (nb) obj2;
                nbVar.getClass();
                oj2.U(2, 3, "Autofill.VirtualCard.SettingsPageUnenrollment.LinkClicked");
                CustomTabActivity.i3(nbVar.a, (String) obj);
                break;
            case 10:
                yh1 yh1Var = (yh1) obj2;
                Tab tab3 = (Tab) obj;
                aq1<Boolean> aq1Var = yh1Var.e;
                if (tab3 != null && yh1Var.f.test(tab3)) {
                    z = true;
                }
                aq1Var.r(Boolean.valueOf(z));
                break;
            case 11:
                ((qf) obj2).c((BookmarkId) obj);
                break;
            case 12:
                PowerBookmarkShoppingItemRow powerBookmarkShoppingItemRow = (PowerBookmarkShoppingItemRow) obj2;
                int i6 = PowerBookmarkShoppingItemRow.T;
                powerBookmarkShoppingItemRow.getClass();
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                powerBookmarkShoppingItemRow.O = zBooleanValue;
                powerBookmarkShoppingItemRow.o.setImageResource(zBooleanValue ? R$drawable.price_tracking_enabled_filled : R$drawable.price_tracking_disabled);
                break;
            case 13:
                WebFeedBridge$QueryResult webFeedBridge$QueryResult = (WebFeedBridge$QueryResult) obj;
                PropertyModel propertyModel = ((CreatorCoordinator) obj2).j;
                propertyModel.p(n10.b, webFeedBridge$QueryResult.b);
                PropertyModel.r<String> rVar = n10.c;
                String str = webFeedBridge$QueryResult.c;
                propertyModel.p(rVar, str);
                propertyModel.p(n10.d, N.MpICpYBr(new GURL(str)));
                break;
            case 14:
                ((m10) obj2).getClass();
                break;
            case 15:
                fj2 fj2Var = (fj2) obj2;
                if (((Integer) obj).intValue() == 1) {
                    fj2Var.getClass();
                    break;
                } else {
                    ej2 ej2Var = (ej2) fj2Var.g.f();
                    if ((((Tab) ej2Var.a.f) == null ? 0 : 1) != 0) {
                        ll llVar = ej2Var.c;
                        if (llVar != null) {
                            llVar.a();
                            ej2Var.c = null;
                        }
                        ej2Var.a(0);
                        break;
                    }
                }
                break;
            case 16:
            default:
                sw0 sw0Var = (sw0) obj2;
                Profile profile = (Profile) obj;
                IdentityManager identityManager = sw0Var.i;
                if (identityManager != null) {
                    identityManager.c(sw0Var);
                }
                if (!profile.h()) {
                    z7.m().getClass();
                    IdentityManager identityManagerV = z7.v(profile);
                    sw0Var.i = identityManagerV;
                    identityManagerV.b.a(sw0Var);
                    sw0Var.e(true);
                } else {
                    sw0Var.i = null;
                }
                break;
            case 17:
                ((hq1) obj2).b((ArrayList) obj);
                break;
            case 18:
                wa2 wa2Var = (wa2) obj2;
                int iIntValue2 = ((Integer) obj).intValue();
                if (iIntValue2 == 5) {
                    wa2Var.a(5, iIntValue2);
                }
                wa2Var.getClass();
                if (iIntValue2 == 1) {
                    i = 3;
                } else if (iIntValue2 != 2) {
                    i = 5;
                }
                oj2.U(i, 6, "Android.Download.Rename.Dialog.Action");
                break;
            case 19:
                a aVar = (a) obj2;
                int i7 = a.l;
                aVar.getClass();
                Iterator it = ((ArrayList) obj).iterator();
                int i8 = 0;
                while (true) {
                    boolean zHasNext = it.hasNext();
                    ArrayList arrayList = aVar.k;
                    if (!zHasNext) {
                        if (arrayList.isEmpty()) {
                            String strM4fixBWD = N.M4fixBWD();
                            while (true) {
                                if (i3 < aVar.getCount()) {
                                    ma0 ma0Var = (ma0) aVar.getItem(i3);
                                    if (ma0Var == null || !strM4fixBWD.equals(ma0Var.b)) {
                                        i3++;
                                    }
                                } else {
                                    i3 = -1;
                                }
                            }
                            aVar.e = i3;
                        }
                        aVar.notifyDataSetChanged();
                        a.InterfaceC0080a interfaceC0080a = aVar.h;
                        if (interfaceC0080a != null) {
                            interfaceC0080a.g();
                        }
                        break;
                    } else {
                        ma0 ma0Var2 = (ma0) ((ma0) it.next()).clone();
                        Context context = aVar.f;
                        int i9 = ma0Var2.e;
                        if (i9 == 0) {
                            ma0Var2.a = context.getString(R$string.menu_downloads);
                            aVar.i.add(ma0Var2);
                        } else if (i9 == 1) {
                            ma0Var2.a = i8 > 0 ? context.getString(R$string.downloads_location_sd_card_number, Integer.valueOf(i8 + 1)) : context.getString(R$string.downloads_location_sd_card);
                            aVar.j.add(ma0Var2);
                            i8++;
                        } else if (i9 == 2) {
                            ma0Var2.a = context.getString(R$string.download_location_no_available_locations);
                            arrayList.add(ma0Var2);
                        }
                    }
                }
                break;
            case 20:
                c cVar = (c) obj2;
                qb3 qb3Var = cVar.G.get();
                if (qb3Var != null) {
                    qb3Var.d(false);
                }
                FeedSwipeRefreshLayout feedSwipeRefreshLayout = cVar.H;
                if (feedSwipeRefreshLayout != null) {
                    feedSwipeRefreshLayout.setRefreshing(false);
                    break;
                }
                break;
            case 21:
                ((xa1) obj2).b.p(jo0.h, (Bitmap) obj);
                break;
            case 22:
                org.chromium.chrome.browser.feed.webfeed.b bVar2 = (org.chromium.chrome.browser.feed.webfeed.b) obj2;
                e.b bVar3 = (e.b) obj;
                if (bVar3 == null) {
                    bVar2.getClass();
                    break;
                } else {
                    e eVar = bVar2.e;
                    Tab tab4 = eVar.i.get();
                    if (tab4 == null || !tab4.getUrl().equals(bVar3.b)) {
                        objArr = false;
                    } else {
                        if (!eVar.d.a("webfeed_follow_intro_debug.enable")) {
                            switch (eVar.a.e) {
                            }
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            SharedPreferencesManager sharedPreferencesManager = eVar.e;
                            long jF = jCurrentTimeMillis - sharedPreferencesManager.f("Chrome.WebFeed.IntroLastShownTimeMs");
                            long jF2 = jCurrentTimeMillis - sharedPreferencesManager.f(ar.p.b(Base64.encodeToString(bVar3.a, 0)));
                            long jF3 = sharedPreferencesManager.f(ar.q.b(Base64.encodeToString(bVar3.a, 0)));
                            long j5 = eVar.l;
                            long j6 = e.m;
                            if (jF < j5 || jF2 < j6 || jF3 >= 3) {
                                Object[] objArr2 = new Object[3];
                                objArr2[0] = Boolean.valueOf(jF > j5);
                                objArr2[1] = Boolean.valueOf(jF2 > j6);
                                objArr2[2] = Boolean.valueOf(jF3 >= 3);
                                fa1.i("WFFollowIntroCtrl", "No intro: enoughTimeSinceLastShown=%s, enoughTimeSinceLastShownForWebFeed=%stooManyShows=%s", objArr2);
                                objArr = false;
                            }
                        }
                        objArr = true;
                    }
                    if (objArr != false) {
                        sk skVar = bq.a;
                        boolean zEquals = N.MMltG$kc("WebFeed", "intro_style").equals("IPH");
                        ar3 ar3Var = eVar.h;
                        Activity activity = eVar.b;
                        if (zEquals) {
                            pk3 pk3Var = new pk3(activity, new Handler());
                            a9 a9Var = new a9(eVar, 8, bVar3);
                            zc0 zc0Var = new zc0(i, eVar);
                            int i10 = R$string.follow_accelerator;
                            int i11 = R$string.accessibility_follow_accelerator_iph;
                            View view = ar3Var.d;
                            hw0 hw0Var = new hw0(view.getContext().getResources(), "IPH_WebFeedFollow", i10, i11);
                            hw0Var.j = view;
                            hw0Var.e = false;
                            hw0Var.o = ar3Var.h;
                            hw0Var.k = new a9(ar3Var, 9, a9Var);
                            hw0Var.l = zc0Var;
                            hw0Var.m = new zc0(5, ar3Var);
                            pk3Var.a(hw0Var.a());
                        } else if (!N.MMltG$kc("WebFeed", "intro_style").equals("accelerator")) {
                            Log.i("cr_WFFollowIntroCtrl", "No intro: not enabled by Finch controls");
                        } else {
                            ar3Var.c(new jg(i, new GestureDetector(activity.getApplicationContext(), new org.chromium.chrome.browser.feed.webfeed.c(eVar, bVar3))), new l91(eVar, 16, bVar3), new ql0(2, eVar));
                        }
                        break;
                    }
                }
                break;
            case 23:
                i.b bVar4 = (i.b) obj2;
                i.this.a(bVar4.e, (WebFeedBridge$FollowResults) obj, bVar4.i, bVar4.j, bVar4.k, bVar4.m);
                break;
            case 24:
                Map.Entry entry = (Map.Entry) obj;
                ((Bundle) obj2).putString((String) entry.getKey(), (String) entry.getValue());
                break;
            case 25:
                gn0 gn0Var = (gn0) obj2;
                gn0Var.c = Boolean.valueOf(((Boolean) obj).booleanValue());
                gn0Var.d();
                break;
            case 26:
                o22 o22Var = (o22) obj2;
                o22Var.i = Boolean.valueOf(((Boolean) obj).booleanValue());
                o22Var.a();
                break;
            case 27:
                iv0 iv0Var = (iv0) obj2;
                iv0Var.l = (Tab) obj;
                iv0Var.c();
                break;
            case 28:
                int i12 = org.chromium.chrome.browser.history.a.A;
                ((org.chromium.chrome.browser.history.a) obj2).S();
                break;
        }
    }
}
