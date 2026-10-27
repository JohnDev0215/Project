package com.neusoft.de.nts.OneCoreTest.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import org.apache.commons.codec.binary.Base64;

/* JADX INFO: loaded from: classes2.dex */
public class OneCoreJsonByteBufferAdapter implements JsonDeserializer<ByteBuffer>, JsonSerializer<ByteBuffer> {
    @Override // com.google.gson.JsonSerializer
    public JsonElement serialize(ByteBuffer byteBuffer, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(Base64.encodeBase64String(byteBuffer.array()));
    }

    @Override // com.google.gson.JsonDeserializer
    public ByteBuffer deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return ByteBuffer.wrap(Base64.decodeBase64(jsonElement.getAsString()));
    }
}
