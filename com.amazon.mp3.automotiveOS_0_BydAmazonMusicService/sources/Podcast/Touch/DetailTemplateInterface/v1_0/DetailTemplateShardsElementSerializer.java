package Podcast.Touch.DetailTemplateInterface.v1_0;

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
public class DetailTemplateShardsElementSerializer extends JsonSerializer<DetailTemplateShardsElement> {
    public static final DetailTemplateShardsElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DetailTemplateShardsElementSerializer detailTemplateShardsElementSerializer = new DetailTemplateShardsElementSerializer();
        INSTANCE = detailTemplateShardsElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateShardsElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DetailTemplateShardsElement.class, detailTemplateShardsElementSerializer);
    }

    private DetailTemplateShardsElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DetailTemplateShardsElement detailTemplateShardsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (detailTemplateShardsElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(detailTemplateShardsElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DetailTemplateShardsElement detailTemplateShardsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.ITEMS);
        DetailTemplateShardElementsSerializer.INSTANCE.serialize(detailTemplateShardsElement.getItems(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("selectedShardPosition");
        SimpleSerializers.serializePrimitiveInt(detailTemplateShardsElement.getSelectedShardPosition(), jsonGenerator, serializerProvider);
    }
}
