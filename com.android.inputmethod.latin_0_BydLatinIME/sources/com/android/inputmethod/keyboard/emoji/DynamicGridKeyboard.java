package com.android.inputmethod.keyboard.emoji;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.latin.settings.Settings;
import com.android.inputmethod.latin.utils.JsonUtils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class DynamicGridKeyboard extends Keyboard {
    private static final String TAG = "DynamicGridKeyboard";
    private static final int TEMPLATE_KEY_CODE_0 = 48;
    private static final int TEMPLATE_KEY_CODE_1 = 49;
    private List<Key> mCachedGridKeys;
    private final int mColumnsNum;
    private final ArrayDeque<GridKey> mGridKeys;
    private final int mHorizontalStep;
    private final boolean mIsRecents;
    private final Object mLock;
    private final int mMaxKeyCount;
    private final ArrayDeque<Key> mPendingKeys;
    private final SharedPreferences mPrefs;
    private final int mVerticalStep;

    public DynamicGridKeyboard(final SharedPreferences prefs, final Keyboard templateKeyboard, final int maxKeyCount, final int categoryId) {
        super(templateKeyboard);
        this.mLock = new Object();
        this.mGridKeys = new ArrayDeque<>();
        this.mPendingKeys = new ArrayDeque<>();
        Key templateKey = getTemplateKey(48);
        int iAbs = Math.abs(getTemplateKey(49).getX() - templateKey.getX());
        this.mHorizontalStep = iAbs;
        this.mVerticalStep = templateKey.getHeight() + this.mVerticalGap;
        this.mColumnsNum = this.mBaseWidth / iAbs;
        this.mMaxKeyCount = maxKeyCount;
        this.mIsRecents = categoryId == 0;
        this.mPrefs = prefs;
    }

    private Key getTemplateKey(final int code) {
        for (Key key : super.getSortedKeys()) {
            if (key.getCode() == code) {
                return key;
            }
        }
        throw new RuntimeException("Can't find template key: code=" + code);
    }

    public void addPendingKey(final Key usedKey) {
        synchronized (this.mLock) {
            this.mPendingKeys.addLast(usedKey);
        }
    }

    public void flushPendingRecentKeys() {
        synchronized (this.mLock) {
            while (!this.mPendingKeys.isEmpty()) {
                addKey(this.mPendingKeys.pollFirst(), true);
            }
            saveRecentKeys();
        }
    }

    public void addKeyFirst(final Key usedKey) {
        addKey(usedKey, true);
        if (this.mIsRecents) {
            saveRecentKeys();
        }
    }

    public void addKeyLast(final Key usedKey) {
        addKey(usedKey, false);
    }

    private void addKey(final Key usedKey, final boolean addFirst) {
        if (usedKey == null) {
            return;
        }
        synchronized (this.mLock) {
            this.mCachedGridKeys = null;
            GridKey gridKey = new GridKey(usedKey);
            while (this.mGridKeys.remove(gridKey)) {
            }
            if (addFirst) {
                this.mGridKeys.addFirst(gridKey);
            } else {
                this.mGridKeys.addLast(gridKey);
            }
            while (this.mGridKeys.size() > this.mMaxKeyCount) {
                this.mGridKeys.removeLast();
            }
            int i = 0;
            Iterator<GridKey> it = this.mGridKeys.iterator();
            while (it.hasNext()) {
                it.next().updateCoordinates(getKeyX0(i), getKeyY0(i), getKeyX1(i), getKeyY1(i));
                i++;
            }
        }
    }

    private void saveRecentKeys() {
        ArrayList arrayList = new ArrayList();
        for (GridKey gridKey : this.mGridKeys) {
            if (gridKey.getOutputText() != null) {
                arrayList.add(gridKey.getOutputText());
            } else {
                arrayList.add(Integer.valueOf(gridKey.getCode()));
            }
        }
        Settings.writeEmojiRecentKeys(this.mPrefs, JsonUtils.listToJsonStr(arrayList));
    }

    private static Key getKeyByCode(final Collection<DynamicGridKeyboard> keyboards, final int code) {
        Iterator<DynamicGridKeyboard> it = keyboards.iterator();
        while (it.hasNext()) {
            for (Key key : it.next().getSortedKeys()) {
                if (key.getCode() == code) {
                    return key;
                }
            }
        }
        return null;
    }

    private static Key getKeyByOutputText(final Collection<DynamicGridKeyboard> keyboards, final String outputText) {
        Iterator<DynamicGridKeyboard> it = keyboards.iterator();
        while (it.hasNext()) {
            for (Key key : it.next().getSortedKeys()) {
                if (outputText.equals(key.getOutputText())) {
                    return key;
                }
            }
        }
        return null;
    }

    public void loadRecentKeys(final Collection<DynamicGridKeyboard> keyboards) {
        Key keyByCode;
        for (Object obj : JsonUtils.jsonStrToList(Settings.readEmojiRecentKeys(this.mPrefs))) {
            if (obj instanceof Integer) {
                keyByCode = getKeyByCode(keyboards, ((Integer) obj).intValue());
            } else if (obj instanceof String) {
                keyByCode = getKeyByOutputText(keyboards, (String) obj);
            } else {
                Log.w(TAG, "Invalid object: " + obj);
            }
            addKeyLast(keyByCode);
        }
    }

    private int getKeyX0(final int index) {
        return (index % this.mColumnsNum) * this.mHorizontalStep;
    }

    private int getKeyX1(final int index) {
        return ((index % this.mColumnsNum) + 1) * this.mHorizontalStep;
    }

    private int getKeyY0(final int index) {
        return ((index / this.mColumnsNum) * this.mVerticalStep) + (this.mVerticalGap / 2);
    }

    private int getKeyY1(final int index) {
        return (((index / this.mColumnsNum) + 1) * this.mVerticalStep) + (this.mVerticalGap / 2);
    }

    @Override // com.android.inputmethod.keyboard.Keyboard
    public List<Key> getSortedKeys() {
        synchronized (this.mLock) {
            List<Key> list = this.mCachedGridKeys;
            if (list != null) {
                return list;
            }
            List<Key> listUnmodifiableList = Collections.unmodifiableList(new ArrayList(this.mGridKeys));
            this.mCachedGridKeys = listUnmodifiableList;
            return listUnmodifiableList;
        }
    }

    @Override // com.android.inputmethod.keyboard.Keyboard
    public List<Key> getNearestKeys(final int x, final int y) {
        return getSortedKeys();
    }

    static final class GridKey extends Key {
        private int mCurrentX;
        private int mCurrentY;

        public GridKey(final Key originalKey) {
            super(originalKey);
        }

        public void updateCoordinates(final int x0, final int y0, final int x1, final int y1) {
            this.mCurrentX = x0;
            this.mCurrentY = y0;
            getHitBox().set(x0, y0, x1, y1);
        }

        @Override // com.android.inputmethod.keyboard.Key
        public int getX() {
            return this.mCurrentX;
        }

        @Override // com.android.inputmethod.keyboard.Key
        public int getY() {
            return this.mCurrentY;
        }

        @Override // com.android.inputmethod.keyboard.Key
        public boolean equals(final Object o) {
            if (!(o instanceof Key)) {
                return false;
            }
            Key key = (Key) o;
            if (getCode() == key.getCode() && TextUtils.equals(getLabel(), key.getLabel())) {
                return TextUtils.equals(getOutputText(), key.getOutputText());
            }
            return false;
        }

        @Override // com.android.inputmethod.keyboard.Key
        public String toString() {
            return "GridKey: " + super.toString();
        }
    }
}
