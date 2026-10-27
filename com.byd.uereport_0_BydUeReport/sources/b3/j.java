package b3;

/* JADX INFO: compiled from: IBtStateChangedCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface j extends a {
    void onBtOff();

    void onBtOn();

    default void onBtTurningOff() {
    }

    default void onBtTurningOn() {
    }
}
