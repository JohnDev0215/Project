package android.support.media;

import android.content.res.AssetManager;
import android.util.Log;
import android.util.Pair;
import androidx.mediarouter.media.MediaRouter;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.android.gms.cast.MediaError;
import com.visualon.OSMPUtils.voOSType;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;

/* JADX INFO: loaded from: classes.dex */
public class ExifInterface {
    private static final Charset ASCII;
    private static final ExifTag[] EXIF_POINTER_TAGS;
    static final ExifTag[][] EXIF_TAGS;
    static final byte[] IDENTIFIER_EXIF_APP1;
    private static final ExifTag[] IFD_EXIF_TAGS;
    private static final ExifTag[] IFD_GPS_TAGS;
    private static final ExifTag[] IFD_INTEROPERABILITY_TAGS;
    private static final ExifTag[] IFD_THUMBNAIL_TAGS;
    private static final ExifTag[] IFD_TIFF_TAGS;
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_LENGTH_TAG;
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_TAG;
    private static final ExifTag[] ORF_CAMERA_SETTINGS_TAGS;
    private static final ExifTag[] ORF_IMAGE_PROCESSING_TAGS;
    private static final ExifTag[] ORF_MAKER_NOTE_TAGS;
    private static final ExifTag[] PEF_TAGS;
    private static final ExifTag TAG_RAF_IMAGE_SIZE;
    private static final HashMap<Integer, Integer> sExifPointerTagMap;
    private static final HashMap<Integer, ExifTag>[] sExifTagMapsForReading;
    private static final HashMap<String, ExifTag>[] sExifTagMapsForWriting;
    private static SimpleDateFormat sFormatter;
    private static final Pattern sGpsTimestampPattern;
    private static final Pattern sNonZeroTimePattern;
    private static final HashSet<String> sTagSetForCompatibility;
    private final AssetManager.AssetInputStream mAssetInputStream;
    private final HashMap<String, ExifAttribute>[] mAttributes = new HashMap[EXIF_TAGS.length];
    private ByteOrder mExifByteOrder = ByteOrder.BIG_ENDIAN;
    private int mExifOffset;
    private final String mFilename;
    private boolean mHasThumbnail;
    private boolean mIsSupportedFile;
    private int mMimeType;
    private int mOrfMakerNoteOffset;
    private int mOrfThumbnailLength;
    private int mOrfThumbnailOffset;
    private int mRw2JpgFromRawOffset;
    private byte[] mThumbnailBytes;
    private int mThumbnailCompression;
    private int mThumbnailLength;
    private int mThumbnailOffset;
    private static final List<Integer> ROTATION_ORDER = Arrays.asList(1, 6, 3, 8);
    private static final List<Integer> FLIPPED_ROTATION_ORDER = Arrays.asList(2, 7, 4, 5);
    public static final int[] BITS_PER_SAMPLE_RGB = {8, 8, 8};
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_1 = {4};
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_2 = {8};
    static final byte[] JPEG_SIGNATURE = {-1, -40, -1};
    private static final byte[] ORF_MAKER_NOTE_HEADER_1 = {79, 76, 89, 77, 80, 0};
    private static final byte[] ORF_MAKER_NOTE_HEADER_2 = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
    static final String[] IFD_FORMAT_NAMES = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE"};
    static final int[] IFD_FORMAT_BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    private static final byte[] EXIF_ASCII_PREFIX = {65, 83, 67, 73, 73, 0, 0, 0};

    static {
        int i = 4;
        int i2 = 3;
        int i3 = 2;
        int i4 = 3;
        int i5 = 4;
        int i6 = 3;
        int i7 = 4;
        int i8 = 5;
        int i9 = 5;
        int i10 = 7;
        ExifTag[] exifTagArr = {new ExifTag("NewSubfileType", 254, i), new ExifTag("SubfileType", 255, i), new ExifTag("ImageWidth", 256, i4, i5), new ExifTag("ImageLength", 257, 3, 4), new ExifTag("BitsPerSample", MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED, i2), new ExifTag("Compression", MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED, i2), new ExifTag("PhotometricInterpretation", MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_SELECTED, i2), new ExifTag("ImageDescription", voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_DIALOGUE_ENHANCEMENT, i3), new ExifTag("Make", voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_DUAL_DECODING_MIXING, i3), new ExifTag("Model", voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_INPUTMODE, i3), new ExifTag("StripOffsets", voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_AUDIO_PROGRAM, i4, i5), new ExifTag("Orientation", voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_MIXING_BALANCE, i2), new ExifTag("SamplesPerPixel", 277, i2), new ExifTag("RowsPerStrip", 278, i6, i7), new ExifTag("StripByteCounts", 279, i6, i7), new ExifTag("XResolution", 282, i8), new ExifTag("YResolution", 283, i8), new ExifTag("PlanarConfiguration", 284, i2), new ExifTag("ResolutionUnit", 296, i2), new ExifTag("TransferFunction", MediaError.DetailedErrorCode.SEGMENT_NETWORK, i2), new ExifTag("Software", 305, i3), new ExifTag("DateTime", 306, i3), new ExifTag("Artist", MediaError.DetailedErrorCode.HLS_NETWORK_INVALID_SEGMENT, i3), new ExifTag("WhitePoint", 318, i9), new ExifTag("PrimaryChromaticities", 319, i9), new ExifTag("SubIFDPointer", 330, i), new ExifTag("JPEGInterchangeFormat", 513, i), new ExifTag("JPEGInterchangeFormatLength", MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_PROVIDER_REMOVED, i), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, i2), new ExifTag("YCbCrPositioning", 531, i2), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, i3), new ExifTag("ExifIFDPointer", 34665, i), new ExifTag("GPSInfoIFDPointer", 34853, i), new ExifTag("SensorTopBorder", i, i), new ExifTag("SensorLeftBorder", 5, i), new ExifTag("SensorBottomBorder", 6, i), new ExifTag("SensorRightBorder", i10, i), new ExifTag("ISO", 23, i2), new ExifTag("JpgFromRaw", 46, i10)};
        IFD_TIFF_TAGS = exifTagArr;
        int i11 = 5;
        int i12 = 7;
        int i13 = 5;
        int i14 = 10;
        int i15 = 5;
        int i16 = 7;
        int i17 = 3;
        int i18 = 4;
        int i19 = 5;
        int i20 = 7;
        ExifTag[] exifTagArr2 = {new ExifTag("ExposureTime", 33434, i11), new ExifTag("FNumber", 33437, i11), new ExifTag("ExposureProgram", 34850, i2), new ExifTag("SpectralSensitivity", 34852, i3), new ExifTag("PhotographicSensitivity", 34855, i2), new ExifTag("OECF", 34856, i12), new ExifTag("ExifVersion", 36864, i3), new ExifTag("DateTimeOriginal", 36867, i3), new ExifTag("DateTimeDigitized", 36868, i3), new ExifTag("ComponentsConfiguration", 37121, i12), new ExifTag("CompressedBitsPerPixel", 37122, i13), new ExifTag("ShutterSpeedValue", 37377, i14), new ExifTag("ApertureValue", 37378, i13), new ExifTag("BrightnessValue", 37379, i14), new ExifTag("ExposureBiasValue", 37380, i14), new ExifTag("MaxApertureValue", 37381, i15), new ExifTag("SubjectDistance", 37382, i15), new ExifTag("MeteringMode", 37383, i2), new ExifTag("LightSource", 37384, i2), new ExifTag("Flash", 37385, i2), new ExifTag("FocalLength", 37386, 5), new ExifTag("SubjectArea", 37396, i2), new ExifTag("MakerNote", 37500, i16), new ExifTag("UserComment", 37510, i16), new ExifTag("SubSecTime", 37520, i3), new ExifTag("SubSecTimeOriginal", 37521, i3), new ExifTag("SubSecTimeDigitized", 37522, i3), new ExifTag("FlashpixVersion", 40960, 7), new ExifTag("ColorSpace", 40961, i2), new ExifTag("PixelXDimension", 40962, i17, i18), new ExifTag("PixelYDimension", 40963, i17, i18), new ExifTag("RelatedSoundFile", 40964, i3), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("FlashEnergy", 41483, 5), new ExifTag("SpatialFrequencyResponse", 41484, 7), new ExifTag("FocalPlaneXResolution", 41486, i19), new ExifTag("FocalPlaneYResolution", 41487, i19), new ExifTag("FocalPlaneResolutionUnit", 41488, i2), new ExifTag("SubjectLocation", 41492, i2), new ExifTag("ExposureIndex", 41493, 5), new ExifTag("SensingMethod", 41495, i2), new ExifTag("FileSource", 41728, i20), new ExifTag("SceneType", 41729, i20), new ExifTag("CFAPattern", 41730, i20), new ExifTag("CustomRendered", 41985, i2), new ExifTag("ExposureMode", 41986, i2), new ExifTag("WhiteBalance", 41987, i2), new ExifTag("DigitalZoomRatio", 41988, 5), new ExifTag("FocalLengthIn35mmFilm", 41989, i2), new ExifTag("SceneCaptureType", 41990, i2), new ExifTag("GainControl", 41991, i2), new ExifTag("Contrast", 41992, i2), new ExifTag("Saturation", 41993, i2), new ExifTag("Sharpness", 41994, i2), new ExifTag("DeviceSettingDescription", 41995, 7), new ExifTag("SubjectDistanceRange", 41996, i2), new ExifTag("ImageUniqueID", 42016, i3), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, i17, i18)};
        IFD_EXIF_TAGS = exifTagArr2;
        int i21 = 1;
        int i22 = 5;
        int i23 = 5;
        int i24 = 5;
        int i25 = 7;
        ExifTag[] exifTagArr3 = {new ExifTag("GPSVersionID", 0, i21), new ExifTag("GPSLatitudeRef", i21, i3), new ExifTag("GPSLatitude", i3, i22), new ExifTag("GPSLongitudeRef", i2, i3), new ExifTag("GPSLongitude", 4, i22), new ExifTag("GPSAltitudeRef", i22, 1), new ExifTag("GPSAltitude", 6, i22), new ExifTag("GPSTimeStamp", 7, i22), new ExifTag("GPSSatellites", 8, i3), new ExifTag("GPSStatus", 9, i3), new ExifTag("GPSMeasureMode", 10, i3), new ExifTag("GPSDOP", 11, i23), new ExifTag("GPSSpeedRef", 12, i3), new ExifTag("GPSSpeed", 13, i23), new ExifTag("GPSTrackRef", 14, i3), new ExifTag("GPSTrack", 15, i23), new ExifTag("GPSImgDirectionRef", 16, i3), new ExifTag("GPSImgDirection", 17, i23), new ExifTag("GPSMapDatum", 18, i3), new ExifTag("GPSDestLatitudeRef", 19, i3), new ExifTag("GPSDestLatitude", 20, 5), new ExifTag("GPSDestLongitudeRef", 21, i3), new ExifTag("GPSDestLongitude", 22, i24), new ExifTag("GPSDestBearingRef", 23, i3), new ExifTag("GPSDestBearing", 24, i24), new ExifTag("GPSDestDistanceRef", 25, i3), new ExifTag("GPSDestDistance", 26, 5), new ExifTag("GPSProcessingMethod", 27, i25), new ExifTag("GPSAreaInformation", 28, i25), new ExifTag("GPSDateStamp", 29, i3), new ExifTag("GPSDifferential", 30, i2)};
        IFD_GPS_TAGS = exifTagArr3;
        ExifTag[] exifTagArr4 = {new ExifTag("InteroperabilityIndex", 1, i3)};
        IFD_INTEROPERABILITY_TAGS = exifTagArr4;
        int i26 = 4;
        int i27 = 3;
        int i28 = 4;
        int i29 = 3;
        int i30 = 2;
        int i31 = 3;
        int i32 = 5;
        int i33 = 3;
        int i34 = 2;
        int i35 = 5;
        int i36 = 4;
        int i37 = 3;
        int i38 = 4;
        ExifTag[] exifTagArr5 = {new ExifTag("NewSubfileType", 254, i26), new ExifTag("SubfileType", 255, i26), new ExifTag("ThumbnailImageWidth", 256, i27, i28), new ExifTag("ThumbnailImageLength", 257, i27, i28), new ExifTag("BitsPerSample", MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED, i29), new ExifTag("Compression", MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED, i29), new ExifTag("PhotometricInterpretation", MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_SELECTED, i29), new ExifTag("ImageDescription", voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_DIALOGUE_ENHANCEMENT, i30), new ExifTag("Make", voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_DUAL_DECODING_MIXING, i30), new ExifTag("Model", voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_INPUTMODE, i30), new ExifTag("StripOffsets", voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_AUDIO_PROGRAM, i27, i28), new ExifTag("Orientation", voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_MIXING_BALANCE, i31), new ExifTag("SamplesPerPixel", 277, i31), new ExifTag("RowsPerStrip", 278, i27, i28), new ExifTag("StripByteCounts", 279, i27, i28), new ExifTag("XResolution", 282, i32), new ExifTag("YResolution", 283, i32), new ExifTag("PlanarConfiguration", 284, i33), new ExifTag("ResolutionUnit", 296, i33), new ExifTag("TransferFunction", MediaError.DetailedErrorCode.SEGMENT_NETWORK, i33), new ExifTag("Software", 305, i34), new ExifTag("DateTime", 306, i34), new ExifTag("Artist", MediaError.DetailedErrorCode.HLS_NETWORK_INVALID_SEGMENT, i34), new ExifTag("WhitePoint", 318, i35), new ExifTag("PrimaryChromaticities", 319, i35), new ExifTag("SubIFDPointer", 330, i36), new ExifTag("JPEGInterchangeFormat", 513, i36), new ExifTag("JPEGInterchangeFormatLength", MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_PROVIDER_REMOVED, i36), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, i37), new ExifTag("YCbCrPositioning", 531, i37), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag("ExifIFDPointer", 34665, i38), new ExifTag("GPSInfoIFDPointer", 34853, i38), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, i27, i28)};
        IFD_THUMBNAIL_TAGS = exifTagArr5;
        TAG_RAF_IMAGE_SIZE = new ExifTag("StripOffsets", voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_AUDIO_PROGRAM, 3);
        int i39 = 4;
        ExifTag[] exifTagArr6 = {new ExifTag("ThumbnailImage", 256, 7), new ExifTag("CameraSettingsIFDPointer", 8224, i39), new ExifTag("ImageProcessingIFDPointer", 8256, i39)};
        ORF_MAKER_NOTE_TAGS = exifTagArr6;
        ExifTag[] exifTagArr7 = {new ExifTag("PreviewImageStart", 257, i39), new ExifTag("PreviewImageLength", MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED, i39)};
        ORF_CAMERA_SETTINGS_TAGS = exifTagArr7;
        int i40 = 3;
        ExifTag[] exifTagArr8 = {new ExifTag("AspectFrame", 4371, i40)};
        ORF_IMAGE_PROCESSING_TAGS = exifTagArr8;
        ExifTag[] exifTagArr9 = {new ExifTag("ColorSpace", 55, i40)};
        PEF_TAGS = exifTagArr9;
        int i41 = 4;
        ExifTag[][] exifTagArr10 = {exifTagArr, exifTagArr2, exifTagArr3, exifTagArr4, exifTagArr5, exifTagArr, exifTagArr6, exifTagArr7, exifTagArr8, exifTagArr9};
        EXIF_TAGS = exifTagArr10;
        int i42 = 1;
        EXIF_POINTER_TAGS = new ExifTag[]{new ExifTag("SubIFDPointer", 330, i41), new ExifTag("ExifIFDPointer", 34665, i41), new ExifTag("GPSInfoIFDPointer", 34853, i41), new ExifTag("InteroperabilityIFDPointer", 40965, i41), new ExifTag("CameraSettingsIFDPointer", 8224, i42), new ExifTag("ImageProcessingIFDPointer", 8256, i42)};
        JPEG_INTERCHANGE_FORMAT_TAG = new ExifTag("JPEGInterchangeFormat", 513, i41);
        JPEG_INTERCHANGE_FORMAT_LENGTH_TAG = new ExifTag("JPEGInterchangeFormatLength", MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_PROVIDER_REMOVED, i41);
        sExifTagMapsForReading = new HashMap[exifTagArr10.length];
        sExifTagMapsForWriting = new HashMap[exifTagArr10.length];
        sTagSetForCompatibility = new HashSet<>(Arrays.asList("FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"));
        sExifPointerTagMap = new HashMap<>();
        Charset charsetForName = Charset.forName("US-ASCII");
        ASCII = charsetForName;
        IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(charsetForName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        sFormatter = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        int i43 = 0;
        while (true) {
            ExifTag[][] exifTagArr11 = EXIF_TAGS;
            if (i43 < exifTagArr11.length) {
                sExifTagMapsForReading[i43] = new HashMap<>();
                sExifTagMapsForWriting[i43] = new HashMap<>();
                for (ExifTag exifTag : exifTagArr11[i43]) {
                    sExifTagMapsForReading[i43].put(Integer.valueOf(exifTag.number), exifTag);
                    sExifTagMapsForWriting[i43].put(exifTag.name, exifTag);
                }
                i43++;
            } else {
                HashMap<Integer, Integer> map = sExifPointerTagMap;
                ExifTag[] exifTagArr12 = EXIF_POINTER_TAGS;
                map.put(Integer.valueOf(exifTagArr12[0].number), 5);
                map.put(Integer.valueOf(exifTagArr12[1].number), 1);
                map.put(Integer.valueOf(exifTagArr12[2].number), 2);
                map.put(Integer.valueOf(exifTagArr12[3].number), 3);
                map.put(Integer.valueOf(exifTagArr12[4].number), 7);
                map.put(Integer.valueOf(exifTagArr12[5].number), 8);
                sNonZeroTimePattern = Pattern.compile(".*[1-9].*");
                sGpsTimestampPattern = Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
                return;
            }
        }
    }

    private static class Rational {
        public final long denominator;
        public final long numerator;

        private Rational(double d) {
            this((long) (d * 10000.0d), 10000L);
        }

        private Rational(long j, long j2) {
            if (j2 == 0) {
                this.numerator = 0L;
                this.denominator = 1L;
            } else {
                this.numerator = j;
                this.denominator = j2;
            }
        }

        public String toString() {
            return this.numerator + "/" + this.denominator;
        }

        public double calculate() {
            return this.numerator / this.denominator;
        }
    }

    private static class ExifAttribute {
        public final byte[] bytes;
        public final int format;
        public final int numberOfComponents;

        private ExifAttribute(int i, int i2, byte[] bArr) {
            this.format = i;
            this.numberOfComponents = i2;
            this.bytes = bArr;
        }

        public static ExifAttribute createUShort(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[3] * iArr.length]);
            byteBufferWrap.order(byteOrder);
            for (int i : iArr) {
                byteBufferWrap.putShort((short) i);
            }
            return new ExifAttribute(3, iArr.length, byteBufferWrap.array());
        }

        public static ExifAttribute createUShort(int i, ByteOrder byteOrder) {
            return createUShort(new int[]{i}, byteOrder);
        }

        public static ExifAttribute createULong(long[] jArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[4] * jArr.length]);
            byteBufferWrap.order(byteOrder);
            for (long j : jArr) {
                byteBufferWrap.putInt((int) j);
            }
            return new ExifAttribute(4, jArr.length, byteBufferWrap.array());
        }

        public static ExifAttribute createULong(long j, ByteOrder byteOrder) {
            return createULong(new long[]{j}, byteOrder);
        }

        public static ExifAttribute createSLong(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[9] * iArr.length]);
            byteBufferWrap.order(byteOrder);
            for (int i : iArr) {
                byteBufferWrap.putInt(i);
            }
            return new ExifAttribute(9, iArr.length, byteBufferWrap.array());
        }

        public static ExifAttribute createByte(String str) {
            if (str.length() != 1 || str.charAt(0) < '0' || str.charAt(0) > '1') {
                byte[] bytes = str.getBytes(ExifInterface.ASCII);
                return new ExifAttribute(1, bytes.length, bytes);
            }
            return new ExifAttribute(1, 1, new byte[]{(byte) (str.charAt(0) - '0')});
        }

        public static ExifAttribute createString(String str) {
            byte[] bytes = (str + (char) 0).getBytes(ExifInterface.ASCII);
            return new ExifAttribute(2, bytes.length, bytes);
        }

        public static ExifAttribute createURational(Rational[] rationalArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[5] * rationalArr.length]);
            byteBufferWrap.order(byteOrder);
            for (Rational rational : rationalArr) {
                byteBufferWrap.putInt((int) rational.numerator);
                byteBufferWrap.putInt((int) rational.denominator);
            }
            return new ExifAttribute(5, rationalArr.length, byteBufferWrap.array());
        }

        public static ExifAttribute createURational(Rational rational, ByteOrder byteOrder) {
            return createURational(new Rational[]{rational}, byteOrder);
        }

        public static ExifAttribute createSRational(Rational[] rationalArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[10] * rationalArr.length]);
            byteBufferWrap.order(byteOrder);
            for (Rational rational : rationalArr) {
                byteBufferWrap.putInt((int) rational.numerator);
                byteBufferWrap.putInt((int) rational.denominator);
            }
            return new ExifAttribute(10, rationalArr.length, byteBufferWrap.array());
        }

        public static ExifAttribute createDouble(double[] dArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[12] * dArr.length]);
            byteBufferWrap.order(byteOrder);
            for (double d : dArr) {
                byteBufferWrap.putDouble(d);
            }
            return new ExifAttribute(12, dArr.length, byteBufferWrap.array());
        }

        public String toString() {
            return "(" + ExifInterface.IFD_FORMAT_NAMES[this.format] + ", data length:" + this.bytes.length + ")";
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Not initialized variable reg: 3, insn: 0x01aa: MOVE (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:151:0x01aa */
        /* JADX WARN: Removed duplicated region for block: B:163:0x01ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object getValue(java.nio.ByteOrder r13) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 466
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.media.ExifInterface.ExifAttribute.getValue(java.nio.ByteOrder):java.lang.Object");
        }

        public double getDoubleValue(ByteOrder byteOrder) throws Throwable {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a double value");
            }
            if (value instanceof String) {
                return Double.parseDouble((String) value);
            }
            if (value instanceof long[]) {
                if (((long[]) value).length == 1) {
                    return r5[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (value instanceof int[]) {
                if (((int[]) value).length == 1) {
                    return r5[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (value instanceof double[]) {
                double[] dArr = (double[]) value;
                if (dArr.length == 1) {
                    return dArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (value instanceof Rational[]) {
                Rational[] rationalArr = (Rational[]) value;
                if (rationalArr.length == 1) {
                    return rationalArr[0].calculate();
                }
                throw new NumberFormatException("There are more than one component");
            }
            throw new NumberFormatException("Couldn't find a double value");
        }

        public int getIntValue(ByteOrder byteOrder) throws Throwable {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a integer value");
            }
            if (value instanceof String) {
                return Integer.parseInt((String) value);
            }
            if (value instanceof long[]) {
                long[] jArr = (long[]) value;
                if (jArr.length == 1) {
                    return (int) jArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (value instanceof int[]) {
                int[] iArr = (int[]) value;
                if (iArr.length == 1) {
                    return iArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            throw new NumberFormatException("Couldn't find a integer value");
        }

        public String getStringValue(ByteOrder byteOrder) throws Throwable {
            Object value = getValue(byteOrder);
            if (value == null) {
                return null;
            }
            if (value instanceof String) {
                return (String) value;
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            if (value instanceof long[]) {
                long[] jArr = (long[]) value;
                while (i < jArr.length) {
                    sb.append(jArr[i]);
                    i++;
                    if (i != jArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (value instanceof int[]) {
                int[] iArr = (int[]) value;
                while (i < iArr.length) {
                    sb.append(iArr[i]);
                    i++;
                    if (i != iArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (value instanceof double[]) {
                double[] dArr = (double[]) value;
                while (i < dArr.length) {
                    sb.append(dArr[i]);
                    i++;
                    if (i != dArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (!(value instanceof Rational[])) {
                return null;
            }
            Rational[] rationalArr = (Rational[]) value;
            while (i < rationalArr.length) {
                sb.append(rationalArr[i].numerator);
                sb.append(JsonPointer.SEPARATOR);
                sb.append(rationalArr[i].denominator);
                i++;
                if (i != rationalArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
    }

    static class ExifTag {
        public final String name;
        public final int number;
        public final int primaryFormat;
        public final int secondaryFormat;

        private ExifTag(String str, int i, int i2) {
            this.name = str;
            this.number = i;
            this.primaryFormat = i2;
            this.secondaryFormat = -1;
        }

        private ExifTag(String str, int i, int i2, int i3) {
            this.name = str;
            this.number = i;
            this.primaryFormat = i2;
            this.secondaryFormat = i3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isFormatCompatible(int i) {
            int i2;
            int i3 = this.primaryFormat;
            if (i3 == 7 || i == 7 || i3 == i || (i2 = this.secondaryFormat) == i) {
                return true;
            }
            if ((i3 == 4 || i2 == 4) && i == 3) {
                return true;
            }
            if ((i3 == 9 || i2 == 9) && i == 8) {
                return true;
            }
            return (i3 == 12 || i2 == 12) && i == 11;
        }
    }

    public ExifInterface(String str) throws Throwable {
        if (str == null) {
            throw new IllegalArgumentException("filename cannot be null");
        }
        FileInputStream fileInputStream = null;
        this.mAssetInputStream = null;
        this.mFilename = str;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                loadAttributes(fileInputStream2);
                closeQuietly(fileInputStream2);
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                closeQuietly(fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private ExifAttribute getExifAttribute(String str) {
        if ("ISOSpeedRatings".equals(str)) {
            str = "PhotographicSensitivity";
        }
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            ExifAttribute exifAttribute = this.mAttributes[i].get(str);
            if (exifAttribute != null) {
                return exifAttribute;
            }
        }
        return null;
    }

    public String getAttribute(String str) {
        ExifAttribute exifAttribute = getExifAttribute(str);
        if (exifAttribute != null) {
            if (!sTagSetForCompatibility.contains(str)) {
                return exifAttribute.getStringValue(this.mExifByteOrder);
            }
            if (str.equals("GPSTimeStamp")) {
                int i = exifAttribute.format;
                if (i == 5 || i == 10) {
                    Rational[] rationalArr = (Rational[]) exifAttribute.getValue(this.mExifByteOrder);
                    if (rationalArr == null || rationalArr.length != 3) {
                        Log.w("ExifInterface", "Invalid GPS Timestamp array. array=" + Arrays.toString(rationalArr));
                        return null;
                    }
                    Rational rational = rationalArr[0];
                    Rational rational2 = rationalArr[1];
                    Rational rational3 = rationalArr[2];
                    return String.format("%02d:%02d:%02d", Integer.valueOf((int) (rational.numerator / rational.denominator)), Integer.valueOf((int) (rational2.numerator / rational2.denominator)), Integer.valueOf((int) (rational3.numerator / rational3.denominator)));
                }
                Log.w("ExifInterface", "GPS Timestamp format is not rational. format=" + exifAttribute.format);
                return null;
            }
            try {
                return Double.toString(exifAttribute.getDoubleValue(this.mExifByteOrder));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public int getAttributeInt(String str, int i) {
        ExifAttribute exifAttribute = getExifAttribute(str);
        if (exifAttribute == null) {
            return i;
        }
        try {
            return exifAttribute.getIntValue(this.mExifByteOrder);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public void setAttribute(String str, String str2) {
        ExifTag exifTag;
        int i;
        String string = str2;
        String str3 = "ISOSpeedRatings".equals(str) ? "PhotographicSensitivity" : str;
        if (string != null && sTagSetForCompatibility.contains(str3)) {
            if (str3.equals("GPSTimeStamp")) {
                Matcher matcher = sGpsTimestampPattern.matcher(string);
                if (!matcher.find()) {
                    Log.w("ExifInterface", "Invalid value for " + str3 + " : " + string);
                    return;
                }
                string = Integer.parseInt(matcher.group(1)) + "/1," + Integer.parseInt(matcher.group(2)) + "/1," + Integer.parseInt(matcher.group(3)) + "/1";
            } else {
                try {
                    string = new Rational(Double.parseDouble(str2)).toString();
                } catch (NumberFormatException unused) {
                    Log.w("ExifInterface", "Invalid value for " + str3 + " : " + string);
                    return;
                }
            }
        }
        for (int i2 = 0; i2 < EXIF_TAGS.length; i2++) {
            if ((i2 != 4 || this.mHasThumbnail) && (exifTag = sExifTagMapsForWriting[i2].get(str3)) != null) {
                if (string == null) {
                    this.mAttributes[i2].remove(str3);
                } else {
                    Pair<Integer, Integer> pairGuessDataFormat = guessDataFormat(string);
                    if (exifTag.primaryFormat == ((Integer) pairGuessDataFormat.first).intValue() || exifTag.primaryFormat == ((Integer) pairGuessDataFormat.second).intValue()) {
                        i = exifTag.primaryFormat;
                    } else {
                        int i3 = exifTag.secondaryFormat;
                        if (i3 != -1 && (i3 == ((Integer) pairGuessDataFormat.first).intValue() || exifTag.secondaryFormat == ((Integer) pairGuessDataFormat.second).intValue())) {
                            i = exifTag.secondaryFormat;
                        } else {
                            int i4 = exifTag.primaryFormat;
                            if (i4 == 1 || i4 == 7 || i4 == 2) {
                                i = i4;
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Given tag (");
                                sb.append(str3);
                                sb.append(") value didn't match with one of expected ");
                                sb.append("formats: ");
                                String[] strArr = IFD_FORMAT_NAMES;
                                sb.append(strArr[exifTag.primaryFormat]);
                                sb.append(exifTag.secondaryFormat == -1 ? "" : ", " + strArr[exifTag.secondaryFormat]);
                                sb.append(" (guess: ");
                                sb.append(strArr[((Integer) pairGuessDataFormat.first).intValue()]);
                                sb.append(((Integer) pairGuessDataFormat.second).intValue() != -1 ? ", " + strArr[((Integer) pairGuessDataFormat.second).intValue()] : "");
                                sb.append(")");
                                Log.w("ExifInterface", sb.toString());
                            }
                        }
                    }
                    switch (i) {
                        case 1:
                            this.mAttributes[i2].put(str3, ExifAttribute.createByte(string));
                            break;
                        case 2:
                        case 7:
                            this.mAttributes[i2].put(str3, ExifAttribute.createString(string));
                            break;
                        case 3:
                            String[] strArrSplit = string.split(",");
                            int[] iArr = new int[strArrSplit.length];
                            for (int i5 = 0; i5 < strArrSplit.length; i5++) {
                                iArr[i5] = Integer.parseInt(strArrSplit[i5]);
                            }
                            this.mAttributes[i2].put(str3, ExifAttribute.createUShort(iArr, this.mExifByteOrder));
                            break;
                        case 4:
                            String[] strArrSplit2 = string.split(",");
                            long[] jArr = new long[strArrSplit2.length];
                            for (int i6 = 0; i6 < strArrSplit2.length; i6++) {
                                jArr[i6] = Long.parseLong(strArrSplit2[i6]);
                            }
                            this.mAttributes[i2].put(str3, ExifAttribute.createULong(jArr, this.mExifByteOrder));
                            break;
                        case 5:
                            String[] strArrSplit3 = string.split(",");
                            Rational[] rationalArr = new Rational[strArrSplit3.length];
                            for (int i7 = 0; i7 < strArrSplit3.length; i7++) {
                                String[] strArrSplit4 = strArrSplit3[i7].split("/");
                                rationalArr[i7] = new Rational((long) Double.parseDouble(strArrSplit4[0]), (long) Double.parseDouble(strArrSplit4[1]));
                            }
                            this.mAttributes[i2].put(str3, ExifAttribute.createURational(rationalArr, this.mExifByteOrder));
                            break;
                        case 6:
                        case 8:
                        case 11:
                        default:
                            Log.w("ExifInterface", "Data format isn't one of expected formats: " + i);
                            break;
                        case 9:
                            String[] strArrSplit5 = string.split(",");
                            int[] iArr2 = new int[strArrSplit5.length];
                            for (int i8 = 0; i8 < strArrSplit5.length; i8++) {
                                iArr2[i8] = Integer.parseInt(strArrSplit5[i8]);
                            }
                            this.mAttributes[i2].put(str3, ExifAttribute.createSLong(iArr2, this.mExifByteOrder));
                            break;
                        case 10:
                            String[] strArrSplit6 = string.split(",");
                            Rational[] rationalArr2 = new Rational[strArrSplit6.length];
                            for (int i9 = 0; i9 < strArrSplit6.length; i9++) {
                                String[] strArrSplit7 = strArrSplit6[i9].split("/");
                                rationalArr2[i9] = new Rational((long) Double.parseDouble(strArrSplit7[0]), (long) Double.parseDouble(strArrSplit7[1]));
                            }
                            this.mAttributes[i2].put(str3, ExifAttribute.createSRational(rationalArr2, this.mExifByteOrder));
                            break;
                        case 12:
                            String[] strArrSplit8 = string.split(",");
                            double[] dArr = new double[strArrSplit8.length];
                            for (int i10 = 0; i10 < strArrSplit8.length; i10++) {
                                dArr[i10] = Double.parseDouble(strArrSplit8[i10]);
                            }
                            this.mAttributes[i2].put(str3, ExifAttribute.createDouble(dArr, this.mExifByteOrder));
                            break;
                    }
                }
            }
        }
    }

    public int getRotationDegrees() {
        switch (getAttributeInt("Orientation", 1)) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 8:
                return voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_DIALOGUE_ENHANCEMENT;
            case 6:
            case 7:
                return 90;
            default:
                return 0;
        }
    }

    private void loadAttributes(InputStream inputStream) throws IOException {
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            try {
                try {
                    this.mAttributes[i] = new HashMap<>();
                } catch (IOException unused) {
                    this.mIsSupportedFile = false;
                }
            } finally {
                addDefaultValuesForCompatibility();
            }
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT);
        this.mMimeType = getMimeType(bufferedInputStream);
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bufferedInputStream);
        switch (this.mMimeType) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 8:
            case 11:
                getRawAttributes(byteOrderedDataInputStream);
                break;
            case 4:
                getJpegAttributes(byteOrderedDataInputStream, 0, 0);
                break;
            case 7:
                getOrfAttributes(byteOrderedDataInputStream);
                break;
            case 9:
                getRafAttributes(byteOrderedDataInputStream);
                break;
            case 10:
                getRw2Attributes(byteOrderedDataInputStream);
                break;
        }
        setThumbnailData(byteOrderedDataInputStream);
        this.mIsSupportedFile = true;
    }

    public void setAltitude(double d) {
        String str = d >= 0.0d ? "0" : "1";
        setAttribute("GPSAltitude", new Rational(Math.abs(d)).toString());
        setAttribute("GPSAltitudeRef", str);
    }

    private int getMimeType(BufferedInputStream bufferedInputStream) throws IOException {
        bufferedInputStream.mark(DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT);
        byte[] bArr = new byte[DisconnectedBufferOptions.DISCONNECTED_BUFFER_SIZE_DEFAULT];
        bufferedInputStream.read(bArr);
        bufferedInputStream.reset();
        if (isJpegFormat(bArr)) {
            return 4;
        }
        if (isRafFormat(bArr)) {
            return 9;
        }
        if (isOrfFormat(bArr)) {
            return 7;
        }
        return isRw2Format(bArr) ? 10 : 0;
    }

    private static boolean isJpegFormat(byte[] bArr) throws IOException {
        int i = 0;
        while (true) {
            byte[] bArr2 = JPEG_SIGNATURE;
            if (i >= bArr2.length) {
                return true;
            }
            if (bArr[i] != bArr2[i]) {
                return false;
            }
            i++;
        }
    }

    private boolean isRafFormat(byte[] bArr) throws IOException {
        byte[] bytes = "FUJIFILMCCD-RAW".getBytes(Charset.defaultCharset());
        for (int i = 0; i < bytes.length; i++) {
            if (bArr[i] != bytes[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isOrfFormat(byte[] bArr) throws IOException {
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bArr);
        ByteOrder byteOrder = readByteOrder(byteOrderedDataInputStream);
        this.mExifByteOrder = byteOrder;
        byteOrderedDataInputStream.setByteOrder(byteOrder);
        short s = byteOrderedDataInputStream.readShort();
        byteOrderedDataInputStream.close();
        return s == 20306 || s == 21330;
    }

    private boolean isRw2Format(byte[] bArr) throws IOException {
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bArr);
        ByteOrder byteOrder = readByteOrder(byteOrderedDataInputStream);
        this.mExifByteOrder = byteOrder;
        byteOrderedDataInputStream.setByteOrder(byteOrder);
        short s = byteOrderedDataInputStream.readShort();
        byteOrderedDataInputStream.close();
        return s == 85;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0057 A[FALL_THROUGH] */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getJpegAttributes(android.support.media.ExifInterface.ByteOrderedDataInputStream r10, int r11, int r12) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.media.ExifInterface.getJpegAttributes(android.support.media.ExifInterface$ByteOrderedDataInputStream, int, int):void");
    }

    private void getRawAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws Throwable {
        ExifAttribute exifAttribute;
        parseTiffHeaders(byteOrderedDataInputStream, byteOrderedDataInputStream.available());
        readImageFileDirectory(byteOrderedDataInputStream, 0);
        updateImageSizeValues(byteOrderedDataInputStream, 0);
        updateImageSizeValues(byteOrderedDataInputStream, 5);
        updateImageSizeValues(byteOrderedDataInputStream, 4);
        validateImages(byteOrderedDataInputStream);
        if (this.mMimeType != 8 || (exifAttribute = this.mAttributes[1].get("MakerNote")) == null) {
            return;
        }
        ByteOrderedDataInputStream byteOrderedDataInputStream2 = new ByteOrderedDataInputStream(exifAttribute.bytes);
        byteOrderedDataInputStream2.setByteOrder(this.mExifByteOrder);
        byteOrderedDataInputStream2.seek(6L);
        readImageFileDirectory(byteOrderedDataInputStream2, 9);
        ExifAttribute exifAttribute2 = this.mAttributes[9].get("ColorSpace");
        if (exifAttribute2 != null) {
            this.mAttributes[1].put("ColorSpace", exifAttribute2);
        }
    }

    private void getRafAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        byteOrderedDataInputStream.skipBytes(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        byteOrderedDataInputStream.read(bArr);
        byteOrderedDataInputStream.skipBytes(4);
        byteOrderedDataInputStream.read(bArr2);
        int i = ByteBuffer.wrap(bArr).getInt();
        int i2 = ByteBuffer.wrap(bArr2).getInt();
        getJpegAttributes(byteOrderedDataInputStream, i, 5);
        byteOrderedDataInputStream.seek(i2);
        byteOrderedDataInputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
        int i3 = byteOrderedDataInputStream.readInt();
        for (int i4 = 0; i4 < i3; i4++) {
            int unsignedShort = byteOrderedDataInputStream.readUnsignedShort();
            int unsignedShort2 = byteOrderedDataInputStream.readUnsignedShort();
            if (unsignedShort == TAG_RAF_IMAGE_SIZE.number) {
                short s = byteOrderedDataInputStream.readShort();
                short s2 = byteOrderedDataInputStream.readShort();
                ExifAttribute exifAttributeCreateUShort = ExifAttribute.createUShort(s, this.mExifByteOrder);
                ExifAttribute exifAttributeCreateUShort2 = ExifAttribute.createUShort(s2, this.mExifByteOrder);
                this.mAttributes[0].put("ImageLength", exifAttributeCreateUShort);
                this.mAttributes[0].put("ImageWidth", exifAttributeCreateUShort2);
                return;
            }
            byteOrderedDataInputStream.skipBytes(unsignedShort2);
        }
    }

    private void getOrfAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws Throwable {
        int i;
        int i2;
        getRawAttributes(byteOrderedDataInputStream);
        ExifAttribute exifAttribute = this.mAttributes[1].get("MakerNote");
        if (exifAttribute != null) {
            ByteOrderedDataInputStream byteOrderedDataInputStream2 = new ByteOrderedDataInputStream(exifAttribute.bytes);
            byteOrderedDataInputStream2.setByteOrder(this.mExifByteOrder);
            byte[] bArr = ORF_MAKER_NOTE_HEADER_1;
            byte[] bArr2 = new byte[bArr.length];
            byteOrderedDataInputStream2.readFully(bArr2);
            byteOrderedDataInputStream2.seek(0L);
            byte[] bArr3 = ORF_MAKER_NOTE_HEADER_2;
            byte[] bArr4 = new byte[bArr3.length];
            byteOrderedDataInputStream2.readFully(bArr4);
            if (Arrays.equals(bArr2, bArr)) {
                byteOrderedDataInputStream2.seek(8L);
            } else if (Arrays.equals(bArr4, bArr3)) {
                byteOrderedDataInputStream2.seek(12L);
            }
            readImageFileDirectory(byteOrderedDataInputStream2, 6);
            ExifAttribute exifAttribute2 = this.mAttributes[7].get("PreviewImageStart");
            ExifAttribute exifAttribute3 = this.mAttributes[7].get("PreviewImageLength");
            if (exifAttribute2 != null && exifAttribute3 != null) {
                this.mAttributes[5].put("JPEGInterchangeFormat", exifAttribute2);
                this.mAttributes[5].put("JPEGInterchangeFormatLength", exifAttribute3);
            }
            ExifAttribute exifAttribute4 = this.mAttributes[8].get("AspectFrame");
            if (exifAttribute4 != null) {
                int[] iArr = (int[]) exifAttribute4.getValue(this.mExifByteOrder);
                if (iArr == null || iArr.length != 4) {
                    Log.w("ExifInterface", "Invalid aspect frame values. frame=" + Arrays.toString(iArr));
                    return;
                }
                int i3 = iArr[2];
                int i4 = iArr[0];
                if (i3 <= i4 || (i = iArr[3]) <= (i2 = iArr[1])) {
                    return;
                }
                int i5 = (i3 - i4) + 1;
                int i6 = (i - i2) + 1;
                if (i5 < i6) {
                    int i7 = i5 + i6;
                    i6 = i7 - i6;
                    i5 = i7 - i6;
                }
                ExifAttribute exifAttributeCreateUShort = ExifAttribute.createUShort(i5, this.mExifByteOrder);
                ExifAttribute exifAttributeCreateUShort2 = ExifAttribute.createUShort(i6, this.mExifByteOrder);
                this.mAttributes[0].put("ImageWidth", exifAttributeCreateUShort);
                this.mAttributes[0].put("ImageLength", exifAttributeCreateUShort2);
            }
        }
    }

    private void getRw2Attributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws Throwable {
        getRawAttributes(byteOrderedDataInputStream);
        if (this.mAttributes[0].get("JpgFromRaw") != null) {
            getJpegAttributes(byteOrderedDataInputStream, this.mRw2JpgFromRawOffset, 5);
        }
        ExifAttribute exifAttribute = this.mAttributes[0].get("ISO");
        ExifAttribute exifAttribute2 = this.mAttributes[1].get("PhotographicSensitivity");
        if (exifAttribute == null || exifAttribute2 != null) {
            return;
        }
        this.mAttributes[1].put("PhotographicSensitivity", exifAttribute);
    }

    private void readExifSegment(byte[] bArr, int i) throws IOException {
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bArr);
        parseTiffHeaders(byteOrderedDataInputStream, bArr.length);
        readImageFileDirectory(byteOrderedDataInputStream, i);
    }

    private void addDefaultValuesForCompatibility() {
        String attribute = getAttribute("DateTimeOriginal");
        if (attribute != null && getAttribute("DateTime") == null) {
            this.mAttributes[0].put("DateTime", ExifAttribute.createString(attribute));
        }
        if (getAttribute("ImageWidth") == null) {
            this.mAttributes[0].put("ImageWidth", ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (getAttribute("ImageLength") == null) {
            this.mAttributes[0].put("ImageLength", ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (getAttribute("Orientation") == null) {
            this.mAttributes[0].put("Orientation", ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (getAttribute("LightSource") == null) {
            this.mAttributes[1].put("LightSource", ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
    }

    private ByteOrder readByteOrder(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        short s = byteOrderedDataInputStream.readShort();
        if (s == 18761) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        if (s == 19789) {
            return ByteOrder.BIG_ENDIAN;
        }
        throw new IOException("Invalid byte order: " + Integer.toHexString(s));
    }

    private void parseTiffHeaders(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) throws IOException {
        ByteOrder byteOrder = readByteOrder(byteOrderedDataInputStream);
        this.mExifByteOrder = byteOrder;
        byteOrderedDataInputStream.setByteOrder(byteOrder);
        int unsignedShort = byteOrderedDataInputStream.readUnsignedShort();
        int i2 = this.mMimeType;
        if (i2 != 7 && i2 != 10 && unsignedShort != 42) {
            throw new IOException("Invalid start code: " + Integer.toHexString(unsignedShort));
        }
        int i3 = byteOrderedDataInputStream.readInt();
        if (i3 < 8 || i3 >= i) {
            throw new IOException("Invalid first Ifd offset: " + i3);
        }
        int i4 = i3 - 8;
        if (i4 <= 0 || byteOrderedDataInputStream.skipBytes(i4) == i4) {
            return;
        }
        throw new IOException("Couldn't jump to first Ifd: " + i4);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void readImageFileDirectory(android.support.media.ExifInterface.ByteOrderedDataInputStream r25, int r26) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 700
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.media.ExifInterface.readImageFileDirectory(android.support.media.ExifInterface$ByteOrderedDataInputStream, int):void");
    }

    private void retrieveJpegImageSize(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) throws IOException {
        ExifAttribute exifAttribute;
        ExifAttribute exifAttribute2 = this.mAttributes[i].get("ImageLength");
        ExifAttribute exifAttribute3 = this.mAttributes[i].get("ImageWidth");
        if ((exifAttribute2 == null || exifAttribute3 == null) && (exifAttribute = this.mAttributes[i].get("JPEGInterchangeFormat")) != null) {
            getJpegAttributes(byteOrderedDataInputStream, exifAttribute.getIntValue(this.mExifByteOrder), i);
        }
    }

    private void setThumbnailData(ByteOrderedDataInputStream byteOrderedDataInputStream) throws Throwable {
        HashMap<String, ExifAttribute> map = this.mAttributes[4];
        ExifAttribute exifAttribute = map.get("Compression");
        if (exifAttribute != null) {
            int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
            this.mThumbnailCompression = intValue;
            if (intValue != 1) {
                if (intValue == 6) {
                    handleThumbnailFromJfif(byteOrderedDataInputStream, map);
                    return;
                } else if (intValue != 7) {
                    return;
                }
            }
            if (isSupportedDataType(map)) {
                handleThumbnailFromStrips(byteOrderedDataInputStream, map);
                return;
            }
            return;
        }
        this.mThumbnailCompression = 6;
        handleThumbnailFromJfif(byteOrderedDataInputStream, map);
    }

    private void handleThumbnailFromJfif(ByteOrderedDataInputStream byteOrderedDataInputStream, HashMap map) throws Throwable {
        int i;
        ExifAttribute exifAttribute = (ExifAttribute) map.get("JPEGInterchangeFormat");
        ExifAttribute exifAttribute2 = (ExifAttribute) map.get("JPEGInterchangeFormatLength");
        if (exifAttribute == null || exifAttribute2 == null) {
            return;
        }
        int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
        int iMin = Math.min(exifAttribute2.getIntValue(this.mExifByteOrder), byteOrderedDataInputStream.available() - intValue);
        int i2 = this.mMimeType;
        if (i2 != 4 && i2 != 9 && i2 != 10) {
            if (i2 == 7) {
                i = this.mOrfMakerNoteOffset;
            }
            if (intValue > 0 || iMin <= 0) {
            }
            this.mHasThumbnail = true;
            this.mThumbnailOffset = intValue;
            this.mThumbnailLength = iMin;
            if (this.mFilename == null && this.mAssetInputStream == null) {
                byte[] bArr = new byte[iMin];
                byteOrderedDataInputStream.seek(intValue);
                byteOrderedDataInputStream.readFully(bArr);
                this.mThumbnailBytes = bArr;
                return;
            }
            return;
        }
        i = this.mExifOffset;
        intValue += i;
        if (intValue > 0) {
        }
    }

    private void handleThumbnailFromStrips(ByteOrderedDataInputStream byteOrderedDataInputStream, HashMap map) throws IOException {
        ExifAttribute exifAttribute = (ExifAttribute) map.get("StripOffsets");
        ExifAttribute exifAttribute2 = (ExifAttribute) map.get("StripByteCounts");
        if (exifAttribute == null || exifAttribute2 == null) {
            return;
        }
        long[] jArrConvertToLongArray = convertToLongArray(exifAttribute.getValue(this.mExifByteOrder));
        long[] jArrConvertToLongArray2 = convertToLongArray(exifAttribute2.getValue(this.mExifByteOrder));
        if (jArrConvertToLongArray == null) {
            Log.w("ExifInterface", "stripOffsets should not be null.");
            return;
        }
        if (jArrConvertToLongArray2 == null) {
            Log.w("ExifInterface", "stripByteCounts should not be null.");
            return;
        }
        long j = 0;
        for (long j2 : jArrConvertToLongArray2) {
            j += j2;
        }
        int i = (int) j;
        byte[] bArr = new byte[i];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < jArrConvertToLongArray.length; i4++) {
            int i5 = (int) jArrConvertToLongArray[i4];
            int i6 = (int) jArrConvertToLongArray2[i4];
            int i7 = i5 - i2;
            if (i7 < 0) {
                Log.d("ExifInterface", "Invalid strip offset value");
            }
            byteOrderedDataInputStream.seek(i7);
            int i8 = i2 + i7;
            byte[] bArr2 = new byte[i6];
            byteOrderedDataInputStream.read(bArr2);
            i2 = i8 + i6;
            System.arraycopy(bArr2, 0, bArr, i3, i6);
            i3 += i6;
        }
        this.mHasThumbnail = true;
        this.mThumbnailBytes = bArr;
        this.mThumbnailLength = i;
    }

    private boolean isSupportedDataType(HashMap map) throws Throwable {
        ExifAttribute exifAttribute;
        ExifAttribute exifAttribute2 = (ExifAttribute) map.get("BitsPerSample");
        if (exifAttribute2 == null) {
            return false;
        }
        int[] iArr = (int[]) exifAttribute2.getValue(this.mExifByteOrder);
        int[] iArr2 = BITS_PER_SAMPLE_RGB;
        if (Arrays.equals(iArr2, iArr)) {
            return true;
        }
        if (this.mMimeType != 3 || (exifAttribute = (ExifAttribute) map.get("PhotometricInterpretation")) == null) {
            return false;
        }
        int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
        return (intValue == 1 && Arrays.equals(iArr, BITS_PER_SAMPLE_GREYSCALE_2)) || (intValue == 6 && Arrays.equals(iArr, iArr2));
    }

    private boolean isThumbnail(HashMap map) throws IOException {
        ExifAttribute exifAttribute = (ExifAttribute) map.get("ImageLength");
        ExifAttribute exifAttribute2 = (ExifAttribute) map.get("ImageWidth");
        if (exifAttribute == null || exifAttribute2 == null) {
            return false;
        }
        return exifAttribute.getIntValue(this.mExifByteOrder) <= 512 && exifAttribute2.getIntValue(this.mExifByteOrder) <= 512;
    }

    private void validateImages(InputStream inputStream) throws Throwable {
        swapBasedOnImageSize(0, 5);
        swapBasedOnImageSize(0, 4);
        swapBasedOnImageSize(5, 4);
        ExifAttribute exifAttribute = this.mAttributes[1].get("PixelXDimension");
        ExifAttribute exifAttribute2 = this.mAttributes[1].get("PixelYDimension");
        if (exifAttribute != null && exifAttribute2 != null) {
            this.mAttributes[0].put("ImageWidth", exifAttribute);
            this.mAttributes[0].put("ImageLength", exifAttribute2);
        }
        if (this.mAttributes[4].isEmpty() && isThumbnail(this.mAttributes[5])) {
            HashMap<String, ExifAttribute>[] mapArr = this.mAttributes;
            mapArr[4] = mapArr[5];
            mapArr[5] = new HashMap<>();
        }
        if (isThumbnail(this.mAttributes[4])) {
            return;
        }
        Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
    }

    private void updateImageSizeValues(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) throws Throwable {
        ExifAttribute exifAttributeCreateUShort;
        ExifAttribute exifAttributeCreateUShort2;
        ExifAttribute exifAttribute = this.mAttributes[i].get("DefaultCropSize");
        ExifAttribute exifAttribute2 = this.mAttributes[i].get("SensorTopBorder");
        ExifAttribute exifAttribute3 = this.mAttributes[i].get("SensorLeftBorder");
        ExifAttribute exifAttribute4 = this.mAttributes[i].get("SensorBottomBorder");
        ExifAttribute exifAttribute5 = this.mAttributes[i].get("SensorRightBorder");
        if (exifAttribute == null) {
            if (exifAttribute2 != null && exifAttribute3 != null && exifAttribute4 != null && exifAttribute5 != null) {
                int intValue = exifAttribute2.getIntValue(this.mExifByteOrder);
                int intValue2 = exifAttribute4.getIntValue(this.mExifByteOrder);
                int intValue3 = exifAttribute5.getIntValue(this.mExifByteOrder);
                int intValue4 = exifAttribute3.getIntValue(this.mExifByteOrder);
                if (intValue2 <= intValue || intValue3 <= intValue4) {
                    return;
                }
                ExifAttribute exifAttributeCreateUShort3 = ExifAttribute.createUShort(intValue2 - intValue, this.mExifByteOrder);
                ExifAttribute exifAttributeCreateUShort4 = ExifAttribute.createUShort(intValue3 - intValue4, this.mExifByteOrder);
                this.mAttributes[i].put("ImageLength", exifAttributeCreateUShort3);
                this.mAttributes[i].put("ImageWidth", exifAttributeCreateUShort4);
                return;
            }
            retrieveJpegImageSize(byteOrderedDataInputStream, i);
            return;
        }
        if (exifAttribute.format == 5) {
            Rational[] rationalArr = (Rational[]) exifAttribute.getValue(this.mExifByteOrder);
            if (rationalArr == null || rationalArr.length != 2) {
                Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(rationalArr));
                return;
            }
            exifAttributeCreateUShort = ExifAttribute.createURational(rationalArr[0], this.mExifByteOrder);
            exifAttributeCreateUShort2 = ExifAttribute.createURational(rationalArr[1], this.mExifByteOrder);
        } else {
            int[] iArr = (int[]) exifAttribute.getValue(this.mExifByteOrder);
            if (iArr == null || iArr.length != 2) {
                Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(iArr));
                return;
            }
            exifAttributeCreateUShort = ExifAttribute.createUShort(iArr[0], this.mExifByteOrder);
            exifAttributeCreateUShort2 = ExifAttribute.createUShort(iArr[1], this.mExifByteOrder);
        }
        this.mAttributes[i].put("ImageWidth", exifAttributeCreateUShort);
        this.mAttributes[i].put("ImageLength", exifAttributeCreateUShort2);
    }

    private static Pair<Integer, Integer> guessDataFormat(String str) {
        if (str.contains(",")) {
            String[] strArrSplit = str.split(",");
            Pair<Integer, Integer> pairGuessDataFormat = guessDataFormat(strArrSplit[0]);
            if (((Integer) pairGuessDataFormat.first).intValue() == 2) {
                return pairGuessDataFormat;
            }
            for (int i = 1; i < strArrSplit.length; i++) {
                Pair<Integer, Integer> pairGuessDataFormat2 = guessDataFormat(strArrSplit[i]);
                int iIntValue = (((Integer) pairGuessDataFormat2.first).equals(pairGuessDataFormat.first) || ((Integer) pairGuessDataFormat2.second).equals(pairGuessDataFormat.first)) ? ((Integer) pairGuessDataFormat.first).intValue() : -1;
                int iIntValue2 = (((Integer) pairGuessDataFormat.second).intValue() == -1 || !(((Integer) pairGuessDataFormat2.first).equals(pairGuessDataFormat.second) || ((Integer) pairGuessDataFormat2.second).equals(pairGuessDataFormat.second))) ? -1 : ((Integer) pairGuessDataFormat.second).intValue();
                if (iIntValue == -1 && iIntValue2 == -1) {
                    return new Pair<>(2, -1);
                }
                if (iIntValue == -1) {
                    pairGuessDataFormat = new Pair<>(Integer.valueOf(iIntValue2), -1);
                } else if (iIntValue2 == -1) {
                    pairGuessDataFormat = new Pair<>(Integer.valueOf(iIntValue), -1);
                }
            }
            return pairGuessDataFormat;
        }
        if (str.contains("/")) {
            String[] strArrSplit2 = str.split("/");
            if (strArrSplit2.length == 2) {
                try {
                    long j = (long) Double.parseDouble(strArrSplit2[0]);
                    long j2 = (long) Double.parseDouble(strArrSplit2[1]);
                    if (j >= 0 && j2 >= 0) {
                        if (j <= 2147483647L && j2 <= 2147483647L) {
                            return new Pair<>(10, 5);
                        }
                        return new Pair<>(5, -1);
                    }
                    return new Pair<>(10, -1);
                } catch (NumberFormatException unused) {
                }
            }
            return new Pair<>(2, -1);
        }
        try {
            try {
                Long lValueOf = Long.valueOf(Long.parseLong(str));
                if (lValueOf.longValue() >= 0 && lValueOf.longValue() <= 65535) {
                    return new Pair<>(3, 4);
                }
                if (lValueOf.longValue() < 0) {
                    return new Pair<>(9, -1);
                }
                return new Pair<>(4, -1);
            } catch (NumberFormatException unused2) {
                return new Pair<>(2, -1);
            }
        } catch (NumberFormatException unused3) {
            Double.parseDouble(str);
            return new Pair<>(12, -1);
        }
    }

    private static class ByteOrderedDataInputStream extends InputStream implements DataInput {
        private ByteOrder mByteOrder;
        private DataInputStream mDataInputStream;
        private final int mLength;
        private int mPosition;
        private static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        private static final ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;

        public ByteOrderedDataInputStream(InputStream inputStream) throws IOException {
            this.mByteOrder = ByteOrder.BIG_ENDIAN;
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            this.mDataInputStream = dataInputStream;
            int iAvailable = dataInputStream.available();
            this.mLength = iAvailable;
            this.mPosition = 0;
            this.mDataInputStream.mark(iAvailable);
        }

        public ByteOrderedDataInputStream(byte[] bArr) throws IOException {
            this(new ByteArrayInputStream(bArr));
        }

        public void setByteOrder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        public void seek(long j) throws IOException {
            int i = this.mPosition;
            if (i > j) {
                this.mPosition = 0;
                this.mDataInputStream.reset();
                this.mDataInputStream.mark(this.mLength);
            } else {
                j -= (long) i;
            }
            int i2 = (int) j;
            if (skipBytes(i2) != i2) {
                throw new IOException("Couldn't seek up to the byteCount");
            }
        }

        public int peek() {
            return this.mPosition;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return this.mDataInputStream.available();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.read();
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.mDataInputStream.read(bArr, i, i2);
            this.mPosition += i3;
            return i3;
        }

        @Override // java.io.DataInput
        public int readUnsignedByte() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.readUnsignedByte();
        }

        @Override // java.io.DataInput
        public String readLine() throws IOException {
            Log.d("ExifInterface", "Currently unsupported");
            return null;
        }

        @Override // java.io.DataInput
        public boolean readBoolean() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.readBoolean();
        }

        @Override // java.io.DataInput
        public char readChar() throws IOException {
            this.mPosition += 2;
            return this.mDataInputStream.readChar();
        }

        @Override // java.io.DataInput
        public String readUTF() throws IOException {
            this.mPosition += 2;
            return this.mDataInputStream.readUTF();
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.mPosition + i2;
            this.mPosition = i3;
            if (i3 > this.mLength) {
                throw new EOFException();
            }
            if (this.mDataInputStream.read(bArr, i, i2) != i2) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr) throws IOException {
            int length = this.mPosition + bArr.length;
            this.mPosition = length;
            if (length > this.mLength) {
                throw new EOFException();
            }
            if (this.mDataInputStream.read(bArr, 0, bArr.length) != bArr.length) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        @Override // java.io.DataInput
        public byte readByte() throws IOException {
            int i = this.mPosition + 1;
            this.mPosition = i;
            if (i > this.mLength) {
                throw new EOFException();
            }
            int i2 = this.mDataInputStream.read();
            if (i2 >= 0) {
                return (byte) i2;
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public short readShort() throws IOException {
            int i = this.mPosition + 2;
            this.mPosition = i;
            if (i > this.mLength) {
                throw new EOFException();
            }
            int i2 = this.mDataInputStream.read();
            int i3 = this.mDataInputStream.read();
            if ((i2 | i3) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.mByteOrder;
            if (byteOrder == LITTLE_ENDIAN) {
                return (short) ((i3 << 8) + i2);
            }
            if (byteOrder == BIG_ENDIAN) {
                return (short) ((i2 << 8) + i3);
            }
            throw new IOException("Invalid byte order: " + this.mByteOrder);
        }

        @Override // java.io.DataInput
        public int readInt() throws IOException {
            int i = this.mPosition + 4;
            this.mPosition = i;
            if (i > this.mLength) {
                throw new EOFException();
            }
            int i2 = this.mDataInputStream.read();
            int i3 = this.mDataInputStream.read();
            int i4 = this.mDataInputStream.read();
            int i5 = this.mDataInputStream.read();
            if ((i2 | i3 | i4 | i5) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.mByteOrder;
            if (byteOrder == LITTLE_ENDIAN) {
                return (i5 << 24) + (i4 << 16) + (i3 << 8) + i2;
            }
            if (byteOrder == BIG_ENDIAN) {
                return (i2 << 24) + (i3 << 16) + (i4 << 8) + i5;
            }
            throw new IOException("Invalid byte order: " + this.mByteOrder);
        }

        @Override // java.io.DataInput
        public int skipBytes(int i) throws IOException {
            int iMin = Math.min(i, this.mLength - this.mPosition);
            int iSkipBytes = 0;
            while (iSkipBytes < iMin) {
                iSkipBytes += this.mDataInputStream.skipBytes(iMin - iSkipBytes);
            }
            this.mPosition += iSkipBytes;
            return iSkipBytes;
        }

        @Override // java.io.DataInput
        public int readUnsignedShort() throws IOException {
            int i = this.mPosition + 2;
            this.mPosition = i;
            if (i > this.mLength) {
                throw new EOFException();
            }
            int i2 = this.mDataInputStream.read();
            int i3 = this.mDataInputStream.read();
            if ((i2 | i3) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.mByteOrder;
            if (byteOrder == LITTLE_ENDIAN) {
                return (i3 << 8) + i2;
            }
            if (byteOrder == BIG_ENDIAN) {
                return (i2 << 8) + i3;
            }
            throw new IOException("Invalid byte order: " + this.mByteOrder);
        }

        public long readUnsignedInt() throws IOException {
            return ((long) readInt()) & 4294967295L;
        }

        @Override // java.io.DataInput
        public long readLong() throws IOException {
            int i = this.mPosition + 8;
            this.mPosition = i;
            if (i > this.mLength) {
                throw new EOFException();
            }
            int i2 = this.mDataInputStream.read();
            int i3 = this.mDataInputStream.read();
            int i4 = this.mDataInputStream.read();
            int i5 = this.mDataInputStream.read();
            int i6 = this.mDataInputStream.read();
            int i7 = this.mDataInputStream.read();
            int i8 = this.mDataInputStream.read();
            int i9 = this.mDataInputStream.read();
            if ((i2 | i3 | i4 | i5 | i6 | i7 | i8 | i9) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.mByteOrder;
            if (byteOrder == LITTLE_ENDIAN) {
                return (((long) i9) << 56) + (((long) i8) << 48) + (((long) i7) << 40) + (((long) i6) << 32) + (((long) i5) << 24) + (((long) i4) << 16) + (((long) i3) << 8) + ((long) i2);
            }
            if (byteOrder == BIG_ENDIAN) {
                return (((long) i2) << 56) + (((long) i3) << 48) + (((long) i4) << 40) + (((long) i5) << 32) + (((long) i6) << 24) + (((long) i7) << 16) + (((long) i8) << 8) + ((long) i9);
            }
            throw new IOException("Invalid byte order: " + this.mByteOrder);
        }

        @Override // java.io.DataInput
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readInt());
        }

        @Override // java.io.DataInput
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readLong());
        }
    }

    private void swapBasedOnImageSize(int i, int i2) throws Throwable {
        if (this.mAttributes[i].isEmpty() || this.mAttributes[i2].isEmpty()) {
            return;
        }
        ExifAttribute exifAttribute = this.mAttributes[i].get("ImageLength");
        ExifAttribute exifAttribute2 = this.mAttributes[i].get("ImageWidth");
        ExifAttribute exifAttribute3 = this.mAttributes[i2].get("ImageLength");
        ExifAttribute exifAttribute4 = this.mAttributes[i2].get("ImageWidth");
        if (exifAttribute == null || exifAttribute2 == null || exifAttribute3 == null || exifAttribute4 == null) {
            return;
        }
        int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
        int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
        int intValue3 = exifAttribute3.getIntValue(this.mExifByteOrder);
        int intValue4 = exifAttribute4.getIntValue(this.mExifByteOrder);
        if (intValue >= intValue3 || intValue2 >= intValue4) {
            return;
        }
        HashMap<String, ExifAttribute>[] mapArr = this.mAttributes;
        HashMap<String, ExifAttribute> map = mapArr[i];
        mapArr[i] = mapArr[i2];
        mapArr[i2] = map;
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    private static long[] convertToLongArray(Object obj) {
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            long[] jArr = new long[iArr.length];
            for (int i = 0; i < iArr.length; i++) {
                jArr[i] = iArr[i];
            }
            return jArr;
        }
        if (obj instanceof long[]) {
            return (long[]) obj;
        }
        return null;
    }
}
