package com.amazon.avod.util.json;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PolymorphicJsonParser<T> extends JacksonJsonParserBase<T> {

    @Nonnull
    private final ParserMapFunction<T> mParserMapFunction;

    @Nonnull
    private final ServiceTypesProxy mServiceTypesProxy;

    protected PolymorphicJsonParser(@Nonnull ObjectMapper objectMapper, @Nonnull ParserMapFunction<T> parserMapFunction) {
        this(objectMapper, parserMapFunction, ServiceTypesProxy.getInstance());
    }

    protected PolymorphicJsonParser(@Nonnull ObjectMapper objectMapper, @Nonnull ParserMapFunction<T> parserMapFunction, @Nonnull ServiceTypesProxy serviceTypesProxy) {
        super(objectMapper);
        this.mParserMapFunction = (ParserMapFunction) Preconditions.checkNotNull(parserMapFunction, "parserMapFunction");
        this.mServiceTypesProxy = (ServiceTypesProxy) Preconditions.checkNotNull(serviceTypesProxy, "serviceTypesProxy");
    }

    @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
    @Nonnull
    public final T parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
        return this.mParserMapFunction.apply(this.mObjectMapper, jsonNode).parse(jsonNode);
    }

    @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
    @Nonnull
    public final T parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
        Object objBeginTrace = this.mServiceTypesProxy.beginTrace("%s:Parse", getClass().getCanonicalName());
        try {
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            return parse((ObjectNode) ((JsonNode) this.mObjectMapper.readTree(jsonParser)));
        } finally {
            this.mServiceTypesProxy.endTrace(objBeginTrace);
        }
    }
}
