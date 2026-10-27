package org.apache.tools.ant.taskdefs.condition;

import java.util.Locale;
import org.apache.tools.ant.BuildException;

/* JADX INFO: loaded from: classes.dex */
public class Os implements Condition {
    private String arch;
    private String family;
    private String name;
    private String version;
    private static final String OS_NAME = System.getProperty("os.name").toLowerCase(Locale.US);
    private static final String OS_ARCH = System.getProperty("os.arch").toLowerCase(Locale.US);
    private static final String OS_VERSION = System.getProperty("os.version").toLowerCase(Locale.US);
    private static final String PATH_SEP = System.getProperty("path.separator");

    public Os() {
    }

    public Os(String family) {
        setFamily(family);
    }

    public void setFamily(String f) {
        this.family = f.toLowerCase(Locale.US);
    }

    public void setName(String name) {
        this.name = name.toLowerCase(Locale.US);
    }

    public void setArch(String arch) {
        this.arch = arch.toLowerCase(Locale.US);
    }

    public void setVersion(String version) {
        this.version = version.toLowerCase(Locale.US);
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        return isOs(this.family, this.name, this.arch, this.version);
    }

    public static boolean isFamily(String family) {
        return isOs(family, null, null, null);
    }

    public static boolean isName(String name) {
        return isOs(null, name, null, null);
    }

    public static boolean isArch(String arch) {
        return isOs(null, null, arch, null);
    }

    public static boolean isVersion(String version) {
        return isOs(null, null, null, version);
    }

    public static boolean isOs(String family, String name, String arch, String version) {
        if (family == null && name == null && arch == null && version == null) {
            return false;
        }
        boolean isFamily = true;
        boolean isName = true;
        boolean isArch = true;
        boolean isVersion = true;
        if (family != null) {
            if (family.equals("windows")) {
                isFamily = OS_NAME.indexOf("windows") > -1;
            } else if (family.equals("os/2")) {
                isFamily = OS_NAME.indexOf("os/2") > -1;
            } else if (family.equals("netware")) {
                isFamily = OS_NAME.indexOf("netware") > -1;
            } else if (family.equals("dos")) {
                isFamily = PATH_SEP.equals(";") && !isFamily("netware");
            } else if (family.equals("mac")) {
                isFamily = OS_NAME.indexOf("mac") > -1;
            } else if (family.equals("tandem")) {
                isFamily = OS_NAME.indexOf("nonstop_kernel") > -1;
            } else if (family.equals("unix")) {
                isFamily = PATH_SEP.equals(":") && !isFamily("openvms") && (!isFamily("mac") || OS_NAME.endsWith("x"));
            } else if (family.equals("win9x")) {
                isFamily = isFamily("windows") && (OS_NAME.indexOf("95") >= 0 || OS_NAME.indexOf("98") >= 0 || OS_NAME.indexOf("me") >= 0 || OS_NAME.indexOf("ce") >= 0);
            } else if (family.equals("z/os")) {
                isFamily = OS_NAME.indexOf("z/os") > -1 || OS_NAME.indexOf("os/390") > -1;
            } else if (family.equals("os/400")) {
                isFamily = OS_NAME.indexOf("os/400") > -1;
            } else if (family.equals("openvms")) {
                isFamily = OS_NAME.indexOf("openvms") > -1;
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Don't know how to detect os family \"");
                stringBuffer.append(family);
                stringBuffer.append("\"");
                throw new BuildException(stringBuffer.toString());
            }
        }
        if (name != null) {
            isName = name.equals(OS_NAME);
        }
        if (arch != null) {
            isArch = arch.equals(OS_ARCH);
        }
        if (version != null) {
            isVersion = version.equals(OS_VERSION);
        }
        boolean retValue = isFamily && isName && isArch && isVersion;
        return retValue;
    }
}
