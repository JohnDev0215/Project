package Podcast.Touch.VerticalSeeMoreItemElementInterface.v1_0;

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
public class VerticalSeeMoreItemElementSerializer extends JsonSerializer<VerticalSeeMoreItemElement> {
    public static final VerticalSeeMoreItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        VerticalSeeMoreItemElementSerializer verticalSeeMoreItemElementSerializer = new VerticalSeeMoreItemElementSerializer();
        INSTANCE = verticalSeeMoreItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.VerticalSeeMoreItemElementInterface.v1_0.VerticalSeeMoreItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(VerticalSeeMoreItemElement.class, verticalSeeMoreItemElementSerializer);
    }

    private VerticalSeeMoreItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull VerticalSeeMoreItemElement verticalSeeMoreItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (verticalSeeMoreItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(verticalSeeMoreItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(VerticalSeeMoreItemElement verticalSeeMoreItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(verticalSeeMoreItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(verticalSeeMoreItemElement.getText(), jsonGenerator, serializerProvider);
    }
}
