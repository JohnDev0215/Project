package ts.car.service.carplay.data;

import android.text.TextUtils;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RouteGuidanceInfo {
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
    private String mManeuverDescription = "";
    private int mManeuverType = -1;
    private String mAfterManeuverRoadName = "";
    private long mDistanceBetweenManeuver = -1;
    private String mDistanceBetweenManeuverDisplayStr = "";
    private int mDistanceBetweenManeuverDisplayUnits = -1;
    private long mDistanceRemaining = -1;
    private String mDistanceRemainingDisplayStr = "";
    private int mDistanceRemainingDisplayUnits = -1;
    private long mEstimatedTimeOfArrival = -1;
    private long mTimeRemainingToDestination = -1;
    private int mRouteGuidanceState = -1;
    private int mJunctionType = -1;
    private int mDrivingSide = -1;
    private int mJunctionElementExitAngle = 181;
    private List<Integer> mJunctionElementAngles = null;
    private List<LaneInformation> mLaneInformationList = null;
    private String mInstructionText = "";
    private int mManeuverState = -1;
    private String mCurrentRoadName = "";
    private String mDestinationName = "";
    private long mDistanceToNextManeuver = -1;
    private String mDistanceToNextManeuverDisplayStr = null;
    private int mDistanceToNextManeuverDisplayUnits = -1;
    private int mManeuverGuidanceTotalCount = -1;
    private boolean mRouteGuidanceVisibleInApp = false;
    private int mLaneGuidanceTotalCount = -1;
    private boolean mLaneGuidanceBeingShowInApp = false;
    private String mSourceName = "";
    private int mSourceSupportsRouteGuidance = -1;
    private String mExitInfo = "";
    private int mDestinationTimeZoneOffsetMinutes = -1;

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

    private boolean checkManeuverGuidanceInfo() {
        return TextUtils.isEmpty(this.mManeuverDescription) && this.mManeuverType == -1 && TextUtils.isEmpty(this.mAfterManeuverRoadName) && this.mDistanceBetweenManeuver == -1 && TextUtils.isEmpty(this.mDistanceBetweenManeuverDisplayStr) && this.mDistanceBetweenManeuverDisplayUnits == -1 && this.mJunctionType == -1 && this.mDrivingSide == -1 && this.mJunctionElementExitAngle == 181 && this.mJunctionElementAngles == null && this.mLaneInformationList == null && TextUtils.isEmpty(this.mInstructionText);
    }

    private boolean checkRouteGuidanceUpdateInfo() {
        return this.mRouteGuidanceState == -1 && this.mManeuverState == -1 && TextUtils.isEmpty(this.mCurrentRoadName) && TextUtils.isEmpty(this.mDestinationName) && this.mEstimatedTimeOfArrival == -1 && this.mTimeRemainingToDestination == -1 && this.mDistanceRemaining == -1 && TextUtils.isEmpty(this.mDistanceRemainingDisplayStr) && this.mDistanceRemainingDisplayUnits == -1 && this.mDistanceToNextManeuver == -1 && TextUtils.isEmpty(this.mDistanceToNextManeuverDisplayStr) && this.mDistanceToNextManeuverDisplayUnits == -1 && !this.mRouteGuidanceVisibleInApp && !this.mLaneGuidanceBeingShowInApp && this.mManeuverGuidanceTotalCount == -1 && this.mLaneGuidanceTotalCount == -1 && this.mSourceName == "" && this.mSourceSupportsRouteGuidance == -1 && this.mExitInfo == "" && this.mDestinationTimeZoneOffsetMinutes == -1;
    }

    public void clear() {
        this.mManeuverDescription = "";
        this.mManeuverType = -1;
        this.mAfterManeuverRoadName = "";
        this.mDistanceBetweenManeuver = -1L;
        this.mDistanceBetweenManeuverDisplayStr = "";
        this.mDistanceBetweenManeuverDisplayUnits = -1;
        this.mDistanceRemaining = -1L;
        this.mDistanceRemainingDisplayStr = "";
        this.mDistanceRemainingDisplayUnits = -1;
        this.mEstimatedTimeOfArrival = -1L;
        this.mTimeRemainingToDestination = -1L;
        this.mRouteGuidanceState = -1;
        this.mJunctionType = -1;
        this.mDrivingSide = -1;
        this.mJunctionElementExitAngle = 181;
        this.mJunctionElementAngles = null;
        this.mLaneInformationList = null;
        this.mInstructionText = "";
        this.mManeuverState = -1;
        this.mCurrentRoadName = "";
        this.mDestinationName = "";
        this.mDistanceToNextManeuver = -1L;
        this.mDistanceToNextManeuverDisplayStr = null;
        this.mDistanceToNextManeuverDisplayUnits = -1;
        this.mManeuverGuidanceTotalCount = -1;
        this.mRouteGuidanceVisibleInApp = false;
        this.mLaneGuidanceTotalCount = -1;
        this.mLaneGuidanceBeingShowInApp = false;
        this.mSourceName = "";
        this.mSourceSupportsRouteGuidance = -1;
        this.mExitInfo = "";
        this.mDestinationTimeZoneOffsetMinutes = -1;
    }

    public void copy(RouteGuidanceInfo routeGuidanceInfo) {
        if (routeGuidanceInfo != null) {
            this.mManeuverType = routeGuidanceInfo.getManeuverType();
            this.mDistanceBetweenManeuverDisplayStr = routeGuidanceInfo.getDistanceBetweenManeuverDisplayStr();
            this.mDistanceBetweenManeuverDisplayUnits = routeGuidanceInfo.getDistanceBetweenManeuverDisplayUnits();
            this.mAfterManeuverRoadName = routeGuidanceInfo.getAfterManeuverRoadName();
            this.mDistanceRemainingDisplayStr = routeGuidanceInfo.getDistanceRemainingDisplayStr();
            this.mDistanceRemainingDisplayUnits = routeGuidanceInfo.getDistanceRemainingDisplayUnits();
            this.mTimeRemainingToDestination = routeGuidanceInfo.getTimeRemainingToDestination();
            this.mRouteGuidanceState = routeGuidanceInfo.getRouteGuidanceState();
            this.mJunctionType = routeGuidanceInfo.getJunctionType();
            this.mDrivingSide = routeGuidanceInfo.getDrivingSide();
            this.mJunctionElementExitAngle = routeGuidanceInfo.getJunctionElementExitAngle();
            this.mManeuverDescription = routeGuidanceInfo.getManeuverDescription();
            this.mDistanceBetweenManeuver = routeGuidanceInfo.getDistanceBetweenManeuver();
            this.mDistanceRemaining = routeGuidanceInfo.getDistanceRemaining();
            this.mEstimatedTimeOfArrival = routeGuidanceInfo.getEstimatedTimeOfArrival();
            this.mJunctionElementAngles = routeGuidanceInfo.getJunctionElementAngles();
            this.mManeuverState = routeGuidanceInfo.getManeuverState();
            this.mCurrentRoadName = routeGuidanceInfo.getCurrentRoadName();
            this.mDestinationName = routeGuidanceInfo.getDestinationName();
            this.mDistanceToNextManeuver = routeGuidanceInfo.getDistanceToNextManeuver();
            this.mDistanceToNextManeuverDisplayStr = routeGuidanceInfo.getDistanceToNextManeuverDisplayStr();
            this.mDistanceToNextManeuverDisplayUnits = routeGuidanceInfo.getDistanceToNextManeuverDisplayUnits();
            this.mManeuverGuidanceTotalCount = routeGuidanceInfo.getLaneGuidanceTotalCount();
            this.mRouteGuidanceVisibleInApp = routeGuidanceInfo.isRouteGuidanceVisibleInApp();
            this.mLaneGuidanceTotalCount = routeGuidanceInfo.getLaneGuidanceTotalCount();
            this.mLaneGuidanceBeingShowInApp = routeGuidanceInfo.isLaneGuidanceBeingShowInApp();
            this.mLaneInformationList = routeGuidanceInfo.getLaneInformationList();
            this.mInstructionText = routeGuidanceInfo.getInstructionText();
            this.mSourceName = routeGuidanceInfo.getSourceName();
            this.mSourceSupportsRouteGuidance = routeGuidanceInfo.getSourceSupportsRouteGuidance();
            this.mExitInfo = routeGuidanceInfo.getExitInfo();
            this.mDestinationTimeZoneOffsetMinutes = routeGuidanceInfo.getDestinationTimeZoneOffsetMinutes();
        }
    }

    public String getAfterManeuverRoadName() {
        return this.mAfterManeuverRoadName;
    }

    public String getCurrentRoadName() {
        return this.mCurrentRoadName;
    }

    public String getDestinationName() {
        return this.mDestinationName;
    }

    public int getDestinationTimeZoneOffsetMinutes() {
        return this.mDestinationTimeZoneOffsetMinutes;
    }

    public long getDistanceBetweenManeuver() {
        return this.mDistanceBetweenManeuver;
    }

    public String getDistanceBetweenManeuverDisplayStr() {
        return this.mDistanceBetweenManeuverDisplayStr;
    }

    public int getDistanceBetweenManeuverDisplayUnits() {
        return this.mDistanceBetweenManeuverDisplayUnits;
    }

    public long getDistanceRemaining() {
        return this.mDistanceRemaining;
    }

    public String getDistanceRemainingDisplayStr() {
        return this.mDistanceRemainingDisplayStr;
    }

    public int getDistanceRemainingDisplayUnits() {
        return this.mDistanceRemainingDisplayUnits;
    }

    public long getDistanceToNextManeuver() {
        return this.mDistanceToNextManeuver;
    }

    public String getDistanceToNextManeuverDisplayStr() {
        return this.mDistanceToNextManeuverDisplayStr;
    }

    public int getDistanceToNextManeuverDisplayUnits() {
        return this.mDistanceToNextManeuverDisplayUnits;
    }

    public int getDrivingSide() {
        return this.mDrivingSide;
    }

    public long getEstimatedTimeOfArrival() {
        return this.mEstimatedTimeOfArrival;
    }

    public String getExitInfo() {
        return this.mExitInfo;
    }

    public String getInstructionText() {
        return this.mInstructionText;
    }

    public List<Integer> getJunctionElementAngles() {
        return this.mJunctionElementAngles;
    }

    public int getJunctionElementExitAngle() {
        return this.mJunctionElementExitAngle;
    }

    public int getJunctionType() {
        return this.mJunctionType;
    }

    public int getLaneGuidanceTotalCount() {
        return this.mLaneGuidanceTotalCount;
    }

    public List<LaneInformation> getLaneInformationList() {
        return this.mLaneInformationList;
    }

    public String getManeuverDescription() {
        return this.mManeuverDescription;
    }

    public int getManeuverGuidanceTotalCount() {
        return this.mManeuverGuidanceTotalCount;
    }

    public int getManeuverState() {
        return this.mManeuverState;
    }

    public int getManeuverType() {
        return this.mManeuverType;
    }

    public int getRouteGuidanceState() {
        return this.mRouteGuidanceState;
    }

    public String getSourceName() {
        return this.mSourceName;
    }

    public int getSourceSupportsRouteGuidance() {
        return this.mSourceSupportsRouteGuidance;
    }

    public long getTimeRemainingToDestination() {
        return this.mTimeRemainingToDestination;
    }

    public boolean isDefaultValue() {
        return checkRouteGuidanceUpdateInfo() && checkManeuverGuidanceInfo();
    }

    public boolean isLaneGuidanceBeingShowInApp() {
        return this.mLaneGuidanceBeingShowInApp;
    }

    public boolean isRouteGuidanceVisibleInApp() {
        return this.mRouteGuidanceVisibleInApp;
    }

    public void setAfterManeuverRoadName(String str) {
        this.mAfterManeuverRoadName = str;
    }

    public void setCurrentRoadName(String str) {
        this.mCurrentRoadName = str;
    }

    public void setDestinationName(String str) {
        this.mDestinationName = str;
    }

    public void setDestinationTimeZoneOffsetMinutes(int i8) {
        this.mDestinationTimeZoneOffsetMinutes = i8;
    }

    public void setDistanceBetweenManeuver(long j8) {
        this.mDistanceBetweenManeuver = j8;
    }

    public void setDistanceBetweenManeuverDisplayStr(String str) {
        this.mDistanceBetweenManeuverDisplayStr = str;
    }

    public void setDistanceBetweenManeuverDisplayUnits(int i8) {
        this.mDistanceBetweenManeuverDisplayUnits = i8;
    }

    public void setDistanceRemaining(long j8) {
        this.mDistanceRemaining = j8;
    }

    public void setDistanceRemainingDisplayStr(String str) {
        this.mDistanceRemainingDisplayStr = str;
    }

    public void setDistanceRemainingDisplayUnits(int i8) {
        this.mDistanceRemainingDisplayUnits = i8;
    }

    public void setDistanceToNextManeuver(long j8) {
        this.mDistanceToNextManeuver = j8;
    }

    public void setDistanceToNextManeuverDisplayStr(String str) {
        this.mDistanceToNextManeuverDisplayStr = str;
    }

    public void setDistanceToNextManeuverDisplayUnits(int i8) {
        this.mDistanceToNextManeuverDisplayUnits = i8;
    }

    public void setDrivingSide(int i8) {
        this.mDrivingSide = i8;
    }

    public void setEstimatedTimeOfArrival(long j8) {
        this.mEstimatedTimeOfArrival = j8;
    }

    public void setExitInfo(String str) {
        this.mExitInfo = str;
    }

    public void setInstructionText(String str) {
        this.mInstructionText = str;
    }

    public void setJunctionElementAngles(List<Integer> list) {
        this.mJunctionElementAngles = list;
    }

    public void setJunctionElementExitAngle(int i8) {
        this.mJunctionElementExitAngle = i8;
    }

    public void setJunctionType(int i8) {
        this.mJunctionType = i8;
    }

    public void setLaneGuidanceBeingShowInApp(boolean z7) {
        this.mLaneGuidanceBeingShowInApp = z7;
    }

    public void setLaneGuidanceTotalCount(int i8) {
        this.mLaneGuidanceTotalCount = i8;
    }

    public void setLaneInformationList(List<LaneInformation> list) {
        this.mLaneInformationList = list;
    }

    public void setManeuverDescription(String str) {
        this.mManeuverDescription = str;
    }

    public void setManeuverGuidanceTotalCount(int i8) {
        this.mManeuverGuidanceTotalCount = i8;
    }

    public void setManeuverState(int i8) {
        this.mManeuverState = i8;
    }

    public void setManeuverType(int i8) {
        this.mManeuverType = i8;
    }

    public void setRouteGuidanceState(int i8) {
        this.mRouteGuidanceState = i8;
    }

    public void setRouteGuidanceVisibleInApp(boolean z7) {
        this.mRouteGuidanceVisibleInApp = z7;
    }

    public void setSourceName(String str) {
        this.mSourceName = str;
    }

    public void setSourceSupportsRouteGuidance(int i8) {
        this.mSourceSupportsRouteGuidance = i8;
    }

    public void setTimeRemainingToDestination(long j8) {
        this.mTimeRemainingToDestination = j8;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("RouteGuidanceInfo{");
        stringBuffer.append("mManeuverDescription = ");
        stringBuffer.append(this.mManeuverDescription);
        stringBuffer.append("mManeuverType = ");
        stringBuffer.append(this.mManeuverType);
        stringBuffer.append(" ");
        stringBuffer.append("mDistanceBetweenManeuver = ");
        stringBuffer.append(this.mDistanceBetweenManeuver);
        stringBuffer.append(" ");
        stringBuffer.append("mDistanceBetweenManeuverDisplayStr = ");
        stringBuffer.append(this.mDistanceBetweenManeuverDisplayStr);
        stringBuffer.append(" ");
        stringBuffer.append("mDistanceBetweenManeuverDisplayUnits = ");
        stringBuffer.append(this.mDistanceBetweenManeuverDisplayUnits);
        stringBuffer.append(" ");
        stringBuffer.append("mAfterManeuverRoadName = ");
        stringBuffer.append(this.mAfterManeuverRoadName);
        stringBuffer.append(" ");
        stringBuffer.append("mDistanceRemaining = ");
        stringBuffer.append(this.mDistanceRemaining);
        stringBuffer.append(" ");
        stringBuffer.append("mDistanceRemainingDisplayStr = ");
        stringBuffer.append(this.mDistanceRemainingDisplayStr);
        stringBuffer.append(" ");
        stringBuffer.append("mDistanceRemainingDisplayUnits = ");
        stringBuffer.append(this.mDistanceRemainingDisplayUnits);
        stringBuffer.append(" ");
        stringBuffer.append("mDistanceToNextManeuver = ");
        stringBuffer.append(this.mDistanceToNextManeuver);
        stringBuffer.append(" ");
        stringBuffer.append("mDistanceToNextManeuverDisplayStr = ");
        stringBuffer.append(this.mDistanceToNextManeuverDisplayStr);
        stringBuffer.append(" ");
        stringBuffer.append("mDistanceToNextManeuverDisplayUnits = ");
        stringBuffer.append(this.mDistanceToNextManeuverDisplayUnits);
        stringBuffer.append(" ");
        stringBuffer.append("mEstimatedTimeOfArrival = ");
        stringBuffer.append(this.mEstimatedTimeOfArrival);
        stringBuffer.append(" ");
        stringBuffer.append("mTimeRemainingToDestination = ");
        stringBuffer.append(this.mTimeRemainingToDestination);
        stringBuffer.append(" ");
        stringBuffer.append("mRouteGuidanceState = ");
        stringBuffer.append(this.mRouteGuidanceState);
        stringBuffer.append(" ");
        stringBuffer.append("mManeuverState = ");
        stringBuffer.append(this.mManeuverState);
        stringBuffer.append(" ");
        stringBuffer.append("mCurrentRoadName = ");
        stringBuffer.append(this.mCurrentRoadName);
        stringBuffer.append(" ");
        stringBuffer.append("mDestinationName = ");
        stringBuffer.append(this.mDestinationName);
        stringBuffer.append(" ");
        stringBuffer.append("mJunctionType = ");
        stringBuffer.append(this.mJunctionType);
        stringBuffer.append(" ");
        stringBuffer.append("mDrivingSide = ");
        stringBuffer.append(this.mDrivingSide);
        stringBuffer.append(" ");
        stringBuffer.append("mJunctionElementAngles = ");
        stringBuffer.append(this.mJunctionElementAngles);
        stringBuffer.append(" ");
        stringBuffer.append("mJunctionElementExitAngle = ");
        stringBuffer.append(this.mJunctionElementExitAngle);
        stringBuffer.append(" ");
        stringBuffer.append("mLaneInformationList = ");
        stringBuffer.append(this.mLaneInformationList);
        stringBuffer.append(" ");
        stringBuffer.append("mInstructionText = ");
        stringBuffer.append(this.mInstructionText);
        stringBuffer.append(" ");
        stringBuffer.append("mManeuverGuidanceTotalCount = ");
        stringBuffer.append(this.mManeuverGuidanceTotalCount);
        stringBuffer.append(" ");
        stringBuffer.append("mRouteGuidanceVisibleInApp = ");
        stringBuffer.append(this.mRouteGuidanceVisibleInApp);
        stringBuffer.append(" ");
        stringBuffer.append("mLaneGuidanceTotalCount = ");
        stringBuffer.append(this.mLaneGuidanceTotalCount);
        stringBuffer.append(" ");
        stringBuffer.append("mLaneGuidanceBeingShowInApp = ");
        stringBuffer.append(this.mLaneGuidanceBeingShowInApp);
        stringBuffer.append(" ");
        stringBuffer.append("mSourceName = ");
        stringBuffer.append(this.mSourceName);
        stringBuffer.append(" ");
        stringBuffer.append("mSourceSupportsRouteGuidance = ");
        stringBuffer.append(this.mSourceSupportsRouteGuidance);
        stringBuffer.append(" ");
        stringBuffer.append("mExitInfo = ");
        stringBuffer.append(this.mExitInfo);
        stringBuffer.append(" ");
        stringBuffer.append("mDestinationTimeZoneOffsetMinutes = ");
        stringBuffer.append(this.mDestinationTimeZoneOffsetMinutes);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
