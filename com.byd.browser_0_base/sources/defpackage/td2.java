package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextPaint;
import android.util.Log;
import android.widget.ImageView;
import defpackage.i82;
import defpackage.jq;
import defpackage.kj3;
import defpackage.q82;
import defpackage.yw1;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.infobar.SafetyTipInfoBar;
import org.chromium.chrome.browser.keyboard_accessory.AutofillKeyboardAccessoryViewBridge;
import org.chromium.chrome.browser.keyboard_accessory.a;
import org.chromium.chrome.browser.omnibox.c;
import org.chromium.chrome.browser.omnibox.g;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.chrome.browser.password_check.h;
import org.chromium.chrome.browser.permissions.NotificationBlockedDialog;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.privacy_sandbox.FledgePreference;
import org.chromium.chrome.browser.privacy_sandbox.v4.FledgeAllSitesFragmentV4;
import org.chromium.chrome.browser.privacy_sandbox.v4.FledgeFragmentV4;
import org.chromium.chrome.browser.privacy_sandbox.v4.TopicsFragmentV4;
import org.chromium.chrome.browser.privacy_sandbox.v4.TopicsLearnMoreFragment;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.safe_browsing.SafeBrowsingPasswordReuseDialogBridge;
import org.chromium.chrome.browser.safety_check.SafetyCheckElementPreference;
import org.chromium.chrome.browser.searchwidget.SearchActivity;
import org.chromium.chrome.browser.share.qrcode.QrCodeDialog;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.components.query_tiles.QueryTile;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class td2 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ td2(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

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
        boolean z;
        String str;
        int i = -1;
        int i2 = this.e;
        int i3 = 3;
        int i4 = 2;
        int i5 = 0;
        Object obj2 = this.f;
        switch (i2) {
            case 0:
                ((SafetyTipInfoBar) obj2).d();
                break;
            case 1:
                AutofillKeyboardAccessoryViewBridge autofillKeyboardAccessoryViewBridge = (AutofillKeyboardAccessoryViewBridge) obj2;
                a aVar = (a) obj;
                if (autofillKeyboardAccessoryViewBridge.g != aVar) {
                    autofillKeyboardAccessoryViewBridge.g = aVar;
                    if (aVar != null) {
                        aVar.m1();
                        break;
                    }
                }
                break;
            case 2:
                ImageView imageView = (ImageView) obj2;
                Drawable drawable = (Drawable) obj;
                int dimensionPixelSize = imageView.getContext().getResources().getDimensionPixelSize(R$dimen.keyboard_accessory_suggestion_icon_size);
                if (drawable != null) {
                    drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                }
                imageView.setImageDrawable(drawable);
                break;
            case 3:
                nw nwVar = (nw) obj2;
                nwVar.c.b(nwVar.b, nwVar.a, null);
                break;
            case 4:
                jq jqVar = (jq) obj2;
                bi1 bi1Var = (bi1) obj;
                bi1 bi1Var2 = jqVar.l;
                jq.b bVar = jqVar.r;
                if (bi1Var2 != null) {
                    bi1Var2.h.c(bVar);
                }
                jqVar.l = bi1Var;
                if (bi1Var != null) {
                    bi1Var.h.a(bVar);
                    break;
                }
                break;
            case 5:
                oj3 oj3Var = (oj3) obj2;
                kj3 kj3Var = (kj3) obj;
                oj3Var.getClass();
                oj2.O("GoogleUpdate.StartingUpdateState", kj3Var != null);
                kj3.a aVarA0 = kj3.a0();
                long jCurrentTimeMillis = System.currentTimeMillis();
                aVarA0.y();
                kj3.Q((kj3) aVarA0.f, jCurrentTimeMillis);
                aVarA0.y();
                kj3.T((kj3) aVarA0.f);
                aVarA0.y();
                kj3.S((kj3) aVarA0.f);
                aVarA0.y();
                kj3.P((kj3) aVarA0.f);
                aVarA0.y();
                kj3.O((kj3) aVarA0.f, false);
                kj3 kj3VarBuild = aVarA0.build();
                ye3 ye3Var = oj3Var.a;
                ye3Var.getClass();
                ye3Var.a.b(new l10(27, kj3VarBuild));
                break;
            case 6:
                g gVar = (g) obj2;
                TemplateUrlService templateUrlService = (TemplateUrlService) obj;
                gVar.getClass();
                templateUrlService.a(gVar);
                rs1<u8> rs1Var = gVar.i;
                Context context = gVar.s;
                zi0 zi0Var = zi0.b;
                bs0 bs0VarA = bs0.a();
                SharedPreferencesManager.getInstance();
                z7 z7VarM = z7.m();
                Profile profileD = Profile.d();
                z7VarM.getClass();
                rs1Var.a(new u8(context, zi0Var, templateUrlService, bs0VarA, gVar, z7.v(profileD), AccountManagerFacadeProvider.getInstance()));
                gVar.k();
                break;
            case 7:
                wt2 wt2Var = (wt2) obj2;
                wt2Var.getClass();
                ((TemplateUrlService) obj).a(wt2Var);
                wt2Var.f(0);
                break;
            case 8:
                d dVar = (d) obj2;
                int iIntValue = ((Integer) obj).intValue();
                c cVar = (c) dVar.g;
                cVar.getClass();
                if (!t41.f.f(cVar.e.getContext(), cVar.l) && cVar.e.getContext().getResources().getConfiguration().keyboard != 2) {
                    z = false;
                }
                if (z) {
                    dVar.m.k = iIntValue;
                }
                break;
            case 9:
                e91 e91Var = (e91) obj2;
                Boolean bool = (Boolean) obj;
                if (e91Var != null) {
                    e91Var.a.e = bool.booleanValue();
                    break;
                }
                break;
            case 10:
                ((PropertyModel) obj2).p(h.a.d, (yw1.a) obj);
                break;
            case 11:
                Context context2 = (Context) obj2;
                if (((Boolean) obj).booleanValue()) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setPackage("com.android.vending");
                    intent.putExtra("callerId", context2.getPackageName());
                    context2.startActivity(intent);
                }
                break;
            case 12:
            case 21:
            default:
                we2 we2Var = (we2) obj2;
                we2Var.getClass();
                int iIntValue2 = ((Integer) obj).intValue();
                Runnable runnable = we2Var.d;
                if (1 == iIntValue2) {
                    oj2.U(1, 4, "Sharing.ScreenshotFallback.Action");
                    Bitmap bitmap = (Bitmap) we2Var.a.j(ye2.b);
                    String string = we2Var.b.getString(R$string.screenshot_title_for_share, new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis())));
                    jk2.b(string, bitmap, new n62(we2Var, 14, string));
                    runnable.run();
                } else if (2 == iIntValue2) {
                    oj2.U(2, 4, "Sharing.ScreenshotFallback.Action");
                    we2Var.c.run();
                } else if (3 == iIntValue2) {
                    oj2.U(3, 4, "Sharing.ScreenshotFallback.Action");
                    runnable.run();
                } else if (4 == iIntValue2) {
                    oj2.U(0, 4, "Sharing.ScreenshotFallback.Action");
                    we2Var.e.onResult(runnable);
                }
                break;
            case 13:
                N.MffoEjsa(((NotificationBlockedDialog) obj2).g);
                break;
            case 14:
                FledgePreference fledgePreference = (FledgePreference) obj2;
                Drawable drawable2 = (Drawable) obj;
                if (drawable2 != null) {
                    fledgePreference.L(drawable2);
                } else {
                    fledgePreference.getClass();
                }
                break;
            case 15:
                FledgeAllSitesFragmentV4 fledgeAllSitesFragmentV4 = (FledgeAllSitesFragmentV4) obj2;
                List<String> list = (List) obj;
                if (fledgeAllSitesFragmentV4.m0 == null) {
                    fledgeAllSitesFragmentV4.m0 = new LargeIconBridge(Profile.d());
                }
                fledgeAllSitesFragmentV4.l0.c0();
                for (String str2 : list) {
                    FledgePreference fledgePreference2 = new FledgePreference(fledgeAllSitesFragmentV4.a0.a, str2, fledgeAllSitesFragmentV4.m0);
                    int i6 = R$drawable.btn_close;
                    String string2 = fledgeAllSitesFragmentV4.N().getString(R$string.settings_fledge_page_block_site_a11y_label, str2);
                    fledgePreference2.Y = i6;
                    fledgePreference2.Z = string2;
                    fledgePreference2.Y(false);
                    fledgePreference2.setOnPreferenceClickListener(fledgeAllSitesFragmentV4);
                    fledgeAllSitesFragmentV4.l0.Y(fledgePreference2);
                }
                break;
            case 16:
                FledgeFragmentV4 fledgeFragmentV4 = (FledgeFragmentV4) obj2;
                List list2 = (List) obj;
                if (fledgeFragmentV4.r0 == null) {
                    fledgeFragmentV4.r0 = new LargeIconBridge(Profile.d());
                }
                fledgeFragmentV4.n0.c0();
                fledgeFragmentV4.t0 = list2.size() > 15;
                int iMin = Math.min(list2.size(), 15);
                for (int i7 = 0; i7 < iMin; i7++) {
                    String str3 = (String) list2.get(i7);
                    FledgePreference fledgePreference3 = new FledgePreference(fledgeFragmentV4.K(), str3, fledgeFragmentV4.r0);
                    int i8 = R$drawable.btn_close;
                    String string3 = fledgeFragmentV4.N().getString(R$string.settings_fledge_page_block_site_a11y_label, str3);
                    fledgePreference3.Y = i8;
                    fledgePreference3.Z = string3;
                    fledgePreference3.V = Boolean.FALSE;
                    fledgePreference3.setOnPreferenceClickListener(fledgeFragmentV4);
                    fledgeFragmentV4.n0.Y(fledgePreference3);
                }
                fledgeFragmentV4.P0();
                break;
            case 17:
                TopicsFragmentV4 topicsFragmentV4 = (TopicsFragmentV4) obj2;
                int i9 = TopicsFragmentV4.r0;
                topicsFragmentV4.getClass();
                n3.X0("Settings.PrivacySandbox.Topics.LearnMoreClicked");
                SettingsLauncher settingsLauncher = topicsFragmentV4.i0;
                if (settingsLauncher != null) {
                    settingsLauncher.g(topicsFragmentV4.K(), TopicsLearnMoreFragment.class);
                }
                break;
            case 18:
                q82 q82Var = (q82) obj2;
                gx0 gx0Var = (gx0) obj;
                q82Var.getClass();
                QueryTile queryTile = (QueryTile) gx0Var;
                la3 la3Var = q82Var.e;
                la3Var.getClass();
                String str4 = queryTile.a;
                Iterator<QueryTile> it = la3Var.b.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                    } else if (it.next().a.equals(str4)) {
                        z = true;
                    }
                }
                StringBuilder sb = new StringBuilder("Search.");
                String str5 = la3Var.a;
                sb.append(str5);
                sb.append(".Tile.Clicked.IsTopLevel");
                oj2.O(sb.toString(), z);
                while (true) {
                    int size = la3Var.b.size();
                    str = queryTile.a;
                    if (i5 < size) {
                        int iA = la3.a(la3Var.b.get(i5), str, i5);
                        if (iA != -1) {
                            i = iA;
                        } else {
                            i5++;
                        }
                    }
                }
                oj2.a0(i, "Search." + str5 + ".Tile.Clicked");
                ja3 ja3Var = q82Var.d;
                ja3Var.a(str);
                r82.b("Chrome.Querytiles.RecentQueryTileClicks");
                if (queryTile.e.isEmpty()) {
                    q82Var.b.onResult(new q82.a(queryTile.d, queryTile.g));
                } else {
                    q82Var.i = true;
                    ja3Var.b(gx0Var.a, new on0(i3, q82Var));
                }
                break;
            case 19:
                SafeBrowsingPasswordReuseDialogBridge safeBrowsingPasswordReuseDialogBridge = (SafeBrowsingPasswordReuseDialogBridge) obj2;
                int iIntValue3 = ((Integer) obj).intValue();
                long j = safeBrowsingPasswordReuseDialogBridge.a;
                if (j != 0) {
                    if (iIntValue3 == 1) {
                        N.MgVzN6AS(j, safeBrowsingPasswordReuseDialogBridge);
                    } else if (iIntValue3 != 2) {
                        N.M_X8ygDO(j, safeBrowsingPasswordReuseDialogBridge);
                    } else {
                        N.MWlqcC9l(j, safeBrowsingPasswordReuseDialogBridge);
                    }
                    break;
                }
                break;
            case 20:
                ((SafetyCheckElementPreference) obj2).Z();
                break;
            case 22:
                SearchActivity searchActivity = (SearchActivity) obj2;
                Boolean bool2 = (Boolean) obj;
                Object obj3 = SearchActivity.q0;
                if (!searchActivity.d()) {
                    if (bool2 == null || !bool2.booleanValue()) {
                        Log.e("cr_searchwidget", "User failed to select a default search engine.");
                        searchActivity.finish();
                    } else {
                        searchActivity.I.post(new f52(i4, searchActivity));
                    }
                    break;
                }
                break;
            case 23:
                ((ei2) obj2).e.r(Boolean.valueOf(((Boolean) obj).booleanValue()));
                break;
            case 24:
                ir irVar = (ir) obj2;
                WindowAndroid windowAndroid = irVar.e.a;
                QrCodeDialog qrCodeDialog = new QrCodeDialog();
                Bundle bundle = new Bundle();
                bundle.putString("url_key", irVar.i);
                qrCodeDialog.setArguments(bundle);
                qrCodeDialog.a(windowAndroid);
                qrCodeDialog.show(irVar.a.getFragmentManager(), (String) null);
                break;
            case 25:
                s71 s71Var = (s71) obj2;
                s71Var.getClass();
                s71Var.k = String.join(",", (String[]) obj);
                y71.a(new ArrayList(), s71Var.f.c().y().c(), new on0(9, s71Var), 0);
                break;
            case 26:
                ((a81) obj2).b.dismissed("IPH_SharedHighlightingReceiver");
                break;
            case 27:
                int iIntValue4 = ((Integer) obj).intValue();
                ((org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.c) obj2).getClass();
                int i10 = iIntValue4 / 1024;
                break;
            case 28:
                i82 i82Var = (i82) obj2;
                if (((Boolean) obj).booleanValue()) {
                    i82Var.a();
                    Bitmap bitmap2 = (Bitmap) i82Var.b.j(m82.a);
                    int i11 = R$string.qr_code_filename_prefix;
                    Object[] objArr = {String.valueOf(System.currentTimeMillis())};
                    Context context3 = i82Var.a;
                    String string4 = context3.getString(i11, objArr);
                    i82Var.e = true;
                    int dimensionPixelSize2 = context3.getResources().getDimensionPixelSize(R$dimen.qrcode_size);
                    int dimensionPixelSize3 = context3.getResources().getDimensionPixelSize(R$dimen.text_size_large);
                    int dimensionPixelSize4 = context3.getResources().getDimensionPixelSize(R$dimen.side_padding);
                    int dimensionPixelSize5 = context3.getResources().getDimensionPixelSize(R$dimen.url_box_top_padding);
                    int dimensionPixelSize6 = context3.getResources().getDimensionPixelSize(R$dimen.url_box_bottom_padding);
                    TextPaint textPaint = new TextPaint();
                    textPaint.setAntiAlias(true);
                    textPaint.setColor(-16777216);
                    textPaint.setTextSize(dimensionPixelSize3);
                    i82.a aVar2 = new i82.a(i82Var.f, textPaint, dimensionPixelSize2, Layout.Alignment.ALIGN_CENTER);
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap((dimensionPixelSize4 * 2) + dimensionPixelSize2, ((aVar2.getHeight() + dimensionPixelSize5 + dimensionPixelSize6) * 2) + dimensionPixelSize2, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmapCreateBitmap);
                    canvas.drawColor(-1);
                    canvas.translate(dimensionPixelSize4, dimensionPixelSize5);
                    aVar2.draw(canvas);
                    canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap2, dimensionPixelSize2, dimensionPixelSize2, false), 0.0f, aVar2.getHeight() + dimensionPixelSize6, textPaint);
                    N.MTm9IWhH(string4, bitmapCreateBitmap);
                    i82Var.g.run();
                } else {
                    i82Var.getClass();
                }
                break;
        }
    }
}
