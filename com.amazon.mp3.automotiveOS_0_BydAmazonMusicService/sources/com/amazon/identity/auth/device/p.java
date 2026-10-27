package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.kcpsdk.auth.ParseErrorException;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceErrorType;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import com.amazon.identity.kcpsdk.common.ParseError;
import com.amazon.identity.kcpsdk.common.WebProtocol;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
import com.amazonaws.http.HttpHeader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class p {
    public static final Set<String> l = new HashSet(Arrays.asList("com.amazon.alta.h2clientservice", "com.amazon.map.sample.one", "com.amazon.map.sample.two", "com.amazon.map.sample.three"));
    public static final String m = p.class.getName();
    public static final long n = wa.c(1, TimeUnit.MILLISECONDS);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f482a;
    public final o5 b;
    public final TokenManagement c;
    public final MAPAccountManager d;
    public final l4 e;
    public final z f;
    public final OAuthTokenManager g;
    public final AmazonAccountManager h;
    public final z3 i;
    public final u7 j;
    public final t8 k;

    /* JADX INFO: compiled from: DCP */
    public class a extends c {
        public final /* synthetic */ String c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(b bVar, String str) {
            super(bVar);
            this.c = str;
        }

        @Override // com.amazon.identity.auth.device.p.c
        public d a(kc kcVar) {
            Bundle bundle = new Bundle();
            bundle.putString("com.amazon.dcp.sso.property.username", kcVar.g);
            bundle.putString("com.amazon.dcp.sso.property.firstname", kcVar.h);
            bundle.putString("com.amazon.dcp.sso.property.devicename", kcVar.e);
            w1.b(p.this.f482a, this.c, kcVar.e);
            String str = kcVar.i;
            if (str != null) {
                bundle.putString("com.amazon.dcp.sso.property.deviceemail", str);
            } else {
                Log.i(ga.a(p.m), "Was not able to updated device email since it was not returned");
            }
            bundle.putString(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN, kcVar.f409a);
            bundle.putString(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY, kcVar.f);
            bundle.putString("com.amazon.dcp.sso.property.account.acctId", kcVar.k);
            t7 t7Var = new t7(Collections.unmodifiableList(kcVar.t));
            t7Var.a();
            bundle.putString(AccountConstants.TOKEN_TYPE_COOKIE_XMAIN_TOKEN, t7Var.c);
            bundle.putString("com.amazon.dcp.sso.token.cookie.xmainAndXabcCookies", t7Var.b());
            return new d((Bundle) bundle.clone());
        }

        @Override // com.amazon.identity.auth.device.p.c
        public void a(RegisterDeviceErrorType registerDeviceErrorType) {
        }
    }

    /* JADX INFO: compiled from: DCP */
    public interface b {
        void a(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError, String str2, Bundle bundle);

        void a(String str, String str2, Bundle bundle);
    }

    public p(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        t5 t5VarA = t5.a(context);
        this.f482a = t5VarA;
        this.h = new AmazonAccountManager(t5VarA);
        this.b = (o5) t5VarA.getSystemService("dcp_device_info");
        this.c = (TokenManagement) t5VarA.getSystemService("dcp_token_mangement");
        this.d = new MAPAccountManager(t5VarA);
        this.e = (l4) t5VarA.getSystemService("sso_webservice_caller_creator");
        this.f = new z(t5VarA);
        this.g = new OAuthTokenManager(t5VarA);
        this.i = t5VarA.b();
        this.j = ((v7) t5VarA.getSystemService("dcp_data_storage_factory")).a();
        this.k = new t8(t5VarA);
    }

    public static void a(p pVar, kc kcVar, u uVar) {
        pVar.getClass();
        Map<String, String> map = kcVar.s;
        if (map == null) {
            Log.i(ga.a(m), "device info attribute is null in register Response.");
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Log.i(ga.a(m), "device attribute received: " + entry.getKey() + " value: " + entry.getValue());
            pVar.j.b("device.metadata", entry.getKey(), entry.getValue());
        }
    }

    public static boolean b(p pVar, kc kcVar, u uVar) {
        pVar.getClass();
        if (uVar == null) {
            Log.e(ga.a(m), "Could not save credentials because no AccountRegistrarAuthenticator was given.");
        } else {
            if (pVar.a(kcVar)) {
                String str = kcVar.f409a;
                String str2 = kcVar.f;
                HashMap map = new HashMap();
                map.put(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN, str);
                map.put(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY, str2);
                Log.i(ga.a("AccountRegistrarAuthenticator"), String.format("Setting anonymous credentials adp token: %s, private key: %s", ga.a(str, 5), ga.a(str2, 5)));
                uVar.b.a("com.amazon.identity.auth.device.credentials.AnonymousAccountCredentials.SHARED_PREFS", map);
                return true;
            }
            Log.e(ga.a(m), "Was expecting anonymous credentials, but received account credentials");
        }
        return false;
    }

    /* JADX INFO: compiled from: DCP */
    public static abstract class c extends tb {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b f483a;
        public final MAPAccountManager b;

        public c(b bVar) {
            this.f483a = bVar;
            this.b = null;
        }

        public abstract d a(kc kcVar);

        public abstract void a(RegisterDeviceErrorType registerDeviceErrorType);

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // com.amazon.identity.auth.device.tb
        public void a(Object obj) {
            String str = p.m;
            Log.i(ga.a(str), "Receive response from server side of the registration request, parsing the response.");
            kc kcVar = (kc) obj;
            if (kcVar == null) {
                this.f483a.a(MAPError.CommonError.INVALID_RESPONSE, "Error occurred during registration. Received a null response", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Registration Error: Null response", null);
                return;
            }
            jc jcVar = kcVar.l;
            bundle = null;
            Bundle bundle = null;
            if (jcVar == null) {
                d dVarA = a(kcVar);
                MAPAccountManager.RegistrationError registrationError = dVarA.f484a;
                if (registrationError == null) {
                    b bVar = this.f483a;
                    Bundle bundle2 = dVarA.d;
                    String string = bundle2 == null ? null : bundle2.getString("com.amazon.dcp.sso.property.account.acctId");
                    Bundle bundle3 = dVarA.d;
                    bVar.a(string, bundle3 != null ? bundle3.getString("com.amazon.dcp.sso.property.username") : null, dVarA.d);
                    return;
                }
                this.f483a.a(dVarA.b, dVarA.c, registrationError, "Registration Error: " + dVarA.f484a.toString(), null);
                return;
            }
            String errorString = jcVar.f399a.getErrorString();
            Log.e(ga.a(str), "Error string: " + errorString);
            ob obVar = kcVar.m;
            switch (jcVar.f399a) {
                case RegisterDeviceErrorTypeCustomerNotFound:
                    if (obVar != null && obVar.e != null) {
                        bundle = new Bundle();
                        String str2 = obVar.e;
                        if (str2 != null) {
                            bundle.putString(MAPAccountManager.KEY_AUTH_DATA_ADDITIONAL_INFO, str2);
                        }
                    }
                    this.f483a.a(MAPError.AccountError.CUSTOMER_NOT_FOUND, "Error occurred during registration. Customer not found. Invalid credentials.", MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND, "Registration Error: Customer not found. Invalid credentials.", bundle);
                    break;
                case RegisterDeviceErrorTypeDeviceAlreadyRegistered:
                    this.f483a.a(MAPError.AccountError.DEVICE_ALREADY_REGISTERED, "Error occurred during registration. Device already registered", MAPAccountManager.RegistrationError.DEVICE_ALREADY_REGISTERED, "Registration Error: Device already registered", null);
                    break;
                case RegisterDeviceErrorTypeDeviceNotRegisteredPanda:
                    yd.a("PrimaryAccountDeregisteredWhenRegisterSecondaryWithPanda", new String[0]);
                    this.f483a.a(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, String.format("Error occurred during registration. %s", errorString), MAPAccountManager.RegistrationError.ALREADY_DEREGISTERED, "Registration Error: " + errorString, null);
                    break;
                case RegisterDeviceErrorTypeDuplicateDeviceName:
                    this.f483a.a(MAPError.AccountError.DUPLICATE_DEVICE_NAME, "Error occurred during registration. Duplicate device name", MAPAccountManager.RegistrationError.DUPLICATE_DEVICE_NAME, "Registration Error: Duplicate device name", null);
                    break;
                case RegisterDeviceErrorTypeUnrecognized:
                case RegisterDeviceErrorTypeUnrecognizedFirs:
                case RegisterDeviceErrorTypeUnrecognizedKindle:
                case RegisterDeviceErrorTypeUnrecognizedPanda:
                case RegisterDeviceErrorTypeInternal:
                default:
                    this.f483a.a(MAPError.CommonError.INVALID_RESPONSE, "Error occurred during registration. Unrecognized response. If this is a 1st party Amazon device and it is not corrected from a retry, please verify that your device has been fulfilled.", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Registration Error: Unrecognized response. If this is a 1st party Amazon device and is not corrected from a retry, please verify that your device has been fulfilled.", null);
                    break;
                case RegisterDeviceErrorTypeChallengeResponse:
                    Log.i(ga.a(str), "Registration Error: Challenge Response Received");
                    if (obVar != null) {
                        Bundle bundle4 = new Bundle();
                        bundle4.putBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE, obVar.a());
                        b bVar2 = this.f483a;
                        MAPError.AccountError accountError = MAPError.AccountError.AUTHENTICATION_CHALLENGED;
                        bVar2.a(accountError, accountError.getErrorMessage(), MAPAccountManager.RegistrationError.AUTHENTICATION_CHALLENGED, null, bundle4);
                    } else {
                        this.f483a.a(MAPError.AccountError.MISSING_CHALLENGE_EXCEPTION, "Error occurred during registration. Challenge Exception was missing.", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Registration Error: Unknown. Challenge Exception was missing.", null);
                    }
                    break;
                case RegisterDeviceErrorTypeMissingValue:
                    this.f483a.a(MAPError.CommonError.BAD_REQUEST, "One or more required values are missing", MAPAccountManager.RegistrationError.BAD_REQUEST, "MAP internal bug: One or more required values are missing", null);
                    break;
                case RegisterDeviceErrorTypeInvalidValue:
                    this.f483a.a(MAPError.CommonError.BAD_REQUEST, "Error occurred during registration. One or more required values are invalid", MAPAccountManager.RegistrationError.BAD_REQUEST, "Registration Error: One or more required values are invalid", null);
                    break;
                case RegisterDeviceErrorTypeInvalidTokenPanda:
                    this.f483a.a(MAPError.AccountError.AUTHENTICATION_FAILED, String.format("Error occurred during registration. Authentication failed with message: %s", errorString), MAPAccountManager.RegistrationError.AUTHENTICATION_FAILED, "Registration Error: " + errorString, null);
                    break;
                case RegisterDeviceErrorTypeProtocolError:
                    this.f483a.a(MAPError.AccountError.UNSUPPORTED_PROTOCOL, "Protocol not supported. SSL required", MAPAccountManager.RegistrationError.UNRECOGNIZED, "MAP internal bug: The Protocol is not supported. SSL required", null);
                    break;
                case RegisterDeviceErrorTypeMethodNotAllowed:
                    this.f483a.a(MAPError.AccountError.INVALID_HTTP_METHOD, "The HTTP method is not valid. For example, using POST instead of GET", MAPAccountManager.RegistrationError.UNRECOGNIZED, "MAP internal bug: The HTTP method is not valid. For example, using POST instead of GET", null);
                    break;
                case RegisterDeviceErrorTypeServerError:
                    this.f483a.a(MAPError.CommonError.SERVER_ERROR, "Error occurred during registration. The server has encountered a runtime error, or the service is temporarily overloaded or unavailable, try again later", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Registration Error: The server has encountered a runtime error, or the service is temporarily overloaded or unavailable, try again later", null);
                    break;
                case RegisterDeviceErrorTypeNotImplemented:
                    this.f483a.a(MAPError.CommonError.FEATURE_NOT_IMPLEMENTED, "The feature has not been implemented yet", MAPAccountManager.RegistrationError.UNRECOGNIZED, "MAP internal bug: The feature is not implemented", null);
                    break;
                case RegisterDeviceErrorTypeInvalidDirectedId:
                    this.f483a.a(MAPError.CommonError.BAD_REQUEST, "Error occurred during registration. The directedId is invalid. Either the customer directedId is invalid, or the device directedId is invalid", MAPAccountManager.RegistrationError.BAD_REQUEST, "Registration Error: The directedId is invalid. e.g. The customer directedId is invalid. The device directedId is invalid", null);
                    break;
                case RegisterDeviceErrorTypeInvalidDevice:
                    this.f483a.a(MAPError.CommonError.BAD_REQUEST, "Error occurred during registration. The device information is invalid.", MAPAccountManager.RegistrationError.BAD_REQUEST, "Registration Error: The device information is invalid.", null);
                    break;
                case RegisterDeviceErrorTypeUnauthorizedPanda:
                    this.f483a.a(MAPError.AccountError.AUTHENTICATION_FAILED, String.format("Error occurred during registration. Authentication failed with message: %s", errorString), MAPAccountManager.RegistrationError.AUTHENTICATION_FAILED, "Registration Error: " + errorString, null);
                    break;
                case RegisterDeviceErrorTypeServerUnavailable:
                    this.f483a.a(MAPError.CommonError.SERVICE_UNAVAILABLE, "Error occurred during registration. The service is temporarily overloaded or unavailable, try again later", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Registration Error: The service is temporarily overloaded or unavailable, try again later", null);
                    break;
                case RegisterDeviceErrorTypePrimaryAccountDeregisteredWhenRegisterSecondary:
                    this.f483a.a(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, String.format("Error occurred during registration. %s", errorString), MAPAccountManager.RegistrationError.ALREADY_DEREGISTERED, "Registration Error: " + errorString, null);
                    break;
                case RegisterDeviceErrorTypeForbidden:
                    this.f483a.a(MAPError.CommonError.BAD_REQUEST, String.format("Error occurred during registration. %s", errorString), MAPAccountManager.RegistrationError.BAD_REQUEST, "Registration error: " + errorString, null);
                    break;
            }
            a(jcVar.f399a);
        }

        @Override // com.amazon.identity.auth.device.tb
        public void b() {
            yd.b("NetworkError4:AccountRegistrar", new String[0]);
            this.f483a.a(MAPError.CommonError.NETWORK_ERROR, "Network failure occurred while performing registration request", MAPAccountManager.RegistrationError.NETWORK_FAILURE, "Network failure performing registration request", null);
        }

        public c(b bVar, MAPAccountManager mAPAccountManager) {
            this.f483a = bVar;
            this.b = mAPAccountManager;
        }

        @Override // com.amazon.identity.auth.device.tb
        public void a() {
            this.f483a.a(MAPError.AccountError.AUTHENTICATION_FAILED, "Authentication failure occurred while performing registration request", MAPAccountManager.RegistrationError.AUTHENTICATION_FAILED, "Authentication failure performing registration request", null);
        }

        @Override // com.amazon.identity.auth.device.tb
        public void a(ParseError parseError) {
            this.f483a.a(MAPError.CommonError.PARSE_ERROR, "Parsing failure occurred while performing registration request", MAPAccountManager.RegistrationError.PARSE_ERROR, "Parsing failure performing registration request", null);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public MAPAccountManager.RegistrationError f484a;
        public MAPError b;
        public String c;
        public Bundle d;

        public d(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError) {
            this.f484a = registrationError;
            this.b = mAPError;
            this.c = str;
        }

        public d(Bundle bundle) {
            this.d = bundle;
        }

        public d() {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest r18, android.os.Bundle r19, java.lang.String r20, com.amazon.identity.auth.device.u r21, com.amazon.identity.auth.device.p.b r22, boolean r23, com.amazon.identity.auth.device.y5 r24) {
        /*
            Method dump skipped, instruction units count: 889
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.p.a(com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest, android.os.Bundle, java.lang.String, com.amazon.identity.auth.device.u, com.amazon.identity.auth.device.p$b, boolean, com.amazon.identity.auth.device.y5):void");
    }

    public final RegisterDeviceRequest.DeviceAccountRole a(Bundle bundle) {
        if (bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsPrimary")) {
            return RegisterDeviceRequest.DeviceAccountRole.PRIMARY;
        }
        if (bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary")) {
            return RegisterDeviceRequest.DeviceAccountRole.SECONDARY;
        }
        return RegisterDeviceRequest.DeviceAccountRole.UNDEFINED;
    }

    public final boolean a(RegistrationType registrationType, Bundle bundle) {
        if (!bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsPrimary") || RegistrationType.FROM_ACCESS_TOKEN.equals(registrationType) || RegistrationType.WITH_LOGIN_CREDENTIALS.equals(registrationType) || RegistrationType.FROM_AUTH_TOKEN.equals(registrationType) || RegistrationType.WITH_DIRECTEDID_CREDENTIALS.equals(registrationType) || RegistrationType.WITH_EXPLICIT_URL.equals(registrationType) || RegistrationType.FROM_AUTHORIZATION_CODE.equals(registrationType)) {
            return false;
        }
        Log.e(ga.a(m), registrationType + " is not currently supported to add primary accounts ");
        return true;
    }

    public final boolean a(kc kcVar) {
        boolean z = kcVar.k == null && (kcVar.g == null || kcVar.e == null);
        Log.i(ga.a(m), "Is anonymous credentials received: " + z);
        return z;
    }

    public void a(b bVar, String str, String str2, d3 d3Var, boolean z, u uVar, y5 y5Var, Bundle bundle) {
        if (bVar == null) {
            throw new IllegalArgumentException("No response set. Could not deregister");
        }
        if (str2 == null) {
            bVar.a(MAPError.AccountError.DEREGISTER_FAILED, "Deregister failed because an account was not specified.", MAPAccountManager.RegistrationError.DEREGISTER_FAILED, "Deregister failed because an account was not specified.", null);
            return;
        }
        vb vbVar = new vb();
        vbVar.b = false;
        vbVar.c = z;
        vbVar.d = bundle;
        q qVar = new q(this, bVar, z, uVar, y5Var, str2);
        ld ldVar = vbVar.f638a;
        if (ldVar == null) {
            ld ldVar2 = new ld();
            vbVar.f638a = ldVar2;
            ldVar2.a(WebProtocol.WebProtocolHttps);
            vbVar.f638a.d = EnvironmentUtils.c.g();
            vbVar.f638a.c("/FirsProxy/disownFiona");
            ld ldVar3 = vbVar.f638a;
            ldVar3.h = HttpVerb.HttpVerbGet;
            if (vbVar.b) {
                ldVar3.a("contentDeleted", "true");
            } else {
                ldVar3.a("contentDeleted", "false");
            }
            if (vbVar.c) {
                vbVar.f638a.a("deregisterExisting", "true");
            } else {
                vbVar.f638a.a("deregisterExisting", "false");
            }
            Bundle bundle2 = vbVar.d;
            if (bundle2 != null) {
                Bundle bundle3 = bundle2.getBundle(MAPAccountManager.KEY_DEREGISTRATION_METADATA);
                if (bundle3 != null) {
                    for (String str3 : bundle3.keySet()) {
                        String string = bundle3.getString(str3);
                        if (string != null) {
                            vbVar.f638a.a(str3, string);
                        } else {
                            Log.e(ga.a(vb.e), String.format("Not setting the value of key %s to the deregistration request as the type is unsupported.", str3));
                        }
                    }
                } else {
                    ga.a(vb.e);
                }
            }
            vbVar.f638a.b(HttpHeader.CONTENT_TYPE, "text/xml");
            vbVar.f638a.j = true;
            String str4 = vb.e;
            Object[] objArr = new Object[1];
            objArr[0] = vbVar.b ? "Yes" : "No";
            ga.c(str4, "DeregisterDeviceRequest: getWebRequest: constructed a web request with:\nContent Deleted: %s", objArr);
            ldVar = vbVar.f638a;
        }
        a(ldVar, str2, str, d3Var, new xb(), qVar, bVar, y5Var);
    }

    public final Bundle a(b bVar, Bundle bundle, y5 y5Var) {
        Bundle bundle2;
        try {
            return this.f.a(bundle, y5Var);
        } catch (MAPCallbackErrorException e) {
            ga.a(m);
            d7 d7VarA = d7.a(e);
            if (d7VarA != null && !bundle.getBoolean(AccountConstants.KEY_RECOVERY_ATTEMPT)) {
                bVar.a(MAPError.AccountError.CORRUPTED_ACCOUNT, "The primary account has been corrupted. It should be recovered", MAPAccountManager.RegistrationError.INTERNAL_ERROR, "Primary account corrupted, should recover", d7VarA.c());
                return null;
            }
            Bundle errorBundle = e.getErrorBundle();
            int i = errorBundle.getInt("com.amazon.dcp.sso.ErrorCode");
            Bundle bundle3 = errorBundle.getBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE);
            MAPError error = e.getError() != null ? e.getError() : MAPError.CommonError.INTERNAL_ERROR;
            String errorMessage = error.getErrorMessage();
            if (bundle3 != null) {
                Bundle bundle4 = new Bundle();
                bundle4.putBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE, bundle3);
                String string = bundle3.getString(MAPAccountManager.KEY_AUTH_DATA_ADDITIONAL_INFO);
                if (string != null) {
                    bundle4.putString(MAPAccountManager.KEY_AUTH_DATA_ADDITIONAL_INFO, string);
                }
                bundle2 = bundle4;
            } else {
                bundle2 = null;
            }
            bVar.a(error, errorMessage, MAPAccountManager.RegistrationError.fromValue(i), null, bundle2);
            return null;
        }
    }

    public final void a(String str, Bundle bundle, u uVar, b bVar, RegisterDeviceRequest.CustomerAccountTokenType customerAccountTokenType, y5 y5Var) {
        boolean z;
        if (bVar != null) {
            if (TextUtils.isEmpty(str)) {
                bVar.a(MAPError.TokenError.MISSING_TOKEN, "Missing token", MAPAccountManager.RegistrationError.BAD_REQUEST, "Missing token", null);
                return;
            }
            t5 t5Var = this.f482a;
            RegisterDeviceRequest registerDeviceRequest = new RegisterDeviceRequest(t5Var, RegisterDeviceRequest.a(t5Var, bundle));
            if (id.b(str)) {
                Log.i(ga.a(RegisterDeviceRequest.b0), "isValidCustomerAccountToken: returning false because a null or empty auth token was given");
                z = false;
            } else {
                z = true;
            }
            if (!z) {
                Log.e(ga.a(RegisterDeviceRequest.b0), "setCustomerAccountToken: password was invalid. Cannot be set.");
            } else if (registerDeviceRequest.i == null && registerDeviceRequest.k == null) {
                registerDeviceRequest.l = str;
            } else {
                Log.e(ga.a(RegisterDeviceRequest.b0), "setCustomerAccountToken: cannot specify both an auth token and a login/password. Cannot be set.");
            }
            registerDeviceRequest.m = customerAccountTokenType;
            registerDeviceRequest.C = bundle.getString("com.amazon.dcp.sso.AddAccount.options.AuthTokenClientContext");
            registerDeviceRequest.a(b(bundle), a(bundle));
            a(registerDeviceRequest, bundle, null, uVar, bVar, false, y5Var);
            return;
        }
        throw new IllegalArgumentException("One or more arguments are null or empty");
    }

    public final void a(String str, String str2, String str3, b bVar, y5 y5Var) {
        if (!this.h.a(str2)) {
            MAPError.AccountError accountError = MAPError.AccountError.CUSTOMER_NOT_FOUND;
            bVar.a(accountError, accountError.getErrorMessage(), MAPAccountManager.RegistrationError.NO_ACCOUNT, "Account not currently registered", null);
            return;
        }
        vc vcVar = new vc();
        if (str != null) {
            vcVar.b(str);
        }
        Long lA = la.a(this.f482a, str3);
        if (lA != null) {
            kd kdVar = new kd(Long.toString(lA.longValue()));
            vcVar.a("TodoItem.GET.NAMS");
            vcVar.d = kdVar;
        }
        vcVar.f = new HashMap(z9.a(this.f482a));
        a aVar = new a(bVar, str2);
        ld ldVarB = vcVar.b();
        if (ldVarB != null) {
            a(ldVarB, str2, str3, (d3) null, new wc(), aVar, bVar, y5Var);
        } else {
            bVar.a(MAPError.CommonError.BAD_REQUEST, "Could not construct a updateCredentials request from this todo item", MAPAccountManager.RegistrationError.BAD_REQUEST, "Could not construct a updateCredentials request from this todo item", null);
        }
    }

    public void a(pc pcVar, tb tbVar, y5 y5Var) {
        try {
            lb lbVar = (lb) this.e.a(this.h.b(), y5Var);
            kc kcVar = (kc) lbVar.a(pcVar.c(), new mc(), tbVar);
            if (kcVar != null) {
                String str = kcVar.w;
                if (str != null) {
                    ga.a(m);
                    yd.a("ClockSkewHappened", new String[0]);
                    pcVar.f = str;
                    pcVar.e = null;
                    kcVar = (kc) lbVar.a(pcVar.c(), new mc(), tbVar);
                }
                tbVar.a(kcVar);
                return;
            }
            tbVar.a();
        } catch (ParseErrorException e) {
            tbVar.a(e.mError);
        } catch (IOException unused) {
            tbVar.b();
        } catch (UnsupportedOperationException unused2) {
            tbVar.b();
        }
    }

    public void a(ld ldVar, String str, String str2, d3 d3Var, WebResponseParser webResponseParser, tb tbVar, b bVar, y5 y5Var) {
        zb lbVar;
        if (str2 == null) {
            lbVar = this.e.a(str, y5Var);
        } else {
            if (d3Var == null) {
                d3Var = a(str, str2, bVar);
            }
            lbVar = new lb(this.e.f418a, new tc(d3Var), y5Var);
        }
        lb lbVar2 = (lb) lbVar;
        new ib(lbVar2.b, ldVar, new uc(new xc(webResponseParser), tbVar, ldVar.j), lbVar2.f422a, lbVar2.c).a();
    }

    public void a(String str, boolean z, u uVar, b bVar, y5 y5Var) {
        String strD = this.b.d();
        String strF = this.b.f();
        vc vcVar = new vc();
        if (!TextUtils.isEmpty(str)) {
            vcVar.b(str);
        }
        vcVar.f = new HashMap(z9.a(this.f482a));
        if (z) {
            vcVar.a("Transfer");
        }
        t tVar = new t(this, bVar, this.d, uVar, null, bVar, strD, strF);
        ld ldVarB = vcVar.b();
        if (ldVarB != null) {
            a(ldVarB, this.h.b(), (String) null, (d3) null, new mc(), tVar, bVar, y5Var);
        } else {
            bVar.a(MAPError.CommonError.BAD_REQUEST, "Could not construct a registration request from this todo item", MAPAccountManager.RegistrationError.BAD_REQUEST, "Could not construct a registration request from this todo item", null);
        }
    }

    public d3 a(String str, String str2, b bVar) {
        try {
            TokenManagement tokenManagement = this.c;
            String adpTokenKeyForPackage = TokenKeys.getAdpTokenKeyForPackage(str2);
            Bundle bundle = new Bundle();
            bundle.putBoolean("ignore.platform.restrictions", true);
            long j = n;
            String value = tokenManagement.getValue(str, adpTokenKeyForPackage, bundle, j);
            TokenManagement tokenManagement2 = this.c;
            String privateKeyKeyForPackage = TokenKeys.getPrivateKeyKeyForPackage(str2);
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("ignore.platform.restrictions", true);
            return new f3(value, tokenManagement2.getValue(str, privateKeyKeyForPackage, bundle2, j));
        } catch (MAPCallbackErrorException e) {
            Bundle errorBundle = e.getErrorBundle();
            Log.e(ga.a(m), "Getting ADP Token failed because of callback error. Error Bundle: " + errorBundle);
            d7 d7VarA = d7.a(e);
            if (d7VarA != null && bVar != null) {
                MAPError.CommonError commonError = MAPError.CommonError.INTERNAL_ERROR;
                bVar.a(commonError, commonError.getErrorMessage(), MAPAccountManager.RegistrationError.INTERNAL_ERROR, null, d7VarA.c());
            }
            return null;
        } catch (InterruptedException e2) {
            Log.e(ga.a(m), "Getting ADP Token failed because of InterruptedException: " + e2.getMessage());
            return null;
        } catch (ExecutionException e3) {
            Log.e(ga.a(m), "Getting ADP Token failed because of ExecutionException: " + e3.getMessage());
            return null;
        } catch (TimeoutException e4) {
            Log.e(ga.a(m), "Getting ADP Token failed because of TimeoutException: " + e4.getMessage());
            return null;
        }
    }

    public final String a(String str) {
        t5 t5Var = this.f482a;
        if (z9.a(t5Var, str)) {
            return z9.e(t5Var);
        }
        return null;
    }

    public final boolean b(Bundle bundle) {
        if (bundle.getBoolean(AccountConstants.KEY_RECOVERY_ATTEMPT) && this.h.e()) {
            return !TextUtils.equals(this.h.b(), bundle.getString("com.amazon.dcp.sso.property.account.acctId"));
        }
        return (bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary") || bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsPrimary")) && this.h.e();
    }

    public final boolean b(RegistrationType registrationType, Bundle bundle) {
        if (!bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary") && !bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsPrimary")) {
            return false;
        }
        if (RegistrationType.FROM_ACCESS_TOKEN.equals(registrationType) || RegistrationType.WITH_LOGIN_CREDENTIALS.equals(registrationType) || RegistrationType.REGISTER_DELEGATED_ACCOUNT.equals(registrationType) || RegistrationType.FROM_AUTH_TOKEN.equals(registrationType) || RegistrationType.WITH_DIRECTEDID_CREDENTIALS.equals(registrationType) || RegistrationType.WITH_EXPLICIT_URL.equals(registrationType) || RegistrationType.WITH_LINK_CODE.equals(registrationType) || RegistrationType.FROM_AUTHORIZATION_CODE.equals(registrationType) || !bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary")) {
            return !a(registrationType, bundle);
        }
        Log.e(ga.a(m), registrationType + " is not currently supported to add secondary accounts ");
        return false;
    }
}
