package com.amazon.mp3.download.widevineMigration;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.DownloadListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WidevineDownloadStateListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u0007J\u0006\u0010\u000e\u001a\u00020\u0007J$\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J$\u0010\u000f\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J \u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J$\u0010\u0019\u001a\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0011\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u001c\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/download/widevineMigration/WidevineDownloadStateListener;", "Lcom/amazon/music/downloads/notification/DownloadListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "downloadStateMap", "", "", "groupIds", "", "[Ljava/lang/String;", "lastItemError", "Lkotlin/Pair;", "getDownloadStateString", "getLastErrorString", "onProgressUpdate", "", "groupId", "group", "Lcom/amazon/music/downloads/Group;", NotificationCompat.CATEGORY_PROGRESS, "", "itemId", "item", "Lcom/amazon/music/downloads/Item;", "onStatusUpdate", "downloadState", "Lcom/amazon/music/downloads/DownloadState;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WidevineDownloadStateListener extends DownloadListener {
    private final Map<String, String> downloadStateMap;
    private final String[] groupIds;
    private Pair<String, String> lastItemError;

    /* JADX INFO: compiled from: WidevineDownloadStateListener.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DownloadState.values().length];
            iArr[DownloadState.ERROR.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onProgressUpdate(String groupId, Group group, float progress) {
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onProgressUpdate(String itemId, Item item, float progress) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WidevineDownloadStateListener(Context context) {
        super(true);
        Intrinsics.checkNotNullParameter(context, "context");
        this.downloadStateMap = new LinkedHashMap();
        this.groupIds = new String[]{context.getResources().getString(R.string.dmusic_redownload_group_name_tracks), context.getResources().getString(R.string.dmusic_redownload_group_name_prime_playlists)};
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onStatusUpdate(DownloadState downloadState, String groupId, Group group) {
        Intrinsics.checkNotNullParameter(groupId, "groupId");
        if (ArraysKt___ArraysKt.contains(this.groupIds, groupId)) {
            if ((downloadState == null ? -1 : WhenMappings.$EnumSwitchMapping$0[downloadState.ordinal()]) == 1) {
                Map<String, String> map = this.downloadStateMap;
                StringBuilder sb = new StringBuilder();
                sb.append("DownloadState = ");
                sb.append(downloadState);
                sb.append(", ERROR REASON = ");
                sb.append(group == null ? null : group.getErrorReason());
                map.put(groupId, sb.toString());
                return;
            }
            this.downloadStateMap.put(groupId, Intrinsics.stringPlus("DownloadState = ", downloadState));
        }
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onStatusUpdate(DownloadState downloadState, String itemId, Item item) {
        Intrinsics.checkNotNullParameter(downloadState, "downloadState");
        Intrinsics.checkNotNullParameter(itemId, "itemId");
        Intrinsics.checkNotNullParameter(item, "item");
        if (WhenMappings.$EnumSwitchMapping$0[downloadState.ordinal()] == 1) {
            this.lastItemError = TuplesKt.to(itemId, "DownloadState = " + downloadState + ", ERROR REASON = " + item.getErrorReason());
        }
    }

    public final String getDownloadStateString() {
        Map<String, String> map = this.downloadStateMap;
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(entry.getKey() + ": " + entry.getValue());
        }
        return CollectionsKt___CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, new Function1<String, CharSequence>() { // from class: com.amazon.mp3.download.widevineMigration.WidevineDownloadStateListener.getDownloadStateString.2
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return '\'' + it + '\'';
            }
        }, 31, null);
    }

    public final String getLastErrorString() {
        if (this.lastItemError == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Pair<String, String> pair = this.lastItemError;
        sb.append((Object) (pair == null ? null : pair.getFirst()));
        sb.append(": ");
        Pair<String, String> pair2 = this.lastItemError;
        sb.append((Object) (pair2 != null ? pair2.getSecond() : null));
        return sb.toString();
    }
}
