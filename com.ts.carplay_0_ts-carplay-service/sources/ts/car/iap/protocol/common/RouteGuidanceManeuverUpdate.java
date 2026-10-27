package ts.car.iap.protocol.common;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RouteGuidanceManeuverUpdate {
    private static final int ANGLE_INVALID = 65535;
    private String mAfterManeuverRoadName;
    private String mDistanceBetweenManeuverDisplayStr;
    private String mManeuverDescription;
    private final List<Integer> mRouteGuidanceDisplayComponentIDs = new ArrayList();
    private final List<Integer> mJunctionElementAngles = new ArrayList();
    private boolean mDisplayComponentIdValid = false;
    private boolean mIndexValid = false;
    private boolean mManeuverDescriptionValid = false;
    private boolean mManeuverTypeValid = false;
    private boolean mAfterManeuverRoadNameValid = false;
    private boolean mDistanceBetweenManeuverValid = false;
    private boolean mDistanceBetweenManeuverDisplayStrValid = false;
    private boolean mDistanceBetweenManeuverDisplayUnitsValid = false;
    private boolean mDrivingSideValid = false;
    private boolean mJunctionTypeValid = false;
    private boolean mJunctionElementAngleValid = false;
    private boolean mJunctionElementExitAngleValid = false;
    private boolean mLinkedLaneGuidanceInformationValid = false;
    private boolean mExitInfoValid = false;
    private int mDisplayComponentId = -1;
    private int mIndex = -1;
    private int mManeuverType = -1;
    private long mDistanceBetweenManeuver = -1;
    private int mDistanceBetweenManeuverDisplayUnits = -1;
    private int mDrivingSide = -1;
    private int mJunctionType = -1;
    private int mJunctionElementAngle = 65535;
    private int mJunctionElementExitAngle = 65535;
    private int mLinkedLaneGuidanceInformation = -1;
    private String mExitInfo = null;

    public int getDisplayComponentId() {
        return this.mDisplayComponentId;
    }

    public void setDisplayComponentId(int id) {
        this.mDisplayComponentId = id;
        this.mDisplayComponentIdValid = true;
        this.mRouteGuidanceDisplayComponentIDs.clear();
        this.mRouteGuidanceDisplayComponentIDs.add(Integer.valueOf(id));
    }

    public void addRouteGuidanceDisplayComponentId(int id) {
        if (this.mRouteGuidanceDisplayComponentIDs.size() == 0) {
            setDisplayComponentId(id);
        } else {
            this.mRouteGuidanceDisplayComponentIDs.add(Integer.valueOf(id));
        }
    }

    public List<Integer> getRouteGuidanceDisplayComponentIds() {
        return this.mRouteGuidanceDisplayComponentIDs;
    }

    public boolean isDisplayComponentIdValid() {
        return this.mDisplayComponentIdValid;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void setIndex(int index) {
        this.mIndex = index;
        this.mIndexValid = true;
    }

    public boolean isIndexValid() {
        return this.mIndexValid;
    }

    public String getManeuverDescription() {
        return this.mManeuverDescription;
    }

    public void setManeuverDescription(String description) {
        this.mManeuverDescription = description;
        this.mManeuverDescriptionValid = true;
    }

    public boolean isManeuverDescriptionValid() {
        return this.mManeuverDescriptionValid;
    }

    public int getManeuverType() {
        return this.mManeuverType;
    }

    public void setManeuverType(int type) {
        this.mManeuverType = type;
        this.mManeuverTypeValid = true;
    }

    public boolean isManeuverTypeValid() {
        return this.mManeuverTypeValid;
    }

    public String getAfterManeuverRoadName() {
        return this.mAfterManeuverRoadName;
    }

    public void setAfterManeuverRoadName(String roadName) {
        this.mAfterManeuverRoadName = roadName;
        this.mAfterManeuverRoadNameValid = true;
    }

    public boolean isAfterManeuverRoadNameValid() {
        return this.mAfterManeuverRoadNameValid;
    }

    public long getDistanceBetweenManeuver() {
        return this.mDistanceBetweenManeuver;
    }

    public void setDistanceBetweenManeuver(long distance) {
        this.mDistanceBetweenManeuver = distance;
        this.mDistanceBetweenManeuverValid = true;
    }

    public boolean isDistanceBetweenManeuverValid() {
        return this.mDistanceBetweenManeuverValid;
    }

    public String getDistanceBetweenManeuverDisplayStr() {
        return this.mDistanceBetweenManeuverDisplayStr;
    }

    public void setDistanceBetweenManeuverDisplayStr(String displayStr) {
        this.mDistanceBetweenManeuverDisplayStr = displayStr;
        this.mDistanceBetweenManeuverDisplayStrValid = true;
    }

    public boolean isDistanceBetweenManeuverDisplayStrValid() {
        return this.mDistanceBetweenManeuverDisplayStrValid;
    }

    public int getDistanceBetweenManeuverDisplayUnits() {
        return this.mDistanceBetweenManeuverDisplayUnits;
    }

    public void setDistanceBetweenManeuverDisplayUnits(int displayUnits) {
        this.mDistanceBetweenManeuverDisplayUnits = displayUnits;
        this.mDistanceBetweenManeuverDisplayUnitsValid = true;
    }

    public boolean isDistanceBetweenManeuverDisplayUnitsValid() {
        return this.mDistanceBetweenManeuverDisplayUnitsValid;
    }

    public int getDrivingSide() {
        return this.mDrivingSide;
    }

    public void setDrivingSide(int side) {
        this.mDrivingSide = side;
        this.mDrivingSideValid = true;
    }

    public boolean isDrivingSideValid() {
        return this.mDrivingSideValid;
    }

    public int getJunctionType() {
        return this.mJunctionType;
    }

    public void setJunctionType(int type) {
        this.mJunctionType = type;
        this.mJunctionTypeValid = true;
    }

    public boolean isJunctionTypeValid() {
        return this.mJunctionTypeValid;
    }

    public int getJunctionElementAngle() {
        return this.mJunctionElementAngle;
    }

    public void setJunctionElementAngle(int angle) {
        this.mJunctionElementAngle = angle;
        this.mJunctionElementAngleValid = true;
        this.mJunctionElementAngles.clear();
        this.mJunctionElementAngles.add(Integer.valueOf(angle));
    }

    public boolean isJunctionElementAngleValid() {
        return this.mJunctionElementAngleValid;
    }

    public void addJunctionElementAngles(int angle) {
        if (this.mJunctionElementAngles.size() == 0) {
            setJunctionElementAngle(angle);
        } else {
            this.mJunctionElementAngles.add(Integer.valueOf(angle));
        }
    }

    public List<Integer> getJunctionElementAngles() {
        return this.mJunctionElementAngles;
    }

    public int getJunctionElementExitAngle() {
        return this.mJunctionElementExitAngle;
    }

    public void setJunctionElementExitAngle(int angle) {
        this.mJunctionElementExitAngle = angle;
        this.mJunctionElementExitAngleValid = true;
    }

    public boolean isJunctionElementExitAngleValid() {
        return this.mJunctionElementExitAngleValid;
    }

    public int getLinkedLaneGuidanceInformation() {
        return this.mLinkedLaneGuidanceInformation;
    }

    public void setLinkedLaneGuidanceInformation(int index) {
        this.mLinkedLaneGuidanceInformation = index;
        this.mLinkedLaneGuidanceInformationValid = true;
    }

    public boolean isLinkedLaneGuidanceInformationValid() {
        return this.mLinkedLaneGuidanceInformationValid;
    }

    public String getExitInfo() {
        return this.mExitInfo;
    }

    public void setExitInfo(String exitInfo) {
        this.mExitInfo = exitInfo;
        this.mExitInfoValid = true;
    }

    public boolean isExitInfoValid() {
        return this.mExitInfoValid;
    }
}
