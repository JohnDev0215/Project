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
import com.amplifyframework.core.category.Category;
import com.amplifyframework.core.category.CategoryType;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthCategory extends Category<AuthPlugin<?>> implements AuthCategoryBehavior {
    @Override // com.amplifyframework.core.category.CategoryTypeable
    @NonNull
    public CategoryType getCategoryType() {
        return CategoryType.AUTH;
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void signUp(@NonNull String str, @NonNull String str2, @NonNull AuthSignUpOptions authSignUpOptions, @NonNull Consumer<AuthSignUpResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().signUp(str, str2, authSignUpOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void confirmSignUp(@NonNull String str, @NonNull String str2, @NonNull AuthConfirmSignUpOptions authConfirmSignUpOptions, @NonNull Consumer<AuthSignUpResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().confirmSignUp(str, str2, authConfirmSignUpOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void confirmSignUp(@NonNull String str, @NonNull String str2, @NonNull Consumer<AuthSignUpResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().confirmSignUp(str, str2, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void resendSignUpCode(@NonNull String str, @NonNull AuthResendSignUpCodeOptions authResendSignUpCodeOptions, @NonNull Consumer<AuthSignUpResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().resendSignUpCode(str, authResendSignUpCodeOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void resendSignUpCode(@NonNull String str, @NonNull Consumer<AuthSignUpResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().resendSignUpCode(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void signIn(@Nullable String str, @Nullable String str2, @NonNull AuthSignInOptions authSignInOptions, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().signIn(str, str2, authSignInOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void signIn(@Nullable String str, @Nullable String str2, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().signIn(str, str2, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void confirmSignIn(@NonNull String str, @NonNull AuthConfirmSignInOptions authConfirmSignInOptions, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().confirmSignIn(str, authConfirmSignInOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void confirmSignIn(@NonNull String str, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().confirmSignIn(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void signInWithSocialWebUI(@NonNull AuthProvider authProvider, @NonNull Activity activity, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().signInWithSocialWebUI(authProvider, activity, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void signInWithSocialWebUI(@NonNull AuthProvider authProvider, @NonNull Activity activity, @NonNull AuthWebUISignInOptions authWebUISignInOptions, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().signInWithSocialWebUI(authProvider, activity, authWebUISignInOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void signInWithWebUI(@NonNull Activity activity, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().signInWithWebUI(activity, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void signInWithWebUI(@NonNull Activity activity, @NonNull AuthWebUISignInOptions authWebUISignInOptions, @NonNull Consumer<AuthSignInResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().signInWithWebUI(activity, authWebUISignInOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void handleWebUISignInResponse(Intent intent) {
        getSelectedPlugin().handleWebUISignInResponse(intent);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void fetchAuthSession(@NonNull Consumer<AuthSession> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().fetchAuthSession(consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void rememberDevice(@NonNull Action action, @NonNull Consumer<AuthException> consumer) {
        getSelectedPlugin().rememberDevice(action, consumer);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void forgetDevice(@NonNull Action action, @NonNull Consumer<AuthException> consumer) {
        getSelectedPlugin().forgetDevice(action, consumer);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void forgetDevice(@NonNull AuthDevice authDevice, @NonNull Action action, @NonNull Consumer<AuthException> consumer) {
        getSelectedPlugin().forgetDevice(authDevice, action, consumer);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void fetchDevices(@NonNull Consumer<List<AuthDevice>> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().fetchDevices(consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void resetPassword(@NonNull String str, @NonNull AuthResetPasswordOptions authResetPasswordOptions, @NonNull Consumer<AuthResetPasswordResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().resetPassword(str, authResetPasswordOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void resetPassword(@NonNull String str, @NonNull Consumer<AuthResetPasswordResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().resetPassword(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void confirmResetPassword(@NonNull String str, @NonNull String str2, @NonNull AuthConfirmResetPasswordOptions authConfirmResetPasswordOptions, @NonNull Action action, @NonNull Consumer<AuthException> consumer) {
        getSelectedPlugin().confirmResetPassword(str, str2, authConfirmResetPasswordOptions, action, consumer);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void confirmResetPassword(@NonNull String str, @NonNull String str2, @NonNull Action action, @NonNull Consumer<AuthException> consumer) {
        getSelectedPlugin().confirmResetPassword(str, str2, action, consumer);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void updatePassword(@NonNull String str, @NonNull String str2, @NonNull Action action, @NonNull Consumer<AuthException> consumer) {
        getSelectedPlugin().updatePassword(str, str2, action, consumer);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void fetchUserAttributes(@NonNull Consumer<List<AuthUserAttribute>> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().fetchUserAttributes(consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void updateUserAttribute(@NonNull AuthUserAttribute authUserAttribute, @NonNull AuthUpdateUserAttributeOptions authUpdateUserAttributeOptions, @NonNull Consumer<AuthUpdateAttributeResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().updateUserAttribute(authUserAttribute, authUpdateUserAttributeOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void updateUserAttribute(@NonNull AuthUserAttribute authUserAttribute, @NonNull Consumer<AuthUpdateAttributeResult> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().updateUserAttribute(authUserAttribute, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void updateUserAttributes(@NonNull List<AuthUserAttribute> list, @NonNull AuthUpdateUserAttributesOptions authUpdateUserAttributesOptions, @NonNull Consumer<Map<AuthUserAttributeKey, AuthUpdateAttributeResult>> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().updateUserAttributes(list, authUpdateUserAttributesOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void updateUserAttributes(@NonNull List<AuthUserAttribute> list, @NonNull Consumer<Map<AuthUserAttributeKey, AuthUpdateAttributeResult>> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().updateUserAttributes(list, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void resendUserAttributeConfirmationCode(@NonNull AuthUserAttributeKey authUserAttributeKey, @NonNull AuthResendUserAttributeConfirmationCodeOptions authResendUserAttributeConfirmationCodeOptions, @NonNull Consumer<AuthCodeDeliveryDetails> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().resendUserAttributeConfirmationCode(authUserAttributeKey, authResendUserAttributeConfirmationCodeOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void resendUserAttributeConfirmationCode(@NonNull AuthUserAttributeKey authUserAttributeKey, @NonNull Consumer<AuthCodeDeliveryDetails> consumer, @NonNull Consumer<AuthException> consumer2) {
        getSelectedPlugin().resendUserAttributeConfirmationCode(authUserAttributeKey, consumer, consumer2);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void confirmUserAttribute(@NonNull AuthUserAttributeKey authUserAttributeKey, @NonNull String str, @NonNull Action action, @NonNull Consumer<AuthException> consumer) {
        getSelectedPlugin().confirmUserAttribute(authUserAttributeKey, str, action, consumer);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public AuthUser getCurrentUser() {
        return getSelectedPlugin().getCurrentUser();
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void signOut(@NonNull Action action, @NonNull Consumer<AuthException> consumer) {
        getSelectedPlugin().signOut(action, consumer);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void signOut(@NonNull AuthSignOutOptions authSignOutOptions, @NonNull Action action, @NonNull Consumer<AuthException> consumer) {
        getSelectedPlugin().signOut(authSignOutOptions, action, consumer);
    }

    @Override // com.amplifyframework.auth.AuthCategoryBehavior
    public void deleteUser(@NonNull Action action, @NonNull Consumer<AuthException> consumer) {
        getSelectedPlugin().deleteUser(action, consumer);
    }
}
