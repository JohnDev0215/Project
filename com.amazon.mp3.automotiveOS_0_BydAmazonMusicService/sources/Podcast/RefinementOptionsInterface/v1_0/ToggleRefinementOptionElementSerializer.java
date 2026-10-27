package Podcast.RefinementOptionsInterface.v1_0;

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
public class ToggleRefinementOptionElementSerializer extends JsonSerializer<ToggleRefinementOptionElement> {
    public static final ToggleRefinementOptionElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ToggleRefinementOptionElementSerializer toggleRefinementOptionElementSerializer = new ToggleRefinementOptionElementSerializer();
        INSTANCE = toggleRefinementOptionElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ToggleRefinementOptionElement.class, toggleRefinementOptionElementSerializer);
    }

    private ToggleRefinementOptionElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ToggleRefinementOptionElement toggleRefinementOptionElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (toggleRefinementOptionElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(toggleRefinementOptionElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ToggleRefinementOptionElement toggleRefinementOptionElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(toggleRefinementOptionElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(toggleRefinementOptionElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(toggleRefinementOptionElement.getText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("accessibilityLabel");
        SimpleSerializers.serializeString(toggleRefinementOptionElement.getAccessibilityLabel(), jsonGenerator, serializerProvider);
    }
}
