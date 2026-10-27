package com.android.providers.contacts;

import android.icu.text.Transliterator;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class HanziToPinyin {
    private static HanziToPinyin sInstance;
    private Transliterator mAsciiTransliterator;
    private Transliterator mPinyinTransliterator;

    public static class Token {
        public String source;
        public String target;
        public int type;

        public Token() {
        }

        public Token(int i, String str, String str2) {
            this.type = i;
            this.source = str;
            this.target = str2;
        }
    }

    private HanziToPinyin() {
        try {
            this.mPinyinTransliterator = Transliterator.getInstance("Han-Latin/Names; Latin-Ascii; Any-Upper");
            this.mAsciiTransliterator = Transliterator.getInstance("Latin-Ascii");
        } catch (IllegalArgumentException unused) {
            Log.w("HanziToPinyin", "Han-Latin/Names transliterator data is missing, HanziToPinyin is disabled");
        }
    }

    public boolean hasChineseTransliterator() {
        return this.mPinyinTransliterator != null;
    }

    public static HanziToPinyin getInstance() {
        HanziToPinyin hanziToPinyin;
        synchronized (HanziToPinyin.class) {
            if (sInstance == null) {
                sInstance = new HanziToPinyin();
            }
            hanziToPinyin = sInstance;
        }
        return hanziToPinyin;
    }

    private void tokenize(char c, Token token) {
        String string = Character.toString(c);
        token.source = string;
        if (c < 128) {
            token.type = 1;
            token.target = string;
            return;
        }
        if (c < 592 || (7680 <= c && c < 7935)) {
            token.type = 1;
            Transliterator transliterator = this.mAsciiTransliterator;
            token.target = transliterator == null ? token.source : transliterator.transliterate(token.source);
            return;
        }
        token.type = 2;
        String strTransliterate = this.mPinyinTransliterator.transliterate(token.source);
        token.target = strTransliterate;
        if (TextUtils.isEmpty(strTransliterate) || TextUtils.equals(token.source, token.target)) {
            token.type = 3;
            token.target = token.source;
        }
    }

    public String transliterate(String str) {
        if (!hasChineseTransliterator() || TextUtils.isEmpty(str)) {
            return null;
        }
        return this.mPinyinTransliterator.transliterate(str);
    }

    public ArrayList<Token> getTokens(String str) {
        ArrayList<Token> arrayList = new ArrayList<>();
        if (hasChineseTransliterator() && !TextUtils.isEmpty(str)) {
            int length = str.length();
            StringBuilder sb = new StringBuilder();
            Token token = new Token();
            int i = 1;
            for (int i2 = 0; i2 < length; i2++) {
                char cCharAt = str.charAt(i2);
                if (Character.isSpaceChar(cCharAt)) {
                    if (sb.length() > 0) {
                        addToken(sb, arrayList, i);
                    }
                } else {
                    tokenize(cCharAt, token);
                    int i3 = token.type;
                    if (i3 == 2) {
                        if (sb.length() > 0) {
                            addToken(sb, arrayList, i);
                        }
                        arrayList.add(token);
                        token = new Token();
                    } else {
                        if (i != i3 && sb.length() > 0) {
                            addToken(sb, arrayList, i);
                        }
                        sb.append(token.target);
                    }
                    i = token.type;
                }
            }
            if (sb.length() > 0) {
                addToken(sb, arrayList, i);
            }
        }
        return arrayList;
    }

    private void addToken(StringBuilder sb, ArrayList<Token> arrayList, int i) {
        String string = sb.toString();
        arrayList.add(new Token(i, string, string));
        sb.setLength(0);
    }
}
