package com.core.system.service.common;

import com.core.system.service.CoreSystemServerImpl;
import com.core.system.service.data.ExecutorData;
import com.core.system.service.data.ServiceData;
import com.core.system.service.utils.ServiceDataManager;
import com.ts.car.sdk.core.Logger;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CoreServicesLoader {
    private static final String CORE_SYSTEM_SERVER_CONFIG_FILE = "system/etc/data/core_services_configs.json";
    private static final String DM_DEFAULT_ENCODING = "utf-8";
    private static final String JSON_KEY_EXECUTOR_DATA = "ExecutorData";
    private static final String JSON_KEY_SERVICE_DATA = "ServiceData";
    private static final String TAG = "CoreServicesLoader";

    private InputStream getResStream() throws IOException {
        return new FileInputStream(CORE_SYSTEM_SERVER_CONFIG_FILE);
    }

    public void loadConfigData(CoreSystemServerImpl coreSystemServerImpl, HashMap<String, ServiceDataManager> serviceDataMap, HashMap<String, ExecutorData> executorDataMap) {
        StringBuffer jsonData;
        Logger.debug("loadConfigData start");
        try {
            try {
                InputStream inputStream = getResStream();
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, DM_DEFAULT_ENCODING));
                    try {
                        try {
                            jsonData = new StringBuffer();
                        } catch (Throwable th) {
                            th = th;
                            Throwable th2 = th;
                            if (inputStream == null) {
                                throw th2;
                            }
                            try {
                                inputStream.close();
                                throw th2;
                            } catch (Throwable th3) {
                                th2.addSuppressed(th3);
                                throw th2;
                            }
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        } else {
                            jsonData.append(line);
                        }
                        Throwable th5 = th;
                        try {
                            br.close();
                            throw th5;
                        } catch (Throwable th6) {
                            th5.addSuppressed(th6);
                            throw th5;
                        }
                    }
                    Logger.debug(TAG, "loadService data : " + jsonData.toString());
                    JSONObject jsonObject = new JSONObject(jsonData.toString());
                    if (jsonObject.has(JSON_KEY_SERVICE_DATA)) {
                        JSONArray serviceDataJsonArray = jsonObject.getJSONArray(JSON_KEY_SERVICE_DATA);
                        int len = serviceDataJsonArray.length();
                        for (int i = 0; i < len; i++) {
                            JSONObject obj = (JSONObject) serviceDataJsonArray.get(i);
                            ServiceData serviceData = ServiceData.createFromJson(obj);
                            if (serviceData != null) {
                                Logger.debug(TAG, "ServiceData info : " + serviceData.toString());
                                ServiceDataManager serviceDataManager = new ServiceDataManager(serviceData, coreSystemServerImpl);
                                try {
                                    serviceDataMap.put(serviceData.getServiceName(), serviceDataManager);
                                } catch (Throwable th7) {
                                    th = th7;
                                }
                            } else {
                                Logger.warning(TAG, "ServiceData parse error, or mServiceUsedState is false, jsonData:" + obj.toString());
                            }
                        }
                        processServiceDataDependency(coreSystemServerImpl, serviceDataMap);
                    }
                    if (jsonObject.has(JSON_KEY_EXECUTOR_DATA)) {
                        JSONArray executorDataJsonArray = jsonObject.getJSONArray(JSON_KEY_EXECUTOR_DATA);
                        int len2 = executorDataJsonArray.length();
                        for (int i2 = 0; i2 < len2; i2++) {
                            ExecutorData executorData = ExecutorData.createFromJson(coreSystemServerImpl, (JSONObject) executorDataJsonArray.get(i2));
                            Logger.debug(TAG, "executorData : " + executorData.toString());
                            if (executorData == null) {
                                Logger.error("illegal ExecutorData json str, check it!!! data:" + jsonObject);
                            } else {
                                try {
                                    executorDataMap.put(executorData.getName(), executorData);
                                } catch (Throwable th8) {
                                    th = th8;
                                }
                            }
                        }
                    }
                    br.close();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Throwable th9) {
                    th = th9;
                }
            } catch (Throwable th10) {
                ex = th10;
                Logger.error("loadConfigData error", ex);
                serviceDataMap.clear();
                executorDataMap.clear();
                Logger.debug("loadConfigData end");
            }
        } catch (Throwable th11) {
            ex = th11;
            Logger.error("loadConfigData error", ex);
            serviceDataMap.clear();
            executorDataMap.clear();
            Logger.debug("loadConfigData end");
        }
        Logger.debug("loadConfigData end");
    }

    private void processServiceDataDependency(CoreSystemServerImpl coreSystemServerImpl, HashMap<String, ServiceDataManager> serviceDataMap) {
        for (ServiceDataManager serviceDataManager : serviceDataMap.values()) {
            for (String serviceName : serviceDataManager.getServiceData().getStrongDependServiceNameList()) {
                ServiceDataManager strongDependServiceDataManager = serviceDataMap.get(serviceName);
                if (strongDependServiceDataManager == null) {
                    ServiceData strongDependedServiceData = new ServiceData();
                    strongDependedServiceData.setServiceName(serviceName);
                    strongDependServiceDataManager = new ServiceDataManager(strongDependedServiceData, coreSystemServerImpl);
                    strongDependServiceDataManager.setServiceState(6);
                }
                serviceDataManager.mStrongDependServiceDataList.add(strongDependServiceDataManager);
                strongDependServiceDataManager.mStrongServiceDataListDependMe.add(serviceDataManager);
            }
            if (isLoopDependExit(serviceDataMap)) {
                Logger.error("ERROR! ServiceData contains loop strong dependence!!", new Exception());
                throw new IllegalStateException("LoopDependExit! change config file to fix it!!!!!!");
            }
            for (String serviceName2 : serviceDataManager.getServiceData().getWeakDependServiceNameList()) {
                ServiceDataManager weakDependedServiceDataManager = serviceDataMap.get(serviceName2);
                if (weakDependedServiceDataManager == null) {
                    ServiceData weakDependedServiceData = new ServiceData();
                    weakDependedServiceData.setServiceName(serviceName2);
                    weakDependedServiceDataManager = new ServiceDataManager(weakDependedServiceData, coreSystemServerImpl);
                    weakDependedServiceDataManager.setServiceState(6);
                }
                serviceDataManager.mWeakDependServiceDataList.add(weakDependedServiceDataManager);
                weakDependedServiceDataManager.mWeakServiceDataListDependMe.add(serviceDataManager);
            }
        }
    }

    private boolean isLoopDependExit(HashMap<String, ServiceDataManager> serviceDataMap) {
        for (ServiceDataManager serviceDataManager : serviceDataMap.values()) {
            boolean res = isServiceDataLoopDepend(serviceDataManager, null, null);
            if (res) {
                Logger.error("RecycleDependExit, service:" + serviceDataManager.getServiceData().getServiceName());
                return true;
            }
        }
        return false;
    }

    private boolean isServiceDataLoopDepend(ServiceDataManager headService, ServiceDataManager nodeService, ArrayList<ServiceDataManager> checkedList) {
        ArrayList<ServiceDataManager> serviceDataManagerList;
        if (checkedList == null) {
            checkedList = new ArrayList<>();
        }
        if (nodeService != null && headService.getServiceData().getServiceName().equals(nodeService.getServiceData().getServiceName())) {
            Logger.error("ServiceData loop Depend Exit! headServiceData:" + headService.getServiceData().getServiceName());
            return true;
        }
        if (nodeService == null) {
            serviceDataManagerList = headService.mStrongDependServiceDataList;
        } else {
            serviceDataManagerList = nodeService.mStrongDependServiceDataList;
        }
        if (serviceDataManagerList.size() == 0) {
            return false;
        }
        for (ServiceDataManager serviceDataManager : serviceDataManagerList) {
            if (!checkedList.contains(serviceDataManager)) {
                checkedList.add(serviceDataManager);
                boolean res = isServiceDataLoopDepend(headService, serviceDataManager, checkedList);
                if (res) {
                    Logger.error("ServiceData loop depend exit! , headServiceData:" + headService.getServiceData().getServiceName() + ", coreServiceList:" + serviceDataManager.getServiceData().getServiceName());
                    return true;
                }
            }
        }
        return false;
    }
}
