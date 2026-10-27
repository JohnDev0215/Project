package android.app.timezonedetector;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ShellCommand;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import android.text.format.DateFormat;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class TelephonyTimeZoneSuggestion implements Parcelable {
    public static final Parcelable.Creator<TelephonyTimeZoneSuggestion> CREATOR = new Parcelable.Creator<TelephonyTimeZoneSuggestion>() { // from class: android.app.timezonedetector.TelephonyTimeZoneSuggestion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TelephonyTimeZoneSuggestion createFromParcel(Parcel parcel) {
            return TelephonyTimeZoneSuggestion.createFromParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TelephonyTimeZoneSuggestion[] newArray(int i) {
            return new TelephonyTimeZoneSuggestion[i];
        }
    };
    public static final int MATCH_TYPE_EMULATOR_ZONE_ID = 4;
    public static final int MATCH_TYPE_NA = 0;
    public static final int MATCH_TYPE_NETWORK_COUNTRY_AND_OFFSET = 3;
    public static final int MATCH_TYPE_NETWORK_COUNTRY_ONLY = 2;
    public static final int MATCH_TYPE_TEST_NETWORK_OFFSET_ONLY = 5;
    public static final int QUALITY_MULTIPLE_ZONES_WITH_DIFFERENT_OFFSETS = 3;
    public static final int QUALITY_MULTIPLE_ZONES_WITH_SAME_OFFSET = 2;
    public static final int QUALITY_NA = 0;
    public static final int QUALITY_SINGLE_ZONE = 1;
    private List<String> mDebugInfo;
    private final int mMatchType;
    private final int mQuality;
    private final int mSlotIndex;
    private final String mZoneId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MatchType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Quality {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static TelephonyTimeZoneSuggestion createEmptySuggestion(int i, String str) {
        return new Builder(i).addDebugInfo(str).build();
    }

    private TelephonyTimeZoneSuggestion(Builder builder) {
        this.mSlotIndex = builder.mSlotIndex;
        this.mZoneId = builder.mZoneId;
        this.mMatchType = builder.mMatchType;
        this.mQuality = builder.mQuality;
        this.mDebugInfo = builder.mDebugInfo != null ? new ArrayList(builder.mDebugInfo) : null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TelephonyTimeZoneSuggestion createFromParcel(Parcel parcel) {
        TelephonyTimeZoneSuggestion telephonyTimeZoneSuggestionBuild = new Builder(parcel.readInt()).setZoneId(parcel.readString()).setMatchType(parcel.readInt()).setQuality(parcel.readInt()).build();
        ArrayList arrayList = parcel.readArrayList(TelephonyTimeZoneSuggestion.class.getClassLoader());
        if (arrayList != null) {
            telephonyTimeZoneSuggestionBuild.addDebugInfo(arrayList);
        }
        return telephonyTimeZoneSuggestionBuild;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mSlotIndex);
        parcel.writeString(this.mZoneId);
        parcel.writeInt(this.mMatchType);
        parcel.writeInt(this.mQuality);
        parcel.writeList(this.mDebugInfo);
    }

    public int getSlotIndex() {
        return this.mSlotIndex;
    }

    public String getZoneId() {
        return this.mZoneId;
    }

    public int getMatchType() {
        return this.mMatchType;
    }

    public int getQuality() {
        return this.mQuality;
    }

    public List<String> getDebugInfo() {
        List<String> list = this.mDebugInfo;
        return list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    public void addDebugInfo(String str) {
        if (this.mDebugInfo == null) {
            this.mDebugInfo = new ArrayList();
        }
        this.mDebugInfo.add(str);
    }

    public void addDebugInfo(List<String> list) {
        if (this.mDebugInfo == null) {
            this.mDebugInfo = new ArrayList(list.size());
        }
        this.mDebugInfo.addAll(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TelephonyTimeZoneSuggestion telephonyTimeZoneSuggestion = (TelephonyTimeZoneSuggestion) obj;
        return this.mSlotIndex == telephonyTimeZoneSuggestion.mSlotIndex && this.mMatchType == telephonyTimeZoneSuggestion.mMatchType && this.mQuality == telephonyTimeZoneSuggestion.mQuality && Objects.equals(this.mZoneId, telephonyTimeZoneSuggestion.mZoneId);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mSlotIndex), this.mZoneId, Integer.valueOf(this.mMatchType), Integer.valueOf(this.mQuality));
    }

    public String toString() {
        return "TelephonyTimeZoneSuggestion{mSlotIndex=" + this.mSlotIndex + ", mZoneId='" + this.mZoneId + DateFormat.QUOTE + ", mMatchType=" + this.mMatchType + ", mQuality=" + this.mQuality + ", mDebugInfo=" + this.mDebugInfo + '}';
    }

    public static final class Builder {
        private List<String> mDebugInfo;
        private int mMatchType;
        private int mQuality;
        private final int mSlotIndex;
        private String mZoneId;

        public Builder(int i) {
            this.mSlotIndex = i;
        }

        public Builder setZoneId(String str) {
            this.mZoneId = str;
            return this;
        }

        public Builder setMatchType(int i) {
            this.mMatchType = i;
            return this;
        }

        public Builder setQuality(int i) {
            this.mQuality = i;
            return this;
        }

        public Builder addDebugInfo(String str) {
            if (this.mDebugInfo == null) {
                this.mDebugInfo = new ArrayList();
            }
            this.mDebugInfo.add(str);
            return this;
        }

        void validate() {
            int i = this.mQuality;
            int i2 = this.mMatchType;
            if (this.mZoneId == null) {
                if (i == 0 && i2 == 0) {
                    return;
                }
                throw new RuntimeException("Invalid quality or match type for null zone ID. quality=" + i + ", matchType=" + i2);
            }
            boolean z = i == 1 || i == 2 || i == 3;
            boolean z2 = i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5;
            if (z && z2) {
                return;
            }
            throw new RuntimeException("Invalid quality or match type with zone ID. quality=" + i + ", matchType=" + i2);
        }

        public TelephonyTimeZoneSuggestion build() {
            validate();
            return new TelephonyTimeZoneSuggestion(this);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static TelephonyTimeZoneSuggestion parseCommandLineArg(ShellCommand shellCommand) throws IllegalArgumentException {
        Integer numValueOf = null;
        String nextArgRequired = null;
        Integer numValueOf2 = null;
        Integer numValueOf3 = null;
        while (true) {
            String nextArg = shellCommand.getNextArg();
            if (nextArg == null) {
                if (numValueOf == null) {
                    throw new IllegalArgumentException("No slotIndex specified.");
                }
                Builder builder = new Builder(numValueOf.intValue());
                if (!TextUtils.isEmpty(nextArgRequired) && !Session.SESSION_SEPARATION_CHAR_CHILD.equals(nextArgRequired)) {
                    builder.setZoneId(nextArgRequired);
                }
                if (numValueOf2 != null) {
                    builder.setQuality(numValueOf2.intValue());
                }
                if (numValueOf3 != null) {
                    builder.setMatchType(numValueOf3.intValue());
                }
                builder.addDebugInfo("Command line injection");
                return builder.build();
            }
            byte b = -1;
            switch (nextArg.hashCode()) {
                case -174375148:
                    if (nextArg.equals("--match_type")) {
                        b = 3;
                    }
                    break;
                case 1274807534:
                    if (nextArg.equals("--zone_id")) {
                        b = 1;
                    }
                    break;
                case 2037196639:
                    if (nextArg.equals("--quality")) {
                        b = 2;
                    }
                    break;
                case 2069298417:
                    if (nextArg.equals("--slot_index")) {
                        b = 0;
                    }
                    break;
            }
            if (b == 0) {
                numValueOf = Integer.valueOf(Integer.parseInt(shellCommand.getNextArgRequired()));
            } else if (b == 1) {
                nextArgRequired = shellCommand.getNextArgRequired();
            } else if (b == 2) {
                numValueOf2 = Integer.valueOf(parseQualityCommandLineArg(shellCommand.getNextArgRequired()));
            } else if (b == 3) {
                numValueOf3 = Integer.valueOf(parseMatchTypeCommandLineArg(shellCommand.getNextArgRequired()));
            } else {
                throw new IllegalArgumentException("Unknown option: " + nextArg);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int parseQualityCommandLineArg(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = -902265784(0xffffffffca388448, float:-3023122.0)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L2c
            r1 = -650306251(0xffffffffd93d1d35, float:-3.3269303E15)
            if (r0 == r1) goto L21
            r1 = 1611832522(0x60129cca, float:4.2258164E19)
            if (r0 == r1) goto L16
            goto L37
        L16:
            java.lang.String r0 = "multiple_different"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L37
            r0 = r2
            goto L38
        L21:
            java.lang.String r0 = "multiple_same"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L37
            r0 = r3
            goto L38
        L2c:
            java.lang.String r0 = "single"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L37
            r0 = 0
            goto L38
        L37:
            r0 = -1
        L38:
            if (r0 == 0) goto L58
            if (r0 == r3) goto L57
            if (r0 != r2) goto L40
            r4 = 3
            return r4
        L40:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unrecognized quality: "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.<init>(r4)
            throw r0
        L57:
            return r2
        L58:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.timezonedetector.TelephonyTimeZoneSuggestion.parseQualityCommandLineArg(java.lang.String):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int parseMatchTypeCommandLineArg(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 1
            r2 = 3
            r3 = 2
            switch(r0) {
                case -1592694013: goto L2a;
                case 556438401: goto L1f;
                case 957831062: goto L15;
                case 1336193813: goto Lb;
                default: goto La;
            }
        La:
            goto L34
        Lb:
            java.lang.String r0 = "emulator"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L34
            r0 = 0
            goto L35
        L15:
            java.lang.String r0 = "country"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L34
            r0 = r3
            goto L35
        L1f:
            java.lang.String r0 = "test_network"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L34
            r0 = r2
            goto L35
        L2a:
            java.lang.String r0 = "country_with_offset"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L34
            r0 = r1
            goto L35
        L34:
            r0 = -1
        L35:
            if (r0 == 0) goto L58
            if (r0 == r1) goto L57
            if (r0 == r3) goto L56
            if (r0 != r2) goto L3f
            r4 = 5
            return r4
        L3f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unrecognized match_type: "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.<init>(r4)
            throw r0
        L56:
            return r3
        L57:
            return r2
        L58:
            r4 = 4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.timezonedetector.TelephonyTimeZoneSuggestion.parseMatchTypeCommandLineArg(java.lang.String):int");
    }

    public static void printCommandLineOpts(PrintWriter printWriter) {
        printWriter.println("Telephony suggestion options:");
        printWriter.println("  --slot_index <number>");
        printWriter.println("  To withdraw a previous suggestion:");
        printWriter.println("    [--zone_id \"_\"]");
        printWriter.println("  To make a new suggestion:");
        printWriter.println("    --zone_id <Olson ID>");
        printWriter.println("    --quality <single|multiple_same|multiple_different>");
        printWriter.println("    --match_type <emulator|country_with_offset|country|test_network>");
        printWriter.println();
        printWriter.println("See " + TelephonyTimeZoneSuggestion.class.getName() + " for more information");
    }
}
