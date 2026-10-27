package com.byd.systemsettings.wifi2.model;

/* JADX INFO: loaded from: classes2.dex */
public class WifiSecurity {
    private boolean checked;
    private String name;
    private CipherType type;

    public WifiSecurity() {
    }

    public String getName() {
        return this.name;
    }

    public CipherType getType() {
        return this.type;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z7) {
        this.checked = z7;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setType(CipherType cipherType) {
        this.type = cipherType;
    }

    public WifiSecurity(String str, CipherType cipherType) {
        this.name = str;
        this.type = cipherType;
    }
}
