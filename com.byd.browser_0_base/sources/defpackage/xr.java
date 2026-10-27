package defpackage;

import J.N;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.bj2;
import defpackage.k70;
import defpackage.ny0;
import defpackage.xq3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionException;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.autofill.settings.b;
import org.chromium.chrome.browser.bookmarks.PowerBookmarkShoppingItemRow;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.device_dialog.UsbChooserDialog;
import org.chromium.chrome.browser.firstrun.FirstRunActivityBase;
import org.chromium.chrome.browser.infobar.AutofillOfferNotificationInfoBar;
import org.chromium.chrome.browser.infobar.NearOomInfoBar;
import org.chromium.chrome.browser.keyboard_accessory.d;
import org.chromium.chrome.browser.merchant_viewer.MerchantTrustSignalsEvent;
import org.chromium.chrome.browser.merchant_viewer.MerchantTrustSignalsEventStorage;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.browser_ui.share.a;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.policy.PolicyService;
import org.chromium.components.prefs.PrefService;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xr implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ xr(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int iM37SqSAy = 3;
        k70.a aVarG = null;
        prefServiceK = null;
        PrefService prefServiceK = null;
        int i = 4;
        int i2 = 0;
        switch (this.e) {
            case 0:
                n3 n3Var = ChromeTabbedActivity.S1;
                ((ChromeTabbedActivity) this.f).z3((Tab) obj);
                break;
            case 1:
                ChromeActivity chromeActivity = (ChromeActivity) this.f;
                k11 k11Var = ChromeActivity.Z0;
                chromeActivity.getClass();
                chromeActivity.S0.a(new um3(i, ((fj) obj).y), 7);
                break;
            case 2:
                ((o7) this.f).n = (hy0) obj;
                break;
            case 3:
                ((b) this.f).f.j(0, (String) obj);
                break;
            case 4:
                ((gg) this.f).c((BookmarkId) obj);
                break;
            case 5:
                PowerBookmarkShoppingItemRow powerBookmarkShoppingItemRow = (PowerBookmarkShoppingItemRow) this.f;
                Bitmap bitmap = (Bitmap) obj;
                int i3 = PowerBookmarkShoppingItemRow.T;
                if (bitmap != null) {
                    powerBookmarkShoppingItemRow.L = true;
                    powerBookmarkShoppingItemRow.setStartIconDrawable(new BitmapDrawable(powerBookmarkShoppingItemRow.getResources(), bitmap));
                } else {
                    powerBookmarkShoppingItemRow.getClass();
                }
                break;
            case 6:
                ((vj) this.f).y();
                break;
            case 7:
                bj2 bj2Var = (bj2) this.f;
                CustomTabsSessionToken customTabsSessionToken = (CustomTabsSessionToken) obj;
                if (customTabsSessionToken != null) {
                    while (true) {
                        SparseArray<bj2.a> sparseArray = bj2Var.b;
                        if (i2 < sparseArray.size()) {
                            if (customTabsSessionToken.equals(sparseArray.valueAt(i2).a)) {
                                sparseArray.removeAt(i2);
                            }
                            i2++;
                        }
                    }
                } else {
                    bj2Var.getClass();
                }
                break;
            case 8:
                ViewGroup viewGroup = (ViewGroup) obj;
                b.a aVarD = ((EphemeralTabCoordinator) this.f).k.c.d();
                while (aVarD.hasNext()) {
                    ((yh0) aVarD.next()).a(viewGroup);
                }
                break;
            case 9:
                h61 h61Var = (h61) this.f;
                Iterator it = h61Var.L.iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    aq1<Boolean> aq1Var = h61Var.O;
                    if (!zHasNext) {
                        aq1Var.r(Boolean.FALSE);
                        break;
                    } else {
                        Boolean bool = ((je2) it.next()).getHandleBackPressChangedSupplier().get();
                        if (bool != null && bool.booleanValue()) {
                            aq1Var.r(Boolean.TRUE);
                            break;
                        }
                    }
                }
                break;
            case 10:
                aq aqVar = (aq) this.f;
                Uri uri = (Uri) obj;
                nw2<ck2> nw2Var = aqVar.d;
                boolean zA = nw2Var.get().a();
                ContextMenuParams contextMenuParams = aqVar.f;
                if (!zA) {
                    hk2.h(aqVar.h(), Profile.b(((az2) aqVar.b).a.g), null, uri, contextMenuParams.b.j());
                } else {
                    String type = xz.a.getContentResolver().getType(uri);
                    WindowAndroid windowAndroidH = aqVar.h();
                    String strF = a8.F(contextMenuParams);
                    ArrayList arrayList = new ArrayList(Collections.singletonList(uri));
                    String strJ = "";
                    if (!TextUtils.isEmpty("") && !TextUtils.isEmpty("")) {
                        strJ = ((GURL) N.M1WDPiaY("")).j();
                    }
                    nw2Var.get().c(new a(windowAndroidH, strF, null, null, strJ, type, arrayList, null, null, null, null, null, null, null), new jr(true, false, false, contextMenuParams.g, contextMenuParams.b, false, false, null, type.equals("image/gif") ? 2 : 1), 2);
                }
                break;
            case 11:
                nz nzVar = (nz) this.f;
                Bitmap bitmap2 = (Bitmap) obj;
                if (bitmap2 == null) {
                    nzVar.getClass();
                } else {
                    Resources resources = nzVar.f.getResources();
                    Drawable drawableC = c6.c(resources, R$drawable.checkerboard_background, 0);
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawableC.getIntrinsicWidth(), drawableC.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmapCreateBitmap);
                    drawableC.setBounds(0, 0, drawableC.getIntrinsicWidth(), drawableC.getIntrinsicHeight());
                    drawableC.draw(canvas);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmapCreateBitmap);
                    Shader.TileMode tileMode = Shader.TileMode.REPEAT;
                    bitmapDrawable.setTileModeXY(tileMode, tileMode);
                    bitmapDrawable.setBounds(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
                    Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmap2.getWidth(), bitmap2.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
                    bitmapDrawable.draw(canvas2);
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    canvas2.drawBitmap(bitmap2, new Matrix(), paint);
                    nzVar.a(bitmapCreateBitmap2, true);
                }
                break;
            case 12:
                md mdVar = (md) this.f;
                Profile profile = (Profile) obj;
                mdVar.getClass();
                if (profile != null) {
                    ca2 ca2Var = new ca2(mdVar.g, oj2.t(profile));
                    sk skVar = bq.a;
                    if (N.M09VlOh_("ReengagementNotification")) {
                        on0 on0Var = new on0(i, ca2Var);
                        ThreadUtils.b();
                        if (k70.a == null) {
                            k70.a = new k70.b();
                        }
                        k70.b bVar = k70.a;
                        bVar.getClass();
                        ThreadUtils.b();
                        if (bVar.b != 2) {
                            if (bVar.b == 0) {
                                try {
                                    bVar.c(i9.e);
                                } catch (RejectedExecutionException unused) {
                                    PostTask.c(rh3.a, new l70(on0Var, 0));
                                    return;
                                }
                            }
                            bVar.h.a(on0Var);
                        } else {
                            try {
                                aVarG = bVar.g();
                            } catch (InterruptedException | ExecutionException unused2) {
                            }
                            PostTask.c(rh3.a, new l91(on0Var, i, aVarG));
                        }
                        break;
                    }
                }
                break;
            case 13:
                ((g30) this.f).t();
                break;
            case 14:
                View view = (View) obj;
                long j = ((UsbChooserDialog) this.f).f;
                if (j != 0) {
                    N.M0RyTBvz(j);
                    view.invalidate();
                    break;
                }
                break;
            case 15:
                ((org.chromium.chrome.browser.download.home.filter.b) this.f).b(((Integer) obj).intValue());
                break;
            case 16:
                m60 m60Var = (m60) this.f;
                m60Var.getClass();
                oj2.V(3);
                m60Var.f.a(((OfflineItem) obj).e);
                break;
            case 17:
                ((aq1) this.f).r((Boolean) obj);
                break;
            case 18:
                xq3.a aVar = (xq3.a) this.f;
                Bitmap bitmap3 = (Bitmap) obj;
                if (bitmap3 != null) {
                    aVar.d.onResult(bitmap3);
                } else {
                    xq3.this.a.b(aVar.a, aVar.b, new gx(11, aVar));
                }
                break;
            case 19:
                ((FirstRunActivityBase) this.f).i2(((Boolean) obj).booleanValue());
                break;
            case 20:
                o22 o22Var = (o22) this.f;
                PolicyService policyService = (PolicyService) obj;
                if (o22Var.f.get() == null) {
                    if (!N.MCCtS0px(policyService.a, policyService)) {
                        n22 n22Var = new n22(o22Var, policyService);
                        o22Var.h = n22Var;
                        org.chromium.base.b<PolicyService.a> bVar2 = policyService.b;
                        if (bVar2.isEmpty()) {
                            N.M4YsjnbO(policyService.a, policyService);
                        }
                        bVar2.a(n22Var);
                    } else {
                        o22Var.a();
                    }
                    break;
                }
                break;
            case 21:
                org.chromium.chrome.browser.firstrun.b bVar3 = (org.chromium.chrome.browser.firstrun.b) this.f;
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                bVar3.getClass();
                SystemClock.elapsedRealtime();
                if (!zBooleanValue) {
                    SharedPreferencesManager.getInstance().l("Chrome.FirstRun.SkippedByPolicy", false);
                }
                new Handler(Looper.getMainLooper()).post(new qm0(5, bVar3));
                break;
            case 22:
                ((gv0) this.f).D();
                break;
            case 23:
                ny0.a aVar2 = (ny0.a) this.f;
                if (!((Boolean) obj).booleanValue()) {
                    aVar2.c();
                } else {
                    aVar2.a();
                }
                break;
            case 24:
                AutofillOfferNotificationInfoBar autofillOfferNotificationInfoBar = (AutofillOfferNotificationInfoBar) this.f;
                N.MPhRlych(autofillOfferNotificationInfoBar.r, autofillOfferNotificationInfoBar, autofillOfferNotificationInfoBar.t);
                break;
            case 25:
                ((NearOomInfoBar) this.f).d();
                break;
            case 26:
                bl blVar = (bl) obj;
                if (((d) this.f).e) {
                    blVar.b(blVar.d);
                }
                break;
            case 27:
                s91 s91Var = (s91) this.f;
                boolean zBooleanValue2 = ((Boolean) obj).booleanValue();
                s91Var.getClass();
                SharedPreferencesManager.getInstance().l("LocaleManager_PREF_AUTO_SWITCH", zBooleanValue2);
                if (zBooleanValue2) {
                    SharedPreferencesManager.getInstance().readBoolean("LocaleManager_PREF_AUTO_SWITCH", false);
                }
                break;
            case 28:
                zf1 zf1Var = (zf1) this.f;
                rf1 rf1Var = (rf1) obj;
                if (rf1Var == null) {
                    zf1Var.getClass();
                    break;
                } else {
                    Profile profile2 = zf1Var.g.a.get();
                    MerchantTrustSignalsEventStorage merchantTrustSignalsEventStorage = profile2 == null ? null : (MerchantTrustSignalsEventStorage) dg1.b.get(profile2);
                    if (merchantTrustSignalsEventStorage != null) {
                        wf1 wf1Var = zf1Var.e;
                        WebContents webContents = rf1Var.a;
                        wf1Var.getClass();
                        if (webContents != null) {
                            N.M$ejnyHh(webContents, "Shopping.MerchantTrust.MessageSeen", "HasOccurred");
                        }
                        Profile profile3 = zf1Var.h.get();
                        if (profile3 != null && !profile3.h()) {
                            prefServiceK = oj2.k(profile3);
                        }
                        if (prefServiceK != null) {
                            String string = Long.toString(System.currentTimeMillis());
                            String strC = prefServiceK.c("commerce_merchant_viewer_messages_shown_time");
                            if (TextUtils.isEmpty(strC)) {
                                prefServiceK.f("commerce_merchant_viewer_messages_shown_time", string);
                            } else {
                                String strK = C0175b.k(strC, "_", string);
                                String[] strArrSplit = strK.split("_");
                                if (a8.P()) {
                                    sk skVar2 = bq.a;
                                    iM37SqSAy = N.M37SqSAy("CommerceMerchantViewer", "trust_signals_max_allowed_number_in_given_window", 3);
                                }
                                if (strArrSplit.length <= iM37SqSAy) {
                                    prefServiceK.f("commerce_merchant_viewer_messages_shown_time", strK);
                                } else {
                                    prefServiceK.f("commerce_merchant_viewer_messages_shown_time", strK.substring(strArrSplit[0].length() + 1));
                                }
                            }
                        }
                        MerchantTrustSignalsEvent merchantTrustSignalsEvent = new MerchantTrustSignalsEvent(rf1Var.a(), System.currentTimeMillis());
                        N.MdTDFP2V(merchantTrustSignalsEventStorage.a, merchantTrustSignalsEvent.a, merchantTrustSignalsEvent.b, null);
                        break;
                    }
                }
                break;
            default:
                int i4 = wu1.m;
                ((wu1) this.f).e((ShoppingService.MerchantInfo) obj);
                break;
        }
    }
}
