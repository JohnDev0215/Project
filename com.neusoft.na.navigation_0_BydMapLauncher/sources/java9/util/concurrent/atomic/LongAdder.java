package java9.util.concurrent.atomic;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java9.util.concurrent.atomic.Striped64;

/* JADX INFO: loaded from: classes3.dex */
public class LongAdder extends Striped64 implements Serializable {
    private static final long serialVersionUID = 7249069246863182397L;

    public void add(long j) {
        int length;
        Striped64.Cell cell;
        Striped64.Cell[] cellArr = this.cells;
        if (cellArr == null) {
            long j2 = this.base;
            if (casBase(j2, j2 + j)) {
                return;
            }
        }
        boolean zCas = true;
        if (cellArr != null && (length = cellArr.length - 1) >= 0 && (cell = cellArr[length & getProbe()]) != null) {
            long j3 = cell.value;
            zCas = cell.cas(j3, j3 + j);
            if (zCas) {
                return;
            }
        }
        longAccumulate(j, null, zCas);
    }

    public void increment() {
        add(1L);
    }

    public void decrement() {
        add(-1L);
    }

    public long sum() {
        Striped64.Cell[] cellArr = this.cells;
        long j = this.base;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j += cell.value;
                }
            }
        }
        return j;
    }

    public void reset() {
        Striped64.Cell[] cellArr = this.cells;
        this.base = 0L;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    cell.reset();
                }
            }
        }
    }

    public long sumThenReset() {
        Striped64.Cell[] cellArr = this.cells;
        long andSetBase = getAndSetBase(0L);
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    andSetBase += cell.getAndSet(0L);
                }
            }
        }
        return andSetBase;
    }

    public String toString() {
        return Long.toString(sum());
    }

    @Override // java.lang.Number
    public long longValue() {
        return sum();
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) sum();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return sum();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return sum();
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 7249069246863182397L;
        private final long value;

        SerializationProxy(LongAdder longAdder) {
            this.value = longAdder.sum();
        }

        private Object readResolve() {
            LongAdder longAdder = new LongAdder();
            longAdder.base = this.value;
            return longAdder;
        }
    }

    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }
}
