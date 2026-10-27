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
public class SpellCorrectionRequestSerializer extends JsonSerializer<SpellCorrectionRequest> {
    public static final SpellCorrectionRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SpellCorrectionRequestSerializer spellCorrectionRequestSerializer = new SpellCorrectionRequestSerializer();
        INSTANCE = spellCorrectionRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.SpellCorrectionRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SpellCorrectionRequest.class, spellCorrectionRequestSerializer);
    }

    private SpellCorrectionRequestSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SpellCorrectionRequest spellCorrectionRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (spellCorrectionRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(spellCorrectionRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SpellCorrectionRequest spellCorrectionRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("allowCorrection");
        SimpleSerializers.serializeBoolean(spellCorrectionRequest.isAllowCorrection(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("rejected");
        SpellCorrectionFeedbackSerializer spellCorrectionFeedbackSerializer = SpellCorrectionFeedbackSerializer.INSTANCE;
        spellCorrectionFeedbackSerializer.serialize(spellCorrectionRequest.getRejected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("accepted");
        spellCorrectionFeedbackSerializer.serialize(spellCorrectionRequest.getAccepted(), jsonGenerator, serializerProvider);
    }
}
