package org.apache.tools.ant.util;

import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.regexp.RegexpMatcher;
import org.apache.tools.ant.util.regexp.RegexpMatcherFactory;

/* JADX INFO: loaded from: classes.dex */
public class RegexpPatternMapper implements FileNameMapper {
    protected RegexpMatcher reg;
    protected char[] to = null;
    protected StringBuffer result = new StringBuffer();
    private boolean handleDirSep = false;
    private int regexpOptions = 0;

    public RegexpPatternMapper() throws BuildException {
        this.reg = null;
        this.reg = new RegexpMatcherFactory().newRegexpMatcher();
    }

    public void setHandleDirSep(boolean handleDirSep) {
        this.handleDirSep = handleDirSep;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        if (!caseSensitive) {
            this.regexpOptions = 256;
        } else {
            this.regexpOptions = 0;
        }
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setFrom(String from) throws BuildException {
        try {
            this.reg.setPattern(from);
        } catch (NoClassDefFoundError e) {
            throw new BuildException("Cannot load regular expression matcher", e);
        }
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setTo(String to) {
        this.to = to.toCharArray();
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public String[] mapFileName(String sourceFileName) {
        if (this.handleDirSep && sourceFileName.indexOf("\\") != -1) {
            sourceFileName = sourceFileName.replace('\\', '/');
        }
        RegexpMatcher regexpMatcher = this.reg;
        if (regexpMatcher == null || this.to == null || !regexpMatcher.matches(sourceFileName, this.regexpOptions)) {
            return null;
        }
        return new String[]{replaceReferences(sourceFileName)};
    }

    protected String replaceReferences(String source) {
        Vector v = this.reg.getGroups(source, this.regexpOptions);
        this.result.setLength(0);
        int i = 0;
        while (true) {
            char[] cArr = this.to;
            if (i < cArr.length) {
                if (cArr[i] == '\\') {
                    i++;
                    if (i < cArr.length) {
                        int value = Character.digit(cArr[i], 10);
                        if (value > -1) {
                            this.result.append((String) v.elementAt(value));
                        } else {
                            this.result.append(this.to[i]);
                        }
                    } else {
                        this.result.append('\\');
                    }
                } else {
                    this.result.append(cArr[i]);
                }
                i++;
            } else {
                return this.result.substring(0);
            }
        }
    }
}
