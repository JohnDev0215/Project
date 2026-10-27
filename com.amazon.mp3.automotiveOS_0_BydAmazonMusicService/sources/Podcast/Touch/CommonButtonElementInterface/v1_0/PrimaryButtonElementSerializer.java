package Podcast.Touch.CommonButtonElementInterface.v1_0;

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
public class PrimaryButtonElementSerializer extends JsonSerializer<PrimaryButtonElement> {
    public static final PrimaryButtonElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PrimaryButtonElementSerializer primaryButtonElementSerializer = new PrimaryButtonElementSerializer();
        INSTANCE = primaryButtonElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CommonButtonElementInterface.v1_0.PrimaryButtonElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PrimaryButtonElement.class, primaryButtonElementSerializer);
    }

    private PrimaryButtonElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PrimaryButtonElement primaryButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (primaryButtonElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(primaryButtonElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PrimaryButtonElement primaryButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(primaryButtonElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(primaryButtonElement.getText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("iconEnabled");
        SimpleSerializers.serializePrimitiveBoolean(primaryButtonElement.isIconEnabled(), jsonGenerator, serializerProvider);
    }
}
