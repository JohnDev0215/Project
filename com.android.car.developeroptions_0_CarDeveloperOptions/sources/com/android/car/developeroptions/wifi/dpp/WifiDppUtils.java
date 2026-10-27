package com.android.car.developeroptions.wifi.dpp;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.settingslib.wifi.AccessPoint;
import java.time.Duration;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class WifiDppUtils {
    private static final Duration VIBRATE_DURATION_QR_CODE_RECOGNITION = Duration.ofMillis(3);

    public static boolean isWifiDppEnabled(Context context) {
        return ((WifiManager) context.getSystemService(WifiManager.class)).isEasyConnectSupported();
    }

    public static Intent getEnrolleeQrCodeScannerIntent(String str) {
        Intent intent = new Intent("android.settings.WIFI_DPP_ENROLLEE_QR_CODE_SCANNER");
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("ssid", str);
        }
        return intent;
    }

    private static String getPresharedKey(WifiManager wifiManager, WifiConfiguration wifiConfiguration) {
        String str = wifiConfiguration.preSharedKey;
        for (WifiConfiguration wifiConfiguration2 : wifiManager.getPrivilegedConfiguredNetworks()) {
            if (wifiConfiguration2.networkId == wifiConfiguration.networkId) {
                return wifiConfiguration2.preSharedKey;
            }
        }
        return str;
    }

    private static String removeFirstAndLastDoubleQuotes(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int length = str.length() - 1;
        int i = str.charAt(0) == '\"' ? 1 : 0;
        if (str.charAt(length) == '\"') {
            length--;
        }
        return str.substring(i, length + 1);
    }

    static String getSecurityString(WifiConfiguration wifiConfiguration) {
        return wifiConfiguration.allowedKeyManagement.get(8) ? "SAE" : wifiConfiguration.allowedKeyManagement.get(9) ? "nopass" : (wifiConfiguration.allowedKeyManagement.get(1) || wifiConfiguration.allowedKeyManagement.get(4)) ? "WPA" : wifiConfiguration.wepKeys[0] == null ? "nopass" : "WEP";
    }

    private static String getSecurityString(SoftApConfiguration softApConfiguration) {
        int securityType = softApConfiguration.getSecurityType();
        return securityType != 1 ? securityType != 3 ? "nopass" : "SAE" : "WPA";
    }

    public static Intent getConfiguratorQrCodeGeneratorIntentOrNull(Context context, WifiManager wifiManager, AccessPoint accessPoint) {
        Intent intent = new Intent(context, (Class<?>) WifiDppConfiguratorActivity.class);
        if (!isSupportConfiguratorQrCodeGenerator(context, accessPoint)) {
            return null;
        }
        intent.setAction("android.settings.WIFI_DPP_CONFIGURATOR_QR_CODE_GENERATOR");
        setConfiguratorIntentExtra(intent, wifiManager, accessPoint.getConfig());
        return intent;
    }

    public static Intent getConfiguratorQrCodeScannerIntentOrNull(Context context, WifiManager wifiManager, AccessPoint accessPoint) {
        Intent intent = new Intent(context, (Class<?>) WifiDppConfiguratorActivity.class);
        if (!isSupportConfiguratorQrCodeScanner(context, accessPoint)) {
            return null;
        }
        intent.setAction("android.settings.WIFI_DPP_CONFIGURATOR_QR_CODE_SCANNER");
        WifiConfiguration config = accessPoint.getConfig();
        setConfiguratorIntentExtra(intent, wifiManager, config);
        int i = config.networkId;
        if (i == -1) {
            throw new IllegalArgumentException("Invalid network ID");
        }
        intent.putExtra("networkId", i);
        return intent;
    }

    public static Intent getHotspotConfiguratorIntentOrNull(Context context, SoftApConfiguration softApConfiguration) {
        Intent intent = new Intent(context, (Class<?>) WifiDppConfiguratorActivity.class);
        if (!isSupportHotspotConfiguratorQrCodeGenerator(softApConfiguration)) {
            return null;
        }
        intent.setAction("android.settings.WIFI_DPP_CONFIGURATOR_QR_CODE_GENERATOR");
        setConfiguratorIntentExtra(intent, softApConfiguration);
        intent.putExtra("networkId", -1);
        intent.putExtra("isHotspot", true);
        return intent;
    }

    private static void setConfiguratorIntentExtra(Intent intent, WifiManager wifiManager, WifiConfiguration wifiConfiguration) {
        String strRemoveFirstAndLastDoubleQuotes = removeFirstAndLastDoubleQuotes(wifiConfiguration.SSID);
        String securityString = getSecurityString(wifiConfiguration);
        String strRemoveFirstAndLastDoubleQuotes2 = wifiConfiguration.preSharedKey;
        if (strRemoveFirstAndLastDoubleQuotes2 != null) {
            strRemoveFirstAndLastDoubleQuotes2 = removeFirstAndLastDoubleQuotes(getPresharedKey(wifiManager, wifiConfiguration));
        }
        if (!TextUtils.isEmpty(strRemoveFirstAndLastDoubleQuotes)) {
            intent.putExtra("ssid", strRemoveFirstAndLastDoubleQuotes);
        }
        if (!TextUtils.isEmpty(securityString)) {
            intent.putExtra("security", securityString);
        }
        if (TextUtils.isEmpty(strRemoveFirstAndLastDoubleQuotes2)) {
            return;
        }
        intent.putExtra("preSharedKey", strRemoveFirstAndLastDoubleQuotes2);
    }

    private static void setConfiguratorIntentExtra(Intent intent, SoftApConfiguration softApConfiguration) {
        String ssid = softApConfiguration.getSsid();
        String securityString = getSecurityString(softApConfiguration);
        String passphrase = softApConfiguration.getPassphrase();
        if (!TextUtils.isEmpty(ssid)) {
            intent.putExtra("ssid", ssid);
        }
        if (!TextUtils.isEmpty(securityString)) {
            intent.putExtra("security", securityString);
        }
        if (TextUtils.isEmpty(passphrase)) {
            return;
        }
        intent.putExtra("preSharedKey", passphrase);
    }

    public static void showLockScreen(Context context, final Runnable runnable) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        if (keyguardManager.isKeyguardSecure()) {
            BiometricPrompt.AuthenticationCallback authenticationCallback = new BiometricPrompt.AuthenticationCallback() { // from class: com.android.car.developeroptions.wifi.dpp.WifiDppUtils.1
                @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
                public void onAuthenticationError(int i, CharSequence charSequence) {
                }

                @Override // android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
                public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
                    runnable.run();
                }
            };
            BiometricPrompt.Builder title = new BiometricPrompt.Builder(context).setTitle(context.getText(R.string.wifi_dpp_lockscreen_title));
            if (keyguardManager.isDeviceSecure()) {
                title.setDeviceCredentialAllowed(true);
            }
            BiometricPrompt biometricPromptBuild = title.build();
            final Handler handler = new Handler(Looper.getMainLooper());
            biometricPromptBuild.authenticate(new CancellationSignal(), new Executor() { // from class: com.android.car.developeroptions.wifi.dpp.-$$Lambda$WifiDppUtils$3Ca_i_22V5SVpyJ7-8uJ3X1X-aU
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable runnable2) {
                    handler.post(runnable2);
                }
            }, authenticationCallback);
            return;
        }
        runnable.run();
    }

    public static boolean isSupportConfiguratorQrCodeScanner(Context context, AccessPoint accessPoint) {
        return isSupportWifiDpp(context, accessPoint.getSecurity());
    }

    public static boolean isSupportConfiguratorQrCodeGenerator(Context context, AccessPoint accessPoint) {
        return isSupportZxing(context, accessPoint.getSecurity());
    }

    public static boolean isSupportEnrolleeQrCodeScanner(Context context, int i) {
        return isSupportWifiDpp(context, i) || isSupportZxing(context, i);
    }

    private static boolean isSupportHotspotConfiguratorQrCodeGenerator(SoftApConfiguration softApConfiguration) {
        int securityType = softApConfiguration.getSecurityType();
        return securityType == 1 || securityType == 0;
    }

    private static boolean isSupportWifiDpp(Context context, int i) {
        if (!isWifiDppEnabled(context)) {
            return false;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService(WifiManager.class);
        if (i != 2) {
            return i == 5 && wifiManager.isWpa3SaeSupported();
        }
        return true;
    }

    private static boolean isSupportZxing(Context context, int i) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(WifiManager.class);
        if (i == 0 || i == 1 || i == 2) {
            return true;
        }
        return i != 4 ? i == 5 && wifiManager.isWpa3SaeSupported() : wifiManager.isEnhancedOpenSupported();
    }

    static void triggerVibrationForQrCodeRecognition(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        if (vibrator == null) {
            return;
        }
        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATE_DURATION_QR_CODE_RECOGNITION.toMillis(), -1));
    }
}
