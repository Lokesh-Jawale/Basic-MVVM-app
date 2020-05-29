package com.phantomapps.lokeshjawalesbnri;

import android.os.AsyncTask;
import android.util.Log;

import com.phantomapps.lokeshjawalesbnri.model.DataEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class QuerryData {

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

    public final String GET_DATA_URL = "https://api.github.com/orgs/octokit/repos?page=1&per_page=10";

    public DataEntity list;

    public ArrayList<DataEntity> extractDataFromUrl() {

        //make http connection
        //read the response
        //save the response in string
        //scrap the string for the required data
        //set the fields in listcontentprovider class
        //add the data object to listcontentprovider

        URL url = null;
        try{
            url = new URL(GET_DATA_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        ArrayList<DataEntity> arrayList = new ArrayList<>();

        try {
            String responseData = makeHttpRequest(url);

            try {
                JSONArray jsonRootArray = new JSONArray(responseData);
                for(int i = 0; i < jsonRootArray.length(); i++){

                    try {
                        JSONObject jsonObject = jsonRootArray.getJSONObject(i);

                        this.name = jsonObject.getString("name");
                        this.des = jsonObject.getString("description");
                        this.open_issues_count = jsonObject.getString("open_issues_count");

                        if(!jsonObject.isNull("license")) {
                            JSONObject licenseObject = jsonObject.getJSONObject("license");
                            this.key = licenseObject.getString("key");
                            this.licenseName = licenseObject.getString("name");
                            this.spdxId = licenseObject.getString("spdx_id");
                            this.url = licenseObject.getString("url");
                            this.nodeId = licenseObject.getString("node_id");
                        }
                        else {
                            Log.d("/////// ", "///////////  name " + this.name);
                            this.key = "-";
                            this.licenseName = "-";
                            this.spdxId = "-";
                            this.url = "-";
                            this.nodeId = "-";
                        }

                        Log.d("/////// ", "///////////  name " + this.name + " key " + this.key);

                        if(jsonObject.has("permissions")) {
                            JSONObject permissionObject = jsonObject.getJSONObject("permissions");
                            this.admin = permissionObject.getString("admin");
                            this.push = permissionObject.getString("push");
                            this.pull = permissionObject.getString("pull");
                        }
                    }catch (JSONException e){
                        e.getMessage();
                        Log.d("aDebugTag", "//////////////////////  " );
                    }

                    list = new DataEntity(
                            name,
                            des,
                            open_issues_count,
                            key,
                            licenseName,
                            spdxId,
                            this.url,
                            nodeId,
                            admin,
                            push,
                            pull
                    );


                    arrayList.add(list);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return arrayList;
    }



    public String makeHttpRequest(URL url) {
        String response = null;

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();

            response = readInputStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }

    public String readInputStream(InputStream inputStream) {
        StringBuilder response = new StringBuilder();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
            String line = bufferedReader.readLine();
            while(line != null){
                response.append(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

}
