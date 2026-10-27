package Podcast.Touch.MediaItemsInterface.v1_0;

import Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElementSerializer;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class MediaItemElementSerializer extends JsonSerializer<MediaItemElement> {
    public static final MediaItemElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MediaItemElementSerializer mediaItemElementSerializer = new MediaItemElementSerializer();
        INSTANCE = mediaItemElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.MediaItemsInterface.v1_0.MediaItemElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MediaItemElement.class, mediaItemElementSerializer);
    }

    private MediaItemElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MediaItemElement mediaItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (mediaItemElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(mediaItemElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MediaItemElement mediaItemElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(mediaItemElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        MethodsSerializer.INSTANCE.serialize(mediaItemElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("mediaId");
        SimpleSerializers.serializeString(mediaItemElement.getMediaId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("bookmark");
        BookmarkElementSerializer.INSTANCE.serialize(mediaItemElement.getBookmark(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("durationMilliseconds");
        SimpleSerializers.serializeLong(mediaItemElement.getDurationMilliseconds(), jsonGenerator, serializerProvider);
    }
}
