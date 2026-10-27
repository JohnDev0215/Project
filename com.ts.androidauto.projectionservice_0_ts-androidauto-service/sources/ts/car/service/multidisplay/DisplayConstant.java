package ts.car.service.multidisplay;

/* JADX INFO: loaded from: classes2.dex */
public class DisplayConstant {
    public static final String KEY_SHARE_VIDEO = "share_video";
    public static final String RELEATED_SURFACE_CONTROL = "surfaceControl";
    public static final String RELEATED_SURFACE_CONTROL_HEIGHT = "height";
    public static final String RELEATED_SURFACE_CONTROL_WIDTH = "width";
    public static final int VALUE_SHARE_VIDEO = 1;

    public enum DisplayType {
        DISPLAY_ID_FOR_IVI,
        DISPLAY_ID_FOR_CLUSTER,
        DISPLAY_ID_FOR_HUD,
        DISPLAY_ID_FOR_CONTROL_PANCEL,
        DISPLAY_ID_FOR_CO_PILOT_1,
        DISPLAY_ID_FOR_CO_PILOT_2,
        DISPLAY_ID_FOR_CO_PILOT_3
    }

    public enum MultiDisplayState {
        VIRTUALDISPLAY_STATE_CREATED,
        VIRTUALDISPLAY_STATE_CAN_BE_USED,
        VIRTUALDISPLAY_STATE_RELEASE
    }

    public enum MultiSurfaceState {
        SURFACE_STATE_CREATED,
        SURFACE_STATE_RELATED,
        SURFACE_STATE_RELEASED
    }
}
