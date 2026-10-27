package android.hardware.face;

import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.hardware.biometrics.IBiometricServiceReceiverInternal;
import android.hardware.face.IFaceServiceReceiver;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IFaceService extends IInterface {

    public static class Default implements IFaceService {
        @Override // android.hardware.face.IFaceService
        public void addLockoutResetCallback(IBiometricServiceLockoutResetCallback iBiometricServiceLockoutResetCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.face.IFaceService
        public void authenticate(IBinder iBinder, long j, int i, IFaceServiceReceiver iFaceServiceReceiver, int i2, String str) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void cancelAuthentication(IBinder iBinder, String str) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void cancelAuthenticationFromService(IBinder iBinder, String str, int i, int i2, int i3, boolean z) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void cancelEnrollment(IBinder iBinder) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void enroll(int i, IBinder iBinder, byte[] bArr, IFaceServiceReceiver iFaceServiceReceiver, String str, int[] iArr) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void enumerate(IBinder iBinder, int i, IFaceServiceReceiver iFaceServiceReceiver) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public long generateChallenge(IBinder iBinder) throws RemoteException {
            return 0L;
        }

        @Override // android.hardware.face.IFaceService
        public long getAuthenticatorId(int i) throws RemoteException {
            return 0L;
        }

        @Override // android.hardware.face.IFaceService
        public List<Face> getEnrolledFaces(int i, String str) throws RemoteException {
            return null;
        }

        @Override // android.hardware.face.IFaceService
        public void getFeature(int i, int i2, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public boolean hasEnrolledFaces(int i, String str) throws RemoteException {
            return false;
        }

        @Override // android.hardware.face.IFaceService
        public void initConfiguredStrength(int i) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public boolean isHardwareDetected(String str) throws RemoteException {
            return false;
        }

        @Override // android.hardware.face.IFaceService
        public void prepareForAuthentication(boolean z, IBinder iBinder, long j, int i, IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal, String str, int i2, int i3, int i4, int i5) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void remove(IBinder iBinder, int i, int i2, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void rename(int i, String str) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void resetLockout(byte[] bArr) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public int revokeChallenge(IBinder iBinder) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.face.IFaceService
        public void setActiveUser(int i) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void setFeature(int i, int i2, boolean z, byte[] bArr, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void startPreparedClient(int i) throws RemoteException {
        }

        @Override // android.hardware.face.IFaceService
        public void userActivity() throws RemoteException {
        }
    }

    void addLockoutResetCallback(IBiometricServiceLockoutResetCallback iBiometricServiceLockoutResetCallback) throws RemoteException;

    void authenticate(IBinder iBinder, long j, int i, IFaceServiceReceiver iFaceServiceReceiver, int i2, String str) throws RemoteException;

    void cancelAuthentication(IBinder iBinder, String str) throws RemoteException;

    void cancelAuthenticationFromService(IBinder iBinder, String str, int i, int i2, int i3, boolean z) throws RemoteException;

    void cancelEnrollment(IBinder iBinder) throws RemoteException;

    void enroll(int i, IBinder iBinder, byte[] bArr, IFaceServiceReceiver iFaceServiceReceiver, String str, int[] iArr) throws RemoteException;

    void enumerate(IBinder iBinder, int i, IFaceServiceReceiver iFaceServiceReceiver) throws RemoteException;

    long generateChallenge(IBinder iBinder) throws RemoteException;

    long getAuthenticatorId(int i) throws RemoteException;

    List<Face> getEnrolledFaces(int i, String str) throws RemoteException;

    void getFeature(int i, int i2, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException;

    boolean hasEnrolledFaces(int i, String str) throws RemoteException;

    void initConfiguredStrength(int i) throws RemoteException;

    boolean isHardwareDetected(String str) throws RemoteException;

    void prepareForAuthentication(boolean z, IBinder iBinder, long j, int i, IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal, String str, int i2, int i3, int i4, int i5) throws RemoteException;

    void remove(IBinder iBinder, int i, int i2, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException;

    void rename(int i, String str) throws RemoteException;

    void resetLockout(byte[] bArr) throws RemoteException;

    int revokeChallenge(IBinder iBinder) throws RemoteException;

    void setActiveUser(int i) throws RemoteException;

    void setFeature(int i, int i2, boolean z, byte[] bArr, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException;

    void startPreparedClient(int i) throws RemoteException;

    void userActivity() throws RemoteException;

    public static abstract class Stub extends Binder implements IFaceService {
        private static final String DESCRIPTOR = "android.hardware.face.IFaceService";
        static final int TRANSACTION_addLockoutResetCallback = 17;
        static final int TRANSACTION_authenticate = 1;
        static final int TRANSACTION_cancelAuthentication = 4;
        static final int TRANSACTION_cancelAuthenticationFromService = 5;
        static final int TRANSACTION_cancelEnrollment = 7;
        static final int TRANSACTION_enroll = 6;
        static final int TRANSACTION_enumerate = 19;
        static final int TRANSACTION_generateChallenge = 12;
        static final int TRANSACTION_getAuthenticatorId = 15;
        static final int TRANSACTION_getEnrolledFaces = 10;
        static final int TRANSACTION_getFeature = 21;
        static final int TRANSACTION_hasEnrolledFaces = 14;
        static final int TRANSACTION_initConfiguredStrength = 23;
        static final int TRANSACTION_isHardwareDetected = 11;
        static final int TRANSACTION_prepareForAuthentication = 2;
        static final int TRANSACTION_remove = 8;
        static final int TRANSACTION_rename = 9;
        static final int TRANSACTION_resetLockout = 16;
        static final int TRANSACTION_revokeChallenge = 13;
        static final int TRANSACTION_setActiveUser = 18;
        static final int TRANSACTION_setFeature = 20;
        static final int TRANSACTION_startPreparedClient = 3;
        static final int TRANSACTION_userActivity = 22;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "authenticate";
                case 2:
                    return "prepareForAuthentication";
                case 3:
                    return "startPreparedClient";
                case 4:
                    return "cancelAuthentication";
                case 5:
                    return "cancelAuthenticationFromService";
                case 6:
                    return "enroll";
                case 7:
                    return "cancelEnrollment";
                case 8:
                    return "remove";
                case 9:
                    return "rename";
                case 10:
                    return "getEnrolledFaces";
                case 11:
                    return "isHardwareDetected";
                case 12:
                    return "generateChallenge";
                case 13:
                    return "revokeChallenge";
                case 14:
                    return "hasEnrolledFaces";
                case 15:
                    return "getAuthenticatorId";
                case 16:
                    return "resetLockout";
                case 17:
                    return "addLockoutResetCallback";
                case 18:
                    return "setActiveUser";
                case 19:
                    return "enumerate";
                case 20:
                    return "setFeature";
                case 21:
                    return "getFeature";
                case 22:
                    return "userActivity";
                case 23:
                    return "initConfiguredStrength";
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

        public static IFaceService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IFaceService)) {
                return (IFaceService) iInterfaceQueryLocalInterface;
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
                    authenticate(parcel.readStrongBinder(), parcel.readLong(), parcel.readInt(), IFaceServiceReceiver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    prepareForAuthentication(parcel.readInt() != 0, parcel.readStrongBinder(), parcel.readLong(), parcel.readInt(), IBiometricServiceReceiverInternal.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    startPreparedClient(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelAuthentication(parcel.readStrongBinder(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelAuthenticationFromService(parcel.readStrongBinder(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    enroll(parcel.readInt(), parcel.readStrongBinder(), parcel.createByteArray(), IFaceServiceReceiver.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelEnrollment(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    remove(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), IFaceServiceReceiver.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    rename(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<Face> enrolledFaces = getEnrolledFaces(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(enrolledFaces);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsHardwareDetected = isHardwareDetected(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsHardwareDetected ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    long jGenerateChallenge = generateChallenge(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    parcel2.writeLong(jGenerateChallenge);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iRevokeChallenge = revokeChallenge(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    parcel2.writeInt(iRevokeChallenge);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasEnrolledFaces = hasEnrolledFaces(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasEnrolledFaces ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    long authenticatorId = getAuthenticatorId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(authenticatorId);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetLockout(parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    addLockoutResetCallback(IBiometricServiceLockoutResetCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    setActiveUser(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    enumerate(parcel.readStrongBinder(), parcel.readInt(), IFaceServiceReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    setFeature(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.createByteArray(), IFaceServiceReceiver.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    getFeature(parcel.readInt(), parcel.readInt(), IFaceServiceReceiver.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    userActivity();
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    initConfiguredStrength(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IFaceService {
            public static IFaceService sDefaultImpl;
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

            @Override // android.hardware.face.IFaceService
            public void authenticate(IBinder iBinder, long j, int i, IFaceServiceReceiver iFaceServiceReceiver, int i2, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iFaceServiceReceiver != null ? iFaceServiceReceiver.asBinder() : null);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().authenticate(iBinder, j, i, iFaceServiceReceiver, i2, str);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.hardware.face.IFaceService
            public void prepareForAuthentication(boolean z, IBinder iBinder, long j, int i, IBiometricServiceReceiverInternal iBiometricServiceReceiverInternal, String str, int i2, int i3, int i4, int i5) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBiometricServiceReceiverInternal != null ? iBiometricServiceReceiverInternal.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().prepareForAuthentication(z, iBinder, j, i, iBiometricServiceReceiverInternal, str, i2, i3, i4, i5);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.hardware.face.IFaceService
            public void startPreparedClient(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startPreparedClient(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void cancelAuthentication(IBinder iBinder, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelAuthentication(iBinder, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void cancelAuthenticationFromService(IBinder iBinder, String str, int i, int i2, int i3, boolean z) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(z ? 1 : 0);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelAuthenticationFromService(iBinder, str, i, i2, i3, z);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.hardware.face.IFaceService
            public void enroll(int i, IBinder iBinder, byte[] bArr, IFaceServiceReceiver iFaceServiceReceiver, String str, int[] iArr) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeStrongBinder(iFaceServiceReceiver != null ? iFaceServiceReceiver.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeIntArray(iArr);
                    try {
                        if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().enroll(i, iBinder, bArr, iFaceServiceReceiver, str, iArr);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        } else {
                            parcelObtain2.readException();
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // android.hardware.face.IFaceService
            public void cancelEnrollment(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelEnrollment(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void remove(IBinder iBinder, int i, int i2, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iFaceServiceReceiver != null ? iFaceServiceReceiver.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().remove(iBinder, i, i2, iFaceServiceReceiver, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void rename(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rename(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public List<Face> getEnrolledFaces(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnrolledFaces(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(Face.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public boolean isHardwareDetected(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHardwareDetected(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public long generateChallenge(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().generateChallenge(iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public int revokeChallenge(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().revokeChallenge(iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public boolean hasEnrolledFaces(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasEnrolledFaces(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public long getAuthenticatorId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAuthenticatorId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void resetLockout(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetLockout(bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void addLockoutResetCallback(IBiometricServiceLockoutResetCallback iBiometricServiceLockoutResetCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBiometricServiceLockoutResetCallback != null ? iBiometricServiceLockoutResetCallback.asBinder() : null);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addLockoutResetCallback(iBiometricServiceLockoutResetCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void setActiveUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setActiveUser(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void enumerate(IBinder iBinder, int i, IFaceServiceReceiver iFaceServiceReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iFaceServiceReceiver != null ? iFaceServiceReceiver.asBinder() : null);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enumerate(iBinder, i, iFaceServiceReceiver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void setFeature(int i, int i2, boolean z, byte[] bArr, IFaceServiceReceiver iFaceServiceReceiver, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeStrongBinder(iFaceServiceReceiver != null ? iFaceServiceReceiver.asBinder() : null);
                    parcelObtain.writeString(str);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFeature(i, i2, z, bArr, iFaceServiceReceiver, str);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.hardware.face.IFaceService
            public void getFeature(int i, int i2, IFaceServiceReceiver iFaceServiceReceiver, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iFaceServiceReceiver != null ? iFaceServiceReceiver.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getFeature(i, i2, iFaceServiceReceiver, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void userActivity() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().userActivity();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.face.IFaceService
            public void initConfiguredStrength(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initConfiguredStrength(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IFaceService iFaceService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iFaceService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iFaceService;
            return true;
        }

        public static IFaceService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
