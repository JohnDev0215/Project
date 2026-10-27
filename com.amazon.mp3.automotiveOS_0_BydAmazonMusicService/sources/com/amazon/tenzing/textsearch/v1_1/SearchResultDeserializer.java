package com.amazon.tenzing.textsearch.v1_1;

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

/* JADX INFO: loaded from: classes5.dex */
public class SearchResultDeserializer extends JsonDeserializer<SearchResult> {
    public static final SearchResultDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SearchResultDeserializer searchResultDeserializer = new SearchResultDeserializer();
        INSTANCE = searchResultDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.SearchResultDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SearchResult.class, searchResultDeserializer);
    }

    private SearchResultDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SearchResult deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.tenzing.textsearch.v1_1#SearchResult".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SearchResult deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SearchResult searchResult = new SearchResult();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (ContextMappingConstants.CONTENT_INFO.equals(currentName)) {
                searchResult.setContentInfo(ContentInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                searchResult.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("searchResultExplanation".equals(currentName)) {
                searchResult.setSearchResultExplanation(SearchResultExplanationDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("totalHitCount".equals(currentName)) {
                searchResult.setTotalHitCount(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("hasFailure".equals(currentName)) {
                searchResult.setHasFailure(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("hits".equals(currentName)) {
                searchResult.setHits(HitsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                searchResult.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("nextPage".equals(currentName)) {
                searchResult.setNextPage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("searchResultMetricsContext".equals(currentName)) {
                searchResult.setSearchResultMetricsContext(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return searchResult;
    }
}
