package com.android.inputmethod.latin;

import android.content.res.Resources;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import com.android.inputmethod.keyboard.KeyboardSwitcher;
import com.android.inputmethod.latin.settings.Settings;
import com.android.inputmethod.latin.utils.SubtypeLocaleUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
final class EmojiAltPhysicalKeyDetector {
    private static final boolean DEBUG = false;
    private static final String TAG = "EmojiAltPhysicalKeyDetector";
    private List<EmojiHotKeys> mHotKeysList = new ArrayList();

    private static class HotKeySet extends HashSet<Pair<Integer, Integer>> {
        private HotKeySet() {
        }
    }

    private abstract class EmojiHotKeys {
        boolean mCanFire = false;
        private final HotKeySet mKeySet;
        int mMetaState;
        private final String mName;

        protected abstract void action();

        public EmojiHotKeys(final String name, HotKeySet keySet) {
            this.mName = name;
            this.mKeySet = keySet;
        }

        public void onKeyDown(@Nonnull final KeyEvent keyEvent) {
            if (this.mKeySet.contains(Pair.create(Integer.valueOf(keyEvent.getKeyCode()), Integer.valueOf(keyEvent.getMetaState())))) {
                this.mCanFire = true;
                this.mMetaState = keyEvent.getMetaState();
            } else if (this.mCanFire) {
                this.mCanFire = false;
            }
        }

        public void onKeyUp(@Nonnull final KeyEvent keyEvent) {
            int keyCode = keyEvent.getKeyCode();
            int metaState = keyEvent.getMetaState();
            if (KeyEvent.isModifierKey(keyCode)) {
                metaState |= this.mMetaState;
            }
            if (this.mKeySet.contains(Pair.create(Integer.valueOf(keyCode), Integer.valueOf(metaState))) && this.mCanFire) {
                if (!keyEvent.isCanceled()) {
                    action();
                }
                this.mCanFire = false;
            }
            if (this.mCanFire) {
                this.mCanFire = false;
            }
        }
    }

    public EmojiAltPhysicalKeyDetector(@Nonnull final Resources resources) {
        this.mHotKeysList.add(new EmojiHotKeys(SubtypeLocaleUtils.EMOJI, parseHotKeys(resources, R.array.keyboard_switcher_emoji)) { // from class: com.android.inputmethod.latin.EmojiAltPhysicalKeyDetector.1
            @Override // com.android.inputmethod.latin.EmojiAltPhysicalKeyDetector.EmojiHotKeys
            protected void action() {
                KeyboardSwitcher.getInstance().onToggleKeyboard(KeyboardSwitcher.KeyboardSwitchState.EMOJI);
            }
        });
        this.mHotKeysList.add(new EmojiHotKeys("symbols", parseHotKeys(resources, R.array.keyboard_switcher_symbols_shifted)) { // from class: com.android.inputmethod.latin.EmojiAltPhysicalKeyDetector.2
            @Override // com.android.inputmethod.latin.EmojiAltPhysicalKeyDetector.EmojiHotKeys
            protected void action() {
                KeyboardSwitcher.getInstance().onToggleKeyboard(KeyboardSwitcher.KeyboardSwitchState.SYMBOLS_SHIFTED);
            }
        });
    }

    public void onKeyDown(@Nonnull final KeyEvent keyEvent) {
        if (shouldProcessEvent(keyEvent)) {
            Iterator<EmojiHotKeys> it = this.mHotKeysList.iterator();
            while (it.hasNext()) {
                it.next().onKeyDown(keyEvent);
            }
        }
    }

    public void onKeyUp(@Nonnull final KeyEvent keyEvent) {
        if (shouldProcessEvent(keyEvent)) {
            Iterator<EmojiHotKeys> it = this.mHotKeysList.iterator();
            while (it.hasNext()) {
                it.next().onKeyUp(keyEvent);
            }
        }
    }

    private static boolean shouldProcessEvent(@Nonnull final KeyEvent keyEvent) {
        return Settings.getInstance().getCurrent().mEnableEmojiAltPhysicalKey;
    }

    private static HotKeySet parseHotKeys(@Nonnull final Resources resources, final int resourceId) {
        HotKeySet hotKeySet = new HotKeySet();
        String resourceEntryName = resources.getResourceEntryName(resourceId);
        String[] stringArray = resources.getStringArray(resourceId);
        for (int i = 0; stringArray != null && i < stringArray.length; i++) {
            String[] strArrSplit = stringArray[i].split(",");
            if (strArrSplit.length != 2) {
                Log.w(TAG, "Expected 2 integers in " + resourceEntryName + "[" + i + "] : " + stringArray[i]);
            }
            try {
                hotKeySet.add(Pair.create(Integer.valueOf(Integer.parseInt(strArrSplit[0])), Integer.valueOf(KeyEvent.normalizeMetaState(Integer.valueOf(Integer.parseInt(strArrSplit[1])).intValue()))));
            } catch (NumberFormatException e) {
                Log.w(TAG, "Failed to parse " + resourceEntryName + "[" + i + "] : " + stringArray[i], e);
            }
        }
        return hotKeySet;
    }
}
