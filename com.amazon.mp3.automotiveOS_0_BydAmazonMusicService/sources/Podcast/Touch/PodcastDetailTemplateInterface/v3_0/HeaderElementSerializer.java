package Podcast.Touch.PodcastDetailTemplateInterface.v3_0;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class HeaderElementSerializer extends JsonSerializer<HeaderElement> {
    public static final HeaderElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        HeaderElementSerializer headerElementSerializer = new HeaderElementSerializer();
        INSTANCE = headerElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v3_0.HeaderElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(HeaderElement.class, headerElementSerializer);
    }

    private HeaderElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull HeaderElement headerElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (headerElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(headerElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(HeaderElement headerElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(headerElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(headerElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(headerElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.LABEL);
        SimpleSerializers.serializeString(headerElement.getLabel(), jsonGenerator, serializerProvider);
    }
}
