package android.car.media;

import android.car.media.ICarAudioControl;
import android.car.media.ICarEqualizer;
import android.media.AudioFocusInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ICarEnhancedAudio extends IInterface {

    public static class Default implements ICarEnhancedAudio {
        @Override // android.car.media.ICarEnhancedAudio
        public void adjustSuggestedGroupVolume(int i, int i2, int i3, int i4, int i5) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void adjustSuggestedGroupVolumeToMaximum(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void adjustSuggestedGroupVolumeToMinimum(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void adjustVolume(int i, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void allowBootVolumeAutoAdjustment(int i, boolean z) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void duckAudio(int i, int i2) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public List<AudioFocusInfo> getAudioFocusHoldersForZoneId(int i) throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public List<AudioFocusInfo> getAudioFocusLosersForZoneId(int i) throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public int getBalanceTowardRightLevel() throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public int[] getBalanceTowardRightLevelRange() throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public ICarAudioControl getCarAudioControlInstance() throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public ICarEqualizer getCarEqualizerInstance() throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public int getFadeTowardFrontLevel() throws RemoteException {
            return 0;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public int[] getFadeTowardFrontLevelRange() throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public String getParameters(String str) throws RemoteException {
            return null;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public boolean isBalanceTowardRightSupported() throws RemoteException {
            return false;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public boolean isBootVolumeAutoAdjustmentAllowed(int i) throws RemoteException {
            return false;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public boolean isFadeTowardFrontSupported() throws RemoteException {
            return false;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public boolean isPlaybackOnVolumeGroupActive(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public boolean isVolumeGroupMuted(int i, int i2) throws RemoteException {
            return false;
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void registerBalanceCallback(IBinder iBinder) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void registerFocusCallback(IBinder iBinder) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void registerGroupMutingCallback(IBinder iBinder) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void setBalanceTowardRightLevel(int i) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void setFadeTowardFrontLevel(int i) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void setParameters(String str) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void setVolumeGroupMute(int i, int i2, boolean z, int i3) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void unduckAudio(int i) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void unregisterBalanceCallback(IBinder iBinder) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void unregisterFocusCallback(IBinder iBinder) throws RemoteException {
        }

        @Override // android.car.media.ICarEnhancedAudio
        public void unregisterGroupMutingCallback(IBinder iBinder) throws RemoteException {
        }
    }

    void adjustSuggestedGroupVolume(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    void adjustSuggestedGroupVolumeToMaximum(int i, int i2, int i3) throws RemoteException;

    void adjustSuggestedGroupVolumeToMinimum(int i, int i2, int i3) throws RemoteException;

    void adjustVolume(int i, int i2, int i3, int i4) throws RemoteException;

    void allowBootVolumeAutoAdjustment(int i, boolean z) throws RemoteException;

    void duckAudio(int i, int i2) throws RemoteException;

    List<AudioFocusInfo> getAudioFocusHoldersForZoneId(int i) throws RemoteException;

    List<AudioFocusInfo> getAudioFocusLosersForZoneId(int i) throws RemoteException;

    int getBalanceTowardRightLevel() throws RemoteException;

    int[] getBalanceTowardRightLevelRange() throws RemoteException;

    ICarAudioControl getCarAudioControlInstance() throws RemoteException;

    ICarEqualizer getCarEqualizerInstance() throws RemoteException;

    int getFadeTowardFrontLevel() throws RemoteException;

    int[] getFadeTowardFrontLevelRange() throws RemoteException;

    String getParameters(String str) throws RemoteException;

    boolean isBalanceTowardRightSupported() throws RemoteException;

    boolean isBootVolumeAutoAdjustmentAllowed(int i) throws RemoteException;

    boolean isFadeTowardFrontSupported() throws RemoteException;

    boolean isPlaybackOnVolumeGroupActive(int i, int i2) throws RemoteException;

    boolean isVolumeGroupMuted(int i, int i2) throws RemoteException;

    void registerBalanceCallback(IBinder iBinder) throws RemoteException;

    void registerFocusCallback(IBinder iBinder) throws RemoteException;

    void registerGroupMutingCallback(IBinder iBinder) throws RemoteException;

    void setBalanceTowardRightLevel(int i) throws RemoteException;

    void setFadeTowardFrontLevel(int i) throws RemoteException;

    void setParameters(String str) throws RemoteException;

    void setVolumeGroupMute(int i, int i2, boolean z, int i3) throws RemoteException;

    void unduckAudio(int i) throws RemoteException;

    void unregisterBalanceCallback(IBinder iBinder) throws RemoteException;

    void unregisterFocusCallback(IBinder iBinder) throws RemoteException;

    void unregisterGroupMutingCallback(IBinder iBinder) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarEnhancedAudio {
        private static final String DESCRIPTOR = "android.car.media.ICarEnhancedAudio";
        static final int TRANSACTION_adjustSuggestedGroupVolume = 9;
        static final int TRANSACTION_adjustSuggestedGroupVolumeToMaximum = 10;
        static final int TRANSACTION_adjustSuggestedGroupVolumeToMinimum = 11;
        static final int TRANSACTION_adjustVolume = 8;
        static final int TRANSACTION_allowBootVolumeAutoAdjustment = 16;
        static final int TRANSACTION_duckAudio = 18;
        static final int TRANSACTION_getAudioFocusHoldersForZoneId = 3;
        static final int TRANSACTION_getAudioFocusLosersForZoneId = 4;
        static final int TRANSACTION_getBalanceTowardRightLevel = 23;
        static final int TRANSACTION_getBalanceTowardRightLevelRange = 24;
        static final int TRANSACTION_getCarAudioControlInstance = 1;
        static final int TRANSACTION_getCarEqualizerInstance = 2;
        static final int TRANSACTION_getFadeTowardFrontLevel = 26;
        static final int TRANSACTION_getFadeTowardFrontLevelRange = 27;
        static final int TRANSACTION_getParameters = 21;
        static final int TRANSACTION_isBalanceTowardRightSupported = 28;
        static final int TRANSACTION_isBootVolumeAutoAdjustmentAllowed = 17;
        static final int TRANSACTION_isFadeTowardFrontSupported = 29;
        static final int TRANSACTION_isPlaybackOnVolumeGroupActive = 7;
        static final int TRANSACTION_isVolumeGroupMuted = 14;
        static final int TRANSACTION_registerBalanceCallback = 30;
        static final int TRANSACTION_registerFocusCallback = 5;
        static final int TRANSACTION_registerGroupMutingCallback = 12;
        static final int TRANSACTION_setBalanceTowardRightLevel = 22;
        static final int TRANSACTION_setFadeTowardFrontLevel = 25;
        static final int TRANSACTION_setParameters = 20;
        static final int TRANSACTION_setVolumeGroupMute = 15;
        static final int TRANSACTION_unduckAudio = 19;
        static final int TRANSACTION_unregisterBalanceCallback = 31;
        static final int TRANSACTION_unregisterFocusCallback = 6;
        static final int TRANSACTION_unregisterGroupMutingCallback = 13;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarEnhancedAudio asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarEnhancedAudio)) {
                return (ICarEnhancedAudio) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
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
                    ICarAudioControl carAudioControlInstance = getCarAudioControlInstance();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(carAudioControlInstance != null ? carAudioControlInstance.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    ICarEqualizer carEqualizerInstance = getCarEqualizerInstance();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(carEqualizerInstance != null ? carEqualizerInstance.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<AudioFocusInfo> audioFocusHoldersForZoneId = getAudioFocusHoldersForZoneId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(audioFocusHoldersForZoneId);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<AudioFocusInfo> audioFocusLosersForZoneId = getAudioFocusLosersForZoneId(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(audioFocusLosersForZoneId);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerFocusCallback(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterFocusCallback(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPlaybackOnVolumeGroupActive = isPlaybackOnVolumeGroupActive(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPlaybackOnVolumeGroupActive ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    adjustVolume(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    adjustSuggestedGroupVolume(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    adjustSuggestedGroupVolumeToMaximum(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    adjustSuggestedGroupVolumeToMinimum(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerGroupMutingCallback(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterGroupMutingCallback(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsVolumeGroupMuted = isVolumeGroupMuted(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsVolumeGroupMuted ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setVolumeGroupMute(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    allowBootVolumeAutoAdjustment(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsBootVolumeAutoAdjustmentAllowed = isBootVolumeAutoAdjustmentAllowed(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBootVolumeAutoAdjustmentAllowed ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    duckAudio(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    unduckAudio(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    setParameters(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    String parameters = getParameters(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(parameters);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBalanceTowardRightLevel(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int balanceTowardRightLevel = getBalanceTowardRightLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(balanceTowardRightLevel);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] balanceTowardRightLevelRange = getBalanceTowardRightLevelRange();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(balanceTowardRightLevelRange);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    setFadeTowardFrontLevel(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    int fadeTowardFrontLevel = getFadeTowardFrontLevel();
                    parcel2.writeNoException();
                    parcel2.writeInt(fadeTowardFrontLevel);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] fadeTowardFrontLevelRange = getFadeTowardFrontLevelRange();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(fadeTowardFrontLevelRange);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsBalanceTowardRightSupported = isBalanceTowardRightSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsBalanceTowardRightSupported ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsFadeTowardFrontSupported = isFadeTowardFrontSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsFadeTowardFrontSupported ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerBalanceCallback(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterBalanceCallback(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICarEnhancedAudio {
            public static ICarEnhancedAudio sDefaultImpl;
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

            @Override // android.car.media.ICarEnhancedAudio
            public ICarAudioControl getCarAudioControlInstance() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarAudioControlInstance();
                    }
                    parcelObtain2.readException();
                    return ICarAudioControl.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public ICarEqualizer getCarEqualizerInstance() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarEqualizerInstance();
                    }
                    parcelObtain2.readException();
                    return ICarEqualizer.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public List<AudioFocusInfo> getAudioFocusHoldersForZoneId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAudioFocusHoldersForZoneId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(AudioFocusInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public List<AudioFocusInfo> getAudioFocusLosersForZoneId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAudioFocusLosersForZoneId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(AudioFocusInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void registerFocusCallback(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerFocusCallback(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void unregisterFocusCallback(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterFocusCallback(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public boolean isPlaybackOnVolumeGroupActive(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPlaybackOnVolumeGroupActive(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void adjustVolume(int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().adjustVolume(i, i2, i3, i4);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void adjustSuggestedGroupVolume(int i, int i2, int i3, int i4, int i5) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().adjustSuggestedGroupVolume(i, i2, i3, i4, i5);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void adjustSuggestedGroupVolumeToMaximum(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().adjustSuggestedGroupVolumeToMaximum(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void adjustSuggestedGroupVolumeToMinimum(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().adjustSuggestedGroupVolumeToMinimum(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void registerGroupMutingCallback(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerGroupMutingCallback(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void unregisterGroupMutingCallback(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterGroupMutingCallback(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public boolean isVolumeGroupMuted(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isVolumeGroupMuted(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void setVolumeGroupMute(int i, int i2, boolean z, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVolumeGroupMute(i, i2, z, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void allowBootVolumeAutoAdjustment(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().allowBootVolumeAutoAdjustment(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public boolean isBootVolumeAutoAdjustmentAllowed(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBootVolumeAutoAdjustmentAllowed(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void duckAudio(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().duckAudio(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void unduckAudio(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unduckAudio(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void setParameters(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setParameters(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public String getParameters(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getParameters(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void setBalanceTowardRightLevel(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBalanceTowardRightLevel(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public int getBalanceTowardRightLevel() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBalanceTowardRightLevel();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public int[] getBalanceTowardRightLevelRange() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBalanceTowardRightLevelRange();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void setFadeTowardFrontLevel(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFadeTowardFrontLevel(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public int getFadeTowardFrontLevel() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFadeTowardFrontLevel();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public int[] getFadeTowardFrontLevelRange() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFadeTowardFrontLevelRange();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public boolean isBalanceTowardRightSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBalanceTowardRightSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public boolean isFadeTowardFrontSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFadeTowardFrontSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void registerBalanceCallback(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerBalanceCallback(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarEnhancedAudio
            public void unregisterBalanceCallback(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterBalanceCallback(iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarEnhancedAudio iCarEnhancedAudio) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarEnhancedAudio == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarEnhancedAudio;
            return true;
        }

        public static ICarEnhancedAudio getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
