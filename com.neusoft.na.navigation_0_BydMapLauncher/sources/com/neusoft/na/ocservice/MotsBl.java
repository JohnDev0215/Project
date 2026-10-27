package com.neusoft.na.ocservice;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import com.neusoft.na.common.bean.TtsBean;
import com.neusoft.na.nodejs.InstanceOptions;
import com.neusoft.na.nodejs.JsCallback;
import com.neusoft.na.nodejs.JsContext;
import com.neusoft.na.nodejs.NodeInstance;
import com.neusoft.na.nodejs.NodeJs;
import com.neusoft.na.ocservice.MotsBl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;
import mu.KLogging;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: MotsBl.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0006\u0018\u0000 W2\u00020\u0001:\u0001WB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\r0+J\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00100+J\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\r0+J\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\r0+J\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\r0+J\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00060+J\u000e\u00101\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010+J\f\u00102\u001a\b\u0012\u0004\u0012\u00020\r0+J\f\u00103\u001a\b\u0012\u0004\u0012\u00020)0+J\f\u00104\u001a\b\u0012\u0004\u0012\u00020\r0+J\u000e\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0019J\u0010\u00108\u001a\u00020\u00102\u0006\u00109\u001a\u00020:H\u0002J\u0010\u0010;\u001a\u00020:2\u0006\u0010<\u001a\u00020\u0010H\u0002J\u000e\u0010=\u001a\u0002062\u0006\u0010>\u001a\u00020\u0017J\u000e\u0010?\u001a\u0002062\u0006\u0010@\u001a\u00020\u0019J\u000e\u0010A\u001a\u0002062\u0006\u0010<\u001a\u00020\u0010J\u000e\u0010B\u001a\u0002062\u0006\u0010C\u001a\u00020\u0019J\u000e\u0010D\u001a\u0002062\u0006\u0010E\u001a\u00020FJ\u000e\u0010G\u001a\u0002062\u0006\u0010H\u001a\u00020FJ\u0006\u0010I\u001a\u000206J\u0006\u0010J\u001a\u000206J\u0006\u0010K\u001a\u000206J\u000e\u0010L\u001a\u0002062\u0006\u0010M\u001a\u00020\u0019J\u000e\u0010N\u001a\u0002062\u0006\u0010O\u001a\u00020\u0019J\u001c\u0010P\u001a\u00020\u00192\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00060R2\u0006\u0010<\u001a\u00020\u0010J\u0006\u0010S\u001a\u000206J\u000e\u0010T\u001a\u0002062\u0006\u0010U\u001a\u00020\u0019J\u0006\u0010V\u001a\u000206R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001aR\u0016\u0010\u001b\u001a\n \u001d*\u0004\u0018\u00010\u001c0\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\"\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Lcom/neusoft/na/ocservice/MotsBl;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "abandonSpeakSubject", "Lrx/subjects/PublishSubject;", "Ljava/lang/Void;", "appLocaleSubject", "Lrx/subjects/BehaviorSubject;", "Ljava/util/Locale;", "exitAppSubject", "exitCompleteSubject", "exitWithoutRestartingSubject", "future", "Ljava/util/concurrent/ScheduledFuture;", "lastBrightness", "", "lastNetworkState", "", "Ljava/lang/Boolean;", "mScheduledExecutorService", "Ljava/util/concurrent/ScheduledExecutorService;", "kotlin.jvm.PlatformType", "nodeInst", "Lcom/neusoft/na/nodejs/NodeInstance;", "playBeepToneSubject", "playSoundEffectSubject", "running", "getRunning", "()Z", "setCurrentLocationSubject", "", "showDiagnoseSettingSubject", "speakAnnouncementSubject", "Lcom/neusoft/na/common/bean/TtsBean;", "getAbandonSpeakSubject", "Lrx/Observable;", "getAppLocaleObs", "getExitAppObs", "getExitCompleteObs", "getExitWithoutRestartingAppObs", "getPlayBeepToneObs", "getSetCurrentLocationObs", "getShowDiagnoseSettingObs", "getSpeakAnnouncementObs", "getplaySoundEffectObs", "internetStatus", "", "isNetWorkOn", "localeFromJS", "jsLocale", "Lorg/json/JSONObject;", "localeToJS", "locale", "setBrightnessValue", "brightness", "setClusterStatus", "ClusterStatusEnable", "setLocale", "setMapOnScreen", "mapOnScreenEnable", "setMapStatus", "status", "", "setOnKeyDownValue", "key", "setTimeChanged", "setTimeFormat", "setUserTime", "setVehicleBrakingStatus", "brakingState", "setWidgetStatus", "WidgetStatusEnable", "start", "args", "", "stop", "timeFormatChanged", "is24Hour", "timeZoneChanged", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MotsBl {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String JS_GLOBAL_ABANDON_SPEAK = "na_sys_abandon_speak";
    public static final String JS_GLOBAL_BOOT_SYSTEM_LOCALE = "na_sys_bootSystemLocale";
    public static final String JS_GLOBAL_EXIT_APP = "na_sys_exitApp";
    public static final String JS_GLOBAL_EXIT_COMPLETE = "na_sys_exit";
    public static final String JS_GLOBAL_EXIT_WITH_OUT_RESTARTING = "na_sys_exitWithoutRestarting";
    public static final String JS_GLOBAL_PLAY_BEEP_TONE = "na_sys_playBeepTone";
    public static final String JS_GLOBAL_PLAY_SOUND_EFFECT = "na_sys_playSoundEffect";
    public static final String JS_GLOBAL_SET_APPLICATION_LOCALE = "na_sys_setAppLocale";
    public static final String JS_GLOBAL_SET_BRIGHTNESS_VALUE = "na_sys_setBrightnessValue";
    public static final String JS_GLOBAL_SET_CLUSTER_STATUS = "na_sys_setClusterStatus";
    public static final String JS_GLOBAL_SET_CURRENT_LOCATION = "na_sys_setCurrentLocation";
    public static final String JS_GLOBAL_SET_INTERNET_STATUS = "na_sys_internetStatus";
    public static final String JS_GLOBAL_SET_MAP_ON_SCREEN = "na_sys_setActivityStatus";
    public static final String JS_GLOBAL_SET_MAP_STATUS = "na_sys_setMapStatusValue";
    public static final String JS_GLOBAL_SET_ON_KEYDOWNVALUE = "na_sys_setOnKeyDownValue";
    public static final String JS_GLOBAL_SET_SYSTEM_LOCALE = "na_sys_setSystemLocale";
    public static final String JS_GLOBAL_SET_SYSTEM_TIME = "na_sys_setSystemTime";
    public static final String JS_GLOBAL_SET_SYSTEM_TIMEZONE_OFFSET = "na_sys_setSystemTimeZoneOffset";
    public static final String JS_GLOBAL_SET_SYSTEM_TIME_Changed = "na_sys_setSystemTimeChange";
    public static final String JS_GLOBAL_SET_SYSTEM_TIME_FORMAT = "na_sys_setSystemTimeFormat";
    public static final String JS_GLOBAL_SET_VEHICLE_BRAKING_STATUS = "na_sys_setVehicleBrakingStatus";
    public static final String JS_GLOBAL_SET_WIDGET_STATUS = "na_sys_setWidgetStatus";
    public static final String JS_GLOBAL_SHOW_DIAGNOSE_SETTING = "na_sys_showDiagnoseSetting";
    public static final String JS_GLOBAL_SPEAK = "na_sys_speak";
    public static final String JS_PROPERTY_LOCALE_COUNTRY = "country";
    public static final String JS_PROPERTY_LOCALE_LANGUAGE = "language";
    public static final String JS_PROPERTY_LOCALE_SCRIPT = "script";
    public static final String LIQUIDCORE_UNIQUE_ID = "bl";
    public static final long TimeRound = 10000;
    private String TAG;
    private final PublishSubject<Void> abandonSpeakSubject;
    private final BehaviorSubject<Locale> appLocaleSubject;
    private final Context context;
    private final PublishSubject<Void> exitAppSubject;
    private final PublishSubject<Void> exitCompleteSubject;
    private final PublishSubject<Void> exitWithoutRestartingSubject;
    private ScheduledFuture<?> future;
    private float lastBrightness;
    private Boolean lastNetworkState;
    private ScheduledExecutorService mScheduledExecutorService;
    private NodeInstance nodeInst;
    private final PublishSubject<String> playBeepToneSubject;
    private final PublishSubject<Void> playSoundEffectSubject;
    private final PublishSubject<float[]> setCurrentLocationSubject;
    private final PublishSubject<Void> showDiagnoseSettingSubject;
    private final PublishSubject<TtsBean> speakAnnouncementSubject;

    public MotsBl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = "XJMotsBl";
        this.mScheduledExecutorService = Executors.newScheduledThreadPool(4);
        PublishSubject<Void> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this.abandonSpeakSubject = publishSubjectCreate;
        PublishSubject<TtsBean> publishSubjectCreate2 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate2, "create()");
        this.speakAnnouncementSubject = publishSubjectCreate2;
        PublishSubject<Void> publishSubjectCreate3 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate3, "create()");
        this.exitAppSubject = publishSubjectCreate3;
        PublishSubject<Void> publishSubjectCreate4 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate4, "create()");
        this.exitWithoutRestartingSubject = publishSubjectCreate4;
        PublishSubject<Void> publishSubjectCreate5 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate5, "create()");
        this.showDiagnoseSettingSubject = publishSubjectCreate5;
        PublishSubject<String> publishSubjectCreate6 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate6, "create()");
        this.playBeepToneSubject = publishSubjectCreate6;
        PublishSubject<Void> publishSubjectCreate7 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate7, "create()");
        this.playSoundEffectSubject = publishSubjectCreate7;
        BehaviorSubject<Locale> behaviorSubjectCreate = BehaviorSubject.create();
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate, "create()");
        this.appLocaleSubject = behaviorSubjectCreate;
        PublishSubject<Void> publishSubjectCreate8 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate8, "create()");
        this.exitCompleteSubject = publishSubjectCreate8;
        PublishSubject<float[]> publishSubjectCreate9 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate9, "create()");
        this.setCurrentLocationSubject = publishSubjectCreate9;
    }

    /* JADX INFO: compiled from: MotsBl.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0086T¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/neusoft/na/ocservice/MotsBl$Companion;", "Lmu/KLogging;", "()V", "JS_GLOBAL_ABANDON_SPEAK", "", "JS_GLOBAL_BOOT_SYSTEM_LOCALE", "JS_GLOBAL_EXIT_APP", "JS_GLOBAL_EXIT_COMPLETE", "JS_GLOBAL_EXIT_WITH_OUT_RESTARTING", "JS_GLOBAL_PLAY_BEEP_TONE", "JS_GLOBAL_PLAY_SOUND_EFFECT", "JS_GLOBAL_SET_APPLICATION_LOCALE", "JS_GLOBAL_SET_BRIGHTNESS_VALUE", "JS_GLOBAL_SET_CLUSTER_STATUS", "JS_GLOBAL_SET_CURRENT_LOCATION", "JS_GLOBAL_SET_INTERNET_STATUS", "JS_GLOBAL_SET_MAP_ON_SCREEN", "JS_GLOBAL_SET_MAP_STATUS", "JS_GLOBAL_SET_ON_KEYDOWNVALUE", "JS_GLOBAL_SET_SYSTEM_LOCALE", "JS_GLOBAL_SET_SYSTEM_TIME", "JS_GLOBAL_SET_SYSTEM_TIMEZONE_OFFSET", "JS_GLOBAL_SET_SYSTEM_TIME_Changed", "JS_GLOBAL_SET_SYSTEM_TIME_FORMAT", "JS_GLOBAL_SET_VEHICLE_BRAKING_STATUS", "JS_GLOBAL_SET_WIDGET_STATUS", "JS_GLOBAL_SHOW_DIAGNOSE_SETTING", "JS_GLOBAL_SPEAK", "JS_PROPERTY_LOCALE_COUNTRY", "JS_PROPERTY_LOCALE_LANGUAGE", "JS_PROPERTY_LOCALE_SCRIPT", "LIQUIDCORE_UNIQUE_ID", "TimeRound", "", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        try {
            NodeJs.init(new String[0]);
        } catch (Exception e) {
            INSTANCE.getLogger().warn(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsBl.Companion.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return e.getMessage();
                }
            });
        }
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void setTAG(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.TAG = str;
    }

    public final boolean getRunning() {
        return this.nodeInst != null;
    }

    public final boolean start(List<String> args, Locale locale) {
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(locale, "locale");
        Log.d(this.TAG, "start");
        if (this.nodeInst != null) {
            return true;
        }
        List<String> listMutableListOf = CollectionsKt.mutableListOf("assets:/bl/bundle.js", "--");
        listMutableListOf.addAll(args);
        NodeInstance nodeInstanceLaunch = NodeJs.launch(new InstanceOptions.Builder().captureConsole().logtag("nodejs:bl").args(listMutableListOf).build());
        Intrinsics.checkNotNullExpressionValue(nodeInstanceLaunch, "launch(InstanceOptions.B…_ID\").args(argv).build())");
        this.nodeInst = MotsBlKt.runSync(nodeInstanceLaunch, new C00861(locale));
        return true;
    }

    /* JADX INFO: renamed from: com.neusoft.na.ocservice.MotsBl$start$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MotsBl.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/neusoft/na/nodejs/JsContext;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class C00861 extends Lambda implements Function1<JsContext, Unit> {
        final /* synthetic */ Locale $locale;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00861(Locale locale) {
            super(1);
            this.$locale = locale;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) throws IOException {
            invoke2(jsContext);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(JsContext runSync) throws IOException {
            Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
            runSync.exec("na_sys_bootSystemLocale=" + MotsBl.this.localeToJS(this.$locale) + ';');
            final MotsBl motsBl = MotsBl.this;
            runSync.set(MotsBl.JS_GLOBAL_SPEAK, new JsCallback() { // from class: com.neusoft.na.ocservice.MotsBl$start$1$$ExternalSyntheticLambda0
                @Override // com.neusoft.na.nodejs.JsCallback
                public final Object apply(Object[] objArr) {
                    return MotsBl.C00861.invoke$lambda$0(motsBl, objArr);
                }
            });
            final MotsBl motsBl2 = MotsBl.this;
            runSync.set(MotsBl.JS_GLOBAL_SET_APPLICATION_LOCALE, new JsCallback() { // from class: com.neusoft.na.ocservice.MotsBl$start$1$$ExternalSyntheticLambda5
                @Override // com.neusoft.na.nodejs.JsCallback
                public final Object apply(Object[] objArr) {
                    return MotsBl.C00861.invoke$lambda$1(motsBl2, objArr);
                }
            });
            final MotsBl motsBl3 = MotsBl.this;
            runSync.set(MotsBl.JS_GLOBAL_ABANDON_SPEAK, new JsCallback() { // from class: com.neusoft.na.ocservice.MotsBl$start$1$$ExternalSyntheticLambda6
                @Override // com.neusoft.na.nodejs.JsCallback
                public final Object apply(Object[] objArr) {
                    return MotsBl.C00861.invoke$lambda$2(motsBl3, objArr);
                }
            });
            final MotsBl motsBl4 = MotsBl.this;
            runSync.set(MotsBl.JS_GLOBAL_EXIT_APP, new JsCallback() { // from class: com.neusoft.na.ocservice.MotsBl$start$1$$ExternalSyntheticLambda7
                @Override // com.neusoft.na.nodejs.JsCallback
                public final Object apply(Object[] objArr) {
                    return MotsBl.C00861.invoke$lambda$3(motsBl4, objArr);
                }
            });
            final MotsBl motsBl5 = MotsBl.this;
            runSync.set(MotsBl.JS_GLOBAL_EXIT_WITH_OUT_RESTARTING, new JsCallback() { // from class: com.neusoft.na.ocservice.MotsBl$start$1$$ExternalSyntheticLambda8
                @Override // com.neusoft.na.nodejs.JsCallback
                public final Object apply(Object[] objArr) {
                    return MotsBl.C00861.invoke$lambda$4(motsBl5, objArr);
                }
            });
            final MotsBl motsBl6 = MotsBl.this;
            runSync.set(MotsBl.JS_GLOBAL_SHOW_DIAGNOSE_SETTING, new JsCallback() { // from class: com.neusoft.na.ocservice.MotsBl$start$1$$ExternalSyntheticLambda9
                @Override // com.neusoft.na.nodejs.JsCallback
                public final Object apply(Object[] objArr) {
                    return MotsBl.C00861.invoke$lambda$5(motsBl6, objArr);
                }
            });
            final MotsBl motsBl7 = MotsBl.this;
            runSync.set(MotsBl.JS_GLOBAL_PLAY_BEEP_TONE, new JsCallback() { // from class: com.neusoft.na.ocservice.MotsBl$start$1$$ExternalSyntheticLambda10
                @Override // com.neusoft.na.nodejs.JsCallback
                public final Object apply(Object[] objArr) {
                    return MotsBl.C00861.invoke$lambda$6(motsBl7, objArr);
                }
            });
            final MotsBl motsBl8 = MotsBl.this;
            runSync.set(MotsBl.JS_GLOBAL_SET_APPLICATION_LOCALE, new JsCallback() { // from class: com.neusoft.na.ocservice.MotsBl$start$1$$ExternalSyntheticLambda11
                @Override // com.neusoft.na.nodejs.JsCallback
                public final Object apply(Object[] objArr) {
                    return MotsBl.C00861.invoke$lambda$7(motsBl8, objArr);
                }
            });
            final MotsBl motsBl9 = MotsBl.this;
            runSync.set(MotsBl.JS_GLOBAL_EXIT_COMPLETE, new JsCallback() { // from class: com.neusoft.na.ocservice.MotsBl$start$1$$ExternalSyntheticLambda1
                @Override // com.neusoft.na.nodejs.JsCallback
                public final Object apply(Object[] objArr) {
                    return MotsBl.C00861.invoke$lambda$8(motsBl9, objArr);
                }
            });
            final MotsBl motsBl10 = MotsBl.this;
            runSync.set(MotsBl.JS_GLOBAL_SET_CURRENT_LOCATION, new JsCallback() { // from class: com.neusoft.na.ocservice.MotsBl$start$1$$ExternalSyntheticLambda2
                @Override // com.neusoft.na.nodejs.JsCallback
                public final Object apply(Object[] objArr) {
                    return MotsBl.C00861.invoke$lambda$9(motsBl10, objArr);
                }
            });
            final MotsBl motsBl11 = MotsBl.this;
            runSync.set(MotsBl.JS_GLOBAL_PLAY_SOUND_EFFECT, new JsCallback() { // from class: com.neusoft.na.ocservice.MotsBl$start$1$$ExternalSyntheticLambda3
                @Override // com.neusoft.na.nodejs.JsCallback
                public final Object apply(Object[] objArr) {
                    return MotsBl.C00861.invoke$lambda$10(motsBl11, objArr);
                }
            });
            MotsBl motsBl12 = MotsBl.this;
            ScheduledExecutorService scheduledExecutorService = motsBl12.mScheduledExecutorService;
            final MotsBl motsBl13 = MotsBl.this;
            motsBl12.future = scheduledExecutorService.scheduleAtFixedRate(new Runnable() { // from class: com.neusoft.na.ocservice.MotsBl$start$1$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    MotsBl.C00861.invoke$lambda$11(motsBl13);
                }
            }, 0L, 10000L, TimeUnit.MILLISECONDS);
            InputStream inputStreamOpen = MotsBl.this.context.getAssets().open("bl/bundle.js");
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "context.assets.open(\"bl/bundle.js\")");
            Reader inputStreamReader = new InputStreamReader(inputStreamOpen, Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                String text = TextStreamsKt.readText(bufferedReader);
                CloseableKt.closeFinally(bufferedReader, null);
                runSync.exec(text);
            } finally {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$0(MotsBl this$0, Object[] it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (!(it.length == 0)) {
                String string = it[0].toString();
                MotsBl.INSTANCE.getLogger().info("speak: " + string);
                TtsBean ttsBean = new TtsBean();
                ttsBean.setMsg(string);
                this$0.speakAnnouncementSubject.onNext(ttsBean);
            } else {
                MotsBl.INSTANCE.getLogger().warn(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsBl$start$1$1$1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "na_sys_speak: unexpected arguments";
                    }
                });
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$1(MotsBl this$0, Object[] it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullExpressionValue(it, "it");
            JSONObject jSONObject = null;
            if (!(it.length == 0)) {
                Object obj = it[0];
                if (obj instanceof JSONObject) {
                    jSONObject = (JSONObject) obj;
                }
            }
            if (jSONObject != null) {
                Locale localeLocaleFromJS = this$0.localeFromJS(jSONObject);
                MotsBl.INSTANCE.getLogger().info("setAppLocale: " + localeLocaleFromJS);
                this$0.appLocaleSubject.onNext(localeLocaleFromJS);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$2(MotsBl this$0, Object[] objArr) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            MotsBl.INSTANCE.getLogger().info("abandonSpeak");
            this$0.abandonSpeakSubject.onNext(null);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$3(MotsBl this$0, Object[] objArr) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.exitAppSubject.onNext(null);
            MotsBl.INSTANCE.getLogger().info("exitApp:");
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$4(MotsBl this$0, Object[] objArr) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.exitWithoutRestartingSubject.onNext(null);
            MotsBl.INSTANCE.getLogger().info("exitWithoutRestarting:");
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$5(MotsBl this$0, Object[] objArr) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            MotsBl.INSTANCE.getLogger().info("showDiagnoseSetting:");
            this$0.showDiagnoseSettingSubject.onNext(null);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$6(MotsBl this$0, Object[] it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullExpressionValue(it, "it");
            String str = null;
            if (!(it.length == 0)) {
                Object obj = it[0];
                if (obj instanceof String) {
                    str = (String) obj;
                }
            }
            if (str != null) {
                MotsBl.INSTANCE.getLogger().info("playBeepTone:" + str);
                this$0.playBeepToneSubject.onNext(str);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$7(MotsBl this$0, Object[] it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullExpressionValue(it, "it");
            JSONObject jSONObject = null;
            if (!(it.length == 0)) {
                Object obj = it[0];
                if (obj instanceof JSONObject) {
                    jSONObject = (JSONObject) obj;
                }
            }
            if (jSONObject != null) {
                Locale localeLocaleFromJS = this$0.localeFromJS(jSONObject);
                MotsBl.INSTANCE.getLogger().info("setAppLocale: " + localeLocaleFromJS);
                this$0.appLocaleSubject.onNext(localeLocaleFromJS);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$8(MotsBl this$0, Object[] objArr) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Log.d(this$0.getTAG(), "exitComplete");
            this$0.exitCompleteSubject.onNext(null);
            MotsBl.INSTANCE.getLogger().info("exitComplete:");
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$9(MotsBl this$0, Object[] it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Log.d(this$0.getTAG(), "setCurrentLocation");
            Intrinsics.checkNotNullExpressionValue(it, "it");
            JSONObject jSONObject = null;
            if (!(it.length == 0)) {
                Object obj = it[0];
                if (obj instanceof JSONObject) {
                    jSONObject = (JSONObject) obj;
                }
            }
            if (jSONObject != null) {
                String latitude = jSONObject.optString("latitude");
                String longitude = jSONObject.optString("longitude");
                String bearing = jSONObject.optString("bearing");
                float[] fArr = new float[3];
                Intrinsics.checkNotNullExpressionValue(latitude, "latitude");
                if (latitude.length() > 0) {
                    fArr[0] = Float.parseFloat(latitude);
                }
                Intrinsics.checkNotNullExpressionValue(longitude, "longitude");
                if (longitude.length() > 0) {
                    fArr[1] = Float.parseFloat(longitude);
                }
                Intrinsics.checkNotNullExpressionValue(bearing, "bearing");
                if (bearing.length() > 0) {
                    float f = Float.parseFloat(bearing);
                    fArr[2] = f;
                    if (f == 0.0f) {
                        fArr[2] = 0.1f;
                    }
                }
                this$0.setCurrentLocationSubject.onNext(fArr);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$10(MotsBl this$0, Object[] objArr) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.playSoundEffectSubject.onNext(null);
            MotsBl.INSTANCE.getLogger().info("playSoundEffect:");
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$11(MotsBl this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.setTimeFormat();
        }
    }

    public final void setLocale(final Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        INSTANCE.getLogger().info("new locale: " + locale.getISO3Language());
        NodeInstance nodeInstance = this.nodeInst;
        if (nodeInstance != null) {
            MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.setLocale.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                    invoke2(jsContext);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(JsContext runSync) {
                    Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                    runSync.call(MotsBl.JS_GLOBAL_SET_SYSTEM_LOCALE, MotsBl.this.localeToJS(locale));
                }
            });
        }
    }

    public final void setBrightnessValue(final float brightness) {
        INSTANCE.getLogger().debug("new brightness value: " + brightness);
        if (((double) (Math.abs(brightness - this.lastBrightness) / this.lastBrightness)) > 0.1d) {
            NodeInstance nodeInstance = this.nodeInst;
            if (nodeInstance != null) {
                MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.setBrightnessValue.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                        invoke2(jsContext);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JsContext runSync) {
                        Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                        runSync.call(MotsBl.JS_GLOBAL_SET_BRIGHTNESS_VALUE, Float.valueOf(brightness));
                    }
                });
            }
            this.lastBrightness = brightness;
        }
    }

    public final void setVehicleBrakingStatus(final boolean brakingState) {
        NodeInstance nodeInstance;
        INSTANCE.getLogger().debug("set setVehicleBrakingStatus " + brakingState);
        if (!getRunning() || (nodeInstance = this.nodeInst) == null) {
            return;
        }
        MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.setVehicleBrakingStatus.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                invoke2(jsContext);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JsContext runSync) {
                Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                runSync.call(MotsBl.JS_GLOBAL_SET_VEHICLE_BRAKING_STATUS, Boolean.valueOf(brakingState));
            }
        });
    }

    public final void internetStatus(final boolean isNetWorkOn) {
        if (!getRunning()) {
            INSTANCE.getLogger().error("MotsBl is not running");
            return;
        }
        Boolean bool = this.lastNetworkState;
        if (bool == null) {
            INSTANCE.getLogger().debug("init set internetStatus, isNetWorkOn is " + isNetWorkOn);
            NodeInstance nodeInstance = this.nodeInst;
            if (nodeInstance != null) {
                MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.internetStatus.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                        invoke2(jsContext);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JsContext runSync) {
                        Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                        runSync.call(MotsBl.JS_GLOBAL_SET_INTERNET_STATUS, Boolean.valueOf(isNetWorkOn));
                    }
                });
            }
            this.lastNetworkState = Boolean.valueOf(isNetWorkOn);
            return;
        }
        if (Intrinsics.areEqual(bool, Boolean.valueOf(isNetWorkOn))) {
            return;
        }
        INSTANCE.getLogger().debug("more set internetStatus, isNetWorkOn is " + isNetWorkOn);
        NodeInstance nodeInstance2 = this.nodeInst;
        if (nodeInstance2 != null) {
            MotsBlKt.runSync(nodeInstance2, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.internetStatus.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                    invoke2(jsContext);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(JsContext runSync) {
                    Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                    runSync.call(MotsBl.JS_GLOBAL_SET_INTERNET_STATUS, Boolean.valueOf(isNetWorkOn));
                }
            });
        }
        this.lastNetworkState = Boolean.valueOf(isNetWorkOn);
    }

    public final void setMapOnScreen(final boolean mapOnScreenEnable) {
        NodeInstance nodeInstance;
        Log.d(this.TAG, "setMapOnScreen:" + mapOnScreenEnable + " + running:" + getRunning());
        if (!getRunning() || (nodeInstance = this.nodeInst) == null) {
            return;
        }
        MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.setMapOnScreen.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                invoke2(jsContext);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JsContext runSync) {
                Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                runSync.call(MotsBl.JS_GLOBAL_SET_MAP_ON_SCREEN, Boolean.valueOf(mapOnScreenEnable));
            }
        });
    }

    public final void setWidgetStatus(final boolean WidgetStatusEnable) {
        NodeInstance nodeInstance;
        Log.d(this.TAG, "setWidgetStatus:" + WidgetStatusEnable);
        if (!getRunning() || (nodeInstance = this.nodeInst) == null) {
            return;
        }
        MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.setWidgetStatus.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                invoke2(jsContext);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JsContext runSync) {
                Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                Log.d(MotsBl.this.getTAG(), "to Bl setWidgetStatus:" + WidgetStatusEnable);
                runSync.call(MotsBl.JS_GLOBAL_SET_WIDGET_STATUS, Boolean.valueOf(WidgetStatusEnable));
            }
        });
    }

    public final void setClusterStatus(final boolean ClusterStatusEnable) {
        NodeInstance nodeInstance;
        Log.d(this.TAG, "setClusterStatus:" + ClusterStatusEnable);
        if (!getRunning() || (nodeInstance = this.nodeInst) == null) {
            return;
        }
        MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.setClusterStatus.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                invoke2(jsContext);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JsContext runSync) {
                Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                Log.d(MotsBl.this.getTAG(), "to Bl setClusterStatus:" + ClusterStatusEnable);
                runSync.call(MotsBl.JS_GLOBAL_SET_CLUSTER_STATUS, Boolean.valueOf(ClusterStatusEnable));
            }
        });
    }

    public final void setMapStatus(final int status) {
        if (getRunning()) {
            Log.d(this.TAG, "setMapStatus:" + status);
            NodeInstance nodeInstance = this.nodeInst;
            if (nodeInstance != null) {
                MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.setMapStatus.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                        invoke2(jsContext);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JsContext runSync) {
                        Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                        runSync.call(MotsBl.JS_GLOBAL_SET_MAP_STATUS, Integer.valueOf(status));
                    }
                });
            }
        }
    }

    public final void setOnKeyDownValue(final int key) {
        NodeInstance nodeInstance;
        Log.d(this.TAG, "setOnKeyDownValue:" + key);
        if (!getRunning() || (nodeInstance = this.nodeInst) == null) {
            return;
        }
        MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.setOnKeyDownValue.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                invoke2(jsContext);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JsContext runSync) {
                Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                runSync.call(MotsBl.JS_GLOBAL_SET_ON_KEYDOWNVALUE, Integer.valueOf(key));
            }
        });
    }

    public final void setUserTime() {
        NodeInstance nodeInstance;
        if (!getRunning() || (nodeInstance = this.nodeInst) == null) {
            return;
        }
        MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.setUserTime.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                invoke2(jsContext);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JsContext runSync) {
                Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                try {
                    Calendar calendar = Calendar.getInstance();
                    long timeInMillis = calendar.getTimeInMillis() + ((long) calendar.getTimeZone().getRawOffset()) + ((long) calendar.get(16));
                    Log.d(MotsBl.this.getTAG(), "setUserTime::" + timeInMillis);
                    runSync.call(MotsBl.JS_GLOBAL_SET_SYSTEM_TIME, Long.valueOf(timeInMillis));
                } catch (Exception e) {
                    Log.e(MotsBl.this.getTAG(), "to funcation Exception " + e.getMessage());
                }
            }
        });
    }

    public final void timeZoneChanged() {
        if (getRunning()) {
            NodeInstance nodeInstance = this.nodeInst;
            if (nodeInstance != null) {
                MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.timeZoneChanged.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                        invoke2(jsContext);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JsContext runSync) {
                        Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                        Calendar calendar = Calendar.getInstance();
                        int rawOffset = (calendar.getTimeZone().getRawOffset() + calendar.get(16)) / 1000;
                        Log.d(MotsBl.this.getTAG(), "timeZoneChanged:offset::" + rawOffset);
                        runSync.call(MotsBl.JS_GLOBAL_SET_SYSTEM_TIMEZONE_OFFSET, Integer.valueOf(rawOffset));
                    }
                });
            }
            setTimeChanged();
            setUserTime();
        }
        Log.d(this.TAG, "timeZoneChanged");
    }

    public final void setTimeFormat() {
        if (getRunning()) {
            NodeInstance nodeInstance = this.nodeInst;
            if (nodeInstance != null) {
                MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.setTimeFormat.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                        invoke2(jsContext);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JsContext runSync) {
                        Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                        boolean zIs24HourFormat = DateFormat.is24HourFormat(MotsBl.this.context);
                        if (zIs24HourFormat) {
                            runSync.call(MotsBl.JS_GLOBAL_SET_SYSTEM_TIME_FORMAT, 2);
                        } else {
                            runSync.call(MotsBl.JS_GLOBAL_SET_SYSTEM_TIME_FORMAT, 1);
                        }
                        Log.d(MotsBl.this.getTAG(), "setTimeFormat:is24Hour[" + zIs24HourFormat + ']');
                        MotsBl.INSTANCE.getLogger().debug("setTimeFormat:is24Hour[" + zIs24HourFormat + ']');
                    }
                });
            }
            setUserTime();
        }
        Log.d(this.TAG, "setTimeFormat");
        INSTANCE.getLogger().debug("setTimeFormat");
    }

    public final void setTimeChanged() {
        NodeInstance nodeInstance;
        Log.d(this.TAG, "setTimeChanged");
        if (!getRunning() || (nodeInstance = this.nodeInst) == null) {
            return;
        }
        MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.setTimeChanged.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                invoke2(jsContext);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JsContext runSync) {
                Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                try {
                    Calendar calendar = Calendar.getInstance();
                    long timeInMillis = calendar.getTimeInMillis() + ((long) calendar.getTimeZone().getRawOffset()) + ((long) calendar.get(16));
                    Log.d(MotsBl.this.getTAG(), "setTimeChanged::" + timeInMillis);
                    runSync.call(MotsBl.JS_GLOBAL_SET_SYSTEM_TIME_Changed, Long.valueOf(timeInMillis));
                } catch (Exception e) {
                    Log.e(MotsBl.this.getTAG(), "to funcation Exception " + e.getMessage());
                }
            }
        });
    }

    public final void timeFormatChanged(final boolean is24Hour) {
        if (getRunning()) {
            NodeInstance nodeInstance = this.nodeInst;
            if (nodeInstance != null) {
                MotsBlKt.runSync(nodeInstance, new Function1<JsContext, Unit>() { // from class: com.neusoft.na.ocservice.MotsBl.timeFormatChanged.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JsContext jsContext) {
                        invoke2(jsContext);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JsContext runSync) {
                        Intrinsics.checkNotNullParameter(runSync, "$this$runSync");
                        if (is24Hour) {
                            runSync.call(MotsBl.JS_GLOBAL_SET_SYSTEM_TIME_FORMAT, 2);
                        } else {
                            runSync.call(MotsBl.JS_GLOBAL_SET_SYSTEM_TIME_FORMAT, 1);
                        }
                        Log.d(this.getTAG(), "timeFormatChanged:is24Hour::" + is24Hour);
                        MotsBl.INSTANCE.getLogger().debug("timeFormatChanged:is24Hour::" + is24Hour);
                    }
                });
            }
            setUserTime();
        }
        Log.d(this.TAG, "timeFormatChanged");
        INSTANCE.getLogger().debug("timeFormatChanged");
    }

    public final void stop() {
        NodeInstance nodeInstance = this.nodeInst;
        if (nodeInstance != null) {
            nodeInstance.terminate();
        }
        this.nodeInst = null;
    }

    public final Observable<Void> getExitAppObs() {
        Observable<Void> observableAsObservable = this.exitAppSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.exitAppSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Void> getExitWithoutRestartingAppObs() {
        Observable<Void> observableAsObservable = this.exitWithoutRestartingSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.exitWithoutRestartingSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Void> getShowDiagnoseSettingObs() {
        Observable<Void> observableAsObservable = this.showDiagnoseSettingSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.showDiagnoseSettingSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<String> getPlayBeepToneObs() {
        Observable<String> observableAsObservable = this.playBeepToneSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.playBeepToneSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Void> getplaySoundEffectObs() {
        Observable<Void> observableAsObservable = this.playSoundEffectSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.playSoundEffectSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Void> getExitCompleteObs() {
        Observable<Void> observableAsObservable = this.exitCompleteSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.exitCompleteSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Void> getAbandonSpeakSubject() {
        Observable<Void> observableAsObservable = this.abandonSpeakSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.abandonSpeakSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<TtsBean> getSpeakAnnouncementObs() {
        Observable<TtsBean> observableAsObservable = this.speakAnnouncementSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.speakAnnouncementSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Locale> getAppLocaleObs() {
        Observable<Locale> observableAsObservable = this.appLocaleSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.appLocaleSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<float[]> getSetCurrentLocationObs() {
        return this.setCurrentLocationSubject.asObservable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONObject localeToJS(Locale locale) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(JS_PROPERTY_LOCALE_LANGUAGE, locale.getISO3Language());
        try {
            String iSO3Country = locale.getISO3Country();
            if (!(iSO3Country == null || iSO3Country.length() == 0)) {
                jSONObject.put(JS_PROPERTY_LOCALE_COUNTRY, locale.getISO3Country());
            }
        } catch (MissingResourceException unused) {
            jSONObject.put(JS_PROPERTY_LOCALE_COUNTRY, "");
        }
        String script = locale.getScript();
        if (!(script == null || script.length() == 0)) {
            jSONObject.put(JS_PROPERTY_LOCALE_SCRIPT, locale.getScript());
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Locale localeFromJS(JSONObject jsLocale) {
        return new Locale(jsLocale.getString(JS_PROPERTY_LOCALE_LANGUAGE));
    }
}
