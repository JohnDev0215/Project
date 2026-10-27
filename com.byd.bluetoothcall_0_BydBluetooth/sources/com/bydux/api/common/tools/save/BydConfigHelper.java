package com.bydux.api.common.tools.save;

import android.content.Context;
import com.bydux.api.BuildConfig;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class BydConfigHelper {
    private final String TAG = getClass().getSimpleName();
    private BydPropertyHelper mBydPropertyHelper;
    private BydXmlHelper mBydXmlHelper;

    public BydConfigHelper(Context context) {
        this.mBydXmlHelper = null;
        this.mBydPropertyHelper = null;
        this.mBydXmlHelper = new BydXmlHelper(context);
        this.mBydPropertyHelper = new BydPropertyHelper(context);
    }

    public String getProperty(String str, String str2) {
        String value = this.mBydXmlHelper.contains(str) ? this.mBydXmlHelper.getValue(str, str2) : this.mBydPropertyHelper.getProperty(str, str2);
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + value);
        return value;
    }

    public boolean init() {
        BydLogHelper.logV(this.TAG, "init", null);
        return this.mBydPropertyHelper.init();
    }

    public boolean release() {
        BydLogHelper.logV(this.TAG, BuildConfig.BUILD_TYPE, null);
        return this.mBydPropertyHelper.release();
    }

    public void setProperty(String str, String str2) {
        this.mBydXmlHelper.putValue(str, str2);
    }

    public void setProperty(String str, int i) {
        this.mBydXmlHelper.putValue(str, i);
    }

    public void setProperty(String str, float f) {
        this.mBydXmlHelper.putValue(str, f);
    }

    public void setProperty(String str, long j) {
        this.mBydXmlHelper.putValue(str, j);
    }

    public int getProperty(String str, int i) {
        int property;
        if (this.mBydXmlHelper.contains(str)) {
            property = this.mBydXmlHelper.getValue(str, i);
        } else {
            property = this.mBydPropertyHelper.getProperty(str, i);
        }
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + property);
        return property;
    }

    public void setProperty(String str, boolean z) {
        this.mBydXmlHelper.putValue(str, z);
    }

    public BydConfigHelper(Context context, String str) {
        this.mBydXmlHelper = null;
        this.mBydPropertyHelper = null;
        this.mBydXmlHelper = new BydXmlHelper(context, str);
        this.mBydPropertyHelper = new BydPropertyHelper(context, str);
    }

    public float getProperty(String str, float f) {
        float property;
        if (this.mBydXmlHelper.contains(str)) {
            property = this.mBydXmlHelper.getValue(str, f);
        } else {
            property = this.mBydPropertyHelper.getProperty(str, f);
        }
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + property);
        return property;
    }

    public long getProperty(String str, long j) {
        long property;
        if (this.mBydXmlHelper.contains(str)) {
            property = this.mBydXmlHelper.getValue(str, j);
        } else {
            property = this.mBydPropertyHelper.getProperty(str, j);
        }
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + property);
        return property;
    }

    public boolean getProperty(String str, boolean z) {
        boolean property;
        if (this.mBydXmlHelper.contains(str)) {
            property = this.mBydXmlHelper.getValue(str, z);
        } else {
            property = this.mBydPropertyHelper.getProperty(str, z);
        }
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + property);
        return property;
    }
}
