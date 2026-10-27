package com.apollographql.apollo3.api;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Optional.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u0005*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0003\u0006\u0005\u0007B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo3/api/Optional;", "V", "", "<init>", "()V", "Companion", "Absent", "Present", "Lcom/apollographql/apollo3/api/Optional$Present;", "Lcom/apollographql/apollo3/api/Optional$Absent;", "apollo-api"}, k = 1, mv = {1, 5, 1})
public abstract class Optional<V> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ Optional(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private Optional() {
    }

    /* JADX INFO: compiled from: Optional.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010\u0002B\u000f\u0012\u0006\u0010\u000b\u001a\u00028\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R\u0017\u0010\u000b\u001a\u00028\u00018\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/apollographql/apollo3/api/Optional$Present;", "V", "Lcom/apollographql/apollo3/api/Optional;", "", "toString", "", "hashCode", "", "other", "", "equals", "value", "Ljava/lang/Object;", "getValue", "()Ljava/lang/Object;", "<init>", "(Ljava/lang/Object;)V", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public static final /* data */ class Present<V> extends Optional<V> {
        private final V value;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Present) && Intrinsics.areEqual(this.value, ((Present) other).value);
        }

        public int hashCode() {
            V v = this.value;
            if (v == null) {
                return 0;
            }
            return v.hashCode();
        }

        public String toString() {
            return "Present(value=" + this.value + ')';
        }

        public Present(V v) {
            super(null);
            this.value = v;
        }

        public final V getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: Optional.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/apollographql/apollo3/api/Optional$Absent;", "Lcom/apollographql/apollo3/api/Optional;", "", "()V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Absent extends Optional {
        public static final Absent INSTANCE = new Absent();

        private Absent() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: Optional.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0001\u0010\u0005*\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u0001H\u0005¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo3/api/Optional$Companion;", "", "()V", "presentIfNotNull", "Lcom/apollographql/apollo3/api/Optional;", "V", "value", "(Ljava/lang/Object;)Lcom/apollographql/apollo3/api/Optional;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <V> Optional<V> presentIfNotNull(V value) {
            return value == null ? Absent.INSTANCE : new Present(value);
        }
    }
}
