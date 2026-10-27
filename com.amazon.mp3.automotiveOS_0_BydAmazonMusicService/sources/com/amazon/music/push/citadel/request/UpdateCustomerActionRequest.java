package com.amazon.music.push.citadel.request;

import android.content.Context;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.push.citadel.exception.CitadelUpdateInvalidException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class UpdateCustomerActionRequest extends CitadelRequest {
    private final String campaignId;
    private final String deeplink;
    private final String pushCategory;
    private final String pushEvent;
    private final List<String> pushTopics;

    @Override // com.amazon.music.push.citadel.request.CitadelRequest
    public String toString() {
        return "UpdateCustomerActionRequest(super=" + super.toString() + ", pushEvent=" + this.pushEvent + ", pushCategory=" + this.pushCategory + ", campaignId=" + this.campaignId + ", deeplink=" + this.deeplink + ", pushTopics=" + this.pushTopics + ")";
    }

    public UpdateCustomerActionRequest(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        super(str, str2, str3, str4, str5, str6, str7, str8);
        this.pushEvent = str9;
        this.pushCategory = str10;
        this.campaignId = str11;
        this.deeplink = str12;
        this.pushTopics = null;
    }

    public UpdateCustomerActionRequest(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, List<String> list) {
        super(str, str2, str3, str4, str5, str6, str7, str8);
        this.pushEvent = str9;
        this.pushCategory = null;
        this.campaignId = null;
        this.deeplink = null;
        this.pushTopics = list;
    }

    @Override // com.amazon.music.push.citadel.request.CitadelRequest
    public void validate(Context context) {
        super.validate(context);
        if (StringUtils.isBlank(this.pushEvent)) {
            throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "pushEvent"));
        }
        if (this.pushEvent.equals("Push Clicked") || this.pushEvent.equals("Push Delivered")) {
            if (StringUtils.isBlank(this.campaignId)) {
                throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", Splash.PARAMS_CAMPAIGN_ID));
            }
            if (StringUtils.isBlank(this.deeplink)) {
                throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "deeplink"));
            }
            return;
        }
        if (this.pushEvent.equals("Subscription OptIn") || this.pushEvent.equals("Subscription OptOut")) {
            if (this.pushTopics == null) {
                throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot be empty", "pushTopics"));
            }
            return;
        }
        throw new CitadelUpdateInvalidException(String.format("Citadel update field '%s' cannot have value '%s'", "pushEvent", this.pushEvent));
    }

    @Override // com.amazon.music.push.citadel.request.CitadelRequest
    public String asJson() {
        Gson gsonCreate = new GsonBuilder().addSerializationExclusionStrategy(new CitadelSerializationExclusionStrategy()).create();
        JsonObject jsonObject = (JsonObject) gsonCreate.fromJson(super.asJson(), JsonObject.class);
        jsonObject.add("actionInfo", gsonCreate.toJsonTree(this));
        return gsonCreate.toJson((JsonElement) jsonObject);
    }

    @Override // com.amazon.music.push.citadel.request.CitadelRequest
    public CitadelRequestType getType() {
        return CitadelRequestType.ACTION;
    }
}
