package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class AdContextInput {
    private final String advertiserId;
    private final String customerAdvertiserId;
    private final DeviceFamilyEnum deviceFamily;
    private final String deviceFamilyEnumValue;
    private final Boolean limitAdTracking;
    private final String userAgent;

    public interface BuildStep {
        BuildStep advertiserId(String str);

        AdContextInput build();

        BuildStep deviceFamilyEnumValue(String str);
    }

    public interface DeviceFamilyStep {
        BuildStep deviceFamily(DeviceFamilyEnum deviceFamilyEnum);
    }

    public interface UserAgentStep {
        DeviceFamilyStep userAgent(String str);
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public DeviceFamilyEnum getDeviceFamily() {
        return this.deviceFamily;
    }

    public String getDeviceFamilyEnumValue() {
        return this.deviceFamilyEnumValue;
    }

    public String getAdvertiserId() {
        return this.advertiserId;
    }

    public String getCustomerAdvertiserId() {
        return this.customerAdvertiserId;
    }

    public Boolean getLimitAdTracking() {
        return this.limitAdTracking;
    }

    private AdContextInput(String str, DeviceFamilyEnum deviceFamilyEnum, String str2, String str3, String str4, Boolean bool) {
        this.userAgent = str;
        this.deviceFamily = deviceFamilyEnum;
        this.deviceFamilyEnumValue = str2;
        this.advertiserId = str3;
        this.customerAdvertiserId = str4;
        this.limitAdTracking = bool;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AdContextInput.class != obj.getClass()) {
            return false;
        }
        AdContextInput adContextInput = (AdContextInput) obj;
        return ObjectsCompat.equals(getUserAgent(), adContextInput.getUserAgent()) && ObjectsCompat.equals(getDeviceFamily(), adContextInput.getDeviceFamily()) && ObjectsCompat.equals(getDeviceFamilyEnumValue(), adContextInput.getDeviceFamilyEnumValue()) && ObjectsCompat.equals(getAdvertiserId(), adContextInput.getAdvertiserId()) && ObjectsCompat.equals(getCustomerAdvertiserId(), adContextInput.getCustomerAdvertiserId()) && ObjectsCompat.equals(getLimitAdTracking(), adContextInput.getLimitAdTracking());
    }

    public int hashCode() {
        return (getUserAgent() + getDeviceFamily() + getDeviceFamilyEnumValue() + getAdvertiserId() + getCustomerAdvertiserId() + getLimitAdTracking()).hashCode();
    }

    public static UserAgentStep builder() {
        return new Builder();
    }

    public static class Builder implements UserAgentStep, DeviceFamilyStep, BuildStep {
        private String advertiserId;
        private String customerAdvertiserId;
        private DeviceFamilyEnum deviceFamily;
        private String deviceFamilyEnumValue;
        private Boolean limitAdTracking;
        private String userAgent;

        @Override // com.amazon.mp3.amplifyqueue.model.AdContextInput.BuildStep
        public AdContextInput build() {
            return new AdContextInput(this.userAgent, this.deviceFamily, this.deviceFamilyEnumValue, this.advertiserId, this.customerAdvertiserId, this.limitAdTracking);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContextInput.UserAgentStep
        public DeviceFamilyStep userAgent(String str) {
            Objects.requireNonNull(str);
            this.userAgent = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContextInput.DeviceFamilyStep
        public BuildStep deviceFamily(DeviceFamilyEnum deviceFamilyEnum) {
            Objects.requireNonNull(deviceFamilyEnum);
            this.deviceFamily = deviceFamilyEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContextInput.BuildStep
        public BuildStep deviceFamilyEnumValue(String str) {
            this.deviceFamilyEnumValue = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContextInput.BuildStep
        public BuildStep advertiserId(String str) {
            this.advertiserId = str;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.AdContextInput.Builder, com.amazon.mp3.amplifyqueue.model.AdContextInput.UserAgentStep
        public CopyOfBuilder userAgent(String str) {
            return (CopyOfBuilder) super.userAgent(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContextInput.Builder, com.amazon.mp3.amplifyqueue.model.AdContextInput.DeviceFamilyStep
        public CopyOfBuilder deviceFamily(DeviceFamilyEnum deviceFamilyEnum) {
            return (CopyOfBuilder) super.deviceFamily(deviceFamilyEnum);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContextInput.Builder, com.amazon.mp3.amplifyqueue.model.AdContextInput.BuildStep
        public CopyOfBuilder deviceFamilyEnumValue(String str) {
            return (CopyOfBuilder) super.deviceFamilyEnumValue(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContextInput.Builder, com.amazon.mp3.amplifyqueue.model.AdContextInput.BuildStep
        public CopyOfBuilder advertiserId(String str) {
            return (CopyOfBuilder) super.advertiserId(str);
        }
    }
}
