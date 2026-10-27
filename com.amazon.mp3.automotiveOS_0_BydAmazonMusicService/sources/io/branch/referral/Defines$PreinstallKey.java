package io.branch.referral;

/* JADX INFO: loaded from: classes7.dex */
public enum Defines$PreinstallKey {
    campaign("preinstall_campaign"),
    partner("preinstall_partner");

    private String key;

    Defines$PreinstallKey(String str) {
        this.key = str;
    }

    public String getKey() {
        return this.key;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.key;
    }
}
