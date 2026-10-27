package com.fasterxml.jackson.module.jaxb.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;

/* JADX INFO: loaded from: classes6.dex */
public class DataHandlerJsonDeserializer extends StdScalarDeserializer<DataHandler> {
    private static final long serialVersionUID = 1;

    public DataHandlerJsonDeserializer() {
        super((Class<?>) DataHandler.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public DataHandler deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final byte[] binaryValue = jsonParser.getBinaryValue();
        return new DataHandler(new DataSource() { // from class: com.fasterxml.jackson.module.jaxb.deser.DataHandlerJsonDeserializer.1
            @Override // javax.activation.DataSource
            public String getContentType() {
                return "application/octet-stream";
            }

            public String getName() {
                return "json-binary-data";
            }

            @Override // javax.activation.DataSource
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(binaryValue);
            }

            public OutputStream getOutputStream() throws IOException {
                throw new IOException();
            }
        });
    }
}
