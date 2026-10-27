package net.bytebuddy.implementation.attribute;

/* JADX INFO: loaded from: classes3.dex */
public enum AnnotationRetention {
    ENABLED(true),
    DISABLED(false);

    private final boolean enabled;

    AnnotationRetention(boolean z) {
        this.enabled = z;
    }

    public static AnnotationRetention of(boolean z) {
        return z ? ENABLED : DISABLED;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
