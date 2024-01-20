package com.example.lab5_music;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, InputStream> {
    private DownloadListener listener;

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    protected InputStream doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return new BufferedInputStream(connection.getInputStream());
            }
        } catch (IOException e) {
            Log.e("DownloadTask", "Error downloading file: " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        if (inputStream != null) {
            listener.onDownloadComplete(inputStream);
        } else {
            listener.onDownloadFailed();
        }
    }

    public interface DownloadListener {
        void onDownloadComplete(InputStream inputStream);

        void onDownloadFailed();
    }
}
