package com.google.common.collect;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
final class AbstractIterator$State {
    public static final AbstractIterator$State c;
    public static final AbstractIterator$State e;
    public static final AbstractIterator$State k;
    public static final AbstractIterator$State l;
    public static final /* synthetic */ AbstractIterator$State[] m;

    static {
        AbstractIterator$State abstractIterator$State = new AbstractIterator$State("READY", 0);
        c = abstractIterator$State;
        AbstractIterator$State abstractIterator$State2 = new AbstractIterator$State("NOT_READY", 1);
        e = abstractIterator$State2;
        AbstractIterator$State abstractIterator$State3 = new AbstractIterator$State("DONE", 2);
        k = abstractIterator$State3;
        AbstractIterator$State abstractIterator$State4 = new AbstractIterator$State("FAILED", 3);
        l = abstractIterator$State4;
        m = new AbstractIterator$State[]{abstractIterator$State, abstractIterator$State2, abstractIterator$State3, abstractIterator$State4};
    }

    public static AbstractIterator$State valueOf(String str) {
        return (AbstractIterator$State) Enum.valueOf(AbstractIterator$State.class, str);
    }

    public static AbstractIterator$State[] values() {
        return (AbstractIterator$State[]) m.clone();
    }
}
