package com.amazon.digitalmusicxp.models;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.SupportedFeatureEnum;
import com.amazon.digitalmusicxp.types.AdContext;
import com.amazon.digitalmusicxp.types.ParentalControls;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerDeviceSettings.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001b\b\u0086\b\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\t\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u0012¢\u0006\u0004\b3\u00104J\u009f\u0001\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\t2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0012HÆ\u0001J\t\u0010\u0016\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0017HÖ\u0001J\u0013\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b \u0010\u001eR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b!\u0010\u001eR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\"\u0010\u001eR\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u001c\u001a\u0004\b#\u0010\u001eR\u001f\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001c\u001a\u0004\b'\u0010\u001eR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010(\u001a\u0004\b)\u0010*R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010+\u001a\u0004\b,\u0010-R\u001f\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u0011\u0010$\u001a\u0004\b.\u0010&R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010/\u001a\u0004\b0\u00101R\u0017\u0010\u0014\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0014\u0010/\u001a\u0004\b2\u00101¨\u00065"}, d2 = {"Lcom/amazon/digitalmusicxp/models/CustomerDeviceSettings;", "", "", "customerId", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "id", Splash.PARAMS_SESSION_ID, Splash.PARAMS_LOCALE, "", "languageOfPreference", "timezone", "Lcom/amazon/digitalmusicxp/types/AdContext;", "adContext", "Lcom/amazon/digitalmusicxp/types/ParentalControls;", "parentalControls", "Lcom/amazon/digitalmusicxp/enums/SupportedFeatureEnum;", "supportedFeatures", "", "createdAt", "updatedAt", "copy", "toString", "", "hashCode", "other", "", "equals", "Ljava/lang/String;", "getCustomerId", "()Ljava/lang/String;", "getDeviceId", "getDeviceType", "getId", "getSessionId", "getLocale", "Ljava/util/List;", "getLanguageOfPreference", "()Ljava/util/List;", "getTimezone", "Lcom/amazon/digitalmusicxp/types/AdContext;", "getAdContext", "()Lcom/amazon/digitalmusicxp/types/AdContext;", "Lcom/amazon/digitalmusicxp/types/ParentalControls;", "getParentalControls", "()Lcom/amazon/digitalmusicxp/types/ParentalControls;", "getSupportedFeatures", "J", "getCreatedAt", "()J", "getUpdatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/AdContext;Lcom/amazon/digitalmusicxp/types/ParentalControls;Ljava/util/List;JJ)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class CustomerDeviceSettings {
    private final AdContext adContext;
    private final long createdAt;
    private final String customerId;
    private final String deviceId;
    private final String deviceType;
    private final String id;
    private final List<String> languageOfPreference;
    private final String locale;
    private final ParentalControls parentalControls;
    private final String sessionId;
    private final List<SupportedFeatureEnum> supportedFeatures;
    private final String timezone;
    private final long updatedAt;

    public final CustomerDeviceSettings copy(String customerId, String deviceId, String deviceType, String id, String sessionId, String locale, List<String> languageOfPreference, String timezone, AdContext adContext, ParentalControls parentalControls, List<? extends SupportedFeatureEnum> supportedFeatures, long createdAt, long updatedAt) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(locale, "locale");
        Intrinsics.checkNotNullParameter(timezone, "timezone");
        Intrinsics.checkNotNullParameter(adContext, "adContext");
        Intrinsics.checkNotNullParameter(parentalControls, "parentalControls");
        return new CustomerDeviceSettings(customerId, deviceId, deviceType, id, sessionId, locale, languageOfPreference, timezone, adContext, parentalControls, supportedFeatures, createdAt, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerDeviceSettings)) {
            return false;
        }
        CustomerDeviceSettings customerDeviceSettings = (CustomerDeviceSettings) other;
        return Intrinsics.areEqual(this.customerId, customerDeviceSettings.customerId) && Intrinsics.areEqual(this.deviceId, customerDeviceSettings.deviceId) && Intrinsics.areEqual(this.deviceType, customerDeviceSettings.deviceType) && Intrinsics.areEqual(this.id, customerDeviceSettings.id) && Intrinsics.areEqual(this.sessionId, customerDeviceSettings.sessionId) && Intrinsics.areEqual(this.locale, customerDeviceSettings.locale) && Intrinsics.areEqual(this.languageOfPreference, customerDeviceSettings.languageOfPreference) && Intrinsics.areEqual(this.timezone, customerDeviceSettings.timezone) && Intrinsics.areEqual(this.adContext, customerDeviceSettings.adContext) && Intrinsics.areEqual(this.parentalControls, customerDeviceSettings.parentalControls) && Intrinsics.areEqual(this.supportedFeatures, customerDeviceSettings.supportedFeatures) && this.createdAt == customerDeviceSettings.createdAt && this.updatedAt == customerDeviceSettings.updatedAt;
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
        return ((((iHashCode4 + (list2 != null ? list2.hashCode() : 0)) * 31) + Long.hashCode(this.createdAt)) * 31) + Long.hashCode(this.updatedAt);
    }

    public String toString() {
        return "CustomerDeviceSettings(customerId=" + this.customerId + ", deviceId=" + this.deviceId + ", deviceType=" + this.deviceType + ", id=" + this.id + ", sessionId=" + this.sessionId + ", locale=" + this.locale + ", languageOfPreference=" + this.languageOfPreference + ", timezone=" + this.timezone + ", adContext=" + this.adContext + ", parentalControls=" + this.parentalControls + ", supportedFeatures=" + this.supportedFeatures + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CustomerDeviceSettings(String customerId, String deviceId, String deviceType, String str, String str2, String locale, List<String> list, String timezone, AdContext adContext, ParentalControls parentalControls, List<? extends SupportedFeatureEnum> list2, long j, long j2) {
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
        this.createdAt = j;
        this.updatedAt = j2;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ CustomerDeviceSettings(String str, String str2, String str3, String str4, String str5, String str6, List list, String str7, AdContext adContext, ParentalControls parentalControls, List list2, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, str6, (i & 64) != 0 ? null : list, str7, adContext, parentalControls, (i & 1024) != 0 ? null : list2, j, j2);
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

    public final AdContext getAdContext() {
        return this.adContext;
    }

    public final ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public final List<SupportedFeatureEnum> getSupportedFeatures() {
        return this.supportedFeatures;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final long getUpdatedAt() {
        return this.updatedAt;
    }
}
