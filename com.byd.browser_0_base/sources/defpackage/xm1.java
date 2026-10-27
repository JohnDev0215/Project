package defpackage;

import J.N;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.View;
import defpackage.ax0;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.b;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$FollowResults;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedPageInformation;
import org.chromium.chrome.browser.feed.webfeed.e;
import org.chromium.chrome.browser.password_entry_edit.d;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.video_tutorials.Tutorial;
import org.chromium.components.browser_ui.widget.async_image.AsyncImageView;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xm1 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ xm1(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        int i2 = 2;
        int i3 = 4;
        int i4 = 1;
        Object obj2 = this.g;
        Object obj3 = this.f;
        switch (i) {
            case 0:
                ym1 ym1Var = (ym1) obj3;
                List<Tutorial> list = (List) obj2;
                ym1Var.getClass();
                if (((Boolean) obj).booleanValue()) {
                    for (final Tutorial tutorial : list) {
                        int i5 = tutorial.a;
                        String str = i5 != 1 ? i5 != 2 ? i5 != 3 ? i5 != 4 ? i5 != 5 ? null : "IPH_VideoTutorial_NTP_VoiceSearch" : "IPH_VideoTutorial_NTP_Search" : "IPH_VideoTutorial_NTP_Download" : "IPH_VideoTutorial_NTP_ChromeIntro" : "IPH_VideoTutorial_NTP_Summary";
                        if (str != null) {
                            xe3 xe3Var = ym1Var.b;
                            if (xe3Var.shouldTriggerHelpUI(str)) {
                                n3.a1(tutorial.a, 8);
                                final rl3 rl3Var = ym1Var.c;
                                PropertyModel propertyModel = rl3Var.b;
                                propertyModel.l(sl3.a, true);
                                propertyModel.p(sl3.b, tutorial.b);
                                PropertyModel.r<String> rVar = sl3.c;
                                int i6 = tutorial.i;
                                propertyModel.p(rVar, im3.a(i6));
                                propertyModel.l(sl3.d, i6 != 0);
                                propertyModel.p(sl3.f, new ul2(rl3Var, i2, tutorial));
                                propertyModel.p(sl3.g, new z8(rl3Var, 28, tutorial));
                                propertyModel.p(sl3.e, new AsyncImageView.a() { // from class: ql3
                                    @Override // org.chromium.components.browser_ui.widget.async_image.AsyncImageView.a
                                    public final Runnable a(int i7, int i8, gp gpVar) {
                                        rl3 rl3Var2 = rl3Var;
                                        rl3Var2.getClass();
                                        Tutorial tutorial2 = tutorial;
                                        boolean z = tutorial2.a == 1;
                                        String str2 = z ? "use_animated_gif_url_for_summary_card" : "use_animated_gif_url";
                                        sk skVar = bq.a;
                                        boolean zM6bsIDpc = N.M6bsIDpc("VideoTutorials", str2, !z);
                                        ax0.a aVarA = ax0.a.a(i7, i8, zM6bsIDpc ? tutorial2.e : tutorial2.f, "VideoTutorialsIPH");
                                        ax0 ax0Var = rl3Var2.c;
                                        if (zM6bsIDpc) {
                                            ax0Var.c(aVarA, new t02(gpVar, 9));
                                        } else {
                                            ax0Var.d(aVarA, new n62(rl3Var2, 21, gpVar));
                                        }
                                        return new gg3(13);
                                    }
                                });
                                xe3Var.dismissed(str);
                                break;
                            }
                        }
                    }
                    break;
                }
                break;
            case 1:
                ((Callback) obj3).onResult((String) obj2);
                break;
            case 2:
                m60 m60Var = (m60) obj3;
                Collection collection = (Collection) obj2;
                m60Var.getClass();
                if (((Boolean) obj).booleanValue()) {
                    Iterator it = collection.iterator();
                    while (it.hasNext()) {
                        m60Var.f.c(((OfflineItem) it.next()).e);
                    }
                } else {
                    k80 k80Var = m60Var.q;
                    k80Var.getClass();
                    Iterator it2 = collection.iterator();
                    while (it2.hasNext()) {
                        k80Var.h.remove(((OfflineItem) it2.next()).e);
                    }
                    k80Var.k();
                }
                break;
            case 3:
                b.e.a.a(new p62((xe3) obj3, i3, (View) obj2));
                break;
            case 4:
                lo0 lo0Var = (lo0) obj3;
                PropertyModel propertyModel2 = (PropertyModel) obj2;
                WebFeedBridge$FollowResults webFeedBridge$FollowResults = (WebFeedBridge$FollowResults) obj;
                lo0Var.getClass();
                lo0Var.a(webFeedBridge$FollowResults.a);
                propertyModel2.p(jo0.e, Boolean.valueOf(webFeedBridge$FollowResults.a == 1));
                propertyModel2.p(jo0.f, Boolean.TRUE);
                break;
            case 5:
                e.a aVar = (e.a) obj3;
                e.a.C0085a c0085a = (e.a.C0085a) obj2;
                tq3 tq3Var = (tq3) obj;
                aVar.getClass();
                int i7 = tq3Var.a;
                int i8 = aVar.b;
                int i9 = tq3Var.b;
                int i10 = aVar.a;
                if (!(i7 >= i10 && i9 >= i8)) {
                    fa1.i("WFFollowIntroCtrl", "No intro: visit requirement not met. totalVisits=%s (minToShow=%s),  dailyVisits=%s (minToShow=%s)", Integer.valueOf(i7), Integer.valueOf(i10), Integer.valueOf(i9), Integer.valueOf(i8));
                    aVar.b(c0085a, null);
                } else if (aVar.a(c0085a)) {
                    N.MSWj76M1(new WebFeedBridge$WebFeedPageInformation(c0085a.a, c0085a.b), 1, new n62(aVar, 7, c0085a));
                } else {
                    aVar.b(c0085a, null);
                }
                break;
            case 6:
                s91 s91Var = (s91) obj3;
                Callback callback = (Callback) obj2;
                Boolean bool = (Boolean) obj;
                s91Var.getClass();
                if (bool != null && bool.booleanValue()) {
                    s91Var.a = true;
                }
                if (callback != null) {
                    callback.onResult(bool);
                }
                break;
            case 7:
                d dVar = (d) obj3;
                Context context = (Context) obj2;
                dVar.getClass();
                if (((Boolean) obj).booleanValue()) {
                    oj2.U(4, 8, "PasswordManager.CredentialEntryActions.SavedPassword");
                    Clipboard.getInstance().g((String) dVar.e.j(org.chromium.chrome.browser.password_entry_edit.e.f));
                    Toast.c(context, R$string.password_entry_viewer_password_copied_into_clipboard, 0).e();
                    break;
                }
                break;
            case 8:
            default:
                bi1 bi1Var = (bi1) obj3;
                bi1Var.getClass();
                bi1Var.b(((Integer) obj).intValue(), (PropertyModel) obj2);
                break;
            case 9:
                Callback callback2 = (Callback) obj3;
                f83 f83Var = (f83) obj2;
                if (((String[]) obj).length > 0) {
                    callback2.onResult(Boolean.TRUE);
                } else {
                    callback2.onResult(Boolean.FALSE);
                }
                f83Var.close();
                break;
            case 10:
                Tab tab = (Tab) obj2;
                a81 a81Var = ((z71) obj3).f;
                if (a81Var.b.shouldTriggerHelpUI("IPH_SharedHighlightingReceiver")) {
                    vg1 vg1VarA = xg1.a(tab.D());
                    PropertyModel.b bVar = new PropertyModel.b(ng1.H);
                    bVar.c(ng1.a, 18);
                    bVar.e(ng1.l, re3.a(tab.getContext().getResources(), R$drawable.ink_highlighter, tab.getContext().getTheme()));
                    bVar.e(ng1.f, tab.getContext().getResources().getString(R$string.iph_message_shared_highlighting_title));
                    bVar.e(ng1.c, tab.getContext().getResources().getString(R$string.iph_message_shared_highlighting_button));
                    bVar.e(ng1.x, new td2(26, a81Var));
                    bVar.e(ng1.d, new hs(9, a81Var));
                    vg1VarA.b(bVar.a(), tab.c(), 2, false);
                }
                break;
            case 11:
                i20 i20Var = (i20) obj3;
                Callback callback3 = (Callback) obj2;
                Tab tab2 = (Tab) obj;
                Tab tab3 = i20Var.e;
                if (tab3 != tab2) {
                    n23 n23Var = i20Var.b;
                    if (tab3 != null) {
                        tab3.t(n23Var);
                    }
                    i20Var.e = tab2;
                    if (tab2 != null) {
                        tab2.x(n23Var);
                    }
                    if (callback3 != null) {
                        callback3.onResult(tab2);
                    }
                    break;
                }
                break;
            case 12:
                j0.k kVar = (j0.k) obj3;
                xl2 xl2Var = (xl2) obj;
                kVar.getClass();
                ((Callback) obj2).onResult(xl2Var);
                new Handler().post(new m03(kVar, i4, xl2Var));
                break;
            case 13:
                C0219x0 c0219x0 = (C0219x0) obj3;
                WindowAndroid.c cVar = (WindowAndroid.c) obj2;
                Intent intent = (Intent) obj;
                if (intent != null) {
                    c0219x0.e.u(intent, cVar, null);
                } else {
                    Activity activity = c0219x0.f;
                    Intent intent2 = new Intent("android.settings.SYNC_SETTINGS");
                    ComponentName componentName = q11.a;
                    try {
                        activity.startActivity(intent2, null);
                    } catch (ActivityNotFoundException unused) {
                        return;
                    }
                }
                break;
            case 14:
                ug3 ug3Var = (ug3) obj3;
                ug3Var.getClass();
                ((Callback) obj2).onResult(new BitmapDrawable(ug3Var.a.getResources(), (Bitmap) obj));
                break;
        }
    }
}
