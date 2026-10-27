package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
@Index(fields = {"customerId", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE}, name = SQLiteCommandFactory.UNDEFINED)
@ModelConfig(pluralName = "CustomerDeviceSettings")
public final class CustomerDeviceSettings implements Model {

    @ModelField(isRequired = true, targetType = "AdContext")
    private final AdContext adContext;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime createdAt;

    @ModelField(isRequired = true, targetType = "String")
    private final String customerId;

    @ModelField(isRequired = true, targetType = "String")
    private final String deviceId;

    @ModelField(isRequired = true, targetType = "String")
    private final String deviceType;

    @ModelField(isRequired = true, targetType = "ID")
    private final String id;

    @ModelField(targetType = "String")
    private final List<String> languageOfPreference;

    @ModelField(isRequired = true, targetType = "String")
    private final String locale;

    @ModelField(isRequired = true, targetType = "ParentalControls")
    private final ParentalControls parentalControls;

    @ModelField(targetType = "String")
    private final String sessionId;

    @ModelField(isRequired = true, targetType = "SupportedFeatureEnum")
    private final List<SupportedFeatureEnum> supportedFeatures;

    @ModelField(isRequired = true, targetType = "String")
    private final List<String> supportedFeaturesEnumValues;

    @ModelField(isRequired = true, targetType = "String")
    private final String timezone;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime updatedAt;
    public static final QueryField ID = QueryField.field("CustomerDeviceSettings", "id");
    public static final QueryField CUSTOMER_ID = QueryField.field("CustomerDeviceSettings", "customerId");
    public static final QueryField DEVICE_ID = QueryField.field("CustomerDeviceSettings", Splash.PARAMS_DEVICE_ID);
    public static final QueryField DEVICE_TYPE = QueryField.field("CustomerDeviceSettings", Splash.PARAMS_DEVICE_TYPE);
    public static final QueryField SESSION_ID = QueryField.field("CustomerDeviceSettings", Splash.PARAMS_SESSION_ID);
    public static final QueryField LOCALE = QueryField.field("CustomerDeviceSettings", Splash.PARAMS_LOCALE);
    public static final QueryField LANGUAGE_OF_PREFERENCE = QueryField.field("CustomerDeviceSettings", "languageOfPreference");
    public static final QueryField TIMEZONE = QueryField.field("CustomerDeviceSettings", "timezone");
    public static final QueryField AD_CONTEXT = QueryField.field("CustomerDeviceSettings", "adContext");
    public static final QueryField PARENTAL_CONTROLS = QueryField.field("CustomerDeviceSettings", "parentalControls");
    public static final QueryField SUPPORTED_FEATURES = QueryField.field("CustomerDeviceSettings", "supportedFeatures");
    public static final QueryField SUPPORTED_FEATURES_ENUM_VALUES = QueryField.field("CustomerDeviceSettings", "supportedFeaturesEnumValues");
    public static final QueryField CREATED_AT = QueryField.field("CustomerDeviceSettings", "createdAt");
    public static final QueryField UPDATED_AT = QueryField.field("CustomerDeviceSettings", "updatedAt");

    public interface AdContextStep {
        ParentalControlsStep adContext(AdContext adContext);
    }

    public interface BuildStep {
        CustomerDeviceSettings build();

        BuildStep id(String str);

        BuildStep languageOfPreference(List<String> list);

        BuildStep sessionId(String str);
    }

    public interface CreatedAtStep {
        UpdatedAtStep createdAt(Temporal.DateTime dateTime);
    }

    public interface CustomerIdStep {
        DeviceIdStep customerId(String str);
    }

    public interface DeviceIdStep {
        DeviceTypeStep deviceId(String str);
    }

    public interface DeviceTypeStep {
        LocaleStep deviceType(String str);
    }

    public interface LocaleStep {
        TimezoneStep locale(String str);
    }

    public interface ParentalControlsStep {
        SupportedFeaturesStep parentalControls(ParentalControls parentalControls);
    }

    public interface SupportedFeaturesEnumValuesStep {
        CreatedAtStep supportedFeaturesEnumValues(List<String> list);
    }

    public interface SupportedFeaturesStep {
        SupportedFeaturesEnumValuesStep supportedFeatures(List<SupportedFeatureEnum> list);
    }

    public interface TimezoneStep {
        AdContextStep timezone(String str);
    }

    public interface UpdatedAtStep {
        BuildStep updatedAt(Temporal.DateTime dateTime);
    }

    public String getId() {
        return this.id;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String getLocale() {
        return this.locale;
    }

    public List<String> getLanguageOfPreference() {
        return this.languageOfPreference;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public AdContext getAdContext() {
        return this.adContext;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public List<SupportedFeatureEnum> getSupportedFeatures() {
        return this.supportedFeatures;
    }

    public List<String> getSupportedFeaturesEnumValues() {
        return this.supportedFeaturesEnumValues;
    }

    public Temporal.DateTime getCreatedAt() {
        return this.createdAt;
    }

    public Temporal.DateTime getUpdatedAt() {
        return this.updatedAt;
    }

    private CustomerDeviceSettings(String str, String str2, String str3, String str4, String str5, String str6, List<String> list, String str7, AdContext adContext, ParentalControls parentalControls, List<SupportedFeatureEnum> list2, List<String> list3, Temporal.DateTime dateTime, Temporal.DateTime dateTime2) {
        this.id = str;
        this.customerId = str2;
        this.deviceId = str3;
        this.deviceType = str4;
        this.sessionId = str5;
        this.locale = str6;
        this.languageOfPreference = list;
        this.timezone = str7;
        this.adContext = adContext;
        this.parentalControls = parentalControls;
        this.supportedFeatures = list2;
        this.supportedFeaturesEnumValues = list3;
        this.createdAt = dateTime;
        this.updatedAt = dateTime2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CustomerDeviceSettings.class != obj.getClass()) {
            return false;
        }
        CustomerDeviceSettings customerDeviceSettings = (CustomerDeviceSettings) obj;
        return ObjectsCompat.equals(getId(), customerDeviceSettings.getId()) && ObjectsCompat.equals(getCustomerId(), customerDeviceSettings.getCustomerId()) && ObjectsCompat.equals(getDeviceId(), customerDeviceSettings.getDeviceId()) && ObjectsCompat.equals(getDeviceType(), customerDeviceSettings.getDeviceType()) && ObjectsCompat.equals(getSessionId(), customerDeviceSettings.getSessionId()) && ObjectsCompat.equals(getLocale(), customerDeviceSettings.getLocale()) && ObjectsCompat.equals(getLanguageOfPreference(), customerDeviceSettings.getLanguageOfPreference()) && ObjectsCompat.equals(getTimezone(), customerDeviceSettings.getTimezone()) && ObjectsCompat.equals(getAdContext(), customerDeviceSettings.getAdContext()) && ObjectsCompat.equals(getParentalControls(), customerDeviceSettings.getParentalControls()) && ObjectsCompat.equals(getSupportedFeatures(), customerDeviceSettings.getSupportedFeatures()) && ObjectsCompat.equals(getSupportedFeaturesEnumValues(), customerDeviceSettings.getSupportedFeaturesEnumValues()) && ObjectsCompat.equals(getCreatedAt(), customerDeviceSettings.getCreatedAt()) && ObjectsCompat.equals(getUpdatedAt(), customerDeviceSettings.getUpdatedAt());
    }

    public int hashCode() {
        return (getId() + getCustomerId() + getDeviceId() + getDeviceType() + getSessionId() + getLocale() + getLanguageOfPreference() + getTimezone() + getAdContext() + getParentalControls() + getSupportedFeatures() + getSupportedFeaturesEnumValues() + getCreatedAt() + getUpdatedAt()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CustomerDeviceSettings {");
        sb.append("id=" + String.valueOf(getId()) + ", ");
        sb.append("customerId=" + String.valueOf(getCustomerId()) + ", ");
        sb.append("deviceId=" + String.valueOf(getDeviceId()) + ", ");
        sb.append("deviceType=" + String.valueOf(getDeviceType()) + ", ");
        sb.append("sessionId=" + String.valueOf(getSessionId()) + ", ");
        sb.append("locale=" + String.valueOf(getLocale()) + ", ");
        sb.append("languageOfPreference=" + String.valueOf(getLanguageOfPreference()) + ", ");
        sb.append("timezone=" + String.valueOf(getTimezone()) + ", ");
        sb.append("adContext=" + String.valueOf(getAdContext()) + ", ");
        sb.append("parentalControls=" + String.valueOf(getParentalControls()) + ", ");
        sb.append("supportedFeatures=" + String.valueOf(getSupportedFeatures()) + ", ");
        sb.append("supportedFeaturesEnumValues=" + String.valueOf(getSupportedFeaturesEnumValues()) + ", ");
        sb.append("createdAt=" + String.valueOf(getCreatedAt()) + ", ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("updatedAt=");
        sb2.append(String.valueOf(getUpdatedAt()));
        sb.append(sb2.toString());
        sb.append("}");
        return sb.toString();
    }

    public static CustomerIdStep builder() {
        return new Builder();
    }

    public CopyOfBuilder copyOfBuilder() {
        return new CopyOfBuilder(this.id, this.customerId, this.deviceId, this.deviceType, this.sessionId, this.locale, this.languageOfPreference, this.timezone, this.adContext, this.parentalControls, this.supportedFeatures, this.supportedFeaturesEnumValues, this.createdAt, this.updatedAt);
    }

    public static class Builder implements CustomerIdStep, DeviceIdStep, DeviceTypeStep, LocaleStep, TimezoneStep, AdContextStep, ParentalControlsStep, SupportedFeaturesStep, SupportedFeaturesEnumValuesStep, CreatedAtStep, UpdatedAtStep, BuildStep {
        private AdContext adContext;
        private Temporal.DateTime createdAt;
        private String customerId;
        private String deviceId;
        private String deviceType;
        private String id;
        private List<String> languageOfPreference;
        private String locale;
        private ParentalControls parentalControls;
        private String sessionId;
        private List<SupportedFeatureEnum> supportedFeatures;
        private List<String> supportedFeaturesEnumValues;
        private String timezone;
        private Temporal.DateTime updatedAt;

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.BuildStep
        public CustomerDeviceSettings build() {
            String string = this.id;
            if (string == null) {
                string = UUID.randomUUID().toString();
            }
            return new CustomerDeviceSettings(string, this.customerId, this.deviceId, this.deviceType, this.sessionId, this.locale, this.languageOfPreference, this.timezone, this.adContext, this.parentalControls, this.supportedFeatures, this.supportedFeaturesEnumValues, this.createdAt, this.updatedAt);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.CustomerIdStep
        public DeviceIdStep customerId(String str) {
            Objects.requireNonNull(str);
            this.customerId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.DeviceIdStep
        public DeviceTypeStep deviceId(String str) {
            Objects.requireNonNull(str);
            this.deviceId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.DeviceTypeStep
        public LocaleStep deviceType(String str) {
            Objects.requireNonNull(str);
            this.deviceType = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.LocaleStep
        public TimezoneStep locale(String str) {
            Objects.requireNonNull(str);
            this.locale = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.TimezoneStep
        public AdContextStep timezone(String str) {
            Objects.requireNonNull(str);
            this.timezone = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.AdContextStep
        public ParentalControlsStep adContext(AdContext adContext) {
            Objects.requireNonNull(adContext);
            this.adContext = adContext;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.ParentalControlsStep
        public SupportedFeaturesStep parentalControls(ParentalControls parentalControls) {
            Objects.requireNonNull(parentalControls);
            this.parentalControls = parentalControls;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.SupportedFeaturesStep
        public SupportedFeaturesEnumValuesStep supportedFeatures(List<SupportedFeatureEnum> list) {
            Objects.requireNonNull(list);
            this.supportedFeatures = list;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.SupportedFeaturesEnumValuesStep
        public CreatedAtStep supportedFeaturesEnumValues(List<String> list) {
            Objects.requireNonNull(list);
            this.supportedFeaturesEnumValues = list;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.CreatedAtStep
        public UpdatedAtStep createdAt(Temporal.DateTime dateTime) {
            Objects.requireNonNull(dateTime);
            this.createdAt = dateTime;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.UpdatedAtStep
        public BuildStep updatedAt(Temporal.DateTime dateTime) {
            Objects.requireNonNull(dateTime);
            this.updatedAt = dateTime;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.BuildStep
        public BuildStep sessionId(String str) {
            this.sessionId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.BuildStep
        public BuildStep languageOfPreference(List<String> list) {
            this.languageOfPreference = list;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.BuildStep
        public BuildStep id(String str) {
            this.id = str;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.BuildStep
        public /* bridge */ /* synthetic */ BuildStep languageOfPreference(List list) {
            return languageOfPreference((List<String>) list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.SupportedFeaturesStep
        public /* bridge */ /* synthetic */ SupportedFeaturesEnumValuesStep supportedFeatures(List list) {
            return supportedFeatures((List<SupportedFeatureEnum>) list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.SupportedFeaturesEnumValuesStep
        public /* bridge */ /* synthetic */ CreatedAtStep supportedFeaturesEnumValues(List list) {
            return supportedFeaturesEnumValues((List<String>) list);
        }

        private CopyOfBuilder(String str, String str2, String str3, String str4, String str5, String str6, List<String> list, String str7, AdContext adContext, ParentalControls parentalControls, List<SupportedFeatureEnum> list2, List<String> list3, Temporal.DateTime dateTime, Temporal.DateTime dateTime2) {
            super.id(str);
            super.customerId(str2).deviceId(str3).deviceType(str4).locale(str6).timezone(str7).adContext(adContext).parentalControls(parentalControls).supportedFeatures(list2).supportedFeaturesEnumValues(list3).createdAt(dateTime).updatedAt(dateTime2).sessionId(str5).languageOfPreference(list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.CustomerIdStep
        public CopyOfBuilder customerId(String str) {
            return (CopyOfBuilder) super.customerId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.DeviceIdStep
        public CopyOfBuilder deviceId(String str) {
            return (CopyOfBuilder) super.deviceId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.DeviceTypeStep
        public CopyOfBuilder deviceType(String str) {
            return (CopyOfBuilder) super.deviceType(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.LocaleStep
        public CopyOfBuilder locale(String str) {
            return (CopyOfBuilder) super.locale(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.TimezoneStep
        public CopyOfBuilder timezone(String str) {
            return (CopyOfBuilder) super.timezone(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.AdContextStep
        public CopyOfBuilder adContext(AdContext adContext) {
            return (CopyOfBuilder) super.adContext(adContext);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.ParentalControlsStep
        public CopyOfBuilder parentalControls(ParentalControls parentalControls) {
            return (CopyOfBuilder) super.parentalControls(parentalControls);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.SupportedFeaturesStep
        public CopyOfBuilder supportedFeatures(List<SupportedFeatureEnum> list) {
            return (CopyOfBuilder) super.supportedFeatures(list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.SupportedFeaturesEnumValuesStep
        public CopyOfBuilder supportedFeaturesEnumValues(List<String> list) {
            return (CopyOfBuilder) super.supportedFeaturesEnumValues(list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.CreatedAtStep
        public CopyOfBuilder createdAt(Temporal.DateTime dateTime) {
            return (CopyOfBuilder) super.createdAt(dateTime);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.UpdatedAtStep
        public CopyOfBuilder updatedAt(Temporal.DateTime dateTime) {
            return (CopyOfBuilder) super.updatedAt(dateTime);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.BuildStep
        public CopyOfBuilder sessionId(String str) {
            return (CopyOfBuilder) super.sessionId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettings.BuildStep
        public CopyOfBuilder languageOfPreference(List<String> list) {
            return (CopyOfBuilder) super.languageOfPreference(list);
        }
    }
}
