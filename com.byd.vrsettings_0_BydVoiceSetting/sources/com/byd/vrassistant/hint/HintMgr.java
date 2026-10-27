package com.byd.vrassistant.hint;

import android.provider.Settings;
import android.util.ArraySet;
import android.util.Log;
import com.byd.vrassistant.hint.bean.Domain;
import com.byd.vrassistant.hint.bean.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/* JADX INFO: loaded from: classes.dex */
public class HintMgr {
    private static final String TAG = "NEU_HintMgr";
    volatile ArraySet<String> availableList = new ArraySet<>();
    Random random = new Random();

    public HintMgr() {
        EventBus.getDefault().register(this);
        reLoadHint();
    }

    @Subscribe
    public void onEvent(NaviUpdateEvent naviUpdateEvent) {
        reLoadHint();
    }

    public ArraySet<String> getAvailableHints() {
        if (this.availableList.size() <= 5) {
            reLoadHint();
        }
        return this.availableList;
    }

    public List<String> getHintShow() {
        Log.i(TAG, "getHintShow: ");
        getAvailableHints();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 2; i++) {
            arrayList.add(getAvailableHints().removeAt(this.random.nextInt(getAvailableHints().size())));
        }
        return arrayList;
    }

    public synchronized void reLoadHint() {
        Log.i(TAG, "reLoadFunction: ");
        this.availableList.clear();
        final ArraySet arraySet = new ArraySet();
        PropertiesUtil.getValueProperties().stringPropertyNames().forEach(new Consumer() { // from class: com.byd.vrassistant.hint.HintMgr$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                HintMgr.lambda$reLoadHint$0(arraySet, (String) obj);
            }
        });
        final ArrayList arrayList = new ArrayList();
        arraySet.forEach(new Consumer() { // from class: com.byd.vrassistant.hint.HintMgr$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                HintMgr.lambda$reLoadHint$4(arrayList, (String) obj);
            }
        });
        arrayList.forEach(new Consumer() { // from class: com.byd.vrassistant.hint.HintMgr$$ExternalSyntheticLambda6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m7lambda$reLoadHint$6$combydvrassistanthintHintMgr((Domain) obj);
            }
        });
        Log.i(TAG, "reLoadHint: " + this.availableList.size());
    }

    static /* synthetic */ void lambda$reLoadHint$0(Set set, String str) {
        if (str.contains("domain_")) {
            set.add(str.replace("domain_", com.chad.library.BuildConfig.FLAVOR));
        }
    }

    static /* synthetic */ void lambda$reLoadHint$4(List list, final String str) {
        Log.i(TAG, "reLoad domainKey = " + str);
        Domain domain = new Domain();
        domain.setKey(str);
        if (!isInstallWithKey(PropertiesUtil.getConfigProperty(str + ".domain_availability"))) {
            Log.i(TAG, "forbid domain key = " + str);
            return;
        }
        String domainName = PropertiesUtil.getDomainName("domain_" + str);
        Log.i(TAG, "reLoadHint: domainName = " + domainName);
        domain.setName(domainName);
        final ArraySet arraySet = new ArraySet();
        PropertiesUtil.getValueProperties().stringPropertyNames().forEach(new Consumer() { // from class: com.byd.vrassistant.hint.HintMgr$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                HintMgr.lambda$reLoadHint$1(str, arraySet, (String) obj);
            }
        });
        final ArrayList arrayList = new ArrayList();
        arraySet.forEach(new Consumer() { // from class: com.byd.vrassistant.hint.HintMgr$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                HintMgr.lambda$reLoadHint$3(arrayList, (String) obj);
            }
        });
        domain.setGroups(arrayList);
        list.add(domain);
    }

    static /* synthetic */ void lambda$reLoadHint$1(String str, Set set, String str2) {
        if (!str2.contains(str) || str2.contains("group") || str2.contains("domain")) {
            return;
        }
        set.add(str2);
    }

    static /* synthetic */ void lambda$reLoadHint$3(List list, String str) {
        Log.i(TAG, "reLoad groupName = " + str);
        Group group = new Group();
        group.setKey(str);
        if (isInstallWithKey(PropertiesUtil.getConfigProperty(str + ".group_availability"))) {
            group.setName(PropertiesUtil.getGroupName(str));
            final ArrayList arrayList = new ArrayList();
            Map<String, String> propertyGroup = PropertiesUtil.getPropertyGroup(str + "_group");
            final Map<String, String> configPropertyGroup = PropertiesUtil.getConfigPropertyGroup(str + ".values_availability");
            propertyGroup.forEach(new BiConsumer() { // from class: com.byd.vrassistant.hint.HintMgr$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    HintMgr.lambda$reLoadHint$2(configPropertyGroup, arrayList, (String) obj, (String) obj2);
                }
            });
            group.setItems(arrayList);
            list.add(group);
        }
    }

    static /* synthetic */ void lambda$reLoadHint$2(Map map, List list, String str, String str2) {
        if (!isInstallWithKey((String) map.get(str))) {
            Log.i(TAG, "forbid hint: key= " + str + ",value= " + str2 + ",config= " + ((String) map.get(str)));
        } else {
            list.add(str2);
        }
    }

    /* JADX INFO: renamed from: lambda$reLoadHint$5$com-byd-vrassistant-hint-HintMgr, reason: not valid java name */
    /* synthetic */ void m6lambda$reLoadHint$5$combydvrassistanthintHintMgr(Group group) {
        this.availableList.addAll(group.getItems());
    }

    /* JADX INFO: renamed from: lambda$reLoadHint$6$com-byd-vrassistant-hint-HintMgr, reason: not valid java name */
    /* synthetic */ void m7lambda$reLoadHint$6$combydvrassistanthintHintMgr(Domain domain) {
        domain.getGroups().forEach(new Consumer() { // from class: com.byd.vrassistant.hint.HintMgr$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m6lambda$reLoadHint$5$combydvrassistanthintHintMgr((Group) obj);
            }
        });
    }

    public static boolean isInstallWithKey(String str) {
        int i;
        Log.i(TAG, "isInstallWithKey: " + str);
        if (str == null || str.isEmpty() || str.contains("null")) {
            return true;
        }
        if (str.contains("|")) {
            String[] strArrSplit = str.split("\\|");
            return isInstallWithKey(strArrSplit[0]) || isInstallWithKey(strArrSplit[1]);
        }
        if ("8155".contains(str)) {
            return true;
        }
        try {
            i = Settings.System.getInt(AppUtils.getApp().getContentResolver(), str);
        } catch (Settings.SettingNotFoundException e) {
            Log.e(TAG, "isInstallWithKey: " + e.getMessage());
            i = 0;
        }
        Log.i(TAG, "isInstallWithKey: key=" + str + " ,install status= " + (i == 1));
        return i == 1;
    }

    public void onDestroy() {
        this.availableList.clear();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
