package kotlinx.coroutines.flow;

import kotlinx.coroutines.flow.SharingStarted;
import z5.a;

/* JADX INFO: compiled from: SharingStarted.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SharingStartedKt {
    /* JADX INFO: renamed from: WhileSubscribed-9tZugJw, reason: not valid java name */
    public static final SharingStarted m9WhileSubscribed9tZugJw(SharingStarted.Companion companion, double d7, double d8) {
        return new StartedWhileSubscribed(a.toLongMilliseconds-impl(d7), a.toLongMilliseconds-impl(d8));
    }

    /* JADX INFO: renamed from: WhileSubscribed-9tZugJw$default, reason: not valid java name */
    public static /* synthetic */ SharingStarted m10WhileSubscribed9tZugJw$default(SharingStarted.Companion companion, double d7, double d8, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            d7 = a.f10049g.getZERO-UwyO8pc();
        }
        if ((i7 & 2) != 0) {
            d8 = a.f10049g.getINFINITE-UwyO8pc();
        }
        return m9WhileSubscribed9tZugJw(companion, d7, d8);
    }
}
