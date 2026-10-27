package android.micphone;

import android.micphone.IMicphoneNotificationCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IMicphoneService extends IInterface {

    public static class Default implements IMicphoneService {
        @Override // android.micphone.IMicphoneService
        public int LoadMicAudioEffect(byte[] bArr) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.micphone.IMicphoneService
        public int change2OtaMode() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int getInternalEffect() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int getInternalEffectGroup() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicAConnect() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicAFreq() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicAPowerLevel() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public String getMicAProductName() throws RemoteException {
            return null;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicARssi() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public String getMicAVersion() throws RemoteException {
            return null;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicBConnect() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicBFreq() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicBPowerLevel() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public String getMicBProductName() throws RemoteException {
            return null;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicBRssi() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public String getMicBVersion() throws RemoteException {
            return null;
        }

        @Override // android.micphone.IMicphoneService
        public String getMicDeviceId() throws RemoteException {
            return null;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicFreq(int i) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicIDByMic(int i) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicIsConnected(int i) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicMax() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public String getMicMicName(int i) throws RemoteException {
            return null;
        }

        @Override // android.micphone.IMicphoneService
        public String getMicMicVersion(int i) throws RemoteException {
            return null;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicPower(int i) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public String getMicProductName() throws RemoteException {
            return null;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicReverb() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicRssi(int i) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public String getMicVersion() throws RemoteException {
            return null;
        }

        @Override // android.micphone.IMicphoneService
        public int getMicVolume() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int getVolumeByMic(int i) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int isSupportOta() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int isThunderMicphone(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int pauseLoopback(int i) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public void registerMicphoneNotificationCallback(IMicphoneNotificationCallback iMicphoneNotificationCallback) throws RemoteException {
        }

        @Override // android.micphone.IMicphoneService
        public int saveEffect() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int selectInternalEffect(int i) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int selectInternalEffectGroup(int i) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int setLoopbackLatency(int i) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int setMicReverb(int i) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int setMicVolume(int i) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int setVolumeByMic(int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int startLoopback() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int stopLoopback() throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public int switchEffect(int i) throws RemoteException {
            return 0;
        }

        @Override // android.micphone.IMicphoneService
        public void unregisterMicphoneNotificationCallback(IMicphoneNotificationCallback iMicphoneNotificationCallback) throws RemoteException {
        }

        @Override // android.micphone.IMicphoneService
        public int upgrade(byte[] bArr) throws RemoteException {
            return 0;
        }
    }

    public static abstract class Stub extends Binder implements IMicphoneService {
        private static final String DESCRIPTOR = "android.micphone.IMicphoneService";
        static final int TRANSACTION_LoadMicAudioEffect = 17;
        static final int TRANSACTION_change2OtaMode = 30;
        static final int TRANSACTION_getInternalEffect = 41;
        static final int TRANSACTION_getInternalEffectGroup = 43;
        static final int TRANSACTION_getMicAConnect = 15;
        static final int TRANSACTION_getMicAFreq = 21;
        static final int TRANSACTION_getMicAPowerLevel = 13;
        static final int TRANSACTION_getMicAProductName = 11;
        static final int TRANSACTION_getMicARssi = 23;
        static final int TRANSACTION_getMicAVersion = 9;
        static final int TRANSACTION_getMicBConnect = 16;
        static final int TRANSACTION_getMicBFreq = 22;
        static final int TRANSACTION_getMicBPowerLevel = 14;
        static final int TRANSACTION_getMicBProductName = 12;
        static final int TRANSACTION_getMicBRssi = 24;
        static final int TRANSACTION_getMicBVersion = 10;
        static final int TRANSACTION_getMicDeviceId = 20;
        static final int TRANSACTION_getMicFreq = 38;
        static final int TRANSACTION_getMicIDByMic = 36;
        static final int TRANSACTION_getMicIsConnected = 33;
        static final int TRANSACTION_getMicMax = 32;
        static final int TRANSACTION_getMicMicName = 45;
        static final int TRANSACTION_getMicMicVersion = 44;
        static final int TRANSACTION_getMicPower = 37;
        static final int TRANSACTION_getMicProductName = 8;
        static final int TRANSACTION_getMicReverb = 6;
        static final int TRANSACTION_getMicRssi = 39;
        static final int TRANSACTION_getMicVersion = 7;
        static final int TRANSACTION_getMicVolume = 4;
        static final int TRANSACTION_getVolumeByMic = 35;
        static final int TRANSACTION_isSupportOta = 46;
        static final int TRANSACTION_isThunderMicphone = 25;
        static final int TRANSACTION_pauseLoopback = 28;
        static final int TRANSACTION_registerMicphoneNotificationCallback = 1;
        static final int TRANSACTION_saveEffect = 18;
        static final int TRANSACTION_selectInternalEffect = 40;
        static final int TRANSACTION_selectInternalEffectGroup = 42;
        static final int TRANSACTION_setLoopbackLatency = 29;
        static final int TRANSACTION_setMicReverb = 5;
        static final int TRANSACTION_setMicVolume = 3;
        static final int TRANSACTION_setVolumeByMic = 34;
        static final int TRANSACTION_startLoopback = 26;
        static final int TRANSACTION_stopLoopback = 27;
        static final int TRANSACTION_switchEffect = 19;
        static final int TRANSACTION_unregisterMicphoneNotificationCallback = 2;
        static final int TRANSACTION_upgrade = 31;

        private static class Proxy implements IMicphoneService {
            public static IMicphoneService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.micphone.IMicphoneService
            public int LoadMicAudioEffect(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().LoadMicAudioEffect(bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.micphone.IMicphoneService
            public int change2OtaMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().change2OtaMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.micphone.IMicphoneService
            public int getInternalEffect() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInternalEffect();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getInternalEffectGroup() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInternalEffectGroup();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicAConnect() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicAConnect();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicAFreq() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicAFreq();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicAPowerLevel() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicAPowerLevel();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public String getMicAProductName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicAProductName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicARssi() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicARssi();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public String getMicAVersion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicAVersion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicBConnect() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicBConnect();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicBFreq() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicBFreq();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicBPowerLevel() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicBPowerLevel();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public String getMicBProductName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicBProductName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicBRssi() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicBRssi();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public String getMicBVersion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicBVersion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public String getMicDeviceId() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicDeviceId();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicFreq(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicFreq(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicIDByMic(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicIDByMic(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicIsConnected(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicIsConnected(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicMax() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicMax();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public String getMicMicName(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicMicName(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public String getMicMicVersion(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicMicVersion(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicPower(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicPower(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public String getMicProductName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicProductName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicReverb() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicReverb();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicRssi(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicRssi(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public String getMicVersion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicVersion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getMicVolume() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMicVolume();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int getVolumeByMic(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVolumeByMic(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int isSupportOta() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSupportOta();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int isThunderMicphone(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isThunderMicphone(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int pauseLoopback(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pauseLoopback(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public void registerMicphoneNotificationCallback(IMicphoneNotificationCallback iMicphoneNotificationCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMicphoneNotificationCallback != null ? iMicphoneNotificationCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().registerMicphoneNotificationCallback(iMicphoneNotificationCallback);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int saveEffect() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().saveEffect();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int selectInternalEffect(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().selectInternalEffect(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int selectInternalEffectGroup(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().selectInternalEffectGroup(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int setLoopbackLatency(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLoopbackLatency(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int setMicReverb(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMicReverb(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int setMicVolume(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMicVolume(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int setVolumeByMic(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setVolumeByMic(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int startLoopback() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startLoopback();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int stopLoopback() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopLoopback();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int switchEffect(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().switchEffect(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public void unregisterMicphoneNotificationCallback(IMicphoneNotificationCallback iMicphoneNotificationCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMicphoneNotificationCallback != null ? iMicphoneNotificationCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterMicphoneNotificationCallback(iMicphoneNotificationCallback);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneService
            public int upgrade(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().upgrade(bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMicphoneService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IMicphoneService)) ? new Proxy(iBinder) : (IMicphoneService) iInterfaceQueryLocalInterface;
        }

        public static IMicphoneService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IMicphoneService iMicphoneService) {
            if (Proxy.sDefaultImpl != null || iMicphoneService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMicphoneService;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
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
                    registerMicphoneNotificationCallback(IMicphoneNotificationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterMicphoneNotificationCallback(IMicphoneNotificationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micVolume = setMicVolume(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(micVolume);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micVolume2 = getMicVolume();
                    parcel2.writeNoException();
                    parcel2.writeInt(micVolume2);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micReverb = setMicReverb(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(micReverb);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micReverb2 = getMicReverb();
                    parcel2.writeNoException();
                    parcel2.writeInt(micReverb2);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String micVersion = getMicVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(micVersion);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    String micProductName = getMicProductName();
                    parcel2.writeNoException();
                    parcel2.writeString(micProductName);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    String micAVersion = getMicAVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(micAVersion);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    String micBVersion = getMicBVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(micBVersion);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    String micAProductName = getMicAProductName();
                    parcel2.writeNoException();
                    parcel2.writeString(micAProductName);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    String micBProductName = getMicBProductName();
                    parcel2.writeNoException();
                    parcel2.writeString(micBProductName);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micAPowerLevel = getMicAPowerLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(micAPowerLevel);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micBPowerLevel = getMicBPowerLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(micBPowerLevel);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micAConnect = getMicAConnect();
                    parcel2.writeNoException();
                    parcel2.writeInt(micAConnect);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micBConnect = getMicBConnect();
                    parcel2.writeNoException();
                    parcel2.writeInt(micBConnect);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iLoadMicAudioEffect = LoadMicAudioEffect(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iLoadMicAudioEffect);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSaveEffect = saveEffect();
                    parcel2.writeNoException();
                    parcel2.writeInt(iSaveEffect);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSwitchEffect = switchEffect(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSwitchEffect);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    String micDeviceId = getMicDeviceId();
                    parcel2.writeNoException();
                    parcel2.writeString(micDeviceId);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micAFreq = getMicAFreq();
                    parcel2.writeNoException();
                    parcel2.writeInt(micAFreq);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micBFreq = getMicBFreq();
                    parcel2.writeNoException();
                    parcel2.writeInt(micBFreq);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micARssi = getMicARssi();
                    parcel2.writeNoException();
                    parcel2.writeInt(micARssi);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micBRssi = getMicBRssi();
                    parcel2.writeNoException();
                    parcel2.writeInt(micBRssi);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iIsThunderMicphone = isThunderMicphone(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iIsThunderMicphone);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iStartLoopback = startLoopback();
                    parcel2.writeNoException();
                    parcel2.writeInt(iStartLoopback);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iStopLoopback = stopLoopback();
                    parcel2.writeNoException();
                    parcel2.writeInt(iStopLoopback);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iPauseLoopback = pauseLoopback(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iPauseLoopback);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    int loopbackLatency = setLoopbackLatency(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(loopbackLatency);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iChange2OtaMode = change2OtaMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(iChange2OtaMode);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iUpgrade = upgrade(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iUpgrade);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micMax = getMicMax();
                    parcel2.writeNoException();
                    parcel2.writeInt(micMax);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micIsConnected = getMicIsConnected(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(micIsConnected);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    int volumeByMic = setVolumeByMic(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(volumeByMic);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    int volumeByMic2 = getVolumeByMic(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(volumeByMic2);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micIDByMic = getMicIDByMic(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(micIDByMic);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micPower = getMicPower(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(micPower);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micFreq = getMicFreq(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(micFreq);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    int micRssi = getMicRssi(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(micRssi);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSelectInternalEffect = selectInternalEffect(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSelectInternalEffect);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    int internalEffect = getInternalEffect();
                    parcel2.writeNoException();
                    parcel2.writeInt(internalEffect);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSelectInternalEffectGroup = selectInternalEffectGroup(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSelectInternalEffectGroup);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    int internalEffectGroup = getInternalEffectGroup();
                    parcel2.writeNoException();
                    parcel2.writeInt(internalEffectGroup);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    String micMicVersion = getMicMicVersion(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(micMicVersion);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    String micMicName = getMicMicName(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(micMicName);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iIsSupportOta = isSupportOta();
                    parcel2.writeNoException();
                    parcel2.writeInt(iIsSupportOta);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int LoadMicAudioEffect(byte[] bArr) throws RemoteException;

    int change2OtaMode() throws RemoteException;

    int getInternalEffect() throws RemoteException;

    int getInternalEffectGroup() throws RemoteException;

    int getMicAConnect() throws RemoteException;

    int getMicAFreq() throws RemoteException;

    int getMicAPowerLevel() throws RemoteException;

    String getMicAProductName() throws RemoteException;

    int getMicARssi() throws RemoteException;

    String getMicAVersion() throws RemoteException;

    int getMicBConnect() throws RemoteException;

    int getMicBFreq() throws RemoteException;

    int getMicBPowerLevel() throws RemoteException;

    String getMicBProductName() throws RemoteException;

    int getMicBRssi() throws RemoteException;

    String getMicBVersion() throws RemoteException;

    String getMicDeviceId() throws RemoteException;

    int getMicFreq(int i) throws RemoteException;

    int getMicIDByMic(int i) throws RemoteException;

    int getMicIsConnected(int i) throws RemoteException;

    int getMicMax() throws RemoteException;

    String getMicMicName(int i) throws RemoteException;

    String getMicMicVersion(int i) throws RemoteException;

    int getMicPower(int i) throws RemoteException;

    String getMicProductName() throws RemoteException;

    int getMicReverb() throws RemoteException;

    int getMicRssi(int i) throws RemoteException;

    String getMicVersion() throws RemoteException;

    int getMicVolume() throws RemoteException;

    int getVolumeByMic(int i) throws RemoteException;

    int isSupportOta() throws RemoteException;

    int isThunderMicphone(int i, int i2) throws RemoteException;

    int pauseLoopback(int i) throws RemoteException;

    void registerMicphoneNotificationCallback(IMicphoneNotificationCallback iMicphoneNotificationCallback) throws RemoteException;

    int saveEffect() throws RemoteException;

    int selectInternalEffect(int i) throws RemoteException;

    int selectInternalEffectGroup(int i) throws RemoteException;

    int setLoopbackLatency(int i) throws RemoteException;

    int setMicReverb(int i) throws RemoteException;

    int setMicVolume(int i) throws RemoteException;

    int setVolumeByMic(int i, int i2) throws RemoteException;

    int startLoopback() throws RemoteException;

    int stopLoopback() throws RemoteException;

    int switchEffect(int i) throws RemoteException;

    void unregisterMicphoneNotificationCallback(IMicphoneNotificationCallback iMicphoneNotificationCallback) throws RemoteException;

    int upgrade(byte[] bArr) throws RemoteException;
}
