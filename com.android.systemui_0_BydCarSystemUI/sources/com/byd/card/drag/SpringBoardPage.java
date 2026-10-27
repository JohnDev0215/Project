package com.byd.card.drag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import com.android.systemui.R;
import com.byd.card.bean.Constant;
import com.byd.card.drag.CombineView;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.util.ScreenUtil;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class SpringBoardPage extends View implements IPageView {
    public static long FOLDER_MOVE_TIME = 80;
    private static final String TAG = "SpringBoardPage";
    private final int FOLDER_IN;
    private final int FOLDER_LEFT;
    private final int FOLDER_RIGHT;
    private int aboveIndex;
    Vector<ApplicationInfo> icons;
    private boolean isAboveFolder;
    private boolean jigging;
    LayoutCalculator lc;
    private int locationX;
    private int locationY;
    Animation.AnimationListener mAnimationListener;
    private Context mContext;
    CombineView.AppMoveListener moveListener;
    private boolean needMoveAnimation;
    private ObjectPool pp;
    private Transformation sFormation;
    private int selected;
    private Point selectedLocation;
    private int viewHeight;

    public SpringBoardPage(Context context) {
        this(context, null);
    }

    public SpringBoardPage(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public SpringBoardPage(Context context, AttributeSet attr, int style) {
        super(context, attr, style);
        this.jigging = true;
        this.icons = new Vector<>(LayoutCalculator.FOLDER_ROWS * LayoutCalculator.FOLDER_COLUMNS);
        this.selected = -1;
        this.isAboveFolder = false;
        this.aboveIndex = -1;
        this.viewHeight = 0;
        this.FOLDER_LEFT = 0;
        this.FOLDER_RIGHT = 1;
        this.FOLDER_IN = 2;
        this.sFormation = new Transformation();
        this.needMoveAnimation = true;
        this.mContext = context;
    }

    public void init(LayoutCalculator lc, ObjectPool pp) {
        this.lc = lc;
        this.pp = pp;
        this.icons.clear();
    }

    public boolean isJigging() {
        return this.jigging;
    }

    public void setJigging(boolean jigging) {
        this.jigging = jigging;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        boolean zDrawNormal;
        super.onDraw(canvas);
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        this.locationX = iArr[0];
        this.locationY = iArr[1];
        if (AppUtils.isRtl(this.mContext)) {
            zDrawNormal = drawRTLNormal(canvas);
        } else {
            zDrawNormal = drawNormal(canvas);
        }
        if (this.jigging || zDrawNormal) {
            invalidate();
        }
    }

    private boolean drawNormal(Canvas canvas) {
        return drawNormalRaw(canvas);
    }

    private boolean drawRTLNormal(Canvas canvas) {
        return drawRtlNormalRaw(canvas);
    }

    @Override // com.byd.card.drag.IPageView
    public void setIconIntoPage(int index, ApplicationInfo info) {
        if (index < 0 || index >= this.icons.size()) {
            return;
        }
        this.icons.set(index, info);
        invalidateIcon(index);
    }

    private void invalidateIcon(int index) {
        if (index >= LayoutCalculator.FOLDER_COLUMNS * LayoutCalculator.FOLDER_ROWS) {
            return;
        }
        Point iconLocation = getIconLocation(index);
        invalidate(this.lc.toItemRect(iconLocation.x, iconLocation.y));
    }

    @Override // com.byd.card.drag.IPageView
    public Point getIconLocation(int index) {
        int i = index / LayoutCalculator.FOLDER_COLUMNS;
        int i2 = index % LayoutCalculator.FOLDER_COLUMNS;
        Point point = new Point();
        int verticalGap = getVerticalGap();
        point.x = this.lc.getMarginLeft() + this.lc.getFolderGapH() + (i2 * (this.lc.getIconWidth() + this.lc.getFolderGapH()));
        point.y = this.lc.getMarginTop() + verticalGap + (i * (verticalGap + this.lc.getItemHeight()));
        return point;
    }

    @Override // com.byd.card.drag.IPageView
    public Point getCorrectIconLocation(int index) {
        int folderGapH;
        int i;
        int i2 = index / LayoutCalculator.FOLDER_COLUMNS;
        int i3 = index % LayoutCalculator.FOLDER_COLUMNS;
        Point point = new Point();
        int verticalGap = getVerticalGap();
        if (ScreenUtil.isScreenLandscape(getContext())) {
            folderGapH = this.lc.marginLeft + this.lc.getFolderGapH();
            i = verticalGap / 2;
        } else {
            folderGapH = (this.lc.marginLeft + this.lc.getFolderGapH()) - 18;
            i = (verticalGap / 2) + this.lc.marginTop;
        }
        point.x = folderGapH + (i3 * (this.lc.iconWidth + this.lc.gapH));
        point.y = i + (i2 * (verticalGap + this.lc.itemHeight));
        return point;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v11 */
    /* JADX WARN: Type inference failed for: r15v12 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4, types: [android.view.animation.ScaleAnimation] */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [com.byd.card.drag.TranslateSpringAnimation] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    private boolean drawNormalRaw(Canvas canvas) {
        int i;
        int folderGapH;
        int i2;
        int i3;
        int i4;
        ?? r2;
        ?? r15;
        ?? r152;
        Paint paintTextWhite = this.pp.getPaintTextWhite();
        int verticalGap = getVerticalGap();
        if (ScreenUtil.isScreenLandscape(getContext())) {
            i = verticalGap / 2;
        } else {
            i = (verticalGap / 2) + this.lc.marginTop;
        }
        int size = this.icons.size();
        ?? r4 = 0;
        int i5 = i;
        Paint paint = null;
        boolean z = false;
        int i6 = 0;
        while (i6 < LayoutCalculator.FOLDER_ROWS) {
            if (AppUtils.isLandSpace(this.mContext)) {
                folderGapH = this.lc.marginLeft + this.lc.getFolderGapH();
            } else {
                folderGapH = (this.lc.marginLeft + this.lc.getFolderGapH()) - 18;
            }
            boolean z2 = z;
            Paint paintDarkener = paint;
            int i7 = folderGapH;
            int i8 = 0;
            ?? r42 = r4;
            while (i8 < LayoutCalculator.FOLDER_COLUMNS) {
                int i9 = (LayoutCalculator.FOLDER_COLUMNS * i6) + i8;
                if (i9 >= size) {
                    return z2;
                }
                ApplicationInfo applicationInfo = this.icons.get(i9);
                if (applicationInfo == null) {
                    i7 += this.lc.iconWidth + this.lc.gapH;
                    i2 = i8;
                    i3 = i6;
                    r2 = r42;
                    i4 = size;
                } else if (i9 != this.selected) {
                    if (applicationInfo.mTranslateAnim != null && applicationInfo.mTranslateAnim.isRunning()) {
                        applicationInfo.drawBoundIcon(this.mContext, this.lc, this.pp, canvas, applicationInfo.mTranslateAnim.getAniX(), applicationInfo.mTranslateAnim.getAniY(), paintTextWhite, new Paint(), this.locationX, this.locationY);
                        i2 = i8;
                        i3 = i6;
                        r2 = r42;
                        i4 = size;
                        z2 = true;
                        i7 += this.lc.iconWidth + this.lc.gapH;
                    } else {
                        if (applicationInfo.mTranslateAnim != null) {
                            applicationInfo.mTranslateAnim.destroy();
                            applicationInfo.mTranslateAnim = r42;
                        }
                        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                        if (applicationInfo.mScaleAnim != null && applicationInfo.mScaleAnim.getTransformation(jCurrentAnimationTimeMillis, this.sFormation)) {
                            int isAboveFolder = applicationInfo.getIsAboveFolder();
                            if (isAboveFolder == 1) {
                                i2 = i8;
                                i3 = i6;
                                r152 = r42;
                                i4 = size;
                                long startTime = jCurrentAnimationTimeMillis - applicationInfo.mScaleAnim.getStartTime();
                                if (startTime > applicationInfo.mScaleAnim.getDuration()) {
                                    startTime = applicationInfo.mScaleAnim.getDuration();
                                }
                                float duration = (((startTime * 1.0f) / applicationInfo.mScaleAnim.getDuration()) * 0.4f) + 1.0f;
                                int i10 = ((int) ((this.lc.iconWidth * duration) - this.lc.iconWidth)) / 2;
                                applicationInfo.drawFolderBound(this.lc, this.pp, canvas, i7 - i10, i5 - i10, paintDarkener, duration);
                                applicationInfo.drawIconContent(this.mContext, this.lc, this.pp, canvas, i7, i5, paintTextWhite);
                            } else if (isAboveFolder == 2) {
                                i2 = i8;
                                i3 = i6;
                                r152 = r42;
                                i4 = size;
                            } else if (this.isAboveFolder && applicationInfo.getIsAboveFolder() == 1) {
                                int i11 = ((int) ((this.lc.iconWidth * 1.4f) - this.lc.iconWidth)) / 2;
                                applicationInfo.drawFolderBound(this.lc, this.pp, canvas, i7 - i11, i5 - i11, paintDarkener, 1.4f);
                                applicationInfo.drawIconContent(this.mContext, this.lc, this.pp, canvas, i7, i5, paintTextWhite);
                                i2 = i8;
                                i3 = i6;
                                r152 = r42;
                                i4 = size;
                            } else {
                                i2 = i8;
                                i3 = i6;
                                r152 = r42;
                                i4 = size;
                                drawNormalIcon(applicationInfo, canvas, i7, i5, paintTextWhite);
                            }
                            this.sFormation.clear();
                            r15 = r152;
                        } else {
                            i2 = i8;
                            i3 = i6;
                            ?? r153 = r42;
                            i4 = size;
                            applicationInfo.mScaleAnim = r153;
                            if (this.isAboveFolder && applicationInfo.getIsAboveFolder() == 1) {
                                int i12 = ((int) ((this.lc.iconWidth * 1.4f) - this.lc.iconWidth)) / 2;
                                applicationInfo.drawFolderBound(this.lc, this.pp, canvas, i7 - i12, i5 - i12, paintDarkener, 1.4f);
                                applicationInfo.drawIconContent(this.mContext, this.lc, this.pp, canvas, i7, i5, paintTextWhite);
                                r15 = r153;
                            } else {
                                drawNormalIcon(applicationInfo, canvas, i7, i5, paintTextWhite);
                                r15 = r153;
                            }
                        }
                        r2 = r15;
                        i7 += this.lc.iconWidth + this.lc.gapH;
                    }
                } else {
                    i2 = i8;
                    i3 = i6;
                    ?? r154 = r42;
                    i4 = size;
                    ApplicationInfo applicationInfo2 = this.icons.get(i9);
                    r15 = r154;
                    if (applicationInfo2 != null) {
                        paintDarkener = this.pp.getPaintDarkener();
                        r2 = r154;
                        applicationInfo2.drawBoundIcon(this.mContext, this.lc, this.pp, canvas, i7, i5, paintTextWhite, paintDarkener, this.locationX, this.locationY);
                    } else {
                        r2 = r15;
                    }
                    i7 += this.lc.iconWidth + this.lc.gapH;
                }
                r42 = r2;
                i6 = i3;
                size = i4;
                i8 = i2 + 1;
            }
            i5 += verticalGap + this.lc.itemHeight;
            i6++;
            z = z2;
            paint = paintDarkener;
            r4 = r42;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v11 */
    /* JADX WARN: Type inference failed for: r15v12 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4, types: [android.view.animation.ScaleAnimation] */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [com.byd.card.drag.TranslateSpringAnimation] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    private boolean drawRtlNormalRaw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        ?? r2;
        ?? r15;
        ?? r152;
        Paint paintTextWhite = this.pp.getPaintTextWhite();
        int verticalGap = getVerticalGap();
        if (AppUtils.isLandSpace(this.mContext)) {
            i = verticalGap / 2;
        } else {
            i = (verticalGap / 2) + this.lc.marginTop;
        }
        int size = this.icons.size();
        ?? r4 = 0;
        int i5 = i;
        Paint paint = null;
        boolean z = false;
        int i6 = 0;
        while (i6 < LayoutCalculator.FOLDER_ROWS) {
            boolean z2 = z;
            Paint paintDarkener = paint;
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_333);
            int i7 = 0;
            ?? r42 = r4;
            while (i7 < LayoutCalculator.FOLDER_COLUMNS) {
                int i8 = (LayoutCalculator.FOLDER_COLUMNS * i6) + i7;
                if (i8 >= size) {
                    return z2;
                }
                ApplicationInfo applicationInfo = this.icons.get(i8);
                if (applicationInfo == null) {
                    dimensionPixelSize -= this.lc.iconWidth + this.lc.gapH;
                    i2 = i7;
                    i3 = i6;
                    r2 = r42;
                    i4 = size;
                } else if (i8 != this.selected) {
                    if (applicationInfo.mTranslateAnim != null && applicationInfo.mTranslateAnim.isRunning()) {
                        applicationInfo.drawBoundIcon(this.mContext, this.lc, this.pp, canvas, applicationInfo.mTranslateAnim.getAniX(), applicationInfo.mTranslateAnim.getAniY(), paintTextWhite, new Paint(), this.locationX, this.locationY);
                        i2 = i7;
                        i3 = i6;
                        r2 = r42;
                        i4 = size;
                        z2 = true;
                        dimensionPixelSize -= this.lc.iconWidth + this.lc.gapH;
                    } else {
                        if (applicationInfo.mTranslateAnim != null) {
                            applicationInfo.mTranslateAnim.destroy();
                            applicationInfo.mTranslateAnim = r42;
                        }
                        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                        if (applicationInfo.mScaleAnim != null && applicationInfo.mScaleAnim.getTransformation(jCurrentAnimationTimeMillis, this.sFormation)) {
                            int isAboveFolder = applicationInfo.getIsAboveFolder();
                            if (isAboveFolder == 1) {
                                i2 = i7;
                                i3 = i6;
                                r152 = r42;
                                i4 = size;
                                long startTime = jCurrentAnimationTimeMillis - applicationInfo.mScaleAnim.getStartTime();
                                if (startTime > applicationInfo.mScaleAnim.getDuration()) {
                                    startTime = applicationInfo.mScaleAnim.getDuration();
                                }
                                float duration = (((startTime * 1.0f) / applicationInfo.mScaleAnim.getDuration()) * 0.4f) + 1.0f;
                                int i9 = ((int) ((this.lc.iconWidth * duration) - this.lc.iconWidth)) / 2;
                                applicationInfo.drawFolderBound(this.lc, this.pp, canvas, dimensionPixelSize - i9, i5 - i9, paintDarkener, duration);
                                applicationInfo.drawIconContent(this.mContext, this.lc, this.pp, canvas, dimensionPixelSize, i5, paintTextWhite);
                            } else if (isAboveFolder == 2) {
                                i2 = i7;
                                i3 = i6;
                                r152 = r42;
                                i4 = size;
                            } else if (this.isAboveFolder && applicationInfo.getIsAboveFolder() == 1) {
                                int i10 = ((int) ((this.lc.iconWidth * 1.4f) - this.lc.iconWidth)) / 2;
                                applicationInfo.drawFolderBound(this.lc, this.pp, canvas, dimensionPixelSize - i10, i5 - i10, paintDarkener, 1.4f);
                                applicationInfo.drawIconContent(this.mContext, this.lc, this.pp, canvas, dimensionPixelSize, i5, paintTextWhite);
                                i2 = i7;
                                i3 = i6;
                                r152 = r42;
                                i4 = size;
                            } else {
                                i2 = i7;
                                i3 = i6;
                                r152 = r42;
                                i4 = size;
                                drawNormalIcon(applicationInfo, canvas, dimensionPixelSize, i5, paintTextWhite);
                            }
                            this.sFormation.clear();
                            r15 = r152;
                        } else {
                            i2 = i7;
                            i3 = i6;
                            ?? r153 = r42;
                            i4 = size;
                            applicationInfo.mScaleAnim = r153;
                            if (this.isAboveFolder && applicationInfo.getIsAboveFolder() == 1) {
                                int i11 = ((int) ((this.lc.iconWidth * 1.4f) - this.lc.iconWidth)) / 2;
                                applicationInfo.drawFolderBound(this.lc, this.pp, canvas, dimensionPixelSize - i11, i5 - i11, paintDarkener, 1.4f);
                                applicationInfo.drawIconContent(this.mContext, this.lc, this.pp, canvas, dimensionPixelSize, i5, paintTextWhite);
                                r15 = r153;
                            } else {
                                drawNormalIcon(applicationInfo, canvas, dimensionPixelSize, i5, paintTextWhite);
                                r15 = r153;
                            }
                        }
                        r2 = r15;
                        dimensionPixelSize -= this.lc.iconWidth + this.lc.gapH;
                    }
                } else {
                    i2 = i7;
                    i3 = i6;
                    ?? r154 = r42;
                    i4 = size;
                    ApplicationInfo applicationInfo2 = this.icons.get(i8);
                    r15 = r154;
                    if (applicationInfo2 != null) {
                        paintDarkener = this.pp.getPaintDarkener();
                        r2 = r154;
                        applicationInfo2.drawBoundIcon(this.mContext, this.lc, this.pp, canvas, dimensionPixelSize, i5, paintTextWhite, paintDarkener, this.locationX, this.locationY);
                    } else {
                        r2 = r15;
                    }
                    dimensionPixelSize -= this.lc.iconWidth + this.lc.gapH;
                }
                r42 = r2;
                i6 = i3;
                size = i4;
                i7 = i2 + 1;
            }
            i5 += verticalGap + this.lc.itemHeight;
            i6++;
            z = z2;
            paint = paintDarkener;
            r4 = r42;
        }
        return z;
    }

    private void drawNormalIcon(ApplicationInfo info, Canvas canvas, int x, int y, Paint paint) {
        info.drawBoundIcon(this.mContext, this.lc, this.pp, canvas, x, y, paint, null, this.locationX, this.locationY);
    }

    private int getVerticalGap() {
        return this.lc.getFolderGapV();
    }

    public void setIcons(ApplicationInfo[] icons) {
        for (ApplicationInfo applicationInfo : icons) {
            if (applicationInfo != null) {
                this.icons.add(applicationInfo);
            }
        }
    }

    @Override // com.byd.card.drag.IPageView
    public int getSelectedIndex() {
        return this.selected;
    }

    @Override // com.byd.card.drag.IPageView
    public void select(int index) {
        this.selected = index;
        Point iconLocation = getIconLocation(index);
        invalidate(this.lc.toItemRect(iconLocation.x, iconLocation.y));
        this.selectedLocation = iconLocation;
    }

    @Override // com.byd.card.drag.IPageView
    public void hitTest3(int x, int y, HitResult hitTest3) {
        int i;
        hitTest3.index = -1;
        hitTest3.buttonRemove = false;
        this.lc.dpToPixel(12);
        this.lc.dpToPixel(26);
        this.lc.dpToPixel(26);
        int verticalGap = getVerticalGap() + this.lc.marginTop;
        int folderGapH = this.lc.marginLeft + this.lc.getFolderGapH();
        int i2 = verticalGap;
        for (int i3 = 0; i3 < LayoutCalculator.FOLDER_ROWS; i3++) {
            if (!(y >= i2 && y < this.lc.iconHeight + i2)) {
                i = this.lc.itemHeight;
            } else {
                for (int i4 = 0; i4 < LayoutCalculator.FOLDER_COLUMNS; i4++) {
                    int i5 = (LayoutCalculator.FOLDER_COLUMNS * i3) + i4;
                    if (x >= folderGapH && x < this.lc.iconWidth + folderGapH) {
                        if (AppUtils.isRtl(this.mContext)) {
                            hitTest3.index = switchRtlIndex(i5);
                        } else {
                            hitTest3.index = i5;
                        }
                        hitTest3.buttonRemove = false;
                    }
                    if (hitTest3.index != -1) {
                        return;
                    }
                    folderGapH += this.lc.iconWidth + this.lc.gapH;
                }
                i = this.lc.itemHeight;
            }
            i2 += i + verticalGap;
        }
        hitTest3.index = -1;
    }

    @Override // com.byd.card.drag.IPageView
    public int hitTest2(int x, int y, HitResult hitTest2, boolean isFolder) {
        int folderLeft;
        int i;
        boolean z;
        if (AppUtils.isLandSpace(this.mContext)) {
            folderLeft = this.lc.getFolderLeft() + this.lc.marginLeft + this.lc.getFolderGapH();
        } else {
            folderLeft = ((this.lc.getFolderLeft() + this.lc.marginLeft) + this.lc.getFolderGapH()) - 18;
        }
        int i2 = folderLeft - ((int) (this.lc.iconWidth * 0.7f));
        int i3 = this.lc.iconWidth + folderLeft + ((this.lc.iconWidth + this.lc.gapH) * 2) + ((int) (this.lc.iconWidth * 0.2f));
        if (x <= i2) {
            return -1;
        }
        if (x >= i3) {
            return 1;
        }
        if (this.icons.size() == 0) {
            hitTest2.index = 0;
            hitTest2.inIcon = false;
            return 0;
        }
        int iIndexOf = this.icons.indexOf(null);
        if (AppUtils.isRtl(this.mContext)) {
            iIndexOf = switchRtlIndex(iIndexOf);
        }
        if (iIndexOf == -1) {
            iIndexOf = this.icons.size();
        }
        int verticalGap = getVerticalGap();
        if (ScreenUtil.isScreenLandscape(getContext())) {
            i = verticalGap / 2;
        } else {
            i = (verticalGap / 2) + this.lc.marginTop;
        }
        int i4 = (y - i) + verticalGap;
        int i5 = i4 / (this.lc.itemHeight + verticalGap);
        int i6 = i4 % (this.lc.itemHeight + verticalGap);
        int i7 = (i5 + (i6 > 0 ? 1 : 0)) - 1;
        if (i7 < 0) {
            i7 = 0;
        }
        int i8 = x - folderLeft;
        int i9 = (this.lc.gapH + i8) / (this.lc.iconWidth + this.lc.gapH);
        int i10 = (i8 + this.lc.gapH) % (this.lc.iconWidth + this.lc.gapH);
        int i11 = i9 + (i10 > 0 ? 1 : 0);
        if (i11 < 1) {
            i11 = 1;
        }
        if (i11 > LayoutCalculator.FOLDER_COLUMNS) {
            i11 = LayoutCalculator.FOLDER_COLUMNS;
        }
        int size = ((LayoutCalculator.FOLDER_COLUMNS * i7) + i11) - 1;
        if (size < this.icons.size()) {
            int i12 = iIndexOf / LayoutCalculator.FOLDER_COLUMNS;
            if (i12 != i7 || (i10 >= this.lc.gapH / 2 && i10 <= (this.lc.gapH * 1 * 0.6f) + this.lc.iconWidth)) {
                z = false;
            } else {
                i11 = (iIndexOf % LayoutCalculator.FOLDER_COLUMNS) + 1;
                z = true;
            }
            if (i6 < verticalGap || i6 > (verticalGap * 1) + this.lc.itemHeight) {
                z = true;
                i7 = i12;
            }
            if (z) {
                size = ((i7 * LayoutCalculator.FOLDER_COLUMNS) + i11) - 1;
            }
        }
        if (AppUtils.isRtl(this.mContext)) {
            size = switchRtlIndex(size);
        }
        if (size >= this.icons.size()) {
            size = this.icons.size() - 1;
        }
        if (iIndexOf == size || size < 0) {
            return 2;
        }
        hitTest2.index = size;
        hitTest2.inIcon = false;
        return 0;
    }

    @Override // com.byd.card.drag.IPageView
    public int hitTest2Rtl(int x, int y, HitResult hitTest2, boolean isFolder) {
        int folderLeft;
        int i;
        float f;
        float f2;
        int i2;
        boolean z;
        boolean zIsRtl = AppUtils.isRtl(this.mContext);
        if (AppUtils.isLandSpace(this.mContext)) {
            folderLeft = this.lc.getFolderLeft() + this.lc.marginLeft + this.lc.getFolderGapH();
        } else {
            folderLeft = ((this.lc.getFolderLeft() + this.lc.marginLeft) + this.lc.getFolderGapH()) - 18;
        }
        int i3 = folderLeft - ((int) (this.lc.iconWidth * 0.7f));
        if (zIsRtl) {
            i = ((this.lc.iconWidth + this.lc.gapH) * 2) + folderLeft;
            f = this.lc.iconWidth;
            f2 = 0.4f;
        } else {
            i = this.lc.iconWidth + folderLeft + ((this.lc.iconWidth + this.lc.gapH) * 2);
            f = this.lc.iconWidth;
            f2 = 0.2f;
        }
        int i4 = i + ((int) (f * f2));
        if (x <= i3) {
            return -1;
        }
        if (x >= i4) {
            return 1;
        }
        if (this.icons.size() == 0) {
            hitTest2.index = 0;
            hitTest2.inIcon = false;
            return 0;
        }
        int iIndexOf = this.icons.indexOf(null);
        if (zIsRtl) {
            iIndexOf = switchRtlIndex(iIndexOf);
        }
        if (iIndexOf == -1) {
            iIndexOf = this.icons.size();
        }
        int verticalGap = getVerticalGap();
        if (ScreenUtil.isScreenLandscape(getContext())) {
            i2 = verticalGap / 2;
        } else {
            i2 = (verticalGap / 2) + this.lc.marginTop;
        }
        int i5 = (y - i2) + verticalGap;
        int i6 = i5 / (this.lc.itemHeight + verticalGap);
        int i7 = i5 % (this.lc.itemHeight + verticalGap);
        int i8 = (i6 + (i7 > 0 ? 1 : 0)) - 1;
        if (i8 < 0) {
            i8 = 0;
        }
        int i9 = x - folderLeft;
        int i10 = (this.lc.gapH + i9) / (this.lc.iconWidth + this.lc.gapH);
        int i11 = (i9 + this.lc.gapH) % (this.lc.iconWidth + this.lc.gapH);
        int i12 = i10 + (i11 > 0 ? 1 : 0);
        if (i12 < 1) {
            i12 = 1;
        }
        if (i12 > LayoutCalculator.FOLDER_COLUMNS) {
            i12 = LayoutCalculator.FOLDER_COLUMNS;
        }
        int size = ((LayoutCalculator.FOLDER_COLUMNS * i8) + i12) - 1;
        if (size < this.icons.size()) {
            int i13 = iIndexOf / LayoutCalculator.FOLDER_COLUMNS;
            if (i13 != i8 || (i11 >= this.lc.gapH / 2 && i11 <= (this.lc.gapH * 1 * 0.6f) + this.lc.iconWidth)) {
                z = false;
            } else {
                i12 = (iIndexOf % LayoutCalculator.FOLDER_COLUMNS) + 1;
                z = true;
            }
            if (i7 < verticalGap || i7 > (verticalGap * 1) + this.lc.itemHeight) {
                z = true;
                i8 = i13;
            }
            if (z) {
                size = ((i8 * LayoutCalculator.FOLDER_COLUMNS) + i12) - 1;
            }
        }
        if (zIsRtl) {
            size = switchRtlIndex(size);
        }
        if (size >= this.icons.size()) {
            size = this.icons.size() - 1;
        }
        if (iIndexOf == size || size < 0) {
            return 2;
        }
        hitTest2.index = size;
        hitTest2.inIcon = false;
        return 0;
    }

    public int switchRtlIndex(int index) {
        int i = index % LayoutCalculator.FOLDER_COLUMNS;
        if (i == 0) {
            return ((index / LayoutCalculator.FOLDER_COLUMNS) * LayoutCalculator.FOLDER_COLUMNS) + 2;
        }
        if (i == 1) {
            return ((index / LayoutCalculator.FOLDER_COLUMNS) * LayoutCalculator.FOLDER_COLUMNS) + 1;
        }
        if (i != 2) {
            return 0;
        }
        return (index / LayoutCalculator.FOLDER_COLUMNS) * LayoutCalculator.FOLDER_COLUMNS;
    }

    @Override // com.byd.card.drag.IPageView
    public ApplicationInfo getIcon(int index) {
        if (index < 0 || index >= this.icons.size()) {
            return null;
        }
        return this.icons.get(index);
    }

    @Override // com.byd.card.drag.IPageView
    public ApplicationInfo getSelectedApp() {
        int i = this.selected;
        if (i < 0 || i >= this.icons.size()) {
            return null;
        }
        return this.icons.get(this.selected);
    }

    @Override // com.byd.card.drag.IPageView
    public void deselect() {
        if (this.selected < 0) {
            return;
        }
        this.selected = -1;
        Point point = this.selectedLocation;
        invalidate(this.lc.toItemRect(point.x, point.y));
    }

    @Override // com.byd.card.drag.IPageView
    public void jiggle() {
        this.jigging = true;
        for (int i = 0; i < this.icons.size(); i++) {
            ApplicationInfo applicationInfo = this.icons.get(i);
            if (applicationInfo != null) {
                applicationInfo.jiggle();
            }
        }
        invalidate();
    }

    @Override // com.byd.card.drag.IPageView
    public void unJiggle() {
        for (int i = 0; i < this.icons.size(); i++) {
            ApplicationInfo applicationInfo = this.icons.get(i);
            if (applicationInfo != null) {
                applicationInfo.unJiggle();
            }
        }
        this.jigging = false;
    }

    @Override // com.byd.card.drag.IPageView
    public int getIconsCount() {
        int i = 0;
        for (int i2 = 0; i2 < this.icons.size(); i2++) {
            if (this.icons.get(i2) != null) {
                i++;
            }
        }
        return i;
    }

    @Override // com.byd.card.drag.IPageView
    public boolean getNeedMoveAnimation() {
        return this.needMoveAnimation;
    }

    @Override // com.byd.card.drag.IPageView
    public boolean setMoveTo(int index) {
        this.needMoveAnimation = true;
        int i = 0;
        if (!this.icons.contains(null) && this.icons.size() >= LayoutCalculator.FOLDER_ROWS * LayoutCalculator.FOLDER_COLUMNS) {
            this.needMoveAnimation = false;
            return false;
        }
        if (index < 0) {
            return false;
        }
        if (index >= this.icons.size() && index < LayoutCalculator.FOLDER_ROWS * LayoutCalculator.FOLDER_COLUMNS) {
            int size = -1;
            while (i < this.icons.size()) {
                if (this.icons.get(i) == null) {
                    size = i;
                }
                i++;
            }
            if (size == -1) {
                size = this.icons.size();
                this.icons.add(null);
            }
            int i2 = size;
            while (i2 < this.icons.size() - 1) {
                CombineView.AppMoveListener appMoveListener = this.moveListener;
                if (appMoveListener != null) {
                    appMoveListener.onAppMove((this.icons.size() - 1) - size);
                }
                if (i2 < this.icons.size() - 2) {
                    moveIcon(i2, i2 + 1, ((long) (i2 - size)) * 50, null);
                } else {
                    moveIcon(i2, i2 + 1, ((long) (i2 - size)) * 50, this.mAnimationListener);
                }
                i2++;
            }
            this.icons.set(i2, null);
            return true;
        }
        if (index < this.icons.size() && this.icons.get(index) == null) {
            this.needMoveAnimation = false;
            return true;
        }
        int size2 = -1;
        for (int i3 = 0; i3 < this.icons.size(); i3++) {
            if (this.icons.get(i3) == null) {
                size2 = i3;
            }
        }
        if (size2 == -1) {
            size2 = this.icons.size();
            this.icons.add(null);
        }
        if (size2 > index) {
            i = size2;
            while (i > index) {
                CombineView.AppMoveListener appMoveListener2 = this.moveListener;
                if (appMoveListener2 != null) {
                    appMoveListener2.onAppMove(size2 - index);
                }
                if (i > index + 1) {
                    moveIcon(i, i - 1, ((long) (size2 - i)) * 50, null);
                } else {
                    moveIcon(i, i - 1, ((long) (size2 - i)) * 50, this.mAnimationListener);
                }
                i--;
            }
        } else if (size2 < index) {
            i = size2;
            while (i < index) {
                CombineView.AppMoveListener appMoveListener3 = this.moveListener;
                if (appMoveListener3 != null) {
                    appMoveListener3.onAppMove(index - size2);
                }
                if (i < index - 1) {
                    moveIcon(i, i + 1, ((long) (i - size2)) * 50, null);
                } else {
                    moveIcon(i, i + 1, ((long) (i - size2)) * 50, this.mAnimationListener);
                }
                i++;
            }
        }
        if (i < this.icons.size()) {
            this.icons.set(i, null);
        }
        return true;
    }

    @Override // com.byd.card.drag.IPageView
    public void clearUp(ApplicationInfo info) {
        boolean z = info == null;
        for (int i = 0; i < this.icons.size(); i++) {
            ApplicationInfo applicationInfo = this.icons.get(i);
            if (applicationInfo == null) {
                if (info != null) {
                    this.icons.set(i, info);
                    z = true;
                } else {
                    int i2 = i;
                    while (i2 < this.icons.size() - 1) {
                        int i3 = i2 + 1;
                        moveIcon(i2, i3, ((long) ((i2 - i) + 1)) * 50);
                        i2 = i3;
                    }
                    this.icons.remove(i2);
                }
            } else if (applicationInfo.getType() == 2 && ((MoreAppFolderInfo) applicationInfo).getIcons().size() == 0) {
                int i4 = i;
                while (i4 < this.icons.size() - 1) {
                    int i5 = i4 + 1;
                    moveIcon(i4, i5, ((long) ((i4 - i) + 1)) * 50);
                    i4 = i5;
                }
                this.icons.remove(i4);
            }
        }
        if (!z) {
            this.icons.add(info);
        }
        this.viewHeight = getViewHeight();
    }

    public int getViewHeight() {
        if (this.lc != null) {
            this.viewHeight = (LayoutCalculator.FOLDER_ROWS * (this.lc.getItemHeight() + getVerticalGap())) + getVerticalGap();
        }
        return this.viewHeight;
    }

    @Override // com.byd.card.drag.IPageView
    public void addToFolder(int i, ApplicationInfo app) {
        ApplicationInfo applicationInfo;
        MoreAppFolderInfo moreAppFolderInfo;
        if (this.icons.size() > i && (applicationInfo = this.icons.get(i)) != null) {
            if (applicationInfo.getType() == 2) {
                moreAppFolderInfo = (MoreAppFolderInfo) applicationInfo;
                moreAppFolderInfo.addIcon(app);
            } else {
                MoreAppFolderInfo moreAppFolderInfo2 = new MoreAppFolderInfo();
                moreAppFolderInfo2.addIcon(applicationInfo);
                moreAppFolderInfo2.addIcon(app);
                moreAppFolderInfo2.setTitle(Constant.FolderType.DEFAULT_FOLDER);
                moreAppFolderInfo = moreAppFolderInfo2;
            }
            setIconIntoPage(i, moreAppFolderInfo);
        }
    }

    private void moveIcon(int to, int from, long offset) {
        Point correctIconLocation;
        Point correctIconLocation2;
        if (AppUtils.isRtl(this.mContext)) {
            correctIconLocation = getCorrectIconLocation(switchRtlIndex(from));
            correctIconLocation2 = getCorrectIconLocation(switchRtlIndex(to));
        } else {
            correctIconLocation = getCorrectIconLocation(from);
            correctIconLocation2 = getCorrectIconLocation(to);
        }
        ApplicationInfo applicationInfo = this.icons.get(from);
        if (to < LayoutCalculator.FOLDER_COLUMNS * LayoutCalculator.FOLDER_ROWS && applicationInfo != null) {
            applicationInfo.mTranslateAnim = new TranslateSpringAnimation(correctIconLocation.x, correctIconLocation2.x, correctIconLocation.y, correctIconLocation2.y).setStartOffset(offset).setHandler(getHandler()).start();
        }
        setIconIntoPage(to, applicationInfo);
    }

    private void moveIcon(int to, int from, long offset, Animation.AnimationListener animationListener) {
        Point correctIconLocation;
        Point correctIconLocation2;
        if (AppUtils.isRtl(this.mContext)) {
            correctIconLocation = getCorrectIconLocation(switchRtlIndex(to));
            correctIconLocation2 = getCorrectIconLocation(switchRtlIndex(from));
        } else {
            correctIconLocation = getCorrectIconLocation(to);
            correctIconLocation2 = getCorrectIconLocation(from);
        }
        ApplicationInfo applicationInfo = from < this.icons.size() ? this.icons.get(from) : null;
        if (to < LayoutCalculator.FOLDER_COLUMNS * LayoutCalculator.FOLDER_ROWS && applicationInfo != null) {
            applicationInfo.mTranslateAnim = new TranslateSpringAnimation(correctIconLocation2.x, correctIconLocation.x, correctIconLocation2.y, correctIconLocation.y).setStartOffset(offset).setAnimationListener(animationListener).setHandler(getHandler()).start();
        }
        setIconIntoPage(to, applicationInfo);
    }

    @Override // com.byd.card.drag.IPageView
    public void setAppMoveAnimationListener(Animation.AnimationListener animationListener) {
        this.mAnimationListener = animationListener;
    }

    @Override // com.byd.card.drag.IPageView
    public void setAppMoveListener(CombineView.AppMoveListener listener) {
        this.moveListener = listener;
    }

    @Override // com.byd.card.drag.IPageView
    public void createFolderBound(int index) {
        this.aboveIndex = index;
        ApplicationInfo applicationInfo = index < this.icons.size() ? this.icons.get(index) : null;
        if (!this.isAboveFolder && applicationInfo != null) {
            applicationInfo.setIsAboveFolder(1);
            applicationInfo.mScaleAnim = new ScaleAnimation(1.0f, 1.4f, 1.0f, 1.4f);
            applicationInfo.mScaleAnim.initialize(this.lc.getItemWidth() + this.lc.getFolderGapH(), this.lc.getItemHeight(), getWidth(), getHeight());
            applicationInfo.mScaleAnim.setFillAfter(true);
            applicationInfo.mScaleAnim.setFillBefore(true);
            applicationInfo.mScaleAnim.setDuration(200L);
            applicationInfo.mScaleAnim.startNow();
        }
        this.isAboveFolder = true;
    }

    @Override // com.byd.card.drag.IPageView
    public void removeFolderBound() {
        final ApplicationInfo applicationInfo;
        this.isAboveFolder = false;
        int i = this.aboveIndex;
        if (i >= 0 && (applicationInfo = this.icons.get(i)) != null) {
            this.aboveIndex = -1;
            applicationInfo.setIsAboveFolder(2);
            applicationInfo.mScaleAnim = new ScaleAnimation(1.4f, 1.0f, 1.4f, 1.0f, 50.0f, 50.0f);
            applicationInfo.mScaleAnim.initialize(this.lc.getItemWidth() + this.lc.getFolderGapH(), this.lc.getItemHeight(), getWidth(), getHeight());
            applicationInfo.mScaleAnim.setFillAfter(true);
            applicationInfo.mScaleAnim.setFillBefore(true);
            applicationInfo.mScaleAnim.setDuration(200L);
            applicationInfo.mScaleAnim.startNow();
            applicationInfo.mScaleAnim.setAnimationListener(new Animation.AnimationListener() { // from class: com.byd.card.drag.SpringBoardPage.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    applicationInfo.setIsAboveFolder(-1);
                }
            });
        }
    }

    @Override // com.byd.card.drag.IPageView
    public void removeApp(int index) {
        setIconIntoPage(index, null);
        clearUp(null);
    }

    @Override // com.byd.card.drag.IPageView
    public void addAppIcon(ApplicationInfo app, boolean isShow) {
        if (isShow) {
            this.icons.remove((Object) null);
            this.icons.add(app);
            setIconIntoPage(this.icons.size() - 1, app);
        }
    }

    @Override // com.byd.card.drag.IPageView
    public void updateLayout(ApplicationInfo app, int leftOrRight) {
        if (leftOrRight == 1) {
            this.icons.remove((Object) null);
            this.icons.add(0, app);
            setIconIntoPage(0, app);
        } else {
            this.icons.remove((Object) null);
            this.icons.add(app);
            setIconIntoPage(this.icons.size() - 1, app);
        }
    }
}
