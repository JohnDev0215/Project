package androidx.test.runner.intent;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface IntentMonitor {
    void addIntentCallback(IntentCallback intentCallback);

    void removeIntentCallback(IntentCallback intentCallback);
}
