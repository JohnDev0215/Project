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
        SystemServiceRegistry.registerContextAwareService("phone", TelephonyManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder() { // from class: android.telephony.-$$Lambda$TelephonyFrameworkInitializer$iP56cjUbUR1ZRIwzluIA2t9jDSM
            @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
            public final Object createService(Context context) {
                return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$0(context);
            }
        });
        SystemServiceRegistry.registerContextAwareService(Context.TELEPHONY_SUBSCRIPTION_SERVICE, SubscriptionManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder() { // from class: android.telephony.-$$Lambda$TelephonyFrameworkInitializer$IEjfG8bGSLwdHGUtloFDkGS6--g
            @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
            public final Object createService(Context context) {
                return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$1(context);
            }
        });
        SystemServiceRegistry.registerContextAwareService("carrier_config", CarrierConfigManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder() { // from class: android.telephony.-$$Lambda$TelephonyFrameworkInitializer$aB_lEuhMyjo_dL0qogCt0xySVlM
            @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
            public final Object createService(Context context) {
                return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$2(context);
            }
        });
        SystemServiceRegistry.registerContextAwareService(Context.EUICC_SERVICE, EuiccManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder() { // from class: android.telephony.-$$Lambda$TelephonyFrameworkInitializer$2QFGz0zRG7KweN6skHiT4JELKpE
            @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
            public final Object createService(Context context) {
                return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$3(context);
            }
        });
        SystemServiceRegistry.registerContextAwareService(Context.EUICC_CARD_SERVICE, EuiccCardManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder() { // from class: android.telephony.-$$Lambda$TelephonyFrameworkInitializer$BKyGKhYUpMaytlSRyiRRsnSFE_A
            @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
            public final Object createService(Context context) {
                return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$4(context);
            }
        });
        SystemServiceRegistry.registerContextAwareService(Context.TELEPHONY_IMS_SERVICE, ImsManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder() { // from class: android.telephony.-$$Lambda$TelephonyFrameworkInitializer$5MLVCuLnek3TLYfxfOKDNJ8h7gk
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
