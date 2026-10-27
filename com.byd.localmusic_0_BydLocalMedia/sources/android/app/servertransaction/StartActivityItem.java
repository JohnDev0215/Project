package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;

/* JADX INFO: loaded from: classes.dex */
public class StartActivityItem extends ActivityLifecycleItem {
    public static final Parcelable.Creator<StartActivityItem> CREATOR = new Parcelable.Creator<StartActivityItem>() { // from class: android.app.servertransaction.StartActivityItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StartActivityItem createFromParcel(Parcel parcel) {
            return new StartActivityItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StartActivityItem[] newArray(int i) {
            return new StartActivityItem[i];
        }
    };
    private static final String TAG = "StartActivityItem";

    @Override // android.app.servertransaction.ActivityLifecycleItem
    public int getTargetState() {
        return 2;
    }

    public int hashCode() {
        return 17;
    }

    public String toString() {
        return "StartActivityItem{}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    @Override // android.app.servertransaction.BaseClientRequest
    public void execute(ClientTransactionHandler clientTransactionHandler, IBinder iBinder, PendingTransactionActions pendingTransactionActions) {
        Trace.traceBegin(64L, "startActivityItem");
        clientTransactionHandler.handleStartActivity(iBinder, pendingTransactionActions);
        Trace.traceEnd(64L);
    }

    private StartActivityItem() {
    }

    public static StartActivityItem obtain() {
        StartActivityItem startActivityItem = (StartActivityItem) ObjectPool.obtain(StartActivityItem.class);
        return startActivityItem == null ? new StartActivityItem() : startActivityItem;
    }

    @Override // android.app.servertransaction.ActivityLifecycleItem, android.app.servertransaction.ObjectPoolItem
    public void recycle() {
        super.recycle();
        ObjectPool.recycle(this);
    }

    private StartActivityItem(Parcel parcel) {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }
}
