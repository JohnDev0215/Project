package org.apache.tools.ant.taskdefs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class Length extends Task implements Condition {
    private static final String ALL = "all";
    private static final String EACH = "each";
    private static final String LENGTH_REQUIRED = "Use of the Length condition requires that the length attribute be set.";
    private static final String STRING = "string";
    private Vector filesets;
    private Long length;
    private String property;
    private String string;
    private Boolean trim;
    private String mode = ALL;
    private When when = When.EQUAL;

    /* JADX INFO: renamed from: org.apache.tools.ant.taskdefs.Length$1, reason: invalid class name */
    class AnonymousClass1 {
    }

    public synchronized void setProperty(String property) {
        this.property = property;
    }

    public synchronized void setFile(File file) {
        FileSet fs = new FileSet();
        fs.setFile(file);
        add(fs);
    }

    public synchronized void add(FileSet fs) {
        if (fs == null) {
            return;
        }
        Vector vector = this.filesets == null ? new Vector() : this.filesets;
        this.filesets = vector;
        vector.add(fs);
    }

    public synchronized void setLength(long ell) {
        this.length = new Long(ell);
    }

    public synchronized void setWhen(When w) {
        this.when = w;
    }

    public synchronized void setMode(FileMode m) {
        this.mode = m.getValue();
    }

    public synchronized void setString(String string) {
        this.string = string;
        this.mode = STRING;
    }

    public synchronized void setTrim(boolean trim) {
        this.trim = trim ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean getTrim() {
        Boolean bool = this.trim;
        return bool != null && bool.booleanValue();
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        validate();
        PrintStream ps = new PrintStream(this.property != null ? new PropertyOutputStream(this, null) : new LogOutputStream((Task) this, 2));
        if (STRING.equals(this.mode)) {
            ps.print(getLength(this.string, getTrim()));
            ps.close();
        } else if (EACH.equals(this.mode)) {
            handleResources(new EachHandler(this, ps));
        } else if (ALL.equals(this.mode)) {
            handleResources(new AllHandler(this, ps));
        }
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() {
        Long ell;
        validate();
        if (this.length == null) {
            throw new BuildException(LENGTH_REQUIRED);
        }
        if (STRING.equals(this.mode)) {
            ell = new Long(getLength(this.string, getTrim()));
        } else {
            ConditionHandler h = new ConditionHandler(this);
            handleResources(h);
            ell = new Long(h.getLength());
        }
        int w = this.when.getIndex();
        int comp = ell.compareTo(this.length);
        if (w != 0 || comp != 0) {
            if (w != 1 || comp <= 0) {
                return w == 2 && comp < 0;
            }
            return true;
        }
        return true;
    }

    private void validate() {
        if (this.string != null) {
            Vector vector = this.filesets;
            if (vector != null && vector.size() > 0) {
                throw new BuildException("the string length function is incompatible with the file length function");
            }
            if (!STRING.equals(this.mode)) {
                throw new BuildException("the mode attribute is for use with the file/resource length function");
            }
            return;
        }
        if (this.filesets != null) {
            if (!EACH.equals(this.mode) && !ALL.equals(this.mode)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("invalid mode setting for file length function: \"");
                stringBuffer.append(this.mode);
                stringBuffer.append("\"");
                throw new BuildException(stringBuffer.toString());
            }
            if (this.trim != null) {
                throw new BuildException("the trim attribute is for use with the string length function only");
            }
            return;
        }
        throw new BuildException("you must set either the string attribute or specify one or more files using the file attribute or nested filesets");
    }

    private void handleResources(Handler h) {
        Length length = this;
        for (FileSet fs : length.filesets) {
            DirectoryScanner ds = fs.getDirectoryScanner(getProject());
            String[] f = ds.getIncludedFiles();
            int j = 0;
            while (j < f.length) {
                Resource r = ds.getResource(f[j]);
                if (!r.isExists()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(r.getName());
                    stringBuffer.append(" does not exist");
                    length.log(stringBuffer.toString(), 0);
                } else if (r.isDirectory()) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(r.getName());
                    stringBuffer2.append(" is a directory; length unspecified");
                    length.log(stringBuffer2.toString(), 0);
                } else {
                    File basedir = ds.getBasedir();
                    String s = FileUtils.getFileUtils().resolveFile(basedir, r.getName()).getAbsolutePath();
                    h.handle(new Resource(s, true, r.getLastModified(), false, r.getSize()));
                }
                j++;
                length = this;
            }
        }
        h.complete();
    }

    private static long getLength(String s, boolean t) {
        return (t ? s.trim() : s).length();
    }

    public static class FileMode extends EnumeratedAttribute {
        static final String[] MODES = {Length.EACH, Length.ALL};

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return MODES;
        }
    }

    public static class When extends EnumeratedAttribute {
        private static final String[] VALUES = {"equal", "greater", "less"};
        private static final When EQUAL = new When("equal");

        public When() {
        }

        public When(String value) {
            setValue(value);
        }

        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return VALUES;
        }
    }

    private class PropertyOutputStream extends ByteArrayOutputStream {
        private PropertyOutputStream() {
        }

        /* synthetic */ PropertyOutputStream(Length x0, AnonymousClass1 x1) {
            this();
        }

        @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            Length.this.getProject().setNewProperty(Length.this.property, new String(toByteArray()).trim());
        }
    }

    private abstract class Handler {
        PrintStream ps;

        protected abstract void handle(Resource resource);

        Handler(PrintStream ps) {
            this.ps = ps;
        }

        void complete() {
            this.ps.close();
        }
    }

    private class EachHandler extends Handler {
        private final /* synthetic */ Length this$0;

        EachHandler(Length this$0, PrintStream ps) {
            super(ps);
            this.this$0 = this$0;
        }

        @Override // org.apache.tools.ant.taskdefs.Length.Handler
        protected void handle(Resource r) {
            this.ps.print(r.getName());
            this.ps.print(" : ");
            long size = r.getSize();
            if (size == -1) {
                this.ps.println("unknown");
            } else {
                this.ps.println(size);
            }
        }
    }

    private class AllHandler extends Handler {
        long accum;
        private final /* synthetic */ Length this$0;

        AllHandler(Length this$0, PrintStream ps) {
            super(ps);
            this.this$0 = this$0;
            this.accum = 0L;
        }

        @Override // org.apache.tools.ant.taskdefs.Length.Handler
        protected synchronized void handle(Resource r) {
            long size = r.getSize();
            if (size == -1) {
                Length length = this.this$0;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Size unknown for ");
                stringBuffer.append(r.getName());
                length.log(stringBuffer.toString(), 1);
            } else {
                this.accum += size;
            }
        }

        @Override // org.apache.tools.ant.taskdefs.Length.Handler
        void complete() {
            this.ps.print(this.accum);
            super.complete();
        }
    }

    private class ConditionHandler extends AllHandler {
        private final /* synthetic */ Length this$0;

        ConditionHandler(Length this$0) {
            super(this$0, null);
            this.this$0 = this$0;
        }

        @Override // org.apache.tools.ant.taskdefs.Length.AllHandler, org.apache.tools.ant.taskdefs.Length.Handler
        void complete() {
        }

        long getLength() {
            return this.accum;
        }
    }
}
