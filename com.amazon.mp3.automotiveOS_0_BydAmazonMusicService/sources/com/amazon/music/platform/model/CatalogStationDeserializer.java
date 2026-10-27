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
public class CatalogStationDeserializer extends JsonDeserializer<CatalogStation> {
    public static final CatalogStationDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CatalogStationDeserializer catalogStationDeserializer = new CatalogStationDeserializer();
        INSTANCE = catalogStationDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.CatalogStationDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CatalogStation.class, catalogStationDeserializer);
    }

    private CatalogStationDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CatalogStation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#CatalogStation".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CatalogStation deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CatalogStation catalogStation = new CatalogStation();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("seedType".equals(currentName)) {
                catalogStation.setSeedType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("genres".equals(currentName)) {
                catalogStation.setGenres(GenresDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("moods".equals(currentName)) {
                catalogStation.setMoods(MoodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("stationSeedId".equals(currentName)) {
                catalogStation.setStationSeedId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("era".equals(currentName)) {
                catalogStation.setEra(ErasDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("popularities".equals(currentName)) {
                catalogStation.setPopularities(PopularitiesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                catalogStation.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                catalogStation.setArtistName(ArtistNamesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artOriginal".equals(currentName)) {
                catalogStation.setArtOriginal(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("primeStatus".equals(currentName)) {
                catalogStation.setPrimeStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                catalogStation.setIsMusicSubscription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                catalogStation.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentTier".equals(currentName)) {
                catalogStation.setContentTier(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("stationKey".equals(currentName)) {
                catalogStation.setStationKey(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("stationSeedType".equals(currentName)) {
                catalogStation.setStationSeedType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return catalogStation;
    }
}
