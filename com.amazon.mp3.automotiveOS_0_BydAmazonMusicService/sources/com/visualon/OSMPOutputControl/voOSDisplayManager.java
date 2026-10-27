package com.visualon.OSMPOutputControl;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.media.MediaRouter;
import android.os.Build;
import android.os.Handler;
import android.view.Display;
import android.view.WindowManager;
import com.visualon.OSMPUtils.voLog;
import com.visualon.OSMPUtils.voOSType;
import java.util.Locale;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes7.dex */
public class voOSDisplayManager {
    public static final int SDK_INT;
    private static final String TAG = "@@@voOSDisplayManager";
    private Context mContext;
    private DisplayManager mDisplayManager;
    private Handler mEventHandler;
    private final DisplayManager.DisplayListener mDisplayListener = new DisplayManager.DisplayListener() { // from class: com.visualon.OSMPOutputControl.voOSDisplayManager.1
        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
            if (voLog.enablePrintLog()) {
                voLog.d(voOSDisplayManager.TAG, "Output Control, Display #" + i + " added.", new Object[0]);
            }
            voOSDisplayManager.this.listDisplay();
            if (voOSDisplayManager.this.mMiracastListener != null && voOSDisplayManager.this.mWifiDisplayId == i) {
                voOSDisplayManager.this.mMiracastListener.onMiracastOpened();
            }
            if (voOSDisplayManager.this.mMiracastListener != null && voOSDisplayManager.this.mHDMIDisplayId == i) {
                voOSDisplayManager.this.mMiracastListener.onExternalDisplayConnected(voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_HDMI);
            }
            voOSDisplayManager.this.forbidMiracast();
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i) {
            if (voLog.enablePrintLog()) {
                voLog.d(voOSDisplayManager.TAG, "Output Control, Display #" + i + " changed.", new Object[0]);
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
            if (voLog.enablePrintLog()) {
                voLog.d(voOSDisplayManager.TAG, "Output Control, Display #" + i + " removed.", new Object[0]);
            }
            if (i == voOSDisplayManager.this.mWifiDisplayId && voOSDisplayManager.this.mMiracastListener != null) {
                voOSDisplayManager.this.mMiracastListener.onMiracastClosed();
            }
            if (voOSDisplayManager.this.mMiracastListener != null && voOSDisplayManager.this.mHDMIDisplayId == i) {
                voOSDisplayManager.this.mMiracastListener.onExternalDisplayDisconnected(voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_HDMI);
            }
            voOSDisplayManager.this.listDisplay();
        }
    };
    private boolean mbForbidMiracast = false;
    private voOSExternalDisplayListener mMiracastListener = null;
    private int mWifiDisplayId = -1;
    private int mHDMIDisplayId = -1;

    public interface voOSExternalDisplayListener {
        void onExternalDisplayConnected(voOSType.VOOSMP_OUTPUT_CONTROL_TYPE voosmp_output_control_type);

        void onExternalDisplayDisconnected(voOSType.VOOSMP_OUTPUT_CONTROL_TYPE voosmp_output_control_type);

        void onMiracastClosed();

        void onMiracastOpened();
    }

    public void setHandler(Handler handler) {
        this.mEventHandler = handler;
    }

    public voOSDisplayManager(Context context) {
        this.mContext = context;
        this.mDisplayManager = (DisplayManager) context.getSystemService("display");
    }

    public void setOnExternalDisplayListener(voOSExternalDisplayListener voosexternaldisplaylistener) {
        this.mMiracastListener = voosexternaldisplaylistener;
    }

    public void start() {
        this.mDisplayManager.registerDisplayListener(this.mDisplayListener, this.mEventHandler);
        listDisplay();
        voOSExternalDisplayListener voosexternaldisplaylistener = this.mMiracastListener;
        if (voosexternaldisplaylistener != null && this.mWifiDisplayId >= 0) {
            voosexternaldisplaylistener.onMiracastOpened();
        }
        voOSExternalDisplayListener voosexternaldisplaylistener2 = this.mMiracastListener;
        if (voosexternaldisplaylistener2 == null || this.mHDMIDisplayId < 0) {
            return;
        }
        voosexternaldisplaylistener2.onExternalDisplayConnected(voOSType.VOOSMP_OUTPUT_CONTROL_TYPE.VOOSMP_OUTPUT_CONTROL_TYPE_HDMI);
    }

    public int closeMiracast() {
        this.mbForbidMiracast = true;
        forbidMiracast();
        return 0;
    }

    public void stop() {
        this.mbForbidMiracast = false;
        this.mWifiDisplayId = -1;
        this.mHDMIDisplayId = -1;
        this.mDisplayManager.unregisterDisplayListener(this.mDisplayListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void listDisplay() {
        Display[] displays = this.mDisplayManager.getDisplays(null);
        this.mWifiDisplayId = -1;
        this.mHDMIDisplayId = -1;
        for (int i = 0; i < displays.length; i++) {
            try {
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "Output Control, Displays View, id is %d, name is %s, type is %s.", Integer.valueOf(displays[i].getDisplayId()), displays[i].getName(), getDisplayType(displays[i]));
                }
                if (getDisplayType(displays[i]).equalsIgnoreCase("wifi") || getDisplayType(displays[i]).equalsIgnoreCase("virtual")) {
                    this.mWifiDisplayId = displays[i].getDisplayId();
                }
                if (getDisplayType(displays[i]).equalsIgnoreCase("hdmi") && (displays[i].getFlags() & 8) == 8) {
                    this.mHDMIDisplayId = displays[i].getDisplayId();
                }
            } catch (StringIndexOutOfBoundsException unused) {
                voLog.e(TAG, "Can get proper display type. Display is " + displays[i].getName(), new Object[0]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forbidMiracast() {
        Display[] displays = this.mDisplayManager.getDisplays(null);
        for (int i = 0; i < displays.length; i++) {
            try {
                if (getDisplayType(displays[i]).equalsIgnoreCase("wifi") && this.mbForbidMiracast) {
                    MediaRouter mediaRouter = (MediaRouter) this.mContext.getSystemService("media_router");
                    for (int i2 = 0; i2 < mediaRouter.getRouteCount(); i2++) {
                        MediaRouter.RouteInfo routeAt = mediaRouter.getRouteAt(i2);
                        if (voLog.enablePrintLog()) {
                            voLog.i(TAG, "Output Control, RouteInfo, name is %s, type is %d, status is %s, SupportTypes is %d. ", routeAt.getName(), Integer.valueOf(routeAt.getPlaybackType()), routeAt.getStatus(), Integer.valueOf(routeAt.getSupportedTypes()));
                        }
                        if (routeAt.getPlaybackType() == 0) {
                            mediaRouter.selectRoute(2, routeAt);
                            if (voLog.enablePrintLog()) {
                                voLog.i(TAG, "Output Control, selectRoute, forbid miracast, close %s", routeAt.getName());
                            }
                        }
                    }
                }
            } catch (StringIndexOutOfBoundsException unused) {
                voLog.e(TAG, "Can get proper display type. Display is " + displays[i].getName(), new Object[0]);
            }
        }
    }

    public boolean isMiracast() {
        return this.mWifiDisplayId != -1;
    }

    private String getDisplayType(Display display) throws StringIndexOutOfBoundsException {
        String strValueOf = String.valueOf(display);
        int iIndexOf = strValueOf.indexOf("type ");
        if (iIndexOf < 0) {
            return "UNKNOWN";
        }
        int iIndexOf2 = strValueOf.indexOf(" ", iIndexOf);
        int iIndexOf3 = strValueOf.indexOf(",", iIndexOf);
        return (iIndexOf2 < 0 || iIndexOf3 < 0) ? "UNKNOWN" : strValueOf.substring(iIndexOf2, iIndexOf3).trim();
    }

    public boolean isHDCPForHDMI() {
        Display[] displays = this.mDisplayManager.getDisplays(null);
        for (int i = 0; i < displays.length; i++) {
            try {
                if (getDisplayType(displays[i]) != null && getDisplayType(displays[i]).equalsIgnoreCase("hdmi")) {
                    if (voLog.enablePrintLog()) {
                        Object[] objArr = new Object[4];
                        objArr[0] = Integer.valueOf(displays[i].getDisplayId());
                        objArr[1] = displays[i].getName();
                        objArr[2] = Integer.valueOf(displays[i].getFlags());
                        objArr[3] = (displays[i].getFlags() & 2) > 0 ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF;
                        voLog.i(TAG, "Output control, id is %d, name is %s, flags is %d, SecureFlag is %s.", objArr);
                    }
                    return (displays[i].getFlags() & 2) > 0;
                }
            } catch (StringIndexOutOfBoundsException unused) {
                voLog.e(TAG, "Can get proper display type. Display is " + displays[i].getName(), new Object[0]);
            }
        }
        voLog.i(TAG, "Output control, Don't find HDMI connection.", new Object[0]);
        return false;
    }

    public boolean isHDCPForMiracast() {
        Display[] displays = this.mDisplayManager.getDisplays(null);
        for (int i = 0; i < displays.length; i++) {
            try {
                if (getDisplayType(displays[i]).equalsIgnoreCase("wifi")) {
                    if (voLog.enablePrintLog()) {
                        Object[] objArr = new Object[4];
                        objArr[0] = Integer.valueOf(displays[i].getDisplayId());
                        objArr[1] = displays[i].getName();
                        objArr[2] = Integer.valueOf(displays[i].getFlags());
                        objArr[3] = (displays[i].getFlags() & 2) > 0 ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF;
                        voLog.i(TAG, "Output control, id is %d, name is %s, flags is %d, SecureFlag is %s.", objArr);
                    }
                    return (displays[i].getFlags() & 2) > 0;
                }
                continue;
            } catch (StringIndexOutOfBoundsException unused) {
                voLog.e(TAG, "Can get proper display type. Display is " + displays[i].getName(), new Object[0]);
            }
        }
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "Output control, Don't find Micrcast and Anti_Mirroring connection.", new Object[0]);
        }
        return false;
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i == 25 && Build.VERSION.CODENAME.charAt(0) == 'O') {
            i = 26;
        }
        SDK_INT = i;
    }

    public static String toLowerInvariant(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase(Locale.US);
    }

    public static Point getPhysicalDisplaySize(Context context) {
        return getPhysicalDisplaySize(context, ((WindowManager) context.getSystemService("window")).getDefaultDisplay());
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Point getPhysicalDisplaySize(android.content.Context r7, android.view.Display r8) {
        /*
            Method dump skipped, instruction units count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.visualon.OSMPOutputControl.voOSDisplayManager.getPhysicalDisplaySize(android.content.Context, android.view.Display):android.graphics.Point");
    }

    @TargetApi(23)
    private static void getDisplaySizeV23(Display display, Point point) {
        Display.Mode mode = display.getMode();
        point.x = mode.getPhysicalWidth();
        point.y = mode.getPhysicalHeight();
        for (Display.Mode mode2 : display.getSupportedModes()) {
            voLog.i(TAG, mode2.toString(), new Object[0]);
            if (mode2.getPhysicalWidth() > point.x) {
                point.x = mode2.getPhysicalWidth();
                point.y = mode2.getPhysicalHeight();
            }
        }
    }

    @TargetApi(17)
    private static void getDisplaySizeV17(Display display, Point point) {
        display.getRealSize(point);
    }

    @TargetApi(16)
    private static void getDisplaySizeV16(Display display, Point point) {
        display.getSize(point);
    }

    private static void getDisplaySizeV9(Display display, Point point) {
        point.x = display.getWidth();
        point.y = display.getHeight();
    }
}
