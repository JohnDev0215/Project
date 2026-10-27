package com.amazon.music.platform;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Outcome.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/platform/Outcome;", "T", "", "()V", "Failure", "Success", "Lcom/amazon/music/platform/Outcome$Failure;", "Lcom/amazon/music/platform/Outcome$Success;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class Outcome<T> {
    public /* synthetic */ Outcome(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: Outcome.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B\u000f\u0012\u0006\u0010\u000b\u001a\u00028\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R\u0017\u0010\u000b\u001a\u00028\u00018\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/Outcome$Success;", "T", "Lcom/amazon/music/platform/Outcome;", "", "toString", "", "hashCode", "", "other", "", "equals", "value", "Ljava/lang/Object;", "getValue", "()Ljava/lang/Object;", "<init>", "(Ljava/lang/Object;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Success<T> extends Outcome<T> {
        private final T value;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Success) && Intrinsics.areEqual(this.value, ((Success) other).value);
        }

        public int hashCode() {
            T t = this.value;
            if (t == null) {
                return 0;
            }
            return t.hashCode();
        }

        public String toString() {
            return "Success(value=" + this.value + ')';
        }

        public Success(T t) {
            super(null);
            this.value = t;
            PlatformProviderUtilKt.freeze(this);
        }

        public final T getValue() {
            return this.value;
        }
    }

    private Outcome() {
    }

    /* JADX INFO: compiled from: Outcome.kt */
    @Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0086\b\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B\u000f\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/platform/Outcome$Failure;", "T", "Lcom/amazon/music/platform/Outcome;", "", "toString", "", "hashCode", "", "other", "", "equals", "", "cause", "Ljava/lang/Throwable;", "getCause", "()Ljava/lang/Throwable;", "<init>", "(Ljava/lang/Throwable;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Failure<T> extends Outcome<T> {
        private final Throwable cause;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Failure) && Intrinsics.areEqual(this.cause, ((Failure) other).cause);
        }

        public int hashCode() {
            return this.cause.hashCode();
        }

        public String toString() {
            return "Failure(cause=" + this.cause + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Failure(Throwable cause) {
            super(null);
            Intrinsics.checkNotNullParameter(cause, "cause");
            this.cause = cause;
            PlatformProviderUtilKt.freeze(this);
        }

        public final Throwable getCause() {
            return this.cause;
        }
    }
}
