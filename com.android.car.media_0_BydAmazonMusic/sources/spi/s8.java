package spi;

import android.car.utils.Logs;
import android.support.v4.media.session.MediaControllerCompat;
import android.util.Log;
import android.view.View;
import com.android.car.media.PlaybackFragment;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.android.car.media.main.R$bool;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class s8 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ s8(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Long l;
        switch (this.a) {
            case 0:
                PlaybackFragment.QueueViewHolder queueViewHolder = (PlaybackFragment.QueueViewHolder) this.b;
                MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) this.c;
                PlaybackFragment playbackFragment = PlaybackFragment.this;
                PlaybackViewModel.d dVar = playbackFragment.t;
                if (dVar == null || (l = mediaItemMetadata.b) == null) {
                    Log.e("PlaybackFragment", "mController == null or item.getQueueId() == null !!!");
                } else {
                    long jLongValue = l.longValue();
                    StringBuilder sb = new StringBuilder();
                    sb.append("skipToQueueItem,");
                    sb.append(jLongValue);
                    sb.append(dVar.a == null ? ",mMediaController is null" : "");
                    Logs.d("PlaybackViewModel", sb.toString());
                    MediaControllerCompat mediaControllerCompat = dVar.a;
                    if (mediaControllerCompat != null) {
                        mediaControllerCompat.getTransportControls().skipToQueueItem(jLongValue);
                    }
                }
                if (playbackFragment.getResources().getBoolean(R$bool.switch_to_playback_view_when_playable_item_is_clicked)) {
                    playbackFragment.g(false);
                }
                break;
            default:
                y6 y6Var = (y6) this.b;
                d3 d3Var = (d3) this.c;
                y6Var.m.a(d3Var.b, d3Var.c);
                break;
        }
    }
}
