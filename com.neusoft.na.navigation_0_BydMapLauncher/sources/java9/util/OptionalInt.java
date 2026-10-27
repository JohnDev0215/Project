package java9.util;

import java.util.NoSuchElementException;
import java9.util.function.IntConsumer;
import java9.util.function.IntSupplier;
import java9.util.function.Supplier;
import java9.util.stream.IntStream;
import java9.util.stream.IntStreams;

/* JADX INFO: loaded from: classes3.dex */
public final class OptionalInt {
    private static final OptionalInt EMPTY = new OptionalInt();
    private final boolean isPresent;
    private final int value;

    private static final class OICache {
        static final OptionalInt[] cache = new OptionalInt[256];

        private OICache() {
        }

        static {
            int i = 0;
            while (true) {
                OptionalInt[] optionalIntArr = cache;
                if (i >= optionalIntArr.length) {
                    return;
                }
                optionalIntArr[i] = new OptionalInt(i - 128);
                i++;
            }
        }
    }

    private OptionalInt() {
        this.isPresent = false;
        this.value = 0;
    }

    public static OptionalInt empty() {
        return EMPTY;
    }

    OptionalInt(int i) {
        this.isPresent = true;
        this.value = i;
    }

    public static OptionalInt of(int i) {
        if (i >= -128 && i <= 127) {
            return OICache.cache[i + 128];
        }
        return new OptionalInt(i);
    }

    public int getAsInt() {
        return orElseThrow();
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public boolean isEmpty() {
        return !this.isPresent;
    }

    public void ifPresent(IntConsumer intConsumer) {
        if (this.isPresent) {
            intConsumer.accept(this.value);
        }
    }

    public void ifPresentOrElse(IntConsumer intConsumer, Runnable runnable) {
        if (this.isPresent) {
            intConsumer.accept(this.value);
        } else {
            runnable.run();
        }
    }

    public IntStream stream() {
        if (this.isPresent) {
            return IntStreams.of(this.value);
        }
        return IntStreams.empty();
    }

    public int orElse(int i) {
        return this.isPresent ? this.value : i;
    }

    public int orElseGet(IntSupplier intSupplier) {
        return this.isPresent ? this.value : intSupplier.getAsInt();
    }

    public int orElseThrow() {
        if (!this.isPresent) {
            throw new NoSuchElementException("No value present");
        }
        return this.value;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public <X extends Throwable> int orElseThrow(Supplier<? extends X> supplier) throws Throwable {
        if (this.isPresent) {
            return this.value;
        }
        throw supplier.get();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OptionalInt)) {
            return false;
        }
        OptionalInt optionalInt = (OptionalInt) obj;
        boolean z = this.isPresent;
        if (z && optionalInt.isPresent) {
            if (this.value == optionalInt.value) {
                return true;
            }
        } else if (z == optionalInt.isPresent) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.isPresent) {
            return this.value;
        }
        return 0;
    }

    public String toString() {
        return this.isPresent ? String.format("OptionalInt[%s]", Integer.valueOf(this.value)) : "OptionalInt.empty";
    }
}
