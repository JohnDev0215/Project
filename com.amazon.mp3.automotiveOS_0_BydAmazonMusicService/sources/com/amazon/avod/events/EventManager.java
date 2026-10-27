package com.amazon.avod.events;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.GuardedBy;
import com.amazon.avod.events.perf.EventManagerProfiler;
import com.amazon.avod.http.internal.TokenKey;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class EventManager {
    private static final Runnable NOOP_RUNNABLE = new Runnable() { // from class: com.amazon.avod.events.EventManager.1
        @Override // java.lang.Runnable
        public void run() {
        }
    };
    private final Set<Long> mCheckedOutEventIds;
    private final CompositeEventModelFactory mCompositeFactory;
    private Context mContext;
    private final InitializationLatch mDependenciesLatch;
    private EventPersistance mEventPersistence;

    @GuardedBy("mStartInitializationLock")
    private boolean mHasInitializationStarted;
    private final InitializationLatch mInitializationLatch;
    private final ConcurrentHashMap<TokenKey, String> mInvalidatedTokenKeys;
    private final PersistenceFactory mPersistenceFactory;
    private final ExecutorService mProcessingExecutor;
    private final EventManagerProfiler mProfiler;
    private final ExecutorService mQueueingExecutor;
    private final Object mStartInitializationLock;

    private static class Holder {

        @SuppressLint({"StaticFieldLeak"})
        private static volatile EventManager sInstance = new EventManager();

        private Holder() {
        }
    }

    @Nonnull
    public static EventManager getInstance() {
        return Holder.sInstance;
    }

    private EventManager() {
        CompositeEventModelFactory compositeEventModelFactory = new CompositeEventModelFactory();
        ExecutorBuilder executorBuilderAllowCoreThreadExpiry = ExecutorBuilder.newBuilderFor(EventManager.class, "QueueingThread").withFixedThreadPoolSize(1).allowCoreThreadExpiry();
        Profiler.TraceLevel traceLevel = Profiler.TraceLevel.DEBUG;
        this(compositeEventModelFactory, executorBuilderAllowCoreThreadExpiry.withProfilerTraceLevel(traceLevel).buildTestFriendly(), ExecutorBuilder.newBuilderFor(EventManager.class, "ProcessingThread").withFixedThreadPoolSize(1).allowCoreThreadExpiry().withProfilerTraceLevel(traceLevel).build(), new PersistenceFactory(), EventManagerProfiler.getInstance());
    }

    EventManager(CompositeEventModelFactory compositeEventModelFactory, ExecutorService executorService, ExecutorService executorService2, PersistenceFactory persistenceFactory, EventManagerProfiler eventManagerProfiler) {
        this.mCheckedOutEventIds = Sets.newHashSet();
        this.mStartInitializationLock = new Object();
        this.mHasInitializationStarted = false;
        this.mDependenciesLatch = new InitializationLatch(String.format(Locale.US, "%s:%s", getClass().getSimpleName(), "Dependencies"));
        this.mInitializationLatch = new InitializationLatch(this);
        this.mInvalidatedTokenKeys = new ConcurrentHashMap<>();
        this.mCompositeFactory = compositeEventModelFactory;
        this.mQueueingExecutor = executorService;
        this.mProcessingExecutor = executorService2;
        this.mPersistenceFactory = persistenceFactory;
        this.mProfiler = eventManagerProfiler;
    }

    public void setInitializationDependencies(@Nonnull Context context) {
        this.mDependenciesLatch.start(30L, TimeUnit.SECONDS);
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mDependenciesLatch.complete();
    }

    public void initialize() {
        this.mInitializationLatch.start(120L, TimeUnit.SECONDS);
        this.mEventPersistence = this.mPersistenceFactory.newPersistence(this.mContext, this.mCompositeFactory);
        this.mInitializationLatch.complete();
    }

    public void waitOnInitializationUninterruptibly() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
    }

    public void registerEventType(@Nonnull EventType eventType, @Nonnull EventModelFactory eventModelFactory) {
        Preconditions.checkNotNull(eventType);
        Preconditions.checkNotNull(eventModelFactory);
        this.mCompositeFactory.registerEventType(eventType, eventModelFactory);
    }

    public void queueEventAsync(@Nonnull EventData eventData) {
        Preconditions.checkNotNull(eventData);
        final Event eventCreateEvent = this.mCompositeFactory.createEvent(eventData);
        Preconditions.checkState(eventCreateEvent != null, "Event type %s is not recognized", eventData.getType());
        this.mQueueingExecutor.execute(new Runnable() { // from class: com.amazon.avod.events.EventManager.2
            @Override // java.lang.Runnable
            public void run() {
                EventManager.this.mInitializationLatch.waitOnInitializationUninterruptibly();
                EventManager.this.queueEventInternal(eventCreateEvent);
            }
        });
    }

    public synchronized void cleanUpEvents() {
        this.mInitializationLatch.checkInitialized();
        this.mEventPersistence.cleanUpEvents();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queueEventInternal(Event event) {
        if (isInvalidated(event)) {
            DLog.warnf("EventManger: Discarding request to queue event because it is restricted. %s event %s (id = %s)", event.getType(), event.getName(), Long.valueOf(event.getId()));
        } else {
            this.mProfiler.onEventQueued(event, event.queue(this.mEventPersistence));
        }
    }

    private boolean isInvalidated(Event event) {
        TokenKey tokenKey = event.getTokenKey();
        return tokenKey != null && this.mInvalidatedTokenKeys.containsKey(tokenKey);
    }

    static class PersistenceFactory {
        PersistenceFactory() {
        }

        public EventPersistance newPersistence(Context context, CompositeEventModelFactory compositeEventModelFactory) {
            return new DBEventPersistance(context, compositeEventModelFactory);
        }
    }
}
