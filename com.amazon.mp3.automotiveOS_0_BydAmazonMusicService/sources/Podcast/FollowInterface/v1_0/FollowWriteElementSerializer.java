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
public class FollowWriteElementSerializer extends JsonSerializer<FollowWriteElement> {
    public static final FollowWriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FollowWriteElementSerializer followWriteElementSerializer = new FollowWriteElementSerializer();
        INSTANCE = followWriteElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.FollowInterface.v1_0.FollowWriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FollowWriteElement.class, followWriteElementSerializer);
    }

    private FollowWriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FollowWriteElement followWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (followWriteElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(followWriteElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FollowWriteElement followWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(followWriteElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("followed");
        SimpleSerializers.serializePrimitiveBoolean(followWriteElement.isFollowed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(followWriteElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("publisher");
        SimpleSerializers.serializeString(followWriteElement.getPublisher(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastShowVariantId");
        SimpleSerializers.serializeString(followWriteElement.getPodcastShowVariantId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(followWriteElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(followWriteElement.getId(), jsonGenerator, serializerProvider);
    }
}
