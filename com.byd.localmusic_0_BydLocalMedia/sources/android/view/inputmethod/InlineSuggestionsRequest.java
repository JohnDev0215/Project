package android.view.inputmethod;

import android.annotation.NonNull;
import android.app.ActivityThread;
import android.os.Bundle;
import android.os.IBinder;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.inline.InlinePresentationSpec;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.Preconditions;
import com.android.internal.widget.InlinePresentationStyleUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class InlineSuggestionsRequest implements Parcelable {
    public static final Parcelable.Creator<InlineSuggestionsRequest> CREATOR = new Parcelable.Creator<InlineSuggestionsRequest>() { // from class: android.view.inputmethod.InlineSuggestionsRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InlineSuggestionsRequest[] newArray(int i) {
            return new InlineSuggestionsRequest[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InlineSuggestionsRequest createFromParcel(Parcel parcel) {
            return new InlineSuggestionsRequest(parcel);
        }
    };
    public static final int SUGGESTION_COUNT_UNLIMITED = Integer.MAX_VALUE;
    private Bundle mExtras;
    private int mHostDisplayId;
    private IBinder mHostInputToken;
    private String mHostPackageName;
    private final List<InlinePresentationSpec> mInlinePresentationSpecs;
    private final int mMaxSuggestionCount;
    private LocaleList mSupportedLocales;

    @Deprecated
    private void __metadata() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int defaultHostDisplayId() {
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IBinder defaultHostInputToken() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int defaultMaxSuggestionCount() {
        return Integer.MAX_VALUE;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setHostInputToken(IBinder iBinder) {
        this.mHostInputToken = iBinder;
    }

    private boolean extrasEquals(Bundle bundle) {
        return InlinePresentationStyleUtils.bundleEquals(this.mExtras, bundle);
    }

    private void parcelHostInputToken(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.mHostInputToken);
    }

    private IBinder unparcelHostInputToken(Parcel parcel) {
        return parcel.readStrongBinder();
    }

    public void setHostDisplayId(int i) {
        this.mHostDisplayId = i;
    }

    private void onConstructed() {
        Preconditions.checkState(!this.mInlinePresentationSpecs.isEmpty());
        Preconditions.checkState(this.mMaxSuggestionCount >= this.mInlinePresentationSpecs.size());
    }

    public void filterContentTypes() {
        InlinePresentationStyleUtils.filterContentTypes(this.mExtras);
        for (int i = 0; i < this.mInlinePresentationSpecs.size(); i++) {
            this.mInlinePresentationSpecs.get(i).filterContentTypes();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String defaultHostPackageName() {
        return ActivityThread.currentPackageName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LocaleList defaultSupportedLocales() {
        return LocaleList.getDefault();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle defaultExtras() {
        return Bundle.EMPTY;
    }

    static abstract class BaseBuilder {
        abstract Builder setHostDisplayId(int i);

        abstract Builder setHostInputToken(IBinder iBinder);

        abstract Builder setHostPackageName(String str);

        abstract Builder setInlinePresentationSpecs(List<InlinePresentationSpec> list);

        BaseBuilder() {
        }
    }

    InlineSuggestionsRequest(int i, List<InlinePresentationSpec> list, String str, LocaleList localeList, Bundle bundle, IBinder iBinder, int i2) {
        this.mMaxSuggestionCount = i;
        this.mInlinePresentationSpecs = list;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) list);
        this.mHostPackageName = str;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) str);
        this.mSupportedLocales = localeList;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) localeList);
        this.mExtras = bundle;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) bundle);
        this.mHostInputToken = iBinder;
        this.mHostDisplayId = i2;
        onConstructed();
    }

    public int getMaxSuggestionCount() {
        return this.mMaxSuggestionCount;
    }

    public List<InlinePresentationSpec> getInlinePresentationSpecs() {
        return this.mInlinePresentationSpecs;
    }

    public String getHostPackageName() {
        return this.mHostPackageName;
    }

    public LocaleList getSupportedLocales() {
        return this.mSupportedLocales;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public IBinder getHostInputToken() {
        return this.mHostInputToken;
    }

    public int getHostDisplayId() {
        return this.mHostDisplayId;
    }

    public String toString() {
        return "InlineSuggestionsRequest { maxSuggestionCount = " + this.mMaxSuggestionCount + ", inlinePresentationSpecs = " + this.mInlinePresentationSpecs + ", hostPackageName = " + this.mHostPackageName + ", supportedLocales = " + this.mSupportedLocales + ", extras = " + this.mExtras + ", hostInputToken = " + this.mHostInputToken + ", hostDisplayId = " + this.mHostDisplayId + " }";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InlineSuggestionsRequest inlineSuggestionsRequest = (InlineSuggestionsRequest) obj;
        return this.mMaxSuggestionCount == inlineSuggestionsRequest.mMaxSuggestionCount && Objects.equals(this.mInlinePresentationSpecs, inlineSuggestionsRequest.mInlinePresentationSpecs) && Objects.equals(this.mHostPackageName, inlineSuggestionsRequest.mHostPackageName) && Objects.equals(this.mSupportedLocales, inlineSuggestionsRequest.mSupportedLocales) && extrasEquals(inlineSuggestionsRequest.mExtras) && Objects.equals(this.mHostInputToken, inlineSuggestionsRequest.mHostInputToken) && this.mHostDisplayId == inlineSuggestionsRequest.mHostDisplayId;
    }

    public int hashCode() {
        return ((((((((((((this.mMaxSuggestionCount + 31) * 31) + Objects.hashCode(this.mInlinePresentationSpecs)) * 31) + Objects.hashCode(this.mHostPackageName)) * 31) + Objects.hashCode(this.mSupportedLocales)) * 31) + Objects.hashCode(this.mExtras)) * 31) + Objects.hashCode(this.mHostInputToken)) * 31) + this.mHostDisplayId;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mHostInputToken != null ? (byte) 32 : (byte) 0);
        parcel.writeInt(this.mMaxSuggestionCount);
        parcel.writeParcelableList(this.mInlinePresentationSpecs, i);
        parcel.writeString(this.mHostPackageName);
        parcel.writeTypedObject(this.mSupportedLocales, i);
        parcel.writeBundle(this.mExtras);
        parcelHostInputToken(parcel, i);
        parcel.writeInt(this.mHostDisplayId);
    }

    InlineSuggestionsRequest(Parcel parcel) {
        parcel.readByte();
        int i = parcel.readInt();
        ArrayList arrayList = new ArrayList();
        parcel.readParcelableList(arrayList, InlinePresentationSpec.class.getClassLoader());
        String string = parcel.readString();
        LocaleList localeList = (LocaleList) parcel.readTypedObject(LocaleList.CREATOR);
        Bundle bundle = parcel.readBundle();
        IBinder iBinderUnparcelHostInputToken = unparcelHostInputToken(parcel);
        int i2 = parcel.readInt();
        this.mMaxSuggestionCount = i;
        this.mInlinePresentationSpecs = arrayList;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) arrayList);
        this.mHostPackageName = string;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) string);
        this.mSupportedLocales = localeList;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) localeList);
        this.mExtras = bundle;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) bundle);
        this.mHostInputToken = iBinderUnparcelHostInputToken;
        this.mHostDisplayId = i2;
        onConstructed();
    }

    public static final class Builder extends BaseBuilder {
        private long mBuilderFieldsSet = 0;
        private Bundle mExtras;
        private int mHostDisplayId;
        private IBinder mHostInputToken;
        private String mHostPackageName;
        private List<InlinePresentationSpec> mInlinePresentationSpecs;
        private int mMaxSuggestionCount;
        private LocaleList mSupportedLocales;

        public Builder(List<InlinePresentationSpec> list) {
            this.mInlinePresentationSpecs = list;
            AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) list);
        }

        public Builder setMaxSuggestionCount(int i) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 1;
            this.mMaxSuggestionCount = i;
            return this;
        }

        @Override // android.view.inputmethod.InlineSuggestionsRequest.BaseBuilder
        public Builder setInlinePresentationSpecs(List<InlinePresentationSpec> list) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 2;
            this.mInlinePresentationSpecs = list;
            return this;
        }

        public Builder addInlinePresentationSpecs(InlinePresentationSpec inlinePresentationSpec) {
            if (this.mInlinePresentationSpecs == null) {
                setInlinePresentationSpecs(new ArrayList());
            }
            this.mInlinePresentationSpecs.add(inlinePresentationSpec);
            return this;
        }

        @Override // android.view.inputmethod.InlineSuggestionsRequest.BaseBuilder
        Builder setHostPackageName(String str) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 4;
            this.mHostPackageName = str;
            return this;
        }

        public Builder setSupportedLocales(LocaleList localeList) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 8;
            this.mSupportedLocales = localeList;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 16;
            this.mExtras = bundle;
            return this;
        }

        @Override // android.view.inputmethod.InlineSuggestionsRequest.BaseBuilder
        Builder setHostInputToken(IBinder iBinder) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 32;
            this.mHostInputToken = iBinder;
            return this;
        }

        @Override // android.view.inputmethod.InlineSuggestionsRequest.BaseBuilder
        Builder setHostDisplayId(int i) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 64;
            this.mHostDisplayId = i;
            return this;
        }

        public InlineSuggestionsRequest build() {
            checkNotUsed();
            long j = this.mBuilderFieldsSet | 128;
            this.mBuilderFieldsSet = j;
            if ((j & 1) == 0) {
                this.mMaxSuggestionCount = InlineSuggestionsRequest.defaultMaxSuggestionCount();
            }
            if ((this.mBuilderFieldsSet & 4) == 0) {
                this.mHostPackageName = InlineSuggestionsRequest.defaultHostPackageName();
            }
            if ((this.mBuilderFieldsSet & 8) == 0) {
                this.mSupportedLocales = InlineSuggestionsRequest.defaultSupportedLocales();
            }
            if ((this.mBuilderFieldsSet & 16) == 0) {
                this.mExtras = InlineSuggestionsRequest.defaultExtras();
            }
            if ((this.mBuilderFieldsSet & 32) == 0) {
                this.mHostInputToken = InlineSuggestionsRequest.defaultHostInputToken();
            }
            if ((this.mBuilderFieldsSet & 64) == 0) {
                this.mHostDisplayId = InlineSuggestionsRequest.defaultHostDisplayId();
            }
            return new InlineSuggestionsRequest(this.mMaxSuggestionCount, this.mInlinePresentationSpecs, this.mHostPackageName, this.mSupportedLocales, this.mExtras, this.mHostInputToken, this.mHostDisplayId);
        }

        private void checkNotUsed() {
            if ((this.mBuilderFieldsSet & 128) != 0) {
                throw new IllegalStateException("This Builder should not be reused. Use a new Builder instance instead");
            }
        }
    }
}
