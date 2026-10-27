package kotlinx.serialization.internal;

import com.amazon.music.destination.parser.ParserUtil;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuildersKt;

/* JADX INFO: compiled from: Tagged.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\bi\u0010jJ+\u0010\b\u001a\u00028\u0001\"\u0004\b\u0001\u0010\u00042\u0006\u0010\u0005\u001a\u00028\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006H\u0002¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\r\u001a\u00028\u0000*\u00020\n2\u0006\u0010\f\u001a\u00020\u000bH$¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u0015\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0005\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010\"\u001a\u00020!2\u0006\u0010\u0005\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\"\u0010#J\u0017\u0010%\u001a\u00020$2\u0006\u0010\u0005\u001a\u00028\u0000H\u0014¢\u0006\u0004\b%\u0010&J\u0017\u0010(\u001a\u00020'2\u0006\u0010\u0005\u001a\u00028\u0000H\u0014¢\u0006\u0004\b(\u0010)J\u0017\u0010+\u001a\u00020*2\u0006\u0010\u0005\u001a\u00028\u0000H\u0014¢\u0006\u0004\b+\u0010,J\u001f\u0010.\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010-\u001a\u00020\nH\u0014¢\u0006\u0004\b.\u0010/J\u001f\u00101\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u00100\u001a\u00020\nH\u0014¢\u0006\u0004\b1\u00102J-\u00107\u001a\u00028\u0001\"\u0004\b\u0001\u001032\f\u00105\u001a\b\u0012\u0004\u0012\u00028\u0001042\b\u00106\u001a\u0004\u0018\u00018\u0001H\u0014¢\u0006\u0004\b7\u00108J\u000e\u0010:\u001a\u00020\u00022\u0006\u00109\u001a\u00020\nJ\b\u0010;\u001a\u00020\u0012H\u0016J\b\u0010=\u001a\u0004\u0018\u00010<J\u0006\u0010>\u001a\u00020\u0012J\u0006\u0010?\u001a\u00020\u0016J\u0006\u0010@\u001a\u00020\u0019J\u0006\u0010A\u001a\u00020\u000bJ\u0006\u0010B\u001a\u00020\u001eJ\u0006\u0010C\u001a\u00020!J\u0006\u0010D\u001a\u00020$J\u0006\u0010E\u001a\u00020'J\u0006\u0010F\u001a\u00020*J\u000e\u0010G\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\nJ\u0010\u0010H\u001a\u00020\u00032\u0006\u00109\u001a\u00020\nH\u0016J\u0010\u0010J\u001a\u00020I2\u0006\u00109\u001a\u00020\nH\u0016J\u0016\u0010K\u001a\u00020\u00122\u0006\u00109\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000bJ\u0016\u0010L\u001a\u00020\u00162\u0006\u00109\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000bJ\u0016\u0010M\u001a\u00020\u00192\u0006\u00109\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000bJ\u0016\u0010N\u001a\u00020\u000b2\u0006\u00109\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000bJ\u0016\u0010O\u001a\u00020\u001e2\u0006\u00109\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000bJ\u0016\u0010P\u001a\u00020!2\u0006\u00109\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000bJ\u0016\u0010Q\u001a\u00020$2\u0006\u00109\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000bJ\u0016\u0010R\u001a\u00020'2\u0006\u00109\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000bJ\u0016\u0010S\u001a\u00020*2\u0006\u00109\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000bJ\u0016\u0010T\u001a\u00020\u00022\u0006\u00109\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000bJ;\u0010U\u001a\u00028\u0001\"\u0004\b\u0001\u001032\u0006\u00109\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000b2\f\u00105\u001a\b\u0012\u0004\u0012\u00028\u0001042\b\u00106\u001a\u0004\u0018\u00018\u0001¢\u0006\u0004\bU\u0010VJC\u0010W\u001a\u0004\u0018\u00018\u0001\"\b\b\u0001\u00103*\u00020\u000f2\u0006\u00109\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000b2\u000e\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u0001042\b\u00106\u001a\u0004\u0018\u00018\u0001¢\u0006\u0004\bW\u0010VJ\u0017\u0010Y\u001a\u00020I2\u0006\u0010X\u001a\u00028\u0000H\u0004¢\u0006\u0004\bY\u0010ZJ\u000f\u0010[\u001a\u00028\u0000H\u0004¢\u0006\u0004\b[\u0010\\R$\u0010_\u001a\u0012\u0012\u0004\u0012\u00028\u00000]j\b\u0012\u0004\u0012\u00028\u0000`^8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b_\u0010`R\u0016\u0010a\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\ba\u0010bR\u0014\u0010f\u001a\u00020c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bd\u0010eR\u0016\u0010h\u001a\u0004\u0018\u00018\u00008DX\u0084\u0004¢\u0006\u0006\u001a\u0004\bg\u0010\\¨\u0006k"}, d2 = {"Lkotlinx/serialization/internal/TaggedDecoder;", "Tag", "Lkotlinx/serialization/encoding/Decoder;", "Lkotlinx/serialization/encoding/CompositeDecoder;", "E", ParserUtil.TAG_QUERY_PARAM_NAME, "Lkotlin/Function0;", "block", "tagBlock", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "", "index", "getTag", "(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Ljava/lang/Object;", "", "decodeTaggedValue", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "", "decodeTaggedNotNullMark", "(Ljava/lang/Object;)Z", "decodeTaggedBoolean", "", "decodeTaggedByte", "(Ljava/lang/Object;)B", "", "decodeTaggedShort", "(Ljava/lang/Object;)S", "decodeTaggedInt", "(Ljava/lang/Object;)I", "", "decodeTaggedLong", "(Ljava/lang/Object;)J", "", "decodeTaggedFloat", "(Ljava/lang/Object;)F", "", "decodeTaggedDouble", "(Ljava/lang/Object;)D", "", "decodeTaggedChar", "(Ljava/lang/Object;)C", "", "decodeTaggedString", "(Ljava/lang/Object;)Ljava/lang/String;", "enumDescriptor", "decodeTaggedEnum", "(Ljava/lang/Object;Lkotlinx/serialization/descriptors/SerialDescriptor;)I", "inlineDescriptor", "decodeTaggedInline", "(Ljava/lang/Object;Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/encoding/Decoder;", "T", "Lkotlinx/serialization/DeserializationStrategy;", "deserializer", "previousValue", "decodeSerializableValue", "(Lkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;", "descriptor", "decodeInline", "decodeNotNullMark", "", "decodeNull", "decodeBoolean", "decodeByte", "decodeShort", "decodeInt", "decodeLong", "decodeFloat", "decodeDouble", "decodeChar", "decodeString", "decodeEnum", "beginStructure", "", "endStructure", "decodeBooleanElement", "decodeByteElement", "decodeShortElement", "decodeIntElement", "decodeLongElement", "decodeFloatElement", "decodeDoubleElement", "decodeCharElement", "decodeStringElement", "decodeInlineElement", "decodeSerializableElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;", "decodeNullableSerializableElement", "name", "pushTag", "(Ljava/lang/Object;)V", "popTag", "()Ljava/lang/Object;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "tagStack", "Ljava/util/ArrayList;", "flag", "Z", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "serializersModule", "getCurrentTagOrNull", "currentTagOrNull", "<init>", "()V", "kotlinx-serialization-core"}, k = 1, mv = {1, 7, 1})
public abstract class TaggedDecoder<Tag> implements Decoder, CompositeDecoder {
    private boolean flag;
    private final ArrayList<Tag> tagStack = new ArrayList<>();

    @Override // kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final Void decodeNull() {
        return null;
    }

    protected boolean decodeTaggedNotNullMark(Tag tag) {
        return true;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
    }

    protected abstract Tag getTag(SerialDescriptor serialDescriptor, int i);

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeCollectionSize(SerialDescriptor serialDescriptor) {
        return CompositeDecoder.DefaultImpls.decodeCollectionSize(this, serialDescriptor);
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public boolean decodeSequentially() {
        return CompositeDecoder.DefaultImpls.decodeSequentially(this);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<T> deserializationStrategy) {
        return (T) Decoder.DefaultImpls.decodeSerializableValue(this, deserializationStrategy);
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return SerializersModuleBuildersKt.EmptySerializersModule();
    }

    protected Object decodeTaggedValue(Tag tag) {
        throw new SerializationException(Reflection.getOrCreateKotlinClass(getClass()) + " can't retrieve untyped values");
    }

    protected boolean decodeTaggedBoolean(Tag tag) {
        Object objDecodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(objDecodeTaggedValue, "null cannot be cast to non-null type kotlin.Boolean");
        return ((Boolean) objDecodeTaggedValue).booleanValue();
    }

    protected byte decodeTaggedByte(Tag tag) {
        Object objDecodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(objDecodeTaggedValue, "null cannot be cast to non-null type kotlin.Byte");
        return ((Byte) objDecodeTaggedValue).byteValue();
    }

    protected short decodeTaggedShort(Tag tag) {
        Object objDecodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(objDecodeTaggedValue, "null cannot be cast to non-null type kotlin.Short");
        return ((Short) objDecodeTaggedValue).shortValue();
    }

    protected int decodeTaggedInt(Tag tag) {
        Object objDecodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(objDecodeTaggedValue, "null cannot be cast to non-null type kotlin.Int");
        return ((Integer) objDecodeTaggedValue).intValue();
    }

    protected long decodeTaggedLong(Tag tag) {
        Object objDecodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(objDecodeTaggedValue, "null cannot be cast to non-null type kotlin.Long");
        return ((Long) objDecodeTaggedValue).longValue();
    }

    protected float decodeTaggedFloat(Tag tag) {
        Object objDecodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(objDecodeTaggedValue, "null cannot be cast to non-null type kotlin.Float");
        return ((Float) objDecodeTaggedValue).floatValue();
    }

    protected double decodeTaggedDouble(Tag tag) {
        Object objDecodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(objDecodeTaggedValue, "null cannot be cast to non-null type kotlin.Double");
        return ((Double) objDecodeTaggedValue).doubleValue();
    }

    protected char decodeTaggedChar(Tag tag) {
        Object objDecodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(objDecodeTaggedValue, "null cannot be cast to non-null type kotlin.Char");
        return ((Character) objDecodeTaggedValue).charValue();
    }

    protected String decodeTaggedString(Tag tag) {
        Object objDecodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(objDecodeTaggedValue, "null cannot be cast to non-null type kotlin.String");
        return (String) objDecodeTaggedValue;
    }

    protected int decodeTaggedEnum(Tag tag, SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        Object objDecodeTaggedValue = decodeTaggedValue(tag);
        Intrinsics.checkNotNull(objDecodeTaggedValue, "null cannot be cast to non-null type kotlin.Int");
        return ((Integer) objDecodeTaggedValue).intValue();
    }

    protected Decoder decodeTaggedInline(Tag tag, SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        pushTag(tag);
        return this;
    }

    protected <T> T decodeSerializableValue(DeserializationStrategy<T> deserializer, T previousValue) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return (T) decodeSerializableValue(deserializer);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final Decoder decodeInline(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedInline(popTag(), descriptor);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        Tag currentTagOrNull = getCurrentTagOrNull();
        if (currentTagOrNull == null) {
            return false;
        }
        return decodeTaggedNotNullMark(currentTagOrNull);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final boolean decodeBoolean() {
        return decodeTaggedBoolean(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final byte decodeByte() {
        return decodeTaggedByte(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final short decodeShort() {
        return decodeTaggedShort(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final int decodeInt() {
        return decodeTaggedInt(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final long decodeLong() {
        return decodeTaggedLong(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final float decodeFloat() {
        return decodeTaggedFloat(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final double decodeDouble() {
        return decodeTaggedDouble(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final char decodeChar() {
        return decodeTaggedChar(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final String decodeString() {
        return decodeTaggedString(popTag());
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final int decodeEnum(SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        return decodeTaggedEnum(popTag(), enumDescriptor);
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final boolean decodeBooleanElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedBoolean(getTag(descriptor, index));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final byte decodeByteElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedByte(getTag(descriptor, index));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final short decodeShortElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedShort(getTag(descriptor, index));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final int decodeIntElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedInt(getTag(descriptor, index));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final long decodeLongElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedLong(getTag(descriptor, index));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final float decodeFloatElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedFloat(getTag(descriptor, index));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final double decodeDoubleElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedDouble(getTag(descriptor, index));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final char decodeCharElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedChar(getTag(descriptor, index));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final String decodeStringElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedString(getTag(descriptor, index));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final Decoder decodeInlineElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return decodeTaggedInline(getTag(descriptor, index), descriptor.getElementDescriptor(index));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final <T> T decodeSerializableElement(SerialDescriptor descriptor, int index, final DeserializationStrategy<T> deserializer, final T previousValue) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return (T) tagBlock(getTag(descriptor, index), new Function0<T>(this) { // from class: kotlinx.serialization.internal.TaggedDecoder.decodeSerializableElement.1
            final /* synthetic */ TaggedDecoder<Tag> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                return (T) this.this$0.decodeSerializableValue(deserializer, previousValue);
            }
        });
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final <T> T decodeNullableSerializableElement(SerialDescriptor descriptor, int index, final DeserializationStrategy<T> deserializer, final T previousValue) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return (T) tagBlock(getTag(descriptor, index), new Function0<T>(this) { // from class: kotlinx.serialization.internal.TaggedDecoder.decodeNullableSerializableElement.1
            final /* synthetic */ TaggedDecoder<Tag> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                if (this.this$0.decodeNotNullMark()) {
                    return (T) this.this$0.decodeSerializableValue(deserializer, previousValue);
                }
                return (T) this.this$0.decodeNull();
            }
        });
    }

    private final <E> E tagBlock(Tag tag, Function0<? extends E> block) {
        pushTag(tag);
        E eInvoke = block.invoke();
        if (!this.flag) {
            popTag();
        }
        this.flag = false;
        return eInvoke;
    }

    protected final Tag getCurrentTagOrNull() {
        return (Tag) CollectionsKt___CollectionsKt.lastOrNull((List) this.tagStack);
    }

    protected final void pushTag(Tag name) {
        this.tagStack.add(name);
    }

    protected final Tag popTag() {
        ArrayList<Tag> arrayList = this.tagStack;
        Tag tagRemove = arrayList.remove(CollectionsKt__CollectionsKt.getLastIndex(arrayList));
        this.flag = true;
        return tagRemove;
    }
}
