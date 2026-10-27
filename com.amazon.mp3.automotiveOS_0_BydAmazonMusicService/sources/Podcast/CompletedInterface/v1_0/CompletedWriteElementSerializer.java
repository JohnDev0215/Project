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
public class CompletedWriteElementSerializer extends JsonSerializer<CompletedWriteElement> {
    public static final CompletedWriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CompletedWriteElementSerializer completedWriteElementSerializer = new CompletedWriteElementSerializer();
        INSTANCE = completedWriteElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.CompletedInterface.v1_0.CompletedWriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CompletedWriteElement.class, completedWriteElementSerializer);
    }

    private CompletedWriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CompletedWriteElement completedWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (completedWriteElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(completedWriteElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CompletedWriteElement completedWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("completed");
        SimpleSerializers.serializePrimitiveBoolean(completedWriteElement.isCompleted(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastTitle");
        SimpleSerializers.serializeString(completedWriteElement.getPodcastTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("publishDate");
        SimpleSerializers.serializeString(completedWriteElement.getPublishDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastImage");
        SimpleSerializers.serializeString(completedWriteElement.getPodcastImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(completedWriteElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(completedWriteElement.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(completedWriteElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("seasonNumber");
        SimpleSerializers.serializeInteger(completedWriteElement.getSeasonNumber(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("authors");
        StringsSerializer.INSTANCE.serialize(completedWriteElement.getAuthors(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(completedWriteElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(completedWriteElement.getId(), jsonGenerator, serializerProvider);
    }
}
