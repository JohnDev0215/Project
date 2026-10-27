package com.android.certinstaller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.net.wifi.hotspot2.ConfigParser;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class WiFiInstaller extends Activity {
    private boolean mIsPasspointConfigurationValid;
    private PasspointConfiguration mPasspointConfig;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addSystemFlags(524288);
        this.mIsPasspointConfigurationValid = false;
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.e("WifiInstaller", "Invalid inputs");
            return;
        }
        String string = extras.getString("wifi-config-file");
        String string2 = extras.getString("wifi-config");
        byte[] byteArray = extras.getByteArray("wifi-config-data");
        StringBuilder sb = new StringBuilder();
        sb.append("WiFi data for wifi-config: ");
        sb.append(string2);
        sb.append(" is ");
        sb.append(byteArray != null ? Integer.valueOf(byteArray.length) : "-");
        Log.d("WifiInstaller", sb.toString());
        if (byteArray == null || byteArray.length == 0 || TextUtils.isEmpty(string)) {
            Log.e("WifiInstaller", "Invalid inputs");
            return;
        }
        if (!TextUtils.equals(string2, "application/x-wifi-config")) {
            Log.e("WifiInstaller", "Unexpected MIME type: " + string2);
            EventLog.writeEvent(1397638484, "176756691", -1, "Invalid mime-type");
            return;
        }
        PasspointConfiguration passpointConfig = ConfigParser.parsePasspointConfig(string2, byteArray);
        this.mPasspointConfig = passpointConfig;
        if (passpointConfig == null) {
            Log.e("WifiInstaller", "Failed to build Passpoint configuration");
            EventLog.writeEvent(1397638484, "176756691", -1, "Invalid data in file " + string);
            return;
        }
        if (passpointConfig.getHomeSp() == null) {
            Log.e("WifiInstaller", "Passpoint profile missing HomeSP information");
        } else {
            this.mIsPasspointConfigurationValid = true;
        }
        dropFile(Uri.parse(string), getApplicationContext());
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        createMainDialog();
    }

    private void createMainDialog() {
        Resources resources = getResources();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewInflate = getLayoutInflater().inflate(R.layout.wifi_main_dialog, (ViewGroup) null);
        builder.setView(viewInflate);
        TextView textView = (TextView) viewInflate.findViewById(R.id.wifi_info);
        if (this.mIsPasspointConfigurationValid) {
            final WifiManager wifiManager = (WifiManager) getSystemService(WifiManager.class);
            textView.setText(String.format(getResources().getString(R.string.wifi_installer_detail), this.mPasspointConfig.getHomeSp().getFriendlyName()));
            builder.setTitle(this.mPasspointConfig.getHomeSp().getFriendlyName());
            builder.setIcon(resources.getDrawable(R.drawable.signal_wifi_4_bar_lock_black_24dp));
            builder.setPositiveButton(R.string.wifi_install_label, new DialogInterface.OnClickListener() { // from class: com.android.certinstaller.WiFiInstaller.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    WiFiInstaller wiFiInstaller = WiFiInstaller.this;
                    Toast.makeText(wiFiInstaller, wiFiInstaller.getString(R.string.wifi_installing_label), 1).show();
                    AsyncTask.execute(new Runnable() { // from class: com.android.certinstaller.WiFiInstaller.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            boolean z;
                            try {
                                wifiManager.removePasspointConfiguration(WiFiInstaller.this.mPasspointConfig.getHomeSp().getFqdn());
                            } catch (IllegalArgumentException unused) {
                            }
                            try {
                                wifiManager.addOrUpdatePasspointConfiguration(WiFiInstaller.this.mPasspointConfig);
                                z = true;
                            } catch (RuntimeException e) {
                                Log.w("WifiInstaller", "Caught exception while installing wifi config: " + e, e);
                                z = false;
                            }
                            if (z) {
                                Intent intent = new Intent(WiFiInstaller.this.getApplicationContext(), (Class<?>) CredentialsInstallDialog.class);
                                intent.putExtra("network_name", WiFiInstaller.this.mPasspointConfig.getHomeSp().getFriendlyName());
                                intent.putExtra("install_state", 2);
                                WiFiInstaller.this.startActivity(intent);
                            } else {
                                Intent intent2 = new Intent(WiFiInstaller.this.getApplicationContext(), (Class<?>) CredentialsInstallDialog.class);
                                intent2.putExtra("install_state", 1);
                                WiFiInstaller.this.startActivity(intent2);
                            }
                            WiFiInstaller.this.finish();
                        }
                    });
                    dialogInterface.dismiss();
                }
            });
            builder.setNegativeButton(R.string.wifi_cancel_label, new DialogInterface.OnClickListener() { // from class: com.android.certinstaller.WiFiInstaller.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    WiFiInstaller.this.finish();
                }
            });
        } else {
            textView.setText(getResources().getString(R.string.wifi_installer_download_error));
            builder.setPositiveButton(R.string.done_label, new DialogInterface.OnClickListener() { // from class: com.android.certinstaller.WiFiInstaller.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    WiFiInstaller.this.finish();
                }
            });
        }
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.show();
        alertDialogCreate.getWindow().addSystemFlags(524288);
    }

    private static void dropFile(Uri uri, Context context) {
        try {
            if (DocumentsContract.isDocumentUri(context, uri)) {
                DocumentsContract.deleteDocument(context.getContentResolver(), uri);
            } else {
                context.getContentResolver().delete(uri, null, null);
            }
        } catch (Exception unused) {
            Log.e("WifiInstaller", "could not delete document " + uri);
        }
    }
}
