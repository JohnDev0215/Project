package defpackage;

import J.N;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.preference.Preference;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.app.bookmarks.BookmarkFolderSelectActivity;
import org.chromium.chrome.browser.autofill.settings.AutofillEditLinkPreference;
import org.chromium.chrome.browser.autofill.settings.AutofillServerCardEditor;
import org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.chrome.browser.firstrun.FirstRunActivityBase;
import org.chromium.chrome.browser.firstrun.LightweightFirstRunActivity;
import org.chromium.chrome.browser.language.settings.ContentLanguagesPreference;
import org.chromium.chrome.browser.language.settings.LanguageItemListFragment;
import org.chromium.chrome.browser.language.settings.LanguageSettings;
import org.chromium.chrome.browser.language.settings.SelectLanguageFragment;
import org.chromium.chrome.browser.language.settings.c;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.privacy_guide.DoneFragment;
import org.chromium.chrome.browser.privacy_guide.PrivacyGuideFragment;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.PrivacyFragment;
import org.chromium.chrome.browser.supervised_user.a;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.browser_ui.accessibility.PageZoomPreference;
import org.chromium.components.browser_ui.site_settings.AllSiteSettings;
import org.chromium.components.page_info.PageInfoContainer;
import org.chromium.components.permissions.BluetoothScanningPermissionDialog;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class by1 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ by1(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) throws Exception {
        boolean z = false;
        switch (this.e) {
            case 0:
                cy1 cy1Var = (cy1) this.f;
                EditText editText = cy1Var.f;
                if (editText != null) {
                    int selectionEnd = editText.getSelectionEnd();
                    EditText editText2 = cy1Var.f;
                    if (editText2 != null && (editText2.getTransformationMethod() instanceof PasswordTransformationMethod)) {
                        z = true;
                    }
                    if (z) {
                        cy1Var.f.setTransformationMethod(null);
                    } else {
                        cy1Var.f.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    if (selectionEnd >= 0) {
                        cy1Var.f.setSelection(selectionEnd);
                    }
                    cy1Var.q();
                    break;
                }
                break;
            case 1:
                kk kkVar = (kk) this.f;
                if (kkVar != null) {
                    Log.i("BydSettingsApi", "---showWifiSettings---");
                    if (kkVar.a >= 1) {
                        if (kkVar.c == null || !kk.i) {
                            Log.e("BydSettingsApi", "BydSettingsApi service is disconnected ,and doing reconnect.");
                            kkVar.a(kkVar.b);
                        } else {
                            z = true;
                        }
                        if (!z) {
                            kkVar.d.offer(new h6(kkVar));
                        } else {
                            try {
                                kkVar.c.h();
                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        break;
                    } else {
                        Log.i("BydSettingsApi", "---showWifiSettingsOnOldApi---");
                        Context context = kkVar.b;
                        if (context != null) {
                            PackageManager packageManager = context.getPackageManager();
                            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?action=WiFi"));
                            intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
                            List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
                            Objects.toString(listQueryIntentServices);
                            if (!listQueryIntentServices.isEmpty()) {
                                kkVar.b.startService(intent);
                            }
                            break;
                        }
                    }
                }
                break;
            case 2:
                n63 n63Var = (n63) this.f;
                n3 n3Var = ChromeTabbedActivity.S1;
                ChromeTabbedActivity chromeTabbedActivity = (ChromeTabbedActivity) n63Var.b;
                n63Var.a.g(false).c();
                chromeTabbedActivity.C2().f(19);
                break;
            case 3:
                AutofillEditLinkPreference autofillEditLinkPreference = (AutofillEditLinkPreference) this.f;
                Preference.d dVar = autofillEditLinkPreference.j;
                if (dVar != null) {
                    dVar.r(autofillEditLinkPreference);
                }
                break;
            case 4:
                AutofillServerCardEditor autofillServerCardEditor = (AutofillServerCardEditor) this.f;
                int i = AutofillServerCardEditor.n0;
                AutofillServerCardEditor.M0(autofillServerCardEditor.N0() ? 2 : 1, 1);
                CustomTabActivity.i3(autofillServerCardEditor.D(), "https://payments.google.com/#paymentMethods");
                break;
            case 5:
                ((BookMarkSelectableListToolbar) this.f).g0.setText("");
                break;
            case 6:
                xg xgVar = (xg) this.f;
                xgVar.getClass();
                n3.X0("MobileBookmark.SaveFlow.EditFolder");
                BookmarkId bookmarkId = xgVar.j;
                int i2 = lh.a;
                BookmarkId[] bookmarkIdArr = {bookmarkId};
                Context context2 = xgVar.e;
                context2.startActivity(BookmarkFolderSelectActivity.K1(context2, false, bookmarkIdArr));
                oj2.t(Profile.d()).notifyEvent("shopping_list_save_flow_folder_tap");
                xgVar.f.run();
                break;
            case 7:
                ((zh0) this.f).f.run();
                break;
            case 8:
                ((cz) this.f).getClass();
                oj2.U(2, 3, "ContextMenu.LensChip.Event");
                break;
            case 9:
                dl0 dl0Var = (dl0) this.f;
                N.MxULk9PS(dl0Var.d, 6);
                dl0Var.b("https://www.google.com/preferences/interests/hidden?sh=n");
                break;
            case 10:
                LightweightFirstRunActivity lightweightFirstRunActivity = (LightweightFirstRunActivity) this.f;
                int i3 = LightweightFirstRunActivity.y0;
                lightweightFirstRunActivity.finish();
                FirstRunActivityBase.h2(lightweightFirstRunActivity.getIntent());
                break;
            case 11:
                LanguageSettings languageSettings = (LanguageSettings) ((ContentLanguagesPreference) this.f).V;
                languageSettings.getClass();
                c.h(1);
                Intent intentE = languageSettings.h0.e(languageSettings.D(), SelectLanguageFragment.class.getName());
                intentE.putExtra("SelectLanguageFragment.PotentialLanguages", 0);
                languageSettings.G0(intentE, 1);
                break;
            case 12:
                LanguageItemListFragment languageItemListFragment = (LanguageItemListFragment) this.f;
                int i4 = LanguageItemListFragment.c0;
                languageItemListFragment.N0();
                Intent intentE2 = languageItemListFragment.Z.e(languageItemListFragment.D(), SelectLanguageFragment.class.getName());
                intentE2.putExtra("SelectLanguageFragment.PotentialLanguages", languageItemListFragment.I0());
                languageItemListFragment.G0(intentE2, 1);
                break;
            case 13:
                g11 g11Var = (g11) this.f;
                g11Var.e.b(3, g11Var.j);
                g11Var.d.run();
                break;
            case 14:
                NewTabPageLayout newTabPageLayout = (NewTabPageLayout) this.f;
                int i5 = NewTabPageLayout.L;
                newTabPageLayout.getClass();
                z7.X(4);
                newTabPageLayout.i.c.getClass();
                s61 s61Var = s61.b;
                Uri uri = Uri.EMPTY;
                uri.equals(uri);
                s61Var.a.getClass();
                break;
            case 15:
                Iterator it = ((cg2) this.f).h.iterator();
                while (it.hasNext()) {
                    ((View.OnClickListener) it.next()).onClick(view);
                }
                break;
            case 16:
                DoneFragment doneFragment = (DoneFragment) this.f;
                int i6 = DoneFragment.b0;
                doneFragment.getClass();
                n3.X0("Settings.PrivacyGuide.CompletionPSClick");
                oj2.U(9, 9, "Settings.PrivacyGuide.EntryExit");
                PrivacySandboxSettingsBaseFragment.M0(doneFragment.K(), doneFragment.a0, 0);
                break;
            case 17:
                PrivacyGuideFragment privacyGuideFragment = (PrivacyGuideFragment) this.f;
                int i7 = PrivacyGuideFragment.n0;
                privacyGuideFragment.H0();
                break;
            case 18:
                x42 x42Var = (x42) this.f;
                x42Var.e.a(x42Var, true, 9);
                break;
            case 19:
                ((ra1) this.f).e.cancel();
                break;
            case 20:
                PrivacyFragment privacyFragment = (PrivacyFragment) this.f;
                int i8 = PrivacyFragment.d0;
                privacyFragment.getClass();
                if (view.getId() == R$id.bt_privacy_disagree) {
                    privacyFragment.b().a1();
                } else if (view.getId() == R$id.bt_privacy_agree) {
                    Object tag = view.getTag();
                    if (tag == null || !((Boolean) tag).booleanValue()) {
                        view.setTag(Boolean.TRUE);
                        privacyFragment.b().E();
                    }
                }
                break;
            case 21:
                org.chromium.chrome.browser.supervised_user.website_approval.c cVar = (org.chromium.chrome.browser.supervised_user.website_approval.c) this.f;
                cVar.b.a(cVar.c, true, 9);
                ((a) cVar.a).getClass();
                oj2.U(0, 8, "FamilyLinkUser.LocalWebApprovalOutcome");
                N.MEedSkgE(0);
                break;
            case 22:
                yr2 yr2Var = (yr2) this.f;
                yr2Var.C();
                yr2Var.z.d().f(3);
                n3.X0("TasksSurface.FakeBox.VoiceSearch");
                break;
            case 23:
                PageZoomPreference pageZoomPreference = (PageZoomPreference) this.f;
                int iD = iv1.d(true, iv1.a(pageZoomPreference.T.getProgress()));
                if (iD >= 0) {
                    int iC = iv1.c(n3.k[iD]);
                    pageZoomPreference.T.setProgress(iC);
                    pageZoomPreference.h(Integer.valueOf(iC));
                }
                break;
            case 24:
                Dialog dialog = (Dialog) this.f;
                int i9 = AllSiteSettings.q0;
                dialog.dismiss();
                break;
            case 25:
                PageInfoContainer.a aVar = (PageInfoContainer.a) this.f;
                int i10 = PageInfoContainer.l;
                aVar.e.run();
                break;
            case 26:
                BluetoothScanningPermissionDialog bluetoothScanningPermissionDialog = (BluetoothScanningPermissionDialog) this.f;
                long j = bluetoothScanningPermissionDialog.h;
                if (j != 0) {
                    N.MdX25cA2(j, 0);
                }
                bluetoothScanningPermissionDialog.c.setOnDismissListener(null);
                bluetoothScanningPermissionDialog.c.dismiss();
                break;
            default:
                jx0 jx0Var = (jx0) this.f;
                int i11 = jx0.e;
                jx0Var.dismiss();
                break;
        }
    }
}
