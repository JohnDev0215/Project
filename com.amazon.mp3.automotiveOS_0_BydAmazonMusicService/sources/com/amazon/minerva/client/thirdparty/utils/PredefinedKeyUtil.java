package com.amazon.minerva.client.thirdparty.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.amazon.minerva.client.thirdparty.api.Predefined;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.amazon.minerva.identifiers.schemaid.attribute.attributes.AttributeEnumV2;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class PredefinedKeyUtil {
    private static final String TAG = "PredefinedKeyUtil";
    private String mAnonymousCustomerId;
    private String mAnonymousDeviceId;
    protected Context mContext;
    private CustomDeviceUtil mCustomDeviceUtil;
    private final SharedPreferences sharedPreferences;

    public String getOsFileTag() {
        return "UNSUPPORTED";
    }

    public PredefinedKeyUtil(Context context, CustomDeviceUtil customDeviceUtil) {
        if (context == null) {
            throw new NullPointerException(String.format("%s: context must not be null.", TAG));
        }
        this.mContext = context;
        this.sharedPreferences = context.getSharedPreferences("minerva-device-util", 0);
        this.mCustomDeviceUtil = customDeviceUtil;
    }

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.utils.PredefinedKeyUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined;

        static {
            int[] iArr = new int[Predefined.values().length];
            $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined = iArr;
            try {
                iArr[Predefined.SOFTWARE_VERSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.SOFTWARE_VERSION_FINGERPRINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.OS_FILE_TAG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.BUILD_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.PLATFORM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.MODEL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.HARDWARE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.DEVICE_TYPE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.DEVICE_ID.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.CUSTOMER_ID.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.MARKETPLACE_ID.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.COUNTRY_OF_RESIDENCE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.DEVICE_LANGUAGE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.OTA_GROUP_NAME.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[Predefined.TIME_ZONE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    public String getPredefinedValue(Predefined predefined, String str) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$minerva$client$thirdparty$api$Predefined[predefined.ordinal()]) {
            case 1:
                return getSoftwareVersion();
            case 2:
                return getSoftwareVersionFingerprint();
            case 3:
                return getOsFileTag();
            case 4:
                return getBuildType();
            case 5:
                return getPlatform();
            case 6:
                return getModel();
            case 7:
                return getHardware();
            case 8:
                return getDeviceType();
            case 9:
                return getDeviceId(str);
            case 10:
                return getCustomerId(str);
            case 11:
                return getMarketPlaceId();
            case 12:
                return getCountryOfResidence();
            case 13:
                return getDeviceLanguage();
            case 14:
                return getOtaGroupName();
            case 15:
                return getTimeZone();
            default:
                return "UNKNOWN";
        }
    }

    public String getSoftwareVersion() {
        return Build.VERSION.INCREMENTAL;
    }

    public String getSoftwareVersionFingerprint() {
        return Build.FINGERPRINT;
    }

    public String getBuildType() {
        return Build.TYPE;
    }

    public String getPlatform() {
        return Build.DEVICE;
    }

    public String getModel() {
        return Build.MODEL;
    }

    public String getHardware() {
        return Build.HARDWARE;
    }

    public String getCountryOfResidence() {
        return Locale.getDefault().getCountry();
    }

    public String getDeviceLanguage() {
        String language = Locale.getDefault().getLanguage();
        return TextUtils.isEmpty(language) ? "UNKNOWN" : language;
    }

    public String getOtaGroupName() {
        Log.e(TAG, "OTA Group Name is only exist on FOS device, this concept doesn' exist on Android 3p device, you shouldn't use this method");
        return null;
    }

    public String getTimeZone() {
        return TimeZone.getDefault().getID();
    }

    public String getDeviceType() {
        String deviceType = this.mCustomDeviceUtil.getDeviceType();
        return deviceType == null ? "UNKNOWN" : deviceType;
    }

    public String getMarketPlaceId() {
        Log.e(TAG, "MarketPlaceId is unsupported as predefined key on Android 3p device, if you want to inject it as meta data to your metric event, please use: metricEvent.addString(\"MarketPlaceId\", \"your MarketPlaceId value\"); ");
        return "UNKNOWN";
    }

    public String getDeviceId(String str) {
        if (new SchemaId(str).getVersionedAttributes().getBooleanValue(AttributeEnumV2.ALLOW_DSN_INFO).booleanValue()) {
            return getNonAnonymousDeviceId();
        }
        return getAnonymousDeviceId();
    }

    public String getCustomerId(String str) {
        if (new SchemaId(str).getVersionedAttributes().getBooleanValue(AttributeEnumV2.ALLOW_CUSTOMER_INFO).booleanValue()) {
            return getNonAnonymousCustomerId();
        }
        return getAnonymousCustomerId();
    }

    private String getNonAnonymousDeviceId() {
        try {
            String nonAnonymousDeviceId = this.mCustomDeviceUtil.getNonAnonymousDeviceId();
            if (!nonAnonymousDeviceId.isEmpty()) {
                return nonAnonymousDeviceId;
            }
            Log.i(TAG, "Non_anonymous deviceId passed by customer is empty, replace to UNKNOWN as it's value");
            return "UNKNOWN";
        } catch (Exception e) {
            Log.e(TAG, "Unable to get non_anonymous deviceId passed by customer from API: withNonAnonymousDeviceId due to exception: " + e);
            return null;
        }
    }

    private synchronized String getAnonymousDeviceId() {
        if (!TextUtils.isEmpty(this.mAnonymousDeviceId)) {
            return this.mAnonymousDeviceId;
        }
        String string = this.sharedPreferences.getString("AnonymousDeviceId", null);
        this.mAnonymousDeviceId = string;
        if (!TextUtils.isEmpty(string)) {
            return this.mAnonymousDeviceId;
        }
        Log.i(TAG, "Generating an anonymous device ID.");
        String string2 = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
        if (string2 != null && string2.length() > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(string2.getBytes(StandardCharsets.UTF_8));
                this.mAnonymousDeviceId = "minerva." + Base64.encodeToString(messageDigest.digest(), 2);
            } catch (NoSuchAlgorithmException e) {
                Log.e(TAG, "Failed to generate anonymous device ID, and falling back to UUID.", e);
                this.mAnonymousDeviceId = "minerva." + UUID.randomUUID().toString();
            }
        }
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putString("AnonymousDeviceId", this.mAnonymousDeviceId);
        editorEdit.apply();
        return this.mAnonymousDeviceId;
    }

    private String getAnonymousCustomerId() {
        if (!TextUtils.isEmpty(this.mAnonymousCustomerId)) {
            return this.mAnonymousCustomerId;
        }
        String string = this.sharedPreferences.getString("AnonymousCustomerId", null);
        this.mAnonymousCustomerId = string;
        if (!TextUtils.isEmpty(string)) {
            return this.mAnonymousCustomerId;
        }
        Log.i(TAG, "Generating an anonymous customer ID.");
        this.mAnonymousCustomerId = "minerva." + String.format(Locale.US, "%09d", Integer.valueOf(new SecureRandom().nextInt(((int) Math.pow(10.0d, 9.0d)) - 1)));
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putString("AnonymousCustomerId", this.mAnonymousCustomerId);
        editorEdit.apply();
        return this.mAnonymousCustomerId;
    }

    private String getNonAnonymousCustomerId() {
        try {
            if (this.mCustomDeviceUtil.getNonAnonymousCustomerIdProvider() != null && this.mCustomDeviceUtil.getNonAnonymousCustomerIdProvider().getNonAnonymousCustomerId() != null) {
                String nonAnonymousCustomerId = this.mCustomDeviceUtil.getNonAnonymousCustomerIdProvider().getNonAnonymousCustomerId();
                if (!nonAnonymousCustomerId.isEmpty()) {
                    return nonAnonymousCustomerId;
                }
                Log.e(TAG, "nonAnonymousCustomerId passed by customer is empty, set UNKNOWN as value for non_anonymous customerId");
                return "UNKNOWN";
            }
            Log.e(TAG, "nonAnonymousCustomerId is null, customer might miss to provide API: withNonAnonymousCustomerIdProvider");
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Unable to retrieve non_anonymous customerId passed by customer from API: withNonAnonymousCustomerIdProvider due to exception: " + e);
            return null;
        }
    }
}
