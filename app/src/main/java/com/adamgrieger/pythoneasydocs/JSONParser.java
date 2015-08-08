package com.adamgrieger.pythoneasydocs;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Class for dealing with Python EasyDocs' JSON files that store documentation information.
 */
public class JSONParser {

    private Context context;

    private static final String TAG = "MainActivity";

    private String[] supportedVersions = {
            "3.4.3",
            "3.3.6",
            "2.7.10"
    };

    private ArrayList<PythonDocument> availableDocs = new ArrayList<>();
    private ArrayList<PythonDocument> downloadedDocs = new ArrayList<>();

    private File availableJSON;
    private File downloadedJSON;

    /**
     * Constructor for JSONParser.
     *
     * @param context The application's Context
     */
    public JSONParser(Context context) {
        this.context = context;

        availableJSON = new File(ContextCompat.getExternalFilesDirs(context, null)[0], "available_docs.json");
        downloadedJSON = new File(ContextCompat.getExternalFilesDirs(context, null)[0], "downloaded_docs.json");

        if (availableJSON.exists() && downloadedJSON.exists()) {
            loadJSONFiles();
        } else {
            createNewJSONFiles();
        }
    }

    /**
     * Creates clean JSON files for Python EasyDocs ("default" files).
     */
    public void createNewJSONFiles() {
        deleteJSONFiles();

        if (StorageChecker.isExternalStorageWritable()) {
            try {
                if (availableJSON.createNewFile()) {
                    Log.i(TAG, "available_docs.json has been created");
                }

                if (downloadedJSON.createNewFile()) {
                    Log.i(TAG, "downloaded_docs.json has been created");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (String version : supportedVersions) {
                availableDocs.add(new PythonDocument(version, "HTML"));
                availableDocs.add(new PythonDocument(version, "PDF"));
                availableDocs.add(new PythonDocument(version, "TXT"));
            }

            Gson gson = new Gson();
            FileOutputStream outputStream;

            try {
                outputStream = context.openFileOutput("available_docs.json", Context.MODE_PRIVATE);
                outputStream.write(gson.toJson(availableDocs).getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "External storage is unavailable for writing!");
        }
    }

    /**
     * Deletes both of Python EasyDocs' JSON files.
     */
    public void deleteJSONFiles() {
        if (StorageChecker.isExternalStorageWritable()) {
            if (availableJSON.delete()) {
                Log.i(TAG, "available_docs.json has been deleted");
            } else {
                Log.i(TAG, "available_docs.json cannot be deleted, because it does not exist");
            }

            if (downloadedJSON.delete()) {
                Log.i(TAG, "downloaded_docs.json has been deleted");
            } else {
                Log.i(TAG, "downloaded_docs.json cannot be deleted, because it does not exist");
            }
        } else {
            Log.e(TAG, "External storage is unavailable for writing!");
        }
    }

    /**
     * Loads both of Python EasyDocs' JSON files.
     */
    public void loadJSONFiles() {
        Gson gson = new Gson();

        if (StorageChecker.isExternalStorageReadable()) {
            try {
                availableDocs = gson.fromJson(new BufferedReader(new FileReader(availableJSON)), new TypeToken<ArrayList<PythonDocument>>(){}.getType());
                downloadedDocs = gson.fromJson(new BufferedReader(new FileReader(downloadedJSON)), new TypeToken<ArrayList<PythonDocument>>(){}.getType());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "External storage is unavailable for writing!");
        }
    }

    /**
     * Edits both of Python EasyDocs' JSON files and makes sure the changes are saved.
     */
    public void updateJSONFiles() {
        deleteJSONFiles();

        if (StorageChecker.isExternalStorageWritable()) {
            try {
                FileOutputStream outputStream = context.openFileOutput("available_docs.json", Context.MODE_PRIVATE);
                outputStream.write(new Gson().toJson(availableDocs).getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                FileOutputStream outputStream = context.openFileOutput("downloaded_docs.json", Context.MODE_PRIVATE);
                outputStream.write(new Gson().toJson(downloadedDocs).getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "External storage is unavailable for writing!");
        }
    }

    // -----------
    // | Getters |
    // -----------

    public String[] getSupportedVersions() {
        return supportedVersions;
    }

    public ArrayList<PythonDocument> getAvailableDocs() {
        return availableDocs;
    }

    public ArrayList<PythonDocument> getDownloadedDocs() {
        return downloadedDocs;
    }

    // -----------
    // | Setters |
    // -----------

    public void setSupportedVersions(String[] supportedVersions) {
        this.supportedVersions = supportedVersions;
    }

    public void setAvailableDocs(ArrayList<PythonDocument> availableDocs) {
        this.availableDocs = availableDocs;
    }

    public void setDownloadedDocs(ArrayList<PythonDocument> downloadedDocs) {
        this.downloadedDocs = downloadedDocs;
    }
}
