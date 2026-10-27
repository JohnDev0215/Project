package com.byd.card.model;

import com.android.systemui.SystemUIApplication;
import com.byd.card.base.BaseModel;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.AppOrShortcutItem;
import com.byd.card.bean.ShortcutInfo;
import com.byd.card.contract.AddCardContract;
import com.byd.card.db.CardDatabase;
import com.byd.card.floatcard.CardItemBean;
import com.byd.card.floatcard.Executors;
import com.byd.card.floatcard.SpUtils;
import com.byd.card.utils.AppUtils;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class AddCardModel extends BaseModel implements AddCardContract.Model {
    private static final String TAG = "AddCardModel";

    @Override // com.byd.card.contract.AddCardContract.Model
    public void queryData(final AddCardContract.View view) {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.model.AddCardModel.1
            @Override // java.lang.Runnable
            public void run() {
                List<AppInfo> allAppExceptPkg = CardDatabase.getInstance().getAppInfoDao().getAllAppExceptPkg();
                List<ShortcutInfo> allShortcut = CardDatabase.getInstance().getShortcutInfoDao().getAllShortcut();
                final List arrayList = new ArrayList();
                arrayList.addAll(allAppExceptPkg);
                arrayList.addAll(allShortcut);
                List checkList = AddCardModel.this.getCheckList();
                if (!checkList.isEmpty()) {
                    arrayList = AddCardModel.this.listRem(arrayList, checkList);
                } else {
                    AddCardModel.this.sortChinese(arrayList);
                }
                AddCardModel.this.removePackage(arrayList, "com.ts.carplay.app");
                AddCardModel.this.removePackage(arrayList, "com.ts.androidauto.app");
                Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.model.AddCardModel.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (view != null) {
                            view.onUpdateView(arrayList);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<AppOrShortcutItem> getCheckList() {
        ShortcutInfo shortcutByIdentify;
        ArrayList arrayList = new ArrayList();
        List<CardItemBean> cardList = SpUtils.getCardList(SystemUIApplication.getInstance().getApplicationContext());
        if (!cardList.isEmpty()) {
            for (CardItemBean cardItemBean : cardList) {
                if (cardItemBean != null) {
                    String type = cardItemBean.getType();
                    if (CardItemBean.TYPE_CARD_APP.equals(type)) {
                        AppInfo appInfoByPackage = CardDatabase.getInstance().getAppInfoDao().getAppInfoByPackage(cardItemBean.getPackageName());
                        if (appInfoByPackage != null) {
                            appInfoByPackage.checked = true;
                        }
                        arrayList.add(appInfoByPackage);
                    } else if (CardItemBean.TYPE_CARD_SHORTCUT.equals(type) && (shortcutByIdentify = CardDatabase.getInstance().getShortcutInfoDao().getShortcutByIdentify(AppUtils.extractShortcutIdentify(cardItemBean.getPackageName()), AppUtils.extractShortcutOrigin(cardItemBean.getPackageName()))) != null) {
                        shortcutByIdentify.checked = true;
                        arrayList.add(shortcutByIdentify);
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<AppOrShortcutItem> listRem(List<AppOrShortcutItem> allApps, final List<AppOrShortcutItem> checkList) {
        Objects.requireNonNull(checkList);
        allApps.removeIf(new Predicate() { // from class: com.byd.card.model.-$$Lambda$YzH6bxzrHxaPB-dJoF_p-HaRHag
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return checkList.contains((AppOrShortcutItem) obj);
            }
        });
        sortChinese(allApps);
        allApps.addAll(0, checkList);
        return allApps;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sortChinese(List<AppOrShortcutItem> appInfoList) {
        final Collator collator = Collator.getInstance(Locale.ENGLISH);
        appInfoList.sort(new Comparator<AppOrShortcutItem>() { // from class: com.byd.card.model.AddCardModel.2
            @Override // java.util.Comparator
            public int compare(AppOrShortcutItem o1, AppOrShortcutItem o2) {
                return collator.compare(o1.getItemName().trim(), o2.getItemName().trim());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removePackage(List<AppOrShortcutItem> appInfoList, String packageName) {
        for (AppOrShortcutItem appOrShortcutItem : appInfoList) {
            if (appOrShortcutItem != null && packageName.equals(appOrShortcutItem.getItemIdentify())) {
                appInfoList.remove(appOrShortcutItem);
                return;
            }
        }
    }
}
