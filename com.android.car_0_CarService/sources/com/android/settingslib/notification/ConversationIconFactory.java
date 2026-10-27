package com.android.settingslib.notification;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.util.IconDrawableFactory;
import android.util.Log;
import com.android.launcher3.icons.BaseIconFactory;
import com.android.settingslib.R;

/* JADX INFO: loaded from: classes3.dex */
public class ConversationIconFactory extends BaseIconFactory {
    private static final float BADGE_CENTER = 46.0f;
    private static final float BADGE_ORIGIN = 40.0f;
    private static final float BADGE_SIZE = 12.0f;
    private static final float BASE_ICON_SIZE = 56.0f;
    private static final float CIRCLE_MARGIN = 36.0f;
    private static final float CIRCLE_RADIUS;
    private static final float HEAD_SIZE = 52.0f;
    private static final float INN_CIRCLE_DIA;
    private static final float INN_CIRCLE_RAD;
    private static final float OUT_CIRCLE_DIA = 20.0f;
    private static final float OUT_CIRCLE_RAD = 10.0f;
    private static final float RING_STROKE_WIDTH;
    final IconDrawableFactory mIconDrawableFactory;
    private int mImportantConversationColor;
    final LauncherApps mLauncherApps;
    final PackageManager mPackageManager;

    static {
        float fSqrt = (float) Math.sqrt(288.0d);
        INN_CIRCLE_DIA = fSqrt;
        float f = fSqrt / 2.0f;
        INN_CIRCLE_RAD = f;
        CIRCLE_RADIUS = f + ((OUT_CIRCLE_RAD - f) / 2.0f);
        RING_STROKE_WIDTH = (OUT_CIRCLE_DIA - fSqrt) / 2.0f;
    }

    public ConversationIconFactory(Context context, LauncherApps la, PackageManager pm, IconDrawableFactory iconDrawableFactory, int iconSizePx) {
        super(context, context.getResources().getConfiguration().densityDpi, iconSizePx);
        this.mLauncherApps = la;
        this.mPackageManager = pm;
        this.mIconDrawableFactory = iconDrawableFactory;
        this.mImportantConversationColor = context.getResources().getColor(R.color.important_conversation, null);
    }

    public Drawable getBaseIconDrawable(ShortcutInfo shortcutInfo) {
        return this.mLauncherApps.getShortcutIconDrawable(shortcutInfo, this.mFillResIconDpi);
    }

    public Drawable getAppBadge(String packageName, int userId) {
        try {
            ApplicationInfo appInfo = this.mPackageManager.getApplicationInfoAsUser(packageName, 128, userId);
            Drawable badge = this.mIconDrawableFactory.getBadgedIcon(appInfo, userId);
            return badge;
        } catch (PackageManager.NameNotFoundException e) {
            Drawable badge2 = this.mPackageManager.getDefaultActivityIcon();
            return badge2;
        }
    }

    public Drawable getConversationDrawable(ShortcutInfo info, String packageName, int uid, boolean important) {
        return getConversationDrawable(getBaseIconDrawable(info), packageName, uid, important);
    }

    public Drawable getConversationDrawable(Drawable baseIcon, String packageName, int uid, boolean important) {
        return new ConversationIconDrawable(baseIcon, getAppBadge(packageName, UserHandle.getUserId(uid)), this.mIconBitmapSize, this.mImportantConversationColor, important);
    }

    public static class ConversationIconDrawable extends Drawable {
        private Drawable mBadgeIcon;
        private Drawable mBaseIcon;
        private int mIconSize;
        private Paint mPaddingPaint;
        private Paint mRingPaint;
        private boolean mShowRing;

        public ConversationIconDrawable(Drawable baseIcon, Drawable badgeIcon, int iconSize, int ringColor, boolean showImportanceRing) {
            this.mBaseIcon = baseIcon;
            this.mBadgeIcon = badgeIcon;
            this.mIconSize = iconSize;
            this.mShowRing = showImportanceRing;
            Paint paint = new Paint();
            this.mRingPaint = paint;
            paint.setStyle(Paint.Style.STROKE);
            this.mRingPaint.setColor(ringColor);
            Paint paint2 = new Paint();
            this.mPaddingPaint = paint2;
            paint2.setStyle(Paint.Style.FILL_AND_STROKE);
            this.mPaddingPaint.setColor(-1);
        }

        public void setImportant(boolean important) {
            if (important != this.mShowRing) {
                this.mShowRing = important;
                invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.mIconSize;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.mIconSize;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            Rect bounds = getBounds();
            float scale = bounds.width() / ConversationIconFactory.BASE_ICON_SIZE;
            int ringStrokeWidth = (int) (ConversationIconFactory.RING_STROKE_WIDTH * scale);
            int headSize = (int) (ConversationIconFactory.HEAD_SIZE * scale);
            int badgePadding = (int) (ConversationIconFactory.BADGE_ORIGIN * scale);
            int badgeCenter = (int) (ConversationIconFactory.BADGE_CENTER * scale);
            this.mPaddingPaint.setStrokeWidth(ringStrokeWidth);
            float radius = (int) (ConversationIconFactory.CIRCLE_RADIUS * scale);
            Drawable drawable = this.mBaseIcon;
            if (drawable != null) {
                drawable.setBounds(0, 0, headSize, headSize);
                this.mBaseIcon.draw(canvas);
            } else {
                Log.w("ConversationIconFactory", "ConversationIconDrawable has null base icon");
            }
            if (this.mBadgeIcon != null) {
                canvas.drawCircle(badgeCenter, badgeCenter, radius, this.mPaddingPaint);
                this.mBadgeIcon.setBounds(badgePadding, badgePadding, headSize, headSize);
                this.mBadgeIcon.draw(canvas);
            } else {
                Log.w("ConversationIconFactory", "ConversationIconDrawable has null badge icon");
            }
            if (this.mShowRing) {
                this.mRingPaint.setStrokeWidth(ringStrokeWidth);
                canvas.drawCircle(badgeCenter, badgeCenter, radius, this.mRingPaint);
            }
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int alpha) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }
    }
}
