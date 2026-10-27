package com.amazon.identity.auth.device.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.f8;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.utils.ResourceHelper;
import com.amazon.identity.auth.device.yd;
import com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommon;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class GetAuthenticatorResultsActivity extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RemoteCallbackWrapper f190a;
    public f8 b;

    public final Bundle a(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("error", JavaScriptBridgeCommon.GENERAL_ERROR);
        bundle.putString("errorMessage", str);
        return bundle;
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        String strEncodeToString;
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            if (i == 0) {
                yd.b("CreateAuthenticatorCredential:FAILED", new String[0]);
                Log.i(ga.a("GetAuthenticatorResultsActivity"), "Create authenticator credential call failed");
            } else if (i == 1) {
                yd.b("GetAuthenticatorAssertion:FAILED", new String[0]);
                Log.i(ga.a("GetAuthenticatorResultsActivity"), "Get authenticator assertion call failed");
            } else {
                yd.b("UnexpectedOperation:FAILED", new String[0]);
                Log.i(ga.a("GetAuthenticatorResultsActivity"), "Unexpected operation call failed");
            }
            this.f190a.onError(a("Call returned with status code not ok."));
        } else if (intent.hasExtra("FIDO2_ERROR_EXTRA")) {
            AuthenticatorErrorResponse authenticatorErrorResponseDeserializeFromBytes = AuthenticatorErrorResponse.deserializeFromBytes(intent.getByteArrayExtra("FIDO2_ERROR_EXTRA"));
            int errorCodeAsInt = authenticatorErrorResponseDeserializeFromBytes.getErrorCodeAsInt();
            String errorMessage = authenticatorErrorResponseDeserializeFromBytes.getErrorMessage();
            MAPError mAPError = MAPError.FidoAuthenticatorError.AUTHENTICATOR_ERRORS_TO_MAP_ERRORS.get(Integer.valueOf(errorCodeAsInt));
            if (mAPError == null) {
                if (i == 0) {
                    yd.b("GetAuthResultsUnexpectedError:CreateCredential" + errorCodeAsInt + ":" + errorMessage, new String[0]);
                    Log.i(ga.a("GetAuthenticatorResultsActivity"), "CreateAuthenticatorCredential call in GetAuthenticatorResultsActivity returned with AuthenticatorErrorResponse that is not mapped to MAPError. AuthenticatorErrorResponse = " + errorCodeAsInt + " " + errorMessage);
                } else if (i == 1) {
                    yd.b("GetAuthResultsUnexpectedError:GetAssertion" + errorCodeAsInt + ":" + errorMessage, new String[0]);
                    Log.i(ga.a("GetAuthenticatorResultsActivity"), "GetAssertionWithAuthenticatorCredential call in GetAuthenticatorResultsActivity returned with AuthenticatorErrorResponse that is not mapped to MAPError. AuthenticatorErrorResponse = " + errorCodeAsInt + " " + errorMessage);
                } else {
                    yd.b("GetAuthResultsUnexpectedError:UnknownOperation" + errorCodeAsInt + ":" + errorMessage, new String[0]);
                    Log.i(ga.a("GetAuthenticatorResultsActivity"), "Unknown operation in GetAuthenticatorResultsActivity returned with AuthenticatorErrorResponse that is not mapped to MAPError. AuthenticatorErrorResponse = " + errorCodeAsInt + " " + errorMessage);
                }
                mAPError = MAPError.CommonError.INTERNAL_ERROR;
            }
            if (i == 0) {
                yd.b("CreateAuthenticatorCredential:" + mAPError.getErrorType(), new String[0]);
                Log.i(ga.a("GetAuthenticatorResultsActivity"), "Create authenticator credential returned with error = " + mAPError);
            } else if (i == 1) {
                yd.b("GetAuthenticatorAssertion:" + mAPError.getErrorType(), new String[0]);
                Log.i(ga.a("GetAuthenticatorResultsActivity"), "Get authenticator assertion returned with error = " + mAPError);
            } else {
                yd.b("UnexpectedOperation:" + mAPError.getErrorType(), new String[0]);
                Log.i(ga.a("GetAuthenticatorResultsActivity"), "Unexpected operation returned with error = " + mAPError);
            }
            RemoteCallbackWrapper remoteCallbackWrapper = this.f190a;
            Bundle bundle = new Bundle();
            bundle.putString("error", mAPError == MAPError.FidoAuthenticatorError.LOCK_SCREEN_NOT_SECURE ? MAPError.getErrorTypeWithUnderscores(mAPError.getErrorType()) : JavaScriptBridgeCommon.GENERAL_ERROR);
            bundle.putString("errorMessage", mAPError.getErrorMessage());
            remoteCallbackWrapper.onError(bundle);
        } else {
            if (i == 0) {
                try {
                    AuthenticatorAttestationResponse authenticatorAttestationResponseDeserializeFromBytes = AuthenticatorAttestationResponse.deserializeFromBytes(intent.getByteArrayExtra("FIDO2_RESPONSE_EXTRA"));
                    String strEncodeToString2 = authenticatorAttestationResponseDeserializeFromBytes.getAttestationObject() != null ? Base64.encodeToString(authenticatorAttestationResponseDeserializeFromBytes.getAttestationObject(), 3) : null;
                    String strEncodeToString3 = authenticatorAttestationResponseDeserializeFromBytes.getClientDataJSON() != null ? Base64.encodeToString(authenticatorAttestationResponseDeserializeFromBytes.getClientDataJSON(), 3) : null;
                    strEncodeToString = authenticatorAttestationResponseDeserializeFromBytes.getKeyHandle() != null ? Base64.encodeToString(authenticatorAttestationResponseDeserializeFromBytes.getKeyHandle(), 3) : null;
                    if (strEncodeToString2 == null || strEncodeToString3 == null || strEncodeToString == null) {
                        yd.b("CreateAuthenticatorCredential:FAILED", new String[0]);
                        Log.i(ga.a("GetAuthenticatorResultsActivity"), "Create authenticator credential returned null data responses");
                        this.f190a.onError(a("Create authenticator credential returned null data responses"));
                    } else {
                        yd.b("CreateAuthenticatorCredential:SUCCESS", new String[0]);
                        Log.i(ga.a("GetAuthenticatorResultsActivity"), "Successfully created authenticator credential");
                        if (!this.b.a(strEncodeToString, Boolean.TRUE)) {
                            this.f190a.onError(a("Error occurred while saving credential id"));
                            finish();
                            return;
                        } else {
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("attestationObject", strEncodeToString2);
                            bundle2.putString("clientDataJson", strEncodeToString3);
                            bundle2.putString("credentialId", strEncodeToString);
                            this.f190a.onSuccess(bundle2);
                        }
                    }
                } catch (Exception e) {
                    yd.b("CreateAuthenticatorCredential:FAILED", new String[0]);
                    Log.i(ga.a("GetAuthenticatorResultsActivity"), "Exception occurred while parsing AuthenticatorAttestationResponse for create authenticator credential call. Exception = " + e);
                    this.f190a.onError(a("Exception occurred while parsing AuthenticatorAttestationResponse for create authenticator credential call."));
                }
            } else if (i == 1) {
                try {
                    AuthenticatorAssertionResponse authenticatorAssertionResponseDeserializeFromBytes = AuthenticatorAssertionResponse.deserializeFromBytes(intent.getByteArrayExtra("FIDO2_RESPONSE_EXTRA"));
                    String strEncodeToString4 = authenticatorAssertionResponseDeserializeFromBytes.getAuthenticatorData() != null ? Base64.encodeToString(authenticatorAssertionResponseDeserializeFromBytes.getAuthenticatorData(), 3) : null;
                    String strEncodeToString5 = authenticatorAssertionResponseDeserializeFromBytes.getClientDataJSON() != null ? Base64.encodeToString(authenticatorAssertionResponseDeserializeFromBytes.getClientDataJSON(), 3) : null;
                    String strEncodeToString6 = authenticatorAssertionResponseDeserializeFromBytes.getSignature() != null ? Base64.encodeToString(authenticatorAssertionResponseDeserializeFromBytes.getSignature(), 3) : null;
                    String strEncodeToString7 = authenticatorAssertionResponseDeserializeFromBytes.getUserHandle() != null ? Base64.encodeToString(authenticatorAssertionResponseDeserializeFromBytes.getUserHandle(), 3) : null;
                    strEncodeToString = authenticatorAssertionResponseDeserializeFromBytes.getKeyHandle() != null ? Base64.encodeToString(authenticatorAssertionResponseDeserializeFromBytes.getKeyHandle(), 3) : null;
                    if (strEncodeToString6 != null) {
                        yd.b("GetAuthenticatorAssertion:SUCCESS", new String[0]);
                        Log.i(ga.a("GetAuthenticatorResultsActivity"), "Successfully generated assertion with authenticator credential");
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("authenticatorData", strEncodeToString4);
                        bundle3.putString("signature", strEncodeToString6);
                        bundle3.putString("credentialId", strEncodeToString);
                        bundle3.putString("userHandle", strEncodeToString7);
                        bundle3.putString("clientDataJson", strEncodeToString5);
                        this.f190a.onSuccess(bundle3);
                    } else {
                        yd.b("GetAuthenticatorAssertion:FAILED", new String[0]);
                        Log.i(ga.a("GetAuthenticatorResultsActivity"), "getAssertionWithAuthenticatorCredential returned a null signature response");
                        this.f190a.onError(a("getAssertionWithAuthenticatorCredential returned a null signature response"));
                    }
                } catch (Exception e2) {
                    yd.b("GetAuthenticatorAssertion:FAILED", new String[0]);
                    Log.i(ga.a("GetAuthenticatorResultsActivity"), "Exception occurred while parsing AuthenticatorAssertionResponse for getAssertionWithAuthenticatorCredential call. Exception = " + e2);
                    this.f190a.onError(a("Exception occurred while parsing AuthenticatorAssertionResponse for getAssertionWithAuthenticatorCredential call."));
                }
            } else {
                yd.b("UnexpectedOperation:SUCCESS", new String[0]);
                Log.i(ga.a("GetAuthenticatorResultsActivity"), "Unknown operation returned successfully.");
                this.f190a.onError(a("Unexpected operation was invoked"));
            }
        }
        finish();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(ResourceHelper.a(this, "layout", "getauthenticatorresultsactivitylayout"));
        this.b = f8.a(this, "fido_authenticator_credential_namespace");
        PendingIntent pendingIntent = (PendingIntent) getIntent().getExtras().get("pendingIntentKey");
        int intExtra = getIntent().getIntExtra("requestTypeKey", -1);
        RemoteCallbackWrapper remoteCallbackWrapper = (RemoteCallbackWrapper) getIntent().getParcelableExtra("callbackKey");
        this.f190a = remoteCallbackWrapper;
        if (remoteCallbackWrapper == null) {
            Log.i(ga.a("GetAuthenticatorResultsActivity"), "GetAuthenticatorResultsActivity invoked with null callback");
            finish();
            return;
        }
        try {
            startIntentSenderForResult(pendingIntent.getIntentSender(), intExtra, null, 0, 0, 0);
        } catch (Exception e) {
            Log.i(ga.a("GetAuthenticatorResultsActivity"), "Exception occurred while creating authenticator credential or generating an assertion. Error = " + e);
            this.f190a.onError(a("Exception occurred while creating authenticator credential or generating an assertion."));
            finish();
        }
    }
}
