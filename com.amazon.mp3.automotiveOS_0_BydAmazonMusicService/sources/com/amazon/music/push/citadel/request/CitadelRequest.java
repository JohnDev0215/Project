package com.amazon.music.push.citadel.request;

import android.content.Context;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.push.citadel.exception.CitadelUpdateInvalidException;
import com.google.gson.Gson;
import okhttp3.Headers;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class CitadelRequest {
    private final String accessToken;
    private final String appinstallId;
    private final String customerId;
    private final String date;
    private final String deviceId;
    private final String deviceType;
    private final String localeId;
    private final String marketplaceId;

    public CitadelRequestType getType() {
        return null;
    }

    public String toString() {
        return "CitadelRequest(accessToken=" + getAccessToken() + ", customerId=" + getCustomerId() + ", appinstallId=" + getAppinstallId() + ", date=" + getDate() + ", marketplaceId=" + getMarketplaceId() + ", localeId=" + getLocaleId() + ", deviceId=" + getDeviceId() + ", deviceType=" + getDeviceType() + ")";
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getAppinstallId() {
        return this.appinstallId;
    }

    public String getDate() {
        return this.date;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getLocaleId() {
        return this.localeId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public CitadelRequest(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.accessToken = str;
        this.customerId = str2;
        this.appinstallId = str3;
        this.date = str4;
        this.marketplaceId = str5;
        this.localeId = str6;
        this.deviceId = str7;
        this.deviceType = str8;
    }

    public void validate(Context context) {
        if (StringUtils.isBlank(this.accessToken)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "accessToken"));
        }
        if (StringUtils.isBlank(this.customerId)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "customerId"));
        }
        if (StringUtils.isBlank(this.appinstallId)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "appinstallId"));
        }
        if (StringUtils.isBlank(this.date)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "date"));
        }
        if (StringUtils.isBlank(this.marketplaceId)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "marketplaceId"));
        }
        if (StringUtils.isBlank(this.localeId)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "localeId"));
        }
        if (StringUtils.isBlank(this.deviceId)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", Splash.PARAMS_DEVICE_ID));
        }
        if (StringUtils.isBlank(this.deviceType)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", Splash.PARAMS_DEVICE_TYPE));
        }
    }

    public Headers getHeaders() {
        return new Headers.Builder().add("X-Amz-Access-Token", this.accessToken).add("X-Amz-Device-Id", this.deviceId).add("X-Amz-Device-Type", this.deviceType).build();
    }

    public String asJson() {
        Gson gson = new Gson();
        return gson.toJson(gson.fromJson(gson.toJson(this), CitadelRequest.class));
    }
}
