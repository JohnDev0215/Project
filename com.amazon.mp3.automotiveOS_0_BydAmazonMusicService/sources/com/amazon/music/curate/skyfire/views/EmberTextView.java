package com.amazon.music.curate.skyfire.views;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import com.amazon.music.curate.R;
import com.amazon.music.curate.skyfire.FontFaceUtil;

/* JADX INFO: loaded from: classes4.dex */
public class EmberTextView extends AppCompatTextView {
    private void applyCustomFont(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
    }

    public EmberTextView(final Context context) {
        super(context);
        applyCustomFont(context, null, 0);
    }

    public EmberTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs, 0);
    }

    public EmberTextView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs, defStyleAttr);
    }

    public void applyFontChangesAsPerSizeKey() {
        applyFontChangesAsPerSizeKey(FontFaceUtil.FontSizeKey.DEFAULT);
    }

    /* JADX INFO: renamed from: com.amazon.music.curate.skyfire.views.EmberTextView$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey;

        static {
            int[] iArr = new int[FontFaceUtil.FontSizeKey.values().length];
            $SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey = iArr;
            try {
                iArr[FontFaceUtil.FontSizeKey.HEADLINE_1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey[FontFaceUtil.FontSizeKey.HEADLINE_2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey[FontFaceUtil.FontSizeKey.INDEX.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey[FontFaceUtil.FontSizeKey.HEADLINE_3.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey[FontFaceUtil.FontSizeKey.HEADLINE_4.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public void applyFontChangesAsPerSizeKey(FontFaceUtil.FontSizeKey key) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey[key.ordinal()];
        if (i == 1) {
            setTextSize(0, getResources().getDimension(R.dimen.text_size_38));
            setTypeface(FontFaceUtil.getTypefaceFromKey(key, getContext()));
        } else if (i == 2) {
            setTextSize(0, getResources().getDimension(R.dimen.text_size_32));
            setTypeface(FontFaceUtil.getTypefaceFromKey(key, getContext()));
        } else if (i == 3) {
            setTextSize(0, getResources().getDimension(R.dimen.text_size_12));
            setTypeface(FontFaceUtil.getTypefaceFromKey(key, getContext()));
        } else {
            setTypeface(FontFaceUtil.getTypefaceFromKey(key, getContext()));
        }
    }
}
