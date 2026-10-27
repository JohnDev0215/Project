package com.bydux.api.common.tools.save;

import android.content.Context;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/* JADX INFO: loaded from: classes2.dex */
public class BydPropertyHelper {
    private Context mContext;
    private String mFilePath;
    private final String TAG = getClass().getSimpleName();
    private Properties mProperties = null;
    private FileInputStream mInputStream = null;
    private final String FILE_NAME = "bydconfigs";
    private final String CONFIG_FOLDER = "/system/etc/";
    private final String CONFIG_FOLDER_2 = "/mnt/sdcard/";

    public BydPropertyHelper(Context context) {
        this.mContext = null;
        this.mFilePath = null;
        this.mContext = context;
        this.mFilePath = getFilePath("bydconfigs.properties");
    }

    private void commit() {
        try {
            this.mProperties.store(new FileWriter(this.mFilePath), (String) null);
        } catch (IOException e8) {
            e8.printStackTrace();
        }
    }

    private String getFilePath(String str) {
        String str2 = "/system/etc/" + str;
        if (!isPathExist(str2)) {
            str2 = "/mnt/sdcard/" + str;
        }
        BydLogHelper.logV(this.TAG, "getFilePath", "path=" + str2);
        return str2;
    }

    private boolean isPathExist(String str) {
        BydLogHelper.logV(this.TAG, "isPathExist", "path=" + str);
        boolean z7 = false;
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (!str.startsWith("/system/etc/") ? !(!str.startsWith("/mnt/sdcard/") || !file.exists() || !file.canRead() || !file.canWrite()) : !(!file.exists() || !file.canRead())) {
            z7 = true;
        }
        BydLogHelper.logV(this.TAG, "isPathExist", "exists=" + file.exists());
        BydLogHelper.logV(this.TAG, "isPathExist", "canRead=" + file.canRead());
        BydLogHelper.logV(this.TAG, "isPathExist", "canWrite=" + file.canWrite());
        return z7;
    }

    public String getProperty(String str, String str2) {
        Properties properties = this.mProperties;
        if (properties == null) {
            return str2;
        }
        String property = properties.getProperty(str);
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + property);
        return property != null ? property.trim() : str2;
    }

    public boolean init() {
        try {
            BydLogHelper.logV(this.TAG, "init", "init filePath=" + this.mFilePath);
            if (!isPathExist(this.mFilePath)) {
                return false;
            }
            this.mInputStream = new FileInputStream(this.mFilePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.mInputStream));
            Properties properties = new Properties();
            this.mProperties = properties;
            properties.load(bufferedReader);
            return true;
        } catch (IOException e8) {
            BydLogHelper.logE(this.TAG, "init", e8.getMessage());
            return false;
        }
    }

    public boolean release() {
        BydLogHelper.logV(this.TAG, "release", null);
        FileInputStream fileInputStream = this.mInputStream;
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
                this.mInputStream = null;
            } catch (IOException e8) {
                BydLogHelper.logE(this.TAG, "release", e8.getMessage());
                return false;
            }
        }
        this.mProperties = null;
        return true;
    }

    public void setProperty(String str, String str2) {
        BydLogHelper.logV(this.TAG, "setProperty", "field=" + str + ", value=" + str2);
        Properties properties = this.mProperties;
        if (properties != null) {
            properties.setProperty(str, str2);
            commit();
        }
    }

    public int getProperty(String str, int i8) {
        Properties properties = this.mProperties;
        if (properties == null) {
            return i8;
        }
        String property = properties.getProperty(str);
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + property);
        return property != null ? Integer.parseInt(property.trim()) : i8;
    }

    public void setProperty(String str, int i8) {
        BydLogHelper.logV(this.TAG, "setProperty", "field=" + str + ", value=" + i8);
        Properties properties = this.mProperties;
        if (properties != null) {
            properties.setProperty(str, i8 + "");
            commit();
        }
    }

    public float getProperty(String str, float f8) {
        Properties properties = this.mProperties;
        if (properties == null) {
            return f8;
        }
        String property = properties.getProperty(str);
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + property);
        return property != null ? Float.parseFloat(property.trim()) : f8;
    }

    public void setProperty(String str, float f8) {
        BydLogHelper.logV(this.TAG, "setProperty", "field=" + str + ", value=" + f8);
        Properties properties = this.mProperties;
        if (properties != null) {
            properties.setProperty(str, f8 + "");
            commit();
        }
    }

    public BydPropertyHelper(Context context, String str) {
        this.mContext = null;
        this.mFilePath = null;
        this.mContext = context;
        this.mFilePath = getFilePath(str + ".properties");
    }

    public long getProperty(String str, long j8) {
        Properties properties = this.mProperties;
        if (properties == null) {
            return j8;
        }
        String property = properties.getProperty(str);
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + property);
        return property != null ? Long.parseLong(property.trim()) : j8;
    }

    public void setProperty(String str, long j8) {
        BydLogHelper.logV(this.TAG, "setProperty", "field=" + str + ", value=" + j8);
        Properties properties = this.mProperties;
        if (properties != null) {
            properties.setProperty(str, j8 + "");
            commit();
        }
    }

    public boolean getProperty(String str, boolean z7) {
        Properties properties = this.mProperties;
        if (properties == null) {
            return z7;
        }
        String property = properties.getProperty(str);
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + property);
        return property != null ? "true".equals(property.trim()) : z7;
    }

    public void setProperty(String str, boolean z7) {
        BydLogHelper.logV(this.TAG, "setProperty", "field=" + str + ", value=" + z7);
        Properties properties = this.mProperties;
        if (properties != null) {
            properties.setProperty(str, z7 + "");
            commit();
        }
    }
}
