package android.app;

import java.util.function.Consumer;

/* JADX INFO: renamed from: android.app.-$$Lambda$dUWXWbBHcaaVBn031EDBP91NR7k, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$dUWXWbBHcaaVBn031EDBP91NR7k implements Consumer {
    public static final /* synthetic */ $$Lambda$dUWXWbBHcaaVBn031EDBP91NR7k INSTANCE = new $$Lambda$dUWXWbBHcaaVBn031EDBP91NR7k();

    private /* synthetic */ $$Lambda$dUWXWbBHcaaVBn031EDBP91NR7k() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((VoiceInteractor) obj).destroy();
    }
}
