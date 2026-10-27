package b.b.g.q.h;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import com.byd.carsettings.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: BydTextWatcher.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements TextWatcher {
    public static final String REGEX_DB = "[^A-Za-z0-9_ ]";
    public static final String REGEX_DEF = "[^A-Za-z0-9_]";
    public static final String REGEX_DIGITS = "[^0-9]";
    public static final String REGEX_NAME = "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"])$";
    public static final String REGEX_NAME_IW = "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"\\u0590-\\u05FF])$";
    public static final String REGEX_NAME_RU = "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"\\p{IsCyrillic}])$";
    public static final String REGEX_NAME_TH = "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"\\p{InThai}])$";
    public static final String REGEX_NAME_ZH_HMT = "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"\\u4E00-\\u9FFF])$";
    public static final String REGEX_PWD = "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"])$";
    public static final String REGEX_PWD_2 = "^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?![,\\.#%'\\+\\*\\-:;^_`]+$)[,\\.#%'\\+\\*\\-:;^_`?0-9A-Za-z]{8,64}$";
    public static final String REGEX_SEARCH = "[%|,&_<=>，]";
    public static final String SPEC_CHARACTERS = " !\"#$%&'()*+,-./:;<=>?@\\]\\[^_`{|}~";
    private static final String TAG = "BydTextWatcher";
    public static final String character = "[a-zA-Z]+$";
    public static final String ncw = "\\w+$";
    public static final String number_and_character = "((^[a-zA-Z]+[0-9]+[a-zA-Z0-9]*)+)|((^[0-9]+[a-zA-Z]+[a-zA-Z0-9]*)+)$";
    public static final String numberic = "[0-9]+$";
    private boolean checkEmoji;
    public volatile String curInput;
    private final WeakReference<EditText> etRef;
    public String regex;
    public String specialText;

    public a(EditText editText, String str) {
        this.regex = null;
        this.specialText = null;
        this.checkEmoji = false;
        this.curInput = null;
        this.etRef = new WeakReference<>(editText);
        this.regex = str;
        if (editText != null) {
            this.specialText = editText.getContext().getString(R.string.special_def_name);
        }
    }

    public static boolean checkPassword(String str) {
        if (str.length() < 8 || str.length() > 64) {
            return false;
        }
        boolean z = false;
        for (char c2 : str.toCharArray()) {
            if (SPEC_CHARACTERS.contains(String.valueOf(c2))) {
                str = str.replace(c2, ' ');
                z = true;
            }
        }
        String strReplace = str.replace(" ", "");
        boolean zMatches = Pattern.compile(numberic).matcher(strReplace).matches();
        boolean zMatches2 = Pattern.compile(character).matcher(strReplace).matches();
        boolean zMatches3 = Pattern.compile(number_and_character).matcher(strReplace).matches();
        return (zMatches && z) || (zMatches2 && z) || ((zMatches3 && z) || zMatches3);
    }

    public static String getNameRegex(Context context) {
        if (context == null) {
            return "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"])$";
        }
        Locale locale = context.getResources().getConfiguration().getLocales().get(0);
        b.b.m.c.a.a(TAG, "getNameRegex---curLocale: " + locale.toString());
        return locale.toString().contains(Locale.forLanguageTag("iw").toString()) ? REGEX_NAME_IW : locale.toString().contains(Locale.forLanguageTag("th").toString()) ? REGEX_NAME_TH : locale.toString().contains(Locale.forLanguageTag("ru").toString()) ? REGEX_NAME_RU : locale.toString().contains(Locale.forLanguageTag("zh_TW").toString()) ? REGEX_NAME_ZH_HMT : "([^A-Za-z0-9_ \\@\\.\\^\\\\\\|\\/\\<\\>\\[\\]\\{\\}\\(\\)\\。\\、\\+\\-\\*\\%\\&\\=\\#\\~\\€\\$\\¥\\：\\:\\；\\;\\，\\,\\！\\!\\'\\‘\\？\\?\\“\\\"])$";
    }

    private boolean hasEmoteIcon(String str) {
        return (TextUtils.isEmpty(str) || str.codePointCount(0, str.length()) == str.length()) ? false : true;
    }

    private boolean isEmojiCharacter(char c2) {
        int type = Character.getType(c2);
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
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (isEmojiCharacter(stringBuffer.charAt(i))) {
                arrayList.add(Integer.valueOf(i));
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
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onInputFiltered(String str) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        WeakReference<EditText> weakReference;
        String string = charSequence.toString();
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(this.regex) || (weakReference = this.etRef) == null || weakReference.get() == null) {
            return;
        }
        String strReplaceAll = Pattern.compile(this.regex).matcher(string).replaceAll("");
        if (this.checkEmoji && hasEmoteIcon(strReplaceAll)) {
            strReplaceAll = removeEmoteIcon(strReplaceAll);
        }
        if (!string.equals(strReplaceAll)) {
            try {
                this.etRef.get().setText(strReplaceAll);
                this.etRef.get().setSelection(strReplaceAll.length());
                onInputFiltered(!string.contains(strReplaceAll) ? string : string.substring(string.indexOf(strReplaceAll) + strReplaceAll.length()));
            } catch (Exception e2) {
                b.b.m.c.a.c(TAG, "Exception:" + e2);
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

    public a(EditText editText, String str, boolean z) {
        this.regex = null;
        this.specialText = null;
        this.checkEmoji = false;
        this.curInput = null;
        this.etRef = new WeakReference<>(editText);
        this.regex = str;
        this.checkEmoji = z;
        if (editText != null) {
            this.specialText = editText.getContext().getString(R.string.special_def_name);
        }
    }
}
