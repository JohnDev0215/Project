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
public class LiveSportsProgramDeserializer extends JsonDeserializer<LiveSportsProgram> {
    public static final LiveSportsProgramDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LiveSportsProgramDeserializer liveSportsProgramDeserializer = new LiveSportsProgramDeserializer();
        INSTANCE = liveSportsProgramDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.LiveSportsProgramDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(LiveSportsProgram.class, liveSportsProgramDeserializer);
    }

    private LiveSportsProgramDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public LiveSportsProgram deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#LiveSportsProgram".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public LiveSportsProgram deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        LiveSportsProgram liveSportsProgram = new LiveSportsProgram();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("programType".equals(currentName)) {
                liveSportsProgram.setProgramType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("liveSportsImages".equals(currentName)) {
                liveSportsProgram.setLiveSportsImages(LiveSportsImagesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("programLink".equals(currentName)) {
                liveSportsProgram.setProgramLink(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("startTime".equals(currentName)) {
                liveSportsProgram.setStartTime(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                liveSportsProgram.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("programId".equals(currentName)) {
                liveSportsProgram.setProgramId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentProfiles".equals(currentName)) {
                liveSportsProgram.setContentProfiles(ContentProfilesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return liveSportsProgram;
    }
}
