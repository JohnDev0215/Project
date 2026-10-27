package com.android.launcher3.popup;

import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.DragSource;
import com.android.launcher3.DropTarget;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.accessibility.LauncherAccessibilityDelegate;
import com.android.launcher3.accessibility.ShortcutMenuAccessibilityDelegate;
import com.android.launcher3.dot.DotInfo;
import com.android.launcher3.dragndrop.DragController;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.logging.LoggerUtils;
import com.android.launcher3.notification.NotificationInfo;
import com.android.launcher3.notification.NotificationItemView;
import com.android.launcher3.notification.NotificationKeyData;
import com.android.launcher3.notification.NotificationMainView;
import com.android.launcher3.popup.PopupDataProvider;
import com.android.launcher3.popup.SystemShortcut;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.shortcuts.DeepShortcutView;
import com.android.launcher3.shortcuts.ShortcutDragPreviewProvider;
import com.android.launcher3.testing.TestProtocol;
import com.android.launcher3.touch.ItemClickHandler;
import com.android.launcher3.touch.ItemLongClickListener;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.util.PackageUserKey;
import com.android.launcher3.views.BaseDragLayer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes4.dex */
public class PopupContainerWithArrow extends ArrowPopup implements DragSource, DragController.DragListener, View.OnLongClickListener, View.OnTouchListener, PopupDataProvider.PopupDataChangeListener {
    private final LauncherAccessibilityDelegate mAccessibilityDelegate;
    protected final Point mIconLastTouchPos;
    private final PointF mInterceptTouchDown;
    private NotificationItemView mNotificationItemView;
    private int mNumNotifications;
    private BubbleTextView mOriginalIcon;
    private final List<DeepShortcutView> mShortcuts;
    private final int mStartDragThreshold;
    private ViewGroup mSystemShortcutContainer;

    @Override // com.android.launcher3.AbstractFloatingView
    public int getLogContainerType() {
        return 9;
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected boolean isOfType(int type) {
        return (type & 2) != 0;
    }

    @Override // com.android.launcher3.DragSource
    public void onDropCompleted(View target, DropTarget.DragObject d, boolean success) {
    }

    public PopupContainerWithArrow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mShortcuts = new ArrayList();
        this.mInterceptTouchDown = new PointF();
        this.mIconLastTouchPos = new Point();
        this.mStartDragThreshold = getResources().getDimensionPixelSize(R.dimen.deep_shortcuts_start_drag_threshold);
        this.mAccessibilityDelegate = new ShortcutMenuAccessibilityDelegate(this.mLauncher);
    }

    public PopupContainerWithArrow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PopupContainerWithArrow(Context context) {
        this(context, null, 0);
    }

    @Override // android.view.View
    public LauncherAccessibilityDelegate getAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLauncher.getPopupDataProvider().setChangeListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mLauncher.getPopupDataProvider().setChangeListener(null);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            this.mInterceptTouchDown.set(ev.getX(), ev.getY());
        }
        NotificationItemView notificationItemView = this.mNotificationItemView;
        return (notificationItemView != null && notificationItemView.onInterceptTouchEvent(ev)) || Utilities.squaredHypot(this.mInterceptTouchDown.x - ev.getX(), this.mInterceptTouchDown.y - ev.getY()) > Utilities.squaredTouchSlop(getContext());
    }

    @Override // com.android.launcher3.AbstractFloatingView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        NotificationItemView notificationItemView = this.mNotificationItemView;
        if (notificationItemView != null) {
            return notificationItemView.onTouchEvent(ev) || super.onTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    @Override // com.android.launcher3.AbstractFloatingView
    public void logActionCommand(int command) {
        this.mLauncher.getUserEventDispatcher().logActionCommand(command, this.mOriginalIcon, getLogContainerType());
    }

    public View.OnClickListener getItemClickListener() {
        return ItemClickHandler.INSTANCE;
    }

    @Override // com.android.launcher3.util.TouchController
    public boolean onControllerInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() != 0) {
            return false;
        }
        BaseDragLayer popupContainer = getPopupContainer();
        if (popupContainer.isEventOverView(this, ev)) {
            return false;
        }
        this.mLauncher.getUserEventDispatcher().logActionTapOutside(LoggerUtils.newContainerTarget(9));
        close(true);
        BubbleTextView bubbleTextView = this.mOriginalIcon;
        return bubbleTextView == null || !popupContainer.isEventOverView(bubbleTextView, ev);
    }

    public static PopupContainerWithArrow showForIcon(BubbleTextView icon) {
        Launcher launcher = Launcher.getLauncher(icon.getContext());
        if (getOpen(launcher) != null) {
            icon.clearFocus();
            return null;
        }
        ItemInfo itemInfo = (ItemInfo) icon.getTag();
        if (!DeepShortcutManager.supportsShortcuts(itemInfo)) {
            return null;
        }
        PopupContainerWithArrow popupContainerWithArrow = (PopupContainerWithArrow) launcher.getLayoutInflater().inflate(R.layout.popup_container, (ViewGroup) launcher.getDragLayer(), false);
        popupContainerWithArrow.populateAndShow(icon, itemInfo, SystemShortcutFactory.INSTANCE.lambda$get$0$MainThreadInitializedObject(launcher));
        return popupContainerWithArrow;
    }

    @Override // com.android.launcher3.popup.ArrowPopup
    protected void onInflationComplete(boolean isReversed) {
        NotificationItemView notificationItemView;
        if (isReversed && (notificationItemView = this.mNotificationItemView) != null) {
            notificationItemView.inverseGutterMargin();
        }
        int childCount = getChildCount();
        DeepShortcutView deepShortcutView = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (childAt instanceof DeepShortcutView)) {
                if (deepShortcutView != null) {
                    deepShortcutView.setDividerVisibility(0);
                }
                DeepShortcutView deepShortcutView2 = (DeepShortcutView) childAt;
                deepShortcutView2.setDividerVisibility(4);
                deepShortcutView = deepShortcutView2;
            }
        }
    }

    protected void populateAndShow(BubbleTextView icon, ItemInfo item, SystemShortcutFactory factory) {
        PopupDataProvider popupDataProvider = this.mLauncher.getPopupDataProvider();
        populateAndShow(icon, popupDataProvider.getShortcutCountForItem(item), popupDataProvider.getNotificationKeysForItem(item), factory.getEnabledShortcuts(this.mLauncher, item));
    }

    public ViewGroup getSystemShortcutContainerForTesting() {
        return this.mSystemShortcutContainer;
    }

    protected void populateAndShow(final BubbleTextView originalIcon, int shortcutCount, final List<NotificationKeyData> notificationKeys, List<SystemShortcut> systemShortcuts) {
        int size = notificationKeys.size();
        this.mNumNotifications = size;
        this.mOriginalIcon = originalIcon;
        if (size > 0) {
            View.inflate(getContext(), R.layout.notification_content, this);
            NotificationItemView notificationItemView = new NotificationItemView(this);
            this.mNotificationItemView = notificationItemView;
            if (this.mNumNotifications == 1) {
                notificationItemView.removeFooter();
            }
            updateNotificationHeader();
        }
        int childCount = getChildCount();
        this.mSystemShortcutContainer = this;
        if (shortcutCount > 0) {
            NotificationItemView notificationItemView2 = this.mNotificationItemView;
            if (notificationItemView2 != null) {
                notificationItemView2.addGutter();
            }
            while (shortcutCount > 0) {
                this.mShortcuts.add((DeepShortcutView) inflateAndAdd(R.layout.deep_shortcut, this));
                shortcutCount--;
            }
            updateHiddenShortcuts();
            if (!systemShortcuts.isEmpty()) {
                this.mSystemShortcutContainer = (ViewGroup) inflateAndAdd(R.layout.system_shortcut_icons, this);
                Iterator<SystemShortcut> it = systemShortcuts.iterator();
                while (it.hasNext()) {
                    initializeSystemShortcut(R.layout.system_shortcut_icon_only, this.mSystemShortcutContainer, it.next());
                }
            }
        } else if (!systemShortcuts.isEmpty()) {
            NotificationItemView notificationItemView3 = this.mNotificationItemView;
            if (notificationItemView3 != null) {
                notificationItemView3.addGutter();
            }
            Iterator<SystemShortcut> it2 = systemShortcuts.iterator();
            while (it2.hasNext()) {
                initializeSystemShortcut(R.layout.system_shortcut, this, it2.next());
            }
        }
        if (getChildCount() > 0) {
            reorderAndShow(childCount);
        }
        ItemInfo itemInfo = (ItemInfo) originalIcon.getTag();
        if (Build.VERSION.SDK_INT >= 28) {
            setAccessibilityPaneTitle(getTitleForAccessibility());
        }
        this.mLauncher.getDragController().addDragListener(this);
        this.mOriginalIcon.forceHideDot(true);
        setLayoutTransition(new LayoutTransition());
        new Handler(LauncherModel.getWorkerLooper()).postAtFrontOfQueue(PopupPopulator.createUpdateRunnable(this.mLauncher, itemInfo, new Handler(Looper.getMainLooper()), this, this.mShortcuts, notificationKeys));
    }

    private String getTitleForAccessibility() {
        return getContext().getString(this.mNumNotifications == 0 ? R.string.action_deep_shortcut : R.string.shortcuts_menu_with_notifications_description);
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected Pair<View, String> getAccessibilityTarget() {
        return Pair.create(this, "");
    }

    @Override // com.android.launcher3.popup.ArrowPopup
    protected void getTargetObjectLocation(Rect outPos) {
        int height;
        getPopupContainer().getDescendantRectRelativeToSelf(this.mOriginalIcon, outPos);
        outPos.top += this.mOriginalIcon.getPaddingTop();
        outPos.left += this.mOriginalIcon.getPaddingLeft();
        outPos.right -= this.mOriginalIcon.getPaddingRight();
        int i = outPos.top;
        if (this.mOriginalIcon.getIcon() != null) {
            height = this.mOriginalIcon.getIcon().getBounds().height();
        } else {
            height = this.mOriginalIcon.getHeight();
        }
        outPos.bottom = i + height;
    }

    public void applyNotificationInfos(List<NotificationInfo> notificationInfos) {
        this.mNotificationItemView.applyNotificationInfos(notificationInfos);
    }

    private void updateHiddenShortcuts() {
        int i = this.mNotificationItemView != null ? 2 : 4;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.bg_popup_item_height);
        int dimensionPixelSize2 = this.mNotificationItemView != null ? getResources().getDimensionPixelSize(R.dimen.bg_popup_item_condensed_height) : dimensionPixelSize;
        float f = dimensionPixelSize2 / dimensionPixelSize;
        int size = this.mShortcuts.size();
        int i2 = 0;
        while (i2 < size) {
            DeepShortcutView deepShortcutView = this.mShortcuts.get(i2);
            deepShortcutView.setVisibility(i2 >= i ? 8 : 0);
            deepShortcutView.getLayoutParams().height = dimensionPixelSize2;
            deepShortcutView.getIconView().setScaleX(f);
            deepShortcutView.getIconView().setScaleY(f);
            i2++;
        }
    }

    private void updateDividers() {
        int childCount = getChildCount();
        DeepShortcutView deepShortcutView = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (childAt instanceof DeepShortcutView)) {
                if (deepShortcutView != null) {
                    deepShortcutView.setDividerVisibility(0);
                }
                DeepShortcutView deepShortcutView2 = (DeepShortcutView) childAt;
                deepShortcutView2.setDividerVisibility(4);
                deepShortcutView = deepShortcutView2;
            }
        }
    }

    @Override // com.android.launcher3.popup.PopupDataProvider.PopupDataChangeListener
    public void onWidgetsBound() {
        View childAt;
        ItemInfo itemInfo = (ItemInfo) this.mOriginalIcon.getTag();
        SystemShortcut.Widgets widgets = new SystemShortcut.Widgets();
        View.OnClickListener onClickListener = widgets.getOnClickListener(this.mLauncher, itemInfo);
        int childCount = this.mSystemShortcutContainer.getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                childAt = null;
                break;
            }
            childAt = this.mSystemShortcutContainer.getChildAt(i);
            if (childAt.getTag() instanceof SystemShortcut.Widgets) {
                break;
            } else {
                i++;
            }
        }
        if (onClickListener != null && childAt == null) {
            ViewGroup viewGroup = this.mSystemShortcutContainer;
            if (viewGroup != this) {
                initializeSystemShortcut(R.layout.system_shortcut_icon_only, viewGroup, widgets);
                return;
            } else {
                close(false);
                showForIcon(this.mOriginalIcon);
                return;
            }
        }
        if (onClickListener != null || childAt == null) {
            return;
        }
        ViewGroup viewGroup2 = this.mSystemShortcutContainer;
        if (viewGroup2 != this) {
            viewGroup2.removeView(childAt);
        } else {
            close(false);
            showForIcon(this.mOriginalIcon);
        }
    }

    private void initializeSystemShortcut(int resId, ViewGroup container, SystemShortcut info) {
        View viewInflateAndAdd = inflateAndAdd(resId, container, getInsertIndexForSystemShortcut(container, info));
        if (viewInflateAndAdd instanceof DeepShortcutView) {
            DeepShortcutView deepShortcutView = (DeepShortcutView) viewInflateAndAdd;
            info.setIconAndLabelFor(deepShortcutView.getIconView(), deepShortcutView.getBubbleText());
        } else if (viewInflateAndAdd instanceof ImageView) {
            info.setIconAndContentDescriptionFor((ImageView) viewInflateAndAdd);
        }
        viewInflateAndAdd.setTag(info);
        viewInflateAndAdd.setOnClickListener(info.getOnClickListener(this.mLauncher, (ItemInfo) this.mOriginalIcon.getTag()));
    }

    private int getInsertIndexForSystemShortcut(ViewGroup container, SystemShortcut shortcut) {
        View viewFindViewById = container.findViewById(R.id.separator);
        if (viewFindViewById != null && shortcut.isLeftGroup()) {
            return container.indexOfChild(viewFindViewById);
        }
        return container.getChildCount();
    }

    public DragOptions.PreDragCondition createPreDragCondition() {
        return new DragOptions.PreDragCondition() { // from class: com.android.launcher3.popup.PopupContainerWithArrow.1
            @Override // com.android.launcher3.dragndrop.DragOptions.PreDragCondition
            public boolean shouldStartDrag(double distanceDragged) {
                if (TestProtocol.sDebugTracing) {
                    Log.d(TestProtocol.NO_DRAG_TAG, "createPreDragCondition().shouldStartDrag " + distanceDragged + ", " + PopupContainerWithArrow.this.mStartDragThreshold);
                }
                return distanceDragged > ((double) PopupContainerWithArrow.this.mStartDragThreshold);
            }

            @Override // com.android.launcher3.dragndrop.DragOptions.PreDragCondition
            public void onPreDragStart(DropTarget.DragObject dragObject) {
                if (PopupContainerWithArrow.this.mIsAboveIcon) {
                    PopupContainerWithArrow.this.mOriginalIcon.setIconVisible(false);
                    PopupContainerWithArrow.this.mOriginalIcon.setVisibility(0);
                } else {
                    PopupContainerWithArrow.this.mOriginalIcon.setVisibility(4);
                }
            }

            @Override // com.android.launcher3.dragndrop.DragOptions.PreDragCondition
            public void onPreDragEnd(DropTarget.DragObject dragObject, boolean dragStarted) {
                PopupContainerWithArrow.this.mOriginalIcon.setIconVisible(true);
                if (dragStarted) {
                    PopupContainerWithArrow.this.mOriginalIcon.setVisibility(4);
                    return;
                }
                PopupContainerWithArrow.this.mLauncher.getUserEventDispatcher().logDeepShortcutsOpen(PopupContainerWithArrow.this.mOriginalIcon);
                if (PopupContainerWithArrow.this.mIsAboveIcon) {
                    return;
                }
                PopupContainerWithArrow.this.mOriginalIcon.setVisibility(0);
            }
        };
    }

    @Override // com.android.launcher3.popup.PopupDataProvider.PopupDataChangeListener
    public void onNotificationDotsUpdated(Predicate<PackageUserKey> updatedDots) {
        if (updatedDots.test(PackageUserKey.fromItemInfo((ItemInfo) this.mOriginalIcon.getTag()))) {
            updateNotificationHeader();
        }
    }

    private void updateNotificationHeader() {
        ItemInfoWithIcon itemInfoWithIcon = (ItemInfoWithIcon) this.mOriginalIcon.getTag();
        DotInfo dotInfoForItem = this.mLauncher.getDotInfoForItem(itemInfoWithIcon);
        NotificationItemView notificationItemView = this.mNotificationItemView;
        if (notificationItemView == null || dotInfoForItem == null) {
            return;
        }
        notificationItemView.updateHeader(dotInfoForItem.getNotificationCount(), itemInfoWithIcon.iconColor);
    }

    @Override // com.android.launcher3.popup.PopupDataProvider.PopupDataChangeListener
    public void trimNotifications(Map<PackageUserKey, DotInfo> updatedDots) {
        if (this.mNotificationItemView == null) {
            return;
        }
        DotInfo dotInfo = updatedDots.get(PackageUserKey.fromItemInfo((ItemInfo) this.mOriginalIcon.getTag()));
        if (dotInfo == null || dotInfo.getNotificationKeys().size() == 0) {
            this.mNotificationItemView.removeAllViews();
            this.mNotificationItemView = null;
            updateHiddenShortcuts();
            updateDividers();
            return;
        }
        this.mNotificationItemView.trimNotifications(NotificationKeyData.extractKeysOnly(dotInfo.getNotificationKeys()));
    }

    @Override // com.android.launcher3.dragndrop.DragController.DragListener
    public void onDragStart(DropTarget.DragObject dragObject, DragOptions options) {
        this.mDeferContainerRemoval = true;
        animateClose();
    }

    @Override // com.android.launcher3.dragndrop.DragController.DragListener
    public void onDragEnd() {
        if (this.mIsOpen) {
            return;
        }
        if (this.mOpenCloseAnimator != null) {
            this.mDeferContainerRemoval = false;
        } else if (this.mDeferContainerRemoval) {
            closeComplete();
        }
    }

    @Override // com.android.launcher3.logging.StatsLogUtils.LogContainerProvider
    public void fillInLogContainerData(View v, ItemInfo info, LauncherLogProto.Target target, LauncherLogProto.Target targetParent) {
        if (info == NotificationMainView.NOTIFICATION_ITEM_INFO) {
            target.itemType = 8;
        } else {
            target.itemType = 5;
            target.rank = info.rank;
        }
        targetParent.containerType = 9;
    }

    @Override // com.android.launcher3.popup.ArrowPopup
    protected void onCreateCloseAnimation(AnimatorSet anim) {
        anim.play(this.mOriginalIcon.createTextAlphaAnimator(true));
        this.mOriginalIcon.forceHideDot(false);
    }

    @Override // com.android.launcher3.popup.ArrowPopup
    protected void closeComplete() {
        super.closeComplete();
        BubbleTextView bubbleTextView = this.mOriginalIcon;
        bubbleTextView.setTextVisibility(bubbleTextView.shouldTextBeVisible());
        this.mOriginalIcon.forceHideDot(false);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent ev) {
        int action = ev.getAction();
        if (action != 0 && action != 2) {
            return false;
        }
        this.mIconLastTouchPos.set((int) ev.getX(), (int) ev.getY());
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        if (!ItemLongClickListener.canStartDrag(this.mLauncher) || !(v.getParent() instanceof DeepShortcutView)) {
            return false;
        }
        DeepShortcutView deepShortcutView = (DeepShortcutView) v.getParent();
        deepShortcutView.setWillDrawIcon(false);
        Point point = new Point();
        point.x = this.mIconLastTouchPos.x - deepShortcutView.getIconCenter().x;
        point.y = this.mIconLastTouchPos.y - this.mLauncher.getDeviceProfile().iconSizePx;
        this.mLauncher.getWorkspace().beginDragShared(deepShortcutView.getIconView(), this, deepShortcutView.getFinalInfo(), new ShortcutDragPreviewProvider(deepShortcutView.getIconView(), point), new DragOptions()).animateShift(-point.x, -point.y);
        AbstractFloatingView.closeOpenContainer(this.mLauncher, 1);
        return false;
    }

    public static PopupContainerWithArrow getOpen(Launcher launcher) {
        return (PopupContainerWithArrow) getOpenView(launcher, 2);
    }
}
