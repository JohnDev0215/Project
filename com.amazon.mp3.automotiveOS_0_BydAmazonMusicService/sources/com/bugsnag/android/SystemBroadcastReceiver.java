package com.bugsnag.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.bugsnag.android.internal.ImmutableConfig;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: SystemBroadcastReceiver.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J,\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\tH\u0002J\u0014\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0002J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/bugsnag/android/SystemBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, "Lcom/bugsnag/android/Client;", "logger", "Lcom/bugsnag/android/Logger;", "(Lcom/bugsnag/android/Client;Lcom/bugsnag/android/Logger;)V", "actions", "", "", "Lcom/bugsnag/android/BreadcrumbType;", "getActions", "()Ljava/util/Map;", "addExtrasToMetadata", "", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "meta", "", "", "shortAction", "buildActions", "onReceive", "context", "Landroid/content/Context;", "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class SystemBroadcastReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<String, BreadcrumbType> actions = buildActions();
    private final Client client;
    private final Logger logger;

    @JvmStatic
    public static final void register(Context context, SystemBroadcastReceiver systemBroadcastReceiver, Logger logger) {
        INSTANCE.register(context, systemBroadcastReceiver, logger);
    }

    public SystemBroadcastReceiver(Client client, Logger logger) {
        this.client = client;
        this.logger = logger;
    }

    /* JADX INFO: compiled from: SystemBroadcastReceiver.kt */
    @kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/bugsnag/android/SystemBroadcastReceiver$Companion;", "", "()V", "INTENT_ACTION_KEY", "", "isAndroidKey", "", "actionName", "register", "", "ctx", "Landroid/content/Context;", "receiver", "Lcom/bugsnag/android/SystemBroadcastReceiver;", "logger", "Lcom/bugsnag/android/Logger;", "shortenActionNameIfNeeded", "action", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void register(Context ctx, SystemBroadcastReceiver receiver, Logger logger) {
            if (!receiver.getActions().isEmpty()) {
                IntentFilter intentFilter = new IntentFilter();
                Iterator<T> it = receiver.getActions().keySet().iterator();
                while (it.hasNext()) {
                    intentFilter.addAction((String) it.next());
                }
                ContextExtensionsKt.registerReceiverSafe(ctx, receiver, intentFilter, logger);
            }
        }

        public final boolean isAndroidKey(String actionName) {
            return StringsKt__StringsJVMKt.startsWith$default(actionName, "android.", false, 2, null);
        }

        public final String shortenActionNameIfNeeded(String action) {
            return isAndroidKey(action) ? StringsKt__StringsKt.substringAfterLast$default(action, '.', (String) null, 2, (Object) null) : action;
        }
    }

    public final Map<String, BreadcrumbType> getActions() {
        return this.actions;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            HashMap map = new HashMap();
            String action = intent.getAction();
            if (action != null) {
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(action, "intent.action ?: return");
                String strShortenActionNameIfNeeded = INSTANCE.shortenActionNameIfNeeded(action);
                map.put("Intent Action", action);
                addExtrasToMetadata(intent, map, strShortenActionNameIfNeeded);
                BreadcrumbType breadcrumbType = this.actions.get(action);
                if (breadcrumbType == null) {
                    breadcrumbType = BreadcrumbType.STATE;
                }
                this.client.leaveBreadcrumb(strShortenActionNameIfNeeded, map, breadcrumbType);
            }
        } catch (Exception e) {
            this.logger.w("Failed to leave breadcrumb in SystemBroadcastReceiver: " + e.getMessage());
        }
    }

    private final void addExtrasToMetadata(Intent intent, Map<String, Object> meta, String shortAction) {
        Set<String> setKeySet;
        Bundle extras = intent.getExtras();
        if (extras == null || (setKeySet = extras.keySet()) == null) {
            return;
        }
        for (String key : setKeySet) {
            Object obj = extras.get(key);
            if (obj != null) {
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(obj, "extras[key] ?: return@forEach");
                String string = obj.toString();
                Companion companion = INSTANCE;
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(key, "key");
                if (companion.isAndroidKey(key)) {
                    meta.put("Extra", shortAction + ": " + string);
                } else {
                    meta.put(key, string);
                }
            }
        }
    }

    private final Map<String, BreadcrumbType> buildActions() {
        HashMap map = new HashMap();
        ImmutableConfig config = this.client.getConfig();
        BreadcrumbType breadcrumbType = BreadcrumbType.USER;
        if (!config.shouldDiscardBreadcrumb(breadcrumbType)) {
            map.put("android.appwidget.action.APPWIDGET_DELETED", breadcrumbType);
            map.put("android.appwidget.action.APPWIDGET_DISABLED", breadcrumbType);
            map.put("android.appwidget.action.APPWIDGET_ENABLED", breadcrumbType);
            map.put("android.intent.action.CAMERA_BUTTON", breadcrumbType);
            map.put("android.intent.action.CLOSE_SYSTEM_DIALOGS", breadcrumbType);
            map.put("android.intent.action.DOCK_EVENT", breadcrumbType);
        }
        BreadcrumbType breadcrumbType2 = BreadcrumbType.STATE;
        if (!config.shouldDiscardBreadcrumb(breadcrumbType2)) {
            map.put("android.appwidget.action.APPWIDGET_HOST_RESTORED", breadcrumbType2);
            map.put("android.appwidget.action.APPWIDGET_RESTORED", breadcrumbType2);
            map.put("android.appwidget.action.APPWIDGET_UPDATE", breadcrumbType2);
            map.put("android.appwidget.action.APPWIDGET_UPDATE_OPTIONS", breadcrumbType2);
            map.put("android.intent.action.ACTION_POWER_CONNECTED", breadcrumbType2);
            map.put("android.intent.action.ACTION_POWER_DISCONNECTED", breadcrumbType2);
            map.put("android.intent.action.ACTION_SHUTDOWN", breadcrumbType2);
            map.put("android.intent.action.AIRPLANE_MODE", breadcrumbType2);
            map.put("android.intent.action.BATTERY_LOW", breadcrumbType2);
            map.put("android.intent.action.BATTERY_OKAY", breadcrumbType2);
            map.put("android.intent.action.BOOT_COMPLETED", breadcrumbType2);
            map.put("android.intent.action.CONFIGURATION_CHANGED", breadcrumbType2);
            map.put("android.intent.action.CONTENT_CHANGED", breadcrumbType2);
            map.put("android.intent.action.DATE_CHANGED", breadcrumbType2);
            map.put("android.intent.action.DEVICE_STORAGE_LOW", breadcrumbType2);
            map.put("android.intent.action.DEVICE_STORAGE_OK", breadcrumbType2);
            map.put("android.intent.action.INPUT_METHOD_CHANGED", breadcrumbType2);
            map.put("android.intent.action.LOCALE_CHANGED", breadcrumbType2);
            map.put("android.intent.action.REBOOT", breadcrumbType2);
            map.put("android.intent.action.SCREEN_OFF", breadcrumbType2);
            map.put("android.intent.action.SCREEN_ON", breadcrumbType2);
            map.put("android.intent.action.TIMEZONE_CHANGED", breadcrumbType2);
            map.put("android.intent.action.TIME_SET", breadcrumbType2);
            map.put("android.os.action.DEVICE_IDLE_MODE_CHANGED", breadcrumbType2);
            map.put("android.os.action.POWER_SAVE_MODE_CHANGED", breadcrumbType2);
        }
        BreadcrumbType breadcrumbType3 = BreadcrumbType.NAVIGATION;
        if (!config.shouldDiscardBreadcrumb(breadcrumbType3)) {
            map.put("android.intent.action.DREAMING_STARTED", breadcrumbType3);
            map.put("android.intent.action.DREAMING_STOPPED", breadcrumbType3);
        }
        return map;
    }
}
