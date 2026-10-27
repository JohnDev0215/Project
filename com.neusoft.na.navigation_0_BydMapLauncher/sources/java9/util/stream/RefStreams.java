package java9.util.stream;

import java9.util.J8Arrays;
import java9.util.Objects;
import java9.util.Spliterator;
import java9.util.Spliterators;
import java9.util.function.Consumer;
import java9.util.function.Predicate;
import java9.util.function.Supplier;
import java9.util.function.UnaryOperator;
import java9.util.stream.Stream;
import java9.util.stream.StreamSpliterators;
import java9.util.stream.Streams;
import java9.util.stream.WhileOps;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes3.dex */
public final class RefStreams {
    public static <T> Stream<T> takeWhile(Stream<? extends T> stream, Predicate<? super T> predicate) {
        Objects.requireNonNull(stream);
        Objects.requireNonNull(predicate);
        return StreamSupport.stream(new WhileOps.UnorderedWhileSpliterator.OfRef.Taking(stream.spliterator2(), true, predicate), stream.isParallel()).onClose(StreamSupport.closeHandler(stream));
    }

    public static <T> Stream<T> dropWhile(Stream<? extends T> stream, Predicate<? super T> predicate) {
        Objects.requireNonNull(stream);
        Objects.requireNonNull(predicate);
        return StreamSupport.stream(new WhileOps.UnorderedWhileSpliterator.OfRef.Dropping(stream.spliterator2(), true, predicate), stream.isParallel()).onClose(StreamSupport.closeHandler(stream));
    }

    public static <T> Stream.Builder<T> builder() {
        return new Streams.StreamBuilderImpl();
    }

    public static <T> Stream<T> empty() {
        return StreamSupport.stream(Spliterators.emptySpliterator(), false);
    }

    public static <T> Stream<T> of(T t) {
        return StreamSupport.stream((Spliterator) new Streams.StreamBuilderImpl(t), false);
    }

    public static <T> Stream<T> ofNullable(T t) {
        if (t == null) {
            return empty();
        }
        return StreamSupport.stream((Spliterator) new Streams.StreamBuilderImpl(t), false);
    }

    public static <T> Stream<T> of(T... tArr) {
        return J8Arrays.stream(tArr);
    }

    public static <T, S extends T> Stream<T> iterate(final S s, final UnaryOperator<S> unaryOperator) {
        Objects.requireNonNull(unaryOperator);
        return StreamSupport.stream((Spliterator) new Spliterators.AbstractSpliterator<T>(LongCompanionObject.MAX_VALUE, 1040) { // from class: java9.util.stream.RefStreams.1
            S prev;
            boolean started;

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java9.util.Spliterator
            public boolean tryAdvance(Consumer<? super T> consumer) {
                Object obj;
                Objects.requireNonNull(consumer);
                if (this.started) {
                    obj = (Object) unaryOperator.apply(this.prev);
                } else {
                    obj = (Object) s;
                    this.started = true;
                }
                this.prev = (S) obj;
                consumer.accept(obj);
                return true;
            }
        }, false);
    }

    public static <T, S extends T> Stream<T> iterate(final S s, final Predicate<? super S> predicate, final UnaryOperator<S> unaryOperator) {
        Objects.requireNonNull(unaryOperator);
        Objects.requireNonNull(predicate);
        return StreamSupport.stream((Spliterator) new Spliterators.AbstractSpliterator<T>(LongCompanionObject.MAX_VALUE, 1040) { // from class: java9.util.stream.RefStreams.2
            boolean finished;
            S prev;
            boolean started;

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java9.util.Spliterator
            public boolean tryAdvance(Consumer<? super T> consumer) {
                T t;
                Objects.requireNonNull(consumer);
                if (this.finished) {
                    return false;
                }
                if (this.started) {
                    t = (Object) unaryOperator.apply(this.prev);
                } else {
                    t = (Object) s;
                    this.started = true;
                }
                if (!predicate.test(t)) {
                    this.prev = null;
                    this.finished = true;
                    return false;
                }
                this.prev = (S) t;
                consumer.accept(t);
                return true;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java9.util.Spliterators.AbstractSpliterator, java9.util.Spliterator
            public void forEachRemaining(Consumer<? super T> consumer) {
                Objects.requireNonNull(consumer);
                if (this.finished) {
                    return;
                }
                this.finished = true;
                Object objApply = this.started ? unaryOperator.apply(this.prev) : s;
                this.prev = null;
                while (predicate.test(objApply)) {
                    consumer.accept(objApply);
                    objApply = unaryOperator.apply(objApply);
                }
            }
        }, false);
    }

    public static <T> Stream<T> generate(Supplier<? extends T> supplier) {
        Objects.requireNonNull(supplier);
        return StreamSupport.stream((Spliterator) new StreamSpliterators.InfiniteSupplyingSpliterator.OfRef(LongCompanionObject.MAX_VALUE, supplier), false);
    }

    public static <T> Stream<T> concat(Stream<? extends T> stream, Stream<? extends T> stream2) {
        Objects.requireNonNull(stream);
        Objects.requireNonNull(stream2);
        return StreamSupport.stream(new Streams.ConcatSpliterator.OfRef(stream.spliterator2(), stream2.spliterator2()), stream.isParallel() || stream2.isParallel()).onClose(Streams.composedClose(stream, stream2));
    }

    private RefStreams() {
    }
}
