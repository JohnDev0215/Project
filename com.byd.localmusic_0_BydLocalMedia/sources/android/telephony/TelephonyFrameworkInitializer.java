package android.telephony;

import android.app.SystemServiceRegistry;
import android.content.Context;
import android.os.TelephonyServiceManager;
import android.telephony.euicc.EuiccCardManager;
import android.telephony.euicc.EuiccManager;
import android.telephony.ims.ImsManager;
import com.android.internal.util.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public class TelephonyFrameworkInitializer {
    private static volatile TelephonyServiceManager sTelephonyServiceManager;

    private TelephonyFrameworkInitializer() {
    }

    public static void setTelephonyServiceManager(TelephonyServiceManager telephonyServiceManager) {
        Preconditions.checkState(sTelephonyServiceManager == null, "setTelephonyServiceManager called twice!");
        sTelephonyServiceManager = (TelephonyServiceManager) Preconditions.checkNotNull(telephonyServiceManager);
    }

    public static void registerServiceWrappers() {
        SystemServiceRegistry.registerContextAwareService("phone", TelephonyManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder() { // from class: android.telephony.-$$Lambda$TelephonyFrameworkInitializer$sQClc4rjc9ydh0nXpY79gr33av4
            @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
            public final Object createService(Context context) {
                return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$0(context);
            }
        });
        SystemServiceRegistry.registerContextAwareService(Context.TELEPHONY_SUBSCRIPTION_SERVICE, SubscriptionManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder() { // from class: android.telephony.-$$Lambda$TelephonyFrameworkInitializer$b_92_3ZijRrdEa9yLyFA5xu19OM
            @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
            public final Object createService(Context context) {
                return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$1(context);
            }
        });
        SystemServiceRegistry.registerContextAwareService("carrier_config", CarrierConfigManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder() { // from class: android.telephony.-$$Lambda$TelephonyFrameworkInitializer$3Kis6wL1IbLustWe9A2o4-2YpGo
            @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
            public final Object createService(Context context) {
                return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$2(context);
            }
        });
        SystemServiceRegistry.registerContextAwareService(Context.EUICC_SERVICE, EuiccManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder() { // from class: android.telephony.-$$Lambda$TelephonyFrameworkInitializer$mpe0Kh92VEQmEtmo60oqykdvnBE
            @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
            public final Object createService(Context context) {
                return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$3(context);
            }
        });
        SystemServiceRegistry.registerContextAwareService(Context.EUICC_CARD_SERVICE, EuiccCardManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder() { // from class: android.telephony.-$$Lambda$TelephonyFrameworkInitializer$MLDtRnX1dj1RKFdjgIsOvcQxhA0
            @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
            public final Object createService(Context context) {
                return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$4(context);
            }
        });
        SystemServiceRegistry.registerContextAwareService(Context.TELEPHONY_IMS_SERVICE, ImsManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder() { // from class: android.telephony.-$$Lambda$TelephonyFrameworkInitializer$o3geRfUaRT9tnqKKZbu1EbUxw4Q
            @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
            public final Object createService(Context context) {
                return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$5(context);
            }
        });
    }

    static /* synthetic */ TelephonyManager lambda$registerServiceWrappers$0(Context context) {
        return new TelephonyManager(context);
    }

    static /* synthetic */ SubscriptionManager lambda$registerServiceWrappers$1(Context context) {
        return new SubscriptionManager(context);
    }

    static /* synthetic */ CarrierConfigManager lambda$registerServiceWrappers$2(Context context) {
        return new CarrierConfigManager(context);
    }

    static /* synthetic */ EuiccManager lambda$registerServiceWrappers$3(Context context) {
        return new EuiccManager(context);
    }

    static /* synthetic */ EuiccCardManager lambda$registerServiceWrappers$4(Context context) {
        return new EuiccCardManager(context);
    }

    static /* synthetic */ ImsManager lambda$registerServiceWrappers$5(Context context) {
        return new ImsManager(context);
    }

    public static TelephonyServiceManager getTelephonyServiceManager() {
        return sTelephonyServiceManager;
    }
}
