package ts.car.diag.bean;

import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class VrPackageBean {
    private String buildTime;
    private String commit;
    private List<ComponentsBean> components;
    private String language;
    private String name;
    private String tag;
    private String version;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class ComponentsBean {
        private String buildTime;
        private String commit;
        private String name;
        private String tag;
        private String version;

        public String getBuildTime() {
            return this.buildTime;
        }

        public String getCommit() {
            return this.commit;
        }

        public String getName() {
            return this.name;
        }

        public String getTag() {
            return this.tag;
        }

        public String getVersion() {
            return this.version;
        }

        public void setBuildTime(String str) {
            this.buildTime = str;
        }

        public void setCommit(String str) {
            this.commit = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setTag(String str) {
            this.tag = str;
        }

        public void setVersion(String str) {
            this.version = str;
        }
    }

    public String getBuildTime() {
        return this.buildTime;
    }

    public String getCommit() {
        return this.commit;
    }

    public List<ComponentsBean> getComponents() {
        return this.components;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getName() {
        return this.name;
    }

    public String getTag() {
        return this.tag;
    }

    public String getVersion() {
        return this.version;
    }

    public void setBuildTime(String str) {
        this.buildTime = str;
    }

    public void setCommit(String str) {
        this.commit = str;
    }

    public void setComponents(List<ComponentsBean> list) {
        this.components = list;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
