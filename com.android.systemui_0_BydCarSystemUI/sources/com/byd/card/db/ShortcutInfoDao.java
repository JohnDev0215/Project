package com.byd.card.db;

import com.byd.card.bean.ShortcutInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ShortcutInfoDao {
    void delete(ShortcutInfo... shortcutInfos);

    void deleteList(List<ShortcutInfo> shortcutInfos);

    void deleteShortcutByOrigin(String origin);

    List<ShortcutInfo> getAllShortcut();

    List<ShortcutInfo> getListForExact(String inputText);

    List<ShortcutInfo> getListForSearch(String inputText);

    List<ShortcutInfo> getListForStart(String inputText);

    List<ShortcutInfo> getRecentList(int recent);

    ShortcutInfo getShortcutByIdentify(String identify, String origin);

    List<ShortcutInfo> getShortcutByPGearFlag(int pGearFlag);

    void insert(ShortcutInfo... shortcutInfos);

    void insertList(List<ShortcutInfo> shortcutInfos);

    void update(ShortcutInfo... shortcutInfos);

    void updateList(List<ShortcutInfo> shortcutInfos);
}
