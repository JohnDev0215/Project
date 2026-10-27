package org.apache.tools.ant.taskdefs;

import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.util.JavaEnvUtils;

/* JADX INFO: loaded from: classes.dex */
public class GenerateKey extends Task {
    protected String alias;
    protected String dname;
    protected DistinguishedName expandedDname;
    protected String keyalg;
    protected String keypass;
    protected int keysize;
    protected String keystore;
    protected String sigalg;
    protected String storepass;
    protected String storetype;
    protected int validity;
    protected boolean verbose;

    public static class DnameParam {
        private String name;
        private String value;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static class DistinguishedName {
        private Vector params = new Vector();

        public Object createParam() {
            DnameParam param = new DnameParam();
            this.params.addElement(param);
            return param;
        }

        public Enumeration getParams() {
            return this.params.elements();
        }

        public String toString() {
            int size = this.params.size();
            StringBuffer sb = new StringBuffer();
            boolean firstPass = true;
            for (int i = 0; i < size; i++) {
                if (!firstPass) {
                    sb.append(" ,");
                }
                firstPass = false;
                DnameParam param = (DnameParam) this.params.elementAt(i);
                sb.append(encode(param.getName()));
                sb.append('=');
                sb.append(encode(param.getValue()));
            }
            return sb.toString();
        }

        public String encode(String string) {
            int end = string.indexOf(44);
            if (-1 == end) {
                return string;
            }
            StringBuffer sb = new StringBuffer();
            int start = 0;
            while (-1 != end) {
                sb.append(string.substring(start, end));
                sb.append("\\,");
                start = end + 1;
                end = string.indexOf(44, start);
            }
            sb.append(string.substring(start));
            return sb.toString();
        }
    }

    public DistinguishedName createDname() throws BuildException {
        if (this.expandedDname != null) {
            throw new BuildException("DName sub-element can only be specified once.");
        }
        if (this.dname != null) {
            throw new BuildException("It is not possible to specify dname  both as attribute and element.");
        }
        DistinguishedName distinguishedName = new DistinguishedName();
        this.expandedDname = distinguishedName;
        return distinguishedName;
    }

    public void setDname(String dname) {
        if (this.expandedDname != null) {
            throw new BuildException("It is not possible to specify dname  both as attribute and element.");
        }
        this.dname = dname;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public void setStorepass(String storepass) {
        this.storepass = storepass;
    }

    public void setStoretype(String storetype) {
        this.storetype = storetype;
    }

    public void setKeypass(String keypass) {
        this.keypass = keypass;
    }

    public void setSigalg(String sigalg) {
        this.sigalg = sigalg;
    }

    public void setKeyalg(String keyalg) {
        this.keyalg = keyalg;
    }

    public void setKeysize(String keysize) throws BuildException {
        try {
            this.keysize = Integer.parseInt(keysize);
        } catch (NumberFormatException e) {
            throw new BuildException("KeySize attribute should be a integer");
        }
    }

    public void setValidity(String validity) throws BuildException {
        try {
            this.validity = Integer.parseInt(validity);
        } catch (NumberFormatException e) {
            throw new BuildException("Validity attribute should be a integer");
        }
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (JavaEnvUtils.isJavaVersion("1.1")) {
            throw new BuildException("The genkey task is only available on JDK versions 1.2 or greater");
        }
        if (this.alias == null) {
            throw new BuildException("alias attribute must be set");
        }
        if (this.storepass == null) {
            throw new BuildException("storepass attribute must be set");
        }
        if (this.dname == null && this.expandedDname == null) {
            throw new BuildException("dname must be set");
        }
        StringBuffer sb = new StringBuffer();
        sb.append("-genkey ");
        if (this.verbose) {
            sb.append("-v ");
        }
        sb.append("-alias \"");
        sb.append(this.alias);
        sb.append("\" ");
        if (this.dname != null) {
            sb.append("-dname \"");
            sb.append(this.dname);
            sb.append("\" ");
        }
        if (this.expandedDname != null) {
            sb.append("-dname \"");
            sb.append(this.expandedDname);
            sb.append("\" ");
        }
        if (this.keystore != null) {
            sb.append("-keystore \"");
            sb.append(this.keystore);
            sb.append("\" ");
        }
        if (this.storepass != null) {
            sb.append("-storepass \"");
            sb.append(this.storepass);
            sb.append("\" ");
        }
        if (this.storetype != null) {
            sb.append("-storetype \"");
            sb.append(this.storetype);
            sb.append("\" ");
        }
        sb.append("-keypass \"");
        String str = this.keypass;
        if (str != null) {
            sb.append(str);
        } else {
            sb.append(this.storepass);
        }
        sb.append("\" ");
        if (this.sigalg != null) {
            sb.append("-sigalg \"");
            sb.append(this.sigalg);
            sb.append("\" ");
        }
        if (this.keyalg != null) {
            sb.append("-keyalg \"");
            sb.append(this.keyalg);
            sb.append("\" ");
        }
        if (this.keysize > 0) {
            sb.append("-keysize \"");
            sb.append(this.keysize);
            sb.append("\" ");
        }
        if (this.validity > 0) {
            sb.append("-validity \"");
            sb.append(this.validity);
            sb.append("\" ");
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Generating Key for ");
        stringBuffer.append(this.alias);
        log(stringBuffer.toString());
        ExecTask cmd = (ExecTask) getProject().createTask("exec");
        cmd.setExecutable(JavaEnvUtils.getJdkExecutable("keytool"));
        Commandline.Argument arg = cmd.createArg();
        arg.setLine(sb.toString());
        cmd.setFailonerror(true);
        cmd.setTaskName(getTaskName());
        cmd.execute();
    }
}
