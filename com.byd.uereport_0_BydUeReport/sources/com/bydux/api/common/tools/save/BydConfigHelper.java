package com.bydux.api.common.tools.save;

import android.content.Context;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
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
        BydLogHelper.logV(this.TAG, "release", null);
        return this.mBydPropertyHelper.release();
    }

    public void setProperty(String str, String str2) {
        this.mBydXmlHelper.putValue(str, str2);
    }

    public void setProperty(String str, int i8) {
        this.mBydXmlHelper.putValue(str, i8);
    }

    public void setProperty(String str, float f8) {
        this.mBydXmlHelper.putValue(str, f8);
    }

    public void setProperty(String str, long j8) {
        this.mBydXmlHelper.putValue(str, j8);
    }

    public int getProperty(String str, int i8) {
        int property;
        if (this.mBydXmlHelper.contains(str)) {
            property = this.mBydXmlHelper.getValue(str, i8);
        } else {
            property = this.mBydPropertyHelper.getProperty(str, i8);
        }
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + property);
        return property;
    }

    public void setProperty(String str, boolean z7) {
        this.mBydXmlHelper.putValue(str, z7);
    }

    public BydConfigHelper(Context context, String str) {
        this.mBydXmlHelper = null;
        this.mBydPropertyHelper = null;
        this.mBydXmlHelper = new BydXmlHelper(context, str);
        this.mBydPropertyHelper = new BydPropertyHelper(context, str);
    }

    public float getProperty(String str, float f8) {
        float property;
        if (this.mBydXmlHelper.contains(str)) {
            property = this.mBydXmlHelper.getValue(str, f8);
        } else {
            property = this.mBydPropertyHelper.getProperty(str, f8);
        }
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + property);
        return property;
    }

    public long getProperty(String str, long j8) {
        long property;
        if (this.mBydXmlHelper.contains(str)) {
            property = this.mBydXmlHelper.getValue(str, j8);
        } else {
            property = this.mBydPropertyHelper.getProperty(str, j8);
        }
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + property);
        return property;
    }

    public boolean getProperty(String str, boolean z7) {
        boolean property;
        if (this.mBydXmlHelper.contains(str)) {
            property = this.mBydXmlHelper.getValue(str, z7);
        } else {
            property = this.mBydPropertyHelper.getProperty(str, z7);
        }
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + str + ", value=" + property);
        return property;
    }
}
