package com.byd.vrassistant.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes2.dex */
public class TextViewUtils {
    public static Typeface DEFAULT = Typeface.DEFAULT;
    private static final String TAG = "NEU_TextViewUtils";
    private static TextViewUtils sSingleton;
    volatile Field field;
    private Map<String, SoftReference<Typeface>> mCache = new HashMap();

    private TextViewUtils() {
    }

    public static TextViewUtils getInstance() {
        if (sSingleton == null) {
            synchronized (TextViewUtils.class) {
                if (sSingleton == null) {
                    sSingleton = new TextViewUtils();
                }
            }
        }
        return sSingleton;
    }

    public void updateLocale(View view, Locale locale) {
        if (view == null || locale == null) {
            return;
        }
        Resources resources = view.getContext().getResources();
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setTextLocale(locale);
            int iIntValue = getPreviousId(textView).intValue();
            if (iIntValue != 0) {
                Log.i(TAG, "updateLocale: " + ((Object) resources.getText(iIntValue)));
                textView.setText(iIntValue);
                return;
            }
            return;
        }
        if (view instanceof ViewGroup) {
            Log.i(TAG, "updateLocale: " + locale + "," + view.getClass().getSimpleName());
            resources.getConfiguration().setLocale(locale);
            view.getContext().getResources().updateConfiguration(resources.getConfiguration(), resources.getDisplayMetrics());
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                updateLocale(viewGroup.getChildAt(i), locale);
            }
        }
    }

    private Integer getPreviousId(TextView textView) {
        try {
            return (Integer) getField(textView).get(textView);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "getPreviousId: ", e);
            return 0;
        }
    }

    private Field getField(TextView textView) {
        if (this.field == null) {
            synchronized (TextViewUtils.class) {
                if (this.field == null) {
                    try {
                        for (Class<?> superclass = textView.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
                            if (superclass == null) {
                                Log.e(TAG, "getField: ", new RuntimeException("className is null"));
                                return null;
                            }
                            if (Arrays.stream(superclass.getDeclaredFields()).anyMatch(new Predicate() { // from class: com.byd.vrassistant.utils.TextViewUtils$$ExternalSyntheticLambda0
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    return ((Field) obj).getName().equals("mTextId");
                                }
                            })) {
                                this.field = superclass.getDeclaredField("mTextId");
                                this.field.setAccessible(true);
                            }
                        }
                    } catch (NoSuchFieldException | NullPointerException e) {
                        Log.e(TAG, "getField: ", e);
                    }
                }
            }
        }
        return this.field;
    }

    public void replaceFontFromAsset(View view, String str) {
        replaceFont(view, createTypefaceFromAsset(view.getContext(), str));
    }

    public void replaceFontFromAsset(View view, String str, int i) {
        replaceFont(view, createTypefaceFromAsset(view.getContext(), str), i);
    }

    public void replaceFontFromFile(View view, String str) {
        replaceFont(view, createTypefaceFromFile(str));
    }

    public void replaceFontFromFile(View view, String str, int i) {
        replaceFont(view, createTypefaceFromFile(str), i);
    }

    private void replaceFont(View view, Typeface typeface) {
        if (view == null || typeface == null) {
            return;
        }
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setTypeface(typeface, textView.getTypeface() != null ? textView.getTypeface().getStyle() : 0);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            while (i < viewGroup.getChildCount()) {
                replaceFont(viewGroup.getChildAt(i), typeface);
                i++;
            }
        }
    }

    private void replaceFont(View view, Typeface typeface, int i) {
        if (view == null || typeface == null) {
            return;
        }
        if (i < 0 || i > 3) {
            i = 0;
        }
        if (view instanceof TextView) {
            ((TextView) view).setTypeface(typeface, i);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                replaceFont(viewGroup.getChildAt(i2), typeface, i);
            }
        }
    }

    private Typeface createTypefaceFromAsset(Context context, String str) {
        Typeface typeface;
        SoftReference<Typeface> softReference = this.mCache.get(str);
        if (softReference != null && (typeface = softReference.get()) != null) {
            return typeface;
        }
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(context.getAssets(), str);
        this.mCache.put(str, new SoftReference<>(typefaceCreateFromAsset));
        return typefaceCreateFromAsset;
    }

    private Typeface createTypefaceFromFile(String str) {
        Typeface typeface;
        SoftReference<Typeface> softReference = this.mCache.get(str);
        if (softReference != null && (typeface = softReference.get()) != null) {
            return typeface;
        }
        Typeface typefaceCreateFromFile = Typeface.createFromFile(str);
        this.mCache.put(str, new SoftReference<>(typefaceCreateFromFile));
        return typefaceCreateFromFile;
    }

    public void replaceSystemDefaultFontFromAsset(Context context, String str) {
        replaceSystemDefaultFont(createTypefaceFromAsset(context, str));
    }

    public void replaceSystemDefaultFontFromFile(Context context, String str) {
        replaceSystemDefaultFont(createTypefaceFromFile(str));
    }

    private void replaceSystemDefaultFont(Typeface typeface) {
        modifyObjectField(null, "MONOSPACE", typeface);
    }

    private void modifyObjectField(Object obj, String str, Object obj2) {
        try {
            Field declaredField = Typeface.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Log.e(TAG, "modifyObjectField: ", e);
        }
    }
}
