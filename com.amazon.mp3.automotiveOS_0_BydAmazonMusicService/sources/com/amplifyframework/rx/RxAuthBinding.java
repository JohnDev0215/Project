package com.amplifyframework.rx;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amplifyframework.auth.AuthCategoryBehavior;
import com.amplifyframework.auth.AuthCodeDeliveryDetails;
import com.amplifyframework.auth.AuthDevice;
import com.amplifyframework.auth.AuthException;
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
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.rx.RxAdapters;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
final class RxAuthBinding implements RxAuthCategoryBehavior {
    private final AuthCategoryBehavior delegate;

    RxAuthBinding() {
        this(Amplify.Auth);
    }

    @VisibleForTesting
    RxAuthBinding(@NonNull AuthCategoryBehavior authCategoryBehavior) {
        Objects.requireNonNull(authCategoryBehavior);
        this.delegate = authCategoryBehavior;
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSignUpResult> signUp(@NonNull final String str, @NonNull final String str2, @NonNull final AuthSignUpOptions authSignUpOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda7
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$signUp$0(str, str2, authSignUpOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$signUp$0(String str, String str2, AuthSignUpOptions authSignUpOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.signUp(str, str2, authSignUpOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSignUpResult> confirmSignUp(@NonNull final String str, @NonNull final String str2, final AuthConfirmSignUpOptions authConfirmSignUpOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda5
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$confirmSignUp$1(str, str2, authConfirmSignUpOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$confirmSignUp$1(String str, String str2, AuthConfirmSignUpOptions authConfirmSignUpOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.confirmSignUp(str, str2, authConfirmSignUpOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSignUpResult> confirmSignUp(@NonNull final String str, @NonNull final String str2) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda24
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$confirmSignUp$2(str, str2, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$confirmSignUp$2(String str, String str2, Consumer consumer, Consumer consumer2) {
        this.delegate.confirmSignUp(str, str2, consumer, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$resendSignUpCode$3(String str, AuthResendSignUpCodeOptions authResendSignUpCodeOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.resendSignUpCode(str, authResendSignUpCodeOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSignUpResult> resendSignUpCode(@NonNull final String str, @NonNull final AuthResendSignUpCodeOptions authResendSignUpCodeOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda3
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$resendSignUpCode$3(str, authResendSignUpCodeOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$resendSignUpCode$4(String str, Consumer consumer, Consumer consumer2) {
        this.delegate.resendSignUpCode(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSignUpResult> resendSignUpCode(@NonNull final String str) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda2
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$resendSignUpCode$4(str, consumer, consumer2);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSignInResult> signIn(@Nullable final String str, @Nullable final String str2, @NonNull final AuthSignInOptions authSignInOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda31
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$signIn$5(str, str2, authSignInOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$signIn$5(String str, String str2, AuthSignInOptions authSignInOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.signIn(str, str2, authSignInOptions, consumer, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$signIn$6(String str, String str2, Consumer consumer, Consumer consumer2) {
        this.delegate.signIn(str, str2, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSignInResult> signIn(@Nullable final String str, @Nullable final String str2) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda15
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$signIn$6(str, str2, consumer, consumer2);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSignInResult> confirmSignIn(@Nullable final String str, @NonNull final AuthConfirmSignInOptions authConfirmSignInOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda22
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$confirmSignIn$7(str, authConfirmSignInOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$confirmSignIn$7(String str, AuthConfirmSignInOptions authConfirmSignInOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.confirmSignIn(str, authConfirmSignInOptions, consumer, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$confirmSignIn$8(String str, Consumer consumer, Consumer consumer2) {
        this.delegate.confirmSignIn(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSignInResult> confirmSignIn(@NonNull final String str) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda26
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$confirmSignIn$8(str, consumer, consumer2);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSignInResult> signInWithSocialWebUI(@NonNull final AuthProvider authProvider, @NonNull final Activity activity) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda8
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$signInWithSocialWebUI$9(authProvider, activity, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithSocialWebUI$9(AuthProvider authProvider, Activity activity, Consumer consumer, Consumer consumer2) {
        this.delegate.signInWithSocialWebUI(authProvider, activity, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSignInResult> signInWithSocialWebUI(@NonNull final AuthProvider authProvider, @NonNull final Activity activity, @NonNull final AuthWebUISignInOptions authWebUISignInOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda14
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$signInWithSocialWebUI$10(authProvider, activity, authWebUISignInOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithSocialWebUI$10(AuthProvider authProvider, Activity activity, AuthWebUISignInOptions authWebUISignInOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.signInWithSocialWebUI(authProvider, activity, authWebUISignInOptions, consumer, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithWebUI$11(Activity activity, Consumer consumer, Consumer consumer2) {
        this.delegate.signInWithWebUI(activity, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSignInResult> signInWithWebUI(@NonNull final Activity activity) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda17
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$signInWithWebUI$11(activity, consumer, consumer2);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSignInResult> signInWithWebUI(@NonNull final Activity activity, @NonNull final AuthWebUISignInOptions authWebUISignInOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda25
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$signInWithWebUI$12(activity, authWebUISignInOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$signInWithWebUI$12(Activity activity, AuthWebUISignInOptions authWebUISignInOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.signInWithWebUI(activity, authWebUISignInOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public void handleWebUISignInResponse(Intent intent) {
        this.delegate.handleWebUISignInResponse(intent);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthSession> fetchAuthSession() {
        final AuthCategoryBehavior authCategoryBehavior = this.delegate;
        authCategoryBehavior.getClass();
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda30
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                authCategoryBehavior.fetchAuthSession(consumer, consumer2);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Completable rememberDevice() {
        final AuthCategoryBehavior authCategoryBehavior = this.delegate;
        authCategoryBehavior.getClass();
        return toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda13
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                authCategoryBehavior.rememberDevice(action, consumer);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Completable forgetDevice() {
        final AuthCategoryBehavior authCategoryBehavior = this.delegate;
        authCategoryBehavior.getClass();
        return toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda23
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                authCategoryBehavior.forgetDevice(action, consumer);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Completable forgetDevice(@NonNull final AuthDevice authDevice) {
        return toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda32
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                this.f$0.lambda$forgetDevice$13(authDevice, action, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$forgetDevice$13(AuthDevice authDevice, Action action, Consumer consumer) {
        this.delegate.forgetDevice(authDevice, action, consumer);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<List<AuthDevice>> fetchDevices() {
        final AuthCategoryBehavior authCategoryBehavior = this.delegate;
        authCategoryBehavior.getClass();
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda6
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                authCategoryBehavior.fetchDevices(consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$resetPassword$14(String str, AuthResetPasswordOptions authResetPasswordOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.resetPassword(str, authResetPasswordOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthResetPasswordResult> resetPassword(@NonNull final String str, @NonNull final AuthResetPasswordOptions authResetPasswordOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda33
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$resetPassword$14(str, authResetPasswordOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$resetPassword$15(String str, Consumer consumer, Consumer consumer2) {
        this.delegate.resetPassword(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthResetPasswordResult> resetPassword(@NonNull final String str) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda16
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$resetPassword$15(str, consumer, consumer2);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Completable confirmResetPassword(@NonNull final String str, @NonNull final String str2, @NonNull final AuthConfirmResetPasswordOptions authConfirmResetPasswordOptions) {
        return toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda11
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                this.f$0.lambda$confirmResetPassword$16(str, str2, authConfirmResetPasswordOptions, action, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$confirmResetPassword$16(String str, String str2, AuthConfirmResetPasswordOptions authConfirmResetPasswordOptions, Action action, Consumer consumer) {
        this.delegate.confirmResetPassword(str, str2, authConfirmResetPasswordOptions, action, consumer);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Completable confirmResetPassword(@NonNull final String str, @NonNull final String str2) {
        return toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda9
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                this.f$0.lambda$confirmResetPassword$17(str, str2, action, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$confirmResetPassword$17(String str, String str2, Action action, Consumer consumer) {
        this.delegate.confirmResetPassword(str, str2, action, consumer);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Completable updatePassword(@NonNull final String str, @NonNull final String str2) {
        return toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda27
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                this.f$0.lambda$updatePassword$18(str, str2, action, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePassword$18(String str, String str2, Action action, Consumer consumer) {
        this.delegate.updatePassword(str, str2, action, consumer);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<List<AuthUserAttribute>> fetchUserAttributes() {
        final AuthCategoryBehavior authCategoryBehavior = this.delegate;
        authCategoryBehavior.getClass();
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda10
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                authCategoryBehavior.fetchUserAttributes(consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateUserAttribute$19(AuthUserAttribute authUserAttribute, AuthUpdateUserAttributeOptions authUpdateUserAttributeOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.updateUserAttribute(authUserAttribute, authUpdateUserAttributeOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthUpdateAttributeResult> updateUserAttribute(@NonNull final AuthUserAttribute authUserAttribute, @NonNull final AuthUpdateUserAttributeOptions authUpdateUserAttributeOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda29
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$updateUserAttribute$19(authUserAttribute, authUpdateUserAttributeOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateUserAttribute$20(AuthUserAttribute authUserAttribute, Consumer consumer, Consumer consumer2) {
        this.delegate.updateUserAttribute(authUserAttribute, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthUpdateAttributeResult> updateUserAttribute(@NonNull final AuthUserAttribute authUserAttribute) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda21
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$updateUserAttribute$20(authUserAttribute, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateUserAttributes$21(List list, AuthUpdateUserAttributesOptions authUpdateUserAttributesOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.updateUserAttributes(list, authUpdateUserAttributesOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<Map<AuthUserAttributeKey, AuthUpdateAttributeResult>> updateUserAttributes(@NonNull final List<AuthUserAttribute> list, @NonNull final AuthUpdateUserAttributesOptions authUpdateUserAttributesOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$updateUserAttributes$21(list, authUpdateUserAttributesOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateUserAttributes$22(List list, Consumer consumer, Consumer consumer2) {
        this.delegate.updateUserAttributes(list, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<Map<AuthUserAttributeKey, AuthUpdateAttributeResult>> updateUserAttributes(@NonNull final List<AuthUserAttribute> list) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda18
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$updateUserAttributes$22(list, consumer, consumer2);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthCodeDeliveryDetails> resendUserAttributeConfirmationCode(@NonNull final AuthUserAttributeKey authUserAttributeKey, @NonNull final AuthResendUserAttributeConfirmationCodeOptions authResendUserAttributeConfirmationCodeOptions) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda20
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$resendUserAttributeConfirmationCode$23(authUserAttributeKey, authResendUserAttributeConfirmationCodeOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$resendUserAttributeConfirmationCode$23(AuthUserAttributeKey authUserAttributeKey, AuthResendUserAttributeConfirmationCodeOptions authResendUserAttributeConfirmationCodeOptions, Consumer consumer, Consumer consumer2) {
        this.delegate.resendUserAttributeConfirmationCode(authUserAttributeKey, authResendUserAttributeConfirmationCodeOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Single<AuthCodeDeliveryDetails> resendUserAttributeConfirmationCode(@NonNull final AuthUserAttributeKey authUserAttributeKey) {
        return toSingle(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda4
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$resendUserAttributeConfirmationCode$24(authUserAttributeKey, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$resendUserAttributeConfirmationCode$24(AuthUserAttributeKey authUserAttributeKey, Consumer consumer, Consumer consumer2) {
        this.delegate.resendUserAttributeConfirmationCode(authUserAttributeKey, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Completable confirmUserAttribute(@NonNull final AuthUserAttributeKey authUserAttributeKey, @NonNull final String str) {
        return toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda1
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                this.f$0.lambda$confirmUserAttribute$25(authUserAttributeKey, str, action, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$confirmUserAttribute$25(AuthUserAttributeKey authUserAttributeKey, String str, Action action, Consumer consumer) {
        this.delegate.confirmUserAttribute(authUserAttributeKey, str, action, consumer);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public AuthUser getCurrentUser() {
        return this.delegate.getCurrentUser();
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Completable signOut() {
        final AuthCategoryBehavior authCategoryBehavior = this.delegate;
        authCategoryBehavior.getClass();
        return toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda28
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                authCategoryBehavior.signOut(action, consumer);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Completable signOut(@NonNull final AuthSignOutOptions authSignOutOptions) {
        return toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda19
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                this.f$0.lambda$signOut$26(authSignOutOptions, action, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$signOut$26(AuthSignOutOptions authSignOutOptions, Action action, Consumer consumer) {
        this.delegate.signOut(authSignOutOptions, action, consumer);
    }

    @Override // com.amplifyframework.rx.RxAuthCategoryBehavior
    public Completable deleteUser() {
        final AuthCategoryBehavior authCategoryBehavior = this.delegate;
        authCategoryBehavior.getClass();
        return toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxAuthBinding$$ExternalSyntheticLambda12
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                authCategoryBehavior.deleteUser(action, consumer);
            }
        });
    }

    private <T> Single<T> toSingle(RxAdapters.VoidBehaviors.ResultEmitter<T, AuthException> resultEmitter) {
        return RxAdapters.VoidBehaviors.toSingle(resultEmitter);
    }

    private Completable toCompletable(RxAdapters.VoidBehaviors.ActionEmitter<AuthException> actionEmitter) {
        return RxAdapters.VoidBehaviors.toCompletable(actionEmitter);
    }
}
