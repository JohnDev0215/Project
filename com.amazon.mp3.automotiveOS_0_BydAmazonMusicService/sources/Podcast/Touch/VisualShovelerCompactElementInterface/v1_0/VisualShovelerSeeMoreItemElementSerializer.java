package Podcast.Touch.VisualShovelerCompactElementInterface.v1_0;

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
public class VisualShovelerSeeMoreItemElementSerializer extends JsonSerializer<VisualShovelerSeeMoreItemElement> {
    public static final VisualShovelerSeeMoreItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        VisualShovelerSeeMoreItemElementSerializer visualShovelerSeeMoreItemElementSerializer = new VisualShovelerSeeMoreItemElementSerializer();
        INSTANCE = visualShovelerSeeMoreItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerSeeMoreItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(VisualShovelerSeeMoreItemElement.class, visualShovelerSeeMoreItemElementSerializer);
    }

    private VisualShovelerSeeMoreItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull VisualShovelerSeeMoreItemElement visualShovelerSeeMoreItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (visualShovelerSeeMoreItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(visualShovelerSeeMoreItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(VisualShovelerSeeMoreItemElement visualShovelerSeeMoreItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(visualShovelerSeeMoreItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(visualShovelerSeeMoreItemElement.getText(), jsonGenerator, serializerProvider);
    }
}
