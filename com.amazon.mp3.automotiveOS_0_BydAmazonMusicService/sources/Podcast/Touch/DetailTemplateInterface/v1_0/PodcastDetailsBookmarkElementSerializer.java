package Podcast.Touch.DetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class PodcastDetailsBookmarkElementSerializer extends JsonSerializer<PodcastDetailsBookmarkElement> {
    public static final PodcastDetailsBookmarkElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PodcastDetailsBookmarkElementSerializer podcastDetailsBookmarkElementSerializer = new PodcastDetailsBookmarkElementSerializer();
        INSTANCE = podcastDetailsBookmarkElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.DetailTemplateInterface.v1_0.PodcastDetailsBookmarkElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PodcastDetailsBookmarkElement.class, podcastDetailsBookmarkElementSerializer);
    }

    private PodcastDetailsBookmarkElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PodcastDetailsBookmarkElement podcastDetailsBookmarkElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (podcastDetailsBookmarkElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(podcastDetailsBookmarkElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PodcastDetailsBookmarkElement podcastDetailsBookmarkElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onResume");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(podcastDetailsBookmarkElement.getOnResume(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.LABEL);
        SimpleSerializers.serializeString(podcastDetailsBookmarkElement.getLabel(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(podcastDetailsBookmarkElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPause");
        jsonSerializer.serialize(podcastDetailsBookmarkElement.getOnPause(), jsonGenerator, serializerProvider);
    }
}
