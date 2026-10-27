package cerence.ark.assistant.controller.domain.hmi;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.hmi.param.DisplayParam;
import cerence.ark.assistant.controller.domain.hmi.param.HintsAndLeadingQuestionsUpdateParam;
import cerence.ark.assistant.controller.domain.hmi.param.HintsUpdateParam;
import cerence.ark.assistant.controller.domain.hmi.param.KnowledgeParam;
import cerence.ark.assistant.controller.domain.hmi.param.PhoneDialingNumberParam;
import cerence.ark.assistant.controller.domain.hmi.param.PhoneSmartListeningParam;
import cerence.ark.assistant.controller.domain.hmi.param.VehicleControlCarInfoParam;
import cerence.ark.assistant.controller.domain.hmi.param.WeatherAqiParam;
import cerence.ark.assistant.controller.domain.hmi.param.WeatherInformationParam;
import cerence.ark.assistant.controller.enumeration.DisplayScenarioEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsDisplayRequestHandler implements RequestHandler<BaseResult> {
    private static final String TAG = "AbsDisplayRequestHandler";
    private final Map<String, RequestHandler<? extends BaseResult>> mMap;

    protected abstract void audiobookFavoriteList(IResponseSender<BaseResult> iResponseSender);

    protected abstract void hintsAndLeadingQuestionsUpdate(HintsAndLeadingQuestionsUpdateParam hintsAndLeadingQuestionsUpdateParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void hintsUpdate(HintsUpdateParam hintsUpdateParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void knowledge(KnowledgeParam knowledgeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void musicFavoriteList(IResponseSender<BaseResult> iResponseSender);

    protected abstract void phoneDialingNumber(PhoneDialingNumberParam phoneDialingNumberParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void phoneSmartListening(PhoneSmartListeningParam phoneSmartListeningParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void radioFavoriteList(IResponseSender<BaseResult> iResponseSender);

    protected abstract void systemHelp(IResponseSender<BaseResult> iResponseSender);

    protected abstract void systemHomePage(IResponseSender<BaseResult> iResponseSender);

    protected abstract void vehicleControlCarInfo(VehicleControlCarInfoParam vehicleControlCarInfoParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void weatherAqi(WeatherAqiParam weatherAqiParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void weatherInformation(WeatherInformationParam weatherInformationParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsDisplayRequestHandler() {
        HashMap map = new HashMap();
        this.mMap = map;
        loadRequestHandlers(map);
    }

    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(DisplayScenarioEnum.AUDIOBOOK_FAVORITE_LIST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDisplayRequestHandler.this.audiobookFavoriteList(iResponseSender);
            }
        });
        map.put(DisplayScenarioEnum.HINTS_AND_LEADING_QUESTIONS_UPDATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.hintsAndLeadingQuestionsUpdate(HintsAndLeadingQuestionsUpdateParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> HINTS_AND_LEADING_QUESTIONS_UPDATE]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.HINTS_UPDATE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.hintsUpdate(HintsUpdateParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> HINTS_UPDATE]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.KNOWLEDGE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.knowledge(KnowledgeParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> KNOWLEDGE]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.MUSIC_FAVORITE_LIST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDisplayRequestHandler.this.musicFavoriteList(iResponseSender);
            }
        });
        map.put(DisplayScenarioEnum.PHONE_DIALING_NUMBER, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.phoneDialingNumber(PhoneDialingNumberParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> PHONE_DIALING_NUMBER]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.PHONE_SMART_LISTENING, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.phoneSmartListening(PhoneSmartListeningParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> PHONE_SMART_LISTENING]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.RADIO_FAVORITE_LIST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDisplayRequestHandler.this.radioFavoriteList(iResponseSender);
            }
        });
        map.put(DisplayScenarioEnum.SYSTEM_HELP, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDisplayRequestHandler.this.systemHelp(iResponseSender);
            }
        });
        map.put(DisplayScenarioEnum.SYSTEM_HOME_PAGE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.10
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsDisplayRequestHandler.this.systemHomePage(iResponseSender);
            }
        });
        map.put(DisplayScenarioEnum.VEHICLE_CONTROL_CAR_INFO, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.11
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.vehicleControlCarInfo(VehicleControlCarInfoParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> VEHICLE_CONTROL_CAR_INFO]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.WEATHER_AQI, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.12
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsDisplayRequestHandler.this.weatherAqi(WeatherAqiParam.fromJson(DisplayParam.fromJson(iRequest.getParams()).getData().toString()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsDisplayRequestHandler.TAG, "[AbsDisplayRequestHandler -> display -> WEATHER_AQI]: exception", e);
                }
            }
        });
        map.put(DisplayScenarioEnum.WEATHER_INFORMATION, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.hmi.AbsDisplayRequestHandler.13
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

    @Override // cerence.ark.assistant.api.controller.RequestHandler
    public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
        try {
            String strOptString = new JSONObject(iRequest.getParams()).optString("scenario", "");
            RequestHandler<? extends BaseResult> requestHandler = this.mMap.get(strOptString);
            if (requestHandler != null) {
                requestHandler.onHandle(iRequest, iResponseSender);
            } else {
                Logger.error(TAG, "the scenario handler of [" + strOptString + "] does not found in the map!");
                iResponseSender.send(new Response(ResultCodeEnum.UNSUPPORTED));
            }
        } catch (JSONException e) {
            Logger.error(TAG, "onHandle exception", e);
        }
    }
}
