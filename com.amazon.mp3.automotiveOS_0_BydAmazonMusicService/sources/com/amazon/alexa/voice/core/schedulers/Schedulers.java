package com.amazon.alexa.voice.core.schedulers;

import android.os.Looper;
import com.amazon.alexa.voice.core.Factory;
import com.amazon.alexa.voice.core.Scheduler;
import com.amazon.alexa.voice.core.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class Schedulers {
    private static final Plugin<Scheduler> IO_SCHEDULER = new Plugin<>(new Factory<Scheduler>() { // from class: com.amazon.alexa.voice.core.schedulers.Schedulers.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.voice.core.Factory
        public Scheduler create() {
            return new ThreadPoolScheduler(Integer.MAX_VALUE);
        }
    });
    private static final Plugin<Scheduler> MAIN_SCHEDULER = new Plugin<>(new Factory<Scheduler>() { // from class: com.amazon.alexa.voice.core.schedulers.Schedulers.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.voice.core.Factory
        public Scheduler create() {
            return new HandlerScheduler(Looper.getMainLooper());
        }
    });
    private static final Plugin<Scheduler> IMMEDIATE_SCHEDULER = new Plugin<>(new Factory<Scheduler>() { // from class: com.amazon.alexa.voice.core.schedulers.Schedulers.3
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.voice.core.Factory
        public Scheduler create() {
            return new ImmediateScheduler();
        }
    });
    private static final Plugin<Scheduler> SERIAL_SCHEDULER = new Plugin<>(new Factory<Scheduler>() { // from class: com.amazon.alexa.voice.core.schedulers.Schedulers.4
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.voice.core.Factory
        public Scheduler create() {
            return new SingleThreadScheduler();
        }
    });

    private Schedulers() {
        throw new IllegalStateException("No instances!");
    }

    public static Scheduler io() {
        return IO_SCHEDULER.get();
    }

    public static Scheduler serial() {
        return SERIAL_SCHEDULER.get();
    }

    public static Scheduler main() {
        return MAIN_SCHEDULER.get();
    }

    public static Scheduler immediate() {
        return IMMEDIATE_SCHEDULER.get();
    }

    static class Plugin<T> {
        private final Factory<T> factory;
        private Factory<T> hookFactory;
        private T value;

        public Plugin(Factory<T> factory) {
            this.factory = factory;
        }

        public synchronized T get() {
            if (this.value == null) {
                Factory<T> factory = this.hookFactory;
                if (factory == null) {
                    factory = this.factory;
                }
                T tCreate = factory.create();
                this.value = tCreate;
                Preconditions.notNull(tCreate, "value == null");
            }
            return this.value;
        }
    }
}
