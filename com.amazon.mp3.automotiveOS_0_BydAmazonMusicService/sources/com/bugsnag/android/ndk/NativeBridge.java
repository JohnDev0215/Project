package com.bugsnag.android.ndk;

import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.bugsnag.android.Logger;
import com.bugsnag.android.NativeInterface;
import com.bugsnag.android.StateEvent;
import com.bugsnag.android.internal.BackgroundTaskService;
import com.bugsnag.android.internal.StateObserver;
import com.bugsnag.android.internal.TaskType;
import java.io.File;
import java.io.FileFilter;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.reflect.KDeclarationContainer;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: NativeBridge.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u001c\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J)\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0086 J\u001b\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0019\u001a\u0004\u0018\u00010\u0013H\u0086 J\t\u0010\u001a\u001a\u00020\u0011H\u0086 J!\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\bH\u0086 J!\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020 H\u0086 J!\u0010!\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0013H\u0086 J!\u0010\"\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0013H\u0086 J\t\u0010#\u001a\u00020\u0011H\u0086 J\u0011\u0010$\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086 J\t\u0010%\u001a\u00020\u0011H\u0086 J\u0011\u0010&\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0013H\u0086 J\b\u0010'\u001a\u00020\u0011H\u0002J\u0011\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u0013H\u0086 J\u0017\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020,\u0018\u00010+H\u0086 J\u0017\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\b\u0018\u00010+H\u0086 J\t\u0010.\u001a\u00020/H\u0086 J\u0010\u00100\u001a\u00020\u00112\u0006\u00101\u001a\u000202H\u0002J\u0010\u00103\u001a\u00020\u00112\u0006\u00101\u001a\u000204H\u0002J\u001d\u00105\u001a\u00020\u00112\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020,0+H\u0086 JI\u00107\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u00132\u0006\u0010:\u001a\u00020\u00132\u0006\u0010;\u001a\u00020,2\u0006\u0010<\u001a\u00020\b2\u0006\u0010=\u001a\u00020,2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010>\u001a\u00020,H\u0086 J\u0012\u0010?\u001a\u00020\b2\b\u0010@\u001a\u0004\u0018\u00010\u0017H\u0002J\u0010\u0010A\u001a\u00020\u00132\u0006\u0010B\u001a\u00020\u0013H\u0002J,\u0010C\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00170+2\u0014\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00170+H\u0002J\u0011\u0010D\u001a\u00020\u00112\u0006\u0010E\u001a\u00020\u0013H\u0086 J\u0011\u0010F\u001a\u00020\u00112\u0006\u0010E\u001a\u00020\u0013H\u0086 J\u0010\u0010G\u001a\u00020\u00112\u0006\u0010H\u001a\u00020IH\u0016J\t\u0010J\u001a\u00020\u0011H\u0086 J\t\u0010K\u001a\u00020\u0011H\u0086 J\u0019\u0010L\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u0013H\u0086 J\u0011\u0010M\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\bH\u0086 J\u0011\u0010O\u001a\u00020\u00112\u0006\u0010P\u001a\u00020\u0013H\u0086 J)\u0010Q\u001a\u00020\u00112\u0006\u0010R\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010S\u001a\u00020,2\u0006\u0010T\u001a\u00020,H\u0086 J\u0011\u0010U\u001a\u00020\u00112\u0006\u0010V\u001a\u00020\u0013H\u0086 J\u0019\u0010W\u001a\u00020\u00112\u0006\u0010X\u001a\u00020\b2\u0006\u0010Y\u001a\u00020\u0013H\u0086 J\u0011\u0010Z\u001a\u00020\u00112\u0006\u0010[\u001a\u00020\bH\u0086 J\u0011\u0010\\\u001a\u00020\u00112\u0006\u0010;\u001a\u00020,H\u0086 J\u0019\u0010]\u001a\u00020\u00112\u0006\u0010^\u001a\u00020\b2\u0006\u0010_\u001a\u00020\u0013H\u0086 J\u0011\u0010`\u001a\u00020\u00112\u0006\u0010a\u001a\u00020\u0013H\u0086 J\u0011\u0010b\u001a\u00020\u00112\u0006\u0010^\u001a\u00020\u0013H\u0086 J\u0011\u0010c\u001a\u00020\u00112\u0006\u0010^\u001a\u00020\u0013H\u0086 J\u0011\u0010d\u001a\u00020\u00112\u0006\u0010^\u001a\u00020\u0013H\u0086 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006e"}, d2 = {"Lcom/bugsnag/android/ndk/NativeBridge;", "Lcom/bugsnag/android/internal/StateObserver;", "bgTaskService", "Lcom/bugsnag/android/internal/BackgroundTaskService;", "(Lcom/bugsnag/android/internal/BackgroundTaskService;)V", "installed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "is32bit", "", "()Z", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "logger", "Lcom/bugsnag/android/Logger;", "reportDirectory", "Ljava/io/File;", "addBreadcrumb", "", "name", "", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, TetheredMessageKey.timestamp, "metadata", "", "addFeatureFlag", "variant", "addHandledEvent", "addMetadataBoolean", "tab", "key", "value", "addMetadataDouble", "", "addMetadataOpaque", "addMetadataString", "addUnhandledEvent", "clearFeatureFlag", "clearFeatureFlags", "clearMetadataTab", "deliverPendingReports", "deliverReportAtPath", "filePath", "getCurrentCallbackSetCounts", "", "", "getCurrentNativeApiCallUsage", "getSignalUnwindStackFunction", "", "handleAddMetadata", "arg", "Lcom/bugsnag/android/StateEvent$AddMetadata;", "handleInstallMessage", "Lcom/bugsnag/android/StateEvent$Install;", "initCallbackCounts", "counts", "install", "apiKey", "reportingDirectory", "lastRunInfoPath", "consecutiveLaunchCrashes", "autoDetectNdkCrashes", "apiLevel", "threadSendPolicy", "isInvalidMessage", NotificationCompat.CATEGORY_MESSAGE, "makeSafe", "text", "makeSafeMetadata", "notifyAddCallback", "callback", "notifyRemoveCallback", "onStateChange", NotificationCompat.CATEGORY_EVENT, "Lcom/bugsnag/android/StateEvent;", "pausedSession", "refreshSymbolTable", "removeMetadata", "setInternalMetricsEnabled", "enabled", "setStaticJsonData", "data", "startedSession", "sessionID", "handledCount", "unhandledCount", "updateContext", "context", "updateInForeground", "inForeground", "activityName", "updateIsLaunching", "isLaunching", "updateLastRunInfo", "updateLowMemory", "newValue", "memoryTrimLevelDescription", "updateOrientation", "orientation", "updateUserEmail", "updateUserId", "updateUserName", "bugsnag-plugin-android-ndk_release"}, k = 1, mv = {1, 4, 2})
public final class NativeBridge implements StateObserver {
    private final BackgroundTaskService bgTaskService;
    private final Logger logger;
    private final File reportDirectory;
    private final ReentrantLock lock = new ReentrantLock();
    private final AtomicBoolean installed = new AtomicBoolean(false);

    public final native void addBreadcrumb(String name, String type, String timestamp, Object metadata);

    public final native void addFeatureFlag(String name, String variant);

    public final native void addHandledEvent();

    public final native void addMetadataBoolean(String tab, String key, boolean value);

    public final native void addMetadataDouble(String tab, String key, double value);

    public final native void addMetadataOpaque(String tab, String key, String value);

    public final native void addMetadataString(String tab, String key, String value);

    public final native void addUnhandledEvent();

    public final native void clearFeatureFlag(String name);

    public final native void clearFeatureFlags();

    public final native void clearMetadataTab(String tab);

    public final native void deliverReportAtPath(String filePath);

    public final native Map<String, Integer> getCurrentCallbackSetCounts();

    public final native Map<String, Boolean> getCurrentNativeApiCallUsage();

    public final native long getSignalUnwindStackFunction();

    public final native void initCallbackCounts(Map<String, Integer> counts);

    public final native void install(String apiKey, String reportingDirectory, String lastRunInfoPath, int consecutiveLaunchCrashes, boolean autoDetectNdkCrashes, int apiLevel, boolean is32bit, int threadSendPolicy);

    public final native void notifyAddCallback(String callback);

    public final native void notifyRemoveCallback(String callback);

    public final native void pausedSession();

    public final native void refreshSymbolTable();

    public final native void removeMetadata(String tab, String key);

    public final native void setInternalMetricsEnabled(boolean enabled);

    public final native void setStaticJsonData(String data);

    public final native void startedSession(String sessionID, String key, int handledCount, int unhandledCount);

    public final native void updateContext(String context);

    public final native void updateInForeground(boolean inForeground, String activityName);

    public final native void updateIsLaunching(boolean isLaunching);

    public final native void updateLastRunInfo(int consecutiveLaunchCrashes);

    public final native void updateLowMemory(boolean newValue, String memoryTrimLevelDescription);

    public final native void updateOrientation(String orientation);

    public final native void updateUserEmail(String newValue);

    public final native void updateUserId(String newValue);

    public final native void updateUserName(String newValue);

    /* JADX INFO: renamed from: com.bugsnag.android.ndk.NativeBridge$onStateChange$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NativeBridge.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 4, 2})
    static final /* synthetic */ class C04641 extends FunctionReference implements Function0<Unit> {
        C04641(NativeBridge nativeBridge) {
            super(0, nativeBridge);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "refreshSymbolTable";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(NativeBridge.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "refreshSymbolTable()V";
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((NativeBridge) this.receiver).refreshSymbolTable();
        }
    }

    public NativeBridge(BackgroundTaskService backgroundTaskService) {
        this.bgTaskService = backgroundTaskService;
        File nativeReportPath = NativeInterface.getNativeReportPath();
        Intrinsics.checkExpressionValueIsNotNull(nativeReportPath, "NativeInterface.getNativeReportPath()");
        this.reportDirectory = nativeReportPath;
        Logger logger = NativeInterface.getLogger();
        Intrinsics.checkExpressionValueIsNotNull(logger, "NativeInterface.getLogger()");
        this.logger = logger;
    }

    private final boolean is32bit() {
        String[] cpuAbi = NativeInterface.getCpuAbi();
        Intrinsics.checkExpressionValueIsNotNull(cpuAbi, "NativeInterface.getCpuAbi()");
        int length = cpuAbi.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String it = cpuAbi[i];
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            if (StringsKt__StringsKt.contains$default((CharSequence) it, (CharSequence) "64", false, 2, (Object) null)) {
                z = true;
                break;
            }
            i++;
        }
        return !z;
    }

    @Override // com.bugsnag.android.internal.StateObserver
    public void onStateChange(StateEvent event) {
        if (isInvalidMessage(event)) {
            return;
        }
        if (event instanceof StateEvent.Install) {
            handleInstallMessage((StateEvent.Install) event);
            return;
        }
        if (Intrinsics.areEqual(event, StateEvent.DeliverPending.INSTANCE)) {
            deliverPendingReports();
            return;
        }
        if (event instanceof StateEvent.AddMetadata) {
            handleAddMetadata((StateEvent.AddMetadata) event);
            return;
        }
        if (event instanceof StateEvent.ClearMetadataSection) {
            clearMetadataTab(makeSafe(((StateEvent.ClearMetadataSection) event).section));
            return;
        }
        if (event instanceof StateEvent.ClearMetadataValue) {
            StateEvent.ClearMetadataValue clearMetadataValue = (StateEvent.ClearMetadataValue) event;
            String strMakeSafe = makeSafe(clearMetadataValue.section);
            String str = clearMetadataValue.key;
            removeMetadata(strMakeSafe, makeSafe(str != null ? str : ""));
            return;
        }
        if (event instanceof StateEvent.AddBreadcrumb) {
            StateEvent.AddBreadcrumb addBreadcrumb = (StateEvent.AddBreadcrumb) event;
            addBreadcrumb(makeSafe(addBreadcrumb.message), makeSafe(addBreadcrumb.type.getType()), makeSafe(addBreadcrumb.timestamp), makeSafeMetadata(addBreadcrumb.metadata));
            return;
        }
        if (Intrinsics.areEqual(event, StateEvent.NotifyHandled.INSTANCE)) {
            addHandledEvent();
            return;
        }
        if (Intrinsics.areEqual(event, StateEvent.NotifyUnhandled.INSTANCE)) {
            addUnhandledEvent();
            return;
        }
        if (Intrinsics.areEqual(event, StateEvent.PauseSession.INSTANCE)) {
            pausedSession();
            return;
        }
        if (event instanceof StateEvent.StartSession) {
            StateEvent.StartSession startSession = (StateEvent.StartSession) event;
            startedSession(makeSafe(startSession.id), makeSafe(startSession.startedAt), startSession.handledCount, startSession.getUnhandledCount());
            return;
        }
        if (event instanceof StateEvent.UpdateContext) {
            String str2 = ((StateEvent.UpdateContext) event).context;
            updateContext(makeSafe(str2 != null ? str2 : ""));
            return;
        }
        if (event instanceof StateEvent.UpdateInForeground) {
            StateEvent.UpdateInForeground updateInForeground = (StateEvent.UpdateInForeground) event;
            boolean z = updateInForeground.inForeground;
            String contextActivity = updateInForeground.getContextActivity();
            updateInForeground(z, makeSafe(contextActivity != null ? contextActivity : ""));
            return;
        }
        if (event instanceof StateEvent.UpdateLastRunInfo) {
            updateLastRunInfo(((StateEvent.UpdateLastRunInfo) event).consecutiveLaunchCrashes);
            return;
        }
        if (event instanceof StateEvent.UpdateIsLaunching) {
            StateEvent.UpdateIsLaunching updateIsLaunching = (StateEvent.UpdateIsLaunching) event;
            updateIsLaunching(updateIsLaunching.isLaunching);
            if (updateIsLaunching.isLaunching) {
                return;
            }
            BackgroundTaskService backgroundTaskService = this.bgTaskService;
            TaskType taskType = TaskType.DEFAULT;
            final C04641 c04641 = new C04641(this);
            backgroundTaskService.submitTask(taskType, new Runnable() { // from class: com.bugsnag.android.ndk.NativeBridge$sam$java_lang_Runnable$0
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    Intrinsics.checkExpressionValueIsNotNull(c04641.invoke(), "invoke(...)");
                }
            });
            return;
        }
        if (event instanceof StateEvent.UpdateOrientation) {
            String str3 = ((StateEvent.UpdateOrientation) event).orientation;
            updateOrientation(str3 != null ? str3 : "");
            return;
        }
        if (event instanceof StateEvent.UpdateUser) {
            StateEvent.UpdateUser updateUser = (StateEvent.UpdateUser) event;
            String id = updateUser.user.getId();
            if (id == null) {
                id = "";
            }
            updateUserId(makeSafe(id));
            String name = updateUser.user.getName();
            if (name == null) {
                name = "";
            }
            updateUserName(makeSafe(name));
            String email = updateUser.user.getEmail();
            updateUserEmail(makeSafe(email != null ? email : ""));
            return;
        }
        if (event instanceof StateEvent.UpdateMemoryTrimEvent) {
            StateEvent.UpdateMemoryTrimEvent updateMemoryTrimEvent = (StateEvent.UpdateMemoryTrimEvent) event;
            updateLowMemory(updateMemoryTrimEvent.isLowMemory, updateMemoryTrimEvent.memoryTrimLevelDescription);
            return;
        }
        if (event instanceof StateEvent.AddFeatureFlag) {
            StateEvent.AddFeatureFlag addFeatureFlag = (StateEvent.AddFeatureFlag) event;
            String strMakeSafe2 = makeSafe(addFeatureFlag.name);
            String str4 = addFeatureFlag.variant;
            addFeatureFlag(strMakeSafe2, str4 != null ? makeSafe(str4) : null);
            return;
        }
        if (event instanceof StateEvent.ClearFeatureFlag) {
            clearFeatureFlag(makeSafe(((StateEvent.ClearFeatureFlag) event).name));
        } else if (event instanceof StateEvent.ClearFeatureFlags) {
            clearFeatureFlags();
        }
    }

    /* JADX INFO: renamed from: com.bugsnag.android.ndk.NativeBridge$makeSafeMetadata$1, reason: invalid class name */
    /* JADX INFO: compiled from: NativeBridge.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00007\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001J\u0011\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0096\u0001J\u0013\u0010\u0016\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0096\u0001J\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0015\u001a\u00020\u0002H\u0096\u0002J\t\u0010\u0019\u001a\u00020\u0014H\u0096\u0001R&\u0010\u0004\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00060\u0005X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0012\u0010\u000b\u001a\u00020\fX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0010X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, d2 = {"com/bugsnag/android/ndk/NativeBridge$makeSafeMetadata$1", "", "", "", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "containsKey", "", "key", "containsValue", "value", "get", "isEmpty", "bugsnag-plugin-android-ndk_release"}, k = 1, mv = {1, 4, 2})
    public static final class AnonymousClass1 implements Map<String, Object>, KMappedMarker {
        private final /* synthetic */ Map<String, ? extends Object> $$delegate_0;
        final /* synthetic */ Map $metadata;

        @Override // java.util.Map
        public void clear() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public /* synthetic */ Object compute(String str, BiFunction<? super String, ? super Object, ? extends Object> biFunction) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public /* synthetic */ Object computeIfAbsent(String str, Function<? super String, ? extends Object> function) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public /* synthetic */ Object computeIfPresent(String str, BiFunction<? super String, ? super Object, ? extends Object> biFunction) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public boolean containsKey(String key) {
            return this.$$delegate_0.containsKey(key);
        }

        @Override // java.util.Map
        public boolean containsValue(Object value) {
            return this.$$delegate_0.containsValue(value);
        }

        public Set<Map.Entry<String, Object>> getEntries() {
            return this.$$delegate_0.entrySet();
        }

        public Set<String> getKeys() {
            return this.$$delegate_0.keySet();
        }

        public int getSize() {
            return this.$$delegate_0.size();
        }

        public Collection<Object> getValues() {
            return this.$$delegate_0.values();
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return this.$$delegate_0.isEmpty();
        }

        @Override // java.util.Map
        public /* synthetic */ Object merge(String str, Object obj, BiFunction<? super Object, ? super Object, ? extends Object> biFunction) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public /* synthetic */ Object put(String str, Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public void putAll(Map<? extends String, ? extends Object> map) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public /* synthetic */ Object putIfAbsent(String str, Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public Object remove(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public boolean remove(Object obj, Object obj2) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public /* synthetic */ Object replace(String str, Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public /* synthetic */ boolean replace(String str, Object obj, Object obj2) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Map
        public void replaceAll(BiFunction<? super String, ? super Object, ? extends Object> biFunction) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        AnonymousClass1(Map map) {
            this.$metadata = map;
            this.$$delegate_0 = map;
        }

        @Override // java.util.Map
        public final /* bridge */ boolean containsKey(Object obj) {
            if (obj instanceof String) {
                return containsKey((String) obj);
            }
            return false;
        }

        @Override // java.util.Map
        public final /* bridge */ Set<Map.Entry<String, Object>> entrySet() {
            return getEntries();
        }

        @Override // java.util.Map
        public final /* bridge */ Object get(Object obj) {
            if (obj instanceof String) {
                return get((String) obj);
            }
            return null;
        }

        @Override // java.util.Map
        public final /* bridge */ Set<String> keySet() {
            return getKeys();
        }

        @Override // java.util.Map
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.Map
        public final /* bridge */ Collection<Object> values() {
            return getValues();
        }

        public Object get(String key) {
            return OpaqueValue.INSTANCE.makeSafe(this.$metadata.get(key));
        }
    }

    private final Map<String, Object> makeSafeMetadata(Map<String, ? extends Object> metadata) {
        return metadata.isEmpty() ? metadata : new AnonymousClass1(metadata);
    }

    private final boolean isInvalidMessage(Object msg) {
        if (msg == null || !(msg instanceof StateEvent)) {
            return true;
        }
        if (this.installed.get() || (msg instanceof StateEvent.Install)) {
            return false;
        }
        this.logger.w("Received message before INSTALL: " + msg);
        return true;
    }

    private final void deliverPendingReports() {
        final Regex regex = new Regex(".*\\.crash$");
        this.lock.lock();
        try {
            try {
                File file = this.reportDirectory;
                if (file.exists()) {
                    File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.bugsnag.android.ndk.NativeBridge$deliverPendingReports$fileList$1
                        @Override // java.io.FileFilter
                        public final boolean accept(File it) {
                            Regex regex2 = regex;
                            Intrinsics.checkExpressionValueIsNotNull(it, "it");
                            String name = it.getName();
                            Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
                            return regex2.containsMatchIn(name);
                        }
                    });
                    if (fileArrListFiles != null) {
                        for (File file2 : fileArrListFiles) {
                            Intrinsics.checkExpressionValueIsNotNull(file2, "file");
                            String absolutePath = file2.getAbsolutePath();
                            Intrinsics.checkExpressionValueIsNotNull(absolutePath, "file.absolutePath");
                            deliverReportAtPath(absolutePath);
                        }
                    }
                } else {
                    this.logger.w("Payload directory does not exist, cannot read pending reports");
                }
            } catch (Exception e) {
                this.logger.w("Failed to parse/write pending reports: " + e);
            }
        } finally {
            this.lock.unlock();
        }
    }

    private final void handleInstallMessage(StateEvent.Install arg) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.installed.get()) {
                this.logger.w("Received duplicate setup message with arg: " + arg);
            } else {
                String reportPath = new File(this.reportDirectory, UUID.randomUUID() + ".crash").getAbsolutePath();
                String strMakeSafe = makeSafe(arg.apiKey);
                Intrinsics.checkExpressionValueIsNotNull(reportPath, "reportPath");
                install(strMakeSafe, reportPath, makeSafe(arg.lastRunInfoPath), arg.consecutiveLaunchCrashes, arg.autoDetectNdkCrashes, Build.VERSION.SDK_INT, is32bit(), arg.sendThreads.ordinal());
                this.installed.set(true);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    private final void handleAddMetadata(StateEvent.AddMetadata arg) {
        if (arg.key != null) {
            Object objMakeSafe = OpaqueValue.INSTANCE.makeSafe(arg.value);
            if (objMakeSafe instanceof String) {
                String str = arg.section;
                String str2 = arg.key;
                if (str2 == null) {
                    Intrinsics.throwNpe();
                }
                addMetadataString(str, str2, makeSafe((String) objMakeSafe));
                return;
            }
            if (objMakeSafe instanceof Boolean) {
                String str3 = arg.section;
                String str4 = arg.key;
                if (str4 == null) {
                    Intrinsics.throwNpe();
                }
                addMetadataBoolean(str3, str4, ((Boolean) objMakeSafe).booleanValue());
                return;
            }
            if (objMakeSafe instanceof Number) {
                String str5 = arg.section;
                String str6 = arg.key;
                if (str6 == null) {
                    Intrinsics.throwNpe();
                }
                addMetadataDouble(str5, str6, ((Number) objMakeSafe).doubleValue());
                return;
            }
            if (objMakeSafe instanceof OpaqueValue) {
                String str7 = arg.section;
                String str8 = arg.key;
                if (str8 == null) {
                    Intrinsics.throwNpe();
                }
                addMetadataOpaque(str7, str8, ((OpaqueValue) objMakeSafe).getJson());
            }
        }
    }

    private final String makeSafe(String text) {
        Charset charsetDefaultCharset = Charset.defaultCharset();
        Intrinsics.checkExpressionValueIsNotNull(charsetDefaultCharset, "Charset.defaultCharset()");
        if (text == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = text.getBytes(charsetDefaultCharset);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        return new String(bytes, Charsets.UTF_8);
    }
}
