package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class RouteCalculationState implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480909959;

    @SerializedName("CalcResult")
    private CalculationResult calcResult = CalculationResult.CALCULATED_SUCCESSFUL;

    @SerializedName("NumOfRoute")
    private Integer numOfRoute = 0;

    public RouteCalculationState() {
    }

    public RouteCalculationState(CalculationResult calculationResult, Integer num) {
        setCalcResult(calculationResult);
        setNumOfRoute(num);
    }

    public CalculationResult getCalcResult() {
        return this.calcResult;
    }

    public RouteCalculationState setCalcResult(CalculationResult calculationResult) {
        this.calcResult = calculationResult;
        return this;
    }

    public Integer getNumOfRoute() {
        return this.numOfRoute;
    }

    public RouteCalculationState setNumOfRoute(Integer num) {
        if (num == null) {
            throw new NullPointerException("numOfRoute cannot be null");
        }
        this.numOfRoute = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RouteCalculationState routeCalculationState = (RouteCalculationState) obj;
        return Objects.equals(this.calcResult, routeCalculationState.calcResult) && Objects.equals(this.numOfRoute, routeCalculationState.numOfRoute);
    }

    public int hashCode() {
        return Objects.hash(this.calcResult, this.numOfRoute);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
