package cerence.ark.assistant.controller.domain.karaoke;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface KaraokeRequestsEnum {
    public static final String IS_KARAOKE_RUNNING = "isKaraokeRunning";
    public static final String SHOW_KARAOKE_HISTORY = "showKaraokeHistory";
    public static final String SING_SONG = "singSong";
    public static final String SWITCH_KARAOKE_MODE = "switchKaraokeMode";
}
