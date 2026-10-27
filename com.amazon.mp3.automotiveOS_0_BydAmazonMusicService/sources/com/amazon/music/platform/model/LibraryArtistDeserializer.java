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
public class LibraryArtistDeserializer extends JsonDeserializer<LibraryArtist> {
    public static final LibraryArtistDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LibraryArtistDeserializer libraryArtistDeserializer = new LibraryArtistDeserializer();
        INSTANCE = libraryArtistDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.LibraryArtistDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(LibraryArtist.class, libraryArtistDeserializer);
    }

    private LibraryArtistDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public LibraryArtist deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#LibraryArtist".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public LibraryArtist deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        LibraryArtist libraryArtist = new LibraryArtist();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("asin".equals(currentName)) {
                libraryArtist.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artLarge".equals(currentName)) {
                libraryArtist.setArtLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artSmall".equals(currentName)) {
                libraryArtist.setArtSmall(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("name".equals(currentName)) {
                libraryArtist.setName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artTiny".equals(currentName)) {
                libraryArtist.setArtTiny(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artMedium".equals(currentName)) {
                libraryArtist.setArtMedium(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artFull".equals(currentName)) {
                libraryArtist.setArtFull(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artExtraLarge".equals(currentName)) {
                libraryArtist.setArtExtraLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artOriginal".equals(currentName)) {
                libraryArtist.setArtOriginal(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("objectId".equals(currentName)) {
                libraryArtist.setObjectId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return libraryArtist;
    }
}
