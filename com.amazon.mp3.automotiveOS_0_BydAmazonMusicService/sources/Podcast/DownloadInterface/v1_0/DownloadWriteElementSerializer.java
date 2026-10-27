package Podcast.DownloadInterface.v1_0;

import Podcast.UpsellInterface.v1_0.AvailableUpsellsSerializer;
import Podcast.UpsellInterface.v1_0.ContentTraitsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class DownloadWriteElementSerializer extends JsonSerializer<DownloadWriteElement> {
    public static final DownloadWriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DownloadWriteElementSerializer downloadWriteElementSerializer = new DownloadWriteElementSerializer();
        INSTANCE = downloadWriteElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.DownloadInterface.v1_0.DownloadWriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DownloadWriteElement.class, downloadWriteElementSerializer);
    }

    private DownloadWriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DownloadWriteElement downloadWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (downloadWriteElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(downloadWriteElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DownloadWriteElement downloadWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("podcastTitle");
        SimpleSerializers.serializeString(downloadWriteElement.getPodcastTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("audioUri");
        SimpleSerializers.serializeString(downloadWriteElement.getAudioUri(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("availableUpsells");
        AvailableUpsellsSerializer.INSTANCE.serialize(downloadWriteElement.getAvailableUpsells(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackMode");
        SimpleSerializers.serializeString(downloadWriteElement.getPlaybackMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(downloadWriteElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastShowVariantId");
        SimpleSerializers.serializeString(downloadWriteElement.getPodcastShowVariantId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(downloadWriteElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastImage");
        SimpleSerializers.serializeString(downloadWriteElement.getPodcastImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("authors");
        StringsSerializer.INSTANCE.serialize(downloadWriteElement.getAuthors(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("contentTraits");
        ContentTraitsSerializer.INSTANCE.serialize(downloadWriteElement.getContentTraits(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(downloadWriteElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("seasonNumber");
        SimpleSerializers.serializeInteger(downloadWriteElement.getSeasonNumber(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("state");
        SimpleSerializers.serializeString(downloadWriteElement.getState(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastEpisodeVariantId");
        SimpleSerializers.serializeString(downloadWriteElement.getPodcastEpisodeVariantId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("availabilityDate");
        SimpleSerializers.serializeString(downloadWriteElement.getAvailabilityDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(downloadWriteElement.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("durationMilliseconds");
        SimpleSerializers.serializeLong(downloadWriteElement.getDurationMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(downloadWriteElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("publishDate");
        SimpleSerializers.serializeString(downloadWriteElement.getPublishDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("explicit");
        SimpleSerializers.serializePrimitiveBoolean(downloadWriteElement.isExplicit(), jsonGenerator, serializerProvider);
    }
}
