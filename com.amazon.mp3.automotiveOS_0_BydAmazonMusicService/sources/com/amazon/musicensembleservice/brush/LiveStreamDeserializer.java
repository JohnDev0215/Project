package com.amazon.musicensembleservice.brush;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class LiveStreamDeserializer extends JsonDeserializer<LiveStream> {
    public static final LiveStreamDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LiveStreamDeserializer liveStreamDeserializer = new LiveStreamDeserializer();
        INSTANCE = liveStreamDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.LiveStreamDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(LiveStream.class, liveStreamDeserializer);
    }

    private LiveStreamDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public LiveStream deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice.brush#LiveStream".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public LiveStream deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        LiveStream liveStream = new LiveStream();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("primaryTitle".equals(currentName)) {
                liveStream.setPrimaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artists".equals(currentName)) {
                liveStream.setArtists(ArtistsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("languagesOfPerformance".equals(currentName)) {
                liveStream.setLanguagesOfPerformance(StringsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                liveStream.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("rankedIndex".equals(currentName)) {
                liveStream.setRankedIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("images".equals(currentName)) {
                liveStream.setImages(ImagesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("streamProvider".equals(currentName)) {
                liveStream.setStreamProvider(StreamProviderDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("viewers".equals(currentName)) {
                liveStream.setViewers(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tertiaryTitle".equals(currentName)) {
                liveStream.setTertiaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("secondaryTitle".equals(currentName)) {
                liveStream.setSecondaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                liveStream.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("name".equals(currentName)) {
                liveStream.setName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                liveStream.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                liveStream.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return liveStream;
    }
}
