package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class AdContext {
    private final String advertiserId;
    private final DeviceFamilyEnum deviceFamily;
    private final String deviceFamilyEnumValue;
    private final String userAgent;

    public interface BuildStep {
        BuildStep advertiserId(String str);

        AdContext build();

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

    private AdContext(String str, DeviceFamilyEnum deviceFamilyEnum, String str2, String str3) {
        this.userAgent = str;
        this.deviceFamily = deviceFamilyEnum;
        this.deviceFamilyEnumValue = str2;
        this.advertiserId = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AdContext.class != obj.getClass()) {
            return false;
        }
        AdContext adContext = (AdContext) obj;
        return ObjectsCompat.equals(getUserAgent(), adContext.getUserAgent()) && ObjectsCompat.equals(getDeviceFamily(), adContext.getDeviceFamily()) && ObjectsCompat.equals(getDeviceFamilyEnumValue(), adContext.getDeviceFamilyEnumValue()) && ObjectsCompat.equals(getAdvertiserId(), adContext.getAdvertiserId());
    }

    public int hashCode() {
        return (getUserAgent() + getDeviceFamily() + getDeviceFamilyEnumValue() + getAdvertiserId()).hashCode();
    }

    public static UserAgentStep builder() {
        return new Builder();
    }

    public static class Builder implements UserAgentStep, DeviceFamilyStep, BuildStep {
        private String advertiserId;
        private DeviceFamilyEnum deviceFamily;
        private String deviceFamilyEnumValue;
        private String userAgent;

        @Override // com.amazon.mp3.amplifyqueue.model.AdContext.BuildStep
        public AdContext build() {
            return new AdContext(this.userAgent, this.deviceFamily, this.deviceFamilyEnumValue, this.advertiserId);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContext.UserAgentStep
        public DeviceFamilyStep userAgent(String str) {
            Objects.requireNonNull(str);
            this.userAgent = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContext.DeviceFamilyStep
        public BuildStep deviceFamily(DeviceFamilyEnum deviceFamilyEnum) {
            Objects.requireNonNull(deviceFamilyEnum);
            this.deviceFamily = deviceFamilyEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContext.BuildStep
        public BuildStep deviceFamilyEnumValue(String str) {
            this.deviceFamilyEnumValue = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContext.BuildStep
        public BuildStep advertiserId(String str) {
            this.advertiserId = str;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.AdContext.Builder, com.amazon.mp3.amplifyqueue.model.AdContext.UserAgentStep
        public CopyOfBuilder userAgent(String str) {
            return (CopyOfBuilder) super.userAgent(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContext.Builder, com.amazon.mp3.amplifyqueue.model.AdContext.DeviceFamilyStep
        public CopyOfBuilder deviceFamily(DeviceFamilyEnum deviceFamilyEnum) {
            return (CopyOfBuilder) super.deviceFamily(deviceFamilyEnum);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContext.Builder, com.amazon.mp3.amplifyqueue.model.AdContext.BuildStep
        public CopyOfBuilder deviceFamilyEnumValue(String str) {
            return (CopyOfBuilder) super.deviceFamilyEnumValue(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.AdContext.Builder, com.amazon.mp3.amplifyqueue.model.AdContext.BuildStep
        public CopyOfBuilder advertiserId(String str) {
            return (CopyOfBuilder) super.advertiserId(str);
        }
    }
}
