package com.neusoft.na.ocservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;
import androidx.core.app.NotificationCompat;
import com.bydux.api.common.tools.util.ListUtils;
import com.neusoft.na.common.ApplicationStatus;
import com.neusoft.na.common.CommonSystem;
import com.neusoft.na.common.FileUtils;
import com.neusoft.na.common.ServiceBase;
import com.neusoft.na.common.Tool;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import com.neusoft.na.ocservice.util.LocationUtils;
import com.neusoft.na.ocservice.util.SystemUtils;
import com.neusoft.na.onecore.AndroidManager;
import com.neusoft.na.onecore.OneCore;
import com.neusoft.na.onecore.OneCoreProvider;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import org.apache.commons.io.IOUtils;
import rx.Observable;
import rx.functions.Action1;

/* JADX INFO: compiled from: OneCoreService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0006\u0018\u0000 A2\u00020\u0001:\u0001AB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010#\u001a\u00020$J\u0010\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020'H\u0014J\b\u0010(\u001a\u00020$H\u0002J\b\u0010)\u001a\u00020$H\u0002J\b\u0010*\u001a\u00020$H\u0002J\b\u0010+\u001a\u00020$H\u0016J\b\u0010,\u001a\u00020$H\u0016J\b\u0010-\u001a\u00020$H\u0014J\"\u0010.\u001a\u00020\f2\b\u0010/\u001a\u0004\u0018\u0001002\u0006\u00101\u001a\u00020\f2\u0006\u00102\u001a\u00020\fH\u0016J\u0010\u00103\u001a\u00020$2\u0006\u00104\u001a\u000205H\u0002J\u0010\u00106\u001a\u00020$2\u0006\u00107\u001a\u00020\u0004H\u0002J\u0010\u00108\u001a\u00020$2\u0006\u00107\u001a\u00020\u0004H\u0002J\b\u00109\u001a\u00020$H\u0002J\u0010\u0010:\u001a\u00020$2\u0006\u0010;\u001a\u00020<H\u0002J\b\u0010=\u001a\u00020$H\u0002J\b\u0010>\u001a\u00020\u0004H\u0002J\b\u0010?\u001a\u00020$H\u0002J\b\u0010@\u001a\u00020$H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\u0015X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0015X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u001c\u001a\u0004\b \u0010\u001aR\u000e\u0010\"\u001a\u00020\u0015X\u0082D¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/neusoft/na/ocservice/OneCoreService;", "Lcom/neusoft/na/common/ServiceBase;", "()V", "bydDrEnabled", "", "latitudeDefault", "", "longitudeDefault", "mAndroidManager", "Lcom/neusoft/na/onecore/AndroidManager;", "mEnableSendLocation", "mGpsStatus", "", "mLastLocation", "Landroid/location/Location;", "mLocationHandler", "Lcom/neusoft/na/ocservice/LocationHandler;", "mOneCore", "Lcom/neusoft/na/onecore/OneCore;", "mSurfaces", "", "", "Landroid/view/Surface;", "strippedDbPublicTempPath", "Ljava/io/File;", "getStrippedDbPublicTempPath", "()Ljava/io/File;", "strippedDbPublicTempPath$delegate", "Lkotlin/Lazy;", "strippedDbTempPath", "trafficPrivateTempPath", "trafficPublicTempPath", "getTrafficPublicTempPath", "trafficPublicTempPath$delegate", "trafficTempPath", "addNmeaListener", "", "handleIncomingMessage", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "init", "initPrintLocationLog", "notifySurfaceSizeChanged", "onCreate", "onDestroy", "onStart", "onStartCommand", "intent", "Landroid/content/Intent;", "flags", "startId", "setCurrentLocation", OneCoreService.PAYLOAD_CURRENT_LOCATION, "", "setGpsLocationCanUpdate", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "setPrintLocationLog", "setSurfaces", "setVehicleSpeed", OneCoreService.PAYLOAD_CAR_SPEED_VALUE, "", "startOC", "startOneCore", "startSendLocationThread", "stopSendLocationThread", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OneCoreService extends ServiceBase {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int KEY_VALUE_BACK_PRESSED = 1;
    public static final int MSG_ADD_NMEA_LISTENER = 589828;
    public static final int MSG_NOTIFY_SURFACE_SIZE_CHANGED = 131076;
    public static final int MSG_REQ_SET_CALLBACK_MSG = 393217;
    public static final int MSG_REQ_SET_CLUSTER_SURFACE = 131075;
    public static final int MSG_REQ_SET_MAP_STATUS = 196609;
    public static final int MSG_REQ_SET_ON_KEYDOWNVALUE = 262145;
    public static final int MSG_REQ_SET_SURFACE = 131073;
    public static final int MSG_REQ_SET_WIDGET_SURFACE = 131074;
    public static final int MSG_REQ_TIMEZONE_CHANGED = 327681;
    public static final int MSG_REQ_USB_MOUNT = 458753;
    public static final int MSG_REQ_USB_UNMOUNT = 458754;
    public static final int MSG_SET_CAR_SPEED_CHANGED = 589826;
    public static final int MSG_SET_CURRENT_LOCATION = 524289;
    public static final int MSG_SET_GPS_LOCATION_CAN_UPDATE = 524290;
    public static final int MSG_SET_IN_MULTI_WINDOW_MODE = 589825;
    public static final int MSG_SET_PRINT_LOCATION_LOG = 589827;
    public static final String PAYLOAD_CAR_SPEED_VALUE = "carSpeedValue";
    public static final String PAYLOAD_CURRENT_LOCATION = "currentLocation";
    public static final String PAYLOAD_GPS_LOCATION_CAN_UPDATE = "gpsLocationCanUpdate";
    public static final String PAYLOAD_IN_MULTI_WINDOW_MODE = "isInMultiWindowMode";
    public static final String PAYLOAD_KEY_PATH = "path";
    public static final String PAYLOAD_KEY_VALUE = "keyValue";
    public static final String PAYLOAD_MAP_STATUS = "mapStatus";
    public static final String PAYLOAD_PRINT_LOCATION_LOG = "isPrintLocationLog";
    public static final String PAYLOAD_SURFACE = "surface";
    private static final String SHARED_DLT_TAG = "dltStatus";
    private static final String SHARED_TAG = "commonShared";
    private static boolean printLocationLog;
    private boolean bydDrEnabled;
    private float latitudeDefault;
    private float longitudeDefault;
    private AndroidManager mAndroidManager;
    private boolean mEnableSendLocation;
    private int mGpsStatus;
    private Location mLastLocation;
    private LocationHandler mLocationHandler;
    private OneCore mOneCore;
    private final Map<String, Surface> mSurfaces;

    /* JADX INFO: renamed from: strippedDbPublicTempPath$delegate, reason: from kotlin metadata */
    private final Lazy strippedDbPublicTempPath;
    private final String strippedDbTempPath;
    private final String trafficPrivateTempPath;

    /* JADX INFO: renamed from: trafficPublicTempPath$delegate, reason: from kotlin metadata */
    private final Lazy trafficPublicTempPath;
    private final String trafficTempPath;

    public OneCoreService() {
        super("onecore");
        this.trafficTempPath = "/local/traffic";
        this.trafficPrivateTempPath = "/home/local/traffic";
        this.trafficPublicTempPath = LazyKt.lazy(new Function0<File>() { // from class: com.neusoft.na.ocservice.OneCoreService$trafficPublicTempPath$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return new File(this.this$0.getFilesDir(), String.valueOf(this.this$0.trafficTempPath));
            }
        });
        this.strippedDbTempPath = "/local/dbu/var/nais/stripped_db";
        this.strippedDbPublicTempPath = LazyKt.lazy(new Function0<File>() { // from class: com.neusoft.na.ocservice.OneCoreService$strippedDbPublicTempPath$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return new File(this.this$0.getFilesDir(), String.valueOf(this.this$0.strippedDbTempPath));
            }
        });
        this.mSurfaces = new TreeMap();
        this.mEnableSendLocation = true;
        this.latitudeDefault = 3.14787f;
        this.longitudeDefault = 101.694f;
    }

    /* JADX INFO: compiled from: OneCoreService.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010#\u001a\u00020\"R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/neusoft/na/ocservice/OneCoreService$Companion;", "", "()V", "KEY_VALUE_BACK_PRESSED", "", "MSG_ADD_NMEA_LISTENER", "MSG_NOTIFY_SURFACE_SIZE_CHANGED", "MSG_REQ_SET_CALLBACK_MSG", "MSG_REQ_SET_CLUSTER_SURFACE", "MSG_REQ_SET_MAP_STATUS", "MSG_REQ_SET_ON_KEYDOWNVALUE", "MSG_REQ_SET_SURFACE", "MSG_REQ_SET_WIDGET_SURFACE", "MSG_REQ_TIMEZONE_CHANGED", "MSG_REQ_USB_MOUNT", "MSG_REQ_USB_UNMOUNT", "MSG_SET_CAR_SPEED_CHANGED", "MSG_SET_CURRENT_LOCATION", "MSG_SET_GPS_LOCATION_CAN_UPDATE", "MSG_SET_IN_MULTI_WINDOW_MODE", "MSG_SET_PRINT_LOCATION_LOG", "PAYLOAD_CAR_SPEED_VALUE", "", "PAYLOAD_CURRENT_LOCATION", "PAYLOAD_GPS_LOCATION_CAN_UPDATE", "PAYLOAD_IN_MULTI_WINDOW_MODE", "PAYLOAD_KEY_PATH", "PAYLOAD_KEY_VALUE", "PAYLOAD_MAP_STATUS", "PAYLOAD_PRINT_LOCATION_LOG", "PAYLOAD_SURFACE", "SHARED_DLT_TAG", "SHARED_TAG", "printLocationLog", "", "checkPrintLocationLog", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean checkPrintLocationLog() {
            return OneCoreService.printLocationLog;
        }
    }

    private final File getTrafficPublicTempPath() {
        return (File) this.trafficPublicTempPath.getValue();
    }

    private final File getStrippedDbPublicTempPath() {
        return (File) this.strippedDbPublicTempPath.getValue();
    }

    private final void startSendLocationThread() {
        new Thread(new Runnable() { // from class: com.neusoft.na.ocservice.OneCoreService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                OneCoreService.startSendLocationThread$lambda$0(this.f$0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startSendLocationThread$lambda$0(OneCoreService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            Float[] pitch = LocationUtils.INSTANCE.getPitch(this$0);
            Float f = pitch[0];
            Intrinsics.checkNotNull(f);
            this$0.latitudeDefault = f.floatValue();
            Float f2 = pitch[1];
            Intrinsics.checkNotNull(f2);
            this$0.longitudeDefault = f2.floatValue();
        }
        long j = CheckBoardKt.isByd() ? 500L : 1000L;
        while (this$0.mEnableSendLocation) {
            try {
                Companion companion = INSTANCE;
                if (companion.checkPrintLocationLog()) {
                    Log.i(ServiceBase.INSTANCE.getTAG(), "Send location data to mAndroidManager:" + this$0.mLastLocation);
                }
                if (this$0.mLastLocation != null) {
                    if (CheckBoardKt.isByd() && !this$0.bydDrEnabled && this$0.mGpsStatus == 0) {
                        if (companion.checkPrintLocationLog()) {
                            Log.i(ServiceBase.INSTANCE.getTAG(), "send location set GPS speed == 0.0f, because bydDrEnabled and m_GpsStatus");
                        }
                        Location location = this$0.mLastLocation;
                        Intrinsics.checkNotNull(location);
                        location.setSpeed(0.0f);
                    }
                    AndroidManager androidManager = this$0.mAndroidManager;
                    if (androidManager != null) {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        Location location2 = this$0.mLastLocation;
                        Intrinsics.checkNotNull(location2);
                        double longitude = location2.getLongitude();
                        Location location3 = this$0.mLastLocation;
                        Intrinsics.checkNotNull(location3);
                        double latitude = location3.getLatitude();
                        Location location4 = this$0.mLastLocation;
                        Intrinsics.checkNotNull(location4);
                        Double dValueOf = Double.valueOf(location4.getAltitude());
                        Location location5 = this$0.mLastLocation;
                        Intrinsics.checkNotNull(location5);
                        Float fValueOf = Float.valueOf(location5.getBearing());
                        Location location6 = this$0.mLastLocation;
                        Intrinsics.checkNotNull(location6);
                        androidManager.setPosition(jCurrentTimeMillis, longitude, latitude, dValueOf, fValueOf, Float.valueOf(location6.getSpeed()), this$0.mGpsStatus);
                    }
                } else {
                    AndroidManager androidManager2 = this$0.mAndroidManager;
                    if (androidManager2 != null) {
                        androidManager2.setPosition(System.currentTimeMillis(), this$0.longitudeDefault, this$0.latitudeDefault, Double.valueOf(0.0d), Float.valueOf(0.0f), Float.valueOf(0.0f));
                    }
                }
                Thread.sleep(j);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private final void stopSendLocationThread() {
        this.mEnableSendLocation = false;
    }

    private final void initPrintLocationLog() {
        SharedPreferences sharedPreferences;
        Log.i(ServiceBase.INSTANCE.getTAG(), "[initPrintLocationLog]");
        if (CheckBoardKt.isByd8155f()) {
            sharedPreferences = createDeviceProtectedStorageContext().getSharedPreferences("commonShared", 0);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "{\n         createDeviceP…AG, MODE_PRIVATE)\n      }");
        } else {
            sharedPreferences = getSharedPreferences("commonShared", 0);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "{\n         getSharedPref…AG, MODE_PRIVATE)\n      }");
        }
        printLocationLog = sharedPreferences.getBoolean("dltStatus", false);
        Log.i(ServiceBase.INSTANCE.getTAG(), "printLocationLog == " + printLocationLog);
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        ServiceBase.INSTANCE.getLogger().debug("OneCoreService.onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public void onCreate() {
        ServiceBase.INSTANCE.getLogger().debug("[NeuMapLCM]OneCoreService.onCreate()");
        super.onCreate();
        initPrintLocationLog();
        if (!BuildConfig.POS_ENABLED.booleanValue()) {
            ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.OneCoreService.onCreate.1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "use LocationHandler, na_pos disabled";
                }
            });
            if (CheckBoardKt.isByd()) {
                this.bydDrEnabled = Intrinsics.areEqual(new SystemUtils().getSystemPropertiesValue("persist.sys.byd.dr.enabled"), "true");
                Log.i(ServiceBase.INSTANCE.getTAG(), "bydDrEnabled == " + this.bydDrEnabled);
            }
            LocationHandler locationHandler = new LocationHandler(this);
            this.mLocationHandler = locationHandler;
            Observable<Location> locationObs = locationHandler.getLocationObs();
            final Function1<Location, Unit> function1 = new Function1<Location, Unit>() { // from class: com.neusoft.na.ocservice.OneCoreService$onCreate$2$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Location location) {
                    invoke2(location);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Location location) {
                    if (location == null) {
                        if (OneCoreService.INSTANCE.checkPrintLocationLog()) {
                            Log.i(ServiceBase.INSTANCE.getTAG(), "subscribe --> set mLastLocation null");
                            Log.i(ServiceBase.INSTANCE.getTAG(), "subscribe --> with NULL,mGpsStatus:" + this.this$0.mGpsStatus);
                        }
                    } else if (OneCoreService.INSTANCE.checkPrintLocationLog()) {
                        Log.i(ServiceBase.INSTANCE.getTAG(), "subscribe --> location == " + location + ", mGpsStatus == " + this.this$0.mGpsStatus);
                    }
                    this.this$0.mLastLocation = location;
                }
            };
            locationObs.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.OneCoreService$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    OneCoreService.onCreate$lambda$3$lambda$1(function1, obj);
                }
            });
            if (CheckBoardKt.isProtonMC2() || CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
                Observable<Integer> gpsStatusObj = locationHandler.getGpsStatusObj();
                final Function1<Integer, Unit> function12 = new Function1<Integer, Unit>() { // from class: com.neusoft.na.ocservice.OneCoreService$onCreate$2$2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                        invoke2(num);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Integer num) {
                        if (num == null) {
                            this.this$0.mGpsStatus = 0;
                            return;
                        }
                        this.this$0.mGpsStatus = num.intValue();
                        if (OneCoreService.INSTANCE.checkPrintLocationLog()) {
                            Log.i(ServiceBase.INSTANCE.getTAG(), "location handler publish mGpsStatus:" + this.this$0.mGpsStatus);
                        }
                    }
                };
                gpsStatusObj.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.OneCoreService$$ExternalSyntheticLambda2
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        OneCoreService.onCreate$lambda$3$lambda$2(function12, obj);
                    }
                });
            }
            init();
            if (CheckBoardKt.isProtonMC2() || CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
                startSendLocationThread();
                return;
            }
            return;
        }
        ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.OneCoreService.onCreate.3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return "disable LocationHandler, use na_pos";
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // android.app.Service
    public void onDestroy() {
        ServiceBase.INSTANCE.getLogger().debug("OneCoreService.onDestroy()");
        if (!BuildConfig.POS_ENABLED.booleanValue()) {
            if (CheckBoardKt.isProtonMC2() || CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
                stopSendLocationThread();
            }
            LocationHandler locationHandler = this.mLocationHandler;
            if (locationHandler != null) {
                locationHandler.stop();
            }
        }
        OneCore oneCore = this.mOneCore;
        if (oneCore != null) {
            oneCore.stop();
        }
        sendBroadcast(new Intent(CommonSystem.ONE_CORE_STOP_ACTION));
        Log.i(ServiceBase.INSTANCE.getTAG(), "onDestroy: send onecore done");
        super.onDestroy();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000e. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0011. Please report as an issue. */
    @Override // com.neusoft.na.common.ServiceBase
    protected boolean handleIncomingMessage(Message msg) {
        boolean z;
        Intrinsics.checkNotNullParameter(msg, "msg");
        int i = msg.what;
        switch (i) {
            case MSG_REQ_SET_SURFACE /* 131073 */:
                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_REQ_SET_SURFACE");
                Map<String, Surface> map = this.mSurfaces;
                Bundle data = msg.getData();
                map.put("main", data != null ? (Surface) data.getParcelable(PAYLOAD_SURFACE) : null);
                setSurfaces();
                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_REQ_SET_SURFACE:end");
                z = true;
                break;
            case MSG_REQ_SET_WIDGET_SURFACE /* 131074 */:
                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_REQ_SET_WIDGET_SURFACE");
                Map<String, Surface> map2 = this.mSurfaces;
                Bundle data2 = msg.getData();
                map2.put("widget", data2 != null ? (Surface) data2.getParcelable(PAYLOAD_SURFACE) : null);
                setSurfaces();
                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_REQ_SET_WIDGET_SURFACE:end");
                z = true;
                break;
            case MSG_REQ_SET_CLUSTER_SURFACE /* 131075 */:
                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_REQ_SET_CLUSTER_SURFACE");
                Map<String, Surface> map3 = this.mSurfaces;
                Bundle data3 = msg.getData();
                map3.put("cluster", data3 != null ? (Surface) data3.getParcelable(PAYLOAD_SURFACE) : null);
                setSurfaces();
                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_REQ_SET_CLUSTER_SURFACE:end");
                z = true;
                break;
            case MSG_NOTIFY_SURFACE_SIZE_CHANGED /* 131076 */:
                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_NOTIFY_SURFACE_SIZE_CHANGED");
                notifySurfaceSizeChanged();
                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_NOTIFY_SURFACE_SIZE_CHANGED:end");
                z = true;
                break;
            default:
                switch (i) {
                    case MSG_SET_CURRENT_LOCATION /* 524289 */:
                        if ((CheckBoardKt.isProtonMC2() || CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) && !BuildConfig.POS_ENABLED.booleanValue()) {
                            Log.d(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_SET_CURRENT_LOCATION(setCurrentLocation)");
                            float[] floatArray = msg.getData().getFloatArray(PAYLOAD_CURRENT_LOCATION);
                            if (floatArray == null) {
                                floatArray = new float[]{0.0f, 0.0f, 0.0f};
                            }
                            setCurrentLocation(floatArray);
                            z = true;
                        } else {
                            Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_SET_CURRENT_LOCATION(It can not setCurrentLocation,because this target is not proton mc2.)");
                            z = false;
                        }
                        break;
                    case MSG_SET_GPS_LOCATION_CAN_UPDATE /* 524290 */:
                        Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_SET_GPS_LOCATION_CAN_UPDATE(setGpsLocationCanUpdate)");
                        setGpsLocationCanUpdate(msg.getData().getBoolean(PAYLOAD_GPS_LOCATION_CAN_UPDATE));
                        z = true;
                        break;
                    default:
                        switch (i) {
                            case MSG_SET_CAR_SPEED_CHANGED /* 589826 */:
                                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_SET_CAR_SPEED_CHANGED");
                                setVehicleSpeed(msg.getData().getFloat(PAYLOAD_CAR_SPEED_VALUE));
                                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_SET_CAR_SPEED_CHANGED:end");
                                z = true;
                                break;
                            case MSG_SET_PRINT_LOCATION_LOG /* 589827 */:
                                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_SET_PRINT_LOCATION_LOG");
                                setPrintLocationLog(msg.getData().getBoolean(PAYLOAD_PRINT_LOCATION_LOG));
                                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_SET_PRINT_LOCATION_LOG:end");
                                z = true;
                                break;
                            case MSG_ADD_NMEA_LISTENER /* 589828 */:
                                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_ADD_NMEA_LISTENER");
                                addNmeaListener();
                                Log.i(ServiceBase.INSTANCE.getTAG(), "handleIncomingMessage::MSG_ADD_NMEA_LISTENER:end");
                                z = true;
                                break;
                            default:
                                ServiceBase.INSTANCE.getLogger().warn("unexpected message type: " + msg.what);
                                z = false;
                                break;
                        }
                        break;
                }
                break;
        }
        if (z) {
            return true;
        }
        return super.handleIncomingMessage(msg);
    }

    private final synchronized void init() {
        updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.INITIALIZING, null, 2, null));
        getPrivateTempDir().mkdirs();
        updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.INITIALIZED, null, 2, null));
        startOC();
    }

    private final boolean startOneCore() throws ClassNotFoundException {
        OneCore oneCore = this.mOneCore;
        if (oneCore == null) {
            return false;
        }
        if (oneCore.isRunning()) {
            ServiceBase.INSTANCE.getLogger().warn("OneCore already running!");
            return false;
        }
        StringBuilder sb = new StringBuilder();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Pair pair = displayMetrics.widthPixels > displayMetrics.heightPixels ? TuplesKt.to(Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels)) : TuplesKt.to(Integer.valueOf(displayMetrics.heightPixels), Integer.valueOf(displayMetrics.widthPixels));
        int iIntValue = ((Number) pair.component1()).intValue();
        int iIntValue2 = ((Number) pair.component2()).intValue();
        int iRoundToInt = 160;
        if (!CheckBoardKt.isC4Pro()) {
            if (CheckBoardKt.isC3()) {
                iRoundToInt = 124;
            } else if (!CheckBoardKt.isGreatwallB01() && !CheckBoardKt.isGreatwallV61() && !CheckBoardKt.isWeyforproductv3() && !CheckBoardKt.isProtonX11Mobile() && !CheckBoardKt.isSmart() && !CheckBoardKt.isZeekr()) {
                if (CheckBoardKt.isGreatwallA01() || CheckBoardKt.isSeres()) {
                    iRoundToInt = getResources().getDisplayMetrics().densityDpi;
                } else if (!CheckBoardKt.isByd()) {
                    iRoundToInt = MathKt.roundToInt(displayMetrics.xdpi);
                }
            }
        }
        StringBuilder sbAppend = sb.append("--main.path_share=" + getAssetsInstallDir() + "/ocrsc/share/");
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
        StringsKt.appendln(sbAppend);
        StringBuilder sbAppend2 = sb.append("--main.path_temp=" + getPrivateTempDir() + IOUtils.DIR_SEPARATOR_UNIX);
        Intrinsics.checkNotNullExpressionValue(sbAppend2, "append(value)");
        StringsKt.appendln(sbAppend2);
        StringBuilder sbAppend3 = sb.append("--main.path_cache=" + getPrivateCacheDir() + IOUtils.DIR_SEPARATOR_UNIX);
        Intrinsics.checkNotNullExpressionValue(sbAppend3, "append(value)");
        StringsKt.appendln(sbAppend3);
        StringBuilder sbAppend4 = sb.append("--main.resource_set=byd_dx");
        Intrinsics.checkNotNullExpressionValue(sbAppend4, "append(value)");
        StringsKt.appendln(sbAppend4);
        StringBuilder sbAppend5 = sb.append("--main.config=" + getAssetsInstallDir() + "/ocrsc/etc/nais/na_onecore.ini");
        Intrinsics.checkNotNullExpressionValue(sbAppend5, "append(value)");
        StringsKt.appendln(sbAppend5);
        Log.i(ServiceBase.INSTANCE.getTAG(), "using na_onecore.ini");
        OneCoreService oneCoreService = this;
        StringBuilder sbAppend6 = sb.append("--main.config+=" + getAssetsInstallDir() + IOUtils.DIR_SEPARATOR_UNIX + FileUtils.getInstance(oneCoreService).getOneCoreIniPath());
        Intrinsics.checkNotNullExpressionValue(sbAppend6, "append(value)");
        StringsKt.appendln(sbAppend6);
        if (CheckBoardKt.isLowEndDevice(oneCoreService)) {
            ServiceBase.INSTANCE.getLogger().warn(new Function0<Object>() { // from class: com.neusoft.na.ocservice.OneCoreService$startOneCore$1$1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "!!!low end device detected!!!";
                }
            });
            File file = new File(getAssetsInstallDir() + "/ocrsc/etc/mots/na_onecore_lowend.ini");
            if (file.exists()) {
                ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.OneCoreService$startOneCore$1$2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "using lowend configuration";
                    }
                });
                StringBuilder sbAppend7 = sb.append("--main.config+=" + file);
                Intrinsics.checkNotNullExpressionValue(sbAppend7, "append(value)");
                StringsKt.appendln(sbAppend7);
            } else {
                ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.OneCoreService$startOneCore$1$3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "no lowend configuration available";
                    }
                });
            }
        }
        StringBuilder sbAppend8 = sb.append("--main.PersistencyPath=" + getFilesDir() + "/na_onecore");
        Intrinsics.checkNotNullExpressionValue(sbAppend8, "append(value)");
        StringsKt.appendln(sbAppend8);
        StringBuilder sbAppend9 = sb.append("--onecore.deploy=" + getAssetsInstallDir() + "/ocrsc/etc/nais/na_onecore_deploy.json");
        Intrinsics.checkNotNullExpressionValue(sbAppend9, "append(value)");
        StringsKt.appendln(sbAppend9);
        StringBuilder sbAppend10 = sb.append("--nds.ndslib=" + getSharedLibraryPath("libNdsLib.so"));
        Intrinsics.checkNotNullExpressionValue(sbAppend10, "append(value)");
        StringsKt.appendln(sbAppend10);
        StringBuilder sbAppend11 = sb.append("--nds.strippeddb_download = " + getStrippedDbPublicTempPath());
        Intrinsics.checkNotNullExpressionValue(sbAppend11, "append(value)");
        StringsKt.appendln(sbAppend11);
        File file2 = new File(getAssetsInstallDir(), "db/ROOT.NDS");
        if (file2.exists() && file2.isFile()) {
            ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.OneCoreService$startOneCore$1$4$1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "using bundled database";
                }
            });
            StringBuilder sbAppend12 = sb.append("--nds.database=" + file2);
            Intrinsics.checkNotNullExpressionValue(sbAppend12, "append(value)");
            StringsKt.appendln(sbAppend12);
        } else {
            ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.OneCoreService$startOneCore$1$4$2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "using external database";
                }
            });
        }
        StringBuilder sbAppend13 = sb.append("--EGLWidgetManager.window_compositor_main=false");
        Intrinsics.checkNotNullExpressionValue(sbAppend13, "append(value)");
        StringsKt.appendln(sbAppend13);
        if (!CheckBoardKt.isByd8155f()) {
            StringBuilder sbAppend14 = sb.append("--EGLWidgetManager.window_dpi_main=" + iRoundToInt);
            Intrinsics.checkNotNullExpressionValue(sbAppend14, "append(value)");
            StringsKt.appendln(sbAppend14);
        }
        StringBuilder sbAppend15 = sb.append("--MapWindow.width=" + iIntValue);
        Intrinsics.checkNotNullExpressionValue(sbAppend15, "append(value)");
        StringsKt.appendln(sbAppend15);
        StringBuilder sbAppend16 = sb.append("--MapWindow.height=" + iIntValue2);
        Intrinsics.checkNotNullExpressionValue(sbAppend16, "append(value)");
        StringsKt.appendln(sbAppend16);
        StringBuilder sbAppend17 = sb.append("--here.dataFileName=" + getTrafficPublicTempPath() + "/HEREData_AroundVehicle.tpeg");
        Intrinsics.checkNotNullExpressionValue(sbAppend17, "append(value)");
        StringsKt.appendln(sbAppend17);
        StringBuilder sbAppend18 = sb.append("--here.authFileName=" + getTrafficPublicTempPath() + "/HEREAuth.xml");
        Intrinsics.checkNotNullExpressionValue(sbAppend18, "append(value)");
        StringsKt.appendln(sbAppend18);
        StringBuilder sbAppend19 = sb.append("--inrix.dataFileName=" + getTrafficPublicTempPath() + "/InrixData.xml");
        Intrinsics.checkNotNullExpressionValue(sbAppend19, "append(value)");
        StringsKt.appendln(sbAppend19);
        StringBuilder sbAppend20 = sb.append("--inrix.authFileName=" + getTrafficPublicTempPath() + "/InrixAuth.xml");
        Intrinsics.checkNotNullExpressionValue(sbAppend20, "append(value)");
        StringsKt.appendln(sbAppend20);
        if (CheckBoardKt.isByd8155f()) {
            String systemPropertiesValue = new SystemUtils().getSystemPropertiesValue("persist.vendor.byd.gnss.mode");
            Log.i(ServiceBase.INSTANCE.getTAG(), "persistVendorBydGnssMode == " + systemPropertiesValue);
            StringBuilder sbAppend21 = sb.append("--positioning.dr_in_tunnel_simulation=" + Intrinsics.areEqual(systemPropertiesValue, FileUtils.DEFAULT_VERSION));
            Intrinsics.checkNotNullExpressionValue(sbAppend21, "append(value)");
            StringsKt.appendln(sbAppend21);
            Float[] pitch = LocationUtils.INSTANCE.getPitch(oneCoreService);
            Float f = pitch[0];
            Intrinsics.checkNotNull(f);
            float fFloatValue = f.floatValue();
            Float f2 = pitch[1];
            Intrinsics.checkNotNull(f2);
            float fFloatValue2 = f2.floatValue();
            StringBuilder sbAppend22 = sb.append("--positioning.BYD8155_latitude=" + fFloatValue);
            Intrinsics.checkNotNullExpressionValue(sbAppend22, "append(value)");
            StringsKt.appendln(sbAppend22);
            StringBuilder sbAppend23 = sb.append("--positioning.BYD8155_longitude=" + fFloatValue2);
            Intrinsics.checkNotNullExpressionValue(sbAppend23, "append(value)");
            StringsKt.appendln(sbAppend23);
            int i = Intrinsics.areEqual(systemPropertiesValue, FileUtils.DEFAULT_VERSION) ? 3 : 0;
            Log.i(ServiceBase.INSTANCE.getTAG(), "carsorFilterType == " + i);
            StringBuilder sbAppend24 = sb.append("--mapviewer.carsor_filter_type=" + i);
            Intrinsics.checkNotNullExpressionValue(sbAppend24, "append(value)");
            StringsKt.appendln(sbAppend24);
        }
        File externalOnecoreIniFile = ConstantsKt.getExternalOnecoreIniFile();
        if (externalOnecoreIniFile.exists() && externalOnecoreIniFile.isFile()) {
            ServiceBase.INSTANCE.getLogger().info("using external ini file: " + externalOnecoreIniFile);
            StringBuilder sbAppend25 = sb.append("--main.config+=" + externalOnecoreIniFile.getAbsolutePath());
            Intrinsics.checkNotNullExpressionValue(sbAppend25, "append(value)");
            StringsKt.appendln(sbAppend25);
        } else {
            boolean z = getSharedPreferences("commonShared", 0).getBoolean("dltStatus", false);
            ServiceBase.INSTANCE.getLogger().info("external config file not found: " + externalOnecoreIniFile + ", " + z);
            if (z) {
                StringBuilder sbAppend26 = sb.append("--DLT.Logger=Client 127.0.0.1:4711 YES");
                Intrinsics.checkNotNullExpressionValue(sbAppend26, "append(value)");
                StringsKt.appendln(sbAppend26);
                StringBuilder sbAppend27 = sb.append("--DLT.Output=0x040f");
                Intrinsics.checkNotNullExpressionValue(sbAppend27, "append(value)");
                StringsKt.appendln(sbAppend27);
                StringBuilder sbAppend28 = sb.append("--DLT.Context=.* 0x040F");
                Intrinsics.checkNotNullExpressionValue(sbAppend28, "append(value)");
                StringsKt.appendln(sbAppend28);
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        Log.i(ServiceBase.INSTANCE.getTAG(), "[NeuMapLCM]startOneCore");
        OneCore oneCore2 = this.mOneCore;
        Intrinsics.checkNotNull(oneCore2);
        if (!oneCore2.start(string)) {
            ServiceBase.INSTANCE.getLogger().error("failed to start OneCore");
            return false;
        }
        OneCore oneCore3 = this.mOneCore;
        Intrinsics.checkNotNull(oneCore3);
        this.mAndroidManager = (AndroidManager) oneCore3.getFeature(AndroidManager.class);
        setSurfaces();
        LocationHandler locationHandler = this.mLocationHandler;
        if (locationHandler != null) {
            locationHandler.start();
        }
        return true;
    }

    @Override // com.neusoft.na.common.ServiceBase
    protected synchronized void onStart() {
    }

    private final void startOC() throws Throwable {
        if (this.mOneCore == null) {
            this.mOneCore = OneCoreProvider.INSTANCE.getInstance();
        }
        if (getMApplicationStatus().getMStatus() == ApplicationStatus.Status.INITIALIZED) {
            updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.STARTING, null, 2, null));
            initPrivateTempDir();
            File privateCacheDir = getPrivateCacheDir();
            if (!privateCacheDir.isDirectory()) {
                privateCacheDir.mkdirs();
            }
            if (startOneCore()) {
                Log.i(ServiceBase.INSTANCE.getTAG(), "[NeuMapLCM]startOneCore success.");
                updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.RUNNING, null, 2, null));
                Tool.getSelfMaps("ONECORE");
            } else {
                ServiceBase.INSTANCE.getLogger().error("failed to start native applications; now we're stuck in undefined state :(");
                updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.ERROR, null, 2, null));
            }
        }
    }

    private final synchronized void setSurfaces() {
        ServiceBase.INSTANCE.getLogger().info("[NeuMapLCM]setSurfaces . size = " + this.mSurfaces.size() + " [" + CollectionsKt.joinToString$default(this.mSurfaces.keySet(), ListUtils.DEFAULT_JOIN_SEPARATOR, null, null, 0, null, null, 62, null) + ']');
        String tag = ServiceBase.INSTANCE.getTAG();
        StringBuilder sb = new StringBuilder("setSurfaces: ");
        Surface surface = this.mSurfaces.get("widget");
        Log.i(tag, sb.append(surface != null ? Boolean.valueOf(surface.isValid()) : null).toString());
        AndroidManager androidManager = this.mAndroidManager;
        if (androidManager != null) {
            androidManager.setSurfaces(this.mSurfaces);
        }
    }

    private final synchronized void notifySurfaceSizeChanged() {
        Log.i(ServiceBase.INSTANCE.getTAG(), "[notifySurfaceSizeChanged]");
        AndroidManager androidManager = this.mAndroidManager;
        if (androidManager != null) {
            androidManager.notifySurfaceSizeChanged();
        }
    }

    private final synchronized void setCurrentLocation(float[] currentLocation) {
        LocationHandler locationHandler = this.mLocationHandler;
        if (locationHandler != null) {
            LocationHandler.setCurrentLocation$default(locationHandler, currentLocation[0], currentLocation[1], currentLocation[2], false, 8, null);
        }
    }

    private final synchronized void setGpsLocationCanUpdate(boolean value) {
        Log.i(ServiceBase.INSTANCE.getTAG(), "[setGpsLocationCanUpdate] --> value == " + value);
        LocationHandler locationHandler = this.mLocationHandler;
        if (locationHandler != null) {
            locationHandler.setGpsLocationCanUpdate(value);
        }
    }

    private final void setVehicleSpeed(double carSpeedValue) {
        AndroidManager androidManager;
        LocationHandler locationHandler;
        Log.i(ServiceBase.INSTANCE.getTAG(), "[setVehicleSpeed] --> carSpeedValue == " + carSpeedValue);
        if ((CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) && (androidManager = this.mAndroidManager) != null) {
            androidManager.setVehicleSpeed(System.currentTimeMillis(), carSpeedValue);
        }
        if (!CheckBoardKt.isByd8155f() || (locationHandler = this.mLocationHandler) == null) {
            return;
        }
        locationHandler.setSystemAdapterCarSpeed(carSpeedValue);
    }

    private final synchronized void setPrintLocationLog(boolean value) {
        Log.i(ServiceBase.INSTANCE.getTAG(), "[setPrintLocationLog] --> value == " + value);
        printLocationLog = value;
    }

    public final synchronized void addNmeaListener() {
        Log.i(ServiceBase.INSTANCE.getTAG(), "[addNmeaListener]");
        LocationHandler locationHandler = this.mLocationHandler;
        if (locationHandler != null) {
            locationHandler.registerNmeaMessageListener();
        }
    }
}
