package org.apache.tools.ant.types;

import java.net.SocketPermission;
import java.security.UnresolvedPermission;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PropertyPermission;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ExitException;

/* JADX INFO: loaded from: classes.dex */
public class Permissions {
    private boolean active;
    private boolean delegateToOldSM;
    private java.security.Permissions granted;
    private List grantedPermissions;
    private SecurityManager origSm;
    private List revokedPermissions;

    /* JADX INFO: renamed from: org.apache.tools.ant.types.Permissions$1, reason: invalid class name */
    class AnonymousClass1 {
    }

    public Permissions() {
        this.grantedPermissions = new LinkedList();
        this.revokedPermissions = new LinkedList();
        this.granted = null;
        this.origSm = null;
        this.active = false;
        this.delegateToOldSM = false;
    }

    public Permissions(boolean delegateToOldSM) {
        this.grantedPermissions = new LinkedList();
        this.revokedPermissions = new LinkedList();
        this.granted = null;
        this.origSm = null;
        this.active = false;
        this.delegateToOldSM = false;
        this.delegateToOldSM = delegateToOldSM;
    }

    public void addConfiguredGrant(Permission perm) {
        this.grantedPermissions.add(perm);
    }

    public void addConfiguredRevoke(Permission perm) {
        this.revokedPermissions.add(perm);
    }

    public void setSecurityManager() throws BuildException {
        this.origSm = System.getSecurityManager();
        init();
        System.setSecurityManager(new MySM(this, null));
        this.active = true;
    }

    private void init() throws BuildException {
        this.granted = new java.security.Permissions();
        Iterator i = this.revokedPermissions.listIterator();
        while (i.hasNext()) {
            Permission p = (Permission) i.next();
            if (p.getClassName() == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Revoked permission ");
                stringBuffer.append(p);
                stringBuffer.append(" does not contain a class.");
                throw new BuildException(stringBuffer.toString());
            }
        }
        Iterator i2 = this.grantedPermissions.listIterator();
        while (i2.hasNext()) {
            Permission p2 = (Permission) i2.next();
            if (p2.getClassName() == null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Granted permission ");
                stringBuffer2.append(p2);
                stringBuffer2.append(" does not contain a class.");
                throw new BuildException(stringBuffer2.toString());
            }
            java.security.Permission perm = new UnresolvedPermission(p2.getClassName(), p2.getName(), p2.getActions(), null);
            this.granted.add(perm);
        }
        this.granted.add(new SocketPermission("localhost:1024-", "listen"));
        this.granted.add(new PropertyPermission("java.version", "read"));
        this.granted.add(new PropertyPermission("java.vendor", "read"));
        this.granted.add(new PropertyPermission("java.vendor.url", "read"));
        this.granted.add(new PropertyPermission("java.class.version", "read"));
        this.granted.add(new PropertyPermission("os.name", "read"));
        this.granted.add(new PropertyPermission("os.version", "read"));
        this.granted.add(new PropertyPermission("os.arch", "read"));
        this.granted.add(new PropertyPermission("file.encoding", "read"));
        this.granted.add(new PropertyPermission("file.separator", "read"));
        this.granted.add(new PropertyPermission("path.separator", "read"));
        this.granted.add(new PropertyPermission("line.separator", "read"));
        this.granted.add(new PropertyPermission("java.specification.version", "read"));
        this.granted.add(new PropertyPermission("java.specification.vendor", "read"));
        this.granted.add(new PropertyPermission("java.specification.name", "read"));
        this.granted.add(new PropertyPermission("java.vm.specification.version", "read"));
        this.granted.add(new PropertyPermission("java.vm.specification.vendor", "read"));
        this.granted.add(new PropertyPermission("java.vm.specification.name", "read"));
        this.granted.add(new PropertyPermission("java.vm.version", "read"));
        this.granted.add(new PropertyPermission("java.vm.vendor", "read"));
        this.granted.add(new PropertyPermission("java.vm.name", "read"));
    }

    public void restoreSecurityManager() {
        this.active = false;
        System.setSecurityManager(this.origSm);
    }

    private class MySM extends SecurityManager {
        private MySM() {
        }

        /* synthetic */ MySM(Permissions x0, AnonymousClass1 x1) {
            this();
        }

        @Override // java.lang.SecurityManager
        public void checkExit(int status) {
            java.security.Permission perm = new RuntimePermission("exitVM", null);
            try {
                checkPermission(perm);
            } catch (SecurityException e) {
                throw new ExitException(e.getMessage(), status);
            }
        }

        @Override // java.lang.SecurityManager
        public void checkPermission(java.security.Permission perm) {
            if (Permissions.this.active) {
                if (!Permissions.this.delegateToOldSM || perm.getName().equals("exitVM")) {
                    if (!Permissions.this.granted.implies(perm)) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Permission ");
                        stringBuffer.append(perm);
                        stringBuffer.append(" was not granted.");
                        throw new SecurityException(stringBuffer.toString());
                    }
                    checkRevoked(perm);
                    return;
                }
                boolean permOK = false;
                if (Permissions.this.granted.implies(perm)) {
                    permOK = true;
                }
                checkRevoked(perm);
                if (!permOK && Permissions.this.origSm != null) {
                    Permissions.this.origSm.checkPermission(perm);
                }
            }
        }

        private void checkRevoked(java.security.Permission perm) {
            Iterator i = Permissions.this.revokedPermissions.listIterator();
            while (i.hasNext()) {
                if (((Permission) i.next()).matches(perm)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Permission ");
                    stringBuffer.append(perm);
                    stringBuffer.append(" was revoked.");
                    throw new SecurityException(stringBuffer.toString());
                }
            }
        }
    }

    public static class Permission {
        private String actionString;
        private Set actions;
        private String className;
        private String name;

        public void setClass(String aClass) {
            this.className = aClass.trim();
        }

        public String getClassName() {
            return this.className;
        }

        public void setName(String aName) {
            this.name = aName.trim();
        }

        public String getName() {
            return this.name;
        }

        public void setActions(String actions) {
            this.actionString = actions;
            if (actions.length() > 0) {
                this.actions = parseActions(actions);
            }
        }

        public String getActions() {
            return this.actionString;
        }

        boolean matches(java.security.Permission perm) {
            if (!this.className.equals(perm.getClass().getName())) {
                return false;
            }
            String str = this.name;
            if (str != null) {
                if (str.endsWith("*")) {
                    String name = perm.getName();
                    String str2 = this.name;
                    if (!name.startsWith(str2.substring(0, str2.length() - 1))) {
                        return false;
                    }
                } else if (!this.name.equals(perm.getName())) {
                    return false;
                }
            }
            if (this.actions != null) {
                Set as = parseActions(perm.getActions());
                int size = as.size();
                as.removeAll(this.actions);
                if (as.size() == size) {
                    return false;
                }
            }
            return true;
        }

        private Set parseActions(String actions) {
            Set result = new HashSet();
            StringTokenizer tk = new StringTokenizer(actions, ",");
            while (tk.hasMoreTokens()) {
                String item = tk.nextToken().trim();
                if (!item.equals("")) {
                    result.add(item);
                }
            }
            return result;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Permission: ");
            stringBuffer.append(this.className);
            stringBuffer.append(" (\"");
            stringBuffer.append(this.name);
            stringBuffer.append("\", \"");
            stringBuffer.append(this.actions);
            stringBuffer.append("\")");
            return stringBuffer.toString();
        }
    }
}
