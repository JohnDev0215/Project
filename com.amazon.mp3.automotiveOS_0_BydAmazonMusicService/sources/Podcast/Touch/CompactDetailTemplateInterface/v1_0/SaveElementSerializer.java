package Podcast.Touch.CompactDetailTemplateInterface.v1_0;

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
public class SaveElementSerializer extends JsonSerializer<SaveElement> {
    public static final SaveElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SaveElementSerializer saveElementSerializer = new SaveElementSerializer();
        INSTANCE = saveElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CompactDetailTemplateInterface.v1_0.SaveElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SaveElement.class, saveElementSerializer);
    }

    private SaveElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SaveElement saveElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (saveElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(saveElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SaveElement saveElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(saveElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onUnsave");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(saveElement.getOnUnsave(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onSave");
        jsonSerializer.serialize(saveElement.getOnSave(), jsonGenerator, serializerProvider);
    }
}
