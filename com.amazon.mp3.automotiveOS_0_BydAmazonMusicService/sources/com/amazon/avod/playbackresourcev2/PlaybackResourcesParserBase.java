package com.amazon.avod.playbackresourcev2;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PlaybackResourcesParserBase<T> extends JacksonJsonParserBase<T> {
    private final Class<T> mTClass;

    protected abstract T parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException;

    protected abstract T parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException;

    public PlaybackResourcesParserBase(@Nonnull ObjectMapper objectMapper, @Nonnull Class<T> cls) {
        super(objectMapper);
        this.mTClass = (Class) Preconditions.checkNotNull(cls, "tClass");
    }

    @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
    @Nonnull
    public T parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
        ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
        Object objBeginTrace = serviceTypesProxy.beginTrace("%s:ParseTree", this.mTClass.getSimpleName());
        try {
            return parseInternal(jsonNode);
        } finally {
            serviceTypesProxy.endTrace(objBeginTrace);
        }
    }

    @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
    @Nonnull
    public T parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
        ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
        Object objBeginTrace = serviceTypesProxy.beginTrace("%s:Parse", this.mTClass.getSimpleName());
        try {
            return parseInternal(jsonParser);
        } finally {
            serviceTypesProxy.endTrace(objBeginTrace);
        }
    }
}
