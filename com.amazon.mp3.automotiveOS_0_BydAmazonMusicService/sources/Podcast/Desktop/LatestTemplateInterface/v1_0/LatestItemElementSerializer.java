package Podcast.Desktop.LatestTemplateInterface.v1_0;

import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElementSerializer;
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
public class LatestItemElementSerializer extends JsonSerializer<LatestItemElement> {
    public static final LatestItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LatestItemElementSerializer latestItemElementSerializer = new LatestItemElementSerializer();
        INSTANCE = latestItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.LatestTemplateInterface.v1_0.LatestItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(LatestItemElement.class, latestItemElementSerializer);
    }

    private LatestItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull LatestItemElement latestItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (latestItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(latestItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(LatestItemElement latestItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("episodeOptions");
        EpisodeOptionsElementSerializer.INSTANCE.serialize(latestItemElement.getEpisodeOptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("download");
        DownloadElementSerializer.INSTANCE.serialize(latestItemElement.getDownload(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPlaySelected");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(latestItemElement.getOnPlaySelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onImageSelected");
        jsonSerializer.serialize(latestItemElement.getOnImageSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.SECONDARY_TEXT);
        SimpleSerializers.serializeString(latestItemElement.getSecondaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metadata");
        TemplateMetadataElementSerializer.INSTANCE.serialize(latestItemElement.getMetadata(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        jsonSerializer.serialize(latestItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("bookmark");
        BookmarkElementSerializer.INSTANCE.serialize(latestItemElement.getBookmark(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isDisabled");
        SimpleSerializers.serializeBoolean(latestItemElement.isIsDisabled(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.TERTIARY_TEXT);
        SimpleSerializers.serializeString(latestItemElement.getTertiaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PRIMARY_TEXT);
        SimpleSerializers.serializeString(latestItemElement.getPrimaryText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(latestItemElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(latestItemElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.LABEL);
        SimpleSerializers.serializeString(latestItemElement.getLabel(), jsonGenerator, serializerProvider);
    }
}
