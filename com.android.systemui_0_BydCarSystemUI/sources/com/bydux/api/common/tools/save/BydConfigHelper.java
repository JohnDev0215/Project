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

    public BydConfigHelper(Context context, String fileName) {
        this.mBydXmlHelper = null;
        this.mBydPropertyHelper = null;
        this.mBydXmlHelper = new BydXmlHelper(context, fileName);
        this.mBydPropertyHelper = new BydPropertyHelper(context, fileName);
    }

    public boolean init() {
        BydLogHelper.logV(this.TAG, "init", null);
        return this.mBydPropertyHelper.init();
    }

    public boolean release() {
        BydLogHelper.logV(this.TAG, "release", null);
        return this.mBydPropertyHelper.release();
    }

    public void setProperty(String field, String value) {
        this.mBydXmlHelper.putValue(field, value);
    }

    public void setProperty(String field, int value) {
        this.mBydXmlHelper.putValue(field, value);
    }

    public void setProperty(String field, float value) {
        this.mBydXmlHelper.putValue(field, value);
    }

    public void setProperty(String field, long value) {
        this.mBydXmlHelper.putValue(field, value);
    }

    public void setProperty(String field, boolean value) {
        this.mBydXmlHelper.putValue(field, value);
    }

    public String getProperty(String field, String dfValue) {
        String property;
        if (this.mBydXmlHelper.contains(field)) {
            property = this.mBydXmlHelper.getValue(field, dfValue);
        } else {
            property = this.mBydPropertyHelper.getProperty(field, dfValue);
        }
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + field + ", value=" + property);
        return property;
    }

    public int getProperty(String field, int dfValue) {
        int property;
        if (this.mBydXmlHelper.contains(field)) {
            property = this.mBydXmlHelper.getValue(field, dfValue);
        } else {
            property = this.mBydPropertyHelper.getProperty(field, dfValue);
        }
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + field + ", value=" + property);
        return property;
    }

    public float getProperty(String field, float dfValue) {
        float property;
        if (this.mBydXmlHelper.contains(field)) {
            property = this.mBydXmlHelper.getValue(field, dfValue);
        } else {
            property = this.mBydPropertyHelper.getProperty(field, dfValue);
        }
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + field + ", value=" + property);
        return property;
    }

    public long getProperty(String field, long dfValue) {
        long property;
        if (this.mBydXmlHelper.contains(field)) {
            property = this.mBydXmlHelper.getValue(field, dfValue);
        } else {
            property = this.mBydPropertyHelper.getProperty(field, dfValue);
        }
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + field + ", value=" + property);
        return property;
    }

    public boolean getProperty(String field, boolean dfValue) {
        boolean property;
        if (this.mBydXmlHelper.contains(field)) {
            property = this.mBydXmlHelper.getValue(field, dfValue);
        } else {
            property = this.mBydPropertyHelper.getProperty(field, dfValue);
        }
        BydLogHelper.logV(this.TAG, "getProperty", "field=" + field + ", value=" + property);
        return property;
    }
}
