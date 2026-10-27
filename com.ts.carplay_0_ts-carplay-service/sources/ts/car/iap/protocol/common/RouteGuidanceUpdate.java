package ts.car.iap.protocol.common;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class RouteGuidanceUpdate {
    private static final String TAG = "RouteGuidanceUpdate";
    private String mCurrentRoadName;
    private String mDestinationName;
    private String mDistanceRemainingDisplayStr;
    private String mDistanceToNextManeuverDisplayStr;
    private int mLaneGuidanceBeingShowInApp;
    private int mLaneGuidanceCurrentIndex;
    private int mLaneGuidanceTotalCount;
    private List<Integer> mManeuverCurrentList;
    private String mSourceName;
    private int mSourceSupportsRouteGuidance;
    private final List<Integer> mRouteGuidanceDisplayComponentIDs = new ArrayList();
    private boolean mDisplayComponentIdValid = false;
    private boolean mStateValid = false;
    private boolean mManeuverStateValid = false;
    private boolean mCurrentRoadNameValid = false;
    private boolean mDestinationNameValid = false;
    private boolean mEstimatedTimeOfArrivalValid = false;
    private boolean mTimeRemainingToDestinationValid = false;
    private boolean mDistanceRemainingValid = false;
    private boolean mDistanceRemainingDisplayStrValid = false;
    private boolean mDistanceRemainingDisplayUnitsValid = false;
    private boolean mDistanceToNextManeuverValid = false;
    private boolean mDistanceToNextManeuverDisplayStrValid = false;
    private boolean mDistanceToNextManeuverDisplayUnitsValid = false;
    private boolean mManeuverCurrentListValid = false;
    private boolean mManeuverCountValid = false;
    private boolean mVisibleInAppValid = false;
    private boolean mLaneGuidanceCurrentIndexValid = false;
    private boolean mLaneGuidanceTotalCountValid = false;
    private boolean mLaneGuidanceBeingShowInAppValid = false;
    private boolean mSourceNameValid = false;
    private boolean mSourceSupportsRouteGuidanceValid = false;
    private boolean mDestinationTimeZoneOffsetMinutesValid = false;
    private int mDisplayComponentId = -1;
    private int mState = -1;
    private int mManeuverState = -1;
    private long mEstimatedTimeOfArrival = -1;
    private long mTimeRemainingToDestination = -1;
    private long mDistanceRemaining = -1;
    private int mDistanceRemainingDisplayUnits = -1;
    private long mDistanceToNextManeuver = -1;
    private int mDistanceToNextManeuverDisplayUnits = -1;
    private int mVisibleInApp = -1;
    private int mManeuverCount = -1;
    private int mDestinationTimeZoneOffsetMinutes = -1;

    public int getDisplayComponentId() {
        return this.mDisplayComponentId;
    }

    public void setDisplayComponentId(int id) {
        this.mDisplayComponentId = id;
        this.mDisplayComponentIdValid = true;
        this.mRouteGuidanceDisplayComponentIDs.clear();
        this.mRouteGuidanceDisplayComponentIDs.add(Integer.valueOf(id));
    }

    public boolean isDisplayComponentIdValid() {
        return this.mDisplayComponentIdValid;
    }

    public int getState() {
        return this.mState;
    }

    public void setState(int state) {
        this.mState = state;
        this.mStateValid = true;
    }

    public boolean isStateValid() {
        return this.mStateValid;
    }

    public int getManeuverState() {
        return this.mManeuverState;
    }

    public void setManeuverState(int state) {
        this.mManeuverState = state;
        this.mManeuverStateValid = true;
    }

    public boolean isManeuverStateValid() {
        return this.mManeuverStateValid;
    }

    public String getCurrentRoadName() {
        return this.mCurrentRoadName;
    }

    public void setCurrentRoadName(String name) {
        this.mCurrentRoadName = name;
        this.mCurrentRoadNameValid = true;
    }

    public boolean isCurrentRoadNameValid() {
        return this.mCurrentRoadNameValid;
    }

    public String getDestinationName() {
        return this.mDestinationName;
    }

    public void setDestinationName(String name) {
        this.mDestinationName = name;
        this.mDestinationNameValid = true;
    }

    public boolean isDestinationNameValid() {
        return this.mDestinationNameValid;
    }

    public long getEstimatedTimeOfArrival() {
        return this.mEstimatedTimeOfArrival;
    }

    public void setEstimatedTimeOfArrival(long time) {
        this.mEstimatedTimeOfArrival = time;
        this.mEstimatedTimeOfArrivalValid = true;
    }

    public boolean isEstimatedTimeOfArrivalValid() {
        return this.mEstimatedTimeOfArrivalValid;
    }

    public long getTimeRemainingToDestination() {
        return this.mTimeRemainingToDestination;
    }

    public void setTimeRemainingToDestination(long time) {
        this.mTimeRemainingToDestination = time;
        this.mTimeRemainingToDestinationValid = true;
    }

    public boolean isTimeRemainingToDestinationValid() {
        return this.mTimeRemainingToDestinationValid;
    }

    public long getDistanceRemaining() {
        return this.mDistanceRemaining;
    }

    public void setDistanceRemaining(long distance) {
        this.mDistanceRemaining = distance;
        this.mDistanceRemainingValid = true;
    }

    public boolean isDistanceRemainingValid() {
        return this.mDistanceRemainingValid;
    }

    public String getDistanceRemainingDisplayStr() {
        return this.mDistanceRemainingDisplayStr;
    }

    public void setDistanceRemainingDisplayStr(String displayStr) {
        this.mDistanceRemainingDisplayStr = displayStr;
        this.mDistanceRemainingDisplayStrValid = true;
    }

    public boolean isDistanceRemainingDisplayStrValid() {
        return this.mDistanceRemainingDisplayStrValid;
    }

    public int getDistanceRemainingDisplayUnits() {
        return this.mDistanceRemainingDisplayUnits;
    }

    public void setDistanceRemainingDisplayUnits(int displayUnits) {
        this.mDistanceRemainingDisplayUnits = displayUnits;
        this.mDistanceRemainingDisplayUnitsValid = true;
    }

    public boolean isDistanceRemainingDisplayUnitsValid() {
        return this.mDistanceRemainingDisplayUnitsValid;
    }

    public long getDistanceToNextManeuver() {
        return this.mDistanceToNextManeuver;
    }

    public void setDistanceToNextManeuver(long distance) {
        this.mDistanceToNextManeuver = distance;
        this.mDistanceToNextManeuverValid = true;
    }

    public boolean isDistanceToNextManeuverValid() {
        return this.mDistanceToNextManeuverValid;
    }

    public String getDistanceToNextManeuverDisplayStr() {
        return this.mDistanceToNextManeuverDisplayStr;
    }

    public void setDistanceToNextManeuverDisplayStr(String displayStr) {
        this.mDistanceToNextManeuverDisplayStr = displayStr;
        this.mDistanceToNextManeuverDisplayStrValid = true;
    }

    public boolean isDistanceToNextManeuverDisplayStrValid() {
        return this.mDistanceToNextManeuverDisplayStrValid;
    }

    public int getmDistanceToNextManeuverDisplayUnits() {
        return this.mDistanceToNextManeuverDisplayUnits;
    }

    public void setmDistanceToNextManeuverDisplayUnits(int displayUnits) {
        this.mDistanceToNextManeuverDisplayUnits = displayUnits;
        this.mDistanceToNextManeuverDisplayUnitsValid = true;
    }

    public boolean isDistanceToNextManeuverDisplayUnitsValid() {
        return this.mDistanceToNextManeuverDisplayUnitsValid;
    }

    public int[] getManeuverCurrentList() {
        List<Integer> list = this.mManeuverCurrentList;
        if (list == null) {
            Log.d(TAG, "getManeuverCurrentList: list is null");
            return null;
        }
        return list.stream().mapToInt(new ToIntFunction() { // from class: ts.car.iap.protocol.common.-$$Lambda$gfCssnBJI7TKfXb_Jmv7raVYNkY
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return Integer.valueOf(((Integer) obj).intValue()).intValue();
            }
        }).toArray();
    }

    public void setManeuverCurrentList(int[] list) {
        if (list == null) {
            Log.d(TAG, "setManeuverCurrentList: list is null");
        } else {
            this.mManeuverCurrentList = (List) Arrays.stream(list).boxed().collect(Collectors.toList());
            this.mManeuverCurrentListValid = true;
        }
    }

    public void addManeuverListValue(int maneuver) {
        if (this.mManeuverCurrentList == null) {
            this.mManeuverCurrentList = new ArrayList();
        }
        this.mManeuverCurrentList.add(Integer.valueOf(maneuver));
        this.mManeuverCurrentListValid = true;
    }

    public boolean isManeuverCurrentListValid() {
        return this.mManeuverCurrentListValid;
    }

    public void setManeuverCount(int count) {
        this.mManeuverCount = count;
        this.mManeuverCountValid = true;
    }

    public int getManeuverCount() {
        return this.mManeuverCount;
    }

    public boolean isManeuverCountValid() {
        return this.mManeuverCountValid;
    }

    public int getVisibleInApp() {
        return this.mVisibleInApp;
    }

    public void setVisibleInApp(int visibleInApp) {
        this.mVisibleInApp = visibleInApp;
        this.mVisibleInAppValid = true;
    }

    public boolean isVisibleInAppValid() {
        return this.mVisibleInAppValid;
    }

    public int getLaneGuidanceCurrentIndex() {
        return this.mLaneGuidanceCurrentIndex;
    }

    public void setLaneGuidanceCurrentIndex(int index) {
        this.mLaneGuidanceCurrentIndex = index;
        this.mLaneGuidanceCurrentIndexValid = true;
    }

    public boolean isLaneGuidanceCurrentIndex() {
        return this.mLaneGuidanceCurrentIndexValid;
    }

    public int getLaneGuidanceTotalCount() {
        return this.mLaneGuidanceTotalCount;
    }

    public void setLaneGuidanceTotalCount(int count) {
        this.mLaneGuidanceTotalCount = count;
        this.mLaneGuidanceTotalCountValid = true;
    }

    public boolean isLaneGuidanceTotalCount() {
        return this.mLaneGuidanceTotalCountValid;
    }

    public int getLaneGuidanceBeingShowInApp() {
        return this.mLaneGuidanceBeingShowInApp;
    }

    public void setLaneGuidanceBeingShowInApp(int beingShowInApp) {
        this.mLaneGuidanceBeingShowInApp = beingShowInApp;
        this.mLaneGuidanceBeingShowInAppValid = true;
    }

    public boolean isLaneGuidanceBeingShowInApp() {
        return this.mLaneGuidanceBeingShowInAppValid;
    }

    public String getSourceName() {
        return this.mSourceName;
    }

    public void setSourceName(String name) {
        this.mSourceName = name;
        this.mSourceNameValid = true;
    }

    public boolean isSourceName() {
        return this.mSourceNameValid;
    }

    public int getSourceSupportsRouteGuidance() {
        return this.mSourceSupportsRouteGuidance;
    }

    public void setSourceSupportsRouteGuidance(int sourceSupports) {
        this.mSourceSupportsRouteGuidance = sourceSupports;
        this.mSourceSupportsRouteGuidanceValid = true;
    }

    public boolean isSourceSupportsRouteGuidance() {
        return this.mSourceSupportsRouteGuidanceValid;
    }

    public void addRouteGuidanceDisplayComponentId(int componentId) {
        if (this.mRouteGuidanceDisplayComponentIDs.size() == 0) {
            setDisplayComponentId(componentId);
        } else {
            this.mRouteGuidanceDisplayComponentIDs.add(Integer.valueOf(componentId));
        }
    }

    public List<Integer> getRouteGuidanceDisplayComponentIds() {
        return this.mRouteGuidanceDisplayComponentIDs;
    }

    public int getDestinationTimeZoneOffsetMinutes() {
        return this.mDestinationTimeZoneOffsetMinutes;
    }

    public void setDestinationTimeZoneOffsetMinutes(int offsetMinutes) {
        this.mDestinationTimeZoneOffsetMinutes = offsetMinutes;
        this.mDestinationTimeZoneOffsetMinutesValid = true;
    }

    public boolean isDestinationTimeZoneOffsetMinutesValid() {
        return this.mDestinationTimeZoneOffsetMinutesValid;
    }

    public String toString() {
        return "RouteGuidanceUpdate [mRouteGuidanceDisplayComponentIDs=" + this.mRouteGuidanceDisplayComponentIDs + ", mState=" + this.mState + ", mManeuverState=" + this.mManeuverState + ", mCurrentRoadName=" + this.mCurrentRoadName + ", mDestinationName=" + this.mDestinationName + ", mEstimatedTimeOfArrival=" + this.mEstimatedTimeOfArrival + ", mTimeRemainingToDestination=" + this.mTimeRemainingToDestination + ", mDistanceRemaining=" + this.mDistanceRemaining + ", mDistanceRemainingDisplayStr=" + this.mDistanceRemainingDisplayStr + ", mDistanceRemainingDisplayUnits=" + this.mDistanceRemainingDisplayUnits + ", mDistanceToNextManeuver=" + this.mDistanceToNextManeuver + ", mDistanceToNextManeuverDisplayStr=" + this.mDistanceToNextManeuverDisplayStr + ", mDistanceToNextManeuverDisplayUnits=" + this.mDistanceToNextManeuverDisplayUnits + ", mManeuverCurrentList=" + this.mManeuverCurrentList + ", mVisibleInApp=" + this.mVisibleInApp + ", mLaneGuidanceCurrentIndex=" + this.mLaneGuidanceCurrentIndex + ", mLaneGuidanceTotalCount=" + this.mLaneGuidanceTotalCount + ", mLaneGuidanceBeingShowInApp=" + this.mLaneGuidanceBeingShowInApp + ", mSourceName=" + this.mSourceName + ", mSourceSupportsRouteGuidance=" + this.mSourceSupportsRouteGuidance + "]";
    }
}
