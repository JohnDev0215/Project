package com.android.launcher3.allapps;

import android.view.View;
import android.view.ViewGroup;
import com.android.launcher3.AppInfo;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.PromiseAppInfo;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.PackageUserKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes4.dex */
public class AllAppsStore {
    public static final int DEFER_UPDATES_NEXT_DRAW = 1;
    public static final int DEFER_UPDATES_TEST = 4;
    public static final int DEFER_UPDATES_USER_INTERACTION = 2;
    private PackageUserKey mTempKey = new PackageUserKey(null, null);
    private final HashMap<ComponentKey, AppInfo> mComponentToAppMap = new HashMap<>();
    private final List<OnUpdateListener> mUpdateListeners = new ArrayList();
    private final ArrayList<ViewGroup> mIconContainers = new ArrayList<>();
    private int mDeferUpdatesFlags = 0;
    private boolean mUpdatePending = false;

    public interface OnUpdateListener {
        void onAppsUpdated();
    }

    public Collection<AppInfo> getApps() {
        return this.mComponentToAppMap.values();
    }

    public void setApps(List<AppInfo> apps) {
        this.mComponentToAppMap.clear();
        addOrUpdateApps(apps);
    }

    public AppInfo getApp(ComponentKey key) {
        return this.mComponentToAppMap.get(key);
    }

    public void enableDeferUpdates(int flag) {
        this.mDeferUpdatesFlags = flag | this.mDeferUpdatesFlags;
    }

    public void disableDeferUpdates(int flag) {
        int i = (~flag) & this.mDeferUpdatesFlags;
        this.mDeferUpdatesFlags = i;
        if (i == 0 && this.mUpdatePending) {
            notifyUpdate();
            this.mUpdatePending = false;
        }
    }

    public int getDeferUpdatesFlags() {
        return this.mDeferUpdatesFlags;
    }

    public void addOrUpdateApps(List<AppInfo> apps) {
        for (AppInfo appInfo : apps) {
            this.mComponentToAppMap.put(appInfo.toComponentKey(), appInfo);
        }
        notifyUpdate();
    }

    public void removeApps(List<AppInfo> apps) {
        Iterator<AppInfo> it = apps.iterator();
        while (it.hasNext()) {
            this.mComponentToAppMap.remove(it.next().toComponentKey());
        }
        notifyUpdate();
    }

    private void notifyUpdate() {
        if (this.mDeferUpdatesFlags != 0) {
            this.mUpdatePending = true;
            return;
        }
        int size = this.mUpdateListeners.size();
        for (int i = 0; i < size; i++) {
            this.mUpdateListeners.get(i).onAppsUpdated();
        }
    }

    public void addUpdateListener(OnUpdateListener listener) {
        this.mUpdateListeners.add(listener);
    }

    public void removeUpdateListener(OnUpdateListener listener) {
        this.mUpdateListeners.remove(listener);
    }

    public void registerIconContainer(ViewGroup container) {
        if (container != null) {
            this.mIconContainers.add(container);
        }
    }

    public void unregisterIconContainer(ViewGroup container) {
        this.mIconContainers.remove(container);
    }

    public void updateNotificationDots(final Predicate<PackageUserKey> updatedDots) {
        updateAllIcons(new Consumer() { // from class: com.android.launcher3.allapps.-$$Lambda$AllAppsStore$p5SLWMHISCd7ho7Ub6Z2K_6Ny6U
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$updateNotificationDots$0$AllAppsStore(updatedDots, (BubbleTextView) obj);
            }
        });
    }

    public /* synthetic */ void lambda$updateNotificationDots$0$AllAppsStore(Predicate predicate, BubbleTextView bubbleTextView) {
        if (bubbleTextView.getTag() instanceof ItemInfo) {
            ItemInfo itemInfo = (ItemInfo) bubbleTextView.getTag();
            if (this.mTempKey.updateFromItemInfo(itemInfo) && predicate.test(this.mTempKey)) {
                bubbleTextView.applyDotState(itemInfo, true);
            }
        }
    }

    public void updatePromiseAppProgress(final PromiseAppInfo app) {
        updateAllIcons(new Consumer() { // from class: com.android.launcher3.allapps.-$$Lambda$AllAppsStore$R7GMsFE50LkJoaVLkgDAAVa1_94
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AllAppsStore.lambda$updatePromiseAppProgress$1(app, (BubbleTextView) obj);
            }
        });
    }

    static /* synthetic */ void lambda$updatePromiseAppProgress$1(PromiseAppInfo promiseAppInfo, BubbleTextView bubbleTextView) {
        if (bubbleTextView.getTag() == promiseAppInfo) {
            bubbleTextView.applyProgressLevel(promiseAppInfo.level);
        }
    }

    private void updateAllIcons(Consumer<BubbleTextView> action) {
        for (int size = this.mIconContainers.size() - 1; size >= 0; size--) {
            ViewGroup viewGroup = this.mIconContainers.get(size);
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof BubbleTextView) {
                    action.accept((BubbleTextView) childAt);
                }
            }
        }
    }
}
