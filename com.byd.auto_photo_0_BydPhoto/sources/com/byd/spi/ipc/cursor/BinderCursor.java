package com.byd.spi.ipc.cursor;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class BinderCursor extends MatrixCursor {
    static final String KEY_BINDER = "binder";
    private Bundle mBinderExtra;

    public static class BinderParcelable implements Parcelable {
        public static final Parcelable.Creator<BinderParcelable> CREATOR = new Parcelable.Creator<BinderParcelable>() { // from class: com.byd.spi.ipc.cursor.BinderCursor.BinderParcelable.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BinderParcelable createFromParcel(Parcel parcel) {
                return new BinderParcelable(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BinderParcelable[] newArray(int i) {
                return new BinderParcelable[i];
            }
        };
        private IBinder mBinder;

        BinderParcelable(IBinder iBinder) {
            this.mBinder = iBinder;
        }

        BinderParcelable(Parcel parcel) {
            this.mBinder = parcel.readStrongBinder();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public IBinder getBinder() {
            return this.mBinder;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeStrongBinder(this.mBinder);
        }
    }

    public BinderCursor(String[] strArr, IBinder iBinder) {
        super(strArr);
        this.mBinderExtra = new Bundle();
        if (iBinder != null) {
            this.mBinderExtra.putParcelable(KEY_BINDER, new BinderParcelable(iBinder));
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public Bundle getExtras() {
        return this.mBinderExtra;
    }
}
