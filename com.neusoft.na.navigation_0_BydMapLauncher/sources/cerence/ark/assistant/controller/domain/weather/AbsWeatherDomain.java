package cerence.ark.assistant.controller.domain.weather;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.controller.domain.weather.result.QueryCurrentAirQualityResult;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsWeatherDomain extends Domain {
    private static final String TAG = "AbsWeatherDomain";

    protected boolean abortQueryCurrentAirQuality() {
        return true;
    }

    protected abstract void queryCurrentAirQuality(IResponseSender<QueryCurrentAirQualityResult> iResponseSender);

    protected AbsWeatherDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(WeatherRequestsEnum.QUERY_CURRENT_AIR_QUALITY, new RequestHandler<QueryCurrentAirQualityResult>() { // from class: cerence.ark.assistant.controller.domain.weather.AbsWeatherDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryCurrentAirQualityResult> iResponseSender) {
                AbsWeatherDomain.this.queryCurrentAirQuality(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsWeatherDomain.this.abortQueryCurrentAirQuality();
            }
        });
    }
}
