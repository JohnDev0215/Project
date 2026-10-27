package ts.car.service.carplay.data;

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
        }
    }

    public boolean isDefaultValue() {
        return checkRouteGuidanceUpdateInfo() && checkManeuverGuidanceInfo();
    }

    private boolean checkRouteGuidanceUpdateInfo() {
        return this.mRouteGuidanceState == -1 && this.mManeuverState == -1 && this.mCurrentRoadName == "" && this.mDestinationName == "" && this.mEstimatedTimeOfArrival == -1 && this.mTimeRemainingToDestination == -1 && this.mDistanceRemaining == -1 && this.mDistanceRemainingDisplayStr == "" && this.mDistanceRemainingDisplayUnits == -1 && this.mDistanceToNextManeuver == -1 && this.mDistanceToNextManeuverDisplayStr == "" && this.mDistanceToNextManeuverDisplayUnits == -1 && !this.mRouteGuidanceVisibleInApp && !this.mLaneGuidanceBeingShowInApp && this.mManeuverGuidanceTotalCount == -1 && this.mLaneGuidanceTotalCount == -1;
    }

    private boolean checkManeuverGuidanceInfo() {
        return this.mManeuverDescription == "" && this.mManeuverType == -1 && this.mAfterManeuverRoadName == "" && this.mDistanceBetweenManeuver == -1 && this.mDistanceBetweenManeuverDisplayStr == "" && this.mDistanceBetweenManeuverDisplayUnits == -1 && this.mJunctionType == -1 && this.mDrivingSide == -1 && this.mJunctionElementExitAngle == 181 && this.mJunctionElementAngles == null && this.mLaneInformationList == null && this.mInstructionText == "";
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("RouteGuidanceInfo{").append("mManeuverDescription = ").append(this.mManeuverDescription).append("mManeuverType = ").append(this.mManeuverType).append(" ").append("mDistanceBetweenManeuver = ").append(this.mDistanceBetweenManeuver).append(" ").append("mDistanceBetweenManeuverDisplayStr = ").append(this.mDistanceBetweenManeuverDisplayStr).append(" ").append("mDistanceBetweenManeuverDisplayUnits = ").append(this.mDistanceBetweenManeuverDisplayUnits).append(" ").append("mAfterManeuverRoadName = ").append(this.mAfterManeuverRoadName).append(" ").append("mDistanceRemaining = ").append(this.mDistanceRemaining).append(" ").append("mDistanceRemainingDisplayStr = ").append(this.mDistanceRemainingDisplayStr).append(" ").append("mDistanceRemainingDisplayUnits = ").append(this.mDistanceRemainingDisplayUnits).append(" ").append("mDistanceToNextManeuver = ").append(this.mDistanceToNextManeuver).append(" ").append("mDistanceToNextManeuverDisplayStr = ").append(this.mDistanceToNextManeuverDisplayStr).append(" ").append("mDistanceToNextManeuverDisplayUnits = ").append(this.mDistanceToNextManeuverDisplayUnits).append(" ").append("mEstimatedTimeOfArrival = ").append(this.mEstimatedTimeOfArrival).append(" ").append("mTimeRemainingToDestination = ").append(this.mTimeRemainingToDestination).append(" ").append("mRouteGuidanceState = ").append(this.mRouteGuidanceState).append(" ").append("mManeuverState = ").append(this.mManeuverState).append(" ").append("mCurrentRoadName = ").append(this.mCurrentRoadName).append(" ").append("mDestinationName = ").append(this.mDestinationName).append(" ").append("mJunctionType = ").append(this.mJunctionType).append(" ").append("mDrivingSide = ").append(this.mDrivingSide).append(" ").append("mJunctionElementAngles = ").append(this.mJunctionElementAngles).append(" ").append("mJunctionElementExitAngle = ").append(this.mJunctionElementExitAngle).append(" ").append("mLaneInformationList = ").append(this.mLaneInformationList).append(" ").append("mInstructionText = ").append(this.mInstructionText).append(" ").append("mManeuverGuidanceTotalCount = ").append(this.mManeuverGuidanceTotalCount).append(" ").append("mRouteGuidanceVisibleInApp = ").append(this.mRouteGuidanceVisibleInApp).append(" ").append("mLaneGuidanceTotalCount = ").append(this.mLaneGuidanceTotalCount).append(" ").append("mLaneGuidanceBeingShowInApp = ").append(this.mLaneGuidanceBeingShowInApp);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    public String getManeuverDescription() {
        return this.mManeuverDescription;
    }

    public void setManeuverDescription(String str) {
        this.mManeuverDescription = str;
    }

    public List<Integer> getJunctionElementAngles() {
        return this.mJunctionElementAngles;
    }

    public void setJunctionElementAngles(List<Integer> list) {
        this.mJunctionElementAngles = list;
    }

    public int getManeuverType() {
        return this.mManeuverType;
    }

    public void setManeuverType(int i) {
        this.mManeuverType = i;
    }

    public long getDistanceBetweenManeuver() {
        return this.mDistanceBetweenManeuver;
    }

    public void setDistanceBetweenManeuver(long j) {
        this.mDistanceBetweenManeuver = j;
    }

    public String getDistanceBetweenManeuverDisplayStr() {
        return this.mDistanceBetweenManeuverDisplayStr;
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

    public void setDistanceBetweenManeuverDisplayStr(String str) {
        this.mDistanceBetweenManeuverDisplayStr = str;
    }

    public void setJunctionElementExitAngle(int i) {
        this.mJunctionElementExitAngle = i;
    }

    public void setJunctionType(int i) {
        this.mJunctionType = i;
    }

    public void setDrivingSide(int i) {
        this.mDrivingSide = i;
    }

    public int getDistanceBetweenManeuverDisplayUnits() {
        return this.mDistanceBetweenManeuverDisplayUnits;
    }

    public void setDistanceBetweenManeuverDisplayUnits(int i) {
        this.mDistanceBetweenManeuverDisplayUnits = i;
    }

    public String getAfterManeuverRoadName() {
        return this.mAfterManeuverRoadName;
    }

    public void setAfterManeuverRoadName(String str) {
        this.mAfterManeuverRoadName = str;
    }

    public long getDistanceRemaining() {
        return this.mDistanceRemaining;
    }

    public void setDistanceRemaining(long j) {
        this.mDistanceRemaining = j;
    }

    public String getDistanceRemainingDisplayStr() {
        return this.mDistanceRemainingDisplayStr;
    }

    public void setDistanceRemainingDisplayStr(String str) {
        this.mDistanceRemainingDisplayStr = str;
    }

    public int getDistanceRemainingDisplayUnits() {
        return this.mDistanceRemainingDisplayUnits;
    }

    public void setDistanceRemainingDisplayUnits(int i) {
        this.mDistanceRemainingDisplayUnits = i;
    }

    public long getDistanceToNextManeuver() {
        return this.mDistanceToNextManeuver;
    }

    public void setDistanceToNextManeuver(long j) {
        this.mDistanceToNextManeuver = j;
    }

    public String getDistanceToNextManeuverDisplayStr() {
        return this.mDistanceToNextManeuverDisplayStr;
    }

    public void setDistanceToNextManeuverDisplayStr(String str) {
        this.mDistanceToNextManeuverDisplayStr = str;
    }

    public int getDistanceToNextManeuverDisplayUnits() {
        return this.mDistanceToNextManeuverDisplayUnits;
    }

    public void setDistanceToNextManeuverDisplayUnits(int i) {
        this.mDistanceToNextManeuverDisplayUnits = i;
    }

    public int getManeuverGuidanceTotalCount() {
        return this.mManeuverGuidanceTotalCount;
    }

    public void setManeuverGuidanceTotalCount(int i) {
        this.mManeuverGuidanceTotalCount = i;
    }

    public boolean isRouteGuidanceVisibleInApp() {
        return this.mRouteGuidanceVisibleInApp;
    }

    public void setRouteGuidanceVisibleInApp(boolean z) {
        this.mRouteGuidanceVisibleInApp = z;
    }

    public int getLaneGuidanceTotalCount() {
        return this.mLaneGuidanceTotalCount;
    }

    public void setLaneGuidanceTotalCount(int i) {
        this.mLaneGuidanceTotalCount = i;
    }

    public boolean isLaneGuidanceBeingShowInApp() {
        return this.mLaneGuidanceBeingShowInApp;
    }

    public void setLaneGuidanceBeingShowInApp(boolean z) {
        this.mLaneGuidanceBeingShowInApp = z;
    }

    public long getTimeRemainingToDestination() {
        return this.mTimeRemainingToDestination;
    }

    public void setTimeRemainingToDestination(long j) {
        this.mTimeRemainingToDestination = j;
    }

    public long getEstimatedTimeOfArrival() {
        return this.mEstimatedTimeOfArrival;
    }

    public void setEstimatedTimeOfArrival(long j) {
        this.mEstimatedTimeOfArrival = j;
    }

    public int getRouteGuidanceState() {
        return this.mRouteGuidanceState;
    }

    public void setRouteGuidanceState(int i) {
        this.mRouteGuidanceState = i;
    }

    public int getManeuverState() {
        return this.mManeuverState;
    }

    public void setManeuverState(int i) {
        this.mManeuverState = i;
    }

    public String getCurrentRoadName() {
        return this.mCurrentRoadName;
    }

    public void setCurrentRoadName(String str) {
        this.mCurrentRoadName = str;
    }

    public String getDestinationName() {
        return this.mDestinationName;
    }

    public void setDestinationName(String str) {
        this.mDestinationName = str;
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

    public void setInstructionText(String str) {
        this.mInstructionText = str;
    }
}
