package j8;

import p8.InterfaceC2140o;

/* JADX INFO: renamed from: j8.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1564i implements InterfaceC2140o {
    CLASS(0),
    INTERFACE(1),
    ENUM_CLASS(2),
    /* JADX INFO: Fake field, exist only in values array */
    ENUM_ENTRY(3),
    ANNOTATION_CLASS(4),
    /* JADX INFO: Fake field, exist only in values array */
    OBJECT(5),
    COMPANION_OBJECT(6);

    public final int l;

    EnumC1564i(int i7) {
        this.l = i7;
    }

    @Override // p8.InterfaceC2140o
    public final int a() {
        return this.l;
    }
}
