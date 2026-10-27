package cerence.ark.assistant.controller.domain.audiocontrol;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.audiocontrol.param.ControlFavoritelistParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.ControlPlaylistParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.ControlVolumeParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.FavoriteControlParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.GetMediaStateBeforeConversationParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.GetRecentPlayedMediaProviderParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.GetVolumeParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.PlayControlParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.SetMICVolumeParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.SetPlayModeParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.SetVolumeParam;
import cerence.ark.assistant.controller.domain.audiocontrol.result.FavoriteControlResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetActiveMediaTypeBeforeConversationResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetCurrentStreamTypeResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetGlobalMuteStateResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetMICVolumeResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetMediaStateBeforeConversationResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetRecentPlayedMediaProviderResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetVolumeResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.IsBTConnectedResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.IsMICConnectedResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.PlayControlResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.SetPlayModeResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsAudioControlDomain extends Domain {
    private static final String TAG = "AbsAudioControlDomain";

    protected boolean abortControlFavoritelist() {
        return true;
    }

    protected boolean abortControlPlaylist() {
        return true;
    }

    protected boolean abortControlVolume() {
        return true;
    }

    protected boolean abortFavoriteControl() {
        return true;
    }

    protected boolean abortGetActiveMediaTypeBeforeConversation() {
        return true;
    }

    protected boolean abortGetCurrentStreamType() {
        return true;
    }

    protected boolean abortGetGlobalMuteState() {
        return true;
    }

    protected boolean abortGetMICVolume() {
        return true;
    }

    protected boolean abortGetMediaStateBeforeConversation() {
        return true;
    }

    protected boolean abortGetRecentPlayedMediaProvider() {
        return true;
    }

    protected boolean abortGetVolume() {
        return true;
    }

    protected boolean abortIsBTConnected() {
        return true;
    }

    protected boolean abortIsMICConnected() {
        return true;
    }

    protected boolean abortPlayControl() {
        return true;
    }

    protected boolean abortSetMICVolume() {
        return true;
    }

    protected boolean abortSetPlayMode() {
        return true;
    }

    protected boolean abortSetVolume() {
        return true;
    }

    protected abstract void controlFavoritelist(ControlFavoritelistParam controlFavoritelistParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlPlaylist(ControlPlaylistParam controlPlaylistParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void controlVolume(ControlVolumeParam controlVolumeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void favoriteControl(FavoriteControlParam favoriteControlParam, IResponseSender<FavoriteControlResult> iResponseSender);

    protected abstract void getActiveMediaTypeBeforeConversation(IResponseSender<GetActiveMediaTypeBeforeConversationResult> iResponseSender);

    protected abstract void getCurrentStreamType(IResponseSender<GetCurrentStreamTypeResult> iResponseSender);

    protected abstract void getGlobalMuteState(IResponseSender<GetGlobalMuteStateResult> iResponseSender);

    protected abstract void getMICVolume(IResponseSender<GetMICVolumeResult> iResponseSender);

    protected abstract void getMediaStateBeforeConversation(GetMediaStateBeforeConversationParam getMediaStateBeforeConversationParam, IResponseSender<GetMediaStateBeforeConversationResult> iResponseSender);

    protected abstract void getRecentPlayedMediaProvider(GetRecentPlayedMediaProviderParam getRecentPlayedMediaProviderParam, IResponseSender<GetRecentPlayedMediaProviderResult> iResponseSender);

    protected abstract void getVolume(GetVolumeParam getVolumeParam, IResponseSender<GetVolumeResult> iResponseSender);

    protected abstract void isBTConnected(IResponseSender<IsBTConnectedResult> iResponseSender);

    protected abstract void isMICConnected(IResponseSender<IsMICConnectedResult> iResponseSender);

    protected abstract void playControl(PlayControlParam playControlParam, IResponseSender<PlayControlResult> iResponseSender);

    protected abstract void setMICVolume(SetMICVolumeParam setMICVolumeParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void setPlayMode(SetPlayModeParam setPlayModeParam, IResponseSender<SetPlayModeResult> iResponseSender);

    protected abstract void setVolume(SetVolumeParam setVolumeParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsAudioControlDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(AudioControlRequestsEnum.GET_ACTIVE_MEDIA_TYPE_BEFORE_CONVERSATION, new RequestHandler<GetActiveMediaTypeBeforeConversationResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetActiveMediaTypeBeforeConversationResult> iResponseSender) {
                AbsAudioControlDomain.this.getActiveMediaTypeBeforeConversation(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortGetActiveMediaTypeBeforeConversation();
            }
        });
        map.put(AudioControlRequestsEnum.GET_MEDIA_STATE_BEFORE_CONVERSATION, new RequestHandler<GetMediaStateBeforeConversationResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetMediaStateBeforeConversationResult> iResponseSender) {
                try {
                    AbsAudioControlDomain.this.getMediaStateBeforeConversation(GetMediaStateBeforeConversationParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudioControlDomain.TAG, "[AudioControl -> getMediaStateBeforeConversation]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortGetMediaStateBeforeConversation();
            }
        });
        map.put(AudioControlRequestsEnum.GET_RECENT_PLAYED_MEDIA_PROVIDER, new RequestHandler<GetRecentPlayedMediaProviderResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetRecentPlayedMediaProviderResult> iResponseSender) {
                try {
                    AbsAudioControlDomain.this.getRecentPlayedMediaProvider(GetRecentPlayedMediaProviderParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudioControlDomain.TAG, "[AudioControl -> getRecentPlayedMediaProvider]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortGetRecentPlayedMediaProvider();
            }
        });
        map.put(AudioControlRequestsEnum.GET_VOLUME, new RequestHandler<GetVolumeResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetVolumeResult> iResponseSender) {
                try {
                    AbsAudioControlDomain.this.getVolume(GetVolumeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudioControlDomain.TAG, "[AudioControl -> getVolume]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortGetVolume();
            }
        });
        map.put(AudioControlRequestsEnum.PLAY_CONTROL, new RequestHandler<PlayControlResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<PlayControlResult> iResponseSender) {
                try {
                    AbsAudioControlDomain.this.playControl(PlayControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudioControlDomain.TAG, "[AudioControl -> playControl]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortPlayControl();
            }
        });
        map.put("setPlayMode", new RequestHandler<SetPlayModeResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<SetPlayModeResult> iResponseSender) {
                try {
                    AbsAudioControlDomain.this.setPlayMode(SetPlayModeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudioControlDomain.TAG, "[AudioControl -> setPlayMode]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortSetPlayMode();
            }
        });
        map.put(AudioControlRequestsEnum.CONTROL_PLAYLIST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsAudioControlDomain.this.controlPlaylist(ControlPlaylistParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudioControlDomain.TAG, "[AudioControl -> controlPlaylist]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortControlPlaylist();
            }
        });
        map.put(AudioControlRequestsEnum.CONTROL_FAVORITELIST, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsAudioControlDomain.this.controlFavoritelist(ControlFavoritelistParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudioControlDomain.TAG, "[AudioControl -> controlFavoritelist]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortControlFavoritelist();
            }
        });
        map.put(AudioControlRequestsEnum.SET_VOLUME, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsAudioControlDomain.this.setVolume(SetVolumeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudioControlDomain.TAG, "[AudioControl -> setVolume]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortSetVolume();
            }
        });
        map.put(AudioControlRequestsEnum.CONTROL_VOLUME, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.10
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsAudioControlDomain.this.controlVolume(ControlVolumeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudioControlDomain.TAG, "[AudioControl -> controlVolume]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortControlVolume();
            }
        });
        map.put("favoriteControl", new RequestHandler<FavoriteControlResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.11
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<FavoriteControlResult> iResponseSender) {
                try {
                    AbsAudioControlDomain.this.favoriteControl(FavoriteControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudioControlDomain.TAG, "[AudioControl -> favoriteControl]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortFavoriteControl();
            }
        });
        map.put(AudioControlRequestsEnum.IS_BTCONNECTED, new RequestHandler<IsBTConnectedResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.12
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsBTConnectedResult> iResponseSender) {
                AbsAudioControlDomain.this.isBTConnected(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortIsBTConnected();
            }
        });
        map.put(AudioControlRequestsEnum.GET_GLOBAL_MUTE_STATE, new RequestHandler<GetGlobalMuteStateResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.13
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetGlobalMuteStateResult> iResponseSender) {
                AbsAudioControlDomain.this.getGlobalMuteState(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortGetGlobalMuteState();
            }
        });
        map.put(AudioControlRequestsEnum.GET_CURRENT_STREAM_TYPE, new RequestHandler<GetCurrentStreamTypeResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.14
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetCurrentStreamTypeResult> iResponseSender) {
                AbsAudioControlDomain.this.getCurrentStreamType(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortGetCurrentStreamType();
            }
        });
        map.put(AudioControlRequestsEnum.IS_MICCONNECTED, new RequestHandler<IsMICConnectedResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.15
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsMICConnectedResult> iResponseSender) {
                AbsAudioControlDomain.this.isMICConnected(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortIsMICConnected();
            }
        });
        map.put(AudioControlRequestsEnum.GET_MICVOLUME, new RequestHandler<GetMICVolumeResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.16
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetMICVolumeResult> iResponseSender) {
                AbsAudioControlDomain.this.getMICVolume(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortGetMICVolume();
            }
        });
        map.put(AudioControlRequestsEnum.SET_MICVOLUME, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain.17
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsAudioControlDomain.this.setMICVolume(SetMICVolumeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsAudioControlDomain.TAG, "[AudioControl -> setMICVolume]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsAudioControlDomain.this.abortSetMICVolume();
            }
        });
    }
}
