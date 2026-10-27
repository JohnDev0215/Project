package p;

import com.spotify.player.model.command.SkipToNextTrackCommand;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public final class cu1 extends zu {
    public final SkipToNextTrackCommand i;

    public cu1(SkipToNextTrackCommand skipToNextTrackCommand) {
        skipToNextTrackCommand.getClass();
        this.i = skipToNextTrackCommand;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof cu1) {
            return ((cu1) obj).i.equals(this.i);
        }
        return false;
    }

    public final int hashCode() {
        return this.i.hashCode();
    }

    @Override // p.zu
    public final Single q0(kh0 kh0Var, kh0 kh0Var2, kh0 kh0Var3, kh0 kh0Var4, kh0 kh0Var5, kh0 kh0Var6) {
        return kh0Var4.a(this);
    }

    public final String toString() {
        return "SkipToNextTrackWithCommand{command=" + this.i + '}';
    }
}
