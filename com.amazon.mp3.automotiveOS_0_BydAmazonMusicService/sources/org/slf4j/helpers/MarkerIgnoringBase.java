package org.slf4j.helpers;

/* JADX INFO: loaded from: classes6.dex */
public abstract class MarkerIgnoringBase extends NamedLoggerBase {
    @Override // org.slf4j.helpers.NamedLoggerBase, org.slf4j.Logger
    public /* bridge */ /* synthetic */ String getName() {
        return super.getName();
    }

    public String toString() {
        return getClass().getName() + "(" + getName() + ")";
    }
}
