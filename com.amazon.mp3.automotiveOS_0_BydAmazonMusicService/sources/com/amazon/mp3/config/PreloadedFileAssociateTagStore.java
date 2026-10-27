package com.amazon.mp3.config;

import android.content.Context;
import android.text.TextUtils;
import androidx.autofill.HintConstants;
import com.amazon.mp3.profile.DeviceProfileProvider;
import com.amazon.mp3.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

/* JADX INFO: loaded from: classes3.dex */
public class PreloadedFileAssociateTagStore {
    private static final String[] ATTRIBUTION_LOCATION_PATH_LIST = {"/system/etc/", "/carrier/etc/", "/oem/etc/"};
    private static final String TAG = "PreloadedFileAssociateTagStore";
    private final Context mContext;
    private final DeviceProfileProvider mDeviceProfile;
    private String mPreloadedAssociatedTag;

    PreloadedFileAssociateTagStore(Context context) {
        this.mContext = context;
        this.mDeviceProfile = new DeviceProfileProvider(context);
    }

    public String loadOrDefault() throws Throwable {
        if (this.mPreloadedAssociatedTag == null) {
            Properties preloadedPropertiesFromFile = readPreloadedPropertiesFromFile();
            String property = preloadedPropertiesFromFile.getProperty("partnerName", "");
            boolean z = Boolean.parseBoolean(preloadedPropertiesFromFile.getProperty("hideModel", "false"));
            if (TextUtils.isEmpty(property)) {
                this.mPreloadedAssociatedTag = "";
            } else {
                this.mPreloadedAssociatedTag = constructTagFromFile(property, z);
            }
        }
        return this.mPreloadedAssociatedTag;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Properties] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.io.FileInputStream, java.io.InputStream] */
    private Properties readPreloadedPropertiesFromFile() throws Throwable {
        IOException e;
        ?? properties = new Properties();
        String[] strArr = ATTRIBUTION_LOCATION_PATH_LIST;
        ?? length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            File file = new File(strArr[i], "amzn.mp3.properties");
            if (file.canRead()) {
                ?? r2 = 0;
                try {
                    try {
                        try {
                            length = new FileInputStream(file);
                        } catch (IOException e2) {
                            length = 0;
                            e = e2;
                        } catch (Throwable th) {
                            th = th;
                            if (r2 != 0) {
                                try {
                                    r2.close();
                                } catch (IOException e3) {
                                    Log.error(TAG, "Error when closing file stream", e3);
                                }
                            }
                            throw th;
                        }
                        try {
                            properties.load(length);
                            length.close();
                            length = length;
                        } catch (IOException e4) {
                            e = e4;
                            Log.error(TAG, "Error when reading carrier file", e);
                            if (length != 0) {
                                length.close();
                                length = length;
                            }
                            break;
                            return properties;
                        }
                    } catch (IOException e5) {
                        length = TAG;
                        Log.error((String) length, "Error when closing file stream", e5);
                    }
                    break;
                } catch (Throwable th2) {
                    th = th2;
                    r2 = length;
                }
            } else {
                i++;
            }
        }
    }

    private String constructTagFromFile(String carrier, boolean hideDeviceModel) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("mp3-android-");
        sb.append(carrier);
        sb.append('-');
        if (hideDeviceModel) {
            str = "";
        } else {
            str = this.mDeviceProfile.getDeviceModel() + '-';
        }
        sb.append(str);
        sb.append(HintConstants.AUTOFILL_HINT_PHONE);
        sb.append('-');
        sb.append("pre");
        sb.append('-');
        sb.append(AssociateTag.getInstance(this.mContext).getAssociateTagRegionInformation());
        return sb.toString().toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9-]", "");
    }
}
