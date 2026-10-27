package Podcast.Touch.CompactDetailTemplateInterface.v1_0;

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
public class CompactDetailTemplateSerializer extends JsonSerializer<CompactDetailTemplate> {
    public static final CompactDetailTemplateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CompactDetailTemplateSerializer compactDetailTemplateSerializer = new CompactDetailTemplateSerializer();
        INSTANCE = compactDetailTemplateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CompactDetailTemplateInterface.v1_0.CompactDetailTemplateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CompactDetailTemplate.class, compactDetailTemplateSerializer);
    }

    private CompactDetailTemplateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CompactDetailTemplate compactDetailTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (compactDetailTemplate == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(compactDetailTemplate, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CompactDetailTemplate compactDetailTemplate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("latencyMetricElement");
        LatencyMetricElementSerializer.INSTANCE.serialize(compactDetailTemplate.getLatencyMetricElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(compactDetailTemplate.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackMode");
        SimpleSerializers.serializeString(compactDetailTemplate.getPlaybackMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("share");
        ShareElementSerializer.INSTANCE.serialize(compactDetailTemplate.getShare(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("button");
        ButtonElementSerializer.INSTANCE.serialize(compactDetailTemplate.getButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("bookmark");
        BookmarkElementSerializer.INSTANCE.serialize(compactDetailTemplate.getBookmark(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onCreated");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(compactDetailTemplate.getOnCreated(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(compactDetailTemplate.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("badge");
        BadgeElementSerializer.INSTANCE.serialize(compactDetailTemplate.getBadge(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(compactDetailTemplate.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onBound");
        jsonSerializer.serialize(compactDetailTemplate.getOnBound(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("download");
        DownloadElementSerializer.INSTANCE.serialize(compactDetailTemplate.getDownload(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("episodeOptions");
        EpisodeOptionsElementSerializer.INSTANCE.serialize(compactDetailTemplate.getEpisodeOptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("cacheSeconds");
        SimpleSerializers.serializeLong(compactDetailTemplate.getCacheSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(compactDetailTemplate.getText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("save");
        SaveElementSerializer.INSTANCE.serialize(compactDetailTemplate.getSave(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("launchMode");
        SimpleSerializers.serializeString(compactDetailTemplate.getLaunchMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        HeaderElementSerializer.INSTANCE.serialize(compactDetailTemplate.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("backgroundImage");
        SimpleSerializers.serializeString(compactDetailTemplate.getBackgroundImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismissed");
        jsonSerializer.serialize(compactDetailTemplate.getOnDismissed(), jsonGenerator, serializerProvider);
    }
}
