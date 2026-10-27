package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class EnterPipRequestedItem extends ClientTransactionItem {
    public static final Parcelable.Creator<EnterPipRequestedItem> CREATOR = new Parcelable.Creator<EnterPipRequestedItem>() { // from class: android.app.servertransaction.EnterPipRequestedItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EnterPipRequestedItem createFromParcel(Parcel parcel) {
            return new EnterPipRequestedItem();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EnterPipRequestedItem[] newArray(int i) {
            return new EnterPipRequestedItem[i];
        }
    };

    public boolean equals(Object obj) {
        return this == obj;
    }

    public String toString() {
        return "EnterPipRequestedItem{}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    @Override // android.app.servertransaction.BaseClientRequest
    public void execute(ClientTransactionHandler clientTransactionHandler, IBinder iBinder, PendingTransactionActions pendingTransactionActions) {
        clientTransactionHandler.handlePictureInPictureRequested(iBinder);
    }

    private EnterPipRequestedItem() {
    }

    public static EnterPipRequestedItem obtain() {
        EnterPipRequestedItem enterPipRequestedItem = (EnterPipRequestedItem) ObjectPool.obtain(EnterPipRequestedItem.class);
        return enterPipRequestedItem == null ? new EnterPipRequestedItem() : enterPipRequestedItem;
    }

    @Override // android.app.servertransaction.ObjectPoolItem
    public void recycle() {
        ObjectPool.recycle(this);
    }
}
