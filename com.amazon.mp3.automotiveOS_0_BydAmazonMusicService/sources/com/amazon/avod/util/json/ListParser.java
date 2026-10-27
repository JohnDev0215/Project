package com.amazon.avod.util.json;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ListParser<T> implements JacksonJsonParser<ImmutableList<T>> {

    @Nonnull
    private final JacksonJsonParser<T> mEntryParser;

    @Nonnull
    private final ServiceTypesProxy mServiceTypesProxy;

    public static <U> ListParser<U> newParser(JacksonJsonParser<U> jacksonJsonParser) {
        return new ListParser<>(jacksonJsonParser, ServiceTypesProxy.getInstance());
    }

    private ListParser(@Nonnull JacksonJsonParser<T> jacksonJsonParser, @Nonnull ServiceTypesProxy serviceTypesProxy) {
        this.mEntryParser = (JacksonJsonParser) Preconditions.checkNotNull(jacksonJsonParser, "entryParser");
        this.mServiceTypesProxy = (ServiceTypesProxy) Preconditions.checkNotNull(serviceTypesProxy, "serviceTypesProxy");
    }

    @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
    @Nonnull
    public ImmutableList<T> parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
        Preconditions.checkNotNull(jsonNode, "node");
        if (!jsonNode.isArray()) {
            throw new JsonContractException("Can't parse a non-array json node to list");
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        Iterator<JsonNode> it = jsonNode.iterator();
        while (it.hasNext()) {
            try {
                builder.add(this.mEntryParser.parse(it.next()));
            } catch (JsonContractException e) {
                this.mServiceTypesProxy.exception(e, "Encountered JsonContractException parsing list entry; will drop from the list", new Object[0]);
            }
        }
        return builder.build();
    }

    @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
    @Nonnull
    public ImmutableList<T> parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
        Preconditions.checkNotNull(jsonParser, "parser");
        ImmutableList.Builder builder = ImmutableList.builder();
        while (JsonValidator.isInsideArray(jsonParser.nextToken())) {
            try {
                if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                    builder.add(this.mEntryParser.parse(jsonParser));
                }
            } catch (JsonContractException e) {
                this.mServiceTypesProxy.exception(e, "Encountered JsonContractException parsing list entry; will drop from the list", new Object[0]);
            }
        }
        return builder.build();
    }
}
