package com.facebook.react.uimanager;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.yoga.YogaMeasureMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ViewManager<T extends View, C extends ReactShadowNode> extends BaseJavaModule {
    private static String NAME = "ViewManager";

    @Nullable
    private HashMap<Integer, Stack<T>> mRecyclableViews = null;
    private int mRecyclableViewsBufferSize = 1024;

    protected void addEventEmitters(@NonNull ThemedReactContext themedReactContext, @NonNull T t) {
    }

    @NonNull
    protected abstract T createViewInstance(@NonNull ThemedReactContext themedReactContext);

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return null;
    }

    @Nullable
    protected ViewManagerDelegate<T> getDelegate() {
        return null;
    }

    @Nullable
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return null;
    }

    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return null;
    }

    @Nullable
    public Map<String, Object> getExportedViewConstants() {
        return null;
    }

    @Override // com.facebook.react.bridge.NativeModule
    @NonNull
    public abstract String getName();

    public abstract Class<? extends C> getShadowNodeClass();

    public long measure(Context context, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, @Nullable float[] fArr) {
        return 0L;
    }

    public long measure(Context context, MapBuffer mapBuffer, MapBuffer mapBuffer2, @Nullable MapBuffer mapBuffer3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, @Nullable float[] fArr) {
        return 0L;
    }

    protected void onAfterUpdateTransaction(@NonNull T t) {
    }

    protected T prepareToRecycleView(@NonNull ThemedReactContext themedReactContext, @NonNull T t) {
        return t;
    }

    @Deprecated
    public void receiveCommand(@NonNull T t, int i, @Nullable ReadableArray readableArray) {
    }

    public void receiveCommand(@NonNull T t, String str, @Nullable ReadableArray readableArray) {
    }

    protected T recycleView(@NonNull ThemedReactContext themedReactContext, @NonNull T t) {
        return t;
    }

    public void setPadding(T t, int i, int i2, int i3, int i4) {
    }

    public abstract void updateExtraData(@NonNull T t, Object obj);

    @Nullable
    public Object updateState(@NonNull T t, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        return null;
    }

    protected void setupViewRecycling() {
        if (ReactFeatureFlags.enableViewRecycling) {
            this.mRecyclableViews = new HashMap<>();
        }
    }

    protected void setupViewRecycling(int i) {
        this.mRecyclableViewsBufferSize = i;
        setupViewRecycling();
    }

    @Nullable
    private Stack<T> getRecyclableViewStack(int i) {
        HashMap<Integer, Stack<T>> map = this.mRecyclableViews;
        if (map == null) {
            return null;
        }
        if (!map.containsKey(Integer.valueOf(i))) {
            this.mRecyclableViews.put(Integer.valueOf(i), new Stack<>());
        }
        return this.mRecyclableViews.get(Integer.valueOf(i));
    }

    public void updateProperties(@NonNull T t, ReactStylesDiffMap reactStylesDiffMap) {
        ViewManagerDelegate<T> delegate = getDelegate();
        if (delegate != null) {
            ViewManagerPropertyUpdater.updateProps(delegate, t, reactStylesDiffMap);
        } else {
            ViewManagerPropertyUpdater.updateProps(this, t, reactStylesDiffMap);
        }
        onAfterUpdateTransaction(t);
    }

    @NonNull
    public T createView(int i, @NonNull ThemedReactContext themedReactContext, @Nullable ReactStylesDiffMap reactStylesDiffMap, @Nullable StateWrapper stateWrapper, JSResponderHandler jSResponderHandler) {
        T t = (T) createViewInstance(i, themedReactContext, reactStylesDiffMap, stateWrapper);
        if (t instanceof ReactInterceptingViewGroup) {
            ((ReactInterceptingViewGroup) t).setOnInterceptTouchEventListener(jSResponderHandler);
        }
        return t;
    }

    public C createShadowNodeInstance() {
        throw new RuntimeException("ViewManager subclasses must implement createShadowNodeInstance()");
    }

    @NonNull
    public C createShadowNodeInstance(@NonNull ReactApplicationContext reactApplicationContext) {
        return (C) createShadowNodeInstance();
    }

    @NonNull
    protected T createViewInstance(int i, @NonNull ThemedReactContext themedReactContext, @Nullable ReactStylesDiffMap reactStylesDiffMap, @Nullable StateWrapper stateWrapper) {
        T t;
        Object objUpdateState;
        Stack<T> recyclableViewStack = getRecyclableViewStack(themedReactContext.getSurfaceId());
        if (recyclableViewStack != null && !recyclableViewStack.empty()) {
            t = (T) recycleView(themedReactContext, recyclableViewStack.pop());
        } else {
            t = (T) createViewInstance(themedReactContext);
        }
        t.setId(i);
        addEventEmitters(themedReactContext, t);
        if (reactStylesDiffMap != null) {
            updateProperties(t, reactStylesDiffMap);
        }
        if (stateWrapper != null && (objUpdateState = updateState(t, reactStylesDiffMap, stateWrapper)) != null) {
            updateExtraData(t, objUpdateState);
        }
        return t;
    }

    public void onDropViewInstance(@NonNull T t) {
        Context context = t.getContext();
        if (context == null) {
            FLog.e(NAME, "onDropViewInstance: view [" + t.getId() + "] has a null context");
            return;
        }
        if (!(context instanceof ThemedReactContext)) {
            FLog.e(NAME, "onDropViewInstance: view [" + t.getId() + "] has a context that is not a ThemedReactContext: " + context);
            return;
        }
        ThemedReactContext themedReactContext = (ThemedReactContext) context;
        Stack<T> recyclableViewStack = getRecyclableViewStack(themedReactContext.getSurfaceId());
        if (recyclableViewStack != null) {
            if (this.mRecyclableViewsBufferSize < 0 || recyclableViewStack.size() < this.mRecyclableViewsBufferSize) {
                recyclableViewStack.push(prepareToRecycleView(themedReactContext, t));
            }
        }
    }

    public Map<String, String> getNativeProps() {
        return ViewManagerPropertyUpdater.getNativeProps(getClass(), getShadowNodeClass());
    }

    public void onSurfaceStopped(int i) {
        HashMap<Integer, Stack<T>> map = this.mRecyclableViews;
        if (map != null) {
            map.remove(Integer.valueOf(i));
        }
    }

    void trimMemory() {
        if (this.mRecyclableViews != null) {
            this.mRecyclableViews = new HashMap<>();
        }
    }
}
