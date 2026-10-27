package javax.xml.bind;

import java.security.PrivilegedAction;

/* JADX INFO: loaded from: classes3.dex */
final class GetPropertyAction implements PrivilegedAction<String> {
    private final String propertyName;

    public GetPropertyAction(String str) {
        this.propertyName = str;
    }

    @Override // java.security.PrivilegedAction
    public String run() {
        return System.getProperty(this.propertyName);
    }
}
