package com.byd.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.byd.auto_photo.R;

/* JADX INFO: loaded from: classes2.dex */
public class ColorPickerView extends ImageView {
    private int bitmapHeight;
    private int bitmapWidth;
    private Bitmap mBitmap;
    private OnColorPickerListener onColorPickerListener;

    public interface OnColorPickerListener {
        void onColorChange(int i);
    }

    public ColorPickerView(Context context) {
        this(context, null);
    }

    public ColorPickerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.drawable_color_picker)).getBitmap();
        this.mBitmap = bitmap;
        setImageBitmap(bitmap);
        setScaleType(ImageView.ScaleType.FIT_XY);
        this.bitmapWidth = this.mBitmap.getWidth();
        this.bitmapHeight = this.mBitmap.getHeight();
    }

    private int getSize(int i, int i2) {
        return View.MeasureSpec.getMode(i) == 1073741824 ? View.MeasureSpec.getSize(i) : i2;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(getSize(i, this.bitmapWidth), getSize(i2, this.bitmapHeight));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int pixel = ViewCompat.MEASURED_STATE_MASK;
            if (x < this.bitmapWidth && y < this.bitmapHeight) {
                pixel = this.mBitmap.getPixel(x, y);
            }
            OnColorPickerListener onColorPickerListener = this.onColorPickerListener;
            if (onColorPickerListener != null) {
                onColorPickerListener.onColorChange(pixel);
            }
        }
        return true;
    }

    public void release() {
        this.mBitmap.recycle();
    }

    public void setOnColorPickerListener(OnColorPickerListener onColorPickerListener) {
        this.onColorPickerListener = onColorPickerListener;
    }
}
