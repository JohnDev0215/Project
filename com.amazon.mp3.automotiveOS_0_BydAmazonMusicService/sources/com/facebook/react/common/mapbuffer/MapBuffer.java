package com.facebook.react.common.mapbuffer;

import androidx.core.internal.view.SupportMenu;
import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: MapBuffer.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\bf\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u0011\u0012\u0013J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\r\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003H&R\u0014\u0010\u0010\u001a\u00020\u00038&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/facebook/react/common/mapbuffer/MapBuffer;", "", "Lcom/facebook/react/common/mapbuffer/MapBuffer$Entry;", "", "key", "", "contains", "getBoolean", "getInt", "", "getDouble", "", "getString", "getMapBuffer", "getCount", "()I", "count", "Companion", "DataType", "Entry", "ReactAndroid_release"}, k = 1, mv = {1, 6, 0})
public interface MapBuffer extends Iterable<Entry>, KMappedMarker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: MapBuffer.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "", "(Ljava/lang/String;I)V", "BOOL", "INT", "DOUBLE", "STRING", "MAP", "ReactAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum DataType {
        BOOL,
        INT,
        DOUBLE,
        STRING,
        MAP
    }

    /* JADX INFO: compiled from: MapBuffer.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0018\u001a\u00020\u0019X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/facebook/react/common/mapbuffer/MapBuffer$Entry;", "", "booleanValue", "", "getBooleanValue", "()Z", "doubleValue", "", "getDoubleValue", "()D", "intValue", "", "getIntValue", "()I", "key", "getKey", "mapBufferValue", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "getMapBufferValue", "()Lcom/facebook/react/common/mapbuffer/MapBuffer;", "stringValue", "", "getStringValue", "()Ljava/lang/String;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "getType", "()Lcom/facebook/react/common/mapbuffer/MapBuffer$DataType;", "ReactAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Entry {
        boolean getBooleanValue();

        double getDoubleValue();

        int getIntValue();

        int getKey();

        MapBuffer getMapBufferValue();

        String getStringValue();

        DataType getType();
    }

    boolean contains(int key);

    boolean getBoolean(int key);

    int getCount();

    double getDouble(int key);

    int getInt(int key);

    MapBuffer getMapBuffer(int key);

    String getString(int key);

    /* JADX INFO: compiled from: MapBuffer.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/common/mapbuffer/MapBuffer$Companion;", "", "()V", "KEY_RANGE", "Lkotlin/ranges/IntRange;", "getKEY_RANGE$ReactAndroid_release", "()Lkotlin/ranges/IntRange;", "ReactAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final IntRange KEY_RANGE = new IntRange(0, SupportMenu.USER_MASK);

        private Companion() {
        }

        public final IntRange getKEY_RANGE$ReactAndroid_release() {
            return KEY_RANGE;
        }
    }
}
