package com.android.launcher3.icons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;
import kotlin.UByte;

/* JADX INFO: loaded from: classes6.dex */
public class IconNormalizer {
    private static final float BOUND_RATIO_MARGIN = 0.05f;
    private static final float CIRCLE_AREA_BY_RECT = 0.7853982f;
    private static final boolean DEBUG = false;
    public static final float ICON_VISIBLE_AREA_FACTOR = 0.92f;
    private static final float LINEAR_SCALE_SLOPE = 0.040449437f;
    private static final float MAX_CIRCLE_AREA_FACTOR = 0.6597222f;
    private static final float MAX_SQUARE_AREA_FACTOR = 0.6510417f;
    private static final int MIN_VISIBLE_ALPHA = 40;
    private static final float PIXEL_DIFF_PERCENTAGE_THRESHOLD = 0.005f;
    private static final float SCALE_NOT_INITIALIZED = 0.0f;
    private static final String TAG = "IconNormalizer";
    private final RectF mAdaptiveIconBounds;
    private float mAdaptiveIconScale;
    private final Bitmap mBitmap;
    private final Rect mBounds;
    private final Canvas mCanvas;
    private boolean mEnableShapeDetection;
    private final float[] mLeftBorder;
    private final Matrix mMatrix;
    private final int mMaxSize;
    private final Paint mPaintMaskShape;
    private final Paint mPaintMaskShapeOutline;
    private final byte[] mPixels;
    private final float[] mRightBorder;
    private final Path mShapePath;

    IconNormalizer(Context context, int iconBitmapSize, boolean shapeDetection) {
        int i = iconBitmapSize * 2;
        this.mMaxSize = i;
        this.mBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ALPHA_8);
        this.mCanvas = new Canvas(this.mBitmap);
        int i2 = this.mMaxSize;
        this.mPixels = new byte[i2 * i2];
        this.mLeftBorder = new float[i2];
        this.mRightBorder = new float[i2];
        this.mBounds = new Rect();
        this.mAdaptiveIconBounds = new RectF();
        Paint paint = new Paint();
        this.mPaintMaskShape = paint;
        paint.setColor(SupportMenu.CATEGORY_MASK);
        this.mPaintMaskShape.setStyle(Paint.Style.FILL);
        this.mPaintMaskShape.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        Paint paint2 = new Paint();
        this.mPaintMaskShapeOutline = paint2;
        paint2.setStrokeWidth(context.getResources().getDisplayMetrics().density * 2.0f);
        this.mPaintMaskShapeOutline.setStyle(Paint.Style.STROKE);
        this.mPaintMaskShapeOutline.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.mPaintMaskShapeOutline.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.mShapePath = new Path();
        this.mMatrix = new Matrix();
        this.mAdaptiveIconScale = 0.0f;
        this.mEnableShapeDetection = shapeDetection;
    }

    private static float getScale(float hullArea, float boundingArea, float fullArea) {
        float scaleRequired;
        float hullByRect = hullArea / boundingArea;
        if (hullByRect < CIRCLE_AREA_BY_RECT) {
            scaleRequired = MAX_CIRCLE_AREA_FACTOR;
        } else {
            scaleRequired = MAX_SQUARE_AREA_FACTOR + ((1.0f - hullByRect) * LINEAR_SCALE_SLOPE);
        }
        float areaScale = hullArea / fullArea;
        if (areaScale > scaleRequired) {
            return (float) Math.sqrt(scaleRequired / areaScale);
        }
        return 1.0f;
    }

    public static float normalizeAdaptiveIcon(Drawable d, int size, RectF outBounds) {
        Rect tmpBounds = new Rect(d.getBounds());
        d.setBounds(0, 0, size, size);
        Path path = ((AdaptiveIconDrawable) d).getIconMask();
        Region region = new Region();
        region.setPath(path, new Region(0, 0, size, size));
        Rect hullBounds = region.getBounds();
        int hullArea = GraphicsUtils.getArea(region);
        if (outBounds != null) {
            float sizeF = size;
            outBounds.set(hullBounds.left / sizeF, hullBounds.top / sizeF, 1.0f - (hullBounds.right / sizeF), 1.0f - (hullBounds.bottom / sizeF));
        }
        d.setBounds(tmpBounds);
        return getScale(hullArea, hullArea, size * size);
    }

    private boolean isShape(Path maskPath) {
        float iconRatio = this.mBounds.width() / this.mBounds.height();
        if (Math.abs(iconRatio - 1.0f) > BOUND_RATIO_MARGIN) {
            return false;
        }
        this.mMatrix.reset();
        this.mMatrix.setScale(this.mBounds.width(), this.mBounds.height());
        this.mMatrix.postTranslate(this.mBounds.left, this.mBounds.top);
        maskPath.transform(this.mMatrix, this.mShapePath);
        this.mCanvas.drawPath(this.mShapePath, this.mPaintMaskShape);
        this.mCanvas.drawPath(this.mShapePath, this.mPaintMaskShapeOutline);
        return isTransparentBitmap();
    }

    private boolean isTransparentBitmap() {
        ByteBuffer buffer = ByteBuffer.wrap(this.mPixels);
        buffer.rewind();
        this.mBitmap.copyPixelsToBuffer(buffer);
        int y = this.mBounds.top;
        int i = this.mMaxSize;
        int index = y * i;
        int rowSizeDiff = i - this.mBounds.right;
        int sum = 0;
        while (y < this.mBounds.bottom) {
            int index2 = index + this.mBounds.left;
            for (int x = this.mBounds.left; x < this.mBounds.right; x++) {
                if ((this.mPixels[index2] & UByte.MAX_VALUE) > 40) {
                    sum++;
                }
                index2++;
            }
            index = index2 + rowSizeDiff;
            y++;
        }
        float percentageDiffPixels = sum / (this.mBounds.width() * this.mBounds.height());
        return percentageDiffPixels < PIXEL_DIFF_PERCENTAGE_THRESHOLD;
    }

    public synchronized float getScale(Drawable d, RectF outBounds, Path path, boolean[] outMaskShape) {
        if (BaseIconFactory.ATLEAST_OREO && (d instanceof AdaptiveIconDrawable)) {
            if (this.mAdaptiveIconScale == 0.0f) {
                this.mAdaptiveIconScale = normalizeAdaptiveIcon(d, this.mMaxSize, this.mAdaptiveIconBounds);
            }
            if (outBounds != null) {
                outBounds.set(this.mAdaptiveIconBounds);
            }
            return this.mAdaptiveIconScale;
        }
        int width = d.getIntrinsicWidth();
        int height = d.getIntrinsicHeight();
        if (width <= 0 || height <= 0) {
            width = (width <= 0 || width > this.mMaxSize) ? this.mMaxSize : width;
            height = (height <= 0 || height > this.mMaxSize) ? this.mMaxSize : height;
        } else if (width > this.mMaxSize || height > this.mMaxSize) {
            int max = Math.max(width, height);
            width = (this.mMaxSize * width) / max;
            height = (this.mMaxSize * height) / max;
        }
        this.mBitmap.eraseColor(0);
        d.setBounds(0, 0, width, height);
        d.draw(this.mCanvas);
        ByteBuffer buffer = ByteBuffer.wrap(this.mPixels);
        buffer.rewind();
        this.mBitmap.copyPixelsToBuffer(buffer);
        int topY = -1;
        int bottomY = -1;
        int leftX = this.mMaxSize + 1;
        int rightX = -1;
        int index = 0;
        int rowSizeDiff = this.mMaxSize - width;
        int y = 0;
        while (true) {
            int firstX = -1;
            if (y >= height) {
                break;
            }
            int lastX = -1;
            int x = 0;
            while (x < width) {
                ByteBuffer buffer2 = buffer;
                if ((this.mPixels[index] & UByte.MAX_VALUE) > 40) {
                    if (firstX == -1) {
                        firstX = x;
                    }
                    lastX = x;
                }
                index++;
                x++;
                buffer = buffer2;
            }
            ByteBuffer buffer3 = buffer;
            index += rowSizeDiff;
            this.mLeftBorder[y] = firstX;
            this.mRightBorder[y] = lastX;
            if (firstX != -1) {
                int bottomY2 = y;
                if (topY == -1) {
                    topY = y;
                }
                leftX = Math.min(leftX, firstX);
                bottomY = bottomY2;
                rightX = Math.max(rightX, lastX);
            }
            y++;
            buffer = buffer3;
        }
        if (topY == -1 || rightX == -1) {
            return 1.0f;
        }
        convertToConvexArray(this.mLeftBorder, 1, topY, bottomY);
        convertToConvexArray(this.mRightBorder, -1, topY, bottomY);
        float area = 0.0f;
        for (int y2 = 0; y2 < height; y2++) {
            if (this.mLeftBorder[y2] > -1.0f) {
                area += (this.mRightBorder[y2] - this.mLeftBorder[y2]) + 1.0f;
            }
        }
        this.mBounds.left = leftX;
        this.mBounds.right = rightX;
        this.mBounds.top = topY;
        this.mBounds.bottom = bottomY;
        if (outBounds != null) {
            outBounds.set(this.mBounds.left / width, this.mBounds.top / height, 1.0f - (this.mBounds.right / width), 1.0f - (this.mBounds.bottom / height));
        }
        if (outMaskShape != null && this.mEnableShapeDetection && outMaskShape.length > 0) {
            outMaskShape[0] = isShape(path);
        }
        float rectArea = ((bottomY + 1) - topY) * ((rightX + 1) - leftX);
        return getScale(area, rectArea, width * height);
    }

    private static void convertToConvexArray(float[] xCoordinates, int direction, int topY, int bottomY) {
        int start;
        int total = xCoordinates.length;
        float[] angles = new float[total - 1];
        int last = -1;
        float lastAngle = Float.MAX_VALUE;
        for (int i = topY + 1; i <= bottomY; i++) {
            if (xCoordinates[i] > -1.0f) {
                if (lastAngle == Float.MAX_VALUE) {
                    start = topY;
                } else {
                    float currentAngle = (xCoordinates[i] - xCoordinates[last]) / (i - last);
                    int start2 = last;
                    if ((currentAngle - lastAngle) * direction >= 0.0f) {
                        start = start2;
                    } else {
                        start = start2;
                        while (start > topY) {
                            start--;
                            float currentAngle2 = (xCoordinates[i] - xCoordinates[start]) / (i - start);
                            if ((currentAngle2 - angles[start]) * direction >= 0.0f) {
                                break;
                            }
                        }
                    }
                }
                float lastAngle2 = (xCoordinates[i] - xCoordinates[start]) / (i - start);
                for (int j = start; j < i; j++) {
                    angles[j] = lastAngle2;
                    xCoordinates[j] = xCoordinates[start] + ((j - start) * lastAngle2);
                }
                last = i;
                lastAngle = lastAngle2;
            }
        }
    }

    public static int getNormalizedCircleSize(int size) {
        float area = size * size * MAX_CIRCLE_AREA_FACTOR;
        return (int) Math.round(Math.sqrt(((double) (4.0f * area)) / 3.141592653589793d));
    }
}
