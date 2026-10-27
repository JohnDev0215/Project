package cerence.ark.assistant.controller.domain.system;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface SystemRequestsEnum {
    public static final String CHECK_PERMISSION = "checkPermission";
    public static final String GET_ACTIVE_MEDIA_TYPE_BEFORE_CONVERSATION = "getActiveMediaTypeBeforeConversation";
    public static final String GET_FOREGROUND_MEDIA_TYPE = "getForegroundMediaType";
    public static final String GET_MEDIA_STATE_BEFORE_CONVERSATION = "getMediaStateBeforeConversation";
    public static final String GET_NETWORK_STATUS = "getNetworkStatus";
    public static final String IS_BLUETOOTH_HEADSET_CONNECTED = "isBluetoothHeadsetConnected";
    public static final String RELEASE_AUDIO_FOCUS = "releaseAudioFocus";
    public static final String REQUEST_AUDIO_FOCUS = "requestAudioFocus";
}
