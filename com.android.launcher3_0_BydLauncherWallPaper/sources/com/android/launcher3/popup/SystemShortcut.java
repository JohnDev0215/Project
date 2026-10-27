package com.android.launcher3.popup;

import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.BaseDraggingActivity;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.popup.SystemShortcut;
import com.android.launcher3.util.InstantAppResolver;
import com.android.launcher3.util.PackageManagerHelper;
import com.android.launcher3.util.PackageUserKey;
import com.android.launcher3.widget.WidgetsBottomSheet;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public abstract class SystemShortcut<T extends BaseDraggingActivity> extends ItemInfo {
    private final int mAccessibilityActionId;
    private final CharSequence mContentDescription;
    private final Icon mIcon;
    private final int mIconResId;
    private final CharSequence mLabel;
    private final int mLabelResId;

    public abstract View.OnClickListener getOnClickListener(T activity, ItemInfo itemInfo);

    public boolean isLeftGroup() {
        return false;
    }

    public SystemShortcut(int iconResId, int labelResId) {
        this.mIconResId = iconResId;
        this.mLabelResId = labelResId;
        this.mAccessibilityActionId = labelResId;
        this.mIcon = null;
        this.mLabel = null;
        this.mContentDescription = null;
    }

    public SystemShortcut(Icon icon, CharSequence label, CharSequence contentDescription, int accessibilityActionId) {
        this.mIcon = icon;
        this.mLabel = label;
        this.mContentDescription = contentDescription;
        this.mAccessibilityActionId = accessibilityActionId;
        this.mIconResId = 0;
        this.mLabelResId = 0;
    }

    public SystemShortcut(SystemShortcut other) {
        this.mIconResId = other.mIconResId;
        this.mLabelResId = other.mLabelResId;
        this.mIcon = other.mIcon;
        this.mLabel = other.mLabel;
        this.mContentDescription = other.mContentDescription;
        this.mAccessibilityActionId = other.mAccessibilityActionId;
    }

    public void setIconAndLabelFor(final View iconView, TextView labelView) {
        Icon icon = this.mIcon;
        if (icon != null) {
            Context context = iconView.getContext();
            Objects.requireNonNull(iconView);
            icon.loadDrawableAsync(context, new Icon.OnDrawableLoadedListener() { // from class: com.android.launcher3.popup.-$$Lambda$SystemShortcut$YQUSqBib_Z_haAiXSD7u1e4Xb9M
                @Override // android.graphics.drawable.Icon.OnDrawableLoadedListener
                public final void onDrawableLoaded(Drawable drawable) {
                    iconView.setBackground(drawable);
                }
            }, new Handler(Looper.getMainLooper()));
        } else {
            iconView.setBackgroundResource(this.mIconResId);
        }
        CharSequence charSequence = this.mLabel;
        if (charSequence != null) {
            labelView.setText(charSequence);
        } else {
            labelView.setText(this.mLabelResId);
        }
    }

    public void setIconAndContentDescriptionFor(final ImageView view) {
        Icon icon = this.mIcon;
        if (icon != null) {
            Context context = view.getContext();
            Objects.requireNonNull(view);
            icon.loadDrawableAsync(context, new Icon.OnDrawableLoadedListener() { // from class: com.android.launcher3.popup.-$$Lambda$SystemShortcut$25Kk0e1_5g04GEQ-72yhX_KkG_k
                @Override // android.graphics.drawable.Icon.OnDrawableLoadedListener
                public final void onDrawableLoaded(Drawable drawable) {
                    view.setImageDrawable(drawable);
                }
            }, new Handler(Looper.getMainLooper()));
        } else {
            view.setImageResource(this.mIconResId);
        }
        view.setContentDescription(getContentDescription(view.getContext()));
    }

    private CharSequence getContentDescription(Context context) {
        CharSequence charSequence = this.mContentDescription;
        return charSequence != null ? charSequence : context.getText(this.mLabelResId);
    }

    public AccessibilityNodeInfo.AccessibilityAction createAccessibilityAction(Context context) {
        return new AccessibilityNodeInfo.AccessibilityAction(this.mAccessibilityActionId, getContentDescription(context));
    }

    public boolean hasHandlerForAction(int action) {
        return this.mAccessibilityActionId == action;
    }

    public static class Widgets extends SystemShortcut<Launcher> {
        public Widgets() {
            super(R.drawable.ic_widget, R.string.widget_button_text);
        }

        @Override // com.android.launcher3.popup.SystemShortcut
        public View.OnClickListener getOnClickListener(final Launcher launcher, final ItemInfo itemInfo) {
            if (launcher.getPopupDataProvider().getWidgetsForPackageUser(new PackageUserKey(itemInfo.getTargetComponent().getPackageName(), itemInfo.user)) == null) {
                return null;
            }
            return new View.OnClickListener() { // from class: com.android.launcher3.popup.-$$Lambda$SystemShortcut$Widgets$ycf5e3weKZiNlhHkxuzJEB2YfVY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SystemShortcut.Widgets.lambda$getOnClickListener$0(launcher, itemInfo, view);
                }
            };
        }

        static /* synthetic */ void lambda$getOnClickListener$0(Launcher launcher, ItemInfo itemInfo, View view) {
            AbstractFloatingView.closeAllOpenViews(launcher);
            ((WidgetsBottomSheet) launcher.getLayoutInflater().inflate(R.layout.widgets_bottom_sheet, (ViewGroup) launcher.getDragLayer(), false)).populateAndShow(itemInfo);
            launcher.getUserEventDispatcher().logActionOnControl(0, 2, view);
        }
    }

    public static class AppInfo extends SystemShortcut {
        public AppInfo() {
            super(R.drawable.ic_info_no_shadow, R.string.app_info_drop_target_label);
        }

        @Override // com.android.launcher3.popup.SystemShortcut
        public View.OnClickListener getOnClickListener(final BaseDraggingActivity activity, final ItemInfo itemInfo) {
            return new View.OnClickListener() { // from class: com.android.launcher3.popup.-$$Lambda$SystemShortcut$AppInfo$8b7AehMAx2qpfHlbVsLpwVsrmys
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SystemShortcut.AppInfo.lambda$getOnClickListener$0(activity, itemInfo, view);
                }
            };
        }

        static /* synthetic */ void lambda$getOnClickListener$0(BaseDraggingActivity baseDraggingActivity, ItemInfo itemInfo, View view) {
            dismissTaskMenuView(baseDraggingActivity);
            new PackageManagerHelper(baseDraggingActivity).startDetailsActivityForInfo(itemInfo, baseDraggingActivity.getViewBounds(view), ActivityOptions.makeBasic().toBundle());
            baseDraggingActivity.getUserEventDispatcher().logActionOnControl(0, 7, view);
        }
    }

    public static class Install extends SystemShortcut {
        public Install() {
            super(R.drawable.ic_install_no_shadow, R.string.install_drop_target_label);
        }

        @Override // com.android.launcher3.popup.SystemShortcut
        public View.OnClickListener getOnClickListener(BaseDraggingActivity activity, ItemInfo itemInfo) {
            boolean z = true;
            boolean z2 = (itemInfo instanceof WorkspaceItemInfo) && ((WorkspaceItemInfo) itemInfo).hasStatusFlag(16);
            boolean zIsInstantApp = itemInfo instanceof com.android.launcher3.AppInfo ? InstantAppResolver.newInstance(activity).isInstantApp((com.android.launcher3.AppInfo) itemInfo) : false;
            if (!z2 && !zIsInstantApp) {
                z = false;
            }
            if (z) {
                return createOnClickListener(activity, itemInfo);
            }
            return null;
        }

        public View.OnClickListener createOnClickListener(final BaseDraggingActivity activity, final ItemInfo itemInfo) {
            return new View.OnClickListener() { // from class: com.android.launcher3.popup.-$$Lambda$SystemShortcut$Install$zvVOCzo_b5gyPCexlavdNAWMjA4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SystemShortcut.Install.lambda$createOnClickListener$0(itemInfo, activity, view);
                }
            };
        }

        static /* synthetic */ void lambda$createOnClickListener$0(ItemInfo itemInfo, BaseDraggingActivity baseDraggingActivity, View view) {
            baseDraggingActivity.lambda$startActivitySafely$0$Launcher(view, new PackageManagerHelper(view.getContext()).getMarketIntent(itemInfo.getTargetComponent().getPackageName()), itemInfo, null);
            AbstractFloatingView.closeAllOpenViews(baseDraggingActivity);
        }
    }

    protected static void dismissTaskMenuView(BaseDraggingActivity activity) {
        AbstractFloatingView.closeOpenViews(activity, true, 1935);
    }
}
