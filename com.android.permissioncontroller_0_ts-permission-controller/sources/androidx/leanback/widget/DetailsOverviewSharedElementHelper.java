package androidx.leanback.widget;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.app.ActivityCompat;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.ViewCompat;
import androidx.leanback.transition.TransitionHelper;
import androidx.leanback.transition.TransitionListener;
import androidx.leanback.widget.DetailsOverviewRowPresenter;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class DetailsOverviewSharedElementHelper extends SharedElementCallback {
    static final boolean DEBUG = false;
    static final String TAG = "DetailsTransitionHelper";
    Activity mActivityToRunTransition;
    int mRightPanelHeight;
    int mRightPanelWidth;
    private Matrix mSavedMatrix;
    private ImageView.ScaleType mSavedScaleType;
    String mSharedElementName;
    boolean mStartedPostpone;
    DetailsOverviewRowPresenter.ViewHolder mViewHolder;

    DetailsOverviewSharedElementHelper() {
    }

    static class TransitionTimeOutRunnable implements Runnable {
        WeakReference<DetailsOverviewSharedElementHelper> mHelperRef;

        TransitionTimeOutRunnable(DetailsOverviewSharedElementHelper helper) {
            this.mHelperRef = new WeakReference<>(helper);
        }

        @Override // java.lang.Runnable
        public void run() {
            DetailsOverviewSharedElementHelper helper = this.mHelperRef.get();
            if (helper == null) {
                return;
            }
            helper.startPostponedEnterTransition();
        }
    }

    private boolean hasImageViewScaleChange(View snapshotView) {
        return snapshotView instanceof ImageView;
    }

    private void saveImageViewScale() {
        if (this.mSavedScaleType == null) {
            ImageView imageView = this.mViewHolder.mImageView;
            ImageView.ScaleType scaleType = imageView.getScaleType();
            this.mSavedScaleType = scaleType;
            this.mSavedMatrix = scaleType == ImageView.ScaleType.MATRIX ? imageView.getMatrix() : null;
        }
    }

    private static void updateImageViewAfterScaleTypeChange(ImageView imageView) {
        imageView.measure(View.MeasureSpec.makeMeasureSpec(imageView.getMeasuredWidth(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(imageView.getMeasuredHeight(), BasicMeasure.EXACTLY));
        imageView.layout(imageView.getLeft(), imageView.getTop(), imageView.getRight(), imageView.getBottom());
    }

    private void changeImageViewScale(View snapshotView) {
        ImageView snapshotImageView = (ImageView) snapshotView;
        ImageView imageView = this.mViewHolder.mImageView;
        imageView.setScaleType(snapshotImageView.getScaleType());
        if (snapshotImageView.getScaleType() == ImageView.ScaleType.MATRIX) {
            imageView.setImageMatrix(snapshotImageView.getImageMatrix());
        }
        updateImageViewAfterScaleTypeChange(imageView);
    }

    private void restoreImageViewScale() {
        if (this.mSavedScaleType != null) {
            ImageView imageView = this.mViewHolder.mImageView;
            imageView.setScaleType(this.mSavedScaleType);
            if (this.mSavedScaleType == ImageView.ScaleType.MATRIX) {
                imageView.setImageMatrix(this.mSavedMatrix);
            }
            this.mSavedScaleType = null;
            updateImageViewAfterScaleTypeChange(imageView);
        }
    }

    @Override // androidx.core.app.SharedElementCallback
    public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
        if (sharedElements.size() < 1) {
            return;
        }
        View overviewView = sharedElements.get(0);
        DetailsOverviewRowPresenter.ViewHolder viewHolder = this.mViewHolder;
        if (viewHolder == null || viewHolder.mOverviewFrame != overviewView) {
            return;
        }
        View snapshot = sharedElementSnapshots.get(0);
        if (hasImageViewScaleChange(snapshot)) {
            saveImageViewScale();
            changeImageViewScale(snapshot);
        }
        View imageView = this.mViewHolder.mImageView;
        int width = overviewView.getWidth();
        int height = overviewView.getHeight();
        imageView.measure(View.MeasureSpec.makeMeasureSpec(width, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(height, BasicMeasure.EXACTLY));
        imageView.layout(0, 0, width, height);
        View rightPanel = this.mViewHolder.mRightPanel;
        int i = this.mRightPanelWidth;
        if (i != 0 && this.mRightPanelHeight != 0) {
            rightPanel.measure(View.MeasureSpec.makeMeasureSpec(i, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(this.mRightPanelHeight, BasicMeasure.EXACTLY));
            rightPanel.layout(width, rightPanel.getTop(), this.mRightPanelWidth + width, rightPanel.getTop() + this.mRightPanelHeight);
        } else {
            rightPanel.offsetLeftAndRight(width - rightPanel.getLeft());
        }
        this.mViewHolder.mActionsRow.setVisibility(4);
        this.mViewHolder.mDetailsDescriptionFrame.setVisibility(4);
    }

    @Override // androidx.core.app.SharedElementCallback
    public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
        if (sharedElements.size() < 1) {
            return;
        }
        View overviewView = sharedElements.get(0);
        DetailsOverviewRowPresenter.ViewHolder viewHolder = this.mViewHolder;
        if (viewHolder == null || viewHolder.mOverviewFrame != overviewView) {
            return;
        }
        restoreImageViewScale();
        this.mViewHolder.mActionsRow.setDescendantFocusability(131072);
        this.mViewHolder.mActionsRow.setVisibility(0);
        this.mViewHolder.mActionsRow.setDescendantFocusability(262144);
        this.mViewHolder.mActionsRow.requestFocus();
        this.mViewHolder.mDetailsDescriptionFrame.setVisibility(0);
    }

    void setSharedElementEnterTransition(Activity activity, String sharedElementName, long timeoutMs) {
        if ((activity == null && !TextUtils.isEmpty(sharedElementName)) || (activity != null && TextUtils.isEmpty(sharedElementName))) {
            throw new IllegalArgumentException();
        }
        if (activity == this.mActivityToRunTransition && TextUtils.equals(sharedElementName, this.mSharedElementName)) {
            return;
        }
        Activity activity2 = this.mActivityToRunTransition;
        if (activity2 != null) {
            ActivityCompat.setEnterSharedElementCallback(activity2, null);
        }
        this.mActivityToRunTransition = activity;
        this.mSharedElementName = sharedElementName;
        ActivityCompat.setEnterSharedElementCallback(activity, this);
        ActivityCompat.postponeEnterTransition(this.mActivityToRunTransition);
        if (timeoutMs > 0) {
            new Handler().postDelayed(new TransitionTimeOutRunnable(this), timeoutMs);
        }
    }

    void onBindToDrawable(DetailsOverviewRowPresenter.ViewHolder vh) {
        DetailsOverviewRowPresenter.ViewHolder viewHolder = this.mViewHolder;
        if (viewHolder != null) {
            ViewCompat.setTransitionName(viewHolder.mOverviewFrame, null);
        }
        this.mViewHolder = vh;
        vh.mRightPanel.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: androidx.leanback.widget.DetailsOverviewSharedElementHelper.1
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                DetailsOverviewSharedElementHelper.this.mViewHolder.mRightPanel.removeOnLayoutChangeListener(this);
                DetailsOverviewSharedElementHelper detailsOverviewSharedElementHelper = DetailsOverviewSharedElementHelper.this;
                detailsOverviewSharedElementHelper.mRightPanelWidth = detailsOverviewSharedElementHelper.mViewHolder.mRightPanel.getWidth();
                DetailsOverviewSharedElementHelper detailsOverviewSharedElementHelper2 = DetailsOverviewSharedElementHelper.this;
                detailsOverviewSharedElementHelper2.mRightPanelHeight = detailsOverviewSharedElementHelper2.mViewHolder.mRightPanel.getHeight();
            }
        });
        this.mViewHolder.mRightPanel.postOnAnimation(new Runnable() { // from class: androidx.leanback.widget.DetailsOverviewSharedElementHelper.2
            @Override // java.lang.Runnable
            public void run() {
                ViewCompat.setTransitionName(DetailsOverviewSharedElementHelper.this.mViewHolder.mOverviewFrame, DetailsOverviewSharedElementHelper.this.mSharedElementName);
                Object transition = TransitionHelper.getSharedElementEnterTransition(DetailsOverviewSharedElementHelper.this.mActivityToRunTransition.getWindow());
                if (transition != null) {
                    TransitionHelper.addTransitionListener(transition, new TransitionListener() { // from class: androidx.leanback.widget.DetailsOverviewSharedElementHelper.2.1
                        @Override // androidx.leanback.transition.TransitionListener
                        public void onTransitionEnd(Object transition2) {
                            if (DetailsOverviewSharedElementHelper.this.mViewHolder.mActionsRow.isFocused()) {
                                DetailsOverviewSharedElementHelper.this.mViewHolder.mActionsRow.requestFocus();
                            }
                            TransitionHelper.removeTransitionListener(transition2, this);
                        }
                    });
                }
                DetailsOverviewSharedElementHelper.this.startPostponedEnterTransition();
            }
        });
    }

    void startPostponedEnterTransition() {
        if (!this.mStartedPostpone) {
            ActivityCompat.startPostponedEnterTransition(this.mActivityToRunTransition);
            this.mStartedPostpone = true;
        }
    }
}
