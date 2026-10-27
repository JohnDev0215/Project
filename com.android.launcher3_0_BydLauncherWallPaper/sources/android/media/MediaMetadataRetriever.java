package android.media;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetrics;
import android.net.Uri;
import android.os.IBinder;
import android.text.TextUtils;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MediaMetadataRetriever implements AutoCloseable {
    private static final int EMBEDDED_PICTURE_TYPE_ANY = 65535;
    public static final int METADATA_KEY_ALBUM = 1;
    public static final int METADATA_KEY_ALBUMARTIST = 13;
    public static final int METADATA_KEY_ARTIST = 2;
    public static final int METADATA_KEY_AUTHOR = 3;
    public static final int METADATA_KEY_BITRATE = 20;
    public static final int METADATA_KEY_BITS_PER_SAMPLE = 39;
    public static final int METADATA_KEY_CAPTURE_FRAMERATE = 25;
    public static final int METADATA_KEY_CD_TRACK_NUMBER = 0;
    public static final int METADATA_KEY_COLOR_RANGE = 37;
    public static final int METADATA_KEY_COLOR_STANDARD = 35;
    public static final int METADATA_KEY_COLOR_TRANSFER = 36;
    public static final int METADATA_KEY_COMPILATION = 15;
    public static final int METADATA_KEY_COMPOSER = 4;
    public static final int METADATA_KEY_DATE = 5;
    public static final int METADATA_KEY_DISC_NUMBER = 14;
    public static final int METADATA_KEY_DURATION = 9;
    public static final int METADATA_KEY_EXIF_LENGTH = 34;
    public static final int METADATA_KEY_EXIF_OFFSET = 33;
    public static final int METADATA_KEY_GENRE = 6;
    public static final int METADATA_KEY_HAS_AUDIO = 16;
    public static final int METADATA_KEY_HAS_IMAGE = 26;
    public static final int METADATA_KEY_HAS_VIDEO = 17;
    public static final int METADATA_KEY_IMAGE_COUNT = 27;
    public static final int METADATA_KEY_IMAGE_HEIGHT = 30;
    public static final int METADATA_KEY_IMAGE_PRIMARY = 28;
    public static final int METADATA_KEY_IMAGE_ROTATION = 31;
    public static final int METADATA_KEY_IMAGE_WIDTH = 29;
    public static final int METADATA_KEY_IS_DRM = 22;
    public static final int METADATA_KEY_LOCATION = 23;
    public static final int METADATA_KEY_MIMETYPE = 12;
    public static final int METADATA_KEY_NUM_TRACKS = 10;
    public static final int METADATA_KEY_SAMPLERATE = 38;
    public static final int METADATA_KEY_TIMED_TEXT_LANGUAGES = 21;
    public static final int METADATA_KEY_TITLE = 7;
    public static final int METADATA_KEY_VIDEO_FRAME_COUNT = 32;
    public static final int METADATA_KEY_VIDEO_HEIGHT = 19;
    public static final int METADATA_KEY_VIDEO_ROTATION = 24;
    public static final int METADATA_KEY_VIDEO_WIDTH = 18;
    public static final int METADATA_KEY_WRITER = 11;
    public static final int METADATA_KEY_YEAR = 8;
    public static final int OPTION_CLOSEST = 3;
    public static final int OPTION_CLOSEST_SYNC = 2;
    public static final int OPTION_NEXT_SYNC = 1;
    public static final int OPTION_PREVIOUS_SYNC = 0;
    private static final String[] STANDARD_GENRES = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Afro-Punk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};
    private long mNativeContext;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Option {
    }

    private native List<Bitmap> _getFrameAtIndex(int i, int i2, BitmapParams bitmapParams);

    private native Bitmap _getFrameAtTime(long j, int i, int i2, int i3, BitmapParams bitmapParams);

    private native Bitmap _getImageAtIndex(int i, BitmapParams bitmapParams);

    private native void _setDataSource(MediaDataSource mediaDataSource) throws IllegalArgumentException;

    private native void _setDataSource(IBinder iBinder, String str, String[] strArr, String[] strArr2) throws IllegalArgumentException;

    private native byte[] getEmbeddedPicture(int i);

    private native String nativeExtractMetadata(int i);

    private final native void native_finalize();

    private static native void native_init();

    private native void native_setup();

    public native Bitmap getThumbnailImageAtIndex(int i, BitmapParams bitmapParams, int i2, int i3);

    public native void release();

    public native void setDataSource(FileDescriptor fileDescriptor, long j, long j2) throws IllegalArgumentException;

    static {
        System.loadLibrary("media_jni");
        native_init();
    }

    public MediaMetadataRetriever() {
        native_setup();
    }

    public void setDataSource(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("null path");
        }
        Uri uri = Uri.parse(str);
        String scheme = uri.getScheme();
        if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            str = uri.getPath();
        } else if (scheme != null) {
            setDataSource(str, new HashMap());
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                setDataSource(fileInputStream.getFD(), 0L, 576460752303423487L);
                fileInputStream.close();
            } finally {
            }
        } catch (FileNotFoundException unused) {
            throw new IllegalArgumentException(str + " does not exist");
        } catch (IOException unused2) {
            throw new IllegalArgumentException("couldn't open " + str);
        }
    }

    public void setDataSource(String str, Map<String, String> map) throws IllegalArgumentException {
        String[] strArr = new String[map.size()];
        String[] strArr2 = new String[map.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            strArr[i] = entry.getKey();
            strArr2[i] = entry.getValue();
            i++;
        }
        _setDataSource(MediaHTTPService.createHttpServiceBinderIfNecessary(str), str, strArr, strArr2);
    }

    public void setDataSource(FileDescriptor fileDescriptor) throws IllegalArgumentException {
        setDataSource(fileDescriptor, 0L, 576460752303423487L);
    }

    public void setDataSource(Context context, Uri uri) throws SecurityException, IllegalArgumentException {
        if (uri == null) {
            throw new IllegalArgumentException("null uri");
        }
        String scheme = uri.getScheme();
        if (scheme == null || scheme.equals(ContentResolver.SCHEME_FILE)) {
            setDataSource(uri.getPath());
            return;
        }
        AutoCloseable autoCloseable = null;
        try {
            try {
                AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
                if (assetFileDescriptorOpenAssetFileDescriptor == null) {
                    throw new IllegalArgumentException("got null FileDescriptor for " + uri);
                }
                FileDescriptor fileDescriptor = assetFileDescriptorOpenAssetFileDescriptor.getFileDescriptor();
                if (!fileDescriptor.valid()) {
                    throw new IllegalArgumentException("got invalid FileDescriptor for " + uri);
                }
                if (assetFileDescriptorOpenAssetFileDescriptor.getDeclaredLength() < 0) {
                    setDataSource(fileDescriptor);
                } else {
                    setDataSource(fileDescriptor, assetFileDescriptorOpenAssetFileDescriptor.getStartOffset(), assetFileDescriptorOpenAssetFileDescriptor.getDeclaredLength());
                }
                if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                    try {
                        assetFileDescriptorOpenAssetFileDescriptor.close();
                    } catch (IOException unused) {
                    }
                }
            } catch (FileNotFoundException unused2) {
                throw new IllegalArgumentException("could not access " + uri);
            }
        } catch (SecurityException unused3) {
            if (0 != 0) {
                try {
                    autoCloseable.close();
                } catch (IOException unused4) {
                }
            }
            setDataSource(uri.toString());
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    autoCloseable.close();
                } catch (IOException unused5) {
                }
            }
            throw th;
        }
    }

    public void setDataSource(MediaDataSource mediaDataSource) throws IllegalArgumentException {
        _setDataSource(mediaDataSource);
    }

    public String extractMetadata(int i) {
        String strNativeExtractMetadata = nativeExtractMetadata(i);
        return i == 6 ? convertGenreTag(strNativeExtractMetadata) : strNativeExtractMetadata;
    }

    private String convertGenreTag(String str) {
        String strSubstring;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (Character.isDigit(str.charAt(0))) {
            try {
                int i = Integer.parseInt(str);
                if (i >= 0) {
                    String[] strArr = STANDARD_GENRES;
                    if (i < strArr.length) {
                        return strArr[i];
                    }
                }
            } catch (NumberFormatException unused) {
            }
            return null;
        }
        String str2 = null;
        StringBuilder sb = null;
        while (true) {
            if (!TextUtils.isEmpty(str2)) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(str2);
            }
            if (!TextUtils.isEmpty(str)) {
                if (str.startsWith("(RX)")) {
                    str = str.substring(4);
                    str2 = "Remix";
                } else if (str.startsWith("(CR)")) {
                    str = str.substring(4);
                    str2 = "Cover";
                } else if (str.startsWith("((")) {
                    int iIndexOf = str.indexOf(41);
                    if (iIndexOf == -1) {
                        str = str.substring(1);
                        str2 = str;
                        str = "";
                    } else {
                        int i2 = iIndexOf + 1;
                        strSubstring = str.substring(1, i2);
                        str = str.substring(i2);
                        str2 = strSubstring;
                    }
                } else if (str.startsWith("(")) {
                    int iIndexOf2 = str.indexOf(41);
                    if (iIndexOf2 == -1) {
                        return null;
                    }
                    try {
                        int i3 = Integer.parseInt(str.substring(1, iIndexOf2).toString());
                        if (i3 < 0) {
                            break;
                        }
                        String[] strArr2 = STANDARD_GENRES;
                        if (i3 >= strArr2.length) {
                            break;
                        }
                        strSubstring = strArr2[i3];
                        str = str.substring(iIndexOf2 + 1);
                        str2 = strSubstring;
                    } catch (NumberFormatException unused2) {
                    }
                } else {
                    str2 = str;
                    str = "";
                }
            } else {
                if (sb == null || sb.length() == 0) {
                    return null;
                }
                return sb.toString();
            }
        }
        return null;
    }

    public Bitmap getFrameAtTime(long j, int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("Unsupported option: " + i);
        }
        return _getFrameAtTime(j, i, -1, -1, null);
    }

    public Bitmap getFrameAtTime(long j, int i, BitmapParams bitmapParams) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("Unsupported option: " + i);
        }
        return _getFrameAtTime(j, i, -1, -1, bitmapParams);
    }

    public Bitmap getScaledFrameAtTime(long j, int i, int i2, int i3) {
        validate(i, i2, i3);
        return _getFrameAtTime(j, i, i2, i3, null);
    }

    public Bitmap getScaledFrameAtTime(long j, int i, int i2, int i3, BitmapParams bitmapParams) {
        validate(i, i2, i3);
        return _getFrameAtTime(j, i, i2, i3, bitmapParams);
    }

    private void validate(int i, int i2, int i3) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("Unsupported option: " + i);
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("Invalid width: " + i2);
        }
        if (i3 <= 0) {
            throw new IllegalArgumentException("Invalid height: " + i3);
        }
    }

    public Bitmap getFrameAtTime(long j) {
        return getFrameAtTime(j, 2);
    }

    public Bitmap getFrameAtTime() {
        return _getFrameAtTime(-1L, 2, -1, -1, null);
    }

    public static final class BitmapParams {
        private Bitmap.Config inPreferredConfig = Bitmap.Config.ARGB_8888;
        private Bitmap.Config outActualConfig = Bitmap.Config.ARGB_8888;

        public void setPreferredConfig(Bitmap.Config config) {
            if (config == null) {
                throw new IllegalArgumentException("preferred config can't be null");
            }
            this.inPreferredConfig = config;
        }

        public Bitmap.Config getPreferredConfig() {
            return this.inPreferredConfig;
        }

        public Bitmap.Config getActualConfig() {
            return this.outActualConfig;
        }
    }

    public Bitmap getFrameAtIndex(int i, BitmapParams bitmapParams) {
        return getFramesAtIndex(i, 1, bitmapParams).get(0);
    }

    public Bitmap getFrameAtIndex(int i) {
        return getFramesAtIndex(i, 1).get(0);
    }

    public List<Bitmap> getFramesAtIndex(int i, int i2, BitmapParams bitmapParams) {
        return getFramesAtIndexInternal(i, i2, bitmapParams);
    }

    public List<Bitmap> getFramesAtIndex(int i, int i2) {
        return getFramesAtIndexInternal(i, i2, null);
    }

    private List<Bitmap> getFramesAtIndexInternal(int i, int i2, BitmapParams bitmapParams) {
        if (!MediaMetrics.Value.YES.equals(extractMetadata(17))) {
            throw new IllegalStateException("Does not contail video or image sequences");
        }
        int i3 = Integer.parseInt(extractMetadata(32));
        if (i < 0 || i2 < 1 || i >= i3 || i > i3 - i2) {
            throw new IllegalArgumentException("Invalid frameIndex or numFrames: " + i + ", " + i2);
        }
        return _getFrameAtIndex(i, i2, bitmapParams);
    }

    public Bitmap getImageAtIndex(int i, BitmapParams bitmapParams) {
        return getImageAtIndexInternal(i, bitmapParams);
    }

    public Bitmap getImageAtIndex(int i) {
        return getImageAtIndexInternal(i, null);
    }

    public Bitmap getPrimaryImage(BitmapParams bitmapParams) {
        return getImageAtIndexInternal(-1, bitmapParams);
    }

    public Bitmap getPrimaryImage() {
        return getImageAtIndexInternal(-1, null);
    }

    private Bitmap getImageAtIndexInternal(int i, BitmapParams bitmapParams) {
        if (!MediaMetrics.Value.YES.equals(extractMetadata(26))) {
            throw new IllegalStateException("Does not contail still images");
        }
        String strExtractMetadata = extractMetadata(27);
        if (i >= Integer.parseInt(strExtractMetadata)) {
            throw new IllegalArgumentException("Invalid image index: " + strExtractMetadata);
        }
        return _getImageAtIndex(i, bitmapParams);
    }

    public byte[] getEmbeddedPicture() {
        return getEmbeddedPicture(65535);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        release();
    }

    protected void finalize() throws Throwable {
        try {
            native_finalize();
        } finally {
            super.finalize();
        }
    }
}
