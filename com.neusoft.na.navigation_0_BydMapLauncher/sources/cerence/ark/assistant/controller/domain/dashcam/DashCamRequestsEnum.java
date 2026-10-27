package cerence.ark.assistant.controller.domain.dashcam;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface DashCamRequestsEnum {
    public static final String CHECK_CONNECTION_STATUS = "checkConnectionStatus";
    public static final String CHECK_RECORDING_AVAILABILITY = "checkRecordingAvailability";
    public static final String CHECK_STATUS = "checkStatus";
    public static final String CONTROL_ADAS = "controlADAS";
    public static final String CONTROL_EMERGENCY_RECORDING = "controlEmergencyRecording";
    public static final String CONTROL_PULLOVER_RECORDING = "controlPulloverRecording";
    public static final String MANAGE_RECORDINGS = "manageRecordings";
    public static final String TAKE_PHOTO = "takePhoto";
}
