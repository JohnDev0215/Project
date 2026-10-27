package android.app;

import java.util.function.Consumer;

/* JADX INFO: renamed from: android.app.-$$Lambda$230aDqJr4aat1ISm-J6P2msVj1c, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$230aDqJr4aat1ISmJ6P2msVj1c implements Consumer {
    public static final /* synthetic */ $$Lambda$230aDqJr4aat1ISmJ6P2msVj1c INSTANCE = new $$Lambda$230aDqJr4aat1ISmJ6P2msVj1c();

    private /* synthetic */ $$Lambda$230aDqJr4aat1ISmJ6P2msVj1c() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((VoiceInteractor) obj).destroy();
    }
}
