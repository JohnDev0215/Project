package com.amazon.musicensembleservice;

import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import com.amazon.layout.music.model.BlockListDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class ArtistDetailLibraryResponseDeserializer extends JsonDeserializer<ArtistDetailLibraryResponse> {
    public static final ArtistDetailLibraryResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ArtistDetailLibraryResponseDeserializer artistDetailLibraryResponseDeserializer = new ArtistDetailLibraryResponseDeserializer();
        INSTANCE = artistDetailLibraryResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.ArtistDetailLibraryResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(ArtistDetailLibraryResponse.class, artistDetailLibraryResponseDeserializer);
    }

    private ArtistDetailLibraryResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public ArtistDetailLibraryResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice#ArtistDetailLibraryResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public ArtistDetailLibraryResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ArtistDetailLibraryResponse artistDetailLibraryResponse = new ArtistDetailLibraryResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("blocks".equals(currentName)) {
                artistDetailLibraryResponse.setBlocks(BlockListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (MAPWebViewEventHelper.KEY_ERRORS.equals(currentName)) {
                artistDetailLibraryResponse.setErrors(ErrorListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return artistDetailLibraryResponse;
    }
}
