package com.ecarx.xui.adaptapi.device.ads;

import android.text.TextUtils;
import android.util.Log;
import com.ecarx.xui.adaptapi.device.ads.IAdvertise;
import com.neusoft.na.common.FileUtils;
import com.neusoft.na.ocservice.OneCoreService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public class AdvertiseImpl implements IAdvertise {
    private static final String AD_PARAM_FILE = "/resources/ad_parameter.txt";
    private static final String AD_RECORD_FILE = "/resources/ad_play_record.txt";
    private static final String cTag = "AdvertiseImpl";
    private AdInfo _adInfo;
    private IAdvertise.OnAdOperationChangedListener _listener;

    @Override // com.ecarx.xui.adaptapi.device.ads.IAdvertise
    public void setBootAdInfo(IBootAdInfo iBootAdInfo) {
        if (iBootAdInfo != null) {
            if (iBootAdInfo.getAdPath().isEmpty()) {
                Log.d(cTag, "path is null,del the ad");
                Log.d(cTag, "del ad param res=" + new File(AD_PARAM_FILE).delete());
                Log.d(cTag, "del ad record res=" + new File(AD_PARAM_FILE).delete());
                Log.d(cTag, "del ad file res=" + new File("/resources/ad.mp4").delete());
                return;
            }
            this._adInfo = new AdInfo(iBootAdInfo.getAdId(), iBootAdInfo.getAdPath(), iBootAdInfo.getMd5(), iBootAdInfo.getDuration(), iBootAdInfo.getMaxTimes(), iBootAdInfo.getEffectiveTime(), iBootAdInfo.getExpiredTime());
        }
        Log.d(cTag, "setBootAdInfo");
        AdInfo adParam = getAdParam();
        Log.d(cTag, "OldAdInfo= " + adParam.adId + StringUtils.SPACE + adParam.adPath + StringUtils.SPACE + adParam.adMaxTimes + StringUtils.SPACE + adParam.adEffectiveTime + StringUtils.SPACE + adParam.adExpiredTime + StringUtils.SPACE);
        Log.d(cTag, "newAdInfo= " + this._adInfo.adId + StringUtils.SPACE + this._adInfo.adPath + StringUtils.SPACE + this._adInfo.adMaxTimes + StringUtils.SPACE + this._adInfo.adEffectiveTime + StringUtils.SPACE + this._adInfo.adExpiredTime + StringUtils.SPACE);
        if (TextUtils.isEmpty(this._adInfo.adId) || this._adInfo.adId.equals(adParam.adId) || this._adInfo.adMaxTimes == 0 || this._adInfo.adEffectiveTime == 0 || this._adInfo.adExpiredTime == 0 || !copyFile(this._adInfo.adPath, "/resources/ad.mp4")) {
            return;
        }
        createAdParam();
        createAdRecord();
    }

    public boolean copyFile(String str, String str2) {
        File file = new File(str2);
        if (file.exists()) {
            file.delete();
        }
        try {
            File file2 = new File(str);
            File file3 = new File(str2);
            if (file3.exists()) {
                file3.delete();
            }
            if (file2.exists()) {
                FileInputStream fileInputStream = new FileInputStream(str);
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                byte[] bArr = new byte[1444];
                int i = 0;
                while (true) {
                    int i2 = fileInputStream.read(bArr);
                    if (i2 == -1) {
                        break;
                    }
                    i += i2;
                    System.out.println(i);
                    fileOutputStream.write(bArr, 0, i2);
                }
                fileInputStream.close();
            }
            return true;
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
            return false;
        }
    }

    private void createAdRecord() {
        makeFilePath(AD_RECORD_FILE);
        try {
            FileInputStream fileInputStream = new FileInputStream(AD_RECORD_FILE);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(AD_RECORD_FILE);
            properties.put("adPlayTimes", FileUtils.DEFAULT_VERSION);
            properties.put("adStartTime", FileUtils.DEFAULT_VERSION);
            properties.put("adStopTime", FileUtils.DEFAULT_VERSION);
            properties.put("adTodayTimes", FileUtils.DEFAULT_VERSION);
            properties.put("adTodayPlayTime", FileUtils.DEFAULT_VERSION);
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createAdParam() {
        makeFilePath(AD_PARAM_FILE);
        try {
            FileInputStream fileInputStream = new FileInputStream(AD_PARAM_FILE);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(AD_PARAM_FILE);
            properties.put("id", this._adInfo.adId);
            properties.put(OneCoreService.PAYLOAD_KEY_PATH, this._adInfo.adPath);
            properties.put("md5", this._adInfo.adMd5);
            properties.put("duration", String.valueOf(this._adInfo.adDuration));
            properties.put("maxTimes", String.valueOf(this._adInfo.adMaxTimes));
            properties.put("effectTime", String.valueOf(this._adInfo.adEffectiveTime / 1000));
            properties.put("expiredTime", String.valueOf(this._adInfo.adExpiredTime / 1000));
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private AdInfo getAdParam() {
        if (new File(AD_PARAM_FILE).exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(AD_PARAM_FILE);
                Properties properties = new Properties();
                properties.load(fileInputStream);
                FileOutputStream fileOutputStream = new FileOutputStream(AD_PARAM_FILE);
                AdInfo adInfo = new AdInfo((String) properties.get("id"), (String) properties.get(OneCoreService.PAYLOAD_KEY_PATH), (String) properties.get("mkd5"), Long.parseLong((String) properties.get("duration")), Integer.parseInt((String) properties.get("maxTimes")), Long.parseLong((String) properties.get("effectTime")), Long.parseLong((String) properties.get("expiredTime")));
                properties.store(fileOutputStream, (String) null);
                fileOutputStream.close();
                return adInfo;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new AdInfo("", "", "", 0L, 0, 0L, 0L);
    }

    public File makeFilePath(String str) {
        File file;
        String strSubstring = str.substring(0, str.lastIndexOf("/") + 1);
        String strSubstring2 = str.substring(str.lastIndexOf("/") + 1);
        makeRootDirectory(strSubstring);
        File file2 = null;
        try {
            file = new File(strSubstring + strSubstring2);
        } catch (Exception e) {
            e = e;
        }
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            return file;
        } catch (Exception e2) {
            e = e2;
            file2 = file;
            e.printStackTrace();
            return file2;
        }
    }

    public static void makeRootDirectory(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return;
            }
            file.mkdir();
        } catch (Exception e) {
            Log.e("error:", e + "");
        }
    }

    @Override // com.ecarx.xui.adaptapi.device.ads.IAdvertise
    public void addOnAdOperationChangedListener(IAdvertise.OnAdOperationChangedListener onAdOperationChangedListener) {
        this._listener = onAdOperationChangedListener;
    }

    @Override // com.ecarx.xui.adaptapi.device.ads.IAdvertise
    public void removeOnAdOperationChangedListener(IAdvertise.OnAdOperationChangedListener onAdOperationChangedListener) {
        this._listener = null;
    }
}
