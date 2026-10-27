package p;

import com.spotify.player.model.command.SkipToPrevTrackCommand;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public final class du1 extends zu {
    public final SkipToPrevTrackCommand i;

    public du1(SkipToPrevTrackCommand skipToPrevTrackCommand) {
        skipToPrevTrackCommand.getClass();
        this.i = skipToPrevTrackCommand;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof du1) {
            return ((du1) obj).i.equals(this.i);
        }
        return false;
    }

    public final int hashCode() {
        return this.i.hashCode();
    }

    @Override // p.zu
    public final Single q0(kh0 kh0Var, kh0 kh0Var2, kh0 kh0Var3, kh0 kh0Var4, kh0 kh0Var5, kh0 kh0Var6) {
        return kh0Var5.a(this);
    }

    public final String toString() {
        return "SkipToPrevTrackWithCommand{command=" + this.i + '}';
    }
}
