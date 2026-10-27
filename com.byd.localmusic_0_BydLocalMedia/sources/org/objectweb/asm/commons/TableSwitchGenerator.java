package org.objectweb.asm.commons;

import org.objectweb.asm.Label;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:org/objectweb/asm/commons/TableSwitchGenerator.SCL.lombok */
public interface TableSwitchGenerator {
    void generateCase(int i, Label label);

    void generateDefault();
}
