package cerence.ark.assistant.controller.domain.hmi;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.hmi.param.CurrentVolumeParam;
import cerence.ark.assistant.controller.domain.hmi.param.DisplayParam;
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
import cerence.ark.assistant.controller.enumeration.DisplayScenarioEnum;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsDisplayRequestHandler extends AbsBaseHmiRequestHandler {
    private static final String TAG = "AbsDisplayRequestHandler";

    protected abstract void currentVolume(CurrentVolumeParam currentVolumeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void dabFavoriteList(IResponseSender<BaseResult> iResponseSender);

    protected abstract void dabStationList(IResponseSender<BaseResult> iResponseSender);

    protected abstract void globalMuteToast(IResponseSender<BaseResult> iResponseSender);

    protected abstract void globalUnmuteToast(IResponseSender<BaseResult> iResponseSender);

    protected abstract void hintsUpdate(HintsUpdateParam hintsUpdateParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void hmiFontColor(HmiFontColorParam hmiFontColorParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void mediaPlayer(MediaPlayerParam mediaPlayerParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void phoneCallLog(PhoneCallLogParam phoneCallLogParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void phoneDialingNumber(PhoneDialingNumberParam phoneDialingNumberParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void phoneShowContact(PhoneShowContactParam phoneShowContactParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void phoneShowContactList(IResponseSender<BaseResult> iResponseSender);

    protected abstract void placeDetail(PlaceDetailParam placeDetailParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void radioFavoriteList(IResponseSender<BaseResult> iResponseSender);

    protected abstract void radioStationList(RadioStationListParam radioStationListParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void tireInformation(TireInformationParam tireInformationParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void volumeChange(VolumeChangeParam volumeChangeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void volumeControl(IResponseSender<BaseResult> iResponseSender);

    protected abstract void vrCloudStat(VrCloudStatParam vrCloudStatParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void vrForbidToast(IResponseSender<BaseResult> iResponseSender);

    protected abstract void weatherInformation(WeatherInformationParam weatherInformationParam, IResponseSender<BaseResult> iResponseSender);

    @Override // cerence.ark.assistant.controller.domain.hmi.AbsBaseHmiRequestHandler
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(DisplayScenarioEnum.CURRENT_VOLUME, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.currentVolume(CurrentVolumeParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> CURRENT_VOLUME]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.DAB_FAVORITE_LIST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDisplayRequestHandler.this.dabFavoriteList(iResponseSender);
            }
        });
        map.put(DisplayScenarioEnum.DAB_STATION_LIST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDisplayRequestHandler.this.dabStationList(iResponseSender);
            }
        });
        map.put(DisplayScenarioEnum.GLOBAL_MUTE_TOAST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDisplayRequestHandler.this.globalMuteToast(iResponseSender);
            }
        });
        map.put(DisplayScenarioEnum.GLOBAL_UNMUTE_TOAST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDisplayRequestHandler.this.globalUnmuteToast(iResponseSender);
            }
        });
        map.put(DisplayScenarioEnum.HINTS_UPDATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.hintsUpdate(HintsUpdateParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> HINTS_UPDATE]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.HMI_FONT_COLOR, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.hmiFontColor(HmiFontColorParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> HMI_FONT_COLOR]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.MEDIA_PLAYER, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.mediaPlayer(MediaPlayerParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> MEDIA_PLAYER]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.PHONE_CALL_LOG, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.phoneCallLog(PhoneCallLogParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> PHONE_CALL_LOG]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.PHONE_DIALING_NUMBER, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.10
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.phoneDialingNumber(PhoneDialingNumberParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> PHONE_DIALING_NUMBER]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.PHONE_SHOW_CONTACT, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.11
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.phoneShowContact(PhoneShowContactParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> PHONE_SHOW_CONTACT]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.PHONE_SHOW_CONTACT_LIST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.12
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDisplayRequestHandler.this.phoneShowContactList(iResponseSender);
            }
        });
        map.put(DisplayScenarioEnum.PLACE_DETAIL, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.13
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.placeDetail(PlaceDetailParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> PLACE_DETAIL]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.RADIO_FAVORITE_LIST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.14
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDisplayRequestHandler.this.radioFavoriteList(iResponseSender);
            }
        });
        map.put(DisplayScenarioEnum.RADIO_STATION_LIST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.15
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.radioStationList(RadioStationListParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> RADIO_STATION_LIST]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.TIRE_INFORMATION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.16
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.tireInformation(TireInformationParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> TIRE_INFORMATION]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.VOLUME_CHANGE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.17
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.volumeChange(VolumeChangeParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> VOLUME_CHANGE]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.VOLUME_CONTROL, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.18
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDisplayRequestHandler.this.volumeControl(iResponseSender);
            }
        });
        map.put(DisplayScenarioEnum.VR_CLOUD_STAT, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.19
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.vrCloudStat(VrCloudStatParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> VR_CLOUD_STAT]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.VR_FORBID_TOAST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.20
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDisplayRequestHandler.this.vrForbidToast(iResponseSender);
            }
        });
        map.put(DisplayScenarioEnum.WEATHER_INFORMATION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.21
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.weatherInformation(WeatherInformationParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> WEATHER_INFORMATION]: exception", e);
                }
            }
        });
    }
}
