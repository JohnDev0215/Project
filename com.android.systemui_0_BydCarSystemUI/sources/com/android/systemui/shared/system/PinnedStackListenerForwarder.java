package com.android.systemui.shared.system;

import android.content.ComponentName;
import android.content.pm.ParceledListSlice;
import android.view.DisplayInfo;
import android.view.IPinnedStackController;
import android.view.IPinnedStackListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PinnedStackListenerForwarder extends IPinnedStackListener.Stub {
    private List<PinnedStackListener> mListeners = new ArrayList();

    public static class PinnedStackListener {
        public void onActionsChanged(ParceledListSlice actions) {
        }

        public void onActivityHidden(ComponentName componentName) {
        }

        public void onAspectRatioChanged(float aspectRatio) {
        }

        public void onConfigurationChanged() {
        }

        public void onDisplayInfoChanged(DisplayInfo displayInfo) {
        }

        public void onImeVisibilityChanged(boolean imeVisible, int imeHeight) {
        }

        public void onListenerRegistered(IPinnedStackController controller) {
        }

        public void onMovementBoundsChanged(boolean fromImeAdjustment) {
        }
    }

    public void addListener(PinnedStackListener listener) {
        this.mListeners.add(listener);
    }

    public void removeListener(PinnedStackListener listener) {
        this.mListeners.remove(listener);
    }

    public void onListenerRegistered(IPinnedStackController controller) {
        Iterator<PinnedStackListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onListenerRegistered(controller);
        }
    }

    public void onMovementBoundsChanged(boolean fromImeAdjustment) {
        Iterator<PinnedStackListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onMovementBoundsChanged(fromImeAdjustment);
        }
    }

    public void onImeVisibilityChanged(boolean imeVisible, int imeHeight) {
        Iterator<PinnedStackListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onImeVisibilityChanged(imeVisible, imeHeight);
        }
    }

    public void onActionsChanged(ParceledListSlice actions) {
        Iterator<PinnedStackListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onActionsChanged(actions);
        }
    }

    public void onActivityHidden(ComponentName componentName) {
        Iterator<PinnedStackListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onActivityHidden(componentName);
        }
    }

    public void onDisplayInfoChanged(DisplayInfo displayInfo) {
        Iterator<PinnedStackListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onDisplayInfoChanged(displayInfo);
        }
    }

    public void onConfigurationChanged() {
        Iterator<PinnedStackListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onConfigurationChanged();
        }
    }

    public void onAspectRatioChanged(float aspectRatio) {
        Iterator<PinnedStackListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onAspectRatioChanged(aspectRatio);
        }
    }
}
