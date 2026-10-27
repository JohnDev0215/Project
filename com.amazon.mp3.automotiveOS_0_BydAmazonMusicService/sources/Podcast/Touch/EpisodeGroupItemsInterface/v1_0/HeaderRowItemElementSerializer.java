package Podcast.Touch.EpisodeGroupItemsInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class HeaderRowItemElementSerializer extends JsonSerializer<HeaderRowItemElement> {
    public static final HeaderRowItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        HeaderRowItemElementSerializer headerRowItemElementSerializer = new HeaderRowItemElementSerializer();
        INSTANCE = headerRowItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.EpisodeGroupItemsInterface.v1_0.HeaderRowItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(HeaderRowItemElement.class, headerRowItemElementSerializer);
    }

    private HeaderRowItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull HeaderRowItemElement headerRowItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (headerRowItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(headerRowItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(HeaderRowItemElement headerRowItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("headerTitle");
        SimpleSerializers.serializeString(headerRowItemElement.getHeaderTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("headerType");
        SimpleSerializers.serializeString(headerRowItemElement.getHeaderType(), jsonGenerator, serializerProvider);
    }
}
