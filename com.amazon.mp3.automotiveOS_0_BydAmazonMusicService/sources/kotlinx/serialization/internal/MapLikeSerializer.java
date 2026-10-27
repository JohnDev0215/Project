package kotlinx.serialization.internal;

import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: CollectionSerializers.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0014\b\u0003\u0010\u0005*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042 \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006B%\b\u0004\u0012\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b\u0012\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00010\u001b¢\u0006\u0004\b&\u0010'J/\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00028\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0004¢\u0006\u0004\b\u000f\u0010\u0010J/\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00028\u00032\u0006\u0010\u0013\u001a\u00020\u0012H\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00028\u0002H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00010\u001b8\u0006¢\u0006\f\n\u0004\b \u0010\u001d\u001a\u0004\b!\u0010\u001fR\u0014\u0010%\u001a\u00020\"8&X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$\u0082\u0001\u0002()¨\u0006*"}, d2 = {"Lkotlinx/serialization/internal/MapLikeSerializer;", "Key", "Value", "Collection", "", "Builder", "Lkotlinx/serialization/internal/AbstractCollectionSerializer;", "", "Lkotlinx/serialization/encoding/CompositeDecoder;", "decoder", "builder", "", "startIndex", "size", "", "readAll", "(Lkotlinx/serialization/encoding/CompositeDecoder;Ljava/util/Map;II)V", "index", "", "checkIndex", "readElement", "(Lkotlinx/serialization/encoding/CompositeDecoder;ILjava/util/Map;Z)V", "Lkotlinx/serialization/encoding/Encoder;", "encoder", "value", "serialize", "(Lkotlinx/serialization/encoding/Encoder;Ljava/lang/Object;)V", "Lkotlinx/serialization/KSerializer;", "keySerializer", "Lkotlinx/serialization/KSerializer;", "getKeySerializer", "()Lkotlinx/serialization/KSerializer;", "valueSerializer", "getValueSerializer", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "<init>", "(Lkotlinx/serialization/KSerializer;Lkotlinx/serialization/KSerializer;)V", "Lkotlinx/serialization/internal/HashMapSerializer;", "Lkotlinx/serialization/internal/LinkedHashMapSerializer;", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
public abstract class MapLikeSerializer<Key, Value, Collection, Builder extends Map<Key, Value>> extends AbstractCollectionSerializer<Map.Entry<? extends Key, ? extends Value>, Collection, Builder> {
    private final KSerializer<Key> keySerializer;
    private final KSerializer<Value> valueSerializer;

    public /* synthetic */ MapLikeSerializer(KSerializer kSerializer, KSerializer kSerializer2, DefaultConstructorMarker defaultConstructorMarker) {
        this(kSerializer, kSerializer2);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public abstract SerialDescriptor getDescriptor();

    public final KSerializer<Key> getKeySerializer() {
        return this.keySerializer;
    }

    public final KSerializer<Value> getValueSerializer() {
        return this.valueSerializer;
    }

    private MapLikeSerializer(KSerializer<Key> kSerializer, KSerializer<Value> kSerializer2) {
        super(null);
        this.keySerializer = kSerializer;
        this.valueSerializer = kSerializer2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public final void readAll(CompositeDecoder decoder, Builder builder, int startIndex, int size) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        if (!(size >= 0)) {
            throw new IllegalArgumentException("Size must be known in advance when using READ_ALL".toString());
        }
        IntProgression intProgressionStep = RangesKt___RangesKt.step(RangesKt___RangesKt.until(0, size * 2), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step <= 0 || first > last) && (step >= 0 || last > first)) {
            return;
        }
        while (true) {
            readElement(decoder, startIndex + first, (Map) builder, false);
            if (first == last) {
                return;
            } else {
                first += step;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public final void readElement(CompositeDecoder decoder, int index, Builder builder, boolean checkIndex) {
        int iDecodeElementIndex;
        Object objDecodeSerializableElement$default;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(builder, "builder");
        Object objDecodeSerializableElement$default2 = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(decoder, getDescriptor(), index, this.keySerializer, null, 8, null);
        if (checkIndex) {
            iDecodeElementIndex = decoder.decodeElementIndex(getDescriptor());
            if (!(iDecodeElementIndex == index + 1)) {
                throw new IllegalArgumentException(("Value must follow key in a map, index for key: " + index + ", returned index for value: " + iDecodeElementIndex).toString());
            }
        } else {
            iDecodeElementIndex = index + 1;
        }
        int i = iDecodeElementIndex;
        if (builder.containsKey(objDecodeSerializableElement$default2) && !(this.valueSerializer.getDescriptor().getKind() instanceof PrimitiveKind)) {
            objDecodeSerializableElement$default = decoder.decodeSerializableElement(getDescriptor(), i, this.valueSerializer, MapsKt__MapsKt.getValue(builder, objDecodeSerializableElement$default2));
        } else {
            objDecodeSerializableElement$default = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(decoder, getDescriptor(), i, this.valueSerializer, null, 8, null);
        }
        builder.put(objDecodeSerializableElement$default2, objDecodeSerializableElement$default);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Collection value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        int iCollectionSize = collectionSize(value);
        SerialDescriptor descriptor = getDescriptor();
        CompositeEncoder compositeEncoderBeginCollection = encoder.beginCollection(descriptor, iCollectionSize);
        Iterator<Map.Entry<? extends Key, ? extends Value>> itCollectionIterator = collectionIterator(value);
        int i = 0;
        while (itCollectionIterator.hasNext()) {
            Map.Entry<? extends Key, ? extends Value> next = itCollectionIterator.next();
            Key key = next.getKey();
            Value value2 = next.getValue();
            int i2 = i + 1;
            compositeEncoderBeginCollection.encodeSerializableElement(getDescriptor(), i, getKeySerializer(), key);
            compositeEncoderBeginCollection.encodeSerializableElement(getDescriptor(), i2, getValueSerializer(), value2);
            i = i2 + 1;
        }
        compositeEncoderBeginCollection.endStructure(descriptor);
    }
}
