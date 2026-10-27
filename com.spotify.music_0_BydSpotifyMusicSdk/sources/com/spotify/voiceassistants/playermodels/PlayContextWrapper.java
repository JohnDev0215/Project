package com.spotify.voiceassistants.playermodels;

import com.spotify.player.model.Context;
import com.spotify.player.model.PlayOrigin;
import com.spotify.player.model.command.options.PreparePlayOptions;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class PlayContextWrapper {
    private final Context context;
    private final PreparePlayOptions playOptions;
    private final PlayOrigin playOrigin;

    public PlayContextWrapper(Context context, PreparePlayOptions preparePlayOptions, PlayOrigin playOrigin) {
        this.context = context;
        this.playOptions = preparePlayOptions;
        this.playOrigin = playOrigin;
    }

    public static /* synthetic */ PlayContextWrapper copy$default(PlayContextWrapper playContextWrapper, Context context, PreparePlayOptions preparePlayOptions, PlayOrigin playOrigin, int i, Object obj) {
        if ((i & 1) != 0) {
            context = playContextWrapper.context;
        }
        if ((i & 2) != 0) {
            preparePlayOptions = playContextWrapper.playOptions;
        }
        if ((i & 4) != 0) {
            playOrigin = playContextWrapper.playOrigin;
        }
        return playContextWrapper.copy(context, preparePlayOptions, playOrigin);
    }

    public final Context component1() {
        return this.context;
    }

    public final PreparePlayOptions component2() {
        return this.playOptions;
    }

    public final PlayOrigin component3() {
        return this.playOrigin;
    }

    public final PlayContextWrapper copy(Context context, PreparePlayOptions preparePlayOptions, PlayOrigin playOrigin) {
        return new PlayContextWrapper(context, preparePlayOptions, playOrigin);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayContextWrapper)) {
            return false;
        }
        PlayContextWrapper playContextWrapper = (PlayContextWrapper) obj;
        return v03.b(this.context, playContextWrapper.context) && v03.b(this.playOptions, playContextWrapper.playOptions) && v03.b(this.playOrigin, playContextWrapper.playOrigin);
    }

    public final Context getContext() {
        return this.context;
    }

    public final PreparePlayOptions getPlayOptions() {
        return this.playOptions;
    }

    public final PlayOrigin getPlayOrigin() {
        return this.playOrigin;
    }

    public int hashCode() {
        Context context = this.context;
        int iHashCode = (context == null ? 0 : context.hashCode()) * 31;
        PreparePlayOptions preparePlayOptions = this.playOptions;
        int iHashCode2 = (iHashCode + (preparePlayOptions == null ? 0 : preparePlayOptions.hashCode())) * 31;
        PlayOrigin playOrigin = this.playOrigin;
        return iHashCode2 + (playOrigin != null ? playOrigin.hashCode() : 0);
    }

    public String toString() {
        return "PlayContextWrapper(context=" + this.context + ", playOptions=" + this.playOptions + ", playOrigin=" + this.playOrigin + ')';
    }
}
