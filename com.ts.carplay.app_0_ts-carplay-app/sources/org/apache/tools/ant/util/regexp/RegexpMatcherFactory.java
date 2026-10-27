package org.apache.tools.ant.util.regexp;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

/* JADX INFO: loaded from: classes.dex */
public class RegexpMatcherFactory {
    public RegexpMatcher newRegexpMatcher() throws BuildException {
        return newRegexpMatcher(null);
    }

    public RegexpMatcher newRegexpMatcher(Project p) throws BuildException {
        String systemDefault;
        if (p == null) {
            systemDefault = System.getProperty("ant.regexp.regexpimpl");
        } else {
            systemDefault = p.getProperty("ant.regexp.regexpimpl");
        }
        if (systemDefault != null) {
            return createInstance(systemDefault);
        }
        try {
            testAvailability("java.util.regex.Matcher");
            return createInstance("org.apache.tools.ant.util.regexp.Jdk14RegexpMatcher");
        } catch (BuildException e) {
            try {
                testAvailability("org.apache.oro.text.regex.Pattern");
                return createInstance("org.apache.tools.ant.util.regexp.JakartaOroMatcher");
            } catch (BuildException e2) {
                try {
                    testAvailability("org.apache.regexp.RE");
                    return createInstance("org.apache.tools.ant.util.regexp.JakartaRegexpMatcher");
                } catch (BuildException e3) {
                    throw new BuildException("No supported regular expression matcher found");
                }
            }
        }
    }

    protected RegexpMatcher createInstance(String className) throws BuildException {
        try {
            return (RegexpMatcher) Class.forName(className).newInstance();
        } catch (Throwable t) {
            throw new BuildException(t);
        }
    }

    protected void testAvailability(String className) throws BuildException {
        try {
            Class.forName(className);
        } catch (Throwable t) {
            throw new BuildException(t);
        }
    }
}
