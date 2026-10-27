package Podcast.Touch.RegularBarkerElementInterface.v1_0;

import Podcast.Touch.BarkerItemElementInterface.v1_0.BarkerItemElementSerializer;
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
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.RegularBarkerElementInterface.v1_0.RegularBarkerElementSerializer", new Version(1, 0, 0, null, null, null));
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
        jsonGenerator.writeFieldName("barker");
        BarkerItemElementSerializer.INSTANCE.serialize(regularBarkerElement.getBarker(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(regularBarkerElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(regularBarkerElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onContentFirstViewed");
        jsonSerializer.serialize(regularBarkerElement.getOnContentFirstViewed(), jsonGenerator, serializerProvider);
    }
}
