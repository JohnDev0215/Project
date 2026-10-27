package com.amazon.client.metrics.thirdparty;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.amazon.client.metrics.thirdparty.utils.DeviceCategoryUtil;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.amazon.dp.logger.DPLogger;

/* JADX INFO: loaded from: classes2.dex */
public class OverridingDeviceInfoManager extends AndroidDeviceInfoManager {
    protected static final DPLogger log = new DPLogger();
    protected String mCachedCustomerID;
    protected String mCachedDSN;
    protected String mCachedSessionID;
    private Context mContext;
    protected String mLegacyDeviceID;
    protected SharedPreferences mSharedPrefs;
    protected String mSharedPrefsCustomerIDKey;
    protected String mSharedPrefsDSNKey;
    protected String mSharedPrefsSessionIDKey;
    private final boolean mUseRealDSN;

    public OverridingDeviceInfoManager(Context context, DeviceUtil deviceUtil, String str, String str2, String str3, boolean z) {
        super(deviceUtil);
        this.mUseRealDSN = z;
        this.mContext = context;
        initialize(context, str, str2, str3, z);
    }

    @Override // com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager, com.amazon.client.metrics.thirdparty.DeviceInfoManager
    public MetricsDeviceInfo getDeviceInfo() {
        super.getDeviceInfo();
        if (this.mLegacyDeviceID != null && "Tablet".equals(DeviceCategoryUtil.getDeviceCategory(this.mContext))) {
            addToMetricsDeviceInfo("oldDeviceId", this.mLegacyDeviceID);
        }
        return this.mMetricsDeviceInfo;
    }

    @Override // com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager
    public String getSessionID() {
        if (this.mDeviceUtil.isDeviceSerialNumberAnonymous()) {
            log.debug("getSessionID", "Returning unique session id for non-anonymous metrics", new Object[0]);
            if (this.mCachedSessionID == null) {
                this.mCachedSessionID = getOrCreateSessionIDSharedPref(this.mSharedPrefsSessionIDKey);
            }
            return this.mCachedSessionID;
        }
        return super.getSessionID();
    }

    @Override // com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager
    protected String getDeviceSerialNumber() {
        if (this.mUseRealDSN) {
            return this.mDeviceUtil.fetchDeviceSerialNumber();
        }
        if (this.mDeviceUtil.isDeviceSerialNumberAnonymous()) {
            log.debug("getDeviceSerialNumber", "Returning unique device id for non-anonymous metrics", new Object[0]);
            if (this.mCachedDSN == null) {
                this.mCachedDSN = getOrCreateDSNSharedPref(this.mSharedPrefsDSNKey);
            }
            return this.mCachedDSN;
        }
        return super.getDeviceSerialNumber();
    }

    @Override // com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager
    public String getCustomerID() {
        if (this.mDeviceUtil.isDeviceSerialNumberAnonymous()) {
            if (this.mCachedCustomerID == null) {
                log.debug("getCustomerId", "Cached customer id is null. Creating a new one", new Object[0]);
                this.mCachedCustomerID = getOrCreateCustomerIDSharedPref(this.mSharedPrefsCustomerIDKey);
            }
            return this.mCachedCustomerID;
        }
        return super.getCustomerID();
    }

    private void initialize(Context context, String str, String str2, String str3, boolean z) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        this.mSharedPrefsDSNKey = str;
        this.mSharedPrefsSessionIDKey = str2;
        this.mSharedPrefsCustomerIDKey = str3;
        this.mSharedPrefs = context.getSharedPreferences("com.amazon.client.metrics", 0);
        this.mCachedDSN = getOrCreateDSNSharedPref(this.mSharedPrefsDSNKey);
        this.mCachedSessionID = getOrCreateSessionIDSharedPref(this.mSharedPrefsSessionIDKey);
        this.mCachedCustomerID = getOrCreateCustomerIDSharedPref(this.mSharedPrefsCustomerIDKey);
        this.mLegacyDeviceID = getLegacyDSNSharedPref();
    }

    private String getOrCreateDSNSharedPref(String str) {
        String string = this.mSharedPrefs.getString(str, null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String strCreateRandomDSN = super.createRandomDSN();
        saveToSharedPref(str, strCreateRandomDSN);
        return strCreateRandomDSN;
    }

    private String getOrCreateCustomerIDSharedPref(String str) {
        String string = this.mSharedPrefs.getString(str, null);
        DPLogger dPLogger = log;
        dPLogger.debug("getOrCreateCustomerIDSharedPref", "Obtained CustomerID: " + string, new Object[0]);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        dPLogger.debug("getOrCreateCustomerIDSharedPref", "CID is empty", new Object[0]);
        String strCreateCustomerID = super.createCustomerID();
        dPLogger.debug("getOrCreateCustomerIDSharedPref", "Created CustomerID: " + strCreateCustomerID, new Object[0]);
        saveToSharedPref(str, strCreateCustomerID);
        return strCreateCustomerID;
    }

    private String getOrCreateSessionIDSharedPref(String str) {
        String string = this.mSharedPrefs.getString(str, null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String strCreateSessionId = super.createSessionId();
        saveToSharedPref(str, strCreateSessionId);
        return strCreateSessionId;
    }

    protected String getLegacyDSNSharedPref() {
        return this.mSharedPrefs.getString("secondaryUUID", null);
    }

    private void saveToSharedPref(String str, String str2) {
        SharedPreferences.Editor editorEdit = this.mSharedPrefs.edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }
}
