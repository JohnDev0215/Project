package com.github.amlcurran.showcaseview;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.github.amlcurran.showcaseview.AnimationFactory;
import com.github.amlcurran.showcaseview.targets.Target;

/* JADX INFO: loaded from: classes6.dex */
public class ShowcaseView extends RelativeLayout implements View.OnTouchListener, ShowcaseViewApi {
    private static final int HOLO_BLUE = Color.parseColor("#33B5E5");
    private final AnimationFactory animationFactory;
    private int backgroundColor;
    private Bitmap bitmapBuffer;
    private boolean blockAllTouches;
    private boolean blockTouches;
    private long fadeInMillis;
    private long fadeOutMillis;
    private boolean hasAlteredText;
    private boolean hasCustomClickListener;
    private boolean hasNoTarget;
    private View.OnClickListener hideOnClickListener;
    private boolean hideOnTouch;
    private boolean isShowing;
    private Button mEndButton;
    private OnShowcaseEventListener mEventListener;
    private OnDrawEventListener mOnDrawEventListener;
    private final int[] positionInWindow;
    private float scaleMultiplier;
    private final ShotStateStore shotStateStore;
    private boolean shouldCentreText;
    private final ShowcaseAreaCalculator showcaseAreaCalculator;
    private int showcaseColor;
    private ShowcaseDrawer showcaseDrawer;
    private int showcaseX;
    private int showcaseY;
    private final TextDrawer textDrawer;

    protected ShowcaseView(Context context, boolean z) {
        this(context, null, R$styleable.CustomTheme_showcaseViewStyle, z);
    }

    protected ShowcaseView(Context context, AttributeSet attributeSet, int i, boolean z) {
        super(context, attributeSet, i);
        this.showcaseX = -1;
        this.showcaseY = -1;
        this.scaleMultiplier = 1.0f;
        this.hasCustomClickListener = false;
        this.blockTouches = true;
        this.hideOnTouch = false;
        this.mEventListener = OnShowcaseEventListener.NONE;
        this.hasAlteredText = false;
        this.hasNoTarget = false;
        this.positionInWindow = new int[2];
        this.hideOnClickListener = new View.OnClickListener() { // from class: com.github.amlcurran.showcaseview.ShowcaseView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ShowcaseView.this.hide();
            }
        };
        if (new ApiUtils().isCompatWithHoneycomb()) {
            this.animationFactory = new AnimatorAnimationFactory();
        } else {
            this.animationFactory = new NoAnimationFactory();
        }
        this.showcaseAreaCalculator = new ShowcaseAreaCalculator();
        this.shotStateStore = new ShotStateStore(context);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.ShowcaseView, R$attr.showcaseViewStyle, R$style.ShowcaseView);
        this.fadeInMillis = getResources().getInteger(R.integer.config_mediumAnimTime);
        this.fadeOutMillis = getResources().getInteger(R.integer.config_mediumAnimTime);
        this.mEndButton = (Button) LayoutInflater.from(context).inflate(R$layout.showcase_button, (ViewGroup) null);
        if (z) {
            this.showcaseDrawer = new NewShowcaseDrawer(getResources(), context.getTheme());
        } else {
            this.showcaseDrawer = new StandardShowcaseDrawer(getResources(), context.getTheme());
        }
        this.textDrawer = new TextDrawer(getResources(), getContext());
        updateStyle(typedArrayObtainStyledAttributes, false);
        init();
    }

    private void init() {
        setOnTouchListener(this);
        if (this.mEndButton.getParent() == null) {
            int dimension = (int) getResources().getDimension(R$dimen.button_margin);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) generateDefaultLayoutParams();
            layoutParams.addRule(12);
            layoutParams.addRule(11);
            layoutParams.setMargins(dimension, dimension, dimension, dimension);
            this.mEndButton.setLayoutParams(layoutParams);
            this.mEndButton.setText(R.string.ok);
            if (!this.hasCustomClickListener) {
                this.mEndButton.setOnClickListener(this.hideOnClickListener);
            }
            addView(this.mEndButton);
        }
    }

    private boolean hasShot() {
        return this.shotStateStore.hasShot();
    }

    void setShowcasePosition(Point point) {
        setShowcasePosition(point.x, point.y);
    }

    void setShowcasePosition(int i, int i2) {
        if (this.shotStateStore.hasShot()) {
            return;
        }
        getLocationInWindow(this.positionInWindow);
        int[] iArr = this.positionInWindow;
        this.showcaseX = i - iArr[0];
        this.showcaseY = i2 - iArr[1];
        recalculateText();
        invalidate();
    }

    public void setTarget(Target target) {
        setShowcase(target, false);
    }

    public void setShowcase(final Target target, final boolean z) {
        postDelayed(new Runnable() { // from class: com.github.amlcurran.showcaseview.ShowcaseView.1
            @Override // java.lang.Runnable
            public void run() {
                if (ShowcaseView.this.shotStateStore.hasShot()) {
                    return;
                }
                if (ShowcaseView.this.canUpdateBitmap()) {
                    ShowcaseView.this.updateBitmap();
                }
                Point point = target.getPoint();
                if (point != null) {
                    ShowcaseView.this.hasNoTarget = false;
                    if (z) {
                        ShowcaseView.this.animationFactory.animateTargetToPoint(ShowcaseView.this, point);
                        return;
                    } else {
                        ShowcaseView.this.setShowcasePosition(point);
                        return;
                    }
                }
                ShowcaseView.this.hasNoTarget = true;
                ShowcaseView.this.invalidate();
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBitmap() {
        if (this.bitmapBuffer == null || haveBoundsChanged()) {
            Bitmap bitmap = this.bitmapBuffer;
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.bitmapBuffer = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        }
    }

    private boolean haveBoundsChanged() {
        return (getMeasuredWidth() == this.bitmapBuffer.getWidth() && getMeasuredHeight() == this.bitmapBuffer.getHeight()) ? false : true;
    }

    public boolean hasShowcaseView() {
        return (this.showcaseX == 1000000 || this.showcaseY == 1000000 || this.hasNoTarget) ? false : true;
    }

    public void setShowcaseX(int i) {
        setShowcasePosition(i, getShowcaseY());
    }

    public void setShowcaseY(int i) {
        setShowcasePosition(getShowcaseX(), i);
    }

    public int getShowcaseX() {
        getLocationInWindow(this.positionInWindow);
        return this.showcaseX + this.positionInWindow[0];
    }

    public int getShowcaseY() {
        getLocationInWindow(this.positionInWindow);
        return this.showcaseY + this.positionInWindow[1];
    }

    public void overrideButtonClick(View.OnClickListener onClickListener) {
        if (this.shotStateStore.hasShot()) {
            return;
        }
        Button button = this.mEndButton;
        if (button != null) {
            if (onClickListener != null) {
                button.setOnClickListener(onClickListener);
            } else {
                button.setOnClickListener(this.hideOnClickListener);
            }
        }
        this.hasCustomClickListener = true;
    }

    public void setOnShowcaseEventListener(OnShowcaseEventListener onShowcaseEventListener) {
        if (onShowcaseEventListener != null) {
            this.mEventListener = onShowcaseEventListener;
        } else {
            this.mEventListener = OnShowcaseEventListener.NONE;
        }
    }

    public void setButtonText(CharSequence charSequence) {
        Button button = this.mEndButton;
        if (button != null) {
            button.setText(charSequence);
        }
    }

    private void recalculateText() {
        if (this.showcaseAreaCalculator.calculateShowcaseRect((float) this.showcaseX, (float) this.showcaseY, this.showcaseDrawer) || this.hasAlteredText) {
            this.textDrawer.calculateTextPosition(getMeasuredWidth(), getMeasuredHeight(), this.shouldCentreText, hasShowcaseView() ? this.showcaseAreaCalculator.getShowcaseRect() : new Rect());
        }
        this.hasAlteredText = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Bitmap bitmap;
        if (this.showcaseX < 0 || this.showcaseY < 0 || this.shotStateStore.hasShot() || (bitmap = this.bitmapBuffer) == null) {
            super.dispatchDraw(canvas);
            return;
        }
        this.showcaseDrawer.erase(bitmap);
        if (!this.hasNoTarget) {
            this.showcaseDrawer.drawShowcase(this.bitmapBuffer, this.showcaseX, this.showcaseY, this.scaleMultiplier);
            this.showcaseDrawer.drawToCanvas(canvas, this.bitmapBuffer);
        }
        this.textDrawer.draw(canvas);
        OnDrawEventListener onDrawEventListener = this.mOnDrawEventListener;
        if (onDrawEventListener != null) {
            onDrawEventListener.onDraw(this.textDrawer.getBestTextPosition());
        }
        super.dispatchDraw(canvas);
    }

    public void hide() {
        this.shotStateStore.storeShot();
        this.mEventListener.onShowcaseViewHide(this);
        fadeOutShowcase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBitmap() {
        Bitmap bitmap = this.bitmapBuffer;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.bitmapBuffer.recycle();
        this.bitmapBuffer = null;
    }

    private void fadeOutShowcase() {
        this.animationFactory.fadeOutView(this, this.fadeOutMillis, new AnimationFactory.AnimationEndListener() { // from class: com.github.amlcurran.showcaseview.ShowcaseView.2
            @Override // com.github.amlcurran.showcaseview.AnimationFactory.AnimationEndListener
            public void onAnimationEnd() {
                ShowcaseView.this.setVisibility(8);
                ShowcaseView.this.clearBitmap();
                ShowcaseView.this.isShowing = false;
                ShowcaseView.this.mEventListener.onShowcaseViewDidHide(ShowcaseView.this);
            }
        });
    }

    public void show() {
        this.isShowing = true;
        if (canUpdateBitmap()) {
            updateBitmap();
        }
        this.mEventListener.onShowcaseViewShow(this);
        fadeInShowcase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canUpdateBitmap() {
        return getMeasuredHeight() > 0 && getMeasuredWidth() > 0;
    }

    public void setOnDrawListener(OnDrawEventListener onDrawEventListener) {
        this.mOnDrawEventListener = onDrawEventListener;
    }

    private void fadeInShowcase() {
        this.animationFactory.fadeInView(this, this.fadeInMillis, new AnimationFactory.AnimationStartListener() { // from class: com.github.amlcurran.showcaseview.ShowcaseView.3
            @Override // com.github.amlcurran.showcaseview.AnimationFactory.AnimationStartListener
            public void onAnimationStart() {
                ShowcaseView.this.setVisibility(0);
            }
        });
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.blockAllTouches) {
            this.mEventListener.onShowcaseViewTouchBlocked(motionEvent);
            return true;
        }
        double dSqrt = Math.sqrt(Math.pow(Math.abs(motionEvent.getRawX() - this.showcaseX), 2.0d) + Math.pow(Math.abs(motionEvent.getRawY() - this.showcaseY), 2.0d));
        if (1 == motionEvent.getAction() && this.hideOnTouch && dSqrt > this.showcaseDrawer.getBlockedRadius()) {
            hide();
            return true;
        }
        boolean z = this.blockTouches && dSqrt > ((double) this.showcaseDrawer.getBlockedRadius());
        if (z) {
            this.mEventListener.onShowcaseViewTouchBlocked(motionEvent);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void insertShowcaseView(ShowcaseView showcaseView, ViewGroup viewGroup, int i) {
        viewGroup.addView(showcaseView, i);
        if (!showcaseView.hasShot()) {
            showcaseView.show();
        } else {
            showcaseView.hideImmediate();
        }
    }

    private void hideImmediate() {
        this.isShowing = false;
        setVisibility(8);
    }

    public void setContentTitle(CharSequence charSequence) {
        this.textDrawer.setContentTitle(charSequence);
        invalidate();
    }

    public void setContentText(CharSequence charSequence) {
        this.textDrawer.setContentText(charSequence);
        invalidate();
    }

    private void setScaleMultiplier(float f) {
        this.scaleMultiplier = f;
    }

    public static class Builder {
        private final Activity activity;
        private ViewGroup parent;
        private int parentIndex;
        private final ShowcaseView showcaseView;

        public Builder(Activity activity) {
            this(activity, false);
        }

        @Deprecated
        public Builder(Activity activity, boolean z) {
            this.activity = activity;
            ShowcaseView showcaseView = new ShowcaseView(activity, z);
            this.showcaseView = showcaseView;
            showcaseView.setTarget(Target.NONE);
            ViewGroup viewGroup = (ViewGroup) activity.findViewById(R.id.content);
            this.parent = viewGroup;
            this.parentIndex = viewGroup.getChildCount();
        }

        public ShowcaseView build() {
            ShowcaseView.insertShowcaseView(this.showcaseView, this.parent, this.parentIndex);
            return this.showcaseView;
        }

        public Builder setContentTitle(CharSequence charSequence) {
            this.showcaseView.setContentTitle(charSequence);
            return this;
        }

        public Builder setContentText(CharSequence charSequence) {
            this.showcaseView.setContentText(charSequence);
            return this;
        }

        public Builder setTarget(Target target) {
            this.showcaseView.setTarget(target);
            return this;
        }

        public Builder setStyle(int i) {
            this.showcaseView.setStyle(i);
            return this;
        }

        public Builder hideOnTouchOutside() {
            this.showcaseView.setBlocksTouches(true);
            this.showcaseView.setHideOnTouchOutside(true);
            return this;
        }

        public Builder replaceEndButton(Button button) {
            this.showcaseView.setEndButton(button);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEndButton(Button button) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mEndButton.getLayoutParams();
        this.mEndButton.setOnClickListener(null);
        removeView(this.mEndButton);
        this.mEndButton = button;
        button.setOnClickListener(this.hideOnClickListener);
        button.setLayoutParams(layoutParams);
        addView(button);
    }

    private void setShowcaseDrawer(ShowcaseDrawer showcaseDrawer) {
        this.showcaseDrawer = showcaseDrawer;
        showcaseDrawer.setBackgroundColour(this.backgroundColor);
        this.showcaseDrawer.setShowcaseColour(this.showcaseColor);
        this.hasAlteredText = true;
        invalidate();
    }

    private void setContentTitlePaint(TextPaint textPaint) {
        this.textDrawer.setTitlePaint(textPaint);
        this.hasAlteredText = true;
        invalidate();
    }

    private void setContentTextPaint(TextPaint textPaint) {
        this.textDrawer.setContentPaint(textPaint);
        this.hasAlteredText = true;
        invalidate();
    }

    public void setShouldCentreText(boolean z) {
        this.shouldCentreText = z;
        this.hasAlteredText = true;
        invalidate();
    }

    private void setSingleShot(long j) {
        this.shotStateStore.setSingleShot(j);
    }

    public void setButtonPosition(RelativeLayout.LayoutParams layoutParams) {
        this.mEndButton.setLayoutParams(layoutParams);
    }

    public void setDetailTextAlignment(Layout.Alignment alignment) {
        this.textDrawer.setDetailTextAlignment(alignment);
        this.hasAlteredText = true;
        invalidate();
    }

    public void setTitleTextAlignment(Layout.Alignment alignment) {
        this.textDrawer.setTitleTextAlignment(alignment);
        this.hasAlteredText = true;
        invalidate();
    }

    public void forceTextPosition(int i) {
        this.textDrawer.forceTextPosition(i);
        this.hasAlteredText = true;
        invalidate();
    }

    public void setHideOnTouchOutside(boolean z) {
        this.hideOnTouch = z;
    }

    public void setBlocksTouches(boolean z) {
        this.blockTouches = z;
    }

    private void setBlockAllTouches(boolean z) {
        this.blockAllTouches = z;
    }

    @Override // com.github.amlcurran.showcaseview.ShowcaseViewApi
    public void setStyle(int i) {
        updateStyle(getContext().obtainStyledAttributes(i, R$styleable.ShowcaseView), true);
    }

    private void updateStyle(TypedArray typedArray, boolean z) {
        this.backgroundColor = typedArray.getColor(R$styleable.ShowcaseView_sv_backgroundColor, Color.argb(128, 80, 80, 80));
        this.showcaseColor = typedArray.getColor(R$styleable.ShowcaseView_sv_showcaseColor, HOLO_BLUE);
        String string = typedArray.getString(R$styleable.ShowcaseView_sv_buttonText);
        if (TextUtils.isEmpty(string)) {
            string = getResources().getString(R.string.ok);
        }
        boolean z2 = typedArray.getBoolean(R$styleable.ShowcaseView_sv_tintButtonColor, true);
        int resourceId = typedArray.getResourceId(R$styleable.ShowcaseView_sv_titleTextAppearance, R$style.TextAppearance_ShowcaseView_Title);
        int resourceId2 = typedArray.getResourceId(R$styleable.ShowcaseView_sv_detailTextAppearance, R$style.TextAppearance_ShowcaseView_Detail);
        typedArray.recycle();
        this.showcaseDrawer.setShowcaseColour(this.showcaseColor);
        this.showcaseDrawer.setBackgroundColour(this.backgroundColor);
        tintButton(this.showcaseColor, z2);
        this.mEndButton.setText(string);
        this.textDrawer.setTitleStyling(resourceId);
        this.textDrawer.setDetailStyling(resourceId2);
        this.hasAlteredText = true;
        if (z) {
            invalidate();
        }
    }

    private void tintButton(int i, boolean z) {
        if (z) {
            this.mEndButton.getBackground().setColorFilter(i, PorterDuff.Mode.MULTIPLY);
        } else {
            this.mEndButton.getBackground().setColorFilter(HOLO_BLUE, PorterDuff.Mode.MULTIPLY);
        }
    }
}
