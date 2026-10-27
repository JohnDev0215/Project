package com.amazon.mp3.view.refinements;

import android.view.View;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class ViewLoadingMonitor {
    private ViewState mCurrentViewState;
    private OnViewsLoadingListener mListener;
    private Map<Integer, Integer> mViewStateCount;
    private Map<View, ViewState> mViewsMap = new HashMap();

    public interface OnViewsLoadingListener {
        void onAllViewsLoaded();

        void onViewsError();

        void onViewsLoading();
    }

    public enum ViewState {
        LOADING(0),
        LOADED(1),
        ERROR(2);

        private final int mValue;

        ViewState(int value) {
            this.mValue = value;
        }

        int getValue() {
            return this.mValue;
        }
    }

    public ViewLoadingMonitor() {
        HashMap map = new HashMap();
        this.mViewStateCount = map;
        map.put(Integer.valueOf(ViewState.LOADING.getValue()), 0);
        this.mViewStateCount.put(Integer.valueOf(ViewState.LOADED.getValue()), 0);
        this.mViewStateCount.put(Integer.valueOf(ViewState.ERROR.getValue()), 0);
    }

    public void setOnViewsLoadingListener(OnViewsLoadingListener listener) {
        this.mListener = listener;
        notifyListener();
    }

    public void registerView(View view) {
        if (!this.mViewsMap.containsKey(view)) {
            this.mViewsMap.put(view, ViewState.LOADING);
        }
        notifyListener();
    }

    public void deregisterAllViews() {
        this.mViewsMap.clear();
    }

    public void notifyViewState(View view, ViewState viewState) {
        if (this.mViewsMap.containsKey(view)) {
            ViewState viewState2 = this.mViewsMap.get(view);
            if (viewState2 != viewState) {
                decrementMapValue(viewState2.getValue());
                incrementMapValue(viewState.getValue());
                this.mViewsMap.put(view, viewState);
            }
            notifyListener();
        }
    }

    public void notifyAllViewsLoaded() {
        Iterator<View> it = this.mViewsMap.keySet().iterator();
        while (it.hasNext()) {
            this.mViewsMap.put(it.next(), ViewState.LOADED);
        }
        this.mViewStateCount.put(Integer.valueOf(ViewState.LOADING.getValue()), 0);
        this.mViewStateCount.put(Integer.valueOf(ViewState.LOADED.getValue()), Integer.valueOf(this.mViewsMap.size()));
        this.mViewStateCount.put(Integer.valueOf(ViewState.ERROR.getValue()), 0);
        notifyListener();
    }

    private ViewState getCurrentViewState() {
        Map<Integer, Integer> map = this.mViewStateCount;
        ViewState viewState = ViewState.ERROR;
        if (map.get(Integer.valueOf(viewState.getValue())).intValue() > 0) {
            return viewState;
        }
        Map<Integer, Integer> map2 = this.mViewStateCount;
        ViewState viewState2 = ViewState.LOADED;
        return map2.get(Integer.valueOf(viewState2.getValue())).intValue() == this.mViewsMap.size() && this.mViewsMap.size() > 0 ? viewState2 : ViewState.LOADING;
    }

    private void notifyListener() {
        ViewState currentViewState = getCurrentViewState();
        if (this.mListener == null || currentViewState == this.mCurrentViewState) {
            return;
        }
        this.mCurrentViewState = currentViewState;
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$view$refinements$ViewLoadingMonitor$ViewState[currentViewState.ordinal()];
        if (i == 1) {
            this.mListener.onAllViewsLoaded();
        } else if (i == 2) {
            this.mListener.onViewsLoading();
        } else {
            if (i != 3) {
                return;
            }
            this.mListener.onViewsError();
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.view.refinements.ViewLoadingMonitor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$view$refinements$ViewLoadingMonitor$ViewState;

        static {
            int[] iArr = new int[ViewState.values().length];
            $SwitchMap$com$amazon$mp3$view$refinements$ViewLoadingMonitor$ViewState = iArr;
            try {
                iArr[ViewState.LOADED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$view$refinements$ViewLoadingMonitor$ViewState[ViewState.LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$view$refinements$ViewLoadingMonitor$ViewState[ViewState.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void decrementMapValue(int key) {
        this.mViewStateCount.put(Integer.valueOf(key), Integer.valueOf(this.mViewStateCount.get(Integer.valueOf(key)).intValue() - 1));
    }

    private void incrementMapValue(int key) {
        this.mViewStateCount.put(Integer.valueOf(key), Integer.valueOf(this.mViewStateCount.get(Integer.valueOf(key)).intValue() + 1));
    }
}
