package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class SpellCorrectionFeedbackSerializer extends JsonSerializer<SpellCorrectionFeedback> {
    public static final SpellCorrectionFeedbackSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SpellCorrectionFeedbackSerializer spellCorrectionFeedbackSerializer = new SpellCorrectionFeedbackSerializer();
        INSTANCE = spellCorrectionFeedbackSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.SpellCorrectionFeedbackSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SpellCorrectionFeedback.class, spellCorrectionFeedbackSerializer);
    }

    private SpellCorrectionFeedbackSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SpellCorrectionFeedback spellCorrectionFeedback, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (spellCorrectionFeedback == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(spellCorrectionFeedback, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SpellCorrectionFeedback spellCorrectionFeedback, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(spellCorrectionFeedback.getId(), jsonGenerator, serializerProvider);
    }
}
