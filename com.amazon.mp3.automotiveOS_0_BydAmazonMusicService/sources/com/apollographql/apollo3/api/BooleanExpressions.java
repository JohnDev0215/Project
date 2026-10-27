package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.BooleanExpression;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BooleanExpression.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0003\u001a'\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0000\"\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a.\u0010\f\u001a\u00020\n\"\b\b\u0000\u0010\b*\u00020\u0007*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\t\u001a(\u0010\f\u001a\u00020\n*\b\u0012\u0004\u0012\u00020\r0\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001¨\u0006\u0011"}, d2 = {"", "", "typenames", "Lcom/apollographql/apollo3/api/BooleanExpression;", "Lcom/apollographql/apollo3/api/BPossibleTypes;", "possibleTypes", "([Ljava/lang/String;)Lcom/apollographql/apollo3/api/BooleanExpression;", "", "T", "Lkotlin/Function1;", "", "block", "evaluate", "Lcom/apollographql/apollo3/api/BTerm;", "", "variables", "typename", "apollo-api"}, k = 2, mv = {1, 5, 1})
@JvmName(name = "BooleanExpressions")
public final class BooleanExpressions {
    public static final BooleanExpression<BPossibleTypes> possibleTypes(String... typenames) {
        Intrinsics.checkNotNullParameter(typenames, "typenames");
        return new BooleanExpression.Element(new BPossibleTypes(ArraysKt___ArraysKt.toSet(typenames)));
    }

    public static final <T> boolean evaluate(BooleanExpression<? extends T> booleanExpression, Function1<? super T, Boolean> block) {
        Intrinsics.checkNotNullParameter(booleanExpression, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (!Intrinsics.areEqual(booleanExpression, BooleanExpression.True.INSTANCE)) {
            if (Intrinsics.areEqual(booleanExpression, BooleanExpression.False.INSTANCE)) {
                return false;
            }
            if (booleanExpression instanceof BooleanExpression.Not) {
                if (evaluate(((BooleanExpression.Not) booleanExpression).getOperand(), block)) {
                    return false;
                }
            } else if (!(booleanExpression instanceof BooleanExpression.Or)) {
                if (!(booleanExpression instanceof BooleanExpression.And)) {
                    if (booleanExpression instanceof BooleanExpression.Element) {
                        return block.invoke((Object) ((BooleanExpression.Element) booleanExpression).getValue()).booleanValue();
                    }
                    throw new NoWhenBranchMatchedException();
                }
                Set<BooleanExpression<T>> operands = ((BooleanExpression.And) booleanExpression).getOperands();
                if (!(operands instanceof Collection) || !operands.isEmpty()) {
                    Iterator<T> it = operands.iterator();
                    while (it.hasNext()) {
                        if (!evaluate((BooleanExpression) it.next(), block)) {
                            return false;
                        }
                    }
                }
            } else {
                Set<BooleanExpression<T>> operands2 = ((BooleanExpression.Or) booleanExpression).getOperands();
                if ((operands2 instanceof Collection) && operands2.isEmpty()) {
                    return false;
                }
                Iterator<T> it2 = operands2.iterator();
                while (it2.hasNext()) {
                    if (evaluate((BooleanExpression) it2.next(), block)) {
                    }
                }
                return false;
            }
        }
        return true;
    }

    public static final boolean evaluate(BooleanExpression<? extends BTerm> booleanExpression, final Set<String> variables, final String str) {
        Intrinsics.checkNotNullParameter(booleanExpression, "<this>");
        Intrinsics.checkNotNullParameter(variables, "variables");
        return evaluate(booleanExpression, new Function1<BTerm, Boolean>() { // from class: com.apollographql.apollo3.api.BooleanExpressions.evaluate.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BTerm it) {
                boolean zContains;
                Intrinsics.checkNotNullParameter(it, "it");
                if (it instanceof BVariable) {
                    zContains = variables.contains(((BVariable) it).getName());
                } else {
                    if (!(it instanceof BPossibleTypes)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    zContains = CollectionsKt___CollectionsKt.contains(((BPossibleTypes) it).getPossibleTypes(), str);
                }
                return Boolean.valueOf(zContains);
            }
        });
    }
}
