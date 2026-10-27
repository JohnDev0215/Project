package com.byd.vrassistant.conversation;

import android.content.Context;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.audiocontrol.result.PlayControlResult;
import cerence.ark.assistant.controller.domain.radio.result.FavoriteControlResult;
import cerence.ark.assistant.controller.enumeration.MediaProviderEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.customizedimpl.CustomRadioDomain;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.utils.RLog;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class RadioHelper {
    private final String TAG;
    private int TimeOut;
    private Context mContext;

    private RadioHelper() {
        this.TAG = "NEU_Radio RadioHelper";
        this.mContext = BydVoiceApplication.getContext();
        this.TimeOut = 4;
    }

    private static class RadioHelperHolder {
        private static final RadioHelper radioHelper = new RadioHelper();

        private RadioHelperHolder() {
        }
    }

    public static RadioHelper getInstance(Context context) {
        return RadioHelperHolder.radioHelper;
    }

    public void openRadioHelper(IResponseSender<BaseResult> iResponseSender, boolean z) {
        RLog.i("NEU_Radio RadioHelper", "openRadioHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).openradio(countDownLatch, z) == -1) {
                RLog.i("NEU_Radio RadioHelper", "调用openradio 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlRadio】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "controlRadio RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlRadio】,result【OK】");
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlRadio】,result【ERROR】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "controlRadio off e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlRadio】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void openRadioBandHelper(IResponseSender<BaseResult> iResponseSender, boolean z, int i) {
        RLog.i("NEU_Radio RadioHelper", "openRadioHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).openradioband(countDownLatch, z, i) == -1) {
                RLog.i("NEU_Radio RadioHelper", "调用 openRadioBandHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【openRadioHelper】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "openRadioBandHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【openRadioHelper】,result【OK】");
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【openRadioHelper】,result【ERROR】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "openRadioBandHelper off e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【openRadioHelper】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void muteRadioHelper(IResponseSender<BaseResult> iResponseSender) {
        RLog.i("NEU_Radio RadioHelper", "muteRadioHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).muteRadio(countDownLatch) == -1) {
                RLog.i("NEU_Radio RadioHelper", "muteRadio 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlRadio】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "muteRadioHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlRadio】,result【OK】");
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlRadio】,result【ERROR】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "muteRadioHelper off e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlRadio】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void SearchRadioHelper(IResponseSender<BaseResult> iResponseSender) {
        RLog.i("NEU_Radio RadioHelper", "SearchRadioHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).Search_orStopSearch(16, countDownLatch) == -1) {
                RLog.i("NEU_Radio RadioHelper", "SearchRadioHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【scanControl】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "SearchRadioHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【scanControl】,result【OK】");
                return;
            }
            if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【scanControl】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【scanControl】,result【ALREADY_EXPECTED】");
            } else if (CustomRadioDomain.RadioResult == 2) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【scanControl】,result【UNSUPPORTED】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "SearchRadioHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【scanControl】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void StopSearchRadioHelper(IResponseSender<BaseResult> iResponseSender) {
        RLog.i("NEU_Radio RadioHelper", "StopSearchRadioHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).Search_orStopSearch(18, countDownLatch) == -1) {
                RLog.i("NEU_Radio RadioHelper", "StopSearchRadioHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【scanControl】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "StopSearchRadioHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【scanControl】,result【OK】");
                return;
            }
            if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【scanControl】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【scanControl】,result【ALREADY_EXPECTED】");
            } else if (CustomRadioDomain.RadioResult == 2) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【scanControl】,result【UNSUPPORTED】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "StopSearchRadioHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【scanControl】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void OpenFavoriteStationListHelper(IResponseSender<BaseResult> iResponseSender) {
        RLog.i("NEU_Radio RadioHelper", "OpenFavoriteStationListHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).openFavoriteStationList(countDownLatch, true) == -1) {
                RLog.i("NEU_Radio RadioHelper", "OpenFavoriteStationListHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "OpenFavoriteStationListHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "OpenFavoriteStationListHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            e.printStackTrace();
        }
    }

    public void ControlfavoriteHelper(int i, IResponseSender<FavoriteControlResult> iResponseSender) {
        RLog.i("NEU_Radio RadioHelper", "ControlfavoriteHelper start");
        FavoriteControlResult favoriteControlResult = new FavoriteControlResult();
        favoriteControlResult.setRadioTitle("");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).Controlfavorite(i, countDownLatch) == -1) {
                RLog.i("NEU_Radio RadioHelper", "ControlfavoriteHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControl】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "ControlfavoriteHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                favoriteControlResult.setRadioTitle(CustomRadioDomain.RadioTitle);
                iResponseSender.send(new Response("OK", favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControl】,result【OK】");
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControl】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControl】,result【ALREADY_EXPECTED】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "ControlfavoriteHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControl】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void ControlfavoriteForAudioHelper(int i, IResponseSender<cerence.ark.assistant.controller.domain.audiocontrol.result.FavoriteControlResult> iResponseSender) {
        RLog.i("NEU_Radio RadioHelper", "ControlfavoriteForAudioHelper start");
        cerence.ark.assistant.controller.domain.audiocontrol.result.FavoriteControlResult favoriteControlResult = new cerence.ark.assistant.controller.domain.audiocontrol.result.FavoriteControlResult();
        favoriteControlResult.setMediaProvider(MediaProviderEnum.LOCAL);
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).Controlfavorite(i, countDownLatch) == -1) {
                RLog.i("NEU_Radio RadioHelper", "ControlfavoriteForAudioHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "ControlfavoriteHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【OK】");
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ALREADY_EXPECTED】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "ControlfavoriteHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void OpenStationListHelper(IResponseSender<BaseResult> iResponseSender) {
        RLog.i("NEU_Radio RadioHelper", "OpenStationListHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).openStationList(countDownLatch) == -1) {
                RLog.i("NEU_Radio RadioHelper", "OpenStationListHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【radioStationList】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "OpenStationListHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【radioStationList】,result【OK】");
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【radioStationList】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【radioStationList】,result【ALREADY_EXPECTED】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "OpenStationListHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【radioStationList】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void PlayFrequencyHelper(int i, int i2, IResponseSender<BaseResult> iResponseSender) {
        RLog.i("NEU_Radio RadioHelper", "PlayFrequencyHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (i2 == -1) {
                i2 = RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaPlayType();
            }
            if (RadioSdk.getInstance(this.mContext).playFrequency(i, i2, countDownLatch) == -1) {
                RLog.i("NEU_Radio RadioHelper", "PlayFrequencyHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "PlayFrequencyHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【OK】");
                return;
            }
            if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【ERROR】");
                return;
            }
            if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【ALREADY_EXPECTED】");
            } else if (CustomRadioDomain.RadioResult == 2) {
                iResponseSender.send(new Response("OUT_OF_RANGE"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【OUT_OF_RANGE】");
            } else if (CustomRadioDomain.RadioResult == 3) {
                iResponseSender.send(new Response(ResultCodeEnum.FREQUENCY_INVALID));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【FREQUENCY_INVALID】");
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【ERROR】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "PlayFrequencyHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void ChangeBandHelper(int i, IResponseSender<BaseResult> iResponseSender) {
        RLog.i("NEU_Radio RadioHelper", "ChangeBandHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).changeBand(i, countDownLatch) == -1) {
                RLog.i("NEU_Radio RadioHelper", "ChangeBandHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "ChangeBandHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0 || CustomRadioDomain.RadioResult == 1) {
                RLog.i("NEU_Radio RadioHelper", "ChangeBandHelper for play");
                if (RadioSdk.getInstance(this.mContext).getPlayStatus() != 0) {
                    AudioSdk.getInstance(BydVoiceApplication.getContext()).playMusic(0);
                }
            }
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【OK】");
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【ALREADY_EXPECTED】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "ChangeBandHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void playPreFreHelper(IResponseSender<PlayControlResult> iResponseSender) {
        RLog.i("NEU_Radio RadioHelper", "playPreFreHelper start");
        PlayControlResult playControlResult = new PlayControlResult();
        playControlResult.setMediaProvider(MediaProviderEnum.LOCAL);
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).playPreFre(countDownLatch) == -1) {
                RLog.i("NEU_Radio RadioHelper", "playPreFreHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "playPreFreHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OK】");
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ALREADY_EXPECTED】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "playPreFreHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void playNextFreHelper(IResponseSender<PlayControlResult> iResponseSender) {
        RLog.i("NEU_Radio RadioHelper", "playNextFreHelper start");
        PlayControlResult playControlResult = new PlayControlResult();
        playControlResult.setMediaProvider(MediaProviderEnum.LOCAL);
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).playNextFre(countDownLatch) == -1) {
                RLog.i("NEU_Radio RadioHelper", "playNextFreHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "playNextFreHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OK】");
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ALREADY_EXPECTED】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "playNextFreHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void playFavRadioHelper(IResponseSender<BaseResult> iResponseSender) {
        RLog.i("NEU_Radio RadioHelper", "playFavRadioHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).playFavoriteFre(countDownLatch) == -1) {
                RLog.i("NEU_Radio RadioHelper", "playFavRadioHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playFavRadio】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "playFavRadioHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                RadioSdk.getInstance(this.mContext).openFavoriteStationList(new CountDownLatch(1), false);
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playFavRadio】,result【OK】");
                return;
            }
            if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playFavRadio】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playFavRadio】,result【ALREADY_EXPECTED】");
            } else if (CustomRadioDomain.RadioResult == 2) {
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playFavRadio】,result【MEDIA_NOT_FOUND】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "playFavRadioHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playFavRadio】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public Boolean IfRadioWorking() {
        if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.LOCAL) && (RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode() == 0 || RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode() == 11 || RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode() == 12)) {
            RLog.i("NEU_Radio RadioHelper", "IfRadioWorking  working");
            return true;
        }
        RLog.i("NEU_Radio RadioHelper", "IfRadioWorking not working");
        return false;
    }

    public Boolean IfDabWorking() {
        int iSelectDabVersion = selectDabVersion();
        if (iSelectDabVersion == -1) {
            RLog.i("NEU_Radio RadioHelper", "IfDabWorking not  working");
            return false;
        }
        if (iSelectDabVersion == 1) {
            String packetAudio = RadioSdk.getInstance(BydVoiceApplication.getContext()).getPacketAudio();
            RLog.i("NEU_Radio RadioHelper", "IfDabWorking: package_name==" + packetAudio);
            if (LocalMediaManager.LOCALMEDIA_PACKAGE_NAME.equals(packetAudio)) {
                if (RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode() == 13) {
                    RLog.i("NEU_Radio RadioHelper", "IfDabWorking  working");
                    return true;
                }
                RLog.i("NEU_Radio RadioHelper", "IfDabWorking not  working");
                return false;
            }
            RLog.i("NEU_Radio RadioHelper", "IfDabWorking not  working");
            return false;
        }
        if (iSelectDabVersion == 2) {
            String packetAudio2 = RadioSdk.getInstance(BydVoiceApplication.getContext()).getPacketAudio();
            RLog.i("NEU_Radio RadioHelper", "IfDabWorking dab2: package_name==" + packetAudio2);
            if (LocalMediaManager.LOCALMEDIA_PACKAGE_NAME.equals(packetAudio2) && RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode() == 13) {
                RLog.i("NEU_Radio RadioHelper", "IfDabWorking   working");
                return true;
            }
            RLog.i("NEU_Radio RadioHelper", "IfDabWorking not  working");
            return false;
        }
        RLog.i("NEU_Radio RadioHelper", "IfDabWorking not  working");
        return false;
    }

    public Boolean IfRadioShow() {
        if (IfRadioWorking().booleanValue()) {
            return false;
        }
        return true;
    }

    public Boolean IfDABShow() {
        if (IfDabWorking().booleanValue()) {
            return false;
        }
        return true;
    }

    public int selectDabVersion() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        int i = -1;
        try {
            RLog.i("NEU_Radio RadioHelper", "start selectDabVersion dabversion==-1");
            RadioSdk.getInstance(BydVoiceApplication.getContext()).getDabSupport(countDownLatch);
            countDownLatch.await(4L, TimeUnit.SECONDS);
            i = RadioSdk.getInstance(BydVoiceApplication.getContext()).dabsupport;
            RLog.i("NEU_Radio RadioHelper", "start selectDabVersion dabversion==" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RLog.i("NEU_Radio RadioHelper", "selectDabVersion ==" + i);
        return i;
    }

    public int CheckFreModeHelper(double d) {
        RLog.i("NEU_Radio RadioHelper", "CheckFreModeHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CustomRadioDomain.RadioMode = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (RadioSdk.getInstance(this.mContext).checkradiomode(countDownLatch, d) == -1) {
                return -1;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Radio RadioHelper", "CheckFreModeHelper RadioResult==" + CustomRadioDomain.RadioResult);
            return CustomRadioDomain.RadioResult == 0 ? 0 : -1;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioHelper", "CheckFreModeHelper  e ==" + e.toString());
            e.printStackTrace();
            return -1;
        }
    }
}
