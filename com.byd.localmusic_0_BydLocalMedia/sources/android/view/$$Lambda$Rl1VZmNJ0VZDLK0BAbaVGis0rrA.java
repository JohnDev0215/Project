package android.view;

import java.util.function.Consumer;

/* JADX INFO: renamed from: android.view.-$$Lambda$Rl1VZmNJ0VZDLK0BAbaVGis0rrA, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$Rl1VZmNJ0VZDLK0BAbaVGis0rrA implements Consumer {
    public static final /* synthetic */ $$Lambda$Rl1VZmNJ0VZDLK0BAbaVGis0rrA INSTANCE = new $$Lambda$Rl1VZmNJ0VZDLK0BAbaVGis0rrA();

    private /* synthetic */ $$Lambda$Rl1VZmNJ0VZDLK0BAbaVGis0rrA() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((SurfaceControl) obj).release();
    }
}
