package Podcast.Desktop.PodcastDetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.MethodsSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class AboutElementSerializer extends JsonSerializer<AboutElement> {
    public static final AboutElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AboutElementSerializer aboutElementSerializer = new AboutElementSerializer();
        INSTANCE = aboutElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.AboutElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AboutElement.class, aboutElementSerializer);
    }

    private AboutElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AboutElement aboutElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (aboutElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(aboutElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AboutElement aboutElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(aboutElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
