package defpackage;

import J.N;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import defpackage.kj3;
import defpackage.lk1;
import defpackage.nj3;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.ntp.LegacyIncognitoDescriptionView;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.omnibox.g;
import org.chromium.chrome.browser.omnibox.geo.GeolocationHeader;
import org.chromium.chrome.browser.omnibox.h;
import org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController;
import org.chromium.chrome.browser.omnibox.suggestions.c;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.chrome.browser.page_info.AboutThisSiteView;
import org.chromium.chrome.browser.password_entry_edit.e;
import org.chromium.chrome.browser.password_manager.CredentialLeakDialogBridge;
import org.chromium.chrome.browser.payments.ui.j;
import org.chromium.chrome.browser.privacy.settings.PrivacySettings;
import org.chromium.chrome.browser.privacy_sandbox.v4.FledgeFragmentV4;
import org.chromium.chrome.browser.privacy_sandbox.v4.FledgeLearnMoreFragment;
import org.chromium.chrome.browser.privacy_sandbox.v4.TopicsFragmentV4;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.chrome.browser.safe_browsing.SafeBrowsingPasswordReuseDialogBridge;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.signin.SigninFirstRunFragment;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.browser_ui.styles.R$attr;
import org.chromium.components.browser_ui.widget.RoundedCornerImageView;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.components.payments.e;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class lj1 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ lj1(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) throws Exception {
        int i = this.e;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                nj1 nj1Var = (nj1) obj2;
                b11 b11Var = (b11) obj;
                nj1Var.getClass();
                int i2 = b11Var.a;
                n3.X0("Android.WindowManager.SelectWindow");
                Activity activity = nj1Var.g;
                int i3 = b11Var.b;
                if (i3 == -1) {
                    kj1.s = 0;
                    activity.startActivity(vj1.c(activity, i2, false, true), vj1.j(((uj1) nj1Var.i).a));
                } else {
                    ((ActivityManager) activity.getSystemService("activity")).moveTaskToFront(i3, 0);
                }
                break;
            case 1:
                ((LegacyIncognitoDescriptionView) obj2).k.callOnClick();
                break;
            case 2:
                ((lk1.b) ((lw2) ((NewTabPageLayout) obj2).m).c).b((LoadUrlParams) obj, false);
                break;
            case 3:
                ((View.OnClickListener) obj2).onClick((View) obj);
                break;
            case 4:
                hj3 hj3Var = (hj3) obj2;
                hj3Var.c = (nj3.c) obj;
                hj3Var.b();
                Iterator<Runnable> it = hj3Var.a.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (aVar.hasNext()) {
                        ((Runnable) aVar.next()).run();
                    }
                    break;
                }
                break;
            case 5:
                oj3 oj3Var = (oj3) obj2;
                kj3 kj3Var = (kj3) obj;
                oj3Var.getClass();
                if (kj3Var != null) {
                    boolean z = System.currentTimeMillis() - kj3Var.X() > 172800000;
                    boolean z2 = !TextUtils.equals(kj3Var.Z(), "4.1.1.0");
                    if (!kj3Var.V()) {
                        oj2.O(z7.b(0, null), z2);
                        oj2.O(z7.b(0, kj3Var), z2);
                    }
                    if (z2 || z) {
                        oj2.O(z7.b(1, null), z2);
                        oj2.O(z7.b(1, kj3Var), z2);
                    }
                    ye3 ye3Var = oj3Var.a;
                    if (z2 || z) {
                        ye3Var.a.b(new wz(18));
                    } else if (!kj3Var.V()) {
                        kj3.a aVarH = kj3Var.h();
                        aVarH.y();
                        kj3.O((kj3) aVarH.f, true);
                        kj3 kj3VarBuild = aVarH.build();
                        ye3Var.getClass();
                        ye3Var.a.b(new l10(27, kj3VarBuild));
                    }
                    break;
                }
                break;
            case 6:
                g gVar = (g) obj2;
                TemplateUrlService templateUrlService = (TemplateUrlService) obj;
                gVar.getClass();
                if (templateUrlService.e()) {
                    GeolocationHeader.f(gVar.n.get(), templateUrlService);
                }
                break;
            case 7:
                h hVar = (h) obj2;
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                InputMethodManager inputMethodManager = (InputMethodManager) hVar.f.getContext().getSystemService("input_method");
                if (zBooleanValue) {
                    inputMethodManager.viewClicked(hVar.f);
                    hVar.f.setCursorVisible(true);
                } else if (inputMethodManager.isActive(hVar.f)) {
                    hVar.e(false, false);
                }
                hVar.n.onResult(Boolean.valueOf(zBooleanValue));
                hVar.d(zBooleanValue);
                break;
            case 8:
                Profile profile = (Profile) obj;
                d dVar = ((c) obj2).h;
                if (dVar.w != null) {
                    dVar.n(true);
                    dVar.w.removeOnSuggestionsReceivedListener(dVar);
                }
                AutocompleteController autocompleteControllerB = dVar.f.b(profile);
                dVar.w = autocompleteControllerB;
                autocompleteControllerB.addOnSuggestionsReceivedListener(dVar);
                ge0 ge0Var = dVar.m;
                LargeIconBridge largeIconBridge = ge0Var.i;
                if (largeIconBridge != null) {
                    largeIconBridge.a();
                    ge0Var.i = null;
                }
                ax0 ax0Var = ge0Var.g;
                if (ax0Var != null) {
                    ax0Var.b();
                    ge0Var.g = null;
                }
                mk0 mk0Var = ge0Var.h;
                if (mk0Var != null) {
                    mk0Var.a.evictAll();
                }
                ge0Var.i = new LargeIconBridge(profile);
                ProfileKey profileKeyG = profile.g();
                na0 na0Var = ys0.a;
                Object obj3 = ThreadUtils.a;
                ge0Var.g = dx0.a(2, new cx0(profileKeyG), na0Var, 512000);
                dVar.l();
                break;
            case 9:
                yj1 yj1Var = so3.j;
                ((so3) obj2).b();
                break;
            case 10:
                ((AboutThisSiteView) obj2).onClick((View) obj);
                break;
            case 11:
                ((org.chromium.chrome.browser.password_edit_dialog.c) obj2).e.n(fx1.c, ((Integer) obj).intValue());
                break;
            case 12:
                org.chromium.chrome.browser.password_entry_edit.d dVar2 = (org.chromium.chrome.browser.password_entry_edit.d) obj2;
                dVar2.getClass();
                if (((Boolean) obj).booleanValue()) {
                    oj2.U(2, 8, "PasswordManager.CredentialEntryActions.SavedPassword");
                    dVar2.e.l(e.e, true);
                    break;
                }
                break;
            case 13:
                CredentialLeakDialogBridge credentialLeakDialogBridge = (CredentialLeakDialogBridge) obj2;
                int iIntValue = ((Integer) obj).intValue();
                long j = credentialLeakDialogBridge.a;
                if (j != 0) {
                    if (iIntValue == 1) {
                        N.Mmumo5h_(j, credentialLeakDialogBridge);
                    } else if (iIntValue == 2) {
                        N.M2h75In5(j, credentialLeakDialogBridge);
                    } else {
                        N.MEu0f3Ks(j, credentialLeakDialogBridge);
                    }
                    break;
                }
                break;
            case 14:
                yq yqVar = (yq) ((j) ((org.chromium.chrome.browser.payments.ui.e) obj2).g).o;
                yqVar.c.getClass();
                Context contextA = e.a.a(yqVar.b);
                if (contextA == null) {
                    yqVar.e.a(8);
                    yqVar.b("Unable to find Chrome context.");
                } else {
                    Intent intent = new Intent();
                    intent.setClass(contextA, SettingsActivity.class);
                    if (!(contextA instanceof Activity)) {
                        intent.addFlags(268435456);
                        intent.addFlags(67108864);
                    }
                    ComponentName componentName = q11.a;
                    try {
                        contextA.startActivity(intent, null);
                    } catch (ActivityNotFoundException unused) {
                        return;
                    }
                }
                break;
            case 15:
                PrivacySettings privacySettings = (PrivacySettings) obj2;
                int i4 = PrivacySettings.i0;
                privacySettings.getClass();
                if (((Boolean) obj).booleanValue()) {
                    privacySettings.M0();
                }
                break;
            case 16:
                FledgeFragmentV4 fledgeFragmentV4 = (FledgeFragmentV4) obj2;
                int i5 = FledgeFragmentV4.u0;
                fledgeFragmentV4.getClass();
                n3.X0("Settings.PrivacySandbox.Fledge.LearnMoreClicked");
                SettingsLauncher settingsLauncher = fledgeFragmentV4.i0;
                if (settingsLauncher != null) {
                    settingsLauncher.g(fledgeFragmentV4.K(), FledgeLearnMoreFragment.class);
                }
                break;
            case 17:
                TopicsFragmentV4 topicsFragmentV4 = (TopicsFragmentV4) obj2;
                int i6 = TopicsFragmentV4.r0;
                SettingsLauncher settingsLauncher2 = topicsFragmentV4.i0;
                if (settingsLauncher2 != null) {
                    settingsLauncher2.g(topicsFragmentV4.K(), FledgeFragmentV4.class);
                }
                break;
            case 18:
                SafeBrowsingPasswordReuseDialogBridge safeBrowsingPasswordReuseDialogBridge = (SafeBrowsingPasswordReuseDialogBridge) obj2;
                ((Integer) obj).intValue();
                long j2 = safeBrowsingPasswordReuseDialogBridge.a;
                if (j2 != 0) {
                    N.M_X8ygDO(j2, safeBrowsingPasswordReuseDialogBridge);
                    break;
                }
                break;
            case 19:
                a00 a00Var = (a00) obj2;
                Tab tab = (Tab) obj;
                a00Var.getClass();
                if (tab != null && !tab.k() && !tab.isFrozen()) {
                    a00Var.b();
                    break;
                }
                break;
            case 20:
                gm2 gm2Var = (gm2) obj2;
                gm2Var.a = Boolean.valueOf(!((Boolean) obj).booleanValue());
                gm2Var.a();
                break;
            case 21:
                ud2 ud2Var = (ud2) obj2;
                boolean zBooleanValue2 = ((Boolean) obj).booleanValue();
                ud2Var.getClass();
                if (zBooleanValue2) {
                    N.MTm9IWhH(ud2Var.a.getString(ud2Var.c, DateFormat.getDateTimeInstance(2, 1).format(new Date(System.currentTimeMillis()))), ud2Var.b);
                    Runnable runnable = ud2Var.e;
                    if (runnable != null) {
                        runnable.run();
                    }
                }
                break;
            case 22:
                pa1 pa1Var = (pa1) obj2;
                Bitmap bitmap = (Bitmap) obj;
                pa1Var.d = bitmap;
                Callback<Integer> callback = pa1Var.f;
                if (callback != null && bitmap != null) {
                    callback.onResult(Integer.valueOf(bitmap.getAllocationByteCount()));
                }
                pa1Var.b(3);
                break;
            case 23:
                vk2 vk2Var = (vk2) obj2;
                Bitmap bitmap2 = (Bitmap) obj;
                if (bitmap2 != null) {
                    RoundedCornerImageView roundedCornerImageView = (RoundedCornerImageView) vk2Var.i.findViewById(R$id.image_preview);
                    roundedCornerImageView.setImageBitmap(bitmap2);
                    roundedCornerImageView.setRoundedFillColor(ii2.g(vk2Var.e, R$attr.colorOnSurface));
                    roundedCornerImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                } else {
                    vk2Var.getClass();
                }
                break;
            case 24:
                SigninFirstRunFragment signinFirstRunFragment = (SigninFirstRunFragment) obj2;
                Intent intent2 = (Intent) obj;
                int i7 = SigninFirstRunFragment.f0;
                if (intent2 != null) {
                    signinFirstRunFragment.G0(intent2, 1);
                } else {
                    FragmentActivity fragmentActivityD = signinFirstRunFragment.D();
                    Intent intent3 = new Intent("android.settings.SYNC_SETTINGS");
                    ComponentName componentName2 = q11.a;
                    try {
                        fragmentActivityD.startActivity(intent3, null);
                    } catch (ActivityNotFoundException unused2) {
                        return;
                    }
                }
                break;
            case 25:
                ox0 ox0Var = (ox0) obj2;
                String str = (String) obj;
                ox0Var.getClass();
                if (str != null) {
                    xo xoVar = new xo(6);
                    ShoppingService shoppingService = ox0Var.c;
                    long j3 = shoppingService.a;
                    if (j3 != 0) {
                        N.MVj6AAVT(j3, shoppingService, 1, 1, 1, str, xoVar);
                        break;
                    }
                }
                break;
            case 26:
                aa3.this.k = (GURL) obj;
                break;
            case 27:
                xe3 xe3Var = (xe3) obj2;
                if (((Integer) obj).intValue() == 4) {
                    xe3Var.notifyEvent("desktop_site_default_on_gesture");
                }
                xe3Var.dismissed("IPH_RequestDesktopSiteDefaultOn");
                break;
            case 28:
                ((py2) obj2).e();
                break;
            default:
                i53 i53Var = (i53) obj2;
                Rect rect = (Rect) obj;
                int[] iArr = i53.j;
                if (rect != null) {
                    i53Var.i.set(rect);
                    i53Var.e();
                } else {
                    i53Var.getClass();
                }
                break;
        }
    }
}
