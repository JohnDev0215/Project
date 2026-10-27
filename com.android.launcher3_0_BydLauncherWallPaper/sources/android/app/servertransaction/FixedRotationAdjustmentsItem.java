package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.DisplayAdjustments;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class FixedRotationAdjustmentsItem extends ClientTransactionItem {
    public static final Parcelable.Creator<FixedRotationAdjustmentsItem> CREATOR = new Parcelable.Creator<FixedRotationAdjustmentsItem>() { // from class: android.app.servertransaction.FixedRotationAdjustmentsItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FixedRotationAdjustmentsItem createFromParcel(Parcel parcel) {
            return new FixedRotationAdjustmentsItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FixedRotationAdjustmentsItem[] newArray(int i) {
            return new FixedRotationAdjustmentsItem[i];
        }
    };
    private DisplayAdjustments.FixedRotationAdjustments mFixedRotationAdjustments;
    private IBinder mToken;

    private FixedRotationAdjustmentsItem() {
    }

    public static FixedRotationAdjustmentsItem obtain(IBinder iBinder, DisplayAdjustments.FixedRotationAdjustments fixedRotationAdjustments) {
        FixedRotationAdjustmentsItem fixedRotationAdjustmentsItem = (FixedRotationAdjustmentsItem) ObjectPool.obtain(FixedRotationAdjustmentsItem.class);
        if (fixedRotationAdjustmentsItem == null) {
            fixedRotationAdjustmentsItem = new FixedRotationAdjustmentsItem();
        }
        fixedRotationAdjustmentsItem.mToken = iBinder;
        fixedRotationAdjustmentsItem.mFixedRotationAdjustments = fixedRotationAdjustments;
        return fixedRotationAdjustmentsItem;
    }

    @Override // android.app.servertransaction.BaseClientRequest
    public void execute(ClientTransactionHandler clientTransactionHandler, IBinder iBinder, PendingTransactionActions pendingTransactionActions) {
        clientTransactionHandler.handleFixedRotationAdjustments(this.mToken, this.mFixedRotationAdjustments);
    }

    @Override // android.app.servertransaction.ObjectPoolItem
    public void recycle() {
        this.mToken = null;
        this.mFixedRotationAdjustments = null;
        ObjectPool.recycle(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.mToken);
        parcel.writeTypedObject(this.mFixedRotationAdjustments, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FixedRotationAdjustmentsItem fixedRotationAdjustmentsItem = (FixedRotationAdjustmentsItem) obj;
        return Objects.equals(this.mToken, fixedRotationAdjustmentsItem.mToken) && Objects.equals(this.mFixedRotationAdjustments, fixedRotationAdjustmentsItem.mFixedRotationAdjustments);
    }

    public int hashCode() {
        return ((527 + Objects.hashCode(this.mToken)) * 31) + Objects.hashCode(this.mFixedRotationAdjustments);
    }

    private FixedRotationAdjustmentsItem(Parcel parcel) {
        this.mToken = parcel.readStrongBinder();
        this.mFixedRotationAdjustments = (DisplayAdjustments.FixedRotationAdjustments) parcel.readTypedObject(DisplayAdjustments.FixedRotationAdjustments.CREATOR);
    }
}
