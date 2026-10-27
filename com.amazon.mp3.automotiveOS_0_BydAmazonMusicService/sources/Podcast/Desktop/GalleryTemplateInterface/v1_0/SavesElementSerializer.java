package Podcast.Desktop.GalleryTemplateInterface.v1_0;

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
public class SavesElementSerializer extends JsonSerializer<SavesElement> {
    public static final SavesElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SavesElementSerializer savesElementSerializer = new SavesElementSerializer();
        INSTANCE = savesElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.SavesElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SavesElement.class, savesElementSerializer);
    }

    private SavesElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SavesElement savesElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (savesElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(savesElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SavesElement savesElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("seeMoreText");
        SimpleSerializers.serializeString(savesElement.getSeeMoreText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(savesElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        MethodsSerializer.INSTANCE.serialize(savesElement.getOnViewed(), jsonGenerator, serializerProvider);
    }
}
