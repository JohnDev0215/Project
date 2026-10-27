package java9.util;

import java.util.NoSuchElementException;
import java9.lang.Longs;
import java9.util.function.LongConsumer;
import java9.util.function.LongSupplier;
import java9.util.function.Supplier;
import java9.util.stream.LongStream;
import java9.util.stream.LongStreams;

/* JADX INFO: loaded from: classes3.dex */
public final class OptionalLong {
    private static final OptionalLong EMPTY = new OptionalLong();
    private final boolean isPresent;
    private final long value;

    private static final class OLCache {
        static final OptionalLong[] cache = new OptionalLong[256];

        private OLCache() {
        }

        static {
            int i = 0;
            while (true) {
                OptionalLong[] optionalLongArr = cache;
                if (i >= optionalLongArr.length) {
                    return;
                }
                optionalLongArr[i] = new OptionalLong(i - 128);
                i++;
            }
        }
    }

    private OptionalLong() {
        this.isPresent = false;
        this.value = 0L;
    }

    public static OptionalLong empty() {
        return EMPTY;
    }

    OptionalLong(long j) {
        this.isPresent = true;
        this.value = j;
    }

    public static OptionalLong of(long j) {
        if (j >= -128 && j <= 127) {
            return OLCache.cache[((int) j) + 128];
        }
        return new OptionalLong(j);
    }

    public long getAsLong() {
        return orElseThrow();
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public boolean isEmpty() {
        return !this.isPresent;
    }

    public void ifPresent(LongConsumer longConsumer) {
        if (this.isPresent) {
            longConsumer.accept(this.value);
        }
    }

    public void ifPresentOrElse(LongConsumer longConsumer, Runnable runnable) {
        if (this.isPresent) {
            longConsumer.accept(this.value);
        } else {
            runnable.run();
        }
    }

    public LongStream stream() {
        if (this.isPresent) {
            return LongStreams.of(this.value);
        }
        return LongStreams.empty();
    }

    public long orElse(long j) {
        return this.isPresent ? this.value : j;
    }

    public long orElseGet(LongSupplier longSupplier) {
        return this.isPresent ? this.value : longSupplier.getAsLong();
    }

    public long orElseThrow() {
        if (!this.isPresent) {
            throw new NoSuchElementException("No value present");
        }
        return this.value;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public <X extends Throwable> long orElseThrow(Supplier<? extends X> supplier) throws Throwable {
        if (this.isPresent) {
            return this.value;
        }
        throw supplier.get();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OptionalLong)) {
            return false;
        }
        OptionalLong optionalLong = (OptionalLong) obj;
        boolean z = this.isPresent;
        if (z && optionalLong.isPresent) {
            if (this.value == optionalLong.value) {
                return true;
            }
        } else if (z == optionalLong.isPresent) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.isPresent) {
            return Longs.hashCode(this.value);
        }
        return 0;
    }

    public String toString() {
        return this.isPresent ? String.format("OptionalLong[%s]", Long.valueOf(this.value)) : "OptionalLong.empty";
    }
}
