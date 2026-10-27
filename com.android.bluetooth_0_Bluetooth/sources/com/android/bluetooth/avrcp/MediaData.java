package com.android.bluetooth.avrcp;

import android.media.session.PlaybackState;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
class MediaData {
    public Metadata metadata;
    public List<Metadata> queue;
    public PlaybackState state;

    MediaData(Metadata m, PlaybackState s, List<Metadata> q) {
        this.metadata = m;
        this.state = s;
        this.queue = q;
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof MediaData)) {
            return false;
        }
        MediaData u = (MediaData) o;
        if (!MediaPlayerWrapper.playstateEquals(this.state, u.state) || !Objects.equals(this.metadata, u.metadata) || !Objects.equals(this.queue, u.queue)) {
            return false;
        }
        return true;
    }
}
