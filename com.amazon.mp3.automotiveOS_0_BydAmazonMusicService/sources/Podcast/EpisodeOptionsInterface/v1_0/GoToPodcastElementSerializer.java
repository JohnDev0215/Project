package Podcast.EpisodeOptionsInterface.v1_0;

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
public class GoToPodcastElementSerializer extends JsonSerializer<GoToPodcastElement> {
    public static final GoToPodcastElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GoToPodcastElementSerializer goToPodcastElementSerializer = new GoToPodcastElementSerializer();
        INSTANCE = goToPodcastElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.EpisodeOptionsInterface.v1_0.GoToPodcastElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GoToPodcastElement.class, goToPodcastElementSerializer);
    }

    private GoToPodcastElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GoToPodcastElement goToPodcastElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (goToPodcastElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(goToPodcastElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GoToPodcastElement goToPodcastElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(goToPodcastElement.getOnItemSelected(), jsonGenerator, serializerProvider);
    }
}
