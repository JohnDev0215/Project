package com.byd.card.drag;

import android.app.UiStyleConfig;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.animation.ScaleAnimation;
import com.android.systemui.R;
import com.byd.card.bean.Constant;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ApplicationInfo extends ItemInfo {
    public static final int ABOVER_FOLDER_BIG = 1;
    public static final int ABOVER_FOLDER_SMALL = 2;
    private static final String TAG = "ApplicationInfo";
    public boolean checked;
    public int folderId;
    private boolean isForbidden;
    private boolean isSelected;
    private Bitmap mDrawIcon;
    private Intent mIntent;
    protected Matrix mMatrix;
    public ScaleAnimation mScaleAnim;
    public TranslateSpringAnimation mTranslateAnim;
    public boolean neddUpdateDrawIcon;
    public int x;
    public int y;
    private int isAboveFolder = -1;
    public boolean showTitle = true;
    private float mIconScale = 1.0f;

    @Override // com.byd.card.drag.ItemInfo
    public void setIconListener() {
        super.setIconListener();
        this.neddUpdateDrawIcon = true;
    }

    public boolean isForbidden() {
        return this.isForbidden;
    }

    public void setForbidden(boolean forbidden) {
        this.isForbidden = forbidden;
    }

    public void setIconScale(float scale) {
        this.mIconScale = scale;
    }

    public float getIconScale() {
        return this.mIconScale;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public boolean getSelected() {
        return this.isSelected;
    }

    public ApplicationInfo() {
        this.isSelected = false;
        this.type = 1;
        this.isSelected = false;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public void setIntent(Intent intent) {
        this.mIntent = intent;
    }

    public void drawBoundIcon(Context context, LayoutCalculator lc, ObjectPool op, Canvas canvas, int x, int y, Paint paint, Paint iconPaint) {
        drawIcon(context, lc, op, canvas, x, y, iconPaint);
        drawTitle(context, lc, paint, canvas, x, y);
    }

    public void drawBoundIcon(Context context, LayoutCalculator lc, ObjectPool op, Canvas canvas, int x, int y, Paint paint, Paint iconPaint, int parentX, int parentY) {
        Paint paint2;
        if (this.isSelected) {
            Paint paint3 = iconPaint == null ? new Paint() : iconPaint;
            paint3.setAlpha(0);
            paint2 = paint3;
        } else {
            paint2 = iconPaint;
        }
        int i = x + 10;
        drawIcon(context, lc, op, canvas, i, y, paint2);
        drawTitle(context, lc, paint, canvas, i, y);
        this.x = i + parentX;
        this.y = y + parentY;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:116:0x021d A[PHI: r13
  0x021d: PHI (r13v5 java.lang.String) = 
  (r13v1 java.lang.String)
  (r13v2 java.lang.String)
  (r13v3 java.lang.String)
  (r13v4 java.lang.String)
  (r13v6 java.lang.String)
 binds: [B:115:0x021b, B:111:0x020e, B:107:0x0201, B:103:0x01f4, B:99:0x01e9] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void drawIcon(android.content.Context r20, com.byd.card.drag.LayoutCalculator r21, com.byd.card.drag.ObjectPool r22, android.graphics.Canvas r23, int r24, int r25, android.graphics.Paint r26) {
        /*
            Method dump skipped, instruction units count: 1240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.card.drag.ApplicationInfo.drawIcon(android.content.Context, com.byd.card.drag.LayoutCalculator, com.byd.card.drag.ObjectPool, android.graphics.Canvas, int, int, android.graphics.Paint):void");
    }

    public void drawFolderBound(LayoutCalculator lc, ObjectPool pp, Canvas canvas, int x, int y, Paint paint, float scale) {
        Bitmap folderIcon;
        if (this.isAboveFolder == -1 || (folderIcon = pp.getFolderIcon()) == null) {
            return;
        }
        Bitmap bitmapStretch = pp.stretch(folderIcon, scale);
        float f = this.mIconScale;
        if (f == 1.0f) {
            canvas.drawBitmap(bitmapStretch, x, y, paint);
            return;
        }
        if (f != 0.0f) {
            if (this.mMatrix == null) {
                this.mMatrix = new Matrix();
            }
            this.mMatrix.setTranslate(x / f, y / f);
            this.mMatrix.postScale(f, f);
            float f2 = (1.0f - f) * 0.5f;
            this.mMatrix.postTranslate(lc.iconWidth * f2, lc.iconWidth * f2);
            canvas.drawBitmap(bitmapStretch, this.mMatrix, paint);
        }
    }

    public void drawTitle(Context context, LayoutCalculator lc, Paint paint, Canvas canvas, int x, int y) {
        if (this.showTitle) {
            paint.setAlpha(255);
            if (Constant.FolderType.DEFAULT_FOLDER.equals(this.title)) {
                this.title = context.getString(R.string.folder_not_name);
            }
            if (Constant.FolderType.TOOLS_FOLDER.equals(this.title)) {
                this.title = context.getString(R.string.folder_name_utils);
            }
            if (TextUtils.isEmpty(this.title)) {
                return;
            }
            if (1 == this.type || 3 == this.type) {
                drawText(context, lc, this.title, x, y, context.getResources().getDimensionPixelOffset(R.dimen.dp_20), context.getResources().getDimensionPixelOffset(R.dimen.dp_128), paint, canvas);
            } else {
                drawFolderTitle(lc, this.title, x, y, context.getResources().getDimensionPixelOffset(R.dimen.dp_20), context.getResources().getDimensionPixelOffset(R.dimen.dp_128), paint, canvas);
            }
        }
    }

    private void drawText(Context context, LayoutCalculator lc, String text, int x, int y, int lineHeight, int maxWidth, Paint paint, Canvas canvas) {
        int alpha = paint.getAlpha();
        if (this.isForbidden) {
            paint.setAlpha(102);
        }
        if (text.contains("-")) {
            drawAppName(text, canvas, paint, x, y, lc, lineHeight, maxWidth);
        } else {
            List<String> wrapText = getWrapText(text, maxWidth, paint);
            int iconWidth = x + (lc.getIconWidth() / 2);
            int size = wrapText.size();
            if (size > 2) {
                float f = iconWidth;
                canvas.drawText(wrapText.get(0), f, y + lc.textTop, paint);
                canvas.drawText(wrapText.get(1).substring(0, wrapText.get(1).length() - 2) + "...", f, y + lineHeight + lc.textTop + 5, paint);
            } else if (size == 2) {
                String str = wrapText.get(1);
                if (str.length() == 1) {
                    Paint paintNewPaint = newPaint(context);
                    paintNewPaint.setLetterSpacing(-0.05f);
                    canvas.drawText(wrapText.get(0) + str, iconWidth, y + lc.textTop, paintNewPaint);
                } else if (str.length() == 2) {
                    float f2 = iconWidth;
                    canvas.drawText(wrapText.get(0), f2, y + lc.textTop, paint);
                    canvas.drawText("-" + str, f2, y + lineHeight + lc.textTop, paint);
                } else {
                    String str2 = wrapText.get(0);
                    float f3 = iconWidth;
                    canvas.drawText(str2, f3, y + lc.textTop, paint);
                    canvas.drawText(str, f3, y + lineHeight + lc.textTop, paint);
                }
            } else {
                for (int i = 0; i < size; i++) {
                    canvas.drawText(wrapText.get(i), iconWidth, y + (lineHeight * i) + lc.textTop, paint);
                }
            }
        }
        if (this.isForbidden) {
            paint.setAlpha(alpha);
        }
    }

    private void drawAppName(String name, Canvas canvas, Paint paint, int x, int y, LayoutCalculator lc, int lineHeight, int maxWidth) {
        if (((int) paint.measureText(name)) > maxWidth) {
            int iLastIndexOf = name.lastIndexOf("-");
            canvas.drawText(name.substring(0, iLastIndexOf) + "-", (lc.getIconWidth() / 2) + x, lc.textTop + y, paint);
            String strSubstring = name.substring(iLastIndexOf + 1);
            if (paint.measureText(strSubstring) > maxWidth) {
                strSubstring = subAppName(paint, strSubstring, maxWidth);
            }
            canvas.drawText(strSubstring, x + (lc.getIconWidth() / 2), y + lineHeight + lc.textTop, paint);
            return;
        }
        canvas.drawText(name, x + (lc.getIconWidth() / 2), y + lc.textTop, paint);
    }

    private String subAppName(Paint paint, String secondName, int maxWidth) {
        String strSubstring = secondName.substring(0, secondName.length() - 1);
        if (paint.measureText(strSubstring) > maxWidth) {
            return subAppName(paint, strSubstring, maxWidth);
        }
        return strSubstring.substring(0, strSubstring.length() - 2) + "...";
    }

    private void drawFolderTitle(LayoutCalculator lc, String text, int x, int y, int lineHeight, int maxWidth, Paint paint, Canvas canvas) {
        List<String> wrapText = getWrapText(text, maxWidth, paint);
        int iconWidth = x + (lc.getIconWidth() / 2);
        int size = wrapText.size();
        if (size > 2) {
            float f = iconWidth;
            canvas.drawText(wrapText.get(0), f, lc.textTop + y, paint);
            canvas.drawText(wrapText.get(1).substring(0, wrapText.get(1).length() - 2) + "...", f, y + lineHeight + lc.textTop + 5, paint);
        } else {
            for (int i = 0; i < size; i++) {
                canvas.drawText(wrapText.get(i), iconWidth, (lineHeight * i) + y + lc.textTop, paint);
            }
        }
    }

    private Paint newPaint(Context context) {
        Paint paint = new Paint();
        int uiStyle = UiStyleConfig.getInstance(context).getUiStyle();
        if (uiStyle == 1) {
            paint.setColor(context.getResources().getColor(R.color.denza_app_name));
        } else if (uiStyle != 2) {
            paint.setColor(context.getResources().getColor(R.color.app_center_tv));
        }
        paint.setTextSize(context.getResources().getDimension(R.dimen.sp_16));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        return paint;
    }

    private List<String> getWrapText(String text, int maxWidth, Paint paint) {
        ArrayList arrayList = new ArrayList();
        String strSubstring = "";
        int i = 0;
        while (true) {
            if (i >= text.codePointCount(0, text.length())) {
                break;
            }
            strSubstring = strSubstring + resolveCodePoint(text.codePointAt(text.offsetByCodePoints(0, i)));
            if (paint.measureText(strSubstring) >= maxWidth) {
                if (arrayList.size() < 1) {
                    int iLastIndexOf = strSubstring.lastIndexOf(" ");
                    if (iLastIndexOf != -1) {
                        arrayList.add(strSubstring.substring(0, iLastIndexOf));
                        strSubstring = strSubstring.substring(iLastIndexOf + 1);
                    } else {
                        arrayList.add(strSubstring);
                        strSubstring = "";
                    }
                } else {
                    arrayList.add(strSubstring);
                    break;
                }
            }
            i++;
        }
        arrayList.add(strSubstring);
        return arrayList;
    }

    private String resolveCodePoint(int codePoint) {
        if (codePoint < 65536) {
            return "" + ((char) codePoint);
        }
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(codePoint - 65536));
        int length = 20 - sb.length();
        for (int i = 0; i < length; i++) {
            sb.insert(0, 0);
        }
        return "" + ((char) (Integer.parseInt(sb.substring(0, 10), 2) + 55296)) + ((char) (Integer.parseInt(sb.substring(10), 2) + 56320));
    }

    public void measureTitle(Paint paint, LayoutCalculator lc) {
        if (TextUtils.isEmpty(this.title)) {
            return;
        }
        int itemWidth = lc.getItemWidth();
        float f = itemWidth;
        int length = this.title.length() - ((int) Math.ceil((r0 - f) / (paint.measureText(this.title, 0, this.title.length()) > f ? r0 / this.title.length() : 1.0f)));
        while (paint.measureText(this.title, 0, this.title.length()) > f) {
            this.title = this.title.subSequence(0, length).toString() + "...";
            length--;
        }
        this.titleMeasured = true;
    }

    public Bitmap getIcon() {
        if (this.icon != null) {
            return this.icon;
        }
        if (this.iconRef == null || this.iconRef.get() == null) {
            return null;
        }
        this.icon = this.iconRef.get();
        return this.icon;
    }

    public void drawIconContent(Context context, LayoutCalculator lc, ObjectPool op, Canvas canvas, int x, int y, Paint iconPaint) {
        drawIcon(context, lc, op, canvas, x, y, iconPaint);
    }

    public void drawIconContent(Context context, LayoutCalculator lc, ObjectPool op, Canvas canvas, int x, int y, Paint iconPaint, int px, int py) {
        drawIcon(context, lc, op, canvas, x, y, iconPaint);
        this.x = x + px;
        this.y = y + py;
    }

    public int getIsAboveFolder() {
        return this.isAboveFolder;
    }

    public void setIsAboveFolder(int isAboveFolder) {
        this.isAboveFolder = isAboveFolder;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setTitleVisibility(boolean show) {
        this.showTitle = show;
    }

    @Override // com.byd.card.drag.ItemInfo
    public String toString() {
        return "ApplicationInfo{folderId=" + this.folderId + "name =" + this.mPackageName + ", mIntent=" + this.mIntent + ", mTranslateAnim=" + this.mTranslateAnim + ", mScaleAnim=" + this.mScaleAnim + ", isAboveFolder=" + this.isAboveFolder + ", checked=" + this.checked + ", isForbidden=" + this.isForbidden + ", title='" + this.title + "', position=" + this.position + ", folderPosition=" + this.folderPosition + '}';
    }
}
