package Podcast.FollowInterface.v1_0;

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
public class FollowMetadataWriteElementSerializer extends JsonSerializer<FollowMetadataWriteElement> {
    public static final FollowMetadataWriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FollowMetadataWriteElementSerializer followMetadataWriteElementSerializer = new FollowMetadataWriteElementSerializer();
        INSTANCE = followMetadataWriteElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.FollowInterface.v1_0.FollowMetadataWriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FollowMetadataWriteElement.class, followMetadataWriteElementSerializer);
    }

    private FollowMetadataWriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FollowMetadataWriteElement followMetadataWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (followMetadataWriteElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(followMetadataWriteElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FollowMetadataWriteElement followMetadataWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(followMetadataWriteElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(followMetadataWriteElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastShowVariantId");
        SimpleSerializers.serializeString(followMetadataWriteElement.getPodcastShowVariantId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("publisher");
        SimpleSerializers.serializeString(followMetadataWriteElement.getPublisher(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(followMetadataWriteElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(followMetadataWriteElement.getDescription(), jsonGenerator, serializerProvider);
    }
}
