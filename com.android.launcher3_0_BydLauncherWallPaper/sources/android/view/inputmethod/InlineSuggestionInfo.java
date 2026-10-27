package android.view.inputmethod;

import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.inline.InlinePresentationSpec;
import com.android.internal.util.AnnotationValidations;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class InlineSuggestionInfo implements Parcelable {
    public static final Parcelable.Creator<InlineSuggestionInfo> CREATOR = new Parcelable.Creator<InlineSuggestionInfo>() { // from class: android.view.inputmethod.InlineSuggestionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InlineSuggestionInfo[] newArray(int i) {
            return new InlineSuggestionInfo[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InlineSuggestionInfo createFromParcel(Parcel parcel) {
            return new InlineSuggestionInfo(parcel);
        }
    };
    public static final String SOURCE_AUTOFILL = "android:autofill";
    public static final String SOURCE_PLATFORM = "android:platform";
    public static final String TYPE_ACTION = "android:autofill:action";
    public static final String TYPE_SUGGESTION = "android:autofill:suggestion";
    private final String[] mAutofillHints;
    private final InlinePresentationSpec mInlinePresentationSpec;
    private final boolean mPinned;
    private final String mSource;
    private final String mType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Source {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static InlineSuggestionInfo newInlineSuggestionInfo(InlinePresentationSpec inlinePresentationSpec, String str, String[] strArr, String str2, boolean z) {
        return new InlineSuggestionInfo(inlinePresentationSpec, str, strArr, str2, z);
    }

    public InlineSuggestionInfo(InlinePresentationSpec inlinePresentationSpec, String str, String[] strArr, String str2, boolean z) {
        this.mInlinePresentationSpec = inlinePresentationSpec;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) inlinePresentationSpec);
        this.mSource = str;
        if (!Objects.equals(str, SOURCE_AUTOFILL) && !Objects.equals(str, SOURCE_PLATFORM)) {
            throw new IllegalArgumentException("source was " + str + " but must be one of: SOURCE_AUTOFILL(" + SOURCE_AUTOFILL + "), SOURCE_PLATFORM(" + SOURCE_PLATFORM + ")");
        }
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) str);
        this.mAutofillHints = strArr;
        this.mType = str2;
        if (!Objects.equals(str2, TYPE_SUGGESTION) && !Objects.equals(str2, TYPE_ACTION)) {
            throw new IllegalArgumentException("type was " + str2 + " but must be one of: TYPE_SUGGESTION(" + TYPE_SUGGESTION + "), TYPE_ACTION(" + TYPE_ACTION + ")");
        }
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) str2);
        this.mPinned = z;
    }

    public InlinePresentationSpec getInlinePresentationSpec() {
        return this.mInlinePresentationSpec;
    }

    public String getSource() {
        return this.mSource;
    }

    public String[] getAutofillHints() {
        return this.mAutofillHints;
    }

    public String getType() {
        return this.mType;
    }

    public boolean isPinned() {
        return this.mPinned;
    }

    public String toString() {
        return "InlineSuggestionInfo { inlinePresentationSpec = " + this.mInlinePresentationSpec + ", source = " + this.mSource + ", autofillHints = " + Arrays.toString(this.mAutofillHints) + ", type = " + this.mType + ", pinned = " + this.mPinned + " }";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InlineSuggestionInfo inlineSuggestionInfo = (InlineSuggestionInfo) obj;
        return Objects.equals(this.mInlinePresentationSpec, inlineSuggestionInfo.mInlinePresentationSpec) && Objects.equals(this.mSource, inlineSuggestionInfo.mSource) && Arrays.equals(this.mAutofillHints, inlineSuggestionInfo.mAutofillHints) && Objects.equals(this.mType, inlineSuggestionInfo.mType) && this.mPinned == inlineSuggestionInfo.mPinned;
    }

    public int hashCode() {
        return ((((((((Objects.hashCode(this.mInlinePresentationSpec) + 31) * 31) + Objects.hashCode(this.mSource)) * 31) + Arrays.hashCode(this.mAutofillHints)) * 31) + Objects.hashCode(this.mType)) * 31) + Boolean.hashCode(this.mPinned);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        byte b = this.mPinned ? (byte) 16 : (byte) 0;
        if (this.mAutofillHints != null) {
            b = (byte) (b | 4);
        }
        parcel.writeByte(b);
        parcel.writeTypedObject(this.mInlinePresentationSpec, i);
        parcel.writeString(this.mSource);
        String[] strArr = this.mAutofillHints;
        if (strArr != null) {
            parcel.writeStringArray(strArr);
        }
        parcel.writeString(this.mType);
    }

    InlineSuggestionInfo(Parcel parcel) {
        byte b = parcel.readByte();
        boolean z = (b & 16) != 0;
        InlinePresentationSpec inlinePresentationSpec = (InlinePresentationSpec) parcel.readTypedObject(InlinePresentationSpec.CREATOR);
        String string = parcel.readString();
        String[] strArrCreateStringArray = (b & 4) == 0 ? null : parcel.createStringArray();
        String string2 = parcel.readString();
        this.mInlinePresentationSpec = inlinePresentationSpec;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) inlinePresentationSpec);
        this.mSource = string;
        if (!Objects.equals(string, SOURCE_AUTOFILL) && !Objects.equals(string, SOURCE_PLATFORM)) {
            throw new IllegalArgumentException("source was " + string + " but must be one of: SOURCE_AUTOFILL(" + SOURCE_AUTOFILL + "), SOURCE_PLATFORM(" + SOURCE_PLATFORM + ")");
        }
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) string);
        this.mAutofillHints = strArrCreateStringArray;
        this.mType = string2;
        if (!Objects.equals(string2, TYPE_SUGGESTION) && !Objects.equals(string2, TYPE_ACTION)) {
            throw new IllegalArgumentException("type was " + string2 + " but must be one of: TYPE_SUGGESTION(" + TYPE_SUGGESTION + "), TYPE_ACTION(" + TYPE_ACTION + ")");
        }
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) string2);
        this.mPinned = z;
    }
}
