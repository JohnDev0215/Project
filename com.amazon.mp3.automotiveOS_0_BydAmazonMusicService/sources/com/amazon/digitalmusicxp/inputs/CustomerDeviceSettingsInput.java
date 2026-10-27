package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.SupportedFeatureEnum;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerDeviceSettingsInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B{\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0017\u0012\u0006\u0010\u001c\u001a\u00020\u0002\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010$\u001a\u00020#\u0012\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\u0017¢\u0006\u0004\b+\u0010,J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0014\u0010\fR\u0017\u0010\u0015\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\fR\u001f\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\n\u001a\u0004\b\u001d\u0010\fR\u0017\u0010\u001f\u001a\u00020\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010$\u001a\u00020#8\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u001f\u0010)\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\u00178\u0006¢\u0006\f\n\u0004\b)\u0010\u0019\u001a\u0004\b*\u0010\u001b¨\u0006-"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/CustomerDeviceSettingsInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "customerId", "Ljava/lang/String;", "getCustomerId", "()Ljava/lang/String;", Splash.PARAMS_DEVICE_ID, "getDeviceId", Splash.PARAMS_DEVICE_TYPE, "getDeviceType", "id", "getId", Splash.PARAMS_SESSION_ID, "getSessionId", Splash.PARAMS_LOCALE, "getLocale", "", "languageOfPreference", "Ljava/util/List;", "getLanguageOfPreference", "()Ljava/util/List;", "timezone", "getTimezone", "Lcom/amazon/digitalmusicxp/inputs/AdContextInput;", "adContext", "Lcom/amazon/digitalmusicxp/inputs/AdContextInput;", "getAdContext", "()Lcom/amazon/digitalmusicxp/inputs/AdContextInput;", "Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "parentalControls", "Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "getParentalControls", "()Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;", "Lcom/amazon/digitalmusicxp/enums/SupportedFeatureEnum;", "supportedFeatures", "getSupportedFeatures", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/amazon/digitalmusicxp/inputs/AdContextInput;Lcom/amazon/digitalmusicxp/inputs/ParentalControlsInput;Ljava/util/List;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class CustomerDeviceSettingsInput {
    private final AdContextInput adContext;
    private final String customerId;
    private final String deviceId;
    private final String deviceType;
    private final String id;
    private final List<String> languageOfPreference;
    private final String locale;
    private final ParentalControlsInput parentalControls;
    private final String sessionId;
    private final List<SupportedFeatureEnum> supportedFeatures;
    private final String timezone;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerDeviceSettingsInput)) {
            return false;
        }
        CustomerDeviceSettingsInput customerDeviceSettingsInput = (CustomerDeviceSettingsInput) other;
        return Intrinsics.areEqual(this.customerId, customerDeviceSettingsInput.customerId) && Intrinsics.areEqual(this.deviceId, customerDeviceSettingsInput.deviceId) && Intrinsics.areEqual(this.deviceType, customerDeviceSettingsInput.deviceType) && Intrinsics.areEqual(this.id, customerDeviceSettingsInput.id) && Intrinsics.areEqual(this.sessionId, customerDeviceSettingsInput.sessionId) && Intrinsics.areEqual(this.locale, customerDeviceSettingsInput.locale) && Intrinsics.areEqual(this.languageOfPreference, customerDeviceSettingsInput.languageOfPreference) && Intrinsics.areEqual(this.timezone, customerDeviceSettingsInput.timezone) && Intrinsics.areEqual(this.adContext, customerDeviceSettingsInput.adContext) && Intrinsics.areEqual(this.parentalControls, customerDeviceSettingsInput.parentalControls) && Intrinsics.areEqual(this.supportedFeatures, customerDeviceSettingsInput.supportedFeatures);
    }

    public int hashCode() {
        int iHashCode = ((((this.customerId.hashCode() * 31) + this.deviceId.hashCode()) * 31) + this.deviceType.hashCode()) * 31;
        String str = this.id;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.sessionId;
        int iHashCode3 = (((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.locale.hashCode()) * 31;
        List<String> list = this.languageOfPreference;
        int iHashCode4 = (((((((iHashCode3 + (list == null ? 0 : list.hashCode())) * 31) + this.timezone.hashCode()) * 31) + this.adContext.hashCode()) * 31) + this.parentalControls.hashCode()) * 31;
        List<SupportedFeatureEnum> list2 = this.supportedFeatures;
        return iHashCode4 + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "CustomerDeviceSettingsInput(customerId=" + this.customerId + ", deviceId=" + this.deviceId + ", deviceType=" + this.deviceType + ", id=" + this.id + ", sessionId=" + this.sessionId + ", locale=" + this.locale + ", languageOfPreference=" + this.languageOfPreference + ", timezone=" + this.timezone + ", adContext=" + this.adContext + ", parentalControls=" + this.parentalControls + ", supportedFeatures=" + this.supportedFeatures + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CustomerDeviceSettingsInput(String customerId, String deviceId, String deviceType, String str, String str2, String locale, List<String> list, String timezone, AdContextInput adContext, ParentalControlsInput parentalControls, List<? extends SupportedFeatureEnum> list2) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(locale, "locale");
        Intrinsics.checkNotNullParameter(timezone, "timezone");
        Intrinsics.checkNotNullParameter(adContext, "adContext");
        Intrinsics.checkNotNullParameter(parentalControls, "parentalControls");
        this.customerId = customerId;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.id = str;
        this.sessionId = str2;
        this.locale = locale;
        this.languageOfPreference = list;
        this.timezone = timezone;
        this.adContext = adContext;
        this.parentalControls = parentalControls;
        this.supportedFeatures = list2;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final String getId() {
        return this.id;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final String getLocale() {
        return this.locale;
    }

    public final List<String> getLanguageOfPreference() {
        return this.languageOfPreference;
    }

    public final String getTimezone() {
        return this.timezone;
    }

    public final AdContextInput getAdContext() {
        return this.adContext;
    }

    public final ParentalControlsInput getParentalControls() {
        return this.parentalControls;
    }

    public final List<SupportedFeatureEnum> getSupportedFeatures() {
        return this.supportedFeatures;
    }
}
