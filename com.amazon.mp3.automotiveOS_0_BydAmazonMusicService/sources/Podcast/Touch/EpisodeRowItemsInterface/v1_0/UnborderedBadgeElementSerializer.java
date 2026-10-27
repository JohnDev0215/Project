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
public class UnborderedBadgeElementSerializer extends JsonSerializer<UnborderedBadgeElement> {
    public static final UnborderedBadgeElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        UnborderedBadgeElementSerializer unborderedBadgeElementSerializer = new UnborderedBadgeElementSerializer();
        INSTANCE = unborderedBadgeElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.EpisodeRowItemsInterface.v1_0.UnborderedBadgeElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(UnborderedBadgeElement.class, unborderedBadgeElementSerializer);
    }

    private UnborderedBadgeElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull UnborderedBadgeElement unborderedBadgeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (unborderedBadgeElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(unborderedBadgeElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(UnborderedBadgeElement unborderedBadgeElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(unborderedBadgeElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(unborderedBadgeElement.getText(), jsonGenerator, serializerProvider);
    }
}
