package Podcast.Desktop.GalleryTemplateInterface.v1_0;

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
public class RegularBarkerElementSerializer extends JsonSerializer<RegularBarkerElement> {
    public static final RegularBarkerElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RegularBarkerElementSerializer regularBarkerElementSerializer = new RegularBarkerElementSerializer();
        INSTANCE = regularBarkerElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.RegularBarkerElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RegularBarkerElement.class, regularBarkerElementSerializer);
    }

    private RegularBarkerElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RegularBarkerElement regularBarkerElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (regularBarkerElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(regularBarkerElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RegularBarkerElement regularBarkerElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("backgroundImageLarge");
        SimpleSerializers.serializeString(regularBarkerElement.getBackgroundImageLarge(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(regularBarkerElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImageSmall");
        SimpleSerializers.serializeString(regularBarkerElement.getBackgroundImageSmall(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(regularBarkerElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(regularBarkerElement.getOnViewed(), jsonGenerator, serializerProvider);
    }
}
