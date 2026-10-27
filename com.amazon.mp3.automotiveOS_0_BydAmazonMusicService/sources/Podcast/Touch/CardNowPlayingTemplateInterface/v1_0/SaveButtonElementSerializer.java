package Podcast.Touch.CardNowPlayingTemplateInterface.v1_0;

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
public class SaveButtonElementSerializer extends JsonSerializer<SaveButtonElement> {
    public static final SaveButtonElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SaveButtonElementSerializer saveButtonElementSerializer = new SaveButtonElementSerializer();
        INSTANCE = saveButtonElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.SaveButtonElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SaveButtonElement.class, saveButtonElementSerializer);
    }

    private SaveButtonElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SaveButtonElement saveButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (saveButtonElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(saveButtonElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SaveButtonElement saveButtonElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("saveText");
        SimpleSerializers.serializeString(saveButtonElement.getSaveText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(saveButtonElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("unsaveText");
        SimpleSerializers.serializeString(saveButtonElement.getUnsaveText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onUnsave");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(saveButtonElement.getOnUnsave(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onSave");
        jsonSerializer.serialize(saveButtonElement.getOnSave(), jsonGenerator, serializerProvider);
    }
}
