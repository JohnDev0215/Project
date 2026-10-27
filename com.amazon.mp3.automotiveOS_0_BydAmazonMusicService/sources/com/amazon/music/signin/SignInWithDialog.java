package com.amazon.music.signin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.api.SigninOption;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class SignInWithDialog {
    protected void signInWithDialog(@NonNull Activity activity, @NonNull Callback callback) {
        Validate.notNull(activity, "activity cannot be null", new Object[0]);
        Validate.notNull(callback, "callback cannot be null", new Object[0]);
        Bundle bundle = new Bundle();
        new SignInRegistrationParameters().setUpRegistrationParameters(bundle, activity.getApplicationContext().getResources().getConfiguration().locale);
        new MAPAccountManager(activity.getApplicationContext()).registerAccountWithUI(activity, SigninOption.WebviewSignin, bundle, callback);
    }

    protected void signInWithRecoverAccount(@NonNull Activity activity, @NonNull Callback callback) {
        MAPAccountManager mAPAccountManager = new MAPAccountManager(activity.getApplicationContext());
        String account = mAPAccountManager.getAccount();
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.dcp.sso.property.account.acctId", account);
        Bundle bundle2 = new Bundle();
        new SignInRegistrationParameters().setUpRegistrationParameters(bundle2, activity.getApplicationContext().getResources().getConfiguration().locale);
        mAPAccountManager.recoverAccount(activity, bundle, bundle2, callback);
    }

    protected void setAccountAsDefaultForThisApp(@NonNull String str, @NonNull Context context) {
        Validate.notNull(str, "accountId cannot be null", new Object[0]);
        Validate.notNull(context, "context cannot be null", new Object[0]);
        new MultipleAccountManager(context).setAccountMappings(str, MultipleAccountManager.PackageMappingType.createCurrentPackageMapping(context));
    }
}
