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
public class NegativeButtonElementSerializer extends JsonSerializer<NegativeButtonElement> {
    public static final NegativeButtonElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        NegativeButtonElementSerializer negativeButtonElementSerializer = new NegativeButtonElementSerializer();
        INSTANCE = negativeButtonElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.AlertTemplateInterface.v1_0.NegativeButtonElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(NegativeButtonElement.class, negativeButtonElementSerializer);
    }

    private NegativeButtonElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull NegativeButtonElement negativeButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (negativeButtonElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(negativeButtonElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(NegativeButtonElement negativeButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(negativeButtonElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(negativeButtonElement.getText(), jsonGenerator, serializerProvider);
    }
}
