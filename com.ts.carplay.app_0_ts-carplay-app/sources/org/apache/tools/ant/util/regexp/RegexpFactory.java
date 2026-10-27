package org.apache.tools.ant.util.regexp;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/* JADX INFO: loaded from: classes.dex */
public class RegexpFactory extends RegexpMatcherFactory {
    public Regexp newRegexp() throws BuildException {
        return newRegexp(null);
    }

    public Regexp newRegexp(Project p) throws BuildException {
        String systemDefault;
        if (p == null) {
            systemDefault = System.getProperty("ant.regexp.regexpimpl");
        } else {
            systemDefault = p.getProperty("ant.regexp.regexpimpl");
        }
        if (systemDefault != null) {
            return createRegexpInstance(systemDefault);
        }
        try {
            testAvailability("java.util.regex.Matcher");
            return createRegexpInstance("org.apache.tools.ant.util.regexp.Jdk14RegexpRegexp");
        } catch (BuildException e) {
            try {
                testAvailability("org.apache.oro.text.regex.Pattern");
                return createRegexpInstance("org.apache.tools.ant.util.regexp.JakartaOroRegexp");
            } catch (BuildException e2) {
                try {
                    testAvailability("org.apache.regexp.RE");
                    return createRegexpInstance("org.apache.tools.ant.util.regexp.JakartaRegexpRegexp");
                } catch (BuildException e3) {
                    throw new BuildException("No supported regular expression matcher found");
                }
            }
        }
    }

    protected Regexp createRegexpInstance(String classname) throws BuildException {
        RegexpMatcher m = createInstance(classname);
        if (m instanceof Regexp) {
            return (Regexp) m;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(classname);
        stringBuffer.append(" doesn't implement the Regexp interface");
        throw new BuildException(stringBuffer.toString());
    }
}
