package com.byd.common.constants;

import c.a.b.a;

/* JADX INFO: loaded from: classes.dex */
public class ResultCodeConsts {

    public enum EcuCodeEnum {
        INVALID_UPDATE_STATUS(0, a.n0),
        VALID_UPDATE_STATUS(1, a.T0),
        ECU_UPDATE_SUCCESS(1000, a.f2314d),
        ECU_ROLLBACK_SUCCESS(1001, a.f2313c),
        FAIL_ECU_UPDATE_THREE_TIMES(1002, a.u),
        FAIL_ECU_ROLLBACK_THREE_TIMES(1003, a.s),
        FAIL_ECU_UNCOMPRESS(1004, a.t),
        FAIL_ECU_VERIFY(1005, a.v),
        FAIL_ECU_DEPENDENCIES_SATISFIED(1006, a.m),
        FAIL_ECU_FILE_NO_EXIST(1007, a.n),
        FAIL_XCD_FILE_PARSE_ERROR(1008, a.l0),
        FAIL_XCD_FILE_CHECKSUM_ERROR(1009, a.k0),
        FAIL_MATCH_ECU_SOFT_CODE_STRATEGY_VEHICLE(1010, a.N),
        FAIL_MATCH_XCD_SOFT_CODE_STRATEGY_DOWNLOAD(1011, a.Q),
        FAIL_MATCH_XCD_SOFT_CODE_DOWNLOAD_VEHICLE(1012, a.P),
        FAIL_MATCH_XCD_SOFT_CODE_TARGET_SOURCE(1013, a.R),
        FAIL_ECU_GET_SOFT_CODE(1014, a.o),
        FAIL_ECU_GET_SOFT_VERSION_BUT_SUCCESS_GET_SOFT_CODE(1015, a.q),
        FAIL_ECU_GET_SOFT_VERSION(1016, a.p),
        FAIL_MATCH_ECU_SOFT_VERSION_STRATEGY_SOURCE(1017, a.O),
        FAIL_MATCH_XCD_SOFT_VERSION_STRATEGY_TARGET(1018, a.T),
        FAIL_MATCH_XCD_SOFT_VERSION_DOWNLOAD_SOURCE(1019, a.S),
        FAIL_WRITE_MCU_START_OTA(1020, a.i0),
        FAIL_WRITE_MCU_TARGET_ID(1021, a.j0),
        FAIL_MEMORY_ALLOCATION(1022, a.U),
        FAIL_XCD_NONEXISTENCE(1023, a.m0),
        FAIL_ERASURE_ROUTINE(1024, a.D),
        FAIL_REQUEST_SEED(1025, a.b0),
        FAIL_SEND_SECRET_KEY(1026, a.d0),
        FAIL_ENTER_PROGRAMMING_SESSION_MODE(1027, a.z),
        FAIL_REQUEST_SEED_BACKUP(1028, a.c0),
        FAIL_SEND_SECRET_KEY_BACKUP(1029, a.e0),
        FAIL_ERASURE_MEMORY(1030, a.C),
        FAIL_REQUEST_DOWNLOAD(1031, a.a0),
        FAIL_DOWNLOAD_XCD_DATA(1032, a.k),
        FAIL_QUIT_XCD_TRANSMISSION(1033, a.Y),
        FAIL_TRANSFER_CRC_VERIFY(1034, a.f0),
        FAIL_HARDWARE_RESET(1035, a.I),
        FAIL_RECOVER_COMMUNICATION_MESSAGE_SEND(1036, a.Z),
        FAIL_CONTROL_AND_RECOVER_DTC(1037, a.f2318h),
        FAIL_HIGH_VOLTAGE_DISCHARGE_TIMEOUT(1038, a.K),
        FAIL_BOOST_VOLTAGE(1039, a.f2315e),
        DOWNLOAD_PACKAGES_SUCCESS(1200, a.f2311a),
        DOWNLOAD_PACKAGES_UNLOAD(1201, a.f2312b),
        FAIL_DOWNLOAD_PACKAGES(1300, a.f2319i),
        FAIL_GET_SOFT_VERSION(1301, a.H),
        FAIL_VERIFY_FILE_MD5(1302, a.h0),
        FAIL_DOWNLOAD_PACKAGES_THREE_TIMES(1303, a.j),
        FAIL_GET_ECU_ETHERNET_RANDOM(1304, a.G);

        public int ecuCode;
        public int ecuCodeMsg;

        EcuCodeEnum(int i2, int i3) {
            this.ecuCode = i2;
            this.ecuCodeMsg = i3;
        }
    }

    public enum NormalCodeEnum {
        LARGER_VERSION_UPDATE_SUCCESS(2000, a.p0),
        LARGER_VERSION_UPDATE_FAILURE(2001, a.o0),
        FAIL_UPDATE_LIST_IS_EMPTY(2002, a.g0),
        FAIL_POLICY_FILE_FORMAT_IS_INCORRECT(2003, a.X),
        FAIL_INTERNAL_STORAGE_SPACE_IS_INSUFFICIENT(2004, a.L),
        FAIL_CONNECT_INFORMATION_SERVICE(2005, a.f2317g),
        OTA_CONDITION_UNMATCHED_EPB_NOT_UP_OR_GEAR_NOT_P(2007, a.x0),
        OTA_CONDITION_UNMATCHED_SOC_NOT_SATISFIED(2008, a.H0),
        OTA_CONDITION_UNMATCHED_ENGINE_HOOD_OPEN(2009, a.w0),
        OTA_CONDITION_UNMATCHED_GENERATES_ELECTRICITY(2010, a.y0),
        OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING(2011, a.t0),
        OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING_DM(2147, a.u0),
        OTA_CONDITION_UNMATCHED_SMART_CHARGING(2012, a.G0),
        OTA_CONDITION_UNMATCHED_POWER_OFF_FAILURE(2013, a.B0),
        OTA_CONDITION_UNMATCHED_POWER_ON_FAILURE(2014, a.C0),
        OTA_CONDITION_UNMATCHED_DC_REDUCE_VOLTAGE_FAILURE(2015, a.v0),
        OTA_CONDITION_UNMATCHED_POWER_LEVEL_NOT_ON(2016, a.A0),
        OTA_CONDITION_UNMATCHED_BCM_RELAY_PULL_IN_FAILURE(2017, a.r0),
        OTA_CONDITION_UNMATCHED_REQUEST_HIGH_POWER_FAILURE(2018, a.E0),
        OTA_CONDITION_UNMATCHED_SET_APPOINTMENT_FAILURE(2019, a.F0),
        OTA_CONDITION_UNMATCHED_CAR_UNLOCK(2020, a.s0),
        OTA_NOW_CONDITION_UNMATCHED_CAR_UNLOCK(2145, a.J0),
        OTA_CONDITION_UNMATCHED_ABNORMAL_POWER_OFF(2021, a.q0),
        FAIL_OTA_UPDATE_RESULT_ABNORMAL(2022, a.W),
        FAIL_VERIFY_FILES_MD5(2023, a.h0),
        FAIL_ENTER_RECOVERY(2027, a.A),
        OTA_CONDITION_UNMATCHED_HANDBRAKE_NOT_ENGAGED_OR_GEAR_NOT_N(2029, a.z0),
        FAIL_ECU_INFO_FILE_NONEXISTENT(2130, a.r),
        FAIL_ENTER_BCM_OTA_MODE(2131, a.w),
        FAIL_ENTER_VEHICLE_OTA_UPDATE(2132, a.B),
        FAIL_ENTER_FACTORY_UPDATE_TIME_EXPIRED(2133, a.y),
        FAIL_ENTER_FACTORY_UPDATE_POWER_LEVEL_NOT_OFF(2134, a.x),
        FAIL_GET_AUTO_TYPE(2135, a.F),
        FAIL_BCM_FORBID(2136, a.f2316f),
        FAIL_LOCAL_FORCIBLE_VERSION(2137, a.M),
        FAIL_ECALL_OPENING(2138, a.l),
        OTA_CONDITION_UNMATCHED_BATTERY_VOLTAGE_NOT_SATISFIED(2139, a.f2315e),
        OTA_CONDITION_UNMATCHED_RECOVERY_BATTERY_VOLTAGE_NOT_SATISFIED(2140, a.D0),
        OTA_CONDITION_UNMATCHED_TEMPERATURE(2141, a.I0),
        FAIL_OTA_PACKAGES_NONEXISTENT(2142, a.V),
        FAIL_HAS_EXTRA_EQUIPMENT(2143, a.J),
        FAIL_FACTORY_UPDATE_SCREEN_ON(2144, a.E),
        UPDATE_MEDIA_SUCCESS(3000, a.S0),
        UPDATE_MEDIA_FAILURE(3001, a.R0),
        OTA_ECU_DIAG_FUNC_SWITCH_CLOSE(2146, a.Q0);

        public int normalCode;
        public int normalCodeMsg;

        NormalCodeEnum(int i2, int i3) {
            this.normalCode = i2;
            this.normalCodeMsg = i3;
        }
    }

    public static NormalCodeEnum a(int i2) {
        if (i2 == 2027) {
            return NormalCodeEnum.FAIL_ENTER_RECOVERY;
        }
        if (i2 == 2029) {
            return NormalCodeEnum.OTA_CONDITION_UNMATCHED_HANDBRAKE_NOT_ENGAGED_OR_GEAR_NOT_N;
        }
        if (i2 == 2146) {
            return NormalCodeEnum.OTA_ECU_DIAG_FUNC_SWITCH_CLOSE;
        }
        if (i2 == 3000) {
            return NormalCodeEnum.UPDATE_MEDIA_SUCCESS;
        }
        if (i2 == 3001) {
            return NormalCodeEnum.UPDATE_MEDIA_FAILURE;
        }
        switch (i2) {
            case 2000:
                return NormalCodeEnum.LARGER_VERSION_UPDATE_SUCCESS;
            case 2001:
                return NormalCodeEnum.LARGER_VERSION_UPDATE_FAILURE;
            case 2002:
                return NormalCodeEnum.FAIL_UPDATE_LIST_IS_EMPTY;
            case 2003:
                return NormalCodeEnum.FAIL_POLICY_FILE_FORMAT_IS_INCORRECT;
            case 2004:
                return NormalCodeEnum.FAIL_INTERNAL_STORAGE_SPACE_IS_INSUFFICIENT;
            case 2005:
                return NormalCodeEnum.FAIL_CONNECT_INFORMATION_SERVICE;
            default:
                switch (i2) {
                    case 2007:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_EPB_NOT_UP_OR_GEAR_NOT_P;
                    case 2008:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_SOC_NOT_SATISFIED;
                    case 2009:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_ENGINE_HOOD_OPEN;
                    case 2010:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_GENERATES_ELECTRICITY;
                    case 2011:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_CHARGING_AND_DISCHARGING;
                    case 2012:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_SMART_CHARGING;
                    case 2013:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_POWER_OFF_FAILURE;
                    case 2014:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_POWER_ON_FAILURE;
                    case 2015:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_DC_REDUCE_VOLTAGE_FAILURE;
                    case 2016:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_POWER_LEVEL_NOT_ON;
                    case 2017:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_BCM_RELAY_PULL_IN_FAILURE;
                    case 2018:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_REQUEST_HIGH_POWER_FAILURE;
                    case 2019:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_SET_APPOINTMENT_FAILURE;
                    case 2020:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_CAR_UNLOCK;
                    case 2021:
                        return NormalCodeEnum.OTA_CONDITION_UNMATCHED_ABNORMAL_POWER_OFF;
                    case 2022:
                        return NormalCodeEnum.FAIL_OTA_UPDATE_RESULT_ABNORMAL;
                    case 2023:
                        return NormalCodeEnum.FAIL_VERIFY_FILES_MD5;
                    default:
                        switch (i2) {
                            case 2130:
                                return NormalCodeEnum.FAIL_ECU_INFO_FILE_NONEXISTENT;
                            case 2131:
                                return NormalCodeEnum.FAIL_ENTER_BCM_OTA_MODE;
                            case 2132:
                                return NormalCodeEnum.FAIL_ENTER_VEHICLE_OTA_UPDATE;
                            case 2133:
                                return NormalCodeEnum.FAIL_ENTER_FACTORY_UPDATE_TIME_EXPIRED;
                            case 2134:
                                return NormalCodeEnum.FAIL_ENTER_FACTORY_UPDATE_POWER_LEVEL_NOT_OFF;
                            case 2135:
                                return NormalCodeEnum.FAIL_GET_AUTO_TYPE;
                            case 2136:
                                return NormalCodeEnum.FAIL_BCM_FORBID;
                            case 2137:
                                return NormalCodeEnum.FAIL_LOCAL_FORCIBLE_VERSION;
                            case 2138:
                                return NormalCodeEnum.FAIL_ECALL_OPENING;
                            case 2139:
                                return NormalCodeEnum.OTA_CONDITION_UNMATCHED_BATTERY_VOLTAGE_NOT_SATISFIED;
                            case 2140:
                                return NormalCodeEnum.OTA_CONDITION_UNMATCHED_RECOVERY_BATTERY_VOLTAGE_NOT_SATISFIED;
                            case 2141:
                                return NormalCodeEnum.OTA_CONDITION_UNMATCHED_TEMPERATURE;
                            case 2142:
                                return NormalCodeEnum.FAIL_OTA_PACKAGES_NONEXISTENT;
                            case 2143:
                                return NormalCodeEnum.FAIL_HAS_EXTRA_EQUIPMENT;
                            case 2144:
                                return NormalCodeEnum.FAIL_FACTORY_UPDATE_SCREEN_ON;
                            default:
                                return null;
                        }
                }
        }
    }
}
