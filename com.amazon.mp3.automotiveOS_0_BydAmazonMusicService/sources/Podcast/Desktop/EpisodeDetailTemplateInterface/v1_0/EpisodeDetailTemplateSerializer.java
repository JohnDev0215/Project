package Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0;

import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElementSerializer;
import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementSerializer;
import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementSerializer;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeDetailTemplateSerializer extends JsonSerializer<EpisodeDetailTemplate> {
    public static final EpisodeDetailTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeDetailTemplateSerializer episodeDetailTemplateSerializer = new EpisodeDetailTemplateSerializer();
        INSTANCE = episodeDetailTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.EpisodeDetailTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(EpisodeDetailTemplate.class, episodeDetailTemplateSerializer);
    }

    private EpisodeDetailTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull EpisodeDetailTemplate episodeDetailTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (episodeDetailTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(episodeDetailTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(EpisodeDetailTemplate episodeDetailTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(episodeDetailTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(episodeDetailTemplate.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(episodeDetailTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("bookmark");
        BookmarkElementSerializer.INSTANCE.serialize(episodeDetailTemplate.getBookmark(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(episodeDetailTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("download");
        DownloadElementSerializer.INSTANCE.serialize(episodeDetailTemplate.getDownload(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(episodeDetailTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(episodeDetailTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("save");
        SaveElementSerializer.INSTANCE.serialize(episodeDetailTemplate.getSave(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        HeaderElementSerializer.INSTANCE.serialize(episodeDetailTemplate.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metadata");
        TemplateMetadataElementSerializer.INSTANCE.serialize(episodeDetailTemplate.getMetadata(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImage");
        SimpleSerializers.serializeString(episodeDetailTemplate.getBackgroundImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(episodeDetailTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("share");
        ShareElementSerializer.INSTANCE.serialize(episodeDetailTemplate.getShare(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("episodeOptions");
        EpisodeOptionsElementSerializer.INSTANCE.serialize(episodeDetailTemplate.getEpisodeOptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(episodeDetailTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(episodeDetailTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
