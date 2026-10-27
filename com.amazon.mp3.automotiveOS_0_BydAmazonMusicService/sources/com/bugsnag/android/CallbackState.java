package com.bugsnag.android;

import androidx.core.app.NotificationCompat;
import com.bugsnag.android.internal.InternalMetrics;
import com.bugsnag.android.internal.InternalMetricsNoop;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: CallbackState.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0080\b\u0018\u0000 02\u00020\u0001:\u00010BG\u0012\u000e\b\u0002\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001f\u0012\u000e\b\u0002\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u001f\u0012\u000e\b\u0002\u0010)\u001a\b\u0012\u0004\u0012\u00020(0\u001f\u0012\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020+0\u001f¢\u0006\u0004\b.\u0010/J\u0014\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0002J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006J\u0016\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fJ\u0016\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\fJ\u0016\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\fJ\u0016\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fJ\u001c\u0010\u0016\u001a\u00020\u000e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00172\u0006\u0010\r\u001a\u00020\fJ\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\u001c\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0016\u0010\u001d\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u001d\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001f8\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u001d\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u001f8\u0006¢\u0006\f\n\u0004\b&\u0010\"\u001a\u0004\b'\u0010$R\u001d\u0010)\u001a\b\u0012\u0004\u0012\u00020(0\u001f8\u0006¢\u0006\f\n\u0004\b)\u0010\"\u001a\u0004\b*\u0010$R\u001d\u0010,\u001a\b\u0012\u0004\u0012\u00020+0\u001f8\u0006¢\u0006\f\n\u0004\b,\u0010\"\u001a\u0004\b-\u0010$¨\u00061"}, d2 = {"Lcom/bugsnag/android/CallbackState;", "", "", "", "", "getCallbackCounts", "Lcom/bugsnag/android/internal/InternalMetrics;", "metrics", "", "setInternalMetrics", "Lcom/bugsnag/android/Event;", NotificationCompat.CATEGORY_EVENT, "Lcom/bugsnag/android/Logger;", "logger", "", "runOnErrorTasks", "Lcom/bugsnag/android/Breadcrumb;", "breadcrumb", "runOnBreadcrumbTasks", "Lcom/bugsnag/android/Session;", "session", "runOnSessionTasks", "runOnSendTasks", "Lkotlin/Function0;", "eventSource", "toString", "hashCode", "other", "equals", "internalMetrics", "Lcom/bugsnag/android/internal/InternalMetrics;", "", "Lcom/bugsnag/android/OnErrorCallback;", "onErrorTasks", "Ljava/util/Collection;", "getOnErrorTasks", "()Ljava/util/Collection;", "Lcom/bugsnag/android/OnBreadcrumbCallback;", "onBreadcrumbTasks", "getOnBreadcrumbTasks", "Lcom/bugsnag/android/OnSessionCallback;", "onSessionTasks", "getOnSessionTasks", "Lcom/bugsnag/android/OnSendCallback;", "onSendTasks", "getOnSendTasks", "<init>", "(Ljava/util/Collection;Ljava/util/Collection;Ljava/util/Collection;Ljava/util/Collection;)V", "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final /* data */ class CallbackState {
    private InternalMetrics internalMetrics;
    private final Collection<OnBreadcrumbCallback> onBreadcrumbTasks;
    private final Collection<OnErrorCallback> onErrorTasks;
    private final Collection<OnSendCallback> onSendTasks;
    private final Collection<OnSessionCallback> onSessionTasks;

    public CallbackState() {
        this(null, null, null, null, 15, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CallbackState)) {
            return false;
        }
        CallbackState callbackState = (CallbackState) other;
        return kotlin.jvm.internal.Intrinsics.areEqual(this.onErrorTasks, callbackState.onErrorTasks) && kotlin.jvm.internal.Intrinsics.areEqual(this.onBreadcrumbTasks, callbackState.onBreadcrumbTasks) && kotlin.jvm.internal.Intrinsics.areEqual(this.onSessionTasks, callbackState.onSessionTasks) && kotlin.jvm.internal.Intrinsics.areEqual(this.onSendTasks, callbackState.onSendTasks);
    }

    public int hashCode() {
        Collection<OnErrorCallback> collection = this.onErrorTasks;
        int iHashCode = (collection != null ? collection.hashCode() : 0) * 31;
        Collection<OnBreadcrumbCallback> collection2 = this.onBreadcrumbTasks;
        int iHashCode2 = (iHashCode + (collection2 != null ? collection2.hashCode() : 0)) * 31;
        Collection<OnSessionCallback> collection3 = this.onSessionTasks;
        int iHashCode3 = (iHashCode2 + (collection3 != null ? collection3.hashCode() : 0)) * 31;
        Collection<OnSendCallback> collection4 = this.onSendTasks;
        return iHashCode3 + (collection4 != null ? collection4.hashCode() : 0);
    }

    public String toString() {
        return "CallbackState(onErrorTasks=" + this.onErrorTasks + ", onBreadcrumbTasks=" + this.onBreadcrumbTasks + ", onSessionTasks=" + this.onSessionTasks + ", onSendTasks=" + this.onSendTasks + ")";
    }

    public CallbackState(Collection<OnErrorCallback> collection, Collection<OnBreadcrumbCallback> collection2, Collection<OnSessionCallback> collection3, Collection<OnSendCallback> collection4) {
        this.onErrorTasks = collection;
        this.onBreadcrumbTasks = collection2;
        this.onSessionTasks = collection3;
        this.onSendTasks = collection4;
        this.internalMetrics = new InternalMetricsNoop();
    }

    public /* synthetic */ CallbackState(Collection collection, Collection collection2, Collection collection3, Collection collection4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new CopyOnWriteArrayList() : collection, (i & 2) != 0 ? new CopyOnWriteArrayList() : collection2, (i & 4) != 0 ? new CopyOnWriteArrayList() : collection3, (i & 8) != 0 ? new CopyOnWriteArrayList() : collection4);
    }

    public final void setInternalMetrics(InternalMetrics metrics) {
        this.internalMetrics = metrics;
        metrics.setCallbackCounts(getCallbackCounts());
    }

    public final boolean runOnErrorTasks(Event event, Logger logger) {
        if (this.onErrorTasks.isEmpty()) {
            return true;
        }
        Iterator<T> it = this.onErrorTasks.iterator();
        while (it.hasNext()) {
            try {
            } catch (Throwable th) {
                logger.w("OnBreadcrumbCallback threw an Exception", th);
            }
            if (!((OnErrorCallback) it.next()).onError(event)) {
                return false;
            }
        }
        return true;
    }

    public final boolean runOnBreadcrumbTasks(Breadcrumb breadcrumb, Logger logger) {
        if (this.onBreadcrumbTasks.isEmpty()) {
            return true;
        }
        Iterator<T> it = this.onBreadcrumbTasks.iterator();
        while (it.hasNext()) {
            try {
            } catch (Throwable th) {
                logger.w("OnBreadcrumbCallback threw an Exception", th);
            }
            if (!((OnBreadcrumbCallback) it.next()).onBreadcrumb(breadcrumb)) {
                return false;
            }
        }
        return true;
    }

    public final boolean runOnSessionTasks(Session session, Logger logger) {
        if (this.onSessionTasks.isEmpty()) {
            return true;
        }
        Iterator<T> it = this.onSessionTasks.iterator();
        while (it.hasNext()) {
            try {
            } catch (Throwable th) {
                logger.w("OnSessionCallback threw an Exception", th);
            }
            if (!((OnSessionCallback) it.next()).onSession(session)) {
                return false;
            }
        }
        return true;
    }

    public final boolean runOnSendTasks(Event event, Logger logger) {
        Iterator<T> it = this.onSendTasks.iterator();
        while (it.hasNext()) {
            try {
            } catch (Throwable th) {
                logger.w("OnSendCallback threw an Exception", th);
            }
            if (!((OnSendCallback) it.next()).onSend(event)) {
                return false;
            }
        }
        return true;
    }

    public final boolean runOnSendTasks(Function0<? extends Event> eventSource, Logger logger) {
        if (this.onSendTasks.isEmpty()) {
            return true;
        }
        return runOnSendTasks(eventSource.invoke(), logger);
    }

    private final Map<String, Integer> getCallbackCounts() {
        HashMap map = new HashMap();
        if (this.onBreadcrumbTasks.size() > 0) {
            map.put("onBreadcrumb", Integer.valueOf(this.onBreadcrumbTasks.size()));
        }
        if (this.onErrorTasks.size() > 0) {
            map.put("onError", Integer.valueOf(this.onErrorTasks.size()));
        }
        if (this.onSendTasks.size() > 0) {
            map.put("onSendError", Integer.valueOf(this.onSendTasks.size()));
        }
        if (this.onSessionTasks.size() > 0) {
            map.put("onSession", Integer.valueOf(this.onSessionTasks.size()));
        }
        return map;
    }
}
