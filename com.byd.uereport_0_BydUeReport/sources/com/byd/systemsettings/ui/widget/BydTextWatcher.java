package com.byd.systemsettings.ui.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import java.lang.ref.WeakReference;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class BydTextWatcher implements TextWatcher {
    public static final String REGEX_DB = "[^A-Za-z0-9_ ]";
    public static final String REGEX_DEF = "[^A-Za-z0-9_]";
    public static final String REGEX_DIGITS = "[^0-9]";
    public static final String REGEX_NAME = "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"])$";
    public static final String REGEX_NAME_AR = "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"\\u0600-\\u06FF\\u0750-\\u077F])$";
    public static final String REGEX_NAME_IW = "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"\\u0590-\\u05FF])$";
    public static final String REGEX_NAME_KO = "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"\\u1100-\\u11FF\\u3130-\\u318F\\uAC00-\\uD7FF])$";
    public static final String REGEX_NAME_TH = "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"\\p{InThai}])$";
    public static final String REGEX_NAME_ZH_HMT = "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"\\u4E00-\\u9FFF])$";
    public static final String REGEX_PWD = "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"])$";
    public static final String REGEX_PWD_2 = "^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?![,\\.#%'\\+\\*\\-:;^_`]+$)[,\\.#%'\\+\\*\\-:;^_`?0-9A-Za-z]{8,64}$";
    public static final String REGEX_SEARCH = "[%|,&_<=>，]";
    public static final String SPEC_CHARACTERS = " !\"#$%&'()*+,-./:;<=>?@\\]\\[^_`{|}~：；！？、。，¥€˚";
    private static final String TAG = "BydTextWatcher";
    public static final String character = "[a-zA-Z]+$";
    public static final String ncw = "\\w+$";
    public static final String number_and_character = "((^[a-zA-Z]+[0-9]+[a-zA-Z0-9]*)+)|((^[0-9]+[a-zA-Z]+[a-zA-Z0-9]*)+)$";
    public static final String numberic = "[0-9]+$";
    private boolean checkASCII;
    private boolean checkEmoji;
    public volatile String curInput;
    private final WeakReference<EditText> etRef;
    public String regex;
    public String specialText;

    public BydTextWatcher(EditText editText, String str) {
        this.regex = null;
        this.specialText = null;
        this.checkEmoji = false;
        this.checkASCII = false;
        this.curInput = null;
        this.etRef = new WeakReference<>(editText);
        this.regex = str;
    }

    public static boolean checkPassword(String str) {
        if (str.length() < 8 || str.length() > 64) {
            return false;
        }
        boolean z7 = false;
        for (char c8 : str.toCharArray()) {
            if (SPEC_CHARACTERS.contains(String.valueOf(c8))) {
                str = str.replace(c8, ' ');
                z7 = true;
            }
        }
        String strReplace = str.replace(" ", "");
        boolean zMatches = Pattern.compile(numberic).matcher(strReplace).matches();
        boolean zMatches2 = Pattern.compile(character).matcher(strReplace).matches();
        boolean zMatches3 = Pattern.compile(number_and_character).matcher(strReplace).matches();
        return (zMatches && z7) || (zMatches2 && z7) || ((zMatches3 && z7) || zMatches3);
    }

    private boolean containsNotAscii(String str) {
        if (StandardCharsets.US_ASCII.newEncoder().canEncode(str)) {
            return false;
        }
        a.e(TAG, "containsNotAscii", "passphrase not ASCII encodable.");
        return true;
    }

    public static String getNameRegex(Context context) {
        if (context == null) {
            return "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"])$";
        }
        Locale locale = context.getResources().getConfiguration().getLocales().get(0);
        a.a(TAG, "getNameRegex---curLocale: " + locale.toString());
        return locale.toString().contains(Locale.forLanguageTag("iw").toString()) ? REGEX_NAME_IW : locale.toString().contains(Locale.forLanguageTag("ar").toString()) ? REGEX_NAME_AR : locale.toString().contains(Locale.forLanguageTag("th").toString()) ? REGEX_NAME_TH : locale.toString().contains("zh_") ? REGEX_NAME_ZH_HMT : locale.toString().contains(Locale.KOREAN.toString()) ? REGEX_NAME_KO : "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"])$";
    }

    private boolean hasEmoteIcon(String str) {
        return (TextUtils.isEmpty(str) || str.codePointCount(0, str.length()) == str.length()) ? false : true;
    }

    private boolean isEmojiCharacter(char c8) {
        int type = Character.getType(c8);
        return type == 19 || type == 28;
    }

    private boolean isSpecialText(String str) {
        if (TextUtils.isEmpty(this.specialText)) {
            return false;
        }
        return str.contains(this.specialText);
    }

    private String removeEmoteIcon(String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        ArrayList arrayList = new ArrayList();
        for (int i8 = 0; i8 < stringBuffer.length(); i8++) {
            if (isEmojiCharacter(stringBuffer.charAt(i8))) {
                arrayList.add(Integer.valueOf(i8));
            }
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            stringBuffer.deleteCharAt(((Integer) arrayList.get(size)).intValue());
        }
        return stringBuffer.toString();
    }

    private String removeNotAsciiChar(String str) {
        CharsetEncoder charsetEncoderNewEncoder = StandardCharsets.US_ASCII.newEncoder();
        StringBuffer stringBuffer = new StringBuffer(str);
        ArrayList arrayList = new ArrayList();
        for (int i8 = 0; i8 < stringBuffer.length(); i8++) {
            if (!charsetEncoderNewEncoder.canEncode(stringBuffer.charAt(i8))) {
                arrayList.add(Integer.valueOf(i8));
            }
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            stringBuffer.deleteCharAt(((Integer) arrayList.get(size)).intValue());
        }
        return stringBuffer.toString();
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
    }

    public void onInputFiltered(String str) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        String string = charSequence.toString();
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(this.regex)) {
            return;
        }
        WeakReference<EditText> weakReference = this.etRef;
        if (weakReference == null && weakReference.get() == null) {
            return;
        }
        String strReplaceAll = Pattern.compile(this.regex).matcher(string).replaceAll("");
        if (this.checkEmoji && hasEmoteIcon(strReplaceAll)) {
            strReplaceAll = removeEmoteIcon(strReplaceAll);
        }
        if (this.checkASCII && containsNotAscii(strReplaceAll)) {
            strReplaceAll = removeNotAsciiChar(strReplaceAll);
        }
        if (!string.equals(strReplaceAll)) {
            try {
                this.etRef.get().setText(strReplaceAll);
                this.etRef.get().setSelection(strReplaceAll.length());
                onInputFiltered(!string.contains(strReplaceAll) ? string : string.substring(string.indexOf(strReplaceAll) + strReplaceAll.length()));
            } catch (Exception e8) {
                a.b(TAG, "Exception:" + e8);
            }
        }
        if (isSpecialText(string)) {
            this.etRef.get().setText("");
            this.etRef.get().setSelection(0);
            onInputFiltered(string);
        }
        String string2 = this.etRef.get().getText().toString();
        if (string2.equals(this.curInput)) {
            return;
        }
        this.curInput = string2;
        onTextChangedAfterFiltered(string2);
    }

    public void onTextChangedAfterFiltered(String str) {
    }

    public void setCheckASCII(boolean z7) {
        this.checkASCII = z7;
    }

    public BydTextWatcher(EditText editText, String str, boolean z7) {
        this.regex = null;
        this.specialText = null;
        this.checkEmoji = false;
        this.checkASCII = false;
        this.curInput = null;
        this.etRef = new WeakReference<>(editText);
        this.regex = str;
        this.checkEmoji = z7;
    }
}
