package com.amazon.alexa.voice.core;

import com.amazon.alexa.voice.core.internal.util.Functions;
import com.amazon.alexa.voice.core.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class Consumables {
    private Consumables() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Consumable<T> empty() {
        return empty(Functions.emptyAction(), Functions.emptyConsumer());
    }

    public static <T> Consumable<T> empty(final Action action, final Consumer<Throwable> consumer) {
        return new Consumable<T>() { // from class: com.amazon.alexa.voice.core.Consumables.1
            @Override // com.amazon.alexa.voice.core.Consumable
            public T get() {
                throw new UnsupportedOperationException("Empty consumable does not contain any value");
            }

            @Override // com.amazon.alexa.voice.core.Consumable
            public void accept() {
                try {
                    action.call();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override // com.amazon.alexa.voice.core.Consumable
            public void reject(Throwable th) {
                try {
                    consumer.accept(th);
                } catch (Exception e) {
                    e.addSuppressed(th);
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public static <T> Consumable<T> of(T t, Consumer<T> consumer, Consumer<Throwable> consumer2) {
        Preconditions.notNull(t, "value == null");
        Preconditions.notNull(consumer, "onAccept == null");
        Preconditions.notNull(consumer2, "onReject == null");
        return new DelegateConsumable(t, consumer, consumer2);
    }

    static class DelegateConsumable<T> implements Consumable<T> {
        private final Consumer<T> onAccept;
        private final Consumer<Throwable> onReject;
        private final T value;

        public DelegateConsumable(T t, Consumer<T> consumer, Consumer<Throwable> consumer2) {
            this.value = t;
            this.onAccept = consumer;
            this.onReject = consumer2;
        }

        @Override // com.amazon.alexa.voice.core.Consumable
        public T get() {
            return this.value;
        }

        @Override // com.amazon.alexa.voice.core.Consumable
        public void accept() {
            try {
                this.onAccept.accept(this.value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.amazon.alexa.voice.core.Consumable
        public void reject(Throwable th) {
            try {
                this.onReject.accept(th);
            } catch (Exception e) {
                e.addSuppressed(th);
                throw new RuntimeException(e);
            }
        }
    }
}
