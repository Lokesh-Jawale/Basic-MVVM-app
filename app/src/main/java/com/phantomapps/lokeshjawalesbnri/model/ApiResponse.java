package com.phantomapps.lokeshjawalesbnri.model;

public class ApiResponse {

    public String name;
    public String description;
    public String open_issues_count;
    public License license;
    public Permissions permissions;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOpen_issues_count() {
        return open_issues_count;
    }

    public class License {
        public String key;
        public String name;
        public String spdx_id;
        public String url;
        public String node_id;

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        public String getSpdx_id() {
            return spdx_id;
        }

        public String getUrl() {
            return url;
        }

        public String getNode_id() {
            return node_id;
        }
    }

    public class Permissions {
        public String admin;
        public String push;
        public String pull;

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

}
