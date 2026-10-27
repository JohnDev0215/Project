package Podcast.JumpBackInInterface.v1_0;

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
public class JumpBackInCacheItemElementSerializer extends JsonSerializer<JumpBackInCacheItemElement> {
    public static final JumpBackInCacheItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        JumpBackInCacheItemElementSerializer jumpBackInCacheItemElementSerializer = new JumpBackInCacheItemElementSerializer();
        INSTANCE = jumpBackInCacheItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.JumpBackInInterface.v1_0.JumpBackInCacheItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(JumpBackInCacheItemElement.class, jumpBackInCacheItemElementSerializer);
    }

    private JumpBackInCacheItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull JumpBackInCacheItemElement jumpBackInCacheItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (jumpBackInCacheItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(jumpBackInCacheItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(JumpBackInCacheItemElement jumpBackInCacheItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("podcastImage");
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getPodcastImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("availableUpsells");
        AvailableUpsellsSerializer.INSTANCE.serialize(jumpBackInCacheItemElement.getAvailableUpsells(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("totalDurationMilliseconds");
        SimpleSerializers.serializeLong(jumpBackInCacheItemElement.getTotalDurationMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackMode");
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getPlaybackMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("availabilityDate");
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getAvailabilityDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("publishDate");
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getPublishDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("showDeeplink");
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getShowDeeplink(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("seasonNumber");
        SimpleSerializers.serializeInteger(jumpBackInCacheItemElement.getSeasonNumber(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastEpisodeVariantId");
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getPodcastEpisodeVariantId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastTitle");
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getPodcastTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("contentTraits");
        ContentTraitsSerializer.INSTANCE.serialize(jumpBackInCacheItemElement.getContentTraits(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("episodeId");
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getEpisodeId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playDeeplink");
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getPlayDeeplink(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("authors");
        StringsSerializer.INSTANCE.serialize(jumpBackInCacheItemElement.getAuthors(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastShowVariantId");
        SimpleSerializers.serializeString(jumpBackInCacheItemElement.getPodcastShowVariantId(), jsonGenerator, serializerProvider);
    }
}
