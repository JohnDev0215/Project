package b.b.g.q.h.c;

import android.widget.SeekBar;

/* JADX INFO: compiled from: OnSeekBarChangeCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public interface b extends SeekBar.OnSeekBarChangeListener {
    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    default void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    default void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    default void onStopTrackingTouch(SeekBar seekBar) {
    }
}
