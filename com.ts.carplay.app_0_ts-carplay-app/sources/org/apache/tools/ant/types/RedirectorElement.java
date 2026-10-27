package org.apache.tools.ant.types;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Redirector;

/* JADX INFO: loaded from: classes.dex */
public class RedirectorElement extends DataType {
    static /* synthetic */ Class class$org$apache$tools$ant$util$MergingMapper;
    private Boolean alwaysLog;
    private Boolean append;
    private Boolean createEmptyFiles;
    private String errorEncoding;
    private Mapper errorMapper;
    private String errorProperty;
    private String inputEncoding;
    private Mapper inputMapper;
    private String inputString;
    private Boolean logError;
    private String outputEncoding;
    private Mapper outputMapper;
    private String outputProperty;
    private boolean usingInput = false;
    private boolean usingOutput = false;
    private boolean usingError = false;
    private Vector inputFilterChains = new Vector();
    private Vector outputFilterChains = new Vector();
    private Vector errorFilterChains = new Vector();

    public void addConfiguredInputMapper(Mapper inputMapper) {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        if (this.inputMapper != null) {
            if (this.usingInput) {
                throw new BuildException("attribute \"input\" cannot coexist with a nested <inputmapper>");
            }
            throw new BuildException("Cannot have > 1 <inputmapper>");
        }
        this.inputMapper = inputMapper;
    }

    public void addConfiguredOutputMapper(Mapper outputMapper) {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        if (this.outputMapper != null) {
            if (this.usingOutput) {
                throw new BuildException("attribute \"output\" cannot coexist with a nested <outputmapper>");
            }
            throw new BuildException("Cannot have > 1 <outputmapper>");
        }
        this.outputMapper = outputMapper;
    }

    public void addConfiguredErrorMapper(Mapper errorMapper) {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        if (this.errorMapper != null) {
            if (this.usingError) {
                throw new BuildException("attribute \"error\" cannot coexist with a nested <errormapper>");
            }
            throw new BuildException("Cannot have > 1 <errormapper>");
        }
        this.errorMapper = errorMapper;
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference r) throws BuildException {
        if (this.usingInput || this.usingOutput || this.usingError || this.inputString != null || this.logError != null || this.append != null || this.createEmptyFiles != null || this.inputEncoding != null || this.outputEncoding != null || this.errorEncoding != null || this.outputProperty != null || this.errorProperty != null) {
            throw tooManyAttributes();
        }
        super.setRefid(r);
    }

    public void setInput(File input) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        if (this.inputString != null) {
            throw new BuildException("The \"input\" and \"inputstring\" attributes cannot both be specified");
        }
        this.usingInput = true;
        this.inputMapper = createMergeMapper(input);
    }

    public void setInputString(String inputString) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        if (this.usingInput) {
            throw new BuildException("The \"input\" and \"inputstring\" attributes cannot both be specified");
        }
        this.inputString = inputString;
    }

    public void setOutput(File out) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        if (out == null) {
            throw new IllegalArgumentException("output file specified as null");
        }
        this.usingOutput = true;
        this.outputMapper = createMergeMapper(out);
    }

    public void setOutputEncoding(String outputEncoding) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.outputEncoding = outputEncoding;
    }

    public void setErrorEncoding(String errorEncoding) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.errorEncoding = errorEncoding;
    }

    public void setInputEncoding(String inputEncoding) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.inputEncoding = inputEncoding;
    }

    public void setLogError(boolean logError) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.logError = logError ? Boolean.TRUE : Boolean.FALSE;
    }

    public void setError(File error) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        if (error == null) {
            throw new IllegalArgumentException("error file specified as null");
        }
        this.usingError = true;
        this.errorMapper = createMergeMapper(error);
    }

    public void setOutputProperty(String outputProperty) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.outputProperty = outputProperty;
    }

    public void setAppend(boolean append) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.append = append ? Boolean.TRUE : Boolean.FALSE;
    }

    public void setAlwaysLog(boolean alwaysLog) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.alwaysLog = alwaysLog ? Boolean.TRUE : Boolean.FALSE;
    }

    public void setCreateEmptyFiles(boolean createEmptyFiles) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.createEmptyFiles = createEmptyFiles ? Boolean.TRUE : Boolean.FALSE;
    }

    public void setErrorProperty(String errorProperty) {
        if (isReference()) {
            throw tooManyAttributes();
        }
        this.errorProperty = errorProperty;
    }

    public FilterChain createInputFilterChain() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        FilterChain result = new FilterChain();
        result.setProject(getProject());
        this.inputFilterChains.add(result);
        return result;
    }

    public FilterChain createOutputFilterChain() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        FilterChain result = new FilterChain();
        result.setProject(getProject());
        this.outputFilterChains.add(result);
        return result;
    }

    public FilterChain createErrorFilterChain() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        FilterChain result = new FilterChain();
        result.setProject(getProject());
        this.errorFilterChains.add(result);
        return result;
    }

    public void configure(Redirector redirector) {
        configure(redirector, null);
    }

    public void configure(Redirector redirector, String sourcefile) {
        if (isReference()) {
            getRef().configure(redirector, sourcefile);
            return;
        }
        Boolean bool = this.alwaysLog;
        if (bool != null) {
            redirector.setAlwaysLog(bool.booleanValue());
        }
        Boolean bool2 = this.logError;
        if (bool2 != null) {
            redirector.setLogError(bool2.booleanValue());
        }
        Boolean bool3 = this.append;
        if (bool3 != null) {
            redirector.setAppend(bool3.booleanValue());
        }
        Boolean bool4 = this.createEmptyFiles;
        if (bool4 != null) {
            redirector.setCreateEmptyFiles(bool4.booleanValue());
        }
        String str = this.outputProperty;
        if (str != null) {
            redirector.setOutputProperty(str);
        }
        String str2 = this.errorProperty;
        if (str2 != null) {
            redirector.setErrorProperty(str2);
        }
        String str3 = this.inputString;
        if (str3 != null) {
            redirector.setInputString(str3);
        }
        Mapper mapper = this.inputMapper;
        if (mapper != null) {
            String[] inputTargets = null;
            try {
                inputTargets = mapper.getImplementation().mapFileName(sourcefile);
            } catch (NullPointerException enPeaEx) {
                if (sourcefile != null) {
                    throw enPeaEx;
                }
            }
            if (inputTargets != null && inputTargets.length > 0) {
                redirector.setInput(toFileArray(inputTargets));
            }
        }
        Mapper mapper2 = this.outputMapper;
        if (mapper2 != null) {
            String[] outputTargets = null;
            try {
                outputTargets = mapper2.getImplementation().mapFileName(sourcefile);
            } catch (NullPointerException enPeaEx2) {
                if (sourcefile != null) {
                    throw enPeaEx2;
                }
            }
            if (outputTargets != null && outputTargets.length > 0) {
                redirector.setOutput(toFileArray(outputTargets));
            }
        }
        Mapper mapper3 = this.errorMapper;
        if (mapper3 != null) {
            String[] errorTargets = null;
            try {
                errorTargets = mapper3.getImplementation().mapFileName(sourcefile);
            } catch (NullPointerException enPeaEx3) {
                if (sourcefile != null) {
                    throw enPeaEx3;
                }
            }
            if (errorTargets != null && errorTargets.length > 0) {
                redirector.setError(toFileArray(errorTargets));
            }
        }
        if (this.inputFilterChains.size() > 0) {
            redirector.setInputFilterChains(this.inputFilterChains);
        }
        if (this.outputFilterChains.size() > 0) {
            redirector.setOutputFilterChains(this.outputFilterChains);
        }
        if (this.errorFilterChains.size() > 0) {
            redirector.setErrorFilterChains(this.errorFilterChains);
        }
        String str4 = this.inputEncoding;
        if (str4 != null) {
            redirector.setInputEncoding(str4);
        }
        String str5 = this.outputEncoding;
        if (str5 != null) {
            redirector.setOutputEncoding(str5);
        }
        String str6 = this.errorEncoding;
        if (str6 != null) {
            redirector.setErrorEncoding(str6);
        }
    }

    protected Mapper createMergeMapper(File destfile) {
        Mapper result = new Mapper(getProject());
        Class clsClass$ = class$org$apache$tools$ant$util$MergingMapper;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.tools.ant.util.MergingMapper");
            class$org$apache$tools$ant$util$MergingMapper = clsClass$;
        }
        result.setClassname(clsClass$.getName());
        result.setTo(destfile.getAbsolutePath());
        return result;
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    protected File[] toFileArray(String[] name) {
        if (name == null) {
            return null;
        }
        ArrayList list = new ArrayList(name.length);
        for (int i = 0; i < name.length; i++) {
            if (name[i] != null) {
                list.add(getProject().resolveFile(name[i]));
            }
        }
        return (File[]) list.toArray(new File[list.size()]);
    }

    protected void dieOnCircularReference() throws BuildException {
        if (isChecked()) {
            return;
        }
        Stack s = new Stack();
        s.push(this);
        dieOnCircularReference(s, getProject());
    }

    @Override // org.apache.tools.ant.types.DataType
    protected void dieOnCircularReference(Stack stk, Project p) throws BuildException {
        if (isChecked()) {
            return;
        }
        if (isReference()) {
            super.dieOnCircularReference(stk, p);
            return;
        }
        Mapper[] m = {this.inputMapper, this.outputMapper, this.errorMapper};
        for (int i = 0; i < m.length; i++) {
            if (m[i] != null) {
                stk.push(m[i]);
                m[i].dieOnCircularReference(stk, p);
                stk.pop();
            }
        }
        Vector[] v = {this.inputFilterChains, this.outputFilterChains, this.errorFilterChains};
        for (int i2 = 0; i2 < v.length; i2++) {
            if (v[i2] != null) {
                for (FilterChain fc : v[i2]) {
                    stk.push(fc);
                    fc.dieOnCircularReference(stk, p);
                    stk.pop();
                }
            }
        }
        setChecked(true);
    }

    private RedirectorElement getRef() {
        dieOnCircularReference();
        Object o = getRefid().getReferencedObject(getProject());
        if (!(o instanceof RedirectorElement)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getRefid().getRefId());
            stringBuffer.append(" doesn't denote a RedirectorElement");
            throw new BuildException(stringBuffer.toString());
        }
        return (RedirectorElement) o;
    }
}
