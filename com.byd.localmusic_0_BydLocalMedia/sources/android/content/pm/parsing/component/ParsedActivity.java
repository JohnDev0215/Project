package android.content.pm.parsing.component;

import android.app.ActivityTaskManager;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class ParsedActivity extends ParsedMainComponent {
    public static final Parcelable.Creator<ParsedActivity> CREATOR = new Parcelable.Creator<ParsedActivity>() { // from class: android.content.pm.parsing.component.ParsedActivity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedActivity createFromParcel(Parcel parcel) {
            return new ParsedActivity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParsedActivity[] newArray(int i) {
            return new ParsedActivity[i];
        }
    };
    int colorMode;
    int configChanges;
    int documentLaunchMode;
    int launchMode;
    int lockTaskLaunchMode;
    private Float maxAspectRatio;
    int maxRecents;
    private Float minAspectRatio;
    private String parentActivityName;
    private String permission;
    int persistableMode;
    int privateFlags;
    String requestedVrComponent;
    int resizeMode;
    int rotationAnimation;
    int screenOrientation;
    int softInputMode;
    private boolean supportsSizeChanges;
    private String targetActivity;
    String taskAffinity;
    int theme;
    int uiOptions;
    ActivityInfo.WindowLayout windowLayout;

    @Override // android.content.pm.parsing.component.ParsedMainComponent, android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParsedActivity(ParsedActivity parsedActivity) {
        super(parsedActivity);
        this.screenOrientation = -1;
        this.resizeMode = 2;
        this.rotationAnimation = -1;
        this.theme = parsedActivity.theme;
        this.uiOptions = parsedActivity.uiOptions;
        this.targetActivity = parsedActivity.targetActivity;
        this.parentActivityName = parsedActivity.parentActivityName;
        this.taskAffinity = parsedActivity.taskAffinity;
        this.privateFlags = parsedActivity.privateFlags;
        this.permission = parsedActivity.permission;
        this.launchMode = parsedActivity.launchMode;
        this.documentLaunchMode = parsedActivity.documentLaunchMode;
        this.maxRecents = parsedActivity.maxRecents;
        this.configChanges = parsedActivity.configChanges;
        this.softInputMode = parsedActivity.softInputMode;
        this.persistableMode = parsedActivity.persistableMode;
        this.lockTaskLaunchMode = parsedActivity.lockTaskLaunchMode;
        this.screenOrientation = parsedActivity.screenOrientation;
        this.resizeMode = parsedActivity.resizeMode;
        this.maxAspectRatio = parsedActivity.maxAspectRatio;
        this.minAspectRatio = parsedActivity.minAspectRatio;
        this.supportsSizeChanges = parsedActivity.supportsSizeChanges;
        this.requestedVrComponent = parsedActivity.requestedVrComponent;
        this.rotationAnimation = parsedActivity.rotationAnimation;
        this.colorMode = parsedActivity.colorMode;
        this.windowLayout = parsedActivity.windowLayout;
    }

    public static ParsedActivity makeAppDetailsActivity(String str, String str2, int i, String str3, boolean z) {
        ParsedActivity parsedActivity = new ParsedActivity();
        parsedActivity.setPackageName(str);
        parsedActivity.theme = 16973909;
        parsedActivity.exported = true;
        parsedActivity.setName(PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME);
        parsedActivity.setProcessName(str2);
        parsedActivity.uiOptions = i;
        parsedActivity.taskAffinity = str3;
        parsedActivity.launchMode = 0;
        parsedActivity.documentLaunchMode = 0;
        parsedActivity.maxRecents = ActivityTaskManager.getDefaultAppRecentsLimitStatic();
        parsedActivity.configChanges = PackageParser.getActivityConfigChanges(0, 0);
        parsedActivity.softInputMode = 0;
        parsedActivity.persistableMode = 1;
        parsedActivity.screenOrientation = -1;
        parsedActivity.resizeMode = 4;
        parsedActivity.lockTaskLaunchMode = 0;
        parsedActivity.setDirectBootAware(false);
        parsedActivity.rotationAnimation = -1;
        parsedActivity.colorMode = 0;
        if (z) {
            parsedActivity.setFlags(parsedActivity.getFlags() | 512);
        }
        return parsedActivity;
    }

    static ParsedActivity makeAlias(String str, ParsedActivity parsedActivity) {
        ParsedActivity parsedActivity2 = new ParsedActivity();
        parsedActivity2.setPackageName(parsedActivity.getPackageName());
        parsedActivity2.setTargetActivity(str);
        parsedActivity2.configChanges = parsedActivity.configChanges;
        parsedActivity2.flags = parsedActivity.flags;
        parsedActivity2.privateFlags = parsedActivity.privateFlags;
        parsedActivity2.icon = parsedActivity.icon;
        parsedActivity2.logo = parsedActivity.logo;
        parsedActivity2.banner = parsedActivity.banner;
        parsedActivity2.labelRes = parsedActivity.labelRes;
        parsedActivity2.nonLocalizedLabel = parsedActivity.nonLocalizedLabel;
        parsedActivity2.launchMode = parsedActivity.launchMode;
        parsedActivity2.lockTaskLaunchMode = parsedActivity.lockTaskLaunchMode;
        parsedActivity2.descriptionRes = parsedActivity.descriptionRes;
        parsedActivity2.screenOrientation = parsedActivity.screenOrientation;
        parsedActivity2.taskAffinity = parsedActivity.taskAffinity;
        parsedActivity2.theme = parsedActivity.theme;
        parsedActivity2.softInputMode = parsedActivity.softInputMode;
        parsedActivity2.uiOptions = parsedActivity.uiOptions;
        parsedActivity2.parentActivityName = parsedActivity.parentActivityName;
        parsedActivity2.maxRecents = parsedActivity.maxRecents;
        parsedActivity2.windowLayout = parsedActivity.windowLayout;
        parsedActivity2.resizeMode = parsedActivity.resizeMode;
        parsedActivity2.maxAspectRatio = parsedActivity.maxAspectRatio;
        parsedActivity2.minAspectRatio = parsedActivity.minAspectRatio;
        parsedActivity2.supportsSizeChanges = parsedActivity.supportsSizeChanges;
        parsedActivity2.requestedVrComponent = parsedActivity.requestedVrComponent;
        parsedActivity2.directBootAware = parsedActivity.directBootAware;
        parsedActivity2.setProcessName(parsedActivity.getProcessName());
        return parsedActivity2;
    }

    public ParsedActivity setMaxAspectRatio(int i, float f) {
        if (i == 2 || i == 1 || (f < 1.0f && f != 0.0f)) {
            return this;
        }
        this.maxAspectRatio = Float.valueOf(f);
        return this;
    }

    public ParsedActivity setMinAspectRatio(int i, float f) {
        if (i == 2 || i == 1 || (f < 1.0f && f != 0.0f)) {
            return this;
        }
        this.minAspectRatio = Float.valueOf(f);
        return this;
    }

    public ParsedActivity setSupportsSizeChanges(boolean z) {
        this.supportsSizeChanges = z;
        return this;
    }

    public ParsedActivity setFlags(int i) {
        this.flags = i;
        return this;
    }

    public ParsedActivity setResizeMode(int i) {
        this.resizeMode = i;
        return this;
    }

    public ParsedActivity setTargetActivity(String str) {
        this.targetActivity = TextUtils.safeIntern(str);
        return this;
    }

    public ParsedActivity setParentActivity(String str) {
        this.parentActivityName = TextUtils.safeIntern(str);
        return this;
    }

    public ParsedActivity setPermission(String str) {
        this.permission = TextUtils.isEmpty(str) ? null : str.intern();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("Activity{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(' ');
        ComponentName.appendShortString(sb, getPackageName(), getName());
        sb.append('}');
        return sb.toString();
    }

    @Override // android.content.pm.parsing.component.ParsedMainComponent, android.content.pm.parsing.component.ParsedComponent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.theme);
        parcel.writeInt(this.uiOptions);
        parcel.writeString(this.targetActivity);
        parcel.writeString(this.parentActivityName);
        parcel.writeString(this.taskAffinity);
        parcel.writeInt(this.privateFlags);
        ParsingPackageImpl.sForInternedString.parcel(this.permission, parcel, i);
        parcel.writeInt(this.launchMode);
        parcel.writeInt(this.documentLaunchMode);
        parcel.writeInt(this.maxRecents);
        parcel.writeInt(this.configChanges);
        parcel.writeInt(this.softInputMode);
        parcel.writeInt(this.persistableMode);
        parcel.writeInt(this.lockTaskLaunchMode);
        parcel.writeInt(this.screenOrientation);
        parcel.writeInt(this.resizeMode);
        parcel.writeValue(this.maxAspectRatio);
        parcel.writeValue(this.minAspectRatio);
        parcel.writeBoolean(this.supportsSizeChanges);
        parcel.writeString(this.requestedVrComponent);
        parcel.writeInt(this.rotationAnimation);
        parcel.writeInt(this.colorMode);
        parcel.writeBundle(this.metaData);
        if (this.windowLayout != null) {
            parcel.writeInt(1);
            this.windowLayout.writeToParcel(parcel);
        } else {
            parcel.writeBoolean(false);
        }
    }

    public ParsedActivity() {
        this.screenOrientation = -1;
        this.resizeMode = 2;
        this.rotationAnimation = -1;
    }

    protected ParsedActivity(Parcel parcel) {
        super(parcel);
        this.screenOrientation = -1;
        this.resizeMode = 2;
        this.rotationAnimation = -1;
        this.theme = parcel.readInt();
        this.uiOptions = parcel.readInt();
        this.targetActivity = parcel.readString();
        this.parentActivityName = parcel.readString();
        this.taskAffinity = parcel.readString();
        this.privateFlags = parcel.readInt();
        this.permission = ParsingPackageImpl.sForInternedString.unparcel(parcel);
        this.launchMode = parcel.readInt();
        this.documentLaunchMode = parcel.readInt();
        this.maxRecents = parcel.readInt();
        this.configChanges = parcel.readInt();
        this.softInputMode = parcel.readInt();
        this.persistableMode = parcel.readInt();
        this.lockTaskLaunchMode = parcel.readInt();
        this.screenOrientation = parcel.readInt();
        this.resizeMode = parcel.readInt();
        this.maxAspectRatio = (Float) parcel.readValue(Float.class.getClassLoader());
        this.minAspectRatio = (Float) parcel.readValue(Float.class.getClassLoader());
        this.supportsSizeChanges = parcel.readBoolean();
        this.requestedVrComponent = parcel.readString();
        this.rotationAnimation = parcel.readInt();
        this.colorMode = parcel.readInt();
        this.metaData = parcel.readBundle();
        if (parcel.readBoolean()) {
            this.windowLayout = new ActivityInfo.WindowLayout(parcel);
        }
    }

    public int getTheme() {
        return this.theme;
    }

    public int getUiOptions() {
        return this.uiOptions;
    }

    public String getTargetActivity() {
        return this.targetActivity;
    }

    public String getParentActivityName() {
        return this.parentActivityName;
    }

    public String getTaskAffinity() {
        return this.taskAffinity;
    }

    public int getPrivateFlags() {
        return this.privateFlags;
    }

    public String getPermission() {
        return this.permission;
    }

    public int getLaunchMode() {
        return this.launchMode;
    }

    public int getDocumentLaunchMode() {
        return this.documentLaunchMode;
    }

    public int getMaxRecents() {
        return this.maxRecents;
    }

    public int getConfigChanges() {
        return this.configChanges;
    }

    public int getSoftInputMode() {
        return this.softInputMode;
    }

    public int getPersistableMode() {
        return this.persistableMode;
    }

    public int getLockTaskLaunchMode() {
        return this.lockTaskLaunchMode;
    }

    public int getScreenOrientation() {
        return this.screenOrientation;
    }

    public int getResizeMode() {
        return this.resizeMode;
    }

    public Float getMaxAspectRatio() {
        return this.maxAspectRatio;
    }

    public Float getMinAspectRatio() {
        return this.minAspectRatio;
    }

    public boolean getSupportsSizeChanges() {
        return this.supportsSizeChanges;
    }

    public String getRequestedVrComponent() {
        return this.requestedVrComponent;
    }

    public int getRotationAnimation() {
        return this.rotationAnimation;
    }

    public int getColorMode() {
        return this.colorMode;
    }

    public ActivityInfo.WindowLayout getWindowLayout() {
        return this.windowLayout;
    }
}
