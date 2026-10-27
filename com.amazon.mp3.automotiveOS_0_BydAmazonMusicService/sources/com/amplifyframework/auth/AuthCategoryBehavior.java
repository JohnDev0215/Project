package com.amplifyframework.auth;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Consumer;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public interface AuthCategoryBehavior {
    void confirmResetPassword(@NonNull String str, @NonNull String str2, @NonNull AuthConfirmResetPasswordOptions authConfirmResetPasswordOptions, @NonNull Action action, @NonNull Consumer<AuthException> consumer);

    void confirmResetPassword(@NonNull String str, @NonNull String str2, @NonNull Action action, @NonNull Consumer<AuthException> consumer);

    void confirmSignIn(@NonNull String str, @NonNull AuthConfirmSignInOptions authConfirmSignInOptions, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void confirmSignIn(@NonNull String str, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void confirmSignUp(@NonNull String str, @NonNull String str2, @NonNull AuthConfirmSignUpOptions authConfirmSignUpOptions, @NonNull Consumer<AuthSignUpResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void confirmSignUp(@NonNull String str, @NonNull String str2, @NonNull Consumer<AuthSignUpResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void confirmUserAttribute(@NonNull AuthUserAttributeKey authUserAttributeKey, @NonNull String str, @NonNull Action action, @NonNull Consumer<AuthException> consumer);

    void deleteUser(@NonNull Action action, @NonNull Consumer<AuthException> consumer);

    void fetchAuthSession(@NonNull Consumer<AuthSession> consumer, @NonNull Consumer<AuthException> consumer2);

    void fetchDevices(@NonNull Consumer<List<AuthDevice>> consumer, @NonNull Consumer<AuthException> consumer2);

    void fetchUserAttributes(@NonNull Consumer<List<AuthUserAttribute>> consumer, @NonNull Consumer<AuthException> consumer2);

    void forgetDevice(@NonNull AuthDevice authDevice, @NonNull Action action, @NonNull Consumer<AuthException> consumer);

    void forgetDevice(@NonNull Action action, @NonNull Consumer<AuthException> consumer);

    AuthUser getCurrentUser();

    void handleWebUISignInResponse(Intent intent);

    void rememberDevice(@NonNull Action action, @NonNull Consumer<AuthException> consumer);

    void resendSignUpCode(@NonNull String str, @NonNull AuthResendSignUpCodeOptions authResendSignUpCodeOptions, @NonNull Consumer<AuthSignUpResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void resendSignUpCode(@NonNull String str, @NonNull Consumer<AuthSignUpResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void resendUserAttributeConfirmationCode(@NonNull AuthUserAttributeKey authUserAttributeKey, @NonNull AuthResendUserAttributeConfirmationCodeOptions authResendUserAttributeConfirmationCodeOptions, @NonNull Consumer<AuthCodeDeliveryDetails> consumer, @NonNull Consumer<AuthException> consumer2);

    void resendUserAttributeConfirmationCode(@NonNull AuthUserAttributeKey authUserAttributeKey, @NonNull Consumer<AuthCodeDeliveryDetails> consumer, @NonNull Consumer<AuthException> consumer2);

    void resetPassword(@NonNull String str, @NonNull AuthResetPasswordOptions authResetPasswordOptions, @NonNull Consumer<AuthResetPasswordResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void resetPassword(@NonNull String str, @NonNull Consumer<AuthResetPasswordResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void signIn(@Nullable String str, @Nullable String str2, @NonNull AuthSignInOptions authSignInOptions, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void signIn(@Nullable String str, @Nullable String str2, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void signInWithSocialWebUI(@NonNull AuthProvider authProvider, @NonNull Activity activity, @NonNull AuthWebUISignInOptions authWebUISignInOptions, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void signInWithSocialWebUI(@NonNull AuthProvider authProvider, @NonNull Activity activity, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void signInWithWebUI(@NonNull Activity activity, @NonNull AuthWebUISignInOptions authWebUISignInOptions, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void signInWithWebUI(@NonNull Activity activity, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void signOut(@NonNull AuthSignOutOptions authSignOutOptions, @NonNull Action action, @NonNull Consumer<AuthException> consumer);

    void signOut(@NonNull Action action, @NonNull Consumer<AuthException> consumer);

    void signUp(@NonNull String str, @NonNull String str2, @NonNull AuthSignUpOptions authSignUpOptions, @NonNull Consumer<AuthSignUpResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void updatePassword(@NonNull String str, @NonNull String str2, @NonNull Action action, @NonNull Consumer<AuthException> consumer);

    void updateUserAttribute(@NonNull AuthUserAttribute authUserAttribute, @NonNull AuthUpdateUserAttributeOptions authUpdateUserAttributeOptions, @NonNull Consumer<AuthUpdateAttributeResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void updateUserAttribute(@NonNull AuthUserAttribute authUserAttribute, @NonNull Consumer<AuthUpdateAttributeResult> consumer, @NonNull Consumer<AuthException> consumer2);

    void updateUserAttributes(@NonNull List<AuthUserAttribute> list, @NonNull AuthUpdateUserAttributesOptions authUpdateUserAttributesOptions, @NonNull Consumer<Map<AuthUserAttributeKey, AuthUpdateAttributeResult>> consumer, @NonNull Consumer<AuthException> consumer2);

    void updateUserAttributes(@NonNull List<AuthUserAttribute> list, @NonNull Consumer<Map<AuthUserAttributeKey, AuthUpdateAttributeResult>> consumer, @NonNull Consumer<AuthException> consumer2);
}
