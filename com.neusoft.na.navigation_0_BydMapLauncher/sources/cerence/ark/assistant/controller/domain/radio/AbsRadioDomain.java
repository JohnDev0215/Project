package cerence.ark.assistant.controller.domain.radio;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.radio.param.CheckRadioStationAvailabilityParam;
import cerence.ark.assistant.controller.domain.radio.param.ControlRadioParam;
import cerence.ark.assistant.controller.domain.radio.param.FavoriteControlParam;
import cerence.ark.assistant.controller.domain.radio.param.PlayControlParam;
import cerence.ark.assistant.controller.domain.radio.param.PlayRadioParam;
import cerence.ark.assistant.controller.domain.radio.param.ScanControlParam;
import cerence.ark.assistant.controller.domain.radio.result.CheckFavoriteListAvailabilityResult;
import cerence.ark.assistant.controller.domain.radio.result.CheckRadioStationAvailabilityResult;
import cerence.ark.assistant.controller.domain.radio.result.PlayRadioResult;
import cerence.ark.assistant.controller.domain.radio.result.QueryCurrentRadioInfoResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsRadioDomain extends Domain {
    private static final String TAG = "AbsRadioDomain";

    protected boolean abortCheckFavoriteListAvailability() {
        return true;
    }

    protected boolean abortCheckRadioStationAvailability() {
        return true;
    }

    protected boolean abortControlRadio() {
        return true;
    }

    protected boolean abortFavoriteControl() {
        return true;
    }

    protected boolean abortPlayControl() {
        return true;
    }

    protected boolean abortPlayFavorite() {
        return true;
    }

    protected boolean abortPlayRadio() {
        return true;
    }

    protected boolean abortQueryCurrentRadioInfo() {
        return true;
    }

    protected boolean abortScanControl() {
        return true;
    }

    protected abstract void checkFavoriteListAvailability(IResponseSender<CheckFavoriteListAvailabilityResult> iResponseSender);

    protected abstract void checkRadioStationAvailability(CheckRadioStationAvailabilityParam checkRadioStationAvailabilityParam, IResponseSender<CheckRadioStationAvailabilityResult> iResponseSender);

    protected abstract void controlRadio(ControlRadioParam controlRadioParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void favoriteControl(FavoriteControlParam favoriteControlParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void playControl(PlayControlParam playControlParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void playFavorite(IResponseSender<BaseResult> iResponseSender);

    protected abstract void playRadio(PlayRadioParam playRadioParam, IResponseSender<PlayRadioResult> iResponseSender);

    protected abstract void queryCurrentRadioInfo(IResponseSender<QueryCurrentRadioInfoResult> iResponseSender);

    protected abstract void scanControl(ScanControlParam scanControlParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsRadioDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(RadioRequestsEnum.CONTROL_RADIO, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsRadioDomain.this.controlRadio(ControlRadioParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsRadioDomain.TAG, "[Radio -> controlRadio]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortControlRadio();
            }
        });
        map.put(RadioRequestsEnum.PLAY_RADIO, new RequestHandler<PlayRadioResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PlayRadioResult> iResponseSender) {
                try {
                    AbsRadioDomain.this.playRadio(PlayRadioParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsRadioDomain.TAG, "[Radio -> playRadio]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortPlayRadio();
            }
        });
        map.put("playControl", new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsRadioDomain.this.playControl(PlayControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsRadioDomain.TAG, "[Radio -> playControl]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortPlayControl();
            }
        });
        map.put(RadioRequestsEnum.SCAN_CONTROL, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsRadioDomain.this.scanControl(ScanControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsRadioDomain.TAG, "[Radio -> scanControl]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortScanControl();
            }
        });
        map.put(RadioRequestsEnum.PLAY_FAVORITE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsRadioDomain.this.playFavorite(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortPlayFavorite();
            }
        });
        map.put(RadioRequestsEnum.CHECK_RADIO_STATION_AVAILABILITY, new RequestHandler<CheckRadioStationAvailabilityResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckRadioStationAvailabilityResult> iResponseSender) {
                try {
                    AbsRadioDomain.this.checkRadioStationAvailability(CheckRadioStationAvailabilityParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsRadioDomain.TAG, "[Radio -> checkRadioStationAvailability]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortCheckRadioStationAvailability();
            }
        });
        map.put(RadioRequestsEnum.CHECK_FAVORITE_LIST_AVAILABILITY, new RequestHandler<CheckFavoriteListAvailabilityResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckFavoriteListAvailabilityResult> iResponseSender) {
                AbsRadioDomain.this.checkFavoriteListAvailability(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortCheckFavoriteListAvailability();
            }
        });
        map.put("favoriteControl", new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsRadioDomain.this.favoriteControl(FavoriteControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsRadioDomain.TAG, "[Radio -> favoriteControl]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortFavoriteControl();
            }
        });
        map.put(RadioRequestsEnum.QUERY_CURRENT_RADIO_INFO, new RequestHandler<QueryCurrentRadioInfoResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryCurrentRadioInfoResult> iResponseSender) {
                AbsRadioDomain.this.queryCurrentRadioInfo(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortQueryCurrentRadioInfo();
            }
        });
    }
}
