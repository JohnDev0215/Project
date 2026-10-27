package net.bytebuddy.dynamic.scaffold;

/* JADX INFO: loaded from: classes3.dex */
public enum TypeValidation {
    ENABLED(true),
    DISABLED(false);

    private final boolean enabled;

    TypeValidation(boolean z) {
        this.enabled = z;
    }

    public static TypeValidation of(boolean z) {
        return z ? ENABLED : DISABLED;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
