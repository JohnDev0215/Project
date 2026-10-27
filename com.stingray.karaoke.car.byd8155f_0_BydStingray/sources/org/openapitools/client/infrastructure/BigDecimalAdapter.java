package org.openapitools.client.infrastructure;

import A7.m;
import E5.InterfaceC0189n;
import E5.N;
import java.math.BigDecimal;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/openapitools/client/infrastructure/BigDecimalAdapter;", "", "Ljava/math/BigDecimal;", "value", "", "toJson", "(Ljava/math/BigDecimal;)Ljava/lang/String;", "fromJson", "(Ljava/lang/String;)Ljava/math/BigDecimal;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BigDecimalAdapter {
    @InterfaceC0189n
    public final BigDecimal fromJson(String value) {
        m.f(value, "value");
        return new BigDecimal(value);
    }

    @N
    public final String toJson(BigDecimal value) {
        m.f(value, "value");
        String plainString = value.toPlainString();
        m.e(plainString, "toPlainString(...)");
        return plainString;
    }
}
