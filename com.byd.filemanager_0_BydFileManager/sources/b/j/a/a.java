package b.j.a;

import android.content.res.AssetManager;
import android.hardware.bydauto.TsNormalNum;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
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
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: ExifInterface.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    private static final HashMap<Integer, c>[] A;
    private static final HashMap<String, c>[] B;
    private static final HashSet<String> C;
    private static final HashMap<Integer, Integer> D;
    static final Charset E;
    static final byte[] F;
    private static final Pattern G;
    private static final Pattern H;
    private static SimpleDateFormat i;
    private static final c[] m;
    private static final c[] n;
    private static final c[] o;
    private static final c[] p;
    private static final c[] q;
    private static final c r;
    private static final c[] s;
    private static final c[] t;
    private static final c[] u;
    private static final c[] v;
    static final c[][] w;
    private static final c[] x;
    private static final c y;
    private static final c z;
    private final String I;
    private final AssetManager.AssetInputStream J;
    private int K;
    private final HashMap<String, b>[] L;
    private Set<Integer> M;
    private ByteOrder N;
    private boolean O;
    private int P;
    private int Q;
    private byte[] R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private boolean Y;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final List<Integer> f2047a = Arrays.asList(1, 6, 3, 8);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final List<Integer> f2048b = Arrays.asList(2, 7, 4, 5);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f2049c = {8, 8, 8};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f2050d = {4};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f2051e = {8};
    static final byte[] f = {-1, -40, -1};
    private static final byte[] g = {79, 76, 89, 77, 80, 0};
    private static final byte[] h = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
    static final String[] j = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE"};
    static final int[] k = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    static final byte[] l = {65, 83, 67, 73, 73, 0, 0, 0};

    /* JADX INFO: compiled from: ExifInterface.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2056a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f2057b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final byte[] f2058c;

        b(int i, int i2, byte[] bArr) {
            this.f2056a = i;
            this.f2057b = i2;
            this.f2058c = bArr;
        }

        public static b a(String str) {
            byte[] bytes = (str + (char) 0).getBytes(a.E);
            return new b(2, bytes.length, bytes);
        }

        public static b b(long j, ByteOrder byteOrder) {
            return c(new long[]{j}, byteOrder);
        }

        public static b c(long[] jArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[a.k[4] * jArr.length]);
            byteBufferWrap.order(byteOrder);
            for (long j : jArr) {
                byteBufferWrap.putInt((int) j);
            }
            return new b(4, jArr.length, byteBufferWrap.array());
        }

        public static b d(d dVar, ByteOrder byteOrder) {
            return e(new d[]{dVar}, byteOrder);
        }

        public static b e(d[] dVarArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[a.k[5] * dVarArr.length]);
            byteBufferWrap.order(byteOrder);
            for (d dVar : dVarArr) {
                byteBufferWrap.putInt((int) dVar.f2063a);
                byteBufferWrap.putInt((int) dVar.f2064b);
            }
            return new b(5, dVarArr.length, byteBufferWrap.array());
        }

        public static b f(int i, ByteOrder byteOrder) {
            return g(new int[]{i}, byteOrder);
        }

        public static b g(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(new byte[a.k[3] * iArr.length]);
            byteBufferWrap.order(byteOrder);
            for (int i : iArr) {
                byteBufferWrap.putShort((short) i);
            }
            return new b(3, iArr.length, byteBufferWrap.array());
        }

        public double h(ByteOrder byteOrder) throws Throwable {
            Object objK = k(byteOrder);
            if (objK == null) {
                throw new NumberFormatException("NULL can't be converted to a double value");
            }
            if (objK instanceof String) {
                return Double.parseDouble((String) objK);
            }
            if (objK instanceof long[]) {
                if (((long[]) objK).length == 1) {
                    return r5[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (objK instanceof int[]) {
                if (((int[]) objK).length == 1) {
                    return r5[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (objK instanceof double[]) {
                double[] dArr = (double[]) objK;
                if (dArr.length == 1) {
                    return dArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (!(objK instanceof d[])) {
                throw new NumberFormatException("Couldn't find a double value");
            }
            d[] dVarArr = (d[]) objK;
            if (dVarArr.length == 1) {
                return dVarArr[0].a();
            }
            throw new NumberFormatException("There are more than one component");
        }

        public int i(ByteOrder byteOrder) throws Throwable {
            Object objK = k(byteOrder);
            if (objK == null) {
                throw new NumberFormatException("NULL can't be converted to a integer value");
            }
            if (objK instanceof String) {
                return Integer.parseInt((String) objK);
            }
            if (objK instanceof long[]) {
                long[] jArr = (long[]) objK;
                if (jArr.length == 1) {
                    return (int) jArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (!(objK instanceof int[])) {
                throw new NumberFormatException("Couldn't find a integer value");
            }
            int[] iArr = (int[]) objK;
            if (iArr.length == 1) {
                return iArr[0];
            }
            throw new NumberFormatException("There are more than one component");
        }

        public String j(ByteOrder byteOrder) throws Throwable {
            Object objK = k(byteOrder);
            if (objK == null) {
                return null;
            }
            if (objK instanceof String) {
                return (String) objK;
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            if (objK instanceof long[]) {
                long[] jArr = (long[]) objK;
                while (i < jArr.length) {
                    sb.append(jArr[i]);
                    i++;
                    if (i != jArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (objK instanceof int[]) {
                int[] iArr = (int[]) objK;
                while (i < iArr.length) {
                    sb.append(iArr[i]);
                    i++;
                    if (i != iArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (objK instanceof double[]) {
                double[] dArr = (double[]) objK;
                while (i < dArr.length) {
                    sb.append(dArr[i]);
                    i++;
                    if (i != dArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
            if (!(objK instanceof d[])) {
                return null;
            }
            d[] dVarArr = (d[]) objK;
            while (i < dVarArr.length) {
                sb.append(dVarArr[i].f2063a);
                sb.append('/');
                sb.append(dVarArr[i].f2064b);
                i++;
                if (i != dVarArr.length) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }

        /* JADX WARN: Not initialized variable reg: 3, insn: 0x01a0: MOVE (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:152:0x01a0 */
        /* JADX WARN: Removed duplicated region for block: B:170:0x01a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        java.lang.Object k(java.nio.ByteOrder r11) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 456
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: b.j.a.a.b.k(java.nio.ByteOrder):java.lang.Object");
        }

        public String toString() {
            return "(" + a.j[this.f2056a] + ", data length:" + this.f2058c.length + ")";
        }
    }

    /* JADX INFO: compiled from: ExifInterface.java */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final long f2063a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final long f2064b;

        d(long j, long j2) {
            if (j2 == 0) {
                this.f2063a = 0L;
                this.f2064b = 1L;
            } else {
                this.f2063a = j;
                this.f2064b = j2;
            }
        }

        public double a() {
            return this.f2063a / this.f2064b;
        }

        public String toString() {
            return this.f2063a + "/" + this.f2064b;
        }
    }

    static {
        c[] cVarArr = {new c("NewSubfileType", 254, 4), new c("SubfileType", 255, 4), new c("ImageWidth", 256, 3, 4), new c("ImageLength", 257, 3, 4), new c("BitsPerSample", 258, 3), new c("Compression", 259, 3), new c("PhotometricInterpretation", 262, 3), new c("ImageDescription", 270, 2), new c("Make", 271, 2), new c("Model", 272, 2), new c("StripOffsets", 273, 3, 4), new c("Orientation", 274, 3), new c("SamplesPerPixel", 277, 3), new c("RowsPerStrip", 278, 3, 4), new c("StripByteCounts", 279, 3, 4), new c("XResolution", 282, 5), new c("YResolution", 283, 5), new c("PlanarConfiguration", 284, 3), new c("ResolutionUnit", 296, 3), new c("TransferFunction", 301, 3), new c("Software", 305, 2), new c("DateTime", 306, 2), new c("Artist", 315, 2), new c("WhitePoint", 318, 5), new c("PrimaryChromaticities", 319, 5), new c("SubIFDPointer", 330, 4), new c("JPEGInterchangeFormat", 513, 4), new c("JPEGInterchangeFormatLength", 514, 4), new c("YCbCrCoefficients", 529, 5), new c("YCbCrSubSampling", 530, 3), new c("YCbCrPositioning", 531, 3), new c("ReferenceBlackWhite", 532, 5), new c("Copyright", 33432, 2), new c("ExifIFDPointer", 34665, 4), new c("GPSInfoIFDPointer", 34853, 4), new c("SensorTopBorder", 4, 4), new c("SensorLeftBorder", 5, 4), new c("SensorBottomBorder", 6, 4), new c("SensorRightBorder", 7, 4), new c("ISO", 23, 3), new c("JpgFromRaw", 46, 7)};
        m = cVarArr;
        c[] cVarArr2 = {new c("ExposureTime", 33434, 5), new c("FNumber", 33437, 5), new c("ExposureProgram", 34850, 3), new c("SpectralSensitivity", 34852, 2), new c("PhotographicSensitivity", 34855, 3), new c("OECF", 34856, 7), new c("ExifVersion", 36864, 2), new c("DateTimeOriginal", 36867, 2), new c("DateTimeDigitized", 36868, 2), new c("ComponentsConfiguration", 37121, 7), new c("CompressedBitsPerPixel", 37122, 5), new c("ShutterSpeedValue", 37377, 10), new c("ApertureValue", 37378, 5), new c("BrightnessValue", 37379, 10), new c("ExposureBiasValue", 37380, 10), new c("MaxApertureValue", 37381, 5), new c("SubjectDistance", 37382, 5), new c("MeteringMode", 37383, 3), new c("LightSource", 37384, 3), new c("Flash", 37385, 3), new c("FocalLength", 37386, 5), new c("SubjectArea", 37396, 3), new c("MakerNote", 37500, 7), new c("UserComment", 37510, 7), new c("SubSecTime", 37520, 2), new c("SubSecTimeOriginal", 37521, 2), new c("SubSecTimeDigitized", 37522, 2), new c("FlashpixVersion", 40960, 7), new c("ColorSpace", 40961, 3), new c("PixelXDimension", 40962, 3, 4), new c("PixelYDimension", 40963, 3, 4), new c("RelatedSoundFile", 40964, 2), new c("InteroperabilityIFDPointer", 40965, 4), new c("FlashEnergy", 41483, 5), new c("SpatialFrequencyResponse", 41484, 7), new c("FocalPlaneXResolution", 41486, 5), new c("FocalPlaneYResolution", 41487, 5), new c("FocalPlaneResolutionUnit", 41488, 3), new c("SubjectLocation", 41492, 3), new c("ExposureIndex", 41493, 5), new c("SensingMethod", 41495, 3), new c("FileSource", 41728, 7), new c("SceneType", 41729, 7), new c("CFAPattern", 41730, 7), new c("CustomRendered", 41985, 3), new c("ExposureMode", 41986, 3), new c("WhiteBalance", 41987, 3), new c("DigitalZoomRatio", 41988, 5), new c("FocalLengthIn35mmFilm", 41989, 3), new c("SceneCaptureType", 41990, 3), new c("GainControl", 41991, 3), new c("Contrast", 41992, 3), new c("Saturation", 41993, 3), new c("Sharpness", 41994, 3), new c("DeviceSettingDescription", 41995, 7), new c("SubjectDistanceRange", 41996, 3), new c("ImageUniqueID", 42016, 2), new c("DNGVersion", 50706, 1), new c("DefaultCropSize", 50720, 3, 4)};
        n = cVarArr2;
        c[] cVarArr3 = {new c("GPSVersionID", 0, 1), new c("GPSLatitudeRef", 1, 2), new c("GPSLatitude", 2, 5), new c("GPSLongitudeRef", 3, 2), new c("GPSLongitude", 4, 5), new c("GPSAltitudeRef", 5, 1), new c("GPSAltitude", 6, 5), new c("GPSTimeStamp", 7, 5), new c("GPSSatellites", 8, 2), new c("GPSStatus", 9, 2), new c("GPSMeasureMode", 10, 2), new c("GPSDOP", 11, 5), new c("GPSSpeedRef", 12, 2), new c("GPSSpeed", 13, 5), new c("GPSTrackRef", 14, 2), new c("GPSTrack", 15, 5), new c("GPSImgDirectionRef", 16, 2), new c("GPSImgDirection", 17, 5), new c("GPSMapDatum", 18, 2), new c("GPSDestLatitudeRef", 19, 2), new c("GPSDestLatitude", 20, 5), new c("GPSDestLongitudeRef", 21, 2), new c("GPSDestLongitude", 22, 5), new c("GPSDestBearingRef", 23, 2), new c("GPSDestBearing", 24, 5), new c("GPSDestDistanceRef", 25, 2), new c("GPSDestDistance", 26, 5), new c("GPSProcessingMethod", 27, 7), new c("GPSAreaInformation", 28, 7), new c("GPSDateStamp", 29, 2), new c("GPSDifferential", 30, 3)};
        o = cVarArr3;
        c[] cVarArr4 = {new c("InteroperabilityIndex", 1, 2)};
        p = cVarArr4;
        c[] cVarArr5 = {new c("NewSubfileType", 254, 4), new c("SubfileType", 255, 4), new c("ThumbnailImageWidth", 256, 3, 4), new c("ThumbnailImageLength", 257, 3, 4), new c("BitsPerSample", 258, 3), new c("Compression", 259, 3), new c("PhotometricInterpretation", 262, 3), new c("ImageDescription", 270, 2), new c("Make", 271, 2), new c("Model", 272, 2), new c("StripOffsets", 273, 3, 4), new c("Orientation", 274, 3), new c("SamplesPerPixel", 277, 3), new c("RowsPerStrip", 278, 3, 4), new c("StripByteCounts", 279, 3, 4), new c("XResolution", 282, 5), new c("YResolution", 283, 5), new c("PlanarConfiguration", 284, 3), new c("ResolutionUnit", 296, 3), new c("TransferFunction", 301, 3), new c("Software", 305, 2), new c("DateTime", 306, 2), new c("Artist", 315, 2), new c("WhitePoint", 318, 5), new c("PrimaryChromaticities", 319, 5), new c("SubIFDPointer", 330, 4), new c("JPEGInterchangeFormat", 513, 4), new c("JPEGInterchangeFormatLength", 514, 4), new c("YCbCrCoefficients", 529, 5), new c("YCbCrSubSampling", 530, 3), new c("YCbCrPositioning", 531, 3), new c("ReferenceBlackWhite", 532, 5), new c("Copyright", 33432, 2), new c("ExifIFDPointer", 34665, 4), new c("GPSInfoIFDPointer", 34853, 4), new c("DNGVersion", 50706, 1), new c("DefaultCropSize", 50720, 3, 4)};
        q = cVarArr5;
        r = new c("StripOffsets", 273, 3);
        c[] cVarArr6 = {new c("ThumbnailImage", 256, 7), new c("CameraSettingsIFDPointer", 8224, 4), new c("ImageProcessingIFDPointer", 8256, 4)};
        s = cVarArr6;
        c[] cVarArr7 = {new c("PreviewImageStart", 257, 4), new c("PreviewImageLength", 258, 4)};
        t = cVarArr7;
        c[] cVarArr8 = {new c("AspectFrame", 4371, 3)};
        u = cVarArr8;
        c[] cVarArr9 = {new c("ColorSpace", 55, 3)};
        v = cVarArr9;
        c[][] cVarArr10 = {cVarArr, cVarArr2, cVarArr3, cVarArr4, cVarArr5, cVarArr, cVarArr6, cVarArr7, cVarArr8, cVarArr9};
        w = cVarArr10;
        x = new c[]{new c("SubIFDPointer", 330, 4), new c("ExifIFDPointer", 34665, 4), new c("GPSInfoIFDPointer", 34853, 4), new c("InteroperabilityIFDPointer", 40965, 4), new c("CameraSettingsIFDPointer", 8224, 1), new c("ImageProcessingIFDPointer", 8256, 1)};
        y = new c("JPEGInterchangeFormat", 513, 4);
        z = new c("JPEGInterchangeFormatLength", 514, 4);
        A = new HashMap[cVarArr10.length];
        B = new HashMap[cVarArr10.length];
        C = new HashSet<>(Arrays.asList("FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"));
        D = new HashMap<>();
        Charset charsetForName = Charset.forName("US-ASCII");
        E = charsetForName;
        F = "Exif\u0000\u0000".getBytes(charsetForName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        i = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        int i2 = 0;
        while (true) {
            c[][] cVarArr11 = w;
            if (i2 >= cVarArr11.length) {
                HashMap<Integer, Integer> map = D;
                c[] cVarArr12 = x;
                map.put(Integer.valueOf(cVarArr12[0].f2059a), 5);
                map.put(Integer.valueOf(cVarArr12[1].f2059a), 1);
                map.put(Integer.valueOf(cVarArr12[2].f2059a), 2);
                map.put(Integer.valueOf(cVarArr12[3].f2059a), 3);
                map.put(Integer.valueOf(cVarArr12[4].f2059a), 7);
                map.put(Integer.valueOf(cVarArr12[5].f2059a), 8);
                G = Pattern.compile(".*[1-9].*");
                H = Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
                return;
            }
            A[i2] = new HashMap<>();
            B[i2] = new HashMap<>();
            for (c cVar : cVarArr11[i2]) {
                A[i2].put(Integer.valueOf(cVar.f2059a), cVar);
                B[i2].put(cVar.f2060b, cVar);
            }
            i2++;
        }
    }

    public a(InputStream inputStream) throws IOException {
        c[][] cVarArr = w;
        this.L = new HashMap[cVarArr.length];
        this.M = new HashSet(cVarArr.length);
        this.N = ByteOrder.BIG_ENDIAN;
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null");
        }
        this.I = null;
        if (inputStream instanceof AssetManager.AssetInputStream) {
            this.J = (AssetManager.AssetInputStream) inputStream;
        } else {
            this.J = null;
        }
        t(inputStream);
    }

    private void A(int i2, int i3) throws Throwable {
        if (this.L[i2].isEmpty() || this.L[i3].isEmpty()) {
            return;
        }
        b bVar = this.L[i2].get("ImageLength");
        b bVar2 = this.L[i2].get("ImageWidth");
        b bVar3 = this.L[i3].get("ImageLength");
        b bVar4 = this.L[i3].get("ImageWidth");
        if (bVar == null || bVar2 == null || bVar3 == null || bVar4 == null) {
            return;
        }
        int i4 = bVar.i(this.N);
        int i5 = bVar2.i(this.N);
        int i6 = bVar3.i(this.N);
        int i7 = bVar4.i(this.N);
        if (i4 >= i6 || i5 >= i7) {
            return;
        }
        HashMap<String, b>[] mapArr = this.L;
        HashMap<String, b> map = mapArr[i2];
        mapArr[i2] = mapArr[i3];
        mapArr[i3] = map;
    }

    private void B(C0044a c0044a, int i2) throws Throwable {
        b bVarF;
        b bVarF2;
        b bVar = this.L[i2].get("DefaultCropSize");
        b bVar2 = this.L[i2].get("SensorTopBorder");
        b bVar3 = this.L[i2].get("SensorLeftBorder");
        b bVar4 = this.L[i2].get("SensorBottomBorder");
        b bVar5 = this.L[i2].get("SensorRightBorder");
        if (bVar == null) {
            if (bVar2 == null || bVar3 == null || bVar4 == null || bVar5 == null) {
                y(c0044a, i2);
                return;
            }
            int i3 = bVar2.i(this.N);
            int i4 = bVar4.i(this.N);
            int i5 = bVar5.i(this.N);
            int i6 = bVar3.i(this.N);
            if (i4 <= i3 || i5 <= i6) {
                return;
            }
            b bVarF3 = b.f(i4 - i3, this.N);
            b bVarF4 = b.f(i5 - i6, this.N);
            this.L[i2].put("ImageLength", bVarF3);
            this.L[i2].put("ImageWidth", bVarF4);
            return;
        }
        if (bVar.f2056a == 5) {
            d[] dVarArr = (d[]) bVar.k(this.N);
            if (dVarArr == null || dVarArr.length != 2) {
                Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(dVarArr));
                return;
            }
            bVarF = b.d(dVarArr[0], this.N);
            bVarF2 = b.d(dVarArr[1], this.N);
        } else {
            int[] iArr = (int[]) bVar.k(this.N);
            if (iArr == null || iArr.length != 2) {
                Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(iArr));
                return;
            }
            bVarF = b.f(iArr[0], this.N);
            bVarF2 = b.f(iArr[1], this.N);
        }
        this.L[i2].put("ImageWidth", bVarF);
        this.L[i2].put("ImageLength", bVarF2);
    }

    private void C(InputStream inputStream) throws Throwable {
        A(0, 5);
        A(0, 4);
        A(5, 4);
        b bVar = this.L[1].get("PixelXDimension");
        b bVar2 = this.L[1].get("PixelYDimension");
        if (bVar != null && bVar2 != null) {
            this.L[0].put("ImageWidth", bVar);
            this.L[0].put("ImageLength", bVar2);
        }
        if (this.L[4].isEmpty() && s(this.L[5])) {
            HashMap<String, b>[] mapArr = this.L;
            mapArr[4] = mapArr[5];
            mapArr[5] = new HashMap<>();
        }
        if (s(this.L[4])) {
            return;
        }
        Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
    }

    private void a() {
        String strC = c("DateTimeOriginal");
        if (strC != null && c("DateTime") == null) {
            this.L[0].put("DateTime", b.a(strC));
        }
        if (c("ImageWidth") == null) {
            this.L[0].put("ImageWidth", b.b(0L, this.N));
        }
        if (c("ImageLength") == null) {
            this.L[0].put("ImageLength", b.b(0L, this.N));
        }
        if (c("Orientation") == null) {
            this.L[0].put("Orientation", b.b(0L, this.N));
        }
        if (c("LightSource") == null) {
            this.L[1].put("LightSource", b.b(0L, this.N));
        }
    }

    private static long[] b(Object obj) {
        if (!(obj instanceof int[])) {
            if (obj instanceof long[]) {
                return (long[]) obj;
            }
            return null;
        }
        int[] iArr = (int[]) obj;
        long[] jArr = new long[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            jArr[i2] = iArr[i2];
        }
        return jArr;
    }

    private b e(String str) {
        if ("ISOSpeedRatings".equals(str)) {
            str = "PhotographicSensitivity";
        }
        for (int i2 = 0; i2 < w.length; i2++) {
            b bVar = this.L[i2].get(str);
            if (bVar != null) {
                return bVar;
            }
        }
        return null;
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
    private void f(b.j.a.a.C0044a r10, int r11, int r12) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.j.a.a.f(b.j.a.a$a, int, int):void");
    }

    private int g(BufferedInputStream bufferedInputStream) throws IOException {
        bufferedInputStream.mark(TsNormalNum.THOUSAND_FIVE);
        byte[] bArr = new byte[TsNormalNum.THOUSAND_FIVE];
        bufferedInputStream.read(bArr);
        bufferedInputStream.reset();
        if (n(bArr)) {
            return 4;
        }
        if (p(bArr)) {
            return 9;
        }
        if (o(bArr)) {
            return 7;
        }
        return q(bArr) ? 10 : 0;
    }

    private void h(C0044a c0044a) throws Throwable {
        j(c0044a);
        b bVar = this.L[1].get("MakerNote");
        if (bVar != null) {
            C0044a c0044a2 = new C0044a(bVar.f2058c);
            c0044a2.m(this.N);
            byte[] bArr = g;
            byte[] bArr2 = new byte[bArr.length];
            c0044a2.readFully(bArr2);
            c0044a2.l(0L);
            byte[] bArr3 = h;
            byte[] bArr4 = new byte[bArr3.length];
            c0044a2.readFully(bArr4);
            if (Arrays.equals(bArr2, bArr)) {
                c0044a2.l(8L);
            } else if (Arrays.equals(bArr4, bArr3)) {
                c0044a2.l(12L);
            }
            x(c0044a2, 6);
            b bVar2 = this.L[7].get("PreviewImageStart");
            b bVar3 = this.L[7].get("PreviewImageLength");
            if (bVar2 != null && bVar3 != null) {
                this.L[5].put("JPEGInterchangeFormat", bVar2);
                this.L[5].put("JPEGInterchangeFormatLength", bVar3);
            }
            b bVar4 = this.L[8].get("AspectFrame");
            if (bVar4 != null) {
                int[] iArr = (int[]) bVar4.k(this.N);
                if (iArr == null || iArr.length != 4) {
                    Log.w("ExifInterface", "Invalid aspect frame values. frame=" + Arrays.toString(iArr));
                    return;
                }
                if (iArr[2] <= iArr[0] || iArr[3] <= iArr[1]) {
                    return;
                }
                int i2 = (iArr[2] - iArr[0]) + 1;
                int i3 = (iArr[3] - iArr[1]) + 1;
                if (i2 < i3) {
                    int i4 = i2 + i3;
                    i3 = i4 - i3;
                    i2 = i4 - i3;
                }
                b bVarF = b.f(i2, this.N);
                b bVarF2 = b.f(i3, this.N);
                this.L[0].put("ImageWidth", bVarF);
                this.L[0].put("ImageLength", bVarF2);
            }
        }
    }

    private void i(C0044a c0044a) throws IOException {
        c0044a.skipBytes(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        c0044a.read(bArr);
        c0044a.skipBytes(4);
        c0044a.read(bArr2);
        int i2 = ByteBuffer.wrap(bArr).getInt();
        int i3 = ByteBuffer.wrap(bArr2).getInt();
        f(c0044a, i2, 5);
        c0044a.l(i3);
        c0044a.m(ByteOrder.BIG_ENDIAN);
        int i4 = c0044a.readInt();
        for (int i5 = 0; i5 < i4; i5++) {
            int unsignedShort = c0044a.readUnsignedShort();
            int unsignedShort2 = c0044a.readUnsignedShort();
            if (unsignedShort == r.f2059a) {
                short s2 = c0044a.readShort();
                short s3 = c0044a.readShort();
                b bVarF = b.f(s2, this.N);
                b bVarF2 = b.f(s3, this.N);
                this.L[0].put("ImageLength", bVarF);
                this.L[0].put("ImageWidth", bVarF2);
                return;
            }
            c0044a.skipBytes(unsignedShort2);
        }
    }

    private void j(C0044a c0044a) throws Throwable {
        b bVar;
        u(c0044a, c0044a.available());
        x(c0044a, 0);
        B(c0044a, 0);
        B(c0044a, 5);
        B(c0044a, 4);
        C(c0044a);
        if (this.K != 8 || (bVar = this.L[1].get("MakerNote")) == null) {
            return;
        }
        C0044a c0044a2 = new C0044a(bVar.f2058c);
        c0044a2.m(this.N);
        c0044a2.l(6L);
        x(c0044a2, 9);
        b bVar2 = this.L[9].get("ColorSpace");
        if (bVar2 != null) {
            this.L[1].put("ColorSpace", bVar2);
        }
    }

    private void k(C0044a c0044a) throws Throwable {
        j(c0044a);
        if (this.L[0].get("JpgFromRaw") != null) {
            f(c0044a, this.X, 5);
        }
        b bVar = this.L[0].get("ISO");
        b bVar2 = this.L[1].get("PhotographicSensitivity");
        if (bVar == null || bVar2 != null) {
            return;
        }
        this.L[1].put("PhotographicSensitivity", bVar);
    }

    private void l(C0044a c0044a, HashMap map) throws Throwable {
        int i2;
        b bVar = (b) map.get("JPEGInterchangeFormat");
        b bVar2 = (b) map.get("JPEGInterchangeFormatLength");
        if (bVar == null || bVar2 == null) {
            return;
        }
        int i3 = bVar.i(this.N);
        int iMin = Math.min(bVar2.i(this.N), c0044a.available() - i3);
        int i4 = this.K;
        if (i4 != 4 && i4 != 9 && i4 != 10) {
            if (i4 == 7) {
                i2 = this.U;
            }
            if (i3 > 0 || iMin <= 0) {
            }
            this.O = true;
            this.P = i3;
            this.Q = iMin;
            if (this.I == null && this.J == null) {
                byte[] bArr = new byte[iMin];
                c0044a.l(i3);
                c0044a.readFully(bArr);
                this.R = bArr;
                return;
            }
            return;
        }
        i2 = this.T;
        i3 += i2;
        if (i3 > 0) {
        }
    }

    private void m(C0044a c0044a, HashMap map) throws IOException {
        b bVar = (b) map.get("StripOffsets");
        b bVar2 = (b) map.get("StripByteCounts");
        if (bVar == null || bVar2 == null) {
            return;
        }
        long[] jArrB = b(bVar.k(this.N));
        long[] jArrB2 = b(bVar2.k(this.N));
        if (jArrB == null) {
            Log.w("ExifInterface", "stripOffsets should not be null.");
            return;
        }
        if (jArrB2 == null) {
            Log.w("ExifInterface", "stripByteCounts should not be null.");
            return;
        }
        long j2 = 0;
        for (long j3 : jArrB2) {
            j2 += j3;
        }
        int i2 = (int) j2;
        byte[] bArr = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < jArrB.length; i5++) {
            int i6 = (int) jArrB[i5];
            int i7 = (int) jArrB2[i5];
            int i8 = i6 - i3;
            if (i8 < 0) {
                Log.d("ExifInterface", "Invalid strip offset value");
            }
            c0044a.l(i8);
            int i9 = i3 + i8;
            byte[] bArr2 = new byte[i7];
            c0044a.read(bArr2);
            i3 = i9 + i7;
            System.arraycopy(bArr2, 0, bArr, i4, i7);
            i4 += i7;
        }
        this.O = true;
        this.R = bArr;
        this.Q = i2;
    }

    private static boolean n(byte[] bArr) throws IOException {
        int i2 = 0;
        while (true) {
            byte[] bArr2 = f;
            if (i2 >= bArr2.length) {
                return true;
            }
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
            i2++;
        }
    }

    private boolean o(byte[] bArr) throws IOException {
        C0044a c0044a = new C0044a(bArr);
        ByteOrder byteOrderV = v(c0044a);
        this.N = byteOrderV;
        c0044a.m(byteOrderV);
        short s2 = c0044a.readShort();
        c0044a.close();
        return s2 == 20306 || s2 == 21330;
    }

    private boolean p(byte[] bArr) throws IOException {
        byte[] bytes = "FUJIFILMCCD-RAW".getBytes(Charset.defaultCharset());
        for (int i2 = 0; i2 < bytes.length; i2++) {
            if (bArr[i2] != bytes[i2]) {
                return false;
            }
        }
        return true;
    }

    private boolean q(byte[] bArr) throws IOException {
        C0044a c0044a = new C0044a(bArr);
        ByteOrder byteOrderV = v(c0044a);
        this.N = byteOrderV;
        c0044a.m(byteOrderV);
        short s2 = c0044a.readShort();
        c0044a.close();
        return s2 == 85;
    }

    private boolean r(HashMap map) throws Throwable {
        b bVar;
        b bVar2 = (b) map.get("BitsPerSample");
        if (bVar2 == null) {
            return false;
        }
        int[] iArr = (int[]) bVar2.k(this.N);
        int[] iArr2 = f2049c;
        if (Arrays.equals(iArr2, iArr)) {
            return true;
        }
        if (this.K != 3 || (bVar = (b) map.get("PhotometricInterpretation")) == null) {
            return false;
        }
        int i2 = bVar.i(this.N);
        return (i2 == 1 && Arrays.equals(iArr, f2051e)) || (i2 == 6 && Arrays.equals(iArr, iArr2));
    }

    private boolean s(HashMap map) throws IOException {
        b bVar = (b) map.get("ImageLength");
        b bVar2 = (b) map.get("ImageWidth");
        if (bVar == null || bVar2 == null) {
            return false;
        }
        return bVar.i(this.N) <= 512 && bVar2.i(this.N) <= 512;
    }

    private void t(InputStream inputStream) throws IOException {
        for (int i2 = 0; i2 < w.length; i2++) {
            try {
                try {
                    this.L[i2] = new HashMap<>();
                } catch (IOException unused) {
                    this.Y = false;
                }
            } finally {
                a();
            }
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, TsNormalNum.THOUSAND_FIVE);
        this.K = g(bufferedInputStream);
        C0044a c0044a = new C0044a(bufferedInputStream);
        switch (this.K) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 8:
            case 11:
                j(c0044a);
                break;
            case 4:
                f(c0044a, 0, 0);
                break;
            case 7:
                h(c0044a);
                break;
            case 9:
                i(c0044a);
                break;
            case 10:
                k(c0044a);
                break;
        }
        z(c0044a);
        this.Y = true;
    }

    private void u(C0044a c0044a, int i2) throws IOException {
        ByteOrder byteOrderV = v(c0044a);
        this.N = byteOrderV;
        c0044a.m(byteOrderV);
        int unsignedShort = c0044a.readUnsignedShort();
        int i3 = this.K;
        if (i3 != 7 && i3 != 10 && unsignedShort != 42) {
            throw new IOException("Invalid start code: " + Integer.toHexString(unsignedShort));
        }
        int i4 = c0044a.readInt();
        if (i4 < 8 || i4 >= i2) {
            throw new IOException("Invalid first Ifd offset: " + i4);
        }
        int i5 = i4 - 8;
        if (i5 <= 0 || c0044a.skipBytes(i5) == i5) {
            return;
        }
        throw new IOException("Couldn't jump to first Ifd: " + i5);
    }

    private ByteOrder v(C0044a c0044a) throws IOException {
        short s2 = c0044a.readShort();
        if (s2 == 18761) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        if (s2 == 19789) {
            return ByteOrder.BIG_ENDIAN;
        }
        throw new IOException("Invalid byte order: " + Integer.toHexString(s2));
    }

    private void w(byte[] bArr, int i2) throws IOException {
        C0044a c0044a = new C0044a(bArr);
        u(c0044a, bArr.length);
        x(c0044a, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x022f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void x(b.j.a.a.C0044a r24, int r25) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 807
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.j.a.a.x(b.j.a.a$a, int):void");
    }

    private void y(C0044a c0044a, int i2) throws IOException {
        b bVar;
        b bVar2 = this.L[i2].get("ImageLength");
        b bVar3 = this.L[i2].get("ImageWidth");
        if ((bVar2 == null || bVar3 == null) && (bVar = this.L[i2].get("JPEGInterchangeFormat")) != null) {
            f(c0044a, bVar.i(this.N), i2);
        }
    }

    private void z(C0044a c0044a) throws Throwable {
        HashMap<String, b> map = this.L[4];
        b bVar = map.get("Compression");
        if (bVar == null) {
            this.S = 6;
            l(c0044a, map);
            return;
        }
        int i2 = bVar.i(this.N);
        this.S = i2;
        if (i2 != 1) {
            if (i2 == 6) {
                l(c0044a, map);
                return;
            } else if (i2 != 7) {
                return;
            }
        }
        if (r(map)) {
            m(c0044a, map);
        }
    }

    public String c(String str) {
        b bVarE = e(str);
        if (bVarE != null) {
            if (!C.contains(str)) {
                return bVarE.j(this.N);
            }
            if (str.equals("GPSTimeStamp")) {
                int i2 = bVarE.f2056a;
                if (i2 != 5 && i2 != 10) {
                    Log.w("ExifInterface", "GPS Timestamp format is not rational. format=" + bVarE.f2056a);
                    return null;
                }
                d[] dVarArr = (d[]) bVarE.k(this.N);
                if (dVarArr != null && dVarArr.length == 3) {
                    return String.format("%02d:%02d:%02d", Integer.valueOf((int) (dVarArr[0].f2063a / dVarArr[0].f2064b)), Integer.valueOf((int) (dVarArr[1].f2063a / dVarArr[1].f2064b)), Integer.valueOf((int) (dVarArr[2].f2063a / dVarArr[2].f2064b)));
                }
                Log.w("ExifInterface", "Invalid GPS Timestamp array. array=" + Arrays.toString(dVarArr));
                return null;
            }
            try {
                return Double.toString(bVarE.h(this.N));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public int d(String str, int i2) {
        b bVarE = e(str);
        if (bVarE == null) {
            return i2;
        }
        try {
            return bVarE.i(this.N);
        } catch (NumberFormatException unused) {
            return i2;
        }
    }

    /* JADX INFO: renamed from: b.j.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ExifInterface.java */
    private static class C0044a extends InputStream implements DataInput {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final ByteOrder f2052b = ByteOrder.LITTLE_ENDIAN;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final ByteOrder f2053c = ByteOrder.BIG_ENDIAN;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private DataInputStream f2054d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private ByteOrder f2055e;
        final int f;
        int g;

        public C0044a(InputStream inputStream) throws IOException {
            this.f2055e = ByteOrder.BIG_ENDIAN;
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            this.f2054d = dataInputStream;
            int iAvailable = dataInputStream.available();
            this.f = iAvailable;
            this.g = 0;
            this.f2054d.mark(iAvailable);
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return this.f2054d.available();
        }

        public int j() {
            return this.g;
        }

        public long k() throws IOException {
            return ((long) readInt()) & 4294967295L;
        }

        public void l(long j) throws IOException {
            int i = this.g;
            if (i > j) {
                this.g = 0;
                this.f2054d.reset();
                this.f2054d.mark(this.f);
            } else {
                j -= (long) i;
            }
            int i2 = (int) j;
            if (skipBytes(i2) != i2) {
                throw new IOException("Couldn't seek up to the byteCount");
            }
        }

        public void m(ByteOrder byteOrder) {
            this.f2055e = byteOrder;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            this.g++;
            return this.f2054d.read();
        }

        @Override // java.io.DataInput
        public boolean readBoolean() throws IOException {
            this.g++;
            return this.f2054d.readBoolean();
        }

        @Override // java.io.DataInput
        public byte readByte() throws IOException {
            int i = this.g + 1;
            this.g = i;
            if (i > this.f) {
                throw new EOFException();
            }
            int i2 = this.f2054d.read();
            if (i2 >= 0) {
                return (byte) i2;
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public char readChar() throws IOException {
            this.g += 2;
            return this.f2054d.readChar();
        }

        @Override // java.io.DataInput
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readLong());
        }

        @Override // java.io.DataInput
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readInt());
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.g + i2;
            this.g = i3;
            if (i3 > this.f) {
                throw new EOFException();
            }
            if (this.f2054d.read(bArr, i, i2) != i2) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        @Override // java.io.DataInput
        public int readInt() throws IOException {
            int i = this.g + 4;
            this.g = i;
            if (i > this.f) {
                throw new EOFException();
            }
            int i2 = this.f2054d.read();
            int i3 = this.f2054d.read();
            int i4 = this.f2054d.read();
            int i5 = this.f2054d.read();
            if ((i2 | i3 | i4 | i5) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.f2055e;
            if (byteOrder == f2052b) {
                return (i5 << 24) + (i4 << 16) + (i3 << 8) + i2;
            }
            if (byteOrder == f2053c) {
                return (i2 << 24) + (i3 << 16) + (i4 << 8) + i5;
            }
            throw new IOException("Invalid byte order: " + this.f2055e);
        }

        @Override // java.io.DataInput
        public String readLine() throws IOException {
            Log.d("ExifInterface", "Currently unsupported");
            return null;
        }

        @Override // java.io.DataInput
        public long readLong() throws IOException {
            int i = this.g + 8;
            this.g = i;
            if (i > this.f) {
                throw new EOFException();
            }
            int i2 = this.f2054d.read();
            int i3 = this.f2054d.read();
            int i4 = this.f2054d.read();
            int i5 = this.f2054d.read();
            int i6 = this.f2054d.read();
            int i7 = this.f2054d.read();
            int i8 = this.f2054d.read();
            int i9 = this.f2054d.read();
            if ((i2 | i3 | i4 | i5 | i6 | i7 | i8 | i9) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.f2055e;
            if (byteOrder == f2052b) {
                return (((long) i9) << 56) + (((long) i8) << 48) + (((long) i7) << 40) + (((long) i6) << 32) + (((long) i5) << 24) + (((long) i4) << 16) + (((long) i3) << 8) + ((long) i2);
            }
            if (byteOrder == f2053c) {
                return (((long) i2) << 56) + (((long) i3) << 48) + (((long) i4) << 40) + (((long) i5) << 32) + (((long) i6) << 24) + (((long) i7) << 16) + (((long) i8) << 8) + ((long) i9);
            }
            throw new IOException("Invalid byte order: " + this.f2055e);
        }

        @Override // java.io.DataInput
        public short readShort() throws IOException {
            int i = this.g + 2;
            this.g = i;
            if (i > this.f) {
                throw new EOFException();
            }
            int i2 = this.f2054d.read();
            int i3 = this.f2054d.read();
            if ((i2 | i3) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.f2055e;
            if (byteOrder == f2052b) {
                return (short) ((i3 << 8) + i2);
            }
            if (byteOrder == f2053c) {
                return (short) ((i2 << 8) + i3);
            }
            throw new IOException("Invalid byte order: " + this.f2055e);
        }

        @Override // java.io.DataInput
        public String readUTF() throws IOException {
            this.g += 2;
            return this.f2054d.readUTF();
        }

        @Override // java.io.DataInput
        public int readUnsignedByte() throws IOException {
            this.g++;
            return this.f2054d.readUnsignedByte();
        }

        @Override // java.io.DataInput
        public int readUnsignedShort() throws IOException {
            int i = this.g + 2;
            this.g = i;
            if (i > this.f) {
                throw new EOFException();
            }
            int i2 = this.f2054d.read();
            int i3 = this.f2054d.read();
            if ((i2 | i3) < 0) {
                throw new EOFException();
            }
            ByteOrder byteOrder = this.f2055e;
            if (byteOrder == f2052b) {
                return (i3 << 8) + i2;
            }
            if (byteOrder == f2053c) {
                return (i2 << 8) + i3;
            }
            throw new IOException("Invalid byte order: " + this.f2055e);
        }

        @Override // java.io.DataInput
        public int skipBytes(int i) throws IOException {
            int iMin = Math.min(i, this.f - this.g);
            int iSkipBytes = 0;
            while (iSkipBytes < iMin) {
                iSkipBytes += this.f2054d.skipBytes(iMin - iSkipBytes);
            }
            this.g += iSkipBytes;
            return iSkipBytes;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.f2054d.read(bArr, i, i2);
            this.g += i3;
            return i3;
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr) throws IOException {
            int length = this.g + bArr.length;
            this.g = length;
            if (length <= this.f) {
                if (this.f2054d.read(bArr, 0, bArr.length) != bArr.length) {
                    throw new IOException("Couldn't read up to the length of buffer");
                }
                return;
            }
            throw new EOFException();
        }

        public C0044a(byte[] bArr) throws IOException {
            this(new ByteArrayInputStream(bArr));
        }
    }

    /* JADX INFO: compiled from: ExifInterface.java */
    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2059a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f2060b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f2061c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f2062d;

        c(String str, int i, int i2) {
            this.f2060b = str;
            this.f2059a = i;
            this.f2061c = i2;
            this.f2062d = -1;
        }

        boolean a(int i) {
            int i2;
            int i3 = this.f2061c;
            if (i3 == 7 || i == 7 || i3 == i || (i2 = this.f2062d) == i) {
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

        c(String str, int i, int i2, int i3) {
            this.f2060b = str;
            this.f2059a = i;
            this.f2061c = i2;
            this.f2062d = i3;
        }
    }
}
