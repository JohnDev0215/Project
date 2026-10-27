package com.amazon.identity.auth.device;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.accounts.MultipleAccountsCommunication;
import com.amazon.identity.auth.device.actor.ActorManagerCommunication;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.amazon.identity.auth.device.api.MAPActorManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.amazon.identity.auth.device.metrics.SSOMetrics;
import com.amazon.identity.auth.device.token.CentralTokenManagementCommunication;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.platform.setting.PlatformSettings;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class e {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0015  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x017a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.content.Context r12, android.net.Uri r13, java.lang.String r14) {
        /*
            Method dump skipped, instruction units count: 457
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.e.a(android.content.Context, android.net.Uri, java.lang.String):void");
    }

    public static f b(Context context) {
        return t9.c(context) ? (CentralAccountManagerCommunication) context.getSystemService("sso_map_account_manager_communicator") : g.b(context);
    }

    public static t1 c(Context context) {
        if (!qe.a(context, n1.e) || !t9.c(context)) {
            t5 t5VarA = t5.a(context);
            return new u1(t5VarA, t5VarA.a());
        }
        if (!qe.j(context)) {
            return new m1(t5.a(context));
        }
        t5 t5VarA2 = t5.a(context);
        return new n1(t5VarA2, (i5) t5VarA2.getSystemService("sso_platform"), new s5(t5VarA2, t5VarA2.getContentResolver()), false);
    }

    public static w0 d(Context context) {
        return qe.j(context) ? new MultipleAccountsCommunication(context) : z0.a(context);
    }

    public static e9 e(Context context) {
        if (qe.j(context) && !t9.f(context)) {
            ga.a("TokenManagementImplementationFactory");
            return new CentralTokenManagementCommunication(context);
        }
        if (qe.i(context) || !qe.k(context)) {
            ga.a("TokenManagementImplementationFactory");
            return h9.a(context);
        }
        ga.a("TokenManagementImplementationFactory");
        return new q8(context);
    }

    public static String f(String str) throws NoSuchAlgorithmException {
        byte[] bArrE = e(str);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bArrE);
        byte[] bArrDigest = messageDigest.digest();
        StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
        for (byte b : bArrDigest) {
            sb.append(String.format("%02X", Integer.valueOf(b & 255)));
        }
        return sb.toString();
    }

    public static boolean g(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean h(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean i(String str) {
        return str != null && str.length() == 0;
    }

    public static Integer j(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static Long k(String str) {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static boolean g(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void h(Context context) {
        h2 h2Var = h2.c;
        synchronized (h2Var) {
            h2Var.f358a.clear();
        }
        z0.h = new z0(context.getApplicationContext());
        MAPApplicationInformationQueryer.a(context).d();
        k3.j = new k3(context.getApplicationContext(), qe.a(context, o3.d));
        q3.e = new q3(context.getApplicationContext());
        t3.c = new t3(context);
        x4.f656a = t3.b(context).f562a;
        r3.a().b();
        s1.j = new s1(context.getApplicationContext());
        g.a(context);
        e2.e = new e2(t5.a(context));
        h9.l = new h9(context.getApplicationContext());
        CustomerAttributeStore.generateNewInstance(context);
        DeviceDataStore.generateNewInstance(context);
    }

    public static Map<String, String> d(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        HashMap map = new HashMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof String) {
                map.put(str, (String) obj);
            }
        }
        return map;
    }

    public static String b(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("public_code", str);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("code_pair", jSONObject);
        jSONObject2.put("access_token", str2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("auth_data", jSONObject2);
        return jSONObject3.toString();
    }

    public static byte[] e(String str) {
        try {
            return str.getBytes(ParserUtil.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String b(String str) {
        try {
            return c(str) + ":" + new URL(str).getHost();
        } catch (MalformedURLException unused) {
            return "CannotGetURL";
        }
    }

    public static String c(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("[");
        for (String str : bundle.keySet()) {
            sb.append(str);
            sb.append("=");
            sb.append(bundle.get(str));
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static String d(String str) {
        return str == null ? "CannotGetURL" : str.contains("getNewDeviceCredentials") ? "getNewDeviceCredentials" : str.contains("registerAssociatedDevice") ? "registerAssociatedDevice" : str.contains("registerDeviceWithToken") ? "registerDeviceWithToken" : str.contains("registerDeviceToSecondaryCustomer") ? "registerDeviceToSecondaryCustomer" : str.contains("registerDevice") ? "registerDevice" : str.contains("renameFiona") ? "renameFiona" : str.contains("disownFiona") ? "disownFiona" : str.contains("/auth/register") ? "/auth/register" : str.contains("/auth/signin") ? "/auth/signin" : str.contains("/ap/exchangetoken") ? "/ap/exchangetoken" : "NonMAPURL";
    }

    public static boolean f(Context context) {
        try {
            return g(context);
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static boolean b(fa faVar) {
        return "com.amazon.dcp.sso.property.deviceemail".equals(faVar.c) || "com.amazon.dcp.sso.property.devicename".equals(faVar.c) || "com.amazon.dcp.sso.property.username".equals(faVar.c) || "com.amazon.dcp.sso.property.firstname".equals(faVar.c) || "com.amazon.dcp.sso.token.devicedevicetype".equals(faVar.c) || "com.amazon.dcp.sso.token.device.deviceserialname".equals(faVar.c) || "com.amazon.dcp.sso.token.device.accountpool".equals(faVar.c) || "com.amazon.dcp.sso.property.account.delegateeaccount".equals(faVar.c) || CustomerAttributeKeys.KEY_IS_ANONYMOUS.equals(faVar.c) || AccountConstants.KEY_ACCOUNT_UUID.equals(faVar.c) || AccountConstants.KEY_SECONDARY_AMAZON_ACCOUNT.equals(faVar.c) || AccountConstants.KEY_DEVICE_ACCOUNT_ROLE.equals(faVar.c) || faVar.c.startsWith("com.amazon.dcp.sso.property.account.extratokens");
    }

    public static String c(String str) {
        return str == null ? "CannotGetURL" : str.contains("/ap/signin") ? "/ap/signin" : str.contains("/ap/forgotpassword") ? "/ap/forgotpassword" : str.contains("/ap/register") ? "/ap/register" : str.contains("/ap/challenge") ? "/ap/challenge" : str.contains("/ap/mfa") ? "/ap/mfa" : str.contains("/ap/dcq") ? "/ap/dcq" : "NonMAPURL";
    }

    public static String c(String str, String str2) {
        String str3 = str + " " + str2;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str3.getBytes(Charset.forName(ParserUtil.UTF_8)));
            String strSubstring = Base64.encodeToString(Arrays.copyOfRange(messageDigest.digest(), 0, 9), 3).substring(0, 11);
            String.format("pkg: %s -- hash: %s", str, strSubstring);
            ga.a("AppSmsSignatureHelper");
            return strSubstring;
        } catch (NoSuchAlgorithmException e) {
            Log.e(ga.a("AppSmsSignatureHelper"), "hash:NoSuchAlgorithm", e);
            return null;
        }
    }

    public static Element b(Element element, String str) {
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem.getNodeType() == 1) {
                Element element2 = (Element) nodeItem;
                if (str.equalsIgnoreCase(element2.getTagName())) {
                    return element2;
                }
            }
        }
        return null;
    }

    public static String d(URL url) {
        return url.getPath() + ":" + url.getHost();
    }

    public static void b(Bundle bundle) {
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof Enum) {
                bundle.putString(str, obj.toString());
            }
        }
    }

    public static boolean c(Element element, String str) {
        return element.getElementsByTagName(str).getLength() > 0;
    }

    public static String c(URL url) {
        return url.getPath() + ":" + url.getHost() + ":IOException";
    }

    public static String b(URL url) {
        return d(url) + ":Availability";
    }

    public static void b(Context context, Intent intent, String str, m4 m4Var) {
        if (m4Var != null) {
            try {
                if (PlatformSettings.a(context).a("ordered.broadcast", true).booleanValue()) {
                    intent.getAction();
                    ga.a("SecurityHelpers");
                    context.sendOrderedBroadcastAsUser(intent, m4Var.f428a, str, null, null, -1, null, null);
                } else {
                    intent.getAction();
                    ga.a("SecurityHelpers");
                    try {
                        context.sendBroadcastAsUser(intent, m4Var.f428a, str);
                    } catch (Exception e) {
                        e = e;
                        yd.b("MAPBroadcastException:" + intent.getAction(), new String[0]);
                        Log.e(ga.a("SecurityHelpers"), "Fail to send the broadcast. " + intent.getAction(), e);
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
        } else {
            try {
                if (PlatformSettings.a(context).a("ordered.broadcast", true).booleanValue()) {
                    intent.getAction();
                    ga.a("SecurityHelpers");
                    context.sendOrderedBroadcast(intent, str, null, null, -1, null, null);
                } else {
                    intent.getAction();
                    ga.a("SecurityHelpers");
                    context.sendBroadcast(intent, str);
                }
            } catch (Exception e3) {
                yd.b("MAPBroadcastException:" + intent.getAction(), new String[0]);
                Log.e(ga.a("SecurityHelpers"), "Fail to send the broadcast. " + intent.getAction(), e3);
            }
        }
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(String.format("%s cannot be null", str));
        }
    }

    public static f2 a(t5 t5Var) {
        if (qe.j(t5Var)) {
            return new ActorManagerCommunication(t5Var, new z4(t5Var, "result_code", MAPActorManager.KEY_ERROR_MESSAGE, 4));
        }
        return e2.a(t5Var);
    }

    public static String a(Locale locale) {
        return locale.toString().replace(Attributes.PREDEFINED_ATTRIBUTE_PREFIX, "-");
    }

    public static Bundle a(Bundle bundle) {
        return bundle == null ? new Bundle() : bundle;
    }

    public static Element a(Element element, String str) {
        if (element == null) {
            return null;
        }
        NodeList elementsByTagName = element.getElementsByTagName(str);
        if (elementsByTagName.getLength() == 0) {
            return null;
        }
        return (Element) elementsByTagName.item(0);
    }

    public static int a(Integer num, Integer num2) {
        if (num == null) {
            return num2 != null ? -1 : 0;
        }
        if (num2 == null) {
            return 1;
        }
        return Integer.signum(num.intValue() - num2.intValue());
    }

    public static Intent a(String str) {
        Intent intent = new Intent();
        intent.setFlags(32);
        intent.setAction(str);
        return intent;
    }

    public static String a() {
        return Locale.getDefault().toString();
    }

    public static b3 a(JSONObject jSONObject) {
        String str;
        String string;
        String string2;
        String string3 = null;
        try {
            string2 = jSONObject.getString("error");
            try {
                string = jSONObject.getString("error_description");
            } catch (JSONException unused) {
                str = null;
                string = null;
            }
        } catch (JSONException unused2) {
            str = null;
            string = null;
        }
        try {
            string3 = jSONObject.getString("error_index");
            return new b3(string2, string, string3);
        } catch (JSONException unused3) {
            str = string3;
            string3 = string2;
            return new b3(string3, string, str);
        }
    }

    public static <T> Set<T> a(T... tArr) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(tArr));
        return hashSet;
    }

    public static String a(String str, Signature signature) throws GeneralSecurityException {
        if (signature == null) {
            return null;
        }
        byte[] bArrDigest = MessageDigest.getInstance(str).digest(signature.toByteArray());
        if (bArrDigest == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArrDigest) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    @Deprecated
    public static void a(Callback callback, MAPError mAPError, String str, int i, String str2) {
        if (callback == null) {
            return;
        }
        callback.onError(a(mAPError, str, i, str2));
    }

    public static URLConnection a(URL url) throws IOException {
        if (url != null) {
            URLConnection uRLConnection = (URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection());
            Map<String, String> map = EnvironmentUtils.f306a;
            if (uRLConnection instanceof HttpsURLConnection) {
                ((HttpsURLConnection) uRLConnection).setSSLSocketFactory(k6.f406a);
            }
            return uRLConnection;
        }
        throw new IllegalArgumentException("Null url is not allowed");
    }

    public static HttpURLConnection a(URL url, RetryLogic retryLogic, y5 y5Var, Context context) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("Must Specify a URL");
        }
        if (retryLogic != null) {
            URLConnection uRLConnectionA = a(url);
            if (uRLConnectionA instanceof HttpsURLConnection) {
                return new m5(url, retryLogic, y5Var, context);
            }
            if (uRLConnectionA instanceof HttpURLConnection) {
                return new l5(new a5(url), retryLogic, y5Var, context);
            }
            throw new IllegalArgumentException("Url must be an Https or Http Url");
        }
        throw new IllegalArgumentException("Must Specify Retry Logic");
    }

    public static String a(String str, Collection<String> collection) {
        if (collection == null) {
            return null;
        }
        return TextUtils.join(str, collection);
    }

    @Deprecated
    public static void a(s2 s2Var, int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("error_code_key", i);
        bundle.putString("error_message_key", str);
        s2Var.onError(bundle);
    }

    public static void a(Context context) {
        z5 z5Var = new z5(context, false);
        z5Var.d.getPackageManager().getNameForUid(Binder.getCallingUid());
        if (z5Var.a(Binder.getCallingUid())) {
            return;
        }
        Log.e(ga.a("SecurityHelpers"), "Unauthorized caller, the caller does not have permission or is signed with a different cert. Please check your manifest file and signature.");
        throw new SecurityException();
    }

    @Deprecated
    public static Bundle a(MAPError mAPError, String str, int i, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt("error_code_key", i);
        bundle.putString("error_message_key", str2);
        bundle.putInt(MAPError.KEY_ERROR_CODE, mAPError.getErrorCode());
        bundle.putString(MAPError.KEY_ERROR_MESSAGE, str);
        bundle.putString(MAPError.KEY_ERROR_TYPE, mAPError.getErrorType());
        return bundle;
    }

    public static String a(PackageInfo packageInfo) {
        ArrayList arrayList = new ArrayList();
        try {
            for (Signature signature : packageInfo.signatures) {
                String strC = c(packageInfo.packageName, signature.toCharsString());
                if (strC != null) {
                    arrayList.add(String.format("%s", strC));
                }
            }
        } catch (Exception e) {
            Log.e(ga.a("AppSmsSignatureHelper"), "Unable to calculate hash.", e);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (String) arrayList.get(0);
    }

    public static boolean a(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean a(fa faVar) {
        String str = faVar.c;
        return CustomerAttributeKeys.KEY_COR.equals(str) || CustomerAttributeKeys.KEY_PFM.equals(str);
    }

    public static void a(Activity activity) {
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity == null) {
            String.format("Android System called activity %s in package %s", activity.getClass().getName(), activity.getPackageName());
            ga.a("SecurityHelpers");
        } else {
            a((Context) activity, callingActivity.getPackageName());
        }
    }

    public static void a(Context context, String str) {
        z5 z5Var = new z5(context, false);
        if (((m6) z5.g).a(z5Var.d, str, true)) {
            return;
        }
        Log.e(ga.a("SecurityHelpers"), String.format("Package is an unauthorized caller", new Object[0]));
        throw new SecurityException();
    }

    public static String a(String str, String str2) {
        return str == null ? str2 : String.format("%s/%s", str, str2);
    }

    public static String a(Context context, String str, String str2) {
        if (z9.a(context, str)) {
            return str2;
        }
        return str + "/" + str2;
    }

    public static String a(Element element) {
        if (element == null) {
            return null;
        }
        return element.getTextContent();
    }

    public static String a(URL url, int i) {
        return d(url) + ":" + i;
    }

    public static String a(URL url, int i, String str) {
        if (str == null) {
            return a(url, i);
        }
        return a(url, i) + ":" + str;
    }

    public static String a(URL url, IOException iOException, Context context) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(c(url));
        sb.append(":");
        sb.append(iOException.getClass().getSimpleName());
        sb.append(":");
        try {
            str = g(context) ? "Connected" : "NotConnected";
        } catch (SecurityException e) {
            Log.e(ga.a(SSOMetrics.f439a), "We do not have permission to get the network state. Please make sure the permission android.permission.ACCESS_NETWORK_STATE is included in your manifest", e);
            str = "NetworkStatusUnknown";
        }
        sb.append(str);
        return sb.toString();
    }

    public static void a(Context context, Intent intent, String str, m4 m4Var) {
        try {
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(ga.a("SecurityHelpers"), "Cannot calculate whether current app is a system app or not", e);
        }
        boolean z = (context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 1) != 0;
        if (!z || !qe.f(context)) {
            if (!((intent == null || (intent.getPackage() == null && intent.getComponent() == null)) ? false : true)) {
                if (m4Var == null) {
                    Iterator it = ((ArrayList) MAPApplicationInformationQueryer.a(context).a()).iterator();
                    while (it.hasNext()) {
                        String str2 = ((j5) it.next()).b;
                        Intent intent2 = new Intent(intent);
                        intent2.setPackage(str2);
                        String.format("On 3P devices, %s sends broadcast %s", context.getPackageName(), intent2.toString());
                        ga.a("SecurityHelpers");
                        b(context, intent2, str, null);
                    }
                    return;
                }
                throw new IllegalStateException("We can only fire a broadcast to a user if we are a system app");
            }
        }
        ga.a("SecurityHelpers");
        b(context, intent, str, m4Var);
    }
}
