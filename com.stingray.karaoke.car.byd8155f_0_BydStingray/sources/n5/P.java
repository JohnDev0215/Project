package N5;

import S8.AbstractC0727y;
import com.stingray.companion.CompanionReceiveListener;
import com.stingray.karaoke.car.player.PlayerActionType;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.shared.logs.segment.ActionOwner;

/* JADX INFO: loaded from: classes.dex */
public final class P implements CompanionReceiveListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Z f4614a;

    public P(Z z6) {
        this.f4614a = z6;
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onAddSongs(String[] strArr, String str, String str2, int i7, boolean z6, boolean z10, boolean z11, boolean z12, Integer num) {
        A7.m.f(strArr, "songIds");
        if (z6 || z12) {
            this.f4614a.o = ActionOwner.COMPANION;
        }
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onClearQueue() {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onCommandReceived(String str) {
        A7.m.f(str, "info");
        int iHashCode = str.hashCode();
        Z z6 = this.f4614a;
        switch (iHashCode) {
            case -1273775369:
                if (str.equals("previous") && !((PlayerStatus) z6.f4634M.getValue()).getBlockCompanionActions()) {
                    ActionOwner actionOwner = ActionOwner.COMPANION;
                    A7.m.f(actionOwner, "actionOwner");
                    z6.d(new C0451g(PlayerActionType.SkipPrevious, new C0463t(z6, actionOwner, null, false)));
                }
                break;
            case -995321554:
                if (str.equals("paused") && !((PlayerStatus) z6.f4634M.getValue()).getBlockCompanionActions()) {
                    z6.s(ActionOwner.COMPANION);
                }
                break;
            case -892481550:
                if (str.equals("status")) {
                    AbstractC0727y.w(z6.f4626E, null, null, new N(z6, null), 3);
                    break;
                }
                break;
            case 3532159:
                if (str.equals("skip") && !((PlayerStatus) z6.f4634M.getValue()).getBlockCompanionActions()) {
                    z6.E(true, ActionOwner.COMPANION);
                }
                break;
            case 1097547223:
                if (str.equals("resumed") && !((PlayerStatus) z6.f4634M.getValue()).getBlockCompanionActions()) {
                    z6.t(ActionOwner.COMPANION);
                }
                break;
        }
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onConnectionChanged() {
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onMoveSong(String str, int i7, int i10) {
        A7.m.f(str, "songId");
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onRemoveSong(String str, int i7) {
        A7.m.f(str, "songId");
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onSeek(long j3) {
        Z z6 = this.f4614a;
        AbstractC0727y.w(z6.f4626E, null, null, new O(z6, j3, null), 3);
    }

    @Override // com.stingray.companion.CompanionReceiveListener
    public final void onSelectSong(String str, int i7, boolean z6) {
        A7.m.f(str, "songId");
        this.f4614a.o = ActionOwner.COMPANION;
    }
}
