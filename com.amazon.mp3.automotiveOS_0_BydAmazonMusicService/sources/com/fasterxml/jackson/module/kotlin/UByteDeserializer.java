package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KotlinDeserializers.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J%\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\nø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u000b"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/UByteDeserializer;", "Lcom/fasterxml/jackson/databind/deser/std/StdDeserializer;", "Lkotlin/UByte;", "()V", "deserialize", "p", "Lcom/fasterxml/jackson/core/JsonParser;", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "deserialize-w2LRezQ", "(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)B", "jackson-module-kotlin"}, k = 1, mv = {1, 4, 1})
public final class UByteDeserializer extends StdDeserializer<UByte> {
    public static final UByteDeserializer INSTANCE = new UByteDeserializer();

    private UByteDeserializer() {
        super((Class<?>) UByte.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return UByte.m2822boximpl(m2660deserializew2LRezQ(jsonParser, deserializationContext));
    }

    /* JADX INFO: renamed from: deserialize-w2LRezQ, reason: not valid java name */
    public byte m2660deserializew2LRezQ(JsonParser p, DeserializationContext ctxt) throws InputCoercionException {
        Intrinsics.checkNotNullParameter(p, "p");
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        UByte uByteAsUByte = UnsignedNumbersKt.asUByte(p.getShortValue());
        if (uByteAsUByte != null) {
            return uByteAsUByte.getData();
        }
        throw new InputCoercionException(p, "Numeric value (" + p.getText() + ") out of range of UByte (0 - " + UByte.m2871toStringimpl((byte) -1) + ").", JsonToken.VALUE_NUMBER_INT, UByte.class);
    }
}
