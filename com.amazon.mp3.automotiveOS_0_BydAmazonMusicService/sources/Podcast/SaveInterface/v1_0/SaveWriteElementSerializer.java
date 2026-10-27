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
public class SaveWriteElementSerializer extends JsonSerializer<SaveWriteElement> {
    public static final SaveWriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SaveWriteElementSerializer saveWriteElementSerializer = new SaveWriteElementSerializer();
        INSTANCE = saveWriteElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.SaveInterface.v1_0.SaveWriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SaveWriteElement.class, saveWriteElementSerializer);
    }

    private SaveWriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SaveWriteElement saveWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (saveWriteElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(saveWriteElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SaveWriteElement saveWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("podcastShowVariantId");
        SimpleSerializers.serializeString(saveWriteElement.getPodcastShowVariantId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("saved");
        SimpleSerializers.serializePrimitiveBoolean(saveWriteElement.isSaved(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(saveWriteElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("contentTraits");
        ContentTraitsSerializer.INSTANCE.serialize(saveWriteElement.getContentTraits(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("seasonNumber");
        SimpleSerializers.serializeInteger(saveWriteElement.getSeasonNumber(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastEpisodeVariantId");
        SimpleSerializers.serializeString(saveWriteElement.getPodcastEpisodeVariantId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(saveWriteElement.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("publishDate");
        SimpleSerializers.serializeString(saveWriteElement.getPublishDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("totalDurationMilliseconds");
        SimpleSerializers.serializeLong(saveWriteElement.getTotalDurationMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("availabilityDate");
        SimpleSerializers.serializeString(saveWriteElement.getAvailabilityDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("availableUpsells");
        AvailableUpsellsSerializer.INSTANCE.serialize(saveWriteElement.getAvailableUpsells(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastImage");
        SimpleSerializers.serializeString(saveWriteElement.getPodcastImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackMode");
        SimpleSerializers.serializeString(saveWriteElement.getPlaybackMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(saveWriteElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("authors");
        StringsSerializer.INSTANCE.serialize(saveWriteElement.getAuthors(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(saveWriteElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastTitle");
        SimpleSerializers.serializeString(saveWriteElement.getPodcastTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(saveWriteElement.getTitle(), jsonGenerator, serializerProvider);
    }
}
