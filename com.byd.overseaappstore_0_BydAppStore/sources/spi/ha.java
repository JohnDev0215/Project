package spi;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareFileUnzipInfo;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: loaded from: classes.dex */
public final class ha<T> implements FlowCollector {
    public static final ha<T> a = new ha<>();

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        SoftwareFileUnzipInfo softwareFileUnzipInfo = (SoftwareFileUnzipInfo) obj;
        ct.a("executor install pkn:" + softwareFileUnzipInfo.getPackageName() + " primePkn:" + softwareFileUnzipInfo.getPrimePackageName() + " multiWork", new Object[0]);
        return Unit.INSTANCE;
    }
}
