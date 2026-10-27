package com.byd.card.model;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.byd.card.base.BaseModel;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.AppOrShortcutItem;
import com.byd.card.bean.SearchHistory;
import com.byd.card.bean.ShortcutInfo;
import com.byd.card.contract.MoreAppContract;
import com.byd.card.db.AppInfoDao;
import com.byd.card.db.CardDatabase;
import com.byd.card.db.SearchHistoryDao;
import com.byd.card.db.ShortcutInfoDao;
import com.byd.card.floatcard.Executors;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes.dex */
public class MoreAppModel extends BaseModel implements MoreAppContract.Model {
    private static final int DELETE_ALL_HISTORY = 1006;
    private static final int DELETE_SINGLE_HISTORY = 1005;
    private static final int GET_APP_LIST = 1000;
    private static final int GET_RECENT_APP_LIST = 1003;
    private static final int GET_WORKSPACE_LIST = 1007;
    private static final int INSERT_APP_LIST = 1002;
    private static final int INSERT_HISTORY = 1004;
    private static final int SEARCH_APP_LIST = 1001;
    private static final String TAG = "MoreAppModel";
    private static final int UPDATE_WORKSPACE_LIST = 1008;
    private String searchThreadName = "search-app-thread";
    private String queryThreadName = "query-app-thread";
    private String insertThreadName = "insert-history-thread";
    private String queryWorkSpaceThread = "query-workspace-thread";
    private String updateThreadName = "update-workspace-thread";

    @Override // com.byd.card.contract.MoreAppContract.Model
    public void queryRecentList(final MoreAppContract.View view) {
        new Thread(new Runnable() { // from class: com.byd.card.model.-$$Lambda$MoreAppModel$qN6m-X5OM_hjT-rleE2WeZ3ecw8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$queryRecentList$1$MoreAppModel(view);
            }
        }).start();
    }

    public /* synthetic */ void lambda$queryRecentList$1$MoreAppModel(MoreAppContract.View view) {
        List<AppInfo> recentList = CardDatabase.getInstance().getAppInfoDao().getRecentList(1);
        List<ShortcutInfo> recentList2 = CardDatabase.getInstance().getShortcutInfoDao().getRecentList(1);
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(recentList);
        arrayList.addAll(recentList2);
        Collections.sort(arrayList, new Comparator<AppOrShortcutItem>() { // from class: com.byd.card.model.MoreAppModel.1
            @Override // java.util.Comparator
            public int compare(AppOrShortcutItem o1, AppOrShortcutItem o2) {
                if (o1 == null || o2 == null) {
                    return 0;
                }
                return o1.getRecentTime() > o2.getRecentTime() ? -1 : 1;
            }
        });
        if (arrayList.size() > 6) {
            arrayList.removeIf(new Predicate() { // from class: com.byd.card.model.-$$Lambda$MoreAppModel$eL_PlRuC1ZLTQt_XdF_8jsIblPw
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return MoreAppModel.lambda$queryRecentList$0(arrayList, (AppOrShortcutItem) obj);
                }
            });
        }
        view.onQueryRecentResult(arrayList);
    }

    static /* synthetic */ boolean lambda$queryRecentList$0(List list, AppOrShortcutItem appOrShortcutItem) {
        return list.indexOf(appOrShortcutItem) > 5;
    }

    @Override // com.byd.card.contract.MoreAppContract.Model
    public void search(final String inputTxt, final MoreAppContract.View view) {
        final HandlerThread handlerThread = new HandlerThread(this.searchThreadName);
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper()) { // from class: com.byd.card.model.MoreAppModel.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (TextUtils.isEmpty(inputTxt)) {
                    view.onSearchResult(null, inputTxt);
                    return;
                }
                AppInfoDao appInfoDao = CardDatabase.getInstance().getAppInfoDao();
                List<AppInfo> listForExact = appInfoDao.getListForExact(inputTxt);
                List<AppInfo> listForStart = appInfoDao.getListForStart(inputTxt);
                List<AppInfo> listForSearch = appInfoDao.getListForSearch(inputTxt);
                ShortcutInfoDao shortcutInfoDao = CardDatabase.getInstance().getShortcutInfoDao();
                List<ShortcutInfo> listForExact2 = shortcutInfoDao.getListForExact(inputTxt);
                List<ShortcutInfo> listForStart2 = shortcutInfoDao.getListForStart(inputTxt);
                List<ShortcutInfo> listForSearch2 = shortcutInfoDao.getListForSearch(inputTxt);
                List<AppOrShortcutItem> arrayList = new ArrayList<>();
                if (listForExact != null) {
                    arrayList.addAll(listForExact);
                }
                if (listForExact2 != null) {
                    arrayList.addAll(listForExact2);
                }
                if (listForStart != null) {
                    arrayList.addAll(listForStart);
                }
                if (listForStart2 != null) {
                    arrayList.addAll(listForStart2);
                }
                if (listForSearch != null) {
                    arrayList.addAll(listForSearch);
                }
                if (listForSearch2 != null) {
                    arrayList.addAll(listForSearch2);
                }
                if (arrayList.size() > 0) {
                    arrayList = (List) Stream.of(arrayList).flatMap(new Function() { // from class: com.byd.card.model.-$$Lambda$2uajARPg7zs3QXdN3ekutEAqAgM
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return ((List) obj).stream();
                        }
                    }).distinct().collect(Collectors.toList());
                }
                view.onSearchResult(arrayList, inputTxt);
                handlerThread.quit();
            }
        };
        Message messageObtain = Message.obtain();
        messageObtain.what = 1001;
        handler.sendMessage(messageObtain);
    }

    @Override // com.byd.card.contract.MoreAppContract.Model
    public void searchHistory(final MoreAppContract.View view) {
        final HandlerThread handlerThread = new HandlerThread(this.searchThreadName);
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper()) { // from class: com.byd.card.model.MoreAppModel.3
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                List<SearchHistory> history = CardDatabase.getInstance().getSearchHistoryDao().getHistory();
                MoreAppContract.View view2 = view;
                if (view2 != null) {
                    view2.onSearchHistory(history);
                }
                handlerThread.quit();
            }
        };
        Message messageObtain = Message.obtain();
        messageObtain.what = 1002;
        handler.sendMessage(messageObtain);
    }

    @Override // com.byd.card.contract.MoreAppContract.Model
    public void deleteHistory(final SearchHistory searchHistory, final MoreAppContract.View view) {
        final HandlerThread handlerThread = new HandlerThread(this.searchThreadName);
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper()) { // from class: com.byd.card.model.MoreAppModel.4
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                SearchHistoryDao searchHistoryDao = CardDatabase.getInstance().getSearchHistoryDao();
                searchHistoryDao.delete(searchHistory);
                List<SearchHistory> history = searchHistoryDao.getHistory();
                MoreAppContract.View view2 = view;
                if (view2 != null) {
                    view2.onDeleteHistory(history, searchHistory);
                }
                handlerThread.quit();
            }
        };
        Message messageObtain = Message.obtain();
        messageObtain.what = 1005;
        handler.sendMessage(messageObtain);
    }

    @Override // com.byd.card.contract.MoreAppContract.Model
    public void deleteAllHistory(final MoreAppContract.View view) {
        final HandlerThread handlerThread = new HandlerThread(this.searchThreadName);
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper()) { // from class: com.byd.card.model.MoreAppModel.5
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                SearchHistoryDao searchHistoryDao = CardDatabase.getInstance().getSearchHistoryDao();
                List<SearchHistory> allHistory = searchHistoryDao.getAllHistory();
                SearchHistory[] searchHistoryArr = new SearchHistory[allHistory.size()];
                allHistory.toArray(searchHistoryArr);
                searchHistoryDao.delete(searchHistoryArr);
                MoreAppContract.View view2 = view;
                if (view2 != null) {
                    view2.onDeleteAllHistory();
                }
                handlerThread.quit();
            }
        };
        Message messageObtain = Message.obtain();
        messageObtain.what = 1006;
        handler.sendMessage(messageObtain);
    }

    @Override // com.byd.card.contract.MoreAppContract.Model
    public void insertHistory(final AppOrShortcutItem item) {
        final HandlerThread handlerThread = new HandlerThread(this.insertThreadName);
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper()) { // from class: com.byd.card.model.MoreAppModel.6
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                long jCurrentTimeMillis = System.currentTimeMillis();
                SearchHistory historyByName = CardDatabase.getInstance().getSearchHistoryDao().getHistoryByName(item.getItemName());
                if (historyByName == null) {
                    CardDatabase.getInstance().getSearchHistoryDao().insert(new SearchHistory(item.getItemName(), jCurrentTimeMillis));
                } else {
                    historyByName.time = jCurrentTimeMillis;
                    CardDatabase.getInstance().getSearchHistoryDao().update(historyByName);
                }
                handlerThread.quit();
            }
        };
        Message messageObtain = Message.obtain();
        messageObtain.what = 1004;
        handler.sendMessage(messageObtain);
    }

    @Override // com.byd.card.contract.MoreAppContract.Model
    public void insertHistory(final String input) {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.model.MoreAppModel.7
            @Override // java.lang.Runnable
            public void run() {
                long jCurrentTimeMillis = System.currentTimeMillis();
                SearchHistory historyByName = CardDatabase.getInstance().getSearchHistoryDao().getHistoryByName(input);
                if (historyByName == null) {
                    CardDatabase.getInstance().getSearchHistoryDao().insert(new SearchHistory(input, jCurrentTimeMillis));
                } else {
                    historyByName.time = jCurrentTimeMillis;
                    CardDatabase.getInstance().getSearchHistoryDao().update(historyByName);
                }
            }
        });
    }

    private List<AppInfo> sort(List<AppInfo> list) {
        if (list == null) {
            return null;
        }
        final Collator collator = Collator.getInstance(Locale.ENGLISH);
        list.sort(new Comparator<AppInfo>() { // from class: com.byd.card.model.MoreAppModel.8
            @Override // java.util.Comparator
            public int compare(AppInfo o1, AppInfo o2) {
                return collator.compare(o1.getName().trim(), o2.getName().trim());
            }
        });
        return list;
    }
}
