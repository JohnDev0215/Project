package com.google.gson;

import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes.dex */
public enum ToNumberPolicy implements o {
    DOUBLE { // from class: com.google.gson.ToNumberPolicy.1
        @Override // com.google.gson.ToNumberPolicy, com.google.gson.o
        public Double readNumber(com.google.gson.stream.a aVar) throws IOException {
            return Double.valueOf(aVar.o());
        }
    },
    LAZILY_PARSED_NUMBER { // from class: com.google.gson.ToNumberPolicy.2
        @Override // com.google.gson.ToNumberPolicy, com.google.gson.o
        public Number readNumber(com.google.gson.stream.a aVar) throws IOException {
            return new LazilyParsedNumber(aVar.v());
        }
    },
    LONG_OR_DOUBLE { // from class: com.google.gson.ToNumberPolicy.3
        @Override // com.google.gson.ToNumberPolicy, com.google.gson.o
        public Number readNumber(com.google.gson.stream.a aVar) throws JsonParseException, IOException {
            String strV = aVar.v();
            try {
                try {
                    return Long.valueOf(Long.parseLong(strV));
                } catch (NumberFormatException unused) {
                    Double dValueOf = Double.valueOf(strV);
                    if ((!dValueOf.isInfinite() && !dValueOf.isNaN()) || aVar.k()) {
                        return dValueOf;
                    }
                    throw new MalformedJsonException("JSON forbids NaN and infinities: " + dValueOf + "; at path " + aVar.i());
                }
            } catch (NumberFormatException e2) {
                throw new JsonParseException("Cannot parse " + strV + "; at path " + aVar.i(), e2);
            }
        }
    },
    BIG_DECIMAL { // from class: com.google.gson.ToNumberPolicy.4
        @Override // com.google.gson.ToNumberPolicy, com.google.gson.o
        public BigDecimal readNumber(com.google.gson.stream.a aVar) throws IOException {
            String strV = aVar.v();
            try {
                return new BigDecimal(strV);
            } catch (NumberFormatException e2) {
                throw new JsonParseException("Cannot parse " + strV + "; at path " + aVar.i(), e2);
            }
        }
    };

    @Override // com.google.gson.o
    public abstract /* synthetic */ Number readNumber(com.google.gson.stream.a aVar) throws IOException;
}
