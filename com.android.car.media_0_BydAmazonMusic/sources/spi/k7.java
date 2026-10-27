package spi;

import android.view.View;
import androidx.lifecycle.Observer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class k7 implements Observer {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ View c;

    public /* synthetic */ k7(Object obj, View view, int i) {
        this.a = i;
        this.b = obj;
        this.c = view;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002d  */
    @Override // androidx.lifecycle.Observer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onChanged(java.lang.Object r8) {
        /*
            r7 = this;
            int r0 = r7.a
            r1 = 0
            r2 = 1
            switch(r0) {
                case 0: goto L8;
                default: goto L7;
            }
        L7:
            goto L59
        L8:
            java.lang.Object r0 = r7.b
            com.android.car.media.common.a r0 = (com.android.car.media.common.a) r0
            android.view.View r7 = r7.c
            android.widget.SeekBar r7 = (android.widget.SeekBar) r7
            com.android.car.media.common.playback.PlaybackViewModel$e r8 = (com.android.car.media.common.playback.PlaybackViewModel.e) r8
            java.util.Objects.requireNonNull(r0)
            if (r8 == 0) goto L2d
            android.support.v4.media.session.PlaybackStateCompat r8 = r8.c
            long r3 = r8.getActions()
            r5 = 256(0x100, double:1.265E-321)
            long r3 = r3 & r5
            r5 = 0
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 == 0) goto L28
            r8 = r2
            goto L29
        L28:
            r8 = r1
        L29:
            if (r8 == 0) goto L2d
            r8 = r2
            goto L2e
        L2d:
            r8 = r1
        L2e:
            r0.c = r1
            android.graphics.drawable.Drawable r0 = r7.getThumb()
            if (r0 == 0) goto L47
            android.graphics.drawable.Drawable r0 = r7.getThumb()
            android.graphics.drawable.Drawable r0 = r0.mutate()
            if (r8 == 0) goto L43
            r3 = 255(0xff, float:3.57E-43)
            goto L44
        L43:
            r3 = r1
        L44:
            r0.setAlpha(r3)
        L47:
            android.graphics.drawable.Drawable r0 = r7.getThumb()
            if (r0 == 0) goto L50
            if (r8 == 0) goto L50
            r1 = r2
        L50:
            spi.h7 r8 = new spi.h7
            r8.<init>()
            r7.setOnTouchListener(r8)
            return
        L59:
            java.lang.Object r0 = r7.b
            com.android.car.media.common.PlaybackFragment r0 = (com.android.car.media.common.PlaybackFragment) r0
            android.view.View r7 = r7.c
            com.android.car.media.common.PlaybackControlsActionBar r7 = (com.android.car.media.common.PlaybackControlsActionBar) r7
            com.android.car.media.common.playback.PlaybackViewModel$e r8 = (com.android.car.media.common.playback.PlaybackViewModel.e) r8
            int r3 = com.android.car.media.common.PlaybackFragment.j
            java.util.Objects.requireNonNull(r0)
            if (r8 == 0) goto L82
            android.support.v4.media.session.PlaybackStateCompat r3 = r8.c
            int r3 = r3.getState()
            if (r3 == 0) goto L7e
            android.support.v4.media.MediaMetadataCompat r3 = r8.b
            if (r3 != 0) goto L7c
            int r3 = r8.b()
            if (r3 == 0) goto L7e
        L7c:
            r3 = r2
            goto L7f
        L7e:
            r3 = r1
        L7f:
            if (r3 == 0) goto L82
            r1 = r2
        L82:
            spi.uc.e(r7, r1)
            com.android.car.media.common.PlaybackFragment$a r7 = r0.h
            if (r7 == 0) goto L8e
            java.lang.String r0 = "PlaybackFragmentWidget"
            r7.c(r0, r8, r2)
        L8e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.k7.onChanged(java.lang.Object):void");
    }
}
