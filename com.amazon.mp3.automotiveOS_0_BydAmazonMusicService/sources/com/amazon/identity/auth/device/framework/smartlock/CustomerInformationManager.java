package com.amazon.identity.auth.device.framework.smartlock;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.autofill.HintConstants;
import com.amazon.identity.auth.device.framework.MAPRuntimePermissionHandler;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.o6;
import com.amazon.identity.auth.device.p6;
import com.amazon.identity.auth.device.v6;
import com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommon;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class CustomerInformationManager {
    public static final Pattern d = Pattern.compile("^\\+?[1-9]\\d{1,14}$");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f333a;
    public b b;
    public boolean c;

    /* JADX INFO: compiled from: DCP */
    public enum HintType {
        NAME,
        EMAIL,
        PHONE
    }

    /* JADX INFO: compiled from: DCP */
    public interface a {
    }

    /* JADX INFO: compiled from: DCP */
    public static class b implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Activity f334a;
        public final int b;
        public GoogleApiClient c;

        public b(Activity activity, int i) {
            this.f334a = activity;
            this.b = i;
            this.c = new GoogleApiClient.Builder(activity).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Auth.CREDENTIALS_API).build();
        }

        public void a(Set<HintType> set) throws IntentSender.SendIntentException {
            boolean z = true;
            HintRequest.Builder phoneNumberIdentifierSupported = new HintRequest.Builder().setHintPickerConfig(new CredentialPickerConfig.Builder().setShowCancelButton(true).setPrompt(1).build()).setPhoneNumberIdentifierSupported(set.contains(HintType.PHONE));
            if (!set.contains(HintType.NAME) && !set.contains(HintType.EMAIL)) {
                z = false;
            }
            this.f334a.startIntentSenderForResult(Auth.CredentialsApi.getHintPickerIntent(this.c, phoneNumberIdentifierSupported.setEmailAddressIdentifierSupported(z).build()).getIntentSender(), this.b, null, 0, 0, 0);
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public void onConnected(@Nullable Bundle bundle) {
            Log.i(ga.a("CustomerInfoManager"), "google api client onConnected");
        }

        @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Log.e(ga.a("CustomerInfoManager"), "google api client onConnectionFailed:" + connectionResult.toString());
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public void onConnectionSuspended(int i) {
            Log.i(ga.a("CustomerInfoManager"), "google api client onConnectionSuspended");
        }
    }

    public CustomerInformationManager(Activity activity, int i) {
        this.c = MAPRuntimePermissionHandler.a(activity.getApplicationContext());
        ga.c("CustomerInfoManager", "smartlock supporting:" + this.c);
        this.f333a = null;
        if (this.c) {
            this.b = new b(activity, i);
        } else {
            this.b = null;
        }
    }

    public void a(Set<HintType> set, a aVar) {
        String string;
        String string2 = JavaScriptBridgeCommon.GENERAL_ERROR;
        try {
            this.f333a = aVar;
            if (this.c) {
                this.b.a(set);
                return;
            }
            CustomeInformationResultType customeInformationResultType = CustomeInformationResultType.NO_PLAY_SERVICE_SUPPORT;
            if (aVar == null) {
                Log.w(ga.a("CustomerInfoManager"), "got null consumer callback, there may be errors when sending hint request");
                return;
            }
            v6.d.a aVar2 = (v6.d.a) aVar;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("resultType", customeInformationResultType.name());
                string = jSONObject.toString();
            } catch (JSONException unused) {
                string = JavaScriptBridgeCommon.GENERAL_ERROR;
            }
            v6.d dVar = v6.d.this;
            v6.this.b("mapJSCallback", dVar.f630a, string);
            this.f333a = null;
        } catch (Throwable th) {
            Log.e(ga.a("CustomerInfoManager"), "startIntentSenderForResult", th);
            CustomeInformationResultType customeInformationResultType2 = CustomeInformationResultType.ERROR;
            a aVar3 = this.f333a;
            if (aVar3 == null) {
                Log.w(ga.a("CustomerInfoManager"), "got null consumer callback, there may be errors when sending hint request");
                return;
            }
            v6.d.a aVar4 = (v6.d.a) aVar3;
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("resultType", customeInformationResultType2.name());
                string2 = jSONObject2.toString();
            } catch (JSONException unused2) {
            }
            v6.d dVar2 = v6.d.this;
            v6.this.b("mapJSCallback", dVar2.f630a, string2);
            this.f333a = null;
        }
    }

    public final p6 b(int i, Intent intent) throws Exception {
        String str;
        if (i != -1) {
            if (i == 1002) {
                Log.i(ga.a("CustomerInfoManager"), "No Available hint");
                return new p6(CustomeInformationResultType.NO_HINTS_AVAILABLE, null);
            }
            Log.i(ga.a("CustomerInfoManager"), "Hint Read cancelled");
            return new p6(CustomeInformationResultType.CANCELLED, null);
        }
        Credential parcelableExtra = intent.getParcelableExtra("com.google.android.gms.credentials.Credential");
        String id = parcelableExtra.getId();
        if (TextUtils.isEmpty(id)) {
            throw new RuntimeException("got empty id from hint response:" + id);
        }
        String name = parcelableExtra.getName();
        if (d.matcher(id).matches()) {
            str = id;
            id = null;
        } else {
            str = null;
        }
        return new p6(CustomeInformationResultType.OK, new o6(name, null, id, str));
    }

    public void a(p6 p6Var) {
        String string;
        a aVar = this.f333a;
        if (aVar == null) {
            Log.w(ga.a("CustomerInfoManager"), "got null consumer callback, there may be errors when sending hint request");
            return;
        }
        v6.d.a aVar2 = (v6.d.a) aVar;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("resultType", p6Var.f490a.name());
            if (p6Var.b != null) {
                JSONObject jSONObject2 = new JSONObject();
                o6 o6Var = p6Var.b;
                String str = o6Var.c;
                if (str != null) {
                    jSONObject2.put("email", str);
                }
                String str2 = o6Var.f475a;
                if (str2 != null) {
                    jSONObject2.put("name", str2);
                }
                String str3 = o6Var.b;
                if (str3 != null) {
                    jSONObject2.put("namePron", str3);
                }
                String str4 = o6Var.d;
                if (str4 != null) {
                    jSONObject2.put(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, str4);
                }
                jSONObject.put("information", jSONObject2);
            }
            string = jSONObject.toString();
        } catch (JSONException unused) {
            string = JavaScriptBridgeCommon.GENERAL_ERROR;
        }
        v6.d dVar = v6.d.this;
        v6.this.b("mapJSCallback", dVar.f630a, string);
        this.f333a = null;
    }

    public void a(int i, Intent intent) {
        p6 p6Var;
        try {
            p6Var = b(i, intent);
        } catch (Exception e) {
            Log.e(ga.a("CustomerInfoManager"), "parseActivityResult error:" + e.getMessage(), e);
            p6Var = new p6(CustomeInformationResultType.ERROR, null);
        }
        a(p6Var);
    }
}
