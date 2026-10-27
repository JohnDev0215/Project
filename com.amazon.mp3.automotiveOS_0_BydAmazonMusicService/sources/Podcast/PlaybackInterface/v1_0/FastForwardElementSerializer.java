package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.MethodsSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class FastForwardElementSerializer extends JsonSerializer<FastForwardElement> {
    public static final FastForwardElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FastForwardElementSerializer fastForwardElementSerializer = new FastForwardElementSerializer();
        INSTANCE = fastForwardElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.FastForwardElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FastForwardElement.class, fastForwardElementSerializer);
    }

    private FastForwardElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FastForwardElement fastForwardElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (fastForwardElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(fastForwardElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FastForwardElement fastForwardElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(fastForwardElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
