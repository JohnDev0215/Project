package com.fasterxml.jackson.datatype.joda;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.util.VersionUtil;

/* JADX INFO: loaded from: classes6.dex */
public final class PackageVersion implements Versioned {
    public static final Version VERSION = VersionUtil.parseVersion("2.12.7", "com.fasterxml.jackson.datatype", "jackson-datatype-joda");

    @Override // com.fasterxml.jackson.core.Versioned
    public Version version() {
        return VERSION;
    }
}
