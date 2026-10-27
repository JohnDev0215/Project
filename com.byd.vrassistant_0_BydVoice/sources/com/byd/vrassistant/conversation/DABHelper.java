package com.byd.vrassistant.conversation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.audiocontrol.result.PlayControlResult;
import cerence.ark.assistant.controller.domain.radio.result.FavoriteControlDabResult;
import cerence.ark.assistant.controller.enumeration.MediaProviderEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.customizedimpl.CustomRadioDomain;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.RLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class DABHelper {
    private final String TAG = "NEU_DAB DABHelper";
    private final String Action = "com.microntek.irkeyDown";
    private final String ActionPlay = " byd.intent.action.MEDIA_BUTTON";
    private Context mContext = BydVoiceApplication.getContext();
    private int TimeOut = 4;
    private int IR_PREV = 45;
    private int IR_NEXT = 46;

    private static class DABHelperHolder {
        private static final DABHelper dabHelper = new DABHelper();

        private DABHelperHolder() {
        }
    }

    public static DABHelper getInstance(Context context) {
        return DABHelperHolder.dabHelper;
    }

    public void DabNext() {
        RLog.i("NEU_DAB DABHelper", "DabNext start");
        Intent intent = new Intent();
        intent.putExtra("keyCode", this.IR_NEXT);
        intent.setAction("com.microntek.irkeyDown");
        this.mContext.sendBroadcast(intent);
    }

    public void DabPrev() {
        RLog.i("NEU_DAB DABHelper", "DabPrev start");
        Intent intent = new Intent();
        intent.putExtra("keyCode", this.IR_PREV);
        intent.setAction("com.microntek.irkeyDown");
        this.mContext.sendBroadcast(intent);
    }

    public void openDAB_ONE_Helper(IResponseSender<BaseResult> iResponseSender) {
        RLog.i("NEU_DAB DABHelper", "openDAB_ONE_Helper start");
        try {
            Boolean bool = false;
            ArrayList arrayList = new ArrayList();
            arrayList.add("com.android.launcher3");
            arrayList.add("com.android.systemui");
            if (BydVoiceApplication.isApplicationForeground(arrayList)) {
                RLog.i("NEU_DAB DABHelper", "Home is top Activity");
                bool = true;
            }
            RLog.i("NEU_DAB DABHelper", "match AppName=com.ex.dabplayer.pad");
            PackageManager packageManager = BydVoiceApplication.getContext().getPackageManager();
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage("com.ex.dabplayer.pad");
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            if (listQueryIntentActivities != null) {
                RLog.i("NEU_DAB DABHelper", "queryIntentAcitivites=>" + listQueryIntentActivities.size());
                Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
                if (it.hasNext()) {
                    ResolveInfo next = it.next();
                    String str = next.activityInfo.packageName;
                    String str2 = next.activityInfo.name;
                    RLog.i("NEU_DAB DABHelper", "className=>" + str2);
                    if (bool.booleanValue()) {
                        RLog.i("NEU_DAB DABHelper", "openApp: home is Top,start activity=>" + str + "," + str2);
                        Intent intent2 = new Intent("android.intent.action.MAIN");
                        intent2.addCategory("android.intent.category.LAUNCHER");
                        intent2.setFlags(272629760);
                        intent2.setComponent(new ComponentName(str, str2));
                        ForegroundAppUtil.startActivity(BydVoiceApplication.getContext(), intent2);
                        RLog.i("NEU_DAB DABHelper", "openApp: AppName=com.ex.dabplayer.pad OK!");
                        DABSdk.getInstance(BydVoiceApplication.getContext()).playDAB();
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【openDAB_ONE_Helper】,result【OK】");
                        iResponseSender.send(new Response("OK"));
                        return;
                    }
                    RLog.i("NEU_DAB DABHelper", "openApp: home is not top.");
                    if (str.equals(ForegroundAppUtil.getTopActivityPackage())) {
                        RLog.i("NEU_DAB DABHelper", "openApp: ALREADY_EXPECTED , is not ForegroundApp");
                        DABSdk.getInstance(BydVoiceApplication.getContext()).playDAB();
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【openDAB_ONE_Helper】,result【ALREADY_EXPECTED】");
                        iResponseSender.send(new Response("OK"));
                        return;
                    }
                    RLog.i("NEU_DAB DABHelper", "openApp: start activity =>" + str + "," + str2);
                    Intent intent3 = new Intent("android.intent.action.MAIN");
                    intent3.addCategory("android.intent.category.LAUNCHER");
                    intent3.setFlags(272629760);
                    intent3.setComponent(new ComponentName(str, str2));
                    ForegroundAppUtil.startActivity(BydVoiceApplication.getContext(), intent3);
                    RLog.i("NEU_DAB DABHelper", "openApp: AppName=com.ex.dabplayer.pad OK!");
                    DABSdk.getInstance(BydVoiceApplication.getContext()).playDAB();
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【openDAB_ONE_Helper】,result【OK】");
                    iResponseSender.send(new Response("OK"));
                }
            }
        } catch (Exception e) {
            RLog.i("NEU_DAB DABHelper", "openDAB_ONE_Helper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            e.printStackTrace();
        }
    }

    public void openDABHelper(IResponseSender<BaseResult> iResponseSender, boolean z) {
        RLog.i("NEU_DAB DABHelper", "openDABHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (DABSdk.getInstance(this.mContext).openDAB(countDownLatch, z) == -1) {
                RLog.i("NEU_DAB DABHelper", "调用 openDABHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_DAB DABHelper", "openDABHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            }
        } catch (Exception e) {
            RLog.i("NEU_DAB DABHelper", "openDABHelper off e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            e.printStackTrace();
        }
    }

    public void muteDABHelper(IResponseSender<BaseResult> iResponseSender) {
        RLog.i("NEU_DAB DABHelper", "muteDABHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (DABSdk.getInstance(this.mContext).muteDAB(countDownLatch) == -1) {
                RLog.i("NEU_DAB DABHelper", "muteDABHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_DAB DABHelper", "muteDABHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            }
        } catch (Exception e) {
            RLog.i("NEU_DAB DABHelper", "muteDABHelper off e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            e.printStackTrace();
        }
    }

    public void playDABPreFreHelper(IResponseSender<PlayControlResult> iResponseSender) {
        RLog.i("NEU_DAB DABHelper", "playDABPreFreHelper start");
        PlayControlResult playControlResult = new PlayControlResult();
        playControlResult.setMediaProvider(MediaProviderEnum.LOCAL);
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (DABSdk.getInstance(this.mContext).playPreFreDAB(countDownLatch) == -1) {
                RLog.i("NEU_DAB DABHelper", "playDABPreFreHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_DAB DABHelper", "playDABPreFreHelper RadioResult==" + CustomRadioDomain.RadioResult);
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
            RLog.i("NEU_DAB DABHelper", "playDABPreFreHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void playDABNextFreHelper(IResponseSender<PlayControlResult> iResponseSender) {
        RLog.i("NEU_DAB DABHelper", "playDABNextFreHelper start");
        PlayControlResult playControlResult = new PlayControlResult();
        playControlResult.setMediaProvider(MediaProviderEnum.LOCAL);
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (DABSdk.getInstance(this.mContext).playNextFreDAB(countDownLatch) == -1) {
                RLog.i("NEU_DAB DABHelper", "playDABNextFreHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_DAB DABHelper", "playDABNextFreHelper RadioResult==" + CustomRadioDomain.RadioResult);
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
            RLog.i("NEU_DAB DABHelper", "playDABNextFreHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void playFavDABHelper(IResponseSender<BaseResult> iResponseSender) {
        RLog.i("NEU_DAB DABHelper", "playFavDABHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (DABSdk.getInstance(this.mContext).playFavoriteFreDAB(countDownLatch) == -1) {
                RLog.i("NEU_DAB DABHelper", "playFavDABHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playFavRadio】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_DAB DABHelper", "playFavDABHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
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
            RLog.i("NEU_DAB DABHelper", "playFavDABHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playFavRadio】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void ControlDABfavoriteHelper(int i, IResponseSender<FavoriteControlDabResult> iResponseSender) {
        RLog.i("NEU_DAB DABHelper", "ControlDABfavoriteHelper start");
        FavoriteControlDabResult favoriteControlDabResult = new FavoriteControlDabResult();
        favoriteControlDabResult.setDabTitle("");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (DABSdk.getInstance(this.mContext).ControlfavoriteDAB(i, countDownLatch) == -1) {
                RLog.i("NEU_DAB DABHelper", "ControlDABfavoriteHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlDabResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControl】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_DAB DABHelper", "ControlDABfavoriteHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK", favoriteControlDabResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControl】,result【OK】");
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlDabResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControl】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", favoriteControlDabResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControl】,result【ALREADY_EXPECTED】");
            }
        } catch (Exception e) {
            RLog.i("NEU_DAB DABHelper", "ControlDABfavoriteHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlDabResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControl】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void OpenDABStationListHelper(IResponseSender<BaseResult> iResponseSender) {
        RLog.i("NEU_DAB DABHelper", "OpenDABStationListHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (DABSdk.getInstance(this.mContext).openStationListDAB(countDownLatch) == -1) {
                RLog.i("NEU_DAB DABHelper", "OpenDABStationListHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_DAB DABHelper", "OpenDABStationListHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
            }
        } catch (Exception e) {
            RLog.i("NEU_DAB DABHelper", "OpenDABStationListHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            e.printStackTrace();
        }
    }

    public void OpenDABFavoriteStationListHelper(IResponseSender<BaseResult> iResponseSender) {
        RLog.i("NEU_DAB DABHelper", "OpenDABFavoriteStationListHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (DABSdk.getInstance(this.mContext).openFavoriteStationListDAB(countDownLatch) == -1) {
                RLog.i("NEU_DAB DABHelper", "OpenDABFavoriteStationListHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_DAB DABHelper", "OpenDABFavoriteStationListHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
            }
        } catch (Exception e) {
            RLog.i("NEU_DAB DABHelper", "OpenDABFavoriteStationListHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            e.printStackTrace();
        }
    }

    public void playByCategoryDABHelper(IResponseSender<BaseResult> iResponseSender, String str) {
        RLog.i("NEU_DAB DABHelper", "playByCategoryDABHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (DABSdk.getInstance(this.mContext).playByCategoryDAB(countDownLatch, str) == -1) {
                RLog.i("NEU_DAB DABHelper", "playByCategoryDABHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadioByCategory】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_DAB DABHelper", "playByCategoryDABHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadioByCategory】,result【OK】");
                return;
            }
            if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadioByCategory】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadioByCategory】,result【ALREADY_EXPECTED】");
            } else if (CustomRadioDomain.RadioResult == 2) {
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadioByCategory】,result【MEDIA_NOT_FOUND】");
            }
        } catch (Exception e) {
            RLog.i("NEU_DAB DABHelper", "playByCategoryDABHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadioByCategory】,result【ERROR】");
            e.printStackTrace();
        }
    }
}
