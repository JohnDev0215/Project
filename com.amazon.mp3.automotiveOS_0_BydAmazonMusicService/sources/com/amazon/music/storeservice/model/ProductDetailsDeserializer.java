package com.amazon.music.storeservice.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class ProductDetailsDeserializer extends JsonDeserializer<ProductDetails> {
    public static final ProductDetailsDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ProductDetailsDeserializer productDetailsDeserializer = new ProductDetailsDeserializer();
        INSTANCE = productDetailsDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.ProductDetailsDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(ProductDetails.class, productDetailsDeserializer);
    }

    private ProductDetailsDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public ProductDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of object, got " + currentToken, jsonParser.getTokenLocation());
        }
        if (jsonParser.nextToken() == JsonToken.FIELD_NAME && "__type".equals(jsonParser.getCurrentName())) {
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            String text = jsonParser.getText();
            jsonParser.nextToken();
            if ("com.amazon.music.storeservice.model#ProductDetails".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public ProductDetails deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ProductDetails productDetails = new ProductDetails();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("merchantName".equals(currentName)) {
                productDetails.setMerchantName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                productDetails.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("sellerOfRecordId".equals(currentName)) {
                productDetails.setSellerOfRecordId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("merchantId".equals(currentName)) {
                productDetails.setMerchantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("primaryGenreName".equals(currentName)) {
                productDetails.setPrimaryGenreName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("copyright".equals(currentName)) {
                productDetails.setCopyright(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("primaryGenre".equals(currentName)) {
                productDetails.setPrimaryGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return productDetails;
    }
}
