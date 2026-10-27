package io.sentry.protocol;

import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class DebugImage implements InterfaceC1485m0 {
    public static final String JVM = "jvm";
    public static final String PROGUARD = "proguard";
    private String arch;
    private String codeFile;
    private String codeId;
    private String debugFile;
    private String debugId;
    private String imageAddr;
    private Long imageSize;
    private String type;
    private Map<String, Object> unknown;
    private String uuid;

    public String getArch() {
        return this.arch;
    }

    public String getCodeFile() {
        return this.codeFile;
    }

    public String getCodeId() {
        return this.codeId;
    }

    public String getDebugFile() {
        return this.debugFile;
    }

    public String getDebugId() {
        return this.debugId;
    }

    public String getImageAddr() {
        return this.imageAddr;
    }

    public Long getImageSize() {
        return this.imageSize;
    }

    public String getType() {
        return this.type;
    }

    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public String getUuid() {
        return this.uuid;
    }

    @Override // io.sentry.InterfaceC1485m0
    public void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.uuid != null) {
            c1489n1.w("uuid");
            c1489n1.L(this.uuid);
        }
        if (this.type != null) {
            c1489n1.w("type");
            c1489n1.L(this.type);
        }
        if (this.debugId != null) {
            c1489n1.w("debug_id");
            c1489n1.L(this.debugId);
        }
        if (this.debugFile != null) {
            c1489n1.w("debug_file");
            c1489n1.L(this.debugFile);
        }
        if (this.codeId != null) {
            c1489n1.w("code_id");
            c1489n1.L(this.codeId);
        }
        if (this.codeFile != null) {
            c1489n1.w("code_file");
            c1489n1.L(this.codeFile);
        }
        if (this.imageAddr != null) {
            c1489n1.w("image_addr");
            c1489n1.L(this.imageAddr);
        }
        if (this.imageSize != null) {
            c1489n1.w("image_size");
            c1489n1.K(this.imageSize);
        }
        if (this.arch != null) {
            c1489n1.w("arch");
            c1489n1.L(this.arch);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = this.unknown.get(str);
                c1489n1.w(str);
                c1489n1.I(k10, obj);
            }
        }
        c1489n1.n();
    }

    public void setArch(String str) {
        this.arch = str;
    }

    public void setCodeFile(String str) {
        this.codeFile = str;
    }

    public void setCodeId(String str) {
        this.codeId = str;
    }

    public void setDebugFile(String str) {
        this.debugFile = str;
    }

    public void setDebugId(String str) {
        this.debugId = str;
    }

    public void setImageAddr(String str) {
        this.imageAddr = str;
    }

    public void setImageSize(Long l) {
        this.imageSize = l;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public void setImageSize(long j3) {
        this.imageSize = Long.valueOf(j3);
    }
}
