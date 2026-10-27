package android.media;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Size;
import com.android.internal.util.ArrayUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.ToIntFunction;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ThumbnailUtils {
    private static final int OPTIONS_NONE = 0;
    public static final int OPTIONS_RECYCLE_INPUT = 2;
    private static final int OPTIONS_SCALE_UP = 1;
    private static final String TAG = "ThumbnailUtils";

    @Deprecated
    public static final int TARGET_SIZE_MICRO_THUMBNAIL = 96;

    @Deprecated
    private static int computeInitialSampleSize(BitmapFactory.Options options, int i, int i2) {
        return 1;
    }

    @Deprecated
    private static int computeSampleSize(BitmapFactory.Options options, int i, int i2) {
        return 1;
    }

    @Deprecated
    private static void createThumbnailFromEXIF(String str, int i, int i2, SizedThumbnailBitmap sizedThumbnailBitmap) {
    }

    private static Size convertKind(int i) {
        return MediaStore.Images.Thumbnails.getKindSize(i);
    }

    private static class Resizer implements ImageDecoder.OnHeaderDecodedListener {
        private final CancellationSignal signal;
        private final Size size;

        public Resizer(Size size, CancellationSignal cancellationSignal) {
            this.size = size;
            this.signal = cancellationSignal;
        }

        @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
        public void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
            CancellationSignal cancellationSignal = this.signal;
            if (cancellationSignal != null) {
                cancellationSignal.throwIfCanceled();
            }
            imageDecoder.setAllocator(1);
            int iMax = Math.max(imageInfo.getSize().getWidth() / this.size.getWidth(), imageInfo.getSize().getHeight() / this.size.getHeight());
            if (iMax > 1) {
                imageDecoder.setTargetSampleSize(iMax);
            }
        }
    }

    @Deprecated
    public static Bitmap createAudioThumbnail(String str, int i) {
        try {
            return createAudioThumbnail(new File(str), convertKind(i), null);
        } catch (IOException e) {
            Log.w(TAG, e);
            return null;
        }
    }

    public static Bitmap createAudioThumbnail(File file, Size size, CancellationSignal cancellationSignal) throws IOException {
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        Resizer resizer = new Resizer(size, cancellationSignal);
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
                byte[] embeddedPicture = mediaMetadataRetriever.getEmbeddedPicture();
                if (embeddedPicture != null) {
                    Bitmap bitmapDecodeBitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(embeddedPicture), resizer);
                    mediaMetadataRetriever.close();
                    return bitmapDecodeBitmap;
                }
                mediaMetadataRetriever.close();
                if ("unknown".equals(Environment.getExternalStorageState(file))) {
                    throw new IOException("No embedded album art found");
                }
                File parentFile = file.getParentFile();
                File parentFile2 = parentFile != null ? parentFile.getParentFile() : null;
                if (parentFile != null && parentFile.getName().equals(Environment.DIRECTORY_DOWNLOADS)) {
                    throw new IOException("No thumbnails in Downloads directories");
                }
                if (parentFile2 != null && "unknown".equals(Environment.getExternalStorageState(parentFile2))) {
                    throw new IOException("No thumbnails in top-level directories");
                }
                File[] fileArrDefeatNullable = ArrayUtils.defeatNullable(file.getParentFile().listFiles(new FilenameFilter() { // from class: android.media.-$$Lambda$ThumbnailUtils$FxFit0dcFzrr0x6KRz3szpF8gWk
                    @Override // java.io.FilenameFilter
                    public final boolean accept(File file2, String str) {
                        return ThumbnailUtils.lambda$createAudioThumbnail$0(file2, str);
                    }
                }));
                final $$Lambda$ThumbnailUtils$sAi8tdonBrprprnsxc4Hm_0TDI __lambda_thumbnailutils_sai8tdonbrprprnsxc4hm_0tdi = new ToIntFunction() { // from class: android.media.-$$Lambda$ThumbnailUtils$sAi8tdo-nBrprprnsxc4Hm_0TDI
                    @Override // java.util.function.ToIntFunction
                    public final int applyAsInt(Object obj) {
                        return ThumbnailUtils.lambda$createAudioThumbnail$1((File) obj);
                    }
                };
                File file2 = (File) Arrays.asList(fileArrDefeatNullable).stream().max(new Comparator() { // from class: android.media.-$$Lambda$ThumbnailUtils$YejekYA5B6Clz103k6bHCaYMxl0
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return ThumbnailUtils.lambda$createAudioThumbnail$2(__lambda_thumbnailutils_sai8tdonbrprprnsxc4hm_0tdi, (File) obj, (File) obj2);
                    }
                }).orElse(null);
                if (file2 == null) {
                    throw new IOException("No album art found");
                }
                if (cancellationSignal != null) {
                    cancellationSignal.throwIfCanceled();
                }
                return ImageDecoder.decodeBitmap(ImageDecoder.createSource(file2), resizer);
            } finally {
            }
        } catch (RuntimeException e) {
            throw new IOException("Failed to create thumbnail", e);
        }
        throw new IOException("Failed to create thumbnail", e);
    }

    static /* synthetic */ boolean lambda$createAudioThumbnail$0(File file, String str) {
        String lowerCase = str.toLowerCase();
        return lowerCase.endsWith(".jpg") || lowerCase.endsWith(".png");
    }

    static /* synthetic */ int lambda$createAudioThumbnail$1(File file) {
        String lowerCase = file.getName().toLowerCase();
        if (lowerCase.equals("albumart.jpg")) {
            return 4;
        }
        if (lowerCase.startsWith("albumart") && lowerCase.endsWith(".jpg")) {
            return 3;
        }
        if (lowerCase.contains("albumart") && lowerCase.endsWith(".jpg")) {
            return 2;
        }
        return lowerCase.endsWith(".jpg") ? 1 : 0;
    }

    static /* synthetic */ int lambda$createAudioThumbnail$2(ToIntFunction toIntFunction, File file, File file2) {
        return toIntFunction.applyAsInt(file) - toIntFunction.applyAsInt(file2);
    }

    @Deprecated
    public static Bitmap createImageThumbnail(String str, int i) {
        try {
            return createImageThumbnail(new File(str), convertKind(i), null);
        } catch (IOException e) {
            Log.w(TAG, e);
            return null;
        }
    }

    public static Bitmap createImageThumbnail(File file, Size size, CancellationSignal cancellationSignal) throws IOException {
        ExifInterface exifInterface;
        byte[] thumbnailBytes;
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        Resizer resizer = new Resizer(size, cancellationSignal);
        String mimeTypeForFile = MediaFile.getMimeTypeForFile(file.getName());
        int i = 0;
        Bitmap thumbnailImageAtIndex = null;
        if (MediaFile.isExifMimeType(mimeTypeForFile)) {
            exifInterface = new ExifInterface(file);
            int attributeInt = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
            if (attributeInt == 3) {
                i = 180;
            } else if (attributeInt == 6) {
                i = 90;
            } else if (attributeInt == 8) {
                i = 270;
            }
        } else {
            exifInterface = null;
        }
        if (mimeTypeForFile.equals("image/heif") || mimeTypeForFile.equals("image/heif-sequence") || mimeTypeForFile.equals("image/heic") || mimeTypeForFile.equals("image/heic-sequence")) {
            try {
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                try {
                    mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
                    thumbnailImageAtIndex = mediaMetadataRetriever.getThumbnailImageAtIndex(-1, new MediaMetadataRetriever.BitmapParams(), size.getWidth(), size.getWidth() * size.getHeight());
                    mediaMetadataRetriever.close();
                } finally {
                }
            } catch (RuntimeException e) {
                throw new IOException("Failed to create thumbnail", e);
            }
        }
        if (thumbnailImageAtIndex == null && exifInterface != null && (thumbnailBytes = exifInterface.getThumbnailBytes()) != null) {
            try {
                thumbnailImageAtIndex = ImageDecoder.decodeBitmap(ImageDecoder.createSource(thumbnailBytes), resizer);
            } catch (ImageDecoder.DecodeException e2) {
                Log.w(TAG, e2);
            }
        }
        Bitmap bitmap = thumbnailImageAtIndex;
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        if (bitmap == null) {
            return ImageDecoder.decodeBitmap(ImageDecoder.createSource(file), resizer);
        }
        if (i == 0 || bitmap == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(i, width / 2, height / 2);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }

    @Deprecated
    public static Bitmap createVideoThumbnail(String str, int i) {
        try {
            return createVideoThumbnail(new File(str), convertKind(i), null);
        } catch (IOException e) {
            Log.w(TAG, e);
            return null;
        }
    }

    public static Bitmap createVideoThumbnail(File file, Size size, CancellationSignal cancellationSignal) throws IOException {
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        Resizer resizer = new Resizer(size, cancellationSignal);
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
                byte[] embeddedPicture = mediaMetadataRetriever.getEmbeddedPicture();
                if (embeddedPicture != null) {
                    Bitmap bitmapDecodeBitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(embeddedPicture), resizer);
                    mediaMetadataRetriever.close();
                    return bitmapDecodeBitmap;
                }
                MediaMetadataRetriever.BitmapParams bitmapParams = new MediaMetadataRetriever.BitmapParams();
                bitmapParams.setPreferredConfig(Bitmap.Config.ARGB_8888);
                int i = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                int i2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                long j = (Long.parseLong(mediaMetadataRetriever.extractMetadata(9)) * 1000) / 2;
                if (size.getWidth() > i && size.getHeight() > i2) {
                    Bitmap bitmap = (Bitmap) Objects.requireNonNull(mediaMetadataRetriever.getFrameAtTime(j, 2, bitmapParams));
                    mediaMetadataRetriever.close();
                    return bitmap;
                }
                Bitmap bitmap2 = (Bitmap) Objects.requireNonNull(mediaMetadataRetriever.getScaledFrameAtTime(j, 2, size.getWidth(), size.getHeight(), bitmapParams));
                mediaMetadataRetriever.close();
                return bitmap2;
            } finally {
            }
        } catch (RuntimeException e) {
            throw new IOException("Failed to create thumbnail", e);
        }
    }

    public static Bitmap extractThumbnail(Bitmap bitmap, int i, int i2) {
        return extractThumbnail(bitmap, i, i2, 0);
    }

    public static Bitmap extractThumbnail(Bitmap bitmap, int i, int i2, int i3) {
        float f;
        int height;
        if (bitmap == null) {
            return null;
        }
        if (bitmap.getWidth() < bitmap.getHeight()) {
            f = i;
            height = bitmap.getWidth();
        } else {
            f = i2;
            height = bitmap.getHeight();
        }
        float f2 = f / height;
        Matrix matrix = new Matrix();
        matrix.setScale(f2, f2);
        return transform(matrix, bitmap, i, i2, i3 | 1);
    }

    @Deprecated
    private static void closeSilently(ParcelFileDescriptor parcelFileDescriptor) {
        IoUtils.closeQuietly(parcelFileDescriptor);
    }

    @Deprecated
    private static ParcelFileDescriptor makeInputStream(Uri uri, ContentResolver contentResolver) {
        try {
            return contentResolver.openFileDescriptor(uri, "r");
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c4  */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Bitmap transform(android.graphics.Matrix r14, android.graphics.Bitmap r15, int r16, int r17, int r18) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.ThumbnailUtils.transform(android.graphics.Matrix, android.graphics.Bitmap, int, int, int):android.graphics.Bitmap");
    }

    @Deprecated
    private static class SizedThumbnailBitmap {
        public Bitmap mBitmap;
        public byte[] mThumbnailData;
        public int mThumbnailHeight;
        public int mThumbnailWidth;

        private SizedThumbnailBitmap() {
        }
    }
}
