package com.android.car.developeroptions.datausage;

import android.content.Context;
import android.net.INetworkPolicyListener;
import android.net.NetworkPolicyManager;
import android.os.RemoteException;
import android.telephony.SubscriptionPlan;
import android.util.SparseIntArray;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class DataSaverBackend {
    private boolean mBlacklistInitialized;
    private final Context mContext;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private final NetworkPolicyManager mPolicyManager;
    private boolean mWhitelistInitialized;
    private final ArrayList<Listener> mListeners = new ArrayList<>();
    private SparseIntArray mUidPolicies = new SparseIntArray();
    private final INetworkPolicyListener mPolicyListener = new AnonymousClass1();

    public interface Listener {
        void onBlacklistStatusChanged(int i, boolean z);

        void onDataSaverChanged(boolean z);

        void onWhitelistStatusChanged(int i, boolean z);
    }

    public DataSaverBackend(Context context) {
        this.mContext = context;
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
        this.mPolicyManager = NetworkPolicyManager.from(context);
    }

    public void addListener(Listener listener) {
        this.mListeners.add(listener);
        if (this.mListeners.size() == 1) {
            this.mPolicyManager.registerListener(this.mPolicyListener);
        }
        listener.onDataSaverChanged(isDataSaverEnabled());
    }

    public void remListener(Listener listener) {
        this.mListeners.remove(listener);
        if (this.mListeners.size() == 0) {
            this.mPolicyManager.unregisterListener(this.mPolicyListener);
        }
    }

    public boolean isDataSaverEnabled() {
        return this.mPolicyManager.getRestrictBackground();
    }

    public void setDataSaverEnabled(boolean z) {
        this.mPolicyManager.setRestrictBackground(z);
        this.mMetricsFeatureProvider.action(this.mContext, 394, z ? 1 : 0);
    }

    public void refreshWhitelist() {
        loadWhitelist();
    }

    public void setIsWhitelisted(int i, String str, boolean z) {
        int i2 = z ? 4 : 0;
        this.mPolicyManager.setUidPolicy(i, i2);
        this.mUidPolicies.put(i, i2);
        if (z) {
            this.mMetricsFeatureProvider.action(this.mContext, 395, str);
        }
    }

    public boolean isWhitelisted(int i) {
        loadWhitelist();
        return this.mUidPolicies.get(i, 0) == 4;
    }

    private void loadWhitelist() {
        if (this.mWhitelistInitialized) {
            return;
        }
        for (int i : this.mPolicyManager.getUidsWithPolicy(4)) {
            this.mUidPolicies.put(i, 4);
        }
        this.mWhitelistInitialized = true;
    }

    public void refreshBlacklist() {
        loadBlacklist();
    }

    public void setIsBlacklisted(int i, String str, boolean z) {
        this.mPolicyManager.setUidPolicy(i, z ? 1 : 0);
        this.mUidPolicies.put(i, z ? 1 : 0);
        if (z) {
            this.mMetricsFeatureProvider.action(this.mContext, 396, str);
        }
    }

    public boolean isBlacklisted(int i) {
        loadBlacklist();
        return this.mUidPolicies.get(i, 0) == 1;
    }

    private void loadBlacklist() {
        if (this.mBlacklistInitialized) {
            return;
        }
        for (int i : this.mPolicyManager.getUidsWithPolicy(1)) {
            this.mUidPolicies.put(i, 1);
        }
        this.mBlacklistInitialized = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRestrictBackgroundChanged(boolean z) {
        for (int i = 0; i < this.mListeners.size(); i++) {
            this.mListeners.get(i).onDataSaverChanged(z);
        }
    }

    private void handleWhitelistChanged(int i, boolean z) {
        for (int i2 = 0; i2 < this.mListeners.size(); i2++) {
            this.mListeners.get(i2).onWhitelistStatusChanged(i, z);
        }
    }

    private void handleBlacklistChanged(int i, boolean z) {
        for (int i2 = 0; i2 < this.mListeners.size(); i2++) {
            this.mListeners.get(i2).onBlacklistStatusChanged(i, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUidPoliciesChanged(int i, int i2) {
        loadWhitelist();
        loadBlacklist();
        int i3 = this.mUidPolicies.get(i, 0);
        if (i2 == 0) {
            this.mUidPolicies.delete(i);
        } else {
            this.mUidPolicies.put(i, i2);
        }
        boolean z = i3 == 4;
        boolean z2 = i3 == 1;
        boolean z3 = i2 == 4;
        boolean z4 = i2 == 1;
        if (z != z3) {
            handleWhitelistChanged(i, z3);
        }
        if (z2 != z4) {
            handleBlacklistChanged(i, z4);
        }
    }

    /* JADX INFO: renamed from: com.android.car.developeroptions.datausage.DataSaverBackend$1, reason: invalid class name */
    class AnonymousClass1 extends INetworkPolicyListener.Stub {
        public void onMeteredIfacesChanged(String[] strArr) throws RemoteException {
        }

        public void onSubscriptionOverride(int i, int i2, int i3) {
        }

        public void onSubscriptionPlansChanged(int i, SubscriptionPlan[] subscriptionPlanArr) {
        }

        public void onUidRulesChanged(int i, int i2) throws RemoteException {
        }

        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onUidPoliciesChanged$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onUidPoliciesChanged$0$DataSaverBackend$1(int i, int i2) {
            DataSaverBackend.this.handleUidPoliciesChanged(i, i2);
        }

        public void onUidPoliciesChanged(final int i, final int i2) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.datausage.-$$Lambda$DataSaverBackend$1$wrJdCDm3oh3PJA1SJSADrY2O-xk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onUidPoliciesChanged$0$DataSaverBackend$1(i, i2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onRestrictBackgroundChanged$1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onRestrictBackgroundChanged$1$DataSaverBackend$1(boolean z) {
            DataSaverBackend.this.handleRestrictBackgroundChanged(z);
        }

        public void onRestrictBackgroundChanged(final boolean z) throws RemoteException {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.datausage.-$$Lambda$DataSaverBackend$1$v_mDB3TbZ3g2m6oVVmu1R0lEPdI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onRestrictBackgroundChanged$1$DataSaverBackend$1(z);
                }
            });
        }
    }
}
