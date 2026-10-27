package Podcast.Touch.DetailTemplateInterface.v1_0;

import SOATemplateListInterface.v1_0.ShardSerializer;
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
public class DetailTemplateShardElementSerializer extends JsonSerializer<DetailTemplateShardElement> {
    public static final DetailTemplateShardElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DetailTemplateShardElementSerializer detailTemplateShardElementSerializer = new DetailTemplateShardElementSerializer();
        INSTANCE = detailTemplateShardElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateShardElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DetailTemplateShardElement.class, detailTemplateShardElementSerializer);
    }

    private DetailTemplateShardElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DetailTemplateShardElement detailTemplateShardElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (detailTemplateShardElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(detailTemplateShardElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DetailTemplateShardElement detailTemplateShardElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(detailTemplateShardElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("shard");
        ShardSerializer.INSTANCE.serialize(detailTemplateShardElement.getShard(), jsonGenerator, serializerProvider);
    }
}
