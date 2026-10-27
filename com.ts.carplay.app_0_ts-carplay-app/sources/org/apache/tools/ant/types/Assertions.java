package org.apache.tools.ant.types;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Commandline;

/* JADX INFO: loaded from: classes.dex */
public class Assertions extends DataType implements Cloneable {
    private ArrayList assertionList = new ArrayList();
    private Boolean enableSystemAssertions;

    public void addEnable(EnabledAssertion assertion) {
        checkChildrenAllowed();
        this.assertionList.add(assertion);
    }

    public void addDisable(DisabledAssertion assertion) {
        checkChildrenAllowed();
        this.assertionList.add(assertion);
    }

    public void setEnableSystemAssertions(Boolean enableSystemAssertions) {
        checkAttributesAllowed();
        this.enableSystemAssertions = enableSystemAssertions;
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference ref) {
        if (this.assertionList.size() > 0 || this.enableSystemAssertions != null) {
            throw tooManyAttributes();
        }
        super.setRefid(ref);
    }

    private Assertions getFinalReference() {
        if (getRefid() == null) {
            return this;
        }
        Object o = getRefid().getReferencedObject(getProject());
        if (!(o instanceof Assertions)) {
            throw new BuildException("reference is of wrong type");
        }
        return (Assertions) o;
    }

    public int size() {
        Assertions clause = getFinalReference();
        return clause.getFinalSize();
    }

    private int getFinalSize() {
        return this.assertionList.size() + (this.enableSystemAssertions != null ? 1 : 0);
    }

    public void applyAssertions(List commandList) {
        getProject().log("Applying assertions", 4);
        Assertions clause = getFinalReference();
        if (Boolean.TRUE.equals(clause.enableSystemAssertions)) {
            getProject().log("Enabling system assertions", 4);
            commandList.add("-enablesystemassertions");
        } else if (Boolean.FALSE.equals(clause.enableSystemAssertions)) {
            getProject().log("disabling system assertions", 4);
            commandList.add("-disablesystemassertions");
        }
        for (BaseAssertion assertion : clause.assertionList) {
            String arg = assertion.toCommand();
            Project project = getProject();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("adding assertion ");
            stringBuffer.append(arg);
            project.log(stringBuffer.toString(), 4);
            commandList.add(arg);
        }
    }

    public void applyAssertions(CommandlineJava command) {
        Assertions clause = getFinalReference();
        if (Boolean.TRUE.equals(clause.enableSystemAssertions)) {
            addVmArgument(command, "-enablesystemassertions");
        } else if (Boolean.FALSE.equals(clause.enableSystemAssertions)) {
            addVmArgument(command, "-disablesystemassertions");
        }
        for (BaseAssertion assertion : clause.assertionList) {
            String arg = assertion.toCommand();
            addVmArgument(command, arg);
        }
    }

    public void applyAssertions(ListIterator commandIterator) {
        getProject().log("Applying assertions", 4);
        Assertions clause = getFinalReference();
        if (Boolean.TRUE.equals(clause.enableSystemAssertions)) {
            getProject().log("Enabling system assertions", 4);
            commandIterator.add("-enablesystemassertions");
        } else if (Boolean.FALSE.equals(clause.enableSystemAssertions)) {
            getProject().log("disabling system assertions", 4);
            commandIterator.add("-disablesystemassertions");
        }
        for (BaseAssertion assertion : clause.assertionList) {
            String arg = assertion.toCommand();
            Project project = getProject();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("adding assertion ");
            stringBuffer.append(arg);
            project.log(stringBuffer.toString(), 4);
            commandIterator.add(arg);
        }
    }

    private static void addVmArgument(CommandlineJava command, String arg) {
        Commandline.Argument argument = command.createVmArgument();
        argument.setValue(arg);
    }

    public Object clone() throws CloneNotSupportedException {
        Assertions that = (Assertions) super.clone();
        that.assertionList = (ArrayList) this.assertionList.clone();
        return that;
    }

    public static abstract class BaseAssertion {
        private String className;
        private String packageName;

        public abstract String getCommandPrefix();

        public void setClass(String className) {
            this.className = className;
        }

        public void setPackage(String packageName) {
            this.packageName = packageName;
        }

        protected String getClassName() {
            return this.className;
        }

        protected String getPackageName() {
            return this.packageName;
        }

        public String toCommand() {
            if (getPackageName() != null && getClassName() != null) {
                throw new BuildException("Both package and class have been set");
            }
            StringBuffer command = new StringBuffer(getCommandPrefix());
            if (getPackageName() != null) {
                command.append(':');
                command.append(getPackageName());
                if (!command.toString().endsWith("...")) {
                    command.append("...");
                }
            } else if (getClassName() != null) {
                command.append(':');
                command.append(getClassName());
            }
            return command.toString();
        }
    }

    public static class EnabledAssertion extends BaseAssertion {
        @Override // org.apache.tools.ant.types.Assertions.BaseAssertion
        public String getCommandPrefix() {
            return "-ea";
        }
    }

    public static class DisabledAssertion extends BaseAssertion {
        @Override // org.apache.tools.ant.types.Assertions.BaseAssertion
        public String getCommandPrefix() {
            return "-da";
        }
    }
}
