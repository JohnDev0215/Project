package com.android.launcher3.notification;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.anim.PropertyListBuilder;
import com.android.launcher3.anim.PropertyResetListener;
import com.android.launcher3.util.Themes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationFooterLayout extends FrameLayout {
    private static final int MAX_FOOTER_NOTIFICATIONS = 5;
    private static final Rect sTempRect = new Rect();
    private final int mBackgroundColor;
    private NotificationItemView mContainer;
    FrameLayout.LayoutParams mIconLayoutParams;
    private LinearLayout mIconRow;
    private final List<NotificationInfo> mNotifications;
    private View mOverflowEllipsis;
    private final List<NotificationInfo> mOverflowNotifications;
    private final boolean mRtl;

    public interface IconAnimationEndListener {
        void onIconAnimationEnd(NotificationInfo animatedNotification);
    }

    public NotificationFooterLayout(Context context) {
        this(context, null, 0);
    }

    public NotificationFooterLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NotificationFooterLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mNotifications = new ArrayList();
        this.mOverflowNotifications = new ArrayList();
        Resources resources = getResources();
        this.mRtl = Utilities.isRtl(resources);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_footer_icon_size);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
        this.mIconLayoutParams = layoutParams;
        layoutParams.gravity = 16;
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.notification_footer_icon_row_padding);
        this.mIconLayoutParams.setMarginStart((((resources.getDimensionPixelSize(R.dimen.bg_popup_item_width) - dimensionPixelSize2) - (resources.getDimensionPixelSize(R.dimen.horizontal_ellipsis_offset) + resources.getDimensionPixelSize(R.dimen.horizontal_ellipsis_size))) - (dimensionPixelSize * 5)) / 5);
        this.mBackgroundColor = Themes.getAttrColor(context, R.attr.popupColorPrimary);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mOverflowEllipsis = findViewById(R.id.overflow);
        this.mIconRow = (LinearLayout) findViewById(R.id.icon_row);
    }

    void setContainer(NotificationItemView container) {
        this.mContainer = container;
    }

    public void addNotificationInfo(final NotificationInfo notificationInfo) {
        if (this.mNotifications.size() < 5) {
            this.mNotifications.add(notificationInfo);
        } else {
            this.mOverflowNotifications.add(notificationInfo);
        }
    }

    public void commitNotificationInfos() {
        this.mIconRow.removeAllViews();
        for (int i = 0; i < this.mNotifications.size(); i++) {
            addNotificationIconForInfo(this.mNotifications.get(i));
        }
        updateOverflowEllipsisVisibility();
    }

    private void updateOverflowEllipsisVisibility() {
        this.mOverflowEllipsis.setVisibility(this.mOverflowNotifications.isEmpty() ? 8 : 0);
    }

    private View addNotificationIconForInfo(NotificationInfo info) {
        View view = new View(getContext());
        view.setBackground(info.getIconForBackground(getContext(), this.mBackgroundColor));
        view.setOnClickListener(info);
        view.setTag(info);
        view.setImportantForAccessibility(2);
        this.mIconRow.addView(view, 0, this.mIconLayoutParams);
        return view;
    }

    public void animateFirstNotificationTo(Rect toBounds, final IconAnimationEndListener callback) {
        AnimatorSet animatorSet = new AnimatorSet();
        LinearLayout linearLayout = this.mIconRow;
        final View childAt = linearLayout.getChildAt(linearLayout.getChildCount() - 1);
        childAt.getGlobalVisibleRect(sTempRect);
        float fHeight = toBounds.height() / r2.height();
        ObjectAnimator objectAnimatorBuild = new PropertyListBuilder().scale(fHeight).translationY((toBounds.top - r2.top) + (((r2.height() * fHeight) - r2.height()) / 2.0f)).build(childAt);
        objectAnimatorBuild.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.notification.NotificationFooterLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                callback.onIconAnimationEnd((NotificationInfo) childAt.getTag());
                NotificationFooterLayout.this.removeViewFromIconRow(childAt);
            }
        });
        animatorSet.play(objectAnimatorBuild);
        int marginStart = this.mIconLayoutParams.width + this.mIconLayoutParams.getMarginStart();
        if (this.mRtl) {
            marginStart = -marginStart;
        }
        if (!this.mOverflowNotifications.isEmpty()) {
            NotificationInfo notificationInfoRemove = this.mOverflowNotifications.remove(0);
            this.mNotifications.add(notificationInfoRemove);
            animatorSet.play(ObjectAnimator.ofFloat(addNotificationIconForInfo(notificationInfoRemove), ALPHA, 0.0f, 1.0f));
        }
        int childCount = this.mIconRow.getChildCount() - 1;
        PropertyResetListener propertyResetListener = new PropertyResetListener(TRANSLATION_X, Float.valueOf(0.0f));
        for (int i = 0; i < childCount; i++) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mIconRow.getChildAt(i), TRANSLATION_X, marginStart);
            objectAnimatorOfFloat.addListener(propertyResetListener);
            animatorSet.play(objectAnimatorOfFloat);
        }
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeViewFromIconRow(View child) {
        NotificationItemView notificationItemView;
        this.mIconRow.removeView(child);
        this.mNotifications.remove(child.getTag());
        updateOverflowEllipsisVisibility();
        if (this.mIconRow.getChildCount() != 0 || (notificationItemView = this.mContainer) == null) {
            return;
        }
        notificationItemView.removeFooter();
    }

    public void trimNotifications(List<String> notifications) {
        if (!isAttachedToWindow() || this.mIconRow.getChildCount() == 0) {
            return;
        }
        Iterator<NotificationInfo> it = this.mOverflowNotifications.iterator();
        while (it.hasNext()) {
            if (!notifications.contains(it.next().notificationKey)) {
                it.remove();
            }
        }
        for (int childCount = this.mIconRow.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.mIconRow.getChildAt(childCount);
            if (!notifications.contains(((NotificationInfo) childAt.getTag()).notificationKey)) {
                removeViewFromIconRow(childAt);
            }
        }
    }
}
