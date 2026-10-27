package spi;

import android.car.media.IXCarMediaControl;
import android.car.utils.Logs;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.text.TextUtils;
import com.android.car.media.BaseMediaApplication;
import com.android.car.media.byd.voice.AmazonMusicVoiceService;
import com.android.car.media.common.browse.MediaItemsRepository;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.byd.amazon.music.bean.BooleanResult;
import com.byd.amazon.music.bean.MusicItem;
import com.byd.amazon.music.bean.MusicItemResult;
import com.byd.amazon.music.bean.PlayModeResult;
import com.byd.amazon.music.bean.PlayStateResult;
import com.byd.amazon.music.bean.Result;
import com.byd.amazon.music.param.PlayMode;
import com.byd.amazon.music.param.PlayState;
import com.byd.amazon.music.param.VodType;
import com.byd.car.locale.ccTLD;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import spi.p4;
import spi.r4;

/* JADX INFO: compiled from: IMusicVoiceAidlInterface.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class q4 extends Binder implements IInterface {
    public q4() {
        attachInterface(this, "com.byd.amazon.music.IMusicVoiceAidlInterface");
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v119, types: [java.util.Map<java.lang.Class<?>, java.lang.Object>, java.util.concurrent.ConcurrentHashMap] */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        Result resultA;
        Result resultA2;
        Result resultA3;
        Result resultA4;
        Result result;
        Result result2;
        Result result3;
        Result resultB;
        Result resultB2;
        Result resultA5;
        Result resultB3;
        p4 c0019a;
        Result resultB4;
        Result result4;
        Result resultA6;
        BooleanResult booleanResult;
        PlayStateResult playStateResult;
        BooleanResult booleanResult2;
        PlayModeResult playModeResult;
        if (i == 1598968902) {
            parcel2.writeString("com.byd.amazon.music.IMusicVoiceAidlInterface");
            return true;
        }
        Object c0020a = null;
        switch (i) {
            case 1:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "play");
                int iG = AmazonMusicVoiceService.this.a.g();
                if (iG == 0) {
                    p7 p7Var = AmazonMusicVoiceService.this.a;
                    StringBuilder sb = new StringBuilder("play()");
                    StringBuilder sb2 = new StringBuilder("startAmazonMusicAppIfNeed");
                    PlaybackViewModel.e value = p7Var.c().j.getValue();
                    sb2.append(" isForceStart=");
                    sb2.append(false);
                    sb2.append(" isNeedStart=");
                    sb2.append(value == null);
                    if (value != null) {
                        sb2.append(" and return");
                    } else {
                        sb2.append(" and start music!!!");
                        p7.b = new CountDownLatch(1);
                        try {
                            p7Var.i();
                            ThreadUtils.runOnUiThreadDelayed(m0.e, 500L);
                            p7.b.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Logs.e("MusicVoiceControlImpl", sb2.toString());
                    p7Var.d();
                    if (p7Var.a != null) {
                        MusicItem musicItem = p7Var.b().b;
                        sb.append(" curMusicItemIsExist=");
                        sb.append(musicItem != null);
                        p7Var.a.play();
                        Logs.e("MusicVoiceControlImpl", sb.toString());
                        resultA = Result.b();
                    } else {
                        Logs.e("MusicVoiceControlImpl", sb.toString());
                        resultA = Result.a();
                    }
                    break;
                } else {
                    resultA = new Result(iG);
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                resultA.writeToParcel(parcel2, 1);
                return true;
            case 2:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar2 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "pause");
                int iG2 = AmazonMusicVoiceService.this.a.g();
                Result result5 = iG2 != 0 ? new Result(iG2) : AmazonMusicVoiceService.this.a.h();
                parcel2.writeNoException();
                parcel2.writeInt(1);
                result5.writeToParcel(parcel2, 1);
                return true;
            case 3:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar3 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "playPre");
                int iG3 = AmazonMusicVoiceService.this.a.g();
                if (iG3 != 0) {
                    resultA2 = new Result(iG3);
                } else {
                    p7 p7Var2 = AmazonMusicVoiceService.this.a;
                    if (p7Var2.f()) {
                        Logs.e("MusicVoiceControlImpl", "playPre error: RESULT_ERROR_NOT_SUPPORT_IN__AD_SCENE");
                        resultA2 = new Result(1005);
                    } else {
                        PlaybackViewModel.e value2 = p7Var2.c().j.getValue();
                        if (value2 != null && value2.f()) {
                            z = true;
                        }
                        if (z) {
                            Logs.d("MusicVoiceControlImpl", "playPre()");
                            p7Var2.d();
                            IXCarMediaControl iXCarMediaControl = p7Var2.a;
                            if (iXCarMediaControl != null) {
                                iXCarMediaControl.previous();
                                resultA2 = Result.b();
                            } else {
                                resultA2 = Result.a();
                            }
                        } else {
                            Logs.e("MusicVoiceControlImpl", "playNext error: RESULT_ERROR_NOT_SUPPORT_SKIP_PREVIOUS_ENABLED");
                            resultA2 = new Result(1007);
                        }
                    }
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                resultA2.writeToParcel(parcel2, 1);
                return true;
            case 4:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar4 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "playNext");
                int iG4 = AmazonMusicVoiceService.this.a.g();
                if (iG4 != 0) {
                    resultA3 = new Result(iG4);
                } else {
                    p7 p7Var3 = AmazonMusicVoiceService.this.a;
                    if (p7Var3.f()) {
                        Logs.e("MusicVoiceControlImpl", "playNext error: RESULT_ERROR_NOT_SUPPORT_IN__AD_SCENE");
                        resultA3 = new Result(1005);
                    } else {
                        PlaybackViewModel.e value3 = p7Var3.c().j.getValue();
                        if (value3 != null && value3.e()) {
                            z = true;
                        }
                        if (z) {
                            Logs.d("MusicVoiceControlImpl", "playNext()");
                            p7Var3.d();
                            IXCarMediaControl iXCarMediaControl2 = p7Var3.a;
                            if (iXCarMediaControl2 != null) {
                                iXCarMediaControl2.next();
                                resultA3 = Result.b();
                            } else {
                                resultA3 = Result.a();
                            }
                        } else {
                            Logs.e("MusicVoiceControlImpl", "playNext error: RESULT_ERROR_NOT_SUPPORT_SKIP_NEXT_ENABLED");
                            resultA3 = new Result(1008);
                        }
                    }
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                resultA3.writeToParcel(parcel2, 1);
                return true;
            case 5:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar5 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "replay");
                int iG5 = AmazonMusicVoiceService.this.a.g();
                if (iG5 != 0) {
                    resultA4 = new Result(iG5);
                } else {
                    p7 p7Var4 = AmazonMusicVoiceService.this.a;
                    if (p7Var4.f()) {
                        Logs.e("MusicVoiceControlImpl", "replay error: RESULT_ERROR_NOT_SUPPORT_IN__AD_SCENE");
                        resultA4 = new Result(1005);
                    } else {
                        PlaybackViewModel.e value4 = p7Var4.c().j.getValue();
                        if (value4 != null) {
                            if (((value4.c.getActions() & 256) != 0) != false) {
                                z = true;
                            }
                        }
                        if (z) {
                            Logs.d("MusicVoiceControlImpl", "replay()");
                            p7Var4.d();
                            IXCarMediaControl iXCarMediaControl3 = p7Var4.a;
                            if (iXCarMediaControl3 != null) {
                                iXCarMediaControl3.replay();
                                resultA4 = Result.b();
                            } else {
                                resultA4 = Result.a();
                            }
                        } else {
                            Logs.e("MusicVoiceControlImpl", "replay error: RESULT_ERROR_NOT_SUPPORT_SEEK_TO_ENABLED");
                            resultA4 = new Result(1006);
                        }
                    }
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                resultA4.writeToParcel(parcel2, 1);
                return true;
            case 6:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar6 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "setFavorite");
                int iG6 = AmazonMusicVoiceService.this.a.g();
                if (iG6 != 0) {
                    result = new Result(iG6);
                } else {
                    Objects.requireNonNull(AmazonMusicVoiceService.this.a);
                    Logs.d("MusicVoiceControlImpl", "setFavorite():  RESULT_ERROR_NOT_SUPPORT");
                    result = new Result(1002);
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                result.writeToParcel(parcel2, 1);
                return true;
            case 7:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar7 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "unSetFavorite");
                int iG7 = AmazonMusicVoiceService.this.a.g();
                if (iG7 != 0) {
                    result2 = new Result(iG7);
                } else {
                    Objects.requireNonNull(AmazonMusicVoiceService.this.a);
                    Logs.d("MusicVoiceControlImpl", "unSetFavorite():  RESULT_ERROR_NOT_SUPPORT");
                    result2 = new Result(1002);
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                result2.writeToParcel(parcel2, 1);
                return true;
            case 8:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                PlayMode playModeCreateFromParcel = parcel.readInt() != 0 ? PlayMode.CREATOR.createFromParcel(parcel) : null;
                AmazonMusicVoiceService.a aVar8 = (AmazonMusicVoiceService.a) this;
                StringBuilder sbK = c.k("setPlayMode, playMode name = ");
                sbK.append(playModeCreateFromParcel.name());
                Logs.d("AmazonMusicVoiceService", sbK.toString());
                int iG8 = AmazonMusicVoiceService.this.a.g();
                if (iG8 != 0) {
                    result3 = new Result(iG8);
                } else {
                    Objects.requireNonNull(AmazonMusicVoiceService.this.a);
                    Logs.d("MusicVoiceControlImpl", "setPlayMode():  RESULT_ERROR_NOT_SUPPORT");
                    result3 = new Result(1002);
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                result3.writeToParcel(parcel2, 1);
                return true;
            case 9:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar9 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "openPlayListPage");
                int iG9 = AmazonMusicVoiceService.this.a.g();
                if (iG9 != 0) {
                    resultB = new Result(iG9);
                } else {
                    p7 p7Var5 = AmazonMusicVoiceService.this.a;
                    if (p7Var5.f()) {
                        Logs.e("MusicVoiceControlImpl", "openPlayListPage error: RESULT_ERROR_NOT_SUPPORT_IN__AD_SCENE");
                        resultB = new Result(1005);
                    } else if (p7Var5.e()) {
                        Logs.d("MusicVoiceControlImpl", "openPlayListPage()");
                        p7Var5.c().k.postValue(Boolean.TRUE);
                        p7Var5.i();
                        resultB = Result.b();
                    } else {
                        Logs.e("MusicVoiceControlImpl", "openPlayListPage error: RESULT_ERROR_NOT_EXIST_PLAY_LIST");
                        resultB = new Result(1003);
                    }
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                resultB.writeToParcel(parcel2, 1);
                return true;
            case 10:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar10 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "closePlayListPage");
                int iG10 = AmazonMusicVoiceService.this.a.g();
                if (iG10 != 0) {
                    resultB2 = new Result(iG10);
                } else {
                    p7 p7Var6 = AmazonMusicVoiceService.this.a;
                    if (p7Var6.f()) {
                        Logs.e("MusicVoiceControlImpl", "closePlayListPage error: RESULT_ERROR_NOT_SUPPORT_IN__AD_SCENE");
                        resultB2 = new Result(1005);
                    } else if (p7Var6.e()) {
                        Logs.d("MusicVoiceControlImpl", "closePlayListPage()");
                        p7Var6.c().k.postValue(Boolean.FALSE);
                        resultB2 = Result.b();
                    } else {
                        Logs.e("MusicVoiceControlImpl", "closePlayListPage error: RESULT_ERROR_NOT_EXIST_PLAY_LIST");
                        resultB2 = new Result(1003);
                    }
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                resultB2.writeToParcel(parcel2, 1);
                return true;
            case 11:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar11 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "stop");
                int iG11 = AmazonMusicVoiceService.this.a.g();
                if (iG11 != 0) {
                    resultA5 = new Result(iG11);
                } else {
                    p7 p7Var7 = AmazonMusicVoiceService.this.a;
                    Objects.requireNonNull(p7Var7);
                    Logs.d("MusicVoiceControlImpl", "stop()");
                    p7Var7.d();
                    IXCarMediaControl iXCarMediaControl4 = p7Var7.a;
                    if (iXCarMediaControl4 != null) {
                        iXCarMediaControl4.stop();
                        resultA5 = Result.b();
                    } else {
                        resultA5 = Result.a();
                    }
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                resultA5.writeToParcel(parcel2, 1);
                return true;
            case 12:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar12 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "exitMusic");
                int iG12 = AmazonMusicVoiceService.this.a.g();
                if (iG12 != 0) {
                    resultB3 = new Result(iG12);
                } else {
                    p7 p7Var8 = AmazonMusicVoiceService.this.a;
                    Objects.requireNonNull(p7Var8);
                    Logs.d("MusicVoiceControlImpl", "exitMusic()");
                    p7Var8.h();
                    ActivityUtils.finishAllActivities();
                    resultB3 = Result.b();
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                resultB3.writeToParcel(parcel2, 1);
                return true;
            case 13:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                String string = parcel.readString();
                VodType vodTypeCreateFromParcel = parcel.readInt() != 0 ? VodType.CREATOR.createFromParcel(parcel) : null;
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder == null) {
                    c0019a = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.byd.amazon.music.IMusicItemsCallBack");
                    c0019a = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof p4)) ? new p4.a.C0019a(strongBinder) : (p4) iInterfaceQueryLocalInterface;
                }
                AmazonMusicVoiceService.a aVar13 = (AmazonMusicVoiceService.a) this;
                StringBuilder sbK2 = c.k("playByVod, vodType name=");
                sbK2.append(vodTypeCreateFromParcel.name());
                sbK2.append(", vodType value=");
                sbK2.append(vodTypeCreateFromParcel.name());
                Logs.d("AmazonMusicVoiceService", sbK2.toString());
                int iG13 = AmazonMusicVoiceService.this.a.g();
                if (iG13 == 0) {
                    p7 p7Var9 = AmazonMusicVoiceService.this.a;
                    Objects.requireNonNull(p7Var9);
                    Locale locale = BaseMediaApplication.b.getResources().getConfiguration().getLocales().get(0);
                    if (locale != null) {
                        String language = locale.getLanguage();
                        if (!TextUtils.isEmpty(language)) {
                            Logs.d("CommonUtils", "isNotVoiceSupportLanguage = " + language);
                            if (!"en".equals(language) && !ccTLD.FRANCE.equals(language) && !ccTLD.PORTUGAL.equals(language) && !ccTLD.SPAIN.equals(language) && !ccTLD.GERMANY.equals(language) && !ccTLD.ITALY.equals(language) && !"ja".equals(language)) {
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        Logs.d("MusicVoiceControlImpl", "playByVod() error: RESULT_ERROR_NOT_SUPPORT_LANGUAGES()");
                        resultB4 = new Result(1004);
                    } else if (p7Var9.f()) {
                        Logs.e("MusicVoiceControlImpl", "playByVod error: RESULT_ERROR_NOT_SUPPORT_IN__AD_SCENE");
                        resultB4 = new Result(1005);
                    } else {
                        Logs.d("MusicVoiceControlImpl", "playByVod() vodType = " + vodTypeCreateFromParcel + " name = " + string + "isMainThread=" + ThreadUtils.isMainThread());
                        p7.b = new CountDownLatch(1);
                        try {
                            p7Var9.i();
                            ThreadUtils.runOnUiThreadDelayed(m0.f, 500L);
                            p7.b.await();
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        String strName = vodTypeCreateFromParcel.name();
                        if (string == null || string.isEmpty()) {
                            Logs.d("MusicVoiceControlImpl", "queryDataByTag() query text is null");
                        } else {
                            Logs.d("MusicVoiceControlImpl", "queryDataByTag() filterTag = " + strName + " queryText = " + string);
                            MediaItemsRepository mediaItemsRepositoryA = MediaItemsRepository.a(BaseMediaApplication.b, 1);
                            o7 o7Var = new o7(p7Var9, string, strName, c0019a);
                            mediaItemsRepositoryA.h = string;
                            mediaItemsRepositoryA.g = o7Var;
                        }
                        o3 o3VarB = o3.b();
                        StringBuilder sbN = c.n(string, ",");
                        sbN.append(p7Var9.a(vodTypeCreateFromParcel.name(), null));
                        r3 r3Var = new r3("CMD_VOICE_PLAY_BY_VOID", sbN.toString());
                        synchronized (o3VarB.c) {
                            o3VarB.c.put(r3.class, r3Var);
                            break;
                        }
                        o3VarB.f(r3Var);
                        resultB4 = Result.b();
                    }
                    break;
                } else {
                    resultB4 = new Result(iG13);
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                resultB4.writeToParcel(parcel2, 1);
                return true;
            case 14:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar14 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "playByFavorite");
                int iG14 = AmazonMusicVoiceService.this.a.g();
                if (iG14 != 0) {
                    result4 = new Result(iG14);
                } else {
                    Objects.requireNonNull(AmazonMusicVoiceService.this.a);
                    Logs.d("MusicVoiceControlImpl", "playByFavorite():  RESULT_ERROR_NOT_SUPPORT");
                    result4 = new Result(1002);
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                result4.writeToParcel(parcel2, 1);
                return true;
            case 15:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                String string2 = parcel.readString();
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.byd.amazon.music.IPlayStateCallBack");
                    c0020a = (iInterfaceQueryLocalInterface2 == null || !(iInterfaceQueryLocalInterface2 instanceof r4)) ? new r4.a.C0020a(strongBinder2) : (r4) iInterfaceQueryLocalInterface2;
                }
                AmazonMusicVoiceService.a aVar15 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "playByFavorite");
                int iG15 = AmazonMusicVoiceService.this.a.g();
                if (iG15 != 0) {
                    resultA6 = new Result(iG15);
                } else {
                    p7 p7Var10 = AmazonMusicVoiceService.this.a;
                    if (p7Var10.f()) {
                        Logs.e("MusicVoiceControlImpl", "playById error: RESULT_ERROR_NOT_SUPPORT_IN__AD_SCENE");
                        resultA6 = new Result(1005);
                    } else if (string2 == null || string2.isEmpty()) {
                        Logs.d("MusicVoiceControlImpl", "playById() play mediaId is null");
                        resultA6 = Result.a();
                    } else {
                        Logs.d("MusicVoiceControlImpl", "playById() start mediaId = " + string2);
                        if (string2.isEmpty()) {
                            Logs.d("MusicVoiceControlImpl", "playByMediaId() play mediaId is null");
                        } else {
                            new Handler(Looper.getMainLooper()).postDelayed(new h6(p7Var10, string2, c0020a, 2), 500L);
                        }
                        resultA6 = Result.b();
                    }
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                resultA6.writeToParcel(parcel2, 1);
                return true;
            case 16:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar16 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "isLogIn");
                int iG16 = AmazonMusicVoiceService.this.a.g();
                if (iG16 != 0) {
                    booleanResult = new BooleanResult(iG16);
                } else {
                    boolean z = AmazonMusicVoiceService.this.a.g() == 0;
                    BooleanResult booleanResult3 = new BooleanResult(0);
                    booleanResult3.b = z;
                    StringBuilder sbK3 = c.k("isLogIn()  isLogIn = ");
                    sbK3.append(booleanResult3.b);
                    Logs.d("MusicVoiceControlImpl", sbK3.toString());
                    booleanResult = booleanResult3;
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                booleanResult.writeToParcel(parcel2, 1);
                return true;
            case 17:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar17 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "getPlayState");
                int iG17 = AmazonMusicVoiceService.this.a.g();
                if (iG17 != 0) {
                    playStateResult = new PlayStateResult(iG17);
                } else {
                    p7 p7Var11 = AmazonMusicVoiceService.this.a;
                    p7Var11.d();
                    PlayStateResult playStateResult2 = new PlayStateResult(0);
                    PlayState playState = PlayState.STATE_NONE;
                    playStateResult2.b = playState;
                    IXCarMediaControl iXCarMediaControl5 = p7Var11.a;
                    if (iXCarMediaControl5 != null) {
                        switch (iXCarMediaControl5.getPlayState()) {
                            case 1:
                                playState = PlayState.STATE_STOPPED;
                                break;
                            case 2:
                                playState = PlayState.STATE_PAUSED;
                                break;
                            case 3:
                                playState = PlayState.STATE_PLAYING;
                                break;
                            case 4:
                                playState = PlayState.STATE_FAST_FORWARDING;
                                break;
                            case 5:
                                playState = PlayState.STATE_REWINDING;
                                break;
                            case 6:
                                playState = PlayState.STATE_BUFFERING;
                                break;
                            case 7:
                                playState = PlayState.STATE_ERROR;
                                break;
                            case 8:
                                playState = PlayState.STATE_CONNECTING;
                                break;
                            case 9:
                                playState = PlayState.STATE_SKIPPING_TO_PREVIOUS;
                                break;
                            case 10:
                                playState = PlayState.STATE_SKIPPING_TO_NEXT;
                                break;
                            case 11:
                                playState = PlayState.STATE_SKIPPING_TO_QUEUE_ITEM;
                                break;
                        }
                        playStateResult2.b = playState;
                    }
                    StringBuilder sbK4 = c.k("getPlayState()  status = ");
                    sbK4.append(playStateResult2.b.name());
                    Logs.d("MusicVoiceControlImpl", sbK4.toString());
                    playStateResult = playStateResult2;
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                playStateResult.writeToParcel(parcel2, 1);
                return true;
            case 18:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar18 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "getNowPlayingSong");
                int iG18 = AmazonMusicVoiceService.this.a.g();
                MusicItemResult musicItemResult = iG18 != 0 ? new MusicItemResult(iG18) : AmazonMusicVoiceService.this.a.b();
                parcel2.writeNoException();
                parcel2.writeInt(1);
                musicItemResult.writeToParcel(parcel2, 1);
                return true;
            case 19:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar19 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "isPlayListPageOpen");
                int iG19 = AmazonMusicVoiceService.this.a.g();
                if (iG19 != 0) {
                    booleanResult2 = new BooleanResult(iG19);
                } else {
                    boolean zEquals = Boolean.TRUE.equals(AmazonMusicVoiceService.this.a.c().k.getValue());
                    BooleanResult booleanResult4 = new BooleanResult(0);
                    booleanResult4.b = zEquals;
                    Logs.d("MusicVoiceControlImpl", "isPlayListPageOpen() and status = " + zEquals);
                    booleanResult2 = booleanResult4;
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                booleanResult2.writeToParcel(parcel2, 1);
                return true;
            case 20:
                parcel.enforceInterface("com.byd.amazon.music.IMusicVoiceAidlInterface");
                AmazonMusicVoiceService.a aVar20 = (AmazonMusicVoiceService.a) this;
                Logs.d("AmazonMusicVoiceService", "getCurPlayMode");
                int iG20 = AmazonMusicVoiceService.this.a.g();
                if (iG20 != 0) {
                    playModeResult = new PlayModeResult(iG20);
                } else {
                    Objects.requireNonNull(AmazonMusicVoiceService.this.a);
                    Logs.d("MusicVoiceControlImpl", "getCurPlayMode():  RESULT_ERROR_NOT_SUPPORT");
                    playModeResult = new PlayModeResult(1002);
                }
                parcel2.writeNoException();
                parcel2.writeInt(1);
                playModeResult.writeToParcel(parcel2, 1);
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
