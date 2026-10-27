package org.apache.tools.ant.types;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class Commandline implements Cloneable {
    protected static final String DISCLAIMER;
    private Vector arguments = new Vector();
    private String executable = null;

    static {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(StringUtils.LINE_SEP);
        stringBuffer.append("The ' characters around the executable and arguments are");
        stringBuffer.append(StringUtils.LINE_SEP);
        stringBuffer.append("not part of the command.");
        stringBuffer.append(StringUtils.LINE_SEP);
        DISCLAIMER = stringBuffer.toString();
    }

    public Commandline(String toProcess) {
        String[] tmp = translateCommandline(toProcess);
        if (tmp != null && tmp.length > 0) {
            setExecutable(tmp[0]);
            for (int i = 1; i < tmp.length; i++) {
                createArgument().setValue(tmp[i]);
            }
        }
    }

    public Commandline() {
    }

    public static class Argument extends ProjectComponent {
        private String[] parts;

        public void setValue(String value) {
            this.parts = new String[]{value};
        }

        public void setLine(String line) {
            if (line == null) {
                return;
            }
            this.parts = Commandline.translateCommandline(line);
        }

        public void setPath(Path value) {
            this.parts = new String[]{value.toString()};
        }

        public void setPathref(Reference value) {
            Path p = new Path(getProject());
            p.setRefid(value);
            this.parts = new String[]{p.toString()};
        }

        public void setFile(File value) {
            this.parts = new String[]{value.getAbsolutePath()};
        }

        public String[] getParts() {
            return this.parts;
        }
    }

    public class Marker {
        private int position;
        private int realPos = -1;

        Marker(int position) {
            this.position = position;
        }

        public int getPosition() {
            if (this.realPos == -1) {
                this.realPos = Commandline.this.executable == null ? 0 : 1;
                for (int i = 0; i < this.position; i++) {
                    Argument arg = (Argument) Commandline.this.arguments.elementAt(i);
                    this.realPos += arg.getParts().length;
                }
            }
            int i2 = this.realPos;
            return i2;
        }
    }

    public Argument createArgument() {
        return createArgument(false);
    }

    public Argument createArgument(boolean insertAtStart) {
        Argument argument = new Argument();
        if (insertAtStart) {
            this.arguments.insertElementAt(argument, 0);
        } else {
            this.arguments.addElement(argument);
        }
        return argument;
    }

    public void setExecutable(String executable) {
        if (executable == null || executable.length() == 0) {
            return;
        }
        this.executable = executable.replace('/', File.separatorChar).replace('\\', File.separatorChar);
    }

    public String getExecutable() {
        return this.executable;
    }

    public void addArguments(String[] line) {
        for (String str : line) {
            createArgument().setValue(str);
        }
    }

    public String[] getCommandline() {
        List commands = new LinkedList();
        ListIterator list = commands.listIterator();
        addCommandToList(list);
        String[] result = new String[commands.size()];
        return (String[]) commands.toArray(result);
    }

    public void addCommandToList(ListIterator list) {
        String str = this.executable;
        if (str != null) {
            list.add(str);
        }
        addArgumentsToList(list);
    }

    public String[] getArguments() {
        List result = new ArrayList(this.arguments.size() * 2);
        addArgumentsToList(result.listIterator());
        String[] res = new String[result.size()];
        return (String[]) result.toArray(res);
    }

    public void addArgumentsToList(ListIterator list) {
        for (int i = 0; i < this.arguments.size(); i++) {
            Argument arg = (Argument) this.arguments.elementAt(i);
            String[] s = arg.getParts();
            if (s != null) {
                for (String str : s) {
                    list.add(str);
                }
            }
        }
    }

    public String toString() {
        return toString(getCommandline());
    }

    public static String quoteArgument(String argument) {
        if (argument.indexOf("\"") > -1) {
            if (argument.indexOf("'") > -1) {
                throw new BuildException("Can't handle single and double quotes in same argument");
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append('\'');
            stringBuffer.append(argument);
            stringBuffer.append('\'');
            return stringBuffer.toString();
        }
        if (argument.indexOf("'") > -1 || argument.indexOf(" ") > -1) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append('\"');
            stringBuffer2.append(argument);
            stringBuffer2.append('\"');
            return stringBuffer2.toString();
        }
        return argument;
    }

    public static String toString(String[] line) {
        if (line == null || line.length == 0) {
            return "";
        }
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < line.length; i++) {
            if (i > 0) {
                result.append(' ');
            }
            result.append(quoteArgument(line[i]));
        }
        return result.toString();
    }

    public static String[] translateCommandline(String toProcess) {
        if (toProcess == null || toProcess.length() == 0) {
            return new String[0];
        }
        int state = 0;
        StringTokenizer tok = new StringTokenizer(toProcess, "\"' ", true);
        Vector v = new Vector();
        StringBuffer current = new StringBuffer();
        boolean lastTokenHasBeenQuoted = false;
        while (tok.hasMoreTokens()) {
            String nextTok = tok.nextToken();
            if (state != 1) {
                if (state == 2) {
                    if ("\"".equals(nextTok)) {
                        lastTokenHasBeenQuoted = true;
                        state = 0;
                    } else {
                        current.append(nextTok);
                    }
                } else {
                    if ("'".equals(nextTok)) {
                        state = 1;
                    } else if ("\"".equals(nextTok)) {
                        state = 2;
                    } else if (" ".equals(nextTok)) {
                        if (lastTokenHasBeenQuoted || current.length() != 0) {
                            v.addElement(current.toString());
                            current = new StringBuffer();
                        }
                    } else {
                        current.append(nextTok);
                    }
                    lastTokenHasBeenQuoted = false;
                }
            } else if ("'".equals(nextTok)) {
                lastTokenHasBeenQuoted = true;
                state = 0;
            } else {
                current.append(nextTok);
            }
        }
        if (lastTokenHasBeenQuoted || current.length() != 0) {
            v.addElement(current.toString());
        }
        if (state == 1 || state == 2) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("unbalanced quotes in ");
            stringBuffer.append(toProcess);
            throw new BuildException(stringBuffer.toString());
        }
        String[] args = new String[v.size()];
        v.copyInto(args);
        return args;
    }

    public int size() {
        return getCommandline().length;
    }

    public Object clone() {
        try {
            Commandline c = (Commandline) super.clone();
            c.arguments = (Vector) this.arguments.clone();
            return c;
        } catch (CloneNotSupportedException e) {
            throw new BuildException(e);
        }
    }

    public void clear() {
        this.executable = null;
        this.arguments.removeAllElements();
    }

    public void clearArgs() {
        this.arguments.removeAllElements();
    }

    public Marker createMarker() {
        return new Marker(this.arguments.size());
    }

    public String describeCommand() {
        return describeCommand(this);
    }

    public String describeArguments() {
        return describeArguments(this);
    }

    public static String describeCommand(Commandline line) {
        return describeCommand(line.getCommandline());
    }

    public static String describeArguments(Commandline line) {
        return describeArguments(line.getArguments());
    }

    public static String describeCommand(String[] args) {
        if (args == null || args.length == 0) {
            return "";
        }
        StringBuffer buf = new StringBuffer("Executing '");
        buf.append(args[0]);
        buf.append("'");
        if (args.length > 0) {
            buf.append(" with ");
            buf.append(describeArguments(args, 1));
        } else {
            buf.append(DISCLAIMER);
        }
        return buf.toString();
    }

    public static String describeArguments(String[] args) {
        return describeArguments(args, 0);
    }

    protected static String describeArguments(String[] args, int offset) {
        if (args == null || args.length <= offset) {
            return "";
        }
        StringBuffer buf = new StringBuffer("argument");
        if (args.length > offset) {
            buf.append("s");
        }
        buf.append(":");
        buf.append(StringUtils.LINE_SEP);
        for (int i = offset; i < args.length; i++) {
            buf.append("'");
            buf.append(args[i]);
            buf.append("'");
            buf.append(StringUtils.LINE_SEP);
        }
        buf.append(DISCLAIMER);
        return buf.toString();
    }
}
