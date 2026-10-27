package Podcast.Touch.ChromeTemplateInterface.v1_0;

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
public class ChromeTemplateShardElementSerializer extends JsonSerializer<ChromeTemplateShardElement> {
    public static final ChromeTemplateShardElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ChromeTemplateShardElementSerializer chromeTemplateShardElementSerializer = new ChromeTemplateShardElementSerializer();
        INSTANCE = chromeTemplateShardElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplateShardElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ChromeTemplateShardElement.class, chromeTemplateShardElementSerializer);
    }

    private ChromeTemplateShardElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ChromeTemplateShardElement chromeTemplateShardElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (chromeTemplateShardElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(chromeTemplateShardElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ChromeTemplateShardElement chromeTemplateShardElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("shard");
        ShardSerializer.INSTANCE.serialize(chromeTemplateShardElement.getShard(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(chromeTemplateShardElement.getHeader(), jsonGenerator, serializerProvider);
    }
}
