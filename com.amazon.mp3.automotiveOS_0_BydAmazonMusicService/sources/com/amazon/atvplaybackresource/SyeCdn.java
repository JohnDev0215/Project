package com.amazon.atvplaybackresource;

import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class SyeCdn {
    public final String cdnName;
    public final String channelId;
    public final String frontendUrl;
    public final String token;
    public final String urlSetId;

    private SyeCdn(Builder builder) {
        this.frontendUrl = (String) Preconditions.checkNotNull(builder.frontendUrl, "Unexpected null field: frontendUrl");
        this.cdnName = (String) Preconditions.checkNotNull(builder.cdnName, "Unexpected null field: cdnName");
        this.urlSetId = (String) Preconditions.checkNotNull(builder.urlSetId, "Unexpected null field: urlSetId");
        this.token = (String) Preconditions.checkNotNull(builder.token, "Unexpected null field: token");
        this.channelId = (String) Preconditions.checkNotNull(builder.channelId, "Unexpected null field: channelId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SyeCdn)) {
            return false;
        }
        SyeCdn syeCdn = (SyeCdn) obj;
        return Objects.equal(this.frontendUrl, syeCdn.frontendUrl) && Objects.equal(this.cdnName, syeCdn.cdnName) && Objects.equal(this.urlSetId, syeCdn.urlSetId) && Objects.equal(this.token, syeCdn.token) && Objects.equal(this.channelId, syeCdn.channelId);
    }

    public int hashCode() {
        return Objects.hashCode(this.frontendUrl, this.cdnName, this.urlSetId, this.token, this.channelId);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("frontendUrl", this.frontendUrl).add("cdnName", this.cdnName).add("urlSetId", this.urlSetId).add("token", this.token).add("channelId", this.channelId).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String cdnName;
        public String channelId;
        public String frontendUrl;
        public String token;
        public String urlSetId;

        public SyeCdn build() {
            return new SyeCdn(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<SyeCdn> {
        private final SimpleParsers.StringParser mnonEmptyStringParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mnonEmptyStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public SyeCdn parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeCdn:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public SyeCdn parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("SyeCdn:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0063  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atvplaybackresource.SyeCdn parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.databind.JsonNode r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 278
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atvplaybackresource.SyeCdn.Parser.parseInternal(com.fasterxml.jackson.databind.JsonNode):com.amazon.atvplaybackresource.SyeCdn");
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:27:0x006d  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atvplaybackresource.SyeCdn parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.core.JsonParser r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 280
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atvplaybackresource.SyeCdn.Parser.parseInternal(com.fasterxml.jackson.core.JsonParser):com.amazon.atvplaybackresource.SyeCdn");
        }
    }
}
