package com.android.server.telecom.callfiltering;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.telecom.Log;
import android.telecom.Logging.Runnable;
import com.android.server.telecom.Call;
import com.android.server.telecom.LoggedHandlerExecutor;
import com.android.server.telecom.TelecomSystem;
import com.android.server.telecom.Timeouts;
import com.android.server.telecom.callfiltering.CallFilteringResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public class IncomingCallFilterGraph {
    public static final CallFilteringResult DEFAULT_RESULT;
    private final Call mCall;
    private Context mContext;
    private CallFilteringResult mCurrentResult;
    private CallFilter mDummyComplete;
    private List<CallFilter> mFiltersList = new ArrayList();
    private boolean mFinished;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private final CallFilterResultCallback mListener;
    private final TelecomSystem.SyncRoot mLock;
    private Timeouts.Adapter mTimeoutsAdapter;

    static {
        CallFilteringResult.Builder builder = new CallFilteringResult.Builder();
        builder.setShouldAllowCall(true);
        builder.setShouldReject(false);
        builder.setShouldAddToCallLog(true);
        builder.setShouldShowNotification(true);
        DEFAULT_RESULT = builder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    class PostFilterTask {
        private final CallFilter mFilter;

        public PostFilterTask(CallFilter callFilter) {
            this.mFilter = callFilter;
        }

        public CallFilteringResult whenDone(CallFilteringResult callFilteringResult) {
            Log.i("IncomingCallFilterGraph", "Filter %s done, result: %s.", new Object[]{this.mFilter, callFilteringResult});
            CallFilter callFilter = this.mFilter;
            callFilter.result = callFilteringResult;
            for (CallFilter callFilter2 : callFilter.getFollowings()) {
                if (callFilter2.decrementAndGetIndegree() == 0) {
                    IncomingCallFilterGraph.this.scheduleFilter(callFilter2);
                }
            }
            if (this.mFilter.equals(IncomingCallFilterGraph.this.mDummyComplete)) {
                synchronized (IncomingCallFilterGraph.this.mLock) {
                    IncomingCallFilterGraph.this.mFinished = true;
                    IncomingCallFilterGraph.this.mListener.onCallFilteringComplete(IncomingCallFilterGraph.this.mCall, callFilteringResult);
                    Log.addEvent(IncomingCallFilterGraph.this.mCall, "FILTERING_COMPLETED", callFilteringResult);
                }
                IncomingCallFilterGraph.this.mHandlerThread.quit();
            }
            return callFilteringResult;
        }
    }

    public IncomingCallFilterGraph(Call call, CallFilterResultCallback callFilterResultCallback, Context context, Timeouts.Adapter adapter, TelecomSystem.SyncRoot syncRoot) {
        this.mListener = callFilterResultCallback;
        this.mCall = call;
        HandlerThread handlerThread = new HandlerThread("IncomingCallFilterGraph");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mLock = syncRoot;
        this.mFinished = false;
        this.mContext = context;
        this.mTimeoutsAdapter = adapter;
        this.mCurrentResult = DEFAULT_RESULT;
    }

    public void addFilter(CallFilter callFilter) {
        this.mFiltersList.add(callFilter);
    }

    public void performFiltering() {
        Log.addEvent(this.mCall, "FILTERING_INITIATED");
        CallFilter callFilter = new CallFilter();
        this.mDummyComplete = new CallFilter();
        Iterator<CallFilter> it = this.mFiltersList.iterator();
        while (it.hasNext()) {
            addEdge(callFilter, it.next());
        }
        Iterator<CallFilter> it2 = this.mFiltersList.iterator();
        while (it2.hasNext()) {
            addEdge(it2.next(), this.mDummyComplete);
        }
        addEdge(callFilter, this.mDummyComplete);
        scheduleFilter(callFilter);
        this.mHandler.postDelayed(new Runnable("ICFG.pF", this.mLock) { // from class: com.android.server.telecom.callfiltering.IncomingCallFilterGraph.1
            public void loggedRun() {
                if (!IncomingCallFilterGraph.this.mFinished) {
                    Log.i(this, "Graph timed out when performing filtering.", new Object[0]);
                    Log.addEvent(IncomingCallFilterGraph.this.mCall, "FILTERING_TIMED_OUT");
                    IncomingCallFilterGraph.this.mListener.onCallFilteringComplete(IncomingCallFilterGraph.this.mCall, IncomingCallFilterGraph.this.mCurrentResult);
                    IncomingCallFilterGraph.this.mFinished = true;
                    IncomingCallFilterGraph.this.mHandlerThread.quit();
                }
                for (CallFilter callFilter2 : IncomingCallFilterGraph.this.mFiltersList) {
                    if (callFilter2 instanceof CallScreeningServiceFilter) {
                        ((CallScreeningServiceFilter) callFilter2).unbindCallScreeningService();
                    }
                }
            }
        }.prepare(), this.mTimeoutsAdapter.getCallScreeningTimeoutMillis(this.mContext.getContentResolver()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleFilter(final CallFilter callFilter) {
        CallFilteringResult.Builder builder = new CallFilteringResult.Builder();
        builder.setShouldAllowCall(true);
        builder.setShouldReject(false);
        builder.setShouldSilence(false);
        builder.setShouldAddToCallLog(true);
        builder.setShouldShowNotification(true);
        CallFilteringResult callFilteringResultBuild = builder.build();
        Iterator<CallFilter> it = callFilter.getDependencies().iterator();
        while (it.hasNext()) {
            callFilteringResultBuild = callFilteringResultBuild.combine(it.next().getResult());
        }
        this.mCurrentResult = callFilteringResultBuild;
        CompletableFuture completableFutureCompletedFuture = CompletableFuture.completedFuture(callFilteringResultBuild);
        final PostFilterTask postFilterTask = new PostFilterTask(callFilter);
        Objects.requireNonNull(callFilter);
        completableFutureCompletedFuture.thenComposeAsync(new Function() { // from class: com.android.server.telecom.callfiltering.-$$Lambda$5WP2hs73pXofbr2etBFDd-DfRqY
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return callFilter.startFilterLookup((CallFilteringResult) obj);
            }
        }, (Executor) new LoggedHandlerExecutor(this.mHandler, "ICFG.sF", null)).thenApplyAsync(new Function() { // from class: com.android.server.telecom.callfiltering.-$$Lambda$cpuCQsNIaqLuhb9nbB5TPWVVRDQ
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                CallFilteringResult callFilteringResult = (CallFilteringResult) obj;
                postFilterTask.whenDone(callFilteringResult);
                return callFilteringResult;
            }
        }, (Executor) new LoggedHandlerExecutor(this.mHandler, "ICFG.sF", null));
        Log.i("IncomingCallFilterGraph", "Filter %s scheduled.", new Object[]{callFilter});
    }

    public static void addEdge(CallFilter callFilter, CallFilter callFilter2) {
        callFilter.addFollowings(callFilter2);
        callFilter2.addDependency(callFilter);
    }

    public HandlerThread getHandlerThread() {
        return this.mHandlerThread;
    }
}
