package cerence.ark.assistant.controller.domain.radio;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface RadioRequestsEnum {
    public static final String CHECK_FAVORITE_LIST_AVAILABILITY = "checkFavoriteListAvailability";
    public static final String CHECK_RADIO_STATION_AVAILABILITY = "checkRadioStationAvailability";
    public static final String CONTROL_RADIO = "controlRadio";
    public static final String FAVORITE_CONTROL = "favoriteControl";
    public static final String PLAY_CONTROL = "playControl";
    public static final String PLAY_FAVORITE = "playFavorite";
    public static final String PLAY_RADIO = "playRadio";
    public static final String QUERY_CURRENT_RADIO_INFO = "queryCurrentRadioInfo";
    public static final String SCAN_CONTROL = "scanControl";
}
