package com.ts.car.sdk.network;

import java.util.ArrayList;
import java.util.List;
import ts.car.service.ncm.data.IfaceInfo;
import ts.car.service.ncm.data.IpConfiguration;
import ts.car.service.ncm.data.StaticIpConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class Convert {
    public static IfaceInfo convertAidlToIfaceInfoData(ts.car.ncm.network.IfaceInfo info) {
        if (info == null) {
            return null;
        }
        IfaceInfo ifaceInfo = new IfaceInfo();
        ifaceInfo.setIfaceName(info.getIfaceName());
        ifaceInfo.setIfaceType(info.getIfaceType());
        int ifaceState = info.getIfaceState().ordinal();
        ifaceInfo.setIfaceState(IfaceInfo.IfaceState.values()[ifaceState]);
        ifaceInfo.setIpConfig(convertAidlToIpConfigData(info.getIpConfig()));
        return ifaceInfo;
    }

    public static List<IfaceInfo> convertAidlToIfaceInfosData(List<ts.car.ncm.network.IfaceInfo> infos) {
        if (infos == null) {
            return null;
        }
        List<IfaceInfo> ifaceInfos = new ArrayList<>();
        for (int i = 0; i < infos.size(); i++) {
            ifaceInfos.add(convertAidlToIfaceInfoData(infos.get(i)));
        }
        return ifaceInfos;
    }

    public static IpConfiguration convertAidlToIpConfigData(ts.car.ncm.network.IpConfiguration config) {
        if (config == null) {
            return null;
        }
        IpConfiguration ipConfig = new IpConfiguration();
        int ipAssignment = config.getIpAssignment().ordinal();
        ipConfig.setIpAssignment(IpConfiguration.IpAssignment.values()[ipAssignment]);
        StaticIpConfiguration staticIpConfig = new StaticIpConfiguration();
        staticIpConfig.setIpAddress(config.getStaticIpConfiguration().getIpAddress());
        staticIpConfig.setGateway(config.getStaticIpConfiguration().getGateway());
        staticIpConfig.setDnsServers(config.getStaticIpConfiguration().getDnsServers());
        staticIpConfig.setDomains(config.getStaticIpConfiguration().getDomains());
        ipConfig.setStaticIpConfiguration(staticIpConfig);
        return ipConfig;
    }
}
