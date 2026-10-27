package cerence.ark.assistant.controller.domain.karaoke;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.karaoke.param.ShowKaraokeHistoryParam;
import cerence.ark.assistant.controller.domain.karaoke.param.SingSongParam;
import cerence.ark.assistant.controller.domain.karaoke.param.SwitchKaraokeModeParam;
import cerence.ark.assistant.controller.domain.karaoke.result.IsKaraokeRunningResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsKaraokeDomain extends Domain {
    private static final String TAG = "AbsKaraokeDomain";

    protected boolean abortIsKaraokeRunning() {
        return true;
    }

    protected boolean abortShowKaraokeHistory() {
        return true;
    }

    protected boolean abortSingSong() {
        return true;
    }

    protected boolean abortSwitchKaraokeMode() {
        return true;
    }

    protected abstract void isKaraokeRunning(IResponseSender<IsKaraokeRunningResult> iResponseSender);

    protected abstract void showKaraokeHistory(ShowKaraokeHistoryParam showKaraokeHistoryParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void singSong(SingSongParam singSongParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void switchKaraokeMode(SwitchKaraokeModeParam switchKaraokeModeParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsKaraokeDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(KaraokeRequestsEnum.SING_SONG, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.karaoke.AbsKaraokeDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsKaraokeDomain.this.singSong(SingSongParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsKaraokeDomain.TAG, "[Karaoke -> singSong]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsKaraokeDomain.this.abortSingSong();
            }
        });
        map.put(KaraokeRequestsEnum.IS_KARAOKE_RUNNING, new RequestHandler<IsKaraokeRunningResult>() { // from class: cerence.ark.assistant.controller.domain.karaoke.AbsKaraokeDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsKaraokeRunningResult> iResponseSender) {
                AbsKaraokeDomain.this.isKaraokeRunning(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsKaraokeDomain.this.abortIsKaraokeRunning();
            }
        });
        map.put(KaraokeRequestsEnum.SWITCH_KARAOKE_MODE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.karaoke.AbsKaraokeDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsKaraokeDomain.this.switchKaraokeMode(SwitchKaraokeModeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsKaraokeDomain.TAG, "[Karaoke -> switchKaraokeMode]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsKaraokeDomain.this.abortSwitchKaraokeMode();
            }
        });
        map.put(KaraokeRequestsEnum.SHOW_KARAOKE_HISTORY, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.karaoke.AbsKaraokeDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsKaraokeDomain.this.showKaraokeHistory(ShowKaraokeHistoryParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsKaraokeDomain.TAG, "[Karaoke -> showKaraokeHistory]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsKaraokeDomain.this.abortShowKaraokeHistory();
            }
        });
    }
}
