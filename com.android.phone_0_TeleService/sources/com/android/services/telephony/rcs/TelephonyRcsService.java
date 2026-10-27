package com.android.services.telephony.rcs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telephony.IndentingPrintWriter;
import com.android.internal.telephony.PhoneConfigurationManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class TelephonyRcsService {
    private final Context mContext;
    private SparseArray<RcsFeatureController> mFeatureControllers;
    private int mNumSlots;
    private FeatureFactory mFeatureFactory = new FeatureFactory(this) { // from class: com.android.services.telephony.rcs.TelephonyRcsService.1
        @Override // com.android.services.telephony.rcs.TelephonyRcsService.FeatureFactory
        public RcsFeatureController createController(Context context, int i) {
            return new RcsFeatureController(context, i);
        }

        @Override // com.android.services.telephony.rcs.TelephonyRcsService.FeatureFactory
        public UserCapabilityExchangeImpl createUserCapabilityExchange(Context context, int i, int i2) {
            return new UserCapabilityExchangeImpl(context, i, i2);
        }
    };
    private final Object mLock = new Object();
    private BroadcastReceiver mCarrierConfigChangedReceiver = new BroadcastReceiver() { // from class: com.android.services.telephony.rcs.TelephonyRcsService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Bundle extras;
            if (intent == null || !"android.telephony.action.CARRIER_CONFIG_CHANGED".equals(intent.getAction()) || (extras = intent.getExtras()) == null) {
                return;
            }
            TelephonyRcsService.this.updateFeatureControllerSubscription(extras.getInt("android.telephony.extra.SLOT_INDEX", -1), extras.getInt("android.telephony.extra.SUBSCRIPTION_INDEX", -1));
        }
    };
    private Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.android.services.telephony.rcs.-$$Lambda$TelephonyRcsService$f5IM5_2pWUq_1k-60dfUVbIn71g
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return this.f$0.lambda$new$0$TelephonyRcsService(message);
        }
    });

    @VisibleForTesting
    public interface FeatureFactory {
        RcsFeatureController createController(Context context, int i);

        UserCapabilityExchangeImpl createUserCapabilityExchange(Context context, int i, int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$new$0$TelephonyRcsService(Message message) {
        if (message.what != 1) {
            return false;
        }
        Integer num = (Integer) ((AsyncResult) message.obj).result;
        if (num == null) {
            Log.w("TelephonyRcsService", "msim config change with null num slots.");
        } else {
            updateFeatureControllerSize(num.intValue());
        }
        return true;
    }

    public TelephonyRcsService(Context context, int i) {
        this.mContext = context;
        this.mNumSlots = i;
        this.mFeatureControllers = new SparseArray<>(i);
    }

    public RcsFeatureController getFeatureController(int i) {
        RcsFeatureController rcsFeatureController;
        synchronized (this.mLock) {
            rcsFeatureController = this.mFeatureControllers.get(i);
        }
        return rcsFeatureController;
    }

    public void initialize() {
        updateFeatureControllerSize(this.mNumSlots);
        PhoneConfigurationManager.registerForMultiSimConfigChange(this.mHandler, 1, (Object) null);
        this.mContext.registerReceiver(this.mCarrierConfigChangedReceiver, new IntentFilter("android.telephony.action.CARRIER_CONFIG_CHANGED"));
    }

    @VisibleForTesting
    public void setFeatureFactory(FeatureFactory featureFactory) {
        this.mFeatureFactory = featureFactory;
    }

    @VisibleForTesting
    public void updateFeatureControllerSize(int i) {
        synchronized (this.mLock) {
            int size = this.mFeatureControllers.size();
            if (size == i) {
                return;
            }
            Log.i("TelephonyRcsService", "updateFeatureControllers: oldSlots=" + size + ", newNumSlots=" + i);
            this.mNumSlots = i;
            if (size < i) {
                while (size < i) {
                    RcsFeatureController rcsFeatureControllerConstructFeatureController = constructFeatureController(size);
                    if (rcsFeatureControllerConstructFeatureController.hasActiveFeatures()) {
                        this.mFeatureControllers.put(size, rcsFeatureControllerConstructFeatureController);
                    }
                    size++;
                }
            } else {
                for (int i2 = size - 1; i2 > i - 1; i2--) {
                    RcsFeatureController rcsFeatureController = this.mFeatureControllers.get(i2);
                    if (rcsFeatureController != null) {
                        this.mFeatureControllers.remove(i2);
                        rcsFeatureController.destroy();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFeatureControllerSubscription(int i, int i2) {
        synchronized (this.mLock) {
            RcsFeatureController rcsFeatureControllerCreateController = this.mFeatureControllers.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append("updateFeatureControllerSubscription: slotId=");
            sb.append(i);
            sb.append(" newSubId=");
            sb.append(i2);
            sb.append(", existing feature=");
            sb.append(rcsFeatureControllerCreateController != null);
            Log.i("TelephonyRcsService", sb.toString());
            if (SubscriptionManager.isValidSubscriptionId(i2)) {
                if (rcsFeatureControllerCreateController == null) {
                    rcsFeatureControllerCreateController = this.mFeatureFactory.createController(this.mContext, i);
                    updateSupportedFeatures(rcsFeatureControllerCreateController, i, i2);
                    if (rcsFeatureControllerCreateController.hasActiveFeatures()) {
                        this.mFeatureControllers.put(i, rcsFeatureControllerCreateController);
                    }
                } else {
                    updateSupportedFeatures(rcsFeatureControllerCreateController, i, i2);
                    if (!rcsFeatureControllerCreateController.hasActiveFeatures()) {
                        rcsFeatureControllerCreateController.destroy();
                        this.mFeatureControllers.remove(i);
                    }
                }
            }
            if (rcsFeatureControllerCreateController != null) {
                rcsFeatureControllerCreateController.updateAssociatedSubscription(i2);
            }
        }
    }

    private RcsFeatureController constructFeatureController(int i) {
        RcsFeatureController rcsFeatureControllerCreateController = this.mFeatureFactory.createController(this.mContext, i);
        updateSupportedFeatures(rcsFeatureControllerCreateController, i, getSubscriptionFromSlot(i));
        return rcsFeatureControllerCreateController;
    }

    private void updateSupportedFeatures(RcsFeatureController rcsFeatureController, int i, int i2) {
        if (doesSubscriptionSupportPresence(i2)) {
            if (rcsFeatureController.getFeature(UserCapabilityExchangeImpl.class) == null) {
                rcsFeatureController.addFeature(this.mFeatureFactory.createUserCapabilityExchange(this.mContext, i, i2), UserCapabilityExchangeImpl.class);
            }
        } else if (rcsFeatureController.getFeature(UserCapabilityExchangeImpl.class) != null) {
            rcsFeatureController.removeFeature(UserCapabilityExchangeImpl.class);
        }
        if (rcsFeatureController.hasActiveFeatures()) {
            rcsFeatureController.connect();
        }
    }

    private boolean doesSubscriptionSupportPresence(int i) {
        CarrierConfigManager carrierConfigManager;
        if (SubscriptionManager.isValidSubscriptionId(i) && (carrierConfigManager = (CarrierConfigManager) this.mContext.getSystemService(CarrierConfigManager.class)) != null) {
            return carrierConfigManager.getConfigForSubId(i).getBoolean("use_rcs_sip_options_bool") | carrierConfigManager.getConfigForSubId(i).getBoolean("use_rcs_presence_bool");
        }
        return false;
    }

    private int getSubscriptionFromSlot(int i) {
        SubscriptionManager subscriptionManager = (SubscriptionManager) this.mContext.getSystemService(SubscriptionManager.class);
        if (subscriptionManager == null) {
            Log.w("TelephonyRcsService", "Couldn't find SubscriptionManager for slotId=" + i);
            return -1;
        }
        int[] subscriptionIds = subscriptionManager.getSubscriptionIds(i);
        if (subscriptionIds == null || subscriptionIds.length <= 0) {
            return -1;
        }
        return subscriptionIds[0];
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
        indentingPrintWriter.println("RcsFeatureControllers:");
        indentingPrintWriter.increaseIndent();
        synchronized (this.mLock) {
            for (int i = 0; i < this.mNumSlots; i++) {
                RcsFeatureController rcsFeatureController = this.mFeatureControllers.get(i);
                indentingPrintWriter.increaseIndent();
                rcsFeatureController.dump(fileDescriptor, printWriter, strArr);
                indentingPrintWriter.decreaseIndent();
            }
        }
        indentingPrintWriter.decreaseIndent();
    }
}
