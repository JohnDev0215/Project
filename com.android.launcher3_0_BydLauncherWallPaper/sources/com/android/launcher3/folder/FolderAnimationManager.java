package com.android.launcher3.folder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.util.Property;
import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.core.graphics.ColorUtils;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.CellLayout;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAnimUtils;
import com.android.launcher3.R;
import com.android.launcher3.ResourceUtils;
import com.android.launcher3.ShortcutAndWidgetContainer;
import com.android.launcher3.Utilities;
import com.android.launcher3.anim.PropertyResetListener;
import com.android.launcher3.graphics.IconShape;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.launcher3.util.Themes;
import com.android.launcher3.views.BaseDragLayer;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class FolderAnimationManager {
    private FolderPagedView mContent;
    private Context mContext;
    private final int mDelay;
    private final int mDuration;
    private Folder mFolder;
    private GradientDrawable mFolderBackground;
    private FolderIcon mFolderIcon;
    private final TimeInterpolator mFolderInterpolator;
    private final boolean mIsOpening;
    private final TimeInterpolator mLargeFolderPreviewItemCloseInterpolator;
    private final TimeInterpolator mLargeFolderPreviewItemOpenInterpolator;
    private Launcher mLauncher;
    private PreviewBackground mPreviewBackground;
    private final PreviewItemDrawingParams mTmpParams = new PreviewItemDrawingParams(0.0f, 0.0f, 0.0f, 0.0f);

    public FolderAnimationManager(Folder folder, boolean isOpening) {
        this.mFolder = folder;
        this.mContent = folder.mContent;
        this.mFolderBackground = (GradientDrawable) this.mFolder.getBackground();
        FolderIcon folderIcon = folder.mFolderIcon;
        this.mFolderIcon = folderIcon;
        this.mPreviewBackground = folderIcon.mBackground;
        this.mContext = folder.getContext();
        this.mLauncher = folder.mLauncher;
        this.mIsOpening = isOpening;
        Resources resources = this.mContent.getResources();
        this.mDuration = resources.getInteger(R.integer.config_materialFolderExpandDuration);
        this.mDelay = resources.getInteger(R.integer.config_folderDelay);
        this.mFolderInterpolator = AnimationUtils.loadInterpolator(this.mContext, R.interpolator.folder_interpolator);
        this.mLargeFolderPreviewItemOpenInterpolator = AnimationUtils.loadInterpolator(this.mContext, R.interpolator.large_folder_preview_item_open_interpolator);
        this.mLargeFolderPreviewItemCloseInterpolator = AnimationUtils.loadInterpolator(this.mContext, R.interpolator.large_folder_preview_item_close_interpolator);
    }

    public AnimatorSet getAnimator() {
        BaseDragLayer.LayoutParams layoutParams = (BaseDragLayer.LayoutParams) this.mFolder.getLayoutParams();
        ClippedFolderIconLayoutRule layoutRule = this.mFolderIcon.getLayoutRule();
        List<BubbleTextView> previewItems = this.mFolderIcon.getPreviewItems();
        Rect rect = new Rect();
        float descendantRectRelativeToSelf = this.mLauncher.getDragLayer().getDescendantRectRelativeToSelf(this.mFolderIcon, rect);
        int scaledRadius = this.mPreviewBackground.getScaledRadius();
        float f = scaledRadius * 2 * descendantRectRelativeToSelf;
        float iconSize = layoutRule.getIconSize() * layoutRule.scaleForItem(previewItems.size());
        float iconSize2 = (iconSize / previewItems.get(0).getIconSize()) * descendantRectRelativeToSelf;
        float f2 = this.mIsOpening ? iconSize2 : 1.0f;
        this.mFolder.setScaleX(f2);
        this.mFolder.setScaleY(f2);
        this.mFolder.setPivotX(0.0f);
        this.mFolder.setPivotY(0.0f);
        int i = (int) (iconSize / 2.0f);
        if (Utilities.isRtl(this.mContext.getResources())) {
            i = (int) (((layoutParams.width * iconSize2) - f) - i);
        }
        int i2 = i;
        int paddingLeft = (int) ((this.mFolder.getPaddingLeft() + this.mContent.getPaddingLeft()) * iconSize2);
        int paddingTop = (int) ((this.mFolder.getPaddingTop() + this.mContent.getPaddingTop()) * iconSize2);
        int offsetX = ((rect.left + this.mPreviewBackground.getOffsetX()) - paddingLeft) - i2;
        int offsetY = (rect.top + this.mPreviewBackground.getOffsetY()) - paddingTop;
        float f3 = offsetX - layoutParams.x;
        float f4 = offsetY - layoutParams.y;
        int alphaComponent = ColorUtils.setAlphaComponent(Themes.getAttrColor(this.mContext, R.attr.folderFillColor), 255);
        int colorAlphaBound = GraphicsUtils.setColorAlphaBound(alphaComponent, this.mPreviewBackground.getBackgroundAlpha());
        this.mFolderBackground.mutate();
        this.mFolderBackground.setColor(this.mIsOpening ? colorAlphaBound : alphaComponent);
        float f5 = paddingLeft + i2;
        float f6 = paddingTop;
        Rect rect2 = new Rect(Math.round(f5 / iconSize2), Math.round(f6 / iconSize2), Math.round((f5 + f) / iconSize2), Math.round((f6 + f) / iconSize2));
        Rect rect3 = new Rect(0, 0, layoutParams.width, layoutParams.height);
        float fPxFromDp = ResourceUtils.pxFromDp(2.0f, this.mContext.getResources().getDisplayMetrics());
        AnimatorSet animatorSet = new AnimatorSet();
        PropertyResetListener propertyResetListener = new PropertyResetListener(BubbleTextView.TEXT_ALPHA_PROPERTY, Float.valueOf(1.0f));
        Folder folder = this.mFolder;
        for (BubbleTextView bubbleTextView : folder.getItemsOnPage(folder.mContent.getCurrentPage())) {
            if (this.mIsOpening) {
                bubbleTextView.setTextVisibility(false);
            }
            ObjectAnimator objectAnimatorCreateTextAlphaAnimator = bubbleTextView.createTextAlphaAnimator(this.mIsOpening);
            objectAnimatorCreateTextAlphaAnimator.addListener(propertyResetListener);
            play(animatorSet, objectAnimatorCreateTextAlphaAnimator);
        }
        play(animatorSet, getAnimator(this.mFolder, View.TRANSLATION_X, f3, 0.0f));
        play(animatorSet, getAnimator(this.mFolder, View.TRANSLATION_Y, f4, 0.0f));
        play(animatorSet, getAnimator(this.mFolder, LauncherAnimUtils.SCALE_PROPERTY, iconSize2, 1.0f));
        play(animatorSet, getAnimator(this.mFolderBackground, "color", colorAlphaBound, alphaComponent));
        play(animatorSet, this.mFolderIcon.mFolderName.createTextAlphaAnimator(!this.mIsOpening));
        play(animatorSet, IconShape.getShape().createRevealAnimator(this.mFolder, rect2, rect3, fPxFromDp, !this.mIsOpening));
        int i3 = this.mDuration / 2;
        play(animatorSet, getAnimator(this.mFolder, View.TRANSLATION_Z, -this.mFolder.getElevation(), 0.0f), this.mIsOpening ? i3 : 0L, i3);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.folder.FolderAnimationManager.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                FolderAnimationManager.this.mFolder.setTranslationX(0.0f);
                FolderAnimationManager.this.mFolder.setTranslationY(0.0f);
                FolderAnimationManager.this.mFolder.setTranslationZ(0.0f);
                FolderAnimationManager.this.mFolder.setScaleX(1.0f);
                FolderAnimationManager.this.mFolder.setScaleY(1.0f);
            }
        });
        Iterator<Animator> it = animatorSet.getChildAnimations().iterator();
        while (it.hasNext()) {
            it.next().setInterpolator(this.mFolderInterpolator);
        }
        int radius = scaledRadius - this.mPreviewBackground.getRadius();
        addPreviewItemAnimators(animatorSet, iconSize2 / descendantRectRelativeToSelf, i2 + radius, radius);
        return animatorSet;
    }

    private void addPreviewItemAnimators(AnimatorSet animatorSet, final float folderScale, int previewItemOffsetX, int previewItemOffsetY) {
        List<BubbleTextView> previewItemsOnPage;
        List<BubbleTextView> list;
        int i;
        List<BubbleTextView> list2;
        ClippedFolderIconLayoutRule layoutRule = this.mFolderIcon.getLayoutRule();
        boolean z = true;
        boolean z2 = this.mFolder.mContent.getCurrentPage() == 0;
        if (z2) {
            previewItemsOnPage = this.mFolderIcon.getPreviewItems();
        } else {
            previewItemsOnPage = this.mFolderIcon.getPreviewItemsOnPage(this.mFolder.mContent.getCurrentPage());
        }
        List<BubbleTextView> list3 = previewItemsOnPage;
        int size = list3.size();
        int i2 = z2 ? size : 9;
        TimeInterpolator previewItemInterpolator = getPreviewItemInterpolator();
        ShortcutAndWidgetContainer shortcutsAndWidgets = this.mContent.getPageAt(0).getShortcutsAndWidgets();
        int i3 = 0;
        while (i3 < size) {
            final BubbleTextView bubbleTextView = list3.get(i3);
            CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) bubbleTextView.getLayoutParams();
            layoutParams.isLockedToGrid = z;
            shortcutsAndWidgets.setupLp(bubbleTextView);
            final float iconSize = ((layoutRule.getIconSize() * layoutRule.scaleForItem(i2)) / list3.get(i3).getIconSize()) / folderScale;
            float f = this.mIsOpening ? iconSize : 1.0f;
            bubbleTextView.setScaleX(f);
            bubbleTextView.setScaleY(f);
            layoutRule.computePreviewItemDrawingParams(i3, i2, this.mTmpParams);
            int iconSize2 = (int) (((this.mTmpParams.transX - (((int) ((layoutParams.width - bubbleTextView.getIconSize()) * r3)) / 2)) + previewItemOffsetX) / folderScale);
            int i4 = (int) ((this.mTmpParams.transY + previewItemOffsetY) / folderScale);
            final float f2 = iconSize2 - layoutParams.x;
            final float f3 = i4 - layoutParams.y;
            Animator animator = getAnimator(bubbleTextView, View.TRANSLATION_X, f2, 0.0f);
            animator.setInterpolator(previewItemInterpolator);
            play(animatorSet, animator);
            int i5 = i3;
            Animator animator2 = getAnimator(bubbleTextView, View.TRANSLATION_Y, f3, 0.0f);
            animator2.setInterpolator(previewItemInterpolator);
            play(animatorSet, animator2);
            ClippedFolderIconLayoutRule clippedFolderIconLayoutRule = layoutRule;
            Animator animator3 = getAnimator(bubbleTextView, LauncherAnimUtils.SCALE_PROPERTY, iconSize, 1.0f);
            animator3.setInterpolator(previewItemInterpolator);
            play(animatorSet, animator3);
            if (this.mFolder.getItemCount() > 9) {
                boolean z3 = this.mIsOpening;
                int i6 = this.mDelay;
                if (!z3) {
                    i6 *= 2;
                }
                if (z3) {
                    list2 = list3;
                    i = size;
                    long j = i6;
                    animator.setStartDelay(j);
                    animator2.setStartDelay(j);
                    animator3.setStartDelay(j);
                } else {
                    list2 = list3;
                    i = size;
                }
                list = list2;
                long j2 = i6;
                animator.setDuration(animator.getDuration() - j2);
                animator2.setDuration(animator2.getDuration() - j2);
                animator3.setDuration(animator3.getDuration() - j2);
            } else {
                list = list3;
                i = size;
            }
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.folder.FolderAnimationManager.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    if (FolderAnimationManager.this.mIsOpening) {
                        bubbleTextView.setTranslationX(f2);
                        bubbleTextView.setTranslationY(f3);
                        bubbleTextView.setScaleX(iconSize);
                        bubbleTextView.setScaleY(iconSize);
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    bubbleTextView.setTranslationX(0.0f);
                    bubbleTextView.setTranslationY(0.0f);
                    bubbleTextView.setScaleX(1.0f);
                    bubbleTextView.setScaleY(1.0f);
                }
            });
            i3 = i5 + 1;
            size = i;
            layoutRule = clippedFolderIconLayoutRule;
            list3 = list;
            z = true;
        }
    }

    private void play(AnimatorSet as, Animator a) {
        play(as, a, a.getStartDelay(), this.mDuration);
    }

    private void play(AnimatorSet as, Animator a, long startDelay, int duration) {
        a.setStartDelay(startDelay);
        a.setDuration(duration);
        as.play(a);
    }

    private TimeInterpolator getPreviewItemInterpolator() {
        if (this.mFolder.getItemCount() > 9) {
            if (this.mIsOpening) {
                return this.mLargeFolderPreviewItemOpenInterpolator;
            }
            return this.mLargeFolderPreviewItemCloseInterpolator;
        }
        return this.mFolderInterpolator;
    }

    private Animator getAnimator(View view, Property property, float v1, float v2) {
        if (this.mIsOpening) {
            return ObjectAnimator.ofFloat(view, (Property<View, Float>) property, v1, v2);
        }
        return ObjectAnimator.ofFloat(view, (Property<View, Float>) property, v2, v1);
    }

    private Animator getAnimator(GradientDrawable drawable, String property, int v1, int v2) {
        if (this.mIsOpening) {
            return ObjectAnimator.ofArgb(drawable, property, v1, v2);
        }
        return ObjectAnimator.ofArgb(drawable, property, v2, v1);
    }
}
