package cerence.ark.assistant.controller.domain.news;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface NewsRequestsEnum {
    public static final String CHECK_NEWS_AVAILABILITY = "checkNewsAvailability";
    public static final String GET_SIMILAR_NEWS_INFO = "getSimilarNewsInfo";
    public static final String NEWS_PLAY_CONTROL = "newsPlayControl";
    public static final String PLAY_NEWS = "playNews";
}
