package Q5;

import com.stingray.karaoke.car.player.SongQueuePosition;
import com.stingray.karaoke.car.ui.player.PlaybackType;
import com.stingray.karaoke.car.ui.player.SideMenuType;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class G1 implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6369m;

    public /* synthetic */ G1(int i7, InterfaceC2782k interfaceC2782k) {
        this.l = i7;
        this.f6369m = interfaceC2782k;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                this.f6369m.b(SongQueuePosition.SING_NOW);
                break;
            case 1:
                this.f6369m.b(SongQueuePosition.SING_LAST);
                break;
            case 2:
                this.f6369m.b(SongQueuePosition.SING_NOW);
                break;
            case 3:
                this.f6369m.b(SongQueuePosition.SING_LAST);
                break;
            case 4:
                this.f6369m.b("");
                break;
            case 5:
                this.f6369m.b(PlaybackType.SKIP_PREVIOUS);
                break;
            case 6:
                this.f6369m.b(PlaybackType.PLAY_PAUSE);
                break;
            case 7:
                this.f6369m.b(PlaybackType.SKIP_NEXT);
                break;
            case 8:
                this.f6369m.b(Boolean.FALSE);
                break;
            case 9:
                this.f6369m.b(SideMenuType.SONG_QUEUE);
                break;
            case 10:
                this.f6369m.b(SideMenuType.COMPANION);
                break;
            case 11:
                this.f6369m.b(SideMenuType.COMPANION);
                break;
            case 12:
                this.f6369m.b(new A3.v(7));
                break;
            case 13:
                this.f6369m.b(SideMenuType.SONG_QUEUE);
                break;
            case 14:
                this.f6369m.b(SideMenuType.COMPANION);
                break;
            default:
                this.f6369m.b(SideMenuType.COMPANION);
                break;
        }
        return m7.q.f19267a;
    }
}
