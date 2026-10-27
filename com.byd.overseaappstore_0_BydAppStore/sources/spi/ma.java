package spi;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: loaded from: classes.dex */
public final class ma<T> implements FlowCollector {
    public final /* synthetic */ String a;
    public final /* synthetic */ na b;

    public ma(String str, na naVar) {
        this.a = str;
        this.b = naVar;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if (zBooleanValue) {
            ct.a("executor install success ret:" + zBooleanValue + " pkn:" + this.a + " singleWork", new Object[0]);
        } else {
            ct.b("executor install error ret:" + zBooleanValue + " pkn:" + this.a + " singleWork", new Object[0]);
            this.b.c.addBizTypeAndData(3, this.b.d.parseToIUUState(this.a, (short) 1, 8)).workNotify();
        }
        return Unit.INSTANCE;
    }
}
