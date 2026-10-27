package com.android.inputmethod.latin.suggestions;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.inputmethod.accessibility.AccessibilityUtils;
import com.android.inputmethod.latin.PunctuationSuggestions;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.settings.Settings;
import com.android.inputmethod.latin.settings.SettingsValues;
import com.android.inputmethod.latin.utils.ResourceUtils;
import com.android.inputmethod.latin.utils.ViewLayoutUtils;
import java.util.ArrayList;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
final class SuggestionStripLayoutHelper {
    private static final int AUTO_CORRECT_BOLD = 1;
    private static final int AUTO_CORRECT_UNDERLINE = 2;
    private static final float DEFAULT_CENTER_SUGGESTION_PERCENTILE = 0.4f;
    private static final int DEFAULT_MAX_MORE_SUGGESTIONS_ROW = 2;
    private static final int DEFAULT_SUGGESTIONS_COUNT_IN_STRIP = 3;
    private static final float MIN_TEXT_XSCALE = 0.7f;
    private static final String MORE_SUGGESTIONS_HINT = "…";
    private static final int PUNCTUATIONS_IN_STRIP = 5;
    private static final int VALID_TYPED_WORD_BOLD = 4;
    private final float mAlphaObsoleted;
    private final int mCenterPositionInStrip;
    private final float mCenterSuggestionWeight;
    private final int mColorAutoCorrect;
    private final int mColorSuggested;
    private final int mColorTypedWord;
    private final int mColorValidTypedWord;
    private final ArrayList<TextView> mDebugInfoViews;
    private final ArrayList<View> mDividerViews;
    public final int mDividerWidth;
    private int mMaxMoreSuggestionsRow;
    public final float mMinMoreSuggestionsWidth;
    private boolean mMoreSuggestionsAvailable;
    public final int mMoreSuggestionsBottomGap;
    private final Drawable mMoreSuggestionsHint;
    public final int mMoreSuggestionsRowHeight;
    public final int mPadding;
    private final int mSuggestionStripOptions;
    private final int mSuggestionsCountInStrip;
    public final int mSuggestionsStripHeight;
    private final int mTypedWordPositionWhenAutocorrect;
    private final ArrayList<TextView> mWordViews;
    private static final CharacterStyle BOLD_SPAN = new StyleSpan(1);
    private static final CharacterStyle UNDERLINE_SPAN = new UnderlineSpan();

    static boolean shouldOmitTypedWord(final int inputStyle, final boolean gestureFloatingPreviewTextEnabled, final boolean shouldShowUiToAcceptTypedWord) {
        return shouldShowUiToAcceptTypedWord && (inputStyle == 1 || inputStyle == 3 || (inputStyle == 2 && gestureFloatingPreviewTextEnabled));
    }

    public SuggestionStripLayoutHelper(final Context context, final AttributeSet attrs, final int defStyle, final ArrayList<TextView> wordViews, final ArrayList<View> dividerViews, final ArrayList<TextView> debugInfoViews) {
        this.mWordViews = wordViews;
        this.mDividerViews = dividerViews;
        this.mDebugInfoViews = debugInfoViews;
        TextView textView = wordViews.get(0);
        View view = dividerViews.get(0);
        this.mPadding = textView.getCompoundPaddingLeft() + textView.getCompoundPaddingRight();
        view.measure(-1, -1);
        this.mDividerWidth = view.getMeasuredWidth();
        Resources resources = textView.getResources();
        this.mSuggestionsStripHeight = resources.getDimensionPixelSize(R.dimen.config_suggestions_strip_height);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.SuggestionStripView, defStyle, R.style.SuggestionStripView);
        this.mSuggestionStripOptions = typedArrayObtainStyledAttributes.getInt(8, 0);
        this.mAlphaObsoleted = ResourceUtils.getFraction(typedArrayObtainStyledAttributes, 0, 1.0f);
        this.mColorValidTypedWord = typedArrayObtainStyledAttributes.getColor(5, 0);
        this.mColorTypedWord = typedArrayObtainStyledAttributes.getColor(4, 0);
        int color = typedArrayObtainStyledAttributes.getColor(2, 0);
        this.mColorAutoCorrect = color;
        this.mColorSuggested = typedArrayObtainStyledAttributes.getColor(3, 0);
        int i = typedArrayObtainStyledAttributes.getInt(9, 3);
        this.mSuggestionsCountInStrip = i;
        this.mCenterSuggestionWeight = ResourceUtils.getFraction(typedArrayObtainStyledAttributes, 1, DEFAULT_CENTER_SUGGESTION_PERCENTILE);
        this.mMaxMoreSuggestionsRow = typedArrayObtainStyledAttributes.getInt(6, 2);
        this.mMinMoreSuggestionsWidth = ResourceUtils.getFraction(typedArrayObtainStyledAttributes, 7, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        this.mMoreSuggestionsHint = getMoreSuggestionsHint(resources, resources.getDimension(R.dimen.config_more_suggestions_hint_text_size), color);
        int i2 = i / 2;
        this.mCenterPositionInStrip = i2;
        this.mTypedWordPositionWhenAutocorrect = i2 - 1;
        this.mMoreSuggestionsBottomGap = resources.getDimensionPixelOffset(R.dimen.config_more_suggestions_bottom_gap);
        this.mMoreSuggestionsRowHeight = resources.getDimensionPixelSize(R.dimen.config_more_suggestions_row_height);
    }

    public int getMaxMoreSuggestionsRow() {
        return this.mMaxMoreSuggestionsRow;
    }

    private int getMoreSuggestionsHeight() {
        return (this.mMaxMoreSuggestionsRow * this.mMoreSuggestionsRowHeight) + this.mMoreSuggestionsBottomGap;
    }

    public void setMoreSuggestionsHeight(final int remainingHeight) {
        if (getMoreSuggestionsHeight() <= remainingHeight) {
            return;
        }
        this.mMaxMoreSuggestionsRow = (remainingHeight - this.mMoreSuggestionsBottomGap) / this.mMoreSuggestionsRowHeight;
    }

    private static Drawable getMoreSuggestionsHint(final Resources res, final float textSize, final int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textSize);
        paint.setColor(color);
        paint.getTextBounds(MORE_SUGGESTIONS_HINT, 0, 1, new Rect());
        int iRound = Math.round(r5.width() + 0.5f);
        int iRound2 = Math.round(r5.height() + 0.5f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iRound, (iRound2 * 3) / 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawText(MORE_SUGGESTIONS_HINT, iRound / 2, iRound2, paint);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(res, bitmapCreateBitmap);
        bitmapDrawable.setTargetDensity(canvas);
        return bitmapDrawable;
    }

    private CharSequence getStyledSuggestedWord(final SuggestedWords suggestedWords, final int indexInSuggestedWords) {
        if (indexInSuggestedWords >= suggestedWords.size()) {
            return null;
        }
        String label = suggestedWords.getLabel(indexInSuggestedWords);
        boolean z = false;
        boolean z2 = suggestedWords.mWillAutoCorrect && indexInSuggestedWords == 1;
        if (suggestedWords.mTypedWordValid && indexInSuggestedWords == 0) {
            z = true;
        }
        if (!z2 && !z) {
            return label;
        }
        SpannableString spannableString = new SpannableString(label);
        int i = this.mSuggestionStripOptions;
        if ((z2 && (i & 1) != 0) || (z && (i & 4) != 0)) {
            addStyleSpan(spannableString, BOLD_SPAN);
        }
        if (z2 && (i & 2) != 0) {
            addStyleSpan(spannableString, UNDERLINE_SPAN);
        }
        return spannableString;
    }

    private int getPositionInSuggestionStrip(final int indexInSuggestedWords, final SuggestedWords suggestedWords) {
        SettingsValues current = Settings.getInstance().getCurrent();
        return getPositionInSuggestionStrip(indexInSuggestedWords, suggestedWords.mWillAutoCorrect, current.mShouldShowLxxSuggestionUi && shouldOmitTypedWord(suggestedWords.mInputStyle, current.mGestureFloatingPreviewTextEnabled, current.mShouldShowLxxSuggestionUi), this.mCenterPositionInStrip, this.mTypedWordPositionWhenAutocorrect);
    }

    static int getPositionInSuggestionStrip(final int indexInSuggestedWords, final boolean willAutoCorrect, final boolean omitTypedWord, final int centerPositionInStrip, final int typedWordPositionWhenAutoCorrect) {
        int i;
        if (omitTypedWord) {
            if (indexInSuggestedWords == 0) {
                return -1;
            }
            if (indexInSuggestedWords == 1) {
                return centerPositionInStrip;
            }
            int i2 = indexInSuggestedWords % 2;
            int i3 = indexInSuggestedWords / 2;
            if (i2 == 0) {
                i3 = -i3;
            }
            return centerPositionInStrip + i3;
        }
        int i4 = 0;
        if (willAutoCorrect) {
            i = 0;
            i4 = 1;
        } else {
            i = 1;
        }
        if (indexInSuggestedWords == i4) {
            return centerPositionInStrip;
        }
        if (indexInSuggestedWords == i) {
            return typedWordPositionWhenAutoCorrect;
        }
        int i5 = indexInSuggestedWords + 1;
        int i6 = i5 % 2;
        int i7 = i5 / 2;
        if (i6 == 0) {
            i7 = -i7;
        }
        return centerPositionInStrip + i7;
    }

    private int getSuggestionTextColor(final SuggestedWords suggestedWords, final int indexInSuggestedWords) {
        int i;
        boolean zIsKindOf = suggestedWords.getInfo(indexInSuggestedWords).isKindOf(0);
        if (indexInSuggestedWords == 1 && suggestedWords.mWillAutoCorrect) {
            i = this.mColorAutoCorrect;
        } else if (zIsKindOf && suggestedWords.mTypedWordValid) {
            i = this.mColorValidTypedWord;
        } else if (zIsKindOf) {
            i = this.mColorTypedWord;
        } else {
            i = this.mColorSuggested;
        }
        return (!suggestedWords.mIsObsoleteSuggestions || zIsKindOf) ? i : applyAlpha(i, this.mAlphaObsoleted);
    }

    private static int applyAlpha(final int color, final float alpha) {
        return Color.argb((int) (Color.alpha(color) * alpha), Color.red(color), Color.green(color), Color.blue(color));
    }

    private static void addDivider(final ViewGroup stripView, final View dividerView) {
        stripView.addView(dividerView);
        ((LinearLayout.LayoutParams) dividerView.getLayoutParams()).gravity = 17;
    }

    public int layoutAndReturnStartIndexOfMoreSuggestions(final Context context, final SuggestedWords suggestedWords, final ViewGroup stripView, final ViewGroup placerView) {
        if (suggestedWords.isPunctuationSuggestions()) {
            return layoutPunctuationsAndReturnStartIndexOfMoreSuggestions((PunctuationSuggestions) suggestedWords, stripView);
        }
        int wordCountToShow = suggestedWords.getWordCountToShow(Settings.getInstance().getCurrent().mShouldShowLxxSuggestionUi);
        int i = setupWordViewsAndReturnStartIndexOfMoreSuggestions(suggestedWords, this.mSuggestionsCountInStrip);
        TextView textView = this.mWordViews.get(this.mCenterPositionInStrip);
        int width = stripView.getWidth();
        int suggestionWidth = getSuggestionWidth(this.mCenterPositionInStrip, width);
        if (wordCountToShow == 1 || getTextScaleX(textView.getText(), suggestionWidth, textView.getPaint()) < MIN_TEXT_XSCALE) {
            this.mMoreSuggestionsAvailable = wordCountToShow > 1;
            layoutWord(context, this.mCenterPositionInStrip, width - this.mPadding);
            stripView.addView(textView);
            setLayoutWeight(textView, 1.0f, -1);
            Integer num = (Integer) textView.getTag();
            return (num != null ? num.intValue() : 0) + 1;
        }
        int i2 = this.mSuggestionsCountInStrip;
        this.mMoreSuggestionsAvailable = wordCountToShow > i2;
        while (i < i2) {
            if (i != 0) {
                View view = this.mDividerViews.get(i);
                addDivider(stripView, view);
                view.getMeasuredWidth();
            }
            TextView textViewLayoutWord = layoutWord(context, i, getSuggestionWidth(i, width));
            stripView.addView(textViewLayoutWord);
            setLayoutWeight(textViewLayoutWord, getSuggestionWeight(i), -1);
            textViewLayoutWord.getMeasuredWidth();
            i++;
        }
        return i;
    }

    private TextView layoutWord(final Context context, final int positionInStrip, final int width) {
        String string;
        TextView textView = this.mWordViews.get(positionInStrip);
        CharSequence text = textView.getText();
        if (positionInStrip == this.mCenterPositionInStrip && this.mMoreSuggestionsAvailable) {
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, this.mMoreSuggestionsHint);
            textView.setCompoundDrawablePadding(-this.mMoreSuggestionsHint.getIntrinsicHeight());
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        }
        if (TextUtils.isEmpty(text)) {
            string = context.getResources().getString(R.string.spoken_empty_suggestion);
        } else {
            string = text.toString();
        }
        textView.setContentDescription(string);
        CharSequence ellipsizedTextWithSettingScaleX = getEllipsizedTextWithSettingScaleX(text, width, textView.getPaint());
        float textScaleX = textView.getTextScaleX();
        textView.setText(ellipsizedTextWithSettingScaleX);
        textView.setTextScaleX(textScaleX);
        textView.setEnabled(!TextUtils.isEmpty(text) || AccessibilityUtils.getInstance().isTouchExplorationEnabled());
        return textView;
    }

    private void layoutDebugInfo(final int positionInStrip, final ViewGroup placerView, final int x) {
        TextView textView = this.mDebugInfoViews.get(positionInStrip);
        if (textView.getText() == null) {
            return;
        }
        placerView.addView(textView);
        textView.measure(-2, -2);
        int measuredWidth = textView.getMeasuredWidth();
        ViewLayoutUtils.placeViewAt(textView, x - measuredWidth, textView.getMeasuredHeight(), measuredWidth, textView.getMeasuredHeight());
    }

    private int getSuggestionWidth(final int positionInStrip, final int maxWidth) {
        int i = this.mPadding;
        int i2 = this.mSuggestionsCountInStrip;
        return (int) (((maxWidth - (i * i2)) - (this.mDividerWidth * (i2 - 1))) * getSuggestionWeight(positionInStrip));
    }

    private float getSuggestionWeight(final int positionInStrip) {
        if (positionInStrip == this.mCenterPositionInStrip) {
            return this.mCenterSuggestionWeight;
        }
        return (1.0f - this.mCenterSuggestionWeight) / (this.mSuggestionsCountInStrip - 1);
    }

    private int setupWordViewsAndReturnStartIndexOfMoreSuggestions(final SuggestedWords suggestedWords, final int maxSuggestionInStrip) {
        int i = 0;
        for (int i2 = 0; i2 < maxSuggestionInStrip; i2++) {
            TextView textView = this.mWordViews.get(i2);
            textView.setText((CharSequence) null);
            textView.setTag(null);
        }
        int i3 = 0;
        while (i < suggestedWords.size() && i3 < maxSuggestionInStrip) {
            int positionInSuggestionStrip = getPositionInSuggestionStrip(i, suggestedWords);
            if (positionInSuggestionStrip >= 0) {
                TextView textView2 = this.mWordViews.get(positionInSuggestionStrip);
                textView2.setTag(Integer.valueOf(i));
                textView2.setText(getStyledSuggestedWord(suggestedWords, i));
                textView2.setTextColor(getSuggestionTextColor(suggestedWords, i));
                i3++;
            }
            i++;
        }
        return i;
    }

    private int layoutPunctuationsAndReturnStartIndexOfMoreSuggestions(final PunctuationSuggestions punctuationSuggestions, final ViewGroup stripView) {
        int iMin = Math.min(punctuationSuggestions.size(), 5);
        for (int i = 0; i < iMin; i++) {
            if (i != 0) {
                addDivider(stripView, this.mDividerViews.get(i));
            }
            TextView textView = this.mWordViews.get(i);
            String label = punctuationSuggestions.getLabel(i);
            textView.setTag(Integer.valueOf(i));
            textView.setText(label);
            textView.setContentDescription(label);
            textView.setTextScaleX(1.0f);
            textView.setCompoundDrawables(null, null, null, null);
            textView.setTextColor(this.mColorAutoCorrect);
            stripView.addView(textView);
            setLayoutWeight(textView, 1.0f, this.mSuggestionsStripHeight);
        }
        this.mMoreSuggestionsAvailable = punctuationSuggestions.size() > iMin;
        return iMin;
    }

    public void layoutImportantNotice(final View importantNoticeStrip, final String importantNoticeTitle) {
        TextView textView = (TextView) importantNoticeStrip.findViewById(R.id.important_notice_title);
        int width = (textView.getWidth() - textView.getPaddingLeft()) - textView.getPaddingRight();
        textView.setTextColor(this.mColorAutoCorrect);
        textView.setText(importantNoticeTitle);
        textView.setTextScaleX(getTextScaleX(importantNoticeTitle, width, textView.getPaint()));
    }

    static void setLayoutWeight(final View v, final float weight, final int height) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.weight = weight;
            layoutParams2.width = 0;
            layoutParams2.height = height;
        }
    }

    private static float getTextScaleX(@Nullable final CharSequence text, final int maxWidth, final TextPaint paint) {
        paint.setTextScaleX(1.0f);
        int textWidth = getTextWidth(text, paint);
        if (textWidth <= maxWidth || maxWidth <= 0) {
            return 1.0f;
        }
        return maxWidth / textWidth;
    }

    @Nullable
    private static CharSequence getEllipsizedTextWithSettingScaleX(@Nullable final CharSequence text, final int maxWidth, @Nonnull final TextPaint paint) {
        if (text == null) {
            return null;
        }
        float textScaleX = getTextScaleX(text, maxWidth, paint);
        if (textScaleX >= MIN_TEXT_XSCALE) {
            paint.setTextScaleX(textScaleX);
            return text;
        }
        paint.setTextScaleX(MIN_TEXT_XSCALE);
        CharacterStyle characterStyle = BOLD_SPAN;
        boolean zHasStyleSpan = hasStyleSpan(text, characterStyle);
        CharacterStyle characterStyle2 = UNDERLINE_SPAN;
        boolean zHasStyleSpan2 = hasStyleSpan(text, characterStyle2);
        CharSequence charSequenceEllipsize = TextUtils.ellipsize(text, paint, maxWidth, TextUtils.TruncateAt.MIDDLE);
        if (!zHasStyleSpan && !zHasStyleSpan2) {
            return charSequenceEllipsize;
        }
        Spannable spannableString = charSequenceEllipsize instanceof Spannable ? (Spannable) charSequenceEllipsize : new SpannableString(charSequenceEllipsize);
        if (zHasStyleSpan) {
            addStyleSpan(spannableString, characterStyle);
        }
        if (zHasStyleSpan2) {
            addStyleSpan(spannableString, characterStyle2);
        }
        return spannableString;
    }

    private static boolean hasStyleSpan(@Nullable final CharSequence text, final CharacterStyle style) {
        return (text instanceof Spanned) && ((Spanned) text).getSpanStart(style) >= 0;
    }

    private static void addStyleSpan(@Nonnull final Spannable text, final CharacterStyle style) {
        text.removeSpan(style);
        text.setSpan(style, 0, text.length(), 17);
    }

    private static int getTextWidth(@Nullable final CharSequence text, final TextPaint paint) {
        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        int length = text.length();
        float[] fArr = new float[length];
        Typeface typeface = paint.getTypeface();
        try {
            paint.setTypeface(getTextTypeface(text));
            int textWidths = paint.getTextWidths(text, 0, length, fArr);
            paint.setTypeface(typeface);
            int iRound = 0;
            for (int i = 0; i < textWidths; i++) {
                iRound += Math.round(fArr[i] + 0.5f);
            }
            return iRound;
        } catch (Throwable th) {
            paint.setTypeface(typeface);
            throw th;
        }
    }

    private static Typeface getTextTypeface(@Nullable final CharSequence text) {
        return hasStyleSpan(text, BOLD_SPAN) ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT;
    }
}
