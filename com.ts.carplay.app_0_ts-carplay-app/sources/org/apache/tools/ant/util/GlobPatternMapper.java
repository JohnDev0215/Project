package org.apache.tools.ant.util;

/* JADX INFO: loaded from: classes.dex */
public class GlobPatternMapper implements FileNameMapper {
    protected int postfixLength;
    protected int prefixLength;
    protected String fromPrefix = null;
    protected String fromPostfix = null;
    protected String toPrefix = null;
    protected String toPostfix = null;
    private boolean handleDirSep = false;
    private boolean caseSensitive = true;

    public void setHandleDirSep(boolean handleDirSep) {
        this.handleDirSep = handleDirSep;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setFrom(String from) {
        int index = from.lastIndexOf("*");
        if (index == -1) {
            this.fromPrefix = from;
            this.fromPostfix = "";
        } else {
            this.fromPrefix = from.substring(0, index);
            this.fromPostfix = from.substring(index + 1);
        }
        this.prefixLength = this.fromPrefix.length();
        this.postfixLength = this.fromPostfix.length();
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setTo(String to) {
        int index = to.lastIndexOf("*");
        if (index == -1) {
            this.toPrefix = to;
            this.toPostfix = "";
        } else {
            this.toPrefix = to.substring(0, index);
            this.toPostfix = to.substring(index + 1);
        }
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public String[] mapFileName(String sourceFileName) {
        if (this.fromPrefix == null || !modifyName(sourceFileName).startsWith(modifyName(this.fromPrefix)) || !modifyName(sourceFileName).endsWith(modifyName(this.fromPostfix))) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.toPrefix);
        stringBuffer.append(extractVariablePart(sourceFileName));
        stringBuffer.append(this.toPostfix);
        return new String[]{stringBuffer.toString()};
    }

    protected String extractVariablePart(String name) {
        return name.substring(this.prefixLength, name.length() - this.postfixLength);
    }

    private String modifyName(String name) {
        if (!this.caseSensitive) {
            name = name.toLowerCase();
        }
        if (this.handleDirSep && name.indexOf(92) != -1) {
            return name.replace('\\', '/');
        }
        return name;
    }
}
