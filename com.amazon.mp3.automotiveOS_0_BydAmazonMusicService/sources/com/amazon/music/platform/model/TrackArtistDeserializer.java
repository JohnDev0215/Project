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
public class TrackArtistDeserializer extends JsonDeserializer<TrackArtist> {
    public static final TrackArtistDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TrackArtistDeserializer trackArtistDeserializer = new TrackArtistDeserializer();
        INSTANCE = trackArtistDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.TrackArtistDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(TrackArtist.class, trackArtistDeserializer);
    }

    private TrackArtistDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public TrackArtist deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#TrackArtist".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public TrackArtist deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TrackArtist trackArtist = new TrackArtist();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("title".equals(currentName)) {
                trackArtist.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                trackArtist.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("alternativeName".equals(currentName)) {
                trackArtist.setAlternativeName(ArtistNamesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("contributorAsin".equals(currentName)) {
                trackArtist.setContributorAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("role".equals(currentName)) {
                trackArtist.setRole(RolesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("titleUnlocalized".equals(currentName)) {
                trackArtist.setTitleUnlocalized(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return trackArtist;
    }
}
