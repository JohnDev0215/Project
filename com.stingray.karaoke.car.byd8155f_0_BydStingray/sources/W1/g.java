package W1;

import android.content.res.AssetManager;
import android.hardware.bydauto.TsNormalNum;
import android.media.MediaMetadataRetriever;
import android.system.OsConstants;
import android.util.Log;
import h6.n;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileDescriptor;
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
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final byte[] f9681A;
    public static final String[] B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final int[] f9682C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final byte[] f9683D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final d f9684E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final d[][] f9685F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final d[] f9686G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final HashMap[] f9687H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public static final HashMap[] f9688I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final HashSet f9689J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public static final HashMap f9690K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public static final Charset f9691L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static final byte[] f9692M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static final byte[] f9693N;
    public static final boolean l = Log.isLoggable("ExifInterface", 3);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int[] f9694m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f9695n;
    public static final byte[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final byte[] f9696p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final byte[] f9697q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final byte[] f9698r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final byte[] f9699s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final byte[] f9700t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final byte[] f9701u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final byte[] f9702v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final byte[] f9703w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final byte[] f9704x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final byte[] f9705y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final byte[] f9706z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final FileDescriptor f9707a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AssetManager.AssetInputStream f9708b;
    public int c;
    public final HashMap[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashSet f9709e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ByteOrder f9710f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f9711g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f9712h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f9713i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f9714j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f9715k;

    static {
        Arrays.asList(1, 6, 3, 8);
        Arrays.asList(2, 7, 4, 5);
        f9694m = new int[]{8, 8, 8};
        f9695n = new int[]{8};
        o = new byte[]{-1, -40, -1};
        f9696p = new byte[]{102, 116, 121, 112};
        f9697q = new byte[]{109, 105, 102, 49};
        f9698r = new byte[]{104, 101, 105, 99};
        f9699s = new byte[]{79, 76, 89, 77, 80, 0};
        f9700t = new byte[]{79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
        f9701u = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};
        f9702v = new byte[]{101, 88, 73, 102};
        f9703w = new byte[]{73, 72, 68, 82};
        f9704x = new byte[]{73, 69, 78, 68};
        f9705y = new byte[]{82, 73, 70, 70};
        f9706z = new byte[]{87, 69, 66, 80};
        f9681A = new byte[]{69, 88, 73, 70};
        "VP8X".getBytes(Charset.defaultCharset());
        "VP8L".getBytes(Charset.defaultCharset());
        "VP8 ".getBytes(Charset.defaultCharset());
        "ANIM".getBytes(Charset.defaultCharset());
        "ANMF".getBytes(Charset.defaultCharset());
        B = new String[]{"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
        f9682C = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
        f9683D = new byte[]{65, 83, 67, 73, 73, 0, 0, 0};
        d[] dVarArr = {new d("NewSubfileType", 254, 4), new d("SubfileType", 255, 4), new d("ImageWidth", 256, 3, 4), new d("ImageLength", 257, 3, 4), new d("BitsPerSample", 258, 3), new d("Compression", 259, 3), new d("PhotometricInterpretation", 262, 3), new d("ImageDescription", 270, 2), new d("Make", 271, 2), new d("Model", 272, 2), new d("StripOffsets", 273, 3, 4), new d("Orientation", 274, 3), new d("SamplesPerPixel", 277, 3), new d("RowsPerStrip", 278, 3, 4), new d("StripByteCounts", 279, 3, 4), new d("XResolution", 282, 5), new d("YResolution", 283, 5), new d("PlanarConfiguration", 284, 3), new d("ResolutionUnit", 296, 3), new d("TransferFunction", 301, 3), new d("Software", 305, 2), new d("DateTime", 306, 2), new d("Artist", 315, 2), new d("WhitePoint", 318, 5), new d("PrimaryChromaticities", 319, 5), new d("SubIFDPointer", 330, 4), new d("JPEGInterchangeFormat", 513, 4), new d("JPEGInterchangeFormatLength", 514, 4), new d("YCbCrCoefficients", 529, 5), new d("YCbCrSubSampling", 530, 3), new d("YCbCrPositioning", 531, 3), new d("ReferenceBlackWhite", 532, 5), new d("Copyright", 33432, 2), new d("ExifIFDPointer", 34665, 4), new d("GPSInfoIFDPointer", 34853, 4), new d("SensorTopBorder", 4, 4), new d("SensorLeftBorder", 5, 4), new d("SensorBottomBorder", 6, 4), new d("SensorRightBorder", 7, 4), new d("ISO", 23, 3), new d("JpgFromRaw", 46, 7), new d("Xmp", 700, 1)};
        d[] dVarArr2 = {new d("ExposureTime", 33434, 5), new d("FNumber", 33437, 5), new d("ExposureProgram", 34850, 3), new d("SpectralSensitivity", 34852, 2), new d("PhotographicSensitivity", 34855, 3), new d("OECF", 34856, 7), new d("SensitivityType", 34864, 3), new d("StandardOutputSensitivity", 34865, 4), new d("RecommendedExposureIndex", 34866, 4), new d("ISOSpeed", 34867, 4), new d("ISOSpeedLatitudeyyy", 34868, 4), new d("ISOSpeedLatitudezzz", 34869, 4), new d("ExifVersion", 36864, 2), new d("DateTimeOriginal", 36867, 2), new d("DateTimeDigitized", 36868, 2), new d("OffsetTime", 36880, 2), new d("OffsetTimeOriginal", 36881, 2), new d("OffsetTimeDigitized", 36882, 2), new d("ComponentsConfiguration", 37121, 7), new d("CompressedBitsPerPixel", 37122, 5), new d("ShutterSpeedValue", 37377, 10), new d("ApertureValue", 37378, 5), new d("BrightnessValue", 37379, 10), new d("ExposureBiasValue", 37380, 10), new d("MaxApertureValue", 37381, 5), new d("SubjectDistance", 37382, 5), new d("MeteringMode", 37383, 3), new d("LightSource", 37384, 3), new d("Flash", 37385, 3), new d("FocalLength", 37386, 5), new d("SubjectArea", 37396, 3), new d("MakerNote", 37500, 7), new d("UserComment", 37510, 7), new d("SubSecTime", 37520, 2), new d("SubSecTimeOriginal", 37521, 2), new d("SubSecTimeDigitized", 37522, 2), new d("FlashpixVersion", 40960, 7), new d("ColorSpace", 40961, 3), new d("PixelXDimension", 40962, 3, 4), new d("PixelYDimension", 40963, 3, 4), new d("RelatedSoundFile", 40964, 2), new d("InteroperabilityIFDPointer", 40965, 4), new d("FlashEnergy", 41483, 5), new d("SpatialFrequencyResponse", 41484, 7), new d("FocalPlaneXResolution", 41486, 5), new d("FocalPlaneYResolution", 41487, 5), new d("FocalPlaneResolutionUnit", 41488, 3), new d("SubjectLocation", 41492, 3), new d("ExposureIndex", 41493, 5), new d("SensingMethod", 41495, 3), new d("FileSource", 41728, 7), new d("SceneType", 41729, 7), new d("CFAPattern", 41730, 7), new d("CustomRendered", 41985, 3), new d("ExposureMode", 41986, 3), new d("WhiteBalance", 41987, 3), new d("DigitalZoomRatio", 41988, 5), new d("FocalLengthIn35mmFilm", 41989, 3), new d("SceneCaptureType", 41990, 3), new d("GainControl", 41991, 3), new d("Contrast", 41992, 3), new d("Saturation", 41993, 3), new d("Sharpness", 41994, 3), new d("DeviceSettingDescription", 41995, 7), new d("SubjectDistanceRange", 41996, 3), new d("ImageUniqueID", 42016, 2), new d("CameraOwnerName", 42032, 2), new d("BodySerialNumber", 42033, 2), new d("LensSpecification", 42034, 5), new d("LensMake", 42035, 2), new d("LensModel", 42036, 2), new d("Gamma", 42240, 5), new d("DNGVersion", 50706, 1), new d("DefaultCropSize", 50720, 3, 4)};
        d[] dVarArr3 = {new d("GPSVersionID", 0, 1), new d("GPSLatitudeRef", 1, 2), new d("GPSLatitude", 2, 5, 10), new d("GPSLongitudeRef", 3, 2), new d("GPSLongitude", 4, 5, 10), new d("GPSAltitudeRef", 5, 1), new d("GPSAltitude", 6, 5), new d("GPSTimeStamp", 7, 5), new d("GPSSatellites", 8, 2), new d("GPSStatus", 9, 2), new d("GPSMeasureMode", 10, 2), new d("GPSDOP", 11, 5), new d("GPSSpeedRef", 12, 2), new d("GPSSpeed", 13, 5), new d("GPSTrackRef", 14, 2), new d("GPSTrack", 15, 5), new d("GPSImgDirectionRef", 16, 2), new d("GPSImgDirection", 17, 5), new d("GPSMapDatum", 18, 2), new d("GPSDestLatitudeRef", 19, 2), new d("GPSDestLatitude", 20, 5), new d("GPSDestLongitudeRef", 21, 2), new d("GPSDestLongitude", 22, 5), new d("GPSDestBearingRef", 23, 2), new d("GPSDestBearing", 24, 5), new d("GPSDestDistanceRef", 25, 2), new d("GPSDestDistance", 26, 5), new d("GPSProcessingMethod", 27, 7), new d("GPSAreaInformation", 28, 7), new d("GPSDateStamp", 29, 2), new d("GPSDifferential", 30, 3), new d("GPSHPositioningError", 31, 5)};
        d[] dVarArr4 = {new d("InteroperabilityIndex", 1, 2)};
        d[] dVarArr5 = {new d("NewSubfileType", 254, 4), new d("SubfileType", 255, 4), new d("ThumbnailImageWidth", 256, 3, 4), new d("ThumbnailImageLength", 257, 3, 4), new d("BitsPerSample", 258, 3), new d("Compression", 259, 3), new d("PhotometricInterpretation", 262, 3), new d("ImageDescription", 270, 2), new d("Make", 271, 2), new d("Model", 272, 2), new d("StripOffsets", 273, 3, 4), new d("ThumbnailOrientation", 274, 3), new d("SamplesPerPixel", 277, 3), new d("RowsPerStrip", 278, 3, 4), new d("StripByteCounts", 279, 3, 4), new d("XResolution", 282, 5), new d("YResolution", 283, 5), new d("PlanarConfiguration", 284, 3), new d("ResolutionUnit", 296, 3), new d("TransferFunction", 301, 3), new d("Software", 305, 2), new d("DateTime", 306, 2), new d("Artist", 315, 2), new d("WhitePoint", 318, 5), new d("PrimaryChromaticities", 319, 5), new d("SubIFDPointer", 330, 4), new d("JPEGInterchangeFormat", 513, 4), new d("JPEGInterchangeFormatLength", 514, 4), new d("YCbCrCoefficients", 529, 5), new d("YCbCrSubSampling", 530, 3), new d("YCbCrPositioning", 531, 3), new d("ReferenceBlackWhite", 532, 5), new d("Copyright", 33432, 2), new d("ExifIFDPointer", 34665, 4), new d("GPSInfoIFDPointer", 34853, 4), new d("DNGVersion", 50706, 1), new d("DefaultCropSize", 50720, 3, 4)};
        f9684E = new d("StripOffsets", 273, 3);
        f9685F = new d[][]{dVarArr, dVarArr2, dVarArr3, dVarArr4, dVarArr5, dVarArr, new d[]{new d("ThumbnailImage", 256, 7), new d("CameraSettingsIFDPointer", 8224, 4), new d("ImageProcessingIFDPointer", 8256, 4)}, new d[]{new d("PreviewImageStart", 257, 4), new d("PreviewImageLength", 258, 4)}, new d[]{new d("AspectFrame", 4371, 3)}, new d[]{new d("ColorSpace", 55, 3)}};
        f9686G = new d[]{new d("SubIFDPointer", 330, 4), new d("ExifIFDPointer", 34665, 4), new d("GPSInfoIFDPointer", 34853, 4), new d("InteroperabilityIFDPointer", 40965, 4), new d("CameraSettingsIFDPointer", 8224, 1), new d("ImageProcessingIFDPointer", 8256, 1)};
        f9687H = new HashMap[10];
        f9688I = new HashMap[10];
        f9689J = new HashSet(Arrays.asList("FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"));
        f9690K = new HashMap();
        Charset charsetForName = Charset.forName("US-ASCII");
        f9691L = charsetForName;
        f9692M = "Exif\u0000\u0000".getBytes(charsetForName);
        f9693N = "http://ns.adobe.com/xap/1.0/\u0000".getBytes(charsetForName);
        Locale locale = Locale.US;
        new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", locale).setTimeZone(TimeZone.getTimeZone("UTC"));
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale).setTimeZone(TimeZone.getTimeZone("UTC"));
        int i7 = 0;
        while (true) {
            d[][] dVarArr6 = f9685F;
            if (i7 >= dVarArr6.length) {
                HashMap map = f9690K;
                d[] dVarArr7 = f9686G;
                map.put(Integer.valueOf(dVarArr7[0].f9677a), 5);
                map.put(Integer.valueOf(dVarArr7[1].f9677a), 1);
                map.put(Integer.valueOf(dVarArr7[2].f9677a), 2);
                map.put(Integer.valueOf(dVarArr7[3].f9677a), 3);
                map.put(Integer.valueOf(dVarArr7[4].f9677a), 7);
                map.put(Integer.valueOf(dVarArr7[5].f9677a), 8);
                Pattern.compile(".*[1-9].*");
                Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
                Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                return;
            }
            f9687H[i7] = new HashMap();
            f9688I[i7] = new HashMap();
            for (d dVar : dVarArr6[i7]) {
                f9687H[i7].put(Integer.valueOf(dVar.f9677a), dVar);
                f9688I[i7].put(dVar.f9678b, dVar);
            }
            i7++;
        }
    }

    public g(InputStream inputStream) throws IOException {
        d[][] dVarArr = f9685F;
        this.d = new HashMap[dVarArr.length];
        this.f9709e = new HashSet(dVarArr.length);
        this.f9710f = ByteOrder.BIG_ENDIAN;
        boolean z6 = inputStream instanceof AssetManager.AssetInputStream;
        boolean z10 = l;
        if (z6) {
            this.f9708b = (AssetManager.AssetInputStream) inputStream;
            this.f9707a = null;
        } else if (inputStream instanceof FileInputStream) {
            FileInputStream fileInputStream = (FileInputStream) inputStream;
            try {
                h.c(fileInputStream.getFD(), 0L, OsConstants.SEEK_CUR);
                this.f9708b = null;
                this.f9707a = fileInputStream.getFD();
            } catch (Exception unused) {
                if (z10) {
                    Log.d("ExifInterface", "The file descriptor for the given input is not seekable");
                }
                this.f9708b = null;
                this.f9707a = null;
            }
        } else {
            this.f9708b = null;
            this.f9707a = null;
        }
        for (int i7 = 0; i7 < dVarArr.length; i7++) {
            try {
                try {
                    this.d[i7] = new HashMap();
                } catch (IOException | UnsupportedOperationException e10) {
                    if (z10) {
                        n.u("ExifInterface", "Invalid image: ExifInterface got an unsupported image format file(ExifInterface supports JPEG and some RAW image formats only) or a corrupted JPEG file to ExifInterface.", e10);
                    }
                    a();
                    if (!z10) {
                        return;
                    }
                }
            } catch (Throwable th) {
                a();
                if (z10) {
                    q();
                }
                throw th;
            }
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, TsNormalNum.THOUSAND_FIVE);
        int iG = g(bufferedInputStream);
        this.c = iG;
        if (iG == 4 || iG == 9 || iG == 13 || iG == 14) {
            b bVar = new b(bufferedInputStream);
            int i10 = this.c;
            if (i10 == 4) {
                f(bVar, 0, 0);
            } else if (i10 == 13) {
                i(bVar);
            } else if (i10 == 9) {
                j(bVar);
            } else if (i10 == 14) {
                m(bVar);
            }
        } else {
            f fVar = new f(bufferedInputStream);
            int i11 = this.c;
            if (i11 == 12) {
                e(fVar);
            } else if (i11 == 7) {
                h(fVar);
            } else if (i11 == 10) {
                l(fVar);
            } else {
                k(fVar);
            }
            fVar.g(this.f9712h);
            v(fVar);
        }
        a();
        if (!z10) {
            return;
        }
        q();
    }

    public static ByteOrder r(b bVar) throws IOException {
        short s6 = bVar.readShort();
        boolean z6 = l;
        if (s6 == 18761) {
            if (z6) {
                Log.d("ExifInterface", "readExifSegment: Byte Align II");
            }
            return ByteOrder.LITTLE_ENDIAN;
        }
        if (s6 == 19789) {
            if (z6) {
                Log.d("ExifInterface", "readExifSegment: Byte Align MM");
            }
            return ByteOrder.BIG_ENDIAN;
        }
        throw new IOException("Invalid byte order: " + Integer.toHexString(s6));
    }

    public final void a() {
        String strB = b("DateTimeOriginal");
        HashMap[] mapArr = this.d;
        if (strB != null && b("DateTime") == null) {
            HashMap map = mapArr[0];
            byte[] bytes = strB.concat("\u0000").getBytes(f9691L);
            map.put("DateTime", new c(2, bytes.length, bytes));
        }
        if (b("ImageWidth") == null) {
            mapArr[0].put("ImageWidth", c.a(0L, this.f9710f));
        }
        if (b("ImageLength") == null) {
            mapArr[0].put("ImageLength", c.a(0L, this.f9710f));
        }
        if (b("Orientation") == null) {
            mapArr[0].put("Orientation", c.a(0L, this.f9710f));
        }
        if (b("LightSource") == null) {
            mapArr[1].put("LightSource", c.a(0L, this.f9710f));
        }
    }

    public final String b(String str) {
        c cVarD = d(str);
        if (cVarD != null) {
            if (!f9689J.contains(str)) {
                return cVarD.f(this.f9710f);
            }
            if (str.equals("GPSTimeStamp")) {
                int i7 = cVarD.f9675a;
                if (i7 != 5 && i7 != 10) {
                    android.hardware.bydauto.ac.tsimpl.a.r("GPS Timestamp format is not rational. format=", i7, "ExifInterface");
                    return null;
                }
                e[] eVarArr = (e[]) cVarD.g(this.f9710f);
                if (eVarArr == null || eVarArr.length != 3) {
                    n.t("ExifInterface", "Invalid GPS Timestamp array. array=" + Arrays.toString(eVarArr));
                    return null;
                }
                e eVar = eVarArr[0];
                Integer numValueOf = Integer.valueOf((int) (eVar.f9679a / eVar.f9680b));
                e eVar2 = eVarArr[1];
                Integer numValueOf2 = Integer.valueOf((int) (eVar2.f9679a / eVar2.f9680b));
                e eVar3 = eVarArr[2];
                return String.format("%02d:%02d:%02d", numValueOf, numValueOf2, Integer.valueOf((int) (eVar3.f9679a / eVar3.f9680b)));
            }
            try {
                return Double.toString(cVarD.d(this.f9710f));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public final int c() {
        c cVarD = d("Orientation");
        if (cVarD == null) {
            return 1;
        }
        try {
            return cVarD.e(this.f9710f);
        } catch (NumberFormatException unused) {
            return 1;
        }
    }

    public final c d(String str) {
        if ("ISOSpeedRatings".equals(str)) {
            if (l) {
                Log.d("ExifInterface", "getExifAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY.");
            }
            str = "PhotographicSensitivity";
        }
        for (int i7 = 0; i7 < f9685F.length; i7++) {
            c cVar = (c) this.d[i7].get(str);
            if (cVar != null) {
                return cVar;
            }
        }
        return null;
    }

    public final void e(f fVar) throws IOException {
        String strExtractMetadata;
        String strExtractMetadata2;
        String strExtractMetadata3;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                i.a(mediaMetadataRetriever, new a(fVar));
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
                    mapArr[0].put("ImageWidth", c.c(Integer.parseInt(strExtractMetadata), this.f9710f));
                }
                if (strExtractMetadata2 != null) {
                    mapArr[0].put("ImageLength", c.c(Integer.parseInt(strExtractMetadata2), this.f9710f));
                }
                if (strExtractMetadata3 != null) {
                    int i7 = Integer.parseInt(strExtractMetadata3);
                    mapArr[0].put("Orientation", c.c(i7 != 90 ? i7 != 180 ? i7 != 270 ? 1 : 8 : 3 : 6, this.f9710f));
                }
                if (strExtractMetadata4 != null && strExtractMetadata5 != null) {
                    int i10 = Integer.parseInt(strExtractMetadata4);
                    int i11 = Integer.parseInt(strExtractMetadata5);
                    if (i11 <= 6) {
                        throw new IOException("Invalid exif length");
                    }
                    fVar.g(i10);
                    byte[] bArr = new byte[6];
                    fVar.readFully(bArr);
                    int i12 = i10 + 6;
                    int i13 = i11 - 6;
                    if (!Arrays.equals(bArr, f9692M)) {
                        throw new IOException("Invalid identifier");
                    }
                    byte[] bArr2 = new byte[i13];
                    fVar.readFully(bArr2);
                    this.f9712h = i12;
                    s(0, bArr2);
                }
                if (l) {
                    Log.d("ExifInterface", "Heif meta: " + strExtractMetadata + "x" + strExtractMetadata2 + ", rotation " + strExtractMetadata3);
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

    /* JADX WARN: Removed duplicated region for block: B:67:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x013e A[SYNTHETIC] */
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
    public final void f(W1.b r23, int r24, int r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: W1.g.f(W1.b, int, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:159:0x00f7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f5 A[RETURN] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int g(java.io.BufferedInputStream r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 386
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: W1.g.g(java.io.BufferedInputStream):int");
    }

    public final void h(f fVar) throws Throwable {
        int i7;
        int i10;
        k(fVar);
        HashMap[] mapArr = this.d;
        c cVar = (c) mapArr[1].get("MakerNote");
        if (cVar != null) {
            f fVar2 = new f(cVar.d);
            fVar2.f9673n = this.f9710f;
            byte[] bArr = f9699s;
            byte[] bArr2 = new byte[bArr.length];
            fVar2.readFully(bArr2);
            fVar2.g(0L);
            byte[] bArr3 = f9700t;
            byte[] bArr4 = new byte[bArr3.length];
            fVar2.readFully(bArr4);
            if (Arrays.equals(bArr2, bArr)) {
                fVar2.g(8L);
            } else if (Arrays.equals(bArr4, bArr3)) {
                fVar2.g(12L);
            }
            t(fVar2, 6);
            c cVar2 = (c) mapArr[7].get("PreviewImageStart");
            c cVar3 = (c) mapArr[7].get("PreviewImageLength");
            if (cVar2 != null && cVar3 != null) {
                mapArr[5].put("JPEGInterchangeFormat", cVar2);
                mapArr[5].put("JPEGInterchangeFormatLength", cVar3);
            }
            c cVar4 = (c) mapArr[8].get("AspectFrame");
            if (cVar4 != null) {
                int[] iArr = (int[]) cVar4.g(this.f9710f);
                if (iArr == null || iArr.length != 4) {
                    n.t("ExifInterface", "Invalid aspect frame values. frame=" + Arrays.toString(iArr));
                    return;
                }
                int i11 = iArr[2];
                int i12 = iArr[0];
                if (i11 <= i12 || (i7 = iArr[3]) <= (i10 = iArr[1])) {
                    return;
                }
                int i13 = (i11 - i12) + 1;
                int i14 = (i7 - i10) + 1;
                if (i13 < i14) {
                    int i15 = i13 + i14;
                    i14 = i15 - i14;
                    i13 = i15 - i14;
                }
                c cVarC = c.c(i13, this.f9710f);
                c cVarC2 = c.c(i14, this.f9710f);
                mapArr[0].put("ImageWidth", cVarC);
                mapArr[0].put("ImageLength", cVarC2);
            }
        }
    }

    public final void i(b bVar) throws Throwable {
        if (l) {
            Log.d("ExifInterface", "getPngAttributes starting with: " + bVar);
        }
        bVar.f9673n = ByteOrder.BIG_ENDIAN;
        byte[] bArr = f9701u;
        bVar.b(bArr.length);
        int length = bArr.length;
        while (true) {
            try {
                int i7 = bVar.readInt();
                byte[] bArr2 = new byte[4];
                bVar.readFully(bArr2);
                int i10 = length + 8;
                if (i10 == 16 && !Arrays.equals(bArr2, f9703w)) {
                    throw new IOException("Encountered invalid PNG file--IHDR chunk should appearas the first chunk");
                }
                if (Arrays.equals(bArr2, f9704x)) {
                    return;
                }
                if (Arrays.equals(bArr2, f9702v)) {
                    byte[] bArr3 = new byte[i7];
                    bVar.readFully(bArr3);
                    int i11 = bVar.readInt();
                    CRC32 crc32 = new CRC32();
                    crc32.update(bArr2);
                    crc32.update(bArr3);
                    if (((int) crc32.getValue()) == i11) {
                        this.f9712h = i10;
                        s(0, bArr3);
                        y();
                        v(new b(bArr3));
                        return;
                    }
                    throw new IOException("Encountered invalid CRC value for PNG-EXIF chunk.\n recorded CRC value: " + i11 + ", calculated CRC value: " + crc32.getValue());
                }
                int i12 = i7 + 4;
                bVar.b(i12);
                length = i10 + i12;
            } catch (EOFException unused) {
                throw new IOException("Encountered corrupt PNG file.");
            }
        }
    }

    public final void j(b bVar) throws Throwable {
        boolean z6 = l;
        if (z6) {
            Log.d("ExifInterface", "getRafAttributes starting with: " + bVar);
        }
        bVar.b(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[4];
        bVar.readFully(bArr);
        bVar.readFully(bArr2);
        bVar.readFully(bArr3);
        int i7 = ByteBuffer.wrap(bArr).getInt();
        int i10 = ByteBuffer.wrap(bArr2).getInt();
        int i11 = ByteBuffer.wrap(bArr3).getInt();
        byte[] bArr4 = new byte[i10];
        bVar.b(i7 - bVar.f9672m);
        bVar.readFully(bArr4);
        f(new b(bArr4), i7, 5);
        bVar.b(i11 - bVar.f9672m);
        bVar.f9673n = ByteOrder.BIG_ENDIAN;
        int i12 = bVar.readInt();
        if (z6) {
            android.hardware.bydauto.ac.tsimpl.a.o("numberOfDirectoryEntry: ", i12, "ExifInterface");
        }
        for (int i13 = 0; i13 < i12; i13++) {
            int unsignedShort = bVar.readUnsignedShort();
            int unsignedShort2 = bVar.readUnsignedShort();
            if (unsignedShort == f9684E.f9677a) {
                short s6 = bVar.readShort();
                short s10 = bVar.readShort();
                c cVarC = c.c(s6, this.f9710f);
                c cVarC2 = c.c(s10, this.f9710f);
                HashMap[] mapArr = this.d;
                mapArr[0].put("ImageLength", cVarC);
                mapArr[0].put("ImageWidth", cVarC2);
                if (z6) {
                    Log.d("ExifInterface", "Updated to length: " + ((int) s6) + ", width: " + ((int) s10));
                    return;
                }
                return;
            }
            bVar.b(unsignedShort2);
        }
    }

    public final void k(f fVar) throws Throwable {
        p(fVar);
        t(fVar, 0);
        x(fVar, 0);
        x(fVar, 5);
        x(fVar, 4);
        y();
        if (this.c == 8) {
            HashMap[] mapArr = this.d;
            c cVar = (c) mapArr[1].get("MakerNote");
            if (cVar != null) {
                f fVar2 = new f(cVar.d);
                fVar2.f9673n = this.f9710f;
                fVar2.b(6);
                t(fVar2, 9);
                c cVar2 = (c) mapArr[9].get("ColorSpace");
                if (cVar2 != null) {
                    mapArr[1].put("ColorSpace", cVar2);
                }
            }
        }
    }

    public final void l(f fVar) throws Throwable {
        if (l) {
            Log.d("ExifInterface", "getRw2Attributes starting with: " + fVar);
        }
        k(fVar);
        HashMap[] mapArr = this.d;
        c cVar = (c) mapArr[0].get("JpgFromRaw");
        if (cVar != null) {
            f(new b(cVar.d), (int) cVar.c, 5);
        }
        c cVar2 = (c) mapArr[0].get("ISO");
        c cVar3 = (c) mapArr[1].get("PhotographicSensitivity");
        if (cVar2 == null || cVar3 != null) {
            return;
        }
        mapArr[1].put("PhotographicSensitivity", cVar2);
    }

    public final void m(b bVar) throws Throwable {
        if (l) {
            Log.d("ExifInterface", "getWebpAttributes starting with: " + bVar);
        }
        bVar.f9673n = ByteOrder.LITTLE_ENDIAN;
        bVar.b(f9705y.length);
        int i7 = bVar.readInt() + 8;
        byte[] bArr = f9706z;
        bVar.b(bArr.length);
        int length = bArr.length + 8;
        while (true) {
            try {
                byte[] bArr2 = new byte[4];
                bVar.readFully(bArr2);
                int i10 = bVar.readInt();
                int i11 = length + 8;
                if (Arrays.equals(f9681A, bArr2)) {
                    byte[] bArr3 = new byte[i10];
                    bVar.readFully(bArr3);
                    this.f9712h = i11;
                    s(0, bArr3);
                    v(new b(bArr3));
                    return;
                }
                if (i10 % 2 == 1) {
                    i10++;
                }
                length = i11 + i10;
                if (length == i7) {
                    return;
                }
                if (length > i7) {
                    throw new IOException("Encountered WebP file with invalid chunk size");
                }
                bVar.b(i10);
            } catch (EOFException unused) {
                throw new IOException("Encountered corrupt WebP file.");
            }
        }
    }

    public final void n(b bVar, HashMap map) throws Throwable {
        c cVar = (c) map.get("JPEGInterchangeFormat");
        c cVar2 = (c) map.get("JPEGInterchangeFormatLength");
        if (cVar == null || cVar2 == null) {
            return;
        }
        int iE = cVar.e(this.f9710f);
        int iE2 = cVar2.e(this.f9710f);
        if (this.c == 7) {
            iE += this.f9713i;
        }
        if (iE > 0 && iE2 > 0 && this.f9708b == null && this.f9707a == null) {
            bVar.b(iE);
            bVar.readFully(new byte[iE2]);
        }
        if (l) {
            Log.d("ExifInterface", "Setting thumbnail attributes with offset: " + iE + ", length: " + iE2);
        }
    }

    public final boolean o(HashMap map) {
        c cVar = (c) map.get("ImageLength");
        c cVar2 = (c) map.get("ImageWidth");
        if (cVar == null || cVar2 == null) {
            return false;
        }
        return cVar.e(this.f9710f) <= 512 && cVar2.e(this.f9710f) <= 512;
    }

    public final void p(f fVar) throws IOException {
        ByteOrder byteOrderR = r(fVar);
        this.f9710f = byteOrderR;
        fVar.f9673n = byteOrderR;
        int unsignedShort = fVar.readUnsignedShort();
        int i7 = this.c;
        if (i7 != 7 && i7 != 10 && unsignedShort != 42) {
            throw new IOException("Invalid start code: " + Integer.toHexString(unsignedShort));
        }
        int i10 = fVar.readInt();
        if (i10 < 8) {
            throw new IOException(android.hardware.bydauto.ac.tsimpl.a.e(i10, "Invalid first Ifd offset: "));
        }
        int i11 = i10 - 8;
        if (i11 > 0) {
            fVar.b(i11);
        }
    }

    public final void q() {
        int i7 = 0;
        while (true) {
            HashMap[] mapArr = this.d;
            if (i7 >= mapArr.length) {
                return;
            }
            StringBuilder sbJ = M5.b.j("The size of tag group[", i7, "]: ");
            sbJ.append(mapArr[i7].size());
            Log.d("ExifInterface", sbJ.toString());
            for (Map.Entry entry : mapArr[i7].entrySet()) {
                c cVar = (c) entry.getValue();
                Log.d("ExifInterface", "tagName: " + ((String) entry.getKey()) + ", tagType: " + cVar.toString() + ", tagValue: '" + cVar.f(this.f9710f) + "'");
            }
            i7++;
        }
    }

    public final void s(int i7, byte[] bArr) throws IOException {
        f fVar = new f(bArr);
        p(fVar);
        t(fVar, i7);
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0119  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void t(W1.f r29, int r30) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 851
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: W1.g.t(W1.f, int):void");
    }

    public final void u(String str, int i7, String str2) {
        HashMap[] mapArr = this.d;
        if (mapArr[i7].isEmpty() || mapArr[i7].get(str) == null) {
            return;
        }
        HashMap map = mapArr[i7];
        map.put(str2, map.get(str));
        mapArr[i7].remove(str);
    }

    public final void v(b bVar) throws Throwable {
        c cVar;
        int iE;
        HashMap map = this.d[4];
        c cVar2 = (c) map.get("Compression");
        if (cVar2 == null) {
            n(bVar, map);
            return;
        }
        int iE2 = cVar2.e(this.f9710f);
        if (iE2 != 1) {
            if (iE2 == 6) {
                n(bVar, map);
                return;
            } else if (iE2 != 7) {
                return;
            }
        }
        c cVar3 = (c) map.get("BitsPerSample");
        if (cVar3 != null) {
            int[] iArr = (int[]) cVar3.g(this.f9710f);
            int[] iArr2 = f9694m;
            if (Arrays.equals(iArr2, iArr) || (this.c == 3 && (cVar = (c) map.get("PhotometricInterpretation")) != null && (((iE = cVar.e(this.f9710f)) == 1 && Arrays.equals(iArr, f9695n)) || (iE == 6 && Arrays.equals(iArr, iArr2))))) {
                c cVar4 = (c) map.get("StripOffsets");
                c cVar5 = (c) map.get("StripByteCounts");
                if (cVar4 == null || cVar5 == null) {
                    return;
                }
                long[] jArrA = AbstractC2673a.A(cVar4.g(this.f9710f));
                long[] jArrA2 = AbstractC2673a.A(cVar5.g(this.f9710f));
                if (jArrA == null || jArrA.length == 0) {
                    n.t("ExifInterface", "stripOffsets should not be null or have zero length.");
                    return;
                }
                if (jArrA2 == null || jArrA2.length == 0) {
                    n.t("ExifInterface", "stripByteCounts should not be null or have zero length.");
                    return;
                }
                if (jArrA.length != jArrA2.length) {
                    n.t("ExifInterface", "stripOffsets and stripByteCounts should have same length.");
                    return;
                }
                long j3 = 0;
                for (long j10 : jArrA2) {
                    j3 += j10;
                }
                byte[] bArr = new byte[(int) j3];
                this.f9711g = true;
                int i7 = 0;
                int i10 = 0;
                for (int i11 = 0; i11 < jArrA.length; i11++) {
                    int i12 = (int) jArrA[i11];
                    int i13 = (int) jArrA2[i11];
                    if (i11 < jArrA.length - 1 && i12 + i13 != jArrA[i11 + 1]) {
                        this.f9711g = false;
                    }
                    int i14 = i12 - i7;
                    if (i14 < 0) {
                        Log.d("ExifInterface", "Invalid strip offset value");
                        return;
                    }
                    try {
                        bVar.b(i14);
                        int i15 = i7 + i14;
                        byte[] bArr2 = new byte[i13];
                        try {
                            bVar.readFully(bArr2);
                            i7 = i15 + i13;
                            System.arraycopy(bArr2, 0, bArr, i10, i13);
                            i10 += i13;
                        } catch (EOFException unused) {
                            Log.d("ExifInterface", "Failed to read " + i13 + " bytes.");
                            return;
                        }
                    } catch (EOFException unused2) {
                        Log.d("ExifInterface", "Failed to skip " + i14 + " bytes.");
                        return;
                    }
                }
                if (this.f9711g) {
                    long j11 = jArrA[0];
                    return;
                }
                return;
            }
        }
        if (l) {
            Log.d("ExifInterface", "Unsupported data type value");
        }
    }

    public final void w(int i7, int i10) throws Throwable {
        HashMap[] mapArr = this.d;
        boolean zIsEmpty = mapArr[i7].isEmpty();
        boolean z6 = l;
        if (zIsEmpty || mapArr[i10].isEmpty()) {
            if (z6) {
                Log.d("ExifInterface", "Cannot perform swap since only one image data exists");
                return;
            }
            return;
        }
        c cVar = (c) mapArr[i7].get("ImageLength");
        c cVar2 = (c) mapArr[i7].get("ImageWidth");
        c cVar3 = (c) mapArr[i10].get("ImageLength");
        c cVar4 = (c) mapArr[i10].get("ImageWidth");
        if (cVar == null || cVar2 == null) {
            if (z6) {
                Log.d("ExifInterface", "First image does not contain valid size information");
                return;
            }
            return;
        }
        if (cVar3 == null || cVar4 == null) {
            if (z6) {
                Log.d("ExifInterface", "Second image does not contain valid size information");
                return;
            }
            return;
        }
        int iE = cVar.e(this.f9710f);
        int iE2 = cVar2.e(this.f9710f);
        int iE3 = cVar3.e(this.f9710f);
        int iE4 = cVar4.e(this.f9710f);
        if (iE >= iE3 || iE2 >= iE4) {
            return;
        }
        HashMap map = mapArr[i7];
        mapArr[i7] = mapArr[i10];
        mapArr[i10] = map;
    }

    public final void x(f fVar, int i7) throws Throwable {
        c cVarC;
        c cVarC2;
        HashMap[] mapArr = this.d;
        c cVar = (c) mapArr[i7].get("DefaultCropSize");
        c cVar2 = (c) mapArr[i7].get("SensorTopBorder");
        c cVar3 = (c) mapArr[i7].get("SensorLeftBorder");
        c cVar4 = (c) mapArr[i7].get("SensorBottomBorder");
        c cVar5 = (c) mapArr[i7].get("SensorRightBorder");
        if (cVar != null) {
            if (cVar.f9675a == 5) {
                e[] eVarArr = (e[]) cVar.g(this.f9710f);
                if (eVarArr == null || eVarArr.length != 2) {
                    n.t("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(eVarArr));
                    return;
                }
                cVarC = c.b(eVarArr[0], this.f9710f);
                cVarC2 = c.b(eVarArr[1], this.f9710f);
            } else {
                int[] iArr = (int[]) cVar.g(this.f9710f);
                if (iArr == null || iArr.length != 2) {
                    n.t("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(iArr));
                    return;
                }
                cVarC = c.c(iArr[0], this.f9710f);
                cVarC2 = c.c(iArr[1], this.f9710f);
            }
            mapArr[i7].put("ImageWidth", cVarC);
            mapArr[i7].put("ImageLength", cVarC2);
            return;
        }
        if (cVar2 != null && cVar3 != null && cVar4 != null && cVar5 != null) {
            int iE = cVar2.e(this.f9710f);
            int iE2 = cVar4.e(this.f9710f);
            int iE3 = cVar5.e(this.f9710f);
            int iE4 = cVar3.e(this.f9710f);
            if (iE2 <= iE || iE3 <= iE4) {
                return;
            }
            c cVarC3 = c.c(iE2 - iE, this.f9710f);
            c cVarC4 = c.c(iE3 - iE4, this.f9710f);
            mapArr[i7].put("ImageLength", cVarC3);
            mapArr[i7].put("ImageWidth", cVarC4);
            return;
        }
        c cVar6 = (c) mapArr[i7].get("ImageLength");
        c cVar7 = (c) mapArr[i7].get("ImageWidth");
        if (cVar6 == null || cVar7 == null) {
            c cVar8 = (c) mapArr[i7].get("JPEGInterchangeFormat");
            c cVar9 = (c) mapArr[i7].get("JPEGInterchangeFormatLength");
            if (cVar8 == null || cVar9 == null) {
                return;
            }
            int iE5 = cVar8.e(this.f9710f);
            int iE6 = cVar8.e(this.f9710f);
            fVar.g(iE5);
            byte[] bArr = new byte[iE6];
            fVar.readFully(bArr);
            f(new b(bArr), iE5, i7);
        }
    }

    public final void y() throws Throwable {
        w(0, 5);
        w(0, 4);
        w(5, 4);
        HashMap[] mapArr = this.d;
        c cVar = (c) mapArr[1].get("PixelXDimension");
        c cVar2 = (c) mapArr[1].get("PixelYDimension");
        if (cVar != null && cVar2 != null) {
            mapArr[0].put("ImageWidth", cVar);
            mapArr[0].put("ImageLength", cVar2);
        }
        if (mapArr[4].isEmpty() && o(mapArr[5])) {
            mapArr[4] = mapArr[5];
            mapArr[5] = new HashMap();
        }
        if (!o(mapArr[4])) {
            Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
        }
        u("ThumbnailOrientation", 0, "Orientation");
        u("ThumbnailImageLength", 0, "ImageLength");
        u("ThumbnailImageWidth", 0, "ImageWidth");
        u("ThumbnailOrientation", 5, "Orientation");
        u("ThumbnailImageLength", 5, "ImageLength");
        u("ThumbnailImageWidth", 5, "ImageWidth");
        u("Orientation", 4, "ThumbnailOrientation");
        u("ImageLength", 4, "ThumbnailImageLength");
        u("ImageWidth", 4, "ThumbnailImageWidth");
    }
}
