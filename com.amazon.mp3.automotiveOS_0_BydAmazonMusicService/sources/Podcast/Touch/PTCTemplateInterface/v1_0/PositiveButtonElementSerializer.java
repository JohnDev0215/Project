package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class PositiveButtonElementSerializer extends JsonSerializer<PositiveButtonElement> {
    public static final PositiveButtonElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PositiveButtonElementSerializer positiveButtonElementSerializer = new PositiveButtonElementSerializer();
        INSTANCE = positiveButtonElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.PositiveButtonElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PositiveButtonElement.class, positiveButtonElementSerializer);
    }

    private PositiveButtonElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PositiveButtonElement positiveButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (positiveButtonElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(positiveButtonElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PositiveButtonElement positiveButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("deselectedText");
        SimpleSerializers.serializeString(positiveButtonElement.getDeselectedText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(positiveButtonElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("selectedText");
        SimpleSerializers.serializeString(positiveButtonElement.getSelectedText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemDeselected");
        jsonSerializer.serialize(positiveButtonElement.getOnItemDeselected(), jsonGenerator, serializerProvider);
    }
}
