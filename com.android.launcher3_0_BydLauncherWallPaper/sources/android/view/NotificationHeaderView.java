package android.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import com.android.internal.R;
import com.android.internal.widget.CachingIconView;
import com.android.internal.widget.NotificationExpandButton;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
@RemoteViews.RemoteView
public class NotificationHeaderView extends ViewGroup {
    public static final int NO_COLOR = 1;
    private boolean mAcceptAllTouches;
    private View mAppName;
    private View mAppOps;
    private View.OnClickListener mAppOpsListener;
    private Drawable mBackground;
    private final int mChildMinWidth;
    private final int mContentEndMargin;
    private boolean mEntireHeaderClickable;
    private NotificationExpandButton mExpandButton;
    private View.OnClickListener mExpandClickListener;
    private boolean mExpandOnlyOnButton;
    private boolean mExpanded;
    private final int mGravity;
    private View mHeaderText;
    private int mHeaderTextMarginEnd;
    private CachingIconView mIcon;
    private View mProfileBadge;
    ViewOutlineProvider mProvider;
    private View mSecondaryHeaderText;
    private boolean mShowExpandButtonAtEnd;
    private boolean mShowWorkBadgeAtEnd;
    private int mTotalWidth;
    private HeaderTouchListener mTouchListener;
    private LinearLayout mTransferChip;

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public NotificationHeaderView(Context context) {
        this(context, null);
    }

    public NotificationHeaderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NotificationHeaderView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public NotificationHeaderView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mTouchListener = new HeaderTouchListener();
        this.mProvider = new ViewOutlineProvider() { // from class: android.view.NotificationHeaderView.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                if (NotificationHeaderView.this.mBackground != null) {
                    outline.setRect(0, 0, NotificationHeaderView.this.getWidth(), NotificationHeaderView.this.getHeight());
                    outline.setAlpha(1.0f);
                }
            }
        };
        Resources resources = getResources();
        this.mChildMinWidth = resources.getDimensionPixelSize(R.dimen.notification_header_shrink_min_width);
        this.mContentEndMargin = resources.getDimensionPixelSize(R.dimen.notification_content_margin_end);
        this.mEntireHeaderClickable = resources.getBoolean(R.bool.config_notificationHeaderClickableForExpand);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842927}, i, i2);
        this.mGravity = typedArrayObtainStyledAttributes.getInt(0, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAppName = findViewById(R.id.app_name_text);
        this.mHeaderText = findViewById(R.id.header_text);
        this.mSecondaryHeaderText = findViewById(R.id.header_text_secondary);
        this.mTransferChip = (LinearLayout) findViewById(R.id.media_seamless);
        this.mExpandButton = (NotificationExpandButton) findViewById(R.id.expand_button);
        this.mIcon = (CachingIconView) findViewById(16908294);
        this.mProfileBadge = findViewById(R.id.profile_badge);
        this.mAppOps = findViewById(R.id.app_ops);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE);
        int paddingStart = getPaddingStart();
        int paddingEnd = getPaddingEnd();
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                childAt.measure(getChildMeasureSpec(iMakeMeasureSpec, marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, marginLayoutParams.width), getChildMeasureSpec(iMakeMeasureSpec2, marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height));
                if ((childAt == this.mExpandButton && this.mShowExpandButtonAtEnd) || childAt == this.mProfileBadge || childAt == this.mAppOps || childAt == this.mTransferChip) {
                    paddingEnd += marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + childAt.getMeasuredWidth();
                } else {
                    paddingStart += marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + childAt.getMeasuredWidth();
                }
            }
        }
        int iMax = Math.max(this.mHeaderTextMarginEnd, paddingEnd);
        if (paddingStart > size - iMax) {
            shrinkViewForOverflow(iMakeMeasureSpec2, shrinkViewForOverflow(iMakeMeasureSpec2, shrinkViewForOverflow(iMakeMeasureSpec2, (paddingStart - size) + iMax, this.mAppName, this.mChildMinWidth), this.mHeaderText, 0), this.mSecondaryHeaderText, 0);
        }
        this.mTotalWidth = Math.min(paddingStart + getPaddingEnd(), size);
        setMeasuredDimension(size, size2);
    }

    private int shrinkViewForOverflow(int i, int i2, View view, int i3) {
        int measuredWidth = view.getMeasuredWidth();
        if (i2 <= 0 || view.getVisibility() == 8 || measuredWidth <= i3) {
            return i2;
        }
        int iMax = Math.max(i3, measuredWidth - i2);
        view.measure(View.MeasureSpec.makeMeasureSpec(iMax, Integer.MIN_VALUE), i);
        return i2 - (measuredWidth - iMax);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int marginStart;
        int measuredWidth;
        int marginEnd;
        int marginEnd2;
        int paddingStart = getPaddingStart();
        int measuredWidth2 = getMeasuredWidth();
        if ((this.mGravity & 1) != 0) {
            paddingStart += (getMeasuredWidth() / 2) - (this.mTotalWidth / 2);
        }
        int childCount = getChildCount();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                int measuredHeight2 = childAt.getMeasuredHeight();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                int paddingTop = (int) (getPaddingTop() + ((measuredHeight - measuredHeight2) / 2.0f));
                int i6 = measuredHeight2 + paddingTop;
                if ((childAt == this.mExpandButton && this.mShowExpandButtonAtEnd) || childAt == this.mProfileBadge || childAt == this.mAppOps || childAt == this.mTransferChip) {
                    if (measuredWidth2 == getMeasuredWidth()) {
                        marginEnd2 = this.mContentEndMargin;
                    } else {
                        marginEnd2 = marginLayoutParams.getMarginEnd();
                    }
                    measuredWidth = measuredWidth2 - marginEnd2;
                    int measuredWidth3 = measuredWidth - childAt.getMeasuredWidth();
                    int marginStart2 = measuredWidth3 - marginLayoutParams.getMarginStart();
                    marginEnd = paddingStart;
                    marginStart = measuredWidth3;
                    measuredWidth2 = marginStart2;
                } else {
                    marginStart = paddingStart + marginLayoutParams.getMarginStart();
                    measuredWidth = childAt.getMeasuredWidth() + marginStart;
                    marginEnd = marginLayoutParams.getMarginEnd() + measuredWidth;
                }
                if (getLayoutDirection() == 1) {
                    int width = getWidth() - measuredWidth;
                    measuredWidth = getWidth() - marginStart;
                    marginStart = width;
                }
                childAt.layout(marginStart, paddingTop, measuredWidth, i6);
                paddingStart = marginEnd;
            }
        }
        updateTouchListener();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public void setHeaderBackgroundDrawable(Drawable drawable) {
        if (drawable != null) {
            setWillNotDraw(false);
            this.mBackground = drawable;
            drawable.setCallback(this);
            setOutlineProvider(this.mProvider);
        } else {
            setWillNotDraw(true);
            this.mBackground = null;
            setOutlineProvider(null);
        }
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable drawable = this.mBackground;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getHeight());
            this.mBackground.draw(canvas);
        }
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mBackground;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        Drawable drawable = this.mBackground;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        this.mBackground.setState(getDrawableState());
    }

    private void updateTouchListener() {
        if (this.mExpandClickListener == null && this.mAppOpsListener == null) {
            setOnTouchListener(null);
        } else {
            setOnTouchListener(this.mTouchListener);
            this.mTouchListener.bindTouchRects();
        }
    }

    public void setAppOpsOnClickListener(View.OnClickListener onClickListener) {
        this.mAppOpsListener = onClickListener;
        updateTouchListener();
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mExpandClickListener = onClickListener;
        this.mExpandButton.setOnClickListener(onClickListener);
        updateTouchListener();
    }

    public int getOriginalIconColor() {
        return this.mIcon.getOriginalIconColor();
    }

    public int getOriginalNotificationColor() {
        return this.mExpandButton.getOriginalNotificationColor();
    }

    @RemotableViewMethod
    public void setExpanded(boolean z) {
        this.mExpanded = z;
        updateExpandButton();
    }

    private void updateExpandButton() {
        int i;
        int i2;
        if (this.mExpanded) {
            i = R.drawable.ic_collapse_notification;
            i2 = R.string.expand_button_content_description_expanded;
        } else {
            i = R.drawable.ic_expand_notification;
            i2 = R.string.expand_button_content_description_collapsed;
        }
        this.mExpandButton.setImageDrawable(getContext().getDrawable(i));
        this.mExpandButton.setColorFilter(getOriginalNotificationColor());
        this.mExpandButton.setContentDescription(this.mContext.getText(i2));
    }

    public void setShowWorkBadgeAtEnd(boolean z) {
        if (z != this.mShowWorkBadgeAtEnd) {
            setClipToPadding(!z);
            this.mShowWorkBadgeAtEnd = z;
        }
    }

    public void setShowExpandButtonAtEnd(boolean z) {
        if (z != this.mShowExpandButtonAtEnd) {
            setClipToPadding(!z);
            this.mShowExpandButtonAtEnd = z;
        }
    }

    public View getWorkProfileIcon() {
        return this.mProfileBadge;
    }

    public CachingIconView getIcon() {
        return this.mIcon;
    }

    @RemotableViewMethod
    public void setHeaderTextMarginEnd(int i) {
        if (this.mHeaderTextMarginEnd != i) {
            this.mHeaderTextMarginEnd = i;
            requestLayout();
        }
    }

    public int getHeaderTextMarginEnd() {
        return this.mHeaderTextMarginEnd;
    }

    public class HeaderTouchListener implements View.OnTouchListener {
        private Rect mAppOpsRect;
        private float mDownX;
        private float mDownY;
        private Rect mExpandButtonRect;
        private final ArrayList<Rect> mTouchRects = new ArrayList<>();
        private int mTouchSlop;
        private boolean mTrackGesture;

        public HeaderTouchListener() {
        }

        public void bindTouchRects() {
            this.mTouchRects.clear();
            addRectAroundView(NotificationHeaderView.this.mIcon);
            this.mExpandButtonRect = addRectAroundView(NotificationHeaderView.this.mExpandButton);
            this.mAppOpsRect = addRectAroundView(NotificationHeaderView.this.mAppOps);
            NotificationHeaderView.this.setTouchDelegate(new TouchDelegate(this.mAppOpsRect, NotificationHeaderView.this.mAppOps));
            addWidthRect();
            this.mTouchSlop = ViewConfiguration.get(NotificationHeaderView.this.getContext()).getScaledTouchSlop();
        }

        private void addWidthRect() {
            Rect rect = new Rect();
            rect.top = 0;
            rect.bottom = (int) (NotificationHeaderView.this.getResources().getDisplayMetrics().density * 32.0f);
            rect.left = 0;
            rect.right = NotificationHeaderView.this.getWidth();
            this.mTouchRects.add(rect);
        }

        private Rect addRectAroundView(View view) {
            Rect rectAroundView = getRectAroundView(view);
            this.mTouchRects.add(rectAroundView);
            return rectAroundView;
        }

        private Rect getRectAroundView(View view) {
            float f = NotificationHeaderView.this.getResources().getDisplayMetrics().density * 48.0f;
            float fMax = Math.max(f, view.getWidth());
            float fMax2 = Math.max(f, view.getHeight());
            Rect rect = new Rect();
            if (view.getVisibility() == 8) {
                view = NotificationHeaderView.this.getFirstChildNotGone();
                rect.left = (int) (view.getLeft() - (fMax / 2.0f));
            } else {
                rect.left = (int) (((view.getLeft() + view.getRight()) / 2.0f) - (fMax / 2.0f));
            }
            rect.top = (int) (((view.getTop() + view.getBottom()) / 2.0f) - (fMax2 / 2.0f));
            rect.bottom = (int) (rect.top + fMax2);
            rect.right = (int) (rect.left + fMax);
            return rect;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int actionMasked = motionEvent.getActionMasked() & 255;
            if (actionMasked == 0) {
                this.mTrackGesture = false;
                if (isInside(x, y)) {
                    this.mDownX = x;
                    this.mDownY = y;
                    this.mTrackGesture = true;
                    return true;
                }
            } else if (actionMasked != 1) {
                if (actionMasked == 2 && this.mTrackGesture && (Math.abs(this.mDownX - x) > this.mTouchSlop || Math.abs(this.mDownY - y) > this.mTouchSlop)) {
                    this.mTrackGesture = false;
                }
            } else if (this.mTrackGesture) {
                if (!NotificationHeaderView.this.mAppOps.isVisibleToUser() || (!this.mAppOpsRect.contains((int) x, (int) y) && !this.mAppOpsRect.contains((int) this.mDownX, (int) this.mDownY))) {
                    NotificationHeaderView.this.mExpandButton.performClick();
                } else {
                    NotificationHeaderView.this.mAppOps.performClick();
                    return true;
                }
            }
            return this.mTrackGesture;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isInside(float f, float f2) {
            if (NotificationHeaderView.this.mAcceptAllTouches) {
                return true;
            }
            if (NotificationHeaderView.this.mExpandOnlyOnButton) {
                return this.mExpandButtonRect.contains((int) f, (int) f2);
            }
            for (int i = 0; i < this.mTouchRects.size(); i++) {
                if (this.mTouchRects.get(i).contains((int) f, (int) f2)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getFirstChildNotGone() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                return childAt;
            }
        }
        return this;
    }

    public ImageView getExpandButton() {
        return this.mExpandButton;
    }

    public boolean isInTouchRect(float f, float f2) {
        if (this.mExpandClickListener == null) {
            return false;
        }
        return this.mTouchListener.isInside(f, f2);
    }

    @RemotableViewMethod
    public void setAcceptAllTouches(boolean z) {
        this.mAcceptAllTouches = this.mEntireHeaderClickable || z;
    }

    @RemotableViewMethod
    public void setExpandOnlyOnButton(boolean z) {
        this.mExpandOnlyOnButton = z;
    }
}
