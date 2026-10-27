package Podcast.FollowInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class FollowMetadataWriteElementDeserializer extends JsonDeserializer<FollowMetadataWriteElement> {
    public static final FollowMetadataWriteElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FollowMetadataWriteElementDeserializer followMetadataWriteElementDeserializer = new FollowMetadataWriteElementDeserializer();
        INSTANCE = followMetadataWriteElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.FollowInterface.v1_0.FollowMetadataWriteElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(FollowMetadataWriteElement.class, followMetadataWriteElementDeserializer);
    }

    private FollowMetadataWriteElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public FollowMetadataWriteElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of object, got " + currentToken, jsonParser.getTokenLocation());
        }
        if (jsonParser.nextToken() == JsonToken.FIELD_NAME && "__type".equals(jsonParser.getCurrentName())) {
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            String text = jsonParser.getText();
            jsonParser.nextToken();
            if ("Podcast.FollowInterface.v1_0#FollowMetadataWriteElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public FollowMetadataWriteElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        FollowMetadataWriteElement followMetadataWriteElement = new FollowMetadataWriteElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("title".equals(currentName)) {
                followMetadataWriteElement.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                followMetadataWriteElement.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastShowVariantId".equals(currentName)) {
                followMetadataWriteElement.setPodcastShowVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("publisher".equals(currentName)) {
                followMetadataWriteElement.setPublisher(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                followMetadataWriteElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                followMetadataWriteElement.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return followMetadataWriteElement;
    }
}
