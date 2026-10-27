package Podcast.Touch.CardNowPlayingTemplateInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementDeserializer;
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
public class CardElementDeserializer extends JsonDeserializer<CardElement> {
    public static final CardElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CardElementDeserializer cardElementDeserializer = new CardElementDeserializer();
        INSTANCE = cardElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CardElement.class, cardElementDeserializer);
    }

    private CardElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CardElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0#CardElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CardElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CardElement cardElement = new CardElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                cardElement.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("follow".equals(currentName)) {
                cardElement.setFollow(FollowButtonElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onCardViewed".equals(currentName)) {
                cardElement.setOnCardViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onPauseSelected".equals(currentName)) {
                cardElement.setOnPauseSelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onCardCentered".equals(currentName)) {
                cardElement.setOnCardCentered(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onSwipeNext".equals(currentName)) {
                cardElement.setOnSwipeNext(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onTapNext".equals(currentName)) {
                cardElement.setOnTapNext(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("save".equals(currentName)) {
                cardElement.setSave(SaveButtonElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onAboutSelected".equals(currentName)) {
                cardElement.setOnAboutSelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onPlaySelected".equals(currentName)) {
                cardElement.setOnPlaySelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("primaryButton".equals(currentName)) {
                cardElement.setPrimaryButton(ButtonElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("episodeOptions".equals(currentName)) {
                cardElement.setEpisodeOptions(EpisodeOptionsElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.SECONDARY_TEXT.equals(currentName)) {
                cardElement.setSecondaryText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onSwipePrevious".equals(currentName)) {
                cardElement.setOnSwipePrevious(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onTapPrevious".equals(currentName)) {
                cardElement.setOnTapPrevious(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                cardElement.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.PRIMARY_TEXT.equals(currentName)) {
                cardElement.setPrimaryText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                cardElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onCardSelected".equals(currentName)) {
                cardElement.setOnCardSelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return cardElement;
    }
}
