package cerence.ark.assistant.api.context.grpc;

import android.text.TextUtils;
import cerence.ark.assistant.api.context.BaseNluContext;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DeviceContextGrpc extends BaseNluContext {
    private static final String CONTEXT_KEY = "device_context";
    private final boolean mEvPluggable;
    private final FuelGradeEnum mFuelGrade;
    private final List<FuelTypeEnum> mFuelTypePrimary;
    private final List<FuelTypeEnum> mFuelTypeSecondary;
    private final String mVehicleModel;
    private final String mVehicleModelCode;

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public String getNluContextJsonKey() {
        return CONTEXT_KEY;
    }

    private DeviceContextGrpc(Builder builder) {
        this.mVehicleModel = builder.mVehicleModel;
        this.mVehicleModelCode = builder.mVehicleModelCode;
        this.mEvPluggable = builder.mEvPluggable;
        this.mFuelTypePrimary = builder.mFuelTypePrimary;
        this.mFuelTypeSecondary = builder.mFuelTypeSecondary;
        this.mFuelGrade = builder.mFuelGrade;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // cerence.ark.assistant.api.context.BaseNluContext
    public JSONObject getNluContextJsonData() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.mVehicleModel)) {
            jSONObject.put("vehicle_model", this.mVehicleModel);
        }
        if (!TextUtils.isEmpty(this.mVehicleModelCode)) {
            jSONObject.put("vehicle_model_code", this.mVehicleModelCode);
        }
        jSONObject.put("ev_pluggable", this.mEvPluggable);
        List<FuelTypeEnum> list = this.mFuelTypePrimary;
        if (list != null && !list.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            Iterator<FuelTypeEnum> it = this.mFuelTypePrimary.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().getValue());
            }
            jSONObject.put("fuel_type_primary", jSONArray);
        }
        List<FuelTypeEnum> list2 = this.mFuelTypeSecondary;
        if (list2 != null && !list2.isEmpty()) {
            JSONArray jSONArray2 = new JSONArray();
            Iterator<FuelTypeEnum> it2 = this.mFuelTypeSecondary.iterator();
            while (it2.hasNext()) {
                jSONArray2.put(it2.next().getValue());
            }
            jSONObject.put("fuel_type_secondary", jSONArray2);
        }
        FuelGradeEnum fuelGradeEnum = this.mFuelGrade;
        if (fuelGradeEnum != null) {
            jSONObject.put("fuel_grade", fuelGradeEnum.getValue());
        }
        return jSONObject;
    }

    public enum FuelTypeEnum {
        E10("e10"),
        E15("e15"),
        E25("e25"),
        E85("e85"),
        E100("e100"),
        DIESEL("diesel"),
        M85("m85"),
        LPG("lpg"),
        CNG("cng"),
        HYDROGEN("hydrogen"),
        ELECTRIC("electric");

        private final String mValue;

        FuelTypeEnum(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public enum FuelGradeEnum {
        REGULAR("regular"),
        MIDGRADE("midgrade"),
        PREMIUM("premium");

        private final String mValue;

        FuelGradeEnum(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    private static class EmptyDeviceContextGrpc extends DeviceContextGrpc {
        private EmptyDeviceContextGrpc(Builder builder) {
            super(builder);
        }

        @Override // cerence.ark.assistant.api.context.grpc.DeviceContextGrpc, cerence.ark.assistant.api.context.BaseNluContext
        public JSONObject getNluContextJsonData() {
            return new JSONObject();
        }
    }

    public static class Builder {
        private boolean mEvPluggable;
        private FuelGradeEnum mFuelGrade;
        private List<FuelTypeEnum> mFuelTypePrimary;
        private List<FuelTypeEnum> mFuelTypeSecondary;
        private String mVehicleModel;
        private String mVehicleModelCode;

        private Builder() {
        }

        public Builder setVehicleModel(String str) {
            this.mVehicleModel = str;
            return this;
        }

        public Builder setVehicleModelCode(String str) {
            this.mVehicleModelCode = str;
            return this;
        }

        public Builder setEvPluggable(boolean z) {
            this.mEvPluggable = z;
            return this;
        }

        public Builder setFuelTypePrimary(List<FuelTypeEnum> list) {
            this.mFuelTypePrimary = list;
            return this;
        }

        public Builder setFuelTypeSecondary(List<FuelTypeEnum> list) {
            this.mFuelTypeSecondary = list;
            return this;
        }

        public Builder setFuelGrade(FuelGradeEnum fuelGradeEnum) {
            this.mFuelGrade = fuelGradeEnum;
            return this;
        }

        public DeviceContextGrpc build() {
            return new DeviceContextGrpc(this);
        }

        public DeviceContextGrpc buildEmptyDeviceContextGrpc() {
            return new EmptyDeviceContextGrpc(this);
        }
    }
}
