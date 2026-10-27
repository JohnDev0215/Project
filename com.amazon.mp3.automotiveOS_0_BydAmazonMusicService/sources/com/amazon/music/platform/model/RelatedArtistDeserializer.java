package com.amazon.music.platform.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class RelatedArtistDeserializer extends JsonDeserializer<RelatedArtist> {
    public static final RelatedArtistDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RelatedArtistDeserializer relatedArtistDeserializer = new RelatedArtistDeserializer();
        INSTANCE = relatedArtistDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.RelatedArtistDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RelatedArtist.class, relatedArtistDeserializer);
    }

    private RelatedArtistDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RelatedArtist deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#RelatedArtist".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RelatedArtist deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RelatedArtist relatedArtist = new RelatedArtist();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("title".equals(currentName)) {
                relatedArtist.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("titleUnlocalized".equals(currentName)) {
                relatedArtist.setTitleUnlocalized(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                relatedArtist.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("alternativeName".equals(currentName)) {
                relatedArtist.setAlternativeName(ArtistNamesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("contributorAsin".equals(currentName)) {
                relatedArtist.setContributorAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("role".equals(currentName)) {
                relatedArtist.setRole(RolesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return relatedArtist;
    }
}
