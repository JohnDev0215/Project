package com.byd.amazon.music;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.amazon.music.IMusicItemsCallBack;
import com.byd.amazon.music.IPlayStateCallBack;
import com.byd.amazon.music.bean.BooleanResult;
import com.byd.amazon.music.bean.MusicItemResult;
import com.byd.amazon.music.bean.PlayModeResult;
import com.byd.amazon.music.bean.PlayStateResult;
import com.byd.amazon.music.bean.Result;
import com.byd.amazon.music.param.PlayMode;
import com.byd.amazon.music.param.VodType;

/* JADX INFO: loaded from: classes.dex */
public interface IMusicVoiceAidlInterface extends IInterface {

    public static class Default implements IMusicVoiceAidlInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result closePlayListPage() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result exitMusic() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public PlayModeResult getCurPlayMode() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public MusicItemResult getNowPlayingSong() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public PlayStateResult getPlayState() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public BooleanResult isLogIn() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public BooleanResult isPlayListPageOpen() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result openPlayListPage() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result pause() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result play() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result playByFavorite() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result playById(String str, IPlayStateCallBack iPlayStateCallBack) throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result playByVod(String str, VodType vodType, IMusicItemsCallBack iMusicItemsCallBack) throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result playNext() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result playPre() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result replay() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result setFavorite() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result setPlayMode(PlayMode playMode) throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result stop() throws RemoteException {
            return null;
        }

        @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
        public Result unSetFavorite() throws RemoteException {
            return null;
        }
    }

    Result closePlayListPage() throws RemoteException;

    Result exitMusic() throws RemoteException;

    PlayModeResult getCurPlayMode() throws RemoteException;

    MusicItemResult getNowPlayingSong() throws RemoteException;

    PlayStateResult getPlayState() throws RemoteException;

    BooleanResult isLogIn() throws RemoteException;

    BooleanResult isPlayListPageOpen() throws RemoteException;

    Result openPlayListPage() throws RemoteException;

    Result pause() throws RemoteException;

    Result play() throws RemoteException;

    Result playByFavorite() throws RemoteException;

    Result playById(String str, IPlayStateCallBack iPlayStateCallBack) throws RemoteException;

    Result playByVod(String str, VodType vodType, IMusicItemsCallBack iMusicItemsCallBack) throws RemoteException;

    Result playNext() throws RemoteException;

    Result playPre() throws RemoteException;

    Result replay() throws RemoteException;

    Result setFavorite() throws RemoteException;

    Result setPlayMode(PlayMode playMode) throws RemoteException;

    Result stop() throws RemoteException;

    Result unSetFavorite() throws RemoteException;

    public static abstract class Stub extends Binder implements IMusicVoiceAidlInterface {
        private static final String DESCRIPTOR = "com.byd.amazon.music.IMusicVoiceAidlInterface";
        static final int TRANSACTION_closePlayListPage = 10;
        static final int TRANSACTION_exitMusic = 12;
        static final int TRANSACTION_getCurPlayMode = 20;
        static final int TRANSACTION_getNowPlayingSong = 18;
        static final int TRANSACTION_getPlayState = 17;
        static final int TRANSACTION_isLogIn = 16;
        static final int TRANSACTION_isPlayListPageOpen = 19;
        static final int TRANSACTION_openPlayListPage = 9;
        static final int TRANSACTION_pause = 2;
        static final int TRANSACTION_play = 1;
        static final int TRANSACTION_playByFavorite = 14;
        static final int TRANSACTION_playById = 15;
        static final int TRANSACTION_playByVod = 13;
        static final int TRANSACTION_playNext = 4;
        static final int TRANSACTION_playPre = 3;
        static final int TRANSACTION_replay = 5;
        static final int TRANSACTION_setFavorite = 6;
        static final int TRANSACTION_setPlayMode = 8;
        static final int TRANSACTION_stop = 11;
        static final int TRANSACTION_unSetFavorite = 7;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMusicVoiceAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMusicVoiceAidlInterface)) {
                return (IMusicVoiceAidlInterface) iInterfaceQueryLocalInterface;
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
                    Result resultPlay = play();
                    parcel2.writeNoException();
                    if (resultPlay != null) {
                        parcel2.writeInt(1);
                        resultPlay.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result resultPause = pause();
                    parcel2.writeNoException();
                    if (resultPause != null) {
                        parcel2.writeInt(1);
                        resultPause.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result resultPlayPre = playPre();
                    parcel2.writeNoException();
                    if (resultPlayPre != null) {
                        parcel2.writeInt(1);
                        resultPlayPre.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result resultPlayNext = playNext();
                    parcel2.writeNoException();
                    if (resultPlayNext != null) {
                        parcel2.writeInt(1);
                        resultPlayNext.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result resultReplay = replay();
                    parcel2.writeNoException();
                    if (resultReplay != null) {
                        parcel2.writeInt(1);
                        resultReplay.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result favorite = setFavorite();
                    parcel2.writeNoException();
                    if (favorite != null) {
                        parcel2.writeInt(1);
                        favorite.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result resultUnSetFavorite = unSetFavorite();
                    parcel2.writeNoException();
                    if (resultUnSetFavorite != null) {
                        parcel2.writeInt(1);
                        resultUnSetFavorite.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result playMode = setPlayMode(parcel.readInt() != 0 ? PlayMode.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (playMode != null) {
                        parcel2.writeInt(1);
                        playMode.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result resultOpenPlayListPage = openPlayListPage();
                    parcel2.writeNoException();
                    if (resultOpenPlayListPage != null) {
                        parcel2.writeInt(1);
                        resultOpenPlayListPage.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result resultClosePlayListPage = closePlayListPage();
                    parcel2.writeNoException();
                    if (resultClosePlayListPage != null) {
                        parcel2.writeInt(1);
                        resultClosePlayListPage.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result resultStop = stop();
                    parcel2.writeNoException();
                    if (resultStop != null) {
                        parcel2.writeInt(1);
                        resultStop.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result resultExitMusic = exitMusic();
                    parcel2.writeNoException();
                    if (resultExitMusic != null) {
                        parcel2.writeInt(1);
                        resultExitMusic.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result resultPlayByVod = playByVod(parcel.readString(), parcel.readInt() != 0 ? VodType.CREATOR.createFromParcel(parcel) : null, IMusicItemsCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (resultPlayByVod != null) {
                        parcel2.writeInt(1);
                        resultPlayByVod.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result resultPlayByFavorite = playByFavorite();
                    parcel2.writeNoException();
                    if (resultPlayByFavorite != null) {
                        parcel2.writeInt(1);
                        resultPlayByFavorite.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    Result resultPlayById = playById(parcel.readString(), IPlayStateCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (resultPlayById != null) {
                        parcel2.writeInt(1);
                        resultPlayById.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    BooleanResult booleanResultIsLogIn = isLogIn();
                    parcel2.writeNoException();
                    if (booleanResultIsLogIn != null) {
                        parcel2.writeInt(1);
                        booleanResultIsLogIn.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    PlayStateResult playState = getPlayState();
                    parcel2.writeNoException();
                    if (playState != null) {
                        parcel2.writeInt(1);
                        playState.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    MusicItemResult nowPlayingSong = getNowPlayingSong();
                    parcel2.writeNoException();
                    if (nowPlayingSong != null) {
                        parcel2.writeInt(1);
                        nowPlayingSong.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    BooleanResult booleanResultIsPlayListPageOpen = isPlayListPageOpen();
                    parcel2.writeNoException();
                    if (booleanResultIsPlayListPageOpen != null) {
                        parcel2.writeInt(1);
                        booleanResultIsPlayListPageOpen.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    PlayModeResult curPlayMode = getCurPlayMode();
                    parcel2.writeNoException();
                    if (curPlayMode != null) {
                        parcel2.writeInt(1);
                        curPlayMode.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMusicVoiceAidlInterface {
            public static IMusicVoiceAidlInterface sDefaultImpl;
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

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result play() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().play();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result pause() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pause();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result playPre() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playPre();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result playNext() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playNext();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result replay() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().replay();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result setFavorite() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setFavorite();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result unSetFavorite() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unSetFavorite();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result setPlayMode(PlayMode playMode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (playMode != null) {
                        parcelObtain.writeInt(1);
                        playMode.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPlayMode(playMode);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result openPlayListPage() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openPlayListPage();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result closePlayListPage() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().closePlayListPage();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result stop() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stop();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result exitMusic() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().exitMusic();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result playByVod(String str, VodType vodType, IMusicItemsCallBack iMusicItemsCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (vodType != null) {
                        parcelObtain.writeInt(1);
                        vodType.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iMusicItemsCallBack != null ? iMusicItemsCallBack.asBinder() : null);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playByVod(str, vodType, iMusicItemsCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result playByFavorite() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playByFavorite();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public Result playById(String str, IPlayStateCallBack iPlayStateCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iPlayStateCallBack != null ? iPlayStateCallBack.asBinder() : null);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playById(str, iPlayStateCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Result.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public BooleanResult isLogIn() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLogIn();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? BooleanResult.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public PlayStateResult getPlayState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlayState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PlayStateResult.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public MusicItemResult getNowPlayingSong() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNowPlayingSong();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? MusicItemResult.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public BooleanResult isPlayListPageOpen() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPlayListPageOpen();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? BooleanResult.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.amazon.music.IMusicVoiceAidlInterface
            public PlayModeResult getCurPlayMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurPlayMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PlayModeResult.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMusicVoiceAidlInterface iMusicVoiceAidlInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMusicVoiceAidlInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMusicVoiceAidlInterface;
            return true;
        }

        public static IMusicVoiceAidlInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
