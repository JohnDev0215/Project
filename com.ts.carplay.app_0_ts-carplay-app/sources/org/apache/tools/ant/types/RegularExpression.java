package org.apache.tools.ant.types;

import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.util.regexp.Regexp;
import org.apache.tools.ant.util.regexp.RegexpFactory;

/* JADX INFO: loaded from: classes.dex */
public class RegularExpression extends DataType {
    public static final String DATA_TYPE_NAME = "regexp";
    private static final RegexpFactory FACTORY = new RegexpFactory();
    private String myPattern;
    private boolean alreadyInit = false;
    private Regexp regexp = null;
    private boolean setPatternPending = false;

    private void init(Project p) {
        if (!this.alreadyInit) {
            this.regexp = FACTORY.newRegexp(p);
            this.alreadyInit = true;
        }
    }

    private void setPattern() {
        if (this.setPatternPending) {
            this.regexp.setPattern(this.myPattern);
            this.setPatternPending = false;
        }
    }

    public void setPattern(String pattern) {
        Regexp regexp = this.regexp;
        if (regexp == null) {
            this.myPattern = pattern;
            this.setPatternPending = true;
        } else {
            regexp.setPattern(pattern);
        }
    }

    public String getPattern(Project p) {
        init(p);
        if (isReference()) {
            return getRef(p).getPattern(p);
        }
        setPattern();
        return this.regexp.getPattern();
    }

    public Regexp getRegexp(Project p) {
        init(p);
        if (isReference()) {
            return getRef(p).getRegexp(p);
        }
        setPattern();
        return this.regexp;
    }

    public RegularExpression getRef(Project p) {
        if (!isChecked()) {
            Stack stk = new Stack();
            stk.push(this);
            dieOnCircularReference(stk, p);
        }
        Object o = getRefid().getReferencedObject(p);
        if (!(o instanceof RegularExpression)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getRefid().getRefId());
            stringBuffer.append(" doesn't denote a ");
            stringBuffer.append(DATA_TYPE_NAME);
            String msg = stringBuffer.toString();
            throw new BuildException(msg);
        }
        return (RegularExpression) o;
    }
}
