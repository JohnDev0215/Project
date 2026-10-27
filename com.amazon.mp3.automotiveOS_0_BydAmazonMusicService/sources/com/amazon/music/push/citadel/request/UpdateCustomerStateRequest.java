package com.amazon.music.push.citadel.request;

import android.content.Context;
import com.amazon.music.push.citadel.CitadelManager;
import com.amazon.music.push.citadel.exception.CitadelUpdateInvalidException;
import com.amazon.music.push.citadel.exception.CitadelUpdateOutdatedException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class UpdateCustomerStateRequest extends CitadelRequest {
    private final String application;
    private final String applicationVersion;
    private final String hardware;
    private final String os;
    private final String osNotification;
    private final String osVersion;
    private final String provider;
    private final String providerKey;
    private final List<String> pushTopics;
    private final String registeredAt;
    private final Integer sequenceNumber;
    private final String token;

    @Override // com.amazon.music.push.citadel.request.CitadelRequest
    public String toString() {
        return "UpdateCustomerStateRequest(super=" + super.toString() + ", application=" + getApplication() + ", applicationVersion=" + getApplicationVersion() + ", hardware=" + getHardware() + ", os=" + getOs() + ", osVersion=" + getOsVersion() + ", provider=" + getProvider() + ", providerKey=" + getProviderKey() + ", token=" + getToken() + ", osNotification=" + getOsNotification() + ", sequenceNumber=" + getSequenceNumber() + ", registeredAt=" + getRegisteredAt() + ", pushTopics=" + getPushTopics() + ")";
    }

    public String getApplication() {
        return this.application;
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public String getHardware() {
        return this.hardware;
    }

    public String getOs() {
        return this.os;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getProvider() {
        return this.provider;
    }

    public String getProviderKey() {
        return this.providerKey;
    }

    public String getToken() {
        return this.token;
    }

    public String getOsNotification() {
        return this.osNotification;
    }

    public Integer getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getRegisteredAt() {
        return this.registeredAt;
    }

    public List<String> getPushTopics() {
        return this.pushTopics;
    }

    public UpdateCustomerStateRequest(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, Integer num, String str18, List<String> list) {
        super(str, str2, str3, str4, str5, str6, str7, str8);
        this.application = str9;
        this.applicationVersion = str10;
        this.hardware = str11;
        this.os = str12;
        this.osVersion = str13;
        this.provider = str14;
        this.providerKey = str15;
        this.token = str16;
        this.osNotification = str17;
        this.sequenceNumber = num;
        this.registeredAt = str18;
        this.pushTopics = list;
    }

    @Override // com.amazon.music.push.citadel.request.CitadelRequest
    public void validate(Context context) {
        if (!CitadelManager.challengeLatestStateUpdate(context, Long.valueOf(Long.parseLong(getDate())))) {
            throw new CitadelUpdateOutdatedException(String.format("Citadel update with timestamp '%s' dropped due to being outdated", getDate()));
        }
        super.validate(context);
        if (StringUtils.isBlank(this.application)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "application"));
        }
        if (StringUtils.isBlank(this.applicationVersion)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "applicationVersion"));
        }
        if (StringUtils.isBlank(this.hardware)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "hardware"));
        }
        if (StringUtils.isBlank(this.os)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "os"));
        }
        if (StringUtils.isBlank(this.osVersion)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "osVersion"));
        }
        if (StringUtils.isBlank(this.provider)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "provider"));
        }
        if (StringUtils.isBlank(this.providerKey)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "providerKey"));
        }
        if (StringUtils.isBlank(this.token)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "token"));
        }
        if (StringUtils.isBlank(this.osNotification)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "osNotification"));
        }
        if (this.sequenceNumber == null) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "sequenceNumber"));
        }
        if (this.pushTopics == null) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "pushTopics"));
        }
    }

    @Override // com.amazon.music.push.citadel.request.CitadelRequest
    public String asJson() {
        Gson gsonCreate = new GsonBuilder().addSerializationExclusionStrategy(new CitadelSerializationExclusionStrategy()).create();
        JsonObject jsonObject = (JsonObject) gsonCreate.fromJson(super.asJson(), JsonObject.class);
        jsonObject.add("stateInfo", gsonCreate.toJsonTree(this));
        return gsonCreate.toJson((JsonElement) jsonObject);
    }

    @Override // com.amazon.music.push.citadel.request.CitadelRequest
    public CitadelRequestType getType() {
        return CitadelRequestType.STATE;
    }
}
