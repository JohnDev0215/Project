package android.app.servertransaction;

import android.app.ActivityTaskManager;
import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.Trace;

/* JADX INFO: loaded from: classes.dex */
public class TopResumedActivityChangeItem extends ClientTransactionItem {
    public static final Parcelable.Creator<TopResumedActivityChangeItem> CREATOR = new Parcelable.Creator<TopResumedActivityChangeItem>() { // from class: android.app.servertransaction.TopResumedActivityChangeItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TopResumedActivityChangeItem createFromParcel(Parcel parcel) {
            return new TopResumedActivityChangeItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TopResumedActivityChangeItem[] newArray(int i) {
            return new TopResumedActivityChangeItem[i];
        }
    };
    private boolean mOnTop;

    @Override // android.app.servertransaction.BaseClientRequest
    public void execute(ClientTransactionHandler clientTransactionHandler, IBinder iBinder, PendingTransactionActions pendingTransactionActions) {
        Trace.traceBegin(64L, "topResumedActivityChangeItem");
        clientTransactionHandler.handleTopResumedActivityChanged(iBinder, this.mOnTop, "topResumedActivityChangeItem");
        Trace.traceEnd(64L);
    }

    @Override // android.app.servertransaction.BaseClientRequest
    public void postExecute(ClientTransactionHandler clientTransactionHandler, IBinder iBinder, PendingTransactionActions pendingTransactionActions) {
        if (this.mOnTop) {
            return;
        }
        try {
            ActivityTaskManager.getService().activityTopResumedStateLost();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private TopResumedActivityChangeItem() {
    }

    public static TopResumedActivityChangeItem obtain(boolean z) {
        TopResumedActivityChangeItem topResumedActivityChangeItem = (TopResumedActivityChangeItem) ObjectPool.obtain(TopResumedActivityChangeItem.class);
        if (topResumedActivityChangeItem == null) {
            topResumedActivityChangeItem = new TopResumedActivityChangeItem();
        }
        topResumedActivityChangeItem.mOnTop = z;
        return topResumedActivityChangeItem;
    }

    @Override // android.app.servertransaction.ObjectPoolItem
    public void recycle() {
        this.mOnTop = false;
        ObjectPool.recycle(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(this.mOnTop);
    }

    private TopResumedActivityChangeItem(Parcel parcel) {
        this.mOnTop = parcel.readBoolean();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.mOnTop == ((TopResumedActivityChangeItem) obj).mOnTop;
    }

    public int hashCode() {
        return 527 + (this.mOnTop ? 1 : 0);
    }

    public String toString() {
        return "TopResumedActivityChangeItem{onTop=" + this.mOnTop + "}";
    }
}
