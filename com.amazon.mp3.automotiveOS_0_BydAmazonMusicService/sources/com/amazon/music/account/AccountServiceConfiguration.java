package com.amazon.music.account;

import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class AccountServiceConfiguration {
    private final String deviceId;
    private final String deviceType;

    private AccountServiceConfiguration(Builder builder) {
        this.deviceId = builder.deviceId;
        this.deviceType = builder.deviceType;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public static final class Builder {
        private final String deviceId;
        private final String deviceType;

        public Builder(String str, String str2) {
            Validate.notEmpty(str, "deviceId cannot be null or empty", new Object[0]);
            Validate.notEmpty(str2, "deviceType cannot be null or empty", new Object[0]);
            this.deviceId = str;
            this.deviceType = str2;
        }

        public AccountServiceConfiguration build() {
            return new AccountServiceConfiguration(this);
        }
    }
}
