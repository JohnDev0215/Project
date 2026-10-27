package android.content.pm;

import android.annotation.NonNull;
import android.content.pm.ApplicationInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.Parcelling;
import java.lang.annotation.Annotation;

/* JADX INFO: loaded from: classes.dex */
public class ProcessInfo implements Parcelable {
    public static final Parcelable.Creator<ProcessInfo> CREATOR;
    static Parcelling<ArraySet<String>> sParcellingForDeniedPermissions;
    public ArraySet<String> deniedPermissions;
    public int gwpAsanMode;
    public String name;

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Deprecated
    public ProcessInfo(ProcessInfo processInfo) {
        this.name = processInfo.name;
        this.deniedPermissions = processInfo.deniedPermissions;
        this.gwpAsanMode = processInfo.gwpAsanMode;
    }

    public ProcessInfo(String str, ArraySet<String> arraySet, int i) {
        this.name = str;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) str);
        this.deniedPermissions = arraySet;
        this.gwpAsanMode = i;
        AnnotationValidations.validate((Class<? extends Annotation>) ApplicationInfo.GwpAsanMode.class, (Annotation) null, i);
    }

    static {
        Parcelling<ArraySet<String>> parcelling = Parcelling.Cache.get(Parcelling.BuiltIn.ForInternedStringArraySet.class);
        sParcellingForDeniedPermissions = parcelling;
        if (parcelling == null) {
            sParcellingForDeniedPermissions = Parcelling.Cache.put(new Parcelling.BuiltIn.ForInternedStringArraySet());
        }
        CREATOR = new Parcelable.Creator<ProcessInfo>() { // from class: android.content.pm.ProcessInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ProcessInfo[] newArray(int i) {
                return new ProcessInfo[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ProcessInfo createFromParcel(Parcel parcel) {
                return new ProcessInfo(parcel);
            }
        };
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.deniedPermissions != null ? (byte) 2 : (byte) 0);
        parcel.writeString(this.name);
        sParcellingForDeniedPermissions.parcel(this.deniedPermissions, parcel, i);
        parcel.writeInt(this.gwpAsanMode);
    }

    protected ProcessInfo(Parcel parcel) {
        parcel.readByte();
        String string = parcel.readString();
        ArraySet<String> arraySetUnparcel = sParcellingForDeniedPermissions.unparcel(parcel);
        int i = parcel.readInt();
        this.name = string;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) string);
        this.deniedPermissions = arraySetUnparcel;
        this.gwpAsanMode = i;
        AnnotationValidations.validate((Class<? extends Annotation>) ApplicationInfo.GwpAsanMode.class, (Annotation) null, i);
    }
}
