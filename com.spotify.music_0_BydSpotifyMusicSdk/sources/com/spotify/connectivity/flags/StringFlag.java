package com.spotify.connectivity.flags;

import p.ng2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class StringFlag extends Flag<String> {
    private final String enabledValue;

    private StringFlag(String str, Source source, Overridable overridable, String str2, String str3) {
        super(String.class, str, source, overridable, str2);
        this.enabledValue = str3;
    }

    @Override // com.spotify.connectivity.flags.Flag
    public String getDisabledValue() {
        return "";
    }

    @Override // com.spotify.connectivity.flags.Flag
    public String getEnabledValue() {
        return this.enabledValue;
    }

    @Override // com.spotify.connectivity.flags.Flag
    public String mapValue(String str) {
        v03.j(str, "value");
        return str;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StringFlag(String str, Source source, Overridable overridable) {
        this(str, source, overridable, "", "Enabled");
        v03.j(str, "identifier");
        v03.j(source, "source");
        v03.j(overridable, "overridable");
    }

    @Override // com.spotify.connectivity.flags.Flag
    public boolean isEnabled(String str) {
        v03.j(str, "value");
        return ng2.b0(this.enabledValue, str, true);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StringFlag(String str, Source source, Overridable overridable, String str2) {
        this(str, source, overridable, "", str2);
        v03.j(str, "identifier");
        v03.j(source, "source");
        v03.j(overridable, "overridable");
        v03.j(str2, "enabledValue");
    }
}
