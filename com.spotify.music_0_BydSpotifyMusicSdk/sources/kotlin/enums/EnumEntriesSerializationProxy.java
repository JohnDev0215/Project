package kotlin.enums;

import java.io.Serializable;
import java.lang.Enum;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class EnumEntriesSerializationProxy<E extends Enum<E>> implements Serializable {
    private static final long serialVersionUID = 0;
    private final Class<E> c;

    public EnumEntriesSerializationProxy(Enum[] enumArr) {
        v03.j(enumArr, "entries");
        Class<E> cls = (Class<E>) enumArr.getClass().getComponentType();
        v03.h(cls);
        this.c = cls;
    }

    private final Object readResolve() {
        E[] enumConstants = this.c.getEnumConstants();
        v03.i(enumConstants, "c.enumConstants");
        return a.a(enumConstants);
    }
}
