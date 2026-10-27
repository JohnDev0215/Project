package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;

/* JADX INFO: loaded from: classes.dex */
public class StopActivityItem extends ActivityLifecycleItem {
    public static final Parcelable.Creator<StopActivityItem> CREATOR = new Parcelable.Creator<StopActivityItem>() { // from class: android.app.servertransaction.StopActivityItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StopActivityItem createFromParcel(Parcel parcel) {
            return new StopActivityItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StopActivityItem[] newArray(int i) {
            return new StopActivityItem[i];
        }
    };
    private static final String TAG = "StopActivityItem";
    private int mConfigChanges;

    @Override // android.app.servertransaction.ActivityLifecycleItem
    public int getTargetState() {
        return 5;
    }

    @Override // android.app.servertransaction.BaseClientRequest
    public void execute(ClientTransactionHandler clientTransactionHandler, IBinder iBinder, PendingTransactionActions pendingTransactionActions) {
        Trace.traceBegin(64L, "activityStop");
        clientTransactionHandler.handleStopActivity(iBinder, this.mConfigChanges, pendingTransactionActions, true, "STOP_ACTIVITY_ITEM");
        Trace.traceEnd(64L);
    }

    @Override // android.app.servertransaction.BaseClientRequest
    public void postExecute(ClientTransactionHandler clientTransactionHandler, IBinder iBinder, PendingTransactionActions pendingTransactionActions) {
        clientTransactionHandler.reportStop(pendingTransactionActions);
    }

    private StopActivityItem() {
    }

    public static StopActivityItem obtain(int i) {
        StopActivityItem stopActivityItem = (StopActivityItem) ObjectPool.obtain(StopActivityItem.class);
        if (stopActivityItem == null) {
            stopActivityItem = new StopActivityItem();
        }
        stopActivityItem.mConfigChanges = i;
        return stopActivityItem;
    }

    @Override // android.app.servertransaction.ActivityLifecycleItem, android.app.servertransaction.ObjectPoolItem
    public void recycle() {
        super.recycle();
        this.mConfigChanges = 0;
        ObjectPool.recycle(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mConfigChanges);
    }

    private StopActivityItem(Parcel parcel) {
        this.mConfigChanges = parcel.readInt();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.mConfigChanges == ((StopActivityItem) obj).mConfigChanges;
    }

    public int hashCode() {
        return 527 + this.mConfigChanges;
    }

    public String toString() {
        return "StopActivityItem{configChanges=" + this.mConfigChanges + "}";
    }
}
