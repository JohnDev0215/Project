package Podcast.RefinementOptionsInterface.v1_0;

import SOACoreInterface.v1_0.MethodsDeserializer;
import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class CheckBoxRefinementOptionElementDeserializer extends JsonDeserializer<CheckBoxRefinementOptionElement> {
    public static final CheckBoxRefinementOptionElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CheckBoxRefinementOptionElementDeserializer checkBoxRefinementOptionElementDeserializer = new CheckBoxRefinementOptionElementDeserializer();
        INSTANCE = checkBoxRefinementOptionElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CheckBoxRefinementOptionElement.class, checkBoxRefinementOptionElementDeserializer);
    }

    private CheckBoxRefinementOptionElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CheckBoxRefinementOptionElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.RefinementOptionsInterface.v1_0#CheckBoxRefinementOptionElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CheckBoxRefinementOptionElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CheckBoxRefinementOptionElement checkBoxRefinementOptionElement = new CheckBoxRefinementOptionElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("options".equals(currentName)) {
                checkBoxRefinementOptionElement.setOptions(CheckBoxRefinementOptionItemElementsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("defaultState".equals(currentName)) {
                checkBoxRefinementOptionElement.setDefaultState(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                checkBoxRefinementOptionElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("text".equals(currentName)) {
                checkBoxRefinementOptionElement.setText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("accessibilityLabel".equals(currentName)) {
                checkBoxRefinementOptionElement.setAccessibilityLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onReset".equals(currentName)) {
                checkBoxRefinementOptionElement.setOnReset(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onItemSelected".equals(currentName)) {
                checkBoxRefinementOptionElement.setOnItemSelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onDismiss".equals(currentName)) {
                checkBoxRefinementOptionElement.setOnDismiss(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onApply".equals(currentName)) {
                checkBoxRefinementOptionElement.setOnApply(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return checkBoxRefinementOptionElement;
    }
}
