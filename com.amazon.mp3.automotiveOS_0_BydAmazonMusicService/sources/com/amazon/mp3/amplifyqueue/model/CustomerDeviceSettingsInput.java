package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class CustomerDeviceSettingsInput {
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
    private final List<String> supportedFeaturesEnumValues;
    private final String timezone;

    public interface AdContextStep {
        ParentalControlsStep adContext(AdContextInput adContextInput);
    }

    public interface BuildStep {
        CustomerDeviceSettingsInput build();

        BuildStep id(String str);

        BuildStep languageOfPreference(List<String> list);

        BuildStep sessionId(String str);
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
        SupportedFeaturesStep parentalControls(ParentalControlsInput parentalControlsInput);
    }

    public interface SupportedFeaturesEnumValuesStep {
        BuildStep supportedFeaturesEnumValues(List<String> list);
    }

    public interface SupportedFeaturesStep {
        SupportedFeaturesEnumValuesStep supportedFeatures(List<SupportedFeatureEnum> list);
    }

    public interface TimezoneStep {
        AdContextStep timezone(String str);
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

    public String getId() {
        return this.id;
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

    public AdContextInput getAdContext() {
        return this.adContext;
    }

    public ParentalControlsInput getParentalControls() {
        return this.parentalControls;
    }

    public List<SupportedFeatureEnum> getSupportedFeatures() {
        return this.supportedFeatures;
    }

    public List<String> getSupportedFeaturesEnumValues() {
        return this.supportedFeaturesEnumValues;
    }

    private CustomerDeviceSettingsInput(String str, String str2, String str3, String str4, String str5, String str6, List<String> list, String str7, AdContextInput adContextInput, ParentalControlsInput parentalControlsInput, List<SupportedFeatureEnum> list2, List<String> list3) {
        this.customerId = str;
        this.deviceId = str2;
        this.deviceType = str3;
        this.id = str4;
        this.sessionId = str5;
        this.locale = str6;
        this.languageOfPreference = list;
        this.timezone = str7;
        this.adContext = adContextInput;
        this.parentalControls = parentalControlsInput;
        this.supportedFeatures = list2;
        this.supportedFeaturesEnumValues = list3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CustomerDeviceSettingsInput.class != obj.getClass()) {
            return false;
        }
        CustomerDeviceSettingsInput customerDeviceSettingsInput = (CustomerDeviceSettingsInput) obj;
        return ObjectsCompat.equals(getCustomerId(), customerDeviceSettingsInput.getCustomerId()) && ObjectsCompat.equals(getDeviceId(), customerDeviceSettingsInput.getDeviceId()) && ObjectsCompat.equals(getDeviceType(), customerDeviceSettingsInput.getDeviceType()) && ObjectsCompat.equals(getId(), customerDeviceSettingsInput.getId()) && ObjectsCompat.equals(getSessionId(), customerDeviceSettingsInput.getSessionId()) && ObjectsCompat.equals(getLocale(), customerDeviceSettingsInput.getLocale()) && ObjectsCompat.equals(getLanguageOfPreference(), customerDeviceSettingsInput.getLanguageOfPreference()) && ObjectsCompat.equals(getTimezone(), customerDeviceSettingsInput.getTimezone()) && ObjectsCompat.equals(getAdContext(), customerDeviceSettingsInput.getAdContext()) && ObjectsCompat.equals(getParentalControls(), customerDeviceSettingsInput.getParentalControls()) && ObjectsCompat.equals(getSupportedFeatures(), customerDeviceSettingsInput.getSupportedFeatures()) && ObjectsCompat.equals(getSupportedFeaturesEnumValues(), customerDeviceSettingsInput.getSupportedFeaturesEnumValues());
    }

    public int hashCode() {
        return (getCustomerId() + getDeviceId() + getDeviceType() + getId() + getSessionId() + getLocale() + getLanguageOfPreference() + getTimezone() + getAdContext() + getParentalControls() + getSupportedFeatures() + getSupportedFeaturesEnumValues()).hashCode();
    }

    public static CustomerIdStep builder() {
        return new Builder();
    }

    public static class Builder implements CustomerIdStep, DeviceIdStep, DeviceTypeStep, LocaleStep, TimezoneStep, AdContextStep, ParentalControlsStep, SupportedFeaturesStep, SupportedFeaturesEnumValuesStep, BuildStep {
        private AdContextInput adContext;
        private String customerId;
        private String deviceId;
        private String deviceType;
        private String id;
        private List<String> languageOfPreference;
        private String locale;
        private ParentalControlsInput parentalControls;
        private String sessionId;
        private List<SupportedFeatureEnum> supportedFeatures;
        private List<String> supportedFeaturesEnumValues;
        private String timezone;

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.BuildStep
        public CustomerDeviceSettingsInput build() {
            return new CustomerDeviceSettingsInput(this.customerId, this.deviceId, this.deviceType, this.id, this.sessionId, this.locale, this.languageOfPreference, this.timezone, this.adContext, this.parentalControls, this.supportedFeatures, this.supportedFeaturesEnumValues);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.CustomerIdStep
        public DeviceIdStep customerId(String str) {
            Objects.requireNonNull(str);
            this.customerId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.DeviceIdStep
        public DeviceTypeStep deviceId(String str) {
            Objects.requireNonNull(str);
            this.deviceId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.DeviceTypeStep
        public LocaleStep deviceType(String str) {
            Objects.requireNonNull(str);
            this.deviceType = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.LocaleStep
        public TimezoneStep locale(String str) {
            Objects.requireNonNull(str);
            this.locale = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.TimezoneStep
        public AdContextStep timezone(String str) {
            Objects.requireNonNull(str);
            this.timezone = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.AdContextStep
        public ParentalControlsStep adContext(AdContextInput adContextInput) {
            Objects.requireNonNull(adContextInput);
            this.adContext = adContextInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.ParentalControlsStep
        public SupportedFeaturesStep parentalControls(ParentalControlsInput parentalControlsInput) {
            Objects.requireNonNull(parentalControlsInput);
            this.parentalControls = parentalControlsInput;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.SupportedFeaturesStep
        public SupportedFeaturesEnumValuesStep supportedFeatures(List<SupportedFeatureEnum> list) {
            Objects.requireNonNull(list);
            this.supportedFeatures = list;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.SupportedFeaturesEnumValuesStep
        public BuildStep supportedFeaturesEnumValues(List<String> list) {
            Objects.requireNonNull(list);
            this.supportedFeaturesEnumValues = list;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.BuildStep
        public BuildStep id(String str) {
            this.id = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.BuildStep
        public BuildStep sessionId(String str) {
            this.sessionId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.BuildStep
        public BuildStep languageOfPreference(List<String> list) {
            this.languageOfPreference = list;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.BuildStep
        public /* bridge */ /* synthetic */ BuildStep languageOfPreference(List list) {
            return languageOfPreference((List<String>) list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.SupportedFeaturesStep
        public /* bridge */ /* synthetic */ SupportedFeaturesEnumValuesStep supportedFeatures(List list) {
            return supportedFeatures((List<SupportedFeatureEnum>) list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.SupportedFeaturesEnumValuesStep
        public /* bridge */ /* synthetic */ BuildStep supportedFeaturesEnumValues(List list) {
            return supportedFeaturesEnumValues((List<String>) list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.CustomerIdStep
        public CopyOfBuilder customerId(String str) {
            return (CopyOfBuilder) super.customerId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.DeviceIdStep
        public CopyOfBuilder deviceId(String str) {
            return (CopyOfBuilder) super.deviceId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.DeviceTypeStep
        public CopyOfBuilder deviceType(String str) {
            return (CopyOfBuilder) super.deviceType(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.LocaleStep
        public CopyOfBuilder locale(String str) {
            return (CopyOfBuilder) super.locale(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.TimezoneStep
        public CopyOfBuilder timezone(String str) {
            return (CopyOfBuilder) super.timezone(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.AdContextStep
        public CopyOfBuilder adContext(AdContextInput adContextInput) {
            return (CopyOfBuilder) super.adContext(adContextInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.ParentalControlsStep
        public CopyOfBuilder parentalControls(ParentalControlsInput parentalControlsInput) {
            return (CopyOfBuilder) super.parentalControls(parentalControlsInput);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.SupportedFeaturesStep
        public CopyOfBuilder supportedFeatures(List<SupportedFeatureEnum> list) {
            return (CopyOfBuilder) super.supportedFeatures(list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.SupportedFeaturesEnumValuesStep
        public CopyOfBuilder supportedFeaturesEnumValues(List<String> list) {
            return (CopyOfBuilder) super.supportedFeaturesEnumValues(list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.BuildStep
        public CopyOfBuilder id(String str) {
            return (CopyOfBuilder) super.id(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.BuildStep
        public CopyOfBuilder sessionId(String str) {
            return (CopyOfBuilder) super.sessionId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.Builder, com.amazon.mp3.amplifyqueue.model.CustomerDeviceSettingsInput.BuildStep
        public CopyOfBuilder languageOfPreference(List<String> list) {
            return (CopyOfBuilder) super.languageOfPreference(list);
        }
    }
}
