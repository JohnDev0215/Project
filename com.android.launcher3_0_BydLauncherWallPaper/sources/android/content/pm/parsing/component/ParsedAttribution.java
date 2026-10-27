package android.content.pm.parsing.component;

import android.annotation.NonNull;
import android.annotation.StringRes;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import com.android.internal.util.AnnotationValidations;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ParsedAttribution implements Parcelable {
    public static final Parcelable.Creator<ParsedAttribution> CREATOR = new Parcelable.Creator<ParsedAttribution>() { // from class: android.content.pm.parsing.component.ParsedAttribution.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedAttribution[] newArray(int i) {
            return new ParsedAttribution[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedAttribution createFromParcel(Parcel parcel) {
            return new ParsedAttribution(parcel);
        }
    };
    public static final int MAX_ATTRIBUTION_TAG_LEN = 50;
    private static final int MAX_NUM_ATTRIBUTIONS = 1000;
    public final List<String> inheritFrom;
    public final int label;
    public final String tag;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Max {
    }

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static boolean isCombinationValid(List<ParsedAttribution> list) {
        if (list == null) {
            return true;
        }
        ArraySet arraySet = new ArraySet(list.size());
        ArraySet arraySet2 = new ArraySet();
        int size = list.size();
        if (size > 1000) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!arraySet.add(list.get(i).tag)) {
                return false;
            }
        }
        for (int i2 = 0; i2 < size; i2++) {
            ParsedAttribution parsedAttribution = list.get(i2);
            int size2 = parsedAttribution.inheritFrom.size();
            for (int i3 = 0; i3 < size2; i3++) {
                String str = parsedAttribution.inheritFrom.get(i3);
                if (arraySet.contains(str) || !arraySet2.add(str)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String maxToString(int i) {
        return i != 50 ? i != 1000 ? Integer.toHexString(i) : "MAX_NUM_ATTRIBUTIONS" : "MAX_ATTRIBUTION_TAG_LEN";
    }

    public ParsedAttribution(String str, int i, List<String> list) {
        this.tag = str;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) str);
        this.label = i;
        AnnotationValidations.validate((Class<? extends Annotation>) StringRes.class, (Annotation) null, i);
        this.inheritFrom = list;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) list);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.tag);
        parcel.writeInt(this.label);
        parcel.writeStringList(this.inheritFrom);
    }

    protected ParsedAttribution(Parcel parcel) {
        String string = parcel.readString();
        int i = parcel.readInt();
        ArrayList arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        this.tag = string;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) string);
        this.label = i;
        AnnotationValidations.validate((Class<? extends Annotation>) StringRes.class, (Annotation) null, i);
        this.inheritFrom = arrayList;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) arrayList);
    }
}
