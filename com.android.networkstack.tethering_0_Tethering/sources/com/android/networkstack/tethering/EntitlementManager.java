package com.android.networkstack.tethering;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.util.SharedLog;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.telephony.CarrierConfigManager;
import android.util.SparseIntArray;
import java.io.PrintWriter;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public class EntitlementManager {
    protected static final String DISABLE_PROVISIONING_SYSPROP_KEY = "net.tethering.noprovisioning";
    private static final String TAG = "EntitlementManager";
    private final Context mContext;
    private TetheringConfigurationFetcher mFetcher;
    private final Handler mHandler;
    private OnUiEntitlementFailedListener mListener;
    private final SharedLog mLog;
    private final Runnable mPermissionChangeCallback;
    private PendingIntent mProvisioningRecheckAlarm;
    private final ComponentName mSilentProvisioningService;
    private boolean mLastCellularUpstreamPermitted = true;
    private boolean mUsingCellularAsUpstream = false;
    private boolean mNeedReRunProvisioningUi = false;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.networkstack.tethering.EntitlementManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.android.networkstack.tethering.PROVISIONING_RECHECK_ALARM".equals(intent.getAction())) {
                EntitlementManager.this.mLog.log("Received provisioning alarm");
                EntitlementManager.this.reevaluateSimCardProvisioning(EntitlementManager.this.mFetcher.fetchTetheringConfiguration());
            }
        }
    };
    private final BitSet mCurrentDownstreams = new BitSet();
    private final BitSet mExemptedDownstreams = new BitSet();
    private final SparseIntArray mCurrentEntitlementResults = new SparseIntArray();
    private final SparseIntArray mEntitlementCacheValue = new SparseIntArray();

    public interface OnUiEntitlementFailedListener {
        void onUiEntitlementFailed(int i);
    }

    public interface TetheringConfigurationFetcher {
        TetheringConfiguration fetchTetheringConfiguration();
    }

    private static boolean isValidDownstreamType(int i) {
        return i == 0 || i == 1 || i == 2 || i == 5;
    }

    public EntitlementManager(Context context, Handler handler, SharedLog sharedLog, Runnable runnable) {
        this.mContext = context;
        this.mLog = sharedLog.forSubComponent(TAG);
        this.mPermissionChangeCallback = runnable;
        this.mHandler = handler;
        this.mContext.registerReceiver(this.mReceiver, new IntentFilter("com.android.networkstack.tethering.PROVISIONING_RECHECK_ALARM"), null, this.mHandler);
        this.mSilentProvisioningService = ComponentName.unflattenFromString(this.mContext.getResources().getString(R.string.config_wifi_tether_enable));
    }

    public void setOnUiEntitlementFailedListener(OnUiEntitlementFailedListener onUiEntitlementFailedListener) {
        this.mListener = onUiEntitlementFailedListener;
    }

    public void setTetheringConfigurationFetcher(TetheringConfigurationFetcher tetheringConfigurationFetcher) {
        this.mFetcher = tetheringConfigurationFetcher;
    }

    public boolean isCellularUpstreamPermitted() {
        return isCellularUpstreamPermitted(this.mFetcher.fetchTetheringConfiguration());
    }

    private boolean isCellularUpstreamPermitted(TetheringConfiguration tetheringConfiguration) {
        if (isTetherProvisioningRequired(tetheringConfiguration)) {
            return this.mCurrentDownstreams.isEmpty() ? !this.mExemptedDownstreams.isEmpty() : this.mCurrentEntitlementResults.indexOfValue(0) > -1;
        }
        return true;
    }

    public void setExemptedDownstreamType(int i) {
        this.mExemptedDownstreams.set(i, true);
    }

    public void startProvisioningIfNeeded(int i, boolean z) {
        if (isValidDownstreamType(i)) {
            this.mCurrentDownstreams.set(i, true);
            this.mExemptedDownstreams.set(i, false);
            TetheringConfiguration tetheringConfigurationFetchTetheringConfiguration = this.mFetcher.fetchTetheringConfiguration();
            if (isTetherProvisioningRequired(tetheringConfigurationFetchTetheringConfiguration)) {
                if (this.mUsingCellularAsUpstream) {
                    if (z) {
                        runUiTetherProvisioning(i, tetheringConfigurationFetchTetheringConfiguration);
                    } else {
                        runSilentTetherProvisioning(i, tetheringConfigurationFetchTetheringConfiguration);
                    }
                    this.mNeedReRunProvisioningUi = false;
                    return;
                }
                this.mNeedReRunProvisioningUi |= z;
            }
        }
    }

    public void stopProvisioningIfNeeded(int i) {
        if (isValidDownstreamType(i)) {
            this.mCurrentDownstreams.set(i, false);
            removeDownstreamMapping(i);
            this.mExemptedDownstreams.set(i, false);
        }
    }

    public void notifyUpstream(boolean z) {
        this.mUsingCellularAsUpstream = z;
        if (z) {
            maybeRunProvisioning(this.mFetcher.fetchTetheringConfiguration());
        }
    }

    public void maybeRunProvisioning() {
        maybeRunProvisioning(this.mFetcher.fetchTetheringConfiguration());
    }

    private void maybeRunProvisioning(TetheringConfiguration tetheringConfiguration) {
        if (this.mCurrentDownstreams.isEmpty() || !isTetherProvisioningRequired(tetheringConfiguration)) {
            return;
        }
        int iNextSetBit = this.mCurrentDownstreams.nextSetBit(0);
        while (iNextSetBit >= 0) {
            if (this.mCurrentEntitlementResults.indexOfKey(iNextSetBit) < 0) {
                if (this.mNeedReRunProvisioningUi) {
                    this.mNeedReRunProvisioningUi = false;
                    runUiTetherProvisioning(iNextSetBit, tetheringConfiguration);
                } else {
                    runSilentTetherProvisioning(iNextSetBit, tetheringConfiguration);
                }
            }
            iNextSetBit = this.mCurrentDownstreams.nextSetBit(iNextSetBit + 1);
        }
    }

    protected boolean isTetherProvisioningRequired(TetheringConfiguration tetheringConfiguration) {
        return (SystemProperties.getBoolean(DISABLE_PROVISIONING_SYSPROP_KEY, false) || tetheringConfiguration.provisioningApp.length == 0 || carrierConfigAffirmsEntitlementCheckNotRequired(tetheringConfiguration) || tetheringConfiguration.provisioningApp.length != 2) ? false : true;
    }

    public void reevaluateSimCardProvisioning(TetheringConfiguration tetheringConfiguration) {
        if (!this.mHandler.getLooper().isCurrentThread()) {
            this.mLog.log("reevaluateSimCardProvisioning() don't run in TetherMaster thread");
        }
        this.mEntitlementCacheValue.clear();
        this.mCurrentEntitlementResults.clear();
        if (!tetheringConfiguration.hasMobileHotspotProvisionApp() || carrierConfigAffirmsEntitlementCheckNotRequired(tetheringConfiguration)) {
            evaluateCellularPermission(tetheringConfiguration);
        } else if (this.mUsingCellularAsUpstream) {
            maybeRunProvisioning(tetheringConfiguration);
        }
    }

    public PersistableBundle getCarrierConfig(TetheringConfiguration tetheringConfiguration) {
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) this.mContext.getSystemService("carrier_config");
        if (carrierConfigManager == null) {
            return null;
        }
        PersistableBundle configForSubId = carrierConfigManager.getConfigForSubId(tetheringConfiguration.activeDataSubId);
        if (CarrierConfigManager.isConfigForIdentifiedCarrier(configForSubId)) {
            return configForSubId;
        }
        return null;
    }

    private boolean carrierConfigAffirmsEntitlementCheckNotRequired(TetheringConfiguration tetheringConfiguration) {
        if (getCarrierConfig(tetheringConfiguration) == null) {
            return false;
        }
        return !r0.getBoolean("require_entitlement_checks_bool");
    }

    protected Intent runSilentTetherProvisioning(int i, TetheringConfiguration tetheringConfiguration) {
        ResultReceiver resultReceiverBuildProxyReceiver = buildProxyReceiver(i, false, null);
        Intent intent = new Intent();
        intent.putExtra("extraAddTetherType", i);
        intent.putExtra("extraRunProvision", true);
        intent.putExtra("android.net.extra.TETHER_SILENT_PROVISIONING_ACTION", tetheringConfiguration.provisioningAppNoUi);
        intent.putExtra("android.net.extra.TETHER_PROVISIONING_RESPONSE", tetheringConfiguration.provisioningResponse);
        intent.putExtra("extraProvisionCallback", resultReceiverBuildProxyReceiver);
        intent.putExtra("android.net.extra.TETHER_SUBID", tetheringConfiguration.activeDataSubId);
        intent.setComponent(this.mSilentProvisioningService);
        this.mContext.startService(intent);
        return intent;
    }

    private void runUiTetherProvisioning(int i, TetheringConfiguration tetheringConfiguration) {
        runUiTetherProvisioning(i, tetheringConfiguration, buildProxyReceiver(i, true, null));
    }

    protected Intent runUiTetherProvisioning(int i, TetheringConfiguration tetheringConfiguration, ResultReceiver resultReceiver) {
        Intent intent = new Intent("android.settings.TETHER_PROVISIONING_UI");
        intent.putExtra("extraAddTetherType", i);
        intent.putExtra("android.net.extra.TETHER_UI_PROVISIONING_APP_NAME", tetheringConfiguration.provisioningApp);
        intent.putExtra("extraProvisionCallback", resultReceiver);
        intent.putExtra("android.net.extra.TETHER_SUBID", tetheringConfiguration.activeDataSubId);
        intent.addFlags(268435456);
        this.mContext.startActivity(intent);
        return intent;
    }

    private void scheduleProvisioningRechecks(TetheringConfiguration tetheringConfiguration) {
        int i;
        if (this.mProvisioningRecheckAlarm != null || (i = tetheringConfiguration.provisioningCheckPeriod) <= 0) {
            return;
        }
        this.mProvisioningRecheckAlarm = PendingIntent.getBroadcast(this.mContext, 0, new Intent("com.android.networkstack.tethering.PROVISIONING_RECHECK_ALARM"), 0);
        long j = i * 3600000;
        ((AlarmManager) this.mContext.getSystemService("alarm")).setRepeating(3, SystemClock.elapsedRealtime() + j, j, this.mProvisioningRecheckAlarm);
    }

    private void cancelTetherProvisioningRechecks() {
        if (this.mProvisioningRecheckAlarm != null) {
            ((AlarmManager) this.mContext.getSystemService("alarm")).cancel(this.mProvisioningRecheckAlarm);
            this.mProvisioningRecheckAlarm = null;
        }
    }

    private void evaluateCellularPermission(TetheringConfiguration tetheringConfiguration) {
        boolean zIsCellularUpstreamPermitted = isCellularUpstreamPermitted(tetheringConfiguration);
        if (this.mLastCellularUpstreamPermitted != zIsCellularUpstreamPermitted) {
            this.mLog.log("Cellular permission change: " + zIsCellularUpstreamPermitted);
            this.mPermissionChangeCallback.run();
        }
        if (zIsCellularUpstreamPermitted && this.mCurrentEntitlementResults.size() > 0) {
            scheduleProvisioningRechecks(tetheringConfiguration);
        } else {
            cancelTetherProvisioningRechecks();
        }
        this.mLastCellularUpstreamPermitted = zIsCellularUpstreamPermitted;
    }

    protected void addDownstreamMapping(int i, int i2) {
        this.mLog.i("addDownstreamMapping: " + i + ", result: " + i2 + " ,TetherTypeRequested: " + this.mCurrentDownstreams.get(i));
        if (this.mCurrentDownstreams.get(i)) {
            this.mCurrentEntitlementResults.put(i, i2);
            evaluateCellularPermission(this.mFetcher.fetchTetheringConfiguration());
        }
    }

    protected void removeDownstreamMapping(int i) {
        this.mLog.i("removeDownstreamMapping: " + i);
        this.mCurrentEntitlementResults.delete(i);
        evaluateCellularPermission(this.mFetcher.fetchTetheringConfiguration());
    }

    public void dump(final PrintWriter printWriter) {
        final ConditionVariable conditionVariable = new ConditionVariable();
        this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$EntitlementManager$HcBAVdNSx9qrFhZ1BFKh4-2Rt-g
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dump$0$EntitlementManager(printWriter, conditionVariable);
            }
        });
        if (!conditionVariable.block(10000L)) {
            printWriter.println("... dump timed out after 10000ms");
        }
        printWriter.print("Exempted: [");
        int iNextSetBit = this.mExemptedDownstreams.nextSetBit(0);
        while (iNextSetBit >= 0) {
            printWriter.print(typeString(iNextSetBit));
            printWriter.print(", ");
            iNextSetBit = this.mExemptedDownstreams.nextSetBit(iNextSetBit + 1);
        }
        printWriter.println("]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$dump$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$dump$0$EntitlementManager(PrintWriter printWriter, ConditionVariable conditionVariable) {
        printWriter.print("isCellularUpstreamPermitted: ");
        printWriter.println(isCellularUpstreamPermitted());
        int iNextSetBit = this.mCurrentDownstreams.nextSetBit(0);
        while (iNextSetBit >= 0) {
            printWriter.print("Type: ");
            printWriter.print(typeString(iNextSetBit));
            if (this.mCurrentEntitlementResults.indexOfKey(iNextSetBit) > -1) {
                printWriter.print(", Value: ");
                printWriter.println(errorString(this.mCurrentEntitlementResults.get(iNextSetBit)));
            } else {
                printWriter.println(", Value: empty");
            }
            iNextSetBit = this.mCurrentDownstreams.nextSetBit(iNextSetBit + 1);
        }
        conditionVariable.open();
    }

    private static String typeString(int i) {
        return i != -1 ? i != 0 ? i != 1 ? i != 2 ? String.format("TETHERING UNKNOWN TYPE (%d)", Integer.valueOf(i)) : "TETHERING_BLUETOOTH" : "TETHERING_USB" : "TETHERING_WIFI" : "TETHERING_INVALID";
    }

    private static String errorString(int i) {
        return i != 0 ? i != 11 ? i != 13 ? String.format("UNKNOWN ERROR (%d)", Integer.valueOf(i)) : "TETHER_ERROR_ENTITLEMENT_UNKONWN" : "TETHER_ERROR_PROVISIONING_FAILED" : "TETHER_ERROR_NO_ERROR";
    }

    private ResultReceiver buildProxyReceiver(final int i, final boolean z, final ResultReceiver resultReceiver) {
        return writeToParcel(new ResultReceiver(this.mHandler) { // from class: com.android.networkstack.tethering.EntitlementManager.2
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i2, Bundle bundle) {
                int iUpdateEntitlementCacheValue = EntitlementManager.this.updateEntitlementCacheValue(i, i2);
                EntitlementManager.this.addDownstreamMapping(i, iUpdateEntitlementCacheValue);
                if (iUpdateEntitlementCacheValue == 11 && z) {
                    EntitlementManager.this.mListener.onUiEntitlementFailed(i);
                }
                ResultReceiver resultReceiver2 = resultReceiver;
                if (resultReceiver2 != null) {
                    resultReceiver2.send(iUpdateEntitlementCacheValue, null);
                }
            }
        });
    }

    private ResultReceiver writeToParcel(ResultReceiver resultReceiver) {
        Parcel parcelObtain = Parcel.obtain();
        resultReceiver.writeToParcel(parcelObtain, 0);
        parcelObtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return resultReceiver2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int updateEntitlementCacheValue(int i, int i2) {
        if (i2 == 0) {
            this.mEntitlementCacheValue.put(i, i2);
            return i2;
        }
        this.mEntitlementCacheValue.put(i, 11);
        return 11;
    }

    public void requestLatestTetheringEntitlementResult(int i, ResultReceiver resultReceiver, boolean z) {
        if (!isValidDownstreamType(i)) {
            resultReceiver.send(13, null);
            return;
        }
        TetheringConfiguration tetheringConfigurationFetchTetheringConfiguration = this.mFetcher.fetchTetheringConfiguration();
        if (!isTetherProvisioningRequired(tetheringConfigurationFetchTetheringConfiguration)) {
            resultReceiver.send(0, null);
            return;
        }
        int i2 = this.mEntitlementCacheValue.get(i, 13);
        if (i2 == 0 || !z) {
            resultReceiver.send(i2, null);
        } else {
            runUiTetherProvisioning(i, tetheringConfigurationFetchTetheringConfiguration, buildProxyReceiver(i, false, resultReceiver));
        }
    }
}
