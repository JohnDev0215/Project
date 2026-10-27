package java9.util.stream;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java9.util.Maps;
import java9.util.Spliterator;

/* JADX INFO: loaded from: classes3.dex */
enum StreamOpFlag {
    DISTINCT(0, set(Type.SPLITERATOR).set(Type.STREAM).setAndClear(Type.OP)),
    SORTED(1, set(Type.SPLITERATOR).set(Type.STREAM).setAndClear(Type.OP)),
    ORDERED(2, set(Type.SPLITERATOR).set(Type.STREAM).setAndClear(Type.OP).clear(Type.TERMINAL_OP).clear(Type.UPSTREAM_TERMINAL_OP)),
    SIZED(3, set(Type.SPLITERATOR).set(Type.STREAM).clear(Type.OP)),
    SHORT_CIRCUIT(12, set(Type.OP).set(Type.TERMINAL_OP));

    private static final int CLEAR_BITS = 2;
    private static final int FLAG_MASK;
    private static final int FLAG_MASK_IS;
    private static final int FLAG_MASK_NOT;
    static final int INITIAL_OPS_VALUE;
    static final int IS_DISTINCT;
    static final int IS_ORDERED;
    static final int IS_SHORT_CIRCUIT;
    static final int IS_SIZED;
    static final int IS_SORTED;
    static final int NOT_DISTINCT;
    static final int NOT_ORDERED;
    static final int NOT_SIZED;
    static final int NOT_SORTED;
    static final int OP_MASK;
    private static final int PRESERVE_BITS = 3;
    private static final int SET_BITS = 1;
    static final int SPLITERATOR_CHARACTERISTICS_MASK;
    static final int STREAM_MASK;
    static final int TERMINAL_OP_MASK;
    static final int UPSTREAM_TERMINAL_OP_MASK;
    private final int bitPosition;
    private final int clear;
    private final Map<Type, Integer> maskTable;
    private final int preserve;
    private final int set;

    enum Type {
        SPLITERATOR,
        STREAM,
        OP,
        TERMINAL_OP,
        UPSTREAM_TERMINAL_OP
    }

    static {
        StreamOpFlag streamOpFlag = DISTINCT;
        StreamOpFlag streamOpFlag2 = SORTED;
        StreamOpFlag streamOpFlag3 = ORDERED;
        StreamOpFlag streamOpFlag4 = SIZED;
        StreamOpFlag streamOpFlag5 = SHORT_CIRCUIT;
        SPLITERATOR_CHARACTERISTICS_MASK = createMask(Type.SPLITERATOR);
        int iCreateMask = createMask(Type.STREAM);
        STREAM_MASK = iCreateMask;
        OP_MASK = createMask(Type.OP);
        TERMINAL_OP_MASK = createMask(Type.TERMINAL_OP);
        UPSTREAM_TERMINAL_OP_MASK = createMask(Type.UPSTREAM_TERMINAL_OP);
        FLAG_MASK = createFlagMask();
        FLAG_MASK_IS = iCreateMask;
        int i = iCreateMask << 1;
        FLAG_MASK_NOT = i;
        INITIAL_OPS_VALUE = iCreateMask | i;
        IS_DISTINCT = streamOpFlag.set;
        NOT_DISTINCT = streamOpFlag.clear;
        IS_SORTED = streamOpFlag2.set;
        NOT_SORTED = streamOpFlag2.clear;
        IS_ORDERED = streamOpFlag3.set;
        NOT_ORDERED = streamOpFlag3.clear;
        IS_SIZED = streamOpFlag4.set;
        NOT_SIZED = streamOpFlag4.clear;
        IS_SHORT_CIRCUIT = streamOpFlag5.set;
    }

    private static MaskBuilder set(Type type) {
        return new MaskBuilder(new EnumMap(Type.class)).set(type);
    }

    private static class MaskBuilder {
        final Map<Type, Integer> map;

        MaskBuilder(Map<Type, Integer> map) {
            this.map = map;
        }

        MaskBuilder mask(Type type, Integer num) {
            this.map.put(type, num);
            return this;
        }

        MaskBuilder set(Type type) {
            return mask(type, 1);
        }

        MaskBuilder clear(Type type) {
            return mask(type, 2);
        }

        MaskBuilder setAndClear(Type type) {
            return mask(type, 3);
        }

        Map<Type, Integer> build() {
            Map<Type, Integer> map = this.map;
            int i = 0;
            if (map instanceof ConcurrentMap) {
                ConcurrentMap concurrentMap = (ConcurrentMap) map;
                Type[] typeArrValues = Type.values();
                int length = typeArrValues.length;
                while (i < length) {
                    concurrentMap.putIfAbsent(typeArrValues[i], 0);
                    i++;
                }
                return concurrentMap;
            }
            Type[] typeArrValues2 = Type.values();
            int length2 = typeArrValues2.length;
            while (i < length2) {
                Maps.putIfAbsent(this.map, typeArrValues2[i], 0);
                i++;
            }
            return this.map;
        }
    }

    StreamOpFlag(int i, MaskBuilder maskBuilder) {
        this.maskTable = maskBuilder.build();
        int i2 = i * 2;
        this.bitPosition = i2;
        this.set = 1 << i2;
        this.clear = 2 << i2;
        this.preserve = 3 << i2;
    }

    int set() {
        return this.set;
    }

    int clear() {
        return this.clear;
    }

    boolean isStreamFlag() {
        return this.maskTable.get(Type.STREAM).intValue() > 0;
    }

    boolean isKnown(int i) {
        return (i & this.preserve) == this.set;
    }

    boolean isCleared(int i) {
        return (i & this.preserve) == this.clear;
    }

    boolean isPreserved(int i) {
        int i2 = this.preserve;
        return (i & i2) == i2;
    }

    boolean canSet(Type type) {
        return (this.maskTable.get(type).intValue() & 1) > 0;
    }

    private static int createMask(Type type) {
        int iIntValue = 0;
        for (StreamOpFlag streamOpFlag : values()) {
            iIntValue |= streamOpFlag.maskTable.get(type).intValue() << streamOpFlag.bitPosition;
        }
        return iIntValue;
    }

    private static int createFlagMask() {
        int i = 0;
        for (StreamOpFlag streamOpFlag : values()) {
            i |= streamOpFlag.preserve;
        }
        return i;
    }

    private static int getMask(int i) {
        if (i == 0) {
            return FLAG_MASK;
        }
        return ~(((i & FLAG_MASK_NOT) >> 1) | ((FLAG_MASK_IS & i) << 1) | i);
    }

    static int combineOpFlags(int i, int i2) {
        return i | (i2 & getMask(i));
    }

    static int toStreamFlags(int i) {
        return i & ((~i) >> 1) & FLAG_MASK_IS;
    }

    static int toCharacteristics(int i) {
        return i & SPLITERATOR_CHARACTERISTICS_MASK;
    }

    static int fromCharacteristics(Spliterator<?> spliterator) {
        int iCharacteristics = spliterator.characteristics();
        if ((iCharacteristics & 4) != 0 && spliterator.getComparator() != null) {
            return SPLITERATOR_CHARACTERISTICS_MASK & iCharacteristics & (-5);
        }
        return SPLITERATOR_CHARACTERISTICS_MASK & iCharacteristics;
    }

    static int fromCharacteristics(int i) {
        return i & SPLITERATOR_CHARACTERISTICS_MASK;
    }
}
