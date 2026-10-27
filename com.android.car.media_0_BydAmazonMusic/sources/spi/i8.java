package spi;

import android.view.View;
import com.android.car.apps.common.UxrButton;
import com.android.car.apps.common.UxrTextView;
import com.android.car.ui.R$id;
import com.android.car.ui.R$integer;

/* JADX INFO: compiled from: PlaybackErrorViewController.java */
/* JADX INFO: loaded from: classes.dex */
public final class i8 {
    public final UxrTextView a;
    public final UxrButton b;
    public final int c;

    public i8(View view) {
        this.a = (UxrTextView) view.findViewById(R$id.error_message);
        this.b = (UxrButton) view.findViewById(R$id.error_button);
        this.c = view.getContext().getResources().getInteger(R$integer.new_album_art_fade_in_duration);
    }
}
