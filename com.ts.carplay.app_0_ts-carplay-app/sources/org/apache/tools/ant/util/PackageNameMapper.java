package org.apache.tools.ant.util;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class PackageNameMapper extends GlobPatternMapper {
    @Override // org.apache.tools.ant.util.GlobPatternMapper
    protected String extractVariablePart(String name) {
        String var = name.substring(this.prefixLength, name.length() - this.postfixLength);
        return var.replace(File.separatorChar, '.');
    }
}
