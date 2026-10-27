package com.amazon.music.featuregating.weblabs;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.platform.data.WeblabTreatment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WeblabManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 02\u00020\u0001:\u00010B?\u0012\u0006\u0010'\u001a\u00020&\u0012\u0006\u0010(\u001a\u00020\n\u0012\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060)\u0012\u0006\u0010+\u001a\u00020\b\u0012\u0006\u0010-\u001a\u00020,\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b.\u0010/J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0002J*\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\u001a\u0010\u0012\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000ej\u0004\u0018\u0001`\u0011J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\bJ\u0016\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\fJ\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0017\u001a\u00020\nR\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u001d\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060!8\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%¨\u00061"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabManager;", "", "", "weblabName", "Lcom/amazon/music/featuregating/weblabs/WeblabTreatmentSnapshot;", "getTreatmentSnapshot", "Lcom/amazon/music/featuregating/weblabs/WeblabDescriptor;", "getRegisteredWeblab", "Lcom/amazon/music/featuregating/weblabs/WeblabCustomerInfo;", "data", "", "updateClientSessionParameters", "Lcom/amazon/music/platform/data/WeblabTreatment;", "treatment", "Lkotlin/Function1;", "Lcom/amazon/music/featuregating/weblabs/WeblabTriggerResult;", "", "Lcom/amazon/music/featuregating/weblabs/WeblabTriggerResultCallback;", "triggerResultCallback", "treatmentWithoutTriggering", "refreshClient", "lockWeblab", "unlockWeblab", "isCurrentThreadMain", "Lcom/amazon/music/featuregating/weblabs/WeblabMetrics;", "metrics", "Lcom/amazon/music/featuregating/weblabs/WeblabMetrics;", "Lcom/amazon/music/featuregating/weblabs/WeblabClient;", SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, "Lcom/amazon/music/featuregating/weblabs/WeblabClient;", "Ljava/util/concurrent/ExecutorService;", "executorService", "Ljava/util/concurrent/ExecutorService;", "", "registeredWeblabs", "Ljava/util/List;", "getRegisteredWeblabs", "()Ljava/util/List;", "Landroid/content/Context;", "context", "isAuthenticated", "", "weblabs", "customerInfo", "Lcom/amazon/music/featuregating/weblabs/WeblabClientInfo;", "clientInfo", "<init>", "(Landroid/content/Context;ZLjava/util/List;Lcom/amazon/music/featuregating/weblabs/WeblabCustomerInfo;Lcom/amazon/music/featuregating/weblabs/WeblabClientInfo;Lcom/amazon/music/featuregating/weblabs/WeblabMetrics;)V", "Companion", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final class WeblabManager {
    private final WeblabClient client;
    private final ExecutorService executorService;
    private final WeblabMetrics metrics;
    private final List<WeblabDescriptor> registeredWeblabs;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Handler backgroundHandler = m2018backgroundHandler$lambda4();
    private static final String TAG = WeblabManager.class.getSimpleName();

    public WeblabManager(Context context, boolean z, List<WeblabDescriptor> weblabs, WeblabCustomerInfo customerInfo, WeblabClientInfo clientInfo, WeblabMetrics metrics) throws NoWeblabManagerAvailable {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(weblabs, "weblabs");
        Intrinsics.checkNotNullParameter(customerInfo, "customerInfo");
        Intrinsics.checkNotNullParameter(clientInfo, "clientInfo");
        Intrinsics.checkNotNullParameter(metrics, "metrics");
        this.metrics = metrics;
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        this.executorService = executorServiceNewSingleThreadExecutor;
        ArrayList arrayList = new ArrayList();
        this.registeredWeblabs = arrayList;
        try {
            this.client = new DefaultWeblabClient(context, z, weblabs, customerInfo, clientInfo, metrics);
            arrayList.addAll(weblabs);
        } catch (Exception e) {
            WeblabMetrics weblabMetrics = this.metrics;
            String message = e.getMessage();
            weblabMetrics.clientInitFailed(message == null ? "" : message);
            throw new NoWeblabManagerAvailable(e);
        }
    }

    public /* synthetic */ WeblabManager(Context context, boolean z, List list, WeblabCustomerInfo weblabCustomerInfo, WeblabClientInfo weblabClientInfo, WeblabMetrics weblabMetrics, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, z, list, weblabCustomerInfo, weblabClientInfo, (i & 32) != 0 ? new WeblabMetrics() : weblabMetrics);
    }

    /* JADX INFO: compiled from: WeblabManager.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabManager$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "backgroundHandler", "Landroid/os/Handler;", "getBackgroundHandler", "()Landroid/os/Handler;", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Handler getBackgroundHandler() {
            return WeblabManager.backgroundHandler;
        }
    }

    /* JADX INFO: renamed from: backgroundHandler$lambda-4, reason: not valid java name */
    private static final Handler m2018backgroundHandler$lambda4() {
        HandlerThread handlerThread = new HandlerThread("BackgroundRecordTriggerThread", 10);
        handlerThread.start();
        return new Handler(handlerThread.getLooper());
    }

    public final List<WeblabDescriptor> getRegisteredWeblabs() {
        return this.registeredWeblabs;
    }

    private final WeblabTreatmentSnapshot getTreatmentSnapshot(String weblabName) {
        WeblabTreatmentSnapshot weblabTreatmentSnapshotTreatmentSnapshot = this.client.treatmentSnapshot(weblabName);
        if (weblabTreatmentSnapshotTreatmentSnapshot != null) {
            return weblabTreatmentSnapshotTreatmentSnapshot;
        }
        this.metrics.noTreatmentSnapshotReturned(weblabName);
        return null;
    }

    public final WeblabTreatment treatment(String weblabName) {
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        return treatment(weblabName, null);
    }

    public final WeblabTreatment treatment(String weblabName, Function1<? super WeblabTriggerResult, Unit> triggerResultCallback) throws Exception {
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        WeblabDescriptor registeredWeblab = getRegisteredWeblab(weblabName);
        WeblabTreatmentSnapshot treatmentSnapshot = getTreatmentSnapshot(weblabName);
        if (treatmentSnapshot == null) {
            Log.e(TAG, "treatment: no treatment snapshot returned, weblab=" + weblabName + ". Will return default treatment.");
            return registeredWeblab.getDefaultTreatment();
        }
        WeblabTreatment treatmentAndTrigger = treatmentSnapshot.getTreatmentAndTrigger(triggerResultCallback);
        if (treatmentAndTrigger != null) {
            return treatmentAndTrigger;
        }
        this.metrics.noTreatmentReturned(weblabName);
        return registeredWeblab.getDefaultTreatment();
    }

    public final WeblabTreatment treatmentWithoutTriggering(String weblabName) throws Exception {
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        WeblabDescriptor registeredWeblab = getRegisteredWeblab(weblabName);
        WeblabTreatmentSnapshot treatmentSnapshot = getTreatmentSnapshot(weblabName);
        if (treatmentSnapshot == null) {
            Log.e(TAG, "treatmentWithoutTriggering: no treatment snapshot returned, weblab=" + weblabName + ". Will return default treatment.");
            return registeredWeblab.getDefaultTreatment();
        }
        WeblabTreatment treatment = treatmentSnapshot.getTreatment();
        if (treatment != null) {
            return treatment;
        }
        this.metrics.noTreatmentReturned(weblabName);
        return registeredWeblab.getDefaultTreatment();
    }

    private final WeblabDescriptor getRegisteredWeblab(String weblabName) {
        Object next;
        Iterator<T> it = this.registeredWeblabs.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((WeblabDescriptor) next).getName(), weblabName)) {
                break;
            }
        }
        WeblabDescriptor weblabDescriptor = (WeblabDescriptor) next;
        if (weblabDescriptor != null) {
            return weblabDescriptor;
        }
        this.metrics.trackUnregisteredWeblab(weblabName);
        throw new RuntimeException("Provided weblab " + weblabName + " must be registered prior to calling this API");
    }

    private final boolean updateClientSessionParameters(WeblabCustomerInfo data) {
        if (data.equals(this.client.getCustomerInfo())) {
            return false;
        }
        this.client.setCustomerInfo(data);
        return true;
    }

    public final void refreshClient(WeblabCustomerInfo data) throws Exception {
        Intrinsics.checkNotNullParameter(data, "data");
        if (isCurrentThreadMain()) {
            throw new IllegalThreadStateException("Must not invoke from main thread");
        }
        if (updateClientSessionParameters(data)) {
            this.client.triggerUpdate(false);
        }
    }

    public final void lockWeblab(String weblabName, WeblabTreatment treatment) {
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        Intrinsics.checkNotNullParameter(treatment, "treatment");
        this.client.lockWeblab(weblabName, treatment);
    }

    public final void unlockWeblab(String weblabName) {
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        this.client.unlockWeblab(weblabName);
    }

    public final boolean isCurrentThreadMain() {
        return Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper());
    }
}
