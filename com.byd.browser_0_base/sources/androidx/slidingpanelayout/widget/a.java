package androidx.slidingpanelayout.widget;

import androidx.window.layout.WindowInfoTracker;
import defpackage.i21;
import defpackage.ir2;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: FoldingFeatureObserver.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nFoldingFeatureObserver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FoldingFeatureObserver.kt\nandroidx/slidingpanelayout/widget/FoldingFeatureObserver\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,91:1\n288#2,2:92\n*S KotlinDebug\n*F\n+ 1 FoldingFeatureObserver.kt\nandroidx/slidingpanelayout/widget/FoldingFeatureObserver\n*L\n89#1:92,2\n*E\n"})
public final class a {
    public final WindowInfoTracker a;
    public final Executor b;
    public ir2 c;

    /* JADX INFO: renamed from: androidx.slidingpanelayout.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FoldingFeatureObserver.kt */
    public interface InterfaceC0028a {
    }

    public a(WindowInfoTracker windowInfoTracker, Executor executor) {
        i21.e(windowInfoTracker, "windowInfoTracker");
        i21.e(executor, "executor");
        this.a = windowInfoTracker;
        this.b = executor;
    }

    public final void setOnFoldingFeatureChangeListener(InterfaceC0028a interfaceC0028a) {
        i21.e(interfaceC0028a, "onFoldingFeatureChangeListener");
    }
}
