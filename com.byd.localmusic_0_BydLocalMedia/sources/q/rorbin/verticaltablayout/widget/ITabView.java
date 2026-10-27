package q.rorbin.verticaltablayout.widget;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.badge.BadgeDrawable;
import q.rorbin.badgeview.Badge;

/* JADX INFO: loaded from: classes5.dex */
public interface ITabView {
    TabBadge getBadge();

    TabIcon getIcon();

    View getTabView();

    TabTitle getTitle();

    ITabView setBackground(int i);

    ITabView setBadge(TabBadge tabBadge);

    ITabView setIcon(TabIcon tabIcon);

    ITabView setTitle(TabTitle tabTitle);

    public static class TabIcon {
        private Builder mBuilder;

        private TabIcon(Builder builder) {
            this.mBuilder = builder;
        }

        public int getSelectedIcon() {
            return this.mBuilder.mSelectedIcon;
        }

        public int getNormalIcon() {
            return this.mBuilder.mNormalIcon;
        }

        public int getIconGravity() {
            return this.mBuilder.mIconGravity;
        }

        public int getIconWidth() {
            return this.mBuilder.mIconWidth;
        }

        public int getIconHeight() {
            return this.mBuilder.mIconHeight;
        }

        public int getMargin() {
            return this.mBuilder.mMargin;
        }

        public static class Builder {
            private int mSelectedIcon = 0;
            private int mNormalIcon = 0;
            private int mIconWidth = -1;
            private int mIconHeight = -1;
            private int mIconGravity = 8388611;
            private int mMargin = 0;

            public Builder setIcon(int i, int i2) {
                this.mSelectedIcon = i;
                this.mNormalIcon = i2;
                return this;
            }

            public Builder setIconSize(int i, int i2) {
                this.mIconWidth = i;
                this.mIconHeight = i2;
                return this;
            }

            public Builder setIconGravity(int i) {
                if (i != 8388611) {
                    if ((i != 8388613) & (i != 48) & (i != 80)) {
                        throw new IllegalStateException("iconGravity only support Gravity.START or Gravity.END or Gravity.TOP or Gravity.BOTTOM");
                    }
                }
                this.mIconGravity = i;
                return this;
            }

            public Builder setIconMargin(int i) {
                this.mMargin = i;
                return this;
            }

            public TabIcon build() {
                return new TabIcon(this);
            }
        }
    }

    public static class TabTitle {
        private Builder mBuilder;

        private TabTitle(Builder builder) {
            this.mBuilder = builder;
        }

        public int getColorSelected() {
            return this.mBuilder.mColorSelected;
        }

        public int getColorNormal() {
            return this.mBuilder.mColorNormal;
        }

        public int getTitleTextSize() {
            return this.mBuilder.mTitleTextSize;
        }

        public String getContent() {
            return this.mBuilder.mContent;
        }

        public static class Builder {
            private int mColorSelected = -49023;
            private int mColorNormal = -9079435;
            private int mTitleTextSize = 16;
            private String mContent = "";

            public Builder setTextColor(int i, int i2) {
                this.mColorSelected = i;
                this.mColorNormal = i2;
                return this;
            }

            public Builder setTextSize(int i) {
                this.mTitleTextSize = i;
                return this;
            }

            public Builder setContent(String str) {
                this.mContent = str;
                return this;
            }

            public TabTitle build() {
                return new TabTitle(this);
            }
        }
    }

    public static class TabBadge {
        private Builder mBuilder;

        private TabBadge(Builder builder) {
            this.mBuilder = builder;
        }

        public int getBackgroundColor() {
            return this.mBuilder.colorBackground;
        }

        public int getBadgeTextColor() {
            return this.mBuilder.colorBadgeText;
        }

        public float getBadgeTextSize() {
            return this.mBuilder.badgeTextSize;
        }

        public float getBadgePadding() {
            return this.mBuilder.badgePadding;
        }

        public int getBadgeNumber() {
            return this.mBuilder.badgeNumber;
        }

        public String getBadgeText() {
            return this.mBuilder.badgeText;
        }

        public int getBadgeGravity() {
            return this.mBuilder.badgeGravity;
        }

        public int getGravityOffsetX() {
            return this.mBuilder.gravityOffsetX;
        }

        public int getGravityOffsetY() {
            return this.mBuilder.gravityOffsetY;
        }

        public boolean isExactMode() {
            return this.mBuilder.exactMode;
        }

        public boolean isShowShadow() {
            return this.mBuilder.showShadow;
        }

        public Drawable getDrawableBackground() {
            return this.mBuilder.drawableBackground;
        }

        public int getStrokeColor() {
            return this.mBuilder.colorStroke;
        }

        public boolean isDrawableBackgroundClip() {
            return this.mBuilder.drawableBackgroundClip;
        }

        public float getStrokeWidth() {
            return this.mBuilder.strokeWidth;
        }

        public Badge.OnDragStateChangedListener getOnDragStateChangedListener() {
            return this.mBuilder.dragStateChangedListener;
        }

        public static class Builder {
            private Badge.OnDragStateChangedListener dragStateChangedListener;
            private int colorBackground = -1552832;
            private int colorBadgeText = -1;
            private int colorStroke = 0;
            private Drawable drawableBackground = null;
            private boolean drawableBackgroundClip = false;
            private float strokeWidth = 0.0f;
            private float badgeTextSize = 11.0f;
            private float badgePadding = 5.0f;
            private int badgeNumber = 0;
            private String badgeText = null;
            private int badgeGravity = BadgeDrawable.TOP_END;
            private int gravityOffsetX = 1;
            private int gravityOffsetY = 1;
            private boolean exactMode = false;
            private boolean showShadow = true;

            public TabBadge build() {
                return new TabBadge(this);
            }

            public Builder stroke(int i, int i2) {
                this.colorStroke = i;
                this.strokeWidth = i2;
                return this;
            }

            public Builder setDrawableBackground(Drawable drawable, boolean z) {
                this.drawableBackground = drawable;
                this.drawableBackgroundClip = z;
                return this;
            }

            public Builder setShowShadow(boolean z) {
                this.showShadow = z;
                return this;
            }

            public Builder setOnDragStateChangedListener(Badge.OnDragStateChangedListener onDragStateChangedListener) {
                this.dragStateChangedListener = onDragStateChangedListener;
                return this;
            }

            public Builder setExactMode(boolean z) {
                this.exactMode = z;
                return this;
            }

            public Builder setBackgroundColor(int i) {
                this.colorBackground = i;
                return this;
            }

            public Builder setBadgePadding(float f) {
                this.badgePadding = f;
                return this;
            }

            public Builder setBadgeNumber(int i) {
                this.badgeNumber = i;
                this.badgeText = null;
                return this;
            }

            public Builder setBadgeGravity(int i) {
                this.badgeGravity = i;
                return this;
            }

            public Builder setBadgeTextColor(int i) {
                this.colorBadgeText = i;
                return this;
            }

            public Builder setBadgeTextSize(float f) {
                this.badgeTextSize = f;
                return this;
            }

            public Builder setBadgeText(String str) {
                this.badgeText = str;
                this.badgeNumber = 0;
                return this;
            }

            public Builder setGravityOffset(int i, int i2) {
                this.gravityOffsetX = i;
                this.gravityOffsetY = i2;
                return this;
            }
        }
    }
}
