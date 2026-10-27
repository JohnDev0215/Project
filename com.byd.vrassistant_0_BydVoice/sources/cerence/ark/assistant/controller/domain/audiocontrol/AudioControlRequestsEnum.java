package cerence.ark.assistant.controller.domain.audiocontrol;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface AudioControlRequestsEnum {
    public static final String CONTROL_FAVORITELIST = "controlFavoritelist";
    public static final String CONTROL_PLAYLIST = "controlPlaylist";
    public static final String CONTROL_VOLUME = "controlVolume";
    public static final String FAVORITE_CONTROL = "favoriteControl";
    public static final String GET_ACTIVE_MEDIA_TYPE_BEFORE_CONVERSATION = "getActiveMediaTypeBeforeConversation";
    public static final String GET_CURRENT_STREAM_TYPE = "getCurrentStreamType";
    public static final String GET_GLOBAL_MUTE_STATE = "getGlobalMuteState";
    public static final String GET_MEDIA_STATE_BEFORE_CONVERSATION = "getMediaStateBeforeConversation";
    public static final String GET_MICVOLUME = "getMICVolume";
    public static final String GET_RECENT_PLAYED_MEDIA_PROVIDER = "getRecentPlayedMediaProvider";
    public static final String GET_VOLUME = "getVolume";
    public static final String IS_BTCONNECTED = "isBTConnected";
    public static final String IS_MICCONNECTED = "isMICConnected";
    public static final String PLAY_CONTROL = "playControl";
    public static final String SET_MICVOLUME = "setMICVolume";
    public static final String SET_PLAY_MODE = "setPlayMode";
    public static final String SET_VOLUME = "setVolume";
}
