package android.content.pm.parsing.component;

import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.CollectionUtils;
import com.android.internal.util.Parcelling;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ParsedProcess implements Parcelable {
    public static final Parcelable.Creator<ParsedProcess> CREATOR;
    static Parcelling<Set<String>> sParcellingForDeniedPermissions;
    protected Set<String> deniedPermissions;
    protected int gwpAsanMode;
    protected String name;

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParsedProcess() {
        this.deniedPermissions = Collections.emptySet();
        this.gwpAsanMode = -1;
    }

    public ParsedProcess(ParsedProcess parsedProcess) {
        this.deniedPermissions = Collections.emptySet();
        this.gwpAsanMode = -1;
        this.name = parsedProcess.name;
        this.deniedPermissions = new ArraySet(parsedProcess.deniedPermissions);
    }

    public void addStateFrom(ParsedProcess parsedProcess) {
        this.deniedPermissions = CollectionUtils.addAll(this.deniedPermissions, parsedProcess.deniedPermissions);
    }

    public ParsedProcess(String str, Set<String> set, int i) {
        this.deniedPermissions = Collections.emptySet();
        this.gwpAsanMode = -1;
        this.name = str;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) str);
        this.deniedPermissions = set;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) set);
        this.gwpAsanMode = i;
    }

    public String getName() {
        return this.name;
    }

    public Set<String> getDeniedPermissions() {
        return this.deniedPermissions;
    }

    public int getGwpAsanMode() {
        return this.gwpAsanMode;
    }

    static {
        Parcelling<Set<String>> parcelling = Parcelling.Cache.get(Parcelling.BuiltIn.ForInternedStringSet.class);
        sParcellingForDeniedPermissions = parcelling;
        if (parcelling == null) {
            sParcellingForDeniedPermissions = Parcelling.Cache.put(new Parcelling.BuiltIn.ForInternedStringSet());
        }
        CREATOR = new Parcelable.Creator<ParsedProcess>() { // from class: android.content.pm.parsing.component.ParsedProcess.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ParsedProcess[] newArray(int i) {
                return new ParsedProcess[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ParsedProcess createFromParcel(Parcel parcel) {
                return new ParsedProcess(parcel);
            }
        };
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        sParcellingForDeniedPermissions.parcel(this.deniedPermissions, parcel, i);
        parcel.writeInt(this.gwpAsanMode);
    }

    protected ParsedProcess(Parcel parcel) {
        this.deniedPermissions = Collections.emptySet();
        this.gwpAsanMode = -1;
        String string = parcel.readString();
        Set<String> setUnparcel = sParcellingForDeniedPermissions.unparcel(parcel);
        int i = parcel.readInt();
        this.name = string;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) string);
        this.deniedPermissions = setUnparcel;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) setUnparcel);
        this.gwpAsanMode = i;
    }
}
