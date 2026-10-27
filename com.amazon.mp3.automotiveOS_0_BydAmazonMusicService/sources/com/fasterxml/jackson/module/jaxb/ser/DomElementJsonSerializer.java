package com.fasterxml.jackson.module.jaxb.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.lang.reflect.Type;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public class DomElementJsonSerializer extends StdSerializer<Element> {
    private static final long serialVersionUID = 1;

    public DomElementJsonSerializer() {
        super(Element.class);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(Element element, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", element.getTagName());
        if (element.getNamespaceURI() != null) {
            jsonGenerator.writeStringField("namespace", element.getNamespaceURI());
        }
        NamedNodeMap attributes = element.getAttributes();
        if (attributes != null && attributes.getLength() > 0) {
            jsonGenerator.writeArrayFieldStart("attributes");
            for (int i = 0; i < attributes.getLength(); i++) {
                Attr attr = (Attr) attributes.item(i);
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("$", attr.getValue());
                jsonGenerator.writeStringField("name", attr.getName());
                String namespaceURI = attr.getNamespaceURI();
                if (namespaceURI != null) {
                    jsonGenerator.writeStringField("namespace", namespaceURI);
                }
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
        NodeList childNodes = element.getChildNodes();
        if (childNodes != null && childNodes.getLength() > 0) {
            jsonGenerator.writeArrayFieldStart("children");
            for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                Node nodeItem = childNodes.item(i2);
                short nodeType = nodeItem.getNodeType();
                if (nodeType == 1) {
                    serialize((Element) nodeItem, jsonGenerator, serializerProvider);
                } else if (nodeType == 3 || nodeType == 4) {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("$", nodeItem.getNodeValue());
                    jsonGenerator.writeEndObject();
                }
            }
            jsonGenerator.writeEndArray();
        }
        jsonGenerator.writeEndObject();
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        if (jsonFormatVisitorWrapper != null) {
            jsonFormatVisitorWrapper.expectStringFormat(javaType);
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.jsonschema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        return createSchemaNode("string", true);
    }
}
