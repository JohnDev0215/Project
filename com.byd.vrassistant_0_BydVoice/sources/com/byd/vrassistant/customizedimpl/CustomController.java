package com.byd.vrassistant.customizedimpl;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IRequest;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.api.controller.Domain;
import cerence.ark.assistant.api.controller.IHostController;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import cerence.ark.assistant.controller.domain.Domains;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CustomController implements IHostController {
    @Override // cerence.ark.assistant.api.controller.IController
    public void loadDomains(Map<String, Domain> map, Dhi dhi) {
        map.put(Domains.SYSTEM, new CustomSystemDomain(dhi));
        map.put(Domains.MEDIA, new CustomMusicDomain(dhi));
        map.put(Domains.PHONE, new CustomPhoneDomain(dhi));
        map.put(Domains.AUDIO_CONTROL, new CustomAudioControlDomain(dhi));
        map.put(Domains.RADIO, new CustomRadioDomain(dhi));
        map.put(Domains.VEHICLE_CONTROL, new CustomVehicleControlDomain(dhi));
        map.put(Domains.HVAC, new CustomHvacDomain(dhi));
        map.put(Domains.UDE, new CustomNaviDomain(dhi));
        map.put(Domains.KARAOKE, new CustomKaraokeDomain(dhi));
    }

    @Override // cerence.ark.assistant.api.controller.IHostController
    public IDynamicContentProvider loadDynamicContentProvider() {
        return new BydDynamicContentProvider();
    }

    @Override // cerence.ark.assistant.api.controller.IHostController
    public Response<BaseResult> onUnhandledRequest(IRequest iRequest) {
        return new Response<>("UNSUPPORTED");
    }
}
