package defpackage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.Size;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.j;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderService;
import androidx.preference.Preference;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.e;
import defpackage.am;
import defpackage.c82;
import defpackage.g60;
import defpackage.il1;
import defpackage.ju2;
import defpackage.pj1;
import defpackage.wa2;
import defpackage.xb;
import defpackage.xx2;
import java.util.Collection;
import java.util.HashSet;
import org.chromium.base.UnguessableToken;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.autofill.settings.AutofillServerCardEditor;
import org.chromium.chrome.browser.autofill.settings.b;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.feed.FeedStream;
import org.chromium.chrome.browser.feed.webfeed.i;
import org.chromium.chrome.browser.firstrun.FirstRunActivityBase;
import org.chromium.chrome.browser.homepage.settings.HomepageSettings;
import org.chromium.chrome.browser.keyboard_accessory.a;
import org.chromium.chrome.browser.language.settings.b;
import org.chromium.chrome.browser.ntp.ForeignSessionHelper;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.privacy.settings.DoNotTrackSettings;
import org.chromium.chrome.browser.privacy_guide.PrivacyGuideFragment;
import org.chromium.chrome.browser.search_resumption.d;
import org.chromium.chrome.browser.test_dummy.TestDummyActivity;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.paintpreview.player.a;
import org.chromium.components.prefs.PrefService;
import org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class pq2 implements am.a, MediaRouteProvider.b.c, SwipeRefreshLayout.f, TabContentManager.a, b.a, xx2.a, LargeIconBridge.LargeIconCallback, CustomTabToolbar.d, g60.b, ju2.a, wa2.a, i.a, xb.c, il1.a, Preference.c, a.b, k32, b.a, pj1.a, SharedPreferencesManager.b, ForeignSessionHelper.ForeignSessionCallback, e.b, d.a, a.InterfaceC0136a, c82.b, p01 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ pq2(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // xb.c
    public final boolean a() {
        FirstRunActivityBase firstRunActivityBase = (FirstRunActivityBase) this.f;
        int i = FirstRunActivityBase.k0;
        firstRunActivityBase.o();
        return true;
    }

    @Override // defpackage.p01
    public final void b() {
        TestDummyActivity testDummyActivity = (TestDummyActivity) this.f;
        int i = TestDummyActivity.D;
        testDummyActivity.getClass();
        throw new RuntimeException("Failed to install module");
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider.b.c
    public final void c(MediaRouteProvider.b bVar, androidx.mediarouter.media.d dVar, Collection collection) {
        ((MediaRouteProviderService.b) this.f).g.g(bVar, dVar, collection);
    }

    @Override // org.chromium.components.paintpreview.player.a.InterfaceC0136a
    public final void g(UnguessableToken unguessableToken, UnguessableToken[] unguessableTokenArr, int[] iArr, int[] iArr2, int[] iArr3, UnguessableToken[] unguessableTokenArr2, int[] iArr4, float f, long j) {
        na1 na1Var = (na1) this.f;
        na1Var.getClass();
        na1Var.c = (iArr == null || iArr.length < 2) ? new Size(0, 0) : new Size(iArr[0], iArr[1]);
        Size size = (iArr2 == null || iArr2.length < 2) ? new Size(0, 0) : new Size(iArr2[0], iArr2[1]);
        na1Var.d = new Point(size.getWidth(), size.getHeight());
        na1Var.b.onResult(0);
    }

    @Override // com.google.android.material.tabs.e.b
    public final void h(TabLayout.g gVar, int i) {
        PrivacyGuideFragment privacyGuideFragment = (PrivacyGuideFragment) this.f;
        int i2 = PrivacyGuideFragment.n0;
        privacyGuideFragment.getClass();
        gVar.i.setClickable(false);
        if (i == 0 || i == privacyGuideFragment.c0.k() - 1) {
            gVar.i.setVisibility(8);
        }
    }

    @Override // org.chromium.chrome.browser.preferences.SharedPreferencesManager.b
    public final void i(String str) {
        zs0 zs0Var = (zs0) this.f;
        zs0Var.getClass();
        if (TextUtils.equals(str, "ui_theme_setting")) {
            zs0Var.g();
        }
    }

    @Override // org.chromium.chrome.browser.autofill.settings.b.a
    public final void j(int i, String str) {
        AutofillServerCardEditor autofillServerCardEditor = (AutofillServerCardEditor) this.f;
        int i2 = AutofillServerCardEditor.n0;
        autofillServerCardEditor.getClass();
        oj2.U(i, 3, "Autofill.VirtualCard.SettingsPageEnrollment.LinkClicked");
        CustomTabActivity.i3(autofillServerCardEditor.D(), str);
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        int i = this.e;
        Object obj2 = this.f;
        switch (i) {
            case 16:
                HomepageSettings homepageSettings = (HomepageSettings) obj2;
                int i2 = HomepageSettings.j0;
                homepageSettings.getClass();
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                qv0 qv0Var = homepageSettings.h0;
                qv0Var.a.l("homepage", zBooleanValue);
                qv0Var.f();
                homepageSettings.i0.Y(homepageSettings.M0());
                break;
            default:
                int i3 = DoNotTrackSettings.h0;
                ((PrefService) obj2).e("enable_do_not_track", ((Boolean) obj).booleanValue());
                break;
        }
        return true;
    }

    @Override // org.chromium.chrome.browser.feed.webfeed.i.a
    public final void o() {
        ((FeedStream) this.f).j.b();
    }

    @Override // am.a
    public final void onCancel() {
        j.b bVar = (j.b) this.f;
        i21.e(bVar, "this$0");
        bVar.a();
    }

    @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
    public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        int i3 = this.e;
        Object obj = this.f;
        switch (i3) {
            case 8:
                nz nzVar = (nz) obj;
                if (bitmap == null) {
                    int dimensionPixelSize = nzVar.f.getResources().getDimensionPixelSize(R$dimen.context_menu_header_monogram_size);
                    bitmap = new uc2(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize / 2, i, r7.getDimensionPixelSize(R$dimen.context_menu_header_monogram_text_size)).c(nzVar.g);
                    if (bitmap == null) {
                    }
                }
                int iH = nzVar.e.h(oz.h);
                nzVar.a(Bitmap.createScaledBitmap(bitmap, iH, iH, true), false);
                break;
            default:
                vk2 vk2Var = (vk2) obj;
                Activity activity = vk2Var.e;
                if (bitmap != null) {
                    int dimensionPixelSize2 = activity.getResources().getDimensionPixelSize(R$dimen.sharing_hub_preview_inner_icon_size);
                    Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, dimensionPixelSize2, dimensionPixelSize2, true);
                    ImageView imageView = (ImageView) vk2Var.i.findViewById(R$id.image_preview);
                    imageView.setImageBitmap(bitmapCreateScaledBitmap);
                    vk2Var.c(imageView);
                    n3.X0("SharingHubAndroid.LinkFaviconShown");
                } else {
                    vk2Var.z(AppCompatResources.a(activity, R$drawable.generic_favicon));
                    n3.X0("SharingHubAndroid.GenericFaviconShown");
                }
                break;
        }
    }

    @Override // org.chromium.chrome.browser.ntp.ForeignSessionHelper.ForeignSessionCallback
    public final void onUpdated() {
        ((org.chromium.chrome.browser.ntp.j) this.f).f();
    }

    @Override // defpackage.k32
    public final boolean q(f51 f51Var) {
        return !((HashSet) this.f).contains(f51Var.a);
    }

    @Override // wa2.a
    public final void r(String str, va2 va2Var) {
        kc0 kc0Var = (kc0) this.f;
        kc0Var.d.i(((OfflineItem) kc0Var.b.j(lc0.a)).e, str, va2Var);
    }
}
