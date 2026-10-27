package p;

import com.spotify.music.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class tt1 {
    public static final List e = v03.C(new tt1(50, "0.5", R.drawable.mediaservice_vector_playback_speed_0_5, "playbackSpeed-0.5"), new tt1(80, "0.8", R.drawable.mediaservice_vector_playback_speed_0_8, "playbackSpeed-0.8"), new tt1(100, "1.0", R.drawable.mediaservice_vector_playback_speed_1_0, "playbackSpeed-1.0"), new tt1(120, "1.2", R.drawable.mediaservice_vector_playback_speed_1_2, "playbackSpeed-1.2"), new tt1(150, "1.5", R.drawable.mediaservice_vector_playback_speed_1_5, "playbackSpeed-1.5"), new tt1(200, "2.0", R.drawable.mediaservice_vector_playback_speed_2_0, "playbackSpeed-2.0"), new tt1(300, "3.0", R.drawable.mediaservice_vector_playback_speed_3_0, "playbackSpeed-3.0"));
    public final String a;
    public final int b;
    public final String c;
    public final int d;

    public tt1(int i, String str, int i2, String str2) {
        this.a = str;
        this.b = i;
        this.c = str2;
        this.d = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof tt1)) {
            return false;
        }
        tt1 tt1Var = (tt1) obj;
        return v03.b(this.a, tt1Var.a) && this.b == tt1Var.b && v03.b(this.c, tt1Var.c) && this.d == tt1Var.d;
    }

    public final int hashCode() {
        return Integer.hashCode(this.d) + hf2.b(this.c, hf2.a(this.b, this.a.hashCode() * 31, 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PlaybackSpeedAction(name=");
        sb.append(this.a);
        sb.append(", speed=");
        sb.append(this.b);
        sb.append(", action=");
        sb.append(this.c);
        sb.append(", iconRes=");
        return u4.k(sb, this.d, ')');
    }
}
