package net.bytebuddy.utility.privilege;

import java.security.PrivilegedAction;

/* JADX INFO: loaded from: classes3.dex */
public class GetSystemPropertyAction implements PrivilegedAction<String> {
    private final String key;

    protected boolean canEqual(Object obj) {
        return obj instanceof GetSystemPropertyAction;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetSystemPropertyAction)) {
            return false;
        }
        GetSystemPropertyAction getSystemPropertyAction = (GetSystemPropertyAction) obj;
        if (!getSystemPropertyAction.canEqual(this)) {
            return false;
        }
        String str = this.key;
        String str2 = getSystemPropertyAction.key;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public int hashCode() {
        String str = this.key;
        return 59 + (str == null ? 43 : str.hashCode());
    }

    public GetSystemPropertyAction(String str) {
        this.key = str;
    }

    @Override // java.security.PrivilegedAction
    public String run() {
        return System.getProperty(this.key);
    }
}
