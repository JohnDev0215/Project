package spi;

import android.car.utils.Logs;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import androidx.annotation.NonNull;
import com.byd.amazon.music.bean.MusicItem;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: MusicVoiceControlImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class o7 extends MediaBrowserCompat.SearchCallback {
    public final /* synthetic */ String a;
    public final /* synthetic */ String b;
    public final /* synthetic */ p4 c;
    public final /* synthetic */ p7 d;

    public o7(p7 p7Var, String str, String str2, p4 p4Var) {
        this.d = p7Var;
        this.a = str;
        this.b = str2;
        this.c = p4Var;
    }

    @Override // android.support.v4.media.MediaBrowserCompat.SearchCallback
    public final void onError(@NonNull String str, Bundle bundle) {
        super.onError(str, bundle);
        try {
            p4 p4Var = this.c;
            if (p4Var != null) {
                p4Var.b(str, "");
            } else {
                Logs.e("MusicVoiceControlImpl", "IMusicItemsCallBack callback is null");
            }
            Logs.e("MusicVoiceControlImpl", "searchByVoice onError : query = " + this.a);
        } catch (RemoteException e) {
            StringBuilder sbK = c.k("playBy ");
            sbK.append(this.a);
            sbK.append(" onError: error: ");
            sbK.append(e.getMessage());
            Logs.e("MusicVoiceControlImpl", sbK.toString());
        }
    }

    @Override // android.support.v4.media.MediaBrowserCompat.SearchCallback
    public final void onSearchResult(@NonNull String str, Bundle bundle, @NonNull List<MediaBrowserCompat.MediaItem> list) {
        super.onSearchResult(str, bundle, list);
        if (Objects.equals(this.a, str)) {
            List list2 = (List) list.stream().filter(n7.b).map(d0.c).collect(Collectors.toList());
            List<String> list3 = (List) list2.stream().filter(new f0(new ConcurrentHashMap(), d0.d, 1)).map(d0.e).collect(Collectors.toList());
            String strA = this.d.a(this.b, list3);
            Logs.d("MusicVoiceControlImpl", "tabGroupList = " + list3 + " convert vodType = " + this.b + " to queryFilter = " + strA + " hasContains = " + list3.contains(strA));
            if (!list3.contains(strA)) {
                Logs.w("MusicVoiceControlImpl", "Voice filterTag is not exit !!!! and  tabGroupList = " + list3 + " queryFilter = " + strA);
            }
            Objects.requireNonNull(this.d);
            List<MusicItem> list4 = (List) ((List) list2.stream().filter(new e0(strA, 1)).collect(Collectors.toList())).stream().filter(n7.c).map(d0.f).collect(Collectors.toList());
            try {
                StringBuilder sb = new StringBuilder("Voice onSearchResult: ");
                sb.append(" query = ");
                sb.append(str);
                sb.append(" filter = ");
                sb.append(strA);
                sb.append(" size = ");
                sb.append(list4.size());
                sb.append("\n");
                for (MusicItem musicItem : list4) {
                    sb.append("title = ");
                    sb.append(musicItem.b);
                    sb.append(" mediaId = ");
                    sb.append(musicItem.a);
                    sb.append("\n");
                }
                Logs.d("MusicVoiceControlImpl", sb.toString());
                p4 p4Var = this.c;
                if (p4Var != null) {
                    p4Var.a(str, list4);
                } else {
                    Logs.e("MusicVoiceControlImpl", "IMusicItemsCallBack callback is null");
                }
            } catch (Exception e) {
                StringBuilder sbK = c.k("playBy ");
                sbK.append(this.a);
                sbK.append(" onResult: error: ");
                sbK.append(e.getMessage());
                Logs.e("MusicVoiceControlImpl", sbK.toString());
            }
        }
    }
}
