package cerence.ark.assistant.controller.domain.audiobook;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.audiobook.param.CheckPlayListAvailabilityParam;
import cerence.ark.assistant.controller.domain.audiobook.param.FavoriteControlParam;
import cerence.ark.assistant.controller.domain.audiobook.param.PlayAudiobookParam;
import cerence.ark.assistant.controller.domain.audiobook.param.PlayControlParam;
import cerence.ark.assistant.controller.domain.audiobook.param.SearchAudiobookParam;
import cerence.ark.assistant.controller.domain.audiobook.result.CheckPlayListAvailabilityResult;
import cerence.ark.assistant.controller.domain.audiobook.result.PlayControlResult;
import cerence.ark.assistant.controller.domain.audiobook.result.QueryCurrentAudiobookInfoResult;
import cerence.ark.assistant.controller.domain.audiobook.result.SearchAudiobookResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsAudiobookDomain extends Domain {
    private static final String TAG = "AbsAudiobookDomain";

    protected boolean abortCheckPlayListAvailability() {
        return true;
    }

    protected boolean abortFavoriteControl() {
        return true;
    }

    protected boolean abortPlayAudiobook() {
        return true;
    }

    protected boolean abortPlayControl() {
        return true;
    }

    protected boolean abortQueryCurrentAudiobookInfo() {
        return true;
    }

    protected boolean abortSearchAudiobook() {
        return true;
    }

    protected abstract void checkPlayListAvailability(CheckPlayListAvailabilityParam checkPlayListAvailabilityParam, IResponseSender<CheckPlayListAvailabilityResult> iResponseSender);

    protected abstract void favoriteControl(FavoriteControlParam favoriteControlParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void playAudiobook(PlayAudiobookParam playAudiobookParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void playControl(PlayControlParam playControlParam, IResponseSender<PlayControlResult> iResponseSender);

    protected abstract void queryCurrentAudiobookInfo(IResponseSender<QueryCurrentAudiobookInfoResult> iResponseSender);

    protected abstract void searchAudiobook(SearchAudiobookParam searchAudiobookParam, IResponseSender<SearchAudiobookResult> iResponseSender);

    protected AbsAudiobookDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(AudiobookRequestsEnum.QUERY_CURRENT_AUDIOBOOK_INFO, new RequestHandler<QueryCurrentAudiobookInfoResult>() { // from class: cerence.ark.assistant.controller.domain.audiobook.AbsAudiobookDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryCurrentAudiobookInfoResult> iResponseSender) {
                AbsAudiobookDomain.this.queryCurrentAudiobookInfo(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudiobookDomain.this.abortQueryCurrentAudiobookInfo();
            }
        });
        map.put(AudiobookRequestsEnum.SEARCH_AUDIOBOOK, new RequestHandler<SearchAudiobookResult>() { // from class: cerence.ark.assistant.controller.domain.audiobook.AbsAudiobookDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<SearchAudiobookResult> iResponseSender) {
                try {
                    AbsAudiobookDomain.this.searchAudiobook(SearchAudiobookParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudiobookDomain.TAG, "[Audiobook -> searchAudiobook]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudiobookDomain.this.abortSearchAudiobook();
            }
        });
        map.put(AudiobookRequestsEnum.PLAY_AUDIOBOOK, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.audiobook.AbsAudiobookDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsAudiobookDomain.this.playAudiobook(PlayAudiobookParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudiobookDomain.TAG, "[Audiobook -> playAudiobook]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudiobookDomain.this.abortPlayAudiobook();
            }
        });
        map.put("playControl", new RequestHandler<PlayControlResult>() { // from class: cerence.ark.assistant.controller.domain.audiobook.AbsAudiobookDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PlayControlResult> iResponseSender) {
                try {
                    AbsAudiobookDomain.this.playControl(PlayControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudiobookDomain.TAG, "[Audiobook -> playControl]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudiobookDomain.this.abortPlayControl();
            }
        });
        map.put("favoriteControl", new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.audiobook.AbsAudiobookDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsAudiobookDomain.this.favoriteControl(FavoriteControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudiobookDomain.TAG, "[Audiobook -> favoriteControl]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudiobookDomain.this.abortFavoriteControl();
            }
        });
        map.put("checkPlayListAvailability", new RequestHandler<CheckPlayListAvailabilityResult>() { // from class: cerence.ark.assistant.controller.domain.audiobook.AbsAudiobookDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckPlayListAvailabilityResult> iResponseSender) {
                try {
                    AbsAudiobookDomain.this.checkPlayListAvailability(CheckPlayListAvailabilityParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudiobookDomain.TAG, "[Audiobook -> checkPlayListAvailability]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudiobookDomain.this.abortCheckPlayListAvailability();
            }
        });
    }
}
