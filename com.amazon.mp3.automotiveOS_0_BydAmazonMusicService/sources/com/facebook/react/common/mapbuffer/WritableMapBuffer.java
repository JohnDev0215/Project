package com.facebook.react.common.mapbuffer;

import android.util.SparseArray;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.common.mapbuffer.MapBuffer;
import java.util.Iterator;
import javax.annotation.concurrent.NotThreadSafe;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: WritableMapBuffer.kt */
/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
@NotThreadSafe
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000  2\u00020\u0001:\u0002 !B\u0007¢\u0006\u0004\b\u001e\u0010\u001fJ\u0014\u0010\u0006\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\b\u001a\u00020\u0007H\u0003J\u0015\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0096\u0002R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001d\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\""}, d2 = {"Lcom/facebook/react/common/mapbuffer/WritableMapBuffer;", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "", "", "key", "Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "dataType", "", "getKeys", "", "getValues", "()[Ljava/lang/Object;", "", "contains", "getBoolean", "getInt", "", "getDouble", "", "getString", "getMapBuffer", "", "Lcom/facebook/react/common/mapbuffer/MapBuffer$Entry;", "iterator", "Landroid/util/SparseArray;", "values", "Landroid/util/SparseArray;", "getCount", "()I", "count", "<init>", "()V", "Companion", "MapBufferEntry", "ReactAndroid_release"}, k = 1, mv = {1, 6, 0})
public final class WritableMapBuffer implements MapBuffer {
    private final SparseArray<Object> values = new SparseArray<>();

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public int getCount() {
        return this.values.size();
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public boolean contains(int key) {
        return this.values.get(key) != null;
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public boolean getBoolean(int key) {
        Object obj = this.values.get(key);
        if (!(obj != null)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
        }
        if (!(obj instanceof Boolean)) {
            throw new IllegalStateException(("Expected " + Boolean.class + " for key: " + key + ", found " + obj.getClass() + " instead.").toString());
        }
        return ((Boolean) obj).booleanValue();
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public int getInt(int key) {
        Object obj = this.values.get(key);
        if (!(obj != null)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
        }
        if (!(obj instanceof Integer)) {
            throw new IllegalStateException(("Expected " + Integer.class + " for key: " + key + ", found " + obj.getClass() + " instead.").toString());
        }
        return ((Number) obj).intValue();
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public double getDouble(int key) {
        Object obj = this.values.get(key);
        if (!(obj != null)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
        }
        if (!(obj instanceof Double)) {
            throw new IllegalStateException(("Expected " + Double.class + " for key: " + key + ", found " + obj.getClass() + " instead.").toString());
        }
        return ((Number) obj).doubleValue();
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public String getString(int key) {
        Object obj = this.values.get(key);
        if (!(obj != null)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
        }
        if (!(obj instanceof String)) {
            throw new IllegalStateException(("Expected " + String.class + " for key: " + key + ", found " + obj.getClass() + " instead.").toString());
        }
        return (String) obj;
    }

    @Override // com.facebook.react.common.mapbuffer.MapBuffer
    public MapBuffer getMapBuffer(int key) {
        Object obj = this.values.get(key);
        if (!(obj != null)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
        }
        if (!(obj instanceof MapBuffer)) {
            throw new IllegalStateException(("Expected " + MapBuffer.class + " for key: " + key + ", found " + obj.getClass() + " instead.").toString());
        }
        return (MapBuffer) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MapBuffer.DataType dataType(Object obj, int i) {
        if (obj instanceof Boolean) {
            return MapBuffer.DataType.BOOL;
        }
        if (obj instanceof Integer) {
            return MapBuffer.DataType.INT;
        }
        if (obj instanceof Double) {
            return MapBuffer.DataType.DOUBLE;
        }
        if (obj instanceof String) {
            return MapBuffer.DataType.STRING;
        }
        if (obj instanceof MapBuffer) {
            return MapBuffer.DataType.MAP;
        }
        throw new IllegalStateException("Key " + i + " has value of unknown type: " + obj.getClass());
    }

    /* JADX INFO: renamed from: com.facebook.react.common.mapbuffer.WritableMapBuffer$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: WritableMapBuffer.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\t\u0010\t\u001a\u00020\nH\u0096\u0002J\t\u0010\u000b\u001a\u00020\u0002H\u0096\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"com/facebook/react/common/mapbuffer/WritableMapBuffer$iterator$1", "", "Lcom/facebook/react/common/mapbuffer/MapBuffer$Entry;", "count", "", "getCount", "()I", "setCount", "(I)V", "hasNext", "", "next", "ReactAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterator<MapBuffer.Entry>, KMappedMarker {
        private int count;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        AnonymousClass1() {
        }

        public final int getCount() {
            return this.count;
        }

        public final void setCount(int i) {
            this.count = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.count < WritableMapBuffer.this.values.size();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public MapBuffer.Entry next() {
            WritableMapBuffer writableMapBuffer = WritableMapBuffer.this;
            int i = this.count;
            this.count = i + 1;
            return new MapBufferEntry(writableMapBuffer, i);
        }
    }

    @Override // java.lang.Iterable
    public Iterator<MapBuffer.Entry> iterator() {
        return new AnonymousClass1();
    }

    /* JADX INFO: compiled from: WritableMapBuffer.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lcom/facebook/react/common/mapbuffer/WritableMapBuffer$MapBufferEntry;", "Lcom/facebook/react/common/mapbuffer/MapBuffer$Entry;", "index", "", "(Lcom/facebook/react/common/mapbuffer/WritableMapBuffer;I)V", "booleanValue", "", "getBooleanValue", "()Z", "doubleValue", "", "getDoubleValue", "()D", "intValue", "getIntValue", "()I", "key", "getKey", "mapBufferValue", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "getMapBufferValue", "()Lcom/facebook/react/common/mapbuffer/MapBuffer;", "stringValue", "", "getStringValue", "()Ljava/lang/String;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "getType", "()Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "ReactAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class MapBufferEntry implements MapBuffer.Entry {
        private final int index;
        private final int key;
        final /* synthetic */ WritableMapBuffer this$0;
        private final MapBuffer.DataType type;

        public MapBufferEntry(WritableMapBuffer this$0, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.index = i;
            this.key = this$0.values.keyAt(i);
            Object objValueAt = this$0.values.valueAt(i);
            Intrinsics.checkNotNullExpressionValue(objValueAt, "values.valueAt(index)");
            this.type = this$0.dataType(objValueAt, getKey());
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public int getKey() {
            return this.key;
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public MapBuffer.DataType getType() {
            return this.type;
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public boolean getBooleanValue() {
            int key = getKey();
            Object objValueAt = this.this$0.values.valueAt(this.index);
            if (!(objValueAt != null)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
            }
            if (!(objValueAt instanceof Boolean)) {
                throw new IllegalStateException(("Expected " + Boolean.class + " for key: " + key + ", found " + objValueAt.getClass() + " instead.").toString());
            }
            return ((Boolean) objValueAt).booleanValue();
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public int getIntValue() {
            int key = getKey();
            Object objValueAt = this.this$0.values.valueAt(this.index);
            if (!(objValueAt != null)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
            }
            if (!(objValueAt instanceof Integer)) {
                throw new IllegalStateException(("Expected " + Integer.class + " for key: " + key + ", found " + objValueAt.getClass() + " instead.").toString());
            }
            return ((Number) objValueAt).intValue();
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public double getDoubleValue() {
            int key = getKey();
            Object objValueAt = this.this$0.values.valueAt(this.index);
            if (!(objValueAt != null)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
            }
            if (!(objValueAt instanceof Double)) {
                throw new IllegalStateException(("Expected " + Double.class + " for key: " + key + ", found " + objValueAt.getClass() + " instead.").toString());
            }
            return ((Number) objValueAt).doubleValue();
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public String getStringValue() {
            int key = getKey();
            Object objValueAt = this.this$0.values.valueAt(this.index);
            if (!(objValueAt != null)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
            }
            if (!(objValueAt instanceof String)) {
                throw new IllegalStateException(("Expected " + String.class + " for key: " + key + ", found " + objValueAt.getClass() + " instead.").toString());
            }
            return (String) objValueAt;
        }

        @Override // com.facebook.react.common.mapbuffer.MapBuffer.Entry
        public MapBuffer getMapBufferValue() {
            int key = getKey();
            Object objValueAt = this.this$0.values.valueAt(this.index);
            if (!(objValueAt != null)) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Key not found: ", Integer.valueOf(key)).toString());
            }
            if (!(objValueAt instanceof MapBuffer)) {
                throw new IllegalStateException(("Expected " + MapBuffer.class + " for key: " + key + ", found " + objValueAt.getClass() + " instead.").toString());
            }
            return (MapBuffer) objValueAt;
        }
    }

    @DoNotStrip
    private final int[] getKeys() {
        int size = this.values.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = this.values.keyAt(i);
        }
        return iArr;
    }

    @DoNotStrip
    private final Object[] getValues() {
        int size = this.values.size();
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            Object objValueAt = this.values.valueAt(i);
            Intrinsics.checkNotNullExpressionValue(objValueAt, "values.valueAt(it)");
            objArr[i] = objValueAt;
        }
        return objArr;
    }

    static {
        MapBufferSoLoader.staticInit();
    }
}
