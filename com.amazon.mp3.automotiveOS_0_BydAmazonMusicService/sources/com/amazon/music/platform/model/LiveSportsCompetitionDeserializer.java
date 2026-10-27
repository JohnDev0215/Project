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
public class LiveSportsCompetitionDeserializer extends JsonDeserializer<LiveSportsCompetition> {
    public static final LiveSportsCompetitionDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LiveSportsCompetitionDeserializer liveSportsCompetitionDeserializer = new LiveSportsCompetitionDeserializer();
        INSTANCE = liveSportsCompetitionDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.LiveSportsCompetitionDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(LiveSportsCompetition.class, liveSportsCompetitionDeserializer);
    }

    private LiveSportsCompetitionDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public LiveSportsCompetition deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#LiveSportsCompetition".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public LiveSportsCompetition deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        LiveSportsCompetition liveSportsCompetition = new LiveSportsCompetition();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("competitionLink".equals(currentName)) {
                liveSportsCompetition.setCompetitionLink(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("liveSportsImages".equals(currentName)) {
                liveSportsCompetition.setLiveSportsImages(LiveSportsImagesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("competitionId".equals(currentName)) {
                liveSportsCompetition.setCompetitionId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("name".equals(currentName)) {
                liveSportsCompetition.setName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return liveSportsCompetition;
    }
}
