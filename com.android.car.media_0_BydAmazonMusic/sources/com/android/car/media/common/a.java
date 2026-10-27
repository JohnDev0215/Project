package com.android.car.media.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Size;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.android.car.ui.R$string;
import java.util.Objects;
import spi.c6;
import spi.e6;
import spi.i7;
import spi.k7;
import spi.x4;

/* JADX INFO: compiled from: MetadataController.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public PlaybackViewModel.d a;
    public final x4<MediaItemMetadata.b> b;
    public boolean c;
    public C0007a d = new C0007a();

    /* JADX INFO: renamed from: com.android.car.media.common.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MetadataController.java */
    public class C0007a implements SeekBar.OnSeekBarChangeListener {
        public C0007a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            PlaybackViewModel.d dVar = a.this.a;
            if (dVar == null || !z) {
                return;
            }
            dVar.e(seekBar.getProgress());
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStartTrackingTouch(SeekBar seekBar) {
            a.this.c = true;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStopTrackingTouch(SeekBar seekBar) {
            PlaybackViewModel.d dVar;
            a aVar = a.this;
            if (aVar.c && (dVar = aVar.a) != null) {
                dVar.e(seekBar.getProgress());
            }
            a.this.c = false;
        }
    }

    public a(@NonNull LifecycleOwner lifecycleOwner, @NonNull PlaybackViewModel playbackViewModel, @NonNull final TextView textView, @Nullable final TextView textView2, @Nullable final TextView textView3, @Nullable TextView textView4, @Nullable final TextView textView5, @Nullable final TextView textView6, @Nullable final TextView textView7, @Nullable final SeekBar seekBar, @Nullable final ImageView imageView, Size size) {
        final Context context = textView.getContext();
        this.b = new x4<>(size, imageView);
        final int i = 0;
        playbackViewModel.i.observe(lifecycleOwner, new Observer(this) { // from class: spi.j7
            public final /* synthetic */ com.android.car.media.common.a b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i) {
                    case 0:
                        this.b.a = (PlaybackViewModel.d) obj;
                        break;
                    default:
                        this.b.c = false;
                        break;
                }
            }
        });
        playbackViewModel.e.observe(lifecycleOwner, new Observer() { // from class: spi.l7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                com.android.car.media.common.a aVar = this.a;
                TextView textView8 = textView;
                TextView textView9 = textView2;
                TextView textView10 = textView3;
                ImageView imageView2 = imageView;
                Context context2 = context;
                MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                Objects.requireNonNull(aVar);
                if (mediaItemMetadata == null) {
                    uc.e(textView8, false);
                    uc.e(textView9, false);
                    uc.e(textView10, false);
                    uc.e(imageView2, false);
                    return;
                }
                CharSequence charSequenceF = mediaItemMetadata.f();
                if (TextUtils.isEmpty(charSequenceF)) {
                    charSequenceF = context2.getString(R$string.metadata_default_title);
                }
                textView8.setText(charSequenceF);
                uc.e(textView8, true);
                if (textView9 != null) {
                    textView9.setText(mediaItemMetadata.f);
                    uc.e(textView9, !TextUtils.isEmpty(r1));
                }
                uc.e(imageView2, true);
                aVar.b.d(context2, mediaItemMetadata.g);
            }
        });
        playbackViewModel.l.observe(lifecycleOwner, new Observer() { // from class: spi.m7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                com.android.car.media.common.a aVar = this.a;
                TextView textView8 = textView5;
                TextView textView9 = textView6;
                TextView textView10 = textView7;
                SeekBar seekBar2 = seekBar;
                u8 u8Var = (u8) obj;
                Objects.requireNonNull(aVar);
                boolean zD = u8Var.d();
                uc.e(textView8, zD);
                uc.e(textView9, zD);
                uc.e(textView10, zD);
                if (textView8 != null && !TextUtils.equals(textView8.getText(), u8Var.b())) {
                    textView8.setText(u8Var.b());
                }
                if (textView10 != null && !TextUtils.equals(textView10.getText(), u8Var.c())) {
                    textView10.setText(u8Var.c());
                }
                if (seekBar2 != null) {
                    seekBar2.setVisibility(zD ? 0 : 4);
                    seekBar2.setMax((int) u8Var.b);
                    if (aVar.c) {
                        return;
                    }
                    seekBar2.setProgress((int) u8Var.a);
                }
            }
        });
        final int i2 = 1;
        if (seekBar != null) {
            playbackViewModel.j.observe(lifecycleOwner, new k7(this, seekBar, 0));
            seekBar.setOnSeekBarChangeListener(this.d);
            playbackViewModel.j.observe(lifecycleOwner, new Observer(this) { // from class: spi.j7
                public final /* synthetic */ com.android.car.media.common.a b;

                {
                    this.b = this;
                }

                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    switch (i2) {
                        case 0:
                            this.b.a = (PlaybackViewModel.d) obj;
                            break;
                        default:
                            this.b.c = false;
                            break;
                    }
                }
            });
        }
        if (textView4 != null) {
            new e6.a(playbackViewModel.e, playbackViewModel.l, c6.c).observe(lifecycleOwner, new i7(textView4, 0));
        }
        if (textView3 != null) {
            new e6.a(playbackViewModel.e, playbackViewModel.l, c6.b).observe(lifecycleOwner, new i7(textView3, 1));
        }
    }
}
