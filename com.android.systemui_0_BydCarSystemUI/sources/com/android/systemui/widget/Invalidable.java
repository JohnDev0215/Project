package com.android.systemui.widget;

import android.view.View;
import com.android.systemui.util.ViewInvalidUtils;

/* JADX INFO: loaded from: classes.dex */
public interface Invalidable {
    public static final OnInvalidStateChangedCallbackRef ref = new OnInvalidStateChangedCallbackRef();

    public interface OnInvalidStateChangedCallback {
        void onInvalidStateChanged(boolean invalid, boolean fromUser);
    }

    public static class OnInvalidStateChangedCallbackRef {
        OnInvalidStateChangedCallback callback;
    }

    default void setInvalid(boolean invalid) {
        setInvalid(invalid, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    default void setInvalid(boolean invalid, boolean fromUser) {
        if (this instanceof View) {
            ViewInvalidUtils.setInvalid((View) this, invalid);
            OnInvalidStateChangedCallbackRef onInvalidStateChangedCallbackRef = ref;
            if (onInvalidStateChangedCallbackRef.callback != null) {
                onInvalidStateChangedCallbackRef.callback.onInvalidStateChanged(invalid, fromUser);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    default boolean isInvalid() {
        if (this instanceof View) {
            return ViewInvalidUtils.isInvalid((View) this);
        }
        return false;
    }

    default void registerInvalidStateChangedCallback(OnInvalidStateChangedCallback callback) {
        ref.callback = callback;
    }

    default void unregisterInvalidStateChangedCallback() {
        ref.callback = null;
    }
}
