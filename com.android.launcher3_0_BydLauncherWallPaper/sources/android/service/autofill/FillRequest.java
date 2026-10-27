package android.service.autofill;

import android.annotation.NonNull;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.inputmethod.InlineSuggestionsRequest;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.BitUtils;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes3.dex */
public final class FillRequest implements Parcelable {
    public static final Parcelable.Creator<FillRequest> CREATOR = new Parcelable.Creator<FillRequest>() { // from class: android.service.autofill.FillRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FillRequest[] newArray(int i) {
            return new FillRequest[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FillRequest createFromParcel(Parcel parcel) {
            return new FillRequest(parcel);
        }
    };
    public static final int FLAG_COMPATIBILITY_MODE_REQUEST = 2;
    public static final int FLAG_MANUAL_REQUEST = 1;
    public static final int FLAG_PASSWORD_INPUT_TYPE = 4;
    public static final int FLAG_VIEW_NOT_FOCUSED = 16;
    public static final int INVALID_REQUEST_ID = Integer.MIN_VALUE;
    private final Bundle mClientState;
    private final List<FillContext> mFillContexts;
    private final int mFlags;
    private final int mId;
    private final InlineSuggestionsRequest mInlineSuggestionsRequest;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RequestFlags {
    }

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private void onConstructed() {
        Preconditions.checkCollectionElementsNotNull(this.mFillContexts, "contexts");
    }

    public static String requestFlagsToString(int i) {
        return BitUtils.flagsToString(i, new IntFunction() { // from class: android.service.autofill.-$$Lambda$uqv5oJtmHxutjyBIhV-Kb0aHmQg
            @Override // java.util.function.IntFunction
            public final Object apply(int i2) {
                return FillRequest.singleRequestFlagsToString(i2);
            }
        });
    }

    static String singleRequestFlagsToString(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 16 ? Integer.toHexString(i) : "FLAG_VIEW_NOT_FOCUSED" : "FLAG_PASSWORD_INPUT_TYPE" : "FLAG_COMPATIBILITY_MODE_REQUEST" : "FLAG_MANUAL_REQUEST";
    }

    public FillRequest(int i, List<FillContext> list, Bundle bundle, int i2, InlineSuggestionsRequest inlineSuggestionsRequest) {
        this.mId = i;
        this.mFillContexts = list;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) list);
        this.mClientState = bundle;
        this.mFlags = i2;
        Preconditions.checkFlagsArgument(i2, 23);
        this.mInlineSuggestionsRequest = inlineSuggestionsRequest;
        onConstructed();
    }

    public int getId() {
        return this.mId;
    }

    public List<FillContext> getFillContexts() {
        return this.mFillContexts;
    }

    public Bundle getClientState() {
        return this.mClientState;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public InlineSuggestionsRequest getInlineSuggestionsRequest() {
        return this.mInlineSuggestionsRequest;
    }

    public String toString() {
        return "FillRequest { id = " + this.mId + ", fillContexts = " + this.mFillContexts + ", clientState = " + this.mClientState + ", flags = " + requestFlagsToString(this.mFlags) + ", inlineSuggestionsRequest = " + this.mInlineSuggestionsRequest + " }";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        byte b = this.mClientState != null ? (byte) 4 : (byte) 0;
        if (this.mInlineSuggestionsRequest != null) {
            b = (byte) (b | 16);
        }
        parcel.writeByte(b);
        parcel.writeInt(this.mId);
        parcel.writeParcelableList(this.mFillContexts, i);
        Bundle bundle = this.mClientState;
        if (bundle != null) {
            parcel.writeBundle(bundle);
        }
        parcel.writeInt(this.mFlags);
        InlineSuggestionsRequest inlineSuggestionsRequest = this.mInlineSuggestionsRequest;
        if (inlineSuggestionsRequest != null) {
            parcel.writeTypedObject(inlineSuggestionsRequest, i);
        }
    }

    FillRequest(Parcel parcel) {
        byte b = parcel.readByte();
        int i = parcel.readInt();
        ArrayList arrayList = new ArrayList();
        parcel.readParcelableList(arrayList, FillContext.class.getClassLoader());
        Bundle bundle = (b & 4) == 0 ? null : parcel.readBundle();
        int i2 = parcel.readInt();
        InlineSuggestionsRequest inlineSuggestionsRequest = (b & 16) == 0 ? null : (InlineSuggestionsRequest) parcel.readTypedObject(InlineSuggestionsRequest.CREATOR);
        this.mId = i;
        this.mFillContexts = arrayList;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) arrayList);
        this.mClientState = bundle;
        this.mFlags = i2;
        Preconditions.checkFlagsArgument(i2, 23);
        this.mInlineSuggestionsRequest = inlineSuggestionsRequest;
        onConstructed();
    }
}
