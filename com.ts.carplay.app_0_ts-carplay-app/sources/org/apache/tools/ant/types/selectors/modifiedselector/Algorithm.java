package org.apache.tools.ant.types.selectors.modifiedselector;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public interface Algorithm {
    String getValue(File file);

    boolean isValid();
}
