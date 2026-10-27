package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.jar.asm.Label;

/* JADX INFO: loaded from: classes3.dex */
public interface TableSwitchGenerator {
    void generateCase(int i, Label label);

    void generateDefault();
}
