package com.amazon.mp3.configuration.models;

import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class EndPoint {
    private HashMap<String, String> mAssocHandle;
    private HashMap<String, String> mDomainName;
    private String mId;
    private HashMap<String, String> mPath;
    private HashMap<String, String> mPort;
    private HashMap<String, String> mProtocol;
    private HashMap<String, String> mSubdomainPrefix;
    private final HashMap<String, String> mUrlParams;

    public EndPoint(String name, HashMap<String, String> domainName, HashMap<String, String> assocHandle, HashMap<String, String> subdomainPrefix, HashMap<String, String> port, HashMap<String, String> protocol, HashMap<String, String> path, HashMap<String, String> urlParams) {
        this.mId = name;
        this.mDomainName = domainName;
        this.mAssocHandle = assocHandle;
        this.mSubdomainPrefix = subdomainPrefix;
        this.mPort = port;
        this.mProtocol = protocol;
        this.mPath = path;
        this.mUrlParams = urlParams;
    }

    public String id() {
        return this.mId;
    }

    public String domainName(String countryCode) {
        return this.mDomainName.get(countryCode);
    }

    public String subdomainPrefix(String countryCode) {
        return this.mSubdomainPrefix.get(countryCode);
    }

    public String port(String countryCode) {
        return this.mPort.get(countryCode);
    }

    public String protocol(String countryCode) {
        return this.mProtocol.get(countryCode);
    }

    public String path(String key) {
        return this.mPath.get(key);
    }

    public HashMap<String, String> getUrlParams() {
        return this.mUrlParams;
    }
}
