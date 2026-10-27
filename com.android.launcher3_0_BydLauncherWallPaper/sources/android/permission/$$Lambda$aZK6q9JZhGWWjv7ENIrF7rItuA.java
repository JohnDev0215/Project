package android.permission;

import com.android.internal.infra.AndroidFuture;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: android.permission.-$$Lambda$aZK6q9JZhGWWjv-7ENIrF7rItuA, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$aZK6q9JZhGWWjv7ENIrF7rItuA implements Consumer {
    public final /* synthetic */ AndroidFuture f$0;

    public /* synthetic */ $$Lambda$aZK6q9JZhGWWjv7ENIrF7rItuA(AndroidFuture androidFuture) {
        this.f$0 = androidFuture;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f$0.complete((List) obj);
    }
}
