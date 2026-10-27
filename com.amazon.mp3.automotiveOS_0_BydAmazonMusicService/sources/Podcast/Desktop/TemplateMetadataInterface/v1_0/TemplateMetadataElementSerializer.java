package Podcast.Desktop.TemplateMetadataInterface.v1_0;

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
public class TemplateMetadataElementSerializer extends JsonSerializer<TemplateMetadataElement> {
    public static final TemplateMetadataElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TemplateMetadataElementSerializer templateMetadataElementSerializer = new TemplateMetadataElementSerializer();
        INSTANCE = templateMetadataElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(TemplateMetadataElement.class, templateMetadataElementSerializer);
    }

    private TemplateMetadataElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull TemplateMetadataElement templateMetadataElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (templateMetadataElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(templateMetadataElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(TemplateMetadataElement templateMetadataElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(templateMetadataElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("deeplink");
        SimpleSerializers.serializeString(templateMetadataElement.getDeeplink(), jsonGenerator, serializerProvider);
    }
}
