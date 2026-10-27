package com.apollographql.apollo3.api;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BooleanExpression.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\n\b\u0000\u0010\u0002 \u0001*\u00020\u00012\u00020\u0001:\u0006\u0005\u0006\u0007\b\t\nB\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0001\u0006\u000b\f\r\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression;", "", "T", "<init>", "()V", "And", "Element", "False", "Not", "Or", "True", "Lcom/apollographql/apollo3/api/BooleanExpression$True;", "Lcom/apollographql/apollo3/api/BooleanExpression$False;", "Lcom/apollographql/apollo3/api/BooleanExpression$Not;", "Lcom/apollographql/apollo3/api/BooleanExpression$Or;", "Lcom/apollographql/apollo3/api/BooleanExpression$And;", "Lcom/apollographql/apollo3/api/BooleanExpression$Element;", "apollo-api"}, k = 1, mv = {1, 5, 1})
public abstract class BooleanExpression<T> {
    public /* synthetic */ BooleanExpression(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private BooleanExpression() {
    }

    /* JADX INFO: compiled from: BooleanExpression.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression$True;", "Lcom/apollographql/apollo3/api/BooleanExpression;", "", "<init>", "()V", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public static final class True extends BooleanExpression {
        public static final True INSTANCE = new True();

        private True() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: BooleanExpression.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression$False;", "Lcom/apollographql/apollo3/api/BooleanExpression;", "", "<init>", "()V", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public static final class False extends BooleanExpression {
        public static final False INSTANCE = new False();

        private False() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: BooleanExpression.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u0000*\n\b\u0001\u0010\u0002 \u0001*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00010\u0003J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression$Not;", "", "T", "Lcom/apollographql/apollo3/api/BooleanExpression;", "", "toString", "", "hashCode", "other", "", "equals", "operand", "Lcom/apollographql/apollo3/api/BooleanExpression;", "getOperand", "()Lcom/apollographql/apollo3/api/BooleanExpression;", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public static final /* data */ class Not<T> extends BooleanExpression<T> {
        private final BooleanExpression<T> operand;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Not) && Intrinsics.areEqual(this.operand, ((Not) other).operand);
        }

        public int hashCode() {
            return this.operand.hashCode();
        }

        public String toString() {
            return "Not(operand=" + this.operand + ')';
        }

        public final BooleanExpression<T> getOperand() {
            return this.operand;
        }
    }

    /* JADX INFO: compiled from: BooleanExpression.kt */
    @Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\b\u0086\b\u0018\u0000*\b\b\u0001\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00010\u0003J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R#\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression$Or;", "", "T", "Lcom/apollographql/apollo3/api/BooleanExpression;", "", "toString", "", "hashCode", "other", "", "equals", "", "operands", "Ljava/util/Set;", "getOperands", "()Ljava/util/Set;", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public static final /* data */ class Or<T> extends BooleanExpression<T> {
        private final Set<BooleanExpression<T>> operands;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Or) && Intrinsics.areEqual(this.operands, ((Or) other).operands);
        }

        public int hashCode() {
            return this.operands.hashCode();
        }

        public final Set<BooleanExpression<T>> getOperands() {
            return this.operands;
        }

        public String toString() {
            return CollectionsKt___CollectionsKt.joinToString$default(this.operands, " | ", null, null, 0, null, null, 62, null);
        }
    }

    /* JADX INFO: compiled from: BooleanExpression.kt */
    @Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\b\u0086\b\u0018\u0000*\b\b\u0001\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00010\u0003J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R#\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression$And;", "", "T", "Lcom/apollographql/apollo3/api/BooleanExpression;", "", "toString", "", "hashCode", "other", "", "equals", "", "operands", "Ljava/util/Set;", "getOperands", "()Ljava/util/Set;", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public static final /* data */ class And<T> extends BooleanExpression<T> {
        private final Set<BooleanExpression<T>> operands;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof And) && Intrinsics.areEqual(this.operands, ((And) other).operands);
        }

        public int hashCode() {
            return this.operands.hashCode();
        }

        public String toString() {
            return "And(operands=" + this.operands + ')';
        }

        public final Set<BooleanExpression<T>> getOperands() {
            return this.operands;
        }
    }

    /* JADX INFO: compiled from: BooleanExpression.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u0000*\n\b\u0001\u0010\u0002 \u0001*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00010\u0003B\u000f\u0012\u0006\u0010\u000b\u001a\u00028\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u000b\u001a\u00028\u00018\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression$Element;", "", "T", "Lcom/apollographql/apollo3/api/BooleanExpression;", "", "toString", "", "hashCode", "other", "", "equals", "value", "Ljava/lang/Object;", "getValue", "()Ljava/lang/Object;", "<init>", "(Ljava/lang/Object;)V", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public static final /* data */ class Element<T> extends BooleanExpression<T> {
        private final T value;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Element) && Intrinsics.areEqual(this.value, ((Element) other).value);
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public String toString() {
            return "Element(value=" + this.value + ')';
        }

        public final T getValue() {
            return this.value;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Element(T value) {
            super(null);
            Intrinsics.checkNotNullParameter(value, "value");
            this.value = value;
        }
    }
}
