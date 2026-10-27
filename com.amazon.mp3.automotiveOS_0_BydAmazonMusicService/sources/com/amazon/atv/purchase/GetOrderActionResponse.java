package com.amazon.atv.purchase;

import com.amazon.atv.purchase.AuthAction;
import com.amazon.atv.purchase.ErrorAction;
import com.amazon.atv.purchase.PollAction;
import com.amazon.atv.purchase.activity.OrderAction;
import com.amazon.avod.servicetypes.ServiceTypesProxy;
import com.amazon.avod.util.json.EnumParser;
import com.amazon.avod.util.json.JacksonJsonParserBase;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.JsonParsingUtils;
import com.amazon.avod.util.json.JsonValidator;
import com.amazon.avod.util.json.SimpleParsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class GetOrderActionResponse {
    public final OrderAction action;
    public final Optional<AuthAction> authAction;
    public final Optional<ErrorAction> errorAction;
    public final Optional<String> orderToken;
    public final Optional<PollAction> pollAction;

    private GetOrderActionResponse(Builder builder) {
        this.orderToken = Optional.fromNullable(builder.orderToken);
        this.pollAction = Optional.fromNullable(builder.pollAction);
        this.authAction = Optional.fromNullable(builder.authAction);
        this.action = (OrderAction) Preconditions.checkNotNull(builder.action, "Unexpected null field: action");
        this.errorAction = Optional.fromNullable(builder.errorAction);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetOrderActionResponse)) {
            return false;
        }
        GetOrderActionResponse getOrderActionResponse = (GetOrderActionResponse) obj;
        return Objects.equal(this.orderToken, getOrderActionResponse.orderToken) && Objects.equal(this.pollAction, getOrderActionResponse.pollAction) && Objects.equal(this.authAction, getOrderActionResponse.authAction) && Objects.equal(this.action, getOrderActionResponse.action) && Objects.equal(this.errorAction, getOrderActionResponse.errorAction);
    }

    public int hashCode() {
        return Objects.hashCode(this.orderToken, this.pollAction, this.authAction, this.action, this.errorAction);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("orderToken", this.orderToken).add("pollAction", this.pollAction).add("authAction", this.authAction).add("action", this.action).add("errorAction", this.errorAction).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public OrderAction action;
        public AuthAction authAction;
        public ErrorAction errorAction;
        public String orderToken;
        public PollAction pollAction;

        public GetOrderActionResponse build() {
            return new GetOrderActionResponse(this);
        }
    }

    public static class Parser extends JacksonJsonParserBase<GetOrderActionResponse> {
        private final AuthAction.Parser mAuthActionParser;
        private final ErrorAction.Parser mErrorActionParser;
        private final EnumParser<OrderAction> mOrderActionParser;
        private final PollAction.Parser mPollActionParser;
        private final SimpleParsers.StringParser morderTokenParser;

        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper);
            this.mAuthActionParser = new AuthAction.Parser(objectMapper);
            this.morderTokenParser = SimpleParsers.StringParser.INSTANCE;
            this.mErrorActionParser = new ErrorAction.Parser(objectMapper);
            this.mPollActionParser = new PollAction.Parser(objectMapper);
            this.mOrderActionParser = EnumParser.newParser(OrderAction.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public GetOrderActionResponse parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetOrderActionResponse:ParseTree");
            try {
                return parseInternal(jsonNode);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        @Override // com.amazon.avod.util.json.JacksonJsonParserBase, com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public GetOrderActionResponse parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            ServiceTypesProxy serviceTypesProxy = ServiceTypesProxy.getInstance();
            Object objBeginTrace = serviceTypesProxy.beginTrace("GetOrderActionResponse:Parse");
            try {
                return parseInternal(jsonParser);
            } finally {
                serviceTypesProxy.endTrace(objBeginTrace);
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetOrderActionResponse parseInternal(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            JsonParsingUtils.throwIfNotObject(jsonNode, "GetOrderActionResponse");
            Builder builder = new Builder();
            Iterator<String> itFieldNames = jsonNode.fieldNames();
            while (itFieldNames.hasNext()) {
                String next = itFieldNames.next();
                JsonNode jsonNode2 = jsonNode.get(next);
                byte b = -1;
                try {
                    switch (next.hashCode()) {
                        case -1692035490:
                            if (next.equals("errorAction")) {
                                b = 4;
                            }
                            break;
                        case -1422950858:
                            if (next.equals("action")) {
                                b = 3;
                            }
                            break;
                        case -1031670059:
                            if (next.equals("pollAction")) {
                                b = 1;
                            }
                            break;
                        case 746103627:
                            if (next.equals("orderToken")) {
                                b = 0;
                            }
                            break;
                        case 1463773694:
                            if (next.equals("authAction")) {
                                b = 2;
                            }
                            break;
                    }
                    ErrorAction errorAction = null;
                    String str = null;
                    PollAction pollAction = null;
                    AuthAction authAction = null;
                    OrderAction orderAction = null;
                    if (b == 0) {
                        if (!jsonNode2.isNull()) {
                            str = this.morderTokenParser.parse(jsonNode2);
                        }
                        builder.orderToken = str;
                    } else if (b == 1) {
                        if (!jsonNode2.isNull()) {
                            pollAction = this.mPollActionParser.parse(jsonNode2);
                        }
                        builder.pollAction = pollAction;
                    } else if (b == 2) {
                        if (!jsonNode2.isNull()) {
                            authAction = this.mAuthActionParser.parse(jsonNode2);
                        }
                        builder.authAction = authAction;
                    } else if (b == 3) {
                        if (!jsonNode2.isNull()) {
                            orderAction = (OrderAction) this.mOrderActionParser.parse(jsonNode2);
                        }
                        builder.action = orderAction;
                    } else if (b == 4) {
                        if (!jsonNode2.isNull()) {
                            errorAction = this.mErrorActionParser.parse(jsonNode2);
                        }
                        builder.errorAction = errorAction;
                    }
                } catch (JsonContractException e) {
                    ServiceTypesProxy.getInstance().exception(e, next + " failed to parse when parsing GetOrderActionResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                }
            }
            JsonParsingUtils.checkNotNull(builder.action, this, "action");
            return builder.build();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nonnull
        private GetOrderActionResponse parseInternal(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
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
                                case -1692035490:
                                    if (currentName.equals("errorAction")) {
                                        b = 4;
                                    }
                                    break;
                                case -1422950858:
                                    if (currentName.equals("action")) {
                                        b = 3;
                                    }
                                    break;
                                case -1031670059:
                                    if (currentName.equals("pollAction")) {
                                        b = 1;
                                    }
                                    break;
                                case 746103627:
                                    if (currentName.equals("orderToken")) {
                                        b = 0;
                                    }
                                    break;
                                case 1463773694:
                                    if (currentName.equals("authAction")) {
                                        b = 2;
                                    }
                                    break;
                            }
                            ErrorAction errorAction = null;
                            String str = null;
                            PollAction pollAction = null;
                            AuthAction authAction = null;
                            OrderAction orderAction = null;
                            if (b == 0) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    str = this.morderTokenParser.parse(jsonParser);
                                }
                                builder.orderToken = str;
                            } else if (b == 1) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    pollAction = this.mPollActionParser.parse(jsonParser);
                                }
                                builder.pollAction = pollAction;
                            } else if (b == 2) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    authAction = this.mAuthActionParser.parse(jsonParser);
                                }
                                builder.authAction = authAction;
                            } else if (b == 3) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    orderAction = (OrderAction) this.mOrderActionParser.parse(jsonParser);
                                }
                                builder.action = orderAction;
                            } else if (b == 4) {
                                if (currentToken != JsonToken.VALUE_NULL) {
                                    errorAction = this.mErrorActionParser.parse(jsonParser);
                                }
                                builder.errorAction = errorAction;
                            } else {
                                jsonParser.skipChildren();
                            }
                        } catch (JsonContractException e) {
                            ServiceTypesProxy.getInstance().exception(e, currentName + " failed to parse when parsing GetOrderActionResponse so we may drop this from the response (if field was required). Will try to continue parsing.", new Object[0]);
                        }
                    }
                } else {
                    JsonParsingUtils.checkNotNull(builder.action, this, "action");
                    return builder.build();
                }
            }
        }
    }
}
