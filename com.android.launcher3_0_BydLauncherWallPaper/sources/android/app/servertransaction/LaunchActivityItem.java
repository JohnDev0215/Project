package android.app.servertransaction;

import android.app.ActivityThread;
import android.app.ClientTransactionHandler;
import android.app.ProfilerInfo;
import android.app.ResultInfo;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.os.BaseBundle;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.Trace;
import android.view.DisplayAdjustments;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.content.ReferrerIntent;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class LaunchActivityItem extends ClientTransactionItem {
    public static final Parcelable.Creator<LaunchActivityItem> CREATOR = new Parcelable.Creator<LaunchActivityItem>() { // from class: android.app.servertransaction.LaunchActivityItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LaunchActivityItem createFromParcel(Parcel parcel) {
            return new LaunchActivityItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LaunchActivityItem[] newArray(int i) {
            return new LaunchActivityItem[i];
        }
    };
    private IBinder mAssistToken;
    private CompatibilityInfo mCompatInfo;
    private Configuration mCurConfig;
    private DisplayAdjustments.FixedRotationAdjustments mFixedRotationAdjustments;
    private int mIdent;
    private ActivityInfo mInfo;
    private Intent mIntent;
    private boolean mIsForward;
    private Configuration mOverrideConfig;
    private List<ReferrerIntent> mPendingNewIntents;
    private List<ResultInfo> mPendingResults;
    private PersistableBundle mPersistentState;
    private int mProcState;
    private ProfilerInfo mProfilerInfo;
    private String mReferrer;
    private Bundle mState;
    private IVoiceInteractor mVoiceInteractor;

    @Override // android.app.servertransaction.BaseClientRequest
    public void preExecute(ClientTransactionHandler clientTransactionHandler, IBinder iBinder) {
        clientTransactionHandler.countLaunchingActivities(1);
        clientTransactionHandler.updateProcessState(this.mProcState, false);
        clientTransactionHandler.updatePendingConfiguration(this.mCurConfig);
    }

    @Override // android.app.servertransaction.BaseClientRequest
    public void execute(ClientTransactionHandler clientTransactionHandler, IBinder iBinder, PendingTransactionActions pendingTransactionActions) {
        Trace.traceBegin(64L, "activityStart");
        clientTransactionHandler.handleLaunchActivity(new ActivityThread.ActivityClientRecord(iBinder, this.mIntent, this.mIdent, this.mInfo, this.mOverrideConfig, this.mCompatInfo, this.mReferrer, this.mVoiceInteractor, this.mState, this.mPersistentState, this.mPendingResults, this.mPendingNewIntents, this.mIsForward, this.mProfilerInfo, clientTransactionHandler, this.mAssistToken, this.mFixedRotationAdjustments), pendingTransactionActions, null);
        Trace.traceEnd(64L);
    }

    @Override // android.app.servertransaction.BaseClientRequest
    public void postExecute(ClientTransactionHandler clientTransactionHandler, IBinder iBinder, PendingTransactionActions pendingTransactionActions) {
        clientTransactionHandler.countLaunchingActivities(-1);
    }

    private LaunchActivityItem() {
    }

    public static LaunchActivityItem obtain(Intent intent, int i, ActivityInfo activityInfo, Configuration configuration, Configuration configuration2, CompatibilityInfo compatibilityInfo, String str, IVoiceInteractor iVoiceInteractor, int i2, Bundle bundle, PersistableBundle persistableBundle, List<ResultInfo> list, List<ReferrerIntent> list2, boolean z, ProfilerInfo profilerInfo, IBinder iBinder, DisplayAdjustments.FixedRotationAdjustments fixedRotationAdjustments) {
        LaunchActivityItem launchActivityItem = (LaunchActivityItem) ObjectPool.obtain(LaunchActivityItem.class);
        if (launchActivityItem == null) {
            launchActivityItem = new LaunchActivityItem();
        }
        setValues(launchActivityItem, intent, i, activityInfo, configuration, configuration2, compatibilityInfo, str, iVoiceInteractor, i2, bundle, persistableBundle, list, list2, z, profilerInfo, iBinder, fixedRotationAdjustments);
        return launchActivityItem;
    }

    @Override // android.app.servertransaction.ObjectPoolItem
    public void recycle() {
        setValues(this, null, 0, null, null, null, null, null, null, 0, null, null, null, null, false, null, null, null);
        ObjectPool.recycle(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedObject(this.mIntent, i);
        parcel.writeInt(this.mIdent);
        parcel.writeTypedObject(this.mInfo, i);
        parcel.writeTypedObject(this.mCurConfig, i);
        parcel.writeTypedObject(this.mOverrideConfig, i);
        parcel.writeTypedObject(this.mCompatInfo, i);
        parcel.writeString(this.mReferrer);
        parcel.writeStrongInterface(this.mVoiceInteractor);
        parcel.writeInt(this.mProcState);
        parcel.writeBundle(this.mState);
        parcel.writePersistableBundle(this.mPersistentState);
        parcel.writeTypedList(this.mPendingResults, i);
        parcel.writeTypedList(this.mPendingNewIntents, i);
        parcel.writeBoolean(this.mIsForward);
        parcel.writeTypedObject(this.mProfilerInfo, i);
        parcel.writeStrongBinder(this.mAssistToken);
        parcel.writeTypedObject(this.mFixedRotationAdjustments, i);
    }

    private LaunchActivityItem(Parcel parcel) {
        setValues(this, (Intent) parcel.readTypedObject(Intent.CREATOR), parcel.readInt(), (ActivityInfo) parcel.readTypedObject(ActivityInfo.CREATOR), (Configuration) parcel.readTypedObject(Configuration.CREATOR), (Configuration) parcel.readTypedObject(Configuration.CREATOR), (CompatibilityInfo) parcel.readTypedObject(CompatibilityInfo.CREATOR), parcel.readString(), IVoiceInteractor.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readBundle(getClass().getClassLoader()), parcel.readPersistableBundle(getClass().getClassLoader()), parcel.createTypedArrayList(ResultInfo.CREATOR), parcel.createTypedArrayList(ReferrerIntent.CREATOR), parcel.readBoolean(), (ProfilerInfo) parcel.readTypedObject(ProfilerInfo.CREATOR), parcel.readStrongBinder(), (DisplayAdjustments.FixedRotationAdjustments) parcel.readTypedObject(DisplayAdjustments.FixedRotationAdjustments.CREATOR));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LaunchActivityItem launchActivityItem = (LaunchActivityItem) obj;
        Intent intent = this.mIntent;
        return ((intent == null && launchActivityItem.mIntent == null) || (intent != null && intent.filterEquals(launchActivityItem.mIntent))) && this.mIdent == launchActivityItem.mIdent && activityInfoEqual(launchActivityItem.mInfo) && Objects.equals(this.mCurConfig, launchActivityItem.mCurConfig) && Objects.equals(this.mOverrideConfig, launchActivityItem.mOverrideConfig) && Objects.equals(this.mCompatInfo, launchActivityItem.mCompatInfo) && Objects.equals(this.mReferrer, launchActivityItem.mReferrer) && this.mProcState == launchActivityItem.mProcState && areBundlesEqualRoughly(this.mState, launchActivityItem.mState) && areBundlesEqualRoughly(this.mPersistentState, launchActivityItem.mPersistentState) && Objects.equals(this.mPendingResults, launchActivityItem.mPendingResults) && Objects.equals(this.mPendingNewIntents, launchActivityItem.mPendingNewIntents) && this.mIsForward == launchActivityItem.mIsForward && Objects.equals(this.mProfilerInfo, launchActivityItem.mProfilerInfo) && Objects.equals(this.mAssistToken, launchActivityItem.mAssistToken) && Objects.equals(this.mFixedRotationAdjustments, launchActivityItem.mFixedRotationAdjustments);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((527 + this.mIntent.filterHashCode()) * 31) + this.mIdent) * 31) + Objects.hashCode(this.mCurConfig)) * 31) + Objects.hashCode(this.mOverrideConfig)) * 31) + Objects.hashCode(this.mCompatInfo)) * 31) + Objects.hashCode(this.mReferrer)) * 31) + Objects.hashCode(Integer.valueOf(this.mProcState))) * 31) + getRoughBundleHashCode(this.mState)) * 31) + getRoughBundleHashCode(this.mPersistentState)) * 31) + Objects.hashCode(this.mPendingResults)) * 31) + Objects.hashCode(this.mPendingNewIntents)) * 31) + (this.mIsForward ? 1 : 0)) * 31) + Objects.hashCode(this.mProfilerInfo)) * 31) + Objects.hashCode(this.mAssistToken)) * 31) + Objects.hashCode(this.mFixedRotationAdjustments);
    }

    private boolean activityInfoEqual(ActivityInfo activityInfo) {
        ActivityInfo activityInfo2 = this.mInfo;
        return activityInfo2 == null ? activityInfo == null : activityInfo != null && activityInfo2.flags == activityInfo.flags && this.mInfo.maxAspectRatio == activityInfo.maxAspectRatio && Objects.equals(this.mInfo.launchToken, activityInfo.launchToken) && Objects.equals(this.mInfo.getComponentName(), activityInfo.getComponentName());
    }

    private static int getRoughBundleHashCode(BaseBundle baseBundle) {
        return (baseBundle == null || baseBundle.isDefinitelyEmpty()) ? 0 : 1;
    }

    private static boolean areBundlesEqualRoughly(BaseBundle baseBundle, BaseBundle baseBundle2) {
        return getRoughBundleHashCode(baseBundle) == getRoughBundleHashCode(baseBundle2);
    }

    public String toString() {
        return "LaunchActivityItem{intent=" + this.mIntent + ",ident=" + this.mIdent + ",info=" + this.mInfo + ",curConfig=" + this.mCurConfig + ",overrideConfig=" + this.mOverrideConfig + ",referrer=" + this.mReferrer + ",procState=" + this.mProcState + ",state=" + this.mState + ",persistentState=" + this.mPersistentState + ",pendingResults=" + this.mPendingResults + ",pendingNewIntents=" + this.mPendingNewIntents + ",profilerInfo=" + this.mProfilerInfo + ",assistToken=" + this.mAssistToken + ",rotationAdj=" + this.mFixedRotationAdjustments + "}";
    }

    private static void setValues(LaunchActivityItem launchActivityItem, Intent intent, int i, ActivityInfo activityInfo, Configuration configuration, Configuration configuration2, CompatibilityInfo compatibilityInfo, String str, IVoiceInteractor iVoiceInteractor, int i2, Bundle bundle, PersistableBundle persistableBundle, List<ResultInfo> list, List<ReferrerIntent> list2, boolean z, ProfilerInfo profilerInfo, IBinder iBinder, DisplayAdjustments.FixedRotationAdjustments fixedRotationAdjustments) {
        launchActivityItem.mIntent = intent;
        launchActivityItem.mIdent = i;
        launchActivityItem.mInfo = activityInfo;
        launchActivityItem.mCurConfig = configuration;
        launchActivityItem.mOverrideConfig = configuration2;
        launchActivityItem.mCompatInfo = compatibilityInfo;
        launchActivityItem.mReferrer = str;
        launchActivityItem.mVoiceInteractor = iVoiceInteractor;
        launchActivityItem.mProcState = i2;
        launchActivityItem.mState = bundle;
        launchActivityItem.mPersistentState = persistableBundle;
        launchActivityItem.mPendingResults = list;
        launchActivityItem.mPendingNewIntents = list2;
        launchActivityItem.mIsForward = z;
        launchActivityItem.mProfilerInfo = profilerInfo;
        launchActivityItem.mAssistToken = iBinder;
        launchActivityItem.mFixedRotationAdjustments = fixedRotationAdjustments;
    }
}
