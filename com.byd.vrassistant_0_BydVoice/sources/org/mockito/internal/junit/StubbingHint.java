package org.mockito.internal.junit;

/* JADX INFO: loaded from: classes4.dex */
class StubbingHint {
    private final StringBuilder hint;

    StubbingHint(String str) {
        this.hint = new StringBuilder("[MockitoHint] ").append(str).append(" (see javadoc for MockitoHint):");
    }

    void appendLine(Object... objArr) {
        this.hint.append("\n[MockitoHint] ");
        for (Object obj : objArr) {
            this.hint.append(obj);
        }
    }

    public String toString() {
        return this.hint.toString() + "\n";
    }
}
