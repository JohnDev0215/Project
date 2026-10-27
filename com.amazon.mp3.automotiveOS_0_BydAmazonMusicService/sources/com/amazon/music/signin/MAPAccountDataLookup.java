package com.amazon.music.signin;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.music.authentication.AdpInfo;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.authentication.mapr5.AdpProviderMAPR5;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class MAPAccountDataLookup {
    private static final Logger LOG = LoggerFactory.getLogger(MAPAccountDataLookup.class.getSimpleName());
    private final Boolean isFireOS;

    public MAPAccountDataLookup(@NonNull Boolean bool) {
        this.isFireOS = bool;
    }

    protected MAPAccountDataResponse getAccountDataFromMAP(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull TokenManagement tokenManagement, @NonNull CustomerAttributeStore customerAttributeStore, @Nullable DeviceDataStore deviceDataStore) throws ExecutionException, MAPCallbackErrorException, InterruptedException, DeviceDataStoreException, TimeoutException, MAPEmptyReturnValueException, AuthenticationException {
        Validate.notNull(context, "Context cannot be null", new Object[0]);
        Validate.notNull(str, "AccountId cannot be null", new Object[0]);
        Validate.notNull(str2, "PackageName cannot be null", new Object[0]);
        Validate.notNull(tokenManagement, "TokenManagement cannot be null", new Object[0]);
        Validate.notNull(customerAttributeStore, "CustomerAttributeStore cannot be null", new Object[0]);
        AdpInfo adpInfo = new AdpProviderMAPR5(context).getAdpInfo();
        Logger logger = LOG;
        logger.info("DMMSignIn: getting adpToken");
        String str3 = adpInfo.adpToken;
        logger.info("DMMSignIn: got adpToken. Getting private key string.");
        String str4 = adpInfo.privateKeyString;
        logger.info("DMMSignIn: got private key string.");
        return new MAPAccountDataResponse(str, str3, str4, getAccountCustomerAttributeFromMAP(str, CustomerAttributeKeys.getUserNameKeyForPackage(str2), str2, customerAttributeStore), getAccountCustomerAttributeFromMAP(str, CustomerAttributeKeys.getDeviceTypeKeyForPackage(str2), str2, customerAttributeStore), this.isFireOS.booleanValue() ? "" : getAccountCustomerAttributeFromMAP(str, CustomerAttributeKeys.getDsnKeyForPackage(str2), str2, customerAttributeStore), deviceDataStore != null ? getAccountDeviceDataFromMAP("DeviceType", deviceDataStore) : null);
    }

    private String getAccountCustomerAttributeFromMAP(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull CustomerAttributeStore customerAttributeStore) throws ExecutionException, MAPCallbackErrorException, InterruptedException, DeviceDataStoreException, TimeoutException, MAPEmptyReturnValueException {
        Validate.notNull(str, "AccountId cannot be null", new Object[0]);
        Validate.notNull(str2, "AttributeName cannot be null", new Object[0]);
        Validate.notNull(customerAttributeStore, "CustomerAttributeStore cannot be null", new Object[0]);
        Bundle valueFromMAP = getValueFromMAP(customerAttributeStore.getAttribute(str, str2, null));
        String valueOrAttributeDefault = CustomerAttributeStore.getValueOrAttributeDefault(valueFromMAP);
        return TextUtils.isEmpty(valueOrAttributeDefault) ? handleEmptyAttributeAndRecordMetric(str2, str3, valueFromMAP) : valueOrAttributeDefault;
    }

    protected String handleEmptyAttributeAndRecordMetric(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle) throws MAPEmptyReturnValueException {
        if (str.equals(CustomerAttributeKeys.getUserNameKeyForPackage(str2))) {
            LOG.error("Got empty username. Proceeding with sign in.");
            return "";
        }
        LOG.error("Error when getting attribute from MAP customer attribute store: got empty value forattribute name : %s. Throwing MAPEmptyReturnValueException. ", str);
        throw new MAPEmptyReturnValueException(bundle);
    }

    private String getAccountDeviceDataFromMAP(@NonNull String str, @NonNull DeviceDataStore deviceDataStore) throws DeviceDataStoreException {
        return deviceDataStore.getValue(str);
    }

    protected Bundle getValueFromMAP(@NonNull MAPFuture<Bundle> mAPFuture) throws ExecutionException, MAPCallbackErrorException, InterruptedException, TimeoutException {
        Validate.notNull(mAPFuture, "Future cannot be null", new Object[0]);
        return mAPFuture.get(90L, TimeUnit.SECONDS);
    }
}
