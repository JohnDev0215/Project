package com.amazon.music.platform.model;

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
public class AlbumDeserializer extends JsonDeserializer<Album> {
    public static final AlbumDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AlbumDeserializer albumDeserializer = new AlbumDeserializer();
        INSTANCE = albumDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.AlbumDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Album.class, albumDeserializer);
    }

    private AlbumDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Album deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#CatalogAlbum".equals(text)) {
                return CatalogAlbumDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#LibraryAlbum".equals(text)) {
                return LibraryAlbumDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#Album".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Album deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
