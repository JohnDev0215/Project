package com.bugsnag.android.repackaged.dslplatform.json;

import androidx.annotation.Nullable;
import com.bugsnag.android.repackaged.dslplatform.json.JsonReader;
import com.bugsnag.android.repackaged.dslplatform.json.JsonWriter;
import com.visualon.OSMPUtils.voOSType;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes5.dex */
public abstract class NumberConverter {
    private static final BigDecimal BD_MAX_LONG;
    private static final BigDecimal BD_MIN_LONG;
    private static final byte[] MIN_INT;
    private static final byte[] MIN_LONG;
    public static final short[] SHORT_EMPTY_ARRAY = new short[0];
    public static final int[] INT_EMPTY_ARRAY = new int[0];
    public static final long[] LONG_EMPTY_ARRAY = new long[0];
    public static final float[] FLOAT_EMPTY_ARRAY = new float[0];
    public static final double[] DOUBLE_EMPTY_ARRAY = new double[0];
    public static final Short SHORT_ZERO = 0;
    public static final Integer INT_ZERO = 0;
    public static final Long LONG_ZERO = 0L;
    public static final Float FLOAT_ZERO = Float.valueOf(0.0f);
    public static final Double DOUBLE_ZERO = Double.valueOf(0.0d);
    private static final int[] DIGITS = new int[1000];
    private static final int[] DIFF = {111, 222, 444, 888, 1776};
    private static final int[] ERROR = {50, 100, 200, 400, 800};
    private static final int[] SCALE_10 = {10000, 1000, 100, 10, 1};
    private static final double[] POW_10 = {10.0d, 100.0d, 1000.0d, 10000.0d, 100000.0d, 1000000.0d, 1.0E7d, 1.0E8d, 1.0E9d, 1.0E10d, 1.0E11d, 1.0E12d, 1.0E13d, 1.0E14d, 1.0E15d, 1.0E16d, 1.0E17d, 1.0E18d, 1.0E19d, 1.0E20d, 1.0E21d, 1.0E22d, 1.0E23d, 1.0E24d, 1.0E25d, 1.0E26d, 1.0E27d, 1.0E28d, 1.0E29d, 1.0E30d, 1.0E31d, 1.0E32d, 1.0E33d, 1.0E34d, 1.0E35d, 1.0E36d, 1.0E37d, 1.0E38d, 1.0E39d, 1.0E40d, 1.0E41d, 1.0E42d, 1.0E43d, 1.0E44d, 1.0E45d, 1.0E46d, 1.0E47d, 1.0E48d, 1.0E49d, 1.0E50d, 1.0E51d, 1.0E52d, 1.0E53d, 1.0E54d, 1.0E55d, 1.0E56d, 1.0E57d, 1.0E58d, 1.0E59d, 1.0E60d, 1.0E61d, 1.0E62d, 1.0E63d, 1.0E64d, 1.0E65d};
    public static final JsonReader.ReadObject<Double> DOUBLE_READER = new JsonReader.ReadObject<Double>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public Double read(JsonReader jsonReader) throws IOException {
            return Double.valueOf(NumberConverter.deserializeDouble(jsonReader));
        }
    };
    public static final JsonReader.ReadObject<Double> NULLABLE_DOUBLE_READER = new JsonReader.ReadObject<Double>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public Double read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            return Double.valueOf(NumberConverter.deserializeDouble(jsonReader));
        }
    };
    public static final JsonWriter.WriteObject<Double> DOUBLE_WRITER = new JsonWriter.WriteObject<Double>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.3
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable Double d) {
            NumberConverter.serializeNullable(d, jsonWriter);
        }
    };
    public static final JsonReader.ReadObject<double[]> DOUBLE_ARRAY_READER = new JsonReader.ReadObject<double[]>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.4
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public double[] read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            if (jsonReader.last() != 91) {
                throw jsonReader.newParseError("Expecting '[' for double array start");
            }
            jsonReader.getNextToken();
            return NumberConverter.deserializeDoubleArray(jsonReader);
        }
    };
    public static final JsonWriter.WriteObject<double[]> DOUBLE_ARRAY_WRITER = new JsonWriter.WriteObject<double[]>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.5
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable double[] dArr) {
            NumberConverter.serialize(dArr, jsonWriter);
        }
    };
    public static final JsonReader.ReadObject<Float> FLOAT_READER = new JsonReader.ReadObject<Float>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.6
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        public Float read(JsonReader jsonReader) throws IOException {
            return Float.valueOf(NumberConverter.deserializeFloat(jsonReader));
        }
    };
    public static final JsonReader.ReadObject<Float> NULLABLE_FLOAT_READER = new JsonReader.ReadObject<Float>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.7
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public Float read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            return Float.valueOf(NumberConverter.deserializeFloat(jsonReader));
        }
    };
    public static final JsonWriter.WriteObject<Float> FLOAT_WRITER = new JsonWriter.WriteObject<Float>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.8
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable Float f) {
            NumberConverter.serializeNullable(f, jsonWriter);
        }
    };
    public static final JsonReader.ReadObject<float[]> FLOAT_ARRAY_READER = new JsonReader.ReadObject<float[]>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.9
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public float[] read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            if (jsonReader.last() != 91) {
                throw jsonReader.newParseError("Expecting '[' for float array start");
            }
            jsonReader.getNextToken();
            return NumberConverter.deserializeFloatArray(jsonReader);
        }
    };
    public static final JsonWriter.WriteObject<float[]> FLOAT_ARRAY_WRITER = new JsonWriter.WriteObject<float[]>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.10
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable float[] fArr) {
            NumberConverter.serialize(fArr, jsonWriter);
        }
    };
    public static final JsonReader.ReadObject<Integer> INT_READER = new JsonReader.ReadObject<Integer>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.11
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        public Integer read(JsonReader jsonReader) throws IOException {
            return Integer.valueOf(NumberConverter.deserializeInt(jsonReader));
        }
    };
    public static final JsonReader.ReadObject<Integer> NULLABLE_INT_READER = new JsonReader.ReadObject<Integer>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.12
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public Integer read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            return Integer.valueOf(NumberConverter.deserializeInt(jsonReader));
        }
    };
    public static final JsonWriter.WriteObject<Integer> INT_WRITER = new JsonWriter.WriteObject<Integer>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.13
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable Integer num) {
            NumberConverter.serializeNullable(num, jsonWriter);
        }
    };
    public static final JsonReader.ReadObject<int[]> INT_ARRAY_READER = new JsonReader.ReadObject<int[]>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.14
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public int[] read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            if (jsonReader.last() != 91) {
                throw jsonReader.newParseError("Expecting '[' for int array start");
            }
            jsonReader.getNextToken();
            return NumberConverter.deserializeIntArray(jsonReader);
        }
    };
    public static final JsonWriter.WriteObject<int[]> INT_ARRAY_WRITER = new JsonWriter.WriteObject<int[]>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.15
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable int[] iArr) {
            NumberConverter.serialize(iArr, jsonWriter);
        }
    };
    public static final JsonReader.ReadObject<Short> SHORT_READER = new JsonReader.ReadObject<Short>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.16
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        public Short read(JsonReader jsonReader) throws IOException {
            return Short.valueOf(NumberConverter.deserializeShort(jsonReader));
        }
    };
    public static final JsonReader.ReadObject<Short> NULLABLE_SHORT_READER = new JsonReader.ReadObject<Short>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.17
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public Short read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            return Short.valueOf(NumberConverter.deserializeShort(jsonReader));
        }
    };
    public static final JsonWriter.WriteObject<Short> SHORT_WRITER = new JsonWriter.WriteObject<Short>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.18
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable Short sh) {
            if (sh == null) {
                jsonWriter.writeNull();
            } else {
                NumberConverter.serialize(sh.intValue(), jsonWriter);
            }
        }
    };
    public static final JsonReader.ReadObject<short[]> SHORT_ARRAY_READER = new JsonReader.ReadObject<short[]>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.19
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public short[] read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            if (jsonReader.last() != 91) {
                throw jsonReader.newParseError("Expecting '[' for short array start");
            }
            jsonReader.getNextToken();
            return NumberConverter.deserializeShortArray(jsonReader);
        }
    };
    public static final JsonWriter.WriteObject<short[]> SHORT_ARRAY_WRITER = new JsonWriter.WriteObject<short[]>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.20
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable short[] sArr) {
            NumberConverter.serialize(sArr, jsonWriter);
        }
    };
    public static final JsonReader.ReadObject<Long> LONG_READER = new JsonReader.ReadObject<Long>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.21
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        public Long read(JsonReader jsonReader) throws IOException {
            return Long.valueOf(NumberConverter.deserializeLong(jsonReader));
        }
    };
    public static final JsonReader.ReadObject<Long> NULLABLE_LONG_READER = new JsonReader.ReadObject<Long>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.22
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public Long read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            return Long.valueOf(NumberConverter.deserializeLong(jsonReader));
        }
    };
    public static final JsonWriter.WriteObject<Long> LONG_WRITER = new JsonWriter.WriteObject<Long>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.23
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable Long l) {
            NumberConverter.serializeNullable(l, jsonWriter);
        }
    };
    public static final JsonReader.ReadObject<long[]> LONG_ARRAY_READER = new JsonReader.ReadObject<long[]>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.24
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public long[] read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            if (jsonReader.last() != 91) {
                throw jsonReader.newParseError("Expecting '[' for long array start");
            }
            jsonReader.getNextToken();
            return NumberConverter.deserializeLongArray(jsonReader);
        }
    };
    public static final JsonWriter.WriteObject<long[]> LONG_ARRAY_WRITER = new JsonWriter.WriteObject<long[]>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.25
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable long[] jArr) {
            NumberConverter.serialize(jArr, jsonWriter);
        }
    };
    public static final JsonReader.ReadObject<BigDecimal> DecimalReader = new JsonReader.ReadObject<BigDecimal>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.26
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public BigDecimal read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            return NumberConverter.deserializeDecimal(jsonReader);
        }
    };
    public static final JsonWriter.WriteObject<BigDecimal> DecimalWriter = new JsonWriter.WriteObject<BigDecimal>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.27
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable BigDecimal bigDecimal) {
            NumberConverter.serializeNullable(bigDecimal, jsonWriter);
        }
    };
    static final JsonReader.ReadObject<Number> NumberReader = new JsonReader.ReadObject<Number>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.28
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            return NumberConverter.deserializeNumber(jsonReader);
        }
    };

    static {
        int i = 0;
        while (true) {
            int[] iArr = DIGITS;
            if (i < iArr.length) {
                iArr[i] = (i < 10 ? voOSType.VOOSMP_SRC_FFAUDIO_WMA : i < 100 ? 16777216 : 0) + (((i / 100) + 48) << 16) + ((((i / 10) % 10) + 48) << 8) + (i % 10) + 48;
                i++;
            } else {
                MIN_INT = "-2147483648".getBytes();
                MIN_LONG = "-9223372036854775808".getBytes();
                BD_MAX_LONG = BigDecimal.valueOf(Long.MAX_VALUE);
                BD_MIN_LONG = BigDecimal.valueOf(Long.MIN_VALUE);
                return;
            }
        }
    }

    static void numberException(JsonReader jsonReader, int i, int i2, String str) throws ParsingException {
        int i3 = i2 - i;
        if (i3 > jsonReader.maxNumberDigits) {
            throw jsonReader.newParseErrorWith("Too many digits detected in number", i3, "", "Too many digits detected in number", Integer.valueOf(i2), "");
        }
        throw jsonReader.newParseErrorWith("Error parsing number", i3, "", str, null, ". Error parsing number");
    }

    static void numberException(JsonReader jsonReader, int i, int i2, String str, Object obj) throws ParsingException {
        int i3 = i2 - i;
        if (i3 > jsonReader.maxNumberDigits) {
            throw jsonReader.newParseErrorWith("Too many digits detected in number", i3, "", "Too many digits detected in number", Integer.valueOf(i2), "");
        }
        throw jsonReader.newParseErrorWith("Error parsing number", i3, "", str, obj, ". Error parsing number");
    }

    public static void serializeNullable(@Nullable Double d, JsonWriter jsonWriter) {
        if (d == null) {
            jsonWriter.writeNull();
        } else {
            serialize(d.doubleValue(), jsonWriter);
        }
    }

    private static BigDecimal parseNumberGeneric(char[] cArr, int i, JsonReader jsonReader, boolean z) throws ParsingException {
        int i2;
        char c;
        int i3 = i;
        while (i3 > 0 && Character.isWhitespace(cArr[i3 - 1])) {
            i3--;
        }
        if (i3 > jsonReader.maxNumberDigits) {
            throw jsonReader.newParseErrorWith("Too many digits detected in number", i, "", "Too many digits detected in number", Integer.valueOf(i3), "");
        }
        int i4 = cArr[0] == '-' ? 1 : 0;
        if (cArr[i4] == '0' && i3 > (i2 = i4 + 1) && (c = cArr[i2]) >= '0' && c <= '9') {
            throw jsonReader.newParseErrorAt("Leading zero is not allowed. Error parsing number", i + (z ? 2 : 0));
        }
        try {
            return new BigDecimal(cArr, 0, i3);
        } catch (NumberFormatException e) {
            throw jsonReader.newParseErrorAt("Error parsing number", i + (z ? 2 : 0), e);
        }
    }

    public static void serialize(double d, JsonWriter jsonWriter) {
        jsonWriter.writeDouble(d);
    }

    public static void serialize(@Nullable double[] dArr, JsonWriter jsonWriter) {
        if (dArr == null) {
            jsonWriter.writeNull();
            return;
        }
        if (dArr.length == 0) {
            jsonWriter.writeAscii(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        jsonWriter.writeByte((byte) 91);
        serialize(dArr[0], jsonWriter);
        for (int i = 1; i < dArr.length; i++) {
            jsonWriter.writeByte((byte) 44);
            serialize(dArr[i], jsonWriter);
        }
        jsonWriter.writeByte((byte) 93);
    }

    private static class NumberInfo {
        final char[] buffer;
        final int length;

        NumberInfo(char[] cArr, int i) {
            this.buffer = cArr;
            this.length = i;
        }
    }

    private static NumberInfo readLongNumber(JsonReader jsonReader, int i) throws IOException {
        int length = jsonReader.length() - i;
        char[] cArrPrepareBuffer = jsonReader.prepareBuffer(i, length);
        while (jsonReader.length() == jsonReader.getCurrentIndex() && !jsonReader.isEndOfStream()) {
            jsonReader.scanNumber();
            int currentIndex = jsonReader.getCurrentIndex();
            int i2 = length + currentIndex;
            int i3 = jsonReader.maxNumberDigits;
            if (i2 > i3) {
                throw jsonReader.newParseErrorFormat("Too many digits detected in number", i2, "Number of digits larger than %d. Unable to read number", Integer.valueOf(i3));
            }
            char[] cArr = new char[i2];
            System.arraycopy(cArrPrepareBuffer, 0, cArr, 0, length);
            System.arraycopy(jsonReader.prepareBuffer(0, currentIndex), 0, cArr, length, currentIndex);
            length = i2;
            cArrPrepareBuffer = cArr;
        }
        return new NumberInfo(cArrPrepareBuffer, length);
    }

    public static double deserializeDouble(JsonReader jsonReader) throws IOException {
        if (jsonReader.last() == 34) {
            return parseDoubleGeneric(jsonReader.readSimpleQuote(), (jsonReader.getCurrentIndex() - jsonReader.getCurrentIndex()) - 1, jsonReader, true);
        }
        int iScanNumber = jsonReader.scanNumber();
        int currentIndex = jsonReader.getCurrentIndex();
        byte[] bArr = jsonReader.buffer;
        if (bArr[iScanNumber] == 45) {
            return -parseDouble(bArr, jsonReader, iScanNumber, currentIndex, 1);
        }
        return parseDouble(bArr, jsonReader, iScanNumber, currentIndex, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0167, code lost:
    
        r4 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0168, code lost:
    
        r1 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0169, code lost:
    
        if (r1 >= r4) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x016b, code lost:
    
        r6 = r24[r1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x016f, code lost:
    
        if (r6 == 101) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0173, code lost:
    
        if (r6 != 69) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0176, code lost:
    
        r2 = r6 - 48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x017a, code lost:
    
        if (r2 < 0) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x017c, code lost:
    
        if (r2 <= 9) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0182, code lost:
    
        if (r25.allWhitespace(r1, r27) == false) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x018d, code lost:
    
        return approximateDouble(r9, r12 / r17, (r1 - r5) - r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x018e, code lost:
    
        numberException(r25, r26, r27, "Unknown digit", java.lang.Character.valueOf((char) r24[r1]));
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0198, code lost:
    
        r9 = ((r9 << 3) + (r9 << 1)) + r2;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x01a2, code lost:
    
        r4 = approximateDouble(r9, r12 / r17, (r1 - r5) - r15);
        r11 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x01ad, code lost:
    
        if (r11 >= r27) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x01b1, code lost:
    
        if (r6 < 48) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x01b5, code lost:
    
        if (r6 > 57) goto L170;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x01b7, code lost:
    
        r6 = r24[r11];
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x01bf, code lost:
    
        if (r6 == 101) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x01c3, code lost:
    
        if (r6 != 69) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x01c6, code lost:
    
        if (r10 <= 0) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x01cf, code lost:
    
        return r4 * com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.POW_10[r10 - 1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x01d1, code lost:
    
        if (r10 >= 0) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x01da, code lost:
    
        return r4 / com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.POW_10[(-r10) - 1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x01ed, code lost:
    
        return doubleExponent(r25, 0, r10, r4, r24, r26, r27, r28, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x0204, code lost:
    
        return doubleExponent(r25, r12, r5 - r14, 0.0d, r24, r26, r27, r28, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0206, code lost:
    
        if (r2 == 101) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x020a, code lost:
    
        if (r2 != 69) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x020e, code lost:
    
        return r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0222, code lost:
    
        return doubleExponent(r25, r12, 0, 0.0d, r24, r26, r27, r28, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:?, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0086, code lost:
    
        if (r14 != r4) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0088, code lost:
    
        numberException(r25, r26, r27, "Digit not found");
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008f, code lost:
    
        if (r5 == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0091, code lost:
    
        if (r2 == 46) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0095, code lost:
    
        if (r14 <= (r4 + 1)) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0097, code lost:
    
        numberException(r25, r26, r27, "Leading zero is not allowed");
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x009c, code lost:
    
        if (r14 != r27) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009f, code lost:
    
        return r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a0, code lost:
    
        if (r2 != 46) goto L140;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a2, code lost:
    
        r14 = r14 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a4, code lost:
    
        if (r14 != r27) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a6, code lost:
    
        numberException(r25, r26, r27, "Number ends with a dot");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ab, code lost:
    
        r17 = 1.0E14d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00bb, code lost:
    
        if (r12 != 0) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00bd, code lost:
    
        r2 = r14 + 15;
        r5 = r24[r14];
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00c3, code lost:
    
        if (r5 != 48) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00c5, code lost:
    
        if (r27 <= r2) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d0, code lost:
    
        return parseDoubleGeneric(r25.prepareBuffer(r4, r1), r1, r25, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00d1, code lost:
    
        if (r5 >= 56) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d3, code lost:
    
        r10 = -1;
        r15 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00d6, code lost:
    
        r17 = 1.0E15d;
        r10 = 0;
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00db, code lost:
    
        r5 = r4 + 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00df, code lost:
    
        if (r24[r4] >= 56) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00e1, code lost:
    
        r10 = (r14 - r5) + 14;
        r15 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00e7, code lost:
    
        r10 = (r14 - r5) + 15;
        r17 = 1.0E15d;
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00ee, code lost:
    
        r5 = r2;
        r2 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00f3, code lost:
    
        if (r2 >= r27) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00f6, code lost:
    
        r2 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00f7, code lost:
    
        r19 = r5;
        r5 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00fa, code lost:
    
        if (r5 >= r2) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00fc, code lost:
    
        r6 = r24[r5];
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00fe, code lost:
    
        if (r6 == r9) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0100, code lost:
    
        if (r6 != r11) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0103, code lost:
    
        r11 = r6 - 48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0105, code lost:
    
        if (r11 < 0) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0109, code lost:
    
        if (r11 <= 9) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x010f, code lost:
    
        if (r25.allWhitespace(r5, r27) == false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x011a, code lost:
    
        return r12 / com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.POW_10[(r5 - r14) - 1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x011b, code lost:
    
        numberException(r25, r26, r27, "Unknown digit", java.lang.Character.valueOf((char) r24[r5]));
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0125, code lost:
    
        r12 = ((r12 << 3) + (r12 << 1)) + ((long) r11);
        r5 = r5 + 1;
        r19 = r6;
        r9 = 101;
        r11 = 69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0139, code lost:
    
        r6 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x013b, code lost:
    
        if (r5 != r27) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0146, code lost:
    
        return r12 / com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.POW_10[(r5 - r14) - 1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0149, code lost:
    
        if (r6 == 101) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x014d, code lost:
    
        if (r6 != 69) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0155, code lost:
    
        if (r25.doublePrecision != com.bugsnag.android.repackaged.dslplatform.json.JsonReader.DoublePrecision.HIGH) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0160, code lost:
    
        return parseDoubleGeneric(r25.prepareBuffer(r4, r1), r1, r25, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0161, code lost:
    
        r9 = 0;
        r4 = r4 + 18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0164, code lost:
    
        if (r4 >= r27) goto L100;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static double parseDouble(byte[] r24, com.bugsnag.android.repackaged.dslplatform.json.JsonReader r25, int r26, int r27, int r28) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 547
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.parseDouble(byte[], com.bugsnag.android.repackaged.dslplatform.json.JsonReader, int, int, int):double");
    }

    private static double approximateDouble(int i, double d, int i2) {
        long jDoubleToRawLongBits = Double.doubleToRawLongBits(d);
        int i3 = ((int) (jDoubleToRawLongBits >> 52)) - 1022;
        return Double.longBitsToDouble(jDoubleToRawLongBits + ((long) (((i * SCALE_10[i2 + 1]) + ERROR[i3]) / DIFF[i3])));
    }

    private static double doubleExponent(JsonReader jsonReader, long j, int i, double d, byte[] bArr, int i2, int i3, int i4, int i5) throws IOException {
        int positiveInt;
        double d2;
        double d3;
        if (jsonReader.doublePrecision == JsonReader.DoublePrecision.EXACT) {
            int i6 = (i3 - i2) - i4;
            return parseDoubleGeneric(jsonReader.prepareBuffer(i2 + i4, i6), i6, jsonReader, false);
        }
        int i7 = i5 + 1;
        byte b = bArr[i7];
        if (b == 45) {
            positiveInt = parseNegativeInt(bArr, jsonReader, i7, i3);
        } else if (b == 43) {
            positiveInt = parsePositiveInt(bArr, jsonReader, i7, i3, 1);
        } else {
            positiveInt = parsePositiveInt(bArr, jsonReader, i7, i3, 0);
        }
        int i8 = positiveInt - i;
        if (d == 0.0d) {
            if (i8 == 0 || j == 0) {
                return j;
            }
            if (i8 > 0) {
                double[] dArr = POW_10;
                if (i8 < dArr.length) {
                    return j * dArr[i8 - 1];
                }
            }
            if (i8 < 0) {
                int i9 = -i8;
                double[] dArr2 = POW_10;
                if (i9 < dArr2.length) {
                    return j / dArr2[i9 - 1];
                }
            }
            if (jsonReader.doublePrecision != JsonReader.DoublePrecision.HIGH) {
                if (i8 > 0 && i8 < 300) {
                    return j * Math.pow(10.0d, i8);
                }
                if (i8 > -300 && i8 < 0) {
                    return j / Math.pow(10.0d, i8);
                }
            }
        } else {
            if (i8 == 0) {
                return j + d;
            }
            if (i8 > 0) {
                double[] dArr3 = POW_10;
                if (i8 < dArr3.length) {
                    double d4 = dArr3[i8 - 1];
                    d2 = d * d4;
                    d3 = j * d4;
                }
                return d2 + d3;
            }
            if (i8 < 0) {
                int i10 = -i8;
                double[] dArr4 = POW_10;
                if (i10 < dArr4.length) {
                    double d5 = dArr4[i10 - 1];
                    d2 = d / d5;
                    d3 = j / d5;
                    return d2 + d3;
                }
            }
            if (jsonReader.doublePrecision != JsonReader.DoublePrecision.HIGH) {
                if (i8 > 0 && i8 < 300) {
                    return j * Math.pow(10.0d, i8);
                }
                if (i8 > -300 && i8 < 0) {
                    return j / Math.pow(10.0d, i8);
                }
            }
        }
        int i11 = (i3 - i2) - i4;
        return parseDoubleGeneric(jsonReader.prepareBuffer(i2 + i4, i11), i11, jsonReader, false);
    }

    private static double parseDoubleGeneric(char[] cArr, int i, JsonReader jsonReader, boolean z) throws IOException {
        int i2;
        char c;
        int i3 = i;
        while (i3 > 0 && Character.isWhitespace(cArr[i3 - 1])) {
            i3--;
        }
        if (i3 > jsonReader.maxNumberDigits) {
            throw jsonReader.newParseErrorWith("Too many digits detected in number", i, "", "Too many digits detected in number", Integer.valueOf(i3), "");
        }
        int i4 = cArr[0] == '-' ? 1 : 0;
        if (cArr[i4] == '0' && i3 > (i2 = i4 + 1) && (c = cArr[i2]) >= '0' && c <= '9') {
            throw jsonReader.newParseErrorAt("Leading zero is not allowed. Error parsing number", i + (z ? 2 : 0));
        }
        try {
            return Double.parseDouble(new String(cArr, 0, i3));
        } catch (NumberFormatException e) {
            throw jsonReader.newParseErrorAt("Error parsing number", i + (z ? 2 : 0), e);
        }
    }

    public static void serializeNullable(@Nullable Float f, JsonWriter jsonWriter) {
        if (f == null) {
            jsonWriter.writeNull();
        } else {
            serialize(f.floatValue(), jsonWriter);
        }
    }

    public static void serialize(float f, JsonWriter jsonWriter) {
        if (f == Float.POSITIVE_INFINITY) {
            jsonWriter.writeAscii("\"Infinity\"");
            return;
        }
        if (f == Float.NEGATIVE_INFINITY) {
            jsonWriter.writeAscii("\"-Infinity\"");
        } else if (f != f) {
            jsonWriter.writeAscii("\"NaN\"");
        } else {
            jsonWriter.writeAscii(Float.toString(f));
        }
    }

    public static void serialize(@Nullable float[] fArr, JsonWriter jsonWriter) {
        if (fArr == null) {
            jsonWriter.writeNull();
            return;
        }
        if (fArr.length == 0) {
            jsonWriter.writeAscii(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        jsonWriter.writeByte((byte) 91);
        serialize(fArr[0], jsonWriter);
        for (int i = 1; i < fArr.length; i++) {
            jsonWriter.writeByte((byte) 44);
            serialize(fArr[i], jsonWriter);
        }
        jsonWriter.writeByte((byte) 93);
    }

    public static float deserializeFloat(JsonReader jsonReader) throws IOException {
        if (jsonReader.last() == 34) {
            return parseFloatGeneric(jsonReader.readSimpleQuote(), (jsonReader.getCurrentIndex() - jsonReader.getCurrentIndex()) - 1, jsonReader, true);
        }
        int iScanNumber = jsonReader.scanNumber();
        int currentIndex = jsonReader.getCurrentIndex();
        if (currentIndex == jsonReader.length()) {
            NumberInfo longNumber = readLongNumber(jsonReader, iScanNumber);
            return parseFloatGeneric(longNumber.buffer, longNumber.length, jsonReader, false);
        }
        byte[] bArr = jsonReader.buffer;
        if (bArr[iScanNumber] == 45) {
            return -parseFloat(bArr, jsonReader, iScanNumber, currentIndex, 1);
        }
        return parseFloat(bArr, jsonReader, iScanNumber, currentIndex, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0136, code lost:
    
        if (r3 <= 0) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0141, code lost:
    
        return (float) (r10 / com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.POW_10[r3 - 1]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0143, code lost:
    
        if (r3 >= 0) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x014e, code lost:
    
        return (float) (r10 * com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.POW_10[(-r3) - 1]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0150, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x015c, code lost:
    
        return floatExponent(r21, r10, r3 - r2, r5, r6, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x015d, code lost:
    
        if (r12 == 101) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x015f, code lost:
    
        if (r12 != 69) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0163, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0173, code lost:
    
        return floatExponent(r21, r10, 0, r20, r23, r13 + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005e, code lost:
    
        if (r13 != r2) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0060, code lost:
    
        numberException(r21, r22, r23, "Digit not found");
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0067, code lost:
    
        if (r3 == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0069, code lost:
    
        if (r12 == 46) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006d, code lost:
    
        if (r13 <= (r2 + 1)) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006f, code lost:
    
        numberException(r21, r22, r23, "Leading zero is not allowed");
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0076, code lost:
    
        if (r13 <= (r2 + 18)) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0078, code lost:
    
        r1 = (r23 - r22) - r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0085, code lost:
    
        return parseFloatGeneric(r21.prepareBuffer(r2, r1), r1, r21, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0087, code lost:
    
        if (r13 != r23) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008a, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x008b, code lost:
    
        if (r12 != 46) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x008d, code lost:
    
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x008f, code lost:
    
        if (r13 != r23) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0091, code lost:
    
        numberException(r21, r22, r23, "Number ends with a dot");
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x009a, code lost:
    
        if (r10 != 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x009c, code lost:
    
        r2 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x009e, code lost:
    
        if (r13 >= r23) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00a4, code lost:
    
        if (r20[r13] != 48) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00a6, code lost:
    
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00a9, code lost:
    
        r8 = r13 + 17;
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00ad, code lost:
    
        r8 = r2 + 17;
        r2 = r13;
        r9 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00b1, code lost:
    
        if (r8 >= r23) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00b4, code lost:
    
        r8 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00b5, code lost:
    
        if (r13 >= r8) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00b7, code lost:
    
        r12 = r20[r13];
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00b9, code lost:
    
        if (r12 == 101) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00bb, code lost:
    
        if (r12 != 69) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00be, code lost:
    
        r3 = r12 - 48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00c0, code lost:
    
        if (r3 < 0) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00c2, code lost:
    
        if (r3 <= r6) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00c8, code lost:
    
        if (r21.allWhitespace(r13, r23) == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00d3, code lost:
    
        return (float) (r10 / com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.POW_10[(r13 - r2) - r9]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00d4, code lost:
    
        numberException(r21, r22, r23, "Unknown digit", java.lang.Character.valueOf((char) r12));
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00dc, code lost:
    
        r10 = ((r10 << 3) + (r10 << 1)) + ((long) r3);
        r13 = r13 + 1;
        r6 = 9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00eb, code lost:
    
        r13 = r13 + 1;
        r17 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00f0, code lost:
    
        r17 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00f4, code lost:
    
        if (r13 != r8) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00f6, code lost:
    
        if (r17 != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00f8, code lost:
    
        r3 = (r13 + 1) - r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00fb, code lost:
    
        if (r13 >= r23) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00ff, code lost:
    
        if (r12 < 48) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0101, code lost:
    
        if (r12 > 57) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0103, code lost:
    
        r12 = r20[r13];
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0109, code lost:
    
        r3 = r13 - r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x010b, code lost:
    
        r6 = r23;
        r5 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x010e, code lost:
    
        if (r13 != r6) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0114, code lost:
    
        if (r21.length() != r6) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0116, code lost:
    
        r5 = r21.scanNumber();
        r6 = r21.getCurrentIndex();
        r8 = r21.buffer;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0120, code lost:
    
        r13 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0123, code lost:
    
        if (r13 >= r6) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0125, code lost:
    
        if (r12 < 48) goto L140;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0127, code lost:
    
        if (r12 > 57) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0129, code lost:
    
        r5 = r13 + 1;
        r12 = r8[r13];
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x012e, code lost:
    
        r5 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0130, code lost:
    
        if (r12 == 101) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0132, code lost:
    
        if (r12 != 69) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0135, code lost:
    
        r3 = r3 - r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static float parseFloat(byte[] r20, com.bugsnag.android.repackaged.dslplatform.json.JsonReader r21, int r22, int r23, int r24) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 372
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.parseFloat(byte[], com.bugsnag.android.repackaged.dslplatform.json.JsonReader, int, int, int):float");
    }

    private static float floatExponent(JsonReader jsonReader, long j, int i, byte[] bArr, int i2, int i3) throws IOException {
        int positiveInt;
        byte b = bArr[i3];
        if (b == 45) {
            positiveInt = parseNegativeInt(bArr, jsonReader, i3, i2);
        } else if (b == 43) {
            positiveInt = parsePositiveInt(bArr, jsonReader, i3, i2, 1);
        } else {
            positiveInt = parsePositiveInt(bArr, jsonReader, i3, i2, 0);
        }
        int i4 = positiveInt - i;
        if (i4 == 0 || j == 0) {
            return j;
        }
        if (i4 > 0) {
            double[] dArr = POW_10;
            if (i4 < dArr.length) {
                return (float) (j * dArr[i4 - 1]);
            }
        }
        if (i4 < 0) {
            int i5 = -i4;
            double[] dArr2 = POW_10;
            if (i5 < dArr2.length) {
                return (float) (j / dArr2[i5 - 1]);
            }
        }
        return i4 > 0 ? Float.POSITIVE_INFINITY : 0.0f;
    }

    private static float parseFloatGeneric(char[] cArr, int i, JsonReader jsonReader, boolean z) throws ParsingException {
        int i2;
        char c;
        int i3 = i;
        while (i3 > 0 && Character.isWhitespace(cArr[i3 - 1])) {
            i3--;
        }
        if (i3 > jsonReader.maxNumberDigits) {
            throw jsonReader.newParseErrorWith("Too many digits detected in number", i, "", "Too many digits detected in number", Integer.valueOf(i3), "");
        }
        int i4 = cArr[0] == '-' ? 1 : 0;
        if (cArr[i4] == '0' && i3 > (i2 = i4 + 1) && (c = cArr[i2]) >= '0' && c <= '9') {
            throw jsonReader.newParseErrorAt("Leading zero is not allowed. Error parsing number", i + (z ? 2 : 0));
        }
        try {
            return Float.parseFloat(new String(cArr, 0, i3));
        } catch (NumberFormatException e) {
            throw jsonReader.newParseErrorAt("Error parsing number", i + (z ? 2 : 0), e);
        }
    }

    public static void serializeNullable(@Nullable Integer num, JsonWriter jsonWriter) {
        if (num == null) {
            jsonWriter.writeNull();
        } else {
            serialize(num.intValue(), jsonWriter);
        }
    }

    public static void serialize(int i, JsonWriter jsonWriter) {
        byte[] bArrEnsureCapacity = jsonWriter.ensureCapacity(11);
        int size = jsonWriter.size();
        jsonWriter.advance(serialize(bArrEnsureCapacity, size, i) - size);
    }

    private static int serialize(byte[] bArr, int i, int i2) {
        int iWriteFirstBuf;
        if (i2 < 0) {
            if (i2 == Integer.MIN_VALUE) {
                int i3 = 0;
                while (true) {
                    byte[] bArr2 = MIN_INT;
                    if (i3 < bArr2.length) {
                        bArr[i + i3] = bArr2[i3];
                        i3++;
                    } else {
                        return i + bArr2.length;
                    }
                }
            } else {
                i2 = -i2;
                bArr[i] = 45;
                i++;
            }
        }
        int i4 = i2 / 1000;
        if (i4 == 0) {
            return i + writeFirstBuf(bArr, DIGITS[i2], i);
        }
        int i5 = i2 - (i4 * 1000);
        int i6 = i4 / 1000;
        if (i6 == 0) {
            int[] iArr = DIGITS;
            int i7 = iArr[i5];
            int iWriteFirstBuf2 = writeFirstBuf(bArr, iArr[i4], i);
            writeBuf(bArr, i7, i + iWriteFirstBuf2);
            return i + 3 + iWriteFirstBuf2;
        }
        int i8 = i6 / 1000;
        int[] iArr2 = DIGITS;
        int i9 = iArr2[i5];
        int i10 = iArr2[i4 - (i6 * 1000)];
        if (i8 == 0) {
            iWriteFirstBuf = i + writeFirstBuf(bArr, iArr2[i6], i);
        } else {
            int i11 = i + 1;
            bArr[i] = (byte) (i8 + 48);
            writeBuf(bArr, iArr2[i6 - (i8 * 1000)], i11);
            iWriteFirstBuf = i11 + 3;
        }
        writeBuf(bArr, i10, iWriteFirstBuf);
        writeBuf(bArr, i9, iWriteFirstBuf + 3);
        return iWriteFirstBuf + 6;
    }

    public static void serialize(@Nullable int[] iArr, JsonWriter jsonWriter) {
        if (iArr == null) {
            jsonWriter.writeNull();
            return;
        }
        if (iArr.length == 0) {
            jsonWriter.writeAscii(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        byte[] bArrEnsureCapacity = jsonWriter.ensureCapacity((iArr.length * 11) + 2);
        int size = jsonWriter.size();
        bArrEnsureCapacity[size] = 91;
        int iSerialize = serialize(bArrEnsureCapacity, size + 1, iArr[0]);
        for (int i = 1; i < iArr.length; i++) {
            bArrEnsureCapacity[iSerialize] = 44;
            iSerialize = serialize(bArrEnsureCapacity, iSerialize + 1, iArr[i]);
        }
        bArrEnsureCapacity[iSerialize] = 93;
        jsonWriter.advance((iSerialize + 1) - jsonWriter.size());
    }

    public static void serialize(@Nullable short[] sArr, JsonWriter jsonWriter) {
        if (sArr == null) {
            jsonWriter.writeNull();
            return;
        }
        if (sArr.length == 0) {
            jsonWriter.writeAscii(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        jsonWriter.writeByte((byte) 91);
        serialize((int) sArr[0], jsonWriter);
        for (int i = 1; i < sArr.length; i++) {
            jsonWriter.writeByte((byte) 44);
            serialize((int) sArr[i], jsonWriter);
        }
        jsonWriter.writeByte((byte) 93);
    }

    public static short deserializeShort(JsonReader jsonReader) throws IOException {
        int positiveInt;
        if (jsonReader.last() == 34) {
            int currentIndex = jsonReader.getCurrentIndex();
            try {
                return parseNumberGeneric(jsonReader.readSimpleQuote(), (jsonReader.getCurrentIndex() - currentIndex) - 1, jsonReader, true).shortValueExact();
            } catch (ArithmeticException unused) {
                throw jsonReader.newParseErrorAt("Short overflow detected", jsonReader.getCurrentIndex() - currentIndex);
            }
        }
        int iScanNumber = jsonReader.scanNumber();
        int currentIndex2 = jsonReader.getCurrentIndex();
        byte[] bArr = jsonReader.buffer;
        if (bArr[iScanNumber] == 45) {
            positiveInt = parseNegativeInt(bArr, jsonReader, iScanNumber, currentIndex2);
        } else {
            positiveInt = parsePositiveInt(bArr, jsonReader, iScanNumber, currentIndex2, 0);
        }
        if (positiveInt < -32768 || positiveInt > 32767) {
            throw jsonReader.newParseErrorAt("Short overflow detected", jsonReader.getCurrentIndex());
        }
        return (short) positiveInt;
    }

    public static int deserializeInt(JsonReader jsonReader) throws IOException {
        int i;
        byte b;
        byte b2;
        if (jsonReader.last() == 34) {
            int currentIndex = jsonReader.getCurrentIndex();
            try {
                return parseNumberGeneric(jsonReader.readSimpleQuote(), (jsonReader.getCurrentIndex() - currentIndex) - 1, jsonReader, true).intValueExact();
            } catch (ArithmeticException unused) {
                throw jsonReader.newParseErrorAt("Integer overflow detected", jsonReader.getCurrentIndex() - currentIndex);
            }
        }
        int iScanNumber = jsonReader.scanNumber();
        int currentIndex2 = jsonReader.getCurrentIndex();
        byte[] bArr = jsonReader.buffer;
        byte b3 = bArr[iScanNumber];
        if (b3 == 45) {
            int i2 = iScanNumber + 2;
            if (currentIndex2 > i2 && bArr[iScanNumber + 1] == 48 && (b2 = bArr[i2]) >= 48 && b2 <= 57) {
                numberException(jsonReader, iScanNumber, currentIndex2, "Leading zero is not allowed");
            }
            return parseNegativeInt(bArr, jsonReader, iScanNumber, currentIndex2);
        }
        if (b3 == 48 && currentIndex2 > (i = iScanNumber + 1) && (b = bArr[i]) >= 48 && b <= 57) {
            numberException(jsonReader, iScanNumber, currentIndex2, "Leading zero is not allowed");
        }
        return parsePositiveInt(bArr, jsonReader, iScanNumber, currentIndex2, 0);
    }

    private static int parsePositiveInt(byte[] bArr, JsonReader jsonReader, int i, int i2, int i3) throws IOException {
        int i4 = i3 + i;
        if (i4 == i2) {
            numberException(jsonReader, i, i2, "Digit not found");
        }
        int i5 = 0;
        for (int i6 = i4; i6 < i2; i6++) {
            int i7 = bArr[i6] - 48;
            if (i7 < 0 || i7 > 9) {
                if (i6 > i4 && jsonReader.allWhitespace(i6, i2)) {
                    return i5;
                }
                if (i6 == i2 - 1 && bArr[i6] == 46) {
                    numberException(jsonReader, i, i2, "Number ends with a dot");
                }
                int i8 = i2 - i;
                BigDecimal numberGeneric = parseNumberGeneric(jsonReader.prepareBuffer(i, i8), i8, jsonReader, false);
                if (numberGeneric.scale() > 0) {
                    numberException(jsonReader, i, i2, "Expecting int but found decimal value", numberGeneric);
                }
                return numberGeneric.intValue();
            }
            i5 = (i5 << 3) + (i5 << 1) + i7;
            if (i5 < 0) {
                numberException(jsonReader, i, i2, "Integer overflow detected");
            }
        }
        return i5;
    }

    private static int parseNegativeInt(byte[] bArr, JsonReader jsonReader, int i, int i2) throws IOException {
        int i3 = i + 1;
        if (i3 == i2) {
            numberException(jsonReader, i, i2, "Digit not found");
        }
        int i4 = 0;
        for (int i5 = i3; i5 < i2; i5++) {
            int i6 = bArr[i5] - 48;
            if (i6 < 0 || i6 > 9) {
                if (i5 > i3 && jsonReader.allWhitespace(i5, i2)) {
                    return i4;
                }
                if (i5 == i2 - 1 && bArr[i5] == 46) {
                    numberException(jsonReader, i, i2, "Number ends with a dot");
                }
                int i7 = i2 - i;
                BigDecimal numberGeneric = parseNumberGeneric(jsonReader.prepareBuffer(i, i7), i7, jsonReader, false);
                if (numberGeneric.scale() > 0) {
                    numberException(jsonReader, i, i2, "Expecting int but found decimal value", numberGeneric);
                }
                return numberGeneric.intValue();
            }
            i4 = ((i4 << 3) + (i4 << 1)) - i6;
            if (i4 > 0) {
                numberException(jsonReader, i, i2, "Integer overflow detected");
            }
        }
        return i4;
    }

    public static int[] deserializeIntArray(JsonReader jsonReader) throws IOException {
        if (jsonReader.last() == 93) {
            return INT_EMPTY_ARRAY;
        }
        int[] iArrCopyOf = new int[4];
        iArrCopyOf[0] = deserializeInt(jsonReader);
        int i = 1;
        while (jsonReader.getNextToken() == 44) {
            jsonReader.getNextToken();
            if (i == iArrCopyOf.length) {
                iArrCopyOf = Arrays.copyOf(iArrCopyOf, iArrCopyOf.length << 1);
            }
            iArrCopyOf[i] = deserializeInt(jsonReader);
            i++;
        }
        jsonReader.checkArrayEnd();
        return Arrays.copyOf(iArrCopyOf, i);
    }

    public static short[] deserializeShortArray(JsonReader jsonReader) throws IOException {
        if (jsonReader.last() == 93) {
            return SHORT_EMPTY_ARRAY;
        }
        short[] sArrCopyOf = new short[4];
        sArrCopyOf[0] = (short) deserializeInt(jsonReader);
        int i = 1;
        while (jsonReader.getNextToken() == 44) {
            jsonReader.getNextToken();
            if (i == sArrCopyOf.length) {
                sArrCopyOf = Arrays.copyOf(sArrCopyOf, sArrCopyOf.length << 1);
            }
            sArrCopyOf[i] = (short) deserializeInt(jsonReader);
            i++;
        }
        jsonReader.checkArrayEnd();
        return Arrays.copyOf(sArrCopyOf, i);
    }

    public static long[] deserializeLongArray(JsonReader jsonReader) throws IOException {
        if (jsonReader.last() == 93) {
            return LONG_EMPTY_ARRAY;
        }
        long[] jArrCopyOf = new long[4];
        jArrCopyOf[0] = deserializeLong(jsonReader);
        int i = 1;
        while (jsonReader.getNextToken() == 44) {
            jsonReader.getNextToken();
            if (i == jArrCopyOf.length) {
                jArrCopyOf = Arrays.copyOf(jArrCopyOf, jArrCopyOf.length << 1);
            }
            jArrCopyOf[i] = deserializeLong(jsonReader);
            i++;
        }
        jsonReader.checkArrayEnd();
        return Arrays.copyOf(jArrCopyOf, i);
    }

    public static float[] deserializeFloatArray(JsonReader jsonReader) throws IOException {
        if (jsonReader.last() == 93) {
            return FLOAT_EMPTY_ARRAY;
        }
        float[] fArrCopyOf = new float[4];
        fArrCopyOf[0] = deserializeFloat(jsonReader);
        int i = 1;
        while (jsonReader.getNextToken() == 44) {
            jsonReader.getNextToken();
            if (i == fArrCopyOf.length) {
                fArrCopyOf = Arrays.copyOf(fArrCopyOf, fArrCopyOf.length << 1);
            }
            fArrCopyOf[i] = deserializeFloat(jsonReader);
            i++;
        }
        jsonReader.checkArrayEnd();
        return Arrays.copyOf(fArrCopyOf, i);
    }

    public static double[] deserializeDoubleArray(JsonReader jsonReader) throws IOException {
        if (jsonReader.last() == 93) {
            return DOUBLE_EMPTY_ARRAY;
        }
        double[] dArrCopyOf = new double[4];
        dArrCopyOf[0] = deserializeDouble(jsonReader);
        int i = 1;
        while (jsonReader.getNextToken() == 44) {
            jsonReader.getNextToken();
            if (i == dArrCopyOf.length) {
                dArrCopyOf = Arrays.copyOf(dArrCopyOf, dArrCopyOf.length << 1);
            }
            dArrCopyOf[i] = deserializeDouble(jsonReader);
            i++;
        }
        jsonReader.checkArrayEnd();
        return Arrays.copyOf(dArrCopyOf, i);
    }

    public static void serializeNullable(@Nullable Long l, JsonWriter jsonWriter) {
        if (l == null) {
            jsonWriter.writeNull();
        } else {
            serialize(l.longValue(), jsonWriter);
        }
    }

    private static int writeFirstBuf(byte[] bArr, int i, int i2) {
        int i3 = i >> 24;
        if (i3 == 0) {
            int i4 = i2 + 1;
            bArr[i2] = (byte) (i >> 16);
            i2 = i4 + 1;
            bArr[i4] = (byte) (i >> 8);
        } else if (i3 == 1) {
            bArr[i2] = (byte) (i >> 8);
            i2++;
        }
        bArr[i2] = (byte) i;
        return 3 - i3;
    }

    private static void writeBuf(byte[] bArr, int i, int i2) {
        bArr[i2] = (byte) (i >> 16);
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 2] = (byte) i;
    }

    public static void serialize(long j, JsonWriter jsonWriter) {
        byte[] bArrEnsureCapacity = jsonWriter.ensureCapacity(21);
        int size = jsonWriter.size();
        jsonWriter.advance(serialize(bArrEnsureCapacity, size, j) - size);
    }

    private static int serialize(byte[] bArr, int i, long j) {
        int iWriteFirstBuf;
        if (j < 0) {
            if (j == Long.MIN_VALUE) {
                int i2 = 0;
                while (true) {
                    byte[] bArr2 = MIN_LONG;
                    if (i2 < bArr2.length) {
                        bArr[i + i2] = bArr2[i2];
                        i2++;
                    } else {
                        return i + bArr2.length;
                    }
                }
            } else {
                j = -j;
                bArr[i] = 45;
                i++;
            }
        }
        long j2 = j / 1000;
        if (j2 == 0) {
            return i + writeFirstBuf(bArr, DIGITS[(int) j], i);
        }
        int i3 = (int) (j - (j2 * 1000));
        long j3 = j2 / 1000;
        if (j3 == 0) {
            int[] iArr = DIGITS;
            int i4 = iArr[i3];
            int iWriteFirstBuf2 = writeFirstBuf(bArr, iArr[(int) j2], i);
            writeBuf(bArr, i4, i + iWriteFirstBuf2);
            return i + 3 + iWriteFirstBuf2;
        }
        int i5 = (int) (j2 - (j3 * 1000));
        long j4 = j3 / 1000;
        if (j4 == 0) {
            int[] iArr2 = DIGITS;
            int i6 = iArr2[i3];
            int i7 = iArr2[i5];
            int iWriteFirstBuf3 = i + writeFirstBuf(bArr, iArr2[(int) j3], i);
            writeBuf(bArr, i7, iWriteFirstBuf3);
            writeBuf(bArr, i6, iWriteFirstBuf3 + 3);
            return iWriteFirstBuf3 + 6;
        }
        int i8 = (int) (j3 - (j4 * 1000));
        int i9 = (int) (j4 / 1000);
        if (i9 == 0) {
            int[] iArr3 = DIGITS;
            int i10 = iArr3[i3];
            int i11 = iArr3[i5];
            int i12 = iArr3[i8];
            int iWriteFirstBuf4 = i + writeFirstBuf(bArr, iArr3[(int) j4], i);
            writeBuf(bArr, i12, iWriteFirstBuf4);
            writeBuf(bArr, i11, iWriteFirstBuf4 + 3);
            writeBuf(bArr, i10, iWriteFirstBuf4 + 6);
            return iWriteFirstBuf4 + 9;
        }
        int i13 = (int) (j4 - ((long) (i9 * 1000)));
        int i14 = i9 / 1000;
        if (i14 == 0) {
            int[] iArr4 = DIGITS;
            int i15 = iArr4[i3];
            int i16 = iArr4[i5];
            int i17 = iArr4[i8];
            int i18 = iArr4[i13];
            int iWriteFirstBuf5 = i + writeFirstBuf(bArr, iArr4[i9], i);
            writeBuf(bArr, i18, iWriteFirstBuf5);
            writeBuf(bArr, i17, iWriteFirstBuf5 + 3);
            writeBuf(bArr, i16, iWriteFirstBuf5 + 6);
            writeBuf(bArr, i15, iWriteFirstBuf5 + 9);
            return iWriteFirstBuf5 + 12;
        }
        int i19 = i14 / 1000;
        int[] iArr5 = DIGITS;
        int i20 = iArr5[i3];
        int i21 = iArr5[i5];
        int i22 = iArr5[i8];
        int i23 = iArr5[i13];
        int i24 = iArr5[i9 - (i14 * 1000)];
        if (i19 == 0) {
            iWriteFirstBuf = i + writeFirstBuf(bArr, iArr5[i14], i);
        } else {
            int i25 = i + 1;
            bArr[i] = (byte) (i19 + 48);
            writeBuf(bArr, iArr5[i14 - (i19 * 1000)], i25);
            iWriteFirstBuf = i25 + 3;
        }
        writeBuf(bArr, i24, iWriteFirstBuf);
        writeBuf(bArr, i23, iWriteFirstBuf + 3);
        writeBuf(bArr, i22, iWriteFirstBuf + 6);
        writeBuf(bArr, i21, iWriteFirstBuf + 9);
        writeBuf(bArr, i20, iWriteFirstBuf + 12);
        return iWriteFirstBuf + 15;
    }

    public static void serialize(@Nullable long[] jArr, JsonWriter jsonWriter) {
        if (jArr == null) {
            jsonWriter.writeNull();
            return;
        }
        if (jArr.length == 0) {
            jsonWriter.writeAscii(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        byte[] bArrEnsureCapacity = jsonWriter.ensureCapacity((jArr.length * 21) + 2);
        int size = jsonWriter.size();
        bArrEnsureCapacity[size] = 91;
        int iSerialize = serialize(bArrEnsureCapacity, size + 1, jArr[0]);
        for (int i = 1; i < jArr.length; i++) {
            bArrEnsureCapacity[iSerialize] = 44;
            iSerialize = serialize(bArrEnsureCapacity, iSerialize + 1, jArr[i]);
        }
        bArrEnsureCapacity[iSerialize] = 93;
        jsonWriter.advance((iSerialize + 1) - jsonWriter.size());
    }

    public static long deserializeLong(JsonReader jsonReader) throws IOException {
        boolean z;
        if (jsonReader.last() == 34) {
            int currentIndex = jsonReader.getCurrentIndex();
            try {
                return parseNumberGeneric(jsonReader.readSimpleQuote(), (jsonReader.getCurrentIndex() - currentIndex) - 1, jsonReader, true).longValueExact();
            } catch (ArithmeticException unused) {
                throw jsonReader.newParseErrorAt("Long overflow detected", jsonReader.getCurrentIndex() - currentIndex);
            }
        }
        int iScanNumber = jsonReader.scanNumber();
        int currentIndex2 = jsonReader.getCurrentIndex();
        byte[] bArr = jsonReader.buffer;
        byte b = bArr[iScanNumber];
        char c = 3;
        int i = 9;
        byte b2 = 48;
        if (b == 45) {
            int i2 = iScanNumber + 1;
            if (i2 == currentIndex2) {
                numberException(jsonReader, iScanNumber, currentIndex2, "Digit not found");
            }
            z = bArr[i2] == 48;
            int i3 = i2;
            long j = 0;
            while (i3 < currentIndex2) {
                int i4 = bArr[i3] - 48;
                if (i4 < 0 || i4 > i) {
                    if (z && i3 > iScanNumber + 2) {
                        numberException(jsonReader, iScanNumber, currentIndex2, "Leading zero is not allowed");
                    }
                    return (i3 <= i2 || !jsonReader.allWhitespace(i3, currentIndex2)) ? parseLongGeneric(jsonReader, iScanNumber, currentIndex2) : j;
                }
                j = ((j << c) + (j << 1)) - ((long) i4);
                if (j > 0) {
                    numberException(jsonReader, iScanNumber, currentIndex2, "Long overflow detected");
                }
                i3++;
                c = 3;
                i = 9;
            }
            if (z && i3 > iScanNumber + 2) {
                numberException(jsonReader, iScanNumber, currentIndex2, "Leading zero is not allowed");
            }
            return j;
        }
        if (iScanNumber == currentIndex2) {
            numberException(jsonReader, iScanNumber, currentIndex2, "Digit not found");
        }
        z = bArr[iScanNumber] == 48;
        int i5 = iScanNumber;
        long j2 = 0;
        while (i5 < currentIndex2) {
            int i6 = bArr[i5] - b2;
            if (i6 < 0 || i6 > 9) {
                if (z && i5 > iScanNumber + 1) {
                    numberException(jsonReader, iScanNumber, currentIndex2, "Leading zero is not allowed");
                }
                return (b == 43 && i5 > iScanNumber + 1 && jsonReader.allWhitespace(i5, currentIndex2)) ? j2 : (b == 43 || i5 <= iScanNumber || !jsonReader.allWhitespace(i5, currentIndex2)) ? parseLongGeneric(jsonReader, iScanNumber, currentIndex2) : j2;
            }
            j2 = (j2 << 3) + (j2 << 1) + ((long) i6);
            if (j2 < 0) {
                numberException(jsonReader, iScanNumber, currentIndex2, "Long overflow detected");
            }
            i5++;
            b2 = 48;
        }
        if (z && i5 > iScanNumber + 1) {
            numberException(jsonReader, iScanNumber, currentIndex2, "Leading zero is not allowed");
        }
        return j2;
    }

    private static long parseLongGeneric(JsonReader jsonReader, int i, int i2) throws IOException {
        int i3 = i2 - i;
        char[] cArrPrepareBuffer = jsonReader.prepareBuffer(i, i3);
        if (i3 > 0 && cArrPrepareBuffer[i3 - 1] == '.') {
            numberException(jsonReader, i, i2, "Number ends with a dot");
        }
        BigDecimal numberGeneric = parseNumberGeneric(cArrPrepareBuffer, i3, jsonReader, false);
        if (numberGeneric.scale() > 0) {
            numberException(jsonReader, i, i2, "Expecting long, but found decimal value ", numberGeneric);
        }
        return numberGeneric.longValue();
    }

    public static void serializeNullable(@Nullable BigDecimal bigDecimal, JsonWriter jsonWriter) {
        if (bigDecimal == null) {
            jsonWriter.writeNull();
        } else {
            jsonWriter.writeAscii(bigDecimal.toString());
        }
    }

    public static BigDecimal deserializeDecimal(JsonReader jsonReader) throws IOException {
        if (jsonReader.last() == 34) {
            return parseNumberGeneric(jsonReader.chars, jsonReader.parseString(), jsonReader, true);
        }
        int iScanNumber = jsonReader.scanNumber();
        int currentIndex = jsonReader.getCurrentIndex();
        if (currentIndex == jsonReader.length()) {
            NumberInfo longNumber = readLongNumber(jsonReader, iScanNumber);
            return parseNumberGeneric(longNumber.buffer, longNumber.length, jsonReader, false);
        }
        int i = currentIndex - iScanNumber;
        if (i > 18) {
            return parseNumberGeneric(jsonReader.prepareBuffer(iScanNumber, i), i, jsonReader, false);
        }
        byte[] bArr = jsonReader.buffer;
        if (bArr[iScanNumber] == 45) {
            return parseNegativeDecimal(bArr, jsonReader, iScanNumber, currentIndex);
        }
        return parsePositiveDecimal(bArr, jsonReader, iScanNumber, currentIndex);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x005d, code lost:
    
        if (r10 != r21) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005f, code lost:
    
        numberException(r20, r21, r22, "Digit not found");
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0066, code lost:
    
        if (r4 == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0068, code lost:
    
        if (r6 == 46) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006c, code lost:
    
        if (r10 <= (r21 + 1)) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006e, code lost:
    
        numberException(r20, r21, r22, "Leading zero is not allowed");
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0073, code lost:
    
        if (r10 != r22) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0079, code lost:
    
        return java.math.BigDecimal.valueOf(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x007c, code lost:
    
        if (r6 != 46) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x007e, code lost:
    
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0080, code lost:
    
        if (r10 != r22) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0082, code lost:
    
        numberException(r20, r21, r22, "Number ends with a dot");
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0087, code lost:
    
        r15 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0088, code lost:
    
        if (r15 >= r22) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x008a, code lost:
    
        r6 = r19[r15];
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x008c, code lost:
    
        if (r6 == 101) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x008e, code lost:
    
        if (r6 != 69) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0091, code lost:
    
        r4 = r6 - 48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0093, code lost:
    
        if (r4 < 0) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0095, code lost:
    
        if (r4 <= r13) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x009b, code lost:
    
        if (r20.allWhitespace(r15, r22) == false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a2, code lost:
    
        return java.math.BigDecimal.valueOf(r8, r15 - r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00a3, code lost:
    
        numberException(r20, r21, r22, "Unknown digit", java.lang.Character.valueOf((char) r6));
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00ab, code lost:
    
        r8 = ((r8 << 3) + (r8 << 1)) + ((long) r4);
        r15 = r15 + 1;
        r13 = 9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00b9, code lost:
    
        if (r15 != r22) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00c1, code lost:
    
        return java.math.BigDecimal.valueOf(r8, r22 - r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00c2, code lost:
    
        if (r6 == 101) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00c4, code lost:
    
        if (r6 != 69) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00cd, code lost:
    
        return java.math.BigDecimal.valueOf(r8, r22 - r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00ce, code lost:
    
        r2 = r15 + 1;
        r4 = r19[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00d2, code lost:
    
        if (r4 != 45) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00d4, code lost:
    
        r0 = parseNegativeInt(r19, r20, r2, r22);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00db, code lost:
    
        if (r4 != 43) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00dd, code lost:
    
        r0 = parsePositiveInt(r19, r20, r2, r22, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00e3, code lost:
    
        r0 = parsePositiveInt(r19, r20, r2, r22, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00ee, code lost:
    
        return java.math.BigDecimal.valueOf(r8, (r15 - r10) - r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00ef, code lost:
    
        if (r6 == 101) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00f1, code lost:
    
        if (r6 != 69) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00f8, code lost:
    
        return java.math.BigDecimal.valueOf(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00f9, code lost:
    
        r10 = r10 + 1;
        r4 = r19[r10];
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00fd, code lost:
    
        if (r4 != 45) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00ff, code lost:
    
        r0 = parseNegativeInt(r19, r20, r10, r22);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0106, code lost:
    
        if (r4 != 43) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0108, code lost:
    
        r0 = parsePositiveInt(r19, r20, r10, r22, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x010d, code lost:
    
        r0 = parsePositiveInt(r19, r20, r10, r22, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0117, code lost:
    
        return java.math.BigDecimal.valueOf(r8, -r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.math.BigDecimal parsePositiveDecimal(byte[] r19, com.bugsnag.android.repackaged.dslplatform.json.JsonReader r20, int r21, int r22) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.parsePositiveDecimal(byte[], com.bugsnag.android.repackaged.dslplatform.json.JsonReader, int, int):java.math.BigDecimal");
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0062, code lost:
    
        if (r11 != r4) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0064, code lost:
    
        numberException(r21, r22, r23, "Digit not found");
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006b, code lost:
    
        if (r5 == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006d, code lost:
    
        if (r7 == 46) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0071, code lost:
    
        if (r11 <= (r22 + 2)) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0073, code lost:
    
        numberException(r21, r22, r23, "Leading zero is not allowed");
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0078, code lost:
    
        if (r11 != r23) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x007e, code lost:
    
        return java.math.BigDecimal.valueOf(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0083, code lost:
    
        if (r7 != 46) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0085, code lost:
    
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0087, code lost:
    
        if (r11 != r23) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0089, code lost:
    
        numberException(r21, r22, r23, "Number ends with a dot");
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x008e, code lost:
    
        r6 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x008f, code lost:
    
        if (r6 >= r23) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0091, code lost:
    
        r7 = r20[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0093, code lost:
    
        if (r7 == 101) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0095, code lost:
    
        if (r7 != 69) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0098, code lost:
    
        r15 = r7 - 48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x009a, code lost:
    
        if (r15 < 0) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x009c, code lost:
    
        if (r15 <= r14) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00a5, code lost:
    
        if (r21.allWhitespace(r6, r23) == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00ac, code lost:
    
        return java.math.BigDecimal.valueOf(r9, r6 - r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00ad, code lost:
    
        numberException(r21, r22, r23, "Unknown digit", java.lang.Character.valueOf((char) r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00b6, code lost:
    
        r9 = ((r9 << 3) + (r9 << 1)) - ((long) r15);
        r6 = r6 + 1;
        r14 = 9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00c6, code lost:
    
        if (r6 != r23) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00ce, code lost:
    
        return java.math.BigDecimal.valueOf(r9, r23 - r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00cf, code lost:
    
        if (r7 == 101) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00d1, code lost:
    
        if (r7 != 69) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00da, code lost:
    
        return java.math.BigDecimal.valueOf(r9, r23 - r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00db, code lost:
    
        r2 = r6 + 1;
        r7 = r20[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00df, code lost:
    
        if (r7 != 45) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00e1, code lost:
    
        r0 = parseNegativeInt(r20, r21, r2, r23);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00e6, code lost:
    
        if (r7 != 43) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00e8, code lost:
    
        r0 = parsePositiveInt(r20, r21, r2, r23, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00ee, code lost:
    
        r0 = parsePositiveInt(r20, r21, r2, r23, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00f9, code lost:
    
        return java.math.BigDecimal.valueOf(r9, (r6 - r11) - r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00fa, code lost:
    
        if (r7 == 101) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00fc, code lost:
    
        if (r7 != 69) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0103, code lost:
    
        return java.math.BigDecimal.valueOf(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0104, code lost:
    
        r11 = r11 + 1;
        r6 = r20[r11];
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0108, code lost:
    
        if (r6 != 45) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x010a, code lost:
    
        r0 = parseNegativeInt(r20, r21, r11, r23);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x010f, code lost:
    
        if (r6 != 43) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0111, code lost:
    
        r0 = parsePositiveInt(r20, r21, r11, r23, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0116, code lost:
    
        r0 = parsePositiveInt(r20, r21, r11, r23, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0120, code lost:
    
        return java.math.BigDecimal.valueOf(r9, -r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.math.BigDecimal parseNegativeDecimal(byte[] r20, com.bugsnag.android.repackaged.dslplatform.json.JsonReader r21, int r22, int r23) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bugsnag.android.repackaged.dslplatform.json.NumberConverter.parseNegativeDecimal(byte[], com.bugsnag.android.repackaged.dslplatform.json.JsonReader, int, int):java.math.BigDecimal");
    }

    private static Number bigDecimalOrDouble(BigDecimal bigDecimal, JsonReader.UnknownNumberParsing unknownNumberParsing) {
        return unknownNumberParsing == JsonReader.UnknownNumberParsing.LONG_AND_BIGDECIMAL ? bigDecimal : Double.valueOf(bigDecimal.doubleValue());
    }

    private static Number tryLongFromBigDecimal(char[] cArr, int i, JsonReader jsonReader) throws IOException {
        BigDecimal numberGeneric = parseNumberGeneric(cArr, i, jsonReader, false);
        if (numberGeneric.scale() == 0 && numberGeneric.precision() <= 19) {
            if (numberGeneric.signum() == 1) {
                if (numberGeneric.compareTo(BD_MAX_LONG) <= 0) {
                    return Long.valueOf(numberGeneric.longValue());
                }
            } else if (numberGeneric.compareTo(BD_MIN_LONG) >= 0) {
                return Long.valueOf(numberGeneric.longValue());
            }
        }
        return bigDecimalOrDouble(numberGeneric, jsonReader.unknownNumbers);
    }

    public static Number deserializeNumber(JsonReader jsonReader) throws IOException {
        JsonReader.UnknownNumberParsing unknownNumberParsing = jsonReader.unknownNumbers;
        if (unknownNumberParsing == JsonReader.UnknownNumberParsing.BIGDECIMAL) {
            return deserializeDecimal(jsonReader);
        }
        if (unknownNumberParsing == JsonReader.UnknownNumberParsing.DOUBLE) {
            return Double.valueOf(deserializeDouble(jsonReader));
        }
        int iScanNumber = jsonReader.scanNumber();
        int currentIndex = jsonReader.getCurrentIndex();
        if (currentIndex == jsonReader.length()) {
            NumberInfo longNumber = readLongNumber(jsonReader, iScanNumber);
            return tryLongFromBigDecimal(longNumber.buffer, longNumber.length, jsonReader);
        }
        int i = currentIndex - iScanNumber;
        if (i > 18) {
            return tryLongFromBigDecimal(jsonReader.prepareBuffer(iScanNumber, i), i, jsonReader);
        }
        byte[] bArr = jsonReader.buffer;
        if (bArr[iScanNumber] == 45) {
            return parseNegativeNumber(bArr, jsonReader, iScanNumber, currentIndex);
        }
        return parsePositiveNumber(bArr, jsonReader, iScanNumber, currentIndex);
    }

    private static Number parsePositiveNumber(byte[] bArr, JsonReader jsonReader, int i, int i2) throws IOException {
        int positiveInt;
        int positiveInt2;
        boolean z = bArr[i] == 48;
        long j = 0;
        byte b = 32;
        int i3 = i;
        while (i3 < i2 && (b = bArr[i3]) != 46 && b != 101 && b != 69) {
            int i4 = b - 48;
            if (i4 < 0 || i4 > 9) {
                if (z && i3 > i + 1) {
                    numberException(jsonReader, i, i2, "Leading zero is not allowed");
                }
                if (i3 > i && jsonReader.allWhitespace(i3, i2)) {
                    return Long.valueOf(j);
                }
                int i5 = i2 - i;
                return tryLongFromBigDecimal(jsonReader.prepareBuffer(i, i5), i5, jsonReader);
            }
            j = ((long) i4) + (j << 3) + (j << 1);
            i3++;
        }
        if (i3 == i) {
            numberException(jsonReader, i, i2, "Digit not found");
        } else if (z && b != 46 && i3 > i + 1) {
            numberException(jsonReader, i, i2, "Leading zero is not allowed");
        } else {
            if (i3 == i2) {
                return Long.valueOf(j);
            }
            if (b == 46) {
                int i6 = i3 + 1;
                if (i6 == i2) {
                    numberException(jsonReader, i, i2, "Number ends with a dot");
                }
                int i7 = i6;
                while (i7 < i2) {
                    b = bArr[i7];
                    if (b == 101 || b == 69) {
                        break;
                    }
                    int i8 = b - 48;
                    if (i8 < 0 || i8 > 9) {
                        if (jsonReader.allWhitespace(i7, i2)) {
                            return BigDecimal.valueOf(j, i7 - i6);
                        }
                        int i9 = i2 - i;
                        return tryLongFromBigDecimal(jsonReader.prepareBuffer(i, i9), i9, jsonReader);
                    }
                    j = (j << 3) + (j << 1) + ((long) i8);
                    i7++;
                }
                if (i7 == i2) {
                    return bigDecimalOrDouble(BigDecimal.valueOf(j, i2 - i6), jsonReader.unknownNumbers);
                }
                if (b == 101 || b == 69) {
                    int i10 = i7 + 1;
                    byte b2 = bArr[i10];
                    if (b2 == 45) {
                        positiveInt2 = parseNegativeInt(bArr, jsonReader, i10, i2);
                    } else if (b2 == 43) {
                        positiveInt2 = parsePositiveInt(bArr, jsonReader, i10, i2, 1);
                    } else {
                        positiveInt2 = parsePositiveInt(bArr, jsonReader, i10, i2, 0);
                    }
                    return bigDecimalOrDouble(BigDecimal.valueOf(j, (i7 - i6) - positiveInt2), jsonReader.unknownNumbers);
                }
                return BigDecimal.valueOf(j, i2 - i6);
            }
            if (b == 101 || b == 69) {
                int i11 = i3 + 1;
                byte b3 = bArr[i11];
                if (b3 == 45) {
                    positiveInt = parseNegativeInt(bArr, jsonReader, i11, i2);
                } else if (b3 == 43) {
                    positiveInt = parsePositiveInt(bArr, jsonReader, i11, i2, 1);
                } else {
                    positiveInt = parsePositiveInt(bArr, jsonReader, i11, i2, 0);
                }
                return bigDecimalOrDouble(BigDecimal.valueOf(j, -positiveInt), jsonReader.unknownNumbers);
            }
        }
        return bigDecimalOrDouble(BigDecimal.valueOf(j), jsonReader.unknownNumbers);
    }

    private static Number parseNegativeNumber(byte[] bArr, JsonReader jsonReader, int i, int i2) throws IOException {
        int positiveInt;
        int positiveInt2;
        int i3 = i + 1;
        boolean z = bArr[i3] == 48;
        long j = 0;
        byte b = 32;
        int i4 = i3;
        while (i4 < i2 && (b = bArr[i4]) != 46 && b != 101 && b != 69) {
            int i5 = b - 48;
            if (i5 < 0 || i5 > 9) {
                if (z && i4 > i + 2) {
                    numberException(jsonReader, i, i2, "Leading zero is not allowed");
                }
                if (i4 > i3 && jsonReader.allWhitespace(i4, i2)) {
                    return Long.valueOf(j);
                }
                int i6 = i2 - i;
                return tryLongFromBigDecimal(jsonReader.prepareBuffer(i, i6), i6, jsonReader);
            }
            j = ((j << 3) + (j << 1)) - ((long) i5);
            i4++;
        }
        if (i4 == i3) {
            numberException(jsonReader, i, i2, "Digit not found");
        } else if (z && b != 46 && i4 > i + 2) {
            numberException(jsonReader, i, i2, "Leading zero is not allowed");
        } else {
            if (i4 == i2) {
                return Long.valueOf(j);
            }
            if (b == 46) {
                int i7 = i4 + 1;
                if (i7 == i2) {
                    numberException(jsonReader, i, i2, "Number ends with a dot");
                }
                int i8 = i7;
                while (i8 < i2) {
                    b = bArr[i8];
                    if (b == 101 || b == 69) {
                        break;
                    }
                    int i9 = b - 48;
                    if (i9 < 0 || i9 > 9) {
                        if (jsonReader.allWhitespace(i8, i2)) {
                            return BigDecimal.valueOf(j, i8 - i7);
                        }
                        int i10 = i2 - i;
                        return tryLongFromBigDecimal(jsonReader.prepareBuffer(i, i10), i10, jsonReader);
                    }
                    j = ((j << 3) + (j << 1)) - ((long) i9);
                    i8++;
                }
                if (i8 == i2) {
                    return bigDecimalOrDouble(BigDecimal.valueOf(j, i2 - i7), jsonReader.unknownNumbers);
                }
                if (b == 101 || b == 69) {
                    int i11 = i8 + 1;
                    byte b2 = bArr[i11];
                    if (b2 == 45) {
                        positiveInt2 = parseNegativeInt(bArr, jsonReader, i11, i2);
                    } else if (b2 == 43) {
                        positiveInt2 = parsePositiveInt(bArr, jsonReader, i11, i2, 1);
                    } else {
                        positiveInt2 = parsePositiveInt(bArr, jsonReader, i11, i2, 0);
                    }
                    return bigDecimalOrDouble(BigDecimal.valueOf(j, (i8 - i7) - positiveInt2), jsonReader.unknownNumbers);
                }
                return bigDecimalOrDouble(BigDecimal.valueOf(j, i2 - i7), jsonReader.unknownNumbers);
            }
            if (b == 101 || b == 69) {
                int i12 = i4 + 1;
                byte b3 = bArr[i12];
                if (b3 == 45) {
                    positiveInt = parseNegativeInt(bArr, jsonReader, i12, i2);
                } else if (b3 == 43) {
                    positiveInt = parsePositiveInt(bArr, jsonReader, i12, i2, 1);
                } else {
                    positiveInt = parsePositiveInt(bArr, jsonReader, i12, i2, 0);
                }
                return bigDecimalOrDouble(BigDecimal.valueOf(j, -positiveInt), jsonReader.unknownNumbers);
            }
        }
        return bigDecimalOrDouble(BigDecimal.valueOf(j), jsonReader.unknownNumbers);
    }
}
