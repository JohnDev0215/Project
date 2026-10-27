package com.byd.vrassistant.customizedimpl;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain;
import cerence.ark.assistant.controller.domain.hvac.param.CheckHvacFunctionAvailabilityParam;
import cerence.ark.assistant.controller.domain.hvac.param.GetACTemperatureParam;
import cerence.ark.assistant.controller.domain.hvac.param.GetDefrosterStateParam;
import cerence.ark.assistant.controller.domain.hvac.param.GetFanDirectionParam;
import cerence.ark.assistant.controller.domain.hvac.param.GetHavcLevelParam;
import cerence.ark.assistant.controller.domain.hvac.param.GetHvacStateParam;
import cerence.ark.assistant.controller.domain.hvac.param.GetMaxHavcLevelParam;
import cerence.ark.assistant.controller.domain.hvac.param.HvacInterfaceWaitTimerParam;
import cerence.ark.assistant.controller.domain.hvac.param.SetACTemperatureParam;
import cerence.ark.assistant.controller.domain.hvac.param.SetDefrosterStateParam;
import cerence.ark.assistant.controller.domain.hvac.param.SetFanDirectionParam;
import cerence.ark.assistant.controller.domain.hvac.param.SetHvacLevelParam;
import cerence.ark.assistant.controller.domain.hvac.param.SetHvacStateParam;
import cerence.ark.assistant.controller.domain.hvac.result.CheckHvacFunctionAvailabilityResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetACTemperatureResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetDefrosterStateResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetFanDirectionResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetHavcLevelResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetHvacMinTempUnitResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetHvacStateResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetHvacTypeResult;
import cerence.ark.assistant.controller.domain.hvac.result.GetMaxHavcLevelResult;
import cerence.ark.assistant.controller.domain.hvac.result.HvacCheckisHeatPumpResult;
import cerence.ark.assistant.controller.domain.hvac.result.IsPowerOnResult;
import cerence.ark.assistant.controller.domain.hvac.result.SetACTemperatureResult;
import cerence.ark.assistant.controller.domain.hvac.result.SetHvacLevelResult;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.conversation.HvacCheckHelper;
import com.byd.vrassistant.conversation.HvacGetStateHelper;
import com.byd.vrassistant.conversation.HvacLevelHelper;
import com.byd.vrassistant.conversation.HvacSetStateHelper;
import com.byd.vrassistant.conversation.HvacTemperatureHelper;
import com.byd.vrassistant.conversation.HvacWindHelper;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;

/* JADX INFO: loaded from: classes.dex */
public class CustomHvacDomain extends AbsHVACDomain {
    private static final String TAG = TagUtil.getTagName(CustomHvacDomain.class);

    protected CustomHvacDomain(Dhi dhi) {
        super(dhi);
        RLog.i(TAG, "CustomHvacDomain: ");
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void isPowerOn(IResponseSender<IsPowerOnResult> iResponseSender) {
        try {
            HvacWindHelper.getInstance().isPowerOn(iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "isPowerOn: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void getHvacState(GetHvacStateParam getHvacStateParam, IResponseSender<GetHvacStateResult> iResponseSender) {
        try {
            HvacGetStateHelper.getInstance().getHvacState(getHvacStateParam, iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "getHvacState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void getACTemperature(GetACTemperatureParam getACTemperatureParam, IResponseSender<GetACTemperatureResult> iResponseSender) {
        try {
            HvacTemperatureHelper.getInstance().getACTemperature(getACTemperatureParam, iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "getACTemperature: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void getHavcLevel(GetHavcLevelParam getHavcLevelParam, IResponseSender<GetHavcLevelResult> iResponseSender) {
        try {
            HvacLevelHelper.getInstance().getHavcLevel(getHavcLevelParam, iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "getHavcLevel: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void getMaxHavcLevel(GetMaxHavcLevelParam getMaxHavcLevelParam, IResponseSender<GetMaxHavcLevelResult> iResponseSender) {
        try {
            HvacLevelHelper.getInstance().getMaxHavcLevel(getMaxHavcLevelParam, iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "getMaxHavcLevel: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void getFanDirection(GetFanDirectionParam getFanDirectionParam, IResponseSender<GetFanDirectionResult> iResponseSender) {
        try {
            HvacWindHelper.getInstance().getFanDirection(getFanDirectionParam, iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "getFanDirection: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void getDefrosterState(GetDefrosterStateParam getDefrosterStateParam, IResponseSender<GetDefrosterStateResult> iResponseSender) {
        try {
            HvacWindHelper.getInstance().getDefrosterState(getDefrosterStateParam, iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "getDefrosterState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void checkHvacFunctionAvailability(CheckHvacFunctionAvailabilityParam checkHvacFunctionAvailabilityParam, IResponseSender<CheckHvacFunctionAvailabilityResult> iResponseSender) {
        try {
            HvacCheckHelper.getInstance().checkHvacFunctionAvailability(checkHvacFunctionAvailabilityParam, iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "checkHvacFunctionAvailability: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void setFanDirection(SetFanDirectionParam setFanDirectionParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            HvacWindHelper.getInstance().setFanDirection(setFanDirectionParam, iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "setFanDirection: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void setHvacState(SetHvacStateParam setHvacStateParam, IResponseSender<BaseResult> iResponseSender) {
        HvacSetStateHelper.getInstance().setHvacState(setHvacStateParam, iResponseSender);
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void setHvacLevel(SetHvacLevelParam setHvacLevelParam, IResponseSender<SetHvacLevelResult> iResponseSender) {
        try {
            HvacLevelHelper.getInstance().setHvacLevel(setHvacLevelParam, iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "setHvacLevel: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void setDefrosterState(SetDefrosterStateParam setDefrosterStateParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            HvacWindHelper.getInstance().setDefrosterState(setDefrosterStateParam, iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "setDefrosterState: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void setACTemperature(SetACTemperatureParam setACTemperatureParam, IResponseSender<SetACTemperatureResult> iResponseSender) {
        try {
            HvacTemperatureHelper.getInstance().setACTemperature(setACTemperatureParam, iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "setACTemperature: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void hvacInterfaceWaitTimer(HvacInterfaceWaitTimerParam hvacInterfaceWaitTimerParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            HvacWindHelper.getInstance().hvacInterfaceWaitTimer(hvacInterfaceWaitTimerParam, iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "hvacInterfaceWaitTimer: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void hvacCheckisHeatPump(IResponseSender<HvacCheckisHeatPumpResult> iResponseSender) {
        try {
            HvacGetStateHelper.getInstance().hvacCheckisHeatPump(iResponseSender);
        } catch (Error | Exception e) {
            RLog.e(TAG, "hvacCheckisHeatPump: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void getHvacType(IResponseSender<GetHvacTypeResult> iResponseSender) {
        try {
            iResponseSender.send(new Response("UNSUPPORTED"));
        } catch (Error | Exception e) {
            RLog.e(TAG, "getHvacType: ", e);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.hvac.AbsHVACDomain
    protected void getHvacMinTempUnit(IResponseSender<GetHvacMinTempUnitResult> iResponseSender) {
        HvacTemperatureHelper.getInstance().getHvacMinTempUnit(BydVoiceApplication.getContext(), iResponseSender);
    }

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
        RLog.i(TAG, "activate: 空调域激活");
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
        RLog.i(TAG, "deactivate: 空调域销毁");
    }
}
