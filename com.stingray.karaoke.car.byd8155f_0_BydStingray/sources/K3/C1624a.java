package k3;

import android.net.Uri;
import android.os.Bundle;
import com.stingray.drivemodedetector.R;
import g5.C1300a;
import h2.AbstractC1346c;

/* JADX INFO: renamed from: k3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1624a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f17877a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public y1 f17878b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Uri f17879e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f17880f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Bundle f17881g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f17882h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C1300a f17883i;

    /* JADX WARN: Illegal instructions before constructor call */
    public C1624a(int i7) {
        int i10;
        String str = C1627b.f17885j;
        switch (i7) {
            case 57369:
                i10 = R.drawable.media3_icon_album;
                break;
            case 57370:
                i10 = R.drawable.media3_icon_artist;
                break;
            case 57372:
                i10 = R.drawable.media3_icon_closed_captions;
                break;
            case 57375:
                i10 = R.drawable.media3_icon_fast_forward;
                break;
            case 57376:
                i10 = R.drawable.media3_icon_rewind;
                break;
            case 57396:
                i10 = R.drawable.media3_icon_pause;
                break;
            case 57399:
                i10 = R.drawable.media3_icon_play;
                break;
            case 57403:
                i10 = R.drawable.media3_icon_playlist_add;
                break;
            case 57408:
                i10 = R.drawable.media3_icon_repeat_all;
                break;
            case 57409:
                i10 = R.drawable.media3_icon_repeat_one;
                break;
            case 57410:
                i10 = R.drawable.media3_icon_skip_back;
                break;
            case 57411:
                i10 = R.drawable.media3_icon_shuffle_on;
                break;
            case 57412:
                i10 = R.drawable.media3_icon_next;
                break;
            case 57413:
                i10 = R.drawable.media3_icon_previous;
                break;
            case 57415:
                i10 = R.drawable.media3_icon_stop;
                break;
            case 57416:
                i10 = R.drawable.media3_icon_subtitles;
                break;
            case 57421:
                i10 = R.drawable.media3_icon_volume_down;
                break;
            case 57423:
                i10 = R.drawable.media3_icon_volume_off;
                break;
            case 57424:
                i10 = R.drawable.media3_icon_volume_up;
                break;
            case 57430:
                i10 = R.drawable.media3_icon_skip_forward_10;
                break;
            case 57431:
                i10 = R.drawable.media3_icon_skip_forward_30;
                break;
            case 57432:
                i10 = R.drawable.media3_icon_skip_forward_5;
                break;
            case 57433:
                i10 = R.drawable.media3_icon_skip_back_10;
                break;
            case 57434:
                i10 = R.drawable.media3_icon_skip_back_30;
                break;
            case 57435:
                i10 = R.drawable.media3_icon_skip_back_5;
                break;
            case 57436:
                i10 = R.drawable.media3_icon_queue_add;
                break;
            case 57446:
                i10 = R.drawable.media3_icon_queue_next;
                break;
            case 57447:
                i10 = R.drawable.media3_icon_queue_remove;
                break;
            case 57448:
                i10 = R.drawable.media3_icon_playback_speed;
                break;
            case 57573:
                i10 = R.drawable.media3_icon_feed;
                break;
            case 57669:
                i10 = R.drawable.media3_icon_plus;
                break;
            case 57671:
                i10 = R.drawable.media3_icon_plus_circle_unfilled;
                break;
            case 57675:
                i10 = R.drawable.media3_icon_block;
                break;
            case 57683:
                i10 = R.drawable.media3_icon_flag_unfilled;
                break;
            case 57691:
                i10 = R.drawable.media3_icon_minus;
                break;
            case 58409:
                i10 = R.drawable.media3_icon_quality;
                break;
            case 58654:
                i10 = R.drawable.media3_icon_radio;
                break;
            case 58919:
                i10 = R.drawable.media3_icon_sync;
                break;
            case 59405:
                i10 = R.drawable.media3_icon_share;
                break;
            case 59448:
                i10 = R.drawable.media3_icon_star_unfilled;
                break;
            case 59494:
                i10 = R.drawable.media3_icon_bookmark_unfilled;
                break;
            case 59500:
                i10 = R.drawable.media3_icon_check_circle_unfilled;
                break;
            case 59517:
                i10 = R.drawable.media3_icon_heart_unfilled;
                break;
            case 59576:
                i10 = R.drawable.media3_icon_settings;
                break;
            case 59611:
                i10 = R.drawable.media3_icon_thumb_down_unfilled;
                break;
            case 59612:
                i10 = R.drawable.media3_icon_thumb_up_unfilled;
                break;
            case 60288:
                i10 = R.drawable.media3_icon_playlist_remove;
                break;
            case 61298:
                i10 = R.drawable.media3_icon_subtitles_off;
                break;
            case 61389:
                i10 = R.drawable.media3_icon_playback_speed_1_0;
                break;
            case 61512:
                i10 = R.drawable.media3_icon_signal;
                break;
            case 61916:
                i10 = R.drawable.media3_icon_closed_captions_off;
                break;
            case 62688:
                i10 = R.drawable.media3_icon_playback_speed_1_5;
                break;
            case 62689:
                i10 = R.drawable.media3_icon_playback_speed_1_2;
                break;
            case 62690:
                i10 = R.drawable.media3_icon_playback_speed_0_5;
                break;
            case 62699:
                i10 = R.drawable.media3_icon_playback_speed_2_0;
                break;
            case 63220:
                i10 = R.drawable.media3_icon_skip_forward;
                break;
            case 1040448:
                i10 = R.drawable.media3_icon_repeat_off;
                break;
            case 1040451:
                i10 = R.drawable.media3_icon_shuffle_star;
                break;
            case 1040452:
                i10 = R.drawable.media3_icon_shuffle_off;
                break;
            case 1040470:
                i10 = R.drawable.media3_icon_skip_forward_15;
                break;
            case 1040473:
                i10 = R.drawable.media3_icon_skip_back_15;
                break;
            case 1040711:
                i10 = R.drawable.media3_icon_plus_circle_filled;
                break;
            case 1040712:
                i10 = R.drawable.media3_icon_minus_circle_filled;
                break;
            case 1040713:
                i10 = R.drawable.media3_icon_minus_circle_unfilled;
                break;
            case 1040723:
                i10 = R.drawable.media3_icon_flag_filled;
                break;
            case 1042488:
                i10 = R.drawable.media3_icon_star_filled;
                break;
            case 1042534:
                i10 = R.drawable.media3_icon_bookmark_filled;
                break;
            case 1042540:
                i10 = R.drawable.media3_icon_check_circle_filled;
                break;
            case 1042557:
                i10 = R.drawable.media3_icon_heart_filled;
                break;
            case 1042651:
                i10 = R.drawable.media3_icon_thumb_down_filled;
                break;
            case 1042652:
                i10 = R.drawable.media3_icon_thumb_up_filled;
                break;
            case 1045728:
                i10 = R.drawable.media3_icon_playback_speed_1_8;
                break;
            case 1045730:
                i10 = R.drawable.media3_icon_playback_speed_0_8;
                break;
            default:
                i10 = 0;
                break;
        }
        this(i7, i10);
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final k3.C1627b a() {
        /*
            r12 = this;
            k3.y1 r0 = r12.f17878b
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L8
            r0 = r2
            goto L9
        L8:
            r0 = r1
        L9:
            int r3 = r12.c
            r4 = -1
            if (r3 != r4) goto L10
            r3 = r2
            goto L11
        L10:
            r3 = r1
        L11:
            if (r0 == r3) goto L14
            r1 = r2
        L14:
            java.lang.String r0 = "Exactly one of sessionCommand and playerCommand should be set"
            h2.AbstractC1346c.i(r0, r1)
            g5.a r0 = r12.f17883i
            if (r0 != 0) goto L9b
            int r0 = r12.c
            java.lang.String r1 = k3.C1627b.f17885j
            if (r0 == r2) goto L90
            r1 = 57399(0xe037, float:8.0433E-41)
            int r3 = r12.f17877a
            if (r3 == r1) goto L90
            r1 = 57396(0xe034, float:8.0429E-41)
            if (r3 != r1) goto L30
            goto L90
        L30:
            r1 = 11
            if (r0 == r1) goto L8f
            r1 = 7
            if (r0 == r1) goto L8f
            r2 = 6
            if (r0 == r2) goto L8f
            r1 = 57413(0xe045, float:8.0453E-41)
            if (r3 == r1) goto L8f
            r1 = 57376(0xe020, float:8.0401E-41)
            if (r3 == r1) goto L8f
            r1 = 57410(0xe042, float:8.0449E-41)
            if (r3 == r1) goto L8f
            r1 = 57435(0xe05b, float:8.0484E-41)
            if (r3 == r1) goto L8f
            r1 = 57433(0xe059, float:8.0481E-41)
            if (r3 == r1) goto L8f
            r1 = 1040473(0xfe059, float:1.458013E-39)
            if (r3 == r1) goto L8f
            r1 = 57434(0xe05a, float:8.0482E-41)
            if (r3 != r1) goto L5e
            goto L8f
        L5e:
            r1 = 12
            if (r0 == r1) goto L8d
            r1 = 9
            if (r0 == r1) goto L8d
            r1 = 8
            if (r0 == r1) goto L8d
            r0 = 57412(0xe044, float:8.0451E-41)
            if (r3 == r0) goto L8d
            r0 = 57375(0xe01f, float:8.04E-41)
            if (r3 == r0) goto L8d
            r0 = 63220(0xf6f4, float:8.859E-41)
            if (r3 == r0) goto L8d
            r0 = 57432(0xe058, float:8.048E-41)
            if (r3 == r0) goto L8d
            r0 = 57430(0xe056, float:8.0477E-41)
            if (r3 == r0) goto L8d
            r0 = 1040470(0xfe056, float:1.458009E-39)
            if (r3 == r0) goto L8d
            r0 = 57431(0xe057, float:8.0478E-41)
            if (r3 != r0) goto L90
        L8d:
            r2 = 3
            goto L90
        L8f:
            r2 = 2
        L90:
            g5.a r0 = new g5.a
            int[] r1 = new int[]{r2}
            r0.<init>(r1)
            r12.f17883i = r0
        L9b:
            k3.b r0 = new k3.b
            k3.y1 r3 = r12.f17878b
            int r4 = r12.c
            int r6 = r12.d
            android.net.Uri r7 = r12.f17879e
            java.lang.CharSequence r8 = r12.f17880f
            android.os.Bundle r9 = r12.f17881g
            boolean r10 = r12.f17882h
            g5.a r11 = r12.f17883i
            int r5 = r12.f17877a
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.C1624a.a():k3.b");
    }

    public final void b(String str) {
        this.f17880f = str;
    }

    public final void c(Bundle bundle) {
        this.f17881g = new Bundle(bundle);
    }

    public final void d(int i7) {
        AbstractC1346c.b("sessionCommand is already set. Only one of sessionCommand and playerCommand should be set.", this.f17878b == null);
        this.c = i7;
    }

    public final void e(y1 y1Var) {
        AbstractC1346c.b("playerCommands is already set. Only one of sessionCommand and playerCommand should be set.", this.c == -1);
        this.f17878b = y1Var;
    }

    public C1624a(int i7, int i10) {
        this.f17877a = i7;
        this.d = i10;
        this.f17880f = "";
        this.f17881g = Bundle.EMPTY;
        this.c = -1;
        this.f17882h = true;
    }
}
