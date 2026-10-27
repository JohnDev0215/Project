package defpackage;

import android.app.Dialog;
import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import defpackage.k81;
import java.util.HashMap;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.about_settings.PrivacyActivity;
import org.chromium.chrome.browser.app.bookmarks.BookmarkAddEditFolderActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkRow;
import org.chromium.chrome.browser.ntp.CardEditActivity;
import org.chromium.chrome.browser.omnibox.g;
import org.chromium.chrome.browser.privacy_guide.PrivacyGuideFragment;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.a0;
import org.chromium.chrome.browser.tasks.tab_management.m;
import org.chromium.chrome.browser.tasks.tab_management.t;
import org.chromium.chrome.browser.tasks.tab_management.u;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.components.browser_ui.modaldialog.TabModalPresenter;
import org.chromium.components.browser_ui.settings.LearnMorePreference;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;
import org.chromium.components.messages.MessageBannerView;
import org.chromium.components.messages.b;
import org.chromium.components.page_info.PageInfoContainer;
import org.chromium.components.page_info.PageInfoCookiesPreference;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gs implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ gs(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [T, java.lang.String] */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                n3 n3Var = ChromeTabbedActivity.S1;
                ((n63) obj).a();
                break;
            case 1:
                int i2 = PrivacyActivity.f53J;
                ((Dialog) obj).dismiss();
                break;
            case 2:
                BookmarkRow bookmarkRow = (BookmarkRow) obj;
                if (bookmarkRow.C != null) {
                    BookmarkItem bookmarkItemH = bookmarkRow.B.h().h(bookmarkRow.C);
                    if (view != bookmarkRow.y) {
                        if (view == bookmarkRow.z) {
                            boolean z = bookmarkItemH.d;
                            BookmarkId bookmarkId = bookmarkItemH.c;
                            if (!z) {
                                lh.f(bookmarkRow.getContext(), bookmarkId);
                            } else {
                                BookmarkAddEditFolderActivity.O1(bookmarkRow.getContext(), bookmarkId);
                            }
                        }
                        break;
                    } else if (bookmarkItemH != null && !bookmarkItemH.d) {
                        Intent intent = new Intent(bookmarkRow.getContext(), (Class<?>) CardEditActivity.class);
                        intent.putExtra("CardType", 2);
                        intent.putExtra("BookmarkEditActivity.BookmarkId", bookmarkItemH.c.toString());
                        bookmarkRow.getContext().startActivity(intent);
                        break;
                    }
                }
                break;
            case 3:
                vo1 vo1Var = (vo1) obj;
                vo1Var.e.a(vo1Var, true, 9);
                vo1Var.c(0, 0);
                break;
            case 4:
                ((View.OnClickListener) obj).onClick(view);
                break;
            case 5:
                g gVar = (g) obj;
                if (gVar.D) {
                    gVar.m.e(false, false);
                    n3.X0("MobileOmniboxVoiceSearch");
                    gVar.g.f(0);
                    break;
                }
                break;
            case 6:
                int i3 = PrivacyGuideFragment.n0;
                ((PrivacyGuideFragment) obj).H0();
                break;
            case 7:
                ((ra1) obj).d(true);
                break;
            case 8:
                m mVar = (m) obj;
                int i4 = mVar.v;
                y13 y13Var = mVar.g;
                Tab tabS = y13Var.s(i4);
                mVar.e(false);
                gz2 gz2Var = mVar.j;
                if (tabS != null) {
                    gz2Var.p0(tabS.isIncognito()).b(13, mVar.b(tabS.getId()).get(r0.size() - 1), new LoadUrlParams("chrome-native://newtab/", 0));
                    n3.X0("MobileNewTabOpened." + mVar.q);
                    if (!tabS.isIncognito()) {
                        zb2.g("Chrome.StartSurface.OpenNewTabPageCount");
                    }
                } else {
                    gz2Var.p0(y13Var.r()).f(2);
                }
                break;
            case 9:
                p4 p4Var = new sz2(view.getContext(), view, (Callback) obj).d;
                if (p4Var != null) {
                    p4Var.d();
                    break;
                }
                break;
            case 10:
                a0 a0Var = (a0) obj;
                Tab tabI = a0Var.i.i();
                if (tabI != null) {
                    List<Tab> listD = a0Var.d(tabI.getId());
                    t tVar = ((u) a0Var.h).x;
                    if (tVar != null) {
                        ((m.b) tVar.get()).N0(listD);
                    }
                    n3.X0("TabGroup.ExpandedFromStrip.TabGridDialog");
                    break;
                }
                break;
            case 11:
                k33 k33Var = (k33) obj;
                if (k33Var.f) {
                    k33Var.j.run();
                    break;
                }
                break;
            case 12:
                ((ToolbarTablet) obj).onClick(view);
                break;
            case 13:
                ((ov0) obj).b();
                break;
            case 14:
                jn2 jn2Var = (jn2) obj;
                if (jn2Var.l != null) {
                    y13 y13Var2 = jn2Var.f;
                    if (y13Var2.k() != -1) {
                        ds2.c(y13Var2.i());
                        if (jn2Var.n) {
                            n3.X0("MobileTabReturnedToCurrentTab.SingleTabCard");
                        }
                        jn2Var.l.t(y13Var2.k(), SystemClock.uptimeMillis());
                        n3.Z0(2, 2);
                    }
                }
                break;
            case 15:
                TabModalPresenter.this.d(5);
                break;
            case 16:
                LearnMorePreference learnMorePreference = (LearnMorePreference) obj;
                learnMorePreference.j.r(learnMorePreference);
                break;
            case 17:
                u73 u73Var = (u73) obj;
                u73Var.h.run();
                u73Var.k.run();
                break;
            case 18:
                final MessageBannerView messageBannerView = (MessageBannerView) obj;
                l81 pg1Var = messageBannerView.t;
                if (pg1Var == null && messageBannerView.r == null) {
                    Runnable runnable = messageBannerView.s;
                    if (runnable != null) {
                        runnable.run();
                    }
                } else {
                    ListMenuButton listMenuButton = messageBannerView.p;
                    if (pg1Var == null) {
                        HashMap mapE = PropertyModel.e(o81.k);
                        PropertyModel.r<String> rVar = o81.b;
                        ?? r2 = messageBannerView.r;
                        int i5 = zj.a;
                        PropertyModel.g gVar2 = new PropertyModel.g();
                        gVar2.a = r2;
                        mapE.put(rVar, gVar2);
                        PropertyModel.n nVar = o81.g;
                        PropertyModel.a aVar = new PropertyModel.a();
                        aVar.a = true;
                        mapE.put(nVar, aVar);
                        final PropertyModel propertyModel = new PropertyModel(mapE);
                        ya1 ya1Var = new ya1();
                        ya1Var.t(new xa1(1, propertyModel));
                        pg1Var = new pg1(new zd(messageBannerView.getContext(), ya1Var, new k81.a(propertyModel) { // from class: og1
                            @Override // k81.a
                            public final void a(PropertyModel propertyModel2) {
                                Runnable runnable2 = this.e.s;
                                if (runnable2 != null) {
                                    runnable2.run();
                                }
                            }
                        }));
                    }
                    listMenuButton.setDelegate(pg1Var);
                    ListMenuButton.a aVar2 = messageBannerView.x;
                    if (aVar2 != null) {
                        messageBannerView.p.m.a(aVar2);
                    }
                    messageBannerView.p.f();
                }
                break;
            case 19:
                b bVar = (b) obj;
                if (!bVar.i) {
                    PropertyModel.r<nw2<Integer>> rVar2 = ng1.d;
                    PropertyModel propertyModel2 = bVar.d;
                    if (((Integer) ((nw2) propertyModel2.j(rVar2)).get()).intValue() == 1) {
                        bVar.e.a(1, propertyModel2);
                    }
                    break;
                }
                break;
            case 20:
                int i6 = PageInfoContainer.l;
                ((PageInfoContainer.a) obj).h.run();
                break;
            default:
                ((PageInfoCookiesPreference) obj).l0.run();
                break;
        }
    }
}
