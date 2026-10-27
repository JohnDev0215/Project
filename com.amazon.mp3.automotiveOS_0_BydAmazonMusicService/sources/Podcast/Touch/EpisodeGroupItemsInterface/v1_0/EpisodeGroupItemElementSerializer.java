package Podcast.Touch.EpisodeGroupItemsInterface.v1_0;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElementsSerializer;
import com.amazon.music.find.utils.DeeplinksUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeGroupItemElementSerializer extends JsonSerializer<EpisodeGroupItemElement> {
    public static final EpisodeGroupItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeGroupItemElementSerializer episodeGroupItemElementSerializer = new EpisodeGroupItemElementSerializer();
        INSTANCE = episodeGroupItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.EpisodeGroupItemsInterface.v1_0.EpisodeGroupItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(EpisodeGroupItemElement.class, episodeGroupItemElementSerializer);
    }

    private EpisodeGroupItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull EpisodeGroupItemElement episodeGroupItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (episodeGroupItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(episodeGroupItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(EpisodeGroupItemElement episodeGroupItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("header");
        HeaderRowItemElementSerializer.INSTANCE.serialize(episodeGroupItemElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(DeeplinksUtils.PODCAST_EPISODES_PATH_SEGMENT);
        EpisodeRowItemElementsSerializer.INSTANCE.serialize(episodeGroupItemElement.getEpisodes(), jsonGenerator, serializerProvider);
    }
}
