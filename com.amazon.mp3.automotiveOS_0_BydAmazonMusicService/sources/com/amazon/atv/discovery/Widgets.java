package com.amazon.atv.discovery;

import com.amazon.atv.discovery.PaginationActionBase;
import com.amazon.atv.discovery.Widget;
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
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class Widgets {
    public final int maxWidgets;
    public final Optional<PaginationActionBase> paginationLink;
    public final int version;
    public final ImmutableList<Widget> widgetList;

    private Widgets(Builder builder) {
        this.paginationLink = Optional.fromNullable(builder.paginationLink);
        this.maxWidgets = builder.maxWidgets;
        this.version = builder.version;
        this.widgetList = (ImmutableList) Preconditions.checkNotNull(builder.widgetList, "Unexpected null field: widgetList");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Widgets)) {
            return false;
        }
        Widgets widgets = (Widgets) obj;
        return Objects.equal(this.paginationLink, widgets.paginationLink) && Objects.equal(Integer.valueOf(this.maxWidgets), Integer.valueOf(widgets.maxWidgets)) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(widgets.version)) && Objects.equal(this.widgetList, widgets.widgetList);
    }

    public int hashCode() {
        return Objects.hashCode(this.paginationLink, Integer.valueOf(this.maxWidgets), Integer.valueOf(this.version), this.widgetList);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("paginationLink", this.paginationLink).add("maxWidgets", this.maxWidgets).add("version", this.version).add("widgetList", this.widgetList).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public int maxWidgets;
        public PaginationActionBase paginationLink;
        public int version;
        public ImmutableList<Widget> widgetList;

        public Widgets build() {
            return new Widgets(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<Widgets> {
        private final PaginationActionBase.Parser mPaginationActionBaseParser;
        private final ListParser<Widget> mWidgetListParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mWidgetListParser = ListParser.newParser(new Widget.Parser(objectMapper));
            this.mPaginationActionBaseParser = new PaginationActionBase.Parser(objectMapper);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public Widgets parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Widgets:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public Widgets parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("Widgets:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Widgets parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "Widgets");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1780692821:
                            if (next.equals("maxWidgets")) {
                                b = 1;
                            }
                            break;
                        case -307446526:
                            if (next.equals("widgetList")) {
                                b = 3;
                            }
                            break;
                        case 142868148:
                            if (next.equals("paginationLink")) {
                                b = 0;
                            }
                            break;
                        case 351608024:
                            if (next.equals("version")) {
                                b = 2;
                            }
                            break;
                    }
                    ImmutableList<Widget> immutableList = null;
                    PaginationActionBase paginationActionBase = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            paginationActionBase = this.mPaginationActionBaseParser.parse(jsonNode2);
                        }
                        builder.paginationLink = paginationActionBase;
                    } else if (b == 1) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field maxWidgets can't be null");
                        }
                        builder.maxWidgets = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    } else if (b == 2) {
                        if (jsonNode2.isNull()) {
                            throw new JsonContractException("primitive field version can't be null");
                        }
                        builder.version = SimpleParsers.parsePrimitiveInt(jsonNode2);
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            immutableList = this.mWidgetListParser.parse(jsonNode2);
                        }
                        builder.widgetList = immutableList;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing Widgets so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.maxWidgets), this, "maxWidgets");
            JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
            JsonParsingUtils.checkNotNull(builder.widgetList, this, "widgetList");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private Widgets parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                            switch (currentName.hashCode()) {
                                case -1780692821:
                                    if (currentName.equals("maxWidgets")) {
                                        b = 1;
                                    }
                                    break;
                                case -307446526:
                                    if (currentName.equals("widgetList")) {
                                        b = 3;
                                    }
                                    break;
                                case 142868148:
                                    if (currentName.equals("paginationLink")) {
                                        b = 0;
                                    }
                                    break;
                                case 351608024:
                                    if (currentName.equals("version")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            ImmutableList<Widget> immutableList = null;
                            PaginationActionBase paginationActionBase = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    paginationActionBase = this.mPaginationActionBaseParser.parse(jsonParser);
                                }
                                builder.paginationLink = paginationActionBase;
                            } else if (b == 1) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field maxWidgets can't be null");
                                }
                                builder.maxWidgets = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else if (b == 2) {
                                if (currentToken == JsonToken.VALUE_NULL) {
                                    throw new JsonContractException("primitive field version can't be null");
                                }
                                builder.version = SimpleParsers.parsePrimitiveInt(jsonParser);
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    immutableList = this.mWidgetListParser.parse(jsonParser);
                                }
                                builder.widgetList = immutableList;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing Widgets so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.maxWidgets), this, "maxWidgets");
                    JsonParsingUtils.checkNotNull(Integer.valueOf(builder.version), this, "version");
                    JsonParsingUtils.checkNotNull(builder.widgetList, this, "widgetList");
                    return builder.build();
                }
            }
        }
    }
}
