package com.amazon.avod.playback.renderer.tate;

import com.visualon.OSMPUtils.voOSType;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public enum OMXILPlaybackError {
    AIV_RENDERER_INTERNAL_ERROR(-1160773376, "Bug in the JNI video renderer"),
    AIV_RENDERER_INSUFFICIENT_SPACE_IN_AUDIO_BUFFER(-1160773375, "Insufficient space in audio buffer to copy entire frame of source data."),
    AIV_RENDERER_INSUFFICIENT_SPACE_IN_VIDEO_BUFFER(-1160773374, "Insufficient space in video buffer to copy entire frame of source data."),
    AIV_BROKEN_AUDIO_FRAME_SEQUENCE(-1160773373, "Broken audio frame sequence"),
    AIV_BROKEN_VIDEO_FRAME_SEQUENCE(-1160773372, "Broken video frame sequence"),
    AIV_INVALID_CALL_SEQUENCE(-1160769536, "Invalid sequence of calls to the video renderer"),
    AIV_RENDERER_UNAVAILABLE(-1160769535, "No native renderer object is available to receive the call"),
    AIV_PLAY_INIT_FAILURE(-1160773631, "Failed to initialize AIV.play renderer"),
    AIV_PLAY_ALREADY_IN_USE(-1160773552, "AIV.play renderer already in use by another playback session"),
    AIV_PLAY_OUT_OF_MEMORY(-1160773120, "Memory allocation failure in AIV.play"),
    AIV_PLAY_UNSUPPORTED_AUDIO_CODEC(-1160772864, "Attempt to use an unsupported audio codec"),
    AIV_PLAY_UNSUPPORTED_VIDEO_CODEC(-1160772784, "Attempt to use an unsupported video codec"),
    AIV_PLAY_UNSUPPORTED_FORMAT_SWITCH(-1160772608, "Failed to switch content formats on the fly"),
    OMX_INIT_FAILURE(-1160773630, "Failed to initialize OpenMAX platform"),
    OMX_MEDIA_SERVER_DIED((int) Long.decode(OMXILPlaybackException.OMX_MEDIA_SERVER_DIED).longValue(), "MediaServer process died"),
    OMX_DRM_ERROR(-1160773628, "DRM error at playback time"),
    OMX_GENERIC_ERROR(-1160773627, "Generic error from OMX"),
    OMX_FRAME_DECODE_ERROR(-1160773625, "Frame decoding error (invalid or corrupt data)"),
    OMX_INPUT_RESOLUTION_TOO_HIGH(-1160773624, "Requested resolution is too high for the current device"),
    OMX_FEATURE_NOT_FOUND(-1160773623, "Some required AIV.play feature was unavailable"),
    OMX_STATE_TRANSITION_FAILED(-1160773622, "AIV.play did not transition to the state we expected it to"),
    PERMISSION_DENIED(-1, "EPERM (permission denied)"),
    NAME_NOT_FOUND(-2, "ENOENT (name not found)"),
    NO_MEMORY(-12, "ENOMEM (no memory)"),
    ALREADY_EXISTS(-17, "EEXIST (already exists)"),
    NO_INIT(-19, "ENODEV (failed to initialize)"),
    BAD_VALUE(-22, "EINVAL (bad value)"),
    DEAD_OBJECT(-32, "EPIPE (dead object)"),
    BAD_INDEX(-75, "EOVERFLOW (bad index)"),
    ANDROID_UNKNOWN_ERROR((int) Long.decode(OMXILPlaybackException.ANDROID_UNKNOWN_ERROR).longValue(), "Unknown error: generic catch-all from OMX layer"),
    BAD_TYPE(-2147483647, "Android error: bad argument type"),
    FAILED_TRANSACTION(-2147483646, "Android error: failed binder transaction"),
    NOT_ENOUGH_DATA(-2147483645, "Android error: not enough data"),
    WOULD_BLOCK(voOSType.VOOSMP_ERR_Video, "Android error: operation would block"),
    TIMED_OUT(voOSType.VOOSMP_ERR_Audio, "Android error: operation timed out"),
    UNKNOWN_TRANSACTION(voOSType.VOOSMP_ERR_OutMemory, "Android error: unknown binder transaction"),
    FDS_NOT_ALLOWED(voOSType.VOOSMP_ERR_Pointer, "Android error: FDS not allowed"),
    OMX_INSUFFICIENT_RESOURCES(-2147479552, "Insufficient resources to perform requested OMX operation"),
    OMX_UNDEFINED_ERROR(voOSType.VOOSMP_SRC_ERR_SOURCE_UNINITIALIZE, "The cause of the OMX error could not be determined"),
    OMX_INVALID_COMPONENT_NAME(voOSType.VOOSMP_SRC_ERR_CB_CONTROL_WARNING_EVENT, "The OMX component name string was not valid"),
    OMX_COMPONENT_NOT_FOUND(-2147479549, "No OMX component with the specified name was found"),
    OMX_INVALID_COMPONENT(-2147479548, "The component did not have a \"OMX_ComponentInit\" or \"OMX_ComponentDeInit\" entry point"),
    OMX_BAD_PARAMETER(-2147479547, "One or more parameters were not valid"),
    OMX_NOT_IMPLEMENTED(-2147479546, "The requested OMX function is not implemented"),
    OMX_UNDERFLOW(-2147479545, "The OMX buffer was emptied before the next buffer was ready"),
    OMX_OVERFLOW(-2147479544, "The OMX buffer was not available when it was needed"),
    OMX_HARDWARE_ERROR(-2147479543, "The hardware failed to respond as expected"),
    OMX_INVALID_STATE(-2147479542, "The OMX component is in the state OMX_StateInvalid"),
    OMX_STREAM_CORRUPT(-2147479541, "The stream was found to be corrupt"),
    OMX_PORTS_NOT_COMPATIBLE(-2147479540, "The OMX ports being connected are not compatible"),
    OMX_RESOURCES_LOST(-2147479539, "The resources allocated to an idle OMX component have been lost, resulting in the component returning to the loaded state"),
    OMX_NO_MORE_INDICES(-2147479538, "No more OMX indices can be enumerated"),
    OMX_VERSION_MISMATCH(-2147479537, "The OMX component detected a version mismatch"),
    OMX_COMPONENT_NOT_READY(-2147479536, "The OMX component is not ready to return data at this time"),
    OMX_TIMEOUT((int) Long.decode(OMXILPlaybackException.OMX_TIMEOUT).longValue(), "There was a timeout in an OMX operation"),
    OMX_SAME_STATE_TRANSITION(-2147479534, "Attempt to transition into the OMX state we were already in"),
    OMX_RESOURCES_PREEMPTED(-2147479533, "Resources allocated to an executing or paused OMX component have been preempted, causing the component to return to the idle state"),
    OMX_PORT_UNRESPONSIVE_DURING_ALLOCATION(-2147479532, "A non-supplier OMX port sends this error to the IL client (via the EventHandler callback) during the allocation of buffers (on a transition from the LOADED to the IDLE state or on a port restart) when it deems that it has waited an unusually long time for the supplier to send it an allocated buffer via a UseBuffer call"),
    OMX_PORT_UNRESPONSIVE_DURING_DEALLOCATION(-2147479531, "A non-supplier OMX port sends this error to the IL client (via the EventHandler callback) during the deallocation of buffers (on a transition from the IDLE to LOADED state or on a port stop) when it deems that it has waited an unusually long time for the supplier to request the deallocation of a buffer header via a FreeBuffer call"),
    OMX_PORT_UNRESPONSIVE_DURING_STOP(-2147479530, "A supplier OMX port sends this error to the IL client (via the EventHandler callback) during the stopping of a port (either on a transition from the IDLE to LOADED state or a port stop) when it deems that it has waited an unusually long time for the non-supplier to return a buffer via an EmptyThisBuffer or FillThisBuffer call"),
    OMX_INCORRECT_STATE_TRANSITION(-2147479529, "Attempting an OMX state transition that is not allowed"),
    OMX_INCORRECT_STATE_OPERATION(-2147479528, "Attempting a command that is not allowed in the present OMX state"),
    OMX_UNSUPPORTED_SETTING(-2147479527, "The values in the OMX parameter or config structure are not supported"),
    OMX_UNSUPPORTED_INDEX(-2147479526, "The OMX parameter or config indicated by the given index is not supported"),
    OMX_BAD_PORT_INDEX(-2147479525, "The OMX port index supplied is incorrect"),
    OMX_PORT_UNPOPULATED(-2147479524, "The OMX port has lost one or more of its buffers and is thus unpopulated"),
    OMX_COMPONENT_SUSPENDED(-2147479523, "OMX component suspended due to temporary loss of resources"),
    OMX_DYNAMIC_RESOURCES_UNAVAILABLE(-2147479522, "OMX component suspended due to an inability to acquire dynamic resources"),
    OMX_MBERRORS_IN_FRAME(-2147479521, "Frame macroblock errors detected"),
    OMX_FORMAT_NOTDETECTED(-2147479520, "OMX component could not parse or determine the format of an input stream"),
    OMX_CONTENT_PIPE_OPEN_FAILED(-2147479519, "The content open operation failed"),
    OMX_CONTENT_PIPE_CREATION_FAILED(-2147479518, "The content creation operation failed"),
    OMX_SEPARATE_TABLES_USED(-2147479517, "Separate table information is being used"),
    OMX_TUNNELING_UNSUPPORTED(-2147479516, "Tunneling is unsupported by the OMX component"),
    UNKNOWN_PLAYBACK_ERROR(1717986918, "Unknown error from native playback component");

    private static final Map<Integer, OMXILPlaybackError> mErrorCodeMap = new HashMap();
    private final String mDescription;
    private final int mID;

    static {
        for (OMXILPlaybackError oMXILPlaybackError : EnumSet.allOf(OMXILPlaybackError.class)) {
            mErrorCodeMap.put(Integer.valueOf(oMXILPlaybackError.mID), oMXILPlaybackError);
        }
    }

    OMXILPlaybackError(int i, String str) {
        this.mID = i;
        this.mDescription = str;
    }

    public static OMXILPlaybackError fromErrorCode(int i) {
        Map<Integer, OMXILPlaybackError> map = mErrorCodeMap;
        if (map.containsKey(Integer.valueOf(i))) {
            return map.get(Integer.valueOf(i));
        }
        return UNKNOWN_PLAYBACK_ERROR;
    }

    public static boolean matchesErrorCode(int i) {
        return mErrorCodeMap.containsKey(Integer.valueOf(i));
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.format(Locale.US, "error 0x%x: %s: %s", Integer.valueOf(this.mID), name(), this.mDescription);
    }

    public int getID() {
        return this.mID;
    }
}
