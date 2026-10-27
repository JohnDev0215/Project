package cerence.ark.assistant.controller.domain.radio;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.radio.param.ControlDabParam;
import cerence.ark.assistant.controller.domain.radio.param.ControlRadioParam;
import cerence.ark.assistant.controller.domain.radio.param.FavoriteControlDabParam;
import cerence.ark.assistant.controller.domain.radio.param.FavoriteControlParam;
import cerence.ark.assistant.controller.domain.radio.param.IsFrequencyOutOfRangeParam;
import cerence.ark.assistant.controller.domain.radio.param.PlayRadioByCategoryParam;
import cerence.ark.assistant.controller.domain.radio.param.PlayRadioParam;
import cerence.ark.assistant.controller.domain.radio.param.ScanControlParam;
import cerence.ark.assistant.controller.domain.radio.result.FavoriteControlDabResult;
import cerence.ark.assistant.controller.domain.radio.result.FavoriteControlResult;
import cerence.ark.assistant.controller.domain.radio.result.GetDabSupplyVersionResult;
import cerence.ark.assistant.controller.domain.radio.result.IsFrequencyOutOfRangeResult;
import cerence.ark.assistant.controller.domain.radio.result.IsRadioStationListSupportedResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsRadioDomain extends Domain {
    private static final String TAG = "AbsRadioDomain";

    protected boolean abortControlDab() {
        return true;
    }

    protected boolean abortControlRadio() {
        return true;
    }

    protected boolean abortFavoriteControl() {
        return true;
    }

    protected boolean abortFavoriteControlDab() {
        return true;
    }

    protected boolean abortGetDabSupplyVersion() {
        return true;
    }

    protected boolean abortIsFrequencyOutOfRange() {
        return true;
    }

    protected boolean abortIsRadioStationListSupported() {
        return true;
    }

    protected boolean abortPlayFavDab() {
        return true;
    }

    protected boolean abortPlayFavRadio() {
        return true;
    }

    protected boolean abortPlayRadio() {
        return true;
    }

    protected boolean abortPlayRadioByCategory() {
        return true;
    }

    protected boolean abortScanControl() {
        return true;
    }

    protected abstract void controlDab(ControlDabParam controlDabParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlRadio(ControlRadioParam controlRadioParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void favoriteControl(FavoriteControlParam favoriteControlParam, IResponseSender<FavoriteControlResult> iResponseSender);

    protected abstract void favoriteControlDab(FavoriteControlDabParam favoriteControlDabParam, IResponseSender<FavoriteControlDabResult> iResponseSender);

    protected abstract void getDabSupplyVersion(IResponseSender<GetDabSupplyVersionResult> iResponseSender);

    protected abstract void isFrequencyOutOfRange(IsFrequencyOutOfRangeParam isFrequencyOutOfRangeParam, IResponseSender<IsFrequencyOutOfRangeResult> iResponseSender);

    protected abstract void isRadioStationListSupported(IResponseSender<IsRadioStationListSupportedResult> iResponseSender);

    protected abstract void playFavDab(IResponseSender<BaseResult> iResponseSender);

    protected abstract void playFavRadio(IResponseSender<BaseResult> iResponseSender);

    protected abstract void playRadio(PlayRadioParam playRadioParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void playRadioByCategory(PlayRadioByCategoryParam playRadioByCategoryParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void scanControl(ScanControlParam scanControlParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsRadioDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put("playRadio", new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
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
        map.put(RadioRequestsEnum.CONTROL_RADIO, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.2
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
        map.put(RadioRequestsEnum.CONTROL_DAB, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsRadioDomain.this.controlDab(ControlDabParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsRadioDomain.TAG, "[Radio -> controlDab]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortControlDab();
            }
        });
        map.put("favoriteControl", new RequestHandler<FavoriteControlResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<FavoriteControlResult> iResponseSender) {
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
        map.put(RadioRequestsEnum.FAVORITE_CONTROL_DAB, new RequestHandler<FavoriteControlDabResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<FavoriteControlDabResult> iResponseSender) {
                try {
                    AbsRadioDomain.this.favoriteControlDab(FavoriteControlDabParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsRadioDomain.TAG, "[Radio -> favoriteControlDab]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortFavoriteControlDab();
            }
        });
        map.put(RadioRequestsEnum.SCAN_CONTROL, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.6
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
        map.put(RadioRequestsEnum.IS_RADIO_STATION_LIST_SUPPORTED, new RequestHandler<IsRadioStationListSupportedResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsRadioStationListSupportedResult> iResponseSender) {
                AbsRadioDomain.this.isRadioStationListSupported(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortIsRadioStationListSupported();
            }
        });
        map.put(RadioRequestsEnum.IS_FREQUENCY_OUT_OF_RANGE, new RequestHandler<IsFrequencyOutOfRangeResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsFrequencyOutOfRangeResult> iResponseSender) {
                try {
                    AbsRadioDomain.this.isFrequencyOutOfRange(IsFrequencyOutOfRangeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsRadioDomain.TAG, "[Radio -> isFrequencyOutOfRange]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortIsFrequencyOutOfRange();
            }
        });
        map.put(RadioRequestsEnum.PLAY_RADIO_BY_CATEGORY, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsRadioDomain.this.playRadioByCategory(PlayRadioByCategoryParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsRadioDomain.TAG, "[Radio -> playRadioByCategory]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortPlayRadioByCategory();
            }
        });
        map.put(RadioRequestsEnum.PLAY_FAV_RADIO, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.10
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsRadioDomain.this.playFavRadio(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortPlayFavRadio();
            }
        });
        map.put(RadioRequestsEnum.PLAY_FAV_DAB, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.11
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsRadioDomain.this.playFavDab(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortPlayFavDab();
            }
        });
        map.put(RadioRequestsEnum.GET_DAB_SUPPLY_VERSION, new RequestHandler<GetDabSupplyVersionResult>() { // from class: cerence.ark.assistant.controller.domain.radio.AbsRadioDomain.12
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetDabSupplyVersionResult> iResponseSender) {
                AbsRadioDomain.this.getDabSupplyVersion(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsRadioDomain.this.abortGetDabSupplyVersion();
            }
        });
    }
}
