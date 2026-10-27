package com.amazon.layout.music.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes3.dex */
public class DoubleHorizontalTileDeserializer extends JsonDeserializer<DoubleHorizontalTile> {
    public static final DoubleHorizontalTileDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DoubleHorizontalTileDeserializer doubleHorizontalTileDeserializer = new DoubleHorizontalTileDeserializer();
        INSTANCE = doubleHorizontalTileDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.DoubleHorizontalTileDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(DoubleHorizontalTile.class, doubleHorizontalTileDeserializer);
    }

    private DoubleHorizontalTileDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public DoubleHorizontalTile deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#DoubleHorizontalTile".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public DoubleHorizontalTile deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        DoubleHorizontalTile doubleHorizontalTile = new DoubleHorizontalTile();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("title2".equals(currentName)) {
                doubleHorizontalTile.setTitle2(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("topicHint".equals(currentName)) {
                doubleHorizontalTile.setTopicHint(HintDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("image2".equals(currentName)) {
                doubleHorizontalTile.setImage2(ImageMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                doubleHorizontalTile.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                doubleHorizontalTile.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("decorator2".equals(currentName)) {
                doubleHorizontalTile.setDecorator2(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("surtitle".equals(currentName)) {
                doubleHorizontalTile.setSurtitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("target".equals(currentName)) {
                doubleHorizontalTile.setTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("image1".equals(currentName)) {
                doubleHorizontalTile.setImage1(ImageMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_SUBTITLE.equals(currentName)) {
                doubleHorizontalTile.setSubtitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title1".equals(currentName)) {
                doubleHorizontalTile.setTitle1(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("image3".equals(currentName)) {
                doubleHorizontalTile.setImage3(ImageMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("programHint".equals(currentName)) {
                doubleHorizontalTile.setProgramHint(HintDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("timerHint".equals(currentName)) {
                doubleHorizontalTile.setTimerHint(HintDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("decorator1".equals(currentName)) {
                doubleHorizontalTile.setDecorator1(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("subtitleIcon".equals(currentName)) {
                doubleHorizontalTile.setSubtitleIcon(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return doubleHorizontalTile;
    }
}
