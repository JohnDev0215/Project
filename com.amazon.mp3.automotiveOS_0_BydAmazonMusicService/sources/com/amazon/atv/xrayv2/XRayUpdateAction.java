package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.XRayActionTargetBase;
import com.amazon.atv.xrayv2.XRayBaseAction;
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
public class XRayUpdateAction extends XRayBaseAction {
    public final XRayActionTargetBase target;

    private XRayUpdateAction(Builder builder) {
        super(builder);
        this.target = (XRayActionTargetBase) Preconditions.checkNotNull(builder.target, "Unexpected null field: target");
    }

    @Override // com.amazon.atv.xrayv2.XRayBaseAction
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof XRayUpdateAction) {
            return super.equals(obj) && Objects.equal(this.target, ((XRayUpdateAction) obj).target);
        }
        return false;
    }

    @Override // com.amazon.atv.xrayv2.XRayBaseAction
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.target);
    }

    @Override // com.amazon.atv.xrayv2.XRayBaseAction
    public String toString() {
        return MoreObjects.toStringHelper(this).add("target", this.target).toString();
    }

    @NotThreadSafe
    public static class Builder extends XRayBaseAction.Builder {
        public XRayActionTargetBase target;

        public XRayUpdateAction build() {
            return new XRayUpdateAction(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<XRayUpdateAction> {
        private final SimpleParsers.StringParser mStringParser;
        private final XRayActionTargetBase.Parser mXRayActionTargetBaseParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mXRayActionTargetBaseParser = new XRayActionTargetBase.Parser(objectMapper);
            this.mStringParser = SimpleParsers.StringParser.INSTANCE;
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public XRayUpdateAction parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayUpdateAction:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public XRayUpdateAction parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("XRayUpdateAction:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0066  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atv.xrayv2.XRayUpdateAction parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.databind.JsonNode r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 294
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.xrayv2.XRayUpdateAction.Parser.parseInternal(com.fasterxml.jackson.databind.JsonNode):com.amazon.atv.xrayv2.XRayUpdateAction");
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0070  */
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.amazon.atv.xrayv2.XRayUpdateAction parseInternal(@javax.annotation.Nonnull com.fasterxml.jackson.core.JsonParser r17) throws com.amazon.avod.util.json.JsonContractException, java.io.IOException {
            /*
                Method dump skipped, instruction units count: 296
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.atv.xrayv2.XRayUpdateAction.Parser.parseInternal(com.fasterxml.jackson.core.JsonParser):com.amazon.atv.xrayv2.XRayUpdateAction");
        }
    }
}
