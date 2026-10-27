package Q5;

import com.stingray.companion.model.PlaylistDataProperties;
import com.stingray.karaoke.car.player.SongQueuePosition;
import com.stingray.karaoke.car.utils.ToastType;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.kca.model.KaraokeItemType;
import com.stingray.shared.logs.segment.ActionOwner;
import i6.C1415d;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import n7.AbstractC1998m;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.o1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0603o1 implements InterfaceC2785n {
    public final /* synthetic */ C1415d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ F6.b f7005m;

    public /* synthetic */ C0603o1(C1415d c1415d, F6.b bVar) {
        this.l = c1415d;
        this.f7005m = bVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        SongQueuePosition songQueuePosition = (SongQueuePosition) obj;
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        A7.m.f(songQueuePosition, "position");
        C1415d c1415d = this.l;
        List<KaraokeItem> list = (List) ((V8.f0) c1415d.f12274x.l).getValue();
        Map map = c1415d.f15775W;
        String str = map != null ? (String) map.get(PlaylistDataProperties.PARTY_MIX_ID.getDataName()) : null;
        Map map2 = c1415d.f15775W;
        String str2 = map2 != null ? (String) map2.get(PlaylistDataProperties.ARTIST_ID.getDataName()) : null;
        A7.m.f(list, "items");
        if (songQueuePosition == SongQueuePosition.SING_NOW) {
            ActionOwner actionOwner = ActionOwner.HOST;
            KaraokeItem karaokeItem = (KaraokeItem) AbstractC1998m.a0(list);
            N5.Z.y(c1415d.f15762J, false, true, actionOwner, false, karaokeItem != null ? A7.m.a(karaokeItem.o, Boolean.TRUE) : false, 8);
        }
        ArrayList arrayList = new ArrayList();
        for (KaraokeItem karaokeItem2 : list) {
            String str3 = karaokeItem2.f13109e == KaraokeItemType.PLAY ? karaokeItem2.f13107a : null;
            if (str3 != null) {
                arrayList.add(str3);
            }
        }
        if (c1415d.f15760H.a(arrayList, songQueuePosition, str, str2, zBooleanValue, this.f7005m)) {
            ToastType toastType = ToastType.ADD_TO_QUEUE;
            A7.m.f(toastType, "toastType");
            h6.e.c(h6.e.d(toastType), new b0.b(-1064300120, new C0618s1(toastType, 2), true));
        }
        return m7.q.f19267a;
    }
}
