package cerence.ark.assistant.controller.domain.audiobook;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface AudiobookRequestsEnum {
    public static final String CHECK_PLAY_LIST_AVAILABILITY = "checkPlayListAvailability";
    public static final String FAVORITE_CONTROL = "favoriteControl";
    public static final String PLAY_AUDIOBOOK = "playAudiobook";
    public static final String PLAY_CONTROL = "playControl";
    public static final String QUERY_CURRENT_AUDIOBOOK_INFO = "queryCurrentAudiobookInfo";
    public static final String SEARCH_AUDIOBOOK = "searchAudiobook";
}
