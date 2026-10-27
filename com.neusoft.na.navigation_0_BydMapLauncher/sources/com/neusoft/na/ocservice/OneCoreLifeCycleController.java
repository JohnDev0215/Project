package com.neusoft.na.ocservice;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import com.neusoft.na.common.BasicLifeCycleController;
import com.neusoft.na.common.ServiceBase;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.functions.Action1;

/* JADX INFO: compiled from: OneCoreLifeCycleController.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001aJ\u000e\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u00020\u001a2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0011J\u000e\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u0013J\u000e\u0010%\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020\u0016J\u000e\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020\u000fJ\u000e\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u0016J\u000e\u0010,\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u0013J\u0010\u0010-\u001a\u00020\u001a2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0011J\u0010\u0010.\u001a\u00020\u001a2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0011J\u0006\u0010/\u001a\u00020\u001aJ\u000e\u00100\u001a\u00020\u001a2\u0006\u00101\u001a\u00020\tJ\u0006\u00102\u001a\u00020\u001aR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/neusoft/na/ocservice/OneCoreLifeCycleController;", "Lcom/neusoft/na/common/BasicLifeCycleController;", "context", "Landroid/content/Context;", "serviceClass", "Ljava/lang/Class;", "Lcom/neusoft/na/common/ServiceBase;", "(Landroid/content/Context;Ljava/lang/Class;)V", "MTAG", "", "getMTAG", "()Ljava/lang/String;", "setMTAG", "(Ljava/lang/String;)V", "callBack", "Lcom/neusoft/na/ocservice/MainCallBack;", "clusterSurface", "Landroid/view/Surface;", OneCoreService.PAYLOAD_IN_MULTI_WINDOW_MODE, "", "Ljava/lang/Boolean;", OneCoreService.PAYLOAD_MAP_STATUS, "", OneCoreService.PAYLOAD_SURFACE, "widgetSurface", "addNmeaListener", "", "notifySurfaceSizeChanged", "setCarSpeed", "carSpeed", "", "setClusterSurface", "setCurrentLocation", OneCoreService.PAYLOAD_CURRENT_LOCATION, "", "setGpsLocationCanUpdate", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "setInMultiWindowMode", "setKeyCode", "code", "setMainCallBack", "callback", "setMapStatus", "status", "setPrintLocationLog", "setSurface", "setWidgetSurface", "timeZoneChanged", "usbMount", OneCoreService.PAYLOAD_KEY_PATH, "usbUnMount", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OneCoreLifeCycleController extends BasicLifeCycleController {
    private String MTAG;
    private MainCallBack callBack;
    private Surface clusterSurface;
    private Boolean isInMultiWindowMode;
    private int mapStatus;
    private Surface surface;
    private Surface widgetSurface;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneCoreLifeCycleController(Context context, Class<? extends ServiceBase> serviceClass) {
        super(context, serviceClass);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(serviceClass, "serviceClass");
        this.mapStatus = -1;
        this.MTAG = "XJOneCoreLifeCycleController";
        Observable<Boolean> serviceReadyObservable = getServiceReadyObservable();
        final Function1<Boolean, Unit> function1 = new Function1<Boolean, Unit>() { // from class: com.neusoft.na.ocservice.OneCoreLifeCycleController.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean it) {
                Log.i(OneCoreLifeCycleController.this.getMTAG(), "getServiceReadyObservable");
                Intrinsics.checkNotNullExpressionValue(it, "it");
                if (it.booleanValue()) {
                    if (OneCoreLifeCycleController.this.isInMultiWindowMode != null) {
                        OneCoreLifeCycleController oneCoreLifeCycleController = OneCoreLifeCycleController.this;
                        Boolean bool = oneCoreLifeCycleController.isInMultiWindowMode;
                        Intrinsics.checkNotNull(bool);
                        oneCoreLifeCycleController.setInMultiWindowMode(bool.booleanValue());
                    }
                    if (OneCoreLifeCycleController.this.surface != null) {
                        OneCoreLifeCycleController oneCoreLifeCycleController2 = OneCoreLifeCycleController.this;
                        oneCoreLifeCycleController2.setSurface(oneCoreLifeCycleController2.surface);
                    }
                    if (OneCoreLifeCycleController.this.mapStatus != -1) {
                        OneCoreLifeCycleController oneCoreLifeCycleController3 = OneCoreLifeCycleController.this;
                        oneCoreLifeCycleController3.setMapStatus(oneCoreLifeCycleController3.mapStatus);
                    }
                    if (OneCoreLifeCycleController.this.widgetSurface != null) {
                        OneCoreLifeCycleController oneCoreLifeCycleController4 = OneCoreLifeCycleController.this;
                        oneCoreLifeCycleController4.setWidgetSurface(oneCoreLifeCycleController4.widgetSurface);
                    }
                    if (OneCoreLifeCycleController.this.clusterSurface != null) {
                        OneCoreLifeCycleController oneCoreLifeCycleController5 = OneCoreLifeCycleController.this;
                        oneCoreLifeCycleController5.setClusterSurface(oneCoreLifeCycleController5.clusterSurface);
                    }
                }
            }
        };
        serviceReadyObservable.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.OneCoreLifeCycleController$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                OneCoreLifeCycleController._init_$lambda$0(function1, obj);
            }
        });
        Observable<Void> playSoundObservable = getPlaySoundObservable();
        final Function1<Void, Unit> function12 = new Function1<Void, Unit>() { // from class: com.neusoft.na.ocservice.OneCoreLifeCycleController.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Void r1) {
                invoke2(r1);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Void r1) {
                MainCallBack mainCallBack = OneCoreLifeCycleController.this.callBack;
                if (mainCallBack != null) {
                    mainCallBack.onPlaySoundEffect();
                }
            }
        };
        playSoundObservable.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.OneCoreLifeCycleController$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                OneCoreLifeCycleController._init_$lambda$1(function12, obj);
            }
        });
        Observable<Integer> dayNightObservable = getDayNightObservable();
        final Function1<Integer, Unit> function13 = new Function1<Integer, Unit>() { // from class: com.neusoft.na.ocservice.OneCoreLifeCycleController.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke2(num);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Integer it) {
                MainCallBack mainCallBack = OneCoreLifeCycleController.this.callBack;
                if (mainCallBack != null) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    mainCallBack.onDayNightModeChanged(it.intValue());
                }
            }
        };
        dayNightObservable.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.OneCoreLifeCycleController$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                OneCoreLifeCycleController._init_$lambda$2(function13, obj);
            }
        });
    }

    public /* synthetic */ OneCoreLifeCycleController(Context context, Class cls, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? OneCoreService.class : cls);
    }

    public final String getMTAG() {
        return this.MTAG;
    }

    public final void setMTAG(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.MTAG = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void setMainCallBack(MainCallBack callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Log.i(this.MTAG, "setMainCallBack");
        this.callBack = callback;
    }

    public final void setSurface(Surface surface) {
        Log.i(this.MTAG, "setSurface");
        this.surface = surface;
        Bundle bundle = new Bundle();
        bundle.putParcelable(OneCoreService.PAYLOAD_SURFACE, surface);
        Unit unit = Unit.INSTANCE;
        send(OneCoreService.MSG_REQ_SET_SURFACE, bundle);
    }

    public final void notifySurfaceSizeChanged() {
        Log.i(this.MTAG, "[notifySurfaceSizeChanged]");
        send(OneCoreService.MSG_NOTIFY_SURFACE_SIZE_CHANGED, null);
    }

    public final void setWidgetSurface(Surface surface) {
        Log.i(this.MTAG, "setWidgetSurface");
        this.widgetSurface = surface;
        Bundle bundle = new Bundle();
        bundle.putParcelable(OneCoreService.PAYLOAD_SURFACE, surface);
        Unit unit = Unit.INSTANCE;
        send(OneCoreService.MSG_REQ_SET_WIDGET_SURFACE, bundle);
    }

    public final void setClusterSurface(Surface surface) {
        Log.i(this.MTAG, "setClusterSurface");
        this.clusterSurface = surface;
        Bundle bundle = new Bundle();
        bundle.putParcelable(OneCoreService.PAYLOAD_SURFACE, surface);
        Unit unit = Unit.INSTANCE;
        send(OneCoreService.MSG_REQ_SET_CLUSTER_SURFACE, bundle);
    }

    public final void setMapStatus(int status) {
        Log.i(this.MTAG, "setMapStatus" + status);
        this.mapStatus = status;
        Bundle bundle = new Bundle();
        bundle.putInt(OneCoreService.PAYLOAD_MAP_STATUS, status);
        Unit unit = Unit.INSTANCE;
        send(OneCoreService.MSG_REQ_SET_MAP_STATUS, bundle);
    }

    public final void setKeyCode(int code) {
        Log.i(this.MTAG, "setBackPressed");
        Bundle bundle = new Bundle();
        bundle.putInt(OneCoreService.PAYLOAD_KEY_VALUE, code);
        Unit unit = Unit.INSTANCE;
        send(OneCoreService.MSG_REQ_SET_ON_KEYDOWNVALUE, bundle);
    }

    public final void timeZoneChanged() {
        Log.i(this.MTAG, "timeZoneChanged");
        send(OneCoreService.MSG_REQ_TIMEZONE_CHANGED, new Bundle());
    }

    public final void usbMount(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        Log.i(this.MTAG, "usbMount --> " + path);
        Bundle bundle = new Bundle();
        bundle.putString(OneCoreService.PAYLOAD_KEY_PATH, path);
        Unit unit = Unit.INSTANCE;
        send(OneCoreService.MSG_REQ_USB_MOUNT, bundle);
    }

    public final void usbUnMount() {
        Log.i(this.MTAG, "usbUnMount");
        send(OneCoreService.MSG_REQ_USB_UNMOUNT, new Bundle());
    }

    public final void setCurrentLocation(float[] currentLocation) {
        Intrinsics.checkNotNullParameter(currentLocation, "currentLocation");
        Log.i(this.MTAG, "setCurrentLocation --> currentLocation == " + currentLocation);
        Bundle bundle = new Bundle();
        bundle.putFloatArray(OneCoreService.PAYLOAD_CURRENT_LOCATION, currentLocation);
        Unit unit = Unit.INSTANCE;
        send(OneCoreService.MSG_SET_CURRENT_LOCATION, bundle);
    }

    public final void setGpsLocationCanUpdate(boolean value) {
        Log.i(this.MTAG, "setGpsLocationCanUpdate --> value == " + value);
        Bundle bundle = new Bundle();
        bundle.putBoolean(OneCoreService.PAYLOAD_GPS_LOCATION_CAN_UPDATE, value);
        Unit unit = Unit.INSTANCE;
        send(OneCoreService.MSG_SET_GPS_LOCATION_CAN_UPDATE, bundle);
    }

    public final void setInMultiWindowMode(boolean isInMultiWindowMode) {
        Log.i(this.MTAG, "[setInMultiWindowMode] --> isInMultiWindowMode == " + isInMultiWindowMode);
        this.isInMultiWindowMode = Boolean.valueOf(isInMultiWindowMode);
        Bundle bundle = new Bundle();
        bundle.putBoolean(OneCoreService.PAYLOAD_IN_MULTI_WINDOW_MODE, isInMultiWindowMode);
        Unit unit = Unit.INSTANCE;
        send(OneCoreService.MSG_SET_IN_MULTI_WINDOW_MODE, bundle);
    }

    public final void setCarSpeed(float carSpeed) {
        Log.i(this.MTAG, "[setCarSpeed] --> carSpeed == " + carSpeed);
        Bundle bundle = new Bundle();
        bundle.putFloat(OneCoreService.PAYLOAD_CAR_SPEED_VALUE, carSpeed);
        Unit unit = Unit.INSTANCE;
        send(OneCoreService.MSG_SET_CAR_SPEED_CHANGED, bundle);
    }

    public final void setPrintLocationLog(boolean value) {
        Log.i(this.MTAG, "[setPrintLocationLog] --> value == " + value);
        Bundle bundle = new Bundle();
        bundle.putBoolean(OneCoreService.PAYLOAD_PRINT_LOCATION_LOG, value);
        Unit unit = Unit.INSTANCE;
        send(OneCoreService.MSG_SET_PRINT_LOCATION_LOG, bundle);
    }

    public final void addNmeaListener() {
        Log.i(this.MTAG, "[addNmeaListener]");
        send(OneCoreService.MSG_ADD_NMEA_LISTENER, new Bundle());
    }
}
