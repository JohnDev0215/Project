package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class VRStatus implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912130;

    @SerializedName("FunctionType")
    private FunctionType functionType = FunctionType.GAS_STATION_SERVICE;

    @SerializedName("VRCommandOnOff")
    private VRCommandOnOff vRCommandOnOff = VRCommandOnOff.ENABLE;

    public VRStatus() {
    }

    public VRStatus(FunctionType functionType, VRCommandOnOff vRCommandOnOff) {
        setFunctionType(functionType);
        setVRCommandOnOff(vRCommandOnOff);
    }

    public FunctionType getFunctionType() {
        return this.functionType;
    }

    public VRStatus setFunctionType(FunctionType functionType) {
        this.functionType = functionType;
        return this;
    }

    public VRCommandOnOff getVRCommandOnOff() {
        return this.vRCommandOnOff;
    }

    public VRStatus setVRCommandOnOff(VRCommandOnOff vRCommandOnOff) {
        this.vRCommandOnOff = vRCommandOnOff;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VRStatus vRStatus = (VRStatus) obj;
        return Objects.equals(this.functionType, vRStatus.functionType) && Objects.equals(this.vRCommandOnOff, vRStatus.vRCommandOnOff);
    }

    public int hashCode() {
        return Objects.hash(this.functionType, this.vRCommandOnOff);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
