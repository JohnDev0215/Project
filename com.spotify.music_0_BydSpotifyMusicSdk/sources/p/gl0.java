package p;

import android.content.res.AssetManager;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.system.OsConstants;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

/* JADX INFO: loaded from: classes.dex */
public final class gl0 {
    public static final byte[] A;
    public static final String[] B;
    public static final int[] C;
    public static final byte[] D;
    public static final dl0 E;
    public static final dl0[][] F;
    public static final dl0[] G;
    public static final HashMap[] H;
    public static final HashMap[] I;
    public static final HashSet J;
    public static final HashMap K;
    public static final Charset L;
    public static final byte[] M;
    public static final byte[] N;
    public static final boolean l = Log.isLoggable("ExifInterface", 3);
    public static final int[] m;
    public static final int[] n;
    public static final byte[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final byte[] f124p;
    public static final byte[] q;
    public static final byte[] r;
    public static final byte[] s;
    public static final byte[] t;
    public static final byte[] u;
    public static final byte[] v;
    public static final byte[] w;
    public static final byte[] x;
    public static final byte[] y;
    public static final byte[] z;
    public final FileDescriptor a;
    public final AssetManager.AssetInputStream b;
    public int c;
    public final HashMap[] d;
    public final HashSet e;
    public ByteOrder f;
    public boolean g;
    public int h;
    public int i;
    public int j;
    public int k;

    static {
        Arrays.asList(1, 6, 3, 8);
        Arrays.asList(2, 7, 4, 5);
        m = new int[]{8, 8, 8};
        n = new int[]{8};
        o = new byte[]{-1, -40, -1};
        f124p = new byte[]{102, 116, 121, 112};
        q = new byte[]{109, 105, 102, 49};
        r = new byte[]{104, 101, 105, 99};
        s = new byte[]{79, 76, 89, 77, 80, 0};
        t = new byte[]{79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
        u = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};
        v = new byte[]{101, 88, 73, 102};
        w = new byte[]{73, 72, 68, 82};
        x = new byte[]{73, 69, 78, 68};
        y = new byte[]{82, 73, 70, 70};
        z = new byte[]{87, 69, 66, 80};
        A = new byte[]{69, 88, 73, 70};
        "VP8X".getBytes(Charset.defaultCharset());
        "VP8L".getBytes(Charset.defaultCharset());
        "VP8 ".getBytes(Charset.defaultCharset());
        "ANIM".getBytes(Charset.defaultCharset());
        "ANMF".getBytes(Charset.defaultCharset());
        B = new String[]{"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
        C = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
        D = new byte[]{65, 83, 67, 73, 73, 0, 0, 0};
        dl0[] dl0VarArr = {new dl0("NewSubfileType", 254, 4), new dl0("SubfileType", 255, 4), new dl0(256, 3, 4, "ImageWidth"), new dl0(257, 3, 4, "ImageLength"), new dl0("BitsPerSample", 258, 3), new dl0("Compression", 259, 3), new dl0("PhotometricInterpretation", 262, 3), new dl0("ImageDescription", 270, 2), new dl0("Make", 271, 2), new dl0("Model", 272, 2), new dl0(273, 3, 4, "StripOffsets"), new dl0("Orientation", 274, 3), new dl0("SamplesPerPixel", 277, 3), new dl0(278, 3, 4, "RowsPerStrip"), new dl0(279, 3, 4, "StripByteCounts"), new dl0("XResolution", 282, 5), new dl0("YResolution", 283, 5), new dl0("PlanarConfiguration", 284, 3), new dl0("ResolutionUnit", 296, 3), new dl0("TransferFunction", 301, 3), new dl0("Software", 305, 2), new dl0("DateTime", 306, 2), new dl0("Artist", 315, 2), new dl0("WhitePoint", 318, 5), new dl0("PrimaryChromaticities", 319, 5), new dl0("SubIFDPointer", 330, 4), new dl0("JPEGInterchangeFormat", 513, 4), new dl0("JPEGInterchangeFormatLength", 514, 4), new dl0("YCbCrCoefficients", 529, 5), new dl0("YCbCrSubSampling", 530, 3), new dl0("YCbCrPositioning", 531, 3), new dl0("ReferenceBlackWhite", 532, 5), new dl0("Copyright", 33432, 2), new dl0("ExifIFDPointer", 34665, 4), new dl0("GPSInfoIFDPointer", 34853, 4), new dl0("SensorTopBorder", 4, 4), new dl0("SensorLeftBorder", 5, 4), new dl0("SensorBottomBorder", 6, 4), new dl0("SensorRightBorder", 7, 4), new dl0("ISO", 23, 3), new dl0("JpgFromRaw", 46, 7), new dl0("Xmp", 700, 1)};
        dl0[] dl0VarArr2 = {new dl0("ExposureTime", 33434, 5), new dl0("FNumber", 33437, 5), new dl0("ExposureProgram", 34850, 3), new dl0("SpectralSensitivity", 34852, 2), new dl0("PhotographicSensitivity", 34855, 3), new dl0("OECF", 34856, 7), new dl0("SensitivityType", 34864, 3), new dl0("StandardOutputSensitivity", 34865, 4), new dl0("RecommendedExposureIndex", 34866, 4), new dl0("ISOSpeed", 34867, 4), new dl0("ISOSpeedLatitudeyyy", 34868, 4), new dl0("ISOSpeedLatitudezzz", 34869, 4), new dl0("ExifVersion", 36864, 2), new dl0("DateTimeOriginal", 36867, 2), new dl0("DateTimeDigitized", 36868, 2), new dl0("OffsetTime", 36880, 2), new dl0("OffsetTimeOriginal", 36881, 2), new dl0("OffsetTimeDigitized", 36882, 2), new dl0("ComponentsConfiguration", 37121, 7), new dl0("CompressedBitsPerPixel", 37122, 5), new dl0("ShutterSpeedValue", 37377, 10), new dl0("ApertureValue", 37378, 5), new dl0("BrightnessValue", 37379, 10), new dl0("ExposureBiasValue", 37380, 10), new dl0("MaxApertureValue", 37381, 5), new dl0("SubjectDistance", 37382, 5), new dl0("MeteringMode", 37383, 3), new dl0("LightSource", 37384, 3), new dl0("Flash", 37385, 3), new dl0("FocalLength", 37386, 5), new dl0("SubjectArea", 37396, 3), new dl0("MakerNote", 37500, 7), new dl0("UserComment", 37510, 7), new dl0("SubSecTime", 37520, 2), new dl0("SubSecTimeOriginal", 37521, 2), new dl0("SubSecTimeDigitized", 37522, 2), new dl0("FlashpixVersion", 40960, 7), new dl0("ColorSpace", 40961, 3), new dl0(40962, 3, 4, "PixelXDimension"), new dl0(40963, 3, 4, "PixelYDimension"), new dl0("RelatedSoundFile", 40964, 2), new dl0("InteroperabilityIFDPointer", 40965, 4), new dl0("FlashEnergy", 41483, 5), new dl0("SpatialFrequencyResponse", 41484, 7), new dl0("FocalPlaneXResolution", 41486, 5), new dl0("FocalPlaneYResolution", 41487, 5), new dl0("FocalPlaneResolutionUnit", 41488, 3), new dl0("SubjectLocation", 41492, 3), new dl0("ExposureIndex", 41493, 5), new dl0("SensingMethod", 41495, 3), new dl0("FileSource", 41728, 7), new dl0("SceneType", 41729, 7), new dl0("CFAPattern", 41730, 7), new dl0("CustomRendered", 41985, 3), new dl0("ExposureMode", 41986, 3), new dl0("WhiteBalance", 41987, 3), new dl0("DigitalZoomRatio", 41988, 5), new dl0("FocalLengthIn35mmFilm", 41989, 3), new dl0("SceneCaptureType", 41990, 3), new dl0("GainControl", 41991, 3), new dl0("Contrast", 41992, 3), new dl0("Saturation", 41993, 3), new dl0("Sharpness", 41994, 3), new dl0("DeviceSettingDescription", 41995, 7), new dl0("SubjectDistanceRange", 41996, 3), new dl0("ImageUniqueID", 42016, 2), new dl0("CameraOwnerName", 42032, 2), new dl0("BodySerialNumber", 42033, 2), new dl0("LensSpecification", 42034, 5), new dl0("LensMake", 42035, 2), new dl0("LensModel", 42036, 2), new dl0("Gamma", 42240, 5), new dl0("DNGVersion", 50706, 1), new dl0(50720, 3, 4, "DefaultCropSize")};
        dl0[] dl0VarArr3 = {new dl0("GPSVersionID", 0, 1), new dl0("GPSLatitudeRef", 1, 2), new dl0(2, 5, 10, "GPSLatitude"), new dl0("GPSLongitudeRef", 3, 2), new dl0(4, 5, 10, "GPSLongitude"), new dl0("GPSAltitudeRef", 5, 1), new dl0("GPSAltitude", 6, 5), new dl0("GPSTimeStamp", 7, 5), new dl0("GPSSatellites", 8, 2), new dl0("GPSStatus", 9, 2), new dl0("GPSMeasureMode", 10, 2), new dl0("GPSDOP", 11, 5), new dl0("GPSSpeedRef", 12, 2), new dl0("GPSSpeed", 13, 5), new dl0("GPSTrackRef", 14, 2), new dl0("GPSTrack", 15, 5), new dl0("GPSImgDirectionRef", 16, 2), new dl0("GPSImgDirection", 17, 5), new dl0("GPSMapDatum", 18, 2), new dl0("GPSDestLatitudeRef", 19, 2), new dl0("GPSDestLatitude", 20, 5), new dl0("GPSDestLongitudeRef", 21, 2), new dl0("GPSDestLongitude", 22, 5), new dl0("GPSDestBearingRef", 23, 2), new dl0("GPSDestBearing", 24, 5), new dl0("GPSDestDistanceRef", 25, 2), new dl0("GPSDestDistance", 26, 5), new dl0("GPSProcessingMethod", 27, 7), new dl0("GPSAreaInformation", 28, 7), new dl0("GPSDateStamp", 29, 2), new dl0("GPSDifferential", 30, 3), new dl0("GPSHPositioningError", 31, 5)};
        dl0[] dl0VarArr4 = {new dl0("InteroperabilityIndex", 1, 2)};
        dl0[] dl0VarArr5 = {new dl0("NewSubfileType", 254, 4), new dl0("SubfileType", 255, 4), new dl0(256, 3, 4, "ThumbnailImageWidth"), new dl0(257, 3, 4, "ThumbnailImageLength"), new dl0("BitsPerSample", 258, 3), new dl0("Compression", 259, 3), new dl0("PhotometricInterpretation", 262, 3), new dl0("ImageDescription", 270, 2), new dl0("Make", 271, 2), new dl0("Model", 272, 2), new dl0(273, 3, 4, "StripOffsets"), new dl0("ThumbnailOrientation", 274, 3), new dl0("SamplesPerPixel", 277, 3), new dl0(278, 3, 4, "RowsPerStrip"), new dl0(279, 3, 4, "StripByteCounts"), new dl0("XResolution", 282, 5), new dl0("YResolution", 283, 5), new dl0("PlanarConfiguration", 284, 3), new dl0("ResolutionUnit", 296, 3), new dl0("TransferFunction", 301, 3), new dl0("Software", 305, 2), new dl0("DateTime", 306, 2), new dl0("Artist", 315, 2), new dl0("WhitePoint", 318, 5), new dl0("PrimaryChromaticities", 319, 5), new dl0("SubIFDPointer", 330, 4), new dl0("JPEGInterchangeFormat", 513, 4), new dl0("JPEGInterchangeFormatLength", 514, 4), new dl0("YCbCrCoefficients", 529, 5), new dl0("YCbCrSubSampling", 530, 3), new dl0("YCbCrPositioning", 531, 3), new dl0("ReferenceBlackWhite", 532, 5), new dl0("Copyright", 33432, 2), new dl0("ExifIFDPointer", 34665, 4), new dl0("GPSInfoIFDPointer", 34853, 4), new dl0("DNGVersion", 50706, 1), new dl0(50720, 3, 4, "DefaultCropSize")};
        E = new dl0("StripOffsets", 273, 3);
        F = new dl0[][]{dl0VarArr, dl0VarArr2, dl0VarArr3, dl0VarArr4, dl0VarArr5, dl0VarArr, new dl0[]{new dl0("ThumbnailImage", 256, 7), new dl0("CameraSettingsIFDPointer", 8224, 4), new dl0("ImageProcessingIFDPointer", 8256, 4)}, new dl0[]{new dl0("PreviewImageStart", 257, 4), new dl0("PreviewImageLength", 258, 4)}, new dl0[]{new dl0("AspectFrame", 4371, 3)}, new dl0[]{new dl0("ColorSpace", 55, 3)}};
        G = new dl0[]{new dl0("SubIFDPointer", 330, 4), new dl0("ExifIFDPointer", 34665, 4), new dl0("GPSInfoIFDPointer", 34853, 4), new dl0("InteroperabilityIFDPointer", 40965, 4), new dl0("CameraSettingsIFDPointer", 8224, 1), new dl0("ImageProcessingIFDPointer", 8256, 1)};
        H = new HashMap[10];
        I = new HashMap[10];
        J = new HashSet(Arrays.asList("FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"));
        K = new HashMap();
        Charset charsetForName = Charset.forName("US-ASCII");
        L = charsetForName;
        M = "Exif\u0000\u0000".getBytes(charsetForName);
        N = "http://ns.adobe.com/xap/1.0/\u0000".getBytes(charsetForName);
        Locale locale = Locale.US;
        new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", locale).setTimeZone(TimeZone.getTimeZone("UTC"));
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale).setTimeZone(TimeZone.getTimeZone("UTC"));
        int i = 0;
        while (true) {
            dl0[][] dl0VarArr6 = F;
            if (i >= dl0VarArr6.length) {
                HashMap map = K;
                dl0[] dl0VarArr7 = G;
                map.put(Integer.valueOf(dl0VarArr7[0].a), 5);
                map.put(Integer.valueOf(dl0VarArr7[1].a), 1);
                map.put(Integer.valueOf(dl0VarArr7[2].a), 2);
                map.put(Integer.valueOf(dl0VarArr7[3].a), 3);
                map.put(Integer.valueOf(dl0VarArr7[4].a), 7);
                map.put(Integer.valueOf(dl0VarArr7[5].a), 8);
                Pattern.compile(".*[1-9].*");
                Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
                Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                return;
            }
            H[i] = new HashMap();
            I[i] = new HashMap();
            for (dl0 dl0Var : dl0VarArr6[i]) {
                H[i].put(Integer.valueOf(dl0Var.a), dl0Var);
                I[i].put(dl0Var.b, dl0Var);
            }
            i++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public gl0(hl0 hl0Var) throws Throwable {
        dl0[][] dl0VarArr = F;
        this.d = new HashMap[dl0VarArr.length];
        this.e = new HashSet(dl0VarArr.length);
        this.f = ByteOrder.BIG_ENDIAN;
        if (hl0Var instanceof AssetManager.AssetInputStream) {
            this.b = (AssetManager.AssetInputStream) hl0Var;
            this.a = null;
        } else if (hl0Var instanceof FileInputStream) {
            FileInputStream fileInputStream = (FileInputStream) hl0Var;
            try {
                il0.c(fileInputStream.getFD(), 0L, OsConstants.SEEK_CUR);
                this.b = null;
                this.a = fileInputStream.getFD();
            } catch (Exception unused) {
                this.b = null;
                this.a = null;
            }
        } else {
            this.b = null;
            this.a = null;
        }
        boolean z2 = l;
        for (int i = 0; i < dl0VarArr.length; i++) {
            try {
                this.d[i] = new HashMap();
            } catch (IOException | UnsupportedOperationException unused2) {
                a();
                if (!z2) {
                    return;
                }
            } catch (Throwable th) {
                a();
                if (z2) {
                    p();
                }
                throw th;
            }
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(hl0Var, 5000);
        int iF = f(bufferedInputStream);
        this.c = iF;
        if (iF == 4 || iF == 9 || iF == 13 || iF == 14) {
            bl0 bl0Var = new bl0(bufferedInputStream);
            int i2 = this.c;
            if (i2 == 4) {
                e(bl0Var, 0, 0);
            } else if (i2 == 13) {
                h(bl0Var);
            } else if (i2 == 9) {
                i(bl0Var);
            } else if (i2 == 14) {
                l(bl0Var);
            }
        } else {
            fl0 fl0Var = new fl0(bufferedInputStream);
            int i3 = this.c;
            if (i3 == 12) {
                d(fl0Var);
            } else if (i3 == 7) {
                g(fl0Var);
            } else if (i3 == 10) {
                k(fl0Var);
            } else {
                j(fl0Var);
            }
            fl0Var.c(this.h);
            u(fl0Var);
        }
        a();
        if (!z2) {
            return;
        }
        p();
    }

    public static ByteOrder q(bl0 bl0Var) throws IOException {
        short s2 = bl0Var.readShort();
        if (s2 == 18761) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        if (s2 == 19789) {
            return ByteOrder.BIG_ENDIAN;
        }
        throw new IOException("Invalid byte order: " + Integer.toHexString(s2));
    }

    public final void a() {
        String strB = b("DateTimeOriginal");
        HashMap[] mapArr = this.d;
        if (strB != null && b("DateTime") == null) {
            HashMap map = mapArr[0];
            byte[] bytes = strB.concat("\u0000").getBytes(L);
            map.put("DateTime", new cl0(bytes, 2, bytes.length));
        }
        if (b("ImageWidth") == null) {
            mapArr[0].put("ImageWidth", cl0.a(0L, this.f));
        }
        if (b("ImageLength") == null) {
            mapArr[0].put("ImageLength", cl0.a(0L, this.f));
        }
        if (b("Orientation") == null) {
            mapArr[0].put("Orientation", cl0.a(0L, this.f));
        }
        if (b("LightSource") == null) {
            mapArr[1].put("LightSource", cl0.a(0L, this.f));
        }
    }

    public final String b(String str) {
        cl0 cl0VarC = c(str);
        if (cl0VarC != null) {
            if (!J.contains(str)) {
                return cl0VarC.f(this.f);
            }
            if (str.equals("GPSTimeStamp")) {
                int i = cl0VarC.a;
                if (i != 5 && i != 10) {
                    return null;
                }
                el0[] el0VarArr = (el0[]) cl0VarC.g(this.f);
                if (el0VarArr == null || el0VarArr.length != 3) {
                    Arrays.toString(el0VarArr);
                    return null;
                }
                el0 el0Var = el0VarArr[0];
                Integer numValueOf = Integer.valueOf((int) (el0Var.a / el0Var.b));
                el0 el0Var2 = el0VarArr[1];
                Integer numValueOf2 = Integer.valueOf((int) (el0Var2.a / el0Var2.b));
                el0 el0Var3 = el0VarArr[2];
                return String.format("%02d:%02d:%02d", numValueOf, numValueOf2, Integer.valueOf((int) (el0Var3.a / el0Var3.b)));
            }
            try {
                return Double.toString(cl0VarC.d(this.f));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public final cl0 c(String str) {
        if ("ISOSpeedRatings".equals(str)) {
            str = "PhotographicSensitivity";
        }
        for (int i = 0; i < F.length; i++) {
            cl0 cl0Var = (cl0) this.d[i].get(str);
            if (cl0Var != null) {
                return cl0Var;
            }
        }
        return null;
    }

    public final void d(fl0 fl0Var) throws IOException {
        String strExtractMetadata;
        String strExtractMetadata2;
        String strExtractMetadata3;
        if (Build.VERSION.SDK_INT < 28) {
            throw new UnsupportedOperationException("Reading EXIF from HEIF files is supported from SDK 28 and above");
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                jl0.a(mediaMetadataRetriever, new al0(fl0Var));
                String strExtractMetadata4 = mediaMetadataRetriever.extractMetadata(33);
                String strExtractMetadata5 = mediaMetadataRetriever.extractMetadata(34);
                String strExtractMetadata6 = mediaMetadataRetriever.extractMetadata(26);
                String strExtractMetadata7 = mediaMetadataRetriever.extractMetadata(17);
                if ("yes".equals(strExtractMetadata6)) {
                    strExtractMetadata = mediaMetadataRetriever.extractMetadata(29);
                    strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(30);
                    strExtractMetadata3 = mediaMetadataRetriever.extractMetadata(31);
                } else if ("yes".equals(strExtractMetadata7)) {
                    strExtractMetadata = mediaMetadataRetriever.extractMetadata(18);
                    strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
                    strExtractMetadata3 = mediaMetadataRetriever.extractMetadata(24);
                } else {
                    strExtractMetadata = null;
                    strExtractMetadata2 = null;
                    strExtractMetadata3 = null;
                }
                HashMap[] mapArr = this.d;
                if (strExtractMetadata != null) {
                    mapArr[0].put("ImageWidth", cl0.c(Integer.parseInt(strExtractMetadata), this.f));
                }
                if (strExtractMetadata2 != null) {
                    mapArr[0].put("ImageLength", cl0.c(Integer.parseInt(strExtractMetadata2), this.f));
                }
                if (strExtractMetadata3 != null) {
                    int i = Integer.parseInt(strExtractMetadata3);
                    mapArr[0].put("Orientation", cl0.c(i != 90 ? i != 180 ? i != 270 ? 1 : 8 : 3 : 6, this.f));
                }
                if (strExtractMetadata4 != null && strExtractMetadata5 != null) {
                    int i2 = Integer.parseInt(strExtractMetadata4);
                    int i3 = Integer.parseInt(strExtractMetadata5);
                    if (i3 <= 6) {
                        throw new IOException("Invalid exif length");
                    }
                    fl0Var.c(i2);
                    byte[] bArr = new byte[6];
                    if (fl0Var.read(bArr) != 6) {
                        throw new IOException("Can't read identifier");
                    }
                    int i4 = i2 + 6;
                    int i5 = i3 - 6;
                    if (!Arrays.equals(bArr, M)) {
                        throw new IOException("Invalid identifier");
                    }
                    byte[] bArr2 = new byte[i5];
                    if (fl0Var.read(bArr2) != i5) {
                        throw new IOException("Can't read exif");
                    }
                    this.h = i4;
                    r(bArr2, 0);
                }
                mediaMetadataRetriever.release();
            } catch (RuntimeException unused) {
                throw new UnsupportedOperationException("Failed to read EXIF from HEIF file. Given stream is either malformed or unsupported.");
            }
        } catch (Throwable th) {
            mediaMetadataRetriever.release();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0153 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0108 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006b A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x014c A[LOOP:0: B:10:0x0024->B:82:0x014c, LOOP_END] */
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
    public final void e(p.bl0 r23, int r24, int r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 472
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.gl0.e(p.bl0, int, int):void");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:(2:139|12)|(3:137|13|(6:16|(2:18|(1:20))(1:23)|24|(1:26)|27|(1:29)(2:30|(4:36|(3:39|(1:41)(2:42|(1:44))|(1:159)(3:156|47|48))(2:38|157)|49|31))))|15|(8:135|58|143|59|60|(1:66)(1:65)|67|(1:69))|141|81|(6:133|82|83|(1:85)(1:86)|87|(1:89))|102|(2:103|(2:105|(4:149|107|(2:108|(2:110|(1:151)(1:113))(3:150|114|(2:115|(2:117|(1:153)(1:120))(2:152|121))))|119)(1:123))(2:148|124))) */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0115, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0116, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0118, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x015a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00db A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010d A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int f(java.io.BufferedInputStream r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.gl0.f(java.io.BufferedInputStream):int");
    }

    public final void g(fl0 fl0Var) throws Throwable {
        int i;
        int i2;
        j(fl0Var);
        HashMap[] mapArr = this.d;
        cl0 cl0Var = (cl0) mapArr[1].get("MakerNote");
        if (cl0Var != null) {
            fl0 fl0Var2 = new fl0(cl0Var.d);
            fl0Var2.e = this.f;
            byte[] bArr = s;
            byte[] bArr2 = new byte[bArr.length];
            fl0Var2.readFully(bArr2);
            fl0Var2.c(0L);
            byte[] bArr3 = t;
            byte[] bArr4 = new byte[bArr3.length];
            fl0Var2.readFully(bArr4);
            if (Arrays.equals(bArr2, bArr)) {
                fl0Var2.c(8L);
            } else if (Arrays.equals(bArr4, bArr3)) {
                fl0Var2.c(12L);
            }
            s(fl0Var2, 6);
            cl0 cl0Var2 = (cl0) mapArr[7].get("PreviewImageStart");
            cl0 cl0Var3 = (cl0) mapArr[7].get("PreviewImageLength");
            if (cl0Var2 != null && cl0Var3 != null) {
                mapArr[5].put("JPEGInterchangeFormat", cl0Var2);
                mapArr[5].put("JPEGInterchangeFormatLength", cl0Var3);
            }
            cl0 cl0Var4 = (cl0) mapArr[8].get("AspectFrame");
            if (cl0Var4 != null) {
                int[] iArr = (int[]) cl0Var4.g(this.f);
                if (iArr == null || iArr.length != 4) {
                    Arrays.toString(iArr);
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
                cl0 cl0VarC = cl0.c(i5, this.f);
                cl0 cl0VarC2 = cl0.c(i6, this.f);
                mapArr[0].put("ImageWidth", cl0VarC);
                mapArr[0].put("ImageLength", cl0VarC2);
            }
        }
    }

    public final void h(bl0 bl0Var) throws Throwable {
        if (l) {
            Objects.toString(bl0Var);
        }
        bl0Var.e = ByteOrder.BIG_ENDIAN;
        byte[] bArr = u;
        bl0Var.b(bArr.length);
        int length = bArr.length;
        while (true) {
            try {
                int i = bl0Var.readInt();
                byte[] bArr2 = new byte[4];
                if (bl0Var.read(bArr2) != 4) {
                    throw new IOException("Encountered invalid length while parsing PNG chunktype");
                }
                int i2 = length + 8;
                if (i2 == 16 && !Arrays.equals(bArr2, w)) {
                    throw new IOException("Encountered invalid PNG file--IHDR chunk should appearas the first chunk");
                }
                if (Arrays.equals(bArr2, x)) {
                    return;
                }
                if (Arrays.equals(bArr2, v)) {
                    byte[] bArr3 = new byte[i];
                    if (bl0Var.read(bArr3) != i) {
                        throw new IOException("Failed to read given length for given PNG chunk type: " + ln1.b(bArr2));
                    }
                    int i3 = bl0Var.readInt();
                    CRC32 crc32 = new CRC32();
                    crc32.update(bArr2);
                    crc32.update(bArr3);
                    if (((int) crc32.getValue()) == i3) {
                        this.h = i2;
                        r(bArr3, 0);
                        x();
                        u(new bl0(bArr3));
                        return;
                    }
                    throw new IOException("Encountered invalid CRC value for PNG-EXIF chunk.\n recorded CRC value: " + i3 + ", calculated CRC value: " + crc32.getValue());
                }
                int i4 = i + 4;
                bl0Var.b(i4);
                length = i2 + i4;
            } catch (EOFException unused) {
                throw new IOException("Encountered corrupt PNG file.");
            }
        }
    }

    public final void i(bl0 bl0Var) throws Throwable {
        if (l) {
            Objects.toString(bl0Var);
        }
        bl0Var.b(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[4];
        bl0Var.read(bArr);
        bl0Var.read(bArr2);
        bl0Var.read(bArr3);
        int i = ByteBuffer.wrap(bArr).getInt();
        int i2 = ByteBuffer.wrap(bArr2).getInt();
        int i3 = ByteBuffer.wrap(bArr3).getInt();
        byte[] bArr4 = new byte[i2];
        bl0Var.b(i - bl0Var.k);
        bl0Var.read(bArr4);
        e(new bl0(bArr4), i, 5);
        bl0Var.b(i3 - bl0Var.k);
        bl0Var.e = ByteOrder.BIG_ENDIAN;
        int i4 = bl0Var.readInt();
        for (int i5 = 0; i5 < i4; i5++) {
            int unsignedShort = bl0Var.readUnsignedShort();
            int unsignedShort2 = bl0Var.readUnsignedShort();
            if (unsignedShort == E.a) {
                short s2 = bl0Var.readShort();
                short s3 = bl0Var.readShort();
                cl0 cl0VarC = cl0.c(s2, this.f);
                cl0 cl0VarC2 = cl0.c(s3, this.f);
                HashMap[] mapArr = this.d;
                mapArr[0].put("ImageLength", cl0VarC);
                mapArr[0].put("ImageWidth", cl0VarC2);
                return;
            }
            bl0Var.b(unsignedShort2);
        }
    }

    public final void j(fl0 fl0Var) throws Throwable {
        o(fl0Var);
        s(fl0Var, 0);
        w(fl0Var, 0);
        w(fl0Var, 5);
        w(fl0Var, 4);
        x();
        if (this.c == 8) {
            HashMap[] mapArr = this.d;
            cl0 cl0Var = (cl0) mapArr[1].get("MakerNote");
            if (cl0Var != null) {
                fl0 fl0Var2 = new fl0(cl0Var.d);
                fl0Var2.e = this.f;
                fl0Var2.b(6);
                s(fl0Var2, 9);
                cl0 cl0Var2 = (cl0) mapArr[9].get("ColorSpace");
                if (cl0Var2 != null) {
                    mapArr[1].put("ColorSpace", cl0Var2);
                }
            }
        }
    }

    public final void k(fl0 fl0Var) throws Throwable {
        if (l) {
            Objects.toString(fl0Var);
        }
        j(fl0Var);
        HashMap[] mapArr = this.d;
        cl0 cl0Var = (cl0) mapArr[0].get("JpgFromRaw");
        if (cl0Var != null) {
            e(new bl0(cl0Var.d), (int) cl0Var.c, 5);
        }
        cl0 cl0Var2 = (cl0) mapArr[0].get("ISO");
        cl0 cl0Var3 = (cl0) mapArr[1].get("PhotographicSensitivity");
        if (cl0Var2 == null || cl0Var3 != null) {
            return;
        }
        mapArr[1].put("PhotographicSensitivity", cl0Var2);
    }

    public final void l(bl0 bl0Var) throws Throwable {
        if (l) {
            Objects.toString(bl0Var);
        }
        bl0Var.e = ByteOrder.LITTLE_ENDIAN;
        bl0Var.b(y.length);
        int i = bl0Var.readInt() + 8;
        byte[] bArr = z;
        bl0Var.b(bArr.length);
        int length = bArr.length + 8;
        while (true) {
            try {
                byte[] bArr2 = new byte[4];
                if (bl0Var.read(bArr2) != 4) {
                    throw new IOException("Encountered invalid length while parsing WebP chunktype");
                }
                int i2 = bl0Var.readInt();
                int i3 = length + 8;
                if (Arrays.equals(A, bArr2)) {
                    byte[] bArr3 = new byte[i2];
                    if (bl0Var.read(bArr3) == i2) {
                        this.h = i3;
                        r(bArr3, 0);
                        u(new bl0(bArr3));
                        return;
                    } else {
                        throw new IOException("Failed to read given length for given PNG chunk type: " + ln1.b(bArr2));
                    }
                }
                if (i2 % 2 == 1) {
                    i2++;
                }
                length = i3 + i2;
                if (length == i) {
                    return;
                }
                if (length > i) {
                    throw new IOException("Encountered WebP file with invalid chunk size");
                }
                bl0Var.b(i2);
            } catch (EOFException unused) {
                throw new IOException("Encountered corrupt WebP file.");
            }
        }
    }

    public final void m(bl0 bl0Var, HashMap map) throws Throwable {
        cl0 cl0Var = (cl0) map.get("JPEGInterchangeFormat");
        cl0 cl0Var2 = (cl0) map.get("JPEGInterchangeFormatLength");
        if (cl0Var == null || cl0Var2 == null) {
            return;
        }
        int iE = cl0Var.e(this.f);
        int iE2 = cl0Var2.e(this.f);
        if (this.c == 7) {
            iE += this.i;
        }
        if (iE <= 0 || iE2 <= 0 || this.b != null || this.a != null) {
            return;
        }
        bl0Var.skip(iE);
        bl0Var.read(new byte[iE2]);
    }

    public final boolean n(HashMap map) {
        cl0 cl0Var = (cl0) map.get("ImageLength");
        cl0 cl0Var2 = (cl0) map.get("ImageWidth");
        if (cl0Var == null || cl0Var2 == null) {
            return false;
        }
        return cl0Var.e(this.f) <= 512 && cl0Var2.e(this.f) <= 512;
    }

    public final void o(bl0 bl0Var) throws IOException {
        ByteOrder byteOrderQ = q(bl0Var);
        this.f = byteOrderQ;
        bl0Var.e = byteOrderQ;
        int unsignedShort = bl0Var.readUnsignedShort();
        int i = this.c;
        if (i != 7 && i != 10 && unsignedShort != 42) {
            throw new IOException("Invalid start code: " + Integer.toHexString(unsignedShort));
        }
        int i2 = bl0Var.readInt();
        if (i2 < 8) {
            throw new IOException(u4.e("Invalid first Ifd offset: ", i2));
        }
        int i3 = i2 - 8;
        if (i3 > 0) {
            bl0Var.b(i3);
        }
    }

    public final void p() throws Throwable {
        int i = 0;
        while (true) {
            HashMap[] mapArr = this.d;
            if (i >= mapArr.length) {
                return;
            }
            mapArr[i].size();
            for (Map.Entry entry : mapArr[i].entrySet()) {
                cl0 cl0Var = (cl0) entry.getValue();
                cl0Var.toString();
                cl0Var.f(this.f);
            }
            i++;
        }
    }

    public final void r(byte[] bArr, int i) throws IOException {
        fl0 fl0Var = new fl0(bArr);
        o(fl0Var);
        s(fl0Var, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x019e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void s(p.fl0 r31, int r32) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 657
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.gl0.s(p.fl0, int):void");
    }

    public final void t(int i, String str, String str2) {
        HashMap[] mapArr = this.d;
        if (mapArr[i].isEmpty() || mapArr[i].get(str) == null) {
            return;
        }
        HashMap map = mapArr[i];
        map.put(str2, map.get(str));
        mapArr[i].remove(str);
    }

    public final void u(bl0 bl0Var) throws Throwable {
        cl0 cl0Var;
        HashMap map = this.d[4];
        cl0 cl0Var2 = (cl0) map.get("Compression");
        if (cl0Var2 == null) {
            m(bl0Var, map);
            return;
        }
        int iE = cl0Var2.e(this.f);
        if (iE != 1) {
            if (iE == 6) {
                m(bl0Var, map);
                return;
            } else if (iE != 7) {
                return;
            }
        }
        cl0 cl0Var3 = (cl0) map.get("BitsPerSample");
        if (cl0Var3 != null) {
            int[] iArr = (int[]) cl0Var3.g(this.f);
            int[] iArr2 = m;
            if (!Arrays.equals(iArr2, iArr)) {
                if (this.c != 3 || (cl0Var = (cl0) map.get("PhotometricInterpretation")) == null) {
                    return;
                }
                int iE2 = cl0Var.e(this.f);
                if ((iE2 != 1 || !Arrays.equals(iArr, n)) && (iE2 != 6 || !Arrays.equals(iArr, iArr2))) {
                    return;
                }
            }
            cl0 cl0Var4 = (cl0) map.get("StripOffsets");
            cl0 cl0Var5 = (cl0) map.get("StripByteCounts");
            if (cl0Var4 == null || cl0Var5 == null) {
                return;
            }
            long[] jArrE = ln1.e(cl0Var4.g(this.f));
            long[] jArrE2 = ln1.e(cl0Var5.g(this.f));
            if (jArrE == null || jArrE.length == 0 || jArrE2 == null || jArrE2.length == 0 || jArrE.length != jArrE2.length) {
                return;
            }
            long j = 0;
            for (long j2 : jArrE2) {
                j += j2;
            }
            byte[] bArr = new byte[(int) j];
            this.g = true;
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < jArrE.length; i3++) {
                int i4 = (int) jArrE[i3];
                int i5 = (int) jArrE2[i3];
                if (i3 < jArrE.length - 1 && i4 + i5 != jArrE[i3 + 1]) {
                    this.g = false;
                }
                int i6 = i4 - i;
                if (i6 < 0) {
                    return;
                }
                long j3 = i6;
                if (bl0Var.skip(j3) != j3) {
                    return;
                }
                int i7 = i + i6;
                byte[] bArr2 = new byte[i5];
                if (bl0Var.read(bArr2) != i5) {
                    return;
                }
                i = i7 + i5;
                System.arraycopy(bArr2, 0, bArr, i2, i5);
                i2 += i5;
            }
            if (this.g) {
                long j4 = jArrE[0];
            }
        }
    }

    public final void v(int i, int i2) throws Throwable {
        HashMap[] mapArr = this.d;
        if (mapArr[i].isEmpty() || mapArr[i2].isEmpty()) {
            return;
        }
        cl0 cl0Var = (cl0) mapArr[i].get("ImageLength");
        cl0 cl0Var2 = (cl0) mapArr[i].get("ImageWidth");
        cl0 cl0Var3 = (cl0) mapArr[i2].get("ImageLength");
        cl0 cl0Var4 = (cl0) mapArr[i2].get("ImageWidth");
        if (cl0Var == null || cl0Var2 == null || cl0Var3 == null || cl0Var4 == null) {
            return;
        }
        int iE = cl0Var.e(this.f);
        int iE2 = cl0Var2.e(this.f);
        int iE3 = cl0Var3.e(this.f);
        int iE4 = cl0Var4.e(this.f);
        if (iE >= iE3 || iE2 >= iE4) {
            return;
        }
        HashMap map = mapArr[i];
        mapArr[i] = mapArr[i2];
        mapArr[i2] = map;
    }

    public final void w(fl0 fl0Var, int i) throws Throwable {
        cl0 cl0VarC;
        cl0 cl0VarC2;
        HashMap[] mapArr = this.d;
        cl0 cl0Var = (cl0) mapArr[i].get("DefaultCropSize");
        cl0 cl0Var2 = (cl0) mapArr[i].get("SensorTopBorder");
        cl0 cl0Var3 = (cl0) mapArr[i].get("SensorLeftBorder");
        cl0 cl0Var4 = (cl0) mapArr[i].get("SensorBottomBorder");
        cl0 cl0Var5 = (cl0) mapArr[i].get("SensorRightBorder");
        if (cl0Var != null) {
            if (cl0Var.a == 5) {
                el0[] el0VarArr = (el0[]) cl0Var.g(this.f);
                if (el0VarArr == null || el0VarArr.length != 2) {
                    Arrays.toString(el0VarArr);
                    return;
                } else {
                    cl0VarC = cl0.b(el0VarArr[0], this.f);
                    cl0VarC2 = cl0.b(el0VarArr[1], this.f);
                }
            } else {
                int[] iArr = (int[]) cl0Var.g(this.f);
                if (iArr == null || iArr.length != 2) {
                    Arrays.toString(iArr);
                    return;
                } else {
                    cl0VarC = cl0.c(iArr[0], this.f);
                    cl0VarC2 = cl0.c(iArr[1], this.f);
                }
            }
            mapArr[i].put("ImageWidth", cl0VarC);
            mapArr[i].put("ImageLength", cl0VarC2);
            return;
        }
        if (cl0Var2 != null && cl0Var3 != null && cl0Var4 != null && cl0Var5 != null) {
            int iE = cl0Var2.e(this.f);
            int iE2 = cl0Var4.e(this.f);
            int iE3 = cl0Var5.e(this.f);
            int iE4 = cl0Var3.e(this.f);
            if (iE2 <= iE || iE3 <= iE4) {
                return;
            }
            cl0 cl0VarC3 = cl0.c(iE2 - iE, this.f);
            cl0 cl0VarC4 = cl0.c(iE3 - iE4, this.f);
            mapArr[i].put("ImageLength", cl0VarC3);
            mapArr[i].put("ImageWidth", cl0VarC4);
            return;
        }
        cl0 cl0Var6 = (cl0) mapArr[i].get("ImageLength");
        cl0 cl0Var7 = (cl0) mapArr[i].get("ImageWidth");
        if (cl0Var6 == null || cl0Var7 == null) {
            cl0 cl0Var8 = (cl0) mapArr[i].get("JPEGInterchangeFormat");
            cl0 cl0Var9 = (cl0) mapArr[i].get("JPEGInterchangeFormatLength");
            if (cl0Var8 == null || cl0Var9 == null) {
                return;
            }
            int iE5 = cl0Var8.e(this.f);
            int iE6 = cl0Var8.e(this.f);
            fl0Var.c(iE5);
            byte[] bArr = new byte[iE6];
            fl0Var.read(bArr);
            e(new bl0(bArr), iE5, i);
        }
    }

    public final void x() throws Throwable {
        v(0, 5);
        v(0, 4);
        v(5, 4);
        HashMap[] mapArr = this.d;
        cl0 cl0Var = (cl0) mapArr[1].get("PixelXDimension");
        cl0 cl0Var2 = (cl0) mapArr[1].get("PixelYDimension");
        if (cl0Var != null && cl0Var2 != null) {
            mapArr[0].put("ImageWidth", cl0Var);
            mapArr[0].put("ImageLength", cl0Var2);
        }
        if (mapArr[4].isEmpty() && n(mapArr[5])) {
            mapArr[4] = mapArr[5];
            mapArr[5] = new HashMap();
        }
        n(mapArr[4]);
        t(0, "ThumbnailOrientation", "Orientation");
        t(0, "ThumbnailImageLength", "ImageLength");
        t(0, "ThumbnailImageWidth", "ImageWidth");
        t(5, "ThumbnailOrientation", "Orientation");
        t(5, "ThumbnailImageLength", "ImageLength");
        t(5, "ThumbnailImageWidth", "ImageWidth");
        t(4, "Orientation", "ThumbnailOrientation");
        t(4, "ImageLength", "ThumbnailImageLength");
        t(4, "ImageWidth", "ThumbnailImageWidth");
    }
}
