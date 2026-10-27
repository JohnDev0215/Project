package com.android.launcher3.notification;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.anim.AnimationSuccessListener;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.touch.OverScroll;
import com.android.launcher3.touch.SwipeDetector;
import com.android.launcher3.util.Themes;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationMainView extends FrameLayout implements SwipeDetector.Listener {
    private static FloatProperty<NotificationMainView> CONTENT_TRANSLATION = new FloatProperty<NotificationMainView>("contentTranslation") { // from class: com.android.launcher3.notification.NotificationMainView.1
        @Override // android.util.FloatProperty
        public void setValue(NotificationMainView view, float v) {
            view.setContentTranslation(v);
        }

        @Override // android.util.Property
        public Float get(NotificationMainView view) {
            return Float.valueOf(view.mTextAndBackground.getTranslationX());
        }
    };
    public static final ItemInfo NOTIFICATION_ITEM_INFO = new ItemInfo();
    private int mBackgroundColor;
    private final ObjectAnimator mContentTranslateAnimator;
    private View mIconView;
    private NotificationInfo mNotificationInfo;
    private SwipeDetector mSwipeDetector;
    private ViewGroup mTextAndBackground;
    private TextView mTextView;
    private TextView mTitleView;

    @Override // com.android.launcher3.touch.SwipeDetector.Listener
    public void onDragStart(boolean start) {
    }

    public NotificationMainView(Context context) {
        this(context, null, 0);
    }

    public NotificationMainView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NotificationMainView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContentTranslateAnimator = ObjectAnimator.ofFloat(this, CONTENT_TRANSLATION, 0.0f);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.text_and_background);
        this.mTextAndBackground = viewGroup;
        ColorDrawable colorDrawable = (ColorDrawable) viewGroup.getBackground();
        this.mBackgroundColor = colorDrawable.getColor();
        this.mTextAndBackground.setBackground(new RippleDrawable(ColorStateList.valueOf(Themes.getAttrColor(getContext(), 16843820)), colorDrawable, (Drawable) null));
        this.mTitleView = (TextView) this.mTextAndBackground.findViewById(R.id.title);
        this.mTextView = (TextView) this.mTextAndBackground.findViewById(R.id.text);
        this.mIconView = findViewById(R.id.popup_item_icon);
    }

    public void setSwipeDetector(SwipeDetector swipeDetector) {
        this.mSwipeDetector = swipeDetector;
    }

    public void applyNotificationInfo(NotificationInfo mainNotification, boolean animate) {
        this.mNotificationInfo = mainNotification;
        NotificationListener instanceIfConnected = NotificationListener.getInstanceIfConnected();
        if (instanceIfConnected != null) {
            instanceIfConnected.setNotificationsShown(new String[]{this.mNotificationInfo.notificationKey});
        }
        CharSequence charSequence = this.mNotificationInfo.title;
        CharSequence charSequence2 = this.mNotificationInfo.text;
        if (!TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(charSequence2)) {
            this.mTitleView.setText(charSequence.toString());
            this.mTextView.setText(charSequence2.toString());
        } else {
            this.mTitleView.setMaxLines(2);
            this.mTitleView.setText(TextUtils.isEmpty(charSequence) ? charSequence2.toString() : charSequence.toString());
            this.mTextView.setVisibility(8);
        }
        this.mIconView.setBackground(this.mNotificationInfo.getIconForBackground(getContext(), this.mBackgroundColor));
        if (this.mNotificationInfo.intent != null) {
            setOnClickListener(this.mNotificationInfo);
        }
        setContentTranslation(0.0f);
        setTag(NOTIFICATION_ITEM_INFO);
        if (animate) {
            ObjectAnimator.ofFloat(this.mTextAndBackground, (Property<ViewGroup, Float>) ALPHA, 0.0f, 1.0f).setDuration(150L).start();
        }
    }

    public void setContentTranslation(float translation) {
        this.mTextAndBackground.setTranslationX(translation);
        this.mIconView.setTranslationX(translation);
    }

    public void setContentVisibility(int visibility) {
        this.mTextAndBackground.setVisibility(visibility);
        this.mIconView.setVisibility(visibility);
    }

    public NotificationInfo getNotificationInfo() {
        return this.mNotificationInfo;
    }

    public boolean canChildBeDismissed() {
        NotificationInfo notificationInfo = this.mNotificationInfo;
        return notificationInfo != null && notificationInfo.dismissable;
    }

    public void onChildDismissed() {
        Launcher launcher = Launcher.getLauncher(getContext());
        launcher.getPopupDataProvider().cancelNotification(this.mNotificationInfo.notificationKey);
        launcher.getUserEventDispatcher().logActionOnItem(3, 4, 8);
    }

    @Override // com.android.launcher3.touch.SwipeDetector.Listener
    public boolean onDrag(float displacement) {
        if (!canChildBeDismissed()) {
            displacement = OverScroll.dampedScroll(displacement, getWidth());
        }
        setContentTranslation(displacement);
        this.mContentTranslateAnimator.cancel();
        return true;
    }

    @Override // com.android.launcher3.touch.SwipeDetector.Listener
    public void onDragEnd(float velocity, boolean fling) {
        final boolean z;
        float translationX = this.mTextAndBackground.getTranslationX();
        float width = 0.0f;
        if (canChildBeDismissed()) {
            if (fling) {
                width = velocity < 0.0f ? -getWidth() : getWidth();
            } else {
                if (Math.abs(translationX) > getWidth() / 2) {
                    width = translationX < 0.0f ? -getWidth() : getWidth();
                }
                z = false;
            }
            z = true;
        } else {
            z = false;
        }
        long jCalculateDuration = SwipeDetector.calculateDuration(velocity, (width - translationX) / getWidth());
        this.mContentTranslateAnimator.removeAllListeners();
        this.mContentTranslateAnimator.setDuration(jCalculateDuration).setInterpolator(Interpolators.scrollInterpolatorForVelocity(velocity));
        this.mContentTranslateAnimator.setFloatValues(translationX, width);
        this.mContentTranslateAnimator.addListener(new AnimationSuccessListener() { // from class: com.android.launcher3.notification.NotificationMainView.2
            @Override // com.android.launcher3.anim.AnimationSuccessListener
            public void onAnimationSuccess(Animator animator) {
                NotificationMainView.this.mSwipeDetector.finishedScrolling();
                if (z) {
                    NotificationMainView.this.onChildDismissed();
                }
            }
        });
        this.mContentTranslateAnimator.start();
    }
}
