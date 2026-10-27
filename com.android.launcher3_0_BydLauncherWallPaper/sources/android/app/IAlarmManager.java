package android.app;

import android.app.AlarmManager;
import android.app.IAlarmListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.WorkSource;

/* JADX INFO: loaded from: classes.dex */
public interface IAlarmManager extends IInterface {

    public static class Default implements IAlarmManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.IAlarmManager
        public long currentNetworkTimeMillis() throws RemoteException {
            return 0L;
        }

        @Override // android.app.IAlarmManager
        public AlarmManager.AlarmClockInfo getNextAlarmClock(int i) throws RemoteException {
            return null;
        }

        @Override // android.app.IAlarmManager
        public long getNextWakeFromIdleTime() throws RemoteException {
            return 0L;
        }

        @Override // android.app.IAlarmManager
        public void remove(PendingIntent pendingIntent, IAlarmListener iAlarmListener) throws RemoteException {
        }

        @Override // android.app.IAlarmManager
        public void set(String str, int i, long j, long j2, long j3, int i2, PendingIntent pendingIntent, IAlarmListener iAlarmListener, String str2, WorkSource workSource, AlarmManager.AlarmClockInfo alarmClockInfo) throws RemoteException {
        }

        @Override // android.app.IAlarmManager
        public boolean setTime(long j) throws RemoteException {
            return false;
        }

        @Override // android.app.IAlarmManager
        public void setTimeZone(String str) throws RemoteException {
        }
    }

    long currentNetworkTimeMillis() throws RemoteException;

    AlarmManager.AlarmClockInfo getNextAlarmClock(int i) throws RemoteException;

    long getNextWakeFromIdleTime() throws RemoteException;

    void remove(PendingIntent pendingIntent, IAlarmListener iAlarmListener) throws RemoteException;

    void set(String str, int i, long j, long j2, long j3, int i2, PendingIntent pendingIntent, IAlarmListener iAlarmListener, String str2, WorkSource workSource, AlarmManager.AlarmClockInfo alarmClockInfo) throws RemoteException;

    boolean setTime(long j) throws RemoteException;

    void setTimeZone(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IAlarmManager {
        private static final String DESCRIPTOR = "android.app.IAlarmManager";
        static final int TRANSACTION_currentNetworkTimeMillis = 7;
        static final int TRANSACTION_getNextAlarmClock = 6;
        static final int TRANSACTION_getNextWakeFromIdleTime = 5;
        static final int TRANSACTION_remove = 4;
        static final int TRANSACTION_set = 1;
        static final int TRANSACTION_setTime = 2;
        static final int TRANSACTION_setTimeZone = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "set";
                case 2:
                    return "setTime";
                case 3:
                    return "setTimeZone";
                case 4:
                    return "remove";
                case 5:
                    return "getNextWakeFromIdleTime";
                case 6:
                    return "getNextAlarmClock";
                case 7:
                    return "currentNetworkTimeMillis";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAlarmManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAlarmManager)) {
                return (IAlarmManager) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    set(parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, IAlarmListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? WorkSource.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? AlarmManager.AlarmClockInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean time = setTime(parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(time ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTimeZone(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    remove(parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, IAlarmListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    long nextWakeFromIdleTime = getNextWakeFromIdleTime();
                    parcel2.writeNoException();
                    parcel2.writeLong(nextWakeFromIdleTime);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    AlarmManager.AlarmClockInfo nextAlarmClock = getNextAlarmClock(parcel.readInt());
                    parcel2.writeNoException();
                    if (nextAlarmClock != null) {
                        parcel2.writeInt(1);
                        nextAlarmClock.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    long jCurrentNetworkTimeMillis = currentNetworkTimeMillis();
                    parcel2.writeNoException();
                    parcel2.writeLong(jCurrentNetworkTimeMillis);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAlarmManager {
            public static IAlarmManager sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r11v1, types: [android.os.IBinder] */
            /* JADX WARN: Type inference failed for: r13v0, types: [android.os.Parcel] */
            /* JADX WARN: Type inference failed for: r16v0 */
            /* JADX WARN: Type inference failed for: r16v1, types: [android.os.Parcel] */
            /* JADX WARN: Type inference failed for: r16v2 */
            /* JADX WARN: Type inference failed for: r16v3 */
            /* JADX WARN: Type inference failed for: r16v7 */
            /* JADX WARN: Type inference failed for: r1v5, types: [android.os.IBinder] */
            /* JADX WARN: Type inference failed for: r28v0, types: [android.app.PendingIntent] */
            /* JADX WARN: Type inference failed for: r31v0, types: [android.os.WorkSource] */
            /* JADX WARN: Type inference failed for: r32v0, types: [android.app.AlarmManager$AlarmClockInfo] */
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // android.app.IAlarmManager
            public void set(String str, int i, long j, long j2, long j3, int i2, PendingIntent pendingIntent, IAlarmListener iAlarmListener, String str2, WorkSource workSource, AlarmManager.AlarmClockInfo alarmClockInfo) throws Throwable {
                ?? AsBinder;
                int i3;
                ?? Obtain = Parcel.obtain();
                Parcel parcelObtain = Parcel.obtain();
                try {
                    Obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    Obtain.writeString(str);
                    Obtain.writeInt(i);
                    Obtain.writeLong(j);
                    Obtain.writeLong(j2);
                    Obtain.writeLong(j3);
                    Obtain.writeInt(i2);
                    if (pendingIntent != 0) {
                        Obtain.writeInt(1);
                        pendingIntent.writeToParcel(Obtain, 0);
                    } else {
                        Obtain.writeInt(0);
                    }
                    AsBinder = iAlarmListener != null ? iAlarmListener.asBinder() : 0;
                    Obtain.writeStrongBinder(AsBinder);
                    Obtain.writeString(str2);
                    if (workSource != 0) {
                        Obtain.writeInt(1);
                        i3 = 0;
                        workSource.writeToParcel(Obtain, 0);
                    } else {
                        i3 = 0;
                        Obtain.writeInt(0);
                    }
                    if (alarmClockInfo != 0) {
                        Obtain.writeInt(1);
                        alarmClockInfo.writeToParcel(Obtain, 0);
                    } else {
                        Obtain.writeInt(i3);
                    }
                    try {
                        if (!this.mRemote.transact(1, Obtain, parcelObtain, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().set(str, i, j, j2, j3, i2, pendingIntent, iAlarmListener, str2, workSource, alarmClockInfo);
                            parcelObtain.recycle();
                            Obtain.recycle();
                            return;
                        } else {
                            parcelObtain.readException();
                            parcelObtain.recycle();
                            Obtain.recycle();
                            return;
                        }
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    AsBinder = parcelObtain;
                }
                AsBinder.recycle();
                Obtain.recycle();
                throw th;
            }

            @Override // android.app.IAlarmManager
            public boolean setTime(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setTime(j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IAlarmManager
            public void setTimeZone(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTimeZone(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IAlarmManager
            public void remove(PendingIntent pendingIntent, IAlarmListener iAlarmListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iAlarmListener != null ? iAlarmListener.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().remove(pendingIntent, iAlarmListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IAlarmManager
            public long getNextWakeFromIdleTime() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNextWakeFromIdleTime();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IAlarmManager
            public AlarmManager.AlarmClockInfo getNextAlarmClock(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNextAlarmClock(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? AlarmManager.AlarmClockInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IAlarmManager
            public long currentNetworkTimeMillis() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().currentNetworkTimeMillis();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAlarmManager iAlarmManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAlarmManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAlarmManager;
            return true;
        }

        public static IAlarmManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
