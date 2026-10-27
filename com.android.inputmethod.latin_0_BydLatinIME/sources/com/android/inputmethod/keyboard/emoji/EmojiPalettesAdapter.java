package com.android.inputmethod.keyboard.emoji;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.emoji.EmojiPageKeyboardView;
import com.android.inputmethod.latin.R;

/* JADX INFO: loaded from: classes2.dex */
final class EmojiPalettesAdapter extends PagerAdapter {
    private static final boolean DEBUG_PAGER = false;
    private static final String TAG = "EmojiPalettesAdapter";
    private final SparseArray<EmojiPageKeyboardView> mActiveKeyboardViews = new SparseArray<>();
    private int mActivePosition = 0;
    private final EmojiCategory mEmojiCategory;
    private final EmojiPageKeyboardView.OnKeyEventListener mListener;
    private final DynamicGridKeyboard mRecentsKeyboard;

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(final View view, final Object object) {
        return view == object;
    }

    public EmojiPalettesAdapter(final EmojiCategory emojiCategory, final EmojiPageKeyboardView.OnKeyEventListener listener) {
        this.mEmojiCategory = emojiCategory;
        this.mListener = listener;
        this.mRecentsKeyboard = emojiCategory.getKeyboard(0, 0);
    }

    public void flushPendingRecentKeys() {
        this.mRecentsKeyboard.flushPendingRecentKeys();
        EmojiPageKeyboardView emojiPageKeyboardView = this.mActiveKeyboardViews.get(this.mEmojiCategory.getRecentTabId());
        if (emojiPageKeyboardView != null) {
            emojiPageKeyboardView.invalidateAllKeys();
        }
    }

    public void addRecentKey(final Key key) {
        if (this.mEmojiCategory.isInRecentTab()) {
            this.mRecentsKeyboard.addPendingKey(key);
            return;
        }
        this.mRecentsKeyboard.addKeyFirst(key);
        EmojiPageKeyboardView emojiPageKeyboardView = this.mActiveKeyboardViews.get(this.mEmojiCategory.getRecentTabId());
        if (emojiPageKeyboardView != null) {
            emojiPageKeyboardView.invalidateAllKeys();
        }
    }

    public void onPageScrolled() {
        releaseCurrentKey(false);
    }

    public void releaseCurrentKey(final boolean withKeyRegistering) {
        EmojiPageKeyboardView emojiPageKeyboardView = this.mActiveKeyboardViews.get(this.mActivePosition);
        if (emojiPageKeyboardView == null) {
            return;
        }
        emojiPageKeyboardView.releaseCurrentKey(withKeyRegistering);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mEmojiCategory.getTotalPageCountOfAllCategories();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(final ViewGroup container, final int position, final Object object) {
        int i = this.mActivePosition;
        if (i == position) {
            return;
        }
        EmojiPageKeyboardView emojiPageKeyboardView = this.mActiveKeyboardViews.get(i);
        if (emojiPageKeyboardView != null) {
            emojiPageKeyboardView.releaseCurrentKey(false);
            emojiPageKeyboardView.deallocateMemory();
        }
        this.mActivePosition = position;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(final ViewGroup container, final int position) {
        EmojiPageKeyboardView emojiPageKeyboardView = this.mActiveKeyboardViews.get(position);
        if (emojiPageKeyboardView != null) {
            emojiPageKeyboardView.deallocateMemory();
            this.mActiveKeyboardViews.remove(position);
        }
        DynamicGridKeyboard keyboardFromPagePosition = this.mEmojiCategory.getKeyboardFromPagePosition(position);
        EmojiPageKeyboardView emojiPageKeyboardView2 = (EmojiPageKeyboardView) LayoutInflater.from(container.getContext()).inflate(R.layout.emoji_keyboard_page, container, false);
        emojiPageKeyboardView2.setKeyboard(keyboardFromPagePosition);
        emojiPageKeyboardView2.setOnKeyEventListener(this.mListener);
        container.addView(emojiPageKeyboardView2);
        this.mActiveKeyboardViews.put(position, emojiPageKeyboardView2);
        return emojiPageKeyboardView2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        EmojiPageKeyboardView emojiPageKeyboardView = this.mActiveKeyboardViews.get(position);
        if (emojiPageKeyboardView != null) {
            emojiPageKeyboardView.deallocateMemory();
            this.mActiveKeyboardViews.remove(position);
        }
        if (object instanceof View) {
            container.removeView((View) object);
        } else {
            Log.w(TAG, "Warning!!! Emoji palette may be leaking. " + object);
        }
    }
}
