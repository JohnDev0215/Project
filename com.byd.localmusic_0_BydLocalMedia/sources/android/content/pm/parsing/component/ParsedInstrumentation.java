package android.content.pm.parsing.component;

import android.content.ComponentName;
import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class ParsedInstrumentation extends ParsedComponent {
    public static final Parcelable.Creator<ParsedInstrumentation> CREATOR = new Parcelable.Creator<ParsedInstrumentation>() { // from class: android.content.pm.parsing.component.ParsedInstrumentation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedInstrumentation createFromParcel(Parcel parcel) {
            return new ParsedInstrumentation(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedInstrumentation[] newArray(int i) {
            return new ParsedInstrumentation[i];
        }
    };
    boolean functionalTest;
    boolean handleProfiling;
    private String targetPackage;
    private String targetProcesses;

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParsedInstrumentation() {
    }

    public void setTargetPackage(String str) {
        this.targetPackage = TextUtils.safeIntern(str);
    }

    public void setTargetProcesses(String str) {
        this.targetProcesses = TextUtils.safeIntern(str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("Instrumentation{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(' ');
        ComponentName.appendShortString(sb, getPackageName(), getName());
        sb.append('}');
        return sb.toString();
    }

    @Override // android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        ParsingPackageImpl.sForInternedString.parcel(this.targetPackage, parcel, i);
        ParsingPackageImpl.sForInternedString.parcel(this.targetProcesses, parcel, i);
        parcel.writeBoolean(this.handleProfiling);
        parcel.writeBoolean(this.functionalTest);
    }

    protected ParsedInstrumentation(Parcel parcel) {
        super(parcel);
        this.targetPackage = ParsingPackageImpl.sForInternedString.unparcel(parcel);
        this.targetProcesses = ParsingPackageImpl.sForInternedString.unparcel(parcel);
        this.handleProfiling = parcel.readByte() != 0;
        this.functionalTest = parcel.readByte() != 0;
    }

    public String getTargetPackage() {
        return this.targetPackage;
    }

    public String getTargetProcesses() {
        return this.targetProcesses;
    }

    public boolean isHandleProfiling() {
        return this.handleProfiling;
    }

    public boolean isFunctionalTest() {
        return this.functionalTest;
    }
}
