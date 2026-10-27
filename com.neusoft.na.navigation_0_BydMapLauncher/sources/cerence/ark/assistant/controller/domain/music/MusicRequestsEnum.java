package cerence.ark.assistant.controller.domain.music;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface MusicRequestsEnum {
    public static final String CHECK_MUSIC_AVAILABILITY = "checkMusicAvailability";
    public static final String CHECK_PLAY_LIST_AVAILABILITY = "checkPlayListAvailability";
    public static final String CHECK_SOURCE_AVAILABILITY = "checkSourceAvailability";
    public static final String FAVORITE_CONTROL = "favoriteControl";
    public static final String GET_RECENT_PLAYED_SOURCE = "getRecentPlayedSource";
    public static final String GET_SIMILAR_ARTIST = "getSimilarArtist";
    public static final String IS_FAVORITE_LIST_FULL = "isFavoriteListFull";
    public static final String IS_MUSIC_INFO_VISIBLE = "isMusicInfoVisible";
    public static final String OPEN_MUSIC_PLAYER = "openMusicPlayer";
    public static final String PLAY_CONTROL = "playControl";
    public static final String PLAY_FAVORITE_MUSIC = "playFavoriteMusic";
    public static final String PLAY_MUSIC = "playMusic";
    public static final String QUERY_ARTIST_BY_ALBUM = "queryArtistByAlbum";
    public static final String QUERY_CURRENT_MUSIC_INFO = "queryCurrentMusicInfo";
    public static final String QUERY_MUSIC_INFO_BY_TITLE = "queryMusicInfoByTitle";
    public static final String SET_PLAY_MODE = "setPlayMode";
}
