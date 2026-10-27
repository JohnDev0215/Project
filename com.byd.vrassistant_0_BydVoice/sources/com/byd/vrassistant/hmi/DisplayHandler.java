package com.byd.vrassistant.hmi;

import android.util.Log;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler;
import cerence.ark.assistant.controller.domain.hmi.param.CurrentVolumeParam;
import cerence.ark.assistant.controller.domain.hmi.param.HintsUpdateParam;
import cerence.ark.assistant.controller.domain.hmi.param.HmiFontColorParam;
import cerence.ark.assistant.controller.domain.hmi.param.MediaPlayerParam;
import cerence.ark.assistant.controller.domain.hmi.param.PhoneCallLogParam;
import cerence.ark.assistant.controller.domain.hmi.param.PhoneDialingNumberParam;
import cerence.ark.assistant.controller.domain.hmi.param.PhoneShowContactParam;
import cerence.ark.assistant.controller.domain.hmi.param.PlaceDetailParam;
import cerence.ark.assistant.controller.domain.hmi.param.RadioStationListParam;
import cerence.ark.assistant.controller.domain.hmi.param.TireInformationParam;
import cerence.ark.assistant.controller.domain.hmi.param.VolumeChangeParam;
import cerence.ark.assistant.controller.domain.hmi.param.VrCloudStatParam;
import cerence.ark.assistant.controller.domain.hmi.param.WeatherInformationParam;
import cerence.ark.assistant.controller.enumeration.CallLogTypeEnum;
import cerence.ark.assistant.controller.enumeration.MileageUnitEnum;
import cerence.ark.assistant.controller.enumeration.PlaceTypeEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.struct.ContactInfo;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import com.blankj.utilcode.util.GsonUtils;
import com.byd.naviserviceapi.Place;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.bean.ViewBlewType;
import com.byd.vrassistant.conversation.AudioSdk;
import com.byd.vrassistant.conversation.DABHelper;
import com.byd.vrassistant.conversation.NaviHelper;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.conversation.RadioHelper;
import com.byd.vrassistant.conversation.RadioSdk;
import com.byd.vrassistant.eventbus.FinalOperation;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.manager.SpotifyManager;
import com.byd.vrassistant.utils.GSON;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.VoiceToastStrChangeUtil;
import com.byd.vrassistant.view.ViewManager;
import com.byd.vrassistant.viewmodel.ViewManagerViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;
import com.byd.vrassistant.viewmodel.WeatherViewModel;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class DisplayHandler extends AbsDisplayRequestHandler {
    private static final String TAG = "NEU_DisplayHandler";
    ViewManagerViewModel viewMangerViewModel = (ViewManagerViewModel) ViewModelHelper.getAppViewModel(ViewManagerViewModel.class);
    WeatherViewModel weatherViewModel = (WeatherViewModel) ViewModelHelper.getAppViewModel(WeatherViewModel.class);

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void tireInformation(TireInformationParam tireInformationParam, IResponseSender<BaseResult> iResponseSender) {
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void currentVolume(CurrentVolumeParam currentVolumeParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter currentVolume，currentVolumeParam =" + currentVolumeParam.getStreamType());
        AudioSdk.getInstance(BydVoiceApplication.getContext()).showVolumeBar();
        iResponseSender.send(new Response("OK"));
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void dabFavoriteList(IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【dabFavoriteList】");
        if (RadioHelper.getInstance(BydVoiceApplication.getContext()).selectDabVersion() == 2) {
            DABHelper.getInstance(BydVoiceApplication.getContext()).OpenDABFavoriteStationListHelper(iResponseSender);
            iResponseSender.send(new Response("OK"));
            RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【dabFavoriteList】,result【OK】");
        } else {
            iResponseSender.send(new Response("UNSUPPORTED"));
            RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【dabFavoriteList】,result【UNSUPPORTED】");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void dabStationList(IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【dabStationList】");
        if (RadioHelper.getInstance(BydVoiceApplication.getContext()).selectDabVersion() == 2) {
            DABHelper.getInstance(BydVoiceApplication.getContext()).OpenDABStationListHelper(iResponseSender);
            RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【dabStationList】,result【OK】");
            iResponseSender.send(new Response("OK"));
        } else {
            RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【dabStationList】,result【UNSUPPORTED】");
            iResponseSender.send(new Response("UNSUPPORTED"));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void globalMuteToast(IResponseSender<BaseResult> iResponseSender) {
        Log.i(TAG, "enter globalMuteToast");
        iResponseSender.send(new Response("OK"));
        EventBus.getDefault().post(new FinalOperation(VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.AUDIO_MUTE), true));
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void globalUnmuteToast(IResponseSender<BaseResult> iResponseSender) {
        Log.i(TAG, "enter globalUnmuteToast");
        iResponseSender.send(new Response("OK"));
        EventBus.getDefault().post(new FinalOperation(VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.AUDIO_UNMUTE), true));
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void hintsUpdate(HintsUpdateParam hintsUpdateParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter hintsUpdate");
        iResponseSender.send(new Response("OK"));
        ViewManager.getViewStateManager().showHint();
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void hmiFontColor(HmiFontColorParam hmiFontColorParam, IResponseSender<BaseResult> iResponseSender) {
        Log.i(TAG, "hmiFontColor: " + GSON.toJson(hmiFontColorParam));
        iResponseSender.send(new Response("OK"));
        ViewManager.getViewStateManager().inputHandleResult(hmiFontColorParam);
    }

    void processSpotify() {
        if (SpotifyManager.get().isConnected()) {
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyManager.get().init(countDownLatch);
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Log.e(TAG, "processSpotify: " + e.getMessage());
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void mediaPlayer(MediaPlayerParam mediaPlayerParam, IResponseSender<BaseResult> iResponseSender) {
        iResponseSender.send(new Response("OK"));
        RLog.i(TAG, "enter mediaPlayer, MediaPlayerParam " + GsonUtils.toJson(mediaPlayerParam));
        String provider = mediaPlayerParam.getProvider();
        provider.hashCode();
        switch (provider) {
            case "SDCARD":
            case "USB":
            case "LOCAL":
                LocalMediaManager.get().openLocalMedia(new CountDownLatch(1));
                break;
            case "SPOTIFY":
                processSpotify();
                SpotifyManager.get().openSpotify();
                break;
            case "":
            case "NONE":
                String packetAudio = RadioSdk.getInstance(BydVoiceApplication.getContext()).getPacketAudio();
                if (packetAudio.isEmpty()) {
                    try {
                        if (RadioSdk.getInstance(BydVoiceApplication.getContext()).getLatestPacketAudio().equals(LocalMediaManager.SPOTIFY_PACKAGE_NAME)) {
                            packetAudio = RadioSdk.getInstance(BydVoiceApplication.getContext()).getLatestPacketAudio();
                        }
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        Log.e(TAG, "mediaPlayer: " + e.getMessage());
                    }
                    break;
                }
                packetAudio.hashCode();
                switch (packetAudio) {
                    case "com.spotify.music":
                        processSpotify();
                        SpotifyManager.get().openSpotify();
                        break;
                    case "com.byd.localmusic":
                        LocalMediaManager.get().openLocalMedia(new CountDownLatch(1));
                        break;
                    case "com.android.bluetooth":
                        LocalMediaManager.get().openBlutoothMedia(new CountDownLatch(1), 255);
                        break;
                }
                break;
            case "BLUETOOTH":
                LocalMediaManager.get().openBlutoothMedia(new CountDownLatch(1), 255);
                break;
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void phoneCallLog(PhoneCallLogParam phoneCallLogParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter phoneCallLog ");
        iResponseSender.send(new Response("OK"));
        if (phoneCallLogParam != null) {
            RLog.i(TAG, "phoneCallLog param size =" + phoneCallLogParam.getCallLogList().size());
            RLog.i(TAG, "phoneCallLog param type=" + phoneCallLogParam.getCallLogType());
        }
        if (CallLogTypeEnum.MISSED_CALL_LOG.equals(phoneCallLogParam.getCallLogType())) {
            PhoneHelper.getInstance().openMissCallPage();
        } else {
            PhoneHelper.getInstance().openCallLogPage();
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void phoneDialingNumber(PhoneDialingNumberParam phoneDialingNumberParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.s_log(TAG, "enter phoneDialingNumber,PhoneDialingNumberParam = " + phoneDialingNumberParam.getPhoneNumber());
        iResponseSender.send(new Response("OK"));
        try {
            PhoneHelper.getInstance().openBTPhonePage();
        } catch (Exception e) {
            RLog.e(TAG, "openBTPhonePage error", e);
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void phoneShowContact(PhoneShowContactParam phoneShowContactParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter phoneShowContact");
        iResponseSender.send(new Response("OK"));
        ContactInfo contact = phoneShowContactParam.getContact();
        if (contact != null) {
            RLog.i(TAG, "PhoneShowContactParam contactInfo =>" + contact.getContactId() + "，" + contact.getContactName());
            PhoneHelper.getInstance().openContactPageWithName(contact.getContactName());
        } else {
            RLog.i(TAG, "PhoneShowContactParam contactInfo is null");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void phoneShowContactList(IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter phoneShowContactList");
        iResponseSender.send(new Response("OK"));
        PhoneHelper.getInstance().openContactsPage();
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void placeDetail(PlaceDetailParam placeDetailParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter placeDetail");
        RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【placeDetail】");
        try {
            if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
                iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【placeDetail】, result【NAVI_AUTH_INAVLID】");
                return;
            }
            PlaceInfo placeInfo = placeDetailParam.getPlaceInfo();
            Place place = new Place();
            place.setId(placeInfo.getExtraInfo());
            place.setName(placeInfo.getName());
            place.setAddress(placeInfo.getAddress());
            place.setLongitude(placeInfo.getLongitude());
            place.setLatitude(placeInfo.getLatitude());
            if (MileageUnitEnum.KM.equals(placeInfo.getUnitOfDistance())) {
                place.setDistance(placeInfo.getDistance() * 1000.0d);
            } else {
                place.setDistance(placeInfo.getDistance() * 1.609d * 1000.0d);
            }
            place.setCountry(placeInfo.getCountry());
            place.setState(placeInfo.getState());
            place.setCity(placeInfo.getCity());
            if (placeInfo.getStreet() == null) {
                place.setStreet(new String[1]);
            } else {
                place.setStreet(new String[placeInfo.getStreet().size()]);
            }
            place.setHouse_number(placeInfo.getHouseNumber());
            if (PlaceTypeEnum.POI == placeInfo.getType()) {
                place.setType(1);
            } else if (PlaceTypeEnum.ADDRESS == placeInfo.getType()) {
                place.setType(2);
            }
            place.setCategoryId(new String[]{"0"});
            int iReqOpenPOIDetail = NaviHelper.getInstance().reqOpenPOIDetail(place);
            RLog.i(TAG, "reqDetailPOIAsDestination==" + iReqOpenPOIDetail);
            if (iReqOpenPOIDetail != 1) {
                if (-2 == iReqOpenPOIDetail) {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【placeDetail】, result【UNSUPPORTED】");
                    return;
                } else {
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【placeDetail】, result【ERROR】");
                    RLog.i(TAG, "placeDetail: ERROR1");
                    return;
                }
            }
            NaviHelper.getInstance().startWaitRoutePlanning();
            int onReqOpenPOIDetailCode = NaviHelper.getInstance().getOnReqOpenPOIDetailCode();
            RLog.i(TAG, "placeDetail: placeDetail==" + onReqOpenPOIDetailCode);
            if (onReqOpenPOIDetailCode != 55 && onReqOpenPOIDetailCode != 56) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【placeDetail】, result【ERROR】");
                RLog.d(TAG, "placeDetail: ERROR1");
                return;
            }
            iResponseSender.send(new Response("OK"));
            try {
                this.viewMangerViewModel.removeViewBlew();
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【placeDetail】, result【OK】");
                RLog.i(TAG, "placeDetail: OK");
            } catch (Exception e) {
                e = e;
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【navi】, api【placeDetail】, result【ERROR】");
                RLog.e(TAG, "placeDetail: ERROR2 e==>" + e.toString(), e);
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void radioFavoriteList(IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter radioFavoriteList");
        RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【radioFavoriteList】");
        RadioHelper.getInstance(BydVoiceApplication.getContext()).OpenFavoriteStationListHelper(iResponseSender);
        iResponseSender.send(new Response("OK"));
        RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【radioFavoriteList】,result【OK】");
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void radioStationList(RadioStationListParam radioStationListParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【radioStationList】");
        iResponseSender.send(new Response("OK"));
        RLog.i(TAG, "enter radioStationList");
        if (radioStationListParam != null) {
            RLog.i(TAG, "RadiaStationListParam param = " + radioStationListParam.getBand());
        }
        RadioHelper.getInstance(BydVoiceApplication.getContext()).OpenStationListHelper(iResponseSender);
        RLog.i(Constant.TAG_FORTEST, "request domain【DisplayHandler】,api【radioStationList】,result【OK】");
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void volumeChange(VolumeChangeParam volumeChangeParam, IResponseSender<BaseResult> iResponseSender) {
        iResponseSender.send(new Response("OK"));
        RLog.i(TAG, "enter volumeChange");
        if (volumeChangeParam != null) {
            RLog.i(TAG, "VolumeChangeParam streamType=" + volumeChangeParam.getStreamType() + ",value=" + volumeChangeParam.getCurrentValue());
        }
        AudioSdk.getInstance(BydVoiceApplication.getContext()).showVolumeBar();
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void volumeControl(IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter volumeControl");
        iResponseSender.send(new Response("OK"));
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void vrForbidToast(IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "enter vrForbidToast");
        iResponseSender.send(new Response("OK"));
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    protected void weatherInformation(WeatherInformationParam weatherInformationParam, IResponseSender<BaseResult> iResponseSender) {
        iResponseSender.send(new Response("OK"));
        if ("OUT_OF_RANGE".equals(weatherInformationParam.getFocusType())) {
            return;
        }
        this.viewMangerViewModel.setViewBlewType(ViewBlewType.WEATHER);
        RLog.s_log(TAG, "weatherInformation: ", GsonUtils.toJson(weatherInformationParam));
        this.weatherViewModel.setWeatherInfo(weatherInformationParam);
    }

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler
    @Deprecated
    protected void vrCloudStat(VrCloudStatParam vrCloudStatParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "vrCloudStat: enter vrCloudStat");
        iResponseSender.send(new Response("OK"));
    }
}
