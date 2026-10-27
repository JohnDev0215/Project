package defpackage;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import defpackage.ax0;
import defpackage.fc;
import defpackage.fx0;
import defpackage.jk2;
import defpackage.lr2;
import defpackage.o10;
import defpackage.xb;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.autofill.settings.AutofillServerCardEditor;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataCheckBoxPreference;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.b;
import org.chromium.chrome.browser.contextmenu.ContextMenuHelper;
import org.chromium.chrome.browser.creator.CreatorCoordinator;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.download.e;
import org.chromium.chrome.browser.download.home.filter.a;
import org.chromium.chrome.browser.feed.FeedStream;
import org.chromium.chrome.browser.feed.sort_ui.FeedOptionsCoordinator;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$FollowResults;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedMetadata;
import org.chromium.chrome.browser.feed.webfeed.WebFeedMainMenuItem;
import org.chromium.chrome.browser.feed.webfeed.i;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.content_creation.notes.models.Background;
import org.chromium.components.content_creation.notes.models.NoteTemplate;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wb implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ wb(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [lr2$b, n7] */
    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        Drawable drawable;
        int i = 4;
        int i2 = 3;
        int i3 = this.e;
        int i4 = 0;
        int i5 = 1;
        Object obj2 = this.f;
        switch (i3) {
            case 0:
                ((xb.a) obj2).b(((Boolean) obj).booleanValue());
                return;
            case 1:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                pr prVar = ((ChromeTabbedActivity) obj2).l1.q;
                if (prVar == null || prVar.f == null) {
                    return;
                }
                prVar.m(!zBooleanValue);
                return;
            case 2:
                ((ChromeActivity) obj2).S0.a((h61) obj, 3);
                return;
            case 3:
                final o7 o7Var = (o7) obj2;
                lr2 lr2Var = (lr2) obj;
                o7Var.getClass();
                o7Var.r = lr2Var.t();
                ?? r2 = new lr2.b() { // from class: n7
                    @Override // lr2.b
                    public final void a(int i6, boolean z) {
                        o7Var.r = i6;
                    }
                };
                o7Var.q = r2;
                lr2Var.q(r2);
                return;
            case 4:
                Integer num = (Integer) obj;
                Tab tab = ((f3) obj2).b;
                if (tab.isInitialized()) {
                    int iIntValue = num.intValue();
                    if (iIntValue == 1) {
                        tab.c().m().x();
                        return;
                    } else {
                        if (iIntValue == 7 || iIntValue == 8) {
                            return;
                        }
                        tab.c().m().v();
                        return;
                    }
                }
                return;
            case 5:
                ym1 ym1Var = (ym1) obj2;
                List list = (List) obj;
                ym1Var.getClass();
                if (list.isEmpty()) {
                    return;
                }
                ym1Var.d.b(new gp(ym1Var, i5, new ArrayList(list)), 1);
                return;
            case 6:
                sq3 sq3Var = (sq3) obj2;
                sq3Var.g((Integer) obj, (fj) sq3.c(sq3Var.h));
                return;
            case 7:
                AutofillServerCardEditor autofillServerCardEditor = (AutofillServerCardEditor) obj2;
                int i6 = AutofillServerCardEditor.n0;
                autofillServerCardEditor.getClass();
                if (((Integer) obj).intValue() != 1) {
                    oj2.O("Autofill.VirtualCard.SettingsPageEnrollment", false);
                    autofillServerCardEditor.h0.setEnabled(true);
                    return;
                }
                oj2.O("Autofill.VirtualCard.SettingsPageEnrollment", true);
                ab abVar = autofillServerCardEditor.j0;
                ib ibVar = autofillServerCardEditor.m0;
                long j = abVar.a;
                if (j == 0) {
                    throw new IllegalStateException("The native delegate was cleaned up or not initialized.");
                }
                N.MTA0Nx58(j, ibVar);
                autofillServerCardEditor.k0 = true;
                return;
            case 8:
                nb nbVar = (nb) obj2;
                Integer num2 = (Integer) obj;
                nbVar.getClass();
                oj2.O("Autofill.VirtualCard.SettingsPageUnenrollment", num2.intValue() == 1);
                nbVar.b.onResult(Boolean.valueOf(num2.intValue() == 1));
                return;
            case 9:
                Runnable runnable = ((ClearBrowsingDataCheckBoxPreference) obj2).Z;
                if (runnable != null) {
                    runnable.run();
                    return;
                }
                return;
            case 10:
                Drawable drawable2 = (Drawable) obj;
                zh0 zh0Var = ((b) obj2).h.f;
                if (zh0Var != null) {
                    if (drawable2 == null) {
                        zh0Var.q = null;
                        zh0Var.r.setImageDrawable(null);
                        return;
                    }
                    Drawable drawable3 = zh0Var.q;
                    if (drawable3 == null || (drawable3 instanceof us)) {
                        drawable = drawable2;
                    } else {
                        us usVar = new us(zh0Var.q, drawable2);
                        usVar.i = true;
                        usVar.f.setAlpha(255 - usVar.j);
                        usVar.b().a.setDuration(218L);
                        drawable = usVar;
                    }
                    zh0Var.r.setImageDrawable(drawable);
                    zh0Var.q = drawable2;
                    return;
                }
                return;
            case 11:
                eo1 eo1Var = (eo1) obj2;
                List<NoteTemplate> list2 = (List) obj;
                eo1Var.getClass();
                ArrayList<Background> arrayList = new ArrayList();
                for (NoteTemplate noteTemplate : list2) {
                    arrayList.add(noteTemplate.c);
                    Background background = noteTemplate.f;
                    if (background != null) {
                        arrayList.add(background);
                    }
                }
                l91 l91Var = new l91(eo1Var, 9, list2);
                fx0 fx0Var = eo1Var.d;
                fx0Var.getClass();
                HashSet<tw0> hashSet = new HashSet();
                for (Background background2 : arrayList) {
                    if (background2 instanceof tw0) {
                        hashSet.add((tw0) background2);
                    }
                }
                if (hashSet.isEmpty()) {
                    l91Var.run();
                    return;
                }
                fx0.a aVar = new fx0.a(hashSet.size());
                for (tw0 tw0Var : hashSet) {
                    fx0Var.a.d(ax0.a.b(tw0Var.a, "WebNotes"), new yo1(tw0Var, i5, aVar, l91Var));
                }
                return;
            case 12:
                aq aqVar = (aq) obj2;
                hk2.h(aqVar.h(), aqVar.g(), hk2.d(), (Uri) obj, null);
                return;
            case 13:
                ContextMenuHelper contextMenuHelper = (ContextMenuHelper) obj2;
                Integer num3 = (Integer) obj;
                vz vzVar = contextMenuHelper.d;
                if (vzVar == null) {
                    return;
                }
                contextMenuHelper.m = true;
                vzVar.b(num3.intValue());
                return;
            case 14:
                CreatorCoordinator creatorCoordinator = (CreatorCoordinator) obj2;
                WebFeedBridge$WebFeedMetadata webFeedBridge$WebFeedMetadata = (WebFeedBridge$WebFeedMetadata) obj;
                if (webFeedBridge$WebFeedMetadata != null) {
                    creatorCoordinator.getClass();
                    i4 = webFeedBridge$WebFeedMetadata.d;
                }
                PropertyModel propertyModel = creatorCoordinator.j;
                if (i4 == 0 || i4 == 2) {
                    propertyModel.p(n10.e, Boolean.FALSE);
                } else if (i4 == 1) {
                    propertyModel.p(n10.e, Boolean.TRUE);
                }
                PropertyModel.r<String> rVar = n10.b;
                if (TextUtils.isEmpty((CharSequence) propertyModel.j(rVar)) && TextUtils.isEmpty(webFeedBridge$WebFeedMetadata.b)) {
                    propertyModel.p(rVar, webFeedBridge$WebFeedMetadata.b);
                }
                PropertyModel.r<String> rVar2 = n10.c;
                if (TextUtils.isEmpty((CharSequence) propertyModel.j(rVar2))) {
                    GURL gurl = webFeedBridge$WebFeedMetadata.c;
                    if (gurl.b) {
                        propertyModel.p(rVar2, gurl.j());
                        propertyModel.p(n10.d, N.MpICpYBr(webFeedBridge$WebFeedMetadata.c));
                        return;
                    }
                    return;
                }
                return;
            case 15:
                m10 m10Var = (m10) obj2;
                WebFeedBridge$FollowResults webFeedBridge$FollowResults = (WebFeedBridge$FollowResults) obj;
                m10Var.getClass();
                int i7 = webFeedBridge$FollowResults.a;
                PropertyModel propertyModel2 = m10Var.a;
                if (i7 == 1) {
                    propertyModel2.p(n10.e, Boolean.TRUE);
                }
                String str = (String) propertyModel2.j(n10.b);
                o10 o10Var = m10Var.b;
                o10.a aVar2 = o10Var.c;
                int i8 = webFeedBridge$FollowResults.a;
                Context context = o10Var.a;
                tp2 tp2VarA = i8 == 1 ? tp2.a(context.getString(R$string.cormorant_creator_follow_success_snackbar, str), aVar2, 0, 56) : i8 == 2 ? tp2.a(context.getString(R$string.cormorant_creator_offline_failure_snackbar), aVar2, 0, 57) : tp2.a(context.getString(R$string.cormorant_creator_follow_failure_snackbar), aVar2, 0, 57);
                tp2VarA.j = 8000;
                tp2VarA.d = context.getString(R$string.dismiss);
                tp2VarA.e = null;
                tp2VarA.i = false;
                o10Var.b.c(tp2VarA);
                return;
            case 16:
                d30 d30Var = (d30) obj2;
                if (d30Var.m == null) {
                    return;
                }
                Integer num4 = d30Var.h.get();
                if (!(num4 != null && num4.intValue() > 0)) {
                    Integer num5 = d30Var.f.m.get();
                    if (!(num5 != null && num5.intValue() > 0)) {
                        i5 = 0;
                    }
                }
                jj jjVar = d30Var.g;
                if (i5 != 0) {
                    d30Var.b().setVisibility(8);
                    jjVar.k(0, 0);
                    return;
                } else {
                    d30Var.b().setVisibility(0);
                    jjVar.k(d30Var.a(), 0);
                    return;
                }
            case 17:
                b40 b40Var = (b40) obj2;
                Tab tab2 = (Tab) obj;
                CustomTabsConnection customTabsConnection = b40Var.e;
                if (customTabsConnection == null || !customTabsConnection.t(b40Var.f) || tab2.c() == null) {
                    return;
                }
                String title = tab2.getTitle();
                if (TextUtils.isEmpty(title)) {
                    return;
                }
                String strJ = tab2.getUrl().j();
                WebContents webContentsC = tab2.c();
                int i9 = b40Var.i;
                int i10 = b40Var.j;
                yo1 yo1Var = new yo1(b40Var, strJ, title);
                RenderWidgetHostViewImpl renderWidgetHostViewImplB0 = webContentsC.b0();
                if (renderWidgetHostViewImplB0 == null) {
                    yo1Var.onResult(null);
                    return;
                }
                try {
                    String str2 = uh3.c(xz.a) + File.separator + "screenshot";
                    jk2.c cVar = new jk2.c(yo1Var);
                    if (renderWidgetHostViewImplB0.a == 0) {
                        cVar.onResult("RWHVA already destroyed!");
                    }
                    N.Mjmv7RJS(renderWidgetHostViewImplB0.a, renderWidgetHostViewImplB0, i9, i10, str2, cVar);
                    return;
                } catch (IOException e) {
                    Log.e("cr_share", "Error getting content bitmap: ", e);
                    yo1Var.onResult(null);
                    return;
                }
            case 18:
                x20 x20Var = (x20) obj2;
                x20Var.getClass();
                jy2.d((Tab) obj).f = x20Var.g.d(x20Var.y);
                return;
            case 19:
                ((rs1) obj2).a((Integer) obj);
                return;
            case 20:
                e eVar = (e) obj2;
                eVar.p = null;
                if (((Integer) obj).intValue() == 4) {
                    oj2.U(eVar.l, 4, "Download.Progress.InfoBar.CloseButtonClicked");
                    eVar.c(null, false, true, false);
                    return;
                }
                return;
            case 21:
                a aVar3 = (a) obj2;
                aVar3.getClass();
                aVar3.g(((PropertyModel) obj).h(ho.f));
                org.chromium.chrome.browser.download.home.filter.b bVar = (org.chromium.chrome.browser.download.home.filter.b) ((gx) aVar3.f).f;
                bVar.b(bVar.b.h(sm0.b));
                return;
            case 22:
                wa2 wa2Var = (wa2) obj2;
                if (((Boolean) obj).booleanValue()) {
                    wa2Var.f.r(wa2Var.d, new va2(wa2Var, i5));
                    return;
                } else {
                    wa2Var.a(5, 2);
                    return;
                }
            case 23:
                ((fc.a) obj2).b(((Boolean) obj).booleanValue());
                return;
            case 24:
                ((FeedStream) obj2).o = false;
                return;
            case 25:
                lo0 lo0Var = (lo0) obj2;
                List<WebFeedBridge$WebFeedMetadata> list3 = (List) obj;
                Context context2 = lo0Var.c;
                String string = context2.getResources().getString(R$string.follow_manage_updates_unavailable);
                String string2 = context2.getResources().getString(R$string.follow_manage_waiting_for_content);
                ya1 ya1Var = lo0Var.a;
                ya1Var.clear();
                for (WebFeedBridge$WebFeedMetadata webFeedBridge$WebFeedMetadata2 : list3) {
                    Objects.toString(webFeedBridge$WebFeedMetadata2.c);
                    int i11 = webFeedBridge$WebFeedMetadata2.e;
                    String str3 = i11 == i ? string2 : i11 == 1 ? string : "";
                    int i12 = webFeedBridge$WebFeedMetadata2.d;
                    boolean z = i12 == 1 || i12 == i2;
                    String strJ2 = webFeedBridge$WebFeedMetadata2.c.j();
                    PropertyModel.b bVar2 = new PropertyModel.b(jo0.i);
                    bVar2.e(jo0.g, webFeedBridge$WebFeedMetadata2.a);
                    bVar2.e(jo0.a, webFeedBridge$WebFeedMetadata2.b);
                    bVar2.e(jo0.b, strJ2);
                    bVar2.e(jo0.c, str3);
                    bVar2.e(jo0.e, Boolean.valueOf(z));
                    bVar2.e(jo0.f, Boolean.TRUE);
                    PropertyModel propertyModelA = bVar2.a();
                    propertyModelA.p(jo0.d, new z8(lo0Var, 15, propertyModelA));
                    xa1 xa1Var = new xa1(0, propertyModelA);
                    ya1Var.t(xa1Var);
                    lo0Var.d.a(context2.getResources().getDimensionPixelSize(R$dimen.web_feed_management_icon_size), context2.getResources().getDimensionPixelSize(R$dimen.web_feed_monogram_text_size), webFeedBridge$WebFeedMetadata2.c, webFeedBridge$WebFeedMetadata2.g, new c3(21, xa1Var));
                    i = 4;
                    i2 = 3;
                }
                if (list3.isEmpty()) {
                    ya1Var.t(new xa1(1, new PropertyModel(new v62[0])));
                    return;
                }
                return;
            case 26:
                FeedOptionsCoordinator feedOptionsCoordinator = (FeedOptionsCoordinator) obj2;
                PropertyModel propertyModel3 = (PropertyModel) obj;
                for (PropertyModel propertyModel4 : feedOptionsCoordinator.d) {
                    PropertyModel.n nVar = ho.g;
                    if (propertyModel4.k(nVar)) {
                        propertyModel4.l(nVar, false);
                    }
                }
                propertyModel3.l(ho.g, true);
                PropertyModel.j jVar = ho.f;
                N.Mn5$2mvw(propertyModel3.h(jVar));
                FeedOptionsCoordinator.a aVar4 = feedOptionsCoordinator.f;
                if (aVar4 != null) {
                    ((org.chromium.chrome.browser.feed.e) aVar4).o(false);
                }
                int iH = propertyModel3.h(jVar);
                N.MxULk9PS(2, iH != 1 ? iH != 2 ? 65 : 60 : 59);
                return;
            case 27:
                ((org.chromium.chrome.browser.feed.webfeed.e) obj2).k.d = null;
                return;
            case 28:
                WebFeedMainMenuItem webFeedMainMenuItem = (WebFeedMainMenuItem) obj2;
                Bitmap bitmap = (Bitmap) obj;
                webFeedMainMenuItem.n.setImageBitmap(bitmap);
                if (bitmap == null) {
                    webFeedMainMenuItem.n.setVisibility(8);
                }
                sk skVar = bq.a;
                if (N.M09VlOh_("Cormorant")) {
                    webFeedMainMenuItem.n.setOnClickListener(new br3(webFeedMainMenuItem, i5));
                    return;
                }
                return;
            default:
                i.b bVar3 = (i.b) obj2;
                WebFeedBridge$FollowResults webFeedBridge$FollowResults2 = (WebFeedBridge$FollowResults) obj;
                bVar3.getClass();
                WebFeedBridge$WebFeedMetadata webFeedBridge$WebFeedMetadata3 = webFeedBridge$FollowResults2.b;
                i.this.a(bVar3.e, webFeedBridge$FollowResults2, webFeedBridge$WebFeedMetadata3 != null ? webFeedBridge$WebFeedMetadata3.a : null, bVar3.j, bVar3.k, bVar3.m);
                return;
        }
    }
}
