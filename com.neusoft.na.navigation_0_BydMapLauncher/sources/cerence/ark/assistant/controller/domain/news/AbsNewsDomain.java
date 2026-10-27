package cerence.ark.assistant.controller.domain.news;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.news.param.CheckNewsAvailabilityParam;
import cerence.ark.assistant.controller.domain.news.param.GetSimilarNewsInfoParam;
import cerence.ark.assistant.controller.domain.news.param.NewsPlayControlParam;
import cerence.ark.assistant.controller.domain.news.param.PlayNewsParam;
import cerence.ark.assistant.controller.domain.news.result.CheckNewsAvailabilityResult;
import cerence.ark.assistant.controller.domain.news.result.GetSimilarNewsInfoResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsNewsDomain extends Domain {
    private static final String TAG = "AbsNewsDomain";

    protected boolean abortCheckNewsAvailability() {
        return true;
    }

    protected boolean abortGetSimilarNewsInfo() {
        return true;
    }

    protected boolean abortNewsPlayControl() {
        return true;
    }

    protected boolean abortPlayNews() {
        return true;
    }

    protected abstract void checkNewsAvailability(CheckNewsAvailabilityParam checkNewsAvailabilityParam, IResponseSender<CheckNewsAvailabilityResult> iResponseSender);

    protected abstract void getSimilarNewsInfo(GetSimilarNewsInfoParam getSimilarNewsInfoParam, IResponseSender<GetSimilarNewsInfoResult> iResponseSender);

    protected abstract void newsPlayControl(NewsPlayControlParam newsPlayControlParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void playNews(PlayNewsParam playNewsParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsNewsDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(NewsRequestsEnum.PLAY_NEWS, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.news.AbsNewsDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsNewsDomain.this.playNews(PlayNewsParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsNewsDomain.TAG, "[News -> playNews]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsNewsDomain.this.abortPlayNews();
            }
        });
        map.put(NewsRequestsEnum.NEWS_PLAY_CONTROL, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.news.AbsNewsDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsNewsDomain.this.newsPlayControl(NewsPlayControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsNewsDomain.TAG, "[News -> newsPlayControl]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsNewsDomain.this.abortNewsPlayControl();
            }
        });
        map.put(NewsRequestsEnum.CHECK_NEWS_AVAILABILITY, new RequestHandler<CheckNewsAvailabilityResult>() { // from class: cerence.ark.assistant.controller.domain.news.AbsNewsDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckNewsAvailabilityResult> iResponseSender) {
                try {
                    AbsNewsDomain.this.checkNewsAvailability(CheckNewsAvailabilityParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsNewsDomain.TAG, "[News -> checkNewsAvailability]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsNewsDomain.this.abortCheckNewsAvailability();
            }
        });
        map.put(NewsRequestsEnum.GET_SIMILAR_NEWS_INFO, new RequestHandler<GetSimilarNewsInfoResult>() { // from class: cerence.ark.assistant.controller.domain.news.AbsNewsDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetSimilarNewsInfoResult> iResponseSender) {
                try {
                    AbsNewsDomain.this.getSimilarNewsInfo(GetSimilarNewsInfoParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsNewsDomain.TAG, "[News -> getSimilarNewsInfo]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsNewsDomain.this.abortGetSimilarNewsInfo();
            }
        });
    }
}
