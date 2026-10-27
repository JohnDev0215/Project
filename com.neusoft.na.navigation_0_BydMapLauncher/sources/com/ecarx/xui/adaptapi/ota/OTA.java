package com.ecarx.xui.adaptapi.ota;

import android.content.Context;
import android.os.Build;
import android.os.RecoverySystem;
import android.os.SystemProperties;
import android.util.Log;
import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.sign.SignVerify;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
public class OTA extends AdaptAPI {
    private static final String TAG = "neuapi";
    public static final int UPDATE_FAILED_REASON_DEFAULT = 0;
    public static final int UPDATE_FAILED_REASON_INSUFFICIENT_STORAGE = 2;
    public static final int UPDATE_FAILED_REASON_INVALID_PACKAGE = 1;
    private static Context mContext;
    private File file;
    private OtaSession session;
    private boolean verify = false;
    RecoverySystem.ProgressListener _recoveryVerifyListener = new RecoverySystem.ProgressListener() { // from class: com.ecarx.xui.adaptapi.ota.OTA.1
        @Override // android.os.RecoverySystem.ProgressListener
        public void onProgress(int i) {
            if (i == 100) {
                OTA.this.verify = true;
                try {
                    RecoverySystem.installPackage(OTA.mContext, OTA.this.file);
                    Log.d(OTA.TAG, "RecoverySystem.installPackage");
                } catch (IOException e) {
                    Log.d(OTA.TAG, e.toString());
                }
            }
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    @interface UpdateFailedReason {
    }

    public boolean supportNoRecoveryOta() {
        return false;
    }

    public static OTA create(Context context) {
        if (!SignVerify.instance().appVerify(context)) {
            return null;
        }
        mContext = context;
        return new OTA();
    }

    public IOtaSession requestOta(boolean z, IOtaSessionCallback iOtaSessionCallback) {
        this.session = new OtaSession();
        Log.d(TAG, "requestOta");
        return this.session;
    }

    public IOtaSession requestOta(int i, boolean z, IOtaSessionCallback iOtaSessionCallback) {
        this.session = new OtaSession();
        Log.d(TAG, "requestOta");
        return this.session;
    }

    public boolean installPackage(IOtaSession iOtaSession, String str) {
        Log.d(TAG, "installPackage Path = " + str);
        File file = new File(str);
        this.file = file;
        if (file.isFile()) {
            try {
                RecoverySystem.installPackage(mContext, this.file);
                this.verify = true;
                this.session.setInstallationStatus(true);
                Log.d(TAG, "RecoverySystem.installPackage");
            } catch (IOException e) {
                Log.d(TAG, e.toString());
            }
        } else {
            Log.d(TAG, "file is null!");
        }
        return this.verify;
    }

    private void verifyPackage() {
        Log.d(TAG, "verifyPackage");
        try {
            RecoverySystem.verifyPackage(this.file, this._recoveryVerifyListener, null);
        } catch (IOException e) {
            this.verify = false;
            Log.d(TAG, e.toString());
        } catch (GeneralSecurityException e2) {
            this.verify = false;
            Log.d(TAG, e2.toString());
        }
    }

    public int getSysVersionCode() {
        int i = 0;
        try {
            String str = SystemProperties.get("ro.product.device");
            String strSubstring = Build.DISPLAY.replaceAll("\\.", "").substring(13, 21);
            i = Integer.parseInt(strSubstring);
            Log.d(TAG, "mProject = " + str + ", start = " + strSubstring + " getSysVersionCode = " + i + ", Build.DISPLAY = " + Build.DISPLAY);
            return i;
        } catch (NumberFormatException e) {
            Log.d(TAG, e.toString());
            return i;
        } catch (StringIndexOutOfBoundsException e2) {
            Log.d(TAG, e2.toString());
            return i;
        }
    }

    public String getSysVersionName() {
        String str = Build.DISPLAY;
        Log.d(TAG, "getSysVersionName = " + str);
        return str;
    }

    public int getOtaBaseSysVersionCode() {
        Log.d(TAG, "getOtaBaseSysVersionCode");
        return getSysVersionCode();
    }

    public String getOtaBaseSysVersionName() {
        Log.d(TAG, "getOtaBaseSysVersionName");
        return getSysVersionName();
    }

    public String getOtaPkgRootPath() {
        Log.d(TAG, "getOtaPkgRootPath = /data");
        return "/data";
    }
}
