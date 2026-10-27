package com.neusoft.na.ocservice;

import com.neusoft.na.nodejs.JsContext;
import com.neusoft.na.nodejs.JsContextRunnable;
import com.neusoft.na.nodejs.NodeInstance;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MotsBl.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a#\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"runSync", "Lcom/neusoft/na/nodejs/NodeInstance;", "c", "Lkotlin/Function1;", "Lcom/neusoft/na/nodejs/JsContext;", "", "Lkotlin/ExtensionFunctionType;", "ocservice_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class MotsBlKt {
    public static final NodeInstance runSync(NodeInstance nodeInstance, final Function1<? super JsContext, Unit> c) {
        Intrinsics.checkNotNullParameter(nodeInstance, "<this>");
        Intrinsics.checkNotNullParameter(c, "c");
        nodeInstance.sync(new JsContextRunnable() { // from class: com.neusoft.na.ocservice.MotsBlKt$$ExternalSyntheticLambda0
            @Override // com.neusoft.na.nodejs.JsContextRunnable
            public final void apply(JsContext jsContext) {
                MotsBlKt.runSync$lambda$0(c, jsContext);
            }
        });
        return nodeInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runSync$lambda$0(Function1 c, JsContext it) {
        Intrinsics.checkNotNullParameter(c, "$c");
        Intrinsics.checkNotNullParameter(it, "it");
        c.invoke(it);
    }
}
