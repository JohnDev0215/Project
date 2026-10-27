package com.android.launcher3.folder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.Launcher;
import com.android.launcher3.Utilities;
import com.android.launcher3.WorkspaceItemInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PreviewItemManager {
    private static final int FINAL_ITEM_ANIMATION_DURATION = 200;
    static final int INITIAL_ITEM_ANIMATION_DURATION = 350;
    private static final int ITEM_SLIDE_IN_OUT_DISTANCE_PX = 200;
    private static final int SLIDE_IN_FIRST_PAGE_ANIMATION_DURATION = 300;
    private static final int SLIDE_IN_FIRST_PAGE_ANIMATION_DURATION_DELAY = 100;
    private FolderIcon mIcon;
    private boolean mShouldSlideInFirstPage;
    private float mIntrinsicIconSize = -1.0f;
    private int mTotalWidth = -1;
    private int mPrevTopPadding = -1;
    private Drawable mReferenceDrawable = null;
    private ArrayList<PreviewItemDrawingParams> mFirstPageParams = new ArrayList<>();
    private ArrayList<PreviewItemDrawingParams> mCurrentPageParams = new ArrayList<>();
    private float mCurrentPageItemsTransX = 0.0f;

    public PreviewItemManager(FolderIcon icon) {
        this.mIcon = icon;
    }

    public FolderPreviewItemAnim createFirstItemAnimation(final boolean reverse, final Runnable onCompleteRunnable) {
        if (reverse) {
            return new FolderPreviewItemAnim(this, this.mFirstPageParams.get(0), 0, 2, -1, -1, 200, onCompleteRunnable);
        }
        return new FolderPreviewItemAnim(this, this.mFirstPageParams.get(0), -1, -1, 0, 2, 350, onCompleteRunnable);
    }

    Drawable prepareCreateAnimation(final View destView) {
        Drawable drawable = ((TextView) destView).getCompoundDrawables()[1];
        computePreviewDrawingParams(drawable.getIntrinsicWidth(), destView.getMeasuredWidth());
        this.mReferenceDrawable = drawable;
        return drawable;
    }

    public void recomputePreviewDrawingParams() {
        Drawable drawable = this.mReferenceDrawable;
        if (drawable != null) {
            computePreviewDrawingParams(drawable.getIntrinsicWidth(), this.mIcon.getMeasuredWidth());
        }
    }

    private void computePreviewDrawingParams(int drawableSize, int totalSize) {
        float f = drawableSize;
        if (this.mIntrinsicIconSize == f && this.mTotalWidth == totalSize && this.mPrevTopPadding == this.mIcon.getPaddingTop()) {
            return;
        }
        this.mIntrinsicIconSize = f;
        this.mTotalWidth = totalSize;
        this.mPrevTopPadding = this.mIcon.getPaddingTop();
        PreviewBackground previewBackground = this.mIcon.mBackground;
        Launcher launcher = this.mIcon.mLauncher;
        Launcher launcher2 = this.mIcon.mLauncher;
        FolderIcon folderIcon = this.mIcon;
        previewBackground.setup(launcher, launcher2, folderIcon, this.mTotalWidth, folderIcon.getPaddingTop());
        this.mIcon.mPreviewLayoutRule.init(this.mIcon.mBackground.previewSize, this.mIntrinsicIconSize, Utilities.isRtl(this.mIcon.getResources()));
        updatePreviewItems(false);
    }

    PreviewItemDrawingParams computePreviewItemDrawingParams(int index, int curNumItems, PreviewItemDrawingParams params) {
        if (index == -1) {
            return getFinalIconParams(params);
        }
        return this.mIcon.mPreviewLayoutRule.computePreviewItemDrawingParams(index, curNumItems, params);
    }

    private PreviewItemDrawingParams getFinalIconParams(PreviewItemDrawingParams params) {
        float f = this.mIcon.mLauncher.getDeviceProfile().iconSizePx;
        float intrinsicWidth = f / this.mReferenceDrawable.getIntrinsicWidth();
        float f2 = (this.mIcon.mBackground.previewSize - f) / 2.0f;
        params.update(f2, f2, intrinsicWidth);
        return params;
    }

    public void drawParams(Canvas canvas, ArrayList<PreviewItemDrawingParams> params, float transX) {
        canvas.translate(transX, 0.0f);
        for (int size = params.size() - 1; size >= 0; size--) {
            PreviewItemDrawingParams previewItemDrawingParams = params.get(size);
            if (!previewItemDrawingParams.hidden) {
                drawPreviewItem(canvas, previewItemDrawingParams);
            }
        }
        canvas.translate(-transX, 0.0f);
    }

    public void draw(Canvas canvas) {
        float f;
        PreviewBackground folderBackground = this.mIcon.getFolderBackground();
        canvas.translate(folderBackground.basePreviewOffsetX, folderBackground.basePreviewOffsetY);
        if (this.mShouldSlideInFirstPage) {
            drawParams(canvas, this.mCurrentPageParams, this.mCurrentPageItemsTransX);
            f = this.mCurrentPageItemsTransX - 200.0f;
        } else {
            f = 0.0f;
        }
        drawParams(canvas, this.mFirstPageParams, f);
        canvas.translate(-folderBackground.basePreviewOffsetX, -folderBackground.basePreviewOffsetY);
    }

    public void onParamsChanged() {
        this.mIcon.invalidate();
    }

    private void drawPreviewItem(Canvas canvas, PreviewItemDrawingParams params) {
        canvas.save();
        canvas.translate(params.transX, params.transY);
        canvas.scale(params.scale, params.scale);
        Drawable drawable = params.drawable;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            canvas.save();
            canvas.translate(-bounds.left, -bounds.top);
            canvas.scale(this.mIntrinsicIconSize / bounds.width(), this.mIntrinsicIconSize / bounds.height());
            drawable.draw(canvas);
            canvas.restore();
        }
        canvas.restore();
    }

    public void hidePreviewItem(int index, boolean hidden) {
        int iMax = index + Math.max(this.mFirstPageParams.size() - 9, 0);
        PreviewItemDrawingParams previewItemDrawingParams = iMax < this.mFirstPageParams.size() ? this.mFirstPageParams.get(iMax) : null;
        if (previewItemDrawingParams != null) {
            previewItemDrawingParams.hidden = hidden;
        }
    }

    void buildParamsForPage(int page, ArrayList<PreviewItemDrawingParams> params, boolean animate) {
        char c;
        PreviewItemManager previewItemManager = this;
        List<BubbleTextView> previewItemsOnPage = previewItemManager.mIcon.getPreviewItemsOnPage(page);
        int size = params.size();
        while (true) {
            c = 1;
            if (previewItemsOnPage.size() >= params.size()) {
                break;
            } else {
                params.remove(params.size() - 1);
            }
        }
        while (previewItemsOnPage.size() > params.size()) {
            params.add(new PreviewItemDrawingParams(0.0f, 0.0f, 0.0f, 0.0f));
        }
        int size2 = page == 0 ? previewItemsOnPage.size() : 9;
        int i = 0;
        while (i < params.size()) {
            PreviewItemDrawingParams previewItemDrawingParams = params.get(i);
            previewItemDrawingParams.drawable = previewItemsOnPage.get(i).getCompoundDrawables()[c];
            if (previewItemDrawingParams.drawable != null && !previewItemManager.mIcon.mFolder.isOpen()) {
                previewItemDrawingParams.drawable.setCallback(previewItemManager.mIcon);
            }
            if (!animate) {
                previewItemManager.computePreviewItemDrawingParams(i, size2, previewItemDrawingParams);
                if (previewItemManager.mReferenceDrawable == null) {
                    previewItemManager.mReferenceDrawable = previewItemDrawingParams.drawable;
                }
            } else {
                FolderPreviewItemAnim folderPreviewItemAnim = new FolderPreviewItemAnim(this, previewItemDrawingParams, i, size, i, size2, 400, null);
                if (previewItemDrawingParams.anim != null) {
                    if (!previewItemDrawingParams.anim.hasEqualFinalState(folderPreviewItemAnim)) {
                        previewItemDrawingParams.anim.cancel();
                        previewItemDrawingParams.anim = folderPreviewItemAnim;
                        previewItemDrawingParams.anim.start();
                    }
                } else {
                    previewItemDrawingParams.anim = folderPreviewItemAnim;
                    previewItemDrawingParams.anim.start();
                }
            }
            i++;
            c = 1;
            previewItemManager = this;
        }
    }

    void onFolderClose(int currentPage) {
        boolean z = currentPage != 0;
        this.mShouldSlideInFirstPage = z;
        if (z) {
            this.mCurrentPageItemsTransX = 0.0f;
            buildParamsForPage(currentPage, this.mCurrentPageParams, false);
            onParamsChanged();
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 200.0f);
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.folder.PreviewItemManager.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PreviewItemManager.this.mCurrentPageItemsTransX = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    PreviewItemManager.this.onParamsChanged();
                }
            });
            valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.folder.PreviewItemManager.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    PreviewItemManager.this.mCurrentPageParams.clear();
                }
            });
            valueAnimatorOfFloat.setStartDelay(100L);
            valueAnimatorOfFloat.setDuration(300L);
            valueAnimatorOfFloat.start();
        }
    }

    void updatePreviewItems(boolean animate) {
        buildParamsForPage(0, this.mFirstPageParams, animate);
    }

    boolean verifyDrawable(Drawable who) {
        for (int i = 0; i < this.mFirstPageParams.size(); i++) {
            if (this.mFirstPageParams.get(i).drawable == who) {
                return true;
            }
        }
        return false;
    }

    float getIntrinsicIconSize() {
        return this.mIntrinsicIconSize;
    }

    public void onDrop(List<BubbleTextView> oldParams, List<BubbleTextView> newParams, WorkspaceItemInfo dropped) {
        int size = newParams.size();
        ArrayList<PreviewItemDrawingParams> arrayList = this.mFirstPageParams;
        buildParamsForPage(0, arrayList, false);
        ArrayList arrayList2 = new ArrayList();
        for (BubbleTextView bubbleTextView : newParams) {
            if (!oldParams.contains(bubbleTextView) && !bubbleTextView.getTag().equals(dropped)) {
                arrayList2.add(bubbleTextView);
            }
        }
        for (int i = 0; i < arrayList2.size(); i++) {
            int iIndexOf = newParams.indexOf(arrayList2.get(i));
            PreviewItemDrawingParams previewItemDrawingParams = arrayList.get(iIndexOf);
            computePreviewItemDrawingParams(iIndexOf, size, previewItemDrawingParams);
            updateTransitionParam(previewItemDrawingParams, (BubbleTextView) arrayList2.get(i), -3, newParams.indexOf(arrayList2.get(i)), size);
        }
        for (int i2 = 0; i2 < newParams.size(); i2++) {
            int iIndexOf2 = oldParams.indexOf(newParams.get(i2));
            if (iIndexOf2 >= 0 && i2 != iIndexOf2) {
                updateTransitionParam(arrayList.get(i2), newParams.get(i2), iIndexOf2, i2, size);
            }
        }
        ArrayList arrayList3 = new ArrayList(oldParams);
        arrayList3.removeAll(newParams);
        for (int i3 = 0; i3 < arrayList3.size(); i3++) {
            BubbleTextView bubbleTextView2 = (BubbleTextView) arrayList3.get(i3);
            int iIndexOf3 = oldParams.indexOf(bubbleTextView2);
            PreviewItemDrawingParams previewItemDrawingParamsComputePreviewItemDrawingParams = computePreviewItemDrawingParams(iIndexOf3, size, null);
            updateTransitionParam(previewItemDrawingParamsComputePreviewItemDrawingParams, bubbleTextView2, iIndexOf3, -2, size);
            arrayList.add(0, previewItemDrawingParamsComputePreviewItemDrawingParams);
        }
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            if (arrayList.get(i4).anim != null) {
                arrayList.get(i4).anim.start();
            }
        }
    }

    private void updateTransitionParam(final PreviewItemDrawingParams p, BubbleTextView btv, int prevIndex, int newIndex, int numItems) {
        p.drawable = btv.getCompoundDrawables()[1];
        if (!this.mIcon.mFolder.isOpen()) {
            p.drawable.setCallback(this.mIcon);
        }
        FolderPreviewItemAnim folderPreviewItemAnim = new FolderPreviewItemAnim(this, p, prevIndex, numItems, newIndex, numItems, 400, null);
        if (p.anim != null && !p.anim.hasEqualFinalState(folderPreviewItemAnim)) {
            p.anim.cancel();
        }
        p.anim = folderPreviewItemAnim;
    }
}
