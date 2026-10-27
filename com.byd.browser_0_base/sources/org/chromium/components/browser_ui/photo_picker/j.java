package org.chromium.components.browser_ui.photo_picker;

import android.media.MediaPlayer;
import android.widget.ImageView;

/* JADX INFO: compiled from: PickerVideoPlayer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j implements MediaPlayer.OnCompletionListener {
    public final /* synthetic */ PickerVideoPlayer a;

    public j(PickerVideoPlayer pickerVideoPlayer) {
        this.a = pickerVideoPlayer;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
        boolean z = PickerVideoPlayer.E;
        int i = R$drawable.ic_play_circle_filled_white_24dp;
        PickerVideoPlayer pickerVideoPlayer = this.a;
        ImageView imageView = pickerVideoPlayer.o;
        imageView.setImageResource(i);
        imageView.setContentDescription(pickerVideoPlayer.f.getResources().getString(R$string.accessibility_play_video));
        pickerVideoPlayer.h();
        pickerVideoPlayer.e(0, false);
    }
}
