package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes4.dex */
public class TextSashImageView extends ImageView {
    private static float sTextSize;
    private int mSideLength;
    private String mText;

    public TextSashImageView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        this.mSideLength = getWidth();
        displayTextSash(this.mText);
    }

    public void setText(String text) {
        this.mText = text;
    }

    private void displayTextSash(String text) {
        int i = this.mSideLength;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_4444);
        bitmapCreateBitmap.eraseColor(0);
        drawSash(new Canvas(bitmapCreateBitmap), text);
        setImageDrawable(new BitmapDrawable(getResources(), bitmapCreateBitmap));
    }

    private void drawSash(Canvas canvas, String text) {
        canvas.rotate(-45.0f);
        canvas.translate((float) computeXValueForTranslation(), (float) computeYValueForTranslation());
        drawRectangleCenteredInAlbumArt(canvas);
        drawNewTextCenteredInRectangle(canvas, text);
    }

    private double computeXValueForTranslation() {
        return ((((double) (this.mSideLength * 0.07574999f)) * Math.cos(-0.7853981633974483d)) - (((double) (this.mSideLength * 0.60605f)) * Math.sin(-0.7853981633974483d))) * (-1.0d);
    }

    private double computeYValueForTranslation() {
        return (((double) (this.mSideLength * 0.92425f)) * Math.sin(-0.7853981633974483d)) + (((double) (this.mSideLength * 0.60605f)) * Math.cos(-0.7853981633974483d));
    }

    private void drawRectangleCenteredInAlbumArt(Canvas canvas) {
        int i = this.mSideLength;
        RectF rectF = new RectF(i * 0.07574999f, i * 0.39395f, i * 0.92425f, i * 0.60605f);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.prime_blue));
        canvas.drawRect(rectF, paint);
    }

    private void drawNewTextCenteredInRectangle(Canvas canvas, String text) {
        Paint paint = new Paint();
        paint.setTextSize(determineTextSize());
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, 1));
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.white));
        int i = this.mSideLength;
        canvas.drawText(text, i * 0.5f, (i / 2.0f) - ((paint.descent() + paint.ascent()) / 2.0f), paint);
    }

    private float determineTextSize() {
        if (sTextSize == 0.0f) {
            sTextSize = (this.mSideLength * 0.2121f) / 1.25f;
        }
        return sTextSize;
    }
}
