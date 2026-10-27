package Podcast.Touch.PodcastDetailTemplateInterface.v2_0;

import SOACoreInterface.v1_0.MethodsDeserializer;
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
public class ShareElementDeserializer extends JsonDeserializer<ShareElement> {
    public static final ShareElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ShareElementDeserializer shareElementDeserializer = new ShareElementDeserializer();
        INSTANCE = shareElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ShareElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(ShareElement.class, shareElementDeserializer);
    }

    private ShareElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public ShareElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#ShareElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public ShareElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ShareElement shareElement = new ShareElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("id".equals(currentName)) {
                shareElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("imageUrl".equals(currentName)) {
                shareElement.setImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("prompt".equals(currentName)) {
                shareElement.setPrompt(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                shareElement.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onShare".equals(currentName)) {
                shareElement.setOnShare(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_SUBTITLE.equals(currentName)) {
                shareElement.setSubtitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("deeplinkUrl".equals(currentName)) {
                shareElement.setDeeplinkUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                shareElement.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                shareElement.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("metricsEntityType".equals(currentName)) {
                shareElement.setMetricsEntityType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return shareElement;
    }
}
