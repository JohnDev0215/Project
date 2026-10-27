package kotlinx.serialization.json.internal;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt___StringsKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.internal.NamedValueDecoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: TreeJsonDecoder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\b\u0004\u0012\u0006\u00108\u001a\u000207\u0012\u0006\u0010<\u001a\u00020\u0003¢\u0006\u0004\bG\u0010HJ\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0014\u0010\f\u001a\u00020\u000b*\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0002J\b\u0010\r\u001a\u00020\u0003H\u0016J#\u0010\u0011\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0014J\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\u0010\u0010\u001f\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u0005H\u0004J\u0010\u0010 \u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0005H$J\u0018\u0010#\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0016H\u0014J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u0005H\u0014J\u0010\u0010%\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u0005H\u0014J\u0010\u0010'\u001a\u00020&2\u0006\u0010\u001e\u001a\u00020\u0005H\u0014J\u0010\u0010)\u001a\u00020(2\u0006\u0010\u001e\u001a\u00020\u0005H\u0014J\u0010\u0010*\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u0005H\u0014J\u0010\u0010,\u001a\u00020+2\u0006\u0010\u001e\u001a\u00020\u0005H\u0014J\u0010\u0010.\u001a\u00020-2\u0006\u0010\u001e\u001a\u00020\u0005H\u0014J\u0010\u00100\u001a\u00020/2\u0006\u0010\u001e\u001a\u00020\u0005H\u0014J\u0010\u00102\u001a\u0002012\u0006\u0010\u001e\u001a\u00020\u0005H\u0014J\u0010\u00103\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0005H\u0014J\u0018\u00106\u001a\u0002052\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u0016H\u0014R\u001a\u00108\u001a\u0002078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?R\u0014\u0010A\u001a\u00020@8\u0004X\u0085\u0004¢\u0006\u0006\n\u0004\bA\u0010BR\u0014\u0010F\u001a\u00020C8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bD\u0010E\u0082\u0001\u0003IJK¨\u0006L"}, d2 = {"Lkotlinx/serialization/json/internal/AbstractJsonTreeDecoder;", "Lkotlinx/serialization/internal/NamedValueDecoder;", "Lkotlinx/serialization/json/JsonDecoder;", "Lkotlinx/serialization/json/JsonElement;", "currentObject", "", "primitive", "", "unparsedPrimitive", "Lkotlinx/serialization/json/JsonPrimitive;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/serialization/json/JsonLiteral;", "asLiteral", "decodeJsonElement", "T", "Lkotlinx/serialization/DeserializationStrategy;", "deserializer", "decodeSerializableValue", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "parentName", "childName", "composeName", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "Lkotlinx/serialization/encoding/CompositeDecoder;", "beginStructure", "", "endStructure", "", "decodeNotNullMark", ParserUtil.TAG_QUERY_PARAM_NAME, "getPrimitiveValue", "currentElement", "enumDescriptor", "", "decodeTaggedEnum", "decodeTaggedNotNullMark", "decodeTaggedBoolean", "", "decodeTaggedByte", "", "decodeTaggedShort", "decodeTaggedInt", "", "decodeTaggedLong", "", "decodeTaggedFloat", "", "decodeTaggedDouble", "", "decodeTaggedChar", "decodeTaggedString", "inlineDescriptor", "Lkotlinx/serialization/encoding/Decoder;", "decodeTaggedInline", "Lkotlinx/serialization/json/Json;", "json", "Lkotlinx/serialization/json/Json;", "getJson", "()Lkotlinx/serialization/json/Json;", "value", "Lkotlinx/serialization/json/JsonElement;", "getValue", "()Lkotlinx/serialization/json/JsonElement;", "Lkotlinx/serialization/json/JsonConfiguration;", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "serializersModule", "<init>", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonElement;)V", "Lkotlinx/serialization/json/internal/JsonPrimitiveDecoder;", "Lkotlinx/serialization/json/internal/JsonTreeDecoder;", "Lkotlinx/serialization/json/internal/JsonTreeListDecoder;", "kotlinx-serialization-json"}, k = 1, mv = {1, 7, 1})
abstract class AbstractJsonTreeDecoder extends NamedValueDecoder implements JsonDecoder {

    @JvmField
    protected final JsonConfiguration configuration;
    private final Json json;
    private final JsonElement value;

    public /* synthetic */ AbstractJsonTreeDecoder(Json json, JsonElement jsonElement, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, jsonElement);
    }

    @Override // kotlinx.serialization.internal.NamedValueDecoder
    protected String composeName(String parentName, String childName) {
        Intrinsics.checkNotNullParameter(parentName, "parentName");
        Intrinsics.checkNotNullParameter(childName, "childName");
        return childName;
    }

    protected abstract JsonElement currentElement(String tag);

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public Json getJson() {
        return this.json;
    }

    public JsonElement getValue() {
        return this.value;
    }

    private AbstractJsonTreeDecoder(Json json, JsonElement jsonElement) {
        this.json = json;
        this.value = jsonElement;
        this.configuration = getJson().getConfiguration();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return getJson().getSerializersModule();
    }

    private final JsonElement currentObject() {
        JsonElement jsonElementCurrentElement;
        String currentTagOrNull = getCurrentTagOrNull();
        return (currentTagOrNull == null || (jsonElementCurrentElement = currentElement(currentTagOrNull)) == null) ? getValue() : jsonElementCurrentElement;
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public JsonElement decodeJsonElement() {
        return currentObject();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<T> deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return (T) PolymorphicKt.decodeSerializableValuePolymorphic(this, deserializer);
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        JsonElement jsonElementCurrentObject = currentObject();
        SerialKind kind = descriptor.getKind();
        if (Intrinsics.areEqual(kind, StructureKind.LIST.INSTANCE) ? true : kind instanceof PolymorphicKind) {
            Json json = getJson();
            if (jsonElementCurrentObject instanceof JsonArray) {
                return new JsonTreeListDecoder(json, (JsonArray) jsonElementCurrentObject);
            }
            throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonArray.class) + " as the serialized body of " + descriptor.getSerialName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentObject.getClass()));
        }
        if (!Intrinsics.areEqual(kind, StructureKind.MAP.INSTANCE)) {
            Json json2 = getJson();
            if (jsonElementCurrentObject instanceof JsonObject) {
                return new JsonTreeDecoder(json2, (JsonObject) jsonElementCurrentObject, null, null, 12, null);
            }
            throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class) + " as the serialized body of " + descriptor.getSerialName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentObject.getClass()));
        }
        Json json3 = getJson();
        SerialDescriptor serialDescriptorCarrierDescriptor = WriteModeKt.carrierDescriptor(descriptor.getElementDescriptor(0), json3.getSerializersModule());
        SerialKind kind2 = serialDescriptorCarrierDescriptor.getKind();
        if ((kind2 instanceof PrimitiveKind) || Intrinsics.areEqual(kind2, SerialKind.ENUM.INSTANCE)) {
            Json json4 = getJson();
            if (jsonElementCurrentObject instanceof JsonObject) {
                return new JsonTreeMapDecoder(json4, (JsonObject) jsonElementCurrentObject);
            }
            throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class) + " as the serialized body of " + descriptor.getSerialName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentObject.getClass()));
        }
        if (!json3.getConfiguration().getAllowStructuredMapKeys()) {
            throw JsonExceptionsKt.InvalidKeyKindException(serialDescriptorCarrierDescriptor);
        }
        Json json5 = getJson();
        if (jsonElementCurrentObject instanceof JsonArray) {
            return new JsonTreeListDecoder(json5, (JsonArray) jsonElementCurrentObject);
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonArray.class) + " as the serialized body of " + descriptor.getSerialName() + ", but had " + Reflection.getOrCreateKotlinClass(jsonElementCurrentObject.getClass()));
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return !(currentObject() instanceof JsonNull);
    }

    protected final JsonPrimitive getPrimitiveValue(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement jsonElementCurrentElement = currentElement(tag);
        JsonPrimitive jsonPrimitive = jsonElementCurrentElement instanceof JsonPrimitive ? (JsonPrimitive) jsonElementCurrentElement : null;
        if (jsonPrimitive != null) {
            return jsonPrimitive;
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected JsonPrimitive at " + tag + ", found " + jsonElementCurrentElement, currentObject().toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public int decodeTaggedEnum(String tag, SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        return JsonNamesMapKt.getJsonNameIndexOrThrow$default(enumDescriptor, getJson(), getPrimitiveValue(tag).getContent(), null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public boolean decodeTaggedNotNullMark(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return currentElement(tag) != JsonNull.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public boolean decodeTaggedBoolean(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonPrimitive primitiveValue = getPrimitiveValue(tag);
        if (!getJson().getConfiguration().getIsLenient() && asLiteral(primitiveValue, "boolean").getIsString()) {
            throw JsonExceptionsKt.JsonDecodingException(-1, "Boolean literal for key '" + tag + "' should be unquoted.\nUse 'isLenient = true' in 'Json {}` builder to accept non-compliant JSON.", currentObject().toString());
        }
        try {
            Boolean booleanOrNull = JsonElementKt.getBooleanOrNull(primitiveValue);
            if (booleanOrNull != null) {
                return booleanOrNull.booleanValue();
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("boolean");
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public byte decodeTaggedByte(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        try {
            int i = JsonElementKt.getInt(getPrimitiveValue(tag));
            boolean z = false;
            if (-128 <= i && i <= 127) {
                z = true;
            }
            Byte bValueOf = z ? Byte.valueOf((byte) i) : null;
            if (bValueOf == null) {
                unparsedPrimitive("byte");
                throw new KotlinNothingValueException();
            }
            return bValueOf.byteValue();
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("byte");
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public short decodeTaggedShort(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        try {
            int i = JsonElementKt.getInt(getPrimitiveValue(tag));
            boolean z = false;
            if (-32768 <= i && i <= 32767) {
                z = true;
            }
            Short shValueOf = z ? Short.valueOf((short) i) : null;
            if (shValueOf == null) {
                unparsedPrimitive("short");
                throw new KotlinNothingValueException();
            }
            return shValueOf.shortValue();
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("short");
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public int decodeTaggedInt(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        try {
            return JsonElementKt.getInt(getPrimitiveValue(tag));
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("int");
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public long decodeTaggedLong(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        try {
            return JsonElementKt.getLong(getPrimitiveValue(tag));
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("long");
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public float decodeTaggedFloat(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        try {
            float f = JsonElementKt.getFloat(getPrimitiveValue(tag));
            if (!getJson().getConfiguration().getAllowSpecialFloatingPointValues()) {
                if (!((Float.isInfinite(f) || Float.isNaN(f)) ? false : true)) {
                    throw JsonExceptionsKt.InvalidFloatingPointDecoded(Float.valueOf(f), tag, currentObject().toString());
                }
            }
            return f;
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("float");
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public double decodeTaggedDouble(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        try {
            double d = JsonElementKt.getDouble(getPrimitiveValue(tag));
            if (!getJson().getConfiguration().getAllowSpecialFloatingPointValues()) {
                if (!((Double.isInfinite(d) || Double.isNaN(d)) ? false : true)) {
                    throw JsonExceptionsKt.InvalidFloatingPointDecoded(Double.valueOf(d), tag, currentObject().toString());
                }
            }
            return d;
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("double");
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public char decodeTaggedChar(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        try {
            return StringsKt___StringsKt.single(getPrimitiveValue(tag).getContent());
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("char");
            throw new KotlinNothingValueException();
        }
    }

    private final Void unparsedPrimitive(String primitive) {
        throw JsonExceptionsKt.JsonDecodingException(-1, "Failed to parse '" + primitive + '\'', currentObject().toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public String decodeTaggedString(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonPrimitive primitiveValue = getPrimitiveValue(tag);
        if (!getJson().getConfiguration().getIsLenient() && !asLiteral(primitiveValue, "string").getIsString()) {
            throw JsonExceptionsKt.JsonDecodingException(-1, "String literal for key '" + tag + "' should be quoted.\nUse 'isLenient = true' in 'Json {}` builder to accept non-compliant JSON.", currentObject().toString());
        }
        if (primitiveValue instanceof JsonNull) {
            throw JsonExceptionsKt.JsonDecodingException(-1, "Unexpected 'null' value instead of string literal", currentObject().toString());
        }
        return primitiveValue.getContent();
    }

    private final JsonLiteral asLiteral(JsonPrimitive jsonPrimitive, String str) {
        JsonLiteral jsonLiteral = jsonPrimitive instanceof JsonLiteral ? (JsonLiteral) jsonPrimitive : null;
        if (jsonLiteral != null) {
            return jsonLiteral;
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Unexpected 'null' when " + str + " was expected");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public Decoder decodeTaggedInline(String tag, SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        return StreamingJsonEncoderKt.isUnsignedNumber(inlineDescriptor) ? new JsonDecoderForUnsignedTypes(new StringJsonLexer(getPrimitiveValue(tag).getContent()), getJson()) : super.decodeTaggedInline(tag, inlineDescriptor);
    }
}
