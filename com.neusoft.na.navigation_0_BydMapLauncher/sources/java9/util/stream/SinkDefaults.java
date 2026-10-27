package java9.util.stream;

import java9.util.stream.Sink;

/* JADX INFO: loaded from: classes3.dex */
final class SinkDefaults {

    static final class OfInt {
        static void accept(Sink.OfInt ofInt, Integer num) {
            ofInt.accept(num.intValue());
        }

        private OfInt() {
        }
    }

    static final class OfLong {
        static void accept(Sink.OfLong ofLong, Long l) {
            ofLong.accept(l.longValue());
        }

        private OfLong() {
        }
    }

    static final class OfDouble {
        static void accept(Sink.OfDouble ofDouble, Double d) {
            ofDouble.accept(d.doubleValue());
        }

        private OfDouble() {
        }
    }

    static <T> void reject() {
        throw new IllegalStateException("called wrong accept method");
    }

    private SinkDefaults() {
    }
}
