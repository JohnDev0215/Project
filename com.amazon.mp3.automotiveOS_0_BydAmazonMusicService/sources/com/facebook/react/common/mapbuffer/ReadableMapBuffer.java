package com.facebook.react.common.mapbuffer;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.common.mapbuffer.MapBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import javax.annotation.concurrent.NotThreadSafe;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: ReadableMapBuffer.kt */
/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
@NotThreadSafe
@Metadata(bv = {}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u0000 <2\u00020\u0001:\u0002<=B\u0011\b\u0013\u0012\u0006\u00108\u001a\u00020,¢\u0006\u0004\b9\u0010:B\u0011\b\u0012\u0012\u0006\u0010/\u001a\u00020\u0002¢\u0006\u0004\b9\u0010;J\t\u0010\u0003\u001a\u00020\u0002H\u0082 J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u0018\u0010\u000e\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\nH\u0002J \u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\u0010\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0006H\u0002J\u0010\u0010\u001d\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010\u001f\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010 \u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010#\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0006H\u0016J\b\u0010$\u001a\u00020\u0006H\u0016J\u0013\u0010'\u001a\u00020\u00172\b\u0010&\u001a\u0004\u0018\u00010%H\u0096\u0002J\b\u0010(\u001a\u00020\u0019H\u0016J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020*0)H\u0096\u0002R\u0016\u0010-\u001a\u0004\u0018\u00010,8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R$\u00102\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u00068\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b4\u00105R\u0014\u00107\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b6\u00105\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006>"}, d2 = {"Lcom/facebook/react/common/mapbuffer/ReadableMapBuffer;", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "Ljava/nio/ByteBuffer;", "importByteBuffer", "", "readHeader", "", "intKey", "getBucketIndexForKey", "bucketIndex", "Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "readDataType", "key", "expected", "getTypedValueOffsetForKey", "bufferPosition", "Lkotlin/UShort;", "readUnsignedShort-BwKQO78", "(I)S", "readUnsignedShort", "", "readDoubleValue", "readIntValue", "", "readBooleanValue", "", "readStringValue", "position", "readMapBufferValue", "getKeyOffsetForBucketIndex", "contains", "getInt", "getDouble", "getString", "getBoolean", "getMapBuffer", "hashCode", "", "other", "equals", "toString", "", "Lcom/facebook/react/common/mapbuffer/MapBuffer$Entry;", "iterator", "Lcom/facebook/jni/HybridData;", "mHybridData", "Lcom/facebook/jni/HybridData;", "buffer", "Ljava/nio/ByteBuffer;", "<set-?>", "count", "I", "getCount", "()I", "getOffsetForDynamicData", "offsetForDynamicData", "hybridData", "<init>", "(Lcom/facebook/jni/HybridData;)V", "(Ljava/nio/ByteBuffer;)V", "Companion", "MapBufferEntry", "ReactAndroid_release"}, k = 1, mv = {1, 6, 0})
public final class ReadableMapBuffer implements MapBuffer {
    private final ByteBuffer buffer;
    private int count;

    @DoNotStrip
    private final HybridData mHybridData;

    /* JADX INFO: compiled from: ReadableMapBuffer.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MapBuffer.DataType.values().length];
            iArr[MapBuffer.DataType.BOOL.ordinal()] = 1;
            iArr[MapBuffer.DataType.INT.ordinal()] = 2;
            iArr[MapBuffer.DataType.DOUBLE.ordinal()] = 3;
            iArr[MapBuffer.DataType.STRING.ordinal()] = 4;
            iArr[MapBuffer.DataType.MAP.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getKeyOffsetForBucketIndex(int bucketIndex) {
        return (bucketIndex * 12) + 8;
    }

    private final native ByteBuffer importByteBuffer();

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public int getCount() {
        return this.count;
    }

    @DoNotStrip
    private ReadableMapBuffer(HybridData hybridData) {
        this.mHybridData = hybridData;
        this.buffer = importByteBuffer();
        readHeader();
    }

    private ReadableMapBuffer(ByteBuffer byteBuffer) {
        this.mHybridData = null;
        this.buffer = byteBuffer;
        readHeader();
    }

    private final void readHeader() {
        if (this.buffer.getShort() != 254) {
            this.buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        this.count = m2659readUnsignedShortBwKQO78(this.buffer.position()) & UShort.MAX_VALUE;
    }

    private final int getOffsetForDynamicData() {
        return getKeyOffsetForBucketIndex(getCount());
    }

    private final int getBucketIndexForKey(int intKey) {
        IntRange kEY_RANGE$ReactAndroid_release = MapBuffer.INSTANCE.getKEY_RANGE$ReactAndroid_release();
        int i = 0;
        if (!(intKey <= kEY_RANGE$ReactAndroid_release.getLast() && kEY_RANGE$ReactAndroid_release.getFirst() <= intKey)) {
            return -1;
        }
        short sM3088constructorimpl = UShort.m3088constructorimpl((short) intKey);
        int count = getCount() - 1;
        while (i <= count) {
            int i2 = (i + count) >>> 1;
            int iM2659readUnsignedShortBwKQO78 = m2659readUnsignedShortBwKQO78(getKeyOffsetForBucketIndex(i2)) & UShort.MAX_VALUE;
            int i3 = 65535 & sM3088constructorimpl;
            if (Intrinsics.compare(iM2659readUnsignedShortBwKQO78, i3) < 0) {
                i = i2 + 1;
            } else {
                if (Intrinsics.compare(iM2659readUnsignedShortBwKQO78, i3) <= 0) {
                    return i2;
                }
                count = i2 - 1;
            }
        }
        return -1;
    }

    private final MapBuffer.DataType readDataType(int bucketIndex) {
        return MapBuffer.DataType.values()[m2659readUnsignedShortBwKQO78(getKeyOffsetForBucketIndex(bucketIndex) + 2) & UShort.MAX_VALUE];
    }

    private final int getTypedValueOffsetForKey(int key, MapBuffer.DataType expected) {
        int bucketIndexForKey = getBucketIndexForKey(key);
        if (!(bucketIndexForKey != -1)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
        }
        MapBuffer.DataType dataType = readDataType(bucketIndexForKey);
        if (!(dataType == expected)) {
            throw new IllegalStateException(("Expected " + expected + " for key: " + key + ", found " + dataType + " instead.").toString());
        }
        return getKeyOffsetForBucketIndex(bucketIndexForKey) + 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: readUnsignedShort-BwKQO78, reason: not valid java name */
    public final short m2659readUnsignedShortBwKQO78(int bufferPosition) {
        return UShort.m3088constructorimpl(this.buffer.getShort(bufferPosition));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double readDoubleValue(int bufferPosition) {
        return this.buffer.getDouble(bufferPosition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int readIntValue(int bufferPosition) {
        return this.buffer.getInt(bufferPosition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean readBooleanValue(int bufferPosition) {
        return readIntValue(bufferPosition) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String readStringValue(int bufferPosition) {
        int offsetForDynamicData = getOffsetForDynamicData() + this.buffer.getInt(bufferPosition);
        int i = this.buffer.getInt(offsetForDynamicData);
        byte[] bArr = new byte[i];
        this.buffer.position(offsetForDynamicData + 4);
        this.buffer.get(bArr, 0, i);
        return new String(bArr, Charsets.UTF_8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ReadableMapBuffer readMapBufferValue(int position) {
        int offsetForDynamicData = getOffsetForDynamicData() + this.buffer.getInt(position);
        int i = this.buffer.getInt(offsetForDynamicData);
        byte[] bArr = new byte[i];
        this.buffer.position(offsetForDynamicData + 4);
        this.buffer.get(bArr, 0, i);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        Intrinsics.checkNotNullExpressionValue(byteBufferWrap, "wrap(newBuffer)");
        return new ReadableMapBuffer(byteBufferWrap);
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public boolean contains(int key) {
        return getBucketIndexForKey(key) != -1;
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public int getInt(int key) {
        return readIntValue(getTypedValueOffsetForKey(key, MapBuffer.DataType.INT));
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public double getDouble(int key) {
        return readDoubleValue(getTypedValueOffsetForKey(key, MapBuffer.DataType.DOUBLE));
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public String getString(int key) {
        return readStringValue(getTypedValueOffsetForKey(key, MapBuffer.DataType.STRING));
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public boolean getBoolean(int key) {
        return readBooleanValue(getTypedValueOffsetForKey(key, MapBuffer.DataType.BOOL));
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public ReadableMapBuffer getMapBuffer(int key) {
        return readMapBufferValue(getTypedValueOffsetForKey(key, MapBuffer.DataType.MAP));
    }

    public int hashCode() {
        this.buffer.rewind();
        return this.buffer.hashCode();
    }

    public boolean equals(Object other) {
        if (!(other instanceof ReadableMapBuffer)) {
            return false;
        }
        ByteBuffer byteBuffer = this.buffer;
        ByteBuffer byteBuffer2 = ((ReadableMapBuffer) other).buffer;
        if (byteBuffer == byteBuffer2) {
            return true;
        }
        byteBuffer.rewind();
        byteBuffer2.rewind();
        return Intrinsics.areEqual(byteBuffer, byteBuffer2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (MapBuffer.Entry entry : this) {
            sb.append(entry.getKey());
            sb.append('=');
            int i = WhenMappings.$EnumSwitchMapping$0[entry.getType().ordinal()];
            if (i == 1) {
                sb.append(entry.getBooleanValue());
            } else if (i == 2) {
                sb.append(entry.getIntValue());
            } else if (i == 3) {
                sb.append(entry.getDoubleValue());
            } else if (i == 4) {
                sb.append(entry.getStringValue());
            } else if (i == 5) {
                sb.append(entry.getMapBufferValue().toString());
            }
            sb.append(',');
        }
        sb.append('}');
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "builder.toString()");
        return string;
    }

    /* JADX INFO: renamed from: com.facebook.react.common.mapbuffer.ReadableMapBuffer$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: ReadableMapBuffer.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\t\u0010\u000b\u001a\u00020\fH\u0096\u0002J\t\u0010\r\u001a\u00020\u0002H\u0096\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u000e"}, d2 = {"com/facebook/react/common/mapbuffer/ReadableMapBuffer$iterator$1", "", "Lcom/facebook/react/common/mapbuffer/MapBuffer$Entry;", "current", "", "getCurrent", "()I", "setCurrent", "(I)V", "last", "getLast", "hasNext", "", "next", "ReactAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterator<MapBuffer.Entry>, KMappedMarker {
        private int current;
        private final int last;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        AnonymousClass1() {
            this.last = ReadableMapBuffer.this.getCount() - 1;
        }

        public final int getCurrent() {
            return this.current;
        }

        public final void setCurrent(int i) {
            this.current = i;
        }

        public final int getLast() {
            return this.last;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.current <= this.last;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public MapBuffer.Entry next() {
            ReadableMapBuffer readableMapBuffer = ReadableMapBuffer.this;
            int i = this.current;
            this.current = i + 1;
            return new MapBufferEntry(readableMapBuffer, readableMapBuffer.getKeyOffsetForBucketIndex(i));
        }
    }

    @Override // java.lang.Iterable
    public Iterator<MapBuffer.Entry> iterator() {
        return new AnonymousClass1();
    }

    /* JADX INFO: compiled from: ReadableMapBuffer.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001bH\u0002R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006!"}, d2 = {"Lcom/facebook/react/common/mapbuffer/ReadableMapBuffer$MapBufferEntry;", "Lcom/facebook/react/common/mapbuffer/MapBuffer$Entry;", "bucketOffset", "", "(Lcom/facebook/react/common/mapbuffer/ReadableMapBuffer;I)V", "booleanValue", "", "getBooleanValue", "()Z", "doubleValue", "", "getDoubleValue", "()D", "intValue", "getIntValue", "()I", "key", "getKey", "mapBufferValue", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "getMapBufferValue", "()Lcom/facebook/react/common/mapbuffer/MapBuffer;", "stringValue", "", "getStringValue", "()Ljava/lang/String;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "getType", "()Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "assertType", "", "expected", "ReactAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class MapBufferEntry implements MapBuffer.Entry {
        private final int bucketOffset;
        final /* synthetic */ ReadableMapBuffer this$0;

        public MapBufferEntry(ReadableMapBuffer this$0, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.bucketOffset = i;
        }

        private final void assertType(MapBuffer.DataType expected) {
            MapBuffer.DataType type = getType();
            if (expected == type) {
                return;
            }
            throw new IllegalStateException(("Expected " + expected + " for key: " + getKey() + " found " + type + " instead.").toString());
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public int getKey() {
            return this.this$0.m2659readUnsignedShortBwKQO78(this.bucketOffset) & UShort.MAX_VALUE;
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public MapBuffer.DataType getType() {
            return MapBuffer.DataType.values()[this.this$0.m2659readUnsignedShortBwKQO78(this.bucketOffset + 2) & UShort.MAX_VALUE];
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public double getDoubleValue() {
            assertType(MapBuffer.DataType.DOUBLE);
            return this.this$0.readDoubleValue(this.bucketOffset + 4);
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public int getIntValue() {
            assertType(MapBuffer.DataType.INT);
            return this.this$0.readIntValue(this.bucketOffset + 4);
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public boolean getBooleanValue() {
            assertType(MapBuffer.DataType.BOOL);
            return this.this$0.readBooleanValue(this.bucketOffset + 4);
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public String getStringValue() {
            assertType(MapBuffer.DataType.STRING);
            return this.this$0.readStringValue(this.bucketOffset + 4);
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public MapBuffer getMapBufferValue() {
            assertType(MapBuffer.DataType.MAP);
            return this.this$0.readMapBufferValue(this.bucketOffset + 4);
        }
    }

    static {
        MapBufferSoLoader.staticInit();
    }
}
