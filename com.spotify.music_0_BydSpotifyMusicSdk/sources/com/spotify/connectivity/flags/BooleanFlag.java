package com.spotify.connectivity.flags;

import java.io.Serializable;
import p.ng2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class BooleanFlag extends Flag<Boolean> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BooleanFlag(String str, Source source, Overridable overridable) {
        super(Boolean.TYPE, str, source, overridable, "false");
        v03.j(str, "identifier");
        v03.j(source, "source");
        v03.j(overridable, "overridable");
    }

    @Override // com.spotify.connectivity.flags.Flag
    public Boolean getDisabledValue() {
        return Boolean.FALSE;
    }

    @Override // com.spotify.connectivity.flags.Flag
    public Boolean getEnabledValue() {
        return Boolean.TRUE;
    }

    public boolean isEnabled(boolean z) {
        return z;
    }

    public boolean mapToBoolean(int i) {
        return i != 0;
    }

    @Override // com.spotify.connectivity.flags.Flag
    public /* bridge */ /* synthetic */ boolean isEnabled(Serializable serializable) {
        return isEnabled(((Boolean) serializable).booleanValue());
    }

    @Override // com.spotify.connectivity.flags.Flag
    public Boolean mapValue(String str) throws UnmappableValueException {
        v03.j(str, "value");
        boolean zMapToBoolean = false;
        if (v03.y(str)) {
            v03.i(Boolean.FALSE, "{\n            /* The rec…g.Boolean.FALSE\n        }");
        } else {
            if (v03.b("true", str)) {
                v03.i(Boolean.TRUE, "{\n            java.lang.Boolean.TRUE\n        }");
            } else if (v03.b("false", str) || ng2.b0(str, "control", true)) {
                v03.i(Boolean.FALSE, "{\n            java.lang.Boolean.FALSE\n        }");
            } else if (ng2.b0(str, "enabled", true)) {
                v03.i(Boolean.TRUE, "{\n            java.lang.Boolean.TRUE\n        }");
            } else {
                try {
                    zMapToBoolean = mapToBoolean(Integer.parseInt(str));
                } catch (NumberFormatException e) {
                    throw new UnmappableValueException(this, str, e);
                }
            }
            zMapToBoolean = true;
        }
        return Boolean.valueOf(zMapToBoolean);
    }
}
