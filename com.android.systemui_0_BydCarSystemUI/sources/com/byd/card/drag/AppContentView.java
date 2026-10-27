package com.byd.card.drag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import com.android.systemui.R;
import com.byd.card.bean.Constant;
import com.byd.card.bean.Folder;
import com.byd.card.db.CardDatabase;
import com.byd.card.drag.CombineView;
import com.byd.card.floatcard.DBUtils;
import com.byd.card.floatcard.Executors;
import com.byd.card.forbidden.ForbiddenManager;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.util.ScreenUtil;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppContentView extends View implements IPageView {
    public static long APP_MOVE_TIME = 50;
    private final int APP_IN;
    private final int APP_LEFT;
    private final int APP_RIGHT;
    private String TAG;
    private OnUpdateAppContentLayoutListener listener;
    private int locationX;
    private int locationY;
    private int mAboveIndex;
    Animation.AnimationListener mAnimationListener;
    private int mColumns;
    private Context mContext;
    private List<ApplicationInfo> mInfo;
    private boolean mIsAboveFolder;
    private boolean mIsEditMode;
    private LayoutCalculator mLayoutCalculator;
    private int[] mLocation;
    private ObjectPool mObjectPool;
    private int mRows;
    private int mSelected;
    private Point mSelectedLocation;
    private int mViewHeight;
    CombineView.AppMoveListener moveListener;
    private boolean needMoveAnimation;
    private Transformation sFormation;

    public interface OnUpdateAppContentLayoutListener {
        void onHeightChanged();
    }

    @Override // com.byd.card.drag.IPageView
    public void addAppIcon(ApplicationInfo app, boolean isShow) {
    }

    @Override // com.byd.card.drag.IPageView
    public void updateLayout(ApplicationInfo app, int leltOrRight) {
    }

    public void setOnUpdateAppContentLayoutListener(OnUpdateAppContentLayoutListener listener) {
        this.listener = listener;
    }

    public AppContentView(Context context) {
        this(context, null);
    }

    public AppContentView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public AppContentView(Context context, AttributeSet attr, int style) {
        super(context, attr, style);
        this.TAG = "AppContentView";
        this.mIsEditMode = false;
        this.mSelected = -1;
        this.mIsAboveFolder = false;
        this.mAboveIndex = -1;
        this.APP_LEFT = 0;
        this.APP_RIGHT = 1;
        this.APP_IN = 2;
        this.sFormation = new Transformation();
        this.mLocation = new int[2];
        this.needMoveAnimation = true;
        this.mContext = context;
    }

    public void init(LayoutCalculator lc, ObjectPool pp) {
        this.mLayoutCalculator = lc;
        this.mObjectPool = pp;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        boolean zDrawNormal;
        super.onDraw(canvas);
        getLocationOnScreen(this.mLocation);
        int[] iArr = this.mLocation;
        this.locationX = iArr[0];
        this.locationY = iArr[1];
        if (AppUtils.isRtl(this.mContext)) {
            zDrawNormal = drawRtlNormal(canvas);
        } else {
            zDrawNormal = drawNormal(canvas);
        }
        if (this.mIsEditMode || zDrawNormal) {
            invalidate();
        }
    }

    @Override // com.byd.card.drag.IPageView
    public void setIconIntoPage(int index, ApplicationInfo info) {
        if (index < 0 || index >= this.mInfo.size()) {
            return;
        }
        this.mInfo.set(index, info);
        invalidateIcon(index);
    }

    private void invalidateIcon(int index) {
        Point iconLocation = getIconLocation(index);
        invalidate(this.mLayoutCalculator.toItemRect(iconLocation.x, iconLocation.y));
    }

    @Override // com.byd.card.drag.IPageView
    public Point getIconLocation(int index) {
        int i = this.mColumns;
        int i2 = index / i;
        int i3 = index % i;
        Point point = new Point();
        int verticalGap = getVerticalGap();
        point.x = this.mLayoutCalculator.getMarginLeft() + this.mLayoutCalculator.gapH + (i3 * (this.mLayoutCalculator.getIconWidth() + this.mLayoutCalculator.gapH));
        point.y = this.mLayoutCalculator.getMarginTop() + verticalGap + (i2 * (verticalGap + this.mLayoutCalculator.getItemHeight()));
        return point;
    }

    @Override // com.byd.card.drag.IPageView
    public Point getCorrectIconLocation(int index) {
        int i = this.mColumns;
        int i2 = index / i;
        int i3 = index % i;
        Point point = new Point();
        int verticalGap = getVerticalGap();
        point.x = this.mLayoutCalculator.getMarginLeft() + this.mLayoutCalculator.gapH + (i3 * (this.mLayoutCalculator.getIconWidth() + this.mLayoutCalculator.gapH));
        point.y = verticalGap + (i2 * (this.mLayoutCalculator.getItemHeight() + verticalGap));
        return point;
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0292, code lost:
    
        r17 = r17 + (r24.mLayoutCalculator.itemHeight + r13);
        r12 = r12 + 1;
        r2 = r18;
        r3 = r19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean drawNormal(android.graphics.Canvas r25) {
        /*
            Method dump skipped, instruction units count: 676
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.card.drag.AppContentView.drawNormal(android.graphics.Canvas):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0296, code lost:
    
        r17 = r17 + (r24.mLayoutCalculator.itemHeight + r13);
        r12 = r12 + 1;
        r2 = r18;
        r3 = r19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean drawRtlNormal(android.graphics.Canvas r25) {
        /*
            Method dump skipped, instruction units count: 680
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.card.drag.AppContentView.drawRtlNormal(android.graphics.Canvas):boolean");
    }

    private int getVerticalGap() {
        if (ScreenUtil.isScreenLandscape(getContext())) {
            return getContext().getResources().getDimensionPixelOffset(R.dimen.dp_48);
        }
        return getContext().getResources().getDimensionPixelOffset(R.dimen.dp_58);
    }

    @Override // com.byd.card.drag.IPageView
    public int getSelectedIndex() {
        return this.mSelected;
    }

    @Override // com.byd.card.drag.IPageView
    public void select(int index) {
        this.mSelected = index;
        Point iconLocation = getIconLocation(index);
        invalidate();
        this.mSelectedLocation = iconLocation;
    }

    @Override // com.byd.card.drag.IPageView
    public void hitTest3(int x, int y, HitResult hitTest3) {
        int i;
        if (this.mLayoutCalculator == null) {
            return;
        }
        hitTest3.index = -1;
        hitTest3.buttonRemove = false;
        int verticalGap = getVerticalGap() + this.mLayoutCalculator.marginTop;
        if (AppUtils.isRightRudder()) {
            i = this.mLayoutCalculator.marginLeft + this.mLayoutCalculator.gapH + 25;
        } else {
            i = this.mLayoutCalculator.marginLeft + this.mLayoutCalculator.gapH;
        }
        int i2 = this.mLayoutCalculator.itemHeight + verticalGap;
        int i3 = this.mLayoutCalculator.iconWidth + this.mLayoutCalculator.gapH;
        int i4 = x - i;
        int i5 = i4 % i3;
        int i6 = (i4 / i3) + (i5 <= 0 ? 0 : 1);
        int i7 = y % i2;
        int i8 = (y / i2) + (i7 == 0 ? 0 : 1);
        if (i5 <= 0 || i5 >= this.mLayoutCalculator.iconWidth || i7 <= verticalGap || i7 >= i2) {
            return;
        }
        if (i6 > LayoutCalculator.APP_COLUMNS) {
            i6 = LayoutCalculator.APP_COLUMNS;
        }
        int size = this.mInfo.size();
        int i9 = (i8 - 1) * LayoutCalculator.APP_COLUMNS;
        if (i6 == 0) {
            i6 = 1;
        }
        int iSwitchRtlIndex = (i9 + i6) - 1;
        if (AppUtils.isRtl(this.mContext)) {
            iSwitchRtlIndex = switchRtlIndex(iSwitchRtlIndex);
        }
        if (iSwitchRtlIndex < size) {
            hitTest3.index = iSwitchRtlIndex;
            hitTest3.buttonRemove = false;
        }
    }

    @Override // com.byd.card.drag.IPageView
    public int hitTest2(int x, int y, HitResult hitTest2, boolean isFolder) {
        hitTest2.inIcon = false;
        int i = this.mLayoutCalculator.iconMarginLeft;
        int verticalGap = getVerticalGap() + this.mLayoutCalculator.marginTop;
        if (this.mInfo.size() == 0) {
            hitTest2.index = 0;
            hitTest2.inIcon = false;
            return 0;
        }
        int iIndexOf = this.mInfo.indexOf(null);
        if (iIndexOf == -1) {
            iIndexOf = this.mInfo.size();
            int size = (this.mInfo.size() / this.mColumns) + 1;
            if (size > 0) {
                this.mRows = size;
                this.mViewHeight = (size * (this.mLayoutCalculator.getItemHeight() + getVerticalGap())) + getVerticalGap();
            }
        }
        int i2 = this.mLayoutCalculator.itemHeight + verticalGap;
        int i3 = this.mLayoutCalculator.iconWidth + this.mLayoutCalculator.gapH;
        int size2 = this.mInfo.size() - 1;
        int i4 = x - i;
        int i5 = i4 % i3;
        int i6 = (i4 / i3) + (i5 <= 0 ? 0 : 1);
        int i7 = y % i2;
        int i8 = (y / i2) + (i7 == 0 ? 0 : 1);
        if (i6 < 0) {
            i6 = 0;
        } else if (i6 > LayoutCalculator.APP_COLUMNS) {
            i6 = LayoutCalculator.APP_COLUMNS;
        }
        int i9 = (((i8 - 1) * LayoutCalculator.APP_COLUMNS) + (i6 == 0 ? 1 : i6)) - 1;
        if (i9 < 0) {
            i9 = 0;
        }
        hitTest2.index = i9;
        if (i9 > size2) {
            return iIndexOf != size2 ? 0 : 2;
        }
        if (y > verticalGap) {
            if (!isFolder && iIndexOf != i9 && i5 > 0 && i5 < this.mLayoutCalculator.iconWidth * 1.25f && i7 > verticalGap && i7 < i2) {
                hitTest2.inIcon = true;
                return 0;
            }
            if (i9 < iIndexOf) {
                if (i6 == LayoutCalculator.APP_COLUMNS) {
                    return 2;
                }
                i9 += i6 == 0 ? 0 : 1;
                hitTest2.index = i9;
            }
            if (iIndexOf != i9 && ((i5 <= 0 || i5 >= this.mLayoutCalculator.iconWidth * 1.25f) && i7 > verticalGap && i7 < i2)) {
                return 0;
            }
        }
        return 2;
    }

    @Override // com.byd.card.drag.IPageView
    public int hitTest2Rtl(int x, int y, HitResult hitTest2, boolean isFolder) {
        hitTest2.inIcon = false;
        int i = this.mLayoutCalculator.iconMarginLeft;
        int verticalGap = getVerticalGap() + this.mLayoutCalculator.marginTop;
        if (this.mInfo.size() == 0) {
            hitTest2.index = 0;
            hitTest2.inIcon = false;
            return 0;
        }
        int iIndexOf = this.mInfo.indexOf(null);
        if (iIndexOf == -1) {
            iIndexOf = this.mInfo.size();
            int size = (this.mInfo.size() / this.mColumns) + 1;
            if (size > 0) {
                this.mRows = size;
                this.mViewHeight = (size * (this.mLayoutCalculator.getItemHeight() + getVerticalGap())) + getVerticalGap();
            }
        }
        int i2 = this.mLayoutCalculator.itemHeight + verticalGap;
        int i3 = this.mLayoutCalculator.iconWidth + this.mLayoutCalculator.gapH;
        int size2 = this.mInfo.size() - 1;
        int i4 = x - i;
        int i5 = i4 % i3;
        int i6 = (i4 / i3) + (i5 <= 0 ? 0 : 1);
        int i7 = y % i2;
        int i8 = (y / i2) + (i7 == 0 ? 0 : 1);
        if (i6 < 0) {
            i6 = 0;
        } else if (i6 > LayoutCalculator.APP_COLUMNS) {
            i6 = LayoutCalculator.APP_COLUMNS;
        }
        int iSwitchRtlIndex = switchRtlIndex((((i8 - 1) * LayoutCalculator.APP_COLUMNS) + (i6 == 0 ? 1 : i6)) - 1);
        if (iSwitchRtlIndex < 0) {
            iSwitchRtlIndex = 0;
        }
        hitTest2.index = iSwitchRtlIndex;
        if (iSwitchRtlIndex > size2) {
            return iIndexOf != size2 ? 0 : 2;
        }
        if (y > verticalGap) {
            if (!isFolder && iIndexOf != iSwitchRtlIndex && i5 > 0 && i5 < this.mLayoutCalculator.iconWidth * 1.25f && i7 > verticalGap && i7 < i2) {
                hitTest2.inIcon = true;
                return 0;
            }
            if (!AppUtils.isRtl(this.mContext) && iSwitchRtlIndex > iIndexOf) {
                if (i6 == LayoutCalculator.APP_COLUMNS) {
                    return 2;
                }
                iSwitchRtlIndex += i6 == 0 ? 0 : 1;
                hitTest2.index = iSwitchRtlIndex;
            }
            if (iIndexOf != iSwitchRtlIndex && ((i5 <= 0 || i5 >= this.mLayoutCalculator.iconWidth * 1.25f) && i7 > verticalGap && i7 < i2)) {
                return 0;
            }
        }
        return 2;
    }

    public int switchRtlIndex(int index) {
        int i;
        if (AppUtils.isLandSpace(this.mContext)) {
            int i2 = index % LayoutCalculator.APP_COLUMNS_LAND;
            if (i2 == 0) {
                i = ((index / LayoutCalculator.APP_COLUMNS_LAND) * LayoutCalculator.APP_COLUMNS_LAND) + 4;
            } else if (i2 == 1) {
                i = ((index / LayoutCalculator.APP_COLUMNS_LAND) * LayoutCalculator.APP_COLUMNS_LAND) + 3;
            } else if (i2 == 2) {
                i = ((index / LayoutCalculator.APP_COLUMNS_LAND) * LayoutCalculator.APP_COLUMNS_LAND) + 2;
            } else if (i2 == 3) {
                i = ((index / LayoutCalculator.APP_COLUMNS_LAND) * LayoutCalculator.APP_COLUMNS) + 1;
            } else {
                if (i2 != 4) {
                    return 0;
                }
                i = (index / LayoutCalculator.APP_COLUMNS_LAND) * LayoutCalculator.APP_COLUMNS_LAND;
            }
            return i;
        }
        int i3 = index % LayoutCalculator.APP_COLUMNS_PORT;
        if (i3 == 0) {
            return ((index / LayoutCalculator.APP_COLUMNS_PORT) * LayoutCalculator.APP_COLUMNS_PORT) + 3;
        }
        if (i3 == 1) {
            return ((index / LayoutCalculator.APP_COLUMNS_PORT) * LayoutCalculator.APP_COLUMNS_PORT) + 2;
        }
        if (i3 == 2) {
            return ((index / LayoutCalculator.APP_COLUMNS_PORT) * LayoutCalculator.APP_COLUMNS_PORT) + 1;
        }
        if (i3 != 3) {
            return 0;
        }
        return (index / LayoutCalculator.APP_COLUMNS_PORT) * LayoutCalculator.APP_COLUMNS_PORT;
    }

    @Override // com.byd.card.drag.IPageView
    public ApplicationInfo getIcon(int index) {
        if (index < 0 || index >= this.mInfo.size()) {
            return null;
        }
        return this.mInfo.get(index);
    }

    @Override // com.byd.card.drag.IPageView
    public ApplicationInfo getSelectedApp() {
        int i = this.mSelected;
        if (i < 0 || i >= this.mInfo.size()) {
            return null;
        }
        return this.mInfo.get(this.mSelected);
    }

    @Override // com.byd.card.drag.IPageView
    public void deselect() {
        if (this.mSelected < 0) {
            return;
        }
        this.mSelected = -1;
        Point point = this.mSelectedLocation;
        invalidate(this.mLayoutCalculator.toItemRect(point.x, point.y));
    }

    @Override // com.byd.card.drag.IPageView
    public void jiggle() {
        this.mIsEditMode = true;
        for (int i = 0; i < this.mInfo.size(); i++) {
            ApplicationInfo applicationInfo = this.mInfo.get(i);
            if (applicationInfo != null) {
                applicationInfo.jiggle();
            }
        }
        invalidate();
    }

    @Override // com.byd.card.drag.IPageView
    public void unJiggle() {
        if (this.mInfo != null) {
            for (int i = 0; i < this.mInfo.size(); i++) {
                ApplicationInfo applicationInfo = this.mInfo.get(i);
                if (applicationInfo != null) {
                    applicationInfo.unJiggle();
                }
            }
        }
        this.mIsEditMode = false;
    }

    @Override // com.byd.card.drag.IPageView
    public int getIconsCount() {
        int i = 0;
        for (int i2 = 0; i2 < this.mInfo.size(); i2++) {
            if (this.mInfo.get(i2) != null) {
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
    public void setAppMoveListener(CombineView.AppMoveListener listener) {
        this.moveListener = listener;
    }

    @Override // com.byd.card.drag.IPageView
    public boolean setMoveTo(int index) {
        this.needMoveAnimation = true;
        int i = 0;
        if (index < 0) {
            this.needMoveAnimation = false;
            return false;
        }
        int size = 0;
        while (true) {
            if (size >= this.mInfo.size()) {
                size = -1;
                break;
            }
            if (this.mInfo.get(size) == null) {
                break;
            }
            size++;
        }
        if (size == -1) {
            size = this.mInfo.size();
            this.mInfo.add(null);
        }
        if (index >= this.mInfo.size()) {
            CombineView.AppMoveListener appMoveListener = this.moveListener;
            if (appMoveListener != null) {
                appMoveListener.onAppMove((this.mInfo.size() - 1) - size);
            }
            int i2 = size;
            while (i2 < this.mInfo.size() - 1) {
                if (i2 < this.mInfo.size() - 2) {
                    moveIcon(i2 + 1, i2, ((long) (i2 - size)) * 50, null);
                } else {
                    moveIcon(i2 + 1, i2, ((long) (i2 - size)) * 50, this.mAnimationListener);
                }
                i2++;
            }
            this.mInfo.set(i2, null);
            return true;
        }
        if (this.mInfo.get(index) == null) {
            this.needMoveAnimation = false;
            return true;
        }
        if (size > index) {
            CombineView.AppMoveListener appMoveListener2 = this.moveListener;
            if (appMoveListener2 != null) {
                appMoveListener2.onAppMove(size - index);
            }
            i = size;
            while (i > index) {
                if (i > index + 1) {
                    moveIcon(i - 1, i, ((long) (size - i)) * 50, null);
                } else {
                    moveIcon(i - 1, i, ((long) (size - i)) * 50, this.mAnimationListener);
                }
                i--;
            }
        } else if (size < index) {
            CombineView.AppMoveListener appMoveListener3 = this.moveListener;
            if (appMoveListener3 != null) {
                appMoveListener3.onAppMove(index - size);
            }
            i = size;
            while (i < index) {
                if (i < index - 1) {
                    moveIcon(i + 1, i, ((long) (i - size)) * 50, null);
                } else {
                    moveIcon(i + 1, i, ((long) (i - size)) * 50, this.mAnimationListener);
                }
                i++;
            }
        }
        this.mInfo.set(i, null);
        return true;
    }

    @Override // com.byd.card.drag.IPageView
    public void clearUp(ApplicationInfo info) {
        boolean z = info == null;
        for (int i = 0; i < this.mInfo.size(); i++) {
            if (this.mInfo.get(i) == null) {
                if (info != null) {
                    this.mInfo.set(i, info);
                    z = true;
                } else {
                    int i2 = i;
                    while (i2 < this.mInfo.size() - 1) {
                        int i3 = i2 + 1;
                        moveIcon(i3, i2, ((long) ((i2 - i) + 1)) * 50);
                        i2 = i3;
                    }
                    this.mInfo.remove(i2);
                }
            }
        }
        if (!z) {
            this.mInfo.add(info);
        }
        int size = ((this.mInfo.size() - 1) / this.mColumns) + 1;
        this.mRows = size;
        this.mViewHeight = (size * (this.mLayoutCalculator.getItemHeight() + getVerticalGap())) + getVerticalGap();
    }

    public void updateViewHeight() {
        int size = ((this.mInfo.size() - 2) / this.mColumns) + 1;
        this.mRows = size;
        this.mViewHeight = (size * (this.mLayoutCalculator.getItemHeight() + getVerticalGap())) + getVerticalGap();
    }

    private void moveIcon(int from, int to, long offset) {
        Point correctIconLocation;
        Point correctIconLocation2;
        if (AppUtils.isRtl(this.mContext)) {
            correctIconLocation = getCorrectIconLocation(switchRtlIndex(from));
            correctIconLocation2 = getCorrectIconLocation(switchRtlIndex(to));
        } else {
            correctIconLocation = getCorrectIconLocation(from);
            correctIconLocation2 = getCorrectIconLocation(to);
        }
        ApplicationInfo applicationInfo = this.mInfo.get(from);
        if (applicationInfo != null) {
            applicationInfo.mTranslateAnim = new TranslateSpringAnimation(correctIconLocation.x, correctIconLocation2.x, correctIconLocation.y, correctIconLocation2.y).setStartOffset(offset).setHandler(getHandler()).start();
        }
        setIconIntoPage(to, applicationInfo);
    }

    private void moveIcon(int from, int to, long offset, Animation.AnimationListener animationListener) {
        Point correctIconLocation;
        Point correctIconLocation2;
        if (AppUtils.isRtl(this.mContext)) {
            correctIconLocation = getCorrectIconLocation(switchRtlIndex(from));
            correctIconLocation2 = getCorrectIconLocation(switchRtlIndex(to));
        } else {
            correctIconLocation = getCorrectIconLocation(from);
            correctIconLocation2 = getCorrectIconLocation(to);
        }
        ApplicationInfo applicationInfo = this.mInfo.get(from);
        if (applicationInfo != null) {
            applicationInfo.mTranslateAnim = new TranslateSpringAnimation(correctIconLocation.x, correctIconLocation2.x, correctIconLocation.y, correctIconLocation2.y).setStartOffset(offset).setAnimationListener(animationListener).setHandler(getHandler()).start();
        }
        setIconIntoPage(to, applicationInfo);
    }

    @Override // com.byd.card.drag.IPageView
    public void setAppMoveAnimationListener(Animation.AnimationListener animationListener) {
        this.mAnimationListener = animationListener;
    }

    public int getViewHeight() {
        return this.mViewHeight;
    }

    @Override // com.byd.card.drag.IPageView
    public void removeFolderBound() {
        final ApplicationInfo applicationInfo;
        this.mIsAboveFolder = false;
        int i = this.mAboveIndex;
        if (i >= 0 && (applicationInfo = this.mInfo.get(i)) != null) {
            this.mAboveIndex = -1;
            applicationInfo.setIsAboveFolder(2);
            applicationInfo.mScaleAnim = new ScaleAnimation(1.4f, 1.0f, 1.4f, 1.0f, 50.0f, 50.0f);
            applicationInfo.mScaleAnim.initialize(this.mLayoutCalculator.getItemWidth() + this.mLayoutCalculator.gapH, this.mLayoutCalculator.getItemHeight(), getWidth(), getHeight());
            applicationInfo.mScaleAnim.setFillAfter(true);
            applicationInfo.mScaleAnim.setFillBefore(true);
            applicationInfo.mScaleAnim.setDuration(200L);
            applicationInfo.mScaleAnim.startNow();
            applicationInfo.mScaleAnim.setAnimationListener(new Animation.AnimationListener() { // from class: com.byd.card.drag.AppContentView.1
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
    public void createFolderBound(int index) {
        this.mAboveIndex = index;
        ApplicationInfo applicationInfo = this.mInfo.get(index);
        if (!this.mIsAboveFolder && applicationInfo != null) {
            applicationInfo.setIsAboveFolder(1);
            applicationInfo.mScaleAnim = new ScaleAnimation(1.0f, 1.4f, 1.0f, 1.4f);
            applicationInfo.mScaleAnim.initialize(this.mLayoutCalculator.getItemWidth() + this.mLayoutCalculator.gapH, this.mLayoutCalculator.getItemHeight(), getWidth(), getHeight());
            applicationInfo.mScaleAnim.setFillAfter(true);
            applicationInfo.mScaleAnim.setFillBefore(true);
            applicationInfo.mScaleAnim.setDuration(200L);
            applicationInfo.mScaleAnim.startNow();
        }
        this.mIsAboveFolder = true;
    }

    @Override // com.byd.card.drag.IPageView
    public void addToFolder(int i, ApplicationInfo srcApp) {
        ApplicationInfo applicationInfo;
        if (this.mInfo.size() > i && (applicationInfo = this.mInfo.get(i)) != null) {
            if (applicationInfo.getType() == 2) {
                MoreAppFolderInfo moreAppFolderInfo = (MoreAppFolderInfo) applicationInfo;
                srcApp.folderPosition = moreAppFolderInfo.getIcons().size() + 1;
                srcApp.folderId = moreAppFolderInfo.getIcons().get(0).folderId;
                moreAppFolderInfo.addIcon(srcApp);
                updateAddToFolder(moreAppFolderInfo, srcApp, i);
                return;
            }
            MoreAppFolderInfo moreAppFolderInfo2 = new MoreAppFolderInfo();
            applicationInfo.folderPosition = 0;
            srcApp.folderPosition = 1;
            moreAppFolderInfo2.addIcon(applicationInfo);
            moreAppFolderInfo2.addIcon(srcApp);
            moreAppFolderInfo2.setTitle(Constant.FolderType.DEFAULT_FOLDER);
            updateFolderId(moreAppFolderInfo2, srcApp, applicationInfo, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFolderInfo(ApplicationInfo app) {
        if (app == null) {
            return;
        }
        for (int i = 0; i < this.mInfo.size(); i++) {
            ApplicationInfo applicationInfo = this.mInfo.get(i);
            if (applicationInfo != null && app.activity.equals(applicationInfo.activity)) {
                this.mInfo.set(i, app);
            }
        }
    }

    private void updateAddToFolder(final MoreAppFolderInfo moreAppFolderInfo, final ApplicationInfo applicationInfo, final int i) {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.drag.AppContentView.2
            @Override // java.lang.Runnable
            public void run() {
                Folder folderById;
                final ApplicationInfo appByActivity;
                if (moreAppFolderInfo == null || (folderById = CardDatabase.getInstance().getFolderDao().getFolderById(moreAppFolderInfo.folderId)) == null || (appByActivity = CardDatabase.getInstance().getWorkSpaceItemDao().getAppByActivity(applicationInfo.getActivity())) == null) {
                    return;
                }
                appByActivity.folderId = folderById.folder_id;
                appByActivity.folderPosition = applicationInfo.folderPosition;
                CardDatabase.getInstance().getWorkSpaceItemDao().update(appByActivity);
                Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.drag.AppContentView.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AppContentView.this.updateFolderInfo(appByActivity);
                        AppContentView.this.setIconIntoPage(i, moreAppFolderInfo);
                        AppContentView.this.clearUp(null);
                    }
                });
            }
        });
    }

    private void updateFolderId(final MoreAppFolderInfo folder, final ApplicationInfo srcApp, final ApplicationInfo desApp, final int i) {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.drag.AppContentView.3
            @Override // java.lang.Runnable
            public void run() {
                int maxFolderId = CardDatabase.getInstance().getFolderDao().getMaxFolderId();
                MoreAppFolderInfo moreAppFolderInfo = folder;
                ApplicationInfo applicationInfo = srcApp;
                int i2 = maxFolderId + 1;
                desApp.folderId = i2;
                applicationInfo.folderId = i2;
                moreAppFolderInfo.folderId = i2;
                Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.drag.AppContentView.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AppContentView.this.updateFolderInfo(srcApp);
                        AppContentView.this.updateFolderInfo(desApp);
                        AppContentView.this.setIconIntoPage(i, folder);
                        AppContentView.this.clearUp(null);
                        if (AppContentView.this.listener != null) {
                            AppContentView.this.listener.onHeightChanged();
                        }
                        DBUtils.getInstance().insertNewFolder(folder, srcApp, desApp);
                    }
                });
            }
        });
    }

    public void setAppInfo(List<ApplicationInfo> icons) {
        this.mInfo = icons;
        this.mColumns = LayoutCalculator.APP_COLUMNS;
        int size = ((this.mInfo.size() - 1) / this.mColumns) + 1;
        this.mRows = size;
        this.mViewHeight = (size * (this.mLayoutCalculator.getItemHeight() + getVerticalGap())) + getVerticalGap();
        updateForbiddenApp(ForbiddenManager.isPGear());
    }

    public void updateForbiddenApp(boolean isPGear) {
        List<ApplicationInfo> list = this.mInfo;
        if (list == null || list.size() == 0) {
            return;
        }
        List<String> forbiddenAppListOnNonP = ForbiddenManager.getForbiddenAppListOnNonP();
        if (forbiddenAppListOnNonP.size() <= 0) {
            Log.d(this.TAG, "updateForbiddenApp, no forbidden app installed!");
            return;
        }
        for (ApplicationInfo applicationInfo : this.mInfo) {
            if (applicationInfo == null) {
                Log.e(this.TAG, "updateForbiddenApp, info is null!");
            } else if (applicationInfo instanceof MoreAppFolderInfo) {
                MoreAppFolderInfo moreAppFolderInfo = (MoreAppFolderInfo) applicationInfo;
                moreAppFolderInfo.setNeedUpdate(true);
                for (ApplicationInfo applicationInfo2 : moreAppFolderInfo.getIcons()) {
                    if (applicationInfo2 == null) {
                        Log.e(this.TAG, "updateForbiddenApp, info is null!");
                    } else {
                        updateForbiddenApp(applicationInfo2, isPGear, forbiddenAppListOnNonP);
                    }
                }
            } else {
                updateForbiddenApp(applicationInfo, isPGear, forbiddenAppListOnNonP);
            }
        }
        invalidate();
    }

    private void updateForbiddenApp(ApplicationInfo info, boolean isPGear, List<String> forbiddenAppOnNonP) {
        if (isPGear) {
            info.setForbidden(false);
            return;
        }
        Iterator<String> it = forbiddenAppOnNonP.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next(), info.getPackageName())) {
                info.setForbidden(true);
                return;
            }
        }
    }

    public void deletedFolder(ApplicationInfo deleteApp) {
        for (int i = 0; i < this.mInfo.size(); i++) {
            ApplicationInfo applicationInfo = this.mInfo.get(i);
            if (applicationInfo != null && applicationInfo.getType() == 2) {
                MoreAppFolderInfo moreAppFolderInfo = (MoreAppFolderInfo) applicationInfo;
                List<ApplicationInfo> icons = moreAppFolderInfo.getIcons();
                if (icons.size() == 1) {
                    this.mInfo.remove(i);
                    this.mInfo.add(i, icons.get(0));
                    DBUtils.getInstance().updateFolderAndAppDB(moreAppFolderInfo, deleteApp);
                } else if (icons.size() == 0) {
                    this.mInfo.remove(i);
                }
            }
        }
    }

    public void deletedFolder() {
        for (int i = 0; i < this.mInfo.size(); i++) {
            ApplicationInfo applicationInfo = this.mInfo.get(i);
            if (applicationInfo != null && applicationInfo.getType() == 2) {
                List<ApplicationInfo> icons = ((MoreAppFolderInfo) applicationInfo).getIcons();
                if (icons.size() == 1) {
                    this.mInfo.remove(i);
                    this.mInfo.add(i, icons.get(0));
                } else if (icons.size() == 0) {
                    this.mInfo.remove(i);
                }
            }
        }
    }

    public void removeFolder(ApplicationInfo stayApp) {
        for (int i = 0; i < this.mInfo.size(); i++) {
            ApplicationInfo applicationInfo = this.mInfo.get(i);
            if (applicationInfo != null && applicationInfo.getType() == 2) {
                Iterator<ApplicationInfo> it = ((MoreAppFolderInfo) applicationInfo).getIcons().iterator();
                while (it.hasNext()) {
                    if (it.next().getPackageName().equals(stayApp.getPackageName())) {
                        this.mInfo.remove(i);
                        this.mInfo.add(i, stayApp);
                    }
                }
            }
        }
    }

    public void updateAppFolderId(ApplicationInfo app) {
        ApplicationInfo appByPackageName = CardDatabase.getInstance().getWorkSpaceItemDao().getAppByPackageName(app.getPackageName());
        appByPackageName.folderId = 0;
        for (int i = 0; i < this.mInfo.size(); i++) {
            ApplicationInfo applicationInfo = this.mInfo.get(i);
            if (applicationInfo != null && appByPackageName.getPackageName().equals(applicationInfo.mPackageName)) {
                appByPackageName.position = i;
            }
        }
        CardDatabase.getInstance().getWorkSpaceItemDao().update(appByPackageName);
    }

    public void deleteAPP(ApplicationInfo app) {
        for (int i = 0; i < this.mInfo.size(); i++) {
            ApplicationInfo applicationInfo = this.mInfo.get(i);
            if (applicationInfo != null && TextUtils.equals(applicationInfo.getPackageName(), app.getPackageName())) {
                removeApp(i);
                Log.i(this.TAG, "deleteAPP app = " + app + " index = " + i);
                return;
            }
        }
    }

    @Override // com.byd.card.drag.IPageView
    public void removeApp(int index) {
        setIconIntoPage(index, null);
        clearUp(null);
    }
}
