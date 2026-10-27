package Podcast.SaveInterface.v1_0;

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
public class SaveMetadataWriteElementSerializer extends JsonSerializer<SaveMetadataWriteElement> {
    public static final SaveMetadataWriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SaveMetadataWriteElementSerializer saveMetadataWriteElementSerializer = new SaveMetadataWriteElementSerializer();
        INSTANCE = saveMetadataWriteElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.SaveInterface.v1_0.SaveMetadataWriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SaveMetadataWriteElement.class, saveMetadataWriteElementSerializer);
    }

    private SaveMetadataWriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SaveMetadataWriteElement saveMetadataWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (saveMetadataWriteElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(saveMetadataWriteElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SaveMetadataWriteElement saveMetadataWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(saveMetadataWriteElement.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(saveMetadataWriteElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("authors");
        StringsSerializer.INSTANCE.serialize(saveMetadataWriteElement.getAuthors(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(saveMetadataWriteElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("availableUpsells");
        AvailableUpsellsSerializer.INSTANCE.serialize(saveMetadataWriteElement.getAvailableUpsells(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(saveMetadataWriteElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastEpisodeVariantId");
        SimpleSerializers.serializeString(saveMetadataWriteElement.getPodcastEpisodeVariantId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("publishDate");
        SimpleSerializers.serializeString(saveMetadataWriteElement.getPublishDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("seasonNumber");
        SimpleSerializers.serializeInteger(saveMetadataWriteElement.getSeasonNumber(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackMode");
        SimpleSerializers.serializeString(saveMetadataWriteElement.getPlaybackMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("availabilityDate");
        SimpleSerializers.serializeString(saveMetadataWriteElement.getAvailabilityDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastTitle");
        SimpleSerializers.serializeString(saveMetadataWriteElement.getPodcastTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastImage");
        SimpleSerializers.serializeString(saveMetadataWriteElement.getPodcastImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("contentTraits");
        ContentTraitsSerializer.INSTANCE.serialize(saveMetadataWriteElement.getContentTraits(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastShowVariantId");
        SimpleSerializers.serializeString(saveMetadataWriteElement.getPodcastShowVariantId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(saveMetadataWriteElement.getId(), jsonGenerator, serializerProvider);
    }
}
