package cerence.ark.assistant.controller.domain.radio;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface RadioRequestsEnum {
    public static final String CONTROL_DAB = "controlDab";
    public static final String CONTROL_RADIO = "controlRadio";
    public static final String FAVORITE_CONTROL = "favoriteControl";
    public static final String FAVORITE_CONTROL_DAB = "favoriteControlDab";
    public static final String GET_DAB_SUPPLY_VERSION = "getDabSupplyVersion";
    public static final String IS_FREQUENCY_OUT_OF_RANGE = "isFrequencyOutOfRange";
    public static final String IS_RADIO_STATION_LIST_SUPPORTED = "isRadioStationListSupported";
    public static final String PLAY_FAV_DAB = "playFavDab";
    public static final String PLAY_FAV_RADIO = "playFavRadio";
    public static final String PLAY_RADIO = "playRadio";
    public static final String PLAY_RADIO_BY_CATEGORY = "playRadioByCategory";
    public static final String SCAN_CONTROL = "scanControl";
}
