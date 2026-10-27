package com.amazon.music.signin;

import androidx.annotation.NonNull;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class MAPAccountIdResponse {
    private final String accountId;

    protected MAPAccountIdResponse(@NonNull String str) {
        Validate.notNull(str, "accountId cannot be null", new Object[0]);
        this.accountId = str;
    }

    protected String getAccountId() {
        return this.accountId;
    }
}
