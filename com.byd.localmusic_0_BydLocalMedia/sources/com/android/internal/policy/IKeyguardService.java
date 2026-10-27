package com.android.internal.policy;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.internal.policy.IKeyguardDrawnCallback;
import com.android.internal.policy.IKeyguardExitCallback;
import com.android.internal.policy.IKeyguardStateCallback;

/* JADX INFO: loaded from: classes4.dex */
public interface IKeyguardService extends IInterface {

    public static class Default implements IKeyguardService {
        @Override // com.android.internal.policy.IKeyguardService
        public void addStateMonitorCallback(IKeyguardStateCallback iKeyguardStateCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void dismiss(IKeyguardDismissCallback iKeyguardDismissCallback, CharSequence charSequence) throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void doKeyguardTimeout(Bundle bundle) throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void onBootCompleted() throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void onDreamingStarted() throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void onDreamingStopped() throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void onFinishedGoingToSleep(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void onFinishedWakingUp() throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void onScreenTurnedOff() throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void onScreenTurnedOn() throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void onScreenTurningOff() throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void onScreenTurningOn(IKeyguardDrawnCallback iKeyguardDrawnCallback) throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void onShortPowerPressedGoHome() throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void onStartedGoingToSleep(int i) throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void onStartedWakingUp() throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void onSystemReady() throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void setCurrentUser(int i) throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void setKeyguardEnabled(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void setOccluded(boolean z, boolean z2) throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void setSwitchingUser(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void startKeyguardExitAnimation(long j, long j2) throws RemoteException {
        }

        @Override // com.android.internal.policy.IKeyguardService
        public void verifyUnlock(IKeyguardExitCallback iKeyguardExitCallback) throws RemoteException {
        }
    }

    void addStateMonitorCallback(IKeyguardStateCallback iKeyguardStateCallback) throws RemoteException;

    void dismiss(IKeyguardDismissCallback iKeyguardDismissCallback, CharSequence charSequence) throws RemoteException;

    void doKeyguardTimeout(Bundle bundle) throws RemoteException;

    void onBootCompleted() throws RemoteException;

    void onDreamingStarted() throws RemoteException;

    void onDreamingStopped() throws RemoteException;

    void onFinishedGoingToSleep(int i, boolean z) throws RemoteException;

    void onFinishedWakingUp() throws RemoteException;

    void onScreenTurnedOff() throws RemoteException;

    void onScreenTurnedOn() throws RemoteException;

    void onScreenTurningOff() throws RemoteException;

    void onScreenTurningOn(IKeyguardDrawnCallback iKeyguardDrawnCallback) throws RemoteException;

    void onShortPowerPressedGoHome() throws RemoteException;

    void onStartedGoingToSleep(int i) throws RemoteException;

    void onStartedWakingUp() throws RemoteException;

    void onSystemReady() throws RemoteException;

    void setCurrentUser(int i) throws RemoteException;

    void setKeyguardEnabled(boolean z) throws RemoteException;

    void setOccluded(boolean z, boolean z2) throws RemoteException;

    void setSwitchingUser(boolean z) throws RemoteException;

    void startKeyguardExitAnimation(long j, long j2) throws RemoteException;

    void verifyUnlock(IKeyguardExitCallback iKeyguardExitCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IKeyguardService {
        private static final String DESCRIPTOR = "com.android.internal.policy.IKeyguardService";
        static final int TRANSACTION_addStateMonitorCallback = 2;
        static final int TRANSACTION_dismiss = 4;
        static final int TRANSACTION_doKeyguardTimeout = 17;
        static final int TRANSACTION_onBootCompleted = 20;
        static final int TRANSACTION_onDreamingStarted = 5;
        static final int TRANSACTION_onDreamingStopped = 6;
        static final int TRANSACTION_onFinishedGoingToSleep = 8;
        static final int TRANSACTION_onFinishedWakingUp = 10;
        static final int TRANSACTION_onScreenTurnedOff = 14;
        static final int TRANSACTION_onScreenTurnedOn = 12;
        static final int TRANSACTION_onScreenTurningOff = 13;
        static final int TRANSACTION_onScreenTurningOn = 11;
        static final int TRANSACTION_onShortPowerPressedGoHome = 22;
        static final int TRANSACTION_onStartedGoingToSleep = 7;
        static final int TRANSACTION_onStartedWakingUp = 9;
        static final int TRANSACTION_onSystemReady = 16;
        static final int TRANSACTION_setCurrentUser = 19;
        static final int TRANSACTION_setKeyguardEnabled = 15;
        static final int TRANSACTION_setOccluded = 1;
        static final int TRANSACTION_setSwitchingUser = 18;
        static final int TRANSACTION_startKeyguardExitAnimation = 21;
        static final int TRANSACTION_verifyUnlock = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "setOccluded";
                case 2:
                    return "addStateMonitorCallback";
                case 3:
                    return "verifyUnlock";
                case 4:
                    return "dismiss";
                case 5:
                    return "onDreamingStarted";
                case 6:
                    return "onDreamingStopped";
                case 7:
                    return "onStartedGoingToSleep";
                case 8:
                    return "onFinishedGoingToSleep";
                case 9:
                    return "onStartedWakingUp";
                case 10:
                    return "onFinishedWakingUp";
                case 11:
                    return "onScreenTurningOn";
                case 12:
                    return "onScreenTurnedOn";
                case 13:
                    return "onScreenTurningOff";
                case 14:
                    return "onScreenTurnedOff";
                case 15:
                    return "setKeyguardEnabled";
                case 16:
                    return "onSystemReady";
                case 17:
                    return "doKeyguardTimeout";
                case 18:
                    return "setSwitchingUser";
                case 19:
                    return "setCurrentUser";
                case 20:
                    return "onBootCompleted";
                case 21:
                    return "startKeyguardExitAnimation";
                case 22:
                    return "onShortPowerPressedGoHome";
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

        public static IKeyguardService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IKeyguardService)) {
                return (IKeyguardService) iInterfaceQueryLocalInterface;
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
                    setOccluded(parcel.readInt() != 0, parcel.readInt() != 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    addStateMonitorCallback(IKeyguardStateCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    verifyUnlock(IKeyguardExitCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    dismiss(IKeyguardDismissCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDreamingStarted();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDreamingStopped();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onStartedGoingToSleep(parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onFinishedGoingToSleep(parcel.readInt(), parcel.readInt() != 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onStartedWakingUp();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onFinishedWakingUp();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onScreenTurningOn(IKeyguardDrawnCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    onScreenTurnedOn();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    onScreenTurningOff();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    onScreenTurnedOff();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setKeyguardEnabled(parcel.readInt() != 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSystemReady();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    doKeyguardTimeout(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSwitchingUser(parcel.readInt() != 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCurrentUser(parcel.readInt());
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    onBootCompleted();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    startKeyguardExitAnimation(parcel.readLong(), parcel.readLong());
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    onShortPowerPressedGoHome();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IKeyguardService {
            public static IKeyguardService sDefaultImpl;
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

            @Override // com.android.internal.policy.IKeyguardService
            public void setOccluded(boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setOccluded(z, z2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void addStateMonitorCallback(IKeyguardStateCallback iKeyguardStateCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeyguardStateCallback != null ? iKeyguardStateCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().addStateMonitorCallback(iKeyguardStateCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void verifyUnlock(IKeyguardExitCallback iKeyguardExitCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeyguardExitCallback != null ? iKeyguardExitCallback.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().verifyUnlock(iKeyguardExitCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void dismiss(IKeyguardDismissCallback iKeyguardDismissCallback, CharSequence charSequence) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeyguardDismissCallback != null ? iKeyguardDismissCallback.asBinder() : null);
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dismiss(iKeyguardDismissCallback, charSequence);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void onDreamingStarted() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDreamingStarted();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void onDreamingStopped() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDreamingStopped();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void onStartedGoingToSleep(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStartedGoingToSleep(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void onFinishedGoingToSleep(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFinishedGoingToSleep(i, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void onStartedWakingUp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStartedWakingUp();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void onFinishedWakingUp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFinishedWakingUp();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void onScreenTurningOn(IKeyguardDrawnCallback iKeyguardDrawnCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeyguardDrawnCallback != null ? iKeyguardDrawnCallback.asBinder() : null);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onScreenTurningOn(iKeyguardDrawnCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void onScreenTurnedOn() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onScreenTurnedOn();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void onScreenTurningOff() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onScreenTurningOff();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void onScreenTurnedOff() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onScreenTurnedOff();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void setKeyguardEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setKeyguardEnabled(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void onSystemReady() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSystemReady();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void doKeyguardTimeout(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(17, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().doKeyguardTimeout(bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void setSwitchingUser(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(18, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setSwitchingUser(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void setCurrentUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(19, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setCurrentUser(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void onBootCompleted() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(20, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onBootCompleted();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void startKeyguardExitAnimation(long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (this.mRemote.transact(21, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startKeyguardExitAnimation(j, j2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.policy.IKeyguardService
            public void onShortPowerPressedGoHome() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(22, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onShortPowerPressedGoHome();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeyguardService iKeyguardService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iKeyguardService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iKeyguardService;
            return true;
        }

        public static IKeyguardService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
