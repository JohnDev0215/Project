package Podcast.PlaybackInterface.v1_0;

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
public class EMPMediaMetadataElementSerializer extends JsonSerializer<EMPMediaMetadataElement> {
    public static final EMPMediaMetadataElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EMPMediaMetadataElementSerializer eMPMediaMetadataElementSerializer = new EMPMediaMetadataElementSerializer();
        INSTANCE = eMPMediaMetadataElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.EMPMediaMetadataElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(EMPMediaMetadataElement.class, eMPMediaMetadataElementSerializer);
    }

    private EMPMediaMetadataElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull EMPMediaMetadataElement eMPMediaMetadataElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (eMPMediaMetadataElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(eMPMediaMetadataElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(EMPMediaMetadataElement eMPMediaMetadataElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("trackId");
        SimpleSerializers.serializeString(eMPMediaMetadataElement.getTrackId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("trackDurationMilliseconds");
        SimpleSerializers.serializeLong(eMPMediaMetadataElement.getTrackDurationMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("artist");
        SimpleSerializers.serializeString(eMPMediaMetadataElement.getArtist(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("trackNumber");
        SimpleSerializers.serializeInteger(eMPMediaMetadataElement.getTrackNumber(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("albumId");
        SimpleSerializers.serializeString(eMPMediaMetadataElement.getAlbumId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("coverId");
        SimpleSerializers.serializeString(eMPMediaMetadataElement.getCoverId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("trackName");
        SimpleSerializers.serializeString(eMPMediaMetadataElement.getTrackName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackSourceId");
        SimpleSerializers.serializeString(eMPMediaMetadataElement.getPlaybackSourceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackSource");
        SimpleSerializers.serializeString(eMPMediaMetadataElement.getPlaybackSource(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("album");
        SimpleSerializers.serializeLong(eMPMediaMetadataElement.getAlbum(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("artistId");
        SimpleSerializers.serializeString(eMPMediaMetadataElement.getArtistId(), jsonGenerator, serializerProvider);
    }
}
