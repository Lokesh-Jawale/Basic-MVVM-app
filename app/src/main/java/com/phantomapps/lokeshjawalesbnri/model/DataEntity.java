package com.phantomapps.lokeshjawalesbnri.model;

public class DataEntity {

    private String name;
    private String des;
    private String open_issues_count;
    private String key;
    private String licenseName;
    private String spdxId;
    private String url;
    private String nodeId;
    private String admin;
    private String push;
    private String pull;

    public DataEntity(
            String name, String des, String open_issues_count, String key,
            String licenseName, String spdxId, String url, String nodeId,
            String admin, String push, String pull) {
        this.name = name;
        this.des = des;
        this.open_issues_count = open_issues_count;
        this.key = key;
        this.licenseName = licenseName;
        this.spdxId = spdxId;
        this.url = url;
        this.nodeId = nodeId;
        this.admin = admin;
        this.push = push;
        this.pull = pull;
    }


    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public String getOpen_issues_count() {
        return open_issues_count;
    }

    public String getKey() {
        return key;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public String getSpdxId() {
        return spdxId;
    }

    public String getUrl() {
        return url;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getAdmin() {
        return admin;
    }

    public String getPush() {
        return push;
    }

    public String getPull() {
        return pull;
    }

}
