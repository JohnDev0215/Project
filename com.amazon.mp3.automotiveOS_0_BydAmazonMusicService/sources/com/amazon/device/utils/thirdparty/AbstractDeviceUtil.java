package com.amazon.device.utils.thirdparty;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.util.Log;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractDeviceUtil extends ContentObserver implements DeviceUtil {
    private static String sCustomerID;
    private static String sSessionID;
    private String mRemoteIP;
    protected final SharedPreferences mSharedPrefs;

    public AbstractDeviceUtil(Context context) {
        super(null);
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null");
        }
        this.mSharedPrefs = context.getSharedPreferences("com.amazon.device.utils", 0);
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchRemoteIP() {
        if (this.mRemoteIP == null) {
            createRemoteIp();
        }
        return this.mRemoteIP;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchSessionID() {
        if (sSessionID == null) {
            initializeSessionID(this.mSharedPrefs);
        }
        return sSessionID;
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String fetchCustomerID() {
        if (sCustomerID == null) {
            initializeCustomerID(this.mSharedPrefs);
        }
        return sCustomerID;
    }

    private void createRemoteIp() {
        this.mRemoteIP = "10.0.0.1";
    }

    private static synchronized void initializeCustomerID(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString("clickstreamCustomerID", null);
        sCustomerID = string;
        if (string != null) {
            return;
        }
        sCustomerID = getRandomDigitsUtil(9, new Random());
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString("clickstreamCustomerID", sCustomerID);
        editorEdit.apply();
        Log.d("AbstractDeviceUtil", "Generated a new CustomerId " + sCustomerID);
    }

    private static synchronized void initializeSessionID(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString("clickstreamSessionID", null);
        sSessionID = string;
        if (string != null) {
            return;
        }
        Random random = new Random();
        sSessionID = String.format(Locale.US, "%s-%s-%s", getRandomDigitsUtil(3, random), getRandomDigitsUtil(7, random), getRandomDigitsUtil(7, random));
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString("clickstreamSessionID", sSessionID);
        editorEdit.apply();
        Log.d("AbstractDeviceUtil", "Generated a new SessionID " + sSessionID);
    }

    @Override // com.amazon.device.utils.thirdparty.DeviceUtil
    public String getRandomDigits(int i, SecureRandom secureRandom) {
        return getRandomDigitsUtil(i, secureRandom);
    }

    private static String getRandomDigitsUtil(int i, Random random) {
        Locale locale = Locale.US;
        return String.format(locale, "%0" + String.format(locale, "%dd", Integer.valueOf(i)), Integer.valueOf(random.nextInt(((int) Math.pow(10.0d, i)) - 1)));
    }
}
