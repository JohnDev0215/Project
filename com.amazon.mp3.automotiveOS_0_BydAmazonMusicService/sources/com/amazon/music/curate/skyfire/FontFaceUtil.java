package com.amazon.music.curate.skyfire;

import android.content.Context;
import android.graphics.Typeface;
import com.amazon.music.curate.R;

/* JADX INFO: loaded from: classes4.dex */
public class FontFaceUtil {
    private static Typeface emberBoldSR;
    private static Typeface groteskBoldSR;
    private static Typeface groteskSemiBoldSR;

    public enum FontSizeKey {
        DEFAULT,
        HEADLINE_1,
        HEADLINE_2,
        HEADLINE_3,
        HEADLINE_4,
        INDEX
    }

    /* JADX INFO: renamed from: com.amazon.music.curate.skyfire.FontFaceUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey;

        static {
            int[] iArr = new int[FontSizeKey.values().length];
            $SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey = iArr;
            try {
                iArr[FontSizeKey.HEADLINE_1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey[FontSizeKey.HEADLINE_2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey[FontSizeKey.INDEX.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey[FontSizeKey.HEADLINE_3.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey[FontSizeKey.HEADLINE_4.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static Typeface getTypefaceFromKey(FontSizeKey key, Context context) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$curate$skyfire$FontFaceUtil$FontSizeKey[key.ordinal()];
        if (i == 1 || i == 2) {
            if (groteskBoldSR == null) {
                groteskBoldSR = Typeface.createFromAsset(context.getResources().getAssets(), context.getResources().getString(R.string.font_sharp_grotesk_bold));
            }
            return groteskBoldSR;
        }
        if (i == 3) {
            if (emberBoldSR == null) {
                emberBoldSR = Typeface.createFromAsset(context.getResources().getAssets(), context.getResources().getString(R.string.font_amazon_ember_bold));
            }
            return emberBoldSR;
        }
        if (groteskSemiBoldSR == null) {
            groteskSemiBoldSR = Typeface.createFromAsset(context.getResources().getAssets(), context.getResources().getString(R.string.font_sharp_grotesk_semi_bold));
        }
        return groteskSemiBoldSR;
    }
}
