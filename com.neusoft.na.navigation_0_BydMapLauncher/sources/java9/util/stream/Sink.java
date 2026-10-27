package java9.util.stream;

import java9.util.Objects;
import java9.util.function.Consumer;
import java9.util.function.DoubleConsumer;
import java9.util.function.IntConsumer;
import java9.util.function.LongConsumer;
import java9.util.stream.SinkDefaults;

/* JADX INFO: loaded from: classes3.dex */
interface Sink<T> extends Consumer<T> {

    public interface OfDouble extends Sink<Double>, DoubleConsumer {
        @Override // java9.util.stream.Sink
        void accept(double d);

        void accept(Double d);
    }

    public interface OfInt extends Sink<Integer>, IntConsumer {
        @Override // java9.util.stream.Sink
        void accept(int i);

        void accept(Integer num);
    }

    public interface OfLong extends Sink<Long>, LongConsumer {
        @Override // java9.util.stream.Sink
        void accept(long j);

        void accept(Long l);
    }

    void accept(double d);

    void accept(int i);

    void accept(long j);

    void begin(long j);

    boolean cancellationRequested();

    void end();

    public static abstract class ChainedReference<T, E_OUT> implements Sink<T> {
        protected final Sink<? super E_OUT> downstream;

        public ChainedReference(Sink<? super E_OUT> sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.downstream.begin(j);
        }

        @Override // java9.util.stream.Sink
        public void end() {
            this.downstream.end();
        }

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            SinkDefaults.reject();
        }
    }

    public static abstract class ChainedInt<E_OUT> implements OfInt {
        protected final Sink<? super E_OUT> downstream;

        public ChainedInt(Sink<? super E_OUT> sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.downstream.begin(j);
        }

        @Override // java9.util.stream.Sink
        public void end() {
            this.downstream.end();
        }

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            SinkDefaults.reject();
        }

        @Override // java9.util.function.Consumer
        public void accept(Integer num) {
            SinkDefaults.OfInt.accept(this, num);
        }
    }

    public static abstract class ChainedLong<E_OUT> implements OfLong {
        protected final Sink<? super E_OUT> downstream;

        public ChainedLong(Sink<? super E_OUT> sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.downstream.begin(j);
        }

        @Override // java9.util.stream.Sink
        public void end() {
            this.downstream.end();
        }

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            SinkDefaults.reject();
        }

        @Override // java9.util.function.Consumer
        public void accept(Long l) {
            SinkDefaults.OfLong.accept(this, l);
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            SinkDefaults.reject();
        }
    }

    public static abstract class ChainedDouble<E_OUT> implements OfDouble {
        protected final Sink<? super E_OUT> downstream;

        public ChainedDouble(Sink<? super E_OUT> sink) {
            this.downstream = (Sink) Objects.requireNonNull(sink);
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.downstream.begin(j);
        }

        @Override // java9.util.stream.Sink
        public void end() {
            this.downstream.end();
        }

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return this.downstream.cancellationRequested();
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            SinkDefaults.reject();
        }

        @Override // java9.util.function.Consumer
        public void accept(Double d) {
            SinkDefaults.OfDouble.accept(this, d);
        }
    }
}
