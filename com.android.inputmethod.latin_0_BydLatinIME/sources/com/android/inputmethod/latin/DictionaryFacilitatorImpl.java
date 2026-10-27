package com.android.inputmethod.latin;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.latin.DictionaryFacilitator;
import com.android.inputmethod.latin.NgramContext;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.ComposedData;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.permissions.PermissionsUtil;
import com.android.inputmethod.latin.personalization.UserHistoryDictionary;
import com.android.inputmethod.latin.settings.SettingsValuesForSuggestion;
import com.android.inputmethod.latin.utils.ExecutorUtils;
import com.android.inputmethod.latin.utils.SuggestionResults;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DictionaryFacilitatorImpl implements DictionaryFacilitator {
    private static final int CAPITALIZED_FORM_MAX_PROBABILITY_FOR_INSERT = 140;
    private static final Class<?>[] DICT_FACTORY_METHOD_ARG_TYPES;
    private static final String DICT_FACTORY_METHOD_NAME = "getDictionary";
    public static final Map<String, Class<? extends ExpandableBinaryDictionary>> DICT_TYPE_TO_CLASS;
    public static final String TAG = "DictionaryFacilitatorImpl";
    private DictionaryGroup mDictionaryGroup = new DictionaryGroup();
    private volatile CountDownLatch mLatchForWaitingLoadingMainDictionaries = new CountDownLatch(0);
    private final Object mLock = new Object();
    private LruCache<String, Boolean> mValidSpellingWordReadCache;
    private LruCache<String, Boolean> mValidSpellingWordWriteCache;

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public String dump(final Context context) {
        return "";
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public String getAccount() {
        return null;
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public void onFinishInput(Context context) {
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public void onStartInput() {
    }

    static {
        HashMap map = new HashMap();
        DICT_TYPE_TO_CLASS = map;
        map.put(Dictionary.TYPE_USER_HISTORY, UserHistoryDictionary.class);
        map.put(Dictionary.TYPE_USER, UserBinaryDictionary.class);
        map.put(Dictionary.TYPE_CONTACTS, ContactsBinaryDictionary.class);
        DICT_FACTORY_METHOD_ARG_TYPES = new Class[]{Context.class, Locale.class, File.class, String.class, String.class};
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public void setValidSpellingWordReadCache(final LruCache<String, Boolean> cache) {
        this.mValidSpellingWordReadCache = cache;
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public void setValidSpellingWordWriteCache(final LruCache<String, Boolean> cache) {
        this.mValidSpellingWordWriteCache = cache;
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public boolean isForLocale(final Locale locale) {
        return locale != null && locale.equals(this.mDictionaryGroup.mLocale);
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public boolean isForAccount(@Nullable final String account) {
        return TextUtils.equals(this.mDictionaryGroup.mAccount, account);
    }

    private static class DictionaryGroup {
        public static final float WEIGHT_FOR_GESTURING_IN_NOT_MOST_PROBABLE_LANGUAGE = 0.95f;
        public static final float WEIGHT_FOR_MOST_PROBABLE_LANGUAGE = 1.0f;
        public static final float WEIGHT_FOR_TYPING_IN_NOT_MOST_PROBABLE_LANGUAGE = 0.6f;

        @Nullable
        public final String mAccount;
        private int mConfidence;

        @Nullable
        public final Locale mLocale;

        @Nullable
        private Dictionary mMainDict;
        public final ConcurrentHashMap<String, ExpandableBinaryDictionary> mSubDictMap;
        public float mWeightForGesturingInLocale;
        public float mWeightForTypingInLocale;

        public DictionaryGroup() {
            this(null, null, null, Collections.emptyMap());
        }

        public DictionaryGroup(@Nullable final Locale locale, @Nullable final Dictionary mainDict, @Nullable final String account, final Map<String, ExpandableBinaryDictionary> subDicts) {
            this.mConfidence = 0;
            this.mWeightForTypingInLocale = 1.0f;
            this.mWeightForGesturingInLocale = 1.0f;
            this.mSubDictMap = new ConcurrentHashMap<>();
            this.mLocale = locale;
            this.mAccount = account;
            setMainDict(mainDict);
            for (Map.Entry<String, ExpandableBinaryDictionary> entry : subDicts.entrySet()) {
                setSubDict(entry.getKey(), entry.getValue());
            }
        }

        private void setSubDict(final String dictType, final ExpandableBinaryDictionary dict) {
            if (dict != null) {
                this.mSubDictMap.put(dictType, dict);
            }
        }

        public void setMainDict(final Dictionary mainDict) {
            Dictionary dictionary = this.mMainDict;
            this.mMainDict = mainDict;
            if (dictionary == null || mainDict == dictionary) {
                return;
            }
            dictionary.close();
        }

        public Dictionary getDict(final String dictType) {
            if ("main".equals(dictType)) {
                return this.mMainDict;
            }
            return getSubDict(dictType);
        }

        public ExpandableBinaryDictionary getSubDict(final String dictType) {
            return this.mSubDictMap.get(dictType);
        }

        public boolean hasDict(final String dictType, @Nullable final String account) {
            if ("main".equals(dictType)) {
                return this.mMainDict != null;
            }
            if (!Dictionary.TYPE_USER_HISTORY.equals(dictType) || TextUtils.equals(account, this.mAccount)) {
                return this.mSubDictMap.containsKey(dictType);
            }
            return false;
        }

        public void closeDict(final String dictType) {
            ExpandableBinaryDictionary expandableBinaryDictionaryRemove;
            if ("main".equals(dictType)) {
                expandableBinaryDictionaryRemove = this.mMainDict;
            } else {
                expandableBinaryDictionaryRemove = this.mSubDictMap.remove(dictType);
            }
            if (expandableBinaryDictionaryRemove != null) {
                expandableBinaryDictionaryRemove.close();
            }
        }
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public boolean isActive() {
        return this.mDictionaryGroup.mLocale != null;
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public Locale getLocale() {
        return this.mDictionaryGroup.mLocale;
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public boolean usesContacts() {
        return this.mDictionaryGroup.getSubDict(Dictionary.TYPE_CONTACTS) != null;
    }

    @Nullable
    private static ExpandableBinaryDictionary getSubDict(final String dictType, final Context context, final Locale locale, final File dictFile, final String dictNamePrefix, @Nullable final String account) {
        Class<? extends ExpandableBinaryDictionary> cls = DICT_TYPE_TO_CLASS.get(dictType);
        if (cls == null) {
            return null;
        }
        try {
            return (ExpandableBinaryDictionary) cls.getMethod(DICT_FACTORY_METHOD_NAME, DICT_FACTORY_METHOD_ARG_TYPES).invoke(null, context, locale, dictFile, dictNamePrefix, account);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            Log.e(TAG, "Cannot create dictionary: " + dictType, e);
            return null;
        }
    }

    @Nullable
    static DictionaryGroup findDictionaryGroupWithLocale(final DictionaryGroup dictionaryGroup, final Locale locale) {
        if (locale.equals(dictionaryGroup.mLocale)) {
            return dictionaryGroup;
        }
        return null;
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public void resetDictionaries(final Context context, final Locale newLocale, final boolean useContactsDict, final boolean usePersonalizedDicts, final boolean forceReloadMainDictionary, @Nullable final String account, final String dictNamePrefix, @Nullable final DictionaryFacilitator.DictionaryInitializationListener listener) {
        Dictionary dict;
        DictionaryGroup dictionaryGroup;
        DictionaryGroup dictionaryGroup2;
        HashMap map;
        ExpandableBinaryDictionary subDict;
        String str;
        HashMap map2 = new HashMap();
        HashSet<String> hashSet = new HashSet();
        hashSet.add(Dictionary.TYPE_USER);
        boolean zCheckAllPermissionsGranted = PermissionsUtil.checkAllPermissionsGranted(context, "android.permission.READ_CONTACTS");
        if (useContactsDict && zCheckAllPermissionsGranted) {
            hashSet.add(Dictionary.TYPE_CONTACTS);
        }
        if (usePersonalizedDicts) {
            hashSet.add(Dictionary.TYPE_USER_HISTORY);
        }
        ArrayList arrayList = new ArrayList();
        map2.put(newLocale, arrayList);
        DictionaryGroup dictionaryGroupFindDictionaryGroupWithLocale = findDictionaryGroupWithLocale(this.mDictionaryGroup, newLocale);
        if (dictionaryGroupFindDictionaryGroupWithLocale != null) {
            for (String str2 : DYNAMIC_DICTIONARY_TYPES) {
                if (dictionaryGroupFindDictionaryGroupWithLocale.hasDict(str2, account)) {
                    arrayList.add(str2);
                }
            }
            if (dictionaryGroupFindDictionaryGroupWithLocale.hasDict("main", account)) {
                arrayList.add("main");
            }
        }
        DictionaryGroup dictionaryGroupFindDictionaryGroupWithLocale2 = findDictionaryGroupWithLocale(this.mDictionaryGroup, newLocale);
        ArrayList arrayList2 = (ArrayList) map2.get(newLocale);
        boolean z = dictionaryGroupFindDictionaryGroupWithLocale2 == null;
        if (forceReloadMainDictionary || z || !dictionaryGroupFindDictionaryGroupWithLocale2.hasDict("main", account)) {
            dict = null;
        } else {
            dict = dictionaryGroupFindDictionaryGroupWithLocale2.getDict("main");
            arrayList2.remove("main");
        }
        Dictionary dictionary = dict;
        HashMap map3 = new HashMap();
        for (String str3 : hashSet) {
            if (z || !dictionaryGroupFindDictionaryGroupWithLocale2.hasDict(str3, account)) {
                dictionaryGroup2 = dictionaryGroupFindDictionaryGroupWithLocale2;
                map = map3;
                subDict = getSubDict(str3, context, newLocale, null, dictNamePrefix, account);
                str = str3;
            } else {
                subDict = dictionaryGroupFindDictionaryGroupWithLocale2.getSubDict(str3);
                arrayList2.remove(str3);
                str = str3;
                dictionaryGroup2 = dictionaryGroupFindDictionaryGroupWithLocale2;
                map = map3;
            }
            map.put(str, subDict);
            map3 = map;
            dictionaryGroupFindDictionaryGroupWithLocale2 = dictionaryGroup2;
        }
        DictionaryGroup dictionaryGroup3 = new DictionaryGroup(newLocale, dictionary, account, map3);
        synchronized (this.mLock) {
            dictionaryGroup = this.mDictionaryGroup;
            this.mDictionaryGroup = dictionaryGroup3;
            if (hasAtLeastOneUninitializedMainDictionary()) {
                asyncReloadUninitializedMainDictionaries(context, newLocale, listener);
            }
        }
        if (listener != null) {
            listener.onUpdateMainDictionaryAvailability(hasAtLeastOneInitializedMainDictionary());
        }
        for (Locale locale : map2.keySet()) {
            ArrayList arrayList3 = (ArrayList) map2.get(locale);
            DictionaryGroup dictionaryGroupFindDictionaryGroupWithLocale3 = findDictionaryGroupWithLocale(dictionaryGroup, locale);
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                dictionaryGroupFindDictionaryGroupWithLocale3.closeDict((String) it.next());
            }
        }
        LruCache<String, Boolean> lruCache = this.mValidSpellingWordWriteCache;
        if (lruCache != null) {
            lruCache.evictAll();
        }
    }

    private void asyncReloadUninitializedMainDictionaries(final Context context, final Locale locale, final DictionaryFacilitator.DictionaryInitializationListener listener) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.mLatchForWaitingLoadingMainDictionaries = countDownLatch;
        ExecutorUtils.getBackgroundExecutor(ExecutorUtils.KEYBOARD).execute(new Runnable() { // from class: com.android.inputmethod.latin.DictionaryFacilitatorImpl.1
            @Override // java.lang.Runnable
            public void run() {
                DictionaryFacilitatorImpl.this.doReloadUninitializedMainDictionaries(context, locale, listener, countDownLatch);
            }
        });
    }

    void doReloadUninitializedMainDictionaries(final Context context, final Locale locale, final DictionaryFacilitator.DictionaryInitializationListener listener, final CountDownLatch latchForWaitingLoadingMainDictionary) {
        DictionaryGroup dictionaryGroupFindDictionaryGroupWithLocale = findDictionaryGroupWithLocale(this.mDictionaryGroup, locale);
        if (dictionaryGroupFindDictionaryGroupWithLocale == null) {
            Log.w(TAG, "Expected a dictionary group for " + locale + " but none found");
            return;
        }
        DictionaryCollection dictionaryCollectionCreateMainDictionaryFromManager = DictionaryFactory.createMainDictionaryFromManager(context, locale);
        synchronized (this.mLock) {
            if (locale.equals(dictionaryGroupFindDictionaryGroupWithLocale.mLocale)) {
                dictionaryGroupFindDictionaryGroupWithLocale.setMainDict(dictionaryCollectionCreateMainDictionaryFromManager);
            } else {
                dictionaryCollectionCreateMainDictionaryFromManager.close();
            }
        }
        if (listener != null) {
            listener.onUpdateMainDictionaryAvailability(hasAtLeastOneInitializedMainDictionary());
        }
        latchForWaitingLoadingMainDictionary.countDown();
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public void resetDictionariesForTesting(final Context context, final Locale locale, final ArrayList<String> dictionaryTypes, final HashMap<String, File> dictionaryFiles, final Map<String, Map<String, String>> additionalDictAttributes, @Nullable final String account) {
        HashMap map = new HashMap();
        DictionaryCollection dictionaryCollectionCreateMainDictionaryFromManager = null;
        for (String str : dictionaryTypes) {
            if (str.equals("main")) {
                dictionaryCollectionCreateMainDictionaryFromManager = DictionaryFactory.createMainDictionaryFromManager(context, locale);
            } else {
                ExpandableBinaryDictionary subDict = getSubDict(str, context, locale, dictionaryFiles.get(str), "", account);
                if (additionalDictAttributes.containsKey(str)) {
                    subDict.clearAndFlushDictionaryWithAdditionalAttributes(additionalDictAttributes.get(str));
                }
                if (subDict == null) {
                    throw new RuntimeException("Unknown dictionary type: " + str);
                }
                subDict.reloadDictionaryIfRequired();
                subDict.waitAllTasksForTests();
                map.put(str, subDict);
            }
        }
        this.mDictionaryGroup = new DictionaryGroup(locale, dictionaryCollectionCreateMainDictionaryFromManager, account, map);
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public void closeDictionaries() {
        DictionaryGroup dictionaryGroup;
        synchronized (this.mLock) {
            dictionaryGroup = this.mDictionaryGroup;
            this.mDictionaryGroup = new DictionaryGroup();
        }
        for (String str : ALL_DICTIONARY_TYPES) {
            dictionaryGroup.closeDict(str);
        }
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public ExpandableBinaryDictionary getSubDictForTesting(final String dictName) {
        return this.mDictionaryGroup.getSubDict(dictName);
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public boolean hasAtLeastOneInitializedMainDictionary() {
        Dictionary dict = this.mDictionaryGroup.getDict("main");
        return dict != null && dict.isInitialized();
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public boolean hasAtLeastOneUninitializedMainDictionary() {
        Dictionary dict = this.mDictionaryGroup.getDict("main");
        return dict == null || !dict.isInitialized();
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public void waitForLoadingMainDictionaries(final long timeout, final TimeUnit unit) throws InterruptedException {
        this.mLatchForWaitingLoadingMainDictionaries.await(timeout, unit);
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public void waitForLoadingDictionariesForTesting(final long timeout, final TimeUnit unit) throws InterruptedException {
        waitForLoadingMainDictionaries(timeout, unit);
        Iterator<ExpandableBinaryDictionary> it = this.mDictionaryGroup.mSubDictMap.values().iterator();
        while (it.hasNext()) {
            it.next().waitAllTasksForTests();
        }
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public void addToUserHistory(final String suggestion, final boolean wasAutoCapitalized, @Nonnull final NgramContext ngramContext, final long timeStampInSeconds, final boolean blockPotentiallyOffensive) {
        putWordIntoValidSpellingWordCache("addToUserHistory", suggestion);
        String[] strArrSplit = suggestion.split(" ");
        NgramContext nextNgramContext = ngramContext;
        int i = 0;
        while (i < strArrSplit.length) {
            String str = strArrSplit[i];
            addWordToUserHistory(this.mDictionaryGroup, nextNgramContext, str, i == 0 ? wasAutoCapitalized : false, (int) timeStampInSeconds, blockPotentiallyOffensive);
            nextNgramContext = nextNgramContext.getNextNgramContext(new NgramContext.WordInfo(str));
            i++;
        }
    }

    private void putWordIntoValidSpellingWordCache(@Nonnull final String caller, @Nonnull final String originalWord) {
        if (this.mValidSpellingWordWriteCache == null) {
            return;
        }
        String lowerCase = originalWord.toLowerCase(getLocale());
        boolean zIsValidSpellingWord = isValidSpellingWord(lowerCase);
        this.mValidSpellingWordWriteCache.put(lowerCase, Boolean.valueOf(zIsValidSpellingWord));
        String strCapitalizeFirstAndDowncaseRest = StringUtils.capitalizeFirstAndDowncaseRest(originalWord, getLocale());
        this.mValidSpellingWordWriteCache.put(strCapitalizeFirstAndDowncaseRest, Boolean.valueOf(zIsValidSpellingWord ? true : isValidSpellingWord(strCapitalizeFirstAndDowncaseRest)));
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void addWordToUserHistory(final com.android.inputmethod.latin.DictionaryFacilitatorImpl.DictionaryGroup r4, final com.android.inputmethod.latin.NgramContext r5, final java.lang.String r6, final boolean r7, final int r8, final boolean r9) {
        /*
            r3 = this;
            java.lang.String r0 = "history"
            com.android.inputmethod.latin.ExpandableBinaryDictionary r0 = r4.getSubDict(r0)
            if (r0 == 0) goto L51
            java.util.Locale r1 = r0.mLocale
            boolean r1 = r3.isForLocale(r1)
            if (r1 != 0) goto L11
            goto L51
        L11:
            int r1 = r3.getFrequency(r6)
            if (r1 != 0) goto L1a
            if (r9 == 0) goto L1a
            return
        L1a:
            java.util.Locale r9 = r4.mLocale
            java.lang.String r9 = r6.toLowerCase(r9)
            if (r7 == 0) goto L2f
            boolean r4 = r3.isValidSuggestionWord(r6)
            if (r4 == 0) goto L48
            boolean r4 = r3.isValidSuggestionWord(r9)
            if (r4 != 0) goto L48
            goto L49
        L2f:
            r7 = 0
            java.lang.String r2 = "main"
            boolean r7 = r4.hasDict(r2, r7)
            if (r7 == 0) goto L41
            com.android.inputmethod.latin.Dictionary r4 = r4.getDict(r2)
            int r4 = r4.getFrequency(r9)
            goto L42
        L41:
            r4 = -1
        L42:
            if (r1 >= r4) goto L49
            r7 = 140(0x8c, float:1.96E-43)
            if (r4 < r7) goto L49
        L48:
            r6 = r9
        L49:
            if (r1 <= 0) goto L4d
            r4 = 1
            goto L4e
        L4d:
            r4 = 0
        L4e:
            com.android.inputmethod.latin.personalization.UserHistoryDictionary.addToDictionary(r0, r5, r6, r4, r8)
        L51:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.latin.DictionaryFacilitatorImpl.addWordToUserHistory(com.android.inputmethod.latin.DictionaryFacilitatorImpl$DictionaryGroup, com.android.inputmethod.latin.NgramContext, java.lang.String, boolean, int, boolean):void");
    }

    private void removeWord(final String dictName, final String word) {
        ExpandableBinaryDictionary subDict = this.mDictionaryGroup.getSubDict(dictName);
        if (subDict != null) {
            subDict.removeUnigramEntryDynamically(word);
        }
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public void unlearnFromUserHistory(final String word, @Nonnull final NgramContext ngramContext, final long timeStampInSeconds, final int eventType) {
        if (eventType != 1) {
            removeWord(Dictionary.TYPE_USER_HISTORY, word);
        }
        putWordIntoValidSpellingWordCache("unlearnFromUserHistory", word.toLowerCase());
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    @Nonnull
    public SuggestionResults getSuggestionResults(ComposedData composedData, NgramContext ngramContext, @Nonnull final Keyboard keyboard, SettingsValuesForSuggestion settingsValuesForSuggestion, int sessionId, int inputStyle) {
        float f;
        int i;
        long nativeProximityInfo = keyboard.getProximityInfo().getNativeProximityInfo();
        SuggestionResults suggestionResults = new SuggestionResults(18, ngramContext.isBeginningOfSentenceContext(), false);
        float[] fArr = {-1.0f};
        String[] strArr = ALL_DICTIONARY_TYPES;
        int length = strArr.length;
        int i2 = 0;
        while (i2 < length) {
            Dictionary dict = this.mDictionaryGroup.getDict(strArr[i2]);
            if (dict == null) {
                i = i2;
            } else {
                if (composedData.mIsBatchMode) {
                    f = this.mDictionaryGroup.mWeightForGesturingInLocale;
                } else {
                    f = this.mDictionaryGroup.mWeightForTypingInLocale;
                }
                i = i2;
                ArrayList<SuggestedWords.SuggestedWordInfo> suggestions = dict.getSuggestions(composedData, ngramContext, nativeProximityInfo, settingsValuesForSuggestion, sessionId, f, fArr);
                if (suggestions != null) {
                    suggestionResults.addAll(suggestions);
                    if (suggestionResults.mRawSuggestions != null) {
                        suggestionResults.mRawSuggestions.addAll(suggestions);
                    }
                }
            }
            i2 = i + 1;
        }
        return suggestionResults;
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public boolean isValidSpellingWord(final String word) {
        Boolean bool;
        LruCache<String, Boolean> lruCache = this.mValidSpellingWordReadCache;
        if (lruCache != null && (bool = lruCache.get(word)) != null) {
            return bool.booleanValue();
        }
        return isValidWord(word, ALL_DICTIONARY_TYPES);
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public boolean isValidSuggestionWord(final String word) {
        return isValidWord(word, ALL_DICTIONARY_TYPES);
    }

    private boolean isValidWord(final String word, final String[] dictionariesToCheck) {
        if (TextUtils.isEmpty(word) || this.mDictionaryGroup.mLocale == null) {
            return false;
        }
        for (String str : dictionariesToCheck) {
            Dictionary dict = this.mDictionaryGroup.getDict(str);
            if (dict != null && dict.isValidWord(word)) {
                return true;
            }
        }
        return false;
    }

    private int getFrequency(final String word) {
        int frequency;
        int i = -1;
        if (TextUtils.isEmpty(word)) {
            return -1;
        }
        for (String str : ALL_DICTIONARY_TYPES) {
            Dictionary dict = this.mDictionaryGroup.getDict(str);
            if (dict != null && (frequency = dict.getFrequency(word)) >= i) {
                i = frequency;
            }
        }
        return i;
    }

    private boolean clearSubDictionary(final String dictName) {
        ExpandableBinaryDictionary subDict = this.mDictionaryGroup.getSubDict(dictName);
        if (subDict == null) {
            return false;
        }
        subDict.clear();
        return true;
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public boolean clearUserHistoryDictionary(final Context context) {
        return clearSubDictionary(Dictionary.TYPE_USER_HISTORY);
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    public void dumpDictionaryForDebug(final String dictName) {
        ExpandableBinaryDictionary subDict = this.mDictionaryGroup.getSubDict(dictName);
        if (subDict == null) {
            Log.e(TAG, "Cannot dump " + dictName + ". The dictionary is not being used for suggestion or cannot be dumped.");
        } else {
            subDict.dumpAllWordsForDebug();
        }
    }

    @Override // com.android.inputmethod.latin.DictionaryFacilitator
    @Nonnull
    public List<DictionaryStats> getDictionaryStats(final Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : DYNAMIC_DICTIONARY_TYPES) {
            ExpandableBinaryDictionary subDict = this.mDictionaryGroup.getSubDict(str);
            if (subDict != null) {
                arrayList.add(subDict.getDictionaryStats());
            }
        }
        return arrayList;
    }
}
