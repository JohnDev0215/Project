package defpackage;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.d;
import java.util.LinkedHashMap;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.privacy.settings.PrivacySettings;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.chrome.browser.tracing.TracingNotificationService;
import org.chromium.chrome.browser.tracing.settings.TracingSettings;
import org.chromium.components.browser_ui.accessibility.AccessibilitySettings;
import org.chromium.components.page_info.PageInfoCookiesPreference;
import org.chromium.components.page_info.R$id;
import org.chromium.components.page_info.R$layout;
import org.chromium.components.page_info.R$string;
import org.chromium.components.page_info.R$style;
import org.chromium.content.browser.TracingControllerAndroidImpl;
import te3.a;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class j52 implements Preference.d {
    public final /* synthetic */ int e;
    public final /* synthetic */ d f;

    public /* synthetic */ j52(d dVar, int i) {
        this.e = i;
        this.f = dVar;
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        int i = this.e;
        d dVar = this.f;
        switch (i) {
            case 0:
                int i2 = PrivacySettings.i0;
                PrivacySandboxSettingsBaseFragment.M0(((PrivacySettings) dVar).K(), new n3(), 0);
                break;
            case 1:
                TracingSettings tracingSettings = (TracingSettings) dVar;
                LinkedHashMap linkedHashMap = TracingSettings.n0;
                tracingSettings.getClass();
                te3 te3VarA = te3.a();
                te3VarA.getClass();
                te3VarA.a = new TracingControllerAndroidImpl(xz.a);
                te3VarA.b(2);
                Context context = xz.a;
                ue3.b = 0;
                String str = String.format("Trace buffer usage: %s%%", 0);
                AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
                if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
                    str = "Tracing is active.";
                }
                np1 np1VarA = ue3.a();
                np1VarA.k("Chrome trace is being recorded");
                np1VarA.j(str);
                np1VarA.o(true);
                int i3 = R$drawable.ic_stop_white_24dp;
                Intent intent = new Intent(context, (Class<?>) TracingNotificationService.class);
                intent.setAction("org.chromium.chrome.browser.tracing.STOP_RECORDING");
                np1VarA.d(i3, "Stop recording", PendingIntent.getService(context, 0, intent, 134217728 | q11.d(false)));
                ue3.a = np1VarA;
                ue3.b(np1VarA.e());
                te3VarA.new a().c(i9.e);
                tracingSettings.P0();
                break;
            case 2:
                AccessibilitySettings accessibilitySettings = (AccessibilitySettings) dVar;
                int i4 = AccessibilitySettings.r0;
                accessibilitySettings.getClass();
                Intent intent2 = new Intent("android.settings.CAPTIONING_SETTINGS");
                intent2.addFlags(268435456);
                accessibilitySettings.F0(intent2);
                break;
            default:
                PageInfoCookiesPreference pageInfoCookiesPreference = (PageInfoCookiesPreference) dVar;
                if (!pageInfoCookiesPreference.m0 && pageInfoCookiesPreference.n0 && pageInfoCookiesPreference.K() != null) {
                    View viewInflate = LayoutInflater.from(pageInfoCookiesPreference.K()).inflate(R$layout.dialog_delete_cookies, (ViewGroup) null);
                    Button button = (Button) viewInflate.findViewById(R$id.btn_cancel);
                    Button button2 = (Button) viewInflate.findViewById(R$id.btn_sure);
                    ((TextView) viewInflate.findViewById(R$id.tv_message)).setText(pageInfoCookiesPreference.P(R$string.page_info_cookies_clear_confirmation, pageInfoCookiesPreference.o0));
                    Dialog dialog = new Dialog(pageInfoCookiesPreference.K(), R$style.Dialog_Style);
                    dialog.setContentView(viewInflate);
                    dialog.setCancelable(false);
                    Window window = dialog.getWindow();
                    window.setGravity(17);
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    DisplayMetrics displayMetrics = pageInfoCookiesPreference.K().getResources().getDisplayMetrics();
                    attributes.width = (Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) * 78) / 100;
                    attributes.height = -2;
                    window.setAttributes(attributes);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    button.setOnClickListener(new e11(dialog, 1));
                    button2.setOnClickListener(new gs(21, pageInfoCookiesPreference));
                }
                break;
        }
        return true;
    }
}
