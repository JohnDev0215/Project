package Podcast.EpisodeOptionsInterface.v1_0;

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
public class EpisodeOptionsElementSerializer extends JsonSerializer<EpisodeOptionsElement> {
    public static final EpisodeOptionsElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeOptionsElementSerializer episodeOptionsElementSerializer = new EpisodeOptionsElementSerializer();
        INSTANCE = episodeOptionsElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(EpisodeOptionsElement.class, episodeOptionsElementSerializer);
    }

    private EpisodeOptionsElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull EpisodeOptionsElement episodeOptionsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (episodeOptionsElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(episodeOptionsElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(EpisodeOptionsElement episodeOptionsElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("share");
        ShareElementSerializer.INSTANCE.serialize(episodeOptionsElement.getShare(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playEpisode");
        PlayEpisodeElementSerializer.INSTANCE.serialize(episodeOptionsElement.getPlayEpisode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("follow");
        FollowElementSerializer.INSTANCE.serialize(episodeOptionsElement.getFollow(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("subTitle");
        SimpleSerializers.serializeString(episodeOptionsElement.getSubTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("goToPodcast");
        GoToPodcastElementSerializer.INSTANCE.serialize(episodeOptionsElement.getGoToPodcast(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(episodeOptionsElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("download");
        DownloadElementSerializer.INSTANCE.serialize(episodeOptionsElement.getDownload(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(episodeOptionsElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("carPreset");
        CarPresetElementSerializer.INSTANCE.serialize(episodeOptionsElement.getCarPreset(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEpisodeOptionsShow");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(episodeOptionsElement.getOnEpisodeOptionsShow(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("completed");
        CompletedElementSerializer.INSTANCE.serialize(episodeOptionsElement.getCompleted(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onEpisodeOptionsDismiss");
        jsonSerializer.serialize(episodeOptionsElement.getOnEpisodeOptionsDismiss(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("save");
        SaveElementSerializer.INSTANCE.serialize(episodeOptionsElement.getSave(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("report");
        ReportElementSerializer.INSTANCE.serialize(episodeOptionsElement.getReport(), jsonGenerator, serializerProvider);
    }
}
