package com.amazon.avod.util.json;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MapParser<K, V> implements JacksonJsonParser<ImmutableMap<K, V>> {

    @Nonnull
    private final JacksonJsonParser<K> mKeyParser;

    @Nonnull
    private final ServiceTypesProxy mServiceTypesProxy;

    @Nonnull
    private final JacksonJsonParser<V> mValueParser;

    public static <T, U> MapParser<T, U> newParser(JacksonJsonParser<T> jacksonJsonParser, JacksonJsonParser<U> jacksonJsonParser2) {
        return new MapParser<>(jacksonJsonParser, jacksonJsonParser2, ServiceTypesProxy.getInstance());
    }

    private MapParser(@Nonnull JacksonJsonParser<K> jacksonJsonParser, @Nonnull JacksonJsonParser<V> jacksonJsonParser2, @Nonnull ServiceTypesProxy serviceTypesProxy) {
        this.mKeyParser = (JacksonJsonParser) Preconditions.checkNotNull(jacksonJsonParser, "keyParser");
        this.mValueParser = (JacksonJsonParser) Preconditions.checkNotNull(jacksonJsonParser2, "valueParser");
        this.mServiceTypesProxy = (ServiceTypesProxy) Preconditions.checkNotNull(serviceTypesProxy, "serviceTypesProxy");
    }

    @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
    @Nonnull
    public ImmutableMap<K, V> parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
        Preconditions.checkNotNull(jsonNode, "node");
        if (!jsonNode.isObject()) {
            throw new JsonContractException("Can't parse a non-object type into a map");
        }
        ImmutableMap.Builder builder = ImmutableMap.builder();
        Iterator<Map.Entry<String, JsonNode>> itFields = jsonNode.fields();
        while (itFields.hasNext()) {
            Map.Entry<String, JsonNode> next = itFields.next();
            TextNode textNode = new TextNode(next.getKey());
            JsonNode value = next.getValue();
            if (!value.isNull()) {
                try {
                    builder.put(this.mKeyParser.parse(textNode), this.mValueParser.parse(value));
                } catch (JsonContractException e) {
                    this.mServiceTypesProxy.exception(e, "Encountered JsonContractException parsing map entry; will drop from the map", new Object[0]);
                }
            }
        }
        return builder.build();
    }

    @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
    @Nonnull
    public ImmutableMap<K, V> parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
        Preconditions.checkNotNull(jsonParser, "parser");
        JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
        ImmutableMap.Builder builder = ImmutableMap.builder();
        while (true) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                    try {
                        K k = this.mKeyParser.parse(jsonParser);
                        jsonParser.nextToken();
                        if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                            builder.put(k, this.mValueParser.parse(jsonParser));
                        }
                    } catch (JsonContractException e) {
                        this.mServiceTypesProxy.exception(e, "Encountered JsonContractException parsing map entry; will drop from the map", new Object[0]);
                    }
                }
            } else {
                return builder.build();
            }
        }
    }
}
