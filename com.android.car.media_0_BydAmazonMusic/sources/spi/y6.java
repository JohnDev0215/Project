package spi;

import android.car.utils.Logs;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.PlayPauseStopImageView;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$id;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: MediaButtonController.java */
/* JADX INFO: loaded from: classes.dex */
public final class y6 {
    public final Map<String, ImageButton> a = new HashMap();
    public Context b;
    public PlayPauseStopImageView c;
    public View d;
    public ProgressBar e;
    public ImageButton f;
    public ImageButton g;
    public ColorStateList h;
    public boolean i;
    public boolean j;
    public boolean k;
    public PlaybackViewModel l;
    public PlaybackViewModel.d m;
    public c1 n;

    public y6(Context context, c1 c1Var, @ColorRes int i, @LayoutRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
        this.b = context;
        this.n = c1Var;
        View viewInflate = LayoutInflater.from(context).inflate(i2, (ViewGroup) null);
        this.d = viewInflate;
        final int i5 = 0;
        viewInflate.setOnClickListener(new View.OnClickListener(this) { // from class: spi.v6
            public final /* synthetic */ y6 b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlaybackViewModel.e value;
                switch (i5) {
                    case 0:
                    case 1:
                        y6.a(this.b);
                        break;
                    case 2:
                        y6 y6Var = this.b;
                        PlaybackViewModel playbackViewModel = y6Var.l;
                        value = playbackViewModel != null ? playbackViewModel.j.getValue() : null;
                        if (y6Var.m != null && value != null && value.f()) {
                            y6Var.m.g();
                            break;
                        }
                        break;
                    default:
                        y6 y6Var2 = this.b;
                        PlaybackViewModel playbackViewModel2 = y6Var2.l;
                        value = playbackViewModel2 != null ? playbackViewModel2.j.getValue() : null;
                        if (y6Var2.m != null && value != null && value.e()) {
                            y6Var2.m.f();
                            break;
                        }
                        break;
                }
            }
        });
        PlayPauseStopImageView playPauseStopImageView = (PlayPauseStopImageView) this.d.findViewById(R$id.play_pause_stop);
        this.c = playPauseStopImageView;
        playPauseStopImageView.setVisibility(4);
        this.e = (ProgressBar) this.d.findViewById(R$id.circular_progress_bar);
        this.c.setAction(0);
        final int i6 = 1;
        this.c.setOnClickListener(new View.OnClickListener(this) { // from class: spi.v6
            public final /* synthetic */ y6 b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlaybackViewModel.e value;
                switch (i6) {
                    case 0:
                    case 1:
                        y6.a(this.b);
                        break;
                    case 2:
                        y6 y6Var = this.b;
                        PlaybackViewModel playbackViewModel = y6Var.l;
                        value = playbackViewModel != null ? playbackViewModel.j.getValue() : null;
                        if (y6Var.m != null && value != null && value.f()) {
                            y6Var.m.g();
                            break;
                        }
                        break;
                    default:
                        y6 y6Var2 = this.b;
                        PlaybackViewModel playbackViewModel2 = y6Var2.l;
                        value = playbackViewModel2 != null ? playbackViewModel2.j.getValue() : null;
                        if (y6Var2.m != null && value != null && value.e()) {
                            y6Var2.m.f();
                            break;
                        }
                        break;
                }
            }
        });
        this.c.setFocusedByDefault(true);
        this.k = context.getResources().getBoolean(R$bool.show_circular_progress_bar);
        this.h = context.getResources().getColorStateList(i, null);
        ImageButton imageButtonB = b(context.getDrawable(i3));
        this.f = imageButtonB;
        imageButtonB.setId(R$id.skip_prev);
        this.f.setVisibility(0);
        final int i7 = 2;
        this.f.setOnClickListener(new View.OnClickListener(this) { // from class: spi.v6
            public final /* synthetic */ y6 b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlaybackViewModel.e value;
                switch (i7) {
                    case 0:
                    case 1:
                        y6.a(this.b);
                        break;
                    case 2:
                        y6 y6Var = this.b;
                        PlaybackViewModel playbackViewModel = y6Var.l;
                        value = playbackViewModel != null ? playbackViewModel.j.getValue() : null;
                        if (y6Var.m != null && value != null && value.f()) {
                            y6Var.m.g();
                            break;
                        }
                        break;
                    default:
                        y6 y6Var2 = this.b;
                        PlaybackViewModel playbackViewModel2 = y6Var2.l;
                        value = playbackViewModel2 != null ? playbackViewModel2.j.getValue() : null;
                        if (y6Var2.m != null && value != null && value.e()) {
                            y6Var2.m.f();
                            break;
                        }
                        break;
                }
            }
        });
        ImageButton imageButtonB2 = b(context.getDrawable(i4));
        this.g = imageButtonB2;
        imageButtonB2.setId(R$id.skip_next);
        this.g.setVisibility(0);
        final int i8 = 3;
        this.g.setOnClickListener(new View.OnClickListener(this) { // from class: spi.v6
            public final /* synthetic */ y6 b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlaybackViewModel.e value;
                switch (i8) {
                    case 0:
                    case 1:
                        y6.a(this.b);
                        break;
                    case 2:
                        y6 y6Var = this.b;
                        PlaybackViewModel playbackViewModel = y6Var.l;
                        value = playbackViewModel != null ? playbackViewModel.j.getValue() : null;
                        if (y6Var.m != null && value != null && value.f()) {
                            y6Var.m.g();
                            break;
                        }
                        break;
                    default:
                        y6 y6Var2 = this.b;
                        PlaybackViewModel playbackViewModel2 = y6Var2.l;
                        value = playbackViewModel2 != null ? playbackViewModel2.j.getValue() : null;
                        if (y6Var2.m != null && value != null && value.e()) {
                            y6Var2.m.f();
                            break;
                        }
                        break;
                }
            }
        });
        c();
    }

    public static void a(y6 y6Var) {
        if (y6Var.m == null) {
            return;
        }
        StringBuilder sbK = c.k("onPlayPauseStopClicked getAction:");
        sbK.append(y6Var.c.getAction());
        Logs.i("MediaButton", sbK.toString());
        int action = y6Var.c.getAction();
        if (action == 1) {
            y6Var.m.c();
            return;
        }
        if (action == 2) {
            y6Var.m.h();
        } else if (action != 3) {
            Log.i("MediaButton", "Play/Pause/Stop clicked on invalid state");
        } else {
            y6Var.m.b();
        }
    }

    public final ImageButton b(Drawable drawable) {
        ImageButton imageButtonB = this.n.b(drawable);
        if (!h2.a(this.b).b() || (drawable instanceof VectorDrawable)) {
            imageButtonB.setImageTintList(this.h);
        } else {
            imageButtonB.setImageTintList(ColorStateList.valueOf(MediaItemMetadata.h));
        }
        imageButtonB.setImageTintMode(PorterDuff.Mode.SRC_ATOP);
        return imageButtonB;
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.HashMap, java.util.Map<java.lang.String, android.widget.ImageButton>] */
    public final void c() {
        this.n.setViews(new View[0]);
        this.n.a(this.d, 0);
        this.n.a(null, 1);
        this.n.a(null, 2);
        this.i = false;
        this.j = false;
        this.a.clear();
    }

    public final void d(@NonNull PlaybackViewModel playbackViewModel, @NonNull LifecycleOwner lifecycleOwner) {
        if (this.l != null) {
            Log.w("MediaButton", "PlaybackViewModel set more than once. Ignoring subsequent call.");
        }
        this.l = playbackViewModel;
        final int i = 0;
        playbackViewModel.i.observe(lifecycleOwner, new Observer(this) { // from class: spi.w6
            public final /* synthetic */ y6 b;

            {
                this.b = this;
            }

            /* JADX WARN: Removed duplicated region for block: B:64:0x00d6  */
            /* JADX WARN: Removed duplicated region for block: B:90:0x0160  */
            @Override // androidx.lifecycle.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void onChanged(java.lang.Object r13) {
                /*
                    Method dump skipped, instruction units count: 764
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: spi.w6.onChanged(java.lang.Object):void");
            }
        });
        this.c.setVisibility(0);
        if (this.b.getResources().getBoolean(R$bool.use_media_source_color_for_fab_spinner)) {
            final int i2 = 1;
            playbackViewModel.d.observe(lifecycleOwner, new Observer(this) { // from class: spi.w6
                public final /* synthetic */ y6 b;

                {
                    this.b = this;
                }

                /* JADX WARN: Removed duplicated region for block: B:64:0x00d6  */
                /* JADX WARN: Removed duplicated region for block: B:90:0x0160  */
                @Override // androidx.lifecycle.Observer
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void onChanged(java.lang.Object r13) {
                    /*
                        Method dump skipped, instruction units count: 764
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: spi.w6.onChanged(java.lang.Object):void");
                }
            });
        }
        final int i3 = 2;
        playbackViewModel.j.observe(lifecycleOwner, new Observer(this) { // from class: spi.w6
            public final /* synthetic */ y6 b;

            {
                this.b = this;
            }

            /* JADX WARN: Removed duplicated region for block: B:64:0x00d6  */
            /* JADX WARN: Removed duplicated region for block: B:90:0x0160  */
            @Override // androidx.lifecycle.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void onChanged(java.lang.Object r13) {
                /*
                    Method dump skipped, instruction units count: 764
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: spi.w6.onChanged(java.lang.Object):void");
            }
        });
    }
}
