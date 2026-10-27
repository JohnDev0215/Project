package p;

/* JADX INFO: loaded from: classes.dex */
public interface n70 extends r91 {
    default void onCreate(s91 s91Var) {
        v03.j(s91Var, "owner");
    }

    default void onDestroy(s91 s91Var) {
    }

    default void onPause(s91 s91Var) {
    }

    default void onResume(s91 s91Var) {
        v03.j(s91Var, "owner");
    }

    default void onStart(s91 s91Var) {
        v03.j(s91Var, "owner");
    }

    default void onStop(s91 s91Var) {
    }
}
