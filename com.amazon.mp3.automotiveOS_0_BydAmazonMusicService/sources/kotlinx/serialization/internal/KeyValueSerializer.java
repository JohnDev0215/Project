package kotlinx.serialization.internal;

import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: Tuples.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b1\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u00028\u00020\u0004B%\b\u0004\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u0007\u001a\u00028\u00022\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00028\u0001H$¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00028\u0002H\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00028\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u00048\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u0018\u0010\u0005\u001a\u00028\u0000*\u00028\u00028$X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\u0006\u001a\u00028\u0001*\u00028\u00028$X¤\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0019\u0082\u0001\u0002\u001d\u001e¨\u0006\u001f"}, d2 = {"Lkotlinx/serialization/internal/KeyValueSerializer;", "K", "V", "R", "Lkotlinx/serialization/KSerializer;", "key", "value", "toResult", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/serialization/encoding/Encoder;", "encoder", "", "serialize", "(Lkotlinx/serialization/encoding/Encoder;Ljava/lang/Object;)V", "Lkotlinx/serialization/encoding/Decoder;", "decoder", "deserialize", "(Lkotlinx/serialization/encoding/Decoder;)Ljava/lang/Object;", "keySerializer", "Lkotlinx/serialization/KSerializer;", "getKeySerializer", "()Lkotlinx/serialization/KSerializer;", "valueSerializer", "getValueSerializer", "getKey", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "getValue", "<init>", "(Lkotlinx/serialization/KSerializer;Lkotlinx/serialization/KSerializer;)V", "Lkotlinx/serialization/internal/MapEntrySerializer;", "Lkotlinx/serialization/internal/PairSerializer;", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
@PublishedApi
public abstract class KeyValueSerializer<K, V, R> implements KSerializer<R> {
    private final KSerializer<K> keySerializer;
    private final KSerializer<V> valueSerializer;

    public /* synthetic */ KeyValueSerializer(KSerializer kSerializer, KSerializer kSerializer2, DefaultConstructorMarker defaultConstructorMarker) {
        this(kSerializer, kSerializer2);
    }

    protected abstract K getKey(R r);

    protected abstract V getValue(R r);

    protected abstract R toResult(K key, V value);

    private KeyValueSerializer(KSerializer<K> kSerializer, KSerializer<V> kSerializer2) {
        this.keySerializer = kSerializer;
        this.valueSerializer = kSerializer2;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, R value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(getDescriptor());
        compositeEncoderBeginStructure.encodeSerializableElement(getDescriptor(), 0, this.keySerializer, getKey(value));
        compositeEncoderBeginStructure.encodeSerializableElement(getDescriptor(), 1, this.valueSerializer, getValue(value));
        compositeEncoderBeginStructure.endStructure(getDescriptor());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.DeserializationStrategy
    public R deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(getDescriptor());
        if (!compositeDecoderBeginStructure.decodeSequentially()) {
            Object objDecodeSerializableElement$default = TuplesKt.NULL;
            Object objDecodeSerializableElement$default2 = TuplesKt.NULL;
            while (true) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(getDescriptor());
                if (iDecodeElementIndex == -1) {
                    compositeDecoderBeginStructure.endStructure(getDescriptor());
                    if (objDecodeSerializableElement$default != TuplesKt.NULL) {
                        if (objDecodeSerializableElement$default2 == TuplesKt.NULL) {
                            throw new SerializationException("Element 'value' is missing");
                        }
                        return (R) toResult(objDecodeSerializableElement$default, objDecodeSerializableElement$default2);
                    }
                    throw new SerializationException("Element 'key' is missing");
                }
                if (iDecodeElementIndex == 0) {
                    objDecodeSerializableElement$default = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), 0, this.keySerializer, null, 8, null);
                } else if (iDecodeElementIndex == 1) {
                    objDecodeSerializableElement$default2 = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), 1, this.valueSerializer, null, 8, null);
                } else {
                    throw new SerializationException("Invalid index: " + iDecodeElementIndex);
                }
            }
        } else {
            return (R) toResult(CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), 0, this.keySerializer, null, 8, null), CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoderBeginStructure, getDescriptor(), 1, this.valueSerializer, null, 8, null));
        }
    }
}
