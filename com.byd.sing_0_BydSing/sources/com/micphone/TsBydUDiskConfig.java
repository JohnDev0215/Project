package com.micphone;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class TsBydUDiskConfig {
    private static final String JSON_KEY_AGREEMENT = "agreement";
    private static final String JSON_KEY_DONGLE = "dongle";
    private static final String JSON_KEY_EFFECT_LOAD = "effect_load";
    private static final String JSON_KEY_NAME_CHECK = "name_check";
    private static final String JSON_KEY_SERVER_CHECK = "server_check";
    private static final String JSON_KEY_SWITCH = "switch";
    private static final String TAG = "TsBydUDiskConfig";
    private static final String U_DISK_EFFECT_PATH;
    private static final String U_DISK_ROOT_PATH = "thunder_test";
    private static final String U_DISK_THUNDER_CONFIG;
    private static final String U_DISK_THUNDER_EFFECT_DEFAULT;
    private static final String U_DISK_THUNDER_EFFECT_KTV;
    private static final String U_DISK_THUNDER_EFFECT_MUSIC;
    private static final String U_DISK_THUNDER_EFFECT_RECORD;
    private static TsBydUDiskConfig instance;
    private Context mContext;
    private int mConfigSwitch = 0;
    private int mNameCheck = 0;
    private int mServerCheck = 0;
    private String mAgreement = null;
    private int mEffectLoad = 0;
    private byte[] effect_default = null;
    private byte[] effect_record = null;
    private byte[] effect_music = null;
    private byte[] effect_ktv = null;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(U_DISK_ROOT_PATH);
        String str = File.separator;
        sb.append(str);
        sb.append("thunder_effect");
        String string = sb.toString();
        U_DISK_EFFECT_PATH = string;
        U_DISK_THUNDER_CONFIG = U_DISK_ROOT_PATH + str + "thunder_config.json";
        U_DISK_THUNDER_EFFECT_DEFAULT = string + str + "default";
        U_DISK_THUNDER_EFFECT_MUSIC = string + str + "music";
        U_DISK_THUNDER_EFFECT_RECORD = string + str + "record";
        U_DISK_THUNDER_EFFECT_KTV = string + str + "ktv";
    }

    public TsBydUDiskConfig(Context context) {
        this.mContext = context;
    }

    public static TsBydUDiskConfig getInstance(Context context) {
        if (instance == null) {
            instance = new TsBydUDiskConfig(context);
        }
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFileExists(String str) {
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] readBytes(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return bArr;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String readString(String str) {
        try {
            return new String(readBytes(str), "UTF-8");
        } catch (Exception unused) {
            return null;
        }
    }

    public String getAgreement() {
        if (this.mConfigSwitch != 1) {
            return null;
        }
        return this.mAgreement;
    }

    public byte[] getEffectDefault() {
        if (this.mConfigSwitch != 1) {
            return null;
        }
        return this.effect_default;
    }

    public byte[] getEffectKtv() {
        if (this.mConfigSwitch != 1) {
            return null;
        }
        return this.effect_ktv;
    }

    public byte[] getEffectMusic() {
        if (this.mConfigSwitch != 1) {
            return null;
        }
        return this.effect_music;
    }

    public byte[] getEffectRecord() {
        if (this.mConfigSwitch != 1) {
            return null;
        }
        return this.effect_record;
    }

    public boolean isJarEffectLoad() {
        return (this.mConfigSwitch == 1 && this.mEffectLoad == 0) ? false : true;
    }

    public boolean isNameCheck() {
        return (this.mConfigSwitch == 1 && this.mNameCheck == 0) ? false : true;
    }

    public boolean isServerCheck() {
        return (this.mConfigSwitch == 1 && this.mServerCheck == 0) ? false : true;
    }

    public void parseUDisk(final String str) {
        String str2 = TAG;
        Log.d(str2, "U盘插入 =" + str);
        final String str3 = str + File.separator + U_DISK_THUNDER_CONFIG;
        if (isFileExists(str3)) {
            new Thread(new Runnable() { // from class: com.micphone.TsBydUDiskConfig.1
                @Override // java.lang.Runnable
                public void run() {
                    Exception e;
                    final int i = 1;
                    try {
                        JSONObject jSONObject = new JSONObject(TsBydUDiskConfig.this.readString(str3));
                        JSONObject jSONObject2 = jSONObject.getJSONObject(TsBydUDiskConfig.JSON_KEY_DONGLE);
                        TsBydUDiskConfig.this.mConfigSwitch = jSONObject.getInt(TsBydUDiskConfig.JSON_KEY_SWITCH);
                        TsBydUDiskConfig.this.mNameCheck = jSONObject2.getInt(TsBydUDiskConfig.JSON_KEY_NAME_CHECK);
                        TsBydUDiskConfig.this.mServerCheck = jSONObject2.getInt(TsBydUDiskConfig.JSON_KEY_SERVER_CHECK);
                        TsBydUDiskConfig.this.mAgreement = jSONObject2.getString(TsBydUDiskConfig.JSON_KEY_AGREEMENT);
                        TsBydUDiskConfig.this.mEffectLoad = jSONObject2.getInt(TsBydUDiskConfig.JSON_KEY_EFFECT_LOAD);
                        try {
                            StringBuilder sb = new StringBuilder();
                            sb.append(str);
                            String str4 = File.separator;
                            sb.append(str4);
                            sb.append(TsBydUDiskConfig.U_DISK_THUNDER_EFFECT_DEFAULT);
                            String string = sb.toString();
                            String str5 = str + str4 + TsBydUDiskConfig.U_DISK_THUNDER_EFFECT_KTV;
                            String str6 = str + str4 + TsBydUDiskConfig.U_DISK_THUNDER_EFFECT_MUSIC;
                            String str7 = str + str4 + TsBydUDiskConfig.U_DISK_THUNDER_EFFECT_RECORD;
                            if (TsBydUDiskConfig.this.isFileExists(string)) {
                                TsBydUDiskConfig tsBydUDiskConfig = TsBydUDiskConfig.this;
                                tsBydUDiskConfig.effect_default = tsBydUDiskConfig.readBytes(string);
                                i = 2;
                            }
                            if (TsBydUDiskConfig.this.isFileExists(str5)) {
                                TsBydUDiskConfig tsBydUDiskConfig2 = TsBydUDiskConfig.this;
                                tsBydUDiskConfig2.effect_ktv = tsBydUDiskConfig2.readBytes(str5);
                                i++;
                            }
                            if (TsBydUDiskConfig.this.isFileExists(str6)) {
                                TsBydUDiskConfig tsBydUDiskConfig3 = TsBydUDiskConfig.this;
                                tsBydUDiskConfig3.effect_music = tsBydUDiskConfig3.readBytes(str6);
                                i++;
                            }
                            if (TsBydUDiskConfig.this.isFileExists(str7)) {
                                TsBydUDiskConfig tsBydUDiskConfig4 = TsBydUDiskConfig.this;
                                tsBydUDiskConfig4.effect_record = tsBydUDiskConfig4.readBytes(str7);
                                i++;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            Log.e(TsBydUDiskConfig.TAG, "error =" + e.getMessage());
                        }
                    } catch (Exception e3) {
                        e = e3;
                        i = 0;
                    }
                    Log.d(TsBydUDiskConfig.TAG, TsBydUDiskConfig.getInstance(TsBydUDiskConfig.this.mContext).toString());
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.micphone.TsBydUDiskConfig.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Toast.makeText(TsBydUDiskConfig.this.mContext.getApplicationContext(), "读取结束=" + i, 0).show();
                        }
                    });
                }
            }).start();
        } else {
            Log.d(str2, "do nothing !!!");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TsBydUDiskConfig{mConfigSwitch=");
        sb.append(this.mConfigSwitch);
        sb.append(", mNameCheck=");
        sb.append(this.mNameCheck);
        sb.append(", mServerCheck=");
        sb.append(this.mServerCheck);
        sb.append(", mAgreement='");
        sb.append(this.mAgreement);
        sb.append('\'');
        sb.append(", mEffectLoad=");
        sb.append(this.mEffectLoad);
        sb.append(", effect_default=");
        byte[] bArr = this.effect_default;
        sb.append(bArr == null ? null : Integer.valueOf(bArr.length));
        sb.append(", effect_record=");
        byte[] bArr2 = this.effect_record;
        sb.append(bArr2 == null ? null : Integer.valueOf(bArr2.length));
        sb.append(", effect_music=");
        byte[] bArr3 = this.effect_music;
        sb.append(bArr3 == null ? null : Integer.valueOf(bArr3.length));
        sb.append(", effect_ktv=");
        byte[] bArr4 = this.effect_ktv;
        sb.append(bArr4 != null ? Integer.valueOf(bArr4.length) : null);
        sb.append('}');
        return sb.toString();
    }
}
