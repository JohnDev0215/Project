package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;

/* JADX INFO: compiled from: JsonNodeDeserializer.java */
/* JADX INFO: loaded from: classes6.dex */
abstract class BaseNodeDeserializer<T extends JsonNode> extends StdDeserializer<T> {
    protected final Boolean _supportsUpdates;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return true;
    }

    public BaseNodeDeserializer(Class<T> cls, Boolean bool) {
        super((Class<?>) cls);
        this._supportsUpdates = bool;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Untyped;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return this._supportsUpdates;
    }

    protected void _handleDuplicateField(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory, String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) throws JsonProcessingException {
        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)) {
            deserializationContext.reportInputMismatch(JsonNode.class, "Duplicate field '%s' for `ObjectNode`: not allowed when `DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY` enabled", str);
        }
        if (deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES)) {
            if (jsonNode.isArray()) {
                ((ArrayNode) jsonNode).add(jsonNode2);
                objectNode.replace(str, jsonNode);
            } else {
                ArrayNode arrayNode = jsonNodeFactory.arrayNode();
                arrayNode.add(jsonNode);
                arrayNode.add(jsonNode2);
                objectNode.replace(str, arrayNode);
            }
        }
    }

    protected final ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonNode jsonNodeDeserializeObject;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        String strNextFieldName = jsonParser.nextFieldName();
        while (strNextFieldName != null) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (jsonTokenNextToken == null) {
                jsonTokenNextToken = JsonToken.NOT_AVAILABLE;
            }
            int iId = jsonTokenNextToken.id();
            if (iId == 1) {
                jsonNodeDeserializeObject = deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (iId == 3) {
                jsonNodeDeserializeObject = deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (iId == 6) {
                jsonNodeDeserializeObject = jsonNodeFactory.textNode(jsonParser.getText());
            } else if (iId == 7) {
                jsonNodeDeserializeObject = _fromInt(jsonParser, deserializationContext, jsonNodeFactory);
            } else {
                switch (iId) {
                    case 9:
                        jsonNodeDeserializeObject = jsonNodeFactory.booleanNode(true);
                        break;
                    case 10:
                        jsonNodeDeserializeObject = jsonNodeFactory.booleanNode(false);
                        break;
                    case 11:
                        jsonNodeDeserializeObject = jsonNodeFactory.nullNode();
                        break;
                    case 12:
                        jsonNodeDeserializeObject = _fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                    default:
                        jsonNodeDeserializeObject = deserializeAny(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                }
            }
            JsonNode jsonNode = jsonNodeDeserializeObject;
            JsonNode jsonNodeReplace = objectNode.replace(strNextFieldName, jsonNode);
            if (jsonNodeReplace != null) {
                _handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory, strNextFieldName, objectNode, jsonNodeReplace, jsonNode);
            }
            strNextFieldName = jsonParser.nextFieldName();
        }
        return objectNode;
    }

    protected final ObjectNode deserializeObjectAtName(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonNode jsonNodeDeserializeObject;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        String strCurrentName = jsonParser.currentName();
        while (strCurrentName != null) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (jsonTokenNextToken == null) {
                jsonTokenNextToken = JsonToken.NOT_AVAILABLE;
            }
            int iId = jsonTokenNextToken.id();
            if (iId == 1) {
                jsonNodeDeserializeObject = deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (iId == 3) {
                jsonNodeDeserializeObject = deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (iId == 6) {
                jsonNodeDeserializeObject = jsonNodeFactory.textNode(jsonParser.getText());
            } else if (iId == 7) {
                jsonNodeDeserializeObject = _fromInt(jsonParser, deserializationContext, jsonNodeFactory);
            } else {
                switch (iId) {
                    case 9:
                        jsonNodeDeserializeObject = jsonNodeFactory.booleanNode(true);
                        break;
                    case 10:
                        jsonNodeDeserializeObject = jsonNodeFactory.booleanNode(false);
                        break;
                    case 11:
                        jsonNodeDeserializeObject = jsonNodeFactory.nullNode();
                        break;
                    case 12:
                        jsonNodeDeserializeObject = _fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                    default:
                        jsonNodeDeserializeObject = deserializeAny(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                }
            }
            JsonNode jsonNode = jsonNodeDeserializeObject;
            JsonNode jsonNodeReplace = objectNode.replace(strCurrentName, jsonNode);
            if (jsonNodeReplace != null) {
                _handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory, strCurrentName, objectNode, jsonNodeReplace, jsonNode);
            }
            strCurrentName = jsonParser.nextFieldName();
        }
        return objectNode;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final com.fasterxml.jackson.databind.JsonNode updateObject(com.fasterxml.jackson.core.JsonParser r6, com.fasterxml.jackson.databind.DeserializationContext r7, com.fasterxml.jackson.databind.node.ObjectNode r8) throws java.io.IOException {
        /*
            r5 = this;
            boolean r0 = r6.isExpectedStartObjectToken()
            if (r0 == 0) goto Lb
            java.lang.String r0 = r6.nextFieldName()
            goto L1e
        Lb:
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.FIELD_NAME
            boolean r0 = r6.hasToken(r0)
            if (r0 != 0) goto L1a
            java.lang.Object r6 = r5.deserialize(r6, r7)
            com.fasterxml.jackson.databind.JsonNode r6 = (com.fasterxml.jackson.databind.JsonNode) r6
            return r6
        L1a:
            java.lang.String r0 = r6.currentName()
        L1e:
            if (r0 == 0) goto Laa
            com.fasterxml.jackson.core.JsonToken r1 = r6.nextToken()
            com.fasterxml.jackson.databind.JsonNode r2 = r8.get(r0)
            if (r2 == 0) goto L55
            boolean r3 = r2 instanceof com.fasterxml.jackson.databind.node.ObjectNode
            if (r3 == 0) goto L40
            com.fasterxml.jackson.core.JsonToken r3 = com.fasterxml.jackson.core.JsonToken.START_OBJECT
            if (r1 != r3) goto L55
            r1 = r2
            com.fasterxml.jackson.databind.node.ObjectNode r1 = (com.fasterxml.jackson.databind.node.ObjectNode) r1
            com.fasterxml.jackson.databind.JsonNode r1 = r5.updateObject(r6, r7, r1)
            if (r1 == r2) goto La4
            r8.set(r0, r1)
            goto La4
        L40:
            boolean r3 = r2 instanceof com.fasterxml.jackson.databind.node.ArrayNode
            if (r3 == 0) goto L55
            com.fasterxml.jackson.core.JsonToken r3 = com.fasterxml.jackson.core.JsonToken.START_ARRAY
            if (r1 != r3) goto L55
            r1 = r2
            com.fasterxml.jackson.databind.node.ArrayNode r1 = (com.fasterxml.jackson.databind.node.ArrayNode) r1
            com.fasterxml.jackson.databind.JsonNode r1 = r5.updateArray(r6, r7, r1)
            if (r1 == r2) goto La4
            r8.set(r0, r1)
            goto La4
        L55:
            if (r1 != 0) goto L59
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE
        L59:
            com.fasterxml.jackson.databind.node.JsonNodeFactory r2 = r7.getNodeFactory()
            int r1 = r1.id()
            r3 = 1
            if (r1 == r3) goto L9d
            r4 = 3
            if (r1 == r4) goto L98
            r4 = 6
            if (r1 == r4) goto L8f
            r4 = 7
            if (r1 == r4) goto L8a
            switch(r1) {
                case 9: goto L85;
                case 10: goto L7f;
                case 11: goto L7a;
                case 12: goto L75;
                default: goto L70;
            }
        L70:
            com.fasterxml.jackson.databind.JsonNode r1 = r5.deserializeAny(r6, r7, r2)
            goto La1
        L75:
            com.fasterxml.jackson.databind.JsonNode r1 = r5._fromEmbedded(r6, r7, r2)
            goto La1
        L7a:
            com.fasterxml.jackson.databind.node.NullNode r1 = r2.nullNode()
            goto La1
        L7f:
            r1 = 0
            com.fasterxml.jackson.databind.node.BooleanNode r1 = r2.booleanNode(r1)
            goto La1
        L85:
            com.fasterxml.jackson.databind.node.BooleanNode r1 = r2.booleanNode(r3)
            goto La1
        L8a:
            com.fasterxml.jackson.databind.JsonNode r1 = r5._fromInt(r6, r7, r2)
            goto La1
        L8f:
            java.lang.String r1 = r6.getText()
            com.fasterxml.jackson.databind.node.TextNode r1 = r2.textNode(r1)
            goto La1
        L98:
            com.fasterxml.jackson.databind.node.ArrayNode r1 = r5.deserializeArray(r6, r7, r2)
            goto La1
        L9d:
            com.fasterxml.jackson.databind.node.ObjectNode r1 = r5.deserializeObject(r6, r7, r2)
        La1:
            r8.set(r0, r1)
        La4:
            java.lang.String r0 = r6.nextFieldName()
            goto L1e
        Laa:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer.updateObject(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.node.ObjectNode):com.fasterxml.jackson.databind.JsonNode");
    }

    protected final ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        while (true) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (jsonTokenNextToken == null) {
                return arrayNode;
            }
            switch (jsonTokenNextToken.id()) {
                case 1:
                    arrayNode.add(deserializeObject(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case 2:
                case 5:
                case 8:
                default:
                    arrayNode.add(deserializeAny(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case 3:
                    arrayNode.add(deserializeArray(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case 4:
                    return arrayNode;
                case 6:
                    arrayNode.add(jsonNodeFactory.textNode(jsonParser.getText()));
                    break;
                case 7:
                    arrayNode.add(_fromInt(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case 9:
                    arrayNode.add(jsonNodeFactory.booleanNode(true));
                    break;
                case 10:
                    arrayNode.add(jsonNodeFactory.booleanNode(false));
                    break;
                case 11:
                    arrayNode.add(jsonNodeFactory.nullNode());
                    break;
                case 12:
                    arrayNode.add(_fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
            }
        }
    }

    protected final JsonNode updateArray(JsonParser jsonParser, DeserializationContext deserializationContext, ArrayNode arrayNode) throws IOException {
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        while (true) {
            switch (jsonParser.nextToken().id()) {
                case 1:
                    arrayNode.add(deserializeObject(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 2:
                case 5:
                case 8:
                default:
                    arrayNode.add(deserializeAny(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 3:
                    arrayNode.add(deserializeArray(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 4:
                    return arrayNode;
                case 6:
                    arrayNode.add(nodeFactory.textNode(jsonParser.getText()));
                    break;
                case 7:
                    arrayNode.add(_fromInt(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 9:
                    arrayNode.add(nodeFactory.booleanNode(true));
                    break;
                case 10:
                    arrayNode.add(nodeFactory.booleanNode(false));
                    break;
                case 11:
                    arrayNode.add(nodeFactory.nullNode());
                    break;
                case 12:
                    arrayNode.add(_fromEmbedded(jsonParser, deserializationContext, nodeFactory));
                    break;
            }
        }
    }

    protected final JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        int iCurrentTokenId = jsonParser.currentTokenId();
        if (iCurrentTokenId == 2) {
            return jsonNodeFactory.objectNode();
        }
        switch (iCurrentTokenId) {
            case 5:
                return deserializeObjectAtName(jsonParser, deserializationContext, jsonNodeFactory);
            case 6:
                return jsonNodeFactory.textNode(jsonParser.getText());
            case 7:
                return _fromInt(jsonParser, deserializationContext, jsonNodeFactory);
            case 8:
                return _fromFloat(jsonParser, deserializationContext, jsonNodeFactory);
            case 9:
                return jsonNodeFactory.booleanNode(true);
            case 10:
                return jsonNodeFactory.booleanNode(false);
            case 11:
                return jsonNodeFactory.nullNode();
            case 12:
                return _fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory);
            default:
                return (JsonNode) deserializationContext.handleUnexpectedToken(handledType(), jsonParser);
        }
    }

    protected final JsonNode _fromInt(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonParser.NumberType numberType;
        int deserializationFeatures = deserializationContext.getDeserializationFeatures();
        if ((StdDeserializer.F_MASK_INT_COERCIONS & deserializationFeatures) != 0) {
            if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(deserializationFeatures)) {
                numberType = JsonParser.NumberType.BIG_INTEGER;
            } else if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(deserializationFeatures)) {
                numberType = JsonParser.NumberType.LONG;
            } else {
                numberType = jsonParser.getNumberType();
            }
        } else {
            numberType = jsonParser.getNumberType();
        }
        if (numberType == JsonParser.NumberType.INT) {
            return jsonNodeFactory.numberNode(jsonParser.getIntValue());
        }
        if (numberType == JsonParser.NumberType.LONG) {
            return jsonNodeFactory.numberNode(jsonParser.getLongValue());
        }
        return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
    }

    protected final JsonNode _fromFloat(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonParser.NumberType numberType = jsonParser.getNumberType();
        if (numberType == JsonParser.NumberType.BIG_DECIMAL) {
            return jsonNodeFactory.numberNode(jsonParser.getDecimalValue());
        }
        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            if (jsonParser.isNaN()) {
                return jsonNodeFactory.numberNode(jsonParser.getDoubleValue());
            }
            return jsonNodeFactory.numberNode(jsonParser.getDecimalValue());
        }
        if (numberType == JsonParser.NumberType.FLOAT) {
            return jsonNodeFactory.numberNode(jsonParser.getFloatValue());
        }
        return jsonNodeFactory.numberNode(jsonParser.getDoubleValue());
    }

    protected final JsonNode _fromEmbedded(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        Object embeddedObject = jsonParser.getEmbeddedObject();
        if (embeddedObject == null) {
            return jsonNodeFactory.nullNode();
        }
        if (embeddedObject.getClass() == byte[].class) {
            return jsonNodeFactory.binaryNode((byte[]) embeddedObject);
        }
        if (embeddedObject instanceof RawValue) {
            return jsonNodeFactory.rawValueNode((RawValue) embeddedObject);
        }
        if (embeddedObject instanceof JsonNode) {
            return (JsonNode) embeddedObject;
        }
        return jsonNodeFactory.pojoNode(embeddedObject);
    }
}
