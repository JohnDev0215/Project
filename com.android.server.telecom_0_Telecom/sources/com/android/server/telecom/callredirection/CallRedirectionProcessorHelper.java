package com.android.server.telecom.callredirection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.PersistableBundle;
import android.telecom.GatewayInfo;
import android.telecom.Log;
import android.telecom.PhoneAccountHandle;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.internal.annotations.VisibleForTesting;
import com.android.server.telecom.CallsManager;
import com.android.server.telecom.PhoneAccountRegistrar;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CallRedirectionProcessorHelper {
    private final CallsManager mCallsManager;
    private final Context mContext;
    private final PhoneAccountRegistrar mPhoneAccountRegistrar;

    public CallRedirectionProcessorHelper(Context context, CallsManager callsManager, PhoneAccountRegistrar phoneAccountRegistrar) {
        this.mContext = context;
        this.mCallsManager = callsManager;
        this.mPhoneAccountRegistrar = phoneAccountRegistrar;
    }

    @VisibleForTesting
    public ComponentName getUserDefinedCallRedirectionService() {
        String defaultCallRedirectionApp = this.mCallsManager.getRoleManagerAdapter().getDefaultCallRedirectionApp();
        if (TextUtils.isEmpty(defaultCallRedirectionApp)) {
            Log.i(this, "PackageName is empty. Not performing user-defined call redirection.", new Object[0]);
            return null;
        }
        return getComponentName(new Intent("android.telecom.CallRedirectionService").setPackage(defaultCallRedirectionApp), "user_defined");
    }

    @VisibleForTesting
    public ComponentName getCarrierCallRedirectionService(PhoneAccountHandle phoneAccountHandle) {
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) this.mContext.getSystemService("carrier_config");
        if (carrierConfigManager == null) {
            Log.i(this, "Cannot get CarrierConfigManager.", new Object[0]);
            return null;
        }
        PersistableBundle configForSubId = carrierConfigManager.getConfigForSubId(this.mPhoneAccountRegistrar.getSubscriptionIdForPhoneAccount(phoneAccountHandle));
        if (configForSubId == null) {
            Log.i(this, "Cannot get PersistableBundle.", new Object[0]);
            return null;
        }
        String string = configForSubId.getString("call_redirection_service_component_name_string");
        if (string == null) {
            Log.i(this, "Cannot get carrier componentNameString.", new Object[0]);
            return null;
        }
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(string);
        if (componentNameUnflattenFromString == null) {
            Log.w(this, "ComponentName is null from string: " + string, new Object[0]);
            return null;
        }
        Intent intent = new Intent("android.telecom.CallRedirectionService");
        intent.setComponent(componentNameUnflattenFromString);
        return getComponentName(intent, "carrier");
    }

    protected ComponentName getComponentName(Intent intent, String str) {
        List listQueryIntentServicesAsUser = this.mContext.getPackageManager().queryIntentServicesAsUser(intent, 0, this.mCallsManager.getCurrentUserHandle().getIdentifier());
        if (listQueryIntentServicesAsUser.isEmpty()) {
            Log.i(this, "There are no " + str + " call redirection services installed on this device.", new Object[0]);
            return null;
        }
        if (listQueryIntentServicesAsUser.size() != 1) {
            Log.i(this, "There are multiple " + str + " call redirection services installed on this device.", new Object[0]);
            return null;
        }
        ResolveInfo resolveInfo = (ResolveInfo) listQueryIntentServicesAsUser.get(0);
        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
        if (serviceInfo == null) {
            Log.w(this, "The " + str + " call redirection service has invalid service info", new Object[0]);
            return null;
        }
        String str2 = serviceInfo.permission;
        if (str2 == null || !str2.equals("android.permission.BIND_CALL_REDIRECTION_SERVICE")) {
            Log.w(this, "CallRedirectionService must require BIND_CALL_REDIRECTION_SERVICE permission: " + resolveInfo.serviceInfo.packageName, new Object[0]);
            return null;
        }
        ServiceInfo serviceInfo2 = resolveInfo.serviceInfo;
        return new ComponentName(serviceInfo2.packageName, serviceInfo2.name);
    }

    protected Uri formatNumberForRedirection(Uri uri) {
        return removePostDialDigits(formatNumberToE164(uri));
    }

    public String getPostDialDigits(Uri uri) {
        return uri == null ? "" : PhoneNumberUtils.extractPostDialPortion(uri.getSchemeSpecificPart());
    }

    protected Uri formatNumberToE164(Uri uri) {
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        Log.i(this, "formatNumberToE164, original number: " + Log.pii(schemeSpecificPart), new Object[0]);
        String numberToE164 = PhoneNumberUtils.formatNumberToE164(schemeSpecificPart, telephonyManager.getNetworkCountryIso());
        Log.i(this, "formatNumberToE164, formatted E164 number: " + Log.pii(numberToE164), new Object[0]);
        return numberToE164 == null ? uri : Uri.fromParts(uri.getScheme(), numberToE164, null);
    }

    protected Uri removePostDialDigits(Uri uri) {
        String strExtractNetworkPortion = PhoneNumberUtils.extractNetworkPortion(uri.getSchemeSpecificPart());
        Log.i(this, "removePostDialDigits, number after being extracted post dial digits: " + Log.pii(strExtractNetworkPortion), new Object[0]);
        return strExtractNetworkPortion == null ? uri : Uri.fromParts(uri.getScheme(), strExtractNetworkPortion, null);
    }

    public static GatewayInfo getGatewayInfoFromGatewayUri(String str, Uri uri, Uri uri2, String str2) {
        if (TextUtils.isEmpty(str) || uri == null) {
            return null;
        }
        if (uri != null && !TextUtils.isEmpty(str2)) {
            uri = new Uri.Builder().scheme(uri.getScheme()).encodedOpaquePart(uri.getSchemeSpecificPart() + str2).build();
        }
        return new GatewayInfo(str, uri, uri2);
    }
}
