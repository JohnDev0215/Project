package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.Action;
import com.amazon.atv.xrayv2.BreadCrumbBase;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.ListParser;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class BreadCrumbV2 extends BreadCrumbBase {
    public final ImmutableList<Action> crumbs;

    private BreadCrumbV2(Builder builder) {
        super(builder);
        this.crumbs = (ImmutableList) Preconditions.checkNotNull(builder.crumbs, "Unexpected null field: crumbs");
    }

    @Override // com.amazon.atv.xrayv2.BreadCrumbBase
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BreadCrumbV2) {
            return super.equals(obj) && Objects.equal(this.crumbs, ((BreadCrumbV2) obj).crumbs);
        }
        return false;
    }

    @Override // com.amazon.atv.xrayv2.BreadCrumbBase
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.crumbs);
    }

    @Override // com.amazon.atv.xrayv2.BreadCrumbBase
    public String toString() {
        return MoreObjects.toStringHelper(this).add("crumbs", this.crumbs).toString();
    }

    @NotThreadSafe
    public static class Builder extends BreadCrumbBase.Builder {
        public ImmutableList<Action> crumbs;

        public BreadCrumbV2 build() {
            return new BreadCrumbV2(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<BreadCrumbV2> {
        private final ListParser<Action> mActionListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mActionListParser = ListParser.newParser(new Action.Parser(objectMapper));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public BreadCrumbV2 parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BreadCrumbV2:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public BreadCrumbV2 parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("BreadCrumbV2:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Nonnull
        private BreadCrumbV2 parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "BreadCrumbV2");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    int iHashCode = next.hashCode();
                    if (iHashCode != -1351806504) {
                        if (iHashCode == 351608024 && next.equals("version")) {
                            b = 0;
                        }
                    } else if (next.equals("crumbs")) {
                        b = 1;
                    }
                    if (b != 0) {
                        if (b == 1) {
                            builder.crumbs = jsonNode2.isNull() ? null : this.mActionListParser.parse(jsonNode2);
                        }
                    } else {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing BreadCrumbV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.crumbs, this, "crumbs");
            return builder.build();
        }

        @Nonnull
        private BreadCrumbV2 parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            Builder builder = new Builder();
            JsonValidator.checkEqual(JsonToken.START_OBJECT, jsonParser.getCurrentToken(), jsonParser);
            while (true) {
                JsonToken jsonTokenNextToken = jsonParser.nextToken();
                if (JsonValidator.isInsideObject(jsonTokenNextToken)) {
                    if (jsonTokenNextToken == JsonToken.FIELD_NAME) {
                        String currentName = jsonParser.getCurrentName();
                        jsonParser.nextToken();
                        JsonToken currentToken = jsonParser.getCurrentToken();
                        byte b = -1;
                        try {
                            int iHashCode = currentName.hashCode();
                            if (iHashCode != -1351806504) {
                                if (iHashCode == 351608024 && currentName.equals("version")) {
                                    b = 0;
                                }
                            } else if (currentName.equals("crumbs")) {
                                b = 1;
                            }
                            if (b != 0) {
                                if (b == 1) {
                                    builder.crumbs = currentToken == JsonToken.VALUE_NULL ? null : this.mActionListParser.parse(jsonParser);
                                } else {
                                    jsonParser.skipChildren();
                                }
                            } else {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field version can't be null");
                                }
                                builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing BreadCrumbV2 so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.crumbs, this, "crumbs");
                    return builder.build();
                }
            }
        }
    }
}
