package ts.car.service.carplay;

import java.util.List;
import ts.car.service.carplay.data.FavoriteContactListInfo;
import ts.car.service.carplay.data.RecentContactListInfo;

/* JADX INFO: loaded from: classes5.dex */
public interface IContactListListener {
    void onFavoriteListChanged(List<FavoriteContactListInfo> list);

    void onFavoriteListCountChanged(int i);

    void onRecentListChanged(List<RecentContactListInfo> list);

    void onRecentListCountChanged(int i);
}
