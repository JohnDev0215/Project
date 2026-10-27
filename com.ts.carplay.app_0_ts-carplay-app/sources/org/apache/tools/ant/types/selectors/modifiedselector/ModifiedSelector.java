package org.apache.tools.ant.types.selectors.modifiedselector;

import java.io.File;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.IntrospectionHelper;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.types.selectors.BaseExtendSelector;

/* JADX INFO: loaded from: classes.dex */
public class ModifiedSelector extends BaseExtendSelector {
    private Cache cache = null;
    private Algorithm algorithm = null;
    private Comparator comparator = null;
    private boolean update = true;
    private boolean selectDirectories = true;
    private boolean isConfigured = false;
    private AlgorithmName algoName = null;
    private CacheName cacheName = null;
    private ComparatorName compName = null;
    private Vector configParameter = new Vector();
    private Vector specialParameter = new Vector();

    @Override // org.apache.tools.ant.types.selectors.BaseSelector
    public void verifySettings() {
        configure();
        Cache cache = this.cache;
        if (cache == null) {
            setError("Cache must be set.");
            return;
        }
        if (this.algorithm == null) {
            setError("Algorithm must be set.");
        } else if (!cache.isValid()) {
            setError("Cache must be proper configured.");
        } else if (!this.algorithm.isValid()) {
            setError("Algorithm must be proper configured.");
        }
    }

    public void configure() {
        File cachefile;
        if (this.isConfigured) {
            return;
        }
        this.isConfigured = true;
        Project project = getProject();
        if (project != null) {
            cachefile = new File(project.getBaseDir(), "cache.properties");
        } else {
            cachefile = new File("cache.properties");
        }
        this.cache = new PropertiesfileCache(cachefile);
        this.algorithm = new DigestAlgorithm();
        this.comparator = new EqualComparator();
        this.update = true;
        this.selectDirectories = true;
        for (Parameter par : this.configParameter) {
            if (par.getName().indexOf(".") > 0) {
                this.specialParameter.add(par);
            } else {
                useParameter(par);
            }
        }
        this.configParameter = new Vector();
        String className = null;
        if (this.algorithm == null) {
            if ("hashvalue".equals(this.algoName.getValue())) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("org.apache.tools.ant.types.selectors.cacheselector");
                stringBuffer.append(".HashvalueAlgorithm");
                className = stringBuffer.toString();
            } else if ("digest".equals(this.algoName.getValue())) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("org.apache.tools.ant.types.selectors.cacheselector");
                stringBuffer2.append(".DigestAlgorithm");
                className = stringBuffer2.toString();
            }
            if (className != null) {
                try {
                    this.algorithm = (Algorithm) Class.forName(className).newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (this.cache == null) {
            if ("propertyfile".equals(this.cacheName.getValue())) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("org.apache.tools.ant.types.selectors.cacheselector");
                stringBuffer3.append(".PropertiesfileCache");
                className = stringBuffer3.toString();
            }
            if (className != null) {
                try {
                    this.cache = (Cache) Class.forName(className).newInstance();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (this.comparator == null) {
            if ("equal".equals(this.compName.getValue())) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("org.apache.tools.ant.types.selectors.cacheselector");
                stringBuffer4.append(".EqualComparator");
                className = stringBuffer4.toString();
            } else if ("role".equals(this.compName.getValue())) {
                className = "java.text.RuleBasedCollator";
            }
            if (className != null) {
                try {
                    this.comparator = (Comparator) Class.forName(className).newInstance();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        Iterator itSpecial = this.specialParameter.iterator();
        while (itSpecial.hasNext()) {
            useParameter((Parameter) itSpecial.next());
        }
        this.specialParameter = new Vector();
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.selectors.BaseSelector, org.apache.tools.ant.types.selectors.FileSelector
    public boolean isSelected(File basedir, String filename, File file) {
        validate();
        File f = new File(basedir, filename);
        if (f.isDirectory()) {
            return this.selectDirectories;
        }
        String cachedValue = String.valueOf(this.cache.get(f.getAbsolutePath()));
        String newValue = this.algorithm.getValue(f);
        boolean rv = this.comparator.compare(cachedValue, newValue) != 0;
        if (this.update && !cachedValue.equals(newValue)) {
            this.cache.put(f.getAbsolutePath(), newValue);
            this.cache.save();
        }
        return rv;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public void setSeldirs(boolean seldirs) {
        this.selectDirectories = seldirs;
    }

    public void addParam(String key, Object value) {
        Parameter par = new Parameter();
        par.setName(key);
        par.setValue(String.valueOf(value));
        this.configParameter.add(par);
    }

    public void addParam(Parameter parameter) {
        this.configParameter.add(parameter);
    }

    @Override // org.apache.tools.ant.types.selectors.BaseExtendSelector, org.apache.tools.ant.types.Parameterizable
    public void setParameters(Parameter[] parameters) {
        if (parameters != null) {
            for (Parameter parameter : parameters) {
                this.configParameter.add(parameter);
            }
        }
    }

    public void useParameter(Parameter parameter) {
        String key = parameter.getName();
        String value = parameter.getValue();
        if ("cache".equals(key)) {
            CacheName cn = new CacheName();
            cn.setValue(value);
            setCache(cn);
            return;
        }
        if ("algorithm".equals(key)) {
            AlgorithmName an = new AlgorithmName();
            an.setValue(value);
            setAlgorithm(an);
            return;
        }
        if ("comparator".equals(key)) {
            ComparatorName cn2 = new ComparatorName();
            cn2.setValue(value);
            setComparator(cn2);
            return;
        }
        if ("update".equals(key)) {
            boolean updateValue = "true".equalsIgnoreCase(value);
            setUpdate(updateValue);
            return;
        }
        if ("seldirs".equals(key)) {
            boolean sdValue = "true".equalsIgnoreCase(value);
            setSeldirs(sdValue);
            return;
        }
        if (key.startsWith("cache.")) {
            String name = key.substring(6);
            tryToSetAParameter(this.cache, name, value);
            return;
        }
        if (key.startsWith("algorithm.")) {
            String name2 = key.substring(10);
            tryToSetAParameter(this.algorithm, name2, value);
        } else if (key.startsWith("comparator.")) {
            String name3 = key.substring(11);
            tryToSetAParameter(this.comparator, name3, value);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid parameter ");
            stringBuffer.append(key);
            setError(stringBuffer.toString());
        }
    }

    protected void tryToSetAParameter(Object obj, String name, String value) {
        Project prj = getProject() != null ? getProject() : new Project();
        IntrospectionHelper iHelper = IntrospectionHelper.getHelper(prj, obj.getClass());
        try {
            iHelper.setAttribute(prj, obj, name, value);
        } catch (BuildException e) {
        }
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("{modifiedselector");
        buf.append(" update=");
        buf.append(this.update);
        buf.append(" seldirs=");
        buf.append(this.selectDirectories);
        buf.append(" cache=");
        buf.append(this.cache);
        buf.append(" algorithm=");
        buf.append(this.algorithm);
        buf.append(" comparator=");
        buf.append(this.comparator);
        buf.append("}");
        return buf.toString();
    }

    public Cache getCache() {
        return this.cache;
    }

    public void setCache(CacheName name) {
        this.cacheName = name;
    }

    public static class CacheName extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"propertyfile"};
        }
    }

    public Algorithm getAlgorithm() {
        return this.algorithm;
    }

    public void setAlgorithm(AlgorithmName name) {
        this.algoName = name;
    }

    public static class AlgorithmName extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"hashvalue", "digest"};
        }
    }

    public Comparator getComparator() {
        return this.comparator;
    }

    public void setComparator(ComparatorName name) {
        this.compName = name;
    }

    public static class ComparatorName extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"equal", "rule"};
        }
    }
}
