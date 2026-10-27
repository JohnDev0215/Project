package com.amazon.music.signin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class MAPAccountDataResponse {
    private final String accountId;
    private final String adpToken;
    private final String centralDeviceType;
    private final String deviceId;
    private final String deviceType;
    private final String privateKeyString;
    private final String username;

    protected MAPAccountDataResponse(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull String str6, @Nullable String str7) {
        Validate.notNull(str, "AccountId cannot be null", new Object[0]);
        Validate.notNull(str4, "Username cannot be null", new Object[0]);
        Validate.notNull(str2, "DeviceToken cannot be null", new Object[0]);
        Validate.notNull(str3, "PrivateKey cannot be null", new Object[0]);
        Validate.notNull(str5, "DeviceType cannot be null", new Object[0]);
        Validate.notNull(str6, "DeviceId cannot be null", new Object[0]);
        this.accountId = str;
        this.adpToken = str2;
        this.privateKeyString = str3;
        this.username = str4;
        this.deviceType = str5;
        this.deviceId = str6;
        this.centralDeviceType = str7;
    }

    protected String getAccountDataAccountId() {
        return this.accountId;
    }

    protected String getAccountDataUsername() {
        return this.username;
    }

    protected String getAccountDataAdpToken() {
        return this.adpToken;
    }

    protected String getAccountDataPrivateKeyString() {
        return this.privateKeyString;
    }

    protected String getAccountDataDeviceType() {
        return this.deviceType;
    }

    protected String getAccountDataDeviceId() {
        return this.deviceId;
    }

    protected String getCentralDeviceType() {
        return this.centralDeviceType;
    }
}
