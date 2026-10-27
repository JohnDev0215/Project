package Podcast.CompletedInterface.v1_0;

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
public class CompletedMetadataWriteElementSerializer extends JsonSerializer<CompletedMetadataWriteElement> {
    public static final CompletedMetadataWriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CompletedMetadataWriteElementSerializer completedMetadataWriteElementSerializer = new CompletedMetadataWriteElementSerializer();
        INSTANCE = completedMetadataWriteElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.CompletedInterface.v1_0.CompletedMetadataWriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CompletedMetadataWriteElement.class, completedMetadataWriteElementSerializer);
    }

    private CompletedMetadataWriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CompletedMetadataWriteElement completedMetadataWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (completedMetadataWriteElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(completedMetadataWriteElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CompletedMetadataWriteElement completedMetadataWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(completedMetadataWriteElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("authors");
        StringsSerializer.INSTANCE.serialize(completedMetadataWriteElement.getAuthors(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastImage");
        SimpleSerializers.serializeString(completedMetadataWriteElement.getPodcastImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("seasonNumber");
        SimpleSerializers.serializeInteger(completedMetadataWriteElement.getSeasonNumber(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(completedMetadataWriteElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("publishDate");
        SimpleSerializers.serializeString(completedMetadataWriteElement.getPublishDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(completedMetadataWriteElement.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(completedMetadataWriteElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastTitle");
        SimpleSerializers.serializeString(completedMetadataWriteElement.getPodcastTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(completedMetadataWriteElement.getTitle(), jsonGenerator, serializerProvider);
    }
}
