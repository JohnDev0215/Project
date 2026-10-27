package com.amazon.music.signin;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class MAPAccountDataSaver {
    private SharedPreferences sharedPreferences;

    protected MAPAccountDataSaver(SharedPreferences sharedPreferences) {
        Validate.notNull(sharedPreferences, "SharedPreferences cannot be null", new Object[0]);
        this.sharedPreferences = sharedPreferences;
    }

    protected void saveAccountDataResponse(@NonNull MAPAccountDataResponse mAPAccountDataResponse) throws Exception {
        Validate.notNull(mAPAccountDataResponse, "AccountDataResponse cannot be null", new Object[0]);
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putString("account_id", mAPAccountDataResponse.getAccountDataAccountId());
        editorEdit.putString("account_device_token", mAPAccountDataResponse.getAccountDataAdpToken());
        editorEdit.putString("account_private_key", mAPAccountDataResponse.getAccountDataPrivateKeyString());
        editorEdit.putString("account_username", mAPAccountDataResponse.getAccountDataUsername());
        editorEdit.putString("account_device_type", mAPAccountDataResponse.getAccountDataDeviceType());
        editorEdit.putString("account_token_device_id", mAPAccountDataResponse.getAccountDataDeviceId());
        editorEdit.putString("central_device_type", mAPAccountDataResponse.getCentralDeviceType());
        editorEdit.putString("last_account_id", getLastAccountId());
        editorEdit.apply();
    }

    protected String getLastAccountId() {
        return this.sharedPreferences.getString("last_account_id", "");
    }
}
