package com.android.providers.contacts;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.sysprop.ContactsProperties;
import com.android.providers.contacts.util.MemoryUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class PhotoProcessor {
    private static final Paint WHITE_PAINT;
    private static int sMaxDisplayPhotoDim;
    private static int sMaxThumbnailDim;
    private Bitmap mDisplayPhoto;
    private final boolean mForceCropToSquare;
    private final int mMaxDisplayPhotoDim;
    private final int mMaxThumbnailPhotoDim;
    private final Bitmap mOriginal;
    private Bitmap mThumbnailPhoto;

    static {
        Paint paint = new Paint();
        WHITE_PAINT = paint;
        paint.setColor(-1);
        boolean z = MemoryUtils.getTotalMemorySize() >= 671088640;
        sMaxThumbnailDim = ((Integer) ContactsProperties.thumbnail_size().orElse(96)).intValue();
        sMaxDisplayPhotoDim = ((Integer) ContactsProperties.display_photo_size().orElse(Integer.valueOf(z ? 720 : 480))).intValue();
    }

    public PhotoProcessor(Bitmap bitmap, int i, int i2) throws IOException {
        this(bitmap, i, i2, false);
    }

    public PhotoProcessor(byte[] bArr, int i, int i2) throws IOException {
        this(BitmapFactory.decodeByteArray(bArr, 0, bArr.length), i, i2, false);
    }

    public PhotoProcessor(Bitmap bitmap, int i, int i2, boolean z) throws IOException {
        this.mOriginal = bitmap;
        this.mMaxDisplayPhotoDim = i;
        this.mMaxThumbnailPhotoDim = i2;
        this.mForceCropToSquare = z;
        process();
    }

    public PhotoProcessor(byte[] bArr, int i, int i2, boolean z) throws IOException {
        this(BitmapFactory.decodeByteArray(bArr, 0, bArr.length), i, i2, z);
    }

    private void process() throws IOException {
        Bitmap bitmap = this.mOriginal;
        if (bitmap == null) {
            throw new IOException("Invalid image file");
        }
        this.mDisplayPhoto = getNormalizedBitmap(bitmap, this.mMaxDisplayPhotoDim, this.mForceCropToSquare);
        this.mThumbnailPhoto = getNormalizedBitmap(this.mOriginal, this.mMaxThumbnailPhotoDim, this.mForceCropToSquare);
    }

    static Bitmap getNormalizedBitmap(Bitmap bitmap, int i, boolean z) throws IOException {
        int i2;
        boolean zHasAlpha = bitmap.hasAlpha();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i3 = 0;
        if (!z || width == height) {
            i2 = 0;
        } else if (height > width) {
            i2 = (height - width) / 2;
            height = width;
        } else {
            i2 = 0;
            i3 = (width - height) / 2;
            width = height;
        }
        float fMin = Math.min(1.0f, i / Math.max(width, height));
        if (fMin >= 1.0f && i3 == 0 && i2 == 0 && !zHasAlpha) {
            return bitmap;
        }
        int i4 = (int) (width * fMin);
        int i5 = (int) (height * fMin);
        if (i4 <= 0 || i5 <= 0) {
            throw new IOException("Invalid bitmap dimensions");
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i4, i5, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        if (zHasAlpha) {
            canvas.drawRect(0.0f, 0.0f, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), WHITE_PAINT);
        }
        canvas.drawBitmap(bitmap, new Rect(i3, i2, width + i3, height + i2), new RectF(0.0f, 0.0f, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight()), (Paint) null);
        return bitmapCreateBitmap;
    }

    private byte[] getCompressedBytes(Bitmap bitmap, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean zCompress = bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (zCompress) {
            return byteArray;
        }
        throw new IOException("Unable to compress image");
    }

    public Bitmap getDisplayPhoto() {
        return this.mDisplayPhoto;
    }

    public byte[] getDisplayPhotoBytes() throws IOException {
        return getCompressedBytes(this.mDisplayPhoto, 75);
    }

    public byte[] getThumbnailPhotoBytes() throws IOException {
        Bitmap bitmap = this.mDisplayPhoto;
        return getCompressedBytes(this.mThumbnailPhoto, bitmap != null && (bitmap.getWidth() > this.mThumbnailPhoto.getWidth() || this.mDisplayPhoto.getHeight() > this.mThumbnailPhoto.getHeight()) ? 90 : 95);
    }

    public int getMaxThumbnailPhotoDim() {
        return this.mMaxThumbnailPhotoDim;
    }

    public static int getMaxThumbnailSize() {
        return sMaxThumbnailDim;
    }

    public static int getMaxDisplayPhotoSize() {
        return sMaxDisplayPhotoDim;
    }
}
