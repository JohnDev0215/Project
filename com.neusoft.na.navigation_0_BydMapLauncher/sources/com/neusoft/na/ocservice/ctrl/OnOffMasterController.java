package com.neusoft.na.ocservice.ctrl;

import api.notifications.com.neusoft.onoff.onoffmaster.NotifyStatusChanged;
import api.processors.com.neusoft.onoff.onoffmaster.IOnOffMasterProcessor;
import api.processors.com.neusoft.onoff.onoffmaster.OnOffMasterActorDelegator;
import api.requests.com.neusoft.onoff.onoffmaster.ReqGetStatus;
import api.requests.com.neusoft.onoff.onoffmaster.ReqStart;
import api.requests.com.neusoft.onoff.onoffmaster.ReqStop;
import api.responses.com.neusoft.onoff.onoffmaster.ResGetStatus;
import api.responses.com.neusoft.onoff.onoffmaster.ResStart;
import api.responses.com.neusoft.onoff.onoffmaster.ResStop;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.na.common.ApplicationStatus;
import com.neusoft.na.common.BasicLifeCycleController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java9.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogging;
import rx.Observable;
import rx.functions.Action1;

/* JADX INFO: compiled from: OnOffMasterController.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010\u0012\u001a\u00020\u0016H\u0016J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00102\u0006\u0010\u0012\u001a\u00020\u0019H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/neusoft/na/ocservice/ctrl/OnOffMasterController;", "Lapi/processors/com/neusoft/onoff/onoffmaster/IOnOffMasterProcessor;", "actorServer", "Lcom/neusoft/na/ocservice/ctrl/ActorServer;", "processMap", "", "", "Lcom/neusoft/na/common/BasicLifeCycleController;", "(Lcom/neusoft/na/ocservice/ctrl/ActorServer;Ljava/util/Map;)V", "delegator", "Lapi/processors/com/neusoft/onoff/onoffmaster/OnOffMasterActorDelegator;", "Lcom/neusoft/na/ocservice/ctrl/ProcessInfo;", "getNotifyStatusChanged", "Lcom/neusoft/de/nts/OneCoreTest/interfaces/IAttribute;", "Lapi/notifications/com/neusoft/onoff/onoffmaster/NotifyStatusChanged;", "processReqGetStatus", "Ljava9/util/concurrent/CompletableFuture;", "Lapi/responses/com/neusoft/onoff/onoffmaster/ResGetStatus;", "rq", "Lapi/requests/com/neusoft/onoff/onoffmaster/ReqGetStatus;", "processReqStart", "Lapi/responses/com/neusoft/onoff/onoffmaster/ResStart;", "Lapi/requests/com/neusoft/onoff/onoffmaster/ReqStart;", "processReqStop", "Lapi/responses/com/neusoft/onoff/onoffmaster/ResStop;", "Lapi/requests/com/neusoft/onoff/onoffmaster/ReqStop;", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OnOffMasterController implements IOnOffMasterProcessor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final OnOffMasterActorDelegator delegator;
    private final Map<String, ProcessInfo> processMap;

    public OnOffMasterController(ActorServer actorServer, Map<String, ? extends BasicLifeCycleController> processMap) {
        Intrinsics.checkNotNullParameter(actorServer, "actorServer");
        Intrinsics.checkNotNullParameter(processMap, "processMap");
        this.delegator = new OnOffMasterActorDelegator(actorServer.getRxTransport(), actorServer.getExecutor(), this);
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(processMap.size()));
        Iterator<T> it = processMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), new ProcessInfo((BasicLifeCycleController) entry.getValue(), false));
        }
        this.processMap = linkedHashMap;
        INSTANCE.getLogger().debug("delegator.startProcess");
        this.delegator.startProcess();
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            final ProcessInfo processInfo = (ProcessInfo) entry2.getValue();
            Observable<ApplicationStatus> applicationStatusObservable = processInfo.getLifeCycleController().getApplicationStatusObservable();
            final Function1<ApplicationStatus, Unit> function1 = new Function1<ApplicationStatus, Unit>() { // from class: com.neusoft.na.ocservice.ctrl.OnOffMasterController$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ApplicationStatus applicationStatus) {
                    invoke2(applicationStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ApplicationStatus applicationStatus) {
                    if (applicationStatus.getMStatus() == ApplicationStatus.Status.INITIALIZED && processInfo.getShouldRun()) {
                        processInfo.getLifeCycleController().startApplication();
                    }
                }
            };
            applicationStatusObservable.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.ctrl.OnOffMasterController$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    OnOffMasterController.lambda$2$lambda$1(function1, obj);
                }
            });
        }
    }

    /* JADX INFO: compiled from: OnOffMasterController.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/neusoft/na/ocservice/ctrl/OnOffMasterController$Companion;", "Lmu/KLogging;", "()V", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static final void lambda$2$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // api.processors.com.neusoft.onoff.onoffmaster.IOnOffMasterProcessor
    public CompletableFuture<ResStart> processReqStart(final ReqStart rq) {
        Intrinsics.checkNotNullParameter(rq, "rq");
        ProcessInfo processInfo = this.processMap.get(rq.getArguments().getProcessName());
        if (processInfo != null) {
            BasicLifeCycleController lifeCycleController = processInfo.getLifeCycleController();
            if (lifeCycleController.getApplicationStatus().getMStatus() == ApplicationStatus.Status.UNINITIALIZED) {
                processInfo.setShouldRun(true);
                lifeCycleController.init();
            }
            lifeCycleController.startApplication();
        } else {
            INSTANCE.getLogger().warn(new Function0<Object>() { // from class: com.neusoft.na.ocservice.ctrl.OnOffMasterController.processReqStart.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "unknown process: '" + rq.getArguments().getProcessName() + '\'';
                }
            });
        }
        CompletableFuture<ResStart> completableFutureCompletedFuture = CompletableFuture.completedFuture(new ResStart());
        Intrinsics.checkNotNullExpressionValue(completableFutureCompletedFuture, "completedFuture(ResStart())");
        return completableFutureCompletedFuture;
    }

    @Override // api.processors.com.neusoft.onoff.onoffmaster.IOnOffMasterProcessor
    public CompletableFuture<ResStop> processReqStop(final ReqStop rq) {
        Intrinsics.checkNotNullParameter(rq, "rq");
        ProcessInfo processInfo = this.processMap.get(rq.getArguments().getProcessName());
        if (processInfo != null) {
            processInfo.setShouldRun(false);
            processInfo.getLifeCycleController().deinit();
        } else {
            INSTANCE.getLogger().warn(new Function0<Object>() { // from class: com.neusoft.na.ocservice.ctrl.OnOffMasterController.processReqStop.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "unknown process: '" + rq.getArguments().getProcessName() + '\'';
                }
            });
        }
        CompletableFuture<ResStop> completableFutureCompletedFuture = CompletableFuture.completedFuture(new ResStop());
        Intrinsics.checkNotNullExpressionValue(completableFutureCompletedFuture, "completedFuture(ResStop())");
        return completableFutureCompletedFuture;
    }

    @Override // api.processors.com.neusoft.onoff.onoffmaster.IOnOffMasterProcessor
    public CompletableFuture<ResGetStatus> processReqGetStatus(ReqGetStatus rq) {
        Intrinsics.checkNotNullParameter(rq, "rq");
        INSTANCE.getLogger().info("processReqGetStatus");
        ResGetStatus resGetStatus = new ResGetStatus();
        ResGetStatus.Arguments arguments = resGetStatus.getArguments();
        Set<Map.Entry<String, ProcessInfo>> setEntrySet = this.processMap.entrySet();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setEntrySet, 10));
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            ProcessInfo processInfo = (ProcessInfo) entry.getValue();
            api.structs.com.neusoft.onoff.onoffmaster.ProcessInfo processInfo2 = new api.structs.com.neusoft.onoff.onoffmaster.ProcessInfo();
            processInfo2.setIsRunning(Boolean.valueOf(processInfo.getLifeCycleController().getApplicationStatus().getMStatus() == ApplicationStatus.Status.RUNNING));
            processInfo2.setProcessName(str);
            arrayList.add(processInfo2);
        }
        arguments.setProcessInfos(arrayList);
        CompletableFuture<ResGetStatus> completableFutureCompletedFuture = CompletableFuture.completedFuture(resGetStatus);
        Intrinsics.checkNotNullExpressionValue(completableFutureCompletedFuture, "completedFuture(result)");
        return completableFutureCompletedFuture;
    }

    @Override // api.processors.com.neusoft.onoff.onoffmaster.IOnOffMasterProcessor
    public IAttribute<NotifyStatusChanged> getNotifyStatusChanged() {
        IAttribute<NotifyStatusChanged> notifyStatusChanged = this.delegator.getNotifyStatusChanged();
        Intrinsics.checkNotNullExpressionValue(notifyStatusChanged, "delegator.notifyStatusChanged");
        return notifyStatusChanged;
    }
}
