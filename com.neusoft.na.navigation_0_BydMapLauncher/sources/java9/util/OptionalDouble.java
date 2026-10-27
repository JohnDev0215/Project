package java9.util;

import java.util.NoSuchElementException;
import java9.lang.Doubles;
import java9.util.function.DoubleConsumer;
import java9.util.function.DoubleSupplier;
import java9.util.function.Supplier;
import java9.util.stream.DoubleStream;
import java9.util.stream.DoubleStreams;

/* JADX INFO: loaded from: classes3.dex */
public final class OptionalDouble {
    private static final OptionalDouble EMPTY = new OptionalDouble();
    private final boolean isPresent;
    private final double value;

    private OptionalDouble() {
        this.isPresent = false;
        this.value = Double.NaN;
    }

    public static OptionalDouble empty() {
        return EMPTY;
    }

    private OptionalDouble(double d) {
        this.isPresent = true;
        this.value = d;
    }

    public static OptionalDouble of(double d) {
        return new OptionalDouble(d);
    }

    public double getAsDouble() {
        return orElseThrow();
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public boolean isEmpty() {
        return !this.isPresent;
    }

    public void ifPresent(DoubleConsumer doubleConsumer) {
        if (this.isPresent) {
            doubleConsumer.accept(this.value);
        }
    }

    public void ifPresentOrElse(DoubleConsumer doubleConsumer, Runnable runnable) {
        if (this.isPresent) {
            doubleConsumer.accept(this.value);
        } else {
            runnable.run();
        }
    }

    public DoubleStream stream() {
        if (this.isPresent) {
            return DoubleStreams.of(this.value);
        }
        return DoubleStreams.empty();
    }

    public double orElse(double d) {
        return this.isPresent ? this.value : d;
    }

    public double orElseGet(DoubleSupplier doubleSupplier) {
        return this.isPresent ? this.value : doubleSupplier.getAsDouble();
    }

    public double orElseThrow() {
        if (!this.isPresent) {
            throw new NoSuchElementException("No value present");
        }
        return this.value;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public <X extends Throwable> double orElseThrow(Supplier<? extends X> supplier) throws Throwable {
        if (this.isPresent) {
            return this.value;
        }
        throw supplier.get();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OptionalDouble)) {
            return false;
        }
        OptionalDouble optionalDouble = (OptionalDouble) obj;
        boolean z = this.isPresent;
        if (z && optionalDouble.isPresent) {
            if (Double.compare(this.value, optionalDouble.value) == 0) {
                return true;
            }
        } else if (z == optionalDouble.isPresent) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.isPresent) {
            return Doubles.hashCode(this.value);
        }
        return 0;
    }

    public String toString() {
        return this.isPresent ? String.format("OptionalDouble[%s]", Double.valueOf(this.value)) : "OptionalDouble.empty";
    }
}
