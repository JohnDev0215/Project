package Podcast.Touch.EpisodeRowItemsInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementSerializer;
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
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeRowItemElementSerializer extends JsonSerializer<EpisodeRowItemElement> {
    public static final EpisodeRowItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeRowItemElementSerializer episodeRowItemElementSerializer = new EpisodeRowItemElementSerializer();
        INSTANCE = episodeRowItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(EpisodeRowItemElement.class, episodeRowItemElementSerializer);
    }

    private EpisodeRowItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull EpisodeRowItemElement episodeRowItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (episodeRowItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(episodeRowItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(EpisodeRowItemElement episodeRowItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("download");
        DownloadElementSerializer.INSTANCE.serialize(episodeRowItemElement.getDownload(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(episodeRowItemElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("episodeOptions");
        EpisodeOptionsElementSerializer.INSTANCE.serialize(episodeRowItemElement.getEpisodeOptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.LABEL);
        SimpleSerializers.serializeString(episodeRowItemElement.getLabel(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onResumeSelected");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(episodeRowItemElement.getOnResumeSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.TERTIARY_TEXT);
        SimpleSerializers.serializeString(episodeRowItemElement.getTertiaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("descriptionBadge");
        BadgeElementSerializer badgeElementSerializer = BadgeElementSerializer.INSTANCE;
        badgeElementSerializer.serialize(episodeRowItemElement.getDescriptionBadge(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackMode");
        SimpleSerializers.serializeString(episodeRowItemElement.getPlaybackMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPlaySelected");
        jsonSerializer.serialize(episodeRowItemElement.getOnPlaySelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("imageBadge");
        badgeElementSerializer.serialize(episodeRowItemElement.getImageBadge(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("button");
        ButtonElementSerializer.INSTANCE.serialize(episodeRowItemElement.getButton(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("bookmark");
        BookmarkElementSerializer.INSTANCE.serialize(episodeRowItemElement.getBookmark(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPauseSelected");
        jsonSerializer.serialize(episodeRowItemElement.getOnPauseSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("completed");
        CompletedElementSerializer.INSTANCE.serialize(episodeRowItemElement.getCompleted(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(episodeRowItemElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(episodeRowItemElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        jsonSerializer.serialize(episodeRowItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("mediaId");
        SimpleSerializers.serializeString(episodeRowItemElement.getMediaId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(episodeRowItemElement.getImage(), jsonGenerator, serializerProvider);
    }
}
