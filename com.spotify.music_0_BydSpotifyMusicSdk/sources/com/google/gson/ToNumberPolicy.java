package com.google.gson;

import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.math.BigDecimal;
import p.am2;
import p.t71;
import p.u4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public abstract class ToNumberPolicy implements am2 {
    public static final ToNumberPolicy c;
    public static final ToNumberPolicy e;
    public static final /* synthetic */ ToNumberPolicy[] k;

    static {
        ToNumberPolicy toNumberPolicy = new ToNumberPolicy() { // from class: com.google.gson.ToNumberPolicy.1
            @Override // p.am2
            public final Number a(t71 t71Var) {
                return Double.valueOf(t71Var.T());
            }
        };
        c = toNumberPolicy;
        ToNumberPolicy toNumberPolicy2 = new ToNumberPolicy() { // from class: com.google.gson.ToNumberPolicy.2
            @Override // p.am2
            public final Number a(t71 t71Var) {
                return new LazilyParsedNumber(t71Var.u());
            }
        };
        e = toNumberPolicy2;
        k = new ToNumberPolicy[]{toNumberPolicy, toNumberPolicy2, new ToNumberPolicy() { // from class: com.google.gson.ToNumberPolicy.3
            @Override // p.am2
            public final Number a(t71 t71Var) throws IOException {
                String strU = t71Var.u();
                try {
                    try {
                        return Long.valueOf(Long.parseLong(strU));
                    } catch (NumberFormatException e2) {
                        StringBuilder sbP = u4.p("Cannot parse ", strU, "; at path ");
                        sbP.append(t71Var.o0());
                        throw new JsonParseException(sbP.toString(), e2);
                    }
                } catch (NumberFormatException unused) {
                    Double dValueOf = Double.valueOf(strU);
                    if (dValueOf.isInfinite() || dValueOf.isNaN()) {
                        if (!t71Var.e) {
                            throw new MalformedJsonException("JSON forbids NaN and infinities: " + dValueOf + "; at path " + t71Var.o0());
                        }
                    }
                    return dValueOf;
                }
            }
        }, new ToNumberPolicy() { // from class: com.google.gson.ToNumberPolicy.4
            @Override // p.am2
            public final Number a(t71 t71Var) throws IOException {
                String strU = t71Var.u();
                try {
                    return new BigDecimal(strU);
                } catch (NumberFormatException e2) {
                    StringBuilder sbP = u4.p("Cannot parse ", strU, "; at path ");
                    sbP.append(t71Var.o0());
                    throw new JsonParseException(sbP.toString(), e2);
                }
            }
        }};
    }

    public static ToNumberPolicy valueOf(String str) {
        return (ToNumberPolicy) Enum.valueOf(ToNumberPolicy.class, str);
    }

    public static ToNumberPolicy[] values() {
        return (ToNumberPolicy[]) k.clone();
    }
}
