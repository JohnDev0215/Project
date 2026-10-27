package com.spotify.connectivity.productstate;

import p.ng2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class BooleanProductStateValueConverter {
    public static final BooleanProductStateValueConverter INSTANCE = new BooleanProductStateValueConverter();

    private BooleanProductStateValueConverter() {
    }

    public static final boolean convert(String str) throws ConvertProductStateValueException {
        if (v03.y(str)) {
            v03.i(Boolean.FALSE, "FALSE");
            return false;
        }
        if (ng2.b0(str, "true", true) || v03.b("1", str)) {
            v03.i(Boolean.TRUE, "TRUE");
            return true;
        }
        if (ng2.b0(str, "false", true) || v03.b("0", str)) {
            v03.i(Boolean.FALSE, "FALSE");
            return false;
        }
        v03.h(str);
        throw new ConvertProductStateValueException(Boolean.class, str);
    }

    public static final boolean convert(String str, boolean z) {
        if (v03.y(str)) {
            return z;
        }
        if (!ng2.b0(str, "true", true) && !v03.b("1", str)) {
            if (!ng2.b0(str, "false", true) && !v03.b("0", str)) {
                return z;
            }
            v03.i(Boolean.FALSE, "FALSE");
            return false;
        }
        v03.i(Boolean.TRUE, "TRUE");
        return true;
    }
}
