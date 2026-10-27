package org.objectweb.asm.commons;

import org.objectweb.asm.Label;

/* JADX INFO: loaded from: classes6.dex */
public interface TableSwitchGenerator {
    void generateCase(int i, Label label);

    void generateDefault();
}
