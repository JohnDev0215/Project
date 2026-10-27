package com.google.android.gms.cloudmessaging;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cloud-messaging@@17.0.0 */
/* JADX INFO: loaded from: classes6.dex */
interface IMessengerCompat extends IInterface {
    public static final String DESCRIPTOR = "com.google.android.gms.iid.IMessengerCompat";
    public static final int TRANSACTION_SEND = 1;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cloud-messaging@@17.0.0 */
    public static class Impl extends Binder implements IMessengerCompat {
        @Override // android.os.IInterface
        @NonNull
        public IBinder asBinder() {
            throw null;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, @NonNull Parcel parcel, @Nullable Parcel parcel2, int i2) throws RemoteException {
            throw null;
        }

        @Override // com.google.android.gms.cloudmessaging.IMessengerCompat
        public void send(@NonNull Message message) throws RemoteException {
            throw null;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cloud-messaging@@17.0.0 */
    public static class Proxy implements IMessengerCompat {
        private final IBinder zza;

        Proxy(@NonNull IBinder iBinder) {
            this.zza = iBinder;
        }

        @Override // android.os.IInterface
        @NonNull
        public IBinder asBinder() {
            return this.zza;
        }

        @Override // com.google.android.gms.cloudmessaging.IMessengerCompat
        public void send(@NonNull Message message) throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            parcelObtain.writeInterfaceToken(IMessengerCompat.DESCRIPTOR);
            parcelObtain.writeInt(1);
            message.writeToParcel(parcelObtain, 0);
            try {
                this.zza.transact(1, parcelObtain, null, 1);
            } finally {
                parcelObtain.recycle();
            }
        }
    }

    void send(@NonNull Message message) throws RemoteException;
}
