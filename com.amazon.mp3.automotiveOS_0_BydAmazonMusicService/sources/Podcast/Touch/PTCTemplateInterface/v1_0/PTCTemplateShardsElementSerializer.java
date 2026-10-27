package Podcast.Touch.PTCTemplateInterface.v1_0;

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
public class PTCTemplateShardsElementSerializer extends JsonSerializer<PTCTemplateShardsElement> {
    public static final PTCTemplateShardsElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PTCTemplateShardsElementSerializer pTCTemplateShardsElementSerializer = new PTCTemplateShardsElementSerializer();
        INSTANCE = pTCTemplateShardsElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplateShardsElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PTCTemplateShardsElement.class, pTCTemplateShardsElementSerializer);
    }

    private PTCTemplateShardsElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PTCTemplateShardsElement pTCTemplateShardsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (pTCTemplateShardsElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(pTCTemplateShardsElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PTCTemplateShardsElement pTCTemplateShardsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        PTCTemplateShardElementsSerializer.INSTANCE.serialize(pTCTemplateShardsElement.getItems(), jsonGenerator, serializerProvider);
    }
}
