package com.byd.card.drag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.android.systemui.R;
import com.byd.card.utils.AppUtils;
import com.byd.card.view.activity.MoreAppActivity;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes.dex */
public class IconMover {
    private ApplicationInfo app;
    private Handler handler;
    private SoftReference<Bitmap> image;
    private int index;
    private LayoutCalculator lc;
    private int mAppWidth;
    private Context mContext;
    private boolean mIsRtl;
    private ObjectPool op;
    private int pageIndex;
    private ImageView panel;
    private int relX;
    private int relY;
    private int sIndex;
    private int sPageIndex;
    private boolean isAboveFolder = false;
    private int mAppTop = 0;
    private Runnable hider = new Runnable() { // from class: com.byd.card.drag.IconMover.1
        @Override // java.lang.Runnable
        public void run() {
            IconMover.this.panel.setVisibility(8);
            IconMover.this.panel.setImageDrawable(null);
        }
    };

    public interface OnMovingStopped {
        void movingStopped(ApplicationInfo appInfo);
    }

    public IconMover(Context context, MoreAppActivity view, LayoutCalculator lc, ObjectPool op, Handler handler, int width) {
        this.mAppWidth = 0;
        this.mContext = null;
        this.lc = lc;
        this.op = op;
        this.handler = handler;
        this.mAppWidth = width;
        ImageView imageView = (ImageView) view.findViewById(R.id.panelMoving);
        this.panel = imageView;
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        this.mContext = context;
        this.mIsRtl = AppUtils.isRtl(context);
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    private void drawIcon(Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(0, PorterDuff.Mode.SRC);
        Paint paintDarkener = this.op.getPaintDarkener();
        ApplicationInfo applicationInfo = this.app;
        Context context = this.mContext;
        LayoutCalculator layoutCalculator = this.lc;
        applicationInfo.drawBoundIcon(context, layoutCalculator, this.op, canvas, layoutCalculator.fullMarginLeftBlackCircle, this.lc.cMarginTop, this.op.getPaintTextWhite(), paintDarkener);
    }

    private Bitmap getImageBitmap() {
        SoftReference<Bitmap> softReference = this.image;
        if (softReference != null && softReference.get() != null) {
            return this.image.get();
        }
        Bitmap bitmapCreateBitmap = BitmapManager.createBitmap(this.lc.getIconWidth() + this.lc.gapH, this.lc.itemHeight + this.lc.gapV + this.lc.marginTop, Bitmap.Config.ARGB_8888);
        this.image = new SoftReference<>(bitmapCreateBitmap);
        return bitmapCreateBitmap;
    }

    public Rect getBounds() {
        return new Rect(this.panel.getLeft(), this.panel.getTop() - this.lc.cMarginTop, this.panel.getRight(), this.panel.getBottom());
    }

    public ApplicationInfo hook() {
        return this.app;
    }

    public boolean isMoving() {
        return this.app != null;
    }

    public void moveIntoFolder(int x, int y, final OnMovingStopped onMovingStopped) {
        Animation animationCreateAnimationIconIntoFolder = this.op.createAnimationIconIntoFolder(x, y, this.panel.getLeft() + this.mAppWidth, this.panel.getTop() + this.mAppTop);
        animationCreateAnimationIconIntoFolder.setAnimationListener(new Animation.AnimationListener() { // from class: com.byd.card.drag.IconMover.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation paramAnimation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation paramAnimation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation paramAnimation) {
                ApplicationInfo applicationInfo = IconMover.this.app;
                IconMover.this.app = null;
                OnMovingStopped onMovingStopped2 = onMovingStopped;
                if (onMovingStopped2 != null) {
                    onMovingStopped2.movingStopped(applicationInfo);
                }
                IconMover.this.handler.post(IconMover.this.hider);
            }
        });
        drawIcon(getImageBitmap());
        this.panel.startAnimation(animationCreateAnimationIconIntoFolder);
        this.isAboveFolder = false;
    }

    public void moveTo(int x, int y) {
        int i = x - this.relX;
        int i2 = y - this.relY;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.panel.getLayoutParams();
        if (AppUtils.isLandSpace(this.mContext)) {
            if (i2 > 646) {
                layoutParams.topMargin = 646;
                i2 = 646;
            } else {
                layoutParams.topMargin = i2;
            }
            if (i >= 1608) {
                layoutParams.leftMargin = 1608;
                i = 1608;
            } else {
                layoutParams.leftMargin = i;
            }
        } else {
            if (i2 > 1430) {
                layoutParams.topMargin = 1430;
            } else {
                layoutParams.topMargin = i2;
            }
            if (i > 1181) {
                layoutParams.leftMargin = 1181;
                i = 1181;
            } else {
                layoutParams.leftMargin = i;
            }
        }
        this.panel.setLayoutParams(layoutParams);
        ImageView imageView = this.panel;
        imageView.layout(this.mAppWidth + i, this.mAppTop + i2, i + imageView.getWidth(), i2 + this.panel.getHeight());
        hideTitle();
    }

    public void hideTitle() {
        Canvas canvas = new Canvas(getImageBitmap());
        canvas.drawColor(0, PorterDuff.Mode.SRC);
        ApplicationInfo applicationInfo = this.app;
        if (applicationInfo instanceof MoreAppFolderInfo) {
            ((MoreAppFolderInfo) applicationInfo).drawFolderIcon(this.op, canvas, this.lc.fullMarginLeftBlackCircle, this.lc.cMarginTop, this.op.getPaintTextWhite());
        }
        ApplicationInfo applicationInfo2 = this.app;
        Context context = this.mContext;
        LayoutCalculator layoutCalculator = this.lc;
        applicationInfo2.drawIconContent(context, layoutCalculator, this.op, canvas, layoutCalculator.fullMarginLeftBlackCircle, this.lc.cMarginTop, this.op.getPaintTextWhite());
        this.app.setTitleVisibility(false);
    }

    public void showTitle() {
        ApplicationInfo applicationInfo = this.app;
        if (applicationInfo == null) {
            return;
        }
        applicationInfo.setTitleVisibility(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int switchRtlX(int r6) {
        /*
            r5 = this;
            android.content.Context r0 = r5.mContext
            boolean r0 = com.byd.card.utils.AppUtils.isLandSpace(r0)
            r1 = 0
            r2 = 3
            r3 = 2
            r4 = 1
            if (r0 == 0) goto L1b
            int r0 = com.byd.card.drag.LayoutCalculator.APP_COLUMNS_LAND
            int r6 = r6 % r0
            r0 = 4
            if (r6 == 0) goto L19
            if (r6 == r4) goto L29
            if (r6 == r3) goto L27
            if (r6 == r2) goto L25
            goto L2a
        L19:
            r1 = r0
            goto L2a
        L1b:
            int r0 = com.byd.card.drag.LayoutCalculator.APP_COLUMNS_PORT
            int r6 = r6 % r0
            if (r6 == 0) goto L29
            if (r6 == r4) goto L27
            if (r6 == r3) goto L25
            goto L2a
        L25:
            r1 = r4
            goto L2a
        L27:
            r1 = r3
            goto L2a
        L29:
            r1 = r2
        L2a:
            com.byd.card.drag.LayoutCalculator r6 = r5.lc
            int r6 = r6.getMarginLeft()
            com.byd.card.drag.LayoutCalculator r0 = r5.lc
            int r0 = r0.gapH
            int r6 = r6 + r0
            com.byd.card.drag.LayoutCalculator r0 = r5.lc
            int r0 = r0.getIconWidth()
            com.byd.card.drag.LayoutCalculator r2 = r5.lc
            int r2 = r2.gapH
            int r0 = r0 + r2
            int r1 = r1 * r0
            int r6 = r6 + r1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.card.drag.IconMover.switchRtlX(int):int");
    }

    private int switchFolderRtlX(int index) {
        int i = index % LayoutCalculator.FOLDER_COLUMNS;
        return this.lc.getMarginLeft() + this.lc.gapH + ((i != 0 ? i != 1 ? 0 : 1 : 2) * (this.lc.getIconWidth() + this.lc.gapH));
    }

    public void startMoving(ApplicationInfo info, boolean isFolder, int index, int x1, int y1, int x2, int y2) {
        int i;
        int moreAppLeft;
        this.app = info;
        if (isFolder) {
            if (AppUtils.isRtl(this.mContext)) {
                x1 = switchFolderRtlX(index);
            }
        } else if (AppUtils.isRtl(this.mContext)) {
            x1 = switchRtlX(index);
        }
        int moreAppLeft2 = (x1 - this.lc.fullMarginLeftBlackCircle) + this.lc.getMoreAppLeft();
        if (isFolder) {
            if (AppUtils.isLandSpace(this.mContext)) {
                if (AppUtils.isRightRudder()) {
                    x1 += 700;
                }
                int i2 = index % 3;
                if (i2 == 0) {
                    moreAppLeft = (x1 - this.lc.fullMarginLeftBlackCircle) + this.lc.getMoreAppLeft() + 50;
                    if (AppUtils.isRtl(this.mContext)) {
                        moreAppLeft -= 130;
                    }
                } else if (i2 == 1) {
                    moreAppLeft = (x1 - this.lc.fullMarginLeftBlackCircle) + this.lc.getMoreAppLeft() + 28;
                    if (AppUtils.isRtl(this.mContext)) {
                        moreAppLeft -= 105;
                    }
                } else {
                    if (i2 == 2) {
                        moreAppLeft = (x1 - this.lc.fullMarginLeftBlackCircle) + this.lc.getMoreAppLeft();
                        if (AppUtils.isRtl(this.mContext)) {
                            moreAppLeft -= 75;
                        }
                    }
                    i = ((y1 - this.lc.cMarginTop) + this.mAppTop) - 8;
                }
                moreAppLeft2 = moreAppLeft;
                i = ((y1 - this.lc.cMarginTop) + this.mAppTop) - 8;
            } else {
                if (this.mIsRtl) {
                    moreAppLeft2 = (x1 - this.lc.fullMarginLeftBlackCircle) + this.lc.getMoreAppLeft() + 120;
                } else {
                    int i3 = index % 3;
                    if (i3 == 0) {
                        moreAppLeft2 = ((x1 - this.lc.fullMarginLeftBlackCircle) + this.lc.getMoreAppLeft()) - 24;
                    } else if (i3 == 1) {
                        moreAppLeft2 = ((x1 - this.lc.fullMarginLeftBlackCircle) + this.lc.getMoreAppLeft()) - 47;
                    } else if (i3 == 2) {
                        moreAppLeft2 = ((x1 - this.lc.fullMarginLeftBlackCircle) + this.lc.getMoreAppLeft()) - 70;
                    }
                }
                i = ((y1 - this.lc.cMarginTop) + this.mAppTop) - 6;
            }
        } else if (AppUtils.isLandSpace(this.mContext)) {
            i = (y1 - this.lc.cMarginTop) + this.mAppTop + 12;
        } else {
            i = ((y1 - this.lc.cMarginTop) + this.mAppTop) - 2;
        }
        this.relX = x2 - moreAppLeft2;
        this.relY = y2 - i;
        Bitmap imageBitmap = getImageBitmap();
        drawIcon(imageBitmap);
        this.panel.setImageBitmap(imageBitmap);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.panel.getLayoutParams();
        layoutParams.width = imageBitmap.getWidth();
        layoutParams.height = imageBitmap.getHeight();
        layoutParams.leftMargin = moreAppLeft2;
        layoutParams.topMargin = i;
        this.panel.setLayoutParams(layoutParams);
        if (isFolder) {
            if (AppUtils.isLandSpace(this.mContext)) {
                this.panel.layout(moreAppLeft2, i, layoutParams.width, layoutParams.height);
            } else {
                int i4 = index % 3;
                if (i4 == 0) {
                    this.panel.layout(moreAppLeft2 - 24, i, layoutParams.width, layoutParams.height - 6);
                } else if (i4 == 1) {
                    this.panel.layout(moreAppLeft2 - 47, i, layoutParams.width, layoutParams.height - 6);
                } else if (i4 == 2) {
                    this.panel.layout(moreAppLeft2 - 70, i, layoutParams.width, layoutParams.height - 6);
                }
            }
        } else if (AppUtils.isLandSpace(this.mContext)) {
            if (i > 625) {
                i = 625;
            }
            this.panel.layout(moreAppLeft2, i, layoutParams.width, layoutParams.height + 12);
        } else {
            this.panel.layout(moreAppLeft2, i, layoutParams.width, layoutParams.height - 2);
        }
        this.panel.setVisibility(0);
        this.panel.startAnimation(this.op.createAnimationIconExpand());
    }

    public ApplicationInfo stopMoving() {
        ApplicationInfo applicationInfo = this.app;
        this.app = null;
        this.index = -1;
        this.pageIndex = -1;
        return applicationInfo;
    }

    public void hidePanel() {
        this.panel.setVisibility(8);
        Drawable drawable = this.panel.getDrawable();
        this.panel.setImageDrawable(null);
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void stopMoving(int x, int y, final OnMovingStopped onMovingStopped) {
        drawIcon(getImageBitmap());
        hidePanel();
        Animation animationCreateAnimationIconContract = this.op.createAnimationIconContract(x - ((this.panel.getLeft() + this.mAppWidth) + this.lc.fullMarginLeftBlackCircle), (y - (this.panel.getTop() + this.lc.cMarginTop)) + this.mAppTop);
        if (onMovingStopped != null) {
            animationCreateAnimationIconContract.setAnimationListener(new Animation.AnimationListener() { // from class: com.byd.card.drag.IconMover.3
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation paramAnimation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation paramAnimation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    animation.setAnimationListener(null);
                    onMovingStopped.movingStopped(IconMover.this.stopMoving());
                }
            });
        }
        this.panel.startAnimation(animationCreateAnimationIconContract);
    }

    public void setAppWidth(int width, int top) {
        this.mAppWidth = width;
        this.mAppTop = top;
    }

    public void aboveFolder() {
        setAboveFolder(true);
        drawIcon(getImageBitmap());
        Animation animationCreateAnimationAboveFolder = this.op.createAnimationAboveFolder();
        animationCreateAnimationAboveFolder.setAnimationListener(new Animation.AnimationListener() { // from class: com.byd.card.drag.IconMover.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation paramAnimation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation paramAnimation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                animation.setAnimationListener(null);
            }
        });
        this.panel.startAnimation(animationCreateAnimationAboveFolder);
    }

    public void bisideFolder() {
        setAboveFolder(false);
        drawIcon(getImageBitmap());
        Animation animationCreateAnimationBisideFolder = this.op.createAnimationBisideFolder();
        animationCreateAnimationBisideFolder.setAnimationListener(new Animation.AnimationListener() { // from class: com.byd.card.drag.IconMover.5
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation paramAnimation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation paramAnimation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                animation.setAnimationListener(null);
            }
        });
        this.panel.startAnimation(animationCreateAnimationBisideFolder);
    }

    public boolean isAboveFolder() {
        return this.isAboveFolder;
    }

    public void setAboveFolder(boolean isAboveFolder) {
        this.isAboveFolder = isAboveFolder;
    }

    public int getsPageIndex() {
        return this.sPageIndex;
    }

    public void setsPageIndex(int sPageIndex) {
        this.sPageIndex = sPageIndex;
    }

    public int getsIndex() {
        return this.sIndex;
    }

    public void setsIndex(int sIndex) {
        this.sIndex = sIndex;
    }
}
