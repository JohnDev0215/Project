package com.android.launcher3;

import android.animation.AnimatorSet;
import android.animation.FloatArrayEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.android.launcher3.DropTarget;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.dragndrop.DragController;
import com.android.launcher3.dragndrop.DragLayer;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.util.Themes;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ButtonDropTarget extends AppCompatTextView implements DropTarget, DragController.DragListener, View.OnClickListener {
    private static final int DRAG_VIEW_DROP_DURATION = 285;
    public static final int TOOLTIP_DEFAULT = 0;
    public static final int TOOLTIP_LEFT = 1;
    public static final int TOOLTIP_RIGHT = 2;
    private boolean mAccessibleDrag;
    protected boolean mActive;
    private int mBottomDragPadding;
    private AnimatorSet mCurrentColorAnim;
    ColorMatrix mCurrentFilter;
    private int mDragDistanceThreshold;
    protected Drawable mDrawable;
    protected DropTargetBar mDropTargetBar;
    ColorMatrix mDstFilter;
    protected int mHoverColor;
    protected Launcher mLauncher;
    protected ColorStateList mOriginalTextColor;
    ColorMatrix mSrcFilter;
    protected CharSequence mText;
    private boolean mTextVisible;
    private PopupWindow mToolTip;
    private int mToolTipLocation;
    private static final Property<ButtonDropTarget, Integer> TEXT_COLOR = new Property<ButtonDropTarget, Integer>(Integer.TYPE, "textColor") { // from class: com.android.launcher3.ButtonDropTarget.1
        @Override // android.util.Property
        public Integer get(ButtonDropTarget target) {
            return Integer.valueOf(target.getTextColor());
        }

        @Override // android.util.Property
        public void set(ButtonDropTarget target, Integer value) {
            target.setTextColor(value.intValue());
        }
    };
    private static final int[] sTempCords = new int[2];

    public abstract void completeDrop(DropTarget.DragObject d);

    public abstract int getAccessibilityAction();

    public abstract LauncherLogProto.Target getDropTargetForLogging();

    public abstract void onAccessibilityDrop(View view, ItemInfo item);

    @Override // com.android.launcher3.DropTarget
    public void onDragOver(DropTarget.DragObject d) {
    }

    @Override // com.android.launcher3.DropTarget
    public void prepareAccessibilityDrop() {
    }

    public abstract boolean supportsAccessibilityDrop(ItemInfo info, View view);

    protected abstract boolean supportsDrop(ItemInfo info);

    public ButtonDropTarget(Context context) {
        super(context);
        this.mHoverColor = 0;
        this.mTextVisible = true;
    }

    public ButtonDropTarget(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.mHoverColor = 0;
        this.mTextVisible = true;
    }

    public ButtonDropTarget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mHoverColor = 0;
        this.mTextVisible = true;
        this.mLauncher = Launcher.getLauncher(context);
        Resources resources = getResources();
        this.mBottomDragPadding = resources.getDimensionPixelSize(R.dimen.drop_target_drag_padding);
        this.mDragDistanceThreshold = resources.getDimensionPixelSize(R.dimen.drag_distanceThreshold);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mText = getText();
        this.mOriginalTextColor = getTextColors();
        setContentDescription(this.mText);
    }

    protected void updateText(int resId) {
        setText(resId);
        CharSequence text = getText();
        this.mText = text;
        setContentDescription(text);
    }

    protected void setDrawable(int resId) {
        if (this.mTextVisible) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(resId, 0, 0, 0);
            this.mDrawable = getCompoundDrawablesRelative()[0];
        } else {
            setCompoundDrawablesRelativeWithIntrinsicBounds(0, resId, 0, 0);
            this.mDrawable = getCompoundDrawablesRelative()[1];
        }
    }

    public void setDropTargetBar(DropTargetBar dropTargetBar) {
        this.mDropTargetBar = dropTargetBar;
    }

    private void hideTooltip() {
        PopupWindow popupWindow = this.mToolTip;
        if (popupWindow != null) {
            popupWindow.dismiss();
            this.mToolTip = null;
        }
    }

    @Override // com.android.launcher3.DropTarget
    public final void onDragEnter(DropTarget.DragObject d) {
        int i;
        if (!d.accessibleDrag && !this.mTextVisible) {
            hideTooltip();
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.drop_target_tool_tip, (ViewGroup) null);
            textView.setText(this.mText);
            this.mToolTip = new PopupWindow(textView, -2, -2);
            int measuredWidth = 0;
            if (this.mToolTipLocation != 0) {
                i = -getMeasuredHeight();
                textView.measure(0, 0);
                if (this.mToolTipLocation == 1) {
                    measuredWidth = (-getMeasuredWidth()) - (textView.getMeasuredWidth() / 2);
                } else {
                    measuredWidth = (getMeasuredWidth() / 2) + (textView.getMeasuredWidth() / 2);
                }
            } else {
                i = 0;
            }
            this.mToolTip.showAsDropDown(this, measuredWidth, i);
        }
        d.dragView.setColor(this.mHoverColor);
        animateTextColor(this.mHoverColor);
        if (d.stateAnnouncer != null) {
            d.stateAnnouncer.cancel();
        }
        sendAccessibilityEvent(4);
    }

    protected void resetHoverColor() {
        animateTextColor(this.mOriginalTextColor.getDefaultColor());
    }

    private void animateTextColor(int targetColor) {
        AnimatorSet animatorSet = this.mCurrentColorAnim;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mCurrentColorAnim = animatorSet2;
        animatorSet2.setDuration(120L);
        if (this.mSrcFilter == null) {
            this.mSrcFilter = new ColorMatrix();
            this.mDstFilter = new ColorMatrix();
            this.mCurrentFilter = new ColorMatrix();
        }
        int defaultColor = this.mOriginalTextColor.getDefaultColor();
        Themes.setColorChangeOnMatrix(defaultColor, getTextColor(), this.mSrcFilter);
        Themes.setColorChangeOnMatrix(defaultColor, targetColor, this.mDstFilter);
        ValueAnimator valueAnimatorOfObject = ValueAnimator.ofObject(new FloatArrayEvaluator(this.mCurrentFilter.getArray()), this.mSrcFilter.getArray(), this.mDstFilter.getArray());
        valueAnimatorOfObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.-$$Lambda$ButtonDropTarget$3jwaL7j4LaQvGi02WtL2s8r2V7U
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$animateTextColor$0$ButtonDropTarget(valueAnimator);
            }
        });
        this.mCurrentColorAnim.play(valueAnimatorOfObject);
        this.mCurrentColorAnim.play(ObjectAnimator.ofArgb(this, TEXT_COLOR, targetColor));
        this.mCurrentColorAnim.start();
    }

    public /* synthetic */ void lambda$animateTextColor$0$ButtonDropTarget(ValueAnimator valueAnimator) {
        this.mDrawable.setColorFilter(new ColorMatrixColorFilter(this.mCurrentFilter));
        invalidate();
    }

    @Override // com.android.launcher3.DropTarget
    public final void onDragExit(DropTarget.DragObject d) {
        hideTooltip();
        if (!d.dragComplete) {
            d.dragView.setColor(0);
            resetHoverColor();
        } else {
            d.dragView.setColor(this.mHoverColor);
        }
    }

    @Override // com.android.launcher3.dragndrop.DragController.DragListener
    public void onDragStart(DropTarget.DragObject dragObject, DragOptions options) {
        this.mActive = supportsDrop(dragObject.dragInfo);
        this.mDrawable.setColorFilter(null);
        AnimatorSet animatorSet = this.mCurrentColorAnim;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.mCurrentColorAnim = null;
        }
        setTextColor(this.mOriginalTextColor);
        setVisibility(this.mActive ? 0 : 8);
        boolean z = options.isAccessibleDrag;
        this.mAccessibleDrag = z;
        setOnClickListener(z ? this : null);
    }

    @Override // com.android.launcher3.DropTarget
    public final boolean acceptDrop(DropTarget.DragObject dragObject) {
        return supportsDrop(dragObject.dragInfo);
    }

    @Override // com.android.launcher3.DropTarget
    public boolean isDropEnabled() {
        return this.mActive && (this.mAccessibleDrag || this.mLauncher.getDragController().getDistanceDragged() >= ((float) this.mDragDistanceThreshold));
    }

    @Override // com.android.launcher3.dragndrop.DragController.DragListener
    public void onDragEnd() {
        this.mActive = false;
        setOnClickListener(null);
    }

    @Override // com.android.launcher3.DropTarget
    public void onDrop(final DropTarget.DragObject d, final DragOptions options) {
        if (options.isFlingToDelete) {
            return;
        }
        DragLayer dragLayer = this.mLauncher.getDragLayer();
        Rect rect = new Rect();
        dragLayer.getViewRectRelativeToSelf(d.dragView, rect);
        this.mDropTargetBar.deferOnDragEnd();
        dragLayer.animateView(d.dragView, rect, getIconRect(d), r6.width() / rect.width(), 1.0f, 1.0f, 0.1f, 0.1f, 285, Interpolators.DEACCEL_2, Interpolators.LINEAR, new Runnable() { // from class: com.android.launcher3.-$$Lambda$ButtonDropTarget$QcVXwDS7dKTJkIco-SxdUYpb6lM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDrop$1$ButtonDropTarget(d);
            }
        }, 0, null);
    }

    public /* synthetic */ void lambda$onDrop$1$ButtonDropTarget(DropTarget.DragObject dragObject) {
        completeDrop(dragObject);
        this.mDropTargetBar.onDragEnd();
        this.mLauncher.getStateManager().goToState(LauncherState.NORMAL);
    }

    @Override // com.android.launcher3.DropTarget
    public void getHitRectRelativeToDragLayer(Rect outRect) {
        super.getHitRect(outRect);
        outRect.bottom += this.mBottomDragPadding;
        int[] iArr = sTempCords;
        iArr[1] = 0;
        iArr[0] = 0;
        this.mLauncher.getDragLayer().getDescendantCoordRelativeToSelf((View) this, iArr);
        outRect.offsetTo(iArr[0], iArr[1]);
    }

    public Rect getIconRect(DropTarget.DragObject dragObject) {
        int paddingLeft;
        int paddingRight;
        int measuredWidth = dragObject.dragView.getMeasuredWidth();
        int measuredHeight = dragObject.dragView.getMeasuredHeight();
        int intrinsicWidth = this.mDrawable.getIntrinsicWidth();
        int intrinsicHeight = this.mDrawable.getIntrinsicHeight();
        DragLayer dragLayer = this.mLauncher.getDragLayer();
        Rect rect = new Rect();
        dragLayer.getViewRectRelativeToSelf(this, rect);
        if (Utilities.isRtl(getResources())) {
            paddingRight = rect.right - getPaddingRight();
            paddingLeft = paddingRight - intrinsicWidth;
        } else {
            paddingLeft = getPaddingLeft() + rect.left;
            paddingRight = paddingLeft + intrinsicWidth;
        }
        int measuredHeight2 = rect.top + ((getMeasuredHeight() - intrinsicHeight) / 2);
        rect.set(paddingLeft, measuredHeight2, paddingRight, measuredHeight2 + intrinsicHeight);
        rect.offset((-(measuredWidth - intrinsicWidth)) / 2, (-(measuredHeight - intrinsicHeight)) / 2);
        return rect;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        this.mLauncher.getAccessibilityDelegate().handleAccessibleDrop(this, null, null);
    }

    public int getTextColor() {
        return getTextColors().getDefaultColor();
    }

    public void setTextVisible(boolean isVisible) {
        String str = isVisible ? this.mText : "";
        if (this.mTextVisible == isVisible && TextUtils.equals(str, getText())) {
            return;
        }
        this.mTextVisible = isVisible;
        setText(str);
        if (this.mTextVisible) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(this.mDrawable, (Drawable) null, (Drawable) null, (Drawable) null);
        } else {
            setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, this.mDrawable, (Drawable) null, (Drawable) null);
        }
    }

    public void setToolTipLocation(int location) {
        this.mToolTipLocation = location;
        hideTooltip();
    }

    public boolean isTextTruncated(int availableWidth) {
        return !this.mText.equals(TextUtils.ellipsize(this.mText, getPaint(), availableWidth - (((getPaddingLeft() + getPaddingRight()) + this.mDrawable.getIntrinsicWidth()) + getCompoundDrawablePadding()), TextUtils.TruncateAt.END));
    }
}
