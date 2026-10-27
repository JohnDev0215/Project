package org.chromium.webapk.lib.runtime_library;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public interface IWebApkApi extends IInterface {
    public static final String DESCRIPTOR = "org.chromium.webapk.lib.runtime_library.IWebApkApi";

    /* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
    public class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static Object readTypedObject(Parcel parcel, Parcelable.Creator creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void writeTypedObject(Parcel parcel, Parcelable parcelable, int i) {
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                parcelable.writeToParcel(parcel, i);
            }
        }
    }

    void cancelNotification(String str, int i);

    int checkNotificationPermission();

    boolean finishAndRemoveTaskSdk23();

    int getSmallIconId();

    boolean notificationPermissionEnabled();

    void notifyNotification(String str, int i, Notification notification);

    void notifyNotificationWithChannel(String str, int i, Notification notification, String str2);

    PendingIntent requestNotificationPermission(String str, String str2);

    /* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
    public class Default implements IWebApkApi {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
        public int checkNotificationPermission() {
            return 0;
        }

        @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
        public boolean finishAndRemoveTaskSdk23() {
            return false;
        }

        @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
        public int getSmallIconId() {
            return 0;
        }

        @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
        public boolean notificationPermissionEnabled() {
            return false;
        }

        @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
        public void notifyNotificationWithChannel(String str, int i, Notification notification, String str2) {
        }

        @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
        public PendingIntent requestNotificationPermission(String str, String str2) {
            return null;
        }

        @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
        public void notifyNotification(String str, int i, Notification notification) {
        }

        @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
        public void cancelNotification(String str, int i) {
        }
    }

    /* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
    public abstract class Stub extends Binder implements IWebApkApi {
        static final int TRANSACTION_cancelNotification = 3;
        static final int TRANSACTION_checkNotificationPermission = 7;
        static final int TRANSACTION_finishAndRemoveTaskSdk23 = 6;
        static final int TRANSACTION_getSmallIconId = 1;
        static final int TRANSACTION_notificationPermissionEnabled = 4;
        static final int TRANSACTION_notifyNotification = 2;
        static final int TRANSACTION_notifyNotificationWithChannel = 5;
        static final int TRANSACTION_requestNotificationPermission = 8;

        /* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
        class Proxy implements IWebApkApi {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
            public void cancelNotification(String str, int i) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IWebApkApi.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
            public int checkNotificationPermission() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IWebApkApi.DESCRIPTOR);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
            public boolean finishAndRemoveTaskSdk23() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IWebApkApi.DESCRIPTOR);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IWebApkApi.DESCRIPTOR;
            }

            @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
            public int getSmallIconId() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IWebApkApi.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
            public boolean notificationPermissionEnabled() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IWebApkApi.DESCRIPTOR);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
            public void notifyNotification(String str, int i, Notification notification) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IWebApkApi.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, notification, 0);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
            public void notifyNotificationWithChannel(String str, int i, Notification notification, String str2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IWebApkApi.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    _Parcel.writeTypedObject(parcelObtain, notification, 0);
                    parcelObtain.writeString(str2);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // org.chromium.webapk.lib.runtime_library.IWebApkApi
            public PendingIntent requestNotificationPermission(String str, String str2) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IWebApkApi.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (PendingIntent) _Parcel.readTypedObject(parcelObtain2, PendingIntent.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IWebApkApi.DESCRIPTOR);
        }

        public static IWebApkApi asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IWebApkApi.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IWebApkApi)) ? new Proxy(iBinder) : (IWebApkApi) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int smallIconId;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IWebApkApi.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IWebApkApi.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    smallIconId = getSmallIconId();
                    parcel2.writeNoException();
                    parcel2.writeInt(smallIconId);
                    return true;
                case 2:
                    notifyNotification(parcel.readString(), parcel.readInt(), (Notification) _Parcel.readTypedObject(parcel, Notification.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    cancelNotification(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    smallIconId = notificationPermissionEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(smallIconId);
                    return true;
                case 5:
                    notifyNotificationWithChannel(parcel.readString(), parcel.readInt(), (Notification) _Parcel.readTypedObject(parcel, Notification.CREATOR), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    smallIconId = finishAndRemoveTaskSdk23();
                    parcel2.writeNoException();
                    parcel2.writeInt(smallIconId);
                    return true;
                case 7:
                    smallIconId = checkNotificationPermission();
                    parcel2.writeNoException();
                    parcel2.writeInt(smallIconId);
                    return true;
                case 8:
                    PendingIntent pendingIntentRequestNotificationPermission = requestNotificationPermission(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, pendingIntentRequestNotificationPermission, 1);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }
    }
}
