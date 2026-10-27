package Podcast.RefinementOptionsInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class CheckBoxRefinementOptionElementSerializer extends JsonSerializer<CheckBoxRefinementOptionElement> {
    public static final CheckBoxRefinementOptionElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CheckBoxRefinementOptionElementSerializer checkBoxRefinementOptionElementSerializer = new CheckBoxRefinementOptionElementSerializer();
        INSTANCE = checkBoxRefinementOptionElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CheckBoxRefinementOptionElement.class, checkBoxRefinementOptionElementSerializer);
    }

    private CheckBoxRefinementOptionElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CheckBoxRefinementOptionElement checkBoxRefinementOptionElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (checkBoxRefinementOptionElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(checkBoxRefinementOptionElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CheckBoxRefinementOptionElement checkBoxRefinementOptionElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("options");
        CheckBoxRefinementOptionItemElementsSerializer.INSTANCE.serialize(checkBoxRefinementOptionElement.getOptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("defaultState");
        SimpleSerializers.serializeString(checkBoxRefinementOptionElement.getDefaultState(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(checkBoxRefinementOptionElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("text");
        SimpleSerializers.serializeString(checkBoxRefinementOptionElement.getText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("accessibilityLabel");
        SimpleSerializers.serializeString(checkBoxRefinementOptionElement.getAccessibilityLabel(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onReset");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(checkBoxRefinementOptionElement.getOnReset(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onItemSelected");
        jsonSerializer.serialize(checkBoxRefinementOptionElement.getOnItemSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onDismiss");
        jsonSerializer.serialize(checkBoxRefinementOptionElement.getOnDismiss(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onApply");
        jsonSerializer.serialize(checkBoxRefinementOptionElement.getOnApply(), jsonGenerator, serializerProvider);
    }
}
