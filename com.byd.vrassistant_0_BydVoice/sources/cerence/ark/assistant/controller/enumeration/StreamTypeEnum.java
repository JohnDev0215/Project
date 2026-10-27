package cerence.ark.assistant.controller.enumeration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface StreamTypeEnum {
    public static final String BLUETOOTH = "BLUETOOTH";
    public static final String BLUETOOTH_MUSIC = "BLUETOOTH_MUSIC";
    public static final String BLUETOOTH_PHONE = "BLUETOOTH_PHONE";
    public static final String GLOBAL = "GLOBAL";
    public static final String MEDIA = "MEDIA";
    public static final String NAVIGATION = "NAVIGATION";
    public static final String UNSPECIFIED = "UNSPECIFIED";
    public static final String VOICE_ASSISTANT = "VOICE_ASSISTANT";
}
