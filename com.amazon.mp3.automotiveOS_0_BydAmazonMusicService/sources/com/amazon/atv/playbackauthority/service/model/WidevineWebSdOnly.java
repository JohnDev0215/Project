package com.amazon.atv.playbackauthority.service.model;

import com.amazon.atv.playbackauthority.service.model.DrmPolicy;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class WidevineWebSdOnly extends DrmPolicy {
    private WidevineWebSdOnly(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.atv.playbackauthority.service.model.DrmPolicy
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WidevineWebSdOnly)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.atv.playbackauthority.service.model.DrmPolicy
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @NotThreadSafe
    public static class Builder extends DrmPolicy.Builder {
        public WidevineWebSdOnly build() {
            return new WidevineWebSdOnly(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<WidevineWebSdOnly> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public WidevineWebSdOnly parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineWebSdOnly:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public WidevineWebSdOnly parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("WidevineWebSdOnly:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private WidevineWebSdOnly parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "WidevineWebSdOnly");
            return new Builder().build();
        }

        @Nonnull
        private WidevineWebSdOnly parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            return new Builder().build();
        }
    }
}
