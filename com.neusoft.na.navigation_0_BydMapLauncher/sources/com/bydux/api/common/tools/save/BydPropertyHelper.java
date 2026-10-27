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
        this.mFilePath = null;
        this.mContext = context;
        this.mFilePath = getFilePath("bydconfigs.properties");
    }

    public BydPropertyHelper(Context context, String fileName) {
        this.mFilePath = null;
        this.mContext = context;
        this.mFilePath = getFilePath(fileName + ".properties");
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
        } catch (IOException e) {
            BydLogHelper.logE(this.TAG, "init", e.getMessage());
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
            } catch (IOException e) {
                BydLogHelper.logE(this.TAG, "release", e.getMessage());
                return false;
            }
        }
        this.mProperties = null;
        return true;
    }

    public void setProperty(String field, String value) {
        BydLogHelper.logV(this.TAG, "setProperty", "field=" + field + ", value=" + value);
        Properties properties = this.mProperties;
        if (properties != null) {
            properties.setProperty(field, value);
            commit();
        }
    }

    public void setProperty(String field, int value) {
        BydLogHelper.logV(this.TAG, "setProperty", "field=" + field + ", value=" + value);
        Properties properties = this.mProperties;
        if (properties != null) {
            properties.setProperty(field, value + "");
            commit();
        }
    }

    public void setProperty(String field, float value) {
        BydLogHelper.logV(this.TAG, "setProperty", "field=" + field + ", value=" + value);
        Properties properties = this.mProperties;
        if (properties != null) {
            properties.setProperty(field, value + "");
            commit();
        }
    }

    public void setProperty(String field, long value) {
        BydLogHelper.logV(this.TAG, "setProperty", "field=" + field + ", value=" + value);
        Properties properties = this.mProperties;
        if (properties != null) {
            properties.setProperty(field, value + "");
            commit();
        }
    }

    public void setProperty(String field, boolean value) {
        BydLogHelper.logV(this.TAG, "setProperty", "field=" + field + ", value=" + value);
        Properties properties = this.mProperties;
        if (properties != null) {
            properties.setProperty(field, value + "");
            commit();
        }
    }

    public String getProperty(String field, String dfValue) {
        Properties properties = this.mProperties;
        if (properties == null) {
            return dfValue;
        }
        String property = properties.getProperty(field);
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + field + ", value=" + property);
        return property != null ? property.trim() : dfValue;
    }

    public int getProperty(String field, int dfValue) {
        Properties properties = this.mProperties;
        if (properties == null) {
            return dfValue;
        }
        String property = properties.getProperty(field);
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + field + ", value=" + property);
        return property != null ? Integer.parseInt(property.trim()) : dfValue;
    }

    public float getProperty(String field, float dfValue) {
        Properties properties = this.mProperties;
        if (properties == null) {
            return dfValue;
        }
        String property = properties.getProperty(field);
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + field + ", value=" + property);
        return property != null ? Float.parseFloat(property.trim()) : dfValue;
    }

    public long getProperty(String field, long dfValue) {
        Properties properties = this.mProperties;
        if (properties == null) {
            return dfValue;
        }
        String property = properties.getProperty(field);
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + field + ", value=" + property);
        return property != null ? Long.parseLong(property.trim()) : dfValue;
    }

    public boolean getProperty(String field, boolean dfValue) {
        Properties properties = this.mProperties;
        if (properties == null) {
            return dfValue;
        }
        String property = properties.getProperty(field);
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + field + ", value=" + property);
        return property != null ? "true".equals(property.trim()) : dfValue;
    }

    private String getFilePath(String fileName) {
        String str = "/system/etc/" + fileName;
        if (!isPathExist(str)) {
            str = "/mnt/sdcard/" + fileName;
        }
        BydLogHelper.logV(this.TAG, "getFilePath", "path=" + str);
        return str;
    }

    private boolean isPathExist(String path) {
        BydLogHelper.logV(this.TAG, "isPathExist", "path=" + path);
        boolean z = false;
        if (path == null) {
            return false;
        }
        File file = new File(path);
        if (!path.startsWith("/system/etc/") ? !(!path.startsWith("/mnt/sdcard/") || !file.exists() || !file.canRead() || !file.canWrite()) : !(!file.exists() || !file.canRead())) {
            z = true;
        }
        BydLogHelper.logV(this.TAG, "isPathExist", "exists=" + file.exists());
        BydLogHelper.logV(this.TAG, "isPathExist", "canRead=" + file.canRead());
        BydLogHelper.logV(this.TAG, "isPathExist", "canWrite=" + file.canWrite());
        return z;
    }

    private void commit() {
        try {
            this.mProperties.store(new FileWriter(this.mFilePath), (String) null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
