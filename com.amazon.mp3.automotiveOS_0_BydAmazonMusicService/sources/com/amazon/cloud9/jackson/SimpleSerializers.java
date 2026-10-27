package com.amazon.cloud9.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleSerializers {
    public static final void serializeString(@CheckForNull String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (str == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(str);
        }
    }

    public static final void serializeInteger(@CheckForNull Integer num, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (num == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(num.intValue());
        }
    }

    public static final void serializeLong(@CheckForNull Long l, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (l == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(l.longValue());
        }
    }

    public static final void serializeFloat(@CheckForNull Float f, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (f == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(f.floatValue());
        }
    }

    public static final void serializeBoolean(@CheckForNull Boolean bool, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (bool == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeBoolean(bool.booleanValue());
        }
    }

    public static final void serializePrimitiveInt(@CheckForNull int i, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(i);
    }

    public static final void serializePrimitiveLong(@CheckForNull long j, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(j);
    }

    public static final void serializePrimitiveDouble(@CheckForNull double d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(d);
    }

    public static final void serializePrimitiveBoolean(@CheckForNull boolean z, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeBoolean(z);
    }

    public static final void serializeDate(@CheckForNull Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (date == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(date.getTime() / 1000.0d);
        }
    }

    public static final void serializeBigDecimal(@CheckForNull BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (bigDecimal == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeNumber(bigDecimal);
        }
    }
}
