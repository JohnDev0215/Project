package android.view.inputmethod;

import android.os.Bundle;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Printer;
import android.view.autofill.AutofillId;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class EditorInfo implements InputType, Parcelable {
    public static final Parcelable.Creator<EditorInfo> CREATOR = new Parcelable.Creator<EditorInfo>() { // from class: android.view.inputmethod.EditorInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EditorInfo createFromParcel(Parcel parcel) {
            EditorInfo editorInfo = new EditorInfo();
            editorInfo.inputType = parcel.readInt();
            editorInfo.imeOptions = parcel.readInt();
            editorInfo.privateImeOptions = parcel.readString();
            editorInfo.actionLabel = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            editorInfo.actionId = parcel.readInt();
            editorInfo.initialSelStart = parcel.readInt();
            editorInfo.initialSelEnd = parcel.readInt();
            editorInfo.initialCapsMode = parcel.readInt();
            editorInfo.hintText = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            editorInfo.label = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            editorInfo.packageName = parcel.readString();
            editorInfo.autofillId = (AutofillId) parcel.readParcelable(AutofillId.class.getClassLoader());
            editorInfo.fieldId = parcel.readInt();
            editorInfo.fieldName = parcel.readString();
            editorInfo.extras = parcel.readBundle();
            editorInfo.mInitialSurroundingText = InitialSurroundingText.CREATOR.createFromParcel(parcel);
            LocaleList localeListCreateFromParcel = LocaleList.CREATOR.createFromParcel(parcel);
            if (localeListCreateFromParcel.isEmpty()) {
                localeListCreateFromParcel = null;
            }
            editorInfo.hintLocales = localeListCreateFromParcel;
            editorInfo.contentMimeTypes = parcel.readStringArray();
            editorInfo.targetInputMethodUser = UserHandle.readFromParcel(parcel);
            return editorInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EditorInfo[] newArray(int i) {
            return new EditorInfo[i];
        }
    };
    public static final int IME_ACTION_DONE = 6;
    public static final int IME_ACTION_GO = 2;
    public static final int IME_ACTION_NEXT = 5;
    public static final int IME_ACTION_NONE = 1;
    public static final int IME_ACTION_PREVIOUS = 7;
    public static final int IME_ACTION_SEARCH = 3;
    public static final int IME_ACTION_SEND = 4;
    public static final int IME_ACTION_UNSPECIFIED = 0;
    public static final int IME_FLAG_FORCE_ASCII = Integer.MIN_VALUE;
    public static final int IME_FLAG_NAVIGATE_NEXT = 134217728;
    public static final int IME_FLAG_NAVIGATE_PREVIOUS = 67108864;
    public static final int IME_FLAG_NO_ACCESSORY_ACTION = 536870912;
    public static final int IME_FLAG_NO_ENTER_ACTION = 1073741824;
    public static final int IME_FLAG_NO_EXTRACT_UI = 268435456;
    public static final int IME_FLAG_NO_FULLSCREEN = 33554432;
    public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 16777216;
    public static final int IME_MASK_ACTION = 255;
    public static final int IME_NULL = 0;
    static final int MAX_INITIAL_SELECTION_LENGTH = 1024;
    static final int MEMORY_EFFICIENT_TEXT_LENGTH = 2048;
    public AutofillId autofillId;
    public Bundle extras;
    public int fieldId;
    public String fieldName;
    public CharSequence hintText;
    public CharSequence label;
    public String packageName;
    public int inputType = 0;
    public int imeOptions = 0;
    public String privateImeOptions = null;
    public CharSequence actionLabel = null;
    public int actionId = 0;
    public int initialSelStart = -1;
    public int initialSelEnd = -1;
    public int initialCapsMode = 0;
    public LocaleList hintLocales = null;
    public String[] contentMimeTypes = null;
    public UserHandle targetInputMethodUser = null;
    private InitialSurroundingText mInitialSurroundingText = new InitialSurroundingText();

    @Retention(RetentionPolicy.SOURCE)
    @interface TrimPolicy {
        public static final int HEAD = 0;
        public static final int TAIL = 1;
    }

    private static boolean isPasswordInputType(int i) {
        int i2 = i & 4095;
        return i2 == 129 || i2 == 225 || i2 == 18;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setInitialSurroundingText(CharSequence charSequence) {
        setInitialSurroundingSubText(charSequence, 0);
    }

    public void setInitialSurroundingSubText(CharSequence charSequence, int i) {
        Editable editableNewEditable = Editable.Factory.getInstance().newEditable(charSequence);
        Objects.requireNonNull(editableNewEditable);
        int i2 = this.initialSelStart;
        int i3 = this.initialSelEnd;
        int i4 = i2 > i3 ? i3 - i : i2 - i;
        int i5 = this.initialSelStart;
        int i6 = this.initialSelEnd;
        int i7 = i5 > i6 ? i5 - i : i6 - i;
        int length = editableNewEditable.length();
        if (i < 0 || i4 < 0 || i7 > length) {
            this.mInitialSurroundingText = new InitialSurroundingText();
            return;
        }
        if (isPasswordInputType(this.inputType)) {
            this.mInitialSurroundingText = new InitialSurroundingText();
        } else if (length <= 2048) {
            this.mInitialSurroundingText = new InitialSurroundingText(editableNewEditable, i4, i7);
        } else {
            trimLongSurroundingText(editableNewEditable, i4, i7);
        }
    }

    private void trimLongSurroundingText(CharSequence charSequence, int i, int i2) {
        CharSequence charSequenceSubSequence;
        int i3 = i2 - i;
        int i4 = i3 > 1024 ? 0 : i3;
        int i5 = 2048 - i4;
        int iMin = Math.min(charSequence.length() - i2, i5 - Math.min(i, (int) (((double) i5) * 0.8d)));
        int iMin2 = Math.min(i, i5 - iMin);
        int i6 = i - iMin2;
        if (isCutOnSurrogate(charSequence, i6, 0)) {
            i6++;
            iMin2--;
        }
        if (isCutOnSurrogate(charSequence, (i2 + iMin) - 1, 1)) {
            iMin--;
        }
        int i7 = iMin2 + i4 + iMin;
        if (i4 != i3) {
            charSequenceSubSequence = TextUtils.concat(charSequence.subSequence(i6, i6 + iMin2), charSequence.subSequence(i2, iMin + i2));
        } else {
            charSequenceSubSequence = charSequence.subSequence(i6, i7 + i6);
        }
        int i8 = iMin2 + 0;
        this.mInitialSurroundingText = new InitialSurroundingText(charSequenceSubSequence, i8, i4 + i8);
    }

    public CharSequence getInitialTextBeforeCursor(int i, int i2) {
        return this.mInitialSurroundingText.getInitialTextBeforeCursor(i, i2);
    }

    public CharSequence getInitialSelectedText(int i) {
        int i2 = this.initialSelStart;
        int i3 = this.initialSelEnd;
        if (i2 > i3) {
            i2 = i3;
        }
        int i4 = this.initialSelStart;
        int i5 = this.initialSelEnd;
        if (i4 <= i5) {
            i4 = i5;
        }
        int i6 = i4 - i2;
        if (this.initialSelStart < 0 || this.initialSelEnd < 0 || this.mInitialSurroundingText.getSelectionLength() != i6) {
            return null;
        }
        return this.mInitialSurroundingText.getInitialSelectedText(i);
    }

    public CharSequence getInitialTextAfterCursor(int i, int i2) {
        return this.mInitialSurroundingText.getInitialTextAfterCursor(i, i2);
    }

    private static boolean isCutOnSurrogate(CharSequence charSequence, int i, int i2) {
        if (i2 == 0) {
            return Character.isLowSurrogate(charSequence.charAt(i));
        }
        if (i2 != 1) {
            return false;
        }
        return Character.isHighSurrogate(charSequence.charAt(i));
    }

    public final void makeCompatible(int i) {
        if (i < 11) {
            int i2 = this.inputType;
            int i3 = i2 & 4095;
            if (i3 == 2 || i3 == 18) {
                this.inputType = (this.inputType & InputType.TYPE_MASK_FLAGS) | 2;
            } else if (i3 == 209) {
                this.inputType = (i2 & InputType.TYPE_MASK_FLAGS) | 33;
            } else {
                if (i3 != 225) {
                    return;
                }
                this.inputType = (i2 & InputType.TYPE_MASK_FLAGS) | 129;
            }
        }
    }

    public void dump(Printer printer, String str) {
        printer.println(str + "inputType=0x" + Integer.toHexString(this.inputType) + " imeOptions=0x" + Integer.toHexString(this.imeOptions) + " privateImeOptions=" + this.privateImeOptions);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("actionLabel=");
        sb.append((Object) this.actionLabel);
        sb.append(" actionId=");
        sb.append(this.actionId);
        printer.println(sb.toString());
        printer.println(str + "initialSelStart=" + this.initialSelStart + " initialSelEnd=" + this.initialSelEnd + " initialCapsMode=0x" + Integer.toHexString(this.initialCapsMode));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("hintText=");
        sb2.append((Object) this.hintText);
        sb2.append(" label=");
        sb2.append((Object) this.label);
        printer.println(sb2.toString());
        printer.println(str + "packageName=" + this.packageName + " autofillId=" + this.autofillId + " fieldId=" + this.fieldId + " fieldName=" + this.fieldName);
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append("extras=");
        sb3.append(this.extras);
        printer.println(sb3.toString());
        StringBuilder sb4 = new StringBuilder();
        sb4.append(str);
        sb4.append("hintLocales=");
        sb4.append(this.hintLocales);
        printer.println(sb4.toString());
        printer.println(str + "contentMimeTypes=" + Arrays.toString(this.contentMimeTypes));
        if (this.targetInputMethodUser != null) {
            printer.println(str + "targetInputMethodUserId=" + this.targetInputMethodUser.getIdentifier());
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.inputType);
        parcel.writeInt(this.imeOptions);
        parcel.writeString(this.privateImeOptions);
        TextUtils.writeToParcel(this.actionLabel, parcel, i);
        parcel.writeInt(this.actionId);
        parcel.writeInt(this.initialSelStart);
        parcel.writeInt(this.initialSelEnd);
        parcel.writeInt(this.initialCapsMode);
        TextUtils.writeToParcel(this.hintText, parcel, i);
        TextUtils.writeToParcel(this.label, parcel, i);
        parcel.writeString(this.packageName);
        parcel.writeParcelable(this.autofillId, i);
        parcel.writeInt(this.fieldId);
        parcel.writeString(this.fieldName);
        parcel.writeBundle(this.extras);
        this.mInitialSurroundingText.writeToParcel(parcel, i);
        LocaleList localeList = this.hintLocales;
        if (localeList != null) {
            localeList.writeToParcel(parcel, i);
        } else {
            LocaleList.getEmptyLocaleList().writeToParcel(parcel, i);
        }
        parcel.writeStringArray(this.contentMimeTypes);
        UserHandle.writeToParcel(this.targetInputMethodUser, parcel);
    }

    static final class InitialSurroundingText implements Parcelable {
        public static final Parcelable.Creator<InitialSurroundingText> CREATOR = new Parcelable.Creator<InitialSurroundingText>() { // from class: android.view.inputmethod.EditorInfo.InitialSurroundingText.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public InitialSurroundingText createFromParcel(Parcel parcel) {
                return new InitialSurroundingText(TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel), parcel.readInt(), parcel.readInt());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public InitialSurroundingText[] newArray(int i) {
                return new InitialSurroundingText[i];
            }
        };
        final int mSelectionEnd;
        final int mSelectionHead;
        final CharSequence mSurroundingText;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        InitialSurroundingText() {
            this.mSurroundingText = null;
            this.mSelectionHead = 0;
            this.mSelectionEnd = 0;
        }

        InitialSurroundingText(CharSequence charSequence, int i, int i2) {
            this.mSurroundingText = charSequence;
            this.mSelectionHead = i;
            this.mSelectionEnd = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CharSequence getInitialTextBeforeCursor(int i, int i2) {
            if (this.mSurroundingText == null) {
                return null;
            }
            int iMin = Math.min(i, this.mSelectionHead);
            if ((i2 & 1) != 0) {
                CharSequence charSequence = this.mSurroundingText;
                int i3 = this.mSelectionHead;
                return charSequence.subSequence(i3 - iMin, i3);
            }
            CharSequence charSequence2 = this.mSurroundingText;
            int i4 = this.mSelectionHead;
            return TextUtils.substring(charSequence2, i4 - iMin, i4);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CharSequence getInitialSelectedText(int i) {
            CharSequence charSequence = this.mSurroundingText;
            if (charSequence == null) {
                return null;
            }
            if ((i & 1) != 0) {
                return charSequence.subSequence(this.mSelectionHead, this.mSelectionEnd);
            }
            return TextUtils.substring(charSequence, this.mSelectionHead, this.mSelectionEnd);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CharSequence getInitialTextAfterCursor(int i, int i2) {
            CharSequence charSequence = this.mSurroundingText;
            if (charSequence == null) {
                return null;
            }
            int iMin = Math.min(i, charSequence.length() - this.mSelectionEnd);
            if ((i2 & 1) != 0) {
                CharSequence charSequence2 = this.mSurroundingText;
                int i3 = this.mSelectionEnd;
                return charSequence2.subSequence(i3, iMin + i3);
            }
            CharSequence charSequence3 = this.mSurroundingText;
            int i4 = this.mSelectionEnd;
            return TextUtils.substring(charSequence3, i4, iMin + i4);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getSelectionLength() {
            return this.mSelectionEnd - this.mSelectionHead;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            TextUtils.writeToParcel(this.mSurroundingText, parcel, i);
            parcel.writeInt(this.mSelectionHead);
            parcel.writeInt(this.mSelectionEnd);
        }
    }
}
