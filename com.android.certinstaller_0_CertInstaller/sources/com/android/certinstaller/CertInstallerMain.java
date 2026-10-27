package com.android.certinstaller;

import android.app.ActivityTaskManager;
import android.app.KeyguardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserManager;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes.dex */
public class CertInstallerMain extends PreferenceActivity {
    private static Map<String, String> MIME_MAPPINGS;

    static {
        HashMap map = new HashMap();
        MIME_MAPPINGS = map;
        map.put("application/x-x509-ca-cert", "CERT");
        MIME_MAPPINGS.put("application/x-x509-user-cert", "CERT");
        MIME_MAPPINGS.put("application/x-x509-server-cert", "CERT");
        MIME_MAPPINGS.put("application/x-pem-file", "CERT");
        MIME_MAPPINGS.put("application/pkix-cert", "CERT");
        MIME_MAPPINGS.put("application/x-pkcs12", "PKCS12");
        MIME_MAPPINGS.put("application/x-wifi-config", "wifi-config");
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setResult(0);
        UserManager userManager = (UserManager) getSystemService("user");
        if (userManager.hasUserRestriction("no_config_credentials") || userManager.isGuestUser()) {
            finish();
            return;
        }
        Intent intent = getIntent();
        String action = intent.getAction();
        if ("android.credentials.INSTALL".equals(action) || "android.credentials.INSTALL_AS_USER".equals(action)) {
            Bundle extras = intent.getExtras();
            String className = intent.getComponent().getClassName();
            String str = getPackageName() + ".InstallCertAsUser";
            if (extras != null && !str.equals(className)) {
                extras.remove("install_as_uid");
            }
            if (nullOrEmptyBundle(extras) || bundleContainsNameOnly(extras) || bundleContainsInstallAsUidOnly(extras) || bundleContainsExtraCertificateUsageOnly(extras)) {
                if (installingCaCertificate(extras)) {
                    confirmDeviceCredential();
                    return;
                } else {
                    startOpenDocumentActivity();
                    return;
                }
            }
            startInstallActivity(intent);
            return;
        }
        if ("android.intent.action.VIEW".equals(action)) {
            startInstallActivity(intent.getType(), intent.getData());
        }
    }

    private boolean nullOrEmptyBundle(Bundle bundle) {
        return bundle == null || bundle.isEmpty();
    }

    private boolean bundleContainsNameOnly(Bundle bundle) {
        return bundle.size() == 1 && bundle.containsKey("name");
    }

    private boolean bundleContainsInstallAsUidOnly(Bundle bundle) {
        return bundle.size() == 1 && bundle.containsKey("install_as_uid");
    }

    private boolean bundleContainsExtraCertificateUsageOnly(Bundle bundle) {
        return bundle.size() == 1 && bundle.containsKey("certificate_install_usage");
    }

    private boolean installingCaCertificate(Bundle bundle) {
        return bundle != null && bundle.size() == 1 && "ca".equals(bundle.getString("certificate_install_usage"));
    }

    private void confirmDeviceCredential() {
        Intent intentCreateConfirmDeviceCredentialIntent = ((KeyguardManager) getSystemService(KeyguardManager.class)).createConfirmDeviceCredentialIntent(null, null);
        if (intentCreateConfirmDeviceCredentialIntent == null) {
            startOpenDocumentActivity();
        } else {
            startActivityForResult(intentCreateConfirmDeviceCredentialIntent, 3);
        }
    }

    private static byte[] readWithLimit(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        int i = 0;
        do {
            int i2 = inputStream.read(bArr);
            if (i2 != -1) {
                byteArrayOutputStream.write(bArr, 0, i2);
                i += i2;
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        } while (i <= 10485760);
        throw new IOException("Data file exceeded maximum size.");
    }

    private void startInstallActivity(Intent intent) {
        Intent intent2 = new Intent(this, (Class<?>) CertInstaller.class);
        if (intent.getExtras() != null && intent.getExtras().getString("android.intent.extra.REFERRER") != null) {
            Log.v("CertInstaller", String.format("Removing referrer extra with value %s which was not meant to be included", intent.getBundleExtra("android.intent.extra.REFERRER")));
            intent.removeExtra("android.intent.extra.REFERRER");
        }
        intent2.putExtras(intent);
        try {
            intent2.putExtra("android.intent.extra.REFERRER", ActivityTaskManager.getService().getLaunchedFromPackage(getActivityToken()));
            startActivityForResult(intent2, 1);
        } catch (RemoteException e) {
            Log.v("CertInstaller", "Could not talk to activity manager.", e);
            Toast.makeText(this, R.string.cert_temp_error, 1).show();
            finish();
        }
    }

    private void startInstallActivity(String str, Uri uri) {
        if (str == null) {
            str = getContentResolver().getType(uri);
        }
        String str2 = MIME_MAPPINGS.get(str);
        if (str2 == null) {
            throw new IllegalArgumentException("Unknown MIME type: " + str);
        }
        if ("wifi-config".equals(str2)) {
            startWifiInstallActivity(str, uri);
            return;
        }
        InputStream inputStreamOpenInputStream = null;
        try {
            try {
                inputStreamOpenInputStream = getContentResolver().openInputStream(uri);
                byte[] withLimit = readWithLimit(inputStreamOpenInputStream);
                Intent intent = getIntent();
                intent.putExtra(str2, withLimit);
                startInstallActivity(intent);
            } catch (IOException e) {
                Log.e("CertInstaller", "Failed to read certificate: " + e);
                Toast.makeText(this, R.string.cert_read_error, 1).show();
            }
        } finally {
            IoUtils.closeQuietly(inputStreamOpenInputStream);
        }
    }

    private void startWifiInstallActivity(String str, Uri uri) {
        Intent intent = new Intent(this, (Class<?>) WiFiInstaller.class);
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(getContentResolver().openInputStream(uri));
            try {
                byte[] withLimit = readWithLimit(bufferedInputStream);
                intent.putExtra("wifi-config-file", uri.toString());
                intent.putExtra("wifi-config-data", withLimit);
                intent.putExtra("wifi-config", str);
                startActivityForResult(intent, 1);
                bufferedInputStream.close();
            } finally {
            }
        } catch (IOException e) {
            Log.e("CertInstaller", "Failed to read wifi config: " + e);
            Toast.makeText(this, R.string.cert_read_error, 1).show();
        }
    }

    private void startOpenDocumentActivity() {
        String[] strArr = (String[]) MIME_MAPPINGS.keySet().toArray(new String[0]);
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.MIME_TYPES", strArr);
        intent.putExtra("android.provider.extra.SHOW_ADVANCED", true);
        startActivityForResult(intent, 2);
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            setResult(i2);
            finish();
            return;
        }
        if (i == 2) {
            if (i2 == -1) {
                startInstallActivity(null, intent.getData());
                return;
            } else {
                finish();
                return;
            }
        }
        if (i != 3) {
            Log.w("CertInstaller", "unknown request code: " + i);
            return;
        }
        if (i2 == -1) {
            startOpenDocumentActivity();
        } else {
            finish();
        }
    }
}
