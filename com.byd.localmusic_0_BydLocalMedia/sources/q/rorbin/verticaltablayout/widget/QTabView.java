package q.rorbin.verticaltablayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.DisplayUtil;
import q.rorbin.verticaltablayout.widget.ITabView;

/* JADX INFO: loaded from: classes5.dex */
public class QTabView extends TabView {
    private Badge mBadgeView;
    private boolean mChecked;
    private Context mContext;
    private Drawable mDefaultBackground;
    private ITabView.TabBadge mTabBadge;
    private ITabView.TabIcon mTabIcon;
    private ITabView.TabTitle mTabTitle;
    private TextView mTitle;

    @Override // q.rorbin.verticaltablayout.widget.TabView
    @Deprecated
    public ImageView getIconView() {
        return null;
    }

    public QTabView(Context context) {
        super(context);
        this.mContext = context;
        this.mTabIcon = new ITabView.TabIcon.Builder().build();
        this.mTabTitle = new ITabView.TabTitle.Builder().build();
        this.mTabBadge = new ITabView.TabBadge.Builder().build();
        initView();
        TypedArray typedArrayObtainStyledAttributes = this.mContext.getTheme().obtainStyledAttributes(Build.VERSION.SDK_INT >= 21 ? new int[]{16843868} : new int[]{16843534});
        this.mDefaultBackground = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
        setDefaultBackground();
    }

    private void initView() {
        setMinimumHeight(DisplayUtil.dp2px(this.mContext, 25.0f));
        if (this.mTitle == null) {
            this.mTitle = new TextView(this.mContext);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -1);
            layoutParams.gravity = 17;
            this.mTitle.setLayoutParams(layoutParams);
            addView(this.mTitle);
        }
        initTitleView();
        initIconView();
        initBadge();
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        this.mTitle.setPaddingRelative(i, i2, i3, i4);
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.mTitle.setPadding(i, i2, i3, i4);
    }

    private void initBadge() {
        this.mBadgeView = TabBadgeView.bindTab(this);
        if (this.mTabBadge.getBackgroundColor() != -1552832) {
            this.mBadgeView.setBadgeBackgroundColor(this.mTabBadge.getBackgroundColor());
        }
        if (this.mTabBadge.getBadgeTextColor() != -1) {
            this.mBadgeView.setBadgeTextColor(this.mTabBadge.getBadgeTextColor());
        }
        if (this.mTabBadge.getStrokeColor() != 0 || this.mTabBadge.getStrokeWidth() != 0.0f) {
            this.mBadgeView.stroke(this.mTabBadge.getStrokeColor(), this.mTabBadge.getStrokeWidth(), true);
        }
        if (this.mTabBadge.getDrawableBackground() != null || this.mTabBadge.isDrawableBackgroundClip()) {
            this.mBadgeView.setBadgeBackground(this.mTabBadge.getDrawableBackground(), this.mTabBadge.isDrawableBackgroundClip());
        }
        if (this.mTabBadge.getBadgeTextSize() != 11.0f) {
            this.mBadgeView.setBadgeTextSize(this.mTabBadge.getBadgeTextSize(), true);
        }
        if (this.mTabBadge.getBadgePadding() != 5.0f) {
            this.mBadgeView.setBadgePadding(this.mTabBadge.getBadgePadding(), true);
        }
        if (this.mTabBadge.getBadgeNumber() != 0) {
            this.mBadgeView.setBadgeNumber(this.mTabBadge.getBadgeNumber());
        }
        if (this.mTabBadge.getBadgeText() != null) {
            this.mBadgeView.setBadgeText(this.mTabBadge.getBadgeText());
        }
        if (this.mTabBadge.getBadgeGravity() != 8388661) {
            this.mBadgeView.setBadgeGravity(this.mTabBadge.getBadgeGravity());
        }
        if (this.mTabBadge.getGravityOffsetX() != 5 || this.mTabBadge.getGravityOffsetY() != 5) {
            this.mBadgeView.setGravityOffset(this.mTabBadge.getGravityOffsetX(), this.mTabBadge.getGravityOffsetY(), true);
        }
        if (this.mTabBadge.isExactMode()) {
            this.mBadgeView.setExactMode(this.mTabBadge.isExactMode());
        }
        if (!this.mTabBadge.isShowShadow()) {
            this.mBadgeView.setShowShadow(this.mTabBadge.isShowShadow());
        }
        if (this.mTabBadge.getOnDragStateChangedListener() != null) {
            this.mBadgeView.setOnDragStateChangedListener(this.mTabBadge.getOnDragStateChangedListener());
        }
    }

    private void initTitleView() {
        this.mTitle.setTextColor(isChecked() ? this.mTabTitle.getColorSelected() : this.mTabTitle.getColorNormal());
        this.mTitle.setTextSize(this.mTabTitle.getTitleTextSize());
        this.mTitle.setText(this.mTabTitle.getContent());
        this.mTitle.setGravity(17);
        this.mTitle.setEllipsize(TextUtils.TruncateAt.END);
        refreshDrawablePadding();
    }

    private void initIconView() {
        Drawable drawable;
        int selectedIcon = this.mChecked ? this.mTabIcon.getSelectedIcon() : this.mTabIcon.getNormalIcon();
        if (selectedIcon != 0) {
            drawable = this.mContext.getResources().getDrawable(selectedIcon);
            drawable.setBounds(0, 0, this.mTabIcon.getIconWidth() != -1 ? this.mTabIcon.getIconWidth() : drawable.getIntrinsicWidth(), this.mTabIcon.getIconHeight() != -1 ? this.mTabIcon.getIconHeight() : drawable.getIntrinsicHeight());
        } else {
            drawable = null;
        }
        int iconGravity = this.mTabIcon.getIconGravity();
        if (iconGravity == 48) {
            this.mTitle.setCompoundDrawables(null, drawable, null, null);
        } else if (iconGravity == 80) {
            this.mTitle.setCompoundDrawables(null, null, null, drawable);
        } else if (iconGravity == 8388611) {
            this.mTitle.setCompoundDrawables(drawable, null, null, null);
        } else if (iconGravity == 8388613) {
            this.mTitle.setCompoundDrawables(null, null, drawable, null);
        }
        refreshDrawablePadding();
    }

    private void refreshDrawablePadding() {
        if ((this.mChecked ? this.mTabIcon.getSelectedIcon() : this.mTabIcon.getNormalIcon()) != 0) {
            if (!TextUtils.isEmpty(this.mTabTitle.getContent()) && this.mTitle.getCompoundDrawablePadding() != this.mTabIcon.getMargin()) {
                this.mTitle.setCompoundDrawablePadding(this.mTabIcon.getMargin());
                return;
            } else {
                if (TextUtils.isEmpty(this.mTabTitle.getContent())) {
                    this.mTitle.setCompoundDrawablePadding(0);
                    return;
                }
                return;
            }
        }
        this.mTitle.setCompoundDrawablePadding(0);
    }

    @Override // q.rorbin.verticaltablayout.widget.ITabView
    public QTabView setBadge(ITabView.TabBadge tabBadge) {
        if (tabBadge != null) {
            this.mTabBadge = tabBadge;
        }
        initBadge();
        return this;
    }

    @Override // q.rorbin.verticaltablayout.widget.ITabView
    public QTabView setIcon(ITabView.TabIcon tabIcon) {
        if (tabIcon != null) {
            this.mTabIcon = tabIcon;
        }
        initIconView();
        return this;
    }

    @Override // q.rorbin.verticaltablayout.widget.ITabView
    public QTabView setTitle(ITabView.TabTitle tabTitle) {
        if (tabTitle != null) {
            this.mTabTitle = tabTitle;
        }
        initTitleView();
        return this;
    }

    @Override // q.rorbin.verticaltablayout.widget.ITabView
    public QTabView setBackground(int i) {
        if (i == 0) {
            setDefaultBackground();
        } else if (i <= 0) {
            setBackground((Drawable) null);
        } else {
            super.setBackgroundResource(i);
        }
        return this;
    }

    @Override // q.rorbin.verticaltablayout.widget.ITabView
    public ITabView.TabBadge getBadge() {
        return this.mTabBadge;
    }

    @Override // q.rorbin.verticaltablayout.widget.ITabView
    public ITabView.TabIcon getIcon() {
        return this.mTabIcon;
    }

    @Override // q.rorbin.verticaltablayout.widget.ITabView
    public ITabView.TabTitle getTitle() {
        return this.mTabTitle;
    }

    @Override // q.rorbin.verticaltablayout.widget.TabView
    public TextView getTitleView() {
        return this.mTitle;
    }

    @Override // q.rorbin.verticaltablayout.widget.TabView
    public Badge getBadgeView() {
        return this.mBadgeView;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.setBackground(drawable);
        } else {
            super.setBackgroundDrawable(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        setBackground(i);
    }

    private void setDefaultBackground() {
        Drawable background = getBackground();
        Drawable drawable = this.mDefaultBackground;
        if (background != drawable) {
            setBackground(drawable);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        this.mChecked = z;
        setSelected(z);
        refreshDrawableState();
        this.mTitle.setTextColor(z ? this.mTabTitle.getColorSelected() : this.mTabTitle.getColorNormal());
        initIconView();
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.mChecked;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.mChecked);
    }
}
