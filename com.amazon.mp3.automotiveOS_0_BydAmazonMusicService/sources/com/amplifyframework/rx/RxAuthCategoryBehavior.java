package com.amplifyframework.rx;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.auth.AuthCodeDeliveryDetails;
import com.amplifyframework.auth.AuthDevice;
import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.auth.AuthSession;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthConfirmResetPasswordOptions;
import com.amplifyframework.auth.options.AuthConfirmSignInOptions;
import com.amplifyframework.auth.options.AuthConfirmSignUpOptions;
import com.amplifyframework.auth.options.AuthResendSignUpCodeOptions;
import com.amplifyframework.auth.options.AuthResendUserAttributeConfirmationCodeOptions;
import com.amplifyframework.auth.options.AuthResetPasswordOptions;
import com.amplifyframework.auth.options.AuthSignInOptions;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.auth.options.AuthUpdateUserAttributeOptions;
import com.amplifyframework.auth.options.AuthUpdateUserAttributesOptions;
import com.amplifyframework.auth.options.AuthWebUISignInOptions;
import com.amplifyframework.auth.result.AuthResetPasswordResult;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.auth.result.AuthUpdateAttributeResult;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public interface RxAuthCategoryBehavior {
    Completable confirmResetPassword(@NonNull String str, @NonNull String str2);

    Completable confirmResetPassword(@NonNull String str, @NonNull String str2, @NonNull AuthConfirmResetPasswordOptions authConfirmResetPasswordOptions);

    Single<AuthSignInResult> confirmSignIn(@NonNull String str);

    Single<AuthSignInResult> confirmSignIn(@Nullable String str, @NonNull AuthConfirmSignInOptions authConfirmSignInOptions);

    Single<AuthSignUpResult> confirmSignUp(@NonNull String str, @NonNull String str2);

    Single<AuthSignUpResult> confirmSignUp(@NonNull String str, @NonNull String str2, @NonNull AuthConfirmSignUpOptions authConfirmSignUpOptions);

    Completable confirmUserAttribute(@NonNull AuthUserAttributeKey authUserAttributeKey, @NonNull String str);

    Completable deleteUser();

    Single<AuthSession> fetchAuthSession();

    Single<List<AuthDevice>> fetchDevices();

    Single<List<AuthUserAttribute>> fetchUserAttributes();

    Completable forgetDevice();

    Completable forgetDevice(@NonNull AuthDevice authDevice);

    AuthUser getCurrentUser();

    void handleWebUISignInResponse(Intent intent);

    Completable rememberDevice();

    Single<AuthSignUpResult> resendSignUpCode(@NonNull String str);

    Single<AuthSignUpResult> resendSignUpCode(@NonNull String str, @NonNull AuthResendSignUpCodeOptions authResendSignUpCodeOptions);

    Single<AuthCodeDeliveryDetails> resendUserAttributeConfirmationCode(@NonNull AuthUserAttributeKey authUserAttributeKey);

    Single<AuthCodeDeliveryDetails> resendUserAttributeConfirmationCode(@NonNull AuthUserAttributeKey authUserAttributeKey, @NonNull AuthResendUserAttributeConfirmationCodeOptions authResendUserAttributeConfirmationCodeOptions);

    Single<AuthResetPasswordResult> resetPassword(@NonNull String str);

    Single<AuthResetPasswordResult> resetPassword(@NonNull String str, @NonNull AuthResetPasswordOptions authResetPasswordOptions);

    Single<AuthSignInResult> signIn(@Nullable String str, @Nullable String str2);

    Single<AuthSignInResult> signIn(@Nullable String str, @Nullable String str2, @NonNull AuthSignInOptions authSignInOptions);

    Single<AuthSignInResult> signInWithSocialWebUI(@NonNull AuthProvider authProvider, @NonNull Activity activity);

    Single<AuthSignInResult> signInWithSocialWebUI(@NonNull AuthProvider authProvider, @NonNull Activity activity, @NonNull AuthWebUISignInOptions authWebUISignInOptions);

    Single<AuthSignInResult> signInWithWebUI(@NonNull Activity activity);

    Single<AuthSignInResult> signInWithWebUI(@NonNull Activity activity, @NonNull AuthWebUISignInOptions authWebUISignInOptions);

    Completable signOut();

    Completable signOut(@NonNull AuthSignOutOptions authSignOutOptions);

    Single<AuthSignUpResult> signUp(@NonNull String str, @NonNull String str2, @NonNull AuthSignUpOptions authSignUpOptions);

    Completable updatePassword(@NonNull String str, @NonNull String str2);

    Single<AuthUpdateAttributeResult> updateUserAttribute(@NonNull AuthUserAttribute authUserAttribute);

    Single<AuthUpdateAttributeResult> updateUserAttribute(@NonNull AuthUserAttribute authUserAttribute, @NonNull AuthUpdateUserAttributeOptions authUpdateUserAttributeOptions);

    Single<Map<AuthUserAttributeKey, AuthUpdateAttributeResult>> updateUserAttributes(@NonNull List<AuthUserAttribute> list);

    Single<Map<AuthUserAttributeKey, AuthUpdateAttributeResult>> updateUserAttributes(@NonNull List<AuthUserAttribute> list, @NonNull AuthUpdateUserAttributesOptions authUpdateUserAttributesOptions);
}
