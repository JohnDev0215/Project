package com.amazon.mp3.account.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.map.MAPUtil;
import com.amazon.mp3.account.signintasks.GetHomeMarketplace;
import com.amazon.mp3.account.signintasks.MusicUnsupportedMarketplaceException;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.dialog.NetworkFailureWithSettingsDialog;
import com.amazon.mp3.library.mylibrary.MyLibraryHomeFragment;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.EmailUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.authentication.mapr5.MapEmptyValueException;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.signin.MAPApiName;
import com.amazon.music.signin.MAPEmptyReturnValueException;
import com.amazon.music.signin.MAPRegistrationException;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public abstract class SignInFlowActivity extends Activity {
    private static final String TAG = "SignInFlowActivity";
    protected Action1<Throwable> onError = new Action1<Throwable>() { // from class: com.amazon.mp3.account.activity.SignInFlowActivity.1
        @Override // rx.functions.Action1
        public void call(final Throwable throwable) {
            if ((throwable instanceof MusicAccountNotCreatedException) || (throwable instanceof GetHomeMarketplace.AccountStatusException) || (throwable instanceof DataNotReadyException)) {
                Log.error(SignInFlowActivity.TAG, "Error is " + throwable);
                if (ChildUserUtil.INSTANCE.isChildUser(SignInFlowActivity.this.getApplicationContext())) {
                    return;
                }
                SignInFlowActivity.this.emitFlexEventForShowSsoWelcome(AccountCredentialStorage.get(SignInFlowActivity.this.getApplicationContext()).hasShownSsoWelcome() ? "ACCOUNT_WEBVIEW_SSO_FLOW" : "ACCOUNT_WEBVIEW_NON_SSO_FLOW");
                SignInFlowActivity.this.startAccountWebView();
                return;
            }
            if (throwable instanceof AuthenticationException) {
                Log.error(SignInFlowActivity.TAG, "Error is AuthenticationException");
                Throwable cause = throwable.getCause();
                String str = SignInFlowActivity.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Error cause is ");
                sb.append(cause == null ? "null " : cause.getClass().getSimpleName());
                Log.error(str, sb.toString());
                if (cause instanceof MapEmptyValueException) {
                    SignInFlowActivity.this.logMapEmptyValueExceptionCause(cause);
                    SignInFlowActivity.this.handleMapEmptyValueException();
                    return;
                }
                return;
            }
            if (throwable instanceof MusicUnsupportedMarketplaceException) {
                Log.info(SignInFlowActivity.TAG, "User is in cloud unsupported marketplace. No sign in flow required. Continuing to offline library.");
                AccountCredentialStorage.get(SignInFlowActivity.this).setShouldPerformSignInTasks(false);
                AccountCredentialStorage.get(SignInFlowActivity.this).setShowSsoWelcome(true);
                SignInFlowActivity.this.cancelSignInFlow();
                SignInFlowActivity.this.startActivity(MusicHomeActivity.getStartIntent(SignInFlowActivity.this).putExtra("com.amazon.mp3.fragment.extra", MyLibraryHomeFragment.class.getSimpleName()).putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus-local"));
                return;
            }
            SignInFlowActivity.this.showErrorDialogBasedOnException(throwable);
            SignInFlowActivity.this.handleException();
        }
    };

    protected abstract void handleException();

    protected abstract void handleMapEmptyValueException();

    @Override // android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void onErrorDialogClickOk() {
        setResult(0);
        finish();
    }

    protected void onErrorDialogClickSendEmailReport() {
        EmailUtil.reportError(getApplicationContext(), getString(R.string.dmusic_sso_error_dialog_title));
        setResult(0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAccountWebView() {
        startActivityForResult(AccountWebViewActivity.getIntent(this), AccountWebViewActivity.REQUEST_CODE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logMapEmptyValueExceptionCause(final Throwable cause) {
        MapEmptyValueException mapEmptyValueException = (MapEmptyValueException) cause;
        Bundle responseBundle = mapEmptyValueException.getResponseBundle();
        String keyName = mapEmptyValueException.getKeyName();
        if (responseBundle != null) {
            int i = responseBundle.getInt("com.amazon.dcp.sso.ErrorCode");
            Log.error(TAG, "Authentication Error: Received MapEmptyValueException for keyName: %s. MAP error code: %d. Error message: %s", keyName, Integer.valueOf(i), responseBundle.getString("com.amazon.dcp.sso.ErrorMessage"));
            return;
        }
        Log.error(TAG, "Authentication Error: Received MapEmptyValueException for keyName: %s.MAP also returned a missing error bundle so we don't have an error code or message.", keyName);
    }

    public void showErrorDialogBasedOnException(final Throwable throwable) {
        int i;
        Log.error(TAG, "Received an error. Displaying error dialog.", throwable);
        if (throwable instanceof MAPRegistrationException) {
            handleMapRegistrationError((MAPRegistrationException) throwable);
            return;
        }
        if (throwable instanceof MAPCallbackErrorException) {
            i = R.string.dmusic_sso_auth_error_121;
            recordGeneralMapError(MAPCallbackErrorException.class.getSimpleName(), extractInfoFromMAPCallbackErrorException((MAPCallbackErrorException) throwable));
        } else if (throwable instanceof MAPEmptyReturnValueException) {
            i = R.string.dmusic_sso_auth_error_122;
            recordGeneralMapError(MAPEmptyReturnValueException.class.getSimpleName(), extractInfoFromMAPEmptyReturnValueException((MAPEmptyReturnValueException) throwable));
        } else {
            i = R.string.dmusic_sso_auth_error_119;
        }
        showErrorDialogFragmentWithMessage(i);
        setResult(0);
    }

    private void handleMapRegistrationError(final MAPRegistrationException exception) {
        recordMapRegistrationError(exception);
        showDialogForMapRegistrationError(exception.getErrorCode(), exception.getMapAccountManagerDetailedErrorType());
    }

    private void recordMapRegistrationError(final MAPRegistrationException exception) {
        sendMetricsAndLogRegistrationError(exception.getErrorCode(), exception.getErrorMessage(), exception.getMapAccountManagerDetailedErrorType(), exception.getMAPApiName());
    }

    private void showDialogForMapRegistrationError(final int mapErrorCode, final String mapAccountManagerDetailedErrorType) {
        if (mapErrorCode == MAPAccountManager.RegistrationError.NETWORK_FAILURE.value()) {
            showDialogBasedOnNetworkFailureType(mapAccountManagerDetailedErrorType);
        } else if (mapErrorCode == MAPAccountManager.RegistrationError.REGISTER_FAILED.value()) {
            cancelSignInFlow();
        } else {
            showErrorDialogFragmentWithMessage(MAPUtil.convertMAPErrorCodeToMessageId(mapErrorCode));
            setResult(0);
        }
    }

    private void sendMetricsAndLogRegistrationError(final int mapErrorCode, final String mapErrorMessage, final String mapAccountManagerDetailedErrorType, final MAPApiName mapApiName) {
        recordMapRegistrationError(mapErrorCode, mapErrorMessage, mapAccountManagerDetailedErrorType, mapApiName);
        emitFlexEventForRegistrationException(mapErrorCode, mapErrorMessage, mapAccountManagerDetailedErrorType, mapApiName);
    }

    private void showDialogBasedOnNetworkFailureType(final String mapAccountManagerDetailedErrorType) {
        if (userNeedsToUpgradeWebview(mapAccountManagerDetailedErrorType)) {
            showWebViewDialog();
        } else {
            showNetworkConnectionFailedDialog();
        }
    }

    private boolean userNeedsToUpgradeWebview(final String mapAccountManagerDetailedErrorType) {
        return MAPAccountManager.KEY_ERROR_CODE_WEBVIEW_SSL_ERROR.equals(mapAccountManagerDetailedErrorType);
    }

    private void showWebViewDialog() {
        Log.error(TAG, "Displaying SSL Error Web View Dialog prompting user to upgrade web view.");
        setResult(3);
        WebViewUtil.handleSslErrorWithMapSignInWebView(this);
    }

    private void showNetworkConnectionFailedDialog() {
        startActivity(new Intent(this, (Class<?>) NetworkFailureWithSettingsDialog.class));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelSignInFlow() {
        setResult(0);
        finish();
    }

    private void emitFlexEventForRegistrationException(final int errorCode, final String errorMessage, final String mapAccountManagerDetailedErrorType, final MAPApiName mapApiName) {
        MetricsLogger.sendEvent(FlexEvent.builder(mapApiName.toString()).withFlexNum1(Float.valueOf(errorCode)).withFlexStr1(errorMessage).withFlexStr2(mapAccountManagerDetailedErrorType).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emitFlexEventForShowSsoWelcome(final String flexEventName) {
        Log.debug(TAG, "Emitting %s flex event", flexEventName);
        MetricsLogger.sendEvent(FlexEvent.builder(flexEventName).build());
    }

    private void recordMapRegistrationError(final int mapErrorCode, final String mapErrorMessage, final String mapAccountManagerDetailedErrorType, final MAPApiName mapApiName) {
        String name;
        try {
            name = MAPAccountManager.RegistrationError.fromValue(mapErrorCode).getName();
        } catch (IndexOutOfBoundsException unused) {
            name = "Unknown";
        }
        if (!TextUtils.isEmpty(mapAccountManagerDetailedErrorType)) {
            Log.error(TAG, "Recieved an error while registering: MAP API that threw exception was: %s - Registration Error Name: %s -Error Code: %d - Error Message: %s. MAPAccountManagerDetailedErrorType is specified: %s", mapApiName, name, Integer.valueOf(mapErrorCode), mapErrorMessage, mapAccountManagerDetailedErrorType);
        } else {
            Log.error(TAG, "Recieved an error while registering:  MAP API that threw exception was: %s - Registration Error Name: %s -Error Code: %d - Error Message: %s.", mapApiName, name, Integer.valueOf(mapErrorCode), mapErrorMessage);
        }
    }

    private void recordGeneralMapError(final String exceptionName, final ErrorCodeAndMessage errorCodeAndMessage) {
        int errorCode = errorCodeAndMessage.getErrorCode();
        Log.error(TAG, "Received a MAP related exception: %s with error code: %d and error message: %s", exceptionName, Integer.valueOf(errorCode), errorCodeAndMessage.getErrorMessage());
    }

    private void showErrorDialogFragmentWithMessage(final int errorMessageId) {
        SignInFlowErrorDialogFragment signInFlowErrorDialogFragmentNewInstance = SignInFlowErrorDialogFragment.newInstance(errorMessageId);
        FragmentTransaction fragmentTransactionBeginTransaction = getFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.add(signInFlowErrorDialogFragmentNewInstance, SignInFlowErrorDialogFragment.TAG);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    private ErrorCodeAndMessage extractInfoFromMAPCallbackErrorException(final MAPCallbackErrorException exception) {
        return new ErrorCodeAndMessage(exception.getErrorBundle().getInt("com.amazon.dcp.sso.ErrorCode"), exception.getErrorBundle().getString("com.amazon.dcp.sso.ErrorMessage"));
    }

    private ErrorCodeAndMessage extractInfoFromMAPEmptyReturnValueException(final MAPEmptyReturnValueException exception) {
        Bundle responseBundle = exception.getResponseBundle();
        return new ErrorCodeAndMessage(responseBundle.getInt("com.amazon.dcp.sso.ErrorCode"), responseBundle.getString("com.amazon.dcp.sso.ErrorMessage"));
    }

    private class ErrorCodeAndMessage {
        private final int errorCode;
        private final String errorMessage;

        public ErrorCodeAndMessage(final int errorCode, final String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public final int getErrorCode() {
            return this.errorCode;
        }

        public final String getErrorMessage() {
            return this.errorMessage;
        }
    }
}
