package com.amazon.mp3.util;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextPaint;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageBlur;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class CompositeImageGenerator {
    private static CacheManager sCacheManager;
    private final int mBarLocation;
    private final float mCompositeFontSizeMax;
    private final float mCompositeFontSizeMin;
    private final float mLargeTextFontSize;
    private final float mSmallTextFontSize;
    private final float mTextAreaHeight;
    private final float mTextAreaWidth;
    private final int mTextOpacity;

    private static class DrawableResult {
        private Drawable mDrawable;
        private boolean mIsDefault;

        public DrawableResult(Drawable d, boolean isDefault) {
            this.mDrawable = d;
            this.mIsDefault = isDefault;
        }

        public boolean isDefault() {
            return this.mIsDefault;
        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }
    }

    private static CacheManager getCacheManager() {
        if (sCacheManager == null) {
            sCacheManager = CacheManager.getInstance();
        }
        return sCacheManager;
    }

    public CompositeImageGenerator(float textHeight, float textWidth, float largeFontSize, float smallFontSize, float fontSizeMin, float fontSizeMax, int opacity, int barLocation) {
        this.mTextAreaHeight = textHeight;
        this.mTextAreaWidth = textWidth;
        this.mLargeTextFontSize = largeFontSize;
        this.mSmallTextFontSize = smallFontSize;
        this.mCompositeFontSizeMin = fontSizeMin;
        this.mCompositeFontSizeMax = fontSizeMax;
        this.mTextOpacity = opacity;
        this.mBarLocation = barLocation;
    }

    public DrawableResult getAlbumArt(int artSize, long albumid, boolean bAllowLoading) {
        Drawable drawable;
        String string = Long.toString(albumid);
        CacheManager cacheManager = getCacheManager();
        ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.ALBUM;
        boolean zHasOnDisk = true;
        if (cacheManager.contains(itemType, artSize, string)) {
            drawable = getCacheManager().get(itemType, artSize, string);
            zHasOnDisk = false;
        } else if (bAllowLoading || getCacheManager().hasOnDisk(itemType, artSize, string)) {
            Drawable onCurrentThread = getCacheManager().getOnCurrentThread(itemType, artSize, string, false);
            zHasOnDisk = true ^ getCacheManager().hasOnDisk(itemType, artSize, string);
            drawable = onCurrentThread;
        } else {
            drawable = getCacheManager().get(itemType, artSize, string);
        }
        return new DrawableResult(drawable, zHasOnDisk);
    }

    private Cursor getAlbumIds(Uri targetUri) {
        int iMatch = DefaultUriMatcher.match(targetUri);
        if (iMatch != 1) {
            if (iMatch != 8 && iMatch != 13 && iMatch != 14 && iMatch != 24 && iMatch != 25) {
                return null;
            }
            targetUri = targetUri.buildUpon().appendPath("tracks").build();
        }
        return MediaProvider.getInstance().query(targetUri, new String[]{"album_id", "album_art_id"}, null, null, null);
    }

    private void fillBitmaps(int nArtSize, Uri targetUri, ArrayList<Bitmap> bitmaps) {
        Cursor albumIds = getAlbumIds(targetUri);
        if (albumIds != null) {
            try {
                if (albumIds.moveToFirst()) {
                    int columnIndex = albumIds.getColumnIndex("album_id");
                    HashSet hashSet = new HashSet();
                    do {
                        long j = albumIds.getLong(columnIndex);
                        if (!hashSet.contains(Long.valueOf(j))) {
                            hashSet.add(Long.valueOf(j));
                            DrawableResult albumArt = getAlbumArt(nArtSize, j, true);
                            if (!albumArt.isDefault()) {
                                Drawable drawable = albumArt.getDrawable();
                                Bitmap bitmap = null;
                                if (drawable instanceof FastBitmapDrawable) {
                                    bitmap = ((FastBitmapDrawable) drawable).getBitmap();
                                } else if (drawable instanceof BitmapDrawable) {
                                    bitmap = ((BitmapDrawable) drawable).getBitmap();
                                }
                                if (bitmap != null) {
                                    bitmaps.add(bitmap);
                                }
                            }
                        }
                        if (bitmaps.size() >= 4) {
                            break;
                        }
                    } while (albumIds.moveToNext());
                }
            } finally {
                DbUtil.closeCursor(albumIds);
            }
        }
    }

    private Bitmap[] getAlbumArts(int nArtSize, Uri targetUri) {
        ArrayList<Bitmap> arrayList = new ArrayList<>();
        fillBitmaps(nArtSize, targetUri, arrayList);
        if (arrayList.size() == 4) {
            return (Bitmap[]) arrayList.toArray(new Bitmap[arrayList.size()]);
        }
        if (arrayList.size() <= 0) {
            return new Bitmap[0];
        }
        Bitmap[] bitmapArr = new Bitmap[4];
        Iterator<Bitmap> it = arrayList.iterator();
        int[] iArr = {0, 3, 1, 2};
        for (int i = 0; i < 4; i++) {
            int i2 = iArr[i];
            if (!it.hasNext()) {
                break;
            }
            bitmapArr[i2] = it.next();
        }
        return bitmapArr;
    }

    public Bitmap getCompositeBitmap(int nIconSize, Uri targetUri) {
        Bitmap bitmap;
        Bitmap[] albumArts = getAlbumArts(nIconSize / 2, targetUri);
        if (albumArts.length == 0) {
            return null;
        }
        int i = 0;
        boolean z = false;
        for (Bitmap bitmap2 : albumArts) {
            if (bitmap2 == null) {
                z = true;
            }
        }
        int iSqrt = (int) Math.sqrt(albumArts.length);
        int i2 = nIconSize / iSqrt;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(nIconSize, nIconSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        if (z && (bitmap = albumArts[0]) != null) {
            canvas.drawBitmap(ImageBlur.generateBoxBlurredBitmap(bitmap, 4, 3), (Rect) null, new Rect(0, 0, nIconSize, nIconSize), (Paint) null);
            canvas.drawARGB(75, 0, 0, 0);
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < iSqrt) {
            int i5 = i;
            while (i5 < iSqrt) {
                int i6 = i4 + 1;
                Bitmap bitmap3 = albumArts[i4];
                if (bitmap3 != null) {
                    canvas.drawBitmap(bitmap3, (Rect) null, new Rect(i5 * i2, i3 * i2, (i5 + 1) * i2, (i3 + 1) * i2), (Paint) null);
                }
                i5++;
                i4 = i6;
            }
            i3++;
            i = 0;
        }
        return bitmapCreateBitmap;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Bitmap paintMetadataOnBitmap(android.graphics.Bitmap r17, android.net.Uri r18, int r19) {
        /*
            r16 = this;
            int r0 = com.amazon.mp3.library.provider.DefaultUriMatcher.match(r18)
            r1 = 2
            r2 = 0
            java.lang.String r3 = "artist"
            r4 = 25
            r5 = 8
            r6 = 4
            r7 = 0
            java.lang.String r8 = "title"
            r9 = 1
            if (r0 == r1) goto L47
            if (r0 == r6) goto L41
            java.lang.String r1 = "name"
            if (r0 == r5) goto L3c
            if (r0 == r4) goto L37
            r3 = 28
            if (r0 == r3) goto L32
            r3 = 33
            if (r0 == r3) goto L32
            r3 = 13
            if (r0 == r3) goto L2d
            r3 = 14
            if (r0 == r3) goto L2d
            return r2
        L2d:
            java.lang.String[] r1 = new java.lang.String[]{r1}
            goto L45
        L32:
            java.lang.String[] r1 = new java.lang.String[]{r8}
            goto L45
        L37:
            java.lang.String[] r1 = new java.lang.String[]{r1}
            goto L45
        L3c:
            java.lang.String[] r1 = new java.lang.String[]{r1}
            goto L45
        L41:
            java.lang.String[] r1 = new java.lang.String[]{r8, r3}
        L45:
            r3 = r9
            goto L4e
        L47:
            java.lang.String r1 = "album"
            java.lang.String[] r1 = new java.lang.String[]{r3, r8, r1}
            r3 = r7
        L4e:
            com.amazon.mp3.library.provider.MediaProvider r10 = com.amazon.mp3.library.provider.MediaProvider.getInstance()
            r13 = 0
            r14 = 0
            r15 = 0
            r11 = r18
            r12 = r1
            android.database.Cursor r8 = r10.query(r11, r12, r13, r14, r15)
            if (r8 == 0) goto Lbd
            boolean r10 = r8.moveToFirst()     // Catch: java.lang.Throwable -> Lb8
            if (r10 == 0) goto Lbd
            r7 = r1[r7]     // Catch: java.lang.Throwable -> Lb8
            int r7 = r8.getColumnIndex(r7)     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r7 = r8.getString(r7)     // Catch: java.lang.Throwable -> Lb8
            int r10 = r1.length     // Catch: java.lang.Throwable -> Lb8
            if (r10 <= r9) goto L7b
            r1 = r1[r9]     // Catch: java.lang.Throwable -> Lb8
            int r1 = r8.getColumnIndex(r1)     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r2 = r8.getString(r1)     // Catch: java.lang.Throwable -> Lb8
        L7b:
            r15 = r2
            if (r0 == r6) goto L9d
            if (r0 == r5) goto L90
            if (r0 == r4) goto L83
            goto Lab
        L83:
            com.amazon.mp3.util.DefaultStringType r0 = com.amazon.mp3.util.DefaultStringType.GENRE     // Catch: java.lang.Throwable -> Lb8
            boolean r1 = r0.isPlatformOrCirrusDefault(r7)     // Catch: java.lang.Throwable -> Lb8
            if (r1 == 0) goto Lab
            java.lang.String r0 = r0.getDefault()     // Catch: java.lang.Throwable -> Lb8
            goto La9
        L90:
            com.amazon.mp3.util.DefaultStringType r0 = com.amazon.mp3.util.DefaultStringType.ARTIST     // Catch: java.lang.Throwable -> Lb8
            boolean r1 = r0.isPlatformOrCirrusDefault(r7)     // Catch: java.lang.Throwable -> Lb8
            if (r1 == 0) goto Lab
            java.lang.String r0 = r0.getDefault()     // Catch: java.lang.Throwable -> Lb8
            goto La9
        L9d:
            com.amazon.mp3.util.DefaultStringType r0 = com.amazon.mp3.util.DefaultStringType.ALBUM     // Catch: java.lang.Throwable -> Lb8
            boolean r1 = r0.isPlatformOrCirrusDefault(r7)     // Catch: java.lang.Throwable -> Lb8
            if (r1 == 0) goto Lab
            java.lang.String r0 = r0.getDefault()     // Catch: java.lang.Throwable -> Lb8
        La9:
            r14 = r0
            goto Lac
        Lab:
            r14 = r7
        Lac:
            r10 = r16
            r11 = r17
            r12 = r3
            r13 = r19
            android.graphics.Bitmap r0 = r10.paintMetadataOnBitmapInternal(r11, r12, r13, r14, r15)     // Catch: java.lang.Throwable -> Lb8
            goto Lbf
        Lb8:
            r0 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r8)
            throw r0
        Lbd:
            r0 = r17
        Lbf:
            com.amazon.mp3.util.DbUtil.closeCursor(r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.util.CompositeImageGenerator.paintMetadataOnBitmap(android.graphics.Bitmap, android.net.Uri, int):android.graphics.Bitmap");
    }

    private Bitmap paintMetadataOnBitmapInternal(Bitmap b, boolean bComposite, int nIconSize, String largeText, String smallText) {
        if (largeText == null) {
            return b;
        }
        Bitmap bitmapCopy = b.copy(b.getConfig(), true);
        if (bitmapCopy == null) {
            return null;
        }
        WrappedTextCanvas wrappedTextCanvas = new WrappedTextCanvas(bitmapCopy);
        if (AmazonApplication.getCapabilities().useOldStyleCarouselBadging()) {
            TextPaint textPaint = new TextPaint();
            textPaint.setARGB(230, 255, 255, 255);
            textPaint.setTypeface(Typeface.create("helvetica lt 57", 1));
            textPaint.setFlags(1);
            if (bComposite) {
                float f = nIconSize;
                float f2 = f / 500.0f;
                wrappedTextCanvas.drawWrappedText(largeText, this.mBarLocation, (int) (this.mTextAreaHeight * f), (int) (f * this.mTextAreaWidth), this.mCompositeFontSizeMin * f2, this.mCompositeFontSizeMax * f2, textPaint, 2, this.mTextOpacity, 0);
            } else {
                float f3 = nIconSize;
                float f4 = f3 / 400.0f;
                float f5 = f4 * 70.0f;
                float f6 = this.mTextAreaWidth;
                int i = (int) (f3 * f6);
                int i2 = (int) (f6 * f3);
                float f7 = this.mLargeTextFontSize;
                float fDrawWrappedText = f5 + wrappedTextCanvas.drawWrappedText(largeText, (int) f5, i, i2, f7 * f4, f7 * f4, textPaint, 2, 0, 0);
                if (smallText != null) {
                    float f8 = this.mTextAreaWidth;
                    float f9 = this.mSmallTextFontSize;
                    wrappedTextCanvas.drawWrappedText(smallText, (int) (fDrawWrappedText + (36.0f * f4)), (int) (f3 * f8), (int) (f3 * f8), f9 * f4, f9 * f4, textPaint, 2, 0, 0);
                }
            }
        } else {
            float f10 = nIconSize / (CacheManager.ALBUM_ART_THUMBNAIL_SIZE * 3);
            int dimensionPixelSize = (int) (AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.carousel_image_padding) * f10);
            int dimensionPixelSize2 = (int) (f10 * AmazonApplication.getContext().getResources().getDimensionPixelSize(getFontSizeIdByIconSize(nIconSize)));
            int i3 = dimensionPixelSize * 2;
            TextPaint textPaint2 = new TextPaint();
            textPaint2.setARGB(230, 255, 255, 255);
            float f11 = dimensionPixelSize2;
            textPaint2.setTextSize(f11);
            textPaint2.setAntiAlias(true);
            wrappedTextCanvas.setMaxLines(2);
            wrappedTextCanvas.drawWrappedText(largeText, this.mBarLocation, i3 + (dimensionPixelSize2 * 2), nIconSize - i3, f11, f11, textPaint2, 0, this.mTextOpacity, dimensionPixelSize);
        }
        return bitmapCopy;
    }

    private int getFontSizeIdByIconSize(int nIconSize) {
        return nIconSize == CacheManager.ALBUM_ART_THUMBNAIL_SIZE ? R.dimen.carousel_image_font_size_large : R.dimen.carousel_image_font_size;
    }
}
