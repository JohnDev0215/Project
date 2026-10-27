package SOACoreInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class CsrfSerializer extends JsonSerializer<Csrf> {
    public static final CsrfSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CsrfSerializer csrfSerializer = new CsrfSerializer();
        INSTANCE = csrfSerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.CsrfSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Csrf.class, csrfSerializer);
    }

    private CsrfSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Csrf csrf, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (csrf == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(csrf, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Csrf csrf, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("token");
        SimpleSerializers.serializeString(csrf.getToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("rnd");
        SimpleSerializers.serializeString(csrf.getRnd(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(HlsSegmentFormat.TS);
        SimpleSerializers.serializeString(csrf.getTs(), jsonGenerator, serializerProvider);
    }
}
