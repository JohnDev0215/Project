package com.android.launcher3.notification;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.launcher3.R;
import com.android.launcher3.graphics.IconPalette;
import com.android.launcher3.notification.NotificationFooterLayout;
import com.android.launcher3.popup.PopupContainerWithArrow;
import com.android.launcher3.touch.SwipeDetector;
import com.android.launcher3.util.Themes;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationItemView {
    private static final Rect sTempRect = new Rect();
    private boolean mAnimatingNextIcon;
    private final PopupContainerWithArrow mContainer;
    private final Context mContext;
    private final View mDivider;
    private final NotificationFooterLayout mFooter;
    private View mGutter;
    private final View mHeader;
    private final TextView mHeaderCount;
    private final TextView mHeaderText;
    private final View mIconView;
    private final NotificationMainView mMainView;
    private final SwipeDetector mSwipeDetector;
    private boolean mIgnoreTouch = false;
    private int mNotificationHeaderTextColor = 0;

    public NotificationItemView(PopupContainerWithArrow container) {
        this.mContainer = container;
        Context context = container.getContext();
        this.mContext = context;
        this.mHeaderText = (TextView) container.findViewById(R.id.notification_text);
        this.mHeaderCount = (TextView) container.findViewById(R.id.notification_count);
        NotificationMainView notificationMainView = (NotificationMainView) container.findViewById(R.id.main_view);
        this.mMainView = notificationMainView;
        NotificationFooterLayout notificationFooterLayout = (NotificationFooterLayout) container.findViewById(R.id.footer);
        this.mFooter = notificationFooterLayout;
        this.mIconView = container.findViewById(R.id.popup_item_icon);
        this.mHeader = container.findViewById(R.id.header);
        this.mDivider = container.findViewById(R.id.divider);
        SwipeDetector swipeDetector = new SwipeDetector(context, notificationMainView, SwipeDetector.HORIZONTAL);
        this.mSwipeDetector = swipeDetector;
        swipeDetector.setDetectableScrollConditions(3, false);
        notificationMainView.setSwipeDetector(swipeDetector);
        notificationFooterLayout.setContainer(this);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void addGutter() {
        if (this.mGutter == null) {
            PopupContainerWithArrow popupContainerWithArrow = this.mContainer;
            this.mGutter = popupContainerWithArrow.inflateAndAdd(R.layout.notification_gutter, popupContainerWithArrow);
        }
    }

    public void removeFooter() {
        if (this.mContainer.indexOfChild(this.mFooter) >= 0) {
            this.mContainer.removeView(this.mFooter);
            this.mContainer.removeView(this.mDivider);
        }
    }

    public void inverseGutterMargin() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mGutter.getLayoutParams();
        int i = marginLayoutParams.topMargin;
        marginLayoutParams.topMargin = marginLayoutParams.bottomMargin;
        marginLayoutParams.bottomMargin = i;
    }

    public void removeAllViews() {
        this.mContainer.removeView(this.mMainView);
        this.mContainer.removeView(this.mHeader);
        if (this.mContainer.indexOfChild(this.mFooter) >= 0) {
            this.mContainer.removeView(this.mFooter);
            this.mContainer.removeView(this.mDivider);
        }
        View view = this.mGutter;
        if (view != null) {
            this.mContainer.removeView(view);
        }
    }

    public void updateHeader(int notificationCount, int iconColor) {
        this.mHeaderCount.setText(notificationCount <= 1 ? "" : String.valueOf(notificationCount));
        if (Color.alpha(iconColor) > 0) {
            if (this.mNotificationHeaderTextColor == 0) {
                Context context = this.mContext;
                this.mNotificationHeaderTextColor = IconPalette.resolveContrastColor(context, iconColor, Themes.getAttrColor(context, R.attr.popupColorPrimary));
            }
            this.mHeaderText.setTextColor(this.mNotificationHeaderTextColor);
            this.mHeaderCount.setTextColor(this.mNotificationHeaderTextColor);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            Rect rect = sTempRect;
            rect.set(this.mMainView.getLeft(), this.mMainView.getTop(), this.mMainView.getRight(), this.mMainView.getBottom());
            boolean z = !rect.contains((int) ev.getX(), (int) ev.getY());
            this.mIgnoreTouch = z;
            if (!z) {
                this.mContainer.getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        if (this.mIgnoreTouch || this.mMainView.getNotificationInfo() == null) {
            return false;
        }
        this.mSwipeDetector.onTouchEvent(ev);
        return this.mSwipeDetector.isDraggingOrSettling();
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (this.mIgnoreTouch || this.mMainView.getNotificationInfo() == null) {
            return false;
        }
        return this.mSwipeDetector.onTouchEvent(ev);
    }

    public void applyNotificationInfos(final List<NotificationInfo> notificationInfos) {
        if (notificationInfos.isEmpty()) {
            return;
        }
        this.mMainView.applyNotificationInfo(notificationInfos.get(0), false);
        for (int i = 1; i < notificationInfos.size(); i++) {
            this.mFooter.addNotificationInfo(notificationInfos.get(i));
        }
        this.mFooter.commitNotificationInfos();
    }

    public void trimNotifications(final List<String> notificationKeys) {
        if ((!notificationKeys.contains(this.mMainView.getNotificationInfo().notificationKey)) && !this.mAnimatingNextIcon) {
            this.mAnimatingNextIcon = true;
            this.mMainView.setContentVisibility(4);
            this.mMainView.setContentTranslation(0.0f);
            View view = this.mIconView;
            Rect rect = sTempRect;
            view.getGlobalVisibleRect(rect);
            this.mFooter.animateFirstNotificationTo(rect, new NotificationFooterLayout.IconAnimationEndListener() { // from class: com.android.launcher3.notification.-$$Lambda$NotificationItemView$h-Bevsky1hT-t-OB9OikjpC4zNA
                @Override // com.android.launcher3.notification.NotificationFooterLayout.IconAnimationEndListener
                public final void onIconAnimationEnd(NotificationInfo notificationInfo) {
                    this.f$0.lambda$trimNotifications$0$NotificationItemView(notificationInfo);
                }
            });
            return;
        }
        this.mFooter.trimNotifications(notificationKeys);
    }

    public /* synthetic */ void lambda$trimNotifications$0$NotificationItemView(NotificationInfo notificationInfo) {
        if (notificationInfo != null) {
            this.mMainView.applyNotificationInfo(notificationInfo, true);
            this.mMainView.setContentVisibility(0);
        }
        this.mAnimatingNextIcon = false;
    }
}
