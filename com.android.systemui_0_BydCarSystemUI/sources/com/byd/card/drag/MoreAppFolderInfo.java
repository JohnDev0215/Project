package com.byd.card.drag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.BitmapUtils;
import com.byd.card.view.activity.MoreAppActivity;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MoreAppFolderInfo extends ApplicationInfo {
    private Context mContext;
    private FolderView mFolderView;
    private CopyOnWriteArrayList<ApplicationInfo> apps = new CopyOnWriteArrayList<>();
    private volatile boolean needUpdate = false;
    private CopyOnWriteArrayList<ApplicationInfo> tempList = new CopyOnWriteArrayList<>();

    public MoreAppFolderInfo() {
        this.type = 2;
    }

    @Override // com.byd.card.drag.ApplicationInfo
    public void drawIcon(Context context, LayoutCalculator lc, ObjectPool op, Canvas canvas, int x, int y, Paint iconPaint) {
        this.mContext = context;
        drawFolderIcon(op, canvas, x, y, iconPaint);
        drawIconContent(context, lc, op, canvas, x, y, iconPaint);
    }

    public void drawFolderIcon(ObjectPool op, Canvas canvas, int x, int y, Paint iconPaint) {
        Bitmap folderIcon = op.getFolderIcon();
        float iconScale = getIconScale();
        if (iconScale == 1.0f) {
            canvas.drawBitmap(folderIcon, x, y, iconPaint);
            return;
        }
        if (folderIcon == null || iconScale == 0.0f) {
            return;
        }
        if (this.mMatrix == null) {
            this.mMatrix = new Matrix();
        }
        this.mMatrix.setTranslate(x / iconScale, y / iconScale);
        this.mMatrix.postScale(iconScale, iconScale);
        float f = (1.0f - iconScale) * 0.5f;
        this.mMatrix.postTranslate(folderIcon.getWidth() * f, folderIcon.getHeight() * f);
        canvas.drawBitmap(folderIcon, this.mMatrix, iconPaint);
    }

    @Override // com.byd.card.drag.ApplicationInfo
    public void drawIconContent(Context context, LayoutCalculator lc, ObjectPool op, Canvas canvas, int x, int y, Paint iconPaint) {
        if (getIcon() == null || getIcon().isRecycled() || this.needUpdate) {
            this.needUpdate = false;
            float fDpToPixel = lc.dpToPixel(8);
            float f = lc.folderIconSize;
            float f2 = lc.folderGap;
            int i = LayoutCalculator.FOLDER_ROWS;
            float f3 = 2.0f * fDpToPixel;
            clearIcon();
            setIcon(BitmapManager.createBitmap((int) ((LayoutCalculator.FOLDER_COLUMNS * f) + (f2 * 2.0f) + f3), (int) ((i * f) + ((i - 1) * f2) + f3), Bitmap.Config.ARGB_8888));
            if (AppUtils.isRtl(this.mContext)) {
                this.tempList.clear();
                if (this.apps.size() > 9) {
                    for (int i2 = 0; i2 < 9; i2++) {
                        this.tempList.add(this.apps.get(i2));
                    }
                } else {
                    this.tempList.addAll(this.apps);
                }
                for (int i3 = 0; i3 < this.apps.size() && i3 < LayoutCalculator.FOLDER_ROWS * LayoutCalculator.FOLDER_COLUMNS; i3++) {
                    float f4 = f + f2;
                    drawFolderSmallIcon(context, i3, fDpToPixel + (switchRtlColumn(i3) * f4), fDpToPixel + ((i3 / LayoutCalculator.FOLDER_ROWS) * f4), f);
                }
            } else {
                for (int i4 = 0; i4 < this.apps.size() && i4 < LayoutCalculator.FOLDER_ROWS * LayoutCalculator.FOLDER_COLUMNS; i4++) {
                    float f5 = f + f2;
                    drawFolderSmallIcon(context, i4, fDpToPixel + ((i4 % LayoutCalculator.FOLDER_COLUMNS) * f5), fDpToPixel + ((i4 / LayoutCalculator.FOLDER_ROWS) * f5), f);
                }
            }
        }
        super.drawIcon(context, lc, op, canvas, x, y, iconPaint);
    }

    private int switchRtlColumn(int index) {
        int i = index % LayoutCalculator.FOLDER_COLUMNS;
        if (i != 0) {
            return i != 1 ? 0 : 1;
        }
        return 2;
    }

    private void drawFolderSmallIcon(Context context, int index, float x1, float y1, float folderIconSize) {
        ApplicationInfo applicationInfo = this.apps.get(index);
        if (applicationInfo != null) {
            Bitmap bitmapChangedBitmap = BitmapUtils.changedBitmap(context, BitmapUtils.adapterMultiTheme(applicationInfo.getPackageName(), applicationInfo.getIconBytes()));
            if (bitmapChangedBitmap == null) {
                bitmapChangedBitmap = applicationInfo.getIcon();
            }
            if (bitmapChangedBitmap != null) {
                Canvas canvas = new Canvas(this.icon);
                Paint paint = new Paint();
                if (applicationInfo.isForbidden()) {
                    paint.setAlpha(102);
                }
                canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 2));
                paint.setFilterBitmap(true);
                paint.setAntiAlias(true);
                RectF rectF = new RectF(x1, y1, x1 + folderIconSize, y1 + folderIconSize);
                canvas.drawRoundRect(rectF, 5.0f, 5.0f, paint);
                int i = (int) folderIconSize;
                Bitmap bitmapScaleBitmap = BitmapUtils.scaleBitmap(bitmapChangedBitmap, i, i);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(bitmapScaleBitmap, (Rect) null, rectF, paint);
            }
        }
    }

    public List<ApplicationInfo> getIcons() {
        return this.apps;
    }

    public void setNeedUpdate(boolean needUpdate) {
        this.needUpdate = needUpdate;
    }

    public void setIcons(List<ApplicationInfo> icons) {
        this.apps.clear();
        this.apps.addAll(icons);
        this.needUpdate = true;
    }

    public FolderView getFolderView(MoreAppActivity activity) {
        if (this.mFolderView == null) {
            FolderView folderView = new FolderView(activity);
            this.mFolderView = folderView;
            folderView.setInfo(this);
        }
        return this.mFolderView;
    }

    public void addIcon(ApplicationInfo app) {
        this.apps.add(app);
        this.needUpdate = true;
    }

    public void addIcon(ApplicationInfo app, int index) {
        this.apps.add(index, app);
        this.needUpdate = true;
    }

    public void removeAppIcon(ApplicationInfo app) {
        this.apps.remove(app);
        this.needUpdate = true;
    }
}
