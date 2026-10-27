package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.kcpsdk.auth.PandaError;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceErrorType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class hc extends ic<kc> {
    public static final String u = "com.amazon.identity.auth.device.hc";
    public static final List<String> v = new ArrayList(Arrays.asList("account_pool", MAPAccountManager.KEY_DEVICE_NAME));
    public String b;
    public String c;
    public String d;
    public String e;
    public int f;
    public String g;
    public JSONArray h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public boolean s;
    public final Set<String> t;

    public hc(List<String> list) {
        HashSet hashSet = new HashSet(v);
        this.t = hashSet;
        hashSet.addAll(list);
        this.h = new JSONArray();
    }

    @Override // com.amazon.identity.auth.device.ic
    public kc a(ob obVar) throws JSONException {
        String str = obVar.f480a;
        ga.c(u, " PandaResponseJsonParser: response received a %s challenge.", str);
        return new kc(null, null, null, 0, null, null, null, null, null, null, null, obVar, ("AuthenticationFailed".equals(str) || "InvalidAuthenticationData".equals(str)) ? new jc(RegisterDeviceErrorType.RegisterDeviceErrorTypeCustomerNotFound) : new jc(RegisterDeviceErrorType.RegisterDeviceErrorTypeChallengeResponse));
    }

    /* JADX WARN: Removed duplicated region for block: B:90:? A[RETURN, SYNTHETIC] */
    @Override // com.amazon.identity.auth.device.ic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.identity.auth.device.kc b(org.json.JSONObject r18) throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 574
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.hc.b(org.json.JSONObject):java.lang.Object");
    }

    @Override // com.amazon.identity.auth.device.ic
    public kc c(JSONObject jSONObject) throws JSONException {
        ga.c(u, " PandaResponseJsonParser: response received a %s error.", jSONObject.toString());
        String string = jSONObject.getString("code");
        return a(string == null ? RegisterDeviceErrorType.RegisterDeviceErrorTypeUnrecognizedPanda : string.equals("ProtocolError") ? RegisterDeviceErrorType.RegisterDeviceErrorTypeProtocolError : string.equals("MethodNotAllowed") ? RegisterDeviceErrorType.RegisterDeviceErrorTypeMethodNotAllowed : string.equals("NotImplemented") ? RegisterDeviceErrorType.RegisterDeviceErrorTypeNotImplemented : string.equals("InvalidDirectedId") ? RegisterDeviceErrorType.RegisterDeviceErrorTypeInvalidDirectedId : string.equals("InvalidDevice") ? RegisterDeviceErrorType.RegisterDeviceErrorTypeInvalidDevice : string.equals("DeviceAlreadyRegistered") ? RegisterDeviceErrorType.RegisterDeviceErrorTypeDeviceAlreadyRegistered : string.equals("DuplicateDeviceName") ? RegisterDeviceErrorType.RegisterDeviceErrorTypeDuplicateDeviceName : string.equals("InvalidToken") ? RegisterDeviceErrorType.RegisterDeviceErrorTypeInvalidTokenPanda : string.equals("DeviceNotRegistered") ? RegisterDeviceErrorType.RegisterDeviceErrorTypeDeviceNotRegisteredPanda : string.equals("Unauthorized") ? RegisterDeviceErrorType.RegisterDeviceErrorTypeUnauthorizedPanda : RegisterDeviceErrorType.RegisterDeviceErrorTypeUnrecognizedPanda);
    }

    @Override // com.amazon.identity.auth.device.ic
    public kc a(PandaError pandaError) {
        RegisterDeviceErrorType registerDeviceErrorType;
        int iOrdinal = pandaError.ordinal();
        if (iOrdinal == 0) {
            registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeMissingValue;
        } else if (iOrdinal == 1) {
            registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeInvalidValue;
        } else if (iOrdinal == 2) {
            registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeCustomerNotFound;
        } else if (iOrdinal == 3) {
            registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeServerError;
        } else if (iOrdinal == 4) {
            registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeServerUnavailable;
        } else if (iOrdinal != 5) {
            registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeUnrecognizedPanda;
        } else {
            registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeForbidden;
        }
        return a(registerDeviceErrorType);
    }

    public final kc a(RegisterDeviceErrorType registerDeviceErrorType) {
        return new kc(null, null, null, 0, null, null, null, null, null, null, null, null, new jc(registerDeviceErrorType));
    }
}
