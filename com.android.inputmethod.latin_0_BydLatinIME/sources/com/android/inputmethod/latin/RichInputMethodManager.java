package com.android.inputmethod.latin;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import com.android.inputmethod.compat.InputMethodManagerCompatWrapper;
import com.android.inputmethod.compat.InputMethodSubtypeCompatUtils;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.settings.Settings;
import com.android.inputmethod.latin.utils.AdditionalSubtypeUtils;
import com.android.inputmethod.latin.utils.LanguageOnSpacebarUtils;
import com.android.inputmethod.latin.utils.SubtypeLocaleUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class RichInputMethodManager {
    private static final boolean DEBUG = false;
    private static final int INDEX_NOT_FOUND = -1;
    private static final String TAG = "RichInputMethodManager";
    private Context mContext;
    private RichInputMethodSubtype mCurrentRichInputMethodSubtype;
    private InputMethodManagerCompatWrapper mImmWrapper;
    private InputMethodInfoCache mInputMethodInfoCache;
    private InputMethodInfo mShortcutInputMethodInfo;
    private InputMethodSubtype mShortcutSubtype;
    private static final RichInputMethodManager sInstance = new RichInputMethodManager();
    private static RichInputMethodSubtype sForcedSubtypeForTesting = null;

    private RichInputMethodManager() {
    }

    public static RichInputMethodManager getInstance() {
        RichInputMethodManager richInputMethodManager = sInstance;
        richInputMethodManager.checkInitialized();
        return richInputMethodManager;
    }

    public static void init(final Context context) {
        sInstance.initInternal(context);
    }

    private boolean isInitialized() {
        return this.mImmWrapper != null;
    }

    private void checkInitialized() {
        if (!isInitialized()) {
            throw new RuntimeException(TAG + " is used before initialization");
        }
    }

    private void initInternal(final Context context) {
        if (isInitialized()) {
            return;
        }
        this.mImmWrapper = new InputMethodManagerCompatWrapper(context);
        this.mContext = context;
        this.mInputMethodInfoCache = new InputMethodInfoCache(this.mImmWrapper.mImm, context.getPackageName());
        SubtypeLocaleUtils.init(context);
        this.mImmWrapper.mImm.setAdditionalInputMethodSubtypes(getInputMethodIdOfThisIme(), getAdditionalSubtypes());
        refreshSubtypeCaches();
    }

    public InputMethodSubtype[] getAdditionalSubtypes() {
        return AdditionalSubtypeUtils.createAdditionalSubtypesArray(Settings.readPrefAdditionalSubtypes(PreferenceManager.getDefaultSharedPreferences(this.mContext), this.mContext.getResources()));
    }

    public InputMethodManager getInputMethodManager() {
        checkInitialized();
        return this.mImmWrapper.mImm;
    }

    public List<InputMethodSubtype> getMyEnabledInputMethodSubtypeList(boolean allowsImplicitlySelectedSubtypes) {
        return getEnabledInputMethodSubtypeList(getInputMethodInfoOfThisIme(), allowsImplicitlySelectedSubtypes);
    }

    public boolean switchToNextInputMethod(final IBinder token, final boolean onlyCurrentIme) {
        if (this.mImmWrapper.switchToNextInputMethod(token, onlyCurrentIme) || switchToNextInputSubtypeInThisIme(token, onlyCurrentIme)) {
            return true;
        }
        return switchToNextInputMethodAndSubtype(token);
    }

    private boolean switchToNextInputSubtypeInThisIme(final IBinder token, final boolean onlyCurrentIme) {
        InputMethodSubtype currentInputMethodSubtype = this.mImmWrapper.mImm.getCurrentInputMethodSubtype();
        List<InputMethodSubtype> myEnabledInputMethodSubtypeList = getMyEnabledInputMethodSubtypeList(true);
        int subtypeIndexInList = getSubtypeIndexInList(currentInputMethodSubtype, myEnabledInputMethodSubtypeList);
        if (subtypeIndexInList == -1) {
            Log.w(TAG, "Can't find current subtype in enabled subtypes: subtype=" + SubtypeLocaleUtils.getSubtypeNameForLogging(currentInputMethodSubtype));
            return false;
        }
        int size = (subtypeIndexInList + 1) % myEnabledInputMethodSubtypeList.size();
        if (size <= subtypeIndexInList && !onlyCurrentIme) {
            return false;
        }
        setInputMethodAndSubtype(token, myEnabledInputMethodSubtypeList.get(size));
        return true;
    }

    private boolean switchToNextInputMethodAndSubtype(final IBinder token) {
        InputMethodManager inputMethodManager = this.mImmWrapper.mImm;
        List<InputMethodInfo> enabledInputMethodList = inputMethodManager.getEnabledInputMethodList();
        int imiIndexInList = getImiIndexInList(getInputMethodInfoOfThisIme(), enabledInputMethodList);
        if (imiIndexInList == -1) {
            Log.w(TAG, "Can't find current IME in enabled IMEs: IME package=" + getInputMethodInfoOfThisIme().getPackageName());
            return false;
        }
        InputMethodInfo nextNonAuxiliaryIme = getNextNonAuxiliaryIme(imiIndexInList, enabledInputMethodList);
        List<InputMethodSubtype> enabledInputMethodSubtypeList = getEnabledInputMethodSubtypeList(nextNonAuxiliaryIme, true);
        if (enabledInputMethodSubtypeList.isEmpty()) {
            inputMethodManager.setInputMethod(token, nextNonAuxiliaryIme.getId());
            return true;
        }
        inputMethodManager.setInputMethodAndSubtype(token, nextNonAuxiliaryIme.getId(), enabledInputMethodSubtypeList.get(0));
        return true;
    }

    private static int getImiIndexInList(final InputMethodInfo inputMethodInfo, final List<InputMethodInfo> imiList) {
        int size = imiList.size();
        for (int i = 0; i < size; i++) {
            if (imiList.get(i).equals(inputMethodInfo)) {
                return i;
            }
        }
        return -1;
    }

    private static InputMethodInfo getNextNonAuxiliaryIme(final int currentIndex, final List<InputMethodInfo> imiList) {
        int size = imiList.size();
        for (int i = 1; i < size; i++) {
            InputMethodInfo inputMethodInfo = imiList.get((currentIndex + i) % size);
            if (!isAuxiliaryIme(inputMethodInfo)) {
                return inputMethodInfo;
            }
        }
        return imiList.get(currentIndex);
    }

    private static boolean isAuxiliaryIme(final InputMethodInfo imi) {
        int subtypeCount = imi.getSubtypeCount();
        if (subtypeCount == 0) {
            return false;
        }
        for (int i = 0; i < subtypeCount; i++) {
            if (!imi.getSubtypeAt(i).isAuxiliary()) {
                return false;
            }
        }
        return true;
    }

    private static class InputMethodInfoCache {
        private InputMethodInfo mCachedThisImeInfo;
        private final String mImePackageName;
        private final InputMethodManager mImm;
        private final HashMap<InputMethodInfo, List<InputMethodSubtype>> mCachedSubtypeListWithImplicitlySelected = new HashMap<>();
        private final HashMap<InputMethodInfo, List<InputMethodSubtype>> mCachedSubtypeListOnlyExplicitlySelected = new HashMap<>();

        public InputMethodInfoCache(final InputMethodManager imm, final String imePackageName) {
            this.mImm = imm;
            this.mImePackageName = imePackageName;
        }

        public synchronized InputMethodInfo getInputMethodOfThisIme() {
            InputMethodInfo inputMethodInfo = this.mCachedThisImeInfo;
            if (inputMethodInfo != null) {
                return inputMethodInfo;
            }
            for (InputMethodInfo inputMethodInfo2 : this.mImm.getInputMethodList()) {
                if (inputMethodInfo2.getPackageName().equals(this.mImePackageName)) {
                    this.mCachedThisImeInfo = inputMethodInfo2;
                    return inputMethodInfo2;
                }
            }
            throw new RuntimeException("Input method id for " + this.mImePackageName + " not found.");
        }

        public synchronized List<InputMethodSubtype> getEnabledInputMethodSubtypeList(final InputMethodInfo imi, final boolean allowsImplicitlySelectedSubtypes) {
            HashMap<InputMethodInfo, List<InputMethodSubtype>> map;
            if (allowsImplicitlySelectedSubtypes) {
                map = this.mCachedSubtypeListWithImplicitlySelected;
            } else {
                map = this.mCachedSubtypeListOnlyExplicitlySelected;
            }
            List<InputMethodSubtype> list = map.get(imi);
            if (list != null) {
                return list;
            }
            List<InputMethodSubtype> enabledInputMethodSubtypeList = this.mImm.getEnabledInputMethodSubtypeList(imi, allowsImplicitlySelectedSubtypes);
            map.put(imi, enabledInputMethodSubtypeList);
            return enabledInputMethodSubtypeList;
        }

        public synchronized void clear() {
            this.mCachedThisImeInfo = null;
            this.mCachedSubtypeListWithImplicitlySelected.clear();
            this.mCachedSubtypeListOnlyExplicitlySelected.clear();
        }
    }

    public InputMethodInfo getInputMethodInfoOfThisIme() {
        return this.mInputMethodInfoCache.getInputMethodOfThisIme();
    }

    public String getInputMethodIdOfThisIme() {
        return getInputMethodInfoOfThisIme().getId();
    }

    public boolean checkIfSubtypeBelongsToThisImeAndEnabled(final InputMethodSubtype subtype) {
        return checkIfSubtypeBelongsToList(subtype, getEnabledInputMethodSubtypeList(getInputMethodInfoOfThisIme(), true));
    }

    public boolean checkIfSubtypeBelongsToThisImeAndImplicitlyEnabled(final InputMethodSubtype subtype) {
        return checkIfSubtypeBelongsToThisImeAndEnabled(subtype) && !checkIfSubtypeBelongsToList(subtype, getMyEnabledInputMethodSubtypeList(false));
    }

    private static boolean checkIfSubtypeBelongsToList(final InputMethodSubtype subtype, final List<InputMethodSubtype> subtypes) {
        return getSubtypeIndexInList(subtype, subtypes) != -1;
    }

    private static int getSubtypeIndexInList(final InputMethodSubtype subtype, final List<InputMethodSubtype> subtypes) {
        int size = subtypes.size();
        for (int i = 0; i < size; i++) {
            if (subtypes.get(i).equals(subtype)) {
                return i;
            }
        }
        return -1;
    }

    public void onSubtypeChanged(@Nonnull final InputMethodSubtype newSubtype) {
        updateCurrentSubtype(newSubtype);
        updateShortcutIme();
    }

    static void forceSubtype(@Nonnull final InputMethodSubtype subtype) {
        sForcedSubtypeForTesting = RichInputMethodSubtype.getRichInputMethodSubtype(subtype);
    }

    @Nonnull
    public Locale getCurrentSubtypeLocale() {
        RichInputMethodSubtype richInputMethodSubtype = sForcedSubtypeForTesting;
        if (richInputMethodSubtype != null) {
            return richInputMethodSubtype.getLocale();
        }
        return getCurrentSubtype().getLocale();
    }

    @Nonnull
    public RichInputMethodSubtype getCurrentSubtype() {
        RichInputMethodSubtype richInputMethodSubtype = sForcedSubtypeForTesting;
        return richInputMethodSubtype != null ? richInputMethodSubtype : this.mCurrentRichInputMethodSubtype;
    }

    public String getCombiningRulesExtraValueOfCurrentSubtype() {
        return SubtypeLocaleUtils.getCombiningRulesExtraValue(getCurrentSubtype().getRawSubtype());
    }

    public boolean hasMultipleEnabledIMEsOrSubtypes(final boolean shouldIncludeAuxiliarySubtypes) {
        return hasMultipleEnabledSubtypes(shouldIncludeAuxiliarySubtypes, this.mImmWrapper.mImm.getEnabledInputMethodList());
    }

    public boolean hasMultipleEnabledSubtypesInThisIme(final boolean shouldIncludeAuxiliarySubtypes) {
        return hasMultipleEnabledSubtypes(shouldIncludeAuxiliarySubtypes, Collections.singletonList(getInputMethodInfoOfThisIme()));
    }

    private boolean hasMultipleEnabledSubtypes(final boolean shouldIncludeAuxiliarySubtypes, final List<InputMethodInfo> imiList) {
        int i = 0;
        for (InputMethodInfo inputMethodInfo : imiList) {
            if (i > 1) {
                return true;
            }
            List<InputMethodSubtype> enabledInputMethodSubtypeList = getEnabledInputMethodSubtypeList(inputMethodInfo, true);
            if (!enabledInputMethodSubtypeList.isEmpty()) {
                Iterator<InputMethodSubtype> it = enabledInputMethodSubtypeList.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    if (it.next().isAuxiliary()) {
                        i2++;
                    }
                }
                if (enabledInputMethodSubtypeList.size() - i2 > 0 || (shouldIncludeAuxiliarySubtypes && i2 > 1)) {
                }
            }
            i++;
        }
        if (i > 1) {
            return true;
        }
        Iterator<InputMethodSubtype> it2 = getMyEnabledInputMethodSubtypeList(true).iterator();
        int i3 = 0;
        while (it2.hasNext()) {
            if (Constants.Subtype.KEYBOARD_MODE.equals(it2.next().getMode())) {
                i3++;
            }
        }
        return i3 > 1;
    }

    public InputMethodSubtype findSubtypeByLocaleAndKeyboardLayoutSet(final String localeString, final String keyboardLayoutSetName) {
        InputMethodInfo inputMethodInfoOfThisIme = getInputMethodInfoOfThisIme();
        int subtypeCount = inputMethodInfoOfThisIme.getSubtypeCount();
        for (int i = 0; i < subtypeCount; i++) {
            InputMethodSubtype subtypeAt = inputMethodInfoOfThisIme.getSubtypeAt(i);
            String keyboardLayoutSetName2 = SubtypeLocaleUtils.getKeyboardLayoutSetName(subtypeAt);
            if (localeString.equals(subtypeAt.getLocale()) && keyboardLayoutSetName.equals(keyboardLayoutSetName2)) {
                return subtypeAt;
            }
        }
        return null;
    }

    public InputMethodSubtype findSubtypeByLocale(final Locale locale) {
        List<InputMethodSubtype> myEnabledInputMethodSubtypeList = getMyEnabledInputMethodSubtypeList(true);
        int size = myEnabledInputMethodSubtypeList.size();
        for (int i = 0; i < size; i++) {
            InputMethodSubtype inputMethodSubtype = myEnabledInputMethodSubtypeList.get(i);
            if (InputMethodSubtypeCompatUtils.getLocaleObject(inputMethodSubtype).equals(locale)) {
                return inputMethodSubtype;
            }
        }
        for (int i2 = 0; i2 < size; i2++) {
            InputMethodSubtype inputMethodSubtype2 = myEnabledInputMethodSubtypeList.get(i2);
            Locale localeObject = InputMethodSubtypeCompatUtils.getLocaleObject(inputMethodSubtype2);
            if (localeObject.getLanguage().equals(locale.getLanguage()) && localeObject.getCountry().equals(locale.getCountry()) && localeObject.getVariant().equals(locale.getVariant())) {
                return inputMethodSubtype2;
            }
        }
        for (int i3 = 0; i3 < size; i3++) {
            InputMethodSubtype inputMethodSubtype3 = myEnabledInputMethodSubtypeList.get(i3);
            Locale localeObject2 = InputMethodSubtypeCompatUtils.getLocaleObject(inputMethodSubtype3);
            if (localeObject2.getLanguage().equals(locale.getLanguage()) && localeObject2.getCountry().equals(locale.getCountry())) {
                return inputMethodSubtype3;
            }
        }
        for (int i4 = 0; i4 < size; i4++) {
            InputMethodSubtype inputMethodSubtype4 = myEnabledInputMethodSubtypeList.get(i4);
            if (InputMethodSubtypeCompatUtils.getLocaleObject(inputMethodSubtype4).getLanguage().equals(locale.getLanguage())) {
                return inputMethodSubtype4;
            }
        }
        return null;
    }

    public void setInputMethodAndSubtype(final IBinder token, final InputMethodSubtype subtype) {
        this.mImmWrapper.mImm.setInputMethodAndSubtype(token, getInputMethodIdOfThisIme(), subtype);
    }

    public void setAdditionalInputMethodSubtypes(final InputMethodSubtype[] subtypes) {
        this.mImmWrapper.mImm.setAdditionalInputMethodSubtypes(getInputMethodIdOfThisIme(), subtypes);
        refreshSubtypeCaches();
    }

    private List<InputMethodSubtype> getEnabledInputMethodSubtypeList(final InputMethodInfo imi, final boolean allowsImplicitlySelectedSubtypes) {
        return this.mInputMethodInfoCache.getEnabledInputMethodSubtypeList(imi, allowsImplicitlySelectedSubtypes);
    }

    public void refreshSubtypeCaches() {
        this.mInputMethodInfoCache.clear();
        updateCurrentSubtype(this.mImmWrapper.mImm.getCurrentInputMethodSubtype());
        updateShortcutIme();
    }

    public boolean shouldOfferSwitchingToNextInputMethod(final IBinder binder, boolean defaultValue) {
        return Build.VERSION.SDK_INT <= 19 ? defaultValue : this.mImmWrapper.shouldOfferSwitchingToNextInputMethod(binder);
    }

    public boolean isSystemLocaleSameAsLocaleOfAllEnabledSubtypesOfEnabledImes() {
        Locale locale = this.mContext.getResources().getConfiguration().locale;
        HashSet<InputMethodSubtype> hashSet = new HashSet();
        InputMethodManager inputMethodManager = getInputMethodManager();
        Iterator<InputMethodInfo> it = inputMethodManager.getEnabledInputMethodList().iterator();
        while (it.hasNext()) {
            List<InputMethodSubtype> enabledInputMethodSubtypeList = inputMethodManager.getEnabledInputMethodSubtypeList(it.next(), true);
            if (enabledInputMethodSubtypeList.isEmpty()) {
                return false;
            }
            hashSet.addAll(enabledInputMethodSubtypeList);
        }
        for (InputMethodSubtype inputMethodSubtype : hashSet) {
            if (!inputMethodSubtype.isAuxiliary() && !inputMethodSubtype.getLocale().isEmpty() && !locale.equals(SubtypeLocaleUtils.getSubtypeLocale(inputMethodSubtype))) {
                return false;
            }
        }
        return true;
    }

    private void updateCurrentSubtype(@Nullable final InputMethodSubtype subtype) {
        this.mCurrentRichInputMethodSubtype = RichInputMethodSubtype.getRichInputMethodSubtype(subtype);
    }

    private void updateShortcutIme() {
        RichInputMethodSubtype richInputMethodSubtype = this.mCurrentRichInputMethodSubtype;
        LanguageOnSpacebarUtils.onSubtypeChanged(richInputMethodSubtype, checkIfSubtypeBelongsToThisImeAndImplicitlyEnabled(richInputMethodSubtype.getRawSubtype()), this.mContext.getResources().getConfiguration().locale);
        LanguageOnSpacebarUtils.setEnabledSubtypes(getMyEnabledInputMethodSubtypeList(true));
        Map<InputMethodInfo, List<InputMethodSubtype>> shortcutInputMethodsAndSubtypes = getInputMethodManager().getShortcutInputMethodsAndSubtypes();
        this.mShortcutInputMethodInfo = null;
        this.mShortcutSubtype = null;
        Iterator<InputMethodInfo> it = shortcutInputMethodsAndSubtypes.keySet().iterator();
        if (it.hasNext()) {
            InputMethodInfo next = it.next();
            List<InputMethodSubtype> list = shortcutInputMethodsAndSubtypes.get(next);
            this.mShortcutInputMethodInfo = next;
            this.mShortcutSubtype = list.size() > 0 ? list.get(0) : null;
        }
    }

    public void switchToShortcutIme(final InputMethodService context) {
        InputMethodInfo inputMethodInfo = this.mShortcutInputMethodInfo;
        if (inputMethodInfo == null) {
            return;
        }
        switchToTargetIME(inputMethodInfo.getId(), this.mShortcutSubtype, context);
    }

    /* JADX WARN: Type inference failed for: r9v4, types: [com.android.inputmethod.latin.RichInputMethodManager$1] */
    private void switchToTargetIME(final String imiId, final InputMethodSubtype subtype, final InputMethodService context) {
        final IBinder iBinder = context.getWindow().getWindow().getAttributes().token;
        if (iBinder == null) {
            return;
        }
        final InputMethodManager inputMethodManager = getInputMethodManager();
        new AsyncTask<Void, Void, Void>() { // from class: com.android.inputmethod.latin.RichInputMethodManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... params) {
                inputMethodManager.setInputMethodAndSubtype(iBinder, imiId, subtype);
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public boolean isShortcutImeReady() {
        if (this.mShortcutInputMethodInfo == null) {
            return false;
        }
        if (this.mShortcutSubtype == null) {
        }
        return true;
    }
}
