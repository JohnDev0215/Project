package Podcast.Touch.PodcastDetailTemplateInterface.v1_0;

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
public class EpisodeItemElementSerializer extends JsonSerializer<EpisodeItemElement> {
    public static final EpisodeItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeItemElementSerializer episodeItemElementSerializer = new EpisodeItemElementSerializer();
        INSTANCE = episodeItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v1_0.EpisodeItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(EpisodeItemElement.class, episodeItemElementSerializer);
    }

    private EpisodeItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull EpisodeItemElement episodeItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (episodeItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(episodeItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(EpisodeItemElement episodeItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(episodeItemElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.LABEL);
        SimpleSerializers.serializeString(episodeItemElement.getLabel(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onImageSelected");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(episodeItemElement.getOnImageSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(episodeItemElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("download");
        DownloadElementSerializer.INSTANCE.serialize(episodeItemElement.getDownload(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(episodeItemElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(episodeItemElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        jsonSerializer.serialize(episodeItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isDisabled");
        SimpleSerializers.serializeBoolean(episodeItemElement.isIsDisabled(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("bookmark");
        BookmarkElementSerializer.INSTANCE.serialize(episodeItemElement.getBookmark(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.TERTIARY_TEXT);
        SimpleSerializers.serializeString(episodeItemElement.getTertiaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("episodeOptions");
        EpisodeOptionsElementSerializer.INSTANCE.serialize(episodeItemElement.getEpisodeOptions(), jsonGenerator, serializerProvider);
    }
}
