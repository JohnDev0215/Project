package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

/* JADX INFO: loaded from: classes3.dex */
public enum MethodManifestation implements ModifierContributor.ForMethod {
    PLAIN(0),
    NATIVE(256),
    ABSTRACT(1024),
    FINAL(16),
    FINAL_NATIVE(272),
    BRIDGE(64),
    FINAL_BRIDGE(80);

    private final int mask;

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public int getRange() {
        return 1360;
    }

    MethodManifestation(int i) {
        this.mask = i;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public int getMask() {
        return this.mask;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public boolean isDefault() {
        return this == PLAIN;
    }

    public boolean isNative() {
        return (this.mask & 256) != 0;
    }

    public boolean isAbstract() {
        return (this.mask & 1024) != 0;
    }

    public boolean isFinal() {
        return (this.mask & 16) != 0;
    }

    public boolean isBridge() {
        return (this.mask & 64) != 0;
    }
}
