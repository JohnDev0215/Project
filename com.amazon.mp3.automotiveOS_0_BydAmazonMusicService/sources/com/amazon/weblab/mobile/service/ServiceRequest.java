package com.amazon.weblab.mobile.service;

import android.content.Context;
import android.net.Uri;
import com.amazon.weblab.mobile.metrics.ApplicationContextHolder;
import com.amazon.weblab.mobile.model.CustomerInfo;
import com.amazon.weblab.mobile.model.MobileWeblabException;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.settings.IMobileWeblabClientAttributes;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
class ServiceRequest {
    private String mAppVersion;
    private Map<String, String> mClientAttributes;
    String mClientIdentifier;
    private String mDirectedId;
    private MobileWeblabDomain mDomainParameterValue;
    private String mMarketplaceId;
    private String mOS;
    private String mOSVersion;
    private String mSessionId;
    private String mXClientId;

    String constructPayload() throws MobileWeblabException {
        throw null;
    }

    ServiceRequest(IMobileWeblabClientAttributes iMobileWeblabClientAttributes, SessionInfo sessionInfo, CustomerInfo customerInfo) {
        if (sessionInfo == null) {
            throw new IllegalArgumentException("info can't be null.");
        }
        if (iMobileWeblabClientAttributes == null) {
            throw new IllegalArgumentException("attributes can't be null.");
        }
        if (iMobileWeblabClientAttributes.getApplicationName() == null || iMobileWeblabClientAttributes.getApplicationName().isEmpty()) {
            throw new IllegalArgumentException("App name can't be null or empty.");
        }
        if (iMobileWeblabClientAttributes.getApplicationVersion() == null || iMobileWeblabClientAttributes.getApplicationVersion().isEmpty()) {
            throw new IllegalArgumentException("App version can't be null or empty.");
        }
        if (iMobileWeblabClientAttributes.getOSName() == null) {
            throw new IllegalArgumentException("OS can't be null.");
        }
        if (iMobileWeblabClientAttributes.getOSVersion() == null || iMobileWeblabClientAttributes.getOSVersion().isEmpty()) {
            throw new IllegalArgumentException("OS version can't be null or empty.");
        }
        if (iMobileWeblabClientAttributes.getClientAttributes() == null) {
            throw new IllegalArgumentException("client attributes can't be null");
        }
        this.mSessionId = sessionInfo.getSessionId();
        this.mMarketplaceId = sessionInfo.getMarketplaceId();
        this.mDirectedId = customerInfo.getDirectedId();
        this.mXClientId = iMobileWeblabClientAttributes.getApplicationName();
        this.mAppVersion = iMobileWeblabClientAttributes.getApplicationVersion();
        this.mOS = iMobileWeblabClientAttributes.getOSName().toString();
        this.mOSVersion = iMobileWeblabClientAttributes.getOSVersion();
        this.mClientAttributes = iMobileWeblabClientAttributes.getClientAttributes();
        this.mClientIdentifier = iMobileWeblabClientAttributes.getIdentifier();
        this.mDomainParameterValue = iMobileWeblabClientAttributes.getDomain();
    }

    Map<String, String> getHttpHeaders() {
        HashMap map = new HashMap();
        map.put("accept", "application/json");
        map.put("x-client-id", this.mXClientId);
        return map;
    }

    void appendParamToUrl(Uri.Builder builder) {
        builder.appendQueryParameter("session-id", this.mSessionId);
        builder.appendQueryParameter("marketplace-id", this.mMarketplaceId);
        String str = this.mDirectedId;
        if (str != null && !str.isEmpty()) {
            builder.appendQueryParameter("directed-id", this.mDirectedId);
        }
        builder.appendQueryParameter("app-version", this.mAppVersion);
        builder.appendQueryParameter("os", this.mOS);
        builder.appendQueryParameter("os-version", this.mOSVersion);
        builder.appendQueryParameter("app-id", getPackageName());
        builder.appendQueryParameter("library-id", "1");
        String hostAppName = getHostAppName();
        if (hostAppName != null) {
            builder.appendQueryParameter("host-app", hostAppName);
        }
        for (Map.Entry<String, String> entry : this.mClientAttributes.entrySet()) {
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        MobileWeblabDomain mobileWeblabDomain = this.mDomainParameterValue;
        if (mobileWeblabDomain == null || mobileWeblabDomain == MobileWeblabDomain.PROD) {
            return;
        }
        builder.appendQueryParameter("stage", mobileWeblabDomain.getDomainValue());
    }

    private String getPackageName() {
        try {
            String packageName = ApplicationContextHolder.getApplicationContext().getPackageName();
            if (packageName.length() <= 50) {
                return packageName;
            }
            return packageName.substring(0, 50) + "...";
        } catch (Exception unused) {
            return "ANDROID_FAILED_PACKAGE_NAME";
        }
    }

    private String getHostAppName() {
        try {
            Context applicationContext = ApplicationContextHolder.getApplicationContext();
            String string = applicationContext.getApplicationInfo().loadLabel(applicationContext.getPackageManager()).toString();
            if (string.equals("")) {
                return null;
            }
            if (string.length() <= 50) {
                return string;
            }
            return string.substring(0, 50) + "...";
        } catch (Exception unused) {
            return null;
        }
    }
}
