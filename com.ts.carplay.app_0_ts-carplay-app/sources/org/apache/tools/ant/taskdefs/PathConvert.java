package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.condition.Os;
import org.apache.tools.ant.types.DirSet;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.FileNameMapper;

/* JADX INFO: loaded from: classes.dex */
public class PathConvert extends Task {
    private boolean onWindows;
    private Path path = null;
    private Reference refid = null;
    private String targetOS = null;
    private boolean targetWindows = false;
    private boolean setonempty = true;
    private String property = null;
    private Vector prefixMap = new Vector();
    private String pathSep = null;
    private String dirSep = null;
    private Mapper mapper = null;

    public PathConvert() {
        this.onWindows = false;
        this.onWindows = Os.isFamily("dos");
    }

    public class MapEntry {
        private String from = null;
        private String to = null;

        public MapEntry() {
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String apply(String elem) {
            if (this.from != null && this.to != null) {
                String cmpElem = PathConvert.this.onWindows ? elem.toLowerCase() : elem;
                String cmpFrom = PathConvert.this.onWindows ? this.from.toLowerCase() : this.from;
                if (!cmpElem.startsWith(cmpFrom)) {
                    return elem;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(this.to);
                stringBuffer.append(elem.substring(this.from.length()));
                return stringBuffer.toString();
            }
            throw new BuildException("Both 'from' and 'to' must be set in a map entry");
        }
    }

    public static class TargetOs extends EnumeratedAttribute {
        @Override // org.apache.tools.ant.types.EnumeratedAttribute
        public String[] getValues() {
            return new String[]{"windows", "unix", "netware", "os/2", "tandem"};
        }
    }

    public Path createPath() {
        if (isReference()) {
            throw noChildrenAllowed();
        }
        if (this.path == null) {
            this.path = new Path(getProject());
        }
        return this.path.createPath();
    }

    public MapEntry createMap() {
        MapEntry entry = new MapEntry();
        this.prefixMap.addElement(entry);
        return entry;
    }

    public void setTargetos(String target) {
        TargetOs to = new TargetOs();
        to.setValue(target);
        setTargetos(to);
    }

    public void setTargetos(TargetOs target) {
        String value = target.getValue();
        this.targetOS = value;
        this.targetWindows = (value.equals("unix") || this.targetOS.equals("tandem")) ? false : true;
    }

    public void setSetonempty(boolean setonempty) {
        this.setonempty = setonempty;
    }

    public void setProperty(String p) {
        this.property = p;
    }

    public void setRefid(Reference r) {
        if (this.path != null) {
            throw noChildrenAllowed();
        }
        this.refid = r;
    }

    public void setPathSep(String sep) {
        this.pathSep = sep;
    }

    public void setDirSep(String sep) {
        this.dirSep = sep;
    }

    public boolean isReference() {
        return this.refid != null;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Path savedPath = this.path;
        String savedPathSep = this.pathSep;
        String savedDirSep = this.dirSep;
        try {
            if (isReference()) {
                this.path = new Path(getProject()).createPath();
                Object obj = this.refid.getReferencedObject(getProject());
                if (obj instanceof Path) {
                    this.path.setRefid(this.refid);
                } else if (obj instanceof FileSet) {
                    this.path.addFileset((FileSet) obj);
                } else if (obj instanceof DirSet) {
                    this.path.addDirset((DirSet) obj);
                } else if (obj instanceof FileList) {
                    this.path.addFilelist((FileList) obj);
                } else {
                    throw new BuildException("'refid' does not refer to a path, fileset, dirset, or filelist.");
                }
            }
            validateSetup();
            String fromDirSep = this.onWindows ? "\\" : "/";
            StringBuffer rslt = new StringBuffer();
            String[] elems = this.path.list();
            if (this.mapper != null) {
                FileNameMapper impl = this.mapper.getImplementation();
                List ret = new ArrayList();
                for (String str : elems) {
                    String[] mapped = impl.mapFileName(str);
                    for (int m = 0; mapped != null && m < mapped.length; m++) {
                        ret.add(mapped[m]);
                    }
                }
                elems = (String[]) ret.toArray(new String[0]);
            }
            for (int i = 0; i < elems.length; i++) {
                String elem = mapElement(elems[i]);
                if (i != 0) {
                    rslt.append(this.pathSep);
                }
                StringTokenizer stDirectory = new StringTokenizer(elem, fromDirSep, true);
                while (stDirectory.hasMoreTokens()) {
                    String token = stDirectory.nextToken();
                    rslt.append(fromDirSep.equals(token) ? this.dirSep : token);
                }
            }
            if (this.setonempty || rslt.length() > 0) {
                String value = rslt.toString();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Set property ");
                stringBuffer.append(this.property);
                stringBuffer.append(" = ");
                stringBuffer.append(value);
                log(stringBuffer.toString(), 3);
                getProject().setNewProperty(this.property, value);
            }
        } finally {
            this.path = savedPath;
            this.dirSep = savedDirSep;
            this.pathSep = savedPathSep;
        }
    }

    private String mapElement(String elem) {
        int size = this.prefixMap.size();
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                MapEntry entry = (MapEntry) this.prefixMap.elementAt(i);
                String newElem = entry.apply(elem);
                if (newElem != elem) {
                    return newElem;
                }
            }
            return elem;
        }
        return elem;
    }

    public void addMapper(Mapper mapper) {
        if (this.mapper != null) {
            throw new BuildException("Cannot define more than one mapper");
        }
        this.mapper = mapper;
    }

    public void add(FileNameMapper fileNameMapper) {
        Mapper m = new Mapper(getProject());
        m.add(fileNameMapper);
        addMapper(m);
    }

    private void validateSetup() throws BuildException {
        if (this.path == null) {
            throw new BuildException("You must specify a path to convert");
        }
        if (this.property == null) {
            throw new BuildException("You must specify a property");
        }
        String dsep = File.separator;
        String psep = File.pathSeparator;
        if (this.targetOS != null) {
            psep = this.targetWindows ? ";" : ":";
            dsep = this.targetWindows ? "\\" : "/";
        }
        if (this.pathSep != null) {
            psep = this.pathSep;
        }
        if (this.dirSep != null) {
            dsep = this.dirSep;
        }
        this.pathSep = psep;
        this.dirSep = dsep;
    }

    private BuildException noChildrenAllowed() {
        return new BuildException("You must not specify nested <path> elements when using the refid attribute.");
    }
}
