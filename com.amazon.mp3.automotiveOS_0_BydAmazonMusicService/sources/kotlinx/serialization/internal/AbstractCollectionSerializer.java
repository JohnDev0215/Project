package kotlinx.serialization.internal;

import com.google.firebase.perf.plugin.util.AsmString;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;

/* JADX INFO: compiled from: CollectionSerializers.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u00028\u00010\u0004B\t\b\u0004¢\u0006\u0004\b'\u0010(J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00028\u0002H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\b*\u00028\u0001H$¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r*\u00028\u0001H$¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0007\u001a\u00028\u0002H$¢\u0006\u0004\b\u0007\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\b*\u00028\u0002H$¢\u0006\u0004\b\u0011\u0010\fJ\u0013\u0010\u0012\u001a\u00028\u0001*\u00028\u0002H$¢\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00028\u0002*\u00028\u0001H$¢\u0006\u0004\b\u0014\u0010\u0013J\u001b\u0010\u0017\u001a\u00020\u0016*\u00028\u00022\u0006\u0010\u0015\u001a\u00020\bH$¢\u0006\u0004\b\u0017\u0010\u0018J!\u0010\u001b\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00018\u0001H\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ1\u0010\"\u001a\u00020\u00162\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u00022\b\b\u0002\u0010!\u001a\u00020 H$¢\u0006\u0004\b\"\u0010#J/\u0010%\u001a\u00020\u00162\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00028\u00022\u0006\u0010$\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\bH$¢\u0006\u0004\b%\u0010&\u0082\u0001\u0002)*¨\u0006+"}, d2 = {"Lkotlinx/serialization/internal/AbstractCollectionSerializer;", "Element", "Collection", "Builder", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/serialization/encoding/CompositeDecoder;", "decoder", "builder", "", "readSize", "(Lkotlinx/serialization/encoding/CompositeDecoder;Ljava/lang/Object;)I", "collectionSize", "(Ljava/lang/Object;)I", "", "collectionIterator", "(Ljava/lang/Object;)Ljava/util/Iterator;", "()Ljava/lang/Object;", "builderSize", "toResult", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "toBuilder", "size", "", "checkCapacity", "(Ljava/lang/Object;I)V", "Lkotlinx/serialization/encoding/Decoder;", "previous", "merge", "(Lkotlinx/serialization/encoding/Decoder;Ljava/lang/Object;)Ljava/lang/Object;", "deserialize", "(Lkotlinx/serialization/encoding/Decoder;)Ljava/lang/Object;", "index", "", "checkIndex", "readElement", "(Lkotlinx/serialization/encoding/CompositeDecoder;ILjava/lang/Object;Z)V", "startIndex", "readAll", "(Lkotlinx/serialization/encoding/CompositeDecoder;Ljava/lang/Object;II)V", "<init>", "()V", "Lkotlinx/serialization/internal/CollectionLikeSerializer;", "Lkotlinx/serialization/internal/MapLikeSerializer;", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
public abstract class AbstractCollectionSerializer<Element, Collection, Builder> implements KSerializer<Collection> {
    public /* synthetic */ AbstractCollectionSerializer(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    protected abstract Builder builder();

    protected abstract int builderSize(Builder builder);

    protected abstract void checkCapacity(Builder builder, int i);

    protected abstract Iterator<Element> collectionIterator(Collection collection);

    protected abstract int collectionSize(Collection collection);

    protected abstract void readAll(CompositeDecoder decoder, Builder builder, int startIndex, int size);

    protected abstract void readElement(CompositeDecoder decoder, int index, Builder builder, boolean checkIndex);

    protected abstract Builder toBuilder(Collection collection);

    protected abstract Collection toResult(Builder builder);

    private AbstractCollectionSerializer() {
    }

    public final Collection merge(Decoder decoder, Collection previous) {
        Builder builder;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        if (previous == null || (builder = toBuilder(previous)) == null) {
            builder = builder();
        }
        int iBuilderSize = builderSize(builder);
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(getDescriptor());
        if (!compositeDecoderBeginStructure.decodeSequentially()) {
            while (true) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(getDescriptor());
                if (iDecodeElementIndex == -1) {
                    break;
                }
                readElement$default(this, compositeDecoderBeginStructure, iBuilderSize + iDecodeElementIndex, builder, false, 8, null);
            }
        } else {
            readAll(compositeDecoderBeginStructure, builder, iBuilderSize, readSize(compositeDecoderBeginStructure, builder));
        }
        compositeDecoderBeginStructure.endStructure(getDescriptor());
        return toResult(builder);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Collection deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        return merge(decoder, null);
    }

    private final int readSize(CompositeDecoder decoder, Builder builder) {
        int iDecodeCollectionSize = decoder.decodeCollectionSize(getDescriptor());
        checkCapacity(builder, iDecodeCollectionSize);
        return iDecodeCollectionSize;
    }

    public static /* synthetic */ void readElement$default(AbstractCollectionSerializer abstractCollectionSerializer, CompositeDecoder compositeDecoder, int i, Object obj, boolean z, int i2, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readElement");
        }
        if ((i2 & 8) != 0) {
            z = true;
        }
        abstractCollectionSerializer.readElement(compositeDecoder, i, obj, z);
    }
}
