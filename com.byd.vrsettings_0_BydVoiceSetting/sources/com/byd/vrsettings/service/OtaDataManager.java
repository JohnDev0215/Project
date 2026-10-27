package com.byd.vrsettings.service;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.vrdownload.model.DownloadTask;
import com.byd.vrdownload.model.GetLanguagesUpdate;
import com.byd.vrdownload.model.LanguageName;
import com.byd.vrdownload.model.LanguageUpdate;
import com.byd.vrdownload.model.PkgVersion;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.eventbus.PrivacyPolicyEvent;
import com.byd.vrsettings.eventbus.SwitchEvent;
import com.byd.vrsettings.interf.LanguageListResultCallBack;
import com.byd.vrsettings.model.AutoEnableData;
import com.byd.vrsettings.model.LanguageBean;
import com.byd.vrsettings.model.LanguageLocal;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.FileUtil;
import com.byd.vrsettings.utils.ForegroundAppUtil;
import com.byd.vrsettings.utils.GSON;
import com.byd.vrsettings.utils.LanguageUtil;
import com.byd.vrsettings.utils.LogUtils;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.utils.SharedPreferencesUtils;
import com.byd.vrsettings.utils.Tools;
import com.byd.vrsettings.view.LanguageView;
import com.chad.library.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class OtaDataManager {
    public static final String DATA_PATH = "/navi/BYDAssistant_CR/data";
    private static final String PATTEN_DEFAULT_YMD = "yyyy-MM-dd";
    public static final int SWITCH_TIMEOUT_TIME = 30000;
    private static final String TAG = "NEU_OtaDataManager";
    public static final String USA_DATA_PATH = "/system/etc/BydVoice/data/data_eng-USA";
    public static boolean isSpaceSimple = false;
    private static boolean isSwitching = false;
    public static boolean isTest = false;
    private static Toast mToast;
    private static HashMap<String, String> swithMap = new HashMap<>();
    private static HashMap<String, String> deleteMap = new HashMap<>();
    private static HashMap<String, LanguageLocal> waitInstallMap = new HashMap<>();
    static List<Integer> spaCodeAry = Arrays.asList(47, 45, 31, 44, 351, 32, 46, 41, 39, 34, 358, 353, 49, 33, 352, 354, 381, 389, 377);
    static List<Integer> porCodeAry = Arrays.asList(47, 45, 31, 44, 351, 32, 46, 41, 39, 34, 358, 353, 49, 33, 352, 354, 377);
    public static List<String> mOtaUseLanguages = new ArrayList(Arrays.asList("data_eng-USA", "data_nor-NOR", "data_spa-XLA", "data_spa_ESP", "data_por-BRA", "data_por-PRT", "data_deu-DEU", "data_fra-FRA", "data_jpn-JPN", "data_tha-THA", "data_cmn-CHN", "data_yue-HKG", "data_ita-ITA", "data_kor-KOR", "data_tur-TUR", "data_swe-SWE", "data_dan-DNK", "data_nld-NLD", "data_heb-ISR"));
    public static List<LanguageLocal> currentSwitchLan = new ArrayList();
    private static Handler mHandler = new Handler();
    private static TimeOutRunnable mRunnable = new TimeOutRunnable();

    private static boolean is8155() {
        return true;
    }

    public static List<LanguageLocal> originalLocalData(boolean z) {
        new ArrayList();
        String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_LANGUAG_DPS);
        if (z) {
            LogUtils.i("OtaDataManager", "原始 localJson==" + string);
        }
        List<LanguageLocal> listFilterLanguage = filterLanguage(string);
        if (z) {
            LogUtils.i("OtaDataManager", "过滤后 localJson==" + new Gson().toJson(listFilterLanguage));
        }
        return listFilterLanguage;
    }

    public static List<LanguageLocal> filterLanguage(String str) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            List<LanguageLocal> list = (List) new Gson().fromJson(str, new TypeToken<List<LanguageLocal>>() { // from class: com.byd.vrsettings.service.OtaDataManager.1
            }.getType());
            HashMap map = new HashMap();
            for (LanguageLocal languageLocal : list) {
                if (!TextUtils.isEmpty(languageLocal.convertName())) {
                    String strConvertName = languageLocal.convertName();
                    if (map.containsKey(strConvertName) && languageLocal.getUsed() == 1) {
                        map.put(strConvertName, languageLocal);
                    } else if (!map.containsKey(strConvertName)) {
                        map.put(strConvertName, languageLocal);
                    }
                }
            }
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.add(0, (LanguageLocal) ((Map.Entry) it.next()).getValue());
            }
        }
        return arrayList;
    }

    public static List<LanguageLocal> getInstallFailList() {
        ArrayList arrayList = new ArrayList();
        String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_LANGUAG_FAIL");
        return !TextUtils.isEmpty(string) ? (List) new Gson().fromJson(string, new TypeToken<List<LanguageLocal>>() { // from class: com.byd.vrsettings.service.OtaDataManager.2
        }.getType()) : arrayList;
    }

    public static synchronized void singlePkgInstallComplete(LanguageLocal languageLocal) {
        boolean zBooleanValue = ((Boolean) SharedPreferencesUtils.getParam(BydVoiceSettingApplication.getContext(), languageLocal.convertName(), false)).booleanValue();
        LogUtils.e("wens", languageLocal.getName() + ",singlePkgInstallComplete  :" + zBooleanValue);
        if (zBooleanValue) {
            BydVoiceSettingApplication context = BydVoiceSettingApplication.getContext();
            SharedPreferencesUtils.setParam(context, languageLocal.convertName(), false);
            String str = String.format(context.getString(R.string.text_toast_download_complete), LanguageUtil.convertAll(context, languageLocal.getName()));
            if (ForegroundAppUtil.isForegroundApp(context)) {
                showToast(str);
            } else {
                NotifyManager.getInstance().saveCache(-3, languageLocal.convertName()).createNotify(-3, null);
            }
            LogUtils.i("wens", "安装成功：" + str);
        }
    }

    public static synchronized void singlePkgInstallFail(LanguageLocal languageLocal) {
        SharedPreferencesUtils.setParam(BydVoiceSettingApplication.getContext(), languageLocal.convertName(), false);
        NotifyManager.getInstance().saveCache(-1, languageLocal.getName()).createNotify(-1, null);
    }

    public static List<LanguageBean> checkOtaUpgrade(List<LanguageUpdate> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<LanguageBean> transformLocalData = getTransformLocalData();
        List<LanguageBean> transformRemoteData = getTransformRemoteData(list);
        for (int i = 0; i < transformRemoteData.size(); i++) {
            String language = transformRemoteData.get(i).getLanguageRemote().getLanguage();
            for (int i2 = 0; i2 < transformLocalData.size(); i2++) {
                if (language.equals(transformLocalData.get(i2).getLanguageLocal().convertName())) {
                    transformLocalData.get(i2).setLanguageRemote(transformRemoteData.get(i).getLanguageRemote());
                    if (transformLocalData.get(i2).isUpdate()) {
                        arrayList.add(transformLocalData.get(i2));
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: com.byd.vrsettings.service.OtaDataManager$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ LanguageListResultCallBack val$listResultCallBack;
        final /* synthetic */ List val$originalRemoteDatas;

        AnonymousClass3(List list, LanguageListResultCallBack languageListResultCallBack) {
            this.val$originalRemoteDatas = list;
            this.val$listResultCallBack = languageListResultCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<LanguageBean> transformLocalDataExcludeSort = OtaDataManager.getTransformLocalDataExcludeSort();
            List<DownloadTask> allTask = AidlServiceManager.getInstance().getAllTask();
            List list = this.val$originalRemoteDatas;
            if (list == null || list.size() == 0) {
                OtaDataManager.combineTaskLocalData(allTask, this.val$listResultCallBack);
                LogUtils.e(OtaDataManager.TAG, " observer localList:" + new Gson().toJson(transformLocalDataExcludeSort));
                return;
            }
            List<LanguageBean> taskToRemoteData = OtaDataManager.getTaskToRemoteData(allTask);
            List<LanguageBean> arrayList = new ArrayList<>();
            arrayList.addAll(transformLocalDataExcludeSort);
            List<LanguageBean> transformRemoteData = OtaDataManager.getTransformRemoteData(this.val$originalRemoteDatas);
            final ArrayList arrayList2 = new ArrayList();
            LogUtils.i("wens", "combineOberverLanguageData resultList：" + arrayList.size());
            LogUtils.i("wens", "combineOberverLanguageData transformRemoteDatas：" + transformRemoteData.size());
            for (int i = 0; i < transformRemoteData.size(); i++) {
                String language = transformRemoteData.get(i).getLanguageRemote().getLanguage();
                int i2 = 0;
                while (true) {
                    if (i2 >= transformLocalDataExcludeSort.size()) {
                        break;
                    }
                    LanguageLocal languageLocal = transformLocalDataExcludeSort.get(i2).getLanguageLocal();
                    if (language.equals(languageLocal.convertName())) {
                        arrayList.get(i2).setSource(1);
                        arrayList.get(i2).setLanguageRemote(transformRemoteData.get(i).getLanguageRemote());
                        arrayList.get(i2).setState(arrayList.get(i2).isUpdate() ? 3 : 4);
                        OtaDataManager.combineTask(languageLocal.convertName(), taskToRemoteData, arrayList.get(i2), arrayList2);
                    } else {
                        i2++;
                    }
                }
                if (OtaDataManager.isNewRemoteDataExcludeInstall(language)) {
                    LanguageBean languageBeanSearchRemoteDataByName = OtaDataManager.searchRemoteDataByName(language, this.val$originalRemoteDatas);
                    OtaDataManager.combineTask(language, taskToRemoteData, languageBeanSearchRemoteDataByName, arrayList2);
                    arrayList.add(languageBeanSearchRemoteDataByName);
                }
            }
            List list2 = (List) taskToRemoteData.stream().filter(new Predicate() { // from class: com.byd.vrsettings.service.OtaDataManager$3$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return OtaDataManager.AnonymousClass3.lambda$run$0(arrayList2, (LanguageBean) obj);
                }
            }).collect(Collectors.toList());
            arrayList.addAll(list2);
            LogUtils.i("wens", "差集为：" + new Gson().toJson(list2));
            LanguageBean languageBean = null;
            for (LanguageBean languageBean2 : arrayList) {
                if (languageBean2.getSource() != 0) {
                    boolean zBooleanValue = ((Boolean) SharedPreferencesUtils.getParam(BydVoiceSettingApplication.getContext(), languageBean2.getLanguageRemote().getLanguage(), false)).booleanValue();
                    languageBean2.setState(zBooleanValue ? 7 : languageBean2.getState());
                    LogUtils.d("wens", "isInstall:" + zBooleanValue + "==" + languageBean2.getLanguageRemote().getLanguage() + ",state:" + languageBean2.getState());
                }
                OtaDataManager.dealLocalSize(languageBean2);
                if ((languageBean2.getLanguageLocal() != null && languageBean2.getLanguageLocal().getName() != null && languageBean2.getLanguageLocal().getName().equals(LanguageName.ENG)) || (languageBean2.getLanguageRemote() != null && languageBean2.getLanguageRemote().getLanguage() != null && languageBean2.getLanguageRemote().getLanguage().equals(LanguageName.ENG))) {
                    languageBean = languageBean2;
                }
            }
            if (languageBean != null) {
                arrayList.remove(languageBean);
                arrayList.add(0, languageBean);
            }
            LogUtils.e(OtaDataManager.TAG, " observer resultList:" + new Gson().toJson(arrayList));
            this.val$listResultCallBack.onResult(arrayList);
        }

        static /* synthetic */ boolean lambda$run$0(List list, LanguageBean languageBean) {
            return !list.contains(languageBean);
        }
    }

    public static void combineOberverLanguageData(List<LanguageUpdate> list, LanguageListResultCallBack languageListResultCallBack) {
        RLog.i("wens", "combineOberverLanguageData");
        new Thread(new AnonymousClass3(list, languageListResultCallBack)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void combineTask(String str, List<LanguageBean> list, LanguageBean languageBean, List<LanguageBean> list2) {
        LanguageBean languageBeanFindTask = findTask(str, list);
        if (languageBeanFindTask != null) {
            languageBean.setTaskId(languageBeanFindTask.getTaskId());
            languageBean.setState(languageBeanFindTask.getState());
            languageBean.setProgress(languageBeanFindTask.getProgress());
            list2.add(languageBeanFindTask);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void combineTaskLocalData(final List<DownloadTask> list, final LanguageListResultCallBack languageListResultCallBack) {
        RLog.i(TAG, "combineTaskLocalData");
        new Thread(new Runnable() { // from class: com.byd.vrsettings.service.OtaDataManager.4
            @Override // java.lang.Runnable
            public void run() {
                List<LanguageBean> transformLocalDataExcludeSort = OtaDataManager.getTransformLocalDataExcludeSort();
                List list2 = list;
                if (list2 == null || list2.size() == 0) {
                    Iterator<LanguageBean> it = transformLocalDataExcludeSort.iterator();
                    while (it.hasNext()) {
                        OtaDataManager.dealLocalSize(it.next());
                    }
                    LogUtils.e(OtaDataManager.TAG, "combineTaskLocalData localList:" + new Gson().toJson(transformLocalDataExcludeSort));
                    languageListResultCallBack.onResult(transformLocalDataExcludeSort);
                    return;
                }
                List<LanguageBean> arrayList = new ArrayList<>();
                arrayList.addAll(transformLocalDataExcludeSort);
                List<LanguageBean> taskToRemoteData = OtaDataManager.getTaskToRemoteData(list);
                LogUtils.i(OtaDataManager.TAG, "localTaskDatas：" + taskToRemoteData.size());
                for (int i = 0; i < transformLocalDataExcludeSort.size(); i++) {
                    LanguageBean languageBean = transformLocalDataExcludeSort.get(i);
                    LanguageBean languageBeanFindTask = OtaDataManager.findTask(languageBean.getLanguageLocal().convertName(), taskToRemoteData);
                    if (languageBeanFindTask != null && languageBean.getLanguageLocal().convertName().equals(languageBeanFindTask.getLanguageRemote().getLanguage())) {
                        arrayList.remove(i);
                        arrayList.add(i, languageBeanFindTask);
                        taskToRemoteData.remove(languageBeanFindTask);
                    }
                }
                arrayList.addAll(taskToRemoteData);
                LanguageBean languageBean2 = null;
                for (LanguageBean languageBean3 : arrayList) {
                    if (languageBean3.getSource() != 0) {
                        boolean zBooleanValue = ((Boolean) SharedPreferencesUtils.getParam(BydVoiceSettingApplication.getContext(), languageBean3.getLanguageRemote().getLanguage(), false)).booleanValue();
                        languageBean3.setState(zBooleanValue ? 7 : languageBean3.getState());
                        LogUtils.d("wens", "isInstall:" + zBooleanValue + "==" + languageBean3.getLanguageRemote().getLanguage() + ",state:" + languageBean3.getState());
                    }
                    OtaDataManager.dealLocalSize(languageBean3);
                    if ((languageBean3.getLanguageLocal() != null && languageBean3.getLanguageLocal().getName() != null && languageBean3.getLanguageLocal().getName().equals(LanguageName.ENG)) || (languageBean3.getLanguageRemote() != null && languageBean3.getLanguageRemote().getLanguage() != null && languageBean3.getLanguageRemote().getLanguage().equals(LanguageName.ENG))) {
                        languageBean2 = languageBean3;
                    }
                }
                if (languageBean2 != null) {
                    arrayList.remove(languageBean2);
                    arrayList.add(0, languageBean2);
                }
                LogUtils.e(OtaDataManager.TAG, "resultList:" + new Gson().toJson(arrayList));
                languageListResultCallBack.onResult(arrayList);
            }
        }).start();
    }

    public static List<LanguageBean> updateDeleteLanguageData(List<LanguageBean> list, LanguageLocal languageLocal) {
        ArrayList arrayList = new ArrayList();
        LanguageBean languageBean = null;
        for (LanguageBean languageBean2 : list) {
            if (languageBean2.getLanguageLocal() != null && languageBean2.getLanguageLocal().getName().equals(languageLocal.getName())) {
                languageBean2.setLanguageLocal(null);
                languageBean2.setState(0);
                languageBean2.setSource(2);
                languageBean = languageBean2;
            } else {
                arrayList.add(languageBean2);
            }
        }
        if (languageBean != null) {
            arrayList.add(languageBean);
        }
        return arrayList;
    }

    public static List<LanguageBean> updateInstallLanguageData(List<LanguageBean> list, LanguageLocal languageLocal) {
        ArrayList arrayList = new ArrayList();
        int iFindUsedLanguage = findUsedLanguage(list);
        if (languageLocal.getType() == 3) {
            for (LanguageBean languageBean : list) {
                if (languageBean.getLanguageRemote() != null && languageBean.getLanguageRemote().getLanguage().equals(languageLocal.convertName())) {
                    languageBean.setState(4);
                    languageBean.setSource(0);
                    languageLocal.setPath("/navi/BYDAssistant_CR/data/" + languageLocal.getName());
                    languageLocal.setState(-1);
                    languageLocal.setType(-1);
                    languageBean.setLanguageLocal(languageLocal);
                    arrayList.add(iFindUsedLanguage + 1, languageBean);
                } else {
                    arrayList.add(languageBean);
                }
            }
        } else if (languageLocal.getType() == 4) {
            for (LanguageBean languageBean2 : list) {
                if (languageBean2.getLanguageLocal() != null && languageBean2.getLanguageLocal().getUsed() == 1) {
                    LanguageLocal languageLocal2 = languageBean2.getLanguageLocal();
                    languageLocal2.setUsed(0);
                    languageBean2.setLanguageLocal(languageLocal2);
                    arrayList.add(languageBean2);
                } else if (languageBean2.getLanguageRemote() != null && languageBean2.getLanguageRemote().getLanguage().equals(languageLocal.convertName())) {
                    languageBean2.setState(4);
                    languageBean2.setSource(0);
                    languageLocal.setPath("/navi/BYDAssistant_CR/data/" + languageBean2.getLanguageRemote().getLanguage());
                    languageLocal.setVersion(languageBean2.getLanguageRemote().getLatestPkgVersion().replace("V.", BuildConfig.FLAVOR));
                    languageLocal.setUsed(1);
                    languageLocal.setState(-1);
                    languageLocal.setType(-1);
                    languageBean2.setLanguageLocal(languageLocal);
                    arrayList.add(1, languageBean2);
                } else {
                    arrayList.add(languageBean2);
                }
            }
        }
        return arrayList;
    }

    public static List<LanguageBean> updateSwitchLanguageData(List<LanguageBean> list, LanguageLocal languageLocal) {
        ArrayList arrayList = new ArrayList();
        for (LanguageBean languageBean : list) {
            if (languageBean.getLanguageLocal() != null && languageBean.getLanguageLocal().getUsed() == 1) {
                LanguageLocal languageLocal2 = languageBean.getLanguageLocal();
                languageLocal2.setUsed(0);
                languageBean.setLanguageLocal(languageLocal2);
                arrayList.add(languageBean);
            } else {
                LanguageLocal languageLocal3 = languageBean.getLanguageLocal();
                if (languageLocal3 != null && languageLocal3.getName().equals(languageLocal.getName())) {
                    if ("data_eng-USA".equals(languageLocal.getName())) {
                        languageLocal3.setUsed(1);
                        languageBean.setLanguageLocal(languageLocal3);
                        arrayList.add(0, languageBean);
                    } else {
                        languageLocal3.setUsed(1);
                        languageBean.setLanguageLocal(languageLocal3);
                        arrayList.add(1, languageBean);
                    }
                } else {
                    arrayList.add(languageBean);
                }
            }
        }
        return arrayList;
    }

    private static int findUsedLanguage(List<LanguageBean> list) {
        int i = 0;
        for (LanguageBean languageBean : list) {
            if (languageBean.getLanguageLocal() != null && languageBean.getLanguageLocal().getUsed() == 1) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static List<LanguageBean> getListFromIterator(Iterator<LanguageBean> it) {
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LanguageBean findTask(String str, List<LanguageBean> list) {
        for (LanguageBean languageBean : list) {
            if (str.equals(languageBean.getLanguageRemote().getLanguage())) {
                return languageBean;
            }
        }
        return null;
    }

    private static boolean isNewRemoteData(String str) {
        List<LanguageBean> transformLocalData = getTransformLocalData();
        for (int i = 0; i < transformLocalData.size(); i++) {
            if (str.equals(transformLocalData.get(i).getLanguageLocal().convertName())) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isNewRemoteDataExcludeInstall(String str) {
        List<LanguageBean> transformLocalDataExcludeSort = getTransformLocalDataExcludeSort();
        for (int i = 0; i < transformLocalDataExcludeSort.size(); i++) {
            if (str.equals(transformLocalDataExcludeSort.get(i).getLanguageLocal().convertName())) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LanguageBean searchRemoteDataByName(String str, List<LanguageUpdate> list) {
        List<LanguageBean> transformRemoteData = getTransformRemoteData(list);
        for (int i = 0; i < transformRemoteData.size(); i++) {
            if (str.equals(transformRemoteData.get(i).getLanguageRemote().getLanguage())) {
                transformRemoteData.get(i).setSource(2);
                transformRemoteData.get(i).setState(0);
                return transformRemoteData.get(i);
            }
        }
        return null;
    }

    public static List<LanguageBean> getTransformLocalData() {
        ArrayList arrayList = new ArrayList();
        for (LanguageLocal languageLocal : originalLocalData(false)) {
            LanguageBean languageBean = new LanguageBean();
            languageBean.setLanguageLocal(languageLocal);
            languageBean.setState(4);
            arrayList.add(languageBean);
        }
        return arrayList;
    }

    public static List<LanguageBean> getTransformLocalDataExcludeSort() {
        String countryCode = CountryCodeUtil.getInstance().getCountryCode(BydVoiceSettingApplication.getContext());
        RLog.i("wens", "countryCode:" + countryCode);
        String systemDefaultLanguage = LanguageUtil.getSystemDefaultLanguage(countryCode);
        RLog.i("wens", "defaultLanguage:" + systemDefaultLanguage);
        ArrayList arrayList = new ArrayList();
        LanguageBean languageBean = null;
        LanguageBean languageBean2 = null;
        LanguageBean languageBean3 = null;
        for (LanguageLocal languageLocal : originalLocalData(false)) {
            LanguageBean languageBean4 = new LanguageBean();
            languageBean4.setLanguageLocal(languageLocal);
            languageBean4.setState(4);
            if (languageLocal.getName().contains(LanguageName.ENG)) {
                languageBean = languageBean4;
            }
            if (languageLocal.getUsed() == 1) {
                languageBean2 = languageBean4;
            }
            if (languageLocal.getName().equals(systemDefaultLanguage)) {
                languageBean3 = languageBean4;
            }
            arrayList.add(languageBean4);
            sortLocalLanguageList(languageBean, languageBean2, languageBean3, arrayList);
        }
        return arrayList;
    }

    private static void sortLocalLanguageList(LanguageBean languageBean, LanguageBean languageBean2, LanguageBean languageBean3, List<LanguageBean> list) {
        RLog.i("wens", "sortLocalLanguageList");
        if (languageBean == null) {
            if (languageBean2 == null) {
                if (languageBean3 != null) {
                    list.remove(languageBean3);
                    list.add(0, languageBean3);
                    RLog.d("wens", "1如果没有英语，没有使用中的语言，只有当地语言，则当地语言第一位");
                    return;
                }
                RLog.d("wens", "2如果没有英语，没有使用中的语言，没有当地语言，无所谓顺序");
                return;
            }
            if (languageBean3 == null) {
                list.remove(languageBean2);
                list.add(0, languageBean2);
                RLog.d("wens", "5如果没有英语，有使用中的语言，没有当地语言，则使用中语言第一位");
                return;
            } else if (languageBean2 == languageBean3) {
                list.remove(languageBean2);
                list.add(0, languageBean2);
                RLog.d("wens", "3如果没有英语，有使用中的语言，使用中的语言就是当地语言，则使用中语言（也就是当地语言）第一位");
                return;
            } else {
                list.remove(languageBean2);
                list.add(0, languageBean2);
                list.remove(languageBean3);
                list.add(1, languageBean3);
                RLog.d("wens", "4如果没有英语，有使用中的语言，使用中的语言不是当地语言，则使用中语言第一位，当地语言第二位");
                return;
            }
        }
        list.remove(languageBean);
        list.add(0, languageBean);
        if (languageBean2 == null) {
            if (languageBean3 != null) {
                list.remove(languageBean3);
                list.add(1, languageBean3);
                RLog.d("wens", "6如果有英语，没有使用中的语言，有当地语言，当地语言第二位");
                return;
            }
            RLog.d("wens", "7如果有英语，没有使用中的语言，没有有当地语言，英语第一位");
            return;
        }
        if (languageBean2 == languageBean) {
            if (languageBean3 != null) {
                list.remove(languageBean3);
                list.add(1, languageBean3);
                RLog.d("wens", "8如果有英语，使用中的语言就是英语，有当地语言，英语（使用中）第一位，当地语言第二位");
                return;
            }
            RLog.d("wens", "9如果有英语，使用中的语言就是英语，没有当地语言，英语（使用中）第一位");
            return;
        }
        if (languageBean3 == null) {
            list.remove(languageBean2);
            list.add(1, languageBean2);
            RLog.d("wens", "12如果有英语，有使用中的语言，没有当地语言，英语第一位，使用中第二位");
        } else if (languageBean2 == languageBean3) {
            list.remove(languageBean2);
            list.add(1, languageBean2);
            RLog.d("wens", "10如果有英语，使用中的语言就是当地语言，英语第一位，使用中（当地语言）第二位");
        } else {
            list.remove(languageBean2);
            list.add(1, languageBean2);
            list.remove(languageBean3);
            list.add(2, languageBean3);
            RLog.d("wens", "11如果有英语，有使用中的语言，有当地语言，使用中的不是当地语言，英语第一位，使用中第二位，当地语言第三位");
        }
    }

    public static boolean isHaveUSA() {
        String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_LANGUAG_DPS);
        return !TextUtils.isEmpty(string) && string.contains(LanguageName.ENG);
    }

    public static List<LanguageBean> getTransformRemoteData(List<LanguageUpdate> list) {
        Integer.parseInt(BydVoiceSettingApplication.getCountryCode());
        ArrayList arrayList = new ArrayList();
        for (LanguageUpdate languageUpdate : list) {
            String language = languageUpdate.getLanguage();
            if (!TextUtils.isEmpty(language)) {
                LanguageBean languageBean = new LanguageBean();
                languageBean.setLanguageRemote(languageUpdate);
                if (language.equals(LanguageName.ENG)) {
                    arrayList.add(0, languageBean);
                } else {
                    arrayList.add(languageBean);
                }
            }
        }
        return arrayList;
    }

    public static List<LanguageBean> getTaskToRemoteData(List<DownloadTask> list) {
        ArrayList arrayList = new ArrayList();
        for (DownloadTask downloadTask : list) {
            boolean zBooleanValue = ((Boolean) SharedPreferencesUtils.getParam(BydVoiceSettingApplication.getContext(), downloadTask.getLanguageUpdate().getLanguage(), false)).booleanValue();
            if (downloadTask.getStatus() != -3 || zBooleanValue) {
                LanguageBean languageBean = new LanguageBean();
                languageBean.setSource(2);
                languageBean.setTaskId(downloadTask.getId());
                languageBean.setRemotePath(downloadTask.getPath());
                languageBean.setState(getDownloadState(downloadTask));
                languageBean.setLanguageRemote(downloadTask.getLanguageUpdate());
                languageBean.setProgress(Tools.percentInt(downloadTask.getSofar(), downloadTask.getTotal()));
                arrayList.add(languageBean);
            }
        }
        return arrayList;
    }

    private static int getDownloadState(DownloadTask downloadTask) {
        byte status = downloadTask.getStatus();
        if (status == -5) {
            return 5;
        }
        if (status == 6) {
            return 1;
        }
        if (status == -2 || status == -1 || status == 0) {
            return 2;
        }
        return (status == 2 || status == 3) ? 1 : 0;
    }

    public static void setSettingValue(LanguageLocal languageLocal, int i) {
        if (languageLocal == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        languageLocal.setType(i);
        String str = TAG;
        LogUtils.i(str, "语言" + languageLocal.getName());
        if (i == 1 || i == 4 || i == 6) {
            if (languageLocal.getName().contains("eng")) {
                swithMap.put("eng", languageLocal.convertName());
                LogUtils.i("wens", "add switch list:" + languageLocal.convertName());
            } else {
                swithMap.put(languageLocal.convertName(), languageLocal.convertName());
                LogUtils.i("wens", "add switch list:" + languageLocal.convertName());
            }
            RLog.i(str, "switch pkg:" + languageLocal.convertName());
            if (i != 4 && i != 6) {
                Intent intent = new Intent(BydVoiceSettingApplication.getContext(), (Class<?>) NotifyService.class);
                intent.putExtra("NotifyParams", "switchPkg");
                intent.putExtra("convertName", languageLocal.convertName());
                BydVoiceSettingApplication.getContext().startForegroundService(intent);
            }
        }
        if (i == 2) {
            if (languageLocal.getName().contains("eng")) {
                deleteMap.put("eng", languageLocal.convertName());
                LogUtils.i("wens", "add delete list:" + languageLocal.convertName());
            } else {
                deleteMap.put(languageLocal.convertName(), languageLocal.convertName());
                LogUtils.i("wens", "add delete list:" + languageLocal.convertName());
            }
            RLog.e(str, "delete pkg:" + languageLocal.convertName());
        }
        if (i == 3 || i == 4 || i == 5 || i == 6) {
            if (isInInstallList(languageLocal.convertName())) {
                return;
            }
            if (isConversationBegin()) {
                RLog.e(str, "wait install :" + languageLocal);
                languageLocal.setType(i);
                waitInstallMap.put(languageLocal.convertName(), languageLocal);
                return;
            } else {
                RLog.e(str, "to install:" + languageLocal);
                EventBus.getDefault().post(new PrivacyPolicyEvent("KEY_LANGUAG_INSTALLING", BuildConfig.FLAVOR));
                SharedPreferencesUtils.setParam(BydVoiceSettingApplication.getContext(), languageLocal.convertName(), true);
                removeAutoEnableList(languageLocal.convertName());
            }
        }
        arrayList.add(languageLocal);
        setSettingValues(arrayList);
    }

    public static void setSettingValues(List<LanguageLocal> list) {
        synchronized (list) {
            try {
                String json = GSON.toJson(list);
                if (json.equals(Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_LANGUAGE"))) {
                    LogUtils.i("wens", "setSettingValue empty str");
                    Settings.System.putString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_LANGUAGE", BuildConfig.FLAVOR);
                }
                Settings.System.putString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_LANGUAGE", json);
                LogUtils.i("wens", "setSettingValue-->" + json);
            } catch (Exception e) {
                LogUtils.e("OtaDataManager", e.getMessage());
            }
        }
    }

    public static String getDPSCurrentLanguage() {
        List<LanguageLocal> listOriginalLocalData = originalLocalData(true);
        if (listOriginalLocalData == null || listOriginalLocalData.size() <= 0) {
            return BuildConfig.FLAVOR;
        }
        for (LanguageLocal languageLocal : listOriginalLocalData) {
            if (languageLocal.getUsed() == 1) {
                return LanguageUtil.convertAll(BydVoiceSettingApplication.getContext(), languageLocal.getName());
            }
        }
        return BuildConfig.FLAVOR;
    }

    public static GetLanguagesUpdate createParemeter() {
        GetLanguagesUpdate getLanguagesUpdate = new GetLanguagesUpdate();
        if (isTest) {
            ArrayList arrayList = new ArrayList();
            PkgVersion pkgVersion = new PkgVersion();
            pkgVersion.setLanguage(LanguageName.ENG);
            pkgVersion.setPkgVersion("V.D.2.23.0112A");
            PkgVersion pkgVersion2 = new PkgVersion();
            pkgVersion2.setLanguage("data_por-PRT");
            pkgVersion2.setPkgVersion("V.D.2.23.0302A");
            PkgVersion pkgVersion3 = new PkgVersion();
            pkgVersion3.setLanguage("data_spa-ESP");
            pkgVersion3.setPkgVersion("V.D.2.23.0302A");
            PkgVersion pkgVersion4 = new PkgVersion();
            pkgVersion4.setLanguage("data_eng-XWW");
            pkgVersion4.setPkgVersion("V.D.2.23.0302A");
            PkgVersion pkgVersion5 = new PkgVersion();
            pkgVersion5.setLanguage("data_a1-XWW");
            pkgVersion5.setPkgVersion("V.D.2.23.0302A");
            PkgVersion pkgVersion6 = new PkgVersion();
            pkgVersion6.setLanguage("data_a2-XWW");
            pkgVersion6.setPkgVersion("V.D.2.23.0302A");
            PkgVersion pkgVersion7 = new PkgVersion();
            pkgVersion7.setLanguage("data_a3-XWW");
            pkgVersion7.setPkgVersion("V.D.2.23.0302A");
            PkgVersion pkgVersion8 = new PkgVersion();
            pkgVersion8.setLanguage("data_a4-XWW");
            pkgVersion8.setPkgVersion("V.D.2.23.0302A");
            PkgVersion pkgVersion9 = new PkgVersion();
            pkgVersion9.setLanguage("data_a5-XWW");
            pkgVersion9.setPkgVersion("V.D.2.23.0302A");
            PkgVersion pkgVersion10 = new PkgVersion();
            pkgVersion10.setLanguage("data_a6-XWW");
            pkgVersion10.setPkgVersion("V.D.2.23.0302A");
            arrayList.add(pkgVersion);
            arrayList.add(pkgVersion2);
            arrayList.add(pkgVersion3);
            arrayList.add(pkgVersion4);
            arrayList.add(pkgVersion5);
            arrayList.add(pkgVersion6);
            arrayList.add(pkgVersion7);
            arrayList.add(pkgVersion8);
            arrayList.add(pkgVersion2);
            getLanguagesUpdate.setApkVersion(com.byd.vrsettings.BuildConfig.VERSION_NAME);
            getLanguagesUpdate.setxPlatform("release");
            getLanguagesUpdate.setVin("20220120001");
            getLanguagesUpdate.setPkgVersion(arrayList);
            return getLanguagesUpdate;
        }
        ArrayList arrayList2 = new ArrayList();
        for (LanguageLocal languageLocal : originalLocalData(true)) {
            PkgVersion pkgVersion11 = new PkgVersion();
            pkgVersion11.setPkgVersion("V." + languageLocal.getVersion().replaceFirst("V.", BuildConfig.FLAVOR));
            pkgVersion11.setLanguage(LanguageUtil.convertLocalName(languageLocal.getName()));
            pkgVersion11.setUpgradeDate(new SimpleDateFormat("yyyy/MM/dd", Locale.CHINESE).format(new Date(new File(languageLocal.getPath()).lastModified())));
            arrayList2.add(pkgVersion11);
        }
        getLanguagesUpdate.setApkVersion(com.byd.vrsettings.BuildConfig.VERSION_NAME);
        getLanguagesUpdate.setxPlatform("release");
        getLanguagesUpdate.setVin(getVIN());
        getLanguagesUpdate.setPkgVersion(arrayList2);
        getLanguagesUpdate.setVehicleType(getVehicleType());
        return getLanguagesUpdate;
    }

    public static boolean isTodayNotTip(Context context) {
        return isNow(((Long) SharedPreferencesUtils.getParam(context, SharedPreferencesUtils.SP_KEY_TODAY_NOT_TIP, 0L)).longValue());
    }

    public static void setTodayNotTip(Context context) {
        SharedPreferencesUtils.setParam(context, SharedPreferencesUtils.SP_KEY_TODAY_NOT_TIP, Long.valueOf(System.currentTimeMillis()));
    }

    public static boolean isNow(long j) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTEN_DEFAULT_YMD);
        return simpleDateFormat.format(new Date(j)).equals(simpleDateFormat.format(date));
    }

    public static void addCurrentSwitchLan(LanguageLocal languageLocal) {
        currentSwitchLan.add(languageLocal);
    }

    public static List<LanguageLocal> getCurrentSwitchLan() {
        return currentSwitchLan;
    }

    public static int getAutoEnableState(String str) {
        String str2 = (String) SharedPreferencesUtils.getParam(BydVoiceSettingApplication.getContext(), SharedPreferencesUtils.SP_KEY_AUTO_ENABLE_TIP, BuildConfig.FLAVOR);
        if (TextUtils.isEmpty(str2)) {
            return -1;
        }
        for (AutoEnableData autoEnableData : (List) new Gson().fromJson(str2, new TypeToken<List<AutoEnableData>>() { // from class: com.byd.vrsettings.service.OtaDataManager.5
        }.getType())) {
            LogUtils.i("wens", "getAutoEnableState ..getLanguageName:" + autoEnableData.getLanguageName() + ",state:" + autoEnableData.getState());
            if (str.equals(autoEnableData.getLanguageName())) {
                return autoEnableData.getState();
            }
        }
        return -1;
    }

    public static void addAutoEnableList(String str, int i) {
        List arrayList;
        AutoEnableData autoEnableData = new AutoEnableData();
        autoEnableData.setLanguageName(str);
        autoEnableData.setState(i);
        String str2 = (String) SharedPreferencesUtils.getParam(BydVoiceSettingApplication.getContext(), SharedPreferencesUtils.SP_KEY_AUTO_ENABLE_TIP, BuildConfig.FLAVOR);
        if (TextUtils.isEmpty(str2)) {
            arrayList = new ArrayList();
            arrayList.add(autoEnableData);
            LogUtils.i("wens", "enableStr == null");
        } else {
            List list = (List) new Gson().fromJson(str2, new TypeToken<List<AutoEnableData>>() { // from class: com.byd.vrsettings.service.OtaDataManager.6
            }.getType());
            if (list.size() == 0) {
                LogUtils.i("wens", "enableStr == null");
            } else {
                Iterator it = list.iterator();
                AutoEnableData autoEnableData2 = null;
                AutoEnableData autoEnableData3 = null;
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AutoEnableData autoEnableData4 = (AutoEnableData) it.next();
                    if (autoEnableData4.getLanguageName().equals(str)) {
                        autoEnableData3 = null;
                        autoEnableData2 = autoEnableData4;
                    }
                    if (autoEnableData2 != null) {
                        list.remove(autoEnableData2);
                        list.add(autoEnableData);
                        break;
                    } else {
                        LogUtils.i("wens", "temp == " + autoEnableData);
                        autoEnableData3 = autoEnableData;
                    }
                }
                autoEnableData = autoEnableData3;
            }
            if (autoEnableData != null) {
                list.add(autoEnableData);
            }
            arrayList = list;
        }
        if (arrayList != null) {
            LogUtils.i("wens", "enableList save:" + new Gson().toJson(arrayList));
            SharedPreferencesUtils.setParam(BydVoiceSettingApplication.getContext(), SharedPreferencesUtils.SP_KEY_AUTO_ENABLE_TIP, new Gson().toJson(arrayList));
        }
    }

    public static void removeAutoEnableList(LanguageUpdate languageUpdate) {
        if (languageUpdate == null) {
            return;
        }
        removeAutoEnableList(languageUpdate.getLanguage());
    }

    public static void removeAutoEnableList(String str) {
        AutoEnableData autoEnableData;
        String str2 = (String) SharedPreferencesUtils.getParam(BydVoiceSettingApplication.getContext(), SharedPreferencesUtils.SP_KEY_AUTO_ENABLE_TIP, BuildConfig.FLAVOR);
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        List list = (List) new Gson().fromJson(str2, new TypeToken<List<AutoEnableData>>() { // from class: com.byd.vrsettings.service.OtaDataManager.7
        }.getType());
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                autoEnableData = null;
                break;
            } else {
                autoEnableData = (AutoEnableData) it.next();
                if (autoEnableData.getLanguageName().equals(str)) {
                    break;
                }
            }
        }
        if (autoEnableData != null) {
            list.remove(autoEnableData);
        }
        SharedPreferencesUtils.setParam(BydVoiceSettingApplication.getContext(), SharedPreferencesUtils.SP_KEY_AUTO_ENABLE_TIP, new Gson().toJson(list));
    }

    public static void clearAutoEnableList() {
        SharedPreferencesUtils.setParam(BydVoiceSettingApplication.getContext(), SharedPreferencesUtils.SP_KEY_AUTO_ENABLE_TIP, "[]");
    }

    public static void dealLanguageDPSResult(String str) {
        LogUtils.i("wens", "onChangeLanguageEvent...KEY_LANGUAG_DPS:" + str);
        BydVoiceSettingApplication context = BydVoiceSettingApplication.getContext();
        List<LanguageLocal> list = (List) new Gson().fromJson(str, new TypeToken<List<LanguageLocal>>() { // from class: com.byd.vrsettings.service.OtaDataManager.8
        }.getType());
        if (list.size() > 0) {
            for (LanguageLocal languageLocal : list) {
                int type = languageLocal.getType();
                if (type == 2) {
                    if (isInDeleteList(languageLocal.convertName())) {
                        if (languageLocal.getState() == 0) {
                            LogUtils.i("wens", "删除成功：" + languageLocal.getName());
                            sendInitBroadcast();
                            removeAutoEnableList(languageLocal.convertName());
                        } else {
                            LogUtils.i("wens", "删除失败：" + languageLocal.getName());
                            showToast("delete fail");
                        }
                        deleteMap.remove(getLanguageKey(languageLocal.convertName()));
                        EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.EVENT_BUS_LANGUAG_DPS, "delete"));
                        return;
                    }
                } else if (type == 3 || type == 4 || type == 5 || type == 6) {
                    if (languageLocal.getState() == 0) {
                        LogUtils.i("wens", "singlePkgInstallComplete:" + (languageLocal != null ? languageLocal.convertName() : "null"));
                        singlePkgInstallComplete(languageLocal);
                        context.startForegroundService(new Intent(context, (Class<?>) NotifyService.class));
                        AidlServiceManager.getInstance().cancel(languageLocal.convertName());
                        removeAutoEnableList(languageLocal.convertName());
                    } else {
                        singlePkgInstallFail(languageLocal);
                        showToast(R.string.text_install_fail);
                        LogUtils.i("wens", "安装失败：" + languageLocal.getName());
                    }
                    EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.EVENT_BUS_LANGUAG_DPS, "refresh"));
                }
            }
        }
        EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.EVENT_BUS_LANGUAG_DPS, "refresh"));
    }

    public static void dealSwitchResult(String str) {
        if (swithMap.size() == 0) {
            LogUtils.i("wens", "当前切换的语音包不在当前切换队列中，不处理结果");
            return;
        }
        for (final LanguageLocal languageLocal : (List) new Gson().fromJson(str, new TypeToken<List<LanguageLocal>>() { // from class: com.byd.vrsettings.service.OtaDataManager.9
        }.getType())) {
            if (languageLocal.getState() == 3) {
                LogUtils.i("wens", "切换失败");
                switchFailed(languageLocal.convertName());
            } else if (languageLocal.getState() == 2) {
                LogUtils.i("wens", "切换成功");
                new Handler().postDelayed(new Runnable() { // from class: com.byd.vrsettings.service.OtaDataManager.10
                    @Override // java.lang.Runnable
                    public void run() {
                        OtaDataManager.switchSucc(languageLocal.convertName());
                    }
                }, 1000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void switchSucc(String str) {
        TimeOutRunnable timeOutRunnable;
        Handler handler = mHandler;
        if (handler != null && (timeOutRunnable = mRunnable) != null) {
            handler.removeCallbacks(timeOutRunnable);
            LogUtils.e("wens", "switchSucc removeCallbacks :" + str);
        }
        isSwitching = false;
        EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.EVENT_BUS_LANGUAG_DPS, "switch"));
        BydVoiceSettingApplication context = BydVoiceSettingApplication.getContext();
        if (isInSwitchList(str)) {
            swithMap.remove(getLanguageKey(str));
            showToast(String.format(context.getString(R.string.text_di_switch_to), LanguageUtil.convertAll(context, str)));
            sendUpdateAppLanguage(500L);
        }
    }

    public static void switchFailed(String str) {
        TimeOutRunnable timeOutRunnable;
        Handler handler = mHandler;
        if (handler != null && (timeOutRunnable = mRunnable) != null) {
            handler.removeCallbacks(timeOutRunnable);
            LogUtils.e("wens", "switchFailed removeCallbacks :" + str);
        }
        EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.EVENT_BUS_LANGUAG_DPS, "switch"));
        if (isInSwitchList(str)) {
            isSwitching = false;
            swithMap.remove(getLanguageKey(str));
            showToast(R.string.text_switch_fail);
            sendUpdateAppLanguage(500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isInSwitchList(String str) {
        return swithMap.containsKey(getLanguageKey(str));
    }

    private static boolean isInDeleteList(String str) {
        return deleteMap.containsKey(getLanguageKey(str));
    }

    private static boolean isInInstallList(String str) {
        return waitInstallMap.containsKey(getLanguageKey(str));
    }

    public static boolean isConversationBegin() {
        String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_VOICE_DIALOGEVENT");
        RLog.e("wens", "KEY_VOICE_DIALOGEVENT:" + string);
        if (Constant.CONVERSATION_BEGIN.equals(string)) {
            RLog.e("wens", "ConversationBegin");
            return true;
        }
        RLog.e("wens", "ConversationEnd");
        return false;
    }

    public static void checkInstallMap() {
        RLog.e("wens", "checkInstallMap start");
        if (waitInstallMap.size() > 0) {
            final ArrayList arrayList = new ArrayList();
            waitInstallMap.forEach(new BiConsumer() { // from class: com.byd.vrsettings.service.OtaDataManager$$ExternalSyntheticLambda0
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    OtaDataManager.lambda$checkInstallMap$0(arrayList, (String) obj, (LanguageLocal) obj2);
                }
            });
            EventBus.getDefault().post(new PrivacyPolicyEvent("KEY_LANGUAG_INSTALLING", BuildConfig.FLAVOR));
            EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.EVENT_BUS_LANGUAG_DPS, BuildConfig.FLAVOR));
            RLog.e("wens", "checkInstallMap:" + new Gson().toJson(waitInstallMap));
            setSettingValues(arrayList);
            waitInstallMap.clear();
        }
    }

    static /* synthetic */ void lambda$checkInstallMap$0(List list, String str, LanguageLocal languageLocal) {
        list.add(languageLocal);
        SharedPreferencesUtils.setParam(BydVoiceSettingApplication.getContext(), languageLocal.convertName(), true);
        removeAutoEnableList(languageLocal.convertName());
    }

    private static String getLanguageKey(String str) {
        return str.contains("eng") ? "eng" : str;
    }

    public static boolean isAssistantLive(Context context) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(100)) {
            if (runningServiceInfo.service != null && "com.byd.vrassistant".equals(runningServiceInfo.process) && runningServiceInfo.service.getClassName().contains("NeuVoiceService")) {
                LogUtils.e(TAG, "info:" + runningServiceInfo.service);
                return true;
            }
        }
        return false;
    }

    public static void showToast(String str) {
        Toast toast = mToast;
        if (toast != null) {
            toast.cancel();
        }
        Toast toastMakeText = Toast.makeText(BydVoiceSettingApplication.getContext(), str, 0);
        mToast = toastMakeText;
        toastMakeText.show();
    }

    public static void showToast(int i) {
        showToast(BydVoiceSettingApplication.getContext().getString(i));
    }

    public static String getVehicleType() {
        return Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_VEHICLE_TYPE");
    }

    public static String getVIN() {
        return Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_DEVICE_ID");
    }

    public static boolean isOtaShowGuide() {
        return !isHaveVoicePkg();
    }

    public static boolean isHaveVoicePkg() {
        List<LanguageLocal> listOriginalLocalData = originalLocalData(false);
        if (listOriginalLocalData != null && listOriginalLocalData.size() > 0) {
            Iterator<LanguageLocal> it = listOriginalLocalData.iterator();
            while (it.hasNext()) {
                if (it.next().getUsed() == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static LanguageLocal getUsingVoicePkg() {
        List<LanguageLocal> listOriginalLocalData = originalLocalData(false);
        if (listOriginalLocalData == null || listOriginalLocalData.size() <= 0) {
            return null;
        }
        for (LanguageLocal languageLocal : listOriginalLocalData) {
            if (languageLocal.getUsed() == 1) {
                return languageLocal;
            }
        }
        return null;
    }

    public static String getCurrentLanguageName() {
        String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "SP_KEY_CUR_LANGUAGE");
        if (TextUtils.isEmpty(string) || !string.contains("/")) {
            return string;
        }
        return string.split("/")[r0.length - 1];
    }

    public static String getCurrentLanguagePath() {
        return Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "SP_KEY_CUR_LANGUAGE");
    }

    public static String getCurrentLanguageVersion(String str) {
        List<LanguageLocal> list;
        String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_LANGUAG_DPS);
        if (TextUtils.isEmpty(string) || (list = (List) new Gson().fromJson(string, new TypeToken<List<LanguageLocal>>() { // from class: com.byd.vrsettings.service.OtaDataManager.11
        }.getType())) == null) {
            return null;
        }
        for (LanguageLocal languageLocal : list) {
            if (languageLocal.getName().equals(str)) {
                return languageLocal.getVersion();
            }
        }
        return null;
    }

    static class TimeOutRunnable implements Runnable {
        String convertName;

        TimeOutRunnable() {
        }

        public TimeOutRunnable setConvertName(String str) {
            this.convertName = str;
            return this;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (OtaDataManager.isInSwitchList(this.convertName)) {
                LogUtils.e("wens", "switchTimeOut:" + this.convertName);
                OtaDataManager.switchFailed(this.convertName);
            }
        }
    }

    public static void switchTimeOut(String str) {
        isSwitching = true;
        if (TextUtils.isEmpty(str) || !isInSwitchList(str)) {
            return;
        }
        LogUtils.e("wens", "switchTimeOut 1111 :" + str);
        mHandler.postDelayed(mRunnable.setConvertName(str), 30000L);
    }

    public static boolean isSwitching() {
        return isSwitching;
    }

    private static void sendInitBroadcast() {
        Intent intent = new Intent();
        intent.setAction("com.byd.voice.action.initlang");
        intent.setComponent(new ComponentName("com.byd.vrassistant", "com.byd.vrassistant.receiver.AutoRunReceiver"));
        intent.addFlags(32);
        BydVoiceSettingApplication.getContext().sendBroadcast(intent, "com.byd.vrassistant.autorun.permission");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dealLocalSize(LanguageBean languageBean) {
        if (languageBean.getLanguageLocal() == null || TextUtils.isEmpty(languageBean.getLanguageLocal().getPath())) {
            return;
        }
        languageBean.setLocalPkgSize(FileUtil.getFileSize(languageBean.getLanguageLocal().getPath()));
    }

    public static String get(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e) {
            Log.e(TAG, "read SystemProperties error", e);
            return null;
        }
    }

    private static boolean isHaveSystemLanguageDp(String str) {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String systemLanguage = LanguageUtil.getSystemLanguage();
        String str4 = null;
        if (systemLanguage.equals("en")) {
            str3 = "data_eng-USA";
        } else if (systemLanguage.equals("da")) {
            str3 = "data_dan-DNK";
        } else {
            if (!systemLanguage.equals("de")) {
                if (systemLanguage.equals("es")) {
                    str4 = "data_spa-ESP";
                    str2 = "data_spa-XLA";
                } else if (systemLanguage.equals("fi")) {
                    str3 = "data_fin-FIN";
                } else if (systemLanguage.equals("fr")) {
                    str3 = "data_fra-FRA";
                } else if (systemLanguage.equals("it")) {
                    str3 = "data_ita-ITA";
                } else if (systemLanguage.equals("iw")) {
                    str3 = "data_heb-ISR";
                } else if (systemLanguage.equals("ja")) {
                    str3 = "data_jpn-JPN";
                } else if (systemLanguage.equals("ko")) {
                    str3 = "data_kor-KOR";
                } else if (systemLanguage.equals("nb")) {
                    str3 = "data_nor-NOR";
                } else if (systemLanguage.equals("nl")) {
                    str3 = "data_nld-NLD";
                } else if (systemLanguage.equals("pl")) {
                    str3 = "data_pol-POL";
                } else if (systemLanguage.equals("pt")) {
                    str4 = "data_por-PRT";
                    str2 = "data_por-BRA";
                } else if (systemLanguage.equals("sv")) {
                    str3 = "data_swe-SWE";
                } else if (systemLanguage.equals("th")) {
                    str3 = "data_tha-THA";
                } else if (systemLanguage.equals("tr")) {
                    str3 = "data_tur-TUR";
                } else if (systemLanguage.equals("zh")) {
                    str4 = "data_cmn-CHN";
                    str2 = "data_yue-HKG";
                } else {
                    str2 = null;
                }
                if (str4 == null && str.contains(str4)) {
                    return true;
                }
                return str2 == null && str.contains(str2);
            }
            str3 = "data_deu-DEU";
        }
        str4 = str3;
        str2 = null;
        if (str4 == null) {
        }
        if (str2 == null) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void updateViewLanguage(View view) {
        if (view == 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                updateViewLanguage(viewGroup.getChildAt(i));
            }
            return;
        }
        if (view instanceof LanguageView) {
            ((LanguageView) view).reLoadLanguage();
        }
    }

    public static Context switchLanguage(Context context, Locale locale) {
        Locale.setDefault(locale);
        Configuration configuration = context.getResources().getConfiguration();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration, displayMetrics);
        BydVoiceSettingApplication.getContext().getResources().updateConfiguration(configuration, displayMetrics);
        return context.createConfigurationContext(configuration);
    }

    public static Context switchAppLanguage(Context context) {
        BydVoiceSettingApplication.getContext().getResources();
        Locale locale = Resources.getSystem().getConfiguration().getLocales().get(0);
        if (locale == null) {
            return BydVoiceSettingApplication.getContext();
        }
        String string = locale.toString();
        Locale language = getLanguage(string);
        RLog.e("wens", "isInSupport(system_locales):" + string + ",systemLanguage19:" + language);
        if (language == null) {
            String currentLanguageName = getCurrentLanguageName();
            RLog.e("wens", "currentVr:" + currentLanguageName);
            Locale vRLanguage = LanguageUtil.getVRLanguage(currentLanguageName);
            if (!TextUtils.isEmpty(currentLanguageName) && vRLanguage != null) {
                RLog.e("wens", "vrLocale:" + vRLanguage);
                return switchLanguage(context, vRLanguage);
            }
            RLog.e("wens", "isInSupportLanguage(system_locales):Locale.ENGLISH");
            return switchLanguage(context, Locale.ENGLISH);
        }
        RLog.e("wens", "原不是系统语言，现更改为系统语言:" + getLanguage(string));
        if (language == null) {
            language = new Locale("en");
        }
        return switchLanguage(context, language);
    }

    private static Locale getLanguage(String str) {
        if (str.contains("en")) {
            return new Locale("en");
        }
        if (str.contains("de")) {
            return new Locale("de");
        }
        if (str.contains("es_LA")) {
            return new Locale("es", "mx");
        }
        if (str.contains("es")) {
            return new Locale("es", "es");
        }
        if (str.contains("fr")) {
            return new Locale("fr");
        }
        if (str.contains("it")) {
            return new Locale("it");
        }
        if (str.contains("ja")) {
            return new Locale("ja");
        }
        if (str.contains("ko")) {
            return new Locale("ko");
        }
        if (str.contains("nb")) {
            return new Locale("nb");
        }
        if (str.contains("pt_PT")) {
            return new Locale("pt", "pt");
        }
        if (str.contains("pt_BR")) {
            return new Locale("pt", "br");
        }
        if (str.contains("th")) {
            return new Locale("th");
        }
        if (str.contains("tr")) {
            return new Locale("tr");
        }
        if (str.contains("zh_TW") || str.contains("zh_HK")) {
            return new Locale("zh", "hk");
        }
        if (str.contains("zh_CN") || str.contains("zh")) {
            return new Locale("zh", "cn");
        }
        if (str.contains("nl")) {
            return new Locale("nl");
        }
        if (str.contains("sv")) {
            return new Locale("sv");
        }
        if (str.contains("iw")) {
            return new Locale("iw");
        }
        if (str.contains("da")) {
            return new Locale("da");
        }
        return null;
    }

    public static void sendUpdateAppLanguage(long j) {
        new Handler().postDelayed(new Runnable() { // from class: com.byd.vrsettings.service.OtaDataManager.12
            @Override // java.lang.Runnable
            public void run() {
                EventBus.getDefault().post(new SwitchEvent());
            }
        }, j);
    }

    public static Context attachBaseContext(Context context) {
        return switchAppLanguage(context);
    }
}
