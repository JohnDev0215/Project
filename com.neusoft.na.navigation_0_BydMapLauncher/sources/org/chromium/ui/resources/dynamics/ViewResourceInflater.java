package org.chromium.ui.resources.dynamics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes4.dex */
public class ViewResourceInflater {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int INVALID_ID = -1;
    private ViewGroup mContainer;
    private Context mContext;
    private boolean mIsAttached;
    private boolean mIsInvalidated;
    private int mLayoutId;
    private boolean mNeedsLayoutUpdate;
    private ViewInflaterOnDrawListener mOnDrawListener;
    private ViewResourceAdapter mResourceAdapter;
    private DynamicResourceLoader mResourceLoader;
    private View mView;
    private int mViewId;

    protected void onFinishInflate() {
    }

    protected boolean shouldAttachView() {
        return true;
    }

    protected boolean shouldDetachViewAfterCapturing() {
        return true;
    }

    public ViewResourceInflater(int i, int i2, Context context, ViewGroup viewGroup, DynamicResourceLoader dynamicResourceLoader) {
        this.mLayoutId = i;
        this.mViewId = i2;
        this.mContext = context;
        this.mContainer = viewGroup;
        this.mResourceLoader = dynamicResourceLoader;
    }

    public void inflate() {
        if (this.mView != null) {
            return;
        }
        this.mView = LayoutInflater.from(this.mContext).inflate(this.mLayoutId, this.mContainer, false);
        onFinishInflate();
        registerResource();
        this.mNeedsLayoutUpdate = true;
    }

    public void invalidate() {
        invalidate(false);
    }

    public void invalidate(boolean z) {
        if (this.mView == null) {
            inflate();
        }
        this.mIsInvalidated = true;
        if (!this.mIsAttached && shouldAttachView()) {
            attachView();
        }
        if (this.mIsAttached) {
            if (z || this.mNeedsLayoutUpdate) {
                updateLayoutParams();
            }
        } else {
            layout();
            invalidateResource();
        }
        this.mNeedsLayoutUpdate = false;
    }

    public void destroy() {
        if (this.mView == null) {
            return;
        }
        unregisterResource();
        detachView();
        this.mView = null;
        this.mLayoutId = -1;
        this.mViewId = -1;
        this.mContext = null;
        this.mContainer = null;
        this.mResourceLoader = null;
    }

    public int getMeasuredWidth() {
        return this.mView.getMeasuredWidth();
    }

    public int getMeasuredHeight() {
        return this.mView.getMeasuredHeight();
    }

    public int getViewId() {
        return this.mViewId;
    }

    protected int getWidthMeasureSpec() {
        return getUnspecifiedMeasureSpec();
    }

    protected int getHeightMeasureSpec() {
        return getUnspecifiedMeasureSpec();
    }

    protected void layout() {
        this.mView.measure(getWidthMeasureSpec(), getHeightMeasureSpec());
        this.mView.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    protected View getView() {
        return this.mView;
    }

    protected Context getContext() {
        return this.mContext;
    }

    private void attachView() {
        if (this.mIsAttached) {
            return;
        }
        this.mContainer.addView(this.mView);
        this.mIsAttached = true;
        if (this.mOnDrawListener == null) {
            this.mOnDrawListener = new ViewInflaterOnDrawListener();
            this.mView.getViewTreeObserver().addOnDrawListener(this.mOnDrawListener);
        }
    }

    private void detachView() {
        if (this.mIsAttached) {
            if (this.mOnDrawListener != null) {
                this.mView.getViewTreeObserver().removeOnDrawListener(this.mOnDrawListener);
                this.mOnDrawListener = null;
            }
            this.mContainer.removeView(this.mView);
            this.mIsAttached = false;
        }
    }

    private void updateLayoutParams() {
        int widthMeasureSpec = getWidthMeasureSpec();
        int size = View.MeasureSpec.getMode(widthMeasureSpec) == 1073741824 ? View.MeasureSpec.getSize(widthMeasureSpec) : -2;
        int heightMeasureSpec = getHeightMeasureSpec();
        int size2 = View.MeasureSpec.getMode(heightMeasureSpec) == 1073741824 ? View.MeasureSpec.getSize(heightMeasureSpec) : -2;
        ViewGroup.LayoutParams layoutParams = this.mView.getLayoutParams();
        layoutParams.width = size;
        layoutParams.height = size2;
        this.mView.setLayoutParams(layoutParams);
    }

    private int getUnspecifiedMeasureSpec() {
        return View.MeasureSpec.makeMeasureSpec(0, 0);
    }

    private void registerResource() {
        if (this.mResourceAdapter == null) {
            this.mResourceAdapter = new ViewInflaterAdapter(this.mView.findViewById(this.mViewId));
        }
        DynamicResourceLoader dynamicResourceLoader = this.mResourceLoader;
        if (dynamicResourceLoader != null) {
            dynamicResourceLoader.registerResource(this.mViewId, this.mResourceAdapter);
        }
    }

    private void unregisterResource() {
        DynamicResourceLoader dynamicResourceLoader = this.mResourceLoader;
        if (dynamicResourceLoader != null) {
            dynamicResourceLoader.unregisterResource(this.mViewId);
        }
        this.mResourceAdapter = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateResource() {
        ViewResourceAdapter viewResourceAdapter;
        if (!this.mIsInvalidated || this.mView == null || (viewResourceAdapter = this.mResourceAdapter) == null) {
            return;
        }
        this.mIsInvalidated = false;
        viewResourceAdapter.invalidate(null);
    }

    private class ViewInflaterAdapter extends ViewResourceAdapter {
        public ViewInflaterAdapter(View view) {
            super(view);
        }

        @Override // org.chromium.ui.resources.dynamics.ViewResourceAdapter
        protected void onCaptureEnd() {
            ViewResourceInflater.this.onCaptureEnd();
        }
    }

    protected void onCaptureEnd() {
        if (shouldDetachViewAfterCapturing()) {
            detachView();
        }
    }

    private class ViewInflaterOnDrawListener implements ViewTreeObserver.OnDrawListener {
        private ViewInflaterOnDrawListener() {
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public void onDraw() {
            ViewResourceInflater.this.invalidateResource();
        }
    }
}
