package Podcast.PlaybackInterface.v1_0;

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
public class MediaMetadataElementSerializer extends JsonSerializer<MediaMetadataElement> {
    public static final MediaMetadataElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MediaMetadataElementSerializer mediaMetadataElementSerializer = new MediaMetadataElementSerializer();
        INSTANCE = mediaMetadataElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.MediaMetadataElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MediaMetadataElement.class, mediaMetadataElementSerializer);
    }

    private MediaMetadataElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MediaMetadataElement mediaMetadataElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (mediaMetadataElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(mediaMetadataElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MediaMetadataElement mediaMetadataElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("podcastDeeplink");
        SimpleSerializers.serializeString(mediaMetadataElement.getPodcastDeeplink(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(mediaMetadataElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("logo");
        SimpleSerializers.serializeString(mediaMetadataElement.getLogo(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("mediaId");
        SimpleSerializers.serializeString(mediaMetadataElement.getMediaId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onAdditionalMetadataRequired");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(mediaMetadataElement.getOnAdditionalMetadataRequired(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("mediaCollectionId");
        SimpleSerializers.serializeString(mediaMetadataElement.getMediaCollectionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("artwork");
        SimpleSerializers.serializeString(mediaMetadataElement.getArtwork(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("durationSeconds");
        SimpleSerializers.serializeLong(mediaMetadataElement.getDurationSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_SUBTITLE);
        SimpleSerializers.serializeString(mediaMetadataElement.getSubtitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(mediaMetadataElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onTranscriptsRequired");
        jsonSerializer.serialize(mediaMetadataElement.getOnTranscriptsRequired(), jsonGenerator, serializerProvider);
    }
}
