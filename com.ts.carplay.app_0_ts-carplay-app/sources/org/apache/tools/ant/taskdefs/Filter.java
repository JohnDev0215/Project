package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class Filter extends Task {
    private File filtersFile;
    private String token;
    private String value;

    public void setToken(String token) {
        this.token = token;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setFiltersfile(File filtersFile) {
        this.filtersFile = filtersFile;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        boolean isFiltersFromFile = this.filtersFile != null && this.token == null && this.value == null;
        boolean isSingleFilter = (this.filtersFile != null || this.token == null || this.value == null) ? false : true;
        if (!isFiltersFromFile && !isSingleFilter) {
            throw new BuildException("both token and value parameters, or only a filtersFile parameter is required", getLocation());
        }
        if (isSingleFilter) {
            getProject().getGlobalFilterSet().addFilter(this.token, this.value);
        }
        if (isFiltersFromFile) {
            readFilters();
        }
    }

    protected void readFilters() throws BuildException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Reading filters from ");
        stringBuffer.append(this.filtersFile);
        log(stringBuffer.toString(), 3);
        getProject().getGlobalFilterSet().readFiltersFromFile(this.filtersFile);
    }
}
