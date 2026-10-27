package com.neusoft.na.byd.util;

import android.app.UiStyleConfig;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.text.TextUtils;
import android.util.Log;
import com.ecarx.sdk.oauth2.openapi.BuildConfig;
import com.neusoft.na.common.FileUtils;
import com.neusoft.na.common.util.SystemUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ChangeHistoryUtil {
    private static final String TAG = "XJChangeHistoryUtil";
    private Context mContext;
    private Object object = new Object();
    private ExecutorService mExecutorService = Executors.newSingleThreadExecutor();

    public interface IChangeHistoryListener {
        void onDone(boolean z);
    }

    public ChangeHistoryUtil(Context context) {
        this.mContext = context;
    }

    public void unzipFile(String str, IChangeHistoryListener iChangeHistoryListener) {
        unzipFile(str, getPrivacyPath(), iChangeHistoryListener);
    }

    public void unzipFile(final String str, final String str2, final IChangeHistoryListener iChangeHistoryListener) {
        Log.i(TAG, "unzip " + str);
        this.mExecutorService.submit(new Runnable() { // from class: com.neusoft.na.byd.util.ChangeHistoryUtil$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m151lambda$unzipFile$0$comneusoftnabydutilChangeHistoryUtil(str, str2, iChangeHistoryListener);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$unzipFile$0$com-neusoft-na-byd-util-ChangeHistoryUtil, reason: not valid java name */
    /* synthetic */ void m151lambda$unzipFile$0$comneusoftnabydutilChangeHistoryUtil(String str, String str2, IChangeHistoryListener iChangeHistoryListener) {
        File file;
        synchronized (this.object) {
            try {
                try {
                    try {
                        file = new File(str);
                    } finally {
                        if (iChangeHistoryListener != null) {
                            iChangeHistoryListener.onDone(false);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (iChangeHistoryListener != null) {
                        iChangeHistoryListener.onDone(false);
                    }
                }
                if (!file.exists()) {
                    Log.i(TAG, "unzip: zip is not exists");
                    return;
                }
                Log.i(TAG, "deleteDirectory");
                deleteDirectory(str2);
                ZipFile zipFile = new ZipFile(file);
                Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    String name = zipEntryNextElement.getName();
                    String strSubstring = name.substring(name.indexOf("/") + 1);
                    if (!TextUtils.isEmpty(strSubstring)) {
                        Log.i(TAG, "unzip: " + strSubstring);
                        File file2 = new File(str2, strSubstring);
                        if (!zipEntryNextElement.isDirectory()) {
                            if (!file2.exists()) {
                                file2.createNewFile();
                            }
                            InputStream inputStream = zipFile.getInputStream(zipEntryNextElement);
                            FileOutputStream fileOutputStream = new FileOutputStream(file2);
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int i = inputStream.read(bArr);
                                if (i <= -1) {
                                    break;
                                } else {
                                    fileOutputStream.write(bArr, 0, i);
                                }
                            }
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            inputStream.close();
                        } else if (!file2.exists()) {
                            file2.mkdirs();
                        }
                    }
                }
                file.delete();
                Log.i(TAG, "unzip: zip delete done");
                if (iChangeHistoryListener != null) {
                    iChangeHistoryListener.onDone(true);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void saveChangeHistory(final int i, final long j, final boolean z, final String str, final String str2, final String str3, final boolean z2, final IChangeHistoryListener iChangeHistoryListener) {
        this.mExecutorService.submit(new Runnable() { // from class: com.neusoft.na.byd.util.ChangeHistoryUtil$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m149xd8c0ddb7(i, j, z, str, str2, str3, z2, iChangeHistoryListener);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$saveChangeHistory$1$com-neusoft-na-byd-util-ChangeHistoryUtil, reason: not valid java name */
    /* synthetic */ void m149xd8c0ddb7(int i, long j, boolean z, String str, String str2, String str3, boolean z2, IChangeHistoryListener iChangeHistoryListener) {
        JSONArray jSONArray;
        synchronized (this.object) {
            Log.i(TAG, "saveChangeHistory");
            try {
                try {
                    String jsonString = getJsonString();
                    if (TextUtils.isEmpty(jsonString)) {
                        jSONArray = new JSONArray();
                    } else {
                        jSONArray = new JSONArray(jsonString);
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.putOpt("Index", Integer.valueOf(i));
                    jSONObject.putOpt("Timestamp", Long.valueOf(j));
                    jSONObject.putOpt("Status", Boolean.valueOf(z));
                    jSONObject.putOpt("Version", str);
                    jSONObject.putOpt("VinCode", str2);
                    jSONObject.putOpt("CountryCode", str3);
                    jSONObject.putOpt("IsSync", Boolean.valueOf(z2));
                    jSONArray.put(jSONObject);
                    boolean zWriteText = writeText(jSONArray.toString());
                    if (iChangeHistoryListener != null) {
                        iChangeHistoryListener.onDone(zWriteText);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (iChangeHistoryListener != null) {
                        iChangeHistoryListener.onDone(false);
                    }
                }
            } catch (Throwable th) {
                if (iChangeHistoryListener != null) {
                    iChangeHistoryListener.onDone(false);
                }
                throw th;
            }
        }
    }

    public void checkVinCode() {
        Log.i(TAG, "updateVinCodeJson");
        String jsonString = getJsonString();
        if (TextUtils.isEmpty(jsonString)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(jsonString);
            BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this.mContext);
            Thread.sleep(300L);
            String autoVIN = bYDAutoBodyworkDevice.getAutoVIN();
            if (TextUtils.isEmpty(autoVIN)) {
                autoVIN = "";
            }
            Log.i(TAG, "vinCode == " + autoVIN);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null && TextUtils.isEmpty(jSONObjectOptJSONObject.optString("VinCode"))) {
                    jSONObjectOptJSONObject.put("VinCode", autoVIN);
                }
            }
            Log.i(TAG, "updateVinCodeJson: " + writeText(jSONArray.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveChangeHistory(final boolean z, final IChangeHistoryListener iChangeHistoryListener) {
        this.mExecutorService.submit(new Runnable() { // from class: com.neusoft.na.byd.util.ChangeHistoryUtil$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m150xbe024c78(z, iChangeHistoryListener);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$saveChangeHistory$2$com-neusoft-na-byd-util-ChangeHistoryUtil, reason: not valid java name */
    /* synthetic */ void m150xbe024c78(boolean z, IChangeHistoryListener iChangeHistoryListener) {
        JSONArray jSONArray;
        synchronized (this.object) {
            Log.i(TAG, "saveChangeHistory");
            try {
                try {
                    PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo("com.neusoft.na.navigation", 0);
                    String str = "";
                    int iCheckSelfPermission = this.mContext.checkSelfPermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
                    if (packageInfo.versionName.contains("_NEU") && iCheckSelfPermission == 0) {
                        Log.i(TAG, "saveChangeHistory: system app");
                        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this.mContext);
                        Thread.sleep(300L);
                        String autoVIN = bYDAutoBodyworkDevice.getAutoVIN();
                        if (TextUtils.isEmpty(autoVIN)) {
                            autoVIN = "";
                        }
                        str = autoVIN;
                        Log.i(TAG, "vinCode == " + str);
                    }
                    String jsonString = getJsonString();
                    int iOptInt = 1;
                    if (TextUtils.isEmpty(jsonString)) {
                        jSONArray = new JSONArray();
                    } else {
                        JSONArray jSONArray2 = new JSONArray(jsonString);
                        iOptInt = 1 + jSONArray2.optJSONObject(jSONArray2.length() - 1).optInt("Index");
                        jSONArray = jSONArray2;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.putOpt("Index", Integer.valueOf(iOptInt));
                    jSONObject.putOpt("Timestamp", Long.valueOf(System.currentTimeMillis()));
                    jSONObject.putOpt("Status", Boolean.valueOf(z));
                    jSONObject.putOpt("Version", BuildConfig.VERSION_NAME);
                    jSONObject.putOpt("VinCode", str);
                    jSONObject.putOpt("CountryCode", getCountryCode());
                    jSONObject.putOpt("IsSync", false);
                    jSONArray.put(jSONObject);
                    boolean zWriteText = writeText(jSONArray.toString());
                    if (iChangeHistoryListener != null) {
                        iChangeHistoryListener.onDone(zWriteText);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (iChangeHistoryListener != null) {
                        iChangeHistoryListener.onDone(false);
                    }
                }
            } catch (Throwable th) {
                if (iChangeHistoryListener != null) {
                    iChangeHistoryListener.onDone(false);
                }
                throw th;
            }
        }
    }

    public void update(final IChangeHistoryListener iChangeHistoryListener) {
        Log.i(TAG, "update");
        this.mExecutorService.submit(new Runnable() { // from class: com.neusoft.na.byd.util.ChangeHistoryUtil$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m152lambda$update$3$comneusoftnabydutilChangeHistoryUtil(iChangeHistoryListener);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$update$3$com-neusoft-na-byd-util-ChangeHistoryUtil, reason: not valid java name */
    /* synthetic */ void m152lambda$update$3$comneusoftnabydutilChangeHistoryUtil(IChangeHistoryListener iChangeHistoryListener) {
        String jsonString;
        synchronized (this.object) {
            boolean zWriteText = true;
            try {
                try {
                    try {
                        jsonString = getJsonString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (iChangeHistoryListener != null) {
                        }
                    }
                    if (TextUtils.isEmpty(jsonString)) {
                        return;
                    }
                    JSONArray jSONArray = new JSONArray(jsonString);
                    jSONArray.optJSONObject(jSONArray.length() - 1).put("IsSync", true);
                    zWriteText = writeText(jSONArray.toString());
                    if (iChangeHistoryListener != null) {
                    }
                    iChangeHistoryListener.onDone(zWriteText);
                } catch (Throwable th) {
                    throw th;
                }
            } finally {
                if (iChangeHistoryListener != null) {
                    iChangeHistoryListener.onDone(zWriteText);
                }
            }
        }
    }

    public void delHistory(final List<Integer> list, final IChangeHistoryListener iChangeHistoryListener) {
        Log.i(TAG, "delHistory");
        this.mExecutorService.submit(new Runnable() { // from class: com.neusoft.na.byd.util.ChangeHistoryUtil$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m148lambda$delHistory$4$comneusoftnabydutilChangeHistoryUtil(list, iChangeHistoryListener);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$delHistory$4$com-neusoft-na-byd-util-ChangeHistoryUtil, reason: not valid java name */
    /* synthetic */ void m148lambda$delHistory$4$comneusoftnabydutilChangeHistoryUtil(List list, IChangeHistoryListener iChangeHistoryListener) {
        String jsonString;
        synchronized (this.object) {
            boolean zWriteText = false;
            try {
                try {
                    try {
                        jsonString = getJsonString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (iChangeHistoryListener != null) {
                        }
                    }
                    if (TextUtils.isEmpty(jsonString)) {
                        return;
                    }
                    JSONArray jSONArray = new JSONArray(jsonString);
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        delHistory(jSONArray, ((Integer) it.next()).intValue());
                    }
                    zWriteText = writeText(jSONArray.toString());
                    if (iChangeHistoryListener != null) {
                        iChangeHistoryListener.onDone(true);
                    }
                    if (iChangeHistoryListener != null) {
                    }
                    iChangeHistoryListener.onDone(zWriteText);
                } catch (Throwable th) {
                    throw th;
                }
            } finally {
                if (iChangeHistoryListener != null) {
                    iChangeHistoryListener.onDone(zWriteText);
                }
            }
        }
    }

    private void delHistory(JSONArray jSONArray, int i) throws Exception {
        Log.i(TAG, "delHistory: " + i);
        int i2 = 0;
        while (true) {
            if (i2 >= jSONArray.length()) {
                i2 = -1;
                break;
            }
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i2);
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.optInt("Index") == i) {
                Log.i(TAG, "delHistory: position:" + i2);
                break;
            }
            i2++;
        }
        if (i2 > -1) {
            jSONArray.remove(i2);
        }
    }

    public String getJsonString() {
        Log.i(TAG, "getJsonString");
        try {
            File file = getFile();
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i <= -1) {
                    break;
                }
                sb.append(new String(bArr, 0, i));
            }
            fileInputStream.close();
            String string = sb.toString();
            if (TextUtils.isEmpty(string)) {
                return "";
            }
            String strDecrypt = AesUtil.decrypt(this.mContext, string);
            if (strDecrypt != null) {
                return strDecrypt;
            }
            Log.i(TAG, "getJsonString: file content is bad, delete this file: " + file.delete());
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getStringInAssets() {
        Log.i(TAG, "getStringInAssets");
        try {
            InputStream inputStreamOpen = this.mContext.getAssets().open(getLanguage() + "/privacy_policy.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStreamOpen);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line + "\n");
                } else {
                    inputStreamOpen.close();
                    inputStreamReader.close();
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getPrivacyUrl() {
        Log.i(TAG, "getPrivacyUrl");
        File file = new File(getPrivacyPath(), getLanguage());
        String str = "/index.html?mode=" + ((this.mContext.getResources().getConfiguration().uiMode & 48) == 32 ? "night" : "day") + "&style=" + (UiStyleConfig.getInstance(this.mContext).getUiStyle() == 1 ? "denza" : "daynasty");
        if (file.exists()) {
            return "file://" + file.getAbsolutePath() + str;
        }
        return "file:///android_asset/" + getLanguage() + str;
    }

    public String getPrivacyUrlForHMI() {
        Log.i(TAG, "getPrivacyUrlForHMI");
        File file = new File(getPrivacyPath(), getLanguage());
        String strConcat = "/index.html?mode=".concat((this.mContext.getResources().getConfiguration().uiMode & 48) == 32 ? "night" : "day");
        if (file.exists()) {
            return file.getAbsolutePath() + "/index.html";
        }
        return "file:///android_asset/" + getLanguage() + strConcat;
    }

    private String getPrivacyPath() {
        Log.i(TAG, "getPrivacyPath");
        File file = new File(this.mContext.getFilesDir(), FileUtils.KEY_PERMISSION);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public String getLanguage() {
        String language;
        Exception e;
        try {
            language = Locale.getDefault().getLanguage();
            try {
                if ("nb".equals(language) || "nn".equals(language)) {
                    language = "no";
                }
                if (this.mContext.getAssets().list(language).length < 1) {
                    return "en";
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
            }
        } catch (Exception e3) {
            language = "en";
            e = e3;
        }
        return language;
    }

    public boolean isSamePath(String str) {
        return getFilePath().equals(str);
    }

    private String getCountryCode() {
        return SystemUtils.INSTANCE.getByd8155CountryCode(this.mContext);
    }

    private boolean writeText(String str) {
        Log.i(TAG, "writeText");
        try {
            File file = getFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String strEncrypt = AesUtil.encrypt(this.mContext, str);
            if (strEncrypt == null) {
                Log.i(TAG, "writeText: file content is bad, delete this file: " + file.delete());
                strEncrypt = "";
            }
            fileOutputStream.write(strEncrypt.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private File getFile() {
        String filePath = getFilePath();
        Log.i(TAG, "getFile:" + filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private String getFilePath() {
        return this.mContext.getCacheDir().getParent() + "/shared_prefs/Disclaimer.bin";
    }

    private boolean deleteDirectory(String str) {
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        boolean zDeleteDirectory = true;
        for (int i = 0; i < fileArrListFiles.length; i++) {
            if (fileArrListFiles[i].isFile()) {
                zDeleteDirectory = deleteFile(fileArrListFiles[i].getAbsolutePath());
                if (!zDeleteDirectory) {
                    break;
                }
            } else {
                if (fileArrListFiles[i].isDirectory() && !(zDeleteDirectory = deleteDirectory(fileArrListFiles[i].getAbsolutePath()))) {
                    break;
                }
            }
        }
        return zDeleteDirectory;
    }

    private static boolean deleteFile(String str) {
        File file = new File(str);
        return file.exists() && file.isFile() && file.delete();
    }
}
