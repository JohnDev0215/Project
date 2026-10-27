package a.d.a.a.f;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;

/* JADX INFO: compiled from: IMediaSession.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: IMediaSession.java */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: a.d.a.a.f.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IMediaSession.java */
        public static class C0005a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f58a;

            public C0005a(IBinder iBinder) {
                this.f58a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f58a;
            }

            @Override // a.d.a.a.f.b
            public boolean c0(KeyEvent keyEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (keyEvent != null) {
                        parcelObtain.writeInt(1);
                        keyEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.f58a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // a.d.a.a.f.b
            public void r(a.d.a.a.f.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    this.f58a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static b d0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0005a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }
    }

    boolean c0(KeyEvent keyEvent) throws RemoteException;

    void r(a.d.a.a.f.a aVar) throws RemoteException;
}
