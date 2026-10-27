package com.amazon.avod.perf;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.connectivity.ConnectionChangeListener;
import com.amazon.avod.connectivity.DetailedNetworkInfo;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.http.MetricEventListener;
import com.amazon.avod.http.internal.UrlServiceNameProvider;
import com.amazon.avod.listeners.SetListenerProxy;
import com.amazon.avod.threading.ProfiledLock;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.amazon.avod.util.Preconditions2;
import com.amazon.bolthttp.EventListener;
import com.amazon.bolthttp.Request;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class MetricsDebugger extends EventListener {
    private static final MetricEventListener.ServiceNameProvider URL_SERVICE_NAME_PROVIDER = new UrlServiceNameProvider();
    private final InitializationLatch mInitLatch;
    private final DebugMetricsListenerProxy mListenerProxy;
    private final ProfiledLock mLock;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final Deque<DebugNetworkInfo> mNetworkStateWithTimingInformation;
    private final Map<Request<?>, Query> mQueries;
    private boolean mShouldEnableMetrics;

    public interface DebugMetricsListener {
        void onDebugMetricsChanged(@Nonnull ImmutableList<Query> immutableList, @Nonnull ImmutableList<DebugNetworkInfo> immutableList2);
    }

    /* synthetic */ MetricsDebugger(AnonymousClass1 anonymousClass1) {
        this();
    }

    private static final class SingletonHolder {
        private static volatile MetricsDebugger sInstance = new MetricsDebugger((AnonymousClass1) null);

        private SingletonHolder() {
        }
    }

    public static MetricsDebugger getInstance() {
        return SingletonHolder.sInstance;
    }

    private MetricsDebugger() {
        this(NetworkConnectionManager.getInstance());
    }

    MetricsDebugger(NetworkConnectionManager networkConnectionManager) {
        this.mLock = new ProfiledLock(this, ProfiledLock.ThreadUsage.ANY);
        this.mQueries = Collections.synchronizedMap(Maps.newLinkedHashMap());
        this.mNetworkStateWithTimingInformation = Lists.newLinkedList();
        this.mListenerProxy = new DebugMetricsListenerProxy(null);
        this.mInitLatch = new InitializationLatch(this);
        this.mShouldEnableMetrics = false;
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
    }

    public void initialize(boolean z) {
        this.mInitLatch.start(30L, TimeUnit.SECONDS);
        this.mShouldEnableMetrics = z;
        if (z) {
            this.mNetworkStateWithTimingInformation.addFirst(new DebugNetworkInfo(this.mNetworkConnectionManager.getNetworkInfo(), System.currentTimeMillis()));
            this.mNetworkConnectionManager.registerListener((ConnectionChangeListener) new DebugConnectionChangeListener(this, null));
        }
        this.mInitLatch.complete();
    }

    @Override // com.amazon.bolthttp.EventListener
    public void onNetworkEvent(@Nonnull EventListener.NetworkEvent networkEvent, @Nonnull Request<?> request) {
        Query query;
        this.mInitLatch.checkInitialized();
        if (this.mShouldEnableMetrics) {
            String apiShortName = URL_SERVICE_NAME_PROVIDER.getApiShortName(request);
            if (apiShortName == null || apiShortName.isEmpty()) {
                DLog.warnf("Unable to parse api name for: %s", request.getUrl());
                return;
            }
            ProfiledLock.Key keyLock = this.mLock.lock("OnNetworkEvent");
            try {
                int i = AnonymousClass1.$SwitchMap$com$amazon$bolthttp$EventListener$NetworkEvent$State[networkEvent.getState().ordinal()];
                if (i == 1) {
                    Query query2 = this.mQueries.get(request);
                    if (query2 != null) {
                        query2.setStatus(Query.Status.PENDING_AWAITING_PARSE);
                        query2.setNetworkTimeMillis(networkEvent.getAttemptDurationMillis());
                    }
                } else if (i == 2 && (query = this.mQueries.get(request)) != null) {
                    query.incrementAttempt();
                }
                cleanUpQueriesAndNotifyListeners();
            } finally {
                this.mLock.unlock(keyLock);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.perf.MetricsDebugger$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$bolthttp$EventListener$NetworkEvent$State;

        static {
            int[] iArr = new int[EventListener.NetworkEvent.State.values().length];
            $SwitchMap$com$amazon$bolthttp$EventListener$NetworkEvent$State = iArr;
            try {
                iArr[EventListener.NetworkEvent.State.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$bolthttp$EventListener$NetworkEvent$State[EventListener.NetworkEvent.State.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.amazon.bolthttp.EventListener
    public void onExecutionStart(@Nonnull Request<?> request) {
        this.mInitLatch.checkInitialized();
        if (this.mShouldEnableMetrics) {
            ProfiledLock.Key keyLock = this.mLock.lock("OnExecutionStart");
            try {
                addQuery(request, URL_SERVICE_NAME_PROVIDER.getApiShortName(request));
                cleanUpQueriesAndNotifyListeners();
            } finally {
                this.mLock.unlock(keyLock);
            }
        }
    }

    @Override // com.amazon.bolthttp.EventListener
    public void onResponseHandlerEvent(@Nonnull EventListener.ResponseHandlerEvent responseHandlerEvent, @Nonnull Request<?> request) {
        this.mInitLatch.checkInitialized();
        if (this.mShouldEnableMetrics) {
            String apiShortName = URL_SERVICE_NAME_PROVIDER.getApiShortName(request);
            if (apiShortName == null || apiShortName.isEmpty()) {
                DLog.warnf("Unable to parse api name for: %s", request.getUrl());
                return;
            }
            ProfiledLock.Key keyLock = this.mLock.lock("OnResponseHandlerEvent");
            try {
                Query query = this.mQueries.get(request);
                if (query == null) {
                    return;
                }
                query.setStatus(responseHandlerEvent.getState() == EventListener.ResponseHandlerEvent.State.SUCCESS ? Query.Status.SUCCEEDED : Query.Status.FAILED_PARSE);
                query.setParseTimeMillis(responseHandlerEvent.getAttemptDurationMillis());
                if (query.getStatus().isFinal()) {
                    DLog.logf("Query completed: %s", query);
                }
                cleanUpQueriesAndNotifyListeners();
            } finally {
                this.mLock.unlock(keyLock);
            }
        }
    }

    @Override // com.amazon.bolthttp.EventListener
    public void onExecutionCompleteEvent(@Nonnull EventListener.ExecutionCompleteEvent executionCompleteEvent, @Nonnull Request<?> request) {
        this.mInitLatch.checkInitialized();
        if (this.mShouldEnableMetrics) {
            ProfiledLock.Key keyLock = this.mLock.lock("OnExecutionCompleteEvent");
            try {
                if (executionCompleteEvent.getState() == EventListener.ExecutionCompleteEvent.State.FAILURE) {
                    Query query = this.mQueries.get(request);
                    if (query == null) {
                        return;
                    }
                    query.setStatus(Query.Status.FAILED_NETWORK);
                    query.setNetworkTimeMillis(executionCompleteEvent.getExecutionDurationMillis());
                }
                cleanUpQueriesAndNotifyListeners();
            } finally {
                this.mLock.unlock(keyLock);
            }
        }
    }

    private void removeCompletedQueries(int i) {
        this.mLock.checkLocked("RemoveCompletedQueries");
        UnmodifiableIterator it = ImmutableSet.copyOf((Collection) this.mQueries.entrySet()).iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            boolean zIsFinal = ((Query) entry.getValue()).getStatus().isFinal();
            boolean z = ((Query) entry.getValue()).mEndTimeMillis + TimeUnit.SECONDS.toMillis(10L) <= System.currentTimeMillis();
            if (!(zIsFinal && z) && i2 < i) {
                i2++;
            } else {
                this.mQueries.remove(entry.getKey());
            }
        }
    }

    @Nonnull
    private Query addQuery(@Nonnull Request<?> request, @Nonnull String str) {
        Query query = new Query(str, null);
        this.mQueries.put(request, query);
        return query;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectionChange(DetailedNetworkInfo detailedNetworkInfo) {
        ProfiledLock.Key keyLock = this.mLock.lock("ConnectionChange");
        try {
            DebugNetworkInfo debugNetworkInfoPeekFirst = this.mNetworkStateWithTimingInformation.peekFirst();
            if (debugNetworkInfoPeekFirst == null || !detailedNetworkInfo.equals(debugNetworkInfoPeekFirst.getDetailedNetworkInfo())) {
                this.mNetworkStateWithTimingInformation.addFirst(new DebugNetworkInfo(detailedNetworkInfo, System.currentTimeMillis()));
                if (this.mNetworkStateWithTimingInformation.size() > 5) {
                    this.mNetworkStateWithTimingInformation.pollLast();
                }
                notifyListenersLocked();
            }
        } finally {
            this.mLock.unlock(keyLock);
        }
    }

    private void cleanUpQueriesAndNotifyListeners() {
        removeCompletedQueries(20);
        notifyListenersLocked();
    }

    private void notifyListenersLocked() {
        this.mLock.checkLocked("NotifyListeners");
        this.mListenerProxy.onDebugMetricsChanged(ImmutableList.copyOf((Collection) this.mQueries.values()), ImmutableList.copyOf((Collection) this.mNetworkStateWithTimingInformation));
    }

    @ThreadSafe
    public static class Query {
        private int mAttempt;
        private final String mDescription;
        private long mEndTimeMillis;
        private long mNetworkTimeMillis;
        private long mParseTimeMillis;
        private final long mStartTimeMillis;
        private Status mStatus;

        /* synthetic */ Query(String str, AnonymousClass1 anonymousClass1) {
            this(str);
        }

        public enum Status {
            PENDING_AWAITING_CALL(false),
            PENDING_AWAITING_PARSE(false),
            FAILED_NETWORK(true),
            FAILED_PARSE(true),
            SUCCEEDED(true);

            private final boolean mIsFinal;

            Status(boolean z) {
                this.mIsFinal = z;
            }

            public boolean isFinal() {
                return this.mIsFinal;
            }
        }

        private Query(@Nonnull String str) {
            this.mAttempt = 1;
            this.mStatus = Status.PENDING_AWAITING_CALL;
            this.mDescription = str;
            this.mStartTimeMillis = System.currentTimeMillis();
        }

        @Nonnull
        public String getDescription() {
            return this.mDescription;
        }

        public long getDurationMillis() {
            return this.mEndTimeMillis - this.mStartTimeMillis;
        }

        public void setNetworkTimeMillis(long j) {
            this.mNetworkTimeMillis = j;
        }

        public long getNetworkTimeMillis() {
            return this.mNetworkTimeMillis;
        }

        public void setParseTimeMillis(long j) {
            this.mParseTimeMillis = j;
        }

        public long getParseTimeMillis() {
            return this.mParseTimeMillis;
        }

        public void incrementAttempt() {
            this.mAttempt++;
        }

        public int getAttempt() {
            return this.mAttempt;
        }

        public void setStatus(Status status) {
            this.mStatus = status;
            if (status.isFinal()) {
                this.mEndTimeMillis = System.currentTimeMillis();
            }
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, getStatus()).add(MediaTrack.ROLE_DESCRIPTION, getDescription()).add("attempt", getAttempt()).add("duration (ms)", getDurationMillis()).add("network time (ms)", getNetworkTimeMillis()).add("parse time (ms)", getParseTimeMillis()).toString();
        }
    }

    public static class DebugNetworkInfo {
        private final DetailedNetworkInfo mNetworkInfo;
        private final long mTimestamp;

        DebugNetworkInfo(@Nonnull DetailedNetworkInfo detailedNetworkInfo, @Nonnegative long j) {
            this.mNetworkInfo = (DetailedNetworkInfo) Preconditions.checkNotNull(detailedNetworkInfo, "detailedNetworkInfo");
            this.mTimestamp = Preconditions2.checkNonNegative(j, TetheredMessageKey.timestamp);
        }

        @Nonnull
        public DetailedNetworkInfo getDetailedNetworkInfo() {
            return this.mNetworkInfo;
        }
    }

    private class DebugConnectionChangeListener extends ConnectionChangeListener {
        private DebugConnectionChangeListener() {
        }

        /* synthetic */ DebugConnectionChangeListener(MetricsDebugger metricsDebugger, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.amazon.avod.connectivity.ConnectivityChangeListener
        public void onConnectionChange(DetailedNetworkInfo detailedNetworkInfo, DetailedNetworkInfo detailedNetworkInfo2) {
            MetricsDebugger.this.onConnectionChange(detailedNetworkInfo2);
        }
    }

    private static class DebugMetricsListenerProxy extends SetListenerProxy<DebugMetricsListener> implements DebugMetricsListener {
        private DebugMetricsListenerProxy() {
        }

        /* synthetic */ DebugMetricsListenerProxy(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.amazon.avod.perf.MetricsDebugger.DebugMetricsListener
        public void onDebugMetricsChanged(@Nonnull ImmutableList<Query> immutableList, @Nonnull ImmutableList<DebugNetworkInfo> immutableList2) {
            Iterator<DebugMetricsListener> it = getListeners().iterator();
            while (it.hasNext()) {
                it.next().onDebugMetricsChanged(immutableList, immutableList2);
            }
        }
    }
}
