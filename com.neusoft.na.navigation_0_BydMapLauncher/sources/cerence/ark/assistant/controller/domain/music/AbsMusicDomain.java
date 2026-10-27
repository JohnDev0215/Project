package cerence.ark.assistant.controller.domain.music;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.RequestHandler;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.controller.domain.music.param.CheckMusicAvailabilityParam;
import cerence.ark.assistant.controller.domain.music.param.CheckPlayListAvailabilityParam;
import cerence.ark.assistant.controller.domain.music.param.CheckSourceAvailabilityParam;
import cerence.ark.assistant.controller.domain.music.param.FavoriteControlParam;
import cerence.ark.assistant.controller.domain.music.param.GetSimilarArtistParam;
import cerence.ark.assistant.controller.domain.music.param.PlayControlParam;
import cerence.ark.assistant.controller.domain.music.param.PlayFavoriteMusicParam;
import cerence.ark.assistant.controller.domain.music.param.PlayMusicParam;
import cerence.ark.assistant.controller.domain.music.param.QueryArtistByAlbumParam;
import cerence.ark.assistant.controller.domain.music.param.QueryMusicInfoByTitleParam;
import cerence.ark.assistant.controller.domain.music.param.SetPlayModeParam;
import cerence.ark.assistant.controller.domain.music.result.CheckMusicAvailabilityResult;
import cerence.ark.assistant.controller.domain.music.result.CheckPlayListAvailabilityResult;
import cerence.ark.assistant.controller.domain.music.result.CheckSourceAvailabilityResult;
import cerence.ark.assistant.controller.domain.music.result.GetRecentPlayedSourceResult;
import cerence.ark.assistant.controller.domain.music.result.GetSimilarArtistResult;
import cerence.ark.assistant.controller.domain.music.result.IsFavoriteListFullResult;
import cerence.ark.assistant.controller.domain.music.result.IsMusicInfoVisibleResult;
import cerence.ark.assistant.controller.domain.music.result.QueryArtistByAlbumResult;
import cerence.ark.assistant.controller.domain.music.result.QueryCurrentMusicInfoResult;
import cerence.ark.assistant.controller.domain.music.result.QueryMusicInfoByTitleResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsMusicDomain extends Domain {
    private static final String TAG = "AbsMusicDomain";

    protected boolean abortCheckMusicAvailability() {
        return true;
    }

    protected boolean abortCheckPlayListAvailability() {
        return true;
    }

    protected boolean abortCheckSourceAvailability() {
        return true;
    }

    protected boolean abortFavoriteControl() {
        return true;
    }

    protected boolean abortGetRecentPlayedSource() {
        return true;
    }

    protected boolean abortGetSimilarArtist() {
        return true;
    }

    protected boolean abortIsFavoriteListFull() {
        return true;
    }

    protected boolean abortIsMusicInfoVisible() {
        return true;
    }

    protected boolean abortOpenMusicPlayer() {
        return true;
    }

    protected boolean abortPlayControl() {
        return true;
    }

    protected boolean abortPlayFavoriteMusic() {
        return true;
    }

    protected boolean abortPlayMusic() {
        return true;
    }

    protected boolean abortQueryArtistByAlbum() {
        return true;
    }

    protected boolean abortQueryCurrentMusicInfo() {
        return true;
    }

    protected boolean abortQueryMusicInfoByTitle() {
        return true;
    }

    protected boolean abortSetPlayMode() {
        return true;
    }

    protected abstract void checkMusicAvailability(CheckMusicAvailabilityParam checkMusicAvailabilityParam, IResponseSender<CheckMusicAvailabilityResult> iResponseSender);

    protected abstract void checkPlayListAvailability(CheckPlayListAvailabilityParam checkPlayListAvailabilityParam, IResponseSender<CheckPlayListAvailabilityResult> iResponseSender);

    protected abstract void checkSourceAvailability(CheckSourceAvailabilityParam checkSourceAvailabilityParam, IResponseSender<CheckSourceAvailabilityResult> iResponseSender);

    protected abstract void favoriteControl(FavoriteControlParam favoriteControlParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void getRecentPlayedSource(IResponseSender<GetRecentPlayedSourceResult> iResponseSender);

    protected abstract void getSimilarArtist(GetSimilarArtistParam getSimilarArtistParam, IResponseSender<GetSimilarArtistResult> iResponseSender);

    protected abstract void isFavoriteListFull(IResponseSender<IsFavoriteListFullResult> iResponseSender);

    protected abstract void isMusicInfoVisible(IResponseSender<IsMusicInfoVisibleResult> iResponseSender);

    protected abstract void openMusicPlayer(IResponseSender<BaseResult> iResponseSender);

    protected abstract void playControl(PlayControlParam playControlParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void playFavoriteMusic(PlayFavoriteMusicParam playFavoriteMusicParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void playMusic(PlayMusicParam playMusicParam, IResponseSender<BaseResult> iResponseSender);

    protected abstract void queryArtistByAlbum(QueryArtistByAlbumParam queryArtistByAlbumParam, IResponseSender<QueryArtistByAlbumResult> iResponseSender);

    protected abstract void queryCurrentMusicInfo(IResponseSender<QueryCurrentMusicInfoResult> iResponseSender);

    protected abstract void queryMusicInfoByTitle(QueryMusicInfoByTitleParam queryMusicInfoByTitleParam, IResponseSender<QueryMusicInfoByTitleResult> iResponseSender);

    protected abstract void setPlayMode(SetPlayModeParam setPlayModeParam, IResponseSender<BaseResult> iResponseSender);

    protected AbsMusicDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.api.controller.Domain
    public void loadRequestHandlers(Map<String, RequestHandler<? extends BaseResult>> map) {
        map.put(MusicRequestsEnum.QUERY_CURRENT_MUSIC_INFO, new RequestHandler<QueryCurrentMusicInfoResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.1
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryCurrentMusicInfoResult> iResponseSender) {
                AbsMusicDomain.this.queryCurrentMusicInfo(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortQueryCurrentMusicInfo();
            }
        });
        map.put(MusicRequestsEnum.QUERY_MUSIC_INFO_BY_TITLE, new RequestHandler<QueryMusicInfoByTitleResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.2
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryMusicInfoByTitleResult> iResponseSender) {
                try {
                    AbsMusicDomain.this.queryMusicInfoByTitle(QueryMusicInfoByTitleParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMusicDomain.TAG, "[Music -> queryMusicInfoByTitle]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortQueryMusicInfoByTitle();
            }
        });
        map.put(MusicRequestsEnum.QUERY_ARTIST_BY_ALBUM, new RequestHandler<QueryArtistByAlbumResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.3
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<QueryArtistByAlbumResult> iResponseSender) {
                try {
                    AbsMusicDomain.this.queryArtistByAlbum(QueryArtistByAlbumParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMusicDomain.TAG, "[Music -> queryArtistByAlbum]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortQueryArtistByAlbum();
            }
        });
        map.put(MusicRequestsEnum.GET_SIMILAR_ARTIST, new RequestHandler<GetSimilarArtistResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.4
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetSimilarArtistResult> iResponseSender) {
                try {
                    AbsMusicDomain.this.getSimilarArtist(GetSimilarArtistParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMusicDomain.TAG, "[Music -> getSimilarArtist]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortGetSimilarArtist();
            }
        });
        map.put(MusicRequestsEnum.CHECK_MUSIC_AVAILABILITY, new RequestHandler<CheckMusicAvailabilityResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.5
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckMusicAvailabilityResult> iResponseSender) {
                try {
                    AbsMusicDomain.this.checkMusicAvailability(CheckMusicAvailabilityParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMusicDomain.TAG, "[Music -> checkMusicAvailability]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortCheckMusicAvailability();
            }
        });
        map.put(MusicRequestsEnum.CHECK_SOURCE_AVAILABILITY, new RequestHandler<CheckSourceAvailabilityResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.6
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckSourceAvailabilityResult> iResponseSender) {
                try {
                    AbsMusicDomain.this.checkSourceAvailability(CheckSourceAvailabilityParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMusicDomain.TAG, "[Music -> checkSourceAvailability]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortCheckSourceAvailability();
            }
        });
        map.put("checkPlayListAvailability", new RequestHandler<CheckPlayListAvailabilityResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.7
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<CheckPlayListAvailabilityResult> iResponseSender) {
                try {
                    AbsMusicDomain.this.checkPlayListAvailability(CheckPlayListAvailabilityParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMusicDomain.TAG, "[Music -> checkPlayListAvailability]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortCheckPlayListAvailability();
            }
        });
        map.put(MusicRequestsEnum.PLAY_MUSIC, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.8
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsMusicDomain.this.playMusic(PlayMusicParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMusicDomain.TAG, "[Music -> playMusic]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortPlayMusic();
            }
        });
        map.put(MusicRequestsEnum.PLAY_FAVORITE_MUSIC, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.9
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsMusicDomain.this.playFavoriteMusic(PlayFavoriteMusicParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMusicDomain.TAG, "[Music -> playFavoriteMusic]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortPlayFavoriteMusic();
            }
        });
        map.put(MusicRequestsEnum.SET_PLAY_MODE, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.10
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsMusicDomain.this.setPlayMode(SetPlayModeParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMusicDomain.TAG, "[Music -> setPlayMode]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortSetPlayMode();
            }
        });
        map.put("playControl", new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.11
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsMusicDomain.this.playControl(PlayControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMusicDomain.TAG, "[Music -> playControl]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortPlayControl();
            }
        });
        map.put("favoriteControl", new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.12
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                try {
                    AbsMusicDomain.this.favoriteControl(FavoriteControlParam.fromJson(iRequest.getParams()), iResponseSender);
                } catch (JSONException e) {
                    Logger.error(AbsMusicDomain.TAG, "[Music -> favoriteControl]: exception", e);
                }
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortFavoriteControl();
            }
        });
        map.put(MusicRequestsEnum.IS_FAVORITE_LIST_FULL, new RequestHandler<IsFavoriteListFullResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.13
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsFavoriteListFullResult> iResponseSender) {
                AbsMusicDomain.this.isFavoriteListFull(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortIsFavoriteListFull();
            }
        });
        map.put(MusicRequestsEnum.IS_MUSIC_INFO_VISIBLE, new RequestHandler<IsMusicInfoVisibleResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.14
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<IsMusicInfoVisibleResult> iResponseSender) {
                AbsMusicDomain.this.isMusicInfoVisible(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortIsMusicInfoVisible();
            }
        });
        map.put(MusicRequestsEnum.GET_RECENT_PLAYED_SOURCE, new RequestHandler<GetRecentPlayedSourceResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.15
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<GetRecentPlayedSourceResult> iResponseSender) {
                AbsMusicDomain.this.getRecentPlayedSource(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortGetRecentPlayedSource();
            }
        });
        map.put(MusicRequestsEnum.OPEN_MUSIC_PLAYER, new RequestHandler<BaseResult>() { // from class: cerence.ark.assistant.controller.domain.music.AbsMusicDomain.16
            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public void onHandle(IRequest iRequest, IResponseSender<BaseResult> iResponseSender) {
                AbsMusicDomain.this.openMusicPlayer(iResponseSender);
            }

            @Override // cerence.ark.assistant.api.controller.RequestHandler
            public boolean onAbort(IRequest iRequest) {
                return AbsMusicDomain.this.abortOpenMusicPlayer();
            }
        });
    }
}
