package com.ts.carplay.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RouteGuidanceInfo implements Parcelable {
    public static final Parcelable.Creator<RouteGuidanceInfo> CREATOR = new Parcelable.Creator<RouteGuidanceInfo>() { // from class: com.ts.carplay.common.data.RouteGuidanceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteGuidanceInfo createFromParcel(Parcel source) {
            RouteGuidanceInfo info = new RouteGuidanceInfo(source);
            return info;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RouteGuidanceInfo[] newArray(int size) {
            return new RouteGuidanceInfo[size];
        }
    };
    public static final int DISTANCE_DISPLAY_NOT_PRESENT = -1;
    public static final int DISTANCE_DISPLAY_UNITS_FT = 4;
    public static final int DISTANCE_DISPLAY_UNITS_KM = 0;
    public static final int DISTANCE_DISPLAY_UNITS_M = 2;
    public static final int DISTANCE_DISPLAY_UNITS_MILES = 1;
    public static final int DISTANCE_DISPLAY_UNITS_YARDS = 3;
    public static final int JUNCTION_EXIT_ANGLE_DEFAULT = 181;
    public static final int MANEUVER_STATE_CONTINUE = 0;
    public static final int MANEUVER_STATE_EXECUTE = 3;
    public static final int MANEUVER_STATE_IAITIAL = 1;
    public static final int MANEUVER_STATE_NOT_PRESENT = -1;
    public static final int MANEUVER_STATE_PREPARE = 2;
    public static final int MANEUVER_TYPE_0 = 0;
    public static final int MANEUVER_TYPE_1 = 1;
    public static final int MANEUVER_TYPE_10 = 10;
    public static final int MANEUVER_TYPE_11 = 11;
    public static final int MANEUVER_TYPE_12 = 12;
    public static final int MANEUVER_TYPE_13 = 13;
    public static final int MANEUVER_TYPE_14 = 14;
    public static final int MANEUVER_TYPE_15 = 15;
    public static final int MANEUVER_TYPE_16 = 16;
    public static final int MANEUVER_TYPE_17 = 17;
    public static final int MANEUVER_TYPE_18 = 18;
    public static final int MANEUVER_TYPE_19 = 19;
    public static final int MANEUVER_TYPE_2 = 2;
    public static final int MANEUVER_TYPE_20 = 20;
    public static final int MANEUVER_TYPE_21 = 21;
    public static final int MANEUVER_TYPE_22 = 22;
    public static final int MANEUVER_TYPE_23 = 23;
    public static final int MANEUVER_TYPE_24 = 24;
    public static final int MANEUVER_TYPE_25 = 25;
    public static final int MANEUVER_TYPE_26 = 26;
    public static final int MANEUVER_TYPE_27 = 27;
    public static final int MANEUVER_TYPE_28 = 28;
    public static final int MANEUVER_TYPE_29 = 29;
    public static final int MANEUVER_TYPE_3 = 3;
    public static final int MANEUVER_TYPE_30 = 30;
    public static final int MANEUVER_TYPE_31 = 31;
    public static final int MANEUVER_TYPE_32 = 32;
    public static final int MANEUVER_TYPE_33 = 33;
    public static final int MANEUVER_TYPE_34 = 34;
    public static final int MANEUVER_TYPE_35 = 35;
    public static final int MANEUVER_TYPE_36 = 36;
    public static final int MANEUVER_TYPE_37 = 37;
    public static final int MANEUVER_TYPE_38 = 38;
    public static final int MANEUVER_TYPE_39 = 39;
    public static final int MANEUVER_TYPE_4 = 4;
    public static final int MANEUVER_TYPE_40 = 40;
    public static final int MANEUVER_TYPE_41 = 41;
    public static final int MANEUVER_TYPE_42 = 42;
    public static final int MANEUVER_TYPE_43 = 43;
    public static final int MANEUVER_TYPE_44 = 44;
    public static final int MANEUVER_TYPE_45 = 45;
    public static final int MANEUVER_TYPE_46 = 46;
    public static final int MANEUVER_TYPE_47 = 47;
    public static final int MANEUVER_TYPE_48 = 48;
    public static final int MANEUVER_TYPE_49 = 49;
    public static final int MANEUVER_TYPE_5 = 5;
    public static final int MANEUVER_TYPE_50 = 50;
    public static final int MANEUVER_TYPE_51 = 51;
    public static final int MANEUVER_TYPE_52 = 52;
    public static final int MANEUVER_TYPE_53 = 53;
    public static final int MANEUVER_TYPE_6 = 6;
    public static final int MANEUVER_TYPE_7 = 7;
    public static final int MANEUVER_TYPE_8 = 8;
    public static final int MANEUVER_TYPE_9 = 9;
    public static final int ROUTE_GUIDANCE_STATE_ARRIVED = 2;
    public static final int ROUTE_GUIDANCE_STATE_LOADING = 3;
    public static final int ROUTE_GUIDANCE_STATE_LOCATING = 4;
    public static final int ROUTE_GUIDANCE_STATE_NOT_PRESENT = -1;
    public static final int ROUTE_GUIDANCE_STATE_NO_ROUTE = 0;
    public static final int ROUTE_GUIDANCE_STATE_PRECEED_TO_ROUTE = 6;
    public static final int ROUTE_GUIDANCE_STATE_REROUTING = 5;
    public static final int ROUTE_GUIDANCE_STATE_ROUTE_SET = 1;
    private String mAfterManeuverRoadName;
    private String mCurrentRoadName;
    private String mDestinationName;
    private int mDestinationTimeZoneOffsetMinutes;
    private long mDistanceBetweenManeuver;
    private String mDistanceBetweenManeuverDisplayStr;
    private int mDistanceBetweenManeuverDisplayUnits;
    private long mDistanceRemaining;
    private String mDistanceRemainingDisplayStr;
    private int mDistanceRemainingDisplayUnits;
    private long mDistanceToNextManeuver;
    private String mDistanceToNextManeuverDisplayStr;
    private int mDistanceToNextManeuverDisplayUnits;
    private int mDrivingSide;
    private long mEstimatedTimeOfArrival;
    private String mExitInfo;
    private String mInstructionText;
    private List<Integer> mJunctionElementAngles;
    private int mJunctionElementExitAngle;
    private int mJunctionType;
    private boolean mLaneGuidanceBeingShowInApp;
    private int mLaneGuidanceTotalCount;
    private List<LaneInformation> mLaneInformationList;
    private String mManeuverDescription;
    private int mManeuverGuidanceTotalCount;
    private int mManeuverState;
    private int mManeuverType;
    private int mRouteGuidanceState;
    private boolean mRouteGuidanceVisibleInApp;
    private String mSourceName;
    private int mSourceSupportsRouteGuidance;
    private long mTimeRemainingToDestination;

    public static final class DrivingSide {
        public static final int DRIVING_SIDE_NOT_LEFT = 1;
        public static final int DRIVING_SIDE_NOT_PRESENT = -1;
        public static final int DRIVING_SIDE_NOT_RIGHT = 0;
    }

    public static final class JunctionType {
        public static final int JUNCTION_TYPE_COMMON = 0;
        public static final int JUNCTION_TYPE_NOT_PRESENT = -1;
        public static final int JUNCTION_TYPE_ROUNDABOUT = 1;
    }

    public RouteGuidanceInfo() {
        this.mManeuverType = -1;
        this.mDistanceToNextManeuverDisplayStr = "";
        this.mDistanceToNextManeuverDisplayUnits = -1;
        this.mAfterManeuverRoadName = "";
        this.mDistanceRemainingDisplayStr = "";
        this.mDistanceRemainingDisplayUnits = -1;
        this.mTimeRemainingToDestination = -1L;
        this.mRouteGuidanceState = -1;
        this.mJunctionType = -1;
        this.mDrivingSide = -1;
        this.mJunctionElementExitAngle = 181;
        this.mCurrentRoadName = "";
        this.mDestinationName = "";
        this.mDistanceToNextManeuver = -1L;
        this.mDistanceRemaining = -1L;
        this.mRouteGuidanceVisibleInApp = false;
        this.mLaneGuidanceBeingShowInApp = false;
        this.mManeuverState = -1;
        this.mManeuverDescription = "";
        this.mEstimatedTimeOfArrival = -1L;
        this.mManeuverGuidanceTotalCount = -1;
        this.mLaneGuidanceTotalCount = -1;
        this.mJunctionElementAngles = new ArrayList();
        this.mDistanceBetweenManeuver = -1L;
        this.mDistanceBetweenManeuverDisplayStr = "";
        this.mDistanceBetweenManeuverDisplayUnits = -1;
        this.mLaneInformationList = new ArrayList();
        this.mInstructionText = "";
        this.mSourceName = "";
        this.mSourceSupportsRouteGuidance = -1;
        this.mExitInfo = "";
        this.mDestinationTimeZoneOffsetMinutes = -1;
    }

    public void clear() {
        this.mManeuverType = -1;
        this.mDistanceToNextManeuverDisplayStr = "";
        this.mDistanceToNextManeuverDisplayUnits = -1;
        this.mAfterManeuverRoadName = "";
        this.mDistanceRemainingDisplayStr = "";
        this.mDistanceRemainingDisplayUnits = -1;
        this.mTimeRemainingToDestination = -1L;
        this.mRouteGuidanceState = -1;
        this.mJunctionType = -1;
        this.mDrivingSide = -1;
        this.mJunctionElementExitAngle = 181;
        this.mJunctionElementAngles = new ArrayList();
        this.mDistanceBetweenManeuver = -1L;
        this.mDistanceBetweenManeuverDisplayStr = "";
        this.mDistanceBetweenManeuverDisplayUnits = -1;
        this.mCurrentRoadName = "";
        this.mDestinationName = "";
        this.mDistanceToNextManeuver = -1L;
        this.mDistanceRemaining = -1L;
        this.mRouteGuidanceVisibleInApp = false;
        this.mLaneGuidanceBeingShowInApp = false;
        this.mManeuverState = -1;
        this.mManeuverDescription = "";
        this.mEstimatedTimeOfArrival = -1L;
        this.mManeuverGuidanceTotalCount = -1;
        this.mLaneGuidanceTotalCount = -1;
        this.mLaneInformationList = new ArrayList();
        this.mInstructionText = "";
        this.mSourceName = "";
        this.mSourceSupportsRouteGuidance = -1;
        this.mExitInfo = "";
        this.mDestinationTimeZoneOffsetMinutes = -1;
    }

    public void clearManeuverInfo() {
        this.mManeuverDescription = "";
        this.mManeuverType = -1;
        this.mAfterManeuverRoadName = "";
        this.mDistanceBetweenManeuver = -1L;
        this.mDistanceBetweenManeuverDisplayStr = "";
        this.mDistanceBetweenManeuverDisplayUnits = -1;
        this.mJunctionType = -1;
        this.mDrivingSide = -1;
        this.mJunctionElementExitAngle = 181;
        this.mJunctionElementAngles = new ArrayList();
    }

    public RouteGuidanceInfo(Parcel in) {
        this.mManeuverType = in.readInt();
        this.mDistanceToNextManeuverDisplayStr = in.readString();
        this.mDistanceToNextManeuverDisplayUnits = in.readInt();
        this.mAfterManeuverRoadName = in.readString();
        this.mDistanceRemainingDisplayStr = in.readString();
        this.mDistanceRemainingDisplayUnits = in.readInt();
        this.mTimeRemainingToDestination = in.readLong();
        this.mRouteGuidanceState = in.readInt();
        this.mJunctionType = in.readInt();
        this.mDrivingSide = in.readInt();
        this.mJunctionElementExitAngle = in.readInt();
        ArrayList arrayList = new ArrayList();
        this.mJunctionElementAngles = arrayList;
        in.readList(arrayList, Integer.class.getClassLoader());
        this.mDistanceBetweenManeuver = in.readLong();
        this.mDistanceBetweenManeuverDisplayStr = in.readString();
        this.mDistanceBetweenManeuverDisplayUnits = in.readInt();
        this.mCurrentRoadName = in.readString();
        this.mDestinationName = in.readString();
        this.mDistanceToNextManeuver = in.readLong();
        this.mDistanceRemaining = in.readLong();
        this.mRouteGuidanceVisibleInApp = in.readByte() != 0;
        this.mLaneGuidanceBeingShowInApp = in.readByte() != 0;
        this.mManeuverState = in.readInt();
        this.mManeuverDescription = in.readString();
        this.mEstimatedTimeOfArrival = in.readLong();
        this.mManeuverGuidanceTotalCount = in.readInt();
        this.mLaneGuidanceTotalCount = in.readInt();
        ArrayList arrayList2 = new ArrayList();
        this.mLaneInformationList = arrayList2;
        in.readTypedList(arrayList2, LaneInformation.CREATOR);
        this.mInstructionText = in.readString();
        this.mSourceName = in.readString();
        this.mSourceSupportsRouteGuidance = in.readInt();
        this.mExitInfo = in.readString();
        this.mDestinationTimeZoneOffsetMinutes = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mManeuverType);
        parcel.writeString(this.mDistanceToNextManeuverDisplayStr);
        parcel.writeInt(this.mDistanceToNextManeuverDisplayUnits);
        parcel.writeString(this.mAfterManeuverRoadName);
        parcel.writeString(this.mDistanceRemainingDisplayStr);
        parcel.writeInt(this.mDistanceRemainingDisplayUnits);
        parcel.writeLong(this.mTimeRemainingToDestination);
        parcel.writeInt(this.mRouteGuidanceState);
        parcel.writeInt(this.mJunctionType);
        parcel.writeInt(this.mDrivingSide);
        parcel.writeInt(this.mJunctionElementExitAngle);
        parcel.writeList(this.mJunctionElementAngles);
        parcel.writeLong(this.mDistanceBetweenManeuver);
        parcel.writeString(this.mDistanceBetweenManeuverDisplayStr);
        parcel.writeInt(this.mDistanceBetweenManeuverDisplayUnits);
        parcel.writeString(this.mCurrentRoadName);
        parcel.writeString(this.mDestinationName);
        parcel.writeLong(this.mDistanceToNextManeuver);
        parcel.writeLong(this.mDistanceRemaining);
        parcel.writeByte(this.mRouteGuidanceVisibleInApp ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mLaneGuidanceBeingShowInApp ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mManeuverState);
        parcel.writeString(this.mManeuverDescription);
        parcel.writeLong(this.mEstimatedTimeOfArrival);
        parcel.writeInt(this.mManeuverGuidanceTotalCount);
        parcel.writeInt(this.mLaneGuidanceTotalCount);
        parcel.writeTypedList(this.mLaneInformationList);
        parcel.writeString(this.mInstructionText);
        parcel.writeString(this.mSourceName);
        parcel.writeInt(this.mSourceSupportsRouteGuidance);
        parcel.writeString(this.mExitInfo);
        parcel.writeInt(this.mDestinationTimeZoneOffsetMinutes);
    }

    public void copy(RouteGuidanceInfo info) {
        if (info != null) {
            this.mManeuverType = info.getManeuverType();
            this.mDistanceToNextManeuverDisplayStr = info.getDistanceToNextManeuverDisplayStr();
            this.mDistanceToNextManeuverDisplayUnits = info.getDistanceToNextManeuverDisplayUnits();
            this.mAfterManeuverRoadName = info.getAfterManeuverRoadName();
            this.mDistanceRemainingDisplayStr = info.getDistanceRemainingDisplayStr();
            this.mDistanceRemainingDisplayUnits = info.getDistanceRemainingDisplayUnits();
            this.mTimeRemainingToDestination = info.getTimeRemainingToDestination();
            this.mRouteGuidanceState = info.getRouteGuidanceState();
            this.mJunctionType = info.getJunctionType();
            this.mDrivingSide = info.getDrivingSide();
            this.mJunctionElementExitAngle = info.getJunctionElementExitAngle();
            this.mJunctionElementAngles = info.getJunctionElementAngles();
            this.mDistanceBetweenManeuver = info.getDistanceBetweenManeuver();
            this.mDistanceBetweenManeuverDisplayStr = info.getDistanceBetweenManeuverDisplayStr();
            this.mDistanceBetweenManeuverDisplayUnits = info.getDistanceBetweenManeuverDisplayUnits();
            this.mCurrentRoadName = info.getCurrentRoadName();
            this.mDestinationName = info.getDestinationName();
            this.mDistanceToNextManeuver = info.getDistanceToNextManeuver();
            this.mDistanceRemaining = info.getDistanceRemaining();
            this.mRouteGuidanceVisibleInApp = info.isRouteGuidanceVisibleInApp();
            this.mLaneGuidanceBeingShowInApp = info.isLaneGuidanceBeingShowInApp();
            this.mManeuverState = info.getManeuverState();
            this.mManeuverDescription = info.getManeuverDescription();
            this.mEstimatedTimeOfArrival = info.getEstimatedTimeOfArrival();
            this.mManeuverGuidanceTotalCount = info.getManeuverGuidanceTotalCount();
            this.mLaneGuidanceTotalCount = info.getLaneGuidanceTotalCount();
            this.mLaneInformationList = info.getLaneInformationList();
            this.mInstructionText = info.getInstructionText();
            this.mSourceName = info.getSourceName();
            this.mSourceSupportsRouteGuidance = info.getSourceSupportsRouteGuidance();
            this.mExitInfo = info.getExitInfo();
            this.mDestinationTimeZoneOffsetMinutes = info.getDestinationTimeZoneOffsetMinutes();
        }
    }

    public boolean isDefaultValue() {
        if (checkRouteGuidanceUpdateInfo() && checkManeuverGuidanceInfo()) {
            return true;
        }
        return false;
    }

    private boolean checkRouteGuidanceUpdateInfo() {
        if (this.mRouteGuidanceState != -1 || this.mManeuverState != -1 || !TextUtils.isEmpty(this.mCurrentRoadName) || !TextUtils.isEmpty(this.mDestinationName) || this.mEstimatedTimeOfArrival != -1 || this.mTimeRemainingToDestination != -1 || this.mDistanceRemaining != -1 || !TextUtils.isEmpty(this.mDistanceRemainingDisplayStr) || this.mDistanceRemainingDisplayUnits != -1 || this.mDistanceToNextManeuver != -1 || !TextUtils.isEmpty(this.mDistanceToNextManeuverDisplayStr) || this.mDistanceToNextManeuverDisplayUnits != -1 || this.mRouteGuidanceVisibleInApp || this.mLaneGuidanceBeingShowInApp || this.mManeuverGuidanceTotalCount != -1 || this.mLaneGuidanceTotalCount != -1 || this.mSourceName != "" || this.mSourceSupportsRouteGuidance != -1 || this.mExitInfo != "" || this.mDestinationTimeZoneOffsetMinutes != -1) {
            return false;
        }
        return true;
    }

    private boolean checkManeuverGuidanceInfo() {
        if (!TextUtils.isEmpty(this.mManeuverDescription) || this.mManeuverType != -1 || !TextUtils.isEmpty(this.mAfterManeuverRoadName) || this.mDistanceBetweenManeuver != -1 || !TextUtils.isEmpty(this.mDistanceBetweenManeuverDisplayStr) || this.mDistanceBetweenManeuverDisplayUnits != -1 || this.mJunctionType != -1 || this.mDrivingSide != -1 || this.mJunctionElementExitAngle != 181 || this.mJunctionElementAngles.size() != 0 || this.mLaneInformationList.size() != 0 || !TextUtils.isEmpty(this.mInstructionText)) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("RouteGuidanceInfo{");
        sb.append("mManeuverDescription = ");
        sb.append(this.mManeuverDescription);
        sb.append(" ");
        sb.append("mManeuverType = ");
        sb.append(this.mManeuverType);
        sb.append(" ");
        sb.append("mDistanceBetweenManeuver = ");
        sb.append(this.mDistanceBetweenManeuver);
        sb.append(" ");
        sb.append("mDistanceBetweenManeuverDisplayStr = ");
        sb.append(this.mDistanceBetweenManeuverDisplayStr);
        sb.append(" ");
        sb.append("mDistanceBetweenManeuverDisplayUnits = ");
        sb.append(this.mDistanceBetweenManeuverDisplayUnits);
        sb.append(" ");
        sb.append("mAfterManeuverRoadName = ");
        sb.append(this.mAfterManeuverRoadName);
        sb.append(" ");
        sb.append("mDistanceRemaining = ");
        sb.append(this.mDistanceRemaining);
        sb.append(" ");
        sb.append("mDistanceRemainingDisplayStr = ");
        sb.append(this.mDistanceRemainingDisplayStr);
        sb.append(" ");
        sb.append("mDistanceRemainingDisplayUnits = ");
        sb.append(this.mDistanceRemainingDisplayUnits);
        sb.append(" ");
        sb.append("mDistanceToNextManeuver = ");
        sb.append(this.mDistanceToNextManeuver);
        sb.append(" ");
        sb.append("mDistanceToNextManeuverDisplayStr = ");
        sb.append(this.mDistanceToNextManeuverDisplayStr);
        sb.append(" ");
        sb.append("mDistanceToNextManeuverDisplayUnits = ");
        sb.append(this.mDistanceToNextManeuverDisplayUnits);
        sb.append(" ");
        sb.append("mEstimatedTimeOfArrival = ");
        sb.append(this.mEstimatedTimeOfArrival);
        sb.append(" ");
        sb.append("mTimeRemainingToDestination = ");
        sb.append(this.mTimeRemainingToDestination);
        sb.append(" ");
        sb.append("mRouteGuidanceState = ");
        sb.append(this.mRouteGuidanceState);
        sb.append(" ");
        sb.append("mManeuverState = ");
        sb.append(this.mManeuverState);
        sb.append(" ");
        sb.append("mCurrentRoadName = ");
        sb.append(this.mCurrentRoadName);
        sb.append(" ");
        sb.append("mDestinationName = ");
        sb.append(this.mDestinationName);
        sb.append(" ");
        sb.append("mJunctionType = ");
        sb.append(this.mJunctionType);
        sb.append(" ");
        sb.append("mDrivingSide = ");
        sb.append(this.mDrivingSide);
        sb.append(" ");
        sb.append("mJunctionElementAngles = ");
        sb.append(this.mJunctionElementAngles);
        sb.append(" ");
        sb.append("mJunctionElementExitAngle = ");
        sb.append(this.mJunctionElementExitAngle);
        sb.append(" ");
        sb.append("mLaneInformationList = ");
        sb.append(this.mLaneInformationList);
        sb.append(" ");
        sb.append("mInstructionText = ");
        sb.append(this.mInstructionText);
        sb.append(" ");
        sb.append("mManeuverGuidanceTotalCount = ");
        sb.append(this.mManeuverGuidanceTotalCount);
        sb.append(" ");
        sb.append("mRouteGuidanceVisibleInApp = ");
        sb.append(this.mRouteGuidanceVisibleInApp);
        sb.append(" ");
        sb.append("mLaneGuidanceTotalCount = ");
        sb.append(this.mLaneGuidanceTotalCount);
        sb.append(" ");
        sb.append("mLaneGuidanceBeingShowInApp = ");
        sb.append(this.mLaneGuidanceBeingShowInApp);
        sb.append(" ");
        sb.append("mSourceName = ");
        sb.append(this.mSourceName);
        sb.append(" ");
        sb.append("mSourceSupportsRouteGuidance = ");
        sb.append(this.mSourceSupportsRouteGuidance);
        sb.append(" ");
        sb.append("mExitInfo = ");
        sb.append(this.mExitInfo);
        sb.append(" ");
        sb.append("mDestinationTimeZoneOffsetMinutes = ");
        sb.append(this.mDestinationTimeZoneOffsetMinutes);
        sb.append("}");
        return sb.toString();
    }

    public int getManeuverState() {
        return this.mManeuverState;
    }

    public void setManeuverState(int maneuverState) {
        this.mManeuverState = maneuverState;
    }

    public String getManeuverDescription() {
        return this.mManeuverDescription;
    }

    public void setManeuverDescription(String maneuverDescription) {
        this.mManeuverDescription = maneuverDescription;
    }

    public long getEstimatedTimeOfArrival() {
        return this.mEstimatedTimeOfArrival;
    }

    public void setEstimatedTimeOfArrival(long estimatedTimeOfArrival) {
        this.mEstimatedTimeOfArrival = estimatedTimeOfArrival;
    }

    public int getManeuverGuidanceTotalCount() {
        return this.mManeuverGuidanceTotalCount;
    }

    public void setManeuverGuidanceTotalCount(int maneuverGuidanceTotalCount) {
        this.mManeuverGuidanceTotalCount = maneuverGuidanceTotalCount;
    }

    public int getLaneGuidanceTotalCount() {
        return this.mLaneGuidanceTotalCount;
    }

    public void setLaneGuidanceTotalCount(int laneGuidanceTotalCount) {
        this.mLaneGuidanceTotalCount = laneGuidanceTotalCount;
    }

    public long getDistanceBetweenManeuver() {
        return this.mDistanceBetweenManeuver;
    }

    public void setDistanceBetweenManeuver(long distanceBetweenManeuver) {
        this.mDistanceBetweenManeuver = distanceBetweenManeuver;
    }

    public String getDistanceBetweenManeuverDisplayStr() {
        return this.mDistanceBetweenManeuverDisplayStr;
    }

    public void setDistanceBetweenManeuverDisplayStr(String distanceBetweenManeuverDisplayStr) {
        this.mDistanceBetweenManeuverDisplayStr = distanceBetweenManeuverDisplayStr;
    }

    public int getDistanceBetweenManeuverDisplayUnits() {
        return this.mDistanceBetweenManeuverDisplayUnits;
    }

    public void setDistanceBetweenManeuverDisplayUnits(int distanceBetweenManeuverDisplayUnits) {
        this.mDistanceBetweenManeuverDisplayUnits = distanceBetweenManeuverDisplayUnits;
    }

    public List<Integer> getJunctionElementAngles() {
        return this.mJunctionElementAngles;
    }

    public void setJunctionElementAngles(List<Integer> junctionElementAngles) {
        this.mJunctionElementAngles = junctionElementAngles;
    }

    public String getCurrentRoadName() {
        return this.mCurrentRoadName;
    }

    public void setCurrentRoadName(String name) {
        this.mCurrentRoadName = name;
    }

    public String getDestinationName() {
        return this.mDestinationName;
    }

    public void setDestinationName(String destinationName) {
        this.mDestinationName = destinationName;
    }

    public long getDistanceToNextManeuver() {
        return this.mDistanceToNextManeuver;
    }

    public void setDistanceToNextManeuver(long distanceToNextManeuver) {
        this.mDistanceToNextManeuver = distanceToNextManeuver;
    }

    public long getDistanceRemaining() {
        return this.mDistanceRemaining;
    }

    public void setDistanceRemaining(long distanceRemaining) {
        this.mDistanceRemaining = distanceRemaining;
    }

    public boolean isRouteGuidanceVisibleInApp() {
        return this.mRouteGuidanceVisibleInApp;
    }

    public void setRouteGuidanceVisibleInApp(boolean routeGuidanceVisibleInApp) {
        this.mRouteGuidanceVisibleInApp = routeGuidanceVisibleInApp;
    }

    public boolean isLaneGuidanceBeingShowInApp() {
        return this.mLaneGuidanceBeingShowInApp;
    }

    public void setLaneGuidanceBeingShowInApp(boolean laneGuidanceBeingShowInApp) {
        this.mLaneGuidanceBeingShowInApp = laneGuidanceBeingShowInApp;
    }

    public int getManeuverType() {
        return this.mManeuverType;
    }

    public void setManeuverType(int maneuverType) {
        this.mManeuverType = maneuverType;
    }

    public String getDistanceToNextManeuverDisplayStr() {
        return this.mDistanceToNextManeuverDisplayStr;
    }

    public int getJunctionType() {
        return this.mJunctionType;
    }

    public int getDrivingSide() {
        return this.mDrivingSide;
    }

    public int getJunctionElementExitAngle() {
        return this.mJunctionElementExitAngle;
    }

    public void setDistanceToNextManeuverDisplayStr(String distanceToNextManeuverDisplayStr) {
        this.mDistanceToNextManeuverDisplayStr = distanceToNextManeuverDisplayStr;
    }

    public void setJunctionElementExitAngle(int junctionElementExitAngle) {
        this.mJunctionElementExitAngle = junctionElementExitAngle;
    }

    public void setJunctionType(int junctionType) {
        this.mJunctionType = junctionType;
    }

    public void setDrivingSide(int drivingSide) {
        this.mDrivingSide = drivingSide;
    }

    public int getDistanceToNextManeuverDisplayUnits() {
        return this.mDistanceToNextManeuverDisplayUnits;
    }

    public void setDistanceToNextManeuverDisplayUnits(int distanceToNextManeuverDisplayUnits) {
        this.mDistanceToNextManeuverDisplayUnits = distanceToNextManeuverDisplayUnits;
    }

    public String getAfterManeuverRoadName() {
        return this.mAfterManeuverRoadName;
    }

    public void setAfterManeuverRoadName(String afterManeuverRoadName) {
        this.mAfterManeuverRoadName = afterManeuverRoadName;
    }

    public String getDistanceRemainingDisplayStr() {
        return this.mDistanceRemainingDisplayStr;
    }

    public void setDistanceRemainingDisplayStr(String distanceRemainingDisplayStr) {
        this.mDistanceRemainingDisplayStr = distanceRemainingDisplayStr;
    }

    public int getDistanceRemainingDisplayUnits() {
        return this.mDistanceRemainingDisplayUnits;
    }

    public void setDistanceRemainingDisplayUnits(int distanceRemainingDisplayUnits) {
        this.mDistanceRemainingDisplayUnits = distanceRemainingDisplayUnits;
    }

    public long getTimeRemainingToDestination() {
        return this.mTimeRemainingToDestination;
    }

    public void setTimeRemainingToDestination(long timeRemainingToDestination) {
        this.mTimeRemainingToDestination = timeRemainingToDestination;
    }

    public int getRouteGuidanceState() {
        return this.mRouteGuidanceState;
    }

    public void setRouteGuidanceState(int routeGuidanceState) {
        this.mRouteGuidanceState = routeGuidanceState;
    }

    public List<LaneInformation> getLaneInformationList() {
        return this.mLaneInformationList;
    }

    public void setLaneInformationList(List<LaneInformation> list) {
        this.mLaneInformationList = list;
    }

    public String getInstructionText() {
        return this.mInstructionText;
    }

    public void setInstructionText(String instructionText) {
        this.mInstructionText = instructionText;
    }

    public String getSourceName() {
        return this.mSourceName;
    }

    public void setSourceName(String sourceName) {
        this.mSourceName = sourceName;
    }

    public int getSourceSupportsRouteGuidance() {
        return this.mSourceSupportsRouteGuidance;
    }

    public void setSourceSupportsRouteGuidance(int sourceSupports) {
        this.mSourceSupportsRouteGuidance = sourceSupports;
    }

    public String getExitInfo() {
        return this.mExitInfo;
    }

    public void setExitInfo(String exitInfo) {
        this.mExitInfo = exitInfo;
    }

    public int getDestinationTimeZoneOffsetMinutes() {
        return this.mDestinationTimeZoneOffsetMinutes;
    }

    public void setDestinationTimeZoneOffsetMinutes(int offsetMinutes) {
        this.mDestinationTimeZoneOffsetMinutes = offsetMinutes;
    }
}
