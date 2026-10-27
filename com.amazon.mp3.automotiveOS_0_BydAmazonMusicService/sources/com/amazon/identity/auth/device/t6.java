package com.amazon.identity.auth.device;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.amazon.identity.auth.device.activity.GetAuthenticatorResultsActivity;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.u6;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommon;
import com.google.android.gms.fido.Fido;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.fido2.Fido2ApiClient;
import com.google.android.gms.fido.fido2.api.common.Attachment;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class t6 extends u6 {
    public static final Random g = new Random();
    public final Fido2ApiClient d;
    public final Context e;
    public final f8 f;

    /* JADX INFO: compiled from: DCP */
    public class a implements s6 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f565a;

        /* JADX INFO: renamed from: com.amazon.identity.auth.device.t6$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DCP */
        public class C0059a implements Callback {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ z6 f566a;

            public C0059a(a aVar, z6 z6Var) {
                this.f566a = z6Var;
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                try {
                    ((u6.c.a) this.f566a).a(bundle.getString("error"), bundle.getString("errorMessage"));
                } catch (Exception unused) {
                    ((u6.c.a) this.f566a).a(JavaScriptBridgeCommon.GENERAL_ERROR, "Exception occurred while calling Fido API");
                }
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("authenticatorData", bundle.containsKey("authenticatorData") ? bundle.getString("authenticatorData") : null);
                    jSONObject.put("clientDataJson", bundle.containsKey("clientDataJson") ? bundle.getString("clientDataJson") : null);
                    jSONObject.put("credentialId", bundle.containsKey("credentialId") ? bundle.getString("credentialId") : null);
                    jSONObject.put("signature", bundle.containsKey("signature") ? bundle.getString("signature") : null);
                    jSONObject.put("userHandle", bundle.containsKey("userHandle") ? bundle.getString("userHandle") : null);
                    ((u6.c.a) this.f566a).a(jSONObject.toString());
                } catch (JSONException unused) {
                    ((u6.c.a) this.f566a).a(JavaScriptBridgeCommon.GENERAL_ERROR, "JSONException occurred while parsing Fido API response");
                } catch (Exception unused2) {
                    ((u6.c.a) this.f566a).a(JavaScriptBridgeCommon.GENERAL_ERROR, "Exception occurred while calling Fido API");
                }
            }
        }

        /* JADX INFO: compiled from: DCP */
        public class b implements OnSuccessListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ z6 f567a;
            public final /* synthetic */ RemoteCallbackWrapper b;

            public b(z6 z6Var, RemoteCallbackWrapper remoteCallbackWrapper) {
                this.f567a = z6Var;
                this.b = remoteCallbackWrapper;
            }

            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(Object obj) {
                if (obj == null || !(obj instanceof PendingIntent)) {
                    ((u6.c.a) this.f567a).a(JavaScriptBridgeCommon.GENERAL_ERROR, "Exception occurred while calling Fido API");
                    return;
                }
                Intent intent = new Intent(t6.this.e, (Class<?>) GetAuthenticatorResultsActivity.class);
                intent.putExtra("requestTypeKey", 1);
                intent.putExtra("pendingIntentKey", (PendingIntent) obj);
                intent.putExtra("callbackKey", this.b);
                intent.setFlags(268435456);
                t6.this.e.startActivity(intent);
            }
        }

        /* JADX INFO: compiled from: DCP */
        public class c implements OnFailureListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ z6 f568a;

            public c(a aVar, z6 z6Var) {
                this.f568a = z6Var;
            }

            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                ((u6.c.a) this.f568a).a(JavaScriptBridgeCommon.GENERAL_ERROR, "Exception occurred while calling Fido API");
            }
        }

        public a(String str) {
            this.f565a = str;
        }

        @Override // com.amazon.identity.auth.device.s6
        public void a(JSONObject jSONObject, z6 z6Var) {
            try {
                Log.i(ga.a("AuthenticatorJavaScriptBridge"), String.format("AuthenticatorJavaScriptBridge getAssertionWithAuthenticatorCredential is called", new Object[0]));
                PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptionsA = t6.a(t6.this, this.f565a, z6Var);
                if (publicKeyCredentialRequestOptionsA != null) {
                    Task signPendingIntent = t6.this.d.getSignPendingIntent(publicKeyCredentialRequestOptionsA);
                    C0059a c0059a = new C0059a(this, z6Var);
                    t6.this.getClass();
                    signPendingIntent.addOnSuccessListener(new b(z6Var, new RemoteCallbackWrapper(c0059a)));
                    signPendingIntent.addOnFailureListener(new c(this, z6Var));
                }
            } catch (Exception unused) {
                ((u6.c.a) z6Var).a(JavaScriptBridgeCommon.GENERAL_ERROR, "Exception occurred while calling Fido API");
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements s6 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f569a;

        /* JADX INFO: compiled from: DCP */
        public class a implements Callback {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ JSONObject f570a;
            public final /* synthetic */ z6 b;

            public a(JSONObject jSONObject, z6 z6Var) {
                this.f570a = jSONObject;
                this.b = z6Var;
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                try {
                    ((u6.c.a) this.b).a(bundle.getString("error"), bundle.getString("errorMessage"));
                } catch (Exception unused) {
                    ((u6.c.a) this.b).a(JavaScriptBridgeCommon.GENERAL_ERROR, "Exception occurred while calling Fido API");
                }
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                String string = bundle.getString("attestationObject");
                String string2 = bundle.getString("clientDataJson");
                String string3 = bundle.getString("credentialId");
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("attestationObject", string);
                    jSONObject.put("clientDataJson", string2);
                    jSONObject.put("credentialId", string3);
                    jSONObject.put("challenge", this.f570a.getString("challenge"));
                    ((u6.c.a) this.b).a(jSONObject.toString());
                    i4.a(t6.this.e).a();
                } catch (JSONException unused) {
                    ((u6.c.a) this.b).a(JavaScriptBridgeCommon.GENERAL_ERROR, "JSONException occurred while parsing Fido API response");
                } catch (Exception unused2) {
                    ((u6.c.a) this.b).a(JavaScriptBridgeCommon.GENERAL_ERROR, "Exception occurred while calling Fido API");
                }
            }
        }

        /* JADX INFO: renamed from: com.amazon.identity.auth.device.t6$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DCP */
        public class C0060b implements OnSuccessListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ z6 f571a;
            public final /* synthetic */ RemoteCallbackWrapper b;

            public C0060b(z6 z6Var, RemoteCallbackWrapper remoteCallbackWrapper) {
                this.f571a = z6Var;
                this.b = remoteCallbackWrapper;
            }

            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(Object obj) {
                if (obj == null || !(obj instanceof PendingIntent)) {
                    ((u6.c.a) this.f571a).a(JavaScriptBridgeCommon.GENERAL_ERROR, "Exception occurred while calling Fido API");
                    return;
                }
                Intent intent = new Intent(t6.this.e, (Class<?>) GetAuthenticatorResultsActivity.class);
                intent.putExtra("requestTypeKey", 0);
                intent.putExtra("pendingIntentKey", (PendingIntent) obj);
                intent.putExtra("callbackKey", this.b);
                intent.setFlags(268435456);
                t6.this.e.startActivity(intent);
            }
        }

        /* JADX INFO: compiled from: DCP */
        public class c implements OnFailureListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ z6 f572a;

            public c(b bVar, z6 z6Var) {
                this.f572a = z6Var;
            }

            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                ((u6.c.a) this.f572a).a(JavaScriptBridgeCommon.GENERAL_ERROR, "Exception occurred while calling Fido API");
            }
        }

        public b(String str) {
            this.f569a = str;
        }

        @Override // com.amazon.identity.auth.device.s6
        public void a(JSONObject jSONObject, z6 z6Var) {
            try {
                Log.i(ga.a("AuthenticatorJavaScriptBridge"), String.format("AuthenticatorJavaScriptBridge createAuthenticatorCredential method is called", new Object[0]));
                PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptionsB = t6.b(t6.this, this.f569a, z6Var);
                if (publicKeyCredentialCreationOptionsB != null) {
                    Task registerPendingIntent = t6.this.d.getRegisterPendingIntent(publicKeyCredentialCreationOptionsB);
                    a aVar = new a(jSONObject, z6Var);
                    t6.this.getClass();
                    registerPendingIntent.addOnSuccessListener(new C0060b(z6Var, new RemoteCallbackWrapper(aVar)));
                    registerPendingIntent.addOnFailureListener(new c(this, z6Var));
                }
            } catch (Exception unused) {
                ((u6.c.a) z6Var).a(JavaScriptBridgeCommon.GENERAL_ERROR, "Exception occurred while calling Fido API");
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class c implements s6 {
        public c() {
        }

        @Override // com.amazon.identity.auth.device.s6
        public void a(JSONObject jSONObject, z6 z6Var) {
            try {
                Log.i(ga.a("AuthenticatorJavaScriptBridge"), String.format("AuthenticatorJavaScriptBridge getCurrentAppInfo method is called", new Object[0]));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("appIdentifier", t6.this.e.getPackageName());
                boolean z = ha.f;
                jSONObject2.put("mapVersion", "20220722N");
                jSONObject2.put("platform", "Android");
                ((u6.c.a) z6Var).a(jSONObject2.toString());
            } catch (JSONException unused) {
                ((u6.c.a) z6Var).a(JavaScriptBridgeCommon.INPUT_ERROR, "JSONException while parsing input");
            } catch (Exception unused2) {
                ((u6.c.a) z6Var).a(JavaScriptBridgeCommon.GENERAL_ERROR, "Exception occurred while calling API");
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class d implements s6 {
        public d() {
        }

        @Override // com.amazon.identity.auth.device.s6
        public void a(JSONObject jSONObject, z6 z6Var) {
            try {
                String string = jSONObject.getString("credentialId");
                Log.i(ga.a("AuthenticatorJavaScriptBridge"), String.format("AuthenticatorJavaScriptBridge isAuthenticatorCredentialAvailable method called", new Object[0]));
                ((u6.c.a) z6Var).a(t6.this.f.a(string, false).toString());
            } catch (JSONException unused) {
                Log.i(ga.a("AuthenticatorJavaScriptBridge"), "Call to isAuthenticatorCredentialAvailable failed because JSONException occurred while parsing input");
                yd.b("isAuthenticatorCredentialAvailable:JSONException", new String[0]);
                ((u6.c.a) z6Var).a(String.valueOf(false));
            } catch (Exception unused2) {
                Log.i(ga.a("AuthenticatorJavaScriptBridge"), "Call to isAuthenticatorCredentialAvailable failed because exception was thrown during function call");
                yd.b("isAuthenticatorCredentialAvailable:ExceptionOccurred", new String[0]);
                ((u6.c.a) z6Var).a(String.valueOf(false));
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class e implements s6 {

        /* JADX INFO: compiled from: DCP */
        public class a implements OnSuccessListener<Boolean> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ z6 f576a;

            public a(e eVar, z6 z6Var) {
                this.f576a = z6Var;
            }

            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(Boolean bool) {
                Boolean bool2 = bool;
                if (bool2 == null) {
                    Log.i(ga.a("AuthenticatorJavaScriptBridge"), "Call to isUserVerifyingPlatformAuthenticatorAvailable failed because the Fido method result was null");
                    yd.b("isUserVerifyingPlatformAuthenticatorAvailable:NullResult", new String[0]);
                    ((u6.c.a) this.f576a).a(String.valueOf(false));
                    return;
                }
                Log.i(ga.a("AuthenticatorJavaScriptBridge"), "Call to isUserVerifyingPlatformAuthenticatorAvailable succeeded with result = " + bool2.toString());
                yd.b("isUserVerifyingPlatformAuthenticatorAvailable:Success", new String[0]);
                ((u6.c.a) this.f576a).a(bool2.toString());
            }
        }

        /* JADX INFO: compiled from: DCP */
        public class b implements OnFailureListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ z6 f577a;

            public b(e eVar, z6 z6Var) {
                this.f577a = z6Var;
            }

            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                Log.i(ga.a("AuthenticatorJavaScriptBridge"), "Call to isUserVerifyingPlatformAuthenticatorAvailable failed because OnFailure was invoked with exception = " + exc);
                yd.b("isUserVerifyingPlatformAuthenticatorAvailable:OnFailureInvoked", new String[0]);
                ((u6.c.a) this.f577a).a(String.valueOf(false));
            }
        }

        public e() {
        }

        @Override // com.amazon.identity.auth.device.s6
        public void a(JSONObject jSONObject, z6 z6Var) {
            try {
                Log.i(ga.a("AuthenticatorJavaScriptBridge"), String.format("AuthenticatorJavaScriptBridge isUserVerifyingPlatformAuthenticatorAvailable method is called", new Object[0]));
                Task taskIsUserVerifyingPlatformAuthenticatorAvailable = t6.this.d.isUserVerifyingPlatformAuthenticatorAvailable();
                taskIsUserVerifyingPlatformAuthenticatorAvailable.addOnSuccessListener(new a(this, z6Var));
                taskIsUserVerifyingPlatformAuthenticatorAvailable.addOnFailureListener(new b(this, z6Var));
            } catch (Exception unused) {
                Log.i(ga.a("AuthenticatorJavaScriptBridge"), "Call to isUserVerifyingPlatformAuthenticatorAvailable failed because exception was thrown during function call");
                yd.b("isUserVerifyingPlatformAuthenticatorAvailable:ExceptionOccurred", new String[0]);
                ((u6.c.a) z6Var).a(String.valueOf(false));
            }
        }
    }

    public t6(WebView webView) {
        super(webView, "AuthenticatorJavaScriptBridge");
        Context applicationContext = webView.getContext().getApplicationContext();
        this.e = applicationContext;
        this.d = Fido.getFido2ApiClient(applicationContext);
        this.f = f8.a(applicationContext, "fido_authenticator_credential_namespace");
    }

    public static PublicKeyCredentialRequestOptions a(t6 t6Var, String str, z6 z6Var) {
        t6Var.getClass();
        PublicKeyCredentialRequestOptions.Builder builder = new PublicKeyCredentialRequestOptions.Builder();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("rpId")) {
                yd.b("GetAssertionWithAuthenticatorCredential:UsingDefaultRP_ID", new String[0]);
            }
            builder.setRpId(jSONObject.optString("rpId", "amazon.com"));
            builder.setChallenge(Base64.decode(jSONObject.getString("challenge"), 3));
            JSONObject jSONObject2 = jSONObject.getJSONArray("allowCredentials").getJSONObject(0);
            String string = jSONObject2.getString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(Transport.INTERNAL);
            arrayList.add(new PublicKeyCredentialDescriptor(string, Base64.decode(jSONObject2.getString("id"), 3), arrayList2));
            builder.setAllowList(arrayList);
            return builder.build();
        } catch (JSONException unused) {
            ((u6.c.a) z6Var).a(JavaScriptBridgeCommon.INPUT_ERROR, "JSONException while parsing get assertion options");
            return null;
        }
    }

    public static PublicKeyCredentialCreationOptions b(t6 t6Var, String str, z6 z6Var) {
        t6Var.getClass();
        PublicKeyCredentialCreationOptions.Builder builder = new PublicKeyCredentialCreationOptions.Builder();
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject("rp");
            builder.setRp(new PublicKeyCredentialRpEntity(jSONObject2.getString("id"), jSONObject2.getString("name"), (String) null));
            byte[] bArr = new byte[32];
            g.nextBytes(bArr);
            builder.setUser(new PublicKeyCredentialUserEntity(bArr, "Amazon Customer", (String) null, "Amazon Customer"));
            builder.setChallenge(Base64.decode(jSONObject.getString("challenge"), 3));
            JSONObject jSONObject3 = jSONObject.getJSONArray("pubKeyCredParams").getJSONObject(0);
            String string = jSONObject3.getString(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            int i = jSONObject3.getJSONObject("alg").getInt("code");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PublicKeyCredentialParameters(string, i));
            builder.setParameters(arrayList);
            AuthenticatorSelectionCriteria.Builder builder2 = new AuthenticatorSelectionCriteria.Builder();
            builder2.setAttachment(Attachment.PLATFORM);
            builder.setAuthenticatorSelection(builder2.build());
        } catch (JSONException unused) {
            ((u6.c.a) z6Var).a(JavaScriptBridgeCommon.INPUT_ERROR, "JSONException occurred while parsing input for credential creation options");
            return null;
        } catch (Exception unused2) {
            ((u6.c.a) z6Var).a(JavaScriptBridgeCommon.GENERAL_ERROR, "Exception occurred while creating credential creation options");
        }
        return builder.build();
    }

    @JavascriptInterface
    public void createAuthenticatorCredential(String str) {
        a("createAuthenticatorCredential", str, new b(str));
    }

    @JavascriptInterface
    public void getAssertionWithAuthenticatorCredential(String str) {
        a("getAssertionWithAuthenticatorCredential", str, new a(str));
    }

    @JavascriptInterface
    public void getCurrentAppInfo(String str) {
        a("getCurrentAppInfo", str, new c());
    }

    @JavascriptInterface
    public void isAuthenticatorCredentialAvailable(String str) {
        a("isAuthenticatorCredentialAvailable", str, new d());
    }

    @JavascriptInterface
    public void isUserVerifyingPlatformAuthenticatorAvailable(String str) {
        a("isUserVerifyingPlatformAuthenticatorAvailable", str, new e());
    }
}
