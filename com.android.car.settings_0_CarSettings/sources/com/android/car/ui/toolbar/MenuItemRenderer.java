package com.android.car.ui.toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.core.util.Consumer;
import com.android.car.ui.R;
import com.android.car.ui.toolbar.MenuItem;
import com.android.car.ui.toolbar.Toolbar;
import com.android.car.ui.utils.CarUiUtils;
import com.android.car.ui.uxr.DrawableStateView;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes6.dex */
class MenuItemRenderer implements MenuItem.Listener {
    private static final int[] RESTRICTED_STATE = {R.attr.state_ux_restricted};
    private View mIconContainer;
    private ImageView mIconView;
    private boolean mIndividualClickListeners;
    private final MenuItem mMenuItem;
    private final int mMenuItemIconSize;
    private final ViewGroup mParentView;
    private Switch mSwitch;
    private TextView mTextView;
    private TextView mTextWithIconView;
    private Toolbar.State mToolbarState;
    private View mView;

    MenuItemRenderer(MenuItem item, ViewGroup parentView) {
        this.mMenuItem = item;
        this.mParentView = parentView;
        item.setListener(this);
        this.mMenuItemIconSize = parentView.getContext().getResources().getDimensionPixelSize(R.dimen.car_ui_toolbar_menu_item_icon_size);
    }

    void setToolbarState(Toolbar.State state) {
        this.mToolbarState = state;
        if (this.mMenuItem.isSearch()) {
            updateView();
        }
    }

    @Override // com.android.car.ui.toolbar.MenuItem.Listener
    public void onMenuItemChanged(MenuItem changedItem) {
        updateView();
    }

    void createView(final Consumer<View> callback) {
        int layout;
        AsyncLayoutInflater inflater = new AsyncLayoutInflater(this.mParentView.getContext());
        if (this.mMenuItem.isPrimary()) {
            layout = R.layout.car_ui_toolbar_menu_item_primary;
        } else {
            layout = R.layout.car_ui_toolbar_menu_item;
        }
        inflater.inflate(layout, this.mParentView, new AsyncLayoutInflater.OnInflateFinishedListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$MenuItemRenderer$p92KjVA5uetSnb4q9D1Fz51yLs0
            @Override // androidx.asynclayoutinflater.view.AsyncLayoutInflater.OnInflateFinishedListener
            public final void onInflateFinished(View view, int i, ViewGroup viewGroup) {
                this.f$0.lambda$createView$0$MenuItemRenderer(callback, view, i, viewGroup);
            }
        });
    }

    public /* synthetic */ void lambda$createView$0$MenuItemRenderer(Consumer callback, View view, int resid, ViewGroup parent) {
        this.mView = view;
        this.mIconContainer = CarUiUtils.requireViewByRefId(view, R.id.car_ui_toolbar_menu_item_icon_container);
        this.mIconView = (ImageView) CarUiUtils.requireViewByRefId(this.mView, R.id.car_ui_toolbar_menu_item_icon);
        this.mSwitch = (Switch) CarUiUtils.requireViewByRefId(this.mView, R.id.car_ui_toolbar_menu_item_switch);
        this.mTextView = (TextView) CarUiUtils.requireViewByRefId(this.mView, R.id.car_ui_toolbar_menu_item_text);
        this.mTextWithIconView = (TextView) CarUiUtils.requireViewByRefId(this.mView, R.id.car_ui_toolbar_menu_item_text_with_icon);
        this.mIndividualClickListeners = this.mView.getContext().getResources().getBoolean(R.bool.car_ui_toolbar_menuitem_individual_click_listeners);
        updateView();
        callback.accept(this.mView);
    }

    private void updateView() {
        View clickTarget;
        View view = this.mView;
        if (view == null) {
            return;
        }
        view.setId(this.mMenuItem.getId());
        boolean hasIcon = this.mMenuItem.getIcon() != null;
        boolean hasText = true ^ TextUtils.isEmpty(this.mMenuItem.getTitle());
        boolean textAndIcon = this.mMenuItem.isShowingIconAndTitle();
        boolean checkable = this.mMenuItem.isCheckable();
        if (!this.mMenuItem.isVisible() || ((this.mMenuItem.isSearch() && this.mToolbarState == Toolbar.State.SEARCH) || (!checkable && !hasIcon && !hasText))) {
            this.mView.setVisibility(8);
            return;
        }
        this.mView.setVisibility(0);
        this.mView.setContentDescription(this.mMenuItem.getTitle());
        if (checkable) {
            this.mSwitch.setChecked(this.mMenuItem.isChecked());
            clickTarget = this.mSwitch;
        } else if (hasText && hasIcon && textAndIcon) {
            Drawable icon = this.mMenuItem.getIcon();
            int i = this.mMenuItemIconSize;
            icon.setBounds(0, 0, i, i);
            this.mTextWithIconView.setCompoundDrawables(this.mMenuItem.getIcon(), null, null, null);
            this.mTextWithIconView.setText(this.mMenuItem.getTitle());
            clickTarget = this.mTextWithIconView;
        } else if (hasIcon) {
            this.mIconView.setImageDrawable(this.mMenuItem.getIcon());
            clickTarget = this.mIconContainer;
        } else {
            this.mTextView.setText(this.mMenuItem.getTitle());
            clickTarget = this.mTextView;
        }
        View view2 = this.mIconContainer;
        view2.setVisibility(clickTarget == view2 ? 0 : 8);
        TextView textView = this.mTextView;
        textView.setVisibility(clickTarget == textView ? 0 : 8);
        TextView textView2 = this.mTextWithIconView;
        textView2.setVisibility(clickTarget == textView2 ? 0 : 8);
        Switch r8 = this.mSwitch;
        r8.setVisibility(clickTarget == r8 ? 0 : 8);
        if (!this.mIndividualClickListeners) {
            clickTarget = this.mView;
        }
        if (!this.mMenuItem.isTinted() && hasIcon) {
            this.mMenuItem.getIcon().setTintList(null);
        }
        recursiveSetEnabledAndDrawableState(this.mView);
        this.mView.setActivated(this.mMenuItem.isActivated());
        if (this.mMenuItem.getOnClickListener() != null || this.mMenuItem.isCheckable() || this.mMenuItem.isActivatable()) {
            clickTarget.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$MenuItemRenderer$ql8WLabAg_CiY9HcQTI4ML8FsgY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    this.f$0.lambda$updateView$1$MenuItemRenderer(view3);
                }
            });
            return;
        }
        View view3 = this.mView;
        if (clickTarget == view3) {
            view3.setOnClickListener(null);
            this.mView.setClickable(false);
        }
    }

    public /* synthetic */ void lambda$updateView$1$MenuItemRenderer(View v) {
        this.mMenuItem.performClick();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void recursiveSetEnabledAndDrawableState(View view) {
        view.setEnabled(this.mMenuItem.isEnabled());
        int[] drawableState = this.mMenuItem.isRestricted() ? RESTRICTED_STATE : null;
        if (view instanceof ImageView) {
            ((ImageView) view).setImageState(drawableState, true);
        } else if (view instanceof DrawableStateView) {
            ((DrawableStateView) view).setExtraDrawableState(drawableState, null);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                recursiveSetEnabledAndDrawableState(viewGroup.getChildAt(i));
            }
        }
    }

    static List<MenuItem> readMenuItemList(Context c, int resId) {
        if (resId == 0) {
            return new ArrayList();
        }
        try {
            XmlResourceParser parser = c.getResources().getXml(resId);
            try {
                AttributeSet attrs = Xml.asAttributeSet(parser);
                List<MenuItem> menuItems = new ArrayList<>();
                parser.next();
                parser.next();
                parser.require(2, null, "MenuItems");
                while (parser.next() != 3) {
                    menuItems.add(readMenuItem(c, parser, attrs));
                }
                if (parser != null) {
                    parser.close();
                }
                return menuItems;
            } catch (Throwable th) {
                if (parser != null) {
                    try {
                        parser.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException | XmlPullParserException e) {
            throw new RuntimeException("Unable to parse Menu Items", e);
        }
    }

    private static MenuItem readMenuItem(Context c, XmlResourceParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
        MenuItem.OnClickListener onClickListener;
        MenuItem.DisplayBehavior displayBehavior;
        boolean checked;
        parser.require(2, null, "MenuItem");
        TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.CarUiToolbarMenuItem);
        try {
            int id = a.getResourceId(R.styleable.CarUiToolbarMenuItem_id, -1);
            String title = a.getString(R.styleable.CarUiToolbarMenuItem_title);
            Drawable icon = a.getDrawable(R.styleable.CarUiToolbarMenuItem_icon);
            boolean isSearch = a.getBoolean(R.styleable.CarUiToolbarMenuItem_search, false);
            boolean isSettings = a.getBoolean(R.styleable.CarUiToolbarMenuItem_settings, false);
            boolean tinted = a.getBoolean(R.styleable.CarUiToolbarMenuItem_tinted, true);
            boolean visible = a.getBoolean(R.styleable.CarUiToolbarMenuItem_visible, true);
            boolean showIconAndTitle = a.getBoolean(R.styleable.CarUiToolbarMenuItem_showIconAndTitle, false);
            boolean checkable = a.getBoolean(R.styleable.CarUiToolbarMenuItem_checkable, false);
            boolean checked2 = a.getBoolean(R.styleable.CarUiToolbarMenuItem_checked, false);
            boolean checkedExists = a.hasValue(R.styleable.CarUiToolbarMenuItem_checked);
            boolean activatable = a.getBoolean(R.styleable.CarUiToolbarMenuItem_activatable, false);
            boolean activated = a.getBoolean(R.styleable.CarUiToolbarMenuItem_activated, false);
            boolean activatedExists = a.hasValue(R.styleable.CarUiToolbarMenuItem_activated);
            int displayBehaviorInt = a.getInt(R.styleable.CarUiToolbarMenuItem_displayBehavior, 0);
            int uxRestrictions = a.getInt(R.styleable.CarUiToolbarMenuItem_uxRestrictions, 0);
            String onClickMethod = a.getString(R.styleable.CarUiToolbarMenuItem_onClick);
            if (onClickMethod == null) {
                onClickListener = null;
            } else {
                final Activity activity = CarUiUtils.getActivity(c);
                if (activity != null) {
                    try {
                        final Method m = activity.getClass().getMethod(onClickMethod, MenuItem.class);
                        MenuItem.OnClickListener onClickListener2 = new MenuItem.OnClickListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$MenuItemRenderer$o4Q9ccZfugknqOARB2tvDnC1XDw
                            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
                            public final void onClick(MenuItem menuItem) {
                                MenuItemRenderer.lambda$readMenuItem$2(m, activity, menuItem);
                            }
                        };
                        onClickListener = onClickListener2;
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException("OnClick method " + onClickMethod + "(MenuItem) not found in your activity", e);
                    }
                } else {
                    throw new RuntimeException("Couldn't find an activity for the MenuItem");
                }
            }
            if (displayBehaviorInt == 0) {
                displayBehavior = MenuItem.DisplayBehavior.ALWAYS;
            } else {
                displayBehavior = MenuItem.DisplayBehavior.NEVER;
            }
            parser.next();
            parser.require(3, null, "MenuItem");
            MenuItem.Builder builder = MenuItem.builder(c).setId(id).setTitle(title).setIcon(icon).setOnClickListener(onClickListener).setUxRestrictions(uxRestrictions).setTinted(tinted).setVisible(visible).setShowIconAndTitle(showIconAndTitle).setDisplayBehavior(displayBehavior);
            if (isSearch) {
                builder.setToSearch();
            }
            if (isSettings) {
                builder.setToSettings();
            }
            if (checkable || checkedExists) {
                checked = checked2;
                builder.setChecked(checked);
            } else {
                checked = checked2;
            }
            if (activatable || activatedExists) {
                builder.setActivated(activated);
            }
            return builder.build();
        } finally {
            a.recycle();
        }
    }

    static /* synthetic */ void lambda$readMenuItem$2(Method m, Activity activity, MenuItem i) {
        try {
            m.invoke(activity, i);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Couldn't call the MenuItem's listener", e);
        }
    }
}
