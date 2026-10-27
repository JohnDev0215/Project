package com.android.bluetooth.opp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import com.android.bluetooth.R;
import com.android.vcard.VCardConfig;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppLauncherActivity extends Activity {
    private static final boolean D = false;
    private static final Pattern PLAIN_TEXT_TO_ESCAPE = Pattern.compile("[<>&]| {2,}|\r?\n");
    private static final String TAG = "BluetoothOppLauncherActivity";
    private static final boolean V = false;

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) throws Throwable {
        super.onCreate(savedInstanceState);
        getWindow().addPrivateFlags(524288);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (action == null) {
            Log.w(TAG, " Received " + intent + " with null action");
            finish();
            return;
        }
        if (action.equals("android.intent.action.SEND") || action.equals("android.intent.action.SEND_MULTIPLE")) {
            if (!isBluetoothAllowed()) {
                Intent in = new Intent(this, (Class<?>) BluetoothOppBtErrorActivity.class);
                in.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
                in.putExtra("title", getString(R.string.airplane_error_title));
                in.putExtra("content", getString(R.string.airplane_error_msg));
                startActivity(in);
                finish();
                return;
            }
            if (action.equals("android.intent.action.SEND")) {
                final String type = intent.getType();
                final Uri stream = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
                CharSequence extraText = intent.getCharSequenceExtra("android.intent.extra.TEXT");
                if (stream != null && type != null) {
                    Thread t = new Thread(new Runnable() { // from class: com.android.bluetooth.opp.BluetoothOppLauncherActivity.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BluetoothOppLauncherActivity.this.sendFileInfo(type, stream.toString(), false, true);
                        }
                    });
                    t.start();
                    return;
                }
                if (extraText == null || type == null) {
                    Log.e(TAG, "type is null; or sending file URI is null");
                    finish();
                    return;
                }
                final Uri fileUri = creatFileForSharedContent(createCredentialProtectedStorageContext(), extraText);
                if (fileUri == null) {
                    Log.w(TAG, "Error trying to do set text...File not created!");
                    finish();
                    return;
                } else {
                    Thread t2 = new Thread(new Runnable() { // from class: com.android.bluetooth.opp.BluetoothOppLauncherActivity.2
                        @Override // java.lang.Runnable
                        public void run() {
                            BluetoothOppLauncherActivity.this.sendFileInfo(type, fileUri.toString(), false, false);
                        }
                    });
                    t2.start();
                    return;
                }
            }
            if (action.equals("android.intent.action.SEND_MULTIPLE")) {
                final String mimeType = intent.getType();
                final ArrayList<Uri> uris = intent.getParcelableArrayListExtra("android.intent.extra.STREAM");
                if (mimeType == null || uris == null) {
                    Log.e(TAG, "type is null; or sending files URIs are null");
                    finish();
                    return;
                } else {
                    Thread t3 = new Thread(new Runnable() { // from class: com.android.bluetooth.opp.BluetoothOppLauncherActivity.3
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                BluetoothOppManager.getInstance(BluetoothOppLauncherActivity.this).saveSendingFileInfo(mimeType, uris, false, true);
                                BluetoothOppLauncherActivity.this.launchDevicePicker();
                                BluetoothOppLauncherActivity.this.finish();
                            } catch (IllegalArgumentException exception) {
                                BluetoothOppLauncherActivity.this.showToast(exception.getMessage());
                                BluetoothOppLauncherActivity.this.finish();
                            }
                        }
                    });
                    t3.start();
                    return;
                }
            }
            return;
        }
        if (action.equals("android.btopp.intent.action.OPEN")) {
            Uri uri = getIntent().getData();
            Intent intent1 = new Intent();
            intent1.setAction(action);
            intent1.setClassName("com.android.bluetooth", BluetoothOppReceiver.class.getName());
            intent1.setDataAndNormalize(uri);
            sendBroadcast(intent1);
            finish();
            return;
        }
        Log.w(TAG, "Unsupported action: " + action);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchDevicePicker() {
        if (!BluetoothOppManager.getInstance(this).isEnabled()) {
            Intent in = new Intent(this, (Class<?>) BluetoothOppBtEnableActivity.class);
            in.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
            startActivity(in);
            return;
        }
        Intent in1 = new Intent("android.bluetooth.devicepicker.action.LAUNCH");
        in1.setFlags(8388608);
        in1.putExtra("android.bluetooth.devicepicker.extra.NEED_AUTH", false);
        in1.putExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 2);
        in1.putExtra("android.bluetooth.devicepicker.extra.LAUNCH_PACKAGE", "com.android.bluetooth");
        in1.putExtra("android.bluetooth.devicepicker.extra.DEVICE_PICKER_LAUNCH_CLASS", BluetoothOppReceiver.class.getName());
        startActivity(in1);
    }

    private boolean isBluetoothAllowed() {
        ContentResolver resolver = getContentResolver();
        boolean isAirplaneModeOn = Settings.System.getInt(resolver, "airplane_mode_on", 0) == 1;
        if (!isAirplaneModeOn) {
            return true;
        }
        String airplaneModeRadios = Settings.System.getString(resolver, "airplane_mode_radios");
        boolean isAirplaneSensitive = airplaneModeRadios == null || airplaneModeRadios.contains("bluetooth");
        if (!isAirplaneSensitive) {
            return true;
        }
        String airplaneModeToggleableRadios = Settings.System.getString(resolver, "airplane_mode_toggleable_radios");
        boolean isAirplaneToggleable = airplaneModeToggleableRadios != null && airplaneModeToggleableRadios.contains("bluetooth");
        return isAirplaneToggleable;
    }

    /* JADX WARN: Removed duplicated region for block: B:76:0x01de A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.net.Uri creatFileForSharedContent(android.content.Context r18, java.lang.CharSequence r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 491
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.opp.BluetoothOppLauncherActivity.creatFileForSharedContent(android.content.Context, java.lang.CharSequence):android.net.Uri");
    }

    private static String escapeCharacterToDisplay(String text) {
        Pattern pattern = PLAIN_TEXT_TO_ESCAPE;
        Matcher match = pattern.matcher(text);
        if (match.find()) {
            StringBuilder out = new StringBuilder();
            int end = 0;
            do {
                int start = match.start();
                out.append(text.substring(end, start));
                end = match.end();
                int c = text.codePointAt(start);
                if (c == 32) {
                    int n = end - start;
                    for (int i = 1; i < n; i++) {
                        out.append("&nbsp;");
                    }
                    out.append(' ');
                } else if (c == 13 || c == 10) {
                    out.append("<br>");
                } else if (c == 60) {
                    out.append("&lt;");
                } else if (c == 62) {
                    out.append("&gt;");
                } else if (c == 38) {
                    out.append("&amp;");
                }
            } while (match.find());
            out.append(text.substring(end));
            return out.toString();
        }
        return text;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFileInfo(String mimeType, String uriString, boolean isHandover, boolean fromExternal) {
        BluetoothOppManager manager = BluetoothOppManager.getInstance(getApplicationContext());
        try {
            manager.saveSendingFileInfo(mimeType, uriString, isHandover, fromExternal);
            launchDevicePicker();
            finish();
        } catch (IllegalArgumentException exception) {
            showToast(exception.getMessage());
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToast(final String msg) {
        runOnUiThread(new Runnable() { // from class: com.android.bluetooth.opp.BluetoothOppLauncherActivity.4
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(BluetoothOppLauncherActivity.this.getApplicationContext(), msg, 0).show();
            }
        });
    }
}
