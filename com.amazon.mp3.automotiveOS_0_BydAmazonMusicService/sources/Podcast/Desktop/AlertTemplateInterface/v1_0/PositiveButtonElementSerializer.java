package Podcast.Desktop.AlertTemplateInterface.v1_0;

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
public class PositiveButtonElementSerializer extends JsonSerializer<PositiveButtonElement> {
    public static final PositiveButtonElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PositiveButtonElementSerializer positiveButtonElementSerializer = new PositiveButtonElementSerializer();
        INSTANCE = positiveButtonElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.AlertTemplateInterface.v1_0.PositiveButtonElementSerializer", new Version(1, 0, 0, null, null, null));
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
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(positiveButtonElement.getText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(positiveButtonElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
