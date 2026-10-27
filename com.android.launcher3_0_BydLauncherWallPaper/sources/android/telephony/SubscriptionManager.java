package android.telephony;

import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.app.PropertyInvalidatedCache;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.NetworkPolicyManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelUuid;
import android.os.Process;
import android.os.RemoteException;
import android.provider.Telephony;
import android.telephony.euicc.EuiccManager;
import android.util.Log;
import android.util.Pair;
import com.android.internal.telephony.ISetOpportunisticDataCallback;
import com.android.internal.telephony.ISub;
import com.android.internal.telephony.PhoneConstants;
import com.android.internal.telephony.util.HandlerExecutor;
import com.android.internal.util.FunctionalUtils;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
public class SubscriptionManager {
    public static final String ACCESS_RULES = "access_rules";
    public static final String ACCESS_RULES_FROM_CARRIER_CONFIGS = "access_rules_from_carrier_configs";
    public static final String ACTION_DEFAULT_SMS_SUBSCRIPTION_CHANGED = "android.telephony.action.DEFAULT_SMS_SUBSCRIPTION_CHANGED";
    public static final String ACTION_DEFAULT_SUBSCRIPTION_CHANGED = "android.telephony.action.DEFAULT_SUBSCRIPTION_CHANGED";
    public static final String ACTION_MANAGE_SUBSCRIPTION_PLANS = "android.telephony.action.MANAGE_SUBSCRIPTION_PLANS";
    public static final String ACTION_REFRESH_SUBSCRIPTION_PLANS = "android.telephony.action.REFRESH_SUBSCRIPTION_PLANS";

    @SystemApi
    public static final String ACTION_SUBSCRIPTION_PLANS_CHANGED = "android.telephony.action.SUBSCRIPTION_PLANS_CHANGED";

    @SystemApi
    public static final Uri ADVANCED_CALLING_ENABLED_CONTENT_URI;
    public static final String ALLOWED_NETWORK_TYPES = "allowed_network_types";
    public static final String CACHE_KEY_ACTIVE_DATA_SUB_ID_PROPERTY = "cache_key.telephony.get_active_data_sub_id";
    public static final String CACHE_KEY_DEFAULT_DATA_SUB_ID_PROPERTY = "cache_key.telephony.get_default_data_sub_id";
    public static final String CACHE_KEY_DEFAULT_SMS_SUB_ID_PROPERTY = "cache_key.telephony.get_default_sms_sub_id";
    public static final String CACHE_KEY_DEFAULT_SUB_ID_PROPERTY = "cache_key.telephony.get_default_sub_id";
    public static final String CACHE_KEY_SLOT_INDEX_PROPERTY = "cache_key.telephony.get_slot_index";
    public static final String CARD_ID = "card_id";
    public static final String CARRIER_ID = "carrier_id";
    public static final String CARRIER_NAME = "carrier_name";
    public static final String CB_ALERT_REMINDER_INTERVAL = "alert_reminder_interval";
    public static final String CB_ALERT_SOUND_DURATION = "alert_sound_duration";
    public static final String CB_ALERT_SPEECH = "enable_alert_speech";
    public static final String CB_ALERT_VIBRATE = "enable_alert_vibrate";
    public static final String CB_AMBER_ALERT = "enable_cmas_amber_alerts";
    public static final String CB_CHANNEL_50_ALERT = "enable_channel_50_alerts";
    public static final String CB_CMAS_TEST_ALERT = "enable_cmas_test_alerts";
    public static final String CB_EMERGENCY_ALERT = "enable_emergency_alerts";
    public static final String CB_ETWS_TEST_ALERT = "enable_etws_test_alerts";
    public static final String CB_EXTREME_THREAT_ALERT = "enable_cmas_extreme_threat_alerts";
    public static final String CB_OPT_OUT_DIALOG = "show_cmas_opt_out_dialog";
    public static final String CB_SEVERE_THREAT_ALERT = "enable_cmas_severe_threat_alerts";
    public static final Uri CONTENT_URI;
    public static final String DATA_ENABLED_OVERRIDE_RULES = "data_enabled_override_rules";
    public static final String DATA_ROAMING = "data_roaming";
    public static final int DATA_ROAMING_DISABLE = 0;
    public static final int DATA_ROAMING_ENABLE = 1;
    private static final boolean DBG = false;
    public static final int DEFAULT_NAME_RES = 17039374;
    public static final int DEFAULT_PHONE_INDEX = Integer.MAX_VALUE;
    public static final int DEFAULT_SIM_SLOT_INDEX = Integer.MAX_VALUE;
    public static final int DEFAULT_SUBSCRIPTION_ID = Integer.MAX_VALUE;
    public static final String DISPLAY_NAME = "display_name";
    public static final int DUMMY_SUBSCRIPTION_ID_BASE = -2;
    public static final String EHPLMNS = "ehplmns";
    public static final String ENHANCED_4G_MODE_ENABLED = "volte_vt_enabled";
    public static final String EXTRA_SLOT_INDEX = "android.telephony.extra.SLOT_INDEX";
    public static final String EXTRA_SUBSCRIPTION_INDEX = "android.telephony.extra.SUBSCRIPTION_INDEX";
    public static final String GROUP_OWNER = "group_owner";
    public static final String GROUP_UUID = "group_uuid";
    public static final String HPLMNS = "hplmns";
    public static final String HUE = "color";
    public static final String ICC_ID = "icc_id";
    public static final String IMSI = "imsi";
    public static final String IMS_RCS_UCE_ENABLED = "ims_rcs_uce_enabled";
    public static final int INVALID_PHONE_INDEX = -1;
    public static final int INVALID_SIM_SLOT_INDEX = -1;
    public static final int INVALID_SUBSCRIPTION_ID = -1;
    public static final String ISO_COUNTRY_CODE = "iso_country_code";
    public static final String IS_EMBEDDED = "is_embedded";
    public static final String IS_OPPORTUNISTIC = "is_opportunistic";
    public static final String IS_REMOVABLE = "is_removable";
    private static final String LOG_TAG = "SubscriptionManager";
    private static final int MAX_CACHE_SIZE = 4;
    public static final int MAX_SUBSCRIPTION_ID_VALUE = 2147483646;
    public static final String MCC = "mcc";
    public static final String MCC_STRING = "mcc_string";
    public static final int MIN_SUBSCRIPTION_ID_VALUE = 0;
    public static final String MNC = "mnc";
    public static final String MNC_STRING = "mnc_string";
    public static final String NAME_SOURCE = "name_source";
    public static final int NAME_SOURCE_CARRIER = 3;
    public static final int NAME_SOURCE_CARRIER_ID = 0;
    public static final int NAME_SOURCE_SIM_PNN = 4;
    public static final int NAME_SOURCE_SIM_SPN = 1;
    public static final int NAME_SOURCE_USER_INPUT = 2;
    public static final String NUMBER = "number";
    public static final String PROFILE_CLASS = "profile_class";

    @SystemApi
    @Deprecated
    public static final int PROFILE_CLASS_DEFAULT = -1;

    @SystemApi
    public static final int PROFILE_CLASS_OPERATIONAL = 2;

    @SystemApi
    public static final int PROFILE_CLASS_PROVISIONING = 1;

    @SystemApi
    public static final int PROFILE_CLASS_TESTING = 0;

    @SystemApi
    public static final int PROFILE_CLASS_UNSET = -1;
    public static final int SIM_NOT_INSERTED = -1;
    public static final String SIM_SLOT_INDEX = "sim_id";
    public static final int SLOT_INDEX_FOR_REMOTE_SIM_SUB = -1;
    public static final String SUBSCRIPTION_TYPE = "subscription_type";
    public static final int SUBSCRIPTION_TYPE_LOCAL_SIM = 0;
    public static final int SUBSCRIPTION_TYPE_REMOTE_SIM = 1;
    public static final String SUB_DEFAULT_CHANGED_ACTION = "android.intent.action.SUB_DEFAULT_CHANGED";
    public static final String UICC_APPLICATIONS_ENABLED = "uicc_applications_enabled";
    public static final String UNIQUE_KEY_SUBSCRIPTION_ID = "_id";
    private static final boolean VDBG = false;

    @SystemApi
    public static final Uri VT_ENABLED_CONTENT_URI;
    public static final String VT_IMS_ENABLED = "vt_ims_enabled";

    @SystemApi
    public static final Uri WFC_ENABLED_CONTENT_URI;
    public static final String WFC_IMS_ENABLED = "wfc_ims_enabled";
    public static final String WFC_IMS_MODE = "wfc_ims_mode";
    public static final String WFC_IMS_ROAMING_ENABLED = "wfc_ims_roaming_enabled";
    public static final String WFC_IMS_ROAMING_MODE = "wfc_ims_roaming_mode";

    @SystemApi
    public static final Uri WFC_MODE_CONTENT_URI;

    @SystemApi
    public static final Uri WFC_ROAMING_ENABLED_CONTENT_URI;

    @SystemApi
    public static final Uri WFC_ROAMING_MODE_CONTENT_URI;
    private static VoidPropertyInvalidatedCache<Integer> sActiveDataSubIdCache;
    private static VoidPropertyInvalidatedCache<Integer> sDefaultDataSubIdCache;
    private static VoidPropertyInvalidatedCache<Integer> sDefaultSmsSubIdCache;
    private static VoidPropertyInvalidatedCache<Integer> sDefaultSubIdCache;
    private static IntegerPropertyInvalidatedCache<Integer> sPhoneIdCache;
    private static final Map<Pair<Context, Integer>, Resources> sResourcesCache;
    private static IntegerPropertyInvalidatedCache<Integer> sSlotIndexCache;
    private final Context mContext;

    /* JADX INFO: Access modifiers changed from: private */
    interface CallISubMethodHelper {
        int callMethod(ISub iSub) throws RemoteException;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProfileClass {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SimDisplayNameSource {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SubscriptionType {
    }

    public static boolean isUsableSubIdValue(int i) {
        return i >= 0 && i <= 2147483646;
    }

    public static boolean isValidSubscriptionId(int i) {
        return i > -1;
    }

    static {
        Uri uri = Telephony.SimInfo.CONTENT_URI;
        CONTENT_URI = uri;
        sDefaultSubIdCache = new VoidPropertyInvalidatedCache<>(new FunctionalUtils.ThrowingFunction() { // from class: android.telephony.-$$Lambda$Yv-m7RGveW6fe55SlwzIMxb0o7g
            @Override // com.android.internal.util.FunctionalUtils.ThrowingFunction
            public final Object applyOrThrow(Object obj) {
                return Integer.valueOf(((ISub) obj).getDefaultSubId());
            }
        }, CACHE_KEY_DEFAULT_SUB_ID_PROPERTY, -1);
        sDefaultDataSubIdCache = new VoidPropertyInvalidatedCache<>(new FunctionalUtils.ThrowingFunction() { // from class: android.telephony.-$$Lambda$uMe0qpExs-9G9O-YUuXtaYzVQ9g
            @Override // com.android.internal.util.FunctionalUtils.ThrowingFunction
            public final Object applyOrThrow(Object obj) {
                return Integer.valueOf(((ISub) obj).getDefaultDataSubId());
            }
        }, CACHE_KEY_DEFAULT_DATA_SUB_ID_PROPERTY, -1);
        sDefaultSmsSubIdCache = new VoidPropertyInvalidatedCache<>(new FunctionalUtils.ThrowingFunction() { // from class: android.telephony.-$$Lambda$0X3yJo8kGVD6-zshUVKX7puMDeU
            @Override // com.android.internal.util.FunctionalUtils.ThrowingFunction
            public final Object applyOrThrow(Object obj) {
                return Integer.valueOf(((ISub) obj).getDefaultSmsSubId());
            }
        }, CACHE_KEY_DEFAULT_SMS_SUB_ID_PROPERTY, -1);
        sActiveDataSubIdCache = new VoidPropertyInvalidatedCache<>(new FunctionalUtils.ThrowingFunction() { // from class: android.telephony.-$$Lambda$EXdGsAsnITeO5BQbrk2RwPdtfbc
            @Override // com.android.internal.util.FunctionalUtils.ThrowingFunction
            public final Object applyOrThrow(Object obj) {
                return Integer.valueOf(((ISub) obj).getActiveDataSubscriptionId());
            }
        }, CACHE_KEY_ACTIVE_DATA_SUB_ID_PROPERTY, -1);
        sSlotIndexCache = new IntegerPropertyInvalidatedCache<>(new FunctionalUtils.ThrowingBiFunction() { // from class: android.telephony.-$$Lambda$aE7Wu7JzXFGZVMA99j_a85iFAfk
            @Override // com.android.internal.util.FunctionalUtils.ThrowingBiFunction
            public final Object applyOrThrow(Object obj, Object obj2) {
                return Integer.valueOf(((ISub) obj).getSlotIndex(((Integer) obj2).intValue()));
            }
        }, CACHE_KEY_SLOT_INDEX_PROPERTY, -1);
        sPhoneIdCache = new IntegerPropertyInvalidatedCache<>(new FunctionalUtils.ThrowingBiFunction() { // from class: android.telephony.-$$Lambda$nI532gOfoINNzVgsF5xxfG3W2Qg
            @Override // com.android.internal.util.FunctionalUtils.ThrowingBiFunction
            public final Object applyOrThrow(Object obj, Object obj2) {
                return Integer.valueOf(((ISub) obj).getPhoneId(((Integer) obj2).intValue()));
            }
        }, CACHE_KEY_DEFAULT_SUB_ID_PROPERTY, -1);
        WFC_ENABLED_CONTENT_URI = Uri.withAppendedPath(uri, "wfc");
        ADVANCED_CALLING_ENABLED_CONTENT_URI = Uri.withAppendedPath(uri, "advanced_calling");
        WFC_MODE_CONTENT_URI = Uri.withAppendedPath(uri, "wfc_mode");
        WFC_ROAMING_MODE_CONTENT_URI = Uri.withAppendedPath(uri, "wfc_roaming_mode");
        VT_ENABLED_CONTENT_URI = Uri.withAppendedPath(uri, "vt_enabled");
        WFC_ROAMING_ENABLED_CONTENT_URI = Uri.withAppendedPath(uri, "wfc_roaming_enabled");
        sResourcesCache = new ConcurrentHashMap();
    }

    private static class VoidPropertyInvalidatedCache<T> extends PropertyInvalidatedCache<Void, T> {
        private final String mCacheKeyProperty;
        private final T mDefaultValue;
        private final FunctionalUtils.ThrowingFunction<ISub, T> mInterfaceMethod;

        VoidPropertyInvalidatedCache(FunctionalUtils.ThrowingFunction<ISub, T> throwingFunction, String str, T t) {
            super(4, str);
            this.mInterfaceMethod = throwingFunction;
            this.mCacheKeyProperty = str;
            this.mDefaultValue = t;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.app.PropertyInvalidatedCache
        public T recompute(Void r3) {
            T t = this.mDefaultValue;
            try {
                ISub subscriptionService = TelephonyManager.getSubscriptionService();
                return subscriptionService != null ? this.mInterfaceMethod.applyOrThrow(subscriptionService) : t;
            } catch (Exception unused) {
                com.android.telephony.Rlog.w(SubscriptionManager.LOG_TAG, "Failed to recompute cache key for " + this.mCacheKeyProperty);
                return t;
            }
        }
    }

    private static class IntegerPropertyInvalidatedCache<T> extends PropertyInvalidatedCache<Integer, T> {
        private final String mCacheKeyProperty;
        private final T mDefaultValue;
        private final FunctionalUtils.ThrowingBiFunction<ISub, Integer, T> mInterfaceMethod;

        IntegerPropertyInvalidatedCache(FunctionalUtils.ThrowingBiFunction<ISub, Integer, T> throwingBiFunction, String str, T t) {
            super(4, str);
            this.mInterfaceMethod = throwingBiFunction;
            this.mCacheKeyProperty = str;
            this.mDefaultValue = t;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.app.PropertyInvalidatedCache
        public T recompute(Integer num) {
            T t = this.mDefaultValue;
            try {
                ISub subscriptionService = TelephonyManager.getSubscriptionService();
                return subscriptionService != null ? this.mInterfaceMethod.applyOrThrow(subscriptionService, num) : t;
            } catch (Exception unused) {
                com.android.telephony.Rlog.w(SubscriptionManager.LOG_TAG, "Failed to recompute cache key for " + this.mCacheKeyProperty);
                return t;
            }
        }
    }

    public static Uri getUriForSubscriptionId(int i) {
        return Uri.withAppendedPath(CONTENT_URI, String.valueOf(i));
    }

    public static class OnSubscriptionsChangedListener {
        private final HandlerExecutor mExecutor;

        public void onSubscriptionsChanged() {
        }

        private class OnSubscriptionsChangedListenerHandler extends Handler {
            OnSubscriptionsChangedListenerHandler() {
            }

            OnSubscriptionsChangedListenerHandler(Looper looper) {
                super(looper);
            }
        }

        public HandlerExecutor getHandlerExecutor() {
            return this.mExecutor;
        }

        public OnSubscriptionsChangedListener() {
            this.mExecutor = new HandlerExecutor(new OnSubscriptionsChangedListenerHandler());
        }

        public OnSubscriptionsChangedListener(Looper looper) {
            this.mExecutor = new HandlerExecutor(new OnSubscriptionsChangedListenerHandler(looper));
        }

        public void onAddListenerFailed() {
            com.android.telephony.Rlog.w(SubscriptionManager.LOG_TAG, "onAddListenerFailed not overridden");
        }

        private void log(String str) {
            com.android.telephony.Rlog.d(SubscriptionManager.LOG_TAG, str);
        }
    }

    public SubscriptionManager(Context context) {
        this.mContext = context;
    }

    private NetworkPolicyManager getNetworkPolicyManager() {
        return (NetworkPolicyManager) this.mContext.getSystemService(Context.NETWORK_POLICY_SERVICE);
    }

    @Deprecated
    public static SubscriptionManager from(Context context) {
        return (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
    }

    public void addOnSubscriptionsChangedListener(OnSubscriptionsChangedListener onSubscriptionsChangedListener) {
        if (onSubscriptionsChangedListener == null) {
            return;
        }
        addOnSubscriptionsChangedListener(onSubscriptionsChangedListener.mExecutor, onSubscriptionsChangedListener);
    }

    public void addOnSubscriptionsChangedListener(Executor executor, final OnSubscriptionsChangedListener onSubscriptionsChangedListener) {
        Context context = this.mContext;
        String opPackageName = context != null ? context.getOpPackageName() : "<unknown>";
        TelephonyRegistryManager telephonyRegistryManager = (TelephonyRegistryManager) this.mContext.getSystemService(Context.TELEPHONY_REGISTRY_SERVICE);
        if (telephonyRegistryManager != null) {
            telephonyRegistryManager.addOnSubscriptionsChangedListener(onSubscriptionsChangedListener, executor);
        } else {
            loge("addOnSubscriptionsChangedListener: pkgname=" + opPackageName + " failed to be added  due to TELEPHONY_REGISTRY_SERVICE being unavailable.");
            executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$SubscriptionManager$B1KmYf2jcqXVILNbUmS7MOrqrqc
                @Override // java.lang.Runnable
                public final void run() {
                    onSubscriptionsChangedListener.onAddListenerFailed();
                }
            });
        }
    }

    public void removeOnSubscriptionsChangedListener(OnSubscriptionsChangedListener onSubscriptionsChangedListener) {
        if (onSubscriptionsChangedListener == null) {
            return;
        }
        Context context = this.mContext;
        if (context != null) {
            context.getOpPackageName();
        }
        TelephonyRegistryManager telephonyRegistryManager = (TelephonyRegistryManager) this.mContext.getSystemService(Context.TELEPHONY_REGISTRY_SERVICE);
        if (telephonyRegistryManager != null) {
            telephonyRegistryManager.removeOnSubscriptionsChangedListener(onSubscriptionsChangedListener);
        }
    }

    public static class OnOpportunisticSubscriptionsChangedListener {
        public void onOpportunisticSubscriptionsChanged() {
        }

        private void log(String str) {
            com.android.telephony.Rlog.d(SubscriptionManager.LOG_TAG, str);
        }
    }

    public void addOnOpportunisticSubscriptionsChangedListener(Executor executor, OnOpportunisticSubscriptionsChangedListener onOpportunisticSubscriptionsChangedListener) {
        if (executor == null || onOpportunisticSubscriptionsChangedListener == null) {
            return;
        }
        Context context = this.mContext;
        if (context != null) {
            context.getOpPackageName();
        }
        TelephonyRegistryManager telephonyRegistryManager = (TelephonyRegistryManager) this.mContext.getSystemService(Context.TELEPHONY_REGISTRY_SERVICE);
        if (telephonyRegistryManager != null) {
            telephonyRegistryManager.addOnOpportunisticSubscriptionsChangedListener(onOpportunisticSubscriptionsChangedListener, executor);
        }
    }

    public void removeOnOpportunisticSubscriptionsChangedListener(OnOpportunisticSubscriptionsChangedListener onOpportunisticSubscriptionsChangedListener) {
        Preconditions.checkNotNull(onOpportunisticSubscriptionsChangedListener, "listener cannot be null");
        Context context = this.mContext;
        if (context != null) {
            context.getOpPackageName();
        }
        TelephonyRegistryManager telephonyRegistryManager = (TelephonyRegistryManager) this.mContext.getSystemService(Context.TELEPHONY_REGISTRY_SERVICE);
        if (telephonyRegistryManager != null) {
            telephonyRegistryManager.removeOnOpportunisticSubscriptionsChangedListener(onOpportunisticSubscriptionsChangedListener);
        }
    }

    public SubscriptionInfo getActiveSubscriptionInfo(int i) {
        if (!isValidSubscriptionId(i)) {
            return null;
        }
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getActiveSubscriptionInfo(i, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    @SystemApi
    public SubscriptionInfo getActiveSubscriptionInfoForIcc(String str) {
        if (str == null) {
            logd("[getActiveSubscriptionInfoForIccIndex]- null iccid");
            return null;
        }
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getActiveSubscriptionInfoForIccId(str, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public SubscriptionInfo getActiveSubscriptionInfoForSimSlotIndex(int i) {
        if (!isValidSlotIndex(i)) {
            logd("[getActiveSubscriptionInfoForSimSlotIndex]- invalid slotIndex");
            return null;
        }
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getActiveSubscriptionInfoForSimSlotIndex(i, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public List<SubscriptionInfo> getAllSubscriptionInfoList() throws RemoteException {
        List<SubscriptionInfo> allSubInfoList = null;
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                allSubInfoList = subscriptionService.getAllSubInfoList(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
        } catch (RemoteException unused) {
        }
        return allSubInfoList == null ? new ArrayList() : allSubInfoList;
    }

    public List<SubscriptionInfo> getActiveSubscriptionInfoList() {
        return getActiveSubscriptionInfoList(true);
    }

    public List<SubscriptionInfo> getCompleteActiveSubscriptionInfoList() {
        List<SubscriptionInfo> activeSubscriptionInfoList = getActiveSubscriptionInfoList(false);
        return activeSubscriptionInfoList == null ? new ArrayList() : activeSubscriptionInfoList;
    }

    public List<SubscriptionInfo> getActiveSubscriptionInfoList(boolean z) {
        List<SubscriptionInfo> activeSubscriptionInfoList = null;
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                activeSubscriptionInfoList = subscriptionService.getActiveSubscriptionInfoList(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
        } catch (RemoteException unused) {
        }
        return (!z || activeSubscriptionInfoList == null) ? activeSubscriptionInfoList : (List) activeSubscriptionInfoList.stream().filter(new Predicate() { // from class: android.telephony.-$$Lambda$SubscriptionManager$h7YpgyHsst6UNCPv-iPgeb904zM
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$getActiveSubscriptionInfoList$1$SubscriptionManager((SubscriptionInfo) obj);
            }
        }).collect(Collectors.toList());
    }

    @SystemApi
    public List<SubscriptionInfo> getAvailableSubscriptionInfoList() {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getAvailableSubscriptionInfoList(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public List<SubscriptionInfo> getAccessibleSubscriptionInfoList() {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getAccessibleSubscriptionInfoList(this.mContext.getOpPackageName());
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    @SystemApi
    public void requestEmbeddedSubscriptionInfoListRefresh() {
        int cardIdForDefaultEuicc = TelephonyManager.from(this.mContext).getCardIdForDefaultEuicc();
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                subscriptionService.requestEmbeddedSubscriptionInfoListRefresh(cardIdForDefaultEuicc);
            }
        } catch (RemoteException unused) {
            logd("requestEmbeddedSubscriptionInfoListFresh for card = " + cardIdForDefaultEuicc + " failed.");
        }
    }

    @SystemApi
    public void requestEmbeddedSubscriptionInfoListRefresh(int i) {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                subscriptionService.requestEmbeddedSubscriptionInfoListRefresh(i);
            }
        } catch (RemoteException unused) {
            logd("requestEmbeddedSubscriptionInfoListFresh for card = " + i + " failed.");
        }
    }

    public int getAllSubscriptionInfoCount() {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getAllSubInfoCount(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return 0;
        } catch (RemoteException unused) {
            return 0;
        }
    }

    public int getActiveSubscriptionInfoCount() {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getActiveSubInfoCount(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return 0;
        } catch (RemoteException unused) {
            return 0;
        }
    }

    public int getActiveSubscriptionInfoCountMax() {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getActiveSubInfoCountMax();
            }
            return 0;
        } catch (RemoteException unused) {
            return 0;
        }
    }

    public Uri addSubscriptionInfoRecord(String str, int i) {
        if (str == null) {
            logd("[addSubscriptionInfoRecord]- null iccId");
        }
        if (!isValidSlotIndex(i)) {
            logd("[addSubscriptionInfoRecord]- invalid slotIndex");
        }
        addSubscriptionInfoRecord(str, null, i, 0);
        return null;
    }

    public void addSubscriptionInfoRecord(String str, String str2, int i, int i2) {
        if (str == null) {
            Log.e(LOG_TAG, "[addSubscriptionInfoRecord]- uniqueId is null");
            return;
        }
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService == null) {
                Log.e(LOG_TAG, "[addSubscriptionInfoRecord]- ISub service is null");
                return;
            }
            int iAddSubInfo = subscriptionService.addSubInfo(str, str2, i, i2);
            if (iAddSubInfo < 0) {
                Log.e(LOG_TAG, "Adding of subscription didn't succeed: error = " + iAddSubInfo);
            } else {
                logd("successfully added new subscription");
            }
        } catch (RemoteException unused) {
        }
    }

    public void removeSubscriptionInfoRecord(String str, int i) {
        if (str == null) {
            Log.e(LOG_TAG, "[addSubscriptionInfoRecord]- uniqueId is null");
            return;
        }
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService == null) {
                Log.e(LOG_TAG, "[removeSubscriptionInfoRecord]- ISub service is null");
                return;
            }
            int iRemoveSubInfo = subscriptionService.removeSubInfo(str, i);
            if (iRemoveSubInfo < 0) {
                Log.e(LOG_TAG, "Removal of subscription didn't succeed: error = " + iRemoveSubInfo);
            } else {
                logd("successfully removed subscription");
            }
        } catch (RemoteException unused) {
        }
    }

    public int setIconTint(final int i, final int i2) {
        return setSubscriptionPropertyHelper(i2, "setIconTint", new CallISubMethodHelper() { // from class: android.telephony.-$$Lambda$SubscriptionManager$CrVx5h1Q2b4G7z1IzRBB--ACLak
            @Override // android.telephony.SubscriptionManager.CallISubMethodHelper
            public final int callMethod(ISub iSub) {
                return iSub.setIconTint(i, i2);
            }
        });
    }

    public int setDisplayName(final String str, final int i, final int i2) {
        return setSubscriptionPropertyHelper(i, "setDisplayName", new CallISubMethodHelper() { // from class: android.telephony.-$$Lambda$SubscriptionManager$Ikml-6uLZuwUZmTC5tZ8R1kOj0o
            @Override // android.telephony.SubscriptionManager.CallISubMethodHelper
            public final int callMethod(ISub iSub) {
                return iSub.setDisplayNameUsingSrc(str, i, i2);
            }
        });
    }

    public int setDisplayNumber(final String str, final int i) {
        if (str == null) {
            logd("[setDisplayNumber]- fail");
            return -1;
        }
        return setSubscriptionPropertyHelper(i, "setDisplayNumber", new CallISubMethodHelper() { // from class: android.telephony.-$$Lambda$SubscriptionManager$cHYtnyorc-4Amb_jsKaHlSS4tD4
            @Override // android.telephony.SubscriptionManager.CallISubMethodHelper
            public final int callMethod(ISub iSub) {
                return iSub.setDisplayNumber(str, i);
            }
        });
    }

    public int setDataRoaming(final int i, final int i2) {
        return setSubscriptionPropertyHelper(i2, "setDataRoaming", new CallISubMethodHelper() { // from class: android.telephony.-$$Lambda$SubscriptionManager$TOIkH_8fD4sr49nK7XX2EciW--g
            @Override // android.telephony.SubscriptionManager.CallISubMethodHelper
            public final int callMethod(ISub iSub) {
                return iSub.setDataRoaming(i, i2);
            }
        });
    }

    public static int getSlotIndex(int i) {
        return sSlotIndexCache.query(Integer.valueOf(i)).intValue();
    }

    public int[] getSubscriptionIds(int i) {
        return getSubId(i);
    }

    public static int[] getSubId(int i) {
        if (!isValidSlotIndex(i)) {
            logd("[getSubId]- fail");
            return null;
        }
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getSubId(i);
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public static int getPhoneId(int i) {
        return sPhoneIdCache.query(Integer.valueOf(i)).intValue();
    }

    private static void logd(String str) {
        com.android.telephony.Rlog.d(LOG_TAG, str);
    }

    private static void loge(String str) {
        com.android.telephony.Rlog.e(LOG_TAG, str);
    }

    public static int getDefaultSubscriptionId() {
        return sDefaultSubIdCache.query(null).intValue();
    }

    public static int getDefaultVoiceSubscriptionId() {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getDefaultVoiceSubId();
            }
            return -1;
        } catch (RemoteException unused) {
            return -1;
        }
    }

    @SystemApi
    public void setDefaultVoiceSubscriptionId(int i) {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                subscriptionService.setDefaultVoiceSubId(i);
            }
        } catch (RemoteException unused) {
        }
    }

    public void setDefaultVoiceSubId(int i) {
        setDefaultVoiceSubscriptionId(i);
    }

    public SubscriptionInfo getDefaultVoiceSubscriptionInfo() {
        return getActiveSubscriptionInfo(getDefaultVoiceSubscriptionId());
    }

    public static int getDefaultVoicePhoneId() {
        return getPhoneId(getDefaultVoiceSubscriptionId());
    }

    public static int getDefaultSmsSubscriptionId() {
        return sDefaultSmsSubIdCache.query(null).intValue();
    }

    @SystemApi
    public void setDefaultSmsSubId(int i) {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                subscriptionService.setDefaultSmsSubId(i);
            }
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public SubscriptionInfo getDefaultSmsSubscriptionInfo() {
        return getActiveSubscriptionInfo(getDefaultSmsSubscriptionId());
    }

    public int getDefaultSmsPhoneId() {
        return getPhoneId(getDefaultSmsSubscriptionId());
    }

    public static int getDefaultDataSubscriptionId() {
        return sDefaultDataSubIdCache.query(null).intValue();
    }

    @SystemApi
    public void setDefaultDataSubId(int i) {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                subscriptionService.setDefaultDataSubId(i);
            }
        } catch (RemoteException unused) {
        }
    }

    public SubscriptionInfo getDefaultDataSubscriptionInfo() {
        return getActiveSubscriptionInfo(getDefaultDataSubscriptionId());
    }

    public int getDefaultDataPhoneId() {
        return getPhoneId(getDefaultDataSubscriptionId());
    }

    public void clearSubscriptionInfo() {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                subscriptionService.clearSubInfo();
            }
        } catch (RemoteException unused) {
        }
    }

    public boolean allDefaultsSelected() {
        return isValidSubscriptionId(getDefaultDataSubscriptionId()) && isValidSubscriptionId(getDefaultSmsSubscriptionId()) && isValidSubscriptionId(getDefaultVoiceSubscriptionId());
    }

    public static boolean isUsableSubscriptionId(int i) {
        return isUsableSubIdValue(i);
    }

    public static boolean isValidSlotIndex(int i) {
        return i >= 0 && i < TelephonyManager.getDefault().getActiveModemCount();
    }

    public static boolean isValidPhoneId(int i) {
        return i >= 0 && i < TelephonyManager.getDefault().getActiveModemCount();
    }

    public static void putPhoneIdAndSubIdExtra(Intent intent, int i) {
        int[] subId = getSubId(i);
        if (subId != null && subId.length > 0) {
            putPhoneIdAndSubIdExtra(intent, i, subId[0]);
            return;
        }
        logd("putPhoneIdAndSubIdExtra: no valid subs");
        intent.putExtra("phone", i);
        intent.putExtra("android.telephony.extra.SLOT_INDEX", i);
    }

    public static void putPhoneIdAndSubIdExtra(Intent intent, int i, int i2) {
        intent.putExtra("android.telephony.extra.SLOT_INDEX", i);
        intent.putExtra("phone", i);
        putSubscriptionIdExtra(intent, i2);
    }

    @SystemApi
    public int[] getActiveSubscriptionIdList() {
        return getActiveSubscriptionIdList(true);
    }

    @SystemApi
    public int[] getCompleteActiveSubscriptionIdList() {
        return getActiveSubscriptionIdList(false);
    }

    public int[] getActiveSubscriptionIdList(boolean z) {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                int[] activeSubIdList = subscriptionService.getActiveSubIdList(z);
                if (activeSubIdList != null) {
                    return activeSubIdList;
                }
            }
        } catch (RemoteException unused) {
        }
        return new int[0];
    }

    public boolean isNetworkRoaming(int i) {
        if (getPhoneId(i) < 0) {
            return false;
        }
        return TelephonyManager.getDefault().isNetworkRoaming(i);
    }

    public static int getSimStateForSlotIndex(int i) {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getSimStateForSlotIndex(i);
            }
            return 0;
        } catch (RemoteException unused) {
            return 0;
        }
    }

    public static void setSubscriptionProperty(int i, String str, String str2) {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                subscriptionService.setSubscriptionProperty(i, str, str2);
            }
        } catch (RemoteException unused) {
        }
    }

    private static String getSubscriptionProperty(int i, String str, Context context) {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getSubscriptionProperty(i, str, context.getOpPackageName(), context.getAttributionTag());
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public static boolean getBooleanSubscriptionProperty(int i, String str, boolean z, Context context) {
        String subscriptionProperty = getSubscriptionProperty(i, str, context);
        if (subscriptionProperty != null) {
            try {
                return Integer.parseInt(subscriptionProperty) == 1;
            } catch (NumberFormatException unused) {
                logd("getBooleanSubscriptionProperty NumberFormat exception");
            }
        }
        return z;
    }

    public static int getIntegerSubscriptionProperty(int i, String str, int i2, Context context) {
        String subscriptionProperty = getSubscriptionProperty(i, str, context);
        if (subscriptionProperty != null) {
            try {
                return Integer.parseInt(subscriptionProperty);
            } catch (NumberFormatException unused) {
                logd("getIntegerSubscriptionProperty NumberFormat exception");
            }
        }
        return i2;
    }

    public static long getLongSubscriptionProperty(int i, String str, long j, Context context) {
        String subscriptionProperty = getSubscriptionProperty(i, str, context);
        if (subscriptionProperty != null) {
            try {
                return Long.parseLong(subscriptionProperty);
            } catch (NumberFormatException unused) {
                logd("getLongSubscriptionProperty NumberFormat exception");
            }
        }
        return j;
    }

    @SystemApi
    public static Resources getResourcesForSubId(Context context, int i) {
        return getResourcesForSubId(context, i, false);
    }

    public static Resources getResourcesForSubId(Context context, int i, boolean z) {
        Pair<Context, Integer> pairCreate;
        if (!isValidSubscriptionId(i) || z) {
            pairCreate = null;
        } else {
            pairCreate = Pair.create(context, Integer.valueOf(i));
            Map<Pair<Context, Integer>, Resources> map = sResourcesCache;
            if (map.containsKey(pairCreate)) {
                return map.get(pairCreate);
            }
        }
        SubscriptionInfo activeSubscriptionInfo = from(context).getActiveSubscriptionInfo(i);
        Configuration configuration = new Configuration();
        if (activeSubscriptionInfo != null) {
            configuration.mcc = activeSubscriptionInfo.getMcc();
            configuration.mnc = activeSubscriptionInfo.getMnc();
            if (configuration.mnc == 0) {
                configuration.mnc = 65535;
            }
        }
        if (z) {
            configuration.setLocale(Locale.ROOT);
        }
        Resources resources = context.createConfigurationContext(configuration).getResources();
        if (pairCreate != null) {
            sResourcesCache.put(pairCreate, resources);
        }
        return resources;
    }

    public boolean isActiveSubscriptionId(int i) {
        return isActiveSubId(i);
    }

    public boolean isActiveSubId(int i) {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.isActiveSubId(i, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public List<SubscriptionPlan> getSubscriptionPlans(int i) {
        SubscriptionPlan[] subscriptionPlans = getNetworkPolicyManager().getSubscriptionPlans(i, this.mContext.getOpPackageName());
        return subscriptionPlans == null ? Collections.emptyList() : Arrays.asList(subscriptionPlans);
    }

    public void setSubscriptionPlans(int i, List<SubscriptionPlan> list) {
        getNetworkPolicyManager().setSubscriptionPlans(i, (SubscriptionPlan[]) list.toArray(new SubscriptionPlan[list.size()]), this.mContext.getOpPackageName());
    }

    public void setSubscriptionOverrideUnmetered(int i, boolean z, long j) {
        getNetworkPolicyManager().setSubscriptionOverride(i, 1, z ? 1 : 0, j, this.mContext.getOpPackageName());
    }

    public void setSubscriptionOverrideCongested(int i, boolean z, long j) {
        getNetworkPolicyManager().setSubscriptionOverride(i, 2, z ? 2 : 0, j, this.mContext.getOpPackageName());
    }

    public boolean canManageSubscription(SubscriptionInfo subscriptionInfo) {
        return canManageSubscription(subscriptionInfo, this.mContext.getPackageName());
    }

    @SystemApi
    public boolean canManageSubscription(SubscriptionInfo subscriptionInfo, String str) {
        if (subscriptionInfo != null && subscriptionInfo.getAllAccessRules() != null && str != null) {
            try {
                PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(str, 134217728);
                Iterator<UiccAccessRule> it = subscriptionInfo.getAllAccessRules().iterator();
                while (it.hasNext()) {
                    if (it.next().getCarrierPrivilegeStatus(packageInfo) == 1) {
                        return true;
                    }
                }
                return false;
            } catch (PackageManager.NameNotFoundException unused) {
                logd("Unknown package: " + str);
            }
        }
        return false;
    }

    @SystemApi
    public void setPreferredDataSubscriptionId(int i, boolean z, Executor executor, Consumer<Integer> consumer) {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService == null) {
                return;
            }
            subscriptionService.setPreferredDataSubscriptionId(i, z, new AnonymousClass1(executor, consumer));
        } catch (RemoteException unused) {
        }
    }

    /* JADX INFO: renamed from: android.telephony.SubscriptionManager$1, reason: invalid class name */
    class AnonymousClass1 extends ISetOpportunisticDataCallback.Stub {
        final /* synthetic */ Consumer val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass1(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$callback = consumer;
        }

        @Override // com.android.internal.telephony.ISetOpportunisticDataCallback
        public void onComplete(final int i) {
            if (this.val$executor == null || this.val$callback == null) {
                return;
            }
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final Consumer consumer = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$SubscriptionManager$1$4K-G9_2fTYIz7BLtxbP6FZu9Lhc
                    @Override // java.lang.Runnable
                    public final void run() {
                        consumer.accept(Integer.valueOf(i));
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public int getPreferredDataSubscriptionId() {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getPreferredDataSubscriptionId();
            }
            return Integer.MAX_VALUE;
        } catch (RemoteException unused) {
            return Integer.MAX_VALUE;
        }
    }

    public List<SubscriptionInfo> getOpportunisticSubscriptions() throws RemoteException {
        Context context = this.mContext;
        String opPackageName = context != null ? context.getOpPackageName() : "<unknown>";
        Context context2 = this.mContext;
        List<SubscriptionInfo> opportunisticSubscriptions = null;
        String attributionTag = context2 != null ? context2.getAttributionTag() : null;
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                opportunisticSubscriptions = subscriptionService.getOpportunisticSubscriptions(opPackageName, attributionTag);
            }
        } catch (RemoteException unused) {
        }
        return opportunisticSubscriptions == null ? new ArrayList() : opportunisticSubscriptions;
    }

    public void switchToSubscription(int i, PendingIntent pendingIntent) {
        Preconditions.checkNotNull(pendingIntent, "callbackIntent cannot be null");
        new EuiccManager(this.mContext).switchToSubscription(i, pendingIntent);
    }

    public boolean setOpportunistic(final boolean z, final int i) {
        return setSubscriptionPropertyHelper(i, "setOpportunistic", new CallISubMethodHelper() { // from class: android.telephony.-$$Lambda$SubscriptionManager$Xtw1YheVsIX81UULcvfHPpYDIcw
            @Override // android.telephony.SubscriptionManager.CallISubMethodHelper
            public final int callMethod(ISub iSub) {
                return this.f$0.lambda$setOpportunistic$6$SubscriptionManager(z, i, iSub);
            }
        }) == 1;
    }

    public /* synthetic */ int lambda$setOpportunistic$6$SubscriptionManager(boolean z, int i, ISub iSub) throws RemoteException {
        return iSub.setOpportunistic(z, i, this.mContext.getOpPackageName());
    }

    public ParcelUuid createSubscriptionGroup(List<Integer> list) {
        Preconditions.checkNotNull(list, "can't create group for null subId list");
        Context context = this.mContext;
        String opPackageName = context != null ? context.getOpPackageName() : "<unknown>";
        int[] array = list.stream().mapToInt(new ToIntFunction() { // from class: android.telephony.-$$Lambda$SubscriptionManager$V90ytWF0usfWu7E4emw-xumf3LY
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).toArray();
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.createSubscriptionGroup(array, opPackageName);
            }
            if (isSystemProcess()) {
                return null;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            loge("createSubscriptionGroup RemoteException " + e);
            if (isSystemProcess()) {
                return null;
            }
            e.rethrowAsRuntimeException();
            return null;
        }
    }

    public void addSubscriptionsIntoGroup(List<Integer> list, ParcelUuid parcelUuid) {
        Preconditions.checkNotNull(list, "subIdList can't be null.");
        Preconditions.checkNotNull(parcelUuid, "groupUuid can't be null.");
        Context context = this.mContext;
        String opPackageName = context != null ? context.getOpPackageName() : "<unknown>";
        int[] array = list.stream().mapToInt(new ToIntFunction() { // from class: android.telephony.-$$Lambda$SubscriptionManager$JIZ09L9BzS89I3rGYxH5S4P7Qyk
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).toArray();
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                subscriptionService.addSubscriptionsIntoGroup(array, parcelUuid, opPackageName);
            } else if (!isSystemProcess()) {
                throw new IllegalStateException("telephony service is null.");
            }
        } catch (RemoteException e) {
            loge("addSubscriptionsIntoGroup RemoteException " + e);
            if (isSystemProcess()) {
                return;
            }
            e.rethrowAsRuntimeException();
        }
    }

    private boolean isSystemProcess() {
        return Process.myUid() == 1000;
    }

    public void removeSubscriptionsFromGroup(List<Integer> list, ParcelUuid parcelUuid) {
        Preconditions.checkNotNull(list, "subIdList can't be null.");
        Preconditions.checkNotNull(parcelUuid, "groupUuid can't be null.");
        Context context = this.mContext;
        String opPackageName = context != null ? context.getOpPackageName() : "<unknown>";
        int[] array = list.stream().mapToInt(new ToIntFunction() { // from class: android.telephony.-$$Lambda$SubscriptionManager$T2_R1mRSdsqjAnaxGSX2X9BumFo
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).toArray();
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                subscriptionService.removeSubscriptionsFromGroup(array, parcelUuid, opPackageName);
            } else if (!isSystemProcess()) {
                throw new IllegalStateException("telephony service is null.");
            }
        } catch (RemoteException e) {
            loge("removeSubscriptionsFromGroup RemoteException " + e);
            if (isSystemProcess()) {
                return;
            }
            e.rethrowAsRuntimeException();
        }
    }

    public List<SubscriptionInfo> getSubscriptionsInGroup(ParcelUuid parcelUuid) {
        Preconditions.checkNotNull(parcelUuid, "groupUuid can't be null");
        Context context = this.mContext;
        String opPackageName = context != null ? context.getOpPackageName() : "<unknown>";
        Context context2 = this.mContext;
        String attributionTag = context2 != null ? context2.getAttributionTag() : null;
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getSubscriptionsInGroup(parcelUuid, opPackageName, attributionTag);
            }
            if (isSystemProcess()) {
                return null;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            loge("removeSubscriptionsFromGroup RemoteException " + e);
            if (isSystemProcess()) {
                return null;
            }
            e.rethrowAsRuntimeException();
            return null;
        }
    }

    /* JADX INFO: renamed from: isSubscriptionVisible, reason: merged with bridge method [inline-methods] */
    public boolean lambda$getActiveSubscriptionInfoList$1$SubscriptionManager(SubscriptionInfo subscriptionInfo) {
        if (subscriptionInfo == null) {
            return false;
        }
        if (subscriptionInfo.getGroupUuid() == null || !subscriptionInfo.isOpportunistic()) {
            return true;
        }
        return TelephonyManager.from(this.mContext).hasCarrierPrivileges(subscriptionInfo.getSubscriptionId()) || canManageSubscription(subscriptionInfo);
    }

    public List<SubscriptionInfo> getSelectableSubscriptionInfoList() {
        List<SubscriptionInfo> availableSubscriptionInfoList = getAvailableSubscriptionInfoList();
        if (availableSubscriptionInfoList == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        for (SubscriptionInfo subscriptionInfo : availableSubscriptionInfoList) {
            if (lambda$getActiveSubscriptionInfoList$1$SubscriptionManager(subscriptionInfo)) {
                ParcelUuid groupUuid = subscriptionInfo.getGroupUuid();
                if (groupUuid == null) {
                    arrayList.add(subscriptionInfo);
                } else if (!map.containsKey(groupUuid) || (((SubscriptionInfo) map.get(groupUuid)).getSimSlotIndex() == -1 && subscriptionInfo.getSimSlotIndex() != -1)) {
                    arrayList.remove(map.get(groupUuid));
                    arrayList.add(subscriptionInfo);
                    map.put(groupUuid, subscriptionInfo);
                }
            }
        }
        return arrayList;
    }

    @SystemApi
    public boolean setSubscriptionEnabled(int i, boolean z) {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.setSubscriptionEnabled(z, i);
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @SystemApi
    public void setUiccApplicationsEnabled(int i, boolean z) {
        try {
            ISub iSubAsInterface = ISub.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getSubscriptionServiceRegisterer().get());
            if (iSubAsInterface != null) {
                iSubAsInterface.setUiccApplicationsEnabled(z, i);
            }
        } catch (RemoteException unused) {
        }
    }

    @SystemApi
    public boolean canDisablePhysicalSubscription() {
        try {
            ISub iSubAsInterface = ISub.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getSubscriptionServiceRegisterer().get());
            if (iSubAsInterface != null) {
                return iSubAsInterface.canDisablePhysicalSubscription();
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @SystemApi
    public boolean isSubscriptionEnabled(int i) {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.isSubscriptionEnabled(i);
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @SystemApi
    public int getEnabledSubscriptionId(int i) {
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return subscriptionService.getEnabledSubscriptionId(i);
            }
            return -1;
        } catch (RemoteException unused) {
            return -1;
        }
    }

    private int setSubscriptionPropertyHelper(int i, String str, CallISubMethodHelper callISubMethodHelper) {
        if (!isValidSubscriptionId(i)) {
            logd("[" + str + "]- fail");
            return -1;
        }
        try {
            ISub subscriptionService = TelephonyManager.getSubscriptionService();
            if (subscriptionService != null) {
                return callISubMethodHelper.callMethod(subscriptionService);
            }
            return 0;
        } catch (RemoteException unused) {
            return 0;
        }
    }

    public static int getActiveDataSubscriptionId() {
        return sActiveDataSubIdCache.query(null).intValue();
    }

    public static void putSubscriptionIdExtra(Intent intent, int i) {
        intent.putExtra("android.telephony.extra.SUBSCRIPTION_INDEX", i);
        intent.putExtra(PhoneConstants.SUBSCRIPTION_KEY, i);
    }

    public static void invalidateDefaultSubIdCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_DEFAULT_SUB_ID_PROPERTY);
    }

    public static void invalidateDefaultDataSubIdCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_DEFAULT_DATA_SUB_ID_PROPERTY);
    }

    public static void invalidateDefaultSmsSubIdCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_DEFAULT_SMS_SUB_ID_PROPERTY);
    }

    public static void invalidateActiveDataSubIdCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_ACTIVE_DATA_SUB_ID_PROPERTY);
    }

    public static void invalidateSlotIndexCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_SLOT_INDEX_PROPERTY);
    }

    public static void disableCaching() {
        sDefaultSubIdCache.disableLocal();
        sDefaultDataSubIdCache.disableLocal();
        sActiveDataSubIdCache.disableLocal();
        sDefaultSmsSubIdCache.disableLocal();
        sSlotIndexCache.disableLocal();
        sPhoneIdCache.disableLocal();
    }

    public static void clearCaches() {
        sDefaultSubIdCache.clear();
        sDefaultDataSubIdCache.clear();
        sActiveDataSubIdCache.clear();
        sDefaultSmsSubIdCache.clear();
        sSlotIndexCache.clear();
        sPhoneIdCache.clear();
    }
}
