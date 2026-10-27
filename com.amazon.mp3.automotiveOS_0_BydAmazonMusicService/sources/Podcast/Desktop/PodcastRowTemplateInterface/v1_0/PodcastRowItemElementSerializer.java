package Podcast.Desktop.PodcastRowTemplateInterface.v1_0;

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
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class PodcastRowItemElementSerializer extends JsonSerializer<PodcastRowItemElement> {
    public static final PodcastRowItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PodcastRowItemElementSerializer podcastRowItemElementSerializer = new PodcastRowItemElementSerializer();
        INSTANCE = podcastRowItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.PodcastRowTemplateInterface.v1_0.PodcastRowItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PodcastRowItemElement.class, podcastRowItemElementSerializer);
    }

    private PodcastRowItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PodcastRowItemElement podcastRowItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (podcastRowItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(podcastRowItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PodcastRowItemElement podcastRowItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(podcastRowItemElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(podcastRowItemElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(podcastRowItemElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(podcastRowItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("follow");
        FollowElementSerializer.INSTANCE.serialize(podcastRowItemElement.getFollow(), jsonGenerator, serializerProvider);
    }
}
