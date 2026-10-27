package com.spotify.connectivity.flags;

import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.ng2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class IntegerFlag extends Flag<Integer> {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public IntegerFlag(String str, Source source, Overridable overridable) {
        this(str, source, overridable, 0, 8, null);
        v03.j(str, "identifier");
        v03.j(source, "source");
        v03.j(overridable, "overridable");
    }

    public boolean isEnabled(int i) {
        return i != 0;
    }

    public /* synthetic */ IntegerFlag(String str, Source source, Overridable overridable, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, source, overridable, (i2 & 8) != 0 ? 0 : i);
    }

    @Override // com.spotify.connectivity.flags.Flag
    public Integer getDisabledValue() {
        return 0;
    }

    @Override // com.spotify.connectivity.flags.Flag
    public Integer getEnabledValue() {
        return 1;
    }

    @Override // com.spotify.connectivity.flags.Flag
    public /* bridge */ /* synthetic */ boolean isEnabled(Serializable serializable) {
        return isEnabled(((Number) serializable).intValue());
    }

    @Override // com.spotify.connectivity.flags.Flag
    public Integer mapValue(String str) throws UnmappableValueException {
        int iIntValue;
        v03.j(str, "value");
        if (ng2.b0("control", str, true)) {
            Integer numValueOf = Integer.valueOf(getDefaultValue());
            v03.i(numValueOf, "{\n            Integer.va…f(defaultValue)\n        }");
            iIntValue = numValueOf.intValue();
        } else {
            try {
                Integer numValueOf2 = Integer.valueOf(str);
                v03.i(numValueOf2, "{\n            try {\n    …)\n            }\n        }");
                iIntValue = numValueOf2.intValue();
            } catch (NumberFormatException e) {
                throw new UnmappableValueException(this, str, e);
            }
        }
        return Integer.valueOf(iIntValue);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IntegerFlag(String str, Source source, Overridable overridable, int i) {
        super(Integer.TYPE, str, source, overridable, String.valueOf(i));
        v03.j(str, "identifier");
        v03.j(source, "source");
        v03.j(overridable, "overridable");
    }
}
