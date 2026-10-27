package Podcast.PlaybackMetricsInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackMetricWriteElementSerializer extends JsonSerializer<PlaybackMetricWriteElement> {
    public static final PlaybackMetricWriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlaybackMetricWriteElementSerializer playbackMetricWriteElementSerializer = new PlaybackMetricWriteElementSerializer();
        INSTANCE = playbackMetricWriteElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricWriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PlaybackMetricWriteElement.class, playbackMetricWriteElementSerializer);
    }

    private PlaybackMetricWriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PlaybackMetricWriteElement playbackMetricWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (playbackMetricWriteElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(playbackMetricWriteElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PlaybackMetricWriteElement playbackMetricWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("mediaId");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getMediaId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackInstanceId");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getPlaybackInstanceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastEpisodeVariantId");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getPodcastEpisodeVariantId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackSignalType");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getPlaybackSignalType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastShowVariantId");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getPodcastShowVariantId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("initialPlaybackStartDelayMilliseconds");
        SimpleSerializers.serializeLong(playbackMetricWriteElement.getInitialPlaybackStartDelayMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isMediaDownloaded");
        SimpleSerializers.serializePrimitiveBoolean(playbackMetricWriteElement.isIsMediaDownloaded(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("mediaSubTitle");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getMediaSubTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("mediaTitle");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getMediaTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("mediaCollectionType");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getMediaCollectionType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackRequestedAtTimestampMilliseconds");
        SimpleSerializers.serializeLong(playbackMetricWriteElement.getPlaybackRequestedAtTimestampMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("rebufferCount");
        SimpleSerializers.serializeInteger(playbackMetricWriteElement.getRebufferCount(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("rebufferDurationMilliseconds");
        SimpleSerializers.serializeLong(playbackMetricWriteElement.getRebufferDurationMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("currentPlaybackSpeed");
        SimpleSerializers.serializeFloat(playbackMetricWriteElement.getCurrentPlaybackSpeed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("audioUri");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getAudioUri(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("currentProgressMilliseconds");
        SimpleSerializers.serializeLong(playbackMetricWriteElement.getCurrentProgressMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackStartOffsetMilliseconds");
        SimpleSerializers.serializeLong(playbackMetricWriteElement.getPlaybackStartOffsetMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackStartedAtTimestampMilliseconds");
        SimpleSerializers.serializeLong(playbackMetricWriteElement.getPlaybackStartedAtTimestampMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("mediaCollectionId");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getMediaCollectionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("errorException");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getErrorException(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getPageType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metricsPreset");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getMetricsPreset(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackEngineType");
        SimpleSerializers.serializeString(playbackMetricWriteElement.getPlaybackEngineType(), jsonGenerator, serializerProvider);
    }
}
