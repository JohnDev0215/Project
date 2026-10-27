package com.facebook.react.uimanager;

import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

/* JADX INFO: loaded from: classes6.dex */
public class FabricViewStateManager {

    @Nullable
    private StateWrapper mStateWrapper = null;

    public interface HasFabricViewStateManager {
        FabricViewStateManager getFabricViewStateManager();
    }

    public interface StateUpdateCallback {
        WritableMap getStateUpdate();
    }

    public void setStateWrapper(StateWrapper stateWrapper) {
        this.mStateWrapper = stateWrapper;
    }

    public boolean hasStateWrapper() {
        return this.mStateWrapper != null;
    }

    private void setState(@Nullable StateWrapper stateWrapper, StateUpdateCallback stateUpdateCallback, int i) {
        WritableMap stateUpdate;
        if (stateWrapper == null) {
            FLog.e("FabricViewStateManager", "setState called without a StateWrapper");
        } else if (stateWrapper == this.mStateWrapper && i <= 60 && (stateUpdate = stateUpdateCallback.getStateUpdate()) != null) {
            stateWrapper.updateState(stateUpdate);
        }
    }

    public void setState(StateUpdateCallback stateUpdateCallback) {
        setState(this.mStateWrapper, stateUpdateCallback, 0);
    }

    @Nullable
    public ReadableMap getStateData() {
        StateWrapper stateWrapper = this.mStateWrapper;
        if (stateWrapper != null) {
            return stateWrapper.getStateData();
        }
        return null;
    }
}
