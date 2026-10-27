package mu;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import mu.KLoggable;

/* JADX INFO: compiled from: KLogging.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lmu/NamedKLogging;", "Lmu/KLoggable;", "name", "", "(Ljava/lang/String;)V", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "kotlin-logging"}, k = 1, mv = {1, 1, 13})
public class NamedKLogging implements KLoggable {
    private final KLogger logger;

    public NamedKLogging(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.logger = logger(name);
    }

    @Override // mu.KLoggable
    public KLogger logger() {
        return KLoggable.DefaultImpls.logger(this);
    }

    @Override // mu.KLoggable
    public KLogger logger(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return KLoggable.DefaultImpls.logger(this, name);
    }

    @Override // mu.KLoggable
    public KLogger getLogger() {
        return this.logger;
    }
}
