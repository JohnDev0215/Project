package com.fasterxml.jackson.module.jaxb.ser;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import javax.activation.DataHandler;

/* JADX INFO: loaded from: classes6.dex */
public class DataHandlerJsonSerializer extends StdSerializer<DataHandler> {
    private static final long serialVersionUID = 1;

    public DataHandlerJsonSerializer() {
        super(DataHandler.class);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(DataHandler dataHandler, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        InputStream inputStream = dataHandler.getInputStream();
        for (int i = inputStream.read(bArr); i > 0; i = inputStream.read(bArr)) {
            byteArrayOutputStream.write(bArr, 0, i);
        }
        inputStream.close();
        jsonGenerator.writeBinary(byteArrayOutputStream.toByteArray());
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonArrayFormatVisitor jsonArrayFormatVisitorExpectArrayFormat;
        if (jsonFormatVisitorWrapper == null || (jsonArrayFormatVisitorExpectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType)) == null) {
            return;
        }
        jsonArrayFormatVisitorExpectArrayFormat.itemsFormat(JsonFormatTypes.STRING);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.jsonschema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode objectNodeCreateSchemaNode = createSchemaNode("array", true);
        objectNodeCreateSchemaNode.set(ContextMappingConstants.ITEMS, createSchemaNode("string"));
        return objectNodeCreateSchemaNode;
    }
}
