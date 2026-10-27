package com.byd.vrdownload;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.InputDeviceCompat;
import com.byd.vrdownload.utils.LogUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends Activity {
    private static final String TAG = "D-MainActivity";
    IDownloadServiceHandler handler;
    boolean isAllow;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("source");
        boolean booleanExtra = intent.getBooleanExtra("requestPermission", false);
        LogUtils.i(TAG, "onCreate: source=" + stringExtra + ",requestPermission=" + booleanExtra);
        if (booleanExtra) {
            requestPermission(stringExtra);
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Log.e(TAG, "onActivityResult:" + i);
        if (Build.VERSION.SDK_INT >= 30) {
            boolean z = !Environment.isExternalStorageManager();
            this.isAllow = z;
            dealPermissionResult(i, z);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        try {
            if (checkPermission()) {
                finish();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    public boolean checkPermission() throws RemoteException {
        if (Build.VERSION.SDK_INT >= 30) {
            return Environment.isExternalStorageManager();
        }
        String[] strArr = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= 2) {
                z = true;
                break;
            }
            String str = strArr[i];
            int iCheckSelfPermission = ContextCompat.checkSelfPermission(this, str);
            Log.d(TAG, "checkPermission=" + String.format("%s - %d", str, Integer.valueOf(iCheckSelfPermission)));
            if (iCheckSelfPermission != 0) {
                break;
            }
            i++;
        }
        LogUtils.i(TAG, "checkPermission: hasPermission=" + z);
        return z;
    }

    protected void requestPermission(String str) {
        if (App.CONTEXT != null) {
            this.handler = DownloadServiceHandler.getInstance(App.CONTEXT);
        }
        int i = str.equals("self") ? 1024 : InputDeviceCompat.SOURCE_GAMEPAD;
        if (Build.VERSION.SDK_INT >= 30) {
            LogUtils.e(TAG, "requestPermission >=30," + i);
            if (Environment.isExternalStorageManager()) {
                return;
            }
            Intent intent = new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, i);
            return;
        }
        LogUtils.e(TAG, "requestPermission >=23," + i);
        String[] strArr = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        for (int i2 = 0; i2 < 2; i2++) {
            String str2 = strArr[i2];
            int iCheckSelfPermission = ContextCompat.checkSelfPermission(this, str2);
            LogUtils.d(TAG, String.format(Locale.getDefault(), "%s - %d", str2, Integer.valueOf(iCheckSelfPermission)));
            if (iCheckSelfPermission != 0) {
                requestPermissions(strArr, i);
                return;
            }
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        LogUtils.e(TAG, "onRequestPermissionsResult");
        dealPermissionResult(i, isPermisionGranted(iArr));
    }

    private boolean isPermisionGranted(int[] iArr) {
        for (int i : iArr) {
            if (i == -1) {
                return false;
            }
        }
        return true;
    }

    private void dealPermissionResult(int i, boolean z) {
        IDownloadServiceHandler iDownloadServiceHandler;
        if (!z && !ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            Toast.makeText(this, getString(R.string.no_permission_and_not_tip), 0).show();
        }
        if (i == 1025 && (iDownloadServiceHandler = this.handler) != null) {
            iDownloadServiceHandler.notifyPermissionResult(z);
        }
        LogUtils.e(TAG, "回调后finish页面");
        finish();
    }
}
