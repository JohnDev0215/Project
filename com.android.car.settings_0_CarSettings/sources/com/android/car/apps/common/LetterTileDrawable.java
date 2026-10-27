package com.android.car.apps.common;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes5.dex */
public class LetterTileDrawable extends Drawable {
    public static final int TYPE_BUSINESS = 2;
    public static final int TYPE_DEFAULT = 1;
    public static final int TYPE_PERSON = 1;
    public static final int TYPE_VOICEMAIL = 3;
    private static int[] sColors;
    private static Drawable sDefaultBusinessAvatar;
    private static int sDefaultColor;
    private static Drawable sDefaultPersonAvatar;
    private static Drawable sDefaultVoicemailAvatar;
    private static float sLetterToTileRatio;
    private static final Paint sPaint = new Paint();
    private static final Rect sRect = new Rect();
    private static int sTileFontColor;
    private int mColor;
    private int mContactType;
    private boolean mIsCircle;
    private String mLetters;
    private float mOffset;
    private final Paint mPaint;
    private float mScale;

    public LetterTileDrawable(Resources res) {
        this(res, null, null);
    }

    public LetterTileDrawable(Resources res, String letters, String identifier) {
        this.mContactType = 1;
        this.mScale = 1.0f;
        this.mOffset = 0.0f;
        this.mIsCircle = false;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setFilterBitmap(true);
        this.mPaint.setDither(true);
        setScale(0.7f);
        if (sColors == null) {
            sDefaultColor = res.getColor(R.color.letter_tile_default_color, null);
            TypedArray ta = res.obtainTypedArray(R.array.letter_tile_colors);
            if (ta.length() == 0) {
                sColors = new int[]{sDefaultColor};
            } else {
                sColors = new int[ta.length()];
                for (int i = ta.length() - 1; i >= 0; i--) {
                    sColors[i] = ta.getColor(i, sDefaultColor);
                }
                ta.recycle();
            }
            sTileFontColor = res.getColor(R.color.letter_tile_font_color, null);
            sLetterToTileRatio = res.getFraction(R.fraction.letter_to_tile_ratio, 1, 1);
            sDefaultPersonAvatar = res.getDrawable(R.drawable.ic_person, null);
            sDefaultBusinessAvatar = res.getDrawable(R.drawable.ic_person, null);
            sDefaultVoicemailAvatar = res.getDrawable(R.drawable.ic_person, null);
            sPaint.setTypeface(Typeface.create(res.getString(R.string.config_letter_tile_font_family), res.getInteger(R.integer.config_letter_tile_text_style)));
            sPaint.setTextAlign(Paint.Align.CENTER);
            sPaint.setAntiAlias(true);
        }
        setContactDetails(letters, identifier);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (!isVisible() || bounds.isEmpty()) {
            return;
        }
        drawLetterTile(canvas);
    }

    private void drawDrawableOnCanvas(Drawable drawable, Canvas canvas) {
        Rect destRect = copyBounds();
        int halfLength = (int) ((this.mScale * Math.min(destRect.width(), destRect.height())) / 2.0f);
        destRect.set(destRect.centerX() - halfLength, (int) ((destRect.centerY() - halfLength) + (this.mOffset * destRect.height())), destRect.centerX() + halfLength, (int) (destRect.centerY() + halfLength + (this.mOffset * destRect.height())));
        drawable.setAlpha(this.mPaint.getAlpha());
        drawable.setColorFilter(sTileFontColor, PorterDuff.Mode.SRC_IN);
        drawable.setBounds(destRect);
        drawable.draw(canvas);
    }

    private void drawLetterTile(Canvas canvas) {
        sPaint.setColor(this.mColor);
        sPaint.setAlpha(this.mPaint.getAlpha());
        Rect bounds = getBounds();
        int minDimension = Math.min(bounds.width(), bounds.height());
        if (this.mIsCircle) {
            canvas.drawCircle(bounds.centerX(), bounds.centerY(), minDimension / 2, sPaint);
        } else {
            canvas.drawRect(bounds, sPaint);
        }
        if (!TextUtils.isEmpty(this.mLetters)) {
            sPaint.setTextSize(this.mScale * sLetterToTileRatio * minDimension);
            Paint paint = sPaint;
            String str = this.mLetters;
            paint.getTextBounds(str, 0, str.length(), sRect);
            sPaint.setColor(sTileFontColor);
            String str2 = this.mLetters;
            canvas.drawText(str2, 0, str2.length(), bounds.centerX(), bounds.centerY() + (this.mOffset * bounds.height()) + (sRect.height() / 2), sPaint);
            return;
        }
        Drawable drawable = getDrawablepForContactType(this.mContactType);
        drawDrawableOnCanvas(drawable, canvas);
    }

    public int getColor() {
        return this.mColor;
    }

    private int pickColor(String identifier) {
        if (TextUtils.isEmpty(identifier) || this.mContactType == 3) {
            int color = sDefaultColor;
            return color;
        }
        int iAbs = Math.abs(identifier.hashCode());
        int[] iArr = sColors;
        int color2 = iAbs % iArr.length;
        return iArr[color2];
    }

    private static Drawable getDrawablepForContactType(int contactType) {
        if (contactType == 1) {
            return sDefaultPersonAvatar;
        }
        if (contactType == 2) {
            return sDefaultBusinessAvatar;
        }
        if (contactType == 3) {
            return sDefaultVoicemailAvatar;
        }
        return sDefaultPersonAvatar;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -1;
    }

    public void setScale(float scale) {
        this.mScale = scale;
    }

    public void setOffset(float offset) {
        this.mOffset = offset;
    }

    public void setContactDetails(String letters, String identifier) {
        this.mLetters = letters;
        this.mColor = pickColor(identifier);
    }

    public void setContactType(int contactType) {
        this.mContactType = contactType;
    }

    public void setIsCircular(boolean isCircle) {
        this.mIsCircle = isCircle;
    }

    public Bitmap toBitmap(int size) {
        Bitmap largeIcon = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(largeIcon);
        Rect bounds = getBounds();
        setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        draw(canvas);
        setBounds(bounds);
        return largeIcon;
    }
}
