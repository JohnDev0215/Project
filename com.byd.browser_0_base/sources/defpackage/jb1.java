package defpackage;

import J.N;
import android.animation.Animator;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout;
import defpackage.om3;
import defpackage.p3;
import defpackage.xl2;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.share.send_tab_to_self.ManageAccountDevicesLinkView;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.browser.toolbar.ToolbarProgressBar;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;
import org.chromium.chrome.browser.touch_to_fill.TouchToFillBridge;
import org.chromium.chrome.browser.touch_to_fill.data.WebAuthnCredential;
import org.chromium.chrome.browser.video_tutorials.Tutorial;
import org.chromium.components.browser_ui.widget.ClipDrawableProgressBar;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.identitymanager.a;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class jb1 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ jb1(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        vj vjVar;
        ToolbarProgressBar toolbarProgressBarI;
        switch (this.e) {
            case 0:
                ManageAccountDevicesLinkView manageAccountDevicesLinkView = (ManageAccountDevicesLinkView) this.f;
                int i = ManageAccountDevicesLinkView.f;
                manageAccountDevicesLinkView.getClass();
                Intent intentPutExtra = new Intent().setAction("android.intent.action.VIEW").setData(Uri.parse("https://myaccount.google.com/device-activity?utm_source=chrome")).setClass(manageAccountDevicesLinkView.getContext(), ChromeLauncherActivity.class).addFlags(268435456).putExtra("com.android.browser.application_id", manageAccountDevicesLinkView.getContext().getPackageName()).putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
                q11.a(intentPutExtra);
                manageAccountDevicesLinkView.getContext().startActivity(intentPutExtra);
                return;
            case 1:
                er erVar = (er) this.f;
                ComponentName componentName = er.p;
                erVar.j.notifyEvent("share_screenshot_clicked");
                oa1 oa1Var = new oa1(erVar.a, erVar.c.get(), erVar.i, erVar.h, erVar.d);
                ji jiVar = erVar.d;
                jiVar.g(oa1Var);
                jiVar.c(erVar.l, true);
                return;
            case 2:
                h62 h62Var = (h62) this.f;
                h62Var.getClass();
                AccountManagerFacadeProvider.getInstance().b().g(new gp(h62Var, 22, (a) obj));
                return;
            case 3:
                ft2 ft2Var = (ft2) this.f;
                ft2Var.f.g(null);
                ft2Var.j.onResult((Runnable) obj);
                return;
            case 4:
                wv2 wv2Var = (wv2) this.f;
                Tab tab = (Tab) obj;
                wv2Var.getClass();
                if (tab == null || tab.c() == null) {
                    return;
                }
                wv2Var.h.b(tab.c());
                return;
            case 5:
                xe3 xe3Var = (xe3) this.f;
                if (((Integer) obj).intValue() == 4) {
                    xe3Var.notifyEvent("desktop_site_opt_in_gesture");
                }
                xe3Var.dismissed("IPH_RequestDesktopSiteOptIn");
                return;
            case 6:
                l02 l02Var = (l02) this.f;
                HashMap map = l02.l;
                l02Var.getClass();
                if (((Boolean) obj).booleanValue()) {
                    l02Var.l();
                    l02Var.k = true;
                    return;
                } else {
                    if (l02Var.k) {
                        l02Var.a();
                        return;
                    }
                    return;
                }
            case 7:
                xl2.e eVar = (xl2.e) this.f;
                int i2 = xl2.t;
                eVar.b.onResult((xl2) obj);
                xl2.t();
                return;
            case 8:
                ly0 ly0Var = (ly0) this.f;
                n1 n1Var = (n1) obj;
                if (ly0Var.h == null) {
                    ly0Var.h = n1Var.m();
                    return;
                }
                return;
            case 9:
                ((h61) this.f).T((Runnable) obj);
                return;
            case 10:
                f23 f23Var = (f23) this.f;
                y13 y13Var = (y13) obj;
                f23Var.h = y13Var;
                y13Var.m(f23Var);
                return;
            case 11:
                d43 d43Var = (d43) this.f;
                a43 a43Var = (a43) obj;
                synchronized (d43Var.i) {
                    if (a43Var.b.equals(d43Var.j)) {
                        d43Var.m--;
                        d43Var.i.addAll(a43Var.a);
                        if (d43Var.m == 0) {
                            Iterator<b43> it = d43Var.l.iterator();
                            while (true) {
                                b.a aVar = (b.a) it;
                                if (aVar.hasNext()) {
                                    ((b43) aVar.next()).b(d43.a(d43Var.i), new dr(11, d43Var));
                                }
                            }
                        }
                    }
                    break;
                }
                return;
            case 12:
                g gVar = (g) this.f;
                BookmarkModel bookmarkModel = (BookmarkModel) obj;
                if (bookmarkModel == null) {
                    gVar.getClass();
                    return;
                } else {
                    bookmarkModel.c(gVar.H);
                    return;
                }
            case 13:
                m3 m3Var = (m3) this.f;
                p3.a aVar2 = (p3.a) obj;
                m3Var.getClass();
                int i3 = aVar2.a ? aVar2.b : 0;
                m3Var.r = i3;
                m3Var.n(i3);
                m3Var.m(aVar2.a);
                return;
            case 14:
                bi biVar = (bi) this.f;
                biVar.getClass();
                biVar.m = ((Boolean) obj).booleanValue();
                biVar.d();
                return;
            case 15:
                ToolbarPhone toolbarPhone = (ToolbarPhone) this.f;
                Integer num = (Integer) obj;
                toolbarPhone.E0 = true;
                if (toolbarPhone.isShown() && (vjVar = toolbarPhone.L) != null) {
                    toolbarPhone.M = vjVar.w(toolbarPhone.M);
                }
                int iIntValue = num.intValue();
                if (iIntValue != 1) {
                    if (iIntValue != 2) {
                        if (iIntValue != 3) {
                            if (iIntValue != 4) {
                                return;
                            }
                        }
                    }
                    toolbarPhone.U = true;
                    ao3.e(toolbarPhone, "ToolbarPhone.initializeOptionalButton.mOptionalButton.setTransitionStartedCallback");
                    return;
                }
                toolbarPhone.S = true;
                return;
            case 16:
                ClipDrawableProgressBar.a aVar3 = (ClipDrawableProgressBar.a) obj;
                qb3 qb3Var = ((ToolbarControlContainer) this.f).g;
                if (qb3Var == null || (toolbarProgressBarI = qb3Var.i()) == null) {
                    return;
                }
                int color = toolbarProgressBarI.e.getColor();
                float alpha = toolbarProgressBarI.getVisibility() == 0 ? toolbarProgressBarI.getAlpha() : 0.0f;
                aVar3.c = (color & 16777215) | (Math.round((color >>> 24) * alpha) << 24);
                aVar3.d = (toolbarProgressBarI.f & 16777215) | (Math.round(alpha * (r0 >>> 24)) << 24);
                WeakHashMap<View, in3> weakHashMap = om3.a;
                int iC = om3.e.c(toolbarProgressBarI);
                Rect rect = aVar3.b;
                Rect rect2 = aVar3.a;
                if (iC == 0) {
                    rect2.set(toolbarProgressBarI.getLeft(), toolbarProgressBarI.getTop(), Math.round(toolbarProgressBarI.g * toolbarProgressBarI.getWidth()) + toolbarProgressBarI.getLeft(), toolbarProgressBarI.getBottom());
                    rect.set(rect2.right, toolbarProgressBarI.getTop(), toolbarProgressBarI.getRight(), toolbarProgressBarI.getBottom());
                    return;
                } else {
                    rect2.set(toolbarProgressBarI.getRight() - Math.round(toolbarProgressBarI.g * toolbarProgressBarI.getWidth()), toolbarProgressBarI.getTop(), toolbarProgressBarI.getRight(), toolbarProgressBarI.getBottom());
                    rect.set(toolbarProgressBarI.getLeft(), toolbarProgressBarI.getTop(), rect2.left, toolbarProgressBarI.getBottom());
                    return;
                }
            case 17:
                td3 td3Var = (td3) this.f;
                Tab tab2 = (Tab) obj;
                if (tab2 == null) {
                    td3Var.getClass();
                    return;
                }
                td3Var.d();
                td3Var.c(tab2);
                td3Var.a();
                return;
            case 18:
                ke3 ke3Var = (ke3) this.f;
                WebAuthnCredential webAuthnCredential = (WebAuthnCredential) obj;
                ke3Var.c.l(le3.a, false);
                int iIndexOf = ke3Var.f.indexOf(webAuthnCredential) + ke3Var.g.size();
                if (ke3Var.f.size() + ke3Var.g.size() > 1) {
                    oj2.Q(iIndexOf, "PasswordManager.TouchToFill.CredentialIndex");
                }
                oj2.U(3, 4, "PasswordManager.TouchToFill.UserAction");
                long j = ((TouchToFillBridge) ke3Var.b).a;
                if (j != 0) {
                    N.M98beDi1(j, webAuthnCredential);
                    return;
                }
                return;
            case 19:
                te3 te3Var = (te3) this.f;
                te3Var.getClass();
                te3Var.d = new HashSet(Arrays.asList((String[]) obj));
                te3Var.b(1);
                return;
            case 20:
                ui uiVar = (ui) this.f;
                l61 l61Var = (l61) obj;
                if (uiVar.l) {
                    ti tiVar = new ti(uiVar);
                    uiVar.n = tiVar;
                    l61Var.j(tiVar);
                    int iD = l61Var.d();
                    if (iD != 0) {
                        uiVar.n.h(iD);
                        return;
                    }
                    return;
                }
                return;
            case 21:
                ((g3) this.f).v((Animator) obj);
                return;
            case 22:
                be beVar = (be) this.f;
                Rect rect3 = (Rect) obj;
                beVar.getClass();
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                int i4 = rect3.left;
                layoutParams.setMargins(i4, rect3.top, i4, rect3.bottom);
                beVar.i.setLayoutParams(layoutParams);
                return;
            case 23:
                C0219x0 c0219x0 = (C0219x0) this.f;
                c0219x0.getClass();
                if (((Boolean) obj).booleanValue()) {
                    c0219x0.i.n(C0221y0.e, 1);
                    return;
                }
                return;
            case 24:
                f1 f1Var = (f1) this.f;
                f1Var.getClass();
                f1Var.f.l(((fb0) obj).a);
                return;
            case 25:
                ((km2) this.f).c((List) obj);
                return;
            case 26:
                ((ah0) this.f).h = (z51) obj;
                return;
            case 27:
                zu1 zu1Var = (zu1) this.f;
                Tab tab3 = (Tab) obj;
                zu1Var.j = tab3;
                if (tab3 == null) {
                    zu1Var.z1(null);
                    return;
                }
                if (tab3.isIncognito()) {
                    zu1Var.z1(null);
                    zu1Var.j.t(zu1Var);
                    return;
                } else {
                    if (zu1Var.j.isHidden()) {
                        return;
                    }
                    zu1Var.z1(zu1Var.j.getUrl());
                    return;
                }
            case 28:
                fk3 fk3Var = (fk3) this.f;
                fk3Var.getClass();
                for (String str : (List) obj) {
                    Object obj2 = ThreadUtils.a;
                    ob3 ob3Var = fk3Var.e;
                    ob3Var.getClass();
                    ob3Var.a.h(new mc((Object) ob3Var, 7, (Object) str, (Object) new s62()), new ps(20));
                }
                return;
            default:
                ((am3) this.f).e((Tutorial) obj);
                return;
        }
    }
}
