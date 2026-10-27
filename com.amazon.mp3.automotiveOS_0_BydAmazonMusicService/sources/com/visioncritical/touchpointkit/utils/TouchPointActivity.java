package com.visioncritical.touchpointkit.utils;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.provider.Settings;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoServiceConstants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.snackbar.Snackbar;
import com.visioncritical.touchpointkit.api.TouchPointRestClient;
import com.visioncritical.touchpointkit.commons.TouchPointCommon;
import com.visioncritical.touchpointkit.commons.TouchPointLogger;
import com.visioncritical.touchpointkit.models.TouchPointScreenComponent;
import com.visioncritical.touchpointkit.models.TouchPointTrigger;
import com.visioncritical.touchpointkit.ui.TouchPointWebAppInterface;
import com.visioncritical.touchpointkit.ui.TouchPointWebViewActivity;
import com.visioncritical.touchpointkit.utils.TouchPointActivity;
import com.visualon.OSMPUtils.voOSType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TouchPointActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0019\u0018\u0000 q2\u00020\u0001:\u0002qrB\t\b\u0002¢\u0006\u0004\bo\u0010pJ\"\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0002J(\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0002J.\u0010\u000b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\b0\n2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\u001c\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0002J\u0012\u0010\u0010\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0002J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0011\u001a\u00020\u0002H\u0002J\u0019\u0010\u0013\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0011\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001c\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0002J,\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0002J\u000e\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0018JT\u0010 \u001a\u00020\f2(\u0010\u001e\u001a$\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u001cj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002`\u001d0\u001b2\"\u0010\u001f\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u001cj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0001`\u001dJ.\u0010#\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!J\u001a\u0010$\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002J\b\u0010%\u001a\u00020\u0002H\u0007J\u0006\u0010&\u001a\u00020\u0002J\u000e\u0010(\u001a\u00020\f2\u0006\u0010'\u001a\u00020\u0002J\u000e\u0010)\u001a\u00020\b2\u0006\u0010'\u001a\u00020\u0002J\u0016\u0010+\u001a\u00020\f2\u0006\u0010'\u001a\u00020\u00022\u0006\u0010*\u001a\u00020\bJ\u001e\u0010-\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\b0\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0007J,\u0010.\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u00022\b\b\u0002\u0010*\u001a\u00020\b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!R\u0016\u0010/\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00100R\u0016\u00102\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u00100R\u0018\u00106\u001a\u0004\u0018\u0001058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0019\u00108R\u0016\u00109\u001a\u00020\u00188\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b9\u00108R\u0014\u0010:\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b:\u00100R2\u0010;\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020,0\u001cj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020,`\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010<R2\u0010=\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u001cj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b`\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010<R\u001c\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00050>8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u0010@R2\u0010B\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020A0\u001cj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020A`\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u0010<R\"\u0010C\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bC\u00103\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\"\u0010H\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bH\u00103\u001a\u0004\bI\u0010E\"\u0004\bJ\u0010GR\"\u0010K\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bK\u00103\u001a\u0004\bL\u0010E\"\u0004\bM\u0010GR\"\u0010N\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bN\u00103\u001a\u0004\bO\u0010E\"\u0004\bP\u0010GR\"\u0010Q\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bQ\u00103\u001a\u0004\bQ\u0010E\"\u0004\bR\u0010GR>\u0010\u001f\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u001cj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0001`\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010<\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR>\u0010W\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u001cj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002`\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bW\u0010<\u001a\u0004\bX\u0010T\"\u0004\bY\u0010VR(\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020Z0>8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010@\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\"\u0010_\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b_\u00100\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR\"\u0010d\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bd\u00100\u001a\u0004\be\u0010a\"\u0004\bf\u0010cR\"\u0010g\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bg\u00100\u001a\u0004\bh\u0010a\"\u0004\bi\u0010cR$\u0010\"\u001a\u0004\u0018\u00010!8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010j\u001a\u0004\bk\u0010l\"\u0004\bm\u0010n¨\u0006s"}, d2 = {"Lcom/visioncritical/touchpointkit/utils/TouchPointActivity;", "", "", "screenName", "componentName", "Lcom/visioncritical/touchpointkit/models/TouchPointTrigger;", "getTriggerForScreenComponent", "trigger", "", "isTriggerIncluded", "Lkotlin/Pair;", "getDistUrlForScreenComponent", "", "syncTriggersData", "cacheActivity", "urlString", "preload", "aString", "getStringResourceByName", "getBooleanResourceByName", "(Ljava/lang/String;)Ljava/lang/Boolean;", "displayTrigger", "Landroid/view/View;", "parent", "Landroid/content/Context;", "context", "initialize", "", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "screenComponents", "visitor", "configure", "Lcom/visioncritical/touchpointkit/utils/TouchPointActivityInterface;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "openActivityForScreenComponent", "shouldShowActivity", "getUUID", "getVisitorId", "distUrl", "setActivitySeen", "isActivitySeen", "alwaysShow", "cacheActivityForUrl", "Landroid/webkit/WebView;", "webView", "openActivityForUrl", "uuid", "Ljava/lang/String;", "visitorId", "isWaitingForTriggersResponse", "Z", "currentScreen", "Landroid/content/SharedPreferences;", "prefs", "Landroid/content/SharedPreferences;", "Landroid/content/Context;", "activityContext", ParserUtil.TAG_QUERY_PARAM_NAME, "webviews", "Ljava/util/HashMap;", "activityCachedDict", "", "triggers", "Ljava/util/List;", "Ljava/util/TimerTask;", "popupEvents", "disableApiFilter", "getDisableApiFilter", "()Z", "setDisableApiFilter", "(Z)V", "enableDebugLogs", "getEnableDebugLogs", "setEnableDebugLogs", "disableAllLogs", "getDisableAllLogs", "setDisableAllLogs", "disableCaching", "getDisableCaching", "setDisableCaching", "isNetworkAvailable", "setNetworkAvailable", "getVisitor", "()Ljava/util/HashMap;", "setVisitor", "(Ljava/util/HashMap;)V", CognitoServiceConstants.CHLG_PARAM_USER_ATTRIBUTE, "getUserAttributes", "setUserAttributes", "Lcom/visioncritical/touchpointkit/models/TouchPointScreenComponent;", "getScreenComponents", "()Ljava/util/List;", "setScreenComponents", "(Ljava/util/List;)V", "apiKey", "getApiKey", "()Ljava/lang/String;", "setApiKey", "(Ljava/lang/String;)V", "apiSecret", "getApiSecret", "setApiSecret", "podName", "getPodName", "setPodName", "Lcom/visioncritical/touchpointkit/utils/TouchPointActivityInterface;", "getListener", "()Lcom/visioncritical/touchpointkit/utils/TouchPointActivityInterface;", "setListener", "(Lcom/visioncritical/touchpointkit/utils/TouchPointActivityInterface;)V", "<init>", "()V", "Companion", "HOLDER", "touchpointkit_release"}, k = 1, mv = {1, 6, 0})
public final class TouchPointActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<TouchPointActivity> shared$delegate = LazyKt__LazyJVMKt.lazy(new Function0<TouchPointActivity>() { // from class: com.visioncritical.touchpointkit.utils.TouchPointActivity$Companion$shared$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TouchPointActivity invoke() {
            return TouchPointActivity.HOLDER.INSTANCE.getINSTANCE();
        }
    });
    private HashMap<String, Boolean> activityCachedDict;
    private Context activityContext;
    private String apiKey;
    private String apiSecret;
    private Context context;
    private String currentScreen;
    private boolean disableAllLogs;
    private boolean disableApiFilter;
    private boolean disableCaching;
    private boolean enableDebugLogs;
    private boolean isNetworkAvailable;
    private boolean isWaitingForTriggersResponse;
    private TouchPointActivityInterface listener;
    private String podName;
    private HashMap<String, TimerTask> popupEvents;
    private SharedPreferences prefs;
    private List<TouchPointScreenComponent> screenComponents;
    private final String tag;
    private List<TouchPointTrigger> triggers;
    private HashMap<String, String> userAttributes;
    private String uuid;
    private HashMap<String, Object> visitor;
    private String visitorId;
    private HashMap<String, WebView> webviews;

    public /* synthetic */ TouchPointActivity(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private TouchPointActivity() {
        this.uuid = "";
        this.visitorId = "";
        this.currentScreen = "";
        this.tag = "TouchPointActivity";
        this.webviews = new HashMap<>();
        this.activityCachedDict = new HashMap<>();
        this.triggers = new ArrayList();
        this.popupEvents = new HashMap<>();
        this.isNetworkAvailable = true;
        this.visitor = new HashMap<>();
        this.userAttributes = new HashMap<>();
        this.screenComponents = new ArrayList();
        this.apiKey = "";
        this.apiSecret = "";
        this.podName = "";
    }

    public final boolean getDisableApiFilter() {
        return this.disableApiFilter;
    }

    public final boolean getEnableDebugLogs() {
        return this.enableDebugLogs;
    }

    public final boolean getDisableAllLogs() {
        return this.disableAllLogs;
    }

    public final boolean getDisableCaching() {
        return this.disableCaching;
    }

    /* JADX INFO: renamed from: isNetworkAvailable, reason: from getter */
    public final boolean getIsNetworkAvailable() {
        return this.isNetworkAvailable;
    }

    public final HashMap<String, Object> getVisitor() {
        return this.visitor;
    }

    public final HashMap<String, String> getUserAttributes() {
        return this.userAttributes;
    }

    public final List<TouchPointScreenComponent> getScreenComponents() {
        return this.screenComponents;
    }

    public final String getApiKey() {
        return this.apiKey;
    }

    public final String getApiSecret() {
        return this.apiSecret;
    }

    public final String getPodName() {
        return this.podName;
    }

    public final TouchPointActivityInterface getListener() {
        return this.listener;
    }

    public final void setListener(TouchPointActivityInterface touchPointActivityInterface) {
        this.listener = touchPointActivityInterface;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: TouchPointActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004¢\u0006\n\n\u0002\b\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/visioncritical/touchpointkit/utils/TouchPointActivity$HOLDER;", "", "()V", "INSTANCE", "Lcom/visioncritical/touchpointkit/utils/TouchPointActivity;", "getINSTANCE", "()Lcom/visioncritical/touchpointkit/utils/TouchPointActivity;", "INSTANCE$1", "touchpointkit_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class HOLDER {
        public static final HOLDER INSTANCE = new HOLDER();

        /* JADX INFO: renamed from: INSTANCE$1, reason: from kotlin metadata */
        private static final TouchPointActivity INSTANCE = new TouchPointActivity(null);

        private HOLDER() {
        }

        public final TouchPointActivity getINSTANCE() {
            return INSTANCE;
        }
    }

    /* JADX INFO: compiled from: TouchPointActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/visioncritical/touchpointkit/utils/TouchPointActivity$Companion;", "", "()V", "shared", "Lcom/visioncritical/touchpointkit/utils/TouchPointActivity;", "getShared", "()Lcom/visioncritical/touchpointkit/utils/TouchPointActivity;", "shared$delegate", "Lkotlin/Lazy;", "touchpointkit_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TouchPointActivity getShared() {
            return (TouchPointActivity) TouchPointActivity.shared$delegate.getValue();
        }
    }

    public final void initialize(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        String stringResourceByName = getStringResourceByName("touchpoint_api_key");
        if (stringResourceByName == null) {
            stringResourceByName = "";
        }
        String stringResourceByName2 = getStringResourceByName("touchpoint_api_secret");
        if (stringResourceByName2 == null) {
            stringResourceByName2 = "";
        }
        String stringResourceByName3 = getStringResourceByName("touchpoint_pod_name");
        String str = stringResourceByName3 != null ? stringResourceByName3 : "";
        if (!(stringResourceByName.length() == 0)) {
            if (!(stringResourceByName2.length() == 0)) {
                if (!(str.length() == 0)) {
                    this.apiKey = stringResourceByName;
                    this.apiSecret = stringResourceByName2;
                    this.podName = str;
                    Boolean booleanResourceByName = getBooleanResourceByName("touchpoint_disable_api_filter");
                    this.disableApiFilter = booleanResourceByName == null ? false : booleanResourceByName.booleanValue();
                    Boolean booleanResourceByName2 = getBooleanResourceByName("touchpoint_enable_debug_logs");
                    this.enableDebugLogs = booleanResourceByName2 == null ? false : booleanResourceByName2.booleanValue();
                    Boolean booleanResourceByName3 = getBooleanResourceByName("touchpoint_disable_all_logs");
                    this.disableAllLogs = booleanResourceByName3 == null ? false : booleanResourceByName3.booleanValue();
                    Boolean booleanResourceByName4 = getBooleanResourceByName("touchpoint_disable_caching");
                    this.disableCaching = booleanResourceByName4 == null ? false : booleanResourceByName4.booleanValue();
                    this.prefs = context.getSharedPreferences("com.visioncritical.touchpointkit", 0);
                    return;
                }
            }
        }
        TouchPointLogger.INSTANCE.errorLog(this.tag, "Error: 'touchpoint_api_key', 'touchpoint_api_secret' or 'touchpoint_pod_name' is missing in string file.");
    }

    public final void configure(List<? extends HashMap<String, String>> screenComponents, HashMap<String, Object> visitor) {
        Intrinsics.checkNotNullParameter(screenComponents, "screenComponents");
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        int size = screenComponents.size();
        TouchPointScreenComponent[] touchPointScreenComponentArr = new TouchPointScreenComponent[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            touchPointScreenComponentArr[i2] = new TouchPointScreenComponent("", null, 2, null);
        }
        int size2 = screenComponents.size();
        int i3 = 0;
        while (i3 < size2) {
            int i4 = i3 + 1;
            String str = screenComponents.get(i3).get("screenName");
            if (str != null) {
                TouchPointScreenComponent touchPointScreenComponent = new TouchPointScreenComponent(str, null, 2, null);
                if (screenComponents.get(i3).get("componentName") != null) {
                    touchPointScreenComponent.setComponentName(screenComponents.get(i3).get("componentName"));
                }
                touchPointScreenComponentArr[i3] = touchPointScreenComponent;
                i3 = i4;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        Object obj = visitor.get("id");
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        map.put("id", (String) obj);
        HashMap<String, String> map2 = new HashMap<>();
        if (visitor.containsKey(CognitoServiceConstants.CHLG_PARAM_USER_ATTRIBUTE)) {
            Object obj2 = visitor.get(CognitoServiceConstants.CHLG_PARAM_USER_ATTRIBUTE);
            if (obj2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<java.util.HashMap<kotlin.String, kotlin.Any>{ kotlin.collections.TypeAliasesKt.HashMap<kotlin.String, kotlin.Any> }>");
            }
            HashMap[] mapArr = (HashMap[]) obj2;
            if (!(mapArr.length == 0)) {
                int length = mapArr.length;
                while (i < length) {
                    HashMap map3 = mapArr[i];
                    i++;
                    Object obj3 = map3.get("key");
                    if (obj3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    String str2 = (String) obj3;
                    Object obj4 = map3.get("value");
                    if (obj4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                    }
                    map.put(str2, obj4);
                    Object obj5 = map3.get(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                    if (obj5 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    map2.put(str2, (String) obj5);
                }
            }
        }
        this.visitor = map;
        this.userAttributes = map2;
        this.screenComponents = ArraysKt___ArraysKt.toMutableList(touchPointScreenComponentArr);
        syncTriggersData();
    }

    public final void openActivityForScreenComponent(Context context, String screenName, String componentName, TouchPointActivityInterface listener) {
        TouchPointTrigger triggerForScreenComponent;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenName, "screenName");
        if (this.triggers.isEmpty()) {
            TouchPointLogger.INSTANCE.errorLog(this.tag, "Unable to open activity. Reason: Triggers data not available");
            return;
        }
        if (componentName == null) {
            triggerForScreenComponent = getTriggerForScreenComponent$default(this, null, null, 3, null);
            if (triggerForScreenComponent == null) {
                triggerForScreenComponent = getTriggerForScreenComponent(screenName, componentName);
            }
            if (triggerForScreenComponent == null) {
                return;
            }
        } else {
            triggerForScreenComponent = getTriggerForScreenComponent(screenName, componentName);
            if (triggerForScreenComponent == null) {
                return;
            } else {
                new TouchPointRestClient().trackTriggerEvent(context, screenName, componentName, triggerForScreenComponent.getId(), triggerForScreenComponent.getDistributionID(), "CUSTOM_COMPONENT_TRIGGERED");
            }
        }
        this.listener = listener;
        openActivityForUrl$default(this, context, triggerForScreenComponent.getDistributionUrl(), triggerForScreenComponent.getAlwaysShow(), null, 8, null);
    }

    public final boolean shouldShowActivity(String screenName, String componentName) {
        Intrinsics.checkNotNullParameter(screenName, "screenName");
        return (getTriggerForScreenComponent$default(this, null, null, 3, null) == null && getTriggerForScreenComponent(screenName, componentName) == null) ? false : true;
    }

    @SuppressLint({"HardwareIds"})
    public final String getUUID() {
        if (this.uuid.length() == 0) {
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context = null;
            }
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            Intrinsics.checkNotNullExpressionValue(string, "getString(context.conten…olver, Secure.ANDROID_ID)");
            this.uuid = string;
        }
        return this.uuid;
    }

    public final String getVisitorId() {
        Object obj;
        if ((this.visitorId.length() == 0) && (obj = this.visitor.get("id")) != null) {
            this.visitorId = obj.toString();
        }
        return this.visitorId;
    }

    public final void setActivitySeen(String distUrl) {
        Intrinsics.checkNotNullParameter(distUrl, "distUrl");
        SharedPreferences sharedPreferences = this.prefs;
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean(distUrl, true);
        editorEdit.apply();
    }

    static /* synthetic */ TouchPointTrigger getTriggerForScreenComponent$default(TouchPointActivity touchPointActivity, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        return touchPointActivity.getTriggerForScreenComponent(str, str2);
    }

    private final TouchPointTrigger getTriggerForScreenComponent(String screenName, String componentName) {
        String strStringPlus = "screenName: '" + ((Object) screenName) + "' componentName: '" + ((Object) componentName) + '\'';
        if (screenName == null && componentName == null) {
            strStringPlus = Intrinsics.stringPlus(strStringPlus, " (showOnAllScreens: true)");
        }
        if (this.triggers.isEmpty()) {
            return null;
        }
        List<TouchPointTrigger> list = this.triggers;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (isTriggerIncluded((TouchPointTrigger) obj, screenName, componentName)) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            TouchPointLogger.INSTANCE.infoLog(this.tag, Intrinsics.stringPlus("No matching trigger found for ", strStringPlus));
            return null;
        }
        TouchPointLogger.INSTANCE.infoLog(this.tag, "The matching trigger found for " + strStringPlus + " was " + CollectionsKt___CollectionsKt.first((List) arrayList));
        if (((TouchPointTrigger) CollectionsKt___CollectionsKt.first((List) arrayList)).getAlwaysShow()) {
            return (TouchPointTrigger) CollectionsKt___CollectionsKt.first((List) arrayList);
        }
        if (isActivitySeen(((TouchPointTrigger) CollectionsKt___CollectionsKt.first((List) arrayList)).getDistributionUrl())) {
            return null;
        }
        return (TouchPointTrigger) CollectionsKt___CollectionsKt.first((List) arrayList);
    }

    private final boolean isTriggerIncluded(TouchPointTrigger trigger, String screenName, String componentName) {
        if (screenName == null && componentName == null) {
            return trigger.getShowOnAllScreens();
        }
        TouchPointScreenComponent[] screenComponents = trigger.getScreenComponents();
        int length = screenComponents.length;
        boolean z = false;
        int i = 0;
        while (i < length) {
            TouchPointScreenComponent touchPointScreenComponent = screenComponents[i];
            i++;
            if (!z) {
                boolean zAreEqual = Intrinsics.areEqual(touchPointScreenComponent.getScreenName(), screenName);
                if (componentName == null) {
                    if (zAreEqual) {
                        String componentName2 = touchPointScreenComponent.getComponentName();
                        if (componentName2 != null && componentName2.length() == 0) {
                        }
                    }
                } else {
                    z = zAreEqual && Intrinsics.areEqual(touchPointScreenComponent.getComponentName(), componentName);
                }
            }
        }
        return z;
    }

    static /* synthetic */ Pair getDistUrlForScreenComponent$default(TouchPointActivity touchPointActivity, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        return touchPointActivity.getDistUrlForScreenComponent(str, str2);
    }

    private final Pair<String, Boolean> getDistUrlForScreenComponent(String screenName, String componentName) {
        if (this.triggers.isEmpty()) {
            return new Pair<>(null, Boolean.FALSE);
        }
        List<TouchPointTrigger> list = this.triggers;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (isTriggerIncluded((TouchPointTrigger) obj, screenName, componentName)) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            return new Pair<>(null, Boolean.FALSE);
        }
        String distributionUrl = ((TouchPointTrigger) CollectionsKt___CollectionsKt.first((List) arrayList)).getDistributionUrl();
        if (((TouchPointTrigger) CollectionsKt___CollectionsKt.first((List) arrayList)).getAlwaysShow()) {
            return new Pair<>(distributionUrl, Boolean.TRUE);
        }
        if (isActivitySeen(distributionUrl)) {
            return new Pair<>(null, Boolean.FALSE);
        }
        return new Pair<>(distributionUrl, Boolean.FALSE);
    }

    public final boolean isActivitySeen(String distUrl) {
        Intrinsics.checkNotNullParameter(distUrl, "distUrl");
        if (distUrl.length() == 0) {
            TouchPointLogger.INSTANCE.infoLog(this.tag, "Unable to get distribution_url. Reason: empty results or distribution_url value missing");
            return true;
        }
        SharedPreferences sharedPreferences = this.prefs;
        Intrinsics.checkNotNull(sharedPreferences);
        if (!sharedPreferences.getBoolean(distUrl, false)) {
            return false;
        }
        TouchPointLogger.INSTANCE.infoLog(this.tag, "Action disabled. Reason: activity already seen by user");
        return true;
    }

    private final void syncTriggersData() {
        TouchPointRestClient touchPointRestClient = new TouchPointRestClient();
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        touchPointRestClient.getTriggers(context, new Function1<List<TouchPointTrigger>, Unit>() { // from class: com.visioncritical.touchpointkit.utils.TouchPointActivity.syncTriggersData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<TouchPointTrigger> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<TouchPointTrigger> list) {
                if (list != null) {
                    TouchPointLogger.INSTANCE.debugLog(TouchPointActivity.this.tag, Intrinsics.stringPlus("Triggers: ", Integer.valueOf(list.size())));
                    TouchPointActivity.this.triggers = list;
                    if (TouchPointActivity.this.isWaitingForTriggersResponse) {
                        TouchPointActivity.this.isWaitingForTriggersResponse = false;
                        TouchPointActivity touchPointActivity = TouchPointActivity.this;
                        TouchPointActivity.displayTrigger$default(touchPointActivity, touchPointActivity.currentScreen, null, 2, null);
                        TouchPointActivity touchPointActivity2 = TouchPointActivity.this;
                        TouchPointActivity.cacheActivity$default(touchPointActivity2, touchPointActivity2.currentScreen, null, 2, null);
                        return;
                    }
                    return;
                }
                TouchPointActivity.this.triggers = new ArrayList();
                TouchPointLogger.INSTANCE.debugLog(TouchPointActivity.this.tag, "Triggers: null");
            }
        });
    }

    static /* synthetic */ void cacheActivity$default(TouchPointActivity touchPointActivity, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        touchPointActivity.cacheActivity(str, str2);
    }

    private final void cacheActivity(String screenName, String componentName) {
        if (this.disableCaching || this.triggers.isEmpty()) {
            return;
        }
        Pair distUrlForScreenComponent$default = getDistUrlForScreenComponent$default(this, null, null, 3, null);
        String strComponent1 = (String) distUrlForScreenComponent$default.component1();
        boolean zBooleanValue = ((Boolean) distUrlForScreenComponent$default.component2()).booleanValue();
        if (strComponent1 == null) {
            Pair<String, Boolean> distUrlForScreenComponent = getDistUrlForScreenComponent(screenName, componentName);
            strComponent1 = distUrlForScreenComponent.component1();
            zBooleanValue = distUrlForScreenComponent.component2().booleanValue();
        }
        if (strComponent1 != null) {
            if ((strComponent1.length() == 0) || zBooleanValue) {
                return;
            }
            cacheActivityForUrl(strComponent1, zBooleanValue);
        }
    }

    public final void cacheActivityForUrl(String distUrl, boolean alwaysShow) {
        Intrinsics.checkNotNullParameter(distUrl, "distUrl");
        String parameterisedActivityUrlString = new TouchPointCommon().getParameterisedActivityUrlString(distUrl, alwaysShow);
        Boolean bool = this.activityCachedDict.get(parameterisedActivityUrlString);
        if (bool == null || !bool.booleanValue()) {
            preload(parameterisedActivityUrlString);
            this.activityCachedDict.put(parameterisedActivityUrlString, Boolean.TRUE);
        }
    }

    private final void preload(String urlString) {
        if (urlString == null) {
            urlString = "";
        }
        webView(urlString).component1().loadUrl(urlString);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public final Pair<WebView, Boolean> webView(String urlString) {
        if (urlString == null) {
            urlString = "";
        }
        if (this.webviews.get(urlString) != null) {
            WebView webView = this.webviews.get(urlString);
            Intrinsics.checkNotNull(webView);
            Intrinsics.checkNotNullExpressionValue(webView, "webviews[distUrl]!!");
            return new Pair<>(webView, Boolean.TRUE);
        }
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        WebView webView2 = new WebView(context);
        WebSettings settings = webView2.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "view.settings");
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        webView2.setScrollBarStyle(voOSType.VOOSMP_SRC_FFAUDIO_WMA);
        webView2.setScrollbarFadingEnabled(true);
        webView2.setLayerType(2, null);
        this.webviews.put(urlString, webView2);
        webView2.addJavascriptInterface(new TouchPointWebAppInterface(), "mobileSDKCommunication");
        return new Pair<>(webView2, Boolean.FALSE);
    }

    private final String getStringResourceByName(String aString) {
        Context context = this.context;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        String packageName = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
        Context context3 = this.context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context3 = null;
        }
        int identifier = context3.getResources().getIdentifier(aString, "string", packageName);
        if (identifier == 0) {
            return "";
        }
        Context context4 = this.context;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context2 = context4;
        }
        return context2.getString(identifier);
    }

    private final Boolean getBooleanResourceByName(String aString) {
        Context context = this.context;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        String packageName = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
        Context context3 = this.context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context3 = null;
        }
        int identifier = context3.getResources().getIdentifier(aString, "bool", packageName);
        if (identifier == 0) {
            return null;
        }
        Context context4 = this.context;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else {
            context2 = context4;
        }
        return Boolean.valueOf(context2.getResources().getBoolean(identifier));
    }

    static /* synthetic */ void displayTrigger$default(TouchPointActivity touchPointActivity, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        touchPointActivity.displayTrigger(str, str2);
    }

    private final void displayTrigger(String screenName, String componentName) {
        if (this.triggers.isEmpty()) {
            return;
        }
        TouchPointTrigger triggerForScreenComponent$default = getTriggerForScreenComponent$default(this, null, null, 3, null);
        if (triggerForScreenComponent$default == null) {
            triggerForScreenComponent$default = getTriggerForScreenComponent(screenName, componentName);
        }
        TouchPointTrigger touchPointTrigger = triggerForScreenComponent$default;
        if (touchPointTrigger == null) {
            return;
        }
        Context context = this.activityContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityContext");
            context = null;
        }
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity != null) {
            View viewFindViewById = activity.findViewById(R.id.content);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "context.findViewById(android.R.id.content)");
            displayTrigger$default(this, viewFindViewById, touchPointTrigger, screenName, null, 8, null);
            return;
        }
        TouchPointLogger.INSTANCE.errorLog(this.tag, "displayTrigger: Error: required activity context");
    }

    static /* synthetic */ void displayTrigger$default(TouchPointActivity touchPointActivity, View view, TouchPointTrigger touchPointTrigger, String str, String str2, int i, Object obj) {
        if ((i & 8) != 0) {
            str2 = null;
        }
        touchPointActivity.displayTrigger(view, touchPointTrigger, str, str2);
    }

    private final void displayTrigger(View parent, final TouchPointTrigger trigger, final String screenName, final String componentName) {
        if (Intrinsics.areEqual(trigger.getType(), "BANNER")) {
            if (!(trigger.getTextColor().length() == 0)) {
                if (!(trigger.getBackgroundColor().length() == 0)) {
                    final Snackbar snackbarMake = Snackbar.make(parent, trigger.getCaptionText(), -2);
                    Intrinsics.checkNotNullExpressionValue(snackbarMake, "make(parent, trigger.cap…ackbar.LENGTH_INDEFINITE)");
                    snackbarMake.setTextColor(Color.parseColor(trigger.getTextColor()));
                    snackbarMake.getView().setBackgroundColor(Color.parseColor(trigger.getBackgroundColor()));
                    snackbarMake.setActionTextColor(Color.parseColor(trigger.getTextColor()));
                    snackbarMake.setAction("Close", new View.OnClickListener() { // from class: com.visioncritical.touchpointkit.utils.TouchPointActivity$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            TouchPointActivity.m2795displayTrigger$lambda3(this.f$0, trigger, screenName, componentName, view);
                        }
                    }).show();
                    snackbarMake.getView().setOnClickListener(new View.OnClickListener() { // from class: com.visioncritical.touchpointkit.utils.TouchPointActivity$$ExternalSyntheticLambda1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            TouchPointActivity.m2796displayTrigger$lambda4(this.f$0, screenName, componentName, trigger, snackbarMake, view);
                        }
                    });
                    return;
                }
            }
            TouchPointLogger.INSTANCE.errorLog(this.tag, "Trigger of type: BANNER received with backgroundColor: " + trigger.getBackgroundColor() + " and textColor: " + trigger.getTextColor());
            TouchPointRestClient touchPointRestClient = new TouchPointRestClient();
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context = null;
            }
            TouchPointRestClient.trackSentryProxy$default(touchPointRestClient, context, "error", "Trigger of type: BANNER received with backgroundColor: " + trigger.getBackgroundColor() + " and textColor: " + trigger.getTextColor(), false, 8, null);
            return;
        }
        if (Intrinsics.areEqual(trigger.getType(), "POPUP")) {
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() { // from class: com.visioncritical.touchpointkit.utils.TouchPointActivity$displayTrigger$popupEvent$1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    Context context2;
                    TouchPointActivity touchPointActivity = this.this$0;
                    Context context3 = touchPointActivity.activityContext;
                    if (context3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activityContext");
                        context3 = null;
                    }
                    TouchPointActivity.openActivityForUrl$default(touchPointActivity, context3, trigger.getDistributionUrl(), trigger.getAlwaysShow(), null, 8, null);
                    TouchPointRestClient touchPointRestClient2 = new TouchPointRestClient();
                    Context context4 = this.this$0.context;
                    if (context4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context2 = null;
                    } else {
                        context2 = context4;
                    }
                    touchPointRestClient2.trackTriggerEvent(context2, screenName, componentName, trigger.getId(), trigger.getDistributionID(), "POPUP_TRIGGERED");
                }
            };
            this.popupEvents.put(screenName, timerTask);
            timer.schedule(timerTask, trigger.getDelayInMS());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: displayTrigger$lambda-3, reason: not valid java name */
    public static final void m2795displayTrigger$lambda3(TouchPointActivity this$0, TouchPointTrigger trigger, String screenName, String str, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(trigger, "$trigger");
        Intrinsics.checkNotNullParameter(screenName, "$screenName");
        this$0.setActivitySeen(trigger.getDistributionUrl());
        TouchPointRestClient touchPointRestClient = new TouchPointRestClient();
        Context context = this$0.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        touchPointRestClient.trackTriggerEvent(context, screenName, str, trigger.getId(), trigger.getDistributionID(), "BANNER_DISMISSED");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: displayTrigger$lambda-4, reason: not valid java name */
    public static final void m2796displayTrigger$lambda4(TouchPointActivity this$0, String screenName, String str, TouchPointTrigger trigger, Snackbar snack, View view) {
        Context context;
        Context context2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(screenName, "$screenName");
        Intrinsics.checkNotNullParameter(trigger, "$trigger");
        Intrinsics.checkNotNullParameter(snack, "$snack");
        TouchPointRestClient touchPointRestClient = new TouchPointRestClient();
        Context context3 = this$0.context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        } else {
            context = context3;
        }
        touchPointRestClient.trackTriggerEvent(context, screenName, str, trigger.getId(), trigger.getDistributionID(), "BANNER_TRIGGERED");
        Context context4 = this$0.activityContext;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityContext");
            context2 = null;
        } else {
            context2 = context4;
        }
        openActivityForUrl$default(this$0, context2, trigger.getDistributionUrl(), trigger.getAlwaysShow(), null, 8, null);
        snack.dismiss();
    }

    public static /* synthetic */ void openActivityForUrl$default(TouchPointActivity touchPointActivity, Context context, String str, boolean z, TouchPointActivityInterface touchPointActivityInterface, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            touchPointActivityInterface = null;
        }
        touchPointActivity.openActivityForUrl(context, str, z, touchPointActivityInterface);
    }

    public final void openActivityForUrl(Context context, String distUrl, boolean alwaysShow, TouchPointActivityInterface listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(distUrl, "distUrl");
        if (listener != null) {
            try {
                this.listener = listener;
            } catch (Exception e) {
                TouchPointLogger.INSTANCE.errorLog(this.tag, Intrinsics.stringPlus("openWebActivity: Exception: ", e.getLocalizedMessage()));
                return;
            }
        }
        Intent intent = new Intent(context, (Class<?>) TouchPointWebViewActivity.class);
        intent.putExtra("distUrl", distUrl);
        intent.putExtra("alwaysShow", alwaysShow);
        context.startActivity(intent);
    }
}
