package org.apache.shiro.authz.permission;

import java.util.Set;
import org.apache.shiro.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class DomainPermission extends WildcardPermission {
    private static final long serialVersionUID = 1;
    private Set<String> actions;
    private String domain;
    private Set<String> targets;

    public DomainPermission() {
        this.domain = getDomain(getClass());
        setParts(getDomain(getClass()));
    }

    public DomainPermission(String str) {
        this.domain = getDomain(getClass());
        this.actions = StringUtils.splitToSet(str, ",");
        encodeParts(this.domain, str, null);
    }

    public DomainPermission(String str, String str2) {
        this.domain = getDomain(getClass());
        this.actions = StringUtils.splitToSet(str, ",");
        this.targets = StringUtils.splitToSet(str2, ",");
        encodeParts(this.domain, str, str2);
    }

    protected DomainPermission(Set<String> set, Set<String> set2) {
        String domain = getDomain(getClass());
        this.domain = domain;
        setParts(domain, set, set2);
    }

    private void encodeParts(String str, String str2, String str3) {
        if (!StringUtils.hasText(str)) {
            throw new IllegalArgumentException("domain argument cannot be null or empty.");
        }
        StringBuilder sb = new StringBuilder(str);
        if (!StringUtils.hasText(str2)) {
            if (StringUtils.hasText(str3)) {
                sb.append(":").append("*");
            }
        } else {
            sb.append(":").append(str2);
        }
        if (StringUtils.hasText(str3)) {
            sb.append(":").append(str3);
        }
        setParts(sb.toString());
    }

    protected void setParts(String str, Set<String> set, Set<String> set2) {
        encodeParts(str, StringUtils.toDelimitedString(set, ","), StringUtils.toDelimitedString(set2, ","));
        this.domain = str;
        this.actions = set;
        this.targets = set2;
    }

    protected String getDomain(Class<? extends DomainPermission> cls) {
        String lowerCase = cls.getSimpleName().toLowerCase();
        int iLastIndexOf = lowerCase.lastIndexOf("permission");
        return iLastIndexOf != -1 ? lowerCase.substring(0, iLastIndexOf) : lowerCase;
    }

    public String getDomain() {
        return this.domain;
    }

    protected void setDomain(String str) {
        String str2 = this.domain;
        if (str2 == null || !str2.equals(str)) {
            this.domain = str;
            setParts(str, this.actions, this.targets);
        }
    }

    public Set<String> getActions() {
        return this.actions;
    }

    protected void setActions(Set<String> set) {
        Set<String> set2 = this.actions;
        if (set2 == null || !set2.equals(set)) {
            this.actions = set;
            setParts(this.domain, set, this.targets);
        }
    }

    public Set<String> getTargets() {
        return this.targets;
    }

    protected void setTargets(Set<String> set) {
        Set<String> set2 = this.targets;
        if (set2 == null || !set2.equals(set)) {
            this.targets = set;
            setParts(this.domain, this.actions, set);
        }
    }
}
