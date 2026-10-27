package org.chromium.chrome.browser.webauth.authenticator;

import android.R;
import android.content.Intent;
import android.hardware.usb.UsbAccessory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import androidx.fragment.app.a;
import defpackage.lp0;
import defpackage.sp;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ChromeBaseAppCompatActivity;
import org.chromium.chrome.browser.webauthn.CableAuthenticatorModuleProvider;

/* JADX INFO: loaded from: classes2.dex */
public class CableAuthenticatorActivity extends ChromeBaseAppCompatActivity {
    public static final /* synthetic */ int I = 0;

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        setTitle(getResources().getString(R$string.cablev2_paask_title));
        sp.a().d(false);
        super.onCreate(bundle);
        onNewIntent(getIntent());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onNewIntent(Intent intent) {
        Bundle bundleExtra;
        super.onNewIntent(intent);
        if (intent.getAction() != null && intent.getAction().equals("android.hardware.usb.action.USB_ACCESSORY_ATTACHED")) {
            UsbAccessory usbAccessory = (UsbAccessory) intent.getParcelableExtra("accessory");
            bundleExtra = new Bundle();
            bundleExtra.putParcelable("accessory", usbAccessory);
        } else if (intent.getAction() != null && intent.getAction().equals("android.intent.action.VIEW") && intent.getData() != null) {
            bundleExtra = new Bundle();
            bundleExtra.putParcelable("org.chromium.chrome.browser.webauth.authenticator.QR", intent.getData());
        } else if (intent.hasExtra("org.chromium.chrome.browser.webauth.authenticator.ServerLink")) {
            String stringExtra = intent.getStringExtra("org.chromium.chrome.browser.webauth.authenticator.ServerLink");
            bundleExtra = new Bundle();
            try {
                bundleExtra.putByteArray("org.chromium.chrome.browser.webauth.authenticator.ServerLink", Base64.decode(stringExtra, 0));
            } catch (IllegalArgumentException unused) {
                Log.i("cr_CableAuthenticatorActivity", "Invalid base64 in ServerLink argument");
                return;
            }
        } else {
            bundleExtra = intent.getBundleExtra("show_fragment_args");
        }
        CableAuthenticatorModuleProvider cableAuthenticatorModuleProvider = new CableAuthenticatorModuleProvider();
        cableAuthenticatorModuleProvider.C0(bundleExtra);
        lp0 lp0VarX1 = x1();
        lp0VarX1.getClass();
        a aVar = new a(lp0VarX1);
        aVar.e(R.id.content, cableAuthenticatorModuleProvider, null);
        aVar.g();
    }
}
