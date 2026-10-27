package com.vivid.spotify;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.vivid.spotify.IMediaInfoControllerCallback;
import com.vivid.spotify.IRequestCallBack;

/* JADX INFO: loaded from: classes5.dex */
public interface ISpotifyMedia extends IInterface {

    public static class Default implements ISpotifyMedia {
        @Override // com.vivid.spotify.ISpotifyMedia
        public void addFavorite(IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void addMediaControllerCallback(IMediaInfoControllerCallback iMediaInfoControllerCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void closePlayList(IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public String getAppName() throws RemoteException {
            return null;
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public int getPlayState() throws RemoteException {
            return 0;
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void isLogin(IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void moveAppToFront(IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void openPlayList(IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void pause(IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void play(IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void removeFavorite(IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void removeMediaControllerCallback(IMediaInfoControllerCallback iMediaInfoControllerCallback) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void replay(IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void resume(IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void setRepeatMode(int i, IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void setShuffleMode(int i, IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void skipToNext(IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void skipToPrevious(IRequestCallBack iRequestCallBack) throws RemoteException {
        }

        @Override // com.vivid.spotify.ISpotifyMedia
        public void stop(IRequestCallBack iRequestCallBack) throws RemoteException {
        }
    }

    void addFavorite(IRequestCallBack iRequestCallBack) throws RemoteException;

    void addMediaControllerCallback(IMediaInfoControllerCallback iMediaInfoControllerCallback) throws RemoteException;

    void closePlayList(IRequestCallBack iRequestCallBack) throws RemoteException;

    String getAppName() throws RemoteException;

    int getPlayState() throws RemoteException;

    void isLogin(IRequestCallBack iRequestCallBack) throws RemoteException;

    void moveAppToFront(IRequestCallBack iRequestCallBack) throws RemoteException;

    void openPlayList(IRequestCallBack iRequestCallBack) throws RemoteException;

    void pause(IRequestCallBack iRequestCallBack) throws RemoteException;

    void play(IRequestCallBack iRequestCallBack) throws RemoteException;

    void removeFavorite(IRequestCallBack iRequestCallBack) throws RemoteException;

    void removeMediaControllerCallback(IMediaInfoControllerCallback iMediaInfoControllerCallback) throws RemoteException;

    void replay(IRequestCallBack iRequestCallBack) throws RemoteException;

    void resume(IRequestCallBack iRequestCallBack) throws RemoteException;

    void setRepeatMode(int i, IRequestCallBack iRequestCallBack) throws RemoteException;

    void setShuffleMode(int i, IRequestCallBack iRequestCallBack) throws RemoteException;

    void skipToNext(IRequestCallBack iRequestCallBack) throws RemoteException;

    void skipToPrevious(IRequestCallBack iRequestCallBack) throws RemoteException;

    void stop(IRequestCallBack iRequestCallBack) throws RemoteException;

    public static abstract class Stub extends Binder implements ISpotifyMedia {
        private static final String DESCRIPTOR = "com.vivid.spotify.ISpotifyMedia";
        static final int TRANSACTION_addFavorite = 8;
        static final int TRANSACTION_addMediaControllerCallback = 14;
        static final int TRANSACTION_closePlayList = 18;
        static final int TRANSACTION_getAppName = 1;
        static final int TRANSACTION_getPlayState = 13;
        static final int TRANSACTION_isLogin = 10;
        static final int TRANSACTION_moveAppToFront = 19;
        static final int TRANSACTION_openPlayList = 17;
        static final int TRANSACTION_pause = 3;
        static final int TRANSACTION_play = 2;
        static final int TRANSACTION_removeFavorite = 9;
        static final int TRANSACTION_removeMediaControllerCallback = 15;
        static final int TRANSACTION_replay = 16;
        static final int TRANSACTION_resume = 5;
        static final int TRANSACTION_setRepeatMode = 11;
        static final int TRANSACTION_setShuffleMode = 12;
        static final int TRANSACTION_skipToNext = 7;
        static final int TRANSACTION_skipToPrevious = 6;
        static final int TRANSACTION_stop = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISpotifyMedia asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISpotifyMedia)) {
                return (ISpotifyMedia) iInterfaceQueryLocalInterface;
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
                    String appName = getAppName();
                    parcel2.writeNoException();
                    parcel2.writeString(appName);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    play(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    pause(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    stop(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    resume(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    skipToPrevious(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    skipToNext(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    addFavorite(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeFavorite(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    isLogin(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRepeatMode(parcel.readInt(), IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setShuffleMode(parcel.readInt(), IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int playState = getPlayState();
                    parcel2.writeNoException();
                    parcel2.writeInt(playState);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    addMediaControllerCallback(IMediaInfoControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeMediaControllerCallback(IMediaInfoControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    replay(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    openPlayList(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    closePlayList(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    moveAppToFront(IRequestCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISpotifyMedia {
            public static ISpotifyMedia sDefaultImpl;
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

            @Override // com.vivid.spotify.ISpotifyMedia
            public String getAppName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void play(IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().play(iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void pause(IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pause(iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void stop(IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stop(iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void resume(IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resume(iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void skipToPrevious(IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().skipToPrevious(iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void skipToNext(IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().skipToNext(iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void addFavorite(IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addFavorite(iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void removeFavorite(IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeFavorite(iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void isLogin(IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().isLogin(iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void setRepeatMode(int i, IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRepeatMode(i, iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void setShuffleMode(int i, IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setShuffleMode(i, iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public int getPlayState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlayState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void addMediaControllerCallback(IMediaInfoControllerCallback iMediaInfoControllerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaInfoControllerCallback != null ? iMediaInfoControllerCallback.asBinder() : null);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addMediaControllerCallback(iMediaInfoControllerCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void removeMediaControllerCallback(IMediaInfoControllerCallback iMediaInfoControllerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaInfoControllerCallback != null ? iMediaInfoControllerCallback.asBinder() : null);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeMediaControllerCallback(iMediaInfoControllerCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void replay(IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().replay(iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void openPlayList(IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openPlayList(iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void closePlayList(IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closePlayList(iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.vivid.spotify.ISpotifyMedia
            public void moveAppToFront(IRequestCallBack iRequestCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRequestCallBack != null ? iRequestCallBack.asBinder() : null);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().moveAppToFront(iRequestCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISpotifyMedia iSpotifyMedia) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSpotifyMedia == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSpotifyMedia;
            return true;
        }

        public static ISpotifyMedia getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
