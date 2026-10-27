package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class VRCommand implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912130;

    @SerializedName("FunctionType")
    private FunctionType functionType = FunctionType.GAS_STATION_SERVICE;

    @SerializedName("CommandState")
    private Integer commandState = 0;

    public VRCommand() {
    }

    public VRCommand(FunctionType functionType, Integer num) {
        setFunctionType(functionType);
        setCommandState(num);
    }

    public FunctionType getFunctionType() {
        return this.functionType;
    }

    public VRCommand setFunctionType(FunctionType functionType) {
        this.functionType = functionType;
        return this;
    }

    public Integer getCommandState() {
        return this.commandState;
    }

    public VRCommand setCommandState(Integer num) {
        if (num == null) {
            throw new NullPointerException("commandState cannot be null");
        }
        this.commandState = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VRCommand vRCommand = (VRCommand) obj;
        return Objects.equals(this.functionType, vRCommand.functionType) && Objects.equals(this.commandState, vRCommand.commandState);
    }

    public int hashCode() {
        return Objects.hash(this.functionType, this.commandState);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
