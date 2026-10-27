package Podcast.Touch.EpisodeRowItemsInterface.v1_0;

import SOACoreInterface.v1_0.MethodsSerializer;
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
public class LightUnborderedBadgeElementSerializer extends JsonSerializer<LightUnborderedBadgeElement> {
    public static final LightUnborderedBadgeElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LightUnborderedBadgeElementSerializer lightUnborderedBadgeElementSerializer = new LightUnborderedBadgeElementSerializer();
        INSTANCE = lightUnborderedBadgeElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.EpisodeRowItemsInterface.v1_0.LightUnborderedBadgeElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(LightUnborderedBadgeElement.class, lightUnborderedBadgeElementSerializer);
    }

    private LightUnborderedBadgeElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull LightUnborderedBadgeElement lightUnborderedBadgeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (lightUnborderedBadgeElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(lightUnborderedBadgeElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(LightUnborderedBadgeElement lightUnborderedBadgeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(lightUnborderedBadgeElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(lightUnborderedBadgeElement.getText(), jsonGenerator, serializerProvider);
    }
}
