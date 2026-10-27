package com.android.inputmethod.keyboard.emoji;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.util.Pair;
import com.android.inputmethod.compat.BuildCompatUtils;
import com.android.inputmethod.dictionarypack.MetadataDbHelper;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.KeyboardLayoutSet;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.settings.Settings;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
final class EmojiCategory {
    private static final int ID_EIGHT_ACTIVITY = 12;
    private static final int ID_EIGHT_ANIMALS_NATURE = 9;
    private static final int ID_EIGHT_FLAGS = 15;
    private static final int ID_EIGHT_FOOD_DRINK = 10;
    private static final int ID_EIGHT_OBJECTS = 13;
    private static final int ID_EIGHT_SMILEY_PEOPLE = 8;
    private static final int ID_EIGHT_SMILEY_PEOPLE_BORING = 16;
    private static final int ID_EIGHT_SYMBOLS = 14;
    private static final int ID_EIGHT_TRAVEL_PLACES = 11;
    private static final int ID_EMOTICONS = 6;
    private static final int ID_FLAGS = 7;
    private static final int ID_NATURE = 3;
    private static final int ID_OBJECTS = 2;
    private static final int ID_PEOPLE = 1;
    private static final int ID_PLACES = 4;
    public static final int ID_RECENTS = 0;
    private static final int ID_SYMBOLS = 5;
    private static final int ID_UNSPECIFIED = -1;
    private int mCurrentCategoryId;
    private final KeyboardLayoutSet mLayoutSet;
    private final int mMaxPageKeyCount;
    private final SharedPreferences mPrefs;
    private final Resources mRes;
    private static final String[] sCategoryName = {"recents", "people", "objects", "nature", "places", "symbols", "emoticons", MetadataDbHelper.FLAGS_COLUMN, "smiley & people", "animals & nature", "food & drink", "travel & places", "activity", "objects2", "symbols2", "flags2", "smiley & people2"};
    private static final int[] sCategoryTabIconAttr = {21, 12, 13, 14, 15, 16, 17, 18, 19, 20, 5, 6, 7, 8, 9, 10, 11};
    private static final int[] sAccessibilityDescriptionResourceIdsForCategories = {R.string.spoken_descrption_emoji_category_recents, R.string.spoken_descrption_emoji_category_people, R.string.spoken_descrption_emoji_category_objects, R.string.spoken_descrption_emoji_category_nature, R.string.spoken_descrption_emoji_category_places, R.string.spoken_descrption_emoji_category_symbols, R.string.spoken_descrption_emoji_category_emoticons, R.string.spoken_descrption_emoji_category_flags, R.string.spoken_descrption_emoji_category_eight_smiley_people, R.string.spoken_descrption_emoji_category_eight_animals_nature, R.string.spoken_descrption_emoji_category_eight_food_drink, R.string.spoken_descrption_emoji_category_eight_travel_places, R.string.spoken_descrption_emoji_category_eight_activity, R.string.spoken_descrption_emoji_category_objects, R.string.spoken_descrption_emoji_category_symbols, R.string.spoken_descrption_emoji_category_flags, R.string.spoken_descrption_emoji_category_eight_smiley_people};
    private static final int[] sCategoryElementId = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
    private static Comparator<Key> EMOJI_KEY_COMPARATOR = new Comparator<Key>() { // from class: com.android.inputmethod.keyboard.emoji.EmojiCategory.1
        @Override // java.util.Comparator
        public int compare(final Key lhs, final Key rhs) {
            Rect hitBox = lhs.getHitBox();
            Rect hitBox2 = rhs.getHitBox();
            if (hitBox.top < hitBox2.top) {
                return -1;
            }
            if (hitBox.top > hitBox2.top) {
                return 1;
            }
            if (hitBox.left < hitBox2.left) {
                return -1;
            }
            if (hitBox.left > hitBox2.left) {
                return 1;
            }
            if (lhs.getCode() == rhs.getCode()) {
                return 0;
            }
            return lhs.getCode() < rhs.getCode() ? -1 : 1;
        }
    };
    private final String TAG = EmojiCategory.class.getSimpleName();
    private final HashMap<String, Integer> mCategoryNameToIdMap = new HashMap<>();
    private final int[] mCategoryTabIconId = new int[sCategoryName.length];
    private final ArrayList<CategoryProperties> mShownCategories = new ArrayList<>();
    private final ConcurrentHashMap<Long, DynamicGridKeyboard> mCategoryKeyboardMap = new ConcurrentHashMap<>();
    private int mCurrentCategoryPageId = 0;

    public final class CategoryProperties {
        public final int mCategoryId;
        public final int mPageCount;

        public CategoryProperties(final int categoryId, final int pageCount) {
            this.mCategoryId = categoryId;
            this.mPageCount = pageCount;
        }
    }

    public EmojiCategory(final SharedPreferences prefs, final Resources res, final KeyboardLayoutSet layoutSet, final TypedArray emojiPaletteViewAttr) {
        this.mCurrentCategoryId = -1;
        this.mPrefs = prefs;
        this.mRes = res;
        this.mMaxPageKeyCount = res.getInteger(R.integer.config_emoji_keyboard_max_page_key_count);
        this.mLayoutSet = layoutSet;
        int i = 0;
        while (true) {
            String[] strArr = sCategoryName;
            if (i >= strArr.length) {
                break;
            }
            this.mCategoryNameToIdMap.put(strArr[i], Integer.valueOf(i));
            this.mCategoryTabIconId[i] = emojiPaletteViewAttr.getResourceId(sCategoryTabIconAttr[i], 0);
            i++;
        }
        addShownCategoryId(0);
        int i2 = 8;
        if (BuildCompatUtils.EFFECTIVE_SDK_INT >= 19) {
            if (canShowUnicodeEightEmoji()) {
                addShownCategoryId(8);
                addShownCategoryId(9);
                addShownCategoryId(10);
                addShownCategoryId(11);
                addShownCategoryId(12);
                addShownCategoryId(13);
                addShownCategoryId(14);
                addShownCategoryId(7);
            } else {
                addShownCategoryId(1);
                addShownCategoryId(2);
                addShownCategoryId(3);
                addShownCategoryId(4);
                addShownCategoryId(5);
                if (canShowFlagEmoji()) {
                    addShownCategoryId(7);
                }
                i2 = 1;
            }
        } else {
            addShownCategoryId(5);
            i2 = 5;
        }
        addShownCategoryId(6);
        DynamicGridKeyboard keyboard = getKeyboard(0, 0);
        keyboard.loadRecentKeys(this.mCategoryKeyboardMap.values());
        this.mCurrentCategoryId = Settings.readLastShownEmojiCategoryId(this.mPrefs, i2);
        Log.i(this.TAG, "Last Emoji category id is " + this.mCurrentCategoryId);
        if (!isShownCategoryId(this.mCurrentCategoryId)) {
            Log.i(this.TAG, "Last emoji category " + this.mCurrentCategoryId + " is invalid, starting in " + i2);
            this.mCurrentCategoryId = i2;
        } else if (this.mCurrentCategoryId == 0 && keyboard.getSortedKeys().isEmpty()) {
            Log.i(this.TAG, "No recent emojis found, starting in category " + i2);
            this.mCurrentCategoryId = i2;
        }
    }

    private void addShownCategoryId(final int categoryId) {
        getKeyboard(categoryId, 0);
        this.mShownCategories.add(new CategoryProperties(categoryId, getCategoryPageCount(categoryId)));
    }

    private boolean isShownCategoryId(final int categoryId) {
        Iterator<CategoryProperties> it = this.mShownCategories.iterator();
        while (it.hasNext()) {
            if (it.next().mCategoryId == categoryId) {
                return true;
            }
        }
        return false;
    }

    public static String getCategoryName(final int categoryId, final int categoryPageId) {
        return sCategoryName[categoryId] + "-" + categoryPageId;
    }

    public int getCategoryId(final String name) {
        return this.mCategoryNameToIdMap.get(name.split("-")[0]).intValue();
    }

    public int getCategoryTabIcon(final int categoryId) {
        return this.mCategoryTabIconId[categoryId];
    }

    public String getAccessibilityDescription(final int categoryId) {
        return this.mRes.getString(sAccessibilityDescriptionResourceIdsForCategories[categoryId]);
    }

    public ArrayList<CategoryProperties> getShownCategories() {
        return this.mShownCategories;
    }

    public int getCurrentCategoryId() {
        return this.mCurrentCategoryId;
    }

    public int getCurrentCategoryPageSize() {
        return getCategoryPageSize(this.mCurrentCategoryId);
    }

    public int getCategoryPageSize(final int categoryId) {
        for (CategoryProperties categoryProperties : this.mShownCategories) {
            if (categoryProperties.mCategoryId == categoryId) {
                return categoryProperties.mPageCount;
            }
        }
        Log.w(this.TAG, "Invalid category id: " + categoryId);
        return 0;
    }

    public void setCurrentCategoryId(final int categoryId) {
        this.mCurrentCategoryId = categoryId;
        Settings.writeLastShownEmojiCategoryId(this.mPrefs, categoryId);
    }

    public void setCurrentCategoryPageId(final int id) {
        this.mCurrentCategoryPageId = id;
    }

    public int getCurrentCategoryPageId() {
        return this.mCurrentCategoryPageId;
    }

    public void saveLastTypedCategoryPage() {
        Settings.writeLastTypedEmojiCategoryPageId(this.mPrefs, this.mCurrentCategoryId, this.mCurrentCategoryPageId);
    }

    public boolean isInRecentTab() {
        return this.mCurrentCategoryId == 0;
    }

    public int getTabIdFromCategoryId(final int categoryId) {
        for (int i = 0; i < this.mShownCategories.size(); i++) {
            if (this.mShownCategories.get(i).mCategoryId == categoryId) {
                return i;
            }
        }
        Log.w(this.TAG, "categoryId not found: " + categoryId);
        return 0;
    }

    public int getPageIdFromCategoryId(final int categoryId) {
        int lastTypedEmojiCategoryPageId = Settings.readLastTypedEmojiCategoryPageId(this.mPrefs, categoryId);
        int i = 0;
        for (int i2 = 0; i2 < this.mShownCategories.size(); i2++) {
            CategoryProperties categoryProperties = this.mShownCategories.get(i2);
            if (categoryProperties.mCategoryId == categoryId) {
                return i + lastTypedEmojiCategoryPageId;
            }
            i += categoryProperties.mPageCount;
        }
        Log.w(this.TAG, "categoryId not found: " + categoryId);
        return 0;
    }

    public int getRecentTabId() {
        return getTabIdFromCategoryId(0);
    }

    private int getCategoryPageCount(final int categoryId) {
        return ((this.mLayoutSet.getKeyboard(sCategoryElementId[categoryId]).getSortedKeys().size() - 1) / this.mMaxPageKeyCount) + 1;
    }

    public Pair<Integer, Integer> getCategoryIdAndPageIdFromPagePosition(final int position) {
        int i = 0;
        for (CategoryProperties categoryProperties : this.mShownCategories) {
            int i2 = categoryProperties.mPageCount + i;
            if (i2 > position) {
                return new Pair<>(Integer.valueOf(categoryProperties.mCategoryId), Integer.valueOf(position - i));
            }
            i = i2;
        }
        return null;
    }

    public DynamicGridKeyboard getKeyboardFromPagePosition(final int position) {
        Pair<Integer, Integer> categoryIdAndPageIdFromPagePosition = getCategoryIdAndPageIdFromPagePosition(position);
        if (categoryIdAndPageIdFromPagePosition != null) {
            return getKeyboard(((Integer) categoryIdAndPageIdFromPagePosition.first).intValue(), ((Integer) categoryIdAndPageIdFromPagePosition.second).intValue());
        }
        return null;
    }

    private static final Long getCategoryKeyboardMapKey(final int categoryId, final int id) {
        return Long.valueOf(((long) id) | (((long) categoryId) << 32));
    }

    public DynamicGridKeyboard getKeyboard(final int categoryId, final int id) {
        synchronized (this.mCategoryKeyboardMap) {
            Long categoryKeyboardMapKey = getCategoryKeyboardMapKey(categoryId, id);
            if (this.mCategoryKeyboardMap.containsKey(categoryKeyboardMapKey)) {
                return this.mCategoryKeyboardMap.get(categoryKeyboardMapKey);
            }
            if (categoryId == 0) {
                DynamicGridKeyboard dynamicGridKeyboard = new DynamicGridKeyboard(this.mPrefs, this.mLayoutSet.getKeyboard(10), this.mMaxPageKeyCount, categoryId);
                this.mCategoryKeyboardMap.put(categoryKeyboardMapKey, dynamicGridKeyboard);
                return dynamicGridKeyboard;
            }
            Key[][] keyArrSortKeysIntoPages = sortKeysIntoPages(this.mLayoutSet.getKeyboard(sCategoryElementId[categoryId]).getSortedKeys(), this.mMaxPageKeyCount);
            for (int i = 0; i < keyArrSortKeysIntoPages.length; i++) {
                DynamicGridKeyboard dynamicGridKeyboard2 = new DynamicGridKeyboard(this.mPrefs, this.mLayoutSet.getKeyboard(10), this.mMaxPageKeyCount, categoryId);
                for (Key key : keyArrSortKeysIntoPages[i]) {
                    if (key == null) {
                        break;
                    }
                    dynamicGridKeyboard2.addKeyLast(key);
                }
                this.mCategoryKeyboardMap.put(getCategoryKeyboardMapKey(categoryId, i), dynamicGridKeyboard2);
            }
            return this.mCategoryKeyboardMap.get(categoryKeyboardMapKey);
        }
    }

    public int getTotalPageCountOfAllCategories() {
        Iterator<CategoryProperties> it = this.mShownCategories.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().mPageCount;
        }
        return i;
    }

    private static Key[][] sortKeysIntoPages(final List<Key> inKeys, final int maxPageCount) {
        ArrayList arrayList = new ArrayList(inKeys);
        Collections.sort(arrayList, EMOJI_KEY_COMPARATOR);
        Key[][] keyArr = (Key[][]) Array.newInstance((Class<?>) Key.class, ((arrayList.size() - 1) / maxPageCount) + 1, maxPageCount);
        for (int i = 0; i < arrayList.size(); i++) {
            keyArr[i / maxPageCount][i % maxPageCount] = (Key) arrayList.get(i);
        }
        return keyArr;
    }

    private static boolean canShowFlagEmoji() {
        Paint paint = new Paint();
        try {
            return paint.hasGlyph("🇨🇭");
        } catch (NoSuchMethodError unused) {
            return ((double) paint.measureText("🇨🇭")) < ((double) paint.measureText("🐧")) * 1.25d;
        }
    }

    private static boolean canShowUnicodeEightEmoji() {
        Paint paint = new Paint();
        try {
            return paint.hasGlyph("🧀");
        } catch (NoSuchMethodError unused) {
            return paint.measureText("🧀") > paint.measureText("\ufffe");
        }
    }
}
