package com.facebook.react.fabric.mounting;

import android.os.SystemClock;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.mounting.mountitems.DispatchCommandMountItem;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.fabric.mounting.mountitems.PreAllocateViewMountItem;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: classes6.dex */
public class MountItemDispatcher {
    private final ItemDispatchListener mItemDispatchListener;
    private final MountingManager mMountingManager;

    @NonNull
    private final ConcurrentLinkedQueue<DispatchCommandMountItem> mViewCommandMountItems = new ConcurrentLinkedQueue<>();

    @NonNull
    private final ConcurrentLinkedQueue<MountItem> mMountItems = new ConcurrentLinkedQueue<>();

    @NonNull
    private final ConcurrentLinkedQueue<PreAllocateViewMountItem> mPreMountItems = new ConcurrentLinkedQueue<>();
    private boolean mInDispatch = false;
    private int mReDispatchCounter = 0;
    private long mBatchedExecutionTime = 0;
    private long mRunStartTime = 0;

    public interface ItemDispatchListener {
        void didDispatchMountItems();
    }

    public MountItemDispatcher(MountingManager mountingManager, ItemDispatchListener itemDispatchListener) {
        this.mMountingManager = mountingManager;
        this.mItemDispatchListener = itemDispatchListener;
    }

    @AnyThread
    public void dispatchCommandMountItem(DispatchCommandMountItem dispatchCommandMountItem) {
        addViewCommandMountItem(dispatchCommandMountItem);
    }

    public void addMountItem(MountItem mountItem) {
        this.mMountItems.add(mountItem);
    }

    public void addPreAllocateMountItem(PreAllocateViewMountItem preAllocateViewMountItem) {
        if (this.mMountingManager.surfaceIsStopped(preAllocateViewMountItem.getSurfaceId())) {
            return;
        }
        this.mPreMountItems.add(preAllocateViewMountItem);
    }

    public void addViewCommandMountItem(DispatchCommandMountItem dispatchCommandMountItem) {
        this.mViewCommandMountItems.add(dispatchCommandMountItem);
    }

    @UiThread
    public boolean tryDispatchMountItems() {
        if (this.mInDispatch) {
            return false;
        }
        try {
            boolean zDispatchMountItems = dispatchMountItems();
            this.mInDispatch = false;
            this.mItemDispatchListener.didDispatchMountItems();
            int i = this.mReDispatchCounter;
            if (i < 10 && zDispatchMountItems) {
                if (i > 2) {
                    ReactSoftExceptionLogger.logSoftException("MountItemDispatcher", new ReactNoCrashSoftException("Re-dispatched " + this.mReDispatchCounter + " times. This indicates setState (?) is likely being called too many times during mounting."));
                }
                this.mReDispatchCounter++;
                tryDispatchMountItems();
            }
            this.mReDispatchCounter = 0;
            return zDispatchMountItems;
        } finally {
        }
    }

    @UiThread
    public void dispatchMountItems(Queue<MountItem> queue) {
        while (!queue.isEmpty()) {
            MountItem mountItemPoll = queue.poll();
            try {
                mountItemPoll.execute(this.mMountingManager);
            } catch (RetryableMountingLayerException e) {
                if (mountItemPoll instanceof DispatchCommandMountItem) {
                    DispatchCommandMountItem dispatchCommandMountItem = (DispatchCommandMountItem) mountItemPoll;
                    if (dispatchCommandMountItem.getRetries() == 0) {
                        dispatchCommandMountItem.incrementRetries();
                        dispatchCommandMountItem(dispatchCommandMountItem);
                    }
                } else {
                    printMountItem(mountItemPoll, "dispatchExternalMountItems: mounting failed with " + e.getMessage());
                }
            }
        }
    }

    @UiThread
    private boolean dispatchMountItems() {
        boolean zIsIgnorable;
        if (this.mReDispatchCounter == 0) {
            this.mBatchedExecutionTime = 0L;
        }
        this.mRunStartTime = SystemClock.uptimeMillis();
        List<DispatchCommandMountItem> andResetViewCommandMountItems = getAndResetViewCommandMountItems();
        List<MountItem> andResetMountItems = getAndResetMountItems();
        if (andResetMountItems == null && andResetViewCommandMountItems == null) {
            return false;
        }
        if (andResetViewCommandMountItems != null) {
            Systrace.beginSection(0L, "FabricUIManager::mountViews viewCommandMountItems");
            for (DispatchCommandMountItem dispatchCommandMountItem : andResetViewCommandMountItems) {
                if (FabricUIManager.ENABLE_FABRIC_LOGS) {
                    printMountItem(dispatchCommandMountItem, "dispatchMountItems: Executing viewCommandMountItem");
                }
                try {
                    executeOrEnqueue(dispatchCommandMountItem);
                } catch (RetryableMountingLayerException e) {
                    if (dispatchCommandMountItem.getRetries() == 0) {
                        dispatchCommandMountItem.incrementRetries();
                        dispatchCommandMountItem(dispatchCommandMountItem);
                    } else {
                        ReactSoftExceptionLogger.logSoftException("MountItemDispatcher", new ReactNoCrashSoftException("Caught exception executing ViewCommand: " + dispatchCommandMountItem.toString(), e));
                    }
                } catch (Throwable th) {
                    ReactSoftExceptionLogger.logSoftException("MountItemDispatcher", new RuntimeException("Caught exception executing ViewCommand: " + dispatchCommandMountItem.toString(), th));
                }
            }
            Systrace.endSection(0L);
        }
        Collection<PreAllocateViewMountItem> andResetPreMountItems = getAndResetPreMountItems();
        if (andResetPreMountItems != null) {
            Systrace.beginSection(0L, "FabricUIManager::mountViews preMountItems");
            Iterator<PreAllocateViewMountItem> it = andResetPreMountItems.iterator();
            while (it.hasNext()) {
                executeOrEnqueue(it.next());
            }
            Systrace.endSection(0L);
        }
        if (andResetMountItems != null) {
            Systrace.beginSection(0L, "FabricUIManager::mountViews mountItems to execute");
            long jUptimeMillis = SystemClock.uptimeMillis();
            Iterator<MountItem> it2 = andResetMountItems.iterator();
            while (it2.hasNext()) {
                MountItem next = it2.next();
                if (FabricUIManager.ENABLE_FABRIC_LOGS) {
                    printMountItem(next, "dispatchMountItems: Executing mountItem");
                }
                try {
                    executeOrEnqueue(next);
                } finally {
                    if (zIsIgnorable) {
                    }
                }
            }
            this.mBatchedExecutionTime += SystemClock.uptimeMillis() - jUptimeMillis;
        }
        Systrace.endSection(0L);
        return true;
    }

    @UiThread
    public void dispatchPreMountItems(long j) {
        PreAllocateViewMountItem preAllocateViewMountItemPoll;
        Systrace.beginSection(0L, "FabricUIManager::premountViews");
        this.mInDispatch = true;
        while (!haveExceededNonBatchedFrameTime(j) && (preAllocateViewMountItemPoll = this.mPreMountItems.poll()) != null) {
            try {
                if (FabricUIManager.ENABLE_FABRIC_LOGS) {
                    printMountItem(preAllocateViewMountItemPoll, "dispatchPreMountItems: Dispatching PreAllocateViewMountItem");
                }
                executeOrEnqueue(preAllocateViewMountItemPoll);
            } catch (Throwable th) {
                this.mInDispatch = false;
                throw th;
            }
        }
        this.mInDispatch = false;
        Systrace.endSection(0L);
    }

    private void executeOrEnqueue(MountItem mountItem) {
        if (this.mMountingManager.isWaitingForViewAttach(mountItem.getSurfaceId())) {
            if (FabricUIManager.ENABLE_FABRIC_LOGS) {
                FLog.e("MountItemDispatcher", "executeOrEnqueue: Item execution delayed, surface %s is not ready yet", Integer.valueOf(mountItem.getSurfaceId()));
            }
            this.mMountingManager.getSurfaceManager(mountItem.getSurfaceId()).executeOnViewAttach(mountItem);
            return;
        }
        mountItem.execute(this.mMountingManager);
    }

    @Nullable
    private static <E extends MountItem> List<E> drainConcurrentItemQueue(ConcurrentLinkedQueue<E> concurrentLinkedQueue) {
        if (concurrentLinkedQueue.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        do {
            E ePoll = concurrentLinkedQueue.poll();
            if (ePoll != null) {
                arrayList.add(ePoll);
            }
        } while (!concurrentLinkedQueue.isEmpty());
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    private static boolean haveExceededNonBatchedFrameTime(long j) {
        return 16 - ((System.nanoTime() - j) / 1000000) < 8;
    }

    @UiThread
    private List<DispatchCommandMountItem> getAndResetViewCommandMountItems() {
        return drainConcurrentItemQueue(this.mViewCommandMountItems);
    }

    @UiThread
    private List<MountItem> getAndResetMountItems() {
        return drainConcurrentItemQueue(this.mMountItems);
    }

    private Collection<PreAllocateViewMountItem> getAndResetPreMountItems() {
        return drainConcurrentItemQueue(this.mPreMountItems);
    }

    public long getBatchedExecutionTime() {
        return this.mBatchedExecutionTime;
    }

    public long getRunStartTime() {
        return this.mRunStartTime;
    }

    private static void printMountItem(MountItem mountItem, String str) {
        for (String str2 : mountItem.toString().split("\n")) {
            FLog.e("MountItemDispatcher", str + ": " + str2);
        }
    }
}
