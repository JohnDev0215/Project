package com.amazon.mp3.playback.view.lyrics;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.Branding;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class TextViewMarkerDecorator {
    private static Locale sLocale;
    private static Drawable sLyricsCheckPDrawable;
    private static Drawable sLyricsDrawable;
    private boolean mHasLyrics;
    private boolean mIsPrime;
    private TextView mTextView;

    public TextViewMarkerDecorator(TextView textView) {
        this.mTextView = textView;
    }

    public TextViewMarkerDecorator showPrimeMarker(boolean isPrime) {
        this.mIsPrime = isPrime;
        return this;
    }

    public TextViewMarkerDecorator showLyricsMarker(boolean hasLyrics) {
        this.mHasLyrics = hasLyrics;
        return this;
    }

    public void decorate() {
        this.mTextView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getMarkerDrawable(), (Drawable) null);
    }

    private Resources getResources() {
        return this.mTextView.getResources();
    }

    private Drawable getMarkerDrawable() {
        Locale locale = getResources().getConfiguration().locale;
        if (!locale.equals(sLocale)) {
            sLyricsCheckPDrawable = null;
            sLyricsDrawable = null;
            sLocale = locale;
        }
        boolean z = this.mHasLyrics;
        if (z && this.mIsPrime) {
            return getLyricsCheckPMarker();
        }
        if (z) {
            return getLyricsMarker();
        }
        if (this.mIsPrime) {
            return getCheckPMarker();
        }
        return null;
    }

    private Drawable getLyricsCheckPMarker() {
        if (sLyricsCheckPDrawable == null && this.mTextView != null) {
            sLyricsCheckPDrawable = createMarkerDrawable(true, Branding.shouldShowPrimeBranding());
        }
        return sLyricsCheckPDrawable;
    }

    private Drawable getCheckPMarker() {
        return getResources().getDrawable(R.drawable.prime_badge_check_p);
    }

    private Drawable getLyricsMarker() {
        if (sLyricsDrawable == null) {
            sLyricsDrawable = createMarkerDrawable(true, false);
        }
        return sLyricsDrawable;
    }

    private Drawable createMarkerDrawable(boolean hasLyrics, boolean isPrime) {
        Resources resources = getResources();
        int dimension = (int) resources.getDimension(R.dimen.lyrics_badge_border_width);
        if (!hasLyrics) {
            if (isPrime) {
                return resources.getDrawable(R.drawable.prime_badge_check_p);
            }
            return null;
        }
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(resources, R.drawable.prime_badge_check_p);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(Typeface.DEFAULT);
        paint.setAntiAlias(true);
        paint.setTextSize(resources.getDimension(R.dimen.lyrics_badge_text_size));
        paint.setColor(resources.getColor(R.color.lyrics_badge_text_color));
        float f = dimension;
        paint.setStrokeWidth(f);
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        paint2.setColor(resources.getColor(R.color.lyrics_badge_border_color));
        paint2.setStrokeWidth(f);
        int dimension2 = (int) resources.getDimension(R.dimen.tiny_padding);
        int iMeasureText = ((int) paint.measureText(resources.getString(R.string.dmusic_lyrics_listview_badge))) + (dimension2 * 2);
        int height = bitmapDecodeResource.getHeight();
        int i = iMeasureText - (dimension * 2);
        if (isPrime) {
            iMeasureText = iMeasureText + bitmapDecodeResource.getWidth() + ((int) resources.getDimension(R.dimen.tiny_padding));
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMeasureText, bitmapDecodeResource.getHeight() + 5, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawText(resources.getString(R.string.dmusic_lyrics_listview_badge), dimension2, (bitmapCreateBitmap.getHeight() * 0.7f) + 0.5f, paint);
        canvas.drawRect(1.0f, 4.0f, i, height + 4, paint2);
        if (isPrime) {
            canvas.drawBitmap(bitmapDecodeResource, bitmapCreateBitmap.getWidth() - bitmapDecodeResource.getWidth(), 4.0f, (Paint) null);
        }
        return new BitmapDrawable(resources, bitmapCreateBitmap);
    }
}
