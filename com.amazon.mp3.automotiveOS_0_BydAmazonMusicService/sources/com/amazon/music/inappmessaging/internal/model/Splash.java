package com.amazon.music.inappmessaging.internal.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.amazon.music.inappmessaging.external.IAMCredentials;
import com.amazon.music.inappmessaging.external.TokenProvider;
import com.amazon.music.inappmessaging.internal.service.InAppMessageJson;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class Splash implements Parcelable {
    public static final String AUTH_TOKEN = "authToken";
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.amazon.music.inappmessaging.internal.model.Splash.1
        @Override // android.os.Parcelable.Creator
        public Splash createFromParcel(Parcel in) {
            return new Splash(in);
        }

        @Override // android.os.Parcelable.Creator
        public Splash[] newArray(int size) {
            return new Splash[size];
        }
    };
    public static final String NAME = "splash";
    public static final String PARAMS_APP_VERSION = "appVersion";
    public static final String PARAMS_CAMPAIGN_ID = "campaignId";
    public static final String PARAMS_CONTENT_INFO_INST_ID = "contentInfoInstId";
    public static final String PARAMS_DEVICE_ID = "deviceId";
    public static final String PARAMS_DEVICE_TYPE = "deviceType";
    public static final String PARAMS_DIRECTED_ID = "directedId";
    public static final String PARAMS_IAM_REF = "iamRef";
    public static final String PARAMS_LOCALE = "locale";
    public static final String PARAMS_MUSIC_TERRITORY = "musicTerritory";
    public static final String PARAMS_SESSION_ID = "sessionId";
    public static final String PARAMS_WEBLAB_ID = "weblabId";
    public final TokenProvider authTokenProvider;
    public final String campaignId;
    public final String contentInfoInstId;
    public Map<String, String> customerInfo;
    public final String generatedUrl;
    public final String iamRef;
    public final String responseType;
    public final String treatmentId;
    public final String trigger;
    public final String type;
    public final String url;
    public final String weblabId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(this.type);
        dest.writeString(this.responseType);
        dest.writeString(this.trigger);
        dest.writeString(this.campaignId);
        dest.writeString(this.weblabId);
        dest.writeString(this.treatmentId);
        dest.writeString(this.iamRef);
        dest.writeString(this.url);
        dest.writeString(this.generatedUrl);
        dest.writeSerializable(this.authTokenProvider);
        dest.writeString(this.contentInfoInstId);
        dest.writeMap(this.customerInfo);
    }

    public Splash(final Parcel in) {
        this.customerInfo = new HashMap();
        this.type = in.readString();
        this.responseType = in.readString();
        this.trigger = in.readString();
        this.campaignId = in.readString();
        this.weblabId = in.readString();
        this.treatmentId = in.readString();
        this.iamRef = in.readString();
        this.url = in.readString();
        this.generatedUrl = in.readString();
        this.authTokenProvider = (TokenProvider) in.readSerializable();
        this.contentInfoInstId = in.readString();
        in.readMap(this.customerInfo, Map.class.getClassLoader());
    }

    public Splash(final Builder builder) {
        this.type = builder.type;
        this.responseType = builder.responseType;
        this.trigger = builder.trigger;
        this.campaignId = builder.campaignId;
        this.weblabId = builder.weblabId;
        this.treatmentId = builder.treatmentId;
        this.iamRef = builder.iamRef;
        this.url = builder.url;
        this.authTokenProvider = builder.authTokenProvider;
        this.customerInfo = builder.customerInfo;
        this.contentInfoInstId = builder.contentInfoInstId;
        this.generatedUrl = generateUrl(Locale.getDefault());
    }

    String generateUrl(final Locale locale) {
        Uri.Builder builderBuildUpon = Uri.parse(this.url).buildUpon();
        for (Map.Entry<String, String> entry : this.customerInfo.entrySet()) {
            builderBuildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        builderBuildUpon.appendQueryParameter(PARAMS_CAMPAIGN_ID, this.campaignId);
        builderBuildUpon.appendQueryParameter(PARAMS_WEBLAB_ID, this.weblabId);
        builderBuildUpon.appendQueryParameter(PARAMS_LOCALE, locale.toString());
        builderBuildUpon.appendQueryParameter("contentInfoInstId", this.contentInfoInstId);
        builderBuildUpon.appendQueryParameter(PARAMS_IAM_REF, this.iamRef);
        return builderBuildUpon.build().toString();
    }

    @Nullable
    public static Splash fromInAppMessageJson(final InAppMessageJson inAppMessageJson, final IAMCredentials iamCredentials) {
        HashMap map = new HashMap();
        map.put("directedId", iamCredentials.getDirectedId());
        map.put(PARAMS_DEVICE_TYPE, iamCredentials.getDeviceType());
        map.put(PARAMS_DEVICE_ID, iamCredentials.getDeviceId());
        map.put(PARAMS_SESSION_ID, iamCredentials.getSessionId());
        map.put(PARAMS_APP_VERSION, iamCredentials.getAppVersion());
        return new Builder().withType(inAppMessageJson.type).withResponseType(inAppMessageJson.responseType).withTrigger(inAppMessageJson.trigger).withCampaignId(inAppMessageJson.campaignId).withWeblabId(inAppMessageJson.weblabId).withTreatmentId(inAppMessageJson.treatmentId).withIamRef(inAppMessageJson.iamRef).withContentInfoInstId(inAppMessageJson.contentInfoInstId).withUrl(inAppMessageJson.splash.url).withCustomerInfo(map).withAuthTokenProvider(iamCredentials.getTokenProvider()).build();
    }

    public static class Builder {
        private TokenProvider authTokenProvider;
        private String campaignId;
        private String contentInfoInstId;
        private Map<String, String> customerInfo = Collections.emptyMap();
        private String iamRef;
        private String responseType;
        private String treatmentId;
        private String trigger;
        private String type;
        private String url;
        private String weblabId;

        public Builder withType(final String type) {
            this.type = type;
            return this;
        }

        public Builder withResponseType(final String type) {
            this.responseType = type;
            return this;
        }

        public Builder withTrigger(final String trigger) {
            this.trigger = trigger;
            return this;
        }

        public Builder withCampaignId(final String campaignId) {
            this.campaignId = campaignId;
            return this;
        }

        public Builder withWeblabId(final String weblabId) {
            this.weblabId = weblabId;
            return this;
        }

        public Builder withTreatmentId(final String treatmentId) {
            this.treatmentId = treatmentId;
            return this;
        }

        public Builder withIamRef(final String iamRef) {
            this.iamRef = iamRef;
            return this;
        }

        public Builder withUrl(final String url) {
            this.url = url;
            return this;
        }

        public Builder withAuthTokenProvider(final TokenProvider authTokenProvider) {
            this.authTokenProvider = authTokenProvider;
            return this;
        }

        public Builder withContentInfoInstId(final String contentInfoInstId) {
            this.contentInfoInstId = contentInfoInstId;
            return this;
        }

        public Builder withCustomerInfo(final Map<String, String> queryParams) {
            this.customerInfo = queryParams;
            return this;
        }

        public Splash build() {
            return new Splash(this);
        }
    }
}
