package cerence.ark.assistant.controller.domain.media;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.media.param.IsMediaProviderRunningParam;
import cerence.ark.assistant.controller.domain.media.param.PlayMediaParam;
import cerence.ark.assistant.controller.domain.media.param.PlayMusicParam;
import cerence.ark.assistant.controller.domain.media.param.SearchMusicParam;
import cerence.ark.assistant.controller.domain.media.result.IsMediaProviderRunningResult;
import cerence.ark.assistant.controller.domain.media.result.PlayMediaResult;
import cerence.ark.assistant.controller.domain.media.result.SearchMusicResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsMediaDomain extends Domain {
    private static final String TAG = "AbsMediaDomain";

    protected boolean abortIsMediaProviderRunning() {
        return true;
    }

    protected boolean abortPlayMedia() {
        return true;
    }

    protected boolean abortPlayMusic() {
        return true;
    }

    protected boolean abortSearchMusic() {
        return true;
    }

    protected abstract void isMediaProviderRunning(IsMediaProviderRunningParam isMediaProviderRunningParam, IResponseSender<IsMediaProviderRunningResult> iResponseSender);

    protected abstract void playMedia(PlayMediaParam playMediaParam, IResponseSender<PlayMediaResult> iResponseSender);

    protected abstract void playMusic(PlayMusicParam playMusicParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void searchMusic(SearchMusicParam searchMusicParam, IResponseSender<SearchMusicResult> iResponseSender);

    protected AbsMediaDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(MediaRequestsEnum.SEARCH_MUSIC, new RequestHandler<SearchMusicResult>() { // from class: cerence.ark.assistant.controller.domain.media.AbsMediaDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<SearchMusicResult> iResponseSender) {
                try {
                    AbsMediaDomain.this.searchMusic(SearchMusicParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMediaDomain.TAG, "[Media -> searchMusic]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMediaDomain.this.abortSearchMusic();
            }
        });
        map.put("playMusic", new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.media.AbsMediaDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsMediaDomain.this.playMusic(PlayMusicParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMediaDomain.TAG, "[Media -> playMusic]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMediaDomain.this.abortPlayMusic();
            }
        });
        map.put(MediaRequestsEnum.PLAY_MEDIA, new RequestHandler<PlayMediaResult>() { // from class: cerence.ark.assistant.controller.domain.media.AbsMediaDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PlayMediaResult> iResponseSender) {
                try {
                    AbsMediaDomain.this.playMedia(PlayMediaParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMediaDomain.TAG, "[Media -> playMedia]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMediaDomain.this.abortPlayMedia();
            }
        });
        map.put(MediaRequestsEnum.IS_MEDIA_PROVIDER_RUNNING, new RequestHandler<IsMediaProviderRunningResult>() { // from class: cerence.ark.assistant.controller.domain.media.AbsMediaDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsMediaProviderRunningResult> iResponseSender) {
                try {
                    AbsMediaDomain.this.isMediaProviderRunning(IsMediaProviderRunningParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMediaDomain.TAG, "[Media -> isMediaProviderRunning]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMediaDomain.this.abortIsMediaProviderRunning();
            }
        });
    }
}
