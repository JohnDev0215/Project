package com.android.launcher3.model;

import android.os.Looper;
import android.util.Log;
import com.android.launcher3.AllAppsList;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherAppWidgetInfo;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.MainThreadExecutor;
import com.android.launcher3.util.IntArray;
import com.android.launcher3.util.IntSet;
import com.android.launcher3.util.LooperIdleLock;
import com.android.launcher3.util.ViewOnDrawExecutor;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseLoaderResults {
    protected static final int INVALID_SCREEN_ID = -1;
    private static final int ITEMS_CHUNK = 6;
    protected static final String TAG = "LoaderResults";
    protected final LauncherAppState mApp;
    private final AllAppsList mBgAllAppsList;
    protected final BgDataModel mBgDataModel;
    protected final WeakReference<LauncherModel.Callbacks> mCallbacks;
    private int mMyBindingId;
    protected final int mPageToBindFirst;
    protected final Executor mUiExecutor = new MainThreadExecutor();

    public abstract void bindDeepShortcuts();

    public abstract void bindWidgets();

    public BaseLoaderResults(LauncherAppState app, BgDataModel dataModel, AllAppsList allAppsList, int pageToBindFirst, WeakReference<LauncherModel.Callbacks> callbacks) {
        this.mApp = app;
        this.mBgDataModel = dataModel;
        this.mBgAllAppsList = allAppsList;
        this.mPageToBindFirst = pageToBindFirst;
        this.mCallbacks = callbacks == null ? new WeakReference<>(null) : callbacks;
    }

    public void bindWorkspace() {
        LauncherModel.Callbacks callbacks = this.mCallbacks.get();
        if (callbacks == null) {
            Log.w(TAG, "LoaderTask running with no launcher");
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final IntArray intArray = new IntArray();
        synchronized (this.mBgDataModel) {
            arrayList.addAll(this.mBgDataModel.workspaceItems);
            arrayList2.addAll(this.mBgDataModel.appWidgets);
            intArray.addAll(this.mBgDataModel.collectWorkspaceScreens());
            this.mBgDataModel.lastBindId++;
            this.mMyBindingId = this.mBgDataModel.lastBindId;
        }
        int currentWorkspaceScreen = this.mPageToBindFirst;
        if (currentWorkspaceScreen == -1001) {
            currentWorkspaceScreen = callbacks.getCurrentWorkspaceScreen();
        }
        final int i = currentWorkspaceScreen < intArray.size() ? currentWorkspaceScreen : -1001;
        final boolean z = i >= 0;
        int i2 = z ? intArray.get(i) : -1;
        ArrayList<ItemInfo> arrayList3 = new ArrayList<>();
        ArrayList<ItemInfo> arrayList4 = new ArrayList<>();
        ArrayList<LauncherAppWidgetInfo> arrayList5 = new ArrayList<>();
        ArrayList<LauncherAppWidgetInfo> arrayList6 = new ArrayList<>();
        filterCurrentWorkspaceItems(i2, arrayList, arrayList3, arrayList4);
        filterCurrentWorkspaceItems(i2, arrayList2, arrayList5, arrayList6);
        sortWorkspaceItemsSpatially(arrayList3);
        sortWorkspaceItemsSpatially(arrayList4);
        executeCallbacksTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.-$$Lambda$BaseLoaderResults$1AVMaeJfqGasRXn1Gn1u2IZkKPk
            @Override // com.android.launcher3.LauncherModel.CallbackTask
            public final void execute(LauncherModel.Callbacks callbacks2) {
                BaseLoaderResults.lambda$bindWorkspace$0(callbacks2);
            }
        }, this.mUiExecutor);
        executeCallbacksTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.-$$Lambda$BaseLoaderResults$UcQ1cBTmSZuvp0CzMamYLn-asQE
            @Override // com.android.launcher3.LauncherModel.CallbackTask
            public final void execute(LauncherModel.Callbacks callbacks2) {
                callbacks2.bindScreens(intArray);
            }
        }, this.mUiExecutor);
        Executor executor = this.mUiExecutor;
        bindWorkspaceItems(arrayList3, executor);
        bindAppWidgets(arrayList5, executor);
        final Executor viewOnDrawExecutor = z ? new ViewOnDrawExecutor() : executor;
        executeCallbacksTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.-$$Lambda$BaseLoaderResults$YAQscEQxNJ61v-i8ehXKZYAxsN8
            @Override // com.android.launcher3.LauncherModel.CallbackTask
            public final void execute(LauncherModel.Callbacks callbacks2) {
                callbacks2.finishFirstPageBind(z ? (ViewOnDrawExecutor) viewOnDrawExecutor : null);
            }
        }, executor);
        bindWorkspaceItems(arrayList4, viewOnDrawExecutor);
        bindAppWidgets(arrayList6, viewOnDrawExecutor);
        executeCallbacksTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.-$$Lambda$BaseLoaderResults$H8Z7SZ5asJyl2wBq2vsZX_kxmRQ
            @Override // com.android.launcher3.LauncherModel.CallbackTask
            public final void execute(LauncherModel.Callbacks callbacks2) {
                this.f$0.lambda$bindWorkspace$3$BaseLoaderResults(callbacks2);
            }
        }, viewOnDrawExecutor);
        if (z) {
            executeCallbacksTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.-$$Lambda$BaseLoaderResults$YNEBCDOuqqc-PLGcU-1-ZxmDV1A
                @Override // com.android.launcher3.LauncherModel.CallbackTask
                public final void execute(LauncherModel.Callbacks callbacks2) {
                    BaseLoaderResults.lambda$bindWorkspace$4(i, viewOnDrawExecutor, callbacks2);
                }
            }, this.mUiExecutor);
        }
    }

    static /* synthetic */ void lambda$bindWorkspace$0(LauncherModel.Callbacks callbacks) {
        callbacks.clearPendingBinds();
        callbacks.startBinding();
    }

    public /* synthetic */ void lambda$bindWorkspace$3$BaseLoaderResults(LauncherModel.Callbacks callbacks) {
        callbacks.finishBindingItems(this.mPageToBindFirst);
    }

    static /* synthetic */ void lambda$bindWorkspace$4(int i, Executor executor, LauncherModel.Callbacks callbacks) {
        if (i != -1001) {
            callbacks.onPageBoundSynchronously(i);
        }
        callbacks.executeOnNextDraw((ViewOnDrawExecutor) executor);
    }

    public static <T extends ItemInfo> void filterCurrentWorkspaceItems(int currentScreenId, ArrayList<T> allWorkspaceItems, ArrayList<T> currentScreenItems, ArrayList<T> otherScreenItems) {
        Iterator<T> it = allWorkspaceItems.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                it.remove();
            }
        }
        IntSet intSet = new IntSet();
        Collections.sort(allWorkspaceItems, new Comparator() { // from class: com.android.launcher3.model.-$$Lambda$BaseLoaderResults$p0t98keXPtQLPi_VynUplnxfYxQ
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Integer.compare(((ItemInfo) obj).container, ((ItemInfo) obj2).container);
            }
        });
        for (T t : allWorkspaceItems) {
            if (t.container == -100) {
                if (t.screenId == currentScreenId) {
                    currentScreenItems.add(t);
                    intSet.add(t.id);
                } else {
                    otherScreenItems.add(t);
                }
            } else if (t.container == -101) {
                currentScreenItems.add(t);
                intSet.add(t.id);
            } else if (intSet.contains(t.container)) {
                currentScreenItems.add(t);
                intSet.add(t.id);
            } else {
                otherScreenItems.add(t);
            }
        }
    }

    protected void sortWorkspaceItemsSpatially(ArrayList<ItemInfo> workspaceItems) {
        InvariantDeviceProfile invariantDeviceProfile = this.mApp.getInvariantDeviceProfile();
        final int i = invariantDeviceProfile.numColumns;
        final int i2 = invariantDeviceProfile.numColumns * invariantDeviceProfile.numRows;
        Collections.sort(workspaceItems, new Comparator<ItemInfo>() { // from class: com.android.launcher3.model.BaseLoaderResults.1
            @Override // java.util.Comparator
            public int compare(ItemInfo lhs, ItemInfo rhs) {
                if (lhs.container == rhs.container) {
                    int i3 = lhs.container;
                    if (i3 == -101) {
                        return Integer.compare(lhs.screenId, rhs.screenId);
                    }
                    if (i3 != -100) {
                        return 0;
                    }
                    return Integer.compare((lhs.screenId * i2) + (lhs.cellY * i) + lhs.cellX, (rhs.screenId * i2) + (rhs.cellY * i) + rhs.cellX);
                }
                return Integer.compare(lhs.container, rhs.container);
            }
        });
    }

    protected void bindWorkspaceItems(final ArrayList<ItemInfo> workspaceItems, final Executor executor) {
        int size = workspaceItems.size();
        final int i = 0;
        while (i < size) {
            int i2 = i + 6;
            final int i3 = i2 <= size ? 6 : size - i;
            executeCallbacksTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.-$$Lambda$BaseLoaderResults$RO-fupKCVM7RBjxGcEy7Q9KeVqc
                @Override // com.android.launcher3.LauncherModel.CallbackTask
                public final void execute(LauncherModel.Callbacks callbacks) {
                    ArrayList arrayList = workspaceItems;
                    int i4 = i;
                    callbacks.bindItems(arrayList.subList(i4, i3 + i4), false);
                }
            }, executor);
            i = i2;
        }
    }

    private void bindAppWidgets(ArrayList<LauncherAppWidgetInfo> appWidgets, Executor executor) {
        int size = appWidgets.size();
        for (int i = 0; i < size; i++) {
            final LauncherAppWidgetInfo launcherAppWidgetInfo = appWidgets.get(i);
            executeCallbacksTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.-$$Lambda$BaseLoaderResults$qY2Pa84suNlSqcC40cDZpObZZZs
                @Override // com.android.launcher3.LauncherModel.CallbackTask
                public final void execute(LauncherModel.Callbacks callbacks) {
                    callbacks.bindItems(Collections.singletonList(launcherAppWidgetInfo), false);
                }
            }, executor);
        }
    }

    public void bindAllApps() {
        final ArrayList arrayList = (ArrayList) this.mBgAllAppsList.data.clone();
        executeCallbacksTask(new LauncherModel.CallbackTask() { // from class: com.android.launcher3.model.-$$Lambda$BaseLoaderResults$k4iJDrZLG_LMFhH78Y-Zz7Z6LCU
            @Override // com.android.launcher3.LauncherModel.CallbackTask
            public final void execute(LauncherModel.Callbacks callbacks) {
                callbacks.bindAllApplications(arrayList);
            }
        }, this.mUiExecutor);
    }

    protected void executeCallbacksTask(final LauncherModel.CallbackTask task, Executor executor) {
        executor.execute(new Runnable() { // from class: com.android.launcher3.model.-$$Lambda$BaseLoaderResults$qipFLUGCV98GcUDjGnOk3boWo50
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$executeCallbacksTask$9$BaseLoaderResults(task);
            }
        });
    }

    public /* synthetic */ void lambda$executeCallbacksTask$9$BaseLoaderResults(LauncherModel.CallbackTask callbackTask) {
        if (this.mMyBindingId != this.mBgDataModel.lastBindId) {
            Log.d(TAG, "Too many consecutive reloads, skipping obsolete data-bind");
            return;
        }
        LauncherModel.Callbacks callbacks = this.mCallbacks.get();
        if (callbacks != null) {
            callbackTask.execute(callbacks);
        }
    }

    public LooperIdleLock newIdleLock(Object lock) {
        LooperIdleLock looperIdleLock = new LooperIdleLock(lock, Looper.getMainLooper());
        if (this.mCallbacks.get() == null) {
            looperIdleLock.queueIdle();
        }
        return looperIdleLock;
    }
}
